package defpackage;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class t9 extends View {
    public final Paint a;
    public boolean b;

    public t9(MainActivity mainActivity, boolean z) {
        super(mainActivity);
        this.a = new Paint(1);
        this.b = z;
        setClickable(true);
        setFocusable(true);
    }

    public final boolean getChecked() {
        return this.b;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int iArgb;
        canvas.getClass();
        float width = getWidth();
        float height = getHeight();
        float f = height / 2.0f;
        Paint.Style style = Paint.Style.FILL;
        Paint paint = this.a;
        paint.setStyle(style);
        if (this.b) {
            int i = l8.a;
            iArgb = l8.a;
        } else {
            iArgb = Color.argb(92, 120, 120, 128);
        }
        paint.setColor(iArgb);
        canvas.drawRoundRect(0.0f, 0.0f, width, height, f, f, paint);
        paint.setColor(-1);
        canvas.drawCircle(this.b ? width - f : f, f, f - (height * 0.09f), paint);
    }

    public final void setChecked(boolean z) {
        this.b = z;
        invalidate();
    }
}
