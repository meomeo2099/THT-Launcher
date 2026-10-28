package org.reference.panel;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import defpackage.ff;
import defpackage.si;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class UpdateInstallReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Object ffVar;
        context.getClass();
        intent.getClass();
        int intExtra = intent.getIntExtra("android.content.pm.extra.STATUS", 1);
        if (intExtra == -1) {
            Intent intent2 = (Intent) intent.getParcelableExtra("android.intent.extra.INTENT");
            if (intent2 != null) {
                context.startActivity(intent2.addFlags(268435456));
                return;
            }
            return;
        }
        if (intExtra != 0) {
            String stringExtra = intent.getStringExtra("android.content.pm.extra.STATUS_MESSAGE");
            if (stringExtra == null) {
                stringExtra = "?";
            }
            Log.w("ReferencePanel.Update", "install failed: ".concat(stringExtra));
            try {
                ffVar = context.getPackageManager().getActivityInfo(new ComponentName("com.teto.inputservice", "com.teto.inputservice.autoboot.ApkInstallActivity"), 0);
            } catch (Throwable th) {
                ffVar = new ff(th);
            }
            Toast.makeText(context, !(ffVar instanceof ff) ? si.a("update_failed_try_freetuga", stringExtra) : si.a("update_failed_0", stringExtra), 1).show();
        }
    }
}
