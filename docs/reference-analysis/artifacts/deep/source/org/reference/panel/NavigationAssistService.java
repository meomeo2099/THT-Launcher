package org.reference.panel;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;
import defpackage.b7;
import defpackage.ba;
import defpackage.d9;
import defpackage.fi;
import defpackage.i3;
import defpackage.j0;
import defpackage.je;
import defpackage.k8;
import defpackage.ke;
import defpackage.l0;
import defpackage.l8;
import defpackage.le;
import defpackage.m2;
import defpackage.mc;
import defpackage.pc;
import defpackage.pi;
import defpackage.si;
import defpackage.x0;
import defpackage.y5;
import org.reference.panel.NavigationAssistService;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class NavigationAssistService extends AccessibilityService implements SharedPreferences.OnSharedPreferenceChangeListener {
    public static final /* synthetic */ int d = 0;
    public final fi a = new fi(new mc(this, 3));
    public final fi b = new fi(new mc(this, 4));
    public ViewGroup c;

    public static final FrameLayout f(NavigationAssistService navigationAssistService, LinearLayout linearLayout, boolean z, String str, String str2, int i, y5 y5Var) {
        FrameLayout frameLayout = new FrameLayout(navigationAssistService);
        frameLayout.setContentDescription(str2);
        frameLayout.setFocusable(true);
        frameLayout.setClickable(true);
        d9 d9Var = new d9(navigationAssistService, str);
        d9Var.setColor(l8.j(z));
        frameLayout.addView(d9Var, new FrameLayout.LayoutParams(navigationAssistService.a(str.equals("handle") ? 20 : 27), navigationAssistService.a(str.equals("handle") ? 20 : 27), 17));
        if (y5Var != null) {
            frameLayout.setOnClickListener(new m2(y5Var, 11));
        }
        linearLayout.addView(frameLayout, new LinearLayout.LayoutParams(navigationAssistService.a(i), -1));
        return frameLayout;
    }

    public final int a(int i) {
        return (int) (i * getResources().getDisplayMetrics().density);
    }

    public final SharedPreferences b() {
        return (SharedPreferences) this.a.a();
    }

    public final void c() {
        try {
            startActivity(new Intent(this, (Class<?>) MainActivity.class).addFlags(872415232).putExtra("org.reference.panel.OPEN_HOME", true));
        } catch (Exception e) {
            Log.w("NavigationAssist", "Cannot open ReferencePanel", e);
            Toast.makeText(this, si.a("could_not_open_ReferencePanel", new Object[0]), 0).show();
        }
    }

    public final void d() {
        ViewGroup viewGroup = this.c;
        if (viewGroup != null) {
            try {
                ((WindowManager) this.b.a()).removeView(viewGroup);
            } catch (Throwable unused) {
            }
        }
        this.c = null;
    }

    public final void e() {
        int i = 0;
        if (!b().getBoolean("navigation_assist", false)) {
            d();
            return;
        }
        if (!pc.a.isEmpty()) {
            d();
            return;
        }
        if (this.c != null) {
            return;
        }
        boolean zE = k8.e(b().getString("navigation_assist_mode", "gestures"), "gestures");
        fi fiVar = this.b;
        int i2 = 1;
        if (zE) {
            FrameLayout frameLayout = new FrameLayout(this);
            frameLayout.setContentDescription(si.a("bottom_gestures_left_for_back_up_for", new Object[0]));
            View view = new View(this);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(-1718629904);
            gradientDrawable.setCornerRadius(a(3));
            view.setBackground(gradientDrawable);
            frameLayout.addView(view, new FrameLayout.LayoutParams(a(88), a(4), 17));
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(a(180), a(24), 2032, 40, -3);
            layoutParams.gravity = 81;
            final ke keVar = new ke();
            final ke keVar2 = new ke();
            final je jeVar = new je();
            frameLayout.setOnTouchListener(new View.OnTouchListener() { // from class: oc
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view2, MotionEvent motionEvent) {
                    rc rcVar;
                    int i3 = NavigationAssistService.d;
                    int actionMasked = motionEvent.getActionMasked();
                    ke keVar3 = keVar;
                    ke keVar4 = keVar2;
                    je jeVar2 = jeVar;
                    if (actionMasked == 0) {
                        keVar3.a = motionEvent.getRawX();
                        keVar4.a = motionEvent.getRawY();
                        jeVar2.a = false;
                        view2.setAlpha(1.0f);
                        return true;
                    }
                    if (actionMasked == 1) {
                        view2.setAlpha(0.65f);
                        boolean z = jeVar2.a;
                        rc rcVar2 = rc.a;
                        NavigationAssistService navigationAssistService = this;
                        if (!z) {
                            float rawX = motionEvent.getRawX() - keVar3.a;
                            float rawY = motionEvent.getRawY() - keVar4.a;
                            float fA = navigationAssistService.a(48);
                            float f = -rawX;
                            if (f < fA || f <= Math.abs(rawY) * 1.3f) {
                                float f2 = -rawY;
                                if (f2 >= fA && f2 > Math.abs(rawX) * 1.3f) {
                                    rcVar = rc.c;
                                }
                            } else {
                                rcVar = rc.b;
                            }
                            rcVar2 = rcVar;
                        }
                        int iOrdinal = rcVar2.ordinal();
                        if (iOrdinal == 0) {
                            view2.performClick();
                            return true;
                        }
                        if (iOrdinal != 1) {
                            if (iOrdinal != 2) {
                                throw new vc();
                            }
                            navigationAssistService.c();
                            return true;
                        }
                        if (!navigationAssistService.performGlobalAction(1)) {
                            Toast.makeText(navigationAssistService, si.a("android_did_not_perform_the_back_action", new Object[0]), 0).show();
                        }
                    } else {
                        if (actionMasked == 3) {
                            view2.setAlpha(0.65f);
                            return true;
                        }
                        if (actionMasked == 5) {
                            jeVar2.a = true;
                            return true;
                        }
                    }
                    return true;
                }
            });
            frameLayout.setAlpha(0.65f);
            try {
                ((WindowManager) fiVar.a()).addView(frameLayout, layoutParams);
                this.c = frameLayout;
                return;
            } catch (Exception e) {
                Log.w("NavigationAssist", "Cannot show gesture zone", e);
                Toast.makeText(this, si.a("the_firmware_blocked_gestures", new Object[0]), 1).show();
                return;
            }
        }
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int iA = a(140);
        int i3 = displayMetrics.widthPixels;
        int i4 = iA > i3 ? i3 : iA;
        int iA2 = a(48);
        int i5 = displayMetrics.heightPixels;
        int i6 = iA2 > i5 ? i5 : iA2;
        int i7 = displayMetrics.widthPixels - i4;
        if (i7 < 0) {
            i7 = 0;
        }
        int i8 = i5 - i6;
        int i9 = i8 < 0 ? 0 : i8;
        final WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams(i4, i6, 2032, 40, -3);
        layoutParams2.gravity = 51;
        layoutParams2.x = (int) (pi.r(b().getFloat("navigation_assist_x", 0.0f), 0.0f, 1.0f) * i7);
        layoutParams2.y = (int) (pi.r(b().getFloat("navigation_assist_y", 0.5f), 0.0f, 1.0f) * i9);
        String string = b().getString("theme_mode", null);
        if (string == null) {
            string = b().getBoolean("night", true) ? "night" : "day";
        }
        boolean z = !string.equals("day") && (!string.equals("system") || (48 & getResources().getConfiguration().uiMode) == 32);
        final LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(0);
        linearLayout.setLayoutDirection(0);
        linearLayout.setTextDirection(5);
        linearLayout.setGravity(16);
        linearLayout.setElevation(a(6));
        linearLayout.setBackground(new b7(z, a(24), false));
        FrameLayout frameLayoutF = f(this, linearLayout, z, "handle", si.a("move_navigation_panel", new Object[0]), 28, null);
        f(this, linearLayout, z, "back", si.a("back", new Object[0]), 56, new mc(this, i));
        f(this, linearLayout, z, "home", si.a("ReferencePanel_home_screen", new Object[0]), 56, new mc(this, i2));
        final ke keVar3 = new ke();
        final int i10 = i9;
        final ke keVar4 = new ke();
        final le leVar = new le();
        final le leVar2 = new le();
        final je jeVar2 = new je();
        final int scaledTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        final int i11 = i7;
        frameLayoutF.setOnTouchListener(new View.OnTouchListener() { // from class: nc
            /* JADX WARN: Code duplicated, block: B:21:0x006f  */
            /* JADX WARN: Code duplicated, block: B:23:0x0073  */
            /* JADX WARN: Code duplicated, block: B:25:0x007e  */
            /* JADX WARN: Code duplicated, block: B:26:0x0080  */
            /* JADX WARN: Code duplicated, block: B:30:0x008e  */
            /* JADX WARN: Code duplicated, block: B:33:0x009d  */
            /* JADX WARN: Code duplicated, block: B:35:0x00a3  */
            /* JADX WARN: Instruction removed from duplicated block: B:23:0x0073, please report this as an issue */
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view2, MotionEvent motionEvent) {
                float f;
                LinearLayout linearLayout2 = linearLayout;
                int i12 = NavigationAssistService.d;
                int actionMasked = motionEvent.getActionMasked();
                ke keVar5 = keVar3;
                ke keVar6 = keVar4;
                le leVar3 = leVar;
                WindowManager.LayoutParams layoutParams3 = layoutParams2;
                le leVar4 = leVar2;
                je jeVar3 = jeVar2;
                if (actionMasked == 0) {
                    keVar5.a = motionEvent.getRawX();
                    keVar6.a = motionEvent.getRawY();
                    leVar3.a = layoutParams3.x;
                    leVar4.a = layoutParams3.y;
                    jeVar3.a = false;
                    return true;
                }
                int i13 = i11;
                int i14 = i10;
                NavigationAssistService navigationAssistService = this;
                if (actionMasked == 1) {
                    if (jeVar3.a) {
                        SharedPreferences.Editor editorEdit = navigationAssistService.b().edit();
                        if (i13 == 0) {
                            f = 0.0f;
                        } else {
                            f = layoutParams3.x / i13;
                        }
                        editorEdit.putFloat("navigation_assist_x", f).putFloat("navigation_assist_y", i14 != 0 ? layoutParams3.y / i14 : 0.0f).apply();
                        return true;
                    }
                    if (motionEvent.getActionMasked() == 1) {
                        view2.performClick();
                        Toast.makeText(navigationAssistService, si.a("drag_the_panel_by_the_dots", new Object[0]), 0).show();
                    }
                } else if (actionMasked == 2) {
                    float rawX = motionEvent.getRawX() - keVar5.a;
                    float rawY = motionEvent.getRawY() - keVar6.a;
                    float fAbs = Math.abs(rawX);
                    float f2 = scaledTouchSlop;
                    if (fAbs > f2 || Math.abs(rawY) > f2) {
                        jeVar3.a = true;
                    }
                    if (jeVar3.a) {
                        layoutParams3.x = pi.s(leVar3.a + ((int) rawX), 0, i13);
                        layoutParams3.y = pi.s(leVar4.a + ((int) rawY), 0, i14);
                        try {
                            ((WindowManager) navigationAssistService.b.a()).updateViewLayout(linearLayout2, layoutParams3);
                            return true;
                        } catch (Throwable unused) {
                        }
                    }
                } else if (actionMasked == 3) {
                    if (jeVar3.a) {
                        SharedPreferences.Editor editorEdit2 = navigationAssistService.b().edit();
                        if (i13 == 0) {
                            f = 0.0f;
                        } else {
                            f = layoutParams3.x / i13;
                        }
                        editorEdit2.putFloat("navigation_assist_x", f).putFloat("navigation_assist_y", i14 != 0 ? layoutParams3.y / i14 : 0.0f).apply();
                        return true;
                    }
                    if (motionEvent.getActionMasked() == 1) {
                        view2.performClick();
                        Toast.makeText(navigationAssistService, si.a("drag_the_panel_by_the_dots", new Object[0]), 0).show();
                    }
                }
                return true;
            }
        });
        try {
            ((WindowManager) fiVar.a()).addView(linearLayout, layoutParams2);
            this.c = linearLayout;
        } catch (Exception e2) {
            Log.w("NavigationAssist", "Cannot show navigation controls", e2);
            Toast.makeText(this, si.a("the_firmware_blocked_the_navigation_panel", new Object[0]), 1).show();
        }
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        configuration.getClass();
        super.onConfigurationChanged(configuration);
        d();
        e();
    }

    @Override // android.app.Service
    public final void onDestroy() {
        b().unregisterOnSharedPreferenceChangeListener(this);
        pc.b = null;
        d();
        super.onDestroy();
    }

    @Override // android.accessibilityservice.AccessibilityService
    public final void onServiceConnected() {
        ba.h.q(this);
        ba.l = true;
        b().registerOnSharedPreferenceChangeListener(this);
        pc.b = new mc(this, 2);
        e();
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (k8.e(str, "app_language")) {
            j0.a(this);
        }
        if (k8.e(str, "icon_set")) {
            l0 l0Var = ba.h;
            ba baVar = ba.k;
            if (baVar != null) {
                baVar.c(this);
            }
        }
        if (i3.r0(x0.Y0(new String[]{"navigation_assist", "navigation_assist_mode", "icon_pack", "theme_mode", "night", "app_language", "icon_set"}), str)) {
            d();
            e();
        }
    }

    @Override // android.accessibilityservice.AccessibilityService
    public final void onInterrupt() {
    }

    @Override // android.accessibilityservice.AccessibilityService
    public final void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
    }
}
