package vn.tht.launcher.stats

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import vn.tht.launcher.LauncherApp
import vn.tht.launcher.settings.Prefs
import vn.tht.launcher.ui.Palette
import vn.tht.launcher.ui.Ui
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

/** Thống kê chuyến đi, đổ xăng, bảo dưỡng. */
class StatsActivity : Activity() {
    private lateinit var prefs: Prefs
    private lateinit var store: StatsStore
    private lateinit var list: LinearLayout
    private var period = 0 // 0 hôm nay, 1 tuần, 2 tháng, 3 năm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = Prefs(this)
        store = StatsStore(this)
        render()
    }

    private fun render() {
        window.decorView.setBackgroundColor(Palette.bg)
        list = Ui.vbox(this, 24)
        setContentView(ScrollView(this).apply { addView(list) })
        val trips = store.trips()
        val refuels = store.refuels()
        val current = (application as LauncherApp).recorder.current

        list.addView(Ui.hbox(this).apply {
            addView(Ui.text(context, "Thống kê", 30f, bold = true), Ui.weight())
            addView(Ui.button(context, "Xong") { finish() })
        })

        section("Chuyến hiện tại")
        if (current == null) note("Máy đang tắt hoặc chưa có dữ liệu.")
        else cards(listOf(
            "Quãng đường" to km(current.distanceKm),
            "Thời gian lái" to dur(current.driveSec),
            "Nổ máy chờ" to dur(current.idleSec),
            "Tốc độ TB" to "${current.avgSpeed.toInt()} km/h",
            "Tiêu hao" to (current.consumption?.let { String.format(Locale.US, "%.1f L/100km", it) } ?: "–"),
        ))

        section("Tổng hợp")
        list.addView(Ui.hbox(this).apply {
            listOf("Hôm nay", "7 ngày", "Tháng này", "Năm nay").forEachIndexed { i, name ->
                val b = Ui.button(context, name) { period = i; render() }
                Ui.setActive(b, i == period)
                addView(b, Ui.wrap(8, context))
            }
        })
        val from = periodStart(period)
        val inPeriod = trips.filter { it.start >= from }
        val dist = inPeriod.sumOf { it.distanceKm.toDouble() }.toFloat()
        val fuel = inPeriod.mapNotNull { it.fuelL }.sum()
        val fuelDist = inPeriod.filter { it.fuelL != null }.sumOf { it.distanceKm.toDouble() }.toFloat()
        cards(listOf(
            "Quãng đường" to km(dist),
            "Số chuyến" to "${inPeriod.size}",
            "Thời gian lái" to dur(inPeriod.sumOf { it.driveSec }),
            "Nổ máy chờ" to dur(inPeriod.sumOf { it.idleSec }),
            "Xăng" to if (fuel > 0f) String.format(Locale.US, "%.1f L", fuel) else "–",
            "Tiêu hao TB" to if (fuel > 0f && fuelDist >= 1f) String.format(Locale.US, "%.1f L/100km", fuel / fuelDist * 100f) else "–",
            "Tốc độ cao nhất" to "${(inPeriod.maxOfOrNull { it.maxSpeed } ?: 0f).toInt()} km/h",
            "Tổng giờ máy" to dur(trips.sumOf { it.driveSec + it.idleSec }),
        ))
        list.addView(DistanceChart(this, trips), LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Ui.dp(this, 200)))

        section("Bảo dưỡng")
        val serviceKm = prefs.sp.getInt("service_km", 0)
        val serviceDay = prefs.sp.getLong("service_date", 0L)
        val odo = (application as LauncherApp).lastOdometerKm
        val kmLeft = if (serviceKm > 0 && odo != null) serviceKm - odo.toInt() else null
        val daysLeft = if (serviceDay > 0) TimeUnit.MILLISECONDS.toDays(serviceDay - System.currentTimeMillis()) else null
        row("Bảo dưỡng tiếp theo",
            listOfNotNull(
                if (serviceKm > 0) "tại ${"%,d".format(serviceKm)} km" + (kmLeft?.let { " (còn ${"%,d".format(it)} km)" } ?: "") else null,
                if (serviceDay > 0) "ngày ${date(serviceDay)}" + (daysLeft?.let { " (còn $it ngày)" } ?: "") else null,
            ).joinToString(" · ").ifEmpty { "Chưa đặt · chạm để đặt" }) { editService() }

        section("Đổ xăng")
        row("Thêm lần đổ xăng", "Nhập số lít") { addRefuel() }
        val fullToFull = consumptionByRefuels(refuels)
        if (fullToFull != null) note(String.format(Locale.US, "Tiêu hao theo các lần đổ đầy: %.1f L/100km", fullToFull))
        refuels.takeLast(10).reversed().forEach { r ->
            row(String.format(Locale.US, "%.1f lít", r.liters) + if (r.full) " · đầy bình" else "",
                date(r.time) + (r.odoKm?.let { " · ODO ${"%,.0f".format(it)} km" } ?: "")) {
                confirm("Xoá lần đổ xăng này?") { store.deleteRefuel(r); render() }
            }
        }
        if (refuels.isEmpty()) note("Chưa có. Launcher tự nhận biết khi % xăng tăng lúc đỗ (cần nhập dung tích bình trong Cài đặt).")

        section("Các chuyến gần đây")
        if (trips.isEmpty()) note("Chưa có chuyến nào được ghi.")
        trips.takeLast(30).reversed().forEach { t ->
            row("${dateTime(t.start)} · ${km(t.distanceKm)}",
                "Lái ${dur(t.driveSec)} · chờ ${dur(t.idleSec)} · TB ${t.avgSpeed.toInt()} km/h · max ${t.maxSpeed.toInt()} km/h" +
                    (t.consumption?.let { String.format(Locale.US, " · %.1f L/100km", it) } ?: ""), null)
        }

        section("Dữ liệu")
        row("Xoá toàn bộ thống kê", "Chuyến đi và đổ xăng") {
            confirm("Xoá toàn bộ chuyến đi và lịch sử đổ xăng?") { store.clear(); render() }
        }
    }

    // ---- Tính toán ----

    private fun periodStart(p: Int): Long {
        val cal = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0); set(Calendar.MINUTE, 0); set(Calendar.SECOND, 0); set(Calendar.MILLISECOND, 0)
        }
        when (p) {
            1 -> cal.add(Calendar.DAY_OF_YEAR, -6)
            2 -> cal.set(Calendar.DAY_OF_MONTH, 1)
            3 -> cal.set(Calendar.DAY_OF_YEAR, 1)
        }
        return cal.timeInMillis
    }

    /** Tiêu hao giữa hai lần đổ đầy liên tiếp có ODO: lít đổ lần sau / quãng đường. */
    private fun consumptionByRefuels(r: List<Refuel>): Float? {
        val full = r.filter { it.full && it.odoKm != null }
        if (full.size < 2) return null
        val a = full[full.size - 2]
        val b = full.last()
        val liters = r.filter { it.time > a.time && it.time <= b.time }.sumOf { it.liters.toDouble() }.toFloat()
        val dist = b.odoKm!! - a.odoKm!!
        return if (dist >= 20f) liters / dist * 100f else null
    }

    // ---- Hộp thoại ----

    private fun addRefuel() {
        val input = EditText(this).apply { inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL; hint = "Số lít" }
        AlertDialog.Builder(this).setTitle("Đổ xăng").setView(input)
            .setPositiveButton("Đầy bình") { _, _ -> saveRefuel(input, true) }
            .setNeutralButton("Chưa đầy") { _, _ -> saveRefuel(input, false) }
            .setNegativeButton("Huỷ", null).show()
    }

    private fun saveRefuel(input: EditText, full: Boolean) {
        val l = input.text.toString().replace(',', '.').toFloatOrNull() ?: return
        if (l <= 0f || l > 200f) return
        store.add(Refuel(System.currentTimeMillis(), l, full, (application as LauncherApp).lastOdometerKm))
        render()
    }

    private fun editService() {
        val km = EditText(this).apply { inputType = InputType.TYPE_CLASS_NUMBER; hint = "Bảo dưỡng tại ODO (km)" }
        val days = EditText(this).apply { inputType = InputType.TYPE_CLASS_NUMBER; hint = "Hoặc sau bao nhiêu ngày nữa" }
        AlertDialog.Builder(this).setTitle("Nhắc bảo dưỡng")
            .setView(Ui.vbox(this, 16).apply { addView(km); addView(days) })
            .setPositiveButton("Lưu") { _, _ ->
                val ed = prefs.sp.edit()
                km.text.toString().toIntOrNull()?.let { ed.putInt("service_km", it) }
                days.text.toString().toIntOrNull()?.let { ed.putLong("service_date", System.currentTimeMillis() + TimeUnit.DAYS.toMillis(it.toLong())) }
                ed.apply(); render()
            }
            .setNeutralButton("Xoá nhắc") { _, _ -> prefs.sp.edit().remove("service_km").remove("service_date").apply(); render() }
            .setNegativeButton("Huỷ", null).show()
    }

    private fun confirm(msg: String, ok: () -> Unit) =
        AlertDialog.Builder(this).setMessage(msg).setPositiveButton("Đồng ý") { _, _ -> ok() }.setNegativeButton("Huỷ", null).show()

    // ---- Dựng giao diện ----

    private fun section(t: String) = list.addView(Ui.text(this, t, 16f, bold = true).apply {
        setTextColor(Palette.accent); setPadding(0, Ui.dp(context, 28), 0, Ui.dp(context, 8))
    })

    private fun note(t: String) = list.addView(Ui.text(this, t, 16f, dim = true).apply { setPadding(0, 0, 0, Ui.dp(context, 8)) })

    private fun row(title: String, summary: String?, onClick: (() -> Unit)?) {
        list.addView(Ui.vbox(this).apply {
            background = Ui.card(context, 14)
            setPadding(Ui.dp(context, 20), Ui.dp(context, 12), Ui.dp(context, 20), Ui.dp(context, 12))
            addView(Ui.text(context, title, 19f))
            if (summary != null) addView(Ui.text(context, summary, 15f, dim = true))
            if (onClick != null) setOnClickListener { onClick() }
        }, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
            bottomMargin = Ui.dp(this@StatsActivity, 8)
        })
    }

    /** Hàng ô số liệu, 4 ô mỗi hàng. */
    private fun cards(items: List<Pair<String, String>>) {
        items.chunked(4).forEach { chunk ->
            list.addView(Ui.hbox(this).apply {
                setPadding(0, 0, 0, Ui.dp(context, 12))
                chunk.forEach { (k, v) ->
                    addView(Ui.vbox(context, 16).apply {
                        background = Ui.card(context, 16)
                        addView(Ui.text(context, k, 15f, dim = true))
                        addView(Ui.text(context, v, 26f))
                    }, Ui.weight().apply { marginEnd = Ui.dp(context, 12) })
                }
                repeat(4 - chunk.size) { addView(View(context), Ui.weight().apply { marginEnd = Ui.dp(context, 12) }) }
            })
        }
    }

    private fun km(v: Float) = String.format(Locale.US, if (v < 10f) "%.1f km" else "%,.0f km", v)
    private fun dur(sec: Long) = if (sec >= 3600) "${sec / 3600} giờ ${sec % 3600 / 60} phút" else "${sec / 60} phút"
    private fun date(t: Long) = SimpleDateFormat("dd/MM/yyyy", Locale.US).format(Date(t))
    private fun dateTime(t: Long) = SimpleDateFormat("dd/MM HH:mm", Locale.US).format(Date(t))

    /** Biểu đồ cột quãng đường 14 ngày gần nhất. */
    private class DistanceChart(c: Context, trips: List<Trip>) : View(c) {
        private val days = 14
        private val values = FloatArray(days)
        private val labels = Array(days) { "" }
        private val bar = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Palette.accent }
        private val text = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Palette.textDim; textSize = Ui.dp(c, 13).toFloat(); textAlign = Paint.Align.CENTER }

        init {
            val cal = Calendar.getInstance().apply { set(Calendar.HOUR_OF_DAY, 0); set(Calendar.MINUTE, 0); set(Calendar.SECOND, 0); set(Calendar.MILLISECOND, 0) }
            cal.add(Calendar.DAY_OF_YEAR, -(days - 1))
            val fmt = SimpleDateFormat("dd/MM", Locale.US)
            for (i in 0 until days) {
                val s = cal.timeInMillis
                labels[i] = fmt.format(Date(s))
                cal.add(Calendar.DAY_OF_YEAR, 1)
                values[i] = trips.filter { it.start in s until cal.timeInMillis }.sumOf { it.distanceKm.toDouble() }.toFloat()
            }
            background = Ui.card(c, 16)
        }

        override fun onDraw(canvas: Canvas) {
            val pad = Ui.dp(context, 16).toFloat()
            val labelH = Ui.dp(context, 24).toFloat()
            val max = (values.maxOrNull() ?: 0f).coerceAtLeast(1f)
            val w = (width - 2 * pad) / days
            val h = height - 2 * pad - labelH
            for (i in 0 until days) {
                val cx = pad + w * i + w / 2
                val bh = h * values[i] / max
                canvas.drawRoundRect(cx - w * 0.3f, pad + h - bh, cx + w * 0.3f, pad + h, 6f, 6f, bar)
                canvas.drawText(labels[i], cx, height - pad, text)
                if (values[i] > 0f) canvas.drawText("${values[i].toInt()}", cx, pad + h - bh - 6f, text)
            }
        }
    }
}
