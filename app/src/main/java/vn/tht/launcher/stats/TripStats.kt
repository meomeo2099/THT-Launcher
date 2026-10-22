package vn.tht.launcher.stats

import android.content.Context
import android.util.Log
import org.json.JSONObject
import vn.tht.launcher.settings.Prefs
import vn.tht.launcher.vehicle.VehicleData
import java.io.File

/** Một chuyến đi (từ lúc nổ máy đến lúc tắt máy). Quãng đường km, thời gian giây, xăng lít. */
data class Trip(
    val start: Long,
    val end: Long,
    val distanceKm: Float,
    val driveSec: Long,
    val idleSec: Long,
    val fuelL: Float?,
    val maxSpeed: Float,
) {
    val avgSpeed get() = if (driveSec > 0) distanceKm / (driveSec / 3600f) else 0f
    /** L/100 km; null nếu không đủ dữ liệu. */
    val consumption get() = if (fuelL != null && fuelL > 0f && distanceKm >= 1f) fuelL / distanceKm * 100f else null

    fun toJson() = JSONObject().put("s", start).put("e", end).put("d", distanceKm.toDouble())
        .put("dr", driveSec).put("id", idleSec).put("f", fuelL?.toDouble() ?: JSONObject.NULL).put("m", maxSpeed.toDouble())

    companion object {
        fun fromJson(o: JSONObject) = Trip(
            o.getLong("s"), o.getLong("e"), o.getDouble("d").toFloat(), o.getLong("dr"), o.getLong("id"),
            if (o.isNull("f")) null else o.getDouble("f").toFloat(), o.getDouble("m").toFloat(),
        )
    }
}

/** Một lần đổ xăng (tự nhận biết khi % xăng tăng ≥ 8% lúc xe đứng yên, hoặc người dùng nhập). */
data class Refuel(val time: Long, val liters: Float, val full: Boolean, val odoKm: Float?) {
    fun toJson() = JSONObject().put("t", time).put("l", liters.toDouble()).put("full", full)
        .put("odo", odoKm?.toDouble() ?: JSONObject.NULL)

    companion object {
        fun fromJson(o: JSONObject) = Refuel(
            o.getLong("t"), o.getDouble("l").toFloat(), o.optBoolean("full"),
            if (o.isNull("odo")) null else o.getDouble("odo").toFloat(),
        )
    }
}

/** Một lần đo điện áp ắc quy: thời điểm, vôn, máy đang nổ không. */
data class VoltagePoint(val time: Long, val volts: Float, val engine: Boolean)

/** Lưu chuyến đi / đổ xăng dạng JSON từng dòng trong filesDir. */
class StatsStore(private val c: Context) {
    private val tripsFile get() = File(c.filesDir, "trips.jsonl")
    private val refuelFile get() = File(c.filesDir, "refuels.jsonl")
    private val voltFile get() = File(c.filesDir, "voltage.jsonl")

    fun trips(): List<Trip> = read(tripsFile) { Trip.fromJson(it) }
    fun refuels(): List<Refuel> = read(refuelFile) { Refuel.fromJson(it) }
    fun add(t: Trip) = tripsFile.appendText(t.toJson().toString() + "\n")
    fun add(r: Refuel) = refuelFile.appendText(r.toJson().toString() + "\n")
    fun deleteRefuel(r: Refuel) = rewrite(refuelFile, refuels().filter { it != r }.map { it.toJson().toString() })
    fun clear() { tripsFile.delete(); refuelFile.delete(); voltFile.delete() }

    fun voltages(): List<VoltagePoint> = read(voltFile) {
        VoltagePoint(it.getLong("t"), it.getDouble("v").toFloat(), it.optBoolean("e"))
    }

    fun add(p: VoltagePoint) = voltFile.appendText(
        JSONObject().put("t", p.time).put("v", p.volts.toDouble()).put("e", p.engine).toString() + "\n")

    /** Chỉ giữ [days] ngày gần nhất. */
    fun trimVoltages(days: Int) {
        val from = System.currentTimeMillis() - days * 86_400_000L
        val keep = voltages().filter { it.time >= from }
        rewrite(voltFile, keep.map { JSONObject().put("t", it.time).put("v", it.volts.toDouble()).put("e", it.engine).toString() })
    }

    private fun <T> read(f: File, parse: (JSONObject) -> T): List<T> = if (!f.exists()) emptyList() else
        f.readLines().mapNotNull { line -> runCatching { parse(JSONObject(line)) }.getOrNull() }

    private fun rewrite(f: File, lines: List<String>) = f.writeText(lines.joinToString("") { it + "\n" })
}

/**
 * Ghi chuyến đi từ dữ liệu VHAL (gọi mỗi lần có dữ liệu mới, ~500 ms).
 * Quãng đường lấy theo ODO nếu có, không thì tích phân tốc độ.
 */
class TripRecorder(private val c: Context) {
    private val store = StatsStore(c)
    private val prefs = Prefs(c)

    private var startAt = 0L
    private var lastAt = 0L
    private var startOdo: Float? = null
    private var integratedKm = 0.0
    private var driveMs = 0L
    private var idleMs = 0L
    private var maxSpeed = 0f
    private var startFuelPct: Float? = null
    private var lastFuelPct: Float? = null
    private var lastOdo: Float? = null
    private var refuelBaseline: Float? = null
    private var engine: Boolean? = null
    private var lastVoltAt = 0L
    private var engineStartAt = 0L

    /** Chuyến đang chạy (null nếu máy tắt). */
    var current: Trip? = null
        private set

    fun onVehicle(d: VehicleData?) {
        if (!prefs.sp.getBoolean("stats_on", true)) return
        val now = System.currentTimeMillis()
        val on = d?.engineOn ?: return
        logVoltage(d, on, now)
        if (on && engine != true) begin(d, now)
        if (!on && engine == true) finish(now)
        engine = on
        if (!on) { detectRefuel(d, now); return }

        val dt = (now - lastAt).coerceIn(0, 5_000)
        lastAt = now
        val speed = d.speedKmh ?: 0f
        if (speed > 1f) {
            driveMs += dt
            integratedKm += speed * dt / 3_600_000.0
        } else idleMs += dt
        if (speed > maxSpeed) maxSpeed = speed
        d.fuelPercent?.let { lastFuelPct = it }
        d.odometerKm?.let { lastOdo = it }
        if (speed < 1f) detectRefuel(d, now) else refuelBaseline = d.fuelPercent
        current = build(now)
    }

    private fun begin(d: VehicleData, now: Long) {
        startAt = now; lastAt = now
        startOdo = d.odometerKm; integratedKm = 0.0
        driveMs = 0; idleMs = 0; maxSpeed = 0f
        startFuelPct = d.fuelPercent
        refuelBaseline = d.fuelPercent
    }

    private fun build(now: Long): Trip {
        val odoKm = startOdo?.let { s -> lastOdo?.let { (it - s).coerceAtLeast(0f) } }
        val tank = tankLiters()
        val fuel = if (tank > 0f && startFuelPct != null && lastFuelPct != null) {
            ((startFuelPct!! - lastFuelPct!!) / 100f * tank).takeIf { it >= 0f }
        } else null
        return Trip(startAt, now, odoKm ?: integratedKm.toFloat(), driveMs / 1000, idleMs / 1000, fuel, maxSpeed)
    }

    private fun finish(now: Long) {
        val t = build(now)
        current = null
        // Bỏ các lần nổ máy rất ngắn không di chuyển
        if (t.distanceKm < 0.2f && (now - startAt) < 120_000) return
        runCatching { store.add(t) }.onFailure { Log.w(TAG, "Không lưu được chuyến: $it") }
    }

    /** % xăng tăng ≥ 8 điểm khi xe đứng yên = vừa đổ xăng. */
    private fun detectRefuel(d: VehicleData?, now: Long) {
        val pct = d?.fuelPercent ?: return
        val base = refuelBaseline
        if (base == null || pct < base) { refuelBaseline = pct; return }
        if (pct - base >= 8f) {
            val tank = tankLiters()
            if (tank > 0f) {
                store.add(Refuel(now, (pct - base) / 100f * tank, pct >= 97f, d.odometerKm))
                Log.i(TAG, "Nhận biết đổ xăng: ${base.toInt()}% -> ${pct.toInt()}%")
            }
            refuelBaseline = pct
        }
    }

    /**
     * Điện áp ắc quy: ghi mỗi phút; 30 giây đầu sau khi nổ máy ghi mỗi giây để bắt được lúc đề
     * (sụt áp khi đề cho biết ắc quy còn khoẻ không). Giữ 30 ngày.
     */
    private fun logVoltage(d: VehicleData, on: Boolean, now: Long) {
        val v = d.batteryVoltage ?: return
        if (on && engine != true) engineStartAt = now
        val starting = on && now - engineStartAt < 30_000
        if (now - lastVoltAt < (if (starting) 1_000L else 60_000L)) return
        if (lastVoltAt == 0L) runCatching { store.trimVoltages(30) }
        lastVoltAt = now
        runCatching { store.add(VoltagePoint(now, v, on)) }
    }

    private fun tankLiters(): Float = prefs.tankLiters.toFloat().takeIf { it > 0f } ?: DEFAULT_TANK_L

    companion object {
        private const val TAG = "TripRecorder"
        /** Dung tích tạm khi người dùng chưa nhập (để ước lượng lít); chỉnh trong Cài đặt → Xe. */
        const val DEFAULT_TANK_L = 0f
    }
}
