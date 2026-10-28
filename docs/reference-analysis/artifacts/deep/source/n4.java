package defpackage;

import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class n4 {
    public final String a;
    public final int b;
    public final String c;
    public final Set d;

    public n4(String str, int i, String str2, Set set) {
        str2.getClass();
        this.a = str;
        this.b = i;
        this.c = str2;
        this.d = set;
    }

    public final boolean a(Integer num) {
        if (num != null) {
            return num.intValue() == this.b || this.d.contains(num);
        }
        return false;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof n4)) {
            return false;
        }
        n4 n4Var = (n4) obj;
        return k8.e(this.a, n4Var.a) && this.b == n4Var.b && k8.e(this.c, n4Var.c) && k8.e(this.d, n4Var.d);
    }

    public final int hashCode() {
        return this.d.hashCode() + c.b((Integer.hashCode(this.b) + (this.a.hashCode() * 31)) * 31, 31, this.c);
    }

    public final String toString() {
        return "Mode(key=" + this.a + ", value=" + this.b + ", title=" + this.c + ", aliases=" + this.d + ")";
    }
}
