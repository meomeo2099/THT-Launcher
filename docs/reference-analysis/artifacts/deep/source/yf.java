package defpackage;

import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class yf implements Iterable, x9 {
    public final /* synthetic */ a4 a;

    public yf(a4 a4Var) {
        this.a = a4Var;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new z3(this.a);
    }
}
