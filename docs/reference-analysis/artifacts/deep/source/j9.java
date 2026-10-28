package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class j9 extends ViewGroup {
    public final boolean a;
    public final View b;
    public final boolean c;
    public final Paint d;
    public final TextView e;
    public final RectF f;
    public boolean g;
    public int h;
    public int i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j9(Context context, boolean z, View view, String str, boolean z2) {
        super(context);
        view.getClass();
        str.getClass();
        this.a = z;
        this.b = view;
        this.c = z2;
        this.d = new Paint(1);
        TextView textView = new TextView(context);
        textView.setText(str);
        textView.setTextColor(l8.j(z));
        textView.setGravity(17);
        textView.setMaxLines(2);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setTypeface(z9.c());
        this.e = textView;
        this.f = new RectF();
        l8.h();
        this.h = -10233877;
        this.i = 255;
        setWillNotDraw(false);
        setClickable(true);
        setFocusable(true);
        setContentDescription(str);
        cj cjVar = view instanceof cj ? (cj) view : null;
        if (cjVar != null) {
            cjVar.setActiveSurface(true);
        }
        addView(view);
        if (z2) {
            addView(textView);
        }
    }

    public final float a(int i, int i2) {
        boolean z = this.c;
        if (z) {
            return Math.min(i2 * (z ? 0.47f : 0.83f), i * (z ? 0.8f : 0.92f)) * 0.88f;
        }
        return Math.min(i2 * 0.64f, i * 0.76f);
    }

    public final int getAccent() {
        return this.h;
    }

    public final int getFaceAlpha() {
        return this.i;
    }

    public final View getIcon() {
        return this.b;
    }

    public final boolean getOn() {
        return this.g;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        canvas.getClass();
        RectF rectF = this.f;
        l0.i(canvas, rectF, 0.24f * rectF.width(), this.a, this.d, this.g, this.h, this.i, true, null, 512);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float f;
        int i5 = i3 - i;
        int i6 = i4 - i2;
        float fA = a(i5, i6);
        float f2 = i6;
        boolean z2 = this.c;
        float f3 = i5;
        float fMin = Math.min((z2 ? 0.47f : 0.83f) * f2, (z2 ? 0.8f : 0.92f) * f3);
        if (z2) {
            f = ((fMin - fA) / 2.0f) + (0.1f * f2);
        } else {
            f = (f2 - fA) / 2.0f;
        }
        RectF rectF = this.f;
        rectF.set((f3 - fA) / 2.0f, f, (f3 + fA) / 2.0f, fA + f);
        float fCenterX = rectF.centerX();
        View view = this.b;
        int measuredWidth = (int) (fCenterX - (view.getMeasuredWidth() / 2.0f));
        int iCenterY = (int) (rectF.centerY() - (view.getMeasuredHeight() / 2.0f));
        view.layout(measuredWidth, iCenterY, view.getMeasuredWidth() + measuredWidth, view.getMeasuredHeight() + iCenterY);
        if (z2) {
            int i7 = (int) ((f2 * 0.15f) + fMin);
            TextView textView = this.e;
            textView.layout(0, i7, i5, textView.getMeasuredHeight() + i7);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        setMeasuredDimension(size, size2);
        float fA = a(size, size2);
        View view = this.b;
        int i3 = (int) (fA * (view instanceof ImageView ? 0.7f : 0.78f));
        view.measure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), View.MeasureSpec.makeMeasureSpec(i3, 1073741824));
        float f = size2;
        float f2 = (0.105f * f) / getResources().getDisplayMetrics().scaledDensity;
        TextView textView = this.e;
        textView.setTextSize(f2);
        textView.measure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec((int) (f * 0.3f), 1073741824));
    }

    public final void setFaceAlpha(int i) {
        this.i = pi.s(i, 0, 255);
        invalidate();
    }
}
