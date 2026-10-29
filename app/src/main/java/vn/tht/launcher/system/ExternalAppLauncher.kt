package vn.tht.launcher.system

import android.app.ActivityManager
import android.app.ActivityOptions
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Rect
import android.os.Handler
import android.os.Looper
import android.os.Bundle
import android.util.Log
import java.util.WeakHashMap
import java.lang.ref.WeakReference

/** Separates an ActivityView preview task from a real display-0 fullscreen launch. */
object ExternalAppLauncher {
    interface Preview {
        /** True when a live preview was released and a fresh external task is necessary. */
        fun prepareExternal(packageName: String): Boolean
        fun externalFailed(packageName: String)
    }
    interface SplitOwner {
        fun exitForFullscreen(): Boolean
        fun homeWithoutClosing(): Boolean = exitForFullscreen()
    }
    private var splitOwner: WeakReference<SplitOwner>? = null
    fun registerSplit(owner: SplitOwner) { splitOwner = WeakReference(owner) }
    fun unregisterSplit(owner: SplitOwner) { if (splitOwner?.get() === owner) splitOwner = null }
    fun leaveSplit(): Boolean = splitOwner?.get()?.exitForFullscreen() ?: true
    fun homeWithoutClosing(): Boolean = splitOwner?.get()?.homeWithoutClosing() ?: true
    fun cancelRepairs() { generation++; handler.removeCallbacksAndMessages(null) }
    fun beginSplit(context: Context, packages: Set<String>): Set<String> {
        generation++
        handler.removeCallbacksAndMessages(null)
        AppWindowPolicy.apply(context, packages)
        val fresh = mutableSetOf<String>()
        packages.forEach { pkg -> snapshot().forEach { if (it.prepareExternal(pkg)) fresh += pkg } }
        if (context.checkSelfPermission(PERMISSION) == PackageManager.PERMISSION_GRANTED) {
            stacks().filter { it.display > 0 }.forEach { stack -> stack.tasks.forEach { (id, name) ->
                val pkg = packages.firstOrNull { ExternalLaunchPolicy.belongs(name, it) }
                if (pkg != null && invoke("removeTask", arrayOf(Int::class.javaPrimitiveType!!), id)) fresh += pkg
            } }
        }
        return fresh
    }
    fun finishSplit(packages: Set<String>) {
        packages.forEach { pkg -> snapshot().forEach { it.externalFailed(pkg) } }
    }
    private val previews = WeakHashMap<Preview, Unit>()
    private val handler = Handler(Looper.getMainLooper())
    private var generation = 0L
    private val detachedHosted = mutableSetOf<String>()
    fun hostedDetached(packages: Set<String>) { detachedHosted += packages }
    fun prepareHosted(context: Context, packages: Set<String>): Boolean {
        if (context.checkSelfPermission(PERMISSION) != PackageManager.PERMISSION_GRANTED) return false
        if (packages.any { !releaseNativeSplit(it) }) return false
        // Refuse a remaining foreign primary rather than put the host in its secondary pane.
        if (stacks().any { it.display == 0 && it.mode == 3 }) return false
        beginSplit(context, packages)
        return true
    }
    @Volatile var lastReport: String = "Chưa mở ứng dụng ngoài"
        private set

    @Synchronized fun register(preview: Preview) { previews[preview] = Unit }
    @Synchronized fun unregister(preview: Preview) { previews.remove(preview) }
    @Synchronized private fun snapshot() = previews.keys.toList()
    fun fullscreenOptions(context: Context): Bundle =
        ActivityOptions.makeBasic().setLaunchDisplayId(0).apply { launchBounds = null }.toBundle().apply {
            if (context.checkSelfPermission(PERMISSION) == PackageManager.PERMISSION_GRANTED) putInt("android.activity.windowingMode", 1)
        }

    fun launch(context: Context, original: Intent): Boolean {
        val intent = Intent(original)
        val component = intent.component ?: intent.resolveActivity(context.packageManager) ?: return false
        if (!leaveSplit()) return false
        val pkg = component.packageName
        intent.component = component
        val listeners = snapshot()
        var fresh = detachedHosted.remove(pkg)
        listeners.forEach { if (it.prepareExternal(pkg)) fresh = true }
        val privileged = context.checkSelfPermission(PERMISSION) == PackageManager.PERMISSION_GRANTED
        val lines = mutableListOf("package=$pkg display=0 fullscreen=1 privileged=$privileged")
        val token = ++generation
        try {
            lines += "before: ${stackReport()}"
            // An OEM split can predate this THT Activity, so leaveSplit alone is insufficient.
            // Only exit a native pair containing the explicitly requested fullscreen app.
            if (privileged && !releaseNativeSplit(pkg)) error("Không thoát được chia đôi cũ của ứng dụng")
            AppWindowPolicy.apply(context, setOf(pkg))
            lines += AppWindowPolicy.lastReport
            // Android can retain a navigator task on a virtual display after its surface closes.
            // Remove only that package's virtual-display tasks; never force-stop a package.
            if (privileged && pkg != context.packageName) stacks().forEach { stack ->
                if (stack.display > 0) stack.tasks.forEach { (id, name) ->
                    if (ExternalLaunchPolicy.belongs(name, pkg) && invoke("removeTask", arrayOf(Int::class.javaPrimitiveType!!), id)) {
                        fresh = true
                        lines += "released virtual-display task=$id display=${stack.display}"
                    }
                }
            }
            intent.flags = intent.flags and (Intent.FLAG_ACTIVITY_MULTIPLE_TASK or
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT).inv()
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            if (fresh) intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            NavAssistService.reserveForExternal()
            context.startActivity(intent, fullscreenOptions(context))
            lines += "start accepted fresh=$fresh"
            lastReport = lines.joinToString("\n")
            if (privileged && pkg != context.packageName) {
                val appContext = context.applicationContext
                listOf(350L, 1100L, 3000L).forEach { delay -> handler.postDelayed({
                    if (token == generation) repair(appContext, pkg)
                }, delay) }
            }
            return true
        } catch (t: Exception) {
            listeners.forEach { it.externalFailed(pkg) }
            lastReport = (lines + "start failed: ${t.javaClass.simpleName}: ${t.message}").joinToString("\n")
            Log.w(TAG, lastReport, t)
            LauncherHome.open(context)
            return false
        }
    }

    private fun repair(context: Context, pkg: String) {
        if (StockMode.active(context)) return
        if (context.checkSelfPermission(PERMISSION) != PackageManager.PERMISSION_GRANTED) return
        if (!releaseNativeSplit(pkg)) {
            lastReport = (lastReport + "\nnative split restore failed: ${stackReport()}").takeLast(10000)
            return
        }
        stacks().filter { it.display == 0 && it.visible && it.top?.packageName == pkg &&
            ExternalLaunchPolicy.mayRepairFullscreen(it.mode) }.forEach { stack ->
            // Resume/focus callbacks may have briefly reapplied the home-only policy.
            AppWindowPolicy.apply(context, setOf(pkg))
            // Clear a stale stack override first. resizeTask is not permitted on an Android 9
            // fullscreen task, and changing its windowing mode alone leaves that override intact.
            if (ExternalLaunchPolicy.mayClearFullscreenStack(stack.standard, stack.mode,
                    stack.top?.packageName, stack.tasks.map { it.second }, pkg)) {
                val cleared = invoke("resizeStack", arrayOf(Int::class.javaPrimitiveType!!, Rect::class.java,
                    Boolean::class.javaPrimitiveType!!, Boolean::class.javaPrimitiveType!!,
                    Boolean::class.javaPrimitiveType!!, Int::class.javaPrimitiveType!!),
                    stack.id, null, false, false, false, -1)
                lastReport = (lastReport + "\nclear stack=${stack.id} bounds=$cleared").takeLast(6000)
                Log.i(TAG, "Fullscreen stack=${stack.id} package=$pkg cleared=$cleared")
            }
            stack.tasks.filter { ExternalLaunchPolicy.belongs(it.second, pkg) }.forEach { (id, _) ->
                val mode = invoke("setTaskWindowingMode", arrayOf(Int::class.javaPrimitiveType!!,
                    Int::class.javaPrimitiveType!!, Boolean::class.javaPrimitiveType!!), id, 1, false)
                lastReport = (lastReport + "\nrepair task=$id display=0 mode=$mode").takeLast(6000)
            }
        }
        lastReport = (lastReport + "\nafter: ${stackReport()}").takeLast(10000)
    }

    private fun releaseNativeSplit(pkg: String): Boolean {
        fun targets() = stacks().filter { it.display == 0 && it.standard && it.mode in listOf(3, 4) &&
            it.tasks.any { task -> ExternalLaunchPolicy.belongs(task.second, pkg) } }
        if (targets().isEmpty()) return true
        val dismissed = invoke("dismissSplitScreenMode", arrayOf(Boolean::class.javaPrimitiveType!!), false)
        // A vendor may reject dismiss. Retry only the selected app's tasks, never unrelated tasks.
        targets().forEach { stack -> stack.tasks.filter { ExternalLaunchPolicy.belongs(it.second, pkg) }.forEach { (id, _) ->
            invoke("setTaskWindowingMode", arrayOf(Int::class.javaPrimitiveType!!,
                Int::class.javaPrimitiveType!!, Boolean::class.javaPrimitiveType!!), id, 1, false)
        } }
        Log.i(TAG, "restore native split package=$pkg dismissed=$dismissed")
        return targets().isEmpty()
    }

    fun stackReport(): String = stacks().joinToString("; ") {
        "stack=${it.id} display=${it.display} mode=${it.mode} standard=${it.standard} visible=${it.visible} bounds=${it.bounds} top=${it.top} tasks=${it.tasks}"
    }

    private data class Stack(val id: Int, val display: Int, val visible: Boolean, val standard: Boolean, val mode: Int?,
                             val top: ComponentName?, val tasks: List<Pair<Int, String?>>, val bounds: Rect?)
    private fun service(): Any? = ActivityManager::class.java.getMethod("getService").invoke(null)
    private fun invoke(name: String, types: Array<Class<*>>, vararg args: Any?): Boolean = runCatching {
        val result = Class.forName("android.app.IActivityManager").getMethod(name, *types).invoke(service(), *args)
        ExternalLaunchPolicy.binderAccepted(result) // Void methods return null on success.
    }.onFailure { Log.d(TAG, "$name unavailable: ${it.cause ?: it}") }.getOrDefault(false)

    private fun stacks(): List<Stack> = runCatching {
        val values = Class.forName("android.app.IActivityManager").getMethod("getAllStackInfos").invoke(service()) as? List<*>
        values.orEmpty().mapNotNull { value ->
            if (value == null) return@mapNotNull null
            runCatching {
                val cls = value.javaClass
                val ids = cls.getField("taskIds").get(value) as IntArray
                val names = cls.getField("taskNames").get(value) as Array<*>
                Stack(cls.getField("stackId").getInt(value), cls.getField("displayId").getInt(value),
                    cls.getField("visible").getBoolean(value),
                    ExternalLaunchPolicy.activityType(value) == 1,
                    ExternalLaunchPolicy.windowingMode(value),
                    cls.getField("topActivity").get(value) as? ComponentName,
                    ids.mapIndexed { index, id -> id to (names.getOrNull(index) as? String) },
                    runCatching { Rect(cls.getField("bounds").get(value) as Rect) }.getOrNull())
            }.getOrNull()
        }
    }.getOrElse { emptyList() }

    private const val TAG = "ExternalAppLauncher"
    private const val PERMISSION = "android.permission.MANAGE_ACTIVITY_STACKS"
}
