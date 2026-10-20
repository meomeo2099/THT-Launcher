package vn.tht.launcher.home

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.Gravity
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextClock
import android.widget.TextView
import vn.tht.launcher.R
import vn.tht.launcher.media.NowPlaying
import vn.tht.launcher.ui.Ui
import vn.tht.launcher.vehicle.EcarxController
import vn.tht.launcher.vehicle.EcarxInfo
import vn.tht.launcher.vehicle.VehicleData
import java.util.Locale
import vn.tht.launcher.vehicle.EcarxFunctions as F

/** Những gì widget cần từ màn hình chính. */
interface WidgetHost {
    val context: Context
    val controller: EcarxController
    fun mediaPlayPause()
    fun mediaNext()
    fun mediaPrevious()
    fun hasMediaPermission(): Boolean
    fun requestMediaPermission()
    fun openApp(pkg: String)
    fun appIcon(pkg: String): Drawable?
    fun appLabel(pkg: String): String?
    fun pickApp(onPicked: (String) -> Unit)
    fun saveLayout()
    fun openNativeHvac()
    fun openControlPanel()
    fun toast(msg: String)
}

/** Một widget trên màn hình chính; nhận dữ liệu mới qua các hàm on*. */
abstract class HomeWidget(protected val host: WidgetHost) {
    protected val c: Context get() = host.context
    abstract val view: View
    open fun onVehicle(d: VehicleData?) {}
    open fun onEcarx(e: EcarxInfo?) {}
    open fun onMedia(np: NowPlaying?) {}

    protected fun base(padDp: Int = 20, gravity: Int = Gravity.START) =
        Ui.vbox(c, padDp, gravity).apply { background = Ui.card(c) }

    protected fun report(r: EcarxController.Result) = host.toast((if (r.ok) "✔ " else "✖ ") + r.message)

    companion object {
        fun create(host: WidgetHost, spec: WidgetSpec): HomeWidget = when (spec.type) {
            WidgetType.CLOCK -> ClockWidget(host)
            WidgetType.SPEED -> SpeedWidget(host)
            WidgetType.MEDIA -> MediaWidget(host)
            WidgetType.CLIMATE -> ClimateWidget(host)
            WidgetType.FUEL -> FuelWidget(host)
            WidgetType.CAR -> CarWidget(host)
            WidgetType.APP -> AppWidget(host, spec)
        }
    }
}

class ClockWidget(host: WidgetHost) : HomeWidget(host) {
    private val outside = Ui.text(c, "", 20f, dim = true)
    override val view: View = base().apply {
        addView(TextClock(c).apply {
            format24Hour = "HH:mm"; format12Hour = "HH:mm"
            textSize = 72f
            setTextColor(c.getColor(R.color.text))
            typeface = android.graphics.Typeface.create("sans-serif-light", android.graphics.Typeface.NORMAL)
        })
        addView(TextClock(c).apply {
            format24Hour = "EEEE, dd/MM/yyyy"; format12Hour = "EEEE, dd/MM/yyyy"
            textSize = 20f
            setTextColor(c.getColor(R.color.text_dim))
        })
        addView(outside)
    }

    override fun onVehicle(d: VehicleData?) {
        outside.text = d?.outsideTempC?.let { String.format(Locale.US, "Ngoài trời %.0f°C", it) } ?: ""
    }
}

class SpeedWidget(host: WidgetHost) : HomeWidget(host) {
    private val speed = Ui.text(c, "--", 110f).apply {
        typeface = android.graphics.Typeface.create("sans-serif-medium", android.graphics.Typeface.NORMAL)
    }
    private val gears = listOf("P", "R", "N", "D").associateWith { g ->
        Ui.text(c, g, 26f, bold = true).apply {
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(Ui.dp(c, 56), Ui.dp(c, 48)).apply { marginEnd = Ui.dp(c, 8) }
        }
    }
    private val info = Ui.text(c, "", 18f, dim = true)

    override val view: View = base().apply {
        addView(speed)
        addView(Ui.text(c, "km/h", 20f, dim = true))
        addView(Ui.spacer(c), LinearLayout.LayoutParams(0, 0, 1f))
        addView(Ui.hbox(c).apply { gears.values.forEach { addView(it) } })
        addView(info.apply { setPadding(0, Ui.dp(c, 8), 0, 0) })
    }

    override fun onVehicle(d: VehicleData?) {
        speed.text = d?.speedKmh?.let { String.format(Locale.US, "%.0f", it) } ?: "--"
        val g = d?.gearText
        gears.forEach { (k, v) ->
            val on = k == g || (k == "D" && g == "M")
            v.background = if (on) Ui.pill(c, c.getColor(R.color.accent), 12) else null
            v.setTextColor(c.getColor(if (on) R.color.text else R.color.text_dim))
        }
        info.text = when {
            d == null -> c.getString(R.string.no_vehicle)
            d.parkingBrake == true -> "Phanh tay: BẬT"
            else -> ""
        }
    }
}

class MediaWidget(host: WidgetHost) : HomeWidget(host) {
    private val art = ImageView(c).apply {
        scaleType = ImageView.ScaleType.CENTER_CROP
        background = Ui.pill(c, c.getColor(R.color.bg), 16)
        clipToOutline = true
    }
    private val title = Ui.text(c, "", 24f).apply { maxLines = 1; ellipsize = android.text.TextUtils.TruncateAt.END; gravity = Gravity.CENTER }
    private val artist = Ui.text(c, "", 18f, dim = true).apply { maxLines = 1; ellipsize = android.text.TextUtils.TruncateAt.END; gravity = Gravity.CENTER }
    private val play = icon(android.R.drawable.ic_media_play) { host.mediaPlayPause() }

    override val view: View = base(gravity = Gravity.CENTER_HORIZONTAL).apply {
        addView(art, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f))
        addView(title, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply { topMargin = Ui.dp(c, 12) })
        addView(artist, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
        addView(Ui.hbox(c, Gravity.CENTER).apply {
            addView(icon(android.R.drawable.ic_media_previous) { host.mediaPrevious() })
            addView(play)
            addView(icon(android.R.drawable.ic_media_next) { host.mediaNext() })
        })
        setOnClickListener { if (!host.hasMediaPermission()) host.requestMediaPermission() }
    }

    private fun icon(res: Int, onClick: () -> Unit) = ImageButton(c).apply {
        setImageResource(res)
        background = null
        layoutParams = LinearLayout.LayoutParams(Ui.dp(c, 88), Ui.dp(c, 72))
        setOnClickListener { onClick() }
    }

    override fun onMedia(np: NowPlaying?) {
        if (np == null) {
            title.text = c.getString(if (host.hasMediaPermission()) R.string.no_media else R.string.grant_media)
            artist.text = ""
            art.setImageDrawable(null)
            play.setImageResource(android.R.drawable.ic_media_play)
            return
        }
        title.text = np.title ?: ""
        artist.text = np.artist ?: ""
        if (np.art != null) art.setImageBitmap(np.art) else art.setImageDrawable(null)
        play.setImageResource(if (np.isPlaying) android.R.drawable.ic_media_pause else android.R.drawable.ic_media_play)
    }
}

class ClimateWidget(host: WidgetHost) : HomeWidget(host) {
    private val temp = Ui.text(c, "--°", 64f)
    private val state = Ui.text(c, "", 18f, dim = true)
    private val power = Ui.button(c, "Bật/Tắt") { host.controller.toggleHvacPower(::report) }
    private val ac = Ui.button(c, "A/C") { host.controller.toggleAc(::report) }
    private val auto = Ui.button(c, "AUTO") { host.controller.toggleAuto(::report) }
    private val fan = Ui.text(c, "Quạt –", 20f).apply { gravity = Gravity.CENTER }

    override val view: View = base().apply {
        addView(Ui.hbox(c).apply {
            addView(Ui.vbox(c).apply {
                addView(Ui.text(c, "Điều hoà", 20f, dim = true))
                addView(Ui.hbox(c).apply {
                    addView(Ui.button(c, "−", 28f) { host.controller.tempStep(-1, ::report) })
                    addView(temp.apply { setPadding(Ui.dp(c, 16), 0, Ui.dp(c, 16), 0) })
                    addView(Ui.button(c, "+", 28f) { host.controller.tempStep(+1, ::report) })
                })
                addView(state)
            }, Ui.weight())
            addView(Ui.vbox(c).apply {
                addView(Ui.hbox(c).apply {
                    addView(power, Ui.wrap(8, c)); addView(ac, Ui.wrap(8, c)); addView(auto)
                })
                addView(Ui.hbox(c).apply {
                    setPadding(0, Ui.dp(c, 12), 0, 0)
                    addView(Ui.button(c, "−") { host.controller.fanStep(-1, ::report) })
                    addView(fan, LinearLayout.LayoutParams(Ui.dp(c, 110), LinearLayout.LayoutParams.WRAP_CONTENT))
                    addView(Ui.button(c, "+") { host.controller.fanStep(+1, ::report) })
                })
            })
        })
        addView(Ui.spacer(c), LinearLayout.LayoutParams(0, 0, 1f))
        addView(Ui.hbox(c).apply {
            addView(Ui.button(c, "Màn hình điều hoà gốc", 16f) { host.openNativeHvac() }, Ui.wrap(8, c))
            addView(Ui.button(c, "Thêm…", 16f) { host.openControlPanel() })
        })
    }

    override fun onEcarx(e: EcarxInfo?) {
        temp.text = e?.tempC?.let { String.format(Locale.US, "%.1f°", it) } ?: "--°"
        Ui.setActive(power, e?.hvacOn == true)
        Ui.setActive(ac, e?.acOn == true)
        fan.text = "Quạt " + (e?.fanLevel?.takeIf { it > 0 }?.toString() ?: "–")
        state.text = when {
            e == null -> "Chưa nối ECARX"
            !host.controller.canWrite -> "Chỉ đọc"
            e.hvacOn == false -> "Đang tắt"
            else -> ""
        }
    }
}

class FuelWidget(host: WidgetHost) : HomeWidget(host) {
    private val pct = Ui.text(c, "--%", 48f)
    private val bar = ProgressBar(c, null, android.R.attr.progressBarStyleHorizontal).apply {
        max = 100
        progressTintList = android.content.res.ColorStateList.valueOf(c.getColor(R.color.accent))
    }
    private val range = Ui.text(c, "", 20f)
    private val odo = Ui.text(c, "", 16f, dim = true)

    override val view: View = base().apply {
        addView(Ui.text(c, "Nhiên liệu", 20f, dim = true))
        addView(pct)
        addView(bar, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Ui.dp(c, 12)))
        addView(Ui.spacer(c), LinearLayout.LayoutParams(0, 0, 1f))
        addView(range)
        addView(odo)
    }

    override fun onVehicle(d: VehicleData?) {
        val p = d?.fuelPercent ?: d?.batteryPercent
        pct.text = p?.let { "${it.toInt()}%" } ?: "--%"
        pct.setTextColor(c.getColor(if (d?.fuelLow == true) R.color.danger else R.color.text))
        bar.progress = p?.toInt() ?: 0
        range.text = d?.rangeKm?.let { String.format(Locale.US, "Còn đi được %.0f km", it) } ?: ""
        odo.text = d?.odometerKm?.let { String.format(Locale.US, "ODO %,.0f km", it) } ?: ""
    }
}

class CarWidget(host: WidgetHost) : HomeWidget(host) {
    private val modes = F.DRIVE_MODES.associate { (n, name) ->
        n to Ui.button(c, name, 18f) { host.controller.setDriveMode(n, ::report) }
    }
    private val ambient = Ui.button(c, "Đèn viền") { host.controller.toggleAmbient(::report) }

    override val view: View = base().apply {
        addView(Ui.text(c, "Chế độ lái", 20f, dim = true))
        addView(Ui.hbox(c).apply { modes.values.forEach { addView(it, Ui.weight().apply { marginEnd = Ui.dp(c, 6) }) } })
        addView(Ui.spacer(c), LinearLayout.LayoutParams(0, 0, 1f))
        addView(Ui.text(c, "Đèn viền", 20f, dim = true))
        addView(Ui.hbox(c).apply {
            addView(ambient, Ui.weight().apply { marginEnd = Ui.dp(c, 6) })
            addView(Ui.button(c, "Màu…") { host.openControlPanel() }, Ui.weight())
        })
    }

    override fun onEcarx(e: EcarxInfo?) {
        modes.forEach { (n, b) -> Ui.setActive(b, e?.driveMode == n) }
        Ui.setActive(ambient, e?.ambientOn == true)
    }
}

class AppWidget(host: WidgetHost, private val spec: WidgetSpec) : HomeWidget(host) {
    private val icon = ImageView(c)
    private val label = Ui.text(c, "", 18f).apply { gravity = Gravity.CENTER; maxLines = 1 }

    override val view: View = base(12, Gravity.CENTER).apply {
        addView(icon, LinearLayout.LayoutParams(Ui.dp(c, 96), Ui.dp(c, 96)))
        addView(label, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
        setOnClickListener {
            if (spec.pkg.isBlank()) host.pickApp { spec.pkg = it; bind(); host.saveLayout() } else host.openApp(spec.pkg)
        }
    }

    init { bind() }

    private fun bind() {
        if (spec.pkg.isBlank()) {
            icon.setImageResource(android.R.drawable.ic_menu_add)
            label.text = "Chọn ứng dụng"
        } else {
            icon.setImageDrawable(host.appIcon(spec.pkg))
            label.text = host.appLabel(spec.pkg) ?: spec.pkg
        }
    }
}
