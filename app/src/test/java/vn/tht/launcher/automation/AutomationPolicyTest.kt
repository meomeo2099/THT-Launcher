package vn.tht.launcher.automation

import org.junit.Assert.*
import org.junit.Test

class AutomationPolicyTest {
    @Test fun newBootIsFreshEvenWithoutCleanEngineOffButActivityRestartIsNot() {
        assertTrue(AutomationCycle.freshStart(10_000, 0, 8, 9))
        assertFalse(AutomationCycle.freshStart(10_000, 0, 9, 9))
        assertTrue(AutomationCycle.freshStart(10_000, 11_000, 9, 9))
        assertTrue(AutomationCycle.freshStart(0, 0, null, null))
        assertFalse(AutomationCycle.freshStart(10_000, 0, null, null))
    }

    @Test fun delayedActionRequiresSameCycleConfigurationAndFreshRunningVehicle() {
        val cycle = AutomationCycle()
        val token = cycle.token(42)
        assertTrue(cycle.permits(token, 42, true, true, 500))
        assertFalse(cycle.permits(token, 42, false, true, 500))
        assertFalse(cycle.permits(token, 42, true, false, 500))
        assertFalse(cycle.permits(token, 42, true, null, 500))
        assertFalse(cycle.permits(token, 43, true, true, 500))
        assertFalse(cycle.permits(token, 42, true, true, 10_001))
        assertFalse(cycle.permits(token, 42, true, true, -1))
        cycle.invalidate()
        assertFalse(cycle.permits(token, 42, true, true, 0))
        assertTrue(cycle.permits(cycle.token(42), 42, true, true, 0))
    }

    @Test fun parkedIntervalDoesNotTreatUnknownOrReversedClockAsLongParking() {
        assertTrue(AutomationCycle.parkedLongEnough(1_000, 0, 0))
        assertFalse(AutomationCycle.parkedLongEnough(1_000, 0, 1))
        assertFalse(AutomationCycle.parkedLongEnough(1_000, 2_000, 1))
        assertFalse(AutomationCycle.parkedLongEnough(60_999, 1_000, 1))
        assertTrue(AutomationCycle.parkedLongEnough(61_000, 1_000, 1))
    }

    @Test fun temperatureFallbackRejectsStaleAndInvalidMeasurements() {
        assertEquals(12f, AutomationCycle.validTemperature(12f, 30f, 100)!!, 0f)
        assertEquals(30f, AutomationCycle.validTemperature(Float.NaN, 30f, 100)!!, 0f)
        assertNull(AutomationCycle.validTemperature(null, 30f, 10_800_001))
        assertNull(AutomationCycle.validTemperature(null, 30f, -1))
        assertNull(AutomationCycle.validTemperature(200f, Float.POSITIVE_INFINITY, 0))
    }

    @Test fun roofDoesNotFloodCommandsAndRetriesOnlyAfterCooldown() {
        val rule = RoofSpeedRule()
        assertTrue(rule.shouldClose(31f, 30, 0))
        assertFalse(rule.shouldClose(35f, 30, 1_000)) // vẫn đang chờ kết quả
        rule.completed(false)
        assertFalse(rule.shouldClose(35f, 30, 4_999))
        assertTrue(rule.shouldClose(35f, 30, 5_000))
    }

    @Test fun roofRearmsOnlyBelowHysteresisOrAfterNewCycle() {
        val rule = RoofSpeedRule()
        assertTrue(rule.shouldClose(30f, 30, 0))
        rule.completed(true)
        assertFalse(rule.shouldClose(60f, 30, 6_000))
        assertFalse(rule.shouldClose(27f, 30, 7_000))
        assertFalse(rule.shouldClose(31f, 30, 8_000))
        assertFalse(rule.shouldClose(24f, 30, 9_000))
        assertTrue(rule.shouldClose(30f, 30, 10_000))
        rule.completed(true)
        rule.reset()
        assertTrue(rule.shouldClose(40f, 30, 10_001))
    }

    @Test fun roofRejectsDisabledAndInvalidSpeeds() {
        val rule = RoofSpeedRule()
        assertFalse(rule.shouldClose(40f, 0, 0))
        assertFalse(rule.shouldClose(null, 30, 0))
        assertFalse(rule.shouldClose(Float.NaN, 30, 0))
        assertFalse(rule.shouldClose(-1f, 30, 0))
        assertFalse(rule.shouldClose(500f, 30, 0))
    }
}
