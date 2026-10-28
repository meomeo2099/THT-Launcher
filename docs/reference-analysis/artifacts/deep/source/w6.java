package defpackage;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class w6 extends Drawable {
    public final boolean a;
    public final Paint b = new Paint(1);
    public int c = 255;

    public w6(boolean z) {
        this.a = z;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        canvas.getClass();
        if (this.c == 0) {
            return;
        }
        RectF rectF = new RectF(getBounds());
        rectF.inset(1.5f, 1.5f);
        float fMin = Math.min(rectF.width(), rectF.height()) * 0.16f;
        int i = this.c;
        Drawable.Callback callback = getCallback();
        l0.i(canvas, rectF, fMin, this.a, this.b, false, 0, i, true, callback instanceof View ? (View) callback : null, 96);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        this.c = pi.s(i, 0, 255);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.b.setColorFilter(colorFilter);
        invalidateSelf();
    }
}
