package defpackage;

import java.io.File;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class k5 implements uf {
    public final File a;
    public final l5 b;
    public final n6 c;

    public k5(File file, l5 l5Var, a aVar) {
        this.a = file;
        this.b = l5Var;
        this.c = aVar;
    }

    @Override // defpackage.uf
    public final Iterator iterator() {
        return new i5(this);
    }
}
