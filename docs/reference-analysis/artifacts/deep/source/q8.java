package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class q8 extends LinearLayout {
    public static final Map t = hb.j0(new yc("seat_heat_1", "driver_heat"), new yc("seat_vent_1", "driver_vent"), new yc("seat_heat_4", "passenger_heat"), new yc("seat_vent_4", "passenger_vent"));
    public final boolean a;
    public final boolean b;
    public final x2 c;
    public final q0 d;
    public final float e;
    public final boolean f;
    public final int g;
    public final int h;
    public final int i;
    public final TextView j;
    public final TextView k;
    public final TextView l;
    public final t2 m;
    public final y8 n;
    public final LinkedHashMap o;
    public final LinkedHashMap p;
    public final LinkedHashMap q;
    public final LinkedHashMap r;
    public y5 s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Code duplicated, block: B:34:0x036a  */
    /* JADX WARN: Code duplicated, block: B:85:0x04e0  */
    public q8(MainActivity mainActivity, boolean z, boolean z2, x2 x2Var, q0 q0Var) {
        float f;
        q8 q8Var;
        int i;
        char c;
        t2 t2Var;
        super(mainActivity);
        mainActivity.getClass();
        this.a = z;
        this.b = z2;
        this.c = x2Var;
        this.d = q0Var;
        float fM = l8.m(mainActivity);
        this.e = fM;
        boolean z3 = mainActivity.getSharedPreferences("panel", 0).getBoolean("menu_labels", false);
        this.f = z3;
        int iJ = l8.j(z);
        this.g = iJ;
        int iK = l8.k(z);
        this.h = iK;
        l8.h();
        this.i = l8.a(-10233877, z);
        TextView textViewG = g();
        this.j = textViewG;
        TextView textViewG2 = g();
        this.k = textViewG2;
        TextView textView = new TextView(mainActivity);
        textView.setTextColor(iK);
        textView.setTextSize((fM * 15.0f) / textView.getResources().getDisplayMetrics().scaledDensity);
        this.l = textView;
        t2 t2VarH = h(this, "power", si.a("climate", new Object[0]), "power", null, 16);
        this.m = t2VarH;
        y8 y8Var = new y8(mainActivity, z, new a(3, this), null);
        this.n = y8Var;
        this.o = new LinkedHashMap();
        this.p = new LinkedHashMap();
        this.q = new LinkedHashMap();
        this.r = new LinkedHashMap();
        setOrientation(1);
        setLayoutDirection(0);
        setTextDirection(5);
        setBackground(new b7(z, d(30.0f), false));
        setPadding(d(22.0f), d(16.0f), d(22.0f), d(22.0f));
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setGravity(16);
        TextView textViewC = c(si.a("climate", new Object[0]), 28.0f, iJ);
        textViewC.setContentDescription(si.a("climate_hold_to_open_diagnostics", new Object[0]));
        textViewC.setOnLongClickListener(new l4(this, 1));
        linearLayout.addView(textViewC, new LinearLayout.LayoutParams(0, -2, 1.0f));
        t2VarH.setOnClickListener(new s(5, this));
        linearLayout.addView(t2VarH, new LinearLayout.LayoutParams(d(z3 ? 126.0f : 56.0f), d(47.0f)));
        t2 t2VarH2 = h(this, "edit", si.a("climate_customize", new Object[0]), "gear", null, 16);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(d(z3 ? 140.0f : 56.0f), d(47.0f));
        layoutParams.leftMargin = d(8.0f);
        linearLayout.addView(t2VarH2, layoutParams);
        float f2 = 8.0f;
        t2 t2VarH3 = h(this, "close", si.a("close", new Object[0]), "close", null, 16);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(d(z3 ? 120.0f : 56.0f), d(47.0f));
        layoutParams2.leftMargin = d(8.0f);
        linearLayout.addView(t2VarH3, layoutParams2);
        addView(linearLayout, new LinearLayout.LayoutParams(-1, d(52.0f)));
        LinearLayout linearLayout2 = new LinearLayout(mainActivity);
        linearLayout2.setOrientation(0);
        q8 q8Var2 = this;
        linearLayout2.addView(b(mainActivity, this, si.a(z2 ? "climate_driver_zone" : "temperature", new Object[0]), textViewG, "temp-", "temp+"), new LinearLayout.LayoutParams(0, q8Var2.d(126.0f), 1.0f));
        LinearLayout linearLayout3 = new LinearLayout(mainActivity);
        int i2 = 1;
        linearLayout3.setOrientation(1);
        char c2 = 0;
        linearLayout3.setPadding(q8Var2.d(18.0f), q8Var2.d(11.0f), q8Var2.d(18.0f), q8Var2.d(14.0f));
        linearLayout3.setBackground(new b7(z, q8Var2.d(20.0f), true));
        linearLayout3.addView(q8Var2.c(si.a("fan_84", new Object[0]), 13.0f, iK));
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, q8Var2.d(29.0f));
        layoutParams3.topMargin = q8Var2.d(5.0f);
        linearLayout3.addView(textView, layoutParams3);
        linearLayout3.addView(y8Var, new LinearLayout.LayoutParams(-1, q8Var2.d(42.0f)));
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(0, q8Var2.d(126.0f), 1.0f);
        layoutParams4.leftMargin = q8Var2.d(10.0f);
        linearLayout2.addView(linearLayout3, layoutParams4);
        if (z2) {
            View viewB = b(mainActivity, q8Var2, si.a("climate_passenger_zone", new Object[0]), textViewG2, "ptemp-", "ptemp+");
            q8 q8Var3 = q8Var2;
            f = 126.0f;
            LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(0, q8Var3.d(126.0f), 1.0f);
            layoutParams5.leftMargin = q8Var3.d(10.0f);
            linearLayout2.addView(viewB, layoutParams5);
            q8Var = q8Var3;
        } else {
            f = 126.0f;
            q8Var = q8Var2;
        }
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-1, q8Var.d(f));
        layoutParams6.topMargin = q8Var.d(12.0f);
        q8Var.addView(linearLayout2, layoutParams6);
        SharedPreferences sharedPreferences = mainActivity.getSharedPreferences("panel", 0);
        List list = y2.a;
        ArrayList arrayList = new ArrayList(k3.n0(list));
        Iterator it = list.iterator();
        while (true) {
            i = 8;
            if (!it.hasNext()) {
                break;
            }
            String str = (String) it.next();
            String strE = y2.e(str);
            sharedPreferences.getClass();
            List<String> listH0 = i3.H0(i3.J0(i3.K0(y2.c(sharedPreferences, str, q8Var.b))), 8);
            ArrayList arrayList2 = new ArrayList();
            for (String str2 : listH0) {
                x2 x2Var2 = q8Var.c;
                Context context = q8Var.getContext();
                context.getClass();
                String strI = k8.i(context, str2);
                if (strI == null) {
                    String str3 = (String) x2Var2.b.b(str2);
                    Context context2 = q8Var.getContext();
                    context2.getClass();
                    t2Var = new t2(context2, q8Var.a, str3, (View) x2Var2.a.b(str2), false, q8Var.f);
                    t2Var.setOnClickListener(new o8(q8Var, str2, i2));
                    t2Var.setContentDescription(str3);
                    q8Var.r.put(str2, t2Var);
                } else if (!str2.equals("sync") || q8Var.b) {
                    String str4 = str2.startsWith("air:") ? str2 : null;
                    Integer numQ = str4 != null ? bi.q(th.C(str4, "air:")) : null;
                    String strH = k8.h(str2);
                    if (strH == null) {
                        t2Var = null;
                    } else {
                        t2 t2VarH4 = h(q8Var, str2, strI, strH, numQ, 8);
                        if (numQ != null) {
                            q8Var.p.put(numQ, t2VarH4);
                        } else if (str2.startsWith("seat:")) {
                            q8Var.q.put(th.C(str2, "seat:"), t2VarH4);
                        } else {
                            q8Var.o.put(str2, t2VarH4);
                        }
                        t2Var = t2VarH4;
                    }
                } else {
                    t2Var = null;
                }
                yc ycVar = t2Var != null ? new yc(str2, t2Var) : null;
                if (ycVar != null) {
                    arrayList2.add(ycVar);
                }
            }
            arrayList.add(new oi(str, strE, arrayList2));
        }
        ArrayList arrayList3 = new ArrayList();
        int size = arrayList.size();
        int i3 = 0;
        while (i3 < size) {
            Object obj = arrayList.get(i3);
            i3++;
            if (!((Collection) ((oi) obj).c).isEmpty()) {
                arrayList3.add(obj);
            }
        }
        int i4 = 0;
        while (i4 < arrayList3.size()) {
            oi oiVar = (oi) arrayList3.get(i4);
            String str5 = (String) oiVar.a;
            String str6 = (String) oiVar.b;
            List list2 = (List) oiVar.c;
            int i5 = i4 + 1;
            oi oiVar2 = (oi) i3.w0(arrayList3, i5);
            if (!k8.e(str5, "heating")) {
                c = c2;
                TextView textViewC2 = q8Var.c(str6, 15.0f, q8Var.h);
                LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(-1, q8Var.d(28.0f));
                f2 = 8.0f;
                layoutParams7.topMargin = q8Var.d(8.0f);
                q8Var.addView(textViewC2, layoutParams7);
                q8Var.addView(q8Var.f(list2), new LinearLayout.LayoutParams(-1, q8Var.d(88.0f)));
            } else if (!k8.e(oiVar2 != null ? (String) oiVar2.a : null, "seats")) {
                c = c2;
                TextView textViewC3 = q8Var.c(str6, 15.0f, q8Var.h);
                LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(-1, q8Var.d(28.0f));
                f2 = 8.0f;
                layoutParams8.topMargin = q8Var.d(8.0f);
                q8Var.addView(textViewC3, layoutParams8);
                q8Var.addView(q8Var.f(list2), new LinearLayout.LayoutParams(-1, q8Var.d(88.0f)));
            } else if (((List) oiVar2.c).size() + list2.size() <= i) {
                yc[] ycVarArr = {new yc(str6, list2), new yc(oiVar2.b, oiVar2.c)};
                LinearLayout linearLayout4 = new LinearLayout(q8Var.getContext());
                linearLayout4.setOrientation(0);
                int i6 = 0;
                int i7 = 0;
                while (i6 < 2) {
                    yc ycVar2 = ycVarArr[i6];
                    int i8 = i7 + 1;
                    String str7 = (String) ycVar2.a;
                    List list3 = (List) ycVar2.b;
                    LinearLayout linearLayout5 = new LinearLayout(q8Var.getContext());
                    linearLayout5.setOrientation(i2);
                    TextView textViewC4 = q8Var.c(str7, 15.0f, q8Var.h);
                    LinearLayout.LayoutParams layoutParams9 = new LinearLayout.LayoutParams(-1, q8Var.d(28.0f));
                    layoutParams9.topMargin = q8Var.d(f2);
                    linearLayout5.addView(textViewC4, layoutParams9);
                    linearLayout5.addView(q8Var.f(list3), new LinearLayout.LayoutParams(-1, q8Var.d(88.0f)));
                    LinearLayout.LayoutParams layoutParams10 = new LinearLayout.LayoutParams(0, -2, list3.size());
                    if (i7 > 0) {
                        layoutParams10.leftMargin = q8Var.d(18.0f);
                    }
                    linearLayout4.addView(linearLayout5, layoutParams10);
                    i6++;
                    c2 = 0;
                    i7 = i8;
                    f2 = 8.0f;
                    i2 = 1;
                }
                c = c2;
                q8Var.addView(linearLayout4, new LinearLayout.LayoutParams(-1, -2));
                i5 = i4 + 2;
                f2 = 8.0f;
            } else {
                c = c2;
                TextView textViewC5 = q8Var.c(str6, 15.0f, q8Var.h);
                LinearLayout.LayoutParams layoutParams11 = new LinearLayout.LayoutParams(-1, q8Var.d(28.0f));
                f2 = 8.0f;
                layoutParams11.topMargin = q8Var.d(8.0f);
                q8Var.addView(textViewC5, layoutParams11);
                q8Var.addView(q8Var.f(list2), new LinearLayout.LayoutParams(-1, q8Var.d(88.0f)));
            }
            i4 = i5;
            c2 = c;
            i = 8;
            i2 = 1;
        }
    }

    public static final p8 a(q8 q8Var, Context context, String str) {
        p8 p8Var = new p8(q8Var, context, str);
        p8Var.setBackground(new b7(q8Var.a, q8Var.d(20.0f), true));
        p8Var.setClickable(true);
        p8Var.setFocusable(true);
        int i = 0;
        p8Var.setContentDescription(str.endsWith("-") ? si.a("cooler", new Object[0]) : si.a("warmer", new Object[0]));
        p8Var.setOnClickListener(new o8(q8Var, str, i));
        return p8Var;
    }

    public static final LinearLayout b(Context context, q8 q8Var, String str, TextView textView, String str2, String str3) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(16);
        linearLayout.setPadding(q8Var.d(15.0f), q8Var.d(10.0f), q8Var.d(15.0f), q8Var.d(10.0f));
        linearLayout.setBackground(new b7(q8Var.a, q8Var.d(20.0f), true));
        linearLayout.addView(q8Var.c(str, 13.0f, q8Var.h));
        LinearLayout linearLayout2 = new LinearLayout(context);
        linearLayout2.setGravity(16);
        linearLayout2.addView(a(q8Var, context, str2), new LinearLayout.LayoutParams(q8Var.d(48.0f), q8Var.d(48.0f)));
        linearLayout2.addView(textView, new LinearLayout.LayoutParams(0, q8Var.d(60.0f), 1.0f));
        linearLayout2.addView(a(q8Var, context, str3), new LinearLayout.LayoutParams(q8Var.d(48.0f), q8Var.d(48.0f)));
        linearLayout.addView(linearLayout2, new LinearLayout.LayoutParams(-1, q8Var.d(65.0f)));
        return linearLayout;
    }

    public static t2 h(q8 q8Var, String str, String str2, String str3, Integer num, int i) {
        boolean z = (i & 8) == 0;
        if ((i & 16) != 0) {
            num = null;
        }
        Context context = q8Var.getContext();
        context.getClass();
        View viewN = k8.N(context, q8Var.a, str3, num);
        Context context2 = q8Var.getContext();
        context2.getClass();
        t2 t2Var = new t2(context2, q8Var.a, str2, viewN, z, q8Var.f);
        t2Var.setOnClickListener(new o8(q8Var, str, 2));
        t2Var.setContentDescription(str2);
        return t2Var;
    }

    public final TextView c(String str, float f, int i) {
        TextView textView = new TextView(getContext());
        textView.setText(str);
        textView.setTextSize((f * this.e) / textView.getResources().getDisplayMetrics().scaledDensity);
        textView.setTextColor(i);
        textView.setIncludeFontPadding(false);
        textView.setTypeface(z9.c());
        return textView;
    }

    public final int d(float f) {
        int i = (int) (f * this.e);
        if (i < 1) {
            return 1;
        }
        return i;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        motionEvent.getClass();
        y5 y5Var = this.s;
        if (y5Var != null) {
            y5Var.a();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public final void e(bj bjVar) {
        String strA;
        int i;
        Integer numA;
        bjVar.getClass();
        List list = z2.a;
        v2 v2Var = bjVar.i;
        Map map = v2Var.f;
        Map map2 = v2Var.a;
        Integer num = (Integer) map.get(268501248);
        boolean z = (num == null || num.intValue() == 0) ? false : true;
        this.m.b(l8.g(), z);
        Float f = (Float) map2.get(1);
        this.j.setText(f != null ? z2.d(f.floatValue()) : "—");
        Float f2 = (Float) map2.get(4);
        this.k.setText(f2 != null ? z2.d(f2.floatValue()) : "—");
        LinkedHashMap linkedHashMap = this.o;
        t2 t2Var = (t2) linkedHashMap.get("sync");
        if (t2Var != null) {
            Integer num2 = (Integer) map.get(268829952);
            boolean z2 = num2 != null && num2.intValue() == 1;
            l8.h();
            t2Var.b(-10233877, z2);
        }
        Integer numC = z2.c((Integer) map.get(268566784));
        this.n.setLevel(z ? pi.s(numC != null ? numC.intValue() : 0, 0, 8) : 0);
        if (!z) {
            strA = si.a("climate_is_off", new Object[0]);
        } else if (numC == null) {
            strA = si.a("speed_unknown", new Object[0]);
        } else if (numC.intValue() > 8) {
            strA = si.a("automatic_speed", new Object[0]);
        } else {
            List list2 = si.a;
            strA = si.a("speed_0_of_1_tap_the_bars", numC, 8);
        }
        this.l.setText(strA);
        t2 t2Var2 = (t2) linkedHashMap.get("auto");
        if (t2Var2 != null) {
            Integer num3 = (Integer) map.get(268501504);
            boolean z3 = num3 != null && num3.intValue() == 1;
            l8.h();
            t2Var2.b(-10233877, z3);
        }
        t2 t2Var3 = (t2) linkedHashMap.get("ac");
        if (t2Var3 != null) {
            Integer num4 = (Integer) map.get(268501760);
            boolean z4 = num4 != null && num4.intValue() == 1;
            l8.h();
            t2Var3.b(-10233877, z4);
        }
        t2 t2Var4 = (t2) linkedHashMap.get("recirc");
        if (t2Var4 != null) {
            Integer num5 = (Integer) map.get(268632320);
            boolean z5 = num5 != null && num5.intValue() == 268632321;
            l8.h();
            t2Var4.b(-10233877, z5);
        }
        t2 t2Var5 = (t2) linkedHashMap.get("front");
        if (t2Var5 != null) {
            Integer num6 = (Integer) map.get(268697856);
            boolean z6 = num6 != null && num6.intValue() == 1;
            l8.h();
            t2Var5.b(-10233877, z6);
        }
        t2 t2Var6 = (t2) linkedHashMap.get("wiper");
        if (t2Var6 != null) {
            Integer num7 = (Integer) map.get(269747456);
            boolean z7 = num7 != null && num7.intValue() == 1;
            l8.i();
            t2Var6.b(-16777, z7);
        }
        t2 t2Var7 = (t2) linkedHashMap.get("nozzle");
        if (t2Var7 != null) {
            Integer num8 = (Integer) map.get(269747200);
            boolean z8 = num8 != null && num8.intValue() == 1;
            l8.i();
            t2Var7.b(-16777, z8);
        }
        t2 t2Var8 = (t2) linkedHashMap.get("rear");
        if (t2Var8 != null) {
            Integer num9 = (Integer) map.get(268698368);
            boolean z9 = num9 != null && num9.intValue() == 1;
            l8.i();
            t2Var8.b(-16777, z9);
        }
        t2 t2Var9 = (t2) linkedHashMap.get("steering");
        if (t2Var9 != null) {
            Integer numI = z2.i((Integer) map.get(269025536));
            t2Var9.b(Color.rgb(255, 137, 117), (numI != null ? numI.intValue() : 0) > 0);
        }
        for (Map.Entry entry : this.p.entrySet()) {
            int iIntValue = ((Number) entry.getKey()).intValue();
            t2 t2Var10 = (t2) entry.getValue();
            boolean z10 = z && (numA = z2.a((Integer) map.get(268894464))) != null && numA.intValue() == iIntValue;
            l8.h();
            t2Var10.b(-10233877, z10);
        }
        for (Map.Entry entry2 : this.q.entrySet()) {
            String str = (String) entry2.getKey();
            t2 t2Var11 = (t2) entry2.getValue();
            Set set = rf.d;
            rf rfVar = (rf) pi.B().get(str);
            if (rfVar != null) {
                tf tfVar = (tf) bjVar.j.get(str);
                Integer numC2 = rfVar.c(tfVar != null ? tfVar.a : null);
                int iA = rfVar.a();
                View view = t2Var11.c;
                cj cjVar = view instanceof cj ? (cj) view : null;
                if (cjVar != null) {
                    cjVar.setMaxLevel(iA);
                    cjVar.setLevel(numC2);
                    cjVar.setEnabledState(numC2 != null ? Boolean.valueOf(numC2.intValue() > 0) : null);
                }
                boolean z11 = (numC2 != null ? numC2.intValue() : 0) > 0;
                if (rfVar.a == 268763648) {
                    l8.i();
                    i = -16777;
                } else {
                    l8.h();
                    i = -10233877;
                }
                t2Var11.b(i, z11);
            }
        }
        for (Map.Entry entry3 : this.r.entrySet()) {
            String str2 = (String) entry3.getKey();
            t2 t2Var12 = (t2) entry3.getValue();
            x2 x2Var = this.c;
            yc ycVar = (yc) x2Var.c.b(str2);
            t2Var12.b(((Number) ycVar.b).intValue(), ((Boolean) ycVar.a).booleanValue());
            x2Var.d.c(str2, t2Var12.c);
        }
    }

    public final LinearLayout f(List list) {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(0);
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                j3.m0();
                throw null;
            }
            t2 t2Var = (t2) ((yc) obj).b;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, d(88.0f), 1.0f);
            if (i > 0) {
                layoutParams.leftMargin = d(9.0f);
            }
            linearLayout.addView(t2Var, layoutParams);
            i = i2;
        }
        return linearLayout;
    }

    public final TextView g() {
        TextView textView = new TextView(getContext());
        textView.setText("—");
        textView.setGravity(17);
        textView.setTextColor(this.g);
        textView.setTextSize((this.e * 42.0f) / textView.getResources().getDisplayMetrics().scaledDensity);
        textView.setTypeface(z9.c());
        textView.setIncludeFontPadding(false);
        return textView;
    }

    public final y5 getOnInteract() {
        return this.s;
    }

    public final void setOnInteract(y5 y5Var) {
        this.s = y5Var;
    }
}
