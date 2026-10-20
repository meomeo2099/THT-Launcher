package vn.tht.launcher.vehicle

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Parcel
import android.util.Log

/**
 * Mở màn hình điều hoà gốc của đầu ECARX.
 *
 * Cách làm: bind vào service của app điều hoà (mặc định package `ecarx.hvac.app`) rồi gọi
 * transaction đầu tiên (FIRST_CALL_TRANSACTION = 1) – lệnh "mở giao diện điều hoà".
 * Interface descriptor lấy ngay từ binder (getInterfaceDescriptor) nên không phải đoán tên AIDL.
 *
 * Không cần quyền hệ thống. Mọi lỗi (không có app, service không export, transact lỗi,
 * quá thời gian) đều bị nuốt: thử mở app điều hoà bằng launch intent, cuối cùng báo false.
 *
 * Tên service thật trên xe CHƯA kiểm chứng: nếu không truyền [serviceClass], class sẽ tự dò
 * và in danh sách service của package ra logcat (tag "HvacOpener") để điền vào cấu hình.
 */
class HvacOpener(private val context: Context) {

    private val handler = Handler(Looper.getMainLooper())

    fun open(
        pkg: String = DEFAULT_PACKAGE,
        serviceClass: String? = null,
        onResult: (Boolean) -> Unit = {},
    ) {
        val component = try {
            resolveService(pkg, serviceClass)
        } catch (t: Throwable) {
            Log.w(TAG, "Không dò được service điều hoà: $t")
            null
        }
        if (component == null) {
            onResult(fallback(pkg))
            return
        }
        bindAndOpen(component, pkg, onResult)
    }

    private fun bindAndOpen(component: ComponentName, pkg: String, onResult: (Boolean) -> Unit) {
        var done = false
        lateinit var conn: ServiceConnection

        fun finish(ok: Boolean) {
            if (done) return
            done = true
            handler.removeCallbacksAndMessages(conn)
            runCatching { context.unbindService(conn) }
            onResult(if (ok) true else fallback(pkg))
        }

        conn = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                if (service == null) {
                    finish(false)
                    return
                }
                // transact là IPC đồng bộ: chạy ngoài main thread cho chắc.
                Thread {
                    val ok = transactOpen(service)
                    handler.post { finish(ok) }
                }.start()
            }

            override fun onServiceDisconnected(name: ComponentName?) {}

            override fun onBindingDied(name: ComponentName?) = finish(false)

            override fun onNullBinding(name: ComponentName?) = finish(false)
        }

        val bound = try {
            context.bindService(Intent().setComponent(component), conn, Context.BIND_AUTO_CREATE)
        } catch (t: Throwable) {
            Log.w(TAG, "bindService $component lỗi: $t")
            false
        }
        if (!bound) {
            Log.w(TAG, "Không bind được $component")
            finish(false)
            return
        }
        handler.postAtTime({ finish(false) }, conn, android.os.SystemClock.uptimeMillis() + TIMEOUT_MS)
    }

    private fun transactOpen(binder: IBinder): Boolean {
        val data = Parcel.obtain()
        val reply = Parcel.obtain()
        return try {
            val descriptor = runCatching { binder.interfaceDescriptor }.getOrNull()
            Log.i(TAG, "Service điều hoà: descriptor=$descriptor")
            if (!descriptor.isNullOrEmpty()) data.writeInterfaceToken(descriptor)
            val ok = binder.transact(IBinder.FIRST_CALL_TRANSACTION, data, reply, 0)
            if (ok) reply.readException()
            Log.i(TAG, "transact mở điều hoà: $ok")
            ok
        } catch (t: Throwable) {
            Log.w(TAG, "transact mở điều hoà lỗi: $t")
            false
        } finally {
            data.recycle()
            reply.recycle()
        }
    }

    /** Service để bind: lấy từ cấu hình nếu có, không thì dò trong package. */
    private fun resolveService(pkg: String, serviceClass: String?): ComponentName? {
        if (!serviceClass.isNullOrBlank()) {
            val cls = if (serviceClass.startsWith(".")) pkg + serviceClass else serviceClass
            return ComponentName(pkg, cls)
        }
        val services = context.packageManager
            .getPackageInfo(pkg, PackageManager.GET_SERVICES)
            .services.orEmpty()
            .filter { it.exported }
        services.forEach { Log.i(TAG, "Service exported của $pkg: ${it.name} (permission=${it.permission})") }
        val pick = services.firstOrNull { it.permission == null && it.name.contains("hvac", true) }
            ?: services.firstOrNull { it.permission == null && it.name.contains("open", true) }
            ?: services.singleOrNull { it.permission == null }
        if (pick == null) Log.w(TAG, "Không chọn được service điều hoà trong $pkg – điền \"service\" trong shortcuts.json")
        return pick?.let { ComponentName(pkg, it.name) }
    }

    /** Dự phòng: mở app điều hoà như một app thường. */
    private fun fallback(pkg: String): Boolean = try {
        val intent = context.packageManager.getLaunchIntentForPackage(pkg)
        if (intent != null) {
            context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
            true
        } else false
    } catch (t: Throwable) {
        Log.w(TAG, "Mở app $pkg lỗi: $t")
        false
    }

    companion object {
        private const val TAG = "HvacOpener"
        private const val TIMEOUT_MS = 3000L
        const val DEFAULT_PACKAGE = "ecarx.hvac.app"
    }
}
