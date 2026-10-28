package defpackage;

import android.content.Context;
import android.os.Handler;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class gj implements y5 {
    public final /* synthetic */ int a;
    public final /* synthetic */ nj b;

    public /* synthetic */ gj(nj njVar, int i) {
        this.a = i;
        this.b = njVar;
    }

    @Override // defpackage.y5
    public final Object a() {
        int i = this.a;
        nj njVar = this.b;
        switch (i) {
            case 0:
                Context applicationContext = njVar.a.getApplicationContext();
                applicationContext.getClass();
                return r4.C.p(applicationContext);
            default:
                Handler handler = njVar.c;
                hj hjVar = njVar.x;
                handler.removeCallbacks(hjVar);
                handler.postDelayed(hjVar, 150L);
                return ti.a;
        }
    }
}
