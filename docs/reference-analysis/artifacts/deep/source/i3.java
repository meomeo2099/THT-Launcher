package defpackage;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class i3 extends o3 {
    public static Double A0(ArrayList arrayList) {
        Iterator it = arrayList.iterator();
        if (!it.hasNext()) {
            return null;
        }
        double dDoubleValue = ((Number) it.next()).doubleValue();
        while (it.hasNext()) {
            dDoubleValue = Math.max(dDoubleValue, ((Number) it.next()).doubleValue());
        }
        return Double.valueOf(dDoubleValue);
    }

    public static Double B0(ArrayList arrayList) {
        Iterator it = arrayList.iterator();
        if (!it.hasNext()) {
            return null;
        }
        double dDoubleValue = ((Number) it.next()).doubleValue();
        while (it.hasNext()) {
            dDoubleValue = Math.min(dDoubleValue, ((Number) it.next()).doubleValue());
        }
        return Double.valueOf(dDoubleValue);
    }

    public static ArrayList C0(List list, Object obj) {
        list.getClass();
        ArrayList arrayList = new ArrayList(k3.n0(list));
        boolean z = false;
        for (Object obj2 : list) {
            boolean z2 = true;
            if (!z && k8.e(obj2, obj)) {
                z = true;
                z2 = false;
            }
            if (z2) {
                arrayList.add(obj2);
            }
        }
        return arrayList;
    }

    public static ArrayList D0(List list, Iterable iterable) {
        list.getClass();
        iterable.getClass();
        if (!(iterable instanceof Collection)) {
            ArrayList arrayList = new ArrayList(list);
            o3.p0(iterable, arrayList);
            return arrayList;
        }
        Collection collection = (Collection) iterable;
        ArrayList arrayList2 = new ArrayList(collection.size() + list.size());
        arrayList2.addAll(list);
        arrayList2.addAll(collection);
        return arrayList2;
    }

    public static ArrayList E0(List list, Object obj) {
        list.getClass();
        ArrayList arrayList = new ArrayList(list.size() + 1);
        arrayList.addAll(list);
        arrayList.add(obj);
        return arrayList;
    }

    public static List F0(Collection collection) {
        collection.getClass();
        if (collection.size() <= 1) {
            return J0(collection);
        }
        Object[] array = collection.toArray(new Comparable[0]);
        Comparable[] comparableArr = (Comparable[]) array;
        comparableArr.getClass();
        if (comparableArr.length > 1) {
            Arrays.sort(comparableArr);
        }
        array.getClass();
        List listAsList = Arrays.asList(array);
        listAsList.getClass();
        return listAsList;
    }

    public static List G0(Collection collection, Comparator comparator) {
        if (collection.size() <= 1) {
            return J0(collection);
        }
        Object[] array = collection.toArray(new Object[0]);
        array.getClass();
        if (array.length > 1) {
            Arrays.sort(array, comparator);
        }
        List listAsList = Arrays.asList(array);
        listAsList.getClass();
        return listAsList;
    }

    public static List H0(List list, int i) {
        list.getClass();
        if (i < 0) {
            fg.a(i, "Requested element count ");
            return null;
        }
        if (i == 0) {
            return u4.a;
        }
        if (i >= list.size()) {
            return J0(list);
        }
        if (i == 1) {
            return pi.J(u0(list));
        }
        ArrayList arrayList = new ArrayList(i);
        Iterator it = list.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            arrayList.add(it.next());
            i2++;
            if (i2 == i) {
                break;
            }
        }
        return j3.k0(arrayList);
    }

    public static final void I0(Iterable iterable, AbstractCollection abstractCollection) {
        iterable.getClass();
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            abstractCollection.add(it.next());
        }
    }

    public static List J0(Iterable iterable) {
        ArrayList arrayList;
        iterable.getClass();
        if (!(iterable instanceof Collection)) {
            iterable.getClass();
            if (iterable instanceof Collection) {
                arrayList = new ArrayList((Collection) iterable);
            } else {
                arrayList = new ArrayList();
                I0(iterable, arrayList);
            }
            return j3.k0(arrayList);
        }
        Collection collection = (Collection) iterable;
        int size = collection.size();
        if (size == 0) {
            return u4.a;
        }
        if (size != 1) {
            return new ArrayList(collection);
        }
        return pi.J(iterable instanceof List ? ((List) iterable).get(0) : collection.iterator().next());
    }

    public static Set K0(Iterable iterable) {
        iterable.getClass();
        if (iterable instanceof Collection) {
            return new LinkedHashSet((Collection) iterable);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        I0(iterable, linkedHashSet);
        return linkedHashSet;
    }

    public static Set L0(Iterable iterable) {
        iterable.getClass();
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            int size = collection.size();
            if (size != 0) {
                if (size == 1) {
                    return pi.P(iterable instanceof List ? ((List) iterable).get(0) : collection.iterator().next());
                }
                LinkedHashSet linkedHashSet = new LinkedHashSet(hb.i0(collection.size()));
                I0(iterable, linkedHashSet);
                return linkedHashSet;
            }
        } else {
            LinkedHashSet linkedHashSet2 = new LinkedHashSet();
            I0(iterable, linkedHashSet2);
            int size2 = linkedHashSet2.size();
            if (size2 != 0) {
                return size2 != 1 ? linkedHashSet2 : pi.P(linkedHashSet2.iterator().next());
            }
        }
        return x4.a;
    }

    public static ArrayList q0(List list) {
        Iterator it;
        pi.n(3, 3);
        if (!(list instanceof RandomAccess)) {
            ArrayList arrayList = new ArrayList();
            Iterator it2 = list.iterator();
            it2.getClass();
            if (it2.hasNext()) {
                vf vfVar = new vf();
                qg qgVar = new qg(it2, vfVar);
                qgVar.g = vfVar;
                vfVar.c = qgVar;
                it = vfVar;
            } else {
                it = t4.a;
            }
            while (it.hasNext()) {
                arrayList.add((List) it.next());
            }
            return arrayList;
        }
        int size = list.size();
        ArrayList arrayList2 = new ArrayList((size / 3) + (size % 3 == 0 ? 0 : 1));
        for (int i = 0; i >= 0 && i < size; i += 3) {
            int i2 = size - i;
            if (3 <= i2) {
                i2 = 3;
            }
            ArrayList arrayList3 = new ArrayList(i2);
            for (int i3 = 0; i3 < i2; i3++) {
                arrayList3.add(list.get(i3 + i));
            }
            arrayList2.add(arrayList3);
        }
        return arrayList2;
    }

    public static boolean r0(Iterable iterable, Object obj) {
        int iIndexOf;
        iterable.getClass();
        if (iterable instanceof Collection) {
            return ((Collection) iterable).contains(obj);
        }
        if (iterable instanceof List) {
            iIndexOf = ((List) iterable).indexOf(obj);
        } else {
            int i = 0;
            for (Object obj2 : iterable) {
                if (i < 0) {
                    j3.m0();
                    throw null;
                }
                if (k8.e(obj, obj2)) {
                    iIndexOf = i;
                } else {
                    i++;
                }
            }
            iIndexOf = -1;
        }
        return iIndexOf >= 0;
    }

    public static List s0(Iterable iterable, int i) {
        ArrayList arrayList;
        Object objZ0;
        iterable.getClass();
        if (i < 0) {
            fg.a(i, "Requested element count ");
            return null;
        }
        if (i == 0) {
            return J0(iterable);
        }
        if (iterable instanceof Collection) {
            int size = ((Collection) iterable).size() - i;
            if (size <= 0) {
                return u4.a;
            }
            if (size == 1) {
                if (iterable instanceof List) {
                    objZ0 = z0((List) iterable);
                } else {
                    Iterator it = iterable.iterator();
                    if (!it.hasNext()) {
                        throw new NoSuchElementException("Collection is empty.");
                    }
                    Object next = it.next();
                    while (it.hasNext()) {
                        next = it.next();
                    }
                    objZ0 = next;
                }
                return pi.J(objZ0);
            }
            arrayList = new ArrayList(size);
            if (iterable instanceof List) {
                if (iterable instanceof RandomAccess) {
                    List list = (List) iterable;
                    int size2 = list.size();
                    while (i < size2) {
                        arrayList.add(list.get(i));
                        i++;
                    }
                } else {
                    ListIterator listIterator = ((List) iterable).listIterator(i);
                    while (listIterator.hasNext()) {
                        arrayList.add(listIterator.next());
                    }
                }
                return arrayList;
            }
        } else {
            arrayList = new ArrayList();
        }
        int i2 = 0;
        for (Object obj : iterable) {
            if (i2 >= i) {
                arrayList.add(obj);
            } else {
                i2++;
            }
        }
        return j3.k0(arrayList);
    }

    public static ArrayList t0(List list) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static Object u0(List list) {
        list.getClass();
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list.get(0);
    }

    public static Object v0(ArrayList arrayList) {
        arrayList.getClass();
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList.get(0);
    }

    public static Object w0(List list, int i) {
        list.getClass();
        if (i < 0 || i >= list.size()) {
            return null;
        }
        return list.get(i);
    }

    public static final void x0(Iterable iterable, StringBuilder sb, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4, j6 j6Var) {
        iterable.getClass();
        sb.append(charSequence2);
        int i = 0;
        for (Object obj : iterable) {
            i++;
            if (i > 1) {
                sb.append(charSequence);
            }
            uh.b(sb, obj, j6Var);
        }
        sb.append(charSequence3);
    }

    public static String y0(Iterable iterable, String str, j6 j6Var, int i) {
        if ((i & 1) != 0) {
            str = ", ";
        }
        String str2 = str;
        CharSequence charSequence = (i & 2) != 0 ? "" : "[";
        String str3 = (i & 4) == 0 ? "]" : "";
        if ((i & 32) != 0) {
            j6Var = null;
        }
        iterable.getClass();
        StringBuilder sb = new StringBuilder();
        x0(iterable, sb, str2, charSequence, str3, "...", j6Var);
        return sb.toString();
    }

    public static Object z0(List list) {
        list.getClass();
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list.get(list.size() - 1);
    }
}
