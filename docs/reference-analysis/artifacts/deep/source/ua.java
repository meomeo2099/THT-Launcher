package defpackage;

import android.app.Dialog;
import android.graphics.Rect;
import android.os.SystemClock;
import android.provider.Settings;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import java.util.ArrayList;
import java.util.List;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ua implements y5 {
    public final /* synthetic */ int a;
    public final /* synthetic */ MainActivity b;

    public /* synthetic */ ua(MainActivity mainActivity, int i) {
        this.a = i;
        this.b = mainActivity;
    }

    @Override // defpackage.y5
    public final Object a() {
        Object ffVar;
        int i = this.a;
        int i2 = 0;
        int i3 = 1;
        ti tiVar = ti.a;
        MainActivity mainActivity = this.b;
        switch (i) {
            case 0:
                mainActivity.f = false;
                mainActivity.e0();
                return tiVar;
            case 1:
                int i4 = MainActivity.E0;
                boolean zU = mainActivity.u();
                ArrayList arrayListN0 = k8.N0(mainActivity);
                List listI0 = j3.i0("app", "image");
                ArrayList arrayList = new ArrayList(k3.n0(arrayListN0));
                int size = arrayListN0.size();
                int i5 = 0;
                while (i5 < size) {
                    Object obj = arrayListN0.get(i5);
                    i5++;
                    arrayList.add(((xj) obj).a);
                }
                ArrayList arrayListD0 = i3.D0(i3.D0(listI0, arrayList), pi.J("dock"));
                List listI1 = j3.i0(si.a("any_app", new Object[0]), si.a("screensaver_picture", new Object[0]));
                ArrayList arrayList2 = new ArrayList(k3.n0(arrayListN0));
                int size2 = arrayListN0.size();
                int i6 = 0;
                while (i6 < size2) {
                    Object obj2 = arrayListN0.get(i6);
                    i6++;
                    arrayList2.add(k8.R(mainActivity, (xj) obj2));
                }
                new i4(mainActivity, mainActivity.z()).b(arrayListD0, i3.D0(i3.D0(listI1, arrayList2), pi.J(si.a("bottom_bar", new Object[0]))), new pb(i2, mainActivity, zU));
                return tiVar;
            case 2:
                int i7 = MainActivity.E0;
                k8.C(mainActivity);
                return tiVar;
            case 3:
                int i8 = MainActivity.E0;
                k8.m0(mainActivity);
                return tiVar;
            case 4:
                int i9 = MainActivity.E0;
                i4 i4Var = new i4(mainActivity, mainActivity.z());
                LinearLayout linearLayoutL = mainActivity.l();
                String strA = si.a("grid", new Object[0]);
                String strA2 = si.a("home_screen_editing_behavior", new Object[0]);
                ScrollView scrollView = new ScrollView(mainActivity);
                scrollView.addView(linearLayoutL);
                Dialog dialogF = i4Var.f(strA, strA2, scrollView);
                k8.B(linearLayoutL, mainActivity, si.a("show_alignment_grid", new Object[0]), "desktop_grid");
                k8.B(linearLayoutL, mainActivity, si.a("snap_to_grid_step_20", new Object[0]), "desktop_snap");
                linearLayoutL.addView(i4Var.a(si.a("done", new Object[0]), true, new rb(dialogF, mainActivity, i3)));
                dialogF.setOnDismissListener(new a0(1, mainActivity));
                dialogF.show();
                return tiVar;
            case 5:
                int i10 = MainActivity.E0;
                if (!mainActivity.isDestroyed()) {
                    mainActivity.e();
                }
                return tiVar;
            case 6:
                mainActivity.f = true;
                mainActivity.e0();
                return tiVar;
            case 7:
                int i11 = MainActivity.E0;
                k8.k(mainActivity, -1, 1);
                return tiVar;
            case 8:
                int i12 = MainActivity.E0;
                k8.k(mainActivity, 1, 1);
                return tiVar;
            case 9:
                int i13 = MainActivity.E0;
                k8.m(mainActivity);
                return tiVar;
            case 10:
                int i14 = MainActivity.E0;
                nj njVarH = mainActivity.H();
                int i15 = 4;
                v1 v1Var = new v1(mainActivity, i15);
                njVarH.getClass();
                njVarH.b(v1Var, new zd(268501248, i15));
                return tiVar;
            case 11:
                if (mainActivity.d && mainActivity.B().getBoolean("suppress_climate_popup", true)) {
                    mainActivity.runOnUiThread(new sa(mainActivity, 3));
                }
                return tiVar;
            case 12:
                Float fValueOf = mainActivity.w0;
                if (fValueOf == null) {
                    float f = mainActivity.B().getFloat("last_brightness", -1.0f);
                    fValueOf = f >= 0.0f ? Float.valueOf(f) : null;
                }
                if (SystemClock.elapsedRealtime() < mainActivity.x0) {
                    return fValueOf;
                }
                Float f2 = mainActivity.Z.p;
                if (f2 != null) {
                    float fFloatValue = f2.floatValue();
                    if (fValueOf == null || Math.abs(fValueOf.floatValue() - fFloatValue) > 0.01f) {
                        mainActivity.w0 = Float.valueOf(fFloatValue);
                        mainActivity.B().edit().putFloat("last_brightness", fFloatValue).apply();
                    }
                    return Float.valueOf(fFloatValue);
                }
                if (fValueOf != null) {
                    return fValueOf;
                }
                try {
                    ffVar = Float.valueOf(Settings.System.getInt(mainActivity.getContentResolver(), "screen_brightness") / 255.0f);
                    break;
                } catch (Throwable th) {
                    ffVar = new ff(th);
                }
                return (Float) (ffVar instanceof ff ? null : ffVar);
            case 13:
                int i16 = MainActivity.E0;
                mainActivity.a0(0);
                return tiVar;
            case 14:
                int i17 = MainActivity.E0;
                k8.G(mainActivity);
                return tiVar;
            case 15:
                int i18 = MainActivity.E0;
                mainActivity.f0();
                return tiVar;
            case 16:
                int i19 = MainActivity.E0;
                mainActivity.Y();
                return tiVar;
            case 17:
                int i20 = MainActivity.E0;
                mainActivity.y().D();
                return tiVar;
            case 18:
                int i21 = MainActivity.E0;
                mainActivity.y().x(false);
                return tiVar;
            case 19:
                int i22 = MainActivity.E0;
                mainActivity.y().x(true);
                return tiVar;
            case 20:
                int i23 = MainActivity.E0;
                mainActivity.y().f();
                return tiVar;
            case 21:
                int i24 = MainActivity.E0;
                mainActivity.y().l();
                return tiVar;
            case 22:
                int i25 = MainActivity.E0;
                mainActivity.f0();
                return tiVar;
            case 23:
                int i26 = MainActivity.E0;
                mainActivity.Y();
                return tiVar;
            case 24:
                int i27 = MainActivity.E0;
                mainActivity.y().D();
                return tiVar;
            case 25:
                int i28 = MainActivity.E0;
                mainActivity.y().x(false);
                return tiVar;
            case 26:
                int i29 = MainActivity.E0;
                mainActivity.y().x(true);
                return tiVar;
            case 27:
                int i30 = MainActivity.E0;
                mainActivity.y().f();
                return tiVar;
            case 28:
                int i31 = MainActivity.E0;
                mainActivity.y().l();
                return tiVar;
            default:
                int i32 = MainActivity.E0;
                Rect rect = mainActivity.s0;
                if (rect == null) {
                    return null;
                }
                if (mainActivity.q0 == null) {
                    rect = null;
                }
                if (rect != null) {
                    return Integer.valueOf(mainActivity.S(rect));
                }
                return null;
        }
    }
}
