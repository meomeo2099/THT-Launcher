package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class xi extends View {
    public float a;
    public final Paint b;
    public final /* synthetic */ yi c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public xi(yi yiVar, MainActivity mainActivity) {
        super(mainActivity);
        this.c = yiVar;
        this.b = new Paint(1);
    }

    public final float getFraction() {
        return this.a;
    }

    public final Paint getP() {
        return this.b;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        canvas.getClass();
        float width = getWidth();
        float height = getHeight();
        float f = height / 2.0f;
        int i = l8.a;
        int iE = l8.e(this.c.c);
        Paint paint = this.b;
        paint.setColor(iE);
        canvas.drawRoundRect(0.0f, 0.0f, width, height, f, f, paint);
        paint.setColor(l8.f());
        float f2 = this.a;
        if (f2 > 0.0f) {
            canvas.drawRoundRect(0.0f, 0.0f, Math.max(height, width * f2), height, f, f, paint);
        }
    }

    public final void setFraction(float f) {
        this.a = f;
    }
}
