package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class m9 extends View {
    public final Paint a;
    public float b;
    public boolean c;
    public long d;
    public long e;
    public final /* synthetic */ MainActivity f;
    public final /* synthetic */ o9 g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m9(MainActivity mainActivity, o9 o9Var) {
        super(mainActivity);
        this.f = mainActivity;
        this.g = o9Var;
        this.a = new Paint(1);
        this.d = -1L;
    }

    public final boolean getDragging() {
        return this.c;
    }

    public final float getFraction() {
        return this.b;
    }

    public final Paint getP() {
        return this.a;
    }

    public final long getTarget() {
        return this.d;
    }

    public final long getTargetAt() {
        return this.e;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        canvas.getClass();
        float width = getWidth();
        float height = getHeight();
        int i = l8.a;
        float fMin = Math.min(height, l8.m(this.f) * 12.0f);
        float height2 = (getHeight() - fMin) / 2.0f;
        float f = fMin / 2.0f;
        o9 o9Var = this.g;
        int iE = l8.e(o9Var.a);
        Paint paint = this.a;
        paint.setColor(iE);
        float f2 = (0.35f * fMin) + height2;
        float f3 = (0.65f * fMin) + height2;
        canvas.drawRoundRect(0.0f, f2, width, f3, f, f, paint);
        paint.setColor(o9Var.f);
        canvas.drawRoundRect(0.0f, f2, width * this.b, f3, f, f, paint);
        canvas.drawCircle(pi.r(this.b * width, f, width - f), getHeight() / 2.0f, fMin * (this.c ? 0.7f : 0.5f), paint);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        motionEvent.getClass();
        o9 o9Var = this.g;
        jc jcVar = o9Var.v;
        boolean z = jcVar.p;
        long j = jcVar.h;
        if (!z || j <= 0 || getWidth() <= 0) {
            return false;
        }
        float fR = pi.r(motionEvent.getX() / getWidth(), 0.0f, 1.0f);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.c = true;
            ViewParent parent = getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        } else {
            if (actionMasked == 1) {
                this.c = false;
                this.b = fR;
                invalidate();
                this.d = (long) (j * fR);
                this.e = SystemClock.elapsedRealtime();
                o9Var.e.b(Long.valueOf(this.d));
                return true;
            }
            if (actionMasked == 3) {
                this.c = false;
                invalidate();
                return true;
            }
        }
        this.b = fR;
        o9Var.k.setText(dd.c((long) (j * fR)));
        invalidate();
        return true;
    }

    public final void setDragging(boolean z) {
        this.c = z;
    }

    public final void setFraction(float f) {
        this.b = f;
    }

    public final void setTarget(long j) {
        this.d = j;
    }

    public final void setTargetAt(long j) {
        this.e = j;
    }
}
