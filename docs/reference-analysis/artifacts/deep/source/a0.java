package defpackage;

import android.content.DialogInterface;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a0 implements DialogInterface.OnDismissListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ a0(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                i0 i0Var = (i0) obj;
                i0Var.h = true;
                i0Var.e.shutdownNow();
                break;
            default:
                ((MainActivity) obj).e0();
                break;
        }
    }
}
