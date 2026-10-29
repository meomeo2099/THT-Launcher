package vn.tht.launcher.shortcuts

import android.content.Context
import android.graphics.PixelFormat
import android.graphics.Rect
import android.provider.Settings
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import vn.tht.launcher.ui.Palette
import vn.tht.launcher.ui.Ui

/**
 * Dock nổi trên app: bản sao thanh dưới (cùng shortcuts.json) trong cửa sổ overlay, hiện khi launcher bị
 * app khác che. Đặt đúng chỗ thanh dưới trên màn hình chính; không sửa được nút từ đây (nhấn giữ / "+").
 */
class FloatingDock(private val c: Context, private val actions: DockBar.Actions) {
    private val wm = c.getSystemService(WindowManager::class.java)
    private var root: FrameLayout? = null
    private var bar: DockBar? = null
    private var bounds = Rect()

    val shown: Boolean get() = root != null

    /** Mép trên của dock trên màn hình (px); vùng chia đôi kết thúc tại đây. */
    val top: Int get() = bounds.top

    /** Hiện dock tại [where] (toạ độ màn hình của thanh dưới); không có quyền overlay thì bỏ qua. */
    fun show(where: Rect) {
        if (root != null) return
        if (!Settings.canDrawOverlays(c)) {
            Log.w(TAG, "Thiếu quyền hiển thị trên app khác")
            return
        }
        bounds = Rect(where)
        val row = LinearLayout(c).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }
        val box = FrameLayout(c).apply {
            background = Ui.pill(c, Palette.dock, 26)
            val p = Ui.dp(c, 8)
            setPadding(p, 0, p, 0)
            addView(HorizontalScrollView(c).apply {
                isHorizontalScrollBarEnabled = false
                isFillViewport = true
                addView(row, ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT))
            }, FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT).apply {
                marginEnd = Ui.dp(c, 80)
            })
            addView(android.widget.ImageView(c).apply {
                contentDescription = "Về màn hình chính THT"
                val pad = Ui.dp(c, 14)
                setPadding(pad, pad, pad, pad)
                setImageDrawable(vn.tht.launcher.ui.Icons.get(c, "home"))
                background = Ui.pill(c, Palette.button, 16)
                setOnClickListener { actions.goHome() }
            }, FrameLayout.LayoutParams(Ui.dp(c, 64), Ui.dp(c, 64), Gravity.END or Gravity.CENTER_VERTICAL).apply {
                marginEnd = Ui.dp(c, 8)
            })
        }
        val lp = WindowManager.LayoutParams(
            bounds.width(), bounds.height(), WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            PixelFormat.TRANSLUCENT,
        ).apply {
            gravity = Gravity.TOP or Gravity.START
            x = bounds.left
            y = bounds.top
            title = "THT floating dock"
        }
        try {
            bar = DockBar(c, row, actions, editable = false).also { it.reload() }
            vn.tht.launcher.ui.ScreenOverlayPosition(box, wm, lp)
            wm.addView(box, lp)
            root = box
        } catch (t: Throwable) {
            Log.w(TAG, "Không hiện được dock nổi: $t")
            bar = null
        }
    }

    fun hide() {
        root?.let { runCatching { wm.removeView(it) } }
        root = null
        bar = null
    }

    fun update(s: DockBar.Status) = bar?.update(s)

    private companion object {
        const val TAG = "FloatingDock"
    }
}
