package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class e3 {
    public static final Set a;
    public static final Set b;
    public static final List c;
    public static final List d;

    static {
        List listI0 = j3.i0("clock_time_format", "clock_date_format", "clock_show", "clock_align");
        ArrayList arrayList = new ArrayList(k3.n0(listI0));
        Iterator it = listI0.iterator();
        while (it.hasNext()) {
            arrayList.add("wall_" + ((String) it.next()));
        }
        a = i3.L0(i3.D0(listI0, arrayList));
        b = x0.Y0(new String[]{"clock_backing", "wall_clock_backing"});
        c = j3.i0("HH:mm", "h:mm");
        d = j3.i0("EEEE, d MMMM", "d MMMM", "EE, d MMMM", "dd.MM.yy", "dd.MM.yyyy", "EEEE, dd.MM.yy", "EE, dd.MM.yy");
    }

    public static String a(String str) {
        Date date = new Date();
        str.getClass();
        List list = si.a;
        String str2 = new SimpleDateFormat(str, si.b.b).format(date);
        str2.getClass();
        if (str2.length() <= 0) {
            return str2;
        }
        return ((Object) pi.Z(str2.charAt(0), si.b.b)) + str2.substring(1);
    }

    public static String b(Context context, boolean z) {
        context.getClass();
        int iC = c(context, z, "clock_date_format");
        List list = d;
        return (String) ((iC < 0 || iC >= list.size()) ? (String) list.get(0) : list.get(iC));
    }

    public static int c(Context context, boolean z, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("panel", 0);
        return sharedPreferences.getInt(z ? "wall_".concat(str) : str, sharedPreferences.getInt(str, 0));
    }

    public static String d(String str) {
        Date date = new Date();
        str.getClass();
        String str2 = new SimpleDateFormat(str, Locale.getDefault()).format(date);
        str2.getClass();
        return str2;
    }

    public static String e(Context context, boolean z) {
        context.getClass();
        int iC = c(context, z, "clock_time_format");
        List list = c;
        return (String) ((iC < 0 || iC >= list.size()) ? (String) list.get(0) : list.get(iC));
    }
}
