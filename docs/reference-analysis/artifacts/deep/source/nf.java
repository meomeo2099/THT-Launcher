package defpackage;

import android.graphics.Canvas;
import android.text.TextPaint;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class nf implements o6 {
    public final /* synthetic */ int a;
    public final /* synthetic */ String b;
    public final /* synthetic */ float c;
    public final /* synthetic */ TextPaint d;

    public /* synthetic */ nf(String str, float f, TextPaint textPaint, int i) {
        this.a = i;
        this.b = str;
        this.c = f;
        this.d = textPaint;
    }

    @Override // defpackage.o6
    public final Object a(Object obj, Object obj2, Object obj3) {
        int i = this.a;
        ti tiVar = ti.a;
        TextPaint textPaint = this.d;
        float f = this.c;
        String str = this.b;
        Canvas canvas = (Canvas) obj;
        float fFloatValue = ((Float) obj2).floatValue();
        float fFloatValue2 = ((Float) obj3).floatValue();
        switch (i) {
            case 0:
                canvas.getClass();
                canvas.drawText(str, fFloatValue, fFloatValue2 + f, textPaint);
                break;
            case 1:
                canvas.getClass();
                canvas.drawText(str, fFloatValue, fFloatValue2 + f, textPaint);
                break;
            default:
                canvas.getClass();
                canvas.drawText(str, fFloatValue, fFloatValue2 + f, textPaint);
                break;
        }
        return tiVar;
    }
}
