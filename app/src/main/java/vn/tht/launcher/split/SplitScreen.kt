package vn.tht.launcher.split

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PixelFormat
import android.graphics.Point
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import vn.tht.launcher.ui.Icons
import vn.tht.launcher.ui.Ui
import java.lang.reflect.InvocationTargetException

/**
 * Chia đôi màn hình bằng chế độ split-screen có sẵn của Android 9, điều khiển qua IActivityManager (API ẩn):
 * đưa task app trái vào nửa chính (windowingMode 3), app phải vào nửa phụ (4), rồi đặt kích thước hai nửa
 * theo tỉ lệ. Cần quyền hệ thống (APK ký khoá platform của đầu xe); không có quyền thì báo lỗi rõ ràng.
 *
 * Khi đang chia đôi, một thanh kéo nổi (cửa sổ overlay) nằm giữa hai app: kéo để đổi tỉ lệ (được lưu),
 * nút trên thanh để đổi bên và thoát chia đôi.
 */
class SplitScreen(private val context: Context, private val toast: (String) -> Unit) {
    private val handler = Handler(Looper.getMainLooper())
    private val wm = context.getSystemService(WindowManager::class.java)

    /** Cặp đang mở (null = không chia đôi). */
    var openId: String? = null
        private set
    /** Khoảng chừa ở đáy màn (px) cho dock nổi; vùng chia đôi kết thúc phía trên dock. */
    var bottomInset: () -> Int = { 0 }
    /** Gọi khi mở/thoát chia đôi (để hiện/ẩn dock nổi). */
    var onStateChanged: () -> Unit = {}
    private var divider: View? = null
    private var dividerLp: WindowManager.LayoutParams? = null
    private var busy = false
    /** Cặp đang mở; giữ cả cặp chưa lưu (chia đôi nhanh từ thanh bên) để đổi bên / kéo tỉ lệ vẫn dùng được. */
    private var current: Split? = null

    // ---- Mở / đóng ----

    fun toggle(split: Split) = if (openId == split.id) close() else open(split)

    fun open(split: Split) {
        if (busy) return
        if (split.left == split.right) return toast("Chọn hai app khác nhau")
        if (am() == null) return toast("Đầu xe không cho chia đôi màn hình (thiếu API hệ thống)")
        // Quyền chỉ có khi APK ký khoá platform của đầu xe: thiếu thì báo ngay, không mở app nào
        if (context.checkSelfPermission(PERMISSION) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
            return toast("Chia đôi cần bản ký khoá platform của đầu xe (thiếu quyền hệ thống)")
        }
        busy = true
        removeDivider()
        ensureTask(split.left) { leftTask ->
            if (leftTask == null) return@ensureTask fail("Không mở được chia đôi: app chưa kịp khởi động")
            if (!moveToSplit(leftTask, WINDOWING_PRIMARY)) return@ensureTask fail("Đầu xe không mở app này ở chế độ chia đôi")
            handler.postDelayed({
                // Bên trái đã vào nửa chính: bên phải hỏng thì phải thoát chia đôi, nếu không màn hình kẹt
                // ở nửa app + nửa trống (nút Home của Android 9 không thoát được)
                ensureTask(split.right) { rightTask ->
                    if (rightTask == null) return@ensureTask abort("Không mở được chia đôi: app bên phải chưa kịp khởi động")
                    if (!moveToSplit(rightTask, WINDOWING_SECONDARY)) return@ensureTask abort("Đầu xe không mở app bên phải ở chế độ chia đôi")
                    handler.postDelayed({
                        current = split
                        openId = split.id
                        onStateChanged()
                        place(split.ratio)
                        busy = false
                        showDivider(split)
                        handler.postDelayed(watch, WATCH_MS)
                    }, SETTLE_MS)
                }
            }, SETTLE_MS)
        }
    }

    fun close() {
        handler.removeCallbacks(watch)
        removeDivider()
        openId = null
        current = null
        call("dismissSplitScreenMode", arrayOf(Boolean::class.javaPrimitiveType!!), false)
        onStateChanged()
    }

    /** Launcher bị huỷ: gỡ thanh kéo, dừng theo dõi (không thoát chia đôi). */
    fun release() {
        handler.removeCallbacksAndMessages(null)
        removeDivider()
    }

    private fun fail(msg: String) {
        busy = false
        toast(msg)
    }

    /** Hỏng giữa chừng (đã có app trong nửa chính): thoát chia đôi, trả màn hình như cũ. */
    private fun abort(msg: String) {
        call("dismissSplitScreenMode", arrayOf(Boolean::class.javaPrimitiveType!!), false)
        fail(msg)
    }

    /** Thoát chia đôi bằng cách khác (nút Home, app tự đóng…): bỏ thanh kéo. */
    private val watch = object : Runnable {
        override fun run() {
            if (openId == null) return
            if (stackId(WINDOWING_PRIMARY) == null) {
                removeDivider(); openId = null; current = null; onStateChanged()
            } else handler.postDelayed(this, WATCH_MS)
        }
    }

    // ---- IActivityManager (API ẩn) ----

    private fun am(): Any? = runCatching {
        android.app.ActivityManager::class.java.getMethod("getService").invoke(null)
    }.getOrNull()

    private fun iam(): Class<*> = Class.forName("android.app.IActivityManager")

    /** Gọi hàm IActivityManager; null nếu lỗi (thiếu quyền, hàm không có). */
    private fun call(name: String, types: Array<Class<*>>, vararg args: Any?): Any? = try {
        iam().getMethod(name, *types).invoke(am(), *args)
    } catch (t: Throwable) {
        val cause = (t as? InvocationTargetException)?.targetException ?: t
        Log.w(TAG, "$name lỗi: $cause")
        null
    }

    /** Task đang chạy của gói [pkg] trên màn hình chính (display 0). */
    private fun taskOf(pkg: String): Int? {
        val infos = call("getAllStackInfos", emptyArray()) as? List<*> ?: return null
        for (info in infos) {
            if (info == null) continue
            val cls = info.javaClass
            val display = runCatching { cls.getField("displayId").getInt(info) }.getOrDefault(0)
            if (display != 0) continue
            val names = runCatching { cls.getField("taskNames").get(info) as? Array<*> }.getOrNull() ?: continue
            val ids = runCatching { cls.getField("taskIds").get(info) as? IntArray }.getOrNull() ?: continue
            names.forEachIndexed { i, n ->
                val cn = android.content.ComponentName.unflattenFromString(n as? String ?: return@forEachIndexed)
                if (cn?.packageName == pkg && i < ids.size) return ids[i]
            }
        }
        return null
    }

    /** Có task của [pkg] thì trả ngay; không thì mở app rồi chờ task xuất hiện. */
    private fun ensureTask(pkg: String, done: (Int?) -> Unit) {
        taskOf(pkg)?.let { return done(it) }
        val intent = context.packageManager.getLaunchIntentForPackage(pkg)?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            ?: return done(null)
        try {
            context.startActivity(intent, ActivityOptions.makeBasic().setLaunchDisplayId(0).toBundle())
        } catch (t: Throwable) {
            Log.w(TAG, "Không mở được $pkg: $t")
            return done(null)
        }
        var tries = 0
        handler.postDelayed(object : Runnable {
            override fun run() {
                val id = taskOf(pkg)
                when {
                    id != null -> done(id)
                    ++tries >= START_TRIES -> done(null)
                    else -> handler.postDelayed(this, START_POLL_MS)
                }
            }
        }, START_POLL_MS)
    }

    /** Đưa task vào nửa chính (bên trái) hoặc nửa phụ (bên phải). */
    private fun moveToSplit(taskId: Int, windowingMode: Int): Boolean {
        val options: Bundle = ActivityOptions.makeBasic().toBundle().apply {
            putInt("android.activity.windowingMode", windowingMode)
            putInt("android:activity.splitScreenCreateMode", CREATE_MODE_TOP_OR_LEFT)
        }
        val r = call("startActivityFromRecents", arrayOf(Int::class.javaPrimitiveType!!, Bundle::class.java), taskId, options)
        Log.i(TAG, "task $taskId -> windowingMode $windowingMode: $r")
        return r != null
    }

    private fun stackId(windowingMode: Int): Int? {
        val info = call("getStackInfo", arrayOf(Int::class.javaPrimitiveType!!, Int::class.javaPrimitiveType!!),
            windowingMode, ACTIVITY_TYPE_STANDARD) ?: return null
        return runCatching { info.javaClass.getField("stackId").getInt(info) }.getOrNull()
    }

    private fun screen(): Point = Point().also { wm.defaultDisplay.getRealSize(it) }

    /** Đặt hai nửa theo [ratio] (% chiều rộng bên trái). */
    fun place(ratio: Int) {
        val size = screen()
        val splitX = size.x * ratio.coerceIn(Split.MIN_RATIO, Split.MAX_RATIO) / 100
        val bottom = (size.y - bottomInset()).coerceIn(size.y / 2, size.y)
        val rect = Rect::class.java
        call("resizeDockedStack", arrayOf(rect, rect, rect, rect, rect), Rect(0, 0, splitX, bottom), null, null, null, null)
        stackId(WINDOWING_SECONDARY)?.let { id ->
            val b = Boolean::class.javaPrimitiveType!!
            call("resizeStack", arrayOf(Int::class.javaPrimitiveType!!, rect, b, b, b, Int::class.javaPrimitiveType!!),
                id, Rect(splitX, 0, size.x, bottom), true, false, false, -1)
        }
        dividerLp?.let { lp ->
            lp.x = splitX - lp.width / 2
            lp.height = bottom
            divider?.let { runCatching { wm.updateViewLayout(it, lp) } }
        }
    }

    // ---- Thanh kéo giữa hai app ----

    @SuppressLint("ClickableViewAccessibility")
    private fun showDivider(split: Split) {
        val c = context
        val size = screen()
        val w = Ui.dp(c, 44)
        val handleBar = View(c).apply { background = Ui.pill(c, Color.WHITE, 3) }
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
            addView(handleBar, FrameLayout.LayoutParams(Ui.dp(c, 6), Ui.dp(c, 96), Gravity.CENTER))
        }
        val lp = WindowManager.LayoutParams(
            w, (size.y - bottomInset()).coerceIn(size.y / 2, size.y), WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            PixelFormat.TRANSLUCENT,
        ).apply {
            gravity = Gravity.TOP or Gravity.START
            x = size.x * split.ratio / 100 - w / 2
            y = 0
            title = "THT split divider"
        }
        var downX = 0f
        var startX = 0
        root.setOnTouchListener { _, e ->
            when (e.actionMasked) {
                MotionEvent.ACTION_DOWN -> { downX = e.rawX; startX = lp.x }
                MotionEvent.ACTION_MOVE -> {
                    lp.x = (startX + (e.rawX - downX).toInt()).coerceIn(
                        size.x * Split.MIN_RATIO / 100 - w / 2, size.x * Split.MAX_RATIO / 100 - w / 2)
                    runCatching { wm.updateViewLayout(root, lp) }
                }
                MotionEvent.ACTION_UP -> {
                    val ratio = ((lp.x + w / 2) * 100f / size.x).toInt().coerceIn(Split.MIN_RATIO, Split.MAX_RATIO)
                    current = current?.copy(ratio = ratio)
                    SplitStore.get(c, split.id)?.let { SplitStore.put(c, it.copy(ratio = ratio)) }
                    place(ratio)
                }
            }
            true
        }
        try {
            wm.addView(root, lp)
            divider = root
            dividerLp = lp
        } catch (t: Throwable) {
            Log.w(TAG, "Không hiện được thanh kéo: $t")
            toast("Đã chia đôi; không hiện được thanh kéo (thiếu quyền hiển thị trên app khác)")
        }
    }

    private fun swap() {
        val s = openId?.let { SplitStore.get(context, it) } ?: current ?: return
        val swapped = s.copy(left = s.right, right = s.left, ratio = 100 - s.ratio)
        // Cặp đã lưu thì lưu chiều mới; cặp chia đôi nhanh không lưu
        if (SplitStore.get(context, s.id) != null) SplitStore.put(context, swapped)
        close()
        handler.postDelayed({ open(swapped) }, SETTLE_MS)
    }

    private fun removeDivider() {
        divider?.let { runCatching { wm.removeView(it) } }
        divider = null
        dividerLp = null
    }

    private companion object {
        const val TAG = "SplitScreen"
        const val PERMISSION = "android.permission.MANAGE_ACTIVITY_STACKS"
        /** WindowConfiguration.WINDOWING_MODE_SPLIT_SCREEN_PRIMARY / SECONDARY. */
        const val WINDOWING_PRIMARY = 3
        const val WINDOWING_SECONDARY = 4
        const val ACTIVITY_TYPE_STANDARD = 1
        /** ActivityManager.SPLIT_SCREEN_CREATE_MODE_TOP_OR_LEFT. */
        const val CREATE_MODE_TOP_OR_LEFT = 0
        const val SETTLE_MS = 600L
        const val START_POLL_MS = 400L
        const val START_TRIES = 20
        const val WATCH_MS = 1500L
    }
}
