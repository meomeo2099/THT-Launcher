package vn.tht.launcher.home

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import vn.tht.launcher.ui.Icons
import vn.tht.launcher.ui.Palette
import vn.tht.launcher.ui.Ui
import java.io.File

/**
 * Widget ảnh có vùng chạm: ảnh do người dùng chọn (ví dụ ảnh xe PNG nền trong suốt), chia lưới
 * [cols]×[rows]; mỗi vùng gán một chức năng ([ImageActions]). Nhấn giữ để cấu hình.
 *
 * Cấu hình lưu trong [WidgetSpec.pkg]: "cols:rows:fn1,fn2,…" ; ảnh ở filesDir/img_<id>.png.
 */
class ImageWidget(host: WidgetHost, private val spec: WidgetSpec) : HomeWidget(host) {
    private var cols = 1
    private var rows = 1
    private var fns = listOf<String>()
    private val image = ImageView(c).apply { scaleType = ImageView.ScaleType.FIT_CENTER }
    private val hint = Ui.text(c, "Nhấn giữ để chọn ảnh\nvà gán chức năng cho từng vùng", 16f, dim = true).apply { gravity = Gravity.CENTER }
    private val zones = ZoneOverlay()

    override val view: View = FrameLayout(c).apply {
        addView(image, FrameLayout.LayoutParams(-1, -1))
        addView(hint, FrameLayout.LayoutParams(-2, -2, Gravity.CENTER))
        addView(zones, FrameLayout.LayoutParams(-1, -1))
    }

    init { load() }

    private fun load() {
        val parts = spec.pkg.split(':')
        cols = parts.getOrNull(0)?.toIntOrNull()?.coerceIn(1, 6) ?: 1
        rows = parts.getOrNull(1)?.toIntOrNull()?.coerceIn(1, 6) ?: 1
        fns = parts.getOrNull(2)?.split(',') ?: emptyList()
        val f = file(host, spec.id)
        val bmp = if (f.exists()) BitmapFactory.decodeFile(f.path) else null
        image.setImageBitmap(bmp)
        hint.visibility = if (bmp == null) View.VISIBLE else View.GONE
        view.background = if (bmp == null) Ui.card(c) else null
        zones.invalidate()
    }

    /** Hộp cấu hình: chọn ảnh, lưới, chức năng từng vùng. */
    private fun configure() {
        vn.tht.launcher.ui.IconList.show(c, "Widget ảnh", listOf(
            vn.tht.launcher.ui.IconList.Item("Chọn ảnh", "apps", summary = "PNG nền trong suốt hợp nhất, tối đa 25 MB"),
            vn.tht.launcher.ui.IconList.Item("Chia vùng", "dual", summary = "Hiện: $cols × $rows"),
            vn.tht.launcher.ui.IconList.Item("Gán chức năng cho từng vùng", "shortcut"),
            vn.tht.launcher.ui.IconList.Item("Xoá ảnh", "close", danger = true),
        )) { i ->
            when (i) {
                0 -> host.pickImage(file(host, spec.id)) { ok -> if (ok) load() else host.toast("Không đọc được ảnh") }
                1 -> {
                    val grids = listOf(1 to 1, 2 to 1, 1 to 2, 2 to 2, 3 to 1, 3 to 2, 4 to 2)
                    vn.tht.launcher.ui.IconList.show(c, "Chia vùng (cột × hàng)",
                        grids.map { (cc, rr) -> vn.tht.launcher.ui.IconList.Item("$cc × $rr", "dual") }, columns = 2) { g ->
                        cols = grids[g].first; rows = grids[g].second
                        fns = fns.take(cols * rows)
                        save()
                    }
                }
                2 -> assign(0)
                3 -> { file(host, spec.id).delete(); load() }
            }
        }
    }

    private fun assign(zone: Int) {
        if (zone >= cols * rows) { save(); host.toast("Đã lưu chức năng các vùng"); return }
        val acts = ImageActions.ALL
        vn.tht.launcher.ui.IconList.show(c, "Vùng ${zone + 1}/${cols * rows}: chọn chức năng",
            acts.map { vn.tht.launcher.ui.IconList.Item(it.label, it.icon) }, columns = 2) { a ->
            val list = fns.toMutableList()
            while (list.size <= zone) list += "none"
            list[zone] = acts[a].id
            fns = list
            assign(zone + 1)
        }
    }

    private fun save() {
        spec.pkg = "$cols:$rows:${fns.joinToString(",")}"
        host.saveLayout()
        zones.invalidate()
    }

    /** Lớp trong suốt nhận chạm theo vùng; vẽ lưới mờ khi có nhiều vùng. */
    @SuppressLint("ViewConstructor", "ClickableViewAccessibility")
    private inner class ZoneOverlay : View(c) {
        private val grid = Paint().apply {
            color = Color.argb(70, 255, 255, 255); strokeWidth = 2f
            pathEffect = DashPathEffect(floatArrayOf(10f, 10f), 0f)
        }
        private var downAt = 0L

        override fun onDraw(canvas: Canvas) {
            if (cols * rows <= 1) return
            for (i in 1 until cols) canvas.drawLine(width * i / cols.toFloat(), 0f, width * i / cols.toFloat(), height.toFloat(), grid)
            for (j in 1 until rows) canvas.drawLine(0f, height * j / rows.toFloat(), width.toFloat(), height * j / rows.toFloat(), grid)
            // Icon nhỏ của chức năng ở góc mỗi vùng
            val s = Ui.dp(context, 22)
            for (z in 0 until cols * rows) {
                val fn = ImageActions.ALL.firstOrNull { it.id == fns.getOrNull(z) } ?: continue
                if (fn.id == "none") continue
                val x = (z % cols) * width / cols + Ui.dp(context, 6)
                val y = (z / cols) * height / rows + Ui.dp(context, 6)
                Icons.get(context, fn.icon, Palette.withAlpha(Color.WHITE, 180))?.apply { setBounds(x, y, x + s, y + s); draw(canvas) }
            }
        }

        override fun onTouchEvent(e: MotionEvent): Boolean {
            when (e.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    downAt = System.currentTimeMillis()
                    postDelayed(longPress, 600)
                }
                MotionEvent.ACTION_UP -> {
                    removeCallbacks(longPress)
                    if (System.currentTimeMillis() - downAt < 600) {
                        val z = (e.y / height * rows).toInt().coerceIn(0, rows - 1) * cols +
                            (e.x / width * cols).toInt().coerceIn(0, cols - 1)
                        val fn = fns.getOrNull(z)
                        if (fn == null || fn == "none") { if (fns.isEmpty()) configure() } else host.runImageAction(fn)
                    }
                }
                MotionEvent.ACTION_CANCEL -> removeCallbacks(longPress)
            }
            return true
        }

        private val longPress = Runnable { configure() }
    }

    companion object {
        fun file(host: WidgetHost, id: String) = File(host.context.filesDir, "img_$id.png")
    }
}

/** Các chức năng gán được cho vùng ảnh. */
object ImageActions {
    data class Action(val id: String, val label: String, val icon: String)

    val ALL = listOf(
        Action("none", "Không làm gì", "close"),
        Action("climate", "Mở cửa sổ điều hoà", "climate"),
        Action("power", "Bật/tắt điều hoà", "power"),
        Action("temp_up", "Tăng nhiệt độ", "plus"),
        Action("temp_down", "Giảm nhiệt độ", "minus"),
        Action("fan_up", "Tăng quạt", "fan"),
        Action("fan_down", "Giảm quạt", "fan"),
        Action("roof_open", "Mở cửa sổ trời", "sunroof-open"),
        Action("roof_close", "Đóng cửa sổ trời", "sunroof-open"),
        Action("curtain_open", "Mở rèm che nắng", "sun-curtain"),
        Action("curtain_close", "Đóng rèm che nắng", "sun-curtain"),
        Action("trunk", "Mở cốp (số P)", "trunk"),
        Action("prev", "Bài trước", "previous"),
        Action("next", "Bài tiếp", "next"),
        Action("play", "Phát / tạm dừng", "play"),
        Action("vol_up", "Tăng âm lượng", "speaker"),
        Action("vol_down", "Giảm âm lượng", "speaker"),
        Action("mute", "Tắt tiếng", "muted"),
        Action("controls", "Trung tâm điều khiển", "shortcut"),
    )
}
