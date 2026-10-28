package defpackage;

import android.content.ComponentName;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class dh implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ ComponentName b;
    public final /* synthetic */ j6 c;
    public final /* synthetic */ le d;
    public final /* synthetic */ lg e;

    public dh(int i, ComponentName componentName, j6 j6Var, le leVar, lg lgVar) {
        this.a = i;
        this.b = componentName;
        this.c = j6Var;
        this.d = leVar;
        this.e = lgVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.a != eh.c) {
            return;
        }
        String packageName = this.b.getPackageName();
        packageName.getClass();
        Integer numL = eh.l(packageName);
        if (numL != null) {
            this.c.b(numL);
            return;
        }
        le leVar = this.d;
        int i = leVar.a + 1;
        leVar.a = i;
        if (i < 30) {
            eh.b.postDelayed(this, 150L);
        } else {
            this.e.b(si.a("split_failed", new Object[0]));
        }
    }
}
