package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class k4 extends View {
    public final int a;
    public final Paint b;

    public k4(MainActivity mainActivity, int i) {
        super(mainActivity);
        this.a = i;
        Paint paint = new Paint(1);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        this.b = paint;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        canvas.getClass();
        float fMin = Math.min(getWidth(), getHeight());
        if (fMin <= 0.0f) {
            return;
        }
        float width = getWidth() / 2.0f;
        float height = getHeight() / 2.0f;
        int i = this.a;
        Paint paint = this.b;
        paint.setColor(i);
        paint.setStrokeWidth(0.07f * fMin);
        float f = 0.14f * fMin;
        float f2 = width - f;
        float f3 = fMin * 0.28f;
        float f4 = height - f3;
        float f5 = height + f3;
        canvas.drawLine(f2, f4, f2, f5, paint);
        float f6 = width + f;
        canvas.drawLine(f6, f4, f6, f5, paint);
    }
}
