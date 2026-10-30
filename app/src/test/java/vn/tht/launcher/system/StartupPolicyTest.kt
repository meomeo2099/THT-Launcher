package vn.tht.launcher.system

import org.junit.Assert.*
import org.junit.Test

class StartupPolicyTest {
    @Test fun selectedPackagesAcceptNormalDottedNamesButRejectSelfAndMalformedInput() {
        assertEquals(listOf("com.ecarx.multimedia"), StartupPolicy.packages(
            listOf("com.ecarx.multimedia"), "vn.tht.launcher"))
        assertEquals(listOf("com.ecarx.multimedia", "com.autonavi.amapauto"), StartupPolicy.packages(
            listOf(" com.ecarx.multimedia ", "vn.tht.launcher", "com.ecarx.multimedia", "", "com.autonavi.amapauto",
                "single", "com..app", "com.bad/app", "com.bad;sh", "com.app trailing"), "vn.tht.launcher"))
    }

    @Test fun startupListHasABoundedNumberOfUniquePackages() {
        val packages = (0 until 100).map { "com.example.app$it" }
        assertEquals(packages.take(20), StartupPolicy.packages(packages, "vn.tht.launcher"))
    }

    @Test fun bootCounterSurvivesWallClockChangesAndRejectsAnOlderBoot() {
        assertTrue(StartupPolicy.sameBoot(12, 12, 1_000, 9_000_000))
        assertFalse(StartupPolicy.sameBoot(11, 12, 1_000, 1_000))
    }

    @Test fun missingBootCounterFallsBackToBoundedBootWallTime() {
        assertTrue(StartupPolicy.sameBoot(null, null, 1_000, 61_000))
        assertFalse(StartupPolicy.sameBoot(null, null, 1_000, 61_001))
        assertFalse(StartupPolicy.sameBoot(null, null, 0, 1_000))
    }
}
