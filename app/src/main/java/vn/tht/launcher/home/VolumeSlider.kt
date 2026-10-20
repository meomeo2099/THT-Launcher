package vn.tht.launcher.home

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.media.AudioManager
import android.view.MotionEvent
import android.view.View
import vn.tht.launcher.ui.Icons
import vn.tht.launcher.ui.Palette
import vn.tht.launcher.ui.Ui

/** Thanh âm lượng dọc (nhạc): kéo để chỉnh, phần sáng là mức hiện tại, icon loa ở đáy. */
@SuppressLint("ViewConstructor")
class VolumeSlider(c: Context) : View(c) {
    private val am = c.getSystemService(AudioManager::class.java)
    private val stream = AudioManager.STREAM_MUSIC
    private val track = Paint(Paint.ANTI_ALIAS_FLAG)
    private val fill = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rect = RectF()
    private val radius = Ui.dp(c, 18).toFloat()

    private val max get() = am?.getStreamMaxVolume(stream)?.coerceAtLeast(1) ?: 1
    private val level get() = am?.getStreamVolume(stream) ?: 0

    fun refresh() = invalidate()

    override fun onDraw(canvas: Canvas) {
        track.color = Palette.withAlpha(if (Palette.night) 0xFF000000.toInt() else 0xFFFFFFFF.toInt(), 150)
        fill.color = Palette.withAlpha(Palette.text, 235)
        rect.set(0f, 0f, width.toFloat(), height.toFloat())
        canvas.drawRoundRect(rect, radius, radius, track)
        val frac = level.toFloat() / max
        val top = height * (1f - frac)
        canvas.save()
        canvas.clipRect(0f, top, width.toFloat(), height.toFloat())
        canvas.drawRoundRect(rect, radius, radius, fill)
        canvas.restore()
        // Icon loa ở đáy, màu tương phản với phần đã tô
        val key = if (level == 0) "muted" else "speaker"
        val s = (width * 0.55f).toInt()
        Icons.get(context, key, if (frac > 0.15f) Palette.bg else Palette.text)?.apply {
            val l = (width - s) / 2
            val t = height - s - l
            setBounds(l, t, l + s, t + s)
            draw(canvas)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(e: MotionEvent): Boolean {
        when (e.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                parent?.requestDisallowInterceptTouchEvent(true)
                val frac = (1f - e.y / height).coerceIn(0f, 1f)
                val v = Math.round(frac * max)
                if (v != level) runCatching { am?.setStreamVolume(stream, v, 0) }
                invalidate()
            }
        }
        return true
    }
}
