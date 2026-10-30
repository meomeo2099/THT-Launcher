package vn.tht.launcher.ui

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.net.Uri
import android.util.AtomicFile
import java.io.File

/**
 * Font chữ riêng cho toàn launcher: người dùng nạp file .ttf/.otf (cần có dấu tiếng Việt),
 * lưu trong filesDir. Mặc định dùng Manrope đầy đủ dấu tiếng Việt; đồng hồ/số lớn dùng Elastre.
 * Font riêng của người dùng được ưu tiên cho cả hai kiểu.
 */
object Fonts {
    private data class Faces(val regular: Typeface, val bold: Typeface, val display: Typeface)
    private var bundled: Faces? = null
    private var current: Faces? = null
    private var stamp: String? = null

    private fun file(c: Context) = File(c.filesDir, "font_custom")

    /** Tên font đang dùng (tên file gốc) hoặc null = mặc định. */
    fun name(c: Context): String? = c.getSharedPreferences("fonts", Context.MODE_PRIVATE).getString("name", null)
        ?.takeIf { file(c).exists() }

    /** Font thường; tự nạp lại khi file riêng thay đổi. */
    fun regular(c: Context): Typeface? { refresh(c); return current?.regular }

    fun bold(c: Context): Typeface? { refresh(c); return current?.bold }

    /** Dành cho đồng hồ và số liệu, không dùng cho nhãn có dấu tiếng Việt. */
    fun display(c: Context): Typeface { refresh(c); return current?.display ?: Typeface.SANS_SERIF }

    @Synchronized private fun refresh(c: Context) {
        val f = file(c)
        val s = "${f.absolutePath}:${if (f.exists()) f.lastModified() else 0L}:${f.length()}"
        if (s == stamp) return
        stamp = s
        val defaults = bundled ?: loadBundled(c).also { bundled = it }
        val custom = if (f.isFile) runCatching { Typeface.Builder(f).build() }.getOrNull() else null
        current = custom?.let { Faces(it, Typeface.create(it, Typeface.BOLD), it) } ?: defaults
    }

    private fun loadBundled(c: Context): Faces {
        val normal = runCatching {
            Typeface.Builder(c.assets, "reference/Manrope.ttf").setFontVariationSettings("'wght' 450").build()
        }.getOrElse { Typeface.create("sans-serif", Typeface.NORMAL) }
        val strong = runCatching {
            Typeface.Builder(c.assets, "reference/Manrope.ttf").setFontVariationSettings("'wght' 600").build()
        }.getOrElse { Typeface.create(normal, Typeface.BOLD) }
        val display = runCatching {
            Typeface.Builder(c.assets, "reference/Elastre.ttf").build()
        }.getOrElse { strong }
        return Faces(normal, strong, display)
    }

    /** Nạp font từ [uri]; trả lỗi (tiếng Việt) hoặc null nếu xong. */
    fun install(c: Context, uri: Uri, displayName: String?): String? {
        val tmp = File(c.cacheDir, "font_tmp")
        try {
            c.contentResolver.openInputStream(uri)?.use { input ->
                tmp.outputStream().use { out ->
                    val buf = ByteArray(64 * 1024)
                    var total = 0L
                    while (true) {
                        val n = input.read(buf)
                        if (n < 0) break
                        total += n
                        if (total > MAX_BYTES) return "File font quá lớn (tối đa 10 MB)"
                        out.write(buf, 0, n)
                    }
                }
            } ?: return "Không đọc được file"
            val tf = runCatching { Typeface.createFromFile(tmp) }.getOrNull() ?: return "File này không phải font .ttf/.otf"
            val paint = Paint().apply { typeface = tf }
            val missing = VIETNAMESE.filterNot { paint.hasGlyph(it.toString()) }
            if (missing.isNotEmpty()) return "Font thiếu chữ tiếng Việt ($missing) – chọn font khác"
            val destination = AtomicFile(file(c))
            val output = destination.startWrite()
            try {
                tmp.inputStream().use { it.copyTo(output) }
                destination.finishWrite(output)
            } catch (t: Throwable) {
                destination.failWrite(output)
                throw t
            }
            c.getSharedPreferences("fonts", Context.MODE_PRIVATE).edit()
                .putString("name", displayName?.substringBeforeLast('.') ?: "Font riêng").apply()
            synchronized(this) { stamp = null }
            return null
        } catch (t: Throwable) {
            return "Không nạp được font: ${t.message}"
        } finally {
            tmp.delete()
        }
    }

    fun remove(c: Context) {
        file(c).delete()
        c.getSharedPreferences("fonts", Context.MODE_PRIVATE).edit().remove("name").apply()
        synchronized(this) { stamp = null }
    }

    private const val MAX_BYTES = 10L * 1024 * 1024
    /** Ký tự kiểm tra font có tiếng Việt. */
    private const val VIETNAMESE = "ăâđêôơưạảấầẩẫậắằẳẵặẹẻẽếềểễệỉịọỏốồổỗộớờởỡợụủứừửữựỳỵỷỹĐ"
}
