package defpackage;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zj {
    public static final /* synthetic */ int a = 0;

    static {
        j3.i0(0, 25, 50, 75, 100);
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0028  */
    public static void a(View view, int i) {
        Drawable drawableMutate;
        View childAt;
        Drawable background = view.getBackground();
        if (background == null) {
            ViewGroup viewGroup = view instanceof ViewGroup ? (ViewGroup) view : null;
            if (viewGroup == null) {
                background = null;
            } else {
                if (viewGroup.getChildCount() <= 0) {
                    viewGroup = null;
                }
                if (viewGroup == null || (childAt = viewGroup.getChildAt(0)) == null) {
                    background = null;
                } else {
                    background = childAt.getBackground();
                }
            }
        }
        if (background != null && (drawableMutate = background.mutate()) != null) {
            drawableMutate.setAlpha(((100 - pi.s(i, 0, 100)) * 255) / 100);
        }
        yj yjVarB = b(view, 2);
        if (yjVarB != null) {
            yjVarB.setFaceAlpha(((100 - pi.s(i, 0, 100)) * 255) / 100);
        }
        view.invalidate();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static yj b(View view, int i) {
        if (view instanceof yj) {
            return (yj) view;
        }
        if (i <= 0 || !(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            childAt.getClass();
            yj yjVarB = b(childAt, i - 1);
            if (yjVarB != null) {
                return yjVarB;
            }
        }
        return null;
    }

    public static int c(SharedPreferences sharedPreferences, String str) {
        sharedPreferences.getClass();
        return pi.s(sharedPreferences.getInt(str, 0), 0, 100);
    }

    public static String d(String str) {
        str.getClass();
        return str.equals("ios_dock") ? "dock_transparency" : "widget_transparency_".concat(str);
    }
}
