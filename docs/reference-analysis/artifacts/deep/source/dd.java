package defpackage;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class dd {
    public static final List a = j3.i0("com.android.bluetooth", "com.google.android.bluetooth", "bluetooth", "avrcp", "btmusic");

    public static boolean a(String str) {
        str.getClass();
        List list = a;
        if (list != null && list.isEmpty()) {
            return false;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (th.v(str, (String) it.next(), true)) {
                return true;
            }
        }
        return false;
    }

    public static boolean b(String str) {
        str.getClass();
        return a(str) || th.v(str, "radio", true) || str.startsWith("ecarx.") || str.startsWith("com.ecarx.") || th.v(str, "tuner", true);
    }

    public static String c(long j) {
        if (j < 0) {
            return "—:—";
        }
        long j2 = j / 1000;
        return j2 >= 3600 ? String.format(Locale.ROOT, "%d:%02d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(j2 / 3600), Long.valueOf((j2 / 60) % 60), Long.valueOf(j2 % 60)}, 3)) : String.format(Locale.ROOT, "%d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(j2 / 60), Long.valueOf(j2 % 60)}, 2));
    }
}
