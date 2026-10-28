package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class p8 extends View {
    public final Paint a;
    public final /* synthetic */ String b;
    public final /* synthetic */ q8 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p8(q8 q8Var, Context context, String str) {
        super(context);
        this.b = str;
        this.c = q8Var;
        this.a = new Paint(1);
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        canvas.getClass();
        float fMin = Math.min(getWidth(), getHeight()) * 0.62f;
        Map map = i9.a;
        i9.a(canvas, this.b.endsWith("-") ? "minus" : "plus", this.a, this.c.i, (getWidth() - fMin) / 2.0f, (getHeight() - fMin) / 2.0f, fMin, 2.2f);
    }
}
