package defpackage;

import android.view.View;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class wd implements View.OnLongClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ wd(Object obj, Object obj2, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        int i = this.a;
        Object obj = this.c;
        Object obj2 = this.b;
        switch (i) {
            case 0:
                MainActivity mainActivity = (MainActivity) obj;
                Object obj3 = ((me) obj2).b;
                if (obj3 == null) {
                    k8.H0("scan");
                    throw null;
                }
                if (!((qd) obj3).k) {
                    xd.a("seek_prev", mainActivity);
                }
                return true;
            case 1:
                MainActivity mainActivity2 = (MainActivity) obj;
                Object obj4 = ((me) obj2).b;
                if (obj4 == null) {
                    k8.H0("scan");
                    throw null;
                }
                if (!((qd) obj4).k) {
                    xd.a("seek_next", mainActivity2);
                }
                return true;
            default:
                b2 b2Var = (b2) obj;
                if (((s8) obj2).e) {
                    b2Var.a();
                }
                return true;
        }
    }
}
