package defpackage;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import java.util.List;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c8 implements DialogInterface.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ MainActivity b;
    public final /* synthetic */ String c;

    public /* synthetic */ c8(MainActivity mainActivity, String str, int i) {
        this.a = i;
        this.b = mainActivity;
        this.c = str;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        int i2 = this.a;
        String str = this.c;
        MainActivity mainActivity = this.b;
        switch (i2) {
            case 0:
                if (i != 0) {
                    mainActivity.f = true;
                    mainActivity.e0();
                } else {
                    f8.b(str, mainActivity);
                }
                break;
            case 1:
                if (i != 0) {
                    mainActivity.f = true;
                    mainActivity.e0();
                } else {
                    k8.A(str, mainActivity);
                }
                break;
            case 2:
                k8.z(str, mainActivity);
                break;
            case 3:
                List list = eh.a;
                SharedPreferences sharedPreferencesB = mainActivity.B();
                sharedPreferencesB.getClass();
                eh.i(sharedPreferencesB, str, ((Number) eh.a.get(i)).intValue());
                dialogInterface.dismiss();
                mainActivity.e0();
                k8.y0(str, mainActivity);
                break;
            case 4:
                k8.y0(str, mainActivity);
                break;
            case 5:
                k8.z(str, mainActivity);
                break;
            default:
                ((ClipboardManager) mainActivity.getSystemService(ClipboardManager.class)).setPrimaryClip(ClipData.newPlainText(si.a("climate_diagnostics", new Object[0]), str));
                mainActivity.v0(si.a("diagnostics_copied", new Object[0]));
                break;
        }
    }
}
