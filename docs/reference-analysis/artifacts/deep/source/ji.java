package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class ji extends View {
    public final boolean a;
    public final y5 b;
    public final Paint c;
    public final Path d;
    public float e;
    public boolean f;

    public ji(Context context, boolean z, y5 y5Var) {
        super(context);
        this.a = z;
        this.b = y5Var;
        this.c = new Paint(1);
        this.d = new Path();
        setClickable(true);
        setFocusable(true);
        List list = si.a;
        setContentDescription(si.a(z ? "theme_switch_to_day" : "theme_switch_to_night", new Object[0]));
        setOnClickListener(new s(15, this));
    }

    @Override // android.view.View
    public CharSequence getAccessibilityClassName() {
        return "android.widget.Button";
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        Canvas canvas2 = canvas;
        canvas2.getClass();
        super.onDraw(canvas);
        float width = getWidth();
        float height = getHeight();
        float fMin = Math.min(width, height);
        if (fMin <= 0.0f) {
            return;
        }
        float f = width / 2.0f;
        float f2 = height / 2.0f;
        float fSin = 1.0f - (((float) Math.sin(((double) this.e) * 3.141592653589793d)) * 0.15f);
        canvas2.save();
        canvas2.rotate(this.e * 90.0f, f, f2);
        canvas2.scale(fSin, fSin, f, f2);
        l0 l0Var = ba.h;
        ba baVar = ba.k;
        if (baVar != null) {
            boolean z = this.a;
            float f3 = fMin / 2.0f;
            if (!baVar.a(canvas2, z ? "theme-night" : "theme-day", z ? Color.rgb(214, 224, 255) : Color.rgb(255, 176, 32), f - f3, f2 - f3, fMin)) {
            }
            canvas.restore();
        }
        if (this.a) {
            float f4 = fMin * 0.36f;
            Path path = this.d;
            path.reset();
            Path.Direction direction = Path.Direction.CW;
            path.addCircle(f, f2, f4, direction);
            Path path2 = new Path();
            path2.addCircle((0.55f * f4) + f, f2 - (0.4f * f4), f4 * 0.85f, direction);
            path.op(path2, Path.Op.DIFFERENCE);
            Paint paint = this.c;
            paint.setColor(Color.rgb(214, 224, 255));
            paint.setStyle(Paint.Style.FILL);
            canvas2.drawPath(path, paint);
        } else {
            Paint paint2 = this.c;
            paint2.setColor(Color.rgb((int) r3, 176, 32));
            paint2.setStyle(Paint.Style.FILL);
            canvas2.drawCircle(f, f2, 0.2f * fMin, paint2);
            paint2.setStyle(Paint.Style.STROKE);
            paint2.setStrokeWidth(0.075f * fMin);
            paint2.setStrokeCap(Paint.Cap.ROUND);
            int i = 0;
            while (i < 8) {
                double d = (((double) i) * 3.141592653589793d) / 4.0d;
                double d2 = 0.31f * fMin;
                double d3 = 0.44f * fMin;
                canvas2.drawLine(((float) (Math.cos(d) * d2)) + f, ((float) (Math.sin(d) * d2)) + f2, ((float) (Math.cos(d) * d3)) + f, ((float) (Math.sin(d) * d3)) + f2, paint2);
                i++;
                canvas2 = canvas;
            }
        }
        canvas.restore();
    }
}
