package vn.tht.launcher

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vn.tht.launcher.apps.AppsAdapter
import vn.tht.launcher.apps.AppsRepository
import vn.tht.launcher.media.MediaWatcher
import vn.tht.launcher.media.NowPlaying
import vn.tht.launcher.vehicle.CarPropertySource
import vn.tht.launcher.vehicle.EcarxCarSource
import vn.tht.launcher.vehicle.VehicleData
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var speed: TextView
    private lateinit var vehicleInfo: TextView
    private lateinit var mediaTitle: TextView
    private lateinit var mediaArtist: TextView
    private lateinit var mediaArt: ImageView
    private lateinit var btnPlay: ImageButton

    private lateinit var carSource: CarPropertySource
    private lateinit var ecarx: EcarxCarSource
    private lateinit var media: MediaWatcher
    private val appsAdapter = AppsAdapter(::launch, ::openAppInfo)
    private var lastNightMode: Boolean? = null

    private val packageReceiver = object : BroadcastReceiver() {
        override fun onReceive(c: Context, i: Intent) = reloadApps()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val profile = (application as LauncherApp).profile
        findViewById<TextView>(R.id.profile_name).text = profile.displayName

        speed = findViewById(R.id.speed)
        vehicleInfo = findViewById(R.id.vehicle_info)
        mediaTitle = findViewById(R.id.media_title)
        mediaArtist = findViewById(R.id.media_artist)
        mediaArt = findViewById(R.id.media_art)
        btnPlay = findViewById(R.id.btn_play)

        findViewById<RecyclerView>(R.id.apps).apply {
            layoutManager = GridLayoutManager(this@MainActivity, 4)
            adapter = appsAdapter
        }
        reloadApps()

        carSource = CarPropertySource(this, profile) { runOnUiThread { showVehicle(it) } }
        // ECARX AdaptAPI: khi nối xong thì dò và in ra logcat (adb logcat -s EcarxCarSource).
        ecarx = EcarxCarSource(this) { ready -> if (ready) ecarx.dumpSupported() }
        media = MediaWatcher(this) { runOnUiThread { showMedia(it) } }

        btnPlay.setOnClickListener { media.playPause() }
        findViewById<ImageButton>(R.id.btn_next).setOnClickListener { media.next() }
        findViewById<ImageButton>(R.id.btn_prev).setOnClickListener { media.previous() }
        findViewById<android.view.View>(R.id.media_card).setOnClickListener {
            if (!media.hasPermission()) {
                startActivity(Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS))
            }
        }

        registerReceiver(packageReceiver, IntentFilter().apply {
            addAction(Intent.ACTION_PACKAGE_ADDED)
            addAction(Intent.ACTION_PACKAGE_REMOVED)
            addAction(Intent.ACTION_PACKAGE_CHANGED)
            addDataScheme("package")
        })
        carSource.start()
        ecarx.start()
    }

    override fun onResume() {
        super.onResume()
        media.start()
    }

    override fun onPause() {
        media.stop()
        super.onPause()
    }

    override fun onDestroy() {
        carSource.stop()
        ecarx.stop()
        unregisterReceiver(packageReceiver)
        super.onDestroy()
    }

    /** Là launcher: nút Back không thoát. */
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
    }

    private fun reloadApps() = appsAdapter.submit(AppsRepository.load(this))

    private fun launch(app: vn.tht.launcher.apps.AppEntry) {
        val intent = packageManager.getLaunchIntentForPackage(app.packageName) ?: return
        try {
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        } catch (e: Exception) {
            Toast.makeText(this, "Không mở được ${app.label}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openAppInfo(app: vn.tht.launcher.apps.AppEntry) {
        startActivity(
            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:${app.packageName}"))
        )
    }

    private fun showVehicle(d: VehicleData?) {
        if (d == null) {
            speed.text = "--"
            vehicleInfo.text = getString(R.string.no_vehicle)
            return
        }
        speed.text = d.speedKmh?.let { String.format(Locale.US, "%.0f", it) } ?: "--"
        val lines = mutableListOf("Số: ${d.gearText}")
        d.fuelPercent?.let { lines += "Xăng: ${it.toInt()}%" + if (d.fuelLow == true) "  ⚠ sắp hết" else "" }
            ?: run { if (d.fuelLow == true) lines += "⚠ Xăng sắp hết" }
        d.batteryPercent?.let { lines += "Pin: ${it.toInt()}%" }
        d.odometerKm?.let { lines += "ODO: ${String.format(Locale.US, "%,.0f", it)} km" }
        if (d.parkingBrake == true) lines += "Phanh tay: BẬT"
        vehicleInfo.text = lines.joinToString("\n")

        // Đổi giao diện sáng/tối theo đèn xe
        if (d.nightMode != null && d.nightMode != lastNightMode) {
            lastNightMode = d.nightMode
            AppCompatDelegate.setDefaultNightMode(
                if (d.nightMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            )
        }
    }

    private fun showMedia(np: NowPlaying?) {
        if (np == null) {
            mediaTitle.text = getString(if (media.hasPermission()) R.string.no_media else R.string.grant_media)
            mediaArtist.text = ""
            mediaArt.setImageDrawable(null)
            btnPlay.setImageResource(android.R.drawable.ic_media_play)
            return
        }
        mediaTitle.text = np.title ?: ""
        mediaArtist.text = np.artist ?: ""
        if (np.art != null) mediaArt.setImageBitmap(np.art) else mediaArt.setImageDrawable(null)
        btnPlay.setImageResource(
            if (np.isPlaying) android.R.drawable.ic_media_pause else android.R.drawable.ic_media_play
        )
    }
}
