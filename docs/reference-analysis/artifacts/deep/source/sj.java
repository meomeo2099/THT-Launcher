package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class sj {
    public final long a;
    public final float b;
    public final boolean c;

    public sj(long j, float f, boolean z) {
        this.a = j;
        this.b = f;
        this.c = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof sj)) {
            return false;
        }
        sj sjVar = (sj) obj;
        return this.a == sjVar.a && Float.compare(this.b, sjVar.b) == 0 && this.c == sjVar.c;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.c) + ((Float.hashCode(this.b) + (Long.hashCode(this.a) * 31)) * 31);
    }

    public final String toString() {
        return "VoltagePoint(ts=" + this.a + ", volts=" + this.b + ", engineOn=" + this.c + ")";
    }
}
