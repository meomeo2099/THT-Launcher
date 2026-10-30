package vn.tht.launcher.vehicle

import android.os.Build
import java.util.Locale

/**
 * Mỗi đời xe/đầu xe là một profile. Phần lõi của launcher dùng chung,
 * chỉ những gì khác nhau giữa các xe mới nằm ở đây.
 */
data class VehicleProfile(
    val id: String,
    val displayName: String,
    /** Các giá trị Build.MODEL / Build.PRODUCT nhận diện đầu xe. */
    val models: Set<String>,
    val products: Set<String> = emptySet(),
    /** Dung tích bình xăng (ml) dự phòng khi xe không báo INFO_FUEL_CAPACITY. 0 = không rõ. */
    val fallbackFuelCapacityMl: Float = 0f,
    /** Xe điện: hiển thị % pin thay cho xăng. */
    val isEv: Boolean = false,
) {
    fun matches(): Boolean = matches(Build.MODEL, Build.PRODUCT, Build.DEVICE)

    /** Pure matcher for diagnostics/tests. Package presence alone never identifies a car. */
    fun matches(model: String, product: String, device: String): Boolean =
        models.any { hardwareId(it) == hardwareId(model) } ||
            products.any { hardwareId(it) == hardwareId(product) || hardwareId(it) == hardwareId(device) }

    private fun hardwareId(value: String) = value.trim().uppercase(Locale.ROOT)
}

object Profiles {

    /** Coolray 2025 – đầu ECARX SX11-LA / IHU730P, Android 9, màn 1920x720 @160dpi. */
    val COOLRAY_2025 = VehicleProfile(
        id = "coolray_2025",
        displayName = "Geely Coolray 2025",
        models = setOf("SX11-LA"),
        products = setOf("IHU730P"),
    )

    /** EX2 – điền MODEL/PRODUCT sau khi lấy `adb shell getprop` trên xe. */
    val EX2 = VehicleProfile(
        id = "ex2",
        displayName = "Geely EX2",
        models = emptySet(),
        isEv = true,
    )

    val GENERIC = VehicleProfile(
        id = "generic",
        displayName = "Android (chung)",
        models = emptySet(),
    )

    private val all = listOf(COOLRAY_2025, EX2)

    fun detect(): VehicleProfile = detect(Build.MODEL, Build.PRODUCT, Build.DEVICE)

    /** OEM APKs can be installed elsewhere; retain explicit hardware identification. */
    fun detect(model: String, product: String, device: String): VehicleProfile =
        all.firstOrNull { it.matches(model, product, device) } ?: GENERIC
}
