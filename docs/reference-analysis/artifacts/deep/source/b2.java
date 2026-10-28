package defpackage;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import java.util.ArrayList;
import java.util.List;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b2 implements y5 {
    public final /* synthetic */ int a;
    public final /* synthetic */ MainActivity b;

    public /* synthetic */ b2(MainActivity mainActivity, int i) {
        this.a = i;
        this.b = mainActivity;
    }

    /* JADX WARN: Code duplicated, block: B:135:0x02dd A[PHI: r0
      0x02dd: PHI (r0v21 java.lang.Integer) = (r0v20 java.lang.Integer), (r0v23 java.lang.Integer) binds: [B:130:0x02c6, B:134:0x02db] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:53:0x0108  */
    @Override // defpackage.y5
    public final Object a() {
        Object ffVar;
        Integer numValueOf;
        int iR;
        Object ffVar2;
        int i = this.a;
        int i2 = 5;
        char c = 1;
        int i3 = 0;
        ti tiVar = ti.a;
        MainActivity mainActivity = this.b;
        switch (i) {
            case 0:
                pi.R(mainActivity);
                return tiVar;
            case 1:
                c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity).setTitle(si.a("clear_statistics_350", new Object[0])).setMessage(si.a("all_trips_and_voltage_history_will_be", new Object[0])).setPositiveButton(si.a("delete", new Object[0]), new z1(mainActivity, i3)), null, mainActivity);
                return tiVar;
            case 2:
                nj njVarH = mainActivity.H();
                v1 v1Var = new v1(mainActivity, i2);
                njVarH.getClass();
                njVarH.b(v1Var, new fj(njVarH, 268501248, i3, i3));
                return tiVar;
            case 3:
                k8.m(mainActivity);
                return tiVar;
            case 4:
                try {
                    mainActivity.startActivity(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + mainActivity.getPackageName())));
                    ffVar = tiVar;
                } catch (Throwable th) {
                    ffVar = new ff(th);
                }
                if (gf.a(ffVar) != null) {
                    mainActivity.v0(si.a("app_unavailable_on_this_head_unit", new Object[0]));
                }
                return tiVar;
            case 5:
                int i4 = MainActivity.E0;
                ComponentName componentNameM = mainActivity.m(1);
                if (componentNameM != null) {
                    return componentNameM.getPackageName();
                }
                return null;
            case 6:
                int i5 = MainActivity.E0;
                mainActivity.Q(new Intent("android.settings.WIFI_SETTINGS"));
                return tiVar;
            case 7:
                int i6 = MainActivity.E0;
                mainActivity.Q(new Intent("android.settings.BLUETOOTH_SETTINGS"));
                return tiVar;
            case 8:
                Integer num = mainActivity.Z.m;
                if (num != null) {
                    mainActivity.j0(Integer.valueOf(num.intValue()));
                    numValueOf = Integer.valueOf(num.intValue());
                } else {
                    Integer numValueOf2 = mainActivity.e;
                    if (numValueOf2 == null) {
                        int i7 = mainActivity.B().getInt("roof_slider_last", -1);
                        numValueOf2 = Integer.valueOf(i7);
                        if (i7 < 0 || i7 >= 101) {
                            numValueOf = null;
                        } else {
                            numValueOf = numValueOf2;
                        }
                    } else {
                        numValueOf = numValueOf2;
                    }
                }
                if (numValueOf != null) {
                    return Float.valueOf(100.0f - numValueOf.intValue());
                }
                return null;
            case 9:
                int i8 = MainActivity.E0;
                String strC = mainActivity.c(0);
                return strC == null ? si.a("maps", new Object[0]) : strC;
            case 10:
                int i9 = MainActivity.E0;
                if (!Settings.System.canWrite(mainActivity) && !mainActivity.B().getBoolean("brightness_asked", false)) {
                    mainActivity.B().edit().putBoolean("brightness_asked", true).apply();
                    c.e("later", new Object[0], new AlertDialog.Builder(mainActivity).setTitle(si.a("screen_brightness", new Object[0])).setMessage(si.a("the_head_unit_rejected_brightness_control_via", new Object[0])).setPositiveButton(si.a("allow", new Object[0]), new z1(mainActivity, c == true ? 1 : 0)), null, mainActivity);
                }
                return tiVar;
            case 11:
                int i10 = MainActivity.E0;
                k8.m(mainActivity);
                return tiVar;
            case 12:
                int i11 = MainActivity.E0;
                nj njVarH2 = mainActivity.H();
                v1 v1Var2 = new v1(mainActivity, 20);
                njVarH2.getClass();
                njVarH2.b(v1Var2, new ej(njVarH2, 6));
                return tiVar;
            case 13:
                int i12 = MainActivity.E0;
                k8.m(mainActivity);
                return tiVar;
            case 14:
                return Boolean.valueOf((!mainActivity.d || mainActivity.c || mainActivity.f || mainActivity.y.b) ? false : true);
            case 15:
                int i13 = MainActivity.E0;
                return mainActivity.getSharedPreferences("panel", 0);
            case 16:
                int i14 = MainActivity.E0;
                String strC2 = mainActivity.c(0);
                return strC2 == null ? si.a("maps", new Object[0]) : strC2;
            case 17:
                int i15 = MainActivity.E0;
                mainActivity.a0(0);
                return tiVar;
            case 18:
                int i16 = MainActivity.E0;
                k8.G(mainActivity);
                return tiVar;
            case 19:
                jc jcVar = mainActivity.b0;
                if (!jcVar.i || th.A(jcVar.c)) {
                    jcVar = null;
                }
                if (jcVar == null) {
                    return null;
                }
                List listI0 = j3.i0(jcVar.d, jcVar.c);
                ArrayList arrayList = new ArrayList();
                for (Object obj : listI0) {
                    if (!th.A((String) obj)) {
                        arrayList.add(obj);
                    }
                }
                return i3.y0(arrayList, " — ", null, 62);
            case 20:
                int i17 = MainActivity.E0;
                Float f = mainActivity.I().i.a;
                if (f == null) {
                    return null;
                }
                int iRound = Math.round(f.floatValue());
                StringBuilder sb = iRound > 0 ? new StringBuilder("+") : new StringBuilder();
                sb.append(iRound);
                sb.append("°");
                return th.J(sb.toString() + " " + mainActivity.I().i.b()).toString();
            case 21:
                int i18 = MainActivity.E0;
                Context applicationContext = mainActivity.getApplicationContext();
                applicationContext.getClass();
                return new kh(applicationContext);
            case 22:
                int i19 = MainActivity.E0;
                return new vj(mainActivity, new v1(mainActivity, 23));
            case 23:
                int i20 = MainActivity.E0;
                return new nj(mainActivity, new v1(mainActivity, 28));
            case 24:
                int i21 = MainActivity.E0;
                return new ye(mainActivity, new ua(mainActivity, i2));
            case 25:
                int i22 = MainActivity.E0;
                int identifier = mainActivity.getResources().getIdentifier("status_bar_height", "dimen", "android");
                Integer numValueOf3 = Integer.valueOf(identifier);
                if (identifier == 0) {
                    numValueOf3 = null;
                }
                if (numValueOf3 != null) {
                    try {
                        ffVar2 = Integer.valueOf(mainActivity.getResources().getDimensionPixelSize(numValueOf3.intValue()));
                    } catch (Throwable th2) {
                        ffVar2 = new ff(th2);
                    }
                    Integer num2 = (Integer) (ffVar2 instanceof ff ? null : ffVar2);
                    if (num2 != null) {
                        iR = num2.intValue();
                        int iR2 = mainActivity.r(24);
                        if (iR < iR2) {
                            iR = iR2;
                        }
                    } else {
                        iR = mainActivity.r(32);
                    }
                    break;
                } else {
                    iR = mainActivity.r(32);
                }
                return Integer.valueOf(iR);
            case 26:
                int i23 = MainActivity.E0;
                if (!mainActivity.isDestroyed()) {
                    mainActivity.e();
                }
                return tiVar;
            case 27:
                jc jcVar2 = mainActivity.b0;
                if (!jcVar2.i || th.A(jcVar2.c)) {
                    jcVar2 = null;
                }
                if (jcVar2 == null) {
                    return null;
                }
                List listI1 = j3.i0(jcVar2.d, jcVar2.c);
                ArrayList arrayList2 = new ArrayList();
                for (Object obj2 : listI1) {
                    if (!th.A((String) obj2)) {
                        arrayList2.add(obj2);
                    }
                }
                return i3.y0(arrayList2, " — ", null, 62);
            case 28:
                int i24 = MainActivity.E0;
                Float f2 = mainActivity.I().i.a;
                if (f2 == null) {
                    return null;
                }
                int iRound2 = Math.round(f2.floatValue());
                StringBuilder sb2 = iRound2 > 0 ? new StringBuilder("+") : new StringBuilder();
                sb2.append(iRound2);
                sb2.append("°");
                return th.J(sb2.toString() + " " + mainActivity.I().i.b()).toString();
            default:
                mainActivity.f = true;
                mainActivity.e0();
                return tiVar;
        }
    }
}
