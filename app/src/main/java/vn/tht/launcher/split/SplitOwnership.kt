package vn.tht.launcher.split

/** A missing owned pane needs cleanup; a different user's pair must be left alone. */
internal object SplitOwnership {
    fun mayClean(primary: Int?, secondary: Int?, left: Int, right: Int?, committed: Boolean,
                 ownHomeReplacedSecondary: Boolean = false): Boolean {
        if (primary != null && primary != left) return false
        if (secondary != null && secondary != right && !ownHomeReplacedSecondary) {
            if (committed || primary == null) return false
        }
        return true
    }
}
