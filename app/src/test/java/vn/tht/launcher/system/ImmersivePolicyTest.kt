package vn.tht.launcher.system

import org.junit.Assert.*
import org.junit.Test

class ImmersivePolicyTest {
    @Test fun targetsOnlySelectedPackagesInsteadOfAllApps() {
        assertEquals("immersive.full=test.map,test.player", ImmersivePolicy.scoped(null, setOf("test.player", "test.map")))
    }
    @Test fun preservesExistingPackagesAndOtherClauses() {
        assertEquals("immersive.navigation=other.app:immersive.full=own.home,test.map:immersive.preconfirms=apps",
            ImmersivePolicy.scoped("immersive.navigation=other.app:immersive.full=own.home:immersive.preconfirms=apps", setOf("test.map")))
    }
    @Test fun neverOverridesExplicitPackageOrAppExclusions() {
        for (base in listOf("immersive.full=apps,-test.map", "immersive.navigation=-test.map", "immersive.full=*,-apps"))
            assertNull(ImmersivePolicy.scoped(base, setOf("test.map")))
    }
    @Test fun existingWildcardNeedsNoAdditionalPolicy() {
        assertEquals("immersive.full=*", ImmersivePolicy.scoped("immersive.full=*", setOf("test.map")))
    }
    @Test fun malformedDuplicateFullPoliciesAreLeftAlone() {
        assertNull(ImmersivePolicy.scoped("immersive.full=a.b:immersive.full=c.d", setOf("test.map")))
    }
    @Test fun packagesCannotInjectGlobalPolicyClauses() {
        assertEquals("immersive.full=test.map", ImmersivePolicy.scoped(null, setOf("test.map", "*:immersive.full=*")))
    }
}
