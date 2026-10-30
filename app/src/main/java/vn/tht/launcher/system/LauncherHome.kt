package vn.tht.launcher.system

import android.content.Context
import android.content.Intent
import vn.tht.launcher.MainActivity

/** GLOBAL_ACTION_HOME follows the OEM default. Our Home controls must name THT explicitly. */
object LauncherHome {
    fun intent(context: Context) = Intent(context, MainActivity::class.java).addFlags(
        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
    fun open(context: Context): Boolean = ExternalAppLauncher.homeWithoutClosing() &&
        runCatching { context.startActivity(intent(context), ExternalAppLauncher.fullscreenOptions(context)); true }.getOrDefault(false)
    fun defaultPackage(context: Context): String? = context.packageManager.resolveActivity(
        Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME), 0)?.activityInfo?.packageName
}
