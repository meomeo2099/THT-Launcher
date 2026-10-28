package defpackage;

import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class lb {
    public final int a;
    public final String b;
    public final String c;
    public final String d;
    public final boolean e;
    public final String f;
    public final String g;

    public lb(int i, String str, String str2, String str3, boolean z, String str4, String str5) {
        str.getClass();
        str2.getClass();
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = z;
        this.f = str4;
        this.g = str5;
    }

    public final String a() {
        int i = this.a;
        if (i == 4) {
            return "AM";
        }
        if (i != 11) {
            return i != 34 ? "FM" : "AM";
        }
        return "DAB";
    }

    public final String b() {
        Set set = nb.l;
        String strZ = pi.z(a(), this.b);
        return strZ != null ? c.d(a(), " ", strZ) : "";
    }

    public final boolean c() {
        Set set = nb.l;
        return nb.l.contains(Integer.valueOf(this.a));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof lb)) {
            return false;
        }
        lb lbVar = (lb) obj;
        return this.a == lbVar.a && k8.e(this.b, lbVar.b) && k8.e(this.c, lbVar.c) && this.d.equals(lbVar.d) && this.e == lbVar.e && this.f.equals(lbVar.f) && this.g.equals(lbVar.g);
    }

    public final int hashCode() {
        return this.g.hashCode() + c.b((Boolean.hashCode(this.e) + c.b(c.b(c.b(Integer.hashCode(this.a) * 31, 31, this.b), 31, this.c), 31, this.d)) * 31, 31, this.f);
    }

    public final String toString() {
        return "State(sourceType=" + this.a + ", frequency=" + this.b + ", station=" + this.c + ", title=" + this.d + ", playing=" + this.e + ", packageName=" + this.f + ", appName=" + this.g + ")";
    }
}
