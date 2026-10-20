package vn.tht.launcher.ui

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import org.json.JSONObject
import vn.tht.launcher.R
import java.io.File
import java.util.zip.ZipInputStream

/**
 * Biểu tượng theo tên chức năng ("ac", "seat-heat-left", "play"…).
 *
 * Mặc định dùng vector có sẵn trong app. Người dùng có thể nạp một bộ biểu tượng dạng .zip
 * (các PNG đặt tên theo chức năng + manifest.json {name, author, mode: "mono"|"color"}),
 * cùng định dạng các bộ icon cộng đồng đang dùng. Bộ icon chỉ lưu trên máy người dùng
 * (filesDir/iconpack), không đóng gói trong APK. "mono" được tô lại theo màu theme.
 */
object Icons {
    private const val TAG = "Icons"
    private const val DIR = "iconpack"
    private const val MAX_FILES = 200
    private const val MAX_BYTES = 1_500_000L

    /** Vector mặc định cho từng tên; tên không có ở đây và không có trong bộ icon -> null. */
    private val DEFAULTS = mapOf(
        "play" to R.drawable.ic_play, "pause" to R.drawable.ic_pause,
        "next" to R.drawable.ic_next, "previous" to R.drawable.ic_prev,
        "apps" to R.drawable.ic_apps, "settings" to R.drawable.ic_settings,
        "plus" to R.drawable.ic_add, "minus" to R.drawable.ic_minus,
        "ac" to R.drawable.ic_ac, "climate" to R.drawable.ic_ac,
        "shortcut" to R.drawable.ic_tune, "home" to R.drawable.ic_home,
        "power" to R.drawable.ic_power, "brightness" to R.drawable.ic_brightness,
        "music" to R.drawable.ic_music, "fuel" to R.drawable.ic_fuel,
        "eco" to R.drawable.ic_eco, "car" to R.drawable.ic_car,
    )

    private var packDir: File? = null
    private var mono = true
    var packName: String? = null; private set
    private val cache = HashMap<String, Entry?>()

    fun init(c: Context) {
        val dir = File(c.filesDir, DIR)
        cache.clear()
        packDir = null; packName = null
        val manifest = File(dir, "manifest.json")
        if (!manifest.exists()) return
        runCatching {
            val m = JSONObject(manifest.readText())
            packName = m.optString("name", "Bộ biểu tượng") + m.optString("author").let { if (it.isBlank()) "" else " · $it" }
            mono = m.optString("mode", "mono") != "color"
            packDir = dir
        }.onFailure { Log.w(TAG, "manifest.json hỏng: $it") }
    }

    /**
     * Biểu tượng cho [key]. Ban đêm ưu tiên bản "-color-night" nếu bộ icon có. Icon mono được tô
     * [tint] (mặc định màu chữ theme); file "-color" giữ nguyên màu.
     */
    fun get(c: Context, key: String, tint: Int = Palette.text): Drawable? {
        packDir?.let { dir ->
            val ck = "$key/${Palette.night}"
            val entry = cache.getOrPut(ck) {
                val names = listOfNotNull(if (Palette.night) "$key-color-night.png" else null, "$key-color.png", "$key.png")
                names.map { File(dir, it) }.firstOrNull { it.exists() }?.let { f ->
                    BitmapFactory.decodeFile(f.path)?.let { Entry(BitmapDrawable(c.resources, it), f.name.contains("-color")) }
                }
            }
            if (entry != null) {
                val d = entry.drawable.constantState!!.newDrawable(c.resources).mutate()
                if (mono && !entry.color) d.setTint(tint)
                return d
            }
        }
        val res = DEFAULTS[key] ?: return null
        return c.getDrawable(res)?.mutate()?.apply { setTint(tint) }
    }

    private class Entry(val drawable: BitmapDrawable, val color: Boolean)

    /** Nạp bộ icon từ file zip người dùng chọn. null = thành công, ngược lại là thông báo lỗi. */
    fun install(c: Context, uri: Uri): String? {
        val tmp = File(c.filesDir, "$DIR.tmp").apply { deleteRecursively(); mkdirs() }
        try {
            var files = 0
            var total = 0L
            c.contentResolver.openInputStream(uri)?.use { input ->
                ZipInputStream(input).use { zip ->
                    while (true) {
                        val e = zip.nextEntry ?: break
                        if (e.isDirectory) continue
                        val name = e.name.substringAfterLast('/')
                        // Chỉ nhận PNG và manifest, tên đơn giản (chống ghi ra ngoài thư mục)
                        if (!(name.endsWith(".png", true) || name == "manifest.json")) continue
                        if (!name.matches(Regex("[A-Za-z0-9 _.-]+"))) continue
                        if (++files > MAX_FILES) return "Bộ icon có quá nhiều file"
                        val bytes = zip.readBytes()
                        if (bytes.size > MAX_BYTES) return "Ảnh $name quá lớn"
                        total += bytes.size
                        if (total > 50_000_000L) return "Bộ icon quá lớn"
                        File(tmp, name.replace(" ", "")).writeBytes(bytes)
                    }
                }
            } ?: return "Không đọc được file"
            if (!File(tmp, "manifest.json").exists()) {
                File(tmp, "manifest.json").writeText("""{"name":"Bộ biểu tượng","mode":"mono"}""")
            }
            if (tmp.listFiles().orEmpty().none { it.name.endsWith(".png") }) return "File zip không có ảnh PNG"
            val dir = File(c.filesDir, DIR)
            dir.deleteRecursively()
            if (!tmp.renameTo(dir)) return "Không lưu được bộ icon"
            init(c)
            return null
        } catch (t: Throwable) {
            return "File zip hỏng: ${t.message}"
        } finally {
            tmp.deleteRecursively()
        }
    }

    fun remove(c: Context) {
        File(c.filesDir, DIR).deleteRecursively()
        init(c)
    }
}
