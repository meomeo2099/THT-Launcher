package vn.tht.launcher.ui

import android.view.View
import android.view.WindowManager

/** WindowManager may add OEM stable insets even with LAYOUT_IN_SCREEN. Use measured screen pixels. */
class ScreenOverlayPosition(private val view: View, private val wm: WindowManager,
                            private val params: WindowManager.LayoutParams) {
    var x = params.x
        private set
    var y = params.y
        private set
    private var correctionX = 0
    private var correctionY = 0
    private var attempts = 0

    init {
        view.viewTreeObserver.addOnGlobalLayoutListener {
            if (view.isAttachedToWindow && attempts < 3) {
                val actual = IntArray(2).also(view::getLocationOnScreen)
                val dx = x - actual[0]; val dy = y - actual[1]
                if (dx != 0 || dy != 0) {
                    correctionX += dx; correctionY += dy; attempts++
                    update()
                }
            }
        }
    }
    fun place(left: Int, top: Int) {
        x = left; y = top; attempts = 0
        update()
    }
    private fun update() {
        params.x = x + correctionX; params.y = y + correctionY
        if (view.isAttachedToWindow) runCatching { wm.updateViewLayout(view, params) }
    }
}
