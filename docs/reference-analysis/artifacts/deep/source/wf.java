package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class wf extends xf {
    public static o5 Q0(uf ufVar, j6 j6Var) {
        return new o5(new a4(ufVar, j6Var, 2), false, new w(28));
    }

    public static List R0(uf ufVar) {
        Iterator it = ufVar.iterator();
        if (!it.hasNext()) {
            return u4.a;
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return pi.J(next);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(next);
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }
}
