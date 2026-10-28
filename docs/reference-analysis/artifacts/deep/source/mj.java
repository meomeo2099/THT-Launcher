package defpackage;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class mj implements ServiceConnection {
    public final /* synthetic */ nj a;
    public final /* synthetic */ int b;
    public final /* synthetic */ Class c;

    public mj(nj njVar, int i, Class cls) {
        this.a = njVar;
        this.b = i;
        this.c = cls;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (this.a.n && this.a.o == this.b) {
            nj njVar = this.a;
            Object obj = njVar.k;
            if (njVar.d.isShutdown()) {
                return;
            }
            nj njVar2 = this.a;
            try {
                njVar2.d.execute(new bc(this, njVar2, this.c, obj, this.b));
            } catch (Throwable unused) {
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        if (this.a.o != this.b) {
            return;
        }
        this.a.l = null;
    }
}
