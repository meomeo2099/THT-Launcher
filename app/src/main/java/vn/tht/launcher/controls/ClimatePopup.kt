package vn.tht.launcher.controls

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import vn.tht.launcher.home.FanDots
import vn.tht.launcher.ui.Palette
import vn.tht.launcher.ui.Ui
import vn.tht.launcher.vehicle.EcarxCarSource
import vn.tht.launcher.vehicle.EcarxController
import java.util.Locale
import vn.tht.launcher.vehicle.EcarxFunctions as F

/**
 * Cửa sổ điều hoà dạng popup (không toàn màn hình): Nhiệt độ · Quạt · Hướng gió · Chế độ · Ghế · Sưởi.
 * Ô nào xe không hỗ trợ thì ẩn; trạng thái đọc lại mỗi 1,5 s.
 */
class ClimatePopup(
    private val c: Context,
    private val ecarx: EcarxCarSource,
    private val ctl: EcarxController,
    /** true = cửa sổ overlay đè lên app đang mở (gọi từ dock nổi khi launcher bị che). */
    private val overlay: Boolean = false,
) {
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var dialog: Dialog
    private var showing = false

    /** Một nút gắn với (id, zone) để đọc trạng thái và ẩn khi không hỗ trợ. */
    private class Bound(val id: Int, val zone: Int, val view: View, val bind: (Int?) -> Unit)
    private val bound = mutableListOf<Bound>()

    private val power = Ui.tile(c, "power", "Bật/tắt điều hoà", 56) { ctl.toggleHvacPower(::report) }
    private val tempL = Ui.text(c, "--°", 44f).apply { gravity = Gravity.CENTER }
    private val tempR = Ui.text(c, "--°", 44f).apply { gravity = Gravity.CENTER }
    private lateinit var rightZone: View
    private val fanLabel = Ui.text(c, "Quạt", 15f, dim = true)
    private val fan = FanDots(c) { lvl -> ctl.setValue(F.HVAC_FAN_SPEED, F.ZONE_ALL, F.level(F.HVAC_FAN_SPEED, lvl), "Quạt mức $lvl", done = ::report) }

    private val refresh = object : Runnable {
        override fun run() {
            if (!showing) return
            val list = bound.toList()
            ctl.background({
                Triple(
                    list.associate { (it.id to it.zone) to ecarx.getInt(it.id, it.zone) },
                    ecarx.getFloat(F.HVAC_TEMP, F.ZONE_ROW_1_LEFT) ?: ecarx.getFloat(F.HVAC_TEMP, F.ZONE_ALL),
                    ecarx.getFloat(F.HVAC_TEMP, F.ZONE_ROW_1_RIGHT),
                )
            }) { v ->
                if (v == null || !showing) return@background
                list.forEach { b -> b.bind(v.first[b.id to b.zone]) }
                tempL.text = v.second?.let { String.format(Locale.US, "%.1f°", it) } ?: "--°"
                tempR.text = v.third?.let { String.format(Locale.US, "%.1f°", it) } ?: "--°"
            }
            handler.postDelayed(this, 1500)
        }
    }

    fun show() {
        val card = Ui.vbox(c, 24).apply {
            background = Ui.pill(c, Palette.withAlpha(if (Palette.night) 0xFF1C2230.toInt() else 0xFFF3F5F9.toInt(), 248), 28)
        }
        // Tiêu đề + nguồn + đóng
        card.addView(Ui.hbox(c).apply {
            addView(Ui.text(c, "Điều hoà", 26f, bold = true), Ui.weight())
            if (!ctl.canWrite) addView(Ui.text(c, "Chỉ đọc", 15f, dim = true).apply { setPadding(0, 0, Ui.dp(c, 16), 0) })
            addView(power)
            addView(Ui.tile(c, "close", "Đóng", 56) { dialog.dismiss() }.apply {
                (layoutParams as LinearLayout.LayoutParams).marginStart = Ui.dp(c, 10)
            })
        }, matchW())
        bindToggle(F.HVAC_POWER, F.ZONE_ALL, power)

        // Hàng 1: nhiệt độ | quạt
        card.addView(Ui.hbox(c).apply {
            setPadding(0, Ui.dp(c, 16), 0, 0)
            addView(section("Nhiệt độ", Ui.hbox(c).apply {
                addView(zoneBox("Bên lái", tempL, F.ZONE_ROW_1_LEFT), Ui.weight())
                rightZone = zoneBox("Bên phụ", tempR, F.ZONE_ROW_1_RIGHT)
                addView(rightZone, Ui.weight().apply { marginStart = Ui.dp(c, 12) })
            }), Ui.weight(1.1f).apply { marginEnd = Ui.dp(c, 16) })
            addView(section(null, Ui.vbox(c).apply {
                addView(fanLabel)
                addView(fan, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Ui.dp(c, 44)).apply { topMargin = Ui.dp(c, 10) })
            }), Ui.weight(1f))
        }, matchW())

        // Hàng 2: hướng gió | lấy gió
        card.addView(Ui.hbox(c).apply {
            setPadding(0, Ui.dp(c, 16), 0, 0)
            addView(section("Hướng gió", row(
                choice(F.HVAC_BLOWING_MODE, 1, "air-upper", "Thổi mặt"),
                choice(F.HVAC_BLOWING_MODE, 3, "air-both", "Mặt và chân"),
                choice(F.HVAC_BLOWING_MODE, 2, "air-lower", "Thổi chân"),
                choice(F.HVAC_BLOWING_MODE, 6, "windows", "Chân và kính"),
                choice(F.HVAC_BLOWING_MODE, 4, "front-defrost", "Thổi kính"),
            )), Ui.weight(1.1f).apply { marginEnd = Ui.dp(c, 16) })
            addView(section("Lấy gió", row(
                choice(F.HVAC_CIRCULATION, 1, "recirc", "Gió trong"),
                choice(F.HVAC_CIRCULATION, 2, "air-clean", "Gió ngoài"),
                choice(F.HVAC_CIRCULATION, 3, "auto", "Tự động"),
            )), Ui.weight(1f))
        }, matchW())

        // Hàng 3: chế độ | ghế
        card.addView(Ui.hbox(c).apply {
            setPadding(0, Ui.dp(c, 16), 0, 0)
            addView(section("Chế độ", Ui.hbox(c).apply {
                addView(bigToggle(F.HVAC_AUTO, "AUTO"), Ui.weight().apply { marginEnd = Ui.dp(c, 10) })
                addView(bigToggle(F.HVAC_AC, "A/C"), Ui.weight().apply { marginEnd = Ui.dp(c, 10) })
                addView(toggle(F.HVAC_AC_MAX, F.ZONE_ALL, "climate", "Lạnh tối đa"))
                addView(toggle(F.HVAC_ECO, F.ZONE_ALL, "eco", "Điều hoà ECO").apply { margin(this) })
            }), Ui.weight(1.1f).apply { marginEnd = Ui.dp(c, 16) })
            addView(section("Ghế", row(
                level(F.HVAC_SEAT_HEATING, F.ZONE_ROW_1_LEFT, "seat-heat-left", "Sưởi ghế lái"),
                level(F.HVAC_SEAT_HEATING, F.ZONE_ROW_1_RIGHT, "seat-heat-right", "Sưởi ghế phụ"),
                level(F.HVAC_SEAT_VENTILATION, F.ZONE_ROW_1_LEFT, "seat-vent-left", "Làm mát ghế lái"),
                level(F.HVAC_SEAT_VENTILATION, F.ZONE_ROW_1_RIGHT, "seat-vent-right", "Làm mát ghế phụ"),
            )), Ui.weight(1f))
        }, matchW())

        // Hàng 4: sưởi / sấy
        card.addView(Ui.hbox(c).apply {
            setPadding(0, Ui.dp(c, 16), 0, 0)
            addView(section("Sưởi & sấy kính", row(
                toggle(F.HVAC_DEFROST_REAR, F.ZONE_ALL, "rear-defrost", "Sấy kính sau"),
                toggle(F.HVAC_DEFROST_FRONT, F.ZONE_ALL, "front-defrost", "Sấy kính trước"),
                toggle(F.HVAC_DEFROST_FRONT_MAX, F.ZONE_ALL, "climate", "Sấy kính tối đa"),
                level(F.HVAC_STEERING_WHEEL_HEAT, F.ZONE_ALL, "wheel-heat", "Sưởi vô lăng"),
                toggle(F.HVAC_IONS, F.ZONE_ALL, "ion", "Ion"),
                toggle(F.HVAC_AUTO_DRY, F.ZONE_ALL, "dry", "Tự sấy dàn lạnh"),
            )), Ui.weight(1f))
        }, matchW())

        dialog = Dialog(c, android.R.style.Theme_Translucent_NoTitleBar).apply {
            setContentView(android.widget.FrameLayout(c).apply {
                setBackgroundColor(0x88000000.toInt())
                setOnClickListener { dismiss() }
                addView(card.apply { isClickable = true },
                    android.widget.FrameLayout.LayoutParams(Ui.dp(c, 1240), ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER))
            })
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            if (overlay) window?.setType(android.view.WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY)
            setOnDismissListener { showing = false; handler.removeCallbacks(refresh) }
        }
        dialog.show()
        showing = true
        hideUnsupported()
        handler.post(refresh)
    }

    // ---- Dựng các phần ----

    private fun matchW() = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

    private fun margin(v: View) { (v.layoutParams as? LinearLayout.LayoutParams)?.marginStart = Ui.dp(c, 10) }

    /** Khối có tiêu đề nhỏ, nền nhạt. */
    private fun section(title: String?, content: View) = Ui.vbox(c, 16).apply {
        background = Ui.pill(c, Palette.withAlpha(Palette.button, 140), 20)
        if (title != null) addView(Ui.text(c, title, 15f, dim = true).apply { setPadding(0, 0, 0, Ui.dp(c, 10)) })
        addView(content, matchW())
    }

    private fun row(vararg views: View) = Ui.hbox(c).apply {
        views.forEach { addView(it, LinearLayout.LayoutParams(0, Ui.dp(c, 64), 1f).apply { marginEnd = Ui.dp(c, 10) }) }
    }

    private fun zoneBox(label: String, value: TextView, zone: Int) = Ui.vbox(c, gravity = Gravity.CENTER).apply {
        addView(Ui.text(c, label, 13f, dim = true))
        addView(Ui.hbox(c, Gravity.CENTER).apply {
            addView(Ui.tile(c, "minus", "Giảm nhiệt độ", 52) { ctl.tempStepZone(zoneOrAll(zone), -1, ::report) })
            addView(value, Ui.weight())
            addView(Ui.tile(c, "plus", "Tăng nhiệt độ", 52) { ctl.tempStepZone(zoneOrAll(zone), +1, ::report) })
        }, matchW())
    }

    private var dualZone = true
    private fun zoneOrAll(zone: Int) = if (dualZone) zone else F.ZONE_ALL

    private fun toggle(id: Int, zone: Int, icon: String, label: String): ImageView {
        val v = Ui.tile(c, icon, label) { ctl.toggleFn(id, zone, label, done = ::report) }
        bindToggle(id, zone, v)
        return v
    }

    private fun bindToggle(id: Int, zone: Int, v: ImageView) {
        bound += Bound(id, zone, v) { raw -> Ui.setTileActive(v, raw == F.ON) }
    }

    private fun level(id: Int, zone: Int, icon: String, label: String): ImageView {
        val v = Ui.tile(c, icon, label) { ctl.cycleLevel(id, zone, 3, label, ::report) }
        bound += Bound(id, zone, v) { raw -> Ui.setTileActive(v, (F.levelOf(id, raw) ?: 0) > 0) }
        return v
    }

    private fun choice(id: Int, n: Int, icon: String, label: String): ImageView {
        val v = Ui.tile(c, icon, label) { ctl.setValue(id, F.ZONE_ALL, F.level(id, n), label, done = ::report) }
        bound += Bound(id, F.ZONE_ALL, v) { raw -> Ui.setTileActive(v, F.levelOf(id, raw) == n) }
        return v
    }

    private fun bigToggle(id: Int, label: String): Button {
        val b = Ui.button(c, label, 22f) { ctl.toggleFn(id, F.ZONE_ALL, label, done = ::report) }
        b.minHeight = Ui.dp(c, 64)
        bound += Bound(id, F.ZONE_ALL, b) { raw ->
            Ui.setActive(b, raw == F.ON)
            b.setTextColor(if (raw == F.ON) Color.WHITE else Palette.text)
        }
        return b
    }

    /** Ẩn nút xe không hỗ trợ; nếu không có nhiệt độ 2 vùng thì dùng một vùng. */
    private fun hideUnsupported() {
        val list = bound.toList()
        ctl.background({
            Pair(
                list.associate { (it.id to it.zone) to ctl.supported(it.id, it.zone) },
                ctl.supported(F.HVAC_TEMP, F.ZONE_ROW_1_LEFT),
            )
        }) { r ->
            if (r == null || !showing) return@background
            list.forEach { b -> if (r.first[b.id to b.zone] == false && b.view !== power) b.view.visibility = View.GONE }
            dualZone = r.second
            if (!dualZone) rightZone.visibility = View.GONE
            // Quạt: hiện mức
            bound += Bound(F.HVAC_FAN_SPEED, F.ZONE_ALL, fan) { raw ->
                val lvl = F.levelOf(F.HVAC_FAN_SPEED, raw) ?: 0
                fan.level = lvl
                fanLabel.text = "Quạt · mức $lvl/${F.FAN_MAX} · chạm vào thanh để chỉnh"
            }
        }
    }

    private fun report(r: EcarxController.Result) {
        if (!r.ok) Toast.makeText(c, "✖ " + r.message, Toast.LENGTH_SHORT).show()
        handler.removeCallbacks(refresh)
        handler.post(refresh)
    }
}
