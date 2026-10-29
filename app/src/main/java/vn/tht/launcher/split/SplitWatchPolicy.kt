package vn.tht.launcher.split

/** Wait for native animation to settle; bound repairs if firmware keeps rejecting our geometry. */
internal class SplitWatchPolicy {
    enum class Action { WAIT, REFIT, CLOSE }
    private var previous: SplitGeometry.Layout? = null
    private var target: SplitGeometry.Layout? = null
    private var attempts = 0
    fun reset() { previous = null; target = null; attempts = 0 }
    fun observe(actual: SplitGeometry.Layout, expected: SplitGeometry.Layout, tolerance: Int, dragging: Boolean): Action {
        if (dragging) { reset(); return Action.WAIT }
        if (expected != target) { previous = null; attempts = 0; target = expected }
        if (SplitGeometry.near(actual.left, expected.left, tolerance) && SplitGeometry.near(actual.right, expected.right, tolerance)) {
            attempts = 0; previous = actual; return Action.WAIT
        }
        if (previous != actual) { previous = actual; return Action.WAIT }
        if (attempts >= 3) return Action.CLOSE
        attempts++
        return Action.REFIT
    }
}
