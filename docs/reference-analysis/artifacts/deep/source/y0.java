package defpackage;

import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class y0 implements uf {
    public final /* synthetic */ int a;
    public final Object b;

    public y0(y1 y1Var) {
        this.a = 2;
        this.b = y1Var;
    }

    @Override // defpackage.uf
    public final Iterator iterator() {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                return new d(1, (Object[]) obj);
            case 1:
                return ((List) obj).iterator();
            case 2:
                return new v6(this);
            case 3:
                return new ga(this);
            case 4:
                return (Iterator) obj;
            default:
                return new fa((String) obj);
        }
    }

    public /* synthetic */ y0(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }
}
