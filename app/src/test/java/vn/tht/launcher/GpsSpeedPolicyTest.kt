package vn.tht.launcher

import org.junit.Assert.*
import org.junit.Test
import vn.tht.launcher.vehicle.GpsSpeedPolicy

class GpsSpeedPolicyTest {
    @Test fun onlyFreshRealFiniteSpeedMayDriveTheFallback() {
        val at = 10_000_000_000L
        assertEquals(36f, GpsSpeedPolicy.accept(10f, true, false, at, at + 5_000_000_000L)!!, 0.001f)
        assertNull(GpsSpeedPolicy.accept(10f, true, false, at, at + 5_000_000_001L))
        assertNull(GpsSpeedPolicy.accept(10f, true, true, at, at))
        assertNull(GpsSpeedPolicy.accept(10f, false, false, at, at))
        assertNull(GpsSpeedPolicy.accept(Float.NaN, true, false, at, at))
        assertNull(GpsSpeedPolicy.accept(10f, true, false, at, at - 1))
        assertNull(GpsSpeedPolicy.accept(-1f, true, false, at, at))
        assertNull(GpsSpeedPolicy.accept(100f, true, false, at, at))
    }
}
