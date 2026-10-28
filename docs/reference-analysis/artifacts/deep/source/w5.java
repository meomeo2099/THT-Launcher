package defpackage;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class w5 {
    public static final List a = j3.i0("saver", "camera360", "theme", "stats");

    public static List a(Context context) {
        context.getClass();
        int i = 0;
        String string = context.getSharedPreferences("panel", 0).getString("folder_items", null);
        if (string == null) {
            return a;
        }
        List listD = th.D(string, new char[]{','});
        ArrayList arrayList = new ArrayList(k3.n0(listD));
        Iterator it = listD.iterator();
        while (it.hasNext()) {
            arrayList.add(th.J((String) it.next()).toString());
        }
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            if (((String) obj).length() > 0) {
                arrayList2.add(obj);
            }
        }
        return arrayList2;
    }

    public static void b(Context context, ArrayList arrayList) {
        context.getSharedPreferences("panel", 0).edit().putString("folder_items", i3.y0(i3.J0(i3.K0(arrayList)), ",", null, 62)).apply();
    }
}
