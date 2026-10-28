package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class uc {
    public final int a;
    public final int b;
    public final String c;

    public uc(int i, int i2, String str) {
        this.a = i;
        this.b = i2;
        this.c = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof uc)) {
            return false;
        }
        uc ucVar = (uc) obj;
        return this.a == ucVar.a && this.b == ucVar.b && k8.e(this.c, ucVar.c);
    }

    public final int hashCode() {
        int iHashCode = (Integer.hashCode(this.b) + (Integer.hashCode(this.a) * 31)) * 31;
        String str = this.c;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public final String toString() {
        return "NavigationPreviewTask(displayId=" + this.a + ", taskId=" + this.b + ", packageName=" + this.c + ")";
    }
}
