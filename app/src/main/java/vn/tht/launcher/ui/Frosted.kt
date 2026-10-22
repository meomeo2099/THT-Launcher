package vn.tht.launcher.ui

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PixelFormat
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.view.View

/**
 * Thẻ "kính mờ": vẽ đúng phần ảnh nền nằm sau thẻ, đã làm mờ, phủ một lớp màu thẻ mỏng.
 * Android 9 không có RenderEffect nên ảnh nền được chụp và làm mờ MỘT lần (thu nhỏ 1/[SCALE] +
 * box blur) mỗi khi đổi nền/ngày-đêm; từng thẻ chỉ cắt vùng của mình nên không tốn CPU khi đứng yên.
 */
object Frosted {
    private const val SCALE = 10
    @Volatile private var blurred: Bitmap? = null
    private var rootW = 1
    private var rootH = 1
    private val rootPos = IntArray(2)

    /** Chụp [wallpaper] (đang hiện, đã layout) và làm mờ. Gọi lại khi đổi ảnh nền hoặc ngày/đêm. */
    fun capture(wallpaper: View) {
        if (wallpaper.width <= 0 || wallpaper.height <= 0) return
        rootW = wallpaper.width
        rootH = wallpaper.height
        wallpaper.getLocationInWindow(rootPos)
        val small = Bitmap.createBitmap((rootW / SCALE).coerceAtLeast(1), (rootH / SCALE).coerceAtLeast(1), Bitmap.Config.ARGB_8888)
        val cv = Canvas(small)
        cv.scale(1f / SCALE, 1f / SCALE)
        wallpaper.draw(cv)
        boxBlur(small, 3)
        boxBlur(small, 3)
        blurred = small
    }

    fun clear() { blurred = null }

    val ready: Boolean get() = blurred != null

    /** Box blur tách ngang/dọc trên ảnh nhỏ (đủ nhanh: ~190×72 điểm ảnh). */
    private fun boxBlur(b: Bitmap, r: Int) {
        val w = b.width; val h = b.height
        val src = IntArray(w * h); val tmp = IntArray(w * h)
        b.getPixels(src, 0, w, 0, 0, w, h)
        fun pass(from: IntArray, to: IntArray, horizontal: Boolean) {
            val len = if (horizontal) w else h
            val lines = if (horizontal) h else w
            for (line in 0 until lines) {
                for (i in 0 until len) {
                    var a = 0; var rr = 0; var g = 0; var bb = 0; var n = 0
                    for (k in -r..r) {
                        val j = (i + k).coerceIn(0, len - 1)
                        val p = from[if (horizontal) line * w + j else j * w + line]
                        a += p ushr 24; rr += (p shr 16) and 0xFF; g += (p shr 8) and 0xFF; bb += p and 0xFF; n++
                    }
                    to[if (horizontal) line * w + i else i * w + line] = ((a / n) shl 24) or ((rr / n) shl 16) or ((g / n) shl 8) or (bb / n)
                }
            }
        }
        pass(src, tmp, true)
        pass(tmp, src, false)
        b.setPixels(src, 0, w, 0, 0, w, h)
    }

    /** Nền thẻ kính mờ cho [host]; tự rơi về thẻ đặc nếu chưa chụp được ảnh nền. */
    class CardDrawable(private val host: View, private val radiusPx: Float, private val fallback: Drawable) : Drawable() {
        private val pos = IntArray(2)
        private val src = Rect()
        private val dst = RectF()
        private val clip = Path()
        private val image = Paint(Paint.FILTER_BITMAP_FLAG)
        private val tint = Paint(Paint.ANTI_ALIAS_FLAG)
        private val stroke = Paint(Paint.ANTI_ALIAS_FLAG).apply { style = Paint.Style.STROKE }

        override fun draw(canvas: Canvas) {
            val bmp = blurred
            if (bmp == null) { fallback.bounds = bounds; fallback.draw(canvas); return }
            // Tỉ lệ thật trên màn (thẻ có thể nằm trong khung đang phóng/thu theo "Tỉ lệ giao diện")
            var s = 1f
            var v: View? = host
            while (v != null) { s *= v.scaleX; v = v.parent as? View }
            host.getLocationInWindow(pos)
            val k = bmp.width / rootW.toFloat()
            val x = (pos[0] - rootPos[0]) * k
            val y = (pos[1] - rootPos[1]) * k
            src.set(x.toInt(), y.toInt(), (x + bounds.width() * s * k).toInt(), (y + bounds.height() * s * k).toInt())
            dst.set(bounds)
            clip.reset()
            clip.addRoundRect(dst, radiusPx, radiusPx, Path.Direction.CW)
            canvas.save()
            canvas.clipPath(clip)
            canvas.drawBitmap(bmp, src, dst, image)
            // Lớp màu thẻ mỏng để chữ vẫn đọc rõ
            tint.color = Palette.withAlpha(Palette.card, 110)
            canvas.drawRect(dst, tint)
            canvas.restore()
            stroke.strokeWidth = host.resources.displayMetrics.density
            stroke.color = if (Palette.night) 0x33FFFFFF else 0x22000000
            canvas.drawRoundRect(dst, radiusPx, radiusPx, stroke)
        }

        override fun setAlpha(alpha: Int) {}
        override fun setColorFilter(colorFilter: ColorFilter?) {}
        @Deprecated("Deprecated in Java")
        override fun getOpacity() = PixelFormat.TRANSLUCENT
    }
}
