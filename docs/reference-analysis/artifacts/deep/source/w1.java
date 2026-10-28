package defpackage;

import android.app.Dialog;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class w1 implements y5 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Dialog b;

    public /* synthetic */ w1(Dialog dialog, int i) {
        this.a = i;
        this.b = dialog;
    }

    @Override // defpackage.y5
    public final Object a() {
        int i = this.a;
        ti tiVar = ti.a;
        Dialog dialog = this.b;
        switch (i) {
            case 0:
                dialog.dismiss();
                break;
            case 1:
                dialog.dismiss();
                break;
            case 2:
                dialog.dismiss();
                break;
            default:
                dialog.dismiss();
                break;
        }
        return tiVar;
    }
}
