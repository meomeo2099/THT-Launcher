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
)

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
        onChange(
            NowPlaying(
                title = md?.getString(MediaMetadata.METADATA_KEY_TITLE),
                artist = md?.getString(MediaMetadata.METADATA_KEY_ARTIST),
                art = md?.getBitmap(MediaMetadata.METADATA_KEY_ALBUM_ART)
                    ?: md?.getBitmap(MediaMetadata.METADATA_KEY_ART),
                isPlaying = c.playbackState?.state == PlaybackState.STATE_PLAYING,
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
