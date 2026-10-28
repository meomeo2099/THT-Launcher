package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class di {
    public static final di f = new di(0.0f, 0, 0, 0, 0.0f);
    public final float a;
    public final int b;
    public final int c;
    public final int d;
    public final float e;

    public di(float f2, int i, int i2, int i3, float f3) {
        this.a = f2;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = f3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof di)) {
            return false;
        }
        di diVar = (di) obj;
        return Float.compare(this.a, diVar.a) == 0 && this.b == diVar.b && this.c == diVar.c && this.d == diVar.d && Float.compare(this.e, diVar.e) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.e) + ((Integer.hashCode(this.d) + ((Integer.hashCode(this.c) + ((Integer.hashCode(this.b) + (Float.hashCode(this.a) * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "Summary(distanceKm=" + this.a + ", trips=" + this.b + ", driveSec=" + this.c + ", idleSec=" + this.d + ", fuelUsedL=" + this.e + ")";
    }
}
