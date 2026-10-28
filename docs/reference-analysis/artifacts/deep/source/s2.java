package defpackage;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class s2 implements r2 {
    static {
        List listI0 = j3.i0(y5.class, j6.class, n6.class, o6.class, p6.class, q6.class, r6.class, s6.class, t6.class, u6.class, z5.class, a6.class, b6.class, c6.class, d6.class, e6.class, f6.class, g6.class, h6.class, i6.class, k6.class, l6.class, m6.class);
        ArrayList arrayList = new ArrayList(k3.n0(listI0));
        int i = 0;
        for (Object obj : listI0) {
            int i2 = i + 1;
            if (i < 0) {
                j3.m0();
                throw null;
            }
            arrayList.add(new yc((Class) obj, Integer.valueOf(i)));
            i = i2;
        }
        hb.l0(arrayList);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof s2) && k8.M(this).equals(k8.M((s2) obj));
    }

    public final int hashCode() {
        return k8.M(this).hashCode();
    }

    public final String toString() {
        return jb.class + " (Kotlin reflection is not available)";
    }
}
