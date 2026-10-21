package vn.tht.launcher.home

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import vn.tht.launcher.ui.Icons
import vn.tht.launcher.ui.textIfChanged
import vn.tht.launcher.ui.Palette
import vn.tht.launcher.ui.Ui
import vn.tht.launcher.vehicle.EcarxController
import vn.tht.launcher.vehicle.EcarxInfo
import vn.tht.launcher.vehicle.EcarxFunctions as F

/**
 * Cụm quạt gió gọn (cạnh cụm điều hoà): icon quạt · − · mức · + · nút hướng gió,
 * hàng chấm mức quạt bên dưới (chạm để đặt). Chạm vào mức để mở cửa sổ điều hoà.
 */
class FanMini(
    c: Context,
    private val ctl: EcarxController,
    private val openPopup: () -> Unit,
    private val toast: (String) -> Unit,
) : LinearLayout(c) {

    private val fanIcon = ImageView(c).apply {
        val p = Ui.dp(c, 7)
        setPadding(p, p, p, p)
        setOnClickListener { openPopup() }
    }
    private val level = Ui.text(c, "–", 22f).apply {
        gravity = Gravity.CENTER
        setOnClickListener { openPopup() }
    }
    private val dir = ImageView(c).apply {
        val p = Ui.dp(c, 10)
        setPadding(p, p, p, p)
        background = Ui.pill(c, Palette.button, 12)
        setOnClickListener { nextDirection() }
    }
    private val dots = FanDots(c) { lvl ->
        ctl.setValue(F.HVAC_FAN_SPEED, F.ZONE_ALL, F.level(F.HVAC_FAN_SPEED, lvl), "Quạt mức $lvl", done = ::report)
    }
    private var blowing: Int? = null

    /** Thứ tự xoay vòng hướng gió và icon tương ứng. */
    private val directions = listOf(1 to "air-upper", 3 to "air-both", 2 to "air-lower", 4 to "front-defrost")

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER
        setPadding(Ui.dp(c, 12), 0, Ui.dp(c, 12), 0)
        addView(Ui.hbox(c, Gravity.CENTER).apply {
            addView(fanIcon, LayoutParams(Ui.dp(c, 44), Ui.dp(c, 44)))
            addView(small("minus") { ctl.fanStep(-1, ::report) }, LayoutParams(Ui.dp(c, 44), Ui.dp(c, 44)).apply { marginStart = Ui.dp(c, 8) })
            addView(level, LayoutParams(Ui.dp(c, 64), LayoutParams.WRAP_CONTENT))
            addView(small("plus") { ctl.fanStep(+1, ::report) }, LayoutParams(Ui.dp(c, 44), Ui.dp(c, 44)))
            addView(dir, LayoutParams(Ui.dp(c, 44), Ui.dp(c, 44)).apply { marginStart = Ui.dp(c, 8) })
        }, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)) // giữ đúng chiều rộng hàng nút, không bị cắt
        addView(dots, LayoutParams(Ui.dp(c, 250), Ui.dp(c, 18)).apply { topMargin = Ui.dp(c, 6) })
        bind(null)
    }

    private fun small(key: String, onClick: () -> Unit) = ImageView(context).apply {
        val p = Ui.dp(context, 10)
        setPadding(p, p, p, p)
        background = Ui.pill(context, Palette.button, 12)
        setImageDrawable(Icons.get(context, key))
        setOnClickListener { onClick() }
    }

    private fun nextDirection() {
        val i = directions.indexOfFirst { it.first == blowing }
        val (n, _) = directions[(i + 1) % directions.size]
        val name = F.BLOWING_MODES.firstOrNull { it.first == n }?.second ?: "$n"
        ctl.setValue(F.HVAC_BLOWING_MODE, F.ZONE_ALL, F.level(F.HVAC_BLOWING_MODE, n), "Hướng gió: $name", done = ::report)
    }

    private var drawn: List<Any?>? = null

    fun bind(e: EcarxInfo?) {
        val on = e?.hvacOn == true
        val lvl = if (on) e?.fanLevel ?: 0 else 0
        val key = listOf(lvl, e?.fanLevel == null, e?.blowing, Palette.night, Palette.accent)
        if (key == drawn) return
        drawn = key
        fanIcon.background = oval(if (lvl > 0) Palette.accent else Palette.button)
        fanIcon.setImageDrawable(Icons.get(context, "fan", if (lvl > 0) Color.WHITE else Palette.text))
        level.textIfChanged = if (e?.fanLevel == null) "–" else if (lvl == 0) "Tắt" else "$lvl/${F.FAN_MAX}"
        dots.level = lvl
        blowing = e?.blowing
        val icon = directions.firstOrNull { it.first == blowing }?.second ?: "air-both"
        dir.setImageDrawable(Icons.get(context, icon))
    }

    private fun report(r: EcarxController.Result) {
        if (!r.ok) toast("✖ " + r.message)
    }
}
