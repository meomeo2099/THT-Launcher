package defpackage;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.reference.panel.MainActivity;
import org.reference.panel.b;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class v1 implements j6, n6, o6 {
    public final /* synthetic */ int a;
    public final /* synthetic */ MainActivity b;

    public /* synthetic */ v1(MainActivity mainActivity, int i) {
        this.a = i;
        this.b = mainActivity;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:37:0x00b7  */
    @Override // defpackage.o6
    public Object a(Object obj, Object obj2, Object obj3) {
        final String str = (String) obj;
        j4 j4Var = (j4) obj2;
        boolean zBooleanValue = ((Boolean) obj3).booleanValue();
        int i = MainActivity.E0;
        str.getClass();
        j4Var.getClass();
        final MainActivity mainActivity = this.b;
        i4 i4Var = new i4(mainActivity, mainActivity.z());
        LinearLayout linearLayoutL = mainActivity.l();
        Set set = aj.i;
        String strA = (String) sh.e().get(th.C(str, "vehicle_"));
        if (strA == null) {
            switch (str) {
                case "car":
                    strA = si.a("vehicle", new Object[0]);
                    break;
                case "date":
                    strA = si.a("date", new Object[0]);
                    break;
                case "dock":
                    strA = si.a("main_menu", new Object[0]);
                    break;
                case "wifi":
                    strA = "Wi-Fi";
                    break;
                case "clock":
                    strA = si.a("clock", new Object[0]);
                    break;
                case "music":
                    strA = si.a("music", new Object[0]);
                    break;
                case "navigation":
                    strA = si.a("navigation_206", new Object[0]);
                    break;
                case "bluetooth":
                    strA = "Bluetooth";
                    break;
                default:
                    strA = si.a("widget_411", new Object[0]);
                    break;
            }
        }
        final c4 c4Var = mainActivity.g;
        if (c4Var != null) {
            List list = si.a;
            linearLayoutL.addView(mainActivity.u0(si.a("screen_pixels_x_and_y_start_at", Integer.valueOf(c4Var.getWidth()), Integer.valueOf(c4Var.getHeight())), 15.0f, mainActivity.p));
            LinearLayout linearLayout = new LinearLayout(mainActivity);
            linearLayout.setGravity(16);
            linearLayoutL.addView(linearLayout);
            final EditText editTextO0 = k8.O0(mainActivity, si.a("width", new Object[0]), j4Var.c, linearLayout);
            final EditText editTextO1 = k8.O0(mainActivity, si.a("height", new Object[0]), j4Var.d, linearLayout);
            LinearLayout linearLayout2 = new LinearLayout(mainActivity);
            linearLayout2.setGravity(16);
            linearLayoutL.addView(linearLayout2);
            final EditText editTextO2 = k8.O0(mainActivity, si.a("x_from_left_edge", new Object[0]), j4Var.a, linearLayout2);
            final EditText editTextO3 = k8.O0(mainActivity, si.a("y_from_top_edge", new Object[0]), j4Var.b, linearLayout2);
            if (zBooleanValue) {
                linearLayoutL.addView(mainActivity.u0(si.a("square_widget_width_and_height_are_linked", new Object[0]), 15.0f, mainActivity.p));
                je jeVar = new je();
                editTextO0.addTextChangedListener(new pg(jeVar, editTextO1));
                editTextO1.addTextChangedListener(new pg(jeVar, editTextO0));
            }
            String strA2 = si.a("backing_transparency", new Object[0]);
            int i2 = zj.a;
            SharedPreferences sharedPreferencesB = mainActivity.B();
            sharedPreferencesB.getClass();
            Button buttonA = i4Var.a(c.d(strA2, ": ", si.a("screensaver_percent", Integer.valueOf(zj.c(sharedPreferencesB, zj.d(str))))), false, new u0(mainActivity, str, c4Var, 4));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, mainActivity.r(52));
            layoutParams.topMargin = mainActivity.r(12);
            linearLayoutL.addView(buttonA, layoutParams);
            String strA3 = si.a("exact_size_and_position", new Object[0]);
            ScrollView scrollView = new ScrollView(mainActivity);
            scrollView.addView(linearLayoutL);
            final Dialog dialogF = i4Var.f(strA, strA3, scrollView);
            linearLayoutL.addView(i4Var.a(si.a("apply", new Object[0]), true, new y5() { // from class: eg
                @Override // defpackage.y5
                public final Object a() {
                    Float f;
                    EditText editText = editTextO2;
                    EditText editText2 = editTextO3;
                    EditText editText3 = editTextO0;
                    EditText editText4 = editTextO1;
                    List listI0 = j3.i0(editText, editText2, editText3, editText4);
                    ArrayList arrayList = new ArrayList(k3.n0(listI0));
                    Iterator it = listI0.iterator();
                    while (it.hasNext()) {
                        arrayList.add(ai.p(ci.r(((EditText) it.next()).getText().toString(), ',', '.')));
                    }
                    boolean z = false;
                    boolean z2 = true;
                    int i3 = 0;
                    for (Object obj4 : listI0) {
                        int i4 = i3 + 1;
                        if (i3 < 0) {
                            j3.m0();
                            throw null;
                        }
                        EditText editText5 = (EditText) obj4;
                        if (arrayList.get(i3) == null || (f = (Float) arrayList.get(i3)) == null || Math.abs(f.floatValue()) > Float.MAX_VALUE) {
                            editText5.setError(si.a("enter_a_number", new Object[0]));
                            z2 = false;
                        }
                        i3 = i4;
                    }
                    if (z2) {
                        Object obj5 = arrayList.get(0);
                        obj5.getClass();
                        float fFloatValue = ((Number) obj5).floatValue();
                        Object obj6 = arrayList.get(1);
                        obj6.getClass();
                        float fFloatValue2 = ((Number) obj6).floatValue();
                        Object obj7 = arrayList.get(2);
                        obj7.getClass();
                        float fFloatValue3 = ((Number) obj7).floatValue();
                        Object obj8 = arrayList.get(3);
                        obj8.getClass();
                        float fFloatValue4 = ((Number) obj8).floatValue();
                        j4 j4Var2 = new j4(fFloatValue, fFloatValue2, fFloatValue3, fFloatValue4);
                        c4 c4Var2 = c4Var;
                        if (fFloatValue < 0.0f || fFloatValue > c4Var2.getWidth() - 24) {
                            editText.setError(si.a("outside_the_screen", new Object[0]));
                            z2 = false;
                        }
                        if (fFloatValue2 < 0.0f || fFloatValue2 > c4Var2.getHeight() - 24) {
                            editText2.setError(si.a("outside_the_screen", new Object[0]));
                            z2 = false;
                        }
                        if (fFloatValue3 < 24.0f || fFloatValue + fFloatValue3 > c4Var2.getWidth()) {
                            editText3.setError(si.a("at_least_24_cannot_extend_beyond_the", new Object[0]));
                            z2 = false;
                        }
                        if (fFloatValue4 < 24.0f || fFloatValue2 + fFloatValue4 > c4Var2.getHeight()) {
                            editText4.setError(si.a("at_least_24_cannot_extend_beyond_the_422", new Object[0]));
                        } else {
                            z = z2;
                        }
                        if (z) {
                            c4Var2.d(str, j4Var2);
                            dialogF.dismiss();
                            mainActivity.e0();
                        }
                    }
                    return ti.a;
                }
            }));
            dialogF.show();
            Window window = dialogF.getWindow();
            if (window != null) {
                window.setSoftInputMode(19);
            }
        }
        return ti.a;
    }

    @Override // defpackage.j6
    public Object b(Object obj) {
        int i = this.a;
        int i2 = 0;
        ti tiVar = ti.a;
        MainActivity mainActivity = this.b;
        switch (i) {
            case 0:
                return Integer.valueOf(mainActivity.r(((Integer) obj).intValue()));
            case 1:
                String str = (String) obj;
                if (str != null) {
                    mainActivity.v0(str);
                }
                return tiVar;
            case 2:
                String str2 = (String) obj;
                str2.getClass();
                return k8.n(str2, mainActivity);
            case 3:
                String str3 = (String) obj;
                str3.getClass();
                Map map = q8.t;
                String strI = k8.i(mainActivity, str3);
                return strI == null ? mainActivity.W(str3) : strI;
            case 4:
                String str4 = (String) obj;
                if (str4 != null) {
                    mainActivity.v0(str4);
                }
                return tiVar;
            case 5:
                String str5 = (String) obj;
                if (str5 != null) {
                    mainActivity.v0(str5);
                }
                return tiVar;
            case 6:
                String str6 = (String) obj;
                if (str6 != null) {
                    mainActivity.v0(str6);
                }
                return tiVar;
            case 7:
                String str7 = (String) obj;
                if (str7 != null) {
                    mainActivity.v0(str7);
                }
                return tiVar;
            case 8:
                String str8 = (String) obj;
                str8.getClass();
                View viewM = mainActivity.M(str8);
                if (viewM instanceof d9) {
                    int i3 = l8.a;
                    ((d9) viewM).setColor(l8.j(mainActivity.z()));
                }
                return viewM;
            case 9:
                String str9 = (String) obj;
                str9.getClass();
                return mainActivity.W(str9);
            case 10:
                String str10 = (String) obj;
                str10.getClass();
                return mainActivity.O(str10);
            case 11:
            case 27:
            default:
                int iIntValue = ((Integer) obj).intValue();
                int i4 = MainActivity.E0;
                k8.t0(mainActivity, iIntValue);
                return tiVar;
            case 12:
                String str11 = (String) obj;
                if (str11 != null) {
                    mainActivity.v0(str11);
                }
                return tiVar;
            case 13:
                String str12 = (String) obj;
                str12.getClass();
                MainActivity mainActivity2 = this.b;
                return new j9(mainActivity2, mainActivity2.z(), mainActivity2.M(str12), mainActivity2.W(str12), false);
            case 14:
                String str13 = (String) obj;
                int i5 = MainActivity.E0;
                str13.getClass();
                xd.a(str13, mainActivity);
                return tiVar;
            case 15:
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                int i6 = MainActivity.E0;
                if (!zBooleanValue) {
                    mainActivity.d0();
                    if (mainActivity.D.isEmpty()) {
                        mainActivity.r0();
                    }
                } else if (mainActivity.o0()) {
                    mainActivity.y().A();
                    mainActivity.H().p();
                }
                return tiVar;
            case 16:
                float fFloatValue = ((Float) obj).floatValue();
                int i7 = MainActivity.E0;
                int iS = pi.s(100 - Math.round(fFloatValue), 0, 100);
                mainActivity.j0(Integer.valueOf(iS));
                nj njVarH = mainActivity.H();
                v1 v1Var = new v1(mainActivity, 18);
                njVarH.getClass();
                njVarH.b(v1Var, new ij(iS, njVarH, 2));
                return tiVar;
            case 17:
                int iIntValue2 = ((Integer) obj).intValue();
                int i8 = MainActivity.E0;
                k8.t0(mainActivity, iIntValue2);
                return tiVar;
            case 18:
                String str14 = (String) obj;
                int i9 = MainActivity.E0;
                if (str14 != null) {
                    mainActivity.v0(str14);
                }
                return tiVar;
            case 19:
                b bVar = (b) obj;
                int i10 = MainActivity.E0;
                bVar.getClass();
                ArrayList arrayList = mainActivity.w;
                ArrayList arrayList2 = new ArrayList();
                int size = arrayList.size();
                int i11 = 0;
                while (i11 < size) {
                    Object obj2 = arrayList.get(i11);
                    i11++;
                    b bVar2 = (b) obj2;
                    if (bVar2 != bVar && (bVar2.getSlot() == bVar.getSlot() || (bVar2.l() != null && k8.e(bVar2.l(), bVar.l())))) {
                        arrayList2.add(obj2);
                    }
                }
                int size2 = arrayList2.size();
                while (i2 < size2) {
                    Object obj3 = arrayList2.get(i2);
                    i2++;
                    ((b) obj3).h();
                }
                return tiVar;
            case 20:
                String str15 = (String) obj;
                int i12 = MainActivity.E0;
                if (str15 != null) {
                    mainActivity.v0(str15);
                }
                return tiVar;
            case 21:
                jc jcVar = (jc) obj;
                int i13 = MainActivity.E0;
                jcVar.getClass();
                mainActivity.b0 = jcVar;
                ArrayList arrayList3 = mainActivity.V;
                int size3 = arrayList3.size();
                int i14 = 0;
                while (i14 < size3) {
                    Object obj4 = arrayList3.get(i14);
                    i14++;
                    ((o9) obj4).d(jcVar);
                }
                ArrayList arrayList4 = mainActivity.W;
                int size4 = arrayList4.size();
                while (i2 < size4) {
                    Object obj5 = arrayList4.get(i2);
                    i2++;
                    ((kc) obj5).a(jcVar);
                }
                return tiVar;
            case 22:
                mainActivity.o0 = ((Integer) obj).intValue();
                return tiVar;
            case 23:
                wj wjVar = (wj) obj;
                int i15 = MainActivity.E0;
                wjVar.getClass();
                ArrayList arrayList5 = mainActivity.M;
                int size5 = arrayList5.size();
                while (i2 < size5) {
                    Object obj6 = arrayList5.get(i2);
                    i2++;
                    ((v9) obj6).setWeather(wjVar);
                }
                return tiVar;
            case 24:
                boolean zBooleanValue2 = ((Boolean) obj).booleanValue();
                int i16 = MainActivity.E0;
                if (!zBooleanValue2) {
                    mainActivity.runOnUiThread(new sa(mainActivity, 5));
                }
                return tiVar;
            case 25:
                String str16 = (String) obj;
                int i17 = MainActivity.E0;
                str16.getClass();
                mainActivity.v0(str16);
                return tiVar;
            case 26:
                ComponentName componentName = (ComponentName) obj;
                int i18 = MainActivity.E0;
                componentName.getClass();
                Intent intentMakeMainActivity = Intent.makeMainActivity(componentName);
                intentMakeMainActivity.getClass();
                mainActivity.Q(intentMakeMainActivity);
                return tiVar;
            case 28:
                bj bjVar = (bj) obj;
                int i19 = MainActivity.E0;
                bjVar.getClass();
                mainActivity.Z = bjVar;
                ArrayList arrayList6 = mainActivity.z;
                int size6 = arrayList6.size();
                int i20 = 0;
                while (i20 < size6) {
                    Object obj7 = arrayList6.get(i20);
                    i20++;
                    ((aj) obj7).a(bjVar);
                }
                ArrayList arrayList7 = mainActivity.A;
                int size7 = arrayList7.size();
                while (i2 < size7) {
                    Object obj8 = arrayList7.get(i2);
                    i2++;
                    yc ycVar = (yc) obj8;
                    mainActivity.x0((String) ycVar.a, (cj) ycVar.b);
                }
                mainActivity.c0();
                return tiVar;
        }
    }

    @Override // defpackage.n6
    public Object c(Object obj, Object obj2) {
        String str = (String) obj;
        View view = (View) obj2;
        str.getClass();
        view.getClass();
        if (view instanceof cj) {
            this.b.x0(str, (cj) view);
        }
        return ti.a;
    }
}
