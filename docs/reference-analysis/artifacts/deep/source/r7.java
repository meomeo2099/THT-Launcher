package defpackage;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class r7 {
    public final List a;
    public final s7 b;

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ r7() {
        float f = 0.0f;
        float f2 = 1.0f;
        this(pi.J(new u7(f, f, f2, f2)), s7.d);
    }

    public static r7 a(r7 r7Var, ArrayList arrayList, s7 s7Var, int i) {
        List list = arrayList;
        if ((i & 1) != 0) {
            list = r7Var.a;
        }
        if ((i & 2) != 0) {
            s7Var = r7Var.b;
        }
        list.getClass();
        s7Var.getClass();
        return new r7(list, s7Var);
    }

    public final r7 b(int i, u7 u7Var) {
        List list = this.a;
        list.getClass();
        ArrayList arrayList = new ArrayList(list);
        arrayList.set(i, u7Var.a());
        return a(this, arrayList, null, 2);
    }

    public final int c(float f, float f2) {
        List list = this.a;
        ListIterator listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            u7 u7Var = (u7) listIterator.previous();
            float f3 = u7Var.a;
            if (f <= u7Var.c && f3 <= f) {
                float f4 = u7Var.b;
                if (f2 <= u7Var.d && f4 <= f2) {
                    return listIterator.nextIndex();
                }
            }
        }
        return -1;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof r7)) {
            return false;
        }
        r7 r7Var = (r7) obj;
        return k8.e(this.a, r7Var.a) && this.b == r7Var.b;
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Config(zones=" + this.a + ", over=" + this.b + ")";
    }

    public r7(List list, s7 s7Var) {
        this.a = list;
        this.b = s7Var;
    }
}
