package vn.tht.launcher.system

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInstaller
import android.content.pm.PackageManager
import android.util.Log
import org.json.JSONObject
import vn.tht.launcher.settings.Prefs
import java.io.File
import java.net.HttpURLConnection
import java.net.URL

/**
 * Tự cập nhật từ GitHub Releases của repo (bản phát hành `build-<số>` do CI tạo, kèm `THT-Launcher-<phiên bản>-release.apk`).
 *
 * An toàn: chỉ cài khi APK tải về cùng package và **cùng chứng chỉ ký** với bản đang cài, và số bản
 * lớn hơn bản hiện tại. Có quyền hệ thống (INSTALL_PACKAGES, bản ký platform) thì cài thẳng;
 * không thì Android hiện màn hình xác nhận.
 */
object Updater {
    private const val TAG = "Updater"
    private const val API = "https://api.github.com/repos/meomeo2099/THT-Launcher/releases/latest"
    private const val KEY_LAST_CHECK = "update_last_check"
    const val KEY_AUTO = "update_auto"

    data class Release(val build: Long, val name: String, val url: String, val notes: String)

    fun currentBuild(c: Context): Long = runCatching {
        @Suppress("DEPRECATION")
        c.packageManager.getPackageInfo(c.packageName, 0).versionCode.toLong()
    }.getOrDefault(0L)

    /** Bản mới nhất trên GitHub (null nếu lỗi mạng / chưa có). Chạy trên luồng nền. */
    fun latest(): Release? = runCatching {
        val conn = URL(API).openConnection() as HttpURLConnection
        conn.setRequestProperty("Accept", "application/vnd.github+json")
        conn.connectTimeout = 10_000; conn.readTimeout = 10_000
        val j = JSONObject(conn.inputStream.use { it.readBytes().toString(Charsets.UTF_8) })
        val tag = j.getString("tag_name")
        val build = tag.substringAfter("build-").toLongOrNull() ?: return@runCatching null
        val assets = j.getJSONArray("assets")
        val url = (0 until assets.length()).map { assets.getJSONObject(it) }
            .firstOrNull { a ->
                val n = a.getString("name")
                n.endsWith(".apk") && !n.contains("debug") && (n.startsWith("THT-Launcher") || n == "app-release.apk")
            }?.getString("browser_download_url") ?: return@runCatching null
        Release(build, j.optString("name", tag), url, j.optString("body"))
    }.onFailure { Log.w(TAG, "Không kiểm tra được cập nhật: $it") }.getOrNull()

    /** Có bản mới hơn bản đang cài? */
    fun check(c: Context): Release? {
        Prefs(c).sp.edit().putLong(KEY_LAST_CHECK, System.currentTimeMillis()).apply()
        val r = latest() ?: return null
        return r.takeIf { it.build > currentBuild(c) }
    }

    /** Tự kiểm tra tối đa một lần mỗi ngày nếu người dùng bật. */
    fun shouldAutoCheck(c: Context): Boolean {
        val sp = Prefs(c).sp
        return sp.getBoolean(KEY_AUTO, true) &&
            System.currentTimeMillis() - sp.getLong(KEY_LAST_CHECK, 0L) > 24 * 3600_000L
    }

    /**
     * Tải và cài [r]. [progress] nhận phần trăm. Trả null nếu đã gửi lệnh cài, ngược lại là lỗi.
     * Chạy trên luồng nền.
     */
    fun downloadAndInstall(c: Context, r: Release, progress: (Int) -> Unit): String? {
        val file = File(c.cacheDir, "update.apk")
        try {
            val conn = URL(r.url).openConnection() as HttpURLConnection
            conn.instanceFollowRedirects = true
            conn.connectTimeout = 15_000; conn.readTimeout = 30_000
            val total = conn.contentLengthLong
            conn.inputStream.use { input ->
                file.outputStream().use { out ->
                    val buf = ByteArray(64 * 1024)
                    var done = 0L
                    while (true) {
                        val n = input.read(buf)
                        if (n < 0) break
                        out.write(buf, 0, n)
                        done += n
                        if (total > 0) progress((done * 100 / total).toInt())
                    }
                }
            }
        } catch (t: Throwable) {
            return "Tải bản cập nhật lỗi: ${t.message}"
        }
        verify(c, file)?.let { file.delete(); return it }
        return install(c, file)
    }

    /** Cùng package, cùng chứng chỉ, số bản lớn hơn. null = hợp lệ. */
    @Suppress("DEPRECATION")
    private fun verify(c: Context, apk: File): String? {
        val pm = c.packageManager
        val info = pm.getPackageArchiveInfo(apk.path, PackageManager.GET_SIGNATURES) ?: return "File cập nhật hỏng"
        if (info.packageName != c.packageName) return "File cập nhật không phải THT Launcher"
        if (info.versionCode.toLong() <= currentBuild(c)) return "Bản tải về không mới hơn bản đang cài"
        val mine = pm.getPackageInfo(c.packageName, PackageManager.GET_SIGNATURES).signatures.map { it.toCharsString() }.toSet()
        val theirs = info.signatures.orEmpty().map { it.toCharsString() }.toSet()
        if (theirs.isEmpty() || theirs != mine) return "Bản cập nhật ký bằng khoá khác bản đang cài – không cài"
        return null
    }

    private fun install(c: Context, apk: File): String? = try {
        val installer = c.packageManager.packageInstaller
        val params = PackageInstaller.SessionParams(PackageInstaller.SessionParams.MODE_FULL_INSTALL).apply {
            setAppPackageName(c.packageName)
        }
        val id = installer.createSession(params)
        installer.openSession(id).use { s ->
            s.openWrite("base.apk", 0, apk.length()).use { out -> apk.inputStream().use { it.copyTo(out) }; s.fsync(out) }
            val pi = PendingIntent.getBroadcast(c, id, Intent(c, InstallResultReceiver::class.java), PendingIntent.FLAG_UPDATE_CURRENT)
            s.commit(pi.intentSender)
        }
        null
    } catch (t: Throwable) {
        "Không cài được: ${t.message}"
    }
}

/** Nhận kết quả cài: cần người dùng xác nhận thì mở màn hình xác nhận của Android. */
class InstallResultReceiver : BroadcastReceiver() {
    override fun onReceive(c: Context, i: Intent) {
        when (val status = i.getIntExtra(PackageInstaller.EXTRA_STATUS, PackageInstaller.STATUS_FAILURE)) {
            PackageInstaller.STATUS_PENDING_USER_ACTION -> {
                @Suppress("DEPRECATION")
                val confirm = i.getParcelableExtra<Intent>(Intent.EXTRA_INTENT) ?: return
                c.startActivity(confirm.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
            }
            PackageInstaller.STATUS_SUCCESS -> Log.i("Updater", "Đã cài bản cập nhật")
            else -> Log.w("Updater", "Cài lỗi ($status): ${i.getStringExtra(PackageInstaller.EXTRA_STATUS_MESSAGE)}")
        }
    }
}
