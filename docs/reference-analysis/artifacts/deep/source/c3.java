package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c3 implements y5 {
    public final /* synthetic */ int a;
    public final /* synthetic */ MainActivity b;
    public final /* synthetic */ int c;

    public /* synthetic */ c3(MainActivity mainActivity, int i, int i2) {
        this.a = i2;
        this.b = mainActivity;
        this.c = i;
    }

    @Override // defpackage.y5
    public final Object a() {
        int i = this.a;
        ti tiVar = ti.a;
        int i2 = this.c;
        MainActivity mainActivity = this.b;
        switch (i) {
            case 0:
                k8.k(mainActivity, -1, i2);
                return tiVar;
            case 1:
                k8.k(mainActivity, 1, i2);
                return tiVar;
            case 2:
                int i3 = MainActivity.E0;
                return mainActivity.m(i2);
            case 3:
                int i4 = MainActivity.E0;
                k8.g0(mainActivity, Integer.valueOf(i2));
                return tiVar;
            case 4:
                sc scVar = mainActivity.y;
                return Boolean.valueOf(scVar.b && scVar.a == i2);
            case 5:
                mainActivity.B().edit().putString("menu_action_" + i2, "empty").apply();
                mainActivity.e0();
                return tiVar;
            case 6:
                int i5 = i2 - 1;
                ArrayList arrayList = new ArrayList(k8.T(mainActivity));
                if (i5 >= 0 && i5 < arrayList.size()) {
                    Collections.swap(arrayList, i2, i5);
                    k8.s0(arrayList, mainActivity);
                }
                return tiVar;
            case 7:
                int i6 = i2 + 1;
                ArrayList arrayList2 = new ArrayList(k8.T(mainActivity));
                if (i6 >= 0 && i6 < arrayList2.size()) {
                    Collections.swap(arrayList2, i2, i6);
                    k8.s0(arrayList2, mainActivity);
                }
                return tiVar;
            case 8:
                ArrayList arrayList3 = new ArrayList(k8.T(mainActivity));
                if (arrayList3.size() > 1) {
                    arrayList3.remove(i2);
                    k8.s0(arrayList3, mainActivity);
                }
                return tiVar;
            case 9:
                k8.u(mainActivity, i2);
                return tiVar;
            case 10:
                ca caVar = ea.a;
                ea.a(mainActivity, mainActivity.z()).delete();
                mainActivity.B().edit().putInt("background", i2).apply();
                mainActivity.e0();
                return tiVar;
            case 11:
                k8.V(mainActivity, i2, i2 - 1);
                return tiVar;
            default:
                k8.V(mainActivity, i2, i2 + 1);
                return tiVar;
        }
    }
}
