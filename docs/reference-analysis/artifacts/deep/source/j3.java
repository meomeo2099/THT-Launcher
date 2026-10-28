package defpackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class j3 extends pi {
    public static j8 g0(Collection collection) {
        return new j8(0, collection.size() - 1, 1);
    }

    public static int h0(List list) {
        list.getClass();
        return list.size() - 1;
    }

    public static List i0(Object... objArr) {
        if (objArr.length <= 0) {
            return u4.a;
        }
        List listAsList = Arrays.asList(objArr);
        listAsList.getClass();
        return listAsList;
    }

    public static ArrayList j0(Object... objArr) {
        return objArr.length == 0 ? new ArrayList() : new ArrayList(new v0(objArr));
    }

    public static final List k0(List list) {
        int size = list.size();
        if (size != 0) {
            return size != 1 ? list : pi.J(list.get(0));
        }
        return u4.a;
    }

    public static void l0() {
        throw new ArithmeticException("Count overflow has happened.");
    }

    public static void m0() {
        throw new ArithmeticException("Index overflow has happened.");
    }
}
