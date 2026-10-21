package vn.tht.launcher.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * Ảnh người dùng chọn được thu về đúng cỡ màn (Coolray: 1920×720) trước khi lưu, để giải mã nhanh
 * và tốn ít RAM trên đầu xe (ảnh 12 MP để nguyên sẽ chiếm hàng chục MB).
 */
object Bitmaps {
    /** Kích thước màn thật theo chiều ngang (dài × ngắn), tính cả thanh hệ thống. */
    fun screen(c: Context): Pair<Int, Int> {
        val m = DisplayMetrics()
        c.getSystemService(WindowManager::class.java).defaultDisplay.getRealMetrics(m)
        return maxOf(m.widthPixels, m.heightPixels) to minOf(m.widthPixels, m.heightPixels)
    }

    /** Giải mã có lấy mẫu: ảnh vẫn không nhỏ hơn [minW]×[minH] (hoặc ảnh gốc nếu nhỏ hơn). */
    fun decode(c: Context, uri: Uri, minW: Int, minH: Int): Bitmap? {
        val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
        c.contentResolver.openInputStream(uri)?.use { BitmapFactory.decodeStream(it, null, bounds) }
        if (bounds.outWidth <= 0 || bounds.outHeight <= 0) return null
        var sample = 1
        while (bounds.outWidth / (sample * 2) >= minW && bounds.outHeight / (sample * 2) >= minH) sample *= 2
        return c.contentResolver.openInputStream(uri)?.use {
            BitmapFactory.decodeStream(it, null, BitmapFactory.Options().apply { inSampleSize = sample })
        }
    }

    /** Phóng/thu và cắt giữa cho phủ kín [w]×[h] (ảnh nền). */
    fun cover(src: Bitmap, w: Int, h: Int): Bitmap {
        if (src.width == w && src.height == h) return src
        val s = maxOf(w.toFloat() / src.width, h.toFloat() / src.height)
        val cw = (w / s).toInt().coerceIn(1, src.width)
        val ch = (h / s).toInt().coerceIn(1, src.height)
        val crop = Bitmap.createBitmap(src, (src.width - cw) / 2, (src.height - ch) / 2, cw, ch)
        return Bitmap.createScaledBitmap(crop, w, h, true)
    }

    /** Thu nhỏ (giữ tỉ lệ) để nằm gọn trong [w]×[h]; ảnh đã nhỏ hơn thì giữ nguyên. */
    fun fit(src: Bitmap, w: Int, h: Int): Bitmap {
        val s = minOf(w.toFloat() / src.width, h.toFloat() / src.height)
        if (s >= 1f) return src
        return Bitmap.createScaledBitmap(src, (src.width * s).toInt().coerceAtLeast(1), (src.height * s).toInt().coerceAtLeast(1), true)
    }
}
