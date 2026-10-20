package vn.tht.launcher.system

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.util.Log
import org.json.JSONObject
import vn.tht.launcher.settings.Prefs
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

/** Thời tiết hiện tại. [icon] là tên icon (weather-sunny, weather-rain…). */
data class WeatherNow(val tempC: Float, val code: Int, val text: String, val icon: String, val place: String?, val at: Long)

/**
 * Thời tiết từ Open-Meteo (miễn phí, không cần khoá API). Vị trí: thành phố người dùng chọn trong
 * Cài đặt, hoặc vị trí gần nhất của máy nếu đã cấp quyền. Làm mới tối đa mỗi 30 phút; mọi lỗi mạng
 * chỉ làm ô thời tiết trống, không ảnh hưởng launcher.
 */
object Weather {
    private const val TAG = "Weather"
    private const val TTL_MS = 30 * 60_000L

    @Volatile var current: WeatherNow? = null
        private set
    @Volatile private var loading = false

    /** Gọi định kỳ (ví dụ mỗi phút); chỉ tải khi dữ liệu cũ hơn 30 phút. */
    fun refreshIfStale(c: Context, onDone: () -> Unit) {
        val cur = current ?: loadCache(c)?.also { current = it }
        if (loading || (cur != null && System.currentTimeMillis() - cur.at < TTL_MS)) return
        val loc = location(c) ?: return
        loading = true
        Thread {
            try {
                val url = "https://api.open-meteo.com/v1/forecast?latitude=${loc.first}&longitude=${loc.second}" +
                    "&current=temperature_2m,weather_code,is_day&timezone=auto"
                val j = JSONObject(get(url)).getJSONObject("current")
                val code = j.getInt("weather_code")
                val day = j.optInt("is_day", 1) == 1
                val w = WeatherNow(j.getDouble("temperature_2m").toFloat(), code, text(code), icon(code, day),
                    Prefs(c).sp.getString("weather_place", null), System.currentTimeMillis())
                current = w
                saveCache(c, w)
                onDone()
            } catch (t: Throwable) {
                Log.w(TAG, "Không tải được thời tiết: $t")
            } finally {
                loading = false
            }
        }.start()
    }

    /** Tìm thành phố theo tên; trả (tên hiển thị, vĩ độ, kinh độ) hoặc null. Chạy trên luồng nền. */
    fun geocode(name: String): Triple<String, Double, Double>? = runCatching {
        val q = URLEncoder.encode(name, "UTF-8")
        val r = JSONObject(get("https://geocoding-api.open-meteo.com/v1/search?name=$q&count=1&language=vi"))
            .getJSONArray("results").getJSONObject(0)
        Triple(listOfNotNull(r.optString("name"), r.optString("country").ifBlank { null }).joinToString(", "),
            r.getDouble("latitude"), r.getDouble("longitude"))
    }.getOrNull()

    fun setPlace(c: Context, name: String, lat: Double, lon: Double) {
        Prefs(c).sp.edit().putString("weather_place", name)
            .putFloat("weather_lat", lat.toFloat()).putFloat("weather_lon", lon.toFloat()).apply()
        current = null
        Prefs(c).sp.edit().remove("weather_cache").apply()
    }

    @SuppressLint("MissingPermission")
    private fun location(c: Context): Pair<Float, Float>? {
        val sp = Prefs(c).sp
        if (sp.contains("weather_lat")) return sp.getFloat("weather_lat", 0f) to sp.getFloat("weather_lon", 0f)
        if (c.checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) return null
        val lm = c.getSystemService(LocationManager::class.java) ?: return null
        val l = runCatching {
            lm.getProviders(true).mapNotNull { lm.getLastKnownLocation(it) }.maxByOrNull { it.time }
        }.getOrNull() ?: return null
        return l.latitude.toFloat() to l.longitude.toFloat()
    }

    private fun get(url: String): String {
        val conn = URL(url).openConnection() as HttpURLConnection
        conn.connectTimeout = 8000
        conn.readTimeout = 8000
        return conn.inputStream.use { it.readBytes().toString(Charsets.UTF_8) }.also { conn.disconnect() }
    }

    private fun saveCache(c: Context, w: WeatherNow) {
        Prefs(c).sp.edit().putString("weather_cache", JSONObject().put("t", w.tempC.toDouble()).put("c", w.code)
            .put("p", w.place ?: "").put("at", w.at).put("d", !w.icon.startsWith("weather-night")).toString()).apply()
    }

    private fun loadCache(c: Context): WeatherNow? = runCatching {
        val j = JSONObject(Prefs(c).sp.getString("weather_cache", null)!!)
        val code = j.getInt("c")
        WeatherNow(j.getDouble("t").toFloat(), code, text(code), icon(code, j.optBoolean("d", true)), j.optString("p").ifBlank { null }, j.getLong("at"))
    }.getOrNull()

    /** Mã thời tiết WMO -> chữ tiếng Việt. */
    private fun text(code: Int) = when (code) {
        0 -> "Trời quang"
        1 -> "Ít mây"
        2 -> "Có mây"
        3 -> "Nhiều mây"
        45, 48 -> "Sương mù"
        51, 53, 55, 56, 57 -> "Mưa phùn"
        61, 63, 66, 80, 81 -> "Mưa"
        65, 67, 82 -> "Mưa to"
        71, 73, 75, 77, 85, 86 -> "Tuyết"
        95, 96, 99 -> "Dông"
        else -> ""
    }

    private fun icon(code: Int, day: Boolean) = when (code) {
        0 -> if (day) "sun" else "weather-night"
        1, 2 -> if (day) "weather-partly-cloudy" else "weather-night-cloudy"
        3 -> "weather-cloudy"
        45, 48 -> "weather-fog"
        in 51..67, 80, 81, 82 -> "weather-rain"
        in 71..77, 85, 86 -> "weather-snow"
        95, 96, 99 -> "weather-lightning"
        else -> "weather-cloudy"
    }
}
