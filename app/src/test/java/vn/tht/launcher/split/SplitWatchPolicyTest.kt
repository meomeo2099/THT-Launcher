package vn.tht.launcher.split

import org.junit.Assert.*
import org.junit.Test

class SplitWatchPolicyTest {
    private val expected = SplitGeometry.layout(SplitGeometry.Bounds(0, 0, 1920, 720), 50, 572)!!
    private val fullHeight = SplitGeometry.layout(SplitGeometry.Bounds(0, 0, 1920, 720), 50, 720)!!
    @Test fun nativeBoundsMustBeStableBeforeRefittingAboveDock() {
        val policy = SplitWatchPolicy()
        assertEquals(SplitWatchPolicy.Action.WAIT, policy.observe(fullHeight, expected, 12, false))
        assertEquals(SplitWatchPolicy.Action.REFIT, policy.observe(fullHeight, expected, 12, false))
    }
    @Test fun repeatedFirmwareRejectionEventuallyRequestsCleanup() {
        val policy = SplitWatchPolicy()
        policy.observe(fullHeight, expected, 12, false)
        repeat(3) { assertEquals(SplitWatchPolicy.Action.REFIT, policy.observe(fullHeight, expected, 12, false)) }
        assertEquals(SplitWatchPolicy.Action.CLOSE, policy.observe(fullHeight, expected, 12, false))
    }
    @Test fun settledBoundsResetTheRepairBudget() {
        val policy = SplitWatchPolicy()
        repeat(4) { policy.observe(fullHeight, expected, 12, false) }
        assertEquals(SplitWatchPolicy.Action.WAIT, policy.observe(expected, expected, 12, false))
        policy.observe(fullHeight, expected, 12, false)
        assertEquals(SplitWatchPolicy.Action.REFIT, policy.observe(fullHeight, expected, 12, false))
    }
    @Test fun aCustomHandleDragCannotBeOverriddenByTheWatchdog() {
        val policy = SplitWatchPolicy()
        repeat(8) { assertEquals(SplitWatchPolicy.Action.WAIT, policy.observe(fullHeight, expected, 12, true)) }
        assertEquals(SplitWatchPolicy.Action.WAIT, policy.observe(fullHeight, expected, 12, false))
    }
    @Test fun nativeDividerChangesAreDetectedEvenWhenScreenSizeIsUnchanged() {
        val policy = SplitWatchPolicy()
        val moved = SplitGeometry.layout(SplitGeometry.Bounds(0, 0, 1920, 720), 60, 572)!!
        assertEquals(SplitWatchPolicy.Action.WAIT, policy.observe(moved, expected, 12, false))
        assertEquals(SplitWatchPolicy.Action.REFIT, policy.observe(moved, expected, 12, false))
    }
}
