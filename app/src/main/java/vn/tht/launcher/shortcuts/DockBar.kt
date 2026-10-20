package vn.tht.launcher.shortcuts

import android.app.AlertDialog
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import vn.tht.launcher.R
import vn.tht.launcher.ui.Icons
import vn.tht.launcher.ui.Ui
import vn.tht.launcher.vehicle.HvacOpener

/**
 * Dock dưới màn hình: tối đa [ShortcutStore.MAX] nút (app hoặc hành động), lưu trong shortcuts.json.
 * Nhấn giữ một nút để di chuyển/xoá; nút "+" ở cuối để thêm.
 */
class DockBar(
    private val context: Context,
    private val container: LinearLayout,
    private val actions: Actions,
    /** File cấu hình (shortcuts.json cho dock, topbar.json cho thanh trên). */
    private val storeName: String = "shortcuts.json",
    private val defaults: List<Shortcut> = ShortcutStore.DEFAULTS,
    /** Thanh nhỏ phía trên: icon nhỏ, không chữ. */
    private val compact: Boolean = false,
) {
    /** Các hành động nội bộ launcher mà nút dock có thể gọi. */
    interface Actions {
        fun openControls()
        fun openAppDrawer()
        fun openSettings()
        fun openStats()
        fun goHome()
        fun cycleDriverSeatHeat()
        fun openWifiSettings()
        fun toggleCollapsed()
        fun isCollapsed(): Boolean
        fun pickApp(onPicked: (String) -> Unit)
    }

    private val pm = context.packageManager
    private val hvac = HvacOpener(context)
    private var items: MutableList<Shortcut> = mutableListOf()
    /** Ô trạng thái đang hiện: chỉ số -> (icon, chữ). */
    private val statusViews = mutableListOf<Pair<String, Pair<ImageView, android.widget.TextView>>>()
    private var last: Status = Status()

    /** Giá trị cho các ô trạng thái. null = không đọc được (hiện "–"). */
    data class Status(
        val wifi: Boolean? = null,
        val coolantC: Float? = null,
        val fuelL: Float? = null,
        val voltage: Float? = null,
        val seatHeatLevel: Int? = null,
    )

    fun update(s: Status) {
        last = s
        statusViews.forEach { (metric, v) -> bindStatus(metric, v.first, v.second) }
    }

    private fun bindStatus(metric: String, icon: ImageView, text: android.widget.TextView) {
        val s = last
        val (key, value) = when (metric) {
            "wifi" -> (if (s.wifi == false) "wifi-off" else "wifi") to ""
            "coolant" -> "engine-temp" to (s.coolantC?.let { "${it.toInt()}°C" } ?: "–")
            "fuel_l" -> "fuel" to (s.fuelL?.let { "${it.toInt()} L" } ?: "–")
            "voltage" -> "battery" to (s.voltage?.let { String.format(java.util.Locale.US, "%.1f V", it) } ?: "–")
            "seat_heat" -> "seat-heat-left" to (s.seatHeatLevel?.takeIf { it > 0 }?.toString() ?: "")
            "home" -> "home" to ""
            else -> "plus" to "?"
        }
        val active = metric == "seat_heat" && (s.seatHeatLevel ?: 0) > 0
        icon.setImageDrawable(Icons.get(context, key, if (active) vn.tht.launcher.ui.Palette.accent else vn.tht.launcher.ui.Palette.text))
        text.text = value
        text.visibility = if (value.isEmpty()) android.view.View.GONE else android.view.View.VISIBLE
    }

    private fun statusCell(s: Shortcut, index: Int) = Ui.hbox(context, Gravity.CENTER).apply {
        background = if (compact) context.getDrawable(R.drawable.ripple) else Ui.pill(context, vn.tht.launcher.ui.Palette.button, 16)
        val pad = Ui.dp(context, if (compact) 12 else 14)
        setPadding(pad, 0, pad, 0)
        val icon = ImageView(context)
        val text = Ui.text(context, "", if (compact) 18f else 22f).apply { setPadding(Ui.dp(context, 6), 0, 0, 0) }
        val iconPx = Ui.dp(context, if (compact) 26 else 36)
        addView(icon, LinearLayout.LayoutParams(iconPx, iconPx))
        addView(text)
        layoutParams = if (compact) LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT)
        else LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, Ui.dp(context, 66)).apply {
            marginStart = Ui.dp(context, 5); marginEnd = Ui.dp(context, 5)
        }
        statusViews += s.action to (icon to text)
        bindStatus(s.action, icon, text)
        setOnClickListener {
            when (s.action) {
                "wifi" -> actions.openWifiSettings()
                "seat_heat" -> actions.cycleDriverSeatHeat()
                "home" -> actions.goHome()
                else -> actions.openStats()
            }
        }
        setOnLongClickListener { editDialog(index); true }
    }

    fun reload() {
        items = ShortcutStore.load(context, storeName, defaults).toMutableList()
        render()
    }

    private fun render() {
        container.removeAllViews()
        statusViews.clear()
        items.forEachIndexed { i, s ->
            container.addView(if (s.type == Shortcut.TYPE_STATUS) statusCell(s, i) else itemView(s, i))
        }
        if (items.size < ShortcutStore.MAX) {
            container.addView(cell(Icons.get(context, "plus"), "Thêm", if (compact) 0.35f else 0.6f).apply {
                setOnClickListener { addDialog() }
            })
        }
    }

    private fun itemView(s: Shortcut, index: Int) = run {
        val icon = iconFor(s)
        cell(icon, s.label, if (s.configured && icon != null) 1f else 0.4f).apply {
            setOnClickListener { open(s) }
            setOnLongClickListener { editDialog(index); true }
        }
    }

    private fun cell(icon: Drawable?, label: String, alpha: Float) = Ui.vbox(context, 0, Gravity.CENTER).apply {
        this.alpha = alpha
        contentDescription = label
        if (compact) {
            background = context.getDrawable(R.drawable.ripple)
            val s = Ui.dp(context, 26)
            addView(ImageView(context).apply { setImageDrawable(icon) }, LinearLayout.LayoutParams(s, s))
            layoutParams = LinearLayout.LayoutParams(Ui.dp(context, 52), LinearLayout.LayoutParams.MATCH_PARENT)
            return@apply
        }
        val prefs = vn.tht.launcher.settings.Prefs(context)
        val tileDp = when (prefs.dockSize) { "compact" -> 56; "large" -> 80; else -> 66 }
        val labels = prefs.dockLabels
        // Ô vuông bo góc chứa icon; tên (nếu bật) nằm dưới
        val tile = android.widget.FrameLayout(context).apply {
            background = Ui.pill(context, vn.tht.launcher.ui.Palette.button, 16)
            val pad = Ui.dp(context, tileDp) * 20 / 100
            setPadding(pad, pad, pad, pad)
            addView(ImageView(context).apply { setImageDrawable(icon) }, android.widget.FrameLayout.LayoutParams(-1, -1))
        }
        addView(tile, LinearLayout.LayoutParams(Ui.dp(context, tileDp), Ui.dp(context, tileDp)))
        if (labels) addView(Ui.text(context, label, 12f).apply { gravity = Gravity.CENTER; maxLines = 1 })
        layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT).apply {
            marginStart = Ui.dp(context, 5); marginEnd = Ui.dp(context, 5)
        }
        setOnLongClickListener { false }
    }

    private fun save() {
        ShortcutStore.save(context, items, storeName)
        render()
    }

    private fun editDialog(index: Int) {
        val s = items[index]
        vn.tht.launcher.ui.IconList.show(context, s.label, listOf(
            vn.tht.launcher.ui.IconList.Item("Sang trái", "chevron-left"),
            vn.tht.launcher.ui.IconList.Item("Sang phải", "chevron-right"),
            vn.tht.launcher.ui.IconList.Item("Xoá", "close", danger = true),
        )) { which ->
            when (which) {
                0 -> if (index > 0) { items.add(index - 1, items.removeAt(index)); save() }
                1 -> if (index < items.size - 1) { items.add(index + 1, items.removeAt(index)); save() }
                2 -> { items.removeAt(index); save() }
            }
        }
    }

    private fun addDialog() {
        val choices = listOf(
            "Ứng dụng…" to null,
            "Tất cả ứng dụng" to Shortcut("apps", "Ứng dụng", Shortcut.TYPE_APPS),
            "Điều khiển xe" to Shortcut("controls", "Điều khiển", Shortcut.TYPE_CONTROLS),
            "Điều hoà gốc" to Shortcut("hvac", "Điều hoà", Shortcut.TYPE_HVAC),
            "Cài đặt launcher" to Shortcut("settings", "Cài đặt", Shortcut.TYPE_SETTINGS),
            "Thống kê chuyến đi" to Shortcut("stats", "Thống kê", Shortcut.TYPE_STATS),
            "Nút ẩn/hiện tất cả" to Shortcut("collapse", "Ẩn/hiện tất cả", Shortcut.TYPE_COLLAPSE),
        ) + STATUS.map { (metric, label) ->
            "Trạng thái: $label" to Shortcut("status_$metric", label, Shortcut.TYPE_STATUS, action = metric)
        } + listOf(
            "THT Voice" to ShortcutStore.DEFAULTS.first { it.id == "voice" },
        )
        fun iconOf(sc: Shortcut?): String = when {
            sc == null -> "apps"
            sc.type == Shortcut.TYPE_STATUS -> when (sc.action) {
                "wifi" -> "wifi"; "coolant" -> "engine-temp"; "fuel_l" -> "fuel"; "voltage" -> "battery"
                "seat_heat" -> "seat-heat-left"; else -> "home"
            }
            sc.type == Shortcut.TYPE_APPS -> "apps"
            sc.type == Shortcut.TYPE_CONTROLS -> "shortcut"
            sc.type == Shortcut.TYPE_HVAC -> "climate"
            sc.type == Shortcut.TYPE_SETTINGS -> "settings"
            sc.type == Shortcut.TYPE_STATS -> "trip"
            sc.type == Shortcut.TYPE_COLLAPSE -> "collapse"
            else -> "mic"
        }
        vn.tht.launcher.ui.IconList.show(context, "Thêm vào thanh",
            choices.map { vn.tht.launcher.ui.IconList.Item(it.first, iconOf(it.second)) }, columns = 2) { which ->
                val s = choices[which].second
                if (s == null) {
                    actions.pickApp { pkg ->
                        val label = runCatching { pm.getApplicationLabel(pm.getApplicationInfo(pkg, 0)).toString() }.getOrDefault(pkg)
                        add(Shortcut("app_$pkg", label, Shortcut.TYPE_ACTIVITY, pkg = pkg))
                    }
                } else add(s.copy(id = s.id + "_" + System.currentTimeMillis()))
            }
    }

    private fun add(s: Shortcut) {
        if (items.size >= ShortcutStore.MAX) return toast("Tối đa ${ShortcutStore.MAX} nút")
        items.add(s)
        save()
    }

    private fun open(s: Shortcut) {
        if (!s.configured) {
            toast("Chưa cấu hình \"${s.label}\". Nhấn giữ để xoá, hoặc sửa ${ShortcutStore.file(context).path}")
            return
        }
        when (s.type) {
            Shortcut.TYPE_HVAC -> hvac.open(
                pkg = s.pkg.ifBlank { HvacOpener.DEFAULT_PACKAGE },
                serviceClass = s.service.ifBlank { null },
            ) { ok -> if (!ok) toast("Không mở được điều hoà") }
            Shortcut.TYPE_CONTROLS -> actions.openControls()
            Shortcut.TYPE_APPS -> actions.openAppDrawer()
            Shortcut.TYPE_SETTINGS -> actions.openSettings()
            Shortcut.TYPE_STATS -> actions.openStats()
            Shortcut.TYPE_COLLAPSE -> actions.toggleCollapsed()
            else -> start(intentFor(s), s.label)
        }
    }

    private fun intentFor(s: Shortcut): Intent? = when (s.type) {
        Shortcut.TYPE_ACTION -> Intent(s.action).apply { if (s.pkg.isNotBlank()) setPackage(s.pkg) }
        else -> if (s.activity.isNotBlank()) {
            val cls = if (s.activity.startsWith(".")) s.pkg + s.activity else s.activity
            Intent(Intent.ACTION_MAIN).setComponent(ComponentName(s.pkg, cls))
        } else pm.getLaunchIntentForPackage(s.pkg)
    }

    private fun start(intent: Intent?, label: String) {
        try {
            if (intent == null) throw IllegalStateException("không có intent")
            context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        } catch (t: Throwable) {
            Log.w(TAG, "Không mở được $label: $t")
            toast("Không mở được $label")
        }
    }

    private fun iconFor(s: Shortcut): Drawable? {
        when (s.type) {
            Shortcut.TYPE_CONTROLS -> return Icons.get(context, "shortcut")
            Shortcut.TYPE_APPS -> return Icons.get(context, "apps")
            Shortcut.TYPE_SETTINGS -> return Icons.get(context, "settings")
            Shortcut.TYPE_STATS -> return Icons.get(context, "trip")
            Shortcut.TYPE_COLLAPSE -> return Icons.get(context, if (actions.isCollapsed()) "expand" else "collapse")
        }
        val pkg = if (s.type == Shortcut.TYPE_HVAC) s.pkg.ifBlank { HvacOpener.DEFAULT_PACKAGE } else s.pkg
        if (pkg.isBlank()) return Icons.get(context, "plus")
        return try {
            if (s.type == Shortcut.TYPE_ACTIVITY && s.activity.isNotBlank()) {
                val cls = if (s.activity.startsWith(".")) pkg + s.activity else s.activity
                pm.getActivityIcon(ComponentName(pkg, cls))
            } else pm.getApplicationIcon(pkg)
        } catch (_: PackageManager.NameNotFoundException) {
            fallbackIcon(s) // app không có trên máy này: icon theo chức năng
        }
    }

    private fun fallbackIcon(s: Shortcut): Drawable? = Icons.get(context, when {
        s.type == Shortcut.TYPE_HVAC -> "climate"
        s.id.startsWith("voice") || s.action.contains("VOICE") -> "mic"
        s.id.startsWith("camera") -> "camera360"
        s.id.startsWith("carplay") -> "carplay"
        s.id.startsWith("car_settings") -> "car-settings"
        else -> "apps"
    }, vn.tht.launcher.ui.Palette.textDim)

    private fun toast(msg: String) = Toast.makeText(context, msg, Toast.LENGTH_LONG).show()

    companion object {
        private const val TAG = "DockBar"

        /** Các ô trạng thái có thể thêm vào dock: chỉ số -> tên. */
        val STATUS = listOf(
            "wifi" to "Wi-Fi", "coolant" to "Nhiệt độ máy", "fuel_l" to "Xăng (lít)",
            "voltage" to "Điện áp ắc quy", "seat_heat" to "Sưởi ghế lái", "home" to "Màn hình chính",
        )

        /** Chiều cao dock (dp) theo cài đặt. */
        fun heightDp(prefs: vn.tht.launcher.settings.Prefs) = when (prefs.dockSize) {
            "compact" -> 88
            "large" -> 120
            else -> 104
        }
        private val HINTS = listOf("ecarx", "geely", "avm", "camera", "carplay", "setting", "hvac", "climate")

        /**
         * In ra logcat (tag "DockBar") các activity có tên gợi ý (camera, carplay, setting...)
         * để chọn package/activity cho dock. Xem: `adb logcat -s DockBar`. Chạy nền.
         */
        fun logCandidates(context: Context) = Thread {
            try {
                val pm = context.packageManager
                for (p in pm.getInstalledPackages(PackageManager.GET_ACTIVITIES)) {
                    for (a in p.activities.orEmpty()) {
                        if (!a.exported) continue
                        val name = a.name.lowercase()
                        if (HINTS.any { it in name || it in p.packageName.lowercase() }) {
                            Log.i(TAG, "activity: ${p.packageName}/${a.name}")
                        }
                    }
                }
            } catch (t: Throwable) {
                Log.w(TAG, "Quét activity lỗi: $t")
            }
        }.start()
    }
}
