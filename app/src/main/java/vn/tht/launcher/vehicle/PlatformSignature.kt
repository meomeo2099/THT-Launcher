package vn.tht.launcher.vehicle

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import java.security.MessageDigest

/**
 * Xác minh APK này ký cùng chứng chỉ với firmware đầu xe (chứng chỉ của gói hệ thống "android").
 *
 * Đây là điều kiện để mở phần GHI/điều khiển xe: chỉ khi khớp, đầu xe mới cấp các quyền mức
 * platform (CONTROL_CAR_CLIMATE, CAR_VENDOR_EXTENSION…). Kiểm tra ngay trên chính đầu xe đang chạy,
 * nên không phụ thuộc vào việc đoán firmware dùng khoá nào.
 */
object PlatformSignature {
    private const val TAG = "PlatformSignature"

    @Volatile
    private var cached: Boolean? = null

    fun verify(context: Context): Boolean = cached ?: compute(context).also { cached = it }

    private fun compute(context: Context): Boolean {
        val pm = context.packageManager
        val result = try {
            pm.checkSignatures("android", context.packageName) == PackageManager.SIGNATURE_MATCH
        } catch (t: Throwable) {
            Log.w(TAG, "checkSignatures lỗi: $t")
            false
        }
        Log.i(
            TAG, "Chữ ký platform ${if (result) "KHỚP" else "KHÔNG khớp"} " +
                "(firmware=${certSha256(context, "android")}, app=${certSha256(context, context.packageName)})"
        )
        return result
    }

    /** SHA-256 chứng chỉ đầu tiên của gói, để in log đối chiếu. */
    @Suppress("DEPRECATION")
    fun certSha256(context: Context, pkg: String): String = try {
        val sig = context.packageManager
            .getPackageInfo(pkg, PackageManager.GET_SIGNATURES)
            .signatures.first()
        MessageDigest.getInstance("SHA-256").digest(sig.toByteArray())
            .joinToString("") { "%02x".format(it) }
    } catch (t: Throwable) {
        "?"
    }
}
