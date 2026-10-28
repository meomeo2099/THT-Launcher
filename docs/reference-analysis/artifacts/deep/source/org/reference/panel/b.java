package org.reference.panel;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.TextView;
import defpackage.a5;
import defpackage.b2;
import defpackage.c3;
import defpackage.k8;
import defpackage.l8;
import defpackage.r1;
import defpackage.ra;
import defpackage.s;
import defpackage.si;
import defpackage.tc;
import defpackage.th;
import defpackage.uc;
import defpackage.v1;
import defpackage.w0;
import java.util.ArrayList;
import java.util.List;
import org.reference.panel.MainActivity;
import org.reference.panel.NativeNavigationWindow;
import org.reference.panel.a;
import org.reference.panel.b;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class b extends FrameLayout {
    public float A;
    public boolean B;
    public final int C;
    public final GradientDrawable D;
    public boolean E;
    public boolean F;
    public final a5 G;
    public final a5 H;
    public final MainActivity a;
    public boolean b;
    public final c3 c;
    public final c3 d;
    public final v1 e;
    public final ra f;
    public final boolean g;
    public final b2 h;
    public final int i;
    public final boolean j;
    public final Handler k;
    public NativeNavigationWindow l;
    public boolean m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public ComponentName r;
    public int s;
    public final ComponentName t;
    public boolean u;
    public boolean v;
    public uc w;
    public final tc x;
    public final r1 y;
    public float z;

    /* JADX WARN: Type inference failed for: r2v8, types: [a5] */
    /* JADX WARN: Type inference failed for: r2v9, types: [a5] */
    public b(MainActivity mainActivity, boolean z, c3 c3Var, c3 c3Var2, v1 v1Var, ra raVar, boolean z2, b2 b2Var, int i, boolean z3) {
        super(mainActivity);
        this.a = mainActivity;
        this.b = z;
        this.c = c3Var;
        this.d = c3Var2;
        this.e = v1Var;
        this.f = raVar;
        this.g = z2;
        this.h = b2Var;
        this.i = i;
        this.j = z3;
        this.k = new Handler(Looper.getMainLooper());
        this.t = (ComponentName) c3Var.a();
        this.x = new tc();
        int i2 = 3;
        this.y = new r1(i2, this);
        this.C = ViewConfiguration.get(mainActivity).getScaledTouchSlop();
        new w0();
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.rgb(20, 27, 36));
        this.D = gradientDrawable;
        final int i3 = 0;
        this.G = new Runnable(this) { // from class: a5
            public final /* synthetic */ b b;

            {
                this.b = this;
            }

            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
                at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // java.lang.Runnable
            public final void run() {
                int i4 = i3;
                b bVar = this.b;
                switch (i4) {
                    case 0:
                        bVar.F = false;
                        MainActivity mainActivity2 = bVar.a;
                        if (!bVar.m || bVar.u || bVar.n || !bVar.isShown() || bVar.getWidth() <= 0 || bVar.getHeight() <= 0 || !mainActivity2.hasWindowFocus()) {
                            bVar.o = false;
                            return;
                        }
                        ComponentName componentName = (ComponentName) bVar.c.a();
                        if (componentName == null) {
                            List list = si.a;
                            bVar.g(si.a(bVar.j ? "app_widget_choose" : "choose_navigation_app", new Object[0]));
                            return;
                        }
                        bVar.r = componentName;
                        qc.b(mainActivity2, componentName);
                        if (Build.VERSION.SDK_INT != 28) {
                            si.a("this_activityview_requires_android_9_ns11", new Object[0]);
                            bVar.d(null);
                            return;
                        }
                        List listI0 = j3.i0("android.permission.INJECT_EVENTS", "android.permission.INTERNAL_SYSTEM_WINDOW", "android.permission.MANAGE_ACTIVITY_STACKS");
                        ArrayList arrayList = new ArrayList();
                        for (Object obj : listI0) {
                            if (mainActivity2.checkSelfPermission((String) obj) != 0) {
                                arrayList.add(obj);
                            }
                        }
                        if (!arrayList.isEmpty()) {
                            List list2 = si.a;
                            si.a("an_ns11signed_build_is_required_missing_permissions", i3.y0(arrayList, null, null, 63));
                            bVar.d(null);
                            return;
                        }
                        try {
                            ActivityInfo activityInfo = mainActivity2.getPackageManager().getActivityInfo(componentName, 0);
                            activityInfo.getClass();
                            if (k8.e(componentName.getPackageName(), mainActivity2.getPackageName()) || !activityInfo.enabled || !activityInfo.exported) {
                                throw new IllegalStateException("Target unavailable");
                            }
                            bVar.e.b(bVar);
                            bVar.removeAllViews();
                            int i5 = bVar.s + 1;
                            bVar.s = i5;
                            NativeNavigationWindow nativeNavigationWindow = new NativeNavigationWindow(mainActivity2, new a(bVar, i5));
                            bVar.l = nativeNavigationWindow;
                            bVar.E = true;
                            nativeNavigationWindow.showWindow(true);
                            bVar.addView(nativeNavigationWindow, new FrameLayout.LayoutParams(-1, -1));
                            bVar.m();
                            if (bVar.p) {
                                return;
                            }
                            Handler handler = bVar.k;
                            a5 a5Var = bVar.H;
                            handler.removeCallbacks(a5Var);
                            handler.postDelayed(a5Var, 10000L);
                            return;
                        } catch (Exception e) {
                            bVar.d(e);
                            return;
                        } catch (LinkageError e2) {
                            bVar.d(e2);
                            return;
                        }
                    default:
                        if (bVar.n || !bVar.m || bVar.u || bVar.l == null || bVar.p) {
                            return;
                        }
                        bVar.d(null);
                        return;
                }
            }
        };
        final int i4 = 1;
        this.H = new Runnable(this) { // from class: a5
            public final /* synthetic */ b b;

            {
                this.b = this;
            }

            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
                at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // java.lang.Runnable
            public final void run() {
                int i5 = i4;
                b bVar = this.b;
                switch (i5) {
                    case 0:
                        bVar.F = false;
                        MainActivity mainActivity2 = bVar.a;
                        if (!bVar.m || bVar.u || bVar.n || !bVar.isShown() || bVar.getWidth() <= 0 || bVar.getHeight() <= 0 || !mainActivity2.hasWindowFocus()) {
                            bVar.o = false;
                            return;
                        }
                        ComponentName componentName = (ComponentName) bVar.c.a();
                        if (componentName == null) {
                            List list = si.a;
                            bVar.g(si.a(bVar.j ? "app_widget_choose" : "choose_navigation_app", new Object[0]));
                            return;
                        }
                        bVar.r = componentName;
                        qc.b(mainActivity2, componentName);
                        if (Build.VERSION.SDK_INT != 28) {
                            si.a("this_activityview_requires_android_9_ns11", new Object[0]);
                            bVar.d(null);
                            return;
                        }
                        List listI0 = j3.i0("android.permission.INJECT_EVENTS", "android.permission.INTERNAL_SYSTEM_WINDOW", "android.permission.MANAGE_ACTIVITY_STACKS");
                        ArrayList arrayList = new ArrayList();
                        for (Object obj : listI0) {
                            if (mainActivity2.checkSelfPermission((String) obj) != 0) {
                                arrayList.add(obj);
                            }
                        }
                        if (!arrayList.isEmpty()) {
                            List list2 = si.a;
                            si.a("an_ns11signed_build_is_required_missing_permissions", i3.y0(arrayList, null, null, 63));
                            bVar.d(null);
                            return;
                        }
                        try {
                            ActivityInfo activityInfo = mainActivity2.getPackageManager().getActivityInfo(componentName, 0);
                            activityInfo.getClass();
                            if (k8.e(componentName.getPackageName(), mainActivity2.getPackageName()) || !activityInfo.enabled || !activityInfo.exported) {
                                throw new IllegalStateException("Target unavailable");
                            }
                            bVar.e.b(bVar);
                            bVar.removeAllViews();
                            int i6 = bVar.s + 1;
                            bVar.s = i6;
                            NativeNavigationWindow nativeNavigationWindow = new NativeNavigationWindow(mainActivity2, new a(bVar, i6));
                            bVar.l = nativeNavigationWindow;
                            bVar.E = true;
                            nativeNavigationWindow.showWindow(true);
                            bVar.addView(nativeNavigationWindow, new FrameLayout.LayoutParams(-1, -1));
                            bVar.m();
                            if (bVar.p) {
                                return;
                            }
                            Handler handler = bVar.k;
                            a5 a5Var = bVar.H;
                            handler.removeCallbacks(a5Var);
                            handler.postDelayed(a5Var, 10000L);
                            return;
                        } catch (Exception e) {
                            bVar.d(e);
                            return;
                        } catch (LinkageError e2) {
                            bVar.d(e2);
                            return;
                        }
                    default:
                        if (bVar.n || !bVar.m || bVar.u || bVar.l == null || bVar.p) {
                            return;
                        }
                        bVar.d(null);
                        return;
                }
            }
        };
        setBackground(gradientDrawable);
        setDescendantFocusability(393216);
        if (z2) {
            setClickable(true);
            setFocusable(false);
            setFocusableInTouchMode(false);
            List list = si.a;
            setContentDescription(si.a(z3 ? "app_widget_tap" : "navigation_tap_to_open_full_screen", new Object[0]));
            setOnClickListener(new s(i2, this));
        }
        if (z2) {
            return;
        }
        List list2 = si.a;
        g(si.a(z3 ? "app_widget" : "navigation", new Object[0]));
    }

    public final boolean a() {
        return !this.n && !this.u && this.q && this.v && isAttachedToWindow() && isShown() && ((Boolean) this.h.a()).booleanValue();
    }

    public final void b() {
        tc tcVar = this.x;
        tcVar.a = null;
        tcVar.b = 0L;
        tcVar.c = 0;
        this.k.removeCallbacks(this.y);
    }

    public final void c() {
        if (this.n) {
            return;
        }
        this.n = true;
        k();
        h();
    }

    public final void d(Throwable th) {
        if (th != null) {
            th.getMessage();
        }
        h();
        List list = si.a;
        g(si.a(this.j ? "app_widget_unavailable" : "window_unavailable_navigation_settings", new Object[0]));
    }

    public final void e() {
        NativeNavigationWindow nativeNavigationWindow;
        ComponentName componentName = this.r;
        if (componentName != null && (nativeNavigationWindow = this.l) != null && this.m && this.p && !this.q && !this.n && isShown() && this.a.hasWindowFocus()) {
            this.q = true;
            try {
                nativeNavigationWindow.launch(new Intent("android.intent.action.MAIN").addCategory("android.intent.category.LAUNCHER").setComponent(componentName).addFlags(268435456));
            } catch (Exception e) {
                d(e);
            } catch (LinkageError e2) {
                d(e2);
            }
        }
    }

    public final void f() {
        this.m = false;
        a5 a5Var = this.H;
        Handler handler = this.k;
        handler.removeCallbacks(a5Var);
        if (this.F) {
            handler.removeCallbacks(this.G);
            this.F = false;
            this.o = false;
        }
    }

    public final void g(String str) {
        removeAllViews();
        TextView textView = new TextView(this.a);
        textView.setText(str);
        textView.setTextSize(14.0f);
        textView.setGravity(17);
        int i = l8.a;
        textView.setTextColor(l8.k(this.b));
        textView.setPadding(16, 16, 16, 16);
        addView(textView, new FrameLayout.LayoutParams(-1, -1));
    }

    public final boolean getRetainOnResume() {
        return this.E;
    }

    public final int getSlot() {
        return this.i;
    }

    public final void h() {
        this.v = false;
        this.w = null;
        b();
        this.o = true;
        this.F = false;
        this.s++;
        this.k.removeCallbacksAndMessages(null);
        NativeNavigationWindow nativeNavigationWindow = this.l;
        this.l = null;
        this.p = false;
        this.q = false;
        this.E = false;
        if (nativeNavigationWindow != null) {
            try {
                nativeNavigationWindow.close();
            } catch (Exception e) {
                e.getMessage();
            } catch (LinkageError unused) {
            }
        }
        removeAllViews();
    }

    /* JADX WARN: Code duplicated, block: B:26:0x003d  */
    public final void i() {
        String str;
        if (a()) {
            uc ucVarI = this.w;
            if (ucVarI == null) {
                ucVarI = k8.I();
                ComponentName componentName = this.r;
                String packageName = componentName != null ? componentName.getPackageName() : null;
                if (ucVarI != null) {
                    String str2 = ucVarI.c;
                    if (packageName == null || th.A(packageName) || !k8.e(str2, packageName) || ucVarI.a <= 0 || ucVarI.b < 0 || str2 == null || th.A(str2)) {
                        ucVarI = null;
                    }
                } else {
                    ucVarI = null;
                }
                if (ucVarI != null) {
                    this.w = ucVarI;
                } else {
                    ucVarI = null;
                }
                if (ucVarI == null) {
                    return;
                }
            }
            long jUptimeMillis = SystemClock.uptimeMillis();
            tc tcVar = this.x;
            tcVar.getClass();
            if (ucVarI.a <= 0 || ucVarI.b < 0 || (str = ucVarI.c) == null || th.A(str)) {
                tcVar.a = null;
                tcVar.b = 0L;
                tcVar.c = 0;
            } else if (!k8.e(tcVar.a, ucVarI) || jUptimeMillis >= tcVar.b) {
                tcVar.a = ucVarI;
                tcVar.b = jUptimeMillis + 30000;
                tcVar.c = 0;
            }
            Handler handler = this.k;
            r1 r1Var = this.y;
            handler.removeCallbacks(r1Var);
            handler.post(r1Var);
        }
    }

    public final void j() {
        if (this.n || this.u || !this.g || !isShown() || getWidth() <= 0 || getHeight() <= 0 || !this.a.hasWindowFocus()) {
            return;
        }
        this.m = true;
        this.v = true;
        NativeNavigationWindow nativeNavigationWindow = this.l;
        if (nativeNavigationWindow != null) {
            nativeNavigationWindow.showWindow(true);
        }
        if (this.p) {
            e();
        }
        boolean z = this.p;
        Handler handler = this.k;
        if (!z && this.l != null) {
            a5 a5Var = this.H;
            handler.removeCallbacks(a5Var);
            handler.postDelayed(a5Var, 10000L);
        }
        if (this.o || !isAttachedToWindow()) {
            return;
        }
        this.o = true;
        this.F = true;
        handler.post(this.G);
    }

    public final void k() {
        this.v = false;
        b();
        f();
        NativeNavigationWindow nativeNavigationWindow = this.l;
        if (nativeNavigationWindow != null) {
            nativeNavigationWindow.showWindow(false);
        }
    }

    public final String l() {
        ComponentName componentName = (ComponentName) this.c.a();
        if (componentName != null) {
            return componentName.getPackageName();
        }
        return null;
    }

    public final void m() {
        int i = l8.a;
        float fMin = Math.min(l8.m(this.a) * 24.0f, Math.min(getWidth(), getHeight()) / 2.0f);
        this.D.setCornerRadius(fMin);
        NativeNavigationWindow nativeNavigationWindow = this.l;
        if (nativeNavigationWindow != null) {
            nativeNavigationWindow.setCornerRadius(fMin);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isShown()) {
            j();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        k();
        super.onDetachedFromWindow();
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        motionEvent.getClass();
        return this.g;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        m();
        if (i <= 0 || i2 <= 0) {
            return;
        }
        j();
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        motionEvent.getClass();
        if (!this.g) {
            return super.onTouchEvent(motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.z = motionEvent.getX();
            this.A = motionEvent.getY();
            this.B = true;
            return true;
        }
        if (actionMasked == 1) {
            if (this.B && motionEvent.getX() >= 0.0f && motionEvent.getX() < getWidth() && motionEvent.getY() >= 0.0f && motionEvent.getY() < getHeight()) {
                performClick();
            }
            this.B = false;
            return true;
        }
        if (actionMasked == 2) {
            float fAbs = Math.abs(motionEvent.getX() - this.z);
            float f = this.C;
            if (fAbs > f || Math.abs(motionEvent.getY() - this.A) > f) {
                this.B = false;
                return true;
            }
        } else if (actionMasked == 3 || actionMasked == 5) {
            this.B = false;
            return true;
        }
        return true;
    }
}
