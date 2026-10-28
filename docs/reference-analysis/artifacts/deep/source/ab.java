package defpackage;

import android.view.KeyEvent;
import android.widget.FrameLayout;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class ab extends FrameLayout {
    public final /* synthetic */ MainActivity a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ab(MainActivity mainActivity) {
        super(mainActivity);
        this.a = mainActivity;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        keyEvent.getClass();
        if (keyEvent.getKeyCode() != 4) {
            return super.dispatchKeyEvent(keyEvent);
        }
        if (keyEvent.getAction() == 1) {
            MainActivity mainActivity = this.a;
            if (mainActivity.c) {
                mainActivity.onBackPressed();
                return true;
            }
            mainActivity.k();
        }
        return true;
    }
}
