package vn.tht.launcher.vehicle

/**
 * Danh mục function ID của ECARX AdaptAPI (ICarFunction).
 *
 * Các ID dưới đây là DỮ LIỆU THAM KHẢO, suy ra từ hành vi đầu ECARX; không phải mọi xe đều có.
 * Luôn kiểm chứng bằng [EcarxCarSource.dumpSupported] trên chính xe trước khi tin dùng:
 * đầu xe trả về "active" nghĩa là có, "notactive"/"unknown" nghĩa là không hỗ trợ.
 *
 * Quy ước ID của ECARX: 4 byte, byte cao là nhóm chức năng.
 */
object EcarxFunctions {

    /** Một function ID kèm tên người đọc được, để in khi dò. */
    data class Fn(val id: Int, val name: String)

    // ----- Điều hoà (HVAC) -----
    const val HVAC_POWER = 0x10010100          // bật/tắt điều hoà
    const val HVAC_AC = 0x10010300             // A/C
    const val HVAC_AC_MAX = 0x10010400         // A/C MAX
    const val HVAC_FAN_SPEED = 0x10020100      // mức quạt
    const val HVAC_AUTO = 0x10030100           // chế độ AUTO
    const val HVAC_DEFROST_FRONT = 0x10040100  // sấy kính trước
    const val HVAC_DEFROST_REAR = 0x10040300   // sấy kính sau
    const val HVAC_SEAT = 0x10050200           // sưởi/thông gió ghế
    const val HVAC_TEMP_ZONE = 0x10060100      // nhiệt độ đặt theo vùng
    const val HVAC_TEMP_UNIT = 0x10060600      // đơn vị nhiệt (°C/°F)
    const val HVAC_SYNC = 0x10060500           // đồng bộ nhiệt 2 vùng
    const val HVAC_AIRFLOW = 0x10070100        // hướng gió
    const val HVAC_RECIRC = 0x10090100         // lấy gió trong/ngoài
    const val HVAC_AIR_CLEAN = 0x10100300      // lọc khí
    const val HVAC_AC_ECO = 0x28070400         // AC eco

    // ----- Trạng thái / thông tin xe -----
    const val POWER_MODE = 0x20030600          // chế độ nguồn (0x20030602 = ACC)
    const val RANGE_REMAINING = 0x20150300     // quãng đường còn lại
    const val FUEL_OR_BATTERY = 0x20150400     // mức nhiên liệu / pin
    const val TEMP_OUTSIDE_A = 0x20150500      // nhiệt độ ngoài (biến thể A)
    const val TEMP_OUTSIDE_B = 0x20150600      // nhiệt độ ngoài (biến thể B)

    // ----- Đèn nội thất (ambient) -----
    const val AMBIENT_SWITCH = 0x21030000      // bật/tắt đèn ambient
    const val AMBIENT_BRIGHTNESS = 0x21030300  // độ sáng
    const val AMBIENT_COLOR = 0x21030400       // màu

    // ----- Chế độ lái -----
    const val DRIVE_MODE = 0x22010100          // eco / comfort / sport / offroad

    /** Giá trị của POWER_MODE. */
    const val POWER_MODE_ACC = 0x20030602

    /**
     * Toàn bộ ID để dò. Thứ tự nhóm giống trên để log dễ đọc.
     * Giữ danh sách này đồng bộ với các hằng số ở trên.
     */
    val ALL: List<Fn> = listOf(
        Fn(HVAC_POWER, "HVAC_POWER"),
        Fn(HVAC_AC, "HVAC_AC"),
        Fn(HVAC_AC_MAX, "HVAC_AC_MAX"),
        Fn(HVAC_FAN_SPEED, "HVAC_FAN_SPEED"),
        Fn(HVAC_AUTO, "HVAC_AUTO"),
        Fn(HVAC_DEFROST_FRONT, "HVAC_DEFROST_FRONT"),
        Fn(HVAC_DEFROST_REAR, "HVAC_DEFROST_REAR"),
        Fn(HVAC_SEAT, "HVAC_SEAT"),
        Fn(HVAC_TEMP_ZONE, "HVAC_TEMP_ZONE"),
        Fn(HVAC_TEMP_UNIT, "HVAC_TEMP_UNIT"),
        Fn(HVAC_SYNC, "HVAC_SYNC"),
        Fn(HVAC_AIRFLOW, "HVAC_AIRFLOW"),
        Fn(HVAC_RECIRC, "HVAC_RECIRC"),
        Fn(HVAC_AIR_CLEAN, "HVAC_AIR_CLEAN"),
        Fn(HVAC_AC_ECO, "HVAC_AC_ECO"),
        Fn(POWER_MODE, "POWER_MODE"),
        Fn(RANGE_REMAINING, "RANGE_REMAINING"),
        Fn(FUEL_OR_BATTERY, "FUEL_OR_BATTERY"),
        Fn(TEMP_OUTSIDE_A, "TEMP_OUTSIDE_A"),
        Fn(TEMP_OUTSIDE_B, "TEMP_OUTSIDE_B"),
        Fn(AMBIENT_SWITCH, "AMBIENT_SWITCH"),
        Fn(AMBIENT_BRIGHTNESS, "AMBIENT_BRIGHTNESS"),
        Fn(AMBIENT_COLOR, "AMBIENT_COLOR"),
        Fn(DRIVE_MODE, "DRIVE_MODE"),
    )

    fun hex(id: Int): String = "0x%08X".format(id)
}
