package vn.tht.launcher.home

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import vn.tht.launcher.ui.Icons
import vn.tht.launcher.ui.Palette
import vn.tht.launcher.ui.Ui
import vn.tht.launcher.vehicle.EcarxController
import vn.tht.launcher.vehicle.EcarxInfo
import java.util.Locale
import vn.tht.launcher.vehicle.EcarxFunctions as F

/**
 * Cụm điều hoà gọn ở đầu thanh dưới: nút nguồn tròn · − · nhiệt độ · + và hàng chấm mức quạt.
 * Chạm nhiệt độ để mở cửa sổ điều hoà.
 */
class ClimateMini(
    c: Context,
    private val ctl: EcarxController,
    private val openPopup: () -> Unit,
    private val toast: (String) -> Unit,
) : LinearLayout(c) {

    private val power = ImageView(c).apply {
        val p = Ui.dp(c, 9)
        setPadding(p, p, p, p)
        setOnClickListener { ctl.toggleHvacPower(::report) }
    }
    private val temp = Ui.text(c, "--°", 22f).apply {
        gravity = Gravity.CENTER
        setOnClickListener { openPopup() }
    }
    private val fan = FanDots(c) { lvl ->
        ctl.setValue(F.HVAC_FAN_SPEED, F.ZONE_ALL, F.level(F.HVAC_FAN_SPEED, lvl), "Quạt mức $lvl", done = ::report)
    }

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER
        setPadding(Ui.dp(c, 12), 0, Ui.dp(c, 12), 0)
        addView(Ui.hbox(c, Gravity.CENTER).apply {
            addView(power, LayoutParams(Ui.dp(c, 44), Ui.dp(c, 44)))
            addView(small("minus") { ctl.tempStep(-1, ::report) }, LayoutParams(Ui.dp(c, 44), Ui.dp(c, 44)).apply { marginStart = Ui.dp(c, 10) })
            addView(temp, LayoutParams(Ui.dp(c, 84), LayoutParams.WRAP_CONTENT))
            addView(small("plus") { ctl.tempStep(+1, ::report) }, LayoutParams(Ui.dp(c, 44), Ui.dp(c, 44)))
        })
        addView(fan, LayoutParams(Ui.dp(c, 250), Ui.dp(c, 18)).apply { topMargin = Ui.dp(c, 6) })
        bind(null)
    }

    private fun small(key: String, onClick: () -> Unit) = ImageView(context).apply {
        val p = Ui.dp(context, 10)
        setPadding(p, p, p, p)
        background = Ui.pill(context, Palette.button, 12)
        setImageDrawable(Icons.get(context, key))
        setOnClickListener { onClick() }
    }

    fun bind(e: EcarxInfo?) {
        val on = e?.hvacOn == true
        power.background = oval(if (on) Palette.ok else Palette.button)
        power.setImageDrawable(Icons.get(context, "power", if (on) Color.WHITE else Palette.text))
        temp.text = e?.tempC?.let { String.format(Locale.US, "%.1f°", it) } ?: "--°"
        fan.level = if (on) e?.fanLevel ?: 0 else 0
    }

    private fun report(r: EcarxController.Result) {
        if (!r.ok) toast("✖ " + r.message)
    }
}
