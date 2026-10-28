package defpackage;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class z7 implements y5 {
    public final /* synthetic */ int a;
    public final /* synthetic */ MainActivity b;
    public final /* synthetic */ String c;
    public final /* synthetic */ le d;
    public final /* synthetic */ me e;

    public /* synthetic */ z7(le leVar, MainActivity mainActivity, String str, me meVar) {
        this.a = 2;
        this.d = leVar;
        this.b = mainActivity;
        this.c = str;
        this.e = meVar;
    }

    @Override // defpackage.y5
    public final Object a() {
        int i = this.a;
        me meVar = this.e;
        String str = this.c;
        MainActivity mainActivity = this.b;
        int i2 = 0;
        le leVar = this.d;
        ti tiVar = ti.a;
        switch (i) {
            case 0:
                j8 j8Var = new j8(1, 8, 1);
                ArrayList arrayList = new ArrayList(k3.n0(j8Var));
                Iterator it = j8Var.iterator();
                while (true) {
                    i8 i8Var = (i8) it;
                    if (i8Var.c) {
                        int iNextInt = i8Var.nextInt();
                        List list = si.a;
                        arrayList.add(new yc(si.a("image_columns_0", Integer.valueOf(iNextInt)), new yc(Integer.valueOf(iNextInt), 1)));
                    } else {
                        j8 j8Var2 = new j8(2, 4, 1);
                        ArrayList arrayList2 = new ArrayList(k3.n0(j8Var2));
                        Iterator it2 = j8Var2.iterator();
                        while (true) {
                            i8 i8Var2 = (i8) it2;
                            if (!i8Var2.c) {
                                ArrayList arrayListD0 = i3.D0(arrayList, arrayList2);
                                List<yc> listI0 = j3.i0(new yc(2, 2), new yc(3, 2), new yc(4, 2), new yc(5, 2), new yc(6, 2), new yc(3, 3), new yc(4, 3), new yc(4, 4));
                                ArrayList arrayList3 = new ArrayList(k3.n0(listI0));
                                for (yc ycVar : listI0) {
                                    int iIntValue = ((Number) ycVar.a).intValue();
                                    int iIntValue2 = ((Number) ycVar.b).intValue();
                                    List list2 = si.a;
                                    arrayList3.add(new yc(si.a("image_grid_0_1", Integer.valueOf(iIntValue2), Integer.valueOf(iIntValue)), new yc(Integer.valueOf(iIntValue), Integer.valueOf(iIntValue2))));
                                }
                                ArrayList arrayListD1 = i3.D0(arrayListD0, arrayList3);
                                MainActivity mainActivity2 = this.b;
                                AlertDialog.Builder title = new AlertDialog.Builder(mainActivity2).setTitle(si.a("image_split", new Object[0]));
                                ArrayList arrayList4 = new ArrayList(k3.n0(arrayListD1));
                                int size = arrayListD1.size();
                                int i3 = 0;
                                while (i3 < size) {
                                    Object obj = arrayListD1.get(i3);
                                    i3++;
                                    arrayList4.add((String) ((yc) obj).a);
                                }
                                AlertDialog.Builder items = title.setItems((CharSequence[]) arrayList4.toArray(new String[0]), new v5(arrayListD1, mainActivity2, this.c, this.d, this.e));
                                items.getClass();
                                k8.w0(items);
                                return tiVar;
                            }
                            int iNextInt2 = i8Var2.nextInt();
                            List list3 = si.a;
                            arrayList2.add(new yc(si.a("image_rows_0", Integer.valueOf(iNextInt2)), new yc(1, Integer.valueOf(iNextInt2))));
                        }
                    }
                }
                break;
            case 1:
                HashMap map = v7.a;
                SharedPreferences sharedPreferencesB = mainActivity.B();
                sharedPreferencesB.getClass();
                r7 r7VarD = v7.d(sharedPreferencesB, str);
                r7 r7VarA = r7.a(r7VarD, i3.E0(r7VarD.a, new u7(0.35f, 0.3f, 0.65f, 0.7f)), null, 2);
                f8.c(mainActivity, str, r7VarA);
                leVar.a = j3.h0(r7VarA.a);
                Object obj2 = meVar.b;
                if (obj2 != null) {
                    ((y5) obj2).a();
                    return tiVar;
                }
                k8.H0("fill");
                throw null;
            case 2:
                int i4 = leVar.a;
                HashMap map2 = v7.a;
                MainActivity mainActivity3 = this.b;
                SharedPreferences sharedPreferencesB2 = mainActivity3.B();
                sharedPreferencesB2.getClass();
                String str2 = this.c;
                u7 u7Var = (u7) i3.w0(v7.d(sharedPreferencesB2, str2).a, i4);
                if (u7Var != null) {
                    List list4 = si.a;
                    String strA = si.a("image_zone_0", Integer.valueOf(i4 + 1));
                    String str3 = u7Var.e;
                    List listU = k8.U(mainActivity3);
                    List list5 = j7.a;
                    List listI1 = j3.i0("power", "temp+", "temp-", "ptemp+", "ptemp-", "fan+", "fan-");
                    List list6 = j7.a;
                    ArrayList arrayList5 = new ArrayList(k3.n0(list6));
                    Iterator it3 = list6.iterator();
                    while (it3.hasNext()) {
                        arrayList5.add("air:" + ((Number) it3.next()).intValue());
                    }
                    ArrayList arrayListD2 = i3.D0(i3.D0(listI1, arrayList5), j3.i0("sync", "front", "roof_open", "roof_close", "curtain_open", "curtain_close", "trunk_open", "prev", "next", "vol+", "vol-", "mute"));
                    ArrayList arrayList6 = new ArrayList(k3.n0(arrayListD2));
                    int size2 = arrayListD2.size();
                    while (i2 < size2) {
                        Object obj3 = arrayListD2.get(i2);
                        i2++;
                        arrayList6.add("x:" + ((String) obj3));
                    }
                    ArrayList arrayListD3 = i3.D0(listU, arrayList6);
                    me meVar2 = this.e;
                    k8.a(mainActivity3, strA, str3, arrayListD3, new c1(mainActivity3, str2, meVar2, i4, 2), null, null, new d8(i4, meVar2, str2, mainActivity3), 112);
                }
                return tiVar;
            default:
                HashMap map3 = v7.a;
                SharedPreferences sharedPreferencesB3 = mainActivity.B();
                sharedPreferencesB3.getClass();
                r7 r7VarD2 = v7.d(sharedPreferencesB3, str);
                int i5 = leVar.a;
                List list7 = r7VarD2.a;
                ArrayList arrayList7 = new ArrayList();
                for (Object obj4 : list7) {
                    int i6 = i2 + 1;
                    if (i2 < 0) {
                        j3.m0();
                        throw null;
                    }
                    if (i2 != i5) {
                        arrayList7.add(obj4);
                    }
                    i2 = i6;
                }
                f8.c(mainActivity, str, r7.a(r7VarD2, arrayList7, null, 2));
                leVar.a = -1;
                Object obj5 = meVar.b;
                if (obj5 != null) {
                    ((y5) obj5).a();
                    return tiVar;
                }
                k8.H0("fill");
                throw null;
        }
    }

    public /* synthetic */ z7(MainActivity mainActivity, String str, le leVar, me meVar, int i) {
        this.a = i;
        this.b = mainActivity;
        this.c = str;
        this.d = leVar;
        this.e = meVar;
    }
}
