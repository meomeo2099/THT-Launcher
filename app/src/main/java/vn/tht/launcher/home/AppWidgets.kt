package vn.tht.launcher.home

import android.app.Activity
import android.appwidget.AppWidgetHost
import android.appwidget.AppWidgetHostView
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProviderInfo
import android.content.Intent
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import vn.tht.launcher.ui.IconList
import vn.tht.launcher.ui.Ui

/**
 * Nhúng widget Android của app khác (đồng hồ, lịch, Spotify…) lên màn chính.
 * Bản ký platform có sẵn quyền BIND_APPWIDGET nên gắn thẳng; bản thường thì hệ thống hỏi người dùng.
 */
class AppWidgets(private val activity: Activity) {
    private val manager = AppWidgetManager.getInstance(activity)
    private val host = AppWidgetHost(activity, HOST_ID)
    private var pending: Pair<Int, (Int?) -> Unit>? = null

    fun startListening() = runCatching { host.startListening() }.let { }
    fun stopListening() = runCatching { host.stopListening() }.let { }

    /** Chọn widget từ danh sách app đã cài; [done] nhận id widget (null nếu huỷ). */
    fun pick(done: (Int?) -> Unit) {
        val pm = activity.packageManager
        val providers = manager.installedProviders.sortedBy { it.loadLabel(pm).lowercase() }
        if (providers.isEmpty()) { done(null); return }
        val density = activity.resources.displayMetrics.densityDpi
        IconList.show(activity, "Widget Android", providers.map { p ->
            val app = runCatching { pm.getApplicationLabel(pm.getApplicationInfo(p.provider.packageName, 0)).toString() }.getOrNull()
            val size = "${p.minWidth / activity.resources.displayMetrics.density.toInt().coerceAtLeast(1)}×" +
                "${p.minHeight / activity.resources.displayMetrics.density.toInt().coerceAtLeast(1)}"
            IconList.Item(p.loadLabel(pm), drawable = runCatching { p.loadIcon(activity, density) }.getOrNull(),
                summary = listOfNotNull(app, size).joinToString(" · "))
        }, columns = 2) { i -> bind(providers[i], done) }
    }

    private fun bind(info: AppWidgetProviderInfo, done: (Int?) -> Unit) {
        val id = host.allocateAppWidgetId()
        pending = id to done
        val allowed = runCatching { manager.bindAppWidgetIdIfAllowed(id, info.provider) }.getOrDefault(false)
        if (allowed) configure(id, info)
        else {
            // Bản không có quyền hệ thống: Android hỏi người dùng có cho launcher dùng widget này không
            val i = Intent(AppWidgetManager.ACTION_APPWIDGET_BIND)
                .putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, id)
                .putExtra(AppWidgetManager.EXTRA_APPWIDGET_PROVIDER, info.provider)
            runCatching { activity.startActivityForResult(i, REQ_BIND) }.onFailure { finish(false) }
        }
    }

    private fun configure(id: Int, info: AppWidgetProviderInfo) {
        if (info.configure == null) return finish(true)
        val i = Intent(AppWidgetManager.ACTION_APPWIDGET_CONFIGURE).setComponent(info.configure)
            .putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, id)
        runCatching { activity.startActivityForResult(i, REQ_CONFIGURE) }.onFailure { finish(true) }
    }

    /** Gọi từ onActivityResult; true nếu đã xử lý. */
    fun onResult(requestCode: Int, resultCode: Int): Boolean {
        val (id, _) = pending ?: return false
        when (requestCode) {
            REQ_BIND -> if (resultCode == Activity.RESULT_OK) manager.getAppWidgetInfo(id)?.let { configure(id, it) } ?: finish(false) else finish(false)
            REQ_CONFIGURE -> finish(resultCode == Activity.RESULT_OK)
            else -> return false
        }
        return true
    }

    private fun finish(ok: Boolean) {
        val (id, done) = pending ?: return
        pending = null
        if (!ok) runCatching { host.deleteAppWidgetId(id) }
        done(if (ok) id else null)
    }

    /** View của widget đã gắn; null nếu app đã gỡ hoặc widget không còn. */
    fun createView(id: Int): AppWidgetHostView? {
        val info = manager.getAppWidgetInfo(id) ?: return null
        // Context thường (không có bộ tạo view AppCompat của Activity): RemoteViews không chạy với view AppCompat
        val plain = android.view.ContextThemeWrapper(activity.applicationContext, android.R.style.Theme_DeviceDefault)
        return runCatching { host.createView(plain, id, info) }.onFailure { Log.w(TAG, "Không tạo được widget $id: $it") }.getOrNull()
    }

    fun delete(id: Int) = runCatching { host.deleteAppWidgetId(id) }.let { }

    companion object {
        private const val TAG = "AppWidgets"
        private const val HOST_ID = 2048
        const val REQ_BIND = 31
        const val REQ_CONFIGURE = 32
    }
}

/** Ô chứa widget Android; id widget lưu trong [WidgetSpec.pkg]. */
class AndroidAppWidget(host: WidgetHost, spec: WidgetSpec) : HomeWidget(host) {
    private val widgetView = spec.pkg.toIntOrNull()?.let { host.appWidgetView(it) }

    override val view: View = FrameLayout(c).apply {
        if (widgetView != null) {
            addView(widgetView, FrameLayout.LayoutParams(-1, -1))
            // Báo cho widget kích thước ô (dp) để nó chọn bố cục phù hợp
            addOnLayoutChangeListener { v, l, t, r, b, ol, ot, or_, ob ->
                if (r - l != or_ - ol || b - t != ob - ot) {
                    val d = v.resources.displayMetrics.density
                    val w = ((r - l) / d).toInt(); val h = ((b - t) / d).toInt()
                    runCatching { (widgetView as? AppWidgetHostView)?.updateAppWidgetSize(null, w, h, w, h) }
                }
            }
        } else {
            background = Ui.card(c)
            addView(Ui.text(c, "Widget không còn\n(app đã gỡ?) · xoá ô này trong chế độ sửa", 15f, dim = true).apply {
                gravity = Gravity.CENTER
            }, FrameLayout.LayoutParams(-2, -2, Gravity.CENTER))
        }
    }
}
