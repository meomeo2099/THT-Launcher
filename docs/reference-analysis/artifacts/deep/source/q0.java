package defpackage;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class q0 implements j6 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ q0(bj bjVar, Map map) {
        this.a = 4;
        List list = z2.a;
        this.b = bjVar;
        this.c = map;
    }

    /* JADX WARN: Code duplicated, block: B:32:0x012f  */
    @Override // defpackage.j6
    public final Object b(Object obj) {
        Integer numValueOf;
        String string;
        String string2;
        xj xjVar;
        int i = this.a;
        int i2 = 268697856;
        int i3 = 268829952;
        int i4 = 268894464;
        int i5 = 268501248;
        Object obj2 = null;
        ti tiVar = ti.a;
        int i6 = 0;
        int i7 = 1;
        int i8 = 4;
        Object obj3 = this.c;
        Object obj4 = this.b;
        switch (i) {
            case 0:
                ((Handler) obj4).post(new t0((yi) obj3, ((Integer) obj).intValue(), i6));
                return tiVar;
            case 1:
                MainActivity mainActivity = (MainActivity) obj4;
                ComponentName componentName = (ComponentName) obj;
                componentName.getClass();
                ArrayList arrayListS = k8.S(mainActivity);
                String strFlattenToString = componentName.flattenToString();
                strFlattenToString.getClass();
                mainActivity.getSharedPreferences("panel", 0).edit().putString("auto_apps_list", i3.y0(i3.E0(arrayListS, new e2(5, strFlattenToString)), "\n", new w(i6), 30)).apply();
                ((y5) obj3).a();
                return tiVar;
            case 2:
                MainActivity mainActivity2 = (MainActivity) obj4;
                Dialog dialog = (Dialog) obj3;
                String str = (String) obj;
                str.getClass();
                mainActivity2.getClass();
                List list = z2.a;
                v1 v1Var = new v1(mainActivity2, i7);
                if (str.equals("close")) {
                    dialog.dismiss();
                } else if (str.equals("edit")) {
                    dialog.dismiss();
                    mainActivity2.u = "climate";
                    mainActivity2.c = true;
                    mainActivity2.e0();
                    if (mainActivity2.A()) {
                        mainActivity2.startActivity(new Intent(mainActivity2, (Class<?>) MainActivity.class).addFlags(268566528));
                    }
                } else {
                    int i9 = -1;
                    if (str.startsWith("run:")) {
                        mainActivity2.g0(-1, th.C(str, "run:"));
                    } else if (str.equals("diagnostics")) {
                        uh.j(mainActivity2);
                    } else if (str.equals("power")) {
                        nj njVarH = mainActivity2.H();
                        njVarH.getClass();
                        njVarH.b(v1Var, new zd(i5, i8));
                    } else if (str.equals("temp-")) {
                        k8.k(mainActivity2, -1, 1);
                    } else if (str.equals("temp+")) {
                        k8.k(mainActivity2, 1, 1);
                    } else if (str.equals("ptemp-")) {
                        nj njVarH2 = mainActivity2.H();
                        njVarH2.getClass();
                        njVarH2.b(v1Var, new fj(njVarH2, i8, i9, i7));
                    } else if (str.equals("ptemp+")) {
                        nj njVarH3 = mainActivity2.H();
                        njVarH3.getClass();
                        njVarH3.b(v1Var, new fj(njVarH3, i8, i7, i7));
                    } else if (str.equals("sync")) {
                        nj njVarH4 = mainActivity2.H();
                        njVarH4.getClass();
                        njVarH4.b(v1Var, new zd(i3, i8));
                    } else if (str.startsWith("fan:")) {
                        k8.t0(mainActivity2, Integer.parseInt(th.C(str, "fan:")));
                    } else if (str.equals("auto")) {
                        nj njVarH5 = mainActivity2.H();
                        njVarH5.getClass();
                        njVarH5.b(v1Var, new zd(268501504, i8));
                    } else if (str.equals("ac")) {
                        nj njVarH6 = mainActivity2.H();
                        njVarH6.getClass();
                        njVarH6.b(v1Var, new zd(268501760, i8));
                    } else if (str.equals("recirc")) {
                        nj njVarH7 = mainActivity2.H();
                        njVarH7.getClass();
                        njVarH7.b(v1Var, new zd(268632320, i8));
                    } else if (str.equals("front")) {
                        nj njVarH8 = mainActivity2.H();
                        njVarH8.getClass();
                        njVarH8.b(v1Var, new zd(i2, i8));
                    } else if (str.equals("wiper")) {
                        nj njVarH9 = mainActivity2.H();
                        njVarH9.getClass();
                        njVarH9.b(v1Var, new zd(269747456, i8));
                    } else if (str.equals("nozzle")) {
                        nj njVarH10 = mainActivity2.H();
                        njVarH10.getClass();
                        njVarH10.b(v1Var, new zd(269747200, i8));
                    } else if (str.equals("rear")) {
                        nj njVarH11 = mainActivity2.H();
                        njVarH11.getClass();
                        njVarH11.b(v1Var, new zd(268698368, i8));
                    } else if (str.equals("steering")) {
                        nj njVarH12 = mainActivity2.H();
                        njVarH12.getClass();
                        njVarH12.b(v1Var, new ej(njVarH12, 3));
                    } else if (str.startsWith("air:")) {
                        nj njVarH13 = mainActivity2.H();
                        int i10 = Integer.parseInt(th.C(str, "air:"));
                        njVarH13.getClass();
                        njVarH13.b(v1Var, new fj(njVarH13, i4, i10, i6));
                    } else if (str.startsWith("seat:")) {
                        mainActivity2.H().c(th.C(str, "seat:"), v1Var);
                    }
                }
                return tiVar;
            case 3:
                Map map = (Map) obj4;
                MainActivity mainActivity3 = (MainActivity) obj3;
                String str2 = (String) obj;
                str2.getClass();
                String strC = th.C(str2, "x:");
                Integer num = (Integer) map.get(268501248);
                if (num != null && num.intValue() != 0) {
                    i6 = 1;
                }
                if (!strC.startsWith("air:") || !str2.startsWith("x:")) {
                    if (str2.equals("x:power")) {
                        numValueOf = Integer.valueOf(l8.g());
                        if (i6 == 0) {
                            return null;
                        }
                    } else if (str2.equals("x:sync")) {
                        l8.h();
                        Integer num2 = (Integer) map.get(268829952);
                        if (num2 == null || num2.intValue() != 1) {
                            return null;
                        }
                    } else if (str2.equals("x:front")) {
                        l8.h();
                        Integer num3 = (Integer) map.get(268697856);
                        if (num3 == null || num3.intValue() != 1) {
                            return null;
                        }
                    } else {
                        if (str2.startsWith("x:")) {
                            return null;
                        }
                        yc ycVarO = mainActivity3.O(str2);
                        boolean zBooleanValue = ((Boolean) ycVarO.a).booleanValue();
                        numValueOf = Integer.valueOf(((Number) ycVarO.b).intValue());
                        if (!zBooleanValue) {
                            return null;
                        }
                    }
                    return numValueOf;
                }
                l8.h();
                if (i6 == 0 || !k8.e(map.get(268894464), bi.q(th.C(strC, "air:")))) {
                    return null;
                }
                return -10233877;
            case 4:
                bj bjVar = (bj) obj4;
                Map map2 = (Map) obj3;
                List list2 = z2.a;
                String str3 = (String) obj;
                str3.getClass();
                Float f = (Float) bjVar.i.a.get(1);
                Float f2 = (Float) bjVar.i.a.get(4);
                if (x0.Y0(new String[]{"temp", "temp2", "climate", "widget:temp", "widget:degree_driver", "widget:anton", "x:temp+", "x:temp-", "x:power"}).contains(str3)) {
                    if (f != null) {
                        return z2.d(f.floatValue());
                    }
                } else if (x0.Y0(new String[]{"temp_passenger", "widget:temp_passenger", "widget:degree_passenger", "x:ptemp+", "x:ptemp-"}).contains(str3)) {
                    if (f2 != null) {
                        return z2.d(f2.floatValue());
                    }
                } else {
                    if (x0.Y0(new String[]{"x:fan+", "x:fan-"}).contains(str3)) {
                        Integer num4 = (Integer) map2.get(268501248);
                        if (num4 != null && num4.intValue() == 0) {
                            return "0";
                        }
                        Integer numC = z2.c((Integer) map2.get(268566784));
                        if (numC != null) {
                            Integer num5 = numC.intValue() <= 8 ? numC : null;
                            if (num5 != null && (string2 = num5.toString()) != null) {
                                return string2;
                            }
                        }
                        return "A";
                    }
                    Set set = rf.d;
                    if (!pi.B().containsKey(str3)) {
                        if (!str3.equals("steering")) {
                            return null;
                        }
                        Integer numI = z2.i((Integer) map2.get(269025536));
                        return String.valueOf(numI != null ? numI.intValue() : 0);
                    }
                    rf rfVar = (rf) hb.g0(pi.B(), str3);
                    tf tfVar = (tf) bjVar.j.get(str3);
                    Integer numC2 = rfVar.c(tfVar != null ? tfVar.a : null);
                    if (numC2 != null && (string = numC2.toString()) != null) {
                        return string;
                    }
                }
                return "—";
            case 5:
                ((le) obj4).a = ((Integer) obj).intValue();
                Object obj5 = ((me) obj3).b;
                if (obj5 != null) {
                    ((y5) obj5).a();
                    return tiVar;
                }
                k8.H0("fill");
                throw null;
            case 6:
                Integer num6 = (Integer) obj4;
                MainActivity mainActivity4 = (MainActivity) obj3;
                ComponentName componentName2 = (ComponentName) obj;
                componentName2.getClass();
                if (num6 == null) {
                    Intent intentMakeMainActivity = Intent.makeMainActivity(componentName2);
                    intentMakeMainActivity.getClass();
                    mainActivity4.Q(intentMakeMainActivity);
                } else {
                    mainActivity4.B().edit().putString(MainActivity.f(num6.intValue()), componentName2.flattenToString()).apply();
                    mainActivity4.e0();
                }
                return tiVar;
            case 7:
                ArrayList arrayList = (ArrayList) obj4;
                MainActivity mainActivity5 = (MainActivity) obj3;
                String str4 = (String) obj;
                str4.getClass();
                int size = arrayList.size();
                while (i6 < size) {
                    Object obj6 = arrayList.get(i6);
                    i6++;
                    if (((xj) obj6).a.equals(str4)) {
                        obj2 = obj6;
                        xjVar = (xj) obj2;
                        if (xjVar != null) {
                            k8.b(xjVar.b, mainActivity5);
                        }
                        return tiVar;
                    }
                }
                xjVar = (xj) obj2;
                if (xjVar != null) {
                    k8.b(xjVar.b, mainActivity5);
                }
                return tiVar;
            case 8:
                MainActivity mainActivity6 = (MainActivity) obj4;
                ComponentName componentName3 = (ComponentName) obj;
                componentName3.getClass();
                List list3 = eh.a;
                SharedPreferences sharedPreferencesB = mainActivity6.B();
                sharedPreferencesB.getClass();
                String string3 = UUID.randomUUID().toString();
                string3.getClass();
                String strI = th.I(8, ci.s(string3, "-", ""));
                sharedPreferencesB.edit().putString("split_list", i3.y0(i3.E0(eh.c(sharedPreferencesB), strI), ",", null, 62)).apply();
                SharedPreferences sharedPreferencesB2 = mainActivity6.B();
                sharedPreferencesB2.getClass();
                eh.h(sharedPreferencesB2, strI, true, (ComponentName) obj3);
                SharedPreferences sharedPreferencesB3 = mainActivity6.B();
                sharedPreferencesB3.getClass();
                eh.h(sharedPreferencesB3, strI, false, componentName3);
                mainActivity6.e0();
                k8.y0(strI, mainActivity6);
                return tiVar;
            case 9:
                Object[] objArr = (Object[]) obj4;
                ri riVar = (ri) obj3;
                jb jbVar = (jb) obj;
                jbVar.getClass();
                if (jbVar.c == null) {
                    jbVar.c = new ib(jbVar);
                }
                ib ibVar = jbVar.c;
                ibVar.getClass();
                int i11 = Integer.parseInt((String) ibVar.get(1));
                if (i11 < 0 || i11 >= objArr.length) {
                    String strGroup = jbVar.a.group();
                    strGroup.getClass();
                    return strGroup;
                }
                String strValueOf = String.valueOf(objArr[i11]);
                if (!riVar.a.equals("ar") || strValueOf.length() <= 0) {
                    return strValueOf;
                }
                return "\u2068" + strValueOf + "\u2069";
            default:
                nj njVar = (nj) obj3;
                r4 r4Var = (r4) obj;
                r4Var.getClass();
                int i12 = ((n4) obj4).b;
                boolean zW = r4Var.w(570491136, i12);
                if (zW) {
                    njVar.d("drive", Integer.valueOf(i12), 6000L);
                }
                return Boolean.valueOf(zW);
        }
    }

    public /* synthetic */ q0(Object obj, Object obj2, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }
}
