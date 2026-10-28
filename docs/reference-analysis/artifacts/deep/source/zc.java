package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.view.View;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class zc extends View {
    public final String a;
    public final int b;
    public final Paint c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zc(Context context, String str) {
        super(context);
        str.getClass();
        this.a = str;
        this.b = -1;
        this.c = new Paint(1);
        setImportantForAccessibility(2);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:32:0x0077  */
    /* JADX WARN: Code duplicated, block: B:42:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:64:0x0105  */
    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        String str;
        String str2;
        Object obj;
        Object obj2;
        Object obj3;
        boolean z;
        Canvas canvas2;
        ba baVar;
        Float fValueOf = Float.valueOf(57.0f);
        canvas.getClass();
        canvas.save();
        canvas.scale(getWidth() / 100.0f, getHeight() / 100.0f);
        switch (this.a) {
            case "nav":
                str2 = "navigation";
                str = str2;
                break;
            case "sun":
                str2 = "brightness";
                str = str2;
                break;
            case "apps":
                str = "apps";
                break;
            case "home":
                str = "home";
                break;
            case "play":
                str = "play";
                break;
            case "music":
                str = "music";
                break;
            case "settings":
                str = "settings";
                break;
            default:
                str = null;
                break;
        }
        if (str != null) {
            l0 l0Var = ba.h;
            ba baVar2 = ba.k;
            if (baVar2 != null) {
                obj3 = "settings";
                obj = "play";
                obj2 = "music";
                z = true;
                if (baVar2.a(canvas, str, this.b, 8.0f, 8.0f, 84.0f)) {
                    canvas.restore();
                    return;
                }
            } else {
                obj = "play";
                obj2 = "music";
                obj3 = "settings";
                z = true;
            }
        } else {
            obj = "play";
            obj2 = "music";
            obj3 = "settings";
            z = true;
        }
        boolean zE = k8.e(this.a, "album");
        if (str == null) {
            l0 l0Var2 = ba.h;
            if (!ba.s.contains(this.a) || (baVar = ba.k) == null) {
                fValueOf = fValueOf;
                canvas2 = canvas;
            } else {
                canvas2 = canvas;
                if (baVar.b(canvas2, this.a, this.b, zE ? 0.0f : 8.0f, zE ? 0.0f : 8.0f, zE ? 100.0f : 84.0f) == z) {
                    canvas2.restore();
                    return;
                }
            }
        } else {
            fValueOf = fValueOf;
            canvas2 = canvas;
        }
        this.c.setColor(this.b);
        this.c.setStyle(Paint.Style.STROKE);
        this.c.setStrokeWidth(4.0f);
        this.c.setStrokeCap(Paint.Cap.ROUND);
        this.c.setStrokeJoin(Paint.Join.ROUND);
        String str3 = this.a;
        float f = 23.0f;
        switch (str3.hashCode()) {
            case -342500282:
                if (str3.equals("shortcut")) {
                    canvas.drawRoundRect(19.0f, 19.0f, 81.0f, 81.0f, 14.0f, 14.0f, this.c);
                    canvas.drawLine(37.0f, 63.0f, 63.0f, 37.0f, this.c);
                    canvas.drawLine(43.0f, 37.0f, 63.0f, 37.0f, this.c);
                    canvas.drawLine(63.0f, 37.0f, 63.0f, 57.0f, this.c);
                }
                break;
            case 107868:
                if (str3.equals("map")) {
                    this.c.setStrokeWidth(1.1f);
                    this.c.setColor(894330750);
                    int i = -5;
                    while (true) {
                        Paint paint = this.c;
                        if (i >= 11) {
                            paint.setStrokeWidth(8.0f);
                            this.c.setColor(625963406);
                            Path path = new Path();
                            path.moveTo(95.0f, -10.0f);
                            path.cubicTo(40.0f, 25.0f, 115.0f, 50.0f, 45.0f, 110.0f);
                            canvas2.drawPath(path, this.c);
                        } else {
                            float f2 = i;
                            float f3 = f2 * 17.0f;
                            canvas.drawLine(f3, 0.0f, f3 + 40.0f, 100.0f, paint);
                            float f4 = f2 * 19.0f;
                            canvas.drawLine(0.0f, f4, 100.0f, f4 - 34.0f, this.c);
                            canvas2 = canvas;
                            i++;
                        }
                    }
                }
                break;
            case 108835:
                if (str3.equals("nav")) {
                    Path path2 = new Path();
                    path2.moveTo(21.0f, 52.0f);
                    path2.lineTo(82.0f, 17.0f);
                    path2.lineTo(57.0f, 85.0f);
                    path2.lineTo(47.0f, 59.0f);
                    path2.close();
                    canvas2.drawPath(path2, this.c);
                }
                break;
            case 114252:
                if (str3.equals("sun")) {
                    canvas2.drawCircle(50.0f, 50.0f, 17.0f, this.c);
                    for (int i2 = 0; i2 < 8; i2++) {
                        canvas2.save();
                        canvas2.rotate(i2 * 45.0f, 50.0f, 50.0f);
                        canvas.drawLine(50.0f, 13.0f, 50.0f, 23.0f, this.c);
                        canvas2 = canvas;
                        canvas2.restore();
                    }
                }
                break;
            case 3000946:
                if (str3.equals("apps")) {
                    Float f5 = fValueOf;
                    Iterator it = j3.i0(Float.valueOf(20.0f), f5).iterator();
                    while (it.hasNext()) {
                        float fFloatValue = ((Number) it.next()).floatValue();
                        Iterator it2 = j3.i0(Float.valueOf(20.0f), f5).iterator();
                        while (it2.hasNext()) {
                            float fFloatValue2 = ((Number) it2.next()).floatValue();
                            Canvas canvas3 = canvas2;
                            float f6 = fFloatValue;
                            canvas3.drawRoundRect(f6, fFloatValue2, fFloatValue + f, fFloatValue2 + f, 4.0f, 4.0f, this.c);
                            canvas2 = canvas3;
                            fFloatValue = f6;
                            f = f;
                        }
                    }
                }
                break;
            case 3208415:
                if (str3.equals("home")) {
                    this.c.setStyle(Paint.Style.FILL);
                    Path path3 = new Path();
                    path3.moveTo(15.0f, 45.0f);
                    path3.lineTo(50.0f, 15.0f);
                    path3.lineTo(85.0f, 45.0f);
                    path3.lineTo(77.0f, 45.0f);
                    path3.lineTo(77.0f, 84.0f);
                    path3.lineTo(58.0f, 84.0f);
                    path3.lineTo(58.0f, 60.0f);
                    path3.lineTo(42.0f, 60.0f);
                    path3.lineTo(42.0f, 84.0f);
                    path3.lineTo(23.0f, 84.0f);
                    path3.lineTo(23.0f, 45.0f);
                    path3.close();
                    canvas2.drawPath(path3, this.c);
                }
                break;
            case 3443508:
                if (str3.equals(obj)) {
                    canvas2.drawCircle(50.0f, 50.0f, 44.0f, this.c);
                    this.c.setStyle(Paint.Style.FILL);
                    Path path4 = new Path();
                    path4.moveTo(41.0f, 32.0f);
                    path4.lineTo(68.0f, 50.0f);
                    path4.lineTo(41.0f, 68.0f);
                    path4.close();
                    canvas2.drawPath(path4, this.c);
                }
                break;
            case 92896879:
                if (str3.equals("album")) {
                    this.c.setStyle(Paint.Style.FILL);
                    Paint paint2 = this.c;
                    int iRgb = Color.rgb(7, 20, 41);
                    int iRgb2 = Color.rgb(44, 94, 128);
                    Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                    paint2.setShader(new LinearGradient(0.0f, 0.0f, 100.0f, 100.0f, iRgb, iRgb2, tileMode));
                    canvas.drawRect(0.0f, 0.0f, 100.0f, 100.0f, this.c);
                    this.c.setShader(new RadialGradient(25.0f, 80.0f, 68.0f, new int[]{-14267286, -15719628, -6760721, 1785437}, new float[]{0.0f, 0.79f, 0.83f, 1.0f}, tileMode));
                    canvas.drawCircle(25.0f, 80.0f, 68.0f, this.c);
                    this.c.setShader(null);
                }
                break;
            case 104263205:
                if (str3.equals(obj2)) {
                    canvas.drawLine(39.0f, 74.0f, 39.0f, 28.0f, this.c);
                    canvas.drawLine(39.0f, 28.0f, 77.0f, 20.0f, this.c);
                    canvas.drawLine(77.0f, 20.0f, 77.0f, 66.0f, this.c);
                    this.c.setStyle(Paint.Style.FILL);
                    canvas.drawOval(17.0f, 65.0f, 40.0f, 82.0f, this.c);
                    canvas.drawOval(55.0f, 57.0f, 78.0f, 74.0f, this.c);
                }
                break;
            case 1434631203:
                if (str3.equals(obj3)) {
                    canvas2.drawCircle(50.0f, 50.0f, 25.0f, this.c);
                    canvas2.drawCircle(50.0f, 50.0f, 9.0f, this.c);
                    int i3 = 0;
                    while (i3 < 8) {
                        canvas2.save();
                        canvas2.rotate(i3 * 45.0f, 50.0f, 50.0f);
                        canvas.drawLine(50.0f, 16.0f, 50.0f, 25.0f, this.c);
                        canvas.restore();
                        i3++;
                        canvas2 = canvas;
                    }
                }
                break;
        }
        this.c.setShader(null);
        canvas.restore();
    }
}
