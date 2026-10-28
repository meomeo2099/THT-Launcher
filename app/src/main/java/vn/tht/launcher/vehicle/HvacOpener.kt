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
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Opens the stock climate UI using the previously observed HVAC AIDL contract.
 * Transaction 1 toggles that UI; it is never used to guess a power-service protocol.
 * Unknown services/descriptors fall back to an explicitly resolved launcher activity.
 */
class HvacOpener(private val context: Context) {
    private val handler = Handler(Looper.getMainLooper())

    fun open(
        pkg: String = DEFAULT_PACKAGE,
        serviceClass: String? = null,
        onResult: (Boolean) -> Unit = {},
    ) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            handler.post { open(pkg, serviceClass, onResult) }
            return
        }
        val component = resolveService(pkg, serviceClass)
        if (component == null) {
            onResult(fallback(pkg))
            return
        }
        bindAndOpen(component, pkg, onResult)
    }

    private fun bindAndOpen(component: ComponentName, pkg: String, onResult: (Boolean) -> Unit) {
        val done = AtomicBoolean(false)
        val transactionStarted = AtomicBoolean(false)
        val deadline = android.os.SystemClock.uptimeMillis() + TIMEOUT_MS
        lateinit var conn: ServiceConnection
        fun finish(ok: Boolean, allowFallback: Boolean = true) {
            if (!done.compareAndSet(false, true)) return
            handler.removeCallbacksAndMessages(conn)
            runCatching { context.unbindService(conn) }
            onResult(ok || (allowFallback && fallback(pkg)))
        }
        conn = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                if (service == null) { finish(false); return }
                Thread({
                    if (done.get() || android.os.SystemClock.uptimeMillis() >= deadline) return@Thread
                    transactionStarted.set(true)
                    if (done.get()) return@Thread
                    val ok = transactOpen(service)
                    handler.post { finish(ok) }
                }, "tht-stock-climate").start()
            }
            override fun onServiceDisconnected(name: ComponentName?) = finish(false)
            override fun onBindingDied(name: ComponentName?) = finish(false)
            override fun onNullBinding(name: ComponentName?) = finish(false)
        }
        val bound = runCatching {
            context.bindService(Intent().setComponent(component), conn, Context.BIND_AUTO_CREATE)
        }.getOrElse {
            Log.w(TAG, "Không bind được điều hòa gốc: $component", it)
            false
        }
        if (!bound) { finish(false); return }
        // Binder calls cannot be cancelled. If one is in flight, do not also launch a
        // fallback UI on timeout and risk toggling/launching the climate screen twice.
        handler.postAtTime({ finish(false, !transactionStarted.get()) }, conn, deadline)
    }

    private fun transactOpen(binder: IBinder): Boolean {
        val data = Parcel.obtain()
        val reply = Parcel.obtain()
        return try {
            // Check the known interface instead of transacting with the first exported service.
            if (binder.interfaceDescriptor != HVAC_DESCRIPTOR) return false
            data.writeInterfaceToken(HVAC_DESCRIPTOR)
            if (!binder.transact(IBinder.FIRST_CALL_TRANSACTION, data, reply, 0)) return false
            reply.readException()
            true
        } catch (error: Exception) {
            Log.w(TAG, "Không mở được giao diện điều hòa theo AIDL đã biết", error)
            false
        } finally {
            data.recycle()
            reply.recycle()
        }
    }

    private fun resolveService(pkg: String, serviceClass: String?): ComponentName? {
        val candidate = if (!serviceClass.isNullOrBlank()) {
            ComponentName(pkg, if (serviceClass.startsWith(".")) pkg + serviceClass else serviceClass)
        } else {
            // Other packages require an explicit component and must still expose the known AIDL.
            if (pkg != DEFAULT_PACKAGE) return null
            ComponentName(DEFAULT_PACKAGE, HVAC_SERVICE)
        }
        return runCatching {
            val info = context.packageManager.getServiceInfo(candidate, 0)
            if (!info.enabled || !info.applicationInfo.enabled || !info.exported) return null
            val permission = info.permission
            if (!permission.isNullOrEmpty() && context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) return null
            candidate
        }.getOrNull()
    }

    private fun fallback(pkg: String): Boolean {
        val intent = OemFirmware.resolvePackage(context, pkg) ?: return false
        return runCatching { context.startActivity(intent); true }.getOrDefault(false)
    }

    companion object {
        private const val TAG = "HvacOpener"
        private const val TIMEOUT_MS = 3000L
        const val DEFAULT_PACKAGE = "ecarx.hvac.app"
        const val HVAC_SERVICE = "ecarx.hvac.app.service.HvacAppService"
        const val HVAC_DESCRIPTOR = "ecarx.hvac.app.IOpenHvacAidlInterface"
    }
}
