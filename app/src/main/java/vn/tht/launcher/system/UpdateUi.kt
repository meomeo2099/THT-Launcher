package vn.tht.launcher.system

import android.app.Activity
import android.widget.Toast
import vn.tht.launcher.ui.IconList

/** Hộp hỏi cập nhật và tiến trình tải. */
object UpdateUi {
    fun offer(a: Activity, r: Updater.Release) {
        if (a.isFinishing) return
        IconList.show(a, "Có bản mới: ${r.name}", listOf(
            IconList.Item("Cập nhật ngay", "expand", summary = "Tải và cài (kiểm tra chữ ký trước khi cài)"),
            IconList.Item("Để sau", "close"),
        )) { i ->
            if (i != 0) return@show
            val toast = Toast.makeText(a, "Đang tải 0%", Toast.LENGTH_SHORT).also { it.show() }
            Thread {
                var last = -1
                val err = Updater.downloadAndInstall(a, r) { p ->
                    if (p / 20 != last) {
                        last = p / 20
                        a.runOnUiThread { toast.setText("Đang tải $p%"); toast.show() }
                    }
                }
                a.runOnUiThread { Toast.makeText(a, err ?: "Đang cài bản mới…", Toast.LENGTH_LONG).show() }
            }.start()
        }
    }
}
