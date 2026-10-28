package defpackage;

import android.view.View;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class na implements View.OnClickListener {
    public final /* synthetic */ int a = 0;
    public final /* synthetic */ MainActivity b;
    public final /* synthetic */ String c;

    public /* synthetic */ na(String str, MainActivity mainActivity) {
        this.c = str;
        this.b = mainActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.a;
        String str = this.c;
        MainActivity mainActivity = this.b;
        switch (i) {
            case 0:
                int i2 = MainActivity.E0;
                if (!k8.e(str, "empty")) {
                    mainActivity.g0(-1, str);
                }
                break;
            default:
                k8.J0(str, mainActivity);
                break;
        }
    }

    public /* synthetic */ na(MainActivity mainActivity, String str) {
        this.b = mainActivity;
        this.c = str;
    }
}
