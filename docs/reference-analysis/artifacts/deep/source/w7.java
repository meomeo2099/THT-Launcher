package defpackage;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import java.util.LinkedHashMap;
import java.util.Set;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class w7 implements y5 {
    public final /* synthetic */ int a;
    public final /* synthetic */ MainActivity b;
    public final /* synthetic */ String c;

    public /* synthetic */ w7(MainActivity mainActivity, String str, int i) {
        this.a = i;
        this.b = mainActivity;
        this.c = str;
    }

    @Override // defpackage.y5
    public final Object a() {
        int i = this.a;
        ti tiVar = ti.a;
        String str = this.c;
        MainActivity mainActivity = this.b;
        int i2 = 0;
        switch (i) {
            case 0:
                me meVar = k7.a;
                k7.a(mainActivity, v7.c(mainActivity, str));
                f8.b(str, mainActivity);
                break;
            case 1:
                AlertDialog.Builder items = new AlertDialog.Builder(mainActivity).setItems(new String[]{si.a("image_settings", new Object[0]), si.a("edit_home_screen", new Object[0])}, new c8(mainActivity, str, i2));
                items.getClass();
                mainActivity.p0(items);
                break;
            case 2:
                int i3 = MainActivity.E0;
                lg lgVar = new lg(mainActivity, 11);
                Set set = rf.d;
                if (pi.B().containsKey(str)) {
                    mainActivity.H().c(str, lgVar);
                } else if (!str.equals("climate")) {
                    int i4 = 6;
                    if (str.equals("airflow")) {
                        nj njVarH = mainActivity.H();
                        njVarH.getClass();
                        njVarH.b(lgVar, new ej(njVarH, i4));
                    } else if (str.equals("roof") || str.equals("roof_tilt")) {
                        nj njVarH2 = mainActivity.H();
                        boolean zEquals = str.equals("roof_tilt");
                        njVarH2.getClass();
                        njVarH2.b(lgVar, new dj(zEquals, njVarH2, i2));
                    } else {
                        int i5 = 4;
                        if (str.equals("curtain")) {
                            nj njVarH3 = mainActivity.H();
                            njVarH3.getClass();
                            njVarH3.b(lgVar, new ej(njVarH3, i5));
                        } else if (str.equals("nozzle_heat")) {
                            nj njVarH4 = mainActivity.H();
                            njVarH4.getClass();
                            njVarH4.b(lgVar, new zd(269747200, i5));
                        } else if (str.equals("wiper_heat")) {
                            nj njVarH5 = mainActivity.H();
                            njVarH5.getClass();
                            njVarH5.b(lgVar, new zd(269747456, i5));
                        } else if (str.equals("cruise")) {
                            mainActivity.H().r(lgVar);
                        } else if (str.equals("start_stop")) {
                            nj njVarH6 = mainActivity.H();
                            njVarH6.getClass();
                            njVarH6.b(lgVar, new ej(njVarH6, i2));
                        } else if (str.equals("trunk")) {
                            nj njVarH7 = mainActivity.H();
                            njVarH7.getClass();
                            njVarH7.h(o2.a, si.a("tailgate", new Object[0]), lgVar, new fg(10));
                        } else if (str.equals("mirrors")) {
                            nj njVarH8 = mainActivity.H();
                            njVarH8.getClass();
                            njVarH8.h(o2.c, si.a("mirrors", new Object[0]), lgVar, new fg(i4));
                        } else if (str.equals("ext_light")) {
                            nj njVarH9 = mainActivity.H();
                            njVarH9.getClass();
                            njVarH9.h(o2.d, si.a("exterior_lights", new Object[0]), lgVar, new fg(7));
                        } else if (!str.equals("wipers")) {
                            LinkedHashMap linkedHashMap = o2.o;
                            if (linkedHashMap.get(str) != null) {
                                mainActivity.H().s(((Number) hb.g0(linkedHashMap, str)).intValue(), lgVar);
                            } else if (pi.j(str) == null) {
                                uh.m(str, mainActivity);
                            } else {
                                mainActivity.H().o(str, lgVar);
                            }
                        } else {
                            nj njVarH10 = mainActivity.H();
                            njVarH10.getClass();
                            njVarH10.h(o2.n, si.a("wiper_service_position", new Object[0]), lgVar, new fg(9));
                        }
                    }
                } else {
                    k8.m(mainActivity);
                }
                break;
            case 3:
                ((ClipboardManager) mainActivity.getSystemService(ClipboardManager.class)).setPrimaryClip(ClipData.newPlainText(si.a("region_pass_code", new Object[0]), str));
                mainActivity.v0(si.a("region_pass_copied", new Object[0]));
                break;
            case 4:
                c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity).setMessage(si.a("split_delete_question", new Object[0])).setPositiveButton(si.a("delete", new Object[0]), new c8(mainActivity, str, 2)), null, mainActivity);
                break;
            default:
                k8.y0(str, mainActivity);
                break;
        }
        return tiVar;
    }
}
