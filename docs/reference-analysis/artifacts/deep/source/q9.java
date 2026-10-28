package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.view.View;
import java.util.Map;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class q9 extends View {
    public final boolean a;
    public final Paint b;
    public String c;
    public String d;
    public String e;
    public String f;
    public int g;
    public Float h;

    public q9(MainActivity mainActivity, boolean z) {
        super(mainActivity);
        this.a = z;
        this.b = new Paint(1);
        this.c = "";
        this.d = "—";
        this.e = "";
        this.f = "carfront";
        this.g = l8.f();
        setBackground(new n8(z, l8.m(mainActivity) * 26.0f));
        setClickable(true);
        setFocusable(true);
    }

    public final void a(String str, String str2, String str3, String str4, int i, Float f) {
        str.getClass();
        str2.getClass();
        str3.getClass();
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = str4;
        this.g = i;
        this.h = f;
        setContentDescription(str + ": " + str2 + " " + str3);
        invalidate();
    }

    public final int getBubble() {
        return this.g;
    }

    public final String getIcon() {
        return this.f;
    }

    public final Float getLevel() {
        return this.h;
    }

    public final String getTitle() {
        return this.c;
    }

    public final String getUnit() {
        return this.e;
    }

    public final String getValue() {
        return this.d;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        canvas.getClass();
        super.onDraw(canvas);
        float width = getWidth();
        float height = getHeight();
        if (width <= 0.0f || height <= 0.0f) {
            return;
        }
        float fMin = Math.min(width / 173.0f, height / 101.0f);
        float f = fMin * 15.0f;
        float f2 = fMin * 32.0f;
        Paint.Style style = Paint.Style.FILL;
        Paint paint = this.b;
        paint.setStyle(style);
        paint.setColor(this.g);
        float f3 = f2 / 2.0f;
        float f4 = f + f3;
        canvas.drawCircle(f4, f4, f3, paint);
        Map map = i9.a;
        float f5 = (0.2f * f2) + f;
        i9.a(canvas, this.f, paint, -1, f5, f5, 0.6f * f2, 2.2f);
        boolean z = this.a;
        TextPaint textPaintY = pi.Y(l8.k(z), 14.0f * fMin, false);
        float f6 = width - (f * 2.0f);
        float f7 = 8.0f * fMin;
        String strY = pi.y(this.c, textPaintY, (f6 - f2) - f7);
        float f8 = f2 + f;
        k8.F(canvas, strY, (9.0f * fMin) + f8, c.a(textPaintY, 0.36f, f4), textPaintY);
        TextPaint textPaintY2 = pi.Y(l8.j(z), 25.0f * fMin, true);
        textPaintY2.setFakeBoldText(true);
        float fA = c.a(textPaintY2, 1.05f, f8);
        k8.F(canvas, this.d, f, fA, textPaintY2);
        k8.F(canvas, this.e, (4.0f * fMin) + textPaintY2.measureText(this.d) + f, fA, pi.Y(l8.j(z), 16.0f * fMin, true));
        Float f9 = this.h;
        if (f9 != null) {
            float fFloatValue = f9.floatValue();
            float f10 = height - (fMin * 13.0f);
            paint.setStyle(style);
            paint.setShader(null);
            paint.setColor(l8.e(z));
            float f11 = f10 - f7;
            float f12 = f7 / 2.0f;
            canvas.drawRoundRect(f, f11, width - f, f10, f12, f12, paint);
            paint.setColor(l8.a);
            canvas.drawRoundRect(f, f11, (pi.r(fFloatValue, 0.0f, 1.0f) * f6) + f, f10, f12, f12, paint);
        }
    }

    public final void setBubble(int i) {
        this.g = i;
    }

    public final void setIcon(String str) {
        str.getClass();
        this.f = str;
    }

    public final void setLevel(Float f) {
        this.h = f;
    }

    public final void setTitle(String str) {
        str.getClass();
        this.c = str;
    }

    public final void setUnit(String str) {
        str.getClass();
        this.e = str;
    }

    public final void setValue(String str) {
        str.getClass();
        this.d = str;
    }
}
