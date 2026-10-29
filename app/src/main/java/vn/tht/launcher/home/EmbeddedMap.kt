package vn.tht.launcher.home

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.FrameLayout

/**
 * Nhúng app bản đồ vào một ô bằng `android.app.ActivityView` (API ẩn của Android 9).
 *
 * Chỉ chạy khi APK có quyền hệ thống (ký khoá platform của đầu xe): firmware mới cho nhúng app
 * không khai báo allowEmbedded. Mọi lỗi (thiếu quyền, API bị chặn, app không cho nhúng) đều trả
 * false để ô dẫn đường quay về chế độ nút mở bản đồ.
 */
class EmbeddedMap(private val c: Context, private val onFail: (String) -> Unit) : FrameLayout(c) {
    private val handler = Handler(Looper.getMainLooper())
    private var activityView: View? = null
    private var pending: Intent? = null
    private var tries = 0
    private var ready = false
    private var released = false
    private var active = false

    /** Tạo ActivityView; false nếu hệ thống không cho. */
    fun create(): Boolean = try {
        released = false
        val v = PlatformActivityView.create(c, ready = {
            if (!released) { ready = true; handler.post { tryStart() } }
        }, destroyed = {
            handler.post { if (!released) { ready = false; onFail("Màn hình bản đồ đã đóng") } }
        })
        addView(v, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
        activityView = v
        true
    } catch (t: Throwable) {
        Log.w(TAG, "Không tạo được ActivityView: $t")
        false
    }

    /** Mở app trong ô; ActivityView cần vài trăm ms để sẵn sàng nên thử lại tới khi được. */
    fun start(intent: Intent) {
        pending = Intent(intent).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        tries = 0
        handler.removeCallbacksAndMessages(null)
        handler.postDelayed(::tryStart, 300)
    }

    private fun tryStart() {
        val v = activityView ?: return
        val i = pending ?: return
        if (released || !active) return
        // Wait for the framework callback and a real laid-out surface, not a guessed 300 ms.
        if (!isAttachedToWindow || !isShown || !hasWindowFocus() || width <= 0 || height <= 0) {
            handler.postDelayed(::tryStart, 250)
            return
        }
        if (!ready) {
            if (++tries < 40) handler.postDelayed(::tryStart, 250)
            else { pending = null; onFail("ActivityView chưa sẵn sàng sau 10 giây") }
            return
        }
        try {
            v.javaClass.getMethod("startActivity", Intent::class.java).invoke(v, i)
            Log.i(TAG, "Đã nhúng ${i.component ?: i.`package`}")
            pending = null
        } catch (t: Throwable) {
            val cause = (t as? java.lang.reflect.InvocationTargetException)?.targetException ?: t
            // Chưa sẵn sàng: thử lại; lỗi quyền/không cho nhúng: bỏ cuộc
            if (cause is IllegalStateException && ++tries < 20) {
                handler.postDelayed(::tryStart, 300)
            } else {
                Log.w(TAG, "Không nhúng được bản đồ: $cause")
                lastError = "${i.`package`}: $cause"
                onFail(cause.javaClass.simpleName + ": " + (cause.message ?: ""))
            }
        }
    }

    fun release() {
        if (released) return
        released = true
        ready = false
        pending = null
        handler.removeCallbacksAndMessages(null)
        activityView?.let { v -> runCatching { v.javaClass.getMethod("release").invoke(v) } }
        activityView = null
        removeAllViews()
    }

    fun setActive(value: Boolean) {
        active = value
        handler.removeCallbacksAndMessages(null)
        if (active) handler.post { tryStart() }
    }

    override fun onDetachedFromWindow() {
        release()
        super.onDetachedFromWindow()
    }

    companion object {
        private const val TAG = "EmbeddedMap"
        /** Lỗi nhúng gần nhất (cho báo cáo chẩn đoán); null = chưa lỗi. */
        @Volatile var lastError: String? = null
    }
}
