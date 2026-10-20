package vn.tht.launcher.vehicle

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import java.lang.reflect.Method

/**
 * Đọc/ghi dữ liệu xe qua ECARX AdaptAPI 1.1.18.9:
 * `com.ecarx.xui.adaptapi.car.Car.create(context).getICarFunction()` với các hàm
 * `isFunctionSupported(id, zone)`, `get/setFunctionValue(id, zone[, int])`,
 * `get/setCustomizeFunctionValue(id, zone[, float])`.
 *
 * Thư viện nằm sẵn trong boot classpath của đầu xe nên chỉ dùng reflection; trên máy khác
 * (điện thoại, máy ảo) đơn giản là không nối được và không có dữ liệu.
 *
 * GHI bị khoá trừ khi [PlatformSignature.verify] xác nhận APK ký cùng chứng chỉ với firmware
 * (gói "android"). Khi bị khoá, [setInt]/[setFloat] trả false và không gọi xuống xe.
 */
class EcarxCarSource(
    private val context: Context,
    private val onReady: (Boolean) -> Unit = {},
) {
    private val handler = Handler(Looper.getMainLooper())
    private var carFunction: Any? = null
    private var mSupported: Method? = null
    private var mGetInt: Method? = null
    private var mSetInt: Method? = null
    private var mGetFloat: Method? = null
    private var mSetFloat: Method? = null
    private var attempts = 0
    private val supportCache = java.util.concurrent.ConcurrentHashMap<Long, Boolean>()

    /** true khi APK ký bằng khoá platform của firmware; tính một lần lúc tạo. */
    val writeAllowed: Boolean = PlatformSignature.verify(context)

    val connected: Boolean get() = carFunction != null

    fun start() {
        if (connected) return
        try {
            val carClass = Class.forName(CAR_CLASS)
            val create = carClass.methods.firstOrNull {
                it.name == "create" && it.parameterTypes.size == 1 &&
                    Context::class.java.isAssignableFrom(it.parameterTypes[0])
            } ?: error("$CAR_CLASS.create(Context) không có")
            val car = create.invoke(null, context.applicationContext) ?: error("Car.create trả null")
            val fn = car.javaClass.methods
                .firstOrNull { it.name == "getICarFunction" && it.parameterTypes.isEmpty() }
                ?.invoke(car)
            if (fn == null) {
                retry("getICarFunction trả null")
                return
            }
            bind(fn)
        } catch (t: Throwable) {
            Log.w(TAG, "ECARX AdaptAPI không khả dụng: $t")
            onReady(false)
        }
    }

    fun stop() {
        handler.removeCallbacksAndMessages(null)
        carFunction = null
        supportCache.clear()
    }

    private fun retry(reason: String) {
        if (++attempts > MAX_ATTEMPTS) {
            Log.w(TAG, "$reason, bỏ cuộc sau $MAX_ATTEMPTS lần")
            onReady(false)
            return
        }
        Log.i(TAG, "$reason, thử lại ($attempts/$MAX_ATTEMPTS)")
        handler.postDelayed({ start() }, RETRY_MS)
    }

    private fun bind(fn: Any) {
        val methods = fn.javaClass.methods
        fun find(name: String, vararg types: Class<*>) =
            methods.firstOrNull { it.name == name && it.parameterTypes.contentEquals(arrayOf(*types)) }
        mSupported = find("isFunctionSupported", INT, INT)
        mGetInt = find("getFunctionValue", INT, INT)
        mSetInt = find("setFunctionValue", INT, INT, INT)
        mGetFloat = find("getCustomizeFunctionValue", INT, INT)
        mSetFloat = find("setCustomizeFunctionValue", INT, INT, FLOAT)
        carFunction = fn
        Log.i(
            TAG, "Đã nối ${fn.javaClass.name}: supported=${mSupported != null} " +
                "int=${mGetInt != null}/${mSetInt != null} float=${mGetFloat != null}/${mSetFloat != null} " +
                "ghi=${if (writeAllowed) "MỞ (chữ ký platform khớp)" else "KHOÁ (chữ ký không khớp firmware)"}"
        )
        onReady(true)
    }

    // ---- Đọc ----

    /** true = active, false = xe không hỗ trợ, null = không hỏi được. Có cache theo (id, zone). */
    fun isSupported(id: Int, zone: Int = EcarxFunctions.ZONE_ALL): Boolean? {
        val key = (id.toLong() shl 32) or (zone.toLong() and 0xFFFFFFFFL)
        supportCache[key]?.let { return it }
        val state = rawSupportState(id, zone)
        val result = state?.equals("active", ignoreCase = true)
        if (result != null) supportCache[key] = result
        return result
    }

    private fun rawSupportState(id: Int, zone: Int): String? = try {
        when (val s = mSupported?.invoke(carFunction, id, zone)) {
            null -> null
            is Enum<*> -> s.name
            else -> s.toString()
        }
    } catch (_: Throwable) {
        null
    }

    fun getInt(id: Int, zone: Int = EcarxFunctions.ZONE_ALL): Int? = try {
        (mGetInt?.invoke(carFunction, id, zone) as? Number)?.toInt()
    } catch (_: Throwable) {
        null
    }

    fun getFloat(id: Int, zone: Int = EcarxFunctions.ZONE_ALL): Float? = try {
        (mGetFloat?.invoke(carFunction, id, zone) as? Number)?.toFloat()?.takeIf { it.isFinite() }
    } catch (_: Throwable) {
        null
    }

    fun supportedValues(id: Int, zone: Int = EcarxFunctions.ZONE_ALL): IntArray? = try {
        carFunction?.javaClass?.getMethod("getSupportedFunctionValue", INT, INT)
            ?.invoke(carFunction, id, zone) as? IntArray
    } catch (_: Throwable) {
        null
    }

    // ---- Ghi (khoá nếu chưa xác minh chữ ký) ----

    /** true chỉ nghĩa là AdaptAPI nhận lệnh; người gọi phải đọc lại để xác nhận. */
    fun setInt(id: Int, zone: Int, value: Int): Boolean = write(mSetInt, id, zone, value)

    fun setFloat(id: Int, zone: Int, value: Float): Boolean = write(mSetFloat, id, zone, value)

    private fun write(method: Method?, id: Int, zone: Int, value: Any): Boolean {
        if (!writeAllowed) {
            Log.w(TAG, "Chặn ghi ${EcarxFunctions.hex(id)}: APK chưa ký bằng khoá platform của firmware")
            return false
        }
        if (method == null || carFunction == null) return false
        return try {
            val r = method.invoke(carFunction, id, zone, value)
            Log.i(TAG, "set ${EcarxFunctions.hex(id)} zone=$zone value=$value -> $r")
            when (r) {
                is Boolean -> r
                is Number -> r.toInt() != 0
                null -> true // hàm void
                else -> false
            }
        } catch (t: Throwable) {
            Log.w(TAG, "set ${EcarxFunctions.hex(id)} lỗi: ${t.cause ?: t}")
            false
        }
    }

    // ---- Hiển thị ----

    /** Trạng thái để hiện trên màn hình chính; chỉ đọc function xe báo hỗ trợ. null nếu chưa nối. */
    fun readInfo(): EcarxInfo? {
        if (!connected) return null
        val f = EcarxFunctions
        fun <T> ifSupported(id: Int, zone: Int = f.ZONE_ALL, read: () -> T?): T? =
            if (isSupported(id, zone) == true) read() else null
        val tempZone = if (isSupported(f.HVAC_TEMP, f.ZONE_ROW_1_LEFT) == true) f.ZONE_ROW_1_LEFT else f.ZONE_ALL
        return EcarxInfo(
            hvacOn = ifSupported(f.HVAC_POWER) { getInt(f.HVAC_POWER) }?.let { it == f.ON },
            acOn = ifSupported(f.HVAC_AC) { getInt(f.HVAC_AC) }?.let { it == f.ON },
            tempC = ifSupported(f.HVAC_TEMP, tempZone) { getFloat(f.HVAC_TEMP, tempZone) }
                ?.takeIf { it in f.TEMP_MIN..f.TEMP_MAX },
            fanLevel = ifSupported(f.HVAC_FAN_SPEED) { f.levelOf(f.HVAC_FAN_SPEED, getInt(f.HVAC_FAN_SPEED)) },
            driveMode = ifSupported(f.DRIVE_MODE) { f.levelOf(f.DRIVE_MODE, getInt(f.DRIVE_MODE)) },
            ambientOn = ifSupported(f.AMBIENT_SWITCH) { getInt(f.AMBIENT_SWITCH) }?.let { it == f.ON },
        )
    }

    /**
     * Dò toàn bộ catalog và in ra logcat (tag "EcarxCarSource"). Xem: `adb logcat -s EcarxCarSource`.
     */
    fun dumpSupported() {
        if (!connected) {
            Log.w(TAG, "Chưa nối ICarFunction, bỏ qua dump")
            return
        }
        Log.i(TAG, "===== Dò function ECARX (ghi ${if (writeAllowed) "MỞ" else "KHOÁ"}) =====")
        var n = 0
        for (fn in EcarxFunctions.ALL) {
            val state = rawSupportState(fn.id, fn.zone) ?: "null"
            if (state.equals("active", ignoreCase = true)) {
                n++
                val values = supportedValues(fn.id, fn.zone)?.joinToString(",")
                Log.i(
                    TAG, "✔ ${fn.name} ${EcarxFunctions.hex(fn.id)} int=${getInt(fn.id, fn.zone)} " +
                        "float=${getFloat(fn.id, fn.zone)}" + (values?.let { "  [giá trị: $it]" } ?: "")
                )
            } else {
                Log.i(TAG, "· ${fn.name} ${EcarxFunctions.hex(fn.id)} ($state)")
            }
        }
        Log.i(TAG, "===== Hỗ trợ $n/${EcarxFunctions.ALL.size} =====")
    }

    private companion object {
        const val TAG = "EcarxCarSource"
        const val CAR_CLASS = "com.ecarx.xui.adaptapi.car.Car"
        const val MAX_ATTEMPTS = 5
        const val RETRY_MS = 2000L
        val INT: Class<*> = Int::class.javaPrimitiveType!!
        val FLOAT: Class<*> = Float::class.javaPrimitiveType!!
    }
}

/** Trạng thái đọc từ ECARX để hiển thị; null = xe không hỗ trợ hoặc không đọc được. */
data class EcarxInfo(
    val hvacOn: Boolean? = null,
    val acOn: Boolean? = null,
    val tempC: Float? = null,
    val fanLevel: Int? = null,
    val driveMode: Int? = null,
    val ambientOn: Boolean? = null,
) {
    fun lines(): List<String> = buildList {
        val hvac = buildList {
            hvacOn?.let { add(if (it) "BẬT" else "TẮT") }
            if (hvacOn != false) {
                tempC?.let { add(String.format(java.util.Locale.US, "%.1f°C", it)) }
                fanLevel?.takeIf { it > 0 }?.let { add("quạt $it") }
                if (acOn == true) add("A/C")
            }
        }
        if (hvac.isNotEmpty()) add("Điều hoà: " + hvac.joinToString(" · "))
        driveMode?.let { m ->
            EcarxFunctions.DRIVE_MODES.firstOrNull { it.first == m }?.let { add("Chế độ lái: ${it.second}") }
        }
        ambientOn?.let { add("Đèn viền: " + if (it) "bật" else "tắt") }
    }
}
