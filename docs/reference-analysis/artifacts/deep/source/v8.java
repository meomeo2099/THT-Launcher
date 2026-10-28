package defpackage;

import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import java.util.LinkedHashSet;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class v8 extends HorizontalScrollView {
    public final boolean a;
    public final LinkedHashSet b;
    public int c;
    public final u8 d;
    public j6 e;

    public v8(MainActivity mainActivity, boolean z, boolean z2) {
        super(mainActivity);
        this.a = z2;
        this.b = new LinkedHashSet();
        u8 u8Var = new u8(mainActivity, this);
        this.d = u8Var;
        setLayoutDirection(0);
        setTextDirection(5);
        setHorizontalScrollBarEnabled(false);
        setOverScrollMode(2);
        setFillViewport(true);
        setBackground(new b7(z, l8.m(mainActivity) * 28.0f, false));
        u8Var.setOnLongClickListener(new l4(this, 2));
        addView(u8Var, new FrameLayout.LayoutParams(-2, -1));
    }

    public final j6 getOnScroll() {
        return this.e;
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        motionEvent.getClass();
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        this.c = View.MeasureSpec.getSize(i);
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    public final void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        j6 j6Var = this.e;
        if (j6Var != null) {
            j6Var.b(Integer.valueOf(i));
        }
        boolean z = x5.a;
        if (x5.a) {
            x5.c(this.d);
        }
    }

    public final void setOnScroll(j6 j6Var) {
        this.e = j6Var;
    }
}
