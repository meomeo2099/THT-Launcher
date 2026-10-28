package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class se extends k8 {
    public final float j;
    public final float k;

    public se(float f, float f2) {
        this.j = f;
        this.k = f2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof se)) {
            return false;
        }
        se seVar = (se) obj;
        return Float.compare(this.j, seVar.j) == 0 && Float.compare(this.k, seVar.k) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.k) + (Float.hashCode(this.j) * 31);
    }

    public final String toString() {
        return "Opened(before=" + this.j + ", after=" + this.k + ")";
    }
}
