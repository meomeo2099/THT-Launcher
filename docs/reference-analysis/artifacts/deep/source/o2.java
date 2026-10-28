package defpackage;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.reference.panel.Guard;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class o2 {
    public static final int a;
    public static final int b;
    public static final int c;
    public static final int d;
    public static final List e;
    public static final int f;
    public static final int g;
    public static final int h;
    public static final int i;
    public static final int j;
    public static final int k;
    public static final int l;
    public static final int m;
    public static final int n;
    public static final LinkedHashMap o;

    static {
        Guard guard = Guard.INSTANCE;
        a = guard.id(0);
        b = guard.id(1);
        c = guard.id(2);
        d = guard.id(3);
        e = j3.i0(0, Integer.valueOf(guard.id(4)), Integer.valueOf(guard.id(5)), Integer.valueOf(guard.id(6)));
        guard.id(7);
        guard.id(8);
        int iId = guard.id(9);
        f = iId;
        int iId2 = guard.id(10);
        g = iId2;
        int iId3 = guard.id(11);
        h = iId3;
        int iId4 = guard.id(12);
        i = iId4;
        int iId5 = guard.id(13);
        j = iId5;
        int iId6 = guard.id(14);
        k = iId6;
        int iId7 = guard.id(15);
        l = iId7;
        int iId8 = guard.id(16);
        m = iId8;
        n = guard.id(17);
        guard.id(18);
        guard.id(19);
        o = hb.h0(new yc("ac_eco", Integer.valueOf(iId)), new yc("defrost_max", 268697856), new yc("air_clean", Integer.valueOf(iId3)), new yc("ac_reminders", Integer.valueOf(iId2)), new yc("esc_sport", Integer.valueOf(iId4)), new yc("g_pilot", 671548416), new yc("lane_keep", Integer.valueOf(iId5)), new yc("park_assist", Integer.valueOf(iId6)), new yc("eco_hints", Integer.valueOf(iId7)), new yc("passenger_screen", Integer.valueOf(iId8)));
    }

    public static LinkedHashMap a() {
        return hb.h0(new yc(Integer.valueOf(f), si.a("eco_ac", new Object[0])), new yc(Integer.valueOf(g), si.a("climate_reminders", new Object[0])), new yc(Integer.valueOf(h), si.a("air_purification", new Object[0])), new yc(Integer.valueOf(i), "ESC Sport"), new yc(671548416, "G-Pilot"), new yc(Integer.valueOf(j), si.a("lane_keeping", new Object[0])), new yc(Integer.valueOf(k), si.a("parking_assist", new Object[0])), new yc(Integer.valueOf(l), si.a("eco_tips", new Object[0])), new yc(Integer.valueOf(m), si.a("passenger_display", new Object[0])), new yc(268697856, si.a("windshield_defogging", new Object[0])));
    }

    public static Map b() {
        LinkedHashMap linkedHashMapH0 = hb.h0(new yc("trunk", si.a("tailgate", new Object[0])), new yc("mirrors", si.a("mirrors", new Object[0])), new yc("ext_light", si.a("exterior_lights", new Object[0])), new yc("wipers", si.a("wiper_service_position", new Object[0])));
        LinkedHashMap linkedHashMap = o;
        ArrayList arrayList = new ArrayList(linkedHashMap.size());
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) a().get(Integer.valueOf(((Number) entry.getValue()).intValue()));
            if (str2 == null) {
                str2 = str;
            }
            arrayList.add(new yc(str, str2));
        }
        if (linkedHashMapH0.isEmpty()) {
            return hb.l0(arrayList);
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(linkedHashMapH0);
        hb.k0(linkedHashMap2, arrayList);
        return linkedHashMap2;
    }
}
