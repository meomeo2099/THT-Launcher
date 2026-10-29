package vn.tht.launcher.home

import android.app.ActivityView
import android.content.Context
import android.view.View

/** Isolated linkage: devices without this hidden API fall back through EmbeddedMap.create(). */
internal object PlatformActivityView {
    fun create(context: Context, ready: () -> Unit, destroyed: () -> Unit): View =
        ActivityView(context).apply {
            isFocusable = false
            isFocusableInTouchMode = false
            descendantFocusability = android.view.ViewGroup.FOCUS_BLOCK_DESCENDANTS
            setCallback(object : ActivityView.StateCallback() {
                override fun onActivityViewReady(view: ActivityView) = ready()
                override fun onActivityViewDestroyed(view: ActivityView) = destroyed()
            })
        }
}
