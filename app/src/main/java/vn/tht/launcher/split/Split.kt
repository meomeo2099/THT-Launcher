package vn.tht.launcher.split

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import java.util.UUID

/**
 * Một cặp chia đôi màn hình: app bên trái, app bên phải và tỉ lệ (% chiều rộng của bên trái).
 * Có thể có nhiều cặp; mỗi cặp đặt được thành widget và nút trên thanh dưới.
 */
data class Split(
    val id: String,
    val left: String,
    val right: String,
    val ratio: Int = DEFAULT_RATIO,
) {
    fun toJson(): JSONObject = JSONObject().put("id", id).put("left", left).put("right", right).put("ratio", ratio)

    companion object {
        const val DEFAULT_RATIO = 50
        const val MIN_RATIO = 20
        const val MAX_RATIO = 80
        /** Tỉ lệ chọn nhanh. */
        val PRESETS = listOf(30, 40, 50, 60, 70)

        fun new(left: String, right: String) = Split(UUID.randomUUID().toString().take(8), left, right)

        fun fromJson(o: JSONObject): Split? = runCatching {
            Split(
                o.getString("id"), o.getString("left"), o.getString("right"),
                o.optInt("ratio", DEFAULT_RATIO).coerceIn(MIN_RATIO, MAX_RATIO),
            )
        }.getOrNull()
    }
}

/** Danh sách cặp chia đôi, lưu trong SharedPreferences (khoá "splits", JSON). */
object SplitStore {
    private const val KEY = "splits"

    private fun sp(c: Context) = vn.tht.launcher.settings.Prefs(c).sp

    fun all(c: Context): List<Split> = runCatching {
        val arr = JSONArray(sp(c).getString(KEY, "[]"))
        (0 until arr.length()).mapNotNull { Split.fromJson(arr.getJSONObject(it)) }
    }.getOrDefault(emptyList())

    fun get(c: Context, id: String): Split? = all(c).firstOrNull { it.id == id }

    fun put(c: Context, s: Split) {
        val list = all(c).toMutableList()
        val i = list.indexOfFirst { it.id == s.id }
        if (i >= 0) list[i] = s else list += s
        write(c, list)
    }

    fun delete(c: Context, id: String) = write(c, all(c).filter { it.id != id })

    private fun write(c: Context, list: List<Split>) {
        sp(c).edit().putString(KEY, JSONArray().apply { list.forEach { put(it.toJson()) } }.toString()).apply()
    }
}
