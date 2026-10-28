package defpackage;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class n9 extends ViewOutlineProvider {
    public final /* synthetic */ int a;
    public final /* synthetic */ float b;

    public /* synthetic */ n9(float f, int i) {
        this.a = i;
        this.b = f;
    }

    @Override // android.view.ViewOutlineProvider
    public final void getOutline(View view, Outline outline) {
        int i = this.a;
        float f = this.b;
        switch (i) {
            case 0:
                view.getClass();
                outline.getClass();
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), f * 14.0f);
                break;
            default:
                view.getClass();
                outline.getClass();
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), f * 16.0f);
                break;
        }
    }
}
