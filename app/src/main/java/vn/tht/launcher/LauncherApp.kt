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
        vn.tht.launcher.ui.Palette.initialize(this)
        vn.tht.launcher.ui.Icons.init(this)
    }
}
