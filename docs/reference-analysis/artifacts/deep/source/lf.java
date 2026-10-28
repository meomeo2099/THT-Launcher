package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class lf extends View {
    public final Paint a;
    public final Path b;
    public final float c;

    public lf(y5 y5Var, Context context, boolean z, boolean z2) {
        super(context);
        this.a = new Paint(1);
        this.b = new Path();
        this.c = z2 ? 0.62f : 1.0f;
        setClickable(true);
        setFocusable(true);
        setContentDescription(si.a("screensaver_start", new Object[0]));
        setOnClickListener(new m2(y5Var, 12));
        if (z2) {
            setBackground(new w6(z));
        }
    }

    public static final void a(lf lfVar, Canvas canvas, float f, float f2, float f3) {
        Paint paint = lfVar.a;
        paint.setStrokeWidth(Math.max(1.5f, 0.2f * f3));
        Path path = lfVar.b;
        path.reset();
        path.moveTo(f, f2);
        float f4 = f + f3;
        path.lineTo(f4, f2);
        float f5 = f2 + f3;
        path.lineTo(f, f5);
        path.lineTo(f4, f5);
        canvas.drawPath(path, paint);
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        canvas.getClass();
        super.onDraw(canvas);
        float fMin = Math.min(getWidth(), getHeight()) * this.c;
        if (fMin <= 0.0f) {
            return;
        }
        float width = getWidth() / 2.0f;
        float height = getHeight() / 2.0f;
        l0 l0Var = ba.h;
        ba baVar = ba.k;
        if (baVar != null) {
            float f = fMin / 2.0f;
            if (baVar.a(canvas, "screensaver", Color.rgb(88, 86, 214), width - f, height - f, fMin)) {
                return;
            }
        }
        float f2 = fMin * 0.82f;
        float f3 = fMin * 0.56f;
        float f4 = width - (f2 / 2.0f);
        float f5 = height - (fMin * 0.4f);
        Paint paint = this.a;
        Paint.Style style = Paint.Style.FILL;
        paint.setStyle(style);
        this.a.setColor(Color.rgb(88, 86, 214));
        float f6 = f5 + f3;
        float f7 = fMin * 0.09f;
        canvas.drawRoundRect(f4, f5, f4 + f2, f6, f7, f7, this.a);
        float f8 = fMin * 0.05f;
        canvas.drawRect(width - f8, f6, width + f8, (fMin * 0.14f) + f6, this.a);
        float f9 = fMin * 0.2f;
        float f10 = fMin * 0.04f;
        canvas.drawRoundRect(width - f9, (0.12f * fMin) + f6, width + f9, f6 + f9, f10, f10, this.a);
        this.a.setColor(-1);
        this.a.setStyle(Paint.Style.STROKE);
        this.a.setStrokeCap(Paint.Cap.ROUND);
        this.a.setStrokeJoin(Paint.Join.ROUND);
        a(this, canvas, (0.14f * f2) + f4, (0.46f * f3) + f5, 0.36f * f3);
        a(this, canvas, (0.5f * f2) + f4, (0.3f * f3) + f5, 0.25f * f3);
        a(this, canvas, (f2 * 0.73f) + f4, (0.16f * f3) + f5, 0.17f * f3);
        this.a.setStyle(style);
    }
}
