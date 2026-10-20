package vn.tht.launcher.media

import android.content.ComponentName
import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadata
import android.media.session.MediaController
import android.media.session.MediaSessionManager
import android.media.session.PlaybackState
import android.provider.Settings

data class NowPlaying(
    val title: String?,
    val artist: String?,
    val art: Bitmap?,
    val isPlaying: Boolean,
    /** Tên app đang phát (Spotify, Zing MP3…). */
    val source: String? = null,
    val durationMs: Long = 0,
    /** Vị trí tại thời điểm [positionAt] (elapsedRealtime) và tốc độ phát, để tự ước lượng. */
    val positionMs: Long = 0,
    val positionAt: Long = 0,
    val speed: Float = 0f,
) {
    /** Vị trí hiện tại ước lượng (ms). */
    fun currentPosition(): Long {
        if (!isPlaying || positionAt == 0L) return positionMs
        val p = positionMs + ((android.os.SystemClock.elapsedRealtime() - positionAt) * speed).toLong()
        return if (durationMs > 0) p.coerceIn(0, durationMs) else p
    }
}

/** Theo dõi phiên phát nhạc đang hoạt động (Spotify, YouTube Music, Zing MP3, Bluetooth...). */
class MediaWatcher(
    private val context: Context,
    private val onChange: (NowPlaying?) -> Unit,
) {
    private val msm = context.getSystemService(MediaSessionManager::class.java)
    private val listenerComponent = ComponentName(context, MediaListenerService::class.java)
    private var controller: MediaController? = null

    private val callback = object : MediaController.Callback() {
        override fun onMetadataChanged(metadata: MediaMetadata?) = publish()
        override fun onPlaybackStateChanged(state: PlaybackState?) = publish()
        override fun onSessionDestroyed() = refresh()
    }

    private val sessionsListener =
        MediaSessionManager.OnActiveSessionsChangedListener { refresh() }

    fun hasPermission(): Boolean {
        val enabled = Settings.Secure.getString(
            context.contentResolver, "enabled_notification_listeners"
        ) ?: return false
        return enabled.contains(context.packageName)
    }

    fun start() {
        if (!hasPermission()) {
            onChange(null); return
        }
        try {
            msm.addOnActiveSessionsChangedListener(sessionsListener, listenerComponent)
        } catch (_: SecurityException) {
        }
        refresh()
    }

    fun stop() {
        try {
            msm.removeOnActiveSessionsChangedListener(sessionsListener)
        } catch (_: Throwable) {
        }
        controller?.unregisterCallback(callback)
        controller = null
    }

    private fun refresh() {
        val sessions = try {
            msm.getActiveSessions(listenerComponent)
        } catch (_: SecurityException) {
            emptyList()
        }
        val best = sessions.firstOrNull { it.playbackState?.state == PlaybackState.STATE_PLAYING }
            ?: sessions.firstOrNull()
        if (best?.sessionToken != controller?.sessionToken) {
            controller?.unregisterCallback(callback)
            controller = best
            best?.registerCallback(callback)
        }
        publish()
    }

    private fun publish() {
        val c = controller ?: return onChange(null)
        val md = c.metadata
        val st = c.playbackState
        val source = runCatching {
            val pm = context.packageManager
            pm.getApplicationLabel(pm.getApplicationInfo(c.packageName, 0)).toString()
        }.getOrNull()
        onChange(
            NowPlaying(
                title = md?.getString(MediaMetadata.METADATA_KEY_TITLE),
                artist = md?.getString(MediaMetadata.METADATA_KEY_ARTIST),
                art = md?.getBitmap(MediaMetadata.METADATA_KEY_ALBUM_ART)
                    ?: md?.getBitmap(MediaMetadata.METADATA_KEY_ART),
                isPlaying = st?.state == PlaybackState.STATE_PLAYING,
                source = source,
                durationMs = md?.getLong(MediaMetadata.METADATA_KEY_DURATION) ?: 0,
                positionMs = st?.position ?: 0,
                positionAt = st?.lastPositionUpdateTime ?: 0,
                speed = st?.playbackSpeed ?: 0f,
            )
        )
    }

    fun playPause() {
        val c = controller ?: return
        if (c.playbackState?.state == PlaybackState.STATE_PLAYING) c.transportControls.pause()
        else c.transportControls.play()
    }

    fun next() = controller?.transportControls?.skipToNext()
    fun previous() = controller?.transportControls?.skipToPrevious()
}
