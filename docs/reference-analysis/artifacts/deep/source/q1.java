package defpackage;

import android.util.Log;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class q1 implements Runnable {
    public final /* synthetic */ le a;
    public final /* synthetic */ String b;
    public final /* synthetic */ s1 c;
    public final /* synthetic */ y5 d;
    public final /* synthetic */ y5 e;

    public q1(le leVar, String str, s1 s1Var, y5 y5Var, y5 y5Var2) {
        this.a = leVar;
        this.b = str;
        this.c = s1Var;
        this.d = y5Var;
        this.e = y5Var2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object ffVar;
        try {
            ffVar = (Boolean) this.d.a();
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        if (ffVar instanceof ff) {
            ffVar = null;
        }
        if (k8.e((Boolean) ffVar, Boolean.FALSE)) {
            le leVar = this.a;
            int i = leVar.a;
            if (i > 0) {
                leVar.a = i - 1;
                try {
                    ((Boolean) this.e.a()).getClass();
                } catch (Throwable unused) {
                }
                this.c.b.postDelayed(this, 4000L);
            } else {
                Log.w("ReferencePanel.Auto", this.b + ": the car did not take it");
            }
        }
    }
}
