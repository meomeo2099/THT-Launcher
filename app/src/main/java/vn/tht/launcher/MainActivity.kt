package vn.tht.launcher

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vn.tht.launcher.apps.AppEntry
import vn.tht.launcher.apps.AppsAdapter
import vn.tht.launcher.apps.AppsRepository
import vn.tht.launcher.controls.ControlPanel
import vn.tht.launcher.home.HomeCanvas
import vn.tht.launcher.home.HomeLayout
import vn.tht.launcher.home.HomeWidget
import vn.tht.launcher.home.WidgetHost
import vn.tht.launcher.home.WidgetSpec
import vn.tht.launcher.home.WidgetType
import vn.tht.launcher.media.MediaWatcher
import vn.tht.launcher.media.NowPlaying
import vn.tht.launcher.shortcuts.DockBar
import vn.tht.launcher.vehicle.CarPropertySource
import vn.tht.launcher.vehicle.EcarxCarSource
import vn.tht.launcher.vehicle.EcarxController
import vn.tht.launcher.vehicle.EcarxInfo
import vn.tht.launcher.vehicle.HvacOpener
import vn.tht.launcher.vehicle.PlatformSignature
import vn.tht.launcher.vehicle.VehicleData

class MainActivity : AppCompatActivity(), WidgetHost, DockBar.Actions {

    private lateinit var carSource: CarPropertySource
    private lateinit var ecarx: EcarxCarSource
    private lateinit var media: MediaWatcher
    private lateinit var dock: DockBar
    private lateinit var canvas: HomeCanvas
    private lateinit var drawer: View
    private lateinit var editBar: View
    override lateinit var controller: EcarxController
    private lateinit var hvac: HvacOpener

    private val widgets = mutableMapOf<String, HomeWidget>()
    private var lastVehicle: VehicleData? = null
    private var lastEcarx: EcarxInfo? = null
    private var lastMedia: NowPlaying? = null
    private var lastNightMode: Boolean? = null
    private var allApps: List<AppEntry> = emptyList()

    private val handler = Handler(Looper.getMainLooper())
    private val ecarxPoll = object : Runnable {
        override fun run() {
            lastEcarx = ecarx.readInfo()
            widgets.values.forEach { it.onEcarx(lastEcarx) }
            handler.postDelayed(this, ECARX_POLL_MS)
        }
    }
    private val appsAdapter = AppsAdapter({ launch(it.packageName); closeDrawer() }, ::openAppInfo)

    private val packageReceiver = object : BroadcastReceiver() {
        override fun onReceive(c: Context, i: Intent) = reloadApps()
    }

    override val context: Context get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val profile = (application as LauncherApp).profile

        hvac = HvacOpener(this)
        carSource = CarPropertySource(this, profile) { runOnUiThread { onVehicle(it) } }
        ecarx = EcarxCarSource(this) { ready ->
            if (ready) {
                // Dò và in ra logcat: adb logcat -s EcarxCarSource
                ecarx.dumpSupported()
                handler.removeCallbacks(ecarxPoll)
                handler.post(ecarxPoll)
            }
        }
        controller = EcarxController(ecarx) { lastVehicle?.speedKmh }
        media = MediaWatcher(this) { np -> runOnUiThread { lastMedia = np; widgets.values.forEach { it.onMedia(np) } } }

        canvas = HomeCanvas(this).apply {
            onLongPress = { setEditMode(true) }
            onChanged = { HomeLayout.save(this@MainActivity, specs()) }
            onRemove = { widgets.remove(it.id) }
        }
        findViewById<FrameLayout>(R.id.canvas_container).addView(canvas)
        buildWidgets(HomeLayout.load(this))

        editBar = findViewById(R.id.edit_bar)
        findViewById<View>(R.id.edit_done).setOnClickListener { setEditMode(false) }
        findViewById<View>(R.id.edit_add).setOnClickListener { addWidgetDialog() }
        findViewById<View>(R.id.edit_reset).setOnClickListener {
            HomeLayout.reset(this)
            buildWidgets(HomeLayout.load(this))
            canvas.setEditing(true)
        }

        dock = DockBar(this, findViewById<LinearLayout>(R.id.dock), this)
        DockBar.logCandidates(this)

        setupDrawer()
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
        dock.reload()
    }

    override fun onPause() {
        media.stop()
        super.onPause()
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        carSource.stop()
        ecarx.stop()
        controller.shutdown()
        unregisterReceiver(packageReceiver)
        super.onDestroy()
    }

    /** Là launcher: Back chỉ đóng ngăn kéo / thoát chế độ sửa. */
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        when {
            drawer.visibility == View.VISIBLE -> closeDrawer()
            canvas.editing -> setEditMode(false)
        }
    }

    /** Nút Home khi đang ở launcher: đóng ngăn kéo / thoát sửa. */
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if (drawer.visibility == View.VISIBLE) closeDrawer()
        if (canvas.editing) setEditMode(false)
    }

    // ---- Màn hình widget ----

    private fun buildWidgets(specs: List<WidgetSpec>) {
        canvas.clear()
        widgets.clear()
        specs.forEach { addWidget(it) }
    }

    private fun addWidget(spec: WidgetSpec) {
        val w = HomeWidget.create(this, spec)
        widgets[spec.id] = w
        canvas.add(spec, w.view)
        w.onVehicle(lastVehicle)
        w.onEcarx(lastEcarx)
        w.onMedia(lastMedia)
    }

    private fun setEditMode(on: Boolean) {
        canvas.setEditing(on)
        editBar.visibility = if (on) View.VISIBLE else View.GONE
        if (!on) HomeLayout.save(this, canvas.specs())
    }

    private fun addWidgetDialog() {
        val types = WidgetType.values()
        AlertDialog.Builder(this)
            .setTitle("Thêm widget")
            .setItems(types.map { it.title }.toTypedArray()) { _, i ->
                addWidget(WidgetSpec.new(types[i]))
                canvas.setEditing(true)
                HomeLayout.save(this, canvas.specs())
            }
            .show()
    }

    override fun saveLayout() = HomeLayout.save(this, canvas.specs())

    // ---- Dữ liệu xe ----

    private fun onVehicle(d: VehicleData?) {
        lastVehicle = d
        widgets.values.forEach { it.onVehicle(d) }
        // Đổi giao diện sáng/tối theo đèn xe
        if (d?.nightMode != null && d.nightMode != lastNightMode) {
            lastNightMode = d.nightMode
            AppCompatDelegate.setDefaultNightMode(
                if (d.nightMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            )
        }
    }

    // ---- Ngăn kéo ứng dụng ----

    private fun setupDrawer() {
        drawer = findViewById(R.id.drawer)
        findViewById<RecyclerView>(R.id.apps).apply {
            layoutManager = GridLayoutManager(this@MainActivity, 8)
            adapter = appsAdapter
        }
        findViewById<View>(R.id.drawer_close).setOnClickListener { closeDrawer() }
        findViewById<EditText>(R.id.drawer_search).addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = filterApps(s?.toString().orEmpty())
            override fun beforeTextChanged(s: CharSequence?, a: Int, b: Int, c: Int) {}
            override fun onTextChanged(s: CharSequence?, a: Int, b: Int, c: Int) {}
        })
        reloadApps()
    }

    private fun reloadApps() {
        allApps = AppsRepository.load(this)
        filterApps(findViewById<EditText>(R.id.drawer_search).text.toString())
    }

    private fun filterApps(q: String) {
        val key = q.trim().lowercase()
        appsAdapter.submit(if (key.isEmpty()) allApps else allApps.filter { key in it.label.lowercase() })
    }

    override fun openAppDrawer() {
        drawer.visibility = View.VISIBLE
    }

    private fun closeDrawer() {
        drawer.visibility = View.GONE
        findViewById<EditText>(R.id.drawer_search).setText("")
    }

    // ---- WidgetHost / DockBar.Actions ----

    override fun mediaPlayPause() { media.playPause() }
    override fun mediaNext() { media.next() }
    override fun mediaPrevious() { media.previous() }
    override fun hasMediaPermission() = media.hasPermission()
    override fun requestMediaPermission() = startActivity(Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS))

    override fun openApp(pkg: String) = launch(pkg)

    override fun appIcon(pkg: String): Drawable? = runCatching { packageManager.getApplicationIcon(pkg) }.getOrNull()

    override fun appLabel(pkg: String): String? = runCatching {
        packageManager.getApplicationLabel(packageManager.getApplicationInfo(pkg, 0)).toString()
    }.getOrNull()

    override fun pickApp(onPicked: (String) -> Unit) {
        val apps = allApps.ifEmpty { AppsRepository.load(this) }
        AlertDialog.Builder(this)
            .setTitle("Chọn ứng dụng")
            .setItems(apps.map { it.label }.toTypedArray()) { _, i -> onPicked(apps[i].packageName) }
            .show()
    }

    override fun openNativeHvac() = hvac.open { ok -> if (!ok) toast("Không mở được điều hoà") }

    override fun openControlPanel() = ControlPanel(this, controller).show()
    override fun openControls() = openControlPanel()

    override fun openSettings() {
        val items = arrayOf(
            "Sửa màn hình chính",
            "Khôi phục bố cục mặc định",
            "Quyền đọc nhạc",
            "Chẩn đoán kết nối xe",
        )
        AlertDialog.Builder(this)
            .setTitle("Cài đặt THT Launcher")
            .setItems(items) { _, i ->
                when (i) {
                    0 -> setEditMode(true)
                    1 -> { HomeLayout.reset(this); buildWidgets(HomeLayout.load(this)) }
                    2 -> requestMediaPermission()
                    3 -> diagnostics()
                }
            }
            .show()
    }

    private fun diagnostics() {
        val msg = buildString {
            appendLine("Profile: ${(application as LauncherApp).profile.displayName}")
            appendLine("ECARX AdaptAPI: ${if (ecarx.connected) "đã nối" else "chưa nối"}")
            appendLine("Chữ ký platform: ${if (PlatformSignature.verify(this@MainActivity)) "KHỚP – ghi được" else "không khớp – chỉ đọc"}")
            appendLine("Chứng chỉ firmware: ${PlatformSignature.certSha256(this@MainActivity, "android").take(16)}…")
            appendLine("Chứng chỉ app: ${PlatformSignature.certSha256(this@MainActivity, packageName).take(16)}…")
            appendLine("Dữ liệu xe (VHAL): ${if (lastVehicle != null) "có" else "không"}")
        }
        AlertDialog.Builder(this).setTitle("Chẩn đoán").setMessage(msg).setPositiveButton("Đóng", null).show()
    }

    override fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    private fun launch(pkg: String) {
        val intent = packageManager.getLaunchIntentForPackage(pkg) ?: return toast("Không tìm thấy ứng dụng")
        try {
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        } catch (e: Exception) {
            toast("Không mở được ứng dụng")
        }
    }

    private fun openAppInfo(app: AppEntry) {
        startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:${app.packageName}")))
    }

    private companion object {
        const val ECARX_POLL_MS = 2000L
    }
}
