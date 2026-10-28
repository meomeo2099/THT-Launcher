package defpackage;

import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.media.MediaMetadata;
import android.media.Rating;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.MediaSessionManager;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.reference.panel.MainActivity;
import org.reference.panel.MusicNotificationListener;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class gc {
    public static final Set H = x0.Y0(new String[]{"com.guhub.radio", "com.ecarx.radio", "com.ecarx.radio.service", "ecarx.radio"});
    public String A;
    public String B;
    public v1 C;
    public final dc D;
    public final r1 E;
    public final Set F;
    public final p1 G;
    public final b2 a;
    public final v1 b;
    public final Context c;
    public final MediaSessionManager d;
    public final ComponentName e;
    public MediaController i;
    public String j;
    public int k;
    public boolean l;
    public boolean m;
    public boolean n;
    public String o;
    public Bitmap p;
    public String q;
    public int r;
    public final hd t;
    public final nb u;
    public final fe v;
    public wa x;
    public final SharedPreferences y;
    public ec z;
    public final Handler f = new Handler(Looper.getMainLooper());
    public final ExecutorService g = Executors.newSingleThreadExecutor();
    public final ArrayList h = new ArrayList();
    public final LinkedHashMap s = new LinkedHashMap();
    public final ac w = new ac(this, 4);

    /* JADX WARN: Type inference failed for: r10v2, types: [dc] */
    public gc(MainActivity mainActivity, b2 b2Var, v1 v1Var) {
        this.a = b2Var;
        this.b = v1Var;
        this.c = mainActivity.getApplicationContext();
        this.d = (MediaSessionManager) mainActivity.getSystemService(MediaSessionManager.class);
        this.e = new ComponentName(mainActivity, (Class<?>) MusicNotificationListener.class);
        this.t = new hd(mainActivity);
        this.u = new nb(mainActivity, new ac(this, 3));
        this.v = fe.o.x(mainActivity);
        SharedPreferences sharedPreferences = mainActivity.getSharedPreferences("panel", 0);
        this.y = sharedPreferences;
        ec ecVar = null;
        String string = sharedPreferences.getString("music_last_package", null);
        if (string != null) {
            boolean z = sharedPreferences.getBoolean("music_last_radio", false);
            String string2 = sharedPreferences.getString("music_last_title", "");
            string2 = string2 == null ? "" : string2;
            String string3 = sharedPreferences.getString("music_last_artist", "");
            ecVar = new ec(z, string, string2, string3 != null ? string3 : "");
        }
        this.z = ecVar;
        this.D = new MediaSessionManager.OnActiveSessionsChangedListener() { // from class: dc
            @Override // android.media.session.MediaSessionManager.OnActiveSessionsChangedListener
            public final void onActiveSessionsChanged(List list) {
                gc gcVar = this.a;
                if (gcVar.l) {
                    if (list == null) {
                        list = u4.a;
                    }
                    gcVar.F(list);
                }
            }
        };
        this.E = new r1(10, this);
        this.F = x0.Y0(new Integer[]{3, 6, 8, 4, 5, 10, 9, 11});
        this.G = new p1();
    }

    public static /* synthetic */ void b(gc gcVar) {
        String packageName;
        MediaController mediaController = gcVar.i;
        if (mediaController == null || (packageName = mediaController.getPackageName()) == null) {
            packageName = gcVar.j;
        }
        gcVar.a(packageName);
    }

    public static boolean e(String str) {
        String strN;
        if (dd.b(str) && (strN = l0.n()) != null) {
            if (th.A(strN)) {
                return false;
            }
            if (!strN.equals(str) && ((!th.v(strN, "bluetooth", true) || (!dd.a(str) && !str.equals("ecarx.xsf.mediacenter"))) && (!str.equals("ecarx.xsf.mediacenter") || !strN.startsWith("com.ecarx") || strN.equals("com.ecarx.radio.service")))) {
                return false;
            }
        }
        return true;
    }

    public static Long g(MediaController mediaController) {
        PlaybackState playbackState = mediaController.getPlaybackState();
        if (playbackState == null) {
            return null;
        }
        if (playbackState.getPosition() < 0) {
            return null;
        }
        if (playbackState.getState() != 3) {
            return Long.valueOf(playbackState.getPosition());
        }
        long position = playbackState.getPosition();
        long playbackSpeed = (long) (playbackState.getPlaybackSpeed() * (SystemClock.elapsedRealtime() - playbackState.getLastPositionUpdateTime()));
        return Long.valueOf(position + (playbackSpeed >= 0 ? playbackSpeed : 0L));
    }

    public static String i(MediaMetadata mediaMetadata, String... strArr) {
        n5 n5Var = new n5(wf.Q0(strArr.length == 0 ? w4.a : new y0(0, strArr), new a(7, mediaMetadata)));
        String str = (String) (!n5Var.hasNext() ? null : n5Var.next());
        return str == null ? "" : str;
    }

    public final void A() {
        if (this.l) {
            return;
        }
        int i = 1;
        this.l = true;
        MusicNotificationListener.c = new ac(this, i);
        MusicNotificationListener.d = new ac(this, 2);
        t();
        this.f.postDelayed(this.E, 1000L);
        if (this.y.getBoolean("radio_widget_enabled", false)) {
            nb nbVar = this.u;
            nbVar.d.post(new kb(nbVar, 3));
        }
        fe feVar = this.v;
        feVar.getClass();
        ac acVar = this.w;
        acVar.getClass();
        feVar.d.add(acVar);
        feVar.b.post(new ae(feVar, i));
        md mdVar = md.a;
        Context context = this.c;
        context.getClass();
        mdVar.c(context);
        fi fiVar = pd.a;
        pd.b(context);
    }

    public final void B(String str) {
        if (k8.e(this.t.g, str)) {
            return;
        }
        this.B = str;
        if (p()) {
            this.v.h();
            lb lbVar = this.u.f;
            if (lbVar != null && lbVar.c()) {
                nb nbVar = this.u;
                nbVar.d.post(new t0(nbVar, 7, 6));
            }
        }
        this.t.a(str, new ac(this, 0));
    }

    public final void C() {
        hd hdVar = this.t;
        hdVar.f.getClass();
        hdVar.c();
        this.B = hdVar.g;
        int i = 1;
        this.k++;
        this.l = false;
        this.f.removeCallbacks(this.E);
        MusicNotificationListener.c = null;
        MusicNotificationListener.d = null;
        if (this.m) {
            try {
                this.d.removeOnActiveSessionsChangedListener(this.D);
            } catch (Throwable unused) {
            }
        }
        this.m = false;
        c();
        this.r++;
        this.p = null;
        this.q = null;
        nb nbVar = this.u;
        nbVar.d.post(new kb(nbVar, i));
        fe feVar = this.v;
        feVar.getClass();
        ac acVar = this.w;
        acVar.getClass();
        feVar.d.remove(acVar);
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0036 A[PHI: r4
      0x0036: PHI (r4v1 java.lang.String) = (r4v0 java.lang.String), (r4v2 java.lang.String), (r4v4 java.lang.String), (r4v9 java.lang.String) binds: [B:4:0x000e, B:6:0x0012, B:12:0x001f, B:19:0x0033] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:22:0x0038 A[Catch: all -> 0x001b, TryCatch #0 {all -> 0x001b, blocks: (B:3:0x0003, B:5:0x0010, B:8:0x0016, B:13:0x0021, B:15:0x002b, B:42:0x0083, B:22:0x0038, B:36:0x0061, B:38:0x0072, B:40:0x0076, B:41:0x0080, B:26:0x0041, B:28:0x0047, B:30:0x004f), top: B:51:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:25:0x003f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:26:0x0041 A[Catch: all -> 0x001b, TryCatch #0 {all -> 0x001b, blocks: (B:3:0x0003, B:5:0x0010, B:8:0x0016, B:13:0x0021, B:15:0x002b, B:42:0x0083, B:22:0x0038, B:36:0x0061, B:38:0x0072, B:40:0x0076, B:41:0x0080, B:26:0x0041, B:28:0x0047, B:30:0x004f), top: B:51:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:34:0x005e  */
    /* JADX WARN: Code duplicated, block: B:36:0x0061 A[Catch: all -> 0x001b, TryCatch #0 {all -> 0x001b, blocks: (B:3:0x0003, B:5:0x0010, B:8:0x0016, B:13:0x0021, B:15:0x002b, B:42:0x0083, B:22:0x0038, B:36:0x0061, B:38:0x0072, B:40:0x0076, B:41:0x0080, B:26:0x0041, B:28:0x0047, B:30:0x004f), top: B:51:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:38:0x0072 A[Catch: all -> 0x001b, TryCatch #0 {all -> 0x001b, blocks: (B:3:0x0003, B:5:0x0010, B:8:0x0016, B:13:0x0021, B:15:0x002b, B:42:0x0083, B:22:0x0038, B:36:0x0061, B:38:0x0072, B:40:0x0076, B:41:0x0080, B:26:0x0041, B:28:0x0047, B:30:0x004f), top: B:51:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:41:0x0080 A[Catch: all -> 0x001b, TryCatch #0 {all -> 0x001b, blocks: (B:3:0x0003, B:5:0x0010, B:8:0x0016, B:13:0x0021, B:15:0x002b, B:42:0x0083, B:22:0x0038, B:36:0x0061, B:38:0x0072, B:40:0x0076, B:41:0x0080, B:26:0x0041, B:28:0x0047, B:30:0x004f), top: B:51:0x0003 }] */
    public final void D() {
        Object ffVar;
        v1 v1Var;
        v1 v1Var2;
        try {
            boolean z = true;
            this.k++;
            MediaController mediaController = this.i;
            String packageName = this.j;
            if (packageName == null && (packageName = this.A) == null) {
                packageName = mediaController != null ? mediaController.getPackageName() : null;
                if (packageName != null) {
                    if (mediaController != null) {
                        if (mediaController == null) {
                            z = false;
                        } else {
                            z = false;
                        }
                    } else if (mediaController == null) {
                        z = false;
                    } else {
                        z = false;
                    }
                    if (z) {
                        this.t.c();
                        this.B = null;
                        if (!pi.O(mediaController, packageName, xb.b).a) {
                            v1Var2.b(si.a("the_player_rejected_the_play_command", new Object[0]));
                        }
                    } else {
                        B(packageName);
                    }
                } else {
                    packageName = (String) this.a.a();
                    if (packageName == null || th.A(packageName)) {
                        packageName = null;
                    }
                    if (packageName != null) {
                        if (mediaController != null) {
                            if (mediaController == null) {
                                z = false;
                            } else {
                                z = false;
                            }
                        } else if (mediaController == null) {
                            z = false;
                        } else {
                            z = false;
                        }
                        if (z) {
                            this.t.c();
                            this.B = null;
                            if (!pi.O(mediaController, packageName, xb.b).a) {
                                v1Var2.b(si.a("the_player_rejected_the_play_command", new Object[0]));
                            }
                        } else {
                            B(packageName);
                        }
                    }
                }
            } else {
                if (mediaController != null || !y(mediaController)) {
                    if (mediaController == null || p()) {
                        z = false;
                    } else {
                        Object obj = MusicNotificationListener.a;
                        hc hcVarL = k8.L(packageName);
                        if (!(hcVarL != null ? k8.e(hcVarL.a(), Boolean.TRUE) : false)) {
                            z = false;
                        }
                    }
                }
                if (z) {
                    this.t.c();
                    this.B = null;
                    if (!pi.O(mediaController, packageName, xb.b).a && (v1Var2 = this.C) != null) {
                        v1Var2.b(si.a("the_player_rejected_the_play_command", new Object[0]));
                    }
                } else {
                    B(packageName);
                }
            }
            ffVar = ti.a;
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        if (gf.a(ffVar) == null || (v1Var = this.C) == null) {
            return;
        }
        v1Var.b(si.a("the_player_rejected_the_play_command", new Object[0]));
    }

    public final void E(kd kdVar) {
        Double dO;
        String strB = kdVar.b();
        String str = kdVar.a;
        str.getClass();
        Integer numValueOf = null;
        if (strB.equals("FM") || strB.equals("AM")) {
            Set set = nb.l;
            String strZ = pi.z(strB, str);
            if (strZ != null && (dO = ai.o(strZ)) != null) {
                double dDoubleValue = dO.doubleValue();
                if (!strB.equals("AM")) {
                    dDoubleValue *= 1000.0d;
                }
                numValueOf = Integer.valueOf(k8.n0(dDoubleValue));
            }
        }
        fe feVar = this.v;
        if (!feVar.c() || numValueOf == null) {
            int i = kdVar.c;
            nb nbVar = this.u;
            nbVar.getClass();
            nbVar.d.post(new u(nbVar, i, str, 2));
            return;
        }
        fi fiVar = pd.a;
        Context context = this.c;
        context.getClass();
        pd.b(context);
        feVar.b.post(new u(feVar, kdVar.b(), numValueOf.intValue(), 5));
    }

    public final void F(List list) {
        String packageName;
        Context context;
        Object ffVar;
        Object obj;
        Object ffVar2;
        MediaController mediaController = this.i;
        if (mediaController == null || (packageName = mediaController.getPackageName()) == null) {
            packageName = this.j;
        }
        Object obj2 = MusicNotificationListener.a;
        ArrayList arrayListY = k8.Y();
        ArrayList arrayList = new ArrayList();
        int size = arrayListY.size();
        int i = 0;
        int i2 = 0;
        while (true) {
            context = this.c;
            if (i2 >= size) {
                break;
            }
            Object obj3 = arrayListY.get(i2);
            i2++;
            hc hcVar = (hc) obj3;
            MediaSession.Token token = hcVar.g;
            MediaController mediaController2 = null;
            if (token != null) {
                try {
                    ffVar2 = new MediaController(context, token);
                } catch (Throwable th) {
                    ffVar2 = new ff(th);
                }
                if (ffVar2 instanceof ff) {
                    ffVar2 = null;
                }
                MediaController mediaController3 = (MediaController) ffVar2;
                if (mediaController3 != null && k8.e(mediaController3.getPackageName(), hcVar.b)) {
                    mediaController2 = mediaController3;
                }
            }
            if (mediaController2 != null) {
                arrayList.add(mediaController2);
            }
        }
        ArrayList arrayListD0 = i3.D0(list, arrayList);
        HashSet hashSet = new HashSet();
        ArrayList arrayList2 = new ArrayList();
        int size2 = arrayListD0.size();
        int i3 = 0;
        while (i3 < size2) {
            Object obj4 = arrayListD0.get(i3);
            i3++;
            if (hashSet.add(((MediaController) obj4).getSessionToken())) {
                arrayList2.add(obj4);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        int size3 = arrayList2.size();
        int i4 = 0;
        while (i4 < size3) {
            Object obj5 = arrayList2.get(i4);
            i4++;
            if (!k8.e(((MediaController) obj5).getPackageName(), context.getPackageName())) {
                arrayList3.add(obj5);
            }
        }
        ArrayList arrayList4 = new ArrayList(k3.n0(arrayList3));
        int size4 = arrayList3.size();
        int i5 = 0;
        while (i5 < size4) {
            Object obj6 = arrayList3.get(i5);
            i5++;
            arrayList4.add(((MediaController) obj6).getSessionToken());
        }
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = this.h;
        int size5 = arrayList6.size();
        int i6 = 0;
        while (i6 < size5) {
            Object obj7 = arrayList6.get(i6);
            i6++;
            if (!arrayList4.contains(((MediaController) ((yc) obj7).a).getSessionToken())) {
                arrayList5.add(obj7);
            }
        }
        int size6 = arrayList5.size();
        int i7 = 0;
        while (i7 < size6) {
            Object obj8 = arrayList5.get(i7);
            i7++;
            yc ycVar = (yc) obj8;
            ((MediaController) ycVar.a).unregisterCallback((MediaController.Callback) ycVar.b);
        }
        arrayList6.removeAll(i3.L0(arrayList5));
        ArrayList arrayList7 = new ArrayList();
        int size7 = arrayList3.size();
        int i8 = 0;
        while (i8 < size7) {
            Object obj9 = arrayList3.get(i8);
            i8++;
            MediaController mediaController4 = (MediaController) obj9;
            if (!arrayList6.isEmpty()) {
                int size8 = arrayList6.size();
                int i9 = 0;
                do {
                    if (i9 < size8) {
                        obj = arrayList6.get(i9);
                        i9++;
                    }
                } while (!k8.e(((MediaController) ((yc) obj).a).getSessionToken(), mediaController4.getSessionToken()));
            }
            arrayList7.add(obj9);
        }
        int size9 = arrayList7.size();
        while (i < size9) {
            Object obj10 = arrayList7.get(i);
            i++;
            MediaController mediaController5 = (MediaController) obj10;
            fc fcVar = new fc(this);
            try {
                mediaController5.registerCallback(fcVar, this.f);
                ffVar = ti.a;
            } catch (Throwable th2) {
                ffVar = new ff(th2);
            }
            if (!(ffVar instanceof ff)) {
                arrayList6.add(new yc(mediaController5, fcVar));
            }
        }
        int i10 = 1;
        if (arrayList6.size() > 1) {
            n3.o0(arrayList6, new g2(i10, arrayList4));
        }
        a(packageName);
    }

    /* JADX WARN: Code duplicated, block: B:121:0x0276  */
    /* JADX WARN: Code duplicated, block: B:124:0x0285  */
    /* JADX WARN: Code duplicated, block: B:130:0x02a0  */
    /* JADX WARN: Code duplicated, block: B:131:0x02a5  */
    /* JADX WARN: Code duplicated, block: B:162:0x0313  */
    /* JADX WARN: Code duplicated, block: B:266:0x029b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:43:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:85:0x01ee A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:86:0x01f0 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:87:0x01f2  */
    /* JADX WARN: Code duplicated, block: B:88:0x01f5  */
    public final void a(String str) {
        String str2;
        boolean z;
        Object next;
        Iterator it;
        i8 i8Var;
        Object next2;
        Integer num;
        int iIntValue;
        Object next3;
        Bitmap bitmap;
        String packageName;
        String str3;
        String str4 = str;
        ArrayList arrayList = this.h;
        ArrayList arrayList2 = new ArrayList(k3.n0(arrayList));
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            arrayList2.add((MediaController) ((yc) obj).a);
        }
        ArrayList arrayList3 = new ArrayList();
        int size2 = arrayList2.size();
        int i2 = 0;
        while (i2 < size2) {
            Object obj2 = arrayList2.get(i2);
            i2++;
            if (!j((MediaController) obj2)) {
                arrayList3.add(obj2);
            }
        }
        Object obj3 = MusicNotificationListener.a;
        ArrayList arrayListY = k8.Y();
        ArrayList arrayList4 = new ArrayList();
        int size3 = arrayListY.size();
        int i3 = 0;
        while (i3 < size3) {
            Object obj4 = arrayListY.get(i3);
            i3++;
            hc hcVar = (hc) obj4;
            if (!arrayList3.isEmpty()) {
                int size4 = arrayList3.size();
                int i4 = 0;
                while (true) {
                    if (i4 < size4) {
                        Object obj5 = arrayList3.get(i4);
                        i4++;
                        if (k8.e(((MediaController) obj5).getPackageName(), hcVar.b)) {
                        }
                    }
                }
            }
            ArrayList arrayList5 = this.h;
            if (arrayList5 == null || !arrayList5.isEmpty()) {
                int size5 = arrayList5.size();
                int i5 = 0;
                while (true) {
                    if (i5 < size5) {
                        Object obj6 = arrayList5.get(i5);
                        i5++;
                        yc ycVar = (yc) obj6;
                        if (!k8.e(((MediaController) ycVar.a).getPackageName(), hcVar.b) || !j((MediaController) ycVar.a)) {
                        }
                    }
                }
            }
            if (!k(hcVar.b, th.J(hcVar.d + " " + hcVar.e).toString())) {
                arrayList4.add(obj4);
            }
        }
        ec ecVar = this.z;
        Uri uri = null;
        if (ecVar == null) {
            str2 = null;
        } else {
            if (ecVar.a) {
                ecVar = null;
            }
            if (ecVar != null) {
                str2 = ecVar.b;
            } else {
                str2 = null;
            }
        }
        ArrayList arrayList6 = new ArrayList(k3.n0(arrayList3));
        int size6 = arrayList3.size();
        int i6 = 0;
        while (i6 < size6) {
            Object obj7 = arrayList3.get(i6);
            i6++;
            arrayList6.add(((MediaController) obj7).getPackageName());
        }
        ArrayList arrayList7 = new ArrayList(k3.n0(arrayList4));
        int size7 = arrayList4.size();
        int i7 = 0;
        while (i7 < size7) {
            Object obj8 = arrayList4.get(i7);
            i7++;
            arrayList7.add(((hc) obj8).b);
        }
        ArrayList arrayListD0 = i3.D0(arrayList6, arrayList7);
        boolean zP = p();
        ArrayList arrayList8 = new ArrayList(k3.n0(arrayList3));
        int size8 = arrayList3.size();
        int i8 = 0;
        while (i8 < size8) {
            Object obj9 = arrayList3.get(i8);
            i8++;
            arrayList8.add(Boolean.valueOf(y((MediaController) obj9)));
        }
        ArrayList arrayList9 = new ArrayList(k3.n0(arrayList4));
        int size9 = arrayList4.size();
        int i9 = 0;
        while (true) {
            boolean z2 = true;
            if (i9 >= size9) {
                break;
            }
            Object obj10 = arrayList4.get(i9);
            i9++;
            hc hcVar2 = (hc) obj10;
            if (zP || !k8.e(hcVar2.a(), Boolean.TRUE) || !e(hcVar2.b)) {
                z2 = false;
            }
            arrayList9.add(Boolean.valueOf(z2));
        }
        ArrayList arrayListD1 = i3.D0(arrayList8, arrayList9);
        fi fiVar = pd.a;
        if (!arrayListD1.isEmpty()) {
            int size10 = arrayListD1.size();
            int i10 = 0;
            while (true) {
                if (i10 >= size10) {
                    z = false;
                    break;
                }
                Object obj11 = arrayListD1.get(i10);
                i10++;
                if (((Boolean) obj11).booleanValue()) {
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
            break;
        }
        if (pd.k != z) {
            pd.k = z;
            ((Handler) pd.a.a()).post(new k(5));
        }
        List list = dd.a;
        String str5 = (String) this.a.a();
        if (str4 != null) {
            String str6 = arrayListD0.contains(str4) ? str4 : null;
            if (str6 != null) {
                str4 = str6;
            } else if (str2 == null) {
                str4 = str2;
            } else if (str4 == null) {
                str4 = this.o;
            }
        } else if (str2 == null) {
            str4 = str2;
        } else if (str4 == null) {
            str4 = this.o;
        }
        if (arrayListD0.size() != arrayListD1.size()) {
            w.a("Failed requirement.");
            return;
        }
        boolean z3 = str5 == null || th.A(str5);
        Iterator it2 = j3.g0(arrayListD0).iterator();
        do {
            i8 i8Var2 = (i8) it2;
            if (!i8Var2.c) {
                next = null;
                break;
            }
            next = i8Var2.next();
        } while (!((Boolean) arrayListD1.get(((Number) next).intValue())).booleanValue());
        Integer num2 = (Integer) next;
        if (num2 != null) {
            iIntValue = num2.intValue();
        } else if (str4 == null || th.A(str4)) {
            it = j3.g0(arrayListD0).iterator();
            do {
                i8Var = (i8) it;
                if (i8Var.c) {
                    next2 = null;
                    break;
                }
                next2 = i8Var.next();
            } while (!k8.e(arrayListD0.get(((Number) next2).intValue()), str5));
            num = (Integer) next2;
            if (num != null) {
                iIntValue = num.intValue();
            } else {
                iIntValue = -1;
                if (z3 && !arrayListD0.isEmpty()) {
                    iIntValue = 0;
                }
            }
        } else {
            Iterator it3 = j3.g0(arrayListD0).iterator();
            do {
                i8 i8Var3 = (i8) it3;
                if (!i8Var3.c) {
                    next3 = null;
                    break;
                }
                next3 = i8Var3.next();
            } while (!k8.e(arrayListD0.get(((Number) next3).intValue()), str4));
            Integer num3 = (Integer) next3;
            if (num3 != null) {
                iIntValue = num3.intValue();
            } else {
                it = j3.g0(arrayListD0).iterator();
                do {
                    i8Var = (i8) it;
                    if (i8Var.c) {
                        next2 = null;
                        break;
                    }
                    next2 = i8Var.next();
                } while (!k8.e(arrayListD0.get(((Number) next2).intValue()), str5));
                num = (Integer) next2;
                if (num != null) {
                    iIntValue = num.intValue();
                } else {
                    iIntValue = -1;
                    if (z3) {
                        iIntValue = 0;
                    }
                }
            }
        }
        MediaController mediaController = (MediaController) i3.w0(arrayList3, iIntValue);
        hc hcVar3 = (hc) i3.w0(arrayList4, iIntValue - arrayList3.size());
        String str7 = (String) i3.w0(arrayListD0, iIntValue);
        if (str7 != null) {
            this.o = str7;
            if (k8.e(i3.w0(arrayListD1, iIntValue), Boolean.TRUE) && (str3 = this.t.g) != null && (!str3.equals(str7))) {
                this.t.c();
                this.B = null;
            }
        }
        MediaController mediaController2 = this.i;
        if (k8.e(mediaController2 != null ? mediaController2.getSessionToken() : null, mediaController != null ? mediaController.getSessionToken() : null)) {
            if (!k8.e(this.j, hcVar3 != null ? hcVar3.b : null)) {
                this.q = null;
                this.p = null;
                this.r++;
            }
        } else {
            this.q = null;
            this.p = null;
            this.r++;
        }
        this.i = mediaController;
        this.j = hcVar3 != null ? hcVar3.b : null;
        MediaMetadata metadata = mediaController != null ? mediaController.getMetadata() : null;
        hc hcVarH = h();
        if (metadata == null || (bitmap = metadata.getBitmap("android.media.metadata.ART")) == null) {
            bitmap = metadata != null ? metadata.getBitmap("android.media.metadata.ALBUM_ART") : null;
            if (bitmap == null) {
                bitmap = metadata != null ? metadata.getBitmap("android.media.metadata.DISPLAY_ICON") : null;
            }
        }
        String strI = i(metadata, "android.media.metadata.ART_URI", "android.media.metadata.ALBUM_ART_URI", "android.media.metadata.DISPLAY_ICON_URI");
        Icon icon = hcVarH != null ? hcVarH.f : null;
        MediaController mediaController3 = this.i;
        if (mediaController3 == null || (packageName = mediaController3.getPackageName()) == null) {
            packageName = this.j;
        }
        Object objValueOf = "";
        if (packageName == null) {
            packageName = "";
        }
        String string = metadata != null ? metadata.getString("android.media.metadata.MEDIA_ID") : null;
        String strI2 = i(metadata, "android.media.metadata.TITLE", "android.media.metadata.DISPLAY_TITLE");
        Integer numValueOf = bitmap != null ? Integer.valueOf(bitmap.getGenerationId()) : null;
        if (bitmap == null && th.A(strI)) {
            objValueOf = hcVarH != null ? Long.valueOf(hcVarH.c) : null;
        }
        String str8 = packageName + "|" + string + "|" + strI2 + "|" + strI + "|" + numValueOf + "|" + objValueOf;
        if (!str8.equals(this.q)) {
            this.q = str8;
            this.p = bitmap;
            int i11 = this.r + 1;
            this.r = i11;
            if (bitmap == null) {
                if (th.A(strI)) {
                    strI = null;
                }
                Uri uri2 = strI != null ? Uri.parse(strI) : null;
                if (uri2 != null && i3.r0(j3.i0("content", "android.resource"), uri2.getScheme())) {
                    uri = uri2;
                }
                if (uri != null || icon != null) {
                    this.g.execute(new bc(this, uri, icon, i11));
                }
            }
        }
        d();
    }

    public final void c() {
        ArrayList arrayList = this.h;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            yc ycVar = (yc) obj;
            try {
                ((MediaController) ycVar.a).unregisterCallback((MediaController.Callback) ycVar.b);
            } catch (Throwable unused) {
            }
        }
        arrayList.clear();
        this.i = null;
        this.j = null;
    }

    /* JADX WARN: Failed to calculate best type for var: r26v7 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r26v7 ??, new type: boolean
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r26v8 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r26v8 ??, new type: boolean
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r26v9 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r26v9 ??, new type: boolean
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r27v0 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r27v0 ??, new type: boolean
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r27v1 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r27v1 ??, new type: boolean
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r27v2 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r27v2 ??, new type: boolean
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r28v2 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r28v2 ??, new type: boolean
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r28v3 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r28v3 ??, new type: boolean
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r31v0 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r31v0 ??, new type: boolean
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r31v1 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r31v1 ??, new type: boolean
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r31v2 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r31v2 ??, new type: boolean
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r32v4 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r32v4 ??, new type: boolean
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r32v5 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r32v5 ??, new type: boolean
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r32v6 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r32v6 ??, new type: boolean
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /*  JADX ERROR: Types fix failed
        jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r5v6 ??, new type: boolean
            at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
            at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
            at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryPossibleTypes(FixTypesVisitor.java:186)
            at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:245)
            at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:224)
            at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
        Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
            at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
            at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
            at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
            at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
            at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
            at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
            ... 5 more
        */
    public final void d() {
        /*
            Method dump skipped, instruction units count: 769
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.gc.d():void");
    }

    public final void f() {
        Rating ratingNewHeartRating;
        if (this.A != null) {
            return;
        }
        try {
            MediaController mediaController = this.i;
            if (mediaController != null) {
                PlaybackState playbackState = mediaController.getPlaybackState();
                if (((playbackState != null ? playbackState.getActions() : 0L) & 128) != 0) {
                    MediaMetadata metadata = mediaController.getMetadata();
                    Rating rating = metadata != null ? metadata.getRating("android.media.metadata.USER_RATING") : null;
                    int ratingType = mediaController.getRatingType();
                    boolean z = false;
                    if (ratingType == 1) {
                        if (rating != null && rating.hasHeart()) {
                            z = true;
                        }
                        ratingNewHeartRating = Rating.newHeartRating(!z);
                    } else {
                        if (ratingType != 2) {
                            return;
                        }
                        if (rating != null && rating.isThumbUp()) {
                            z = true;
                        }
                        ratingNewHeartRating = Rating.newThumbRating(!z);
                    }
                    mediaController.getTransportControls().setRating(ratingNewHeartRating);
                }
            }
        } catch (Throwable unused) {
        }
    }

    public final hc h() {
        String packageName;
        boolean zEqualsIgnoreCase;
        Object obj = MusicNotificationListener.a;
        MediaController mediaController = this.i;
        if (mediaController == null || (packageName = mediaController.getPackageName()) == null) {
            packageName = this.j;
        }
        hc hcVarL = k8.L(packageName);
        if (hcVarL != null) {
            String str = hcVarL.d;
            MediaController mediaController2 = this.i;
            String strI = i(mediaController2 != null ? mediaController2.getMetadata() : null, "android.media.metadata.TITLE", "android.media.metadata.DISPLAY_TITLE");
            boolean z = true;
            if (!th.A(strI) && !th.A(str)) {
                String string = th.J(strI).toString();
                String string2 = th.J(str).toString();
                if (string == null) {
                    zEqualsIgnoreCase = string2 == null;
                } else {
                    zEqualsIgnoreCase = string.equalsIgnoreCase(string2);
                }
                if (!zEqualsIgnoreCase) {
                    z = false;
                }
            }
            if (z) {
                return hcVarL;
            }
        }
        return null;
    }

    public final boolean j(MediaController mediaController) {
        String string;
        String packageName = mediaController.getPackageName();
        packageName.getClass();
        List listI0 = j3.i0("android.media.metadata.TITLE", "android.media.metadata.ARTIST", "android.media.metadata.DISPLAY_TITLE", "android.media.metadata.DISPLAY_SUBTITLE", "android.media.metadata.ALBUM");
        ArrayList arrayList = new ArrayList();
        Iterator it = listI0.iterator();
        while (true) {
            String str = null;
            if (!it.hasNext()) {
                return k(packageName, i3.y0(arrayList, " ", null, 62));
            }
            String str2 = (String) it.next();
            MediaMetadata metadata = mediaController.getMetadata();
            if (metadata != null && (string = metadata.getString(str2)) != null && !th.A(string)) {
                str = string;
            }
            if (str != null) {
                arrayList.add(str);
            }
        }
    }

    public final boolean k(String str, String str2) {
        if (!H.contains(str)) {
            if (!dd.a(str)) {
                de deVar = this.v.f;
                if (deVar == null || (!deVar.d() && !deVar.a())) {
                    deVar = null;
                }
                lb lbVar = this.u.f;
                boolean z = lbVar != null && lbVar.c() && lbVar.e;
                if (deVar != null || z) {
                    String strR = ci.r(str2, ',', '.');
                    if (!th.v(str, "radio", true) && (deVar == null || !th.v(strR, deVar.c(), false))) {
                        if (((deVar != null ? k8.e(deVar.d, Boolean.TRUE) : false) || z) && (str.equals("ecarx.xsf.mediacenter") || th.A(strR))) {
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final void l() {
        if (this.A != null) {
            x(false);
            return;
        }
        MediaController mediaController = this.i;
        if (mediaController == null) {
            x(false);
            return;
        }
        MediaMetadata metadata = mediaController.getMetadata();
        String strD = metadata != null ? c.d(metadata.getString("android.media.metadata.TITLE"), "|", metadata.getString("android.media.metadata.ARTIST")) : null;
        Long lG = g(mediaController);
        long jLongValue = lG != null ? lG.longValue() : 0L;
        x(false);
        int i = this.k;
        MediaSession.Token sessionToken = mediaController.getSessionToken();
        sessionToken.getClass();
        if (strD == null || jLongValue < 3000) {
            return;
        }
        this.f.postDelayed(new bc(this, i, strD, sessionToken), 700L);
    }

    public final void m(jc jcVar) {
        fi fiVar = pd.a;
        String str = jcVar.e;
        if (th.A(str)) {
            str = null;
        }
        pd.l = str;
        this.b.b(jcVar);
    }

    public final boolean n() {
        return this.v.c() || this.u.e != null;
    }

    public final boolean o(lb lbVar) {
        if (lbVar == null) {
            return false;
        }
        if (!lbVar.c() || th.A(lbVar.b)) {
            lbVar = null;
        }
        if (lbVar == null) {
            return false;
        }
        Context context = this.c;
        context.getClass();
        return k8.v(context, lbVar);
    }

    public final boolean p() {
        de deVar = this.v.f;
        if (deVar != null && ((deVar.d() || deVar.a()) && k8.e(deVar.d, Boolean.TRUE))) {
            return true;
        }
        lb lbVar = this.u.f;
        return lbVar != null && lbVar.c() && lbVar.e;
    }

    public final boolean q() {
        return this.y.getBoolean("radio_widget_enabled", false);
    }

    public final Bitmap r(lb lbVar) {
        if (lbVar != null) {
            if (!lbVar.c()) {
                lbVar = null;
            }
            if (lbVar != null) {
                md mdVar = md.a;
                Context context = this.c;
                context.getClass();
                String str = lbVar.c;
                context.getClass();
                return mdVar.d(context, j3.i0(str, mdVar.e(context, lbVar.a(), lbVar.b)));
            }
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:36:0x0074  */
    /* JADX WARN: Code duplicated, block: B:41:0x0084  */
    public final lb s() {
        String strW;
        lb lbVar;
        String str;
        lb lbVar2 = this.u.f;
        if (lbVar2 == null || !lbVar2.c()) {
            lbVar2 = null;
        }
        de deVar = this.v.f;
        if (deVar == null) {
            return lbVar2;
        }
        if (!deVar.d() && !deVar.a() && !this.v.g && lbVar2 == null) {
            return null;
        }
        String str2 = deVar.b() ? "FM" : "AM";
        String strC = deVar.c();
        if (lbVar2 == null) {
            strW = null;
        } else {
            if (lbVar2.a().equals(str2)) {
                String str3 = lbVar2.b;
                str3.getClass();
                Set set = nb.l;
                String strZ = pi.z(str2, str3);
                if (strZ == null) {
                    strZ = th.J(str3).toString();
                }
                strC.getClass();
                String strZ2 = pi.z(str2, strC);
                if (strZ2 == null) {
                    strZ2 = th.J(strC).toString();
                }
                if (k8.e(strZ, strZ2)) {
                    lbVar = lbVar2;
                } else {
                    lbVar = null;
                }
            } else {
                lbVar = null;
            }
            if (lbVar == null || (str = lbVar.c) == null) {
                strW = null;
            } else {
                strW = th.J(str).toString();
            }
        }
        if (strW == null) {
            strW = "";
        }
        int i = deVar.b() ? 3 : 4;
        if (th.A(strW)) {
            Context context = this.c;
            context.getClass();
            strW = k8.W(context, str2, strC);
            if (th.A(strW)) {
                md mdVar = md.a;
                Context context2 = this.c;
                context2.getClass();
                strW = mdVar.e(context2, str2, strC);
            }
        }
        String str4 = lbVar2 != null ? lbVar2.d : null;
        String str5 = str4 == null ? "" : str4;
        boolean z = deVar.d() || deVar.a();
        String str6 = lbVar2 != null ? lbVar2.f : "com.ecarx.radio.service";
        String str7 = lbVar2 != null ? lbVar2.g : null;
        return new lb(i, strC, strW, str5, z, str6, str7 == null ? "" : str7);
    }

    public final void t() {
        NotificationManager notificationManager = (NotificationManager) this.c.getSystemService(NotificationManager.class);
        ComponentName componentName = this.e;
        boolean zIsNotificationListenerAccessGranted = notificationManager.isNotificationListenerAccessGranted(componentName);
        this.n = zIsNotificationListenerAccessGranted;
        boolean z = this.m;
        dc dcVar = this.D;
        MediaSessionManager mediaSessionManager = this.d;
        if (!zIsNotificationListenerAccessGranted) {
            if (z) {
                try {
                    mediaSessionManager.removeOnActiveSessionsChangedListener(dcVar);
                } catch (Throwable unused) {
                }
            }
            this.m = false;
            c();
            this.r++;
            this.p = null;
            this.q = null;
            d();
            return;
        }
        if (!z) {
            try {
                mediaSessionManager.addOnActiveSessionsChangedListener(dcVar, componentName, this.f);
                this.m = true;
            } catch (SecurityException unused2) {
                this.n = false;
                c();
                this.r++;
                this.p = null;
                this.q = null;
                d();
                return;
            }
        }
        List<MediaController> activeSessions = mediaSessionManager.getActiveSessions(componentName);
        activeSessions.getClass();
        F(activeSessions);
    }

    public final void u(String str, String str2, String str3) {
        ec ecVar = new ec(false, str, str2, str3);
        if (ecVar.equals(this.z) || th.A(str)) {
            return;
        }
        this.z = ecVar;
        this.y.edit().putBoolean("music_last_radio", false).putString("music_last_package", str).putString("music_last_title", str2).putString("music_last_artist", str3).apply();
    }

    public final boolean v(boolean z) {
        nb nbVar = this.u;
        fe feVar = this.v;
        int i = 0;
        if (feVar.c()) {
            fi fiVar = pd.a;
            Context context = this.c;
            context.getClass();
            pd.b(context);
            feVar.b.post(new yd(feVar, z, i));
            return true;
        }
        if (nbVar.e == null) {
            return false;
        }
        Handler handler = nbVar.d;
        int i2 = 6;
        if (z) {
            handler.post(new t0(nbVar, 8, i2));
            return true;
        }
        handler.post(new t0(nbVar, 9, i2));
        return true;
    }

    public final void w(long j) {
        MediaController.TransportControls transportControls;
        try {
            if (this.A == null) {
                MediaController mediaController = this.i;
                if (mediaController != null) {
                    PlaybackState playbackState = mediaController.getPlaybackState();
                    if (((playbackState != null ? playbackState.getActions() : 0L) & 256) == 0) {
                        mediaController = null;
                    }
                    if (mediaController != null && (transportControls = mediaController.getTransportControls()) != null) {
                        if (j < 0) {
                            j = 0;
                        }
                        transportControls.seekTo(j);
                    }
                }
                this.f.postDelayed(new cc(this, 1), 250L);
            }
        } catch (Throwable unused) {
        }
    }

    public final void x(boolean z) {
        this.k++;
        this.t.c();
        this.B = null;
        String str = this.j;
        if (str == null && (str = this.A) == null) {
            MediaController mediaController = this.i;
            String packageName = mediaController != null ? mediaController.getPackageName() : null;
            if (packageName == null) {
                return;
            } else {
                str = packageName;
            }
        }
        pi.O(this.i, str, z ? xb.d : xb.e);
    }

    /* JADX WARN: Code duplicated, block: B:42:0x008f  */
    public final boolean y(MediaController mediaController) {
        boolean zBooleanValue;
        Boolean boolA;
        String string;
        if (!p()) {
            String packageName = mediaController.getPackageName();
            packageName.getClass();
            if (e(packageName)) {
                PlaybackState playbackState = mediaController.getPlaybackState();
                String string2 = null;
                Integer numValueOf = playbackState != null ? Integer.valueOf(playbackState.getState()) : null;
                if (!i3.r0(this.F, numValueOf)) {
                    MediaMetadata metadata = mediaController.getMetadata();
                    if (metadata == null || (string = metadata.getString("android.media.metadata.TITLE")) == null) {
                        MediaMetadata metadata2 = mediaController.getMetadata();
                        if (metadata2 != null) {
                            string2 = metadata2.getString("android.media.metadata.DISPLAY_TITLE");
                        }
                    } else {
                        string2 = string;
                    }
                    boolean z = string2 == null || th.A(string2);
                    if (numValueOf == null || numValueOf.intValue() == 0) {
                        Object obj = MusicNotificationListener.a;
                        hc hcVarL = k8.L(mediaController.getPackageName());
                        if (hcVarL != null && (boolA = hcVarL.a()) != null) {
                            zBooleanValue = boolA.booleanValue();
                        } else if (z) {
                            zBooleanValue = false;
                        } else {
                            List list = dd.a;
                            String packageName2 = mediaController.getPackageName();
                            packageName2.getClass();
                            if (dd.b(packageName2)) {
                                zBooleanValue = true;
                            } else {
                                zBooleanValue = false;
                            }
                        }
                        if (zBooleanValue) {
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    public final String z(String str) {
        Object ffVar;
        String strA;
        Context context = this.c;
        LinkedHashMap linkedHashMap = this.s;
        Object obj = linkedHashMap.get(str);
        if (obj == null) {
            if (str.equals("ecarx.xsf.mediacenter")) {
                strA = si.a("media_centre_source", new Object[0]);
            } else {
                try {
                    ffVar = context.getPackageManager().getApplicationLabel(context.getPackageManager().getApplicationInfo(str, 0)).toString();
                } catch (Throwable th) {
                    ffVar = new ff(th);
                }
                if (ffVar instanceof ff) {
                    ffVar = str;
                }
                strA = (String) ffVar;
            }
            obj = strA;
            linkedHashMap.put(str, obj);
        }
        return (String) obj;
    }
}
