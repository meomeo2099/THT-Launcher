package vn.tht.launcher.settings

import android.content.Context
import android.net.Uri
import org.json.JSONObject
import java.io.File

/**
 * Xuất/nhập cài đặt ra một file JSON: Prefs + bố cục widget (home.json) + dock (shortcuts.json).
 * Ảnh nền riêng không nằm trong file sao lưu.
 */
object Backup {
    private const val FORMAT = "tht-launcher-backup"
    private const val VERSION = 1

    fun export(c: Context, uri: Uri): Boolean = try {
        val prefs = JSONObject()
        Prefs(c).sp.all.forEach { (k, v) -> prefs.put(k, JSONObject().put("t", typeOf(v)).put("v", v)) }
        val json = JSONObject()
            .put("format", FORMAT).put("version", VERSION)
            .put("prefs", prefs)
            .put("home", readOrNull(File(c.filesDir, "home.json")))
            .put("dock", readOrNull(vn.tht.launcher.shortcuts.ShortcutStore.file(c)))
        c.contentResolver.openOutputStream(uri, "wt")?.use { it.write(json.toString(2).toByteArray()) } != null
    } catch (_: Throwable) {
        false
    }

    /** null nếu thành công, ngược lại là thông báo lỗi. */
    fun import(c: Context, uri: Uri): String? {
        return try {
            val text = c.contentResolver.openInputStream(uri)?.use { it.readBytes().toString(Charsets.UTF_8) }
                ?: return "Không đọc được file"
            if (text.length > 2_000_000) return "File quá lớn"
            val json = JSONObject(text)
            if (json.optString("format") != FORMAT) return "Đây không phải file sao lưu THT Launcher"
            if (json.optInt("version") > VERSION) return "File sao lưu của phiên bản mới hơn"
            val prefs = json.getJSONObject("prefs")
            val ed = Prefs(c).sp.edit().clear()
            prefs.keys().forEach { k ->
                val o = prefs.getJSONObject(k)
                when (o.getString("t")) {
                    "b" -> ed.putBoolean(k, o.getBoolean("v"))
                    "i" -> ed.putInt(k, o.getInt("v"))
                    "f" -> ed.putFloat(k, o.getDouble("v").toFloat())
                    "l" -> ed.putLong(k, o.getLong("v"))
                    else -> ed.putString(k, o.getString("v"))
                }
            }
            ed.apply()
            json.optString("home").takeIf { it.isNotBlank() }?.let { File(c.filesDir, "home.json").writeText(it) }
            json.optString("dock").takeIf { it.isNotBlank() }?.let {
                vn.tht.launcher.shortcuts.ShortcutStore.file(c).apply { parentFile?.mkdirs() }.writeText(it)
            }
            null
        } catch (t: Throwable) {
            "File sao lưu hỏng: ${t.message}"
        }
    }

    private fun typeOf(v: Any?) = when (v) {
        is Boolean -> "b"; is Int -> "i"; is Float -> "f"; is Long -> "l"; else -> "s"
    }

    private fun readOrNull(f: File) = if (f.exists()) f.readText() else ""
}
