package defpackage;

import android.view.View;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class sd implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ me b;
    public final /* synthetic */ MainActivity c;

    public /* synthetic */ sd(MainActivity mainActivity, me meVar) {
        this.a = 0;
        this.c = mainActivity;
        this.b = meVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.a;
        MainActivity mainActivity = this.c;
        me meVar = this.b;
        switch (i) {
            case 0:
                lb lbVarS = mainActivity.y().s();
                if (lbVarS != null) {
                    if (th.A(lbVarS.b)) {
                        lbVarS = null;
                    }
                    if (lbVarS == null) {
                        return;
                    }
                    k8.I0(mainActivity, new kd(lbVarS.b, th.J(lbVarS.c).toString(), lbVarS.a));
                    Object obj = meVar.b;
                    if (obj != null) {
                        ((y5) obj).a();
                        return;
                    } else {
                        k8.H0("refresh");
                        throw null;
                    }
                }
                return;
            case 1:
                Object obj2 = meVar.b;
                if (obj2 == null) {
                    k8.H0("scan");
                    throw null;
                }
                if (((qd) obj2).k) {
                    return;
                }
                xd.g(mainActivity);
                return;
            case 2:
                Object obj3 = meVar.b;
                if (obj3 == null) {
                    k8.H0("scan");
                    throw null;
                }
                if (((qd) obj3).k) {
                    return;
                }
                xd.f(mainActivity, -1);
                return;
            default:
                Object obj4 = meVar.b;
                if (obj4 == null) {
                    k8.H0("scan");
                    throw null;
                }
                if (((qd) obj4).k) {
                    return;
                }
                xd.f(mainActivity, 1);
                return;
        }
    }

    public /* synthetic */ sd(me meVar, MainActivity mainActivity, int i) {
        this.a = i;
        this.b = meVar;
        this.c = mainActivity;
    }
}
