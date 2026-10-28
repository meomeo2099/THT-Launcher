package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class t2 extends LinearLayout {
    public final boolean a;
    public final String b;
    public final View c;
    public final float d;
    public final b7 e;
    public final TextView f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t2(Context context, boolean z, String str, View view, boolean z2, boolean z3) {
        float f;
        super(context);
        str.getClass();
        view.getClass();
        this.a = z;
        this.b = str;
        this.c = view;
        float fM = l8.m(context);
        this.d = fM;
        b7 b7Var = new b7(z, a(17.0f), true);
        this.e = b7Var;
        TextView textView = new TextView(context);
        textView.setText(str);
        textView.setGravity(17);
        textView.setMaxLines(2);
        List list = si.a;
        textView.setIncludeFontPadding(si.b.a.equals("ar"));
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setTextSize(((z2 ? 15.0f : 13.5f) * fM) / textView.getResources().getDisplayMetrics().scaledDensity);
        textView.setTypeface(z9.c());
        int iA = a(10.0f);
        int iA2 = a(z2 ? 15.0f : 13.5f);
        int iA3 = a(10.0f);
        textView.setAutoSizeTextTypeUniformWithConfiguration(iA, iA2 < iA3 ? iA3 : iA2, 1, 0);
        this.f = textView;
        setOrientation(!z2 ? 1 : 0);
        setGravity(17);
        if (z3) {
            f = z2 ? 28.0f : 46.0f;
        } else {
            f = (z2 ? 47.0f : 88.0f) * 0.78f;
        }
        cj cjVar = view instanceof cj ? (cj) view : null;
        if (cjVar != null) {
            cjVar.setActiveSurface(true);
        }
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.addView(view, new FrameLayout.LayoutParams(a(f), a(f), 17));
        addView(frameLayout, new LinearLayout.LayoutParams(a(f), a(f)));
        if (z3) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(z2 ? 0 : -1, a(z2 ? 37.0f : 30.0f), z2 ? 1.0f : 0.0f);
            if (z2) {
                layoutParams.leftMargin = a(7.0f);
            } else {
                layoutParams.topMargin = a(2.0f);
            }
            addView(textView, layoutParams);
        }
        setClickable(true);
        setFocusable(true);
        setPadding(a(7.0f), a(5.0f), a(7.0f), a(5.0f));
        setBackground(b7Var);
        l8.h();
        b(-10233877, false);
    }

    public final int a(float f) {
        int i = (int) (f * this.d);
        if (i < 1) {
            return 1;
        }
        return i;
    }

    public final void b(int i, boolean z) {
        int i2 = l8.a;
        int iB = l8.b(this.a, z, i);
        this.e.a(i, z);
        this.f.setTextColor(iB);
        View view = this.c;
        d9 d9Var = view instanceof d9 ? (d9) view : null;
        if (d9Var != null) {
            d9Var.setColor(iB);
        }
        cj cjVar = view instanceof cj ? (cj) view : null;
        if (cjVar != null) {
            cjVar.setEnabledState(Boolean.valueOf(z));
        }
        TextView textView = view instanceof TextView ? (TextView) view : null;
        if (textView != null) {
            textView.setTextColor(iB);
        }
        setContentDescription(this.b + ": " + (z ? si.a("on_101", new Object[0]) : si.a("off_102", new Object[0])));
    }
}
