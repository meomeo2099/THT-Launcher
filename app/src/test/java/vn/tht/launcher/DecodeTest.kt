package vn.tht.launcher

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import vn.tht.launcher.radio.EcarxRadio
import vn.tht.launcher.ui.Text
import vn.tht.launcher.vehicle.CarPropertySource
import vn.tht.launcher.vehicle.EcarxFunctions as F

/** Các phép giải mã dữ liệu xe dễ sai mà không thử được trên emulator. */
class DecodeTest {
    private fun bytes(vararg v: Int) = ByteArray(v.size) { v[it].toByte() }

    @Test fun voltageTenthsOfVolt() {
        assertEquals(14.1f, CarPropertySource.decodeVoltage(bytes(141))!!, 0.001f)
        assertEquals(12.6f, CarPropertySource.decodeVoltage(bytes(126, 0))!!, 0.001f)
    }

    @Test fun voltageMillivoltsLittleEndian() {
        // 14100 mV = 0x3714 → byte thấp trước
        assertEquals(14.1f, CarPropertySource.decodeVoltage(bytes(0x14, 0x37))!!, 0.001f)
        assertEquals(12.45f, CarPropertySource.decodeVoltage(bytes(0xA2, 0x30, 0, 0))!!, 0.001f)
    }

    @Test fun voltageRejectsGarbage() {
        assertNull(CarPropertySource.decodeVoltage(null))
        assertNull(CarPropertySource.decodeVoltage(bytes()))
        assertNull(CarPropertySource.decodeVoltage(bytes(0, 0)))          // 0 V
        assertNull(CarPropertySource.decodeVoltage(bytes(200)))           // 20 V
        assertNull(CarPropertySource.decodeVoltage(bytes(1, 2, 3, 4, 5))) // quá dài
    }

    @Test fun ecarxLevelRoundTrip() {
        assertEquals(F.HVAC_FAN_SPEED + 3, F.level(F.HVAC_FAN_SPEED, 3))
        assertEquals(F.OFF, F.level(F.HVAC_FAN_SPEED, 0))
        assertEquals(3, F.levelOf(F.HVAC_FAN_SPEED, F.HVAC_FAN_SPEED + 3))
        assertEquals(0, F.levelOf(F.HVAC_FAN_SPEED, F.OFF))
        assertEquals(3, F.levelOf(F.HVAC_FAN_SPEED, 3)) // firmware trả thẳng mức nhỏ
        assertNull(F.levelOf(F.HVAC_FAN_SPEED, null))
        assertNull(F.levelOf(F.HVAC_FAN_SPEED, 0x7FFF0000))
    }

    @Test fun radioLabels() {
        val fm = EcarxRadio.State(1, 98500, 0x2000)
        assertEquals("FM 98.5", fm.label())
        assertTrue(fm.fm); assertTrue(fm.playing)
        val am = EcarxRadio.State(0, 702, 0)
        assertEquals("AM 702", am.label())
        assertFalse(am.fm); assertFalse(am.playing)
        // Băng chưa rõ: suy theo tần số (≥ 30000 kHz là FM)
        assertTrue(EcarxRadio.State(-1, 101700, 0).fm)
    }

    @Test fun searchIgnoresVietnameseMarks() {
        assertEquals("dong ho", Text.plain("Đồng hồ"))
        assertTrue("nhac" in Text.plain("Nhạc & dẫn đường"))
        assertTrue("xang" in Text.plain("Ô xăng (lít)"))
    }
}
