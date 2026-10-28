package defpackage;

import java.util.LinkedHashSet;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class pc {
    public static final LinkedHashSet a = new LinkedHashSet();
    public static mc b;

    public static void a(MainActivity mainActivity, boolean z) {
        mc mcVar;
        LinkedHashSet linkedHashSet = a;
        boolean z2 = !linkedHashSet.isEmpty();
        if (z) {
            linkedHashSet.add(mainActivity);
        } else {
            linkedHashSet.remove(mainActivity);
        }
        if (z2 == (!linkedHashSet.isEmpty()) || (mcVar = b) == null) {
            return;
        }
        mcVar.a();
    }
}
