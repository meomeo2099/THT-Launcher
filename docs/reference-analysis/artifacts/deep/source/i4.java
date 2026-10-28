package defpackage;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class i4 {
    public final Activity a;
    public final boolean b;
    public final int c;
    public final int d;
    public final int e;

    public i4(Activity activity, boolean z) {
        activity.getClass();
        this.a = activity;
        this.b = z;
        this.c = z ? -1445641 : -15258818;
        this.d = z ? -6442556 : -12231310;
        this.e = z ? -15127491 : -919813;
    }

    public static final String c(String str) {
        String strC = th.C(th.C(th.C(th.C(str, "w:"), "a:"), "v:"), "vehicle_");
        if (str.equals("app") || str.startsWith("s:") || x0.Y0(new String[]{"music", "music2", "music_title", "radio", "nav", "maps", "voice", "app_window"}).contains(strC)) {
            return si.a("music_and_navigation", new Object[0]);
        }
        if (str.equals("dock") || str.equals("image") || x0.Y0(new String[]{"brightness", "volume", "weather", "clock", "bt", "wifi", "wifi_mark", "bt_mark", "folder"}).contains(strC)) {
            return si.a("display_and_connectivity", new Object[0]);
        }
        if (strC.startsWith("seat_heat")) {
            return si.a("heating", new Object[0]);
        }
        if (strC.startsWith("seat_vent")) {
            return si.a("ventilation", new Object[0]);
        }
        return strC.startsWith("drive_") ? si.a("drive_modes", new Object[0]) : si.a("vehicle", new Object[0]);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:106:0x031d  */
    /* JADX WARN: Code duplicated, block: B:137:0x0385  */
    /* JADX WARN: Code duplicated, block: B:147:0x03a3 A[PHI: r2
      0x03a3: PHI (r2v71 java.lang.String) =
      (r2v33 java.lang.String)
      (r2v37 java.lang.String)
      (r2v49 java.lang.String)
      (r2v52 java.lang.String)
      (r2v24 java.lang.String)
      (r2v24 java.lang.String)
     binds: [B:230:0x04bc, B:216:0x048c, B:165:0x03e2, B:155:0x03c0, B:145:0x039f, B:81:0x02cb] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:163:0x03d8  */
    /* JADX WARN: Code duplicated, block: B:173:0x03f8  */
    /* JADX WARN: Code duplicated, block: B:185:0x0421  */
    /* JADX WARN: Code duplicated, block: B:189:0x042d  */
    /* JADX WARN: Code duplicated, block: B:211:0x0478  */
    /* JADX WARN: Code duplicated, block: B:224:0x04a4  */
    /* JADX WARN: Code duplicated, block: B:245:0x04e6  */
    /* JADX WARN: Code duplicated, block: B:253:0x04fe  */
    /* JADX WARN: Code duplicated, block: B:260:0x0515  */
    /* JADX WARN: Code duplicated, block: B:67:0x0299  */
    /* JADX WARN: Code duplicated, block: B:71:0x02a6  */
    /* JADX WARN: Code duplicated, block: B:75:0x02b3  */
    public static final void d(final i4 i4Var, final j6 j6Var, final me meVar, final me meVar2, final GridLayout gridLayout, final LinearLayout linearLayout, ArrayList arrayList, final ArrayList arrayList2) {
        int i;
        String str;
        int size;
        final ArrayList arrayList3 = arrayList;
        int i2 = i4Var.c;
        linearLayout.removeAllViews();
        gridLayout.removeAllViews();
        int i3 = 0;
        ArrayList arrayListE0 = i3.E0(j3.i0(si.a("music_and_navigation", new Object[0]), si.a("display_and_connectivity", new Object[0]), si.a("vehicle", new Object[0]), si.a("drive_modes", new Object[0]), si.a("heating", new Object[0]), si.a("ventilation", new Object[0])), si.a("all", new Object[0]));
        int size2 = arrayListE0.size();
        int i4 = 0;
        while (i4 < size2) {
            int i5 = i4 + 1;
            final String str2 = (String) arrayListE0.get(i4);
            if (k8.e(str2, si.a("all", new Object[i3]))) {
                size = arrayList3.size();
            } else if (arrayList3.isEmpty()) {
                size = i3;
            } else {
                int size3 = arrayList3.size();
                int i6 = i3;
                int i7 = i6;
                while (i7 < size3) {
                    Object obj = arrayList3.get(i7);
                    i7++;
                    if (k8.e(c((String) obj), str2) && (i6 = i6 + 1) < 0) {
                        j3.l0();
                        throw null;
                    }
                }
                size = i6;
            }
            if (size != 0) {
                View viewA = i4Var.a(str2 + " · " + size, k8.e(meVar.b, str2), new y5() { // from class: f4
                    @Override // defpackage.y5
                    public final Object a() {
                        me meVar3 = meVar;
                        meVar3.b = str2;
                        i4.d(i4Var, j6Var, meVar3, meVar2, gridLayout, linearLayout, arrayList3, arrayList2);
                        return ti.a;
                    }
                });
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, i4Var.g(46));
                layoutParams.topMargin = i4Var.g(6);
                layoutParams.rightMargin = i4Var.g(6);
                linearLayout.addView(viewA, layoutParams);
            }
            i4 = i5;
            arrayListE0 = arrayListE0;
            i3 = 0;
        }
        j8 j8VarG0 = j3.g0(arrayList3);
        ArrayList arrayList4 = new ArrayList();
        Iterator it = j8VarG0.iterator();
        while (true) {
            i8 i8Var = (i8) it;
            if (!i8Var.c) {
                break;
            }
            Object next = i8Var.next();
            int iIntValue = ((Number) next).intValue();
            if (k8.e(meVar.b, si.a("all", new Object[0])) || k8.e(c((String) arrayList3.get(iIntValue)), meVar.b)) {
                if (th.v((CharSequence) arrayList2.get(iIntValue), (CharSequence) meVar2.b, true)) {
                    arrayList4.add(next);
                }
            }
        }
        int i8 = 12;
        if (arrayList4.isEmpty()) {
            TextView textViewH = i4Var.h(si.a("nothing_found", new Object[0]), 18.0f, i4Var.d);
            textViewH.setPadding(i4Var.g(12), i4Var.g(24), i4Var.g(12), 0);
            gridLayout.addView(textViewH);
        }
        int size4 = arrayList4.size();
        int i9 = 0;
        while (i9 < size4) {
            Object obj2 = arrayList4.get(i9);
            int i10 = i9 + 1;
            int iIntValue2 = ((Number) obj2).intValue();
            String str3 = (String) arrayList3.get(iIntValue2);
            LinearLayout linearLayoutE = i4Var.e();
            Activity activity = i4Var.a;
            boolean z = i4Var.b;
            linearLayoutE.setGravity(17);
            int i11 = size4;
            ArrayList arrayList5 = arrayList4;
            linearLayoutE.setPadding(i4Var.g(i8), i4Var.g(10), i4Var.g(i8), i4Var.g(10));
            linearLayoutE.setBackground(new RippleDrawable(ColorStateList.valueOf(1082108871), i4Var.i(z ? -14534067 : -1), null));
            linearLayoutE.setFocusable(true);
            List list = si.a;
            linearLayoutE.setContentDescription(si.a("add_0", arrayList2.get(iIntValue2)));
            linearLayoutE.setOnClickListener(new g4(j6Var, str3, 0));
            if (k8.e(str3, "a:steering")) {
                cj cjVar = new cj(activity, "steering");
                cjVar.setNightMode(z);
                linearLayoutE.addView(cjVar, new LinearLayout.LayoutParams(i4Var.g(66), i4Var.g(55)));
                i = i10;
            } else if (ci.t(str3, "v:") || str3.startsWith("vehicle_")) {
                i = i10;
                cj cjVar2 = new cj(activity, th.C(th.C(str3, "v:"), "vehicle_"));
                cjVar2.setNightMode(z);
                linearLayoutE.addView(cjVar2, new LinearLayout.LayoutParams(i4Var.g(66), i4Var.g(55)));
            } else {
                if (!str3.startsWith("s:")) {
                    String strC = th.C(th.C(str3, "w:"), "a:");
                    String str4 = "wifi";
                    i = i10;
                    switch (strC.hashCode()) {
                        case -1785238953:
                            if (!strC.equals("favorites")) {
                                str = "empty";
                            } else {
                                str = "favorite";
                            }
                            break;
                        case -1703270904:
                            if (!strC.equals("defrost_max")) {
                                str = "empty";
                            } else {
                                str = "front";
                            }
                            break;
                        case -1423580684:
                            if (!strC.equals("ac_eco")) {
                                str = "empty";
                            } else {
                                str = "leaf";
                            }
                            break;
                        case -1423573049:
                            if (!strC.equals("ac_max")) {
                                str = "empty";
                            } else {
                                str = "text:A/C|MAX";
                            }
                            break;
                        case -1302320901:
                            if (!strC.equals("degree_driver")) {
                                str = "empty";
                            } else {
                                str = "thermometer";
                            }
                            break;
                        case -1268966290:
                            if (!strC.equals("folder")) {
                                str = "empty";
                            } else {
                                str = "menu";
                            }
                            break;
                        case -1261638272:
                            if (!strC.equals("defrost_rear")) {
                                str = "empty";
                            } else {
                                str = "rear";
                            }
                            break;
                        case -1062807891:
                            if (!strC.equals("music2")) {
                                str = "empty";
                            } else {
                                str = "music";
                            }
                            break;
                        case -934914614:
                            str4 = "recirc";
                            if (!strC.equals("recirc")) {
                                str = "empty";
                            } else {
                                str = str4;
                            }
                            break;
                        case -810883302:
                            if (!strC.equals("volume")) {
                                str = "empty";
                            } else {
                                str = "speaker";
                            }
                            break;
                        case -799216230:
                            if (!strC.equals("fuel_compact")) {
                                str = "empty";
                            } else {
                                str = "pump";
                            }
                            break;
                        case -789256271:
                            if (!strC.equals("passenger_screen")) {
                                str = "empty";
                            } else {
                                str = "screen";
                            }
                            break;
                        case -632085587:
                            str4 = "collapse";
                            if (!strC.equals("collapse")) {
                                str = "empty";
                            } else {
                                str = str4;
                            }
                            break;
                        case -580800793:
                            if (!strC.equals("degree_passenger")) {
                                str = "empty";
                            } else {
                                str = "thermometer";
                            }
                            break;
                        case -345754895:
                            if (!strC.equals("battery_compact")) {
                                str = "empty";
                            } else {
                                str = "batt";
                            }
                            break;
                        case -342035224:
                            if (!strC.equals("camera360")) {
                                str = "empty";
                            } else {
                                str = "screen";
                            }
                            break;
                        case -331239923:
                            if (!strC.equals("battery")) {
                                str = "empty";
                            } else {
                                str = "batt";
                            }
                            break;
                        case -249962272:
                            if (!strC.equals("g_pilot")) {
                                str = "empty";
                            } else {
                                str = "pilot";
                            }
                            break;
                        case -7212268:
                            if (!strC.equals("air_clean")) {
                                str = "empty";
                            } else {
                                str = "fan";
                            }
                            break;
                        case 3106:
                            if (!strC.equals("ac")) {
                                str = "empty";
                            } else {
                                str = "snow";
                            }
                            break;
                        case 3154:
                            if (!strC.equals("bt")) {
                                str = "empty";
                            } else {
                                str = "bt";
                            }
                            break;
                        case 96801:
                            if (!strC.equals("app")) {
                                str = "empty";
                            } else {
                                str = "grid";
                            }
                            break;
                        case 98260:
                            if (!strC.equals("car")) {
                                str = "empty";
                            } else {
                                str = "carfront";
                            }
                            break;
                        case 102570:
                            if (!strC.equals("gps")) {
                                str = "empty";
                            } else {
                                str = "globe";
                            }
                            break;
                        case 108835:
                            if (!strC.equals("nav")) {
                                str = "empty";
                            } else {
                                str = "nav";
                            }
                            break;
                        case 3000946:
                            if (!strC.equals("apps")) {
                                str = "empty";
                            } else {
                                str = "menu";
                            }
                            break;
                        case 3005871:
                            str4 = "auto";
                            if (!strC.equals("auto")) {
                                str = "empty";
                            } else {
                                str = str4;
                            }
                            break;
                        case 3088947:
                            if (!strC.equals("dock")) {
                                str = "empty";
                            } else {
                                str = "menu";
                            }
                            break;
                        case 3154358:
                            if (!strC.equals("fuel")) {
                                str = "empty";
                            } else {
                                str = "pump";
                            }
                            break;
                        case 3208415:
                            str4 = "home";
                            if (!strC.equals("home")) {
                                str = "empty";
                            } else {
                                str = str4;
                            }
                            break;
                        case 3344023:
                            if (!strC.equals("maps")) {
                                str = "empty";
                            } else {
                                str = "nav";
                            }
                            break;
                        case 3556308:
                            if (!strC.equals("temp")) {
                                str = "empty";
                            } else {
                                str = "thermometer";
                            }
                            break;
                        case 3649301:
                            if (!strC.equals("wifi")) {
                                str = "empty";
                            } else {
                                str = str4;
                            }
                            break;
                        case 92973574:
                            if (!strC.equals("anton")) {
                                str = "empty";
                            } else {
                                str = "thermometer";
                            }
                            break;
                        case 94755854:
                            if (!strC.equals("clock")) {
                                str = "empty";
                            } else {
                                str = "clock";
                            }
                            break;
                        case 100313435:
                            if (!strC.equals("image")) {
                                str = "empty";
                            } else {
                                str = "screen";
                            }
                            break;
                        case 104263205:
                            if (!strC.equals("music")) {
                                str = "empty";
                            } else {
                                str = "music";
                            }
                            break;
                        case 108270587:
                            if (!strC.equals("radio")) {
                                str = "empty";
                            } else {
                                str = "music";
                            }
                            break;
                        case 109211285:
                            if (!strC.equals("saver")) {
                                str = "empty";
                            } else {
                                str = "screen";
                            }
                            break;
                        case 109757599:
                            if (!strC.equals("stats")) {
                                str = "empty";
                            } else {
                                str = "lane";
                            }
                            break;
                        case 110327241:
                            if (!strC.equals("theme")) {
                                str = "empty";
                            } else {
                                str = "moon";
                            }
                            break;
                        case 112386354:
                            if (!strC.equals("voice")) {
                                str = "empty";
                            } else {
                                str = "mic";
                            }
                            break;
                        case 193107610:
                            if (!strC.equals("bt_mark")) {
                                str = "empty";
                            } else {
                                str = "bt";
                            }
                            break;
                        case 344200471:
                            if (!strC.equals("automation")) {
                                str = "empty";
                            } else {
                                str = "ambient";
                            }
                            break;
                        case 648162385:
                            if (!strC.equals("brightness")) {
                                str = "empty";
                            } else {
                                str = "sun";
                            }
                            break;
                        case 1122970782:
                            if (!strC.equals("eco_hints")) {
                                str = "empty";
                            } else {
                                str = "leaf";
                            }
                            break;
                        case 1188390954:
                            if (!strC.equals("esc_sport")) {
                                str = "empty";
                            } else {
                                str = "sport";
                            }
                            break;
                        case 1223440372:
                            if (!strC.equals("weather")) {
                                str = "empty";
                            } else {
                                str = "sun";
                            }
                            break;
                        case 1272109551:
                            if (!strC.equals("temp_passenger")) {
                                str = "empty";
                            } else {
                                str = "thermometer";
                            }
                            break;
                        case 1388570574:
                            if (!strC.equals("app_window")) {
                                str = "empty";
                            } else {
                                str = "grid";
                            }
                            break;
                        case 1390681772:
                            if (!strC.equals("roof_slider")) {
                                str = "empty";
                            } else {
                                str = "roofslider";
                            }
                            break;
                        case 1400976823:
                            if (!strC.equals("wifi_mark")) {
                                str = "empty";
                            } else {
                                str = str4;
                            }
                            break;
                        case 1434631203:
                            if (!strC.equals("settings")) {
                                str = "empty";
                            } else {
                                str = "gear";
                            }
                            break;
                        case 1441048056:
                            if (!strC.equals("lane_keep")) {
                                str = "empty";
                            } else {
                                str = "lane";
                            }
                            break;
                        case 1589871550:
                            if (!strC.equals("music_title")) {
                                str = "empty";
                            } else {
                                str = "music";
                            }
                            break;
                        case 1740837476:
                            if (!strC.equals("ac_reminders")) {
                                str = "empty";
                            } else {
                                str = "clock";
                            }
                            break;
                        case 1928970334:
                            if (!strC.equals("park_assist")) {
                                str = "empty";
                            } else {
                                str = "park";
                            }
                            break;
                        default:
                            str = "empty";
                            break;
                    }
                } else {
                    str = "split";
                    i = i10;
                }
                d9 d9Var = new d9(activity, str);
                d9Var.setColor(z ? -5643020 : i2);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(i4Var.g(44), i4Var.g(44));
                layoutParams2.bottomMargin = i4Var.g(8);
                linearLayoutE.addView(d9Var, layoutParams2);
            }
            TextView textViewH2 = i4Var.h((String) arrayList2.get(iIntValue2), 16.0f, i2);
            textViewH2.setGravity(17);
            textViewH2.setMaxLines(2);
            linearLayoutE.addView(textViewH2, new LinearLayout.LayoutParams(-1, i4Var.g(44)));
            GridLayout.LayoutParams layoutParams3 = new GridLayout.LayoutParams();
            layoutParams3.width = 0;
            layoutParams3.height = i4Var.g(130);
            layoutParams3.columnSpec = GridLayout.spec(Integer.MIN_VALUE, 1.0f);
            layoutParams3.setMargins(i4Var.g(4), i4Var.g(4), i4Var.g(4), i4Var.g(4));
            gridLayout.addView(linearLayoutE, layoutParams3);
            size4 = i11;
            arrayList3 = arrayList;
            arrayList4 = arrayList5;
            i9 = i;
            i8 = 12;
        }
    }

    public final Button a(String str, boolean z, y5 y5Var) {
        str.getClass();
        y5Var.getClass();
        Button button = new Button(this.a);
        button.setText(str);
        button.setTextSize(16.0f);
        button.setAllCaps(false);
        button.setMinHeight(g(48));
        button.setMinimumHeight(g(48));
        button.setTextColor(z ? -15321785 : this.c);
        button.setBackground(new RippleDrawable(ColorStateList.valueOf(1082108871), i(z ? -5643020 : this.e), null));
        button.setPadding(g(16), g(8), g(16), g(8));
        button.setOnClickListener(new m2(y5Var, 1));
        return button;
    }

    public final void b(ArrayList arrayList, ArrayList arrayList2, j6 j6Var) {
        LinearLayout linearLayoutE = e();
        me meVar = new me(0);
        meVar.b = si.a("all", new Object[0]);
        me meVar2 = new me(0);
        meVar2.b = "";
        Activity activity = this.a;
        EditText editText = new EditText(activity);
        editText.setHint(si.a("find_a_widget", new Object[0]));
        editText.setTextSize(17.0f);
        editText.setTextColor(this.c);
        editText.setHintTextColor(this.d);
        editText.setSingleLine(true);
        editText.setBackground(i(this.b ? -15720144 : -1));
        editText.setPadding(g(16), 0, g(16), 0);
        linearLayoutE.addView(editText, new LinearLayout.LayoutParams(-1, g(48)));
        LinearLayout linearLayout = new LinearLayout(activity);
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(activity);
        horizontalScrollView.addView(linearLayout);
        horizontalScrollView.setHorizontalScrollBarEnabled(false);
        linearLayoutE.addView(horizontalScrollView, new LinearLayout.LayoutParams(-1, g(58)));
        GridLayout gridLayout = new GridLayout(activity);
        gridLayout.setColumnCount(((float) activity.getResources().getDisplayMetrics().widthPixels) / activity.getResources().getDisplayMetrics().density < 700.0f ? 2 : 3);
        ScrollView scrollView = new ScrollView(activity);
        scrollView.addView(gridLayout);
        scrollView.setFillViewport(false);
        linearLayoutE.addView(scrollView, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        Dialog dialogF = f(si.a("widget_library", new Object[0]), "", linearLayoutE);
        editText.addTextChangedListener(new h4(this, j6Var, meVar2, meVar, gridLayout, linearLayout, arrayList, arrayList2));
        d(this, j6Var, meVar, meVar2, gridLayout, linearLayout, arrayList, arrayList2);
        dialogF.show();
        Window window = dialogF.getWindow();
        if (window != null) {
            window.setSoftInputMode(3);
        }
    }

    public final LinearLayout e() {
        LinearLayout linearLayout = new LinearLayout(this.a);
        linearLayout.setOrientation(1);
        return linearLayout;
    }

    public final Dialog f(String str, String str2, ViewGroup viewGroup) {
        View decorView;
        Window window;
        str.getClass();
        str2.getClass();
        final Dialog dialog = new Dialog(this.a);
        if (pi.c && (window = dialog.getWindow()) != null) {
            window.setType(2038);
        }
        dialog.requestWindowFeature(1);
        LinearLayout linearLayoutE = e();
        linearLayoutE.setPadding(g(22), g(18), g(22), g(18));
        linearLayoutE.setBackground(i(this.e));
        LinearLayout linearLayout = new LinearLayout(this.a);
        linearLayout.setGravity(16);
        TextView textViewH = h(str, 25.0f, this.c);
        textViewH.setTypeface(null, 1);
        linearLayout.addView(textViewH, new LinearLayout.LayoutParams(0, -2, 1.0f));
        Button buttonA = a("✕", false, new w1(dialog, 1));
        buttonA.setContentDescription(si.a("close", new Object[0]));
        linearLayout.addView(buttonA, new LinearLayout.LayoutParams(g(52), g(48)));
        linearLayoutE.addView(linearLayout);
        if (!th.A(str2)) {
            TextView textViewH2 = h(str2, 14.0f, this.d);
            textViewH2.setPadding(0, g(6), 0, g(16));
            linearLayoutE.addView(textViewH2);
        }
        linearLayoutE.addView(viewGroup, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        z9.a(linearLayoutE);
        dialog.setContentView(linearLayoutE);
        Window window2 = dialog.getWindow();
        if (window2 != null) {
            window2.setBackgroundDrawableResource(R.color.transparent);
        }
        Window window3 = dialog.getWindow();
        if (window3 != null && (decorView = window3.getDecorView()) != null) {
            decorView.setSystemUiVisibility(this.a.getWindow().getDecorView().getSystemUiVisibility());
        }
        dialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: e4
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                i4 i4Var = this.a;
                DisplayMetrics displayMetrics = i4Var.a.getResources().getDisplayMetrics();
                Dialog dialog2 = dialog;
                Window window4 = dialog2.getWindow();
                if (window4 != null) {
                    int i = (int) (displayMetrics.widthPixels * 0.92f);
                    int iG = i4Var.g(1040);
                    if (i > iG) {
                        i = iG;
                    }
                    window4.setLayout(i, (int) (displayMetrics.heightPixels * 0.9f));
                }
                Window window5 = dialog2.getWindow();
                if (window5 != null) {
                    window5.setDimAmount(0.65f);
                }
            }
        });
        return dialog;
    }

    public final int g(int i) {
        return (int) (i * this.a.getResources().getDisplayMetrics().density);
    }

    public final TextView h(String str, float f, int i) {
        TextView textView = new TextView(this.a);
        textView.setText(str);
        textView.setTextSize(f);
        textView.setTextColor(i);
        textView.setIncludeFontPadding(false);
        return textView;
    }

    public final GradientDrawable i(int i) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(i);
        gradientDrawable.setCornerRadius(g(18));
        gradientDrawable.setStroke(g(1), 897559495);
        return gradientDrawable;
    }
}
