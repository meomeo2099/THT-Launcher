package vn.tht.launcher.system

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.provider.Settings
import android.util.Log
import vn.tht.launcher.vehicle.PlatformSignature

/**
 * Dọn dẹp: đóng app chạy nền để giải phóng RAM.
 *
 * - Luôn dùng `killBackgroundProcesses` (quyền thường) cho mọi app có thể mở.
 * - Có quyền hệ thống (APK ký khoá platform): buộc dừng thêm các app người dùng cài
 *   (không đụng app hệ thống / dịch vụ đầu xe).
 * - Bỏ qua [keep] (launcher, nhạc đang phát, bản đồ, THT Voice, bàn phím).
 */
object Cleaner {
    private const val TAG = "Cleaner"

    data class Result(val apps: Int, val freedMb: Long)

    /** Chạy trên luồng nền; trả số app đã xử lý và RAM giải phóng (ước lượng). */
    fun clean(c: Context, keep: Set<String>): Result {
        val am = c.getSystemService(ActivityManager::class.java) ?: return Result(0, 0)
        val before = freeMb(am)
        val pm = c.packageManager
        val skip = keep + c.packageName + defaultInputMethods(c)
        val launchable = pm.queryIntentActivities(
            Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER), 0,
        ).map { it.activityInfo.packageName }.toSet()
        val canForce = PlatformSignature.verify(c)
        var n = 0
        for (pkg in launchable) {
            if (pkg in skip || pkg.startsWith("com.tht.")) continue
            runCatching { am.killBackgroundProcesses(pkg) }
            if (canForce) {
                val info = runCatching { pm.getApplicationInfo(pkg, 0) }.getOrNull() ?: continue
                val system = info.flags and (ApplicationInfo.FLAG_SYSTEM or ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0
                if (!system) forceStop(am, pkg)
            }
            n++
        }
        Thread.sleep(600)
        val freed = (freeMb(am) - before).coerceAtLeast(0)
        Log.i(TAG, "Dọn dẹp $n app (buộc dừng: $canForce), giải phóng ~$freed MB")
        return Result(n, freed)
    }

    private fun forceStop(am: ActivityManager, pkg: String) = runCatching {
        am.javaClass.getMethod("forceStopPackage", String::class.java).invoke(am, pkg)
    }.onFailure { Log.w(TAG, "forceStop $pkg: ${it.cause ?: it}") }

    private fun freeMb(am: ActivityManager): Long =
        ActivityManager.MemoryInfo().also { am.getMemoryInfo(it) }.availMem / (1024 * 1024)

    private fun defaultInputMethods(c: Context): Set<String> =
        Settings.Secure.getString(c.contentResolver, Settings.Secure.DEFAULT_INPUT_METHOD)
            ?.substringBefore('/')?.let { setOf(it) } ?: emptySet()
}
