package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class ih {
    public final String a;
    public final float b;

    public ih(String str, float f) {
        this.a = str;
        this.b = f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ih)) {
            return false;
        }
        ih ihVar = (ih) obj;
        return this.a.equals(ihVar.a) && Float.compare(this.b, ihVar.b) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.b) + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "StatBar(label=" + this.a + ", km=" + this.b + ")";
    }
}
