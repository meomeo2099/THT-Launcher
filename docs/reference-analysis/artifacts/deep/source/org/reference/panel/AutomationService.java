package org.reference.panel;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.util.Log;
import defpackage.ff;
import defpackage.gf;
import defpackage.hd;
import defpackage.k2;
import defpackage.k8;
import defpackage.ni;
import defpackage.q;
import defpackage.r1;
import defpackage.r4;
import defpackage.s1;
import defpackage.si;
import defpackage.ti;
import defpackage.y6;
import defpackage.yd;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import org.reference.panel.R;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class AutomationService extends Service {
    public static final /* synthetic */ int c = 0;
    public s1 a;
    public s1 b;

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public final void onDestroy() {
        s1 s1Var = this.a;
        if (s1Var != null) {
            s1Var.b.removeCallbacksAndMessages(null);
            ni niVar = (ni) s1Var.i;
            niVar.e.post(new yd(niVar, true, 1 == true ? 1 : 0));
            y6 y6Var = (y6) s1Var.h;
            Looper looper = ((HandlerThread) s1Var.g).getLooper();
            looper.getClass();
            y6Var.a(false, looper);
        }
        this.a = null;
        s1 s1Var2 = this.b;
        if (s1Var2 != null) {
            ArrayList arrayList = (ArrayList) s1Var2.h;
            try {
                TelephonyManager telephonyManager = (TelephonyManager) s1Var2.f;
                if (telephonyManager != null) {
                    telephonyManager.listen((k2) s1Var2.i, 0);
                }
            } catch (Throwable unused) {
            }
            s1Var2.b.removeCallbacksAndMessages(null);
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                ((hd) obj).c();
            }
            arrayList.clear();
            s1Var2.c = false;
        }
        this.b = null;
        boolean z = q.a;
        q.f();
        super.onDestroy();
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        CopyOnWriteArraySet copyOnWriteArraySet;
        Object ffVar;
        ti tiVar;
        Object ffVar2;
        Integer numValueOf;
        if (!k8.X(this)) {
            stopSelf();
            return 2;
        }
        ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(new NotificationChannel("automation", si.a("automations_27", new Object[0]), 1));
        startForeground(42, new Notification.Builder(this, "automation").setContentTitle(si.a("ReferencePanel_automations", new Object[0])).setContentText(si.a("monitoring_engine_starts", new Object[0])).setSmallIcon(R.drawable.ic_notification).build());
        s1 s1Var = this.a;
        if (s1Var != null) {
            s1Var.j();
        } else {
            Context applicationContext = getApplicationContext();
            applicationContext.getClass();
            s1 s1Var2 = new s1(applicationContext, 0);
            Handler handler = s1Var2.b;
            r1 r1Var = (r1) s1Var2.j;
            handler.removeCallbacks(r1Var);
            handler.post(r1Var);
            s1Var2.j();
            this.a = s1Var2;
        }
        if (this.b == null) {
            Context applicationContext2 = getApplicationContext();
            applicationContext2.getClass();
            s1 s1Var3 = new s1(applicationContext2, 1);
            TelephonyManager telephonyManager = (TelephonyManager) s1Var3.f;
            if (telephonyManager != null) {
                try {
                    telephonyManager.listen((k2) s1Var3.i, 32);
                    tiVar = ti.a;
                } catch (Throwable th) {
                    ffVar = new ff(th);
                }
            } else {
                tiVar = null;
            }
            ffVar = tiVar;
            Throwable thA = gf.a(ffVar);
            if (thA != null) {
                Log.w("ReferencePanel.Call", "call state unavailable", thA);
            }
            if (telephonyManager != null) {
                try {
                    numValueOf = Integer.valueOf(telephonyManager.getCallState());
                } catch (Throwable th2) {
                    ffVar2 = new ff(th2);
                }
            } else {
                numValueOf = null;
            }
            ffVar2 = numValueOf;
            Integer num = (Integer) (ffVar2 instanceof ff ? null : ffVar2);
            if (num != null) {
                s1Var3.f(num.intValue());
            }
            this.b = s1Var3;
        }
        boolean z = q.a;
        if (!q.c(this)) {
            q.f();
        } else if (q.d == null) {
            q.e = getApplicationContext();
            HandlerThread handlerThread = new HandlerThread("ReferencePanel-anton");
            handlerThread.start();
            Handler handler2 = new Handler(handlerThread.getLooper());
            handler2.post(q.l);
            q.d = handler2;
            r4 r4VarP = r4.C.p(this);
            if (r4VarP != null && (copyOnWriteArraySet = r4VarP.w) != null) {
                copyOnWriteArraySet.add(q.j);
            }
        }
        return 1;
    }
}
