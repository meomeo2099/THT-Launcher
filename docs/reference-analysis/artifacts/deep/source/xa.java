package defpackage;

import android.app.AlertDialog;
import android.view.View;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class xa implements View.OnLongClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ MainActivity b;
    public final /* synthetic */ String c;

    public /* synthetic */ xa(MainActivity mainActivity, String str, int i) {
        this.a = i;
        this.b = mainActivity;
        this.c = str;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        int i = this.a;
        String str = this.c;
        MainActivity mainActivity = this.b;
        switch (i) {
            case 0:
                int i2 = MainActivity.E0;
                uh.m(str, mainActivity);
                break;
            case 1:
                AlertDialog.Builder items = new AlertDialog.Builder(mainActivity).setItems(new String[]{si.a("choose_app", new Object[0]), si.a("edit_home_screen", new Object[0])}, new c8(mainActivity, str, 1));
                items.getClass();
                mainActivity.p0(items);
                break;
            default:
                k8.y0(str, mainActivity);
                break;
        }
        return true;
    }
}
