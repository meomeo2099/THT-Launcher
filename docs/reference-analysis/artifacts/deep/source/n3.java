package defpackage;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class n3 extends m3 {
    public static void o0(List list, Comparator comparator) {
        list.getClass();
        if (list.size() > 1) {
            Collections.sort(list, comparator);
        }
    }
}
