package vn.tht.launcher.ui

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.net.Uri
import java.io.File

/**
 * Font chữ riêng cho toàn launcher: người dùng nạp file .ttf/.otf (cần có dấu tiếng Việt),
 * lưu trong filesDir. [Ui.text]/[Ui.button] dùng font này; không có thì là font hệ thống.
 */
object Fonts {
    private var regular: Typeface? = null
    private var bold: Typeface? = null
    private var stamp = -1L

    private fun file(c: Context) = File(c.filesDir, "font_custom")

    /** Tên font đang dùng (tên file gốc) hoặc null = mặc định. */
    fun name(c: Context): String? = c.getSharedPreferences("fonts", Context.MODE_PRIVATE).getString("name", null)
        ?.takeIf { file(c).exists() }

    /** Font thường đang dùng (null = mặc định hệ thống). Tự nạp lại khi file đổi. */
    fun regular(c: Context): Typeface? { refresh(c); return regular }

    fun bold(c: Context): Typeface? { refresh(c); return bold }

    private fun refresh(c: Context) {
        val f = file(c)
        val s = if (f.exists()) f.lastModified() else 0L
        if (s == stamp) return
        stamp = s
        regular = if (s == 0L) null else runCatching { Typeface.createFromFile(f) }.getOrNull()
        bold = regular?.let { Typeface.create(it, Typeface.BOLD) }
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
            tmp.copyTo(file(c), overwrite = true)
            c.getSharedPreferences("fonts", Context.MODE_PRIVATE).edit()
                .putString("name", displayName?.substringBeforeLast('.') ?: "Font riêng").apply()
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
    }

    private const val MAX_BYTES = 10L * 1024 * 1024
    /** Ký tự kiểm tra font có tiếng Việt. */
    private const val VIETNAMESE = "ăâđêôơưạảấầẩẫậắằẳẵặẹẻẽếềểễệỉịọỏốồổỗộớờởỡợụủứừửữựỳỵỷỹĐ"
}
