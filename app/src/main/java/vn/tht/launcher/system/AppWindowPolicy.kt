package vn.tht.launcher.system

import android.content.Context
import android.provider.Settings
import vn.tht.launcher.settings.Prefs

/** Reversible, package-scoped Android 9 system-bar policy, including recovery after process death. */
object AppWindowPolicy {
    private const val STORE = "tht_window_policy"
    var lastReport = "Chưa đặt policy ứng dụng"
        private set

    fun restore(context: Context): Boolean {
        val prefs = context.getSharedPreferences(STORE, Context.MODE_PRIVATE)
        if (!prefs.contains("written")) return true
        return runCatching {
            val current = Settings.Global.getString(context.contentResolver, "policy_control")
            if (current == prefs.getString("written", null)) {
                check(Settings.Global.putString(context.contentResolver, "policy_control", prefs.getString("base", null)))
            }
            check(prefs.edit().clear().commit())
            true
        }.onFailure { lastReport = "policy restore: ${it.message}" }.getOrDefault(false)
    }

    fun apply(context: Context, packages: Set<String>) {
        if (!restore(context)) return
        if (!Prefs(context).fullScreen) return
        runCatching {
            val base = Settings.Global.getString(context.contentResolver, "policy_control")
            val desired = ImmersivePolicy.scoped(base, packages + context.packageName)
            if (desired == null) { lastReport = "Giữ policy có loại trừ của người dùng: $base"; return }
            if (desired == base) { lastReport = "policy đã bao phủ ${packages.sorted()}"; return }
            val prefs = context.getSharedPreferences(STORE, Context.MODE_PRIVATE)
            check(prefs.edit().putString("base", base).putString("written", desired).commit())
            if (!Settings.Global.putString(context.contentResolver, "policy_control", desired)) {
                prefs.edit().clear().commit()
                error("WRITE_SECURE_SETTINGS không được chấp nhận")
            }
            lastReport = "policy_control=$desired"
        }.onFailure { lastReport = "Không ẩn được thanh hệ thống: ${it.message}" }
    }
}
