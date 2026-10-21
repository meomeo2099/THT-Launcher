package vn.tht.launcher.ui

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import vn.tht.launcher.R

/**
 * Hộp thoại chung theo theme launcher (thay AlertDialog mặc định): thẻ bo góc, tiêu đề, nội dung,
 * ô nhập bo góc, hàng nút cùng kiểu với phần còn lại (nút chính màu nhấn, nút nguy hiểm màu đỏ).
 */
object Dialogs {
    enum class Kind { NORMAL, PRIMARY, DANGER }

    data class Button(val label: String, val kind: Kind = Kind.NORMAL, val onClick: () -> Unit = {})

    /** Nút của [form]: nhận giá trị các ô theo thứ tự. */
    data class FormButton(val label: String, val kind: Kind = Kind.NORMAL, val onClick: (List<String>) -> Unit = {})

    /** Ô nhập. [number] = bàn phím số; [decimal] cho phép số thập phân. */
    data class Field(val label: String, val initial: String = "", val hint: String = "", val number: Boolean = false, val decimal: Boolean = false)

    /** Khung chung: tiêu đề + nội dung + hàng nút. Trả Dialog đã hiện. */
    fun show(c: Context, title: String, content: View?, buttons: List<Button>, widthDp: Int = 720): Dialog {
        lateinit var dialog: Dialog
        val card = Ui.vbox(c, 24).apply {
            background = Ui.pill(c, Palette.withAlpha(if (Palette.night) 0xFF1C2230.toInt() else 0xFFF3F5F9.toInt(), 250), 26)
            addView(Ui.hbox(c).apply {
                addView(Ui.text(c, title, 22f, bold = true), Ui.weight())
                addView(Ui.tile(c, "close", "Đóng", 48) { dialog.dismiss() })
            }, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
                bottomMargin = Ui.dp(c, 12)
            })
            // Phần nội dung cuộn được, giới hạn chiều cao để hàng nút luôn hiện
            val maxH = c.resources.displayMetrics.heightPixels - Ui.dp(c, 250)
            if (content != null) addView(object : ScrollView(c) {
                override fun onMeasure(w: Int, h: Int) =
                    super.onMeasure(w, MeasureSpec.makeMeasureSpec(maxH, MeasureSpec.AT_MOST))
            }.apply { addView(content) },
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
            if (buttons.isNotEmpty()) addView(Ui.hbox(c, Gravity.END or Gravity.CENTER_VERTICAL).apply {
                setPadding(0, Ui.dp(c, 18), 0, 0)
                buttons.forEach { b ->
                    addView(button(c, b) { dialog.dismiss(); b.onClick() },
                        LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, Ui.dp(c, 56)).apply { marginStart = Ui.dp(c, 10) })
                }
            }, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
        }
        dialog = Dialog(c, android.R.style.Theme_Translucent_NoTitleBar).apply {
            setContentView(FrameLayout(c).apply {
                setBackgroundColor(0x88000000.toInt())
                setOnClickListener { dismiss() }
                addView(card.apply { isClickable = true },
                    FrameLayout.LayoutParams(Ui.dp(c, widthDp), ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER))
            })
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            // Hiện bàn phím khi có ô nhập
            window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }
        dialog.show()
        return dialog
    }

    private fun button(c: Context, b: Button, onClick: () -> Unit) = Ui.button(c, b.label, 18f) { onClick() }.apply {
        setPadding(Ui.dp(c, 22), 0, Ui.dp(c, 22), 0)
        when (b.kind) {
            Kind.PRIMARY -> { background = Ui.pill(c, Palette.accent); setTextColor(Color.WHITE) }
            Kind.DANGER -> { background = Ui.pill(c, Palette.danger); setTextColor(Color.WHITE) }
            Kind.NORMAL -> {}
        }
    }

    /** Ô nhập bo góc theo theme. */
    fun input(c: Context, f: Field) = EditText(c).apply {
        setText(f.initial)
        hint = f.hint
        textSize = 20f
        setTextColor(Palette.text)
        setHintTextColor(Palette.textDim)
        background = Ui.pill(c, Palette.button, 14)
        setPadding(Ui.dp(c, 16), Ui.dp(c, 12), Ui.dp(c, 16), Ui.dp(c, 12))
        isSingleLine = true
        inputType = when {
            f.decimal -> InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            f.number -> InputType.TYPE_CLASS_NUMBER
            else -> InputType.TYPE_CLASS_TEXT
        }
    }

    /**
     * Hộp nhập nhiều ô.
     * [note]: dòng ghi chú nhỏ phía trên; [columns]: số ô trên một hàng.
     */
    fun form(
        c: Context, title: String, fields: List<Field>,
        buttons: List<FormButton>, note: String? = null, columns: Int = 1,
    ): Dialog {
        val inputs = fields.map { input(c, it) }
        val box = Ui.vbox(c).apply {
            if (note != null) addView(Ui.text(c, note, 15f, dim = true).apply { setPadding(0, 0, 0, Ui.dp(c, 10)) })
            fields.indices.chunked(columns).forEach { row ->
                addView(Ui.hbox(c, Gravity.TOP).apply {
                    row.forEach { i ->
                        addView(Ui.vbox(c).apply {
                            if (fields[i].label.isNotBlank()) addView(Ui.text(c, fields[i].label, 15f, dim = true).apply {
                                setPadding(Ui.dp(c, 4), Ui.dp(c, 6), 0, Ui.dp(c, 6))
                            })
                            addView(inputs[i], LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
                        }, Ui.weight().apply { if (i != row.last()) marginEnd = Ui.dp(c, 12); bottomMargin = Ui.dp(c, 8) })
                    }
                    repeat(columns - row.size) { addView(View(c), Ui.weight()) }
                }, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
            }
        }
        val d = show(c, title, box, buttons.map { b -> Button(b.label, b.kind) { b.onClick(inputs.map { it.text.toString() }) } })
        inputs.firstOrNull()?.requestFocus()
        return d
    }

    /** Xác nhận: Huỷ + nút thực hiện (đỏ nếu [danger]). */
    fun confirm(c: Context, title: String, message: String, okLabel: String = "Đồng ý", danger: Boolean = false, onOk: () -> Unit): Dialog =
        show(c, title, Ui.text(c, message, 18f), listOf(
            Button("Huỷ"),
            Button(okLabel, if (danger) Kind.DANGER else Kind.PRIMARY, onOk),
        ))

    /** Thông báo có nút Đóng. */
    fun message(c: Context, title: String, message: String): Dialog =
        show(c, title, Ui.text(c, message, 17f).apply { setLineSpacing(0f, 1.25f) }, listOf(Button("Đóng", Kind.PRIMARY)))

    /** Chọn nhiều mục có icon; [checked] là chỉ số đang chọn, trả danh sách chỉ số theo thứ tự chọn. */
    fun multiChoice(
        c: Context, title: String, items: List<Pair<String, Drawable?>>, checked: List<Int>, onDone: (List<Int>) -> Unit,
    ): Dialog {
        val order = checked.toMutableList()
        val rows = items.mapIndexed { i, (label, icon) ->
            val mark = ImageView(c)
            Ui.hbox(c).apply {
                background = c.getDrawable(R.drawable.ripple)
                setPadding(Ui.dp(c, 10), Ui.dp(c, 8), Ui.dp(c, 10), Ui.dp(c, 8))
                addView(ImageView(c).apply { setImageDrawable(icon) }, LinearLayout.LayoutParams(Ui.dp(c, 44), Ui.dp(c, 44)).apply { marginEnd = Ui.dp(c, 14) })
                addView(Ui.text(c, label, 19f).apply { maxLines = 1 }, Ui.weight())
                addView(mark, LinearLayout.LayoutParams(Ui.dp(c, 30), Ui.dp(c, 30)))
                fun paint() = mark.setImageDrawable(Icons.get(c, if (i in order) "check" else "plus",
                    if (i in order) Palette.accent else Palette.textDim))
                paint()
                setOnClickListener { if (i in order) order.remove(i) else order.add(i); paint() }
            }
        }
        val list = Ui.vbox(c).apply { rows.forEach { addView(it) } }
        return show(c, title, list, listOf(Button("Huỷ"), Button("Lưu", Kind.PRIMARY) { onDone(order.toList()) }))
    }
}
