package vn.tht.launcher.home

import android.content.Context
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.util.UUID

/** Các loại widget trên màn hình chính. [title] là tên hiện trong thư viện widget. */
enum class WidgetType(val title: String, val defaultW: Int, val defaultH: Int) {
    CLOCK("Đồng hồ", 900, 360),
    SPEED("Tốc độ & số", 440, 360),
    MEDIA("Nhạc", 760, 300),
    CLIMATE("Điều hoà", 776, 280),
    FUEL("Xăng & quãng đường", 380, 280),
    CAR("Xe: chế độ lái, đèn viền", 380, 280),
    APP("Lối tắt ứng dụng", 200, 200),
    TRIP("Chuyến đi", 380, 280),
}

/**
 * Vị trí/kích thước một widget, đơn vị dp trên khung thiết kế [HomeLayout.DESIGN_W]×[HomeLayout.DESIGN_H].
 * [pkg] chỉ dùng cho [WidgetType.APP].
 */
data class WidgetSpec(
    val id: String,
    val type: WidgetType,
    var x: Int,
    var y: Int,
    var w: Int,
    var h: Int,
    var pkg: String = "",
) {
    fun toJson(): JSONObject = JSONObject()
        .put("id", id).put("type", type.name)
        .put("x", x).put("y", y).put("w", w).put("h", h).put("pkg", pkg)

    companion object {
        fun new(type: WidgetType, x: Int = 16, y: Int = 16) =
            WidgetSpec(UUID.randomUUID().toString(), type, x, y, type.defaultW, type.defaultH)

        fun fromJson(o: JSONObject): WidgetSpec? = runCatching {
            WidgetSpec(
                id = o.getString("id"),
                type = WidgetType.valueOf(o.getString("type")),
                x = o.getInt("x"), y = o.getInt("y"), w = o.getInt("w"), h = o.getInt("h"),
                pkg = o.optString("pkg"),
            )
        }.getOrNull()
    }
}

/** Lưu bố cục màn hình chính trong filesDir/home.json. */
object HomeLayout {
    private const val TAG = "HomeLayout"
    private const val FILE = "home.json"

    /** Khung thiết kế: màn 1920×720dp trừ dock và lề. */
    const val DESIGN_W = 1920
    const val DESIGN_H = 600
    const val GRID = 20
    const val MIN_SIZE = 120

    fun defaults(): List<WidgetSpec> = listOf(
        WidgetSpec("clock", WidgetType.CLOCK, 20, 40, 900, 360),
        WidgetSpec("media", WidgetType.MEDIA, 1100, 20, 760, 300),
        WidgetSpec("fuel", WidgetType.FUEL, 1100, 340, 370, 240),
        WidgetSpec("car", WidgetType.CAR, 1490, 340, 370, 240),
    )

    private fun file(c: Context) = File(c.filesDir, FILE)

    fun load(c: Context): MutableList<WidgetSpec> = try {
        val f = file(c)
        if (!f.exists()) defaults().toMutableList()
        else {
            val arr = JSONObject(f.readText()).getJSONArray("widgets")
            (0 until arr.length()).mapNotNull { WidgetSpec.fromJson(arr.getJSONObject(it)) }.toMutableList()
        }
    } catch (t: Throwable) {
        Log.w(TAG, "home.json hỏng, dùng mặc định: $t")
        defaults().toMutableList()
    }

    fun save(c: Context, list: List<WidgetSpec>) {
        try {
            val json = JSONObject().put("version", 1)
                .put("widgets", JSONArray().apply { list.forEach { put(it.toJson()) } })
            file(c).writeText(json.toString())
        } catch (t: Throwable) {
            Log.w(TAG, "Không ghi được home.json: $t")
        }
    }

    fun reset(c: Context) {
        file(c).delete()
    }
}
