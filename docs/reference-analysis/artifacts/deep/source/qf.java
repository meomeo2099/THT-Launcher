package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class qf extends View {
    public static final int w = Color.rgb(255, 149, 0);
    public static final List x = j3.i0("time", "date", "weather", "track");
    public final int a;
    public final y5 b;
    public final y5 c;
    public final boolean d;
    public final SharedPreferences e;
    public final int f;
    public final boolean g;
    public final float h;
    public final boolean i;
    public final boolean j;
    public final boolean k;
    public List l;
    public final HashMap m;
    public String n;
    public float o;
    public float p;
    public final Paint q;
    public String r;
    public long s;
    public final Handler t;
    public final Bitmap u;
    public final r1 v;

    public qf(Context context, int i, y5 y5Var, y5 y5Var2, boolean z) {
        Object ffVar;
        super(context);
        this.a = i;
        this.b = y5Var;
        this.c = y5Var2;
        this.d = z;
        SharedPreferences sharedPreferences = context.getSharedPreferences("panel", 0);
        this.e = sharedPreferences;
        int iS = pi.s(sharedPreferences.getInt("saver_clock", 0), 0, 2);
        this.f = iS;
        this.g = iS == 1;
        int i2 = sharedPreferences.getInt("saver_clock_size", 1);
        this.h = i2 != 0 ? i2 != 2 ? 1.0f : 1.3f : 0.7f;
        this.i = sharedPreferences.getBoolean("saver_date", true);
        this.j = sharedPreferences.getBoolean("saver_weather", true);
        this.k = sharedPreferences.getBoolean("saver_track", true);
        this.l = u4.a;
        this.m = new HashMap();
        this.q = new Paint(1);
        this.t = new Handler(Looper.getMainLooper());
        File file = new File(context.getFilesDir(), "wallpaper_screensaver.png");
        Bitmap bitmap = null;
        if (file.exists()) {
            try {
                ffVar = BitmapFactory.decodeFile(file.getAbsolutePath());
            } catch (Throwable th) {
                ffVar = new ff(th);
            }
            bitmap = (Bitmap) (ffVar instanceof ff ? null : ffVar);
        }
        this.u = bitmap;
        this.v = new r1(12, this);
        setClickable(true);
        List list = si.a;
        setContentDescription(si.a(this.d ? "saver_layout_hint" : "screensaver_touch_to_return", new Object[0]));
    }

    public static final void a(qf qfVar, Canvas canvas, float f, float f2, float f3, float f4, float f5, float f6, int i) {
        double radians = Math.toRadians(((double) f3) - 90.0d);
        float fCos = (float) Math.cos(radians);
        float fSin = (float) Math.sin(radians);
        Paint paint = qfVar.q;
        paint.setStrokeWidth(f6);
        paint.setColor(i);
        paint.setShadowLayer(0.9f * f6, 0.0f, f6 * 0.3f, -1946157056);
        canvas.drawLine(f - (fCos * f5), f2 - (f5 * fSin), (fCos * f4) + f, (fSin * f4) + f2, paint);
        paint.clearShadowLayer();
    }

    public static final void b(final qf qfVar, ArrayList arrayList, final float f, float f2, final TextPaint textPaint, String str, float f3, String str2, final String str3, float f4, float f5, final float f6, float f7, float f8) {
        int i;
        float f9 = f4;
        float fA = f8;
        int i2 = 1;
        if (qfVar.i) {
            arrayList.add(new pf("date", (f * 4.0f) + f7, fA - f2, textPaint.measureText(str), f3, new nf(str, f2, textPaint, i2)));
            i = 1;
        } else {
            i = 0;
        }
        if (str2 != null) {
            if (i != 0) {
                fA = c.a(textPaint, 1.5f, fA);
            }
            String strY = pi.y(str2, textPaint, f9);
            arrayList.add(new pf("weather", (4.0f * f) + f7, fA - f2, textPaint.measureText(strY), f3, new nf(strY, f2, textPaint, 2)));
        } else {
            i2 = i;
        }
        if (str3 != null) {
            float fA2 = i2 != 0 ? c.a(textPaint, 0.75f, fA) : fA - f2;
            TextPaint textPaintY = pi.Y(-1, textPaint.getTextSize() * 0.82f, false);
            float fMeasureText = textPaintY.measureText("ооо") + (440.0f * f);
            if (f9 >= fMeasureText) {
                f9 = fMeasureText;
            }
            float fMeasureText2 = textPaintY.measureText(str3);
            float f10 = 2.0f * f;
            float f11 = f9 - f10;
            if (fMeasureText2 >= f11) {
                fMeasureText2 = f11;
            }
            arrayList.add(new pf("track", f7, fA2, fMeasureText2 + f10, f5, new o6() { // from class: of
                @Override // defpackage.o6
                public final Object a(Object obj, Object obj2, Object obj3) {
                    Canvas canvas = (Canvas) obj;
                    float fFloatValue = ((Float) obj2).floatValue();
                    float fFloatValue2 = ((Float) obj3).floatValue();
                    canvas.getClass();
                    float f12 = f6 - fFloatValue;
                    float f13 = f;
                    float f14 = f12 - (20.0f * f13);
                    if (f14 < 0.0f) {
                        f14 = 0.0f;
                    }
                    float textSize = textPaint.getTextSize();
                    qf qfVar2 = this.a;
                    String str4 = qfVar2.r;
                    String str5 = str3;
                    if (!k8.e(str5, str4)) {
                        qfVar2.r = str5;
                        qfVar2.s = SystemClock.uptimeMillis();
                    }
                    float f15 = textSize * 0.82f;
                    TextPaint textPaintY2 = pi.Y(-1, f15, false);
                    float f16 = f13 * 2.0f;
                    textPaintY2.setShadowLayer(8.0f * f13, 0.0f, f16, -1946157056);
                    float f17 = 2.15f * f15;
                    float f18 = f16 + fFloatValue;
                    float fMeasureText3 = textPaintY2.measureText("ооо") + (440.0f * f13);
                    if (f14 >= fMeasureText3) {
                        f14 = fMeasureText3;
                    }
                    float fMeasureText4 = textPaintY2.measureText(str5);
                    float f19 = f14 - (f18 - fFloatValue);
                    if (fMeasureText4 < f19) {
                        f19 = fMeasureText4;
                    }
                    float fAscent = ((f17 / 2.0f) + fFloatValue2) - ((textPaintY2.ascent() + textPaintY2.descent()) / 2.0f);
                    canvas.save();
                    float f20 = f18 + f19;
                    canvas.clipRect(f18, fFloatValue2, f20, f17 + fFloatValue2);
                    if (fMeasureText4 <= 0.5f + f19) {
                        canvas.drawText(str5, f18, fAscent, textPaintY2);
                    } else {
                        float f21 = (2.5f * f15) + fMeasureText4;
                        long jUptimeMillis = (SystemClock.uptimeMillis() - qfVar2.s) - 1500;
                        long j = jUptimeMillis >= 0 ? jUptimeMillis : 0L;
                        float f22 = (((j * 46.0f) * f13) / 1000.0f) % f21;
                        float f23 = (f15 * 1.2f) / f19;
                        textPaintY2.setShader(new LinearGradient(f18, 0.0f, f20, 0.0f, new int[]{l8.l(-1, 0), -1, -1, l8.l(-1, 0)}, new float[]{0.0f, j > 0 ? f23 : 0.0f, 1.0f - f23, 1.0f}, Shader.TileMode.CLAMP));
                        float f24 = f18 - f22;
                        canvas.drawText(str5, f24, fAscent, textPaintY2);
                        canvas.drawText(str5, f24 + f21, fAscent, textPaintY2);
                        textPaintY2.setShader(null);
                        qfVar2.postInvalidateDelayed(40L);
                    }
                    canvas.restore();
                    return ti.a;
                }
            }));
        }
    }

    public static final float c(qf qfVar, String str, float f, TextPaint textPaint, String str2, float f2) {
        int i = 0;
        List listI0 = j3.i0(Boolean.valueOf(qfVar.i), Boolean.valueOf(str != null));
        if (!listI0.isEmpty()) {
            Iterator it = listI0.iterator();
            while (it.hasNext()) {
                if (((Boolean) it.next()).booleanValue() && (i = i + 1) < 0) {
                    j3.l0();
                    throw null;
                }
            }
        }
        float fDescent = i > 0 ? textPaint.descent() + (textPaint.getTextSize() * 1.5f * (i - 1)) + f : 0.0f;
        if (str2 == null) {
            return fDescent;
        }
        if (i > 0) {
            f2 = c.a(textPaint, 0.75f, f2) - textPaint.descent();
        }
        return fDescent + f2;
    }

    @Override // android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.t.post(this.v);
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        this.t.removeCallbacks(this.v);
        super.onDetachedFromWindow();
    }

    /* JADX WARN: Failed to calculate best type for var: r0v15 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r0v15 ??, new type: android.text.TextPaint
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r0v23 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r0v23 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r0v25 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r0v25 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r0v26 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r0v26 ??, new type: android.graphics.Canvas
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r0v7 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r0v7 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r0v8 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r0v8 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r10v0 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r10v0 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r11v0 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r11v0 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:159)
        at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:136)
        at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:241)
        at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:224)
        at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 6 more
     */
    /* JADX WARN: Failed to calculate best type for var: r11v0 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r11v0 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r12v11 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r12v11 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r12v12 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r12v12 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r12v13 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r12v13 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r12v25 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r12v25 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r12v26 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r12v26 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r12v30 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r12v30 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r12v31 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r12v31 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r13v14 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r13v14 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r13v15 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r13v15 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r13v16 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r13v16 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r13v17 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r13v17 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r13v20 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r13v20 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r13v21 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r13v21 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r13v23 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r13v23 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r13v24 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r13v24 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r13v4 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r13v4 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r13v5 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r13v5 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r15v1 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r15v1 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r15v2 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r15v2 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r15v3 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r15v3 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r15v4 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r15v4 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r17v1 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r17v1 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r17v2 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r17v2 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r18v1 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r18v1 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r18v12 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r18v12 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r18v2 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r18v2 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r18v3 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r18v3 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r18v5 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r18v5 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r19v1 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r19v1 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r19v3 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r19v3 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r19v4 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r19v4 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r19v5 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r19v5 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r19v6 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r19v6 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r1v13 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r1v13 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r1v19 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r1v19 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r1v2 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r1v2 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r1v20 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r1v20 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r1v25 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r1v25 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r1v3 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r1v3 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r1v4 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r1v4 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r1v5 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r1v5 ??, new type: android.text.TextPaint
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r20v0 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r20v0 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r20v2 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r20v2 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r21v0 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r21v0 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r21v1 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r21v1 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r21v3 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r21v3 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r22v5 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r22v5 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r23v1 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r23v1 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r24v0 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r24v0 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r24v1 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r24v1 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r25v0 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r25v0 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r25v1 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r25v1 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r25v2 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r25v2 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r25v3 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r25v3 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r28v0 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r28v0 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r28v1 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r28v1 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r28v2 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r28v2 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r28v3 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r28v3 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r2v13 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r2v13 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r2v15 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r2v15 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r2v16 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r2v16 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r2v18 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r2v18 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r2v20 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r2v20 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r2v21 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r2v21 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r2v23 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r2v23 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r2v24 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r2v24 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r2v25 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r2v25 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r2v26 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r2v26 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r2v27 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r2v27 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r2v28 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r2v28 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r2v40 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r2v40 ??, new type: float[]
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r2v43 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r2v43 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r2v49 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r2v49 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r2v50 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r2v50 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r2v51 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r2v51 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r2v55 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r2v55 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r33v0 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r33v0 ??, new type: android.graphics.Canvas
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r3v13 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r3v13 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r3v15 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r3v15 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r3v16 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r3v16 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r3v17 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r3v17 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r3v2 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r3v2 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r3v28 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r3v28 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r3v29 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r3v29 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r3v30 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r3v30 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r3v5 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r3v5 ??, new type: android.text.TextPaint
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r4v15 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r4v15 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r4v16 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r4v16 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r4v17 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r4v17 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r4v18 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r4v18 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r4v19 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r4v19 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r4v20 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r4v20 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r4v24 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r4v24 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r4v26 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r4v26 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r4v27 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r4v27 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r4v30 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r4v30 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r4v34 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r4v34 ??, new type: java.lang.Number
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r4v35 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r4v35 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r4v36 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r4v36 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r4v48 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r4v48 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r4v49 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r4v49 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r4v51 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r4v51 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r4v52 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r4v52 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r4v53 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r4v53 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r4v54 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r4v54 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r5v0 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r5v0 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r5v1 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r5v1 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r5v14 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r5v14 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r5v15 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r5v15 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r5v2 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r5v2 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r5v23 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r5v23 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r5v24 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r5v24 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r5v25 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r5v25 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r5v3 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r5v3 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r5v6 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r5v6 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r6v12 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r6v12 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r6v31 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r6v31 ??, new type: java.lang.Number
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r6v32 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r6v32 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r6v33 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r6v33 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r7v0 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r7v0 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r7v10 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r7v10 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r7v11 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r7v11 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r7v12 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r7v12 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r7v13 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r7v13 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r7v16 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r7v16 ??, new type: android.graphics.Canvas
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r7v3 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r7v3 ??, new type: android.graphics.Canvas
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r7v4 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r7v4 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r7v5 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r7v5 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r7v6 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r7v6 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r7v7 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r7v7 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r8v17 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r8v17 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r8v18 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r8v18 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r9v10 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r9v10 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r9v2 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r9v2 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r9v25 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r9v25 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r9v28 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r9v28 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r9v29 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r9v29 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r9v3 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r9v3 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r9v30 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r9v30 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r9v31 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r9v31 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r9v7 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r9v7 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r9v8 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r9v8 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r9v9 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r9v9 ??, new type: float
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 7 more
     */
    /* JADX WARN: Failed to set immutable type for var: r33v0 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r33v0 ??, new type: android.graphics.Canvas
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.applyWithWiderIgnSame(TypeUpdate.java:73)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setImmutableType(TypeInferenceVisitor.java:111)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$1(TypeInferenceVisitor.java:102)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:102)
        at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        ... 6 more
     */
    /*  JADX ERROR: Types fix failed
        jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r11v0 ??, new type: float
            at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
            at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
            at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryPossibleTypes(FixTypesVisitor.java:186)
            at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:245)
            at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:224)
            at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
        Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
            at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
            at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
            at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
            at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
            at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
            at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
            ... 5 more
        */
    @Override // android.view.View
    public final void onDraw(android.graphics.Canvas r33) {
        /*
            Method dump skipped, instruction units count: 1282
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.qf.onDraw(android.graphics.Canvas):void");
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0050  */
    /* JADX WARN: Code duplicated, block: B:20:0x0054  */
    /* JADX WARN: Code duplicated, block: B:23:0x0060  */
    /* JADX WARN: Code duplicated, block: B:29:0x0075  */
    /* JADX WARN: Code duplicated, block: B:55:0x0070 A[SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:29:0x0075, please report this as an issue */
    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        Object objPrevious;
        String str;
        Iterator it;
        Object next;
        pf pfVar;
        motionEvent.getClass();
        if (this.d) {
            int i = l8.a;
            Context context = getContext();
            context.getClass();
            float fM = l8.m(context);
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                List list = this.l;
                ListIterator listIterator = list.listIterator(list.size());
                while (true) {
                    if (!listIterator.hasPrevious()) {
                        objPrevious = null;
                        break;
                    }
                    objPrevious = listIterator.previous();
                    pf pfVar2 = (pf) objPrevious;
                    float x2 = motionEvent.getX();
                    float f = pfVar2.b;
                    float f2 = pfVar2.c;
                    float f3 = 16.0f * fM;
                    if (x2 >= f - f3 && motionEvent.getX() <= pfVar2.b + pfVar2.d + f3 && motionEvent.getY() >= f2 - f3 && motionEvent.getY() <= f2 + pfVar2.e + f3) {
                        break;
                    }
                }
                pf pfVar3 = (pf) objPrevious;
                this.n = pfVar3 != null ? pfVar3.a : null;
                if (pfVar3 != null) {
                    this.o = motionEvent.getX() - pfVar3.b;
                    this.p = motionEvent.getY() - pfVar3.c;
                    ViewParent parent = getParent();
                    if (parent != null) {
                        parent.requestDisallowInterceptTouchEvent(true);
                    }
                }
                invalidate();
                return true;
            }
            HashMap map = this.m;
            if (actionMasked == 1) {
                str = this.n;
                if (str != null) {
                    it = this.l.iterator();
                    do {
                        if (it.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it.next();
                    } while (!((pf) next).a.equals(str));
                    pfVar = (pf) next;
                    if (pfVar != null) {
                        float f4 = pfVar.c;
                        float f5 = pfVar.b;
                        this.e.edit().putString("saver_pos_".concat(str), (f5 / getWidth()) + "," + (f4 / getHeight())).apply();
                        map.put(str, new yc(Float.valueOf(f5), Float.valueOf(f4)));
                    }
                    this.n = null;
                    invalidate();
                }
            } else if (actionMasked == 2) {
                String str2 = this.n;
                if (str2 != null) {
                    map.put(str2, new yc(Float.valueOf(motionEvent.getX() - this.o), Float.valueOf(motionEvent.getY() - this.p)));
                    invalidate();
                    return true;
                }
            } else if (actionMasked == 3) {
                str = this.n;
                if (str != null) {
                    it = this.l.iterator();
                    do {
                        if (it.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it.next();
                    } while (!((pf) next).a.equals(str));
                    pfVar = (pf) next;
                    if (pfVar != null) {
                        float f6 = pfVar.c;
                        float f7 = pfVar.b;
                        this.e.edit().putString("saver_pos_".concat(str), (f7 / getWidth()) + "," + (f6 / getHeight())).apply();
                        map.put(str, new yc(Float.valueOf(f7), Float.valueOf(f6)));
                    }
                    this.n = null;
                    invalidate();
                }
            }
        }
        return true;
    }
}
