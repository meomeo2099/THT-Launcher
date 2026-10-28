package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class hb extends pi {
    public static Object g0(Map map, Object obj) {
        map.getClass();
        Object obj2 = map.get(obj);
        if (obj2 != null || map.containsKey(obj)) {
            return obj2;
        }
        throw new NoSuchElementException("Key " + obj + " is missing in the map.");
    }

    public static LinkedHashMap h0(yc... ycVarArr) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(i0(ycVarArr.length));
        for (yc ycVar : ycVarArr) {
            linkedHashMap.put(ycVar.a, ycVar.b);
        }
        return linkedHashMap;
    }

    public static int i0(int i) {
        if (i < 0) {
            return i;
        }
        if (i < 3) {
            return i + 1;
        }
        if (i < 1073741824) {
            return (int) ((i / 0.75f) + 1.0f);
        }
        return Integer.MAX_VALUE;
    }

    public static Map j0(yc... ycVarArr) {
        if (ycVarArr.length <= 0) {
            return v4.a;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(i0(ycVarArr.length));
        for (yc ycVar : ycVarArr) {
            linkedHashMap.put(ycVar.a, ycVar.b);
        }
        return linkedHashMap;
    }

    public static final void k0(LinkedHashMap linkedHashMap, ArrayList arrayList) {
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            yc ycVar = (yc) obj;
            linkedHashMap.put(ycVar.a, ycVar.b);
        }
    }

    public static Map l0(ArrayList arrayList) {
        int size = arrayList.size();
        if (size == 0) {
            return v4.a;
        }
        if (size != 1) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(i0(arrayList.size()));
            k0(linkedHashMap, arrayList);
            return linkedHashMap;
        }
        yc ycVar = (yc) arrayList.get(0);
        ycVar.getClass();
        Map mapSingletonMap = Collections.singletonMap(ycVar.a, ycVar.b);
        mapSingletonMap.getClass();
        return mapSingletonMap;
    }
}
