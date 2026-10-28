package defpackage;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class vf implements Iterator, t3, x9 {
    public int a;
    public Object b;
    public t3 c;

    @Override // defpackage.t3
    public final s4 a() {
        return s4.a;
    }

    @Override // defpackage.t3
    public final void b(Object obj) throws Throwable {
        k8.G0(obj);
        this.a = 4;
    }

    public final RuntimeException c() {
        int i = this.a;
        if (i == 4) {
            return new NoSuchElementException();
        }
        if (i == 5) {
            return new IllegalStateException("Iterator has failed.");
        }
        return new IllegalStateException("Unexpected state of the iterator: " + this.a);
    }

    public final void d(Object obj, qg qgVar) {
        this.b = obj;
        this.a = 3;
        this.c = qgVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i;
        while (true) {
            i = this.a;
            if (i != 0) {
                break;
            }
            this.a = 5;
            t3 t3Var = this.c;
            t3Var.getClass();
            this.c = null;
            t3Var.b(ti.a);
        }
        if (i == 1) {
            throw null;
        }
        if (i == 2 || i == 3) {
            return true;
        }
        if (i == 4) {
            return false;
        }
        throw c();
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i = this.a;
        if (i == 0 || i == 1) {
            if (hasNext()) {
                return next();
            }
            throw new NoSuchElementException();
        }
        if (i == 2) {
            this.a = 1;
            throw null;
        }
        if (i != 3) {
            throw c();
        }
        this.a = 0;
        Object obj = this.b;
        this.b = null;
        return obj;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
