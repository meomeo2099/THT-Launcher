package defpackage;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class l8 {
    public static final int a = Color.rgb(48, 209, 88);
    public static final int b = Color.rgb(255, 69, 58);

    public static int a(int i, boolean z) {
        if (!z) {
            h();
            if (i == -10233877) {
                return Color.rgb(0, 91, 125);
            }
            if (i == f()) {
                return Color.rgb(0, 81, 181);
            }
            if (i == a || i == g()) {
                return Color.rgb(12, 95, 48);
            }
            i();
            if (i == -16777) {
                return Color.rgb(145, 65, 12);
            }
            if (i == b || i == Color.rgb(255, 137, 117)) {
                return Color.rgb(163, 30, 27);
            }
        }
        return i;
    }

    public static int b(boolean z, boolean z2, int i) {
        if (!z2 || z) {
            return z2 ? a(i, z) : j(z);
        }
        return -1;
    }

    public static String c() {
        List list = si.a;
        String str = new SimpleDateFormat("EEEE, d MMMM", si.b.b).format(new Date());
        str.getClass();
        if (str.length() <= 0) {
            return str;
        }
        return ((Object) pi.Z(str.charAt(0), si.b.b)) + str.substring(1);
    }

    public static int d(boolean z) {
        return z ? Color.argb(26, 255, 255, 255) : Color.argb(18, 15, 30, 60);
    }

    public static int e(boolean z) {
        return z ? Color.argb(41, 255, 255, 255) : Color.argb(31, 15, 30, 60);
    }

    public static int f() {
        return Color.rgb(10, 132, 255);
    }

    public static int g() {
        return Color.rgb(84, 206, 120);
    }

    public static int h() {
        ea.a.getClass();
        return -10233877;
    }

    public static int i() {
        ea.a.getClass();
        return -16777;
    }

    public static int j(boolean z) {
        int i;
        int i2;
        int i3;
        if (z) {
            i = 235;
            i2 = 240;
            i3 = 232;
        } else {
            i = 52;
            i2 = 59;
            i3 = 48;
        }
        return Color.rgb(i3, i, i2);
    }

    public static int k(boolean z) {
        return z ? Color.argb(158, 235, 240, 250) : Color.rgb(48, 62, 80);
    }

    public static int l(int i, int i2) {
        return Color.argb(i2, Color.red(i), Color.green(i), Color.blue(i));
    }

    public static float m(Context context) {
        context.getClass();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.min(Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels) / 1280.0f, Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) / 720.0f);
    }
}
