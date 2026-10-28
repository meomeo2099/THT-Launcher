package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class cj extends View {
    public static final float[] n = {0.795f, 0.33f, 0.022f, 0.69f, 0.5f, 0.042f, 0.66f, 0.572f, 0.046f, 0.395f, 0.572f, 0.046f, 0.3f, 0.73f, 0.032f, 0.248f, 0.84f, 0.014f};
    public static final float[] o = {0.305f, 0.05f, 0.475f, 0.05f, 0.6f, 0.14f, 0.57f, 0.275f, 0.475f, 0.34f, 0.305f, 0.34f, 0.21f, 0.275f, 0.18f, 0.14f};
    public final String a;
    public final boolean b;
    public final Paint c;
    public Integer d;
    public Integer e;
    public Boolean f;
    public boolean g;
    public boolean h;
    public final RectF i;
    public Integer j;
    public Integer k;
    public boolean l;
    public int m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:28:0x00ad  */
    public cj(Context context, String str) {
        RectF rectF;
        super(context);
        context.getClass();
        this.a = str;
        boolean z = false;
        if (str.equals("wiper_heat") && k8.e(context.getSharedPreferences("panel", 0).getString("vehicle", null), "geely_tugella")) {
            z = true;
        }
        this.b = z;
        Paint paint = new Paint(1);
        paint.setStrokeWidth(4.9f);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        this.c = paint;
        this.g = true;
        switch (str) {
            case "roof_tilt":
                rectF = new RectF(14.0f, 1.0f, 106.0f, 93.0f);
                break;
            case "wipers":
                rectF = new RectF(14.0f, -2.0f, 106.0f, 90.0f);
                break;
            case "ext_light":
                rectF = new RectF(19.0f, 2.0f, 109.0f, 92.0f);
                break;
            case "trunk":
                rectF = new RectF(16.0f, 7.0f, 104.0f, 95.0f);
                break;
            case "mirrors":
                rectF = new RectF(20.0f, 8.0f, 102.0f, 90.0f);
                break;
            default:
                rectF = new RectF(10.0f, -2.0f, 110.0f, 98.0f);
                break;
        }
        this.i = rectF;
        this.l = true;
        this.m = 3;
    }

    public static Path a(float[] fArr) {
        int length = fArr.length / 3;
        Path path = new Path();
        for (int i = 0; i < length; i++) {
            yc ycVarB = b(fArr, length, i);
            float fFloatValue = ((Number) ycVarB.a).floatValue();
            float fFloatValue2 = ((Number) ycVarB.b).floatValue();
            int i2 = i * 3;
            float f = fArr[i2];
            float f2 = fArr[i2 + 2];
            float f3 = ((fFloatValue * f2) + f) * 96.0f;
            float f4 = ((fFloatValue2 * f2) + fArr[i2 + 1]) * 96.0f;
            if (i == 0) {
                path.moveTo(f3, f4);
            } else {
                path.lineTo(f3, f4);
            }
        }
        for (int i3 = length - 1; -1 < i3; i3--) {
            yc ycVarB2 = b(fArr, length, i3);
            float fFloatValue3 = ((Number) ycVarB2.a).floatValue();
            float fFloatValue4 = ((Number) ycVarB2.b).floatValue();
            int i4 = i3 * 3;
            float f5 = fArr[i4];
            float f6 = fArr[i4 + 2];
            path.lineTo((f5 - (fFloatValue3 * f6)) * 96.0f, (fArr[i4 + 1] - (fFloatValue4 * f6)) * 96.0f);
        }
        path.close();
        return path;
    }

    public static final yc b(float[] fArr, int i, int i2) {
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        if (i2 == 0) {
            f5 = fArr[3] - fArr[0];
            f4 = fArr[4] - fArr[1];
        } else {
            if (i2 == i - 1) {
                int i3 = i2 * 3;
                int i4 = (i2 - 1) * 3;
                f = fArr[i3] - fArr[i4];
                f2 = fArr[i3 + 1];
                f3 = fArr[i4 + 1];
            } else {
                int i5 = (i2 + 1) * 3;
                int i6 = (i2 - 1) * 3;
                f = fArr[i5] - fArr[i6];
                f2 = fArr[i5 + 1];
                f3 = fArr[i6 + 1];
            }
            f4 = f2 - f3;
            f5 = f;
        }
        float fHypot = (float) Math.hypot(f5, f4);
        Float fValueOf = Float.valueOf(fHypot);
        if (fHypot <= 0.0f) {
            fValueOf = null;
        }
        float fFloatValue = fValueOf != null ? fValueOf.floatValue() : 1.0f;
        return new yc(Float.valueOf((-f4) / fFloatValue), Float.valueOf(f5 / fFloatValue));
    }

    public static final void d(Canvas canvas, cj cjVar, float f, float f2, float f3, float f4) {
        canvas.drawLine(f, f2, f3, f4, cjVar.c);
    }

    public static final void e(Canvas canvas, cj cjVar, float... fArr) {
        Path path = new Path();
        path.moveTo(fArr[0], fArr[1]);
        h8 h8VarW = pi.W(pi.f0(2, fArr.length), 2);
        int i = h8VarW.a;
        int i2 = h8VarW.b;
        int i3 = h8VarW.c;
        if ((i3 > 0 && i <= i2) || (i3 < 0 && i2 <= i)) {
            while (true) {
                path.lineTo(fArr[i], fArr[i + 1]);
                if (i == i2) {
                    break;
                } else {
                    i += i3;
                }
            }
        }
        canvas.drawPath(path, cjVar.c);
    }

    public final void c(Canvas canvas, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        Path pathA = a(new float[]{f, f2, 0.01f, f3, f4, f5});
        Paint paint = this.c;
        canvas.drawPath(pathA, paint);
        float f9 = f6 - f3;
        float f10 = f7 - f4;
        float fHypot = (float) Math.hypot(f9, f10);
        Float fValueOf = Float.valueOf(fHypot);
        if (fHypot <= 0.0f) {
            fValueOf = null;
        }
        if (fValueOf != null) {
            float fFloatValue = fValueOf.floatValue();
            float f11 = (-f10) / fFloatValue;
            float f12 = f9 / fFloatValue;
            Path path = new Path();
            path.moveTo(f6 * 96.0f, f7 * 96.0f);
            float f13 = f11 * f8;
            float f14 = f12 * f8;
            path.lineTo((f3 + f13) * 96.0f, (f4 + f14) * 96.0f);
            path.lineTo((f3 - f13) * 96.0f, (f4 - f14) * 96.0f);
            path.close();
            canvas.drawPath(path, paint);
        }
    }

    public final int getActiveAccent() {
        String str = this.a;
        if (k8.e(str, "steering") || j3.i0("drive_sport", "drive_sport_plus", "drive_power").contains(str)) {
            int i = l8.a;
            return Color.rgb(255, 137, 117);
        }
        if (k8.e(str, "drive_eco") || k8.e(str, "start_stop")) {
            return l8.g();
        }
        if (ci.t(str, "seat_heat")) {
            l8.i();
            return -16777;
        }
        l8.h();
        return -10233877;
    }

    public final boolean getActiveSurface() {
        return this.h;
    }

    public final Integer getBlowMode() {
        return this.j;
    }

    public final Integer getCruiseMode() {
        return this.d;
    }

    public final Boolean getEnabledState() {
        return this.f;
    }

    public final Integer getFlowInk() {
        return this.k;
    }

    public final boolean getHalo() {
        return this.l;
    }

    public final Integer getLevel() {
        return this.e;
    }

    public final int getMaxLevel() {
        return this.m;
    }

    public final boolean getNightMode() {
        return this.g;
    }

    /* JADX WARN: Code duplicated, block: B:152:0x03fe  */
    /* JADX WARN: Code duplicated, block: B:154:0x040f  */
    /* JADX WARN: Code duplicated, block: B:156:0x042a  */
    /* JADX WARN: Code duplicated, block: B:159:0x0465  */
    /* JADX WARN: Code duplicated, block: B:162:0x047d A[LOOP:2: B:160:0x0477->B:162:0x047d, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:163:0x04a8  */
    /* JADX WARN: Code duplicated, block: B:165:0x04b3 A[LOOP:4: B:164:0x04b1->B:165:0x04b3, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:168:0x04eb  */
    /* JADX WARN: Code duplicated, block: B:170:0x04fc  */
    /* JADX WARN: Code duplicated, block: B:173:0x0546 A[LOOP:5: B:171:0x0540->B:173:0x0546, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:174:0x0571  */
    /* JADX WARN: Code duplicated, block: B:176:0x0585 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:177:0x0587  */
    /* JADX WARN: Code duplicated, block: B:180:0x05c3 A[LOOP:6: B:178:0x05bd->B:180:0x05c3, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:181:0x05ee  */
    /* JADX WARN: Code duplicated, block: B:184:0x063a A[LOOP:7: B:182:0x0634->B:184:0x063a, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:186:0x066a  */
    /* JADX WARN: Code duplicated, block: B:188:0x0674  */
    /* JADX WARN: Code duplicated, block: B:190:0x06ab  */
    /* JADX WARN: Code duplicated, block: B:194:0x06bb  */
    /* JADX WARN: Code duplicated, block: B:298:0x0c77  */
    /* JADX WARN: Code duplicated, block: B:299:0x0c9e  */
    /* JADX WARN: Code duplicated, block: B:300:0x0cc9  */
    /* JADX WARN: Code duplicated, block: B:303:0x0cdb  */
    /* JADX WARN: Code duplicated, block: B:305:0x0cf3  */
    /* JADX WARN: Code duplicated, block: B:306:0x0cf9  */
    /* JADX WARN: Code duplicated, block: B:309:0x0d00  */
    /* JADX WARN: Code duplicated, block: B:311:0x0d03  */
    /* JADX WARN: Code duplicated, block: B:312:0x0d06  */
    /* JADX WARN: Code duplicated, block: B:315:0x0d26 A[LOOP:3: B:310:0x0d01->B:315:0x0d26, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:316:0x0d29  */
    /* JADX WARN: Code duplicated, block: B:325:0x0d2b A[EDGE_INSN: B:325:0x0d2b->B:317:0x0d2b BREAK  A[LOOP:3: B:310:0x0d01->B:315:0x0d26], SYNTHETIC] */
    @Override // android.view.View
    public final void onDraw(Canvas canvas) throws IllegalAccessException, InvocationTargetException {
        int iRgb;
        int i;
        Paint paint;
        int i2;
        Canvas canvas2;
        boolean zBooleanValue;
        String str;
        cj cjVar;
        float f;
        Shader shader;
        Canvas canvas3;
        float f2;
        Integer num;
        int iIntValue;
        int i3;
        int i4;
        int i5;
        Canvas canvas4;
        String str2;
        Canvas canvas5;
        Iterator it;
        Iterator it2;
        Iterator it3;
        int i6;
        Iterator it4;
        Boolean boolValueOf;
        int iJ;
        int i7;
        int i8;
        Canvas canvas6;
        Integer num2;
        Integer num3;
        Integer num4;
        float f3;
        int i9;
        cj cjVar2;
        int i10;
        boolean z = this.b;
        canvas.getClass();
        super.onDraw(canvas);
        float width = getWidth();
        RectF rectF = this.i;
        float fMin = Math.min(width / rectF.width(), getHeight() / rectF.height());
        int iSave = canvas.save();
        canvas.translate((getWidth() / 2.0f) - (rectF.centerX() * fMin), (getHeight() / 2.0f) - (rectF.centerY() * fMin));
        canvas.scale(fMin, fMin);
        Float fValueOf = Float.valueOf(76.0f);
        Float fValueOf2 = Float.valueOf(44.0f);
        Float fValueOf3 = Float.valueOf(84.0f);
        Float fValueOf4 = Float.valueOf(60.0f);
        Paint paint2 = this.c;
        canvas.save();
        String str3 = this.a;
        boolean zT = ci.t(str3, "seat_heat");
        Boolean bool = this.f;
        Boolean bool2 = Boolean.TRUE;
        if (k8.e(bool, bool2)) {
            iRgb = getActiveAccent();
        } else if (k8.e(bool, Boolean.FALSE)) {
            int i11 = l8.a;
            iRgb = l8.j(this.g);
        } else {
            if (bool != null) {
                throw new vc();
            }
            iRgb = this.g ? Color.rgb(114, 136, 153) : Color.rgb(91, 111, 128);
        }
        if (k8.e(this.f, bool2) && this.h) {
            int i12 = l8.a;
            iRgb = l8.b(this.g, true, iRgb);
        } else if (k8.e(this.f, bool2)) {
            int i13 = l8.a;
            iRgb = l8.a(iRgb, this.g);
        }
        String strA = "front-defrost";
        if (str3.equals("airflow")) {
            Integer num5 = this.j;
            boolean z2 = this.h && k8.e(this.f, bool2) && !this.g;
            Integer num6 = this.k;
            int iA = -1;
            if (z2) {
                i7 = -1;
            } else {
                if (num5 == null) {
                    iJ = this.g ? Color.rgb(114, 136, 153) : Color.rgb(91, 111, 128);
                } else if (num6 != null) {
                    iJ = num6.intValue();
                } else {
                    int i14 = l8.a;
                    iJ = l8.j(this.g);
                }
                i7 = iJ;
            }
            if (z2) {
                i8 = iA;
            } else if (num6 == null && k8.e(this.f, bool2)) {
                l8.h();
                iA = l8.a(-10233877, this.g);
                i8 = iA;
            } else {
                i8 = i7;
            }
            l0 l0Var = ba.h;
            ba baVar = ba.k;
            int i15 = 268894466;
            if (baVar != null) {
                List list = z2.a;
                if ((num5 != null && num5.intValue() == 268894465) || (num5 != null && num5.intValue() == 268894469)) {
                    strA = "air-upper";
                } else if ((num5 != null && num5.intValue() == 268894466) || (num5 != null && num5.intValue() == 268894470)) {
                    strA = "air-lower";
                } else if ((num5 != null && num5.intValue() == 268894467) || ((num5 != null && num5.intValue() == 268894471) || (num5 != null && num5.intValue() == 268894472))) {
                    strA = "air-both";
                } else if (num5 == null || num5.intValue() != 268894468) {
                    strA = null;
                }
                if (strA == null) {
                    i10 = i8;
                    num2 = num5;
                    i = iSave;
                    num3 = 268894470;
                    num4 = 268894471;
                    f3 = 4.9f;
                    i9 = 268894465;
                } else {
                    num2 = num5;
                    i = iSave;
                    num4 = 268894471;
                    f3 = 4.9f;
                    i9 = 268894465;
                    num3 = 268894470;
                    i10 = i8;
                    if (baVar.a(canvas, strA, i8, 12.0f, 0.0f, 96.0f)) {
                        if (i3.r0(j3.i0(268894469, 268894470, 268894471), num2)) {
                            baVar.a(canvas, "front-defrost", i10, 12.0f, 0.0f, 40.32f);
                            canvas3 = canvas;
                        } else {
                            canvas3 = canvas;
                        }
                    }
                    canvas3.restore();
                }
                i8 = i10;
                canvas6 = canvas;
            } else {
                canvas6 = canvas;
                num2 = num5;
                i = iSave;
                num3 = 268894470;
                num4 = 268894471;
                f3 = 4.9f;
                i15 = 268894466;
                i9 = 268894465;
            }
            List list2 = z2.a;
            boolean zR0 = i3.r0(j3.i0(Integer.valueOf(i9), 268894467, 268894469, num4, 268894472), num2);
            boolean zR1 = i3.r0(j3.i0(Integer.valueOf(i15), 268894467, num3, num4, 268894472), num2);
            boolean zR2 = i3.r0(j3.i0(268894468, 268894469, num3, num4), num2);
            canvas6.save();
            canvas6.translate(12.0f, 2.0f);
            this.c.setStyle(Paint.Style.FILL);
            this.c.setAlpha(255);
            this.c.setColor(i7);
            canvas6.drawCircle(77.951996f, 20.16f, 6.0480003f, this.c);
            canvas6.drawPath(a(n), this.c);
            this.c.setColor(i8);
            if (zR0) {
                c(canvas, 0.145f, 0.333f, 0.42f, 0.383f, 0.032f, 0.525f, 0.383f, 0.06f);
            }
            if (zR1) {
                c(canvas, 0.05f, 0.41f, 0.215f, 0.63f, 0.03f, 0.3f, 0.74f, 0.058f);
                cjVar2 = this;
                canvas3 = canvas;
            } else {
                cjVar2 = this;
                canvas3 = canvas;
            }
            if (zR2) {
                cjVar2.c.setStyle(Paint.Style.STROKE);
                cjVar2.c.setStrokeWidth(3.2640002f);
                Path path = new Path();
                int iD = pi.D(0, 15, 2);
                if (iD >= 0) {
                    int i16 = 0;
                    while (true) {
                        float[] fArr = o;
                        float f4 = fArr[i16] * 96.0f;
                        float f5 = fArr[i16 + 1] * 96.0f;
                        if (i16 == 0) {
                            path.moveTo(f4, f5);
                        } else {
                            path.lineTo(f4, f5);
                        }
                        if (i16 == iD) {
                            break;
                        } else {
                            i16 += 2;
                        }
                    }
                }
                path.close();
                canvas3.drawPath(path, cjVar2.c);
                cjVar2.c.setStrokeWidth(2.8799999f);
                float[] fArr2 = {0.27f, 0.355f, 0.44f};
                for (int i17 = 0; i17 < 3; i17++) {
                    float f6 = fArr2[i17] * 96.0f;
                    canvas3.drawLine(f6, 12.960001f, f6, 25.439999f, cjVar2.c);
                }
                cjVar2.c.setStrokeWidth(f3);
            }
            canvas3.restore();
            canvas3.restore();
        } else {
            i = iSave;
            Paint.Style style = Paint.Style.FILL;
            paint.setStyle(style);
            paint.setColor(iRgb);
            paint.setAlpha(22);
            if (this.l) {
                paint = paint2;
                canvas.drawCircle(60.0f, 46.0f, 43.0f, paint);
            }
            paint = paint2;
            if (this.l && k8.e(this.f, bool2)) {
                i2 = 255;
                paint.setAlpha(255);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(2.0f);
                canvas.drawCircle(60.0f, 46.0f, 43.0f, paint);
                paint.setStrokeWidth(4.9f);
            } else {
                i2 = 255;
            }
            paint.setAlpha(i2);
            Paint.Style style2 = Paint.Style.STROKE;
            paint.setStyle(style2);
            paint.setColor(iRgb);
            if (!z) {
                l0 l0Var2 = ba.h;
                strA = l0.A(str3);
            }
            if (strA != null) {
                l0 l0Var3 = ba.h;
                ba baVar2 = ba.k;
                if (baVar2 != null) {
                    canvas2 = canvas;
                    boolValueOf = Boolean.valueOf(baVar2.a(canvas2, strA, iRgb, 12.0f, 0.0f, 96.0f));
                } else {
                    canvas2 = canvas;
                    boolValueOf = null;
                }
                zBooleanValue = boolValueOf != null ? boolValueOf.booleanValue() : false;
                if (zBooleanValue) {
                    str = "seat_";
                    cjVar = this;
                    f = 18.0f;
                } else {
                    if (str3.startsWith("seat_")) {
                        canvas2.save();
                        if (j3.i0("4", "64").contains(th.E(str3, '_'))) {
                            canvas2.translate(120.0f, 0.0f);
                            canvas2.scale(-1.0f, 1.0f);
                        }
                        str = "seat_";
                        canvas.drawRoundRect(33.0f, 12.0f, 49.0f, 28.0f, 6.0f, 6.0f, paint);
                        e(canvas, this, 36.0f, 35.0f, 42.0f, 64.0f, 76.0f, 64.0f, 82.0f, 72.0f, 39.0f, 72.0f, 29.0f, 39.0f);
                        e(canvas, this, 43.0f, 73.0f, 39.0f, 82.0f, 81.0f, 82.0f);
                        if (zT) {
                            it4 = j3.i0(fValueOf4, Float.valueOf(72.0f), fValueOf3).iterator();
                            while (it4.hasNext()) {
                                float fFloatValue = ((Number) it4.next()).floatValue();
                                Path path2 = new Path();
                                path2.moveTo(fFloatValue, 54.0f);
                                path2.cubicTo(fFloatValue - 9.0f, 43.0f, fFloatValue + 9.0f, 38.0f, fFloatValue, 27.0f);
                                canvas.drawPath(path2, paint);
                            }
                        } else {
                            canvas.drawCircle(75.0f, 38.0f, 4.0f, paint);
                            for (i6 = 0; i6 < 4; i6++) {
                                canvas.save();
                                canvas.rotate(i6 * 90.0f, 75.0f, 38.0f);
                                Path path3 = new Path();
                                path3.moveTo(78.0f, 35.0f);
                                path3.cubicTo(99.0f, 20.0f, 89.0f, 12.0f, 77.0f, 29.0f);
                                canvas.drawPath(path3, paint);
                                canvas.restore();
                            }
                        }
                        canvas.restore();
                    } else {
                        canvas4 = canvas2;
                        str = "seat_";
                        if (str3.equals("nozzle_heat")) {
                            canvas4.drawRoundRect(48.0f, 52.0f, 72.0f, 62.0f, 4.0f, 4.0f, paint);
                            canvas.drawLine(55.0f, 44.0f, 45.0f, 24.0f, this.c);
                            canvas.drawLine(60.0f, 42.0f, 60.0f, 20.0f, this.c);
                            canvas.drawLine(65.0f, 44.0f, 75.0f, 24.0f, this.c);
                            it3 = j3.i0(fValueOf2, fValueOf4, fValueOf).iterator();
                            while (it3.hasNext()) {
                                float fFloatValue2 = ((Number) it3.next()).floatValue();
                                Path path4 = new Path();
                                path4.moveTo(fFloatValue2, 84.0f);
                                path4.cubicTo(fFloatValue2 - 5.0f, 79.0f, fFloatValue2 + 5.0f, 74.0f, fFloatValue2, 69.0f);
                                canvas.drawPath(path4, paint);
                            }
                        } else {
                            if (k8.e(str3, "wiper_heat")) {
                                f = 18.0f;
                                if (k8.e(str3, "curtain")) {
                                    canvas4.drawRoundRect(24.0f, 18.0f, 96.0f, 76.0f, 9.0f, 9.0f, paint);
                                    canvas.drawRoundRect(37.0f, 30.0f, 83.0f, 61.0f, 4.0f, 4.0f, paint);
                                    cjVar = this;
                                    d(canvas, cjVar, 37.0f, 38.0f, 83.0f, 38.0f);
                                    d(canvas, cjVar, 37.0f, 46.0f, 83.0f, 46.0f);
                                } else if (!k8.e(str3, "roof") || k8.e(str3, "roof_tilt")) {
                                    canvas.drawRoundRect(24.0f, 18.0f, 96.0f, 76.0f, 9.0f, 9.0f, paint);
                                    if (k8.e(str3, "roof")) {
                                        canvas.drawRoundRect(37.0f, 30.0f, 83.0f, 61.0f, 4.0f, 4.0f, paint);
                                        e(canvas, this, 50.0f, 48.0f, 60.0f, 38.0f, 70.0f, 48.0f);
                                        cjVar = this;
                                        d(canvas, cjVar, 60.0f, 38.0f, 60.0f, 58.0f);
                                    } else {
                                        cjVar = this;
                                        e(canvas, cjVar, 35.0f, 59.0f, 83.0f, 41.0f, 83.0f, 61.0f);
                                        d(canvas, cjVar, 36.0f, 65.0f, 83.0f, 65.0f);
                                        e(canvas, cjVar, 49.0f, 37.0f, 57.0f, 29.0f, 65.0f, 37.0f);
                                        d(canvas, cjVar, 57.0f, 29.0f, 57.0f, 44.0f);
                                    }
                                } else if (k8.e(str3, "drive_eco")) {
                                    Path path5 = new Path();
                                    path5.moveTo(34.0f, 74.0f);
                                    path5.cubicTo(30.0f, 40.0f, 56.0f, 22.0f, 90.0f, 20.0f);
                                    path5.cubicTo(92.0f, 54.0f, 70.0f, 74.0f, 34.0f, 74.0f);
                                    canvas4.drawPath(path5, paint);
                                    cjVar = this;
                                    d(canvas4, cjVar, 34.0f, 74.0f, 72.0f, 38.0f);
                                } else if (k8.e(str3, "drive_comfort")) {
                                    canvas5 = canvas;
                                    canvas5.drawRoundRect(40.0f, 22.0f, 80.0f, 54.0f, 8.0f, 8.0f, paint);
                                    canvas5.drawRoundRect(28.0f, 48.0f, 92.0f, 66.0f, 7.0f, 7.0f, paint);
                                    cjVar = this;
                                    d(canvas5, cjVar, 36.0f, 66.0f, 36.0f, 78.0f);
                                    d(canvas5, cjVar, 84.0f, 66.0f, 84.0f, 78.0f);
                                } else {
                                    cj cjVar3 = this;
                                    Canvas canvas7 = canvas;
                                    if (k8.e(str3, "drive_sport") || k8.e(str3, "drive_sport_plus")) {
                                        Canvas canvas8 = canvas7;
                                        float f7 = k8.e(str3, "drive_sport_plus") ? 54.0f : 60.0f;
                                        canvas8.drawArc(f7 - 34.0f, 22.0f, f7 + 34.0f, 90.0f, 180.0f, 180.0f, false, paint);
                                        paint = paint;
                                        Iterator it5 = j3.i0(Float.valueOf(200.0f), Float.valueOf(240.0f), Float.valueOf(280.0f), Float.valueOf(320.0f)).iterator();
                                        while (it5.hasNext()) {
                                            float fFloatValue3 = ((Number) it5.next()).floatValue();
                                            canvas8.save();
                                            canvas8.rotate(fFloatValue3 + 90.0f, f7, 56.0f);
                                            d(canvas8, this, f7, 26.0f, f7, 33.0f);
                                            canvas.restore();
                                            canvas8 = canvas;
                                        }
                                        float f8 = f7;
                                        d(canvas, this, f8, 56.0f, f8 + 20.0f, 36.0f);
                                        canvas.drawCircle(f8, 56.0f, 4.0f, paint);
                                        if (k8.e(str3, "drive_sport_plus")) {
                                            cjVar = this;
                                            d(canvas, cjVar, 84.0f, 66.0f, 98.0f, 66.0f);
                                            d(canvas, cjVar, 91.0f, 59.0f, 91.0f, 73.0f);
                                        } else {
                                            shader = null;
                                            cjVar = this;
                                        }
                                    } else {
                                        if (k8.e(str3, "drive_power")) {
                                            e(canvas7, cjVar3, 68.0f, 14.0f, 40.0f, 52.0f, 58.0f, 52.0f, 50.0f, 82.0f, 82.0f, 40.0f, 63.0f, 40.0f, 68.0f, 14.0f);
                                        } else if (k8.e(str3, "drive_snow")) {
                                            Iterator it6 = j3.i0(Float.valueOf(0.0f), fValueOf4, Float.valueOf(120.0f)).iterator();
                                            while (it6.hasNext()) {
                                                float fFloatValue4 = ((Number) it6.next()).floatValue();
                                                canvas7.save();
                                                canvas7.rotate(fFloatValue4, 60.0f, 47.0f);
                                                cj cjVar4 = cjVar3;
                                                d(canvas, cjVar4, 60.0f, 15.0f, 60.0f, 79.0f);
                                                canvas7 = canvas;
                                                cjVar3 = cjVar4;
                                                e(canvas7, cjVar3, 52.0f, 22.0f, 60.0f, 30.0f, 68.0f, 22.0f);
                                                e(canvas7, cjVar3, 52.0f, 72.0f, 60.0f, 64.0f, 68.0f, 72.0f);
                                                canvas7.restore();
                                            }
                                        } else if (k8.e(str3, "drive_offroad")) {
                                            e(canvas7, cjVar3, 20.0f, 76.0f, 46.0f, 32.0f, 62.0f, 56.0f, 74.0f, 40.0f, 100.0f, 76.0f);
                                            cjVar = cjVar3;
                                            d(canvas, cjVar, 20.0f, 76.0f, 100.0f, 76.0f);
                                        } else {
                                            cj cjVar5 = cjVar3;
                                            if (k8.e(str3, "drive_sand")) {
                                                e(canvas7, cjVar5, 20.0f, 74.0f, 40.0f, 58.0f, 60.0f, 70.0f, 80.0f, 54.0f, 100.0f, 70.0f);
                                                e(canvas7, cjVar5, 20.0f, 84.0f, 44.0f, 72.0f, 68.0f, 82.0f, 100.0f, 68.0f);
                                                canvas7.drawCircle(84.0f, 30.0f, 11.0f, paint);
                                            } else if (k8.e(str3, "trunk")) {
                                                canvas7.drawRoundRect(26.0f, 54.0f, 94.0f, 76.0f, 6.0f, 6.0f, paint);
                                                e(canvas7, cjVar5, 28.0f, 54.0f, 34.0f, 28.0f, 78.0f, 22.0f);
                                                d(canvas7, cjVar5, 78.0f, 22.0f, 92.0f, 50.0f);
                                                cjVar = this;
                                                canvas5 = canvas;
                                                d(canvas5, cjVar, 60.0f, 62.0f, 60.0f, 68.0f);
                                            } else if (k8.e(str3, "mirrors")) {
                                                e(canvas7, cjVar5, 30.0f, 36.0f, 74.0f, 30.0f, 80.0f, 48.0f, 36.0f, 54.0f, 30.0f, 36.0f);
                                                d(canvas, cjVar5, 74.0f, 44.0f, 92.0f, 44.0f);
                                                e(canvas, cjVar5, 86.0f, 38.0f, 92.0f, 44.0f, 86.0f, 50.0f);
                                                d(canvas, cjVar5, 30.0f, 62.0f, 60.0f, 62.0f);
                                                cjVar3 = cjVar5;
                                                e(canvas, cjVar3, 36.0f, 56.0f, 30.0f, 62.0f, 36.0f, 68.0f);
                                            } else if (k8.e(str3, "ext_light")) {
                                                e(canvas7, cjVar5, 30.0f, 20.0f, 52.0f, 20.0f, 64.0f, 34.0f, 64.0f, 60.0f, 52.0f, 74.0f, 30.0f, 74.0f, 30.0f, 20.0f);
                                                canvas5 = canvas;
                                                d(canvas5, cjVar5, 74.0f, 30.0f, 98.0f, 30.0f);
                                                cjVar = this;
                                                d(canvas5, cjVar, 74.0f, 47.0f, 98.0f, 47.0f);
                                                d(canvas5, cjVar, 74.0f, 64.0f, 98.0f, 64.0f);
                                            } else if (k8.e(str3, "wipers")) {
                                                e(canvas7, cjVar5, 24.0f, 66.0f, 34.0f, 32.0f, 86.0f, 32.0f, 96.0f, 66.0f);
                                                d(canvas, cjVar5, 24.0f, 66.0f, 96.0f, 66.0f);
                                                cjVar = this;
                                                d(canvas, cjVar, 36.0f, 62.0f, 78.0f, 26.0f);
                                                d(canvas, cjVar, 74.0f, 22.0f, 84.0f, 30.0f);
                                            } else {
                                                if (k8.e(str3, "drive_adaptive")) {
                                                    for (yc ycVar : j3.i0(uh.l(Float.valueOf(36.0f), Float.valueOf(58.0f)), uh.l(fValueOf4, Float.valueOf(34.0f)), uh.l(fValueOf3, Float.valueOf(50.0f)))) {
                                                        float fFloatValue5 = ((Number) ycVar.a).floatValue();
                                                        float fFloatValue6 = ((Number) ycVar.b).floatValue();
                                                        d(canvas, this, fFloatValue5, 18.0f, fFloatValue5, fFloatValue6 - 7.0f);
                                                        d(canvas, this, fFloatValue5, fFloatValue6 + 7.0f, fFloatValue5, 78.0f);
                                                        canvas.drawCircle(fFloatValue5, fFloatValue6, 7.0f, paint);
                                                    }
                                                } else if (k8.e(str3, "cruise")) {
                                                    canvas.drawArc(30.0f, 10.0f, 90.0f, 70.0f, 160.0f, 220.0f, false, paint);
                                                    d(canvas, this, 60.0f, 40.0f, 77.0f, 24.0f);
                                                    d(canvas, this, 27.0f, 64.0f, 38.0f, 51.0f);
                                                    d(canvas, this, 93.0f, 64.0f, 82.0f, 51.0f);
                                                    cjVar5 = this;
                                                    paint.setStyle(style);
                                                    paint.setTextAlign(Paint.Align.CENTER);
                                                    paint.setTextSize(21.0f);
                                                    paint.setTypeface(Typeface.create("sans-serif-medium", 1));
                                                    Integer num7 = cjVar5.d;
                                                    if (num7 != null && num7.intValue() == 537069058) {
                                                        paint = paint;
                                                        str2 = "ACC";
                                                    } else {
                                                        str2 = (num7 != null && num7.intValue() == 537069059) ? "G-Pilot" : "—";
                                                    }
                                                    canvas.drawText(str2, 60.0f, 91.0f, paint);
                                                    paint.setStyle(style2);
                                                } else {
                                                    if (k8.e(str3, "start_stop")) {
                                                        canvas.drawArc(28.0f, 15.0f, 92.0f, 79.0f, -60.0f, 300.0f, false, paint);
                                                        paint = paint;
                                                        e(canvas, this, 80.0f, 14.0f, 76.0f, 26.0f, 88.0f, 28.0f);
                                                        e(canvas, this, 48.0f, 62.0f, 60.0f, 32.0f, 72.0f, 62.0f);
                                                        d(canvas, this, 53.0f, 51.0f, 67.0f, 51.0f);
                                                    } else if (k8.e(str3, "steering")) {
                                                        canvas.drawCircle(60.0f, 49.0f, 40.0f, paint);
                                                        canvas.drawCircle(60.0f, 49.0f, 10.0f, paint);
                                                        d(canvas, this, 20.0f, 43.0f, 50.0f, 47.0f);
                                                        d(canvas, this, 70.0f, 47.0f, 100.0f, 43.0f);
                                                        d(canvas, this, 60.0f, 59.0f, 60.0f, 88.0f);
                                                        Iterator it7 = j3.i0(Float.valueOf(49.0f), fValueOf4, Float.valueOf(71.0f)).iterator();
                                                        while (it7.hasNext()) {
                                                            float fFloatValue7 = ((Number) it7.next()).floatValue();
                                                            Path path6 = new Path();
                                                            path6.moveTo(fFloatValue7, 28.0f);
                                                            path6.cubicTo(fFloatValue7 - 5.0f, 23.0f, fFloatValue7 + 5.0f, 18.0f, fFloatValue7, 13.0f);
                                                            canvas.drawPath(path6, paint);
                                                        }
                                                    } else if (k8.e(str3, "battery")) {
                                                        canvas.drawRoundRect(27.0f, 29.0f, 91.0f, 69.0f, 7.0f, 7.0f, paint);
                                                        cjVar = this;
                                                        d(canvas, cjVar, 40.0f, 22.0f, 49.0f, 22.0f);
                                                        d(canvas, cjVar, 70.0f, 22.0f, 79.0f, 22.0f);
                                                        d(canvas, cjVar, 39.0f, 49.0f, 51.0f, 49.0f);
                                                        d(canvas, cjVar, 45.0f, 43.0f, 45.0f, 55.0f);
                                                        d(canvas, cjVar, 68.0f, 49.0f, 80.0f, 49.0f);
                                                    } else if (k8.e(str3, "fuel")) {
                                                        canvas.drawRoundRect(34.0f, 21.0f, 69.0f, 76.0f, 5.0f, 5.0f, paint);
                                                        canvas.drawRoundRect(41.0f, 28.0f, 62.0f, 44.0f, 3.0f, 3.0f, paint);
                                                        d(canvas, this, 28.0f, 79.0f, 76.0f, 79.0f);
                                                        cjVar5 = this;
                                                        e(canvas, cjVar5, 70.0f, 49.0f, 80.0f, 49.0f, 80.0f, 68.0f, 91.0f, 68.0f, 91.0f, 35.0f, 81.0f, 25.0f);
                                                    } else {
                                                        cj cjVar6 = this;
                                                        if (k8.e(str3, "ambient")) {
                                                            e(canvas, cjVar6, 26.0f, 59.0f, 35.0f, 35.0f, 85.0f, 35.0f, 94.0f, 59.0f);
                                                            canvas.drawRoundRect(28.0f, 60.0f, 92.0f, 74.0f, 6.0f, 6.0f, paint);
                                                            int iD2 = pi.D(39, 81, 14);
                                                            if (39 <= iD2) {
                                                                int i18 = 39;
                                                                while (true) {
                                                                    float f9 = i18;
                                                                    d(canvas, cjVar6, f9, 22.0f, f9, 13.0f);
                                                                    if (i18 == iD2) {
                                                                        break;
                                                                    }
                                                                    i18 += 14;
                                                                    cjVar6 = this;
                                                                }
                                                            }
                                                        } else {
                                                            float f10 = 60.0f;
                                                            canvas.drawCircle(60.0f, 47.0f, 8.0f, paint);
                                                            int i19 = 0;
                                                            while (i19 < 4) {
                                                                canvas.save();
                                                                canvas.rotate(i19 * 90.0f, f10, 47.0f);
                                                                Path path7 = new Path();
                                                                path7.moveTo(66.0f, 40.0f);
                                                                path7.cubicTo(105.0f, 14.0f, 86.0f, 3.0f, 64.0f, 29.0f);
                                                                canvas.drawPath(path7, paint);
                                                                canvas.restore();
                                                                i19++;
                                                                f10 = 60.0f;
                                                            }
                                                        }
                                                    }
                                                    shader = null;
                                                    cjVar = this;
                                                }
                                                shader = null;
                                                cjVar = this;
                                            }
                                            cjVar = cjVar5;
                                        }
                                        cjVar = cjVar3;
                                    }
                                }
                            } else if (z) {
                                Path path8 = new Path();
                                path8.moveTo(22.0f, 73.0f);
                                path8.lineTo(31.0f, 25.0f);
                                path8.quadTo(60.0f, 10.0f, 89.0f, 25.0f);
                                path8.lineTo(98.0f, 73.0f);
                                path8.quadTo(60.0f, 65.0f, 22.0f, 73.0f);
                                path8.close();
                                canvas4.drawPath(path8, paint);
                                it2 = j3.i0(fValueOf2, fValueOf4, fValueOf).iterator();
                                while (it2.hasNext()) {
                                    float fFloatValue8 = ((Number) it2.next()).floatValue();
                                    Path path9 = new Path();
                                    path9.moveTo(fFloatValue8, 61.0f);
                                    path9.cubicTo(fFloatValue8 - 7.0f, 53.0f, fFloatValue8 + 7.0f, 47.0f, fFloatValue8, 36.0f);
                                    canvas4.drawPath(path9, paint);
                                }
                            } else {
                                Path path10 = new Path();
                                path10.moveTo(26.0f, 58.0f);
                                path10.lineTo(34.0f, 18.0f);
                                path10.lineTo(86.0f, 18.0f);
                                path10.lineTo(94.0f, 58.0f);
                                path10.close();
                                canvas4.drawPath(path10, paint);
                                canvas4.drawLine(36.0f, 52.0f, 80.0f, 44.0f, this.c);
                                d(canvas, this, 80.0f, 44.0f, 82.0f, 52.0f);
                                it = j3.i0(fValueOf2, fValueOf4, fValueOf).iterator();
                                while (it.hasNext()) {
                                    float fFloatValue9 = ((Number) it.next()).floatValue();
                                    Path path11 = new Path();
                                    path11.moveTo(fFloatValue9, 86.0f);
                                    path11.cubicTo(fFloatValue9 - 5.0f, 81.0f, fFloatValue9 + 5.0f, 76.0f, fFloatValue9, 71.0f);
                                    canvas.drawPath(path11, paint);
                                }
                                shader = null;
                                cjVar = this;
                                f = 18.0f;
                            }
                            paint.setShader(shader);
                            if (ci.t(str3, str)) {
                                paint.setStyle(Paint.Style.FILL);
                                f2 = 60.0f - (((cjVar.m * f) - 5.0f) / 2.0f);
                                num = cjVar.e;
                                if (num != null) {
                                    iIntValue = num.intValue();
                                } else {
                                    iIntValue = 0;
                                }
                                i3 = cjVar.m;
                                if (1 <= i3) {
                                    i4 = 1;
                                    while (true) {
                                        if (i4 <= iIntValue) {
                                            i5 = 255;
                                        } else {
                                            i5 = 45;
                                        }
                                        paint.setAlpha(i5);
                                        float f11 = (i4 - 1) * 18;
                                        float f12 = f2 + f11;
                                        float f13 = 13.0f + f2 + f11;
                                        canvas3 = canvas;
                                        canvas3.drawRoundRect(f12, 92.0f, f13, 96.0f, 2.0f, 2.0f, paint);
                                        if (i4 != i3) {
                                            break;
                                        } else {
                                            i4++;
                                        }
                                    }
                                } else {
                                    canvas3 = canvas;
                                }
                            } else {
                                canvas3 = canvas;
                            }
                            canvas3.restore();
                        }
                    }
                    cjVar = this;
                    shader = null;
                    f = 18.0f;
                    paint.setShader(shader);
                    if (ci.t(str3, str)) {
                        paint.setStyle(Paint.Style.FILL);
                        f2 = 60.0f - (((cjVar.m * f) - 5.0f) / 2.0f);
                        num = cjVar.e;
                        if (num != null) {
                            iIntValue = num.intValue();
                        } else {
                            iIntValue = 0;
                        }
                        i3 = cjVar.m;
                        if (1 <= i3) {
                            i4 = 1;
                            while (true) {
                                if (i4 <= iIntValue) {
                                    i5 = 255;
                                } else {
                                    i5 = 45;
                                }
                                paint.setAlpha(i5);
                                float f14 = (i4 - 1) * 18;
                                float f15 = f2 + f14;
                                float f16 = 13.0f + f2 + f14;
                                canvas3 = canvas;
                                canvas3.drawRoundRect(f15, 92.0f, f16, 96.0f, 2.0f, 2.0f, paint);
                                if (i4 != i3) {
                                    break;
                                    break;
                                }
                                i4++;
                            }
                        } else {
                            canvas3 = canvas;
                        }
                    } else {
                        canvas3 = canvas;
                    }
                    canvas3.restore();
                }
                shader = null;
                paint.setShader(shader);
                if (ci.t(str3, str)) {
                    paint.setStyle(Paint.Style.FILL);
                    f2 = 60.0f - (((cjVar.m * f) - 5.0f) / 2.0f);
                    num = cjVar.e;
                    if (num != null) {
                        iIntValue = num.intValue();
                    } else {
                        iIntValue = 0;
                    }
                    i3 = cjVar.m;
                    if (1 <= i3) {
                        i4 = 1;
                        while (true) {
                            if (i4 <= iIntValue) {
                                i5 = 255;
                            } else {
                                i5 = 45;
                            }
                            paint.setAlpha(i5);
                            float f17 = (i4 - 1) * 18;
                            float f18 = f2 + f17;
                            float f19 = 13.0f + f2 + f17;
                            canvas3 = canvas;
                            canvas3.drawRoundRect(f18, 92.0f, f19, 96.0f, 2.0f, 2.0f, paint);
                            if (i4 != i3) {
                                break;
                                break;
                            }
                            i4++;
                        }
                    } else {
                        canvas3 = canvas;
                    }
                } else {
                    canvas3 = canvas;
                }
                canvas3.restore();
            } else {
                canvas2 = canvas;
                str3 = str3;
            }
            if (zBooleanValue) {
                if (str3.startsWith("seat_")) {
                    canvas2.save();
                    if (j3.i0("4", "64").contains(th.E(str3, '_'))) {
                        canvas2.translate(120.0f, 0.0f);
                        canvas2.scale(-1.0f, 1.0f);
                    }
                    str = "seat_";
                    canvas.drawRoundRect(33.0f, 12.0f, 49.0f, 28.0f, 6.0f, 6.0f, paint);
                    e(canvas, this, 36.0f, 35.0f, 42.0f, 64.0f, 76.0f, 64.0f, 82.0f, 72.0f, 39.0f, 72.0f, 29.0f, 39.0f);
                    e(canvas, this, 43.0f, 73.0f, 39.0f, 82.0f, 81.0f, 82.0f);
                    if (zT) {
                        it4 = j3.i0(fValueOf4, Float.valueOf(72.0f), fValueOf3).iterator();
                        while (it4.hasNext()) {
                            float fFloatValue10 = ((Number) it4.next()).floatValue();
                            Path path12 = new Path();
                            path12.moveTo(fFloatValue10, 54.0f);
                            path12.cubicTo(fFloatValue10 - 9.0f, 43.0f, fFloatValue10 + 9.0f, 38.0f, fFloatValue10, 27.0f);
                            canvas.drawPath(path12, paint);
                        }
                    } else {
                        canvas.drawCircle(75.0f, 38.0f, 4.0f, paint);
                        while (i6 < 4) {
                            canvas.save();
                            canvas.rotate(i6 * 90.0f, 75.0f, 38.0f);
                            Path path13 = new Path();
                            path13.moveTo(78.0f, 35.0f);
                            path13.cubicTo(99.0f, 20.0f, 89.0f, 12.0f, 77.0f, 29.0f);
                            canvas.drawPath(path13, paint);
                            canvas.restore();
                        }
                    }
                    canvas.restore();
                } else {
                    canvas4 = canvas2;
                    str = "seat_";
                    if (str3.equals("nozzle_heat")) {
                        canvas4.drawRoundRect(48.0f, 52.0f, 72.0f, 62.0f, 4.0f, 4.0f, paint);
                        canvas.drawLine(55.0f, 44.0f, 45.0f, 24.0f, this.c);
                        canvas.drawLine(60.0f, 42.0f, 60.0f, 20.0f, this.c);
                        canvas.drawLine(65.0f, 44.0f, 75.0f, 24.0f, this.c);
                        it3 = j3.i0(fValueOf2, fValueOf4, fValueOf).iterator();
                        while (it3.hasNext()) {
                            float fFloatValue11 = ((Number) it3.next()).floatValue();
                            Path path14 = new Path();
                            path14.moveTo(fFloatValue11, 84.0f);
                            path14.cubicTo(fFloatValue11 - 5.0f, 79.0f, fFloatValue11 + 5.0f, 74.0f, fFloatValue11, 69.0f);
                            canvas.drawPath(path14, paint);
                        }
                    } else {
                        if (k8.e(str3, "wiper_heat")) {
                            f = 18.0f;
                            if (k8.e(str3, "curtain")) {
                                canvas4.drawRoundRect(24.0f, 18.0f, 96.0f, 76.0f, 9.0f, 9.0f, paint);
                                canvas.drawRoundRect(37.0f, 30.0f, 83.0f, 61.0f, 4.0f, 4.0f, paint);
                                cjVar = this;
                                d(canvas, cjVar, 37.0f, 38.0f, 83.0f, 38.0f);
                                d(canvas, cjVar, 37.0f, 46.0f, 83.0f, 46.0f);
                            } else if (k8.e(str3, "roof")) {
                                canvas.drawRoundRect(24.0f, 18.0f, 96.0f, 76.0f, 9.0f, 9.0f, paint);
                                if (k8.e(str3, "roof")) {
                                    canvas.drawRoundRect(37.0f, 30.0f, 83.0f, 61.0f, 4.0f, 4.0f, paint);
                                    e(canvas, this, 50.0f, 48.0f, 60.0f, 38.0f, 70.0f, 48.0f);
                                    cjVar = this;
                                    d(canvas, cjVar, 60.0f, 38.0f, 60.0f, 58.0f);
                                } else {
                                    cjVar = this;
                                    e(canvas, cjVar, 35.0f, 59.0f, 83.0f, 41.0f, 83.0f, 61.0f);
                                    d(canvas, cjVar, 36.0f, 65.0f, 83.0f, 65.0f);
                                    e(canvas, cjVar, 49.0f, 37.0f, 57.0f, 29.0f, 65.0f, 37.0f);
                                    d(canvas, cjVar, 57.0f, 29.0f, 57.0f, 44.0f);
                                }
                            } else {
                                canvas.drawRoundRect(24.0f, 18.0f, 96.0f, 76.0f, 9.0f, 9.0f, paint);
                                if (k8.e(str3, "roof")) {
                                    canvas.drawRoundRect(37.0f, 30.0f, 83.0f, 61.0f, 4.0f, 4.0f, paint);
                                    e(canvas, this, 50.0f, 48.0f, 60.0f, 38.0f, 70.0f, 48.0f);
                                    cjVar = this;
                                    d(canvas, cjVar, 60.0f, 38.0f, 60.0f, 58.0f);
                                } else {
                                    cjVar = this;
                                    e(canvas, cjVar, 35.0f, 59.0f, 83.0f, 41.0f, 83.0f, 61.0f);
                                    d(canvas, cjVar, 36.0f, 65.0f, 83.0f, 65.0f);
                                    e(canvas, cjVar, 49.0f, 37.0f, 57.0f, 29.0f, 65.0f, 37.0f);
                                    d(canvas, cjVar, 57.0f, 29.0f, 57.0f, 44.0f);
                                }
                            }
                        } else if (z) {
                            Path path15 = new Path();
                            path15.moveTo(22.0f, 73.0f);
                            path15.lineTo(31.0f, 25.0f);
                            path15.quadTo(60.0f, 10.0f, 89.0f, 25.0f);
                            path15.lineTo(98.0f, 73.0f);
                            path15.quadTo(60.0f, 65.0f, 22.0f, 73.0f);
                            path15.close();
                            canvas4.drawPath(path15, paint);
                            it2 = j3.i0(fValueOf2, fValueOf4, fValueOf).iterator();
                            while (it2.hasNext()) {
                                float fFloatValue12 = ((Number) it2.next()).floatValue();
                                Path path16 = new Path();
                                path16.moveTo(fFloatValue12, 61.0f);
                                path16.cubicTo(fFloatValue12 - 7.0f, 53.0f, fFloatValue12 + 7.0f, 47.0f, fFloatValue12, 36.0f);
                                canvas4.drawPath(path16, paint);
                            }
                        } else {
                            Path path17 = new Path();
                            path17.moveTo(26.0f, 58.0f);
                            path17.lineTo(34.0f, 18.0f);
                            path17.lineTo(86.0f, 18.0f);
                            path17.lineTo(94.0f, 58.0f);
                            path17.close();
                            canvas4.drawPath(path17, paint);
                            canvas4.drawLine(36.0f, 52.0f, 80.0f, 44.0f, this.c);
                            d(canvas, this, 80.0f, 44.0f, 82.0f, 52.0f);
                            it = j3.i0(fValueOf2, fValueOf4, fValueOf).iterator();
                            while (it.hasNext()) {
                                float fFloatValue13 = ((Number) it.next()).floatValue();
                                Path path18 = new Path();
                                path18.moveTo(fFloatValue13, 86.0f);
                                path18.cubicTo(fFloatValue13 - 5.0f, 81.0f, fFloatValue13 + 5.0f, 76.0f, fFloatValue13, 71.0f);
                                canvas.drawPath(path18, paint);
                            }
                            shader = null;
                            cjVar = this;
                            f = 18.0f;
                        }
                        paint.setShader(shader);
                        if (ci.t(str3, str)) {
                            paint.setStyle(Paint.Style.FILL);
                            f2 = 60.0f - (((cjVar.m * f) - 5.0f) / 2.0f);
                            num = cjVar.e;
                            if (num != null) {
                                iIntValue = num.intValue();
                            } else {
                                iIntValue = 0;
                            }
                            i3 = cjVar.m;
                            if (1 <= i3) {
                                i4 = 1;
                                while (true) {
                                    if (i4 <= iIntValue) {
                                        i5 = 255;
                                    } else {
                                        i5 = 45;
                                    }
                                    paint.setAlpha(i5);
                                    float f110 = (i4 - 1) * 18;
                                    float f111 = f2 + f110;
                                    float f112 = 13.0f + f2 + f110;
                                    canvas3 = canvas;
                                    canvas3.drawRoundRect(f111, 92.0f, f112, 96.0f, 2.0f, 2.0f, paint);
                                    if (i4 != i3) {
                                        break;
                                        break;
                                    }
                                    i4++;
                                }
                            } else {
                                canvas3 = canvas;
                            }
                        } else {
                            canvas3 = canvas;
                        }
                        canvas3.restore();
                    }
                }
                cjVar = this;
                shader = null;
                f = 18.0f;
                paint.setShader(shader);
                if (ci.t(str3, str)) {
                    paint.setStyle(Paint.Style.FILL);
                    f2 = 60.0f - (((cjVar.m * f) - 5.0f) / 2.0f);
                    num = cjVar.e;
                    if (num != null) {
                        iIntValue = num.intValue();
                    } else {
                        iIntValue = 0;
                    }
                    i3 = cjVar.m;
                    if (1 <= i3) {
                        i4 = 1;
                        while (true) {
                            if (i4 <= iIntValue) {
                                i5 = 255;
                            } else {
                                i5 = 45;
                            }
                            paint.setAlpha(i5);
                            float f113 = (i4 - 1) * 18;
                            float f114 = f2 + f113;
                            float f115 = 13.0f + f2 + f113;
                            canvas3 = canvas;
                            canvas3.drawRoundRect(f114, 92.0f, f115, 96.0f, 2.0f, 2.0f, paint);
                            if (i4 != i3) {
                                break;
                                break;
                            }
                            i4++;
                        }
                    } else {
                        canvas3 = canvas;
                    }
                } else {
                    canvas3 = canvas;
                }
                canvas3.restore();
            } else {
                str = "seat_";
                cjVar = this;
                f = 18.0f;
            }
            shader = null;
            paint.setShader(shader);
            if (ci.t(str3, str)) {
                paint.setStyle(Paint.Style.FILL);
                f2 = 60.0f - (((cjVar.m * f) - 5.0f) / 2.0f);
                num = cjVar.e;
                if (num != null) {
                    iIntValue = num.intValue();
                } else {
                    iIntValue = 0;
                }
                i3 = cjVar.m;
                if (1 <= i3) {
                    i4 = 1;
                    while (true) {
                        if (i4 <= iIntValue) {
                            i5 = 255;
                        } else {
                            i5 = 45;
                        }
                        paint.setAlpha(i5);
                        float f116 = (i4 - 1) * 18;
                        float f117 = f2 + f116;
                        float f118 = 13.0f + f2 + f116;
                        canvas3 = canvas;
                        canvas3.drawRoundRect(f117, 92.0f, f118, 96.0f, 2.0f, 2.0f, paint);
                        if (i4 != i3) {
                            break;
                            break;
                        }
                        i4++;
                    }
                } else {
                    canvas3 = canvas;
                }
            } else {
                canvas3 = canvas;
            }
            canvas3.restore();
        }
        canvas3.restoreToCount(i);
    }

    public final void setActiveSurface(boolean z) {
        this.h = z;
        invalidate();
    }

    public final void setBlowMode(Integer num) {
        this.j = num;
        invalidate();
    }

    public final void setCruiseMode(Integer num) {
        this.d = num;
        invalidate();
    }

    public final void setEnabledState(Boolean bool) {
        this.f = bool;
        invalidate();
    }

    public final void setFlowInk(Integer num) {
        this.k = num;
        invalidate();
    }

    public final void setHalo(boolean z) {
        this.l = z;
        invalidate();
    }

    public final void setLevel(Integer num) {
        this.e = num;
        invalidate();
    }

    public final void setMaxLevel(int i) {
        this.m = pi.s(i, 1, 3);
        invalidate();
    }

    public final void setNightMode(boolean z) {
        this.g = z;
        invalidate();
    }
}
