package defpackage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class uh {
    public static boolean a(String str) {
        str.getClass();
        return str.startsWith("desktop_") || str.startsWith("wall_") || str.startsWith("widget_transparency_") || str.startsWith("dock_button_transparency_") || str.startsWith("clock_") || str.startsWith("image_scale_") || x0.Y0(new String[]{"background", "ui_scale", "dock_size", "dock_alignment", "dock_transparency", "icon_set", "menu_labels", "ios_collapsed", "ios_home_v1", "ios_home_v2", "ios_music2_hidden"}).contains(str);
    }

    public static void b(StringBuilder sb, Object obj, j6 j6Var) {
        if (j6Var != null) {
            sb.append((CharSequence) j6Var.b(obj));
            return;
        }
        if (obj == null ? true : obj instanceof CharSequence) {
            sb.append((CharSequence) obj);
        } else if (obj instanceof Character) {
            sb.append(((Character) obj).charValue());
        } else {
            sb.append((CharSequence) obj.toString());
        }
    }

    public static String c(String str, int i, boolean z, boolean z2) {
        str.getClass();
        if (z || z2) {
            return null;
        }
        if (str.equals("knewstar_001")) {
            return "knewstar_" + pi.s(i, 0, 2) + ".png";
        }
        if (d(str).isEmpty()) {
            return null;
        }
        return str + "_" + pi.s(i, 0, d(str).size() - 1) + ".png";
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:23:0x0076  */
    /* JADX WARN: Code duplicated, block: B:26:0x0081  */
    /* JADX WARN: Code duplicated, block: B:29:0x008b  */
    /* JADX WARN: Code duplicated, block: B:38:0x0099 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:39:? A[LOOP:0: B:27:0x0085->B:39:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x003c, code lost:

        if (r5.equals("livan_s6") == false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x004e, code lost:

        if (r5.equals("geely_coolray") == false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x006d, code lost:

        return defpackage.j3.i0(defpackage.si.a("gray", new java.lang.Object[0]), defpackage.si.a("blue", new java.lang.Object[0]), defpackage.si.a("white", new java.lang.Object[0]));
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List d(java.lang.String r5) {
        /*
            Method dump skipped, instruction units count: 236
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.uh.d(java.lang.String):java.util.List");
    }

    public static zi e(String str) {
        Object next;
        Iterator it = h().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!((zi) next).a.equals(str));
        zi ziVar = (zi) next;
        return ziVar == null ? (zi) i3.u0(h()) : ziVar;
    }

    public static final void f(MainActivity mainActivity) {
        mainActivity.getClass();
        bj bjVar = mainActivity.Z;
        float f = mainActivity.B().getFloat("tank_liters", 50.0f);
        int iS = pi.s(mainActivity.B().getInt("fuel_display", 0), 0, 2);
        c.e("close", new Object[0], new AlertDialog.Builder(mainActivity).setTitle(si.a("fuel_widget", new Object[0])).setSingleChoiceItems(new String[]{g(bjVar, f, si.a("kilometers_range", new Object[0]), 0), g(bjVar, f, si.a("percent", new Object[0]), 1), g(bjVar, f, si.a("liters", new Object[0]), 2)}, iS, new z1(mainActivity, 4)).setPositiveButton(si.a("tank_capacity_0_l", Integer.valueOf(Math.round(f))), new z1(mainActivity, 7)).setNeutralButton(si.a("diagnostics", new Object[0]), new z1(mainActivity, 10)), null, mainActivity);
    }

    public static final String g(bj bjVar, float f, String str, int i) {
        String strX = pi.X(bjVar, i, f);
        if (strX == null) {
            strX = si.a("no_data", new Object[0]);
        }
        return c.d(str, " — ", strX);
    }

    public static List h() {
        return j3.i0(new zi("knewstar_001", "Knewstar", "001"), new zi("livan_s6", "Livan", "S6 Pro"), new zi("livan_x6", "Livan", "X6 Pro"), new zi("geely_monjaro", "Geely", "Monjaro"), new zi("geely_tugella", "Geely", "Tugella"), new zi("geely_coolray", "Geely", "Coolray"), new zi("geely_coolray_new", "Geely", "Coolray (2026)"), new zi("geely_emgrand", "Geely", "Emgrand"), new zi("belgee_x50", "Belgee", "X50"), new zi("belgee_x70", "Belgee", "X70"), new zi("belgee_s50", "Belgee", "S50"), new zi("custom", si.a("other_brand", new Object[0]), si.a("my_vehicle", new Object[0])));
    }

    public static final void i(final MainActivity mainActivity) {
        String strA;
        mainActivity.getClass();
        bj bjVar = mainActivity.Z;
        LinearLayout linearLayoutL = mainActivity.l();
        linearLayoutL.setPadding(mainActivity.r(18), mainActivity.r(12), mainActivity.r(18), mainActivity.r(12));
        int i = 13;
        final lg lgVar = new lg(mainActivity, i);
        boolean zEquals = mainActivity.E().a.equals("geely_monjaro");
        Boolean bool = bjVar.e;
        final int i2 = 0;
        if (k8.e(bool, Boolean.TRUE)) {
            strA = si.a("ambient_lighting_on", new Object[0]);
        } else if (k8.e(bool, Boolean.FALSE)) {
            strA = si.a("ambient_lighting_off", new Object[0]);
        } else {
            if (bool != null) {
                throw new vc();
            }
            strA = si.a("state_unknown", new Object[0]);
        }
        linearLayoutL.addView(mainActivity.u0(strA, 23.0f, mainActivity.o));
        linearLayoutL.addView(MainActivity.i(mainActivity, si.a("turn_on_327", new Object[0]), new y5() { // from class: qj
            @Override // defpackage.y5
            public final Object a() {
                int i3 = i2;
                final int i4 = 1;
                ti tiVar = ti.a;
                final int i5 = 0;
                final lg lgVar2 = lgVar;
                final MainActivity mainActivity2 = mainActivity;
                switch (i3) {
                    case 0:
                        nj njVarH = mainActivity2.H();
                        njVarH.getClass();
                        njVarH.b(lgVar2, new dj(true, njVarH, 2));
                        break;
                    case 1:
                        nj njVarH2 = mainActivity2.H();
                        njVarH2.getClass();
                        njVarH2.b(lgVar2, new dj(false, njVarH2, 2));
                        break;
                    case 2:
                        Set setEntrySet = pi.C().entrySet();
                        setEntrySet.getClass();
                        final List listJ0 = i3.J0(setEntrySet);
                        AlertDialog.Builder title = new AlertDialog.Builder(mainActivity2).setTitle(si.a("lighting_color", new Object[0]));
                        ArrayList arrayList = new ArrayList(k3.n0(listJ0));
                        Iterator it = listJ0.iterator();
                        while (it.hasNext()) {
                            arrayList.add((String) ((Map.Entry) it.next()).getValue());
                        }
                        AlertDialog.Builder items = title.setItems((CharSequence[]) arrayList.toArray(new String[0]), new DialogInterface.OnClickListener() { // from class: oj
                            @Override // android.content.DialogInterface.OnClickListener
                            public final void onClick(DialogInterface dialogInterface, int i6) {
                                int i7 = i4;
                                lg lgVar3 = lgVar2;
                                List list = listJ0;
                                MainActivity mainActivity3 = mainActivity2;
                                switch (i7) {
                                    case 0:
                                        nj njVarH3 = mainActivity3.H();
                                        Object key = ((Map.Entry) list.get(i6)).getKey();
                                        key.getClass();
                                        int iIntValue = ((Number) key).intValue();
                                        njVarH3.getClass();
                                        njVarH3.b(lgVar3, new ij(iIntValue, njVarH3, 0));
                                        break;
                                    default:
                                        nj njVarH4 = mainActivity3.H();
                                        Object key2 = ((Map.Entry) list.get(i6)).getKey();
                                        key2.getClass();
                                        int iIntValue2 = ((Number) key2).intValue();
                                        njVarH4.getClass();
                                        njVarH4.b(lgVar3, new ij(iIntValue2, njVarH4, 1));
                                        break;
                                }
                            }
                        });
                        items.getClass();
                        mainActivity2.p0(items);
                        break;
                    default:
                        Set setEntrySet2 = hb.h0(new yc(704709121, si.a("rose_wine", new Object[0])), new yc(704709122, si.a("autumn", new Object[0])), new yc(704709123, si.a("new_year", new Object[0])), new yc(704709126, si.a("azure", new Object[0])), new yc(704709127, si.a("indigo", new Object[0]))).entrySet();
                        setEntrySet2.getClass();
                        final List listJ1 = i3.J0(setEntrySet2);
                        AlertDialog.Builder title2 = new AlertDialog.Builder(mainActivity2).setTitle(si.a("lighting_theme", new Object[0]));
                        ArrayList arrayList2 = new ArrayList(k3.n0(listJ1));
                        Iterator it2 = listJ1.iterator();
                        while (it2.hasNext()) {
                            arrayList2.add((String) ((Map.Entry) it2.next()).getValue());
                        }
                        AlertDialog.Builder items2 = title2.setItems((CharSequence[]) arrayList2.toArray(new String[0]), new DialogInterface.OnClickListener() { // from class: oj
                            @Override // android.content.DialogInterface.OnClickListener
                            public final void onClick(DialogInterface dialogInterface, int i6) {
                                int i7 = i5;
                                lg lgVar3 = lgVar2;
                                List list = listJ1;
                                MainActivity mainActivity3 = mainActivity2;
                                switch (i7) {
                                    case 0:
                                        nj njVarH3 = mainActivity3.H();
                                        Object key = ((Map.Entry) list.get(i6)).getKey();
                                        key.getClass();
                                        int iIntValue = ((Number) key).intValue();
                                        njVarH3.getClass();
                                        njVarH3.b(lgVar3, new ij(iIntValue, njVarH3, 0));
                                        break;
                                    default:
                                        nj njVarH4 = mainActivity3.H();
                                        Object key2 = ((Map.Entry) list.get(i6)).getKey();
                                        key2.getClass();
                                        int iIntValue2 = ((Number) key2).intValue();
                                        njVarH4.getClass();
                                        njVarH4.b(lgVar3, new ij(iIntValue2, njVarH4, 1));
                                        break;
                                }
                            }
                        });
                        items2.getClass();
                        mainActivity2.p0(items2);
                        break;
                }
                return tiVar;
            }
        }));
        final int i3 = 1;
        linearLayoutL.addView(MainActivity.i(mainActivity, si.a("turn_off_306", new Object[0]), new y5() { // from class: qj
            @Override // defpackage.y5
            public final Object a() {
                int i4 = i3;
                final int i5 = 1;
                ti tiVar = ti.a;
                final int i6 = 0;
                final lg lgVar2 = lgVar;
                final MainActivity mainActivity2 = mainActivity;
                switch (i4) {
                    case 0:
                        nj njVarH = mainActivity2.H();
                        njVarH.getClass();
                        njVarH.b(lgVar2, new dj(true, njVarH, 2));
                        break;
                    case 1:
                        nj njVarH2 = mainActivity2.H();
                        njVarH2.getClass();
                        njVarH2.b(lgVar2, new dj(false, njVarH2, 2));
                        break;
                    case 2:
                        Set setEntrySet = pi.C().entrySet();
                        setEntrySet.getClass();
                        final List listJ0 = i3.J0(setEntrySet);
                        AlertDialog.Builder title = new AlertDialog.Builder(mainActivity2).setTitle(si.a("lighting_color", new Object[0]));
                        ArrayList arrayList = new ArrayList(k3.n0(listJ0));
                        Iterator it = listJ0.iterator();
                        while (it.hasNext()) {
                            arrayList.add((String) ((Map.Entry) it.next()).getValue());
                        }
                        AlertDialog.Builder items = title.setItems((CharSequence[]) arrayList.toArray(new String[0]), new DialogInterface.OnClickListener() { // from class: oj
                            @Override // android.content.DialogInterface.OnClickListener
                            public final void onClick(DialogInterface dialogInterface, int i7) {
                                int i8 = i5;
                                lg lgVar3 = lgVar2;
                                List list = listJ0;
                                MainActivity mainActivity3 = mainActivity2;
                                switch (i8) {
                                    case 0:
                                        nj njVarH3 = mainActivity3.H();
                                        Object key = ((Map.Entry) list.get(i7)).getKey();
                                        key.getClass();
                                        int iIntValue = ((Number) key).intValue();
                                        njVarH3.getClass();
                                        njVarH3.b(lgVar3, new ij(iIntValue, njVarH3, 0));
                                        break;
                                    default:
                                        nj njVarH4 = mainActivity3.H();
                                        Object key2 = ((Map.Entry) list.get(i7)).getKey();
                                        key2.getClass();
                                        int iIntValue2 = ((Number) key2).intValue();
                                        njVarH4.getClass();
                                        njVarH4.b(lgVar3, new ij(iIntValue2, njVarH4, 1));
                                        break;
                                }
                            }
                        });
                        items.getClass();
                        mainActivity2.p0(items);
                        break;
                    default:
                        Set setEntrySet2 = hb.h0(new yc(704709121, si.a("rose_wine", new Object[0])), new yc(704709122, si.a("autumn", new Object[0])), new yc(704709123, si.a("new_year", new Object[0])), new yc(704709126, si.a("azure", new Object[0])), new yc(704709127, si.a("indigo", new Object[0]))).entrySet();
                        setEntrySet2.getClass();
                        final List listJ1 = i3.J0(setEntrySet2);
                        AlertDialog.Builder title2 = new AlertDialog.Builder(mainActivity2).setTitle(si.a("lighting_theme", new Object[0]));
                        ArrayList arrayList2 = new ArrayList(k3.n0(listJ1));
                        Iterator it2 = listJ1.iterator();
                        while (it2.hasNext()) {
                            arrayList2.add((String) ((Map.Entry) it2.next()).getValue());
                        }
                        AlertDialog.Builder items2 = title2.setItems((CharSequence[]) arrayList2.toArray(new String[0]), new DialogInterface.OnClickListener() { // from class: oj
                            @Override // android.content.DialogInterface.OnClickListener
                            public final void onClick(DialogInterface dialogInterface, int i7) {
                                int i8 = i6;
                                lg lgVar3 = lgVar2;
                                List list = listJ1;
                                MainActivity mainActivity3 = mainActivity2;
                                switch (i8) {
                                    case 0:
                                        nj njVarH3 = mainActivity3.H();
                                        Object key = ((Map.Entry) list.get(i7)).getKey();
                                        key.getClass();
                                        int iIntValue = ((Number) key).intValue();
                                        njVarH3.getClass();
                                        njVarH3.b(lgVar3, new ij(iIntValue, njVarH3, 0));
                                        break;
                                    default:
                                        nj njVarH4 = mainActivity3.H();
                                        Object key2 = ((Map.Entry) list.get(i7)).getKey();
                                        key2.getClass();
                                        int iIntValue2 = ((Number) key2).intValue();
                                        njVarH4.getClass();
                                        njVarH4.b(lgVar3, new ij(iIntValue2, njVarH4, 1));
                                        break;
                                }
                            }
                        });
                        items2.getClass();
                        mainActivity2.p0(items2);
                        break;
                }
                return tiVar;
            }
        }));
        final int i4 = 2;
        linearLayoutL.addView(MainActivity.i(mainActivity, si.a("color", new Object[0]), new y5() { // from class: qj
            @Override // defpackage.y5
            public final Object a() {
                int i5 = i4;
                final int i6 = 1;
                ti tiVar = ti.a;
                final int i7 = 0;
                final lg lgVar2 = lgVar;
                final MainActivity mainActivity2 = mainActivity;
                switch (i5) {
                    case 0:
                        nj njVarH = mainActivity2.H();
                        njVarH.getClass();
                        njVarH.b(lgVar2, new dj(true, njVarH, 2));
                        break;
                    case 1:
                        nj njVarH2 = mainActivity2.H();
                        njVarH2.getClass();
                        njVarH2.b(lgVar2, new dj(false, njVarH2, 2));
                        break;
                    case 2:
                        Set setEntrySet = pi.C().entrySet();
                        setEntrySet.getClass();
                        final List listJ0 = i3.J0(setEntrySet);
                        AlertDialog.Builder title = new AlertDialog.Builder(mainActivity2).setTitle(si.a("lighting_color", new Object[0]));
                        ArrayList arrayList = new ArrayList(k3.n0(listJ0));
                        Iterator it = listJ0.iterator();
                        while (it.hasNext()) {
                            arrayList.add((String) ((Map.Entry) it.next()).getValue());
                        }
                        AlertDialog.Builder items = title.setItems((CharSequence[]) arrayList.toArray(new String[0]), new DialogInterface.OnClickListener() { // from class: oj
                            @Override // android.content.DialogInterface.OnClickListener
                            public final void onClick(DialogInterface dialogInterface, int i8) {
                                int i9 = i6;
                                lg lgVar3 = lgVar2;
                                List list = listJ0;
                                MainActivity mainActivity3 = mainActivity2;
                                switch (i9) {
                                    case 0:
                                        nj njVarH3 = mainActivity3.H();
                                        Object key = ((Map.Entry) list.get(i8)).getKey();
                                        key.getClass();
                                        int iIntValue = ((Number) key).intValue();
                                        njVarH3.getClass();
                                        njVarH3.b(lgVar3, new ij(iIntValue, njVarH3, 0));
                                        break;
                                    default:
                                        nj njVarH4 = mainActivity3.H();
                                        Object key2 = ((Map.Entry) list.get(i8)).getKey();
                                        key2.getClass();
                                        int iIntValue2 = ((Number) key2).intValue();
                                        njVarH4.getClass();
                                        njVarH4.b(lgVar3, new ij(iIntValue2, njVarH4, 1));
                                        break;
                                }
                            }
                        });
                        items.getClass();
                        mainActivity2.p0(items);
                        break;
                    default:
                        Set setEntrySet2 = hb.h0(new yc(704709121, si.a("rose_wine", new Object[0])), new yc(704709122, si.a("autumn", new Object[0])), new yc(704709123, si.a("new_year", new Object[0])), new yc(704709126, si.a("azure", new Object[0])), new yc(704709127, si.a("indigo", new Object[0]))).entrySet();
                        setEntrySet2.getClass();
                        final List listJ1 = i3.J0(setEntrySet2);
                        AlertDialog.Builder title2 = new AlertDialog.Builder(mainActivity2).setTitle(si.a("lighting_theme", new Object[0]));
                        ArrayList arrayList2 = new ArrayList(k3.n0(listJ1));
                        Iterator it2 = listJ1.iterator();
                        while (it2.hasNext()) {
                            arrayList2.add((String) ((Map.Entry) it2.next()).getValue());
                        }
                        AlertDialog.Builder items2 = title2.setItems((CharSequence[]) arrayList2.toArray(new String[0]), new DialogInterface.OnClickListener() { // from class: oj
                            @Override // android.content.DialogInterface.OnClickListener
                            public final void onClick(DialogInterface dialogInterface, int i8) {
                                int i9 = i7;
                                lg lgVar3 = lgVar2;
                                List list = listJ1;
                                MainActivity mainActivity3 = mainActivity2;
                                switch (i9) {
                                    case 0:
                                        nj njVarH3 = mainActivity3.H();
                                        Object key = ((Map.Entry) list.get(i8)).getKey();
                                        key.getClass();
                                        int iIntValue = ((Number) key).intValue();
                                        njVarH3.getClass();
                                        njVarH3.b(lgVar3, new ij(iIntValue, njVarH3, 0));
                                        break;
                                    default:
                                        nj njVarH4 = mainActivity3.H();
                                        Object key2 = ((Map.Entry) list.get(i8)).getKey();
                                        key2.getClass();
                                        int iIntValue2 = ((Number) key2).intValue();
                                        njVarH4.getClass();
                                        njVarH4.b(lgVar3, new ij(iIntValue2, njVarH4, 1));
                                        break;
                                }
                            }
                        });
                        items2.getClass();
                        mainActivity2.p0(items2);
                        break;
                }
                return tiVar;
            }
        }));
        if (!zEquals) {
            final int i5 = 3;
            linearLayoutL.addView(MainActivity.i(mainActivity, si.a("theme", new Object[0]), new y5() { // from class: qj
                @Override // defpackage.y5
                public final Object a() {
                    int i6 = i5;
                    final int i7 = 1;
                    ti tiVar = ti.a;
                    final int i8 = 0;
                    final lg lgVar2 = lgVar;
                    final MainActivity mainActivity2 = mainActivity;
                    switch (i6) {
                        case 0:
                            nj njVarH = mainActivity2.H();
                            njVarH.getClass();
                            njVarH.b(lgVar2, new dj(true, njVarH, 2));
                            break;
                        case 1:
                            nj njVarH2 = mainActivity2.H();
                            njVarH2.getClass();
                            njVarH2.b(lgVar2, new dj(false, njVarH2, 2));
                            break;
                        case 2:
                            Set setEntrySet = pi.C().entrySet();
                            setEntrySet.getClass();
                            final List listJ0 = i3.J0(setEntrySet);
                            AlertDialog.Builder title = new AlertDialog.Builder(mainActivity2).setTitle(si.a("lighting_color", new Object[0]));
                            ArrayList arrayList = new ArrayList(k3.n0(listJ0));
                            Iterator it = listJ0.iterator();
                            while (it.hasNext()) {
                                arrayList.add((String) ((Map.Entry) it.next()).getValue());
                            }
                            AlertDialog.Builder items = title.setItems((CharSequence[]) arrayList.toArray(new String[0]), new DialogInterface.OnClickListener() { // from class: oj
                                @Override // android.content.DialogInterface.OnClickListener
                                public final void onClick(DialogInterface dialogInterface, int i9) {
                                    int i10 = i7;
                                    lg lgVar3 = lgVar2;
                                    List list = listJ0;
                                    MainActivity mainActivity3 = mainActivity2;
                                    switch (i10) {
                                        case 0:
                                            nj njVarH3 = mainActivity3.H();
                                            Object key = ((Map.Entry) list.get(i9)).getKey();
                                            key.getClass();
                                            int iIntValue = ((Number) key).intValue();
                                            njVarH3.getClass();
                                            njVarH3.b(lgVar3, new ij(iIntValue, njVarH3, 0));
                                            break;
                                        default:
                                            nj njVarH4 = mainActivity3.H();
                                            Object key2 = ((Map.Entry) list.get(i9)).getKey();
                                            key2.getClass();
                                            int iIntValue2 = ((Number) key2).intValue();
                                            njVarH4.getClass();
                                            njVarH4.b(lgVar3, new ij(iIntValue2, njVarH4, 1));
                                            break;
                                    }
                                }
                            });
                            items.getClass();
                            mainActivity2.p0(items);
                            break;
                        default:
                            Set setEntrySet2 = hb.h0(new yc(704709121, si.a("rose_wine", new Object[0])), new yc(704709122, si.a("autumn", new Object[0])), new yc(704709123, si.a("new_year", new Object[0])), new yc(704709126, si.a("azure", new Object[0])), new yc(704709127, si.a("indigo", new Object[0]))).entrySet();
                            setEntrySet2.getClass();
                            final List listJ1 = i3.J0(setEntrySet2);
                            AlertDialog.Builder title2 = new AlertDialog.Builder(mainActivity2).setTitle(si.a("lighting_theme", new Object[0]));
                            ArrayList arrayList2 = new ArrayList(k3.n0(listJ1));
                            Iterator it2 = listJ1.iterator();
                            while (it2.hasNext()) {
                                arrayList2.add((String) ((Map.Entry) it2.next()).getValue());
                            }
                            AlertDialog.Builder items2 = title2.setItems((CharSequence[]) arrayList2.toArray(new String[0]), new DialogInterface.OnClickListener() { // from class: oj
                                @Override // android.content.DialogInterface.OnClickListener
                                public final void onClick(DialogInterface dialogInterface, int i9) {
                                    int i10 = i8;
                                    lg lgVar3 = lgVar2;
                                    List list = listJ1;
                                    MainActivity mainActivity3 = mainActivity2;
                                    switch (i10) {
                                        case 0:
                                            nj njVarH3 = mainActivity3.H();
                                            Object key = ((Map.Entry) list.get(i9)).getKey();
                                            key.getClass();
                                            int iIntValue = ((Number) key).intValue();
                                            njVarH3.getClass();
                                            njVarH3.b(lgVar3, new ij(iIntValue, njVarH3, 0));
                                            break;
                                        default:
                                            nj njVarH4 = mainActivity3.H();
                                            Object key2 = ((Map.Entry) list.get(i9)).getKey();
                                            key2.getClass();
                                            int iIntValue2 = ((Number) key2).intValue();
                                            njVarH4.getClass();
                                            njVarH4.b(lgVar3, new ij(iIntValue2, njVarH4, 1));
                                            break;
                                    }
                                }
                            });
                            items2.getClass();
                            mainActivity2.p0(items2);
                            break;
                    }
                    return tiVar;
                }
            }));
        }
        if (!bjVar.g.isEmpty()) {
            linearLayoutL.addView(MainActivity.i(mainActivity, si.a("brightness", new Object[0]), new u0(mainActivity, bjVar, lgVar, 9)));
        }
        linearLayoutL.addView(MainActivity.i(mainActivity, si.a("stock_settings", new Object[0]), new mg(mainActivity, i)));
        AlertDialog.Builder title = new AlertDialog.Builder(mainActivity).setTitle(si.a("ambient_lighting", new Object[0]));
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.addView(linearLayoutL);
        AlertDialog.Builder positiveButton = title.setView(scrollView).setPositiveButton(si.a("close", new Object[0]), (DialogInterface.OnClickListener) null);
        positiveButton.getClass();
        mainActivity.p0(positiveButton);
    }

    public static final void j(MainActivity mainActivity) {
        String str;
        String strY0;
        mainActivity.getClass();
        String str2 = mainActivity.Z.l;
        if (th.A(str2)) {
            str2 = mainActivity.Z.k;
        }
        r4 r4VarP = r4.C.p(mainActivity);
        if (r4VarP != null) {
            List list = si.a;
            String strA = si.a("climate_0n1n", r4VarP.l() ? "Monjaro" : "NS11 / ECARX", r4VarP.q);
            synchronized (r4VarP.p) {
                strY0 = i3.y0(r4VarP.p, "\n", null, 62);
            }
            str = strA + ((Object) strY0);
        } else {
            str = null;
        }
        if (str == null) {
            str = "";
        }
        List listI0 = j3.i0(str2, str);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listI0) {
            if (!th.A((String) obj)) {
                arrayList.add(obj);
            }
        }
        String strY1 = i3.y0(arrayList, "\n\n", null, 62);
        AlertDialog.Builder positiveButton = new AlertDialog.Builder(mainActivity).setTitle(si.a("vehicle_connection", new Object[0])).setMessage(strY1).setNeutralButton(si.a("copy", new Object[0]), new c8(mainActivity, strY1, 6)).setPositiveButton(si.a("close", new Object[0]), (DialogInterface.OnClickListener) null);
        positiveButton.getClass();
        mainActivity.p0(positiveButton);
    }

    public static String k(String str) {
        str.getClass();
        if (!str.startsWith("theme_profile_")) {
            return null;
        }
        String strC = th.C(str, "theme_profile_");
        String strG = th.G(strC, "__");
        Pattern patternCompile = Pattern.compile("[a-z0-9_]{1,64}");
        patternCompile.getClass();
        if (!patternCompile.matcher(strG).matches() || !th.v(strC, "__", false)) {
            return null;
        }
        int iY = th.y(6, strC, "__", false);
        if (iY != -1) {
            strC = strC.substring(2 + iY, strC.length());
        }
        if (strC.equals("ready") || a(strC)) {
            return strC;
        }
        return null;
    }

    public static final yc l(Object obj, Object obj2) {
        return new yc(obj, obj2);
    }

    /* JADX WARN: Code duplicated, block: B:51:0x01c3  */
    public static final void m(final String str, final MainActivity mainActivity) {
        String strA;
        String strA2;
        String strA3;
        mainActivity.getClass();
        final lg lgVar = new lg(mainActivity, 10);
        int i = 6;
        if (str.equals("curtain")) {
            c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity).setTitle(si.a("sun_curtain", new Object[0])).setItems(new String[]{si.a("open", new Object[0]), si.a("close", new Object[0])}, new c2(mainActivity, new lg(mainActivity, 12), 6)), null, mainActivity);
            return;
        }
        if (str.equals("roof") || str.equals("roof_tilt")) {
            Integer num = mainActivity.Z.m;
            if (num != null) {
                int iIntValue = num.intValue();
                List list = si.a;
                strA = si.a("0_open", Integer.valueOf(iIntValue));
                if (strA == null) {
                    strA = si.a("position_unknown", new Object[0]);
                }
            } else {
                strA = si.a("position_unknown", new Object[0]);
            }
            c.e("close", new Object[0], new AlertDialog.Builder(mainActivity).setTitle(si.a("sunroof_01", strA, k8.e(mainActivity.Z.u, Boolean.TRUE) ? si.a("tilt", new Object[0]) : "")).setItems(new String[]{si.a("open_fully", new Object[0]), si.a("ventilation_304", new Object[0]), si.a("close", new Object[0])}, new c2(mainActivity, lgVar, 5)).setNeutralButton(si.a("diagnostics", new Object[0]), new z1(mainActivity, 5)), null, mainActivity);
            return;
        }
        Set set = rf.d;
        rf rfVar = (rf) pi.B().get(str);
        if (rfVar == null) {
            if (str.equals("climate")) {
                k8.m(mainActivity);
                return;
            }
            if (str.equals("ambient")) {
                i(mainActivity);
                return;
            }
            if (str.equals("fuel")) {
                f(mainActivity);
                return;
            }
            if (!str.equals("battery") || mainActivity.Z.a == null) {
                List list2 = si.a;
                strA2 = si.a("0_dashes_mean_the_data_is_unavailable", mainActivity.Z.k);
            } else {
                strA2 = si.a("battery_voltage_received_from_the_vehicle", new Object[0]);
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            Set set2 = aj.i;
            c.e("close", new Object[0], builder.setTitle((CharSequence) sh.e().get(str)).setMessage(strA2).setPositiveButton(si.a("vehicle_settings", new Object[0]), new z1(mainActivity, 8)).setNeutralButton(si.a("diagnostics", new Object[0]), new z1(mainActivity, 9)), null, mainActivity);
            return;
        }
        final List listJ0 = i3.J0(new j8(0, rfVar.a(), 1));
        AlertDialog.Builder builder2 = new AlertDialog.Builder(mainActivity);
        String str2 = rfVar.c;
        tf tfVar = (tf) mainActivity.Z.j.get(str);
        AlertDialog.Builder title = builder2.setTitle(str2 + " · " + rfVar.b(tfVar != null ? tfVar.a : null));
        ArrayList arrayList = new ArrayList(k3.n0(listJ0));
        Iterator it = listJ0.iterator();
        while (it.hasNext()) {
            int iIntValue2 = ((Number) it.next()).intValue();
            if (iIntValue2 == 0) {
                strA3 = si.a("turn_off_306", new Object[0]);
            } else {
                List list3 = si.a;
                strA3 = si.a("level_0", Integer.valueOf(iIntValue2));
            }
            arrayList.add(strA3);
        }
        c.e("close", new Object[0], title.setItems((CharSequence[]) arrayList.toArray(new String[0]), new DialogInterface.OnClickListener() { // from class: pj
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                nj njVarH = mainActivity.H();
                int iIntValue3 = ((Number) listJ0.get(i2)).intValue();
                njVarH.getClass();
                Set set3 = rf.d;
                LinkedHashMap linkedHashMapB = pi.B();
                String str3 = str;
                rf rfVar2 = (rf) linkedHashMapB.get(str3);
                lg lgVar2 = lgVar;
                if (rfVar2 == null) {
                    lgVar2.b(si.a("unknown_seat", new Object[0]));
                } else {
                    njVarH.b(lgVar2, new d8(rfVar2, pi.s(iIntValue3, 0, rfVar2.a()), njVarH, str3));
                }
            }
        }).setNeutralButton(si.a("diagnostics", new Object[0]), new z1(mainActivity, i)), null, mainActivity);
    }
}
