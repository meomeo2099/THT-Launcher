package defpackage;

import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class y2 {
    public static final List a = j3.i0("airflow", "modes", "heating", "seats");

    public static List a() {
        return j3.i0(268894465, 268894467, 268894466, 268894470, 268894468);
    }

    public static List b() {
        return a;
    }

    public static List c(SharedPreferences sharedPreferences, String str, boolean z) {
        str.getClass();
        if (sharedPreferences.contains("climate_group_".concat(str))) {
            String string = sharedPreferences.getString("climate_group_".concat(str), "");
            List listD = th.D(string != null ? string : "", new char[]{','});
            ArrayList arrayList = new ArrayList();
            for (Object obj : listD) {
                if (!th.A((String) obj)) {
                    arrayList.add(obj);
                }
            }
            return arrayList;
        }
        int iHashCode = str.hashCode();
        if (iHashCode != -991967880) {
            if (iHashCode != 104069936) {
                if (iHashCode == 795788274 && str.equals("heating")) {
                    return j3.i0("rear", "wiper", "nozzle", "steering");
                }
            } else if (str.equals("modes")) {
                return i3.D0(j3.i0("auto", "ac", "recirc", "front"), z ? pi.J("sync") : u4.a);
            }
        } else if (str.equals("airflow")) {
            List listA = a();
            ArrayList arrayList2 = new ArrayList(k3.n0(listA));
            Iterator it = listA.iterator();
            while (it.hasNext()) {
                arrayList2.add("air:" + ((Number) it.next()).intValue());
            }
            return arrayList2;
        }
        List listI0 = j3.i0("seat_heat_1", "seat_vent_1", "seat_heat_4", "seat_vent_4");
        ArrayList arrayList3 = new ArrayList(k3.n0(listI0));
        Iterator it2 = listI0.iterator();
        while (it2.hasNext()) {
            arrayList3.add("seat:" + ((String) it2.next()));
        }
        return arrayList3;
    }

    public static void d(SharedPreferences sharedPreferences, String str, List list) {
        sharedPreferences.edit().putString("climate_group_".concat(str), i3.y0(list, ",", null, 62)).apply();
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0036  */
    public static String e(String str) {
        String str2;
        str.getClass();
        List list = si.a;
        int iHashCode = str.hashCode();
        if (iHashCode != -991967880) {
            if (iHashCode != 104069936) {
                if (iHashCode != 795788274) {
                    str2 = "seats";
                } else {
                    str2 = "heating";
                    if (!str.equals("heating")) {
                        str2 = "seats";
                    }
                }
            } else if (str.equals("modes")) {
                str2 = "climate_modes";
            } else {
                str2 = "seats";
            }
        } else if (str.equals("airflow")) {
            str2 = "airflow_direction_90";
        } else {
            str2 = "seats";
        }
        return si.a(str2, new Object[0]);
    }
}
