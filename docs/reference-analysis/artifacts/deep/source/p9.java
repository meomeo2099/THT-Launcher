package defpackage;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import java.util.Map;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class p9 extends View {
    public final String a;
    public final float b;
    public final y5 c;
    public final j6 d;
    public final boolean e;
    public final Paint f;
    public final Handler g;
    public Float h;
    public int i;
    public long j;
    public final r1 k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p9(MainActivity mainActivity, String str, String str2, float f, y5 y5Var, j6 j6Var, boolean z) {
        super(mainActivity);
        str2.getClass();
        this.a = str;
        this.b = f;
        this.c = y5Var;
        this.d = j6Var;
        this.e = z;
        this.f = new Paint(1);
        this.g = new Handler(Looper.getMainLooper());
        this.k = new r1(7, this);
        setClickable(true);
        setFocusable(true);
        setContentDescription(str2);
    }

    @Override // android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.g.post(this.k);
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        this.g.removeCallbacksAndMessages(null);
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        canvas.getClass();
        float width = getWidth();
        float height = getHeight();
        if (width <= 0.0f || height <= 0.0f) {
            return;
        }
        float fMin = Math.min(0.42f * width, height / 2.0f);
        Path path = new Path();
        float f = width - 1.0f;
        float f2 = height - 1.0f;
        path.addRoundRect(new RectF(1.0f, 1.0f, f, f2), fMin, fMin, Path.Direction.CW);
        Paint.Style style = Paint.Style.FILL;
        Paint paint = this.f;
        paint.setStyle(style);
        paint.setShader(null);
        l0.i(canvas, new RectF(1.0f, 1.0f, f, f2), fMin, true, paint, false, 0, 0, false, this, 480);
        Float f3 = this.h;
        if (f3 == null) {
            f3 = (Float) this.c.a();
        }
        float fR = f3 != null ? pi.r((f3.floatValue() - 0.0f) / (this.b - 0.0f), 0.0f, 1.0f) : 0.0f;
        canvas.save();
        canvas.clipPath(path);
        float f4 = (1.0f - fR) * height;
        paint.setColor(-1);
        canvas.drawRect(0.0f, f4, width, height, paint);
        paint.setShader(null);
        canvas.restore();
        float fMin2 = Math.min(height * 0.12f, 0.78f * width);
        float f5 = (height - fMin2) - (0.05f * height);
        Map map = i9.a;
        int i = l8.a;
        float f6 = (width - fMin2) / 2.0f;
        i9.a(canvas, this.a, paint, Color.rgb(232, 235, 240), f6, f5, fMin2, 2.6f);
        canvas.save();
        canvas.clipRect(0.0f, f4, width, height);
        i9.a(canvas, this.a, paint, Color.rgb(48, 52, 59), f6, f5, fMin2, 2.6f);
        canvas.restore();
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0016  */
    /* JADX WARN: Code duplicated, block: B:17:0x0040  */
    /* JADX WARN: Code duplicated, block: B:20:0x004a  */
    /* JADX WARN: Code duplicated, block: B:22:0x004e  */
    /* JADX WARN: Code duplicated, block: B:24:0x0054  */
    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        ViewParent parent;
        float fR;
        long jUptimeMillis;
        ViewParent parent2;
        Float f;
        motionEvent.getClass();
        int actionMasked = motionEvent.getActionMasked();
        j6 j6Var = this.d;
        if (actionMasked == 0) {
            parent = getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            fR = ((this.b - 0.0f) * pi.r(1.0f - (motionEvent.getY() / getHeight()), 0.0f, 1.0f)) + 0.0f;
            this.h = Float.valueOf(fR);
            this.i++;
            invalidate();
            jUptimeMillis = SystemClock.uptimeMillis();
            if (this.e && jUptimeMillis - this.j > 250) {
                this.j = jUptimeMillis;
                j6Var.b(Float.valueOf(fR));
            }
        } else if (actionMasked == 1) {
            if (motionEvent.getActionMasked() == 1 && (f = this.h) != null) {
                j6Var.b(Float.valueOf(f.floatValue()));
            }
            this.g.postDelayed(new t0(this.i, this, 4), 1500L);
            parent2 = getParent();
            if (parent2 != null) {
                parent2.requestDisallowInterceptTouchEvent(false);
            }
            if (motionEvent.getActionMasked() == 1) {
                super.performClick();
                return true;
            }
        } else if (actionMasked == 2) {
            parent = getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            fR = ((this.b - 0.0f) * pi.r(1.0f - (motionEvent.getY() / getHeight()), 0.0f, 1.0f)) + 0.0f;
            this.h = Float.valueOf(fR);
            this.i++;
            invalidate();
            jUptimeMillis = SystemClock.uptimeMillis();
            if (this.e) {
                this.j = jUptimeMillis;
                j6Var.b(Float.valueOf(fR));
            }
        } else if (actionMasked == 3) {
            if (motionEvent.getActionMasked() == 1) {
                j6Var.b(Float.valueOf(f.floatValue()));
            }
            this.g.postDelayed(new t0(this.i, this, 4), 1500L);
            parent2 = getParent();
            if (parent2 != null) {
                parent2.requestDisallowInterceptTouchEvent(false);
            }
            if (motionEvent.getActionMasked() == 1) {
                super.performClick();
                return true;
            }
        }
        return true;
    }

    @Override // android.view.View
    public final boolean performClick() {
        super.performClick();
        return true;
    }
}
