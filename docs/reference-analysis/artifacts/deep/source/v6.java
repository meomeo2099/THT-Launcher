package defpackage;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class v6 implements Iterator, x9 {
    public Object a;
    public int b = -2;
    public final /* synthetic */ y0 c;

    public v6(y0 y0Var) {
        this.c = y0Var;
    }

    public final void a() {
        Object objB;
        if (this.b == -2) {
            objB = ((y1) this.c.b).a();
        } else {
            Object obj = this.a;
            obj.getClass();
            objB = ue.d.b(obj);
        }
        this.a = objB;
        this.b = objB == null ? 0 : 1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.b < 0) {
            a();
        }
        return this.b == 1;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.b < 0) {
            a();
        }
        if (this.b == 0) {
            throw new NoSuchElementException();
        }
        Object obj = this.a;
        obj.getClass();
        this.b = -1;
        return obj;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
