package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;
import java.util.List;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class nd extends View {
    public final boolean a;
    public final String b;
    public final boolean c;
    public final Paint d;
    public final Path e;
    public final RectF f;
    public boolean g;

    public nd(MainActivity mainActivity, boolean z, String str, boolean z2) {
        super(mainActivity);
        this.a = z;
        this.b = str;
        this.c = z2;
        this.d = new Paint(1);
        this.e = new Path();
        this.f = new RectF();
        setClickable(true);
        setFocusable(true);
        List list = si.a;
        setContentDescription(si.a(str.equals("next") ? "radio_next_favorite" : str.equals("prev") ? "radio_previous_favorite" : "play", new Object[0]));
    }

    public static final void a(nd ndVar, float f, float f2, Canvas canvas, float f3, float f4) {
        Path path = ndVar.e;
        path.reset();
        float f5 = f4 * f;
        float f6 = f3 - (0.5f * f5);
        float f7 = f * 0.62f;
        path.moveTo(f6, f2 - f7);
        path.lineTo((f5 * 0.55f) + f3, f2);
        path.lineTo(f6, f2 + f7);
        path.close();
        canvas.drawPath(path, ndVar.d);
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        canvas.getClass();
        float width = getWidth();
        float height = getHeight();
        float fMin = Math.min(width, height) / 2.0f;
        float f = width / 2.0f;
        float f2 = height / 2.0f;
        this.f.set(f - fMin, f2 - fMin, f + fMin, f2 + fMin);
        RectF rectF = this.f;
        boolean z = this.a;
        Paint paint = this.d;
        boolean z2 = this.c;
        l8.h();
        l0.i(canvas, rectF, fMin, z, paint, z2, -10233877, 0, true, null, 640);
        this.d.setStyle(Paint.Style.FILL);
        this.d.setShader(null);
        Paint paint2 = this.d;
        boolean z3 = this.a;
        boolean z4 = this.c;
        l8.h();
        paint2.setColor(l8.b(z3, z4, -10233877));
        float f3 = 0.42f * fMin;
        String str = (String) ba.u.get((k8.e(this.b, "play") && this.g) ? "pause" : this.b);
        if (str == null) {
            str = "play";
        }
        ba baVar = ba.k;
        if (baVar != null) {
            float f4 = fMin * 0.5f;
            if (baVar.a(canvas, str, this.d.getColor(), f - f4, f2 - f4, fMin)) {
                return;
            }
        }
        String str2 = this.b;
        if (!k8.e(str2, "play")) {
            if (k8.e(str2, "next")) {
                a(this, f3, f2, canvas, f - (f3 * 0.2f), 1.0f);
                float f5 = f3 * 0.62f;
                canvas.drawRect((f3 * 0.5f) + f, f2 - f5, (f3 * 0.7f) + f, f2 + f5, this.d);
                return;
            } else {
                a(this, f3, f2, canvas, (0.2f * f3) + f, -1.0f);
                float f6 = f3 * 0.62f;
                canvas.drawRect(f - (f3 * 0.7f), f2 - f6, f - (f3 * 0.5f), f2 + f6, this.d);
                return;
            }
        }
        if (!this.g) {
            a(this, f3, f2, canvas, (0.1f * f3) + f, 1.0f);
            return;
        }
        float f7 = 0.55f * f3;
        float f8 = 0.62f * f3;
        float f9 = f2 - f8;
        float f10 = 0.15f * f3;
        float f11 = f2 + f8;
        float f12 = f3 * 0.1f;
        canvas.drawRoundRect(f - f7, f9, f - f10, f11, f12, f12, this.d);
        canvas.drawRoundRect(f + f10, f9, f + f7, f11, f12, f12, this.d);
    }
}
