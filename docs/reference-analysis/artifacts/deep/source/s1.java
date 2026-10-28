package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.media.AudioManager;
import android.media.session.MediaSessionManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.reference.panel.MusicNotificationListener;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class s1 {
    public final Context a;
    public final Handler b;
    public boolean c;
    public final LinkedHashSet d;
    public boolean e;
    public final Object f;
    public final Object g;
    public final Object h;
    public final Object i;
    public final Runnable j;

    public s1(Context context, int i) {
        switch (i) {
            case 1:
                Context applicationContext = context.getApplicationContext();
                this.a = applicationContext;
                this.b = new Handler(Looper.getMainLooper());
                this.f = (TelephonyManager) applicationContext.getSystemService(TelephonyManager.class);
                this.g = (AudioManager) applicationContext.getSystemService(AudioManager.class);
                this.d = new LinkedHashSet();
                this.h = new ArrayList();
                this.i = new k2(this);
                this.j = new r1(2, this);
                break;
            default:
                this.a = context;
                this.f = new z0(context);
                HandlerThread handlerThread = new HandlerThread("ReferencePanel-automation");
                handlerThread.start();
                this.g = handlerThread;
                this.b = new Handler(handlerThread.getLooper());
                this.e = true;
                this.h = new y6(context);
                this.i = new ni(context, new b1(this, 0));
                this.d = new LinkedHashSet();
                this.j = new r1(0, this);
                break;
        }
    }

    /* JADX WARN: Code duplicated, block: B:23:0x008a  */
    public static final void a(final s1 s1Var) {
        Long lD;
        Float fValueOf;
        Float fValueOf2;
        final int i = 0;
        if (!((z0) s1Var.f).a.getBoolean("auto_enabled", false)) {
            s1Var.c = false;
            s1Var.d.clear();
            y6 y6Var = (y6) s1Var.h;
            Looper looper = ((HandlerThread) s1Var.g).getLooper();
            looper.getClass();
            y6Var.a(false, looper);
            return;
        }
        y6 y6Var2 = (y6) s1Var.h;
        boolean zB = ((z0) s1Var.f).b("close", false);
        Looper looper2 = ((HandlerThread) s1Var.g).getLooper();
        looper2.getClass();
        y6Var2.a(zB, looper2);
        final r4 r4VarP = r4.C.p(s1Var.a);
        if (r4VarP == null || !r4VarP.m()) {
            r4VarP = null;
        }
        if (r4VarP == null) {
            return;
        }
        final int i2 = 1;
        if (((z0) s1Var.f).b("close", false)) {
            Float fV = r4VarP.v(1048832);
            Float fValueOf3 = fV != null ? Float.valueOf(fV.floatValue() * 3.6f) : null;
            Location location = ((y6) s1Var.h).c;
            if (location == null || !location.hasSpeed() || location.isFromMockProvider()) {
                fValueOf = null;
            } else {
                long jElapsedRealtimeNanos = (SystemClock.elapsedRealtimeNanos() - location.getElapsedRealtimeNanos()) / 1000000;
                if (0 > jElapsedRealtimeNanos || jElapsedRealtimeNanos >= 5001) {
                    fValueOf = null;
                } else {
                    fValueOf = Float.valueOf(location.getSpeed() * 3.6f);
                }
            }
            Iterator it = ((ArrayList) x0.S0(new Float[]{fValueOf3, fValueOf})).iterator();
            if (it.hasNext()) {
                float fFloatValue = ((Number) it.next()).floatValue();
                while (it.hasNext()) {
                    fFloatValue = Math.max(fFloatValue, ((Number) it.next()).floatValue());
                }
                fValueOf2 = Float.valueOf(fFloatValue);
            } else {
                fValueOf2 = null;
            }
            if (fValueOf2 != null) {
                float fFloatValue2 = fValueOf2.floatValue();
                float fA = ((z0) s1Var.f).a(30, "close_kmh");
                if (fFloatValue2 < fA - 5.0f) {
                    s1Var.e = true;
                } else if (s1Var.e && fFloatValue2 >= fA) {
                    s1Var.e = false;
                    r4VarP.C(0);
                    si.a("speed_0_closing_sunroof", fValueOf2);
                }
            }
        }
        Float fV2 = r4VarP.v(2097408);
        Integer numValueOf = fV2 != null ? Integer.valueOf(Math.round(fV2.floatValue())) : null;
        if (numValueOf == null || numValueOf.intValue() != 2097415) {
            if (s1Var.c) {
                s1Var.c = false;
                s1Var.d.clear();
                s1Var.e = true;
                k8.f++;
                k8.g = false;
                ((z0) s1Var.f).a.edit().putLong("auto_last_stop", System.currentTimeMillis()).apply();
                si.a("engine_stopped_automations_rearmed", new Object[0]);
                return;
            }
            return;
        }
        if (!s1Var.c) {
            s1Var.c = true;
            z0 z0Var = (z0) s1Var.f;
            boolean z = z0Var.a.getInt("auto_condition", 0) == 0 || (lD = s1Var.d()) == null || lD.longValue() >= ((long) z0Var.a.getInt("auto_idle_minutes", 120));
            List list = si.a;
            Object objD = s1Var.d();
            if (objD == null) {
                objD = si.a("unknown", new Object[0]);
            }
            si.a("engine_start_parked_0_min_condition_1", objD, z ? si.a("met", new Object[0]) : si.a("not_met", new Object[0]));
            if (z) {
                final p1 p1Var = new p1();
                p1Var.b = s1Var;
                s1Var.g("heat", new y5(s1Var) { // from class: i1
                    public final /* synthetic */ s1 b;

                    {
                        this.b = s1Var;
                    }

                    /* JADX WARN: Code duplicated, block: B:31:0x0117  */
                    /* JADX WARN: Code duplicated, block: B:33:0x0138  */
                    /* JADX WARN: Code duplicated, block: B:34:0x013a  */
                    /* JADX WARN: Code duplicated, block: B:36:0x0142  */
                    /* JADX WARN: Code duplicated, block: B:37:0x0144  */
                    /* JADX WARN: Code duplicated, block: B:41:0x0171  */
                    /* JADX WARN: Code duplicated, block: B:43:0x0185  */
                    /* JADX WARN: Code duplicated, block: B:44:0x01d4  */
                    /* JADX WARN: Code duplicated, block: B:48:0x01e4  */
                    /* JADX WARN: Code duplicated, block: B:52:0x023e  */
                    /* JADX WARN: Code duplicated, block: B:54:0x0256  */
                    /* JADX WARN: Code duplicated, block: B:55:0x0274  */
                    @Override // defpackage.y5
                    public final Object a() {
                        String str;
                        int i3;
                        int iA;
                        r4 r4Var;
                        s1 s1Var2;
                        String str2;
                        int iIntValue;
                        s1 s1Var3;
                        r4 r4Var2;
                        String str3;
                        final String str4;
                        r4 r4Var3;
                        s1 s1Var4;
                        int i4 = i;
                        ti tiVar = ti.a;
                        int i5 = 0;
                        p1 p1Var2 = p1Var;
                        int i6 = 2;
                        switch (i4) {
                            case 0:
                                s1 s1Var5 = this.b;
                                z0 z0Var2 = (z0) s1Var5.f;
                                boolean zB2 = z0Var2.b("heat_cold", false);
                                r4 r4Var4 = r4VarP;
                                if (zB2) {
                                    Float fH = s1Var5.h(r4Var4);
                                    int iA2 = z0Var2.a(5, "heat_cold_temp");
                                    if (fH != null && fH.floatValue() <= iA2) {
                                        Set set = sf.a;
                                        str = uh.e(s1Var5.a.getSharedPreferences("panel", 0).getString("vehicle", "knewstar_001")).a;
                                        if (i3.r0(sf.a, str)) {
                                            i3 = 3;
                                        } else if (i3.r0(sf.b, str)) {
                                            i3 = 1;
                                        } else {
                                            i3 = 2;
                                        }
                                        sf.c = i3;
                                        iA = z0Var2.a(15, "heat_minutes");
                                        while (r9.hasNext()) {
                                            str3 = (String) ycVar.a;
                                            str4 = (String) ycVar.b;
                                            if (z0Var2.b(str3, false)) {
                                                Set set2 = rf.d;
                                                final rf rfVar = (rf) hb.g0(pi.B(), str4);
                                                final int iS = pi.s(z0Var2.a(2, str3.concat("_level")), 1, rfVar.a());
                                                List list2 = si.a;
                                                si.a("0_level_1", str4, Integer.valueOf(iS));
                                                final int i7 = 1;
                                                final r4 r4Var5 = r4Var4;
                                                final s1 s1Var6 = s1Var5;
                                                y5 y5Var = new y5() { // from class: f1
                                                    @Override // defpackage.y5
                                                    public final Object a() {
                                                        int i8 = i7;
                                                        ti tiVar2 = ti.a;
                                                        String str5 = str4;
                                                        int i9 = iS;
                                                        rf rfVar2 = rfVar;
                                                        r4 r4Var6 = r4Var5;
                                                        s1 s1Var7 = s1Var6;
                                                        switch (i8) {
                                                            case 0:
                                                                s1Var7.c(r4Var6, rfVar2, i9, str5);
                                                                break;
                                                            default:
                                                                s1Var7.c(r4Var6, rfVar2, i9, str5);
                                                                break;
                                                        }
                                                        return tiVar2;
                                                    }
                                                };
                                                final int i8 = 1;
                                                y5 y5Var2 = new y5() { // from class: g1
                                                    @Override // defpackage.y5
                                                    public final Object a() {
                                                        int i9 = i8;
                                                        ti tiVar2 = ti.a;
                                                        String str5 = str4;
                                                        rf rfVar2 = rfVar;
                                                        r4 r4Var6 = r4Var5;
                                                        s1 s1Var7 = s1Var6;
                                                        switch (i9) {
                                                            case 0:
                                                                s1Var7.c(r4Var6, rfVar2, 0, str5 + " off");
                                                                break;
                                                            default:
                                                                s1Var7.c(r4Var6, rfVar2, 0, str5 + " off");
                                                                break;
                                                        }
                                                        return tiVar2;
                                                    }
                                                };
                                                s1Var4 = s1Var6;
                                                r4Var3 = r4Var5;
                                                p1Var2.b(iA, y5Var, y5Var2);
                                            } else {
                                                r4Var3 = r4Var4;
                                                s1Var4 = s1Var5;
                                            }
                                            s1Var5 = s1Var4;
                                            r4Var4 = r4Var3;
                                        }
                                        r4Var = r4Var4;
                                        s1Var2 = s1Var5;
                                        if (z0Var2.b("wheel", false)) {
                                            p1Var2.b(iA, new n1(s1Var2, r4Var, 2), new n1(s1Var2, r4Var, 3));
                                        }
                                        List list3 = a1.a;
                                        while (r0.hasNext()) {
                                            str2 = (String) ycVar.a;
                                            iIntValue = ((Number) ycVar.b).intValue();
                                            if (z0Var2.b(str2, false)) {
                                                s1Var3 = s1Var2;
                                                r4 r4Var6 = r4Var;
                                                r4Var2 = r4Var6;
                                                p1Var2.b(iA, new c1(s1Var3, str2, r4Var6, iIntValue, 0), new c1(s1Var3, str2, r4Var6, iIntValue, 1));
                                            } else {
                                                s1Var3 = s1Var2;
                                                r4Var2 = r4Var;
                                            }
                                            s1Var2 = s1Var3;
                                            r4Var = r4Var2;
                                        }
                                    }
                                } else {
                                    Set set3 = sf.a;
                                    str = uh.e(s1Var5.a.getSharedPreferences("panel", 0).getString("vehicle", "knewstar_001")).a;
                                    if (i3.r0(sf.a, str)) {
                                        i3 = 3;
                                    } else if (i3.r0(sf.b, str)) {
                                        i3 = 1;
                                    } else {
                                        i3 = 2;
                                    }
                                    sf.c = i3;
                                    iA = z0Var2.a(15, "heat_minutes");
                                    for (yc ycVar : j3.i0(new yc("seat_left", "seat_heat_1"), new yc("seat_right", "seat_heat_4"))) {
                                        str3 = (String) ycVar.a;
                                        str4 = (String) ycVar.b;
                                        if (z0Var2.b(str3, false)) {
                                            Set set4 = rf.d;
                                            final rf rfVar2 = (rf) hb.g0(pi.B(), str4);
                                            final int iS2 = pi.s(z0Var2.a(2, str3.concat("_level")), 1, rfVar2.a());
                                            List list4 = si.a;
                                            si.a("0_level_1", str4, Integer.valueOf(iS2));
                                            final int i9 = 1;
                                            final r4 r4Var7 = r4Var4;
                                            final s1 s1Var7 = s1Var5;
                                            y5 y5Var3 = new y5() { // from class: f1
                                                @Override // defpackage.y5
                                                public final Object a() {
                                                    int i10 = i9;
                                                    ti tiVar2 = ti.a;
                                                    String str5 = str4;
                                                    int i11 = iS2;
                                                    rf rfVar3 = rfVar2;
                                                    r4 r4Var8 = r4Var7;
                                                    s1 s1Var8 = s1Var7;
                                                    switch (i10) {
                                                        case 0:
                                                            s1Var8.c(r4Var8, rfVar3, i11, str5);
                                                            break;
                                                        default:
                                                            s1Var8.c(r4Var8, rfVar3, i11, str5);
                                                            break;
                                                    }
                                                    return tiVar2;
                                                }
                                            };
                                            final int i10 = 1;
                                            y5 y5Var4 = new y5() { // from class: g1
                                                @Override // defpackage.y5
                                                public final Object a() {
                                                    int i11 = i10;
                                                    ti tiVar2 = ti.a;
                                                    String str5 = str4;
                                                    rf rfVar3 = rfVar2;
                                                    r4 r4Var8 = r4Var7;
                                                    s1 s1Var8 = s1Var7;
                                                    switch (i11) {
                                                        case 0:
                                                            s1Var8.c(r4Var8, rfVar3, 0, str5 + " off");
                                                            break;
                                                        default:
                                                            s1Var8.c(r4Var8, rfVar3, 0, str5 + " off");
                                                            break;
                                                    }
                                                    return tiVar2;
                                                }
                                            };
                                            s1Var4 = s1Var7;
                                            r4Var3 = r4Var7;
                                            p1Var2.b(iA, y5Var3, y5Var4);
                                        } else {
                                            r4Var3 = r4Var4;
                                            s1Var4 = s1Var5;
                                        }
                                        s1Var5 = s1Var4;
                                        r4Var4 = r4Var3;
                                    }
                                    r4Var = r4Var4;
                                    s1Var2 = s1Var5;
                                    if (z0Var2.b("wheel", false)) {
                                        p1Var2.b(iA, new n1(s1Var2, r4Var, 2), new n1(s1Var2, r4Var, 3));
                                    }
                                    List list5 = a1.a;
                                    for (yc ycVar2 : j3.i0(new yc("rear_glass", 268698368), new yc("front_glass", 269747456), new yc("nozzle", 269747200), new yc("defrost", 268697856))) {
                                        str2 = (String) ycVar2.a;
                                        iIntValue = ((Number) ycVar2.b).intValue();
                                        if (z0Var2.b(str2, false)) {
                                            s1Var3 = s1Var2;
                                            r4 r4Var8 = r4Var;
                                            r4Var2 = r4Var8;
                                            p1Var2.b(iA, new c1(s1Var3, str2, r4Var8, iIntValue, 0), new c1(s1Var3, str2, r4Var8, iIntValue, 1));
                                        } else {
                                            s1Var3 = s1Var2;
                                            r4Var2 = r4Var;
                                        }
                                        s1Var2 = s1Var3;
                                        r4Var = r4Var2;
                                    }
                                }
                                break;
                            default:
                                final s1 s1Var8 = this.b;
                                z0 z0Var3 = (z0) s1Var8.f;
                                List listI0 = j3.i0(new yc("vent_left", "seat_vent_1"), new yc("vent_right", "seat_vent_4"));
                                ArrayList arrayList = new ArrayList();
                                for (Object obj : listI0) {
                                    if (z0Var3.b((String) ((yc) obj).a, false)) {
                                        arrayList.add(obj);
                                    }
                                }
                                if (!arrayList.isEmpty()) {
                                    r4 r4Var9 = r4VarP;
                                    Float fH2 = s1Var8.h(r4Var9);
                                    int iA3 = z0Var3.a(25, "vent_temp");
                                    if (fH2 != null && fH2.floatValue() >= iA3) {
                                        int iA4 = z0Var3.a(15, "vent_minutes");
                                        int size = arrayList.size();
                                        while (i5 < size) {
                                            Object obj2 = arrayList.get(i5);
                                            i5++;
                                            yc ycVar3 = (yc) obj2;
                                            String str5 = (String) ycVar3.a;
                                            final String str6 = (String) ycVar3.b;
                                            Set set5 = rf.d;
                                            final rf rfVar3 = (rf) hb.g0(pi.B(), str6);
                                            final int iS3 = pi.s(z0Var3.a(i6, str5 + "_level"), 1, rfVar3.a());
                                            List list6 = si.a;
                                            si.a("0_level_1", str6, Integer.valueOf(iS3));
                                            int i11 = size;
                                            final int i12 = 0;
                                            ArrayList arrayList2 = arrayList;
                                            final r4 r4Var10 = r4Var9;
                                            y5 y5Var5 = new y5() { // from class: f1
                                                @Override // defpackage.y5
                                                public final Object a() {
                                                    int i13 = i12;
                                                    ti tiVar2 = ti.a;
                                                    String str7 = str6;
                                                    int i14 = iS3;
                                                    rf rfVar4 = rfVar3;
                                                    r4 r4Var11 = r4Var10;
                                                    s1 s1Var9 = s1Var8;
                                                    switch (i13) {
                                                        case 0:
                                                            s1Var9.c(r4Var11, rfVar4, i14, str7);
                                                            break;
                                                        default:
                                                            s1Var9.c(r4Var11, rfVar4, i14, str7);
                                                            break;
                                                    }
                                                    return tiVar2;
                                                }
                                            };
                                            final int i13 = 0;
                                            p1Var2.b(iA4, y5Var5, new y5() { // from class: g1
                                                @Override // defpackage.y5
                                                public final Object a() {
                                                    int i14 = i13;
                                                    ti tiVar2 = ti.a;
                                                    String str7 = str6;
                                                    rf rfVar4 = rfVar3;
                                                    r4 r4Var11 = r4Var10;
                                                    s1 s1Var9 = s1Var8;
                                                    switch (i14) {
                                                        case 0:
                                                            s1Var9.c(r4Var11, rfVar4, 0, str7 + " off");
                                                            break;
                                                        default:
                                                            s1Var9.c(r4Var11, rfVar4, 0, str7 + " off");
                                                            break;
                                                    }
                                                    return tiVar2;
                                                }
                                            });
                                            arrayList = arrayList2;
                                            r4Var9 = r4Var10;
                                            size = i11;
                                            i6 = 2;
                                        }
                                    }
                                }
                                break;
                        }
                        return tiVar;
                    }
                });
                s1Var.g("vent", new y5(s1Var) { // from class: i1
                    public final /* synthetic */ s1 b;

                    {
                        this.b = s1Var;
                    }

                    /* JADX WARN: Code duplicated, block: B:31:0x0117  */
                    /* JADX WARN: Code duplicated, block: B:33:0x0138  */
                    /* JADX WARN: Code duplicated, block: B:34:0x013a  */
                    /* JADX WARN: Code duplicated, block: B:36:0x0142  */
                    /* JADX WARN: Code duplicated, block: B:37:0x0144  */
                    /* JADX WARN: Code duplicated, block: B:41:0x0171  */
                    /* JADX WARN: Code duplicated, block: B:43:0x0185  */
                    /* JADX WARN: Code duplicated, block: B:44:0x01d4  */
                    /* JADX WARN: Code duplicated, block: B:48:0x01e4  */
                    /* JADX WARN: Code duplicated, block: B:52:0x023e  */
                    /* JADX WARN: Code duplicated, block: B:54:0x0256  */
                    /* JADX WARN: Code duplicated, block: B:55:0x0274  */
                    @Override // defpackage.y5
                    public final Object a() {
                        String str;
                        int i3;
                        int iA;
                        r4 r4Var;
                        s1 s1Var2;
                        String str2;
                        int iIntValue;
                        s1 s1Var3;
                        r4 r4Var2;
                        String str3;
                        final String str4;
                        r4 r4Var3;
                        s1 s1Var4;
                        int i4 = i2;
                        ti tiVar = ti.a;
                        int i5 = 0;
                        p1 p1Var2 = p1Var;
                        int i6 = 2;
                        switch (i4) {
                            case 0:
                                s1 s1Var5 = this.b;
                                z0 z0Var2 = (z0) s1Var5.f;
                                boolean zB2 = z0Var2.b("heat_cold", false);
                                r4 r4Var4 = r4VarP;
                                if (zB2) {
                                    Float fH = s1Var5.h(r4Var4);
                                    int iA2 = z0Var2.a(5, "heat_cold_temp");
                                    if (fH != null && fH.floatValue() <= iA2) {
                                        Set set3 = sf.a;
                                        str = uh.e(s1Var5.a.getSharedPreferences("panel", 0).getString("vehicle", "knewstar_001")).a;
                                        if (i3.r0(sf.a, str)) {
                                            i3 = 3;
                                        } else if (i3.r0(sf.b, str)) {
                                            i3 = 1;
                                        } else {
                                            i3 = 2;
                                        }
                                        sf.c = i3;
                                        iA = z0Var2.a(15, "heat_minutes");
                                        while (r9.hasNext()) {
                                            str3 = (String) ycVar.a;
                                            str4 = (String) ycVar.b;
                                            if (z0Var2.b(str3, false)) {
                                                Set set4 = rf.d;
                                                final rf rfVar2 = (rf) hb.g0(pi.B(), str4);
                                                final int iS2 = pi.s(z0Var2.a(2, str3.concat("_level")), 1, rfVar2.a());
                                                List list4 = si.a;
                                                si.a("0_level_1", str4, Integer.valueOf(iS2));
                                                final int i9 = 1;
                                                final r4 r4Var7 = r4Var4;
                                                final s1 s1Var7 = s1Var5;
                                                y5 y5Var3 = new y5() { // from class: f1
                                                    @Override // defpackage.y5
                                                    public final Object a() {
                                                        int i13 = i9;
                                                        ti tiVar2 = ti.a;
                                                        String str7 = str4;
                                                        int i14 = iS2;
                                                        rf rfVar4 = rfVar2;
                                                        r4 r4Var11 = r4Var7;
                                                        s1 s1Var9 = s1Var7;
                                                        switch (i13) {
                                                            case 0:
                                                                s1Var9.c(r4Var11, rfVar4, i14, str7);
                                                                break;
                                                            default:
                                                                s1Var9.c(r4Var11, rfVar4, i14, str7);
                                                                break;
                                                        }
                                                        return tiVar2;
                                                    }
                                                };
                                                final int i10 = 1;
                                                y5 y5Var4 = new y5() { // from class: g1
                                                    @Override // defpackage.y5
                                                    public final Object a() {
                                                        int i14 = i10;
                                                        ti tiVar2 = ti.a;
                                                        String str7 = str4;
                                                        rf rfVar4 = rfVar2;
                                                        r4 r4Var11 = r4Var7;
                                                        s1 s1Var9 = s1Var7;
                                                        switch (i14) {
                                                            case 0:
                                                                s1Var9.c(r4Var11, rfVar4, 0, str7 + " off");
                                                                break;
                                                            default:
                                                                s1Var9.c(r4Var11, rfVar4, 0, str7 + " off");
                                                                break;
                                                        }
                                                        return tiVar2;
                                                    }
                                                };
                                                s1Var4 = s1Var7;
                                                r4Var3 = r4Var7;
                                                p1Var2.b(iA, y5Var3, y5Var4);
                                            } else {
                                                r4Var3 = r4Var4;
                                                s1Var4 = s1Var5;
                                            }
                                            s1Var5 = s1Var4;
                                            r4Var4 = r4Var3;
                                        }
                                        r4Var = r4Var4;
                                        s1Var2 = s1Var5;
                                        if (z0Var2.b("wheel", false)) {
                                            p1Var2.b(iA, new n1(s1Var2, r4Var, 2), new n1(s1Var2, r4Var, 3));
                                        }
                                        List list5 = a1.a;
                                        while (r0.hasNext()) {
                                            str2 = (String) ycVar2.a;
                                            iIntValue = ((Number) ycVar2.b).intValue();
                                            if (z0Var2.b(str2, false)) {
                                                s1Var3 = s1Var2;
                                                r4 r4Var8 = r4Var;
                                                r4Var2 = r4Var8;
                                                p1Var2.b(iA, new c1(s1Var3, str2, r4Var8, iIntValue, 0), new c1(s1Var3, str2, r4Var8, iIntValue, 1));
                                            } else {
                                                s1Var3 = s1Var2;
                                                r4Var2 = r4Var;
                                            }
                                            s1Var2 = s1Var3;
                                            r4Var = r4Var2;
                                        }
                                    }
                                } else {
                                    Set set5 = sf.a;
                                    str = uh.e(s1Var5.a.getSharedPreferences("panel", 0).getString("vehicle", "knewstar_001")).a;
                                    if (i3.r0(sf.a, str)) {
                                        i3 = 3;
                                    } else if (i3.r0(sf.b, str)) {
                                        i3 = 1;
                                    } else {
                                        i3 = 2;
                                    }
                                    sf.c = i3;
                                    iA = z0Var2.a(15, "heat_minutes");
                                    for (yc ycVar : j3.i0(new yc("seat_left", "seat_heat_1"), new yc("seat_right", "seat_heat_4"))) {
                                        str3 = (String) ycVar.a;
                                        str4 = (String) ycVar.b;
                                        if (z0Var2.b(str3, false)) {
                                            Set set6 = rf.d;
                                            final rf rfVar3 = (rf) hb.g0(pi.B(), str4);
                                            final int iS3 = pi.s(z0Var2.a(2, str3.concat("_level")), 1, rfVar3.a());
                                            List list6 = si.a;
                                            si.a("0_level_1", str4, Integer.valueOf(iS3));
                                            final int i11 = 1;
                                            final r4 r4Var9 = r4Var4;
                                            final s1 s1Var8 = s1Var5;
                                            y5 y5Var5 = new y5() { // from class: f1
                                                @Override // defpackage.y5
                                                public final Object a() {
                                                    int i13 = i11;
                                                    ti tiVar2 = ti.a;
                                                    String str7 = str4;
                                                    int i14 = iS3;
                                                    rf rfVar4 = rfVar3;
                                                    r4 r4Var11 = r4Var9;
                                                    s1 s1Var9 = s1Var8;
                                                    switch (i13) {
                                                        case 0:
                                                            s1Var9.c(r4Var11, rfVar4, i14, str7);
                                                            break;
                                                        default:
                                                            s1Var9.c(r4Var11, rfVar4, i14, str7);
                                                            break;
                                                    }
                                                    return tiVar2;
                                                }
                                            };
                                            final int i12 = 1;
                                            y5 y5Var6 = new y5() { // from class: g1
                                                @Override // defpackage.y5
                                                public final Object a() {
                                                    int i14 = i12;
                                                    ti tiVar2 = ti.a;
                                                    String str7 = str4;
                                                    rf rfVar4 = rfVar3;
                                                    r4 r4Var11 = r4Var9;
                                                    s1 s1Var9 = s1Var8;
                                                    switch (i14) {
                                                        case 0:
                                                            s1Var9.c(r4Var11, rfVar4, 0, str7 + " off");
                                                            break;
                                                        default:
                                                            s1Var9.c(r4Var11, rfVar4, 0, str7 + " off");
                                                            break;
                                                    }
                                                    return tiVar2;
                                                }
                                            };
                                            s1Var4 = s1Var8;
                                            r4Var3 = r4Var9;
                                            p1Var2.b(iA, y5Var5, y5Var6);
                                        } else {
                                            r4Var3 = r4Var4;
                                            s1Var4 = s1Var5;
                                        }
                                        s1Var5 = s1Var4;
                                        r4Var4 = r4Var3;
                                    }
                                    r4Var = r4Var4;
                                    s1Var2 = s1Var5;
                                    if (z0Var2.b("wheel", false)) {
                                        p1Var2.b(iA, new n1(s1Var2, r4Var, 2), new n1(s1Var2, r4Var, 3));
                                    }
                                    List list7 = a1.a;
                                    for (yc ycVar2 : j3.i0(new yc("rear_glass", 268698368), new yc("front_glass", 269747456), new yc("nozzle", 269747200), new yc("defrost", 268697856))) {
                                        str2 = (String) ycVar2.a;
                                        iIntValue = ((Number) ycVar2.b).intValue();
                                        if (z0Var2.b(str2, false)) {
                                            s1Var3 = s1Var2;
                                            r4 r4Var10 = r4Var;
                                            r4Var2 = r4Var10;
                                            p1Var2.b(iA, new c1(s1Var3, str2, r4Var10, iIntValue, 0), new c1(s1Var3, str2, r4Var10, iIntValue, 1));
                                        } else {
                                            s1Var3 = s1Var2;
                                            r4Var2 = r4Var;
                                        }
                                        s1Var2 = s1Var3;
                                        r4Var = r4Var2;
                                    }
                                }
                                break;
                            default:
                                final s1 s1Var9 = this.b;
                                z0 z0Var3 = (z0) s1Var9.f;
                                List listI0 = j3.i0(new yc("vent_left", "seat_vent_1"), new yc("vent_right", "seat_vent_4"));
                                ArrayList arrayList = new ArrayList();
                                for (Object obj : listI0) {
                                    if (z0Var3.b((String) ((yc) obj).a, false)) {
                                        arrayList.add(obj);
                                    }
                                }
                                if (!arrayList.isEmpty()) {
                                    r4 r4Var11 = r4VarP;
                                    Float fH2 = s1Var9.h(r4Var11);
                                    int iA3 = z0Var3.a(25, "vent_temp");
                                    if (fH2 != null && fH2.floatValue() >= iA3) {
                                        int iA4 = z0Var3.a(15, "vent_minutes");
                                        int size = arrayList.size();
                                        while (i5 < size) {
                                            Object obj2 = arrayList.get(i5);
                                            i5++;
                                            yc ycVar3 = (yc) obj2;
                                            String str5 = (String) ycVar3.a;
                                            final String str6 = (String) ycVar3.b;
                                            Set set7 = rf.d;
                                            final rf rfVar4 = (rf) hb.g0(pi.B(), str6);
                                            final int iS4 = pi.s(z0Var3.a(i6, str5 + "_level"), 1, rfVar4.a());
                                            List list8 = si.a;
                                            si.a("0_level_1", str6, Integer.valueOf(iS4));
                                            int i13 = size;
                                            final int i14 = 0;
                                            ArrayList arrayList2 = arrayList;
                                            final r4 r4Var12 = r4Var11;
                                            y5 y5Var7 = new y5() { // from class: f1
                                                @Override // defpackage.y5
                                                public final Object a() {
                                                    int i15 = i14;
                                                    ti tiVar2 = ti.a;
                                                    String str7 = str6;
                                                    int i16 = iS4;
                                                    rf rfVar5 = rfVar4;
                                                    r4 r4Var13 = r4Var12;
                                                    s1 s1Var10 = s1Var9;
                                                    switch (i15) {
                                                        case 0:
                                                            s1Var10.c(r4Var13, rfVar5, i16, str7);
                                                            break;
                                                        default:
                                                            s1Var10.c(r4Var13, rfVar5, i16, str7);
                                                            break;
                                                    }
                                                    return tiVar2;
                                                }
                                            };
                                            final int i15 = 0;
                                            p1Var2.b(iA4, y5Var7, new y5() { // from class: g1
                                                @Override // defpackage.y5
                                                public final Object a() {
                                                    int i16 = i15;
                                                    ti tiVar2 = ti.a;
                                                    String str7 = str6;
                                                    rf rfVar5 = rfVar4;
                                                    r4 r4Var13 = r4Var12;
                                                    s1 s1Var10 = s1Var9;
                                                    switch (i16) {
                                                        case 0:
                                                            s1Var10.c(r4Var13, rfVar5, 0, str7 + " off");
                                                            break;
                                                        default:
                                                            s1Var10.c(r4Var13, rfVar5, 0, str7 + " off");
                                                            break;
                                                    }
                                                    return tiVar2;
                                                }
                                            });
                                            arrayList = arrayList2;
                                            r4Var11 = r4Var12;
                                            size = i13;
                                            i6 = 2;
                                        }
                                    }
                                }
                                break;
                        }
                        return tiVar;
                    }
                });
                s1Var.g("airing", new n1(s1Var, r4VarP, i));
                s1Var.g("climate", new n1(s1Var, r4VarP, i2));
                s1Var.g("apps", new b1(s1Var, 1));
            }
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - ((z0) s1Var.f).a.getLong("auto_last_driving", 0L) >= 30000 || jCurrentTimeMillis < ((z0) s1Var.f).a.getLong("auto_last_driving", 0L)) {
            ((z0) s1Var.f).a.edit().putLong("auto_last_driving", jCurrentTimeMillis).apply();
        }
    }

    public void b(String str, y5 y5Var, y5 y5Var2) {
        try {
            ((Boolean) y5Var.a()).getClass();
        } catch (Throwable unused) {
        }
        le leVar = new le();
        leVar.a = 2;
        this.b.postDelayed(new q1(leVar, str, this, y5Var2, y5Var), 4000L);
    }

    public void c(final r4 r4Var, final rf rfVar, final int i, String str) {
        final int i2 = 0;
        final int i3 = 1;
        b(str, new y5() { // from class: m1
            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
                at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
                at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // defpackage.y5
            public final Object a() {
                int i4 = i2;
                int i5 = 0;
                z = false;
                boolean z = false;
                int i6 = i;
                rf rfVar2 = rfVar;
                r4 r4Var2 = r4Var;
                switch (i4) {
                    case 0:
                        r4Var2.getClass();
                        rfVar2.getClass();
                        int i7 = rfVar2.a;
                        int i8 = rfVar2.b;
                        if (i6 > 0) {
                            int iA = rfVar2.a();
                            if (i6 > iA) {
                                i6 = iA;
                            }
                            i5 = i7 + i6;
                        }
                        return Boolean.valueOf(r4Var2.x(i7, i8, i5));
                    default:
                        Integer numR = r4Var2.r(rfVar2);
                        if (numR == null) {
                            return null;
                        }
                        Integer numC = rfVar2.c(Integer.valueOf(numR.intValue()));
                        if (numC != null && numC.intValue() == i6) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                }
            }
        }, new y5() { // from class: m1
            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
                at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
                at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // defpackage.y5
            public final Object a() {
                int i4 = i3;
                int i5 = 0;
                z = false;
                boolean z = false;
                int i6 = i;
                rf rfVar2 = rfVar;
                r4 r4Var2 = r4Var;
                switch (i4) {
                    case 0:
                        r4Var2.getClass();
                        rfVar2.getClass();
                        int i7 = rfVar2.a;
                        int i8 = rfVar2.b;
                        if (i6 > 0) {
                            int iA = rfVar2.a();
                            if (i6 > iA) {
                                i6 = iA;
                            }
                            i5 = i7 + i6;
                        }
                        return Boolean.valueOf(r4Var2.x(i7, i8, i5));
                    default:
                        Integer numR = r4Var2.r(rfVar2);
                        if (numR == null) {
                            return null;
                        }
                        Integer numC = rfVar2.c(Integer.valueOf(numR.intValue()));
                        if (numC != null && numC.intValue() == i6) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                }
            }
        });
    }

    public Long d() {
        z0 z0Var = (z0) this.f;
        long jMax = Math.max(z0Var.a.getLong("auto_last_stop", 0L), z0Var.a.getLong("auto_last_driving", 0L));
        if (jMax <= 0) {
            return null;
        }
        long jCurrentTimeMillis = System.currentTimeMillis() - jMax;
        if (jCurrentTimeMillis < 0) {
            return null;
        }
        return Long.valueOf(jCurrentTimeMillis / 60000);
    }

    public void e(int i) {
        int i2;
        try {
            long jUptimeMillis = SystemClock.uptimeMillis();
            int[] iArr = {0, 1};
            int i3 = 0;
            while (i3 < 2) {
                int i4 = iArr[i3];
                AudioManager audioManager = (AudioManager) this.g;
                if (audioManager != null) {
                    i2 = i;
                    audioManager.dispatchMediaKeyEvent(new KeyEvent(jUptimeMillis, jUptimeMillis, i4, i2, 0));
                } else {
                    i2 = i;
                }
                i3++;
                i = i2;
            }
        } catch (Throwable unused) {
        }
    }

    public void f(int i) {
        Context context = this.a;
        context.getClass();
        int i2 = 1;
        boolean z = context.getSharedPreferences("panel", 0).getBoolean("call_pause_music", true);
        LinkedHashSet linkedHashSet = this.d;
        if (!z) {
            this.c = false;
            linkedHashSet.clear();
            this.e = false;
            return;
        }
        boolean z2 = i == 1 || i == 2;
        Handler handler = this.b;
        if (z2 && !this.c) {
            this.c = true;
            handler.removeCallbacksAndMessages(null);
            handler.post((r1) this.j);
        } else {
            if (z2 || !this.c) {
                return;
            }
            this.c = false;
            linkedHashSet.size();
            handler.removeCallbacksAndMessages(null);
            handler.postDelayed(new k1(i2, this), 2500L);
        }
    }

    public void g(String str, y5 y5Var) {
        if (this.d.add(str)) {
            y5Var.a();
        }
    }

    public Float h(r4 r4Var) {
        Float fV = r4Var.v(1051392);
        if (fV != null) {
            float fFloatValue = fV.floatValue();
            if (-60.0f > fFloatValue || fFloatValue > 70.0f) {
                fV = null;
            }
            if (fV != null) {
                return Float.valueOf(fV.floatValue());
            }
        }
        SharedPreferences sharedPreferences = this.a.getSharedPreferences("panel", 0);
        long j = sharedPreferences.getLong("weather_time", 0L);
        if (j > 0 && sharedPreferences.contains("weather_temp")) {
            long jCurrentTimeMillis = System.currentTimeMillis() - j;
            if (0 <= jCurrentTimeMillis && jCurrentTimeMillis < 10800001) {
                float f = sharedPreferences.getFloat("weather_temp", Float.NaN);
                Float fValueOf = Float.valueOf(f);
                if (Math.abs(f) <= Float.MAX_VALUE) {
                    return fValueOf;
                }
            }
        }
        return null;
    }

    public List i() {
        Object ffVar;
        Context context = this.a;
        try {
            ffVar = ((MediaSessionManager) context.getSystemService(MediaSessionManager.class)).getActiveSessions(new ComponentName(context, (Class<?>) MusicNotificationListener.class));
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        if (ffVar instanceof ff) {
            ffVar = null;
        }
        return (List) ffVar;
    }

    public void j() {
        boolean z = false;
        int i = 1;
        boolean z2 = this.a.getSharedPreferences("panel", 0).getBoolean("stats_enabled", true);
        ni niVar = (ni) this.i;
        if (z2) {
            niVar.e.post(new k1(10, niVar));
        } else {
            li liVar = ni.N;
            niVar.e.post(new yd(niVar, z, i));
        }
    }
}
