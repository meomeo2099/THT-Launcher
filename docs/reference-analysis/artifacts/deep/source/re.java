package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class re extends k8 {
    public final float j;

    public re(float f) {
        this.j = f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof re) && Float.compare(this.j, ((re) obj).j) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.j);
    }

    public final String toString() {
        return "Grown(after=" + this.j + ")";
    }
}
