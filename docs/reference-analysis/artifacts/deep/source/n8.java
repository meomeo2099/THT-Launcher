package defpackage;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class n8 extends Drawable {
    public final boolean a;
    public final float b;
    public final Paint c = new Paint(1);
    public int d = 255;

    public n8(boolean z, float f) {
        this.a = z;
        this.b = f;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        canvas.getClass();
        RectF rectF = new RectF(getBounds());
        rectF.inset(1.5f, 1.5f);
        int i = this.d;
        Drawable.Callback callback = getCallback();
        l0.i(canvas, rectF, this.b, this.a, this.c, false, 0, i, true, callback instanceof View ? (View) callback : null, 96);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        this.d = pi.s(i, 0, 255);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.c.setColorFilter(colorFilter);
        invalidateSelf();
    }
}
