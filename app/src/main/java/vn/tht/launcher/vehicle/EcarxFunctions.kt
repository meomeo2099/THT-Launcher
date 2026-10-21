package vn.tht.launcher.vehicle

/**
 * Function ID của ECARX AdaptAPI 1.1.18.9 (`ICarFunction`) mà launcher dùng.
 *
 * Nguồn: Coolray2025-Voice-Spec.md, phụ lục A, sinh tự động từ `AdapterAPI.jar` trên chính đầu xe
 * Coolray 2025. Có ID trong catalog chưa có nghĩa là xe có function đó: luôn kiểm tra bằng
 * `isFunctionSupported(id, zone)` (xem [EcarxCarSource.dumpSupported]).
 *
 * Quy ước giá trị: bật/tắt = [ON]/[OFF] (tuyệt đối); giá trị enum = `ID + n` (xem [level]).
 */
object EcarxFunctions {

    data class Fn(val id: Int, val name: String, val zone: Int = ZONE_ALL)

    const val OFF = 0
    const val ON = 1

    /** Giá trị enum `id + n`; n <= 0 nghĩa là tắt (0 tuyệt đối). */
    fun level(id: Int, n: Int): Int = if (n <= 0) OFF else id + n

    /** Ngược của [level]: 0 -> 0, `id + n` -> n; một số firmware trả thẳng n nhỏ. */
    fun levelOf(id: Int, value: Int?): Int? = when {
        value == null -> null
        value == OFF -> 0
        value in (id + 1)..(id + 0xFF) -> value - id
        value in 1..15 -> value
        else -> null
    }

    // ----- Vùng (com.ecarx.xui.adaptapi.vehicle) -----
    const val ZONE_ALL = Int.MIN_VALUE
    const val ZONE_ROW_1_LEFT = 1
    const val ZONE_ROW_1_RIGHT = 4

    // ----- Điều hoà: car.hvac.IHvac -----
    const val HVAC_POWER = 268501248
    const val HVAC_AUTO = 268501504
    const val HVAC_AC = 268501760
    const val HVAC_AC_MAX = 268502016
    const val HVAC_FAN_SPEED = 268566784         // +1..+7 trên Coolray (VHAL 1–7)
    const val HVAC_CIRCULATION = 268632320       // +1 gió trong, +2 gió ngoài, +3 tự động
    const val HVAC_DEFROST_FRONT = 268697856
    const val HVAC_DEFROST_REAR = 268698368
    const val HVAC_TEMP = 268828928              // float (customize), 16–32 °C
    const val HVAC_TEMP_STEP = 268829696
    const val HVAC_TEMP_DUAL = 268829952
    const val HVAC_BLOWING_MODE = 268894464      // +1 mặt, +2 chân, +3 mặt+chân, +4 kính
    const val HVAC_DEFROST_FRONT_MAX = 268698112
    const val HVAC_SEAT_VENTILATION = 268763392  // zone trái/phải, +1..+3
    const val HVAC_SEAT_HEATING = 268763648      // zone trái/phải, +1..+3
    const val HVAC_STEERING_WHEEL_HEAT = 269025536 // +1 thấp, +2 vừa, +3 cao
    const val HVAC_ECO = 268960000
    const val HVAC_IONS = 268961024
    const val HVAC_G_CLEAN = 269485056
    const val HVAC_AUTO_DRY = 269485312
    const val HVAC_PM25 = 269811968
    const val HVAC_FRAGRANCE = 269156608
    const val HVAC_FRAGRANCE_LEVEL = 269157120   // +1..+3

    val CIRCULATION_MODES = listOf(1 to "Gió trong", 2 to "Gió ngoài", 3 to "Tự động")
    val BLOWING_MODES = listOf(1 to "Mặt", 2 to "Chân", 3 to "Mặt + chân", 4 to "Kính", 6 to "Chân + kính")

    const val FAN_MIN = 1
    const val FAN_MAX = 7
    const val TEMP_MIN = 16f
    const val TEMP_MAX = 32f

    // ----- Thân xe: car.vehicle.IBcm / IVehicle -----
    const val BCM_WINDOW = 553844992             // ON mở / OFF đóng, +1 dừng, +2 nửa
    const val BCM_SUNROOF_TILT = 553845760
    const val BCM_DOOR = 553779456
    const val BCM_FOLD_MIRROR = 554041600
    const val SUN_CURTAIN_CLOSE = 537395456      // ON đóng rèm / OFF mở rèm
    const val WINDOW_VENTILATE = 537396736
    const val ZONE_ROOF = 4                      // WINDOW_ROOF_TOP
    const val ZONE_DOOR_REAR = 536870912         // DOOR_REAR (cốp)
    const val WINDOW_ALL = 16 or 32 or 256 or 512

    // ----- Đèn ngoài / trong -----
    const val READING_LIGHT = 553980672
    const val DRL = 537135104
    const val WELCOME_LIGHT = 721617152
    const val AUTO_HIGH_BEAM = 721486080

    // ----- Màn hình -----
    const val BACKLIGHT = 687997184
    const val BACKLIGHT_MAX = 687997440
    const val BACKLIGHT_MIN = 687997696
    const val BACKLIGHT_STEP = 687997952
    const val DAYMODE = 538247424                // +1 ngày, +2 đêm, +3 tự động
    val DAYMODES = listOf(1 to "Ngày", 2 to "Đêm", 3 to "Tự động")

    // ----- Đèn viền: car.vehicle.IBcm / IAmbienceLight -----
    const val AMBIENT_SWITCH = 553979904         // BCM_FUNC_LIGHT_ATMOSPHERE_LAMPS
    const val AMBIENT_THEME_COLOR = 704709120    // +1 đỏ … +8 trắng
    const val AMBIENT_INTENSITY = 704708864      // float (customize)
    const val AMBIENT_MUSIC = 704972800          // +1 sôi động, +2 thường, +3 nhẹ
    val AMBIENT_MUSIC_MODES = listOf(1 to "Sôi động", 2 to "Thường", 3 to "Nhẹ")
    const val VOICE_LIGHT_MODE = 705103104       // hiệu ứng đèn theo giọng nói

    /** Màu đèn viền: n trong `AMBIENT_THEME_COLOR + n` -> tên + mã màu hiển thị. */
    val AMBIENT_COLORS: List<Triple<Int, String, Int>> = listOf(
        Triple(1, "Đỏ", 0xFFE53935.toInt()),
        Triple(2, "Cam", 0xFFFB8C00.toInt()),
        Triple(3, "Vàng", 0xFFFDD835.toInt()),
        Triple(4, "Xanh lá", 0xFF43A047.toInt()),
        Triple(5, "Chàm", 0xFF3949AB.toInt()),
        Triple(6, "Xanh dương", 0xFF1E88E5.toInt()),
        Triple(7, "Tím", 0xFF8E24AA.toInt()),
        Triple(8, "Trắng", 0xFFFFFFFF.toInt()),
    )

    // ----- Chế độ lái: car.vehicle.IDriveMode -----
    const val DRIVE_MODE = 570491136             // +1 eco, +2 comfort, +3 sport (dynamic)

    val DRIVE_MODES: List<Pair<Int, String>> = listOf(1 to "Eco", 2 to "Comfort", 3 to "Sport")
    /** Thêm các chế độ có thể có trên một số bản xe; chỉ hiện khi getSupportedFunctionValue có. */
    val DRIVE_MODES_EXTRA: List<Pair<Int, String>> = listOf(9 to "Tuyết", 19 to "Off-road", 21 to "Sport+", 22 to "Thích ứng")

    /** Toàn bộ ID để dò. Giữ đồng bộ với các hằng số ở trên. */
    val ALL: List<Fn> = listOf(
        Fn(HVAC_POWER, "HVAC_POWER"),
        Fn(HVAC_AUTO, "HVAC_AUTO"),
        Fn(HVAC_AC, "HVAC_AC"),
        Fn(HVAC_AC_MAX, "HVAC_AC_MAX"),
        Fn(HVAC_FAN_SPEED, "HVAC_FAN_SPEED"),
        Fn(HVAC_CIRCULATION, "HVAC_CIRCULATION"),
        Fn(HVAC_DEFROST_FRONT, "HVAC_DEFROST_FRONT"),
        Fn(HVAC_DEFROST_REAR, "HVAC_DEFROST_REAR"),
        Fn(HVAC_TEMP, "HVAC_TEMP (ALL)"),
        Fn(HVAC_TEMP, "HVAC_TEMP (LEFT)", ZONE_ROW_1_LEFT),
        Fn(HVAC_TEMP, "HVAC_TEMP (RIGHT)", ZONE_ROW_1_RIGHT),
        Fn(HVAC_TEMP_STEP, "HVAC_TEMP_STEP"),
        Fn(HVAC_TEMP_DUAL, "HVAC_TEMP_DUAL"),
        Fn(HVAC_BLOWING_MODE, "HVAC_BLOWING_MODE"),
        Fn(AMBIENT_SWITCH, "AMBIENT_SWITCH"),
        Fn(AMBIENT_THEME_COLOR, "AMBIENT_THEME_COLOR"),
        Fn(AMBIENT_INTENSITY, "AMBIENT_INTENSITY"),
        Fn(DRIVE_MODE, "DRIVE_MODE"),
        Fn(HVAC_DEFROST_FRONT_MAX, "HVAC_DEFROST_FRONT_MAX"),
        Fn(HVAC_SEAT_HEATING, "SEAT_HEAT (LEFT)", ZONE_ROW_1_LEFT),
        Fn(HVAC_SEAT_HEATING, "SEAT_HEAT (RIGHT)", ZONE_ROW_1_RIGHT),
        Fn(HVAC_SEAT_VENTILATION, "SEAT_VENT (LEFT)", ZONE_ROW_1_LEFT),
        Fn(HVAC_SEAT_VENTILATION, "SEAT_VENT (RIGHT)", ZONE_ROW_1_RIGHT),
        Fn(HVAC_STEERING_WHEEL_HEAT, "WHEEL_HEAT"),
        Fn(HVAC_ECO, "HVAC_ECO"),
        Fn(HVAC_IONS, "HVAC_IONS"),
        Fn(HVAC_G_CLEAN, "HVAC_G_CLEAN"),
        Fn(HVAC_AUTO_DRY, "HVAC_AUTO_DRY"),
        Fn(HVAC_PM25, "HVAC_PM25"),
        Fn(HVAC_FRAGRANCE, "HVAC_FRAGRANCE"),
        Fn(BCM_WINDOW, "SUNROOF", ZONE_ROOF),
        Fn(BCM_WINDOW, "WINDOWS (ALL)", WINDOW_ALL),
        Fn(BCM_SUNROOF_TILT, "SUNROOF_TILT", ZONE_ROOF),
        Fn(SUN_CURTAIN_CLOSE, "SUN_CURTAIN"),
        Fn(WINDOW_VENTILATE, "WINDOW_VENTILATE"),
        Fn(BCM_DOOR, "TRUNK", ZONE_DOOR_REAR),
        Fn(BCM_FOLD_MIRROR, "FOLD_MIRROR"),
        Fn(READING_LIGHT, "READING_LIGHT"),
        Fn(DRL, "DRL"),
        Fn(WELCOME_LIGHT, "WELCOME_LIGHT"),
        Fn(AUTO_HIGH_BEAM, "AUTO_HIGH_BEAM"),
        Fn(BACKLIGHT, "BACKLIGHT"),
        Fn(DAYMODE, "DAYMODE"),
        Fn(AMBIENT_MUSIC, "AMBIENT_MUSIC"),
        Fn(VOICE_LIGHT_MODE, "VOICE_LIGHT_MODE"),
    )

    fun hex(id: Int): String = "0x%08X".format(id)
}
