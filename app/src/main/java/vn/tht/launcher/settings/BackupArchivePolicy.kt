package vn.tht.launcher.settings

import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

/** Caps are enforced during streaming, even when an archive has no declared entry size. */
internal object BackupArchivePolicy {
    const val MAX_ENTRIES = 512
    const val MAX_ENTRY_BYTES = 16L * 1024 * 1024
    const val MAX_TOTAL_BYTES = 128L * 1024 * 1024
    const val MAX_ARCHIVE_BYTES = MAX_TOTAL_BYTES + 4L * 1024 * 1024
    const val MAX_SETTINGS_BYTES = 2L * 1024 * 1024
    const val MAX_FONT_BYTES = 10L * 1024 * 1024
    val configNames = setOf("home.json", "shortcuts.json", "topbar.json", "siderail.json")
    private val images = Regex("img_[A-Za-z0-9_-]{1,80}\\.png")
    private val icons = Regex("[A-Za-z0-9_-]{1,80}\\.png")

    fun accepts(path: String): Boolean {
        if (path.isEmpty() || path.length > 180 || path.contains('\\') || path.contains("..") ||
            path.startsWith('/') || path.any { it.code < 32 } || path.contains(':')) return false
        if (path == "settings.json") return true
        if (path.startsWith("config/")) return path.removePrefix("config/") in configNames
        if (path.startsWith("files/iconpack/")) {
            val name = path.removePrefix("files/iconpack/")
            return name == "manifest.json" || icons.matches(name)
        }
        if (!path.startsWith("files/")) return false
        val name = path.removePrefix("files/")
        return name in setOf("wallpaper_day.jpg", "wallpaper_night.jpg", "font_custom") || images.matches(name)
    }

    fun limit(path: String): Long = when {
        path == "settings.json" || path.startsWith("config/") -> MAX_SETTINGS_BYTES
        path.endsWith("/manifest.json") -> 64L * 1024
        path == "files/font_custom" -> MAX_FONT_BYTES
        path.startsWith("files/iconpack/") -> 2L * 1024 * 1024
        else -> MAX_ENTRY_BYTES
    }

    class Budget(private val maximum: Long = MAX_TOTAL_BYTES) {
        var consumed: Long = 0; private set
        fun add(bytes: Int) {
            if (bytes < 0 || bytes.toLong() > maximum - consumed) throw IOException("Bản sao lưu vượt giới hạn tổng dung lượng")
            consumed += bytes
        }
    }

    /** Also cap compressed input: a stream of empty deflate blocks must not bypass work limits. */
    class LimitedInput(private val input: InputStream, private val maximum: Long = MAX_ARCHIVE_BYTES) : InputStream() {
        private var consumed = 0L
        private fun add(count: Int) {
            if (count > 0) {
                consumed += count
                if (consumed > maximum) throw IOException("File sao lưu nén quá lớn")
            }
        }
        override fun read(): Int = input.read().also { if (it >= 0) add(1) }
        override fun read(buffer: ByteArray, offset: Int, length: Int): Int {
            if (length == 0) return 0
            val request = minOf(length.toLong(), (maximum - consumed + 1).coerceAtLeast(1)).toInt()
            return input.read(buffer, offset, request).also(::add)
        }
        override fun close() = input.close()
    }

    fun copy(input: InputStream, output: OutputStream, maximum: Long, budget: Budget): Long {
        val buffer = ByteArray(32 * 1024)
        var count = 0L
        while (true) {
            val n = input.read(buffer)
            if (n < 0) return count
            if (n == 0) {
                val one = input.read()
                if (one < 0) return count
                if (count >= maximum) throw IOException("Một file trong bản sao lưu quá lớn")
                budget.add(1); output.write(one); count++; continue
            }
            if (n.toLong() > maximum - count) throw IOException("Một file trong bản sao lưu quá lớn")
            budget.add(n); output.write(buffer, 0, n); count += n
        }
    }
}
