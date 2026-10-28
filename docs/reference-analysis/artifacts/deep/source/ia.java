package defpackage;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class ia extends h implements RandomAccess, Serializable {
    public Object[] a;
    public final int b;
    public int c;
    public final ia d;
    public final ja e;

    public ia(Object[] objArr, int i, int i2, ia iaVar, ja jaVar) {
        objArr.getClass();
        jaVar.getClass();
        this.a = objArr;
        this.b = i;
        this.c = i2;
        this.d = iaVar;
        this.e = jaVar;
        ((AbstractList) this).modCount = ((AbstractList) jaVar).modCount;
    }

    @Override // defpackage.h
    public final int a() {
        f();
        return this.c;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        g();
        f();
        int i2 = this.c;
        if (i < 0 || i > i2) {
            throw new IndexOutOfBoundsException(c.c(i, i2, "index: ", ", size: "));
        }
        e(this.b + i, obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection collection) {
        collection.getClass();
        g();
        f();
        int i2 = this.c;
        if (i < 0 || i > i2) {
            throw new IndexOutOfBoundsException(c.c(i, i2, "index: ", ", size: "));
        }
        int size = collection.size();
        d(this.b + i, collection, size);
        return size > 0;
    }

    @Override // defpackage.h
    public final Object b(int i) {
        g();
        f();
        int i2 = this.c;
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException(c.c(i, i2, "index: ", ", size: "));
        }
        return h(this.b + i);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        g();
        f();
        i(this.b, this.c);
    }

    public final void d(int i, Collection collection, int i2) {
        ((AbstractList) this).modCount++;
        ja jaVar = this.e;
        ia iaVar = this.d;
        if (iaVar != null) {
            iaVar.d(i, collection, i2);
        } else {
            ja jaVar2 = ja.d;
            jaVar.d(i, collection, i2);
        }
        this.a = jaVar.a;
        this.c += i2;
    }

    public final void e(int i, Object obj) {
        ((AbstractList) this).modCount++;
        ja jaVar = this.e;
        ia iaVar = this.d;
        if (iaVar != null) {
            iaVar.e(i, obj);
        } else {
            ja jaVar2 = ja.d;
            jaVar.e(i, obj);
        }
        this.a = jaVar.a;
        this.c++;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        f();
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            Object[] objArr = this.a;
            int i = this.c;
            if (i == list.size()) {
                for (int i2 = 0; i2 < i; i2++) {
                    if (k8.e(objArr[this.b + i2], list.get(i2))) {
                    }
                }
                return true;
            }
        }
        return false;
    }

    public final void f() {
        if (((AbstractList) this.e).modCount != ((AbstractList) this).modCount) {
            throw new ConcurrentModificationException();
        }
    }

    public final void g() {
        if (this.e.c) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        f();
        int i2 = this.c;
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException(c.c(i, i2, "index: ", ", size: "));
        }
        return this.a[this.b + i];
    }

    public final Object h(int i) {
        Object objH;
        ((AbstractList) this).modCount++;
        ia iaVar = this.d;
        if (iaVar != null) {
            objH = iaVar.h(i);
        } else {
            ja jaVar = ja.d;
            objH = this.e.h(i);
        }
        this.c--;
        return objH;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        f();
        Object[] objArr = this.a;
        int i = this.c;
        int iHashCode = 1;
        for (int i2 = 0; i2 < i; i2++) {
            Object obj = objArr[this.b + i2];
            iHashCode = (iHashCode * 31) + (obj != null ? obj.hashCode() : 0);
        }
        return iHashCode;
    }

    public final void i(int i, int i2) {
        if (i2 > 0) {
            ((AbstractList) this).modCount++;
        }
        ia iaVar = this.d;
        if (iaVar != null) {
            iaVar.i(i, i2);
        } else {
            ja jaVar = ja.d;
            this.e.i(i, i2);
        }
        this.c -= i2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        f();
        for (int i = 0; i < this.c; i++) {
            if (k8.e(this.a[this.b + i], obj)) {
                return i;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        f();
        return this.c == 0;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return listIterator(0);
    }

    public final int j(int i, int i2, Collection collection, boolean z) {
        int iJ;
        ia iaVar = this.d;
        if (iaVar != null) {
            iJ = iaVar.j(i, i2, collection, z);
        } else {
            ja jaVar = ja.d;
            iJ = this.e.j(i, i2, collection, z);
        }
        if (iJ > 0) {
            ((AbstractList) this).modCount++;
        }
        this.c -= iJ;
        return iJ;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        f();
        for (int i = this.c - 1; i >= 0; i--) {
            if (k8.e(this.a[this.b + i], obj)) {
                return i;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i) {
        f();
        int i2 = this.c;
        if (i < 0 || i > i2) {
            throw new IndexOutOfBoundsException(c.c(i, i2, "index: ", ", size: "));
        }
        return new ha(this, i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        g();
        f();
        int iIndexOf = indexOf(obj);
        if (iIndexOf >= 0) {
            b(iIndexOf);
        }
        return iIndexOf >= 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        collection.getClass();
        g();
        f();
        return j(this.b, this.c, collection, false) > 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection collection) {
        collection.getClass();
        g();
        f();
        return j(this.b, this.c, collection, true) > 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        g();
        f();
        int i2 = this.c;
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException(c.c(i, i2, "index: ", ", size: "));
        }
        Object[] objArr = this.a;
        int i3 = this.b;
        Object obj2 = objArr[i3 + i];
        objArr[i3 + i] = obj;
        return obj2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final List subList(int i, int i2) {
        k8.l(i, i2, this.c);
        return new ia(this.a, this.b + i, i2 - i, this, this.e);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray(Object[] objArr) {
        objArr.getClass();
        f();
        int length = objArr.length;
        int i = this.c;
        Object[] objArr2 = this.a;
        int i2 = this.b;
        if (length < i) {
            Object[] objArrCopyOfRange = Arrays.copyOfRange(objArr2, i2, i + i2, objArr.getClass());
            objArrCopyOfRange.getClass();
            return objArrCopyOfRange;
        }
        x0.Q0(objArr2, objArr, 0, i2, i + i2);
        int i3 = this.c;
        if (i3 < objArr.length) {
            objArr[i3] = null;
        }
        return objArr;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        f();
        return pi.a(this.a, this.b, this.c, this);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        g();
        f();
        e(this.b + this.c, obj);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray() {
        f();
        Object[] objArr = this.a;
        int i = this.c;
        int i2 = this.b;
        int i3 = i + i2;
        objArr.getClass();
        k8.x(i3, objArr.length);
        Object[] objArrCopyOfRange = Arrays.copyOfRange(objArr, i2, i3);
        objArrCopyOfRange.getClass();
        return objArrCopyOfRange;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        collection.getClass();
        g();
        f();
        int size = collection.size();
        d(this.b + this.c, collection, size);
        return size > 0;
    }
}
