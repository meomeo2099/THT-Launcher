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
) {
    data class Result(val ok: Boolean, val message: String)

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
