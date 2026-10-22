package vn.tht.launcher.vehicle

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import java.lang.reflect.Method

/**
 * Đọc/ghi dữ liệu xe qua ECARX AdaptAPI 1.1.18.9:
 * `com.ecarx.xui.adaptapi.car.Car.create(context)` → `connect()` → chờ `onConnected` →
 * `getICarFunction()` với các hàm `isFunctionSupported(id, zone)`,
 * `get/setFunctionValue(id, zone[, int])`, `get/setCustomizeFunctionValue(id, zone[, float])`.
 *
 * Trên đầu xe, AdaptAPI KHÔNG nằm trong classpath của app thường (manifest chỉ khai báo
 * android.car), nên phải nạp các jar trong /system/framework bằng PathClassLoader riêng.
 * Car là đối tượng IConnectable: lấy ICarFunction trước khi nối xong sẽ đọc sai / ghi không ăn.
 * Trên máy khác (điện thoại, máy ảo) đơn giản là không nối được và không có dữ liệu.
 *
 * GHI bị khoá trừ khi [PlatformSignature.verify] xác nhận APK ký cùng chứng chỉ với firmware
 * (gói "android"). Khi bị khoá, [setInt]/[setFloat] trả false và không gọi xuống xe.
 */
class EcarxCarSource(
    private val context: Context,
    private val onReady: (Boolean) -> Unit = {},
) {
    private val handler = Handler(Looper.getMainLooper())
    @Volatile private var carFunction: Any? = null
    private var loader: ClassLoader? = null
    private var car: Any? = null
    private var watching = false
    private var mSupported: Method? = null
    private var mGetInt: Method? = null
    private var mSetInt: Method? = null
    private var mGetFloat: Method? = null
    private var mSetFloat: Method? = null
    /** Bản không có vùng: getFunctionValue(id), setFunctionValue(id, value) – dùng cho chức năng toàn xe. */
    private var mGetIntNoZone: Method? = null
    private var mSetIntNoZone: Method? = null
    private var attempts = 0
    private val supportCache = java.util.concurrent.ConcurrentHashMap<Long, Boolean>()

    /** true khi APK ký bằng khoá platform của firmware; tính một lần lúc tạo. */
    var writeAllowed: Boolean = PlatformSignature.verify(context)
        private set

    val connected: Boolean get() = carFunction != null

    /** Trạng thái nối (hiện trong Chẩn đoán). */
    @Volatile var status: String = "chưa nối"
        private set

    fun start() {
        if (connected) return
        try {
            val cl = loader ?: adaptApiLoader().also { loader = it }
            val c = car ?: createCar(cl).also { car = it }
            val connectable = runCatching { cl.loadClass(CONNECTABLE) }.getOrNull()
            if (connectable != null && connectable.isInstance(c)) {
                if (!watching) watching = watchConnection(cl, connectable, c)
                val already = runCatching { connectable.getMethod("isConnected").invoke(c) == true }.getOrDefault(false)
                if (already) {
                    fetchFunction(c)
                } else {
                    status = "đang kết nối dịch vụ xe…"
                    connectable.getMethod("connect").invoke(c)
                    // Nếu bản AdaptAPI không gọi onConnected: thử lấy ICarFunction sau một lúc
                    handler.postDelayed({ if (!connected) fetchFunction(c) }, CONNECT_WAIT_MS)
                }
            } else {
                fetchFunction(c)
            }
        } catch (t: Throwable) {
            Log.w(TAG, "ECARX AdaptAPI không khả dụng: $t")
            status = "không có AdaptAPI (${t.javaClass.simpleName}: ${t.message})"
            if (DemoCarFunction.enabled(context)) {
                // Bản debug, không có xe thật: dùng xe giả để xem giao diện (ghi chỉ vào bộ nhớ)
                Log.i(TAG, "Chế độ demo: dùng DemoCarFunction")
                writeAllowed = true
                bind(DemoCarFunction())
                return
            }
            onReady(false)
        }
    }

    fun stop() {
        handler.removeCallbacksAndMessages(null)
        carFunction = null
        supportCache.clear()
    }

    /**
     * ClassLoader có AdaptAPI: của app nếu đã có sẵn, không thì nạp các jar hệ thống của đầu xe
     * (cha là ClassLoader của app để dùng chung lớp Android).
     */
    private fun adaptApiLoader(): ClassLoader {
        val app = context.classLoader
        if (runCatching { app.loadClass(CAR_CLASS) }.isSuccess) return app
        val jars = SYSTEM_JARS.filter { java.io.File(it).exists() }
        if (jars.isEmpty()) throw ClassNotFoundException("$CAR_CLASS (không có jar AdaptAPI trong /system/framework)")
        Log.i(TAG, "Nạp AdaptAPI từ ${jars.joinToString(":")}")
        return dalvik.system.PathClassLoader(jars.joinToString(":"), app)
    }

    /** `Car.create(Context)`; bản không có hàm này thì tạo `CarImpl(Context)`. */
    private fun createCar(cl: ClassLoader): Any {
        val ctx = context.applicationContext
        val create = runCatching { cl.loadClass(CAR_CLASS) }.getOrNull()?.methods?.firstOrNull {
            it.name == "create" && it.parameterTypes.size == 1 && Context::class.java.isAssignableFrom(it.parameterTypes[0])
        }
        if (create != null) create.invoke(null, ctx)?.let { return it }
        return cl.loadClass(CAR_IMPL_CLASS).getConstructor(Context::class.java).newInstance(ctx)
    }

    /** Đăng ký IConnectWatcher: nối xong mới lấy ICarFunction; mất nối thì nối lại. */
    private fun watchConnection(cl: ClassLoader, connectable: Class<*>, c: Any): Boolean = try {
        val watcher = cl.loadClass("$CONNECTABLE\$IConnectWatcher")
        val proxy = java.lang.reflect.Proxy.newProxyInstance(watcher.classLoader, arrayOf(watcher)) { self, m, args ->
            when (m.name) {
                "onConnected" -> { handler.post { fetchFunction(c) }; null }
                "onDisConnected", "onDisconnected" -> { handler.post { onDisconnected() }; null }
                "hashCode" -> System.identityHashCode(self)
                "equals" -> self === args?.getOrNull(0)
                "toString" -> "THTCarConnectWatcher"
                else -> null
            }
        }
        connectable.methods.first { it.name == "registerConnectWatcher" && it.parameterTypes.size == 1 }.invoke(c, proxy)
        true
    } catch (t: Throwable) {
        Log.w(TAG, "Không đăng ký được IConnectWatcher: $t")
        false
    }

    private fun onDisconnected() {
        Log.w(TAG, "Mất kết nối dịch vụ xe, nối lại sau ${RETRY_MS} ms")
        carFunction = null
        supportCache.clear()
        status = "mất kết nối, đang nối lại…"
        onReady(false)
        handler.postDelayed({ start() }, RETRY_MS)
    }

    private fun fetchFunction(c: Any) {
        if (connected) return
        val fn = try {
            c.javaClass.methods.firstOrNull { it.name == "getICarFunction" && it.parameterTypes.isEmpty() }?.invoke(c)
        } catch (t: Throwable) {
            Log.w(TAG, "getICarFunction lỗi: ${t.cause ?: t}")
            null
        }
        if (fn == null) retry("getICarFunction trả null") { fetchFunction(c) } else bind(fn)
    }

    private fun retry(reason: String, again: () -> Unit) {
        if (++attempts > MAX_ATTEMPTS) {
            Log.w(TAG, "$reason, bỏ cuộc sau $MAX_ATTEMPTS lần")
            status = "$reason (đã thử $MAX_ATTEMPTS lần)"
            onReady(false)
            return
        }
        Log.i(TAG, "$reason, thử lại ($attempts/$MAX_ATTEMPTS)")
        handler.postDelayed(again, RETRY_MS)
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
        mGetIntNoZone = find("getFunctionValue", INT)
        mSetIntNoZone = find("setFunctionValue", INT, INT)
        supportCache.clear()
        attempts = 0
        carFunction = fn
        status = "đã nối (${fn.javaClass.simpleName})"
        Log.i(
            TAG, "Đã nối ${fn.javaClass.name}: supported=${mSupported != null} " +
                "int=${mGetInt != null}/${mSetInt != null} float=${mGetFloat != null}/${mSetFloat != null} " +
                "ghi=${if (writeAllowed) "MỞ (chữ ký platform khớp)" else "KHOÁ (chữ ký không khớp firmware)"}"
        )
        onReady(true)
    }

    // ---- Đọc ----

    /**
     * true = xe có chức năng này (trạng thái "active", hoặc "notactive" = có nhưng đang tạm không dùng,
     * ví dụ nhiệt độ khi điều hoà tắt), false = xe không có, null = không hỏi được.
     * Chỉ nhớ kết quả chắc chắn; trạng thái khác (lỗi, chưa sẵn sàng) hỏi lại lần sau.
     */
    fun isSupported(id: Int, zone: Int = EcarxFunctions.ZONE_ALL): Boolean? {
        val key = (id.toLong() shl 32) or (zone.toLong() and 0xFFFFFFFFL)
        supportCache[key]?.let { return it }
        val state = rawSupportState(id, zone)?.lowercase() ?: return null
        val result = when (state) {
            "active", "notactive" -> true
            "notavailable", "unsupported", "notsupport" -> false
            else -> return null
        }
        supportCache[key] = result
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

    fun getInt(id: Int, zone: Int = EcarxFunctions.ZONE_ALL): Int? {
        // Chức năng toàn xe: hỏi bản không vùng trước, không được thì hỏi kèm ZONE_ALL
        if (zone == EcarxFunctions.ZONE_ALL) {
            runCatching { (mGetIntNoZone?.invoke(carFunction, id) as? Number)?.toInt() }.getOrNull()?.let { return it }
        }
        return try {
            (mGetInt?.invoke(carFunction, id, zone) as? Number)?.toInt()
        } catch (_: Throwable) {
            null
        }
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
    fun setInt(id: Int, zone: Int, value: Int): Boolean {
        // Nhiều chức năng toàn xe chỉ nhận setFunctionValue(id, value); không ăn thì gửi kèm vùng
        if (zone == EcarxFunctions.ZONE_ALL && mSetIntNoZone != null && write(mSetIntNoZone, id, null, value)) return true
        return write(mSetInt, id, zone, value)
    }

    fun setFloat(id: Int, zone: Int, value: Float): Boolean = write(mSetFloat, id, zone, value)

    /** [zone] null = gọi bản không có tham số vùng. */
    private fun write(method: Method?, id: Int, zone: Int?, value: Any): Boolean {
        if (!writeAllowed) {
            Log.w(TAG, "Chặn ghi ${EcarxFunctions.hex(id)}: APK chưa ký bằng khoá platform của firmware")
            return false
        }
        if (method == null || carFunction == null) return false
        return try {
            val r = if (zone == null) method.invoke(carFunction, id, value) else method.invoke(carFunction, id, zone, value)
            Log.i(TAG, "set ${EcarxFunctions.hex(id)} zone=${zone ?: "-"} value=$value -> $r")
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
            autoOn = ifSupported(f.HVAC_AUTO) { getInt(f.HVAC_AUTO) }?.let { it == f.ON },
            recirc = ifSupported(f.HVAC_CIRCULATION) { f.levelOf(f.HVAC_CIRCULATION, getInt(f.HVAC_CIRCULATION)) },
            blowing = ifSupported(f.HVAC_BLOWING_MODE) { f.levelOf(f.HVAC_BLOWING_MODE, getInt(f.HVAC_BLOWING_MODE)) },
            defrostFront = ifSupported(f.HVAC_DEFROST_FRONT) { getInt(f.HVAC_DEFROST_FRONT) }?.let { it == f.ON },
            defrostRear = ifSupported(f.HVAC_DEFROST_REAR) { getInt(f.HVAC_DEFROST_REAR) }?.let { it == f.ON },
            seatHeatLeft = ifSupported(f.HVAC_SEAT_HEATING, f.ZONE_ROW_1_LEFT) {
                f.levelOf(f.HVAC_SEAT_HEATING, getInt(f.HVAC_SEAT_HEATING, f.ZONE_ROW_1_LEFT))
            },
            seatHeatRight = ifSupported(f.HVAC_SEAT_HEATING, f.ZONE_ROW_1_RIGHT) {
                f.levelOf(f.HVAC_SEAT_HEATING, getInt(f.HVAC_SEAT_HEATING, f.ZONE_ROW_1_RIGHT))
            },
            tempRightC = ifSupported(f.HVAC_TEMP, f.ZONE_ROW_1_RIGHT) { getFloat(f.HVAC_TEMP, f.ZONE_ROW_1_RIGHT) }
                ?.takeIf { it in f.TEMP_MIN..f.TEMP_MAX },
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
            if (state.equals("active", ignoreCase = true) || state.equals("notactive", ignoreCase = true)) {
                n++
                val values = supportedValues(fn.id, fn.zone)?.joinToString(",")
                Log.i(
                    TAG, "✔ ${fn.name} ${EcarxFunctions.hex(fn.id)} ($state) int=${getInt(fn.id, fn.zone)} " +
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
        const val CAR_IMPL_CLASS = "com.ecarx.xui.adaptapi.car.impl.CarImpl"
        const val CONNECTABLE = "com.ecarx.xui.adaptapi.binder.IConnectable"
        /** Jar AdaptAPI có sẵn trong firmware ECARX (không đóng gói vào APK). */
        val SYSTEM_JARS = listOf(
            "/system/framework/AdapterAPI.jar", "/system/framework/AdapterAPIImpl.jar",
            "/system/framework/ecarx.jar", "/system/framework/ecarxSignalAPI.jar",
        )
        const val CONNECT_WAIT_MS = 5000L
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
    val autoOn: Boolean? = null,
    /** 1 gió trong, 2 gió ngoài, 3 tự động. */
    val recirc: Int? = null,
    /** Hướng gió: 1 mặt, 2 chân, 3 mặt+chân, 4 kính… */
    val blowing: Int? = null,
    val defrostFront: Boolean? = null,
    val defrostRear: Boolean? = null,
    val seatHeatLeft: Int? = null,
    val seatHeatRight: Int? = null,
    val tempRightC: Float? = null,
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
