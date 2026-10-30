package vn.tht.launcher.split

import org.junit.Assert.*
import org.junit.Test

class SplitSessionTest {
    private val first = SplitSession.Request("one", "left.app", "right.app", 40)
    private val second = first.copy(id = "two")

    private class Queue : SplitSession.Scheduler {
        val pending = mutableListOf<() -> Unit>()
        val cancelled = mutableListOf<() -> Unit>()
        val delays = mutableListOf<Long>()
        override fun post(delayMs: Long, action: () -> Unit) { delays += delayMs; pending += action }
        override fun cancelAll() { cancelled += pending; pending.clear() }
        fun next() { pending.removeAt(0).invoke() }
        fun drain() {
            var count = 0
            while (pending.isNotEmpty()) { check(++count < 200); next() }
        }
        fun runStale() { cancelled.toList().forEach { it() }; cancelled.clear() }
    }
    private class FakeDriver : SplitSession.Driver {
        val found = mutableMapOf("left.app" to 11, "right.app" to 22)
        val lookups = mutableListOf<String>()
        val launches = mutableListOf<String>()
        val moves = mutableListOf<Pair<Int, Int>>()
        val rollbacks = mutableListOf<Pair<SplitSession.Tasks?, Int?>>()
        var error: String? = null
        var prepared = true
        var launchOk = true
        var launchCreatesTask = false
        var failMode: Int? = null
        var primaryReady = true
        var pairReady = true
        var layoutReady = true
        var resizeOk = true
        var cleanupOk = true
        var finishes = 0
        var preparations = 0
        var placements = 0
        var primaryChecks = 0
        var pairChecks = 0
        var layoutChecks = 0
        override fun preflight(request: SplitSession.Request) = error
        override fun prepare(request: SplitSession.Request): Boolean { preparations++; return prepared }
        override fun findTask(pkg: String): Int? { lookups += pkg; return found[pkg] }
        override fun launch(pkg: String): Boolean {
            launches += pkg
            if (launchOk && launchCreatesTask) found[pkg] = if (pkg == "left.app") 11 else 22
            return launchOk
        }
        override fun move(task: Int, mode: Int): Boolean { moves += task to mode; return mode != failMode }
        override fun primaryMatches(left: Int): Boolean { primaryChecks++; return primaryReady }
        override fun pairMatches(tasks: SplitSession.Tasks): Boolean { pairChecks++; return pairReady }
        override fun place(request: SplitSession.Request, tasks: SplitSession.Tasks): Boolean {
            placements++; return resizeOk
        }
        override fun layoutMatches(request: SplitSession.Request, tasks: SplitSession.Tasks): Boolean {
            layoutChecks++; return layoutReady
        }
        override fun rollback(tasks: SplitSession.Tasks?, primary: Int?): Boolean {
            rollbacks += tasks to primary; return cleanupOk
        }
        override fun finished() { finishes++ }
    }
    private class Signals : SplitSession.Events {
        val openings = mutableListOf<SplitSession.Request>()
        val committed = mutableListOf<SplitSession.Request>()
        val failures = mutableListOf<Pair<String, Boolean>>()
        var closes = 0
        override fun opening(request: SplitSession.Request) { openings += request }
        override fun active(request: SplitSession.Request, tasks: SplitSession.Tasks) { committed += request }
        override fun closed() { closes++ }
        override fun failed(message: String, cleanupOk: Boolean) { failures += message to cleanupOk }
    }
    private class Fixture {
        val driver = FakeDriver()
        val queue = Queue()
        val signals = Signals()
        val session = SplitSession(driver, queue, signals)
    }

    @Test fun bothTasksExistBeforeAnyWindowMutationAndActivationWaitsForResize() {
        val f = Fixture()
        f.session.open(first)
        assertEquals(listOf("right.app", "left.app"), f.driver.lookups)
        assertEquals(listOf(11 to 3), f.driver.moves)
        assertTrue(f.session.starting)
        assertFalse(f.session.active)
        f.queue.next()
        assertEquals(listOf(11 to 3, 22 to 4), f.driver.moves)
        assertFalse(f.session.active)
        f.queue.next()
        assertFalse(f.session.active)
        assertEquals(1, f.driver.placements)
        f.queue.next()
        assertTrue(f.session.active)
        assertFalse(f.session.starting)
        assertEquals(1, f.driver.placements)
        assertEquals(listOf(first), f.signals.committed)
    }

    @Test fun closeCancelsEveryPendingStageIncludingCallbacksAlreadyDequeued() {
        // Waiting for right, left, primary, pair, and actual bounds confirmation.
        for (stage in 0..4) {
            val f = Fixture()
            if (stage == 0) f.driver.found.remove("right.app")
            if (stage == 1) f.driver.found.remove("left.app")
            f.session.open(first)
            if (stage >= 3) f.queue.next()
            if (stage == 4) f.queue.next()
            val calls = f.driver.moves.size
            assertTrue(f.session.close())
            f.queue.runStale()
            f.queue.drain()
            assertEquals("stage=$stage", calls, f.driver.moves.size)
            assertFalse(f.session.hasWork)
            assertFalse(f.session.starting)
            assertTrue(f.signals.committed.isEmpty())
            assertEquals(if (stage >= 2) 1 else 0, f.driver.rollbacks.size)
        }
    }

    @Test fun releaseRollsBackAnIncompletePrimaryAndCancelsSecondary() {
        val f = Fixture()
        f.session.open(first)
        assertTrue(f.session.release())
        f.queue.runStale()
        assertEquals(11, f.driver.rollbacks.single().second)
        assertEquals(listOf(11 to 3), f.driver.moves)
        assertFalse(f.session.hasWork)
    }

    @Test fun failedPrimaryMoveStillRollsBackPossibleBinderMutation() {
        val f = Fixture()
        f.driver.failMode = 3
        f.session.open(first)
        assertEquals(11, f.driver.rollbacks.single().second)
        assertFalse(f.session.hasWork)
        assertTrue(f.signals.failures.single().second)
    }

    @Test fun failedSecondaryMoveRollsBackAndNeverCommits() {
        val f = Fixture()
        f.driver.failMode = 4
        f.session.open(first)
        f.queue.drain()
        assertEquals(1, f.driver.rollbacks.size)
        assertFalse(f.session.active)
        assertTrue(f.signals.committed.isEmpty())
    }

    @Test fun missingPrimaryConfirmationIsBoundedAndDoesNotMoveSecondary() {
        val f = Fixture()
        f.driver.primaryReady = false
        f.session.open(first)
        f.queue.drain()
        assertEquals(12, f.driver.primaryChecks)
        assertEquals(listOf(11 to 3), f.driver.moves)
        assertEquals(1, f.driver.rollbacks.size)
        assertTrue(f.queue.delays.all { it == 150L })
    }

    @Test fun missingPairConfirmationIsBoundedAndRollsBack() {
        val f = Fixture()
        f.driver.pairReady = false
        f.session.open(first)
        f.queue.drain()
        assertEquals(12, f.driver.pairChecks)
        assertEquals(0, f.driver.placements)
        assertEquals(1, f.driver.rollbacks.size)
        assertFalse(f.session.hasWork)
    }

    @Test fun resizeFailureRollsBackInsteadOfAnnouncingActive() {
        val f = Fixture()
        f.driver.resizeOk = false
        f.session.open(first)
        f.queue.drain()
        assertEquals(1, f.driver.placements)
        assertEquals(1, f.driver.rollbacks.size)
        assertTrue(f.signals.committed.isEmpty())
    }

    @Test fun acceptedResizeWhoseBoundsNeverSettleRollsBack() {
        val f = Fixture()
        f.driver.layoutReady = false
        f.session.open(first)
        f.queue.drain()
        assertEquals(3, f.driver.placements)
        assertEquals(12, f.driver.layoutChecks)
        assertEquals(1, f.driver.rollbacks.size)
        assertFalse(f.session.hasWork)
        assertTrue(f.signals.committed.isEmpty())
        assertTrue(f.signals.failures.single().second)
    }

    @Test fun abandonNeverRollsBackAndStaleCallbacksCannotResurrectOwnership() {
        // Include a pending layout readback and a fully committed pair.
        for (committed in listOf(false, true)) {
            val f = Fixture()
            f.session.open(first)
            f.queue.next()
            f.queue.next()
            if (committed) f.queue.next()
            val commits = f.signals.committed.size
            f.session.abandon()
            f.queue.runStale()
            f.queue.drain()
            assertTrue(f.driver.rollbacks.isEmpty())
            assertFalse(f.session.hasWork)
            assertFalse(f.session.active)
            assertFalse(f.session.starting)
            assertEquals(commits, f.signals.committed.size)
            assertEquals(1, f.driver.finishes)
            assertEquals(1, f.signals.closes)
            // Ownership really was forgotten: a later close must also avoid rollback.
            assertTrue(f.session.close())
            assertTrue(f.driver.rollbacks.isEmpty())
        }
    }

    @Test fun adoptedOwnershipRetriesOnlyCleanupWithoutOpeningOrCommitting() {
        val f = Fixture()
        val pair = SplitSession.Tasks(71, 82)
        assertTrue(f.session.adoptForCleanup(first, pair, 71))
        assertEquals(first, f.session.request)
        assertTrue(f.session.hasWork)
        assertFalse(f.session.active)
        assertFalse(f.session.starting)
        assertTrue(f.signals.openings.isEmpty())
        assertTrue(f.signals.committed.isEmpty())
        assertTrue(f.signals.failures.isEmpty())
        assertEquals(0, f.signals.closes)
        assertEquals(0, f.driver.preparations)
        assertTrue(f.driver.moves.isEmpty())
        assertTrue(f.driver.launches.isEmpty())
        assertTrue(f.session.close())
        assertEquals(pair to 71, f.driver.rollbacks.single())
        assertFalse(f.session.hasWork)
    }

    @Test fun adoptedCleanupFailureRemainsRetryableWithoutTasks() {
        val f = Fixture()
        assertTrue(f.session.adoptForCleanup(first, null, 71))
        f.driver.cleanupOk = false
        assertFalse(f.session.close())
        assertEquals(first, f.session.request)
        assertFalse(f.session.active)
        assertFalse(f.session.starting)
        assertEquals(0, f.driver.finishes)
        assertFalse(f.signals.failures.single().second)
        f.driver.cleanupOk = true
        assertTrue(f.session.close())
        assertEquals(listOf(null to 71, null to 71), f.driver.rollbacks)
        assertFalse(f.session.hasWork)
        assertTrue(f.signals.committed.isEmpty())
    }

    @Test fun adoptionCannotOverwritePendingOrActiveWork() {
        for (committed in listOf(false, true)) {
            val f = Fixture()
            f.session.open(first)
            if (committed) f.queue.drain()
            val queued = f.queue.pending.size
            val openings = f.signals.openings.size
            val commits = f.signals.committed.size
            assertFalse(f.session.adoptForCleanup(second, SplitSession.Tasks(71, 82), 71))
            assertEquals(first, f.session.request)
            assertEquals(queued, f.queue.pending.size)
            assertEquals(openings, f.signals.openings.size)
            assertEquals(commits, f.signals.committed.size)
            f.queue.drain()
            assertTrue(f.session.active)
            assertTrue(f.session.close())
            assertEquals(11, f.driver.rollbacks.single().second)
        }
    }

    @Test fun taskTimeoutNeverMovesPrimaryAndReleasesPreparedResources() {
        val f = Fixture()
        f.driver.found.remove("left.app")
        f.session.open(first)
        f.queue.drain()
        assertEquals(30, f.queue.delays.size)
        assertTrue(f.queue.delays.all { it == 250L })
        assertTrue(f.driver.moves.isEmpty())
        assertTrue(f.driver.rollbacks.isEmpty())
        assertEquals(1, f.driver.finishes)
        assertFalse(f.session.hasWork)
    }

    @Test fun delayedTaskAppearanceContinuesInRightThenLeftOrder() {
        val f = Fixture()
        f.driver.found.clear()
        f.driver.launchCreatesTask = true
        f.session.open(first)
        f.queue.drain()
        assertEquals(listOf("right.app", "left.app"), f.driver.launches)
        assertTrue(f.session.active)
    }

    @Test fun sameTaskIsRejectedBeforeMutation() {
        val f = Fixture()
        f.driver.found["right.app"] = 11
        f.session.open(first)
        assertTrue(f.driver.moves.isEmpty())
        assertTrue(f.driver.rollbacks.isEmpty())
        assertFalse(f.session.hasWork)
    }

    @Test fun lateNativeTransitionGetsBoundedResizeRetryBeforeCommit() {
        val f = Fixture()
        f.driver.layoutReady = false
        f.session.open(first)
        f.queue.next(); f.queue.next()
        repeat(4) { f.queue.next() }
        assertEquals(2, f.driver.placements)
        assertFalse(f.session.active)
        f.driver.layoutReady = true
        f.queue.next()
        assertTrue(f.session.active)
        assertEquals(2, f.driver.placements)
    }

    @Test fun invalidReplacementPreservesExistingActivePairAndControls() {
        val f = Fixture()
        f.session.open(first)
        f.queue.drain()
        f.driver.error = "App unavailable"
        f.session.open(second)
        assertTrue(f.session.active)
        assertEquals(first, f.session.request)
        assertEquals(0, f.signals.closes)
        assertTrue(f.driver.rollbacks.isEmpty())
    }

    @Test fun validReplacementClosesOldPairBeforePreparingNext() {
        val f = Fixture()
        f.session.open(first)
        f.queue.drain()
        f.session.open(second)
        assertEquals(1, f.driver.rollbacks.size)
        assertEquals(1, f.driver.finishes)
        assertEquals(2, f.driver.preparations)
        assertEquals(second, f.session.request)
        assertFalse(f.session.active)
        f.queue.drain()
        assertTrue(f.session.active)
    }

    @Test fun pendingReplacementCannotBeOvertakenByStaleCallbacks() {
        val f = Fixture()
        f.session.open(first)
        f.session.open(second)
        val moves = f.driver.moves.size
        f.queue.runStale()
        assertEquals(moves, f.driver.moves.size)
        f.queue.drain()
        assertEquals(listOf(second), f.signals.committed)
    }

    @Test fun invalidNewRequestStillCancelsAnOlderPendingAttempt() {
        val f = Fixture()
        f.session.open(first)
        f.driver.error = "App unavailable"
        f.session.open(second)
        f.queue.runStale()
        assertFalse(f.session.hasWork)
        assertFalse(f.session.starting)
        assertTrue(f.signals.committed.isEmpty())
        assertEquals(1, f.driver.rollbacks.size)
    }

    @Test fun cleanupFailurePreservesOwnershipAndBlocksReplacementUntilRetry() {
        val f = Fixture()
        f.session.open(first)
        f.queue.drain()
        f.driver.cleanupOk = false
        f.session.open(second)
        assertEquals(first, f.session.request)
        assertTrue(f.session.active)
        assertFalse(f.session.starting)
        assertEquals(1, f.driver.preparations)
        assertEquals(0, f.driver.finishes)
        assertFalse(f.signals.failures.last().second)
        f.driver.cleanupOk = true
        assertTrue(f.session.close())
        assertEquals(2, f.driver.rollbacks.size)
        assertFalse(f.session.hasWork)
        assertFalse(f.session.active)
    }

    @Test fun failedPendingRollbackRetainsRecoveryOwnershipAndRejectsStaleWork() {
        val f = Fixture()
        f.session.open(first)
        f.driver.cleanupOk = false
        assertFalse(f.session.release())
        f.queue.runStale()
        assertTrue(f.session.hasWork)
        assertFalse(f.session.starting)
        assertFalse(f.session.active)
        assertTrue(f.signals.committed.isEmpty())
        f.driver.cleanupOk = true
        assertTrue(f.session.close())
        assertEquals(11, f.driver.rollbacks.last().second)
    }

    @Test fun prepareAndLaunchFailuresFinishWithoutRollback() {
        for (prepareFails in listOf(true, false)) {
            val f = Fixture()
            if (prepareFails) f.driver.prepared = false else {
                f.driver.found.remove("right.app")
                f.driver.launchOk = false
            }
            f.session.open(first)
            assertFalse(f.session.hasWork)
            assertTrue(f.driver.rollbacks.isEmpty())
            assertEquals(1, f.driver.finishes)
            assertTrue(f.signals.failures.single().second)
        }
    }
}
