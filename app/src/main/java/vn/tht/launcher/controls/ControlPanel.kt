package vn.tht.launcher.controls

import vn.tht.launcher.ui.Palette
import android.app.AlertDialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import vn.tht.launcher.R
import vn.tht.launcher.vehicle.EcarxController
import vn.tht.launcher.vehicle.EcarxFunctions as F

/**
 * Bảng điều khiển nhanh: điều hoà, chế độ lái, đèn viền.
 * Chỉ hiện những hàng xe báo hỗ trợ; nút bị vô hiệu khi phần ghi đang khoá.
 */
class ControlPanel(private val context: Context, private val controller: EcarxController) {

    private lateinit var status: TextView
    private val dp = context.resources.displayMetrics.density

    fun show() {
        val root = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(px(24), px(16), px(24), px(8))
        }
        val enabled = controller.canWrite
        root.addView(text(
            if (enabled) "Điều khiển xe" else "Chỉ đọc: chưa xác minh APK ký bằng khoá platform của đầu xe",
            if (enabled) 22f else 16f,
        ))

        var rows = 0
        if (controller.supported(F.HVAC_POWER) || controller.supported(F.HVAC_AC) || controller.supported(F.HVAC_AUTO)) {
            root.addView(row("Điều hoà", enabled, buildList {
                if (controller.supported(F.HVAC_POWER)) add("Bật/Tắt" to { controller.toggleHvacPower(::report) })
                if (controller.supported(F.HVAC_AC)) add("A/C" to { controller.toggleAc(::report) })
                if (controller.supported(F.HVAC_AUTO)) add("AUTO" to { controller.toggleAuto(::report) })
            })); rows++
        }
        if (controller.supported(F.HVAC_TEMP, F.ZONE_ROW_1_LEFT) || controller.supported(F.HVAC_TEMP)) {
            root.addView(row("Nhiệt độ", enabled, listOf(
                "−" to { controller.tempStep(-1, ::report) },
                "+" to { controller.tempStep(+1, ::report) },
            ))); rows++
        }
        if (controller.supported(F.HVAC_FAN_SPEED)) {
            root.addView(row("Quạt", enabled, listOf(
                "−" to { controller.fanStep(-1, ::report) },
                "+" to { controller.fanStep(+1, ::report) },
            ))); rows++
        }
        if (controller.supported(F.DRIVE_MODE)) {
            root.addView(row("Chế độ lái", enabled, F.DRIVE_MODES.map { (n, name) ->
                name to { controller.setDriveMode(n, ::report) }
            })); rows++
        }
        if (controller.supported(F.AMBIENT_SWITCH)) {
            root.addView(row("Đèn viền", enabled, listOf("Bật/Tắt" to { controller.toggleAmbient(::report) }))); rows++
        }
        if (controller.supported(F.AMBIENT_THEME_COLOR)) {
            val colors = LinearLayout(context).apply { orientation = LinearLayout.HORIZONTAL }
            colors.addView(label("Màu đèn"))
            F.AMBIENT_COLORS.forEach { (n, name, color) ->
                colors.addView(Button(context).apply {
                    contentDescription = name
                    backgroundTintList = ColorStateList.valueOf(color)
                    isEnabled = enabled
                    setOnClickListener { controller.setAmbientColor(n, ::report) }
                }, LinearLayout.LayoutParams(px(56), px(56)).apply { marginEnd = px(6) })
            }
            root.addView(colors); rows++
        }
        if (rows == 0) root.addView(text("Xe chưa báo hỗ trợ chức năng điều khiển nào (hoặc chưa nối ECARX).", 16f))

        status = text("", 16f).apply { setPadding(0, px(12), 0, 0) }
        root.addView(status)

        AlertDialog.Builder(context)
            .setView(ScrollView(context).apply {
                setBackgroundColor(Palette.card)
                addView(root)
            })
            .setPositiveButton("Đóng", null)
            .show()
    }

    private fun report(r: EcarxController.Result) {
        if (!::status.isInitialized) return
        status.text = (if (r.ok) "✔ " else "✖ ") + r.message
        status.setTextColor(if (r.ok) Color.parseColor("#43A047") else Color.parseColor("#E53935"))
    }

    private fun row(title: String, enabled: Boolean, actions: List<Pair<String, () -> Unit>>): View =
        LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(0, px(6), 0, px(6))
            addView(label(title))
            actions.forEach { (name, action) ->
                addView(Button(context).apply {
                    text = name
                    textSize = 18f
                    isEnabled = enabled
                    setOnClickListener { action() }
                }, LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, px(64)).apply {
                    marginEnd = px(8)
                })
            }
        }

    private fun label(s: String) = text(s, 18f).apply {
        layoutParams = LinearLayout.LayoutParams(px(140), LinearLayout.LayoutParams.WRAP_CONTENT)
    }

    private fun text(s: String, size: Float) = TextView(context).apply {
        text = s
        textSize = size
        setTextColor(Palette.text)
    }

    private fun px(v: Int) = (v * dp).toInt()
}
