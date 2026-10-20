package vn.tht.launcher.shortcuts

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import vn.tht.launcher.R
import vn.tht.launcher.vehicle.HvacOpener

/** Dựng thanh lối tắt vào [container] (LinearLayout ngang) từ [ShortcutStore]. */
class ShortcutBar(
    private val context: Context,
    private val container: LinearLayout,
) {
    private val pm = context.packageManager
    private val hvac = HvacOpener(context)

    fun reload() {
        container.removeAllViews()
        val inflater = LayoutInflater.from(context)
        for (s in ShortcutStore.load(context)) {
            val v = inflater.inflate(R.layout.item_app, container, false)
            v.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            v.findViewById<TextView>(R.id.label).text = s.label
            val icon = iconFor(s)
            v.findViewById<ImageView>(R.id.icon).setImageDrawable(icon)
            v.alpha = if (s.configured && icon != null) 1f else 0.4f
            v.setOnClickListener { open(s) }
            v.setOnLongClickListener {
                toast("Sửa lối tắt trong ${ShortcutStore.file(context).path}")
                true
            }
            container.addView(v)
        }
    }

    private fun open(s: Shortcut) {
        if (!s.configured) {
            toast("Chưa cấu hình \"${s.label}\". Điền package/activity trong ${ShortcutStore.file(context).path}")
            return
        }
        when (s.type) {
            Shortcut.TYPE_HVAC -> hvac.open(
                pkg = s.pkg.ifBlank { HvacOpener.DEFAULT_PACKAGE },
                serviceClass = s.service.ifBlank { null },
            ) { ok -> if (!ok) toast("Không mở được điều hoà") }
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
        val pkg = when {
            s.type == Shortcut.TYPE_HVAC -> s.pkg.ifBlank { HvacOpener.DEFAULT_PACKAGE }
            else -> s.pkg
        }
        if (pkg.isBlank()) return context.getDrawable(android.R.drawable.ic_menu_add)
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
        private const val TAG = "ShortcutBar"
        private val HINTS = listOf("ecarx", "geely", "avm", "camera", "carplay", "setting", "hvac", "climate")

        /**
         * In ra logcat (tag "ShortcutBar") các activity có tên gợi ý (camera, carplay, setting...)
         * để chọn package/activity điền vào shortcuts.json. Xem: `adb logcat -s ShortcutBar`.
         * Chạy nền vì phải quét toàn bộ package.
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
