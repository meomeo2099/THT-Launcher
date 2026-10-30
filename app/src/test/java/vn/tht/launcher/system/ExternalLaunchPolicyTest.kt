package vn.tht.launcher.system

import org.junit.Assert.*
import org.junit.Test

class ExternalLaunchPolicyTest {
    @Test fun sharedFullscreenStackCanRestoreItsNormalFrameForTheSelectedTopApp() {
        assertTrue(ExternalLaunchPolicy.mayClearFullscreenStack(true, 1, "test.map",
            listOf("vn.tht.launcher/.MainActivity", "other/.Main", "test.map/.Main"), "test.map"))
    }
    @Test fun sharedFreeformHomeAndForeignForegroundStacksRemainProtected() {
        val mixed = listOf("other/.Main", "test.map/.Main")
        assertFalse(ExternalLaunchPolicy.mayClearFullscreenStack(true, 5, "test.map", mixed, "test.map"))
        assertFalse(ExternalLaunchPolicy.mayClearFullscreenStack(false, 1, "test.map", mixed, "test.map"))
        assertFalse(ExternalLaunchPolicy.mayClearFullscreenStack(true, 1, "other", mixed, "test.map"))
        assertFalse(ExternalLaunchPolicy.mayClearFullscreenStack(true, 3, "test.map", mixed, "test.map"))
    }
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
    class Window(private val type: Int, private val mode: Int = 1) {
        fun getActivityType() = type
        fun getWindowingMode() = mode
    }
    class Config(@JvmField val windowConfiguration: Window)
    class Legacy(@JvmField val configuration: Config)
    class Modern { fun getActivityType() = 1 }
    @Test fun android9StackTypeIsReadFromItsWindowConfiguration() {
        assertEquals(1, ExternalLaunchPolicy.activityType(Legacy(Config(Window(1)))))
        assertEquals(2, ExternalLaunchPolicy.activityType(Legacy(Config(Window(2)))))
    }
    @Test fun directGetterIsSupportedOnOtherFrameworkVersions() {
        assertEquals(1, ExternalLaunchPolicy.activityType(Modern()))
    }
    @Test fun unknownFrameworkShapeNeverGrantsPermissionToResizeAStack() {
        assertNull(ExternalLaunchPolicy.activityType(Any()))
    }
    @Test fun aFullscreenRepairCannotResizeANewlyRequestedSplit() {
        for (mode in listOf(3, 4)) {
            val stack = Legacy(Config(Window(1, mode)))
            assertFalse(ExternalLaunchPolicy.mayRepairFullscreen(ExternalLaunchPolicy.windowingMode(stack)))
        }
        assertTrue(ExternalLaunchPolicy.mayRepairFullscreen(1))
        assertTrue(ExternalLaunchPolicy.mayRepairFullscreen(5))
        assertFalse(ExternalLaunchPolicy.mayRepairFullscreen(null))
    }
}
