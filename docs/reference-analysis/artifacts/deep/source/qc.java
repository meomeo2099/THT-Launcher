package defpackage;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.media.AudioManager;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class qc {
    public static final SimpleDateFormat a = new SimpleDateFormat("HH:mm:ss.SSS", Locale.US);
    public static final fi b = new fi(new j(10));
    public static final List c = j3.i0("android.permission.INJECT_EVENTS", "android.permission.INTERNAL_SYSTEM_WINDOW", "android.permission.MANAGE_ACTIVITY_STACKS", "android.permission.FORCE_STOP_PACKAGES", "android.permission.KILL_BACKGROUND_PROCESSES");

    public static String a(Activity activity, ComponentName componentName) {
        Object ffVar;
        Object ffVar2;
        Serializable ffVar3;
        activity.getClass();
        String str = a.format(new Date());
        str.getClass();
        ArrayList arrayListJ0 = j3.j0("Audio " + str + " (snapshot; not proof of microphone capture)");
        AudioManager audioManager = (AudioManager) activity.getSystemService(AudioManager.class);
        try {
            ffVar = Boolean.valueOf(arrayListJ0.add("Microphone muted=" + audioManager.isMicrophoneMute() + "; audioMode=" + audioManager.getMode() + "; musicActive=" + audioManager.isMusicActive() + "; bluetoothSco=" + audioManager.isBluetoothScoOn()));
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        Throwable thA = gf.a(ffVar);
        if (thA != null) {
            arrayListJ0.add("Audio state unavailable: " + thA.getMessage());
        }
        try {
            String packageName = componentName.getPackageName();
            packageName.getClass();
            ApplicationInfo applicationInfo = activity.getPackageManager().getApplicationInfo(packageName, 0);
            applicationInfo.getClass();
            arrayListJ0.add(packageName + " RECORD_AUDIO=" + (activity.getPackageManager().checkPermission("android.permission.RECORD_AUDIO", packageName) == 0));
            try {
                ffVar3 = Boolean.valueOf(arrayListJ0.add("RECORD_AUDIO appOp=" + ((AppOpsManager) activity.getSystemService(AppOpsManager.class)).checkOpNoThrow("android:record_audio", applicationInfo.uid, packageName) + " (0=allowed, 1=ignored, 2=errored, 3=default; does not prove live access)"));
            } catch (Throwable th2) {
                ffVar3 = new ff(th2);
            }
            Throwable thA2 = gf.a(ffVar3);
            if (thA2 != null) {
                arrayListJ0.add("Microphone appOp unavailable: " + thA2.getMessage());
            }
            ffVar2 = new gf(ffVar3);
        } catch (Throwable th3) {
            ffVar2 = new ff(th3);
        }
        Throwable thA3 = gf.a(ffVar2);
        if (thA3 != null) {
            arrayListJ0.add("Navigator microphone permission unavailable: " + thA3.getMessage());
        }
        return i3.y0(arrayListJ0, "\n", null, 62);
    }

    public static String b(Activity activity, ComponentName componentName) {
        String str;
        Object ffVar;
        String str2;
        activity.getClass();
        ArrayList arrayList = new ArrayList();
        arrayList.add(Build.MANUFACTURER + " " + Build.MODEL + " / " + Build.PRODUCT + "; Android " + Build.VERSION.RELEASE + " (API " + Build.VERSION.SDK_INT + "); " + Build.DISPLAY);
        Point point = new Point();
        Point point2 = new Point();
        try {
            Display display = ((DisplayManager) activity.getSystemService(DisplayManager.class)).getDisplay(0);
            if (display != null) {
                display.getRealSize(point);
            }
            if (display != null) {
                display.getSize(point2);
            }
        } catch (Throwable unused) {
        }
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        arrayList.add("Display 0: real=" + point.x + "x" + point.y + " app=" + point2.x + "x" + point2.y + " density=" + displayMetrics.densityDpi + "dpi (" + displayMetrics.density + "x)");
        Window window = activity.getWindow();
        View decorView = window != null ? window.getDecorView() : null;
        int width = decorView != null ? decorView.getWidth() : 0;
        int height = decorView != null ? decorView.getHeight() : 0;
        arrayList.add("Launcher window: " + width + "x" + height + "; fullscreen=" + activity.getSharedPreferences("panel", 0).getBoolean("fullscreen", true));
        arrayList.add("Features: ".concat(i3.y0(j3.i0(new yc("pip", "android.software.picture_in_picture"), new yc("freeform", "android.software.freeform_window_management"), new yc("secondary_displays", "android.software.activities_on_secondary_displays")), null, new a(8, activity.getPackageManager()), 31)));
        arrayList.add("Permissions: ".concat(i3.y0(c, null, new a(9, activity), 31)));
        try {
            ActivityInfo activityInfo = activity.getPackageManager().getActivityInfo(componentName, 0);
            activityInfo.getClass();
            try {
                ffVar = activity.getPackageManager().getPackageInfo(componentName.getPackageName(), 0).versionName;
            } catch (Throwable th) {
                ffVar = new ff(th);
            }
            if (ffVar instanceof ff) {
                ffVar = null;
            }
            String str3 = (String) ffVar;
            if (str3 == null) {
                str3 = "?";
            }
            Integer num = (Integer) b.a();
            if (num == null) {
                str2 = "unknown (constant unavailable)";
            } else {
                boolean z = (activityInfo.flags & num.intValue()) != 0;
                str2 = z + " (0x" + Integer.toHexString(num.intValue()) + ")";
            }
            str = "Target " + componentName.flattenToShortString() + " v" + str3 + ": enabled=" + activityInfo.enabled + " exported=" + activityInfo.exported + " pip=" + str2 + " resizeMode=" + c(activityInfo, "resizeMode") + " maxAspect=" + c(activityInfo, "maxAspectRatio") + " orientation=" + activityInfo.screenOrientation + " configChanges=0x" + Integer.toHexString(activityInfo.configChanges) + " flags=0x" + Integer.toHexString(activityInfo.flags);
        } catch (Exception e) {
            str = "Target " + componentName.flattenToShortString() + ": " + e.getClass().getSimpleName() + ": " + e.getMessage();
        }
        arrayList.add(str);
        arrayList.add(a(activity, componentName));
        return i3.y0(arrayList, "\n", null, 62);
    }

    public static String c(ActivityInfo activityInfo, String str) {
        Object ffVar;
        try {
            Object obj = ActivityInfo.class.getField(str).get(activityInfo);
            if (obj == null || (ffVar = obj.toString()) == null) {
                ffVar = "null";
            }
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        if (gf.a(ffVar) != null) {
            ffVar = "unavailable";
        }
        return (String) ffVar;
    }

    public static String d(Object obj) {
        Class<?> cls = obj.getClass();
        return "stack=" + f(cls, obj, "stackId") + " display=" + f(cls, obj, "displayId") + " visible=" + f(cls, obj, "visible") + " windowingMode=" + e(cls, obj, "getWindowingMode") + " activityType=" + e(cls, obj, "getActivityType") + " bounds=" + f(cls, obj, "bounds") + " top=" + f(cls, obj, "topActivity") + " tasks=" + f(cls, obj, "taskIds") + " taskBounds=" + f(cls, obj, "taskBounds");
    }

    public static final String e(Class cls, Object obj, String str) {
        Object ffVar;
        try {
            Object objInvoke = cls.getMethod(str, null).invoke(obj, null);
            if (objInvoke == null || (ffVar = objInvoke.toString()) == null) {
                ffVar = "null";
            }
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        if (gf.a(ffVar) != null) {
            ffVar = "?";
        }
        return (String) ffVar;
    }

    public static final String f(Class cls, Object obj, String str) {
        Object ffVar;
        try {
            Object obj2 = cls.getField(str).get(obj);
            if (obj2 == null) {
                ffVar = "null";
            } else if (obj2 instanceof int[]) {
                ffVar = x0.V0((int[]) obj2, null, 63);
            } else if (obj2 instanceof Object[]) {
                StringBuilder sb = new StringBuilder();
                sb.append((CharSequence) "");
                int i = 0;
                for (Object obj3 : (Object[]) obj2) {
                    i++;
                    if (i > 1) {
                        sb.append((CharSequence) ", ");
                    }
                    uh.b(sb, obj3, null);
                }
                sb.append((CharSequence) "");
                ffVar = sb.toString();
            } else {
                ffVar = obj2.toString();
            }
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        if (gf.a(ffVar) != null) {
            ffVar = "?";
        }
        return (String) ffVar;
    }
}
