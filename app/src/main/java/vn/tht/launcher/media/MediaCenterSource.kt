package vn.tht.launcher.media

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.os.Looper
import android.os.Parcel
import android.util.Log

/**
 * Trình phát có sẵn của đầu xe ECARX (nhạc USB/Bluetooth, radio): nhiều nguồn không công bố
 * MediaSession chuẩn nên [MediaWatcher] không thấy. Dịch vụ `ecarx.xsf.mediacenter` có API cho
 * widget trả về thông tin đang phát.
 *
 * Giao thức (binder, đọc tĩnh từ cách một launcher khác gọi; chỉ là số hiệu giao dịch):
 * - `IMediaCenterWidgetApiSvc` giao dịch 2 → `IMusicPlaybackInfo` (strong binder)
 * - `IMusicPlaybackInfo`: 9 = loại nguồn (int), 5 = tần số, 6 = tên đài, 2 = tên bài (chuỗi),
 *   11 = đang phát (int, 1 = có), 27 = tên gói, 25 = tên app.
 * Chỉ đọc; điều khiển dùng phím media chuẩn. Không có dịch vụ (máy khác) thì không làm gì.
 */
class MediaCenterSource(
    private val context: Context,
    private val onState: (State?) -> Unit,
) {
    data class State(
        val sourceType: Int,
        val frequency: String?,
        val station: String?,
        val title: String?,
        val playing: Boolean,
        val pkg: String?,
        val appName: String?,
    ) {
        /** Có gì để hiện không (đang phát hoặc có tên bài/đài). */
        val hasContent: Boolean get() = playing || !title.isNullOrBlank() || !station.isNullOrBlank()
    }

    private val main = Handler(Looper.getMainLooper())
    private var thread: HandlerThread? = null
    private var worker: Handler? = null
    @Volatile private var svc: IBinder? = null
    private var bound = false
    private var last: State? = null

    private val conn = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val ok = runCatching { service?.interfaceDescriptor == SVC }.getOrDefault(false)
            if (!ok) {
                Log.w(TAG, "Dịch vụ media center không đúng giao diện: ${runCatching { service?.interfaceDescriptor }.getOrNull()}")
                return
            }
            svc = service
            worker?.post(poll)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            svc = null
            publish(null)
        }
    }

    private val poll = object : Runnable {
        override fun run() {
            publish(read())
            worker?.postDelayed(this, POLL_MS)
        }
    }

    fun start() {
        if (thread != null) return
        thread = HandlerThread("media-center").apply { start() }
        worker = Handler(thread!!.looper)
        val intent = Intent(ACTION).setComponent(ComponentName(PKG, SERVICE))
        bound = runCatching { context.bindService(intent, conn, Context.BIND_AUTO_CREATE) }.getOrDefault(false) ||
            runCatching { context.bindService(Intent(ACTION).setPackage(PKG), conn, Context.BIND_AUTO_CREATE) }.getOrDefault(false)
        if (!bound) Log.i(TAG, "Không có dịch vụ $PKG (không phải đầu xe ECARX?)")
    }

    fun stop() {
        worker?.removeCallbacksAndMessages(null)
        thread?.quitSafely()
        thread = null
        worker = null
        if (bound) runCatching { context.unbindService(conn) }
        bound = false
        svc = null
        last = null
    }

    private fun publish(s: State?) {
        if (s == last) return
        last = s
        main.post { onState(s) }
    }

    private fun read(): State? {
        val s = svc ?: return null
        val info = call(s, SVC, 2) { it.readStrongBinder() } ?: return null
        return State(
            sourceType = call(info, INFO, 9) { it.readInt() } ?: -1,
            frequency = call(info, INFO, 5) { it.readString() },
            station = call(info, INFO, 6) { it.readString() },
            title = call(info, INFO, 2) { it.readString() },
            playing = call(info, INFO, 11) { it.readInt() } == 1,
            pkg = call(info, INFO, 27) { it.readString() },
            appName = call(info, INFO, 25) { it.readString() },
        )
    }

    /** Một giao dịch binder không tham số; lỗi → null. */
    private fun <T> call(b: IBinder, descriptor: String, code: Int, read: (Parcel) -> T): T? {
        val data = Parcel.obtain()
        val reply = Parcel.obtain()
        return try {
            data.writeInterfaceToken(descriptor)
            b.transact(code, data, reply, 0)
            reply.readException()
            read(reply)
        } catch (t: Throwable) {
            null
        } finally {
            data.recycle()
            reply.recycle()
        }
    }

    private companion object {
        const val TAG = "MediaCenterSource"
        const val PKG = "ecarx.xsf.mediacenter"
        const val SERVICE = "ecarx.xsf.mediacenter.MediaCenterService"
        const val ACTION = "ecarx.xsf.ACTION_MEDIA_CENTER_WIDGET_API_SERVICE"
        const val SVC = "ecarx.xsf.mediacenter.IMediaCenterWidgetApiSvc"
        const val INFO = "ecarx.xsf.mediacenter.IMusicPlaybackInfo"
        const val POLL_MS = 1500L
    }
}
