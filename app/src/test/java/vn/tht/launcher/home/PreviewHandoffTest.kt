package vn.tht.launcher.home

import org.junit.Assert.*
import org.junit.Test

class PreviewHandoffTest {
    @Test fun focusAndTicksBeforeDepartureCannotReembed() {
        val state = PreviewHandoff()
        state.begin()
        repeat(20) { assertFalse(state.foreground(true)); assertFalse(state.mayEmbed) }
    }
    @Test fun onlyAPauseFollowedByResumeRestoresPreview() {
        val state = PreviewHandoff()
        state.begin()
        assertFalse(state.foreground(false)); assertFalse(state.mayEmbed)
        assertTrue(state.foreground(true)); assertTrue(state.mayEmbed)
        assertFalse(state.foreground(true))
    }
    @Test fun failedLaunchDoesNotLeavePreviewPermanentlySuspended() {
        val state = PreviewHandoff()
        state.begin(); state.failed()
        assertTrue(state.mayEmbed)
        assertFalse(state.foreground(true))
    }
    @Test fun aNewHandoffCannotReuseDepartureFromAnOldCycle() {
        val state = PreviewHandoff()
        state.begin(); state.foreground(false)
        state.begin()
        assertFalse(state.foreground(true)); assertFalse(state.mayEmbed)
    }
}
