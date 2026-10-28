package defpackage;

import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class f extends g implements RandomAccess {
    public final g a;
    public final int b;
    public final int c;

    public f(g gVar, int i, int i2) {
        this.a = gVar;
        this.b = i;
        k8.l(i, i2, gVar.a());
        this.c = i2 - i;
    }

    @Override // defpackage.g
    public final int a() {
        return this.c;
    }

    @Override // java.util.List
    public final Object get(int i) {
        int i2 = this.c;
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException(c.c(i, i2, "index: ", ", size: "));
        }
        return this.a.get(this.b + i);
    }

    @Override // defpackage.g, java.util.List
    public final List subList(int i, int i2) {
        k8.l(i, i2, this.c);
        int i3 = this.b;
        return new f(this.a, i + i3, i3 + i2);
    }
}
