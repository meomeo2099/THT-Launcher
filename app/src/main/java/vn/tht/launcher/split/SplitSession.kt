package vn.tht.launcher.split

/** Main-thread coordinator. Android task/window operations stay behind [Driver]. */
class SplitSession(
    private val driver: Driver,
    private val scheduler: Scheduler,
    private val events: Events,
) {
    data class Request(val id: String, val left: String, val right: String, val ratio: Int)
    data class Tasks(val left: Int, val right: Int)

    interface Driver {
        fun preflight(request: Request): String?
        fun prepare(request: Request): Boolean
        fun findTask(pkg: String): Int?
        fun launch(pkg: String): Boolean
        fun move(task: Int, mode: Int): Boolean
        fun primaryMatches(left: Int): Boolean
        fun pairMatches(tasks: Tasks): Boolean
        fun place(request: Request, tasks: Tasks): Boolean
        fun layoutMatches(request: Request, tasks: Tasks): Boolean
        fun rollback(tasks: Tasks?, primary: Int?): Boolean
        fun finished()
    }
    interface Scheduler {
        fun post(delayMs: Long, action: () -> Unit)
        fun cancelAll()
    }
    interface Events {
        fun opening(request: Request)
        fun active(request: Request, tasks: Tasks)
        fun closed()
        fun failed(message: String, cleanupOk: Boolean)
    }

    var request: Request? = null
        private set
    var active = false
        private set
    var starting = false
        private set
    val hasWork: Boolean get() = request != null

    private var generation = 0L
    private var tasks: Tasks? = null
    // Set before entering Binder: a reported failure can still have changed the window state.
    private var ownedPrimary: Int? = null

    fun open(next: Request) {
        // A pending attempt must not keep running behind a newer request, even if the
        // newer request is invalid. An already committed pair stays visible on rejection.
        if (hasWork && !active && !close()) return
        val error = driver.preflight(next)
        if (error != null) {
            events.failed(error, true)
            return
        }
        if (hasWork && !close()) return
        invalidate()
        val token = generation
        request = next
        starting = true
        events.opening(next)
        if (!valid(token)) return
        if (!driver.prepare(next)) return fail("Unable to prepare split")
        obtain(next.right, token) { right ->
            obtain(next.left, token) { left ->
                if (left == right) {
                    fail("Both apps resolved to the same task")
                } else {
                    val pair = Tasks(left, right)
                    tasks = pair
                    ownedPrimary = left
                    if (!driver.move(left, PRIMARY)) {
                        fail("Unable to move primary task")
                    } else {
                        confirm(token, { driver.primaryMatches(left) }, "Primary task did not enter split") {
                            if (!driver.move(right, SECONDARY)) {
                                fail("Unable to move secondary task")
                            } else {
                                confirm(token, { driver.pairMatches(pair) }, "Split pair did not become ready") {
                                    if (!driver.place(next, pair)) {
                                        fail("Unable to resize split pair")
                                    } else {
                                        confirm(token, { driver.layoutMatches(next, pair) }, "Split bounds did not settle",
                                            retry = { driver.place(next, pair) }) {
                                            starting = false
                                            active = true
                                            events.active(next, pair)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /** A failed cleanup keeps ownership so the caller can offer a retry. */
    fun close(): Boolean {
        invalidate()
        starting = false
        if (ownedPrimary != null && !driver.rollback(tasks, ownedPrimary)) {
            events.failed("Unable to restore fullscreen; retry close", false)
            return false
        }
        request = null
        tasks = null
        ownedPrimary = null
        active = false
        driver.finished()
        events.closed()
        return true
    }

    fun release(): Boolean = close()

    /** Recover only cleanup ownership from an earlier owner; never launch or commit a pair. */
    fun adoptForCleanup(request: Request, tasks: Tasks?, primary: Int): Boolean {
        if (hasWork) return false
        invalidate()
        this.request = request
        this.tasks = tasks
        ownedPrimary = primary
        starting = false
        active = false
        return true
    }

    /** Android replaced our pair: forget ownership without touching the replacement. */
    fun abandon() {
        invalidate()
        request = null
        tasks = null
        ownedPrimary = null
        active = false
        starting = false
        driver.finished()
        events.closed()
    }

    private fun invalidate() {
        generation++
        scheduler.cancelAll()
    }

    private fun valid(token: Long) = token == generation && starting && request != null

    private fun fail(message: String) {
        // close() emits its own failure when rollback fails, retaining recovery state.
        if (close()) events.failed(message, true)
    }

    private fun obtain(pkg: String, token: Long, done: (Int) -> Unit) {
        if (!valid(token)) return
        driver.findTask(pkg)?.let { done(it); return }
        if (!driver.launch(pkg)) return fail("Unable to launch app")
        fun poll(attempt: Int) {
            scheduler.post(TASK_POLL_MS) {
                if (!valid(token)) return@post
                val task = driver.findTask(pkg)
                when {
                    task != null -> done(task)
                    attempt >= TASK_ATTEMPTS -> fail("App task did not appear")
                    else -> poll(attempt + 1)
                }
            }
        }
        poll(1)
    }

    private fun confirm(token: Long, matches: () -> Boolean, message: String, retry: (() -> Boolean)? = null, done: () -> Unit) {
        fun poll(attempt: Int) {
            scheduler.post(STACK_POLL_MS) {
                if (!valid(token)) return@post
                when {
                    matches() -> done()
                    attempt >= STACK_ATTEMPTS -> fail(message)
                    else -> {
                        // Android may finish its dock transition after our first resize.
                        // Reapply at most twice; never commit geometry that was not read back.
                        if (attempt % 4 == 0 && retry != null && !retry()) fail(message)
                        else poll(attempt + 1)
                    }
                }
            }
        }
        poll(1)
    }

    private companion object {
        const val PRIMARY = 3
        const val SECONDARY = 4
        const val TASK_POLL_MS = 250L
        const val TASK_ATTEMPTS = 30
        const val STACK_POLL_MS = 150L
        const val STACK_ATTEMPTS = 12
    }
}
