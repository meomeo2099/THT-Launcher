package vn.tht.launcher.automation

import android.content.SharedPreferences
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.util.Log
import vn.tht.launcher.settings.Prefs
import vn.tht.launcher.system.Weather
import vn.tht.launcher.vehicle.EcarxController
import vn.tht.launcher.vehicle.VehicleData
import vn.tht.launcher.vehicle.EcarxFunctions as F

/** Cài đặt tự động hoá; từng mức 0 nghĩa là không bật chức năng đó. */
class AutomationPrefs(private val p: Prefs) {
    private val sp get() = p.sp
    private fun i(k: String, d: Int) = sp.getInt(k, d)
    private fun b(k: String, d: Boolean) = sp.getBoolean(k, d)
    private fun put(k: String, v: Int) = sp.edit().putInt(k, v).apply()
    private fun put(k: String, v: Boolean) = sp.edit().putBoolean(k, v).apply()

    var enabled get() = b("auto_on", false); set(v) = put("auto_on", v)
    var parkedMinutes get() = i("auto_parked_min", 0).coerceIn(0, 1440); set(v) = put("auto_parked_min", v.coerceIn(0, 1440))
    var heatDriver get() = i("auto_heat_drv", 0).coerceIn(0, 3); set(v) = put("auto_heat_drv", v.coerceIn(0, 3))
    var heatPassenger get() = i("auto_heat_pas", 0).coerceIn(0, 3); set(v) = put("auto_heat_pas", v.coerceIn(0, 3))
    var heatWheel get() = i("auto_heat_wheel", 0).coerceIn(0, 3); set(v) = put("auto_heat_wheel", v.coerceIn(0, 3))
    var heatRearGlass get() = b("auto_heat_rear_glass", false); set(v) = put("auto_heat_rear_glass", v)
    var heatBelowC get() = i("auto_heat_below", 5).coerceIn(-30, 40); set(v) = put("auto_heat_below", v.coerceIn(-30, 40))
    var heatOffMinutes get() = i("auto_heat_off", 15).coerceIn(0, 120); set(v) = put("auto_heat_off", v.coerceIn(0, 120))
    var ventDriver get() = i("auto_vent_drv", 0).coerceIn(0, 3); set(v) = put("auto_vent_drv", v.coerceIn(0, 3))
    var ventPassenger get() = i("auto_vent_pas", 0).coerceIn(0, 3); set(v) = put("auto_vent_pas", v.coerceIn(0, 3))
    var ventAboveC get() = i("auto_vent_above", 25).coerceIn(-10, 60); set(v) = put("auto_vent_above", v.coerceIn(-10, 60))
    var ventOffMinutes get() = i("auto_vent_off", 15).coerceIn(0, 120); set(v) = put("auto_vent_off", v.coerceIn(0, 120))
    var airOut get() = b("auto_air_out", false); set(v) = put("auto_air_out", v)
    var airOutAboveC get() = i("auto_air_above", 15).coerceIn(-10, 60); set(v) = put("auto_air_above", v.coerceIn(-10, 60))
    var airOutFan get() = i("auto_air_fan", 4).coerceIn(0, F.FAN_MAX); set(v) = put("auto_air_fan", v.coerceIn(0, F.FAN_MAX))
    var airOutMinutes get() = i("auto_air_min", 3).coerceIn(1, 30); set(v) = put("auto_air_min", v.coerceIn(1, 30))
    var climateAuto get() = b("auto_climate_auto", false); set(v) = put("auto_climate_auto", v)
    var climateTempC get() = i("auto_climate_temp", 22).coerceIn(F.TEMP_MIN.toInt(), F.TEMP_MAX.toInt()); set(v) = put("auto_climate_temp", v.coerceIn(F.TEMP_MIN.toInt(), F.TEMP_MAX.toInt()))
    var appsEnabled get() = b("auto_apps_on", false); set(v) = put("auto_apps_on", v)
    var closeRoofAtSpeed get() = i("auto_roof_speed", 0).coerceIn(0, 120); set(v) = put("auto_roof_speed", v.coerceIn(0, 120))

    internal fun signature(): Int = listOf(
        enabled, parkedMinutes, heatDriver, heatPassenger, heatWheel, heatRearGlass,
        heatBelowC, heatOffMinutes, ventDriver, ventPassenger, ventAboveC, ventOffMinutes,
        airOut, airOutAboveC, airOutFan, airOutMinutes, climateAuto, climateTempC, appsEnabled, closeRoofAtSpeed,
    ).hashCode()
}

/**
 * Mỗi chu kỳ động cơ chạy một lần. Mọi callback thuộc chu kỳ/cấu hình đã tạo nó và phải có dữ
 * liệu xe còn mới trước khi gửi. Ghi luôn đi qua controller kiểm tra quyền và đọc lại trạng thái.
 */
class AutomationEngine(
    private val prefs: Prefs,
    private val controller: EcarxController,
    private val log: (String) -> Unit = {},
    /** Đọc trên worker của controller, dùng để không tắt đè mức người dùng đã thay bằng tay. */
    private val readValue: ((Int, Int) -> Int?)? = null,
    /** Settings.Global.BOOT_COUNT nếu firmware cho đọc; không giả định về lần tắt nguồn trước. */
    private val bootCount: Int? = null,
    private val onApps: ((() -> Boolean) -> Unit)? = null,
    private val onCancelApps: (() -> Unit)? = null,
    private val gpsSpeedKmh: (() -> Float?)? = null,
) {
    private val a = AutomationPrefs(prefs)
    private val handler = Handler(Looper.getMainLooper())
    private val cycle = AutomationCycle()
    private val roof = RoofSpeedRule()
    @Volatile private var engineOn: Boolean? = null
    private var confirmedEngineOn: Boolean? = null
    private var lastEnabled = a.enabled
    @Volatile private var observedAt = Long.MIN_VALUE
    @Volatile private var stopped = false
    private val settingsListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        if (key != null && key.startsWith("auto_") && key != KEY_LAST_RUN && key != KEY_ENGINE_OFF_AT && key != KEY_LAST_BOOT_COUNT) {
            invalidateScheduled()
        }
    }

    init { prefs.sp.registerOnSharedPreferenceChangeListener(settingsListener) }

    fun onVehicle(d: VehicleData?) {
        if (stopped) return
        observedAt = SystemClock.elapsedRealtime()
        engineOn = d?.engineOn
        val enabled = a.enabled
        val becameEnabled = enabled && !lastEnabled
        if (lastEnabled != enabled) invalidateScheduled()
        lastEnabled = enabled
        val on = engineOn
        if (on == null) {
            // Không tiếp tục lệnh cũ khi nguồn xe mất hoặc chưa xác định được trạng thái máy.
            invalidateScheduled()
            return
        }
        val was = confirmedEngineOn
        confirmedEngineOn = on
        if (!on) {
            if (was != false) invalidateScheduled()
            if (was == true) prefs.sp.edit().putLong(KEY_ENGINE_OFF_AT, System.currentTimeMillis()).apply()
            return
        }
        val fresh = was == false || was == null && isFreshStart()
        if (enabled && (fresh || becameEnabled)) onEngineStart(d!!)
        if (was == null) markStarted()
        if (enabled) checkSpeedRules(d!!)
    }

    private fun invalidateScheduled() {
        cycle.invalidate()
        handler.removeCallbacksAndMessages(null)
        roof.reset()
        onCancelApps?.invoke()
    }

    private fun valid(token: AutomationCycle.Token): Boolean = !stopped && cycle.permits(
        token, a.signature(), a.enabled, engineOn,
        if (observedAt == Long.MIN_VALUE) Long.MAX_VALUE else SystemClock.elapsedRealtime() - observedAt,
    )

    private fun schedule(token: AutomationCycle.Token, delayMs: Long, block: () -> Unit) {
        handler.postDelayed({ if (valid(token)) block() }, delayMs.coerceAtLeast(0L))
    }

    private fun isFreshStart(): Boolean {
        val last = prefs.sp.getLong(KEY_LAST_RUN, 0L)
        val off = prefs.sp.getLong(KEY_ENGINE_OFF_AT, 0L)
        val previousBoot = prefs.sp.getInt(KEY_LAST_BOOT_COUNT, -1).takeIf { it >= 0 }
        return AutomationCycle.freshStart(last, off, previousBoot, bootCount?.takeIf { it >= 0 })
    }

    private fun markStarted() = prefs.sp.edit().putLong(KEY_LAST_RUN, System.currentTimeMillis()).apply {
        bootCount?.takeIf { it >= 0 }?.let { putInt(KEY_LAST_BOOT_COUNT, it) }
    }.apply()

    private fun onEngineStart(d: VehicleData) {
        invalidateScheduled()
        val token = cycle.token(a.signature())
        val now = System.currentTimeMillis()
        val off = prefs.sp.getLong(KEY_ENGINE_OFF_AT, 0L)
        markStarted()
        if (!AutomationCycle.parkedLongEnough(now, off, a.parkedMinutes)) {
            log("Bỏ qua tự động hoá: chưa xác nhận đủ ${a.parkedMinutes} phút đỗ")
            return
        }
        val weather = Weather.current
        val outside = AutomationCycle.validTemperature(d.outsideTempC, weather?.tempC,
            if (weather == null) Long.MAX_VALUE else now - weather.at)
        log("Nổ máy: chạy tự động hoá (ngoài trời ${outside ?: "chưa rõ"}°C)")
        var delay = 2_000L
        fun later(block: () -> Unit) {
            schedule(token, delay, block)
            delay += 1_500L
        }
        fun timedLevel(id: Int, zone: Int, level: Int, offMinutes: Int, name: String) {
            if (level <= 0) return
            val expected = F.level(id, level)
            later {
                controller.setValue(id, zone, expected, "$name mức $level", stillValid = { valid(token) }) { result ->
                    log(result.message)
                    if (result.ok && valid(token)) timedOff(token, id, zone, expected, offMinutes, name)
                }
            }
        }
        if (a.climateAuto) {
            later { setOn(token, F.HVAC_AUTO, F.ZONE_ALL, "Điều hoà AUTO") }
            val target = a.climateTempC.toFloat()
            later { controller.setTemp(target, stillValid = { valid(token) }) { log(it.message) } }
        }
        if (outside != null && outside < a.heatBelowC) {
            timedLevel(F.HVAC_SEAT_HEATING, F.ZONE_ROW_1_LEFT, a.heatDriver, a.heatOffMinutes, "Sưởi ghế lái")
            timedLevel(F.HVAC_SEAT_HEATING, F.ZONE_ROW_1_RIGHT, a.heatPassenger, a.heatOffMinutes, "Sưởi ghế phụ")
            timedLevel(F.HVAC_STEERING_WHEEL_HEAT, F.ZONE_ALL, a.heatWheel, a.heatOffMinutes, "Sưởi vô lăng")
            if (a.heatRearGlass) later {
                controller.setValue(F.HVAC_DEFROST_REAR, F.ZONE_ALL, F.ON, "Sấy kính sau", stillValid = { valid(token) }) { result ->
                    log(result.message)
                    if (result.ok && valid(token)) timedOff(token, F.HVAC_DEFROST_REAR, F.ZONE_ALL, F.ON, a.heatOffMinutes, "Sấy kính sau")
                }
            }
        }
        if (outside != null && outside >= a.ventAboveC) {
            timedLevel(F.HVAC_SEAT_VENTILATION, F.ZONE_ROW_1_LEFT, a.ventDriver, a.ventOffMinutes, "Thông gió ghế lái")
            timedLevel(F.HVAC_SEAT_VENTILATION, F.ZONE_ROW_1_RIGHT, a.ventPassenger, a.ventOffMinutes, "Thông gió ghế phụ")
        }
        // Xả nóng có ngưỡng riêng, không phụ thuộc người dùng bật thông gió ghế.
        if (a.airOut && outside != null && outside >= a.airOutAboveC) {
            later {
                controller.setValue(F.BCM_SUNROOF_TILT, F.ZONE_ROOF, F.ON, "Hé cửa sổ trời",
                    EcarxController.Safety.PARKED, stillValid = { valid(token) }) { result ->
                    log(result.message)
                    if (result.ok && valid(token)) timedOff(token, F.BCM_SUNROOF_TILT, F.ZONE_ROOF, F.ON, a.airOutMinutes, "Hé cửa sổ trời")
                }
            }
            val fan = a.airOutFan
            later {
                controller.setValue(F.HVAC_FAN_SPEED, F.ZONE_ALL, F.level(F.HVAC_FAN_SPEED, fan), "Quạt mức $fan", stillValid = { valid(token) }) { log(it.message) }
            }
        }
        if (a.appsEnabled) later { onApps?.invoke { valid(token) } }
    }

    /** Tính thời lượng từ lúc bật đã được xe xác nhận, không tính từ lúc còn nằm trong hàng đợi. */
    private fun timedOff(token: AutomationCycle.Token, id: Int, zone: Int, expected: Int, minutes: Int, name: String) {
        if (minutes <= 0) return
        schedule(token, minutes.coerceAtMost(120) * 60_000L) {
            val reader = readValue ?: return@schedule
            controller.setValue(id, zone, F.OFF, "$name: tắt sau $minutes phút", stillValid = {
                // Kiểm tra sở hữu ngay trên worker trước SDK write, không qua read/main/write race.
                val current = if (valid(token)) runCatching { reader(id, zone) }.getOrNull() else null
                valid(token) && current != null &&
                    (current == expected || F.levelOf(id, current) == F.levelOf(id, expected))
            }) { result ->
                if (valid(token)) log(result.message)
            }
        }
    }

    private fun setOn(token: AutomationCycle.Token, id: Int, zone: Int, name: String) =
        controller.setValue(id, zone, F.ON, "$name: bật", stillValid = { valid(token) }) { log(it.message) }

    private fun checkSpeedRules(d: VehicleData) {
        if (!controller.canWrite) return
        val speed = listOfNotNull(d.speedKmh, gpsSpeedKmh?.invoke()).filter { it.isFinite() && it in 0f..300f }.maxOrNull()
        val limit = a.closeRoofAtSpeed
        if (!roof.shouldClose(speed, limit, SystemClock.elapsedRealtime())) return
        val token = cycle.token(a.signature())
        val operations = listOf(F.BCM_WINDOW, F.BCM_SUNROOF_TILT).filter { controller.supported(it, F.ZONE_ROOF) }
        if (operations.isEmpty()) { roof.completed(false); return }
        var remaining = operations.size
        var allOk = true
        operations.forEach { id ->
            controller.setValue(id, F.ZONE_ROOF, F.OFF, "Đóng cửa sổ trời ở ${speed!!.toInt()} km/h", stillValid = { valid(token) }) { result ->
                if (valid(token)) {
                    log(result.message)
                    allOk = allOk && result.ok
                    remaining--
                    if (remaining == 0) roof.completed(allOk)
                }
            }
        }
    }

    fun stop() {
        if (stopped) return
        stopped = true
        invalidateScheduled()
        prefs.sp.unregisterOnSharedPreferenceChangeListener(settingsListener)
    }

    companion object {
        private const val KEY_LAST_RUN = "auto_last_run"
        private const val KEY_ENGINE_OFF_AT = "auto_engine_off_at"
        private const val KEY_LAST_BOOT_COUNT = "auto_last_boot_count"
        const val TAG = "Automation"
        fun defaultLog(msg: String) { Log.i(TAG, msg) }
    }
}
