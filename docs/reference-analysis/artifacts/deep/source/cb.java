package defpackage;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class cb implements Iterator, x9 {
    public final eb a;
    public int b;
    public int c;
    public int d;
    public final /* synthetic */ int e;

    public cb(eb ebVar, int i) {
        this.e = i;
        ebVar.getClass();
        this.a = ebVar;
        this.c = -1;
        this.d = ebVar.h;
        b();
    }

    public final void a() {
        if (this.a.h != this.d) {
            throw new ConcurrentModificationException();
        }
    }

    public final void b() {
        while (true) {
            int i = this.b;
            eb ebVar = this.a;
            if (i >= ebVar.f || ebVar.c[i] >= 0) {
                return;
            } else {
                this.b = i + 1;
            }
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.b < this.a.f;
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i = this.e;
        eb ebVar = this.a;
        switch (i) {
            case 0:
                a();
                int i2 = this.b;
                if (i2 >= ebVar.f) {
                    throw new NoSuchElementException();
                }
                this.b = i2 + 1;
                this.c = i2;
                db dbVar = new db(ebVar, i2);
                b();
                return dbVar;
            case 1:
                a();
                int i3 = this.b;
                if (i3 >= ebVar.f) {
                    throw new NoSuchElementException();
                }
                this.b = i3 + 1;
                this.c = i3;
                Object obj = ebVar.a[i3];
                b();
                return obj;
            default:
                a();
                int i4 = this.b;
                if (i4 >= ebVar.f) {
                    throw new NoSuchElementException();
                }
                this.b = i4 + 1;
                this.c = i4;
                Object[] objArr = ebVar.b;
                objArr.getClass();
                Object obj2 = objArr[this.c];
                b();
                return obj2;
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        a();
        if (this.c == -1) {
            throw new IllegalStateException("Call next() before removing element from the iterator.");
        }
        eb ebVar = this.a;
        ebVar.b();
        ebVar.j(this.c);
        this.c = -1;
        this.d = ebVar.h;
    }
}
