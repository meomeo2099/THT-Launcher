package vn.tht.launcher.home

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextClock
import android.widget.TextView
import vn.tht.launcher.R
import vn.tht.launcher.media.NowPlaying
import vn.tht.launcher.ui.Icons
import vn.tht.launcher.ui.Palette
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
    val prefs: vn.tht.launcher.settings.Prefs
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
    fun openStats()
    fun currentTrip(): vn.tht.launcher.stats.Trip?
    fun todayKm(): Float
    fun openNativeHvac()
    fun openControlPanel(tab: Int = 0)
    fun openClimate()
    fun toast(msg: String)
    fun lastStatus(): vn.tht.launcher.shortcuts.DockBar.Status?
    fun openSystemSettings(action: String)
    fun openNav()
    fun chooseNavApp()
    fun navAppLabel(): String?
    /** Intent mở app bản đồ đã chọn; null nếu chưa có app nào. */
    fun navLaunchIntent(): android.content.Intent?
    /** Được phép thử nhúng bản đồ (có quyền hệ thống + bật trong Cài đặt). */
    fun canEmbedMap(): Boolean
    fun weather(): vn.tht.launcher.system.WeatherNow?
    fun openRadio()
    /** View thanh tương ứng (thanh trên / dưới / dọc) để đặt vào khung widget. */
    fun barView(type: WidgetType): View
    /** Chọn ảnh từ bộ nhớ, lưu (thu nhỏ, giữ nền trong suốt) vào [dest]; [done] báo thành công. */
    fun pickImage(dest: java.io.File, done: (Boolean) -> Unit)
    /** Chạy chức năng của vùng ảnh (xem ImageActions). */
    fun runImageAction(id: String)
    fun radioLabel(): String?
    /** 0..1; null = không đọc được. */
    fun volume(): Float?
    fun setVolume(v: Float)
    fun brightness(): Float?
    fun setBrightness(v: Float)
}

/**
 * Một widget trên màn hình chính; nhận dữ liệu mới qua các hàm on*.
 * Chữ số chính dùng auto-size nên khi người dùng kéo to/nhỏ widget, nội dung co giãn theo.
 */
abstract class HomeWidget(protected val host: WidgetHost) {
    protected val c: Context get() = host.context
    abstract val view: View
    open fun onVehicle(d: VehicleData?) {}
    open fun onEcarx(e: EcarxInfo?) {}
    open fun onMedia(np: NowPlaying?) {}
    /** Gọi mỗi giây (thanh tiến trình nhạc…). */
    open fun onTick() {}

    protected fun base(padDp: Int = 22, gravity: Int = Gravity.START) =
        Ui.vbox(c, padDp, gravity).apply { background = Ui.card(c) }

    /** Dòng icon + chữ (thông tin phụ). */
    protected fun infoRow(icon: String, text: TextView, tint: Int = Palette.textDim) = Ui.hbox(c).apply {
        addView(ImageView(c).apply { setImageDrawable(Icons.get(c, icon, tint)) },
            LinearLayout.LayoutParams(Ui.dp(c, 22), Ui.dp(c, 22)).apply { marginEnd = Ui.dp(c, 8) })
        addView(text)
    }

    protected fun fill(w: Float = 1f) = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, w)
    protected fun matchW() = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

    /** Chữ tự co theo ô chứa (cần ô có kích thước cố định / weight). */
    protected fun auto(t: TextView, minSp: Int, maxSp: Int) = t.apply {
        setAutoSizeTextTypeUniformWithConfiguration(minSp, maxSp, 1, TypedValue.COMPLEX_UNIT_SP)
        maxLines = 1
    }

    protected fun report(r: EcarxController.Result) = host.toast((if (r.ok) "✔ " else "✖ ") + r.message)

    companion object {
        fun create(host: WidgetHost, spec: WidgetSpec): HomeWidget = when (spec.type) {
            WidgetType.CLOCK -> ClockWidget(host)
            WidgetType.CLOCK_TILE -> ClockTile(host)
            WidgetType.SPEED -> SpeedWidget(host)
            WidgetType.MEDIA -> MediaWidget(host, vertical = spec.h > spec.w * 0.85f)
            WidgetType.NAV -> NavWidget(host)
            WidgetType.STAT_FUEL -> StatTile(host, "fuel_l")
            WidgetType.STAT_VOLTAGE -> StatTile(host, "voltage")
            WidgetType.STAT_COOLANT -> StatTile(host, "coolant")
            WidgetType.STAT_RANGE -> StatTile(host, "range")
            WidgetType.BLUETOOTH -> BluetoothTile(host)
            WidgetType.WIFI -> WifiTile(host)
            WidgetType.VOLUME -> SliderWidget(host, brightness = false)
            WidgetType.BRIGHTNESS -> SliderWidget(host, brightness = true)
            WidgetType.RADIO -> RadioWidget(host)
            WidgetType.IMAGE -> ImageWidget(host, spec)
            WidgetType.TOP_BAR, WidgetType.BOTTOM_BAR, WidgetType.SIDE_RAIL, WidgetType.CLIMATE_MINI, WidgetType.FAN_MINI ->
                BarWidget(host, host.barView(spec.type))
            WidgetType.CLIMATE -> ClimateWidget(host)
            WidgetType.FUEL -> FuelWidget(host)
            WidgetType.CAR -> CarWidget(host)
            WidgetType.APP -> AppWidget(host, spec)
            WidgetType.TRIP -> TripWidget(host)
        }
    }
}

/** Đồng hồ lớn đặt thẳng trên nền (không khung), chữ tự co theo kích thước widget. */
class ClockWidget(host: WidgetHost) : HomeWidget(host) {
    /** Ngày luôn hiện tiếng Việt, không phụ thuộc ngôn ngữ hệ thống của đầu xe. */
    private val date = Ui.text(c, "", 40f)
    private val dateFmt = java.text.SimpleDateFormat("EEEE, d 'tháng' M", Locale("vi", "VN"))
    private val outside = Ui.text(c, "", 22f)
    private val outsideRow = infoRow("thermometer", outside, Color.WHITE)

    override val view: View = Ui.vbox(c, 8).apply {
        val fmt = if (host.prefs.clock24h) "HH:mm" else "h:mm a"
        addView(auto(TextClock(c).apply {
            format24Hour = fmt; format12Hour = fmt
            includeFontPadding = false
            setTextColor(Color.WHITE)
            setShadowLayer(12f, 0f, 2f, 0x66000000)
            typeface = android.graphics.Typeface.create("sans-serif-light", android.graphics.Typeface.NORMAL)
        }, 40, 260), fill(3f))
        if (host.prefs.clockShowDate) addView(auto(date.apply {
            setTextColor(Color.WHITE)
            setShadowLayer(10f, 0f, 2f, 0x66000000)
        }, 14, 52), fill(1f))
        outside.setTextColor(Color.WHITE)
        outside.setShadowLayer(8f, 0f, 1f, 0x66000000)
        addView(outsideRow)
    }

    override fun onTick() {
        val s = dateFmt.format(java.util.Date())
        date.text = s.replaceFirstChar { it.uppercase() }
    }

    override fun onVehicle(d: VehicleData?) {
        onTick()
        val t = d?.outsideTempC
        outside.text = t?.let { String.format(Locale.US, "Ngoài trời %.0f°C", it) } ?: ""
        outsideRow.visibility = if (t == null) View.GONE else View.VISIBLE
    }
}

/** Thẻ nhạc gọn: ảnh bìa trái; phải là nguồn, tên bài, tiến trình, điều khiển. */
class MediaWidget(host: WidgetHost, private val vertical: Boolean = false) : HomeWidget(host) {
    private var np: NowPlaying? = null
    private val art = ImageView(c).apply {
        scaleType = ImageView.ScaleType.CENTER_CROP
        background = Ui.pill(c, Palette.button, 18)
        clipToOutline = true
    }
    private val placeholder = ImageView(c).apply {
        setImageDrawable(Icons.get(c, "music", Palette.textDim))
        scaleType = ImageView.ScaleType.FIT_CENTER
    }
    private val source = Ui.text(c, "", 14f, dim = true).apply { maxLines = 1 }
    private val title = auto(Ui.text(c, "", 22f, bold = true), 14, 30).apply { ellipsize = android.text.TextUtils.TruncateAt.END }
    private val artist = Ui.text(c, "", 16f, dim = true).apply { maxLines = 1; ellipsize = android.text.TextUtils.TruncateAt.END }
    private val progress = ProgressBar(c, null, android.R.attr.progressBarStyleHorizontal).apply {
        max = 1000
        progressTintList = android.content.res.ColorStateList.valueOf(Palette.text)
        progressBackgroundTintList = android.content.res.ColorStateList.valueOf(Palette.textDim)
    }
    private val pos = Ui.text(c, "", 12f, dim = true)
    private val dur = Ui.text(c, "", 12f, dim = true)
    private val play = ctrl("play") { host.mediaPlayPause() }

    private fun artBox() = FrameLayout(c).apply {
        addView(art, FrameLayout.LayoutParams(-1, -1))
        addView(placeholder, FrameLayout.LayoutParams(Ui.dp(c, 64), Ui.dp(c, 64), Gravity.CENTER))
    }

    private fun controls() = Ui.hbox(c, Gravity.CENTER).apply {
        addView(ctrl("previous") { host.mediaPrevious() })
        addView(play, LinearLayout.LayoutParams(Ui.dp(c, 64), Ui.dp(c, 56)).apply {
            marginStart = Ui.dp(c, 18); marginEnd = Ui.dp(c, 18)
        })
        addView(ctrl("next") { host.mediaNext() })
    }

    private fun progressBlock() = Ui.vbox(c).apply {
        addView(progress, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Ui.dp(c, 6)))
        addView(Ui.hbox(c).apply { addView(pos, Ui.weight()); addView(dur) }, matchW())
    }

    /** Ngang: ảnh trái, chữ phải. Dọc (widget cao): nguồn, ảnh lớn, tên bài, tiến trình, điều khiển. */
    override val view: View = if (vertical) Ui.vbox(c, 16).apply {
        background = Ui.card(c)
        addView(source, matchW())
        addView(artBox(), LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f).apply { topMargin = Ui.dp(c, 8) })
        addView(title, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Ui.dp(c, 36)).apply { topMargin = Ui.dp(c, 10) })
        addView(artist, matchW())
        addView(progressBlock(), matchW().apply { topMargin = Ui.dp(c, 6) })
        addView(controls(), matchW())
        setOnClickListener { if (!host.hasMediaPermission()) host.requestMediaPermission() }
    } else Ui.hbox(c).apply {
        background = Ui.card(c)
        val p = Ui.dp(c, 14)
        setPadding(p, p, Ui.dp(c, 20), p)
        addView(artBox(), LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 0.42f))
        addView(Ui.vbox(c).apply {
            setPadding(Ui.dp(c, 18), 0, 0, 0)
            addView(source, matchW())
            addView(View(c), fill(0.4f))
            addView(title, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 0.9f))
            addView(artist, matchW())
            addView(View(c), fill(0.4f))
            addView(progressBlock(), matchW())
            addView(controls(), matchW())
        }, LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 0.58f))
        setOnClickListener { if (!host.hasMediaPermission()) host.requestMediaPermission() }
    }

    private fun ctrl(key: String, onClick: () -> Unit) = ImageView(c).apply {
        setImageDrawable(Icons.get(c, key))
        val p = Ui.dp(c, 10)
        setPadding(p, p, p, p)
        layoutParams = LinearLayout.LayoutParams(Ui.dp(c, 56), Ui.dp(c, 56))
        background = c.getDrawable(R.drawable.ripple)
        setOnClickListener { onClick() }
    }

    private fun time(ms: Long) = String.format(Locale.US, "%d:%02d", ms / 60000, ms / 1000 % 60)

    override fun onMedia(np: NowPlaying?) {
        this.np = np
        if (np == null) {
            source.text = ""
            title.text = c.getString(if (host.hasMediaPermission()) R.string.no_media else R.string.grant_media)
            artist.text = ""
            art.setImageDrawable(null)
            placeholder.visibility = View.VISIBLE
            play.setImageDrawable(Icons.get(c, "play"))
        } else {
            source.text = np.source ?: ""
            title.text = np.title ?: ""
            artist.text = np.artist ?: ""
            if (np.art != null) art.setImageBitmap(np.art) else art.setImageDrawable(null)
            placeholder.visibility = if (np.art != null) View.GONE else View.VISIBLE
            play.setImageDrawable(Icons.get(c, if (np.isPlaying) "pause" else "play"))
        }
        onTick()
    }

    override fun onTick() {
        val n = np
        val d = n?.durationMs ?: 0
        val visible = n != null && d > 0
        progress.visibility = if (visible) View.VISIBLE else View.INVISIBLE
        if (!visible) { pos.text = ""; dur.text = ""; return }
        val p = n!!.currentPosition()
        progress.progress = (p * 1000 / d).toInt()
        pos.text = time(p)
        dur.text = time(d)
    }
}

/** Widget điều hoà lớn (tuỳ chọn; thanh dưới đã có cụm điều hoà gọn). */
class ClimateWidget(host: WidgetHost) : HomeWidget(host) {
    private val ctl get() = host.controller
    private val temp = auto(Ui.text(c, "--°", 56f).apply { gravity = Gravity.CENTER; includeFontPadding = false }, 24, 96)
    private val state = Ui.text(c, "", 15f, dim = true)
    private val fan = FanDots(c) { lvl -> ctl.setValue(F.HVAC_FAN_SPEED, F.ZONE_ALL, F.level(F.HVAC_FAN_SPEED, lvl), "Quạt mức $lvl", done = ::report) }
    private val toggles = listOf(
        Triple("power", "Điều hoà") { ctl.toggleHvacPower(::report) },
        Triple("ac", "A/C") { ctl.toggleAc(::report) },
        Triple("auto", "AUTO") { ctl.toggleAuto(::report) },
        Triple("front-defrost", "Sấy kính trước") { ctl.toggleFn(F.HVAC_DEFROST_FRONT, F.ZONE_ALL, "Sấy kính trước", done = ::report) },
        Triple("rear-defrost", "Sấy kính sau") { ctl.toggleFn(F.HVAC_DEFROST_REAR, F.ZONE_ALL, "Sấy kính sau", done = ::report) },
        Triple("seat-heat-left", "Sưởi ghế lái") { ctl.cycleLevel(F.HVAC_SEAT_HEATING, F.ZONE_ROW_1_LEFT, 3, "Sưởi ghế lái", ::report) },
    ).map { (icon, label, action) -> Ui.tile(c, icon, label) { action() } }

    override val view: View = base().apply {
        addView(Ui.hbox(c).apply {
            addView(Ui.header(c, "climate", "Điều hoà"), Ui.weight())
            addView(state)
        }, matchW())
        addView(Ui.hbox(c).apply {
            addView(Ui.tile(c, "minus", "Giảm") { ctl.tempStep(-1, ::report) })
            addView(temp, LinearLayout.LayoutParams(0, Ui.dp(c, 90), 1f))
            addView(Ui.tile(c, "plus", "Tăng") { ctl.tempStep(+1, ::report) })
            temp.setOnClickListener { host.openClimate() }
        }, fill(1.2f))
        addView(fan, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Ui.dp(c, 30)).apply { topMargin = Ui.dp(c, 6) })
        addView(Ui.hbox(c).apply {
            setPadding(0, Ui.dp(c, 12), 0, 0)
            toggles.forEach { addView(it, LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f).apply { marginEnd = Ui.dp(c, 8) }) }
        }, fill(1f))
    }

    override fun onEcarx(e: EcarxInfo?) {
        temp.text = e?.tempC?.let { String.format(Locale.US, "%.1f°", it) } ?: "--°"
        fan.level = e?.fanLevel ?: 0
        val on = listOf(e?.hvacOn, e?.acOn, e?.autoOn, e?.defrostFront, e?.defrostRear, (e?.seatHeatLeft ?: 0) > 0)
        toggles.forEachIndexed { i, t -> Ui.setTileActive(t, on[i] == true) }
        state.text = when {
            e == null -> "Chưa nối xe"
            !ctl.canWrite -> "Chỉ đọc"
            e.hvacOn == false -> "Đang tắt"
            else -> ""
        }
    }
}

/** Hàng chấm mức quạt, chạm vào chấm để đặt mức (1–7). */
class FanDots(c: Context, private val onSet: (Int) -> Unit) : LinearLayout(c) {
    private val dots = List(F.FAN_MAX) { i ->
        View(c).apply { setOnClickListener { onSet(i + 1) } }
    }
    var level = 0
        set(v) { field = v; paint() }

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        dots.forEachIndexed { i, d ->
            addView(d, LayoutParams(0, Ui.dp(c, if (i < 2) 10 else 16), 1f).apply { marginEnd = Ui.dp(c, 6) })
        }
        paint()
    }

    private fun paint() = dots.forEachIndexed { i, d ->
        d.background = Ui.pill(context, if (i < level) Palette.text else Palette.withAlpha(Palette.textDim, 110), 8)
    }
}

class SpeedWidget(host: WidgetHost) : HomeWidget(host) {
    private val speed = auto(Ui.text(c, "--", 96f).apply {
        includeFontPadding = false
        typeface = android.graphics.Typeface.create("sans-serif-medium", android.graphics.Typeface.NORMAL)
    }, 30, 160)
    private val gears = listOf("P", "R", "N", "D").associateWith { g ->
        Ui.text(c, g, 22f, bold = true).apply {
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(0, Ui.dp(c, 44), 1f).apply { marginEnd = Ui.dp(c, 6) }
        }
    }
    private val brake = Ui.text(c, "Phanh tay", 16f)
    private val brakeRow = infoRow("car", brake)

    override val view: View = base().apply {
        addView(Ui.header(c, "sport", "Tốc độ (km/h)"))
        addView(speed, fill(1f))
        addView(Ui.hbox(c).apply { gears.values.forEach { addView(it) } }, matchW())
        addView(brakeRow.apply { setPadding(0, Ui.dp(c, 8), 0, 0) })
    }

    override fun onVehicle(d: VehicleData?) {
        speed.text = d?.speedKmh?.let { String.format(Locale.US, "%.0f", it) } ?: "--"
        val g = d?.gearText
        gears.forEach { (k, v) ->
            val on = k == g || (k == "D" && g == "M")
            v.background = Ui.pill(c, if (on) Palette.accent else Palette.button, 12)
            v.setTextColor(if (on) Color.WHITE else Palette.textDim)
        }
        brakeRow.visibility = if (d?.parkingBrake == true) View.VISIBLE else View.INVISIBLE
    }
}

class FuelWidget(host: WidgetHost) : HomeWidget(host) {
    private val liters = auto(Ui.text(c, "–", 52f).apply { includeFontPadding = false }, 20, 90)
    private val bar = ProgressBar(c, null, android.R.attr.progressBarStyleHorizontal).apply {
        max = 100
        progressBackgroundTintList = android.content.res.ColorStateList.valueOf(Palette.button)
    }
    private val range = Ui.text(c, "", 17f)
    private val odo = Ui.text(c, "", 15f, dim = true)

    override val view: View = base().apply {
        addView(Ui.header(c, "fuel", "Nhiên liệu"))
        addView(liters, fill(1f))
        addView(bar, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Ui.dp(c, 12)))
        addView(infoRow("range", range).apply { setPadding(0, Ui.dp(c, 10), 0, 0) })
        addView(odo)
    }

    override fun onVehicle(d: VehicleData?) {
        val p = d?.fuelPercent ?: d?.batteryPercent
        liters.text = d?.fuelLiters?.let { "${it.toInt()} L" } ?: p?.let { "${it.toInt()}%" } ?: "–"
        liters.setTextColor(if (d?.fuelLow == true) Palette.danger else Palette.text)
        bar.progress = p?.toInt() ?: 0
        bar.progressTintList = android.content.res.ColorStateList.valueOf(if (d?.fuelLow == true) Palette.danger else Palette.accent)
        range.text = d?.rangeKm?.let { String.format(Locale.US, "Còn đi được %.0f km", it) } ?: "Còn đi được –"
        odo.text = d?.odometerKm?.let { String.format(Locale.US, "ODO %,.0f km", it) } ?: ""
    }
}

class CarWidget(host: WidgetHost) : HomeWidget(host) {
    private val modeIcons = mapOf(1 to "eco", 2 to "comfort", 3 to "sport")
    private val modes = F.DRIVE_MODES.associate { (n, name) ->
        n to Ui.tile(c, modeIcons[n] ?: "car", name) { host.controller.setDriveMode(n, ::report) }
    }
    private val ambient = Ui.tile(c, "ambient", "Đèn viền") { host.controller.toggleAmbient(::report) }

    override val view: View = base().apply {
        addView(Ui.header(c, "car", "Xe"))
        addView(Ui.hbox(c).apply {
            setPadding(0, Ui.dp(c, 10), 0, 0)
            modes.values.forEach { addView(it, LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f).apply { marginEnd = Ui.dp(c, 8) }) }
        }, fill(1f))
        addView(Ui.hbox(c).apply {
            setPadding(0, Ui.dp(c, 8), 0, 0)
            addView(ambient, LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f).apply { marginEnd = Ui.dp(c, 8) })
            addView(Ui.tile(c, "music-light", "Màu đèn") { host.openControlPanel(3) },
                LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f).apply { marginEnd = Ui.dp(c, 8) })
            addView(Ui.tile(c, "shortcut", "Thêm") { host.openControlPanel(2) },
                LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f).apply { marginEnd = Ui.dp(c, 8) })
        }, fill(1f))
    }

    override fun onEcarx(e: EcarxInfo?) {
        modes.forEach { (n, v) -> Ui.setTileActive(v, e?.driveMode == n) }
        Ui.setTileActive(ambient, e?.ambientOn == true)
    }
}

class AppWidget(host: WidgetHost, private val spec: WidgetSpec) : HomeWidget(host) {
    private val icon = ImageView(c)
    private val label = Ui.text(c, "", 17f).apply { gravity = Gravity.CENTER; maxLines = 1 }

    override val view: View = base(12, Gravity.CENTER).apply {
        addView(icon, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f))
        addView(label, matchW().apply { topMargin = Ui.dp(c, 6) })
        setOnClickListener {
            if (spec.pkg.isBlank()) host.pickApp { spec.pkg = it; bind(); host.saveLayout() } else host.openApp(spec.pkg)
        }
    }

    init { bind() }

    private fun bind() {
        if (spec.pkg.isBlank()) {
            icon.setImageDrawable(Icons.get(c, "plus"))
            label.text = "Chọn ứng dụng"
        } else {
            icon.setImageDrawable(host.appIcon(spec.pkg))
            label.text = host.appLabel(spec.pkg) ?: spec.pkg
        }
    }
}

class TripWidget(host: WidgetHost) : HomeWidget(host) {
    private val dist = auto(Ui.text(c, "–", 44f).apply { includeFontPadding = false }, 20, 80)
    private val time = Ui.text(c, "", 16f, dim = true)
    private val today = Ui.text(c, "", 16f)

    override val view: View = base().apply {
        addView(Ui.header(c, "trip", "Chuyến đi"))
        addView(dist, fill(1f))
        addView(time)
        addView(infoRow("clock", today).apply { setPadding(0, Ui.dp(c, 8), 0, 0) })
        setOnClickListener { host.openStats() }
    }

    override fun onVehicle(d: VehicleData?) {
        val t = host.currentTrip()
        dist.text = t?.let { String.format(Locale.US, "%.1f km", it.distanceKm) } ?: "Máy tắt"
        time.text = t?.let { "Lái ${it.driveSec / 60} phút · TB ${it.avgSpeed.toInt()} km/h" } ?: ""
        today.text = String.format(Locale.US, "Hôm nay %.1f km", host.todayKm())
    }
}

/** Dùng chung cho GradientDrawable tròn. */
internal fun oval(color: Int) = GradientDrawable().apply { shape = GradientDrawable.OVAL; setColor(color) }

/** Thanh trên / dưới / dọc đặt như widget: di chuyển, đổi cỡ, xoá được. */
class BarWidget(host: WidgetHost, bar: View) : HomeWidget(host) {
    override val view: View = bar.also { (it.parent as? android.view.ViewGroup)?.removeView(it) }
}
