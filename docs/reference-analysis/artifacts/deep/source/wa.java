package defpackage;

import java.util.ArrayList;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class wa implements y5 {
    public final /* synthetic */ int a;
    public final /* synthetic */ MainActivity b;
    public final /* synthetic */ gc c;

    public /* synthetic */ wa(MainActivity mainActivity, gc gcVar, int i) {
        this.a = i;
        this.b = mainActivity;
        this.c = gcVar;
    }

    @Override // defpackage.y5
    public final Object a() {
        wa waVar;
        int i = this.a;
        ti tiVar = ti.a;
        gc gcVar = this.c;
        MainActivity mainActivity = this.b;
        switch (i) {
            case 0:
                ArrayList arrayList = mainActivity.H;
                int size = arrayList.size();
                int i2 = 0;
                while (i2 < size) {
                    Object obj = arrayList.get(i2);
                    i2++;
                    lb lbVarS = gcVar.s();
                    ((ie) obj).e(lbVarS, gcVar.r(lbVarS), gcVar.o(lbVarS));
                }
                y1 y1Var = mainActivity.I;
                if (y1Var != null) {
                    y1Var.a();
                }
                break;
            default:
                int i3 = MainActivity.E0;
                if (!mainActivity.isDestroyed() && (waVar = gcVar.x) != null) {
                    waVar.a();
                }
                break;
        }
        return tiVar;
    }
}
