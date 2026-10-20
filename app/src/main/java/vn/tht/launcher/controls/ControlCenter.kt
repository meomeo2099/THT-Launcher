package vn.tht.launcher.controls

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import vn.tht.launcher.ui.Icons
import vn.tht.launcher.ui.Palette
import vn.tht.launcher.ui.Ui
import vn.tht.launcher.vehicle.EcarxCarSource
import vn.tht.launcher.vehicle.EcarxController
import vn.tht.launcher.vehicle.EcarxController.Safety
import java.util.Locale
import vn.tht.launcher.vehicle.EcarxFunctions as F

/**
 * Trung tâm điều khiển toàn màn hình: Điều hoà · Ghế & sưởi · Xe · Đèn & màn hình.
 * Chỉ hiện ô mà xe báo hỗ trợ; trạng thái đọc lại mỗi [REFRESH_MS].
 */
class ControlCenter(
    private val context: Context,
    private val ecarx: EcarxCarSource,
    private val controller: EcarxController,
    private val startTab: Int = 0,
) {
    /** Một ô điều khiển. [reads]: các (id, zone) cần đọc để vẽ trạng thái. */
    private abstract inner class Tile(val id: Int, val zone: Int) {
        abstract val view: View
        open val reads: List<Pair<Int, Int>> get() = listOf(id to zone)
        abstract fun bind(v: Values)
    }

    /** Giá trị đọc được trong một lượt làm mới. */
    private class Values(val ints: Map<Pair<Int, Int>, Int?>, val floats: Map<Pair<Int, Int>, Float?>) {
        fun int(id: Int, zone: Int = F.ZONE_ALL) = ints[id to zone]
        fun float(id: Int, zone: Int = F.ZONE_ALL) = floats[id to zone]
    }

    private val c = context
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var dialog: Dialog
    private lateinit var body: LinearLayout
    private lateinit var status: TextView
    private val tabs = mutableListOf<Button>()
    private var tiles = listOf<Tile>()
    private var floatReads = setOf<Pair<Int, Int>>()
    private var showing = false

    private val refresh = object : Runnable {
        override fun run() {
            if (!showing) return
            val ts = tiles
            controller.background({ read(ts) }) { v -> if (v != null && ts === tiles) ts.forEach { it.bind(v) } }
            handler.postDelayed(this, REFRESH_MS)
        }
    }

    fun show() {
        val root = Ui.vbox(c, 24).apply { setBackgroundColor(Palette.withAlpha(Palette.bg, 250)) }
        root.addView(Ui.hbox(c).apply {
            addView(Ui.text(c, "Điều khiển xe", 28f, bold = true), Ui.weight())
            TAB_NAMES.forEachIndexed { i, name ->
                val b = Ui.button(c, name) { select(i) }
                tabs += b
                addView(b, Ui.wrap(8, c))
            }
            addView(Ui.button(c, "Đóng") { dialog.dismiss() })
        })
        status = Ui.text(c, if (controller.canWrite) "" else "Chỉ đọc: chưa xác minh APK ký bằng khoá platform của đầu xe", 16f, dim = true)
        root.addView(status.apply { setPadding(0, Ui.dp(c, 8), 0, Ui.dp(c, 8)) })
        body = Ui.vbox(c)
        root.addView(ScrollView(c).apply { addView(body) }, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f))

        dialog = Dialog(c, android.R.style.Theme_Black_NoTitleBar_Fullscreen).apply {
            setContentView(root)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
            setOnDismissListener { showing = false; handler.removeCallbacks(refresh) }
        }
        dialog.show()
        showing = true
        select(startTab)
    }

    private fun select(tab: Int) {
        tabs.forEachIndexed { i, b -> Ui.setActive(b, i == tab) }
        body.removeAllViews()
        body.addView(Ui.text(c, "Đang dò chức năng xe hỗ trợ…", 18f, dim = true))
        tiles = emptyList()
        val candidates = when (tab) {
            0 -> climateTiles()
            1 -> seatTiles()
            2 -> vehicleTiles()
            else -> lightTiles()
        }
        // Kiểm tra hỗ trợ trên luồng nền rồi mới dựng
        controller.background({ candidates.filter { t -> t.reads.take(1).all { controller.supported(it.first, it.second) } } }) { ok ->
            if (!showing) return@background
            body.removeAllViews()
            var list = ok.orEmpty()
            // Xe có nhiệt độ 2 vùng thì bỏ ô nhiệt độ chung
            if (list.any { it is StepperTile && it.id == F.HVAC_TEMP && it.zone == F.ZONE_ROW_1_LEFT }) {
                list = list.filterNot { it is StepperTile && it.id == F.HVAC_TEMP && it.zone == F.ZONE_ALL }
            }
            tiles = list
            if (list.isEmpty()) {
                body.addView(Ui.text(c, if (ecarx.connected) "Xe không báo hỗ trợ chức năng nào trong mục này." else "Chưa nối được ECARX AdaptAPI.", 18f, dim = true))
                return@background
            }
            layoutTiles(list)
            handler.removeCallbacks(refresh)
            handler.post(refresh)
        }
    }

    /** Ô nhỏ xếp lưới 4 cột (hàng cuối lấp chỗ trống), ô rộng (chọn / −+) mỗi ô một hàng ở dưới. */
    private fun layoutTiles(list: List<Tile>) {
        fun newRow() = Ui.hbox(c).apply { setPadding(0, 0, 0, Ui.dp(c, 12)) }.also {
            body.addView(it, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
        }
        val (wide, small) = list.partition { it is ChoiceTile || it is StepperTile }
        small.chunked(4).forEach { chunk ->
            val row = newRow()
            chunk.forEach { row.addView(it.view, LinearLayout.LayoutParams(0, Ui.dp(c, 130), 1f).apply { marginEnd = Ui.dp(c, 12) }) }
            repeat(4 - chunk.size) { row.addView(View(c), LinearLayout.LayoutParams(0, 0, 1f).apply { marginEnd = Ui.dp(c, 12) }) }
        }
        wide.forEach { t ->
            newRow().addView(t.view, LinearLayout.LayoutParams(0, Ui.dp(c, 100), 1f).apply { marginEnd = Ui.dp(c, 12) })
        }
    }

    private fun read(ts: List<Tile>): Values {
        val keys = ts.flatMap { it.reads }.toSet()
        return Values(
            ints = keys.filter { it !in floatReads }.associateWith { ecarx.getInt(it.first, it.second) },
            floats = keys.filter { it in floatReads }.associateWith { ecarx.getFloat(it.first, it.second) },
        )
    }

    private fun report(r: EcarxController.Result) {
        Toast.makeText(c, (if (r.ok) "✔ " else "✖ ") + r.message, Toast.LENGTH_SHORT).show()
        handler.post(refresh)
    }

    // ---- Các loại ô ----

    private fun tileBox(label: String, icon: String?): Triple<LinearLayout, ImageView, TextView> {
        val img = ImageView(c)
        val state = Ui.text(c, "", 15f, dim = true).apply { gravity = Gravity.CENTER }
        val box = Ui.vbox(c, 14, Gravity.CENTER).apply {
            background = Ui.card(c, 18)
            if (icon != null) Icons.get(c, icon)?.let { d ->
                img.setImageDrawable(d)
                addView(img, LinearLayout.LayoutParams(Ui.dp(c, 48), Ui.dp(c, 48)))
            }
            addView(Ui.text(c, label, 18f).apply { gravity = Gravity.CENTER; maxLines = 1 })
            addView(state, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
        }
        return Triple(box, img, state)
    }

    /** Nhãn của ô rộng: icon + chữ. */
    private fun wideLabel(label: String, icon: String?): View = Ui.hbox(c).apply {
        if (icon != null) Icons.get(c, icon)?.let { d ->
            addView(ImageView(c).apply { setImageDrawable(d) }, LinearLayout.LayoutParams(Ui.dp(c, 32), Ui.dp(c, 32)).apply { marginEnd = Ui.dp(c, 12) })
        }
        addView(Ui.text(c, label, 20f))
    }

    private fun setOn(box: View, img: ImageView, state: TextView, on: Boolean) {
        box.background = Ui.pill(c, if (on) Palette.accent else Palette.card, 18)
        img.drawable?.setTint(if (on) Color.WHITE else Palette.text)
        state.setTextColor(if (on) Color.argb(220, 255, 255, 255) else Palette.textDim)
    }

    private inner class ToggleTile(id: Int, zone: Int, val label: String, icon: String?, val safety: Safety = Safety.SAFE) : Tile(id, zone) {
        private val parts = tileBox(label, icon)
        override val view = parts.first.apply { setOnClickListener { controller.toggleFn(id, zone, label, safety, ::report) } }
        override fun bind(v: Values) {
            val on = v.int(id, zone) == F.ON
            setOn(parts.first, parts.second, parts.third, on)
            parts.third.text = when (v.int(id, zone)) { null -> "–"; F.ON -> "Bật"; else -> "Tắt" }
        }
    }

    private inner class LevelTile(id: Int, zone: Int, val label: String, icon: String?, val max: Int = 3) : Tile(id, zone) {
        private val parts = tileBox(label, icon)
        override val view = parts.first.apply { setOnClickListener { controller.cycleLevel(id, zone, max, label, ::report) } }
        override fun bind(v: Values) {
            val lvl = F.levelOf(id, v.int(id, zone))
            setOn(parts.first, parts.second, parts.third, (lvl ?: 0) > 0)
            parts.third.text = when (lvl) { null -> "–"; 0 -> "Tắt"; else -> "Mức $lvl/$max" }
        }
    }

    /** Ô rộng chọn một trong nhiều giá trị `id + n` (hoặc giá trị tuyệt đối nếu [absolute]). */
    private inner class ChoiceTile(
        id: Int, zone: Int, val label: String, val options: List<Pair<Int, String>>,
        val safety: Safety = Safety.SAFE, val absolute: Boolean = false, val icon: String? = null,
    ) : Tile(id, zone) {
        private val buttons = options.map { (n, name) ->
            n to Ui.button(c, name) {
                val value = if (absolute) n else F.level(id, n)
                controller.setValue(id, zone, value, "$label: $name", safety, ::report)
            }
        }
        override val view: View = Ui.hbox(c).apply {
            background = Ui.card(c, 18)
            setPadding(Ui.dp(c, 20), 0, Ui.dp(c, 12), 0)
            addView(wideLabel(label, icon), LinearLayout.LayoutParams(Ui.dp(c, 260), ViewGroup.LayoutParams.WRAP_CONTENT))
            buttons.forEach { addView(it.second, Ui.weight().apply { marginEnd = Ui.dp(c, 8) }) }
        }
        override fun bind(v: Values) {
            val raw = v.int(id, zone)
            val cur = if (absolute) raw else F.levelOf(id, raw)
            buttons.forEach { (n, b) -> Ui.setActive(b, n == cur) }
        }
    }

    /** Ô rộng có nút −/+ và giá trị ở giữa. */
    private inner class StepperTile(
        id: Int, zone: Int, val label: String, val isFloat: Boolean,
        val format: (Values) -> String, val icon: String? = null, val step: (Int) -> Unit,
    ) : Tile(id, zone) {
        init { if (isFloat) floatReads = floatReads + (id to zone) }
        private val value = Ui.text(c, "–", 30f).apply { gravity = Gravity.CENTER }
        override val view: View = Ui.hbox(c).apply {
            background = Ui.card(c, 18)
            setPadding(Ui.dp(c, 20), 0, Ui.dp(c, 12), 0)
            addView(wideLabel(label, icon), LinearLayout.LayoutParams(Ui.dp(c, 260), ViewGroup.LayoutParams.WRAP_CONTENT))
            addView(Ui.button(c, "−", 28f) { step(-1) }, LinearLayout.LayoutParams(Ui.dp(c, 140), Ui.dp(c, 72)))
            addView(value, Ui.weight())
            addView(Ui.button(c, "+", 28f) { step(+1) }, LinearLayout.LayoutParams(Ui.dp(c, 140), Ui.dp(c, 72)))
        }
        override fun bind(v: Values) { value.text = format(v) }
    }

    // ---- Danh sách ô theo tab (chỉ ô xe hỗ trợ mới hiện) ----

    private fun tempLabel(v: Values, zone: Int) =
        v.float(F.HVAC_TEMP, zone)?.let { String.format(Locale.US, "%.1f°C", it) } ?: "–"

    private fun climateTiles(): List<Tile> = listOf(
        ToggleTile(F.HVAC_POWER, F.ZONE_ALL, "Điều hoà", "power"),
        ToggleTile(F.HVAC_AC, F.ZONE_ALL, "A/C", "ac"),
        ToggleTile(F.HVAC_AUTO, F.ZONE_ALL, "AUTO", "auto"),
        ToggleTile(F.HVAC_AC_MAX, F.ZONE_ALL, "Lạnh tối đa", "climate"),
        StepperTile(F.HVAC_TEMP, F.ZONE_ROW_1_LEFT, "Nhiệt độ bên lái", true,
            { tempLabel(it, F.ZONE_ROW_1_LEFT) }, "thermometer") { d -> controller.tempStepZone(F.ZONE_ROW_1_LEFT, d, ::report) },
        StepperTile(F.HVAC_TEMP, F.ZONE_ROW_1_RIGHT, "Nhiệt độ bên phụ", true,
            { tempLabel(it, F.ZONE_ROW_1_RIGHT) }, "thermometer") { d -> controller.tempStepZone(F.ZONE_ROW_1_RIGHT, d, ::report) },
        StepperTile(F.HVAC_TEMP, F.ZONE_ALL, "Nhiệt độ", true,
            { tempLabel(it, F.ZONE_ALL) }, "thermometer") { d -> controller.tempStepZone(F.ZONE_ALL, d, ::report) },
        ToggleTile(F.HVAC_TEMP_DUAL, F.ZONE_ALL, "Tách 2 vùng", "dual"),
        StepperTile(F.HVAC_FAN_SPEED, F.ZONE_ALL, "Quạt", false,
            { v -> F.levelOf(F.HVAC_FAN_SPEED, v.int(F.HVAC_FAN_SPEED))?.let { if (it == 0) "Tắt" else "Mức $it/7" } ?: "–" }, "fan",
        ) { d -> controller.fanStep(d, ::report) },
        ChoiceTile(F.HVAC_CIRCULATION, F.ZONE_ALL, "Lấy gió", F.CIRCULATION_MODES, icon = "recirc"),
        ChoiceTile(F.HVAC_BLOWING_MODE, F.ZONE_ALL, "Hướng gió", F.BLOWING_MODES, icon = "air-both"),
        ToggleTile(F.HVAC_DEFROST_FRONT, F.ZONE_ALL, "Sấy kính trước", "front-defrost"),
        ToggleTile(F.HVAC_DEFROST_FRONT_MAX, F.ZONE_ALL, "Sấy kính tối đa", "front-defrost"),
        ToggleTile(F.HVAC_DEFROST_REAR, F.ZONE_ALL, "Sấy kính sau", "rear-defrost"),
        ToggleTile(F.HVAC_ECO, F.ZONE_ALL, "Điều hoà ECO", "eco"),
        ToggleTile(F.HVAC_IONS, F.ZONE_ALL, "Ion", "ion"),
        ToggleTile(F.HVAC_G_CLEAN, F.ZONE_ALL, "Làm sạch khí", "air-clean"),
        ToggleTile(F.HVAC_AUTO_DRY, F.ZONE_ALL, "Tự sấy dàn lạnh", "dry"),
        ToggleTile(F.HVAC_PM25, F.ZONE_ALL, "Lọc PM2.5", "pm25"),
        ToggleTile(F.HVAC_FRAGRANCE, F.ZONE_ALL, "Nước hoa", "fragrance"),
        LevelTile(F.HVAC_FRAGRANCE_LEVEL, F.ZONE_ALL, "Độ đậm nước hoa", "fragrance"),
    )

    private fun seatTiles(): List<Tile> = listOf(
        LevelTile(F.HVAC_SEAT_HEATING, F.ZONE_ROW_1_LEFT, "Sưởi ghế lái", "seat-heat-left"),
        LevelTile(F.HVAC_SEAT_HEATING, F.ZONE_ROW_1_RIGHT, "Sưởi ghế phụ", "seat-heat-right"),
        LevelTile(F.HVAC_SEAT_VENTILATION, F.ZONE_ROW_1_LEFT, "Làm mát ghế lái", "seat-vent-left"),
        LevelTile(F.HVAC_SEAT_VENTILATION, F.ZONE_ROW_1_RIGHT, "Làm mát ghế phụ", "seat-vent-right"),
        LevelTile(F.HVAC_STEERING_WHEEL_HEAT, F.ZONE_ALL, "Sưởi vô lăng", "wheel-heat"),
    )

    private fun vehicleTiles(): List<Tile> = listOf(
        ChoiceTile(F.DRIVE_MODE, F.ZONE_ALL, "Chế độ lái", F.DRIVE_MODES, Safety.PARKED, icon = "sport"),
        ChoiceTile(F.BCM_WINDOW, F.ZONE_ROOF, "Cửa sổ trời", listOf(F.ON to "Mở", F.OFF to "Đóng"), Safety.BELOW_80, absolute = true, icon = "sunroof-open"),
        ToggleTile(F.BCM_SUNROOF_TILT, F.ZONE_ROOF, "Hé cửa sổ trời", "roof-tilt"),
        ToggleTile(F.SUN_CURTAIN_CLOSE, F.ZONE_ALL, "Rèm che nắng (đóng)", "sun-curtain"),
        ChoiceTile(F.BCM_WINDOW, F.WINDOW_ALL, "Tất cả kính",
            listOf(F.ON to "Mở", F.BCM_WINDOW + 2 to "Mở nửa", F.OFF to "Đóng"), Safety.BELOW_80, absolute = true, icon = "windows"),
        ToggleTile(F.WINDOW_VENTILATE, F.ZONE_ALL, "Hé kính thông gió", "window-vent"),
        ToggleTile(F.BCM_FOLD_MIRROR, F.ZONE_ALL, "Gập gương", "mirrors", Safety.PARKED),
        ChoiceTile(F.BCM_DOOR, F.ZONE_DOOR_REAR, "Cốp sau (chỉ số P)", listOf(F.ON to "Mở cốp"), Safety.PARKED_IN_P, absolute = true, icon = "trunk"),
    )

    private fun lightTiles(): List<Tile> = listOf(
        ToggleTile(F.AMBIENT_SWITCH, F.ZONE_ALL, "Đèn viền", "ambient"),
        ChoiceTile(F.AMBIENT_THEME_COLOR, F.ZONE_ALL, "Màu đèn viền", F.AMBIENT_COLORS.map { it.first to it.second }, icon = "ambient"),
        ChoiceTile(F.AMBIENT_MUSIC, F.ZONE_ALL, "Nháy theo nhạc", F.AMBIENT_MUSIC_MODES, icon = "music-light"),
        ToggleTile(F.READING_LIGHT, F.ZONE_ALL, "Đèn đọc sách", "reading-light"),
        ToggleTile(F.DRL, F.ZONE_ALL, "Đèn ban ngày", "ext-light"),
        ToggleTile(F.WELCOME_LIGHT, F.ZONE_ALL, "Đèn chào mừng", "welcome-light"),
        ToggleTile(F.AUTO_HIGH_BEAM, F.ZONE_ALL, "Pha tự động", "high-beam"),
        StepperTile(F.BACKLIGHT, F.ZONE_ALL, "Độ sáng màn hình", false,
            { v -> v.int(F.BACKLIGHT)?.toString() ?: "–" }, "brightness") { d -> controller.backlightStep(d, ::report) },
        ChoiceTile(F.DAYMODE, F.ZONE_ALL, "Ngày / đêm", F.DAYMODES, icon = "daynight"),
    )

    companion object {
        private const val REFRESH_MS = 1500L
        val TAB_NAMES = listOf("Điều hoà", "Ghế & sưởi", "Xe", "Đèn & màn hình")
    }
}
