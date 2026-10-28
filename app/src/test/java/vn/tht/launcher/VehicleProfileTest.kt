package vn.tht.launcher

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test
import vn.tht.launcher.vehicle.Profiles

class VehicleProfileTest {
    @Test fun knownHardwareSelectsCoolrayWithoutPackageAssumptions() {
        assertEquals(Profiles.COOLRAY_2025, Profiles.detect("SX11-LA", "unknown", "unknown"))
        assertEquals(Profiles.COOLRAY_2025, Profiles.detect("unknown", "IHU730P", "unknown"))
        assertEquals(Profiles.COOLRAY_2025, Profiles.detect("unknown", "unknown", "IHU730P"))
        assertEquals(Profiles.COOLRAY_2025, Profiles.detect(" sx11-la ", "unknown", "unknown"))
    }

    @Test fun unrelatedAndMissingHardwareRemainGeneric() {
        assertEquals(Profiles.GENERIC, Profiles.detect("Pixel", "sdk_gphone64_arm64", "emu64a"))
        assertEquals(Profiles.GENERIC, Profiles.detect("", "", ""))
        assertFalse(Profiles.COOLRAY_2025.matches("SX11", "IHU730", "IHU730P-extra"))
    }
}
