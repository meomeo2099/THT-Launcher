package defpackage;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class aj extends FrameLayout {
    public static final Set i = x0.Y0(new String[]{"nozzle_heat", "wiper_heat"});
    public static final Set j = x0.Y0(new String[]{"battery", "fuel"});
    public final String a;
    public final int b;
    public final int c;
    public final TextView d;
    public final cj e;
    public final b7 f;
    public final SharedPreferences g;
    public final TextView h;

    public aj(MainActivity mainActivity, String str, boolean z, w7 w7Var) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        super(mainActivity);
        this.a = str;
        if (z) {
            i2 = 240;
            i3 = 247;
            i4 = 233;
        } else {
            i2 = 43;
            i3 = 62;
            i4 = 23;
        }
        int iRgb = Color.rgb(i4, i2, i3);
        this.b = iRgb;
        if (z) {
            i5 = 177;
            i6 = 196;
            i7 = 157;
        } else {
            i5 = 93;
            i6 = 114;
            i7 = 69;
        }
        int iRgb2 = Color.rgb(i7, i5, i6);
        this.c = iRgb2;
        TextView textView = new TextView(mainActivity);
        textView.setTextColor(iRgb);
        textView.setIncludeFontPadding(false);
        textView.setGravity(17);
        this.d = textView;
        cj cjVar = new cj(mainActivity, str);
        cjVar.setHalo(false);
        cjVar.setNightMode(z);
        cjVar.setActiveSurface(true);
        this.e = cjVar;
        b7 b7Var = new b7(z, l8.m(mainActivity) * 24.0f, true);
        this.f = b7Var;
        SharedPreferences sharedPreferences = mainActivity.getSharedPreferences("panel", 0);
        this.g = sharedPreferences;
        TextView textView2 = new TextView(mainActivity);
        textView2.setTextColor(iRgb2);
        textView2.setIncludeFontPadding(false);
        textView2.setMaxLines(2);
        this.h = textView2;
        int i8 = !str.equals("climate") ? 180 : 320;
        c4 c4Var = new c4(mainActivity, i8, 180.0f);
        c4Var.setBackground(b7Var);
        if (str.equals("battery") || str.equals("fuel")) {
            c4.c(c4Var, cjVar, (i8 - 112) / 2, 12, 112, 96, null, 96);
            c4.c(c4Var, textView, 4, 110, i8 - 8, 62, null, 64);
        } else {
            c4.c(c4Var, cjVar, (i8 - 140) / 2, 20, 140, 140, null, 96);
        }
        textView2.setGravity(17);
        addView(c4Var, new FrameLayout.LayoutParams(-1, -1));
        setOnClickListener(new s(16, w7Var));
        setContentDescription(sh.m(str, sharedPreferences.getString("vehicle", null)));
        a(new bj(null, null, null, null, null, null, 67108863));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:225:0x0444  */
    /* JADX WARN: Code duplicated, block: B:227:0x045f  */
    /* JADX WARN: Code duplicated, block: B:228:0x0469  */
    /* JADX WARN: Code duplicated, block: B:232:0x048f  */
    /* JADX WARN: Code duplicated, block: B:233:0x0499  */
    /* JADX WARN: Code duplicated, block: B:235:0x04a0  */
    /* JADX WARN: Code duplicated, block: B:236:0x04a9 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:237:0x04ab  */
    /* JADX WARN: Code duplicated, block: B:238:0x04b4  */
    /* JADX WARN: Code duplicated, block: B:78:0x0188  */
    /* JADX WARN: Code duplicated, block: B:84:0x01b6  */
    /* JADX WARN: Code duplicated, block: B:85:0x01b9  */
    /* JADX WARN: Instruction removed from duplicated block: B:84:0x01b6, please report this as an issue */
    public final void a(bj bjVar) {
        Boolean bool;
        Boolean boolValueOf;
        String strA;
        String strA2;
        Boolean boolValueOf2;
        Integer numC;
        int iIntValue;
        String strA3;
        boolean z;
        String strA4;
        String strA5;
        Boolean boolValueOf3;
        Integer num;
        bjVar.getClass();
        Boolean bool2 = bjVar.e;
        Float f = bjVar.a;
        v2 v2Var = bjVar.i;
        Integer num2 = bjVar.r;
        Set set = rf.d;
        LinkedHashMap linkedHashMapB = pi.B();
        String str = this.a;
        rf rfVar = (rf) linkedHashMapB.get(str);
        TextView textView = this.h;
        TextView textView2 = this.d;
        cj cjVar = this.e;
        if (rfVar != null) {
            tf tfVar = (tf) bjVar.j.get(str);
            textView2.setText(rfVar.b(tfVar != null ? tfVar.a : null));
            cjVar.setMaxLevel(rfVar.a());
            cjVar.setLevel(rfVar.c(tfVar != null ? tfVar.a : null));
            if (tfVar == null || (num = tfVar.a) == null) {
                boolValueOf3 = null;
            } else {
                boolValueOf3 = Boolean.valueOf(num.intValue() != 0);
            }
            cjVar.setEnabledState(boolValueOf3);
            textView.setText((tfVar != null ? tfVar.a : null) == null ? si.a("state_unknown", new Object[0]) : si.a("tap_for_the_next_level", new Object[0]));
            setContentDescription(rfVar.c + ": " + ((Object) textView2.getText()));
            b();
            return;
        }
        SharedPreferences sharedPreferences = this.g;
        String str2 = "—";
        switch (str) {
            case "drive_comfort":
            case "start_stop":
            case "drive_eco":
                boolean zN = sh.n(str, bjVar);
                cjVar.setEnabledState(Boolean.valueOf(zN));
                cjVar.setLevel(null);
                String strM = sh.m(str, sharedPreferences.getString("vehicle", null));
                if (zN) {
                    strA5 = si.a("on_101", new Object[0]);
                } else {
                    strA5 = si.a("off_102", new Object[0]);
                }
                setContentDescription(strM + ": " + strA5);
            case "cruise":
                cjVar.setCruiseMode(num2);
                if (num2 != null && num2.intValue() == 537069058) {
                    bool = Boolean.FALSE;
                } else {
                    bool = (num2 != null && num2.intValue() == 537069059) ? Boolean.TRUE : null;
                }
                cjVar.setEnabledState(bool);
                String strA6 = si.a("cruise_mode", new Object[0]);
                if (num2 != null && num2.intValue() == 537069058) {
                    str2 = "ACC";
                } else if (num2 != null && num2.intValue() == 537069059) {
                    str2 = "G-Pilot";
                }
                setContentDescription(strA6 + ": " + str2);
                z = num2 != null;
                if (str.equals("climate")) {
                    strA4 = si.a("tap_for_climate_controls", new Object[0]);
                } else if (str.equals("ambient")) {
                    strA4 = si.a("tap_for_lighting_controls", new Object[0]);
                } else if (z) {
                    strA4 = si.a("vehicle_data", new Object[0]);
                } else {
                    strA4 = bjVar.k;
                }
                textView.setText(strA4);
                b();
                return;
            case "roof_tilt":
                boolean zN2 = sh.n(str, bjVar);
                cjVar.setEnabledState(Boolean.valueOf(zN2));
                cjVar.setLevel(null);
                String strM2 = sh.m(str, sharedPreferences.getString("vehicle", null));
                if (zN2) {
                    strA5 = si.a("on_101", new Object[0]);
                } else {
                    strA5 = si.a("off_102", new Object[0]);
                }
                setContentDescription(strM2 + ": " + strA5);
            case "airflow":
                List list = z2.a;
                Integer numA = z2.a((Integer) v2Var.f.get(268894464));
                cjVar.setBlowMode(numA);
                if (numA != null) {
                    Integer num3 = (Integer) v2Var.f.get(268501248);
                    boolValueOf = Boolean.valueOf((num3 == null || num3.intValue() == 0) ? false : true);
                } else {
                    boolValueOf = null;
                }
                cjVar.setEnabledState(boolValueOf);
                cjVar.setLevel(null);
                List list2 = si.a;
                if (numA == null || (strA = z2.g(268894464, numA.intValue())) == null) {
                    strA = si.a("direction_unknown", new Object[0]);
                }
                setContentDescription(si.a("airflow_0", strA));
                if (numA != null) {
                }
                if (str.equals("climate")) {
                    strA4 = si.a("tap_for_climate_controls", new Object[0]);
                } else if (str.equals("ambient")) {
                    strA4 = si.a("tap_for_lighting_controls", new Object[0]);
                } else if (z) {
                    strA4 = si.a("vehicle_data", new Object[0]);
                } else {
                    strA4 = bjVar.k;
                }
                textView.setText(strA4);
                b();
                return;
            case "ambient":
                cjVar.setEnabledState(bool2);
                cjVar.setLevel(null);
                Object obj = sh.e().get(str);
                if (k8.e(bool2, Boolean.TRUE)) {
                    strA2 = si.a("on_651", new Object[0]);
                } else if (k8.e(bool2, Boolean.FALSE)) {
                    strA2 = si.a("off_652", new Object[0]);
                } else {
                    if (bool2 != null) {
                        throw new vc();
                    }
                    strA2 = si.a("state_unknown_653", new Object[0]);
                }
                setContentDescription(obj + ": " + strA2);
                if (bool2 != null) {
                }
                if (str.equals("climate")) {
                    strA4 = si.a("tap_for_climate_controls", new Object[0]);
                } else if (str.equals("ambient")) {
                    strA4 = si.a("tap_for_lighting_controls", new Object[0]);
                } else if (z) {
                    strA4 = si.a("vehicle_data", new Object[0]);
                } else {
                    strA4 = bjVar.k;
                }
                textView.setText(strA4);
                b();
                return;
            case "wipers":
            case "ext_light":
                boolean zN3 = sh.n(str, bjVar);
                cjVar.setEnabledState(Boolean.valueOf(zN3));
                cjVar.setLevel(null);
                String strM3 = sh.m(str, sharedPreferences.getString("vehicle", null));
                if (zN3) {
                    strA5 = si.a("on_101", new Object[0]);
                } else {
                    strA5 = si.a("off_102", new Object[0]);
                }
                setContentDescription(strM3 + ": " + strA5);
            case "battery":
                textView2.setText(f != null ? String.format(Locale.getDefault(), "%.1f %s", Arrays.copyOf(new Object[]{Float.valueOf(f.floatValue()), si.a("v", new Object[0])}, 2)) : "—");
                setContentDescription(sh.e().get(str) + ": " + ((Object) textView2.getText()));
                cjVar.setEnabledState(f != null ? Boolean.TRUE : null);
                if (f != null) {
                }
                if (str.equals("climate")) {
                    strA4 = si.a("tap_for_climate_controls", new Object[0]);
                } else if (str.equals("ambient")) {
                    strA4 = si.a("tap_for_lighting_controls", new Object[0]);
                } else if (z) {
                    strA4 = si.a("vehicle_data", new Object[0]);
                } else {
                    strA4 = bjVar.k;
                }
                textView.setText(strA4);
                b();
                return;
            case "drive_offroad":
            case "drive_sport_plus":
                boolean zN4 = sh.n(str, bjVar);
                cjVar.setEnabledState(Boolean.valueOf(zN4));
                cjVar.setLevel(null);
                String strM4 = sh.m(str, sharedPreferences.getString("vehicle", null));
                if (zN4) {
                    strA5 = si.a("on_101", new Object[0]);
                } else {
                    strA5 = si.a("off_102", new Object[0]);
                }
                setContentDescription(strM4 + ": " + strA5);
            case "fuel":
                String strX = pi.X(bjVar, sharedPreferences.getInt("fuel_display", 0), sharedPreferences.getFloat("tank_liters", 50.0f));
                textView2.setText(strX != null ? strX : "—");
                setContentDescription(sh.e().get(str) + ": " + ((Object) textView2.getText()));
                cjVar.setEnabledState(strX != null ? Boolean.TRUE : null);
                if (strX != null) {
                }
                if (str.equals("climate")) {
                    strA4 = si.a("tap_for_climate_controls", new Object[0]);
                } else if (str.equals("ambient")) {
                    strA4 = si.a("tap_for_lighting_controls", new Object[0]);
                } else if (z) {
                    strA4 = si.a("vehicle_data", new Object[0]);
                } else {
                    strA4 = bjVar.k;
                }
                textView.setText(strA4);
                b();
                return;
            case "roof":
            case "trunk":
            case "nozzle_heat":
            case "drive_sand":
            case "drive_snow":
            case "drive_power":
            case "drive_sport":
                boolean zN5 = sh.n(str, bjVar);
                cjVar.setEnabledState(Boolean.valueOf(zN5));
                cjVar.setLevel(null);
                String strM5 = sh.m(str, sharedPreferences.getString("vehicle", null));
                if (zN5) {
                    strA5 = si.a("on_101", new Object[0]);
                } else {
                    strA5 = si.a("off_102", new Object[0]);
                }
                setContentDescription(strM5 + ": " + strA5);
            case "climate":
                Map map = v2Var.f;
                Map map2 = v2Var.a;
                Integer num4 = (Integer) map.get(268501248);
                if (num4 != null) {
                    boolValueOf2 = Boolean.valueOf(num4.intValue() != 0);
                } else {
                    boolValueOf2 = null;
                }
                cjVar.setEnabledState(boolValueOf2);
                Integer num5 = (Integer) map.get(268501248);
                if (num5 != null && num5.intValue() == 0) {
                    numC = 0;
                } else {
                    List list3 = z2.a;
                    numC = z2.c((Integer) map.get(268566784));
                    if (numC == null || (iIntValue = numC.intValue()) < 0 || iIntValue >= 10) {
                        numC = null;
                    }
                }
                cjVar.setLevel(numC);
                Float f2 = (Float) map2.get(1);
                if (f2 != null) {
                    str2 = Math.round(f2.floatValue()) + "°";
                }
                String str3 = str2;
                Integer num6 = (Integer) map.get(268566784);
                if (num6 != null) {
                    int iIntValue2 = num6.intValue();
                    List list4 = z2.a;
                    strA3 = z2.g(268566784, iIntValue2);
                    if (strA3 == null) {
                        strA3 = si.a("fan_648", new Object[0]);
                    }
                } else {
                    strA3 = si.a("fan_648", new Object[0]);
                }
                textView2.setText(str3 + "\n" + strA3);
                if (!map2.isEmpty() || !map.isEmpty()) {
                }
                if (str.equals("climate")) {
                    strA4 = si.a("tap_for_climate_controls", new Object[0]);
                } else if (str.equals("ambient")) {
                    strA4 = si.a("tap_for_lighting_controls", new Object[0]);
                } else if (z) {
                    strA4 = si.a("vehicle_data", new Object[0]);
                } else {
                    strA4 = bjVar.k;
                }
                textView.setText(strA4);
                b();
                return;
            case "mirrors":
            case "curtain":
            case "drive_adaptive":
            case "wiper_heat":
                boolean zN6 = sh.n(str, bjVar);
                cjVar.setEnabledState(Boolean.valueOf(zN6));
                cjVar.setLevel(null);
                String strM6 = sh.m(str, sharedPreferences.getString("vehicle", null));
                if (zN6) {
                    strA5 = si.a("on_101", new Object[0]);
                } else {
                    strA5 = si.a("off_102", new Object[0]);
                }
                setContentDescription(strM6 + ": " + strA5);
            default:
        }
    }

    public final void b() {
        cj cjVar = this.e;
        boolean z = k8.e(cjVar.getEnabledState(), Boolean.TRUE) && !j.contains(this.a);
        this.f.a(cjVar.getActiveAccent(), z);
        this.d.setTextColor((!z || cjVar.getNightMode()) ? this.b : -1);
        this.h.setTextColor((!z || cjVar.getNightMode()) ? this.c : -1);
    }

    public final String getKind() {
        return this.a;
    }
}
