package defpackage;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class a3 implements ServiceConnection {
    @Override // android.content.ServiceConnection
    public final void onBindingDied(ComponentName componentName) {
        k8.a = null;
        k8.b = false;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        k8.a = iBinder;
        k8.b = false;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        k8.a = null;
        k8.b = false;
    }
}
