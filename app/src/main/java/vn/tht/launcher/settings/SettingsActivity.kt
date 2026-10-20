package vn.tht.launcher.settings

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import vn.tht.launcher.apps.AppsRepository
import vn.tht.launcher.home.HomeLayout
import vn.tht.launcher.ui.Palette
import vn.tht.launcher.ui.Ui
import java.io.File

/** Cài đặt THT Launcher. Mỗi thay đổi ghi ngay vào [Prefs]; màn hình chính tự áp dụng khi quay lại. */
class SettingsActivity : Activity() {

    private lateinit var prefs: Prefs
    private lateinit var list: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = Prefs(this)
        render()
    }

    private fun render() {
        // Theme "tự động" giữ ngày/đêm hiện tại của màn hình chính
        Palette.apply(prefs, when (prefs.themeMode) { "day" -> false; "night" -> true; else -> Palette.night })
        list = Ui.vbox(this, 24)
        window.decorView.setBackgroundColor(Palette.bg)
        setContentView(ScrollView(this).apply { addView(list) })

        list.addView(Ui.hbox(this).apply {
            addView(Ui.text(this@SettingsActivity, "Cài đặt THT Launcher", 30f, bold = true), Ui.weight())
            addView(Ui.button(this@SettingsActivity, "Xong") { finish() })
        })

        section("Giao diện")
        choice("Theme", mapOf("auto" to "Tự động (theo đèn xe)", "day" to "Ngày", "night" to "Đêm"), { prefs.themeMode }) { prefs.themeMode = it }
        choice("Màu nhấn", Palette.ACCENTS.mapValues { it.value.first }, { prefs.accent }) { prefs.accent = it }
        choice("Nền ban ngày", Palette.BACKGROUNDS.mapValues { it.value.first }, { prefs.backgroundDay }) { prefs.backgroundDay = it }
        choice("Nền ban đêm", Palette.BACKGROUNDS.mapValues { it.value.first }, { prefs.backgroundNight }) { prefs.backgroundNight = it }
        action("Ảnh nền riêng – ngày", wallpaperState(DAY)) { pickWallpaper(DAY) }
        action("Ảnh nền riêng – đêm", wallpaperState(NIGHT)) { pickWallpaper(NIGHT) }
        action("Bộ biểu tượng", vn.tht.launcher.ui.Icons.packName ?: "Mặc định · chạm để nạp file .zip") { iconPack() }
        slider("Độ đặc nền widget", 0, 100, "%", { prefs.cardOpacity }) { prefs.cardOpacity = it }
        slider("Tỉ lệ giao diện", 75, 150, "%", { prefs.uiScale }) { prefs.uiScale = it }

        section("Dock")
        choice("Cỡ dock", mapOf("compact" to "Gọn", "normal" to "Thường", "large" to "Lớn"), { prefs.dockSize }) { prefs.dockSize = it }
        toggle("Hiện tên dưới biểu tượng", { prefs.dockLabels }) { prefs.dockLabels = it }
        slider("Độ đặc dock", 0, 100, "%", { prefs.dockOpacity }) { prefs.dockOpacity = it }
        action("Dùng mẫu thanh trạng thái", "Wi-Fi · Ứng dụng · Nhiệt độ máy · Xăng · Điện áp · Sưởi ghế · Home") {
            confirm("Thay dock hiện tại bằng mẫu thanh trạng thái?") {
                fun sc(id: String, l: String, type: String, metric: String = "") =
                    vn.tht.launcher.shortcuts.Shortcut(id, l, type, action = metric)
                val status = vn.tht.launcher.shortcuts.Shortcut.TYPE_STATUS
                vn.tht.launcher.shortcuts.ShortcutStore.save(this, listOf(
                    sc("status_wifi", "Wi-Fi", status, "wifi"),
                    sc("apps", "Ứng dụng", vn.tht.launcher.shortcuts.Shortcut.TYPE_APPS),
                    sc("status_coolant", "Nhiệt độ máy", status, "coolant"),
                    sc("status_fuel_l", "Xăng", status, "fuel_l"),
                    sc("status_voltage", "Điện áp", status, "voltage"),
                    sc("status_seat_heat", "Sưởi ghế lái", status, "seat_heat"),
                    sc("status_home", "Màn hình chính", status, "home"),
                    sc("controls", "Điều khiển", vn.tht.launcher.shortcuts.Shortcut.TYPE_CONTROLS),
                    sc("settings", "Cài đặt", vn.tht.launcher.shortcuts.Shortcut.TYPE_SETTINGS),
                ))
                prefs.dockLabels = false
                prefs.dockSize = "compact"
                toast("Đã đổi dock")
            }
        }

        section("Đồng hồ")
        toggle("Định dạng 24 giờ", { prefs.clock24h }) { prefs.clock24h = it }
        toggle("Hiện ngày tháng", { prefs.clockShowDate }) { prefs.clockShowDate = it }

        section("Màn hình chính")
        action("Sửa widget", "Di chuyển, đổi cỡ, thêm, xoá") { setResult(RESULT_EDIT_HOME); finish() }
        action("Khôi phục bố cục mặc định", "Giữ nguyên dock và cài đặt") {
            confirm("Đưa widget về bố cục mặc định?") { HomeLayout.reset(this); toast("Đã khôi phục") }
        }

        section("Khởi động & hệ thống")
        action("Đặt làm màn hình chính", "Chọn THT Launcher → Luôn luôn") {
            runCatching { startActivity(Intent(Settings.ACTION_HOME_SETTINGS)) }
                .onFailure { startActivity(Intent(Settings.ACTION_SETTINGS)) }
        }
        toggle("Mở launcher khi đầu xe khởi động", { prefs.openOnBoot }) { prefs.openOnBoot = it }
        toggle("Toàn màn hình (ẩn thanh hệ thống)", { prefs.fullScreen }) { prefs.fullScreen = it }
        action("Ứng dụng tự mở khi khởi động", autostartSummary()) { editAutostart() }
        slider("Độ trễ giữa các app", 0, 30, " s", { prefs.autostartDelaySec }) { prefs.autostartDelaySec = it }
        toggle("Quay về màn hình chính sau khi mở", { prefs.autostartReturnHome }) { prefs.autostartReturnHome = it }
        action("Tự phát nhạc sau khi khởi động", appLabel(prefs.autoplayPlayer) ?: "Không") { pickPlayer() }

        section("Bảo vệ màn hình")
        toggle("Bật bảo vệ màn hình", { prefs.saverEnabled }) { prefs.saverEnabled = it }
        slider("Sau khi không chạm", 1, 30, " phút", { prefs.saverMinutes }) { prefs.saverMinutes = it }
        slider("Độ tối", 0, 90, "%", { prefs.saverDim }) { prefs.saverDim = it }
        toggle("Chỉ khi xe đỗ", { prefs.saverParkedOnly }) { prefs.saverParkedOnly = it }
        toggle("Hiện bài đang phát", { prefs.saverShowTrack }) { prefs.saverShowTrack = it }

        section("Tự động hoá sau khi nổ máy")
        val a = vn.tht.launcher.automation.AutomationPrefs(prefs)
        toggle("Bật tự động hoá", { a.enabled }) { a.enabled = it }
        slider("Chỉ chạy khi đã đỗ ít nhất (0 = mỗi lần nổ máy)", 0, 240, " phút", { a.parkedMinutes }) { a.parkedMinutes = it }
        toggle("Bật điều hoà AUTO", { a.climateAuto }) { a.climateAuto = it }
        slider("Sưởi khi ngoài trời dưới", -20, 25, "°C", { a.heatBelowC }) { a.heatBelowC = it }
        slider("Sưởi ghế lái (0 = không)", 0, 3, "", { a.heatDriver }) { a.heatDriver = it }
        slider("Sưởi ghế phụ (0 = không)", 0, 3, "", { a.heatPassenger }) { a.heatPassenger = it }
        slider("Sưởi vô lăng (0 = không)", 0, 3, "", { a.heatWheel }) { a.heatWheel = it }
        slider("Tắt sưởi sau (0 = không tắt)", 0, 60, " phút", { a.heatOffMinutes }) { a.heatOffMinutes = it }
        slider("Làm mát khi ngoài trời từ", 15, 45, "°C", { a.ventAboveC }) { a.ventAboveC = it }
        slider("Làm mát ghế lái (0 = không)", 0, 3, "", { a.ventDriver }) { a.ventDriver = it }
        slider("Làm mát ghế phụ (0 = không)", 0, 3, "", { a.ventPassenger }) { a.ventPassenger = it }
        slider("Tắt làm mát sau (0 = không tắt)", 0, 60, " phút", { a.ventOffMinutes }) { a.ventOffMinutes = it }
        toggle("Xả nóng: hé cửa sổ trời + bật quạt khi trời nóng", { a.airOut }) { a.airOut = it }
        slider("Mức quạt khi xả nóng", 1, 7, "", { a.airOutFan }) { a.airOutFan = it }
        slider("Đóng cửa sổ trời sau", 1, 30, " phút", { a.airOutMinutes }) { a.airOutMinutes = it }
        slider("Đóng cửa sổ trời khi chạy từ (0 = không)", 0, 120, " km/h", { a.closeRoofAtSpeed }) { a.closeRoofAtSpeed = it }

        section("Xe")
        action("Thống kê chuyến đi", "Quãng đường, tiêu hao, đổ xăng, bảo dưỡng") {
            startActivity(Intent(this, vn.tht.launcher.stats.StatsActivity::class.java))
        }
        toggle("Ghi thống kê chuyến đi", { prefs.sp.getBoolean("stats_on", true) }) { prefs.sp.edit().putBoolean("stats_on", it).apply() }
        action("Dung tích bình xăng", if (prefs.tankLiters > 0) "${prefs.tankLiters} lít" else "Theo xe báo") { editTank() }

        if ((applicationInfo.flags and android.content.pm.ApplicationInfo.FLAG_DEBUGGABLE) != 0) {
            section("Gỡ lỗi (chỉ bản debug)")
            toggle("Chế độ demo (xe giả khi không có ECARX)", { prefs.sp.getBoolean("demo_mode", false) }) {
                prefs.sp.edit().putBoolean("demo_mode", it).apply()
                toast("Khởi động lại launcher để áp dụng")
            }
        }

        section("Quyền")
        action("Quyền đọc nhạc", "Cho phép THT Launcher đọc bài đang phát") {
            startActivity(Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS))
        }

        section("Sao lưu")
        action("Xuất cài đặt", "Cài đặt, bố cục widget và dock ra file JSON") {
            startActivityForResult(Intent(Intent.ACTION_CREATE_DOCUMENT).addCategory(Intent.CATEGORY_OPENABLE)
                .setType("application/json").putExtra(Intent.EXTRA_TITLE, "tht-launcher-backup.json"), REQ_EXPORT)
        }
        action("Nhập cài đặt", "Thay toàn bộ cài đặt hiện tại") {
            startActivityForResult(Intent(Intent.ACTION_OPEN_DOCUMENT).addCategory(Intent.CATEGORY_OPENABLE)
                .setType("*/*"), REQ_IMPORT)
        }
    }

    // ---- Các loại dòng ----

    private fun section(title: String) {
        list.addView(Ui.text(this, title, 16f, dim = true, bold = true).apply {
            setPadding(0, Ui.dp(context, 28), 0, Ui.dp(context, 8))
            setTextColor(Palette.accent)
        })
    }

    private fun row(title: String, summary: String?, right: View? = null, onClick: (() -> Unit)? = null): LinearLayout {
        val r = Ui.hbox(this).apply {
            background = Ui.card(context, 14)
            setPadding(Ui.dp(context, 20), Ui.dp(context, 14), Ui.dp(context, 20), Ui.dp(context, 14))
            addView(Ui.vbox(context).apply {
                addView(Ui.text(context, title, 20f))
                if (summary != null) addView(Ui.text(context, summary, 15f, dim = true))
            }, Ui.weight())
            if (right != null) addView(right)
            if (onClick != null) setOnClickListener { onClick() }
        }
        list.addView(r, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
            bottomMargin = Ui.dp(this@SettingsActivity, 8)
        })
        return r
    }

    private fun action(title: String, summary: String?, onClick: () -> Unit) = row(title, summary, null, onClick)

    private fun toggle(title: String, get: () -> Boolean, set: (Boolean) -> Unit) {
        val sw = Switch(this).apply { isChecked = get(); setOnCheckedChangeListener { _, v -> set(v) } }
        row(title, null, sw) { sw.toggle() }
    }

    private fun choice(title: String, options: Map<String, String>, get: () -> String, set: (String) -> Unit) {
        lateinit var r: LinearLayout
        r = row(title, options[get()] ?: get()) {
            val keys = options.keys.toList()
            AlertDialog.Builder(this).setTitle(title)
                .setSingleChoiceItems(options.values.toTypedArray(), keys.indexOf(get())) { d, i ->
                    set(keys[i]); d.dismiss(); render()
                }.show()
        }
    }

    private fun slider(title: String, min: Int, max: Int, unit: String, get: () -> Int, set: (Int) -> Unit) {
        val value = Ui.text(this, "${get()}$unit", 18f).apply { gravity = Gravity.END }
        val bar = SeekBar(this).apply {
            this.max = max - min
            progress = get() - min
            setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(s: SeekBar?, p: Int, fromUser: Boolean) {
                    value.text = "${p + min}$unit"
                    if (fromUser) set(p + min)
                }
                override fun onStartTrackingTouch(s: SeekBar?) {}
                override fun onStopTrackingTouch(s: SeekBar?) {}
            })
        }
        list.addView(Ui.vbox(this).apply {
            background = Ui.card(context, 14)
            setPadding(Ui.dp(context, 20), Ui.dp(context, 14), Ui.dp(context, 20), Ui.dp(context, 6))
            addView(Ui.hbox(context).apply {
                addView(Ui.text(context, title, 20f), Ui.weight())
                addView(value)
            })
            addView(bar, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Ui.dp(context, 48)))
        }, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
            bottomMargin = Ui.dp(this@SettingsActivity, 8)
        })
    }

    // ---- Hộp thoại ----

    private fun confirm(msg: String, ok: () -> Unit) {
        AlertDialog.Builder(this).setMessage(msg).setPositiveButton("Đồng ý") { _, _ -> ok() }
            .setNegativeButton("Huỷ", null).show()
    }

    private fun toast(m: String) = Toast.makeText(this, m, Toast.LENGTH_SHORT).show()

    private fun appLabel(pkg: String): String? = if (pkg.isBlank()) null else runCatching {
        packageManager.getApplicationLabel(packageManager.getApplicationInfo(pkg, 0)).toString()
    }.getOrDefault(pkg)

    private fun autostartSummary() =
        prefs.autostartApps.mapNotNull { appLabel(it) }.joinToString(", ").ifEmpty { "Không có" }

    private fun editAutostart() {
        val apps = AppsRepository.load(this)
        val chosen = prefs.autostartApps.toMutableList()
        val checked = apps.map { it.packageName in chosen }.toBooleanArray()
        AlertDialog.Builder(this).setTitle("Ứng dụng tự mở (theo thứ tự chọn)")
            .setMultiChoiceItems(apps.map { it.label }.toTypedArray(), checked) { _, i, on ->
                val p = apps[i].packageName
                if (on) { if (p !in chosen) chosen += p } else chosen -= p
            }
            .setPositiveButton("Lưu") { _, _ -> prefs.autostartApps = chosen; render() }
            .setNegativeButton("Huỷ", null).show()
    }

    private fun pickPlayer() {
        val apps = AppsRepository.load(this)
        val labels = listOf("Không") + apps.map { it.label }
        AlertDialog.Builder(this).setTitle("Trình phát nhạc")
            .setItems(labels.toTypedArray()) { _, i ->
                prefs.autoplayPlayer = if (i == 0) "" else apps[i - 1].packageName
                render()
            }.show()
    }

    private fun editTank() {
        val input = EditText(this).apply {
            inputType = android.text.InputType.TYPE_CLASS_NUMBER
            setText(if (prefs.tankLiters > 0) prefs.tankLiters.toString() else "")
            hint = "20–150 lít, để trống = theo xe"
        }
        AlertDialog.Builder(this).setTitle("Dung tích bình xăng (lít)").setView(input)
            .setPositiveButton("Lưu") { _, _ ->
                val v = input.text.toString().toIntOrNull() ?: 0
                if (v != 0 && v !in 20..150) toast("Nhập trong khoảng 20–150 lít") else { prefs.tankLiters = v; render() }
            }.setNegativeButton("Huỷ", null).show()
    }

    private fun iconPack() {
        val items = if (vn.tht.launcher.ui.Icons.packName != null) arrayOf("Nạp bộ khác (.zip)", "Dùng biểu tượng mặc định")
        else arrayOf("Nạp bộ biểu tượng (.zip)")
        AlertDialog.Builder(this).setTitle("Bộ biểu tượng").setItems(items) { _, i ->
            if (i == 0) startActivityForResult(Intent(Intent.ACTION_OPEN_DOCUMENT).addCategory(Intent.CATEGORY_OPENABLE)
                .setType("*/*"), REQ_ICONS)
            else { vn.tht.launcher.ui.Icons.remove(this); prefs.sp.edit().putLong("icons_changed", System.currentTimeMillis()).apply(); render() }
        }.show()
    }

    // ---- Ảnh nền ----

    private fun wallpaperState(which: String) =
        if (Wallpapers.file(this, which).exists()) "Đang dùng ảnh riêng · chạm để đổi/xoá" else "Chưa có · chạm để chọn ảnh"

    private fun pickWallpaper(which: String) {
        if (Wallpapers.file(this, which).exists()) {
            AlertDialog.Builder(this).setItems(arrayOf("Chọn ảnh khác", "Xoá ảnh riêng")) { _, i ->
                if (i == 0) openImage(which) else { Wallpapers.file(this, which).delete(); render() }
            }.show()
        } else openImage(which)
    }

    private fun openImage(which: String) {
        startActivityForResult(Intent(Intent.ACTION_OPEN_DOCUMENT).addCategory(Intent.CATEGORY_OPENABLE)
            .setType("image/*"), if (which == DAY) REQ_WP_DAY else REQ_WP_NIGHT)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val uri: Uri = data?.data ?: return
        if (resultCode != RESULT_OK) return
        when (requestCode) {
            REQ_WP_DAY, REQ_WP_NIGHT -> {
                val ok = Wallpapers.save(this, uri, if (requestCode == REQ_WP_DAY) DAY else NIGHT)
                toast(if (ok) "Đã lưu ảnh nền" else "Không đọc được ảnh")
            }
            REQ_ICONS -> {
                val err = vn.tht.launcher.ui.Icons.install(this, uri)
                prefs.sp.edit().putLong("icons_changed", System.currentTimeMillis()).apply()
                toast(err ?: "Đã nạp ${vn.tht.launcher.ui.Icons.packName}")
            }
            REQ_EXPORT -> toast(if (Backup.export(this, uri)) "Đã xuất cài đặt" else "Không ghi được file")
            REQ_IMPORT -> {
                val err = Backup.import(this, uri)
                toast(err ?: "Đã nhập cài đặt")
            }
        }
        render()
    }

    companion object {
        const val RESULT_EDIT_HOME = 100
        const val DAY = "day"
        const val NIGHT = "night"
        private const val REQ_WP_DAY = 1
        private const val REQ_WP_NIGHT = 2
        private const val REQ_EXPORT = 3
        private const val REQ_IMPORT = 4
        private const val REQ_ICONS = 5
    }
}

/** Ảnh nền riêng, lưu thu nhỏ (≤ 1920 px) trong filesDir. */
object Wallpapers {
    fun file(c: android.content.Context, which: String) = File(c.filesDir, "wallpaper_$which.jpg")

    fun save(c: android.content.Context, uri: Uri, which: String): Boolean = try {
        val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
        c.contentResolver.openInputStream(uri)?.use { BitmapFactory.decodeStream(it, null, bounds) }
        var sample = 1
        while (bounds.outWidth / (sample * 2) >= 1920) sample *= 2
        val bmp = c.contentResolver.openInputStream(uri)?.use {
            BitmapFactory.decodeStream(it, null, BitmapFactory.Options().apply { inSampleSize = sample })
        } ?: error("decode null")
        file(c, which).outputStream().use { bmp.compress(Bitmap.CompressFormat.JPEG, 90, it) }
        true
    } catch (_: Throwable) {
        false
    }

    fun load(c: android.content.Context, which: String): Bitmap? =
        file(c, which).takeIf { it.exists() }?.let { BitmapFactory.decodeFile(it.path) }
}
