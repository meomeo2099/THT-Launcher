package vn.tht.launcher

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import vn.tht.launcher.settings.Prefs
import vn.tht.launcher.system.Autostart

/** Khi đầu xe khởi động xong: đánh dấu để chạy app tự mở, và mở launcher nếu được bật. */
class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != Intent.ACTION_BOOT_COMPLETED && intent.action != Intent.ACTION_LOCKED_BOOT_COMPLETED) return
        // Preferences/layouts are credential-encrypted; await the normal boot event while locked.
        if (context.getSystemService(android.os.UserManager::class.java)?.isUserUnlocked == false) return
        Autostart.markBoot(context)
        if (Prefs(context).openOnBoot) {
            runCatching { context.startActivity(
                Intent(context, MainActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            ) }.onFailure { android.util.Log.w("BootReceiver", "Không mở được launcher sau khi khởi động", it) }
        }
    }
}
