package vn.tht.launcher.ui

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import vn.tht.launcher.R

/** Hàm dựng view dùng chung cho widget/hộp thoại, để không phải viết XML cho từng ô. */
object Ui {
    fun dp(c: Context, v: Number): Int = (v.toFloat() * c.resources.displayMetrics.density).toInt()

    fun text(c: Context, s: String = "", size: Float = 18f, dim: Boolean = false, bold: Boolean = false) =
        TextView(c).apply {
            text = s
            textSize = size
            setTextColor(if (dim) Palette.textDim else Palette.text)
            if (bold) typeface = android.graphics.Typeface.create("sans-serif-medium", android.graphics.Typeface.NORMAL)
        }

    fun card(c: Context, radiusDp: Int = 20): GradientDrawable = GradientDrawable().apply {
        cornerRadius = dp(c, radiusDp).toFloat()
        setColor(Palette.card)
    }

    fun pill(c: Context, color: Int, radiusDp: Int = 14) = GradientDrawable().apply {
        cornerRadius = dp(c, radiusDp).toFloat()
        setColor(color)
    }

    fun vbox(c: Context, padDp: Int = 0, gravity: Int = Gravity.START) = LinearLayout(c).apply {
        orientation = LinearLayout.VERTICAL
        this.gravity = gravity
        val p = dp(c, padDp)
        setPadding(p, p, p, p)
    }

    fun hbox(c: Context, gravity: Int = Gravity.CENTER_VERTICAL) = LinearLayout(c).apply {
        orientation = LinearLayout.HORIZONTAL
        this.gravity = gravity
    }

    /** Nút phẳng bo góc. [active] = trạng thái bật (tô màu nhấn). */
    fun button(c: Context, label: String, size: Float = 18f, onClick: () -> Unit) = Button(c).apply {
        text = label
        textSize = size
        isAllCaps = false
        setTextColor(Palette.text)
        background = pill(c, Palette.button)
        minWidth = dp(c, 64)
        minHeight = dp(c, 56)
        stateListAnimator = null
        setOnClickListener { onClick() }
    }

    fun setActive(b: View, active: Boolean) {
        val c = b.context
        b.background = pill(c, if (active) Palette.accent else Palette.button)
    }

    fun weight(w: Float = 1f) = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, w)

    fun wrap(marginEndDp: Int = 0, c: Context? = null) = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,
    ).apply { if (c != null) marginEnd = dp(c, marginEndDp) }

    fun spacer(c: Context) = View(c).apply {
        layoutParams = LinearLayout.LayoutParams(0, 0, 1f)
    }
}
