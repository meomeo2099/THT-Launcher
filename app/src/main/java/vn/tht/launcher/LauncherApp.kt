package vn.tht.launcher

import android.app.Application
import vn.tht.launcher.vehicle.Profiles
import vn.tht.launcher.vehicle.VehicleProfile

class LauncherApp : Application() {
    lateinit var profile: VehicleProfile
        private set

    override fun onCreate() {
        super.onCreate()
        profile = Profiles.detect()
    }
}
