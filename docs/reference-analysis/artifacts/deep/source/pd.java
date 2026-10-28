package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.MediaSessionManager;
import android.media.session.PlaybackState;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.reference.panel.MusicNotificationListener;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class pd {
    public static Context b;
    public static fe c;
    public static MediaSession d;
    public static AudioTrack e;
    public static int f;
    public static String g;
    public static Boolean h;
    public static volatile boolean k;
    public static volatile String l;
    public static volatile long m;
    public static final fi a = new fi(new j(12));
    public static final p1 i = new p1();
    public static final j j = new j(13);
    public static final od n = new od();

    /* JADX WARN: Code duplicated, block: B:107:0x017e A[Catch: all -> 0x0178, TryCatch #1 {all -> 0x0178, blocks: (B:103:0x0174, B:106:0x017a, B:112:0x018f, B:107:0x017e, B:108:0x0182, B:110:0x0188, B:111:0x018c), top: B:148:0x016a }] */
    /* JADX WARN: Code duplicated, block: B:109:0x0186 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:110:0x0188 A[Catch: all -> 0x0178, TryCatch #1 {all -> 0x0178, blocks: (B:103:0x0174, B:106:0x017a, B:112:0x018f, B:107:0x017e, B:108:0x0182, B:110:0x0188, B:111:0x018c), top: B:148:0x016a }] */
    /* JADX WARN: Code duplicated, block: B:111:0x018c A[Catch: all -> 0x0178, TryCatch #1 {all -> 0x0178, blocks: (B:103:0x0174, B:106:0x017a, B:112:0x018f, B:107:0x017e, B:108:0x0182, B:110:0x0188, B:111:0x018c), top: B:148:0x016a }] */
    public static final void a(int i2) {
        fe feVar;
        Object ffVar;
        ArrayList arrayList;
        String strN;
        boolean z;
        int size;
        int i3;
        Object obj;
        MediaController mediaController;
        Object ffVar2;
        PlaybackState playbackState;
        Object obj2;
        Object obj3;
        de deVar;
        boolean z2 = true;
        Object obj4 = null;
        if (d() || !((feVar = c) == null || (deVar = feVar.f) == null || k8.e(deVar.d, Boolean.FALSE) || k || m == 0 || SystemClock.elapsedRealtime() - m >= 1800000)) {
            int i4 = 5;
            if (i2 != 79) {
                if (i2 == 126) {
                    fe feVar2 = c;
                    if (feVar2 != null) {
                        feVar2.b.post(new o(feVar2, obj4, i4));
                        return;
                    }
                    return;
                }
                if (i2 != 127) {
                    switch (i2) {
                        case 87:
                            f(1);
                            break;
                        case 88:
                            f(-1);
                            break;
                    }
                    return;
                }
                m = SystemClock.elapsedRealtime();
                fe feVar3 = c;
                if (feVar3 != null) {
                    feVar3.h();
                    return;
                }
                return;
            }
            if (!d()) {
                fe feVar4 = c;
                if (feVar4 != null) {
                    feVar4.b.post(new o(feVar4, obj4, i4));
                    return;
                }
                return;
            }
            m = SystemClock.elapsedRealtime();
            fe feVar5 = c;
            if (feVar5 != null) {
                feVar5.h();
                return;
            }
            return;
        }
        Context context = b;
        if (context == null) {
            return;
        }
        try {
            ffVar = ((MediaSessionManager) context.getSystemService(MediaSessionManager.class)).getActiveSessions(new ComponentName(context, (Class<?>) MusicNotificationListener.class));
            while (true) {
                if (i3 >= size) {
                    obj = null;
                    break;
                }
                obj = arrayList.get(i3);
                i3++;
                MediaController mediaController2 = (MediaController) obj;
                if (strN != null) {
                    if (k8.e(mediaController2.getPackageName(), strN)) {
                        break;
                    }
                    if (z) {
                        List list = dd.a;
                        String packageName = mediaController2.getPackageName();
                        packageName.getClass();
                        if (dd.a(packageName)) {
                            break;
                        }
                    } else {
                        continue;
                    }
                }
            }
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        Object obj5 = u4.a;
        if (ffVar instanceof ff) {
            ffVar = obj5;
        }
        arrayList = new ArrayList();
        for (Object obj6 : (Iterable) ffVar) {
            if (!k8.e(((MediaController) obj6).getPackageName(), context.getPackageName())) {
                arrayList.add(obj6);
            }
        }
        strN = l0.n();
        if (strN == null || th.A(strN) || th.v(strN, "com.ecarx.radio.service", false)) {
            strN = null;
        }
        z = strN != null && th.v(strN, "bluetooth", true);
        size = arrayList.size();
        i3 = 0;
        MediaController mediaController3 = (MediaController) obj;
        if (mediaController3 == null) {
            int size2 = arrayList.size();
            int i5 = 0;
            while (true) {
                if (i5 >= size2) {
                    obj3 = null;
                    break;
                }
                obj3 = arrayList.get(i5);
                i5++;
                MediaController mediaController4 = (MediaController) obj3;
                if (z && k8.e(mediaController4.getPackageName(), "ecarx.xsf.mediacenter")) {
                    break;
                }
            }
            mediaController3 = (MediaController) obj3;
        }
        if (mediaController3 == null) {
            int size3 = arrayList.size();
            int i6 = 0;
            while (true) {
                if (i6 >= size3) {
                    obj2 = null;
                    break;
                }
                obj2 = arrayList.get(i6);
                i6++;
                PlaybackState playbackState2 = ((MediaController) obj2).getPlaybackState();
                if (playbackState2 != null && playbackState2.getState() == 3) {
                    break;
                }
            }
            mediaController = (MediaController) obj2;
            if (mediaController == null) {
                int size4 = arrayList.size();
                int i7 = 0;
                while (i7 < size4) {
                    Object obj7 = arrayList.get(i7);
                    i7++;
                    if (k8.e(((MediaController) obj7).getPackageName(), l)) {
                        obj4 = obj7;
                        break;
                    }
                }
                mediaController = (MediaController) obj4;
                if (mediaController == null) {
                    return;
                }
            }
        } else {
            mediaController = mediaController3;
        }
        MediaController.TransportControls transportControls = mediaController.getTransportControls();
        transportControls.getClass();
        if (mediaController3 == null && ((playbackState = mediaController.getPlaybackState()) == null || playbackState.getState() != 3)) {
            z2 = false;
        }
        try {
            if (i2 == 79) {
                if (z2) {
                    transportControls.pause();
                } else {
                    transportControls.play();
                }
            } else if (i2 == 126) {
                transportControls.play();
            } else if (i2 != 127) {
                switch (i2) {
                    case 85:
                        if (z2) {
                            transportControls.pause();
                        } else {
                            transportControls.play();
                        }
                        break;
                    case 86:
                        transportControls.pause();
                        break;
                    case 87:
                        transportControls.skipToNext();
                        break;
                    case 88:
                        transportControls.skipToPrevious();
                        break;
                }
            } else {
                transportControls.pause();
            }
            ffVar2 = ti.a;
        } catch (Throwable th2) {
            ffVar2 = new ff(th2);
        }
        Throwable thA = gf.a(ffVar2);
        if (thA != null) {
            Log.w("ReferencePanel.RadioKeys", "key " + i2 + " for " + mediaController.getPackageName() + " failed", thA);
        }
    }

    public static void b(Context context) {
        context.getClass();
        if (b != null) {
            return;
        }
        b = context.getApplicationContext();
        fe feVarX = fe.o.x(context);
        j jVar = j;
        jVar.getClass();
        feVarX.d.add(jVar);
        feVarX.b.post(new ae(feVarX, 1));
        c = feVarX;
    }

    /* JADX WARN: Code duplicated, block: B:53:0x00ec  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    public static void c(Context context, String str, int i2) {
        Double dO;
        Integer numValueOf;
        lb lbVarE;
        b(context);
        fe feVar = c;
        if (feVar == null) {
            return;
        }
        if (!feVar.c()) {
            if (i2 > 0) {
                ((Handler) a.a()).postDelayed(new u(context, str, i2, 4), 300L);
            }
            return;
        }
        int i3 = 5;
        int i4 = 0;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        boolean z = true;
        yc ycVar = null;
        switch (str.hashCode()) {
            case -1292297102:
                if (str.equals("RADIO_PAUSE")) {
                    m = SystemClock.elapsedRealtime();
                    feVar.h();
                    break;
                }
                break;
            case -1283528997:
                if (str.equals("RADIO_PREVIOUS")) {
                    f(-1);
                    break;
                }
                break;
            case 512446679:
                if (str.equals("RADIO_NEXT")) {
                    f(1);
                    break;
                }
                break;
            case 512512280:
                if (str.equals("RADIO_PLAY")) {
                    for (kd kdVar : k8.l0(context, "radio_favorites")) {
                        String strB = kdVar.b();
                        String str2 = kdVar.a;
                        str2.getClass();
                        if (strB.equals("FM") || strB.equals("AM")) {
                            Set set = nb.l;
                            String strZ = pi.z(strB, str2);
                            if (strZ == null || (dO = ai.o(strZ)) == null) {
                                numValueOf = null;
                            } else {
                                double dDoubleValue = dO.doubleValue();
                                if (!strB.equals("AM")) {
                                    dDoubleValue *= 1000.0d;
                                }
                                numValueOf = Integer.valueOf(k8.n0(dDoubleValue));
                            }
                        } else {
                            numValueOf = null;
                        }
                        yc ycVar2 = numValueOf != null ? new yc(kdVar.b(), Integer.valueOf(numValueOf.intValue())) : null;
                        if (ycVar2 != null) {
                            ycVar = ycVar2;
                            feVar.b.post(new o(feVar, ycVar, i3));
                            break;
                        }
                    }
                    feVar.b.post(new o(feVar, ycVar, i3));
                    break;
                }
                break;
            case 556243621:
                if (str.equals("RADIO_SEEK_DOWN")) {
                    feVar.b.post(new yd(feVar, objArr2 == true ? 1 : 0, objArr == true ? 1 : 0));
                    break;
                }
                break;
            case 1041036672:
                if (str.equals("RADIO_FAVORITE") && (lbVarE = e()) != null) {
                    lb lbVar = th.A(lbVarE.b) ? null : lbVarE;
                    if (lbVar != null) {
                        k8.I0(context, new kd(lbVar.b, th.J(lbVar.c).toString(), lbVar.a));
                    }
                }
                break;
            case 2055450607:
                if (str.equals("RADIO_PLAY_PAUSE")) {
                    if (d()) {
                        m = SystemClock.elapsedRealtime();
                        feVar.h();
                    } else {
                        feVar.b.post(new o(feVar, ycVar, i3));
                    }
                    break;
                }
                break;
            case 2110074206:
                if (str.equals("RADIO_SEEK_UP")) {
                    feVar.b.post(new yd(feVar, z, i4));
                    break;
                }
                break;
        }
    }

    public static boolean d() {
        de deVar;
        fe feVar = c;
        if (feVar == null || (deVar = feVar.f) == null) {
            return false;
        }
        if (!deVar.d() && !deVar.a()) {
            return false;
        }
        if (k8.e(deVar.d, Boolean.TRUE)) {
            return true;
        }
        return deVar.d == null && !k;
    }

    public static lb e() {
        de deVar;
        Context context;
        fe feVar = c;
        if (feVar == null || (deVar = feVar.f) == null || (context = b) == null) {
            return null;
        }
        String str = deVar.b() ? "FM" : "AM";
        String strC = deVar.c();
        String strW = k8.W(context, str, strC);
        if (th.A(strW)) {
            strW = md.a.e(context, str, strC);
        }
        return new lb(deVar.b() ? 3 : 4, strC, strW, "", deVar.d() || deVar.a(), "com.ecarx.radio.service", "");
    }

    public static void f(int i2) {
        fe feVar;
        Double dO;
        Context context = b;
        if (context == null || (feVar = c) == null) {
            return;
        }
        Integer numValueOf = null;
        if (!feVar.c()) {
            feVar = null;
        }
        if (feVar == null) {
            return;
        }
        Handler handler = feVar.b;
        kd kdVarA = i.a(k8.l0(context, "radio_favorites"), e(), i2, SystemClock.elapsedRealtime());
        if (kdVarA == null) {
            handler.post(new yd(feVar, i2 > 0, 0));
            return;
        }
        String strB = kdVarA.b();
        String str = kdVarA.a;
        str.getClass();
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
        if (numValueOf != null) {
            handler.post(new u(feVar, kdVarA.b(), numValueOf.intValue(), 5));
        }
    }

    public static void g() {
        Object ffVar;
        Object ffVar2;
        Context context = b;
        if (context == null) {
            return;
        }
        boolean zD = d();
        if (zD && d == null) {
            try {
                MediaSession mediaSession = new MediaSession(context, "ReferencePanelRadio");
                mediaSession.setCallback(n, (Handler) a.a());
                mediaSession.setFlags(3);
                mediaSession.setActive(true);
                ffVar2 = mediaSession;
            } catch (Throwable th) {
                ffVar2 = new ff(th);
            }
            Throwable thA = gf.a(ffVar2);
            if (thA != null) {
                Log.w("ReferencePanel.RadioKeys", "no media session: the steering wheel keys stay with the firmware", thA);
            }
            boolean z = ffVar2 instanceof ff;
            Object obj = ffVar2;
            if (z) {
                obj = null;
            }
            d = (MediaSession) obj;
        }
        MediaSession mediaSession2 = d;
        if (mediaSession2 == null) {
            return;
        }
        if (!k8.e(h, Boolean.valueOf(zD))) {
            h = Boolean.valueOf(zD);
            mediaSession2.setPlaybackState(new PlaybackState.Builder().setActions(567L).setState(zD ? 3 : 2, -1L, zD ? 1.0f : 0.0f).build());
            if (zD) {
                AudioTrack audioTrack = e;
                if (audioTrack != null) {
                    try {
                        audioTrack.play();
                    } catch (Throwable unused) {
                    }
                } else if (f < 3) {
                    try {
                        int nativeOutputSampleRate = AudioTrack.getNativeOutputSampleRate(3);
                        Integer numValueOf = Integer.valueOf(nativeOutputSampleRate);
                        if (nativeOutputSampleRate <= 0) {
                            numValueOf = null;
                        }
                        int iIntValue = numValueOf != null ? numValueOf.intValue() : 44100;
                        int i2 = iIntValue / 2;
                        int i3 = i2 * 2;
                        AudioTrack audioTrackBuild = new AudioTrack.Builder().setAudioAttributes(new AudioAttributes.Builder().setUsage(1).setContentType(2).build()).setAudioFormat(new AudioFormat.Builder().setEncoding(2).setSampleRate(iIntValue).setChannelMask(4).build()).setBufferSizeInBytes(i3).setTransferMode(0).build();
                        if (audioTrackBuild.write(new byte[i3], 0, i3) != i3 || audioTrackBuild.getState() != 1) {
                            throw new IllegalStateException("anchor not ready");
                        }
                        if (audioTrackBuild.setLoopPoints(0, i2, -1) != 0) {
                            throw new IllegalStateException("anchor loop");
                        }
                        audioTrackBuild.setVolume(0.0f);
                        audioTrackBuild.play();
                        ffVar = audioTrackBuild;
                        Throwable thA2 = gf.a(ffVar);
                        if (thA2 != null) {
                            int i4 = f + 1;
                            f = i4;
                            Log.w("ReferencePanel.RadioKeys", "anchor track failed (" + i4 + " of 3)", thA2);
                        }
                        e = (AudioTrack) (ffVar instanceof ff ? null : ffVar);
                    } catch (Throwable th2) {
                        ffVar = new ff(th2);
                    }
                }
            } else {
                AudioTrack audioTrack2 = e;
                if (audioTrack2 != null) {
                    try {
                        audioTrack2.stop();
                        audioTrack2.release();
                    } catch (Throwable unused2) {
                    }
                }
                e = null;
            }
        }
        lb lbVarE = e();
        if (lbVarE != null) {
            String str = lbVarE.c;
            String strB = th.A(str) ? lbVarE.b() : str;
            if (strB.equals(g)) {
                return;
            }
            g = strB;
            mediaSession2.setMetadata(new MediaMetadata.Builder().putString("android.media.metadata.TITLE", strB).putString("android.media.metadata.ARTIST", th.A(str) ? "" : lbVarE.b()).putString("android.media.metadata.ALBUM", si.a("radio", new Object[0])).putBitmap("android.media.metadata.ALBUM_ART", md.a.d(context, pi.J(str))).build());
        }
    }
}
