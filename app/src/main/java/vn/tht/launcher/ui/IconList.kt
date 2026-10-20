package vn.tht.launcher.ui

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import vn.tht.launcher.R

/**
 * Hộp chọn có icon theo theme launcher (thay AlertDialog chỉ có chữ):
 * mỗi dòng là icon trong ô bo góc + tên + mô tả nhỏ. Dạng lưới khi [columns] > 1.
 */
object IconList {
    data class Item(
        val label: String,
        /** Tên icon (Icons) hoặc null nếu dùng [drawable]. */
        val icon: String? = null,
        val drawable: Drawable? = null,
        val summary: String? = null,
        /** Tô màu đỏ (xoá…). */
        val danger: Boolean = false,
    )

    fun show(c: Context, title: String, items: List<Item>, columns: Int = 1, onPick: (Int) -> Unit): Dialog {
        lateinit var dialog: Dialog
        val list = Ui.vbox(c)
        items.chunked(columns).forEachIndexed { r, rowItems ->
            val row = Ui.hbox(c)
            rowItems.forEachIndexed { k, item ->
                val index = r * columns + k
                row.addView(cell(c, item) { dialog.dismiss(); onPick(index) },
                    LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f).apply {
                        marginEnd = Ui.dp(c, 8); bottomMargin = Ui.dp(c, 8)
                    })
            }
            repeat(columns - rowItems.size) { row.addView(FrameLayout(c), LinearLayout.LayoutParams(0, 0, 1f)) }
            list.addView(row, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
        }
        val card = Ui.vbox(c, 20).apply {
            background = Ui.pill(c, Palette.withAlpha(if (Palette.night) 0xFF1C2230.toInt() else 0xFFF3F5F9.toInt(), 250), 26)
            addView(Ui.hbox(c).apply {
                addView(Ui.text(c, title, 22f, bold = true), Ui.weight())
                addView(Ui.tile(c, "close", "Đóng", 48) { dialog.dismiss() })
            }, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
                bottomMargin = Ui.dp(c, 12)
            })
            addView(ScrollView(c).apply { addView(list) },
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
        }
        dialog = Dialog(c, android.R.style.Theme_Translucent_NoTitleBar).apply {
            setContentView(FrameLayout(c).apply {
                setBackgroundColor(0x88000000.toInt())
                setOnClickListener { dismiss() }
                val w = if (columns > 1) 1100 else 720
                addView(card.apply { isClickable = true }, FrameLayout.LayoutParams(Ui.dp(c, w), ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER).apply {
                    topMargin = Ui.dp(c, 24); bottomMargin = Ui.dp(c, 24)
                })
            })
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        dialog.show()
        return dialog
    }

    private fun cell(c: Context, item: Item, onClick: () -> Unit) = Ui.hbox(c).apply {
        background = c.getDrawable(R.drawable.ripple)
        setPadding(Ui.dp(c, 10), Ui.dp(c, 8), Ui.dp(c, 10), Ui.dp(c, 8))
        val tint = if (item.danger) Palette.danger else Palette.text
        val d = item.drawable ?: item.icon?.let { Icons.get(c, it, tint) }
        addView(android.widget.FrameLayout(c).apply {
            background = Ui.pill(c, if (item.danger) Palette.withAlpha(Palette.danger, 40) else Palette.button, 14)
            val p = Ui.dp(c, if (item.drawable != null) 6 else 10)
            setPadding(p, p, p, p)
            addView(ImageView(c).apply { setImageDrawable(d) }, FrameLayout.LayoutParams(-1, -1))
        }, LinearLayout.LayoutParams(Ui.dp(c, 52), Ui.dp(c, 52)).apply { marginEnd = Ui.dp(c, 14) })
        addView(Ui.vbox(c).apply {
            addView(Ui.text(c, item.label, 19f).apply { setTextColor(tint); maxLines = 1 })
            if (item.summary != null) addView(Ui.text(c, item.summary, 14f, dim = true).apply { maxLines = 2 })
        }, Ui.weight())
        setOnClickListener { onClick() }
    }
}
