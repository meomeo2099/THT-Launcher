package vn.tht.launcher

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/** Mở launcher ngay khi đầu xe khởi động xong (phòng khi chưa đặt làm mặc định). */
class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        context.startActivity(
            Intent(context, MainActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }
}
