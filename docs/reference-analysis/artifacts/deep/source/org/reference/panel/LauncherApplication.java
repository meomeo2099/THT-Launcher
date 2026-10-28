package org.reference.panel;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.content.res.Configuration;
import defpackage.j0;
import defpackage.jd;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class LauncherApplication extends Application {
    @Override // android.content.ContextWrapper
    public final void attachBaseContext(Context context) {
        context.getClass();
        super.attachBaseContext(j0.e(context));
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        configuration.getClass();
        super.onConfigurationChanged(configuration);
        j0.a(this);
    }

    @Override // android.app.Application
    public final void onCreate() {
        super.onCreate();
        Set set = RadioCommandReceiver.a;
        IntentFilter intentFilter = new IntentFilter();
        Iterator it = RadioCommandReceiver.a.iterator();
        while (it.hasNext()) {
            intentFilter.addAction("org.reference.panel." + ((String) it.next()));
        }
        try {
            Context applicationContext = getApplicationContext();
            Set set2 = RadioCommandReceiver.a;
            applicationContext.registerReceiver(new jd(), intentFilter);
        } catch (Throwable unused) {
        }
    }
}
