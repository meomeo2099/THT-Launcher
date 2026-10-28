package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import java.util.List;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class w2 extends View implements x8 {
    public final int a;
    public final Paint b;
    public String c;

    public w2(MainActivity mainActivity, boolean z, int i, boolean z2, b2 b2Var) {
        super(mainActivity);
        this.a = i;
        Paint paint = new Paint(1);
        paint.setColor(l8.j(z));
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(z9.c());
        this.b = paint;
        this.c = "—";
        if (z2) {
            setBackground(new n8(z, l8.m(mainActivity) * 24.0f));
        }
        setClickable(true);
        setFocusable(true);
        setOnClickListener(new s(2, b2Var));
        b(null);
    }

    @Override // defpackage.x8
    public final int a(int i) {
        return (int) (i * 1.12f);
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0020  */
    public final void b(Float f) {
        String strD;
        if (f == null) {
            strD = "—";
        } else {
            if (Math.abs(f.floatValue()) > Float.MAX_VALUE) {
                f = null;
            }
            if (f != null) {
                List list = z2.a;
                strD = z2.d(f.floatValue());
            } else {
                strD = "—";
            }
        }
        this.c = strD;
        List list2 = si.a;
        setContentDescription(si.a(this.a == 1 ? "degree_driver" : "degree_passenger", new Object[0]) + ": " + this.c + ". " + si.a("open_climate_controls", new Object[0]));
        invalidate();
    }

    public final int getZone() {
        return this.a;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        canvas.getClass();
        super.onDraw(canvas);
        float height = getHeight() * 0.48f;
        Paint paint = this.b;
        paint.setTextSize(height);
        float fMeasureText = paint.measureText(this.c);
        if (fMeasureText > getWidth() * 0.88f && fMeasureText > 0.0f) {
            paint.setTextSize(((getWidth() * 0.88f) / fMeasureText) * paint.getTextSize());
        }
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        canvas.drawText(this.c, getWidth() / 2.0f, (getHeight() / 2.0f) - ((fontMetrics.ascent + fontMetrics.descent) / 2.0f), paint);
    }
}
