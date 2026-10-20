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
            TYPE_HVAC -> true
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
 * Lần đầu chạy (hoặc file hỏng) sẽ ghi bản mặc định với chỗ trống để điền package/activity thật.
 */
object ShortcutStore {
    private const val TAG = "ShortcutStore"
    private const val FILE = "shortcuts.json"
    private const val VERSION = 1

    /** Mặc định cho Coolray 2025. package/activity thật cần lấy từ xe nên để trống. */
    val DEFAULTS = listOf(
        Shortcut("camera", "Camera 540°", Shortcut.TYPE_ACTIVITY),
        Shortcut("hvac", "Điều hoà", Shortcut.TYPE_HVAC),
        Shortcut("carplay", "CarPlay", Shortcut.TYPE_ACTIVITY),
        Shortcut("car_settings", "Cài đặt xe", Shortcut.TYPE_ACTIVITY),
    )

    fun file(context: Context): File =
        File(context.getExternalFilesDir(null) ?: context.filesDir, FILE)

    fun load(context: Context): List<Shortcut> {
        val f = file(context)
        try {
            if (f.exists()) {
                val arr = JSONObject(f.readText()).getJSONArray("shortcuts")
                val list = (0 until arr.length()).map { Shortcut.fromJson(arr.getJSONObject(it)) }
                if (list.isNotEmpty()) return list
            }
        } catch (t: Throwable) {
            Log.w(TAG, "shortcuts.json hỏng, dùng mặc định: $t")
            runCatching { f.copyTo(File(f.path + ".bak"), overwrite = true) }
        }
        save(context, DEFAULTS)
        return DEFAULTS
    }

    fun save(context: Context, list: List<Shortcut>) {
        try {
            val json = JSONObject()
                .put("version", VERSION)
                .put("shortcuts", JSONArray().apply { list.forEach { put(it.toJson()) } })
            file(context).apply { parentFile?.mkdirs() }.writeText(json.toString(2))
        } catch (t: Throwable) {
            Log.w(TAG, "Không ghi được shortcuts.json: $t")
        }
    }
}
