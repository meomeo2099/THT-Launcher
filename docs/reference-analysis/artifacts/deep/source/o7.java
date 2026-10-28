package defpackage;

import android.view.View;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class o7 implements View.OnLongClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ y5 b;

    public /* synthetic */ o7(y5 y5Var, int i) {
        this.a = i;
        this.b = y5Var;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        int i = this.a;
        y5 y5Var = this.b;
        switch (i) {
            case 0:
                y5Var.a();
                break;
            case 1:
                y5Var.a();
                break;
            default:
                int i2 = MainActivity.E0;
                y5Var.a();
                break;
        }
        return true;
    }
}
