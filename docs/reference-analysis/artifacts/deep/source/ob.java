package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import java.util.Map;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class ob extends View {
    public final int a;
    public String b;
    public final Paint c;
    public float d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ob(MainActivity mainActivity, int i, String str, String str2, y5 y5Var) {
        super(mainActivity);
        str2.getClass();
        this.a = i;
        this.b = str;
        this.c = new Paint(1);
        setContentDescription(str2);
        setFocusable(true);
        setClickable(true);
        setOnClickListener(new m2(y5Var, 10));
        this.d = 0.62f;
    }

    public final String getSymbol() {
        return this.b;
    }

    public final float getSymbolScale() {
        return this.d;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:29:0x0100  */
    /* JADX WARN: Code duplicated, block: B:32:0x010b  */
    /* JADX WARN: Code duplicated, block: B:39:0x016c  */
    /* JADX WARN: Code duplicated, block: B:40:0x0187  */
    /* JADX WARN: Code duplicated, block: B:42:0x018f  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        Canvas canvas2;
        canvas.getClass();
        super.onDraw(canvas);
        float fMin = Math.min(getWidth(), getHeight()) * this.d;
        if (k8.e(this.b, "sound") || k8.e(this.b, "muted")) {
            Map map = i9.a;
            i9.a(canvas, k8.e(this.b, "muted") ? "speaker_off" : "speaker", this.c, this.a, (getWidth() - fMin) / 2.0f, (getHeight() - fMin) / 2.0f, fMin, 2.2f);
            return;
        }
        String str = (String) ba.u.get(this.b);
        if (str == null) {
            str = "play";
        }
        String str2 = str;
        ba baVar = ba.k;
        if (baVar != null) {
            canvas2 = canvas;
            if (baVar.a(canvas2, str2, this.a, (getWidth() - fMin) / 2.0f, (getHeight() - fMin) / 2.0f, fMin)) {
                return;
            }
        } else {
            canvas2 = canvas;
        }
        canvas2.save();
        float fMin2 = Math.min(getWidth(), getHeight()) * this.d;
        canvas2.translate((getWidth() - fMin2) / 2.0f, (getHeight() - fMin2) / 2.0f);
        float f = fMin2 / 48.0f;
        canvas2.scale(f, f);
        this.c.setColor(this.a);
        Paint paint = this.c;
        Paint.Style style = Paint.Style.FILL;
        paint.setStyle(style);
        this.c.setStrokeWidth(3.0f);
        this.c.setStrokeJoin(Paint.Join.ROUND);
        String str3 = this.b;
        switch (str3.hashCode()) {
            case 3377907:
                if (!str3.equals("next")) {
                    Path path = new Path();
                    path.moveTo(12.0f, 7.0f);
                    path.lineTo(39.0f, 24.0f);
                    path.lineTo(12.0f, 41.0f);
                    path.close();
                    canvas2.drawPath(path, this.c);
                } else {
                    if (k8.e(this.b, "prev")) {
                        canvas2.translate(48.0f, 0.0f);
                        canvas2.scale(-1.0f, 1.0f);
                    }
                    Path path2 = new Path();
                    path2.moveTo(8.0f, 7.0f);
                    path2.lineTo(32.0f, 24.0f);
                    path2.lineTo(8.0f, 41.0f);
                    path2.close();
                    canvas2.drawPath(path2, this.c);
                    canvas.drawRoundRect(35.0f, 7.0f, 40.0f, 41.0f, 1.0f, 1.0f, this.c);
                }
                break;
            case 3449395:
                if (!str3.equals("prev")) {
                    Path path3 = new Path();
                    path3.moveTo(12.0f, 7.0f);
                    path3.lineTo(39.0f, 24.0f);
                    path3.lineTo(12.0f, 41.0f);
                    path3.close();
                    canvas2.drawPath(path3, this.c);
                } else {
                    if (k8.e(this.b, "prev")) {
                        canvas2.translate(48.0f, 0.0f);
                        canvas2.scale(-1.0f, 1.0f);
                    }
                    Path path4 = new Path();
                    path4.moveTo(8.0f, 7.0f);
                    path4.lineTo(32.0f, 24.0f);
                    path4.lineTo(8.0f, 41.0f);
                    path4.close();
                    canvas2.drawPath(path4, this.c);
                    canvas.drawRoundRect(35.0f, 7.0f, 40.0f, 41.0f, 1.0f, 1.0f, this.c);
                }
                break;
            case 99151942:
                if (!str3.equals("heart")) {
                    Path path5 = new Path();
                    path5.moveTo(12.0f, 7.0f);
                    path5.lineTo(39.0f, 24.0f);
                    path5.lineTo(12.0f, 41.0f);
                    path5.close();
                    canvas2.drawPath(path5, this.c);
                } else {
                    Paint paint2 = this.c;
                    if (!k8.e(this.b, "liked")) {
                        style = Paint.Style.STROKE;
                    }
                    paint2.setStyle(style);
                    Path path6 = new Path();
                    path6.moveTo(24.0f, 41.0f);
                    path6.cubicTo(3.0f, 27.0f, 1.0f, 12.0f, 11.0f, 8.0f);
                    path6.cubicTo(17.0f, 5.0f, 22.0f, 9.0f, 24.0f, 13.0f);
                    path6.cubicTo(27.0f, 8.0f, 33.0f, 5.0f, 39.0f, 9.0f);
                    path6.cubicTo(49.0f, 18.0f, 39.0f, 30.0f, 24.0f, 41.0f);
                    path6.close();
                    canvas2.drawPath(path6, this.c);
                }
                break;
            case 102974381:
                if (!str3.equals("liked")) {
                    Path path7 = new Path();
                    path7.moveTo(12.0f, 7.0f);
                    path7.lineTo(39.0f, 24.0f);
                    path7.lineTo(12.0f, 41.0f);
                    path7.close();
                    canvas2.drawPath(path7, this.c);
                } else {
                    Paint paint3 = this.c;
                    if (!k8.e(this.b, "liked")) {
                        style = Paint.Style.STROKE;
                    }
                    paint3.setStyle(style);
                    Path path8 = new Path();
                    path8.moveTo(24.0f, 41.0f);
                    path8.cubicTo(3.0f, 27.0f, 1.0f, 12.0f, 11.0f, 8.0f);
                    path8.cubicTo(17.0f, 5.0f, 22.0f, 9.0f, 24.0f, 13.0f);
                    path8.cubicTo(27.0f, 8.0f, 33.0f, 5.0f, 39.0f, 9.0f);
                    path8.cubicTo(49.0f, 18.0f, 39.0f, 30.0f, 24.0f, 41.0f);
                    path8.close();
                    canvas2.drawPath(path8, this.c);
                }
                break;
            case 106440182:
                if (!str3.equals("pause")) {
                    Path path9 = new Path();
                    path9.moveTo(12.0f, 7.0f);
                    path9.lineTo(39.0f, 24.0f);
                    path9.lineTo(12.0f, 41.0f);
                    path9.close();
                    canvas2.drawPath(path9, this.c);
                } else {
                    canvas.drawRoundRect(10.0f, 6.0f, 19.0f, 42.0f, 2.0f, 2.0f, this.c);
                    canvas.drawRoundRect(29.0f, 6.0f, 38.0f, 42.0f, 2.0f, 2.0f, this.c);
                }
                break;
            default:
                Path path10 = new Path();
                path10.moveTo(12.0f, 7.0f);
                path10.lineTo(39.0f, 24.0f);
                path10.lineTo(12.0f, 41.0f);
                path10.close();
                canvas2.drawPath(path10, this.c);
                break;
        }
        canvas.restore();
    }

    public final void setSymbol(String str) {
        str.getClass();
        this.b = str;
    }

    public final void setSymbolScale(float f) {
        if (this.d == f) {
            return;
        }
        this.d = f;
        invalidate();
    }
}
