package defpackage;

import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class gf implements Serializable {
    public final Serializable a;

    public static final Throwable a(Object obj) {
        if (obj instanceof ff) {
            return ((ff) obj).a;
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof gf) {
            return this.a.equals(((gf) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        Serializable serializable = this.a;
        if (serializable instanceof ff) {
            return ((ff) serializable).toString();
        }
        return "Success(" + serializable + ')';
    }
}
