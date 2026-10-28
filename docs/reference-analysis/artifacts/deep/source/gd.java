package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.MediaSessionManager;
import android.media.session.PlaybackState;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.reference.panel.MusicNotificationListener;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class gd implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ hd b;
    public final /* synthetic */ String c;
    public final /* synthetic */ long d;
    public final /* synthetic */ ed e;
    public final /* synthetic */ y5 f;
    public final /* synthetic */ je g;
    public final /* synthetic */ je h;

    public gd(int i, hd hdVar, String str, long j, ed edVar, y5 y5Var, je jeVar, je jeVar2) {
        this.a = i;
        this.b = hdVar;
        this.c = str;
        this.d = j;
        this.e = edVar;
        this.f = y5Var;
        this.g = jeVar;
        this.h = jeVar2;
    }

    /* JADX WARN: Code duplicated, block: B:109:0x01ca  */
    /* JADX WARN: Code duplicated, block: B:236:0x00fe A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:237:0x0122 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:240:? A[LOOP:8: B:54:0x0105->B:240:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:52:0x00fe A[EDGE_INSN: B:52:0x00fe->B:64:0x0123 BREAK  A[LOOP:8: B:54:0x0105->B:240:?]] */
    /* JADX WARN: Code duplicated, block: B:53:0x0100  */
    /* JADX WARN: Code duplicated, block: B:55:0x0107  */
    /* JADX WARN: Code duplicated, block: B:57:0x0115  */
    /* JADX WARN: Code duplicated, block: B:58:0x011a  */
    /* JADX WARN: Code duplicated, block: B:60:0x011d  */
    /* JADX WARN: Code duplicated, block: B:61:0x011f  */
    /* JADX WARN: Code duplicated, block: B:65:0x0125 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:66:0x0127  */
    /* JADX WARN: Code duplicated, block: B:67:0x0132  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [ff] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v18, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r7v19 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v20 */
    @Override // java.lang.Runnable
    public final void run() {
        ?? ffVar;
        MediaController mediaController;
        int size;
        int i;
        PlaybackState playbackState;
        int state;
        boolean z;
        boolean z2;
        boolean zE;
        boolean z3;
        boolean z4;
        boolean z5;
        Object ffVar2;
        Object ffVar3;
        Object next;
        Object obj;
        PlaybackState playbackState2;
        int i2;
        MediaSession.Token token;
        Object ffVar4;
        String str = this.c;
        hd hdVar = this.b;
        int i3 = hdVar.e;
        Handler handler = hdVar.b;
        Context context = hdVar.a;
        int i4 = this.a;
        if (i4 != i3) {
            return;
        }
        try {
            List<MediaController> activeSessions = ((MediaSessionManager) context.getSystemService(MediaSessionManager.class)).getActiveSessions(new ComponentName(context, (Class<?>) MusicNotificationListener.class));
            activeSessions.getClass();
            ffVar = new ArrayList();
            for (Object obj2 : activeSessions) {
                if (k8.e(((MediaController) obj2).getPackageName(), str)) {
                    ffVar.add(obj2);
                }
            }
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        boolean z6 = ffVar instanceof ff;
        ?? r7 = ffVar;
        if (z6) {
            r7 = u4.a;
        }
        List list = (List) r7;
        Object obj3 = MusicNotificationListener.a;
        hc hcVarL = k8.L(str);
        if (hcVarL == null || (token = hcVarL.g) == null) {
            mediaController = null;
        } else {
            try {
                ffVar4 = new MediaController(context, token);
            } catch (Throwable th2) {
                ffVar4 = new ff(th2);
            }
            if (ffVar4 instanceof ff) {
                ffVar4 = null;
            }
            mediaController = (MediaController) ffVar4;
        }
        ArrayList arrayListD0 = i3.D0(list, x0.S0(new MediaController[]{hdVar.d, mediaController}));
        ArrayList arrayList = new ArrayList();
        int size2 = arrayListD0.size();
        int i5 = 0;
        while (i5 < size2) {
            Object obj4 = arrayListD0.get(i5);
            i5++;
            if (k8.e(((MediaController) obj4).getPackageName(), str)) {
                arrayList.add(obj4);
            }
        }
        HashSet hashSet = new HashSet();
        ArrayList arrayList2 = new ArrayList();
        int size3 = arrayList.size();
        int i6 = 0;
        while (i6 < size3) {
            Object obj5 = arrayList.get(i6);
            i6++;
            if (hashSet.add(((MediaController) obj5).getSessionToken())) {
                arrayList2.add(obj5);
            }
        }
        if (arrayList2.isEmpty()) {
            if (arrayList2.isEmpty()) {
                z2 = true;
                break;
            }
            size = arrayList2.size();
            i = 0;
            while (true) {
                if (i < size) {
                    z2 = true;
                    break;
                }
                Object obj6 = arrayList2.get(i);
                i++;
                playbackState = ((MediaController) obj6).getPlaybackState();
                if (playbackState != null) {
                    state = playbackState.getState();
                } else {
                    state = 0;
                }
                if (state != 0) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    z2 = false;
                    break;
                }
            }
            if (z2) {
                if (hcVarL != null) {
                    zE = k8.e(hcVarL.a(), Boolean.TRUE);
                } else {
                    zE = false;
                }
                z3 = zE;
            }
        } else {
            int size4 = arrayList2.size();
            int i7 = 0;
            while (true) {
                if (i7 < size4) {
                    Object obj7 = arrayList2.get(i7);
                    i7++;
                    PlaybackState playbackState3 = ((MediaController) obj7).getPlaybackState();
                    if (playbackState3 == null || playbackState3.getState() != 3) {
                    }
                } else {
                    if (arrayList2.isEmpty()) {
                        z2 = true;
                        break;
                    }
                    size = arrayList2.size();
                    i = 0;
                    while (true) {
                        if (i < size) {
                            z2 = true;
                            break;
                        }
                        Object obj8 = arrayList2.get(i);
                        i++;
                        playbackState = ((MediaController) obj8).getPlaybackState();
                        if (playbackState != null) {
                            state = playbackState.getState();
                        } else {
                            state = 0;
                        }
                        if (state != 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (z) {
                            z2 = false;
                            break;
                        }
                    }
                    if (z2) {
                        if (hcVarL != null) {
                            zE = k8.e(hcVarL.a(), Boolean.TRUE);
                        } else {
                            zE = false;
                        }
                        if (zE) {
                        }
                    }
                }
            }
        }
        if (z3) {
            hdVar.g = null;
            handler.postDelayed(new t0(i4, hdVar, 7), 3000L);
            return;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime() - this.d;
        ed edVar = this.e;
        edVar.getClass();
        if (jElapsedRealtime >= 20000) {
            hdVar.c();
            this.f.a();
            return;
        }
        if (arrayList2.isEmpty()) {
            z4 = false;
            break;
        }
        int size5 = arrayList2.size();
        int i8 = 0;
        while (true) {
            if (i8 >= size5) {
                z4 = false;
                break;
            }
            Object obj9 = arrayList2.get(i8);
            i8++;
            List listI0 = j3.i0(6, 8);
            PlaybackState playbackState4 = ((MediaController) obj9).getPlaybackState();
            if (i3.r0(listI0, playbackState4 != null ? Integer.valueOf(playbackState4.getState()) : null)) {
                z4 = true;
                break;
            }
        }
        if (z4 || edVar.b || (i2 = edVar.a) >= 3) {
            z5 = false;
        } else {
            if (jElapsedRealtime - 0 >= (i2 != 0 ? i2 != 1 ? 10000L : 4000L : 0L)) {
                z5 = true;
            } else {
                z5 = false;
            }
        }
        je jeVar = this.g;
        if (z5) {
            int size6 = arrayList2.size();
            int i9 = 0;
            do {
                if (i9 >= size6) {
                    obj = null;
                    break;
                } else {
                    obj = arrayList2.get(i9);
                    i9++;
                    playbackState2 = ((MediaController) obj).getPlaybackState();
                }
            } while (!((playbackState2 != null ? playbackState2.getActions() : 0L) != 0));
            MediaController mediaController2 = (MediaController) obj;
            if (mediaController2 == null) {
                mediaController2 = (MediaController) i3.v0(arrayList2);
            }
            zb zbVarO = pi.O(mediaController2, str, xb.a);
            boolean z7 = zbVarO.a;
            if (z7) {
                edVar.a++;
                if (zbVarO.b) {
                    edVar.b = true;
                }
            }
            jeVar.a = jeVar.a || z7;
        }
        if (!z4 && !jeVar.a && jElapsedRealtime >= 1000) {
            je jeVar2 = this.h;
            if (!jeVar2.a) {
                jeVar2.a = true;
                try {
                    List<ResolveInfo> listQueryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(new Intent("android.intent.action.MEDIA_BUTTON").setPackage(str), 0);
                    listQueryBroadcastReceivers.getClass();
                    Iterator it = listQueryBroadcastReceivers.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it.next();
                        ActivityInfo activityInfo = ((ResolveInfo) next).activityInfo;
                        if (activityInfo != null && activityInfo.enabled && activityInfo.exported) {
                            break;
                        }
                    }
                    ResolveInfo resolveInfo = (ResolveInfo) next;
                    ffVar2 = resolveInfo != null ? resolveInfo.activityInfo : null;
                } catch (Throwable th3) {
                    ffVar2 = new ff(th3);
                }
                ActivityInfo activityInfo2 = (ActivityInfo) (ffVar2 instanceof ff ? null : ffVar2);
                if (activityInfo2 != null) {
                    long jUptimeMillis = SystemClock.uptimeMillis();
                    int[] iArr = {0, 1};
                    for (int i10 = 0; i10 < 2; i10++) {
                        try {
                            context.sendBroadcast(new Intent("android.intent.action.MEDIA_BUTTON").setComponent(new ComponentName(activityInfo2.packageName, activityInfo2.name)).addFlags(268435456).putExtra("android.intent.extra.KEY_EVENT", new KeyEvent(jUptimeMillis, jUptimeMillis, iArr[i10], 126, 0)));
                            ffVar3 = ti.a;
                        } catch (Throwable th4) {
                            ffVar3 = new ff(th4);
                        }
                        Throwable thA = gf.a(ffVar3);
                        if (thA != null) {
                            Log.w("ReferencePanel.Music", "Media button rejected by " + str, thA);
                        }
                    }
                }
            }
        }
        handler.postDelayed(this, 500L);
    }
}
