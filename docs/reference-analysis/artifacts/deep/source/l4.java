package defpackage;

import android.view.KeyEvent;
import android.view.View;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class l4 implements View.OnLongClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ KeyEvent.Callback b;

    public /* synthetic */ l4(KeyEvent.Callback callback, int i) {
        this.a = i;
        this.b = callback;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        int i = this.a;
        KeyEvent.Callback callback = this.b;
        switch (i) {
            case 0:
                return ((m4) callback).performLongClick();
            case 1:
                ((q8) callback).d.b("diagnostics");
                return true;
            case 2:
                return ((v8) callback).performLongClick();
            case 3:
                ((k9) callback).c.a();
                return true;
            default:
                int i2 = MainActivity.E0;
                xd.b((MainActivity) callback);
                return true;
        }
    }
}
