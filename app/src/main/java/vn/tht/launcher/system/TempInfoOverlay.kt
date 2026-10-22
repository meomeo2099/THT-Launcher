package vn.tht.launcher.system

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.PixelFormat
import android.graphics.drawable.GradientDrawable
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import vn.tht.launcher.settings.Prefs
import vn.tht.launcher.ui.Icons
import vn.tht.launcher.ui.Ui

/**
 * Khung "Nhiệt độ" nổi trên app: khi đang mở app khác mà nhiệt độ / quạt / hướng gió đổi,
 * hiện vài giây ở vị trí đã chọn (mặc định giữa mép trái, cạnh viên nút điều hướng), không chặn chạm vào app bên dưới.
 * Ở chế độ xem thử thì kéo được để đổi chỗ. Dùng lớp phủ của dịch vụ trợ năng (không cần quyền
 * hiển thị trên app khác).
 */
class TempInfoOverlay(private val c: Context, private val wm: WindowManager, private val prefs: Prefs) {
    private val handler = Handler(Looper.getMainLooper())
    private var view: LinearLayout? = null
    private lateinit var lp: WindowManager.LayoutParams
    private lateinit var temp: TextView
    private lateinit var fan: TextView
    private lateinit var airIcon: ImageView
    private val hide = Runnable { remove() }

    /** Hiện [tempText] (vd "23.5°"), [fanText] (vd "Quạt 3"), icon hướng gió trong [ms]. */
    fun show(tempText: String, fanText: String, airKey: String?, ms: Long = 3500, draggable: Boolean = false) {
        if (view == null) create()
        temp.text = tempText
        fan.text = fanText
        airIcon.visibility = if (airKey == null) View.GONE else View.VISIBLE
        airKey?.let { airIcon.setImageDrawable(Icons.get(c, it, Color.WHITE)) }
        lp.flags = baseFlags or if (draggable) 0 else WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        runCatching { wm.updateViewLayout(view, lp) }
        handler.removeCallbacks(hide)
        handler.postDelayed(hide, ms)
    }

    fun resetPosition() {
        prefs.sp.edit().remove(KEY_X).remove(KEY_Y).apply()
        remove()
    }

    fun remove() {
        handler.removeCallbacks(hide)
        view?.let { runCatching { wm.removeView(it) } }
        view = null
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun create() {
        val pad = Ui.dp(c, 14)
        temp = TextView(c).apply { textSize = 30f; setTextColor(Color.WHITE); typeface = android.graphics.Typeface.DEFAULT_BOLD }
        fan = TextView(c).apply { textSize = 16f; setTextColor(0xCCFFFFFF.toInt()) }
        airIcon = ImageView(c)
        val v = LinearLayout(c).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(pad, pad, pad + Ui.dp(c, 4), pad)
            background = GradientDrawable().apply { cornerRadius = Ui.dp(c, 24).toFloat(); setColor(0xD9101418.toInt()) }
            addView(ImageView(c).apply { setImageDrawable(Icons.get(c, "fan", Color.WHITE)) },
                LinearLayout.LayoutParams(Ui.dp(c, 40), Ui.dp(c, 40)).apply { marginEnd = Ui.dp(c, 12) })
            addView(LinearLayout(c).apply {
                orientation = LinearLayout.VERTICAL
                addView(temp)
                addView(fan)
            })
            addView(airIcon, LinearLayout.LayoutParams(Ui.dp(c, 36), Ui.dp(c, 36)).apply { marginStart = Ui.dp(c, 14) })
        }
        lp = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY, baseFlags, PixelFormat.TRANSLUCENT,
        ).apply {
            gravity = Gravity.TOP or Gravity.START
            val screen = c.resources.displayMetrics
            // Mặc định giữa mép trái, cạnh phải viên nút Quay lại/Màn hình chính
            x = prefs.sp.getInt(KEY_X, Ui.dp(c, 88))
            y = prefs.sp.getInt(KEY_Y, screen.heightPixels / 2 - Ui.dp(c, 50))
        }
        // Kéo để đổi chỗ (chỉ khi đang xem thử – lúc thường khung không nhận chạm)
        var sx = 0f; var sy = 0f; var ox = 0; var oy = 0
        v.setOnTouchListener { _, ev ->
            when (ev.actionMasked) {
                MotionEvent.ACTION_DOWN -> { sx = ev.rawX; sy = ev.rawY; ox = lp.x; oy = lp.y; handler.removeCallbacks(hide) }
                MotionEvent.ACTION_MOVE -> {
                    lp.x = ox + (ev.rawX - sx).toInt(); lp.y = oy + (ev.rawY - sy).toInt()
                    runCatching { wm.updateViewLayout(v, lp) }
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    prefs.sp.edit().putInt(KEY_X, lp.x).putInt(KEY_Y, lp.y).apply()
                    handler.postDelayed(hide, 3000)
                }
            }
            true
        }
        runCatching { wm.addView(v, lp) }.onSuccess { view = v }
    }

    private companion object {
        const val KEY_X = "temp_info_x"
        const val KEY_Y = "temp_info_y"
        const val baseFlags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
    }
}
