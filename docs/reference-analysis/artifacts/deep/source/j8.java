package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class j8 extends h8 {
    public static final j8 d = new j8(1, 0, 1);

    @Override // defpackage.h8
    public final boolean equals(Object obj) {
        if (!(obj instanceof j8)) {
            return false;
        }
        if (isEmpty() && ((j8) obj).isEmpty()) {
            return true;
        }
        j8 j8Var = (j8) obj;
        return this.a == j8Var.a && this.b == j8Var.b;
    }

    @Override // defpackage.h8
    public final int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (this.a * 31) + this.b;
    }

    @Override // defpackage.h8
    public final boolean isEmpty() {
        return this.a > this.b;
    }

    @Override // defpackage.h8
    public final String toString() {
        return this.a + ".." + this.b;
    }
}
