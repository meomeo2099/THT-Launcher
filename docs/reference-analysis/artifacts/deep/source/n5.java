package defpackage;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class n5 implements Iterator, x9 {
    public final Iterator a;
    public int b = -1;
    public Object c;
    public final /* synthetic */ o5 d;

    public n5(o5 o5Var) {
        this.d = o5Var;
        this.a = o5Var.a.iterator();
    }

    public final void a() {
        Object next;
        o5 o5Var;
        do {
            Iterator it = this.a;
            if (!it.hasNext()) {
                this.b = 0;
                return;
            } else {
                next = it.next();
                o5Var = this.d;
            }
        } while (((Boolean) o5Var.c.b(next)).booleanValue() != o5Var.b);
        this.c = next;
        this.b = 1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.b == -1) {
            a();
        }
        return this.b == 1;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.b == -1) {
            a();
        }
        if (this.b == 0) {
            throw new NoSuchElementException();
        }
        Object obj = this.c;
        this.c = null;
        this.b = -1;
        return obj;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
