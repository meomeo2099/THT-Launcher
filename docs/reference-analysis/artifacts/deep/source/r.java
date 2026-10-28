package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class r {
    public final Float a;
    public final Integer b;
    public final Integer c;
    public final boolean d;
    public final Float e;

    public r(Float f, Integer num, Integer num2, boolean z, Float f2) {
        this.a = f;
        this.b = num;
        this.c = num2;
        this.d = z;
        this.e = f2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        return k8.e(this.a, rVar.a) && k8.e(this.b, rVar.b) && k8.e(this.c, rVar.c) && this.d == rVar.d && k8.e(this.e, rVar.e);
    }

    public final int hashCode() {
        Float f = this.a;
        int iHashCode = (f == null ? 0 : f.hashCode()) * 31;
        Integer num = this.b;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.c;
        int iHashCode3 = (Boolean.hashCode(this.d) + ((iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31)) * 31;
        Float f2 = this.e;
        return iHashCode3 + (f2 != null ? f2.hashCode() : 0);
    }

    public final String toString() {
        return "AntonSeen(temperature=" + this.a + ", fan=" + this.b + ", airflow=" + this.c + ", on=" + this.d + ", passenger=" + this.e + ")";
    }
}
