package vn.tht.launcher.home

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import vn.tht.launcher.ui.Icons
import vn.tht.launcher.ui.textIfChanged
import vn.tht.launcher.ui.Palette
import vn.tht.launcher.ui.Ui
import vn.tht.launcher.vehicle.VehicleData
import java.util.Locale

/**
 * Ô nhỏ kiểu thẻ: icon tròn màu + tiêu đề + giá trị lớn + vạch trạng thái dưới.
 * Dùng cho xăng, điện áp, nhiệt độ máy, Bluetooth, Wi-Fi.
 */
abstract class SmallTile(host: WidgetHost, iconKey: String, title: String) : HomeWidget(host) {
    protected val badge = ImageView(c).apply {
        val p = Ui.dp(c, 6)
        setPadding(p, p, p, p)
        background = oval(Palette.accent)
        setImageDrawable(Icons.get(c, iconKey, Color.WHITE))
    }
    protected val titleView = Ui.text(c, title, 14f, dim = true).apply { maxLines = 1 }
    protected val value = auto(Ui.text(c, "–", 28f, bold = true).apply { includeFontPadding = false }, 14, 40)
    protected val line = View(c)

    override val view: View = base(14).apply {
        addView(Ui.hbox(c).apply {
            addView(badge, LinearLayout.LayoutParams(Ui.dp(c, 30), Ui.dp(c, 30)).apply { marginEnd = Ui.dp(c, 8) })
            addView(titleView, Ui.weight())
            addView(ImageView(c).apply { setImageDrawable(Icons.get(c, "chevron-right", Palette.textDim)) },
                LinearLayout.LayoutParams(Ui.dp(c, 20), Ui.dp(c, 20)))
        }, matchW())
        addView(value, fill(1f))
        addView(line, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Ui.dp(c, 4)))
        setOnClickListener { onTap() }
    }

    open fun onTap() {}

    /** Đặt trạng thái: [ok] = xanh lá, false = đỏ, null = xám. [frac] = độ dài vạch 0..1. */
    private var lastState: Triple<String, Boolean?, Int>? = null

    protected fun state(text: String, ok: Boolean?, frac: Float = 1f) {
        val key = Triple(text, ok, (frac.coerceIn(0f, 1f) * 100).toInt())
        if (key == lastState) return
        lastState = key
        value.textIfChanged = text
        val color = when (ok) { true -> Palette.ok; false -> Palette.danger; null -> Palette.textDim }
        badge.background = oval(if (ok == null) Palette.button else color)
        badge.drawable?.setTint(if (ok == null) Palette.text else Color.WHITE)
        line.background = android.graphics.drawable.LayerDrawable(arrayOf(
            Ui.pill(c, Palette.withAlpha(Palette.textDim, 70), 2),
            android.graphics.drawable.ClipDrawable(Ui.pill(c, color, 2), Gravity.START, android.graphics.drawable.ClipDrawable.HORIZONTAL)
                .apply { level = (frac.coerceIn(0f, 1f) * 10000).toInt() },
        ))
    }
}

/** Ô số liệu xe: [metric] = "fuel_l" | "voltage" | "coolant" | "range". */
class StatTile(host: WidgetHost, private val metric: String) : SmallTile(
    host,
    when (metric) { "voltage" -> "battery"; "coolant" -> "engine-temp"; "range" -> "range"; else -> "fuel" },
    when (metric) { "voltage" -> "Ắc quy"; "coolant" -> "Nhiệt độ máy"; "range" -> "Còn đi được"; else -> "Xăng" },
) {
    private var last: VehicleData? = null
    override fun onVehicle(d: VehicleData?) { last = d; onTick() }
    override fun onTap() = host.openStats()

    override fun onTick() {
        val d = last
        when (metric) {
            "voltage" -> {
                val v = host.lastStatus()?.voltage
                state(v?.let { String.format(Locale.US, "%.1f V", it) } ?: "– V", v?.let { it >= 12.2f }, ((v ?: 0f) - 11f) / 4f)
            }
            "coolant" -> {
                val t = d?.coolantTempC
                state(t?.let { "${it.toInt()}°C" } ?: "–", t?.let { it < 105f }, (t ?: 0f) / 120f)
            }
            "range" -> {
                val r = d?.rangeKm
                state(r?.let { "${it.toInt()} km" } ?: "–", r?.let { it > 60f }, (r ?: 0f) / 600f)
            }
            else -> {
                val l = d?.fuelLiters
                val p = d?.fuelPercent
                state(l?.let { "${it.toInt()} L" } ?: p?.let { "${it.toInt()}%" } ?: "–",
                    if (d?.fuelLow == true) false else if (p != null || l != null) true else null, (p ?: 0f) / 100f)
            }
        }
    }
}

class BluetoothTile(host: WidgetHost) : SmallTile(host, "bluetooth", "Bluetooth") {
    override fun onTap() = host.openSystemSettings(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS)
    @Suppress("MissingPermission", "DEPRECATION")
    override fun onTick() {
        val on = runCatching { android.bluetooth.BluetoothAdapter.getDefaultAdapter()?.isEnabled }.getOrNull()
        state(when (on) { true -> "Bật"; false -> "Tắt"; null -> "–" }, on)
    }
}

class WifiTile(host: WidgetHost) : SmallTile(host, "wifi", "Wi-Fi") {
    override fun onTap() = host.openSystemSettings(android.provider.Settings.ACTION_WIFI_SETTINGS)
    override fun onTick() {
        val on = host.lastStatus()?.wifi
        state(when (on) { true -> "Đã kết nối"; false -> "Chưa nối"; null -> "–" }, on)
    }
}

/** Ô đồng hồ gọn: giờ, ngày tiếng Việt, thời tiết (Open-Meteo) hoặc nhiệt độ ngoài xe. */
class ClockTile(host: WidgetHost) : HomeWidget(host) {
    private val time = auto(Ui.text(c, "", 48f).apply { includeFontPadding = false }, 20, 120)
    private val date = auto(Ui.text(c, "", 18f, dim = true), 10, 28)
    private val wIcon = ImageView(c)
    private val wTemp = auto(Ui.text(c, "", 26f, bold = true).apply { gravity = Gravity.END }, 14, 40)
    private val wText = Ui.text(c, "", 14f, dim = true).apply { gravity = Gravity.END; maxLines = 1 }
    private var outsideC: Float? = null
    private var fmt24: Boolean? = null
    private var timeFmtCache: java.text.SimpleDateFormat? = null
    private val timeFmt: java.text.SimpleDateFormat
        get() {
            val h24 = host.prefs.clock24h
            if (h24 != fmt24) { fmt24 = h24; timeFmtCache = java.text.SimpleDateFormat(if (h24) "HH:mm" else "h:mm a", Locale.US) }
            return timeFmtCache!!
        }
    private var wIconKey: String? = null
    private val dateFmt = java.text.SimpleDateFormat("EEEE, d 'tháng' M", Locale("vi", "VN"))

    override val view: View = base(18).apply {
        addView(Ui.hbox(c).apply {
            addView(time, LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.6f))
            addView(Ui.vbox(c, gravity = Gravity.END or Gravity.CENTER_VERTICAL).apply {
                addView(wIcon, LinearLayout.LayoutParams(Ui.dp(c, 52), Ui.dp(c, 52)).apply { gravity = Gravity.END })
                addView(wTemp, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Ui.dp(c, 36)))
                addView(wText, matchW())
            }, LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f))
        }, fill(2.2f))
        addView(date, fill(0.8f))
    }

    override fun onTick() {
        val now = java.util.Date()
        time.textIfChanged = timeFmt.format(now)
        date.textIfChanged = dateFmt.format(now).replaceFirstChar { it.uppercase() }
        val w = host.weather()
        if (w != null) {
            if (wIconKey != w.icon) { wIconKey = w.icon; wIcon.setImageDrawable(Icons.get(c, w.icon)) }
            wTemp.textIfChanged = String.format(Locale.US, "%+.0f°", w.tempC)
            wText.textIfChanged = w.text
        } else {
            val key = if (outsideC != null) "thermometer" else ""
            if (wIconKey != key) { wIconKey = key; wIcon.setImageDrawable(if (key.isEmpty()) null else Icons.get(c, key, Palette.textDim)) }
            wTemp.textIfChanged = outsideC?.let { String.format(Locale.US, "%+.0f°", it) } ?: ""
            wText.textIfChanged = if (outsideC != null) "Ngoài xe" else ""
        }
    }

    override fun onVehicle(d: VehicleData?) {
        outsideC = d?.outsideTempC
        onTick()
    }
}

/**
 * Ô dẫn đường. Có quyền hệ thống (bản ký platform) và bật trong Cài đặt: nhúng app bản đồ ngay trong ô
 * (nút ⤢ mở toàn màn hình). Không thì là nút lớn mở app bản đồ đã chọn (nhấn giữ để chọn app).
 */
class NavWidget(host: WidgetHost) : HomeWidget(host) {
    private val label = Ui.text(c, "", 16f, dim = true).apply { gravity = Gravity.CENTER }
    private val fallback = Ui.vbox(c, 18, Gravity.CENTER).apply {
        addView(Ui.header(c, "navigation", "Dẫn đường"), matchW())
        addView(ImageView(c).apply {
            setImageDrawable(Icons.get(c, "map", Palette.accent))
            scaleType = ImageView.ScaleType.FIT_CENTER
            val p = Ui.dp(c, 24)
            setPadding(p, p, p, p)
        }, fill(1f))
        addView(Ui.text(c, "Chạm để mở bản đồ", 20f).apply { gravity = Gravity.CENTER }, matchW())
        addView(label, matchW())
    }
    private var embedded: EmbeddedMap? = null
    private var expand: View? = null
    /** Đã đóng ô nhúng để mở toàn màn hình: nhúng lại khi launcher hiện lại. */
    private var reembed = false

    override val view: View = android.widget.FrameLayout(c).apply {
        background = cardBackground(this)
        clipToOutline = true
        addView(fallback, android.widget.FrameLayout.LayoutParams(-1, -1))
        setOnClickListener { host.openNav() }
        setOnLongClickListener { host.chooseNavApp(); true }
    }

    init { tryEmbed() }

    private fun tryEmbed() {
        val intent = host.navLaunchIntent() ?: return
        if (!host.canEmbedMap()) return
        val frame = view as android.widget.FrameLayout
        val map = EmbeddedMap(c) { why ->
            // Không nhúng được: quay về nút mở bản đồ
            dropEmbed()
            host.toast("Không nhúng được bản đồ ($why); chạm ô để mở toàn màn hình")
        }
        if (!map.create()) return
        embedded = map
        fallback.visibility = View.GONE
        frame.addView(map, android.widget.FrameLayout.LayoutParams(-1, -1))
        // Nút mở toàn màn hình ở góc
        expand = Ui.tile(c, "expand", "Mở toàn màn hình", 48) { openFull() }
        frame.addView(expand,
            android.widget.FrameLayout.LayoutParams(Ui.dp(c, 48), Ui.dp(c, 48), Gravity.TOP or Gravity.END).apply {
                topMargin = Ui.dp(c, 10); marginEnd = Ui.dp(c, 10)
            })
        map.start(intent)
    }

    private fun dropEmbed() {
        val frame = view as android.widget.FrameLayout
        embedded?.let { it.release(); frame.removeView(it) }
        expand?.let { frame.removeView(it) }
        embedded = null
        expand = null
        fallback.visibility = View.VISIBLE
    }

    /**
     * Bản đồ đang chạy trong màn hình ảo của ô. Mở thẳng thì hệ thống kéo nguyên task đó sang màn hình
     * chính, giữ kích thước của ô (bản đồ chỉ chiếm một phần, phần còn lại trống). Nên đóng ô trước
     * (huỷ màn hình ảo cũng đóng task bên trong), đợi một nhịp rồi mở bản mới toàn màn hình.
     */
    private fun openFull() {
        dropEmbed()
        reembed = true
        view.postDelayed({ host.openNav() }, 400)
    }

    override fun onTick() {
        label.textIfChanged = host.navAppLabel() ?: "Nhấn giữ để chọn app bản đồ"
        // onTick chỉ chạy khi launcher đang hiện: người dùng đã quay về từ bản đồ
        if (reembed && view.hasWindowFocus()) { reembed = false; tryEmbed() }
    }
    override fun onVehicle(d: VehicleData?) = onTick()
}

/** Thanh trượt dọc: âm lượng nhạc hoặc độ sáng màn hình, đặt như widget. */
class SliderWidget(host: WidgetHost, private val brightness: Boolean) : HomeWidget(host) {
    private val slider = VerticalSlider(c, if (brightness) "sun" else "speaker",
        get = { if (brightness) host.brightness() else host.volume() },
        set = { if (brightness) host.setBrightness(it) else host.setVolume(it) })
    override val view: View = slider
    private var shown: Float? = null

    /** Âm lượng/độ sáng có thể đổi từ nút vô lăng: vẽ lại chỉ khi giá trị khác lần trước. */
    override fun onTick() {
        val v = if (brightness) host.brightness() else host.volume()
        if (v != shown) { shown = v; slider.invalidate() }
    }
}

/** Thanh trượt dọc dùng chung (0..1). */
@SuppressLint("ViewConstructor")
class VerticalSlider(
    c: Context,
    private val iconKey: String,
    private val get: () -> Float?,
    private val set: (Float) -> Unit,
) : View(c) {
    private val track = Paint(Paint.ANTI_ALIAS_FLAG)
    private val fill = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rect = RectF()

    override fun onDraw(canvas: Canvas) {
        val radius = minOf(width, height) * 0.32f
        track.color = Palette.card
        fill.color = Palette.withAlpha(if (Palette.night) Color.WHITE else Palette.text, 235)
        rect.set(0f, 0f, width.toFloat(), height.toFloat())
        canvas.drawRoundRect(rect, radius, radius, track)
        val frac = (get() ?: 0f).coerceIn(0f, 1f)
        canvas.save()
        canvas.clipRect(0f, height * (1f - frac), width.toFloat(), height.toFloat())
        canvas.drawRoundRect(rect, radius, radius, fill)
        canvas.restore()
        val s = (width * 0.5f).toInt()
        Icons.get(context, iconKey, if (frac > 0.12f) Palette.bg else Palette.text)?.apply {
            val l = (width - s) / 2
            setBounds(l, height - s - l, l + s, height - l)
            draw(canvas)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(e: MotionEvent): Boolean {
        if (e.actionMasked == MotionEvent.ACTION_DOWN || e.actionMasked == MotionEvent.ACTION_MOVE) {
            parent?.requestDisallowInterceptTouchEvent(true)
            set((1f - e.y / height).coerceIn(0f, 1f))
            invalidate()
        }
        return true
    }
}
