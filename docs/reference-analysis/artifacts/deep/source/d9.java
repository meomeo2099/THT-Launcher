package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class d9 extends View {
    public String a;
    public final Paint b;
    public int c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d9(Context context, String str) {
        super(context);
        context.getClass();
        this.a = str;
        this.b = new Paint(1);
        this.c = -1;
        setImportantForAccessibility(2);
    }

    public final int getColor() {
        return this.c;
    }

    public final String getName() {
        return this.a;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        Paint paint = this.b;
        canvas.getClass();
        float fMin = Math.min(getWidth(), getHeight());
        if (!ci.t(this.a, "text:")) {
            int iSave = canvas.save();
            if (k8.e(this.a, "chev")) {
                List list = si.a;
                if (si.b.a.equals("ar")) {
                    canvas.scale(-1.0f, 1.0f, getWidth() / 2.0f, getHeight() / 2.0f);
                }
            }
            Map map = i9.a;
            i9.a(canvas, this.a, paint, this.c, (getWidth() - fMin) / 2.0f, (getHeight() - fMin) / 2.0f, fMin, 2.2f);
            canvas.restoreToCount(iSave);
            return;
        }
        List listD = th.D(th.C(this.a, "text:"), new char[]{'|'});
        paint.setColor(this.c);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(z9.c());
        paint.setFakeBoldText(true);
        paint.setTextSize((0.9f * fMin) / listD.size());
        Iterator it = listD.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        float fMeasureText = paint.measureText((String) it.next());
        while (it.hasNext()) {
            fMeasureText = Math.max(fMeasureText, paint.measureText((String) it.next()));
        }
        float f = fMin * 0.92f;
        if (fMeasureText > f) {
            paint.setTextSize((f / fMeasureText) * paint.getTextSize());
        }
        float fontSpacing = paint.getFontSpacing() * 0.92f;
        float height = (getHeight() / 2.0f) - (((listD.size() - 1) * fontSpacing) / 2.0f);
        int i = 0;
        for (Object obj : listD) {
            int i2 = i + 1;
            if (i < 0) {
                j3.m0();
                throw null;
            }
            canvas.drawText((String) obj, getWidth() / 2.0f, ((i * fontSpacing) + height) - ((paint.ascent() + paint.descent()) / 2.0f), paint);
            i = i2;
        }
        paint.setFakeBoldText(false);
    }

    public final void setColor(int i) {
        this.c = i;
        invalidate();
    }

    public final void setName(String str) {
        str.getClass();
        this.a = str;
    }
}
