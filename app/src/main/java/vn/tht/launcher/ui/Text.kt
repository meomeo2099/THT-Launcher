package vn.tht.launcher.ui

import java.text.Normalizer

object Text {
    /** Chữ thường, bỏ dấu tiếng Việt (đ → d) – để tìm kiếm không phân biệt dấu. */
    fun plain(s: String): String =
        Normalizer.normalize(s.lowercase().replace('đ', 'd'), Normalizer.Form.NFD).replace(Regex("\\p{Mn}+"), "")
}
