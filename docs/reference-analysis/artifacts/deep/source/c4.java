package defpackage;

import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class c4 extends FrameLayout {
    public final float a;
    public final float b;
    public final boolean c;
    public final ArrayList d;
    public final SharedPreferences e;
    public boolean f;
    public boolean g;
    public b4 h;
    public j4 i;
    public float j;
    public float k;
    public boolean l;
    public long m;
    public String n;
    public d4 o;
    public boolean p;
    public final int q;
    public o6 r;
    public final Paint s;
    public y5 t;
    public float u;
    public float v;
    public final k1 w;

    public c4(MainActivity mainActivity, float f, float f2) {
        super(mainActivity);
        this.a = f;
        this.b = f2;
        this.c = true;
        this.d = new ArrayList();
        this.e = mainActivity.getSharedPreferences("panel", 0);
        this.q = ViewConfiguration.get(mainActivity).getScaledTouchSlop();
        Paint paint = new Paint(1);
        paint.setColor(-6365185);
        paint.setStrokeWidth(3.0f);
        this.s = paint;
        setClipChildren(true);
        setLayoutDirection(0);
        setTextDirection(5);
        this.w = new k1(3, this);
    }

    public static void c(c4 c4Var, View view, int i, int i2, int i3, int i4, String str, int i5) {
        Object ffVar;
        j4 j4Var;
        ArrayList arrayList;
        int i6 = (i5 & 32) != 0 ? 0 : 30;
        String str2 = (i5 & 64) != 0 ? "" : str;
        ArrayList arrayList2 = c4Var.d;
        SharedPreferences sharedPreferences = c4Var.e;
        str2.getClass();
        j4 j4Var2 = new j4(i, i2, i3, i4);
        if (th.A(str2)) {
            str2 = "item_" + arrayList2.size();
        }
        String str3 = str2;
        if (c4Var.f) {
            Set<String> set = x4.a;
            Set<String> stringSet = sharedPreferences.getStringSet("desktop_hidden", set);
            if (stringSet != null) {
                set = stringSet;
            }
            if (set.contains(str3)) {
                return;
            }
        }
        if (c4Var.f) {
            try {
                String string = sharedPreferences.getString("desktop_".concat(str3), null);
                if (string != null) {
                    List listD = th.D(string, new char[]{','});
                    arrayList = new ArrayList(k3.n0(listD));
                    Iterator it = listD.iterator();
                    while (it.hasNext()) {
                        arrayList.add(Float.valueOf(Float.parseFloat((String) it.next())));
                    }
                } else {
                    arrayList = null;
                }
                if (arrayList == null || arrayList.size() != 4) {
                    ffVar = j4Var2;
                } else {
                    if (!arrayList.isEmpty()) {
                        int size = arrayList.size();
                        int i7 = 0;
                        while (true) {
                            if (i7 < size) {
                                Object obj = arrayList.get(i7);
                                i7++;
                                if (Math.abs(((Number) obj).floatValue()) > Float.MAX_VALUE) {
                                    ffVar = j4Var2;
                                }
                            }
                        }
                    }
                    float fFloatValue = ((Number) arrayList.get(0)).floatValue();
                    float fFloatValue2 = ((Number) arrayList.get(1)).floatValue();
                    float fFloatValue3 = ((Number) arrayList.get(2)).floatValue();
                    float fFloatValue4 = ((Number) arrayList.get(3)).floatValue();
                    float fR = pi.r(fFloatValue3, 1.0f, 1280.0f);
                    float fR2 = pi.r(fFloatValue4, 1.0f, 720.0f);
                    ffVar = new j4(pi.r(fFloatValue, 0.0f, 1280.0f - fR), pi.r(fFloatValue2, 0.0f, 720.0f - fR2), fR, fR2);
                }
            } catch (Throwable th) {
                ffVar = new ff(th);
            }
            if (ffVar instanceof ff) {
                ffVar = j4Var2;
            }
            j4Var = (j4) ffVar;
        } else {
            j4Var = j4Var2;
        }
        arrayList2.add(new b4(view, j4Var, j4Var2, i6, str3));
        if (c4Var.f) {
            int i8 = zj.a;
            sharedPreferences.getClass();
            int iC = zj.c(sharedPreferences, zj.d(str3));
            Integer numValueOf = iC > 0 ? Integer.valueOf(iC) : null;
            if (numValueOf != null) {
                zj.a(view, numValueOf.intValue());
            }
        }
        c4Var.addView(view, new FrameLayout.LayoutParams(0, 0));
    }

    private final float getUiScale() {
        if (this.f) {
            return pi.r(this.e.getFloat("ui_scale", 1.0f), 0.75f, 1.5f);
        }
        return 1.0f;
    }

    public static boolean h(String str) {
        return x0.Y0(new String[]{"vehicle_cruise", "vehicle_battery", "vehicle_fuel", "vehicle_ambient", "vehicle_roof", "vehicle_roof_tilt", "vehicle_start_stop", "vehicle_airflow", "vehicle_trunk", "vehicle_mirrors", "vehicle_ext_light", "vehicle_wipers"}).contains(str) || str.startsWith("vehicle_seat_") || str.startsWith("vehicle_drive_");
    }

    public final j4 a(b4 b4Var) {
        return this.f ? l0.b.w(b4Var.b, getUiScale()) : b4Var.b;
    }

    public final boolean b() {
        b4 b4Var = this.h;
        if (b4Var == null) {
            return false;
        }
        String str = b4Var.e;
        View view = b4Var.a;
        o6 o6Var = this.r;
        if (o6Var == null) {
            return true;
        }
        o6Var.a(str, new j4(view.getLeft(), view.getTop(), view.getWidth(), view.getHeight()), Boolean.valueOf(h(str)));
        return true;
    }

    public final void d(String str, j4 j4Var) {
        str.getClass();
        float fE = e();
        float f = f();
        if (fE <= 0.0f || f <= 0.0f) {
            w.a("Failed requirement.");
            return;
        }
        float width = (getWidth() - (this.a * fE)) / 2.0f;
        float height = (getHeight() - (this.b * f)) / 2.0f;
        float uiScale = getUiScale();
        if (fE <= 0.0f || f <= 0.0f || uiScale <= 0.0f) {
            w.a("Failed requirement.");
            return;
        }
        float f2 = (j4Var.a - width) / fE;
        float f3 = (j4Var.b - height) / f;
        float f4 = (j4Var.c / fE) / uiScale;
        float f5 = (j4Var.d / f) / uiScale;
        this.e.edit().putString("desktop_".concat(str), f2 + "," + f3 + "," + f4 + "," + f5).apply();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        Canvas canvas2;
        float f;
        canvas.getClass();
        boolean z = this.g;
        int i = 0;
        Paint paint = this.s;
        if (z && this.e.getBoolean("desktop_grid", true)) {
            paint.setStyle(Paint.Style.STROKE);
            paint.setAlpha(40);
            paint.setStrokeWidth(1.0f);
            float fE = e();
            float f2 = f();
            float width = (getWidth() - (this.a * fE)) / 2.0f;
            float height = (getHeight() - (this.b * f2)) / 2.0f;
            int iD = pi.D(0, 1280, 20);
            if (iD >= 0) {
                int i2 = 0;
                while (true) {
                    float f3 = (i2 * fE) + width;
                    canvas2 = canvas;
                    canvas2.drawLine(f3, height, f3, (720.0f * f2) + height, paint);
                    f = height;
                    if (i2 == iD) {
                        break;
                    }
                    i2 += 20;
                    height = f;
                    canvas = canvas2;
                }
            } else {
                canvas2 = canvas;
                f = height;
            }
            int iD2 = pi.D(0, 720, 20);
            if (iD2 >= 0) {
                int i3 = 0;
                while (true) {
                    float f4 = (i3 * f2) + f;
                    float f5 = width;
                    canvas2.drawLine(f5, f4, (1280.0f * fE) + width, f4, paint);
                    if (i3 == iD2) {
                        break;
                    }
                    i3 += 20;
                    width = f5;
                }
            }
        } else {
            canvas2 = canvas;
        }
        super.dispatchDraw(canvas2);
        if (this.g) {
            ArrayList arrayList = this.d;
            int size = arrayList.size();
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                b4 b4Var = (b4) obj;
                paint.setStrokeWidth(3.0f);
                View view = b4Var.a;
                paint.setStyle(Paint.Style.STROKE);
                paint.setAlpha(b4Var == this.h ? 255 : 110);
                Paint paint2 = paint;
                canvas2.drawRoundRect(view.getLeft() + 2.0f, view.getTop() + 2.0f, view.getRight() - 2.0f, view.getBottom() - 2.0f, 12.0f, 12.0f, paint2);
                paint = paint2;
                paint.setStyle(Paint.Style.FILL);
                canvas2.drawCircle(view.getLeft() + 12.0f, view.getTop() + 12.0f, 10.0f, paint);
                canvas2.drawCircle(view.getRight() - 12.0f, view.getTop() + 12.0f, 10.0f, paint);
                canvas2.drawCircle(view.getLeft() + 12.0f, view.getBottom() - 12.0f, 10.0f, paint);
                canvas2.drawCircle(view.getRight() - 12.0f, view.getBottom() - 12.0f, 10.0f, paint);
            }
        }
    }

    public final float e() {
        return this.c ? getWidth() / this.a : f();
    }

    public final float f() {
        boolean z = this.c;
        float f = this.b;
        return (!z || this.f) ? Math.min(getWidth() / this.a, getHeight() / f) : getHeight() / f;
    }

    public final void g(int i, String str) {
        Object obj;
        str.getClass();
        this.e.edit().putInt(zj.d(str), i).apply();
        ArrayList arrayList = this.d;
        int size = arrayList.size();
        int i2 = 0;
        do {
            if (i2 >= size) {
                obj = null;
                break;
            } else {
                obj = arrayList.get(i2);
                i2++;
            }
        } while (!((b4) obj).e.equals(str));
        b4 b4Var = (b4) obj;
        if (b4Var != null) {
            int i3 = zj.a;
            zj.a(b4Var.a, i);
        }
    }

    public final float getDesignHeight() {
        return this.b;
    }

    public final float getDesignWidth() {
        return this.a;
    }

    public final y5 getOnHold() {
        return this.t;
    }

    public final o6 getOnItemTap() {
        return this.r;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        removeCallbacks(this.w);
        super.onDetachedFromWindow();
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        motionEvent.getClass();
        return this.g || super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float f = f();
        float fE = e();
        float width = (getWidth() - (this.a * fE)) / 2.0f;
        float height = (getHeight() - (this.b * f)) / 2.0f;
        ArrayList arrayList = this.d;
        int size = arrayList.size();
        int i5 = 0;
        while (i5 < size) {
            Object obj = arrayList.get(i5);
            i5++;
            b4 b4Var = (b4) obj;
            j4 j4VarA = a(b4Var);
            View view = b4Var.a;
            int iO0 = k8.o0((j4VarA.a * fE) + width);
            int iO1 = k8.o0((j4VarA.b * f) + height);
            view.layout(iO0, iO1, view.getMeasuredWidth() + iO0, view.getMeasuredHeight() + iO1);
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        float fMin;
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        setMeasuredDimension(size, size2);
        float f = this.a;
        float f2 = this.b;
        boolean z = this.c;
        float fMin2 = (!z || this.f) ? Math.min(size / f, size2 / f2) : size2 / f2;
        float f3 = z ? size / f : fMin2;
        float fR = pi.r(getResources().getConfiguration().fontScale, 1.0f, 1.2f);
        ArrayList arrayList = this.d;
        int size3 = arrayList.size();
        int i3 = 0;
        while (i3 < size3) {
            Object obj = arrayList.get(i3);
            i3++;
            b4 b4Var = (b4) obj;
            j4 j4VarA = a(b4Var);
            String str = b4Var.e;
            View view = b4Var.a;
            if (this.f) {
                float f4 = j4VarA.c;
                j4 j4Var = b4Var.c;
                fMin = Math.min(f4 / j4Var.c, j4VarA.d / j4Var.d);
            } else {
                fMin = 1.0f;
            }
            float f5 = b4Var.d;
            if (f5 > 0.0f && (view instanceof TextView)) {
                ((TextView) view).setTextSize(0, Math.min(f3, fMin2) * f5 * fR * fMin);
            }
            int iO0 = k8.o0(j4VarA.c * f3);
            int iO1 = k8.o0(j4VarA.d * fMin2);
            int iMin = Math.min(iO0, iO1);
            if (this.f && h(str)) {
                iO0 = iMin;
            }
            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iO0, 1073741824);
            if (this.f && h(str)) {
                iO1 = iMin;
            }
            view.measure(iMakeMeasureSpec, View.MeasureSpec.makeMeasureSpec(iO1, 1073741824));
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x01c2  */
    /* JADX WARN: Code duplicated, block: B:103:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:106:0x01db  */
    /* JADX WARN: Code duplicated, block: B:108:0x01de  */
    /* JADX WARN: Code duplicated, block: B:53:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:60:0x0102  */
    /* JADX WARN: Code duplicated, block: B:62:0x010c  */
    /* JADX WARN: Code duplicated, block: B:63:0x0114  */
    /* JADX WARN: Code duplicated, block: B:66:0x0120  */
    /* JADX WARN: Code duplicated, block: B:67:0x0128  */
    /* JADX WARN: Code duplicated, block: B:69:0x013a  */
    /* JADX WARN: Code duplicated, block: B:71:0x0155  */
    /* JADX WARN: Code duplicated, block: B:73:0x0161  */
    /* JADX WARN: Code duplicated, block: B:75:0x016c  */
    /* JADX WARN: Code duplicated, block: B:76:0x016e  */
    /* JADX WARN: Code duplicated, block: B:79:0x017a  */
    /* JADX WARN: Code duplicated, block: B:80:0x017d  */
    /* JADX WARN: Code duplicated, block: B:84:0x018e  */
    /* JADX WARN: Code duplicated, block: B:86:0x0191  */
    /* JADX WARN: Code duplicated, block: B:88:0x0194  */
    /* JADX WARN: Code duplicated, block: B:89:0x0197  */
    /* JADX WARN: Code duplicated, block: B:91:0x019a  */
    /* JADX WARN: Code duplicated, block: B:92:0x019c  */
    /* JADX WARN: Code duplicated, block: B:95:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:96:0x01a6  */
    /* JADX WARN: Code duplicated, block: B:99:0x01bf  */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0023, code lost:

        if (r1 != 3) goto L111;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouchEvent(android.view.MotionEvent r24) {
        /*
            Method dump skipped, instruction units count: 1193
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.c4.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public final boolean performClick() {
        super.performClick();
        return true;
    }

    public final void setOnHold(y5 y5Var) {
        this.t = y5Var;
    }

    public final void setOnItemTap(o6 o6Var) {
        this.r = o6Var;
    }
}
