package vn.tht.launcher.radio

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.os.Looper
import android.os.Parcel
import android.util.Log

/**
 * Điều khiển radio của đầu xe ECARX qua dịch vụ `com.ecarx.radio.service` (binder `IRadioSvc`).
 *
 * Số hiệu giao dịch (đọc tĩnh từ cách một launcher khác gọi dịch vụ; tự viết lại):
 * 1 mở radio (tham số: binder nhận callback; trả int ≠ 0 nếu nhận), 3 đặt băng (int: 0 AM, 1 FM),
 * 4 đặt tần số (int, kHz), 17 chuyển nguồn âm thanh sang radio, 12 đọc băng, 13 đọc tần số,
 * 20 đọc trạng thái nguồn (0x1001, 0x2000, 0x2001, 0x4000 = radio đang giữ âm thanh).
 * Tần số tính bằng kHz: FM ≥ 30000 (98500 = 98,5 MHz), AM khoảng 522–1620.
 */
class EcarxRadio(private val context: Context) {
    data class State(val band: Int, val freqKHz: Int, val status: Int) {
        val fm: Boolean get() = band == 1 || (band != 0 && freqKHz >= 30000)
        /** Radio đang là nguồn phát âm thanh. */
        val playing: Boolean get() = status in PLAYING_STATES
        fun label(): String = if (fm) "FM " + String.format(java.util.Locale.US, "%.1f", freqKHz / 1000f) else "AM $freqKHz"
    }

    /** Đã nối dịch vụ radio (chỉ có trên đầu xe ECARX). */
    val available: Boolean get() = svc != null
    @Volatile var state: State? = null
        private set

    private val listeners = mutableListOf<(State?) -> Unit>()
    private val main = Handler(Looper.getMainLooper())
    private var thread: HandlerThread? = null
    private var worker: Handler? = null
    @Volatile private var svc: IBinder? = null
    private var bound = false
    private var opened = false
    private val callback = Binder()
    private val prefs = context.getSharedPreferences("radio", Context.MODE_PRIVATE)

    private val conn = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            if (runCatching { service?.interfaceDescriptor }.getOrNull() != DESCRIPTOR) {
                Log.w(TAG, "Dịch vụ radio không đúng giao diện")
                return
            }
            svc = service
            worker?.post { open(); poll.run() }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            svc = null
            opened = false
            publish(null)
        }
    }

    private val poll = object : Runnable {
        override fun run() {
            worker?.removeCallbacks(this)
            if (svc == null) return
            val f = int(13)
            publish(if (f != null && f > 0) State(int(12) ?: -1, f, int(20) ?: -1) else null)
            worker?.postDelayed(this, POLL_MS)
        }
    }

    fun addListener(l: (State?) -> Unit) { listeners += l; l(state) }
    fun removeListener(l: (State?) -> Unit) { listeners -= l }

    fun start() {
        if (thread != null) return
        thread = HandlerThread("radio").apply { start() }
        worker = Handler(thread!!.looper)
        val i = Intent(ACTION).addCategory(CATEGORY).setComponent(ComponentName(PKG, SERVICE))
        bound = runCatching { context.bindService(i, conn, Context.BIND_AUTO_CREATE) }.getOrDefault(false)
        if (!bound) Log.i(TAG, "Không có dịch vụ radio $PKG")
    }

    fun stop() {
        worker?.removeCallbacksAndMessages(null)
        thread?.quitSafely()
        thread = null
        worker = null
        if (bound) runCatching { context.unbindService(conn) }
        bound = false
        svc = null
        opened = false
    }

    // ---- Lệnh (chạy trên luồng radio, rồi đọc lại trạng thái) ----

    /** Đặt tần số (kHz) và chuyển âm thanh sang radio. */
    fun tune(kHz: Int) = run {
        val cur = state
        val wantFm = kHz >= 30000
        if (cur == null || cur.fm != wantFm) call(3) { it.writeInt(if (wantFm) 1 else 0) }
        call(4) { it.writeInt(kHz) }
        if (state?.playing != true) call(17)
    }

    /** Dò từng bước: FM 0,1 MHz (87,5–108), AM 9 kHz (531–1602). */
    fun step(dir: Int) {
        val s = state ?: return
        val next = if (s.fm) (s.freqKHz + dir * 100).let { if (it > 108000) 87500 else if (it < 87500) 108000 else it }
        else (s.freqKHz + dir * 9).let { if (it > 1602) 531 else if (it < 531) 1602 else it }
        tune(next)
    }

    fun play() = run { call(17) }

    fun switchBand() {
        val s = state ?: return
        tune(if (s.fm) lastFreq(false) else lastFreq(true))
    }

    private fun lastFreq(fm: Boolean) = prefs.getInt(if (fm) "last_fm" else "last_am", if (fm) 87500 else 531)

    // ---- Đài yêu thích (kHz, theo thứ tự thêm) ----

    fun favorites(): List<Int> = prefs.getString("favorites", "").orEmpty().split(',').mapNotNull { it.toIntOrNull() }

    fun isFavorite(kHz: Int) = kHz in favorites()

    fun toggleFavorite(kHz: Int) {
        val list = favorites().toMutableList()
        if (kHz in list) list -= kHz else list += kHz
        prefs.edit().putString("favorites", list.joinToString(",")).apply()
        publish(state, force = true)
    }

    /** Sang đài yêu thích kế tiếp/trước; chưa có đài yêu thích thì dò từng bước. */
    fun nextFavorite(dir: Int) {
        val favs = favorites().sorted()
        val cur = state?.freqKHz ?: return
        if (favs.isEmpty()) return step(dir)
        val target = if (dir > 0) favs.firstOrNull { it > cur } ?: favs.first() else favs.lastOrNull { it < cur } ?: favs.last()
        tune(target)
    }

    // ---- Nội bộ ----

    private fun run(block: () -> Unit) {
        val w = worker ?: return
        w.post {
            if (svc == null) return@post
            if (!opened) open()
            block()
            w.postDelayed(poll, 300)
        }
    }

    private fun open() {
        if (opened) return
        opened = call(1, { it.writeStrongBinder(callback) }) { it.readInt() != 0 } == true
        if (!opened) Log.w(TAG, "Dịch vụ radio từ chối mở")
    }

    private fun int(code: Int): Int? = call(code, {}) { it.readInt() }

    private fun call(code: Int, write: (Parcel) -> Unit = {}) { call<Unit>(code, write) {} }

    private fun <T> call(code: Int, write: (Parcel) -> Unit, read: (Parcel) -> T): T? {
        val b = svc ?: return null
        val data = Parcel.obtain()
        val reply = Parcel.obtain()
        return try {
            data.writeInterfaceToken(DESCRIPTOR)
            write(data)
            b.transact(code, data, reply, 0)
            reply.readException()
            read(reply)
        } catch (t: Throwable) {
            Log.w(TAG, "radio $code lỗi: $t")
            null
        } finally {
            data.recycle()
            reply.recycle()
        }
    }

    private fun publish(s: State?, force: Boolean = false) {
        if (s == state && !force) return
        state = s
        s?.let { prefs.edit().putInt(if (it.fm) "last_fm" else "last_am", it.freqKHz).apply() }
        main.post { listeners.toList().forEach { it(s) } }
    }

    private companion object {
        const val TAG = "EcarxRadio"
        const val PKG = "com.ecarx.radio.service"
        const val SERVICE = "com.ecarx.radio.service.RadioService"
        const val ACTION = "android.intent.action.START_XCRADIO_SERVICE"
        const val CATEGORY = "android.intent.category.XCRadio"
        const val DESCRIPTOR = "com.ecarx.radio.service.IRadioSvc"
        const val POLL_MS = 1500L
        val PLAYING_STATES = setOf(0x1001, 0x2000, 0x2001, 0x4000)
    }
}
