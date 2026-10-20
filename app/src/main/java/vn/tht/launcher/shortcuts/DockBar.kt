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
) {
    /** Các hành động nội bộ launcher mà nút dock có thể gọi. */
    interface Actions {
        fun openControls()
        fun openAppDrawer()
        fun openSettings()
        fun openStats()
        fun pickApp(onPicked: (String) -> Unit)
    }

    private val pm = context.packageManager
    private val hvac = HvacOpener(context)
    private var items: MutableList<Shortcut> = mutableListOf()

    fun reload() {
        items = ShortcutStore.load(context).toMutableList()
        render()
    }

    private fun render() {
        container.removeAllViews()
        items.forEachIndexed { i, s -> container.addView(itemView(s, i)) }
        if (items.size < ShortcutStore.MAX) {
            container.addView(cell(Icons.get(context, "plus"), "Thêm", 0.6f).apply {
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

    private fun cell(icon: Drawable?, label: String, alpha: Float) = Ui.vbox(context, 6, Gravity.CENTER).apply {
        val prefs = vn.tht.launcher.settings.Prefs(context)
        val (iconDp, cellDp) = when (prefs.dockSize) {
            "compact" -> 40 to 88
            "large" -> 68 to 132
            else -> 52 to 108
        }
        val labels = prefs.dockLabels
        this.alpha = alpha
        background = context.getDrawable(R.drawable.ripple)
        val size = Ui.dp(context, if (labels) iconDp else iconDp + 12)
        addView(ImageView(context).apply { setImageDrawable(icon) }, LinearLayout.LayoutParams(size, size))
        if (labels) addView(Ui.text(context, label, 13f).apply { gravity = Gravity.CENTER; maxLines = 1 })
        layoutParams = LinearLayout.LayoutParams(Ui.dp(context, cellDp), LinearLayout.LayoutParams.MATCH_PARENT)
    }


    private fun save() {
        ShortcutStore.save(context, items)
        render()
    }

    private fun editDialog(index: Int) {
        val s = items[index]
        AlertDialog.Builder(context)
            .setTitle(s.label)
            .setItems(arrayOf("Sang trái", "Sang phải", "Xoá khỏi dock")) { _, which ->
                when (which) {
                    0 -> if (index > 0) { items.add(index - 1, items.removeAt(index)); save() }
                    1 -> if (index < items.size - 1) { items.add(index + 1, items.removeAt(index)); save() }
                    2 -> { items.removeAt(index); save() }
                }
            }
            .show()
    }

    private fun addDialog() {
        val choices = listOf(
            "Ứng dụng…" to null,
            "Tất cả ứng dụng" to Shortcut("apps", "Ứng dụng", Shortcut.TYPE_APPS),
            "Điều khiển xe" to Shortcut("controls", "Điều khiển", Shortcut.TYPE_CONTROLS),
            "Điều hoà gốc" to Shortcut("hvac", "Điều hoà", Shortcut.TYPE_HVAC),
            "Cài đặt launcher" to Shortcut("settings", "Cài đặt", Shortcut.TYPE_SETTINGS),
            "Thống kê chuyến đi" to Shortcut("stats", "Thống kê", Shortcut.TYPE_STATS),
            "THT Voice" to ShortcutStore.DEFAULTS.first { it.id == "voice" },
        )
        AlertDialog.Builder(context)
            .setTitle("Thêm vào dock")
            .setItems(choices.map { it.first }.toTypedArray()) { _, which ->
                val s = choices[which].second
                if (s == null) {
                    actions.pickApp { pkg ->
                        val label = runCatching { pm.getApplicationLabel(pm.getApplicationInfo(pkg, 0)).toString() }.getOrDefault(pkg)
                        add(Shortcut("app_$pkg", label, Shortcut.TYPE_ACTIVITY, pkg = pkg))
                    }
                } else add(s.copy(id = s.id + "_" + System.currentTimeMillis()))
            }
            .show()
    }

    private fun add(s: Shortcut) {
        if (items.size >= ShortcutStore.MAX) return toast("Dock tối đa ${ShortcutStore.MAX} nút")
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
        }
        val pkg = if (s.type == Shortcut.TYPE_HVAC) s.pkg.ifBlank { HvacOpener.DEFAULT_PACKAGE } else s.pkg
        if (pkg.isBlank()) return Icons.get(context, "plus")
        return try {
            if (s.type == Shortcut.TYPE_ACTIVITY && s.activity.isNotBlank()) {
                val cls = if (s.activity.startsWith(".")) pkg + s.activity else s.activity
                pm.getActivityIcon(ComponentName(pkg, cls))
            } else pm.getApplicationIcon(pkg)
        } catch (_: PackageManager.NameNotFoundException) {
            null // app không có trên máy này
        }
    }

    private fun toast(msg: String) = Toast.makeText(context, msg, Toast.LENGTH_LONG).show()

    companion object {
        private const val TAG = "DockBar"

        /** Chiều cao dock (dp) theo cài đặt. */
        fun heightDp(prefs: vn.tht.launcher.settings.Prefs) = when (prefs.dockSize) {
            "compact" -> 84
            "large" -> 140
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
