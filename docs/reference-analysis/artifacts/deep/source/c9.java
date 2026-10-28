package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.Iterator;
import java.util.List;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class c9 {
    public final MainActivity a;
    public final boolean b;
    public final float c;
    public final j6 d;
    public final int e;
    public final int f;

    public c9(MainActivity mainActivity, boolean z, float f, j6 j6Var) {
        this.a = mainActivity;
        this.b = z;
        this.c = f;
        this.d = j6Var;
        this.e = l8.j(z);
        this.f = l8.k(z);
    }

    public static /* synthetic */ TextView h(c9 c9Var, String str, float f, int i, int i2) {
        if ((i2 & 4) != 0) {
            i = c9Var.e;
        }
        return c9Var.g(str, f, i, false);
    }

    public static /* synthetic */ LinearLayout o(c9 c9Var, String str, String str2, String str3, y5 y5Var, int i) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            str3 = null;
        }
        return c9Var.n(str, str2, str3, (i & 8) != 0, y5Var);
    }

    public final LinearLayout a(FrameLayout frameLayout, String str, String str2, String str3, View view, y5 y5Var) {
        MainActivity mainActivity = this.a;
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setGravity(16);
        j6 j6Var = this.d;
        linearLayout.setMinimumHeight(((Number) j6Var.b(64)).intValue());
        linearLayout.setPadding(((Number) j6Var.b(16)).intValue(), ((Number) j6Var.b(10)).intValue(), ((Number) j6Var.b(14)).intValue(), ((Number) j6Var.b(10)).intValue());
        if (y5Var != null) {
            linearLayout.setClickable(true);
            linearLayout.setFocusable(true);
            int i = l8.a;
            linearLayout.setBackground(new RippleDrawable(ColorStateList.valueOf(l8.e(this.b)), null, new ColorDrawable(-1)));
            linearLayout.setOnClickListener(new m2(y5Var, 5));
        }
        if (frameLayout != null) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(((Number) j6Var.b(36)).intValue(), ((Number) j6Var.b(36)).intValue());
            layoutParams.setMarginEnd(((Number) j6Var.b(14)).intValue());
            linearLayout.addView(frameLayout, layoutParams);
        }
        LinearLayout linearLayout2 = new LinearLayout(mainActivity);
        linearLayout2.setOrientation(1);
        linearLayout2.addView(h(this, str, 18.0f, 0, 12));
        int i2 = this.f;
        if (str2 != null) {
            TextView textViewH = h(this, str2, 14.0f, i2, 8);
            textViewH.setPadding(0, ((Number) j6Var.b(2)).intValue(), 0, 0);
            linearLayout2.addView(textViewH);
        }
        linearLayout.addView(linearLayout2, new LinearLayout.LayoutParams(0, -2, 1.0f));
        if (str3 != null) {
            TextView textViewH2 = h(this, str3, 16.0f, i2, 8);
            textViewH2.setMaxLines(1);
            textViewH2.setEllipsize(TextUtils.TruncateAt.END);
            textViewH2.setPadding(((Number) j6Var.b(10)).intValue(), 0, ((Number) j6Var.b(6)).intValue(), 0);
            linearLayout.addView(textViewH2, new LinearLayout.LayoutParams(-2, -2));
        }
        if (view != null) {
            linearLayout.addView(view);
        }
        return linearLayout;
    }

    public final TextView b(String str) {
        str.getClass();
        TextView textViewH = h(this, str, 14.0f, this.f, 8);
        j6 j6Var = this.d;
        textViewH.setPadding(((Number) j6Var.b(16)).intValue(), ((Number) j6Var.b(20)).intValue(), ((Number) j6Var.b(16)).intValue(), ((Number) j6Var.b(8)).intValue());
        return textViewH;
    }

    public final FrameLayout c(FrameLayout frameLayout) {
        FrameLayout frameLayout2 = new FrameLayout(this.a);
        int i = l8.a;
        frameLayout2.setBackground(m(l8.d(this.b), 18));
        frameLayout2.setClipToOutline(true);
        frameLayout2.addView(frameLayout, new FrameLayout.LayoutParams(-1, -1));
        frameLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, ((Number) this.d.b(220)).intValue()));
        return frameLayout2;
    }

    public final LinearLayout d(View... viewArr) {
        LinearLayout linearLayout = new LinearLayout(this.a);
        linearLayout.setOrientation(1);
        int i = l8.a;
        boolean z = this.b;
        linearLayout.setBackground(m(l8.d(z), 18));
        linearLayout.setClipToOutline(true);
        int length = viewArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            View view = viewArr[i2];
            int i4 = i3 + 1;
            if (i3 > 0) {
                View view2 = new View(linearLayout.getContext());
                view2.setBackgroundColor(l8.e(z));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 1);
                layoutParams.setMarginStart(((Number) this.d.b(16)).intValue());
                linearLayout.addView(view2, layoutParams);
            }
            linearLayout.addView(view, new LinearLayout.LayoutParams(-1, -2));
            i2++;
            i3 = i4;
        }
        return linearLayout;
    }

    public final TextView e(String str) {
        str.getClass();
        TextView textViewG = g(str, 26.0f, this.e, true);
        j6 j6Var = this.d;
        textViewG.setPadding(((Number) j6Var.b(4)).intValue(), ((Number) j6Var.b(4)).intValue(), ((Number) j6Var.b(4)).intValue(), ((Number) j6Var.b(2)).intValue());
        return textViewG;
    }

    public final LinearLayout f(View view, String str, List list, y5 y5Var) {
        view.getClass();
        str.getClass();
        MainActivity mainActivity = this.a;
        FrameLayout frameLayout = new FrameLayout(mainActivity);
        int i = l8.a;
        frameLayout.setBackground(m(l8.e(this.b), 10));
        j6 j6Var = this.d;
        frameLayout.addView(view, new FrameLayout.LayoutParams(((Number) j6Var.b(30)).intValue(), ((Number) j6Var.b(26)).intValue(), 17));
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setGravity(16);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            View view2 = (View) it.next();
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(((Number) j6Var.b(42)).intValue(), ((Number) j6Var.b(42)).intValue());
            layoutParams.setMarginStart(((Number) j6Var.b(8)).intValue());
            linearLayout.addView(view2, layoutParams);
        }
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        return a(frameLayout, str, null, null, linearLayout, y5Var);
    }

    public final TextView g(String str, float f, int i, boolean z) {
        str.getClass();
        TextView textView = new TextView(this.a);
        textView.setText(str);
        textView.setTextSize(f * this.c);
        textView.setTextColor(i);
        textView.setTypeface(z ? z9.c() : z9.b());
        return textView;
    }

    public final TextView i(String str) {
        str.getClass();
        TextView textViewH = h(this, str, 15.0f, this.f, 8);
        j6 j6Var = this.d;
        textViewH.setPadding(((Number) j6Var.b(4)).intValue(), 0, ((Number) j6Var.b(4)).intValue(), ((Number) j6Var.b(14)).intValue());
        return textViewH;
    }

    public final FrameLayout j(String str, boolean z, boolean z2, y5 y5Var) {
        FrameLayout frameLayout = new FrameLayout(this.a);
        int i = l8.a;
        frameLayout.setBackground(m(l8.e(this.b), 12));
        frameLayout.setAlpha(z ? 1.0f : 0.3f);
        Context context = frameLayout.getContext();
        context.getClass();
        d9 d9Var = new d9(context, str);
        d9Var.setColor(z2 ? l8.b : this.e);
        j6 j6Var = this.d;
        frameLayout.addView(d9Var, new FrameLayout.LayoutParams(((Number) j6Var.b(18)).intValue(), ((Number) j6Var.b(18)).intValue(), 17));
        if (z) {
            frameLayout.setClickable(true);
            frameLayout.setOnClickListener(new m2(y5Var, 6));
        }
        return frameLayout;
    }

    public final TextView l(String str) {
        str.getClass();
        TextView textViewH = h(this, str, 14.0f, this.f, 8);
        j6 j6Var = this.d;
        textViewH.setPadding(((Number) j6Var.b(16)).intValue(), ((Number) j6Var.b(8)).intValue(), ((Number) j6Var.b(16)).intValue(), 0);
        return textViewH;
    }

    public final GradientDrawable m(int i, int i2) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(i);
        gradientDrawable.setCornerRadius(((Number) this.d.b(Integer.valueOf(i2))).intValue());
        return gradientDrawable;
    }

    public final LinearLayout n(String str, String str2, String str3, boolean z, y5 y5Var) {
        d9 d9Var;
        str.getClass();
        if (z) {
            d9Var = new d9(this.a, "chev");
            d9Var.setColor(this.f);
            j6 j6Var = this.d;
            d9Var.setLayoutParams(new LinearLayout.LayoutParams(((Number) j6Var.b(18)).intValue(), ((Number) j6Var.b(18)).intValue()));
        } else {
            d9Var = null;
        }
        return a(null, str, str2, str3, d9Var, y5Var);
    }

    public final FrameLayout p(List list, String str, ya yaVar, za zaVar, ScrollView scrollView, za zaVar2) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        Integer num = 34;
        str.getClass();
        MainActivity mainActivity = this.a;
        FrameLayout frameLayout = new FrameLayout(mainActivity);
        boolean z = this.b;
        frameLayout.setBackgroundColor(z ? Color.argb(115, 0, 0, 0) : Color.argb(140, 220, 228, 238));
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        int i6 = 17;
        if (z) {
            i2 = 38;
            i = 24;
        } else {
            i = 245;
            i2 = 249;
            i6 = 242;
        }
        linearLayout.setBackground(m(Color.rgb(i6, i, i2), 30));
        linearLayout.setClipToOutline(true);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        j6 j6Var = this.d;
        Integer num2 = 10;
        layoutParams.setMargins(((Number) j6Var.b(40)).intValue(), ((Number) j6Var.b(24)).intValue(), ((Number) j6Var.b(40)).intValue(), ((Number) j6Var.b(24)).intValue());
        frameLayout.addView(linearLayout, layoutParams);
        LinearLayout linearLayout2 = new LinearLayout(mainActivity);
        linearLayout2.setGravity(16);
        linearLayout2.setPadding(((Number) j6Var.b(26)).intValue(), ((Number) j6Var.b(16)).intValue(), ((Number) j6Var.b(22)).intValue(), ((Number) j6Var.b(8)).intValue());
        String strA = si.a("settings", new Object[0]);
        int i7 = this.e;
        linearLayout2.addView(g(strA, 28.0f, i7, true), new LinearLayout.LayoutParams(0, -2, 1.0f));
        TextView textViewG = g(si.a("buy_the_developer_a_meal", new Object[0]), 17.0f, Color.rgb(255, 55, 95), true);
        textViewG.setGravity(17);
        if (z) {
            i5 = 60;
            i3 = 255;
            i4 = 95;
        } else {
            i3 = 255;
            i4 = 95;
            i5 = 36;
        }
        textViewG.setBackground(m(Color.argb(i5, i3, 55, i4), 24));
        textViewG.setPadding(((Number) j6Var.b(22)).intValue(), 0, ((Number) j6Var.b(22)).intValue(), 0);
        textViewG.setClickable(true);
        textViewG.setContentDescription(si.a("buy_the_developer_a_meal_198", new Object[0]));
        textViewG.setOnClickListener(new s(7, zaVar2));
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, ((Number) j6Var.b(48)).intValue());
        layoutParams2.setMarginEnd(((Number) j6Var.b(12)).intValue());
        linearLayout2.addView(textViewG, layoutParams2);
        TextView textViewG2 = g(si.a("done", new Object[0]), 17.0f, -1, true);
        textViewG2.setGravity(17);
        textViewG2.setBackground(m(l8.f(), 24));
        textViewG2.setPadding(((Number) j6Var.b(26)).intValue(), 0, ((Number) j6Var.b(26)).intValue(), 0);
        textViewG2.setClickable(true);
        textViewG2.setOnClickListener(new s(8, zaVar));
        linearLayout2.addView(textViewG2, new LinearLayout.LayoutParams(-2, ((Number) j6Var.b(48)).intValue()));
        linearLayout.addView(linearLayout2);
        LinearLayout linearLayout3 = new LinearLayout(mainActivity);
        LinearLayout linearLayout4 = new LinearLayout(mainActivity);
        linearLayout4.setOrientation(1);
        linearLayout4.setPadding(((Number) j6Var.b(12)).intValue(), ((Number) j6Var.b(8)).intValue(), ((Number) j6Var.b(8)).intValue(), ((Number) j6Var.b(16)).intValue());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            b9 b9Var = (b9) it.next();
            String str2 = b9Var.a;
            String str3 = b9Var.b;
            boolean zEquals = str2.equals(str);
            LinearLayout linearLayout5 = new LinearLayout(mainActivity);
            linearLayout5.setGravity(16);
            Integer num3 = num2;
            Iterator it2 = it;
            linearLayout5.setPadding(((Number) j6Var.b(num3)).intValue(), 0, ((Number) j6Var.b(num3)).intValue(), 0);
            linearLayout5.setClickable(true);
            linearLayout5.setOnClickListener(new g4(yaVar, b9Var, 1));
            if (zEquals) {
                linearLayout5.setBackground(m(l8.f(), 14));
            }
            linearLayout5.setContentDescription(str3);
            FrameLayout frameLayout2 = new FrameLayout(mainActivity);
            frameLayout2.setBackground(m(b9Var.c, 9));
            Context context = frameLayout2.getContext();
            context.getClass();
            d9 d9Var = new d9(context, b9Var.d);
            d9Var.setColor(-1);
            num2 = num3;
            frameLayout2.addView(d9Var, new FrameLayout.LayoutParams(((Number) j6Var.b(20)).intValue(), ((Number) j6Var.b(20)).intValue(), 17));
            Integer num4 = num;
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(((Number) j6Var.b(num4)).intValue(), ((Number) j6Var.b(num4)).intValue());
            layoutParams3.setMarginEnd(((Number) j6Var.b(12)).intValue());
            linearLayout5.addView(frameLayout2, layoutParams3);
            linearLayout5.addView(h(this, str3, 18.0f, zEquals ? -1 : i7, 8));
            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, ((Number) j6Var.b(56)).intValue());
            layoutParams4.bottomMargin = ((Number) j6Var.b(4)).intValue();
            linearLayout4.addView(linearLayout5, layoutParams4);
            it = it2;
            num = num4;
        }
        ScrollView scrollView2 = new ScrollView(mainActivity);
        scrollView2.setVerticalScrollBarEnabled(false);
        scrollView2.addView(linearLayout4);
        linearLayout3.addView(scrollView2, new LinearLayout.LayoutParams(((Number) j6Var.b(290)).intValue(), -1));
        linearLayout3.addView(scrollView, new LinearLayout.LayoutParams(0, -1, 1.0f));
        linearLayout.addView(linearLayout3, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        return frameLayout;
    }

    public final LinearLayout q(String str, List list, int i, final j6 j6Var) {
        str.getClass();
        LinearLayout linearLayout = new LinearLayout(this.a);
        int i2 = l8.a;
        linearLayout.setBackground(m(l8.e(this.b), 12));
        j6 j6Var2 = this.d;
        linearLayout.setPadding(((Number) j6Var2.b(3)).intValue(), ((Number) j6Var2.b(3)).intValue(), ((Number) j6Var2.b(3)).intValue(), ((Number) j6Var2.b(3)).intValue());
        final int i3 = 0;
        for (Object obj : list) {
            int i4 = i3 + 1;
            if (i3 < 0) {
                j3.m0();
                throw null;
            }
            String str2 = (String) obj;
            boolean z = i3 == i;
            TextView textViewG = g(str2, 15.0f, z ? -1 : this.e, z);
            textViewG.setGravity(17);
            textViewG.setPadding(((Number) j6Var2.b(14)).intValue(), 0, ((Number) j6Var2.b(14)).intValue(), 0);
            if (z) {
                textViewG.setBackground(m(l8.f(), 9));
            }
            textViewG.setClickable(true);
            textViewG.setOnClickListener(new View.OnClickListener() { // from class: z8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    j6Var.b(Integer.valueOf(i3));
                }
            });
            linearLayout.addView(textViewG, new LinearLayout.LayoutParams(-2, ((Number) j6Var2.b(40)).intValue()));
            i3 = i4;
        }
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        return a(null, str, null, null, linearLayout, null);
    }

    public final LinearLayout r(String str, String str2, boolean z, j6 j6Var) {
        str.getClass();
        t9 t9Var = new t9(this.a, z);
        j6 j6Var2 = this.d;
        t9Var.setLayoutParams(new LinearLayout.LayoutParams(((Number) j6Var2.b(58)).intValue(), ((Number) j6Var2.b(34)).intValue()));
        t9Var.setContentDescription(str);
        LinearLayout linearLayoutA = a(null, str, str2, null, t9Var, null);
        final y1 y1Var = new y1(t9Var, j6Var, 3);
        final int i = 1;
        linearLayoutA.setClickable(true);
        final int i2 = 0;
        linearLayoutA.setOnClickListener(new View.OnClickListener() { // from class: a9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i3 = i2;
                y1 y1Var2 = y1Var;
                switch (i3) {
                    case 0:
                        y1Var2.a();
                        break;
                    default:
                        y1Var2.a();
                        break;
                }
            }
        });
        t9Var.setOnClickListener(new View.OnClickListener() { // from class: a9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i3 = i;
                y1 y1Var2 = y1Var;
                switch (i3) {
                    case 0:
                        y1Var2.a();
                        break;
                    default:
                        y1Var2.a();
                        break;
                }
            }
        });
        return linearLayoutA;
    }

    public final LinearLayout s(kf kfVar, String str, boolean z, c3 c3Var) {
        str.getClass();
        LinearLayout linearLayout = new LinearLayout(this.a);
        linearLayout.setOrientation(1);
        linearLayout.setClickable(true);
        linearLayout.setOnClickListener(new s(9, c3Var));
        linearLayout.setContentDescription(z ? si.a("0_selected", str) : str);
        FrameLayout frameLayout = new FrameLayout(linearLayout.getContext());
        frameLayout.setBackground(m(z ? l8.f() : 0, 18));
        j6 j6Var = this.d;
        frameLayout.setPadding(((Number) j6Var.b(3)).intValue(), ((Number) j6Var.b(3)).intValue(), ((Number) j6Var.b(3)).intValue(), ((Number) j6Var.b(3)).intValue());
        FrameLayout frameLayout2 = new FrameLayout(linearLayout.getContext());
        int i = l8.a;
        frameLayout2.setBackground(m(l8.e(this.b), 16));
        frameLayout2.setClipToOutline(true);
        frameLayout2.addView(kfVar, new FrameLayout.LayoutParams(-1, -1));
        frameLayout.addView(frameLayout2, new FrameLayout.LayoutParams(-1, -1));
        linearLayout.addView(frameLayout, new LinearLayout.LayoutParams(-1, ((Number) j6Var.b(110)).intValue()));
        TextView textViewH = h(this, str, 15.0f, 0, 12);
        textViewH.setGravity(17);
        textViewH.setPadding(0, ((Number) j6Var.b(8)).intValue(), 0, 0);
        linearLayout.addView(textViewH);
        return linearLayout;
    }

    public final LinearLayout t(String str, FrameLayout frameLayout, y5 y5Var) {
        str.getClass();
        MainActivity mainActivity = this.a;
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setBackground(m(this.b ? Color.rgb(17, 24, 38) : Color.rgb(242, 245, 249), 28));
        linearLayout.setClipToOutline(true);
        j6 j6Var = this.d;
        linearLayout.setPadding(((Number) j6Var.b(22)).intValue(), ((Number) j6Var.b(14)).intValue(), ((Number) j6Var.b(22)).intValue(), ((Number) j6Var.b(18)).intValue());
        LinearLayout linearLayout2 = new LinearLayout(mainActivity);
        linearLayout2.setGravity(16);
        linearLayout2.addView(g(str, 26.0f, this.e, true), new LinearLayout.LayoutParams(0, -2, 1.0f));
        TextView textViewG = g(si.a("done", new Object[0]), 17.0f, -1, true);
        textViewG.setGravity(17);
        textViewG.setBackground(m(l8.f(), 24));
        textViewG.setPadding(((Number) j6Var.b(24)).intValue(), 0, ((Number) j6Var.b(24)).intValue(), 0);
        textViewG.setClickable(true);
        textViewG.setOnClickListener(new m2(y5Var, 4));
        linearLayout2.addView(textViewG, new LinearLayout.LayoutParams(-2, ((Number) j6Var.b(48)).intValue()));
        linearLayout.addView(linearLayout2, new LinearLayout.LayoutParams(-1, ((Number) j6Var.b(56)).intValue()));
        linearLayout.addView(frameLayout, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        return linearLayout;
    }
}
