package org.reference.panel;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import defpackage.hd;
import defpackage.i2;
import defpackage.k8;
import defpackage.r1;
import defpackage.si;
import defpackage.th;
import org.reference.panel.R;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class BootPlaybackService extends Service {
    public static final /* synthetic */ int g = 0;
    public boolean b;
    public int c;
    public hd e;
    public final Handler a = new Handler(Looper.getMainLooper());
    public String d = "";
    public final r1 f = new r1(1, this);

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public final void onDestroy() {
        this.a.removeCallbacksAndMessages(null);
        hd hdVar = this.e;
        if (hdVar != null) {
            hdVar.c();
        }
        this.e = null;
        stopForeground(true);
        super.onDestroy();
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(new NotificationChannel("boot_music", si.a("starting_music", new Object[0]), 2));
        startForeground(73, new Notification.Builder(this, "boot_music").setSmallIcon(R.drawable.ic_notification).setContentTitle("ReferencePanel").setContentText(si.a("starting_the_selected_player", new Object[0])).build());
        SharedPreferences sharedPreferences = getSharedPreferences("panel", 0);
        String string = sharedPreferences.getString("slot_1", "");
        if (string == null) {
            string = "";
        }
        ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(string);
        String packageName = componentNameUnflattenFromString != null ? componentNameUnflattenFromString.getPackageName() : null;
        this.d = packageName != null ? packageName : "";
        if ((!k8.e(intent != null ? intent.getAction() : null, "org.reference.panel.PLAY_PREFERRED") && !sharedPreferences.getBoolean("music_autoplay", false)) || th.A(this.d) || !notificationManager.isNotificationListenerAccessGranted(new ComponentName(this, (Class<?>) MusicNotificationListener.class))) {
            stopSelf();
            return 2;
        }
        if (!this.b) {
            this.b = true;
            hd hdVar = new hd(this);
            this.e = hdVar;
            hdVar.a(this.d, new i2(3, this));
            this.a.postDelayed(this.f, 2000L);
        }
        return 2;
    }
}
