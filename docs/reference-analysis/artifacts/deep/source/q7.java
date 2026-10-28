package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class q7 extends ViewGroup {
    public static final /* synthetic */ int v = 0;
    public final boolean a;
    public final Bitmap b;
    public r7 c;
    public final boolean d;
    public final j6 e;
    public final y5 f;
    public final j6 g;
    public final j6 h;
    public final Paint i;
    public final RectF j;
    public int k;
    public final HashMap l;
    public final HashMap m;
    public final List n;
    public final GestureDetector o;
    public String p;
    public u7 q;
    public int r;
    public float s;
    public float t;
    public boolean u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [android.view.View, android.view.ViewGroup, q7] */
    /* JADX WARN: Type inference failed for: r12v10, types: [java.lang.Iterable, java.util.List] */
    /* JADX WARN: Type inference failed for: r12v17, types: [u4] */
    /* JADX WARN: Type inference failed for: r12v9, types: [java.util.ArrayList] */
    public q7(Context context, boolean z, Bitmap bitmap, r7 r7Var, v1 v1Var, j6 j6Var, w7 w7Var, q0 q0Var, y7 y7Var, int i) {
        ?? arrayList;
        super(context);
        boolean z2 = (i & 16) == 0;
        j6 j6Var2 = (i & 32) != 0 ? l0.d : v1Var;
        int i2 = 4;
        j6 wVar = (i & 64) != 0 ? new w(i2) : j6Var;
        w7 w7Var2 = (i & 128) != 0 ? null : w7Var;
        j6 wVar2 = (i & 256) != 0 ? new w(i2) : q0Var;
        j6 wVar3 = (i & 512) != 0 ? new w(5) : y7Var;
        context.getClass();
        this.a = z;
        this.b = bitmap;
        this.c = r7Var;
        this.d = z2;
        this.e = wVar;
        this.f = w7Var2;
        this.g = wVar2;
        this.h = wVar3;
        this.i = new Paint(3);
        this.j = new RectF();
        this.k = -1;
        this.l = new HashMap();
        this.m = new HashMap();
        if (z2) {
            arrayList = u4.a;
        } else {
            List list = this.c.a;
            list.getClass();
            ArrayList arrayList2 = new ArrayList();
            Iterator it = list.iterator();
            it.getClass();
            int i3 = 0;
            while (it.hasNext()) {
                int i4 = i3 + 1;
                if (i3 < 0) {
                    j3.m0();
                    throw null;
                }
                Object next = it.next();
                g8 g8Var = new g8(i3, next);
                u7 u7Var = (u7) next;
                if (u7Var.f == t7.e && !th.A(u7Var.e)) {
                    arrayList2.add(g8Var);
                }
                i3 = i4;
            }
            arrayList = new ArrayList();
            int size = arrayList2.size();
            int i5 = 0;
            while (i5 < size) {
                Object obj = arrayList2.get(i5);
                i5++;
                g8 g8Var2 = (g8) obj;
                int i6 = g8Var2.a;
                j9 j9Var = (j9) j6Var2.b(((u7) g8Var2.b).e);
                yc ycVar = j9Var != null ? new yc(Integer.valueOf(i6), j9Var) : null;
                if (ycVar != null) {
                    arrayList.add(ycVar);
                }
            }
        }
        this.n = arrayList;
        this.o = new GestureDetector(context, new p7(this));
        setWillNotDraw(false);
        setClickable(true);
        setFocusable(true);
        if (this.b == null) {
            setBackground(new w6(this.a));
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            j9 j9Var2 = (j9) ((yc) it2.next()).b;
            j9Var2.setFaceAlpha(0);
            j9Var2.setClickable(false);
            j9Var2.setFocusable(false);
            addView(j9Var2);
        }
        setContentDescription(si.a("screensaver_picture", new Object[0]));
    }

    public final RectF a(u7 u7Var) {
        RectF rectF = this.j;
        return new RectF((rectF.width() * u7Var.a) + rectF.left, (rectF.height() * u7Var.b) + rectF.top, (rectF.width() * u7Var.c) + rectF.left, (rectF.height() * u7Var.d) + rectF.top);
    }

    public final List<yc> getBadges() {
        return this.n;
    }

    public final s7 getOver() {
        return this.c.b;
    }

    public final int getSelected() {
        return this.k;
    }

    public final List<u7> getZones() {
        return this.c.a;
    }

    /* JADX WARN: Code duplicated, block: B:35:0x0174  */
    /* JADX WARN: Code duplicated, block: B:36:0x017f  */
    @Override // android.view.View
    public final void onDraw(Canvas canvas) throws Throwable {
        int i;
        int i2;
        Throwable th;
        Object ffVar;
        Integer num;
        int iRgb;
        canvas.getClass();
        Paint.Style style = Paint.Style.FILL;
        Paint paint = this.i;
        paint.setStyle(style);
        float f = 3.0f;
        boolean z = this.a;
        float f2 = 2.0f;
        Throwable th2 = null;
        Bitmap bitmap = this.b;
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, (Rect) null, this.j, paint);
        } else {
            paint.setColor(l8.k(z));
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTextSize(Math.min(getWidth(), getHeight()) * 0.11f);
            canvas.drawText(si.a("image_tap_to_choose", new Object[0]), getWidth() / 2.0f, (paint.getTextSize() / 3.0f) + (getHeight() / 2.0f), paint);
        }
        int i3 = 0;
        for (Object obj : this.c.a) {
            int i4 = i3 + 1;
            if (i3 < 0) {
                Throwable th3 = th2;
                j3.m0();
                throw th3;
            }
            u7 u7Var = (u7) obj;
            RectF rectFA = a(u7Var);
            float f3 = f;
            float f4 = f2;
            float fMin = Math.min(rectFA.width(), rectFA.height()) * 0.22f;
            Integer num2 = (Integer) this.l.get(Integer.valueOf(i3));
            if (num2 != null) {
                int iIntValue = num2.intValue();
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.argb(70, Color.red(iIntValue), Color.green(iIntValue), Color.blue(iIntValue)));
                canvas.drawRoundRect(rectFA, fMin, fMin, paint);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(Math.min(rectFA.width(), rectFA.height()) * 0.04f);
                paint.setColor(iIntValue);
                canvas.drawRoundRect(rectFA, fMin, fMin, paint);
            }
            String str = (String) this.m.get(Integer.valueOf(i3));
            if (str != null) {
                if (bitmap == null) {
                    num = null;
                } else {
                    float f5 = u7Var.a;
                    int iS = pi.s((int) ((((u7Var.c - f5) * 0.06f) + f5) * bitmap.getWidth()), 0, bitmap.getWidth() - 1);
                    float f6 = u7Var.b;
                    try {
                        ffVar = Integer.valueOf(bitmap.getPixel(iS, pi.s((int) ((((u7Var.d - f6) * 0.1f) + f6) * bitmap.getHeight()), 0, bitmap.getHeight() - 1)));
                    } catch (Throwable th4) {
                        ffVar = new ff(th4);
                    }
                    if (ffVar instanceof ff) {
                        ffVar = null;
                    }
                    num = (Integer) ffVar;
                }
                paint.setStyle(Paint.Style.FILL);
                if (num != null) {
                    paint.setColor(num.intValue());
                    canvas.drawRect(rectFA, paint);
                }
                if (num != null) {
                    int iIntValue2 = num.intValue();
                    if (((Color.blue(iIntValue2) * 114) + ((Color.green(iIntValue2) * 587) + (Color.red(iIntValue2) * 299))) / 1000 > 140) {
                        iRgb = Color.rgb(20, 32, 54);
                    } else {
                        iRgb = -1;
                    }
                } else if (z) {
                    iRgb = -1;
                } else {
                    iRgb = Color.rgb(20, 32, 54);
                }
                paint.setColor(iRgb);
                paint.setTextAlign(Paint.Align.CENTER);
                paint.setTextSize(rectFA.height() * 0.62f);
                paint.setTypeface(z9.c());
                float fMeasureText = paint.measureText(str);
                if (fMeasureText > rectFA.width() * 0.92f) {
                    paint.setTextSize(((rectFA.width() * 0.92f) / fMeasureText) * paint.getTextSize());
                }
                canvas.drawText(str, rectFA.centerX(), rectFA.centerY() - ((paint.ascent() + paint.descent()) / f4), paint);
                th = null;
                paint.setTypeface(null);
            } else {
                th = null;
            }
            th2 = th;
            i3 = i4;
            f = f3;
            f2 = f4;
        }
        float f7 = f;
        if (this.d) {
            int i5 = 0;
            for (Object obj2 : this.c.a) {
                int i6 = i5 + 1;
                if (i5 < 0) {
                    j3.m0();
                    throw null;
                }
                RectF rectFA2 = a((u7) obj2);
                boolean z2 = i5 == this.k;
                Paint.Style style2 = Paint.Style.FILL;
                paint.setStyle(style2);
                if (z2) {
                    i2 = 60;
                    i = 0;
                } else {
                    i = 0;
                    i2 = 28;
                }
                paint.setColor(Color.argb(i2, i, 170, 255));
                canvas.drawRect(rectFA2, paint);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(z2 ? 5.0f : 2.5f);
                l8.h();
                paint.setColor(-10233877);
                canvas.drawRect(rectFA2, paint);
                paint.setStyle(style2);
                float fR = pi.r(Math.min(rectFA2.width(), rectFA2.height()) * 0.28f, 10.0f, getResources().getDisplayMetrics().density * 28.0f);
                paint.setColor(Color.argb(190, 0, 0, 0));
                canvas.drawCircle(rectFA2.left + fR, rectFA2.top + fR, 0.8f * fR, paint);
                paint.setColor(-1);
                paint.setTextAlign(Paint.Align.CENTER);
                paint.setTextSize(fR);
                canvas.drawText(String.valueOf(i6), rectFA2.left + fR, (fR / f7) + rectFA2.top + fR, paint);
                if (z2) {
                    l8.h();
                    paint.setColor(-10233877);
                    for (yc ycVar : j3.i0(new yc(Float.valueOf(rectFA2.left), Float.valueOf(rectFA2.top)), new yc(Float.valueOf(rectFA2.right), Float.valueOf(rectFA2.top)), new yc(Float.valueOf(rectFA2.left), Float.valueOf(rectFA2.bottom)), new yc(Float.valueOf(rectFA2.right), Float.valueOf(rectFA2.bottom)))) {
                        canvas.drawCircle(((Number) ycVar.a).floatValue(), ((Number) ycVar.b).floatValue(), getResources().getDisplayMetrics().density * 9.0f, paint);
                    }
                }
                i5 = i6;
            }
        }
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        motionEvent.getClass();
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        for (yc ycVar : this.n) {
            int iIntValue = ((Number) ycVar.a).intValue();
            j9 j9Var = (j9) ycVar.b;
            RectF rectFA = a((u7) this.c.a.get(iIntValue));
            int iCenterX = (int) (rectFA.centerX() - (j9Var.getMeasuredWidth() / 2.0f));
            int iCenterY = (int) (rectFA.centerY() - (j9Var.getMeasuredHeight() / 2.0f));
            j9Var.layout(iCenterX, iCenterY, j9Var.getMeasuredWidth() + iCenterX, j9Var.getMeasuredHeight() + iCenterY);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        RectF rectF;
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        setMeasuredDimension(size, size2);
        HashMap map = v7.a;
        Bitmap bitmap = this.b;
        int width = bitmap != null ? bitmap.getWidth() : 0;
        int height = bitmap != null ? bitmap.getHeight() : 0;
        float f = size;
        float f2 = size2;
        if (width <= 0 || height <= 0) {
            rectF = new RectF(0.0f, 0.0f, f, f2);
        } else {
            float f3 = width;
            float f4 = height;
            float fMin = Math.min(f / f3, f2 / f4);
            float f5 = f3 * fMin;
            float f6 = f4 * fMin;
            rectF = new RectF((f - f5) / 2.0f, (f2 - f6) / 2.0f, (f + f5) / 2.0f, (f2 + f6) / 2.0f);
        }
        this.j.set(rectF);
        for (yc ycVar : this.n) {
            int iIntValue = ((Number) ycVar.a).intValue();
            j9 j9Var = (j9) ycVar.b;
            RectF rectFA = a((u7) this.c.a.get(iIntValue));
            int iMin = (int) (Math.min(rectFA.width(), rectFA.height()) * 0.8f);
            if (iMin < 1) {
                iMin = 1;
            }
            j9Var.measure(View.MeasureSpec.makeMeasureSpec(iMin, 1073741824), View.MeasureSpec.makeMeasureSpec(iMin, 1073741824));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0025, code lost:

        if (r3 != 3) goto L22;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouchEvent(android.view.MotionEvent r19) {
        /*
            Method dump skipped, instruction units count: 646
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.q7.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public final boolean performClick() {
        super.performClick();
        return true;
    }

    public final void setSelected(int i) {
        this.k = i;
        invalidate();
    }
}
