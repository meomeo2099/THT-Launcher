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
import android.util.Log
import java.util.WeakHashMap

/** Separates an ActivityView preview task from a real display-0 fullscreen launch. */
object ExternalAppLauncher {
    interface Preview {
        /** True when a live preview was released and a fresh external task is necessary. */
        fun prepareExternal(packageName: String): Boolean
        fun externalFailed(packageName: String)
    }
    private val previews = WeakHashMap<Preview, Unit>()
    private val handler = Handler(Looper.getMainLooper())
    private var generation = 0L
    @Volatile var lastReport: String = "Chưa mở ứng dụng ngoài"
        private set

    @Synchronized fun register(preview: Preview) { previews[preview] = Unit }
    @Synchronized fun unregister(preview: Preview) { previews.remove(preview) }
    @Synchronized private fun snapshot() = previews.keys.toList()

    fun launch(context: Context, original: Intent): Boolean {
        val intent = Intent(original)
        val component = intent.component ?: intent.resolveActivity(context.packageManager) ?: return false
        val pkg = component.packageName
        intent.component = component
        val listeners = snapshot()
        var fresh = false
        listeners.forEach { if (it.prepareExternal(pkg)) fresh = true }
        val privileged = context.checkSelfPermission(PERMISSION) == PackageManager.PERMISSION_GRANTED
        val lines = mutableListOf("package=$pkg display=0 fullscreen=1 privileged=$privileged")
        val token = ++generation
        try {
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
            val options = ActivityOptions.makeBasic().setLaunchDisplayId(0).apply { launchBounds = null }.toBundle()
            if (privileged) options.putInt("android.activity.windowingMode", 1)
            context.startActivity(intent, options)
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
            return false
        }
    }

    private fun repair(context: Context, pkg: String) {
        if (context.checkSelfPermission(PERMISSION) != PackageManager.PERMISSION_GRANTED) return
        stacks().filter { it.display == 0 && it.visible && it.top?.packageName == pkg }.forEach { stack ->
            stack.tasks.filter { ExternalLaunchPolicy.belongs(it.second, pkg) }.forEach { (id, _) ->
                val mode = invoke("setTaskWindowingMode", arrayOf(Int::class.javaPrimitiveType!!,
                    Int::class.javaPrimitiveType!!, Boolean::class.javaPrimitiveType!!), id, 1, false)
                val bounds = invoke("resizeTask", arrayOf(Int::class.javaPrimitiveType!!, Rect::class.java,
                    Int::class.javaPrimitiveType!!), id, null, 0)
                lastReport = (lastReport + "\nrepair task=$id display=0 mode=$mode bounds=$bounds").takeLast(6000)
            }
            // A stack override can retain the old widget width. Only clear a stack exclusively
            // occupied by the requested app, excluding HOME and other special activity types.
            if (stack.standard && ExternalLaunchPolicy.mayClearStack(stack.tasks.map { it.second }, pkg)) {
                invoke("resizeStack", arrayOf(Int::class.javaPrimitiveType!!, Rect::class.java,
                    Boolean::class.javaPrimitiveType!!, Boolean::class.javaPrimitiveType!!,
                    Boolean::class.javaPrimitiveType!!, Int::class.javaPrimitiveType!!),
                    stack.id, null, false, false, false, -1)
            }
        }
    }

    private data class Stack(val id: Int, val display: Int, val visible: Boolean, val standard: Boolean,
                             val top: ComponentName?, val tasks: List<Pair<Int, String?>>)
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
                    runCatching { cls.getMethod("getActivityType").invoke(value) == 1 }.getOrDefault(false),
                    cls.getField("topActivity").get(value) as? ComponentName,
                    ids.mapIndexed { index, id -> id to (names.getOrNull(index) as? String) })
            }.getOrNull()
        }
    }.getOrElse { emptyList() }

    private const val TAG = "ExternalAppLauncher"
    private const val PERMISSION = "android.permission.MANAGE_ACTIVITY_STACKS"
}
