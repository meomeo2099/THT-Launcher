package vn.tht.launcher.split

/** Screen-space geometry includes OEM/launcher left insets and keeps both panes above the dock. */
internal object SplitGeometry {
    data class Bounds(val left: Int, val top: Int, val right: Int, val bottom: Int) {
        val width get() = right - left
        val height get() = bottom - top
    }
    data class Layout(val left: Bounds, val right: Bounds) { val divider get() = left.right }
    fun layout(area: Bounds, ratio: Int, dockTop: Int): Layout? {
        val bottom = minOf(area.bottom, dockTop)
        if (area.width < 80 || bottom - area.top < 40) return null
        val x = area.left + (area.width.toLong() * ratio.coerceIn(20, 80) / 100).toInt()
        // Keep the primary stack anchored to the physical left edge. The stable left inset
        // changes the divider ratio; Android applies that inset to the app's content itself.
        return Layout(Bounds(0, area.top, x, bottom), Bounds(x, area.top, area.right, bottom))
    }
    fun ratio(area: Bounds, divider: Int): Int =
        if (area.width <= 0) 50 else ((divider - area.left).toLong() * 100 / area.width).toInt().coerceIn(20, 80)
    fun acceptedStart(result: Any?): Boolean = result is Int && result >= 0
    fun near(actual: Bounds, expected: Bounds, tolerance: Int): Boolean =
        listOf(actual.left.toLong() - expected.left, actual.top.toLong() - expected.top,
            actual.right.toLong() - expected.right, actual.bottom.toLong() - expected.bottom)
            .all { kotlin.math.abs(it) <= tolerance.coerceAtLeast(0) }
}
