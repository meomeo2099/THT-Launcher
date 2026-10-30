package vn.tht.launcher.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.util.LruCache

/** Center-cropped bundled artwork, cached independently from each drawable's bounds. */
class ReferenceWallpaperDrawable(context: Context, private val night: Boolean) : Drawable() {
    private val bitmap = load(context.applicationContext, night)
    private val image = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)
    private val fill = Paint()
    private val destination = RectF()
    private val layerBounds = RectF()
    private var opacity = 255

    override fun draw(canvas: Canvas) {
        if (bounds.isEmpty) return
        layerBounds.set(bounds)
        val checkpoint = if (opacity < 255) canvas.saveLayerAlpha(layerBounds, opacity) else canvas.save()
        canvas.clipRect(bounds)
        fill.color = if (night) 0xFF0A0F15.toInt() else 0xFFD9E4EF.toInt()
        canvas.drawRect(bounds, fill)
        bitmap?.let { photo ->
            val scale = maxOf(bounds.width() / photo.width.toFloat(), bounds.height() / photo.height.toFloat())
            val width = photo.width * scale
            val height = photo.height * scale
            val x = bounds.exactCenterX() - width / 2f
            val y = bounds.exactCenterY() - height / 2f
            destination.set(x, y, x + width, y + height)
            canvas.drawBitmap(photo, null, destination, image)
            if (night) {
                fill.color = 0x14050B12
                canvas.drawRect(bounds, fill)
            }
        }
        canvas.restoreToCount(checkpoint)
    }

    override fun setAlpha(alpha: Int) { opacity = alpha.coerceIn(0, 255); invalidateSelf() }
    override fun setColorFilter(colorFilter: ColorFilter?) { image.colorFilter = colorFilter; invalidateSelf() }
    @Deprecated("Deprecated in Java")
    override fun getOpacity() = if (opacity == 255) PixelFormat.OPAQUE else PixelFormat.TRANSLUCENT

    companion object {
        // Two 1672×941 ARGB images fit below this budget; older entries can be reclaimed.
        private val cache = object : LruCache<String, Bitmap>(16 * 1024 * 1024) {
            override fun sizeOf(key: String, value: Bitmap) = value.allocationByteCount
        }

        @Synchronized private fun load(context: Context, night: Boolean): Bitmap? {
            val path = if (night) "reference/waterfront.png" else "reference/waterfront-day.png"
            cache[path]?.let { return it }
            val decoded = runCatching {
                context.assets.open(path).use { stream ->
                    BitmapFactory.decodeStream(stream, null, BitmapFactory.Options().apply { inScaled = false })
                }
            }.getOrNull() ?: return null
            cache.put(path, decoded)
            return decoded
        }
    }
}
