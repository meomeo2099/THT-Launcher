package defpackage;

import android.os.Handler;
import android.os.Parcel;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ae implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ fe b;

    public /* synthetic */ ae(fe feVar, int i) {
        this.a = i;
        this.b = feVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.a;
        fe feVar = this.b;
        switch (i) {
            case 0:
                Iterator it = feVar.d.iterator();
                while (it.hasNext()) {
                    ((y5) it.next()).a();
                }
                break;
            case 1:
                Handler handler = feVar.b;
                r1 r1Var = feVar.n;
                handler.removeCallbacks(r1Var);
                r1Var.run();
                break;
            case 2:
                feVar.g();
                Parcel parcelB = feVar.b(18, new w(25));
                if (parcelB != null) {
                    parcelB.recycle();
                }
                feVar.e();
                break;
            case 3:
                feVar.i();
                break;
            case 4:
                feVar.f("died");
                break;
            case 5:
                if (feVar.h) {
                    try {
                        feVar.a.unbindService(feVar.k);
                        break;
                    } catch (Throwable unused) {
                    }
                }
                feVar.h = false;
                feVar.f("unbound");
                feVar.f("binding died");
                break;
            default:
                feVar.f("disconnected");
                break;
        }
    }
}
