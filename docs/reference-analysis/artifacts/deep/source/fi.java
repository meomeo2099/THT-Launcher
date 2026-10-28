package defpackage;

import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class fi implements Serializable {
    public y5 a;
    public volatile Object b = l0.e;
    public final Object c = this;

    public fi(y5 y5Var) {
        this.a = y5Var;
    }

    public final Object a() {
        Object objA;
        Object obj = this.b;
        l0 l0Var = l0.e;
        if (obj != l0Var) {
            return obj;
        }
        synchronized (this.c) {
            objA = this.b;
            if (objA == l0Var) {
                y5 y5Var = this.a;
                y5Var.getClass();
                objA = y5Var.a();
                this.b = objA;
                this.a = null;
            }
        }
        return objA;
    }

    public final String toString() {
        return this.b != l0.e ? String.valueOf(a()) : "Lazy value not initialized yet.";
    }
}
