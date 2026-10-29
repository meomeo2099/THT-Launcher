package vn.tht.launcher

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.Log
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
import vn.tht.launcher.home.isBar
import vn.tht.launcher.media.MediaWatcher
import vn.tht.launcher.media.NowPlaying
import vn.tht.launcher.settings.Prefs
import vn.tht.launcher.settings.SettingsActivity
import vn.tht.launcher.settings.Wallpapers
import vn.tht.launcher.shortcuts.DockBar
import vn.tht.launcher.shortcuts.FloatingDock
import vn.tht.launcher.ui.Bitmaps
import vn.tht.launcher.shortcuts.Shortcut
import vn.tht.launcher.split.Split
import vn.tht.launcher.split.SplitScreen
import vn.tht.launcher.split.SplitStore
import vn.tht.launcher.split.SplitUi
import vn.tht.launcher.system.Autostart
import vn.tht.launcher.system.Screensaver
import vn.tht.launcher.ui.Dialogs
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
    override val radio by lazy { vn.tht.launcher.radio.EcarxRadio(this) }
    private val appWidgets by lazy { vn.tht.launcher.home.AppWidgets(this) }

    override fun appWidgetView(id: Int): View? = appWidgets.createView(id)

    override fun onStart() {
        super.onStart()
        appWidgets.startListening()
    }

    override fun onStop() {
        appWidgets.stopListening()
        super.onStop()
    }
    private lateinit var dock: DockBar
    private lateinit var topBar: DockBar
    private lateinit var sideRail: DockBar
    private lateinit var climateMini: vn.tht.launcher.home.ClimateMini
    private lateinit var fanMini: vn.tht.launcher.home.FanMini
    private var lastStatus: DockBar.Status? = null
    private lateinit var canvas: HomeCanvas
    private lateinit var drawer: View
    private lateinit var editBar: View
    override lateinit var controller: EcarxController
    private lateinit var hvac: HvacOpener
    override lateinit var prefs: Prefs
    private lateinit var screensaver: Screensaver
    private lateinit var automation: vn.tht.launcher.automation.AutomationEngine
    private var todayKmCache = 0f to 0L
    private var appearanceDirty = false
    private var appliedNight: Boolean? = null
    private val prefsListener = android.content.SharedPreferences.OnSharedPreferenceChangeListener { _, _ ->
        appearanceDirty = true
    }

    private val widgets = mutableMapOf<String, HomeWidget>()
    private var lastVehicle: VehicleData? = null
    private var lastEcarx: EcarxInfo? = null
    private var lastMedia: NowPlaying? = null
    private val gpsSpeed by lazy { vn.tht.launcher.vehicle.GpsSpeedSource(applicationContext) }
    private var allApps: List<AppEntry> = emptyList()

    private val handler = Handler(Looper.getMainLooper())
    /** Luồng phụ cho lệnh đọc ECARX (hàng chục lời gọi binder mỗi lượt), để luồng giao diện không bị giật. */
    private val carIo = android.os.HandlerThread("car-io").apply { start() }
    private val io = Handler(carIo.looper)
    /** Launcher đang hiện trên màn (giữa onResume và onPause). */
    private var inFront = false
    /** Nhiệt độ/quạt/hướng gió lần đọc trước khi launcher bị che (cho khung nhiệt độ nổi). */
    private var bgClimate: Triple<Float?, Int?, Int?>? = null
    private val bgClimatePoll = object : Runnable {
        override fun run() {
            val f = vn.tht.launcher.vehicle.EcarxFunctions
            val zone = if (ecarx.isSupported(f.HVAC_TEMP, f.ZONE_ROW_1_LEFT) == true) f.ZONE_ROW_1_LEFT else f.ZONE_ALL
            val now = Triple(
                ecarx.getFloat(f.HVAC_TEMP, zone)?.takeIf { it in f.TEMP_MIN..f.TEMP_MAX },
                f.levelOf(f.HVAC_FAN_SPEED, ecarx.getInt(f.HVAC_FAN_SPEED)),
                f.levelOf(f.HVAC_BLOWING_MODE, ecarx.getInt(f.HVAC_BLOWING_MODE)),
            )
            val before = bgClimate
            bgClimate = now
            if (before != null && now != before && (now.first != null || now.second != null)) handler.post { showTempInfo(now) }
            io.postDelayed(this, 1000)
        }
    }

    private fun showTempInfo(v: Triple<Float?, Int?, Int?>) {
        val air = when (v.third) { 1 -> "air-upper"; 2 -> "air-lower"; 3 -> "air-both"; 4 -> "front-defrost"; else -> null }
        val ok = vn.tht.launcher.system.NavAssistService.showTempInfo(
            v.first?.let { String.format(java.util.Locale.US, "%.1f°", it) } ?: "--°",
            v.second?.let { if (it == 0) "Quạt tắt" else "Quạt $it/${vn.tht.launcher.vehicle.EcarxFunctions.FAN_MAX}" } ?: "Quạt –",
            air,
        )
        if (!ok) android.util.Log.i("MainActivity", "Khung nhiệt độ: dịch vụ trợ năng chưa chạy")
    }
    private val ecarxPoll = object : Runnable {
        override fun run() {
            val info = ecarx.readInfo()
            handler.post {
                lastEcarx = info
                widgets.values.forEach { it.onEcarx(info) }
                if (::climateMini.isInitialized) climateMini.bind(info)
                if (::fanMini.isInitialized) fanMini.bind(info)
                updateDockStatus(lastVehicle)
            }
            io.postDelayed(this, ECARX_POLL_MS)
        }
    }
    private var tickCount = 0
    private val tick = object : Runnable {
        override fun run() {
            widgets.values.forEach { it.onTick() }
            updateDockStatus(lastVehicle)
            // Thời tiết: kiểm tra mỗi phút, chỉ tải khi dữ liệu cũ hơn 30 phút
            if (tickCount++ % 60 == 0) vn.tht.launcher.system.Weather.refreshIfStale(this@MainActivity) {
                runOnUiThread { widgets.values.forEach { it.onTick() } }
            }
            handler.postDelayed(this, 1000)
        }
    }
    private val appsAdapter = AppsAdapter({ launch(it.packageName); closeDrawer() }, ::appMenu)

    private val packageReceiver = object : BroadcastReceiver() {
        override fun onReceive(c: Context, i: Intent) = reloadApps()
    }

    override val context: Context get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vn.tht.launcher.system.NavAssistService.host = object : vn.tht.launcher.system.NavAssistService.Companion.Host {
            override fun openAppDrawer() = this@MainActivity.openAppDrawer()
            override fun openClimate() = this@MainActivity.openClimate()
            override fun openControlPanel() = inLauncher { this@MainActivity.openControlPanel(0) }
            override fun climate(): vn.tht.launcher.system.NavAssistService.Companion.BarClimate? {
                if (!ecarx.connected) return null
                val f = vn.tht.launcher.vehicle.EcarxFunctions
                fun temp(zone: Int) = ecarx.getFloat(f.HVAC_TEMP, zone)?.takeIf { it in f.TEMP_MIN..f.TEMP_MAX }
                val dual = ecarx.isSupported(f.HVAC_TEMP, f.ZONE_ROW_1_LEFT) == true
                val left = if (dual) temp(f.ZONE_ROW_1_LEFT) else temp(f.ZONE_ALL)
                val right = if (ecarx.isSupported(f.HVAC_TEMP, f.ZONE_ROW_1_RIGHT) == true) temp(f.ZONE_ROW_1_RIGHT) else left
                val on = ecarx.getInt(f.HVAC_POWER)?.let { it == f.ON }
                val fan = if (on == false) 0 else f.levelOf(f.HVAC_FAN_SPEED, ecarx.getInt(f.HVAC_FAN_SPEED))
                return vn.tht.launcher.system.NavAssistService.Companion.BarClimate(left, right, fan)
            }
            override fun onBarInset() = runOnUiThread { applyBarInset() }
            override fun quickSplit(current: String?) = runOnUiThread { this@MainActivity.quickSplit(current) }
        }
        // Gốc giao diện + ảnh nền đã phủ kín màn: bỏ nền cửa sổ để khỏi vẽ 1920×720 thêm một lần mỗi khung hình
        window.setBackgroundDrawable(null)
        // Thanh trên: hàng icon nằm trong khung tự thu nhỏ, thêm ô trạng thái không bị cắt hai đầu
        val topRow = findViewById<LinearLayout>(R.id.top_bar)
        (topRow.parent as? android.view.ViewGroup)?.removeView(topRow)
        val topHolder = vn.tht.launcher.ui.FitFrame(this, matchHeight = true).apply {
            addView(topRow, FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.MATCH_PARENT))
        }
        bars = mapOf(
            WidgetType.TOP_BAR to topHolder,
            WidgetType.SIDE_RAIL to findViewById(R.id.side_rail),
            WidgetType.BOTTOM_BAR to findViewById(R.id.bottom_bar),
            // Cụm điều hoà gọn: khung riêng, đặt như widget
            WidgetType.CLIMATE_MINI to FrameLayout(this),
            WidgetType.FAN_MINI to vn.tht.launcher.ui.FitFrame(this).apply { val p = Ui.dp(this@MainActivity, 6); setPadding(p, p, p, p) },
        )
        val profile = (application as LauncherApp).profile
        prefs = Prefs(this)
        Palette.apply(prefs, isNight())

        hvac = HvacOpener(this)
        carSource = CarPropertySource(this, profile) { runOnUiThread { onVehicle(it) } }
        ecarx = EcarxCarSource(this) { ready ->
            if (ready) {
                // Dò và in ra logcat: adb logcat -s EcarxCarSource
                ecarx.dumpSupported()
                io.removeCallbacks(ecarxPoll)
                if (inFront) io.post(ecarxPoll)
            }
        }
        controller = EcarxController(ecarx, { lastVehicle?.speedKmh }, { lastVehicle?.gearText })
        automation = vn.tht.launcher.automation.AutomationEngine(prefs, controller,
            vn.tht.launcher.automation.AutomationEngine::defaultLog,
            readValue = { id, zone -> ecarx.getInt(id, zone) },
            bootCount = runCatching { Settings.Global.getInt(contentResolver, Settings.Global.BOOT_COUNT, -1) }
                .getOrNull()?.takeIf { it >= 0 },
            onApps = { current -> Autostart.runForEngine(applicationContext, current) },
            onCancelApps = { Autostart.cancelEngine() },
            gpsSpeedKmh = { gpsSpeed.speedKmh() })
        requestCarPermissions()
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
            onRemove = {
                widgets.remove(it.id)
                if (it.type == WidgetType.ANDROID_WIDGET) it.pkg.toIntOrNull()?.let { id -> appWidgets.delete(id) }
            }
            onWidgetTap = { exactDialog(it) }
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

        dock = DockBar(this, bars.getValue(WidgetType.BOTTOM_BAR).findViewById<LinearLayout>(R.id.dock), this)
        sideRail = DockBar(
            this, bars.getValue(WidgetType.SIDE_RAIL) as LinearLayout, this,
            vn.tht.launcher.shortcuts.ShortcutStore.SIDERAIL_FILE, vn.tht.launcher.shortcuts.ShortcutStore.SIDERAIL_DEFAULTS,
            compact = true, vertical = true,
        )
        climateMini = vn.tht.launcher.home.ClimateMini(this, controller, ::openClimate, ::toast)
        (bars.getValue(WidgetType.CLIMATE_MINI) as FrameLayout).addView(climateMini,
            FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.MATCH_PARENT, android.view.Gravity.CENTER))
        fanMini = vn.tht.launcher.home.FanMini(this, controller, ::openClimate, ::toast)
        (bars.getValue(WidgetType.FAN_MINI) as FrameLayout).addView(fanMini,
            FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.MATCH_PARENT, android.view.Gravity.CENTER))
        topBar = DockBar(
            this, topRow, this,
            vn.tht.launcher.shortcuts.ShortcutStore.TOPBAR_FILE, vn.tht.launcher.shortcuts.ShortcutStore.TOPBAR_DEFAULTS,
            compact = true,
        )
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
        // Tự kiểm tra cập nhật tối đa mỗi ngày một lần
        if (vn.tht.launcher.system.Updater.shouldAutoCheck(this)) Thread {
            val r = vn.tht.launcher.system.Updater.check(this) ?: return@Thread
            runOnUiThread { vn.tht.launcher.system.UpdateUi.offer(this, r) }
        }.start()
    }

    override fun onResume() {
        super.onResume()
        inFront = true
        val automationPrefs = vn.tht.launcher.automation.AutomationPrefs(prefs)
        gpsSpeed.setEnabled(automationPrefs.enabled && automationPrefs.closeRoofAtSpeed > 0)
        if (::ecarx.isInitialized && !ecarx.connected) ecarx.start()
        refreshFloatingDock()
        // Khi ẩn chỉ ghi chuyến đi/tự động hoá; giờ hiện lại thì cập nhật giao diện theo dữ liệu mới nhất
        refreshVehicleUi(lastVehicle)
        io.removeCallbacks(bgClimatePoll)
        if (ecarx.connected) { io.removeCallbacks(ecarxPoll); io.post(ecarxPoll) }
        media.start()
        radio.start()
        if (appearanceDirty) applyAppearance() else { dock.reload(); topBar.reload(); sideRail.reload() }
        widgets.values.forEach { it.onLauncherVisibility(true) }
        screensaver.setActive(true)
        vn.tht.launcher.system.NavAssistService.setLauncherInFront(true)
        handler.removeCallbacks(tick)
        handler.post(tick)
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
        inFront = false
        widgets.values.forEach { it.onLauncherVisibility(false) }
        rememberDockBounds()
        refreshFloatingDock()
        io.removeCallbacks(ecarxPoll)
        // Khung nhiệt độ nổi: chỉ đọc 3 giá trị điều hoà mỗi giây khi launcher bị che
        if (ecarx.connected && prefs.sp.getBoolean(vn.tht.launcher.system.NavAssistService.KEY_TEMP_INFO, false)) {
            bgClimate = null
            io.post(bgClimatePoll)
        }
        media.stop()
        radio.stop()
        screensaver.setActive(false)
        vn.tht.launcher.system.NavAssistService.setLauncherInFront(false)
        handler.removeCallbacks(tick)
        super.onPause()
    }

    override fun onDestroy() {
        vn.tht.launcher.system.NavAssistService.host = null
        splitScreen.release()
        floatingDock.hide()
        handler.removeCallbacksAndMessages(null)
        io.removeCallbacksAndMessages(null)
        automation.stop()
        Autostart.cancel()
        gpsSpeed.stop()
        carIo.quitSafely()
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
        w.onLauncherVisibility(inFront)
    }

    private fun setEditMode(on: Boolean) {
        canvas.setEditing(on)
        editBar.visibility = if (on) View.VISIBLE else View.GONE
        if (!on) HomeLayout.save(this, canvas.specs())
    }

    /** Thư viện widget: chọn nhóm rồi chọn loại (hộp chọn có icon). */
    private fun addWidgetDialog() {
        val groups = WidgetType.values().groupBy { it.group }
        val keys = groups.keys.toList()
        vn.tht.launcher.ui.IconList.show(this, "Thư viện widget",
            listOf(vn.tht.launcher.ui.IconList.Item("Tìm widget", "apps", summary = "Gõ tên, vd: nhạc, xăng, radio")) +
                keys.map { vn.tht.launcher.ui.IconList.Item(it, vn.tht.launcher.home.groupIcon(it), summary = "${groups.getValue(it).size} loại") },
            columns = 2) { gi ->
            if (gi == 0) return@show searchWidgets()
            showWidgetTypes(keys[gi - 1], groups.getValue(keys[gi - 1]))
        }
    }

    /** Tìm loại widget theo tên (không phân biệt dấu). */
    private fun searchWidgets() {
        fun plain(s: String) = vn.tht.launcher.ui.Text.plain(s)
        Dialogs.form(this, "Tìm widget", listOf(Dialogs.Field("Tên widget", hint = "vd: nhạc, xăng, radio")), listOf(
            Dialogs.FormButton("Huỷ"),
            Dialogs.FormButton("Tìm", Dialogs.Kind.PRIMARY) { (q) ->
                val key = plain(q.trim())
                val found = WidgetType.values().filter { key.isNotEmpty() && (key in plain(it.title) || key in plain(it.group)) }
                if (found.isEmpty()) toast("Không thấy widget nào khớp \"${q.trim()}\"")
                else showWidgetTypes("Kết quả: ${q.trim()}", found)
            },
        ))
    }

    private fun showWidgetTypes(title: String, types: List<WidgetType>) {
        vn.tht.launcher.ui.IconList.show(this, title,
            types.map { t -> vn.tht.launcher.ui.IconList.Item(t.title, with(vn.tht.launcher.home.WidgetSpecIcons) { t.iconKey }) }, columns = 2) { i ->
            addWidgetOfType(types[i])
        }
    }

    private fun addWidgetOfType(type: WidgetType) {
        if (type.isBar && canvas.specs().any { it.type == type }) {
            toast("Thanh này đã có trên màn hình"); return
        }
        if (type == WidgetType.ANDROID_WIDGET) {
            appWidgets.pick { id ->
                if (id == null) return@pick toast("Chưa thêm widget")
                addWidget(WidgetSpec.new(type).also { it.pkg = id.toString() })
                canvas.setEditing(true)
                HomeLayout.save(this, canvas.specs())
            }
            return
        }
        addWidget(WidgetSpec.new(type))
        canvas.setEditing(true)
        HomeLayout.save(this, canvas.specs())
    }

    override fun saveLayout() = HomeLayout.save(this, canvas.specs())

    // ---- Dữ liệu xe ----

    private fun onVehicle(data: VehicleData?) {
        val d = data ?: if (vn.tht.launcher.vehicle.DemoCarFunction.enabled(this)) vn.tht.launcher.vehicle.DemoCarFunction.vehicle() else null
        lastVehicle = d
        val app = application as LauncherApp
        d?.odometerKm?.let { app.lastOdometerKm = it }
        app.recorder.onVehicle(d)
        automation.onVehicle(d)
        if (inFront) refreshVehicleUi(d)
        else if (floatingDock.shown) updateDockStatus(d)
        else if (prefs.themeMode == "auto" && isNight() != appliedNight) appearanceDirty = true
    }

    /** Cập nhật widget, thanh trạng thái và theme ngày/đêm; bỏ qua khi launcher đang ẩn sau app khác. */
    private fun refreshVehicleUi(d: VehicleData?) {
        widgets.values.forEach { it.onVehicle(d) }
        updateDockStatus(d)
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
        val which = if (night) SettingsActivity.NIGHT else SettingsActivity.DAY
        val custom = if (prefs.sp.getBoolean("use_wallpaper_$which", true)) Wallpapers.load(this, which) else null
        if (custom != null) wp.setImageBitmap(custom)
        else wp.setImageDrawable(Palette.background(if (night) prefs.backgroundNight else prefs.backgroundDay))

        val bottom = bars.getValue(WidgetType.BOTTOM_BAR)
        bottom.background = Ui.pill(this, Palette.dock, 26)
        bars.getValue(WidgetType.CLIMATE_MINI).background = Ui.pill(this, Palette.dock, 26)
        bars.getValue(WidgetType.FAN_MINI).background = Ui.pill(this, Palette.dock, 26)
        fanMini.bind(lastEcarx)
        climateMini.bind(lastEcarx)
        editBar.background = Ui.pill(this, Palette.withAlpha(Palette.dock, 245), 24)
        drawer.setBackgroundColor(Palette.scrim)
        findViewById<EditText>(R.id.drawer_search).apply {
            background = Ui.card(this@MainActivity, 16)
            setTextColor(Palette.text)
            setHintTextColor(Palette.textDim)
        }

        canvas.uiScale = prefs.uiScale.coerceIn(75, 150) / 100f
        // Kính mờ: chụp + làm mờ ảnh nền một lần (sau khi ảnh nền đã layout), rồi vẽ lại các thẻ
        if (prefs.cardStyle == "frosted") wp.post {
            vn.tht.launcher.ui.Frosted.capture(wp)
            widgets.values.forEach { w -> invalidateTree(w.view) }
        } else vn.tht.launcher.ui.Frosted.clear()
        val editing = canvas.editing
        buildWidgets(HomeLayout.load(this))
        canvas.setEditing(editing)
        dock.reload()
        val top = bars.getValue(WidgetType.TOP_BAR)
        top.background = Ui.pill(this, Palette.withAlpha(if (Palette.night) 0xFF000000.toInt() else 0xFFFFFFFF.toInt(), 200), 14)
        topBar.reload()
        bars.getValue(WidgetType.SIDE_RAIL).background = Ui.pill(this, Palette.dock, 24)
        sideRail.reload()
        applyCollapsed()
        appsAdapter.notifyDataSetChanged()
        applyFullScreen()
    }

    /**
     * Thanh bên của THT hiện cả trên màn chính: lùi khung widget và ngăn ứng dụng sang phải cho khỏi bị che.
     * Nếu cửa sổ launcher đã nằm sau thanh của xe (không toàn màn hình) thì chỉ lùi phần còn thiếu.
     */
    private fun applyBarInset() {
        val root = findViewById<View>(R.id.root)
        root.post {
            val x = IntArray(2).also { root.getLocationOnScreen(it) }[0]
            val pad = (vn.tht.launcher.system.NavAssistService.homeInset - x).coerceAtLeast(0)
            for (id in intArrayOf(R.id.canvas_container, R.id.drawer)) {
                val v = findViewById<View>(id)
                if (v.paddingLeft != pad) v.setPadding(pad, v.paddingTop, v.paddingRight, v.paddingBottom)
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun applyFullScreen() {
        window.decorView.systemUiVisibility = if (prefs.fullScreen) {
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        } else 0
        if (prefs.fullScreen) window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        else window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        applyPolicyControl(prefs.fullScreen)
        applyBarInset()
    }

    /**
     * Thanh hệ thống của ECARX không chịu ẩn theo cờ của cửa sổ. Android 9 còn `policy_control` (cần
     * WRITE_SECURE_SETTINGS): policy theo từng package, có khôi phục và giữ cấu hình khác.
     */
    private fun applyPolicyControl(on: Boolean) {
        if (!on) {
            vn.tht.launcher.system.AppWindowPolicy.restore(this)
            // Migrate the launcher-only policy written by older versions as well.
            runCatching {
                if (Settings.Global.getString(contentResolver, "policy_control") == "immersive.full=$packageName")
                    Settings.Global.putString(contentResolver, "policy_control", null)
            }
        }
        else if (inFront && !splitScreen.hasWork)
            vn.tht.launcher.system.AppWindowPolicy.apply(this, setOf(packageName))
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
        navCache = null; radioCache = null
        filterApps(findViewById<EditText>(R.id.drawer_search).text.toString())
    }

    /** App ghim lên đầu ngăn kéo (theo thứ tự ghim). */
    private var favoriteApps: List<String>
        get() = prefs.sp.getString("drawer_favorites", "").orEmpty().split(',').filter { it.isNotBlank() }
        set(v) = prefs.sp.edit().putString("drawer_favorites", v.joinToString(",")).apply()

    private fun filterApps(q: String) {
        val key = q.trim().lowercase()
        val favs = favoriteApps
        val list = if (key.isEmpty()) {
            val byPkg = allApps.associateBy { it.packageName }
            favs.mapNotNull { byPkg[it] } + allApps.filter { it.packageName !in favs }
        } else allApps.filter { key in it.label.lowercase() }
        appsAdapter.submit(list, favs.toSet())
    }

    /** Nhấn giữ app trong ngăn kéo: ghim, thông tin, gỡ. */
    private fun appMenu(app: AppEntry) {
        val pinned = app.packageName in favoriteApps
        vn.tht.launcher.ui.IconList.show(this, app.label, listOf(
            vn.tht.launcher.ui.IconList.Item(if (pinned) "Bỏ ghim" else "Ghim lên đầu", if (pinned) "favorite-off" else "favorite-on"),
            vn.tht.launcher.ui.IconList.Item("Thông tin ứng dụng", "settings"),
            vn.tht.launcher.ui.IconList.Item("Gỡ ứng dụng", "close", danger = true),
        )) { i ->
            when (i) {
                0 -> {
                    favoriteApps = if (pinned) favoriteApps - app.packageName else favoriteApps + app.packageName
                    filterApps(findViewById<EditText>(R.id.drawer_search).text.toString())
                }
                1 -> openAppInfo(app)
                2 -> runCatching {
                    startActivity(Intent(Intent.ACTION_DELETE, Uri.parse("package:${app.packageName}")))
                }.onFailure { toast("Không gỡ được ứng dụng này") }
            }
        }
    }

    override fun openAppDrawer() = inLauncher {
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
    override fun canSeekMedia(): Boolean = media.canSeek()
    override fun mediaSeekTo(positionMs: Long): Boolean = media.seekTo(positionMs)
    override fun hasMediaPermission() = media.hasPermission()
    override fun requestMediaPermission() {
        vn.tht.launcher.system.ExternalAppLauncher.launch(this, Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS))
    }

    override fun openApp(pkg: String) = launch(pkg)

    override fun appIcon(pkg: String): Drawable? = runCatching { packageManager.getApplicationIcon(pkg) }.getOrNull()

    override fun appLabel(pkg: String): String? = runCatching {
        packageManager.getApplicationLabel(packageManager.getApplicationInfo(pkg, 0)).toString()
    }.getOrNull()

    override fun pickApp(onPicked: (String) -> Unit) {
        val apps = allApps.ifEmpty { AppsRepository.load(this) }
        vn.tht.launcher.ui.IconList.show(this, "Chọn ứng dụng",
            apps.map { vn.tht.launcher.ui.IconList.Item(it.label, drawable = it.icon) }, columns = 3) { i ->
            onPicked(apps[i].packageName)
        }
    }

    override fun openNativeHvac() {
        vn.tht.launcher.system.NavAssistService.allowStockHvac(15_000)
        hvac.open { ok -> if (!ok) toast("Không mở được điều hoà") }
    }

    override fun openControlPanel(tab: Int) = ControlCenter(this, ecarx, controller, tab).show()
    override fun openControls() = inLauncher { openControlPanel() }

    override fun goHome() = inLauncher {
        if (drawer.visibility == View.VISIBLE) closeDrawer()
        if (canvas.editing) setEditMode(false)
    }

    override fun cycleDriverSeatHeat() = controller.cycleLevel(
        vn.tht.launcher.vehicle.EcarxFunctions.HVAC_SEAT_HEATING, vn.tht.launcher.vehicle.EcarxFunctions.ZONE_ROW_1_LEFT,
        3, "Sưởi ghế lái",
    ) { toast((if (it.ok) "✔ " else "✖ ") + it.message) }

    override fun runDockAction(action: String) {
        val f = vn.tht.launcher.vehicle.EcarxFunctions
        val report: (vn.tht.launcher.vehicle.EcarxController.Result) -> Unit = {
            if (!it.ok) toast(it.message)
        }
        when {
            action == "seat_heat_passenger" -> controller.cycleLevel(
                f.HVAC_SEAT_HEATING, f.ZONE_ROW_1_RIGHT, 3, "Sưởi ghế phụ", report)
            action.startsWith("temp_set_") -> action.removePrefix("temp_set_").toFloatOrNull()
                ?.takeIf { it.isFinite() && it in f.TEMP_MIN..f.TEMP_MAX }?.let { controller.setTemp(it, report) }
            action.startsWith("fan_set_") -> action.removePrefix("fan_set_").toIntOrNull()
                ?.takeIf { it in 0..f.FAN_MAX }?.let {
                    controller.setValue(f.HVAC_FAN_SPEED, f.ZONE_ALL, f.level(f.HVAC_FAN_SPEED, it), "Quạt mức $it", done = report)
                }
            else -> runImageAction(action)
        }
    }

    override fun openWifiSettings() {
        vn.tht.launcher.system.ExternalAppLauncher.launch(this, Intent(Settings.ACTION_WIFI_SETTINGS))
    }

    private var lastStatusAt = 0L

    /** Cập nhật ô trạng thái trên dock (tối đa 1 lần/giây). */
    private fun updateDockStatus(d: VehicleData?) {
        val now = System.currentTimeMillis()
        if (now - lastStatusAt < 1000) return
        lastStatusAt = now
        val tank = prefs.tankLiters.toFloat()
        val seat = lastEcarx?.seatHeatLeft
        val status = DockBar.Status(
            wifi = wifiConnected(),
            coolantC = d?.coolantTempC,
            fuelL = d?.fuelLiters ?: if (tank > 0f) d?.fuelPercent?.let { it / 100f * tank } else null,
            voltage = supplyVoltage(),
            seatHeatLevel = seat,
            passengerSeatHeatLevel = lastEcarx?.seatHeatRight,
            temperatureC = lastEcarx?.tempC,
            fanLevel = lastEcarx?.fanLevel,
            hvacOn = lastEcarx?.hvacOn,
        )
        lastStatus = status
        dock.update(status)
        floatingDock.update(status)
        if (::sideRail.isInitialized) sideRail.update(status)
        topBar.update(status)
    }

    @Suppress("DEPRECATION")
    private fun wifiConnected(): Boolean? = runCatching {
        val cm = getSystemService(android.net.ConnectivityManager::class.java)
        cm.activeNetworkInfo?.let { it.isConnected && it.type == android.net.ConnectivityManager.TYPE_WIFI } ?: false
    }.getOrNull()

    /**
     * Điện áp ắc quy: ưu tiên số xe báo (thuộc tính VPowerInfo của ECARX), không có thì thử
     * thông tin pin hệ thống (mV) mà một số đầu xe dùng. Chỉ nhận 9–16 V từ nguồn thứ hai.
     */
    private fun supplyVoltage(): Float? = lastVehicle?.batteryVoltage ?: runCatching {
        val i = registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        val mv = i?.getIntExtra(android.os.BatteryManager.EXTRA_VOLTAGE, -1) ?: -1
        (mv / 1000f).takeIf { it in 9f..16f }
    }.getOrNull()

    // ---- Ẩn/hiện tất cả ----

    override fun isCollapsed() = prefs.collapsed

    override fun toggleCollapsed() {
        prefs.collapsed = !prefs.collapsed
        applyCollapsed()
        dock.reload()
    }

    /** Ẩn: chỉ còn hình nền và thanh dưới (không có cụm điều hoà, icon nằm giữa). */
    private fun applyCollapsed() {
        val c = prefs.collapsed
        canvas.showOnly(if (c) { s -> s.type == WidgetType.BOTTOM_BAR } else null)
        val dockRow = bars.getValue(WidgetType.BOTTOM_BAR).findViewById<View>(R.id.dock)
        (dockRow.layoutParams as? FrameLayout.LayoutParams)?.let {
            it.gravity = if (c) android.view.Gravity.CENTER else android.view.Gravity.START or android.view.Gravity.CENTER_VERTICAL
            dockRow.layoutParams = it
        }
    }

    /** Các thanh lấy một lần lúc mở (sau đó chúng chuyển qua lại giữa các khung widget). */
    private lateinit var bars: Map<WidgetType, View>

    /** Dọn dẹp: đóng app chạy nền, giữ nhạc đang phát, bản đồ, THT Voice. */
    override fun cleanup() {
        val keep = setOfNotNull(lastMedia?.pkg, navPackage())
        toast("Đang dọn dẹp…")
        Thread {
            val r = vn.tht.launcher.system.Cleaner.clean(this, keep)
            runOnUiThread {
                toast("Đã dọn ${r.apps} ứng dụng" + if (r.freedMb > 0) ", giải phóng ~${r.freedMb} MB RAM" else "")
            }
        }.start()
    }

    // ---- Widget ảnh ----

    private var pendingImage: Pair<java.io.File, (Boolean) -> Unit>? = null

    @Suppress("DEPRECATION")
    override fun pickImage(dest: java.io.File, done: (Boolean) -> Unit) {
        pendingImage = dest to done
        runCatching {
            startActivityForResult(Intent(Intent.ACTION_OPEN_DOCUMENT).addCategory(Intent.CATEGORY_OPENABLE).setType("image/*"), REQ_IMAGE)
        }.onFailure { pendingImage = null; toast("Đầu xe không có trình chọn file") }
    }

    /** Lưu ảnh thu nhỏ vừa màn hình (widget không thể lớn hơn màn), PNG để giữ nền trong suốt. */
    private fun saveImage(uri: Uri, dest: java.io.File): Boolean = runCatching {
        val (sw, sh) = vn.tht.launcher.ui.Bitmaps.screen(this)
        val src = vn.tht.launcher.ui.Bitmaps.decode(this, uri, sh, sh) ?: return false
        val bmp = vn.tht.launcher.ui.Bitmaps.fit(src, sw, sh)
        dest.outputStream().use { bmp.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, it) }
        true
    }.getOrDefault(false)

    override fun runImageAction(id: String) {
        val F = vn.tht.launcher.vehicle.EcarxFunctions

        val r: (vn.tht.launcher.vehicle.EcarxController.Result) -> Unit = { toast((if (it.ok) "✔ " else "✖ ") + it.message) }
        when (id) {
            "climate" -> openClimate()
            "controls" -> openControlPanel(0)
            "power" -> controller.toggleHvacPower(r)
            "temp_up" -> controller.tempStep(+1, r)
            "temp_down" -> controller.tempStep(-1, r)
            "fan_up" -> controller.fanStep(+1, r)
            "fan_down" -> controller.fanStep(-1, r)
            "roof_open" -> controller.setValue(F.BCM_WINDOW, F.ZONE_ROOF, F.ON, "Mở cửa sổ trời", vn.tht.launcher.vehicle.EcarxController.Safety.BELOW_80, r)
            "roof_close" -> controller.setValue(F.BCM_WINDOW, F.ZONE_ROOF, F.OFF, "Đóng cửa sổ trời", done = r)
            "curtain_open" -> controller.setValue(F.SUN_CURTAIN_CLOSE, F.ZONE_ALL, F.OFF, "Mở rèm", done = r)
            "curtain_close" -> controller.setValue(F.SUN_CURTAIN_CLOSE, F.ZONE_ALL, F.ON, "Đóng rèm", done = r)
            "trunk" -> controller.setValue(F.BCM_DOOR, F.ZONE_DOOR_REAR, F.ON, "Mở cốp", vn.tht.launcher.vehicle.EcarxController.Safety.PARKED_IN_P, r)
            "prev" -> mediaPrevious()
            "next" -> mediaNext()
            "play" -> mediaPlayPause()
            "vol_up" -> audio?.adjustStreamVolume(android.media.AudioManager.STREAM_MUSIC, android.media.AudioManager.ADJUST_RAISE, android.media.AudioManager.FLAG_SHOW_UI)
            "vol_down" -> audio?.adjustStreamVolume(android.media.AudioManager.STREAM_MUSIC, android.media.AudioManager.ADJUST_LOWER, android.media.AudioManager.FLAG_SHOW_UI)
            "mute" -> audio?.adjustStreamVolume(android.media.AudioManager.STREAM_MUSIC, android.media.AudioManager.ADJUST_TOGGLE_MUTE, android.media.AudioManager.FLAG_SHOW_UI)
        }
    }

    override fun barView(type: WidgetType): View = bars.getValue(if (type.isBar) type else WidgetType.BOTTOM_BAR)

    // ---- Nhập chính xác vị trí / kích thước ----

    private fun exactDialog(spec: WidgetSpec) {
        Dialogs.form(this, "Kích thước & vị trí: ${spec.type.title}", listOf(
            Dialogs.Field("X (từ trái)", spec.x.toString(), number = true),
            Dialogs.Field("Y (từ trên)", spec.y.toString(), number = true),
            Dialogs.Field("Rộng", spec.w.toString(), number = true),
            Dialogs.Field("Cao", spec.h.toString(), number = true),
        ), listOf(
            Dialogs.FormButton("Huỷ"),
            // Vuông: cao = rộng (theo số đang nhập ở ô Rộng)
            Dialogs.FormButton("Vuông") { (x, y, w, _) -> applyExact(spec, x, y, w, w) },
            Dialogs.FormButton("Áp dụng", Dialogs.Kind.PRIMARY) { (x, y, w, h) -> applyExact(spec, x, y, w, h) },
        ), note = "Đơn vị dp trên khung ${HomeLayout.DESIGN_W}×${HomeLayout.DESIGN_H}, nhỏ nhất ${HomeLayout.MIN_SIZE}", columns = 2)
    }

    private fun applyExact(spec: WidgetSpec, x: String, y: String, w: String, h: String) {
        val nw = (w.toIntOrNull() ?: spec.w).coerceIn(HomeLayout.MIN_SIZE, HomeLayout.DESIGN_W)
        val nh = (h.toIntOrNull() ?: spec.h).coerceIn(HomeLayout.MIN_SIZE, HomeLayout.DESIGN_H)
        spec.w = nw; spec.h = nh
        spec.x = (x.toIntOrNull() ?: spec.x).coerceIn(0, HomeLayout.DESIGN_W - nw)
        spec.y = (y.toIntOrNull() ?: spec.y).coerceIn(0, HomeLayout.DESIGN_H - nh)
        HomeLayout.save(this, canvas.specs())
        // Thẻ nhạc đổi ngang/dọc theo tỉ lệ mới: dựng lại
        buildWidgets(HomeLayout.load(this)); canvas.setEditing(true)
    }

    // ---- Widget: dẫn đường, âm lượng, độ sáng, cài đặt hệ thống ----

    override fun lastStatus() = lastStatus

    override fun openSystemSettings(action: String) {
        if (!vn.tht.launcher.system.ExternalAppLauncher.launch(this, Intent(action))) toast("Không mở được cài đặt")
    }

    private val navCandidates = listOf(
        "com.autonavi.amapauto", "vn.vietmap.live", "com.google.android.apps.maps", "com.waze", "ru.yandex.yandexnavi", "ru.yandex.yandexmaps",
    )

    /** Gói + tên app đã dò; giữ lại vì widget hỏi mỗi giây (mỗi lần dò là vài lời gọi PackageManager). */
    private class Resolved(val pkg: String?, val label: String?)
    private var navCache: Resolved? = null
    private var radioCache: Resolved? = null

    private fun nav(): Resolved = navCache ?: run {
        val p = prefs.navApp.takeIf { it.isNotBlank() && appLabel(it) != null }
            ?: navCandidates.firstOrNull { appLabel(it) != null }
        Resolved(p, p?.let { appLabel(it) }).also { navCache = it }
    }

    private fun navPackage(): String? = nav().pkg

    override fun navAppLabel(): String? = nav().label

    override fun navLaunchIntent(): Intent? = navPackage()?.let { packageManager.getLaunchIntentForPackage(it) }

    override fun canEmbedMap() = prefs.sp.getBoolean("embed_map", true) && PlatformSignature.verify(this) &&
        packageManager.hasSystemFeature(android.content.pm.PackageManager.FEATURE_ACTIVITIES_ON_SECONDARY_DISPLAYS)

    override fun weather() = vn.tht.launcher.system.Weather.current

    /** App radio của đầu xe: gói ECARX trước, rồi bất kỳ app nào có "radio" trong tên gói. */
    private fun radioPackage(): String? = radio().pkg

    private fun radio(): Resolved = radioCache ?: findRadio().let { p ->
        Resolved(p, p?.let { appLabel(it) }).also { if (allApps.isNotEmpty()) radioCache = it }
    }

    private fun findRadio(): String? {
        val known = listOf("com.ecarx.radio", "com.ecarx.radio.service", "ecarx.radio", "sw.com.dabdrmradio")
        known.firstOrNull { packageManager.getLaunchIntentForPackage(it) != null }?.let { return it }
        return allApps.firstOrNull { it.packageName.contains("radio", ignoreCase = true) }?.packageName
    }

    override fun openRadio() {
        val p = radioPackage() ?: return toast("Không tìm thấy app radio")
        launch(p)
    }

    override fun radioLabel(): String? = radio().label

    override fun openNav() {
        val p = navPackage()
        if (p == null) chooseNavApp() else launch(p)
    }

    override fun chooseNavApp() = pickApp { prefs.navApp = it; navCache = null; toast("Bản đồ: ${appLabel(it) ?: it}") }

    private val audio by lazy { getSystemService(android.media.AudioManager::class.java) }

    override fun volume(): Float? = audio?.let {
        it.getStreamVolume(android.media.AudioManager.STREAM_MUSIC).toFloat() /
            it.getStreamMaxVolume(android.media.AudioManager.STREAM_MUSIC).coerceAtLeast(1)
    }

    override fun setVolume(v: Float) {
        val a = audio ?: return
        val max = a.getStreamMaxVolume(android.media.AudioManager.STREAM_MUSIC)
        runCatching { a.setStreamVolume(android.media.AudioManager.STREAM_MUSIC, Math.round(v * max), 0) }
    }

    override fun brightness(): Float? = runCatching {
        Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS) / 255f
    }.getOrNull()

    /** Đổi độ sáng hệ thống nếu được phép (bản ký platform có sẵn quyền), không thì chỉ độ sáng launcher. */
    override fun setBrightness(v: Float) {
        val value = (v * 255).toInt().coerceIn(5, 255)
        if (Settings.System.canWrite(this)) {
            runCatching {
                Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL)
                Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, value)
            }
        } else {
            window.attributes = window.attributes.apply { screenBrightness = value / 255f }
        }
    }

    override fun openClimate() = vn.tht.launcher.controls.ClimatePopup(this, ecarx, controller, overlay = !inFront).show()

    override fun openStats() {
        vn.tht.launcher.system.ExternalAppLauncher.launch(this, Intent(this, vn.tht.launcher.stats.StatsActivity::class.java))
    }

    override fun currentTrip() = (application as LauncherApp).recorder.current

    /** Quãng đường hôm nay (chuyến đã lưu + chuyến đang chạy); đọc file tối đa 1 lần/phút. */
    override fun todayKm(): Float {
        val now = System.currentTimeMillis()
        if (now - todayKmCache.second > 60_000) {
            val cal = java.util.Calendar.getInstance().apply {
                set(java.util.Calendar.HOUR_OF_DAY, 0); set(java.util.Calendar.MINUTE, 0); set(java.util.Calendar.SECOND, 0)
            }
            val saved = vn.tht.launcher.stats.StatsStore(this).trips().filter { it.start >= cal.timeInMillis }
                .sumOf { it.distanceKm.toDouble() }.toFloat()
            todayKmCache = saved to now
        }
        return todayKmCache.first + (currentTrip()?.distanceKm ?: 0f)
    }

    /** Quyền đọc dữ liệu xe loại "dangerous" (tốc độ, vòng tua, nhiệt độ máy…): xin một lần. */
    private fun requestCarPermissions() {
        val wanted = arrayOf(
            "android.car.permission.CAR_SPEED", "android.car.permission.CAR_ENERGY",
            "android.car.permission.CAR_ENGINE_DETAILED",
        ).filter { checkSelfPermission(it) != android.content.pm.PackageManager.PERMISSION_GRANTED }
        if (wanted.isNotEmpty() && !prefs.sp.getBoolean("car_perm_asked", false)) {
            prefs.sp.edit().putBoolean("car_perm_asked", true).apply()
            runCatching { requestPermissions(wanted.toTypedArray(), 1) }
        }
    }

    @Suppress("DEPRECATION")
    override fun openSettings() {
        if (!vn.tht.launcher.system.ExternalAppLauncher.leaveSplit()) return
        startActivityForResult(Intent(this, SettingsActivity::class.java), REQ_SETTINGS,
            vn.tht.launcher.system.ExternalAppLauncher.fullscreenOptions(this))
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (appWidgets.onResult(requestCode, resultCode)) return
        if (requestCode == REQ_IMAGE) {
            val p = pendingImage ?: return
            pendingImage = null
            val uri = data?.data
            if (resultCode != RESULT_OK || uri == null) return
            Thread {
                val ok = saveImage(uri, p.first)
                runOnUiThread { p.second(ok) }
            }.start()
            return
        }
        if (requestCode == REQ_REPORT) {
            val uri = data?.data
            if (resultCode != RESULT_OK || uri == null) return
            io.post {
                val ok = runCatching {
                    contentResolver.openOutputStream(uri)?.use { it.write(buildReport().toByteArray()) } != null
                }.getOrDefault(false)
                handler.post { toast(if (ok) "Đã lưu báo cáo chẩn đoán" else "Không ghi được file") }
            }
            return
        }
        if (requestCode != REQ_SETTINGS) return
        if (resultCode == SettingsActivity.RESULT_DIAGNOSTICS) { diagnostics(); return }
        applyAppearance()
        if (resultCode == SettingsActivity.RESULT_EDIT_HOME) setEditMode(true)
    }

    /** Tóm tắt kết nối với xe; nút lưu báo cáo đầy đủ ra file (USB…) để gửi nhà phát triển. */
    fun diagnostics() {
        val v = lastVehicle
        val msg = buildString {
            appendLine("Phiên bản: ${appVersion()}")
            appendLine("Chữ ký platform: ${if (PlatformSignature.verify(this@MainActivity)) "KHỚP – ghi được" else "không khớp – chỉ đọc"}")
            appendLine("ECARX AdaptAPI: ${ecarx.status}")
            appendLine("Dữ liệu xe (VHAL): ${if (v != null) "có" else "không"}" + (v?.batteryVoltage?.let { " · ắc quy ${it} V" } ?: ""))
            appendLine("Radio xe: ${if (radio.available) radio.state?.label() ?: "đã nối" else "không có dịch vụ"}")
            appendLine("Trợ năng THT: ${if (vn.tht.launcher.system.NavAssistService.isEnabled(this@MainActivity)) "bật" else "tắt"}")
            vn.tht.launcher.home.EmbeddedMap.lastError?.let { appendLine("Nhúng bản đồ: lỗi – $it") }
        }
        Dialogs.show(this, "Chẩn đoán", Ui.text(this, msg.trim(), 17f).apply { setLineSpacing(0f, 1.25f) }, listOf(
            Dialogs.Button("Đóng"),
            Dialogs.Button("Lưu báo cáo đầy đủ", Dialogs.Kind.PRIMARY) { saveReport() },
        ))
    }

    private fun invalidateTree(v: View) {
        v.invalidate()
        (v as? android.view.ViewGroup)?.let { g -> for (i in 0 until g.childCount) invalidateTree(g.getChildAt(i)) }
    }

    private fun appVersion() = runCatching {
        packageManager.getPackageInfo(packageName, 0).let { "${it.versionName} (${it.versionCode})" }
    }.getOrDefault("?")

    private fun saveReport() {
        val name = "tht-chan-doan-" + java.text.SimpleDateFormat("yyyyMMdd-HHmm", java.util.Locale.US).format(java.util.Date()) + ".txt"
        runCatching {
            startActivityForResult(Intent(Intent.ACTION_CREATE_DOCUMENT).addCategory(Intent.CATEGORY_OPENABLE)
                .setType("text/plain").putExtra(Intent.EXTRA_TITLE, name), REQ_REPORT)
        }.onFailure { toast("Đầu xe không có trình lưu file") }
    }

    /** Báo cáo đầy đủ: máy, chữ ký, ECARX (cả catalog), VHAL, nhạc, radio, lớp nổi, bản đồ nhúng. Chạy trên luồng car-io. */
    private fun buildReport(): String = buildString {
        val v = lastVehicle
        appendLine("== THT Launcher – báo cáo chẩn đoán ${java.util.Date()}")
        appendLine("Phiên bản app: ${appVersion()}")
        appendLine("Máy: ${android.os.Build.MANUFACTURER} ${android.os.Build.MODEL} · ${android.os.Build.PRODUCT} · Android ${android.os.Build.VERSION.RELEASE} (API ${android.os.Build.VERSION.SDK_INT})")
        appendLine("Firmware: ${android.os.Build.FINGERPRINT}")
        val dm = resources.displayMetrics
        appendLine("Màn hình: ${dm.widthPixels}×${dm.heightPixels} @${dm.densityDpi}dpi")
        appendLine()
        appendLine("== Chữ ký")
        appendLine("Khớp platform: ${PlatformSignature.verify(this@MainActivity)}")
        appendLine("Firmware: ${PlatformSignature.certSha256(this@MainActivity, "android")}")
        appendLine("App: ${PlatformSignature.certSha256(this@MainActivity, packageName)}")
        appendLine()
        appendLine("== Hợp đồng OEM Coolray")
        appendLine(vn.tht.launcher.vehicle.OemFirmware.diagnosticReport(this@MainActivity))
        appendLine("Default HOME: ${vn.tht.launcher.system.LauncherHome.defaultPackage(this@MainActivity)}")
        appendLine("== Mở ứng dụng ngoài")
        appendLine("Secondary-display activities: ${packageManager.hasSystemFeature(android.content.pm.PackageManager.FEATURE_ACTIVITIES_ON_SECONDARY_DISPLAYS)}")
        appendLine(vn.tht.launcher.system.ExternalAppLauncher.lastReport)
        appendLine(vn.tht.launcher.system.AppWindowPolicy.lastReport)
        appendLine(vn.tht.launcher.system.ExternalAppLauncher.stackReport())
        appendLine("== Chia đôi màn hình")
        appendLine(splitScreen.diagnosticReport())
        appendLine()
        appendLine("== ECARX")
        appendLine("Trạng thái: ${ecarx.status} · ghi=${ecarx.writeAllowed}")
        append(ecarx.report())
        appendLine()
        appendLine("== VHAL (lần đọc gần nhất)")
        appendLine(v?.toString() ?: "(không có)")
        appendLine()
        appendLine("== Nhạc / radio")
        appendLine("Đang phát: ${lastMedia?.let { "${it.title} – ${it.artist} · ${it.source} (${it.pkg}) playing=${it.isPlaying}" } ?: "(không)"}")
        appendLine("Quyền đọc nhạc: ${media.hasPermission()}")
        appendLine("Radio: available=${radio.available} state=${radio.state}")
        appendLine("Đài yêu thích: ${radio.favorites()}")
        appendLine()
        appendLine("== Hệ thống")
        appendLine("Trợ năng THT: ${vn.tht.launcher.system.NavAssistService.isEnabled(this@MainActivity)}")
        appendLine("Vẽ trên app khác: ${android.provider.Settings.canDrawOverlays(this@MainActivity)}")
        appendLine("Nhúng bản đồ: bật=${prefs.sp.getBoolean("embed_map", true)} lỗi gần nhất=${vn.tht.launcher.home.EmbeddedMap.lastError ?: "không"}")
        run {
            val real = android.graphics.Point().also { windowManager.defaultDisplay.getRealSize(it) }
            val app = android.graphics.Point().also { windowManager.defaultDisplay.getSize(it) }
            val os = vn.tht.launcher.system.Overscan.applied(prefs)
            appendLine("Thanh bên: kiểu=${prefs.sp.getString("nav_mode", "buttons")} màn=${real.x}x${real.y} vùng app=${app.x}x${app.y} " +
                "overscan THT=$os thanh hệ thống trái≈${real.x - app.x - os}px chừa màn chính=${vn.tht.launcher.system.NavAssistService.homeInset}px " +
                "policy_control=${Settings.Global.getString(contentResolver, "policy_control")}")
        }
        appendLine("App bản đồ: ${navPackage() ?: "(chưa có)"} · app radio: ${radioPackage() ?: "(chưa có)"}")
        appendLine("Gói ECARX đã cài: " + listOf("ecarx.hvac.app", "ecarx.xsf.mediacenter", "com.ecarx.radio.service", "com.ecarx.radio", "ecarx.launcher3")
            .joinToString(", ") { p -> p + if (runCatching { packageManager.getPackageInfo(p, 0) }.isSuccess) "✔" else "✖" })
        appendLine()
        appendLine("== Danh mục hằng số AdaptAPI của xe")
        append(ecarx.constantsCatalog())
        // Bản debug với xe giả: chạy cùng cách liệt kê trên lớp của chính app để chắc nó hoạt động
        if (vn.tht.launcher.vehicle.DemoCarFunction.enabled(this@MainActivity)) {
            appendLine("-- tự kiểm tra (bản debug): EcarxFunctions")
            append(ecarx.catalog(classLoader, "vn.tht.launcher.vehicle.EcarxFunctions"))
        }
    }

    override fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    // ---- Chia đôi màn hình ----

    private val splitScreen by lazy {
        SplitScreen(this, ::toast).apply {
            bottomInset = {
                val height = Bitmaps.screen(this@MainActivity).second
                if (prefs.dockOverlay != "off") height - (dockBounds?.top ?: height - Ui.dp(this@MainActivity, 100)) else 0
            }
            usableBounds = {
                val root = findViewById<View>(R.id.root)
                val size = Bitmaps.screen(this@MainActivity)
                val inset = runCatching {
                    val result = Rect()
                    val service = Class.forName("android.view.WindowManagerGlobal")
                        .getMethod("getWindowManagerService").invoke(null)
                    Class.forName("android.view.IWindowManager").getMethod("getStableInsets",
                        Int::class.javaPrimitiveType!!, Rect::class.java).invoke(service, 0, result)
                    result.left
                }.getOrNull() ?: (root.rootWindowInsets?.stableInsetLeft ?: 0)
                // The launcher itself can be moved into the secondary pane while the pair opens.
                // Its live window rectangle is then half a display, not the area we are splitting.
                Rect(maxOf(inset, vn.tht.launcher.system.NavAssistService.homeInset), 0, size.first, size.second)
            }
            onStateChanged = { refreshFloatingDock() }
        }
    }

    /**
     * Chia đôi nhanh từ thanh bên: app đang dùng sang trái, chọn app bên phải (đang ở launcher thì chọn cả
     * hai). Đang chia đôi thì bấm lần nữa để thoát. Cặp không lưu; muốn lưu dùng widget/nút "Chia đôi".
     */
    private fun quickSplit(current: String?) {
        if (splitScreen.hasWork) { splitScreen.close(); return }
        inLauncher {
            fun open(left: String) = pickApp { right -> splitScreen.open(vn.tht.launcher.split.Split.new(left, right)) }
            if (current != null) open(current) else { toast("Chọn app bên trái"); pickApp { open(it) } }
        }
    }

    // ---- Dock nổi trên app ----

    private val floatingDock by lazy { FloatingDock(this, this) }

    /** Vị trí thanh dưới trên màn hình chính, để dock nổi đặt đúng chỗ đó. */
    private var dockBounds: Rect? = null

    private fun rememberDockBounds() {
        val v = bars.getValue(WidgetType.BOTTOM_BAR)
        if (v.isShown && v.width > 0) {
            val p = IntArray(2).also { v.getLocationOnScreen(it) }
            dockBounds = Rect(p[0], p[1], p[0] + v.width, p[1] + v.height)
        }
    }

    /** Hiện dock nổi khi launcher bị che, theo Cài đặt (mọi lúc / chỉ khi đang chia đôi). */
    private fun refreshFloatingDock() {
        val want = !inFront && when (prefs.dockOverlay) {
            "always" -> true
            "split" -> splitScreen.hasWork
            else -> false
        }
        if (!want) return floatingDock.hide()
        val (w, h) = Bitmaps.screen(this)
        floatingDock.show(dockBounds ?: Rect(0, h - Ui.dp(this, 100), w, h))
        lastStatusAt = 0L
        updateDockStatus(lastVehicle)
    }

    /** Hành động cần màn hình launcher (ngăn kéo app, bảng điều khiển): đưa launcher lên trước nếu đang bị che. */
    private fun inLauncher(action: () -> Unit) {
        if (!vn.tht.launcher.system.ExternalAppLauncher.leaveSplit()) return
        if (inFront) return action()
        startActivity(Intent(this, MainActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT),
            vn.tht.launcher.system.ExternalAppLauncher.fullscreenOptions(this))
        handler.postDelayed(action, 350)
    }

    private val splitUi: SplitUi by lazy {
        SplitUi(this, object : SplitUi.Host {
            override val splitScreen get() = this@MainActivity.splitScreen
            override fun pickApp(onPicked: (String) -> Unit) = this@MainActivity.pickApp(onPicked)
            override fun appLabel(pkg: String) = this@MainActivity.appLabel(pkg)
            override fun toast(msg: String) = this@MainActivity.toast(msg)
            override fun addSplitWidget(id: String) {
                addWidget(WidgetSpec.new(WidgetType.SPLIT).apply { pkg = id })
                HomeLayout.save(this@MainActivity, canvas.specs())
                setEditMode(true)
                toast("Đã thêm lên màn hình chính – kéo để đặt chỗ")
            }
            override fun addSplitToDock(split: Split) =
                dock.addShortcut(Shortcut("split_${split.id}", splitUi.label(split), Shortcut.TYPE_SPLIT, action = split.id))
            override fun splitsChanged() {
                val editing = canvas.editing
                HomeLayout.save(this@MainActivity, canvas.specs())
                buildWidgets(HomeLayout.load(this@MainActivity))
                canvas.setEditing(editing)
                dock.reload(); topBar.reload(); sideRail.reload()
            }
        })
    }

    override fun openSplit(id: String) {
        val s = SplitStore.get(this, id) ?: return toast("Cặp chia đôi này đã bị xoá")
        splitScreen.toggle(s)
    }

    override fun editSplit(id: String) = splitUi.edit(id)

    override fun newSplit(onCreated: (String) -> Unit) = splitUi.create { onCreated(it.id) }

    override fun chooseSplit(onPicked: (String, String) -> Unit) = splitUi.choose { onPicked(it.id, splitUi.label(it)) }

    private fun launch(pkg: String) {
        val intent = packageManager.getLaunchIntentForPackage(pkg) ?: return toast("Không tìm thấy ứng dụng")
        if (splitScreen.hasWork && !splitScreen.close()) return
        if (!vn.tht.launcher.system.ExternalAppLauncher.launch(this, intent)) {
            toast("Không mở được ứng dụng")
        }
    }

    private fun openAppInfo(app: AppEntry) {
        startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:${app.packageName}")))
    }

    private companion object {
        const val ECARX_POLL_MS = 2000L
        const val REQ_SETTINGS = 10
        const val REQ_IMAGE = 11
        const val REQ_REPORT = 12
    }
}
