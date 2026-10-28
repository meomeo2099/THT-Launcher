package defpackage;

import java.util.AbstractList;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class ha implements ListIterator, x9 {
    public int b;
    public int d;
    public final h e;
    public final /* synthetic */ int a = 0;
    public int c = -1;

    public ha(ja jaVar, int i) {
        this.e = jaVar;
        this.b = i;
        this.d = ((AbstractList) jaVar).modCount;
    }

    public void a() {
        if (((AbstractList) ((ia) this.e).e).modCount != this.d) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        int i = this.a;
        h hVar = this.e;
        switch (i) {
            case 0:
                a();
                ia iaVar = (ia) hVar;
                int i2 = this.b;
                this.b = i2 + 1;
                iaVar.add(i2, obj);
                this.c = -1;
                this.d = ((AbstractList) iaVar).modCount;
                break;
            default:
                b();
                ja jaVar = (ja) hVar;
                int i3 = this.b;
                this.b = i3 + 1;
                jaVar.add(i3, obj);
                this.c = -1;
                this.d = ((AbstractList) jaVar).modCount;
                break;
        }
    }

    public void b() {
        if (((AbstractList) ((ja) this.e)).modCount != this.d) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        int i = this.a;
        h hVar = this.e;
        switch (i) {
            case 0:
                return this.b < ((ia) hVar).c;
            default:
                return this.b < ((ja) hVar).b;
        }
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        switch (this.a) {
            case 0:
                return this.b > 0;
            default:
                return this.b > 0;
        }
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        int i = this.a;
        h hVar = this.e;
        switch (i) {
            case 0:
                a();
                int i2 = this.b;
                ia iaVar = (ia) hVar;
                if (i2 >= iaVar.c) {
                    throw new NoSuchElementException();
                }
                this.b = i2 + 1;
                this.c = i2;
                return iaVar.a[iaVar.b + i2];
            default:
                b();
                int i3 = this.b;
                ja jaVar = (ja) hVar;
                if (i3 >= jaVar.b) {
                    throw new NoSuchElementException();
                }
                this.b = i3 + 1;
                this.c = i3;
                return jaVar.a[i3];
        }
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        switch (this.a) {
            case 0:
                break;
        }
        return this.b;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        int i = this.a;
        h hVar = this.e;
        switch (i) {
            case 0:
                a();
                int i2 = this.b;
                if (i2 <= 0) {
                    throw new NoSuchElementException();
                }
                int i3 = i2 - 1;
                this.b = i3;
                this.c = i3;
                ia iaVar = (ia) hVar;
                return iaVar.a[iaVar.b + i3];
            default:
                b();
                int i4 = this.b;
                if (i4 <= 0) {
                    throw new NoSuchElementException();
                }
                int i5 = i4 - 1;
                this.b = i5;
                this.c = i5;
                return ((ja) hVar).a[i5];
        }
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        int i;
        switch (this.a) {
            case 0:
                i = this.b;
                break;
            default:
                i = this.b;
                break;
        }
        return i - 1;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        int i = this.a;
        h hVar = this.e;
        switch (i) {
            case 0:
                ia iaVar = (ia) hVar;
                a();
                int i2 = this.c;
                if (i2 == -1) {
                    throw new IllegalStateException("Call next() or previous() before removing element from the iterator.");
                }
                iaVar.b(i2);
                this.b = this.c;
                this.c = -1;
                this.d = ((AbstractList) iaVar).modCount;
                return;
            default:
                ja jaVar = (ja) hVar;
                b();
                int i3 = this.c;
                if (i3 == -1) {
                    throw new IllegalStateException("Call next() or previous() before removing element from the iterator.");
                }
                jaVar.b(i3);
                this.b = this.c;
                this.c = -1;
                this.d = ((AbstractList) jaVar).modCount;
                return;
        }
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        int i = this.a;
        h hVar = this.e;
        switch (i) {
            case 0:
                a();
                int i2 = this.c;
                if (i2 == -1) {
                    throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.");
                }
                ((ia) hVar).set(i2, obj);
                return;
            default:
                b();
                int i3 = this.c;
                if (i3 == -1) {
                    throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.");
                }
                ((ja) hVar).set(i3, obj);
                return;
        }
    }

    public ha(ia iaVar, int i) {
        this.e = iaVar;
        this.b = i;
        this.d = ((AbstractList) iaVar).modCount;
    }
}
