package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.view.View;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class p3 extends View {
    public final boolean a;
    public final Paint b;
    public Float c;

    public p3(MainActivity mainActivity, boolean z) {
        super(mainActivity);
        this.a = z;
        this.b = new Paint(1);
        setBackground(new n8(z, l8.m(mainActivity) * 22.0f));
        setClickable(true);
        setFocusable(true);
        b(null);
    }

    public final String a() {
        Float f = this.c;
        if (f == null) {
            return "—";
        }
        float fFloatValue = f.floatValue();
        List list = si.a;
        return String.format(si.b.b, "%.1f", Arrays.copyOf(new Object[]{Float.valueOf(fFloatValue)}, 1));
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0018  */
    public final void b(Float f) {
        String str;
        if (f != null) {
            float fFloatValue = f.floatValue();
            if (Math.abs(fFloatValue) > Float.MAX_VALUE || fFloatValue <= 0.0f) {
                f = null;
            }
        } else {
            f = null;
        }
        this.c = f;
        String strA = si.a("battery_compact", new Object[0]);
        String strA2 = a();
        if (this.c == null) {
            str = "";
        } else {
            str = " " + si.a("v", new Object[0]);
        }
        setContentDescription(strA + ": " + strA2 + str);
        invalidate();
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
        String strA = a();
        String strA2 = this.c == null ? "" : si.a("v", new Object[0]);
        boolean z = this.a;
        TextPaint textPaintY = pi.Y(l8.j(z), 1.0f, true);
        textPaintY.setFakeBoldText(true);
        Paint paintY = pi.Y(l8.k(z), 1.0f, true);
        int iK = this.c == null ? l8.k(z) : l8.a(l8.f(), z);
        float f = 1.6f * height;
        Paint paint = this.b;
        if (width < f) {
            float fMin = Math.min(width, height) * 0.45f;
            Map map = i9.a;
            i9.a(canvas, "batt", paint, iK, (width - fMin) / 2.0f, height * 0.08f, fMin, 2.2f);
            textPaintY.setTextSize(Math.min(width, height) * 0.27f);
            paintY.setTextSize(Math.min(width, height) * 0.18f);
            float textSize = (width - ((textPaintY.getTextSize() * 0.12f) + (paintY.measureText(strA2) + textPaintY.measureText(strA)))) / 2.0f;
            float f2 = height * 0.9f;
            canvas.drawText(strA, textSize, f2, textPaintY);
            canvas.drawText(strA2, c.a(textPaintY, 0.12f, textPaintY.measureText(strA) + textSize), f2, paintY);
            return;
        }
        float f3 = height * 0.55f;
        float f4 = height * 0.2f;
        float f5 = (0.13f * height) + f4 + f3;
        Map map2 = i9.a;
        i9.a(canvas, "batt", paint, iK, f4, (height - f3) / 2.0f, f3, 2.2f);
        textPaintY.setTextSize(0.43f * height);
        paintY.setTextSize(0.26f * height);
        float f6 = 0.05f * height;
        float fMeasureText = paintY.measureText(strA2) + textPaintY.measureText(strA) + f6;
        float f7 = (width - f5) - (height * 0.12f);
        if (f7 < 1.0f) {
            f7 = 1.0f;
        }
        float fMin2 = Math.min(1.0f, f7 / fMeasureText);
        textPaintY.setTextSize(textPaintY.getTextSize() * fMin2);
        paintY.setTextSize(paintY.getTextSize() * fMin2);
        float fDescent = (height / 2.0f) - ((textPaintY.descent() + textPaintY.ascent()) / 2.0f);
        canvas.drawText(strA, f5, fDescent, textPaintY);
        canvas.drawText(strA2, textPaintY.measureText(strA) + f5 + f6, fDescent, paintY);
    }
}
