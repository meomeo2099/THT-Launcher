package vn.tht.launcher.automation

import android.os.Handler
import android.os.Looper
import android.util.Log
import vn.tht.launcher.settings.Prefs
import vn.tht.launcher.vehicle.EcarxController
import vn.tht.launcher.vehicle.VehicleData
import vn.tht.launcher.vehicle.EcarxFunctions as F

/** Cài đặt tự động hoá (lưu trong [Prefs]). Mức 0 = không làm. */
class AutomationPrefs(private val p: Prefs) {
    private val sp get() = p.sp
    private fun i(k: String, d: Int) = sp.getInt(k, d)
    private fun b(k: String, d: Boolean) = sp.getBoolean(k, d)
    private fun put(k: String, v: Int) = sp.edit().putInt(k, v).apply()
    private fun put(k: String, v: Boolean) = sp.edit().putBoolean(k, v).apply()

    var enabled get() = b("auto_on", false); set(v) = put("auto_on", v)
    /** Chỉ chạy khi xe đã đỗ ít nhất N phút trước lần nổ máy này (0 = mỗi lần nổ máy). */
    var parkedMinutes get() = i("auto_parked_min", 0); set(v) = put("auto_parked_min", v)

    var heatDriver get() = i("auto_heat_drv", 0); set(v) = put("auto_heat_drv", v)
    var heatPassenger get() = i("auto_heat_pas", 0); set(v) = put("auto_heat_pas", v)
    var heatWheel get() = i("auto_heat_wheel", 0); set(v) = put("auto_heat_wheel", v)
    /** Chỉ sưởi khi ngoài trời dưới X °C. */
    var heatBelowC get() = i("auto_heat_below", 10); set(v) = put("auto_heat_below", v)
    var heatOffMinutes get() = i("auto_heat_off", 15); set(v) = put("auto_heat_off", v)

    var ventDriver get() = i("auto_vent_drv", 0); set(v) = put("auto_vent_drv", v)
    var ventPassenger get() = i("auto_vent_pas", 0); set(v) = put("auto_vent_pas", v)
    /** Chỉ làm mát ghế / thông gió khi ngoài trời từ X °C. */
    var ventAboveC get() = i("auto_vent_above", 30); set(v) = put("auto_vent_above", v)
    var ventOffMinutes get() = i("auto_vent_off", 15); set(v) = put("auto_vent_off", v)

    var airOut get() = b("auto_air_out", false); set(v) = put("auto_air_out", v)
    var airOutFan get() = i("auto_air_fan", 5); set(v) = put("auto_air_fan", v)
    var airOutMinutes get() = i("auto_air_min", 3); set(v) = put("auto_air_min", v)

    var climateAuto get() = b("auto_climate_auto", false); set(v) = put("auto_climate_auto", v)

    var closeRoofAtSpeed get() = i("auto_roof_speed", 0); set(v) = put("auto_roof_speed", v)
}

/**
 * Chạy các quy tắc sau mỗi lần nổ máy, khi launcher đang chạy. Mỗi quy tắc chạy một lần mỗi lần
 * nổ máy. Lệnh ghi đi qua [EcarxController] (khoá theo chữ ký platform, có đọc lại xác nhận).
 */
class AutomationEngine(
    private val prefs: Prefs,
    private val controller: EcarxController,
    private val log: (String) -> Unit = {},
) {
    private val a = AutomationPrefs(prefs)
    private val handler = Handler(Looper.getMainLooper())
    private var engineOn: Boolean? = null
    private var roofClosedThisTrip = false

    fun onVehicle(d: VehicleData?) {
        val on = d?.engineOn ?: return
        val was = engineOn
        engineOn = on
        if (!on) {
            if (was == true) prefs.sp.edit().putLong(KEY_ENGINE_OFF_AT, System.currentTimeMillis()).apply()
            return
        }
        if (was == false || was == null && isFreshStart()) onEngineStart(d)
        if (was == null) markStarted()
        checkSpeedRules(d)
    }

    /** Lần đầu thấy máy nổ trong tiến trình: chỉ coi là "vừa nổ máy" nếu chưa chạy cho lần này. */
    private fun isFreshStart(): Boolean {
        val last = prefs.sp.getLong(KEY_LAST_RUN, 0L)
        val off = prefs.sp.getLong(KEY_ENGINE_OFF_AT, 0L)
        return last == 0L || off > last
    }

    private fun markStarted() = prefs.sp.edit().putLong(KEY_LAST_RUN, System.currentTimeMillis()).apply()

    private fun onEngineStart(d: VehicleData) {
        roofClosedThisTrip = false
        if (!a.enabled) return
        val off = prefs.sp.getLong(KEY_ENGINE_OFF_AT, 0L)
        val parkedMin = if (off > 0) (System.currentTimeMillis() - off) / 60_000 else Long.MAX_VALUE
        if (a.parkedMinutes > 0 && parkedMin < a.parkedMinutes) {
            log("Bỏ qua tự động hoá: mới đỗ $parkedMin phút (< ${a.parkedMinutes})")
            markStarted()
            return
        }
        markStarted()
        val outside = d.outsideTempC
        log("Nổ máy: chạy tự động hoá (ngoài trời ${outside ?: "?"}°C)")
        // Lệnh cách nhau 1,5 s cho bộ điều khiển xe kịp xử lý
        var t = 2000L
        fun later(block: () -> Unit) { handler.postDelayed(block, t); t += 1500L }

        if (a.climateAuto) later { setOn(F.HVAC_AUTO, F.ZONE_ALL, "Điều hoà AUTO") }

        val cold = outside != null && outside < a.heatBelowC
        if (cold) {
            levelRule(F.HVAC_SEAT_HEATING, F.ZONE_ROW_1_LEFT, a.heatDriver, a.heatOffMinutes, "Sưởi ghế lái", ::later)
            levelRule(F.HVAC_SEAT_HEATING, F.ZONE_ROW_1_RIGHT, a.heatPassenger, a.heatOffMinutes, "Sưởi ghế phụ", ::later)
            levelRule(F.HVAC_STEERING_WHEEL_HEAT, F.ZONE_ALL, a.heatWheel, a.heatOffMinutes, "Sưởi vô lăng", ::later)
        }
        val warm = outside != null && outside >= a.ventAboveC
        if (warm) {
            levelRule(F.HVAC_SEAT_VENTILATION, F.ZONE_ROW_1_LEFT, a.ventDriver, a.ventOffMinutes, "Làm mát ghế lái", ::later)
            levelRule(F.HVAC_SEAT_VENTILATION, F.ZONE_ROW_1_RIGHT, a.ventPassenger, a.ventOffMinutes, "Làm mát ghế phụ", ::later)
            if (a.airOut) {
                later { setOn(F.BCM_SUNROOF_TILT, F.ZONE_ROOF, "Hé cửa sổ trời") }
                later {
                    controller.setValue(F.HVAC_FAN_SPEED, F.ZONE_ALL, F.level(F.HVAC_FAN_SPEED, a.airOutFan.coerceIn(1, 7)),
                        "Quạt mức ${a.airOutFan}") { log(it.message) }
                }
                handler.postDelayed({
                    controller.setValue(F.BCM_SUNROOF_TILT, F.ZONE_ROOF, F.OFF, "Đóng hé cửa sổ trời") { log(it.message) }
                }, t + a.airOutMinutes.coerceIn(1, 30) * 60_000L)
            }
        }
    }

    private fun levelRule(id: Int, zone: Int, level: Int, offMin: Int, name: String, later: (() -> Unit) -> Unit) {
        if (level <= 0) return
        later { controller.setValue(id, zone, F.level(id, level.coerceIn(1, 3)), "$name mức $level") { log(it.message) } }
        if (offMin > 0) handler.postDelayed({
            controller.setValue(id, zone, F.OFF, "$name: tắt sau $offMin phút") { log(it.message) }
        }, offMin * 60_000L)
    }

    private fun setOn(id: Int, zone: Int, name: String) =
        controller.setValue(id, zone, F.ON, "$name: bật") { log(it.message) }

    private fun checkSpeedRules(d: VehicleData) {
        val limit = a.closeRoofAtSpeed
        val speed = d.speedKmh ?: return
        if (!a.enabled || limit <= 0 || roofClosedThisTrip || speed < limit) return
        roofClosedThisTrip = true
        controller.setValue(F.BCM_WINDOW, F.ZONE_ROOF, F.OFF, "Đóng cửa sổ trời ở ${speed.toInt()} km/h") { log(it.message) }
        controller.setValue(F.BCM_SUNROOF_TILT, F.ZONE_ROOF, F.OFF, "Đóng hé cửa sổ trời") { log(it.message) }
    }

    fun stop() = handler.removeCallbacksAndMessages(null)

    companion object {
        private const val KEY_LAST_RUN = "auto_last_run"
        private const val KEY_ENGINE_OFF_AT = "auto_engine_off_at"
        const val TAG = "Automation"
        fun defaultLog(msg: String) { Log.i(TAG, msg) }
    }
}
