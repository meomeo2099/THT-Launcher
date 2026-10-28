package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class t extends ViewGroup implements x8 {
    public static final /* synthetic */ int o = 0;
    public final boolean a;
    public final y5 b;
    public final y5 c;
    public final j6 d;
    public final cj e;
    public float f;
    public float g;
    public final Paint h;
    public final TextPaint i;
    public final TextPaint j;
    public Float k;
    public boolean l;
    public Integer m;
    public boolean n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(Context context, boolean z, boolean z2, b2 b2Var, b2 b2Var2, v1 v1Var) {
        super(context);
        context.getClass();
        this.a = z;
        this.b = b2Var;
        this.c = b2Var2;
        this.d = v1Var;
        cj cjVar = new cj(context, "airflow");
        int i = 0;
        cjVar.setHalo(false);
        cjVar.setNightMode(z);
        cjVar.setEnabledState(null);
        cjVar.setFlowInk(Integer.valueOf(z ? -1 : -16777216));
        this.e = cjVar;
        this.h = new Paint(1);
        this.i = pi.Y(l8.j(z), 1.0f, false);
        this.j = pi.Y(l8.j(z), 1.0f, false);
        if (z2) {
            setBackground(new n8(z, l8.m(context) * 24.0f));
        }
        setWillNotDraw(false);
        addView(cjVar);
        if (b2Var != null || b2Var2 != null || v1Var != null) {
            setClickable(true);
            setFocusable(true);
            setOnClickListener(new s(i, this));
        }
        d(null, 1);
    }

    public static oi b(float f, float f2) {
        float fMin = Math.min(f, f2) * 0.09f;
        float f3 = f - fMin;
        RectF rectF = new RectF(fMin, 0.64f * f2, f3, f2 - fMin);
        return new oi(new RectF(fMin, fMin, f3, rectF.top - (0.6f * fMin)), rectF, Float.valueOf(fMin));
    }

    public static yc c(RectF rectF) {
        float fHeight = rectF.height() / 2.0f;
        float f = 0.55f * fHeight;
        return new yc(Float.valueOf((fHeight * 0.45f) + rectF.left + f + (rectF.height() * 0.66f)), Float.valueOf(rectF.right - f));
    }

    @Override // defpackage.x8
    public final int a(int i) {
        return (int) (i * 1.75f);
    }

    public final void d(v2 v2Var, int i) {
        Float f;
        Integer numA;
        Map map;
        Integer num;
        List list = z2.a;
        Map map2 = v2Var != null ? v2Var.f : null;
        this.n = (map2 == null || (num = (Integer) map2.get(268501248)) == null || num.intValue() == 0) ? false : true;
        this.l = i == 4;
        if (v2Var == null || (map = v2Var.a) == null || (f = (Float) map.get(Integer.valueOf(i))) == null || Math.abs(f.floatValue()) > Float.MAX_VALUE) {
            f = null;
        }
        this.k = f;
        this.m = z2.c(map2 != null ? (Integer) map2.get(268566784) : null);
        if (this.n) {
            numA = z2.a(map2 != null ? (Integer) map2.get(268894464) : null);
        } else {
            numA = null;
        }
        cj cjVar = this.e;
        cjVar.setBlowMode(numA);
        cjVar.setEnabledState(v2Var != null ? Boolean.valueOf(this.n) : null);
        String strA = si.a("anton", new Object[0]);
        Float f2 = this.k;
        setContentDescription(strA + ": " + (f2 != null ? z2.d(f2.floatValue()) : "—"));
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        motionEvent.getClass();
        if (motionEvent.getActionMasked() == 0 || motionEvent.getActionMasked() == 1) {
            this.f = motionEvent.getX();
            this.g = motionEvent.getY();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    /* JADX WARN: Code duplicated, block: B:41:0x024f  */
    /* JADX WARN: Code duplicated, block: B:42:0x0251  */
    /* JADX WARN: Code duplicated, block: B:44:0x0255  */
    /* JADX WARN: Code duplicated, block: B:45:0x025a  */
    /* JADX WARN: Code duplicated, block: B:47:0x025d  */
    /* JADX WARN: Code duplicated, block: B:50:0x0261  */
    /* JADX WARN: Code duplicated, block: B:52:0x026e  */
    /* JADX WARN: Code duplicated, block: B:53:0x0273  */
    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        String str;
        Canvas canvas2;
        float fFloatValue;
        float f;
        float f2;
        float fHeight;
        float fCenterY;
        Integer num;
        int iIntValue;
        int i;
        boolean z;
        int iE;
        canvas.getClass();
        super.onDraw(canvas);
        float width = getWidth();
        float height = getHeight();
        if (width <= 0.0f || height <= 0.0f) {
            return;
        }
        oi oiVarB = b(width, height);
        RectF rectF = (RectF) oiVarB.a;
        RectF rectF2 = (RectF) oiVarB.b;
        float fFloatValue2 = ((Number) oiVarB.c).floatValue();
        boolean z2 = this.n;
        float right = (1.1f * fFloatValue2) + this.e.getRight();
        this.h.setStyle(Paint.Style.FILL);
        Paint paint = this.h;
        int i2 = l8.a;
        paint.setColor(l8.e(this.a));
        canvas.drawRect(right, (rectF.height() * 0.12f) + rectF.top, Math.max(1.0f, height * 0.006f) + right, rectF.bottom - (rectF.height() * 0.12f), this.h);
        Float f3 = this.k;
        if (f3 != null) {
            float fFloatValue3 = f3.floatValue();
            List list = si.a;
            Locale locale = si.b.b;
            List list2 = z2.a;
            str = String.format(locale, "%.1f", Arrays.copyOf(new Object[]{Float.valueOf(z2.h(fFloatValue3))}, 1));
        } else {
            str = "—";
        }
        String str2 = this.k == null ? "" : "°C";
        boolean z3 = this.a;
        int iK = !z2 ? l8.k(z3) : l8.j(z3);
        this.i.setColor(iK);
        this.j.setColor(iK);
        float f4 = (rectF.right - right) - (1.4f * fFloatValue2);
        this.i.setTextSize(rectF.height() * 0.62f);
        this.j.setTextSize(this.i.getTextSize() * 0.42f);
        float fA = c.a(this.i, 0.04f, this.j.measureText(str2) + this.i.measureText(str));
        if (fA > f4 && fA > 0.0f) {
            float f5 = f4 / fA;
            TextPaint textPaint = this.i;
            textPaint.setTextSize(textPaint.getTextSize() * f5);
            TextPaint textPaint2 = this.j;
            textPaint2.setTextSize(textPaint2.getTextSize() * f5);
        }
        float fA2 = (fFloatValue2 * 0.2f) + (((rectF.right - right) - c.a(this.i, 0.04f, this.j.measureText(str2) + this.i.measureText(str))) / 2.0f) + right;
        float fCenterY2 = rectF.centerY() - ((this.i.descent() + this.i.ascent()) / 2.0f);
        canvas.drawText(str, fA2, fCenterY2, this.i);
        canvas.drawText(str2, c.a(this.i, 0.04f, this.i.measureText(str) + fA2), ((this.i.ascent() * 0.42f) + fCenterY2) - (this.j.ascent() * 0.9f), this.j);
        if (this.l) {
            this.j.setColor(l8.k(this.a));
            String strA = si.a("climate_passenger_zone", new Object[0]);
            canvas.drawText(strA, (((rectF.right - right) - this.j.measureText(strA)) / 2.0f) + right, rectF.top - (this.j.ascent() * 0.9f), this.j);
        }
        float fHeight2 = rectF2.height() / 2.0f;
        this.h.setColor(l8.e(this.a));
        canvas.drawRoundRect(rectF2, fHeight2, fHeight2, this.h);
        float fHeight3 = rectF2.height() * 0.66f;
        float f6 = (fHeight2 * 0.55f) + rectF2.left;
        boolean z4 = this.a;
        int iK2 = !z2 ? l8.k(z4) : l8.j(z4);
        l0 l0Var = ba.h;
        ba baVar = ba.k;
        if (baVar != null) {
            canvas2 = canvas;
            if (!baVar.a(canvas2, "fan", iK2, f6, rectF2.centerY() - (fHeight3 / 2.0f), fHeight3)) {
            }
            yc ycVarC = c(rectF2);
            fFloatValue = ((Number) ycVarC.a).floatValue();
            float fFloatValue4 = ((Number) ycVarC.b).floatValue() - fFloatValue;
            f = (fFloatValue4 / 8.0f) * 0.14f;
            f2 = (fFloatValue4 - (7.0f * f)) / 8.0f;
            fHeight = rectF2.height() * 0.52f;
            fCenterY = rectF2.centerY() - (fHeight / 2.0f);
            if (this.n) {
                num = this.m;
                if (num != null) {
                    iIntValue = num.intValue();
                } else {
                    iIntValue = 0;
                }
                if (iIntValue > 8) {
                    iIntValue = 8;
                }
                i = iIntValue;
            } else {
                i = 0;
            }
            for (int i3 = 0; i3 < 8; i3++) {
                float f7 = ((f2 + f) * i3) + fFloatValue;
                Paint paint2 = this.h;
                int i4 = l8.a;
                z = this.a;
                if (i3 < i) {
                    iE = l8.k(z);
                } else {
                    iE = l8.e(z);
                }
                paint2.setColor(iE);
                float f8 = fHeight * 0.22f;
                canvas.drawRoundRect(f7, fCenterY, f7 + f2, fCenterY + fHeight, f8, f8, this.h);
            }
        }
        canvas2 = canvas;
        this.h.setColor(iK2);
        canvas2.drawCircle((fHeight3 / 2.0f) + f6, rectF2.centerY(), fHeight3 * 0.3f, this.h);
        yc ycVarC2 = c(rectF2);
        fFloatValue = ((Number) ycVarC2.a).floatValue();
        float fFloatValue5 = ((Number) ycVarC2.b).floatValue() - fFloatValue;
        f = (fFloatValue5 / 8.0f) * 0.14f;
        f2 = (fFloatValue5 - (7.0f * f)) / 8.0f;
        fHeight = rectF2.height() * 0.52f;
        fCenterY = rectF2.centerY() - (fHeight / 2.0f);
        if (this.n) {
            i = 0;
        } else {
            num = this.m;
            if (num != null) {
                iIntValue = num.intValue();
            } else {
                iIntValue = 0;
            }
            if (iIntValue > 8) {
                iIntValue = 8;
            }
            i = iIntValue;
        }
        while (i3 < 8) {
            float f9 = ((f2 + f) * i3) + fFloatValue;
            Paint paint3 = this.h;
            int i5 = l8.a;
            z = this.a;
            if (i3 < i) {
                iE = l8.k(z);
            } else {
                iE = l8.e(z);
            }
            paint3.setColor(iE);
            float f10 = fHeight * 0.22f;
            canvas.drawRoundRect(f9, fCenterY, f9 + f2, fCenterY + fHeight, f10, f10, this.h);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        oi oiVarB = b(i3 - i, i4 - i2);
        RectF rectF = (RectF) oiVarB.a;
        float fFloatValue = ((Number) oiVarB.c).floatValue();
        int iMin = (int) Math.min(rectF.height(), rectF.width() * 0.38f);
        int i5 = (int) ((fFloatValue * 0.4f) + rectF.left);
        int iCenterY = (int) (rectF.centerY() - (iMin / 2.0f));
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iMin, 1073741824);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(iMin, 1073741824);
        cj cjVar = this.e;
        cjVar.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        cjVar.layout(i5, iCenterY, i5 + iMin, iMin + iCenterY);
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
    }
}
