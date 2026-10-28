package vn.tht.launcher.home

import android.content.Context
import android.util.Log
import android.util.AtomicFile
import kotlin.math.roundToInt
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.util.UUID

/** Các loại widget trên màn hình chính. [title] là tên hiện trong thư viện widget. */
enum class WidgetType(val title: String, val group: String, val defaultW: Int, val defaultH: Int) {
    CLOCK("Đồng hồ lớn (trên nền)", "Đồng hồ", 900, 360),
    CLOCK_TILE("Đồng hồ & thời tiết", "Đồng hồ", 507, 250),
    MEDIA("Nhạc", "Nhạc & dẫn đường", 506, 516),
    NAV("Dẫn đường", "Nhạc & dẫn đường", 506, 516),
    RADIO("Radio", "Nhạc & dẫn đường", 620, 120),
    CLIMATE("Điều hoà", "Xe", 776, 280),
    SPEED("Tốc độ & số", "Xe", 440, 360),
    FUEL("Xăng & quãng đường", "Xe", 380, 280),
    CAR("Chế độ lái & đèn", "Xe", 380, 280),
    TRIP("Chuyến đi", "Xe", 380, 280),
    STAT_FUEL("Ô xăng (lít)", "Ô nhỏ", 242, 117),
    STAT_VOLTAGE("Ô điện áp ắc quy", "Ô nhỏ", 242, 117),
    STAT_COOLANT("Ô nhiệt độ máy", "Ô nhỏ", 242, 117),
    STAT_RANGE("Ô quãng đường còn lại", "Ô nhỏ", 242, 117),
    BLUETOOTH("Ô Bluetooth", "Ô nhỏ", 242, 117),
    WIFI("Ô Wi-Fi", "Ô nhỏ", 242, 117),
    VOLUME("Thanh âm lượng", "Thanh trượt", 96, 516),
    BRIGHTNESS("Thanh độ sáng", "Thanh trượt", 96, 516),
    APP("Lối tắt ứng dụng", "Ứng dụng", 200, 200),
    IMAGE("Ảnh có vùng chạm (ví dụ ảnh xe)", "Ứng dụng", 640, 360),
    SPLIT("Chia đôi màn hình", "Ứng dụng", 320, 200),
    ANDROID_WIDGET("Widget Android (của app khác)", "Ứng dụng", 480, 260),
    TOP_BAR("Thanh trạng thái (trên)", "Thanh", 520, 48),
    BOTTOM_BAR("Thanh dưới (dock)", "Thanh", 1800, 128),
    CLIMATE_MINI("Cụm điều hoà gọn", "Thanh", 320, 100),
    FAN_MINI("Cụm quạt gió gọn", "Thanh", 360, 100),
    SIDE_RAIL("Thanh dọc", "Thanh", 72, 440),
}

/** Các thanh: mỗi loại chỉ có một trên màn hình. */
val WidgetType.isBar get() = this == WidgetType.TOP_BAR || this == WidgetType.BOTTOM_BAR ||
    this == WidgetType.SIDE_RAIL || this == WidgetType.CLIMATE_MINI || this == WidgetType.FAN_MINI

/**
 * Vị trí/kích thước một widget, đơn vị dp trên khung thiết kế [HomeLayout.DESIGN_W]×[HomeLayout.DESIGN_H].
 * [pkg]: gói app ([WidgetType.APP]), cấu hình ảnh ([WidgetType.IMAGE]) hoặc id cặp chia đôi ([WidgetType.SPLIT]).
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
    /** Phiên bản lưu trữ; đổi preset không ghi đè bố cục người dùng. */
    private const val VERSION = 4
    const val GRID = 20
    const val MIN_SIZE = 40

    const val REFERENCE_PRESET = "reference"
    const val CLASSIC_PRESET = "classic"

    /**
     * Hình học tham chiếu dùng khung 1280×720: hai slider, dẫn đường, nhạc, đồng hồ/thời tiết,
     * bốn ô nhỏ và một dock chung. Coolray 1920×720 mở rộng trục X đúng theo tỉ lệ 1,5.
     * Chỉ dùng cho cài mới hoặc khi người dùng chủ động chọn lại preset.
     */
    fun referenceDefaults(): List<WidgetSpec> {
        fun item(id: String, type: WidgetType, x: Int, y: Int, w: Int, h: Int): WidgetSpec {
            val left = (x * DESIGN_W / 1280f).roundToInt()
            val right = ((x + w) * DESIGN_W / 1280f).roundToInt()
            return WidgetSpec(id, type, left, y, right - left, h)
        }
        return listOf(
            item("brightness", WidgetType.BRIGHTNESS, 40, 40, 64, 516),
            item("volume", WidgetType.VOLUME, 116, 40, 64, 516),
            item("nav", WidgetType.NAV, 196, 40, 337, 516),
            item("media", WidgetType.MEDIA, 549, 40, 337, 516),
            item("clock", WidgetType.CLOCK_TILE, 902, 40, 338, 250),
            item("fuel", WidgetType.STAT_FUEL, 902, 306, 161, 117),
            item("voltage", WidgetType.STAT_VOLTAGE, 1079, 306, 161, 117),
            item("bt", WidgetType.BLUETOOTH, 902, 439, 161, 117),
            item("wifi", WidgetType.WIFI, 1079, 439, 161, 117),
            item("bottom_bar", WidgetType.BOTTOM_BAR, 40, 572, 1200, 128),
        )
    }

    fun defaults(): List<WidgetSpec> = referenceDefaults()

    fun preset(id: String): List<WidgetSpec> = when (id) {
        CLASSIC_PRESET -> classicDefaults()
        else -> referenceDefaults()
    }

    /** Giữ sẵn bố cục THT cũ để người dùng có thể chuyển về khi cần. */
    fun classicDefaults(): List<WidgetSpec> = listOf(
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
        WidgetSpec("fan_mini", WidgetType.FAN_MINI, 352, 612, 360, 100),
        WidgetSpec("bottom_bar", WidgetType.BOTTOM_BAR, 728, 612, 1176, 100),
    )

    private fun file(c: Context) = File(c.filesDir, FILE)

    /** Chuẩn hóa đầu vào mà không bỏ toàn bộ bố cục chỉ vì phiên bản cũ. */
    fun bounded(spec: WidgetSpec): WidgetSpec {
        val w = spec.w.coerceIn(MIN_SIZE, DESIGN_W)
        val h = spec.h.coerceIn(MIN_SIZE, DESIGN_H)
        return spec.copy(
            x = spec.x.coerceIn(0, DESIGN_W - w),
            y = spec.y.coerceIn(0, DESIGN_H - h),
            w = w, h = h,
        )
    }

    /** Giữ thứ tự và dữ liệu hợp lệ của bố cục cũ; chỉ loại mục trùng hoặc đầu vào lỗi. */
    internal fun sanitize(specs: List<WidgetSpec>): MutableList<WidgetSpec> {
        val ids = HashSet<String>()
        val bars = HashSet<WidgetType>()
        return specs.take(96).mapNotNull { spec ->
            if (spec.id.isBlank() || spec.id.length > 120 || spec.pkg.length > 16384) return@mapNotNull null
            if (spec.id in ids || (spec.type.isBar && spec.type in bars)) return@mapNotNull null
            ids.add(spec.id)
            if (spec.type.isBar) bars.add(spec.type)
            bounded(spec)
        }.toMutableList()
    }

    fun load(c: Context): MutableList<WidgetSpec> = try {
        val f = file(c)
        if (!f.exists() && !File(f.path + ".bak").exists()) defaults().toMutableList()
        else {
            val json = JSONObject(AtomicFile(f).openRead().bufferedReader().use { it.readText() })
            val arr = json.getJSONArray("widgets")
            sanitize((0 until minOf(arr.length(), 96)).mapNotNull { index ->
                val obj = arr.optJSONObject(index) ?: return@mapNotNull null
                WidgetSpec.fromJson(obj)
            })
        }
    } catch (t: Throwable) {
        Log.w(TAG, "Không đọc được bố cục, dùng mặc định", t)
        defaults().toMutableList()
    }

    fun save(c: Context, list: List<WidgetSpec>) {
        val atomic = AtomicFile(file(c))
        var stream: java.io.FileOutputStream? = null
        try {
            val json = JSONObject().put("version", VERSION)
                .put("widgets", JSONArray().apply { list.take(96).forEach { put(bounded(it).toJson()) } })
            val output = atomic.startWrite()
            stream = output
            output.write(json.toString().toByteArray(Charsets.UTF_8))
            atomic.finishWrite(output)
        } catch (t: Throwable) {
            stream?.let { atomic.failWrite(it) }
            Log.w(TAG, "Không ghi được bố cục", t)
        }
    }

    fun reset(c: Context) {
        AtomicFile(file(c)).delete()
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
        WidgetType.SPLIT -> "dual"
        WidgetType.ANDROID_WIDGET -> "apps"
        WidgetType.TOP_BAR -> "chevron-up"
        WidgetType.BOTTOM_BAR -> "chevron-down"
        WidgetType.SIDE_RAIL -> "chevron-left"
        WidgetType.CLIMATE_MINI -> "climate"
        WidgetType.FAN_MINI -> "fan"
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
