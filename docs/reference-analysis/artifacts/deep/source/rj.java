package defpackage;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.view.View;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class rj extends View {
    public final boolean a;
    public final Paint b;
    public final Paint c;
    public final Paint d;
    public final Paint e;
    public List f;
    public long g;
    public long h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public rj(MainActivity mainActivity, boolean z) {
        super(mainActivity);
        mainActivity.getClass();
        this.a = z;
        Paint paint = new Paint(1);
        Paint.Style style = Paint.Style.STROKE;
        paint.setStyle(style);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        this.b = paint;
        this.c = new Paint(1);
        Paint paint2 = new Paint(1);
        paint2.setStyle(style);
        paint2.setColor(Color.rgb(255, 69, 58));
        this.d = paint2;
        this.e = new Paint(1);
        this.f = u4.a;
        this.h = 1L;
    }

    public static final float a(float f, float f2, float f3, float f4, float f5) {
        return f - ((f - f4) * ((f5 - f2) / (f3 - f2)));
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        float f;
        sj sjVar;
        int iK;
        int iK2;
        canvas.getClass();
        List list = this.f;
        if (list.isEmpty()) {
            return;
        }
        float width = getWidth();
        float height = getHeight();
        float f2 = height / 150.0f;
        float f3 = 34.0f * f2;
        float f4 = f2 * 6.0f;
        float f5 = width - f4;
        float f6 = f2 * 8.0f;
        float f7 = height - (22.0f * f2);
        Iterator it = list.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        float fMin = ((sj) it.next()).b;
        while (it.hasNext()) {
            fMin = Math.min(fMin, ((sj) it.next()).b);
        }
        float fFloor = (float) Math.floor(Math.min(fMin, 11.5f) - 0.3f);
        Iterator it2 = list.iterator();
        if (!it2.hasNext()) {
            throw new NoSuchElementException();
        }
        float fMax = ((sj) it2.next()).b;
        while (it2.hasNext()) {
            fMax = Math.max(fMax, ((sj) it2.next()).b);
        }
        float fCeil = (float) Math.ceil(Math.max(fMax, 14.5f) + 0.1f);
        float f8 = f5;
        long jMax = Math.max(this.g, ((sj) i3.u0(list)).a);
        long j = 1 + jMax;
        float f9 = fFloor;
        float f10 = f3;
        long jMin = Math.min(this.h, System.currentTimeMillis());
        long j2 = ((sj) i3.z0(list)).a;
        if (jMin < j2) {
            jMin = j2;
        }
        long jMax2 = Math.max(j, jMin);
        Paint paint = this.c;
        int i = l8.a;
        paint.setColor(l8.e(this.a));
        this.c.setStrokeWidth(Math.max(1.0f, f2));
        this.e.setColor(l8.k(this.a));
        this.e.setTextSize(15.0f * f2);
        this.e.setTextAlign(Paint.Align.RIGHT);
        float f11 = fCeil - f9 > 6.0f ? 2.0f : 1.0f;
        float f12 = f9;
        while (f12 <= 0.01f + fCeil) {
            float f13 = f9;
            float f14 = f10;
            float f15 = f12;
            float f16 = f8;
            float f17 = f2;
            float f18 = f4;
            canvas.drawLine(f14, a(f7, f13, fCeil, f6, f12), f16, a(f7, f13, fCeil, f6, f12), this.c);
            float fA = (f17 * 5.0f) + a(f7, f13, fCeil, f6, f15);
            f10 = f14;
            k8.F(canvas, String.format("%.0f", Arrays.copyOf(new Object[]{Float.valueOf(f15)}, 1)), f14 - f18, fA, this.e);
            f8 = f16;
            jMax = jMax;
            height = height;
            f12 = f15 + f11;
            f2 = f17;
            f4 = f18;
            f9 = f13;
            list = list;
        }
        List<sj> list2 = list;
        float f19 = f9;
        float f20 = f2;
        float f21 = f8;
        float f22 = height;
        long j3 = jMax;
        float f23 = f21;
        this.d.setStrokeWidth(Math.max(1.0f, 1.4f * f20));
        this.d.setPathEffect(new DashPathEffect(new float[]{f4, f20 * 5.0f}, 0.0f));
        float f24 = f10;
        canvas.drawLine(f24, a(f7, f19, fCeil, f6, 11.5f), f23, a(f7, f19, fCeil, f6, 11.5f), this.d);
        this.b.setStrokeWidth(2.4f * f20);
        sj sjVar2 = null;
        for (sj sjVar3 : list2) {
            if (sjVar2 != null) {
                float f25 = f23;
                if (sjVar3.a - sjVar2.a <= 1800000) {
                    Paint paint2 = this.b;
                    if (sjVar3.c || sjVar2.c) {
                        iK2 = jh.f;
                    } else {
                        int i2 = l8.a;
                        iK2 = l8.k(this.a);
                    }
                    paint2.setColor(iK2);
                    float f26 = jMax2 - j3;
                    float f27 = f25 - f24;
                    jMax2 = jMax2;
                    f = f25;
                    sjVar = sjVar3;
                    canvas.drawLine((((sjVar2.a - j3) / f26) * f27) + f24, a(f7, f19, fCeil, f6, sjVar2.b), (((sjVar3.a - j3) / f26) * f27) + f24, a(f7, f19, fCeil, f6, sjVar3.b), this.b);
                } else {
                    f = f25;
                }
                f23 = f;
                sjVar2 = sjVar;
                jMax2 = jMax2;
            } else {
                f = f23;
            }
            sjVar = sjVar3;
            Paint paint3 = this.b;
            if (sjVar.c) {
                iK = jh.f;
            } else {
                int i3 = l8.a;
                iK = l8.k(this.a);
            }
            paint3.setColor(iK);
            canvas.drawPoint(((f - f24) * ((sjVar.a - j3) / (jMax2 - j3))) + f24, a(f7, f19, fCeil, f6, sjVar.b), this.b);
            f23 = f;
            sjVar2 = sjVar;
            jMax2 = jMax2;
        }
        long j4 = jMax2;
        float f28 = f23;
        this.e.setTextSize(14.0f * f20);
        Paint paint4 = this.e;
        int i4 = l8.a;
        paint4.setColor(l8.k(this.a));
        String str = j4 - j3 > 129600000 ? "d MMM" : "HH:mm";
        List list3 = si.a;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, si.b.b);
        this.e.setTextAlign(Paint.Align.LEFT);
        String str2 = simpleDateFormat.format(new Date(j3));
        str2.getClass();
        float f29 = f22 - (f20 * 4.0f);
        k8.F(canvas, str2, f24, f29, this.e);
        this.e.setTextAlign(Paint.Align.RIGHT);
        String str3 = simpleDateFormat.format(new Date(j4));
        str3.getClass();
        k8.F(canvas, str3, f28, f29, this.e);
    }
}
