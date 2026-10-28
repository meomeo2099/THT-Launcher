package defpackage;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class a4 implements uf {
    public final /* synthetic */ int a;
    public final Object b;
    public final Object c;

    public a4(CharSequence charSequence, a aVar) {
        this.a = 0;
        charSequence.getClass();
        this.b = charSequence;
        this.c = aVar;
    }

    @Override // defpackage.uf
    public final Iterator iterator() {
        switch (this.a) {
            case 0:
                return new z3(this);
            case 1:
                o5 o5Var = (o5) this.b;
                ArrayList arrayList = new ArrayList();
                n5 n5Var = new n5(o5Var);
                while (n5Var.hasNext()) {
                    arrayList.add(n5Var.next());
                }
                n3.o0(arrayList, (r3) this.c);
                return arrayList.iterator();
            default:
                return new ki(this);
        }
    }

    public /* synthetic */ a4(uf ufVar, Object obj, int i) {
        this.a = i;
        this.b = ufVar;
        this.c = obj;
    }
}
