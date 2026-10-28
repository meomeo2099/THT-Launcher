package defpackage;

import android.R;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class yi {
    public final MainActivity a;
    public final k0 b;
    public final boolean c;
    public final i4 d;
    public final int e;
    public final int f;
    public final Dialog g;
    public final TextView h;
    public final TextView i;
    public final LinearLayout j;
    public final LinearLayout k;
    public final xi l;
    public final TextView m;

    public yi(MainActivity mainActivity, k0 k0Var) {
        int i;
        int i2;
        int i3;
        View decorView;
        Window window;
        k0Var.getClass();
        this.a = mainActivity;
        this.b = k0Var;
        boolean z = mainActivity.z();
        this.c = z;
        this.d = new i4(mainActivity, z);
        int iJ = l8.j(z);
        this.e = iJ;
        int iK = l8.k(z);
        this.f = iK;
        Dialog dialog = new Dialog(mainActivity);
        if (pi.c && (window = dialog.getWindow()) != null) {
            window.setType(2038);
        }
        this.g = dialog;
        TextView textViewD = d("", 24.0f, iJ, true);
        this.h = textViewD;
        TextView textViewD2 = d("", 15.0f, iK, false);
        this.i = textViewD2;
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        this.j = linearLayout;
        LinearLayout linearLayout2 = new LinearLayout(mainActivity);
        linearLayout2.setGravity(8388613);
        this.k = linearLayout2;
        this.l = new xi(this, mainActivity);
        this.m = d("", 16.0f, iJ, false);
        dialog.requestWindowFeature(1);
        LinearLayout linearLayout3 = new LinearLayout(mainActivity);
        linearLayout3.setOrientation(1);
        linearLayout3.setPadding(a(26), a(22), a(26), a(20));
        GradientDrawable gradientDrawable = new GradientDrawable();
        if (z) {
            i = 38;
            i2 = 49;
            i3 = 32;
        } else {
            i = 250;
            i2 = 253;
            i3 = 248;
        }
        gradientDrawable.setColor(Color.rgb(i3, i, i2));
        gradientDrawable.setCornerRadius(a(24));
        linearLayout3.setBackground(gradientDrawable);
        LinearLayout linearLayout4 = new LinearLayout(mainActivity);
        linearLayout4.setGravity(16);
        View r9Var = new r9(mainActivity);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(a(52), a(52));
        layoutParams.rightMargin = a(16);
        linearLayout4.addView(r9Var, layoutParams);
        LinearLayout linearLayout5 = new LinearLayout(mainActivity);
        linearLayout5.setOrientation(1);
        linearLayout5.addView(textViewD);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.topMargin = a(6);
        linearLayout5.addView(textViewD2, layoutParams2);
        linearLayout4.addView(linearLayout5, new LinearLayout.LayoutParams(0, -2, 1.0f));
        linearLayout3.addView(linearLayout4);
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.addView(linearLayout);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams3.topMargin = a(18);
        linearLayout3.addView(scrollView, layoutParams3);
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams4.topMargin = a(20);
        linearLayout3.addView(linearLayout2, layoutParams4);
        dialog.setContentView(linearLayout3);
        Window window2 = dialog.getWindow();
        if (window2 != null) {
            window2.setBackgroundDrawableResource(R.color.transparent);
        }
        Window window3 = dialog.getWindow();
        if (window3 != null && (decorView = window3.getDecorView()) != null) {
            decorView.setSystemUiVisibility(mainActivity.getWindow().getDecorView().getSystemUiVisibility());
        }
        dialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: ui
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                yi yiVar = this.a;
                DisplayMetrics displayMetrics = yiVar.a.getResources().getDisplayMetrics();
                Dialog dialog2 = yiVar.g;
                Window window4 = dialog2.getWindow();
                if (window4 != null) {
                    window4.setLayout(pi.s((int) (displayMetrics.widthPixels * 0.6f), yiVar.a(420), yiVar.a(760)), -2);
                }
                Window window5 = dialog2.getWindow();
                if (window5 != null) {
                    window5.setDimAmount(0.6f);
                }
            }
        });
    }

    public static final void e(yi yiVar, Button button) {
        LinearLayout linearLayout = yiVar.k;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, yiVar.a(52));
        layoutParams.leftMargin = yiVar.a(10);
        linearLayout.addView(button, layoutParams);
    }

    public static final void f(ArrayList arrayList, le leVar, boolean z, yi yiVar) {
        int iD;
        boolean z2 = yiVar.c;
        int size = arrayList.size();
        int i = 0;
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayList.get(i2);
            i2++;
            int i3 = i + 1;
            if (i < 0) {
                j3.m0();
                throw null;
            }
            LinearLayout linearLayout = (LinearLayout) obj;
            boolean z3 = true;
            boolean z4 = i == leVar.a;
            if (i != 0 && !z) {
                z3 = false;
            }
            GradientDrawable gradientDrawable = new GradientDrawable();
            if (z4) {
                iD = l8.l(l8.f(), z2 ? 60 : 36);
            } else {
                int i4 = l8.a;
                iD = l8.d(z2);
            }
            gradientDrawable.setColor(iD);
            gradientDrawable.setCornerRadius(yiVar.a(16));
            gradientDrawable.setStroke(yiVar.a(2), z4 ? l8.f() : 0);
            linearLayout.setBackground(gradientDrawable);
            linearLayout.setAlpha(z3 ? 1.0f : 0.45f);
            i = i3;
        }
    }

    public final int a(int i) {
        return (int) (i * this.a.getResources().getDisplayMetrics().density);
    }

    public final void b(String str, yc ycVar) {
        str.getClass();
        this.h.setText(si.a("update_not_installed", new Object[0]));
        this.i.setText("");
        LinearLayout linearLayout = this.j;
        linearLayout.removeAllViews();
        LinearLayout linearLayout2 = this.k;
        linearLayout2.removeAllViews();
        linearLayout.addView(d(str, 17.0f, l8.b, false));
        i4 i4Var = this.d;
        if (ycVar != null) {
            Button buttonA = i4Var.a((String) ycVar.a, false, new a8((y5) ycVar.b, 1));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, a(52));
            layoutParams.rightMargin = a(10);
            linearLayout2.addView(buttonA, layoutParams);
        }
        linearLayout2.addView(i4Var.a(si.a("close", new Object[0]), true, new wi(this, 3)), new LinearLayout.LayoutParams(-2, a(52)));
        this.g.setCancelable(true);
    }

    public final void c() {
        this.h.setText(si.a("update_handed_over", new Object[0]));
        this.i.setText(si.a("update_to_0", this.b.b));
        LinearLayout linearLayout = this.j;
        linearLayout.removeAllViews();
        LinearLayout linearLayout2 = this.k;
        linearLayout2.removeAllViews();
        linearLayout.addView(d(si.a("update_handed_over_hint", new Object[0]), 17.0f, this.e, false));
        linearLayout2.addView(this.d.a(si.a("close", new Object[0]), true, new wi(this, 2)), new LinearLayout.LayoutParams(-2, a(52)));
        this.g.setCancelable(true);
    }

    public final TextView d(String str, float f, int i, boolean z) {
        TextView textView = new TextView(this.a);
        textView.setText(str);
        textView.setTextSize(f);
        textView.setTextColor(i);
        textView.setIncludeFontPadding(false);
        if (z) {
            textView.setTypeface(textView.getTypeface(), 1);
        }
        textView.setLineSpacing(0.0f, 1.15f);
        return textView;
    }

    public final void g(int i, boolean z) {
        String strA;
        LinearLayout linearLayout = this.k;
        int childCount = linearLayout.getChildCount();
        TextView textView = this.m;
        xi xiVar = this.l;
        if (childCount > 0 || xiVar.getParent() == null) {
            List list = si.a;
            this.h.setText(si.a("update_to_0", this.b.b));
            this.i.setText(si.a("update_keep_on", new Object[0]));
            LinearLayout linearLayout2 = this.j;
            linearLayout2.removeAllViews();
            linearLayout.removeAllViews();
            linearLayout2.addView(xiVar, new LinearLayout.LayoutParams(-1, a(14)));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.topMargin = a(12);
            linearLayout2.addView(textView, layoutParams);
            this.g.setCancelable(false);
        }
        xiVar.setFraction(z ? 1.0f : i / 100.0f);
        xiVar.invalidate();
        if (z) {
            strA = si.a("update_installing", new Object[0]);
        } else {
            List list2 = si.a;
            strA = si.a("update_downloading_0", Integer.valueOf(i));
        }
        textView.setText(strA);
    }
}
