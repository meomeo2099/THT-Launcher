package vn.tht.launcher.system

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.media.browse.MediaBrowser
import android.media.session.MediaController
import android.media.session.MediaSessionManager
import android.media.session.PlaybackState
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.provider.Settings
import android.service.media.MediaBrowserService
import android.util.Log
import android.view.KeyEvent
import vn.tht.launcher.MainActivity
import vn.tht.launcher.media.MediaListenerService
import vn.tht.launcher.settings.Prefs

/** Một lịch cho mỗi lần boot; tất cả lệnh media chỉ gửi tới trình phát người dùng đã chọn. */
object Autostart {
    private const val TAG = "Autostart"
    private const val KEY_PENDING = "autostart_pending_boot"
    private const val KEY_PENDING_COUNT = "autostart_pending_boot_count"
    private const val KEY_DONE_COUNT = "autostart_done_boot_count"
    private const val KEY_DONE_WALL = "autostart_done_wall_boot"
    private val handler = Handler(Looper.getMainLooper())
    private val engineHandler = Handler(Looper.getMainLooper())
    private var generation = 0L
    private var engineGeneration = 0L
    private var playback: PlaybackAttempt? = null

    private fun bootCount(c: Context): Int? = runCatching {
        Settings.Global.getInt(c.contentResolver, Settings.Global.BOOT_COUNT, -1).takeIf { it >= 0 }
    }.getOrNull()

    fun markBoot(c: Context) {
        val prefs = Prefs(c).sp
        val count = bootCount(c)
        val wall = System.currentTimeMillis() - SystemClock.elapsedRealtime()
        if (alreadyDone(prefs.getInt(KEY_DONE_COUNT, -1).takeIf { it >= 0 }, count,
                prefs.getLong(KEY_DONE_WALL, 0L), wall)) return
        prefs.edit().putLong(KEY_PENDING, wall).apply {
            if (count == null) remove(KEY_PENDING_COUNT) else putInt(KEY_PENDING_COUNT, count)
        }.apply()
    }

    private fun alreadyDone(doneCount: Int?, count: Int?, doneWall: Long, wall: Long): Boolean =
        (doneCount != null || doneWall > 0L) && StartupPolicy.sameBoot(doneCount, count, doneWall, wall)

    fun runIfPending(activity: Activity) {
        val c = activity.applicationContext
        val prefs = Prefs(c)
        val sp = prefs.sp
        val pendingWall = sp.getLong(KEY_PENDING, 0L)
        if (pendingWall == 0L) return
        val pendingCount = sp.getInt(KEY_PENDING_COUNT, -1).takeIf { it >= 0 }
        val count = bootCount(c)
        val wall = System.currentTimeMillis() - SystemClock.elapsedRealtime()
        sp.edit().remove(KEY_PENDING).remove(KEY_PENDING_COUNT).apply()
        if (!StartupPolicy.sameBoot(pendingCount, count, pendingWall, wall)) return
        if (alreadyDone(sp.getInt(KEY_DONE_COUNT, -1).takeIf { it >= 0 }, count, sp.getLong(KEY_DONE_WALL, 0L), wall)) return
        // Nhận boot trước khi lên lịch; process/activity bị tạo lại không nhân đôi lệnh.
        sp.edit().putLong(KEY_DONE_WALL, wall).apply {
            if (count == null) remove(KEY_DONE_COUNT) else putInt(KEY_DONE_COUNT, count)
        }.apply()
        val apps = StartupPolicy.packages(prefs.autostartApps, c.packageName)
        val player = StartupPolicy.packages(listOf(prefs.autoplayPlayer), c.packageName).firstOrNull()
        if (apps.isEmpty() && player == null) return
        cancel()
        val token = generation
        val returnHome = prefs.autostartReturnHome
        val gap = prefs.autostartDelaySec.coerceIn(0, 120) * 1_000L
        fun current(): Boolean = token == generation &&
            apps == StartupPolicy.packages(prefs.autostartApps, c.packageName) &&
            player == StartupPolicy.packages(listOf(prefs.autoplayPlayer), c.packageName).firstOrNull() &&
            returnHome == prefs.autostartReturnHome
        fun later(delay: Long, block: () -> Unit) {
            handler.postDelayed({ if (current()) block() }, delay)
        }
        var delay = 1_500L
        apps.forEach { pkg ->
            later(delay) { launch(c, pkg) }
            delay += gap
        }
        if (apps.isNotEmpty() && returnHome) {
            later(delay) { home(c) }
            delay += 1_000L
        }
        if (player != null) {
            later(delay) {
                if (launch(c, player)) {
                    playback = PlaybackAttempt(c, player, ::current).also { it.start() }
                }
            }
            if (returnHome) later(delay + 2_500L) { home(c) }
        }
    }

    /** Hủy lịch cũ khi launcher bị hủy hoặc trước khi nhận lịch thay thế. Không giữ Activity. */
    fun cancel() {
        generation++
        handler.removeCallbacksAndMessages(null)
        playback?.close()
        playback = null
    }

    /** Nhóm mở app sau nổ máy, dùng danh sách đã chọn; lịch độc lập với một-lần-mỗi-boot. */
    fun runForEngine(context: Context, stillValid: () -> Boolean) {
        val c = context.applicationContext
        val prefs = Prefs(c)
        cancelEngine()
        if (!prefs.sp.getBoolean("auto_apps_on", false)) return
        val apps = StartupPolicy.packages(prefs.autostartApps, c.packageName)
        if (apps.isEmpty()) return
        val token = engineGeneration
        val returnHome = prefs.autostartReturnHome
        fun current() = token == engineGeneration && stillValid() && prefs.sp.getBoolean("auto_apps_on", false) &&
            apps == StartupPolicy.packages(prefs.autostartApps, c.packageName) && returnHome == prefs.autostartReturnHome
        var delay = 1_500L
        apps.forEach { pkg ->
            engineHandler.postDelayed({ if (current()) launch(c, pkg) }, delay)
            delay += prefs.autostartDelaySec.coerceIn(0, 120) * 1_000L
        }
        if (returnHome) engineHandler.postDelayed({ if (current()) home(c) }, delay + 500L)
    }

    fun cancelEngine() {
        engineGeneration++
        engineHandler.removeCallbacksAndMessages(null)
    }

    private fun launch(c: Context, pkg: String): Boolean {
        val intent = c.packageManager.getLaunchIntentForPackage(pkg) ?: return false
        return runCatching {
            ExternalAppLauncher.launch(c, intent.setPackage(pkg))
        }.onFailure { Log.w(TAG, "Không mở được $pkg", it) }.getOrDefault(false)
    }

    private fun home(c: Context) {
        runCatching {
            c.startActivity(Intent(c, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }.onFailure { Log.w(TAG, "Không trở về được launcher", it) }
    }

    /** API tương thích: PLAY được giới hạn vào package cấu hình, không gửi media key toàn hệ thống. */
    fun play(c: Context) {
        val context = c.applicationContext
        val selected = StartupPolicy.packages(listOf(Prefs(context).autoplayPlayer), context.packageName).firstOrNull() ?: return
        playback?.close()
        playback = PlaybackAttempt(context, selected) { Prefs(context).autoplayPlayer == selected }.also { it.start() }
    }

    private class PlaybackAttempt(
        private val c: Context,
        private val selected: String,
        private val current: () -> Boolean,
    ) {
        private var closed = false
        private var polls = 0
        private var fallbackSent = false
        private var browser: MediaBrowser? = null
        private var browserController: MediaController? = null
        private var serviceIndex = 0
        private val services = runCatching {
            c.packageManager.queryIntentServices(Intent(MediaBrowserService.SERVICE_INTERFACE).setPackage(selected), 0)
                .mapNotNull { it.serviceInfo }
                .filter { it.enabled && it.exported && it.packageName == selected }
                .map { ComponentName(it.packageName, it.name) }.distinct().take(4)
        }.getOrDefault(emptyList())
        private val browserTimeout = Runnable { if (valid()) nextBrowser() else close() }
        private val poll = object : Runnable {
            override fun run() {
                if (!valid() || polls++ >= 25) { close(); return }
                val sessions = sessions()
                val target = sessions.firstOrNull { it.packageName == selected } ?: browserController
                if (target?.playbackState?.state == PlaybackState.STATE_PLAYING ||
                    target?.playbackState?.state == PlaybackState.STATE_BUFFERING) { close(); return }
                // Sau lần yêu cầu đầu, không tranh lại quyền phát với app khác mà người dùng chọn.
                if (polls > 1 && sessions.any { it.packageName != selected && it.playbackState?.state == PlaybackState.STATE_PLAYING }) {
                    close(); return
                }
                if (target != null) runCatching { target.transportControls.play() }
                    .onFailure { Log.w(TAG, "Trình phát chưa nhận PLAY", it) }
                else if (!fallbackSent) {
                    fallbackSent = true
                    val now = SystemClock.uptimeMillis()
                    intArrayOf(KeyEvent.ACTION_DOWN, KeyEvent.ACTION_UP).forEach { action ->
                        runCatching {
                            c.sendBroadcast(Intent(Intent.ACTION_MEDIA_BUTTON).setPackage(selected)
                                .putExtra(Intent.EXTRA_KEY_EVENT, KeyEvent(now, now, action, KeyEvent.KEYCODE_MEDIA_PLAY, 0)))
                        }
                    }
                }
                handler.postDelayed(this, 3_000L)
            }
        }

        private fun valid() = !closed && current()

        fun start() {
            if (!valid()) { close(); return }
            nextBrowser()
            handler.postDelayed(poll, 2_000L)
        }

        private fun sessions(): List<MediaController> = runCatching {
            c.getSystemService(MediaSessionManager::class.java)
                ?.getActiveSessions(ComponentName(c, MediaListenerService::class.java)).orEmpty()
        }.getOrDefault(emptyList())

        private fun nextBrowser() {
            handler.removeCallbacks(browserTimeout)
            browser?.let { runCatching { it.disconnect() } }
            browser = null
            browserController = null
            if (!valid() || serviceIndex >= services.size) return
            val component = services[serviceIndex++]
            lateinit var candidate: MediaBrowser
            val callbacks = object : MediaBrowser.ConnectionCallback() {
                override fun onConnected() {
                    if (!valid() || browser !== candidate) { runCatching { candidate.disconnect() }; return }
                    handler.removeCallbacks(browserTimeout)
                    val controller = runCatching { MediaController(c, candidate.sessionToken) }.getOrNull()
                        ?: run { nextBrowser(); return }
                    if (controller.packageName != selected) { nextBrowser(); return }
                    browserController = controller
                    runCatching { controller.transportControls.play() }
                }
                override fun onConnectionFailed() { if (valid() && browser === candidate) nextBrowser() }
                override fun onConnectionSuspended() { if (valid() && browser === candidate) nextBrowser() }
            }
            candidate = MediaBrowser(c, component, callbacks, null)
            browser = candidate
            runCatching { candidate.connect() }.onFailure { nextBrowser() }
            if (browser === candidate && valid()) handler.postDelayed(browserTimeout, 3_000L)
        }

        fun close() {
            if (closed) return
            closed = true
            handler.removeCallbacks(poll)
            handler.removeCallbacks(browserTimeout)
            browser?.let { runCatching { it.disconnect() } }
            browser = null
            browserController = null
        }
    }
}
