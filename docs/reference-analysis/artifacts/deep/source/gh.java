package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import java.util.Map;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class gh extends View {
    public final boolean a;
    public final Drawable b;
    public final Drawable c;
    public final int d;
    public final Paint e;
    public final RectF f;

    public gh(MainActivity mainActivity, boolean z, boolean z2, Drawable drawable, Drawable drawable2, int i) {
        super(mainActivity);
        this.a = z;
        this.b = drawable;
        this.c = drawable2;
        this.d = i;
        this.e = new Paint(1);
        this.f = new RectF();
        if (z2) {
            setBackground(new n8(z, l8.m(mainActivity) * 24.0f));
        }
        setClickable(true);
        setFocusable(true);
        setLongClickable(true);
    }

    public static final void a(gh ghVar, float f, float f2, Canvas canvas, float f3, float f4, Drawable drawable) {
        RectF rectF = ghVar.f;
        rectF.set(f3, f, f4, f2 - f);
        float fMin = Math.min(rectF.width(), rectF.height()) * 0.22f;
        Paint paint = ghVar.e;
        paint.setStyle(Paint.Style.FILL);
        int i = l8.a;
        boolean z = ghVar.a;
        paint.setColor(l8.e(z));
        canvas.drawRoundRect(rectF, fMin, fMin, paint);
        int iMin = (int) (Math.min(rectF.width(), rectF.height()) * 0.62f);
        float f5 = iMin;
        float f6 = f5 / 2.0f;
        int iCenterX = (int) (rectF.centerX() - f6);
        int iCenterY = (int) (rectF.centerY() - f6);
        if (drawable != null) {
            drawable.setBounds(iCenterX, iCenterY, iCenterX + iMin, iMin + iCenterY);
            drawable.draw(canvas);
        } else {
            Map map = i9.a;
            i9.a(canvas, "plus", paint, l8.k(z), iCenterX, iCenterY, f5, 2.2f);
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        canvas.getClass();
        float width = getWidth();
        float height = getHeight();
        if (width <= 0.0f || height <= 0.0f) {
            return;
        }
        float fMin = Math.min(width, height) * (getBackground() != null ? 0.14f : 0.06f);
        float fMin2 = Math.min(width, height) * 0.06f;
        float f = ((((width - (2.0f * fMin)) - fMin2) * this.d) / 100.0f) + fMin;
        a(this, fMin, height, canvas, fMin, f, this.b);
        a(this, fMin, height, canvas, f + fMin2, width - fMin, this.c);
    }
}
