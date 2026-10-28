package defpackage;

import android.view.View;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ud implements View.OnClickListener {
    public final /* synthetic */ int a = 1;
    public final /* synthetic */ me b;
    public final /* synthetic */ MainActivity c;
    public final /* synthetic */ kd d;

    public /* synthetic */ ud(me meVar, MainActivity mainActivity, kd kdVar) {
        this.b = meVar;
        this.c = mainActivity;
        this.d = kdVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.a;
        kd kdVar = this.d;
        MainActivity mainActivity = this.c;
        me meVar = this.b;
        switch (i) {
            case 0:
                k8.I0(mainActivity, kdVar);
                Object obj = meVar.b;
                if (obj != null) {
                    ((y5) obj).a();
                    return;
                } else {
                    k8.H0("refresh");
                    throw null;
                }
            default:
                Object obj2 = meVar.b;
                if (obj2 == null) {
                    k8.H0("scan");
                    throw null;
                }
                if (((qd) obj2).k) {
                    return;
                }
                gc gcVarY = mainActivity.y();
                gcVarY.G.b = null;
                if (gcVarY.n()) {
                    gcVarY.E(kdVar);
                    return;
                } else {
                    mainActivity.v0(si.a("radio_unavailable", new Object[0]));
                    return;
                }
        }
    }

    public /* synthetic */ ud(MainActivity mainActivity, kd kdVar, me meVar) {
        this.c = mainActivity;
        this.d = kdVar;
        this.b = meVar;
    }
}
