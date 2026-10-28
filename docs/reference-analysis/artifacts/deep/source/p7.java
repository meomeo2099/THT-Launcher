package defpackage;

import android.graphics.RectF;
import android.view.GestureDetector;
import android.view.MotionEvent;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class p7 extends GestureDetector.SimpleOnGestureListener {
    public final /* synthetic */ q7 a;

    public p7(q7 q7Var) {
        this.a = q7Var;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onDown(MotionEvent motionEvent) {
        motionEvent.getClass();
        return true;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final void onLongPress(MotionEvent motionEvent) {
        motionEvent.getClass();
        q7 q7Var = this.a;
        y5 y5Var = q7Var.f;
        if (y5Var != null) {
            q7Var.performHapticFeedback(0);
            y5Var.a();
        }
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onSingleTapUp(MotionEvent motionEvent) {
        motionEvent.getClass();
        q7 q7Var = this.a;
        r7 r7Var = q7Var.c;
        float x = motionEvent.getX();
        RectF rectF = q7Var.j;
        float f = x - rectF.left;
        float fWidth = rectF.width();
        if (fWidth < 1.0f) {
            fWidth = 1.0f;
        }
        float f2 = f / fWidth;
        float y = motionEvent.getY() - rectF.top;
        float fHeight = rectF.height();
        int iC = r7Var.c(f2, y / (fHeight >= 1.0f ? fHeight : 1.0f));
        if (iC >= 0) {
            q7Var.playSoundEffect(0);
            q7Var.e.b(Integer.valueOf(iC));
        }
        return iC >= 0;
    }
}
