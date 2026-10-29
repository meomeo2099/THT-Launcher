package vn.tht.launcher.system

import org.junit.Assert.*
import org.junit.Test

class ExternalLaunchPolicyTest {
    @Test fun taskMatchingIsExactForPackagesAndComponents() {
        assertTrue(ExternalLaunchPolicy.belongs("test.map/.Main", "test.map"))
        assertTrue(ExternalLaunchPolicy.belongs("test.map", "test.map"))
        assertFalse(ExternalLaunchPolicy.belongs("test.map.other/.Main", "test.map"))
        assertFalse(ExternalLaunchPolicy.belongs(null, "test.map"))
    }
    @Test fun stackOverridesCannotBeClearedForMixedOrUnknownOwners() {
        assertTrue(ExternalLaunchPolicy.mayClearStack(listOf("test.map/.Main", "test.map/.Search"), "test.map"))
        assertFalse(ExternalLaunchPolicy.mayClearStack(listOf("test.map/.Main", "other/.Main"), "test.map"))
        assertFalse(ExternalLaunchPolicy.mayClearStack(listOf("test.map/.Main", null), "test.map"))
        assertFalse(ExternalLaunchPolicy.mayClearStack(emptyList(), "test.map"))
    }
    @Test fun voidBinderSuccessIsDifferentFromAnExplicitFalseResult() {
        assertTrue(ExternalLaunchPolicy.binderAccepted(null))
        assertTrue(ExternalLaunchPolicy.binderAccepted(true))
        assertFalse(ExternalLaunchPolicy.binderAccepted(false))
    }
}
