package defpackage;

import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class eh {
    public static final List a = j3.i0(30, 40, 50, 60, 70);
    public static final Handler b = new Handler(Looper.getMainLooper());
    public static int c;
    public static String d;

    public static void a() {
        Object ffVar;
        d = null;
        try {
            ffVar = Class.forName("android.app.IActivityManager").getMethod("dismissSplitScreenMode", Boolean.TYPE).invoke(b(), Boolean.FALSE);
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        Throwable thA = gf.a(ffVar);
        if (thA != null) {
            Throwable cause = thA.getCause();
            if (cause != null) {
                thA = cause;
            }
            Log.w("ReferencePanelSplit", "dismiss: " + thA);
        }
    }

    public static Object b() {
        return ActivityManager.class.getMethod("getService", null).invoke(null, null);
    }

    public static ArrayList c(SharedPreferences sharedPreferences) {
        String string = sharedPreferences.getString("split_list", "");
        List listD = th.D(string != null ? string : "", new char[]{','});
        ArrayList arrayList = new ArrayList();
        for (Object obj : listD) {
            if (!th.A((String) obj)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static boolean d(int i, int i2) {
        Object ffVar;
        Bundle bundle = ActivityOptions.makeBasic().toBundle();
        bundle.putInt("android.activity.windowingMode", i2);
        if (i2 == 3) {
            bundle.putInt("android:activity.splitScreenCreateMode", 0);
        }
        try {
            Object objInvoke = Class.forName("android.app.IActivityManager").getMethod("startActivityFromRecents", Integer.TYPE, Bundle.class).invoke(b(), Integer.valueOf(i), bundle);
            objInvoke.getClass();
            ffVar = (Integer) objInvoke;
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        Throwable thA = gf.a(ffVar);
        if (thA != null) {
            Throwable cause = thA.getCause();
            if (cause != null) {
                thA = cause;
            }
            Log.w("ReferencePanelSplit", "move task " + i + " to " + i2 + ": " + thA);
        }
        if (ffVar instanceof ff) {
            ffVar = null;
        }
        Integer num = (Integer) ffVar;
        return num != null && num.intValue() >= 0;
    }

    public static final void e(int i, long j, y5 y5Var) {
        b.postDelayed(new t0(i, y5Var, 9), j);
    }

    public static final void f(Context context, lg lgVar, int i, ComponentName componentName, j6 j6Var) {
        Object ffVar;
        Intent intentAddFlags;
        String packageName = componentName.getPackageName();
        packageName.getClass();
        Integer numL = l(packageName);
        if (numL != null) {
            j6Var.b(Integer.valueOf(numL.intValue()));
            return;
        }
        Object ffVar2 = ti.a;
        Bundle bundle = ActivityOptions.makeBasic().setLaunchDisplayId(0).toBundle();
        Intent intentAddFlags2 = Intent.makeMainActivity(componentName).addFlags(270532608);
        intentAddFlags2.getClass();
        try {
            context.startActivity(intentAddFlags2, bundle);
            ffVar = ffVar2;
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        boolean z = ffVar instanceof ff;
        boolean z2 = true;
        if (z) {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(componentName.getPackageName());
            if (launchIntentForPackage == null || (intentAddFlags = launchIntentForPackage.addFlags(268435456)) == null) {
                z2 = false;
            } else {
                try {
                    context.startActivity(intentAddFlags, bundle);
                } catch (Throwable th2) {
                    ffVar2 = new ff(th2);
                }
                z2 = true ^ (ffVar2 instanceof ff);
            }
        }
        if (z2) {
            b.postDelayed(new dh(i, componentName, j6Var, new le(), lgVar), 300L);
        } else {
            lgVar.b(si.a("app_unavailable_on_this_head_unit", new Object[0]));
        }
    }

    public static void g(Rect rect, int i) {
        Object ffVar;
        try {
            Class.forName("android.app.IActivityManager").getMethod("resizeDockedStack", Rect.class, Rect.class, Rect.class, Rect.class, Rect.class).invoke(b(), new Rect(rect.left, rect.top, i, rect.bottom), null, null, null, null);
            ch chVarK = k(false);
            if (chVarK == null) {
                return;
            }
            Class<?> cls = Class.forName("android.app.IActivityManager");
            Class cls2 = Integer.TYPE;
            Class cls3 = Boolean.TYPE;
            Method method = cls.getMethod("resizeStack", cls2, Rect.class, cls3, cls3, cls3, cls2);
            Object objB = b();
            Integer numValueOf = Integer.valueOf(chVarK.a);
            Rect rect2 = new Rect(i, rect.top, rect.right, rect.bottom);
            Boolean bool = Boolean.TRUE;
            Boolean bool2 = Boolean.FALSE;
            ffVar = method.invoke(objB, numValueOf, rect2, bool, bool2, bool2, -1);
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        Throwable thA = gf.a(ffVar);
        if (thA != null) {
            Throwable cause = thA.getCause();
            if (cause != null) {
                thA = cause;
            }
            Log.w("ReferencePanelSplit", "place: " + thA);
        }
    }

    public static void h(SharedPreferences sharedPreferences, String str, boolean z, ComponentName componentName) {
        str.getClass();
        componentName.getClass();
        sharedPreferences.edit().putString("split_" + str + "_" + (z ? "left" : "right"), componentName.flattenToString()).apply();
    }

    public static void i(SharedPreferences sharedPreferences, String str, int i) {
        str.getClass();
        sharedPreferences.edit().putInt("split_" + str + "_ratio", pi.s(i, 20, 80)).apply();
    }

    public static bh j(SharedPreferences sharedPreferences, String str) {
        str.getClass();
        String string = sharedPreferences.getString("split_" + str + "_left", null);
        ComponentName componentNameUnflattenFromString = string != null ? ComponentName.unflattenFromString(string) : null;
        String string2 = sharedPreferences.getString("split_" + str + "_right", null);
        return new bh(str, componentNameUnflattenFromString, string2 != null ? ComponentName.unflattenFromString(string2) : null, pi.s(sharedPreferences.getInt("split_" + str + "_ratio", 50), 20, 80));
    }

    public static ch k(boolean z) {
        Object ffVar;
        try {
            Class<?> cls = Class.forName("android.app.IActivityManager");
            Class cls2 = Integer.TYPE;
            Object objInvoke = cls.getMethod("getStackInfo", cls2, cls2).invoke(b(), Integer.valueOf(z ? 3 : 4), 1);
            if (objInvoke == null) {
                return null;
            }
            Class<?> cls3 = objInvoke.getClass();
            int i = cls3.getField("stackId").getInt(objInvoke);
            Object obj = cls3.getField("bounds").get(objInvoke);
            obj.getClass();
            ffVar = new ch(i, new Rect((Rect) obj), cls3.getField("visible").getBoolean(objInvoke));
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        return (ch) (ffVar instanceof ff ? null : ffVar);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0076  */
    public static Integer l(String str) {
        ff ffVar;
        Integer numValueOf;
        int i;
        String packageName;
        try {
            Object objInvoke = Class.forName("android.app.IActivityManager").getMethod("getAllStackInfos", null).invoke(b(), null);
            objInvoke.getClass();
            ArrayList arrayListT0 = i3.t0((List) objInvoke);
            int size = arrayListT0.size();
            int i2 = 0;
            while (i2 < size) {
                Object obj = arrayListT0.get(i2);
                i2++;
                Class<?> cls = obj.getClass();
                if (cls.getField("displayId").getInt(obj) == 0) {
                    Object obj2 = cls.getField("taskNames").get(obj);
                    obj2.getClass();
                    Object[] objArr = (Object[]) obj2;
                    Object obj3 = cls.getField("taskIds").get(obj);
                    obj3.getClass();
                    int[] iArr = (int[]) obj3;
                    boolean z = true;
                    j8 j8Var = new j8(0, objArr.length - 1, 1);
                    int i3 = j8Var.b;
                    int i4 = -j8Var.c;
                    if (i4 == 0) {
                        throw new IllegalArgumentException("Step must be non-zero.");
                    }
                    if (i4 == Integer.MIN_VALUE) {
                        throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
                    }
                    int iD = pi.D(i3, 0, i4);
                    if (i4 > 0) {
                        if (i3 > iD) {
                            z = false;
                        }
                    } else if (i3 < iD) {
                        z = false;
                    }
                    if (!z) {
                        i3 = iD;
                    }
                    while (true) {
                        if (!z) {
                            numValueOf = null;
                            break;
                        }
                        if (i3 != iD) {
                            i = i3 + i4;
                        } else {
                            if (!z) {
                                throw new NoSuchElementException();
                            }
                            z = false;
                            i = i3;
                        }
                        numValueOf = Integer.valueOf(i3);
                        Object obj4 = objArr[i3];
                        String str2 = obj4 instanceof String ? (String) obj4 : null;
                        if (str2 != null) {
                            ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(str2);
                            if (componentNameUnflattenFromString != null && (packageName = componentNameUnflattenFromString.getPackageName()) != null) {
                                str2 = packageName;
                            }
                        } else {
                            str2 = null;
                        }
                        if (k8.e(str2, str)) {
                            break;
                        }
                        i3 = i;
                    }
                    if (numValueOf != null) {
                        return Integer.valueOf(iArr[numValueOf.intValue()]);
                    }
                }
            }
            ffVar = null;
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        return (Integer) (ffVar == null ? ffVar : null);
    }
}
