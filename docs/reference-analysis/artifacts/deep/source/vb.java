package defpackage;

import android.view.View;
import android.widget.FrameLayout;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class vb extends FrameLayout {
    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int iMin = (int) (Math.min(getMeasuredWidth(), getMeasuredHeight()) * 0.7f);
        View childAt = getChildAt(0);
        if (childAt != null) {
            childAt.measure(View.MeasureSpec.makeMeasureSpec(iMin, 1073741824), View.MeasureSpec.makeMeasureSpec(iMin, 1073741824));
        }
    }
}
