package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class rg {
    public static Context b;
    public static y5 c;
    public static int d;
    public static j6 e;
    public static boolean f;
    public static Rect g;
    public static final Handler a = new Handler(Looper.getMainLooper());
    public static final p h = new p(1);

    public static void a(MainActivity mainActivity, y5 y5Var, int i, v1 v1Var) {
        b = mainActivity.getApplicationContext();
        c = y5Var;
        d = i;
        e = v1Var;
        f = false;
        g = null;
        Handler handler = a;
        p pVar = h;
        handler.removeCallbacks(pVar);
        handler.post(pVar);
    }

    public static void b() {
        b = null;
        c = null;
        e = null;
        f = false;
        g = null;
        a.removeCallbacks(h);
        ug.a();
    }
}
