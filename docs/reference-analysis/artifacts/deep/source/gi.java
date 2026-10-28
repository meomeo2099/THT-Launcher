package defpackage;

import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class gi implements uf, o4 {
    public final a4 a;

    public gi(a4 a4Var) {
        this.a = a4Var;
    }

    @Override // defpackage.uf
    public final Iterator iterator() {
        return new d(this);
    }

    @Override // defpackage.o4
    public final uf a() {
        return this;
    }
}
