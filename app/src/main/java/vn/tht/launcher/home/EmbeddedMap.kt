package vn.tht.launcher.home

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.graphics.Rect
import android.hardware.display.VirtualDisplay
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.SurfaceView
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

/** Launch only after the Surface and its virtual display agree on the final host size. */
class EmbeddedMap(private val c: Context, private val requireFocus: Boolean = true,
                  private val onStarted: () -> Unit = {},
                  private val onFail: (String) -> Unit) : FrameLayout(c) {
    private val handler = Handler(Looper.getMainLooper())
    private var activityView: View? = null
    private var requested: Intent? = null
    private var pending: Intent? = null
    private var tries = 0
    private val gate = EmbeddedViewportGate()
    private var ready = false
    private var released = false
    private var active = false
    private var startedWidth = 0
    private var startedHeight = 0
    private var displayId = -1
    private var generation = 0

    fun create(): Boolean = try {
        check(c.checkSelfPermission("android.permission.REMOVE_TASKS") == android.content.pm.PackageManager.PERMISSION_GRANTED) {
            "Thiếu quyền dọn task của màn hình ảo"
        }
        val token = ++generation
        released = false
        val v = PlatformActivityView.create(c, ready = {
            if (!released && generation == token) { ready = true; handler.post { if (generation == token) tryStart() } }
        }, destroyed = {
            handler.post { if (!released && generation == token) fail("Màn hình bản đồ đã đóng") }
        })
        activityView = v
        addView(v, LayoutParams(-1, -1))
        true
    } catch (t: Throwable) { lastError = t.toString(); false }

    fun start(intent: Intent) {
        val reason = embeddingRejection(c, intent)
        if (reason != null) { handler.post { if (!released) fail(reason) }; return }
        requested = Intent(intent)
        pending = Intent(intent).apply {
            flags = flags and (Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT or Intent.FLAG_ACTIVITY_NEW_DOCUMENT).inv()
            // Never inherit the previous display's task bounds/configuration.
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_MULTIPLE_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        tries = 0; gate.reset()
        handler.removeCallbacksAndMessages(null)
        handler.postDelayed(::tryStart, 100)
    }

    private fun surface(v: View): SurfaceView? = when (v) {
        is SurfaceView -> v
        is ViewGroup -> (0 until v.childCount).firstNotNullOfOrNull { surface(v.getChildAt(it)) }
        else -> null
    }
    private fun virtualDisplay(v: View): VirtualDisplay? = runCatching {
        Class.forName("android.app.ActivityView").getDeclaredField("mVirtualDisplay").apply { isAccessible = true }.get(v) as? VirtualDisplay
    }.getOrNull()

    private fun tryStart() {
        val v = activityView ?: return
        val intent = pending ?: return
        if (released || !active || !isAttachedToWindow || !isShown) return
        val vd = virtualDisplay(v)
        val size = vd?.display?.let { display -> Point().also(display::getRealSize) }
        val s = surface(v)
        val frame = s?.holder?.surfaceFrame
        val matches = ready && width > 0 && height > 0 && (!requireFocus || hasWindowFocus()) &&
            s?.holder?.surface?.isValid == true && frame?.width() == width && frame.height() == height &&
            size?.x == width && size.y == height
        if (!matches) gate.reset()
        val settled = matches && gate.observe(width, height, frame?.width() ?: -1, frame?.height() ?: -1, size?.x ?: -1, size?.y ?: -1)
        if (!settled) {
            if (++tries >= 60) { fail("Surface/màn hình ảo chưa khớp ô ${width}×$height; display=$size surface=$frame"); return }
            handler.postDelayed(::tryStart, 100)
            return
        }
        try {
            displayId = vd!!.display.displayId
            v.javaClass.getMethod("startActivity", Intent::class.java).invoke(v, intent)
            pending = null; startedWidth = width; startedHeight = height
            onStarted()
            lastReport = "display=$displayId host=${width}x$height surface=$frame displaySize=$size"
            listOf(300L, 1000L).forEach { delay -> handler.postDelayed({ normalizeDisplayTask() }, delay) }
        } catch (t: Throwable) { fail((t.cause ?: t).toString()) }
    }

    private fun normalizeDisplayTask() {
        if (released || displayId < 1) return
        runCatching {
            val service = ActivityManager::class.java.getMethod("getService").invoke(null)
            val api = Class.forName("android.app.IActivityManager")
            val stacks = api.getMethod("getAllStackInfos").invoke(service) as List<*>
            for (info in stacks.filterNotNull()) {
                val cls = info.javaClass
                if (cls.getField("displayId").getInt(info) != displayId) continue
                val id = cls.getField("stackId").getInt(info)
                api.getMethod("resizeStack", Int::class.javaPrimitiveType, Rect::class.java,
                    Boolean::class.javaPrimitiveType, Boolean::class.javaPrimitiveType,
                    Boolean::class.javaPrimitiveType, Int::class.javaPrimitiveType)
                    .invoke(service, id, null, false, false, false, -1)
                lastReport += " stack=$id bounds=${cls.getField("bounds").get(info)}"
            }
        }.onFailure { lastReport += " normalize=${it.cause ?: it}" }
    }

    private val rebuild = Runnable {
        if (!released && active && isShown && startedWidth > 0 && (width != startedWidth || height != startedHeight)) {
            // ActivityView resizes its existing virtual display in surfaceChanged. Keep it alive:
            // removing/recreating PUBLIC displays can reparent tasks or hit Android 9 WM races.
            normalizeDisplayTask()
            startedWidth = width; startedHeight = height
        }
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (w > 0 && h > 0 && startedWidth > 0 && (w != startedWidth || h != startedHeight)) {
            handler.removeCallbacks(rebuild); handler.postDelayed(rebuild, 300)
        }
    }
    private fun fail(message: String) {
        lastError = message; Log.w("EmbeddedMap", message)
        pending = null
        onFail(message)
    }
    fun release() {
        if (released) return
        generation++
        // Android 9 migrates live tasks from a removed PUBLIC display to display 0.
        // Remove tasks on this owned display FIRST, otherwise resize/rebuild can cover
        // the host with an old half-sized activity (and trigger framework transition bugs).
        val owned = activityView?.let(::virtualDisplay)?.display?.displayId ?: displayId
        if (owned > 0) runCatching {
            val service = ActivityManager::class.java.getMethod("getService").invoke(null)
            val api = Class.forName("android.app.IActivityManager")
            val stacks = api.getMethod("getAllStackInfos").invoke(service) as List<*>
            for (info in stacks.filterNotNull()) {
                val cls = info.javaClass
                if (cls.getField("displayId").getInt(info) == owned) {
                    for (task in cls.getField("taskIds").get(info) as IntArray)
                        check(api.getMethod("removeTask", Int::class.javaPrimitiveType).invoke(service, task) != false)
                }
            }
        }.onFailure { lastError = "Dọn display=$owned: ${it.cause ?: it}" }
        released = true; ready = false; pending = null; displayId = -1
        startedWidth = 0; startedHeight = 0
        handler.removeCallbacksAndMessages(null)
        val old = activityView
        activityView = null; removeAllViews()
        // Let task/window removal animations finish while their Display is still valid.
        // This queue is independent of the next generation's start()/cancel callbacks.
        if (old != null) Handler(Looper.getMainLooper()).postDelayed({
            runCatching { old.javaClass.getMethod("release").invoke(old) }
        }, 1000)
    }
    fun setActive(value: Boolean) {
        active = value
        handler.removeCallbacksAndMessages(null)
        if (active) handler.post { rebuild.run(); tryStart(); if (pending == null && startedWidth > 0) normalizeDisplayTask() }
    }
    override fun onAttachedToWindow() { super.onAttachedToWindow(); if (active) handler.post { tryStart() } }
    override fun onDetachedFromWindow() { release(); super.onDetachedFromWindow() }
    companion object {
        fun embeddingRejection(context: Context, intent: Intent): String? {
            val component = intent.component ?: intent.resolveActivity(context.packageManager)
                ?: return "Không tìm thấy ứng dụng"
            if (component.packageName == context.packageName) return "Màn hình chính phải mở trên màn hình xe"
            val homes = context.packageManager.queryIntentActivities(Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME), 0)
            if (homes.any { it.activityInfo.packageName == component.packageName })
                return "Launcher gốc chỉ mở trên màn hình xe"
            val info = runCatching { context.packageManager.getActivityInfo(component, 0) }.getOrNull()
                ?: return "Không đọc được thông tin ứng dụng"
            val mode = runCatching { info.javaClass.getField("resizeMode").getInt(info) }.getOrNull()
            if (mode == 0) return "Ứng dụng không hỗ trợ ô màn hình; hãy mở toàn màn hình"
            return null
        }
        @Volatile var lastError: String? = null
        @Volatile var lastReport = "Chưa nhúng app"
    }
}
