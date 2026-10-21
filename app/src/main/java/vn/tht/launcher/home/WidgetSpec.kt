package vn.tht.launcher.home

import android.content.Context
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.util.UUID

/** Các loại widget trên màn hình chính. [title] là tên hiện trong thư viện widget. */
enum class WidgetType(val title: String, val group: String, val defaultW: Int, val defaultH: Int) {
    CLOCK("Đồng hồ lớn (trên nền)", "Đồng hồ", 900, 360),
    CLOCK_TILE("Đồng hồ dạng thẻ", "Đồng hồ", 480, 260),
    MEDIA("Nhạc", "Nhạc & dẫn đường", 520, 560),
    NAV("Dẫn đường", "Nhạc & dẫn đường", 620, 560),
    RADIO("Radio", "Nhạc & dẫn đường", 620, 120),
    CLIMATE("Điều hoà", "Xe", 776, 280),
    SPEED("Tốc độ & số", "Xe", 440, 360),
    FUEL("Xăng & quãng đường", "Xe", 380, 280),
    CAR("Chế độ lái & đèn", "Xe", 380, 280),
    TRIP("Chuyến đi", "Xe", 380, 280),
    STAT_FUEL("Ô xăng (lít)", "Ô nhỏ", 240, 140),
    STAT_VOLTAGE("Ô điện áp ắc quy", "Ô nhỏ", 240, 140),
    STAT_COOLANT("Ô nhiệt độ máy", "Ô nhỏ", 240, 140),
    STAT_RANGE("Ô quãng đường còn lại", "Ô nhỏ", 240, 140),
    BLUETOOTH("Ô Bluetooth", "Ô nhỏ", 240, 140),
    WIFI("Ô Wi-Fi", "Ô nhỏ", 240, 140),
    VOLUME("Thanh âm lượng", "Thanh trượt", 96, 560),
    BRIGHTNESS("Thanh độ sáng", "Thanh trượt", 96, 560),
    APP("Lối tắt ứng dụng", "Ứng dụng", 200, 200),
    IMAGE("Ảnh có vùng chạm (ví dụ ảnh xe)", "Ứng dụng", 640, 360),
    TOP_BAR("Thanh trạng thái (trên)", "Thanh", 520, 48),
    BOTTOM_BAR("Thanh dưới (dock)", "Thanh", 1552, 100),
    CLIMATE_MINI("Cụm điều hoà gọn", "Thanh", 320, 100),
    SIDE_RAIL("Thanh dọc", "Thanh", 72, 440),
}

/** Các thanh: mỗi loại chỉ có một trên màn hình. */
val WidgetType.isBar get() = this == WidgetType.TOP_BAR || this == WidgetType.BOTTOM_BAR ||
    this == WidgetType.SIDE_RAIL || this == WidgetType.CLIMATE_MINI

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

    /** Khung thiết kế: toàn màn 1920×720dp (kể cả các thanh). */
    const val DESIGN_W = 1920
    const val DESIGN_H = 720
    /** Tăng khi đổi khung: bố cục cũ được thay bằng mặc định. */
    private const val VERSION = 3
    const val GRID = 20
    const val MIN_SIZE = 40

    /** Bố cục mặc định dạng lưới (giống bố cục phổ biến của người dùng AutoEasy), khe 16dp. */
    fun defaults(): List<WidgetSpec> = listOf(
        WidgetSpec("top_bar", WidgetType.TOP_BAR, 700, 8, 520, 48),
        WidgetSpec("brightness", WidgetType.BRIGHTNESS, 16, 72, 96, 528),
        WidgetSpec("volume", WidgetType.VOLUME, 128, 72, 96, 528),
        WidgetSpec("nav", WidgetType.NAV, 240, 72, 620, 528),
        WidgetSpec("media", WidgetType.MEDIA, 876, 72, 520, 528),
        WidgetSpec("clock", WidgetType.CLOCK_TILE, 1412, 72, 492, 248),
        WidgetSpec("fuel", WidgetType.STAT_FUEL, 1412, 336, 238, 124),
        WidgetSpec("voltage", WidgetType.STAT_VOLTAGE, 1666, 336, 238, 124),
        WidgetSpec("bt", WidgetType.BLUETOOTH, 1412, 476, 238, 124),
        WidgetSpec("wifi", WidgetType.WIFI, 1666, 476, 238, 124),
        WidgetSpec("climate_mini", WidgetType.CLIMATE_MINI, 16, 612, 320, 100),
        WidgetSpec("bottom_bar", WidgetType.BOTTOM_BAR, 352, 612, 1552, 100),
    )

    private fun file(c: Context) = File(c.filesDir, FILE)

    fun load(c: Context): MutableList<WidgetSpec> = try {
        val f = file(c)
        if (!f.exists()) defaults().toMutableList()
        else {
            val json = JSONObject(f.readText())
            if (json.optInt("version", 1) < VERSION) defaults().toMutableList()
            else {
                val arr = json.getJSONArray("widgets")
                (0 until arr.length()).mapNotNull { WidgetSpec.fromJson(arr.getJSONObject(it)) }.toMutableList()
            }
        }
    } catch (t: Throwable) {
        Log.w(TAG, "home.json hỏng, dùng mặc định: $t")
        defaults().toMutableList()
    }

    fun save(c: Context, list: List<WidgetSpec>) {
        try {
            val json = JSONObject().put("version", VERSION)
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

/** Icon của loại widget (thư viện widget). */
object WidgetSpecIcons {
    val WidgetType.iconKey: String
    get() = when (this) {
        WidgetType.CLOCK -> "clock"
        WidgetType.CLOCK_TILE -> "clock"
        WidgetType.MEDIA -> "music"
        WidgetType.NAV -> "navigation"
        WidgetType.RADIO -> "radio"
        WidgetType.CLIMATE -> "climate"
        WidgetType.SPEED -> "sport"
        WidgetType.FUEL -> "fuel"
        WidgetType.CAR -> "car"
        WidgetType.TRIP -> "trip"
        WidgetType.STAT_FUEL -> "fuel"
        WidgetType.STAT_VOLTAGE -> "battery"
        WidgetType.STAT_COOLANT -> "engine-temp"
        WidgetType.STAT_RANGE -> "range"
        WidgetType.BLUETOOTH -> "bluetooth"
        WidgetType.WIFI -> "wifi"
        WidgetType.VOLUME -> "speaker"
        WidgetType.BRIGHTNESS -> "sun"
        WidgetType.APP -> "apps"
        WidgetType.IMAGE -> "car"
        WidgetType.TOP_BAR -> "chevron-up"
        WidgetType.BOTTOM_BAR -> "chevron-down"
        WidgetType.SIDE_RAIL -> "chevron-left"
        WidgetType.CLIMATE_MINI -> "climate"
    }
}

/** Icon của nhóm widget. */
fun groupIcon(group: String) = when (group) {
    "Đồng hồ" -> "clock"
    "Nhạc & dẫn đường" -> "music"
    "Xe" -> "car"
    "Ô nhỏ" -> "wifi"
    "Ứng dụng" -> "apps"
    "Thanh" -> "shortcut"
    "Thanh trượt" -> "brightness"
    else -> "plus"
}
