package defpackage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import java.util.ArrayList;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class x1 implements y5 {
    public final /* synthetic */ int a = 0;
    public final /* synthetic */ MainActivity b;
    public final /* synthetic */ int c;
    public final /* synthetic */ Object d;

    public /* synthetic */ x1(MainActivity mainActivity, int i, y5 y5Var) {
        this.b = mainActivity;
        this.c = i;
        this.d = y5Var;
    }

    @Override // defpackage.y5
    public final Object a() {
        int i = this.a;
        ti tiVar = ti.a;
        Object obj = this.d;
        switch (i) {
            case 0:
                final y5 y5Var = (y5) obj;
                final MainActivity mainActivity = this.b;
                final ArrayList arrayList = new ArrayList(k8.S(mainActivity));
                final int i2 = this.c;
                final e2 e2Var = (e2) i3.w0(arrayList, i2);
                if (e2Var != null) {
                    final ArrayList arrayListJ0 = j3.j0("delay");
                    if (i2 > 0) {
                        arrayListJ0.add("up");
                    }
                    arrayListJ0.add("remove");
                    ArrayList arrayList2 = new ArrayList(k3.n0(arrayListJ0));
                    int size = arrayListJ0.size();
                    int i3 = 0;
                    while (i3 < size) {
                        Object obj2 = arrayListJ0.get(i3);
                        i3++;
                        String str = (String) obj2;
                        arrayList2.add(k8.e(str, "delay") ? si.a("app_autostart_delay", new Object[0]) : k8.e(str, "up") ? si.a("app_autostart_move_up", new Object[0]) : si.a("app_autostart_remove", new Object[0]));
                    }
                    c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity).setTitle(pi.i(e2Var.a, mainActivity)).setItems((CharSequence[]) arrayList2.toArray(new String[0]), new DialogInterface.OnClickListener() { // from class: a2
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i4) {
                            String str2 = (String) arrayListJ0.get(i4);
                            boolean zE = k8.e(str2, "delay");
                            final MainActivity mainActivity2 = mainActivity;
                            final e2 e2Var2 = e2Var;
                            final ArrayList arrayList3 = arrayList;
                            final int i5 = i2;
                            final y5 y5Var2 = y5Var;
                            if (zE) {
                                String strA = si.a("app_autostart_delay", new Object[0]);
                                int i6 = e2Var2.b;
                                pi.K(mainActivity2, strA, i6 - (i6 % 5), 0, 120, 5, si.a("seconds_short", new Object[0]), null, new j6() { // from class: d2
                                    @Override // defpackage.j6
                                    public final Object b(Object obj3) {
                                        e2 e2Var3 = new e2(((Integer) obj3).intValue(), e2Var2.a);
                                        ArrayList arrayList4 = arrayList3;
                                        arrayList4.set(i5, e2Var3);
                                        mainActivity2.getSharedPreferences("panel", 0).edit().putString("auto_apps_list", i3.y0(arrayList4, "\n", new w(0), 30)).apply();
                                        y5Var2.a();
                                        return ti.a;
                                    }
                                });
                            } else if (!k8.e(str2, "up")) {
                                arrayList3.remove(i5);
                                mainActivity2.getSharedPreferences("panel", 0).edit().putString("auto_apps_list", i3.y0(arrayList3, "\n", new w(0), 30)).apply();
                                y5Var2.a();
                            } else {
                                arrayList3.remove(i5);
                                arrayList3.add(i5 - 1, e2Var2);
                                mainActivity2.getSharedPreferences("panel", 0).edit().putString("auto_apps_list", i3.y0(arrayList3, "\n", new w(0), 30)).apply();
                                y5Var2.a();
                            }
                        }
                    }), null, mainActivity);
                }
                break;
            default:
                k8.o(this.b, (String) obj, Integer.valueOf(this.c));
                break;
        }
        return tiVar;
    }

    public /* synthetic */ x1(MainActivity mainActivity, String str, int i) {
        this.b = mainActivity;
        this.d = str;
        this.c = i;
    }
}
