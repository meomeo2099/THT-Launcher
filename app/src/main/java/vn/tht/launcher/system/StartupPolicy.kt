package vn.tht.launcher.system

/** Stable boot identity and selected-package planning without depending on Android classes. */
internal object StartupPolicy {
    private val packageName = Regex("""[A-Za-z][A-Za-z0-9_]*(?:\.[A-Za-z][A-Za-z0-9_]*)+""")

    fun packages(input: List<String>, ownPackage: String): List<String> = input.asSequence()
        .map(String::trim)
        .filter { packageName.matches(it) && it != ownPackage }
        .distinct().take(20).toList()

    fun sameBoot(pendingCount: Int?, currentCount: Int?, pendingWallBootMs: Long, currentWallBootMs: Long): Boolean {
        if (pendingCount != null && currentCount != null) return pendingCount == currentCount
        return pendingWallBootMs > 0L && currentWallBootMs > 0L &&
            kotlin.math.abs(pendingWallBootMs - currentWallBootMs) <= 60_000L
    }
}
