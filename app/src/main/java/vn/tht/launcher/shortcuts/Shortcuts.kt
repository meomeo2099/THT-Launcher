package vn.tht.launcher.shortcuts

import android.content.Context
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

/**
 * Một lối tắt trên thanh lối tắt.
 *
 * type:
 *  - "hvac":     mở điều hoà gốc qua [vn.tht.launcher.vehicle.HvacOpener]
 *                (package/service tuỳ chọn, mặc định ecarx.hvac.app + tự dò service)
 *  - "activity": mở activity [pkg]/[activity]; bỏ trống activity = mở app [pkg]
 *  - "action":   startActivity với intent [action] (kèm [pkg] nếu có)
 *  - "controls": mở bảng điều khiển xe (điều hoà, chế độ lái, đèn viền)
 *  - "apps":     mở ngăn kéo tất cả ứng dụng
 *  - "settings": mở cài đặt launcher
 */
data class Shortcut(
    val id: String,
    val label: String,
    val type: String,
    val pkg: String = "",
    val activity: String = "",
    val action: String = "",
    val service: String = "",
) {
    /** Chưa điền package/action: hiện mờ, chạm vào chỉ nhắc cách cấu hình. */
    val configured: Boolean
        get() = when (type) {
            TYPE_HVAC, TYPE_CONTROLS, TYPE_APPS, TYPE_SETTINGS, TYPE_STATS, TYPE_STATUS, TYPE_COLLAPSE -> true
            TYPE_ACTION -> action.isNotBlank()
            else -> pkg.isNotBlank()
        }

    fun toJson(): JSONObject = JSONObject()
        .put("id", id).put("label", label).put("type", type)
        .put("package", pkg).put("activity", activity)
        .put("action", action).put("service", service)

    companion object {
        const val TYPE_HVAC = "hvac"
        const val TYPE_ACTIVITY = "activity"
        const val TYPE_ACTION = "action"
        const val TYPE_CONTROLS = "controls"
        const val TYPE_APPS = "apps"
        const val TYPE_SETTINGS = "settings"
        const val TYPE_STATS = "stats"
        /** Ô trạng thái sống (icon + giá trị); [action] là tên chỉ số, xem [DockBar.STATUS]. */
        const val TYPE_STATUS = "status"
        /** Ẩn/hiện tất cả widget (chỉ còn nền + thanh dưới). */
        const val TYPE_COLLAPSE = "collapse"

        fun fromJson(o: JSONObject) = Shortcut(
            id = o.optString("id"),
            label = o.optString("label", o.optString("id")),
            type = o.optString("type", TYPE_ACTIVITY),
            pkg = o.optString("package"),
            activity = o.optString("activity"),
            action = o.optString("action"),
            service = o.optString("service"),
        )
    }
}

/**
 * Đọc/ghi cấu hình lối tắt dạng JSON.
 *
 * File nằm ở thư mục ngoài riêng của app để sửa được bằng adb mà không cần root:
 *   /sdcard/Android/data/vn.tht.launcher/files/shortcuts.json
 * Lần đầu chạy (hoặc file hỏng) sẽ ghi bản mặc định; file bản cũ được nâng cấp, giữ mục đã sửa.
 */
object ShortcutStore {
    private const val TAG = "ShortcutStore"
    private const val FILE = "shortcuts.json"
    private const val VERSION = 4
    const val MAX = 20

    /**
     * Mặc định cho Coolray 2025. Package lấy từ danh sách gói trên đầu xe (Coolray2025-Voice-Spec.md
     * mục 7); activity để trống = mở activity chính của app. Chưa thử mở trên xe.
     */
    val DEFAULTS = listOf(
        Shortcut("status_fuel_l", "Xăng", Shortcut.TYPE_STATUS, action = "fuel_l"),
        Shortcut("status_seat_heat", "Sưởi ghế lái", Shortcut.TYPE_STATUS, action = "seat_heat"),
        Shortcut(
            "voice", "THT Voice", Shortcut.TYPE_ACTION,
            pkg = "com.tht.voice.coolray2025", action = "android.intent.action.VOICE_COMMAND",
        ),
        Shortcut("hvac", "Điều hoà", Shortcut.TYPE_HVAC),
        Shortcut("camera", "Camera 540°", Shortcut.TYPE_ACTIVITY, pkg = "com.neusoft.nsapa"),
        Shortcut("carplay", "CarPlay", Shortcut.TYPE_ACTIVITY, pkg = "com.neusoft.carplay"),
        Shortcut("controls", "Điều khiển", Shortcut.TYPE_CONTROLS),
        Shortcut("settings", "Cài đặt", Shortcut.TYPE_SETTINGS),
        Shortcut("apps", "Ứng dụng", Shortcut.TYPE_APPS),
        Shortcut("car_settings", "Cài đặt xe", Shortcut.TYPE_ACTIVITY, pkg = "ecarx.settings"),
        Shortcut("stats", "Thống kê", Shortcut.TYPE_STATS),
        Shortcut("collapse", "Ẩn/hiện tất cả", Shortcut.TYPE_COLLAPSE),
    )

    /** Thanh trạng thái nhỏ phía trên màn hình. */
    const val TOPBAR_FILE = "topbar.json"

    private fun status(metric: String, label: String) =
        Shortcut("status_$metric", label, Shortcut.TYPE_STATUS, action = metric)

    val TOPBAR_DEFAULTS = listOf(
        status("wifi", "Wi-Fi"),
        Shortcut("apps", "Ứng dụng", Shortcut.TYPE_APPS),
        status("coolant", "Nhiệt độ máy"),
        status("fuel_l", "Xăng"),
        status("voltage", "Điện áp"),
        status("seat_heat", "Sưởi ghế lái"),
        status("home", "Màn hình chính"),
    )

    fun file(context: Context, name: String = FILE): File =
        File(context.getExternalFilesDir(null) ?: context.filesDir, name)

    fun load(context: Context, name: String = FILE, defaults: List<Shortcut> = DEFAULTS): List<Shortcut> {
        val f = file(context, name)
        try {
            if (f.exists()) {
                val json = JSONObject(f.readText())
                val arr = json.getJSONArray("shortcuts")
                var list = (0 until arr.length()).map { Shortcut.fromJson(arr.getJSONObject(it)) }
                if (list.isNotEmpty()) {
                    if (name == FILE && json.optInt("version", 1) < VERSION) {
                        list = migrate(list)
                        save(context, list)
                    }
                    return list
                }
            }
        } catch (t: Throwable) {
            Log.w(TAG, "$name hỏng, dùng mặc định: $t")
            runCatching { f.copyTo(File(f.path + ".bak"), overwrite = true) }
        }
        save(context, defaults, name)
        return defaults
    }

    /** Bản cũ: điền package mặc định vào mục còn trống và thêm mục mới; giữ nguyên mục người dùng đã sửa. */
    private fun migrate(old: List<Shortcut>): List<Shortcut> {
        val byId = DEFAULTS.associateBy { it.id }
        val updated = old.map { s ->
            val d = byId[s.id]
            if (d != null && s.type == d.type && s.pkg.isBlank() && s.action.isBlank()) d.copy(label = s.label) else s
        }
        val ids = updated.map { it.id }.toSet()
        return updated + DEFAULTS.filter { it.id !in ids }
    }

    fun save(context: Context, list: List<Shortcut>, name: String = FILE) {
        try {
            val json = JSONObject()
                .put("version", VERSION)
                .put("shortcuts", JSONArray().apply { list.forEach { put(it.toJson()) } })
            file(context, name).apply { parentFile?.mkdirs() }.writeText(json.toString(2))
        } catch (t: Throwable) {
            Log.w(TAG, "Không ghi được $name: $t")
        }
    }
}
