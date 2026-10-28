package defpackage;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class s3 implements uf {
    public final AtomicReference a;

    public s3(uf ufVar) {
        this.a = new AtomicReference(ufVar);
    }

    @Override // defpackage.uf
    public final Iterator iterator() {
        uf ufVar = (uf) this.a.getAndSet(null);
        if (ufVar != null) {
            return ufVar.iterator();
        }
        throw new IllegalStateException("This sequence can be consumed only once.");
    }
}
