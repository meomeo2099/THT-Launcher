package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class pe {
    public final long a;
    public final long b;
    public final int c;
    public final float d;
    public final float e;
    public final float f;
    public final boolean g;
    public final boolean h;

    public pe(long j, long j2, int i, float f, float f2, float f3, boolean z, boolean z2) {
        this.a = j;
        this.b = j2;
        this.c = i;
        this.d = f;
        this.e = f2;
        this.f = f3;
        this.g = z;
        this.h = z2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof pe)) {
            return false;
        }
        pe peVar = (pe) obj;
        return this.a == peVar.a && this.b == peVar.b && this.c == peVar.c && Float.compare(this.d, peVar.d) == 0 && Float.compare(this.e, peVar.e) == 0 && Float.compare(this.f, peVar.f) == 0 && this.g == peVar.g && this.h == peVar.h;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.h) + ((Boolean.hashCode(this.g) + ((Float.hashCode(this.f) + ((Float.hashCode(this.e) + ((Float.hashCode(this.d) + ((Integer.hashCode(this.c) + ((Long.hashCode(this.b) + (Long.hashCode(this.a) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "Refuel(id=" + this.a + ", ts=" + this.b + ", odometerKm=" + this.c + ", percentBefore=" + this.d + ", percentAfter=" + this.e + ", liters=" + this.f + ", full=" + this.g + ", manual=" + this.h + ")";
    }
}
