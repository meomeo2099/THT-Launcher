package defpackage;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class zf extends pi {
    public static LinkedHashSet g0(Set set, Object obj) {
        LinkedHashSet linkedHashSet = new LinkedHashSet(hb.i0(set.size()));
        boolean z = false;
        for (Object obj2 : set) {
            boolean z2 = true;
            if (!z && k8.e(obj2, obj)) {
                z = true;
                z2 = false;
            }
            if (z2) {
                linkedHashSet.add(obj2);
            }
        }
        return linkedHashSet;
    }

    public static LinkedHashSet h0(Set set, Iterable iterable) {
        int size;
        set.getClass();
        iterable.getClass();
        Integer numValueOf = iterable instanceof Collection ? Integer.valueOf(((Collection) iterable).size()) : null;
        if (numValueOf != null) {
            size = set.size() + numValueOf.intValue();
        } else {
            size = set.size() * 2;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(hb.i0(size));
        linkedHashSet.addAll(set);
        o3.p0(iterable, linkedHashSet);
        return linkedHashSet;
    }

    public static LinkedHashSet i0(Set set, Object obj) {
        LinkedHashSet linkedHashSet = new LinkedHashSet(hb.i0(set.size() + 1));
        linkedHashSet.addAll(set);
        linkedHashSet.add(obj);
        return linkedHashSet;
    }
}
