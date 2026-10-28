package defpackage;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class u8 extends ViewGroup {
    public final /* synthetic */ v8 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u8(MainActivity mainActivity, v8 v8Var) {
        super(mainActivity);
        this.a = v8Var;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7 = i4 - i2;
        int i8 = (int) (i7 * 0.1f);
        Iterator it = pi.f0(0, getChildCount()).iterator();
        int measuredWidth = 0;
        while (true) {
            i8 i8Var = (i8) it;
            if (!i8Var.c) {
                break;
            } else {
                measuredWidth += getChildAt(i8Var.nextInt()).getMeasuredWidth();
            }
        }
        if (!this.a.a || (i5 = ((i3 - i) - (i8 * 2)) - measuredWidth) < 0) {
            i5 = 0;
        }
        Iterable iterableF0 = pi.f0(0, getChildCount());
        if ((iterableF0 instanceof Collection) && ((Collection) iterableF0).isEmpty()) {
            i6 = 0;
        } else {
            Iterator it2 = iterableF0.iterator();
            i6 = 0;
            while (true) {
                i8 i8Var2 = (i8) it2;
                if (!i8Var2.c) {
                    break;
                }
                if (!(getChildAt(i8Var2.nextInt()) instanceof w8) && (i6 = i6 + 1) < 0) {
                    j3.l0();
                    throw null;
                }
            }
        }
        int childCount = getChildCount();
        int i9 = 0;
        int measuredWidth2 = 0;
        int i10 = 0;
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (!(childAt instanceof w8)) {
                i10 = i6 <= 1 ? i5 / 2 : (i5 * i9) / (i6 - 1);
                i9++;
            }
            int i12 = i8 + measuredWidth2 + i10;
            childAt.layout(i12, 0, childAt.getMeasuredWidth() + i12, i7);
            measuredWidth2 += childAt.getMeasuredWidth();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int i3;
        float f;
        int size = View.MeasureSpec.getSize(i2);
        float f2 = size;
        int i4 = (int) (0.1f * f2);
        j8 j8VarF0 = pi.f0(0, getChildCount());
        ArrayList arrayList = new ArrayList(k3.n0(j8VarF0));
        Iterator it = j8VarF0.iterator();
        while (true) {
            i8 i8Var = (i8) it;
            if (!i8Var.c) {
                break;
            } else {
                arrayList.add(getChildAt(i8Var.nextInt()));
            }
        }
        ArrayList arrayList2 = new ArrayList();
        int size2 = arrayList.size();
        int i5 = 0;
        while (i5 < size2) {
            Object obj = arrayList.get(i5);
            i5++;
            if (obj instanceof x8) {
                arrayList2.add(obj);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        int size3 = arrayList.size();
        int i6 = 0;
        while (i6 < size3) {
            Object obj2 = arrayList.get(i6);
            i6++;
            if (obj2 instanceof w8) {
                arrayList3.add(obj2);
            }
        }
        int size4 = arrayList2.size();
        int iA = 0;
        int i7 = 0;
        while (i7 < size4) {
            Object obj3 = arrayList2.get(i7);
            i7++;
            iA += ((x8) obj3).a(size) + ((int) (0.08f * f2));
        }
        if (arrayList.isEmpty()) {
            i3 = 0;
        } else {
            int size5 = arrayList.size();
            i3 = 0;
            int i8 = 0;
            while (i8 < size5) {
                Object obj4 = arrayList.get(i8);
                i8++;
                View view = (View) obj4;
                if (!(view instanceof x8) && !(view instanceof w8) && (i3 = i3 + 1) < 0) {
                    j3.l0();
                    throw null;
                }
            }
        }
        float f3 = i3;
        if (f3 < 1.0f) {
            f3 = 1.0f;
        }
        t8 t8Var = new t8(arrayList, size);
        float fMax = 1.05f * f2;
        Object objB = t8Var.b(Float.valueOf(fMax));
        Float f4 = (Float) (((Number) objB).floatValue() > 0.0f ? objB : null);
        int iFloatValue = (int) ((f4 != null ? f4.floatValue() : f2 * 0.64f) / 4.0f);
        int size6 = arrayList3.size() * iFloatValue;
        v8 v8Var = this.a;
        if (v8Var.a) {
            f = 2.0f;
        } else {
            int i9 = i4 * 2;
            f = 2.0f;
            fMax = Math.max(fMax, (((((Number) t8Var.b(Float.valueOf(Math.max(fMax, (((((Number) t8Var.b(Float.valueOf(fMax))).floatValue() * arrayList2.size()) / 2.0f) + (((v8Var.c - i9) - iA) - size6)) / ((arrayList2.size() * 0.5f) + f3))))).floatValue() * arrayList2.size()) / 2.0f) + (((v8Var.c - i9) - iA) - size6)) / ((arrayList2.size() * 0.5f) + f3));
        }
        float fFloatValue = ((Number) t8Var.b(Float.valueOf(fMax))).floatValue();
        float f5 = (fMax - fFloatValue) / f;
        float f6 = f5 >= 0.0f ? f5 : 0.0f;
        float f7 = fFloatValue + f6;
        int i10 = i4 * 2;
        int childCount = getChildCount();
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            int iA2 = childAt instanceof x8 ? ((x8) childAt).a(size) + ((int) f6) : childAt instanceof w8 ? iFloatValue : (int) f7;
            i10 += iA2;
            childAt.measure(View.MeasureSpec.makeMeasureSpec(iA2, 1073741824), View.MeasureSpec.makeMeasureSpec(size, 1073741824));
        }
        setMeasuredDimension(Math.max(i10, v8Var.c), size);
    }
}
