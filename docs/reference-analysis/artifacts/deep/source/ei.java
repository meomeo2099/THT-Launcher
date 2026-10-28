package defpackage;

import android.graphics.Path;
import android.graphics.RectF;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class ei {
    public static final me a = new me("[MmLlHhVvCcSsQqAaZz]|-?(?:\\d+\\.?\\d*|\\.\\d+)(?:[eE][-+]?\\d+)?");

    public static Path a(String str) {
        float fB;
        float fB2;
        float fB3;
        float fB4;
        float f;
        float f2;
        int i;
        me meVar = a;
        meVar.getClass();
        if (str.length() < 0) {
            throw new IndexOutOfBoundsException("Start index out of bounds: 0, input length: " + str.length());
        }
        List listR0 = wf.R0(new a4(new y0(new y1(meVar, str, 11)), new fg(5), 2));
        Path path = new Path();
        le leVar = new le();
        char c = 'M';
        int i2 = 0;
        char cCharAt = 'M';
        int i3 = 0;
        float fB5 = 0.0f;
        float fB6 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        float fB7 = 0.0f;
        float fB8 = 0.0f;
        while (leVar.a < listR0.size()) {
            if (Character.isLetter(((String) listR0.get(leVar.a)).charAt(i2))) {
                cCharAt = ((String) listR0.get(leVar.a)).charAt(i2);
                leVar.a++;
            }
            char c2 = cCharAt;
            boolean zIsLowerCase = Character.isLowerCase(c2);
            float f5 = zIsLowerCase ? fB5 : 0.0f;
            float f6 = zIsLowerCase ? fB6 : 0.0f;
            char upperCase = Character.toUpperCase(c2);
            if (upperCase != 'A') {
                if (upperCase != 'C') {
                    if (upperCase == 'H') {
                        fB5 = b(listR0, leVar) + f5;
                        path.lineTo(fB5, fB6);
                    } else if (upperCase == 'Q') {
                        float fB9 = b(listR0, leVar) + f5;
                        float fB10 = b(listR0, leVar) + f6;
                        float fB11 = b(listR0, leVar) + f5;
                        float fB12 = b(listR0, leVar) + f6;
                        path.quadTo(fB9, fB10, fB11, fB12);
                        fB5 = fB11;
                        fB6 = fB12;
                    } else if (upperCase == 'S') {
                        if (i3 != 0) {
                            fB5 = (fB5 * 2.0f) - f3;
                        }
                        if (i3 != 0) {
                            fB6 = (2.0f * fB6) - f4;
                        }
                        float fB13 = b(listR0, leVar) + f5;
                        fB2 = b(listR0, leVar) + f6;
                        fB3 = b(listR0, leVar) + f5;
                        fB4 = b(listR0, leVar) + f6;
                        float f7 = fB6;
                        fB = fB13;
                        path.cubicTo(fB5, f7, fB, fB2, fB3, fB4);
                    } else if (upperCase != 'V') {
                        if (upperCase == 'Z') {
                            path.close();
                            if (leVar.a < listR0.size() && !Character.isLetter(((String) listR0.get(leVar.a)).charAt(i2))) {
                                leVar.a++;
                            }
                            i3 = i2;
                            fB5 = fB7;
                            fB6 = fB8;
                        } else if (upperCase == 'L') {
                            fB5 = b(listR0, leVar) + f5;
                            fB6 = b(listR0, leVar) + f6;
                            path.lineTo(fB5, fB6);
                        } else if (upperCase != c) {
                            leVar.a++;
                        } else {
                            fB7 = b(listR0, leVar) + f5;
                            fB8 = b(listR0, leVar) + f6;
                            path.moveTo(fB7, fB8);
                            cCharAt = zIsLowerCase ? 'l' : 'L';
                            i3 = i2;
                            fB5 = fB7;
                            fB6 = fB8;
                        }
                        cCharAt = c2;
                    } else {
                        fB6 = b(listR0, leVar) + f6;
                        path.lineTo(fB5, fB6);
                    }
                    i3 = i2;
                    cCharAt = c2;
                } else {
                    float fB14 = b(listR0, leVar) + f5;
                    float fB15 = b(listR0, leVar) + f6;
                    fB = b(listR0, leVar) + f5;
                    fB2 = b(listR0, leVar) + f6;
                    fB3 = b(listR0, leVar) + f5;
                    fB4 = b(listR0, leVar) + f6;
                    path.cubicTo(fB14, fB15, fB, fB2, fB3, fB4);
                }
                fB5 = fB3;
                cCharAt = c2;
                f3 = fB;
                fB6 = fB4;
                f4 = fB2;
                i3 = 1;
            } else {
                float fB16 = b(listR0, leVar);
                float fB17 = b(listR0, leVar);
                b(listR0, leVar);
                int i4 = (b(listR0, leVar) == 0.0f ? 1 : i2) ^ 1;
                int i5 = b(listR0, leVar) == 0.0f ? 1 : i2;
                int i6 = i5 ^ 1;
                float fB18 = b(listR0, leVar) + f5;
                List list = listR0;
                float fB19 = b(listR0, leVar) + f6;
                if (fB5 == fB18 && fB6 == fB19) {
                    f2 = fB19;
                    f = fB18;
                    fB7 = fB7;
                    fB8 = fB8;
                    c2 = c2;
                    i = 0;
                } else {
                    float f8 = fB5;
                    double dAbs = Math.abs(fB16);
                    double dAbs2 = Math.abs(fB17);
                    if (dAbs == 0.0d || dAbs2 == 0.0d) {
                        path.lineTo(fB18, fB19);
                        f2 = fB19;
                        f = fB18;
                        fB7 = fB7;
                        fB8 = fB8;
                        c2 = c2;
                        i = 0;
                    } else {
                        double d = ((double) (f8 - fB18)) / 2.0d;
                        float f9 = fB6;
                        double d2 = ((double) (fB6 - fB19)) / 2.0d;
                        double d3 = ((d2 * d2) / (dAbs2 * dAbs2)) + ((d * d) / (dAbs * dAbs));
                        if (d3 > 1.0d) {
                            double dSqrt = Math.sqrt(d3);
                            dAbs *= dSqrt;
                            dAbs2 *= dSqrt;
                        }
                        double d4 = dAbs2;
                        double d5 = dAbs * dAbs;
                        double d6 = d5 * d4 * d4;
                        double d7 = d5 * d2 * d2;
                        double d8 = d4 * d4 * d * d;
                        double d9 = (d6 - d7) - d8;
                        double d10 = d7 + d8;
                        f = fB18;
                        double dSqrt2 = d10 == 0.0d ? 0.0d : Math.sqrt(Math.max(0.0d, d9 / d10));
                        if (i4 == i6) {
                            dSqrt2 = -dSqrt2;
                        }
                        double d11 = ((dAbs * d2) / d4) * dSqrt2;
                        double d12 = (((-d4) * d) / dAbs) * dSqrt2;
                        double d13 = (((double) (f8 + f)) / 2.0d) + d11;
                        f2 = fB19;
                        double d14 = (((double) (f9 + f2)) / 2.0d) + d12;
                        double d15 = (d - d11) / dAbs;
                        double d16 = (d2 - d12) / d4;
                        double dAtan2 = Math.atan2((1.0d * d16) - (0.0d * d15), (0.0d * d16) + (1.0d * d15));
                        double d17 = ((-d) - d11) / dAbs;
                        double d18 = ((-d2) - d12) / d4;
                        double dAtan3 = Math.atan2((d15 * d18) - (d16 * d17), (d16 * d18) + (d15 * d17));
                        if (i5 != 0 && dAtan3 > 0.0d) {
                            dAtan3 -= 6.283185307179586d;
                        }
                        if (i5 == 0 && dAtan3 < 0.0d) {
                            dAtan3 += 6.283185307179586d;
                        }
                        i = 0;
                        path.arcTo(new RectF((float) (d13 - dAbs), (float) (d14 - d4), (float) (d13 + dAbs), (float) (d14 + d4)), (float) Math.toDegrees(dAtan2), (float) Math.toDegrees(dAtan3), false);
                    }
                }
                leVar = leVar;
                i3 = i;
                i2 = i3;
                fB6 = f2;
                listR0 = list;
                f3 = f3;
                f4 = f4;
                fB8 = fB8;
                cCharAt = c2;
                fB7 = fB7;
                fB5 = f;
                c = 'M';
            }
        }
        return path;
    }

    public static final float b(List list, le leVar) {
        int i = leVar.a;
        leVar.a = i + 1;
        return Float.parseFloat((String) list.get(i));
    }
}
