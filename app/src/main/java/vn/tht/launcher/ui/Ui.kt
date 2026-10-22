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
            val custom = if (bold) Fonts.bold(c) else Fonts.regular(c)
            if (custom != null) typeface = custom
            else if (bold) typeface = android.graphics.Typeface.create("sans-serif-medium", android.graphics.Typeface.NORMAL)
        }

    fun card(c: Context, radiusDp: Int = 24): GradientDrawable = GradientDrawable().apply {
        cornerRadius = dp(c, radiusDp).toFloat()
        setColor(Palette.card)
        // Viền mảnh cho thẻ nổi trên nền ảnh/gradient
        setStroke(dp(c, 1), if (Palette.night) 0x1FFFFFFF else 0x14000000)
    }

    /** Tiêu đề widget: icon nhỏ + chữ mờ. */
    fun header(c: Context, iconKey: String, title: String) = hbox(c).apply {
        Icons.get(c, iconKey, Palette.textDim)?.let { d ->
            addView(android.widget.ImageView(c).apply { setImageDrawable(d) },
                LinearLayout.LayoutParams(dp(c, 22), dp(c, 22)).apply { marginEnd = dp(c, 8) })
        }
        addView(text(c, title, 17f, dim = true))
    }

    /** Nút icon tròn. Dùng [setIconActive] để tô trạng thái bật. */
    fun iconButton(c: Context, iconKey: String, sizeDp: Int = 60, onClick: () -> Unit) =
        android.widget.ImageView(c).apply {
            tag = iconKey
            val p = dp(c, sizeDp) / 4
            setPadding(p, p, p, p)
            scaleType = android.widget.ImageView.ScaleType.FIT_CENTER
            layoutParams = LinearLayout.LayoutParams(dp(c, sizeDp), dp(c, sizeDp))
            setIconActive(this, false)
            setOnClickListener { onClick() }
        }

    fun setIconActive(v: android.widget.ImageView, active: Boolean, label: String? = null) {
        val c = v.context
        v.background = GradientDrawable().apply {
            shape = GradientDrawable.OVAL
            setColor(if (active) Palette.accent else Palette.button)
        }
        v.setImageDrawable(Icons.get(c, v.tag as String, if (active) android.graphics.Color.WHITE else Palette.text))
        if (label != null) v.contentDescription = label
    }

    /**
     * Ô vuông bo góc chỉ có icon (kiểu thanh dưới / bảng điều khiển). [label] là mô tả (đọc màn hình,
     * nhấn giữ hiện tên). Bật/tắt bằng [setTileActive].
     */
    fun tile(c: Context, iconKey: String, label: String, sizeDp: Int = 64, onClick: () -> Unit) =
        android.widget.ImageView(c).apply {
            tag = iconKey
            contentDescription = label
            val p = dp(c, sizeDp) * 22 / 100
            setPadding(p, p, p, p)
            scaleType = android.widget.ImageView.ScaleType.FIT_CENTER
            layoutParams = LinearLayout.LayoutParams(dp(c, sizeDp), dp(c, sizeDp))
            setTileActive(this, false)
            setOnClickListener { onClick() }
            setOnLongClickListener { android.widget.Toast.makeText(c, label, android.widget.Toast.LENGTH_SHORT).show(); true }
        }

    fun setTileActive(v: android.widget.ImageView, active: Boolean) {
        val c = v.context
        v.background = pill(c, if (active) Palette.accent else Palette.button, 16)
        v.setImageDrawable(Icons.get(c, v.tag as String, if (active) android.graphics.Color.WHITE else Palette.text))
    }

    /** Ô icon + chữ nhỏ (chế độ lái, nút nhanh). */
    fun iconChip(c: Context, iconKey: String, label: String, onClick: () -> Unit) = vbox(c, 8, Gravity.CENTER).apply {
        tag = iconKey
        addView(android.widget.ImageView(c), LinearLayout.LayoutParams(dp(c, 30), dp(c, 30)))
        addView(text(c, label, 15f).apply { gravity = Gravity.CENTER; maxLines = 1 })
        setChipActive(this, false)
        setOnClickListener { onClick() }
    }

    fun setChipActive(v: LinearLayout, active: Boolean) {
        val c = v.context
        v.background = pill(c, if (active) Palette.accent else Palette.button, 18)
        (v.getChildAt(0) as android.widget.ImageView).setImageDrawable(
            Icons.get(c, v.tag as String, if (active) android.graphics.Color.WHITE else Palette.text))
        (v.getChildAt(1) as TextView).setTextColor(if (active) android.graphics.Color.WHITE else Palette.text)
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
        Fonts.regular(c)?.let { typeface = it }
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

/**
 * Đặt chữ chỉ khi khác: setText luôn yêu cầu đo lại bố cục (và tính lại cỡ chữ tự co),
 * nên các widget cập nhật mỗi giây / mỗi 0,5 giây dùng thuộc tính này thay cho `text`.
 */
var TextView.textIfChanged: CharSequence
    get() = text
    set(v) { if (!android.text.TextUtils.equals(text, v)) text = v }
