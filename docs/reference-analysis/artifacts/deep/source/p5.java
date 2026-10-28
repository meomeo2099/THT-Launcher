package defpackage;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class p5 extends ViewGroup {
    public final boolean a;
    public final Paint b;
    public final int c;

    public p5(MainActivity mainActivity, boolean z, ArrayList arrayList, boolean z2) {
        super(mainActivity);
        this.a = z2;
        this.b = new Paint(1);
        this.c = z ? Color.argb(34, 255, 255, 255) : Color.argb(22, 15, 30, 60);
        setWillNotDraw(false);
        setContentDescription(si.a("folder", new Object[0]));
        if (z2) {
            setBackground(new w6(z));
            setClickable(true);
            setFocusable(true);
        }
        for (View view : i3.H0(arrayList, 4)) {
            view.setClickable(false);
            view.setFocusable(false);
            view.setLongClickable(false);
            view.setImportantForAccessibility(2);
            addView(view);
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        canvas.getClass();
        super.onDraw(canvas);
        float fMin = Math.min(getWidth(), getHeight()) * (this.a ? 0.74f : 0.96f);
        Float fValueOf = Float.valueOf((getWidth() - fMin) / 2.0f);
        Float fValueOf2 = Float.valueOf((getHeight() - fMin) / 2.0f);
        Float fValueOf3 = Float.valueOf(fMin);
        float fFloatValue = fValueOf.floatValue();
        float fFloatValue2 = fValueOf2.floatValue();
        float fFloatValue3 = fValueOf3.floatValue() / 2.0f;
        float f = 0.08f * fFloatValue3;
        Paint.Style style = Paint.Style.FILL;
        Paint paint = this.b;
        paint.setStyle(style);
        paint.setColor(this.c);
        for (int i = 0; i < 4; i++) {
            float f2 = ((i % 2) * fFloatValue3) + fFloatValue;
            float f3 = ((i / 2) * fFloatValue3) + fFloatValue2;
            float f4 = fFloatValue3 * 0.26f;
            canvas.drawRoundRect(f2 + f, f3 + f, (f2 + fFloatValue3) - f, (f3 + fFloatValue3) - f, f4, f4, paint);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int i6 = i4 - i2;
        float fMin = Math.min(i5, i6) * (this.a ? 0.74f : 0.96f);
        float f = (i5 - fMin) / 2.0f;
        float f2 = (i6 - fMin) / 2.0f;
        float f3 = fMin * 0.5f;
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            float f4 = f3 / 2.0f;
            float f5 = ((i7 % 2) * f3) + f + f4;
            float f6 = ((i7 / 2) * f3) + f2 + f4;
            childAt.layout((int) (f5 - (childAt.getMeasuredWidth() / 2.0f)), (int) (f6 - (childAt.getMeasuredHeight() / 2.0f)), (int) ((childAt.getMeasuredWidth() / 2.0f) + f5), (int) ((childAt.getMeasuredHeight() / 2.0f) + f6));
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        setMeasuredDimension(size, size2);
        int iMin = (int) (Math.min(size, size2) * (this.a ? 0.74f : 0.96f) * 0.35f);
        if (iMin < 1) {
            iMin = 1;
        }
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            getChildAt(i3).measure(View.MeasureSpec.makeMeasureSpec(iMin, 1073741824), View.MeasureSpec.makeMeasureSpec(iMin, 1073741824));
        }
    }
}
