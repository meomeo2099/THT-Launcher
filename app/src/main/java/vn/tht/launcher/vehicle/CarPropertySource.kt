package vn.tht.launcher.vehicle

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log

/**
 * Đọc dữ liệu xe qua API chuẩn Android Automotive (android.car / CarPropertyManager).
 * Dùng reflection để APK vẫn build và chạy được trên máy không có thư viện android.car
 * (điện thoại, máy ảo) – khi đó chỉ đơn giản là không có dữ liệu.
 */
class CarPropertySource(
    private val context: Context,
    private val profile: VehicleProfile,
    private val onUpdate: (VehicleData?) -> Unit,
) {
    private val handler = Handler(Looper.getMainLooper())
    private var car: Any? = null
    private var propertyManager: Any? = null
    private var fuelCapacityMl: Float = 0f
    private var running = false

    private val poll = object : Runnable {
        override fun run() {
            if (!running) return
            onUpdate(read())
            handler.postDelayed(this, POLL_MS)
        }
    }

    fun start() {
        running = true
        try {
            val carClass = Class.forName("android.car.Car")
            val conn = object : ServiceConnection {
                override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                    onConnected()
                }
                override fun onServiceDisconnected(name: ComponentName?) {
                    propertyManager = null
                }
            }
            car = carClass
                .getMethod("createCar", Context::class.java, ServiceConnection::class.java)
                .invoke(null, context, conn)
            carClass.getMethod("connect").invoke(car)
        } catch (t: Throwable) {
            Log.w(TAG, "android.car không khả dụng: $t")
            onUpdate(null)
        }
    }

    fun stop() {
        running = false
        handler.removeCallbacks(poll)
        try {
            car?.javaClass?.getMethod("disconnect")?.invoke(car)
        } catch (_: Throwable) {
        }
        car = null
        propertyManager = null
    }

    private fun onConnected() {
        try {
            propertyManager = car!!.javaClass
                .getMethod("getCarManager", String::class.java)
                .invoke(car, "property")
            // Ưu tiên dung tích người dùng nhập trong Cài đặt, rồi tới xe báo, cuối cùng là profile
            val userMl = vn.tht.launcher.settings.Prefs(context).tankLiters * 1000f
            fuelCapacityMl = userMl.takeIf { it > 0f } ?: float(INFO_FUEL_CAPACITY) ?: profile.fallbackFuelCapacityMl
            handler.post(poll)
        } catch (t: Throwable) {
            Log.w(TAG, "Không lấy được CarPropertyManager: $t")
            onUpdate(null)
        }
    }

    private fun read(): VehicleData? {
        if (propertyManager == null) return null
        val fuelMl = float(FUEL_LEVEL)
        val fuelPct = if (fuelMl != null && fuelCapacityMl > 0f) {
            (fuelMl / fuelCapacityMl * 100f).coerceIn(0f, 100f)
        } else null
        val batteryWh = float(EV_BATTERY_LEVEL)
        val batteryCap = float(INFO_EV_BATTERY_CAPACITY)
        val batteryPct = if (batteryWh != null && batteryCap != null && batteryCap > 0f) {
            (batteryWh / batteryCap * 100f).coerceIn(0f, 100f)
        } else null

        return VehicleData(
            speedKmh = float(PERF_VEHICLE_SPEED)?.let { kotlin.math.abs(it) * 3.6f },
            gear = int(GEAR_SELECTION),
            fuelPercent = fuelPct,
            fuelLow = bool(FUEL_LEVEL_LOW),
            batteryPercent = batteryPct,
            odometerKm = float(PERF_ODOMETER),
            parkingBrake = bool(PARKING_BRAKE_ON),
            nightMode = bool(NIGHT_MODE),
            outsideTempC = float(ENV_OUTSIDE_TEMPERATURE)?.takeIf { it in -50f..70f },
            rangeKm = float(RANGE_REMAINING)?.let { it / 1000f }?.takeIf { it in 0f..2000f },
            ignition = int(IGNITION_STATE),
            rpm = float(ENGINE_RPM),
            fuelLiters = fuelMl?.let { it / 1000f }?.takeIf { it in 0f..200f },
            coolantTempC = float(ENGINE_COOLANT_TEMP)?.takeIf { it in -40f..150f },
        )
    }

    private fun call(name: String, prop: Int): Any? = try {
        propertyManager!!.javaClass
            .getMethod(name, Int::class.javaPrimitiveType, Int::class.javaPrimitiveType)
            .invoke(propertyManager, prop, 0)
    } catch (_: Throwable) {
        null // thuộc tính không có trên xe này hoặc thiếu quyền
    }

    private fun float(prop: Int) = call("getFloatProperty", prop) as? Float
    private fun int(prop: Int) = call("getIntProperty", prop) as? Int
    private fun bool(prop: Int) = call("getBooleanProperty", prop) as? Boolean

    companion object {
        private const val TAG = "CarPropertySource"
        private const val POLL_MS = 500L

        // VehiclePropertyIds – đã đối chiếu với dump car_service của Coolray 2025
        const val INFO_FUEL_CAPACITY = 0x11600104
        const val INFO_EV_BATTERY_CAPACITY = 0x11600106
        const val PERF_ODOMETER = 0x11600204
        const val PERF_VEHICLE_SPEED = 0x11600207
        const val FUEL_LEVEL = 0x11600307
        const val EV_BATTERY_LEVEL = 0x11600309
        const val GEAR_SELECTION = 0x11400400
        const val PARKING_BRAKE_ON = 0x11200402
        const val FUEL_LEVEL_LOW = 0x11200405
        const val NIGHT_MODE = 0x11200407
        // Chuẩn VHAL; chưa thấy trong dump Coolray 2025 nên có thể không có trên xe (khi đó null)
        const val ENV_OUTSIDE_TEMPERATURE = 0x11600703  // °C
        const val RANGE_REMAINING = 0x11600308          // mét
        const val IGNITION_STATE = 0x11400409
        const val ENGINE_RPM = 0x11600305
        const val ENGINE_COOLANT_TEMP = 0x11600301      // °C
    }
}
