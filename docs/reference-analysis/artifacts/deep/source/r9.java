package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import java.util.Map;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class r9 extends View {
    public final /* synthetic */ int a;
    public final Object b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r9(Context context) {
        super(context);
        this.a = 3;
        this.b = new Paint(1);
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int i = this.a;
        canvas.getClass();
        switch (i) {
            case 0:
                s9.b((s9) this.b, canvas, this, false);
                break;
            case 1:
                s9.b((s9) this.b, canvas, this, true);
                break;
            case 2:
                float fMin = Math.min(getWidth(), getHeight());
                s9 s9Var = (s9) this.b;
                boolean zE = k8.e(s9Var.f, Boolean.TRUE);
                float width = (getWidth() - fMin) / 2.0f;
                float height = (getHeight() - fMin) / 2.0f;
                int i2 = l8.a;
                int iB = l8.b(s9Var.a, zE, l8.g());
                boolean z = s9Var.a;
                Paint paint = s9Var.b;
                l0.i(canvas, new RectF(width + 1.0f, height + 1.0f, (width + fMin) - 1.0f, (height + fMin) - 1.0f), 0.24f * fMin, z, paint, zE, l8.g(), s9Var.getFaceAlpha(), true, null, 512);
                Map map = i9.a;
                float f = 0.18f * fMin;
                i9.a(canvas, "power", paint, iB, width + f, height + f, fMin * 0.64f, 2.6f);
                break;
            default:
                float fMin2 = Math.min(getWidth(), getHeight());
                float width2 = getWidth() / 2.0f;
                float height2 = getHeight() / 2.0f;
                ((Paint) this.b).setStyle(Paint.Style.FILL);
                ((Paint) this.b).setColor(l8.f());
                canvas.drawCircle(width2, height2, fMin2 / 2.0f, (Paint) this.b);
                l0 l0Var = ba.h;
                ba baVar = ba.k;
                if (baVar != null) {
                    float f2 = 0.3f * fMin2;
                    if (baVar.b(canvas, "update", -1, width2 - f2, height2 - f2, fMin2 * 0.6f)) {
                    }
                }
                ((Paint) this.b).setStyle(Paint.Style.STROKE);
                ((Paint) this.b).setColor(-1);
                ((Paint) this.b).setStrokeWidth(0.09f * fMin2);
                ((Paint) this.b).setStrokeCap(Paint.Cap.ROUND);
                float f3 = fMin2 * 0.14f;
                float f4 = height2 + f3;
                canvas.drawLine(width2, height2 - (0.22f * fMin2), width2, f4, (Paint) this.b);
                canvas.drawLine(width2 - f3, height2, width2, f4, (Paint) this.b);
                canvas.drawLine(width2 + f3, height2, width2, f4, (Paint) this.b);
                float f5 = 0.2f * fMin2;
                float f6 = height2 + (fMin2 * 0.26f);
                canvas.drawLine(width2 - f5, f6, width2 + f5, f6, (Paint) this.b);
                break;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ r9(MainActivity mainActivity, s9 s9Var, int i) {
        super(mainActivity);
        this.a = i;
        this.b = s9Var;
    }
}
