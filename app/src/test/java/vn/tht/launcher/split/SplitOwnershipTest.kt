package vn.tht.launcher.split

import org.junit.Assert.*
import org.junit.Test

class SplitOwnershipTest {
    @Test fun missingSecondaryIsOurIncompleteSplit() { assertTrue(SplitOwnership.mayClean(11, null, 11, 22, true)) }
    @Test fun missingPrimaryStillAllowsRecoveringOurSecondary() { assertTrue(SplitOwnership.mayClean(null, 22, 11, 22, true)) }
    @Test fun alreadyClosedPairCanHaveItsOldBoundsCleared() { assertTrue(SplitOwnership.mayClean(null, null, 11, 22, true)) }
    @Test fun aDifferentPrimaryCannotBeDismissed() { assertFalse(SplitOwnership.mayClean(99, 22, 11, 22, false)) }
    @Test fun replacementSecondaryBelongsToANewCommittedPair() { assertFalse(SplitOwnership.mayClean(11, 99, 11, 22, true)) }
    @Test fun pendingPrimaryMayTemporarilyHaveAnAutomaticallyChosenSecondary() {
        assertTrue(SplitOwnership.mayClean(11, 99, 11, 22, false))
        assertFalse(SplitOwnership.mayClean(null, 99, 11, 22, false))
    }
    @Test fun ourOwnHomeReplacingTheSecondaryShouldExitOurSplit() {
        assertTrue(SplitOwnership.mayClean(11, 99, 11, 22, true, true))
        assertFalse(SplitOwnership.mayClean(88, 99, 11, 22, true, true))
    }
}
