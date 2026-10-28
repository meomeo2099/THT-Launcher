package vn.tht.launcher.ui

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PixelFormat
import android.graphics.RadialGradient
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.drawable.Drawable
import kotlin.random.Random

/**
 * Hình nền minh hoạ: SUV cỡ nhỏ dáng Geely Coolray 2025 nhìn ngang (đầu xe bên trái, mui dốc về sau,
 * trụ C sơn đen kiểu "mui nổi", mâm 5 chấu) đỗ trên đường, phía sau là thành phố. Vẽ bằng mã nên nét ở
 * mọi kích thước, không dùng ảnh hay logo của hãng. [night] = bản đêm (đèn xe và cửa sổ phố sáng).
 */
class CarSceneDrawable(private val night: Boolean) : Drawable() {
    private val p = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()

    // Màu theo bản ngày/đêm
    private val skyTop = if (night) 0xFF070B16.toInt() else 0xFF7FB2E5.toInt()
    private val skyBottom = if (night) 0xFF1B2440.toInt() else 0xFFE3EEF7.toInt()
    private val city = if (night) 0xFF101728.toInt() else 0xFFB5C6D8.toInt()
    private val cityFar = if (night) 0xFF161F35.toInt() else 0xFFCBD8E5.toInt()
    private val road = if (night) 0xFF0B0F18.toInt() else 0xFF5E6874.toInt()
    private val roadTop = if (night) 0xFF1A2130.toInt() else 0xFF8A949F.toInt()
    private val bodyTop = if (night) 0xFF3A4A63.toInt() else 0xFFF2F5F8.toInt()
    private val bodyBottom = if (night) 0xFF161D2A.toInt() else 0xFFA9B4C0.toInt()
    private val glass = if (night) 0xFF070A10.toInt() else 0xFF26303C.toInt()
    private val trim = 0xFF0E1217.toInt()

    override fun draw(canvas: Canvas) {
        val w = bounds.width().toFloat()
        val h = bounds.height().toFloat()
        if (w <= 0 || h <= 0) return
        val horizon = h * 0.64f

        // Trời
        p.alpha = 255; p.shader = LinearGradient(0f, 0f, 0f, horizon, skyTop, skyBottom, Shader.TileMode.CLAMP)
        canvas.drawRect(0f, 0f, w, horizon, p)
        p.shader = null
        if (night) stars(canvas, w, horizon) else sunGlow(canvas, w, h)

        // Thành phố: hai lớp nhà cao tầng (cố định theo seed), lớp gần có cửa sổ sáng về đêm
        skyline(canvas, w, horizon, h * 0.30f, cityFar, Random(11), windows = false)
        skyline(canvas, w, horizon, h * 0.20f, city, Random(23), windows = night)

        // Mặt đường + vạch kẻ
        p.alpha = 255; p.shader = LinearGradient(0f, horizon, 0f, h, roadTop, road, Shader.TileMode.CLAMP)
        canvas.drawRect(0f, horizon, w, h, p)
        p.shader = null
        p.color = if (night) 0x55E8EDF5 else 0x99FFFFFF.toInt()
        var x = -w * 0.02f
        val laneY = h * 0.80f
        while (x < w) { canvas.drawRect(x, laneY, x + w * 0.06f, laneY + h * 0.008f, p); x += w * 0.11f }

        // Xe: dài ~44% chiều ngang, đặt hơi lệch phải để không bị cụm widget lớn bên trái che hết
        val len = w * 0.42f
        val left = w * 0.50f
        val ground = h * 0.78f // trên thanh dock (đáy ~14% màn)
        drawCar(canvas, left, ground, len)
    }

    private fun stars(c: Canvas, w: Float, hz: Float) {
        val r = Random(5)
        p.color = 0xCCFFFFFF.toInt()
        repeat(90) {
            val s = 0.6f + r.nextFloat() * 1.4f
            p.alpha = 60 + r.nextInt(170)
            c.drawCircle(r.nextFloat() * w, r.nextFloat() * hz * 0.75f, s, p)
        }
        p.alpha = 255
        // Trăng
        p.alpha = 255; p.shader = RadialGradient(w * 0.18f, hz * 0.22f, hz * 0.22f, intArrayOf(0x33DDE6F5, 0x00DDE6F5), null, Shader.TileMode.CLAMP)
        c.drawCircle(w * 0.18f, hz * 0.22f, hz * 0.22f, p)
        p.shader = null
        p.color = 0xFFE6ECF5.toInt()
        c.drawCircle(w * 0.18f, hz * 0.22f, hz * 0.05f, p)
    }

    private fun sunGlow(c: Canvas, w: Float, h: Float) {
        p.alpha = 255; p.shader = RadialGradient(w * 0.20f, h * 0.18f, h * 0.45f, intArrayOf(0x66FFF6E0, 0x00FFF6E0), null, Shader.TileMode.CLAMP)
        c.drawCircle(w * 0.20f, h * 0.18f, h * 0.45f, p)
        p.shader = null
    }

    private fun skyline(c: Canvas, w: Float, base: Float, maxH: Float, color: Int, r: Random, windows: Boolean) {
        var x = -10f
        while (x < w) {
            val bw = w * (0.025f + r.nextFloat() * 0.045f)
            val bh = maxH * (0.25f + r.nextFloat() * 0.75f)
            p.color = color
            c.drawRect(x, base - bh, x + bw, base, p)
            if (windows) {
                val cell = bw / 5f
                var wy = base - bh + cell
                while (wy < base - cell) {
                    var wx = x + cell * 0.6f
                    while (wx < x + bw - cell * 0.6f) {
                        if (r.nextFloat() < 0.32f) {
                            p.color = if (r.nextBoolean()) 0xCCFFD58A.toInt() else 0x99BFD7FF.toInt()
                            c.drawRect(wx, wy, wx + cell * 0.45f, wy + cell * 0.55f, p)
                        }
                        wx += cell
                    }
                    wy += cell * 1.2f
                }
            }
            x += bw + w * 0.004f
        }
    }

    /** Toạ độ chuẩn hoá: x 0 (đầu) → 1 (đuôi) theo chiều dài; y 0 (nóc) → 1 (mặt đất) theo chiều cao xe. */
    private fun drawCar(c: Canvas, left: Float, ground: Float, len: Float) {
        val ht = len * 0.36f
        val top = ground - ht
        fun X(v: Float) = left + v * len
        fun Y(v: Float) = top + v * ht
        val wheelR = ht * 0.215f
        val frontW = 0.185f
        val rearW = 0.79f
        val wheelY = 1f - wheelR / ht

        // Bóng đổ
        p.alpha = 255; p.shader = RadialGradient(X(0.5f), ground, len * 0.55f, intArrayOf(0x88000000.toInt(), 0x00000000), null, Shader.TileMode.CLAMP)
        c.save(); c.scale(1f, 0.12f, X(0.5f), ground)
        c.drawCircle(X(0.5f), ground, len * 0.55f, p)
        c.restore()
        p.shader = null

        // Đèn chiếu xuống đường (bản đêm)
        if (night) {
            path.reset()
            path.moveTo(X(0.01f), Y(0.56f)); path.lineTo(X(-0.55f), ground + ht * 0.05f); path.lineTo(X(-0.05f), ground + ht * 0.10f); path.close()
            p.alpha = 255; p.shader = LinearGradient(X(0.01f), 0f, X(-0.55f), 0f, 0x55F3F7FF, 0x00F3F7FF, Shader.TileMode.CLAMP)
            c.drawPath(path, p); p.shader = null
        }

        // Thân xe
        path.reset()
        path.moveTo(X(0.020f), Y(0.88f))
        path.lineTo(X(0.004f), Y(0.70f))
        path.quadTo(X(0.000f), Y(0.56f), X(0.020f), Y(0.50f))       // mũi xe
        path.quadTo(X(0.12f), Y(0.43f), X(0.300f), Y(0.405f))       // nắp capo ngắn
        path.lineTo(X(0.425f), Y(0.085f))                           // kính chắn gió dốc
        path.quadTo(X(0.47f), Y(0.045f), X(0.560f), Y(0.045f))
        path.quadTo(X(0.78f), Y(0.050f), X(0.900f), Y(0.135f))      // mui dốc về sau
        path.lineTo(X(0.930f), Y(0.165f))                           // cánh gió
        path.quadTo(X(0.965f), Y(0.30f), X(0.985f), Y(0.44f))       // kính hậu
        path.quadTo(X(1.000f), Y(0.55f), X(0.995f), Y(0.70f))
        path.lineTo(X(0.975f), Y(0.88f))
        // Gầm + hốc bánh (cung tròn)
        path.lineTo(X(rearW + wheelR / len * 1.30f), Y(0.88f))
        path.arcTo(RectF(X(rearW) - wheelR * 1.22f, Y(wheelY) - wheelR * 1.22f, X(rearW) + wheelR * 1.22f, Y(wheelY) + wheelR * 1.22f), -8f, -164f)
        path.lineTo(X(frontW + wheelR / len * 1.30f), Y(0.88f))
        path.arcTo(RectF(X(frontW) - wheelR * 1.22f, Y(wheelY) - wheelR * 1.22f, X(frontW) + wheelR * 1.22f, Y(wheelY) + wheelR * 1.22f), -8f, -164f)
        path.close()
        p.alpha = 255; p.shader = LinearGradient(0f, Y(0.05f), 0f, Y(0.9f), bodyTop, bodyBottom, Shader.TileMode.CLAMP)
        c.drawPath(path, p)
        p.shader = null

        // Ốp nhựa đen quanh hốc bánh và sườn dưới (đặc trưng crossover)
        p.color = trim
        c.drawRect(X(0.018f), Y(0.80f), X(0.978f), Y(0.88f), p)
        for (cx in floatArrayOf(frontW, rearW)) {
            p.style = Paint.Style.STROKE; p.strokeWidth = wheelR * 0.16f
            c.drawArc(RectF(X(cx) - wheelR * 1.30f, Y(wheelY) - wheelR * 1.30f, X(cx) + wheelR * 1.30f, Y(wheelY) + wheelR * 1.30f), 188f, 164f, false, p)
            p.style = Paint.Style.FILL
        }

        // Kính (cụm cửa sổ) + trụ C đen tạo "mui nổi"
        path.reset()
        path.moveTo(X(0.318f), Y(0.385f))
        path.lineTo(X(0.432f), Y(0.115f))
        path.quadTo(X(0.47f), Y(0.085f), X(0.560f), Y(0.085f))
        path.quadTo(X(0.75f), Y(0.090f), X(0.865f), Y(0.165f))
        path.quadTo(X(0.905f), Y(0.26f), X(0.900f), Y(0.385f))
        path.close()
        p.color = glass
        c.drawPath(path, p)
        p.color = if (night) 0x22FFFFFF else 0x33FFFFFF
        path.reset(); path.moveTo(X(0.44f), Y(0.12f)); path.lineTo(X(0.52f), Y(0.10f)); path.lineTo(X(0.40f), Y(0.36f)); path.lineTo(X(0.35f), Y(0.37f)); path.close()
        c.drawPath(path, p) // vệt phản chiếu
        p.color = trim
        c.drawRect(X(0.585f), Y(0.09f), X(0.605f), Y(0.385f), p)       // trụ B
        path.reset()                                                    // trụ C sơn đen
        path.moveTo(X(0.770f), Y(0.105f)); path.quadTo(X(0.84f), Y(0.13f), X(0.875f), Y(0.175f))
        path.quadTo(X(0.905f), Y(0.27f), X(0.900f), Y(0.385f)); path.lineTo(X(0.800f), Y(0.385f)); path.close()
        c.drawPath(path, p)
        // Thanh giá nóc
        p.color = if (night) 0xFF5A6578.toInt() else 0xFF2E353D.toInt()
        c.drawRoundRect(RectF(X(0.50f), Y(0.025f), X(0.86f), Y(0.050f)), ht * 0.01f, ht * 0.01f, p)

        // Đường gân vai + khe cửa + tay nắm + gương
        p.style = Paint.Style.STROKE; p.strokeWidth = ht * 0.010f
        p.color = if (night) 0x33FFFFFF else 0x55FFFFFF
        c.drawLine(X(0.06f), Y(0.50f), X(0.97f), Y(0.47f), p)
        p.color = if (night) 0x55000000 else 0x33000000
        c.drawLine(X(0.590f), Y(0.40f), X(0.585f), Y(0.78f), p)
        c.drawLine(X(0.318f), Y(0.40f), X(0.335f), Y(0.78f), p)
        c.drawLine(X(0.800f), Y(0.40f), X(0.790f), Y(0.70f), p)
        p.style = Paint.Style.FILL
        p.color = if (night) 0xFF8792A6.toInt() else 0xFF7C8794.toInt()
        c.drawRoundRect(RectF(X(0.48f), Y(0.475f), X(0.53f), Y(0.495f)), 4f, 4f, p)
        c.drawRoundRect(RectF(X(0.68f), Y(0.465f), X(0.73f), Y(0.485f)), 4f, 4f, p)
        p.color = trim
        path.reset(); path.moveTo(X(0.322f), Y(0.36f)); path.lineTo(X(0.290f), Y(0.33f)); path.lineTo(X(0.282f), Y(0.40f)); path.lineTo(X(0.325f), Y(0.405f)); path.close()
        c.drawPath(path, p)

        // Đèn pha dải mảnh + đèn hậu xuyên ngang
        val headOn = if (night) 0xFFF4F8FF.toInt() else 0xFFE8EEF5.toInt()
        if (night) {
            p.alpha = 255; p.shader = RadialGradient(X(0.03f), Y(0.53f), ht * 0.45f, intArrayOf(0x88E6EEFF.toInt(), 0x00E6EEFF), null, Shader.TileMode.CLAMP)
            c.drawCircle(X(0.03f), Y(0.53f), ht * 0.45f, p); p.shader = null
            p.alpha = 255; p.shader = RadialGradient(X(0.985f), Y(0.47f), ht * 0.35f, intArrayOf(0x99FF2A2A.toInt(), 0x00FF2A2A), null, Shader.TileMode.CLAMP)
            c.drawCircle(X(0.985f), Y(0.47f), ht * 0.35f, p); p.shader = null
        }
        p.color = headOn
        c.drawRoundRect(RectF(X(0.008f), Y(0.515f), X(0.095f), Y(0.545f)), ht * 0.015f, ht * 0.015f, p)
        p.color = trim
        c.drawRoundRect(RectF(X(0.022f), Y(0.655f), X(0.105f), Y(0.735f)), ht * 0.02f, ht * 0.02f, p) // hốc gió cản trước
        p.color = if (night) 0xFFFF3B3B.toInt() else 0xFFC62828.toInt()
        c.drawRoundRect(RectF(X(0.905f), Y(0.455f), X(0.998f), Y(0.485f)), ht * 0.015f, ht * 0.015f, p)

        // Bánh xe: lốp + mâm 5 chấu
        for (cx in floatArrayOf(frontW, rearW)) wheel(c, X(cx), Y(wheelY), wheelR)
    }

    private fun wheel(c: Canvas, cx: Float, cy: Float, r: Float) {
        p.color = 0xFF0A0C10.toInt()
        c.drawCircle(cx, cy, r, p)
        val rim = r * 0.68f
        p.alpha = 255; p.shader = RadialGradient(cx - rim * 0.3f, cy - rim * 0.3f, rim * 1.4f,
            if (night) intArrayOf(0xFF8A94A6.toInt(), 0xFF2A313C.toInt()) else intArrayOf(0xFFE3E8EE.toInt(), 0xFF6B7682.toInt()),
            null, Shader.TileMode.CLAMP)
        c.drawCircle(cx, cy, rim, p)
        p.shader = null
        p.color = 0xFF14181E.toInt()
        for (i in 0 until 5) {
            c.save(); c.rotate(i * 72f + 18f, cx, cy)
            path.reset()
            path.moveTo(cx - rim * 0.10f, cy - rim * 0.18f); path.lineTo(cx - rim * 0.22f, cy - rim * 0.92f)
            path.lineTo(cx + rim * 0.22f, cy - rim * 0.92f); path.lineTo(cx + rim * 0.10f, cy - rim * 0.18f); path.close()
            c.drawPath(path, p)
            c.restore()
        }
        p.color = if (night) 0xFF9AA4B5.toInt() else 0xFFF3F5F8.toInt()
        c.drawCircle(cx, cy, rim * 0.16f, p)
    }

    override fun setAlpha(alpha: Int) {}
    override fun setColorFilter(colorFilter: ColorFilter?) {}
    @Deprecated("Deprecated in Java")
    override fun getOpacity() = PixelFormat.OPAQUE
}
