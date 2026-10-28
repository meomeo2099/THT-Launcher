package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class p2 {
    public final Integer a;
    public final Integer b;
    public final Integer c;
    public final Float d;

    public p2(Integer num, Integer num2, Integer num3, Float f) {
        this.a = num;
        this.b = num2;
        this.c = num3;
        this.d = f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof p2)) {
            return false;
        }
        p2 p2Var = (p2) obj;
        return k8.e(this.a, p2Var.a) && k8.e(this.b, p2Var.b) && k8.e(this.c, p2Var.c) && k8.e(this.d, p2Var.d);
    }

    public final int hashCode() {
        Integer num = this.a;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        Integer num2 = this.b;
        int iHashCode2 = (iHashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.c;
        int iHashCode3 = (iHashCode2 + (num3 == null ? 0 : num3.hashCode())) * 31;
        Float f = this.d;
        return iHashCode3 + (f != null ? f.hashCode() : 0);
    }

    public final String toString() {
        return "CarSnapshot(odometerKm=" + this.a + ", serviceKm=" + this.b + ", serviceDays=" + this.c + ", fuelPercent=" + this.d + ")";
    }
}
