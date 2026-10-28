package defpackage;

import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class kd {
    public final String a;
    public final String b;
    public final int c;

    public kd(String str, String str2, int i) {
        str.getClass();
        str2.getClass();
        this.a = str;
        this.b = str2;
        this.c = i;
    }

    public static kd a(kd kdVar, String str) {
        String str2 = kdVar.a;
        int i = kdVar.c;
        kdVar.getClass();
        str2.getClass();
        str.getClass();
        return new kd(str2, str, i);
    }

    public final String b() {
        int i = this.c;
        if (i == 4) {
            return "AM";
        }
        if (i != 11) {
            return i != 34 ? "FM" : "AM";
        }
        return "DAB";
    }

    public final boolean c(String str) {
        str.getClass();
        String strB = b();
        String str2 = this.a;
        str2.getClass();
        Set set = nb.l;
        String strZ = pi.z(strB, str2);
        if (strZ == null) {
            strZ = th.J(str2).toString();
        }
        String strZ2 = pi.z(b(), str);
        if (strZ2 == null) {
            strZ2 = th.J(str).toString();
        }
        return k8.e(strZ, strZ2);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof kd)) {
            return false;
        }
        kd kdVar = (kd) obj;
        return k8.e(this.a, kdVar.a) && k8.e(this.b, kdVar.b) && this.c == kdVar.c;
    }

    public final int hashCode() {
        return Integer.hashCode(this.c) + c.b(this.a.hashCode() * 31, 31, this.b);
    }

    public final String toString() {
        return "Station(frequency=" + this.a + ", name=" + this.b + ", type=" + this.c + ")";
    }
}
