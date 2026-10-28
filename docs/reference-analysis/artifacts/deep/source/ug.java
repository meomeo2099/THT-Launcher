package defpackage;

import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class ug {
    public static tg a;
    public static WindowManager.LayoutParams b;
    public static Point c = new Point(0, 0);

    public static void a() {
        tg tgVar = a;
        if (tgVar == null) {
            return;
        }
        a = null;
        b = null;
        try {
            WindowManager windowManager = (WindowManager) tgVar.getContext().getSystemService(WindowManager.class);
            if (windowManager != null) {
                windowManager.removeView(tgVar);
            }
        } catch (Throwable unused) {
        }
    }

    public static void b(Context context, int i, int i2, int i3, int i4, a aVar) {
        Object ffVar;
        WindowManager windowManager = (WindowManager) context.getSystemService(WindowManager.class);
        if (windowManager == null) {
            return;
        }
        float fM = l8.m(context);
        int i5 = (int) (36.0f * fM);
        Point point = new Point();
        windowManager.getDefaultDisplay().getRealSize(point);
        tg tgVar = a;
        WindowManager.LayoutParams layoutParams = b;
        if (tgVar != null && layoutParams != null) {
            Point point2 = c;
            int i6 = (i - (i5 / 2)) + point2.x;
            int i7 = i2 + point2.y;
            if (tgVar.isPressed()) {
                return;
            }
            if (layoutParams.x == i6 && layoutParams.y == i7 && layoutParams.height == i3 - i2) {
                return;
            }
            layoutParams.x = i6;
            layoutParams.y = i7;
            layoutParams.height = i3 - i2;
            try {
                windowManager.updateViewLayout(tgVar, layoutParams);
                return;
            } catch (Throwable unused) {
                return;
            }
        }
        WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams(i5, i3 - i2, 2038, 776, -3);
        layoutParams2.gravity = 8388659;
        Point point3 = c;
        layoutParams2.x = (i - (i5 / 2)) + point3.x;
        layoutParams2.y = i2 + point3.y;
        layoutParams2.setTitle("ReferencePanel split divider");
        tg tgVar2 = new tg(context, fM, layoutParams2, i4, point, i5, aVar, windowManager);
        tgVar2.setAlpha(0.0f);
        try {
            windowManager.addView(tgVar2, layoutParams2);
            ffVar = ti.a;
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        if (ffVar instanceof ff) {
            return;
        }
        a = tgVar2;
        b = layoutParams2;
        tgVar2.getViewTreeObserver().addOnGlobalLayoutListener(new sg(tgVar2, i, i5, i2, layoutParams2, windowManager));
    }
}
