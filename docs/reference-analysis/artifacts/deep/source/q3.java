package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.view.View;
import java.util.Map;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class q3 extends View {
    public final boolean a;
    public final Paint b;
    public String c;
    public boolean d;

    public q3(MainActivity mainActivity, boolean z, boolean z2) {
        super(mainActivity);
        this.a = z;
        this.b = new Paint(1);
        this.c = "—";
        if (z2) {
            setBackground(new n8(z, l8.m(mainActivity) * 22.0f));
            setClickable(true);
            setFocusable(true);
        }
        setContentDescription(si.a("fuel_compact", new Object[0]));
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
        boolean z = this.d;
        boolean z2 = this.a;
        int iA = z ? l8.b : l8.a(l8.f(), z2);
        String string = th.J(si.a("0_l", "")).toString();
        TextPaint textPaintY = pi.Y(l8.j(z2), 1.0f, true);
        textPaintY.setFakeBoldText(true);
        Paint paintY = pi.Y(l8.k(z2), 1.0f, true);
        float f = 1.6f * height;
        Paint paint = this.b;
        if (width < f) {
            float fMin = Math.min(width, height) * 0.46f;
            Map map = i9.a;
            i9.a(canvas, "pump", paint, iA, (width - fMin) / 2.0f, height * 0.08f, fMin, 2.2f);
            textPaintY.setTextSize(Math.min(width, height) * 0.3f);
            paintY.setTextSize(Math.min(width, height) * 0.2f);
            float textSize = (width - ((textPaintY.getTextSize() * 0.12f) + (paintY.measureText(string) + textPaintY.measureText(this.c)))) / 2.0f;
            float f2 = height * 0.9f;
            canvas.drawText(this.c, textSize, f2, textPaintY);
            canvas.drawText(string, c.a(textPaintY, 0.12f, textPaintY.measureText(this.c) + textSize), f2, paintY);
            return;
        }
        float f3 = height * 0.56f;
        float f4 = height * 0.26f;
        Map map2 = i9.a;
        i9.a(canvas, "pump", paint, iA, f4, (height - f3) / 2.0f, f3, 2.2f);
        textPaintY.setTextSize(0.46f * height);
        paintY.setTextSize(0.3f * height);
        float f5 = (0.18f * height) + f4 + f3;
        float fAscent = (height / 2.0f) - ((textPaintY.ascent() + textPaintY.descent()) / 2.0f);
        canvas.drawText(this.c, f5, fAscent, textPaintY);
        canvas.drawText(string, (height * 0.06f) + textPaintY.measureText(this.c) + f5, fAscent, paintY);
    }
}
