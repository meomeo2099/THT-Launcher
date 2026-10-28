package vn.tht.launcher.system

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import vn.tht.launcher.ui.Fonts
import vn.tht.launcher.ui.Icons
import vn.tht.launcher.ui.Palette
import vn.tht.launcher.ui.Ui
import vn.tht.launcher.vehicle.EcarxFunctions as F
import java.util.Locale

/** Màu thanh bên theo kiểu thanh gốc ECARX: đêm nền gần đen chữ trắng, ngày nền sáng chữ tối. */
object SideBarColors {
    val bg get() = if (Palette.night) 0xFF15181C.toInt() else 0xFFE6EDF5.toInt()
    val fg get() = if (Palette.night) 0xFFFFFFFF.toInt() else 0xFF1C2733.toInt()
    val dim get() = Palette.withAlpha(fg, 90)
}

/** Hàng vạch nghiêng mức quạt (như "✱ ////////" dưới nhiệt độ của thanh gốc). */
@SuppressLint("ViewConstructor")
class FanBars(c: Context) : View(c) {
    var level: Int = 0
        set(v) { if (field != v) { field = v; invalidate() } }
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply { strokeCap = Paint.Cap.ROUND }
    private val fan = Icons.get(c, "fan", SideBarColors.fg)

    override fun onDraw(canvas: Canvas) {
        val h = height.toFloat()
        val icon = (h * 0.9f).toInt()
        fan?.setBounds(0, ((h - icon) / 2).toInt(), icon, ((h + icon) / 2).toInt())
        fan?.draw(canvas)
        val start = icon + h * 0.35f
        val step = (width - start) / F.FAN_MAX
        paint.strokeWidth = maxOf(2f, step * 0.28f)
        for (i in 0 until F.FAN_MAX) {
            paint.color = if (i < level) SideBarColors.fg else SideBarColors.dim
            val x = start + i * step + step * 0.3f
            canvas.drawLine(x, h * 0.85f, x + step * 0.45f, h * 0.15f, paint)
        }
    }
}

/** Khối nhiệt độ một bên: "22.0°C" lớn + hàng vạch quạt; chạm để mở điều hoà. */
@SuppressLint("ViewConstructor")
class TempBlock(c: Context, onClick: () -> Unit) : LinearLayout(c) {
    private val value = TextView(c).apply {
        gravity = Gravity.CENTER
        maxLines = 1
        setTextColor(SideBarColors.fg)
        typeface = Fonts.bold(c) ?: Typeface.create("sans-serif-condensed", Typeface.BOLD)
        setAutoSizeTextTypeUniformWithConfiguration(14, 30, 1, android.util.TypedValue.COMPLEX_UNIT_SP)
        text = "--"
    }
    private val bars = FanBars(c)

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER
        val p = Ui.dp(c, 10)
        setPadding(p, 0, p, 0)
        background = c.getDrawable(vn.tht.launcher.R.drawable.ripple)
        addView(value, LayoutParams(LayoutParams.MATCH_PARENT, Ui.dp(c, 38)))
        addView(bars, LayoutParams(LayoutParams.MATCH_PARENT, Ui.dp(c, 14)).apply { topMargin = Ui.dp(c, 2) })
        setOnClickListener { onClick() }
    }

    fun bind(t: Float?, fan: Int?) {
        val s = t?.let { String.format(Locale.US, "%.1f°C", it) } ?: "--°C"
        if (value.text.toString() != s) value.text = s
        bars.level = fan ?: 0
    }
}

/** Nút quay lại kiểu thanh gốc: ô vuông bo góc nền tương phản, mũi tên ‹ ở giữa. */
fun backSquare(c: Context, onClick: () -> Unit): View = LinearLayout(c).apply {
    gravity = Gravity.CENTER
    background = c.getDrawable(vn.tht.launcher.R.drawable.ripple)
    setOnClickListener { onClick() }
    addView(ImageView(c).apply {
        setImageDrawable(Icons.get(c, "chevron-left", SideBarColors.bg))
        background = Ui.pill(c, SideBarColors.fg, 10)
        val p = Ui.dp(c, 8)
        setPadding(p, p, p, p)
        contentDescription = "Quay lại"
    }, LinearLayout.LayoutParams(Ui.dp(c, 46), Ui.dp(c, 46)))
}
