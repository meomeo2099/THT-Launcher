package vn.tht.launcher.system

import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextClock
import vn.tht.launcher.settings.Prefs
import vn.tht.launcher.ui.Ui

/**
 * Bảo vệ màn hình: sau N phút không chạm (khi launcher đang hiện) phủ một lớp tối có đồng hồ lớn
 * và bài đang phát. Chạm để tắt. Tuỳ chọn chỉ bật khi xe đỗ.
 */
class Screensaver(
    private val root: FrameLayout,
    private val prefs: Prefs,
    /** true khi xe đang đỗ (tốc độ ~0 hoặc số P); null = không rõ. */
    private val parked: () -> Boolean?,
    private val track: () -> String?,
) {
    private val c = root.context
    private val handler = Handler(Looper.getMainLooper())
    private var overlay: View? = null
    private var active = false
    private val trackText = Ui.text(c, "", 24f).apply { setTextColor(Color.argb(200, 255, 255, 255)); gravity = Gravity.CENTER }

    private val trigger = Runnable { maybeShow() }

    /** Gọi ở onResume và mỗi lần chạm. */
    fun poke() {
        if (overlay != null) hide()
        handler.removeCallbacks(trigger)
        if (active && prefs.saverEnabled) handler.postDelayed(trigger, prefs.saverMinutes.coerceIn(1, 30) * 60_000L)
    }

    fun setActive(on: Boolean) {
        active = on
        if (!on) { handler.removeCallbacks(trigger); hide() } else poke()
    }

    private fun maybeShow() {
        if (!active || !prefs.saverEnabled) return
        if (prefs.saverParkedOnly && parked() != true) {
            handler.postDelayed(trigger, 60_000L)
            return
        }
        val dim = prefs.saverDim.coerceIn(0, 90)
        val v = FrameLayout(c).apply {
            setBackgroundColor(Color.argb(255 * (10 + dim) / 100, 0, 0, 0).let { if (dim >= 90) Color.BLACK else it })
            elevation = 50f
            isClickable = true
            setOnClickListener { poke() }
            addView(Ui.vbox(c, gravity = Gravity.CENTER).apply {
                addView(TextClock(c).apply {
                    format24Hour = if (prefs.clock24h) "HH:mm" else "hh:mm"
                    format12Hour = if (prefs.clock24h) "HH:mm" else "hh:mm a"
                    textSize = 160f
                    setTextColor(Color.argb(220, 255, 255, 255))
                    typeface = android.graphics.Typeface.create("sans-serif-thin", android.graphics.Typeface.NORMAL)
                })
                if (prefs.clockShowDate) addView(TextClock(c).apply {
                    format24Hour = "EEEE, dd/MM"; format12Hour = "EEEE, dd/MM"
                    textSize = 28f
                    setTextColor(Color.argb(170, 255, 255, 255))
                })
                val w = vn.tht.launcher.system.Weather.current
                if (prefs.sp.getBoolean("saver_weather", true) && w != null) addView(Ui.hbox(c, Gravity.CENTER).apply {
                    setPadding(0, Ui.dp(c, 16), 0, 0)
                    addView(android.widget.ImageView(c).apply {
                        setImageDrawable(vn.tht.launcher.ui.Icons.get(c, w.icon, Color.argb(200, 255, 255, 255)))
                    }, LinearLayout.LayoutParams(Ui.dp(c, 40), Ui.dp(c, 40)).apply { marginEnd = Ui.dp(c, 12) })
                    addView(Ui.text(c, String.format(java.util.Locale.US, "%+.0f° · %s", w.tempC, w.text), 26f).apply {
                        setTextColor(Color.argb(190, 255, 255, 255))
                    })
                })
                if (prefs.saverShowTrack) addView(trackText.apply { setPadding(0, Ui.dp(c, 24), 0, 0) })
            }, FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT))
        }
        trackText.text = track().orEmpty()
        overlay = v
        root.addView(v, FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT))
    }

    fun updateTrack() {
        if (overlay != null) trackText.text = track().orEmpty()
    }

    private fun hide() {
        overlay?.let { (trackText.parent as? LinearLayout)?.removeView(trackText); root.removeView(it) }
        overlay = null
    }
}
