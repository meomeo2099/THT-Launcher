package defpackage;

import android.R;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class q {
    public static volatile boolean a;
    public static volatile boolean b;
    public static Handler d;
    public static Context e;
    public static volatile r f;
    public static t g;
    public static Boolean h;
    public static boolean i;
    public static final Handler c = new Handler(Looper.getMainLooper());
    public static final j j = new j(0);
    public static final k k = new k(0);
    public static final p l = new p(0);
    public static final k m = new k(1);

    public static boolean a(Context context) {
        Object ffVar;
        context.getClass();
        try {
            ffVar = Boolean.valueOf(Settings.canDrawOverlays(context));
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        Object obj = Boolean.FALSE;
        if (ffVar instanceof ff) {
            ffVar = obj;
        }
        return ((Boolean) ffVar).booleanValue();
    }

    public static void b() {
        r4 r4VarP;
        Object ffVar;
        Context context = e;
        if (context == null || (r4VarP = r4.C.p(context)) == null) {
            return;
        }
        if (!r4VarP.m()) {
            r4VarP = null;
        }
        if (r4VarP == null) {
            return;
        }
        try {
            ffVar = r4VarP.q();
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        if (ffVar instanceof ff) {
            ffVar = null;
        }
        v2 v2Var = (v2) ffVar;
        if (v2Var == null) {
            return;
        }
        List list = z2.a;
        Map map = v2Var.f;
        Float f2 = (Float) v2Var.a.get(1);
        Float fValueOf = f2 != null ? Float.valueOf(z2.h(f2.floatValue())) : null;
        Integer numC = z2.c((Integer) map.get(268566784));
        Integer numA = z2.a((Integer) map.get(268894464));
        Integer num = (Integer) map.get(268501248);
        boolean z = (num == null || num.intValue() == 0) ? false : true;
        Float f3 = (Float) v2Var.a.get(4);
        Float fValueOf2 = f3 != null ? Float.valueOf(z2.h(f3.floatValue())) : null;
        r rVar = new r(fValueOf, numC, numA, z, fValueOf2);
        if (fValueOf == null && fValueOf2 == null && numC == null && numA == null) {
            return;
        }
        r rVar2 = f;
        f = rVar;
        if (rVar2 == null || rVar2.equals(rVar)) {
            return;
        }
        if ((!a || b) && c(context) && a(context)) {
            c.post(new l(context, v2Var, rVar, rVar2, 0));
        }
    }

    public static boolean c(Context context) {
        context.getClass();
        return context.getSharedPreferences("panel", 0).getBoolean("anton_popup", true);
    }

    public static void d() {
        c.removeCallbacks(m);
        t tVar = g;
        if (tVar == null) {
            return;
        }
        g = null;
        h = null;
        i = false;
        try {
            WindowManager windowManager = (WindowManager) tVar.getContext().getSystemService(WindowManager.class);
            if (windowManager != null) {
                windowManager.removeView(tVar);
            }
        } catch (Throwable unused) {
        }
    }

    public static void e(Context context, v2 v2Var, int i2, int i3) {
        final WindowManager windowManager;
        Object ffVar;
        boolean z;
        int iHashCode;
        boolean z2 = (i3 & 4) == 0;
        int i4 = (i3 & 8) != 0 ? 1 : i2;
        if (a(context) && (windowManager = (WindowManager) context.getSystemService(WindowManager.class)) != null) {
            String string = context.getSharedPreferences("panel", 0).getString("theme_mode", "night");
            boolean z3 = string == null || ((iHashCode = string.hashCode()) == -887328209 ? !(string.equals("system") && (context.getResources().getConfiguration().uiMode & 48) != 32) : !(iHashCode == 99228 && string.equals("day")));
            if (g != null && (!k8.e(h, Boolean.valueOf(z3)) || (z2 != (z = i) && !z))) {
                d();
            }
            t tVar = g;
            if (tVar == null) {
                tVar = new t(context, z3, true, null, null, null);
                float fM = l8.m(context);
                final int i5 = (int) (340.0f * fM);
                final int i6 = (int) (196.0f * fM);
                final Point point = new Point();
                windowManager.getDefaultDisplay().getRealSize(point);
                final SharedPreferences sharedPreferences = context.getSharedPreferences("panel", 0);
                final WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(i5, i6, 2038, (z2 ? 0 : 16) | 264, -3);
                layoutParams.gravity = 8388659;
                int i7 = sharedPreferences.getInt("anton_x", (int) (24.0f * fM));
                int i8 = point.x - i5;
                if (i8 < 0) {
                    i8 = 0;
                }
                layoutParams.x = pi.s(i7, 0, i8);
                int i9 = sharedPreferences.getInt("anton_y", (point.y - i6) / 2);
                int i10 = point.y - i6;
                if (i10 < 0) {
                    i10 = 0;
                }
                layoutParams.y = pi.s(i9, 0, i10);
                layoutParams.windowAnimations = R.style.Animation.Toast;
                layoutParams.setTitle("ReferencePanel Anton");
                if (z2) {
                    final ke keVar = new ke();
                    final ke keVar2 = new ke();
                    final le leVar = new le();
                    final le leVar2 = new le();
                    tVar.setOnTouchListener(new View.OnTouchListener() { // from class: m
                        @Override // android.view.View.OnTouchListener
                        public final boolean onTouch(View view, MotionEvent motionEvent) {
                            WindowManager windowManager2 = windowManager;
                            k kVar = q.m;
                            Handler handler = q.c;
                            int actionMasked = motionEvent.getActionMasked();
                            ke keVar3 = keVar;
                            ke keVar4 = keVar2;
                            le leVar3 = leVar;
                            WindowManager.LayoutParams layoutParams2 = layoutParams;
                            le leVar4 = leVar2;
                            if (actionMasked == 0) {
                                keVar3.a = motionEvent.getRawX();
                                keVar4.a = motionEvent.getRawY();
                                leVar3.a = layoutParams2.x;
                                leVar4.a = layoutParams2.y;
                                handler.removeCallbacks(kVar);
                                return true;
                            }
                            if (actionMasked != 1) {
                                if (actionMasked == 2) {
                                    int rawX = (int) ((motionEvent.getRawX() - keVar3.a) + leVar3.a);
                                    Point point2 = point;
                                    int i11 = point2.x - i5;
                                    if (i11 < 0) {
                                        i11 = 0;
                                    }
                                    layoutParams2.x = pi.s(rawX, 0, i11);
                                    int rawY = (int) ((motionEvent.getRawY() - keVar4.a) + leVar4.a);
                                    int i12 = point2.y - i6;
                                    if (i12 < 0) {
                                        i12 = 0;
                                    }
                                    layoutParams2.y = pi.s(rawY, 0, i12);
                                    try {
                                        windowManager2.updateViewLayout(view, layoutParams2);
                                    } catch (Throwable unused) {
                                    }
                                    return true;
                                }
                                if (actionMasked != 3) {
                                    return true;
                                }
                            }
                            sharedPreferences.edit().putInt("anton_x", layoutParams2.x).putInt("anton_y", layoutParams2.y).apply();
                            handler.removeCallbacks(kVar);
                            handler.postDelayed(kVar, 8000L);
                            return true;
                        }
                    });
                }
                try {
                    windowManager.addView(tVar, layoutParams);
                    ffVar = ti.a;
                } catch (Throwable th) {
                    ffVar = new ff(th);
                }
                Throwable thA = gf.a(ffVar);
                if (thA != null) {
                    Log.w("ReferencePanel.Anton", "overlay refused", thA);
                }
                if (ffVar instanceof ff) {
                    return;
                }
                g = tVar;
                h = Boolean.valueOf(z3);
                i = z2;
            }
            tVar.d(v2Var, i4);
            Handler handler = c;
            k kVar = m;
            handler.removeCallbacks(kVar);
            handler.postDelayed(kVar, i ? 8000L : 4000L);
        }
    }

    public static void f() {
        r4 r4VarP;
        CopyOnWriteArraySet copyOnWriteArraySet;
        Handler handler = d;
        if (handler == null) {
            return;
        }
        Context context = e;
        if (context != null && (r4VarP = r4.C.p(context)) != null && (copyOnWriteArraySet = r4VarP.w) != null) {
            copyOnWriteArraySet.remove(j);
        }
        handler.removeCallbacksAndMessages(null);
        handler.getLooper().quitSafely();
        d = null;
        f = null;
        c.post(new k(2));
    }
}
