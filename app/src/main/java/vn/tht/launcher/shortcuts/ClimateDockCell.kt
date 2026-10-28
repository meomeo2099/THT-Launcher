package vn.tht.launcher.shortcuts

import android.content.Context
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import vn.tht.launcher.home.FanDots
import vn.tht.launcher.ui.Icons
import vn.tht.launcher.ui.Palette
import vn.tht.launcher.ui.Ui
import vn.tht.launcher.ui.textIfChanged
import java.util.Locale

/** Inline climate controls for the dashboard dock; all writes pass through the host controller. */
internal class ClimateDockCell(
    context: Context,
    private val fan: Boolean,
    private val actions: DockBar.Actions,
) : LinearLayout(context) {
    private val value = Ui.text(context, if (fan) "–" else "--°", 26f).apply {
        gravity = Gravity.CENTER
        setOnClickListener { actions.runDockAction("climate") }
        contentDescription = if (fan) "Mở điều hòa, mức quạt" else "Mở điều hòa, nhiệt độ"
    }
    private val leading = button(if (fan) "fan" else "power", if (fan) "Điều hòa" else "Bật/tắt điều hòa") {
        actions.runDockAction(if (fan) "climate" else "power")
    }
    private val dots = FanDots(context, ramp = fan) { level ->
        actions.runDockAction(if (fan) "fan_set_$level" else "temp_set_${TEMPERATURES[level - 1]}")
    }

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER
        setPadding(Ui.dp(context, 14), Ui.dp(context, 8), Ui.dp(context, 14), Ui.dp(context, 8))
        contentDescription = if (fan) "Điều chỉnh quạt và hướng gió" else "Điều chỉnh nhiệt độ"
        addView(Ui.hbox(context, Gravity.CENTER).apply {
            addView(leading, LayoutParams(Ui.dp(context, 44), Ui.dp(context, 44)))
            addView(button("minus", if (fan) "Giảm quạt" else "Giảm nhiệt độ") {
                actions.runDockAction(if (fan) "fan_down" else "temp_down")
            }, LayoutParams(Ui.dp(context, 44), Ui.dp(context, 44)))
            addView(value, LayoutParams(Ui.dp(context, if (fan) 54 else 84), Ui.dp(context, 48)))
            addView(button("plus", if (fan) "Tăng quạt" else "Tăng nhiệt độ") {
                actions.runDockAction(if (fan) "fan_up" else "temp_up")
            }, LayoutParams(Ui.dp(context, 44), Ui.dp(context, 44)))
            if (fan) addView(button("air-both", "Chọn hướng gió") { actions.runDockAction("climate") },
                LayoutParams(Ui.dp(context, 44), Ui.dp(context, 44)))
        })
        addView(dots, LayoutParams(Ui.dp(context, 230), Ui.dp(context, 20)).apply { topMargin = Ui.dp(context, 8) })
        layoutParams = LayoutParams(Ui.dp(context, if (fan) 290 else 280), LayoutParams.MATCH_PARENT).apply {
            marginStart = Ui.dp(context, 4)
            marginEnd = Ui.dp(context, 8)
        }
    }

    private fun button(icon: String, label: String, click: () -> Unit) = ImageView(context).apply {
        contentDescription = label
        val pad = Ui.dp(context, 10)
        setPadding(pad, pad, pad, pad)
        background = Ui.pill(context, Palette.button, 14)
        setImageDrawable(Icons.get(context, icon))
        setOnClickListener { click() }
    }

    fun bind(status: DockBar.Status) {
        value.textIfChanged = if (fan) status.fanLevel?.toString() ?: "–"
            else status.temperatureC?.let { String.format(Locale.US, "%.1f°", it) } ?: "--°"
        dots.level = if (status.hvacOn == false) 0 else if (fan) status.fanLevel ?: 0
            else status.temperatureC?.let { temperature -> TEMPERATURES.count { it <= temperature + 0.01f } } ?: 0
        leading.setImageDrawable(Icons.get(context, if (fan) "fan" else "power",
            if (status.hvacOn == true) Palette.accent else Palette.text))
    }

    private companion object {
        val TEMPERATURES = listOf(18, 20, 22, 24, 26, 28, 30)
    }
}
