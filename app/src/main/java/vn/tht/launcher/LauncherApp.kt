package vn.tht.launcher

import android.app.Application
import vn.tht.launcher.vehicle.Profiles
import vn.tht.launcher.vehicle.VehicleProfile

class LauncherApp : Application() {
    lateinit var profile: VehicleProfile
        private set

    /** Ghi chuyến đi; sống cùng tiến trình để màn Thống kê đọc được chuyến hiện tại. */
    val recorder by lazy { vn.tht.launcher.stats.TripRecorder(this) }

    /** ODO đọc được gần nhất (km). */
    @Volatile var lastOdometerKm: Float? = null

    override fun onCreate() {
        super.onCreate()
        profile = Profiles.detect()
        // One-time upgrade to the requested fullscreen-app + persistent THT dock behavior.
        // A later choice of "off" in Settings remains respected.
        val settings = vn.tht.launcher.settings.Prefs(this).sp
        if (!settings.getBoolean("external_fullscreen_ui_v1", false)) settings.edit()
            .putString("dock_overlay", "always").putBoolean("external_fullscreen_ui_v1", true).apply()
        vn.tht.launcher.ui.Palette.initialize(this)
        vn.tht.launcher.ui.Icons.init(this)
    }
}
