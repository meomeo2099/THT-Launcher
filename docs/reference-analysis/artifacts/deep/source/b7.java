package defpackage;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class b7 extends Drawable {
    public static final /* synthetic */ int h = 0;
    public final boolean a;
    public final float b;
    public final boolean c;
    public final Paint d = new Paint(1);
    public int e = 255;
    public boolean f;
    public int g;

    public b7(boolean z, float f, boolean z2) {
        this.a = z;
        this.b = f;
        this.c = z2;
        l8.h();
        this.g = -10233877;
    }

    public final void a(int i, boolean z) {
        if (this.f == z && this.g == i) {
            return;
        }
        this.f = z;
        this.g = i;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        canvas.getClass();
        RectF rectF = new RectF(getBounds());
        rectF.inset(1.5f, 1.5f);
        boolean z = this.f;
        int i = this.g;
        int i2 = this.e;
        Drawable.Callback callback = getCallback();
        l0.h(canvas, rectF, this.b, this.a, this.d, z, i, i2, this.c, callback instanceof View ? (View) callback : null);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        this.e = pi.s(i, 0, 255);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.d.setColorFilter(colorFilter);
        invalidateSelf();
    }
}
