package defpackage;

import android.app.Dialog;
import android.content.Context;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import android.os.SystemClock;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class k1 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ k1(s1 s1Var, d1 d1Var) {
        this.a = 0;
        this.b = d1Var;
    }

    /* JADX INFO: Infinite loop detected, blocks: 44, insns: 0 */
    @Override // java.lang.Runnable
    public final void run() {
        Object ffVar;
        Throwable targetException;
        int i = this.a;
        final int i2 = 1;
        final int i3 = 0;
        Object obj = this.b;
        switch (i) {
            case 0:
                try {
                    ((d1) obj).a();
                    ffVar = ti.a;
                } catch (Throwable th) {
                    ffVar = new ff(th);
                }
                Throwable thA = gf.a(ffVar);
                if (thA != null) {
                    Log.w("ReferencePanel.Auto", si.a("delayed_action", new Object[0]), thA);
                }
                break;
            case 1:
                s1 s1Var = (s1) obj;
                if (!s1Var.c) {
                    ArrayList arrayList = (ArrayList) s1Var.h;
                    LinkedHashSet linkedHashSet = s1Var.d;
                    if (s1Var.e) {
                        s1Var.e(126);
                        s1Var.e = false;
                    }
                    if (!linkedHashSet.isEmpty()) {
                        List listJ0 = i3.J0(linkedHashSet);
                        linkedHashSet.clear();
                        List listI = s1Var.i();
                        if (listI == null) {
                            listI = u4.a;
                        }
                        ArrayList arrayList2 = new ArrayList();
                        for (Object obj2 : listI) {
                            PlaybackState playbackState = ((MediaController) obj2).getPlaybackState();
                            if (playbackState != null && playbackState.getState() == 3) {
                                arrayList2.add(obj2);
                            }
                        }
                        ArrayList arrayList3 = new ArrayList(k3.n0(arrayList2));
                        int size = arrayList2.size();
                        int i4 = 0;
                        while (i4 < size) {
                            Object obj3 = arrayList2.get(i4);
                            i4++;
                            arrayList3.add(((MediaController) obj3).getPackageName());
                        }
                        if (!arrayList3.isEmpty()) {
                            int size2 = arrayList3.size();
                            int i5 = 0;
                            while (i5 < size2) {
                                Object obj4 = arrayList3.get(i5);
                                i5++;
                                if (!listJ0.contains((String) obj4)) {
                                    i3.y0(arrayList3, null, null, 63);
                                    break;
                                }
                            }
                        }
                        int size3 = arrayList.size();
                        int i6 = 0;
                        while (i6 < size3) {
                            Object obj5 = arrayList.get(i6);
                            i6++;
                            ((hd) obj5).c();
                        }
                        arrayList.clear();
                        ArrayList arrayList4 = new ArrayList();
                        for (Object obj6 : listJ0) {
                            if (!arrayList3.contains((String) obj6)) {
                                arrayList4.add(obj6);
                            }
                        }
                        int size4 = arrayList4.size();
                        int i7 = 0;
                        while (i7 < size4) {
                            Object obj7 = arrayList4.get(i7);
                            i7++;
                            final String str = (String) obj7;
                            Context context = s1Var.a;
                            context.getClass();
                            hd hdVar = new hd(context);
                            hdVar.a(str, new y5() { // from class: j2
                                @Override // defpackage.y5
                                public final Object a() {
                                    int i8 = i3;
                                    String str2 = str;
                                    switch (i8) {
                                        case 0:
                                            Log.w("ReferencePanel.Call", str2 + " did not start after the call");
                                            return ti.a;
                                        default:
                                            return si.a("ecarx_connection_error_0", str2);
                                    }
                                }
                            });
                            arrayList.add(hdVar);
                        }
                        break;
                    }
                }
                break;
            case 2:
                Dialog dialog = (Dialog) obj;
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                break;
            case 3:
                c4 c4Var = (c4) obj;
                c4Var.performHapticFeedback(0);
                y5 y5Var = c4Var.t;
                if (y5Var != null) {
                    y5Var.a();
                }
                break;
            case 4:
                r4 r4Var = (r4) obj;
                while (true) {
                    int i8 = 0;
                    while (r4Var.j == null) {
                        long jElapsedRealtime = SystemClock.elapsedRealtime() - r4Var.l;
                        if (i8 != 0 || r4Var.l == 0 || jElapsedRealtime >= 8000) {
                            i8++;
                            try {
                                r4Var.a();
                            } catch (Throwable th2) {
                                th = th2;
                                InvocationTargetException invocationTargetException = th instanceof InvocationTargetException ? (InvocationTargetException) th : null;
                                if (invocationTargetException != null && (targetException = invocationTargetException.getTargetException()) != null) {
                                    th = targetException;
                                }
                                final String simpleName = th.getClass().getSimpleName();
                                r4Var.n = new y5() { // from class: j2
                                    @Override // defpackage.y5
                                    public final Object a() {
                                        int i9 = i2;
                                        String str2 = simpleName;
                                        switch (i9) {
                                            case 0:
                                                Log.w("ReferencePanel.Call", str2 + " did not start after the call");
                                                return ti.a;
                                            default:
                                                return si.a("ecarx_connection_error_0", str2);
                                        }
                                    }
                                };
                                List list = si.a;
                                Log.e("ReferencePanel.Ecarx", si.a("attempt_0", Integer.valueOf(i8)), th);
                            }
                            long jElapsedRealtime2 = SystemClock.elapsedRealtime() + (i8 <= 3 ? (((long) i8) * 1000) + 3500 : 10000L);
                            while (r4Var.j == null && SystemClock.elapsedRealtime() < jElapsedRealtime2) {
                                SystemClock.sleep(250L);
                            }
                        } else {
                            SystemClock.sleep(500L);
                        }
                        break;
                    }
                    SystemClock.sleep(2000L);
                }
                break;
            case 5:
                ((o9) obj).c();
                break;
            case 6:
                qd qdVar = (qd) obj;
                if (qdVar.k) {
                    lb lbVarS = qdVar.d.s();
                    if (lbVarS == null) {
                        qdVar.a(false);
                    } else {
                        long jUptimeMillis = SystemClock.uptimeMillis();
                        if (jUptimeMillis - qdVar.j > 150000 || qdVar.f.size() >= 60) {
                            qdVar.a(true);
                        } else {
                            de deVar = qdVar.d.v.f;
                            int i9 = 6;
                            if (deVar != null && deVar.a()) {
                                qdVar.i = jUptimeMillis;
                                qdVar.e.postDelayed(new k1(i9, qdVar), 300L);
                            } else if (!th.A(lbVarS.b) && !qd.b(lbVarS).equals(qdVar.h)) {
                                String strB = qd.b(lbVarS);
                                if (qdVar.f.containsKey(strB)) {
                                    qdVar.a(true);
                                } else {
                                    qdVar.h = strB;
                                    qdVar.e.postDelayed(new r0(qdVar, strB, lbVarS, 2), 1200L);
                                }
                            } else if (jUptimeMillis - qdVar.i > 8000) {
                                qdVar.a(true);
                            } else {
                                qdVar.e.postDelayed(new k1(i9, qdVar), 300L);
                            }
                        }
                    }
                    break;
                }
                break;
            case 7:
                ie ieVar = (ie) obj;
                int i10 = ieVar.q;
                if (i10 >= 0) {
                    Set setY0 = x0.Y0(new String[]{"prev", "next"});
                    List list2 = ie.D;
                    if (setY0.contains(list2.get(i10))) {
                        ieVar.r = true;
                        ieVar.performHapticFeedback(0);
                        j6 j6Var = ieVar.n;
                        if (j6Var != null) {
                            j6Var.b("seek_" + list2.get(i10));
                        }
                    }
                }
                break;
            case 8:
                Set set = bf.a;
                bf.c = (xe) obj;
                bf.d = false;
                break;
            case 9:
                ((b2) obj).a();
                break;
            default:
                ni niVar = (ni) obj;
                if (!niVar.f) {
                    niVar.f = true;
                    try {
                        niVar.m();
                    } catch (Throwable th3) {
                        Log.w("ReferencePanel.Stats", "trip checkpoint", th3);
                        niVar.d.edit().remove("stats_open_trip").apply();
                    }
                    niVar.H = SystemClock.elapsedRealtime();
                    niVar.e.removeCallbacks(niVar.L);
                    niVar.e.post(niVar.L);
                    break;
                }
                break;
        }
    }

    public /* synthetic */ k1(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }
}
