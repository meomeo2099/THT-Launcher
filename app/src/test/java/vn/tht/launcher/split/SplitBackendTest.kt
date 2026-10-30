package vn.tht.launcher.split
import org.junit.Assert.*
import org.junit.Test
class SplitBackendTest {
 @Test fun coolrayAutoAvoidsSecondaryDisplayEvenWhenFeatureIsPresent() { assertFalse(SplitBackend.hosted("auto",true,true)) }
 @Test fun genericAutoRetainsEmbedding() { assertTrue(SplitBackend.hosted("auto",true,false)) }
 @Test fun explicitTrialStillRequiresCapability() { assertTrue(SplitBackend.hosted("hosted",true,true));assertFalse(SplitBackend.hosted("hosted",false,true)) }
 @Test fun nativeNeverCreatesSecondaryDisplays() { assertFalse(SplitBackend.hosted("native",true,false)) }
}
