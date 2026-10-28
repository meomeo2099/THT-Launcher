package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class li {
    public final long a;
    public final long b;
    public final long c;
    public final float d;
    public final int e;
    public final int f;
    public final int g;
    public final float h;
    public final float i;

    public li(long j, long j2, long j3, float f, int i, int i2, int i3, float f2, float f3) {
        this.a = j;
        this.b = j2;
        this.c = j3;
        this.d = f;
        this.e = i;
        this.f = i2;
        this.g = i3;
        this.h = f2;
        this.i = f3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof li)) {
            return false;
        }
        li liVar = (li) obj;
        return this.a == liVar.a && this.b == liVar.b && this.c == liVar.c && Float.compare(this.d, liVar.d) == 0 && this.e == liVar.e && this.f == liVar.f && this.g == liVar.g && Float.compare(this.h, liVar.h) == 0 && Float.compare(this.i, liVar.i) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.i) + ((Float.hashCode(this.h) + ((Integer.hashCode(this.g) + ((Integer.hashCode(this.f) + ((Integer.hashCode(this.e) + ((Float.hashCode(this.d) + ((Long.hashCode(this.c) + ((Long.hashCode(this.b) + (Long.hashCode(this.a) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "Trip(id=" + this.a + ", startedAt=" + this.b + ", endedAt=" + this.c + ", distanceKm=" + this.d + ", driveSec=" + this.e + ", idleSec=" + this.f + ", maxSpeed=" + this.g + ", avgConsumption=" + this.h + ", fuelUsedL=" + this.i + ")";
    }
}
