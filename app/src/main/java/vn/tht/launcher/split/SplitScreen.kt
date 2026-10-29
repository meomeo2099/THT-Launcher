package vn.tht.launcher.split

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.PixelFormat
import android.graphics.Point
import android.graphics.Rect
import android.os.Handler
import android.os.Looper
import android.os.PowerManager
import android.provider.Settings
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import vn.tht.launcher.system.ExternalAppLauncher
import vn.tht.launcher.system.ExternalLaunchPolicy
import vn.tht.launcher.ui.Icons
import vn.tht.launcher.ui.Ui

/** Native Android split with a cancellable transaction and task-identity readback. */
class SplitScreen(private val context: Context, private val toast: (String) -> Unit) : ExternalAppLauncher.SplitOwner {
    private val handler = Handler(Looper.getMainLooper())
    private val wm = context.getSystemService(WindowManager::class.java)
    var bottomInset: () -> Int = { 0 }
    var usableBounds: () -> Rect? = { null }
    var onStateChanged: () -> Unit = {}
    val openId: String? get() = session.request?.id?.takeIf { session.active }
    val hasWork: Boolean get() = session.hasWork
    fun owns(id: String): Boolean = session.request?.id == id
    private var divider: View? = null
    private var dividerLp: WindowManager.LayoutParams? = null
    private var dividerPosition: vn.tht.launcher.ui.ScreenOverlayPosition? = null
    private var current: Split? = null
    private var activeTasks: SplitSession.Tasks? = null
    private var prepared: SplitSession.Request? = null
    private var fresh = emptySet<String>()
    private var lastLayout: SplitGeometry.Layout? = null
    private var capturedArea: SplitGeometry.Bounds? = null
    private var capturedSize: Point? = null
    private var lastProblem = ""
    private var dragging = false
    private val watchPolicy = SplitWatchPolicy()
    private data class Recovery(val request: SplitSession.Request, val tasks: SplitSession.Tasks?, val primary: Int, val committed: Boolean)
    private var cleanupRecord: Recovery? = null
    private val driver = AndroidDriver()
    private val session: SplitSession by lazy { SplitSession(driver, object : SplitSession.Scheduler {
        override fun post(delayMs: Long, action: () -> Unit) { handler.postDelayed({ action() }, delayMs) }
        override fun cancelAll() { handler.removeCallbacksAndMessages(null) }
    }, object : SplitSession.Events {
        override fun opening(request: SplitSession.Request) {
            removeDivider(); current = null; activeTasks = null
            onStateChanged()
        }
        override fun active(request: SplitSession.Request, tasks: SplitSession.Tasks) {
            current = request.toSplit(); activeTasks = tasks
            onStateChanged()
            if (!showDivider(current!!)) { session.close(); return }
            handler.postDelayed(watch, WATCH_MS)
        }
        override fun closed() {
            removeDivider(); current = null; activeTasks = null; lastLayout = null; watchPolicy.reset()
            onStateChanged()
        }
        override fun failed(message: String, cleanupOk: Boolean) {
            lastProblem = message
            if (!cleanupOk) session.request?.let { request ->
                current = current ?: request.toSplit()
                showDivider(current!!)
            }
            toast(when (message) {
                "Unable to move primary task" -> "Ứng dụng bên trái không vào được chia đôi; đã yêu cầu khôi phục"
                "Unable to move secondary task" -> "Ứng dụng bên phải không vào được chia đôi; đã yêu cầu khôi phục"
                "Unable to restore fullscreen; retry close" -> "Chưa khôi phục được toàn màn hình; bấm × để thử lại"
                "Unable to resize split pair" -> "Không đặt được kích thước hai ô; đã yêu cầu khôi phục"
                "Unable to launch app", "App task did not appear" -> "Ứng dụng chưa mở được; không tiếp tục chia đôi"
                "Both apps resolved to the same task" -> "Hai ứng dụng đang dùng chung một task"
                "Primary task did not enter split", "Split pair did not become ready", "Split bounds did not settle" -> "Không xác nhận được đủ hai ứng dụng trong chia đôi"
                "Unable to prepare split" -> "Không chuẩn bị được chia đôi"
                else -> message
            })
        }
    }) }

    init {
        ExternalAppLauncher.registerSplit(this)
        pendingRecovery?.let { record ->
            prepared = record.request
            activeTasks = record.tasks.takeIf { record.committed }
            session.adoptForCleanup(record.request, record.tasks, record.primary)
            handler.post { if (session.hasWork) session.close() }
        }
    }
    fun toggle(split: Split) { if (owns(split.id)) close() else open(split) }
    fun open(split: Split) { session.open(SplitSession.Request(split.id, split.left, split.right, split.ratio)) }
    fun close(): Boolean = session.close()
    override fun exitForFullscreen(): Boolean = !hasWork || close()
    fun release() {
        if (!session.release()) pendingRecovery = cleanupRecord
        removeDivider()
        ExternalAppLauncher.unregisterSplit(this)
    }
    fun diagnosticReport(): String = "id=${session.request?.id} starting=${session.starting} active=${session.active} tasks=$activeTasks layout=$lastLayout error=$lastProblem"
    private fun SplitSession.Request.toSplit() = Split(id, left, right, ratio)

    private data class Stack(val id: Int, val display: Int, val visible: Boolean, val bounds: Rect,
                             val ids: IntArray, val standard: Boolean, val names: List<String?>, val topPackage: String?) {
        fun containsTop(task: Int): Boolean = ids.lastOrNull() == task
    }
    private data class Call(val ok: Boolean, val value: Any?)
    private fun call(name: String, types: Array<Class<*>>, vararg args: Any?): Call = try {
        val am = ActivityManager::class.java.getMethod("getService").invoke(null)
        Call(true, Class.forName("android.app.IActivityManager").getMethod(name, *types).invoke(am, *args))
    } catch (t: Exception) {
        lastProblem = "$name: ${t.cause ?: t}"
        Log.w(TAG, lastProblem)
        Call(false, null)
    }
    private fun invoke(name: String, types: Array<Class<*>>, vararg args: Any?): Boolean {
        val result = call(name, types, *args)
        return result.ok && ExternalLaunchPolicy.binderAccepted(result.value)
    }
    private fun decode(info: Any): Stack? = runCatching {
        val cls = info.javaClass
        Stack(cls.getField("stackId").getInt(info), cls.getField("displayId").getInt(info),
            cls.getField("visible").getBoolean(info), Rect(cls.getField("bounds").get(info) as Rect),
            cls.getField("taskIds").get(info) as IntArray, ExternalLaunchPolicy.activityType(info) == 1,
            (cls.getField("taskNames").get(info) as Array<*>).map { it as? String },
            (cls.getField("topActivity").get(info) as? android.content.ComponentName)?.packageName)
    }.getOrNull()
    private fun stack(mode: Int): Pair<Boolean, Stack?> {
        val result = call("getStackInfo", arrayOf(INT, INT), mode, 1)
        if (!result.ok) return false to null
        if (result.value == null) return true to null
        val parsed = decode(result.value!!)
        return (parsed != null) to parsed
    }
    private fun allStacks(): List<Stack>? {
        val result = call("getAllStackInfos", emptyArray())
        if (!result.ok) return null
        return (result.value as? List<*>)?.mapNotNull { it?.let(::decode) }
    }
    private fun ownedPair(tasks: SplitSession.Tasks, visible: Boolean): Boolean {
        val (a, primary) = stack(3); val (b, secondary) = stack(4)
        return a && b && primary != null && secondary != null && primary.display == 0 && secondary.display == 0 &&
            primary.containsTop(tasks.left) && secondary.containsTop(tasks.right) &&
            (!visible || primary.visible && secondary.visible) && primary.bounds.width() > 0 && secondary.bounds.width() > 0
    }

    private inner class AndroidDriver : SplitSession.Driver {
        override fun preflight(request: SplitSession.Request): String? {
            if (request.left == request.right) return "Chọn hai ứng dụng khác nhau"
            if (request.left == context.packageName || request.right == context.packageName) return "Không chọn chính THT làm ứng dụng trong ô chia đôi"
            if (context.checkSelfPermission(PERMISSION) != PackageManager.PERMISSION_GRANTED) return "Chia đôi cần APK ký platform và quyền quản lý task"
            if (!Settings.canDrawOverlays(context)) return "Cấp quyền hiển thị trên ứng dụng khác để có nút thoát chia đôi"
            val apiComplete = runCatching {
                val api = Class.forName("android.app.IActivityManager")
                api.getMethod("getAllStackInfos")
                api.getMethod("getStackInfo", INT, INT)
                api.getMethod("startActivityFromRecents", INT, android.os.Bundle::class.java)
                api.getMethod("setTaskWindowingModeSplitScreenPrimary", INT, INT, BOOL, BOOL, RECT, BOOL)
                api.getMethod("resizeDockedStack", RECT, RECT, RECT, RECT, RECT)
                api.getMethod("resizeStack", INT, RECT, BOOL, BOOL, BOOL, INT)
                api.getMethod("dismissSplitScreenMode", BOOL)
            }.isSuccess
            if (!apiComplete) return "Firmware chưa hỗ trợ đủ chức năng mở và thoát chia đôi"
            val supported = runCatching { ActivityManager::class.java.getMethod("supportsSplitScreenMultiWindow", Context::class.java).invoke(null, context) as? Boolean }.getOrNull()
            if (supported == false) return "Firmware không hỗ trợ chia đôi màn hình"
            if (listOf(request.left, request.right).any { launchIntent(it) == null }) return "Một ứng dụng của cặp đã bị gỡ hoặc không mở được"
            if (geometry(request.ratio) == null) return "Không đủ vùng hiển thị cho hai ứng dụng và dock"
            val (ok, primary) = stack(3)
            if (!ok) return "Không đọc được trạng thái chia đôi của firmware"
            if (primary != null && (!session.active || activeTasks?.let { ownedPair(it, false) } != true))
                return "Đang có phiên chia đôi ngoài THT; hãy thoát phiên đó trước"
            return null
        }
        override fun prepare(request: SplitSession.Request): Boolean = runCatching {
            prepared = request
            capturedArea = readArea()
            capturedSize = screen()
            fresh = ExternalAppLauncher.beginSplit(context, setOf(request.left, request.right))
            true
        }.getOrElse { lastProblem = it.toString(); false }
        override fun findTask(pkg: String): Int? = allStacks()?.filter { it.display == 0 }?.firstNotNullOfOrNull { info ->
            info.ids.indices.reversed().firstNotNullOfOrNull { i -> info.ids[i].takeIf {
                ExternalLaunchPolicy.belongs(info.names.getOrNull(i), pkg)
            } }
        }
        override fun launch(pkg: String): Boolean = runCatching {
            val intent = launchIntent(pkg) ?: return false
            intent.flags = intent.flags and (Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT or Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK).inv()
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            if (pkg in fresh) intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            val options = ActivityOptions.makeBasic().setLaunchDisplayId(0).apply { launchBounds = null }.toBundle()
            options.putInt("android.activity.windowingMode", 1)
            context.startActivity(intent, options)
            true
        }.getOrElse { lastProblem = it.toString(); false }
        override fun move(task: Int, mode: Int): Boolean {
            if (mode == 3) {
                // Android 9 startActivityFromRecents(primary) leaves a task in the
                // framework's resizing-during-animation queue. Dismissing before
                // that transition ends can crash system_server (Task.setDragResizing).
                // The direct native split API avoids that Recents-only bookkeeping.
                val bounds = prepared?.let { geometry(it.ratio)?.left?.rect() } ?: return false
                val result = call("setTaskWindowingModeSplitScreenPrimary",
                    arrayOf(INT, INT, BOOL, BOOL, RECT, BOOL), task, 0, true, false, bounds, false)
                Log.i(TAG, "task=$task requestedMode=$mode directPrimary=${result.value}")
                return result.ok && result.value == true
            }
            val options = ActivityOptions.makeBasic().setLaunchDisplayId(0).toBundle().apply {
                putInt("android.activity.windowingMode", mode)
                if (mode == 3) putInt("android:activity.splitScreenCreateMode", 0)
            }
            val result = call("startActivityFromRecents", arrayOf(INT, android.os.Bundle::class.java), task, options)
            Log.i(TAG, "task=$task requestedMode=$mode accepted=${result.value}")
            return result.ok && SplitGeometry.acceptedStart(result.value)
        }
        override fun primaryMatches(left: Int): Boolean = stack(3).let { (ok, s) -> ok && s != null && s.display == 0 && s.containsTop(left) }
        override fun pairMatches(tasks: SplitSession.Tasks): Boolean = ownedPair(tasks, true)
        override fun place(request: SplitSession.Request, tasks: SplitSession.Tasks): Boolean = resize(request.ratio, tasks)
        override fun layoutMatches(request: SplitSession.Request, tasks: SplitSession.Tasks): Boolean {
            val expected = geometry(request.ratio) ?: return false
            val (a, left) = stack(3); val (b, right) = stack(4)
            if (!a || !b || left == null || right == null || !ownedPair(tasks, true)) return false
            val tolerance = Ui.dp(context, 12)
            return SplitGeometry.near(left.bounds.bounds(), expected.left, tolerance) &&
                SplitGeometry.near(right.bounds.bounds(), expected.right, tolerance)
        }
        override fun rollback(tasks: SplitSession.Tasks?, primary: Int?): Boolean {
            val request = prepared ?: session.request
            if (request != null && primary != null) cleanupRecord = Recovery(request, tasks, primary, activeTasks != null)
            val cleaned = rollbackNative(tasks, primary)
            if (cleaned) {
                cleanupRecord = null
                if (pendingRecovery?.primary == primary) pendingRecovery = null
            }
            return cleaned
        }
        private fun rollbackNative(tasks: SplitSession.Tasks?, primary: Int?): Boolean {
            val (ok, currentPrimary) = stack(3); val (secondaryOk, originalSecondary) = stack(4)
            if (!ok || !secondaryOk) return false
            // Missing pane is an incomplete owned split. Different tasks belong to a new user session.
            if (primary != null && !SplitOwnership.mayClean(currentPrimary?.ids?.lastOrNull(),
                originalSecondary?.ids?.lastOrNull(), primary, tasks?.right, activeTasks != null,
                originalSecondary?.topPackage == context.packageName)) return true
            if (currentPrimary != null) invoke("dismissSplitScreenMode", arrayOf(BOOL), false)
            var (read, remaining) = stack(3)
            if (!read) return false
            if (remaining?.containsTop(primary ?: -1) == true) {
                if (!invoke("setTaskWindowingMode", arrayOf(INT, INT, BOOL), primary, 1, false)) return false
                val reread = stack(3); read = reread.first; remaining = reread.second
                if (!read || remaining?.containsTop(primary ?: -1) == true) return false
            }
            val (secondaryRead, secondary) = stack(4)
            if (!secondaryRead) return false
            if (tasks != null && secondary?.containsTop(tasks.right) == true) {
                if (!invoke("setTaskWindowingMode", arrayOf(INT, INT, BOOL), tasks.right, 1, false)) return false
                val (again, remainingRight) = stack(4)
                if (!again || remainingRight?.containsTop(tasks.right) == true) return false
            }
            // Clear fullscreen stack overrides left on our two task IDs, never HOME or a mixed stack.
            val targetIds = tasks?.let { setOf(it.left, it.right) } ?: setOfNotNull(primary)
            val snapshots = allStacks() ?: return false
            snapshots.filter { s -> s.display == 0 && s.standard && s.ids.any { it in targetIds } }.forEach { s ->
                val expected = prepared?.let { setOf(it.left, it.right, context.packageName) }.orEmpty()
                if (s.names.isNotEmpty() && s.names.all { n -> expected.any { ExternalLaunchPolicy.belongs(n, it) } }) {
                    if (!invoke("resizeStack", arrayOf(INT, RECT, BOOL, BOOL, BOOL, INT), s.id, null, false, false, false, -1)) return false
                }
            }
            return true
        }
        override fun finished() {
            prepared?.let { ExternalAppLauncher.finishSplit(setOf(it.left, it.right)) }
            vn.tht.launcher.system.AppWindowPolicy.apply(context, setOf(context.packageName))
            prepared = null; fresh = emptySet(); capturedArea = null; capturedSize = null
        }
    }
    private fun launchIntent(pkg: String): Intent? = context.packageManager.getLaunchIntentForPackage(pkg)?.let { intent ->
        val component = intent.component ?: return null
        val activity = runCatching { context.packageManager.getActivityInfo(component, 0) }.getOrNull() ?: return null
        intent.takeIf { activity.enabled && activity.applicationInfo.enabled && activity.exported }
    }
    private fun screen(): Point = Point().also { wm.defaultDisplay.getRealSize(it) }
    private fun readArea(): SplitGeometry.Bounds {
        val size = screen()
        val rect = usableBounds() ?: Rect(0, 0, size.x, size.y)
        return SplitGeometry.Bounds(rect.left.coerceIn(0, size.x), rect.top.coerceIn(0, size.y),
            rect.right.coerceIn(0, size.x), rect.bottom.coerceIn(0, size.y))
    }
    private fun area(): SplitGeometry.Bounds {
        if (capturedArea != null && capturedSize != screen()) {
            capturedArea = readArea(); capturedSize = screen()
        }
        return capturedArea ?: readArea()
    }
    private fun geometry(ratio: Int) = SplitGeometry.layout(area(), ratio, screen().y - bottomInset().coerceAtLeast(0))
    private fun SplitGeometry.Bounds.rect() = Rect(left, top, right, bottom)
    private fun Rect.bounds() = SplitGeometry.Bounds(left, top, right, bottom)
    private fun resize(ratio: Int, tasks: SplitSession.Tasks): Boolean {
        if (!ownedPair(tasks, true)) return false
        val layout = geometry(ratio) ?: return false
        if (!invoke("resizeDockedStack", arrayOf(RECT, RECT, RECT, RECT, RECT), layout.left.rect(), null, null, null, null)) return false
        val (_, right) = stack(4)
        if (right == null || !right.containsTop(tasks.right)) return false
        if (!invoke("resizeStack", arrayOf(INT, RECT, BOOL, BOOL, BOOL, INT), right.id, layout.right.rect(), true, false, false, -1)) return false
        lastLayout = layout
        dividerLp?.let { lp ->
            lp.height = layout.left.height
            dividerPosition?.place(layout.divider - lp.width / 2, layout.left.top)
        }
        return true
    }
    fun place(ratio: Int): Boolean {
        if (!session.active) return false
        val tasks = activeTasks ?: return false
        if (!resize(ratio, tasks)) { session.close(); return false }
        current = current?.copy(ratio = ratio.coerceIn(Split.MIN_RATIO, Split.MAX_RATIO))
        return true
    }
    private val watch = object : Runnable {
        override fun run() {
            if (!session.active) return
            if (context.getSystemService(PowerManager::class.java)?.isInteractive == false) {
                handler.postDelayed(this, WATCH_MS); return
            }
            val tasks = activeTasks ?: return
            val (readLeft, left) = stack(3); val (readRight, right) = stack(4)
            if (!readLeft || !readRight) { handler.postDelayed(this, WATCH_MS); return }
            if (!SplitOwnership.mayClean(left?.ids?.lastOrNull(), right?.ids?.lastOrNull(), tasks.left,
                tasks.right, true, right?.topPackage == context.packageName)) { session.abandon(); return }
            if (left == null || right == null || right.topPackage == context.packageName ||
                left.bounds.width() <= 0 || right.bounds.width() <= 0 || left.bounds.height() <= 0 || right.bounds.height() <= 0) {
                session.close(); return
            }
            if (!ownedPair(tasks, true)) removeDivider()
            else {
                val split = current ?: return
                val expected = geometry(split.ratio) ?: run { session.close(); return }
                val actual = SplitGeometry.Layout(left.bounds.bounds(), right.bounds.bounds())
                when (watchPolicy.observe(actual, expected, Ui.dp(context, 12), dragging)) {
                    SplitWatchPolicy.Action.CLOSE -> { session.close(); return }
                    SplitWatchPolicy.Action.REFIT -> {
                        val ratio = if (expected != lastLayout) split.ratio else SplitGeometry.ratio(area(), left.bounds.right)
                        if (!place(ratio)) return
                        SplitStore.get(context, split.id)?.let { SplitStore.put(context, it.copy(ratio = ratio)) }
                    }
                    SplitWatchPolicy.Action.WAIT -> Unit
                }
                if (divider == null && !showDivider(current ?: split)) { session.close(); return }
            }
            handler.postDelayed(this, WATCH_MS)
        }
    }

    // ---- Thanh kéo giữa hai app ----

    @SuppressLint("ClickableViewAccessibility")
    private fun showDivider(split: Split): Boolean {
        removeDivider()
        val layout = geometry(split.ratio) ?: return false
        val c = context
        val w = Ui.dp(c, 44)
        fun button(icon: String, onClick: () -> Unit) = ImageView(c).apply {
            val p = Ui.dp(c, 8)
            setPadding(p, p, p, p)
            background = Ui.pill(c, Color.argb(220, 20, 24, 32), 20)
            setImageDrawable(Icons.get(c, icon, Color.WHITE))
            setOnClickListener { onClick() }
        }
        val root = FrameLayout(c).apply {
            addView(LinearLayout(c).apply {
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER_HORIZONTAL
                addView(button("close") { close() }, LinearLayout.LayoutParams(Ui.dp(c, 40), Ui.dp(c, 40)))
                addView(button("recirc") { swap() }, LinearLayout.LayoutParams(Ui.dp(c, 40), Ui.dp(c, 40)).apply {
                    topMargin = Ui.dp(c, 8)
                })
            }, FrameLayout.LayoutParams(-2, -2, Gravity.TOP or Gravity.CENTER_HORIZONTAL).apply { topMargin = Ui.dp(c, 12) })
            // Native split already renders its handle. Drawing a second white handle here
            // creates two controls (with different vertical centres above the THT dock).
        }
        val lp = WindowManager.LayoutParams(
            w, layout.left.height, WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            PixelFormat.TRANSLUCENT,
        ).apply {
            gravity = Gravity.TOP or Gravity.START
            x = layout.divider - w / 2
            y = layout.left.top
            title = "THT split divider"
        }
        var downX = 0f
        var startX = 0
        var startRatio = split.ratio
        var lastResizeAt = 0L
        root.setOnTouchListener { _, e ->
            when (e.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    dragging = true; downX = e.rawX
                    startX = dividerPosition?.x ?: (layout.divider - w / 2)
                    startRatio = current?.ratio ?: split.ratio
                    lastResizeAt = 0L
                }
                MotionEvent.ACTION_MOVE -> {
                    val x = (startX + (e.rawX - downX).toInt()).coerceIn(
                        (geometry(Split.MIN_RATIO)?.divider ?: layout.divider) - w / 2,
                        (geometry(Split.MAX_RATIO)?.divider ?: layout.divider) - w / 2)
                    val now = android.os.SystemClock.uptimeMillis()
                    // Move the native boundary too, rather than dragging an independent overlay
                    // and leaving the OEM divider at its previous position until ACTION_UP.
                    if (now - lastResizeAt >= 70) {
                        lastResizeAt = now
                        place(SplitGeometry.ratio(area(), x + w / 2))
                    }
                }
                MotionEvent.ACTION_UP -> {
                    dragging = false
                    val x = (startX + (e.rawX - downX).toInt()) + w / 2
                    val ratio = SplitGeometry.ratio(area(), x)
                    if (place(ratio)) SplitStore.get(c, split.id)?.let { SplitStore.put(c, it.copy(ratio = ratio)) }
                }
                MotionEvent.ACTION_CANCEL -> { dragging = false; place(startRatio) }
            }
            true
        }
        try {
            val position = vn.tht.launcher.ui.ScreenOverlayPosition(root, wm, lp)
            wm.addView(root, lp)
            divider = root
            dividerLp = lp
            dividerPosition = position
            return true
        } catch (t: Throwable) {
            Log.w(TAG, "Không hiện được thanh kéo: $t")
            toast("Không hiện được thanh điều khiển; đang khôi phục toàn màn hình")
            return false
        }
    }

    private fun swap() {
        if (!session.active) return
        val s = current ?: return
        val swapped = s.copy(left = s.right, right = s.left, ratio = 100 - s.ratio)
        if (SplitStore.get(context, s.id) != null) SplitStore.put(context, swapped)
        open(swapped)
    }

    private fun removeDivider() {
        dragging = false
        divider?.let { runCatching { wm.removeView(it) } }
        divider = null
        dividerLp = null
        dividerPosition = null
    }

    private companion object {
        private var pendingRecovery: Recovery? = null // Immutable IDs only: never retain an Activity across recreation.
        const val TAG = "SplitScreen"
        const val PERMISSION = "android.permission.MANAGE_ACTIVITY_STACKS"
        const val WATCH_MS = 700L
        val INT = Int::class.javaPrimitiveType!!
        val BOOL = Boolean::class.javaPrimitiveType!!
        val RECT = Rect::class.java
    }
}
