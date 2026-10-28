package defpackage;

import android.util.Log;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class o1 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ y5 b;

    public /* synthetic */ o1(y5 y5Var) {
        this.a = 2;
        this.b = y5Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.a;
        Object ffVar = ti.a;
        y5 y5Var = this.b;
        switch (i) {
            case 0:
                try {
                    y5Var.a();
                } catch (Throwable th) {
                    ffVar = new ff(th);
                }
                Throwable thA = gf.a(ffVar);
                if (thA != null) {
                    Log.w("ReferencePanel.Auto", si.a("delayed_action", new Object[0]), thA);
                }
                break;
            case 1:
                try {
                    y5Var.a();
                } catch (Throwable th2) {
                    ffVar = new ff(th2);
                }
                Throwable thA2 = gf.a(ffVar);
                if (thA2 != null) {
                    Log.w("ReferencePanel.Auto", si.a("delayed_action", new Object[0]), thA2);
                }
                break;
            default:
                y5Var.a();
                break;
        }
    }

    public /* synthetic */ o1(p1 p1Var, y5 y5Var, int i) {
        this.a = i;
        this.b = y5Var;
    }
}
