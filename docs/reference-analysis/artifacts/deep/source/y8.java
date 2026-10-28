package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class y8 extends View {
    public final boolean a;
    public final j6 b;
    public final Integer c;
    public final Paint d;
    public int e;
    public int f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y8(Context context, boolean z, j6 j6Var, Integer num) {
        super(context);
        context.getClass();
        this.a = z;
        this.b = j6Var;
        this.c = num;
        this.d = new Paint(1);
        setClickable(true);
        setContentDescription(si.a("fan_speed", new Object[0]));
    }

    public final int getDrawHeight() {
        return this.f;
    }

    public final int getLevel() {
        return this.e;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int iE;
        canvas.getClass();
        float width = getWidth();
        float height = getHeight();
        if (width <= 0.0f || height <= 0.0f) {
            return;
        }
        int height2 = getHeight();
        int i = this.f;
        float f = (1 > i || i >= height2) ? height : i;
        float f2 = (width / 8.0f) * 0.28f;
        float f3 = (width - (7.0f * f2)) / 8.0f;
        Paint.Style style = Paint.Style.FILL;
        Paint paint = this.d;
        paint.setStyle(style);
        int i2 = 0;
        while (i2 < 8) {
            int i3 = i2 + 1;
            float f4 = (((i3 * 0.68f) / 8.0f) + 0.32f) * f;
            float f5 = (f3 + f2) * i2;
            int i4 = this.e;
            boolean z = this.a;
            if (i2 < i4) {
                Integer num = this.c;
                if (num != null) {
                    iE = num.intValue();
                } else if (this.b != null) {
                    l8.h();
                    iE = l8.a(-10233877, z);
                } else {
                    iE = l8.j(z);
                }
            } else {
                iE = l8.e(z);
            }
            paint.setColor(iE);
            float f6 = f3 * 0.35f;
            canvas.drawRoundRect(f5, height - f4, f5 + f3, height, f6, f6, paint);
            i2 = i3;
        }
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        ViewParent parent;
        motionEvent.getClass();
        j6 j6Var = this.b;
        if (j6Var == null) {
            return super.onTouchEvent(motionEvent);
        }
        if (motionEvent.getActionMasked() == 0 || motionEvent.getActionMasked() == 2) {
            setLevel(pi.s((int) (((motionEvent.getX() / getWidth()) * 8.0f) + 1.0f), 1, 8));
            ViewParent parent2 = getParent();
            if (parent2 != null) {
                parent2.requestDisallowInterceptTouchEvent(true);
            }
        }
        if (motionEvent.getActionMasked() == 1) {
            float x = motionEvent.getX();
            int width = getWidth();
            if (width < 1) {
                width = 1;
            }
            setLevel(pi.s((int) (((x / width) * 8.0f) + 1.0f), 1, 8));
            super.performClick();
            j6Var.b(Integer.valueOf(this.e));
            ViewParent parent3 = getParent();
            if (parent3 != null) {
                parent3.requestDisallowInterceptTouchEvent(false);
            }
        }
        if (motionEvent.getActionMasked() == 3 && (parent = getParent()) != null) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
        return true;
    }

    @Override // android.view.View
    public final boolean performClick() {
        super.performClick();
        return true;
    }

    public final void setDrawHeight(int i) {
        this.f = i;
        invalidate();
    }

    public final void setLevel(int i) {
        this.e = pi.s(i, 0, 8);
        invalidate();
    }
}
