package vn.tht.launcher.settings

import android.content.Context
import android.content.SharedPreferences

/** Toàn bộ cài đặt launcher (SharedPreferences "tht"). Mặc định hợp với Coolray 2025. */
class Prefs(context: Context) {
    val sp: SharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)

    private fun str(k: String, d: String) = sp.getString(k, d) ?: d
    private fun put(k: String, v: Any) = sp.edit().apply {
        when (v) {
            is Boolean -> putBoolean(k, v)
            is Int -> putInt(k, v)
            is Float -> putFloat(k, v)
            else -> putString(k, v.toString())
        }
    }.apply()

    // ---- Giao diện ----
    /** "auto" (theo đèn xe / hệ thống), "day", "night". */
    var themeMode: String get() = str("theme_mode", "auto"); set(v) = put("theme_mode", v)
    var backgroundDay: String get() = str("bg_day", "blue_horizon"); set(v) = put("bg_day", v)
    var backgroundNight: String get() = str("bg_night", "midnight"); set(v) = put("bg_night", v)
    /** Độ đặc của nền widget, 0–100 (%). */
    var cardOpacity: Int get() = sp.getInt("card_opacity", 85); set(v) = put("card_opacity", v)
    var dockOpacity: Int get() = sp.getInt("dock_opacity", 80); set(v) = put("dock_opacity", v)
    /** Tỉ lệ giao diện widget, 75–150 (%). */
    var uiScale: Int get() = sp.getInt("ui_scale", 100); set(v) = put("ui_scale", v)
    var accent: String get() = str("accent", "blue"); set(v) = put("accent", v)

    // ---- Dock ----
    /** "compact", "normal", "large". */
    var dockSize: String get() = str("dock_size", "normal"); set(v) = put("dock_size", v)
    var dockLabels: Boolean get() = sp.getBoolean("dock_labels", true); set(v) = put("dock_labels", v)

    // ---- Đồng hồ ----
    var clock24h: Boolean get() = sp.getBoolean("clock_24h", true); set(v) = put("clock_24h", v)
    var clockShowDate: Boolean get() = sp.getBoolean("clock_date", true); set(v) = put("clock_date", v)

    // ---- Hệ thống ----
    var fullScreen: Boolean get() = sp.getBoolean("full_screen", false); set(v) = put("full_screen", v)
    var openOnBoot: Boolean get() = sp.getBoolean("open_on_boot", true); set(v) = put("open_on_boot", v)

    // ---- Bảo vệ màn hình ----
    var saverEnabled: Boolean get() = sp.getBoolean("saver_on", false); set(v) = put("saver_on", v)
    var saverMinutes: Int get() = sp.getInt("saver_min", 5); set(v) = put("saver_min", v)
    var saverDim: Int get() = sp.getInt("saver_dim", 60); set(v) = put("saver_dim", v)
    var saverParkedOnly: Boolean get() = sp.getBoolean("saver_parked", true); set(v) = put("saver_parked", v)
    var saverShowTrack: Boolean get() = sp.getBoolean("saver_track", true); set(v) = put("saver_track", v)

    // ---- Tự mở app khi khởi động ----
    /** Danh sách package, phân tách bằng dấu phẩy, mở theo thứ tự. */
    var autostartApps: List<String>
        get() = str("autostart", "").split(',').filter { it.isNotBlank() }
        set(v) = put("autostart", v.joinToString(","))
    var autostartDelaySec: Int get() = sp.getInt("autostart_delay", 5); set(v) = put("autostart_delay", v)
    var autostartReturnHome: Boolean get() = sp.getBoolean("autostart_home", true); set(v) = put("autostart_home", v)
    /** Trình phát tự phát nhạc sau khi khởi động ("" = không). */
    var autoplayPlayer: String get() = str("autoplay_player", ""); set(v) = put("autoplay_player", v)

    // ---- Xe ----
    /** Dung tích bình xăng (lít) người dùng nhập; 0 = dùng giá trị xe báo. */
    var tankLiters: Int get() = sp.getInt("tank_l", 0); set(v) = put("tank_l", v)

    companion object {
        const val NAME = "tht"
    }
}
