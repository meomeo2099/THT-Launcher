package defpackage;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.media.AudioManager;
import android.media.session.MediaController;
import android.media.session.MediaSessionManager;
import android.media.session.PlaybackState;
import android.os.Handler;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.reference.panel.BootPlaybackService;
import org.reference.panel.MainActivity;
import org.reference.panel.MusicNotificationListener;
import org.reference.panel.b;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class r1 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ r1(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    /* JADX WARN: Code duplicated, block: B:208:0x03cd  */
    /* JADX WARN: Code duplicated, block: B:209:0x03d1  */
    @Override // java.lang.Runnable
    public final void run() {
        Object ffVar;
        Object ffVar2;
        MediaController mediaController;
        int i;
        PlaybackState playbackState;
        Object ffVar3;
        long j;
        PowerManager powerManager;
        KeyguardManager keyguardManager;
        Object obj = null;
        switch (this.a) {
            case 0:
                s1 s1Var = (s1) this.b;
                z0 z0Var = (z0) s1Var.f;
                try {
                    s1.a(s1Var);
                    ffVar = ti.a;
                    break;
                } catch (Throwable th) {
                    ffVar = new ff(th);
                }
                Throwable thA = gf.a(ffVar);
                if (thA != null) {
                    Log.w("ReferencePanel.Auto", si.a("automations", new Object[0]), thA);
                }
                s1Var.b.postDelayed(this, (z0Var.a.getBoolean("auto_enabled", false) && z0Var.b("close", false)) ? 2000L : 5000L);
                return;
            case 1:
                BootPlaybackService bootPlaybackService = (BootPlaybackService) this.b;
                try {
                    ffVar2 = ((MediaSessionManager) bootPlaybackService.getSystemService(MediaSessionManager.class)).getActiveSessions(new ComponentName(bootPlaybackService, (Class<?>) MusicNotificationListener.class));
                } catch (Throwable th2) {
                    ffVar2 = new ff(th2);
                }
                if (ffVar2 instanceof ff) {
                    ffVar2 = null;
                }
                List list = (List) ffVar2;
                if (list == null) {
                    bootPlaybackService.stopSelf();
                    return;
                }
                for (Object obj2 : list) {
                    if (k8.e(((MediaController) obj2).getPackageName(), bootPlaybackService.d)) {
                        obj = obj2;
                        mediaController = (MediaController) obj;
                        if (mediaController == null && (playbackState = mediaController.getPlaybackState()) != null && playbackState.getState() == 3) {
                            bootPlaybackService.stopSelf();
                            return;
                        }
                        i = bootPlaybackService.c + 1;
                        bootPlaybackService.c = i;
                        if (i >= 25) {
                            bootPlaybackService.stopSelf();
                            return;
                        } else {
                            bootPlaybackService.a.postDelayed(this, 3000L);
                            return;
                        }
                    }
                }
                mediaController = (MediaController) obj;
                if (mediaController == null) {
                }
                i = bootPlaybackService.c + 1;
                bootPlaybackService.c = i;
                if (i >= 25) {
                    bootPlaybackService.stopSelf();
                    return;
                } else {
                    bootPlaybackService.a.postDelayed(this, 3000L);
                    return;
                }
                break;
            case 2:
                s1 s1Var2 = (s1) this.b;
                if (s1Var2.c) {
                    List listI = s1Var2.i();
                    if (listI == null) {
                        AudioManager audioManager = (AudioManager) s1Var2.g;
                        if (audioManager != null && audioManager.isMusicActive()) {
                            s1Var2.e(127);
                            s1Var2.e = true;
                        }
                    } else {
                        ArrayList arrayList = new ArrayList();
                        for (Object obj3 : listI) {
                            PlaybackState playbackState2 = ((MediaController) obj3).getPlaybackState();
                            if (playbackState2 != null && playbackState2.getState() == 3) {
                                arrayList.add(obj3);
                            }
                        }
                        int size = arrayList.size();
                        int i2 = 0;
                        while (i2 < size) {
                            int i3 = i2 + 1;
                            MediaController mediaController2 = (MediaController) arrayList.get(i2);
                            try {
                                PlaybackState playbackState3 = mediaController2.getPlaybackState();
                                long actions = playbackState3 != null ? playbackState3.getActions() : 0L;
                                if ((2 & actions) != 0 || (actions & 512) == 0) {
                                    mediaController2.getTransportControls().pause();
                                } else {
                                    long jUptimeMillis = SystemClock.uptimeMillis();
                                    int[] iArr = {0, 1};
                                    for (int i4 = 0; i4 < 2; i4++) {
                                        mediaController2.dispatchMediaButtonEvent(new KeyEvent(jUptimeMillis, jUptimeMillis, iArr[i4], 127, 0));
                                    }
                                }
                                ffVar3 = Boolean.TRUE;
                            } catch (Throwable th3) {
                                ffVar3 = new ff(th3);
                            }
                            Object obj4 = Boolean.FALSE;
                            if (ffVar3 instanceof ff) {
                                ffVar3 = obj4;
                            }
                            if (((Boolean) ffVar3).booleanValue()) {
                                s1Var2.d.add(mediaController2.getPackageName());
                                mediaController2.getPackageName();
                            }
                            i2 = i3;
                        }
                    }
                    s1Var2.b.postDelayed(this, 1000L);
                    return;
                }
                return;
            case 3:
                id idVar = id.c;
                b bVar = (b) this.b;
                tc tcVar = bVar.x;
                if (SystemClock.uptimeMillis() >= tcVar.b) {
                    tcVar.a = null;
                    tcVar.b = 0L;
                    tcVar.c = 0;
                }
                uc ucVar = tcVar.a;
                if (ucVar == null) {
                    return;
                }
                if (!bVar.a()) {
                    bVar.b();
                    return;
                }
                MainActivity mainActivity = bVar.a;
                mainActivity.getClass();
                if (!mainActivity.isFinishing() && !mainActivity.isDestroyed() && mainActivity.getTaskId() >= 0 && (powerManager = (PowerManager) mainActivity.getSystemService(PowerManager.class)) != null && (keyguardManager = (KeyguardManager) mainActivity.getSystemService(KeyguardManager.class)) != null && powerManager.isInteractive() && !keyguardManager.isKeyguardLocked()) {
                    try {
                        Object objInvoke = ActivityManager.class.getMethod("getService", null).invoke(null, null);
                        if (objInvoke != null) {
                            Class<?> cls = Class.forName("android.app.IActivityManager");
                            id idVarV = pi.v(ucVar, k8.I(), new uc(mainActivity.getWindowManager().getDefaultDisplay().getDisplayId(), mainActivity.getTaskId(), mainActivity.getPackageName()));
                            if (idVarV == id.a) {
                                cls.getMethod("setFocusedTask", Integer.TYPE).invoke(objInvoke, Integer.valueOf(mainActivity.getTaskId()));
                            }
                            idVar = idVarV;
                            break;
                        }
                    } catch (Exception e) {
                        Log.w("NavigationPreview", "Cannot return input to launcher", e);
                    }
                }
                int iOrdinal = idVar.ordinal();
                if (iOrdinal != 0 && iOrdinal != 1) {
                    if (iOrdinal != 2) {
                        throw new vc();
                    }
                    bVar.b();
                    return;
                }
                Handler handler = bVar.k;
                int i5 = tcVar.c;
                tcVar.c = i5 + 1;
                switch (i5) {
                    case 0:
                        j = 32;
                        break;
                    case 1:
                        j = 64;
                        break;
                    case 2:
                        j = 120;
                        break;
                    case 3:
                        j = 240;
                        break;
                    case 4:
                    case 5:
                    case 6:
                        j = 400;
                        break;
                    default:
                        j = 1000;
                        break;
                }
                handler.postDelayed(this, j);
                return;
            case 4:
                a7 a7Var = (a7) this.b;
                a7.a(a7Var);
                a7Var.d.postDelayed(this, 1000L);
                return;
            case 5:
                m8 m8Var = (m8) this.b;
                m8Var.invalidate();
                ((Handler) m8Var.d).postDelayed(this, 10000L);
                return;
            case 6:
                k9 k9Var = (k9) this.b;
                k9Var.a();
                k9Var.f.postDelayed(this, k9Var.i != null ? 500L : 3000L);
                return;
            case 7:
                p9 p9Var = (p9) this.b;
                if (p9Var.h == null) {
                    p9Var.invalidate();
                }
                p9Var.g.postDelayed(this, 1000L);
                return;
            case 8:
                v9 v9Var = (v9) this.b;
                v9Var.invalidate();
                v9Var.b.postDelayed(this, 10000L);
                return;
            case 9:
                nb nbVar = (nb) this.b;
                if (nbVar.h) {
                    if (nbVar.e == null) {
                        nb nbVar2 = (nb) this.b;
                        if (!nbVar2.g && !nbVar2.i) {
                            nbVar2.a();
                        }
                    }
                    ((nb) this.b).c();
                    ((nb) this.b).d.postDelayed(this, 2500L);
                    return;
                }
                return;
            case 10:
                gc gcVar = (gc) this.b;
                if (gcVar.l) {
                    if (((NotificationManager) gcVar.c.getSystemService(NotificationManager.class)).isNotificationListenerAccessGranted(gcVar.e) != gcVar.n) {
                        gcVar.t();
                    } else {
                        gcVar.d();
                    }
                    gcVar.f.postDelayed(this, 1000L);
                    return;
                }
                return;
            case 11:
                if (((fe) this.b).d.isEmpty()) {
                    return;
                }
                if (((fe) this.b).e == null) {
                    fe feVar = (fe) this.b;
                    if (!feVar.h && !feVar.i) {
                        feVar.a();
                    }
                }
                ((fe) this.b).i();
                ((fe) this.b).b.postDelayed(this, 2000L);
                return;
            case 12:
                qf qfVar = (qf) this.b;
                qfVar.invalidate();
                qfVar.t.postDelayed(this, qfVar.g ? 1000L : 10000L);
                return;
            case 13:
                try {
                    ni.a((ni) this.b);
                    ni.N = ((ni) this.b).c();
                    break;
                } catch (Throwable th4) {
                    Log.w("ReferencePanel.Stats", si.a("collecting_statistics", new Object[0]), th4);
                }
                if (((ni) this.b).f) {
                    ((ni) this.b).e.postDelayed(this, 2000L);
                    return;
                }
                return;
            case 14:
                nj njVar = (nj) this.b;
                if (njVar.q && SystemClock.elapsedRealtime() - njVar.r >= 20000) {
                    Log.e("ReferencePanel.Vehicle", si.a("vehicle_read_timed_out_after_20_s", new Object[0]));
                    njVar.p++;
                    njVar.q = false;
                    ExecutorService executorService = njVar.d;
                    ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
                    executorServiceNewSingleThreadExecutor.getClass();
                    njVar.d = executorServiceNewSingleThreadExecutor;
                    executorService.shutdownNow();
                }
                ((nj) this.b).j();
                if (((nj) this.b).n) {
                    ((nj) this.b).c.postDelayed(this, 1500L);
                    return;
                }
                return;
            default:
                vj vjVar = (vj) this.b;
                vjVar.e(false);
                if (vjVar.f) {
                    vjVar.d.postDelayed(this, 300000L);
                    return;
                }
                return;
        }
    }
}
