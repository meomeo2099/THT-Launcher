package vn.tht.launcher.system

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.util.Log
import android.view.KeyEvent
import vn.tht.launcher.settings.Prefs

/**
 * Sau khi đầu xe khởi động: mở lần lượt các app đã chọn, (tuỳ chọn) quay về màn hình chính,
 * rồi mở trình phát nhạc và gửi phím PLAY.
 *
 * Chạy đúng một lần mỗi lần khởi động: BootReceiver đặt cờ [markBoot], launcher gọi [runIfPending].
 */
object Autostart {
    private const val TAG = "Autostart"
    private const val KEY_PENDING = "autostart_pending_boot"

    fun markBoot(c: Context) {
        Prefs(c).sp.edit().putLong(KEY_PENDING, System.currentTimeMillis() - SystemClock.elapsedRealtime()).apply()
    }

    fun runIfPending(activity: Activity) {
        val prefs = Prefs(activity)
        val boot = prefs.sp.getLong(KEY_PENDING, 0L)
        if (boot == 0L) return
        prefs.sp.edit().remove(KEY_PENDING).apply()
        // Cờ cũ từ lần khởi động trước (ví dụ launcher bị tắt): bỏ qua
        val thisBoot = System.currentTimeMillis() - SystemClock.elapsedRealtime()
        if (kotlin.math.abs(thisBoot - boot) > 60_000) return

        val apps = prefs.autostartApps
        val player = prefs.autoplayPlayer
        if (apps.isEmpty() && player.isBlank()) return
        val delay = prefs.autostartDelaySec.coerceIn(0, 30) * 1000L
        val h = Handler(Looper.getMainLooper())
        var t = 1500L
        for (pkg in apps) {
            h.postDelayed({ launch(activity, pkg) }, t)
            t += delay
        }
        if (apps.isNotEmpty() && prefs.autostartReturnHome) {
            h.postDelayed({ home(activity) }, t)
            t += 1000
        }
        if (player.isNotBlank()) {
            h.postDelayed({ launch(activity, player) }, t)
            h.postDelayed({ play(activity); if (prefs.autostartReturnHome) home(activity) }, t + 2500)
        }
    }

    private fun launch(c: Context, pkg: String) {
        val i = c.packageManager.getLaunchIntentForPackage(pkg) ?: return
        runCatching { c.startActivity(i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)) }
            .onFailure { Log.w(TAG, "Không mở được $pkg: $it") }
    }

    private fun home(c: Context) {
        runCatching {
            c.startActivity(Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME)
                .setPackage(c.packageName).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        }
    }

    /** Gửi phím PLAY tới phiên media đang hoạt động (không cần quyền đặc biệt). */
    fun play(c: Context) {
        val am = c.getSystemService(AudioManager::class.java) ?: return
        val now = SystemClock.uptimeMillis()
        am.dispatchMediaKeyEvent(KeyEvent(now, now, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PLAY, 0))
        am.dispatchMediaKeyEvent(KeyEvent(now, now, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MEDIA_PLAY, 0))
    }
}
