package defpackage;

import android.graphics.Rect;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class bb implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ v8 a;
    public final /* synthetic */ Rect b;
    public final /* synthetic */ WindowManager.LayoutParams c;
    public final /* synthetic */ WindowManager d;

    public bb(v8 v8Var, Rect rect, WindowManager.LayoutParams layoutParams, WindowManager windowManager) {
        this.a = v8Var;
        this.b = rect;
        this.c = layoutParams;
        this.d = windowManager;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        v8 v8Var = this.a;
        v8Var.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        if (v8Var.isAttachedToWindow()) {
            int[] iArr = new int[2];
            v8Var.getLocationOnScreen(iArr);
            int i = iArr[0];
            Rect rect = this.b;
            int i2 = rect.left;
            if (i != i2 || iArr[1] != rect.top) {
                WindowManager.LayoutParams layoutParams = this.c;
                layoutParams.x = (i2 - i) + layoutParams.x;
                layoutParams.y = (rect.top - iArr[1]) + layoutParams.y;
                try {
                    this.d.updateViewLayout(v8Var, layoutParams);
                } catch (Throwable unused) {
                }
            }
            v8Var.animate().alpha(1.0f).setDuration(150L).start();
        }
    }
}
