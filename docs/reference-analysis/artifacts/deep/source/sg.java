package defpackage;

import android.graphics.Point;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class sg implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ tg a;
    public final /* synthetic */ int b;
    public final /* synthetic */ int c;
    public final /* synthetic */ int d;
    public final /* synthetic */ WindowManager.LayoutParams e;
    public final /* synthetic */ WindowManager f;

    public sg(tg tgVar, int i, int i2, int i3, WindowManager.LayoutParams layoutParams, WindowManager windowManager) {
        this.a = tgVar;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = layoutParams;
        this.f = windowManager;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        tg tgVar = this.a;
        tgVar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        if (tgVar.isAttachedToWindow()) {
            int[] iArr = new int[2];
            tgVar.getLocationOnScreen(iArr);
            int i = (this.b - (this.c / 2)) - iArr[0];
            int i2 = this.d - iArr[1];
            if (i != 0 || i2 != 0) {
                tg tgVar2 = ug.a;
                Point point = ug.c;
                ug.c = new Point(point.x + i, point.y + i2);
                WindowManager.LayoutParams layoutParams = this.e;
                layoutParams.x += i;
                layoutParams.y += i2;
                try {
                    this.f.updateViewLayout(tgVar, layoutParams);
                } catch (Throwable unused) {
                }
            }
            tgVar.animate().alpha(1.0f).setDuration(150L).start();
        }
    }
}
