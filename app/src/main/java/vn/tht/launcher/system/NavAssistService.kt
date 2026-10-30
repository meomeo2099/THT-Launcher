package vn.tht.launcher.system

import android.accessibilityservice.AccessibilityService
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.PixelFormat
import android.graphics.drawable.GradientDrawable
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.provider.Settings
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.accessibility.AccessibilityEvent
import android.widget.ImageView
import android.widget.LinearLayout
import vn.tht.launcher.settings.Prefs
import vn.tht.launcher.ui.Icons
import vn.tht.launcher.ui.Palette
import vn.tht.launcher.ui.Ui
import java.util.Locale
import kotlin.math.abs

/**
 * Nút Quay lại / Màn hình chính trên mọi app cho đầu xe không có thanh điều hướng.
 *
 * Kiểu (Cài đặt → Điều hướng, khoá "nav_mode"):
 *  - "buttons": viên nổi có ← và ⌂, nhấn giữ để kéo, vị trí được nhớ.
 *  - "gestures": dải mỏng giữa mép dưới; vuốt lên = Home, vuốt ngang = Back.
 *  - "bar": thanh dọc mép trái như thanh của xe (← ⌂ ▦ điều hoà), thu gọn thành tab nhỏ được.
 *  - "both" / "off".
 * Tự ẩn khi launcher đang ở trước (thanh bên có thể hiện cả trên màn chính, khoá "nav_bar_home"). Không đọc nội dung màn hình (chỉ nghe sự kiện đổi cửa sổ).
 */
class NavAssistService : AccessibilityService(), SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var wm: WindowManager
    private lateinit var prefs: Prefs
    private var pill: View? = null
    private var strip: View? = null
    private var bar: View? = null
    private var tempInfo: TempInfoOverlay? = null
    override fun onUnbind(intent: android.content.Intent?): Boolean {
        // Dịch vụ bị tắt trong Trợ năng: trả lại toàn màn hình cho app
        Overscan.set(this, Prefs(this), 0)
        return super.onUnbind(intent)
    }

    override fun onServiceConnected() {
        instance = this
        wm = getSystemService(WindowManager::class.java)
        prefs = Prefs(this)
        prefs.sp.registerOnSharedPreferenceChangeListener(this)
        tempInfo = TempInfoOverlay(this, wm, prefs)
        rebuild()
    }

    override fun onDestroy() {
        if (instance === this) instance = null
        runCatching { prefs.sp.unregisterOnSharedPreferenceChangeListener(this) }
        removeAll()
        tempInfo?.remove()
        tempInfo = null
        barIo.looper.quitSafely()
        super.onDestroy()
    }

    override fun onInterrupt() {}

    /** Launcher (cùng tiến trình) tự báo khi hiện/ẩn, xem [setLauncherInFront]. */
    /**
     * Popup điều hoà gốc (app ecarx.hvac.app) vừa hiện mà người dùng bật "Ẩn cửa sổ điều hoà gốc":
     * đóng ngay bằng lệnh của chính app điều hoà (lệnh này bật/tắt cửa sổ, nên chỉ gửi khi thấy nó hiện).
     * Bỏ qua khi người dùng vừa chủ động mở điều hoà gốc từ launcher.
     */
    override fun onAccessibilityEvent(e: AccessibilityEvent?) {
        if (StockMode.active(this)) return
        if (e?.eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) return
        // Nhớ app đang ở trước (cho nút chia đôi): bỏ qua launcher, thanh hệ thống, bàn phím
        e.packageName?.toString()?.let { pkg ->
            if (pkg != packageName && pkg != "android" && !pkg.startsWith("com.android.systemui") &&
                packageManager.getLaunchIntentForPackage(pkg) != null) foregroundApp = pkg
            else if (pkg == packageName) foregroundApp = null
        }
        if (e.packageName?.toString() != HVAC_PACKAGE) return
        if (!prefs.sp.getBoolean(KEY_HIDE_STOCK_HVAC, false)) return
        if (android.os.SystemClock.elapsedRealtime() < allowStockUntil) return
        android.util.Log.i("NavAssist", "Đóng popup điều hoà gốc")
        vn.tht.launcher.vehicle.HvacOpener(this).open()
    }

    override fun onSharedPreferenceChanged(sp: SharedPreferences?, key: String?) {
        if (key == KEY_MODE || key == KEY_BAR_COLLAPSED || key == KEY_BAR_ITEMS) rebuild()
        if (key == KEY_BAR_HOME) rebuild()
    }

    private fun mode() = prefs.sp.getString(KEY_MODE, "buttons") ?: "buttons"

    private fun rebuild() {
        removeAll()
        if (StockMode.active(this)) return
        val m = mode()
        // A killed/rebound service has no bar View, but its persisted overscan can remain.
        if (m != "bar") Overscan.set(this, prefs, 0)
        if (m == "buttons" || m == "both") addPill()
        if (m == "gestures" || m == "both") addStrip()
        if (m == "bar") { addBar(); addPill(); addStrip() }
        applyVisibility()
    }

    private fun applyVisibility() {
        if (StockMode.active(this)) return
        if (bar != null && barStyle != style()) return rebuild()
        val v = if (launcherInFront) View.GONE else View.VISIBLE
        pill?.visibility = v
        strip?.visibility = v
        // Đã thu hẹp màn hình cho thanh: luôn hiện (kể cả màn chính), nếu không sẽ còn dải trống
        bar?.visibility = if (launcherInFront) View.VISIBLE else View.GONE
    }

    private fun removeAll() {
        listOfNotNull(pill, strip, bar).forEach { runCatching { wm.removeView(it) } }
        if (bar != null && homeInset != 0) { homeInset = 0; host?.onBarInset() }
        if (bar != null) Overscan.set(this, prefs, 0)
        barEverywhere = false
        pill = null; strip = null; bar = null
        tempBlocks.clear()
        barIo.removeCallbacks(tempPoll)
    }

    private fun params(w: Int, h: Int) = WindowManager.LayoutParams(
        w, h,
        WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        PixelFormat.TRANSLUCENT,
    )

    // ---- Viên nút nổi ----

    @SuppressLint("ClickableViewAccessibility")
    private fun addPill() {
        val c: Context = this
        val size = Ui.dp(c, 56)
        fun btn(icon: String, action: Int) = ImageView(c).apply {
            setImageDrawable(Icons.get(c, icon, Color.WHITE))
            val p = Ui.dp(c, 14)
            setPadding(p, p, p, p)
            setOnClickListener { if (action == GLOBAL_ACTION_HOME) LauncherHome.open(c) else performGlobalAction(action) }
        }
        val view = LinearLayout(c).apply {
            orientation = LinearLayout.VERTICAL
            background = GradientDrawable().apply { cornerRadius = size / 2f; setColor(0xB3000000.toInt()) }
            addView(btn("back", GLOBAL_ACTION_BACK), LinearLayout.LayoutParams(size, size))
            addView(btn("home", GLOBAL_ACTION_HOME), LinearLayout.LayoutParams(size, size))
        }
        val lp = params(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT).apply {
            gravity = Gravity.TOP or Gravity.START
            x = prefs.sp.getInt(KEY_X, Ui.dp(c, 8))
            y = prefs.sp.getInt(KEY_Y, Ui.dp(c, 260))
        }
        // Nhấn giữ rồi kéo để dời viên nút
        var dragging = false
        var sx = 0f; var sy = 0f; var ox = 0; var oy = 0
        val drag = View.OnTouchListener { _, ev ->
            when (ev.actionMasked) {
                MotionEvent.ACTION_DOWN -> { sx = ev.rawX; sy = ev.rawY; ox = lp.x; oy = lp.y; dragging = false }
                MotionEvent.ACTION_MOVE -> {
                    if (dragging) {
                        lp.x = ox + (ev.rawX - sx).toInt(); lp.y = oy + (ev.rawY - sy).toInt()
                        runCatching { wm.updateViewLayout(view, lp) }
                    }
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> if (!dragging && ev.actionMasked == MotionEvent.ACTION_UP &&
                    (sy - ev.rawY > Ui.dp(c, 48) || kotlin.math.abs(ev.rawX - sx) > Ui.dp(c, 48))) {
                    if (sy - ev.rawY > kotlin.math.abs(ev.rawX - sx)) LauncherHome.open(c)
                    else performGlobalAction(GLOBAL_ACTION_BACK)
                    return@OnTouchListener true
                } else if (dragging) {
                    prefs.sp.edit().putInt(KEY_X, lp.x).putInt(KEY_Y, lp.y).apply()
                    dragging = false
                    return@OnTouchListener true
                }
            }
            false
        }
        for (i in 0 until view.childCount) view.getChildAt(i).apply {
            setOnTouchListener(drag)
            setOnLongClickListener { dragging = true; true }
        }
        runCatching { wm.addView(view, lp) }.onSuccess { pill = view }
    }

    // ---- Thanh bên (theo mẫu thanh của launcher gốc) ----

    private val tempBlocks = mutableListOf<Pair<Int, TempBlock>>()
    private var barEverywhere = false
    /** Màu/theme lúc dựng thanh; launcher đổi theme thì dựng lại. */
    private var barStyle: List<Int>? = null
    private val main = Handler(Looper.getMainLooper())
    private val barIo by lazy { Handler(HandlerThread("nav-bar").apply { start() }.looper) }
    private val tempPoll = object : Runnable {
        override fun run() {
            val cl = runCatching { host?.climate() }.getOrNull()
            main.post {
                for ((side, b) in tempBlocks) b.bind(if (side == 0) cl?.left else cl?.right, cl?.fan)
            }
            barIo.postDelayed(this, 2000)
        }
    }

    private fun style() = listOf(if (Palette.night) 1 else 0, Palette.text, Palette.accent)

    /**
     * Bề rộng thanh hệ thống bên trái của xe (px), 0 nếu không có. Trừ phần overscan do chính mình đặt,
     * nếu không lần sau sẽ tưởng đó là thanh của xe.
     */
    private fun systemRailWidth(): Int {
        val d = wm.defaultDisplay
        val real = android.graphics.Point().also { d.getRealSize(it) }
        val app = android.graphics.Point().also { d.getSize(it) }
        val sys = real.x - app.x - Overscan.applied(prefs)
        return if (sys in Ui.dp(this, 60)..Ui.dp(this, 200)) sys else 0
    }

    /**
     * Thanh dọc sát mép trái, cao hết màn như thanh gốc ECARX: nhiệt độ ghế lái (trên), ứng dụng, xe,
     * nhiệt độ ghế phụ, ⌂, nút ‹ quay lại (dưới). Nút nào hiện và thứ tự do người dùng chọn
     * (khoá "nav_bar_items", xem [BAR_ITEMS]). Đặt đè đúng chỗ thanh của xe nên không che app; trên màn
     * chính launcher tự chừa chỗ ([homeInset]). Thu gọn: tab nhỏ giữa mép trái, chạm để mở lại.
     */
    private fun addBar() {
        val c: Context = this
        if (Palette.text == 0) Palette.apply(prefs, Palette.systemNight(c))
        barStyle = style()
        val collapsed = prefs.sp.getBoolean(KEY_BAR_COLLAPSED, false)
        fun setCollapsed(v: Boolean) = prefs.sp.edit().putBoolean(KEY_BAR_COLLAPSED, v).apply()
        val radius = Ui.dp(c, 20).toFloat()
        val bg = GradientDrawable().apply {
            cornerRadii = floatArrayOf(0f, 0f, radius, radius, radius, radius, 0f, 0f)
            setColor(SideBarColors.bg)
        }
        fun icon(key: String, label: String, onClick: () -> Unit) = ImageView(c).apply {
            setImageDrawable(Icons.get(c, key, SideBarColors.fg))
            contentDescription = label
            scaleType = ImageView.ScaleType.FIT_CENTER
            val p = Ui.dp(c, 30)
            setPadding(p, p, p, p)
            background = c.getDrawable(vn.tht.launcher.R.drawable.ripple)
            setOnClickListener { onClick() }
        }
        val view: View
        val lp: WindowManager.LayoutParams
        if (collapsed) {
            view = icon("chevron-right", "Mở thanh bên") { setCollapsed(false) }.apply {
                background = bg
                val p = Ui.dp(c, 6)
                setPadding(p, p, p, p)
            }
            lp = params(Ui.dp(c, 30), Ui.dp(c, 110)).apply { gravity = Gravity.START or Gravity.CENTER_VERTICAL }
            homeInset = 0
            Overscan.set(c, prefs, 0)
        } else {
            // Xe có thanh trái riêng: nằm đè đúng chỗ đó (app đã chừa sẵn). Không có: thu hẹp màn hình bên
            // trái bằng overscan để MỌI app lùi vào, thanh vẽ vào phần được chừa.
            val rail = systemRailWidth()
            val w = Ui.dp(c, 120)
            // Immersive apps can remove the OEM rail after we measured it. Reserve our own
            // rail in fullscreen mode rather than drawing over app/settings content.
            Overscan.set(c, prefs, 0)
            val shifted = true
            val col = LinearLayout(c).apply {
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER_HORIZONTAL
                background = bg
                val p = Ui.dp(c, 8)
                setPadding(0, p, 0, p)
            }
            tempBlocks.clear()
            for (id in barItems(prefs)) {
                val v: View = when (id) {
                    "back" -> backSquare(c) { performGlobalAction(GLOBAL_ACTION_BACK) }
                    "home" -> icon("home", "Màn hình chính THT") { LauncherHome.open(c) }
                    "apps" -> icon("apps", "Ứng dụng") { host?.openAppDrawer() ?: LauncherHome.open(c) }
                    "car" -> icon("car", "Xe") { host?.openControlPanel() ?: LauncherHome.open(c) }
                    "climate" -> icon("climate", "Điều hoà") { host?.openClimate() }
                    "recents" -> icon("chevron-up", "Đa nhiệm") { performGlobalAction(GLOBAL_ACTION_RECENTS) }
                    "notifications" -> icon("chevron-down", "Thông báo") { performGlobalAction(GLOBAL_ACTION_NOTIFICATIONS) }
                    "collapse" -> icon("minus", "Thu gọn") { setCollapsed(true) }
                    "split" -> icon("dual", "Chia đôi màn hình") { host?.quickSplit(foregroundApp) }
                    "temp", "temp2" -> TempBlock(c) { host?.openClimate() }.also { tempBlocks += (if (id == "temp") 0 else 1) to it }
                    else -> continue
                }
                col.addView(v, LinearLayout.LayoutParams(w, 0, 1f))
            }
            view = col
            // Toạ độ tính từ mép màn hình (không phải dưới thanh trạng thái) để thanh cao hết màn
            lp = params(w, WindowManager.LayoutParams.MATCH_PARENT).apply {
                gravity = Gravity.START or Gravity.TOP
                // IN_OVERSCAN: toạ độ tính từ mép thật của màn hình, thanh nằm trọn trong phần đã chừa
                flags = flags or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or FLAG_LAYOUT_IN_OVERSCAN
            }
            barEverywhere = shifted
            homeInset = if (shifted || prefs.sp.getBoolean(KEY_BAR_HOME, false)) w else 0
        }
        if (!collapsed) vn.tht.launcher.ui.ScreenOverlayPosition(view, wm, lp)
        runCatching { wm.addView(view, lp) }.onSuccess {
            bar = view
            if (tempBlocks.isNotEmpty()) { barIo.removeCallbacks(tempPoll); barIo.post(tempPoll) }
        }.onFailure { homeInset = 0 }
        host?.onBarInset()
    }

    // ---- Dải cử chỉ ----

    @SuppressLint("ClickableViewAccessibility")
    private fun addStrip() {
        val c: Context = this
        val minMove = Ui.dp(c, 40)
        val view = android.widget.FrameLayout(c).apply {
            contentDescription = "Vuốt lên về THT; vuốt ngang để quay lại"
            addView(View(c).apply {
                background = GradientDrawable().apply { cornerRadius = Ui.dp(c, 3).toFloat(); setColor(0x66FFFFFF) }
            }, android.widget.FrameLayout.LayoutParams(Ui.dp(c, 160), Ui.dp(c, 4), Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL))
        }
        var sx = 0f; var sy = 0f
        view.setOnTouchListener { _, ev ->
            when (ev.actionMasked) {
                MotionEvent.ACTION_DOWN -> { sx = ev.rawX; sy = ev.rawY }
                MotionEvent.ACTION_UP -> {
                    val dx = ev.rawX - sx
                    val dy = ev.rawY - sy
                    when {
                        -dy > minMove && abs(dy) > abs(dx) -> LauncherHome.open(this@NavAssistService)
                        abs(dx) > minMove -> performGlobalAction(GLOBAL_ACTION_BACK)
                    }
                }
            }
            true
        }
        val lp = params(Ui.dp(c, 400), Ui.dp(c, 40)).apply {
            gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
            y = Ui.dp(c, 24)
        }
        runCatching { wm.addView(view, lp) }.onSuccess { strip = view }
    }

    companion object {
        const val KEY_MODE = "nav_mode"
        const val KEY_HIDE_STOCK_HVAC = "hide_stock_hvac"
        const val KEY_TEMP_INFO = "temp_info"
        const val KEY_BAR_HOME = "nav_bar_home"
        private const val KEY_BAR_COLLAPSED = "nav_bar_collapsed"

        /** Việc thanh bên nhờ launcher làm (cùng tiến trình); MainActivity gắn khi tạo, gỡ khi huỷ. */
        interface Host {
            fun openAppDrawer()
            fun openClimate()
            fun openControlPanel()
            /** Nhiệt độ hai bên + mức quạt, hoặc null; gọi từ luồng nền. */
            fun climate(): BarClimate?
            /** [homeInset] đổi: màn chính chừa/bỏ chừa chỗ cho thanh bên. */
            fun onBarInset()
            /** Nút chia đôi: đang chia thì thoát; không thì [current] sang trái, chọn app cho bên phải. */
            fun quickSplit(current: String?)
        }

        class BarClimate(val left: Float?, val right: Float?, val fan: Int?)

        /** Bề rộng (px) thanh bên đang chiếm trên màn chính; 0 = không hiện ở màn chính. */
        @Volatile var homeInset = 0
            private set

        fun contentInsetLeft(content: View): Int {
            val overlay = instance?.bar ?: return 0
            if (!overlay.isAttachedToWindow || overlay.visibility != View.VISIBLE) return 0
            val barXY = IntArray(2).also(overlay::getLocationOnScreen)
            val windowXY = IntArray(2).also(content::getLocationOnScreen)
            return (barXY[0] + overlay.width - windowXY[0]).coerceIn(0, content.width.coerceAtLeast(0))
        }

        /** App đang ở trước (không phải launcher), null nếu đang ở launcher hoặc chưa biết. */
        @Volatile var foregroundApp: String? = null
            private set
        const val KEY_BAR_ITEMS = "nav_bar_items"
        /** WindowManager.LayoutParams.FLAG_LAYOUT_IN_OVERSCAN (ẩn trong SDK): cho phép vẽ vào phần overscan. */
        private const val FLAG_LAYOUT_IN_OVERSCAN = 0x02000000

        /** Các nút có thể đặt lên thanh bên (khoá → tên hiển thị), theo thứ tự mặc định. */
        val BAR_ITEMS = linkedMapOf(
            "car" to "Xe (bảng điều khiển)",
            "temp" to "Nhiệt độ điều hoà",
            "apps" to "Ứng dụng",
            "home" to "Màn hình chính",
            "back" to "Quay lại",
            "climate" to "Điều hoà (biểu tượng)",
            "recents" to "Đa nhiệm",
            "notifications" to "Thông báo",
            "split" to "Chia đôi màn hình",
            "collapse" to "Thu gọn thanh",
        )
        /** Một nhiệt độ chung cho điều hoà một vùng của xe. */
        val BAR_DEFAULT = listOf("temp", "apps", "car", "split", "home", "back")

        fun barItems(p: Prefs): List<String> =
            p.sp.getString(KEY_BAR_ITEMS, null)?.split(',')?.map { if (it == "temp2") "temp" else it }?.filter { it in BAR_ITEMS }?.distinct() ?: BAR_DEFAULT
        @Volatile var host: Host? = null
        private const val HVAC_PACKAGE = "ecarx.hvac.app"
        @Volatile private var allowStockUntil = 0L

        /** Người dùng chủ động mở điều hoà gốc: đừng tự đóng trong [ms]. */
        fun allowStockHvac(ms: Long) { allowStockUntil = android.os.SystemClock.elapsedRealtime() + ms }

        /** Hiện khung nhiệt độ nổi; false nếu dịch vụ trợ năng chưa chạy. */
        fun showTempInfo(temp: String, fan: String, airKey: String?, preview: Boolean = false): Boolean {
            val service = instance ?: return false
            if (StockMode.active(service)) return false
            val t = service.tempInfo ?: return false
            t.show(temp, fan, airKey, if (preview) 8000 else 3500, draggable = preview)
            return true
        }

        fun resetTempInfo() { instance?.tempInfo?.resetPosition() }

        private var instance: NavAssistService? = null
        /** Launcher đang ở trước: ẩn nút điều hướng. Gọi từ MainActivity.onResume/onPause. */
        @Volatile var launcherInFront = true
            private set

        fun setLauncherInFront(front: Boolean) {
            launcherInFront = front
            if (front) instance?.let { Overscan.set(it, it.prefs, 0); host?.onBarInset() }
            instance?.applyVisibility()
        }
        fun reserveForExternal() {
            instance?.let { Overscan.set(it, it.prefs, 0) }
        }
        fun stockModeChanged() {
            instance?.let { it.tempInfo?.remove(); it.rebuild() }
        }
        private const val KEY_X = "nav_pill_x"
        private const val KEY_Y = "nav_pill_y"

        fun isEnabled(c: Context): Boolean {
            val list = Settings.Secure.getString(c.contentResolver, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES) ?: return false
            return list.split(':').any { it.startsWith(c.packageName + "/") }
        }

        /** Bật dịch vụ không cần người dùng (chỉ được khi APK có WRITE_SECURE_SETTINGS — bản ký platform). */
        fun tryEnable(c: Context): Boolean = runCatching {
            val me = "${c.packageName}/${NavAssistService::class.java.name}"
            val cur = Settings.Secure.getString(c.contentResolver, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES).orEmpty()
            val next = (cur.split(':').filter { it.isNotBlank() && it != me } + me).joinToString(":")
            Settings.Secure.putString(c.contentResolver, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES, next)
            Settings.Secure.putInt(c.contentResolver, Settings.Secure.ACCESSIBILITY_ENABLED, 1)
            true
        }.getOrDefault(false)
    }
}
