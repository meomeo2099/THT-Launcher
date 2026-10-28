package defpackage;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.hardware.display.DisplayManager;
import android.media.AudioManager;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;
import org.reference.panel.AutomationService;
import org.reference.panel.BootPlaybackService;
import org.reference.panel.Guard;
import org.reference.panel.MainActivity;
import org.reference.panel.MusicNotificationListener;
import org.reference.panel.NavigationAssistService;
import org.reference.panel.b;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class k8 {
    public static volatile IBinder a;
    public static volatile boolean b;
    public static final a3 c = new a3();
    public static final u2 d;
    public static u2 e;
    public static volatile int f;
    public static volatile boolean g;
    public static volatile boolean h;
    public static volatile wa i;

    static {
        Object obj = null;
        d = new u2(obj, obj, obj);
    }

    public static final void A(String str, MainActivity mainActivity) {
        new i0(mainActivity, mainActivity.z(), si.a("widget_app", new Object[0]), false, new y7(mainActivity, str, 2)).show();
    }

    public static final String A0(String str, MainActivity mainActivity) {
        mainActivity.getClass();
        str.getClass();
        List list = eh.a;
        SharedPreferences sharedPreferencesB = mainActivity.B();
        sharedPreferencesB.getClass();
        bh bhVarJ = eh.j(sharedPreferencesB, str);
        String strB0 = B0(mainActivity, bhVarJ.a);
        if (strB0 == null) {
            strB0 = "—";
        }
        String strB1 = B0(mainActivity, bhVarJ.b);
        return c.d(strB0, " | ", strB1 != null ? strB1 : "—");
    }

    public static void B(LinearLayout linearLayout, final MainActivity mainActivity, String str, final String str2) {
        Switch r0 = new Switch(mainActivity);
        r0.setText(str);
        r0.setTextSize(18.0f);
        r0.setTextColor(mainActivity.o);
        r0.setMinHeight(mainActivity.r(54));
        r0.setChecked(mainActivity.B().getBoolean(str2, true));
        r0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: hg
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                compoundButton.getClass();
                mainActivity.B().edit().putBoolean(str2, z).apply();
            }
        });
        linearLayout.addView(r0);
    }

    public static final String B0(MainActivity mainActivity, ComponentName componentName) {
        Object ffVar;
        if (componentName == null) {
            return null;
        }
        try {
            ffVar = mainActivity.getPackageManager().getActivityInfo(componentName, 0).loadLabel(mainActivity.getPackageManager()).toString();
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        return (String) (ffVar instanceof ff ? null : ffVar);
    }

    public static final void C(MainActivity mainActivity) {
        float fR = pi.r(mainActivity.B().getFloat("ui_scale", 1.0f), 0.75f, 1.5f);
        LinearLayout linearLayoutL = mainActivity.l();
        linearLayoutL.setPadding(mainActivity.r(20), mainActivity.r(12), mainActivity.r(20), mainActivity.r(12));
        List list = si.a;
        int i2 = (int) (fR * 100.0f);
        View viewU0 = mainActivity.u0(si.a("scale_0", Integer.valueOf(i2)), 22.0f, mainActivity.o);
        linearLayoutL.addView(viewU0);
        SeekBar seekBar = new SeekBar(mainActivity);
        seekBar.setMax(75);
        seekBar.setProgress(i2 - 75);
        linearLayoutL.addView(seekBar, new LinearLayout.LayoutParams(-1, mainActivity.r(56)));
        linearLayoutL.addView(mainActivity.u0(si.a("75_to_150_enlarged_widgets_may_overlap", new Object[0]), 16.0f, mainActivity.p));
        seekBar.setOnSeekBarChangeListener(new og(viewU0, 0));
        i4 i4Var = new i4(mainActivity, mainActivity.z());
        String strA = si.a("interface_scale", new Object[0]);
        String strA2 = si.a("choose_a_comfortable_size", new Object[0]);
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.addView(linearLayoutL);
        Dialog dialogF = i4Var.f(strA, strA2, scrollView);
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setGravity(16);
        Iterator it = j3.i0(75, 100, 125, 150).iterator();
        while (true) {
            int i3 = 1;
            if (!it.hasNext()) {
                linearLayoutL.addView(linearLayout);
                Button buttonA = i4Var.a(si.a("apply", new Object[0]), true, new u0(mainActivity, seekBar, dialogF, 5));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, mainActivity.r(52));
                layoutParams.topMargin = mainActivity.r(16);
                linearLayoutL.addView(buttonA, layoutParams);
                dialogF.show();
                return;
            }
            int iIntValue = ((Number) it.next()).intValue();
            Button buttonA2 = i4Var.a(iIntValue + "%", false, new i2(seekBar, iIntValue, i3));
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, mainActivity.r(48), 1.0f);
            layoutParams2.rightMargin = mainActivity.r(6);
            linearLayout.addView(buttonA2, layoutParams2);
        }
    }

    public static final gh C0(String str, MainActivity mainActivity, boolean z) {
        str.getClass();
        List list = eh.a;
        SharedPreferences sharedPreferencesB = mainActivity.B();
        sharedPreferencesB.getClass();
        bh bhVarJ = eh.j(sharedPreferencesB, str);
        gh ghVar = new gh(mainActivity, mainActivity.z(), z, D0(mainActivity, bhVarJ.a), D0(mainActivity, bhVarJ.b), bhVarJ.c);
        ghVar.setContentDescription(si.a("split", new Object[0]) + ": " + A0(str, mainActivity));
        ghVar.setOnClickListener(new na(mainActivity, str));
        if (z) {
            ghVar.setOnLongClickListener(new xa(mainActivity, str, 2));
        }
        return ghVar;
    }

    public static final void D(final MainActivity mainActivity, final Integer num) {
        ArrayList arrayList = new ArrayList();
        final int i2 = 0;
        arrayList.add(new yc(si.a("add_widget", new Object[0]), new za(mainActivity, 11)));
        arrayList.add(new yc(si.a("add_from_home_screen", new Object[0]), new za(mainActivity, 12)));
        arrayList.add(new yc(si.a("add_app_or_action", new Object[0]), new za(mainActivity, 13)));
        arrayList.add(new yc(si.a("dock_add_gap", new Object[0]), new y5() { // from class: ub
            @Override // defpackage.y5
            public final Object a() {
                int i3 = i2;
                ti tiVar = ti.a;
                Integer num2 = num;
                MainActivity mainActivity2 = mainActivity;
                switch (i3) {
                    case 0:
                        k8.c(mainActivity2, num2);
                        break;
                    case 1:
                        int iIntValue = num2.intValue();
                        ArrayList arrayList2 = new ArrayList(k8.T(mainActivity2));
                        if (arrayList2.size() > 1) {
                            arrayList2.remove(iIntValue);
                            k8.s0(arrayList2, mainActivity2);
                        }
                        break;
                    case 2:
                        k8.u(mainActivity2, num2.intValue());
                        break;
                    default:
                        int i4 = zj.a;
                        k8.L0(mainActivity2, "dock_button_transparency_".concat(mainActivity2.U(num2.intValue())), new za(mainActivity2, 10));
                        break;
                }
                return tiVar;
            }
        }));
        final int i3 = 1;
        boolean z = num != null && mainActivity.U(num.intValue()).equals("gap");
        if (num != null && z) {
            arrayList.add(new yc(c.d(si.a("delete", new Object[0]), ": ", mainActivity.W(mainActivity.U(num.intValue()))), new y5() { // from class: ub
                @Override // defpackage.y5
                public final Object a() {
                    int i4 = i3;
                    ti tiVar = ti.a;
                    Integer num2 = num;
                    MainActivity mainActivity2 = mainActivity;
                    switch (i4) {
                        case 0:
                            k8.c(mainActivity2, num2);
                            break;
                        case 1:
                            int iIntValue = num2.intValue();
                            ArrayList arrayList2 = new ArrayList(k8.T(mainActivity2));
                            if (arrayList2.size() > 1) {
                                arrayList2.remove(iIntValue);
                                k8.s0(arrayList2, mainActivity2);
                            }
                            break;
                        case 2:
                            k8.u(mainActivity2, num2.intValue());
                            break;
                        default:
                            int i5 = zj.a;
                            k8.L0(mainActivity2, "dock_button_transparency_".concat(mainActivity2.U(num2.intValue())), new za(mainActivity2, 10));
                            break;
                    }
                    return tiVar;
                }
            }));
        }
        if (num != null && !z) {
            final int i4 = 2;
            arrayList.add(new yc(si.a("edit_0_button", mainActivity.W(mainActivity.U(num.intValue()))), new y5() { // from class: ub
                @Override // defpackage.y5
                public final Object a() {
                    int i5 = i4;
                    ti tiVar = ti.a;
                    Integer num2 = num;
                    MainActivity mainActivity2 = mainActivity;
                    switch (i5) {
                        case 0:
                            k8.c(mainActivity2, num2);
                            break;
                        case 1:
                            int iIntValue = num2.intValue();
                            ArrayList arrayList2 = new ArrayList(k8.T(mainActivity2));
                            if (arrayList2.size() > 1) {
                                arrayList2.remove(iIntValue);
                                k8.s0(arrayList2, mainActivity2);
                            }
                            break;
                        case 2:
                            k8.u(mainActivity2, num2.intValue());
                            break;
                        default:
                            int i6 = zj.a;
                            k8.L0(mainActivity2, "dock_button_transparency_".concat(mainActivity2.U(num2.intValue())), new za(mainActivity2, 10));
                            break;
                    }
                    return tiVar;
                }
            }));
        }
        final int i5 = 3;
        if (num != null && !z) {
            arrayList.add(new yc(si.a("button_transparency", new Object[0]), new y5() { // from class: ub
                @Override // defpackage.y5
                public final Object a() {
                    int i6 = i5;
                    ti tiVar = ti.a;
                    Integer num2 = num;
                    MainActivity mainActivity2 = mainActivity;
                    switch (i6) {
                        case 0:
                            k8.c(mainActivity2, num2);
                            break;
                        case 1:
                            int iIntValue = num2.intValue();
                            ArrayList arrayList2 = new ArrayList(k8.T(mainActivity2));
                            if (arrayList2.size() > 1) {
                                arrayList2.remove(iIntValue);
                                k8.s0(arrayList2, mainActivity2);
                            }
                            break;
                        case 2:
                            k8.u(mainActivity2, num2.intValue());
                            break;
                        default:
                            int i7 = zj.a;
                            k8.L0(mainActivity2, "dock_button_transparency_".concat(mainActivity2.U(num2.intValue())), new za(mainActivity2, 10));
                            break;
                    }
                    return tiVar;
                }
            }));
        }
        arrayList.add(new yc(si.a("dock_transparency", new Object[0]), new za(mainActivity, 7)));
        arrayList.add(new yc(si.a("configure_bottom_bar", new Object[0]), new za(mainActivity, 8)));
        AlertDialog.Builder title = new AlertDialog.Builder(mainActivity).setTitle(si.a("bottom_bar", new Object[0]));
        ArrayList arrayList2 = new ArrayList(k3.n0(arrayList));
        int size = arrayList.size();
        int i6 = 0;
        while (i6 < size) {
            Object obj = arrayList.get(i6);
            i6++;
            arrayList2.add((String) ((yc) obj).a);
        }
        c.e("cancel", new Object[0], title.setItems((CharSequence[]) arrayList2.toArray(new String[0]), new f2(i5, arrayList)), null, mainActivity);
    }

    public static final Drawable D0(MainActivity mainActivity, ComponentName componentName) {
        Object ffVar;
        if (componentName == null) {
            return null;
        }
        try {
            ffVar = mainActivity.getPackageManager().getActivityIcon(componentName);
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        return (Drawable) (ffVar instanceof ff ? null : ffVar);
    }

    public static final TextView E(MainActivity mainActivity, String str, float f2, int i2, boolean z) {
        TextView textView = new TextView(mainActivity);
        textView.setText(str);
        textView.setTextSize(f2);
        textView.setTextColor(i2);
        textView.setLineSpacing(0.0f, 1.2f);
        if (z) {
            textView.setTypeface(null, 1);
        }
        return textView;
    }

    public static void E0(Context context) {
        Object ffVar;
        SharedPreferences sharedPreferences = context.getSharedPreferences("panel", 0);
        boolean z = sharedPreferences.getBoolean("radio_autoplay", false);
        if ((sharedPreferences.getBoolean("music_autoplay", false) || z) && !h) {
            try {
                ffVar = Integer.valueOf(Settings.Global.getInt(context.getContentResolver(), "boot_count"));
            } catch (Throwable th) {
                ffVar = new ff(th);
            }
            if (ffVar instanceof ff) {
                ffVar = -1;
            }
            int iIntValue = ((Number) ffVar).intValue();
            if (iIntValue >= 0) {
                if (sharedPreferences.getInt("autoplay_boot_count", -1) == iIntValue) {
                    return;
                }
            } else if (SystemClock.elapsedRealtime() > 300000) {
                return;
            }
            h = true;
            sharedPreferences.edit().putInt("autoplay_boot_count", iIntValue).apply();
            if (z) {
                fi fiVar = pd.a;
                pd.c(context, "RADIO_PLAY", 100);
            } else {
                try {
                    context.startForegroundService(new Intent(context, (Class<?>) BootPlaybackService.class));
                } catch (Throwable unused) {
                }
            }
        }
    }

    public static final void F(Canvas canvas, String str, float f2, float f3, Paint paint) {
        float f4;
        str.getClass();
        paint.getClass();
        int i2 = 0;
        while (true) {
            if (i2 >= str.length()) {
                canvas.drawText(str, f2, f3, paint);
                return;
            }
            char cCharAt = str.charAt(i2);
            if ((1536 <= cCharAt && cCharAt < 1792) || ((1872 <= cCharAt && cCharAt < 1920) || (2208 <= cCharAt && cCharAt < 2304))) {
                break;
            } else {
                i2++;
            }
        }
        TextPaint textPaint = new TextPaint(paint);
        textPaint.setTextAlign(Paint.Align.LEFT);
        List list = si.a;
        textPaint.setTextLocale(si.b.b);
        int iCeil = (int) Math.ceil(Layout.getDesiredWidth(str, textPaint));
        if (iCeil < 1) {
            iCeil = 1;
        }
        StaticLayout staticLayoutBuild = StaticLayout.Builder.obtain(str, 0, str.length(), textPaint, iCeil).setAlignment(Layout.Alignment.ALIGN_NORMAL).setTextDirection(TextDirectionHeuristics.FIRSTSTRONG_LTR).setIncludePad(false).setMaxLines(1).build();
        staticLayoutBuild.getClass();
        Paint.Align textAlign = paint.getTextAlign();
        int i3 = textAlign == null ? -1 : ka.a[textAlign.ordinal()];
        if (i3 != 1) {
            if (i3 == 2) {
                f4 = iCeil / 2.0f;
            }
            int iSave = canvas.save();
            canvas.translate(f2, f3 - staticLayoutBuild.getLineBaseline(0));
            staticLayoutBuild.draw(canvas);
            canvas.restoreToCount(iSave);
        }
        f4 = iCeil;
        f2 -= f4;
        int iSave2 = canvas.save();
        canvas.translate(f2, f3 - staticLayoutBuild.getLineBaseline(0));
        staticLayoutBuild.draw(canvas);
        canvas.restoreToCount(iSave2);
    }

    public static void F0(Context context) {
        context.getClass();
        Intent intent = new Intent(context, (Class<?>) AutomationService.class);
        try {
            if (X(context)) {
                context.startForegroundService(intent);
            } else {
                context.stopService(intent);
            }
        } catch (Throwable unused) {
        }
    }

    public static final void G(MainActivity mainActivity) {
        c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity).setTitle(si.a("app", new Object[0])).setItems(new String[]{si.a("choose_app", new Object[0]), si.a("swap", new Object[0]), si.a("clear", new Object[0]), si.a("edit_home_screen", new Object[0])}, new sb(mainActivity, new String[]{si.a("navigation_206", new Object[0]), si.a("music", new Object[0]), si.a("favorite_1", new Object[0]), si.a("favorite_2", new Object[0])}, j3.i0(0, 1, 2, 3), 0)), null, mainActivity);
    }

    public static final void G0(Object obj) throws Throwable {
        if (obj instanceof ff) {
            throw ((ff) obj).a;
        }
    }

    public static final void H(MainActivity mainActivity) {
        mainActivity.getClass();
        mainActivity.B().edit().putBoolean("onboarded", true).apply();
        mainActivity.c = false;
        mainActivity.e0();
    }

    public static void H0(String str) {
        vc vcVar = new vc("lateinit property " + str + " has not been initialized");
        q0(vcVar, k8.class.getName());
        throw vcVar;
    }

    /* JADX WARN: Code duplicated, block: B:21:0x005e  */
    public static uc I() {
        int iIntValue;
        try {
            Object objInvoke = ActivityManager.class.getMethod("getService", null).invoke(null, null);
            Object objInvoke2 = objInvoke != null ? Class.forName("android.app.IActivityManager").getMethod("getFocusedStackInfo", null).invoke(objInvoke, null) : null;
            if (objInvoke2 == null) {
                return null;
            }
            Class<?> cls = objInvoke2.getClass();
            int i2 = cls.getField("displayId").getInt(objInvoke2);
            Object obj = cls.getField("taskIds").get(objInvoke2);
            int[] iArr = obj instanceof int[] ? (int[]) obj : null;
            if (iArr == null) {
                iIntValue = -1;
            } else {
                Integer numValueOf = iArr.length == 0 ? null : Integer.valueOf(iArr[iArr.length - 1]);
                if (numValueOf != null) {
                    iIntValue = numValueOf.intValue();
                } else {
                    iIntValue = -1;
                }
            }
            Object obj2 = cls.getField("topActivity").get(objInvoke2);
            ComponentName componentName = obj2 instanceof ComponentName ? (ComponentName) obj2 : null;
            return new uc(i2, iIntValue, componentName != null ? componentName.getPackageName() : null);
        } catch (Exception e2) {
            Log.w("NavigationPreview", "Cannot read focused task", e2);
            return null;
        }
    }

    public static void I0(Context context, kd kdVar) {
        Object next;
        List listL0 = l0(context, "radio_favorites");
        Iterator it = listL0.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            kd kdVar2 = (kd) next;
            if (kdVar2.b().equals(kdVar.b()) && kdVar2.c(kdVar.a)) {
                break;
            }
        }
        kd kdVar3 = (kd) next;
        r0(context, kdVar3 != null ? i3.C0(listL0, kdVar3) : x0(i3.E0(listL0, kdVar)));
    }

    public static final ArrayList J(MainActivity mainActivity) {
        List listH0 = i3.H0(w5.a(mainActivity), 4);
        ArrayList arrayList = new ArrayList(k3.n0(listH0));
        Iterator it = listH0.iterator();
        while (it.hasNext()) {
            View viewM = mainActivity.M((String) it.next());
            if (viewM instanceof cj) {
                cj cjVar = (cj) viewM;
                cjVar.setHalo(false);
                cjVar.setNightMode(mainActivity.z());
            }
            arrayList.add(viewM);
        }
        return arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:45:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:48:0x00ca  */
    public static final void J0(String str, MainActivity mainActivity) {
        Object ffVar;
        Object ffVar2;
        int iS;
        ch chVarK;
        mainActivity.getClass();
        str.getClass();
        if (mainActivity.A() && e(eh.d, str) && (chVarK = eh.k(true)) != null && chVarK.c) {
            eh.a();
            mainActivity.g0(-1, "home");
            return;
        }
        List list = eh.a;
        SharedPreferences sharedPreferencesB = mainActivity.B();
        sharedPreferencesB.getClass();
        bh bhVarJ = eh.j(sharedPreferencesB, str);
        ComponentName componentName = bhVarJ.a;
        ComponentName componentName2 = bhVarJ.b;
        if (componentName != null) {
            try {
                ffVar = mainActivity.getPackageManager().getActivityInfo(componentName, 0);
            } catch (Throwable th) {
                ffVar = new ff(th);
            }
            if (!(ffVar instanceof ff) && componentName2 != null) {
                try {
                    ffVar2 = mainActivity.getPackageManager().getActivityInfo(componentName2, 0);
                } catch (Throwable th2) {
                    ffVar2 = new ff(th2);
                }
                if (!(ffVar2 instanceof ff)) {
                    componentName.getClass();
                    String packageName = componentName.getPackageName();
                    componentName2.getClass();
                    if (e(packageName, componentName2.getPackageName())) {
                        mainActivity.v0(si.a("split_same_app", new Object[0]));
                        y0(str, mainActivity);
                        return;
                    }
                    if (g) {
                        f++;
                        g = false;
                    }
                    int i2 = bhVarJ.c;
                    Point point = new Point();
                    mainActivity.getWindowManager().getDefaultDisplay().getRealSize(point);
                    Rect rectT = mainActivity.T();
                    if (rectT != null) {
                        mainActivity.s0 = rectT;
                    }
                    Rect rect = mainActivity.s0;
                    if (rect == null) {
                        iS = point.y;
                    } else {
                        if (mainActivity.v() > 0) {
                            boolean z = q.a;
                            if (!q.a(mainActivity)) {
                                rect = null;
                            }
                        } else {
                            rect = null;
                        }
                        if (rect != null) {
                            iS = mainActivity.S(rect);
                        } else {
                            iS = point.y;
                        }
                    }
                    WindowInsets rootWindowInsets = mainActivity.getWindow().getDecorView().getRootWindowInsets();
                    int stableInsetLeft = rootWindowInsets != null ? rootWindowInsets.getStableInsetLeft() : 0;
                    Rect rect2 = new Rect(0, 0, point.x, iS);
                    int i3 = (((point.x - stableInsetLeft) * i2) / 100) + stableInsetLeft;
                    ArrayList arrayList = mainActivity.w;
                    ArrayList arrayList2 = new ArrayList();
                    int size = arrayList.size();
                    int i4 = 0;
                    while (i4 < size) {
                        Object obj = arrayList.get(i4);
                        i4++;
                        if (i3.r0(x0.Y0(new String[]{componentName.getPackageName(), componentName2.getPackageName()}), ((b) obj).l())) {
                            arrayList2.add(obj);
                        }
                    }
                    int size2 = arrayList2.size();
                    int i5 = 0;
                    while (i5 < size2) {
                        Object obj2 = arrayList2.get(i5);
                        i5++;
                        b bVar = (b) obj2;
                        bVar.u = true;
                        bVar.k();
                        bVar.h();
                    }
                    ArrayList arrayList3 = mainActivity.w;
                    int size3 = arrayList3.size();
                    int i6 = 0;
                    while (i6 < size3) {
                        Object obj3 = arrayList3.get(i6);
                        i6++;
                        b bVar2 = (b) obj3;
                        bVar2.v = false;
                        bVar2.b();
                        bVar2.f();
                    }
                    mainActivity.getWindow().getDecorView().postDelayed(new qa(mainActivity, str, componentName, componentName2, rect2, i3), arrayList2.isEmpty() ? 0L : 300L);
                    return;
                }
            }
        }
        y0(str, mainActivity);
    }

    public static final void K(MainActivity mainActivity, boolean z) {
        if (mainActivity.h) {
            return;
        }
        if (!z || mainActivity.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0) {
            new h2(mainActivity, true, new lg(mainActivity, 3), new mg(mainActivity, 12), si.a("font_load_from", new Object[0]), x0.Y0(new String[]{"ttf", "otf", "zip"}), 16).b();
        } else {
            mainActivity.requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 55);
        }
    }

    public static final void K0(MainActivity mainActivity, Uri uri, boolean z) {
        uri.getClass();
        mainActivity.h = true;
        mainActivity.H().q();
        mainActivity.e0();
        mainActivity.b.execute(new ag(mainActivity, z, uri, 1));
    }

    public static hc L(String str) {
        Object obj;
        ArrayList arrayListY = Y();
        int size = arrayListY.size();
        int i2 = 0;
        while (i2 < size) {
            obj = arrayListY.get(i2);
            i2++;
            if (((hc) obj).b.equals(str)) {
                return (hc) obj;
            }
        }
        obj = null;
        return (hc) obj;
    }

    public static final void L0(MainActivity mainActivity, String str, y5 y5Var) {
        boolean z = mainActivity.z();
        String strA = si.a("backing_transparency", new Object[0]);
        int i2 = zj.a;
        SharedPreferences sharedPreferencesB = mainActivity.B();
        sharedPreferencesB.getClass();
        ((Dialog) new u2(mainActivity, z, strA, zj.c(sharedPreferencesB, str), new w(17), new o0(mainActivity, str, y5Var, 4)).c).show();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final Class M(s2 s2Var) {
        s2Var.getClass();
        if (jb.class.isPrimitive()) {
            String name = jb.class.getName();
            switch (name.hashCode()) {
                case -1325958191:
                    if (name.equals("double")) {
                        return Double.class;
                    }
                    break;
                case 104431:
                    if (name.equals("int")) {
                        return Integer.class;
                    }
                    break;
                case 3039496:
                    if (name.equals("byte")) {
                        return Byte.class;
                    }
                    break;
                case 3052374:
                    if (name.equals("char")) {
                        return Character.class;
                    }
                    break;
                case 3327612:
                    if (name.equals("long")) {
                        return Long.class;
                    }
                    break;
                case 3625364:
                    if (name.equals("void")) {
                        return Void.class;
                    }
                    break;
                case 64711720:
                    if (name.equals("boolean")) {
                        return Boolean.class;
                    }
                    break;
                case 97526364:
                    if (name.equals("float")) {
                        return Float.class;
                    }
                    break;
                case 109413500:
                    if (name.equals("short")) {
                        return Short.class;
                    }
                    break;
            }
        }
        return jb.class;
    }

    public static double M0(String str, String str2) {
        Double dO;
        Set set = nb.l;
        String strZ = pi.z(str, str2);
        if (strZ == null || (dO = ai.o(strZ)) == null) {
            return 0.0d;
        }
        return dO.doubleValue();
    }

    public static View N(Context context, boolean z, String str, Integer num) {
        float fM = l8.m(context);
        if (str.equals("airflow") || str.equals("steering") || str.startsWith("seat_") || aj.i.contains(str)) {
            cj cjVar = new cj(context, str);
            cjVar.setHalo(false);
            cjVar.setNightMode(z);
            cjVar.setBlowMode(num);
            return cjVar;
        }
        if (!str.startsWith("text:")) {
            d9 d9Var = new d9(context, str);
            d9Var.setColor(l8.j(z));
            return d9Var;
        }
        TextView textView = new TextView(context);
        textView.setText(th.C(str, "text:"));
        textView.setGravity(17);
        textView.setTextColor(l8.j(z));
        textView.setIncludeFontPadding(false);
        textView.setTypeface(Typeface.create("sans-serif-medium", 1));
        textView.setMaxLines(1);
        int i2 = (int) (10.0f * fM);
        if (i2 < 1) {
            i2 = 1;
        }
        int i3 = (int) (26.0f * fM);
        if (i3 < 1) {
            i3 = 1;
        }
        textView.setAutoSizeTextTypeUniformWithConfiguration(i2, i3, 1, 0);
        return textView;
    }

    public static final ArrayList N0(MainActivity mainActivity) {
        mainActivity.getClass();
        List list = mainActivity.m0;
        Set set = aj.i;
        Set setKeySet = sh.e().keySet();
        setKeySet.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : setKeySet) {
            String str = (String) obj;
            if (!x0.Y0(new String[]{"battery", "fuel"}).contains(str)) {
                Set set2 = aj.i;
                str.getClass();
                String str2 = mainActivity.E().a;
                if (aj.i.contains(str)) {
                    List list2 = z2.a;
                }
                arrayList.add(obj);
            }
        }
        ArrayList arrayListD0 = i3.D0(j3.i0("apps", "settings", "home", "favorites", "music", "voice", "collapse", "folder", "saver", "camera360", "theme", "gps", "stats", "automation", "bt", "wifi", "wifi_mark", "bt_mark", "fuel_compact", "radio"), mainActivity.G());
        list.getClass();
        List<String> listJ0 = i3.J0(i3.K0(list));
        ArrayList arrayList2 = new ArrayList(k3.n0(listJ0));
        for (String str3 : listJ0) {
            arrayList2.add(new xj("w:" + str3, "widget:" + str3));
        }
        List<String> listJ1 = i3.J0(i3.K0(arrayList));
        ArrayList arrayList3 = new ArrayList(k3.n0(listJ1));
        for (String str4 : listJ1) {
            arrayList3.add(new xj("v:" + str4, str4));
        }
        ArrayList arrayListD1 = i3.D0(arrayList2, arrayList3);
        List listJ2 = i3.J0(i3.K0(arrayListD0));
        ArrayList arrayList4 = new ArrayList();
        for (Object obj2 : listJ2) {
            String str5 = (String) obj2;
            if (!arrayList.contains(str5) && !list.contains(str5) && !x0.Y0(new String[]{"temp2", "nav_window"}).contains(str5)) {
                arrayList4.add(obj2);
            }
        }
        ArrayList arrayList5 = new ArrayList(k3.n0(arrayList4));
        int size = arrayList4.size();
        int i2 = 0;
        int i3 = 0;
        while (i3 < size) {
            Object obj3 = arrayList4.get(i3);
            i3++;
            String str6 = (String) obj3;
            arrayList5.add(new xj("a:" + str6, str6));
        }
        ArrayList arrayListD2 = i3.D0(arrayListD1, arrayList5);
        List listI0 = j3.i0("music", "temp");
        ArrayList arrayList6 = new ArrayList();
        for (Object obj4 : listI0) {
            String str7 = (String) obj4;
            if (arrayListD0.contains(str7) && list.contains(str7)) {
                arrayList6.add(obj4);
            }
        }
        ArrayList arrayList7 = new ArrayList(k3.n0(arrayList6));
        int size2 = arrayList6.size();
        int i4 = 0;
        while (i4 < size2) {
            Object obj5 = arrayList6.get(i4);
            i4++;
            String str8 = (String) obj5;
            arrayList7.add(new xj("a:" + str8, str8));
        }
        ArrayList arrayListD3 = i3.D0(arrayListD2, arrayList7);
        List list3 = eh.a;
        SharedPreferences sharedPreferencesB = mainActivity.B();
        sharedPreferencesB.getClass();
        ArrayList arrayListC = eh.c(sharedPreferencesB);
        ArrayList arrayList8 = new ArrayList(k3.n0(arrayListC));
        int size3 = arrayListC.size();
        while (i2 < size3) {
            Object obj6 = arrayListC.get(i2);
            i2++;
            String str9 = (String) obj6;
            arrayList8.add(new xj("s:" + str9, "split:" + str9));
        }
        return i3.D0(arrayListD3, arrayList8);
    }

    public static final void O(MainActivity mainActivity, boolean z, boolean z2) {
        if (mainActivity.h) {
            return;
        }
        mainActivity.j = z;
        if (z2 && mainActivity.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            mainActivity.requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 52);
            return;
        }
        pb pbVar = new pb(4, mainActivity, z);
        kg kgVar = new kg(1, mainActivity, z);
        List list = si.a;
        new h2(mainActivity, z, pbVar, kgVar, si.a(z ? "icon_set_load_from" : "icon_set_save_to", new Object[0]), null, 64).b();
    }

    public static final EditText O0(MainActivity mainActivity, String str, float f2, LinearLayout linearLayout) {
        LinearLayout linearLayoutL = mainActivity.l();
        linearLayoutL.setPadding(mainActivity.r(6), mainActivity.r(8), mainActivity.r(6), mainActivity.r(8));
        linearLayoutL.addView(mainActivity.u0(str, 16.0f, mainActivity.p));
        EditText editText = new EditText(mainActivity);
        editText.setInputType(8194);
        editText.setSingleLine();
        editText.setTextColor(mainActivity.o);
        editText.setTextSize(22.0f);
        editText.setText(String.format(Locale.ROOT, "%.1f", Arrays.copyOf(new Object[]{Float.valueOf(f2)}, 1)));
        editText.selectAll();
        editText.setContentDescription(str);
        editText.setBackground(mainActivity.m0(mainActivity.q, 12));
        editText.setPadding(mainActivity.r(12), mainActivity.r(8), mainActivity.r(12), mainActivity.r(8));
        linearLayoutL.addView(editText, new LinearLayout.LayoutParams(-1, mainActivity.r(52)));
        linearLayout.addView(linearLayoutL, new LinearLayout.LayoutParams(0, -2, 1.0f));
        return editText;
    }

    public static final d9 P(String str, MainActivity mainActivity) {
        d9 d9Var = new d9(mainActivity, str);
        int i2 = l8.a;
        d9Var.setColor(l8.j(mainActivity.z()));
        return d9Var;
    }

    public static void P0(Context context, String str, List list) {
        JSONArray jSONArray = new JSONArray();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            kd kdVar = (kd) it.next();
            jSONArray.put(new JSONObject().put("f", kdVar.a).put("n", kdVar.b).put("t", kdVar.c));
        }
        context.getSharedPreferences("panel", 0).edit().putString(str, jSONArray.toString()).apply();
    }

    public static final void Q(MainActivity mainActivity, boolean z) {
        if (mainActivity.h) {
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
            intent.setType("image/*");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.addFlags(1);
            mainActivity.startActivityForResult(intent, z ? 43 : 42);
        } catch (ActivityNotFoundException unused) {
            mainActivity.v0(si.a("no_file_picker_on_this_head_unit", new Object[0]));
        }
    }

    public static final String R(MainActivity mainActivity, xj xjVar) {
        xjVar.getClass();
        String str = xjVar.b;
        String str2 = xjVar.a;
        if (str2.startsWith("w:")) {
            return (String) hb.g0(mainActivity.n0, th.C(str2, "w:"));
        }
        if (!str2.startsWith("v:")) {
            return str2.startsWith("s:") ? c.d(si.a("split", new Object[0]), ": ", A0(th.C(str2, "s:"), mainActivity)) : mainActivity.W(str);
        }
        Set set = aj.i;
        return sh.m(str, mainActivity.E().a);
    }

    public static ArrayList S(Context context) {
        Iterable iterableJ;
        e2 e2Var;
        String string;
        Integer numQ;
        context.getClass();
        String string2 = context.getSharedPreferences("panel", 0).getString("auto_apps_list", null);
        if (string2 == null) {
            string2 = "";
        }
        fa faVar = new fa(string2);
        if (faVar.hasNext()) {
            Object next = faVar.next();
            if (faVar.hasNext()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(next);
                while (faVar.hasNext()) {
                    arrayList.add(faVar.next());
                }
                iterableJ = arrayList;
            } else {
                iterableJ = pi.J(next);
            }
        } else {
            iterableJ = u4.a;
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = iterableJ.iterator();
        while (it.hasNext()) {
            List listD = th.D((String) it.next(), new char[]{'\t'});
            String str = (String) i3.w0(listD, 0);
            String string3 = str != null ? th.J(str).toString() : null;
            if (string3 == null) {
                string3 = "";
            }
            if (string3.length() == 0) {
                e2Var = null;
            } else {
                String str2 = (String) i3.w0(listD, 1);
                e2Var = new e2((str2 == null || (string = th.J(str2).toString()) == null || (numQ = bi.q(string)) == null) ? 0 : pi.s(numQ.intValue(), 0, 120), string3);
            }
            if (e2Var != null) {
                arrayList2.add(e2Var);
            }
        }
        return arrayList2;
    }

    public static final ArrayList T(MainActivity mainActivity) {
        mainActivity.getClass();
        j8 j8VarF0 = pi.f0(0, mainActivity.V());
        ArrayList arrayList = new ArrayList(k3.n0(j8VarF0));
        Iterator it = j8VarF0.iterator();
        while (true) {
            i8 i8Var = (i8) it;
            if (!i8Var.c) {
                return arrayList;
            }
            arrayList.add(mainActivity.U(i8Var.nextInt()));
        }
    }

    public static final List U(MainActivity mainActivity) {
        mainActivity.getClass();
        ArrayList arrayListN0 = N0(mainActivity);
        ArrayList arrayList = new ArrayList(k3.n0(arrayListN0));
        int size = arrayListN0.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayListN0.get(i2);
            i2++;
            arrayList.add(((xj) obj).b);
        }
        return i3.J0(i3.K0(arrayList));
    }

    public static final void V(MainActivity mainActivity, int i2, int i3) {
        List list = eh.a;
        SharedPreferences sharedPreferencesB = mainActivity.B();
        sharedPreferencesB.getClass();
        ArrayList arrayList = new ArrayList(eh.c(sharedPreferencesB));
        if (i2 < 0 || i2 >= arrayList.size() || i3 < 0 || i3 >= arrayList.size()) {
            return;
        }
        Collections.swap(arrayList, i2, i3);
        mainActivity.B().edit().putString("split_list", i3.y0(arrayList, ",", null, 62)).apply();
        mainActivity.e0();
    }

    public static String W(Context context, String str, String str2) {
        Object obj;
        context.getClass();
        str2.getClass();
        ArrayList arrayListD0 = i3.D0(l0(context, "radio_favorites"), l0(context, "radio_stations"));
        int size = arrayListD0.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                obj = null;
                break;
            }
            obj = arrayListD0.get(i2);
            i2++;
            kd kdVar = (kd) obj;
            if (kdVar.b().equals(str) && kdVar.c(str2) && !th.A(kdVar.b)) {
                break;
            }
        }
        kd kdVar2 = (kd) obj;
        String str3 = kdVar2 != null ? kdVar2.b : null;
        return str3 == null ? "" : str3;
    }

    public static boolean X(Context context) {
        context.getClass();
        if (!context.getSharedPreferences("panel", 0).getBoolean("auto_enabled", false) && !context.getSharedPreferences("panel", 0).getBoolean("stats_enabled", true) && !context.getSharedPreferences("panel", 0).getBoolean("call_pause_music", true)) {
            boolean z = q.a;
            if (!q.c(context)) {
                return false;
            }
        }
        return true;
    }

    public static ArrayList Y() {
        ArrayList arrayList;
        synchronized (MusicNotificationListener.a) {
            Collection collectionValues = MusicNotificationListener.b.values();
            collectionValues.getClass();
            List listG0 = i3.G0(collectionValues, new h0(5));
            HashSet hashSet = new HashSet();
            arrayList = new ArrayList();
            for (Object obj : listG0) {
                if (hashSet.add(((hc) obj).b)) {
                    arrayList.add(obj);
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0125  */
    public static void Z(Activity activity, ComponentName componentName, c3 c3Var, ya yaVar) {
        Class cls = Integer.TYPE;
        String str = qc.a.format(new Date());
        str.getClass();
        ArrayList arrayListJ0 = j3.j0("=== External launch " + str + " ===", qc.b(activity, componentName));
        int i2 = 0;
        Display display = ((DisplayManager) activity.getSystemService(DisplayManager.class)).getDisplay(0);
        Point point = new Point();
        if (display != null) {
            display.getRealSize(point);
        }
        c0(arrayListJ0, yaVar, c.c(point.x, point.y, "Requested: display=0 windowingMode=1 NEW_TASK|CLEAR_TASK; physical=", "x"));
        ActivityOptions launchDisplayId = ActivityOptions.makeBasic().setLaunchDisplayId(0);
        launchDisplayId.setLaunchBounds(null);
        try {
            ActivityOptions.class.getMethod("setLaunchWindowingMode", cls).invoke(launchDisplayId, 1);
        } catch (ReflectiveOperationException e2) {
            e = e2;
            Throwable cause = e.getCause();
            if (cause != null) {
                e = cause;
            }
            c0(arrayListJ0, yaVar, "Fullscreen option: " + e);
        }
        try {
            Object objInvoke = ActivityManager.class.getMethod("getService", null).invoke(null, null);
            Class<?> cls2 = Class.forName("android.app.IActivityManager");
            Object objInvoke2 = cls2.getMethod("getAllStackInfos", null).invoke(objInvoke, null);
            objInvoke2.getClass();
            Method method = cls2.getMethod("removeTask", cls);
            ArrayList arrayListT0 = i3.t0((List) objInvoke2);
            int size = arrayListT0.size();
            int i3 = 0;
            while (i3 < size) {
                Object obj = arrayListT0.get(i3);
                i3++;
                Class<?> cls3 = obj.getClass();
                Object obj2 = cls3.getField("taskNames").get(obj);
                obj2.getClass();
                Object[] objArr = (Object[]) obj2;
                Object obj3 = cls3.getField("taskIds").get(obj);
                obj3.getClass();
                int[] iArr = (int[]) obj3;
                int length = iArr.length;
                int i4 = i2;
                while (i4 < length) {
                    Object objT0 = x0.T0(objArr, i4);
                    if (objT0 instanceof String) {
                        if (!objT0.equals(componentName.getPackageName())) {
                            ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString((String) objT0);
                            if (e(componentNameUnflattenFromString != null ? componentNameUnflattenFromString.getPackageName() : null, componentName.getPackageName())) {
                            }
                        }
                        method.invoke(objInvoke, Integer.valueOf(iArr[i4]));
                        c0(arrayListJ0, yaVar, "Removed navigator task " + iArr[i4]);
                    }
                    i4++;
                    objInvoke = objInvoke;
                    i2 = 0;
                }
            }
        } catch (Exception e3) {
            e = e3;
            Throwable cause2 = e.getCause();
            if (cause2 != null) {
                e = cause2;
            }
            c0(arrayListJ0, yaVar, "Task cleanup: " + e);
        }
        ActivityManager activityManager = (ActivityManager) activity.getSystemService(ActivityManager.class);
        try {
            ActivityManager.class.getMethod("forceStopPackage", String.class).invoke(activityManager, componentName.getPackageName());
            c0(arrayListJ0, yaVar, "forceStopPackage accepted");
        } catch (Exception e4) {
            Throwable th = e4;
            try {
                activityManager.killBackgroundProcesses(componentName.getPackageName());
                c0(arrayListJ0, yaVar, "killBackgroundProcesses accepted");
            } catch (Exception e5) {
                e = e5;
                Throwable cause3 = th.getCause();
                if (cause3 != null) {
                    th = cause3;
                }
                Throwable cause4 = e.getCause();
                if (cause4 != null) {
                    e = cause4;
                }
                c0(arrayListJ0, yaVar, "Restart refused: " + th + "; " + e);
            }
        }
        Intent intentAddFlags = new Intent("android.intent.action.MAIN").addCategory("android.intent.category.LAUNCHER").setComponent(componentName).addFlags(268468224);
        intentAddFlags.getClass();
        activity.startActivity(intentAddFlags, launchDisplayId.toBundle());
        c0(arrayListJ0, yaVar, "Fresh external start accepted");
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new b5(c3Var, activity, componentName, arrayListJ0, yaVar, 0), 350L);
        handler.postDelayed(new b5(c3Var, activity, componentName, arrayListJ0, yaVar, 1), 1100L);
        handler.postDelayed(new b5(c3Var, activity, componentName, arrayListJ0, yaVar, 2), 3000L);
    }

    public static void a(MainActivity mainActivity, String str, String str2, List list, y5 y5Var, v1 v1Var, v1 v1Var2, j6 j6Var, int i2) {
        int iArgb;
        y5 y5Var2 = (i2 & 8) != 0 ? null : y5Var;
        boolean z = (i2 & 16) != 0;
        j6 yaVar = (i2 & 32) != 0 ? new ya(mainActivity, 5) : v1Var;
        j6 yaVar2 = (i2 & 64) != 0 ? new ya(mainActivity, 6) : v1Var2;
        mainActivity.getClass();
        str.getClass();
        i4 i4Var = new i4(mainActivity, mainActivity.z());
        int i3 = l8.a;
        int iJ = l8.j(mainActivity.z());
        int iK = l8.k(mainActivity.z());
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        me meVar = new me(0);
        LinearLayout linearLayout2 = new LinearLayout(mainActivity);
        if (z) {
            Button buttonA = i4Var.a(si.a("any_app", new Object[0]), true, new n7(meVar, mainActivity, str, j6Var));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, mainActivity.r(52), 1.0f);
            layoutParams.rightMargin = mainActivity.r(8);
            linearLayout2.addView(buttonA, layoutParams);
        }
        if (y5Var2 != null) {
            linearLayout2.addView(i4Var.a(si.a("clear", new Object[0]), false, new y1(meVar, y5Var2, 6)), new LinearLayout.LayoutParams(-2, mainActivity.r(52)));
        }
        linearLayout.addView(linearLayout2);
        TextView textView = new TextView(mainActivity);
        textView.setText(si.a("action", new Object[0]));
        textView.setTextSize(15.0f);
        textView.setTextColor(iK);
        textView.setPadding(mainActivity.r(4), mainActivity.r(18), 0, mainActivity.r(8));
        linearLayout.addView(textView);
        GridLayout gridLayout = new GridLayout(mainActivity);
        gridLayout.setColumnCount(((float) gridLayout.getResources().getDisplayMetrics().widthPixels) / gridLayout.getResources().getDisplayMetrics().density < 700.0f ? 2 : 3);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str3 = (String) it.next();
            boolean zE = e(str3, str2);
            LinearLayout linearLayout3 = new LinearLayout(mainActivity);
            linearLayout3.setOrientation(1);
            linearLayout3.setGravity(17);
            linearLayout3.setPadding(mainActivity.r(10), mainActivity.r(10), mainActivity.r(10), mainActivity.r(10));
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setCornerRadius(mainActivity.r(18));
            gradientDrawable.setColor(mainActivity.z() ? Color.rgb(34, 58, 77) : -1);
            int iR = mainActivity.r(zE ? 2 : 1);
            if (zE) {
                l8.h();
                iArgb = -10233877;
            } else {
                iArgb = Color.argb(80, 127, 171, 199);
            }
            gradientDrawable.setStroke(iR, iArgb);
            linearLayout3.setBackground(gradientDrawable);
            linearLayout3.setClickable(true);
            linearLayout3.setFocusable(true);
            linearLayout3.setContentDescription((CharSequence) yaVar2.b(str3));
            linearLayout3.setOnClickListener(new qb(str3, meVar, j6Var));
            View view = (View) yaVar.b(str3);
            if (view instanceof cj) {
                cj cjVar = (cj) view;
                cjVar.setEnabledState(Boolean.FALSE);
                cjVar.setNightMode(mainActivity.z());
            }
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(mainActivity.r(52), mainActivity.r(46));
            layoutParams2.bottomMargin = mainActivity.r(6);
            linearLayout3.addView(view, layoutParams2);
            TextView textView2 = new TextView(mainActivity);
            textView2.setText((CharSequence) yaVar2.b(str3));
            textView2.setTextSize(16.0f);
            textView2.setTextColor(iJ);
            textView2.setGravity(17);
            textView2.setMaxLines(2);
            linearLayout3.addView(textView2, new LinearLayout.LayoutParams(-1, -2));
            GridLayout.LayoutParams layoutParams3 = new GridLayout.LayoutParams();
            layoutParams3.width = 0;
            layoutParams3.height = mainActivity.r(124);
            layoutParams3.columnSpec = GridLayout.spec(Integer.MIN_VALUE, 1.0f);
            layoutParams3.setMargins(mainActivity.r(4), mainActivity.r(4), mainActivity.r(4), mainActivity.r(4));
            gridLayout.addView(linearLayout3, layoutParams3);
            it = it;
            i4Var = i4Var;
        }
        linearLayout.addView(gridLayout);
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.addView(linearLayout);
        Dialog dialogF = i4Var.f(str, "", scrollView);
        meVar.b = dialogF;
        dialogF.show();
    }

    public static final void a0(c3 c3Var, Activity activity, ComponentName componentName, ArrayList arrayList, ya yaVar, String str) {
        boolean z;
        ArrayList arrayList2;
        Object ffVar;
        ArrayList arrayList3;
        componentName = componentName;
        if (!((Boolean) c3Var.a()).booleanValue() || activity.isDestroyed()) {
            return;
        }
        try {
            Object objInvoke = ActivityManager.class.getMethod("getService", null).invoke(null, null);
            Class<?> cls = Class.forName("android.app.IActivityManager");
            Object objInvoke2 = cls.getMethod("getAllStackInfos", null).invoke(objInvoke, null);
            objInvoke2.getClass();
            ArrayList arrayListT0 = i3.t0((List) objInvoke2);
            int size = arrayListT0.size();
            boolean z2 = false;
            int i2 = 0;
            while (i2 < size) {
                Object obj = arrayListT0.get(i2);
                i2++;
                Class<?> cls2 = obj.getClass();
                Object obj2 = cls2.getField("taskNames").get(obj);
                obj2.getClass();
                Object[] objArr = (Object[]) obj2;
                int length = objArr.length;
                int i3 = 0;
                while (i3 < length) {
                    boolean z3 = z2;
                    if (b0(componentName, objArr[i3])) {
                        int i4 = cls2.getField("displayId").getInt(obj);
                        int i5 = cls2.getField("stackId").getInt(obj);
                        Object obj3 = cls2.getField("taskIds").get(obj);
                        obj3.getClass();
                        int[] iArr = (int[]) obj3;
                        c0(arrayList, yaVar, str + ": " + qc.d(obj));
                        if (i4 == 0) {
                            if (str.equals("repair")) {
                                int length2 = objArr.length;
                                int i6 = 0;
                                while (true) {
                                    if (i6 >= length2) {
                                        z = true;
                                        Class cls3 = Integer.TYPE;
                                        Class cls4 = Boolean.TYPE;
                                        Method method = cls.getMethod("resizeStack", cls3, Rect.class, cls4, cls4, cls4, cls3);
                                        Integer numValueOf = Integer.valueOf(i5);
                                        Boolean bool = Boolean.FALSE;
                                        method.invoke(objInvoke, numValueOf, null, bool, bool, bool, -1);
                                        c0(arrayList, yaVar, "Stack override cleared; preserveWindows=false");
                                        int length3 = iArr.length;
                                        int i7 = 0;
                                        while (i7 < length3) {
                                            if (b0(componentName, x0.T0(objArr, i7))) {
                                                try {
                                                    Class cls5 = Integer.TYPE;
                                                    arrayList2 = arrayListT0;
                                                    try {
                                                        ffVar = cls.getMethod("setTaskWindowingMode", cls5, cls5, Boolean.TYPE).invoke(objInvoke, Integer.valueOf(iArr[i7]), 1, Boolean.FALSE);
                                                    } catch (Throwable th) {
                                                        th = th;
                                                        ffVar = new ff(th);
                                                    }
                                                } catch (Throwable th2) {
                                                    th = th2;
                                                    arrayList2 = arrayListT0;
                                                }
                                                if (!(ffVar instanceof ff)) {
                                                    c0(arrayList, yaVar, "Task " + iArr[i7] + " set to windowingMode=1");
                                                }
                                                Throwable thA = gf.a(ffVar);
                                                if (thA != null) {
                                                    Throwable cause = thA.getCause();
                                                    if (cause != null) {
                                                        thA = cause;
                                                    }
                                                    c0(arrayList, yaVar, "Window mode: " + thA);
                                                }
                                            } else {
                                                arrayList2 = arrayListT0;
                                            }
                                            i7++;
                                            componentName = componentName;
                                            arrayListT0 = arrayList2;
                                        }
                                        break;
                                    }
                                    z = true;
                                    if (!b0(componentName, objArr[i6])) {
                                        break;
                                    } else {
                                        i6++;
                                    }
                                }
                                arrayList3 = arrayListT0;
                            }
                            z2 = z;
                            arrayListT0 = arrayList3;
                            break;
                        }
                        c0(arrayList, yaVar, "Navigator is still on display " + i4);
                        arrayList3 = arrayListT0;
                        z = true;
                        z2 = z;
                        arrayListT0 = arrayList3;
                        break;
                        break;
                    }
                    i3++;
                    z2 = z3;
                    componentName = componentName;
                }
            }
            if (z2) {
                return;
            }
            c0(arrayList, yaVar, str + ": navigator task not yet present");
        } catch (Exception e2) {
            e = e2;
            Throwable cause2 = e.getCause();
            if (cause2 != null) {
                e = cause2;
            }
            c0(arrayList, yaVar, str + ": " + e);
        }
    }

    public static final boolean b(String str, MainActivity mainActivity) {
        int i2;
        mainActivity.getClass();
        str.getClass();
        ArrayList arrayList = new ArrayList(T(mainActivity));
        int iIndexOf = arrayList.indexOf(str);
        if (iIndexOf >= 0) {
            List list = si.a;
            mainActivity.v0(si.a("this_icon_is_already_in_the_bottom", Integer.valueOf(iIndexOf + 1)));
            return false;
        }
        int iIndexOf2 = arrayList.indexOf("empty");
        if (iIndexOf2 >= 0) {
            arrayList.set(iIndexOf2, str);
        } else {
            if (arrayList.isEmpty()) {
                i2 = 0;
            } else {
                int size = arrayList.size();
                i2 = 0;
                int i3 = 0;
                while (i3 < size) {
                    Object obj = arrayList.get(i3);
                    i3++;
                    if (!e((String) obj, "gap") && (i2 = i2 + 1) < 0) {
                        j3.l0();
                        throw null;
                    }
                }
            }
            if (i2 >= 20 || arrayList.size() >= 40) {
                mainActivity.v0(si.a("the_bottom_bar_supports_20_buttons_remove", new Object[0]));
                return false;
            }
            arrayList.add(str);
        }
        SharedPreferences sharedPreferencesB = mainActivity.B();
        Set<String> set = x4.a;
        Set<String> stringSet = sharedPreferencesB.getStringSet("desktop_hidden", set);
        if (stringSet != null) {
            set = stringSet;
        }
        SharedPreferences.Editor editorPutStringSet = mainActivity.B().edit().putStringSet("desktop_hidden", zf.g0(set, "ios_dock"));
        if (str.equals("widget:car")) {
            editorPutStringSet.putBoolean("show_vehicle", true);
        }
        editorPutStringSet.apply();
        mainActivity.o0 = 0;
        s0(arrayList, mainActivity);
        mainActivity.v0(si.a("added_to_the_bottom_bar", new Object[0]));
        return true;
    }

    public static final boolean b0(ComponentName componentName, Object obj) {
        if (!(obj instanceof String)) {
            return false;
        }
        if (obj.equals(componentName.getPackageName())) {
            return true;
        }
        ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString((String) obj);
        return e(componentNameUnflattenFromString != null ? componentNameUnflattenFromString.getPackageName() : null, componentName.getPackageName());
    }

    public static final void c(MainActivity mainActivity, Integer num) {
        ArrayList arrayList = new ArrayList(T(mainActivity));
        if (arrayList.size() >= 40) {
            mainActivity.v0(si.a("the_bottom_bar_supports_up_to_20", new Object[0]));
        } else {
            arrayList.add(num != null ? pi.s(num.intValue() + 1, 0, arrayList.size()) : arrayList.size(), "gap");
            s0(arrayList, mainActivity);
        }
    }

    public static final void c0(ArrayList arrayList, ya yaVar, String str) {
        if (arrayList.size() >= 60) {
            arrayList.remove(2);
        }
        String str2 = qc.a.format(new Date());
        str2.getClass();
        arrayList.add(str2 + "  " + str);
        yaVar.b(i3.y0(arrayList, "\n", null, 62));
    }

    public static final void d(MainActivity mainActivity) {
        if (j(mainActivity) >= 20 || mainActivity.V() >= 40) {
            mainActivity.v0(si.a("the_bottom_bar_supports_up_to_20", new Object[0]));
            return;
        }
        ArrayList arrayListE0 = i3.E0(T(mainActivity), "empty");
        s0(arrayListE0, mainActivity);
        u(mainActivity, arrayListE0.size() - 1);
    }

    public static final void d0(MainActivity mainActivity) {
        Intent intent;
        Object ffVar;
        if (Guard.INSTANCE.offset(mainActivity) != 0) {
            return;
        }
        Iterator it = l2.a.iterator();
        do {
            intent = null;
            if (!it.hasNext()) {
                break;
            }
            try {
                ffVar = mainActivity.getPackageManager().getLaunchIntentForPackage((String) it.next());
            } catch (Throwable th) {
                ffVar = new ff(th);
            }
            intent = (Intent) (ffVar instanceof ff ? null : ffVar);
        } while (intent == null);
        if (intent == null) {
            mainActivity.v0(si.a("camera360_missing", new Object[0]));
        } else {
            mainActivity.Q(intent);
        }
    }

    public static boolean e(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    public static final void e0(MainActivity mainActivity) {
        Window window;
        Dialog dialog = new Dialog(mainActivity);
        if (pi.c && (window = dialog.getWindow()) != null) {
            window.setType(2038);
        }
        dialog.requestWindowFeature(1);
        int i2 = l8.a;
        int iJ = l8.j(mainActivity.z());
        int iK = l8.k(mainActivity.z());
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(mainActivity.r(22), mainActivity.r(16), mainActivity.r(22), mainActivity.r(20));
        int i3 = 0;
        linearLayout.setBackground(new b7(mainActivity.z(), mainActivity.r(28), false));
        LinearLayout linearLayout2 = new LinearLayout(mainActivity);
        linearLayout2.setGravity(16);
        TextView textView = new TextView(mainActivity);
        textView.setText(si.a("folder", new Object[0]));
        textView.setTextSize(26.0f);
        textView.setTextColor(iJ);
        textView.setTypeface(textView.getTypeface(), 1);
        linearLayout2.addView(textView, new LinearLayout.LayoutParams(0, -2, 1.0f));
        TextView textView2 = new TextView(mainActivity);
        textView2.setText("✕");
        textView2.setTextSize(22.0f);
        textView2.setTextColor(iJ);
        textView2.setGravity(17);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(l8.e(mainActivity.z()));
        gradientDrawable.setCornerRadius(mainActivity.r(16));
        textView2.setBackground(gradientDrawable);
        textView2.setContentDescription(si.a("close", new Object[0]));
        textView2.setOnClickListener(new q5(dialog, i3));
        linearLayout2.addView(textView2, new LinearLayout.LayoutParams(mainActivity.r(52), mainActivity.r(48)));
        linearLayout.addView(linearLayout2);
        TextView textView3 = new TextView(mainActivity);
        textView3.setText(si.a("folder_hint", new Object[0]));
        textView3.setTextSize(14.0f);
        textView3.setTextColor(iK);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = mainActivity.r(4);
        layoutParams.bottomMargin = mainActivity.r(12);
        linearLayout.addView(textView3, layoutParams);
        GridLayout gridLayout = new GridLayout(mainActivity);
        gridLayout.setColumnCount(5);
        ArrayList arrayList = new ArrayList();
        f0(dialog, gridLayout, arrayList, mainActivity);
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.addView(gridLayout);
        linearLayout.addView(scrollView, new LinearLayout.LayoutParams(-2, -2));
        dialog.setContentView(linearLayout);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setOnDismissListener(new r5(mainActivity, arrayList, i3));
        Window window2 = dialog.getWindow();
        if (window2 != null) {
            window2.setBackgroundDrawable(new ColorDrawable(0));
            window2.setDimAmount(0.55f);
            window2.setFlags(8, 8);
            window2.getDecorView().setSystemUiVisibility(mainActivity.getWindow().getDecorView().getSystemUiVisibility());
        }
        dialog.show();
        Window window3 = dialog.getWindow();
        if (window3 != null) {
            window3.clearFlags(8);
        }
    }

    public static final void f(MainActivity mainActivity, boolean z) {
        if (mainActivity.h) {
            return;
        }
        mainActivity.i = z;
        if (mainActivity.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            mainActivity.requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 46);
        } else {
            new h2(mainActivity, z, new pb(3, mainActivity, z), new kg(0, mainActivity, z), null, null, 112).b();
        }
    }

    public static final void f0(Dialog dialog, final GridLayout gridLayout, ArrayList arrayList, final MainActivity mainActivity) {
        final Dialog dialog2 = dialog;
        final ArrayList arrayList2 = arrayList;
        gridLayout.removeAllViews();
        ArrayList arrayList3 = mainActivity.B;
        arrayList3.removeAll(arrayList2);
        arrayList2.clear();
        Iterator it = w5.a(mainActivity).iterator();
        while (true) {
            int i2 = 0;
            if (!it.hasNext()) {
                ArrayList arrayList4 = arrayList2;
                boolean z = mainActivity.z();
                d9 d9Var = new d9(mainActivity, "plus");
                d9Var.setColor(l8.f());
                View j9Var = new j9(mainActivity, z, d9Var, si.a("folder_add", new Object[0]), true);
                j9Var.setOnClickListener(new u5(dialog2, gridLayout, arrayList4, mainActivity));
                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                layoutParams.width = mainActivity.r(150);
                layoutParams.height = mainActivity.r(128);
                layoutParams.setMargins(mainActivity.r(4), mainActivity.r(4), mainActivity.r(4), mainActivity.r(4));
                gridLayout.addView(j9Var, layoutParams);
                arrayList3.addAll(arrayList4);
                mainActivity.c0();
                return;
            }
            final String str = (String) it.next();
            View j9Var2 = new j9(mainActivity, mainActivity.z(), mainActivity.M(str), mainActivity.W(str), true);
            j9Var2.setOnClickListener(new s5(mainActivity, str, dialog2, i2));
            ArrayList arrayList5 = arrayList2;
            j9Var2.setOnLongClickListener(new View.OnLongClickListener() { // from class: t5
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    MainActivity mainActivity2 = mainActivity;
                    AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity2);
                    String str2 = str;
                    AlertDialog.Builder items = builder.setTitle(mainActivity2.W(str2)).setItems(new String[]{si.a("folder_remove", new Object[0])}, new v5(mainActivity2, str2, gridLayout, arrayList2, dialog2));
                    items.getClass();
                    mainActivity2.p0(items);
                    return true;
                }
            });
            arrayList5.add(new yc(str, j9Var2));
            GridLayout.LayoutParams layoutParams2 = new GridLayout.LayoutParams();
            layoutParams2.width = mainActivity.r(150);
            layoutParams2.height = mainActivity.r(128);
            layoutParams2.setMargins(mainActivity.r(4), mainActivity.r(4), mainActivity.r(4), mainActivity.r(4));
            gridLayout.addView(j9Var2, layoutParams2);
            dialog2 = dialog2;
            arrayList2 = arrayList5;
        }
    }

    public static int g(String str) {
        if (str.equals("FM")) {
            return 0;
        }
        return str.equals("AM") ? 1 : 2;
    }

    public static final void g0(MainActivity mainActivity, Integer num) {
        mainActivity.getClass();
        new i0(mainActivity, mainActivity.z(), num == null ? si.a("apps", new Object[0]) : si.a("choose_an_app", new Object[0]), num == null, new q0(num, mainActivity, 6)).show();
    }

    public static String h(String str) {
        str.getClass();
        if (str.startsWith("air:")) {
            return "airflow";
        }
        return str.startsWith("seat:") ? th.C(str, "seat:") : (String) hb.j0(new yc("auto", "auto"), new yc("ac", "snow"), new yc("recirc", "recirc"), new yc("front", "front"), new yc("sync", "text:SYNC"), new yc("rear", "rear"), new yc("wiper", "wiper_heat"), new yc("nozzle", "nozzle_heat"), new yc("steering", "steering")).get(str);
    }

    public static final void h0(MainActivity mainActivity) {
        ArrayList arrayListN0 = N0(mainActivity);
        i4 i4Var = new i4(mainActivity, mainActivity.z());
        ArrayList arrayList = new ArrayList(k3.n0(arrayListN0));
        int size = arrayListN0.size();
        int i2 = 0;
        int i3 = 0;
        while (i3 < size) {
            Object obj = arrayListN0.get(i3);
            i3++;
            arrayList.add(((xj) obj).a);
        }
        ArrayList arrayList2 = new ArrayList(k3.n0(arrayListN0));
        int size2 = arrayListN0.size();
        while (i2 < size2) {
            Object obj2 = arrayListN0.get(i2);
            i2++;
            arrayList2.add(R(mainActivity, (xj) obj2));
        }
        i4Var.b(arrayList, arrayList2, new q0(arrayListN0, mainActivity, 7));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static String i(Context context, String str) {
        String strA;
        str.getClass();
        if (!str.startsWith("air:")) {
            if (!str.startsWith("seat:")) {
                switch (str.hashCode()) {
                    case -1039494662:
                        if (str.equals("nozzle")) {
                            return si.a("nozzle_heating", new Object[0]);
                        }
                        break;
                    case -934914614:
                        if (str.equals("recirc")) {
                            return si.a("recirculation", new Object[0]);
                        }
                        break;
                    case 3106:
                        if (str.equals("ac")) {
                            return "A/C";
                        }
                        break;
                    case 3005871:
                        if (str.equals("auto")) {
                            return "AUTO";
                        }
                        break;
                    case 3496356:
                        if (str.equals("rear")) {
                            return si.a("rear_window_heating", new Object[0]);
                        }
                        break;
                    case 3545755:
                        if (str.equals("sync")) {
                            return si.a("climate_sync_zones", new Object[0]);
                        }
                        break;
                    case 97705513:
                        if (str.equals("front")) {
                            return si.a("windshield_defogging", new Object[0]);
                        }
                        break;
                    case 113137931:
                        if (str.equals("wiper")) {
                            List list = z2.a;
                            return z2.b(context.getSharedPreferences("panel", 0).getString("vehicle", null));
                        }
                        break;
                    case 1419257969:
                        if (str.equals("steering")) {
                            return si.a("steering_wheel_heating", new Object[0]);
                        }
                        break;
                }
            } else {
                String strC = th.C(str, "seat:");
                String str2 = (String) q8.t.get(strC);
                if (str2 != null && (strA = si.a(str2, new Object[0])) != null) {
                    return strA;
                }
                Set set = rf.d;
                rf rfVar = (rf) pi.B().get(strC);
                if (rfVar != null) {
                    return rfVar.c;
                }
            }
        } else {
            String str3 = (String) hb.j0(new yc(268894465, "face_85"), new yc(268894467, "face_feet"), new yc(268894466, "feet_87"), new yc(268894470, "feet_windshield"), new yc(268894468, "windshield_89")).get(bi.q(th.C(str, "air:")));
            if (str3 != null) {
                return si.a(str3, new Object[0]);
            }
        }
        return null;
    }

    public static void i0(Context context) {
        Object ffVar;
        Object ffVar2;
        Object ffVar3;
        a3 a3Var = c;
        IBinder iBinder = a;
        boolean z = true;
        if ((iBinder == null || !iBinder.isBinderAlive()) && !b) {
            try {
                context.getPackageManager().getPackageInfo("ecarx.hvac.app", 0);
                ffVar = Boolean.TRUE;
            } catch (Throwable th) {
                ffVar = new ff(th);
            }
            Object obj = Boolean.FALSE;
            if (ffVar instanceof ff) {
                ffVar = obj;
            }
            if (((Boolean) ffVar).booleanValue()) {
                Context applicationContext = context.getApplicationContext();
                Intent component = new Intent().setComponent(new ComponentName("ecarx.hvac.app", "ecarx.hvac.app.service.HvacAppService"));
                component.getClass();
                Intent intent = new Intent("ecarx.hvac.app.HvacAppService").setPackage("ecarx.hvac.app");
                intent.getClass();
                try {
                    ffVar2 = Boolean.valueOf(applicationContext.bindService(component, a3Var, 1));
                } catch (Throwable th2) {
                    ffVar2 = new ff(th2);
                }
                Object obj2 = Boolean.FALSE;
                if (ffVar2 instanceof ff) {
                    ffVar2 = obj2;
                }
                if (!((Boolean) ffVar2).booleanValue()) {
                    try {
                        ffVar3 = Boolean.valueOf(applicationContext.bindService(intent, a3Var, 1));
                    } catch (Throwable th3) {
                        ffVar3 = new ff(th3);
                    }
                    Object obj3 = Boolean.FALSE;
                    if (ffVar3 instanceof ff) {
                        ffVar3 = obj3;
                    }
                    if (!((Boolean) ffVar3).booleanValue()) {
                        z = false;
                    }
                }
                b = z;
                if (b) {
                    return;
                }
                Log.w("ReferencePanel.HvacClose", "HvacAppService is not bindable");
            }
        }
    }

    public static final int j(MainActivity mainActivity) {
        ArrayList arrayListT = T(mainActivity);
        int i2 = 0;
        if (arrayListT.isEmpty()) {
            return 0;
        }
        int size = arrayListT.size();
        int i3 = 0;
        while (i3 < size) {
            Object obj = arrayListT.get(i3);
            i3++;
            if (!e((String) obj, "gap") && (i2 = i2 + 1) < 0) {
                j3.l0();
                throw null;
            }
        }
        return i2;
    }

    public static final void j0(MainActivity mainActivity, Dialog dialog, ld ldVar) {
        md mdVar = md.a;
        SharedPreferences.Editor editorEdit = mainActivity.getSharedPreferences("panel", 0).edit();
        if (ldVar == null) {
            editorEdit.remove("radio_city");
        } else {
            editorEdit.putString("radio_city", ldVar.a + "|" + ldVar.b);
        }
        editorEdit.apply();
        dialog.dismiss();
        wa waVar = mainActivity.y().x;
        if (waVar != null) {
            waVar.a();
        }
        mainActivity.e0();
    }

    public static final void k(MainActivity mainActivity, int i2, int i3) {
        mainActivity.getClass();
        nj njVarH = mainActivity.H();
        v1 v1Var = new v1(mainActivity, 7);
        njVarH.getClass();
        njVarH.b(v1Var, new fj(njVarH, i3, i2, 1));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void k0(LinearLayout linearLayout, i4 i4Var, MainActivity mainActivity, List list, Dialog dialog, Map map, String str) {
        linearLayout.removeAllViews();
        View viewA = i4Var.a(si.a("radio_city_auto", new Object[0]), false, new rb(mainActivity, dialog, 3));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, mainActivity.r(60));
        int i2 = 6;
        layoutParams.bottomMargin = mainActivity.r(6);
        linearLayout.addView(viewA, layoutParams);
        String lowerCase = th.J(str).toString().toLowerCase(Locale.ROOT);
        lowerCase.getClass();
        String strReplace = lowerCase.replace((char) 1105, (char) 1077);
        strReplace.getClass();
        int i3 = 1;
        a4 a4Var = new a4(new o5(new y0(i3, list), true, new g7(i3, strReplace)), s(new g7(2, strReplace), new fg(0), new fg(i3)), i3);
        for (ld ldVar : a4Var instanceof o4 ? ((o4) a4Var).a() : new gi(a4Var)) {
            String str2 = ldVar.b;
            String str3 = ldVar.a;
            String str4 = (String) map.get(str3);
            if (str4 != null) {
                str3 = str4;
            }
            View viewA2 = i4Var.a(c.d(str2, " · ", str3), false, new u0(ldVar, mainActivity, dialog, i2));
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, mainActivity.r(60));
            layoutParams2.bottomMargin = mainActivity.r(6);
            linearLayout.addView(viewA2, layoutParams2);
        }
    }

    public static void l(int i2, int i3, int i4) {
        if (i2 >= 0 && i3 <= i4) {
            if (i2 <= i3) {
                return;
            }
            w.a(c.c(i2, i3, "fromIndex: ", " > toIndex: "));
        } else {
            throw new IndexOutOfBoundsException("fromIndex: " + i2 + ", toIndex: " + i3 + ", size: " + i4);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v1, types: [ff] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v6, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r8v8 */
    public static List l0(Context context, String str) {
        ?? ffVar;
        try {
            JSONArray jSONArray = new JSONArray(context.getSharedPreferences("panel", 0).getString(str, "[]"));
            j8 j8VarF0 = pi.f0(0, jSONArray.length());
            ffVar = new ArrayList(k3.n0(j8VarF0));
            Iterator it = j8VarF0.iterator();
            while (((i8) it).c) {
                JSONObject jSONObject = jSONArray.getJSONObject(((i8) it).nextInt());
                String string = jSONObject.getString("f");
                string.getClass();
                String strOptString = jSONObject.optString("n");
                strOptString.getClass();
                ffVar.add(new kd(string, strOptString, jSONObject.optInt("t", 3)));
            }
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        boolean z = ffVar instanceof ff;
        ?? r8 = ffVar;
        if (z) {
            r8 = u4.a;
        }
        return (List) r8;
    }

    public static final void m(final MainActivity mainActivity) {
        Window window;
        mainActivity.getClass();
        Dialog dialog = new Dialog(mainActivity);
        if (pi.c && (window = dialog.getWindow()) != null) {
            window.setType(2038);
        }
        List list = z2.a;
        boolean zR0 = i3.r0(z2.b, mainActivity.E().a);
        int i2 = 2;
        final q8 q8Var = new q8(mainActivity, mainActivity.z(), zR0, new x2(new v1(mainActivity, 8), new v1(mainActivity, 9), new v1(mainActivity, 10), new v1(mainActivity, 11)), new q0(mainActivity, dialog, i2));
        final Handler handler = new Handler(Looper.getMainLooper());
        final k1 k1Var = new k1(i2, dialog);
        q8Var.setOnInteract(new y1(handler, k1Var, 1));
        mainActivity.D0 = q8Var;
        q8Var.e(mainActivity.Z);
        List<String> list2 = y2.a;
        ArrayList arrayList = new ArrayList();
        for (String str : list2) {
            List list3 = y2.a;
            SharedPreferences sharedPreferencesB = mainActivity.B();
            sharedPreferencesB.getClass();
            o3.p0(y2.c(sharedPreferencesB, str, zR0), arrayList);
        }
        nj njVarH = mainActivity.H();
        Set set = mainActivity.H().s;
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        int i3 = 0;
        while (i3 < size) {
            Object obj = arrayList.get(i3);
            i3++;
            if (ci.t((String) obj, "seat:")) {
                arrayList2.add(obj);
            }
        }
        ArrayList arrayList3 = new ArrayList(k3.n0(arrayList2));
        int size2 = arrayList2.size();
        int i4 = 0;
        while (i4 < size2) {
            Object obj2 = arrayList2.get(i4);
            i4++;
            arrayList3.add(th.C((String) obj2, "seat:"));
        }
        LinkedHashSet linkedHashSetH0 = zf.h0(set, arrayList3);
        ArrayList arrayList4 = new ArrayList();
        int size3 = arrayList.size();
        int i5 = 0;
        while (i5 < size3) {
            Object obj3 = arrayList.get(i5);
            i5++;
            Set set2 = rf.d;
            if (pi.B().containsKey((String) obj3)) {
                arrayList4.add(obj3);
            }
        }
        LinkedHashSet linkedHashSetH1 = zf.h0(linkedHashSetH0, arrayList4);
        njVarH.getClass();
        njVarH.s = linkedHashSetH1;
        mainActivity.H().p();
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.setFillViewport(true);
        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.addView(q8Var);
        dialog.setContentView(scrollView);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: d3
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                handler.removeCallbacks(k1Var);
                MainActivity mainActivity2 = mainActivity;
                if (mainActivity2.D0 == q8Var) {
                    mainActivity2.D0 = null;
                }
            }
        });
        Window window2 = dialog.getWindow();
        if (window2 != null) {
            if (mainActivity.A()) {
                window2.setType(2038);
            }
            window2.setBackgroundDrawable(new ColorDrawable(0));
            window2.setDimAmount(0.55f);
            window2.setFlags(8, 8);
            window2.getDecorView().setSystemUiVisibility(mainActivity.getWindow().getDecorView().getSystemUiVisibility());
        }
        dialog.show();
        DisplayMetrics displayMetrics = mainActivity.getResources().getDisplayMetrics();
        Window window3 = dialog.getWindow();
        if (window3 != null) {
            window3.setLayout(Math.min((int) (l8.m(mainActivity) * 1120.0f), displayMetrics.widthPixels - mainActivity.r(36)), Math.min((int) (l8.m(mainActivity) * 610.0f), displayMetrics.heightPixels - mainActivity.r(36)));
        }
        Window window4 = dialog.getWindow();
        if (window4 != null) {
            window4.clearFlags(8);
        }
        handler.postDelayed(k1Var, 15000L);
    }

    public static final void m0(MainActivity mainActivity) {
        i4 i4Var = new i4(mainActivity, mainActivity.z());
        LinearLayout linearLayoutL = mainActivity.l();
        TextView textViewU0 = mainActivity.u0(si.a("positions_sizes_and_scale_will_be_reset", new Object[0]), 19.0f, mainActivity.o);
        textViewU0.setPadding(0, 0, 0, mainActivity.r(20));
        linearLayoutL.addView(textViewU0);
        String strA = si.a("default_home_screen", new Object[0]);
        String strA2 = si.a("restore_the_default_layout", new Object[0]);
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.addView(linearLayoutL);
        Dialog dialogF = i4Var.f(strA, strA2, scrollView);
        int i2 = 2;
        linearLayoutL.addView(i4Var.a(si.a("restore_default_appearance", new Object[0]), false, new rb(mainActivity, dialogF, i2)), new LinearLayout.LayoutParams(-1, mainActivity.r(52)));
        Button buttonA = i4Var.a(si.a("keep_as_is", new Object[0]), true, new w1(dialogF, i2));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, mainActivity.r(52));
        layoutParams.topMargin = mainActivity.r(8);
        linearLayoutL.addView(buttonA, layoutParams);
        dialogF.show();
    }

    public static final View n(String str, MainActivity mainActivity) {
        str.getClass();
        Map map = q8.t;
        String strH = h(str);
        if (strH != null) {
            boolean z = mainActivity.z();
            if (!str.startsWith("air:")) {
                str = null;
            }
            return N(mainActivity, z, strH, str != null ? bi.q(th.C(str, "air:")) : null);
        }
        View viewM = mainActivity.M(str);
        if (viewM instanceof d9) {
            int i2 = l8.a;
            ((d9) viewM).setColor(l8.j(mainActivity.z()));
        }
        return viewM;
    }

    public static int n0(double d2) {
        if (Double.isNaN(d2)) {
            w.a("Cannot round NaN value.");
            return 0;
        }
        if (d2 > 2.147483647E9d) {
            return Integer.MAX_VALUE;
        }
        if (d2 < -2.147483648E9d) {
            return Integer.MIN_VALUE;
        }
        return (int) Math.round(d2);
    }

    public static final void o(MainActivity mainActivity, String str, Integer num) {
        List list = z2.a;
        boolean zR0 = i3.r0(z2.b, mainActivity.E().a);
        List list2 = y2.a;
        SharedPreferences sharedPreferencesB = mainActivity.B();
        sharedPreferencesB.getClass();
        List listC = y2.c(sharedPreferencesB, str, zR0);
        String str2 = num != null ? (String) i3.w0(listC, num.intValue()) : null;
        List<String> list3 = y2.a;
        ArrayList arrayList = new ArrayList();
        for (String str3 : list3) {
            List list4 = y2.a;
            SharedPreferences sharedPreferencesB2 = mainActivity.B();
            sharedPreferencesB2.getClass();
            o3.p0(y2.c(sharedPreferencesB2, str3, zR0), arrayList);
        }
        Set setL0 = i3.L0(arrayList);
        List listA = y2.a();
        ArrayList arrayList2 = new ArrayList(k3.n0(listA));
        Iterator it = listA.iterator();
        while (it.hasNext()) {
            arrayList2.add("air:" + ((Number) it.next()).intValue());
        }
        ArrayList arrayListD0 = i3.D0(i3.D0(i3.D0(arrayList2, j3.i0("auto", "ac", "recirc", "front")), zR0 ? pi.J("sync") : u4.a), j3.i0("rear", "wiper", "nozzle", "steering"));
        Set set = rf.d;
        Set setKeySet = pi.B().keySet();
        setKeySet.getClass();
        ArrayList arrayList3 = new ArrayList(k3.n0(setKeySet));
        Iterator it2 = setKeySet.iterator();
        while (it2.hasNext()) {
            arrayList3.add("seat:" + ((String) it2.next()));
        }
        ArrayList arrayListD1 = i3.D0(arrayListD0, arrayList3);
        ArrayList arrayListG = mainActivity.G();
        ArrayList arrayList4 = new ArrayList();
        int size = arrayListG.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayListG.get(i2);
            i2++;
            List list5 = y2.a;
            LinkedHashSet linkedHashSetH0 = zf.h0(x0.Y0(new String[]{"temp", "temp2", "temp_passenger", "climate", "airflow", "nav_window", "ac", "auto", "recirc", "defrost_rear", "steering"}), aj.i);
            Set set2 = rf.d;
            Set setKeySet2 = pi.B().keySet();
            setKeySet2.getClass();
            if (!zf.h0(linkedHashSetH0, setKeySet2).contains((String) obj)) {
                arrayList4.add(obj);
            }
        }
        List listJ0 = i3.J0(i3.K0(i3.D0(arrayListD1, arrayList4)));
        ArrayList arrayList5 = new ArrayList();
        for (Object obj2 : listJ0) {
            String str4 = (String) obj2;
            if (!setL0.contains(str4) || e(str4, str2)) {
                arrayList5.add(obj2);
            }
        }
        a(mainActivity, y2.e(str), str2, arrayList5, null, new v1(mainActivity, 2), new v1(mainActivity, 3), new b3(listC, num, mainActivity, str), 8);
    }

    public static int o0(float f2) {
        if (!Float.isNaN(f2)) {
            return Math.round(f2);
        }
        w.a("Cannot round NaN value.");
        return 0;
    }

    public static final void p(Closeable closeable, Throwable th) throws IllegalAccessException, IOException, InvocationTargetException {
        if (closeable != null) {
            if (th == null) {
                closeable.close();
                return;
            }
            try {
                closeable.close();
            } catch (Throwable th2) {
                if (th != th2) {
                    Integer num = w9.a;
                    if (num == null || num.intValue() >= 19) {
                        th.addSuppressed(th2);
                        return;
                    }
                    Method method = cd.a;
                    if (method != null) {
                        method.invoke(th, th2);
                    }
                }
            }
        }
    }

    public static final void p0(MainActivity mainActivity, int i2) {
        try {
            AudioManager audioManager = (AudioManager) mainActivity.getSystemService(AudioManager.class);
            if (audioManager != null) {
                audioManager.adjustStreamVolume(3, i2, 1);
            }
        } catch (Throwable unused) {
        }
    }

    public static final s8 q(int i2, MainActivity mainActivity, boolean z) {
        boolean z2 = false;
        z2 = false;
        s8 s8Var = new s8(mainActivity, mainActivity.z(), new c3(mainActivity, i2, false ? 1 : 0), new c3(mainActivity, i2, 1), new b2(mainActivity, 2), new b2(mainActivity, 3), z, i2);
        mainActivity.P.add(s8Var);
        Float f2 = (Float) mainActivity.Z.i.a.get(Integer.valueOf(i2));
        Integer num = (Integer) mainActivity.Z.i.f.get(268501248);
        if (num != null && num.intValue() != 0) {
            z2 = true;
        }
        s8Var.c(f2, z2);
        return s8Var;
    }

    public static void q0(RuntimeException runtimeException, String str) {
        StackTraceElement[] stackTrace = runtimeException.getStackTrace();
        int length = stackTrace.length;
        int i2 = -1;
        for (int i3 = 0; i3 < length; i3++) {
            if (str.equals(stackTrace[i3].getClassName())) {
                i2 = i3;
            }
        }
        runtimeException.setStackTrace((StackTraceElement[]) Arrays.copyOfRange(stackTrace, i2 + 1, length));
    }

    public static void r0(Context context, List list) {
        P0(context, "radio_favorites", list);
        wa waVar = i;
        if (waVar != null) {
            new Handler(Looper.getMainLooper()).post(new o1(waVar));
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [r3] */
    public static r3 s(final j6... j6VarArr) {
        if (j6VarArr.length > 0) {
            return new Comparator() { // from class: r3
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    for (j6 j6Var : j6VarArr) {
                        int iT = k8.t((Comparable) j6Var.b(obj), (Comparable) j6Var.b(obj2));
                        if (iT != 0) {
                            return iT;
                        }
                    }
                    return 0;
                }
            };
        }
        w.a("Failed requirement.");
        return null;
    }

    public static final void s0(List list, MainActivity mainActivity) {
        mainActivity.getClass();
        SharedPreferences.Editor editorEdit = mainActivity.B().edit();
        int i2 = 0;
        for (int i3 = 0; i3 < 40; i3++) {
            editorEdit.remove("menu_action_" + i3);
        }
        for (Object obj : list) {
            int i4 = i2 + 1;
            if (i2 < 0) {
                j3.m0();
                throw null;
            }
            editorEdit.putString("menu_action_" + i2, (String) obj);
            i2 = i4;
        }
        editorEdit.putInt("menu_count", list.size()).apply();
        mainActivity.e0();
    }

    public static int t(Comparable comparable, Comparable comparable2) {
        if (comparable == comparable2) {
            return 0;
        }
        if (comparable == null) {
            return -1;
        }
        if (comparable2 == null) {
            return 1;
        }
        return comparable.compareTo(comparable2);
    }

    public static final void t0(MainActivity mainActivity, int i2) {
        mainActivity.getClass();
        List list = z2.a;
        v1 v1Var = new v1(mainActivity, 6);
        nj njVarH = mainActivity.H();
        int iS = pi.s(i2, 1, 8) + 268566784;
        njVarH.getClass();
        njVarH.b(v1Var, new fj(njVarH, 268566784, iS, 0));
    }

    public static final void u(MainActivity mainActivity, int i2) {
        List list = si.a;
        a(mainActivity, si.a("button_0", Integer.valueOf(i2 + 1)), mainActivity.U(i2), U(mainActivity), new c3(mainActivity, i2, 5), null, null, new ra(mainActivity, i2, 1), 112);
    }

    /* JADX WARN: Code duplicated, block: B:20:0x022b A[PHI: r24
      0x022b: PHI (r24v22 java.util.List) =
      (r24v8 java.util.List)
      (r24v9 java.util.List)
      (r24v11 java.util.List)
      (r24v15 java.util.List)
      (r24v23 java.util.List)
     binds: [B:178:0x0b7b, B:174:0x0b55, B:419:?, B:418:?, B:19:0x0229] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:21:0x022f A[PHI: r0 r4 r24
      0x022f: PHI (r0v427 int) = (r0v39 int), (r0v89 int), (r0v428 int) binds: [B:295:0x13f8, B:291:0x13d5, B:20:0x022b] A[DONT_GENERATE, DONT_INLINE]
      0x022f: PHI (r4v136 int) = (r4v16 int), (r4v21 int), (r4v137 int) binds: [B:295:0x13f8, B:291:0x13d5, B:20:0x022b] A[DONT_GENERATE, DONT_INLINE]
      0x022f: PHI (r24v21 java.util.List) = (r24v4 java.util.List), (r24v5 java.util.List), (r24v22 java.util.List) binds: [B:295:0x13f8, B:291:0x13d5, B:20:0x022b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:244:0x10fc  */
    /* JADX WARN: Code duplicated, block: B:318:0x17bb  */
    /* JADX WARN: Code duplicated, block: B:319:0x17bd  */
    /* JADX WARN: Code duplicated, block: B:322:0x17c7  */
    /* JADX WARN: Code duplicated, block: B:329:0x185f  */
    /* JADX WARN: Code duplicated, block: B:331:0x1869  */
    /* JADX WARN: Code duplicated, block: B:334:0x18c6  */
    /* JADX WARN: Code duplicated, block: B:336:0x18d0  */
    /* JADX WARN: Code duplicated, block: B:339:0x1936  */
    /* JADX WARN: Code duplicated, block: B:340:0x1956  */
    /* JADX WARN: Code duplicated, block: B:343:0x1a22  */
    /* JADX WARN: Code duplicated, block: B:345:0x1a28  */
    /* JADX WARN: Code duplicated, block: B:347:0x1a2c  */
    /* JADX WARN: Code duplicated, block: B:348:0x1a2f  */
    /* JADX WARN: Code duplicated, block: B:386:0x17bb A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:387:0x17df A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:390:0x17c1 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v334, types: [boolean] */
    /* JADX WARN: Type inference failed for: r11v80, types: [j6] */
    /* JADX WARN: Type inference failed for: r12v110, types: [android.view.View, android.widget.ScrollView, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r14v10 */
    /* JADX WARN: Type inference failed for: r14v11, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r14v12 */
    /* JADX WARN: Type inference failed for: r14v13 */
    /* JADX WARN: Type inference failed for: r14v14 */
    /* JADX WARN: Type inference failed for: r14v15 */
    /* JADX WARN: Type inference failed for: r14v16 */
    /* JADX WARN: Type inference failed for: r14v17 */
    /* JADX WARN: Type inference failed for: r14v18 */
    /* JADX WARN: Type inference failed for: r14v19 */
    /* JADX WARN: Type inference failed for: r14v2, types: [android.view.View, android.view.ViewGroup, android.widget.LinearLayout] */
    /* JADX WARN: Type inference failed for: r14v20 */
    /* JADX WARN: Type inference failed for: r14v21 */
    /* JADX WARN: Type inference failed for: r14v22 */
    /* JADX WARN: Type inference failed for: r14v23 */
    /* JADX WARN: Type inference failed for: r14v24 */
    /* JADX WARN: Type inference failed for: r14v25 */
    /* JADX WARN: Type inference failed for: r14v26 */
    /* JADX WARN: Type inference failed for: r14v27 */
    /* JADX WARN: Type inference failed for: r14v3, types: [android.view.ViewGroup] */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r14v6, types: [android.view.ViewGroup] */
    /* JADX WARN: Type inference failed for: r14v8, types: [boolean] */
    /* JADX WARN: Type inference failed for: r27v4 */
    /* JADX WARN: Type inference failed for: r27v5 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v6, types: [android.content.Context, java.lang.Object, org.reference.panel.MainActivity] */
    /* JADX WARN: Type inference failed for: r7v0, types: [c9] */
    public static final FrameLayout u0(MainActivity mainActivity) {
        List list;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        boolean z;
        int i7;
        String strA;
        int i8;
        int i9;
        int i10;
        Integer num;
        boolean z2;
        int i11;
        final int i12;
        String str;
        String str2;
        int i13;
        String str3;
        String strA2;
        int i14;
        int i15;
        int i16;
        ?? r14;
        ?? r3;
        ?? r15;
        MainActivity mainActivity2;
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList;
        Iterator it;
        AccessibilityServiceInfo accessibilityServiceInfo;
        boolean z3;
        String strA3;
        String strA4;
        long j;
        int i17;
        String strA5;
        String str4;
        final MainActivity mainActivity3 = mainActivity;
        ?? c9Var = new c9(mainActivity3, mainActivity3.z(), mainActivity3.w(), new ya(mainActivity3, 17));
        String str5 = "system";
        Object obj = "apps";
        Object obj2 = "split";
        List<b9> listI0 = j3.i0(new b9(l8.f(), "desktop", si.a("home_screen", new Object[0]), "grid"), new b9(Color.rgb(94, 92, 230), "dock", si.a("bottom_bar", new Object[0]), "menu"), new b9(Color.rgb(255, 159, 10), "style", si.a("appearance", new Object[0]), "sun"), new b9(Color.rgb(255, 55, 95), "apps", si.a("apps", new Object[0]), "music"), new b9(Color.rgb(0, 199, 190), "split", si.a("split", new Object[0]), "split"), new b9(Color.rgb(48, 209, 88), "car", si.a("vehicle", new Object[0]), "carfront"), new b9(Color.rgb(90, 200, 250), "climate", si.a("climate_window", new Object[0]), "thermometer"), new b9(Color.rgb(175, 82, 222), "auto", si.a("automation", new Object[0]), "ambient"), new b9(Color.rgb(50, 173, 230), "stats", si.a("trip_statistics", new Object[0]), "lane"), new b9(Color.rgb(88, 86, 214), "saver", si.a("screensaver", new Object[0]), "screen"), new b9(Color.rgb(142, 142, 147), "system", si.a("startup_and_system", new Object[0]), "gear"));
        if (listI0.isEmpty()) {
            mainActivity3.l0();
            break;
        }
        Iterator it2 = listI0.iterator();
        do {
            if (!it2.hasNext()) {
                mainActivity3.l0();
                break;
            }
        } while (!e(((b9) it2.next()).a, mainActivity3.u));
        ?? linearLayout = new LinearLayout(c9Var.a);
        linearLayout.setOrientation(1);
        int i18 = 28;
        linearLayout.setPadding(mainActivity3.r(8), mainActivity3.r(4), mainActivity3.r(28), mainActivity3.r(28));
        for (b9 b9Var : listI0) {
            if (e(b9Var.a, mainActivity3.u)) {
                linearLayout.addView(c9Var.e(b9Var.a()));
                String str6 = mainActivity3.u;
                String str7 = "chevdown";
                String str8 = "chevup";
                final int i19 = 3;
                int i20 = 2;
                switch (str6.hashCode()) {
                    case 98260:
                        final int i21 = i18;
                        list = listI0;
                        i2 = 14;
                        i3 = 16;
                        i4 = 0;
                        i5 = 24;
                        if (str6.equals("car")) {
                            linearLayout.addView(c9Var.i(si.a("your_vehicles_model_color_and_image", new Object[0])));
                            linearLayout.addView(c9Var.d(c9.o(c9Var, si.a("model", new Object[0]), null, mainActivity3.y0(), new y5() { // from class: jg
                                @Override // defpackage.y5
                                public final Object a() {
                                    String strY0;
                                    switch (i21) {
                                        case 0:
                                            k8.d(mainActivity3);
                                            break;
                                        case 1:
                                            k8.c(mainActivity3, null);
                                            break;
                                        case 2:
                                            MainActivity mainActivity4 = mainActivity3;
                                            ArrayList arrayListC = j0.c();
                                            AlertDialog.Builder title = new AlertDialog.Builder(mainActivity4).setTitle(si.a("app_language_choose", new Object[0]));
                                            ArrayList arrayList = new ArrayList(k3.n0(arrayListC));
                                            int size = arrayListC.size();
                                            int i22 = 0;
                                            while (i22 < size) {
                                                Object obj3 = arrayListC.get(i22);
                                                i22++;
                                                Object obj4 = (String) obj3;
                                                List list2 = j0.a;
                                                obj4.getClass();
                                                List list3 = j0.a;
                                                int iIndexOf = si.a.indexOf(obj4);
                                                if (iIndexOf >= 0 && iIndexOf < list3.size()) {
                                                    obj4 = list3.get(iIndexOf);
                                                }
                                                arrayList.add((String) obj4);
                                            }
                                            CharSequence[] charSequenceArr = (CharSequence[]) arrayList.toArray(new String[0]);
                                            List list4 = si.a;
                                            AlertDialog.Builder negativeButton = title.setSingleChoiceItems(charSequenceArr, arrayListC.indexOf(si.b.a), new dg(arrayListC, mainActivity4)).setNegativeButton(si.a("cancel", new Object[0]), (DialogInterface.OnClickListener) null);
                                            negativeButton.getClass();
                                            k8.w0(negativeButton);
                                            break;
                                        case 3:
                                            MainActivity mainActivity5 = mainActivity3;
                                            mainActivity5.c = false;
                                            mainActivity5.f = true;
                                            mainActivity5.e0();
                                            mainActivity5.X(new j(14));
                                            break;
                                        case 4:
                                            k8.v0("elastre", mainActivity3);
                                            break;
                                        case 5:
                                            k8.v0("manrope", mainActivity3);
                                            break;
                                        case 6:
                                            k8.v0("custom", mainActivity3);
                                            break;
                                        case 7:
                                            k8.K(mainActivity3, true);
                                            break;
                                        case 8:
                                            MainActivity mainActivity6 = mainActivity3;
                                            x3.d(mainActivity6).delete();
                                            SharedPreferences sharedPreferences = mainActivity6.getSharedPreferences("panel", 0);
                                            SharedPreferences.Editor editorRemove = sharedPreferences.edit().remove("font_custom_name").remove("font_custom_kk").remove("font_custom_wght");
                                            if (k8.e(sharedPreferences.getString("font_choice", ""), "custom")) {
                                                editorRemove.remove("font_choice");
                                            }
                                            editorRemove.apply();
                                            z9.d(mainActivity6);
                                            z9.a = true;
                                            mainActivity6.e0();
                                            break;
                                        case 9:
                                            MainActivity mainActivity7 = mainActivity3;
                                            i7.g(mainActivity7, "");
                                            mainActivity7.e0();
                                            break;
                                        case 10:
                                            k8.O(mainActivity3, true, true);
                                            break;
                                        case 11:
                                            k8.O(mainActivity3, false, true);
                                            break;
                                        case 12:
                                            k8.Q(mainActivity3, false);
                                            break;
                                        case 13:
                                            k8.Q(mainActivity3, true);
                                            break;
                                        case 14:
                                            MainActivity mainActivity8 = mainActivity3;
                                            new i0(mainActivity8, mainActivity8.z(), si.a("split_left", new Object[0]), false, new lg(mainActivity8, 7)).show();
                                            break;
                                        case 15:
                                            k8.C(mainActivity3);
                                            break;
                                        case 16:
                                            MainActivity mainActivity9 = mainActivity3;
                                            mainActivity9.u = "dock";
                                            mainActivity9.s = null;
                                            mainActivity9.t = 0;
                                            mainActivity9.e0();
                                            break;
                                        case 17:
                                            k8.g0(mainActivity3, 1);
                                            break;
                                        case 18:
                                            MainActivity mainActivity10 = mainActivity3;
                                            md mdVar = md.a;
                                            List list5 = md.d;
                                            if (list5 == null) {
                                                mdVar.c(mainActivity10);
                                                list5 = u4.a;
                                            }
                                            List list6 = list5;
                                            if (list6.isEmpty()) {
                                                mainActivity10.v0(si.a("radio_city_loading", new Object[0]));
                                            } else {
                                                Map mapJ0 = hb.j0(new yc("RU", si.a("country_ru", new Object[0])), new yc("BY", si.a("country_by", new Object[0])), new yc("KZ", si.a("country_kz", new Object[0])));
                                                i4 i4Var = new i4(mainActivity10, mainActivity10.z());
                                                ViewGroup viewGroupL = mainActivity10.l();
                                                EditText editText = new EditText(mainActivity10);
                                                editText.setHint(si.a("radio_city_search", new Object[0]));
                                                editText.setTextSize(20.0f);
                                                editText.setTextColor(mainActivity10.o);
                                                editText.setHintTextColor(mainActivity10.p);
                                                editText.setSingleLine(true);
                                                editText.setBackground(mainActivity10.m0(mainActivity10.q, 14));
                                                editText.setPadding(mainActivity10.r(16), 0, mainActivity10.r(16), 0);
                                                viewGroupL.addView(editText, new LinearLayout.LayoutParams(-1, mainActivity10.r(60)));
                                                LinearLayout linearLayoutL = mainActivity10.l();
                                                ScrollView scrollView = new ScrollView(mainActivity10);
                                                scrollView.addView(linearLayoutL);
                                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, mainActivity10.r(420));
                                                layoutParams.topMargin = mainActivity10.r(10);
                                                viewGroupL.addView(scrollView, layoutParams);
                                                Dialog dialogF = i4Var.f(si.a("radio_city", new Object[0]), si.a("radio_city_hint", new Object[0]), viewGroupL);
                                                editText.addTextChangedListener(new f0(linearLayoutL, i4Var, mainActivity10, list6, dialogF, mapJ0, 1));
                                                k8.k0(linearLayoutL, i4Var, mainActivity10, list6, dialogF, mapJ0, "");
                                                dialogF.show();
                                            }
                                            break;
                                        case 19:
                                            mainActivity3.f0();
                                            break;
                                        case 20:
                                            MainActivity mainActivity11 = mainActivity3;
                                            i4 i4Var2 = new i4(mainActivity11, mainActivity11.z());
                                            TextView textView = new TextView(mainActivity11);
                                            gc gcVarY = mainActivity11.y();
                                            if (gcVarY.n) {
                                                ArrayList arrayList2 = gcVarY.h;
                                                ArrayList arrayList3 = new ArrayList(k3.n0(arrayList2));
                                                int size2 = arrayList2.size();
                                                int i23 = 0;
                                                while (i23 < size2) {
                                                    Object obj5 = arrayList2.get(i23);
                                                    i23++;
                                                    arrayList3.add((MediaController) ((yc) obj5).a);
                                                }
                                                Object obj6 = MusicNotificationListener.a;
                                                ArrayList arrayListY = k8.Y();
                                                if (arrayList3.isEmpty() && arrayListY.isEmpty()) {
                                                    strY0 = si.a("no_media_sessions_start_radio_or_bluetooth", new Object[0]);
                                                } else {
                                                    List listI1 = j3.i0(i3.y0(arrayList3, "\n\n", new a(6, gcVarY), 30), i3.y0(arrayListY, "\n", new w(18), 30));
                                                    ArrayList arrayList4 = new ArrayList();
                                                    for (Object obj7 : listI1) {
                                                        if (!th.A((String) obj7)) {
                                                            arrayList4.add(obj7);
                                                        }
                                                    }
                                                    strY0 = i3.y0(arrayList4, "\n\n", null, 62);
                                                }
                                            } else {
                                                strY0 = si.a("no_music_access_enable_ReferencePanel_music_information", new Object[0]);
                                            }
                                            textView.setText(strY0);
                                            textView.setTextSize(17.0f);
                                            int i24 = l8.a;
                                            textView.setTextColor(l8.j(mainActivity11.z()));
                                            textView.setTextIsSelectable(true);
                                            textView.setLineSpacing(0.0f, 1.2f);
                                            String strA6 = si.a("music_diagnostics", new Object[0]);
                                            String strA7 = si.a("source_currently_shown_by_the_widget", new Object[0]);
                                            ScrollView scrollView2 = new ScrollView(mainActivity11);
                                            scrollView2.addView(textView);
                                            i4Var2.f(strA6, strA7, scrollView2).show();
                                            break;
                                        case 21:
                                            k8.m0(mainActivity3);
                                            break;
                                        case 22:
                                            mainActivity3.a0(0);
                                            break;
                                        case 23:
                                            MainActivity mainActivity12 = mainActivity3;
                                            ComponentName componentNameM = mainActivity12.m(0);
                                            if (componentNameM == null) {
                                                k8.g0(mainActivity12, 0);
                                            } else {
                                                mainActivity12.Q(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", componentNameM.getPackageName(), null)));
                                            }
                                            break;
                                        case 24:
                                            MainActivity mainActivity13 = mainActivity3;
                                            ArrayList arrayList5 = mainActivity13.w;
                                            int size3 = arrayList5.size();
                                            int i25 = 0;
                                            while (i25 < size3) {
                                                Object obj8 = arrayList5.get(i25);
                                                i25++;
                                                ((b) obj8).c();
                                            }
                                            arrayList5.clear();
                                            mainActivity13.c = false;
                                            mainActivity13.e0();
                                            break;
                                        case 25:
                                            MainActivity mainActivity14 = mainActivity3;
                                            k8.g0(mainActivity14, Integer.valueOf(mainActivity14.B0));
                                            break;
                                        case 26:
                                            MainActivity mainActivity15 = mainActivity3;
                                            mainActivity15.a0(mainActivity15.B0);
                                            break;
                                        case 27:
                                            k8.g0(mainActivity3, 0);
                                            break;
                                        case 28:
                                            MainActivity mainActivity16 = mainActivity3;
                                            if (!mainActivity16.h) {
                                                List listH = uh.h();
                                                ArrayList arrayList6 = new ArrayList(k3.n0(listH));
                                                Iterator it3 = listH.iterator();
                                                while (it3.hasNext()) {
                                                    arrayList6.add(((zi) it3.next()).b);
                                                }
                                                List listJ0 = i3.J0(i3.K0(arrayList6));
                                                c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity16).setTitle(si.a("vehicle_brand", new Object[0])).setItems((CharSequence[]) listJ0.toArray(new String[0]), new tb(mainActivity16, listJ0)), null, mainActivity16);
                                            }
                                            break;
                                        default:
                                            MainActivity mainActivity17 = mainActivity3;
                                            boolean z4 = mainActivity17.h;
                                            if (!z4 && !z4) {
                                                try {
                                                    Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                                                    intent.setType("image/*");
                                                    intent.addCategory("android.intent.category.OPENABLE");
                                                    intent.addFlags(1);
                                                    mainActivity17.startActivityForResult(intent, 41);
                                                } catch (ActivityNotFoundException unused) {
                                                    mainActivity17.v0(si.a("no_file_picker_app_on_this_head", new Object[0]));
                                                }
                                            }
                                            break;
                                    }
                                    return ti.a;
                                }
                            }, 8), c9Var.r(si.a("show_vehicle", new Object[0]), si.a("on_the_collapsed_screen_and_in_the", new Object[0]), mainActivity3.B().getBoolean("show_vehicle", true), new ya(mainActivity3, 29))));
                            if (mainActivity3.B().getBoolean("show_vehicle", true)) {
                                linearLayout.addView(c9Var.b(si.a("image", new Object[0])));
                                FrameLayout frameLayout = new FrameLayout(mainActivity3);
                                mainActivity3.i0(frameLayout);
                                linearLayout.addView(c9Var.c(frameLayout));
                                mainActivity3.b0(frameLayout);
                                boolean zExists = mainActivity3.o(mainActivity3.E().a).exists();
                                if (zExists) {
                                    strA = si.a("your_image_stored_on_the_device", new Object[0]);
                                    i7 = 0;
                                } else if (uh.c(mainActivity3.E().a, 0, false, false) != null) {
                                    zi ziVarE = mainActivity3.E();
                                    strA = si.a("0_catalog_image", c.d(ziVarE.b, " ", ziVarE.c));
                                    i7 = 0;
                                } else {
                                    i7 = 0;
                                    strA = si.a("add_your_own_image_for_this_model", new Object[0]);
                                }
                                linearLayout.addView(c9Var.l(strA));
                                final int i22 = 29;
                                View[] viewArr = new View[1];
                                viewArr[i7] = c9.o(c9Var, mainActivity3.x() ? si.a("loading", new Object[i7]) : si.a("upload_image", new Object[i7]), si.a("a_transparent_png_blends_into_the_background", new Object[i7]), null, new y5() { // from class: jg
                                    @Override // defpackage.y5
                                    public final Object a() {
                                        String strY0;
                                        switch (i22) {
                                            case 0:
                                                k8.d(mainActivity3);
                                                break;
                                            case 1:
                                                k8.c(mainActivity3, null);
                                                break;
                                            case 2:
                                                MainActivity mainActivity4 = mainActivity3;
                                                ArrayList arrayListC = j0.c();
                                                AlertDialog.Builder title = new AlertDialog.Builder(mainActivity4).setTitle(si.a("app_language_choose", new Object[0]));
                                                ArrayList arrayList = new ArrayList(k3.n0(arrayListC));
                                                int size = arrayListC.size();
                                                int i23 = 0;
                                                while (i23 < size) {
                                                    Object obj3 = arrayListC.get(i23);
                                                    i23++;
                                                    Object obj4 = (String) obj3;
                                                    List list2 = j0.a;
                                                    obj4.getClass();
                                                    List list3 = j0.a;
                                                    int iIndexOf = si.a.indexOf(obj4);
                                                    if (iIndexOf >= 0 && iIndexOf < list3.size()) {
                                                        obj4 = list3.get(iIndexOf);
                                                    }
                                                    arrayList.add((String) obj4);
                                                }
                                                CharSequence[] charSequenceArr = (CharSequence[]) arrayList.toArray(new String[0]);
                                                List list4 = si.a;
                                                AlertDialog.Builder negativeButton = title.setSingleChoiceItems(charSequenceArr, arrayListC.indexOf(si.b.a), new dg(arrayListC, mainActivity4)).setNegativeButton(si.a("cancel", new Object[0]), (DialogInterface.OnClickListener) null);
                                                negativeButton.getClass();
                                                k8.w0(negativeButton);
                                                break;
                                            case 3:
                                                MainActivity mainActivity5 = mainActivity3;
                                                mainActivity5.c = false;
                                                mainActivity5.f = true;
                                                mainActivity5.e0();
                                                mainActivity5.X(new j(14));
                                                break;
                                            case 4:
                                                k8.v0("elastre", mainActivity3);
                                                break;
                                            case 5:
                                                k8.v0("manrope", mainActivity3);
                                                break;
                                            case 6:
                                                k8.v0("custom", mainActivity3);
                                                break;
                                            case 7:
                                                k8.K(mainActivity3, true);
                                                break;
                                            case 8:
                                                MainActivity mainActivity6 = mainActivity3;
                                                x3.d(mainActivity6).delete();
                                                SharedPreferences sharedPreferences = mainActivity6.getSharedPreferences("panel", 0);
                                                SharedPreferences.Editor editorRemove = sharedPreferences.edit().remove("font_custom_name").remove("font_custom_kk").remove("font_custom_wght");
                                                if (k8.e(sharedPreferences.getString("font_choice", ""), "custom")) {
                                                    editorRemove.remove("font_choice");
                                                }
                                                editorRemove.apply();
                                                z9.d(mainActivity6);
                                                z9.a = true;
                                                mainActivity6.e0();
                                                break;
                                            case 9:
                                                MainActivity mainActivity7 = mainActivity3;
                                                i7.g(mainActivity7, "");
                                                mainActivity7.e0();
                                                break;
                                            case 10:
                                                k8.O(mainActivity3, true, true);
                                                break;
                                            case 11:
                                                k8.O(mainActivity3, false, true);
                                                break;
                                            case 12:
                                                k8.Q(mainActivity3, false);
                                                break;
                                            case 13:
                                                k8.Q(mainActivity3, true);
                                                break;
                                            case 14:
                                                MainActivity mainActivity8 = mainActivity3;
                                                new i0(mainActivity8, mainActivity8.z(), si.a("split_left", new Object[0]), false, new lg(mainActivity8, 7)).show();
                                                break;
                                            case 15:
                                                k8.C(mainActivity3);
                                                break;
                                            case 16:
                                                MainActivity mainActivity9 = mainActivity3;
                                                mainActivity9.u = "dock";
                                                mainActivity9.s = null;
                                                mainActivity9.t = 0;
                                                mainActivity9.e0();
                                                break;
                                            case 17:
                                                k8.g0(mainActivity3, 1);
                                                break;
                                            case 18:
                                                MainActivity mainActivity10 = mainActivity3;
                                                md mdVar = md.a;
                                                List list5 = md.d;
                                                if (list5 == null) {
                                                    mdVar.c(mainActivity10);
                                                    list5 = u4.a;
                                                }
                                                List list6 = list5;
                                                if (list6.isEmpty()) {
                                                    mainActivity10.v0(si.a("radio_city_loading", new Object[0]));
                                                } else {
                                                    Map mapJ0 = hb.j0(new yc("RU", si.a("country_ru", new Object[0])), new yc("BY", si.a("country_by", new Object[0])), new yc("KZ", si.a("country_kz", new Object[0])));
                                                    i4 i4Var = new i4(mainActivity10, mainActivity10.z());
                                                    ViewGroup viewGroupL = mainActivity10.l();
                                                    EditText editText = new EditText(mainActivity10);
                                                    editText.setHint(si.a("radio_city_search", new Object[0]));
                                                    editText.setTextSize(20.0f);
                                                    editText.setTextColor(mainActivity10.o);
                                                    editText.setHintTextColor(mainActivity10.p);
                                                    editText.setSingleLine(true);
                                                    editText.setBackground(mainActivity10.m0(mainActivity10.q, 14));
                                                    editText.setPadding(mainActivity10.r(16), 0, mainActivity10.r(16), 0);
                                                    viewGroupL.addView(editText, new LinearLayout.LayoutParams(-1, mainActivity10.r(60)));
                                                    LinearLayout linearLayoutL = mainActivity10.l();
                                                    ScrollView scrollView = new ScrollView(mainActivity10);
                                                    scrollView.addView(linearLayoutL);
                                                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, mainActivity10.r(420));
                                                    layoutParams.topMargin = mainActivity10.r(10);
                                                    viewGroupL.addView(scrollView, layoutParams);
                                                    Dialog dialogF = i4Var.f(si.a("radio_city", new Object[0]), si.a("radio_city_hint", new Object[0]), viewGroupL);
                                                    editText.addTextChangedListener(new f0(linearLayoutL, i4Var, mainActivity10, list6, dialogF, mapJ0, 1));
                                                    k8.k0(linearLayoutL, i4Var, mainActivity10, list6, dialogF, mapJ0, "");
                                                    dialogF.show();
                                                }
                                                break;
                                            case 19:
                                                mainActivity3.f0();
                                                break;
                                            case 20:
                                                MainActivity mainActivity11 = mainActivity3;
                                                i4 i4Var2 = new i4(mainActivity11, mainActivity11.z());
                                                TextView textView = new TextView(mainActivity11);
                                                gc gcVarY = mainActivity11.y();
                                                if (gcVarY.n) {
                                                    ArrayList arrayList2 = gcVarY.h;
                                                    ArrayList arrayList3 = new ArrayList(k3.n0(arrayList2));
                                                    int size2 = arrayList2.size();
                                                    int i24 = 0;
                                                    while (i24 < size2) {
                                                        Object obj5 = arrayList2.get(i24);
                                                        i24++;
                                                        arrayList3.add((MediaController) ((yc) obj5).a);
                                                    }
                                                    Object obj6 = MusicNotificationListener.a;
                                                    ArrayList arrayListY = k8.Y();
                                                    if (arrayList3.isEmpty() && arrayListY.isEmpty()) {
                                                        strY0 = si.a("no_media_sessions_start_radio_or_bluetooth", new Object[0]);
                                                    } else {
                                                        List listI1 = j3.i0(i3.y0(arrayList3, "\n\n", new a(6, gcVarY), 30), i3.y0(arrayListY, "\n", new w(18), 30));
                                                        ArrayList arrayList4 = new ArrayList();
                                                        for (Object obj7 : listI1) {
                                                            if (!th.A((String) obj7)) {
                                                                arrayList4.add(obj7);
                                                            }
                                                        }
                                                        strY0 = i3.y0(arrayList4, "\n\n", null, 62);
                                                    }
                                                } else {
                                                    strY0 = si.a("no_music_access_enable_ReferencePanel_music_information", new Object[0]);
                                                }
                                                textView.setText(strY0);
                                                textView.setTextSize(17.0f);
                                                int i25 = l8.a;
                                                textView.setTextColor(l8.j(mainActivity11.z()));
                                                textView.setTextIsSelectable(true);
                                                textView.setLineSpacing(0.0f, 1.2f);
                                                String strA6 = si.a("music_diagnostics", new Object[0]);
                                                String strA7 = si.a("source_currently_shown_by_the_widget", new Object[0]);
                                                ScrollView scrollView2 = new ScrollView(mainActivity11);
                                                scrollView2.addView(textView);
                                                i4Var2.f(strA6, strA7, scrollView2).show();
                                                break;
                                            case 21:
                                                k8.m0(mainActivity3);
                                                break;
                                            case 22:
                                                mainActivity3.a0(0);
                                                break;
                                            case 23:
                                                MainActivity mainActivity12 = mainActivity3;
                                                ComponentName componentNameM = mainActivity12.m(0);
                                                if (componentNameM == null) {
                                                    k8.g0(mainActivity12, 0);
                                                } else {
                                                    mainActivity12.Q(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", componentNameM.getPackageName(), null)));
                                                }
                                                break;
                                            case 24:
                                                MainActivity mainActivity13 = mainActivity3;
                                                ArrayList arrayList5 = mainActivity13.w;
                                                int size3 = arrayList5.size();
                                                int i26 = 0;
                                                while (i26 < size3) {
                                                    Object obj8 = arrayList5.get(i26);
                                                    i26++;
                                                    ((b) obj8).c();
                                                }
                                                arrayList5.clear();
                                                mainActivity13.c = false;
                                                mainActivity13.e0();
                                                break;
                                            case 25:
                                                MainActivity mainActivity14 = mainActivity3;
                                                k8.g0(mainActivity14, Integer.valueOf(mainActivity14.B0));
                                                break;
                                            case 26:
                                                MainActivity mainActivity15 = mainActivity3;
                                                mainActivity15.a0(mainActivity15.B0);
                                                break;
                                            case 27:
                                                k8.g0(mainActivity3, 0);
                                                break;
                                            case 28:
                                                MainActivity mainActivity16 = mainActivity3;
                                                if (!mainActivity16.h) {
                                                    List listH = uh.h();
                                                    ArrayList arrayList6 = new ArrayList(k3.n0(listH));
                                                    Iterator it3 = listH.iterator();
                                                    while (it3.hasNext()) {
                                                        arrayList6.add(((zi) it3.next()).b);
                                                    }
                                                    List listJ0 = i3.J0(i3.K0(arrayList6));
                                                    c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity16).setTitle(si.a("vehicle_brand", new Object[0])).setItems((CharSequence[]) listJ0.toArray(new String[0]), new tb(mainActivity16, listJ0)), null, mainActivity16);
                                                }
                                                break;
                                            default:
                                                MainActivity mainActivity17 = mainActivity3;
                                                boolean z4 = mainActivity17.h;
                                                if (!z4 && !z4) {
                                                    try {
                                                        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                                                        intent.setType("image/*");
                                                        intent.addCategory("android.intent.category.OPENABLE");
                                                        intent.addFlags(1);
                                                        mainActivity17.startActivityForResult(intent, 41);
                                                    } catch (ActivityNotFoundException unused) {
                                                        mainActivity17.v0(si.a("no_file_picker_app_on_this_head", new Object[0]));
                                                    }
                                                }
                                                break;
                                        }
                                        return ti.a;
                                    }
                                }, 12);
                                ArrayList arrayListJ0 = j3.j0(viewArr);
                                if (zExists) {
                                    arrayListJ0.add(c9.o(c9Var, si.a("delete_image", new Object[i7]), null, null, new mg(mainActivity3, i7), 6));
                                }
                                linearLayout.addView(c9Var.b(""));
                                View[] viewArr2 = (View[]) arrayListJ0.toArray(new View[i7]);
                                linearLayout.addView(c9Var.d((View[]) Arrays.copyOf(viewArr2, viewArr2.length)));
                                List listD = uh.d(mainActivity3.E().a);
                                if (zExists || listD.isEmpty()) {
                                    i8 = 0;
                                } else {
                                    i8 = 0;
                                    linearLayout.addView(c9Var.b(si.a("color", new Object[0])));
                                    linearLayout.addView(c9Var.d(c9Var.q(si.a("vehicle_color", new Object[0]), listD, pi.s(mainActivity3.F(), 0, j3.h0(listD)), new lg(mainActivity3, 1))));
                                }
                                linearLayout.addView(c9Var.b(si.a("image_size", new Object[i8])));
                                SeekBar seekBar = new SeekBar(mainActivity3);
                                seekBar.setMax(60);
                                seekBar.setProgress(mainActivity3.B().getInt("image_scale_".concat(mainActivity3.E().a), 20));
                                seekBar.setContentDescription(si.a("vehicle_image_size", new Object[0]));
                                seekBar.setPadding(mainActivity3.r(20), mainActivity3.r(22), mainActivity3.r(20), mainActivity3.r(22));
                                seekBar.setOnSeekBarChangeListener(new og(mainActivity3, 1));
                                i6 = 0;
                                linearLayout.addView(c9Var.d(seekBar));
                            } else {
                                i6 = 0;
                            }
                            linearLayout.addView(c9Var.b(si.a("connection", new Object[i6])));
                            z = true;
                            View[] viewArr3 = new View[1];
                            viewArr3[i6] = c9.o(c9Var, si.a("vehicle_connection_status", new Object[i6]), si.a("latest_vehicle_service_reading", new Object[i6]), null, new mg(mainActivity3, true ? 1 : 0), 12);
                            linearLayout.addView(c9Var.d(viewArr3));
                            r3 = mainActivity3;
                            r14 = linearLayout;
                        } else {
                            linearLayout.addView(c9Var.i(si.a("startup_display_system_and_backup", new Object[i4])));
                            linearLayout.addView(c9Var.b(si.a("start_with_the_head_unit", new Object[i4])));
                            LinearLayout linearLayoutR = c9Var.r(si.a("open_after_head_unit_boot", new Object[i4]), null, mainActivity3.B().getBoolean("autostart", true), new ya(mainActivity3, 11));
                            LinearLayout linearLayoutO = c9.o(c9Var, si.a("set_ReferencePanel_as_home_screen", new Object[i4]), null, null, new za(mainActivity3, 15), 14);
                            LinearLayout linearLayoutR2 = c9Var.r(si.a("full_screen", new Object[i4]), si.a("hide_head_unit_system_bars", new Object[i4]), mainActivity3.B().getBoolean("fullscreen", true), new ya(mainActivity3, 13));
                            View[] viewArr4 = new View[3];
                            viewArr4[i4] = linearLayoutR;
                            viewArr4[1] = linearLayoutO;
                            viewArr4[2] = linearLayoutR2;
                            linearLayout.addView(c9Var.d(viewArr4));
                            linearLayout.addView(c9Var.l(si.a("starts_after_a_reboot_wake_from_sleep", new Object[i4])));
                            linearLayout.addView(c9Var.b(si.a("system_519", new Object[i4])));
                            Object systemService = mainActivity3.getSystemService("accessibility");
                            systemService.getClass();
                            enabledAccessibilityServiceList = ((AccessibilityManager) systemService).getEnabledAccessibilityServiceList(-1);
                            enabledAccessibilityServiceList.getClass();
                            if (enabledAccessibilityServiceList.isEmpty()) {
                                z3 = false;
                            } else {
                                it = enabledAccessibilityServiceList.iterator();
                                while (true) {
                                    if (it.hasNext()) {
                                        accessibilityServiceInfo = (AccessibilityServiceInfo) it.next();
                                        if (!e(accessibilityServiceInfo.getResolveInfo().serviceInfo.packageName, mainActivity3.getPackageName()) && e(accessibilityServiceInfo.getResolveInfo().serviceInfo.name, NavigationAssistService.class.getName())) {
                                            z3 = true;
                                        }
                                    } else {
                                        z3 = false;
                                    }
                                }
                            }
                            LinearLayout linearLayoutR3 = c9Var.r(si.a("back_and_home_over_apps", new Object[0]), si.a("edge_gestures_or_floating_buttons", new Object[0]), mainActivity3.B().getBoolean("navigation_assist", false), new pb(2, mainActivity3, z3));
                            LinearLayout linearLayoutQ = c9Var.q(si.a("controls", new Object[0]), j3.i0(si.a("gestures", new Object[0]), si.a("buttons", new Object[0])), !e(mainActivity3.B().getString("navigation_assist_mode", "gestures"), "gestures") ? 1 : 0, new ya(mainActivity3, i2));
                            String strA6 = si.a("navigation_service", new Object[0]);
                            if (z3) {
                                strA3 = si.a("enabled", new Object[0]);
                            } else {
                                strA3 = si.a("enable_in_accessibility_settings", new Object[0]);
                            }
                            linearLayout.addView(c9Var.d(linearLayoutR3, linearLayoutQ, c9.o(c9Var, strA6, strA3, null, new za(mainActivity3, i3), 12)));
                            linearLayout.addView(c9Var.l(si.a("start_on_the_strip_at_the_bottom", new Object[0])));
                            LinearLayout linearLayoutO2 = c9.o(c9Var, si.a("media_settings", new Object[0]), null, null, new za(mainActivity3, 17), 14);
                            String strA7 = si.a("android_brightness_control", new Object[0]);
                            if (Settings.System.canWrite(mainActivity3)) {
                                strA4 = si.a("allowed", new Object[0]);
                            } else {
                                strA4 = si.a("allow_system_settings_changes", new Object[0]);
                            }
                            linearLayout.addView(c9Var.d(linearLayoutO2, c9.o(c9Var, strA7, strA4, null, new za(mainActivity3, 18), 12)));
                            linearLayout.addView(c9Var.d(c9Var.r(si.a("hide_stock_climate_popup", new Object[0]), si.a("do_not_show_the_stock_popup_over", new Object[0]), mainActivity3.B().getBoolean("suppress_climate_popup", true), new ya(mainActivity3, 15))));
                            j = mainActivity3.B().getLong("hvac_gate_seen", 0L);
                            if (j > 0) {
                                strA5 = si.a("hvac_gate_active", new SimpleDateFormat("d MMM, HH:mm", si.b.b).format(new Date(j)));
                                i17 = 0;
                            } else {
                                i17 = 0;
                                strA5 = si.a("some_head_units_open_the_stock_climate", new Object[0]);
                            }
                            linearLayout.addView(c9Var.l(strA5));
                            linearLayout.addView(c9Var.b(si.a("updates", new Object[i17])));
                            LinearLayout linearLayoutO3 = c9.o(c9Var, si.a("update_check", new Object[i17]), si.a("update_current_0", "1.0.2"), null, new za(mainActivity3, 19), 12);
                            String strW = pi.w(mainActivity3);
                            linearLayout.addView(c9Var.d(linearLayoutO3, c9Var.n(si.a("region_pass_code", new Object[0]), si.a("region_pass_code_hint", new Object[0]), strW, false, new w7(mainActivity3, strW, 3)), c9Var.r(si.a("update_auto", new Object[0]), si.a("update_auto_hint", new Object[0]), l0.k(mainActivity3), new ya(mainActivity3, 18)), c9Var.q(si.a("update_installer", new Object[0]), j3.i0(si.a("update_installer_system", new Object[0]), si.a("update_installer_freetuga", new Object[0])), l0.t(mainActivity3), new ya(mainActivity3, 19))));
                            if (l0.t(mainActivity3) != 1) {
                                str4 = "update_installer_system_note";
                            } else if (l0.o(mainActivity3)) {
                                str4 = "update_installer_freetuga_note";
                            } else {
                                str4 = "update_freetuga_missing";
                            }
                            linearLayout.addView(c9Var.l(si.a(str4, new Object[0])));
                            linearLayout.addView(c9Var.b(si.a("backup", new Object[0])));
                            linearLayout.addView(c9Var.d(c9.o(c9Var, si.a("export_settings", new Object[0]), si.a("home_screen_buttons_apps_themes_and_custom", new Object[0]), null, new za(mainActivity3, i5), 12), c9.o(c9Var, si.a("import_settings", new Object[0]), si.a("restore_an_ReferencePanel_backup", new Object[0]), null, new za(mainActivity3, 25), 12)));
                            linearLayout.addView(c9Var.l(si.a("import_replaces_the_current_settings_system_permissions", new Object[0])));
                            linearLayout.addView(c9Var.l(si.a("ReferencePanel_05_appearance_works_offline", "1.0.2")));
                            mainActivity2 = mainActivity3;
                            r15 = linearLayout;
                            z = true;
                            r3 = mainActivity2;
                            r14 = r15;
                        }
                        ?? scrollView = new ScrollView(r3);
                        scrollView.addView(r14);
                        scrollView.setVerticalScrollBarEnabled(z);
                        r3.k0(scrollView);
                        scrollView.post(new o(scrollView, r3, 9));
                        return c9Var.p(list, r3.u, new ya(r3, 20), new za(r3, 26), scrollView, new za(r3, 27));
                    case 3000946:
                        int i23 = i18;
                        Object obj3 = obj;
                        i2 = 14;
                        i3 = 16;
                        list = listI0;
                        if (str6.equals(obj3)) {
                            linearLayout.addView(c9Var.i(si.a("music_and_navigation_480", new Object[0])));
                            linearLayout.addView(c9Var.b(si.a("music", new Object[0])));
                            String strA8 = si.a("default_player", new Object[0]);
                            String strC = mainActivity3.c(1);
                            if (strC == null) {
                                strC = si.a("choose", new Object[0]);
                            }
                            String str9 = strC;
                            final int i24 = 17;
                            final int i25 = 24;
                            LinearLayout linearLayoutO4 = c9.o(c9Var, strA8, null, str9, new y5() { // from class: jg
                                @Override // defpackage.y5
                                public final Object a() {
                                    String strY0;
                                    switch (i24) {
                                        case 0:
                                            k8.d(mainActivity3);
                                            break;
                                        case 1:
                                            k8.c(mainActivity3, null);
                                            break;
                                        case 2:
                                            MainActivity mainActivity4 = mainActivity3;
                                            ArrayList arrayListC = j0.c();
                                            AlertDialog.Builder title = new AlertDialog.Builder(mainActivity4).setTitle(si.a("app_language_choose", new Object[0]));
                                            ArrayList arrayList = new ArrayList(k3.n0(arrayListC));
                                            int size = arrayListC.size();
                                            int i26 = 0;
                                            while (i26 < size) {
                                                Object obj4 = arrayListC.get(i26);
                                                i26++;
                                                Object obj5 = (String) obj4;
                                                List list2 = j0.a;
                                                obj5.getClass();
                                                List list3 = j0.a;
                                                int iIndexOf = si.a.indexOf(obj5);
                                                if (iIndexOf >= 0 && iIndexOf < list3.size()) {
                                                    obj5 = list3.get(iIndexOf);
                                                }
                                                arrayList.add((String) obj5);
                                            }
                                            CharSequence[] charSequenceArr = (CharSequence[]) arrayList.toArray(new String[0]);
                                            List list4 = si.a;
                                            AlertDialog.Builder negativeButton = title.setSingleChoiceItems(charSequenceArr, arrayListC.indexOf(si.b.a), new dg(arrayListC, mainActivity4)).setNegativeButton(si.a("cancel", new Object[0]), (DialogInterface.OnClickListener) null);
                                            negativeButton.getClass();
                                            k8.w0(negativeButton);
                                            break;
                                        case 3:
                                            MainActivity mainActivity5 = mainActivity3;
                                            mainActivity5.c = false;
                                            mainActivity5.f = true;
                                            mainActivity5.e0();
                                            mainActivity5.X(new j(14));
                                            break;
                                        case 4:
                                            k8.v0("elastre", mainActivity3);
                                            break;
                                        case 5:
                                            k8.v0("manrope", mainActivity3);
                                            break;
                                        case 6:
                                            k8.v0("custom", mainActivity3);
                                            break;
                                        case 7:
                                            k8.K(mainActivity3, true);
                                            break;
                                        case 8:
                                            MainActivity mainActivity6 = mainActivity3;
                                            x3.d(mainActivity6).delete();
                                            SharedPreferences sharedPreferences = mainActivity6.getSharedPreferences("panel", 0);
                                            SharedPreferences.Editor editorRemove = sharedPreferences.edit().remove("font_custom_name").remove("font_custom_kk").remove("font_custom_wght");
                                            if (k8.e(sharedPreferences.getString("font_choice", ""), "custom")) {
                                                editorRemove.remove("font_choice");
                                            }
                                            editorRemove.apply();
                                            z9.d(mainActivity6);
                                            z9.a = true;
                                            mainActivity6.e0();
                                            break;
                                        case 9:
                                            MainActivity mainActivity7 = mainActivity3;
                                            i7.g(mainActivity7, "");
                                            mainActivity7.e0();
                                            break;
                                        case 10:
                                            k8.O(mainActivity3, true, true);
                                            break;
                                        case 11:
                                            k8.O(mainActivity3, false, true);
                                            break;
                                        case 12:
                                            k8.Q(mainActivity3, false);
                                            break;
                                        case 13:
                                            k8.Q(mainActivity3, true);
                                            break;
                                        case 14:
                                            MainActivity mainActivity8 = mainActivity3;
                                            new i0(mainActivity8, mainActivity8.z(), si.a("split_left", new Object[0]), false, new lg(mainActivity8, 7)).show();
                                            break;
                                        case 15:
                                            k8.C(mainActivity3);
                                            break;
                                        case 16:
                                            MainActivity mainActivity9 = mainActivity3;
                                            mainActivity9.u = "dock";
                                            mainActivity9.s = null;
                                            mainActivity9.t = 0;
                                            mainActivity9.e0();
                                            break;
                                        case 17:
                                            k8.g0(mainActivity3, 1);
                                            break;
                                        case 18:
                                            MainActivity mainActivity10 = mainActivity3;
                                            md mdVar = md.a;
                                            List list5 = md.d;
                                            if (list5 == null) {
                                                mdVar.c(mainActivity10);
                                                list5 = u4.a;
                                            }
                                            List list6 = list5;
                                            if (list6.isEmpty()) {
                                                mainActivity10.v0(si.a("radio_city_loading", new Object[0]));
                                            } else {
                                                Map mapJ0 = hb.j0(new yc("RU", si.a("country_ru", new Object[0])), new yc("BY", si.a("country_by", new Object[0])), new yc("KZ", si.a("country_kz", new Object[0])));
                                                i4 i4Var = new i4(mainActivity10, mainActivity10.z());
                                                ViewGroup viewGroupL = mainActivity10.l();
                                                EditText editText = new EditText(mainActivity10);
                                                editText.setHint(si.a("radio_city_search", new Object[0]));
                                                editText.setTextSize(20.0f);
                                                editText.setTextColor(mainActivity10.o);
                                                editText.setHintTextColor(mainActivity10.p);
                                                editText.setSingleLine(true);
                                                editText.setBackground(mainActivity10.m0(mainActivity10.q, 14));
                                                editText.setPadding(mainActivity10.r(16), 0, mainActivity10.r(16), 0);
                                                viewGroupL.addView(editText, new LinearLayout.LayoutParams(-1, mainActivity10.r(60)));
                                                LinearLayout linearLayoutL = mainActivity10.l();
                                                ScrollView scrollView2 = new ScrollView(mainActivity10);
                                                scrollView2.addView(linearLayoutL);
                                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, mainActivity10.r(420));
                                                layoutParams.topMargin = mainActivity10.r(10);
                                                viewGroupL.addView(scrollView2, layoutParams);
                                                Dialog dialogF = i4Var.f(si.a("radio_city", new Object[0]), si.a("radio_city_hint", new Object[0]), viewGroupL);
                                                editText.addTextChangedListener(new f0(linearLayoutL, i4Var, mainActivity10, list6, dialogF, mapJ0, 1));
                                                k8.k0(linearLayoutL, i4Var, mainActivity10, list6, dialogF, mapJ0, "");
                                                dialogF.show();
                                            }
                                            break;
                                        case 19:
                                            mainActivity3.f0();
                                            break;
                                        case 20:
                                            MainActivity mainActivity11 = mainActivity3;
                                            i4 i4Var2 = new i4(mainActivity11, mainActivity11.z());
                                            TextView textView = new TextView(mainActivity11);
                                            gc gcVarY = mainActivity11.y();
                                            if (gcVarY.n) {
                                                ArrayList arrayList2 = gcVarY.h;
                                                ArrayList arrayList3 = new ArrayList(k3.n0(arrayList2));
                                                int size2 = arrayList2.size();
                                                int i27 = 0;
                                                while (i27 < size2) {
                                                    Object obj6 = arrayList2.get(i27);
                                                    i27++;
                                                    arrayList3.add((MediaController) ((yc) obj6).a);
                                                }
                                                Object obj7 = MusicNotificationListener.a;
                                                ArrayList arrayListY = k8.Y();
                                                if (arrayList3.isEmpty() && arrayListY.isEmpty()) {
                                                    strY0 = si.a("no_media_sessions_start_radio_or_bluetooth", new Object[0]);
                                                } else {
                                                    List listI1 = j3.i0(i3.y0(arrayList3, "\n\n", new a(6, gcVarY), 30), i3.y0(arrayListY, "\n", new w(18), 30));
                                                    ArrayList arrayList4 = new ArrayList();
                                                    for (Object obj8 : listI1) {
                                                        if (!th.A((String) obj8)) {
                                                            arrayList4.add(obj8);
                                                        }
                                                    }
                                                    strY0 = i3.y0(arrayList4, "\n\n", null, 62);
                                                }
                                            } else {
                                                strY0 = si.a("no_music_access_enable_ReferencePanel_music_information", new Object[0]);
                                            }
                                            textView.setText(strY0);
                                            textView.setTextSize(17.0f);
                                            int i28 = l8.a;
                                            textView.setTextColor(l8.j(mainActivity11.z()));
                                            textView.setTextIsSelectable(true);
                                            textView.setLineSpacing(0.0f, 1.2f);
                                            String strA9 = si.a("music_diagnostics", new Object[0]);
                                            String strA10 = si.a("source_currently_shown_by_the_widget", new Object[0]);
                                            ScrollView scrollView3 = new ScrollView(mainActivity11);
                                            scrollView3.addView(textView);
                                            i4Var2.f(strA9, strA10, scrollView3).show();
                                            break;
                                        case 21:
                                            k8.m0(mainActivity3);
                                            break;
                                        case 22:
                                            mainActivity3.a0(0);
                                            break;
                                        case 23:
                                            MainActivity mainActivity12 = mainActivity3;
                                            ComponentName componentNameM = mainActivity12.m(0);
                                            if (componentNameM == null) {
                                                k8.g0(mainActivity12, 0);
                                            } else {
                                                mainActivity12.Q(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", componentNameM.getPackageName(), null)));
                                            }
                                            break;
                                        case 24:
                                            MainActivity mainActivity13 = mainActivity3;
                                            ArrayList arrayList5 = mainActivity13.w;
                                            int size3 = arrayList5.size();
                                            int i29 = 0;
                                            while (i29 < size3) {
                                                Object obj9 = arrayList5.get(i29);
                                                i29++;
                                                ((b) obj9).c();
                                            }
                                            arrayList5.clear();
                                            mainActivity13.c = false;
                                            mainActivity13.e0();
                                            break;
                                        case 25:
                                            MainActivity mainActivity14 = mainActivity3;
                                            k8.g0(mainActivity14, Integer.valueOf(mainActivity14.B0));
                                            break;
                                        case 26:
                                            MainActivity mainActivity15 = mainActivity3;
                                            mainActivity15.a0(mainActivity15.B0);
                                            break;
                                        case 27:
                                            k8.g0(mainActivity3, 0);
                                            break;
                                        case 28:
                                            MainActivity mainActivity16 = mainActivity3;
                                            if (!mainActivity16.h) {
                                                List listH = uh.h();
                                                ArrayList arrayList6 = new ArrayList(k3.n0(listH));
                                                Iterator it3 = listH.iterator();
                                                while (it3.hasNext()) {
                                                    arrayList6.add(((zi) it3.next()).b);
                                                }
                                                List listJ0 = i3.J0(i3.K0(arrayList6));
                                                c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity16).setTitle(si.a("vehicle_brand", new Object[0])).setItems((CharSequence[]) listJ0.toArray(new String[0]), new tb(mainActivity16, listJ0)), null, mainActivity16);
                                            }
                                            break;
                                        default:
                                            MainActivity mainActivity17 = mainActivity3;
                                            boolean z4 = mainActivity17.h;
                                            if (!z4 && !z4) {
                                                try {
                                                    Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                                                    intent.setType("image/*");
                                                    intent.addCategory("android.intent.category.OPENABLE");
                                                    intent.addFlags(1);
                                                    mainActivity17.startActivityForResult(intent, 41);
                                                } catch (ActivityNotFoundException unused) {
                                                    mainActivity17.v0(si.a("no_file_picker_app_on_this_head", new Object[0]));
                                                }
                                            }
                                            break;
                                    }
                                    return ti.a;
                                }
                            }, 8);
                            LinearLayout linearLayoutR4 = c9Var.r(si.a("play_music_after_head_unit_startup", new Object[0]), si.a("the_selected_player_starts_playback_automatically", new Object[0]), mainActivity3.B().getBoolean("music_autoplay", false), new ya(mainActivity3, 23));
                            LinearLayout linearLayoutR5 = c9Var.r(si.a("radio_autoplay", new Object[0]), si.a("radio_autoplay_hint", new Object[0]), mainActivity3.B().getBoolean("radio_autoplay", false), new ya(mainActivity3, i25));
                            LinearLayout linearLayoutR6 = c9Var.r(si.a("call_pause_music", new Object[0]), si.a("call_pause_music_hint", new Object[0]), mainActivity3.getSharedPreferences("panel", 0).getBoolean("call_pause_music", true), new ya(mainActivity3, 25));
                            LinearLayout linearLayoutR7 = c9Var.r(si.a("cover_swipe", new Object[0]), si.a("cover_swipe_hint", new Object[0]), mainActivity3.getSharedPreferences("panel", 0).getBoolean("music_cover_swipe", true), new ya(mainActivity3, 26));
                            LinearLayout linearLayoutR8 = c9Var.r(si.a("radio_in_widget", new Object[0]), si.a("radio_in_widget_hint", new Object[0]), mainActivity3.y().q(), new ya(mainActivity3, 27));
                            String strA9 = si.a("radio_city", new Object[0]);
                            String strA10 = si.a("radio_city_hint", new Object[0]);
                            md mdVar = md.a;
                            ld ldVarA = md.a(mainActivity3);
                            final int i26 = 18;
                            LinearLayout linearLayoutO5 = c9.o(c9Var, strA9, strA10, ldVarA != null ? ldVarA.a() : si.a("radio_city_auto", new Object[0]), new y5() { // from class: jg
                                @Override // defpackage.y5
                                public final Object a() {
                                    String strY0;
                                    switch (i26) {
                                        case 0:
                                            k8.d(mainActivity3);
                                            break;
                                        case 1:
                                            k8.c(mainActivity3, null);
                                            break;
                                        case 2:
                                            MainActivity mainActivity4 = mainActivity3;
                                            ArrayList arrayListC = j0.c();
                                            AlertDialog.Builder title = new AlertDialog.Builder(mainActivity4).setTitle(si.a("app_language_choose", new Object[0]));
                                            ArrayList arrayList = new ArrayList(k3.n0(arrayListC));
                                            int size = arrayListC.size();
                                            int i27 = 0;
                                            while (i27 < size) {
                                                Object obj4 = arrayListC.get(i27);
                                                i27++;
                                                Object obj5 = (String) obj4;
                                                List list2 = j0.a;
                                                obj5.getClass();
                                                List list3 = j0.a;
                                                int iIndexOf = si.a.indexOf(obj5);
                                                if (iIndexOf >= 0 && iIndexOf < list3.size()) {
                                                    obj5 = list3.get(iIndexOf);
                                                }
                                                arrayList.add((String) obj5);
                                            }
                                            CharSequence[] charSequenceArr = (CharSequence[]) arrayList.toArray(new String[0]);
                                            List list4 = si.a;
                                            AlertDialog.Builder negativeButton = title.setSingleChoiceItems(charSequenceArr, arrayListC.indexOf(si.b.a), new dg(arrayListC, mainActivity4)).setNegativeButton(si.a("cancel", new Object[0]), (DialogInterface.OnClickListener) null);
                                            negativeButton.getClass();
                                            k8.w0(negativeButton);
                                            break;
                                        case 3:
                                            MainActivity mainActivity5 = mainActivity3;
                                            mainActivity5.c = false;
                                            mainActivity5.f = true;
                                            mainActivity5.e0();
                                            mainActivity5.X(new j(14));
                                            break;
                                        case 4:
                                            k8.v0("elastre", mainActivity3);
                                            break;
                                        case 5:
                                            k8.v0("manrope", mainActivity3);
                                            break;
                                        case 6:
                                            k8.v0("custom", mainActivity3);
                                            break;
                                        case 7:
                                            k8.K(mainActivity3, true);
                                            break;
                                        case 8:
                                            MainActivity mainActivity6 = mainActivity3;
                                            x3.d(mainActivity6).delete();
                                            SharedPreferences sharedPreferences = mainActivity6.getSharedPreferences("panel", 0);
                                            SharedPreferences.Editor editorRemove = sharedPreferences.edit().remove("font_custom_name").remove("font_custom_kk").remove("font_custom_wght");
                                            if (k8.e(sharedPreferences.getString("font_choice", ""), "custom")) {
                                                editorRemove.remove("font_choice");
                                            }
                                            editorRemove.apply();
                                            z9.d(mainActivity6);
                                            z9.a = true;
                                            mainActivity6.e0();
                                            break;
                                        case 9:
                                            MainActivity mainActivity7 = mainActivity3;
                                            i7.g(mainActivity7, "");
                                            mainActivity7.e0();
                                            break;
                                        case 10:
                                            k8.O(mainActivity3, true, true);
                                            break;
                                        case 11:
                                            k8.O(mainActivity3, false, true);
                                            break;
                                        case 12:
                                            k8.Q(mainActivity3, false);
                                            break;
                                        case 13:
                                            k8.Q(mainActivity3, true);
                                            break;
                                        case 14:
                                            MainActivity mainActivity8 = mainActivity3;
                                            new i0(mainActivity8, mainActivity8.z(), si.a("split_left", new Object[0]), false, new lg(mainActivity8, 7)).show();
                                            break;
                                        case 15:
                                            k8.C(mainActivity3);
                                            break;
                                        case 16:
                                            MainActivity mainActivity9 = mainActivity3;
                                            mainActivity9.u = "dock";
                                            mainActivity9.s = null;
                                            mainActivity9.t = 0;
                                            mainActivity9.e0();
                                            break;
                                        case 17:
                                            k8.g0(mainActivity3, 1);
                                            break;
                                        case 18:
                                            MainActivity mainActivity10 = mainActivity3;
                                            md mdVar2 = md.a;
                                            List list5 = md.d;
                                            if (list5 == null) {
                                                mdVar2.c(mainActivity10);
                                                list5 = u4.a;
                                            }
                                            List list6 = list5;
                                            if (list6.isEmpty()) {
                                                mainActivity10.v0(si.a("radio_city_loading", new Object[0]));
                                            } else {
                                                Map mapJ0 = hb.j0(new yc("RU", si.a("country_ru", new Object[0])), new yc("BY", si.a("country_by", new Object[0])), new yc("KZ", si.a("country_kz", new Object[0])));
                                                i4 i4Var = new i4(mainActivity10, mainActivity10.z());
                                                ViewGroup viewGroupL = mainActivity10.l();
                                                EditText editText = new EditText(mainActivity10);
                                                editText.setHint(si.a("radio_city_search", new Object[0]));
                                                editText.setTextSize(20.0f);
                                                editText.setTextColor(mainActivity10.o);
                                                editText.setHintTextColor(mainActivity10.p);
                                                editText.setSingleLine(true);
                                                editText.setBackground(mainActivity10.m0(mainActivity10.q, 14));
                                                editText.setPadding(mainActivity10.r(16), 0, mainActivity10.r(16), 0);
                                                viewGroupL.addView(editText, new LinearLayout.LayoutParams(-1, mainActivity10.r(60)));
                                                LinearLayout linearLayoutL = mainActivity10.l();
                                                ScrollView scrollView2 = new ScrollView(mainActivity10);
                                                scrollView2.addView(linearLayoutL);
                                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, mainActivity10.r(420));
                                                layoutParams.topMargin = mainActivity10.r(10);
                                                viewGroupL.addView(scrollView2, layoutParams);
                                                Dialog dialogF = i4Var.f(si.a("radio_city", new Object[0]), si.a("radio_city_hint", new Object[0]), viewGroupL);
                                                editText.addTextChangedListener(new f0(linearLayoutL, i4Var, mainActivity10, list6, dialogF, mapJ0, 1));
                                                k8.k0(linearLayoutL, i4Var, mainActivity10, list6, dialogF, mapJ0, "");
                                                dialogF.show();
                                            }
                                            break;
                                        case 19:
                                            mainActivity3.f0();
                                            break;
                                        case 20:
                                            MainActivity mainActivity11 = mainActivity3;
                                            i4 i4Var2 = new i4(mainActivity11, mainActivity11.z());
                                            TextView textView = new TextView(mainActivity11);
                                            gc gcVarY = mainActivity11.y();
                                            if (gcVarY.n) {
                                                ArrayList arrayList2 = gcVarY.h;
                                                ArrayList arrayList3 = new ArrayList(k3.n0(arrayList2));
                                                int size2 = arrayList2.size();
                                                int i28 = 0;
                                                while (i28 < size2) {
                                                    Object obj6 = arrayList2.get(i28);
                                                    i28++;
                                                    arrayList3.add((MediaController) ((yc) obj6).a);
                                                }
                                                Object obj7 = MusicNotificationListener.a;
                                                ArrayList arrayListY = k8.Y();
                                                if (arrayList3.isEmpty() && arrayListY.isEmpty()) {
                                                    strY0 = si.a("no_media_sessions_start_radio_or_bluetooth", new Object[0]);
                                                } else {
                                                    List listI1 = j3.i0(i3.y0(arrayList3, "\n\n", new a(6, gcVarY), 30), i3.y0(arrayListY, "\n", new w(18), 30));
                                                    ArrayList arrayList4 = new ArrayList();
                                                    for (Object obj8 : listI1) {
                                                        if (!th.A((String) obj8)) {
                                                            arrayList4.add(obj8);
                                                        }
                                                    }
                                                    strY0 = i3.y0(arrayList4, "\n\n", null, 62);
                                                }
                                            } else {
                                                strY0 = si.a("no_music_access_enable_ReferencePanel_music_information", new Object[0]);
                                            }
                                            textView.setText(strY0);
                                            textView.setTextSize(17.0f);
                                            int i29 = l8.a;
                                            textView.setTextColor(l8.j(mainActivity11.z()));
                                            textView.setTextIsSelectable(true);
                                            textView.setLineSpacing(0.0f, 1.2f);
                                            String strA11 = si.a("music_diagnostics", new Object[0]);
                                            String strA12 = si.a("source_currently_shown_by_the_widget", new Object[0]);
                                            ScrollView scrollView3 = new ScrollView(mainActivity11);
                                            scrollView3.addView(textView);
                                            i4Var2.f(strA11, strA12, scrollView3).show();
                                            break;
                                        case 21:
                                            k8.m0(mainActivity3);
                                            break;
                                        case 22:
                                            mainActivity3.a0(0);
                                            break;
                                        case 23:
                                            MainActivity mainActivity12 = mainActivity3;
                                            ComponentName componentNameM = mainActivity12.m(0);
                                            if (componentNameM == null) {
                                                k8.g0(mainActivity12, 0);
                                            } else {
                                                mainActivity12.Q(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", componentNameM.getPackageName(), null)));
                                            }
                                            break;
                                        case 24:
                                            MainActivity mainActivity13 = mainActivity3;
                                            ArrayList arrayList5 = mainActivity13.w;
                                            int size3 = arrayList5.size();
                                            int i210 = 0;
                                            while (i210 < size3) {
                                                Object obj9 = arrayList5.get(i210);
                                                i210++;
                                                ((b) obj9).c();
                                            }
                                            arrayList5.clear();
                                            mainActivity13.c = false;
                                            mainActivity13.e0();
                                            break;
                                        case 25:
                                            MainActivity mainActivity14 = mainActivity3;
                                            k8.g0(mainActivity14, Integer.valueOf(mainActivity14.B0));
                                            break;
                                        case 26:
                                            MainActivity mainActivity15 = mainActivity3;
                                            mainActivity15.a0(mainActivity15.B0);
                                            break;
                                        case 27:
                                            k8.g0(mainActivity3, 0);
                                            break;
                                        case 28:
                                            MainActivity mainActivity16 = mainActivity3;
                                            if (!mainActivity16.h) {
                                                List listH = uh.h();
                                                ArrayList arrayList6 = new ArrayList(k3.n0(listH));
                                                Iterator it3 = listH.iterator();
                                                while (it3.hasNext()) {
                                                    arrayList6.add(((zi) it3.next()).b);
                                                }
                                                List listJ0 = i3.J0(i3.K0(arrayList6));
                                                c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity16).setTitle(si.a("vehicle_brand", new Object[0])).setItems((CharSequence[]) listJ0.toArray(new String[0]), new tb(mainActivity16, listJ0)), null, mainActivity16);
                                            }
                                            break;
                                        default:
                                            MainActivity mainActivity17 = mainActivity3;
                                            boolean z4 = mainActivity17.h;
                                            if (!z4 && !z4) {
                                                try {
                                                    Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                                                    intent.setType("image/*");
                                                    intent.addCategory("android.intent.category.OPENABLE");
                                                    intent.addFlags(1);
                                                    mainActivity17.startActivityForResult(intent, 41);
                                                } catch (ActivityNotFoundException unused) {
                                                    mainActivity17.v0(si.a("no_file_picker_app_on_this_head", new Object[0]));
                                                }
                                            }
                                            break;
                                    }
                                    return ti.a;
                                }
                            }, 8);
                            final int i27 = 19;
                            LinearLayout linearLayoutO6 = c9.o(c9Var, si.a("connect_music", new Object[0]), si.a("track_title_artwork_and_playback_controls", new Object[0]), null, new y5() { // from class: jg
                                @Override // defpackage.y5
                                public final Object a() {
                                    String strY0;
                                    switch (i27) {
                                        case 0:
                                            k8.d(mainActivity3);
                                            break;
                                        case 1:
                                            k8.c(mainActivity3, null);
                                            break;
                                        case 2:
                                            MainActivity mainActivity4 = mainActivity3;
                                            ArrayList arrayListC = j0.c();
                                            AlertDialog.Builder title = new AlertDialog.Builder(mainActivity4).setTitle(si.a("app_language_choose", new Object[0]));
                                            ArrayList arrayList = new ArrayList(k3.n0(arrayListC));
                                            int size = arrayListC.size();
                                            int i28 = 0;
                                            while (i28 < size) {
                                                Object obj4 = arrayListC.get(i28);
                                                i28++;
                                                Object obj5 = (String) obj4;
                                                List list2 = j0.a;
                                                obj5.getClass();
                                                List list3 = j0.a;
                                                int iIndexOf = si.a.indexOf(obj5);
                                                if (iIndexOf >= 0 && iIndexOf < list3.size()) {
                                                    obj5 = list3.get(iIndexOf);
                                                }
                                                arrayList.add((String) obj5);
                                            }
                                            CharSequence[] charSequenceArr = (CharSequence[]) arrayList.toArray(new String[0]);
                                            List list4 = si.a;
                                            AlertDialog.Builder negativeButton = title.setSingleChoiceItems(charSequenceArr, arrayListC.indexOf(si.b.a), new dg(arrayListC, mainActivity4)).setNegativeButton(si.a("cancel", new Object[0]), (DialogInterface.OnClickListener) null);
                                            negativeButton.getClass();
                                            k8.w0(negativeButton);
                                            break;
                                        case 3:
                                            MainActivity mainActivity5 = mainActivity3;
                                            mainActivity5.c = false;
                                            mainActivity5.f = true;
                                            mainActivity5.e0();
                                            mainActivity5.X(new j(14));
                                            break;
                                        case 4:
                                            k8.v0("elastre", mainActivity3);
                                            break;
                                        case 5:
                                            k8.v0("manrope", mainActivity3);
                                            break;
                                        case 6:
                                            k8.v0("custom", mainActivity3);
                                            break;
                                        case 7:
                                            k8.K(mainActivity3, true);
                                            break;
                                        case 8:
                                            MainActivity mainActivity6 = mainActivity3;
                                            x3.d(mainActivity6).delete();
                                            SharedPreferences sharedPreferences = mainActivity6.getSharedPreferences("panel", 0);
                                            SharedPreferences.Editor editorRemove = sharedPreferences.edit().remove("font_custom_name").remove("font_custom_kk").remove("font_custom_wght");
                                            if (k8.e(sharedPreferences.getString("font_choice", ""), "custom")) {
                                                editorRemove.remove("font_choice");
                                            }
                                            editorRemove.apply();
                                            z9.d(mainActivity6);
                                            z9.a = true;
                                            mainActivity6.e0();
                                            break;
                                        case 9:
                                            MainActivity mainActivity7 = mainActivity3;
                                            i7.g(mainActivity7, "");
                                            mainActivity7.e0();
                                            break;
                                        case 10:
                                            k8.O(mainActivity3, true, true);
                                            break;
                                        case 11:
                                            k8.O(mainActivity3, false, true);
                                            break;
                                        case 12:
                                            k8.Q(mainActivity3, false);
                                            break;
                                        case 13:
                                            k8.Q(mainActivity3, true);
                                            break;
                                        case 14:
                                            MainActivity mainActivity8 = mainActivity3;
                                            new i0(mainActivity8, mainActivity8.z(), si.a("split_left", new Object[0]), false, new lg(mainActivity8, 7)).show();
                                            break;
                                        case 15:
                                            k8.C(mainActivity3);
                                            break;
                                        case 16:
                                            MainActivity mainActivity9 = mainActivity3;
                                            mainActivity9.u = "dock";
                                            mainActivity9.s = null;
                                            mainActivity9.t = 0;
                                            mainActivity9.e0();
                                            break;
                                        case 17:
                                            k8.g0(mainActivity3, 1);
                                            break;
                                        case 18:
                                            MainActivity mainActivity10 = mainActivity3;
                                            md mdVar2 = md.a;
                                            List list5 = md.d;
                                            if (list5 == null) {
                                                mdVar2.c(mainActivity10);
                                                list5 = u4.a;
                                            }
                                            List list6 = list5;
                                            if (list6.isEmpty()) {
                                                mainActivity10.v0(si.a("radio_city_loading", new Object[0]));
                                            } else {
                                                Map mapJ0 = hb.j0(new yc("RU", si.a("country_ru", new Object[0])), new yc("BY", si.a("country_by", new Object[0])), new yc("KZ", si.a("country_kz", new Object[0])));
                                                i4 i4Var = new i4(mainActivity10, mainActivity10.z());
                                                ViewGroup viewGroupL = mainActivity10.l();
                                                EditText editText = new EditText(mainActivity10);
                                                editText.setHint(si.a("radio_city_search", new Object[0]));
                                                editText.setTextSize(20.0f);
                                                editText.setTextColor(mainActivity10.o);
                                                editText.setHintTextColor(mainActivity10.p);
                                                editText.setSingleLine(true);
                                                editText.setBackground(mainActivity10.m0(mainActivity10.q, 14));
                                                editText.setPadding(mainActivity10.r(16), 0, mainActivity10.r(16), 0);
                                                viewGroupL.addView(editText, new LinearLayout.LayoutParams(-1, mainActivity10.r(60)));
                                                LinearLayout linearLayoutL = mainActivity10.l();
                                                ScrollView scrollView2 = new ScrollView(mainActivity10);
                                                scrollView2.addView(linearLayoutL);
                                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, mainActivity10.r(420));
                                                layoutParams.topMargin = mainActivity10.r(10);
                                                viewGroupL.addView(scrollView2, layoutParams);
                                                Dialog dialogF = i4Var.f(si.a("radio_city", new Object[0]), si.a("radio_city_hint", new Object[0]), viewGroupL);
                                                editText.addTextChangedListener(new f0(linearLayoutL, i4Var, mainActivity10, list6, dialogF, mapJ0, 1));
                                                k8.k0(linearLayoutL, i4Var, mainActivity10, list6, dialogF, mapJ0, "");
                                                dialogF.show();
                                            }
                                            break;
                                        case 19:
                                            mainActivity3.f0();
                                            break;
                                        case 20:
                                            MainActivity mainActivity11 = mainActivity3;
                                            i4 i4Var2 = new i4(mainActivity11, mainActivity11.z());
                                            TextView textView = new TextView(mainActivity11);
                                            gc gcVarY = mainActivity11.y();
                                            if (gcVarY.n) {
                                                ArrayList arrayList2 = gcVarY.h;
                                                ArrayList arrayList3 = new ArrayList(k3.n0(arrayList2));
                                                int size2 = arrayList2.size();
                                                int i29 = 0;
                                                while (i29 < size2) {
                                                    Object obj6 = arrayList2.get(i29);
                                                    i29++;
                                                    arrayList3.add((MediaController) ((yc) obj6).a);
                                                }
                                                Object obj7 = MusicNotificationListener.a;
                                                ArrayList arrayListY = k8.Y();
                                                if (arrayList3.isEmpty() && arrayListY.isEmpty()) {
                                                    strY0 = si.a("no_media_sessions_start_radio_or_bluetooth", new Object[0]);
                                                } else {
                                                    List listI1 = j3.i0(i3.y0(arrayList3, "\n\n", new a(6, gcVarY), 30), i3.y0(arrayListY, "\n", new w(18), 30));
                                                    ArrayList arrayList4 = new ArrayList();
                                                    for (Object obj8 : listI1) {
                                                        if (!th.A((String) obj8)) {
                                                            arrayList4.add(obj8);
                                                        }
                                                    }
                                                    strY0 = i3.y0(arrayList4, "\n\n", null, 62);
                                                }
                                            } else {
                                                strY0 = si.a("no_music_access_enable_ReferencePanel_music_information", new Object[0]);
                                            }
                                            textView.setText(strY0);
                                            textView.setTextSize(17.0f);
                                            int i210 = l8.a;
                                            textView.setTextColor(l8.j(mainActivity11.z()));
                                            textView.setTextIsSelectable(true);
                                            textView.setLineSpacing(0.0f, 1.2f);
                                            String strA11 = si.a("music_diagnostics", new Object[0]);
                                            String strA12 = si.a("source_currently_shown_by_the_widget", new Object[0]);
                                            ScrollView scrollView3 = new ScrollView(mainActivity11);
                                            scrollView3.addView(textView);
                                            i4Var2.f(strA11, strA12, scrollView3).show();
                                            break;
                                        case 21:
                                            k8.m0(mainActivity3);
                                            break;
                                        case 22:
                                            mainActivity3.a0(0);
                                            break;
                                        case 23:
                                            MainActivity mainActivity12 = mainActivity3;
                                            ComponentName componentNameM = mainActivity12.m(0);
                                            if (componentNameM == null) {
                                                k8.g0(mainActivity12, 0);
                                            } else {
                                                mainActivity12.Q(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", componentNameM.getPackageName(), null)));
                                            }
                                            break;
                                        case 24:
                                            MainActivity mainActivity13 = mainActivity3;
                                            ArrayList arrayList5 = mainActivity13.w;
                                            int size3 = arrayList5.size();
                                            int i211 = 0;
                                            while (i211 < size3) {
                                                Object obj9 = arrayList5.get(i211);
                                                i211++;
                                                ((b) obj9).c();
                                            }
                                            arrayList5.clear();
                                            mainActivity13.c = false;
                                            mainActivity13.e0();
                                            break;
                                        case 25:
                                            MainActivity mainActivity14 = mainActivity3;
                                            k8.g0(mainActivity14, Integer.valueOf(mainActivity14.B0));
                                            break;
                                        case 26:
                                            MainActivity mainActivity15 = mainActivity3;
                                            mainActivity15.a0(mainActivity15.B0);
                                            break;
                                        case 27:
                                            k8.g0(mainActivity3, 0);
                                            break;
                                        case 28:
                                            MainActivity mainActivity16 = mainActivity3;
                                            if (!mainActivity16.h) {
                                                List listH = uh.h();
                                                ArrayList arrayList6 = new ArrayList(k3.n0(listH));
                                                Iterator it3 = listH.iterator();
                                                while (it3.hasNext()) {
                                                    arrayList6.add(((zi) it3.next()).b);
                                                }
                                                List listJ0 = i3.J0(i3.K0(arrayList6));
                                                c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity16).setTitle(si.a("vehicle_brand", new Object[0])).setItems((CharSequence[]) listJ0.toArray(new String[0]), new tb(mainActivity16, listJ0)), null, mainActivity16);
                                            }
                                            break;
                                        default:
                                            MainActivity mainActivity17 = mainActivity3;
                                            boolean z4 = mainActivity17.h;
                                            if (!z4 && !z4) {
                                                try {
                                                    Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                                                    intent.setType("image/*");
                                                    intent.addCategory("android.intent.category.OPENABLE");
                                                    intent.addFlags(1);
                                                    mainActivity17.startActivityForResult(intent, 41);
                                                } catch (ActivityNotFoundException unused) {
                                                    mainActivity17.v0(si.a("no_file_picker_app_on_this_head", new Object[0]));
                                                }
                                            }
                                            break;
                                    }
                                    return ti.a;
                                }
                            }, 12);
                            final int i28 = 20;
                            linearLayout.addView(c9Var.d(linearLayoutO4, linearLayoutR4, linearLayoutR5, linearLayoutR6, linearLayoutR7, linearLayoutR8, linearLayoutO5, linearLayoutO6, c9.o(c9Var, si.a("music_diagnostics", new Object[0]), si.a("sources_available_to_the_widget_start_radio", new Object[0]), null, new y5() { // from class: jg
                                @Override // defpackage.y5
                                public final Object a() {
                                    String strY0;
                                    switch (i28) {
                                        case 0:
                                            k8.d(mainActivity3);
                                            break;
                                        case 1:
                                            k8.c(mainActivity3, null);
                                            break;
                                        case 2:
                                            MainActivity mainActivity4 = mainActivity3;
                                            ArrayList arrayListC = j0.c();
                                            AlertDialog.Builder title = new AlertDialog.Builder(mainActivity4).setTitle(si.a("app_language_choose", new Object[0]));
                                            ArrayList arrayList = new ArrayList(k3.n0(arrayListC));
                                            int size = arrayListC.size();
                                            int i29 = 0;
                                            while (i29 < size) {
                                                Object obj4 = arrayListC.get(i29);
                                                i29++;
                                                Object obj5 = (String) obj4;
                                                List list2 = j0.a;
                                                obj5.getClass();
                                                List list3 = j0.a;
                                                int iIndexOf = si.a.indexOf(obj5);
                                                if (iIndexOf >= 0 && iIndexOf < list3.size()) {
                                                    obj5 = list3.get(iIndexOf);
                                                }
                                                arrayList.add((String) obj5);
                                            }
                                            CharSequence[] charSequenceArr = (CharSequence[]) arrayList.toArray(new String[0]);
                                            List list4 = si.a;
                                            AlertDialog.Builder negativeButton = title.setSingleChoiceItems(charSequenceArr, arrayListC.indexOf(si.b.a), new dg(arrayListC, mainActivity4)).setNegativeButton(si.a("cancel", new Object[0]), (DialogInterface.OnClickListener) null);
                                            negativeButton.getClass();
                                            k8.w0(negativeButton);
                                            break;
                                        case 3:
                                            MainActivity mainActivity5 = mainActivity3;
                                            mainActivity5.c = false;
                                            mainActivity5.f = true;
                                            mainActivity5.e0();
                                            mainActivity5.X(new j(14));
                                            break;
                                        case 4:
                                            k8.v0("elastre", mainActivity3);
                                            break;
                                        case 5:
                                            k8.v0("manrope", mainActivity3);
                                            break;
                                        case 6:
                                            k8.v0("custom", mainActivity3);
                                            break;
                                        case 7:
                                            k8.K(mainActivity3, true);
                                            break;
                                        case 8:
                                            MainActivity mainActivity6 = mainActivity3;
                                            x3.d(mainActivity6).delete();
                                            SharedPreferences sharedPreferences = mainActivity6.getSharedPreferences("panel", 0);
                                            SharedPreferences.Editor editorRemove = sharedPreferences.edit().remove("font_custom_name").remove("font_custom_kk").remove("font_custom_wght");
                                            if (k8.e(sharedPreferences.getString("font_choice", ""), "custom")) {
                                                editorRemove.remove("font_choice");
                                            }
                                            editorRemove.apply();
                                            z9.d(mainActivity6);
                                            z9.a = true;
                                            mainActivity6.e0();
                                            break;
                                        case 9:
                                            MainActivity mainActivity7 = mainActivity3;
                                            i7.g(mainActivity7, "");
                                            mainActivity7.e0();
                                            break;
                                        case 10:
                                            k8.O(mainActivity3, true, true);
                                            break;
                                        case 11:
                                            k8.O(mainActivity3, false, true);
                                            break;
                                        case 12:
                                            k8.Q(mainActivity3, false);
                                            break;
                                        case 13:
                                            k8.Q(mainActivity3, true);
                                            break;
                                        case 14:
                                            MainActivity mainActivity8 = mainActivity3;
                                            new i0(mainActivity8, mainActivity8.z(), si.a("split_left", new Object[0]), false, new lg(mainActivity8, 7)).show();
                                            break;
                                        case 15:
                                            k8.C(mainActivity3);
                                            break;
                                        case 16:
                                            MainActivity mainActivity9 = mainActivity3;
                                            mainActivity9.u = "dock";
                                            mainActivity9.s = null;
                                            mainActivity9.t = 0;
                                            mainActivity9.e0();
                                            break;
                                        case 17:
                                            k8.g0(mainActivity3, 1);
                                            break;
                                        case 18:
                                            MainActivity mainActivity10 = mainActivity3;
                                            md mdVar2 = md.a;
                                            List list5 = md.d;
                                            if (list5 == null) {
                                                mdVar2.c(mainActivity10);
                                                list5 = u4.a;
                                            }
                                            List list6 = list5;
                                            if (list6.isEmpty()) {
                                                mainActivity10.v0(si.a("radio_city_loading", new Object[0]));
                                            } else {
                                                Map mapJ0 = hb.j0(new yc("RU", si.a("country_ru", new Object[0])), new yc("BY", si.a("country_by", new Object[0])), new yc("KZ", si.a("country_kz", new Object[0])));
                                                i4 i4Var = new i4(mainActivity10, mainActivity10.z());
                                                ViewGroup viewGroupL = mainActivity10.l();
                                                EditText editText = new EditText(mainActivity10);
                                                editText.setHint(si.a("radio_city_search", new Object[0]));
                                                editText.setTextSize(20.0f);
                                                editText.setTextColor(mainActivity10.o);
                                                editText.setHintTextColor(mainActivity10.p);
                                                editText.setSingleLine(true);
                                                editText.setBackground(mainActivity10.m0(mainActivity10.q, 14));
                                                editText.setPadding(mainActivity10.r(16), 0, mainActivity10.r(16), 0);
                                                viewGroupL.addView(editText, new LinearLayout.LayoutParams(-1, mainActivity10.r(60)));
                                                LinearLayout linearLayoutL = mainActivity10.l();
                                                ScrollView scrollView2 = new ScrollView(mainActivity10);
                                                scrollView2.addView(linearLayoutL);
                                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, mainActivity10.r(420));
                                                layoutParams.topMargin = mainActivity10.r(10);
                                                viewGroupL.addView(scrollView2, layoutParams);
                                                Dialog dialogF = i4Var.f(si.a("radio_city", new Object[0]), si.a("radio_city_hint", new Object[0]), viewGroupL);
                                                editText.addTextChangedListener(new f0(linearLayoutL, i4Var, mainActivity10, list6, dialogF, mapJ0, 1));
                                                k8.k0(linearLayoutL, i4Var, mainActivity10, list6, dialogF, mapJ0, "");
                                                dialogF.show();
                                            }
                                            break;
                                        case 19:
                                            mainActivity3.f0();
                                            break;
                                        case 20:
                                            MainActivity mainActivity11 = mainActivity3;
                                            i4 i4Var2 = new i4(mainActivity11, mainActivity11.z());
                                            TextView textView = new TextView(mainActivity11);
                                            gc gcVarY = mainActivity11.y();
                                            if (gcVarY.n) {
                                                ArrayList arrayList2 = gcVarY.h;
                                                ArrayList arrayList3 = new ArrayList(k3.n0(arrayList2));
                                                int size2 = arrayList2.size();
                                                int i210 = 0;
                                                while (i210 < size2) {
                                                    Object obj6 = arrayList2.get(i210);
                                                    i210++;
                                                    arrayList3.add((MediaController) ((yc) obj6).a);
                                                }
                                                Object obj7 = MusicNotificationListener.a;
                                                ArrayList arrayListY = k8.Y();
                                                if (arrayList3.isEmpty() && arrayListY.isEmpty()) {
                                                    strY0 = si.a("no_media_sessions_start_radio_or_bluetooth", new Object[0]);
                                                } else {
                                                    List listI1 = j3.i0(i3.y0(arrayList3, "\n\n", new a(6, gcVarY), 30), i3.y0(arrayListY, "\n", new w(18), 30));
                                                    ArrayList arrayList4 = new ArrayList();
                                                    for (Object obj8 : listI1) {
                                                        if (!th.A((String) obj8)) {
                                                            arrayList4.add(obj8);
                                                        }
                                                    }
                                                    strY0 = i3.y0(arrayList4, "\n\n", null, 62);
                                                }
                                            } else {
                                                strY0 = si.a("no_music_access_enable_ReferencePanel_music_information", new Object[0]);
                                            }
                                            textView.setText(strY0);
                                            textView.setTextSize(17.0f);
                                            int i211 = l8.a;
                                            textView.setTextColor(l8.j(mainActivity11.z()));
                                            textView.setTextIsSelectable(true);
                                            textView.setLineSpacing(0.0f, 1.2f);
                                            String strA11 = si.a("music_diagnostics", new Object[0]);
                                            String strA12 = si.a("source_currently_shown_by_the_widget", new Object[0]);
                                            ScrollView scrollView3 = new ScrollView(mainActivity11);
                                            scrollView3.addView(textView);
                                            i4Var2.f(strA11, strA12, scrollView3).show();
                                            break;
                                        case 21:
                                            k8.m0(mainActivity3);
                                            break;
                                        case 22:
                                            mainActivity3.a0(0);
                                            break;
                                        case 23:
                                            MainActivity mainActivity12 = mainActivity3;
                                            ComponentName componentNameM = mainActivity12.m(0);
                                            if (componentNameM == null) {
                                                k8.g0(mainActivity12, 0);
                                            } else {
                                                mainActivity12.Q(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", componentNameM.getPackageName(), null)));
                                            }
                                            break;
                                        case 24:
                                            MainActivity mainActivity13 = mainActivity3;
                                            ArrayList arrayList5 = mainActivity13.w;
                                            int size3 = arrayList5.size();
                                            int i212 = 0;
                                            while (i212 < size3) {
                                                Object obj9 = arrayList5.get(i212);
                                                i212++;
                                                ((b) obj9).c();
                                            }
                                            arrayList5.clear();
                                            mainActivity13.c = false;
                                            mainActivity13.e0();
                                            break;
                                        case 25:
                                            MainActivity mainActivity14 = mainActivity3;
                                            k8.g0(mainActivity14, Integer.valueOf(mainActivity14.B0));
                                            break;
                                        case 26:
                                            MainActivity mainActivity15 = mainActivity3;
                                            mainActivity15.a0(mainActivity15.B0);
                                            break;
                                        case 27:
                                            k8.g0(mainActivity3, 0);
                                            break;
                                        case 28:
                                            MainActivity mainActivity16 = mainActivity3;
                                            if (!mainActivity16.h) {
                                                List listH = uh.h();
                                                ArrayList arrayList6 = new ArrayList(k3.n0(listH));
                                                Iterator it3 = listH.iterator();
                                                while (it3.hasNext()) {
                                                    arrayList6.add(((zi) it3.next()).b);
                                                }
                                                List listJ0 = i3.J0(i3.K0(arrayList6));
                                                c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity16).setTitle(si.a("vehicle_brand", new Object[0])).setItems((CharSequence[]) listJ0.toArray(new String[0]), new tb(mainActivity16, listJ0)), null, mainActivity16);
                                            }
                                            break;
                                        default:
                                            MainActivity mainActivity17 = mainActivity3;
                                            boolean z4 = mainActivity17.h;
                                            if (!z4 && !z4) {
                                                try {
                                                    Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                                                    intent.setType("image/*");
                                                    intent.addCategory("android.intent.category.OPENABLE");
                                                    intent.addFlags(1);
                                                    mainActivity17.startActivityForResult(intent, 41);
                                                } catch (ActivityNotFoundException unused) {
                                                    mainActivity17.v0(si.a("no_file_picker_app_on_this_head", new Object[0]));
                                                }
                                            }
                                            break;
                                    }
                                    return ti.a;
                                }
                            }, 12)));
                            linearLayout.addView(c9Var.b(si.a("navigation_206", new Object[0])));
                            linearLayout.addView(c9Var.d(c9Var.r(si.a("navigation_embedded_title", new Object[0]), si.a("navigation_embedded_hint", new Object[0]), mainActivity3.B().getBoolean("navigation_embedded", true), new ya(mainActivity3, i23))));
                            if (mainActivity3.B().getBoolean("navigation_embedded", true)) {
                                final int i29 = 22;
                                final int i30 = 23;
                                linearLayout.addView(c9Var.d(c9.o(c9Var, si.a("open_navigation_full_screen", new Object[0]), null, null, new y5() { // from class: jg
                                    @Override // defpackage.y5
                                    public final Object a() {
                                        String strY0;
                                        switch (i29) {
                                            case 0:
                                                k8.d(mainActivity3);
                                                break;
                                            case 1:
                                                k8.c(mainActivity3, null);
                                                break;
                                            case 2:
                                                MainActivity mainActivity4 = mainActivity3;
                                                ArrayList arrayListC = j0.c();
                                                AlertDialog.Builder title = new AlertDialog.Builder(mainActivity4).setTitle(si.a("app_language_choose", new Object[0]));
                                                ArrayList arrayList = new ArrayList(k3.n0(arrayListC));
                                                int size = arrayListC.size();
                                                int i210 = 0;
                                                while (i210 < size) {
                                                    Object obj4 = arrayListC.get(i210);
                                                    i210++;
                                                    Object obj5 = (String) obj4;
                                                    List list2 = j0.a;
                                                    obj5.getClass();
                                                    List list3 = j0.a;
                                                    int iIndexOf = si.a.indexOf(obj5);
                                                    if (iIndexOf >= 0 && iIndexOf < list3.size()) {
                                                        obj5 = list3.get(iIndexOf);
                                                    }
                                                    arrayList.add((String) obj5);
                                                }
                                                CharSequence[] charSequenceArr = (CharSequence[]) arrayList.toArray(new String[0]);
                                                List list4 = si.a;
                                                AlertDialog.Builder negativeButton = title.setSingleChoiceItems(charSequenceArr, arrayListC.indexOf(si.b.a), new dg(arrayListC, mainActivity4)).setNegativeButton(si.a("cancel", new Object[0]), (DialogInterface.OnClickListener) null);
                                                negativeButton.getClass();
                                                k8.w0(negativeButton);
                                                break;
                                            case 3:
                                                MainActivity mainActivity5 = mainActivity3;
                                                mainActivity5.c = false;
                                                mainActivity5.f = true;
                                                mainActivity5.e0();
                                                mainActivity5.X(new j(14));
                                                break;
                                            case 4:
                                                k8.v0("elastre", mainActivity3);
                                                break;
                                            case 5:
                                                k8.v0("manrope", mainActivity3);
                                                break;
                                            case 6:
                                                k8.v0("custom", mainActivity3);
                                                break;
                                            case 7:
                                                k8.K(mainActivity3, true);
                                                break;
                                            case 8:
                                                MainActivity mainActivity6 = mainActivity3;
                                                x3.d(mainActivity6).delete();
                                                SharedPreferences sharedPreferences = mainActivity6.getSharedPreferences("panel", 0);
                                                SharedPreferences.Editor editorRemove = sharedPreferences.edit().remove("font_custom_name").remove("font_custom_kk").remove("font_custom_wght");
                                                if (k8.e(sharedPreferences.getString("font_choice", ""), "custom")) {
                                                    editorRemove.remove("font_choice");
                                                }
                                                editorRemove.apply();
                                                z9.d(mainActivity6);
                                                z9.a = true;
                                                mainActivity6.e0();
                                                break;
                                            case 9:
                                                MainActivity mainActivity7 = mainActivity3;
                                                i7.g(mainActivity7, "");
                                                mainActivity7.e0();
                                                break;
                                            case 10:
                                                k8.O(mainActivity3, true, true);
                                                break;
                                            case 11:
                                                k8.O(mainActivity3, false, true);
                                                break;
                                            case 12:
                                                k8.Q(mainActivity3, false);
                                                break;
                                            case 13:
                                                k8.Q(mainActivity3, true);
                                                break;
                                            case 14:
                                                MainActivity mainActivity8 = mainActivity3;
                                                new i0(mainActivity8, mainActivity8.z(), si.a("split_left", new Object[0]), false, new lg(mainActivity8, 7)).show();
                                                break;
                                            case 15:
                                                k8.C(mainActivity3);
                                                break;
                                            case 16:
                                                MainActivity mainActivity9 = mainActivity3;
                                                mainActivity9.u = "dock";
                                                mainActivity9.s = null;
                                                mainActivity9.t = 0;
                                                mainActivity9.e0();
                                                break;
                                            case 17:
                                                k8.g0(mainActivity3, 1);
                                                break;
                                            case 18:
                                                MainActivity mainActivity10 = mainActivity3;
                                                md mdVar2 = md.a;
                                                List list5 = md.d;
                                                if (list5 == null) {
                                                    mdVar2.c(mainActivity10);
                                                    list5 = u4.a;
                                                }
                                                List list6 = list5;
                                                if (list6.isEmpty()) {
                                                    mainActivity10.v0(si.a("radio_city_loading", new Object[0]));
                                                } else {
                                                    Map mapJ0 = hb.j0(new yc("RU", si.a("country_ru", new Object[0])), new yc("BY", si.a("country_by", new Object[0])), new yc("KZ", si.a("country_kz", new Object[0])));
                                                    i4 i4Var = new i4(mainActivity10, mainActivity10.z());
                                                    ViewGroup viewGroupL = mainActivity10.l();
                                                    EditText editText = new EditText(mainActivity10);
                                                    editText.setHint(si.a("radio_city_search", new Object[0]));
                                                    editText.setTextSize(20.0f);
                                                    editText.setTextColor(mainActivity10.o);
                                                    editText.setHintTextColor(mainActivity10.p);
                                                    editText.setSingleLine(true);
                                                    editText.setBackground(mainActivity10.m0(mainActivity10.q, 14));
                                                    editText.setPadding(mainActivity10.r(16), 0, mainActivity10.r(16), 0);
                                                    viewGroupL.addView(editText, new LinearLayout.LayoutParams(-1, mainActivity10.r(60)));
                                                    LinearLayout linearLayoutL = mainActivity10.l();
                                                    ScrollView scrollView2 = new ScrollView(mainActivity10);
                                                    scrollView2.addView(linearLayoutL);
                                                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, mainActivity10.r(420));
                                                    layoutParams.topMargin = mainActivity10.r(10);
                                                    viewGroupL.addView(scrollView2, layoutParams);
                                                    Dialog dialogF = i4Var.f(si.a("radio_city", new Object[0]), si.a("radio_city_hint", new Object[0]), viewGroupL);
                                                    editText.addTextChangedListener(new f0(linearLayoutL, i4Var, mainActivity10, list6, dialogF, mapJ0, 1));
                                                    k8.k0(linearLayoutL, i4Var, mainActivity10, list6, dialogF, mapJ0, "");
                                                    dialogF.show();
                                                }
                                                break;
                                            case 19:
                                                mainActivity3.f0();
                                                break;
                                            case 20:
                                                MainActivity mainActivity11 = mainActivity3;
                                                i4 i4Var2 = new i4(mainActivity11, mainActivity11.z());
                                                TextView textView = new TextView(mainActivity11);
                                                gc gcVarY = mainActivity11.y();
                                                if (gcVarY.n) {
                                                    ArrayList arrayList2 = gcVarY.h;
                                                    ArrayList arrayList3 = new ArrayList(k3.n0(arrayList2));
                                                    int size2 = arrayList2.size();
                                                    int i211 = 0;
                                                    while (i211 < size2) {
                                                        Object obj6 = arrayList2.get(i211);
                                                        i211++;
                                                        arrayList3.add((MediaController) ((yc) obj6).a);
                                                    }
                                                    Object obj7 = MusicNotificationListener.a;
                                                    ArrayList arrayListY = k8.Y();
                                                    if (arrayList3.isEmpty() && arrayListY.isEmpty()) {
                                                        strY0 = si.a("no_media_sessions_start_radio_or_bluetooth", new Object[0]);
                                                    } else {
                                                        List listI1 = j3.i0(i3.y0(arrayList3, "\n\n", new a(6, gcVarY), 30), i3.y0(arrayListY, "\n", new w(18), 30));
                                                        ArrayList arrayList4 = new ArrayList();
                                                        for (Object obj8 : listI1) {
                                                            if (!th.A((String) obj8)) {
                                                                arrayList4.add(obj8);
                                                            }
                                                        }
                                                        strY0 = i3.y0(arrayList4, "\n\n", null, 62);
                                                    }
                                                } else {
                                                    strY0 = si.a("no_music_access_enable_ReferencePanel_music_information", new Object[0]);
                                                }
                                                textView.setText(strY0);
                                                textView.setTextSize(17.0f);
                                                int i212 = l8.a;
                                                textView.setTextColor(l8.j(mainActivity11.z()));
                                                textView.setTextIsSelectable(true);
                                                textView.setLineSpacing(0.0f, 1.2f);
                                                String strA11 = si.a("music_diagnostics", new Object[0]);
                                                String strA12 = si.a("source_currently_shown_by_the_widget", new Object[0]);
                                                ScrollView scrollView3 = new ScrollView(mainActivity11);
                                                scrollView3.addView(textView);
                                                i4Var2.f(strA11, strA12, scrollView3).show();
                                                break;
                                            case 21:
                                                k8.m0(mainActivity3);
                                                break;
                                            case 22:
                                                mainActivity3.a0(0);
                                                break;
                                            case 23:
                                                MainActivity mainActivity12 = mainActivity3;
                                                ComponentName componentNameM = mainActivity12.m(0);
                                                if (componentNameM == null) {
                                                    k8.g0(mainActivity12, 0);
                                                } else {
                                                    mainActivity12.Q(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", componentNameM.getPackageName(), null)));
                                                }
                                                break;
                                            case 24:
                                                MainActivity mainActivity13 = mainActivity3;
                                                ArrayList arrayList5 = mainActivity13.w;
                                                int size3 = arrayList5.size();
                                                int i213 = 0;
                                                while (i213 < size3) {
                                                    Object obj9 = arrayList5.get(i213);
                                                    i213++;
                                                    ((b) obj9).c();
                                                }
                                                arrayList5.clear();
                                                mainActivity13.c = false;
                                                mainActivity13.e0();
                                                break;
                                            case 25:
                                                MainActivity mainActivity14 = mainActivity3;
                                                k8.g0(mainActivity14, Integer.valueOf(mainActivity14.B0));
                                                break;
                                            case 26:
                                                MainActivity mainActivity15 = mainActivity3;
                                                mainActivity15.a0(mainActivity15.B0);
                                                break;
                                            case 27:
                                                k8.g0(mainActivity3, 0);
                                                break;
                                            case 28:
                                                MainActivity mainActivity16 = mainActivity3;
                                                if (!mainActivity16.h) {
                                                    List listH = uh.h();
                                                    ArrayList arrayList6 = new ArrayList(k3.n0(listH));
                                                    Iterator it3 = listH.iterator();
                                                    while (it3.hasNext()) {
                                                        arrayList6.add(((zi) it3.next()).b);
                                                    }
                                                    List listJ0 = i3.J0(i3.K0(arrayList6));
                                                    c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity16).setTitle(si.a("vehicle_brand", new Object[0])).setItems((CharSequence[]) listJ0.toArray(new String[0]), new tb(mainActivity16, listJ0)), null, mainActivity16);
                                                }
                                                break;
                                            default:
                                                MainActivity mainActivity17 = mainActivity3;
                                                boolean z4 = mainActivity17.h;
                                                if (!z4 && !z4) {
                                                    try {
                                                        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                                                        intent.setType("image/*");
                                                        intent.addCategory("android.intent.category.OPENABLE");
                                                        intent.addFlags(1);
                                                        mainActivity17.startActivityForResult(intent, 41);
                                                    } catch (ActivityNotFoundException unused) {
                                                        mainActivity17.v0(si.a("no_file_picker_app_on_this_head", new Object[0]));
                                                    }
                                                }
                                                break;
                                        }
                                        return ti.a;
                                    }
                                }, 14), c9.o(c9Var, si.a("navigation_permissions", new Object[0]), si.a("for_alice_allow_yandex_to_use_the", new Object[0]), null, new y5() { // from class: jg
                                    @Override // defpackage.y5
                                    public final Object a() {
                                        String strY0;
                                        switch (i30) {
                                            case 0:
                                                k8.d(mainActivity3);
                                                break;
                                            case 1:
                                                k8.c(mainActivity3, null);
                                                break;
                                            case 2:
                                                MainActivity mainActivity4 = mainActivity3;
                                                ArrayList arrayListC = j0.c();
                                                AlertDialog.Builder title = new AlertDialog.Builder(mainActivity4).setTitle(si.a("app_language_choose", new Object[0]));
                                                ArrayList arrayList = new ArrayList(k3.n0(arrayListC));
                                                int size = arrayListC.size();
                                                int i210 = 0;
                                                while (i210 < size) {
                                                    Object obj4 = arrayListC.get(i210);
                                                    i210++;
                                                    Object obj5 = (String) obj4;
                                                    List list2 = j0.a;
                                                    obj5.getClass();
                                                    List list3 = j0.a;
                                                    int iIndexOf = si.a.indexOf(obj5);
                                                    if (iIndexOf >= 0 && iIndexOf < list3.size()) {
                                                        obj5 = list3.get(iIndexOf);
                                                    }
                                                    arrayList.add((String) obj5);
                                                }
                                                CharSequence[] charSequenceArr = (CharSequence[]) arrayList.toArray(new String[0]);
                                                List list4 = si.a;
                                                AlertDialog.Builder negativeButton = title.setSingleChoiceItems(charSequenceArr, arrayListC.indexOf(si.b.a), new dg(arrayListC, mainActivity4)).setNegativeButton(si.a("cancel", new Object[0]), (DialogInterface.OnClickListener) null);
                                                negativeButton.getClass();
                                                k8.w0(negativeButton);
                                                break;
                                            case 3:
                                                MainActivity mainActivity5 = mainActivity3;
                                                mainActivity5.c = false;
                                                mainActivity5.f = true;
                                                mainActivity5.e0();
                                                mainActivity5.X(new j(14));
                                                break;
                                            case 4:
                                                k8.v0("elastre", mainActivity3);
                                                break;
                                            case 5:
                                                k8.v0("manrope", mainActivity3);
                                                break;
                                            case 6:
                                                k8.v0("custom", mainActivity3);
                                                break;
                                            case 7:
                                                k8.K(mainActivity3, true);
                                                break;
                                            case 8:
                                                MainActivity mainActivity6 = mainActivity3;
                                                x3.d(mainActivity6).delete();
                                                SharedPreferences sharedPreferences = mainActivity6.getSharedPreferences("panel", 0);
                                                SharedPreferences.Editor editorRemove = sharedPreferences.edit().remove("font_custom_name").remove("font_custom_kk").remove("font_custom_wght");
                                                if (k8.e(sharedPreferences.getString("font_choice", ""), "custom")) {
                                                    editorRemove.remove("font_choice");
                                                }
                                                editorRemove.apply();
                                                z9.d(mainActivity6);
                                                z9.a = true;
                                                mainActivity6.e0();
                                                break;
                                            case 9:
                                                MainActivity mainActivity7 = mainActivity3;
                                                i7.g(mainActivity7, "");
                                                mainActivity7.e0();
                                                break;
                                            case 10:
                                                k8.O(mainActivity3, true, true);
                                                break;
                                            case 11:
                                                k8.O(mainActivity3, false, true);
                                                break;
                                            case 12:
                                                k8.Q(mainActivity3, false);
                                                break;
                                            case 13:
                                                k8.Q(mainActivity3, true);
                                                break;
                                            case 14:
                                                MainActivity mainActivity8 = mainActivity3;
                                                new i0(mainActivity8, mainActivity8.z(), si.a("split_left", new Object[0]), false, new lg(mainActivity8, 7)).show();
                                                break;
                                            case 15:
                                                k8.C(mainActivity3);
                                                break;
                                            case 16:
                                                MainActivity mainActivity9 = mainActivity3;
                                                mainActivity9.u = "dock";
                                                mainActivity9.s = null;
                                                mainActivity9.t = 0;
                                                mainActivity9.e0();
                                                break;
                                            case 17:
                                                k8.g0(mainActivity3, 1);
                                                break;
                                            case 18:
                                                MainActivity mainActivity10 = mainActivity3;
                                                md mdVar2 = md.a;
                                                List list5 = md.d;
                                                if (list5 == null) {
                                                    mdVar2.c(mainActivity10);
                                                    list5 = u4.a;
                                                }
                                                List list6 = list5;
                                                if (list6.isEmpty()) {
                                                    mainActivity10.v0(si.a("radio_city_loading", new Object[0]));
                                                } else {
                                                    Map mapJ0 = hb.j0(new yc("RU", si.a("country_ru", new Object[0])), new yc("BY", si.a("country_by", new Object[0])), new yc("KZ", si.a("country_kz", new Object[0])));
                                                    i4 i4Var = new i4(mainActivity10, mainActivity10.z());
                                                    ViewGroup viewGroupL = mainActivity10.l();
                                                    EditText editText = new EditText(mainActivity10);
                                                    editText.setHint(si.a("radio_city_search", new Object[0]));
                                                    editText.setTextSize(20.0f);
                                                    editText.setTextColor(mainActivity10.o);
                                                    editText.setHintTextColor(mainActivity10.p);
                                                    editText.setSingleLine(true);
                                                    editText.setBackground(mainActivity10.m0(mainActivity10.q, 14));
                                                    editText.setPadding(mainActivity10.r(16), 0, mainActivity10.r(16), 0);
                                                    viewGroupL.addView(editText, new LinearLayout.LayoutParams(-1, mainActivity10.r(60)));
                                                    LinearLayout linearLayoutL = mainActivity10.l();
                                                    ScrollView scrollView2 = new ScrollView(mainActivity10);
                                                    scrollView2.addView(linearLayoutL);
                                                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, mainActivity10.r(420));
                                                    layoutParams.topMargin = mainActivity10.r(10);
                                                    viewGroupL.addView(scrollView2, layoutParams);
                                                    Dialog dialogF = i4Var.f(si.a("radio_city", new Object[0]), si.a("radio_city_hint", new Object[0]), viewGroupL);
                                                    editText.addTextChangedListener(new f0(linearLayoutL, i4Var, mainActivity10, list6, dialogF, mapJ0, 1));
                                                    k8.k0(linearLayoutL, i4Var, mainActivity10, list6, dialogF, mapJ0, "");
                                                    dialogF.show();
                                                }
                                                break;
                                            case 19:
                                                mainActivity3.f0();
                                                break;
                                            case 20:
                                                MainActivity mainActivity11 = mainActivity3;
                                                i4 i4Var2 = new i4(mainActivity11, mainActivity11.z());
                                                TextView textView = new TextView(mainActivity11);
                                                gc gcVarY = mainActivity11.y();
                                                if (gcVarY.n) {
                                                    ArrayList arrayList2 = gcVarY.h;
                                                    ArrayList arrayList3 = new ArrayList(k3.n0(arrayList2));
                                                    int size2 = arrayList2.size();
                                                    int i211 = 0;
                                                    while (i211 < size2) {
                                                        Object obj6 = arrayList2.get(i211);
                                                        i211++;
                                                        arrayList3.add((MediaController) ((yc) obj6).a);
                                                    }
                                                    Object obj7 = MusicNotificationListener.a;
                                                    ArrayList arrayListY = k8.Y();
                                                    if (arrayList3.isEmpty() && arrayListY.isEmpty()) {
                                                        strY0 = si.a("no_media_sessions_start_radio_or_bluetooth", new Object[0]);
                                                    } else {
                                                        List listI1 = j3.i0(i3.y0(arrayList3, "\n\n", new a(6, gcVarY), 30), i3.y0(arrayListY, "\n", new w(18), 30));
                                                        ArrayList arrayList4 = new ArrayList();
                                                        for (Object obj8 : listI1) {
                                                            if (!th.A((String) obj8)) {
                                                                arrayList4.add(obj8);
                                                            }
                                                        }
                                                        strY0 = i3.y0(arrayList4, "\n\n", null, 62);
                                                    }
                                                } else {
                                                    strY0 = si.a("no_music_access_enable_ReferencePanel_music_information", new Object[0]);
                                                }
                                                textView.setText(strY0);
                                                textView.setTextSize(17.0f);
                                                int i212 = l8.a;
                                                textView.setTextColor(l8.j(mainActivity11.z()));
                                                textView.setTextIsSelectable(true);
                                                textView.setLineSpacing(0.0f, 1.2f);
                                                String strA11 = si.a("music_diagnostics", new Object[0]);
                                                String strA12 = si.a("source_currently_shown_by_the_widget", new Object[0]);
                                                ScrollView scrollView3 = new ScrollView(mainActivity11);
                                                scrollView3.addView(textView);
                                                i4Var2.f(strA11, strA12, scrollView3).show();
                                                break;
                                            case 21:
                                                k8.m0(mainActivity3);
                                                break;
                                            case 22:
                                                mainActivity3.a0(0);
                                                break;
                                            case 23:
                                                MainActivity mainActivity12 = mainActivity3;
                                                ComponentName componentNameM = mainActivity12.m(0);
                                                if (componentNameM == null) {
                                                    k8.g0(mainActivity12, 0);
                                                } else {
                                                    mainActivity12.Q(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", componentNameM.getPackageName(), null)));
                                                }
                                                break;
                                            case 24:
                                                MainActivity mainActivity13 = mainActivity3;
                                                ArrayList arrayList5 = mainActivity13.w;
                                                int size3 = arrayList5.size();
                                                int i213 = 0;
                                                while (i213 < size3) {
                                                    Object obj9 = arrayList5.get(i213);
                                                    i213++;
                                                    ((b) obj9).c();
                                                }
                                                arrayList5.clear();
                                                mainActivity13.c = false;
                                                mainActivity13.e0();
                                                break;
                                            case 25:
                                                MainActivity mainActivity14 = mainActivity3;
                                                k8.g0(mainActivity14, Integer.valueOf(mainActivity14.B0));
                                                break;
                                            case 26:
                                                MainActivity mainActivity15 = mainActivity3;
                                                mainActivity15.a0(mainActivity15.B0);
                                                break;
                                            case 27:
                                                k8.g0(mainActivity3, 0);
                                                break;
                                            case 28:
                                                MainActivity mainActivity16 = mainActivity3;
                                                if (!mainActivity16.h) {
                                                    List listH = uh.h();
                                                    ArrayList arrayList6 = new ArrayList(k3.n0(listH));
                                                    Iterator it3 = listH.iterator();
                                                    while (it3.hasNext()) {
                                                        arrayList6.add(((zi) it3.next()).b);
                                                    }
                                                    List listJ0 = i3.J0(i3.K0(arrayList6));
                                                    c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity16).setTitle(si.a("vehicle_brand", new Object[0])).setItems((CharSequence[]) listJ0.toArray(new String[0]), new tb(mainActivity16, listJ0)), null, mainActivity16);
                                                }
                                                break;
                                            default:
                                                MainActivity mainActivity17 = mainActivity3;
                                                boolean z4 = mainActivity17.h;
                                                if (!z4 && !z4) {
                                                    try {
                                                        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                                                        intent.setType("image/*");
                                                        intent.addCategory("android.intent.category.OPENABLE");
                                                        intent.addFlags(1);
                                                        mainActivity17.startActivityForResult(intent, 41);
                                                    } catch (ActivityNotFoundException unused) {
                                                        mainActivity17.v0(si.a("no_file_picker_app_on_this_head", new Object[0]));
                                                    }
                                                }
                                                break;
                                        }
                                        return ti.a;
                                    }
                                }, 12), c9.o(c9Var, si.a("restart_window", new Object[0]), si.a("return_home_and_recreate_the_window", new Object[0]), null, new y5() { // from class: jg
                                    @Override // defpackage.y5
                                    public final Object a() {
                                        String strY0;
                                        switch (i25) {
                                            case 0:
                                                k8.d(mainActivity3);
                                                break;
                                            case 1:
                                                k8.c(mainActivity3, null);
                                                break;
                                            case 2:
                                                MainActivity mainActivity4 = mainActivity3;
                                                ArrayList arrayListC = j0.c();
                                                AlertDialog.Builder title = new AlertDialog.Builder(mainActivity4).setTitle(si.a("app_language_choose", new Object[0]));
                                                ArrayList arrayList = new ArrayList(k3.n0(arrayListC));
                                                int size = arrayListC.size();
                                                int i210 = 0;
                                                while (i210 < size) {
                                                    Object obj4 = arrayListC.get(i210);
                                                    i210++;
                                                    Object obj5 = (String) obj4;
                                                    List list2 = j0.a;
                                                    obj5.getClass();
                                                    List list3 = j0.a;
                                                    int iIndexOf = si.a.indexOf(obj5);
                                                    if (iIndexOf >= 0 && iIndexOf < list3.size()) {
                                                        obj5 = list3.get(iIndexOf);
                                                    }
                                                    arrayList.add((String) obj5);
                                                }
                                                CharSequence[] charSequenceArr = (CharSequence[]) arrayList.toArray(new String[0]);
                                                List list4 = si.a;
                                                AlertDialog.Builder negativeButton = title.setSingleChoiceItems(charSequenceArr, arrayListC.indexOf(si.b.a), new dg(arrayListC, mainActivity4)).setNegativeButton(si.a("cancel", new Object[0]), (DialogInterface.OnClickListener) null);
                                                negativeButton.getClass();
                                                k8.w0(negativeButton);
                                                break;
                                            case 3:
                                                MainActivity mainActivity5 = mainActivity3;
                                                mainActivity5.c = false;
                                                mainActivity5.f = true;
                                                mainActivity5.e0();
                                                mainActivity5.X(new j(14));
                                                break;
                                            case 4:
                                                k8.v0("elastre", mainActivity3);
                                                break;
                                            case 5:
                                                k8.v0("manrope", mainActivity3);
                                                break;
                                            case 6:
                                                k8.v0("custom", mainActivity3);
                                                break;
                                            case 7:
                                                k8.K(mainActivity3, true);
                                                break;
                                            case 8:
                                                MainActivity mainActivity6 = mainActivity3;
                                                x3.d(mainActivity6).delete();
                                                SharedPreferences sharedPreferences = mainActivity6.getSharedPreferences("panel", 0);
                                                SharedPreferences.Editor editorRemove = sharedPreferences.edit().remove("font_custom_name").remove("font_custom_kk").remove("font_custom_wght");
                                                if (k8.e(sharedPreferences.getString("font_choice", ""), "custom")) {
                                                    editorRemove.remove("font_choice");
                                                }
                                                editorRemove.apply();
                                                z9.d(mainActivity6);
                                                z9.a = true;
                                                mainActivity6.e0();
                                                break;
                                            case 9:
                                                MainActivity mainActivity7 = mainActivity3;
                                                i7.g(mainActivity7, "");
                                                mainActivity7.e0();
                                                break;
                                            case 10:
                                                k8.O(mainActivity3, true, true);
                                                break;
                                            case 11:
                                                k8.O(mainActivity3, false, true);
                                                break;
                                            case 12:
                                                k8.Q(mainActivity3, false);
                                                break;
                                            case 13:
                                                k8.Q(mainActivity3, true);
                                                break;
                                            case 14:
                                                MainActivity mainActivity8 = mainActivity3;
                                                new i0(mainActivity8, mainActivity8.z(), si.a("split_left", new Object[0]), false, new lg(mainActivity8, 7)).show();
                                                break;
                                            case 15:
                                                k8.C(mainActivity3);
                                                break;
                                            case 16:
                                                MainActivity mainActivity9 = mainActivity3;
                                                mainActivity9.u = "dock";
                                                mainActivity9.s = null;
                                                mainActivity9.t = 0;
                                                mainActivity9.e0();
                                                break;
                                            case 17:
                                                k8.g0(mainActivity3, 1);
                                                break;
                                            case 18:
                                                MainActivity mainActivity10 = mainActivity3;
                                                md mdVar2 = md.a;
                                                List list5 = md.d;
                                                if (list5 == null) {
                                                    mdVar2.c(mainActivity10);
                                                    list5 = u4.a;
                                                }
                                                List list6 = list5;
                                                if (list6.isEmpty()) {
                                                    mainActivity10.v0(si.a("radio_city_loading", new Object[0]));
                                                } else {
                                                    Map mapJ0 = hb.j0(new yc("RU", si.a("country_ru", new Object[0])), new yc("BY", si.a("country_by", new Object[0])), new yc("KZ", si.a("country_kz", new Object[0])));
                                                    i4 i4Var = new i4(mainActivity10, mainActivity10.z());
                                                    ViewGroup viewGroupL = mainActivity10.l();
                                                    EditText editText = new EditText(mainActivity10);
                                                    editText.setHint(si.a("radio_city_search", new Object[0]));
                                                    editText.setTextSize(20.0f);
                                                    editText.setTextColor(mainActivity10.o);
                                                    editText.setHintTextColor(mainActivity10.p);
                                                    editText.setSingleLine(true);
                                                    editText.setBackground(mainActivity10.m0(mainActivity10.q, 14));
                                                    editText.setPadding(mainActivity10.r(16), 0, mainActivity10.r(16), 0);
                                                    viewGroupL.addView(editText, new LinearLayout.LayoutParams(-1, mainActivity10.r(60)));
                                                    LinearLayout linearLayoutL = mainActivity10.l();
                                                    ScrollView scrollView2 = new ScrollView(mainActivity10);
                                                    scrollView2.addView(linearLayoutL);
                                                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, mainActivity10.r(420));
                                                    layoutParams.topMargin = mainActivity10.r(10);
                                                    viewGroupL.addView(scrollView2, layoutParams);
                                                    Dialog dialogF = i4Var.f(si.a("radio_city", new Object[0]), si.a("radio_city_hint", new Object[0]), viewGroupL);
                                                    editText.addTextChangedListener(new f0(linearLayoutL, i4Var, mainActivity10, list6, dialogF, mapJ0, 1));
                                                    k8.k0(linearLayoutL, i4Var, mainActivity10, list6, dialogF, mapJ0, "");
                                                    dialogF.show();
                                                }
                                                break;
                                            case 19:
                                                mainActivity3.f0();
                                                break;
                                            case 20:
                                                MainActivity mainActivity11 = mainActivity3;
                                                i4 i4Var2 = new i4(mainActivity11, mainActivity11.z());
                                                TextView textView = new TextView(mainActivity11);
                                                gc gcVarY = mainActivity11.y();
                                                if (gcVarY.n) {
                                                    ArrayList arrayList2 = gcVarY.h;
                                                    ArrayList arrayList3 = new ArrayList(k3.n0(arrayList2));
                                                    int size2 = arrayList2.size();
                                                    int i211 = 0;
                                                    while (i211 < size2) {
                                                        Object obj6 = arrayList2.get(i211);
                                                        i211++;
                                                        arrayList3.add((MediaController) ((yc) obj6).a);
                                                    }
                                                    Object obj7 = MusicNotificationListener.a;
                                                    ArrayList arrayListY = k8.Y();
                                                    if (arrayList3.isEmpty() && arrayListY.isEmpty()) {
                                                        strY0 = si.a("no_media_sessions_start_radio_or_bluetooth", new Object[0]);
                                                    } else {
                                                        List listI1 = j3.i0(i3.y0(arrayList3, "\n\n", new a(6, gcVarY), 30), i3.y0(arrayListY, "\n", new w(18), 30));
                                                        ArrayList arrayList4 = new ArrayList();
                                                        for (Object obj8 : listI1) {
                                                            if (!th.A((String) obj8)) {
                                                                arrayList4.add(obj8);
                                                            }
                                                        }
                                                        strY0 = i3.y0(arrayList4, "\n\n", null, 62);
                                                    }
                                                } else {
                                                    strY0 = si.a("no_music_access_enable_ReferencePanel_music_information", new Object[0]);
                                                }
                                                textView.setText(strY0);
                                                textView.setTextSize(17.0f);
                                                int i212 = l8.a;
                                                textView.setTextColor(l8.j(mainActivity11.z()));
                                                textView.setTextIsSelectable(true);
                                                textView.setLineSpacing(0.0f, 1.2f);
                                                String strA11 = si.a("music_diagnostics", new Object[0]);
                                                String strA12 = si.a("source_currently_shown_by_the_widget", new Object[0]);
                                                ScrollView scrollView3 = new ScrollView(mainActivity11);
                                                scrollView3.addView(textView);
                                                i4Var2.f(strA11, strA12, scrollView3).show();
                                                break;
                                            case 21:
                                                k8.m0(mainActivity3);
                                                break;
                                            case 22:
                                                mainActivity3.a0(0);
                                                break;
                                            case 23:
                                                MainActivity mainActivity12 = mainActivity3;
                                                ComponentName componentNameM = mainActivity12.m(0);
                                                if (componentNameM == null) {
                                                    k8.g0(mainActivity12, 0);
                                                } else {
                                                    mainActivity12.Q(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", componentNameM.getPackageName(), null)));
                                                }
                                                break;
                                            case 24:
                                                MainActivity mainActivity13 = mainActivity3;
                                                ArrayList arrayList5 = mainActivity13.w;
                                                int size3 = arrayList5.size();
                                                int i213 = 0;
                                                while (i213 < size3) {
                                                    Object obj9 = arrayList5.get(i213);
                                                    i213++;
                                                    ((b) obj9).c();
                                                }
                                                arrayList5.clear();
                                                mainActivity13.c = false;
                                                mainActivity13.e0();
                                                break;
                                            case 25:
                                                MainActivity mainActivity14 = mainActivity3;
                                                k8.g0(mainActivity14, Integer.valueOf(mainActivity14.B0));
                                                break;
                                            case 26:
                                                MainActivity mainActivity15 = mainActivity3;
                                                mainActivity15.a0(mainActivity15.B0);
                                                break;
                                            case 27:
                                                k8.g0(mainActivity3, 0);
                                                break;
                                            case 28:
                                                MainActivity mainActivity16 = mainActivity3;
                                                if (!mainActivity16.h) {
                                                    List listH = uh.h();
                                                    ArrayList arrayList6 = new ArrayList(k3.n0(listH));
                                                    Iterator it3 = listH.iterator();
                                                    while (it3.hasNext()) {
                                                        arrayList6.add(((zi) it3.next()).b);
                                                    }
                                                    List listJ0 = i3.J0(i3.K0(arrayList6));
                                                    c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity16).setTitle(si.a("vehicle_brand", new Object[0])).setItems((CharSequence[]) listJ0.toArray(new String[0]), new tb(mainActivity16, listJ0)), null, mainActivity16);
                                                }
                                                break;
                                            default:
                                                MainActivity mainActivity17 = mainActivity3;
                                                boolean z4 = mainActivity17.h;
                                                if (!z4 && !z4) {
                                                    try {
                                                        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                                                        intent.setType("image/*");
                                                        intent.addCategory("android.intent.category.OPENABLE");
                                                        intent.addFlags(1);
                                                        mainActivity17.startActivityForResult(intent, 41);
                                                    } catch (ActivityNotFoundException unused) {
                                                        mainActivity17.v0(si.a("no_file_picker_app_on_this_head", new Object[0]));
                                                    }
                                                }
                                                break;
                                        }
                                        return ti.a;
                                    }
                                }, 12)));
                            }
                            linearLayout.addView(c9Var.b(si.a("app_widget", new Object[0])));
                            String strA11 = si.a("widget_app", new Object[0]);
                            String strC2 = mainActivity3.c(mainActivity3.t());
                            if (strC2 == null) {
                                strC2 = si.a("choose", new Object[0]);
                            }
                            String str10 = strC2;
                            final int i31 = 25;
                            final int i32 = 26;
                            linearLayout.addView(c9Var.d(c9.o(c9Var, strA11, null, str10, new y5() { // from class: jg
                                @Override // defpackage.y5
                                public final Object a() {
                                    String strY0;
                                    switch (i31) {
                                        case 0:
                                            k8.d(mainActivity3);
                                            break;
                                        case 1:
                                            k8.c(mainActivity3, null);
                                            break;
                                        case 2:
                                            MainActivity mainActivity4 = mainActivity3;
                                            ArrayList arrayListC = j0.c();
                                            AlertDialog.Builder title = new AlertDialog.Builder(mainActivity4).setTitle(si.a("app_language_choose", new Object[0]));
                                            ArrayList arrayList = new ArrayList(k3.n0(arrayListC));
                                            int size = arrayListC.size();
                                            int i210 = 0;
                                            while (i210 < size) {
                                                Object obj4 = arrayListC.get(i210);
                                                i210++;
                                                Object obj5 = (String) obj4;
                                                List list2 = j0.a;
                                                obj5.getClass();
                                                List list3 = j0.a;
                                                int iIndexOf = si.a.indexOf(obj5);
                                                if (iIndexOf >= 0 && iIndexOf < list3.size()) {
                                                    obj5 = list3.get(iIndexOf);
                                                }
                                                arrayList.add((String) obj5);
                                            }
                                            CharSequence[] charSequenceArr = (CharSequence[]) arrayList.toArray(new String[0]);
                                            List list4 = si.a;
                                            AlertDialog.Builder negativeButton = title.setSingleChoiceItems(charSequenceArr, arrayListC.indexOf(si.b.a), new dg(arrayListC, mainActivity4)).setNegativeButton(si.a("cancel", new Object[0]), (DialogInterface.OnClickListener) null);
                                            negativeButton.getClass();
                                            k8.w0(negativeButton);
                                            break;
                                        case 3:
                                            MainActivity mainActivity5 = mainActivity3;
                                            mainActivity5.c = false;
                                            mainActivity5.f = true;
                                            mainActivity5.e0();
                                            mainActivity5.X(new j(14));
                                            break;
                                        case 4:
                                            k8.v0("elastre", mainActivity3);
                                            break;
                                        case 5:
                                            k8.v0("manrope", mainActivity3);
                                            break;
                                        case 6:
                                            k8.v0("custom", mainActivity3);
                                            break;
                                        case 7:
                                            k8.K(mainActivity3, true);
                                            break;
                                        case 8:
                                            MainActivity mainActivity6 = mainActivity3;
                                            x3.d(mainActivity6).delete();
                                            SharedPreferences sharedPreferences = mainActivity6.getSharedPreferences("panel", 0);
                                            SharedPreferences.Editor editorRemove = sharedPreferences.edit().remove("font_custom_name").remove("font_custom_kk").remove("font_custom_wght");
                                            if (k8.e(sharedPreferences.getString("font_choice", ""), "custom")) {
                                                editorRemove.remove("font_choice");
                                            }
                                            editorRemove.apply();
                                            z9.d(mainActivity6);
                                            z9.a = true;
                                            mainActivity6.e0();
                                            break;
                                        case 9:
                                            MainActivity mainActivity7 = mainActivity3;
                                            i7.g(mainActivity7, "");
                                            mainActivity7.e0();
                                            break;
                                        case 10:
                                            k8.O(mainActivity3, true, true);
                                            break;
                                        case 11:
                                            k8.O(mainActivity3, false, true);
                                            break;
                                        case 12:
                                            k8.Q(mainActivity3, false);
                                            break;
                                        case 13:
                                            k8.Q(mainActivity3, true);
                                            break;
                                        case 14:
                                            MainActivity mainActivity8 = mainActivity3;
                                            new i0(mainActivity8, mainActivity8.z(), si.a("split_left", new Object[0]), false, new lg(mainActivity8, 7)).show();
                                            break;
                                        case 15:
                                            k8.C(mainActivity3);
                                            break;
                                        case 16:
                                            MainActivity mainActivity9 = mainActivity3;
                                            mainActivity9.u = "dock";
                                            mainActivity9.s = null;
                                            mainActivity9.t = 0;
                                            mainActivity9.e0();
                                            break;
                                        case 17:
                                            k8.g0(mainActivity3, 1);
                                            break;
                                        case 18:
                                            MainActivity mainActivity10 = mainActivity3;
                                            md mdVar2 = md.a;
                                            List list5 = md.d;
                                            if (list5 == null) {
                                                mdVar2.c(mainActivity10);
                                                list5 = u4.a;
                                            }
                                            List list6 = list5;
                                            if (list6.isEmpty()) {
                                                mainActivity10.v0(si.a("radio_city_loading", new Object[0]));
                                            } else {
                                                Map mapJ0 = hb.j0(new yc("RU", si.a("country_ru", new Object[0])), new yc("BY", si.a("country_by", new Object[0])), new yc("KZ", si.a("country_kz", new Object[0])));
                                                i4 i4Var = new i4(mainActivity10, mainActivity10.z());
                                                ViewGroup viewGroupL = mainActivity10.l();
                                                EditText editText = new EditText(mainActivity10);
                                                editText.setHint(si.a("radio_city_search", new Object[0]));
                                                editText.setTextSize(20.0f);
                                                editText.setTextColor(mainActivity10.o);
                                                editText.setHintTextColor(mainActivity10.p);
                                                editText.setSingleLine(true);
                                                editText.setBackground(mainActivity10.m0(mainActivity10.q, 14));
                                                editText.setPadding(mainActivity10.r(16), 0, mainActivity10.r(16), 0);
                                                viewGroupL.addView(editText, new LinearLayout.LayoutParams(-1, mainActivity10.r(60)));
                                                LinearLayout linearLayoutL = mainActivity10.l();
                                                ScrollView scrollView2 = new ScrollView(mainActivity10);
                                                scrollView2.addView(linearLayoutL);
                                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, mainActivity10.r(420));
                                                layoutParams.topMargin = mainActivity10.r(10);
                                                viewGroupL.addView(scrollView2, layoutParams);
                                                Dialog dialogF = i4Var.f(si.a("radio_city", new Object[0]), si.a("radio_city_hint", new Object[0]), viewGroupL);
                                                editText.addTextChangedListener(new f0(linearLayoutL, i4Var, mainActivity10, list6, dialogF, mapJ0, 1));
                                                k8.k0(linearLayoutL, i4Var, mainActivity10, list6, dialogF, mapJ0, "");
                                                dialogF.show();
                                            }
                                            break;
                                        case 19:
                                            mainActivity3.f0();
                                            break;
                                        case 20:
                                            MainActivity mainActivity11 = mainActivity3;
                                            i4 i4Var2 = new i4(mainActivity11, mainActivity11.z());
                                            TextView textView = new TextView(mainActivity11);
                                            gc gcVarY = mainActivity11.y();
                                            if (gcVarY.n) {
                                                ArrayList arrayList2 = gcVarY.h;
                                                ArrayList arrayList3 = new ArrayList(k3.n0(arrayList2));
                                                int size2 = arrayList2.size();
                                                int i211 = 0;
                                                while (i211 < size2) {
                                                    Object obj6 = arrayList2.get(i211);
                                                    i211++;
                                                    arrayList3.add((MediaController) ((yc) obj6).a);
                                                }
                                                Object obj7 = MusicNotificationListener.a;
                                                ArrayList arrayListY = k8.Y();
                                                if (arrayList3.isEmpty() && arrayListY.isEmpty()) {
                                                    strY0 = si.a("no_media_sessions_start_radio_or_bluetooth", new Object[0]);
                                                } else {
                                                    List listI1 = j3.i0(i3.y0(arrayList3, "\n\n", new a(6, gcVarY), 30), i3.y0(arrayListY, "\n", new w(18), 30));
                                                    ArrayList arrayList4 = new ArrayList();
                                                    for (Object obj8 : listI1) {
                                                        if (!th.A((String) obj8)) {
                                                            arrayList4.add(obj8);
                                                        }
                                                    }
                                                    strY0 = i3.y0(arrayList4, "\n\n", null, 62);
                                                }
                                            } else {
                                                strY0 = si.a("no_music_access_enable_ReferencePanel_music_information", new Object[0]);
                                            }
                                            textView.setText(strY0);
                                            textView.setTextSize(17.0f);
                                            int i212 = l8.a;
                                            textView.setTextColor(l8.j(mainActivity11.z()));
                                            textView.setTextIsSelectable(true);
                                            textView.setLineSpacing(0.0f, 1.2f);
                                            String strA12 = si.a("music_diagnostics", new Object[0]);
                                            String strA13 = si.a("source_currently_shown_by_the_widget", new Object[0]);
                                            ScrollView scrollView3 = new ScrollView(mainActivity11);
                                            scrollView3.addView(textView);
                                            i4Var2.f(strA12, strA13, scrollView3).show();
                                            break;
                                        case 21:
                                            k8.m0(mainActivity3);
                                            break;
                                        case 22:
                                            mainActivity3.a0(0);
                                            break;
                                        case 23:
                                            MainActivity mainActivity12 = mainActivity3;
                                            ComponentName componentNameM = mainActivity12.m(0);
                                            if (componentNameM == null) {
                                                k8.g0(mainActivity12, 0);
                                            } else {
                                                mainActivity12.Q(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", componentNameM.getPackageName(), null)));
                                            }
                                            break;
                                        case 24:
                                            MainActivity mainActivity13 = mainActivity3;
                                            ArrayList arrayList5 = mainActivity13.w;
                                            int size3 = arrayList5.size();
                                            int i213 = 0;
                                            while (i213 < size3) {
                                                Object obj9 = arrayList5.get(i213);
                                                i213++;
                                                ((b) obj9).c();
                                            }
                                            arrayList5.clear();
                                            mainActivity13.c = false;
                                            mainActivity13.e0();
                                            break;
                                        case 25:
                                            MainActivity mainActivity14 = mainActivity3;
                                            k8.g0(mainActivity14, Integer.valueOf(mainActivity14.B0));
                                            break;
                                        case 26:
                                            MainActivity mainActivity15 = mainActivity3;
                                            mainActivity15.a0(mainActivity15.B0);
                                            break;
                                        case 27:
                                            k8.g0(mainActivity3, 0);
                                            break;
                                        case 28:
                                            MainActivity mainActivity16 = mainActivity3;
                                            if (!mainActivity16.h) {
                                                List listH = uh.h();
                                                ArrayList arrayList6 = new ArrayList(k3.n0(listH));
                                                Iterator it3 = listH.iterator();
                                                while (it3.hasNext()) {
                                                    arrayList6.add(((zi) it3.next()).b);
                                                }
                                                List listJ0 = i3.J0(i3.K0(arrayList6));
                                                c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity16).setTitle(si.a("vehicle_brand", new Object[0])).setItems((CharSequence[]) listJ0.toArray(new String[0]), new tb(mainActivity16, listJ0)), null, mainActivity16);
                                            }
                                            break;
                                        default:
                                            MainActivity mainActivity17 = mainActivity3;
                                            boolean z4 = mainActivity17.h;
                                            if (!z4 && !z4) {
                                                try {
                                                    Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                                                    intent.setType("image/*");
                                                    intent.addCategory("android.intent.category.OPENABLE");
                                                    intent.addFlags(1);
                                                    mainActivity17.startActivityForResult(intent, 41);
                                                } catch (ActivityNotFoundException unused) {
                                                    mainActivity17.v0(si.a("no_file_picker_app_on_this_head", new Object[0]));
                                                }
                                            }
                                            break;
                                    }
                                    return ti.a;
                                }
                            }, 8), c9.o(c9Var, th.K(si.a("open_286", new Object[0]), 8250, ' '), null, null, new y5() { // from class: jg
                                @Override // defpackage.y5
                                public final Object a() {
                                    String strY0;
                                    switch (i32) {
                                        case 0:
                                            k8.d(mainActivity3);
                                            break;
                                        case 1:
                                            k8.c(mainActivity3, null);
                                            break;
                                        case 2:
                                            MainActivity mainActivity4 = mainActivity3;
                                            ArrayList arrayListC = j0.c();
                                            AlertDialog.Builder title = new AlertDialog.Builder(mainActivity4).setTitle(si.a("app_language_choose", new Object[0]));
                                            ArrayList arrayList = new ArrayList(k3.n0(arrayListC));
                                            int size = arrayListC.size();
                                            int i210 = 0;
                                            while (i210 < size) {
                                                Object obj4 = arrayListC.get(i210);
                                                i210++;
                                                Object obj5 = (String) obj4;
                                                List list2 = j0.a;
                                                obj5.getClass();
                                                List list3 = j0.a;
                                                int iIndexOf = si.a.indexOf(obj5);
                                                if (iIndexOf >= 0 && iIndexOf < list3.size()) {
                                                    obj5 = list3.get(iIndexOf);
                                                }
                                                arrayList.add((String) obj5);
                                            }
                                            CharSequence[] charSequenceArr = (CharSequence[]) arrayList.toArray(new String[0]);
                                            List list4 = si.a;
                                            AlertDialog.Builder negativeButton = title.setSingleChoiceItems(charSequenceArr, arrayListC.indexOf(si.b.a), new dg(arrayListC, mainActivity4)).setNegativeButton(si.a("cancel", new Object[0]), (DialogInterface.OnClickListener) null);
                                            negativeButton.getClass();
                                            k8.w0(negativeButton);
                                            break;
                                        case 3:
                                            MainActivity mainActivity5 = mainActivity3;
                                            mainActivity5.c = false;
                                            mainActivity5.f = true;
                                            mainActivity5.e0();
                                            mainActivity5.X(new j(14));
                                            break;
                                        case 4:
                                            k8.v0("elastre", mainActivity3);
                                            break;
                                        case 5:
                                            k8.v0("manrope", mainActivity3);
                                            break;
                                        case 6:
                                            k8.v0("custom", mainActivity3);
                                            break;
                                        case 7:
                                            k8.K(mainActivity3, true);
                                            break;
                                        case 8:
                                            MainActivity mainActivity6 = mainActivity3;
                                            x3.d(mainActivity6).delete();
                                            SharedPreferences sharedPreferences = mainActivity6.getSharedPreferences("panel", 0);
                                            SharedPreferences.Editor editorRemove = sharedPreferences.edit().remove("font_custom_name").remove("font_custom_kk").remove("font_custom_wght");
                                            if (k8.e(sharedPreferences.getString("font_choice", ""), "custom")) {
                                                editorRemove.remove("font_choice");
                                            }
                                            editorRemove.apply();
                                            z9.d(mainActivity6);
                                            z9.a = true;
                                            mainActivity6.e0();
                                            break;
                                        case 9:
                                            MainActivity mainActivity7 = mainActivity3;
                                            i7.g(mainActivity7, "");
                                            mainActivity7.e0();
                                            break;
                                        case 10:
                                            k8.O(mainActivity3, true, true);
                                            break;
                                        case 11:
                                            k8.O(mainActivity3, false, true);
                                            break;
                                        case 12:
                                            k8.Q(mainActivity3, false);
                                            break;
                                        case 13:
                                            k8.Q(mainActivity3, true);
                                            break;
                                        case 14:
                                            MainActivity mainActivity8 = mainActivity3;
                                            new i0(mainActivity8, mainActivity8.z(), si.a("split_left", new Object[0]), false, new lg(mainActivity8, 7)).show();
                                            break;
                                        case 15:
                                            k8.C(mainActivity3);
                                            break;
                                        case 16:
                                            MainActivity mainActivity9 = mainActivity3;
                                            mainActivity9.u = "dock";
                                            mainActivity9.s = null;
                                            mainActivity9.t = 0;
                                            mainActivity9.e0();
                                            break;
                                        case 17:
                                            k8.g0(mainActivity3, 1);
                                            break;
                                        case 18:
                                            MainActivity mainActivity10 = mainActivity3;
                                            md mdVar2 = md.a;
                                            List list5 = md.d;
                                            if (list5 == null) {
                                                mdVar2.c(mainActivity10);
                                                list5 = u4.a;
                                            }
                                            List list6 = list5;
                                            if (list6.isEmpty()) {
                                                mainActivity10.v0(si.a("radio_city_loading", new Object[0]));
                                            } else {
                                                Map mapJ0 = hb.j0(new yc("RU", si.a("country_ru", new Object[0])), new yc("BY", si.a("country_by", new Object[0])), new yc("KZ", si.a("country_kz", new Object[0])));
                                                i4 i4Var = new i4(mainActivity10, mainActivity10.z());
                                                ViewGroup viewGroupL = mainActivity10.l();
                                                EditText editText = new EditText(mainActivity10);
                                                editText.setHint(si.a("radio_city_search", new Object[0]));
                                                editText.setTextSize(20.0f);
                                                editText.setTextColor(mainActivity10.o);
                                                editText.setHintTextColor(mainActivity10.p);
                                                editText.setSingleLine(true);
                                                editText.setBackground(mainActivity10.m0(mainActivity10.q, 14));
                                                editText.setPadding(mainActivity10.r(16), 0, mainActivity10.r(16), 0);
                                                viewGroupL.addView(editText, new LinearLayout.LayoutParams(-1, mainActivity10.r(60)));
                                                LinearLayout linearLayoutL = mainActivity10.l();
                                                ScrollView scrollView2 = new ScrollView(mainActivity10);
                                                scrollView2.addView(linearLayoutL);
                                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, mainActivity10.r(420));
                                                layoutParams.topMargin = mainActivity10.r(10);
                                                viewGroupL.addView(scrollView2, layoutParams);
                                                Dialog dialogF = i4Var.f(si.a("radio_city", new Object[0]), si.a("radio_city_hint", new Object[0]), viewGroupL);
                                                editText.addTextChangedListener(new f0(linearLayoutL, i4Var, mainActivity10, list6, dialogF, mapJ0, 1));
                                                k8.k0(linearLayoutL, i4Var, mainActivity10, list6, dialogF, mapJ0, "");
                                                dialogF.show();
                                            }
                                            break;
                                        case 19:
                                            mainActivity3.f0();
                                            break;
                                        case 20:
                                            MainActivity mainActivity11 = mainActivity3;
                                            i4 i4Var2 = new i4(mainActivity11, mainActivity11.z());
                                            TextView textView = new TextView(mainActivity11);
                                            gc gcVarY = mainActivity11.y();
                                            if (gcVarY.n) {
                                                ArrayList arrayList2 = gcVarY.h;
                                                ArrayList arrayList3 = new ArrayList(k3.n0(arrayList2));
                                                int size2 = arrayList2.size();
                                                int i211 = 0;
                                                while (i211 < size2) {
                                                    Object obj6 = arrayList2.get(i211);
                                                    i211++;
                                                    arrayList3.add((MediaController) ((yc) obj6).a);
                                                }
                                                Object obj7 = MusicNotificationListener.a;
                                                ArrayList arrayListY = k8.Y();
                                                if (arrayList3.isEmpty() && arrayListY.isEmpty()) {
                                                    strY0 = si.a("no_media_sessions_start_radio_or_bluetooth", new Object[0]);
                                                } else {
                                                    List listI1 = j3.i0(i3.y0(arrayList3, "\n\n", new a(6, gcVarY), 30), i3.y0(arrayListY, "\n", new w(18), 30));
                                                    ArrayList arrayList4 = new ArrayList();
                                                    for (Object obj8 : listI1) {
                                                        if (!th.A((String) obj8)) {
                                                            arrayList4.add(obj8);
                                                        }
                                                    }
                                                    strY0 = i3.y0(arrayList4, "\n\n", null, 62);
                                                }
                                            } else {
                                                strY0 = si.a("no_music_access_enable_ReferencePanel_music_information", new Object[0]);
                                            }
                                            textView.setText(strY0);
                                            textView.setTextSize(17.0f);
                                            int i212 = l8.a;
                                            textView.setTextColor(l8.j(mainActivity11.z()));
                                            textView.setTextIsSelectable(true);
                                            textView.setLineSpacing(0.0f, 1.2f);
                                            String strA12 = si.a("music_diagnostics", new Object[0]);
                                            String strA13 = si.a("source_currently_shown_by_the_widget", new Object[0]);
                                            ScrollView scrollView3 = new ScrollView(mainActivity11);
                                            scrollView3.addView(textView);
                                            i4Var2.f(strA12, strA13, scrollView3).show();
                                            break;
                                        case 21:
                                            k8.m0(mainActivity3);
                                            break;
                                        case 22:
                                            mainActivity3.a0(0);
                                            break;
                                        case 23:
                                            MainActivity mainActivity12 = mainActivity3;
                                            ComponentName componentNameM = mainActivity12.m(0);
                                            if (componentNameM == null) {
                                                k8.g0(mainActivity12, 0);
                                            } else {
                                                mainActivity12.Q(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", componentNameM.getPackageName(), null)));
                                            }
                                            break;
                                        case 24:
                                            MainActivity mainActivity13 = mainActivity3;
                                            ArrayList arrayList5 = mainActivity13.w;
                                            int size3 = arrayList5.size();
                                            int i213 = 0;
                                            while (i213 < size3) {
                                                Object obj9 = arrayList5.get(i213);
                                                i213++;
                                                ((b) obj9).c();
                                            }
                                            arrayList5.clear();
                                            mainActivity13.c = false;
                                            mainActivity13.e0();
                                            break;
                                        case 25:
                                            MainActivity mainActivity14 = mainActivity3;
                                            k8.g0(mainActivity14, Integer.valueOf(mainActivity14.B0));
                                            break;
                                        case 26:
                                            MainActivity mainActivity15 = mainActivity3;
                                            mainActivity15.a0(mainActivity15.B0);
                                            break;
                                        case 27:
                                            k8.g0(mainActivity3, 0);
                                            break;
                                        case 28:
                                            MainActivity mainActivity16 = mainActivity3;
                                            if (!mainActivity16.h) {
                                                List listH = uh.h();
                                                ArrayList arrayList6 = new ArrayList(k3.n0(listH));
                                                Iterator it3 = listH.iterator();
                                                while (it3.hasNext()) {
                                                    arrayList6.add(((zi) it3.next()).b);
                                                }
                                                List listJ0 = i3.J0(i3.K0(arrayList6));
                                                c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity16).setTitle(si.a("vehicle_brand", new Object[0])).setItems((CharSequence[]) listJ0.toArray(new String[0]), new tb(mainActivity16, listJ0)), null, mainActivity16);
                                            }
                                            break;
                                        default:
                                            MainActivity mainActivity17 = mainActivity3;
                                            boolean z4 = mainActivity17.h;
                                            if (!z4 && !z4) {
                                                try {
                                                    Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                                                    intent.setType("image/*");
                                                    intent.addCategory("android.intent.category.OPENABLE");
                                                    intent.addFlags(1);
                                                    mainActivity17.startActivityForResult(intent, 41);
                                                } catch (ActivityNotFoundException unused) {
                                                    mainActivity17.v0(si.a("no_file_picker_app_on_this_head", new Object[0]));
                                                }
                                            }
                                            break;
                                    }
                                    return ti.a;
                                }
                            }, 12)));
                            linearLayout.addView(c9Var.l(si.a("app_widget_hint", new Object[0])));
                            String strA12 = si.a("navigation", new Object[0]);
                            String strC3 = mainActivity3.c(0);
                            if (strC3 == null) {
                                strC3 = si.a("choose", new Object[0]);
                            }
                            String str11 = strC3;
                            final int i33 = 27;
                            linearLayout.addView(c9Var.d(c9.o(c9Var, strA12, null, str11, new y5() { // from class: jg
                                @Override // defpackage.y5
                                public final Object a() {
                                    String strY0;
                                    switch (i33) {
                                        case 0:
                                            k8.d(mainActivity3);
                                            break;
                                        case 1:
                                            k8.c(mainActivity3, null);
                                            break;
                                        case 2:
                                            MainActivity mainActivity4 = mainActivity3;
                                            ArrayList arrayListC = j0.c();
                                            AlertDialog.Builder title = new AlertDialog.Builder(mainActivity4).setTitle(si.a("app_language_choose", new Object[0]));
                                            ArrayList arrayList = new ArrayList(k3.n0(arrayListC));
                                            int size = arrayListC.size();
                                            int i210 = 0;
                                            while (i210 < size) {
                                                Object obj4 = arrayListC.get(i210);
                                                i210++;
                                                Object obj5 = (String) obj4;
                                                List list2 = j0.a;
                                                obj5.getClass();
                                                List list3 = j0.a;
                                                int iIndexOf = si.a.indexOf(obj5);
                                                if (iIndexOf >= 0 && iIndexOf < list3.size()) {
                                                    obj5 = list3.get(iIndexOf);
                                                }
                                                arrayList.add((String) obj5);
                                            }
                                            CharSequence[] charSequenceArr = (CharSequence[]) arrayList.toArray(new String[0]);
                                            List list4 = si.a;
                                            AlertDialog.Builder negativeButton = title.setSingleChoiceItems(charSequenceArr, arrayListC.indexOf(si.b.a), new dg(arrayListC, mainActivity4)).setNegativeButton(si.a("cancel", new Object[0]), (DialogInterface.OnClickListener) null);
                                            negativeButton.getClass();
                                            k8.w0(negativeButton);
                                            break;
                                        case 3:
                                            MainActivity mainActivity5 = mainActivity3;
                                            mainActivity5.c = false;
                                            mainActivity5.f = true;
                                            mainActivity5.e0();
                                            mainActivity5.X(new j(14));
                                            break;
                                        case 4:
                                            k8.v0("elastre", mainActivity3);
                                            break;
                                        case 5:
                                            k8.v0("manrope", mainActivity3);
                                            break;
                                        case 6:
                                            k8.v0("custom", mainActivity3);
                                            break;
                                        case 7:
                                            k8.K(mainActivity3, true);
                                            break;
                                        case 8:
                                            MainActivity mainActivity6 = mainActivity3;
                                            x3.d(mainActivity6).delete();
                                            SharedPreferences sharedPreferences = mainActivity6.getSharedPreferences("panel", 0);
                                            SharedPreferences.Editor editorRemove = sharedPreferences.edit().remove("font_custom_name").remove("font_custom_kk").remove("font_custom_wght");
                                            if (k8.e(sharedPreferences.getString("font_choice", ""), "custom")) {
                                                editorRemove.remove("font_choice");
                                            }
                                            editorRemove.apply();
                                            z9.d(mainActivity6);
                                            z9.a = true;
                                            mainActivity6.e0();
                                            break;
                                        case 9:
                                            MainActivity mainActivity7 = mainActivity3;
                                            i7.g(mainActivity7, "");
                                            mainActivity7.e0();
                                            break;
                                        case 10:
                                            k8.O(mainActivity3, true, true);
                                            break;
                                        case 11:
                                            k8.O(mainActivity3, false, true);
                                            break;
                                        case 12:
                                            k8.Q(mainActivity3, false);
                                            break;
                                        case 13:
                                            k8.Q(mainActivity3, true);
                                            break;
                                        case 14:
                                            MainActivity mainActivity8 = mainActivity3;
                                            new i0(mainActivity8, mainActivity8.z(), si.a("split_left", new Object[0]), false, new lg(mainActivity8, 7)).show();
                                            break;
                                        case 15:
                                            k8.C(mainActivity3);
                                            break;
                                        case 16:
                                            MainActivity mainActivity9 = mainActivity3;
                                            mainActivity9.u = "dock";
                                            mainActivity9.s = null;
                                            mainActivity9.t = 0;
                                            mainActivity9.e0();
                                            break;
                                        case 17:
                                            k8.g0(mainActivity3, 1);
                                            break;
                                        case 18:
                                            MainActivity mainActivity10 = mainActivity3;
                                            md mdVar2 = md.a;
                                            List list5 = md.d;
                                            if (list5 == null) {
                                                mdVar2.c(mainActivity10);
                                                list5 = u4.a;
                                            }
                                            List list6 = list5;
                                            if (list6.isEmpty()) {
                                                mainActivity10.v0(si.a("radio_city_loading", new Object[0]));
                                            } else {
                                                Map mapJ0 = hb.j0(new yc("RU", si.a("country_ru", new Object[0])), new yc("BY", si.a("country_by", new Object[0])), new yc("KZ", si.a("country_kz", new Object[0])));
                                                i4 i4Var = new i4(mainActivity10, mainActivity10.z());
                                                ViewGroup viewGroupL = mainActivity10.l();
                                                EditText editText = new EditText(mainActivity10);
                                                editText.setHint(si.a("radio_city_search", new Object[0]));
                                                editText.setTextSize(20.0f);
                                                editText.setTextColor(mainActivity10.o);
                                                editText.setHintTextColor(mainActivity10.p);
                                                editText.setSingleLine(true);
                                                editText.setBackground(mainActivity10.m0(mainActivity10.q, 14));
                                                editText.setPadding(mainActivity10.r(16), 0, mainActivity10.r(16), 0);
                                                viewGroupL.addView(editText, new LinearLayout.LayoutParams(-1, mainActivity10.r(60)));
                                                LinearLayout linearLayoutL = mainActivity10.l();
                                                ScrollView scrollView2 = new ScrollView(mainActivity10);
                                                scrollView2.addView(linearLayoutL);
                                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, mainActivity10.r(420));
                                                layoutParams.topMargin = mainActivity10.r(10);
                                                viewGroupL.addView(scrollView2, layoutParams);
                                                Dialog dialogF = i4Var.f(si.a("radio_city", new Object[0]), si.a("radio_city_hint", new Object[0]), viewGroupL);
                                                editText.addTextChangedListener(new f0(linearLayoutL, i4Var, mainActivity10, list6, dialogF, mapJ0, 1));
                                                k8.k0(linearLayoutL, i4Var, mainActivity10, list6, dialogF, mapJ0, "");
                                                dialogF.show();
                                            }
                                            break;
                                        case 19:
                                            mainActivity3.f0();
                                            break;
                                        case 20:
                                            MainActivity mainActivity11 = mainActivity3;
                                            i4 i4Var2 = new i4(mainActivity11, mainActivity11.z());
                                            TextView textView = new TextView(mainActivity11);
                                            gc gcVarY = mainActivity11.y();
                                            if (gcVarY.n) {
                                                ArrayList arrayList2 = gcVarY.h;
                                                ArrayList arrayList3 = new ArrayList(k3.n0(arrayList2));
                                                int size2 = arrayList2.size();
                                                int i211 = 0;
                                                while (i211 < size2) {
                                                    Object obj6 = arrayList2.get(i211);
                                                    i211++;
                                                    arrayList3.add((MediaController) ((yc) obj6).a);
                                                }
                                                Object obj7 = MusicNotificationListener.a;
                                                ArrayList arrayListY = k8.Y();
                                                if (arrayList3.isEmpty() && arrayListY.isEmpty()) {
                                                    strY0 = si.a("no_media_sessions_start_radio_or_bluetooth", new Object[0]);
                                                } else {
                                                    List listI1 = j3.i0(i3.y0(arrayList3, "\n\n", new a(6, gcVarY), 30), i3.y0(arrayListY, "\n", new w(18), 30));
                                                    ArrayList arrayList4 = new ArrayList();
                                                    for (Object obj8 : listI1) {
                                                        if (!th.A((String) obj8)) {
                                                            arrayList4.add(obj8);
                                                        }
                                                    }
                                                    strY0 = i3.y0(arrayList4, "\n\n", null, 62);
                                                }
                                            } else {
                                                strY0 = si.a("no_music_access_enable_ReferencePanel_music_information", new Object[0]);
                                            }
                                            textView.setText(strY0);
                                            textView.setTextSize(17.0f);
                                            int i212 = l8.a;
                                            textView.setTextColor(l8.j(mainActivity11.z()));
                                            textView.setTextIsSelectable(true);
                                            textView.setLineSpacing(0.0f, 1.2f);
                                            String strA13 = si.a("music_diagnostics", new Object[0]);
                                            String strA14 = si.a("source_currently_shown_by_the_widget", new Object[0]);
                                            ScrollView scrollView3 = new ScrollView(mainActivity11);
                                            scrollView3.addView(textView);
                                            i4Var2.f(strA13, strA14, scrollView3).show();
                                            break;
                                        case 21:
                                            k8.m0(mainActivity3);
                                            break;
                                        case 22:
                                            mainActivity3.a0(0);
                                            break;
                                        case 23:
                                            MainActivity mainActivity12 = mainActivity3;
                                            ComponentName componentNameM = mainActivity12.m(0);
                                            if (componentNameM == null) {
                                                k8.g0(mainActivity12, 0);
                                            } else {
                                                mainActivity12.Q(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", componentNameM.getPackageName(), null)));
                                            }
                                            break;
                                        case 24:
                                            MainActivity mainActivity13 = mainActivity3;
                                            ArrayList arrayList5 = mainActivity13.w;
                                            int size3 = arrayList5.size();
                                            int i213 = 0;
                                            while (i213 < size3) {
                                                Object obj9 = arrayList5.get(i213);
                                                i213++;
                                                ((b) obj9).c();
                                            }
                                            arrayList5.clear();
                                            mainActivity13.c = false;
                                            mainActivity13.e0();
                                            break;
                                        case 25:
                                            MainActivity mainActivity14 = mainActivity3;
                                            k8.g0(mainActivity14, Integer.valueOf(mainActivity14.B0));
                                            break;
                                        case 26:
                                            MainActivity mainActivity15 = mainActivity3;
                                            mainActivity15.a0(mainActivity15.B0);
                                            break;
                                        case 27:
                                            k8.g0(mainActivity3, 0);
                                            break;
                                        case 28:
                                            MainActivity mainActivity16 = mainActivity3;
                                            if (!mainActivity16.h) {
                                                List listH = uh.h();
                                                ArrayList arrayList6 = new ArrayList(k3.n0(listH));
                                                Iterator it3 = listH.iterator();
                                                while (it3.hasNext()) {
                                                    arrayList6.add(((zi) it3.next()).b);
                                                }
                                                List listJ0 = i3.J0(i3.K0(arrayList6));
                                                c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity16).setTitle(si.a("vehicle_brand", new Object[0])).setItems((CharSequence[]) listJ0.toArray(new String[0]), new tb(mainActivity16, listJ0)), null, mainActivity16);
                                            }
                                            break;
                                        default:
                                            MainActivity mainActivity17 = mainActivity3;
                                            boolean z4 = mainActivity17.h;
                                            if (!z4 && !z4) {
                                                try {
                                                    Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                                                    intent.setType("image/*");
                                                    intent.addCategory("android.intent.category.OPENABLE");
                                                    intent.addFlags(1);
                                                    mainActivity17.startActivityForResult(intent, 41);
                                                } catch (ActivityNotFoundException unused) {
                                                    mainActivity17.v0(si.a("no_file_picker_app_on_this_head", new Object[0]));
                                                }
                                            }
                                            break;
                                    }
                                    return ti.a;
                                }
                            }, 8)));
                            mainActivity2 = mainActivity3;
                            r15 = linearLayout;
                        } else {
                            i4 = 0;
                            i5 = 24;
                            linearLayout.addView(c9Var.i(si.a("startup_display_system_and_backup", new Object[i4])));
                            linearLayout.addView(c9Var.b(si.a("start_with_the_head_unit", new Object[i4])));
                            LinearLayout linearLayoutR9 = c9Var.r(si.a("open_after_head_unit_boot", new Object[i4]), null, mainActivity3.B().getBoolean("autostart", true), new ya(mainActivity3, 11));
                            LinearLayout linearLayoutO7 = c9.o(c9Var, si.a("set_ReferencePanel_as_home_screen", new Object[i4]), null, null, new za(mainActivity3, 15), 14);
                            LinearLayout linearLayoutR10 = c9Var.r(si.a("full_screen", new Object[i4]), si.a("hide_head_unit_system_bars", new Object[i4]), mainActivity3.B().getBoolean("fullscreen", true), new ya(mainActivity3, 13));
                            View[] viewArr5 = new View[3];
                            viewArr5[i4] = linearLayoutR9;
                            viewArr5[1] = linearLayoutO7;
                            viewArr5[2] = linearLayoutR10;
                            linearLayout.addView(c9Var.d(viewArr5));
                            linearLayout.addView(c9Var.l(si.a("starts_after_a_reboot_wake_from_sleep", new Object[i4])));
                            linearLayout.addView(c9Var.b(si.a("system_519", new Object[i4])));
                            Object systemService2 = mainActivity3.getSystemService("accessibility");
                            systemService2.getClass();
                            enabledAccessibilityServiceList = ((AccessibilityManager) systemService2).getEnabledAccessibilityServiceList(-1);
                            enabledAccessibilityServiceList.getClass();
                            if (enabledAccessibilityServiceList.isEmpty()) {
                                z3 = false;
                            } else {
                                it = enabledAccessibilityServiceList.iterator();
                                while (true) {
                                    if (it.hasNext()) {
                                        accessibilityServiceInfo = (AccessibilityServiceInfo) it.next();
                                        if (!e(accessibilityServiceInfo.getResolveInfo().serviceInfo.packageName, mainActivity3.getPackageName())) {
                                        }
                                    } else {
                                        z3 = false;
                                    }
                                }
                            }
                            LinearLayout linearLayoutR11 = c9Var.r(si.a("back_and_home_over_apps", new Object[0]), si.a("edge_gestures_or_floating_buttons", new Object[0]), mainActivity3.B().getBoolean("navigation_assist", false), new pb(2, mainActivity3, z3));
                            LinearLayout linearLayoutQ2 = c9Var.q(si.a("controls", new Object[0]), j3.i0(si.a("gestures", new Object[0]), si.a("buttons", new Object[0])), !e(mainActivity3.B().getString("navigation_assist_mode", "gestures"), "gestures") ? 1 : 0, new ya(mainActivity3, i2));
                            String strA13 = si.a("navigation_service", new Object[0]);
                            if (z3) {
                                strA3 = si.a("enabled", new Object[0]);
                            } else {
                                strA3 = si.a("enable_in_accessibility_settings", new Object[0]);
                            }
                            linearLayout.addView(c9Var.d(linearLayoutR11, linearLayoutQ2, c9.o(c9Var, strA13, strA3, null, new za(mainActivity3, i3), 12)));
                            linearLayout.addView(c9Var.l(si.a("start_on_the_strip_at_the_bottom", new Object[0])));
                            LinearLayout linearLayoutO8 = c9.o(c9Var, si.a("media_settings", new Object[0]), null, null, new za(mainActivity3, 17), 14);
                            String strA14 = si.a("android_brightness_control", new Object[0]);
                            if (Settings.System.canWrite(mainActivity3)) {
                                strA4 = si.a("allowed", new Object[0]);
                            } else {
                                strA4 = si.a("allow_system_settings_changes", new Object[0]);
                            }
                            linearLayout.addView(c9Var.d(linearLayoutO8, c9.o(c9Var, strA14, strA4, null, new za(mainActivity3, 18), 12)));
                            linearLayout.addView(c9Var.d(c9Var.r(si.a("hide_stock_climate_popup", new Object[0]), si.a("do_not_show_the_stock_popup_over", new Object[0]), mainActivity3.B().getBoolean("suppress_climate_popup", true), new ya(mainActivity3, 15))));
                            j = mainActivity3.B().getLong("hvac_gate_seen", 0L);
                            if (j > 0) {
                                strA5 = si.a("hvac_gate_active", new SimpleDateFormat("d MMM, HH:mm", si.b.b).format(new Date(j)));
                                i17 = 0;
                            } else {
                                i17 = 0;
                                strA5 = si.a("some_head_units_open_the_stock_climate", new Object[0]);
                            }
                            linearLayout.addView(c9Var.l(strA5));
                            linearLayout.addView(c9Var.b(si.a("updates", new Object[i17])));
                            LinearLayout linearLayoutO9 = c9.o(c9Var, si.a("update_check", new Object[i17]), si.a("update_current_0", "1.0.2"), null, new za(mainActivity3, 19), 12);
                            String strW2 = pi.w(mainActivity3);
                            linearLayout.addView(c9Var.d(linearLayoutO9, c9Var.n(si.a("region_pass_code", new Object[0]), si.a("region_pass_code_hint", new Object[0]), strW2, false, new w7(mainActivity3, strW2, 3)), c9Var.r(si.a("update_auto", new Object[0]), si.a("update_auto_hint", new Object[0]), l0.k(mainActivity3), new ya(mainActivity3, 18)), c9Var.q(si.a("update_installer", new Object[0]), j3.i0(si.a("update_installer_system", new Object[0]), si.a("update_installer_freetuga", new Object[0])), l0.t(mainActivity3), new ya(mainActivity3, 19))));
                            if (l0.t(mainActivity3) != 1) {
                                str4 = "update_installer_system_note";
                            } else if (l0.o(mainActivity3)) {
                                str4 = "update_installer_freetuga_note";
                            } else {
                                str4 = "update_freetuga_missing";
                            }
                            linearLayout.addView(c9Var.l(si.a(str4, new Object[0])));
                            linearLayout.addView(c9Var.b(si.a("backup", new Object[0])));
                            linearLayout.addView(c9Var.d(c9.o(c9Var, si.a("export_settings", new Object[0]), si.a("home_screen_buttons_apps_themes_and_custom", new Object[0]), null, new za(mainActivity3, i5), 12), c9.o(c9Var, si.a("import_settings", new Object[0]), si.a("restore_an_ReferencePanel_backup", new Object[0]), null, new za(mainActivity3, 25), 12)));
                            linearLayout.addView(c9Var.l(si.a("import_replaces_the_current_settings_system_permissions", new Object[0])));
                            linearLayout.addView(c9Var.l(si.a("ReferencePanel_05_appearance_works_offline", "1.0.2")));
                            mainActivity2 = mainActivity3;
                            r15 = linearLayout;
                        }
                        z = true;
                        r3 = mainActivity2;
                        r14 = r15;
                        ?? scrollView2 = new ScrollView(r3);
                        scrollView2.addView(r14);
                        scrollView2.setVerticalScrollBarEnabled(z);
                        r3.k0(scrollView2);
                        scrollView2.post(new o(scrollView2, r3, 9));
                        return c9Var.p(list, r3.u, new ya(r3, 20), new za(r3, 26), scrollView2, new za(r3, 27));
                    case 3005871:
                        list = listI0;
                        i2 = 14;
                        i3 = 16;
                        if (str6.equals("auto")) {
                            linearLayout.addView(pi.e(mainActivity3, c9Var, new mg(mainActivity3, 8)));
                            mainActivity2 = mainActivity3;
                            r15 = linearLayout;
                        } else {
                            i4 = 0;
                            i5 = 24;
                            linearLayout.addView(c9Var.i(si.a("startup_display_system_and_backup", new Object[i4])));
                            linearLayout.addView(c9Var.b(si.a("start_with_the_head_unit", new Object[i4])));
                            LinearLayout linearLayoutR12 = c9Var.r(si.a("open_after_head_unit_boot", new Object[i4]), null, mainActivity3.B().getBoolean("autostart", true), new ya(mainActivity3, 11));
                            LinearLayout linearLayoutO10 = c9.o(c9Var, si.a("set_ReferencePanel_as_home_screen", new Object[i4]), null, null, new za(mainActivity3, 15), 14);
                            LinearLayout linearLayoutR13 = c9Var.r(si.a("full_screen", new Object[i4]), si.a("hide_head_unit_system_bars", new Object[i4]), mainActivity3.B().getBoolean("fullscreen", true), new ya(mainActivity3, 13));
                            View[] viewArr6 = new View[3];
                            viewArr6[i4] = linearLayoutR12;
                            viewArr6[1] = linearLayoutO10;
                            viewArr6[2] = linearLayoutR13;
                            linearLayout.addView(c9Var.d(viewArr6));
                            linearLayout.addView(c9Var.l(si.a("starts_after_a_reboot_wake_from_sleep", new Object[i4])));
                            linearLayout.addView(c9Var.b(si.a("system_519", new Object[i4])));
                            Object systemService3 = mainActivity3.getSystemService("accessibility");
                            systemService3.getClass();
                            enabledAccessibilityServiceList = ((AccessibilityManager) systemService3).getEnabledAccessibilityServiceList(-1);
                            enabledAccessibilityServiceList.getClass();
                            if (enabledAccessibilityServiceList.isEmpty()) {
                                z3 = false;
                            } else {
                                it = enabledAccessibilityServiceList.iterator();
                                while (true) {
                                    if (it.hasNext()) {
                                        accessibilityServiceInfo = (AccessibilityServiceInfo) it.next();
                                        if (!e(accessibilityServiceInfo.getResolveInfo().serviceInfo.packageName, mainActivity3.getPackageName())) {
                                        }
                                    } else {
                                        z3 = false;
                                    }
                                }
                            }
                            LinearLayout linearLayoutR14 = c9Var.r(si.a("back_and_home_over_apps", new Object[0]), si.a("edge_gestures_or_floating_buttons", new Object[0]), mainActivity3.B().getBoolean("navigation_assist", false), new pb(2, mainActivity3, z3));
                            LinearLayout linearLayoutQ3 = c9Var.q(si.a("controls", new Object[0]), j3.i0(si.a("gestures", new Object[0]), si.a("buttons", new Object[0])), !e(mainActivity3.B().getString("navigation_assist_mode", "gestures"), "gestures") ? 1 : 0, new ya(mainActivity3, i2));
                            String strA15 = si.a("navigation_service", new Object[0]);
                            if (z3) {
                                strA3 = si.a("enabled", new Object[0]);
                            } else {
                                strA3 = si.a("enable_in_accessibility_settings", new Object[0]);
                            }
                            linearLayout.addView(c9Var.d(linearLayoutR14, linearLayoutQ3, c9.o(c9Var, strA15, strA3, null, new za(mainActivity3, i3), 12)));
                            linearLayout.addView(c9Var.l(si.a("start_on_the_strip_at_the_bottom", new Object[0])));
                            LinearLayout linearLayoutO11 = c9.o(c9Var, si.a("media_settings", new Object[0]), null, null, new za(mainActivity3, 17), 14);
                            String strA16 = si.a("android_brightness_control", new Object[0]);
                            if (Settings.System.canWrite(mainActivity3)) {
                                strA4 = si.a("allowed", new Object[0]);
                            } else {
                                strA4 = si.a("allow_system_settings_changes", new Object[0]);
                            }
                            linearLayout.addView(c9Var.d(linearLayoutO11, c9.o(c9Var, strA16, strA4, null, new za(mainActivity3, 18), 12)));
                            linearLayout.addView(c9Var.d(c9Var.r(si.a("hide_stock_climate_popup", new Object[0]), si.a("do_not_show_the_stock_popup_over", new Object[0]), mainActivity3.B().getBoolean("suppress_climate_popup", true), new ya(mainActivity3, 15))));
                            j = mainActivity3.B().getLong("hvac_gate_seen", 0L);
                            if (j > 0) {
                                strA5 = si.a("hvac_gate_active", new SimpleDateFormat("d MMM, HH:mm", si.b.b).format(new Date(j)));
                                i17 = 0;
                            } else {
                                i17 = 0;
                                strA5 = si.a("some_head_units_open_the_stock_climate", new Object[0]);
                            }
                            linearLayout.addView(c9Var.l(strA5));
                            linearLayout.addView(c9Var.b(si.a("updates", new Object[i17])));
                            LinearLayout linearLayoutO12 = c9.o(c9Var, si.a("update_check", new Object[i17]), si.a("update_current_0", "1.0.2"), null, new za(mainActivity3, 19), 12);
                            String strW3 = pi.w(mainActivity3);
                            linearLayout.addView(c9Var.d(linearLayoutO12, c9Var.n(si.a("region_pass_code", new Object[0]), si.a("region_pass_code_hint", new Object[0]), strW3, false, new w7(mainActivity3, strW3, 3)), c9Var.r(si.a("update_auto", new Object[0]), si.a("update_auto_hint", new Object[0]), l0.k(mainActivity3), new ya(mainActivity3, 18)), c9Var.q(si.a("update_installer", new Object[0]), j3.i0(si.a("update_installer_system", new Object[0]), si.a("update_installer_freetuga", new Object[0])), l0.t(mainActivity3), new ya(mainActivity3, 19))));
                            if (l0.t(mainActivity3) != 1) {
                                str4 = "update_installer_system_note";
                            } else if (l0.o(mainActivity3)) {
                                str4 = "update_installer_freetuga_note";
                            } else {
                                str4 = "update_freetuga_missing";
                            }
                            linearLayout.addView(c9Var.l(si.a(str4, new Object[0])));
                            linearLayout.addView(c9Var.b(si.a("backup", new Object[0])));
                            linearLayout.addView(c9Var.d(c9.o(c9Var, si.a("export_settings", new Object[0]), si.a("home_screen_buttons_apps_themes_and_custom", new Object[0]), null, new za(mainActivity3, i5), 12), c9.o(c9Var, si.a("import_settings", new Object[0]), si.a("restore_an_ReferencePanel_backup", new Object[0]), null, new za(mainActivity3, 25), 12)));
                            linearLayout.addView(c9Var.l(si.a("import_replaces_the_current_settings_system_permissions", new Object[0])));
                            linearLayout.addView(c9Var.l(si.a("ReferencePanel_05_appearance_works_offline", "1.0.2")));
                            mainActivity2 = mainActivity3;
                            r15 = linearLayout;
                        }
                        z = true;
                        r3 = mainActivity2;
                        r14 = r15;
                        ?? scrollView3 = new ScrollView(r3);
                        scrollView3.addView(r14);
                        scrollView3.setVerticalScrollBarEnabled(z);
                        r3.k0(scrollView3);
                        scrollView3.post(new o(scrollView3, r3, 9));
                        return c9Var.p(list, r3.u, new ya(r3, 20), new za(r3, 26), scrollView3, new za(r3, 27));
                    case 3088947:
                        list = listI0;
                        String str12 = str8;
                        i2 = 14;
                        i3 = 16;
                        i4 = 0;
                        if (str6.equals("dock")) {
                            linearLayout.addView(c9Var.i(si.a("up_to_20_buttons_swipe_sideways_if", new Object[0])));
                            ArrayList arrayListJ1 = j3.j0(c9Var.q(si.a("dock_overlay", new Object[0]), j3.i0(si.a("off_616", new Object[0]), si.a("dock_overlay_split_only", new Object[0]), si.a("dock_overlay_everywhere", new Object[0])), mainActivity3.v(), new lg(mainActivity3, i4)));
                            if (mainActivity3.v() > 0) {
                                boolean z4 = q.a;
                                if (q.a(mainActivity3)) {
                                    i9 = i18;
                                } else {
                                    i9 = i18;
                                    arrayListJ1.add(c9.o(c9Var, si.a("anton_popup_allow", new Object[0]), si.a("dock_overlay_allow_hint", new Object[0]), null, new mg(mainActivity3, 5), 12));
                                }
                            } else {
                                i9 = i18;
                            }
                            View[] viewArr7 = (View[]) arrayListJ1.toArray(new View[0]);
                            linearLayout.addView(c9Var.d((View[]) Arrays.copyOf(viewArr7, viewArr7.length)));
                            if (mainActivity3.v() > 0) {
                                linearLayout.addView(c9Var.l(si.a(mainActivity3.v() == 1 ? "dock_overlay_split_hint" : "dock_overlay_hint", new Object[0])));
                                linearLayout.addView(c9Var.l(si.a("dock_overlay_split", new Object[0])));
                            }
                            linearLayout.addView(c9Var.b(""));
                            final List listI1 = j3.i0("s", "m", "l");
                            String strA17 = si.a("bar_size", new Object[0]);
                            List listI2 = j3.i0(si.a("compact", new Object[0]), si.a("normal", new Object[0]), si.a("large", new Object[0]));
                            String string = mainActivity3.B().getString("dock_size", "m");
                            int iIndexOf = listI1.indexOf(string != null ? string : "m");
                            if (iIndexOf < 0) {
                                iIndexOf = 1;
                            }
                            final int i34 = 3;
                            LinearLayout linearLayoutQ4 = c9Var.q(strA17, listI2, iIndexOf, new j6() { // from class: cg
                                @Override // defpackage.j6
                                public final Object b(Object obj4) {
                                    int i35 = i34;
                                    ti tiVar = ti.a;
                                    List list2 = listI1;
                                    MainActivity mainActivity4 = mainActivity3;
                                    int iIntValue = ((Integer) obj4).intValue();
                                    switch (i35) {
                                        case 0:
                                            mainActivity4.B().edit().putInt("saver_dim", ((Number) list2.get(iIntValue)).intValue()).apply();
                                            mainActivity4.e0();
                                            break;
                                        case 1:
                                            mainActivity4.B().edit().putString("theme_mode", (String) list2.get(iIntValue)).apply();
                                            mainActivity4.e0();
                                            break;
                                        case 2:
                                            mainActivity4.B().edit().putInt("saver_minutes", ((Number) list2.get(iIntValue)).intValue()).apply();
                                            mainActivity4.h0();
                                            mainActivity4.e0();
                                            break;
                                        default:
                                            String str13 = (String) list2.get(iIntValue);
                                            str13.getClass();
                                            int i36 = str13.equals("s") ? 104 : str13.equals("l") ? 152 : 128;
                                            int i37 = 700 - i36;
                                            float f2 = i37 - 16.0f;
                                            SharedPreferences.Editor editorPutString = mainActivity4.B().edit().putString("dock_size", str13).putString("desktop_ios_dock", "40.0," + i37 + ".0,1200.0," + i36 + ".0");
                                            for (String str14 : mainActivity4.m0) {
                                                Iterator it3 = j3.i0("ios_", "wall_").iterator();
                                                while (it3.hasNext()) {
                                                    String str15 = ((String) it3.next()) + str14;
                                                    String string2 = mainActivity4.B().getString("desktop_".concat(str15), null);
                                                    if (string2 != null) {
                                                        List listD2 = th.D(string2, new char[]{','});
                                                        ArrayList arrayList = new ArrayList();
                                                        Iterator it4 = listD2.iterator();
                                                        while (it4.hasNext()) {
                                                            Float fP = ai.p((String) it4.next());
                                                            if (fP != null) {
                                                                arrayList.add(fP);
                                                            }
                                                        }
                                                        ArrayList arrayList2 = arrayList.size() == 4 ? arrayList : null;
                                                        if (arrayList2 != null && ((Number) arrayList2.get(1)).floatValue() < f2) {
                                                            if (((Number) arrayList2.get(3)).floatValue() + ((Number) arrayList2.get(1)).floatValue() >= f2 - 40.0f) {
                                                                String strConcat = "desktop_".concat(str15);
                                                                Object obj5 = arrayList2.get(0);
                                                                Object obj6 = arrayList2.get(1);
                                                                Object obj7 = arrayList2.get(2);
                                                                float fFloatValue = f2 - ((Number) arrayList2.get(1)).floatValue();
                                                                editorPutString.putString(strConcat, obj5 + "," + obj6 + "," + obj7 + "," + (fFloatValue >= 40.0f ? fFloatValue : 40.0f));
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            editorPutString.apply();
                                            mainActivity4.e0();
                                            break;
                                    }
                                    return tiVar;
                                }
                            });
                            LinearLayout linearLayoutQ5 = c9Var.q(si.a("icon_arrangement", new Object[0]), j3.i0(si.a("left", new Object[0]), si.a("evenly", new Object[0])), e(mainActivity3.B().getString("dock_alignment", "left"), "spread") ? 1 : 0, new ya(mainActivity3, 12));
                            LinearLayout linearLayoutR15 = c9Var.r(si.a("icon_labels", new Object[0]), si.a("without_labels_icons_are_larger_and_centered", new Object[0]), mainActivity3.B().getBoolean("menu_labels", false), new ya(mainActivity3, i3));
                            String strA18 = si.a("dock_transparency", new Object[0]);
                            int i35 = zj.a;
                            SharedPreferences sharedPreferencesB = mainActivity3.B();
                            sharedPreferencesB.getClass();
                            linearLayout.addView(c9Var.d(linearLayoutQ4, linearLayoutQ5, linearLayoutR15, c9.o(c9Var, strA18, null, si.a("screensaver_percent", Integer.valueOf(zj.c(sharedPreferencesB, "dock_transparency"))), new za(mainActivity3, i9), 8)));
                            linearLayout.addView(c9Var.l(si.a("evenly_spreads_buttons_across_the_full_bar", new Object[0])));
                            linearLayout.addView(c9Var.d(c9.o(c9Var, si.a("add_widget", new Object[0]), si.a("widget_library", new Object[0]), null, new za(mainActivity3, 29), 12)));
                            linearLayout.addView(c9Var.b(si.a("buttons_0_of_20", Integer.valueOf(j(mainActivity3)))));
                            ArrayList arrayListT = T(mainActivity3);
                            ArrayList arrayList = new ArrayList(k3.n0(arrayListT));
                            int size = arrayListT.size();
                            int i36 = 0;
                            int i37 = 0;
                            while (i37 < size) {
                                Object obj4 = arrayListT.get(i37);
                                i37++;
                                int i38 = i36 + 1;
                                if (i36 < 0) {
                                    j3.m0();
                                    throw null;
                                }
                                String str13 = (String) obj4;
                                View viewM = mainActivity3.M(str13);
                                if (viewM instanceof d9) {
                                    int i39 = l8.a;
                                    ((d9) viewM).setColor(l8.j(mainActivity3.z()));
                                }
                                int i40 = size;
                                String str14 = str12;
                                arrayList.add(c9Var.f(viewM, mainActivity3.W(str13), j3.i0(c9Var.j(str12, i36 > 0, false, new c3(mainActivity3, i36, 6)), c9Var.j(str7, i36 < j3.h0(arrayListT), false, new c3(mainActivity3, i36, 7)), c9Var.j("minus", arrayListT.size() > 1, true, new c3(mainActivity3, i36, 8))), new c3(mainActivity3, i36, 9)));
                                i36 = i38;
                                size = i40;
                                arrayListT = arrayListT;
                                str12 = str14;
                            }
                            final int i41 = 0;
                            View[] viewArr8 = (View[]) arrayList.toArray(new View[0]);
                            linearLayout.addView(c9Var.d((View[]) Arrays.copyOf(viewArr8, viewArr8.length)));
                            linearLayout.addView(c9Var.b(""));
                            final int i42 = 1;
                            linearLayout.addView(c9Var.d(c9.o(c9Var, si.a("add_button_451", new Object[0]), j(mainActivity3) >= 20 ? si.a("maximum_reached", new Object[0]) : si.a("climate_modes_vehicle_apps", new Object[0]), null, new y5() { // from class: jg
                                @Override // defpackage.y5
                                public final Object a() {
                                    String strY0;
                                    switch (i41) {
                                        case 0:
                                            k8.d(mainActivity3);
                                            break;
                                        case 1:
                                            k8.c(mainActivity3, null);
                                            break;
                                        case 2:
                                            MainActivity mainActivity4 = mainActivity3;
                                            ArrayList arrayListC = j0.c();
                                            AlertDialog.Builder title = new AlertDialog.Builder(mainActivity4).setTitle(si.a("app_language_choose", new Object[0]));
                                            ArrayList arrayList2 = new ArrayList(k3.n0(arrayListC));
                                            int size2 = arrayListC.size();
                                            int i210 = 0;
                                            while (i210 < size2) {
                                                Object obj5 = arrayListC.get(i210);
                                                i210++;
                                                Object obj6 = (String) obj5;
                                                List list2 = j0.a;
                                                obj6.getClass();
                                                List list3 = j0.a;
                                                int iIndexOf2 = si.a.indexOf(obj6);
                                                if (iIndexOf2 >= 0 && iIndexOf2 < list3.size()) {
                                                    obj6 = list3.get(iIndexOf2);
                                                }
                                                arrayList2.add((String) obj6);
                                            }
                                            CharSequence[] charSequenceArr = (CharSequence[]) arrayList2.toArray(new String[0]);
                                            List list4 = si.a;
                                            AlertDialog.Builder negativeButton = title.setSingleChoiceItems(charSequenceArr, arrayListC.indexOf(si.b.a), new dg(arrayListC, mainActivity4)).setNegativeButton(si.a("cancel", new Object[0]), (DialogInterface.OnClickListener) null);
                                            negativeButton.getClass();
                                            k8.w0(negativeButton);
                                            break;
                                        case 3:
                                            MainActivity mainActivity5 = mainActivity3;
                                            mainActivity5.c = false;
                                            mainActivity5.f = true;
                                            mainActivity5.e0();
                                            mainActivity5.X(new j(14));
                                            break;
                                        case 4:
                                            k8.v0("elastre", mainActivity3);
                                            break;
                                        case 5:
                                            k8.v0("manrope", mainActivity3);
                                            break;
                                        case 6:
                                            k8.v0("custom", mainActivity3);
                                            break;
                                        case 7:
                                            k8.K(mainActivity3, true);
                                            break;
                                        case 8:
                                            MainActivity mainActivity6 = mainActivity3;
                                            x3.d(mainActivity6).delete();
                                            SharedPreferences sharedPreferences = mainActivity6.getSharedPreferences("panel", 0);
                                            SharedPreferences.Editor editorRemove = sharedPreferences.edit().remove("font_custom_name").remove("font_custom_kk").remove("font_custom_wght");
                                            if (k8.e(sharedPreferences.getString("font_choice", ""), "custom")) {
                                                editorRemove.remove("font_choice");
                                            }
                                            editorRemove.apply();
                                            z9.d(mainActivity6);
                                            z9.a = true;
                                            mainActivity6.e0();
                                            break;
                                        case 9:
                                            MainActivity mainActivity7 = mainActivity3;
                                            i7.g(mainActivity7, "");
                                            mainActivity7.e0();
                                            break;
                                        case 10:
                                            k8.O(mainActivity3, true, true);
                                            break;
                                        case 11:
                                            k8.O(mainActivity3, false, true);
                                            break;
                                        case 12:
                                            k8.Q(mainActivity3, false);
                                            break;
                                        case 13:
                                            k8.Q(mainActivity3, true);
                                            break;
                                        case 14:
                                            MainActivity mainActivity8 = mainActivity3;
                                            new i0(mainActivity8, mainActivity8.z(), si.a("split_left", new Object[0]), false, new lg(mainActivity8, 7)).show();
                                            break;
                                        case 15:
                                            k8.C(mainActivity3);
                                            break;
                                        case 16:
                                            MainActivity mainActivity9 = mainActivity3;
                                            mainActivity9.u = "dock";
                                            mainActivity9.s = null;
                                            mainActivity9.t = 0;
                                            mainActivity9.e0();
                                            break;
                                        case 17:
                                            k8.g0(mainActivity3, 1);
                                            break;
                                        case 18:
                                            MainActivity mainActivity10 = mainActivity3;
                                            md mdVar2 = md.a;
                                            List list5 = md.d;
                                            if (list5 == null) {
                                                mdVar2.c(mainActivity10);
                                                list5 = u4.a;
                                            }
                                            List list6 = list5;
                                            if (list6.isEmpty()) {
                                                mainActivity10.v0(si.a("radio_city_loading", new Object[0]));
                                            } else {
                                                Map mapJ0 = hb.j0(new yc("RU", si.a("country_ru", new Object[0])), new yc("BY", si.a("country_by", new Object[0])), new yc("KZ", si.a("country_kz", new Object[0])));
                                                i4 i4Var = new i4(mainActivity10, mainActivity10.z());
                                                ViewGroup viewGroupL = mainActivity10.l();
                                                EditText editText = new EditText(mainActivity10);
                                                editText.setHint(si.a("radio_city_search", new Object[0]));
                                                editText.setTextSize(20.0f);
                                                editText.setTextColor(mainActivity10.o);
                                                editText.setHintTextColor(mainActivity10.p);
                                                editText.setSingleLine(true);
                                                editText.setBackground(mainActivity10.m0(mainActivity10.q, 14));
                                                editText.setPadding(mainActivity10.r(16), 0, mainActivity10.r(16), 0);
                                                viewGroupL.addView(editText, new LinearLayout.LayoutParams(-1, mainActivity10.r(60)));
                                                LinearLayout linearLayoutL = mainActivity10.l();
                                                ScrollView scrollView4 = new ScrollView(mainActivity10);
                                                scrollView4.addView(linearLayoutL);
                                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, mainActivity10.r(420));
                                                layoutParams.topMargin = mainActivity10.r(10);
                                                viewGroupL.addView(scrollView4, layoutParams);
                                                Dialog dialogF = i4Var.f(si.a("radio_city", new Object[0]), si.a("radio_city_hint", new Object[0]), viewGroupL);
                                                editText.addTextChangedListener(new f0(linearLayoutL, i4Var, mainActivity10, list6, dialogF, mapJ0, 1));
                                                k8.k0(linearLayoutL, i4Var, mainActivity10, list6, dialogF, mapJ0, "");
                                                dialogF.show();
                                            }
                                            break;
                                        case 19:
                                            mainActivity3.f0();
                                            break;
                                        case 20:
                                            MainActivity mainActivity11 = mainActivity3;
                                            i4 i4Var2 = new i4(mainActivity11, mainActivity11.z());
                                            TextView textView = new TextView(mainActivity11);
                                            gc gcVarY = mainActivity11.y();
                                            if (gcVarY.n) {
                                                ArrayList arrayList3 = gcVarY.h;
                                                ArrayList arrayList4 = new ArrayList(k3.n0(arrayList3));
                                                int size3 = arrayList3.size();
                                                int i211 = 0;
                                                while (i211 < size3) {
                                                    Object obj7 = arrayList3.get(i211);
                                                    i211++;
                                                    arrayList4.add((MediaController) ((yc) obj7).a);
                                                }
                                                Object obj8 = MusicNotificationListener.a;
                                                ArrayList arrayListY = k8.Y();
                                                if (arrayList4.isEmpty() && arrayListY.isEmpty()) {
                                                    strY0 = si.a("no_media_sessions_start_radio_or_bluetooth", new Object[0]);
                                                } else {
                                                    List listI3 = j3.i0(i3.y0(arrayList4, "\n\n", new a(6, gcVarY), 30), i3.y0(arrayListY, "\n", new w(18), 30));
                                                    ArrayList arrayList5 = new ArrayList();
                                                    for (Object obj9 : listI3) {
                                                        if (!th.A((String) obj9)) {
                                                            arrayList5.add(obj9);
                                                        }
                                                    }
                                                    strY0 = i3.y0(arrayList5, "\n\n", null, 62);
                                                }
                                            } else {
                                                strY0 = si.a("no_music_access_enable_ReferencePanel_music_information", new Object[0]);
                                            }
                                            textView.setText(strY0);
                                            textView.setTextSize(17.0f);
                                            int i212 = l8.a;
                                            textView.setTextColor(l8.j(mainActivity11.z()));
                                            textView.setTextIsSelectable(true);
                                            textView.setLineSpacing(0.0f, 1.2f);
                                            String strA19 = si.a("music_diagnostics", new Object[0]);
                                            String strA110 = si.a("source_currently_shown_by_the_widget", new Object[0]);
                                            ScrollView scrollView5 = new ScrollView(mainActivity11);
                                            scrollView5.addView(textView);
                                            i4Var2.f(strA19, strA110, scrollView5).show();
                                            break;
                                        case 21:
                                            k8.m0(mainActivity3);
                                            break;
                                        case 22:
                                            mainActivity3.a0(0);
                                            break;
                                        case 23:
                                            MainActivity mainActivity12 = mainActivity3;
                                            ComponentName componentNameM = mainActivity12.m(0);
                                            if (componentNameM == null) {
                                                k8.g0(mainActivity12, 0);
                                            } else {
                                                mainActivity12.Q(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", componentNameM.getPackageName(), null)));
                                            }
                                            break;
                                        case 24:
                                            MainActivity mainActivity13 = mainActivity3;
                                            ArrayList arrayList6 = mainActivity13.w;
                                            int size4 = arrayList6.size();
                                            int i213 = 0;
                                            while (i213 < size4) {
                                                Object obj10 = arrayList6.get(i213);
                                                i213++;
                                                ((b) obj10).c();
                                            }
                                            arrayList6.clear();
                                            mainActivity13.c = false;
                                            mainActivity13.e0();
                                            break;
                                        case 25:
                                            MainActivity mainActivity14 = mainActivity3;
                                            k8.g0(mainActivity14, Integer.valueOf(mainActivity14.B0));
                                            break;
                                        case 26:
                                            MainActivity mainActivity15 = mainActivity3;
                                            mainActivity15.a0(mainActivity15.B0);
                                            break;
                                        case 27:
                                            k8.g0(mainActivity3, 0);
                                            break;
                                        case 28:
                                            MainActivity mainActivity16 = mainActivity3;
                                            if (!mainActivity16.h) {
                                                List listH = uh.h();
                                                ArrayList arrayList7 = new ArrayList(k3.n0(listH));
                                                Iterator it3 = listH.iterator();
                                                while (it3.hasNext()) {
                                                    arrayList7.add(((zi) it3.next()).b);
                                                }
                                                List listJ0 = i3.J0(i3.K0(arrayList7));
                                                c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity16).setTitle(si.a("vehicle_brand", new Object[0])).setItems((CharSequence[]) listJ0.toArray(new String[0]), new tb(mainActivity16, listJ0)), null, mainActivity16);
                                            }
                                            break;
                                        default:
                                            MainActivity mainActivity17 = mainActivity3;
                                            boolean z5 = mainActivity17.h;
                                            if (!z5 && !z5) {
                                                try {
                                                    Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                                                    intent.setType("image/*");
                                                    intent.addCategory("android.intent.category.OPENABLE");
                                                    intent.addFlags(1);
                                                    mainActivity17.startActivityForResult(intent, 41);
                                                } catch (ActivityNotFoundException unused) {
                                                    mainActivity17.v0(si.a("no_file_picker_app_on_this_head", new Object[0]));
                                                }
                                            }
                                            break;
                                    }
                                    return ti.a;
                                }
                            }, 12), c9.o(c9Var, si.a("dock_add_gap", new Object[0]), si.a("dock_add_gap_hint", new Object[0]), null, new y5() { // from class: jg
                                @Override // defpackage.y5
                                public final Object a() {
                                    String strY0;
                                    switch (i42) {
                                        case 0:
                                            k8.d(mainActivity3);
                                            break;
                                        case 1:
                                            k8.c(mainActivity3, null);
                                            break;
                                        case 2:
                                            MainActivity mainActivity4 = mainActivity3;
                                            ArrayList arrayListC = j0.c();
                                            AlertDialog.Builder title = new AlertDialog.Builder(mainActivity4).setTitle(si.a("app_language_choose", new Object[0]));
                                            ArrayList arrayList2 = new ArrayList(k3.n0(arrayListC));
                                            int size2 = arrayListC.size();
                                            int i210 = 0;
                                            while (i210 < size2) {
                                                Object obj5 = arrayListC.get(i210);
                                                i210++;
                                                Object obj6 = (String) obj5;
                                                List list2 = j0.a;
                                                obj6.getClass();
                                                List list3 = j0.a;
                                                int iIndexOf2 = si.a.indexOf(obj6);
                                                if (iIndexOf2 >= 0 && iIndexOf2 < list3.size()) {
                                                    obj6 = list3.get(iIndexOf2);
                                                }
                                                arrayList2.add((String) obj6);
                                            }
                                            CharSequence[] charSequenceArr = (CharSequence[]) arrayList2.toArray(new String[0]);
                                            List list4 = si.a;
                                            AlertDialog.Builder negativeButton = title.setSingleChoiceItems(charSequenceArr, arrayListC.indexOf(si.b.a), new dg(arrayListC, mainActivity4)).setNegativeButton(si.a("cancel", new Object[0]), (DialogInterface.OnClickListener) null);
                                            negativeButton.getClass();
                                            k8.w0(negativeButton);
                                            break;
                                        case 3:
                                            MainActivity mainActivity5 = mainActivity3;
                                            mainActivity5.c = false;
                                            mainActivity5.f = true;
                                            mainActivity5.e0();
                                            mainActivity5.X(new j(14));
                                            break;
                                        case 4:
                                            k8.v0("elastre", mainActivity3);
                                            break;
                                        case 5:
                                            k8.v0("manrope", mainActivity3);
                                            break;
                                        case 6:
                                            k8.v0("custom", mainActivity3);
                                            break;
                                        case 7:
                                            k8.K(mainActivity3, true);
                                            break;
                                        case 8:
                                            MainActivity mainActivity6 = mainActivity3;
                                            x3.d(mainActivity6).delete();
                                            SharedPreferences sharedPreferences = mainActivity6.getSharedPreferences("panel", 0);
                                            SharedPreferences.Editor editorRemove = sharedPreferences.edit().remove("font_custom_name").remove("font_custom_kk").remove("font_custom_wght");
                                            if (k8.e(sharedPreferences.getString("font_choice", ""), "custom")) {
                                                editorRemove.remove("font_choice");
                                            }
                                            editorRemove.apply();
                                            z9.d(mainActivity6);
                                            z9.a = true;
                                            mainActivity6.e0();
                                            break;
                                        case 9:
                                            MainActivity mainActivity7 = mainActivity3;
                                            i7.g(mainActivity7, "");
                                            mainActivity7.e0();
                                            break;
                                        case 10:
                                            k8.O(mainActivity3, true, true);
                                            break;
                                        case 11:
                                            k8.O(mainActivity3, false, true);
                                            break;
                                        case 12:
                                            k8.Q(mainActivity3, false);
                                            break;
                                        case 13:
                                            k8.Q(mainActivity3, true);
                                            break;
                                        case 14:
                                            MainActivity mainActivity8 = mainActivity3;
                                            new i0(mainActivity8, mainActivity8.z(), si.a("split_left", new Object[0]), false, new lg(mainActivity8, 7)).show();
                                            break;
                                        case 15:
                                            k8.C(mainActivity3);
                                            break;
                                        case 16:
                                            MainActivity mainActivity9 = mainActivity3;
                                            mainActivity9.u = "dock";
                                            mainActivity9.s = null;
                                            mainActivity9.t = 0;
                                            mainActivity9.e0();
                                            break;
                                        case 17:
                                            k8.g0(mainActivity3, 1);
                                            break;
                                        case 18:
                                            MainActivity mainActivity10 = mainActivity3;
                                            md mdVar2 = md.a;
                                            List list5 = md.d;
                                            if (list5 == null) {
                                                mdVar2.c(mainActivity10);
                                                list5 = u4.a;
                                            }
                                            List list6 = list5;
                                            if (list6.isEmpty()) {
                                                mainActivity10.v0(si.a("radio_city_loading", new Object[0]));
                                            } else {
                                                Map mapJ0 = hb.j0(new yc("RU", si.a("country_ru", new Object[0])), new yc("BY", si.a("country_by", new Object[0])), new yc("KZ", si.a("country_kz", new Object[0])));
                                                i4 i4Var = new i4(mainActivity10, mainActivity10.z());
                                                ViewGroup viewGroupL = mainActivity10.l();
                                                EditText editText = new EditText(mainActivity10);
                                                editText.setHint(si.a("radio_city_search", new Object[0]));
                                                editText.setTextSize(20.0f);
                                                editText.setTextColor(mainActivity10.o);
                                                editText.setHintTextColor(mainActivity10.p);
                                                editText.setSingleLine(true);
                                                editText.setBackground(mainActivity10.m0(mainActivity10.q, 14));
                                                editText.setPadding(mainActivity10.r(16), 0, mainActivity10.r(16), 0);
                                                viewGroupL.addView(editText, new LinearLayout.LayoutParams(-1, mainActivity10.r(60)));
                                                LinearLayout linearLayoutL = mainActivity10.l();
                                                ScrollView scrollView4 = new ScrollView(mainActivity10);
                                                scrollView4.addView(linearLayoutL);
                                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, mainActivity10.r(420));
                                                layoutParams.topMargin = mainActivity10.r(10);
                                                viewGroupL.addView(scrollView4, layoutParams);
                                                Dialog dialogF = i4Var.f(si.a("radio_city", new Object[0]), si.a("radio_city_hint", new Object[0]), viewGroupL);
                                                editText.addTextChangedListener(new f0(linearLayoutL, i4Var, mainActivity10, list6, dialogF, mapJ0, 1));
                                                k8.k0(linearLayoutL, i4Var, mainActivity10, list6, dialogF, mapJ0, "");
                                                dialogF.show();
                                            }
                                            break;
                                        case 19:
                                            mainActivity3.f0();
                                            break;
                                        case 20:
                                            MainActivity mainActivity11 = mainActivity3;
                                            i4 i4Var2 = new i4(mainActivity11, mainActivity11.z());
                                            TextView textView = new TextView(mainActivity11);
                                            gc gcVarY = mainActivity11.y();
                                            if (gcVarY.n) {
                                                ArrayList arrayList3 = gcVarY.h;
                                                ArrayList arrayList4 = new ArrayList(k3.n0(arrayList3));
                                                int size3 = arrayList3.size();
                                                int i211 = 0;
                                                while (i211 < size3) {
                                                    Object obj7 = arrayList3.get(i211);
                                                    i211++;
                                                    arrayList4.add((MediaController) ((yc) obj7).a);
                                                }
                                                Object obj8 = MusicNotificationListener.a;
                                                ArrayList arrayListY = k8.Y();
                                                if (arrayList4.isEmpty() && arrayListY.isEmpty()) {
                                                    strY0 = si.a("no_media_sessions_start_radio_or_bluetooth", new Object[0]);
                                                } else {
                                                    List listI3 = j3.i0(i3.y0(arrayList4, "\n\n", new a(6, gcVarY), 30), i3.y0(arrayListY, "\n", new w(18), 30));
                                                    ArrayList arrayList5 = new ArrayList();
                                                    for (Object obj9 : listI3) {
                                                        if (!th.A((String) obj9)) {
                                                            arrayList5.add(obj9);
                                                        }
                                                    }
                                                    strY0 = i3.y0(arrayList5, "\n\n", null, 62);
                                                }
                                            } else {
                                                strY0 = si.a("no_music_access_enable_ReferencePanel_music_information", new Object[0]);
                                            }
                                            textView.setText(strY0);
                                            textView.setTextSize(17.0f);
                                            int i212 = l8.a;
                                            textView.setTextColor(l8.j(mainActivity11.z()));
                                            textView.setTextIsSelectable(true);
                                            textView.setLineSpacing(0.0f, 1.2f);
                                            String strA19 = si.a("music_diagnostics", new Object[0]);
                                            String strA110 = si.a("source_currently_shown_by_the_widget", new Object[0]);
                                            ScrollView scrollView5 = new ScrollView(mainActivity11);
                                            scrollView5.addView(textView);
                                            i4Var2.f(strA19, strA110, scrollView5).show();
                                            break;
                                        case 21:
                                            k8.m0(mainActivity3);
                                            break;
                                        case 22:
                                            mainActivity3.a0(0);
                                            break;
                                        case 23:
                                            MainActivity mainActivity12 = mainActivity3;
                                            ComponentName componentNameM = mainActivity12.m(0);
                                            if (componentNameM == null) {
                                                k8.g0(mainActivity12, 0);
                                            } else {
                                                mainActivity12.Q(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", componentNameM.getPackageName(), null)));
                                            }
                                            break;
                                        case 24:
                                            MainActivity mainActivity13 = mainActivity3;
                                            ArrayList arrayList6 = mainActivity13.w;
                                            int size4 = arrayList6.size();
                                            int i213 = 0;
                                            while (i213 < size4) {
                                                Object obj10 = arrayList6.get(i213);
                                                i213++;
                                                ((b) obj10).c();
                                            }
                                            arrayList6.clear();
                                            mainActivity13.c = false;
                                            mainActivity13.e0();
                                            break;
                                        case 25:
                                            MainActivity mainActivity14 = mainActivity3;
                                            k8.g0(mainActivity14, Integer.valueOf(mainActivity14.B0));
                                            break;
                                        case 26:
                                            MainActivity mainActivity15 = mainActivity3;
                                            mainActivity15.a0(mainActivity15.B0);
                                            break;
                                        case 27:
                                            k8.g0(mainActivity3, 0);
                                            break;
                                        case 28:
                                            MainActivity mainActivity16 = mainActivity3;
                                            if (!mainActivity16.h) {
                                                List listH = uh.h();
                                                ArrayList arrayList7 = new ArrayList(k3.n0(listH));
                                                Iterator it3 = listH.iterator();
                                                while (it3.hasNext()) {
                                                    arrayList7.add(((zi) it3.next()).b);
                                                }
                                                List listJ0 = i3.J0(i3.K0(arrayList7));
                                                c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity16).setTitle(si.a("vehicle_brand", new Object[0])).setItems((CharSequence[]) listJ0.toArray(new String[0]), new tb(mainActivity16, listJ0)), null, mainActivity16);
                                            }
                                            break;
                                        default:
                                            MainActivity mainActivity17 = mainActivity3;
                                            boolean z5 = mainActivity17.h;
                                            if (!z5 && !z5) {
                                                try {
                                                    Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                                                    intent.setType("image/*");
                                                    intent.addCategory("android.intent.category.OPENABLE");
                                                    intent.addFlags(1);
                                                    mainActivity17.startActivityForResult(intent, 41);
                                                } catch (ActivityNotFoundException unused) {
                                                    mainActivity17.v0(si.a("no_file_picker_app_on_this_head", new Object[0]));
                                                }
                                            }
                                            break;
                                    }
                                    return ti.a;
                                }
                            }, 12)));
                            mainActivity2 = mainActivity3;
                            r15 = linearLayout;
                        } else {
                            i5 = 24;
                            linearLayout.addView(c9Var.i(si.a("startup_display_system_and_backup", new Object[i4])));
                            linearLayout.addView(c9Var.b(si.a("start_with_the_head_unit", new Object[i4])));
                            LinearLayout linearLayoutR16 = c9Var.r(si.a("open_after_head_unit_boot", new Object[i4]), null, mainActivity3.B().getBoolean("autostart", true), new ya(mainActivity3, 11));
                            LinearLayout linearLayoutO13 = c9.o(c9Var, si.a("set_ReferencePanel_as_home_screen", new Object[i4]), null, null, new za(mainActivity3, 15), 14);
                            LinearLayout linearLayoutR17 = c9Var.r(si.a("full_screen", new Object[i4]), si.a("hide_head_unit_system_bars", new Object[i4]), mainActivity3.B().getBoolean("fullscreen", true), new ya(mainActivity3, 13));
                            View[] viewArr9 = new View[3];
                            viewArr9[i4] = linearLayoutR16;
                            viewArr9[1] = linearLayoutO13;
                            viewArr9[2] = linearLayoutR17;
                            linearLayout.addView(c9Var.d(viewArr9));
                            linearLayout.addView(c9Var.l(si.a("starts_after_a_reboot_wake_from_sleep", new Object[i4])));
                            linearLayout.addView(c9Var.b(si.a("system_519", new Object[i4])));
                            Object systemService4 = mainActivity3.getSystemService("accessibility");
                            systemService4.getClass();
                            enabledAccessibilityServiceList = ((AccessibilityManager) systemService4).getEnabledAccessibilityServiceList(-1);
                            enabledAccessibilityServiceList.getClass();
                            if (enabledAccessibilityServiceList.isEmpty()) {
                                z3 = false;
                            } else {
                                it = enabledAccessibilityServiceList.iterator();
                                while (true) {
                                    if (it.hasNext()) {
                                        accessibilityServiceInfo = (AccessibilityServiceInfo) it.next();
                                        if (!e(accessibilityServiceInfo.getResolveInfo().serviceInfo.packageName, mainActivity3.getPackageName())) {
                                        }
                                    } else {
                                        z3 = false;
                                    }
                                }
                            }
                            LinearLayout linearLayoutR18 = c9Var.r(si.a("back_and_home_over_apps", new Object[0]), si.a("edge_gestures_or_floating_buttons", new Object[0]), mainActivity3.B().getBoolean("navigation_assist", false), new pb(2, mainActivity3, z3));
                            LinearLayout linearLayoutQ6 = c9Var.q(si.a("controls", new Object[0]), j3.i0(si.a("gestures", new Object[0]), si.a("buttons", new Object[0])), !e(mainActivity3.B().getString("navigation_assist_mode", "gestures"), "gestures") ? 1 : 0, new ya(mainActivity3, i2));
                            String strA19 = si.a("navigation_service", new Object[0]);
                            if (z3) {
                                strA3 = si.a("enabled", new Object[0]);
                            } else {
                                strA3 = si.a("enable_in_accessibility_settings", new Object[0]);
                            }
                            linearLayout.addView(c9Var.d(linearLayoutR18, linearLayoutQ6, c9.o(c9Var, strA19, strA3, null, new za(mainActivity3, i3), 12)));
                            linearLayout.addView(c9Var.l(si.a("start_on_the_strip_at_the_bottom", new Object[0])));
                            LinearLayout linearLayoutO14 = c9.o(c9Var, si.a("media_settings", new Object[0]), null, null, new za(mainActivity3, 17), 14);
                            String strA110 = si.a("android_brightness_control", new Object[0]);
                            if (Settings.System.canWrite(mainActivity3)) {
                                strA4 = si.a("allowed", new Object[0]);
                            } else {
                                strA4 = si.a("allow_system_settings_changes", new Object[0]);
                            }
                            linearLayout.addView(c9Var.d(linearLayoutO14, c9.o(c9Var, strA110, strA4, null, new za(mainActivity3, 18), 12)));
                            linearLayout.addView(c9Var.d(c9Var.r(si.a("hide_stock_climate_popup", new Object[0]), si.a("do_not_show_the_stock_popup_over", new Object[0]), mainActivity3.B().getBoolean("suppress_climate_popup", true), new ya(mainActivity3, 15))));
                            j = mainActivity3.B().getLong("hvac_gate_seen", 0L);
                            if (j > 0) {
                                strA5 = si.a("hvac_gate_active", new SimpleDateFormat("d MMM, HH:mm", si.b.b).format(new Date(j)));
                                i17 = 0;
                            } else {
                                i17 = 0;
                                strA5 = si.a("some_head_units_open_the_stock_climate", new Object[0]);
                            }
                            linearLayout.addView(c9Var.l(strA5));
                            linearLayout.addView(c9Var.b(si.a("updates", new Object[i17])));
                            LinearLayout linearLayoutO15 = c9.o(c9Var, si.a("update_check", new Object[i17]), si.a("update_current_0", "1.0.2"), null, new za(mainActivity3, 19), 12);
                            String strW4 = pi.w(mainActivity3);
                            linearLayout.addView(c9Var.d(linearLayoutO15, c9Var.n(si.a("region_pass_code", new Object[0]), si.a("region_pass_code_hint", new Object[0]), strW4, false, new w7(mainActivity3, strW4, 3)), c9Var.r(si.a("update_auto", new Object[0]), si.a("update_auto_hint", new Object[0]), l0.k(mainActivity3), new ya(mainActivity3, 18)), c9Var.q(si.a("update_installer", new Object[0]), j3.i0(si.a("update_installer_system", new Object[0]), si.a("update_installer_freetuga", new Object[0])), l0.t(mainActivity3), new ya(mainActivity3, 19))));
                            if (l0.t(mainActivity3) != 1) {
                                str4 = "update_installer_system_note";
                            } else if (l0.o(mainActivity3)) {
                                str4 = "update_installer_freetuga_note";
                            } else {
                                str4 = "update_freetuga_missing";
                            }
                            linearLayout.addView(c9Var.l(si.a(str4, new Object[0])));
                            linearLayout.addView(c9Var.b(si.a("backup", new Object[0])));
                            linearLayout.addView(c9Var.d(c9.o(c9Var, si.a("export_settings", new Object[0]), si.a("home_screen_buttons_apps_themes_and_custom", new Object[0]), null, new za(mainActivity3, i5), 12), c9.o(c9Var, si.a("import_settings", new Object[0]), si.a("restore_an_ReferencePanel_backup", new Object[0]), null, new za(mainActivity3, 25), 12)));
                            linearLayout.addView(c9Var.l(si.a("import_replaces_the_current_settings_system_permissions", new Object[0])));
                            linearLayout.addView(c9Var.l(si.a("ReferencePanel_05_appearance_works_offline", "1.0.2")));
                            mainActivity2 = mainActivity3;
                            r15 = linearLayout;
                        }
                        z = true;
                        r3 = mainActivity2;
                        r14 = r15;
                        ?? scrollView4 = new ScrollView(r3);
                        scrollView4.addView(r14);
                        scrollView4.setVerticalScrollBarEnabled(z);
                        r3.k0(scrollView4);
                        scrollView4.post(new o(scrollView4, r3, 9));
                        return c9Var.p(list, r3.u, new ya(r3, 20), new za(r3, 26), scrollView4, new za(r3, 27));
                    case 109211285:
                        list = listI0;
                        i2 = 14;
                        i3 = 16;
                        if (str6.equals("saver")) {
                            linearLayout.addView(c9Var.i(si.a("screensaver_subtitle", new Object[0])));
                            ArrayList arrayListJ2 = j3.j0(c9Var.r(si.a("screensaver_timer", new Object[0]), si.a("screensaver_timer_hint", new Object[0]), mainActivity3.B().getBoolean("saver_on", false), new lg(mainActivity3, 4)));
                            if (mainActivity3.B().getBoolean("saver_on", false)) {
                                num = 20;
                                final List listI3 = j3.i0(1, 3, 5, 10, 20);
                                String strA20 = si.a("screensaver_delay", new Object[0]);
                                ArrayList arrayList2 = new ArrayList(k3.n0(listI3));
                                Iterator it3 = listI3.iterator();
                                while (it3.hasNext()) {
                                    int iIntValue = ((Number) it3.next()).intValue();
                                    List list2 = si.a;
                                    arrayList2.add(si.a("screensaver_minutes", Integer.valueOf(iIntValue)));
                                }
                                int iIndexOf2 = listI3.indexOf(Integer.valueOf(pi.s(mainActivity3.B().getInt("saver_minutes", 5), 1, 60)));
                                if (iIndexOf2 < 0) {
                                    iIndexOf2 = 2;
                                }
                                final int i43 = 2;
                                arrayListJ2.add(c9Var.q(strA20, arrayList2, iIndexOf2, new j6() { // from class: cg
                                    @Override // defpackage.j6
                                    public final Object b(Object obj5) {
                                        int i310 = i43;
                                        ti tiVar = ti.a;
                                        List list3 = listI3;
                                        MainActivity mainActivity4 = mainActivity3;
                                        int iIntValue2 = ((Integer) obj5).intValue();
                                        switch (i310) {
                                            case 0:
                                                mainActivity4.B().edit().putInt("saver_dim", ((Number) list3.get(iIntValue2)).intValue()).apply();
                                                mainActivity4.e0();
                                                break;
                                            case 1:
                                                mainActivity4.B().edit().putString("theme_mode", (String) list3.get(iIntValue2)).apply();
                                                mainActivity4.e0();
                                                break;
                                            case 2:
                                                mainActivity4.B().edit().putInt("saver_minutes", ((Number) list3.get(iIntValue2)).intValue()).apply();
                                                mainActivity4.h0();
                                                mainActivity4.e0();
                                                break;
                                            default:
                                                String str15 = (String) list3.get(iIntValue2);
                                                str15.getClass();
                                                int i311 = str15.equals("s") ? 104 : str15.equals("l") ? 152 : 128;
                                                int i312 = 700 - i311;
                                                float f2 = i312 - 16.0f;
                                                SharedPreferences.Editor editorPutString = mainActivity4.B().edit().putString("dock_size", str15).putString("desktop_ios_dock", "40.0," + i312 + ".0,1200.0," + i311 + ".0");
                                                for (String str16 : mainActivity4.m0) {
                                                    Iterator it4 = j3.i0("ios_", "wall_").iterator();
                                                    while (it4.hasNext()) {
                                                        String str17 = ((String) it4.next()) + str16;
                                                        String string2 = mainActivity4.B().getString("desktop_".concat(str17), null);
                                                        if (string2 != null) {
                                                            List listD2 = th.D(string2, new char[]{','});
                                                            ArrayList arrayList3 = new ArrayList();
                                                            Iterator it5 = listD2.iterator();
                                                            while (it5.hasNext()) {
                                                                Float fP = ai.p((String) it5.next());
                                                                if (fP != null) {
                                                                    arrayList3.add(fP);
                                                                }
                                                            }
                                                            ArrayList arrayList4 = arrayList3.size() == 4 ? arrayList3 : null;
                                                            if (arrayList4 != null && ((Number) arrayList4.get(1)).floatValue() < f2) {
                                                                if (((Number) arrayList4.get(3)).floatValue() + ((Number) arrayList4.get(1)).floatValue() >= f2 - 40.0f) {
                                                                    String strConcat = "desktop_".concat(str17);
                                                                    Object obj6 = arrayList4.get(0);
                                                                    Object obj7 = arrayList4.get(1);
                                                                    Object obj8 = arrayList4.get(2);
                                                                    float fFloatValue = f2 - ((Number) arrayList4.get(1)).floatValue();
                                                                    editorPutString.putString(strConcat, obj6 + "," + obj7 + "," + obj8 + "," + (fFloatValue >= 40.0f ? fFloatValue : 40.0f));
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                                editorPutString.apply();
                                                mainActivity4.e0();
                                                break;
                                        }
                                        return tiVar;
                                    }
                                }));
                                i10 = 0;
                                arrayListJ2.add(c9Var.r(si.a("screensaver_parked_only", new Object[0]), si.a("screensaver_parked_only_hint", new Object[0]), mainActivity3.B().getBoolean("saver_parked", true), new lg(mainActivity3, 5)));
                            } else {
                                i10 = 0;
                                num = 20;
                            }
                            View[] viewArr10 = (View[]) arrayListJ2.toArray(new View[i10]);
                            linearLayout.addView(c9Var.d((View[]) Arrays.copyOf(viewArr10, viewArr10.length)));
                            linearLayout.addView(c9Var.b(si.a("appearance", new Object[i10])));
                            int i44 = qf.w;
                            final File file = new File(mainActivity3.getFilesDir(), "wallpaper_screensaver.png");
                            LinearLayout linearLayoutQ7 = c9Var.q(si.a("screensaver_clock", new Object[i10]), j3.i0(si.a("clock_digital", new Object[i10]), si.a("clock_analog", new Object[i10]), si.a("clock_none", new Object[i10])), pi.s(mainActivity3.B().getInt("saver_clock", i10), i10, 2), new lg(mainActivity3, 6));
                            LinearLayout linearLayoutO16 = c9.o(c9Var, si.a("saver_layout", new Object[i10]), si.a("saver_layout_row_hint", new Object[i10]), null, new mg(mainActivity3, 10), 12);
                            LinearLayout linearLayoutO17 = c9.o(c9Var, si.a("screensaver_picture", new Object[i10]), file.exists() ? si.a("loaded_replace", new Object[i10]) : si.a("choose_image", new Object[i10]), null, new mg(mainActivity3, 11), 12);
                            View[] viewArr11 = new View[3];
                            viewArr11[i10] = linearLayoutQ7;
                            viewArr11[1] = linearLayoutO16;
                            viewArr11[2] = linearLayoutO17;
                            ArrayList arrayListJ3 = j3.j0(viewArr11);
                            if (mainActivity3.B().getInt("saver_clock", i10) != 2) {
                                z2 = true;
                                arrayListJ3.add(1, c9Var.q(si.a("saver_clock_size", new Object[i10]), j3.i0(si.a("saver_size_small", new Object[i10]), si.a("saver_size_normal", new Object[i10]), si.a("saver_size_large", new Object[i10])), pi.s(mainActivity3.B().getInt("saver_clock_size", 1), i10, 2), new ya(mainActivity3, 7)));
                                i11 = 2;
                            } else {
                                z2 = true;
                                i11 = 1;
                            }
                            arrayListJ3.add(i11, c9Var.r(si.a("saver_show_date", new Object[i10]), null, mainActivity3.B().getBoolean("saver_date", z2), new ya(mainActivity3, 8)));
                            arrayListJ3.add(i11 + 1, c9Var.r(si.a("saver_show_weather", new Object[i10]), null, mainActivity3.B().getBoolean("saver_weather", z2), new ya(mainActivity3, 9)));
                            arrayListJ3.add(i11 + 2, c9Var.r(si.a("saver_show_track", new Object[i10]), null, mainActivity3.B().getBoolean("saver_track", z2), new ya(mainActivity3, 10)));
                            if (file.exists()) {
                                i12 = 0;
                                arrayListJ3.add(c9.o(c9Var, si.a("screensaver_remove_picture", new Object[0]), si.a("screensaver_remove_picture_hint", new Object[0]), null, new y5() { // from class: bg
                                    @Override // defpackage.y5
                                    public final Object a() {
                                        int i45 = i12;
                                        ti tiVar = ti.a;
                                        MainActivity mainActivity4 = mainActivity3;
                                        File file2 = file;
                                        switch (i45) {
                                            case 0:
                                                file2.delete();
                                                mainActivity4.e0();
                                                break;
                                            case 1:
                                                file2.delete();
                                                mainActivity4.e0();
                                                break;
                                            default:
                                                file2.delete();
                                                mainActivity4.e0();
                                                break;
                                        }
                                        return tiVar;
                                    }
                                }, 4));
                            } else {
                                i12 = 0;
                            }
                            final List listI4 = j3.i0(Integer.valueOf(i12), num, 35, 55);
                            String strA21 = si.a("screensaver_dim", new Object[i12]);
                            ArrayList arrayList3 = new ArrayList(k3.n0(listI4));
                            Iterator it4 = listI4.iterator();
                            while (it4.hasNext()) {
                                int iIntValue2 = ((Number) it4.next()).intValue();
                                List list3 = si.a;
                                arrayList3.add(si.a("screensaver_percent", Integer.valueOf(iIntValue2)));
                            }
                            int iIndexOf3 = listI4.indexOf(Integer.valueOf(mainActivity3.B().getInt("saver_dim", 35)));
                            final int i45 = 0;
                            arrayListJ3.add(c9Var.q(strA21, arrayList3, iIndexOf3 < 0 ? 2 : iIndexOf3, new j6() { // from class: cg
                                @Override // defpackage.j6
                                public final Object b(Object obj5) {
                                    int i310 = i45;
                                    ti tiVar = ti.a;
                                    List list4 = listI4;
                                    MainActivity mainActivity4 = mainActivity3;
                                    int iIntValue3 = ((Integer) obj5).intValue();
                                    switch (i310) {
                                        case 0:
                                            mainActivity4.B().edit().putInt("saver_dim", ((Number) list4.get(iIntValue3)).intValue()).apply();
                                            mainActivity4.e0();
                                            break;
                                        case 1:
                                            mainActivity4.B().edit().putString("theme_mode", (String) list4.get(iIntValue3)).apply();
                                            mainActivity4.e0();
                                            break;
                                        case 2:
                                            mainActivity4.B().edit().putInt("saver_minutes", ((Number) list4.get(iIntValue3)).intValue()).apply();
                                            mainActivity4.h0();
                                            mainActivity4.e0();
                                            break;
                                        default:
                                            String str15 = (String) list4.get(iIntValue3);
                                            str15.getClass();
                                            int i311 = str15.equals("s") ? 104 : str15.equals("l") ? 152 : 128;
                                            int i312 = 700 - i311;
                                            float f2 = i312 - 16.0f;
                                            SharedPreferences.Editor editorPutString = mainActivity4.B().edit().putString("dock_size", str15).putString("desktop_ios_dock", "40.0," + i312 + ".0,1200.0," + i311 + ".0");
                                            for (String str16 : mainActivity4.m0) {
                                                Iterator it5 = j3.i0("ios_", "wall_").iterator();
                                                while (it5.hasNext()) {
                                                    String str17 = ((String) it5.next()) + str16;
                                                    String string2 = mainActivity4.B().getString("desktop_".concat(str17), null);
                                                    if (string2 != null) {
                                                        List listD2 = th.D(string2, new char[]{','});
                                                        ArrayList arrayList4 = new ArrayList();
                                                        Iterator it6 = listD2.iterator();
                                                        while (it6.hasNext()) {
                                                            Float fP = ai.p((String) it6.next());
                                                            if (fP != null) {
                                                                arrayList4.add(fP);
                                                            }
                                                        }
                                                        ArrayList arrayList5 = arrayList4.size() == 4 ? arrayList4 : null;
                                                        if (arrayList5 != null && ((Number) arrayList5.get(1)).floatValue() < f2) {
                                                            if (((Number) arrayList5.get(3)).floatValue() + ((Number) arrayList5.get(1)).floatValue() >= f2 - 40.0f) {
                                                                String strConcat = "desktop_".concat(str17);
                                                                Object obj6 = arrayList5.get(0);
                                                                Object obj7 = arrayList5.get(1);
                                                                Object obj8 = arrayList5.get(2);
                                                                float fFloatValue = f2 - ((Number) arrayList5.get(1)).floatValue();
                                                                editorPutString.putString(strConcat, obj6 + "," + obj7 + "," + obj8 + "," + (fFloatValue >= 40.0f ? fFloatValue : 40.0f));
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            editorPutString.apply();
                                            mainActivity4.e0();
                                            break;
                                    }
                                    return tiVar;
                                }
                            }));
                            arrayListJ3.add(c9.o(c9Var, si.a("screensaver_start", new Object[0]), null, null, new za(mainActivity3, i2), 12));
                            View[] viewArr12 = (View[]) arrayListJ3.toArray(new View[0]);
                            linearLayout.addView(c9Var.d((View[]) Arrays.copyOf(viewArr12, viewArr12.length)));
                            linearLayout.addView(c9Var.l(si.a("screensaver_widget_note", new Object[0])));
                            mainActivity2 = mainActivity3;
                            r15 = linearLayout;
                        } else {
                            i4 = 0;
                            i5 = 24;
                            linearLayout.addView(c9Var.i(si.a("startup_display_system_and_backup", new Object[i4])));
                            linearLayout.addView(c9Var.b(si.a("start_with_the_head_unit", new Object[i4])));
                            LinearLayout linearLayoutR19 = c9Var.r(si.a("open_after_head_unit_boot", new Object[i4]), null, mainActivity3.B().getBoolean("autostart", true), new ya(mainActivity3, 11));
                            LinearLayout linearLayoutO18 = c9.o(c9Var, si.a("set_ReferencePanel_as_home_screen", new Object[i4]), null, null, new za(mainActivity3, 15), 14);
                            LinearLayout linearLayoutR110 = c9Var.r(si.a("full_screen", new Object[i4]), si.a("hide_head_unit_system_bars", new Object[i4]), mainActivity3.B().getBoolean("fullscreen", true), new ya(mainActivity3, 13));
                            View[] viewArr13 = new View[3];
                            viewArr13[i4] = linearLayoutR19;
                            viewArr13[1] = linearLayoutO18;
                            viewArr13[2] = linearLayoutR110;
                            linearLayout.addView(c9Var.d(viewArr13));
                            linearLayout.addView(c9Var.l(si.a("starts_after_a_reboot_wake_from_sleep", new Object[i4])));
                            linearLayout.addView(c9Var.b(si.a("system_519", new Object[i4])));
                            Object systemService5 = mainActivity3.getSystemService("accessibility");
                            systemService5.getClass();
                            enabledAccessibilityServiceList = ((AccessibilityManager) systemService5).getEnabledAccessibilityServiceList(-1);
                            enabledAccessibilityServiceList.getClass();
                            if (enabledAccessibilityServiceList.isEmpty()) {
                                z3 = false;
                            } else {
                                it = enabledAccessibilityServiceList.iterator();
                                while (true) {
                                    if (it.hasNext()) {
                                        accessibilityServiceInfo = (AccessibilityServiceInfo) it.next();
                                        if (!e(accessibilityServiceInfo.getResolveInfo().serviceInfo.packageName, mainActivity3.getPackageName())) {
                                        }
                                    } else {
                                        z3 = false;
                                    }
                                }
                            }
                            LinearLayout linearLayoutR111 = c9Var.r(si.a("back_and_home_over_apps", new Object[0]), si.a("edge_gestures_or_floating_buttons", new Object[0]), mainActivity3.B().getBoolean("navigation_assist", false), new pb(2, mainActivity3, z3));
                            LinearLayout linearLayoutQ8 = c9Var.q(si.a("controls", new Object[0]), j3.i0(si.a("gestures", new Object[0]), si.a("buttons", new Object[0])), !e(mainActivity3.B().getString("navigation_assist_mode", "gestures"), "gestures") ? 1 : 0, new ya(mainActivity3, i2));
                            String strA111 = si.a("navigation_service", new Object[0]);
                            if (z3) {
                                strA3 = si.a("enabled", new Object[0]);
                            } else {
                                strA3 = si.a("enable_in_accessibility_settings", new Object[0]);
                            }
                            linearLayout.addView(c9Var.d(linearLayoutR111, linearLayoutQ8, c9.o(c9Var, strA111, strA3, null, new za(mainActivity3, i3), 12)));
                            linearLayout.addView(c9Var.l(si.a("start_on_the_strip_at_the_bottom", new Object[0])));
                            LinearLayout linearLayoutO19 = c9.o(c9Var, si.a("media_settings", new Object[0]), null, null, new za(mainActivity3, 17), 14);
                            String strA112 = si.a("android_brightness_control", new Object[0]);
                            if (Settings.System.canWrite(mainActivity3)) {
                                strA4 = si.a("allowed", new Object[0]);
                            } else {
                                strA4 = si.a("allow_system_settings_changes", new Object[0]);
                            }
                            linearLayout.addView(c9Var.d(linearLayoutO19, c9.o(c9Var, strA112, strA4, null, new za(mainActivity3, 18), 12)));
                            linearLayout.addView(c9Var.d(c9Var.r(si.a("hide_stock_climate_popup", new Object[0]), si.a("do_not_show_the_stock_popup_over", new Object[0]), mainActivity3.B().getBoolean("suppress_climate_popup", true), new ya(mainActivity3, 15))));
                            j = mainActivity3.B().getLong("hvac_gate_seen", 0L);
                            if (j > 0) {
                                strA5 = si.a("hvac_gate_active", new SimpleDateFormat("d MMM, HH:mm", si.b.b).format(new Date(j)));
                                i17 = 0;
                            } else {
                                i17 = 0;
                                strA5 = si.a("some_head_units_open_the_stock_climate", new Object[0]);
                            }
                            linearLayout.addView(c9Var.l(strA5));
                            linearLayout.addView(c9Var.b(si.a("updates", new Object[i17])));
                            LinearLayout linearLayoutO110 = c9.o(c9Var, si.a("update_check", new Object[i17]), si.a("update_current_0", "1.0.2"), null, new za(mainActivity3, 19), 12);
                            String strW5 = pi.w(mainActivity3);
                            linearLayout.addView(c9Var.d(linearLayoutO110, c9Var.n(si.a("region_pass_code", new Object[0]), si.a("region_pass_code_hint", new Object[0]), strW5, false, new w7(mainActivity3, strW5, 3)), c9Var.r(si.a("update_auto", new Object[0]), si.a("update_auto_hint", new Object[0]), l0.k(mainActivity3), new ya(mainActivity3, 18)), c9Var.q(si.a("update_installer", new Object[0]), j3.i0(si.a("update_installer_system", new Object[0]), si.a("update_installer_freetuga", new Object[0])), l0.t(mainActivity3), new ya(mainActivity3, 19))));
                            if (l0.t(mainActivity3) != 1) {
                                str4 = "update_installer_system_note";
                            } else if (l0.o(mainActivity3)) {
                                str4 = "update_installer_freetuga_note";
                            } else {
                                str4 = "update_freetuga_missing";
                            }
                            linearLayout.addView(c9Var.l(si.a(str4, new Object[0])));
                            linearLayout.addView(c9Var.b(si.a("backup", new Object[0])));
                            linearLayout.addView(c9Var.d(c9.o(c9Var, si.a("export_settings", new Object[0]), si.a("home_screen_buttons_apps_themes_and_custom", new Object[0]), null, new za(mainActivity3, i5), 12), c9.o(c9Var, si.a("import_settings", new Object[0]), si.a("restore_an_ReferencePanel_backup", new Object[0]), null, new za(mainActivity3, 25), 12)));
                            linearLayout.addView(c9Var.l(si.a("import_replaces_the_current_settings_system_permissions", new Object[0])));
                            linearLayout.addView(c9Var.l(si.a("ReferencePanel_05_appearance_works_offline", "1.0.2")));
                            mainActivity2 = mainActivity3;
                            r15 = linearLayout;
                        }
                        z = true;
                        r3 = mainActivity2;
                        r14 = r15;
                        ?? scrollView5 = new ScrollView(r3);
                        scrollView5.addView(r14);
                        scrollView5.setVerticalScrollBarEnabled(z);
                        r3.k0(scrollView5);
                        scrollView5.post(new o(scrollView5, r3, 9));
                        return c9Var.p(list, r3.u, new ya(r3, 20), new za(r3, 26), scrollView5, new za(r3, 27));
                    case 109648666:
                        list = listI0;
                        if (str6.equals(obj2)) {
                            linearLayout.addView(c9Var.i(si.a("split_lead", new Object[0])));
                            List list4 = eh.a;
                            SharedPreferences sharedPreferencesB2 = mainActivity3.B();
                            sharedPreferencesB2.getClass();
                            ArrayList arrayListC = eh.c(sharedPreferencesB2);
                            if (!arrayListC.isEmpty()) {
                                ArrayList arrayList4 = new ArrayList(k3.n0(arrayListC));
                                int size2 = arrayListC.size();
                                int i46 = 0;
                                int i47 = 0;
                                while (i47 < size2) {
                                    Object obj5 = arrayListC.get(i47);
                                    int i48 = i47 + 1;
                                    int i49 = i46 + 1;
                                    if (i46 < 0) {
                                        j3.m0();
                                        throw null;
                                    }
                                    String str15 = (String) obj5;
                                    gh ghVarC0 = C0(str15, mainActivity3, false);
                                    ghVarC0.setClickable(false);
                                    ghVarC0.setLongClickable(false);
                                    ArrayList arrayList5 = arrayListC;
                                    int i50 = size2;
                                    arrayList4.add(c9Var.f(ghVarC0, A0(str15, mainActivity3), j3.i0(c9Var.j(str8, i46 > 0, false, new c3(mainActivity3, i46, 11)), c9Var.j(str7, i46 < j3.h0(arrayList5), false, new c3(mainActivity3, i46, 12)), c9Var.j("minus", true, true, new w7(mainActivity3, str15, 4))), new w7(mainActivity3, str15, 5)));
                                    i46 = i49;
                                    arrayListC = arrayList5;
                                    size2 = i50;
                                    i47 = i48;
                                }
                                View[] viewArr14 = (View[]) arrayList4.toArray(new View[0]);
                                linearLayout.addView(c9Var.d((View[]) Arrays.copyOf(viewArr14, viewArr14.length)));
                            }
                            linearLayout.addView(c9Var.b(""));
                            final int i51 = 14;
                            final int i52 = 16;
                            linearLayout.addView(c9Var.d(c9.o(c9Var, si.a("split_add", new Object[0]), si.a("split_add_hint", new Object[0]), null, new y5() { // from class: jg
                                @Override // defpackage.y5
                                public final Object a() {
                                    String strY0;
                                    switch (i51) {
                                        case 0:
                                            k8.d(mainActivity3);
                                            break;
                                        case 1:
                                            k8.c(mainActivity3, null);
                                            break;
                                        case 2:
                                            MainActivity mainActivity4 = mainActivity3;
                                            ArrayList arrayListC2 = j0.c();
                                            AlertDialog.Builder title = new AlertDialog.Builder(mainActivity4).setTitle(si.a("app_language_choose", new Object[0]));
                                            ArrayList arrayList6 = new ArrayList(k3.n0(arrayListC2));
                                            int size3 = arrayListC2.size();
                                            int i210 = 0;
                                            while (i210 < size3) {
                                                Object obj6 = arrayListC2.get(i210);
                                                i210++;
                                                Object obj7 = (String) obj6;
                                                List list5 = j0.a;
                                                obj7.getClass();
                                                List list6 = j0.a;
                                                int iIndexOf4 = si.a.indexOf(obj7);
                                                if (iIndexOf4 >= 0 && iIndexOf4 < list6.size()) {
                                                    obj7 = list6.get(iIndexOf4);
                                                }
                                                arrayList6.add((String) obj7);
                                            }
                                            CharSequence[] charSequenceArr = (CharSequence[]) arrayList6.toArray(new String[0]);
                                            List list7 = si.a;
                                            AlertDialog.Builder negativeButton = title.setSingleChoiceItems(charSequenceArr, arrayListC2.indexOf(si.b.a), new dg(arrayListC2, mainActivity4)).setNegativeButton(si.a("cancel", new Object[0]), (DialogInterface.OnClickListener) null);
                                            negativeButton.getClass();
                                            k8.w0(negativeButton);
                                            break;
                                        case 3:
                                            MainActivity mainActivity5 = mainActivity3;
                                            mainActivity5.c = false;
                                            mainActivity5.f = true;
                                            mainActivity5.e0();
                                            mainActivity5.X(new j(14));
                                            break;
                                        case 4:
                                            k8.v0("elastre", mainActivity3);
                                            break;
                                        case 5:
                                            k8.v0("manrope", mainActivity3);
                                            break;
                                        case 6:
                                            k8.v0("custom", mainActivity3);
                                            break;
                                        case 7:
                                            k8.K(mainActivity3, true);
                                            break;
                                        case 8:
                                            MainActivity mainActivity6 = mainActivity3;
                                            x3.d(mainActivity6).delete();
                                            SharedPreferences sharedPreferences = mainActivity6.getSharedPreferences("panel", 0);
                                            SharedPreferences.Editor editorRemove = sharedPreferences.edit().remove("font_custom_name").remove("font_custom_kk").remove("font_custom_wght");
                                            if (k8.e(sharedPreferences.getString("font_choice", ""), "custom")) {
                                                editorRemove.remove("font_choice");
                                            }
                                            editorRemove.apply();
                                            z9.d(mainActivity6);
                                            z9.a = true;
                                            mainActivity6.e0();
                                            break;
                                        case 9:
                                            MainActivity mainActivity7 = mainActivity3;
                                            i7.g(mainActivity7, "");
                                            mainActivity7.e0();
                                            break;
                                        case 10:
                                            k8.O(mainActivity3, true, true);
                                            break;
                                        case 11:
                                            k8.O(mainActivity3, false, true);
                                            break;
                                        case 12:
                                            k8.Q(mainActivity3, false);
                                            break;
                                        case 13:
                                            k8.Q(mainActivity3, true);
                                            break;
                                        case 14:
                                            MainActivity mainActivity8 = mainActivity3;
                                            new i0(mainActivity8, mainActivity8.z(), si.a("split_left", new Object[0]), false, new lg(mainActivity8, 7)).show();
                                            break;
                                        case 15:
                                            k8.C(mainActivity3);
                                            break;
                                        case 16:
                                            MainActivity mainActivity9 = mainActivity3;
                                            mainActivity9.u = "dock";
                                            mainActivity9.s = null;
                                            mainActivity9.t = 0;
                                            mainActivity9.e0();
                                            break;
                                        case 17:
                                            k8.g0(mainActivity3, 1);
                                            break;
                                        case 18:
                                            MainActivity mainActivity10 = mainActivity3;
                                            md mdVar2 = md.a;
                                            List list8 = md.d;
                                            if (list8 == null) {
                                                mdVar2.c(mainActivity10);
                                                list8 = u4.a;
                                            }
                                            List list9 = list8;
                                            if (list9.isEmpty()) {
                                                mainActivity10.v0(si.a("radio_city_loading", new Object[0]));
                                            } else {
                                                Map mapJ0 = hb.j0(new yc("RU", si.a("country_ru", new Object[0])), new yc("BY", si.a("country_by", new Object[0])), new yc("KZ", si.a("country_kz", new Object[0])));
                                                i4 i4Var = new i4(mainActivity10, mainActivity10.z());
                                                ViewGroup viewGroupL = mainActivity10.l();
                                                EditText editText = new EditText(mainActivity10);
                                                editText.setHint(si.a("radio_city_search", new Object[0]));
                                                editText.setTextSize(20.0f);
                                                editText.setTextColor(mainActivity10.o);
                                                editText.setHintTextColor(mainActivity10.p);
                                                editText.setSingleLine(true);
                                                editText.setBackground(mainActivity10.m0(mainActivity10.q, 14));
                                                editText.setPadding(mainActivity10.r(16), 0, mainActivity10.r(16), 0);
                                                viewGroupL.addView(editText, new LinearLayout.LayoutParams(-1, mainActivity10.r(60)));
                                                LinearLayout linearLayoutL = mainActivity10.l();
                                                ScrollView scrollView6 = new ScrollView(mainActivity10);
                                                scrollView6.addView(linearLayoutL);
                                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, mainActivity10.r(420));
                                                layoutParams.topMargin = mainActivity10.r(10);
                                                viewGroupL.addView(scrollView6, layoutParams);
                                                Dialog dialogF = i4Var.f(si.a("radio_city", new Object[0]), si.a("radio_city_hint", new Object[0]), viewGroupL);
                                                editText.addTextChangedListener(new f0(linearLayoutL, i4Var, mainActivity10, list9, dialogF, mapJ0, 1));
                                                k8.k0(linearLayoutL, i4Var, mainActivity10, list9, dialogF, mapJ0, "");
                                                dialogF.show();
                                            }
                                            break;
                                        case 19:
                                            mainActivity3.f0();
                                            break;
                                        case 20:
                                            MainActivity mainActivity11 = mainActivity3;
                                            i4 i4Var2 = new i4(mainActivity11, mainActivity11.z());
                                            TextView textView = new TextView(mainActivity11);
                                            gc gcVarY = mainActivity11.y();
                                            if (gcVarY.n) {
                                                ArrayList arrayList7 = gcVarY.h;
                                                ArrayList arrayList8 = new ArrayList(k3.n0(arrayList7));
                                                int size4 = arrayList7.size();
                                                int i211 = 0;
                                                while (i211 < size4) {
                                                    Object obj8 = arrayList7.get(i211);
                                                    i211++;
                                                    arrayList8.add((MediaController) ((yc) obj8).a);
                                                }
                                                Object obj9 = MusicNotificationListener.a;
                                                ArrayList arrayListY = k8.Y();
                                                if (arrayList8.isEmpty() && arrayListY.isEmpty()) {
                                                    strY0 = si.a("no_media_sessions_start_radio_or_bluetooth", new Object[0]);
                                                } else {
                                                    List listI5 = j3.i0(i3.y0(arrayList8, "\n\n", new a(6, gcVarY), 30), i3.y0(arrayListY, "\n", new w(18), 30));
                                                    ArrayList arrayList9 = new ArrayList();
                                                    for (Object obj10 : listI5) {
                                                        if (!th.A((String) obj10)) {
                                                            arrayList9.add(obj10);
                                                        }
                                                    }
                                                    strY0 = i3.y0(arrayList9, "\n\n", null, 62);
                                                }
                                            } else {
                                                strY0 = si.a("no_music_access_enable_ReferencePanel_music_information", new Object[0]);
                                            }
                                            textView.setText(strY0);
                                            textView.setTextSize(17.0f);
                                            int i212 = l8.a;
                                            textView.setTextColor(l8.j(mainActivity11.z()));
                                            textView.setTextIsSelectable(true);
                                            textView.setLineSpacing(0.0f, 1.2f);
                                            String strA113 = si.a("music_diagnostics", new Object[0]);
                                            String strA114 = si.a("source_currently_shown_by_the_widget", new Object[0]);
                                            ScrollView scrollView7 = new ScrollView(mainActivity11);
                                            scrollView7.addView(textView);
                                            i4Var2.f(strA113, strA114, scrollView7).show();
                                            break;
                                        case 21:
                                            k8.m0(mainActivity3);
                                            break;
                                        case 22:
                                            mainActivity3.a0(0);
                                            break;
                                        case 23:
                                            MainActivity mainActivity12 = mainActivity3;
                                            ComponentName componentNameM = mainActivity12.m(0);
                                            if (componentNameM == null) {
                                                k8.g0(mainActivity12, 0);
                                            } else {
                                                mainActivity12.Q(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", componentNameM.getPackageName(), null)));
                                            }
                                            break;
                                        case 24:
                                            MainActivity mainActivity13 = mainActivity3;
                                            ArrayList arrayList10 = mainActivity13.w;
                                            int size5 = arrayList10.size();
                                            int i213 = 0;
                                            while (i213 < size5) {
                                                Object obj11 = arrayList10.get(i213);
                                                i213++;
                                                ((b) obj11).c();
                                            }
                                            arrayList10.clear();
                                            mainActivity13.c = false;
                                            mainActivity13.e0();
                                            break;
                                        case 25:
                                            MainActivity mainActivity14 = mainActivity3;
                                            k8.g0(mainActivity14, Integer.valueOf(mainActivity14.B0));
                                            break;
                                        case 26:
                                            MainActivity mainActivity15 = mainActivity3;
                                            mainActivity15.a0(mainActivity15.B0);
                                            break;
                                        case 27:
                                            k8.g0(mainActivity3, 0);
                                            break;
                                        case 28:
                                            MainActivity mainActivity16 = mainActivity3;
                                            if (!mainActivity16.h) {
                                                List listH = uh.h();
                                                ArrayList arrayList11 = new ArrayList(k3.n0(listH));
                                                Iterator it5 = listH.iterator();
                                                while (it5.hasNext()) {
                                                    arrayList11.add(((zi) it5.next()).b);
                                                }
                                                List listJ0 = i3.J0(i3.K0(arrayList11));
                                                c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity16).setTitle(si.a("vehicle_brand", new Object[0])).setItems((CharSequence[]) listJ0.toArray(new String[0]), new tb(mainActivity16, listJ0)), null, mainActivity16);
                                            }
                                            break;
                                        default:
                                            MainActivity mainActivity17 = mainActivity3;
                                            boolean z5 = mainActivity17.h;
                                            if (!z5 && !z5) {
                                                try {
                                                    Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                                                    intent.setType("image/*");
                                                    intent.addCategory("android.intent.category.OPENABLE");
                                                    intent.addFlags(1);
                                                    mainActivity17.startActivityForResult(intent, 41);
                                                } catch (ActivityNotFoundException unused) {
                                                    mainActivity17.v0(si.a("no_file_picker_app_on_this_head", new Object[0]));
                                                }
                                            }
                                            break;
                                    }
                                    return ti.a;
                                }
                            }, 12)));
                            linearLayout.addView(c9Var.l(si.a("split_hint", new Object[0])));
                            List listI5 = j3.i0(si.a("off_616", new Object[0]), si.a("dock_overlay_split_only", new Object[0]), si.a("dock_overlay_everywhere", new Object[0]));
                            linearLayout.addView(c9Var.b(si.a("bottom_bar", new Object[0])));
                            linearLayout.addView(c9Var.d(c9.o(c9Var, si.a("dock_overlay", new Object[0]), null, (String) listI5.get(pi.s(mainActivity3.v(), 0, 2)), new y5() { // from class: jg
                                @Override // defpackage.y5
                                public final Object a() {
                                    String strY0;
                                    switch (i52) {
                                        case 0:
                                            k8.d(mainActivity3);
                                            break;
                                        case 1:
                                            k8.c(mainActivity3, null);
                                            break;
                                        case 2:
                                            MainActivity mainActivity4 = mainActivity3;
                                            ArrayList arrayListC2 = j0.c();
                                            AlertDialog.Builder title = new AlertDialog.Builder(mainActivity4).setTitle(si.a("app_language_choose", new Object[0]));
                                            ArrayList arrayList6 = new ArrayList(k3.n0(arrayListC2));
                                            int size3 = arrayListC2.size();
                                            int i210 = 0;
                                            while (i210 < size3) {
                                                Object obj6 = arrayListC2.get(i210);
                                                i210++;
                                                Object obj7 = (String) obj6;
                                                List list5 = j0.a;
                                                obj7.getClass();
                                                List list6 = j0.a;
                                                int iIndexOf4 = si.a.indexOf(obj7);
                                                if (iIndexOf4 >= 0 && iIndexOf4 < list6.size()) {
                                                    obj7 = list6.get(iIndexOf4);
                                                }
                                                arrayList6.add((String) obj7);
                                            }
                                            CharSequence[] charSequenceArr = (CharSequence[]) arrayList6.toArray(new String[0]);
                                            List list7 = si.a;
                                            AlertDialog.Builder negativeButton = title.setSingleChoiceItems(charSequenceArr, arrayListC2.indexOf(si.b.a), new dg(arrayListC2, mainActivity4)).setNegativeButton(si.a("cancel", new Object[0]), (DialogInterface.OnClickListener) null);
                                            negativeButton.getClass();
                                            k8.w0(negativeButton);
                                            break;
                                        case 3:
                                            MainActivity mainActivity5 = mainActivity3;
                                            mainActivity5.c = false;
                                            mainActivity5.f = true;
                                            mainActivity5.e0();
                                            mainActivity5.X(new j(14));
                                            break;
                                        case 4:
                                            k8.v0("elastre", mainActivity3);
                                            break;
                                        case 5:
                                            k8.v0("manrope", mainActivity3);
                                            break;
                                        case 6:
                                            k8.v0("custom", mainActivity3);
                                            break;
                                        case 7:
                                            k8.K(mainActivity3, true);
                                            break;
                                        case 8:
                                            MainActivity mainActivity6 = mainActivity3;
                                            x3.d(mainActivity6).delete();
                                            SharedPreferences sharedPreferences = mainActivity6.getSharedPreferences("panel", 0);
                                            SharedPreferences.Editor editorRemove = sharedPreferences.edit().remove("font_custom_name").remove("font_custom_kk").remove("font_custom_wght");
                                            if (k8.e(sharedPreferences.getString("font_choice", ""), "custom")) {
                                                editorRemove.remove("font_choice");
                                            }
                                            editorRemove.apply();
                                            z9.d(mainActivity6);
                                            z9.a = true;
                                            mainActivity6.e0();
                                            break;
                                        case 9:
                                            MainActivity mainActivity7 = mainActivity3;
                                            i7.g(mainActivity7, "");
                                            mainActivity7.e0();
                                            break;
                                        case 10:
                                            k8.O(mainActivity3, true, true);
                                            break;
                                        case 11:
                                            k8.O(mainActivity3, false, true);
                                            break;
                                        case 12:
                                            k8.Q(mainActivity3, false);
                                            break;
                                        case 13:
                                            k8.Q(mainActivity3, true);
                                            break;
                                        case 14:
                                            MainActivity mainActivity8 = mainActivity3;
                                            new i0(mainActivity8, mainActivity8.z(), si.a("split_left", new Object[0]), false, new lg(mainActivity8, 7)).show();
                                            break;
                                        case 15:
                                            k8.C(mainActivity3);
                                            break;
                                        case 16:
                                            MainActivity mainActivity9 = mainActivity3;
                                            mainActivity9.u = "dock";
                                            mainActivity9.s = null;
                                            mainActivity9.t = 0;
                                            mainActivity9.e0();
                                            break;
                                        case 17:
                                            k8.g0(mainActivity3, 1);
                                            break;
                                        case 18:
                                            MainActivity mainActivity10 = mainActivity3;
                                            md mdVar2 = md.a;
                                            List list8 = md.d;
                                            if (list8 == null) {
                                                mdVar2.c(mainActivity10);
                                                list8 = u4.a;
                                            }
                                            List list9 = list8;
                                            if (list9.isEmpty()) {
                                                mainActivity10.v0(si.a("radio_city_loading", new Object[0]));
                                            } else {
                                                Map mapJ0 = hb.j0(new yc("RU", si.a("country_ru", new Object[0])), new yc("BY", si.a("country_by", new Object[0])), new yc("KZ", si.a("country_kz", new Object[0])));
                                                i4 i4Var = new i4(mainActivity10, mainActivity10.z());
                                                ViewGroup viewGroupL = mainActivity10.l();
                                                EditText editText = new EditText(mainActivity10);
                                                editText.setHint(si.a("radio_city_search", new Object[0]));
                                                editText.setTextSize(20.0f);
                                                editText.setTextColor(mainActivity10.o);
                                                editText.setHintTextColor(mainActivity10.p);
                                                editText.setSingleLine(true);
                                                editText.setBackground(mainActivity10.m0(mainActivity10.q, 14));
                                                editText.setPadding(mainActivity10.r(16), 0, mainActivity10.r(16), 0);
                                                viewGroupL.addView(editText, new LinearLayout.LayoutParams(-1, mainActivity10.r(60)));
                                                LinearLayout linearLayoutL = mainActivity10.l();
                                                ScrollView scrollView6 = new ScrollView(mainActivity10);
                                                scrollView6.addView(linearLayoutL);
                                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, mainActivity10.r(420));
                                                layoutParams.topMargin = mainActivity10.r(10);
                                                viewGroupL.addView(scrollView6, layoutParams);
                                                Dialog dialogF = i4Var.f(si.a("radio_city", new Object[0]), si.a("radio_city_hint", new Object[0]), viewGroupL);
                                                editText.addTextChangedListener(new f0(linearLayoutL, i4Var, mainActivity10, list9, dialogF, mapJ0, 1));
                                                k8.k0(linearLayoutL, i4Var, mainActivity10, list9, dialogF, mapJ0, "");
                                                dialogF.show();
                                            }
                                            break;
                                        case 19:
                                            mainActivity3.f0();
                                            break;
                                        case 20:
                                            MainActivity mainActivity11 = mainActivity3;
                                            i4 i4Var2 = new i4(mainActivity11, mainActivity11.z());
                                            TextView textView = new TextView(mainActivity11);
                                            gc gcVarY = mainActivity11.y();
                                            if (gcVarY.n) {
                                                ArrayList arrayList7 = gcVarY.h;
                                                ArrayList arrayList8 = new ArrayList(k3.n0(arrayList7));
                                                int size4 = arrayList7.size();
                                                int i211 = 0;
                                                while (i211 < size4) {
                                                    Object obj8 = arrayList7.get(i211);
                                                    i211++;
                                                    arrayList8.add((MediaController) ((yc) obj8).a);
                                                }
                                                Object obj9 = MusicNotificationListener.a;
                                                ArrayList arrayListY = k8.Y();
                                                if (arrayList8.isEmpty() && arrayListY.isEmpty()) {
                                                    strY0 = si.a("no_media_sessions_start_radio_or_bluetooth", new Object[0]);
                                                } else {
                                                    List listI6 = j3.i0(i3.y0(arrayList8, "\n\n", new a(6, gcVarY), 30), i3.y0(arrayListY, "\n", new w(18), 30));
                                                    ArrayList arrayList9 = new ArrayList();
                                                    for (Object obj10 : listI6) {
                                                        if (!th.A((String) obj10)) {
                                                            arrayList9.add(obj10);
                                                        }
                                                    }
                                                    strY0 = i3.y0(arrayList9, "\n\n", null, 62);
                                                }
                                            } else {
                                                strY0 = si.a("no_music_access_enable_ReferencePanel_music_information", new Object[0]);
                                            }
                                            textView.setText(strY0);
                                            textView.setTextSize(17.0f);
                                            int i212 = l8.a;
                                            textView.setTextColor(l8.j(mainActivity11.z()));
                                            textView.setTextIsSelectable(true);
                                            textView.setLineSpacing(0.0f, 1.2f);
                                            String strA113 = si.a("music_diagnostics", new Object[0]);
                                            String strA114 = si.a("source_currently_shown_by_the_widget", new Object[0]);
                                            ScrollView scrollView7 = new ScrollView(mainActivity11);
                                            scrollView7.addView(textView);
                                            i4Var2.f(strA113, strA114, scrollView7).show();
                                            break;
                                        case 21:
                                            k8.m0(mainActivity3);
                                            break;
                                        case 22:
                                            mainActivity3.a0(0);
                                            break;
                                        case 23:
                                            MainActivity mainActivity12 = mainActivity3;
                                            ComponentName componentNameM = mainActivity12.m(0);
                                            if (componentNameM == null) {
                                                k8.g0(mainActivity12, 0);
                                            } else {
                                                mainActivity12.Q(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", componentNameM.getPackageName(), null)));
                                            }
                                            break;
                                        case 24:
                                            MainActivity mainActivity13 = mainActivity3;
                                            ArrayList arrayList10 = mainActivity13.w;
                                            int size5 = arrayList10.size();
                                            int i213 = 0;
                                            while (i213 < size5) {
                                                Object obj11 = arrayList10.get(i213);
                                                i213++;
                                                ((b) obj11).c();
                                            }
                                            arrayList10.clear();
                                            mainActivity13.c = false;
                                            mainActivity13.e0();
                                            break;
                                        case 25:
                                            MainActivity mainActivity14 = mainActivity3;
                                            k8.g0(mainActivity14, Integer.valueOf(mainActivity14.B0));
                                            break;
                                        case 26:
                                            MainActivity mainActivity15 = mainActivity3;
                                            mainActivity15.a0(mainActivity15.B0);
                                            break;
                                        case 27:
                                            k8.g0(mainActivity3, 0);
                                            break;
                                        case 28:
                                            MainActivity mainActivity16 = mainActivity3;
                                            if (!mainActivity16.h) {
                                                List listH = uh.h();
                                                ArrayList arrayList11 = new ArrayList(k3.n0(listH));
                                                Iterator it5 = listH.iterator();
                                                while (it5.hasNext()) {
                                                    arrayList11.add(((zi) it5.next()).b);
                                                }
                                                List listJ0 = i3.J0(i3.K0(arrayList11));
                                                c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity16).setTitle(si.a("vehicle_brand", new Object[0])).setItems((CharSequence[]) listJ0.toArray(new String[0]), new tb(mainActivity16, listJ0)), null, mainActivity16);
                                            }
                                            break;
                                        default:
                                            MainActivity mainActivity17 = mainActivity3;
                                            boolean z5 = mainActivity17.h;
                                            if (!z5 && !z5) {
                                                try {
                                                    Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                                                    intent.setType("image/*");
                                                    intent.addCategory("android.intent.category.OPENABLE");
                                                    intent.addFlags(1);
                                                    mainActivity17.startActivityForResult(intent, 41);
                                                } catch (ActivityNotFoundException unused) {
                                                    mainActivity17.v0(si.a("no_file_picker_app_on_this_head", new Object[0]));
                                                }
                                            }
                                            break;
                                    }
                                    return ti.a;
                                }
                            }, 8)));
                            mainActivity2 = mainActivity3;
                            r15 = linearLayout;
                        } else {
                            i2 = 14;
                            i3 = 16;
                            i4 = 0;
                            i5 = 24;
                            linearLayout.addView(c9Var.i(si.a("startup_display_system_and_backup", new Object[i4])));
                            linearLayout.addView(c9Var.b(si.a("start_with_the_head_unit", new Object[i4])));
                            LinearLayout linearLayoutR112 = c9Var.r(si.a("open_after_head_unit_boot", new Object[i4]), null, mainActivity3.B().getBoolean("autostart", true), new ya(mainActivity3, 11));
                            LinearLayout linearLayoutO111 = c9.o(c9Var, si.a("set_ReferencePanel_as_home_screen", new Object[i4]), null, null, new za(mainActivity3, 15), 14);
                            LinearLayout linearLayoutR113 = c9Var.r(si.a("full_screen", new Object[i4]), si.a("hide_head_unit_system_bars", new Object[i4]), mainActivity3.B().getBoolean("fullscreen", true), new ya(mainActivity3, 13));
                            View[] viewArr15 = new View[3];
                            viewArr15[i4] = linearLayoutR112;
                            viewArr15[1] = linearLayoutO111;
                            viewArr15[2] = linearLayoutR113;
                            linearLayout.addView(c9Var.d(viewArr15));
                            linearLayout.addView(c9Var.l(si.a("starts_after_a_reboot_wake_from_sleep", new Object[i4])));
                            linearLayout.addView(c9Var.b(si.a("system_519", new Object[i4])));
                            Object systemService6 = mainActivity3.getSystemService("accessibility");
                            systemService6.getClass();
                            enabledAccessibilityServiceList = ((AccessibilityManager) systemService6).getEnabledAccessibilityServiceList(-1);
                            enabledAccessibilityServiceList.getClass();
                            if (enabledAccessibilityServiceList.isEmpty()) {
                                z3 = false;
                            } else {
                                it = enabledAccessibilityServiceList.iterator();
                                while (true) {
                                    if (it.hasNext()) {
                                        accessibilityServiceInfo = (AccessibilityServiceInfo) it.next();
                                        if (!e(accessibilityServiceInfo.getResolveInfo().serviceInfo.packageName, mainActivity3.getPackageName())) {
                                        }
                                    } else {
                                        z3 = false;
                                    }
                                }
                            }
                            LinearLayout linearLayoutR114 = c9Var.r(si.a("back_and_home_over_apps", new Object[0]), si.a("edge_gestures_or_floating_buttons", new Object[0]), mainActivity3.B().getBoolean("navigation_assist", false), new pb(2, mainActivity3, z3));
                            LinearLayout linearLayoutQ9 = c9Var.q(si.a("controls", new Object[0]), j3.i0(si.a("gestures", new Object[0]), si.a("buttons", new Object[0])), !e(mainActivity3.B().getString("navigation_assist_mode", "gestures"), "gestures") ? 1 : 0, new ya(mainActivity3, i2));
                            String strA113 = si.a("navigation_service", new Object[0]);
                            if (z3) {
                                strA3 = si.a("enabled", new Object[0]);
                            } else {
                                strA3 = si.a("enable_in_accessibility_settings", new Object[0]);
                            }
                            linearLayout.addView(c9Var.d(linearLayoutR114, linearLayoutQ9, c9.o(c9Var, strA113, strA3, null, new za(mainActivity3, i3), 12)));
                            linearLayout.addView(c9Var.l(si.a("start_on_the_strip_at_the_bottom", new Object[0])));
                            LinearLayout linearLayoutO112 = c9.o(c9Var, si.a("media_settings", new Object[0]), null, null, new za(mainActivity3, 17), 14);
                            String strA114 = si.a("android_brightness_control", new Object[0]);
                            if (Settings.System.canWrite(mainActivity3)) {
                                strA4 = si.a("allowed", new Object[0]);
                            } else {
                                strA4 = si.a("allow_system_settings_changes", new Object[0]);
                            }
                            linearLayout.addView(c9Var.d(linearLayoutO112, c9.o(c9Var, strA114, strA4, null, new za(mainActivity3, 18), 12)));
                            linearLayout.addView(c9Var.d(c9Var.r(si.a("hide_stock_climate_popup", new Object[0]), si.a("do_not_show_the_stock_popup_over", new Object[0]), mainActivity3.B().getBoolean("suppress_climate_popup", true), new ya(mainActivity3, 15))));
                            j = mainActivity3.B().getLong("hvac_gate_seen", 0L);
                            if (j > 0) {
                                strA5 = si.a("hvac_gate_active", new SimpleDateFormat("d MMM, HH:mm", si.b.b).format(new Date(j)));
                                i17 = 0;
                            } else {
                                i17 = 0;
                                strA5 = si.a("some_head_units_open_the_stock_climate", new Object[0]);
                            }
                            linearLayout.addView(c9Var.l(strA5));
                            linearLayout.addView(c9Var.b(si.a("updates", new Object[i17])));
                            LinearLayout linearLayoutO113 = c9.o(c9Var, si.a("update_check", new Object[i17]), si.a("update_current_0", "1.0.2"), null, new za(mainActivity3, 19), 12);
                            String strW6 = pi.w(mainActivity3);
                            linearLayout.addView(c9Var.d(linearLayoutO113, c9Var.n(si.a("region_pass_code", new Object[0]), si.a("region_pass_code_hint", new Object[0]), strW6, false, new w7(mainActivity3, strW6, 3)), c9Var.r(si.a("update_auto", new Object[0]), si.a("update_auto_hint", new Object[0]), l0.k(mainActivity3), new ya(mainActivity3, 18)), c9Var.q(si.a("update_installer", new Object[0]), j3.i0(si.a("update_installer_system", new Object[0]), si.a("update_installer_freetuga", new Object[0])), l0.t(mainActivity3), new ya(mainActivity3, 19))));
                            if (l0.t(mainActivity3) != 1) {
                                str4 = "update_installer_system_note";
                            } else if (l0.o(mainActivity3)) {
                                str4 = "update_installer_freetuga_note";
                            } else {
                                str4 = "update_freetuga_missing";
                            }
                            linearLayout.addView(c9Var.l(si.a(str4, new Object[0])));
                            linearLayout.addView(c9Var.b(si.a("backup", new Object[0])));
                            linearLayout.addView(c9Var.d(c9.o(c9Var, si.a("export_settings", new Object[0]), si.a("home_screen_buttons_apps_themes_and_custom", new Object[0]), null, new za(mainActivity3, i5), 12), c9.o(c9Var, si.a("import_settings", new Object[0]), si.a("restore_an_ReferencePanel_backup", new Object[0]), null, new za(mainActivity3, 25), 12)));
                            linearLayout.addView(c9Var.l(si.a("import_replaces_the_current_settings_system_permissions", new Object[0])));
                            linearLayout.addView(c9Var.l(si.a("ReferencePanel_05_appearance_works_offline", "1.0.2")));
                            mainActivity2 = mainActivity3;
                            r15 = linearLayout;
                        }
                        z = true;
                        r3 = mainActivity2;
                        r14 = r15;
                        ?? scrollView6 = new ScrollView(r3);
                        scrollView6.addView(r14);
                        scrollView6.setVerticalScrollBarEnabled(z);
                        r3.k0(scrollView6);
                        scrollView6.post(new o(scrollView6, r3, 9));
                        return c9Var.p(list, r3.u, new ya(r3, 20), new za(r3, 26), scrollView6, new za(r3, 27));
                    case 109757599:
                        list = listI0;
                        int i53 = 9;
                        if (str6.equals("stats")) {
                            linearLayout.addView(pi.Q(mainActivity3, c9Var, new mg(mainActivity3, i53)));
                            mainActivity2 = mainActivity3;
                            r15 = linearLayout;
                        } else {
                            i2 = 14;
                            i3 = 16;
                            i4 = 0;
                            i5 = 24;
                            linearLayout.addView(c9Var.i(si.a("startup_display_system_and_backup", new Object[i4])));
                            linearLayout.addView(c9Var.b(si.a("start_with_the_head_unit", new Object[i4])));
                            LinearLayout linearLayoutR115 = c9Var.r(si.a("open_after_head_unit_boot", new Object[i4]), null, mainActivity3.B().getBoolean("autostart", true), new ya(mainActivity3, 11));
                            LinearLayout linearLayoutO114 = c9.o(c9Var, si.a("set_ReferencePanel_as_home_screen", new Object[i4]), null, null, new za(mainActivity3, 15), 14);
                            LinearLayout linearLayoutR116 = c9Var.r(si.a("full_screen", new Object[i4]), si.a("hide_head_unit_system_bars", new Object[i4]), mainActivity3.B().getBoolean("fullscreen", true), new ya(mainActivity3, 13));
                            View[] viewArr16 = new View[3];
                            viewArr16[i4] = linearLayoutR115;
                            viewArr16[1] = linearLayoutO114;
                            viewArr16[2] = linearLayoutR116;
                            linearLayout.addView(c9Var.d(viewArr16));
                            linearLayout.addView(c9Var.l(si.a("starts_after_a_reboot_wake_from_sleep", new Object[i4])));
                            linearLayout.addView(c9Var.b(si.a("system_519", new Object[i4])));
                            Object systemService7 = mainActivity3.getSystemService("accessibility");
                            systemService7.getClass();
                            enabledAccessibilityServiceList = ((AccessibilityManager) systemService7).getEnabledAccessibilityServiceList(-1);
                            enabledAccessibilityServiceList.getClass();
                            if (enabledAccessibilityServiceList.isEmpty()) {
                                z3 = false;
                            } else {
                                it = enabledAccessibilityServiceList.iterator();
                                while (true) {
                                    if (it.hasNext()) {
                                        accessibilityServiceInfo = (AccessibilityServiceInfo) it.next();
                                        if (!e(accessibilityServiceInfo.getResolveInfo().serviceInfo.packageName, mainActivity3.getPackageName())) {
                                        }
                                    } else {
                                        z3 = false;
                                    }
                                }
                            }
                            LinearLayout linearLayoutR117 = c9Var.r(si.a("back_and_home_over_apps", new Object[0]), si.a("edge_gestures_or_floating_buttons", new Object[0]), mainActivity3.B().getBoolean("navigation_assist", false), new pb(2, mainActivity3, z3));
                            LinearLayout linearLayoutQ10 = c9Var.q(si.a("controls", new Object[0]), j3.i0(si.a("gestures", new Object[0]), si.a("buttons", new Object[0])), !e(mainActivity3.B().getString("navigation_assist_mode", "gestures"), "gestures") ? 1 : 0, new ya(mainActivity3, i2));
                            String strA115 = si.a("navigation_service", new Object[0]);
                            if (z3) {
                                strA3 = si.a("enabled", new Object[0]);
                            } else {
                                strA3 = si.a("enable_in_accessibility_settings", new Object[0]);
                            }
                            linearLayout.addView(c9Var.d(linearLayoutR117, linearLayoutQ10, c9.o(c9Var, strA115, strA3, null, new za(mainActivity3, i3), 12)));
                            linearLayout.addView(c9Var.l(si.a("start_on_the_strip_at_the_bottom", new Object[0])));
                            LinearLayout linearLayoutO115 = c9.o(c9Var, si.a("media_settings", new Object[0]), null, null, new za(mainActivity3, 17), 14);
                            String strA116 = si.a("android_brightness_control", new Object[0]);
                            if (Settings.System.canWrite(mainActivity3)) {
                                strA4 = si.a("allowed", new Object[0]);
                            } else {
                                strA4 = si.a("allow_system_settings_changes", new Object[0]);
                            }
                            linearLayout.addView(c9Var.d(linearLayoutO115, c9.o(c9Var, strA116, strA4, null, new za(mainActivity3, 18), 12)));
                            linearLayout.addView(c9Var.d(c9Var.r(si.a("hide_stock_climate_popup", new Object[0]), si.a("do_not_show_the_stock_popup_over", new Object[0]), mainActivity3.B().getBoolean("suppress_climate_popup", true), new ya(mainActivity3, 15))));
                            j = mainActivity3.B().getLong("hvac_gate_seen", 0L);
                            if (j > 0) {
                                strA5 = si.a("hvac_gate_active", new SimpleDateFormat("d MMM, HH:mm", si.b.b).format(new Date(j)));
                                i17 = 0;
                            } else {
                                i17 = 0;
                                strA5 = si.a("some_head_units_open_the_stock_climate", new Object[0]);
                            }
                            linearLayout.addView(c9Var.l(strA5));
                            linearLayout.addView(c9Var.b(si.a("updates", new Object[i17])));
                            LinearLayout linearLayoutO116 = c9.o(c9Var, si.a("update_check", new Object[i17]), si.a("update_current_0", "1.0.2"), null, new za(mainActivity3, 19), 12);
                            String strW7 = pi.w(mainActivity3);
                            linearLayout.addView(c9Var.d(linearLayoutO116, c9Var.n(si.a("region_pass_code", new Object[0]), si.a("region_pass_code_hint", new Object[0]), strW7, false, new w7(mainActivity3, strW7, 3)), c9Var.r(si.a("update_auto", new Object[0]), si.a("update_auto_hint", new Object[0]), l0.k(mainActivity3), new ya(mainActivity3, 18)), c9Var.q(si.a("update_installer", new Object[0]), j3.i0(si.a("update_installer_system", new Object[0]), si.a("update_installer_freetuga", new Object[0])), l0.t(mainActivity3), new ya(mainActivity3, 19))));
                            if (l0.t(mainActivity3) != 1) {
                                str4 = "update_installer_system_note";
                            } else if (l0.o(mainActivity3)) {
                                str4 = "update_installer_freetuga_note";
                            } else {
                                str4 = "update_freetuga_missing";
                            }
                            linearLayout.addView(c9Var.l(si.a(str4, new Object[0])));
                            linearLayout.addView(c9Var.b(si.a("backup", new Object[0])));
                            linearLayout.addView(c9Var.d(c9.o(c9Var, si.a("export_settings", new Object[0]), si.a("home_screen_buttons_apps_themes_and_custom", new Object[0]), null, new za(mainActivity3, i5), 12), c9.o(c9Var, si.a("import_settings", new Object[0]), si.a("restore_an_ReferencePanel_backup", new Object[0]), null, new za(mainActivity3, 25), 12)));
                            linearLayout.addView(c9Var.l(si.a("import_replaces_the_current_settings_system_permissions", new Object[0])));
                            linearLayout.addView(c9Var.l(si.a("ReferencePanel_05_appearance_works_offline", "1.0.2")));
                            mainActivity2 = mainActivity3;
                            r15 = linearLayout;
                        }
                        z = true;
                        r3 = mainActivity2;
                        r14 = r15;
                        ?? scrollView7 = new ScrollView(r3);
                        scrollView7.addView(r14);
                        scrollView7.setVerticalScrollBarEnabled(z);
                        r3.k0(scrollView7);
                        scrollView7.post(new o(scrollView7, r3, 9));
                        return c9Var.p(list, r3.u, new ya(r3, 20), new za(r3, 26), scrollView7, new za(r3, 27));
                    case 109780401:
                        list = listI0;
                        if (str6.equals("style")) {
                            if (j0.b()) {
                                String strA22 = si.a("app_language_title", new Object[0]);
                                Object objB = si.b();
                                List list5 = j0.a;
                                int iIndexOf4 = si.a.indexOf(objB);
                                if (iIndexOf4 >= 0 && iIndexOf4 < list5.size()) {
                                    objB = list5.get(iIndexOf4);
                                }
                                final int i54 = 2;
                                str2 = "";
                                str = "loaded_replace";
                                i13 = 0;
                                linearLayout.addView(c9Var.d(c9.o(c9Var, strA22, null, (String) objB, new y5() { // from class: jg
                                    @Override // defpackage.y5
                                    public final Object a() {
                                        String strY0;
                                        switch (i54) {
                                            case 0:
                                                k8.d(mainActivity3);
                                                break;
                                            case 1:
                                                k8.c(mainActivity3, null);
                                                break;
                                            case 2:
                                                MainActivity mainActivity4 = mainActivity3;
                                                ArrayList arrayListC2 = j0.c();
                                                AlertDialog.Builder title = new AlertDialog.Builder(mainActivity4).setTitle(si.a("app_language_choose", new Object[0]));
                                                ArrayList arrayList6 = new ArrayList(k3.n0(arrayListC2));
                                                int size3 = arrayListC2.size();
                                                int i210 = 0;
                                                while (i210 < size3) {
                                                    Object obj6 = arrayListC2.get(i210);
                                                    i210++;
                                                    Object obj7 = (String) obj6;
                                                    List list6 = j0.a;
                                                    obj7.getClass();
                                                    List list7 = j0.a;
                                                    int iIndexOf5 = si.a.indexOf(obj7);
                                                    if (iIndexOf5 >= 0 && iIndexOf5 < list7.size()) {
                                                        obj7 = list7.get(iIndexOf5);
                                                    }
                                                    arrayList6.add((String) obj7);
                                                }
                                                CharSequence[] charSequenceArr = (CharSequence[]) arrayList6.toArray(new String[0]);
                                                List list8 = si.a;
                                                AlertDialog.Builder negativeButton = title.setSingleChoiceItems(charSequenceArr, arrayListC2.indexOf(si.b.a), new dg(arrayListC2, mainActivity4)).setNegativeButton(si.a("cancel", new Object[0]), (DialogInterface.OnClickListener) null);
                                                negativeButton.getClass();
                                                k8.w0(negativeButton);
                                                break;
                                            case 3:
                                                MainActivity mainActivity5 = mainActivity3;
                                                mainActivity5.c = false;
                                                mainActivity5.f = true;
                                                mainActivity5.e0();
                                                mainActivity5.X(new j(14));
                                                break;
                                            case 4:
                                                k8.v0("elastre", mainActivity3);
                                                break;
                                            case 5:
                                                k8.v0("manrope", mainActivity3);
                                                break;
                                            case 6:
                                                k8.v0("custom", mainActivity3);
                                                break;
                                            case 7:
                                                k8.K(mainActivity3, true);
                                                break;
                                            case 8:
                                                MainActivity mainActivity6 = mainActivity3;
                                                x3.d(mainActivity6).delete();
                                                SharedPreferences sharedPreferences = mainActivity6.getSharedPreferences("panel", 0);
                                                SharedPreferences.Editor editorRemove = sharedPreferences.edit().remove("font_custom_name").remove("font_custom_kk").remove("font_custom_wght");
                                                if (k8.e(sharedPreferences.getString("font_choice", ""), "custom")) {
                                                    editorRemove.remove("font_choice");
                                                }
                                                editorRemove.apply();
                                                z9.d(mainActivity6);
                                                z9.a = true;
                                                mainActivity6.e0();
                                                break;
                                            case 9:
                                                MainActivity mainActivity7 = mainActivity3;
                                                i7.g(mainActivity7, "");
                                                mainActivity7.e0();
                                                break;
                                            case 10:
                                                k8.O(mainActivity3, true, true);
                                                break;
                                            case 11:
                                                k8.O(mainActivity3, false, true);
                                                break;
                                            case 12:
                                                k8.Q(mainActivity3, false);
                                                break;
                                            case 13:
                                                k8.Q(mainActivity3, true);
                                                break;
                                            case 14:
                                                MainActivity mainActivity8 = mainActivity3;
                                                new i0(mainActivity8, mainActivity8.z(), si.a("split_left", new Object[0]), false, new lg(mainActivity8, 7)).show();
                                                break;
                                            case 15:
                                                k8.C(mainActivity3);
                                                break;
                                            case 16:
                                                MainActivity mainActivity9 = mainActivity3;
                                                mainActivity9.u = "dock";
                                                mainActivity9.s = null;
                                                mainActivity9.t = 0;
                                                mainActivity9.e0();
                                                break;
                                            case 17:
                                                k8.g0(mainActivity3, 1);
                                                break;
                                            case 18:
                                                MainActivity mainActivity10 = mainActivity3;
                                                md mdVar2 = md.a;
                                                List list9 = md.d;
                                                if (list9 == null) {
                                                    mdVar2.c(mainActivity10);
                                                    list9 = u4.a;
                                                }
                                                List list10 = list9;
                                                if (list10.isEmpty()) {
                                                    mainActivity10.v0(si.a("radio_city_loading", new Object[0]));
                                                } else {
                                                    Map mapJ0 = hb.j0(new yc("RU", si.a("country_ru", new Object[0])), new yc("BY", si.a("country_by", new Object[0])), new yc("KZ", si.a("country_kz", new Object[0])));
                                                    i4 i4Var = new i4(mainActivity10, mainActivity10.z());
                                                    ViewGroup viewGroupL = mainActivity10.l();
                                                    EditText editText = new EditText(mainActivity10);
                                                    editText.setHint(si.a("radio_city_search", new Object[0]));
                                                    editText.setTextSize(20.0f);
                                                    editText.setTextColor(mainActivity10.o);
                                                    editText.setHintTextColor(mainActivity10.p);
                                                    editText.setSingleLine(true);
                                                    editText.setBackground(mainActivity10.m0(mainActivity10.q, 14));
                                                    editText.setPadding(mainActivity10.r(16), 0, mainActivity10.r(16), 0);
                                                    viewGroupL.addView(editText, new LinearLayout.LayoutParams(-1, mainActivity10.r(60)));
                                                    LinearLayout linearLayoutL = mainActivity10.l();
                                                    ScrollView scrollView8 = new ScrollView(mainActivity10);
                                                    scrollView8.addView(linearLayoutL);
                                                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, mainActivity10.r(420));
                                                    layoutParams.topMargin = mainActivity10.r(10);
                                                    viewGroupL.addView(scrollView8, layoutParams);
                                                    Dialog dialogF = i4Var.f(si.a("radio_city", new Object[0]), si.a("radio_city_hint", new Object[0]), viewGroupL);
                                                    editText.addTextChangedListener(new f0(linearLayoutL, i4Var, mainActivity10, list10, dialogF, mapJ0, 1));
                                                    k8.k0(linearLayoutL, i4Var, mainActivity10, list10, dialogF, mapJ0, "");
                                                    dialogF.show();
                                                }
                                                break;
                                            case 19:
                                                mainActivity3.f0();
                                                break;
                                            case 20:
                                                MainActivity mainActivity11 = mainActivity3;
                                                i4 i4Var2 = new i4(mainActivity11, mainActivity11.z());
                                                TextView textView = new TextView(mainActivity11);
                                                gc gcVarY = mainActivity11.y();
                                                if (gcVarY.n) {
                                                    ArrayList arrayList7 = gcVarY.h;
                                                    ArrayList arrayList8 = new ArrayList(k3.n0(arrayList7));
                                                    int size4 = arrayList7.size();
                                                    int i211 = 0;
                                                    while (i211 < size4) {
                                                        Object obj8 = arrayList7.get(i211);
                                                        i211++;
                                                        arrayList8.add((MediaController) ((yc) obj8).a);
                                                    }
                                                    Object obj9 = MusicNotificationListener.a;
                                                    ArrayList arrayListY = k8.Y();
                                                    if (arrayList8.isEmpty() && arrayListY.isEmpty()) {
                                                        strY0 = si.a("no_media_sessions_start_radio_or_bluetooth", new Object[0]);
                                                    } else {
                                                        List listI6 = j3.i0(i3.y0(arrayList8, "\n\n", new a(6, gcVarY), 30), i3.y0(arrayListY, "\n", new w(18), 30));
                                                        ArrayList arrayList9 = new ArrayList();
                                                        for (Object obj10 : listI6) {
                                                            if (!th.A((String) obj10)) {
                                                                arrayList9.add(obj10);
                                                            }
                                                        }
                                                        strY0 = i3.y0(arrayList9, "\n\n", null, 62);
                                                    }
                                                } else {
                                                    strY0 = si.a("no_music_access_enable_ReferencePanel_music_information", new Object[0]);
                                                }
                                                textView.setText(strY0);
                                                textView.setTextSize(17.0f);
                                                int i212 = l8.a;
                                                textView.setTextColor(l8.j(mainActivity11.z()));
                                                textView.setTextIsSelectable(true);
                                                textView.setLineSpacing(0.0f, 1.2f);
                                                String strA117 = si.a("music_diagnostics", new Object[0]);
                                                String strA118 = si.a("source_currently_shown_by_the_widget", new Object[0]);
                                                ScrollView scrollView9 = new ScrollView(mainActivity11);
                                                scrollView9.addView(textView);
                                                i4Var2.f(strA117, strA118, scrollView9).show();
                                                break;
                                            case 21:
                                                k8.m0(mainActivity3);
                                                break;
                                            case 22:
                                                mainActivity3.a0(0);
                                                break;
                                            case 23:
                                                MainActivity mainActivity12 = mainActivity3;
                                                ComponentName componentNameM = mainActivity12.m(0);
                                                if (componentNameM == null) {
                                                    k8.g0(mainActivity12, 0);
                                                } else {
                                                    mainActivity12.Q(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", componentNameM.getPackageName(), null)));
                                                }
                                                break;
                                            case 24:
                                                MainActivity mainActivity13 = mainActivity3;
                                                ArrayList arrayList10 = mainActivity13.w;
                                                int size5 = arrayList10.size();
                                                int i213 = 0;
                                                while (i213 < size5) {
                                                    Object obj11 = arrayList10.get(i213);
                                                    i213++;
                                                    ((b) obj11).c();
                                                }
                                                arrayList10.clear();
                                                mainActivity13.c = false;
                                                mainActivity13.e0();
                                                break;
                                            case 25:
                                                MainActivity mainActivity14 = mainActivity3;
                                                k8.g0(mainActivity14, Integer.valueOf(mainActivity14.B0));
                                                break;
                                            case 26:
                                                MainActivity mainActivity15 = mainActivity3;
                                                mainActivity15.a0(mainActivity15.B0);
                                                break;
                                            case 27:
                                                k8.g0(mainActivity3, 0);
                                                break;
                                            case 28:
                                                MainActivity mainActivity16 = mainActivity3;
                                                if (!mainActivity16.h) {
                                                    List listH = uh.h();
                                                    ArrayList arrayList11 = new ArrayList(k3.n0(listH));
                                                    Iterator it5 = listH.iterator();
                                                    while (it5.hasNext()) {
                                                        arrayList11.add(((zi) it5.next()).b);
                                                    }
                                                    List listJ0 = i3.J0(i3.K0(arrayList11));
                                                    c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity16).setTitle(si.a("vehicle_brand", new Object[0])).setItems((CharSequence[]) listJ0.toArray(new String[0]), new tb(mainActivity16, listJ0)), null, mainActivity16);
                                                }
                                                break;
                                            default:
                                                MainActivity mainActivity17 = mainActivity3;
                                                boolean z5 = mainActivity17.h;
                                                if (!z5 && !z5) {
                                                    try {
                                                        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                                                        intent.setType("image/*");
                                                        intent.addCategory("android.intent.category.OPENABLE");
                                                        intent.addFlags(1);
                                                        mainActivity17.startActivityForResult(intent, 41);
                                                    } catch (ActivityNotFoundException unused) {
                                                        mainActivity17.v0(si.a("no_file_picker_app_on_this_head", new Object[0]));
                                                    }
                                                }
                                                break;
                                        }
                                        return ti.a;
                                    }
                                }, 8)));
                                linearLayout.addView(c9Var.l(si.a("app_language_scope", new Object[0])));
                            } else {
                                str = "loaded_replace";
                                str2 = "";
                                i13 = 0;
                            }
                            linearLayout.addView(c9Var.i(si.a("home_screen_theme_and_background", new Object[i13])));
                            final List listI6 = j3.i0("night", "day", str5);
                            String str16 = null;
                            final int i55 = 1;
                            LinearLayout linearLayoutQ11 = c9Var.q(si.a("theme", new Object[i13]), j3.i0(si.a("night_455", new Object[i13]), si.a("day_456", new Object[i13]), si.a(str5, new Object[i13])), pi.q(listI6.indexOf(mainActivity3.D())), new j6() { // from class: cg
                                @Override // defpackage.j6
                                public final Object b(Object obj6) {
                                    int i310 = i55;
                                    ti tiVar = ti.a;
                                    List list6 = listI6;
                                    MainActivity mainActivity4 = mainActivity3;
                                    int iIntValue3 = ((Integer) obj6).intValue();
                                    switch (i310) {
                                        case 0:
                                            mainActivity4.B().edit().putInt("saver_dim", ((Number) list6.get(iIntValue3)).intValue()).apply();
                                            mainActivity4.e0();
                                            break;
                                        case 1:
                                            mainActivity4.B().edit().putString("theme_mode", (String) list6.get(iIntValue3)).apply();
                                            mainActivity4.e0();
                                            break;
                                        case 2:
                                            mainActivity4.B().edit().putInt("saver_minutes", ((Number) list6.get(iIntValue3)).intValue()).apply();
                                            mainActivity4.h0();
                                            mainActivity4.e0();
                                            break;
                                        default:
                                            String str17 = (String) list6.get(iIntValue3);
                                            str17.getClass();
                                            int i311 = str17.equals("s") ? 104 : str17.equals("l") ? 152 : 128;
                                            int i312 = 700 - i311;
                                            float f2 = i312 - 16.0f;
                                            SharedPreferences.Editor editorPutString = mainActivity4.B().edit().putString("dock_size", str17).putString("desktop_ios_dock", "40.0," + i312 + ".0,1200.0," + i311 + ".0");
                                            for (String str18 : mainActivity4.m0) {
                                                Iterator it5 = j3.i0("ios_", "wall_").iterator();
                                                while (it5.hasNext()) {
                                                    String str19 = ((String) it5.next()) + str18;
                                                    String string2 = mainActivity4.B().getString("desktop_".concat(str19), null);
                                                    if (string2 != null) {
                                                        List listD2 = th.D(string2, new char[]{','});
                                                        ArrayList arrayList6 = new ArrayList();
                                                        Iterator it6 = listD2.iterator();
                                                        while (it6.hasNext()) {
                                                            Float fP = ai.p((String) it6.next());
                                                            if (fP != null) {
                                                                arrayList6.add(fP);
                                                            }
                                                        }
                                                        ArrayList arrayList7 = arrayList6.size() == 4 ? arrayList6 : null;
                                                        if (arrayList7 != null && ((Number) arrayList7.get(1)).floatValue() < f2) {
                                                            if (((Number) arrayList7.get(3)).floatValue() + ((Number) arrayList7.get(1)).floatValue() >= f2 - 40.0f) {
                                                                String strConcat = "desktop_".concat(str19);
                                                                Object obj7 = arrayList7.get(0);
                                                                Object obj8 = arrayList7.get(1);
                                                                Object obj9 = arrayList7.get(2);
                                                                float fFloatValue = f2 - ((Number) arrayList7.get(1)).floatValue();
                                                                editorPutString.putString(strConcat, obj7 + "," + obj8 + "," + obj9 + "," + (fFloatValue >= 40.0f ? fFloatValue : 40.0f));
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            editorPutString.apply();
                                            mainActivity4.e0();
                                            break;
                                    }
                                    return tiVar;
                                }
                            });
                            View[] viewArr17 = new View[1];
                            viewArr17[i13] = linearLayoutQ11;
                            linearLayout.addView(c9Var.d(viewArr17));
                            linearLayout.addView(c9Var.l(si.a("system_follows_the_head_units_day_and", new Object[i13])));
                            View[] viewArr18 = new View[1];
                            viewArr18[i13] = c9Var.q(si.a("surface_style", new Object[i13]), j3.i0(si.a("graphite", new Object[i13]), si.a("surface_frosted", new Object[i13])), pi.s(mainActivity3.B().getInt(mainActivity3.C(), i13), i13, 1), new ya(mainActivity3, 21));
                            linearLayout.addView(c9Var.d(viewArr18));
                            linearLayout.addView(c9Var.b(si.a("background", new Object[i13])));
                            ArrayList arrayListQ0 = i3.q0(mainActivity3.z() ? j3.i0(si.a("evening_city", new Object[i13]), si.a("midnight", new Object[i13]), si.a("northern_lights", new Object[i13]), si.a("sunset", new Object[i13]), si.a("ocean", new Object[i13]), si.a("graphite", new Object[i13])) : j3.i0(si.a("daytime_city", new Object[i13]), si.a("blue_horizon", new Object[i13]), si.a("fresh_breeze", new Object[i13]), si.a("warm_day", new Object[i13]), si.a("ocean", new Object[i13]), si.a("graphite", new Object[i13])));
                            int size3 = arrayListQ0.size();
                            int i56 = 0;
                            int i57 = 0;
                            while (i57 < size3) {
                                Object obj6 = arrayListQ0.get(i57);
                                i57++;
                                int i58 = i56 + 1;
                                if (i56 < 0) {
                                    j3.m0();
                                    throw null;
                                }
                                LinearLayout linearLayout2 = new LinearLayout(mainActivity3);
                                int i59 = 0;
                                for (Object obj7 : (List) obj6) {
                                    int i60 = i59 + 1;
                                    if (i59 < 0) {
                                        j3.m0();
                                        throw null;
                                    }
                                    String str17 = (String) obj7;
                                    int i61 = (i56 * 3) + i59;
                                    ArrayList arrayList6 = arrayListQ0;
                                    int i62 = size3;
                                    int i63 = i56;
                                    LinearLayout linearLayoutS = c9Var.s(new kf(mainActivity3, i61, mainActivity3.z(), false), str17, mainActivity3.B().getInt("background", 0) == i61, new c3(mainActivity3, i61, 10));
                                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2, 1.0f);
                                    if (i59 > 0) {
                                        layoutParams.leftMargin = mainActivity3.r(12);
                                    }
                                    linearLayout2.addView(linearLayoutS, layoutParams);
                                    arrayListQ0 = arrayList6;
                                    i59 = i60;
                                    size3 = i62;
                                    i56 = i63;
                                }
                                ArrayList arrayList7 = arrayListQ0;
                                int i64 = size3;
                                int i65 = i56;
                                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
                                if (i65 > 0) {
                                    layoutParams2.topMargin = mainActivity3.r(12);
                                }
                                linearLayout.addView(linearLayout2, layoutParams2);
                                i56 = i58;
                                arrayListQ0 = arrayList7;
                                size3 = i64;
                            }
                            linearLayout.addView(c9Var.b(si.a("font", new Object[0])));
                            boolean zG = x3.g(mainActivity3);
                            boolean zC = x3.c(mainActivity3);
                            String str18 = e(mainActivity3.getSharedPreferences("panel", 0).getString("font_choice", str2), "manrope") ? "manrope" : "elastre";
                            final int i66 = 4;
                            LinearLayout linearLayoutN = c9Var.n(si.a("font_standard", new Object[0]), null, (zC || !e(str18, "elastre")) ? null : si.a("icon_set_selected", new Object[0]), false, new y5() { // from class: jg
                                @Override // defpackage.y5
                                public final Object a() {
                                    String strY0;
                                    switch (i66) {
                                        case 0:
                                            k8.d(mainActivity3);
                                            break;
                                        case 1:
                                            k8.c(mainActivity3, null);
                                            break;
                                        case 2:
                                            MainActivity mainActivity4 = mainActivity3;
                                            ArrayList arrayListC2 = j0.c();
                                            AlertDialog.Builder title = new AlertDialog.Builder(mainActivity4).setTitle(si.a("app_language_choose", new Object[0]));
                                            ArrayList arrayList8 = new ArrayList(k3.n0(arrayListC2));
                                            int size4 = arrayListC2.size();
                                            int i210 = 0;
                                            while (i210 < size4) {
                                                Object obj8 = arrayListC2.get(i210);
                                                i210++;
                                                Object obj9 = (String) obj8;
                                                List list6 = j0.a;
                                                obj9.getClass();
                                                List list7 = j0.a;
                                                int iIndexOf5 = si.a.indexOf(obj9);
                                                if (iIndexOf5 >= 0 && iIndexOf5 < list7.size()) {
                                                    obj9 = list7.get(iIndexOf5);
                                                }
                                                arrayList8.add((String) obj9);
                                            }
                                            CharSequence[] charSequenceArr = (CharSequence[]) arrayList8.toArray(new String[0]);
                                            List list8 = si.a;
                                            AlertDialog.Builder negativeButton = title.setSingleChoiceItems(charSequenceArr, arrayListC2.indexOf(si.b.a), new dg(arrayListC2, mainActivity4)).setNegativeButton(si.a("cancel", new Object[0]), (DialogInterface.OnClickListener) null);
                                            negativeButton.getClass();
                                            k8.w0(negativeButton);
                                            break;
                                        case 3:
                                            MainActivity mainActivity5 = mainActivity3;
                                            mainActivity5.c = false;
                                            mainActivity5.f = true;
                                            mainActivity5.e0();
                                            mainActivity5.X(new j(14));
                                            break;
                                        case 4:
                                            k8.v0("elastre", mainActivity3);
                                            break;
                                        case 5:
                                            k8.v0("manrope", mainActivity3);
                                            break;
                                        case 6:
                                            k8.v0("custom", mainActivity3);
                                            break;
                                        case 7:
                                            k8.K(mainActivity3, true);
                                            break;
                                        case 8:
                                            MainActivity mainActivity6 = mainActivity3;
                                            x3.d(mainActivity6).delete();
                                            SharedPreferences sharedPreferences = mainActivity6.getSharedPreferences("panel", 0);
                                            SharedPreferences.Editor editorRemove = sharedPreferences.edit().remove("font_custom_name").remove("font_custom_kk").remove("font_custom_wght");
                                            if (k8.e(sharedPreferences.getString("font_choice", ""), "custom")) {
                                                editorRemove.remove("font_choice");
                                            }
                                            editorRemove.apply();
                                            z9.d(mainActivity6);
                                            z9.a = true;
                                            mainActivity6.e0();
                                            break;
                                        case 9:
                                            MainActivity mainActivity7 = mainActivity3;
                                            i7.g(mainActivity7, "");
                                            mainActivity7.e0();
                                            break;
                                        case 10:
                                            k8.O(mainActivity3, true, true);
                                            break;
                                        case 11:
                                            k8.O(mainActivity3, false, true);
                                            break;
                                        case 12:
                                            k8.Q(mainActivity3, false);
                                            break;
                                        case 13:
                                            k8.Q(mainActivity3, true);
                                            break;
                                        case 14:
                                            MainActivity mainActivity8 = mainActivity3;
                                            new i0(mainActivity8, mainActivity8.z(), si.a("split_left", new Object[0]), false, new lg(mainActivity8, 7)).show();
                                            break;
                                        case 15:
                                            k8.C(mainActivity3);
                                            break;
                                        case 16:
                                            MainActivity mainActivity9 = mainActivity3;
                                            mainActivity9.u = "dock";
                                            mainActivity9.s = null;
                                            mainActivity9.t = 0;
                                            mainActivity9.e0();
                                            break;
                                        case 17:
                                            k8.g0(mainActivity3, 1);
                                            break;
                                        case 18:
                                            MainActivity mainActivity10 = mainActivity3;
                                            md mdVar2 = md.a;
                                            List list9 = md.d;
                                            if (list9 == null) {
                                                mdVar2.c(mainActivity10);
                                                list9 = u4.a;
                                            }
                                            List list10 = list9;
                                            if (list10.isEmpty()) {
                                                mainActivity10.v0(si.a("radio_city_loading", new Object[0]));
                                            } else {
                                                Map mapJ0 = hb.j0(new yc("RU", si.a("country_ru", new Object[0])), new yc("BY", si.a("country_by", new Object[0])), new yc("KZ", si.a("country_kz", new Object[0])));
                                                i4 i4Var = new i4(mainActivity10, mainActivity10.z());
                                                ViewGroup viewGroupL = mainActivity10.l();
                                                EditText editText = new EditText(mainActivity10);
                                                editText.setHint(si.a("radio_city_search", new Object[0]));
                                                editText.setTextSize(20.0f);
                                                editText.setTextColor(mainActivity10.o);
                                                editText.setHintTextColor(mainActivity10.p);
                                                editText.setSingleLine(true);
                                                editText.setBackground(mainActivity10.m0(mainActivity10.q, 14));
                                                editText.setPadding(mainActivity10.r(16), 0, mainActivity10.r(16), 0);
                                                viewGroupL.addView(editText, new LinearLayout.LayoutParams(-1, mainActivity10.r(60)));
                                                LinearLayout linearLayoutL = mainActivity10.l();
                                                ScrollView scrollView8 = new ScrollView(mainActivity10);
                                                scrollView8.addView(linearLayoutL);
                                                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, mainActivity10.r(420));
                                                layoutParams3.topMargin = mainActivity10.r(10);
                                                viewGroupL.addView(scrollView8, layoutParams3);
                                                Dialog dialogF = i4Var.f(si.a("radio_city", new Object[0]), si.a("radio_city_hint", new Object[0]), viewGroupL);
                                                editText.addTextChangedListener(new f0(linearLayoutL, i4Var, mainActivity10, list10, dialogF, mapJ0, 1));
                                                k8.k0(linearLayoutL, i4Var, mainActivity10, list10, dialogF, mapJ0, "");
                                                dialogF.show();
                                            }
                                            break;
                                        case 19:
                                            mainActivity3.f0();
                                            break;
                                        case 20:
                                            MainActivity mainActivity11 = mainActivity3;
                                            i4 i4Var2 = new i4(mainActivity11, mainActivity11.z());
                                            TextView textView = new TextView(mainActivity11);
                                            gc gcVarY = mainActivity11.y();
                                            if (gcVarY.n) {
                                                ArrayList arrayList9 = gcVarY.h;
                                                ArrayList arrayList10 = new ArrayList(k3.n0(arrayList9));
                                                int size5 = arrayList9.size();
                                                int i211 = 0;
                                                while (i211 < size5) {
                                                    Object obj10 = arrayList9.get(i211);
                                                    i211++;
                                                    arrayList10.add((MediaController) ((yc) obj10).a);
                                                }
                                                Object obj11 = MusicNotificationListener.a;
                                                ArrayList arrayListY = k8.Y();
                                                if (arrayList10.isEmpty() && arrayListY.isEmpty()) {
                                                    strY0 = si.a("no_media_sessions_start_radio_or_bluetooth", new Object[0]);
                                                } else {
                                                    List listI7 = j3.i0(i3.y0(arrayList10, "\n\n", new a(6, gcVarY), 30), i3.y0(arrayListY, "\n", new w(18), 30));
                                                    ArrayList arrayList11 = new ArrayList();
                                                    for (Object obj12 : listI7) {
                                                        if (!th.A((String) obj12)) {
                                                            arrayList11.add(obj12);
                                                        }
                                                    }
                                                    strY0 = i3.y0(arrayList11, "\n\n", null, 62);
                                                }
                                            } else {
                                                strY0 = si.a("no_music_access_enable_ReferencePanel_music_information", new Object[0]);
                                            }
                                            textView.setText(strY0);
                                            textView.setTextSize(17.0f);
                                            int i212 = l8.a;
                                            textView.setTextColor(l8.j(mainActivity11.z()));
                                            textView.setTextIsSelectable(true);
                                            textView.setLineSpacing(0.0f, 1.2f);
                                            String strA117 = si.a("music_diagnostics", new Object[0]);
                                            String strA118 = si.a("source_currently_shown_by_the_widget", new Object[0]);
                                            ScrollView scrollView9 = new ScrollView(mainActivity11);
                                            scrollView9.addView(textView);
                                            i4Var2.f(strA117, strA118, scrollView9).show();
                                            break;
                                        case 21:
                                            k8.m0(mainActivity3);
                                            break;
                                        case 22:
                                            mainActivity3.a0(0);
                                            break;
                                        case 23:
                                            MainActivity mainActivity12 = mainActivity3;
                                            ComponentName componentNameM = mainActivity12.m(0);
                                            if (componentNameM == null) {
                                                k8.g0(mainActivity12, 0);
                                            } else {
                                                mainActivity12.Q(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", componentNameM.getPackageName(), null)));
                                            }
                                            break;
                                        case 24:
                                            MainActivity mainActivity13 = mainActivity3;
                                            ArrayList arrayList12 = mainActivity13.w;
                                            int size6 = arrayList12.size();
                                            int i213 = 0;
                                            while (i213 < size6) {
                                                Object obj13 = arrayList12.get(i213);
                                                i213++;
                                                ((b) obj13).c();
                                            }
                                            arrayList12.clear();
                                            mainActivity13.c = false;
                                            mainActivity13.e0();
                                            break;
                                        case 25:
                                            MainActivity mainActivity14 = mainActivity3;
                                            k8.g0(mainActivity14, Integer.valueOf(mainActivity14.B0));
                                            break;
                                        case 26:
                                            MainActivity mainActivity15 = mainActivity3;
                                            mainActivity15.a0(mainActivity15.B0);
                                            break;
                                        case 27:
                                            k8.g0(mainActivity3, 0);
                                            break;
                                        case 28:
                                            MainActivity mainActivity16 = mainActivity3;
                                            if (!mainActivity16.h) {
                                                List listH = uh.h();
                                                ArrayList arrayList13 = new ArrayList(k3.n0(listH));
                                                Iterator it5 = listH.iterator();
                                                while (it5.hasNext()) {
                                                    arrayList13.add(((zi) it5.next()).b);
                                                }
                                                List listJ0 = i3.J0(i3.K0(arrayList13));
                                                c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity16).setTitle(si.a("vehicle_brand", new Object[0])).setItems((CharSequence[]) listJ0.toArray(new String[0]), new tb(mainActivity16, listJ0)), null, mainActivity16);
                                            }
                                            break;
                                        default:
                                            MainActivity mainActivity17 = mainActivity3;
                                            boolean z5 = mainActivity17.h;
                                            if (!z5 && !z5) {
                                                try {
                                                    Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                                                    intent.setType("image/*");
                                                    intent.addCategory("android.intent.category.OPENABLE");
                                                    intent.addFlags(1);
                                                    mainActivity17.startActivityForResult(intent, 41);
                                                } catch (ActivityNotFoundException unused) {
                                                    mainActivity17.v0(si.a("no_file_picker_app_on_this_head", new Object[0]));
                                                }
                                            }
                                            break;
                                    }
                                    return ti.a;
                                }
                            });
                            String strA23 = si.a("font_manrope", new Object[0]);
                            String strA24 = (zC || !e(str18, "manrope")) ? null : si.a("icon_set_selected", new Object[0]);
                            final int i67 = 5;
                            ArrayList arrayListJ4 = j3.j0(linearLayoutN, c9Var.n(strA23, null, strA24, false, new y5() { // from class: jg
                                @Override // defpackage.y5
                                public final Object a() {
                                    String strY0;
                                    switch (i67) {
                                        case 0:
                                            k8.d(mainActivity3);
                                            break;
                                        case 1:
                                            k8.c(mainActivity3, null);
                                            break;
                                        case 2:
                                            MainActivity mainActivity4 = mainActivity3;
                                            ArrayList arrayListC2 = j0.c();
                                            AlertDialog.Builder title = new AlertDialog.Builder(mainActivity4).setTitle(si.a("app_language_choose", new Object[0]));
                                            ArrayList arrayList8 = new ArrayList(k3.n0(arrayListC2));
                                            int size4 = arrayListC2.size();
                                            int i210 = 0;
                                            while (i210 < size4) {
                                                Object obj8 = arrayListC2.get(i210);
                                                i210++;
                                                Object obj9 = (String) obj8;
                                                List list6 = j0.a;
                                                obj9.getClass();
                                                List list7 = j0.a;
                                                int iIndexOf5 = si.a.indexOf(obj9);
                                                if (iIndexOf5 >= 0 && iIndexOf5 < list7.size()) {
                                                    obj9 = list7.get(iIndexOf5);
                                                }
                                                arrayList8.add((String) obj9);
                                            }
                                            CharSequence[] charSequenceArr = (CharSequence[]) arrayList8.toArray(new String[0]);
                                            List list8 = si.a;
                                            AlertDialog.Builder negativeButton = title.setSingleChoiceItems(charSequenceArr, arrayListC2.indexOf(si.b.a), new dg(arrayListC2, mainActivity4)).setNegativeButton(si.a("cancel", new Object[0]), (DialogInterface.OnClickListener) null);
                                            negativeButton.getClass();
                                            k8.w0(negativeButton);
                                            break;
                                        case 3:
                                            MainActivity mainActivity5 = mainActivity3;
                                            mainActivity5.c = false;
                                            mainActivity5.f = true;
                                            mainActivity5.e0();
                                            mainActivity5.X(new j(14));
                                            break;
                                        case 4:
                                            k8.v0("elastre", mainActivity3);
                                            break;
                                        case 5:
                                            k8.v0("manrope", mainActivity3);
                                            break;
                                        case 6:
                                            k8.v0("custom", mainActivity3);
                                            break;
                                        case 7:
                                            k8.K(mainActivity3, true);
                                            break;
                                        case 8:
                                            MainActivity mainActivity6 = mainActivity3;
                                            x3.d(mainActivity6).delete();
                                            SharedPreferences sharedPreferences = mainActivity6.getSharedPreferences("panel", 0);
                                            SharedPreferences.Editor editorRemove = sharedPreferences.edit().remove("font_custom_name").remove("font_custom_kk").remove("font_custom_wght");
                                            if (k8.e(sharedPreferences.getString("font_choice", ""), "custom")) {
                                                editorRemove.remove("font_choice");
                                            }
                                            editorRemove.apply();
                                            z9.d(mainActivity6);
                                            z9.a = true;
                                            mainActivity6.e0();
                                            break;
                                        case 9:
                                            MainActivity mainActivity7 = mainActivity3;
                                            i7.g(mainActivity7, "");
                                            mainActivity7.e0();
                                            break;
                                        case 10:
                                            k8.O(mainActivity3, true, true);
                                            break;
                                        case 11:
                                            k8.O(mainActivity3, false, true);
                                            break;
                                        case 12:
                                            k8.Q(mainActivity3, false);
                                            break;
                                        case 13:
                                            k8.Q(mainActivity3, true);
                                            break;
                                        case 14:
                                            MainActivity mainActivity8 = mainActivity3;
                                            new i0(mainActivity8, mainActivity8.z(), si.a("split_left", new Object[0]), false, new lg(mainActivity8, 7)).show();
                                            break;
                                        case 15:
                                            k8.C(mainActivity3);
                                            break;
                                        case 16:
                                            MainActivity mainActivity9 = mainActivity3;
                                            mainActivity9.u = "dock";
                                            mainActivity9.s = null;
                                            mainActivity9.t = 0;
                                            mainActivity9.e0();
                                            break;
                                        case 17:
                                            k8.g0(mainActivity3, 1);
                                            break;
                                        case 18:
                                            MainActivity mainActivity10 = mainActivity3;
                                            md mdVar2 = md.a;
                                            List list9 = md.d;
                                            if (list9 == null) {
                                                mdVar2.c(mainActivity10);
                                                list9 = u4.a;
                                            }
                                            List list10 = list9;
                                            if (list10.isEmpty()) {
                                                mainActivity10.v0(si.a("radio_city_loading", new Object[0]));
                                            } else {
                                                Map mapJ0 = hb.j0(new yc("RU", si.a("country_ru", new Object[0])), new yc("BY", si.a("country_by", new Object[0])), new yc("KZ", si.a("country_kz", new Object[0])));
                                                i4 i4Var = new i4(mainActivity10, mainActivity10.z());
                                                ViewGroup viewGroupL = mainActivity10.l();
                                                EditText editText = new EditText(mainActivity10);
                                                editText.setHint(si.a("radio_city_search", new Object[0]));
                                                editText.setTextSize(20.0f);
                                                editText.setTextColor(mainActivity10.o);
                                                editText.setHintTextColor(mainActivity10.p);
                                                editText.setSingleLine(true);
                                                editText.setBackground(mainActivity10.m0(mainActivity10.q, 14));
                                                editText.setPadding(mainActivity10.r(16), 0, mainActivity10.r(16), 0);
                                                viewGroupL.addView(editText, new LinearLayout.LayoutParams(-1, mainActivity10.r(60)));
                                                LinearLayout linearLayoutL = mainActivity10.l();
                                                ScrollView scrollView8 = new ScrollView(mainActivity10);
                                                scrollView8.addView(linearLayoutL);
                                                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, mainActivity10.r(420));
                                                layoutParams3.topMargin = mainActivity10.r(10);
                                                viewGroupL.addView(scrollView8, layoutParams3);
                                                Dialog dialogF = i4Var.f(si.a("radio_city", new Object[0]), si.a("radio_city_hint", new Object[0]), viewGroupL);
                                                editText.addTextChangedListener(new f0(linearLayoutL, i4Var, mainActivity10, list10, dialogF, mapJ0, 1));
                                                k8.k0(linearLayoutL, i4Var, mainActivity10, list10, dialogF, mapJ0, "");
                                                dialogF.show();
                                            }
                                            break;
                                        case 19:
                                            mainActivity3.f0();
                                            break;
                                        case 20:
                                            MainActivity mainActivity11 = mainActivity3;
                                            i4 i4Var2 = new i4(mainActivity11, mainActivity11.z());
                                            TextView textView = new TextView(mainActivity11);
                                            gc gcVarY = mainActivity11.y();
                                            if (gcVarY.n) {
                                                ArrayList arrayList9 = gcVarY.h;
                                                ArrayList arrayList10 = new ArrayList(k3.n0(arrayList9));
                                                int size5 = arrayList9.size();
                                                int i211 = 0;
                                                while (i211 < size5) {
                                                    Object obj10 = arrayList9.get(i211);
                                                    i211++;
                                                    arrayList10.add((MediaController) ((yc) obj10).a);
                                                }
                                                Object obj11 = MusicNotificationListener.a;
                                                ArrayList arrayListY = k8.Y();
                                                if (arrayList10.isEmpty() && arrayListY.isEmpty()) {
                                                    strY0 = si.a("no_media_sessions_start_radio_or_bluetooth", new Object[0]);
                                                } else {
                                                    List listI7 = j3.i0(i3.y0(arrayList10, "\n\n", new a(6, gcVarY), 30), i3.y0(arrayListY, "\n", new w(18), 30));
                                                    ArrayList arrayList11 = new ArrayList();
                                                    for (Object obj12 : listI7) {
                                                        if (!th.A((String) obj12)) {
                                                            arrayList11.add(obj12);
                                                        }
                                                    }
                                                    strY0 = i3.y0(arrayList11, "\n\n", null, 62);
                                                }
                                            } else {
                                                strY0 = si.a("no_music_access_enable_ReferencePanel_music_information", new Object[0]);
                                            }
                                            textView.setText(strY0);
                                            textView.setTextSize(17.0f);
                                            int i212 = l8.a;
                                            textView.setTextColor(l8.j(mainActivity11.z()));
                                            textView.setTextIsSelectable(true);
                                            textView.setLineSpacing(0.0f, 1.2f);
                                            String strA117 = si.a("music_diagnostics", new Object[0]);
                                            String strA118 = si.a("source_currently_shown_by_the_widget", new Object[0]);
                                            ScrollView scrollView9 = new ScrollView(mainActivity11);
                                            scrollView9.addView(textView);
                                            i4Var2.f(strA117, strA118, scrollView9).show();
                                            break;
                                        case 21:
                                            k8.m0(mainActivity3);
                                            break;
                                        case 22:
                                            mainActivity3.a0(0);
                                            break;
                                        case 23:
                                            MainActivity mainActivity12 = mainActivity3;
                                            ComponentName componentNameM = mainActivity12.m(0);
                                            if (componentNameM == null) {
                                                k8.g0(mainActivity12, 0);
                                            } else {
                                                mainActivity12.Q(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", componentNameM.getPackageName(), null)));
                                            }
                                            break;
                                        case 24:
                                            MainActivity mainActivity13 = mainActivity3;
                                            ArrayList arrayList12 = mainActivity13.w;
                                            int size6 = arrayList12.size();
                                            int i213 = 0;
                                            while (i213 < size6) {
                                                Object obj13 = arrayList12.get(i213);
                                                i213++;
                                                ((b) obj13).c();
                                            }
                                            arrayList12.clear();
                                            mainActivity13.c = false;
                                            mainActivity13.e0();
                                            break;
                                        case 25:
                                            MainActivity mainActivity14 = mainActivity3;
                                            k8.g0(mainActivity14, Integer.valueOf(mainActivity14.B0));
                                            break;
                                        case 26:
                                            MainActivity mainActivity15 = mainActivity3;
                                            mainActivity15.a0(mainActivity15.B0);
                                            break;
                                        case 27:
                                            k8.g0(mainActivity3, 0);
                                            break;
                                        case 28:
                                            MainActivity mainActivity16 = mainActivity3;
                                            if (!mainActivity16.h) {
                                                List listH = uh.h();
                                                ArrayList arrayList13 = new ArrayList(k3.n0(listH));
                                                Iterator it5 = listH.iterator();
                                                while (it5.hasNext()) {
                                                    arrayList13.add(((zi) it5.next()).b);
                                                }
                                                List listJ0 = i3.J0(i3.K0(arrayList13));
                                                c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity16).setTitle(si.a("vehicle_brand", new Object[0])).setItems((CharSequence[]) listJ0.toArray(new String[0]), new tb(mainActivity16, listJ0)), null, mainActivity16);
                                            }
                                            break;
                                        default:
                                            MainActivity mainActivity17 = mainActivity3;
                                            boolean z5 = mainActivity17.h;
                                            if (!z5 && !z5) {
                                                try {
                                                    Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                                                    intent.setType("image/*");
                                                    intent.addCategory("android.intent.category.OPENABLE");
                                                    intent.addFlags(1);
                                                    mainActivity17.startActivityForResult(intent, 41);
                                                } catch (ActivityNotFoundException unused) {
                                                    mainActivity17.v0(si.a("no_file_picker_app_on_this_head", new Object[0]));
                                                }
                                            }
                                            break;
                                    }
                                    return ti.a;
                                }
                            }));
                            if (zG) {
                                String strH = x3.h(mainActivity3);
                                if (strH == null) {
                                    strH = si.a("font_custom", new Object[0]);
                                }
                                final int i68 = 6;
                                arrayListJ4.add(c9Var.n(strH, si.a("font_custom", new Object[0]), zC ? si.a("icon_set_selected", new Object[0]) : null, false, new y5() { // from class: jg
                                    @Override // defpackage.y5
                                    public final Object a() {
                                        String strY0;
                                        switch (i68) {
                                            case 0:
                                                k8.d(mainActivity3);
                                                break;
                                            case 1:
                                                k8.c(mainActivity3, null);
                                                break;
                                            case 2:
                                                MainActivity mainActivity4 = mainActivity3;
                                                ArrayList arrayListC2 = j0.c();
                                                AlertDialog.Builder title = new AlertDialog.Builder(mainActivity4).setTitle(si.a("app_language_choose", new Object[0]));
                                                ArrayList arrayList8 = new ArrayList(k3.n0(arrayListC2));
                                                int size4 = arrayListC2.size();
                                                int i210 = 0;
                                                while (i210 < size4) {
                                                    Object obj8 = arrayListC2.get(i210);
                                                    i210++;
                                                    Object obj9 = (String) obj8;
                                                    List list6 = j0.a;
                                                    obj9.getClass();
                                                    List list7 = j0.a;
                                                    int iIndexOf5 = si.a.indexOf(obj9);
                                                    if (iIndexOf5 >= 0 && iIndexOf5 < list7.size()) {
                                                        obj9 = list7.get(iIndexOf5);
                                                    }
                                                    arrayList8.add((String) obj9);
                                                }
                                                CharSequence[] charSequenceArr = (CharSequence[]) arrayList8.toArray(new String[0]);
                                                List list8 = si.a;
                                                AlertDialog.Builder negativeButton = title.setSingleChoiceItems(charSequenceArr, arrayListC2.indexOf(si.b.a), new dg(arrayListC2, mainActivity4)).setNegativeButton(si.a("cancel", new Object[0]), (DialogInterface.OnClickListener) null);
                                                negativeButton.getClass();
                                                k8.w0(negativeButton);
                                                break;
                                            case 3:
                                                MainActivity mainActivity5 = mainActivity3;
                                                mainActivity5.c = false;
                                                mainActivity5.f = true;
                                                mainActivity5.e0();
                                                mainActivity5.X(new j(14));
                                                break;
                                            case 4:
                                                k8.v0("elastre", mainActivity3);
                                                break;
                                            case 5:
                                                k8.v0("manrope", mainActivity3);
                                                break;
                                            case 6:
                                                k8.v0("custom", mainActivity3);
                                                break;
                                            case 7:
                                                k8.K(mainActivity3, true);
                                                break;
                                            case 8:
                                                MainActivity mainActivity6 = mainActivity3;
                                                x3.d(mainActivity6).delete();
                                                SharedPreferences sharedPreferences = mainActivity6.getSharedPreferences("panel", 0);
                                                SharedPreferences.Editor editorRemove = sharedPreferences.edit().remove("font_custom_name").remove("font_custom_kk").remove("font_custom_wght");
                                                if (k8.e(sharedPreferences.getString("font_choice", ""), "custom")) {
                                                    editorRemove.remove("font_choice");
                                                }
                                                editorRemove.apply();
                                                z9.d(mainActivity6);
                                                z9.a = true;
                                                mainActivity6.e0();
                                                break;
                                            case 9:
                                                MainActivity mainActivity7 = mainActivity3;
                                                i7.g(mainActivity7, "");
                                                mainActivity7.e0();
                                                break;
                                            case 10:
                                                k8.O(mainActivity3, true, true);
                                                break;
                                            case 11:
                                                k8.O(mainActivity3, false, true);
                                                break;
                                            case 12:
                                                k8.Q(mainActivity3, false);
                                                break;
                                            case 13:
                                                k8.Q(mainActivity3, true);
                                                break;
                                            case 14:
                                                MainActivity mainActivity8 = mainActivity3;
                                                new i0(mainActivity8, mainActivity8.z(), si.a("split_left", new Object[0]), false, new lg(mainActivity8, 7)).show();
                                                break;
                                            case 15:
                                                k8.C(mainActivity3);
                                                break;
                                            case 16:
                                                MainActivity mainActivity9 = mainActivity3;
                                                mainActivity9.u = "dock";
                                                mainActivity9.s = null;
                                                mainActivity9.t = 0;
                                                mainActivity9.e0();
                                                break;
                                            case 17:
                                                k8.g0(mainActivity3, 1);
                                                break;
                                            case 18:
                                                MainActivity mainActivity10 = mainActivity3;
                                                md mdVar2 = md.a;
                                                List list9 = md.d;
                                                if (list9 == null) {
                                                    mdVar2.c(mainActivity10);
                                                    list9 = u4.a;
                                                }
                                                List list10 = list9;
                                                if (list10.isEmpty()) {
                                                    mainActivity10.v0(si.a("radio_city_loading", new Object[0]));
                                                } else {
                                                    Map mapJ0 = hb.j0(new yc("RU", si.a("country_ru", new Object[0])), new yc("BY", si.a("country_by", new Object[0])), new yc("KZ", si.a("country_kz", new Object[0])));
                                                    i4 i4Var = new i4(mainActivity10, mainActivity10.z());
                                                    ViewGroup viewGroupL = mainActivity10.l();
                                                    EditText editText = new EditText(mainActivity10);
                                                    editText.setHint(si.a("radio_city_search", new Object[0]));
                                                    editText.setTextSize(20.0f);
                                                    editText.setTextColor(mainActivity10.o);
                                                    editText.setHintTextColor(mainActivity10.p);
                                                    editText.setSingleLine(true);
                                                    editText.setBackground(mainActivity10.m0(mainActivity10.q, 14));
                                                    editText.setPadding(mainActivity10.r(16), 0, mainActivity10.r(16), 0);
                                                    viewGroupL.addView(editText, new LinearLayout.LayoutParams(-1, mainActivity10.r(60)));
                                                    LinearLayout linearLayoutL = mainActivity10.l();
                                                    ScrollView scrollView8 = new ScrollView(mainActivity10);
                                                    scrollView8.addView(linearLayoutL);
                                                    LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, mainActivity10.r(420));
                                                    layoutParams3.topMargin = mainActivity10.r(10);
                                                    viewGroupL.addView(scrollView8, layoutParams3);
                                                    Dialog dialogF = i4Var.f(si.a("radio_city", new Object[0]), si.a("radio_city_hint", new Object[0]), viewGroupL);
                                                    editText.addTextChangedListener(new f0(linearLayoutL, i4Var, mainActivity10, list10, dialogF, mapJ0, 1));
                                                    k8.k0(linearLayoutL, i4Var, mainActivity10, list10, dialogF, mapJ0, "");
                                                    dialogF.show();
                                                }
                                                break;
                                            case 19:
                                                mainActivity3.f0();
                                                break;
                                            case 20:
                                                MainActivity mainActivity11 = mainActivity3;
                                                i4 i4Var2 = new i4(mainActivity11, mainActivity11.z());
                                                TextView textView = new TextView(mainActivity11);
                                                gc gcVarY = mainActivity11.y();
                                                if (gcVarY.n) {
                                                    ArrayList arrayList9 = gcVarY.h;
                                                    ArrayList arrayList10 = new ArrayList(k3.n0(arrayList9));
                                                    int size5 = arrayList9.size();
                                                    int i211 = 0;
                                                    while (i211 < size5) {
                                                        Object obj10 = arrayList9.get(i211);
                                                        i211++;
                                                        arrayList10.add((MediaController) ((yc) obj10).a);
                                                    }
                                                    Object obj11 = MusicNotificationListener.a;
                                                    ArrayList arrayListY = k8.Y();
                                                    if (arrayList10.isEmpty() && arrayListY.isEmpty()) {
                                                        strY0 = si.a("no_media_sessions_start_radio_or_bluetooth", new Object[0]);
                                                    } else {
                                                        List listI7 = j3.i0(i3.y0(arrayList10, "\n\n", new a(6, gcVarY), 30), i3.y0(arrayListY, "\n", new w(18), 30));
                                                        ArrayList arrayList11 = new ArrayList();
                                                        for (Object obj12 : listI7) {
                                                            if (!th.A((String) obj12)) {
                                                                arrayList11.add(obj12);
                                                            }
                                                        }
                                                        strY0 = i3.y0(arrayList11, "\n\n", null, 62);
                                                    }
                                                } else {
                                                    strY0 = si.a("no_music_access_enable_ReferencePanel_music_information", new Object[0]);
                                                }
                                                textView.setText(strY0);
                                                textView.setTextSize(17.0f);
                                                int i212 = l8.a;
                                                textView.setTextColor(l8.j(mainActivity11.z()));
                                                textView.setTextIsSelectable(true);
                                                textView.setLineSpacing(0.0f, 1.2f);
                                                String strA117 = si.a("music_diagnostics", new Object[0]);
                                                String strA118 = si.a("source_currently_shown_by_the_widget", new Object[0]);
                                                ScrollView scrollView9 = new ScrollView(mainActivity11);
                                                scrollView9.addView(textView);
                                                i4Var2.f(strA117, strA118, scrollView9).show();
                                                break;
                                            case 21:
                                                k8.m0(mainActivity3);
                                                break;
                                            case 22:
                                                mainActivity3.a0(0);
                                                break;
                                            case 23:
                                                MainActivity mainActivity12 = mainActivity3;
                                                ComponentName componentNameM = mainActivity12.m(0);
                                                if (componentNameM == null) {
                                                    k8.g0(mainActivity12, 0);
                                                } else {
                                                    mainActivity12.Q(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", componentNameM.getPackageName(), null)));
                                                }
                                                break;
                                            case 24:
                                                MainActivity mainActivity13 = mainActivity3;
                                                ArrayList arrayList12 = mainActivity13.w;
                                                int size6 = arrayList12.size();
                                                int i213 = 0;
                                                while (i213 < size6) {
                                                    Object obj13 = arrayList12.get(i213);
                                                    i213++;
                                                    ((b) obj13).c();
                                                }
                                                arrayList12.clear();
                                                mainActivity13.c = false;
                                                mainActivity13.e0();
                                                break;
                                            case 25:
                                                MainActivity mainActivity14 = mainActivity3;
                                                k8.g0(mainActivity14, Integer.valueOf(mainActivity14.B0));
                                                break;
                                            case 26:
                                                MainActivity mainActivity15 = mainActivity3;
                                                mainActivity15.a0(mainActivity15.B0);
                                                break;
                                            case 27:
                                                k8.g0(mainActivity3, 0);
                                                break;
                                            case 28:
                                                MainActivity mainActivity16 = mainActivity3;
                                                if (!mainActivity16.h) {
                                                    List listH = uh.h();
                                                    ArrayList arrayList13 = new ArrayList(k3.n0(listH));
                                                    Iterator it5 = listH.iterator();
                                                    while (it5.hasNext()) {
                                                        arrayList13.add(((zi) it5.next()).b);
                                                    }
                                                    List listJ0 = i3.J0(i3.K0(arrayList13));
                                                    c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity16).setTitle(si.a("vehicle_brand", new Object[0])).setItems((CharSequence[]) listJ0.toArray(new String[0]), new tb(mainActivity16, listJ0)), null, mainActivity16);
                                                }
                                                break;
                                            default:
                                                MainActivity mainActivity17 = mainActivity3;
                                                boolean z5 = mainActivity17.h;
                                                if (!z5 && !z5) {
                                                    try {
                                                        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                                                        intent.setType("image/*");
                                                        intent.addCategory("android.intent.category.OPENABLE");
                                                        intent.addFlags(1);
                                                        mainActivity17.startActivityForResult(intent, 41);
                                                    } catch (ActivityNotFoundException unused) {
                                                        mainActivity17.v0(si.a("no_file_picker_app_on_this_head", new Object[0]));
                                                    }
                                                }
                                                break;
                                        }
                                        return ti.a;
                                    }
                                }));
                            }
                            final int i69 = 7;
                            arrayListJ4.add(c9.o(c9Var, si.a("font_load", new Object[0]), si.a("font_load_hint", new Object[0]), null, new y5() { // from class: jg
                                @Override // defpackage.y5
                                public final Object a() {
                                    String strY0;
                                    switch (i69) {
                                        case 0:
                                            k8.d(mainActivity3);
                                            break;
                                        case 1:
                                            k8.c(mainActivity3, null);
                                            break;
                                        case 2:
                                            MainActivity mainActivity4 = mainActivity3;
                                            ArrayList arrayListC2 = j0.c();
                                            AlertDialog.Builder title = new AlertDialog.Builder(mainActivity4).setTitle(si.a("app_language_choose", new Object[0]));
                                            ArrayList arrayList8 = new ArrayList(k3.n0(arrayListC2));
                                            int size4 = arrayListC2.size();
                                            int i210 = 0;
                                            while (i210 < size4) {
                                                Object obj8 = arrayListC2.get(i210);
                                                i210++;
                                                Object obj9 = (String) obj8;
                                                List list6 = j0.a;
                                                obj9.getClass();
                                                List list7 = j0.a;
                                                int iIndexOf5 = si.a.indexOf(obj9);
                                                if (iIndexOf5 >= 0 && iIndexOf5 < list7.size()) {
                                                    obj9 = list7.get(iIndexOf5);
                                                }
                                                arrayList8.add((String) obj9);
                                            }
                                            CharSequence[] charSequenceArr = (CharSequence[]) arrayList8.toArray(new String[0]);
                                            List list8 = si.a;
                                            AlertDialog.Builder negativeButton = title.setSingleChoiceItems(charSequenceArr, arrayListC2.indexOf(si.b.a), new dg(arrayListC2, mainActivity4)).setNegativeButton(si.a("cancel", new Object[0]), (DialogInterface.OnClickListener) null);
                                            negativeButton.getClass();
                                            k8.w0(negativeButton);
                                            break;
                                        case 3:
                                            MainActivity mainActivity5 = mainActivity3;
                                            mainActivity5.c = false;
                                            mainActivity5.f = true;
                                            mainActivity5.e0();
                                            mainActivity5.X(new j(14));
                                            break;
                                        case 4:
                                            k8.v0("elastre", mainActivity3);
                                            break;
                                        case 5:
                                            k8.v0("manrope", mainActivity3);
                                            break;
                                        case 6:
                                            k8.v0("custom", mainActivity3);
                                            break;
                                        case 7:
                                            k8.K(mainActivity3, true);
                                            break;
                                        case 8:
                                            MainActivity mainActivity6 = mainActivity3;
                                            x3.d(mainActivity6).delete();
                                            SharedPreferences sharedPreferences = mainActivity6.getSharedPreferences("panel", 0);
                                            SharedPreferences.Editor editorRemove = sharedPreferences.edit().remove("font_custom_name").remove("font_custom_kk").remove("font_custom_wght");
                                            if (k8.e(sharedPreferences.getString("font_choice", ""), "custom")) {
                                                editorRemove.remove("font_choice");
                                            }
                                            editorRemove.apply();
                                            z9.d(mainActivity6);
                                            z9.a = true;
                                            mainActivity6.e0();
                                            break;
                                        case 9:
                                            MainActivity mainActivity7 = mainActivity3;
                                            i7.g(mainActivity7, "");
                                            mainActivity7.e0();
                                            break;
                                        case 10:
                                            k8.O(mainActivity3, true, true);
                                            break;
                                        case 11:
                                            k8.O(mainActivity3, false, true);
                                            break;
                                        case 12:
                                            k8.Q(mainActivity3, false);
                                            break;
                                        case 13:
                                            k8.Q(mainActivity3, true);
                                            break;
                                        case 14:
                                            MainActivity mainActivity8 = mainActivity3;
                                            new i0(mainActivity8, mainActivity8.z(), si.a("split_left", new Object[0]), false, new lg(mainActivity8, 7)).show();
                                            break;
                                        case 15:
                                            k8.C(mainActivity3);
                                            break;
                                        case 16:
                                            MainActivity mainActivity9 = mainActivity3;
                                            mainActivity9.u = "dock";
                                            mainActivity9.s = null;
                                            mainActivity9.t = 0;
                                            mainActivity9.e0();
                                            break;
                                        case 17:
                                            k8.g0(mainActivity3, 1);
                                            break;
                                        case 18:
                                            MainActivity mainActivity10 = mainActivity3;
                                            md mdVar2 = md.a;
                                            List list9 = md.d;
                                            if (list9 == null) {
                                                mdVar2.c(mainActivity10);
                                                list9 = u4.a;
                                            }
                                            List list10 = list9;
                                            if (list10.isEmpty()) {
                                                mainActivity10.v0(si.a("radio_city_loading", new Object[0]));
                                            } else {
                                                Map mapJ0 = hb.j0(new yc("RU", si.a("country_ru", new Object[0])), new yc("BY", si.a("country_by", new Object[0])), new yc("KZ", si.a("country_kz", new Object[0])));
                                                i4 i4Var = new i4(mainActivity10, mainActivity10.z());
                                                ViewGroup viewGroupL = mainActivity10.l();
                                                EditText editText = new EditText(mainActivity10);
                                                editText.setHint(si.a("radio_city_search", new Object[0]));
                                                editText.setTextSize(20.0f);
                                                editText.setTextColor(mainActivity10.o);
                                                editText.setHintTextColor(mainActivity10.p);
                                                editText.setSingleLine(true);
                                                editText.setBackground(mainActivity10.m0(mainActivity10.q, 14));
                                                editText.setPadding(mainActivity10.r(16), 0, mainActivity10.r(16), 0);
                                                viewGroupL.addView(editText, new LinearLayout.LayoutParams(-1, mainActivity10.r(60)));
                                                LinearLayout linearLayoutL = mainActivity10.l();
                                                ScrollView scrollView8 = new ScrollView(mainActivity10);
                                                scrollView8.addView(linearLayoutL);
                                                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, mainActivity10.r(420));
                                                layoutParams3.topMargin = mainActivity10.r(10);
                                                viewGroupL.addView(scrollView8, layoutParams3);
                                                Dialog dialogF = i4Var.f(si.a("radio_city", new Object[0]), si.a("radio_city_hint", new Object[0]), viewGroupL);
                                                editText.addTextChangedListener(new f0(linearLayoutL, i4Var, mainActivity10, list10, dialogF, mapJ0, 1));
                                                k8.k0(linearLayoutL, i4Var, mainActivity10, list10, dialogF, mapJ0, "");
                                                dialogF.show();
                                            }
                                            break;
                                        case 19:
                                            mainActivity3.f0();
                                            break;
                                        case 20:
                                            MainActivity mainActivity11 = mainActivity3;
                                            i4 i4Var2 = new i4(mainActivity11, mainActivity11.z());
                                            TextView textView = new TextView(mainActivity11);
                                            gc gcVarY = mainActivity11.y();
                                            if (gcVarY.n) {
                                                ArrayList arrayList9 = gcVarY.h;
                                                ArrayList arrayList10 = new ArrayList(k3.n0(arrayList9));
                                                int size5 = arrayList9.size();
                                                int i211 = 0;
                                                while (i211 < size5) {
                                                    Object obj10 = arrayList9.get(i211);
                                                    i211++;
                                                    arrayList10.add((MediaController) ((yc) obj10).a);
                                                }
                                                Object obj11 = MusicNotificationListener.a;
                                                ArrayList arrayListY = k8.Y();
                                                if (arrayList10.isEmpty() && arrayListY.isEmpty()) {
                                                    strY0 = si.a("no_media_sessions_start_radio_or_bluetooth", new Object[0]);
                                                } else {
                                                    List listI7 = j3.i0(i3.y0(arrayList10, "\n\n", new a(6, gcVarY), 30), i3.y0(arrayListY, "\n", new w(18), 30));
                                                    ArrayList arrayList11 = new ArrayList();
                                                    for (Object obj12 : listI7) {
                                                        if (!th.A((String) obj12)) {
                                                            arrayList11.add(obj12);
                                                        }
                                                    }
                                                    strY0 = i3.y0(arrayList11, "\n\n", null, 62);
                                                }
                                            } else {
                                                strY0 = si.a("no_music_access_enable_ReferencePanel_music_information", new Object[0]);
                                            }
                                            textView.setText(strY0);
                                            textView.setTextSize(17.0f);
                                            int i212 = l8.a;
                                            textView.setTextColor(l8.j(mainActivity11.z()));
                                            textView.setTextIsSelectable(true);
                                            textView.setLineSpacing(0.0f, 1.2f);
                                            String strA117 = si.a("music_diagnostics", new Object[0]);
                                            String strA118 = si.a("source_currently_shown_by_the_widget", new Object[0]);
                                            ScrollView scrollView9 = new ScrollView(mainActivity11);
                                            scrollView9.addView(textView);
                                            i4Var2.f(strA117, strA118, scrollView9).show();
                                            break;
                                        case 21:
                                            k8.m0(mainActivity3);
                                            break;
                                        case 22:
                                            mainActivity3.a0(0);
                                            break;
                                        case 23:
                                            MainActivity mainActivity12 = mainActivity3;
                                            ComponentName componentNameM = mainActivity12.m(0);
                                            if (componentNameM == null) {
                                                k8.g0(mainActivity12, 0);
                                            } else {
                                                mainActivity12.Q(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", componentNameM.getPackageName(), null)));
                                            }
                                            break;
                                        case 24:
                                            MainActivity mainActivity13 = mainActivity3;
                                            ArrayList arrayList12 = mainActivity13.w;
                                            int size6 = arrayList12.size();
                                            int i213 = 0;
                                            while (i213 < size6) {
                                                Object obj13 = arrayList12.get(i213);
                                                i213++;
                                                ((b) obj13).c();
                                            }
                                            arrayList12.clear();
                                            mainActivity13.c = false;
                                            mainActivity13.e0();
                                            break;
                                        case 25:
                                            MainActivity mainActivity14 = mainActivity3;
                                            k8.g0(mainActivity14, Integer.valueOf(mainActivity14.B0));
                                            break;
                                        case 26:
                                            MainActivity mainActivity15 = mainActivity3;
                                            mainActivity15.a0(mainActivity15.B0);
                                            break;
                                        case 27:
                                            k8.g0(mainActivity3, 0);
                                            break;
                                        case 28:
                                            MainActivity mainActivity16 = mainActivity3;
                                            if (!mainActivity16.h) {
                                                List listH = uh.h();
                                                ArrayList arrayList13 = new ArrayList(k3.n0(listH));
                                                Iterator it5 = listH.iterator();
                                                while (it5.hasNext()) {
                                                    arrayList13.add(((zi) it5.next()).b);
                                                }
                                                List listJ0 = i3.J0(i3.K0(arrayList13));
                                                c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity16).setTitle(si.a("vehicle_brand", new Object[0])).setItems((CharSequence[]) listJ0.toArray(new String[0]), new tb(mainActivity16, listJ0)), null, mainActivity16);
                                            }
                                            break;
                                        default:
                                            MainActivity mainActivity17 = mainActivity3;
                                            boolean z5 = mainActivity17.h;
                                            if (!z5 && !z5) {
                                                try {
                                                    Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                                                    intent.setType("image/*");
                                                    intent.addCategory("android.intent.category.OPENABLE");
                                                    intent.addFlags(1);
                                                    mainActivity17.startActivityForResult(intent, 41);
                                                } catch (ActivityNotFoundException unused) {
                                                    mainActivity17.v0(si.a("no_file_picker_app_on_this_head", new Object[0]));
                                                }
                                            }
                                            break;
                                    }
                                    return ti.a;
                                }
                            }, 12));
                            if (zG) {
                                final int i70 = 8;
                                arrayListJ4.add(c9.o(c9Var, si.a("font_remove", new Object[0]), null, null, new y5() { // from class: jg
                                    @Override // defpackage.y5
                                    public final Object a() {
                                        String strY0;
                                        switch (i70) {
                                            case 0:
                                                k8.d(mainActivity3);
                                                break;
                                            case 1:
                                                k8.c(mainActivity3, null);
                                                break;
                                            case 2:
                                                MainActivity mainActivity4 = mainActivity3;
                                                ArrayList arrayListC2 = j0.c();
                                                AlertDialog.Builder title = new AlertDialog.Builder(mainActivity4).setTitle(si.a("app_language_choose", new Object[0]));
                                                ArrayList arrayList8 = new ArrayList(k3.n0(arrayListC2));
                                                int size4 = arrayListC2.size();
                                                int i210 = 0;
                                                while (i210 < size4) {
                                                    Object obj8 = arrayListC2.get(i210);
                                                    i210++;
                                                    Object obj9 = (String) obj8;
                                                    List list6 = j0.a;
                                                    obj9.getClass();
                                                    List list7 = j0.a;
                                                    int iIndexOf5 = si.a.indexOf(obj9);
                                                    if (iIndexOf5 >= 0 && iIndexOf5 < list7.size()) {
                                                        obj9 = list7.get(iIndexOf5);
                                                    }
                                                    arrayList8.add((String) obj9);
                                                }
                                                CharSequence[] charSequenceArr = (CharSequence[]) arrayList8.toArray(new String[0]);
                                                List list8 = si.a;
                                                AlertDialog.Builder negativeButton = title.setSingleChoiceItems(charSequenceArr, arrayListC2.indexOf(si.b.a), new dg(arrayListC2, mainActivity4)).setNegativeButton(si.a("cancel", new Object[0]), (DialogInterface.OnClickListener) null);
                                                negativeButton.getClass();
                                                k8.w0(negativeButton);
                                                break;
                                            case 3:
                                                MainActivity mainActivity5 = mainActivity3;
                                                mainActivity5.c = false;
                                                mainActivity5.f = true;
                                                mainActivity5.e0();
                                                mainActivity5.X(new j(14));
                                                break;
                                            case 4:
                                                k8.v0("elastre", mainActivity3);
                                                break;
                                            case 5:
                                                k8.v0("manrope", mainActivity3);
                                                break;
                                            case 6:
                                                k8.v0("custom", mainActivity3);
                                                break;
                                            case 7:
                                                k8.K(mainActivity3, true);
                                                break;
                                            case 8:
                                                MainActivity mainActivity6 = mainActivity3;
                                                x3.d(mainActivity6).delete();
                                                SharedPreferences sharedPreferences = mainActivity6.getSharedPreferences("panel", 0);
                                                SharedPreferences.Editor editorRemove = sharedPreferences.edit().remove("font_custom_name").remove("font_custom_kk").remove("font_custom_wght");
                                                if (k8.e(sharedPreferences.getString("font_choice", ""), "custom")) {
                                                    editorRemove.remove("font_choice");
                                                }
                                                editorRemove.apply();
                                                z9.d(mainActivity6);
                                                z9.a = true;
                                                mainActivity6.e0();
                                                break;
                                            case 9:
                                                MainActivity mainActivity7 = mainActivity3;
                                                i7.g(mainActivity7, "");
                                                mainActivity7.e0();
                                                break;
                                            case 10:
                                                k8.O(mainActivity3, true, true);
                                                break;
                                            case 11:
                                                k8.O(mainActivity3, false, true);
                                                break;
                                            case 12:
                                                k8.Q(mainActivity3, false);
                                                break;
                                            case 13:
                                                k8.Q(mainActivity3, true);
                                                break;
                                            case 14:
                                                MainActivity mainActivity8 = mainActivity3;
                                                new i0(mainActivity8, mainActivity8.z(), si.a("split_left", new Object[0]), false, new lg(mainActivity8, 7)).show();
                                                break;
                                            case 15:
                                                k8.C(mainActivity3);
                                                break;
                                            case 16:
                                                MainActivity mainActivity9 = mainActivity3;
                                                mainActivity9.u = "dock";
                                                mainActivity9.s = null;
                                                mainActivity9.t = 0;
                                                mainActivity9.e0();
                                                break;
                                            case 17:
                                                k8.g0(mainActivity3, 1);
                                                break;
                                            case 18:
                                                MainActivity mainActivity10 = mainActivity3;
                                                md mdVar2 = md.a;
                                                List list9 = md.d;
                                                if (list9 == null) {
                                                    mdVar2.c(mainActivity10);
                                                    list9 = u4.a;
                                                }
                                                List list10 = list9;
                                                if (list10.isEmpty()) {
                                                    mainActivity10.v0(si.a("radio_city_loading", new Object[0]));
                                                } else {
                                                    Map mapJ0 = hb.j0(new yc("RU", si.a("country_ru", new Object[0])), new yc("BY", si.a("country_by", new Object[0])), new yc("KZ", si.a("country_kz", new Object[0])));
                                                    i4 i4Var = new i4(mainActivity10, mainActivity10.z());
                                                    ViewGroup viewGroupL = mainActivity10.l();
                                                    EditText editText = new EditText(mainActivity10);
                                                    editText.setHint(si.a("radio_city_search", new Object[0]));
                                                    editText.setTextSize(20.0f);
                                                    editText.setTextColor(mainActivity10.o);
                                                    editText.setHintTextColor(mainActivity10.p);
                                                    editText.setSingleLine(true);
                                                    editText.setBackground(mainActivity10.m0(mainActivity10.q, 14));
                                                    editText.setPadding(mainActivity10.r(16), 0, mainActivity10.r(16), 0);
                                                    viewGroupL.addView(editText, new LinearLayout.LayoutParams(-1, mainActivity10.r(60)));
                                                    LinearLayout linearLayoutL = mainActivity10.l();
                                                    ScrollView scrollView8 = new ScrollView(mainActivity10);
                                                    scrollView8.addView(linearLayoutL);
                                                    LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, mainActivity10.r(420));
                                                    layoutParams3.topMargin = mainActivity10.r(10);
                                                    viewGroupL.addView(scrollView8, layoutParams3);
                                                    Dialog dialogF = i4Var.f(si.a("radio_city", new Object[0]), si.a("radio_city_hint", new Object[0]), viewGroupL);
                                                    editText.addTextChangedListener(new f0(linearLayoutL, i4Var, mainActivity10, list10, dialogF, mapJ0, 1));
                                                    k8.k0(linearLayoutL, i4Var, mainActivity10, list10, dialogF, mapJ0, "");
                                                    dialogF.show();
                                                }
                                                break;
                                            case 19:
                                                mainActivity3.f0();
                                                break;
                                            case 20:
                                                MainActivity mainActivity11 = mainActivity3;
                                                i4 i4Var2 = new i4(mainActivity11, mainActivity11.z());
                                                TextView textView = new TextView(mainActivity11);
                                                gc gcVarY = mainActivity11.y();
                                                if (gcVarY.n) {
                                                    ArrayList arrayList9 = gcVarY.h;
                                                    ArrayList arrayList10 = new ArrayList(k3.n0(arrayList9));
                                                    int size5 = arrayList9.size();
                                                    int i211 = 0;
                                                    while (i211 < size5) {
                                                        Object obj10 = arrayList9.get(i211);
                                                        i211++;
                                                        arrayList10.add((MediaController) ((yc) obj10).a);
                                                    }
                                                    Object obj11 = MusicNotificationListener.a;
                                                    ArrayList arrayListY = k8.Y();
                                                    if (arrayList10.isEmpty() && arrayListY.isEmpty()) {
                                                        strY0 = si.a("no_media_sessions_start_radio_or_bluetooth", new Object[0]);
                                                    } else {
                                                        List listI7 = j3.i0(i3.y0(arrayList10, "\n\n", new a(6, gcVarY), 30), i3.y0(arrayListY, "\n", new w(18), 30));
                                                        ArrayList arrayList11 = new ArrayList();
                                                        for (Object obj12 : listI7) {
                                                            if (!th.A((String) obj12)) {
                                                                arrayList11.add(obj12);
                                                            }
                                                        }
                                                        strY0 = i3.y0(arrayList11, "\n\n", null, 62);
                                                    }
                                                } else {
                                                    strY0 = si.a("no_music_access_enable_ReferencePanel_music_information", new Object[0]);
                                                }
                                                textView.setText(strY0);
                                                textView.setTextSize(17.0f);
                                                int i212 = l8.a;
                                                textView.setTextColor(l8.j(mainActivity11.z()));
                                                textView.setTextIsSelectable(true);
                                                textView.setLineSpacing(0.0f, 1.2f);
                                                String strA117 = si.a("music_diagnostics", new Object[0]);
                                                String strA118 = si.a("source_currently_shown_by_the_widget", new Object[0]);
                                                ScrollView scrollView9 = new ScrollView(mainActivity11);
                                                scrollView9.addView(textView);
                                                i4Var2.f(strA117, strA118, scrollView9).show();
                                                break;
                                            case 21:
                                                k8.m0(mainActivity3);
                                                break;
                                            case 22:
                                                mainActivity3.a0(0);
                                                break;
                                            case 23:
                                                MainActivity mainActivity12 = mainActivity3;
                                                ComponentName componentNameM = mainActivity12.m(0);
                                                if (componentNameM == null) {
                                                    k8.g0(mainActivity12, 0);
                                                } else {
                                                    mainActivity12.Q(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", componentNameM.getPackageName(), null)));
                                                }
                                                break;
                                            case 24:
                                                MainActivity mainActivity13 = mainActivity3;
                                                ArrayList arrayList12 = mainActivity13.w;
                                                int size6 = arrayList12.size();
                                                int i213 = 0;
                                                while (i213 < size6) {
                                                    Object obj13 = arrayList12.get(i213);
                                                    i213++;
                                                    ((b) obj13).c();
                                                }
                                                arrayList12.clear();
                                                mainActivity13.c = false;
                                                mainActivity13.e0();
                                                break;
                                            case 25:
                                                MainActivity mainActivity14 = mainActivity3;
                                                k8.g0(mainActivity14, Integer.valueOf(mainActivity14.B0));
                                                break;
                                            case 26:
                                                MainActivity mainActivity15 = mainActivity3;
                                                mainActivity15.a0(mainActivity15.B0);
                                                break;
                                            case 27:
                                                k8.g0(mainActivity3, 0);
                                                break;
                                            case 28:
                                                MainActivity mainActivity16 = mainActivity3;
                                                if (!mainActivity16.h) {
                                                    List listH = uh.h();
                                                    ArrayList arrayList13 = new ArrayList(k3.n0(listH));
                                                    Iterator it5 = listH.iterator();
                                                    while (it5.hasNext()) {
                                                        arrayList13.add(((zi) it5.next()).b);
                                                    }
                                                    List listJ0 = i3.J0(i3.K0(arrayList13));
                                                    c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity16).setTitle(si.a("vehicle_brand", new Object[0])).setItems((CharSequence[]) listJ0.toArray(new String[0]), new tb(mainActivity16, listJ0)), null, mainActivity16);
                                                }
                                                break;
                                            default:
                                                MainActivity mainActivity17 = mainActivity3;
                                                boolean z5 = mainActivity17.h;
                                                if (!z5 && !z5) {
                                                    try {
                                                        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                                                        intent.setType("image/*");
                                                        intent.addCategory("android.intent.category.OPENABLE");
                                                        intent.addFlags(1);
                                                        mainActivity17.startActivityForResult(intent, 41);
                                                    } catch (ActivityNotFoundException unused) {
                                                        mainActivity17.v0(si.a("no_file_picker_app_on_this_head", new Object[0]));
                                                    }
                                                }
                                                break;
                                        }
                                        return ti.a;
                                    }
                                }, 4));
                            }
                            View[] viewArr19 = (View[]) arrayListJ4.toArray(new View[0]);
                            linearLayout.addView(c9Var.d((View[]) Arrays.copyOf(viewArr19, viewArr19.length)));
                            linearLayout.addView(c9Var.l(si.a("font_note", new Object[0])));
                            linearLayout.addView(c9Var.b(si.a("icons", new Object[0])));
                            String strA25 = i7.a(mainActivity3);
                            final int i71 = 9;
                            ArrayList arrayListJ5 = j3.j0(c9Var.n(si.a("icon_sets_standard", new Object[0]), si.a("icon_sets_standard_hint", new Object[0]), strA25.length() == 0 ? si.a("icon_set_selected", new Object[0]) : null, false, new y5() { // from class: jg
                                @Override // defpackage.y5
                                public final Object a() {
                                    String strY0;
                                    switch (i71) {
                                        case 0:
                                            k8.d(mainActivity3);
                                            break;
                                        case 1:
                                            k8.c(mainActivity3, null);
                                            break;
                                        case 2:
                                            MainActivity mainActivity4 = mainActivity3;
                                            ArrayList arrayListC2 = j0.c();
                                            AlertDialog.Builder title = new AlertDialog.Builder(mainActivity4).setTitle(si.a("app_language_choose", new Object[0]));
                                            ArrayList arrayList8 = new ArrayList(k3.n0(arrayListC2));
                                            int size4 = arrayListC2.size();
                                            int i210 = 0;
                                            while (i210 < size4) {
                                                Object obj8 = arrayListC2.get(i210);
                                                i210++;
                                                Object obj9 = (String) obj8;
                                                List list6 = j0.a;
                                                obj9.getClass();
                                                List list7 = j0.a;
                                                int iIndexOf5 = si.a.indexOf(obj9);
                                                if (iIndexOf5 >= 0 && iIndexOf5 < list7.size()) {
                                                    obj9 = list7.get(iIndexOf5);
                                                }
                                                arrayList8.add((String) obj9);
                                            }
                                            CharSequence[] charSequenceArr = (CharSequence[]) arrayList8.toArray(new String[0]);
                                            List list8 = si.a;
                                            AlertDialog.Builder negativeButton = title.setSingleChoiceItems(charSequenceArr, arrayListC2.indexOf(si.b.a), new dg(arrayListC2, mainActivity4)).setNegativeButton(si.a("cancel", new Object[0]), (DialogInterface.OnClickListener) null);
                                            negativeButton.getClass();
                                            k8.w0(negativeButton);
                                            break;
                                        case 3:
                                            MainActivity mainActivity5 = mainActivity3;
                                            mainActivity5.c = false;
                                            mainActivity5.f = true;
                                            mainActivity5.e0();
                                            mainActivity5.X(new j(14));
                                            break;
                                        case 4:
                                            k8.v0("elastre", mainActivity3);
                                            break;
                                        case 5:
                                            k8.v0("manrope", mainActivity3);
                                            break;
                                        case 6:
                                            k8.v0("custom", mainActivity3);
                                            break;
                                        case 7:
                                            k8.K(mainActivity3, true);
                                            break;
                                        case 8:
                                            MainActivity mainActivity6 = mainActivity3;
                                            x3.d(mainActivity6).delete();
                                            SharedPreferences sharedPreferences = mainActivity6.getSharedPreferences("panel", 0);
                                            SharedPreferences.Editor editorRemove = sharedPreferences.edit().remove("font_custom_name").remove("font_custom_kk").remove("font_custom_wght");
                                            if (k8.e(sharedPreferences.getString("font_choice", ""), "custom")) {
                                                editorRemove.remove("font_choice");
                                            }
                                            editorRemove.apply();
                                            z9.d(mainActivity6);
                                            z9.a = true;
                                            mainActivity6.e0();
                                            break;
                                        case 9:
                                            MainActivity mainActivity7 = mainActivity3;
                                            i7.g(mainActivity7, "");
                                            mainActivity7.e0();
                                            break;
                                        case 10:
                                            k8.O(mainActivity3, true, true);
                                            break;
                                        case 11:
                                            k8.O(mainActivity3, false, true);
                                            break;
                                        case 12:
                                            k8.Q(mainActivity3, false);
                                            break;
                                        case 13:
                                            k8.Q(mainActivity3, true);
                                            break;
                                        case 14:
                                            MainActivity mainActivity8 = mainActivity3;
                                            new i0(mainActivity8, mainActivity8.z(), si.a("split_left", new Object[0]), false, new lg(mainActivity8, 7)).show();
                                            break;
                                        case 15:
                                            k8.C(mainActivity3);
                                            break;
                                        case 16:
                                            MainActivity mainActivity9 = mainActivity3;
                                            mainActivity9.u = "dock";
                                            mainActivity9.s = null;
                                            mainActivity9.t = 0;
                                            mainActivity9.e0();
                                            break;
                                        case 17:
                                            k8.g0(mainActivity3, 1);
                                            break;
                                        case 18:
                                            MainActivity mainActivity10 = mainActivity3;
                                            md mdVar2 = md.a;
                                            List list9 = md.d;
                                            if (list9 == null) {
                                                mdVar2.c(mainActivity10);
                                                list9 = u4.a;
                                            }
                                            List list10 = list9;
                                            if (list10.isEmpty()) {
                                                mainActivity10.v0(si.a("radio_city_loading", new Object[0]));
                                            } else {
                                                Map mapJ0 = hb.j0(new yc("RU", si.a("country_ru", new Object[0])), new yc("BY", si.a("country_by", new Object[0])), new yc("KZ", si.a("country_kz", new Object[0])));
                                                i4 i4Var = new i4(mainActivity10, mainActivity10.z());
                                                ViewGroup viewGroupL = mainActivity10.l();
                                                EditText editText = new EditText(mainActivity10);
                                                editText.setHint(si.a("radio_city_search", new Object[0]));
                                                editText.setTextSize(20.0f);
                                                editText.setTextColor(mainActivity10.o);
                                                editText.setHintTextColor(mainActivity10.p);
                                                editText.setSingleLine(true);
                                                editText.setBackground(mainActivity10.m0(mainActivity10.q, 14));
                                                editText.setPadding(mainActivity10.r(16), 0, mainActivity10.r(16), 0);
                                                viewGroupL.addView(editText, new LinearLayout.LayoutParams(-1, mainActivity10.r(60)));
                                                LinearLayout linearLayoutL = mainActivity10.l();
                                                ScrollView scrollView8 = new ScrollView(mainActivity10);
                                                scrollView8.addView(linearLayoutL);
                                                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, mainActivity10.r(420));
                                                layoutParams3.topMargin = mainActivity10.r(10);
                                                viewGroupL.addView(scrollView8, layoutParams3);
                                                Dialog dialogF = i4Var.f(si.a("radio_city", new Object[0]), si.a("radio_city_hint", new Object[0]), viewGroupL);
                                                editText.addTextChangedListener(new f0(linearLayoutL, i4Var, mainActivity10, list10, dialogF, mapJ0, 1));
                                                k8.k0(linearLayoutL, i4Var, mainActivity10, list10, dialogF, mapJ0, "");
                                                dialogF.show();
                                            }
                                            break;
                                        case 19:
                                            mainActivity3.f0();
                                            break;
                                        case 20:
                                            MainActivity mainActivity11 = mainActivity3;
                                            i4 i4Var2 = new i4(mainActivity11, mainActivity11.z());
                                            TextView textView = new TextView(mainActivity11);
                                            gc gcVarY = mainActivity11.y();
                                            if (gcVarY.n) {
                                                ArrayList arrayList9 = gcVarY.h;
                                                ArrayList arrayList10 = new ArrayList(k3.n0(arrayList9));
                                                int size5 = arrayList9.size();
                                                int i211 = 0;
                                                while (i211 < size5) {
                                                    Object obj10 = arrayList9.get(i211);
                                                    i211++;
                                                    arrayList10.add((MediaController) ((yc) obj10).a);
                                                }
                                                Object obj11 = MusicNotificationListener.a;
                                                ArrayList arrayListY = k8.Y();
                                                if (arrayList10.isEmpty() && arrayListY.isEmpty()) {
                                                    strY0 = si.a("no_media_sessions_start_radio_or_bluetooth", new Object[0]);
                                                } else {
                                                    List listI7 = j3.i0(i3.y0(arrayList10, "\n\n", new a(6, gcVarY), 30), i3.y0(arrayListY, "\n", new w(18), 30));
                                                    ArrayList arrayList11 = new ArrayList();
                                                    for (Object obj12 : listI7) {
                                                        if (!th.A((String) obj12)) {
                                                            arrayList11.add(obj12);
                                                        }
                                                    }
                                                    strY0 = i3.y0(arrayList11, "\n\n", null, 62);
                                                }
                                            } else {
                                                strY0 = si.a("no_music_access_enable_ReferencePanel_music_information", new Object[0]);
                                            }
                                            textView.setText(strY0);
                                            textView.setTextSize(17.0f);
                                            int i212 = l8.a;
                                            textView.setTextColor(l8.j(mainActivity11.z()));
                                            textView.setTextIsSelectable(true);
                                            textView.setLineSpacing(0.0f, 1.2f);
                                            String strA117 = si.a("music_diagnostics", new Object[0]);
                                            String strA118 = si.a("source_currently_shown_by_the_widget", new Object[0]);
                                            ScrollView scrollView9 = new ScrollView(mainActivity11);
                                            scrollView9.addView(textView);
                                            i4Var2.f(strA117, strA118, scrollView9).show();
                                            break;
                                        case 21:
                                            k8.m0(mainActivity3);
                                            break;
                                        case 22:
                                            mainActivity3.a0(0);
                                            break;
                                        case 23:
                                            MainActivity mainActivity12 = mainActivity3;
                                            ComponentName componentNameM = mainActivity12.m(0);
                                            if (componentNameM == null) {
                                                k8.g0(mainActivity12, 0);
                                            } else {
                                                mainActivity12.Q(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", componentNameM.getPackageName(), null)));
                                            }
                                            break;
                                        case 24:
                                            MainActivity mainActivity13 = mainActivity3;
                                            ArrayList arrayList12 = mainActivity13.w;
                                            int size6 = arrayList12.size();
                                            int i213 = 0;
                                            while (i213 < size6) {
                                                Object obj13 = arrayList12.get(i213);
                                                i213++;
                                                ((b) obj13).c();
                                            }
                                            arrayList12.clear();
                                            mainActivity13.c = false;
                                            mainActivity13.e0();
                                            break;
                                        case 25:
                                            MainActivity mainActivity14 = mainActivity3;
                                            k8.g0(mainActivity14, Integer.valueOf(mainActivity14.B0));
                                            break;
                                        case 26:
                                            MainActivity mainActivity15 = mainActivity3;
                                            mainActivity15.a0(mainActivity15.B0);
                                            break;
                                        case 27:
                                            k8.g0(mainActivity3, 0);
                                            break;
                                        case 28:
                                            MainActivity mainActivity16 = mainActivity3;
                                            if (!mainActivity16.h) {
                                                List listH = uh.h();
                                                ArrayList arrayList13 = new ArrayList(k3.n0(listH));
                                                Iterator it5 = listH.iterator();
                                                while (it5.hasNext()) {
                                                    arrayList13.add(((zi) it5.next()).b);
                                                }
                                                List listJ0 = i3.J0(i3.K0(arrayList13));
                                                c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity16).setTitle(si.a("vehicle_brand", new Object[0])).setItems((CharSequence[]) listJ0.toArray(new String[0]), new tb(mainActivity16, listJ0)), null, mainActivity16);
                                            }
                                            break;
                                        default:
                                            MainActivity mainActivity17 = mainActivity3;
                                            boolean z5 = mainActivity17.h;
                                            if (!z5 && !z5) {
                                                try {
                                                    Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                                                    intent.setType("image/*");
                                                    intent.addCategory("android.intent.category.OPENABLE");
                                                    intent.addFlags(1);
                                                    mainActivity17.startActivityForResult(intent, 41);
                                                } catch (ActivityNotFoundException unused) {
                                                    mainActivity17.v0(si.a("no_file_picker_app_on_this_head", new Object[0]));
                                                }
                                            }
                                            break;
                                    }
                                    return ti.a;
                                }
                            }));
                            for (d7 d7Var : i7.e(mainActivity3)) {
                                List list6 = si.a;
                                String strA26 = si.a(d7Var.b() ? "icon_set_color" : "icon_set_mono", new Object[0]);
                                String strA27 = d7Var.a();
                                if (th.A(strA27)) {
                                    strA27 = str16;
                                }
                                arrayListJ5.add(c9.o(c9Var, d7Var.e(), i3.y0(i3.t0(j3.i0(strA27, si.a("icon_set_info_0_1", strA26, Integer.valueOf(d7Var.c())))), " · ", str16, 62), e(d7Var.d(), strA25) ? si.a("icon_set_selected", new Object[0]) : null, new y1(mainActivity3, d7Var, 12), 8));
                                str16 = null;
                            }
                            final int i72 = 10;
                            arrayListJ5.add(c9.o(c9Var, si.a("icon_set_add", new Object[0]), si.a("icon_set_add_hint", new Object[0]), null, new y5() { // from class: jg
                                @Override // defpackage.y5
                                public final Object a() {
                                    String strY0;
                                    switch (i72) {
                                        case 0:
                                            k8.d(mainActivity3);
                                            break;
                                        case 1:
                                            k8.c(mainActivity3, null);
                                            break;
                                        case 2:
                                            MainActivity mainActivity4 = mainActivity3;
                                            ArrayList arrayListC2 = j0.c();
                                            AlertDialog.Builder title = new AlertDialog.Builder(mainActivity4).setTitle(si.a("app_language_choose", new Object[0]));
                                            ArrayList arrayList8 = new ArrayList(k3.n0(arrayListC2));
                                            int size4 = arrayListC2.size();
                                            int i210 = 0;
                                            while (i210 < size4) {
                                                Object obj8 = arrayListC2.get(i210);
                                                i210++;
                                                Object obj9 = (String) obj8;
                                                List list7 = j0.a;
                                                obj9.getClass();
                                                List list8 = j0.a;
                                                int iIndexOf5 = si.a.indexOf(obj9);
                                                if (iIndexOf5 >= 0 && iIndexOf5 < list8.size()) {
                                                    obj9 = list8.get(iIndexOf5);
                                                }
                                                arrayList8.add((String) obj9);
                                            }
                                            CharSequence[] charSequenceArr = (CharSequence[]) arrayList8.toArray(new String[0]);
                                            List list9 = si.a;
                                            AlertDialog.Builder negativeButton = title.setSingleChoiceItems(charSequenceArr, arrayListC2.indexOf(si.b.a), new dg(arrayListC2, mainActivity4)).setNegativeButton(si.a("cancel", new Object[0]), (DialogInterface.OnClickListener) null);
                                            negativeButton.getClass();
                                            k8.w0(negativeButton);
                                            break;
                                        case 3:
                                            MainActivity mainActivity5 = mainActivity3;
                                            mainActivity5.c = false;
                                            mainActivity5.f = true;
                                            mainActivity5.e0();
                                            mainActivity5.X(new j(14));
                                            break;
                                        case 4:
                                            k8.v0("elastre", mainActivity3);
                                            break;
                                        case 5:
                                            k8.v0("manrope", mainActivity3);
                                            break;
                                        case 6:
                                            k8.v0("custom", mainActivity3);
                                            break;
                                        case 7:
                                            k8.K(mainActivity3, true);
                                            break;
                                        case 8:
                                            MainActivity mainActivity6 = mainActivity3;
                                            x3.d(mainActivity6).delete();
                                            SharedPreferences sharedPreferences = mainActivity6.getSharedPreferences("panel", 0);
                                            SharedPreferences.Editor editorRemove = sharedPreferences.edit().remove("font_custom_name").remove("font_custom_kk").remove("font_custom_wght");
                                            if (k8.e(sharedPreferences.getString("font_choice", ""), "custom")) {
                                                editorRemove.remove("font_choice");
                                            }
                                            editorRemove.apply();
                                            z9.d(mainActivity6);
                                            z9.a = true;
                                            mainActivity6.e0();
                                            break;
                                        case 9:
                                            MainActivity mainActivity7 = mainActivity3;
                                            i7.g(mainActivity7, "");
                                            mainActivity7.e0();
                                            break;
                                        case 10:
                                            k8.O(mainActivity3, true, true);
                                            break;
                                        case 11:
                                            k8.O(mainActivity3, false, true);
                                            break;
                                        case 12:
                                            k8.Q(mainActivity3, false);
                                            break;
                                        case 13:
                                            k8.Q(mainActivity3, true);
                                            break;
                                        case 14:
                                            MainActivity mainActivity8 = mainActivity3;
                                            new i0(mainActivity8, mainActivity8.z(), si.a("split_left", new Object[0]), false, new lg(mainActivity8, 7)).show();
                                            break;
                                        case 15:
                                            k8.C(mainActivity3);
                                            break;
                                        case 16:
                                            MainActivity mainActivity9 = mainActivity3;
                                            mainActivity9.u = "dock";
                                            mainActivity9.s = null;
                                            mainActivity9.t = 0;
                                            mainActivity9.e0();
                                            break;
                                        case 17:
                                            k8.g0(mainActivity3, 1);
                                            break;
                                        case 18:
                                            MainActivity mainActivity10 = mainActivity3;
                                            md mdVar2 = md.a;
                                            List list10 = md.d;
                                            if (list10 == null) {
                                                mdVar2.c(mainActivity10);
                                                list10 = u4.a;
                                            }
                                            List list11 = list10;
                                            if (list11.isEmpty()) {
                                                mainActivity10.v0(si.a("radio_city_loading", new Object[0]));
                                            } else {
                                                Map mapJ0 = hb.j0(new yc("RU", si.a("country_ru", new Object[0])), new yc("BY", si.a("country_by", new Object[0])), new yc("KZ", si.a("country_kz", new Object[0])));
                                                i4 i4Var = new i4(mainActivity10, mainActivity10.z());
                                                ViewGroup viewGroupL = mainActivity10.l();
                                                EditText editText = new EditText(mainActivity10);
                                                editText.setHint(si.a("radio_city_search", new Object[0]));
                                                editText.setTextSize(20.0f);
                                                editText.setTextColor(mainActivity10.o);
                                                editText.setHintTextColor(mainActivity10.p);
                                                editText.setSingleLine(true);
                                                editText.setBackground(mainActivity10.m0(mainActivity10.q, 14));
                                                editText.setPadding(mainActivity10.r(16), 0, mainActivity10.r(16), 0);
                                                viewGroupL.addView(editText, new LinearLayout.LayoutParams(-1, mainActivity10.r(60)));
                                                LinearLayout linearLayoutL = mainActivity10.l();
                                                ScrollView scrollView8 = new ScrollView(mainActivity10);
                                                scrollView8.addView(linearLayoutL);
                                                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, mainActivity10.r(420));
                                                layoutParams3.topMargin = mainActivity10.r(10);
                                                viewGroupL.addView(scrollView8, layoutParams3);
                                                Dialog dialogF = i4Var.f(si.a("radio_city", new Object[0]), si.a("radio_city_hint", new Object[0]), viewGroupL);
                                                editText.addTextChangedListener(new f0(linearLayoutL, i4Var, mainActivity10, list11, dialogF, mapJ0, 1));
                                                k8.k0(linearLayoutL, i4Var, mainActivity10, list11, dialogF, mapJ0, "");
                                                dialogF.show();
                                            }
                                            break;
                                        case 19:
                                            mainActivity3.f0();
                                            break;
                                        case 20:
                                            MainActivity mainActivity11 = mainActivity3;
                                            i4 i4Var2 = new i4(mainActivity11, mainActivity11.z());
                                            TextView textView = new TextView(mainActivity11);
                                            gc gcVarY = mainActivity11.y();
                                            if (gcVarY.n) {
                                                ArrayList arrayList9 = gcVarY.h;
                                                ArrayList arrayList10 = new ArrayList(k3.n0(arrayList9));
                                                int size5 = arrayList9.size();
                                                int i211 = 0;
                                                while (i211 < size5) {
                                                    Object obj10 = arrayList9.get(i211);
                                                    i211++;
                                                    arrayList10.add((MediaController) ((yc) obj10).a);
                                                }
                                                Object obj11 = MusicNotificationListener.a;
                                                ArrayList arrayListY = k8.Y();
                                                if (arrayList10.isEmpty() && arrayListY.isEmpty()) {
                                                    strY0 = si.a("no_media_sessions_start_radio_or_bluetooth", new Object[0]);
                                                } else {
                                                    List listI7 = j3.i0(i3.y0(arrayList10, "\n\n", new a(6, gcVarY), 30), i3.y0(arrayListY, "\n", new w(18), 30));
                                                    ArrayList arrayList11 = new ArrayList();
                                                    for (Object obj12 : listI7) {
                                                        if (!th.A((String) obj12)) {
                                                            arrayList11.add(obj12);
                                                        }
                                                    }
                                                    strY0 = i3.y0(arrayList11, "\n\n", null, 62);
                                                }
                                            } else {
                                                strY0 = si.a("no_music_access_enable_ReferencePanel_music_information", new Object[0]);
                                            }
                                            textView.setText(strY0);
                                            textView.setTextSize(17.0f);
                                            int i212 = l8.a;
                                            textView.setTextColor(l8.j(mainActivity11.z()));
                                            textView.setTextIsSelectable(true);
                                            textView.setLineSpacing(0.0f, 1.2f);
                                            String strA117 = si.a("music_diagnostics", new Object[0]);
                                            String strA118 = si.a("source_currently_shown_by_the_widget", new Object[0]);
                                            ScrollView scrollView9 = new ScrollView(mainActivity11);
                                            scrollView9.addView(textView);
                                            i4Var2.f(strA117, strA118, scrollView9).show();
                                            break;
                                        case 21:
                                            k8.m0(mainActivity3);
                                            break;
                                        case 22:
                                            mainActivity3.a0(0);
                                            break;
                                        case 23:
                                            MainActivity mainActivity12 = mainActivity3;
                                            ComponentName componentNameM = mainActivity12.m(0);
                                            if (componentNameM == null) {
                                                k8.g0(mainActivity12, 0);
                                            } else {
                                                mainActivity12.Q(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", componentNameM.getPackageName(), null)));
                                            }
                                            break;
                                        case 24:
                                            MainActivity mainActivity13 = mainActivity3;
                                            ArrayList arrayList12 = mainActivity13.w;
                                            int size6 = arrayList12.size();
                                            int i213 = 0;
                                            while (i213 < size6) {
                                                Object obj13 = arrayList12.get(i213);
                                                i213++;
                                                ((b) obj13).c();
                                            }
                                            arrayList12.clear();
                                            mainActivity13.c = false;
                                            mainActivity13.e0();
                                            break;
                                        case 25:
                                            MainActivity mainActivity14 = mainActivity3;
                                            k8.g0(mainActivity14, Integer.valueOf(mainActivity14.B0));
                                            break;
                                        case 26:
                                            MainActivity mainActivity15 = mainActivity3;
                                            mainActivity15.a0(mainActivity15.B0);
                                            break;
                                        case 27:
                                            k8.g0(mainActivity3, 0);
                                            break;
                                        case 28:
                                            MainActivity mainActivity16 = mainActivity3;
                                            if (!mainActivity16.h) {
                                                List listH = uh.h();
                                                ArrayList arrayList13 = new ArrayList(k3.n0(listH));
                                                Iterator it5 = listH.iterator();
                                                while (it5.hasNext()) {
                                                    arrayList13.add(((zi) it5.next()).b);
                                                }
                                                List listJ0 = i3.J0(i3.K0(arrayList13));
                                                c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity16).setTitle(si.a("vehicle_brand", new Object[0])).setItems((CharSequence[]) listJ0.toArray(new String[0]), new tb(mainActivity16, listJ0)), null, mainActivity16);
                                            }
                                            break;
                                        default:
                                            MainActivity mainActivity17 = mainActivity3;
                                            boolean z5 = mainActivity17.h;
                                            if (!z5 && !z5) {
                                                try {
                                                    Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                                                    intent.setType("image/*");
                                                    intent.addCategory("android.intent.category.OPENABLE");
                                                    intent.addFlags(1);
                                                    mainActivity17.startActivityForResult(intent, 41);
                                                } catch (ActivityNotFoundException unused) {
                                                    mainActivity17.v0(si.a("no_file_picker_app_on_this_head", new Object[0]));
                                                }
                                            }
                                            break;
                                    }
                                    return ti.a;
                                }
                            }, 12));
                            final int i73 = 11;
                            arrayListJ5.add(c9.o(c9Var, si.a("icon_set_template", new Object[0]), si.a("icon_set_template_hint", new Object[0]), null, new y5() { // from class: jg
                                @Override // defpackage.y5
                                public final Object a() {
                                    String strY0;
                                    switch (i73) {
                                        case 0:
                                            k8.d(mainActivity3);
                                            break;
                                        case 1:
                                            k8.c(mainActivity3, null);
                                            break;
                                        case 2:
                                            MainActivity mainActivity4 = mainActivity3;
                                            ArrayList arrayListC2 = j0.c();
                                            AlertDialog.Builder title = new AlertDialog.Builder(mainActivity4).setTitle(si.a("app_language_choose", new Object[0]));
                                            ArrayList arrayList8 = new ArrayList(k3.n0(arrayListC2));
                                            int size4 = arrayListC2.size();
                                            int i210 = 0;
                                            while (i210 < size4) {
                                                Object obj8 = arrayListC2.get(i210);
                                                i210++;
                                                Object obj9 = (String) obj8;
                                                List list7 = j0.a;
                                                obj9.getClass();
                                                List list8 = j0.a;
                                                int iIndexOf5 = si.a.indexOf(obj9);
                                                if (iIndexOf5 >= 0 && iIndexOf5 < list8.size()) {
                                                    obj9 = list8.get(iIndexOf5);
                                                }
                                                arrayList8.add((String) obj9);
                                            }
                                            CharSequence[] charSequenceArr = (CharSequence[]) arrayList8.toArray(new String[0]);
                                            List list9 = si.a;
                                            AlertDialog.Builder negativeButton = title.setSingleChoiceItems(charSequenceArr, arrayListC2.indexOf(si.b.a), new dg(arrayListC2, mainActivity4)).setNegativeButton(si.a("cancel", new Object[0]), (DialogInterface.OnClickListener) null);
                                            negativeButton.getClass();
                                            k8.w0(negativeButton);
                                            break;
                                        case 3:
                                            MainActivity mainActivity5 = mainActivity3;
                                            mainActivity5.c = false;
                                            mainActivity5.f = true;
                                            mainActivity5.e0();
                                            mainActivity5.X(new j(14));
                                            break;
                                        case 4:
                                            k8.v0("elastre", mainActivity3);
                                            break;
                                        case 5:
                                            k8.v0("manrope", mainActivity3);
                                            break;
                                        case 6:
                                            k8.v0("custom", mainActivity3);
                                            break;
                                        case 7:
                                            k8.K(mainActivity3, true);
                                            break;
                                        case 8:
                                            MainActivity mainActivity6 = mainActivity3;
                                            x3.d(mainActivity6).delete();
                                            SharedPreferences sharedPreferences = mainActivity6.getSharedPreferences("panel", 0);
                                            SharedPreferences.Editor editorRemove = sharedPreferences.edit().remove("font_custom_name").remove("font_custom_kk").remove("font_custom_wght");
                                            if (k8.e(sharedPreferences.getString("font_choice", ""), "custom")) {
                                                editorRemove.remove("font_choice");
                                            }
                                            editorRemove.apply();
                                            z9.d(mainActivity6);
                                            z9.a = true;
                                            mainActivity6.e0();
                                            break;
                                        case 9:
                                            MainActivity mainActivity7 = mainActivity3;
                                            i7.g(mainActivity7, "");
                                            mainActivity7.e0();
                                            break;
                                        case 10:
                                            k8.O(mainActivity3, true, true);
                                            break;
                                        case 11:
                                            k8.O(mainActivity3, false, true);
                                            break;
                                        case 12:
                                            k8.Q(mainActivity3, false);
                                            break;
                                        case 13:
                                            k8.Q(mainActivity3, true);
                                            break;
                                        case 14:
                                            MainActivity mainActivity8 = mainActivity3;
                                            new i0(mainActivity8, mainActivity8.z(), si.a("split_left", new Object[0]), false, new lg(mainActivity8, 7)).show();
                                            break;
                                        case 15:
                                            k8.C(mainActivity3);
                                            break;
                                        case 16:
                                            MainActivity mainActivity9 = mainActivity3;
                                            mainActivity9.u = "dock";
                                            mainActivity9.s = null;
                                            mainActivity9.t = 0;
                                            mainActivity9.e0();
                                            break;
                                        case 17:
                                            k8.g0(mainActivity3, 1);
                                            break;
                                        case 18:
                                            MainActivity mainActivity10 = mainActivity3;
                                            md mdVar2 = md.a;
                                            List list10 = md.d;
                                            if (list10 == null) {
                                                mdVar2.c(mainActivity10);
                                                list10 = u4.a;
                                            }
                                            List list11 = list10;
                                            if (list11.isEmpty()) {
                                                mainActivity10.v0(si.a("radio_city_loading", new Object[0]));
                                            } else {
                                                Map mapJ0 = hb.j0(new yc("RU", si.a("country_ru", new Object[0])), new yc("BY", si.a("country_by", new Object[0])), new yc("KZ", si.a("country_kz", new Object[0])));
                                                i4 i4Var = new i4(mainActivity10, mainActivity10.z());
                                                ViewGroup viewGroupL = mainActivity10.l();
                                                EditText editText = new EditText(mainActivity10);
                                                editText.setHint(si.a("radio_city_search", new Object[0]));
                                                editText.setTextSize(20.0f);
                                                editText.setTextColor(mainActivity10.o);
                                                editText.setHintTextColor(mainActivity10.p);
                                                editText.setSingleLine(true);
                                                editText.setBackground(mainActivity10.m0(mainActivity10.q, 14));
                                                editText.setPadding(mainActivity10.r(16), 0, mainActivity10.r(16), 0);
                                                viewGroupL.addView(editText, new LinearLayout.LayoutParams(-1, mainActivity10.r(60)));
                                                LinearLayout linearLayoutL = mainActivity10.l();
                                                ScrollView scrollView8 = new ScrollView(mainActivity10);
                                                scrollView8.addView(linearLayoutL);
                                                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, mainActivity10.r(420));
                                                layoutParams3.topMargin = mainActivity10.r(10);
                                                viewGroupL.addView(scrollView8, layoutParams3);
                                                Dialog dialogF = i4Var.f(si.a("radio_city", new Object[0]), si.a("radio_city_hint", new Object[0]), viewGroupL);
                                                editText.addTextChangedListener(new f0(linearLayoutL, i4Var, mainActivity10, list11, dialogF, mapJ0, 1));
                                                k8.k0(linearLayoutL, i4Var, mainActivity10, list11, dialogF, mapJ0, "");
                                                dialogF.show();
                                            }
                                            break;
                                        case 19:
                                            mainActivity3.f0();
                                            break;
                                        case 20:
                                            MainActivity mainActivity11 = mainActivity3;
                                            i4 i4Var2 = new i4(mainActivity11, mainActivity11.z());
                                            TextView textView = new TextView(mainActivity11);
                                            gc gcVarY = mainActivity11.y();
                                            if (gcVarY.n) {
                                                ArrayList arrayList9 = gcVarY.h;
                                                ArrayList arrayList10 = new ArrayList(k3.n0(arrayList9));
                                                int size5 = arrayList9.size();
                                                int i211 = 0;
                                                while (i211 < size5) {
                                                    Object obj10 = arrayList9.get(i211);
                                                    i211++;
                                                    arrayList10.add((MediaController) ((yc) obj10).a);
                                                }
                                                Object obj11 = MusicNotificationListener.a;
                                                ArrayList arrayListY = k8.Y();
                                                if (arrayList10.isEmpty() && arrayListY.isEmpty()) {
                                                    strY0 = si.a("no_media_sessions_start_radio_or_bluetooth", new Object[0]);
                                                } else {
                                                    List listI7 = j3.i0(i3.y0(arrayList10, "\n\n", new a(6, gcVarY), 30), i3.y0(arrayListY, "\n", new w(18), 30));
                                                    ArrayList arrayList11 = new ArrayList();
                                                    for (Object obj12 : listI7) {
                                                        if (!th.A((String) obj12)) {
                                                            arrayList11.add(obj12);
                                                        }
                                                    }
                                                    strY0 = i3.y0(arrayList11, "\n\n", null, 62);
                                                }
                                            } else {
                                                strY0 = si.a("no_music_access_enable_ReferencePanel_music_information", new Object[0]);
                                            }
                                            textView.setText(strY0);
                                            textView.setTextSize(17.0f);
                                            int i212 = l8.a;
                                            textView.setTextColor(l8.j(mainActivity11.z()));
                                            textView.setTextIsSelectable(true);
                                            textView.setLineSpacing(0.0f, 1.2f);
                                            String strA117 = si.a("music_diagnostics", new Object[0]);
                                            String strA118 = si.a("source_currently_shown_by_the_widget", new Object[0]);
                                            ScrollView scrollView9 = new ScrollView(mainActivity11);
                                            scrollView9.addView(textView);
                                            i4Var2.f(strA117, strA118, scrollView9).show();
                                            break;
                                        case 21:
                                            k8.m0(mainActivity3);
                                            break;
                                        case 22:
                                            mainActivity3.a0(0);
                                            break;
                                        case 23:
                                            MainActivity mainActivity12 = mainActivity3;
                                            ComponentName componentNameM = mainActivity12.m(0);
                                            if (componentNameM == null) {
                                                k8.g0(mainActivity12, 0);
                                            } else {
                                                mainActivity12.Q(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", componentNameM.getPackageName(), null)));
                                            }
                                            break;
                                        case 24:
                                            MainActivity mainActivity13 = mainActivity3;
                                            ArrayList arrayList12 = mainActivity13.w;
                                            int size6 = arrayList12.size();
                                            int i213 = 0;
                                            while (i213 < size6) {
                                                Object obj13 = arrayList12.get(i213);
                                                i213++;
                                                ((b) obj13).c();
                                            }
                                            arrayList12.clear();
                                            mainActivity13.c = false;
                                            mainActivity13.e0();
                                            break;
                                        case 25:
                                            MainActivity mainActivity14 = mainActivity3;
                                            k8.g0(mainActivity14, Integer.valueOf(mainActivity14.B0));
                                            break;
                                        case 26:
                                            MainActivity mainActivity15 = mainActivity3;
                                            mainActivity15.a0(mainActivity15.B0);
                                            break;
                                        case 27:
                                            k8.g0(mainActivity3, 0);
                                            break;
                                        case 28:
                                            MainActivity mainActivity16 = mainActivity3;
                                            if (!mainActivity16.h) {
                                                List listH = uh.h();
                                                ArrayList arrayList13 = new ArrayList(k3.n0(listH));
                                                Iterator it5 = listH.iterator();
                                                while (it5.hasNext()) {
                                                    arrayList13.add(((zi) it5.next()).b);
                                                }
                                                List listJ0 = i3.J0(i3.K0(arrayList13));
                                                c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity16).setTitle(si.a("vehicle_brand", new Object[0])).setItems((CharSequence[]) listJ0.toArray(new String[0]), new tb(mainActivity16, listJ0)), null, mainActivity16);
                                            }
                                            break;
                                        default:
                                            MainActivity mainActivity17 = mainActivity3;
                                            boolean z5 = mainActivity17.h;
                                            if (!z5 && !z5) {
                                                try {
                                                    Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                                                    intent.setType("image/*");
                                                    intent.addCategory("android.intent.category.OPENABLE");
                                                    intent.addFlags(1);
                                                    mainActivity17.startActivityForResult(intent, 41);
                                                } catch (ActivityNotFoundException unused) {
                                                    mainActivity17.v0(si.a("no_file_picker_app_on_this_head", new Object[0]));
                                                }
                                            }
                                            break;
                                    }
                                    return ti.a;
                                }
                            }, 12));
                            View[] viewArr20 = (View[]) arrayListJ5.toArray(new View[0]);
                            linearLayout.addView(c9Var.d((View[]) Arrays.copyOf(viewArr20, viewArr20.length)));
                            linearLayout.addView(c9Var.l(si.a("icon_set_note", new Object[0])));
                            linearLayout.addView(c9Var.b(si.a("custom_wallpaper", new Object[0])));
                            final File fileA = ea.a(mainActivity3, false);
                            final File fileA2 = ea.a(mainActivity3, true);
                            String strA28 = si.a("custom_wallpaper_day_theme", new Object[0]);
                            if (fileA.exists()) {
                                str3 = str;
                                strA2 = si.a(str3, new Object[0]);
                            } else {
                                str3 = str;
                                strA2 = si.a(
                                /*  JADX ERROR: Method code generation error
                                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0aa9: INVOKE (r4v93 'strA2' java.lang.String) = (r45v0 ?? I:??[OBJECT, ARRAY]), (wrap java.lang.Object[]:0x0aa7: NEW_ARRAY (0 int) A[WRAPPED] type: java.lang.Object[]) STATIC call: si.a(java.lang.String, java.lang.Object[]):java.lang.String A[MD:(java.lang.String, java.lang.Object[]):java.lang.String VARARG (m), VARARG_CALL] in method: k8.u0(org.reference.panel.MainActivity):android.widget.FrameLayout, file: classes.dex
                                        at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                                        at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                                        at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                                        at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                        at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        at jadx.core.dex.regions.Region.generate(Region.java:35)
                                        at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                        at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:140)
                                        at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                        at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        at jadx.core.dex.regions.Region.generate(Region.java:35)
                                        at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                        at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                        at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                        at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        at jadx.core.dex.regions.Region.generate(Region.java:35)
                                        at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                        at jadx.core.codegen.RegionGen.makeSwitch(RegionGen.java:267)
                                        at jadx.core.dex.regions.SwitchRegion.generate(SwitchRegion.java:90)
                                        at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        at jadx.core.dex.regions.Region.generate(Region.java:35)
                                        at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                        at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                        at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                        at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        at jadx.core.dex.regions.Region.generate(Region.java:35)
                                        at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        at jadx.core.dex.regions.Region.generate(Region.java:35)
                                        at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                        at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:207)
                                        at jadx.core.dex.regions.loops.LoopRegion.generate(LoopRegion.java:173)
                                        at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        at jadx.core.dex.regions.Region.generate(Region.java:35)
                                        at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:291)
                                        at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:270)
                                        at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:420)
                                        at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
                                        at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
                                        at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
                                        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                                        at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                        at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r45v0 ??
                                        at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
                                    */
                                /*
                                    Method dump skipped, instruction units count: 7546
                                    To view this dump change 'Code comments level' option to 'DEBUG'
                                */
                                throw new UnsupportedOperationException("Method not decompiled: defpackage.k8.u0(org.reference.panel.MainActivity):android.widget.FrameLayout");
                            }

                            public static boolean v(Context context, lb lbVar) {
                                context.getClass();
                                List<kd> listL0 = l0(context, "radio_favorites");
                                if (listL0.isEmpty()) {
                                    return false;
                                }
                                for (kd kdVar : listL0) {
                                    if (kdVar.b().equals(lbVar.a()) && kdVar.c(lbVar.b)) {
                                        return true;
                                    }
                                }
                                return false;
                            }

                            public static final void v0(String str, MainActivity mainActivity) {
                                ArrayList arrayList = x3.a;
                                mainActivity.getSharedPreferences("panel", 0).edit().putString("font_choice", str).apply();
                                z9.d(mainActivity);
                                z9.a = true;
                                mainActivity.e0();
                            }

                            public static void w(MainActivity mainActivity, Uri uri, String str, boolean z, File file, w7 w7Var, int i2) {
                                if ((i2 & 4) != 0) {
                                    z = false;
                                }
                                if ((i2 & 8) != 0) {
                                    file = null;
                                }
                                w7 w7Var2 = (i2 & 16) != 0 ? null : w7Var;
                                str.getClass();
                                if (file == null) {
                                    if (z && x0.Y0(new String[]{"day", "night"}).contains(str)) {
                                        ca caVar = ea.a;
                                        file = ea.a(mainActivity, str.equals("night"));
                                    } else if (z) {
                                        file = new File(mainActivity.getFilesDir(), "wallpaper_" + str + ".png");
                                    } else {
                                        file = mainActivity.o(str);
                                    }
                                }
                                File file2 = file;
                                mainActivity.h = true;
                                mainActivity.e0();
                                mainActivity.b.execute(new l(mainActivity, uri, file2, w7Var2, 4));
                            }

                            public static final void w0(AlertDialog.Builder builder) {
                                Window window;
                                AlertDialog alertDialogCreate = builder.create();
                                alertDialogCreate.getClass();
                                if (pi.c && (window = alertDialogCreate.getWindow()) != null) {
                                    window.setType(2038);
                                }
                                alertDialogCreate.show();
                            }

                            public static final void x(int i2, int i3) {
                                if (i2 <= i3) {
                                    return;
                                }
                                throw new IndexOutOfBoundsException("toIndex (" + i2 + ") is greater than size (" + i3 + ").");
                            }

                            public static List x0(ArrayList arrayList) {
                                return i3.G0(arrayList, s(new w(21), new w(22)));
                            }

                            public static int y(float f2, float f3, float f4, float f5) {
                                if (Math.abs(f2) < Math.abs(f3) * 2.0f) {
                                    return 0;
                                }
                                boolean z = Math.abs(f2) >= Math.max(60.0f, f5 / 3.0f);
                                boolean z2 = Math.abs(f2) >= 40.0f && f4 >= 800.0f;
                                if (z || z2) {
                                    return f2 < 0.0f ? 1 : -1;
                                }
                                return 0;
                            }

                            public static final void y0(String str, MainActivity mainActivity) {
                                mainActivity.getClass();
                                str.getClass();
                                List list = eh.a;
                                SharedPreferences sharedPreferencesB = mainActivity.B();
                                sharedPreferencesB.getClass();
                                if (eh.c(sharedPreferencesB).contains(str)) {
                                    SharedPreferences sharedPreferencesB2 = mainActivity.B();
                                    sharedPreferencesB2.getClass();
                                    bh bhVarJ = eh.j(sharedPreferencesB2, str);
                                    String strD = c.d(si.a("split_left", new Object[0]), ": ", z0(mainActivity, bhVarJ.a));
                                    String strD2 = c.d(si.a("split_right", new Object[0]), ": ", z0(mainActivity, bhVarJ.b));
                                    String strA = si.a("split_ratio", new Object[0]);
                                    int i2 = bhVarJ.c;
                                    c.e("done", new Object[0], new AlertDialog.Builder(mainActivity).setTitle(A0(str, mainActivity)).setItems(new String[]{strD, strD2, strA + ": " + i2 + " / " + (100 - i2), si.a("split_open", new Object[0]), si.a("split_to_desktop", new Object[0]), si.a("split_to_dock", new Object[0]), si.a("split_delete", new Object[0])}, new sb(mainActivity, bhVarJ, str, 1)), null, mainActivity);
                                }
                            }

                            public static final void z(String str, MainActivity mainActivity) {
                                mainActivity.getClass();
                                str.getClass();
                                List list = eh.a;
                                SharedPreferences sharedPreferencesB = mainActivity.B();
                                sharedPreferencesB.getClass();
                                SharedPreferences.Editor editorRemove = sharedPreferencesB.edit().putString("split_list", i3.y0(i3.C0(eh.c(sharedPreferencesB), str), ",", null, 62)).remove("split_" + str + "_left").remove("split_" + str + "_right").remove("split_" + str + "_ratio").remove("desktop_split_".concat(str)).remove("desktop_wsplit_".concat(str));
                                for (String str2 : j3.i0("desktop_splits", "wall_splits")) {
                                    Set<String> set = x4.a;
                                    Set<String> stringSet = sharedPreferencesB.getStringSet(str2, set);
                                    if (stringSet != null) {
                                        set = stringSet;
                                    }
                                    editorRemove.putStringSet(str2, zf.g0(set, str));
                                }
                                editorRemove.apply();
                                if (e(eh.d, str)) {
                                    eh.d = null;
                                }
                                ArrayList arrayListT = T(mainActivity);
                                if (!arrayListT.contains("split:".concat(str))) {
                                    mainActivity.e0();
                                    return;
                                }
                                ArrayList arrayList = new ArrayList();
                                int size = arrayListT.size();
                                int i2 = 0;
                                while (i2 < size) {
                                    Object obj = arrayListT.get(i2);
                                    i2++;
                                    if (!e((String) obj, "split:".concat(str))) {
                                        arrayList.add(obj);
                                    }
                                }
                                boolean zIsEmpty = arrayList.isEmpty();
                                List listJ = arrayList;
                                if (zIsEmpty) {
                                    listJ = pi.J("empty");
                                }
                                s0(listJ, mainActivity);
                            }

                            public static final String z0(MainActivity mainActivity, ComponentName componentName) {
                                Object ffVar;
                                if (componentName != null) {
                                    try {
                                        ffVar = mainActivity.getPackageManager().getActivityInfo(componentName, 0).loadLabel(mainActivity.getPackageManager()).toString();
                                    } catch (Throwable th) {
                                        ffVar = new ff(th);
                                    }
                                    if (ffVar instanceof ff) {
                                        ffVar = null;
                                    }
                                    String str = (String) ffVar;
                                    if (str != null) {
                                        return str;
                                    }
                                }
                                return si.a("choose", new Object[0]);
                            }
                        }
