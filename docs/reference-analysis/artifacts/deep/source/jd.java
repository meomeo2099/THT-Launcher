package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.Set;
import org.reference.panel.RadioCommandReceiver;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class jd extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        String action;
        context.getClass();
        intent.getClass();
        if (intent.getPackage() == null && intent.getComponent() == null && (action = intent.getAction()) != null) {
            String strC = th.C(action, "org.reference.panel.");
            Set set = RadioCommandReceiver.a;
            if (RadioCommandReceiver.a.contains(strC)) {
                fi fiVar = pd.a;
                pd.c(context, strC, 10);
            }
        }
    }
}
