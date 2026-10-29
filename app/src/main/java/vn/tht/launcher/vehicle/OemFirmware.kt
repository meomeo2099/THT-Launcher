package vn.tht.launcher.vehicle

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build

/**
 * Public activities observed in the supplied SX11-LA launcher manifest/resources.
 * Candidates must still resolve on the installed firmware. No power Binder is invoked.
 */
object OemFirmware {
    const val LAUNCHER_PACKAGE = "ecarx.launcher3"
    const val POWER_PACKAGE = "ecarx.power.service"
    const val HOME_CLASS = "ecarx.launcher3.Launcher"
    const val APP_PANE_CLASS = "ecarx.launcher3.AppPane"

    enum class App(val title: String, val packages: List<String>) {
        HOME("Màn hình gốc của xe", listOf(LAUNCHER_PACKAGE)),
        APPS("Ứng dụng gốc của xe", listOf(LAUNCHER_PACKAGE)),
        NAVIGATION("Dẫn đường", listOf("com.autonavi.amapauto")),
        MEDIA("Nhạc gốc của xe", listOf("com.ecarx.multimedia")),
        PHONE("Điện thoại Bluetooth", listOf("com.ecarx.btphone")),
        SETTINGS("Cài đặt đầu xe", listOf("com.neusoft.optimus.wheeljack.setting", "ecarx.settings")),
        VEHICLE("Cài đặt xe", listOf("com.ecarx.mycar", "ecarx.settings")),
        CLIMATE("Điều hòa gốc", listOf("ecarx.hvac.app")),
        RADIO("Radio gốc", listOf("com.ecarx.radio", "com.ecarx.multimedia")),
        CAMERA("Camera toàn cảnh", listOf("com.neusoft.avm", "com.ecarx.avm.app")),
        DVR("Camera hành trình", listOf("com.neusoft.dvrlogin")),
        THEMES("Giao diện đầu xe", listOf("com.ecarx.thememanager")),
        STORE("Kho ứng dụng", listOf("com.ecarx.appstore")),
    }

    data class PackageStatus(
        val packageName: String,
        val installed: Boolean,
        val versionName: String?,
        val versionCode: Long?,
        val platformSigned: Boolean?,
        val certificateSha256: String?,
    )

    /** Resolve only enabled/exported activities; returned intents always target a component. */
    fun resolve(context: Context, app: App): Intent? {
        val preferred = when (app) {
            App.HOME -> listOf(ComponentName(LAUNCHER_PACKAGE, HOME_CLASS))
            App.APPS -> listOf(ComponentName(LAUNCHER_PACKAGE, APP_PANE_CLASS))
            else -> emptyList()
        }
        preferred.forEach { component -> explicitActivity(context, component)?.let { return it } }
        if (app == App.APPS) return null
        app.packages.forEach { packageName ->
            if (app == App.VEHICLE && packageName == "ecarx.settings") {
                explicitActivity(context, ComponentName(packageName, "ecarx.settings.setting.CarSettingActivity"))?.let { return it }
            }
            resolvePackage(context, packageName)?.let { return it }
        }
        return null
    }

    fun available(context: Context, app: App): Boolean = resolve(context, app) != null
    fun open(context: Context, app: App): Boolean = launch(context, resolve(context, app))
    fun openStockLauncher(context: Context): Boolean = open(context, App.HOME)
    fun openSettings(context: Context): Boolean = open(context, App.SETTINGS)

    /** Package launch fallback, with the same enabled/exported/permission checks. */
    fun resolvePackage(context: Context, packageName: String): Intent? {
        if (packageName.isBlank() || packageName == context.packageName) return null
        val launch = runCatching { context.packageManager.getLaunchIntentForPackage(packageName) }.getOrNull() ?: return null
        val component = launch.component ?: return null
        if (component.packageName != packageName) return null
        return explicitActivity(context, component, launch)
    }

    private fun explicitActivity(context: Context, component: ComponentName, template: Intent? = null): Intent? =
        runCatching {
            val info = context.packageManager.getActivityInfo(component, 0)
            if (!info.enabled || !info.applicationInfo.enabled || !info.exported) return null
            val permission = info.permission
            if (!permission.isNullOrEmpty() && context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) return null
            Intent(template ?: Intent(Intent.ACTION_MAIN)).apply {
                setComponent(component)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                // OEM HOME has no LAUNCHER category. Explicit invocation keeps its HOME contract.
                if (template == null && component.className == HOME_CLASS) addCategory(Intent.CATEGORY_HOME)
            }
        }.getOrNull()

    private fun launch(context: Context, intent: Intent?): Boolean =
        intent != null && vn.tht.launcher.system.ExternalAppLauncher.leaveSplit() && runCatching {
            vn.tht.launcher.system.AppWindowPolicy.restore(context)
            context.startActivity(intent, vn.tht.launcher.system.ExternalAppLauncher.fullscreenOptions(context))
            true
        }.getOrDefault(false)

    /** Read-only package report; an OEM package certificate is not the framework certificate. */
    @Suppress("DEPRECATION")
    fun packageStatus(context: Context, packageName: String): PackageStatus = try {
        val pm = context.packageManager
        val info = pm.getPackageInfo(packageName, 0)
        PackageStatus(
            packageName, true, info.versionName,
            if (Build.VERSION.SDK_INT >= 28) info.longVersionCode else info.versionCode.toLong(),
            runCatching { pm.checkSignatures("android", packageName) == PackageManager.SIGNATURE_MATCH }.getOrNull(),
            PlatformSignature.certSha256(context, packageName).takeUnless { it == "?" },
        )
    } catch (_: Exception) {
        PackageStatus(packageName, false, null, null, null, null)
    }

    fun diagnosticReport(context: Context): String = buildString {
        appendLine("OEM Coolray — chỉ đọc thông tin, không gọi dịch vụ nguồn")
        appendLine("MODEL=${Build.MODEL}; PRODUCT=${Build.PRODUCT}; DEVICE=${Build.DEVICE}; API=${Build.VERSION.SDK_INT}")
        appendLine("Profile=${Profiles.detect().id}")
        for (pkg in listOf(LAUNCHER_PACKAGE, POWER_PACKAGE)) {
            val status = packageStatus(context, pkg)
            appendLine("$pkg: ${if (status.installed) "đã cài" else "không tìm thấy"}")
            if (status.installed) {
                appendLine("  version=${status.versionName} (${status.versionCode})")
                appendLine("  khớp chữ ký android=${status.platformSigned ?: "không rõ"}")
                appendLine("  cert SHA-256=${status.certificateSha256 ?: "không rõ"}")
            }
        }
        for (app in App.entries) appendLine("${app.title}: ${resolve(context, app)?.component?.flattenToShortString() ?: "không có activity khả dụng"}")
        appendLine("APK hệ thống cung cấp không có DEX; chưa xác minh Binder/ODex/Vdex dịch vụ nguồn.")
    }
}
