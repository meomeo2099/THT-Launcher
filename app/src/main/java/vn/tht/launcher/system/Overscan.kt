package vn.tht.launcher.system

import android.content.Context
import android.os.IBinder
import android.util.Log
import vn.tht.launcher.settings.Prefs

/**
 * Thu hẹp vùng hiển thị của màn hình bên trái (overscan, như lệnh `wm overscan`) để mọi app lùi vào chừa
 * chỗ cho thanh bên. Dùng IWindowManager.setOverscan (API ẩn Android 9), cần WRITE_SECURE_SETTINGS
 * (chỉ bản ký platform). Hệ thống nhớ overscan qua khởi động lại, nên giá trị đã đặt được ghi lại để
 * gỡ đúng phần của mình (tắt thanh, thu gọn, tắt dịch vụ). Gỡ tay nếu cần: `adb shell wm overscan reset`.
 */
object Overscan {
    private const val TAG = "Overscan"
    private const val KEY = "nav_bar_overscan"

    /** Phần overscan trái (px) do THT đã đặt. */
    fun applied(p: Prefs) = p.sp.getInt(KEY, 0)

    /** Đặt overscan trái = [left] px; true nếu hệ thống nhận. */
    fun set(c: Context, p: Prefs, left: Int): Boolean {
        if (applied(p) == left) return left > 0
        val ok = viaBinder(left) || viaCommand(left)
        if (ok) {
            p.sp.edit().putInt(KEY, left).apply()
            Log.i(TAG, "overscan trái = $left")
        }
        return ok && left > 0
    }

    private fun viaBinder(left: Int): Boolean = try {
        val b = Class.forName("android.os.ServiceManager").getMethod("getService", String::class.java)
            .invoke(null, Context.WINDOW_SERVICE) as IBinder
        val wm = Class.forName("android.view.IWindowManager\$Stub").getMethod("asInterface", IBinder::class.java)
            .invoke(null, b)
        val i = Int::class.javaPrimitiveType
        wm.javaClass.getMethod("setOverscan", i, i, i, i, i).invoke(wm, 0, left, 0, 0, 0)
        true
    } catch (t: Throwable) {
        Log.w(TAG, "setOverscan: ${(t as? java.lang.reflect.InvocationTargetException)?.targetException ?: t}")
        false
    }

    /** Hàm ẩn bị chặn gọi qua reflection: chạy lệnh `wm` của hệ thống (cùng quyền của app). */
    private fun viaCommand(left: Int): Boolean = try {
        val arg = if (left == 0) "reset" else "$left,0,0,0"
        val proc = Runtime.getRuntime().exec(arrayOf("wm", "overscan", arg))
        val err = proc.errorStream.bufferedReader().readText() + proc.inputStream.bufferedReader().readText()
        val code = proc.waitFor()
        if (code != 0 || err.contains("Exception")) Log.w(TAG, "wm overscan $arg: $code $err")
        code == 0 && !err.contains("Exception")
    } catch (t: Throwable) {
        Log.w(TAG, "wm overscan: $t")
        false
    }
}
