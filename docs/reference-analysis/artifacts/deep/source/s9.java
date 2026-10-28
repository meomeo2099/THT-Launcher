package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Map;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class s9 extends ViewGroup implements x8, yj {
    public final boolean a;
    public final Paint b;
    public int c;
    public final TextView d;
    public final y8 e;
    public Boolean f;
    public final r9 g;
    public final r9 h;
    public final r9 i;

    public s9(MainActivity mainActivity, boolean z, ua uaVar, ua uaVar2, v1 v1Var, ua uaVar3, ua uaVar4) {
        super(mainActivity);
        this.a = z;
        this.b = new Paint(1);
        this.c = 255;
        TextView textView = new TextView(mainActivity);
        textView.setTextColor(l8.j(z));
        textView.setGravity(17);
        textView.setText("—");
        textView.setTypeface(z9.c());
        this.d = textView;
        y8 y8Var = new y8(mainActivity, z, v1Var, Integer.valueOf(l8.j(z)));
        this.e = y8Var;
        r9 r9Var = new r9(mainActivity, this, 2);
        r9Var.setClickable(true);
        r9Var.setContentDescription(si.a("turn_climate_on_or_off", new Object[0]));
        r9Var.setOnClickListener(new s(11, uaVar4));
        this.g = r9Var;
        r9 r9Var2 = new r9(mainActivity, this, 0);
        r9Var2.setClickable(true);
        r9Var2.setContentDescription(si.a("cooler", new Object[0]));
        r9Var2.setOnClickListener(new s(12, uaVar));
        this.h = r9Var2;
        r9 r9Var3 = new r9(mainActivity, this, 1);
        r9Var3.setClickable(true);
        r9Var3.setContentDescription(si.a("warmer", new Object[0]));
        r9Var3.setOnClickListener(new s(13, uaVar2));
        this.i = r9Var3;
        addView(r9Var);
        addView(r9Var2);
        addView(textView);
        addView(r9Var3);
        addView(y8Var);
        textView.setClickable(true);
        textView.setContentDescription(si.a("open_climate_controls", new Object[0]));
        textView.setOnClickListener(new s(14, uaVar3));
    }

    public static final void b(s9 s9Var, Canvas canvas, View view, boolean z) {
        Paint paint = s9Var.b;
        float fMin = Math.min(view.getWidth(), view.getHeight());
        int iF = z ? l8.b : l8.f();
        float width = (view.getWidth() - fMin) / 2.0f;
        float height = (view.getHeight() - fMin) / 2.0f;
        l0.i(canvas, new RectF(width + 1.0f, height + 1.0f, (width + fMin) - 1.0f, (height + fMin) - 1.0f), fMin * 0.24f, s9Var.a, paint, false, 0, s9Var.getFaceAlpha(), true, null, 608);
        Map map = i9.a;
        float f = 0.22f * fMin;
        i9.a(canvas, z ? "plus" : "minus", paint, l8.a(iF, s9Var.a), width + f, height + f, fMin * 0.56f, 2.2f);
    }

    @Override // defpackage.x8
    public final int a(int i) {
        int i2 = (int) (i * 0.44f);
        r9 r9Var = this.g;
        int i3 = (r9Var != null ? 3 : 2) * i2;
        float f = i2;
        return (((int) (f * 0.22f)) * (r9Var != null ? 3 : 2)) + ((int) (2.05f * f)) + i3;
    }

    public int getFaceAlpha() {
        return this.c;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6 = i4 - i2;
        float f = i6;
        int i7 = (int) (0.44f * f);
        float f2 = i7;
        int i8 = (int) (2.05f * f2);
        int i9 = (int) (f2 * 0.22f);
        int iA = ((i3 - i) - a(i6)) / 2;
        if (iA < 0) {
            iA = 0;
        }
        y8 y8Var = this.e;
        int i10 = y8Var.getVisibility() == 0 ? (int) (0.12f * f) : (i6 - i7) / 2;
        r9 r9Var = this.g;
        if (r9Var != null) {
            r9Var.layout(iA, i10, iA + i7, i10 + i7);
            i5 = i7 + i9 + iA;
        } else {
            i5 = iA;
        }
        int i11 = i10 + i7;
        this.h.layout(i5, i10, i5 + i7, i11);
        int i12 = i7 + i9 + i5;
        this.d.layout(i12, i10, i12 + i8, i11);
        int i13 = i8 + i9 + i12;
        this.i.layout(i13, i10, i7 + i13, i11);
        int i14 = i6 - ((int) (f * 0.06f));
        y8Var.layout(iA, i14 - y8Var.getMeasuredHeight(), y8Var.getMeasuredWidth() + iA, i14);
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        setMeasuredDimension(size, size2);
        float f = size2;
        int i3 = (int) (0.44f * f);
        ArrayList arrayList = (ArrayList) x0.S0(new View[]{this.h, this.i, this.g});
        int size3 = arrayList.size();
        int i4 = 0;
        while (i4 < size3) {
            Object obj = arrayList.get(i4);
            i4++;
            ((View) obj).measure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), View.MeasureSpec.makeMeasureSpec(i3, 1073741824));
        }
        int i5 = (int) (i3 * 2.05f);
        float f2 = 0.2f * f;
        TextView textView = this.d;
        textView.setTextSize(0, f2);
        CharSequence text = textView.getText();
        String string = text != null ? text.toString() : null;
        if (string == null) {
            string = "";
        }
        if (i5 > 0 && string.length() > 0) {
            float fMeasureText = textView.getPaint().measureText(string);
            float f3 = i5;
            if (fMeasureText > f3) {
                textView.setTextSize(0, (f3 / fMeasureText) * f2);
            }
        }
        textView.measure(View.MeasureSpec.makeMeasureSpec(i5, 1073741824), View.MeasureSpec.makeMeasureSpec(i3, 1073741824));
        y8 y8Var = this.e;
        y8Var.setDrawHeight((int) (0.22f * f));
        y8Var.measure(View.MeasureSpec.makeMeasureSpec(a(size2), 1073741824), View.MeasureSpec.makeMeasureSpec((int) (f * 0.36f), 1073741824));
    }

    @Override // defpackage.yj
    public void setFaceAlpha(int i) {
        this.c = i;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            getChildAt(i2).invalidate();
        }
    }
}
