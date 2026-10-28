package vn.tht.launcher.system

import android.accessibilityservice.AccessibilityService
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.PixelFormat
import android.graphics.drawable.GradientDrawable
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
import vn.tht.launcher.ui.Ui
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
        if (e?.eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) return
        if (e.packageName?.toString() != HVAC_PACKAGE) return
        if (!prefs.sp.getBoolean(KEY_HIDE_STOCK_HVAC, false)) return
        if (android.os.SystemClock.elapsedRealtime() < allowStockUntil) return
        android.util.Log.i("NavAssist", "Đóng popup điều hoà gốc")
        vn.tht.launcher.vehicle.HvacOpener(this).open()
    }

    override fun onSharedPreferenceChanged(sp: SharedPreferences?, key: String?) {
        if (key == KEY_MODE || key == KEY_BAR_COLLAPSED) rebuild()
        if (key == KEY_BAR_HOME) applyVisibility()
    }

    private fun mode() = prefs.sp.getString(KEY_MODE, "buttons") ?: "buttons"

    private fun rebuild() {
        removeAll()
        val m = mode()
        if (m == "buttons" || m == "both") addPill()
        if (m == "gestures" || m == "both") addStrip()
        if (m == "bar") addBar()
        applyVisibility()
    }

    private fun applyVisibility() {
        val v = if (launcherInFront) View.GONE else View.VISIBLE
        pill?.visibility = v
        strip?.visibility = v
        bar?.visibility = if (launcherInFront && !prefs.sp.getBoolean(KEY_BAR_HOME, false)) View.GONE else View.VISIBLE
    }

    private fun removeAll() {
        listOfNotNull(pill, strip, bar).forEach { runCatching { wm.removeView(it) } }
        pill = null; strip = null; bar = null
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
            setOnClickListener { performGlobalAction(action) }
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
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> if (dragging) {
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

    // ---- Thanh bên (như thanh của xe) ----

    /**
     * Mở rộng: thanh dọc cao hết màn ở mép trái, nút ← ⌂ ▦ điều hoà, dưới cùng là ‹ để thu gọn.
     * Thu gọn: tab nhỏ giữa mép trái, chạm để mở lại. Trạng thái được nhớ (khoá "nav_bar_collapsed").
     * Đây là lớp phủ nên không chừa chỗ như thanh hệ thống: app bên dưới bị che phần mép trái.
     */
    private fun addBar() {
        val c: Context = this
        val collapsed = prefs.sp.getBoolean(KEY_BAR_COLLAPSED, false)
        fun setCollapsed(v: Boolean) = prefs.sp.edit().putBoolean(KEY_BAR_COLLAPSED, v).apply()
        fun btn(icon: String, label: String, onClick: () -> Unit) = ImageView(c).apply {
            setImageDrawable(Icons.get(c, icon, Color.WHITE))
            contentDescription = label
            val p = Ui.dp(c, 18)
            setPadding(p, p, p, p)
            setOnClickListener { onClick() }
        }
        val radius = Ui.dp(c, 18).toFloat()
        val bg = GradientDrawable().apply {
            cornerRadii = floatArrayOf(0f, 0f, radius, radius, radius, radius, 0f, 0f)
            setColor(0xCC000000.toInt())
        }
        val view: View
        val lp: WindowManager.LayoutParams
        if (collapsed) {
            view = btn("chevron-right", "Mở thanh bên") { setCollapsed(false) }.apply {
                background = bg
                val p = Ui.dp(c, 4)
                setPadding(p, p, p, p)
            }
            lp = params(Ui.dp(c, 28), Ui.dp(c, 96)).apply { gravity = Gravity.START or Gravity.CENTER_VERTICAL }
        } else {
            val w = Ui.dp(c, 72)
            fun row() = LinearLayout.LayoutParams(w, 0, 1f)
            view = LinearLayout(c).apply {
                orientation = LinearLayout.VERTICAL
                background = bg
                addView(btn("back", "Quay lại") { performGlobalAction(GLOBAL_ACTION_BACK) }, row())
                addView(btn("home", "Màn hình chính") { performGlobalAction(GLOBAL_ACTION_HOME) }, row())
                addView(btn("apps", "Ứng dụng") { host?.openAppDrawer() ?: performGlobalAction(GLOBAL_ACTION_HOME) }, row())
                addView(btn("climate", "Điều hoà") { host?.openClimate() }, row())
                addView(btn("chevron-left", "Thu gọn") { setCollapsed(true) }, row())
            }
            lp = params(w, WindowManager.LayoutParams.MATCH_PARENT).apply { gravity = Gravity.START or Gravity.TOP }
        }
        runCatching { wm.addView(view, lp) }.onSuccess { bar = view }
    }

    // ---- Dải cử chỉ ----

    @SuppressLint("ClickableViewAccessibility")
    private fun addStrip() {
        val c: Context = this
        val minMove = Ui.dp(c, 40)
        val view = View(c).apply {
            background = GradientDrawable().apply { cornerRadius = Ui.dp(c, 3).toFloat(); setColor(0x66FFFFFF) }
        }
        var sx = 0f; var sy = 0f
        view.setOnTouchListener { _, ev ->
            when (ev.actionMasked) {
                MotionEvent.ACTION_DOWN -> { sx = ev.rawX; sy = ev.rawY }
                MotionEvent.ACTION_UP -> {
                    val dx = ev.rawX - sx
                    val dy = ev.rawY - sy
                    when {
                        -dy > minMove && abs(dy) > abs(dx) -> performGlobalAction(GLOBAL_ACTION_HOME)
                        abs(dx) > minMove -> performGlobalAction(GLOBAL_ACTION_BACK)
                    }
                }
            }
            true
        }
        val lp = params(Ui.dp(c, 320), Ui.dp(c, 14)).apply {
            gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
            y = Ui.dp(c, 2)
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
        }
        @Volatile var host: Host? = null
        private const val HVAC_PACKAGE = "ecarx.hvac.app"
        @Volatile private var allowStockUntil = 0L

        /** Người dùng chủ động mở điều hoà gốc: đừng tự đóng trong [ms]. */
        fun allowStockHvac(ms: Long) { allowStockUntil = android.os.SystemClock.elapsedRealtime() + ms }

        /** Hiện khung nhiệt độ nổi; false nếu dịch vụ trợ năng chưa chạy. */
        fun showTempInfo(temp: String, fan: String, airKey: String?, preview: Boolean = false): Boolean {
            val t = instance?.tempInfo ?: return false
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
            instance?.applyVisibility()
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
