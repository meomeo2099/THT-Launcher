package vn.tht.launcher.ui

import android.view.View
import vn.tht.launcher.system.NavAssistService

/** Reserve only the overlay portion that is not already outside this window (e.g. overscan). */
object ContentInsets {
    fun bind(view: View) {
        val l = view.paddingLeft; val t = view.paddingTop
        val r = view.paddingRight; val b = view.paddingBottom
        val update = Runnable {
            val inset = NavAssistService.contentInsetLeft(view)
            val top = view.rootWindowInsets?.systemWindowInsetTop ?: 0
            val bottom = maxOf(b, vn.tht.launcher.shortcuts.FloatingDock.coveredBottom(view))
            if (view.paddingLeft != l + inset || view.paddingTop != t + top || view.paddingBottom != bottom)
                view.setPadding(l + inset, t + top, r, bottom)
        }
        view.viewTreeObserver.addOnGlobalLayoutListener { update.run() }
        listOf(150L, 500L, 1500L).forEach { view.postDelayed(update, it) }
    }
}
