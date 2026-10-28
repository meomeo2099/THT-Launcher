package defpackage;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class f8 {
    public static final WeakHashMap a = new WeakHashMap();

    public static final q7 a(String str, MainActivity mainActivity, boolean z) {
        str.getClass();
        HashMap map = v7.a;
        SharedPreferences sharedPreferencesB = mainActivity.B();
        sharedPreferencesB.getClass();
        r7 r7VarD = v7.d(sharedPreferencesB, str);
        int i = 1;
        q7 q7Var = new q7(mainActivity, mainActivity.z(), v7.a(mainActivity, str), r7VarD, new v1(mainActivity, 13), new y7(mainActivity, str, i), z ? null : new w7(mainActivity, str, i), null, null, 784);
        for (yc ycVar : q7Var.getBadges()) {
            int iIntValue = ((Number) ycVar.a).intValue();
            (z ? mainActivity.E : mainActivity.B).add(new yc(((u7) r7VarD.a.get(iIntValue)).e, (j9) ycVar.b));
        }
        if (!z) {
            mainActivity.C.add(new yc(str, q7Var));
        }
        e(mainActivity, q7Var);
        return q7Var;
    }

    public static final void b(String str, MainActivity mainActivity) {
        mainActivity.getClass();
        str.getClass();
        i4 i4Var = new i4(mainActivity, mainActivity.z());
        LinearLayout linearLayoutL = mainActivity.l();
        me meVar = new me(0);
        le leVar = new le();
        leVar.a = -1;
        me meVar2 = new me(0);
        l7 l7Var = new l7(linearLayoutL, mainActivity, str, leVar, i4Var, meVar2, meVar);
        meVar2.b = l7Var;
        l7Var.a();
        String strA = si.a("screensaver_picture", new Object[0]);
        String strA2 = si.a("image_settings_hint", new Object[0]);
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.addView(linearLayoutL);
        Dialog dialogF = i4Var.f(strA, strA2, scrollView);
        meVar.b = dialogF;
        dialogF.show();
    }

    public static final void c(MainActivity mainActivity, String str, r7 r7Var) {
        HashMap map = v7.a;
        SharedPreferences sharedPreferencesB = mainActivity.B();
        sharedPreferencesB.getClass();
        str.getClass();
        r7Var.getClass();
        sharedPreferencesB.edit().putString(v7.b(str), "v2|" + r7Var.b.a + "|" + i3.y0(r7Var.a, ";", new w(6), 30)).apply();
        mainActivity.e0();
    }

    public static final void d(WindowManager.LayoutParams layoutParams, q7 q7Var, WindowManager windowManager, boolean z) {
        int i = layoutParams.flags;
        int i2 = z ? i & (-17) : i | 16;
        if (i2 == layoutParams.flags || !q7Var.isAttachedToWindow()) {
            return;
        }
        layoutParams.flags = i2;
        try {
            windowManager.updateViewLayout(q7Var, layoutParams);
        } catch (Throwable unused) {
        }
    }

    public static final boolean e(MainActivity mainActivity, q7 q7Var) {
        q7Var.getClass();
        bj bjVar = mainActivity.Z;
        List list = z2.a;
        Map map = bjVar.i.f;
        q0 q0Var = new q0(map, mainActivity, 3);
        q0 q0Var2 = new q0(bjVar, map);
        HashMap map2 = q7Var.l;
        map2.clear();
        HashMap map3 = q7Var.m;
        map3.clear();
        StringBuilder sb = new StringBuilder();
        Iterator it = q7Var.c.a.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                q7Var.invalidate();
                String str = q7Var.p;
                String string = sb.toString();
                q7Var.p = string;
                return (str == null || str.equals(string)) ? false : true;
            }
            Object next = it.next();
            int i2 = i + 1;
            if (i < 0) {
                j3.m0();
                throw null;
            }
            u7 u7Var = (u7) next;
            String str2 = u7Var.e;
            t7 t7Var = u7Var.f;
            if (!th.A(str2) && t7Var != t7.d) {
                Integer num = (Integer) q0Var.b(str2);
                String str3 = (String) q0Var2.b(str2);
                int iOrdinal = t7Var.ordinal();
                if (iOrdinal != 2) {
                    if (iOrdinal == 3 && str3 != null) {
                        map3.put(Integer.valueOf(i), str3);
                    }
                } else if (num != null) {
                    map2.put(Integer.valueOf(i), Integer.valueOf(num.intValue()));
                }
                sb.append(i);
                sb.append(':');
                sb.append(num != null);
                sb.append(':');
                if (t7Var != t7.f) {
                    str3 = "";
                }
                sb.append(str3);
                sb.append(';');
            }
            i = i2;
        }
    }

    public static final void f(MainActivity mainActivity) {
        ArrayList arrayList = mainActivity.D;
        if (arrayList.isEmpty()) {
            return;
        }
        WindowManager windowManager = (WindowManager) mainActivity.getSystemService(WindowManager.class);
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            q7 q7Var = (q7) obj;
            Runnable runnable = (Runnable) a.remove(q7Var);
            if (runnable != null) {
                q7Var.removeCallbacks(runnable);
            }
            if (windowManager != null) {
                try {
                    windowManager.removeView(q7Var);
                } catch (Throwable unused) {
                }
            }
        }
        arrayList.clear();
        mainActivity.E.clear();
    }
}
