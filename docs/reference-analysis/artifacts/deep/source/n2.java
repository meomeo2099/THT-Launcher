package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.View;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class n2 extends View {
    public static final int f = Color.rgb(10, 132, 255);
    public final boolean a;
    public final boolean b;
    public final Paint c;
    public final Path d;
    public final RectF e;

    public n2(y5 y5Var, Context context, boolean z, boolean z2) {
        super(context);
        this.a = z;
        this.b = z2;
        this.c = new Paint(1);
        this.d = new Path();
        this.e = new RectF();
        int i = 0;
        setContentDescription(si.a("camera360", new Object[0]));
        if (z2) {
            setBackground(new w6(z));
            setClickable(true);
            setFocusable(true);
            setOnClickListener(new m2(y5Var, i));
        }
    }

    public final void a(Canvas canvas, float f2, float f3, float f4, int i, Paint.Align align) {
        Paint.Style style = Paint.Style.FILL;
        Paint paint = this.c;
        paint.setStyle(style);
        paint.setColor(i);
        paint.setTextSize(f4);
        paint.setTextAlign(align);
        paint.setTypeface(Typeface.create("sans-serif-medium", 1));
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        canvas.drawText("360°", f2, f3 - ((fontMetrics.ascent + fontMetrics.descent) / 2.0f), paint);
    }

    public final void b(Canvas canvas, float f2, float f3, float f4, int i) {
        int i2;
        int i3;
        int i4;
        l0 l0Var = ba.h;
        ba baVar = ba.k;
        if (baVar != null) {
            float f5 = f4 / 2.0f;
            if (baVar.a(canvas, "camera360", f, f2 - f5, f3 - f5, f4)) {
                return;
            }
        }
        float f6 = 0.42f * f4;
        this.c.setStyle(Paint.Style.STROKE);
        this.c.setStrokeCap(Paint.Cap.ROUND);
        this.c.setStrokeJoin(Paint.Join.ROUND);
        this.c.setStrokeWidth(0.075f * f4);
        this.c.setColor(f);
        this.e.set(f2 - f6, f3 - f6, f2 + f6, f3 + f6);
        canvas.drawArc(this.e, -50.0f, 290.0f, false, this.c);
        double radians = Math.toRadians(240.0d);
        float fCos = (((float) Math.cos(radians)) * f6) + f2;
        float fSin = (f6 * ((float) Math.sin(radians))) + f3;
        float f7 = -((float) Math.sin(radians));
        float fCos2 = (float) Math.cos(radians);
        float fCos3 = (float) Math.cos(radians);
        float fSin2 = (float) Math.sin(radians);
        float f8 = 0.13f * f4;
        this.d.reset();
        float f9 = f7 * f8;
        float f10 = fCos - f9;
        float f11 = fCos3 * f8 * 0.8f;
        float f12 = fCos2 * f8;
        float f13 = fSin - f12;
        float f14 = fSin2 * f8 * 0.8f;
        this.d.moveTo(f10 + f11, f13 + f14);
        this.d.lineTo((f9 * 0.15f) + fCos, (f12 * 0.15f) + fSin);
        this.d.lineTo(f10 - f11, f13 - f14);
        canvas.drawPath(this.d, this.c);
        float f15 = 0.27f * f4;
        float f16 = f4 * 0.46f;
        float f17 = f2 - (f15 / 2.0f);
        float f18 = f3 - (f16 / 2.0f);
        this.c.setStyle(Paint.Style.FILL);
        this.c.setColor(i);
        float f19 = f15 * 0.1f;
        float f20 = f17 - f19;
        float f21 = (f16 * 0.16f) + f18;
        float f22 = (0.04f * f15) + f17;
        float f23 = (0.34f * f16) + f18;
        float f24 = f15 * 0.06f;
        canvas.drawRoundRect(f20, f21, f22, f23, f24, f24, this.c);
        float f25 = (0.96f * f15) + f17;
        float f26 = (1.1f * f15) + f17;
        canvas.drawRoundRect(f25, f21, f26, f23, f24, f24, this.c);
        float f27 = (0.66f * f16) + f18;
        float f28 = (f16 * 0.84f) + f18;
        canvas.drawRoundRect(f20, f27, f22, f28, f24, f24, this.c);
        canvas.drawRoundRect(f25, f27, f26, f28, f24, f24, this.c);
        float f29 = f15 * 0.32f;
        canvas.drawRoundRect(f17, f18, f17 + f15, f18 + f16, f29, f29, this.c);
        Paint paint = this.c;
        if (this.a) {
            i2 = 32;
            i3 = 40;
            i4 = 28;
        } else {
            i2 = 240;
            i3 = 246;
            i4 = 236;
        }
        paint.setColor(Color.rgb(i4, i2, i3));
        canvas.drawRoundRect((0.16f * f15) + f17, (0.22f * f16) + f18, (0.84f * f15) + f17, (0.38f * f16) + f18, f19, f19, this.c);
        float f30 = f15 * 0.08f;
        canvas.drawRoundRect((0.2f * f15) + f17, (0.72f * f16) + f18, (0.8f * f15) + f17, (f16 * 0.83f) + f18, f30, f30, this.c);
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        canvas.getClass();
        super.onDraw(canvas);
        if (getWidth() <= 0 || getHeight() <= 0) {
            return;
        }
        int i = l8.a;
        int iJ = l8.j(this.a);
        if (!this.b) {
            b(canvas, getWidth() / 2.0f, getHeight() / 2.0f, Math.min(getWidth(), getHeight()), iJ);
            return;
        }
        if (getWidth() > getHeight() * 1.6f) {
            b(canvas, getHeight() * 0.55f, getHeight() / 2.0f, getHeight() * 0.7f, iJ);
            a(canvas, getHeight() * 1.05f, getHeight() / 2.0f, getHeight() * 0.34f, iJ, Paint.Align.LEFT);
        } else {
            b(canvas, getWidth() / 2.0f, getHeight() * 0.42f, Math.min(getWidth(), getHeight()) * 0.6f, iJ);
            a(canvas, getWidth() / 2.0f, getHeight() * 0.86f, Math.min(getWidth(), getHeight()) * 0.17f, iJ, Paint.Align.CENTER);
        }
    }
}
