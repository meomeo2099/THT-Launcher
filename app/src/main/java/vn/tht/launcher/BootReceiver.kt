package vn.tht.launcher

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import vn.tht.launcher.settings.Prefs
import vn.tht.launcher.system.Autostart

/** Khi đầu xe khởi động xong: đánh dấu để chạy app tự mở, và mở launcher nếu được bật. */
class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Autostart.markBoot(context)
        if (Prefs(context).openOnBoot) {
            context.startActivity(
                Intent(context, MainActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }
    }
}
