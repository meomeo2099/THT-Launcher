package org.reference.panel;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import defpackage.k8;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class BootReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        context.getClass();
        intent.getClass();
        if (k8.e(intent.getAction(), "android.intent.action.BOOT_COMPLETED")) {
            int i = AutomationService.c;
            k8.F0(context);
            k8.E0(context);
            if (context.getSharedPreferences("panel", 0).getBoolean("autostart", true)) {
                Intent intent2 = new Intent(context, (Class<?>) MainActivity.class);
                intent2.setAction("android.intent.action.MAIN");
                intent2.addCategory("android.intent.category.LAUNCHER");
                intent2.addFlags(872415232);
                try {
                    context.startActivity(intent2);
                } catch (ActivityNotFoundException e) {
                    Log.w("ReferencePanel", "Panel activity unavailable after boot", e);
                } catch (SecurityException e2) {
                    Log.w("ReferencePanel", "System refused startup after boot", e2);
                }
            }
        }
    }
}
