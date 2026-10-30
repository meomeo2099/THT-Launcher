package vn.tht.launcher.home

import org.junit.Assert.*
import org.junit.Test

class EmbeddedViewportGateTest {
    @Test fun displayAndSurfaceMustBothMatchTheHost() {
        val g = EmbeddedViewportGate()
        assertFalse(g.observe(700, 400, 700, 400, 350, 400))
        assertFalse(g.observe(700, 400, 350, 400, 700, 400))
        assertFalse(g.observe(0, 0, 0, 0, 0, 0))
    }
    @Test fun oneValidCallbackCannotLaunchBeforeTheSecondSample() {
        val g = EmbeddedViewportGate()
        assertFalse(g.observe(700, 400, 700, 400, 700, 400))
        assertTrue(g.observe(700, 400, 700, 400, 700, 400))
    }
    @Test fun changingSizesDoNotCountAsTwoStableSamples() {
        val g = EmbeddedViewportGate()
        assertFalse(g.observe(700, 400, 700, 400, 700, 400))
        assertFalse(g.observe(1200, 500, 1200, 500, 1200, 500))
        assertTrue(g.observe(1200, 500, 1200, 500, 1200, 500))
    }
    @Test fun aLostSurfaceOrNewGenerationInvalidatesThePreviousSample() {
        val g = EmbeddedViewportGate()
        g.observe(700, 400, 700, 400, 700, 400)
        g.observe(700, 400, 0, 0, 700, 400)
        assertFalse(g.observe(700, 400, 700, 400, 700, 400))
        g.reset()
        assertFalse(g.observe(700, 400, 700, 400, 700, 400))
    }
}
