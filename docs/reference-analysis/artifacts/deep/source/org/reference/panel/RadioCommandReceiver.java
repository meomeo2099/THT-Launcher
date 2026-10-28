package org.reference.panel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import defpackage.fi;
import defpackage.pd;
import defpackage.th;
import defpackage.x0;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class RadioCommandReceiver extends BroadcastReceiver {
    public static final Set a = x0.Y0(new String[]{"RADIO_NEXT", "RADIO_PREVIOUS", "RADIO_SEEK_UP", "RADIO_SEEK_DOWN", "RADIO_PLAY_PAUSE", "RADIO_PLAY", "RADIO_PAUSE", "RADIO_FAVORITE"});

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        context.getClass();
        intent.getClass();
        String action = intent.getAction();
        if (action != null) {
            String strC = th.C(action, "org.reference.panel.");
            if (a.contains(strC)) {
                fi fiVar = pd.a;
                pd.c(context, strC, 10);
            }
        }
    }
}
