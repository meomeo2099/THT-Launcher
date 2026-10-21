package vn.tht.launcher.vehicle

import android.os.Handler
import android.os.Looper
import java.util.Locale
import java.util.concurrent.Executors
import vn.tht.launcher.vehicle.EcarxFunctions as F

/**
 * Lệnh điều khiển xe (điều hoà, chế độ lái, đèn viền) qua [EcarxCarSource].
 *
 * Mỗi lệnh: kiểm tra xe hỗ trợ -> kiểm tra an toàn -> ghi -> chờ -> ĐỌC LẠI, và chỉ báo thành công
 * khi giá trị đọc lại khớp. Chạy trên luồng nền (IPC), kết quả trả về main thread.
 *
 * Ghi bị chặn sẵn trong [EcarxCarSource] nếu APK không ký bằng khoá platform của firmware.
 */
class EcarxController(
    private val ecarx: EcarxCarSource,
    /** Tốc độ hiện tại (km/h) từ CarPropertySource; null = không đọc được. */
    private val speedKmh: () -> Float?,
    /** Số hiện tại ("P", "R", "N", "D"…); null = không đọc được. */
    private val gear: () -> String? = { null },
) {
    data class Result(val ok: Boolean, val message: String)

    /** Mức an toàn của lệnh (theo spec mục 4.4 / 8). */
    enum class Safety { SAFE, BELOW_80, PARKED, PARKED_IN_P }

    private val io = Executors.newSingleThreadExecutor()
    private val main = Handler(Looper.getMainLooper())

    val canWrite: Boolean get() = ecarx.connected && ecarx.writeAllowed

    fun supported(id: Int, zone: Int = F.ZONE_ALL): Boolean = ecarx.isSupported(id, zone) == true

    private fun run(done: (Result) -> Unit, block: () -> Result) {
        io.execute {
            val r = try {
                if (!ecarx.connected) Result(false, "Chưa nối được ECARX")
                else if (!ecarx.writeAllowed) Result(false, "Chưa xác minh chữ ký platform – chỉ đọc")
                else block()
            } catch (t: Throwable) {
                Result(false, "Lỗi: ${t.message ?: t.javaClass.simpleName}")
            }
            main.post { done(r) }
        }
    }

    private fun unsupported() = Result(false, "Xe không hỗ trợ tính năng này")

    private fun safetyBlock(safety: Safety): Result? {
        if (safety == Safety.SAFE) return null
        val speed = speedKmh()
        if (safety == Safety.BELOW_80) {
            return if (speed == null || speed > 80f) Result(false, "Không mở kính/cửa sổ trời khi xe chạy trên 80 km/h") else null
        }
        if (speed == null || speed >= 1f) return Result(false, "Chỉ làm được khi xe đứng yên")
        if (safety == Safety.PARKED_IN_P && gear() != "P") return Result(false, "Chỉ làm được khi xe ở số P")
        return null
    }

    /** Chạy [block] trên luồng nền của controller, trả kết quả về main thread. */
    fun <T> background(block: () -> T, done: (T) -> Unit) {
        io.execute {
            val v = runCatching(block).getOrNull()
            @Suppress("UNCHECKED_CAST")
            main.post { done(v as T) }
        }
    }

    // ---- Thao tác tổng quát (dùng cho Trung tâm điều khiển) ----

    fun setValue(id: Int, zone: Int, value: Int, okMsg: String, safety: Safety = Safety.SAFE, done: (Result) -> Unit) =
        run(done) { safetyBlock(safety) ?: setIntVerified(id, zone, value, okMsg) }

    fun toggleFn(id: Int, zone: Int, name: String, safety: Safety = Safety.SAFE, done: (Result) -> Unit) = run(done) {
        safetyBlock(safety)?.let { return@run it }
        if (!supported(id, zone)) return@run unsupported()
        val on = ecarx.getInt(id, zone) == F.ON
        val target = if (on) F.OFF else F.ON
        setIntVerified(id, zone, target, "$name: " + if (target == F.ON) "bật" else "tắt")
    }

    /** Chạm để sang mức kế tiếp: tắt -> 1 -> … -> [max] -> tắt. */
    fun cycleLevel(id: Int, zone: Int, max: Int, name: String, done: (Result) -> Unit) = run(done) {
        if (!supported(id, zone)) return@run unsupported()
        val cur = F.levelOf(id, ecarx.getInt(id, zone)) ?: 0
        val next = if (cur >= max) 0 else cur + 1
        setIntVerified(id, zone, F.level(id, next), if (next == 0) "$name: tắt" else "$name: mức $next")
    }

    /** Nhiệt độ theo vùng (trái/phải/tất cả). */
    fun tempStepZone(zone: Int, direction: Int, done: (Result) -> Unit) = run(done) {
        if (!supported(F.HVAC_TEMP, zone)) return@run unsupported()
        val cur = ecarx.getFloat(F.HVAC_TEMP, zone) ?: return@run Result(false, "Không đọc được nhiệt độ")
        val step = ecarx.getFloat(F.HVAC_TEMP_STEP, F.ZONE_ALL)?.takeIf { it in 0.1f..2f } ?: 0.5f
        val target = (cur + direction * step).coerceIn(F.TEMP_MIN, F.TEMP_MAX)
        if (target == cur) return@run Result(true, "Nhiệt độ đã ở mức giới hạn")
        setFloatVerified(F.HVAC_TEMP, zone, target, String.format(Locale.US, "Nhiệt độ %.1f°C", target))
    }

    /** Độ sáng màn hình theo bước của xe (BACKLIGHT_MIN/MAX/STEP). */
    fun backlightStep(direction: Int, done: (Result) -> Unit) = run(done) {
        if (!supported(F.BACKLIGHT)) return@run unsupported()
        val cur = ecarx.getInt(F.BACKLIGHT, F.ZONE_ALL) ?: return@run Result(false, "Không đọc được độ sáng")
        val min = ecarx.getInt(F.BACKLIGHT_MIN, F.ZONE_ALL) ?: 0
        val max = ecarx.getInt(F.BACKLIGHT_MAX, F.ZONE_ALL) ?: 10
        val step = ecarx.getInt(F.BACKLIGHT_STEP, F.ZONE_ALL)?.takeIf { it > 0 } ?: 1
        val target = (cur + direction * step).coerceIn(min, max)
        if (target == cur) return@run Result(true, "Độ sáng đã ở mức giới hạn")
        setIntVerified(F.BACKLIGHT, F.ZONE_ALL, target, "Độ sáng $target")
    }

    /** Ghi int rồi đọc lại tới khi khớp (tối đa ~1,2 s). */
    private fun setIntVerified(id: Int, zone: Int, value: Int, okMsg: String): Result {
        if (!supported(id, zone)) return unsupported()
        if (!ecarx.setInt(id, zone, value)) return Result(false, "Xe không nhận lệnh")
        repeat(4) {
            Thread.sleep(VERIFY_DELAY_MS)
            if (ecarx.getInt(id, zone) == value) return Result(true, okMsg)
        }
        return Result(false, "Đã gửi lệnh nhưng đọc lại chưa thấy đổi")
    }

    private fun setFloatVerified(id: Int, zone: Int, value: Float, okMsg: String): Result {
        if (!supported(id, zone)) return unsupported()
        if (!ecarx.setFloat(id, zone, value)) return Result(false, "Xe không nhận lệnh")
        repeat(4) {
            Thread.sleep(VERIFY_DELAY_MS)
            val v = ecarx.getFloat(id, zone)
            if (v != null && kotlin.math.abs(v - value) < 0.26f) return Result(true, okMsg)
        }
        return Result(false, "Đã gửi lệnh nhưng đọc lại chưa thấy đổi")
    }

    private fun toggle(id: Int, name: String, done: (Result) -> Unit) = run(done) {
        if (!supported(id)) return@run unsupported()
        val on = ecarx.getInt(id, F.ZONE_ALL) == F.ON
        val target = if (on) F.OFF else F.ON
        setIntVerified(id, F.ZONE_ALL, target, "$name: " + if (target == F.ON) "bật" else "tắt")
    }

    // ---- Điều hoà ----

    fun toggleHvacPower(done: (Result) -> Unit) = toggle(F.HVAC_POWER, "Điều hoà", done)
    fun toggleAc(done: (Result) -> Unit) = toggle(F.HVAC_AC, "A/C", done)
    fun toggleAuto(done: (Result) -> Unit) = toggle(F.HVAC_AUTO, "AUTO", done)

    /** Tăng/giảm nhiệt độ một bước (đọc HVAC_TEMP_STEP, mặc định 0,5 °C), kẹp 16–32 °C. */
    fun tempStep(direction: Int, done: (Result) -> Unit) = run(done) {
        val zone = if (supported(F.HVAC_TEMP, F.ZONE_ROW_1_LEFT)) F.ZONE_ROW_1_LEFT else F.ZONE_ALL
        if (!supported(F.HVAC_TEMP, zone)) return@run unsupported()
        val cur = ecarx.getFloat(F.HVAC_TEMP, zone) ?: return@run Result(false, "Không đọc được nhiệt độ")
        val step = ecarx.getFloat(F.HVAC_TEMP_STEP, F.ZONE_ALL)?.takeIf { it in 0.1f..2f } ?: 0.5f
        val target = (cur + direction * step).coerceIn(F.TEMP_MIN, F.TEMP_MAX)
        if (target == cur) return@run Result(true, "Nhiệt độ đã ở mức giới hạn")
        setFloatVerified(F.HVAC_TEMP, zone, target, String.format(Locale.US, "Nhiệt độ %.1f°C", target))
    }

    /** Tăng/giảm quạt một mức, kẹp 1–7. */
    fun fanStep(direction: Int, done: (Result) -> Unit) = run(done) {
        if (!supported(F.HVAC_FAN_SPEED)) return@run unsupported()
        val cur = F.levelOf(F.HVAC_FAN_SPEED, ecarx.getInt(F.HVAC_FAN_SPEED, F.ZONE_ALL)) ?: 0
        val target = (cur + direction).coerceIn(F.FAN_MIN, F.FAN_MAX)
        if (target == cur) return@run Result(true, "Quạt đã ở mức giới hạn")
        setIntVerified(F.HVAC_FAN_SPEED, F.ZONE_ALL, F.level(F.HVAC_FAN_SPEED, target), "Quạt mức $target")
    }

    // ---- Chế độ lái (chỉ khi xe đứng yên) ----

    fun setDriveMode(n: Int, done: (Result) -> Unit) = run(done) {
        val speed = speedKmh()
        if (speed == null || speed >= 1f) {
            return@run Result(false, "Chỉ đổi chế độ lái khi xe đứng yên")
        }
        val name = F.DRIVE_MODES.firstOrNull { it.first == n }?.second ?: "$n"
        setIntVerified(F.DRIVE_MODE, F.ZONE_ALL, F.level(F.DRIVE_MODE, n), "Chế độ lái: $name")
    }

    // ---- Đèn viền ----

    fun toggleAmbient(done: (Result) -> Unit) = toggle(F.AMBIENT_SWITCH, "Đèn viền", done)

    fun setAmbientColor(n: Int, done: (Result) -> Unit) = run(done) {
        val name = F.AMBIENT_COLORS.firstOrNull { it.first == n }?.second ?: "$n"
        setIntVerified(F.AMBIENT_THEME_COLOR, F.ZONE_ALL, F.level(F.AMBIENT_THEME_COLOR, n), "Đèn viền màu $name")
    }

    fun shutdown() = io.shutdownNow()

    private companion object {
        const val VERIFY_DELAY_MS = 300L
    }
}
