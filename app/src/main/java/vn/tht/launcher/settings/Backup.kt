package vn.tht.launcher.settings

import android.content.Context
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.Typeface
import android.net.Uri
import org.json.JSONArray
import org.json.JSONObject
import vn.tht.launcher.home.WidgetType
import vn.tht.launcher.shortcuts.ShortcutStore
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.UUID
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

/** User-selected ZIP backups. All content validates in cache before any live state changes. */
object Backup {
    const val MIME_TYPE = "application/zip"
    const val FILE_EXTENSION = "zip"
    const val DEFAULT_FILE_NAME = "THT-Launcher-backup.zip"
    private const val FORMAT = "tht-launcher-backup"
    private const val VERSION = 2
    private val booleans = setOf("clock_24h", "clock_date", "dock_labels", "top_bar", "volume_slider",
        "collapsed", "side_rail", "climate_mini", "full_screen", "open_on_boot", "saver_on", "saver_parked",
        "saver_track", "saver_weather", "autostart_home", "stats_on", "demo_mode", "embed_map", "update_auto",
        "auto_on", "auto_apps_on", "auto_air_out", "auto_climate_auto", "auto_heat_rear_glass", "hide_stock_hvac", "temp_info", "nav_bar_home", "nav_bar_collapsed",
        "use_wallpaper_day", "use_wallpaper_night")
    private val integers = setOf("card_opacity", "dock_opacity", "ui_scale", "saver_min", "saver_dim",
        "autostart_delay", "tank_l", "service_km", "auto_parked_min", "auto_heat_drv", "auto_heat_pas",
        "auto_heat_wheel", "auto_heat_below", "auto_heat_off", "auto_vent_drv", "auto_vent_pas",
        "auto_vent_above", "auto_vent_off", "auto_air_above", "auto_air_fan", "auto_air_min", "auto_climate_temp", "auto_roof_speed", "nav_bar_overscan")
    private val strings = setOf("theme_mode", "bg_day", "bg_night", "card_style", "accent", "dock_size",
        "dock_overlay", "split_backend", "nav_app", "autostart", "autoplay_player", "drawer_favorites", "splits", "nav_mode", "nav_bar_items")
    private val floats = setOf("nav_pill_x", "nav_pill_y", "temp_info_x", "temp_info_y")
    private val longs = setOf("service_date")
    private val settingKeys get() = booleans + integers + strings + floats + longs
    private val shortcutTypes = setOf("hvac", "activity", "action", "controls", "apps", "settings", "stats", "status", "collapse", "cleanup", "split", "climate_inline")

    /** Construct privately first, so a source error does not truncate the chosen document. */
    @Synchronized fun export(c: Context, uri: Uri): Boolean {
        val temp = File(c.cacheDir, "backup-export-${UUID.randomUUID()}.zip")
        return try {
            val files = managedFiles(c)
            val settings = JSONObject().put("format", FORMAT).put("version", VERSION)
                .put("prefs", encode(Prefs(c).sp.all.filterKeys { it in settingKeys }))
                .put("fontPrefs", encode(c.getSharedPreferences("fonts", 0).all.filterKeys { it == "name" && "files/font_custom" in files }))
                .put("radioPrefs", encode(c.getSharedPreferences("radio", 0).all.filterKeys { it in setOf("favorites", "last_fm", "last_am") }))
                .put("files", JSONArray(files.keys.sorted()))
            val metadata = settings.toString(2).toByteArray(Charsets.UTF_8)
            require(metadata.size <= BackupArchivePolicy.MAX_SETTINGS_BYTES)
            val budget = BackupArchivePolicy.Budget()
            ZipOutputStream(temp.outputStream().buffered()).use { zip ->
                zip.putNextEntry(ZipEntry("settings.json")); budget.add(metadata.size); zip.write(metadata); zip.closeEntry()
                files.toSortedMap().forEach { (name, file) ->
                    zip.putNextEntry(ZipEntry(name))
                    file.inputStream().use { BackupArchivePolicy.copy(it, zip, BackupArchivePolicy.limit(name), budget) }
                    zip.closeEntry()
                }
            }
            c.contentResolver.openOutputStream(uri, "wt")?.use { output -> temp.inputStream().use { it.copyTo(output) } } != null
        } catch (_: Exception) { false } finally { temp.delete() }
    }

    /** Returns null on success. ZIP v2 and existing JSON v1 backups are accepted. */
    @Synchronized fun import(c: Context, uri: Uri): String? {
        val stage = File(c.cacheDir, "backup-import-${UUID.randomUUID()}")
        var keepRecovery = false
        return try {
            check(stage.mkdirs())
            c.contentResolver.openInputStream(uri)?.use { raw ->
                BufferedInputStream(BackupArchivePolicy.LimitedInput(raw)).use { input ->
                    input.mark(4)
                    val first = input.read(); val second = input.read(); input.reset()
                    if (first == 'P'.code && second == 'K'.code) importZip(c, input, stage)
                    else importLegacy(c, input, stage)
                }
            } ?: return "Không đọc được file sao lưu"
            null
        } catch (e: Exception) {
            if (e is RecoveryRequiredException) {
                keepRecovery = true
                android.util.Log.e("Backup", "Restore recovery snapshots retained at ${stage.path}", e)
            }
            "Không nhập được bản sao lưu: ${e.message?.take(180) ?: "file không hợp lệ"}"
        } finally { if (!keepRecovery) stage.deleteRecursively() }
    }

    private fun importZip(c: Context, input: java.io.InputStream, stage: File) {
        val entries = linkedMapOf<String, File>()
        val budget = BackupArchivePolicy.Budget()
        ZipInputStream(input).use { zip ->
            while (true) {
                val entry = zip.nextEntry ?: break
                require(!entry.isDirectory && BackupArchivePolicy.accepts(entry.name)) { "Tên file không được phép" }
                require(entry.name !in entries && entries.size < BackupArchivePolicy.MAX_ENTRIES) { "Tên file trùng hoặc quá nhiều file" }
                val out = File(stage, entry.name)
                check(out.canonicalPath.startsWith(stage.canonicalPath + File.separator))
                check(out.parentFile!!.exists() || out.parentFile!!.mkdirs())
                out.outputStream().use { BackupArchivePolicy.copy(zip, it, BackupArchivePolicy.limit(entry.name), budget) }
                entries[entry.name] = out; zip.closeEntry()
            }
        }
        val metadata = entries.remove("settings.json") ?: error("Thiếu settings.json")
        val json = JSONObject(metadata.readText())
        require(json.getString("format") == FORMAT && json.getInt("version") == VERSION) { "Sai định dạng hoặc phiên bản sao lưu" }
        val declared = json.getJSONArray("files")
        require(declared.length() == entries.size) { "Danh mục file không khớp" }
        val names = (0 until declared.length()).map { declared.getString(it) }
        require(names.toSet().size == names.size && names.toSet() == entries.keys) { "Danh mục file không khớp" }
        entries.forEach { (name, file) -> validateFile(name, file) }
        val prefs = decodeSettings(json.getJSONObject("prefs"))
        val font = decodeAux(json.optJSONObject("fontPrefs") ?: JSONObject(), setOf("name"), emptySet())
        val radio = decodeAux(json.optJSONObject("radioPrefs") ?: JSONObject(), setOf("favorites"), setOf("last_fm", "last_am"))
        require("files/font_custom" in entries || font.isEmpty()) { "Thiếu file font riêng" }
        restore(c, stage, entries, prefs, font, radio, true)
    }

    private fun importLegacy(c: Context, input: java.io.InputStream, stage: File) {
        val bytes = ByteArrayOutputStream()
        BackupArchivePolicy.copy(input, bytes, BackupArchivePolicy.MAX_SETTINGS_BYTES, BackupArchivePolicy.Budget())
        val json = JSONObject(bytes.toString(Charsets.UTF_8.name()))
        require(json.getString("format") == FORMAT && json.getInt("version") == 1) { "Đây không phải bản sao lưu THT Launcher được hỗ trợ" }
        val prefs = decodeSettings(json.getJSONObject("prefs"))
        val entries = linkedMapOf<String, File>()
        for ((old, path) in listOf("home" to "config/home.json", "dock" to "config/shortcuts.json")) {
            val text = json.optString(old).takeIf { it.isNotBlank() } ?: continue
            val file = File(stage, path).apply { parentFile?.mkdirs(); writeText(text) }
            validateFile(path, file); entries[path] = file
        }
        restore(c, stage, entries, prefs, null, null, false)
    }

    /** Explicit managed-file list; app databases, caches and arbitrary paths are never exported. */
    private fun managedFiles(c: Context): LinkedHashMap<String, File> {
        val files = linkedMapOf<String, File>()
        fun add(name: String, file: File, parent: File) {
            if (!file.exists()) return
            require(file.isFile && file.canonicalFile.parentFile == parent.canonicalFile) { "Đường dẫn dữ liệu không hợp lệ" }
            require(BackupArchivePolicy.accepts(name)); files[name] = file
        }
        add("config/home.json", File(c.filesDir, "home.json"), c.filesDir)
        for (name in BackupArchivePolicy.configNames - "home.json") {
            val file = ShortcutStore.file(c, name); add("config/$name", file, file.parentFile!!)
        }
        c.filesDir.listFiles().orEmpty().filter { BackupArchivePolicy.accepts("files/${it.name}") }
            .forEach { add("files/${it.name}", it, c.filesDir) }
        val icons = File(c.filesDir, "iconpack")
        if (icons.exists()) require(icons.canonicalFile.parentFile == c.filesDir.canonicalFile)
        icons.listFiles().orEmpty().filter { BackupArchivePolicy.accepts("files/iconpack/${it.name}") }
            .forEach { add("files/iconpack/${it.name}", it, icons) }
        require(files.size < BackupArchivePolicy.MAX_ENTRIES) { "Quá nhiều file để sao lưu" }
        return files
    }

    private fun target(c: Context, path: String): File {
        require(BackupArchivePolicy.accepts(path) && path != "settings.json")
        return when {
            path == "config/home.json" -> File(c.filesDir, "home.json")
            path.startsWith("config/") -> ShortcutStore.file(c, path.removePrefix("config/"))
            else -> File(c.filesDir, path.removePrefix("files/"))
        }
    }

    private data class PreferenceChange(val store: SharedPreferences, val values: Map<String, Any>, val keys: Set<String>)
    private class RecoveryRequiredException(cause: Exception) : IOException("Chưa phục hồi trọn vẹn dữ liệu cũ; bản dự phòng được giữ lại", cause)

    private fun restore(c: Context, stage: File, entries: Map<String, File>, prefs: Map<String, Any>,
                        font: Map<String, Any>?, radio: Map<String, Any>?, complete: Boolean) {
        val current = managedFiles(c)
        val affected = if (complete) current.keys + entries.keys else entries.keys
        val oldFiles = linkedMapOf<String, File?>()
        val rollback = File(stage, "rollback").apply { check(mkdirs()) }
        val budget = BackupArchivePolicy.Budget()
        affected.forEachIndexed { i, path ->
            val file = target(c, path)
            if (file.exists()) {
                val copy = File(rollback, i.toString())
                file.inputStream().use { input -> copy.outputStream().use { BackupArchivePolicy.copy(input, it, BackupArchivePolicy.limit(path), budget) } }
                oldFiles[path] = copy
            } else oldFiles[path] = null
        }
        val settings = prefs + mapOf("font_changed" to System.currentTimeMillis(), "icons_changed" to System.currentTimeMillis())
        val changes = mutableListOf(PreferenceChange(Prefs(c).sp, settings, settingKeys + setOf("font_changed", "icons_changed")))
        if (font != null) changes += PreferenceChange(c.getSharedPreferences("fonts", 0), font, setOf("name"))
        if (radio != null) changes += PreferenceChange(c.getSharedPreferences("radio", 0), radio, setOf("favorites", "last_fm", "last_am"))
        val before = changes.associate { it.store to it.store.all.toMap() }
        try {
            affected.forEach { path ->
                val dst = target(c, path); val src = entries[path]
                if (src == null) { if (dst.exists()) check(dst.delete()) { "Không xóa được dữ liệu cũ" } }
                else replace(src, dst)
            }
            // Auxiliary font/radio state first; launcher preference listeners run after files are complete.
            changes.asReversed().forEach { check(writePreferences(it.store, it.values, it.keys)) { "Không lưu được cài đặt" } }
        } catch (e: Exception) {
            var rolledBack = true
            oldFiles.forEach { (path, file) ->
                runCatching { val dst = target(c, path); if (file == null) { if (dst.exists()) check(dst.delete()) } else replace(file, dst) }
                    .onFailure { rolledBack = false }
            }
            before.forEach { (store, old) ->
                val values = old.filterValues { it != null }.mapValues { it.value!! }
                if (!writePreferences(store, values, store.all.keys + old.keys)) rolledBack = false
            }
            if (!rolledBack) throw RecoveryRequiredException(e)
            throw e
        }
    }

    /** Same-directory temp plus rename avoids a half-written destination, including external files. */
    private fun replace(src: File, dst: File) {
        check(dst.parentFile!!.exists() || dst.parentFile!!.mkdirs())
        val temp = File(dst.parentFile, ".${dst.name}.restore-${UUID.randomUUID()}")
        try {
            FileOutputStream(temp).use { out -> src.inputStream().use { it.copyTo(out) }; out.fd.sync() }
            check(temp.renameTo(dst)) { "Không thay được ${dst.name}" }
        } finally { temp.delete() }
    }

    private fun encode(values: Map<String, *>): JSONObject = JSONObject().apply {
        values.forEach { (key, value) ->
            val type = when (value) { is Boolean -> "b"; is Int -> "i"; is Long -> "l"; is Float -> "f"; else -> "s" }
            put(key, JSONObject().put("t", type).put("v", value))
        }
    }

    private fun decodeSettings(json: JSONObject): Map<String, Any> {
        require(json.length() <= 256) { "Quá nhiều cài đặt" }
        val result = linkedMapOf<String, Any>()
        json.keys().forEach { key ->
            if (key !in settingKeys) return@forEach // Existing JSON v1 backups also held transient keys.
            val row = json.getJSONObject(key); val raw = row.get("v"); val type = row.getString("t")
            val value: Any = when (key) {
                in booleans -> { require(type == "b" && raw is Boolean); raw }
                in integers -> {
                    require(type == "i" && raw is Number && raw.toDouble().isFinite() && raw.toLong().toDouble() == raw.toDouble() && raw.toLong() in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong())
                    raw.toInt()
                }
                in floats -> { require(type == "f" && raw is Number && raw.toFloat().isFinite()); raw.toFloat() }
                in longs -> { require(type == "l" && raw is Number && raw.toDouble().isFinite() && raw.toLong() >= 0); raw.toLong() }
                else -> { require(type == "s" && raw is String && raw.length <= 65536 && '\u0000' !in raw); raw }
            }
            validatePreference(key, value); result[key] = value
        }
        return result
    }

    private fun validatePreference(key: String, value: Any) {
        if (value is Int) {
            val range = when (key) {
                "card_opacity", "dock_opacity", "saver_dim" -> 0..100
                "ui_scale" -> 75..150
                "tank_l" -> 0..300
                "auto_heat_drv", "auto_heat_pas", "auto_heat_wheel", "auto_vent_drv", "auto_vent_pas" -> 0..3
                "auto_heat_below" -> -30..40
                "auto_vent_above", "auto_air_above" -> -10..60
                "auto_air_fan" -> 0..7
                "auto_climate_temp" -> 16..32
                "auto_roof_speed" -> 0..120
                "auto_heat_off", "auto_vent_off" -> 0..120
                "auto_air_min" -> 1..30
                "autostart_delay" -> 0..120
                "nav_bar_overscan" -> 0..300
                "service_km" -> 0..2_000_000
                else -> 0..1440
            }
            require(value in range) { "Cài đặt $key ngoài giới hạn" }
        }
        if (key == "splits") {
            val list = JSONArray(value as String); require(list.length() <= 100)
            val ids = hashSetOf<String>()
            for (i in 0 until list.length()) {
                val row = list.getJSONObject(i); require(simpleId(row.getString("id")) && ids.add(row.getString("id")))
                require(row.getString("left").length in 1..512 && row.getString("right").length in 1..512)
                require(row.optInt("ratio", 50) in 20..80)
            }
        }
    }

    private fun decodeAux(json: JSONObject, strings: Set<String>, integers: Set<String>): Map<String, Any> {
        require(json.length() <= strings.size + integers.size)
        return json.keys().asSequence().associateWith { key ->
            val row = json.getJSONObject(key); val value = row.get("v")
            when (key) {
                in strings -> { require(row.getString("t") == "s" && value is String && value.length <= 4096 && '\u0000' !in value); value }
                in integers -> {
                    require(row.getString("t") == "i" && value is Number && value.toDouble().isFinite() && value.toLong().toDouble() == value.toDouble() && value.toLong() in 0L..200000L)
                    value.toInt()
                }
                else -> error("Cài đặt phụ không hợp lệ")
            }
        }
    }

    private fun writePreferences(store: SharedPreferences, values: Map<String, Any>, replaceKeys: Set<String>): Boolean {
        val edit = store.edit(); replaceKeys.forEach { edit.remove(it) }
        values.forEach { (key, value) ->
            when (value) {
                is Boolean -> edit.putBoolean(key, value)
                is Int -> edit.putInt(key, value)
                is Long -> edit.putLong(key, value)
                is Float -> edit.putFloat(key, value)
                is String -> edit.putString(key, value)
                is Set<*> -> edit.putStringSet(key, value.filterIsInstance<String>().toSet())
            }
        }
        return edit.commit()
    }

    private fun validateFile(path: String, file: File) {
        if (path.startsWith("config/")) { validateConfig(path, JSONObject(file.readText())); return }
        if (path.endsWith("/manifest.json")) {
            val json = JSONObject(file.readText())
            require(json.optString("name").length <= 120 && json.optString("author").length <= 240)
            require(json.optString("mode", "mono") in setOf("mono", "color")); return
        }
        if (path == "files/font_custom") {
            require(file.length() in 1..BackupArchivePolicy.MAX_FONT_BYTES)
            val face = Typeface.createFromFile(file); val paint = Paint().apply { typeface = face }
            require("ăâđêôơưạảấầẩẫậắằẳẵặẹẻẽếềểễệỉịọỏốồổỗộớờởỡợụủứừửữựỳỵỷỹĐ".all { paint.hasGlyph(it.toString()) }) { "Font thiếu chữ tiếng Việt" }
            return
        }
        val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }; BitmapFactory.decodeFile(file.path, bounds)
        val maximum = if (path.startsWith("files/iconpack/")) 1024 else 8192
        require(bounds.outWidth in 1..maximum && bounds.outHeight in 1..maximum && bounds.outWidth.toLong() * bounds.outHeight <= 24_000_000L) { "Ảnh không hợp lệ hoặc quá lớn" }
    }

    private fun simpleId(value: String) = value.matches(Regex("[A-Za-z0-9_-]{1,80}"))

    private fun validateConfig(path: String, json: JSONObject) {
        require(json.optInt("version", 1) in 1..32)
        if (path == "config/home.json") {
            val list = json.getJSONArray("widgets"); require(list.length() <= 100)
            val ids = hashSetOf<String>(); val types = WidgetType.values().map { it.name }.toSet()
            for (i in 0 until list.length()) {
                val row = list.getJSONObject(i); val id = row.getString("id")
                require(simpleId(id) && ids.add(id) && row.getString("type") in types)
                val x = row.getInt("x"); val y = row.getInt("y"); val w = row.getInt("w"); val h = row.getInt("h")
                require(x in 0..1920 && y in 0..720 && w in 20..1920 && h in 20..720 && x.toLong()+w <= 1920 && y.toLong()+h <= 720) { "Bố cục widget ngoài khung màn hình" }
                require(row.optString("pkg").length <= 4096)
            }
        } else {
            val list = json.getJSONArray("shortcuts"); require(list.length() <= ShortcutStore.MAX)
            val ids = hashSetOf<String>()
            for (i in 0 until list.length()) {
                val row = list.getJSONObject(i); val id = row.getString("id")
                require(simpleId(id) && ids.add(id) && row.getString("type") in shortcutTypes)
                for (key in listOf("label", "package", "activity", "action", "service")) require(row.optString(key).length <= 2048 && '\u0000' !in row.optString(key))
            }
        }
    }
}
