package vn.tht.launcher.home

import vn.tht.launcher.ui.Palette
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import vn.tht.launcher.R
import vn.tht.launcher.ui.Ui
import kotlin.math.roundToInt

/**
 * Khung chứa widget, đặt mỗi widget theo [WidgetSpec] (dp trên khung thiết kế, co theo kích thước thật).
 *
 * Chế độ sửa: kéo widget để di chuyển, kéo chấm góc phải-dưới để đổi kích thước, nút × để xoá.
 * Vị trí bám lưới [HomeLayout.GRID] dp và không vượt khỏi khung.
 */
class HomeCanvas(context: Context) : ViewGroup(context) {

    /** Gọi khi bố cục thay đổi trong chế độ sửa (để lưu). */
    var onChanged: () -> Unit = {}
    /** Nhấn giữ trên khung/widget khi không sửa: vào chế độ sửa. */
    var onLongPress: () -> Unit = {}

    var editing = false
        private set

    /** Tỉ lệ giao diện (Cài đặt → 75–150%): phóng to/thu nhỏ nội dung widget, giữ nguyên vị trí. */
    var uiScale = 1f
        set(v) { field = v; requestLayout() }

    private val density = resources.displayMetrics.density
    private val frames = mutableListOf<WidgetFrame>()

    /** Hệ số co từ dp thiết kế sang px thật, riêng cho từng chiều để luôn phủ kín khung. */
    private var sx = density
    private var sy = density

    private val gridPaint = Paint().apply {
        color = Color.argb(60, 255, 255, 255)
        strokeWidth = 1f
        pathEffect = DashPathEffect(floatArrayOf(4f, 8f), 0f)
    }

    init {
        setWillNotDraw(false)
        setOnLongClickListener { if (!editing) onLongPress(); true }
    }

    fun specs(): List<WidgetSpec> = frames.map { it.spec }

    fun add(spec: WidgetSpec, content: View) {
        val f = WidgetFrame(context, spec, content)
        frames += f
        addView(f)
    }

    fun remove(spec: WidgetSpec) {
        frames.firstOrNull { it.spec.id == spec.id }?.let { frames -= it; removeView(it) }
    }

    fun clear() {
        frames.clear()
        removeAllViews()
    }

    fun setEditing(on: Boolean) {
        editing = on
        frames.forEach { it.setEditing(on) }
        invalidate()
    }

    /** Callback xoá do MainActivity gắn (để huỷ widget). */
    var onRemove: (WidgetSpec) -> Unit = {}
    /** Chạm (không kéo) một widget trong chế độ sửa: mở hộp nhập vị trí/kích thước. */
    var onWidgetTap: (WidgetSpec) -> Unit = {}

    /** Chỉ hiện các widget thoả [keep] (null = hiện tất cả). */
    fun showOnly(keep: ((WidgetSpec) -> Boolean)?) {
        frames.forEach { it.visibility = if (keep == null || keep(it.spec)) VISIBLE else INVISIBLE }
    }

    /** Dựng lại vị trí sau khi đổi số liệu bằng tay. */
    fun relayout() = requestLayout()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val w = MeasureSpec.getSize(widthMeasureSpec)
        val h = MeasureSpec.getSize(heightMeasureSpec)
        sx = w.toFloat() / HomeLayout.DESIGN_W
        sy = h.toFloat() / HomeLayout.DESIGN_H
        for (f in frames) {
            f.measure(
                MeasureSpec.makeMeasureSpec(pxX(f.spec.w), MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(pxY(f.spec.h), MeasureSpec.EXACTLY),
            )
        }
        setMeasuredDimension(w, h)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (f in frames) {
            val x = pxX(f.spec.x)
            val y = pxY(f.spec.y)
            f.layout(x, y, x + f.measuredWidth, y + f.measuredHeight)
            f.pivotX = 0f
            f.pivotY = 0f
            f.scaleX = uiScale
            f.scaleY = uiScale
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (!editing) return
        var x = 0f
        while (x < width) { canvas.drawLine(x, 0f, x, height.toFloat(), gridPaint); x += HomeLayout.GRID * 4 * sx }
        var y = 0f
        while (y < height) { canvas.drawLine(0f, y, width.toFloat(), y, gridPaint); y += HomeLayout.GRID * 4 * sy }
    }

    private fun pxX(dp: Int) = (dp * sx).roundToInt()
    private fun pxY(dp: Int) = (dp * sy).roundToInt()

    private fun snap(v: Float) = ((v / HomeLayout.GRID).roundToInt() * HomeLayout.GRID)

    /** Khung bao một widget: chặn chạm khi sửa, vẽ viền, nút xoá và tay nắm đổi cỡ. */
    @SuppressLint("ViewConstructor", "ClickableViewAccessibility")
    private inner class WidgetFrame(c: Context, val spec: WidgetSpec, val content: View) : FrameLayout(c) {
        private val remove = TextView(c).apply {
            text = "×"
            textSize = 26f
            gravity = Gravity.CENTER
            setTextColor(Color.WHITE)
            background = Ui.pill(c, Palette.danger, 22)
            visibility = GONE
            setOnClickListener { this@HomeCanvas.remove(spec); onRemove(spec); onChanged() }
        }
        private val handle = View(c).apply {
            background = Ui.pill(c, Palette.accent, 14)
            visibility = GONE
        }
        private var downX = 0f
        private var downY = 0f
        private var startX = 0
        private var startY = 0
        private var startW = 0
        private var startH = 0
        private var resizing = false
        /** Chỉ kéo khi đã nhận ACTION_DOWN trong chế độ sửa (tránh kéo theo cú nhấn giữ vừa mở chế độ sửa). */
        private var tracking = false

        init {
            addView(content, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
            addView(remove, LayoutParams(Ui.dp(c, 44), Ui.dp(c, 44), Gravity.TOP or Gravity.END))
            addView(handle, LayoutParams(Ui.dp(c, 28), Ui.dp(c, 28), Gravity.BOTTOM or Gravity.END))
            // Nhấn giữ trên nền widget (không phải nút) cũng vào chế độ sửa
            content.setOnLongClickListener { if (!editing) onLongPress(); true }
        }

        fun setEditing(on: Boolean) {
            remove.visibility = if (on) VISIBLE else GONE
            handle.visibility = if (on) VISIBLE else GONE
            foreground = if (on) Ui.pill(context, Color.argb(40, 255, 255, 255), 20) else null
        }

        override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
            if (!editing) return false
            // Để nút × tự xử lý chạm
            if (hit(remove, ev)) return false
            return true
        }

        override fun onTouchEvent(ev: MotionEvent): Boolean {
            if (!editing) return super.onTouchEvent(ev)
            when (ev.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    tracking = true
                    downX = ev.rawX; downY = ev.rawY
                    startX = spec.x; startY = spec.y; startW = spec.w; startH = spec.h
                    resizing = hit(handle, ev, slopDp = 24)
                    bringToFront()
                }
                MotionEvent.ACTION_MOVE -> {
                    if (!tracking) return true
                    val dx = (ev.rawX - downX) / sx
                    val dy = (ev.rawY - downY) / sy
                    if (resizing) {
                        spec.w = snap(startW + dx).coerceIn(HomeLayout.MIN_SIZE, HomeLayout.DESIGN_W - spec.x)
                        spec.h = snap(startH + dy).coerceIn(HomeLayout.MIN_SIZE, HomeLayout.DESIGN_H - spec.y)
                    } else {
                        spec.x = snap(startX + dx).coerceIn(0, HomeLayout.DESIGN_W - spec.w)
                        spec.y = snap(startY + dy).coerceIn(0, HomeLayout.DESIGN_H - spec.h)
                    }
                    this@HomeCanvas.requestLayout()
                }
                MotionEvent.ACTION_UP -> {
                    if (!tracking) return true
                    tracking = false
                    val moved = kotlin.math.abs(ev.rawX - downX) + kotlin.math.abs(ev.rawY - downY)
                    if (moved < Ui.dp(context, 12)) onWidgetTap(spec)
                    onChanged()
                }
                MotionEvent.ACTION_CANCEL -> { tracking = false; onChanged() }
            }
            return true
        }

        private fun hit(v: View, ev: MotionEvent, slopDp: Int = 0): Boolean {
            if (v.visibility != VISIBLE) return false
            val s = Ui.dp(context, slopDp)
            return ev.x >= v.left - s && ev.x <= v.right + s && ev.y >= v.top - s && ev.y <= v.bottom + s
        }
    }
}
