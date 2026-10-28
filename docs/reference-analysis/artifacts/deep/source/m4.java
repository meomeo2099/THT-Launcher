package defpackage;

import android.view.View;
import android.view.ViewGroup;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class m4 extends ViewGroup implements x8 {
    public final View a;
    public final float b;

    public m4(MainActivity mainActivity, View view, float f) {
        super(mainActivity);
        this.a = view;
        this.b = f;
        addView(view);
    }

    @Override // defpackage.x8
    public final int a(int i) {
        float f = i;
        int i2 = (int) (0.8f * f * this.b);
        int i3 = (int) (f * 0.65f);
        return i2 < i3 ? i3 : i2;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int width = getWidth();
        View view = this.a;
        int measuredWidth = (width - view.getMeasuredWidth()) / 2;
        int height = (getHeight() - view.getMeasuredHeight()) / 2;
        view.layout(measuredWidth, height, view.getMeasuredWidth() + measuredWidth, view.getMeasuredHeight() + height);
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        setMeasuredDimension(size, size2);
        float f = size2;
        int i3 = (int) (size - (0.08f * f));
        if (i3 < 1) {
            i3 = 1;
        }
        int i4 = (int) (f * 0.8f);
        this.a.measure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), View.MeasureSpec.makeMeasureSpec(i4 >= 1 ? i4 : 1, 1073741824));
    }

    @Override // android.view.View
    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        super.setOnLongClickListener(onLongClickListener);
        this.a.setOnLongClickListener(new l4(this, 0));
    }
}
