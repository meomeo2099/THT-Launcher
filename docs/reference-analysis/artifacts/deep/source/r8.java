package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class r8 extends View {
    public final /* synthetic */ boolean a;
    public final /* synthetic */ s8 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r8(boolean z, s8 s8Var, Context context) {
        super(context);
        this.a = z;
        this.b = s8Var;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        s8 s8Var = this.b;
        Paint paint = s8Var.d;
        canvas.getClass();
        float fMin = Math.min(getWidth(), getHeight());
        if (fMin <= 0.0f) {
            return;
        }
        float width = (getWidth() - fMin) / 2.0f;
        float height = (getHeight() - fMin) / 2.0f;
        boolean z = this.a;
        int iF = z ? l8.b : l8.f();
        l0.i(canvas, new RectF(width + 1.0f, height + 1.0f, (width + fMin) - 1.0f, (height + fMin) - 1.0f), 0.24f * fMin, s8Var.a, paint, false, 0, s8Var.getFaceAlpha(), true, null, 608);
        String str = z ? "plus" : "minus";
        Map map = i9.a;
        boolean z2 = s8Var.e;
        boolean z3 = s8Var.a;
        int iA = z2 ? l8.a(iF, z3) : l8.k(z3);
        float f = 0.22f * fMin;
        i9.a(canvas, str, paint, iA, width + f, height + f, fMin * 0.56f, 2.2f);
    }
}
