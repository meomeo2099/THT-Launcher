package defpackage;

import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class v2 {
    public final Map a;
    public final Float b;
    public final Float c;
    public final Float d;
    public final String e;
    public final Map f;

    public v2(Map map, Float f, Float f2, Float f3, String str, Map map2) {
        str.getClass();
        map2.getClass();
        this.a = map;
        this.b = f;
        this.c = f2;
        this.d = f3;
        this.e = str;
        this.f = map2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof v2)) {
            return false;
        }
        v2 v2Var = (v2) obj;
        return k8.e(this.a, v2Var.a) && k8.e(this.b, v2Var.b) && k8.e(this.c, v2Var.c) && k8.e(this.d, v2Var.d) && k8.e(this.e, v2Var.e) && k8.e(this.f, v2Var.f);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        Float f = this.b;
        int iHashCode2 = (iHashCode + (f == null ? 0 : f.hashCode())) * 31;
        Float f2 = this.c;
        int iHashCode3 = (iHashCode2 + (f2 == null ? 0 : f2.hashCode())) * 31;
        Float f3 = this.d;
        return this.f.hashCode() + c.b((iHashCode3 + (f3 != null ? f3.hashCode() : 0)) * 31, 31, this.e);
    }

    public final String toString() {
        return "ClimateData(temperatures=" + this.a + ", minimum=" + this.b + ", maximum=" + this.c + ", step=" + this.d + ", unit=" + this.e + ", values=" + this.f + ")";
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ v2() {
        v4 v4Var = v4.a;
        this(v4Var, null, null, null, "", v4Var);
    }
}
