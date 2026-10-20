package vn.tht.launcher.vehicle

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import java.lang.reflect.Proxy

/**
 * Đọc dữ liệu xe qua ECARX AdaptAPI (`com.ecarx.xui.adaptapi.car.impl.CarImpl` -> `ICarFunction`).
 *
 * Bổ sung cho [CarPropertySource] (API chuẩn android.car): nhiều đầu ECARX phơi chức năng
 * điều hoà / chế độ lái / đèn ambient qua API riêng này chứ không qua CarPropertyManager.
 *
 * Toàn bộ dùng reflection nên APK vẫn build & chạy trên máy không có thư viện ECARX
 * (điện thoại, máy ảo) – khi đó đơn giản là không kết nối được và không có dữ liệu.
 *
 * CHỈ ĐỌC. `getFunctionValue` / `isFunctionSupported` thường không cần quyền hệ thống.
 * Ghi (`setFunctionValue`) cần APK ký bằng khoá platform của firmware nên KHÔNG làm ở đây.
 */
class EcarxCarSource(
    private val context: Context,
    private val onReady: (Boolean) -> Unit = {},
) {
    private val handler = Handler(Looper.getMainLooper())
    private var car: Any? = null
    private var carFunction: Any? = null
    private var connected = false

    /** Gọi một lần: nạp CarImpl, kết nối, rồi lấy ICarFunction. */
    fun start() {
        try {
            val loader = context.classLoader
            // Máy không phải đầu ECARX: loadClass sẽ ném, ta nuốt và coi như không có dữ liệu.
            val carImplClass = loader.loadClass("com.ecarx.xui.adaptapi.car.impl.CarImpl")
            val instance = carImplClass.getConstructor(Context::class.java).newInstance(context)
            car = instance

            val connectable = runCatching {
                loader.loadClass("com.ecarx.xui.adaptapi.binder.IConnectable")
            }.getOrNull()

            if (connectable != null && connectable.isInstance(instance)) {
                // Đầu xe kết nối bất đồng bộ: đăng ký watcher rồi connect, onConnected() chạy khi sẵn sàng.
                val watcherClass = loader.loadClass(
                    "com.ecarx.xui.adaptapi.binder.IConnectable\$IConnectWatcher"
                )
                val proxy = Proxy.newProxyInstance(
                    watcherClass.classLoader, arrayOf(watcherClass)
                ) { _, method, _ ->
                    // Giao diện có onConnected()/onDisconnected(); ta chỉ quan tâm khi đã nối.
                    if (method.name == "onConnected") handler.post { onConnected() }
                    null
                }
                connectable.getMethod("registerConnectWatcher", watcherClass)
                    .invoke(instance, proxy)
                connectable.getMethod("connect").invoke(instance)
            } else {
                // Không cần kết nối: dùng luôn.
                onConnected()
            }
        } catch (t: Throwable) {
            Log.w(TAG, "ECARX AdaptAPI không khả dụng: $t")
            onReady(false)
        }
    }

    fun stop() {
        try {
            car?.let { c ->
                runCatching {
                    context.classLoader
                        .loadClass("com.ecarx.xui.adaptapi.binder.IConnectable")
                        .getMethod("disconnect")
                        .invoke(c)
                }
            }
        } catch (_: Throwable) {
        }
        car = null
        carFunction = null
        connected = false
    }

    private fun onConnected() {
        if (connected) return
        try {
            carFunction = car!!.javaClass.getMethod("getICarFunction").invoke(car)
            connected = carFunction != null
            Log.i(TAG, if (connected) "Đã nối ICarFunction" else "getICarFunction trả null")
            onReady(connected)
        } catch (t: Throwable) {
            Log.w(TAG, "getICarFunction lỗi: $t")
            onReady(false)
        }
    }

    // ---- Đọc ----

    /** "active" nếu xe hỗ trợ function này, "notactive"/"unknown"/null nếu không. */
    fun supportState(id: Int): String? = invoke(
        "isFunctionSupported", arrayOf(Int::class.javaPrimitiveType!!), id
    )?.toString()

    fun isSupported(id: Int): Boolean = supportState(id)?.lowercase() == "active"

    /** Giá trị hiện tại (int) của function, hoặc null nếu không đọc được. */
    fun getInt(id: Int): Int? = invoke(
        "getFunctionValue", arrayOf(Int::class.javaPrimitiveType!!), id
    ) as? Int

    /** Các giá trị hợp lệ mà function nhận (ví dụ các mức quạt). */
    fun supportedValues(id: Int): IntArray? = invoke(
        "getSupportedFunctionValue", arrayOf(Int::class.javaPrimitiveType!!), id
    ) as? IntArray

    /**
     * Dò toàn bộ catalog và in ra logcat (tag "EcarxCarSource") function nào xe hỗ trợ,
     * kèm giá trị hiện tại. Xem bằng: `adb logcat -s EcarxCarSource`.
     *
     * Đây là bước để biết chắc Coolray của bạn phơi những gì trước khi làm UI điều khiển.
     */
    fun dumpSupported() {
        if (!connected) {
            Log.w(TAG, "Chưa nối ICarFunction, bỏ qua dump")
            return
        }
        Log.i(TAG, "===== Dò function ECARX =====")
        var n = 0
        for (fn in EcarxFunctions.ALL) {
            val state = supportState(fn.id) ?: "null"
            if (state.lowercase() == "active") {
                n++
                val value = getInt(fn.id)
                val values = supportedValues(fn.id)?.joinToString(",") { EcarxFunctions.hex(it) }
                Log.i(
                    TAG,
                    "✔ ${fn.name} ${EcarxFunctions.hex(fn.id)} = $value" +
                        (values?.let { "  [giá trị: $it]" } ?: "")
                )
            } else {
                Log.i(TAG, "· ${fn.name} ${EcarxFunctions.hex(fn.id)} ($state)")
            }
        }
        Log.i(TAG, "===== Hỗ trợ $n/${EcarxFunctions.ALL.size} function =====")
    }

    /** Gọi một method của ICarFunction bằng reflection; nuốt mọi lỗi -> null. */
    private fun invoke(name: String, paramTypes: Array<Class<*>>, vararg args: Any?): Any? {
        val f = carFunction ?: return null
        return try {
            f.javaClass.getMethod(name, *paramTypes).invoke(f, *args)
        } catch (_: Throwable) {
            null // function không có / thiếu quyền / chữ ký method khác
        }
    }

    private companion object {
        const val TAG = "EcarxCarSource"
    }
}
