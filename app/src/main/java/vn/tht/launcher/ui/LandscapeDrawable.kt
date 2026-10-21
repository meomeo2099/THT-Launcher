package vn.tht.launcher.ui

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PixelFormat
import android.graphics.RadialGradient
import android.graphics.Shader
import android.graphics.drawable.Drawable
import kotlin.math.sin
import kotlin.random.Random

/**
 * Hình nền phong cảnh vẽ bằng code: bầu trời chuyển màu, mặt trời/trăng, các lớp núi chồng nhau
 * và hàng cây ở tiền cảnh. Không dùng ảnh ngoài, co giãn theo mọi kích thước màn hình.
 */
class LandscapeDrawable(
    private val skyTop: Int,
    private val skyBottom: Int,
    /** Màu các lớp núi từ xa tới gần. */
    private val layers: IntArray,
    private val sun: Int,
    private val seed: Int = 7,
) : Drawable() {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun draw(canvas: Canvas) {
        val w = bounds.width().toFloat()
        val h = bounds.height().toFloat()
        if (w <= 0 || h <= 0) return
        // Trời
        paint.shader = LinearGradient(0f, 0f, 0f, h, skyTop, skyBottom, Shader.TileMode.CLAMP)
        canvas.drawRect(0f, 0f, w, h, paint)
        // Mặt trời / trăng có quầng sáng
        val sx = w * 0.62f
        val sy = h * 0.26f
        val sr = h * 0.09f
        paint.shader = RadialGradient(sx, sy, sr * 3.5f, intArrayOf(Palette.withAlpha(sun, 90), Palette.withAlpha(sun, 0)), null, Shader.TileMode.CLAMP)
        canvas.drawCircle(sx, sy, sr * 3.5f, paint)
        paint.shader = null
        paint.color = sun
        canvas.drawCircle(sx, sy, sr, paint)

        // Các lớp núi: tổng các sóng sin ngẫu nhiên (cố định theo seed)
        val rnd = Random(seed)
        layers.forEachIndexed { i, color ->
            val baseY = h * (0.45f + i * 0.11f)
            val amp = h * (0.16f - i * 0.022f)
            val f1 = 1.5f + rnd.nextFloat() * 2f
            val f2 = 4f + rnd.nextFloat() * 4f
            val f3 = 9f + rnd.nextFloat() * 8f
            val p1 = rnd.nextFloat() * 6f
            val p2 = rnd.nextFloat() * 6f
            val path = Path().apply {
                moveTo(0f, h)
                var x = 0f
                while (x <= w) {
                    val t = x / w * Math.PI.toFloat() * 2f
                    val y = baseY - amp * (0.55f * sin(f1 * t + p1) + 0.3f * sin(f2 * t + p2) + 0.15f * sin(f3 * t))
                    lineTo(x, y)
                    x += w / 160f
                }
                lineTo(w, h)
                close()
            }
            paint.color = color
            canvas.drawPath(path, paint)
        }

        // Hàng cây thông ở tiền cảnh
        paint.color = layers.last()
        val treeBase = h * 0.93f
        var x = -10f
        while (x < w) {
            val th = h * (0.06f + rnd.nextFloat() * 0.08f)
            val tw = th * 0.45f
            val path = Path().apply {
                moveTo(x, treeBase)
                lineTo(x + tw / 2, treeBase - th)
                lineTo(x + tw, treeBase)
                close()
            }
            canvas.drawPath(path, paint)
            x += tw * (0.6f + rnd.nextFloat() * 1.8f)
            if (rnd.nextFloat() < 0.15f) x += w * 0.08f
        }
        canvas.drawRect(0f, treeBase - 1f, w, h, paint)
    }

    override fun setAlpha(alpha: Int) {}
    override fun setColorFilter(colorFilter: ColorFilter?) {}
    @Deprecated("Deprecated in Java")
    override fun getOpacity() = PixelFormat.OPAQUE

    companion object {
        private fun c(v: Long) = v.toInt()

        /** Hoàng hôn tím hồng (giống phong cách nền núi). */
        fun dusk() = LandscapeDrawable(
            c(0xFF2B2350), c(0xFFE7A1B0),
            intArrayOf(c(0xFF9C7BB0), c(0xFF6F5A93), c(0xFF4A3D72), c(0xFF2B2550), c(0xFF1A1736)),
            c(0xFFFFE3C2), seed = 11,
        )

        fun night() = LandscapeDrawable(
            c(0xFF05070F), c(0xFF1B2A4A),
            intArrayOf(c(0xFF27385C), c(0xFF1D2B48), c(0xFF152037), c(0xFF0E1628), c(0xFF080D19)),
            c(0xFFE8EEF8), seed = 3,
        )

        fun day() = LandscapeDrawable(
            c(0xFF6FB7F2), c(0xFFDDF0FF),
            intArrayOf(c(0xFFA9C6E0), c(0xFF7FA6C8), c(0xFF5B87AE), c(0xFF3E6A8E), c(0xFF27506F)),
            c(0xFFFFF6D6), seed = 5,
        )
    }
}
