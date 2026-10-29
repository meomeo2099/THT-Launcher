package vn.tht.launcher.home

/** Focus can remain true between a tap and onPause: only a real pause/resume permits re-embedding. */
internal class PreviewHandoff {
    private var leaving = false
    private var departed = false
    val mayEmbed get() = !leaving
    fun begin() { leaving = true; departed = false }
    fun foreground(front: Boolean): Boolean {
        if (leaving && !front) departed = true
        if (leaving && front && departed) {
            leaving = false; departed = false
            return true
        }
        return false
    }
    fun failed() { leaving = false; departed = false }
}
