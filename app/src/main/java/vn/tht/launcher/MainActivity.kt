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
import android.widget.ImageView
import android.view.MotionEvent
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vn.tht.launcher.apps.AppEntry
import vn.tht.launcher.apps.AppsAdapter
import vn.tht.launcher.apps.AppsRepository
import vn.tht.launcher.controls.ControlCenter
import vn.tht.launcher.home.HomeCanvas
import vn.tht.launcher.home.HomeLayout
import vn.tht.launcher.home.HomeWidget
import vn.tht.launcher.home.WidgetHost
import vn.tht.launcher.home.WidgetSpec
import vn.tht.launcher.home.WidgetType
import vn.tht.launcher.media.MediaWatcher
import vn.tht.launcher.media.NowPlaying
import vn.tht.launcher.settings.Prefs
import vn.tht.launcher.settings.SettingsActivity
import vn.tht.launcher.settings.Wallpapers
import vn.tht.launcher.shortcuts.DockBar
import vn.tht.launcher.system.Autostart
import vn.tht.launcher.system.Screensaver
import vn.tht.launcher.ui.Palette
import vn.tht.launcher.ui.Ui
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
    override lateinit var prefs: Prefs
    private lateinit var screensaver: Screensaver
    private var appearanceDirty = false
    private var appliedNight: Boolean? = null
    private val prefsListener = android.content.SharedPreferences.OnSharedPreferenceChangeListener { _, _ ->
        appearanceDirty = true
    }

    private val widgets = mutableMapOf<String, HomeWidget>()
    private var lastVehicle: VehicleData? = null
    private var lastEcarx: EcarxInfo? = null
    private var lastMedia: NowPlaying? = null
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
        prefs = Prefs(this)
        Palette.apply(prefs, isNight())

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
        controller = EcarxController(ecarx, { lastVehicle?.speedKmh }, { lastVehicle?.gearText })
        media = MediaWatcher(this) { np ->
            runOnUiThread {
                lastMedia = np
                widgets.values.forEach { it.onMedia(np) }
                if (::screensaver.isInitialized) screensaver.updateTrack()
            }
        }

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
        screensaver = Screensaver(
            findViewById(R.id.root), prefs,
            parked = { lastVehicle?.let { (it.speedKmh ?: 99f) < 1f || it.gearText == "P" } },
            track = { lastMedia?.let { listOfNotNull(it.title, it.artist).joinToString(" · ") } },
        )
        prefs.sp.registerOnSharedPreferenceChangeListener(prefsListener)
        applyAppearance()
        carSource.start()
        ecarx.start()
        Autostart.runIfPending(this)
    }

    override fun onResume() {
        super.onResume()
        media.start()
        if (appearanceDirty) applyAppearance() else dock.reload()
        screensaver.setActive(true)
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.actionMasked == MotionEvent.ACTION_DOWN) screensaver.poke()
        return super.dispatchTouchEvent(ev)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) applyFullScreen()
    }

    override fun onPause() {
        media.stop()
        screensaver.setActive(false)
        super.onPause()
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        carSource.stop()
        ecarx.stop()
        controller.shutdown()
        unregisterReceiver(packageReceiver)
        prefs.sp.unregisterOnSharedPreferenceChangeListener(prefsListener)
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

    private fun onVehicle(data: VehicleData?) {
        val d = data ?: if (vn.tht.launcher.vehicle.DemoCarFunction.enabled(this)) vn.tht.launcher.vehicle.DemoCarFunction.vehicle() else null
        lastVehicle = d
        widgets.values.forEach { it.onVehicle(d) }
        // Theme "tự động": đổi ngày/đêm theo đèn xe
        if (prefs.themeMode == "auto" && isNight() != appliedNight) applyAppearance()
    }

    // ---- Giao diện ----

    private fun isNight(): Boolean = when (prefs.themeMode) {
        "day" -> false
        "night" -> true
        else -> lastVehicle?.nightMode ?: Palette.systemNight(this)
    }

    /** Áp dụng theme, nền, dock, tỉ lệ… rồi dựng lại widget. Gọi khi cài đặt hoặc ngày/đêm đổi. */
    private fun applyAppearance() {
        appearanceDirty = false
        val night = isNight()
        appliedNight = night
        Palette.apply(prefs, night)

        findViewById<View>(R.id.root).setBackgroundColor(Palette.bg)
        val wp = findViewById<ImageView>(R.id.wallpaper)
        val custom = Wallpapers.load(this, if (night) SettingsActivity.NIGHT else SettingsActivity.DAY)
        if (custom != null) wp.setImageBitmap(custom)
        else wp.setImageDrawable(Palette.background(if (night) prefs.backgroundNight else prefs.backgroundDay))

        val dockScroll = findViewById<View>(R.id.dock_scroll)
        dockScroll.background = Ui.pill(this, Palette.dock, 24)
        dockScroll.layoutParams = dockScroll.layoutParams.apply { height = Ui.dp(this@MainActivity, DockBar.heightDp(prefs)) }
        editBar.background = Ui.pill(this, Palette.withAlpha(Palette.dock, 245), 24)
        drawer.setBackgroundColor(Palette.scrim)
        findViewById<EditText>(R.id.drawer_search).apply {
            background = Ui.card(this@MainActivity, 16)
            setTextColor(Palette.text)
            setHintTextColor(Palette.textDim)
        }

        canvas.uiScale = prefs.uiScale.coerceIn(75, 150) / 100f
        val editing = canvas.editing
        buildWidgets(HomeLayout.load(this))
        canvas.setEditing(editing)
        dock.reload()
        appsAdapter.notifyDataSetChanged()
        applyFullScreen()
    }

    @Suppress("DEPRECATION")
    private fun applyFullScreen() {
        window.decorView.systemUiVisibility = if (prefs.fullScreen) {
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        } else 0
        if (prefs.fullScreen) window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        else window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
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

    override fun openControlPanel(tab: Int) = ControlCenter(this, ecarx, controller, tab).show()
    override fun openControls() = openControlPanel()

    @Suppress("DEPRECATION")
    override fun openSettings() {
        startActivityForResult(Intent(this, SettingsActivity::class.java), REQ_SETTINGS)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode != REQ_SETTINGS) return
        applyAppearance()
        if (resultCode == SettingsActivity.RESULT_EDIT_HOME) setEditMode(true)
    }

    fun diagnostics() {
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
        const val REQ_SETTINGS = 10
    }
}
