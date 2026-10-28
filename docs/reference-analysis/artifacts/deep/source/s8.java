package defpackage;

import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class s8 extends ViewGroup implements x8, yj {
    public final boolean a;
    public final boolean b;
    public final int c;
    public final Paint d;
    public boolean e;
    public int f;
    public final TextView g;
    public final r8 h;
    public final r8 i;

    public s8(MainActivity mainActivity, boolean z, c3 c3Var, c3 c3Var2, b2 b2Var, b2 b2Var2, boolean z2, int i) {
        super(mainActivity);
        this.a = z;
        this.b = z2;
        this.c = i;
        this.d = new Paint(1);
        this.f = 255;
        TextView textView = new TextView(mainActivity);
        textView.setTextColor(l8.j(z));
        textView.setGravity(17);
        textView.setText("—");
        textView.setTypeface(z9.c());
        textView.setIncludeFontPadding(false);
        textView.setMaxLines(1);
        textView.setClickable(true);
        textView.setLongClickable(true);
        textView.setOnClickListener(new s(6, b2Var2));
        textView.setOnLongClickListener(new wd(this, b2Var, 2));
        this.g = textView;
        r8 r8Var = new r8(false, this, getContext());
        r8Var.setClickable(true);
        int i2 = 3;
        r8Var.setOnClickListener(new m2(c3Var, i2));
        this.h = r8Var;
        r8 r8Var2 = new r8(true, this, getContext());
        r8Var2.setClickable(true);
        r8Var2.setOnClickListener(new m2(c3Var2, i2));
        this.i = r8Var2;
        if (z2) {
            setBackground(new n8(z, l8.m(mainActivity) * 26.0f));
        }
        addView(r8Var);
        addView(textView);
        addView(r8Var2);
        c(null, false);
    }

    @Override // defpackage.x8
    public final int a(int i) {
        return (((int) (b(i) * 0.22f)) * 2) + ((int) (b(i) * 2.05f)) + (b(i) * 2);
    }

    public final int b(int i) {
        int i2 = (int) (i * (this.b ? 0.62f : 0.44f));
        if (i2 < 1) {
            return 1;
        }
        return i2;
    }

    public final void c(Float f, boolean z) {
        String strD;
        String strA;
        this.e = z;
        if (f != null) {
            float fFloatValue = f.floatValue();
            List list = z2.a;
            strD = z2.d(fFloatValue);
        } else {
            strD = "—";
        }
        TextView textView = this.g;
        CharSequence text = textView.getText();
        if (!k8.e(text != null ? text.toString() : null, strD)) {
            textView.setText(strD);
            requestLayout();
        }
        textView.setAlpha(z ? 1.0f : 0.45f);
        if (z) {
            List list2 = si.a;
            strA = si.a("temperature_0_tap_opens_climate_hold_off", textView.getText());
        } else {
            strA = si.a("open_climate_controls", new Object[0]);
        }
        textView.setContentDescription(strA);
        String strA2 = z ? si.a("cooler", new Object[0]) : si.a("start_climate_in_auto", new Object[0]);
        r8 r8Var = this.h;
        r8Var.setContentDescription(strA2);
        String strA3 = z ? si.a("warmer", new Object[0]) : si.a("start_climate_in_auto", new Object[0]);
        r8 r8Var2 = this.i;
        r8Var2.setContentDescription(strA3);
        r8Var.invalidate();
        r8Var2.invalidate();
    }

    public int getFaceAlpha() {
        return this.f;
    }

    public final int getZone() {
        return this.c;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int i6 = i4 - i2;
        int iB = b(i6);
        int iB2 = (int) (b(i6) * 0.22f);
        int iB3 = (int) (b(i6) * 2.05f);
        int i7 = (i5 - ((iB2 * 2) + ((iB * 2) + iB3))) / 2;
        if (i7 < 0) {
            i7 = 0;
        }
        int i8 = (i6 - iB) / 2;
        int i9 = i8 + iB;
        this.h.layout(i7, i8, i7 + iB, i9);
        int i10 = iB + iB2 + i7;
        this.g.layout(i10, i8, i10 + iB3, i9);
        int i11 = iB3 + iB2 + i10;
        this.i.layout(i11, i8, iB + i11, i9);
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        setMeasuredDimension(size, size2);
        int iB = b(size2);
        int iB2 = (int) (b(size2) * 2.05f);
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iB, 1073741824);
        this.h.measure(iMakeMeasureSpec, iMakeMeasureSpec);
        this.i.measure(iMakeMeasureSpec, iMakeMeasureSpec);
        float f = iB * 0.45f;
        TextView textView = this.g;
        textView.setTextSize(0, f);
        CharSequence text = textView.getText();
        String string = text != null ? text.toString() : null;
        if (string == null) {
            string = "";
        }
        if (iB2 > 0 && string.length() > 0) {
            float fMeasureText = textView.getPaint().measureText(string);
            float f2 = iB2;
            if (fMeasureText > f2) {
                textView.setTextSize(0, (f2 / fMeasureText) * f);
            }
        }
        textView.measure(View.MeasureSpec.makeMeasureSpec(iB2, 1073741824), iMakeMeasureSpec);
    }

    @Override // defpackage.yj
    public void setFaceAlpha(int i) {
        this.f = i;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            getChildAt(i2).invalidate();
        }
    }
}
