package defpackage;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class xd {
    public static final List a = j3.i0("com.ecarx.multimedia", "com.ecarx.radio", "ecarx.radio");

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002a, code lost:

        if (r6.y().v(r5.equals("seek_next")) != false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002c, code lost:

        r6.v0(defpackage.si.a("radio_unavailable", new java.lang.Object[0]));
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0014, code lost:

        if (r5.equals("seek_prev") == false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001b, code lost:

        if (r5.equals("seek_next") == false) goto L27;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void a(java.lang.String r5, org.reference.panel.MainActivity r6) {
        /*
            Method dump skipped, instruction units count: 210
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.xd.a(java.lang.String, org.reference.panel.MainActivity):void");
    }

    public static final void b(final MainActivity mainActivity) {
        int i;
        Window window;
        final int iMin = Math.min(mainActivity.r(1040), mainActivity.getResources().getDisplayMetrics().widthPixels - mainActivity.r(24));
        final int iS = pi.s((iMin - mainActivity.r(56)) / mainActivity.r(220), 1, 4);
        Dialog dialog = new Dialog(mainActivity);
        if (pi.c && (window = dialog.getWindow()) != null) {
            window.setType(2038);
        }
        dialog.requestWindowFeature(1);
        int i2 = l8.a;
        final int iJ = l8.j(mainActivity.z());
        final int iK = l8.k(mainActivity.z());
        l8.h();
        l8.i();
        final int iA = l8.a(-16777, mainActivity.z());
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(mainActivity.r(28), mainActivity.r(24), mainActivity.r(28), mainActivity.r(26));
        linearLayout.setBackground(new b7(mainActivity.z(), mainActivity.r(34), false));
        LinearLayout linearLayout2 = new LinearLayout(mainActivity);
        linearLayout2.setGravity(16);
        final ie ieVar = new ie(mainActivity, mainActivity.z(), false);
        ieVar.setCovers(true);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(mainActivity.r(84), mainActivity.r(84));
        layoutParams.rightMargin = mainActivity.r(20);
        linearLayout2.addView(ieVar, layoutParams);
        LinearLayout linearLayout3 = new LinearLayout(mainActivity);
        linearLayout3.setOrientation(1);
        final TextView textViewE = e(mainActivity, "", 46.0f, iJ, true);
        textViewE.setMaxLines(1);
        TextUtils.TruncateAt truncateAt = TextUtils.TruncateAt.END;
        textViewE.setEllipsize(truncateAt);
        final TextView textViewE2 = e(mainActivity, "", 21.0f, iK, false);
        textViewE2.setMaxLines(1);
        textViewE2.setEllipsize(truncateAt);
        linearLayout3.addView(textViewE);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.topMargin = mainActivity.r(8);
        linearLayout3.addView(textViewE2, layoutParams2);
        linearLayout2.addView(linearLayout3, new LinearLayout.LayoutParams(0, -2, 1.0f));
        TextView textViewC = c(iJ, mainActivity, -10233877, "✕", 26.0f);
        textViewC.setPadding(0, 0, 0, 0);
        textViewC.setContentDescription(si.a("close", new Object[0]));
        textViewC.setOnClickListener(new q5(dialog, 1));
        linearLayout2.addView(textViewC, new LinearLayout.LayoutParams(mainActivity.r(68), mainActivity.r(64)));
        linearLayout.addView(linearLayout2);
        LinearLayout linearLayout4 = new LinearLayout(mainActivity);
        linearLayout4.setGravity(16);
        final me meVar = new me(0);
        final me meVar2 = new me(0);
        final nd ndVar = new nd(mainActivity, mainActivity.z(), "play", true);
        ndVar.setOnClickListener(new sd(meVar2, mainActivity, 1));
        final nd ndVar2 = new nd(mainActivity, mainActivity.z(), "prev", false);
        ndVar2.setOnClickListener(new sd(meVar2, mainActivity, 2));
        ndVar2.setOnLongClickListener(new wd(meVar2, mainActivity, 0));
        final nd ndVar3 = new nd(mainActivity, mainActivity.z(), "next", false);
        ndVar3.setOnClickListener(new sd(meVar2, mainActivity, 3));
        ndVar3.setOnLongClickListener(new wd(meVar2, mainActivity, 1));
        linearLayout4.setGravity(17);
        linearLayout4.addView(ndVar2, new LinearLayout.LayoutParams(mainActivity.r(84), mainActivity.r(84)));
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(mainActivity.r(104), mainActivity.r(104));
        layoutParams3.leftMargin = mainActivity.r(36);
        layoutParams3.rightMargin = mainActivity.r(36);
        linearLayout4.addView(ndVar, layoutParams3);
        linearLayout4.addView(ndVar3, new LinearLayout.LayoutParams(mainActivity.r(84), mainActivity.r(84)));
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams4.topMargin = mainActivity.r(20);
        linearLayout.addView(linearLayout4, layoutParams4);
        final TextView textViewC2 = c(iJ, mainActivity, -10233877, "", 20.0f);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-1, mainActivity.r(64));
        layoutParams5.topMargin = mainActivity.r(16);
        linearLayout.addView(textViewC2, layoutParams5);
        final je jeVar = new je();
        LinearLayout linearLayout5 = new LinearLayout(mainActivity);
        linearLayout5.setGravity(16);
        final TextView textViewC3 = c(iJ, mainActivity, -10233877, "", 20.0f);
        final TextView textViewC4 = c(iJ, mainActivity, -10233877, "", 20.0f);
        linearLayout5.addView(textViewC4, new LinearLayout.LayoutParams(0, mainActivity.r(64), 1.0f));
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(0, mainActivity.r(64), 1.0f);
        layoutParams6.leftMargin = mainActivity.r(10);
        linearLayout5.addView(textViewC3, layoutParams6);
        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams7.topMargin = mainActivity.r(20);
        layoutParams7.bottomMargin = mainActivity.r(12);
        linearLayout.addView(linearLayout5, layoutParams7);
        final GridLayout gridLayout = new GridLayout(mainActivity);
        gridLayout.setColumnCount(iS);
        linearLayout.addView(gridLayout, new LinearLayout.LayoutParams(-1, -2));
        final TextView textViewE3 = e(mainActivity, "", 19.0f, iK, false);
        textViewE3.setPadding(mainActivity.r(4), mainActivity.r(6), mainActivity.r(4), mainActivity.r(6));
        linearLayout.addView(textViewE3);
        TextView textViewE4 = e(mainActivity, si.a("radio_switch_favorites", new Object[0]), 16.0f, iK, false);
        textViewE4.setGravity(17);
        textViewE4.setPadding(0, mainActivity.r(8), 0, mainActivity.r(8));
        linearLayout.addView(textViewE4);
        LinearLayout linearLayout6 = new LinearLayout(mainActivity);
        linearLayout6.setGravity(16);
        final TextView textViewC5 = c(iJ, mainActivity, -10233877, si.a("radio_scan", new Object[0]), 20.0f);
        linearLayout6.addView(textViewC5, new LinearLayout.LayoutParams(0, mainActivity.r(68), 1.0f));
        TextView textViewC6 = c(iJ, mainActivity, -10233877, si.a("radio_open", new Object[0]), 20.0f);
        textViewC6.setOnClickListener(new qb(meVar2, dialog, mainActivity));
        LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(0, mainActivity.r(68), 1.0f);
        layoutParams8.leftMargin = mainActivity.r(12);
        linearLayout6.addView(textViewC6, layoutParams8);
        LinearLayout.LayoutParams layoutParams9 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams9.topMargin = mainActivity.r(6);
        linearLayout.addView(linearLayout6, layoutParams9);
        meVar2.b = new qd(mainActivity, new a(10, textViewC5), new b3(textViewC5, mainActivity, jeVar, meVar));
        final int i3 = -10233877;
        textViewC5.setOnClickListener(new View.OnClickListener() { // from class: rd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                me meVar3 = meVar2;
                Object obj = meVar3.b;
                if (obj == null) {
                    k8.H0("scan");
                    throw null;
                }
                qd qdVar = (qd) obj;
                boolean z = qdVar.k;
                if (z) {
                    if (z) {
                        qdVar.a(true);
                        return;
                    }
                    return;
                }
                MainActivity mainActivity2 = mainActivity;
                if (mainActivity2.y().s() == null) {
                    mainActivity2.v0(si.a("radio_scan_start_first", new Object[0]));
                    xd.g(mainActivity2);
                    return;
                }
                List list = si.a;
                String strA = si.a("radio_scanning_stop_0", 0);
                TextView textView = textViewC5;
                textView.setText(strA);
                Drawable background = textView.getBackground();
                background.getClass();
                ((b7) background).a(i3, true);
                Object obj2 = meVar3.b;
                if (obj2 == null) {
                    k8.H0("scan");
                    throw null;
                }
                qd qdVar2 = (qd) obj2;
                lb lbVarS = qdVar2.d.s();
                if (lbVarS != null) {
                    qdVar2.f.clear();
                    qdVar2.k = true;
                    qdVar2.g = lbVarS;
                    qdVar2.j = SystemClock.uptimeMillis();
                    qdVar2.c(lbVarS);
                    qdVar2.h = qd.b(lbVarS);
                    qdVar2.d();
                }
                Object obj3 = meVar.b;
                if (obj3 != null) {
                    ((y5) obj3).a();
                } else {
                    k8.H0("refresh");
                    throw null;
                }
            }
        });
        y5 y5Var = new y5() { // from class: vd
            /* JADX WARN: Code duplicated, block: B:139:0x0285  */
            /* JADX WARN: Code duplicated, block: B:166:0x039f A[EDGE_INSN: B:166:0x039f->B:176:0x03dc BREAK  A[LOOP:5: B:168:0x03ab->B:175:0x03d5], PHI: r5 r7 r12
              0x039f: PHI (r5v24 java.lang.String) = (r5v1 java.lang.String), (r5v2 java.lang.String) binds: [B:165:0x039d, B:252:0x039f] A[DONT_GENERATE, DONT_INLINE]
              0x039f: PHI (r7v41 java.util.List) = (r7v26 java.util.List), (r7v27 java.util.List) binds: [B:165:0x039d, B:252:0x039f] A[DONT_GENERATE, DONT_INLINE]
              0x039f: PHI (r12v12 java.lang.String) = (r12v6 java.lang.String), (r12v7 java.lang.String) binds: [B:165:0x039d, B:252:0x039f] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Code duplicated, block: B:167:0x03a7  */
            /* JADX WARN: Code duplicated, block: B:170:0x03b1  */
            /* JADX WARN: Code duplicated, block: B:178:0x040f  */
            /* JADX WARN: Code duplicated, block: B:179:0x0418  */
            /* JADX WARN: Code duplicated, block: B:183:0x0420  */
            /* JADX WARN: Code duplicated, block: B:186:0x042b  */
            /* JADX WARN: Code duplicated, block: B:188:0x043a  */
            /* JADX WARN: Code duplicated, block: B:190:0x0440  */
            /* JADX WARN: Code duplicated, block: B:191:0x0444  */
            /* JADX WARN: Code duplicated, block: B:194:0x047d  */
            /* JADX WARN: Code duplicated, block: B:197:0x049d  */
            /* JADX WARN: Code duplicated, block: B:198:0x049f  */
            /* JADX WARN: Code duplicated, block: B:202:0x04a6  */
            /* JADX WARN: Code duplicated, block: B:205:0x04d0  */
            /* JADX WARN: Code duplicated, block: B:206:0x04d3  */
            /* JADX WARN: Code duplicated, block: B:208:0x04d7  */
            /* JADX WARN: Code duplicated, block: B:211:0x04f0  */
            /* JADX WARN: Code duplicated, block: B:212:0x04f3  */
            /* JADX WARN: Code duplicated, block: B:248:0x0571 A[SYNTHETIC] */
            /* JADX WARN: Code duplicated, block: B:249:0x056d A[SYNTHETIC] */
            /* JADX WARN: Code duplicated, block: B:252:0x039f A[SYNTHETIC] */
            /* JADX WARN: Code duplicated, block: B:54:0x0112  */
            @Override // defpackage.y5
            public final Object a() {
                String strA;
                String strA2;
                boolean z;
                Iterator it;
                boolean z2;
                String str;
                lb lbVar;
                String strD;
                List listL0;
                Iterator it2;
                String str2;
                kd kdVar;
                List list;
                String str3;
                boolean z3;
                int iB;
                FrameLayout frameLayout;
                boolean z4;
                String str4;
                String strZ;
                boolean z5;
                String strD2;
                Object obj;
                Object obj2;
                float f;
                String strZ2;
                String str5;
                int i4;
                String str6;
                String str7;
                String str8;
                MainActivity mainActivity2 = mainActivity;
                lb lbVarS = mainActivity2.y().s();
                String str9 = "radio_favorites";
                if (lbVarS != null) {
                    String str10 = lbVarS.b;
                    if (lbVarS.c() && !th.A(str10)) {
                        ArrayList arrayList = new ArrayList(k8.l0(mainActivity2, "radio_stations"));
                        int size = arrayList.size();
                        int i5 = 0;
                        int i6 = 0;
                        while (true) {
                            if (i6 >= size) {
                                i5 = -1;
                                break;
                            }
                            Object obj3 = arrayList.get(i6);
                            i6++;
                            kd kdVar2 = (kd) obj3;
                            if (kdVar2.b().equals(lbVarS.a()) && kdVar2.c(str10)) {
                                break;
                            }
                            i5++;
                        }
                        String string = th.J(lbVarS.c).toString();
                        if (i5 < 0) {
                            arrayList.add(new kd(str10, string, lbVarS.a));
                        } else if (!th.A(string) && !k8.e(((kd) arrayList.get(i5)).b, string)) {
                            arrayList.set(i5, kd.a((kd) arrayList.get(i5), string));
                        }
                        k8.P0(mainActivity2, "radio_stations", k8.x0(arrayList));
                        List<kd> listL1 = k8.l0(mainActivity2, "radio_favorites");
                        ArrayList arrayList2 = new ArrayList(k3.n0(listL1));
                        for (kd kdVarA : listL1) {
                            if (kdVarA.b().equals(lbVarS.a()) && kdVarA.c(str10) && !th.A(string)) {
                                kdVarA = kd.a(kdVarA, string);
                            }
                            arrayList2.add(kdVarA);
                        }
                        if (!arrayList2.equals(k8.l0(mainActivity2, "radio_favorites"))) {
                            k8.r0(mainActivity2, arrayList2);
                        }
                    }
                }
                ie.f(ieVar, lbVarS, mainActivity2.y().r(lbVarS), 4);
                String string2 = (lbVarS == null || (str8 = lbVarS.c) == null) ? null : th.J(str8).toString();
                if (string2 == null) {
                    string2 = "";
                }
                if (lbVarS != null) {
                    strA = lbVarS.b();
                    if (th.A(strA)) {
                        strA = null;
                    }
                    if (strA == null) {
                        strA = si.a("radio", new Object[0]);
                    }
                } else {
                    strA = si.a("radio", new Object[0]);
                }
                textViewE.setText(strA);
                String str11 = " · ";
                if (lbVarS == null) {
                    strA2 = si.a("radio_tap_to_start", new Object[0]);
                } else {
                    boolean z6 = lbVarS.e;
                    if (th.A(string2)) {
                        List list2 = si.a;
                        strA2 = si.a(z6 ? "playing" : "paused", new Object[0]);
                    } else {
                        List list3 = si.a;
                        strA2 = c.d(string2, " · ", si.a(z6 ? "playing" : "paused", new Object[0]));
                    }
                }
                textViewE2.setText(strA2);
                int i7 = l8.a;
                boolean z7 = mainActivity2.z();
                me meVar3 = meVar2;
                Object obj4 = meVar3.b;
                String str12 = "scan";
                if (obj4 == null) {
                    k8.H0("scan");
                    throw null;
                }
                boolean z8 = ((qd) obj4).k;
                int i8 = i3;
                textViewC5.setTextColor(l8.b(z7, z8, i8));
                final int i9 = 1;
                boolean z9 = lbVarS != null && lbVarS.e;
                nd ndVar4 = ndVar;
                ndVar4.g = z9;
                if (k8.e(ndVar4.b, "play")) {
                    ndVar4.setContentDescription(si.a(z9 ? "pause" : "play", new Object[0]));
                }
                ndVar4.invalidate();
                for (nd ndVar5 : j3.i0(ndVar2, ndVar3)) {
                    Object obj5 = meVar3.b;
                    if (obj5 == null) {
                        k8.H0("scan");
                        throw null;
                    }
                    ndVar5.setEnabled((((qd) obj5).k || !mainActivity2.y().n() || k8.l0(mainActivity2, "radio_favorites").isEmpty()) ? false : true);
                    ndVar5.setAlpha(ndVar5.isEnabled() ? 1.0f : 0.35f);
                }
                Object obj6 = meVar3.b;
                if (obj6 == null) {
                    k8.H0("scan");
                    throw null;
                }
                ndVar4.setEnabled(!((qd) obj6).k);
                ndVar4.setAlpha(ndVar4.isEnabled() ? 1.0f : 0.35f);
                boolean z10 = lbVarS != null && k8.v(mainActivity2, lbVarS);
                String str13 = z10 ? "★  " : "☆  ";
                List list4 = si.a;
                String str14 = "radio_to_favorites";
                String str15 = str13 + si.a(z10 ? "radio_in_favorites" : "radio_to_favorites", new Object[0]);
                TextView textView = textViewC2;
                textView.setText(str15);
                int i10 = iA;
                textView.setTextColor(z10 ? i10 : iJ);
                Drawable background = textView.getBackground();
                background.getClass();
                l8.i();
                ((b7) background).a(-16777, z10);
                String str16 = lbVarS != null ? lbVarS.b : null;
                if (str16 == null || th.A(str16)) {
                    z = false;
                } else {
                    Object obj7 = meVar3.b;
                    if (obj7 == null) {
                        k8.H0("scan");
                        throw null;
                    }
                    if (((qd) obj7).k) {
                        z = false;
                    } else {
                        z = true;
                    }
                }
                textView.setEnabled(z);
                textView.setAlpha(textView.isEnabled() ? 1.0f : 0.45f);
                final me meVar4 = meVar;
                textView.setOnClickListener(new sd(mainActivity2, meVar4));
                List listL2 = k8.l0(mainActivity2, "radio_favorites");
                List listL3 = k8.l0(mainActivity2, "radio_stations");
                String strA3 = si.a("radio_tab_all_0", Integer.valueOf(listL3.size()));
                TextView textView2 = textViewC3;
                textView2.setText(strA3);
                String strA4 = si.a("radio_tab_favorites_0", Integer.valueOf(listL2.size()));
                TextView textView3 = textViewC4;
                textView3.setText(strA4);
                final je jeVar2 = jeVar;
                Iterator it3 = j3.i0(new yc(textView2, Boolean.valueOf(jeVar2.a)), new yc(textView3, Boolean.valueOf(!jeVar2.a))).iterator();
                while (it3.hasNext()) {
                    yc ycVar = (yc) it3.next();
                    TextView textView4 = (TextView) ycVar.a;
                    boolean zBooleanValue = ((Boolean) ycVar.b).booleanValue();
                    Drawable background2 = textView4.getBackground();
                    background2.getClass();
                    Iterator it4 = it3;
                    ((b7) background2).a(i8, zBooleanValue);
                    int i11 = l8.a;
                    textView4.setTextColor(l8.b(mainActivity2.z(), zBooleanValue, i8));
                    it3 = it4;
                }
                final int i12 = 0;
                textView2.setOnClickListener(new View.OnClickListener() { // from class: td
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        int i13 = i12;
                        me meVar5 = meVar4;
                        je jeVar3 = jeVar2;
                        switch (i13) {
                            case 0:
                                jeVar3.a = true;
                                Object obj8 = meVar5.b;
                                if (obj8 != null) {
                                    ((y5) obj8).a();
                                    return;
                                } else {
                                    k8.H0("refresh");
                                    throw null;
                                }
                            default:
                                jeVar3.a = false;
                                Object obj9 = meVar5.b;
                                if (obj9 != null) {
                                    ((y5) obj9).a();
                                    return;
                                } else {
                                    k8.H0("refresh");
                                    throw null;
                                }
                        }
                    }
                });
                textView3.setOnClickListener(new View.OnClickListener() { // from class: td
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        int i13 = i9;
                        me meVar5 = meVar4;
                        je jeVar3 = jeVar2;
                        switch (i13) {
                            case 0:
                                jeVar3.a = true;
                                Object obj8 = meVar5.b;
                                if (obj8 != null) {
                                    ((y5) obj8).a();
                                    return;
                                } else {
                                    k8.H0("refresh");
                                    throw null;
                                }
                            default:
                                jeVar3.a = false;
                                Object obj9 = meVar5.b;
                                if (obj9 != null) {
                                    ((y5) obj9).a();
                                    return;
                                } else {
                                    k8.H0("refresh");
                                    throw null;
                                }
                        }
                    }
                });
                List list5 = jeVar2.a ? listL3 : listL2;
                GridLayout gridLayout2 = gridLayout;
                gridLayout2.removeAllViews();
                Iterator it5 = list5.iterator();
                while (it5.hasNext()) {
                    kd kdVar3 = (kd) it5.next();
                    if (lbVarS != null) {
                        it = it5;
                        if (lbVarS.a().equals(kdVar3.b()) && kdVar3.c(lbVarS.b)) {
                            z2 = true;
                        }
                        kdVar3.getClass();
                        str = kdVar3.b;
                        lbVar = lbVarS;
                        strD = kdVar3.a;
                        listL0 = k8.l0(mainActivity2, str9);
                        if (listL0.isEmpty()) {
                            str2 = str9;
                            list = list5;
                            str3 = str12;
                            z3 = false;
                            break;
                        }
                        it2 = listL0.iterator();
                        while (true) {
                            if (it2.hasNext()) {
                                str2 = str9;
                                list = list5;
                                str3 = str12;
                                z3 = false;
                                break;
                            }
                            str2 = str9;
                            kdVar = (kd) it2.next();
                            list = list5;
                            str3 = str12;
                            if (!kdVar.b().equals(kdVar3.b()) && kdVar.c(strD)) {
                                z3 = true;
                                break;
                            }
                            list5 = list;
                            str9 = str2;
                            str12 = str3;
                        }
                        int i13 = l8.a;
                        iB = l8.b(mainActivity2.z(), z2, i8);
                        frameLayout = new FrameLayout(mainActivity2);
                        z4 = z3;
                        str4 = str14;
                        frameLayout.setBackground(xd.d(i8, mainActivity2, 16, z2, 16));
                        frameLayout.setClickable(true);
                        frameLayout.setFocusable(true);
                        Set set = nb.l;
                        strZ = pi.z(kdVar3.b(), strD);
                        z5 = z2;
                        if (strZ != null) {
                            strD2 = c.d(kdVar3.b(), " ", strZ);
                        } else {
                            strD2 = strD;
                        }
                        if (!th.A(str)) {
                            strD2 = c.d(str, str11, strD2);
                        }
                        frameLayout.setContentDescription(strD2);
                        obj = meVar3.b;
                        if (obj != null) {
                            k8.H0(str3);
                            throw null;
                        }
                        frameLayout.setEnabled(!((qd) obj).k);
                        obj2 = meVar3.b;
                        if (obj2 != null) {
                            k8.H0(str3);
                            throw null;
                        }
                        if (((qd) obj2).k) {
                            f = 0.45f;
                        } else {
                            f = 1.0f;
                        }
                        frameLayout.setAlpha(f);
                        LinearLayout linearLayout7 = new LinearLayout(mainActivity2);
                        linearLayout7.setOrientation(1);
                        String str17 = str11;
                        int i14 = i8;
                        linearLayout7.setPadding(mainActivity2.r(20), mainActivity2.r(16), mainActivity2.r(72), mainActivity2.r(14));
                        strZ2 = pi.z(kdVar3.b(), strD);
                        if (strZ2 != null) {
                            strD = c.d(kdVar3.b(), " ", strZ2);
                        }
                        TextView textViewE5 = xd.e(mainActivity2, strD, 25.0f, iB, true);
                        textViewE5.setMaxLines(1);
                        TextUtils.TruncateAt truncateAt2 = TextUtils.TruncateAt.END;
                        textViewE5.setEllipsize(truncateAt2);
                        linearLayout7.addView(textViewE5);
                        if (th.A(str)) {
                            str5 = " ";
                        } else {
                            str5 = str;
                        }
                        i4 = iK;
                        if (!z5) {
                            iB = i4;
                        }
                        TextView textViewE6 = xd.e(mainActivity2, str5, 17.0f, iB, false);
                        textViewE6.setMaxLines(1);
                        textViewE6.setEllipsize(truncateAt2);
                        LinearLayout.LayoutParams layoutParams10 = new LinearLayout.LayoutParams(-1, -2);
                        layoutParams10.topMargin = mainActivity2.r(7);
                        linearLayout7.addView(textViewE6, layoutParams10);
                        frameLayout.addView(linearLayout7, new FrameLayout.LayoutParams(-1, -1));
                        if (z4) {
                            str6 = "★";
                        } else {
                            str6 = "☆";
                        }
                        if (z4) {
                            i4 = i10;
                        }
                        TextView textViewE7 = xd.e(mainActivity2, str6, 30.0f, i4, false);
                        textViewE7.setGravity(17);
                        textViewE7.setClickable(true);
                        textViewE7.setFocusable(true);
                        List list6 = si.a;
                        if (z4) {
                            str7 = "radio_remove_favorite";
                        } else {
                            str7 = str4;
                        }
                        textViewE7.setContentDescription(si.a(str7, new Object[0]));
                        textViewE7.setOnClickListener(new ud(mainActivity2, kdVar3, meVar4));
                        frameLayout.addView(textViewE7, new FrameLayout.LayoutParams(mainActivity2.r(64), mainActivity2.r(64), 8388661));
                        frameLayout.setOnClickListener(new ud(meVar3, mainActivity2, kdVar3));
                        GridLayout.LayoutParams layoutParams11 = new GridLayout.LayoutParams();
                        layoutParams11.width = ((iMin - mainActivity2.r(56)) / iS) - mainActivity2.r(10);
                        layoutParams11.height = mainActivity2.r(108);
                        layoutParams11.setMargins(mainActivity2.r(5), 0, mainActivity2.r(5), mainActivity2.r(12));
                        gridLayout2.addView(frameLayout, layoutParams11);
                        it5 = it;
                        lbVarS = lbVar;
                        list5 = list;
                        str9 = str2;
                        str12 = str3;
                        str14 = str4;
                        str11 = str17;
                        i8 = i14;
                    } else {
                        it = it5;
                    }
                    z2 = false;
                    kdVar3.getClass();
                    str = kdVar3.b;
                    lbVar = lbVarS;
                    strD = kdVar3.a;
                    listL0 = k8.l0(mainActivity2, str9);
                    if (listL0.isEmpty()) {
                        str2 = str9;
                        list = list5;
                        str3 = str12;
                        z3 = false;
                        break;
                    }
                    it2 = listL0.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            str2 = str9;
                            list = list5;
                            str3 = str12;
                            z3 = false;
                            break;
                        }
                        str2 = str9;
                        kdVar = (kd) it2.next();
                        list = list5;
                        str3 = str12;
                        if (!kdVar.b().equals(kdVar3.b())) {
                        }
                        list5 = list;
                        str9 = str2;
                        str12 = str3;
                    }
                    int i15 = l8.a;
                    iB = l8.b(mainActivity2.z(), z2, i8);
                    frameLayout = new FrameLayout(mainActivity2);
                    z4 = z3;
                    str4 = str14;
                    frameLayout.setBackground(xd.d(i8, mainActivity2, 16, z2, 16));
                    frameLayout.setClickable(true);
                    frameLayout.setFocusable(true);
                    Set set2 = nb.l;
                    strZ = pi.z(kdVar3.b(), strD);
                    z5 = z2;
                    if (strZ != null) {
                        strD2 = c.d(kdVar3.b(), " ", strZ);
                    } else {
                        strD2 = strD;
                    }
                    if (!th.A(str)) {
                        strD2 = c.d(str, str11, strD2);
                    }
                    frameLayout.setContentDescription(strD2);
                    obj = meVar3.b;
                    if (obj != null) {
                        k8.H0(str3);
                        throw null;
                    }
                    frameLayout.setEnabled(!((qd) obj).k);
                    obj2 = meVar3.b;
                    if (obj2 != null) {
                        k8.H0(str3);
                        throw null;
                    }
                    if (((qd) obj2).k) {
                        f = 0.45f;
                    } else {
                        f = 1.0f;
                    }
                    frameLayout.setAlpha(f);
                    LinearLayout linearLayout8 = new LinearLayout(mainActivity2);
                    linearLayout8.setOrientation(1);
                    String str18 = str11;
                    int i16 = i8;
                    linearLayout8.setPadding(mainActivity2.r(20), mainActivity2.r(16), mainActivity2.r(72), mainActivity2.r(14));
                    strZ2 = pi.z(kdVar3.b(), strD);
                    if (strZ2 != null) {
                        strD = c.d(kdVar3.b(), " ", strZ2);
                    }
                    TextView textViewE8 = xd.e(mainActivity2, strD, 25.0f, iB, true);
                    textViewE8.setMaxLines(1);
                    TextUtils.TruncateAt truncateAt3 = TextUtils.TruncateAt.END;
                    textViewE8.setEllipsize(truncateAt3);
                    linearLayout8.addView(textViewE8);
                    if (th.A(str)) {
                        str5 = " ";
                    } else {
                        str5 = str;
                    }
                    i4 = iK;
                    if (!z5) {
                        iB = i4;
                    }
                    TextView textViewE9 = xd.e(mainActivity2, str5, 17.0f, iB, false);
                    textViewE9.setMaxLines(1);
                    textViewE9.setEllipsize(truncateAt3);
                    LinearLayout.LayoutParams layoutParams12 = new LinearLayout.LayoutParams(-1, -2);
                    layoutParams12.topMargin = mainActivity2.r(7);
                    linearLayout8.addView(textViewE9, layoutParams12);
                    frameLayout.addView(linearLayout8, new FrameLayout.LayoutParams(-1, -1));
                    if (z4) {
                        str6 = "★";
                    } else {
                        str6 = "☆";
                    }
                    if (z4) {
                        i4 = i10;
                    }
                    TextView textViewE10 = xd.e(mainActivity2, str6, 30.0f, i4, false);
                    textViewE10.setGravity(17);
                    textViewE10.setClickable(true);
                    textViewE10.setFocusable(true);
                    List list7 = si.a;
                    if (z4) {
                        str7 = "radio_remove_favorite";
                    } else {
                        str7 = str4;
                    }
                    textViewE10.setContentDescription(si.a(str7, new Object[0]));
                    textViewE10.setOnClickListener(new ud(mainActivity2, kdVar3, meVar4));
                    frameLayout.addView(textViewE10, new FrameLayout.LayoutParams(mainActivity2.r(64), mainActivity2.r(64), 8388661));
                    frameLayout.setOnClickListener(new ud(meVar3, mainActivity2, kdVar3));
                    GridLayout.LayoutParams layoutParams13 = new GridLayout.LayoutParams();
                    layoutParams13.width = ((iMin - mainActivity2.r(56)) / iS) - mainActivity2.r(10);
                    layoutParams13.height = mainActivity2.r(108);
                    layoutParams13.setMargins(mainActivity2.r(5), 0, mainActivity2.r(5), mainActivity2.r(12));
                    gridLayout2.addView(frameLayout, layoutParams13);
                    it5 = it;
                    lbVarS = lbVar;
                    list5 = list;
                    str9 = str2;
                    str12 = str3;
                    str14 = str4;
                    str11 = str18;
                    i8 = i16;
                }
                int i17 = list5.isEmpty() ? 0 : 8;
                TextView textView5 = textViewE3;
                textView5.setVisibility(i17);
                List list8 = si.a;
                textView5.setText(si.a(jeVar2.a ? "radio_all_empty" : "radio_favorites_empty", new Object[0]));
                return ti.a;
            }
        };
        meVar.b = y5Var;
        y5Var.a();
        mainActivity.I = new y1(dialog, meVar, 10);
        dialog.setOnDismissListener(new r5(mainActivity, meVar2, 2));
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.setFillViewport(true);
        scrollView.addView(linearLayout);
        dialog.setContentView(scrollView);
        dialog.setCanceledOnTouchOutside(true);
        Window window2 = dialog.getWindow();
        if (window2 != null) {
            window2.setBackgroundDrawable(new ColorDrawable(0));
            window2.setDimAmount(0.55f);
            i = 8;
            window2.setFlags(8, 8);
            window2.getDecorView().setSystemUiVisibility(mainActivity.getWindow().getDecorView().getSystemUiVisibility());
        } else {
            i = 8;
        }
        dialog.show();
        Window window3 = dialog.getWindow();
        if (window3 != null) {
            window3.setLayout(iMin, Math.min(mainActivity.r(860), mainActivity.getResources().getDisplayMetrics().heightPixels - mainActivity.r(24)));
        }
        Window window4 = dialog.getWindow();
        if (window4 != null) {
            window4.clearFlags(i);
        }
    }

    public static final TextView c(int i, MainActivity mainActivity, int i2, String str, float f) {
        TextView textViewE = e(mainActivity, str, f, i, true);
        textViewE.setGravity(17);
        textViewE.setPadding(mainActivity.r(24), 0, mainActivity.r(24), 0);
        textViewE.setClickable(true);
        textViewE.setFocusable(true);
        textViewE.setBackground(d(i2, mainActivity, 20, false, 24));
        textViewE.setMaxLines(1);
        textViewE.setEllipsize(TextUtils.TruncateAt.END);
        return textViewE;
    }

    public static b7 d(int i, MainActivity mainActivity, int i2, boolean z, int i3) {
        if ((i3 & 8) != 0) {
            z = false;
        }
        b7 b7Var = new b7(mainActivity.z(), mainActivity.r(i2), true);
        b7Var.a(i, z);
        return b7Var;
    }

    public static final TextView e(MainActivity mainActivity, String str, float f, int i, boolean z) {
        TextView textView = new TextView(mainActivity);
        textView.setText(str);
        textView.setTextSize(f);
        textView.setTextColor(i);
        textView.setIncludeFontPadding(false);
        textView.setTypeface(z ? z9.c() : z9.b());
        return textView;
    }

    public static final void f(MainActivity mainActivity, int i) {
        gc gcVarY = mainActivity.y();
        if (gcVarY.n()) {
            p1 p1Var = gcVarY.G;
            Context context = gcVarY.c;
            context.getClass();
            kd kdVarA = p1Var.a(k8.l0(context, "radio_favorites"), gcVarY.s(), i, SystemClock.elapsedRealtime());
            if (kdVarA != null) {
                gcVarY.E(kdVarA);
                return;
            }
        }
        List list = si.a;
        mainActivity.v0(si.a(mainActivity.y().n() ? "radio_favorites_empty" : "radio_unavailable", new Object[0]));
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0075  */
    public static final void g(MainActivity mainActivity) {
        yc ycVar;
        Double dO;
        Integer numValueOf;
        gc gcVarY = mainActivity.y();
        lb lbVarS = gcVarY.s();
        if (!gcVarY.v.c()) {
            if (gcVarY.u.e == null) {
                mainActivity.v0(si.a("radio_unavailable", new Object[0]));
                return;
            }
            int i = 6;
            if (lbVarS != null && lbVarS.e) {
                nb nbVar = gcVarY.u;
                nbVar.d.post(new t0(nbVar, 7, i));
                return;
            }
            nb nbVar2 = gcVarY.u;
            if (lbVarS != null) {
                nbVar2.d.post(new t0(nbVar2, i, i));
                return;
            } else {
                nbVar2.d.post(new kb(nbVar2, 2));
                return;
            }
        }
        fi fiVar = pd.a;
        Context context = gcVarY.c;
        context.getClass();
        pd.b(context);
        if (lbVarS != null && lbVarS.e) {
            gcVarY.v.h();
            pd.m = SystemClock.elapsedRealtime();
            ((Handler) pd.a.a()).post(new k(4));
            return;
        }
        fe feVar = gcVarY.v;
        Context context2 = gcVarY.c;
        context2.getClass();
        Iterator it = k8.l0(context2, "radio_favorites").iterator();
        do {
            ycVar = null;
            if (!it.hasNext()) {
                break;
            }
            kd kdVar = (kd) it.next();
            String strB = kdVar.b();
            String str = kdVar.a;
            str.getClass();
            if (strB.equals("FM") || strB.equals("AM")) {
                Set set = nb.l;
                String strZ = pi.z(strB, str);
                if (strZ == null || (dO = ai.o(strZ)) == null) {
                    numValueOf = null;
                } else {
                    double dDoubleValue = dO.doubleValue();
                    if (!strB.equals("AM")) {
                        dDoubleValue *= 1000.0d;
                    }
                    numValueOf = Integer.valueOf(k8.n0(dDoubleValue));
                }
            } else {
                numValueOf = null;
            }
            if (numValueOf != null) {
                ycVar = new yc(kdVar.b(), Integer.valueOf(numValueOf.intValue()));
            }
        } while (ycVar == null);
        feVar.b.post(new o(feVar, ycVar, 5));
    }
}
