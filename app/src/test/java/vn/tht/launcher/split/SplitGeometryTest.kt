package vn.tht.launcher.split

import org.junit.Assert.*
import org.junit.Test

class SplitGeometryTest {
    private val screen = SplitGeometry.Bounds(0, 0, 1920, 720)
    @Test fun bothPanesFitAboveTheDockWithoutOverlapping() {
        val result = SplitGeometry.layout(screen, 50, 572)!!
        assertEquals(SplitGeometry.Bounds(0, 0, 960, 572), result.left)
        assertEquals(SplitGeometry.Bounds(960, 0, 1920, 572), result.right)
    }
    @Test fun oemLeftRailIsExcludedFromRatioAndDragCoordinates() {
        val area = SplitGeometry.Bounds(160, 0, 1920, 720)
        val result = SplitGeometry.layout(area, 30, 572)!!
        assertEquals(688, result.divider)
        assertEquals(30, SplitGeometry.ratio(area, 688))
        assertEquals(0, result.left.left)
    }
    @Test fun invalidRatiosAreBoundedAndBothSidesStillExist() {
        assertEquals(384, SplitGeometry.layout(screen, -500, 720)!!.divider)
        assertEquals(1536, SplitGeometry.layout(screen, 500, 720)!!.divider)
    }
    @Test fun impossibleAvailableSpaceIsRejectedBeforeOpeningApps() {
        assertNull(SplitGeometry.layout(SplitGeometry.Bounds(1900, 0, 1920, 720), 50, 572))
        assertNull(SplitGeometry.layout(screen, 50, 10))
    }
    @Test fun negativeStartErrorsAndVoidAreNeverAcceptedAsTaskLaunches() {
        assertFalse(SplitGeometry.acceptedStart(-96))
        assertFalse(SplitGeometry.acceptedStart(-1))
        assertFalse(SplitGeometry.acceptedStart(null))
        assertFalse(SplitGeometry.acceptedStart(true))
        assertTrue(SplitGeometry.acceptedStart(0))
        assertTrue(SplitGeometry.acceptedStart(2))
    }
    @Test fun firmwareCannotSilentlyKeepPanesCoveringTheDock() {
        assertFalse(SplitGeometry.near(SplitGeometry.Bounds(0, 0, 960, 720),
            SplitGeometry.Bounds(0, 0, 960, 572), 12))
    }
    @Test fun smallNativeDividerGapsAreAllowedButNotLargePositionChanges() {
        assertTrue(SplitGeometry.near(SplitGeometry.Bounds(968, 0, 1920, 572),
            SplitGeometry.Bounds(960, 0, 1920, 572), 12))
        assertFalse(SplitGeometry.near(SplitGeometry.Bounds(1152, 0, 1920, 572),
            SplitGeometry.Bounds(960, 0, 1920, 572), 12))
    }
}
