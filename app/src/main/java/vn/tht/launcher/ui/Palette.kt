package vn.tht.launcher.ui

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import vn.tht.launcher.settings.Prefs

/**
 * Bảng màu hiện tại (ngày/đêm + màu nhấn + độ trong suốt). Mọi view dựng bằng code lấy màu ở đây;
 * gọi [apply] khi đổi cài đặt hoặc đổi ngày/đêm rồi dựng lại giao diện.
 */
object Palette {
    var night = true; private set
    var bg = 0; private set
    var card = 0; private set
    var text = 0; private set
    var textDim = 0; private set
    var accent = 0; private set
    var button = 0; private set
    var dock = 0; private set
    var scrim = 0; private set
    const val danger = 0xFFE53935.toInt()
    const val ok = 0xFF43A047.toInt()

    /** Màu nhấn chọn được. */
    val ACCENTS = linkedMapOf(
        "blue" to ("Xanh dương" to 0xFF2F80ED.toInt()),
        "teal" to ("Xanh ngọc" to 0xFF14B8A6.toInt()),
        "green" to ("Xanh lá" to 0xFF22C55E.toInt()),
        "orange" to ("Cam" to 0xFFF97316.toInt()),
        "red" to ("Đỏ" to 0xFFEF4444.toInt()),
        "purple" to ("Tím" to 0xFF8B5CF6.toInt()),
    )

    fun apply(prefs: Prefs, isNight: Boolean) {
        night = isNight
        val cardA = (prefs.cardOpacity.coerceIn(0, 100) * 255 / 100)
        val dockA = (prefs.dockOpacity.coerceIn(0, 100) * 255 / 100)
        accent = ACCENTS[prefs.accent]?.second ?: ACCENTS.getValue("blue").second
        if (isNight) {
            bg = 0xFF0E1116.toInt()
            card = withAlpha(0xFF1A1F27.toInt(), cardA)
            text = 0xFFF2F4F7.toInt()
            textDim = 0xFF8B95A5.toInt()
            button = withAlpha(0xFF2A313C.toInt(), maxOf(cardA, 160))
            dock = withAlpha(0xFF12161C.toInt(), dockA)
            scrim = 0xFA0E1116.toInt()
        } else {
            bg = 0xFFE9EEF4.toInt()
            card = withAlpha(0xFFFFFFFF.toInt(), cardA)
            text = 0xFF111827.toInt()
            textDim = 0xFF5B6472.toInt()
            button = withAlpha(0xFFE2E8F0.toInt(), maxOf(cardA, 160))
            dock = withAlpha(0xFFF8FAFC.toInt(), dockA)
            scrim = 0xFAE9EEF4.toInt()
        }
    }

    fun withAlpha(c: Int, a: Int) = Color.argb(a, Color.red(c), Color.green(c), Color.blue(c))

    /** Nền dựng sẵn: tên hiển thị + các màu gradient (vẽ bằng code, không dùng ảnh ngoài). */
    val BACKGROUNDS = linkedMapOf(
        "mountains_dusk" to ("Núi hoàng hôn" to intArrayOf()),
        "mountains_night" to ("Núi đêm" to intArrayOf()),
        "mountains_day" to ("Núi ban ngày" to intArrayOf()),
        "midnight" to ("Nửa đêm" to intArrayOf(0xFF05070D.toInt(), 0xFF111827.toInt(), 0xFF1E293B.toInt())),
        "graphite" to ("Graphite" to intArrayOf(0xFF111111.toInt(), 0xFF1F1F1F.toInt(), 0xFF2B2B2B.toInt())),
        "evening_city" to ("Phố đêm" to intArrayOf(0xFF0F172A.toInt(), 0xFF312E81.toInt(), 0xFF7C2D12.toInt())),
        "northern_lights" to ("Cực quang" to intArrayOf(0xFF021024.toInt(), 0xFF064E3B.toInt(), 0xFF10B981.toInt())),
        "ocean" to ("Đại dương" to intArrayOf(0xFF03132B.toInt(), 0xFF0C4A6E.toInt(), 0xFF0891B2.toInt())),
        "sunset" to ("Hoàng hôn" to intArrayOf(0xFF1E1B4B.toInt(), 0xFF9D174D.toInt(), 0xFFF59E0B.toInt())),
        "blue_horizon" to ("Chân trời" to intArrayOf(0xFFDBEAFE.toInt(), 0xFFBFDBFE.toInt(), 0xFF93C5FD.toInt())),
        "fresh_breeze" to ("Gió mát" to intArrayOf(0xFFECFDF5.toInt(), 0xFFD1FAE5.toInt(), 0xFFA7F3D0.toInt())),
        "warm_day" to ("Ngày ấm" to intArrayOf(0xFFFFF7ED.toInt(), 0xFFFFEDD5.toInt(), 0xFFFED7AA.toInt())),
        "plain" to ("Màu trơn" to intArrayOf()),
    )

    fun background(key: String): Drawable {
        when (key) {
            "mountains_dusk" -> return LandscapeDrawable.dusk()
            "mountains_night" -> return LandscapeDrawable.night()
            "mountains_day" -> return LandscapeDrawable.day()
        }
        val colors = BACKGROUNDS[key]?.second
        return if (colors == null || colors.isEmpty()) GradientDrawable().apply { setColor(bg) }
        else GradientDrawable(GradientDrawable.Orientation.TL_BR, colors)
    }

    /** Ngày/đêm hệ thống (khi không đọc được đèn xe). */
    fun systemNight(c: Context): Boolean =
        (c.resources.configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK) ==
            android.content.res.Configuration.UI_MODE_NIGHT_YES
}
