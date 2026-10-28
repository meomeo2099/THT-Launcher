package defpackage;

import android.app.Dialog;
import android.view.View;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class q5 implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Dialog b;

    public /* synthetic */ q5(Dialog dialog, int i) {
        this.a = i;
        this.b = dialog;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.a;
        Dialog dialog = this.b;
        switch (i) {
            case 0:
                dialog.dismiss();
                break;
            default:
                dialog.dismiss();
                break;
        }
    }
}
