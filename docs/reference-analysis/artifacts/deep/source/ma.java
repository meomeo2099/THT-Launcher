package defpackage;

import android.view.View;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ma implements View.OnLongClickListener {
    public final /* synthetic */ int a = 0;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ MainActivity c;

    public /* synthetic */ ma(MainActivity mainActivity, boolean z) {
        this.c = mainActivity;
        this.b = z;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        int i = this.a;
        MainActivity mainActivity = this.c;
        boolean z = this.b;
        switch (i) {
            case 0:
                int i2 = MainActivity.E0;
                pi.o(mainActivity, z);
                break;
            default:
                int i3 = MainActivity.E0;
                if (!z) {
                    k8.D(mainActivity, null);
                } else {
                    mainActivity.h();
                }
                break;
        }
        return true;
    }

    public /* synthetic */ ma(boolean z, MainActivity mainActivity) {
        this.b = z;
        this.c = mainActivity;
    }
}
