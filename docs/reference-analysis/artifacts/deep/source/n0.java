package defpackage;

import android.app.Dialog;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class n0 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ yi b;

    public /* synthetic */ n0(yi yiVar, int i) {
        this.a = i;
        this.b = yiVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.a;
        yi yiVar = this.b;
        switch (i) {
            case 0:
                Dialog dialog = yiVar.g;
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                break;
            default:
                yiVar.g(100, true);
                break;
        }
    }
}
