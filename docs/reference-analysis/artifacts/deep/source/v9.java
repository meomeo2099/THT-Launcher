package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Handler;
import android.os.Looper;
import android.text.TextPaint;
import android.view.View;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class v9 extends View {
    public final Paint a;
    public final Handler b;
    public final r1 c;
    public final float d;
    public wj e;

    public v9(Context context) {
        super(context);
        this.a = new Paint(1);
        this.b = new Handler(Looper.getMainLooper());
        this.c = new r1(8, this);
        this.d = l8.m(context) * 26.0f;
        this.e = new wj((Float) null, (String) null, 0L, 15);
        setClickable(true);
        setFocusable(true);
        setContentDescription(si.a("clock_and_weather", new Object[0]));
        setOutlineProvider(new u9(this));
        setClipToOutline(true);
    }

    public final void a(Canvas canvas, float f, float f2, float f3, int i) {
        Paint paint = this.a;
        paint.setShader(null);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(i);
        float f4 = 0.2f * f3;
        float f5 = 0.05f * f3;
        canvas.drawCircle(f - f4, f2 + f5, f4, paint);
        float f6 = 0.08f * f3;
        canvas.drawCircle(f5 + f, f2 - f6, 0.27f * f3, paint);
        canvas.drawCircle((0.28f * f3) + f, f6 + f2, 0.18f * f3, paint);
        float f7 = f3 * 0.12f;
        canvas.drawRoundRect(f - (0.4f * f3), (0.02f * f3) + f2, (0.46f * f3) + f, (0.26f * f3) + f2, f7, f7, paint);
    }

    public final void b(Canvas canvas, uj ujVar, boolean z, float f, float f2, float f3) {
        canvas.getClass();
        ujVar.getClass();
        int iArgb = Color.argb(245, 255, 255, 255);
        l0 l0Var = ba.h;
        ba baVar = ba.k;
        if (baVar != null) {
            float f4 = f3 / 2.0f;
            if (baVar.b(canvas, l0.B(ujVar, z), iArgb, f - f4, f2 - f4, f3)) {
                iArgb = iArgb;
                return;
            }
        }
        iArgb = iArgb;
        switch (ujVar.ordinal()) {
            case 0:
                if (z) {
                    c(canvas, f, f2, f3);
                    return;
                } else {
                    d(canvas, f, f2, f3);
                    return;
                }
            case 1:
                float f5 = (f3 * 0.18f) + f;
                float f6 = f2 - (0.16f * f3);
                float f7 = f3 * 0.8f;
                if (z) {
                    c(canvas, f5, f6, f7);
                } else {
                    d(canvas, f5, f6, f7);
                }
                a(canvas, f - (f3 * 0.05f), f2 + (f3 * 0.1f), f3 * 0.8f, iArgb);
                return;
            case 2:
            case 8:
                a(canvas, f, f2, f3, iArgb);
                return;
            case 3:
            case 4:
            case 5:
            case 7:
                a(canvas, f, f2 - (0.14f * f3), f3 * 0.85f, iArgb);
                this.a.setStyle(Paint.Style.STROKE);
                this.a.setStrokeWidth(0.055f * f3);
                this.a.setStrokeCap(Paint.Cap.ROUND);
                int i = -1;
                for (int i2 = 2; i < i2; i2 = 2) {
                    float f8 = (i * f3 * 0.2f) + f;
                    float f9 = (0.22f * f3) + f2;
                    int iOrdinal = ujVar.ordinal();
                    Paint paint = this.a;
                    if (iOrdinal == 4) {
                        paint.setStyle(Paint.Style.FILL);
                        this.a.setColor(-1);
                        canvas.drawCircle(f8, (f3 * 0.12f) + f9, f3 * 0.05f, this.a);
                    } else if (iOrdinal != 5) {
                        paint.setStyle(Paint.Style.STROKE);
                        this.a.setColor(Color.rgb(170, 215, 255));
                        canvas.drawLine(f8, f9, f8 - (f3 * 0.06f), (f3 * 0.2f) + f9, this.a);
                    } else {
                        paint.setStyle(Paint.Style.STROKE);
                        this.a.setColor(Color.rgb(170, 215, 255));
                        canvas.drawLine(f8, f9, f8 - (f3 * 0.05f), (f3 * 0.18f) + f9, this.a);
                        this.a.setStyle(Paint.Style.FILL);
                        this.a.setColor(-1);
                        canvas.drawCircle((0.07f * f3) + f8, (f3 * 0.1f) + f9, 0.035f * f3, this.a);
                    }
                    i++;
                }
                if (ujVar == uj.h) {
                    this.a.setStyle(Paint.Style.FILL);
                    this.a.setColor(Color.rgb(255, 214, 10));
                    Path path = new Path();
                    float f10 = f3 * 0.12f;
                    path.moveTo((0.04f * f3) + f, f2 + f10);
                    float f11 = (0.34f * f3) + f2;
                    path.lineTo(f - (f3 * 0.1f), f11);
                    path.lineTo(f, f11);
                    path.lineTo(f - (f3 * 0.06f), (0.5f * f3) + f2);
                    float f12 = f + f10;
                    float f13 = (0.26f * f3) + f2;
                    path.lineTo(f12, f13);
                    path.lineTo((0.02f * f3) + f, f13);
                    path.close();
                    canvas.drawPath(path, this.a);
                    return;
                }
                return;
            case 6:
                a(canvas, f, f2 - (f3 * 0.12f), f3 * 0.85f, iArgb);
                this.a.setStyle(Paint.Style.STROKE);
                this.a.setStrokeWidth(f3 * 0.06f);
                this.a.setStrokeCap(Paint.Cap.ROUND);
                this.a.setColor(iArgb);
                for (int i3 = 0; i3 < 2; i3++) {
                    float f14 = 0.36f * f3;
                    float f15 = (((i3 * 0.13f) + 0.3f) * f3) + f2;
                    canvas.drawLine(f - f14, f15, f + f14, f15, this.a);
                }
                return;
            default:
                throw new vc();
        }
    }

    public final void c(Canvas canvas, float f, float f2, float f3) {
        Path path = new Path();
        Path.Direction direction = Path.Direction.CW;
        path.addCircle(f, f2, 0.3f * f3, direction);
        Path path2 = new Path();
        path2.addCircle((0.16f * f3) + f, f2 - (0.12f * f3), f3 * 0.26f, direction);
        path.op(path2, Path.Op.DIFFERENCE);
        Paint paint = this.a;
        paint.setShader(null);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.rgb(255, 240, 190));
        canvas.drawPath(path, paint);
    }

    public final void d(Canvas canvas, float f, float f2, float f3) {
        Paint paint = this.a;
        paint.setShader(null);
        paint.setColor(Color.rgb(255, 212, 59));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(0.05f * f3);
        paint.setStrokeCap(Paint.Cap.ROUND);
        for (int i = 0; i < 8; i++) {
            double radians = Math.toRadians(((double) i) * 45.0d);
            double d = 0.36f * f3;
            double d2 = 0.48f * f3;
            canvas.drawLine(((float) (Math.cos(radians) * d)) + f, ((float) (Math.sin(radians) * d)) + f2, ((float) (Math.cos(radians) * d2)) + f, ((float) (Math.sin(radians) * d2)) + f2, paint);
        }
        paint.setStyle(Paint.Style.FILL);
        paint.setShader(new RadialGradient(f - (0.08f * f3), f2 - (0.1f * f3), f3 * 0.24f, new int[]{Color.rgb(255, 246, 168), Color.rgb(255, 212, 59), Color.rgb(255, 179, 0)}, new float[]{0.0f, 0.6f, 1.0f}, Shader.TileMode.CLAMP));
        canvas.drawCircle(f, f2, 0.22f * f3, paint);
        paint.setShader(null);
    }

    public final wj getWeather() {
        return this.e;
    }

    @Override // android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.b.post(this.c);
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        this.b.removeCallbacks(this.c);
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        boolean z;
        boolean z2;
        int[] iArr;
        String string;
        canvas.getClass();
        float width = getWidth();
        float height = getHeight();
        if (width <= 0.0f || height <= 0.0f) {
            return;
        }
        float fMin = Math.min(width / 338.0f, height / 250.0f);
        uj ujVarH = sh.h(this.e.b);
        boolean zJ = sh.j(this.e.b);
        uj ujVar = uj.b;
        boolean z3 = zJ && (ujVarH == uj.a || ujVarH == ujVar);
        boolean zContains = x0.Y0(new uj[]{uj.c, uj.d, uj.e, uj.f, uj.g, uj.h}).contains(ujVarH);
        if (z3) {
            z = true;
            z2 = false;
            iArr = new int[]{Color.rgb(20, 32, 70), Color.rgb(38, 62, 118), Color.rgb(64, 94, 150)};
        } else {
            z = true;
            z2 = false;
            iArr = zContains ? new int[]{Color.rgb(74, 94, 118), Color.rgb(112, 132, 154), Color.rgb(150, 168, 186)} : new int[]{Color.rgb(47, 127, 224), Color.rgb(92, 168, 242), Color.rgb(140, 199, 248)};
        }
        int[] iArr2 = iArr;
        RectF rectF = new RectF(0.0f, 0.0f, width, height);
        Path path = new Path();
        float f = this.d;
        path.addRoundRect(rectF, f, f, Path.Direction.CW);
        canvas.save();
        canvas.clipPath(path);
        Paint.Style style = Paint.Style.FILL;
        Paint paint = this.a;
        paint.setStyle(style);
        paint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, height, iArr2, new float[]{0.0f, 0.6f, 1.0f}, Shader.TileMode.CLAMP));
        canvas.drawRect(rectF, paint);
        paint.setShader(null);
        paint.setColor(Color.argb(z3 ? 60 : 225, 255, 255, 255));
        float f2 = fMin * 40.0f;
        canvas.drawOval((-10.0f) * fMin, height - f2, 110.0f * fMin, (16.0f * fMin) + height, paint);
        float f3 = fMin * 18.0f;
        canvas.drawOval(fMin * 60.0f, height - (62.0f * fMin), fMin * 210.0f, height + f3, paint);
        canvas.drawOval(fMin * 180.0f, height - (36.0f * fMin), fMin * 290.0f, (12.0f * fMin) + height, paint);
        TextPaint textPaintY = pi.Y(-1, Math.min(64.0f * fMin, 0.3f * height), z);
        int i = l8.a;
        String str = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        str.getClass();
        float f4 = 24.0f * fMin;
        float f5 = 20.0f * fMin;
        k8.F(canvas, str, f4, c.a(textPaintY, 0.9f, f5), textPaintY);
        TextPaint textPaintY2 = pi.Y(-1, Math.min(19.0f * fMin, 0.08f * height), z2);
        float fA = c.a(textPaintY2, 1.8f, c.a(textPaintY, 0.9f, f5));
        float f6 = 26.0f * fMin;
        k8.F(canvas, pi.y(l8.c(), textPaintY2, 0.58f * width), f6, fA, textPaintY2);
        float fMin2 = Math.min(80.0f * fMin, 0.34f * height);
        float f7 = width - f4;
        float f8 = fMin2 / 2.0f;
        float f9 = f7 - f8;
        float f10 = f3 + f8;
        wj wjVar = this.e;
        if (wjVar.a != null) {
            b(canvas, ujVarH, sh.j(wjVar.b), f9, f10, fMin2);
            TextPaint textPaintY3 = pi.Y(-1, Math.min(f2, 0.17f * height), true);
            Paint.Align align = Paint.Align.RIGHT;
            textPaintY3.setTextAlign(align);
            Float f11 = this.e.a;
            if (f11 != null) {
                int iRound = Math.round(f11.floatValue());
                StringBuilder sb = iRound > 0 ? new StringBuilder("+") : new StringBuilder();
                sb.append(iRound);
                sb.append("°");
                string = sb.toString();
            } else {
                string = "—";
            }
            float f12 = f10 + f8;
            k8.F(canvas, string, f7, c.a(textPaintY3, 1.05f, f12), textPaintY3);
            TextPaint textPaintY4 = pi.Y(-1, Math.min(17.0f * fMin, height * 0.075f), false);
            textPaintY4.setTextAlign(align);
            k8.F(canvas, pi.y(this.e.b(), textPaintY4, width * 0.45f), f7, c.a(textPaintY4, 1.5f, c.a(textPaintY3, 1.05f, f12)), textPaintY4);
        } else {
            b(canvas, ujVar, false, f9, f10, fMin2 * 0.8f);
            String str2 = this.e.d;
            if (str2 != null) {
                TextPaint textPaintY5 = pi.Y(-1, Math.min(15.0f * fMin, height * 0.065f), false);
                k8.F(canvas, pi.y(str2, textPaintY5, width - (fMin * 50.0f)), f6, c.a(textPaintY5, 2.2f, fA), textPaintY5);
            }
        }
        canvas.restore();
    }

    public final void setWeather(wj wjVar) {
        String string;
        wjVar.getClass();
        this.e = wjVar;
        List list = si.a;
        Float f = wjVar.a;
        if (f != null) {
            int iRound = Math.round(f.floatValue());
            StringBuilder sb = iRound > 0 ? new StringBuilder("+") : new StringBuilder();
            sb.append(iRound);
            sb.append("°");
            string = sb.toString();
        } else {
            string = "—";
        }
        setContentDescription(si.a("clock_and_weather_0_1", string, wjVar.b()));
        invalidate();
    }
}
