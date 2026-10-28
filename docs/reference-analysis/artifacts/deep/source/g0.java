package defpackage;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.RippleDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class g0 extends BaseAdapter {
    public final /* synthetic */ i0 a;

    public g0(i0 i0Var) {
        this.a = i0Var;
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        return this.a.g.size();
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i) {
        return (e0) this.a.g.get(i);
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        viewGroup.getClass();
        i0 i0Var = this.a;
        e0 e0Var = (e0) i0Var.g.get(i);
        LinearLayout linearLayout = view instanceof LinearLayout ? (LinearLayout) view : null;
        if (linearLayout == null) {
            linearLayout = new LinearLayout(i0Var.getContext());
            linearLayout.setOrientation(1);
            linearLayout.setGravity(17);
            int iA = i0Var.a(4);
            float f = i0Var.l;
            linearLayout.setPadding(iA, i0Var.a(10), i0Var.a(4), i0Var.a(8));
            int i2 = l8.a;
            linearLayout.setBackground(new RippleDrawable(ColorStateList.valueOf(l8.e(i0Var.b)), null, i0Var.e(-1, 22)));
            FrameLayout frameLayout = new FrameLayout(linearLayout.getContext());
            ImageView imageView = new ImageView(linearLayout.getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setImportantForAccessibility(2);
            frameLayout.addView(imageView, new FrameLayout.LayoutParams(i0Var.a(68), i0Var.a(68), 17));
            TextView textView = new TextView(linearLayout.getContext());
            textView.setText("★");
            textView.setTextSize(14.0f * f);
            textView.setTextColor(Color.rgb(255, 214, 10));
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2, 8388661);
            layoutParams.rightMargin = i0Var.a(2);
            layoutParams.topMargin = i0Var.a(0);
            frameLayout.addView(textView, layoutParams);
            linearLayout.addView(frameLayout, new LinearLayout.LayoutParams(i0Var.a(78), i0Var.a(78)));
            TextView textView2 = new TextView(linearLayout.getContext());
            textView2.setTextSize(f * 15.0f);
            textView2.setTextColor(i0Var.m);
            textView2.setGravity(17);
            textView2.setMaxLines(2);
            textView2.setEllipsize(TextUtils.TruncateAt.END);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, i0Var.a(42));
            layoutParams2.topMargin = i0Var.a(8);
            linearLayout.addView(textView2, layoutParams2);
            linearLayout.setLayoutParams(new AbsListView.LayoutParams(-1, i0Var.a(142)));
        }
        linearLayout.setTag(e0Var.b);
        View childAt = linearLayout.getChildAt(0);
        childAt.getClass();
        FrameLayout frameLayout2 = (FrameLayout) childAt;
        View childAt2 = frameLayout2.getChildAt(0);
        childAt2.getClass();
        ImageView imageView2 = (ImageView) childAt2;
        imageView2.setImageDrawable(i0Var.a.getPackageManager().getDefaultActivityIcon());
        frameLayout2.getChildAt(1).setVisibility(i0Var.j.contains(e0Var.b.flattenToString()) ? 0 : 8);
        View childAt3 = linearLayout.getChildAt(1);
        childAt3.getClass();
        ((TextView) childAt3).setText(e0Var.a);
        LinearLayout linearLayout2 = linearLayout;
        i0Var.e.execute(new l(this, i0Var, e0Var, linearLayout2, imageView2));
        return linearLayout2;
    }
}
