package vn.tht.launcher.vehicle

data class VehicleData(
    val speedKmh: Float? = null,
    val gear: Int? = null,
    val fuelPercent: Float? = null,
    val fuelLow: Boolean? = null,
    val batteryPercent: Float? = null,
    val odometerKm: Float? = null,
    val parkingBrake: Boolean? = null,
    val nightMode: Boolean? = null,
    val outsideTempC: Float? = null,
    val rangeKm: Float? = null,
    /** VehicleIgnitionState: 3 ACC, 4 ON, 5 START; null = không đọc được. */
    val ignition: Int? = null,
    val rpm: Float? = null,
    val coolantTempC: Float? = null,
) {
    /**
     * Máy đang nổ: ưu tiên khoá điện, rồi vòng tua, cuối cùng là xe đang chạy.
     * null = không đủ dữ liệu để biết.
     */
    val engineOn: Boolean?
        get() = when {
            ignition != null -> ignition >= IGNITION_ON
            rpm != null -> rpm > 300f
            speedKmh != null && speedKmh > 1f -> true
            else -> null
        }

    val gearText: String
        get() = when (gear) {
            null -> "-"
            GEAR_PARK -> "P"
            GEAR_REVERSE -> "R"
            GEAR_NEUTRAL -> "N"
            GEAR_DRIVE -> "D"
            else -> if (gear >= GEAR_1) "M" else "-"
        }

    companion object {
        // android.car.VehicleGear
        const val GEAR_NEUTRAL = 0x1
        const val GEAR_REVERSE = 0x2
        const val GEAR_PARK = 0x4
        const val GEAR_DRIVE = 0x8
        const val GEAR_1 = 0x10
        const val IGNITION_ON = 4
    }
}
