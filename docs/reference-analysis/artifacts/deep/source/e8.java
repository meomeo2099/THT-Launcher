package defpackage;

import android.graphics.Rect;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class e8 implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ q7 a;
    public final /* synthetic */ Rect b;
    public final /* synthetic */ WindowManager.LayoutParams c;
    public final /* synthetic */ boolean d;
    public final /* synthetic */ WindowManager e;

    public e8(q7 q7Var, Rect rect, WindowManager.LayoutParams layoutParams, boolean z, WindowManager windowManager) {
        this.a = q7Var;
        this.b = rect;
        this.c = layoutParams;
        this.d = z;
        this.e = windowManager;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        q7 q7Var = this.a;
        q7Var.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        if (q7Var.isAttachedToWindow()) {
            int[] iArr = new int[2];
            q7Var.getLocationOnScreen(iArr);
            int i = iArr[0];
            Rect rect = this.b;
            int i2 = rect.left;
            if (i != i2 || iArr[1] != rect.top) {
                WindowManager.LayoutParams layoutParams = this.c;
                layoutParams.x = (i2 - i) + layoutParams.x;
                layoutParams.y = (rect.top - iArr[1]) + layoutParams.y;
                try {
                    this.e.updateViewLayout(q7Var, layoutParams);
                } catch (Throwable unused) {
                }
            }
            if (this.d) {
                return;
            }
            q7Var.animate().alpha(1.0f).setDuration(150L).start();
        }
    }
}
