package defpackage;

import android.app.Dialog;
import android.view.View;
import java.util.Set;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class s5 implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ MainActivity b;
    public final /* synthetic */ String c;
    public final /* synthetic */ Dialog d;

    public /* synthetic */ s5(MainActivity mainActivity, String str, Dialog dialog, int i) {
        this.a = i;
        this.b = mainActivity;
        this.c = str;
        this.d = dialog;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.a;
        Dialog dialog = this.d;
        String str = this.c;
        MainActivity mainActivity = this.b;
        switch (i) {
            case 0:
                mainActivity.g0(-1, str);
                Set set = rf.d;
                if (!pi.B().containsKey(str)) {
                    Set set2 = aj.i;
                    if (!sh.g(str) && !x0.Y0(new String[]{"ac", "ac_max", "auto", "recirc", "defrost_rear", "steering", "airflow", "nozzle_heat", "wiper_heat"}).contains(str) && !o2.o.containsKey(str)) {
                        dialog.dismiss();
                        break;
                    }
                }
                break;
            default:
                if (k8.b(str, mainActivity)) {
                    dialog.dismiss();
                }
                break;
        }
    }
}
