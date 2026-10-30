package vn.tht.launcher.home

/** Surface and Display callbacks can arrive in either order; require two identical valid samples. */
internal class EmbeddedViewportGate {
    private var candidate: Pair<Int, Int>? = null
    private var samples = 0
    fun reset() { candidate = null; samples = 0 }
    fun observe(w: Int, h: Int, sw: Int, sh: Int, dw: Int, dh: Int): Boolean {
        if (w <= 0 || h <= 0 || sw != w || sh != h || dw != w || dh != h) { reset(); return false }
        val next = w to h
        samples = if (candidate == next) samples + 1 else 1
        candidate = next
        return samples >= 2
    }
}
