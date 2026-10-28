package defpackage;

import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class o5 implements uf {
    public final uf a;
    public final boolean b;
    public final j6 c;

    public o5(uf ufVar, boolean z, j6 j6Var) {
        this.a = ufVar;
        this.b = z;
        this.c = j6Var;
    }

    @Override // defpackage.uf
    public final Iterator iterator() {
        return new n5(this);
    }
}
