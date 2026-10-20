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
) {
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
    }
}
