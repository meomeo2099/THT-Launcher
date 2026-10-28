package vn.tht.launcher.settings

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import org.junit.Assert.*
import org.junit.Test

class BackupArchivePolicyTest {
    @Test fun onlyManagedRelativePathsAreAccepted() {
        for (path in listOf("settings.json", "config/home.json", "config/topbar.json", "files/font_custom",
            "files/wallpaper_day.jpg", "files/img_93c-21.png", "files/iconpack/seat-heat-left-color.png")) assertTrue(path, BackupArchivePolicy.accepts(path))
        for (path in listOf("../settings.json", "/settings.json", "files/../font_custom", "files\\font_custom",
            "files/iconpack/../../private", "files/iconpack/a/b.png", "config/evil.json", "files/stats.db",
            "files/iconpack/evil.so", "file:settings.json", "files/img_bad\u0000.png")) assertFalse(path, BackupArchivePolicy.accepts(path))
    }

    @Test fun rejectsUnknownSizeEntryWhileStreamingBeforeWritingOverLimit() {
        val output = ByteArrayOutputStream()
        try {
            BackupArchivePolicy.copy(ByteArrayInputStream(ByteArray(100_000)), output, 40_000, BackupArchivePolicy.Budget())
            fail("Expected entry limit")
        } catch (_: IOException) { assertTrue(output.size() <= 40_000) }
    }

    @Test fun aggregateBudgetSpansMultipleEntries() {
        val budget = BackupArchivePolicy.Budget(50)
        BackupArchivePolicy.copy(ByteArrayInputStream(ByteArray(30)), ByteArrayOutputStream(), 40, budget)
        try {
            BackupArchivePolicy.copy(ByteArrayInputStream(ByteArray(30)), ByteArrayOutputStream(), 40, budget)
            fail("Expected aggregate limit")
        } catch (_: IOException) { assertEquals(30L, budget.consumed) }
    }

    @Test fun exactEntryAndTotalLimitsAreAllowed() {
        val output = ByteArrayOutputStream(); val budget = BackupArchivePolicy.Budget(3)
        assertEquals(3L, BackupArchivePolicy.copy(ByteArrayInputStream(byteArrayOf(1, 2, 3)), output, 3, budget))
        assertArrayEquals(byteArrayOf(1, 2, 3), output.toByteArray()); assertEquals(3L, budget.consumed)
    }

    @Test fun compressedStreamHasIndependentLimit() {
        val input = BackupArchivePolicy.LimitedInput(ByteArrayInputStream(ByteArray(10)), 5)
        assertEquals(5, input.read(ByteArray(5)))
        try { input.read(); fail("Expected compressed input limit") } catch (_: IOException) { }
    }
}
