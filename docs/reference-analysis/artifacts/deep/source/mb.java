package defpackage;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class mb implements ServiceConnection {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ mb(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.content.ServiceConnection
    public final void onBindingDied(ComponentName componentName) {
        switch (this.a) {
            case 0:
                ((nb) this.b).e = null;
                nb nbVar = (nb) this.b;
                nbVar.g = false;
                nbVar.e(null);
                break;
            default:
                fe feVar = (fe) this.b;
                feVar.b.post(new ae(feVar, 5));
                break;
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                nb nbVar = (nb) obj;
                nbVar.d.post(new o(this, iBinder, nbVar, 4));
                break;
            default:
                fe feVar = (fe) obj;
                feVar.b.post(new o(this, iBinder, feVar, 6));
                break;
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        switch (this.a) {
            case 0:
                ((nb) this.b).e = null;
                ((nb) this.b).e(null);
                break;
            default:
                fe feVar = (fe) this.b;
                feVar.b.post(new ae(feVar, 6));
                break;
        }
    }
}
