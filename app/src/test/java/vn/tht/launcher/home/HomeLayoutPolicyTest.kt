package vn.tht.launcher.home

import org.junit.Assert.*
import org.junit.Test

class HomeLayoutPolicyTest {
    @Test fun referencePresetFitsCanvasWithoutOverlaps() {
        val layout = HomeLayout.referenceDefaults()
        assertEquals(10, layout.size)
        assertEquals(layout.size, layout.map { it.id }.toSet().size)
        for (spec in layout) {
            assertEquals("Outside canvas: ${spec.id}", spec, HomeLayout.bounded(spec))
            for (other in layout.filter { it.id != spec.id }) {
                val overlaps = spec.x < other.x + other.w && other.x < spec.x + spec.w &&
                    spec.y < other.y + other.h && other.y < spec.y + spec.h
                assertFalse("Overlapping widgets: ${spec.id}, ${other.id}", overlaps)
            }
        }
        val dock = layout.single { it.type == WidgetType.BOTTOM_BAR }
        assertEquals(60, dock.x)
        assertEquals(1800, dock.w)
        assertEquals(700, dock.y + dock.h)
    }

    @Test fun savedClassicAndCustomGeometrySurvivesSanitizing() {
        val classic = HomeLayout.classicDefaults()
        assertEquals(classic, HomeLayout.sanitize(classic))
        val custom = listOf(WidgetSpec("custom", WidgetType.MEDIA, 313, 121, 711, 389, "chosen.player"))
        assertEquals(custom, HomeLayout.sanitize(custom))
        assertEquals(emptyList<WidgetSpec>(), HomeLayout.sanitize(emptyList()))
    }

    @Test fun invalidGeometryIsBoundedWithoutMutatingInput() {
        val original = WidgetSpec("custom", WidgetType.MEDIA, Int.MIN_VALUE, Int.MAX_VALUE, -1, Int.MAX_VALUE, "player")
        val restored = HomeLayout.sanitize(listOf(original)).single()
        assertEquals(0, restored.x)
        assertEquals(0, restored.y)
        assertEquals(HomeLayout.MIN_SIZE, restored.w)
        assertEquals(HomeLayout.DESIGN_H, restored.h)
        assertEquals("player", restored.pkg)
        assertEquals(-1, original.w)
        assertEquals(Int.MAX_VALUE, original.y)
    }

    @Test fun duplicateIdsAndSingletonBarsAreRejectedWithoutDiscardingOtherWidgets() {
        val specs = listOf(
            WidgetSpec("first", WidgetType.BOTTOM_BAR, 0, 0, 100, 100),
            WidgetSpec("duplicate-bar", WidgetType.BOTTOM_BAR, 0, 0, 100, 100),
            WidgetSpec("duplicate-bar", WidgetType.MEDIA, 200, 0, 100, 100),
            WidgetSpec("first", WidgetType.NAV, 300, 0, 100, 100),
            WidgetSpec("", WidgetType.APP, 0, 0, 100, 100),
            WidgetSpec("valid", WidgetType.APP, 400, 0, 100, 100, "map.app"),
        )
        assertEquals(listOf("first", "duplicate-bar", "valid"), HomeLayout.sanitize(specs).map { it.id })
    }

    @Test fun presetsReturnFreshMutableSpecsAndLimitRestoredWidgetCount() {
        val first = HomeLayout.defaults()
        first.first().x = 0
        assertEquals(60, HomeLayout.defaults().first().x)
        val many = List(200) { WidgetSpec("widget-$it", WidgetType.APP, 0, 0, 100, 100) }
        assertEquals(96, HomeLayout.sanitize(many).size)
    }
}
