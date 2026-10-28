package defpackage;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class jh extends View {
    public static final int f = Color.rgb(48, 176, 199);
    public final boolean a;
    public final Paint b;
    public final Paint c;
    public final RectF d;
    public List e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public jh(MainActivity mainActivity, boolean z) {
        super(mainActivity);
        mainActivity.getClass();
        this.a = z;
        this.b = new Paint(1);
        Paint paint = new Paint(1);
        paint.setTextAlign(Paint.Align.CENTER);
        this.c = paint;
        this.d = new RectF();
        this.e = u4.a;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        canvas.getClass();
        List list = this.e;
        if (list.isEmpty()) {
            return;
        }
        float width = getWidth();
        float height = getHeight();
        float f2 = height / 150.0f;
        float f3 = 22.0f * f2;
        float f4 = 18.0f * f2;
        float f5 = f2 * 4.0f;
        float f6 = f4 + f5;
        float f7 = height - f3;
        Iterator it = list.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        float fMax = ((ih) it.next()).b;
        while (it.hasNext()) {
            fMax = Math.max(fMax, ((ih) it.next()).b);
        }
        float fMax2 = Math.max(0.1f, fMax);
        float size = width / list.size();
        float f8 = 0.5f * size;
        boolean z = this.a;
        int iK = l8.k(z);
        Paint paint = this.c;
        paint.setColor(iK);
        Iterator it2 = list.iterator();
        int i = 0;
        while (it2.hasNext()) {
            Object next = it2.next();
            int i2 = i + 1;
            if (i < 0) {
                j3.m0();
                throw null;
            }
            ih ihVar = (ih) next;
            float f9 = (size / 2.0f) + (i * size);
            Iterator it3 = it2;
            float f10 = ihVar.b;
            float f11 = f8 / 2.0f;
            float f12 = size;
            float f13 = f9 - f11;
            float f14 = height;
            float f15 = f7 - ((f7 - f6) * (f10 / fMax2));
            float f16 = f5;
            float f17 = f9 + f11;
            float f18 = f3;
            RectF rectF = this.d;
            rectF.set(f13, f15, f17, f7);
            int iE = f10 > 0.0f ? f : l8.e(z);
            Paint paint2 = this.b;
            paint2.setColor(iE);
            float f19 = 0.28f * f8;
            canvas.drawRoundRect(rectF, f19, f19, paint2);
            if (f10 > 0.0f) {
                paint.setTextSize(0.85f * f4);
                paint.setColor(l8.j(z));
                k8.F(canvas, f10 >= 10.0f ? String.valueOf((int) f10) : String.format("%.1f", Arrays.copyOf(new Object[]{Float.valueOf(f10)}, 1)), f9, f15 - f16, paint);
            }
            paint.setTextSize(f18 * 0.72f);
            paint.setColor(l8.k(z));
            k8.F(canvas, ihVar.a, f9, f14 - f16, paint);
            i = i2;
            f3 = f18;
            it2 = it3;
            size = f12;
            f5 = f16;
            height = f14;
        }
    }

    public final void set(List<ih> list) {
        list.getClass();
        this.e = list;
        invalidate();
    }
}
