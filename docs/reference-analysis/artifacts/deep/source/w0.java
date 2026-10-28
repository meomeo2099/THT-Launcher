package defpackage;

import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class w0 extends h {
    public static final Object[] d = new Object[0];
    public int a;
    public Object[] b = d;
    public int c;

    @Override // defpackage.h
    public final int a() {
        return this.c;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        int length;
        int i2 = this.c;
        if (i < 0 || i > i2) {
            throw new IndexOutOfBoundsException(c.c(i, i2, "index: ", ", size: "));
        }
        if (i == i2) {
            addLast(obj);
            return;
        }
        if (i == 0) {
            addFirst(obj);
            return;
        }
        i();
        d(this.c + 1);
        int iH = h(this.a + i);
        int i3 = this.c;
        if (i < ((i3 + 1) >> 1)) {
            if (iH == 0) {
                Object[] objArr = this.b;
                objArr.getClass();
                length = objArr.length - 1;
            } else {
                length = iH - 1;
            }
            int length2 = this.a;
            if (length2 == 0) {
                Object[] objArr2 = this.b;
                objArr2.getClass();
                length2 = objArr2.length;
            }
            int i4 = length2 - 1;
            int i5 = this.a;
            Object[] objArr3 = this.b;
            if (length >= i5) {
                objArr3[i4] = objArr3[i5];
                x0.Q0(objArr3, objArr3, i5, i5 + 1, length + 1);
            } else {
                x0.Q0(objArr3, objArr3, i5 - 1, i5, objArr3.length);
                Object[] objArr4 = this.b;
                objArr4[objArr4.length - 1] = objArr4[0];
                x0.Q0(objArr4, objArr4, 0, 1, length + 1);
            }
            this.b[length] = obj;
            this.a = i4;
        } else {
            int iH2 = h(i3 + this.a);
            Object[] objArr5 = this.b;
            if (iH < iH2) {
                x0.Q0(objArr5, objArr5, iH + 1, iH, iH2);
            } else {
                x0.Q0(objArr5, objArr5, 1, 0, iH2);
                Object[] objArr6 = this.b;
                objArr6[0] = objArr6[objArr6.length - 1];
                x0.Q0(objArr6, objArr6, iH + 1, iH, objArr6.length - 1);
            }
            this.b[iH] = obj;
        }
        this.c++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection collection) {
        collection.getClass();
        int i2 = this.c;
        if (i < 0 || i > i2) {
            throw new IndexOutOfBoundsException(c.c(i, i2, "index: ", ", size: "));
        }
        if (collection.isEmpty()) {
            return false;
        }
        if (i == this.c) {
            return addAll(collection);
        }
        i();
        d(collection.size() + this.c);
        int iH = h(this.c + this.a);
        int iH2 = h(this.a + i);
        int size = collection.size();
        if (i >= ((this.c + 1) >> 1)) {
            int i3 = iH2 + size;
            Object[] objArr = this.b;
            if (iH2 < iH) {
                int i4 = size + iH;
                if (i4 <= objArr.length) {
                    x0.Q0(objArr, objArr, i3, iH2, iH);
                } else if (i3 >= objArr.length) {
                    x0.Q0(objArr, objArr, i3 - objArr.length, iH2, iH);
                } else {
                    int length = iH - (i4 - objArr.length);
                    x0.Q0(objArr, objArr, 0, length, iH);
                    Object[] objArr2 = this.b;
                    x0.Q0(objArr2, objArr2, i3, iH2, length);
                }
            } else {
                x0.Q0(objArr, objArr, size, 0, iH);
                Object[] objArr3 = this.b;
                if (i3 >= objArr3.length) {
                    x0.Q0(objArr3, objArr3, i3 - objArr3.length, iH2, objArr3.length);
                } else {
                    x0.Q0(objArr3, objArr3, 0, objArr3.length - size, objArr3.length);
                    Object[] objArr4 = this.b;
                    x0.Q0(objArr4, objArr4, i3, iH2, objArr4.length - size);
                }
            }
            c(iH2, collection);
            return true;
        }
        int i5 = this.a;
        int length2 = i5 - size;
        Object[] objArr5 = this.b;
        if (iH2 < i5) {
            x0.Q0(objArr5, objArr5, length2, i5, objArr5.length);
            Object[] objArr6 = this.b;
            if (size >= iH2) {
                x0.Q0(objArr6, objArr6, objArr6.length - size, 0, iH2);
            } else {
                x0.Q0(objArr6, objArr6, objArr6.length - size, 0, size);
                Object[] objArr7 = this.b;
                x0.Q0(objArr7, objArr7, 0, size, iH2);
            }
        } else if (length2 >= 0) {
            x0.Q0(objArr5, objArr5, length2, i5, iH2);
        } else {
            length2 += objArr5.length;
            int i6 = iH2 - i5;
            int length3 = objArr5.length - length2;
            if (length3 >= i6) {
                x0.Q0(objArr5, objArr5, length2, i5, iH2);
            } else {
                x0.Q0(objArr5, objArr5, length2, i5, i5 + length3);
                Object[] objArr8 = this.b;
                x0.Q0(objArr8, objArr8, 0, this.a + length3, iH2);
            }
        }
        this.a = length2;
        c(f(iH2 - size), collection);
        return true;
    }

    public final void addFirst(Object obj) {
        i();
        d(this.c + 1);
        int length = this.a;
        if (length == 0) {
            Object[] objArr = this.b;
            objArr.getClass();
            length = objArr.length;
        }
        int i = length - 1;
        this.a = i;
        this.b[i] = obj;
        this.c++;
    }

    public final void addLast(Object obj) {
        i();
        d(a() + 1);
        this.b[h(a() + this.a)] = obj;
        this.c = a() + 1;
    }

    @Override // defpackage.h
    public final Object b(int i) {
        int i2 = this.c;
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException(c.c(i, i2, "index: ", ", size: "));
        }
        if (i == a() - 1) {
            return removeLast();
        }
        if (i == 0) {
            return removeFirst();
        }
        i();
        int iH = h(this.a + i);
        Object[] objArr = this.b;
        Object obj = objArr[iH];
        int i3 = this.c >> 1;
        int i4 = this.a;
        if (i < i3) {
            if (iH >= i4) {
                x0.Q0(objArr, objArr, i4 + 1, i4, iH);
            } else {
                x0.Q0(objArr, objArr, 1, 0, iH);
                Object[] objArr2 = this.b;
                objArr2[0] = objArr2[objArr2.length - 1];
                int i5 = this.a;
                x0.Q0(objArr2, objArr2, i5 + 1, i5, objArr2.length - 1);
            }
            Object[] objArr3 = this.b;
            int i6 = this.a;
            objArr3[i6] = null;
            this.a = e(i6);
        } else {
            int iH2 = h((a() - 1) + i4);
            Object[] objArr4 = this.b;
            if (iH <= iH2) {
                x0.Q0(objArr4, objArr4, iH, iH + 1, iH2 + 1);
            } else {
                x0.Q0(objArr4, objArr4, iH, iH + 1, objArr4.length);
                Object[] objArr5 = this.b;
                objArr5[objArr5.length - 1] = objArr5[0];
                x0.Q0(objArr5, objArr5, 0, 1, iH2 + 1);
            }
            this.b[iH2] = null;
        }
        this.c--;
        return obj;
    }

    public final void c(int i, Collection collection) {
        Iterator it = collection.iterator();
        int length = this.b.length;
        while (i < length && it.hasNext()) {
            this.b[i] = it.next();
            i++;
        }
        int i2 = this.a;
        for (int i3 = 0; i3 < i2 && it.hasNext(); i3++) {
            this.b[i3] = it.next();
        }
        this.c = collection.size() + this.c;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        if (!isEmpty()) {
            i();
            g(this.a, h(a() + this.a));
        }
        this.a = 0;
        this.c = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final void d(int i) {
        if (i < 0) {
            throw new IllegalStateException("Deque is too big.");
        }
        Object[] objArr = this.b;
        if (i <= objArr.length) {
            return;
        }
        if (objArr == d) {
            if (i < 10) {
                i = 10;
            }
            this.b = new Object[i];
            return;
        }
        int length = objArr.length;
        int i2 = length + (length >> 1);
        if (i2 - i < 0) {
            i2 = i;
        }
        if (i2 - 2147483639 > 0) {
            i2 = i > 2147483639 ? Integer.MAX_VALUE : 2147483639;
        }
        Object[] objArr2 = new Object[i2];
        x0.Q0(objArr, objArr2, 0, this.a, objArr.length);
        Object[] objArr3 = this.b;
        int length2 = objArr3.length;
        int i3 = this.a;
        x0.Q0(objArr3, objArr2, length2 - i3, 0, i3);
        this.a = 0;
        this.b = objArr2;
    }

    public final int e(int i) {
        Object[] objArr = this.b;
        objArr.getClass();
        if (i == objArr.length - 1) {
            return 0;
        }
        return i + 1;
    }

    public final int f(int i) {
        return i < 0 ? i + this.b.length : i;
    }

    public final void g(int i, int i2) {
        Object[] objArr = this.b;
        if (i < i2) {
            x0.R0(objArr, i, i2);
        } else {
            Arrays.fill(objArr, i, objArr.length, (Object) null);
            x0.R0(this.b, 0, i2);
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        int iA = a();
        if (i < 0 || i >= iA) {
            throw new IndexOutOfBoundsException(c.c(i, iA, "index: ", ", size: "));
        }
        return this.b[h(this.a + i)];
    }

    public final int h(int i) {
        Object[] objArr = this.b;
        return i >= objArr.length ? i - objArr.length : i;
    }

    public final void i() {
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        int i;
        int iH = h(a() + this.a);
        int length = this.a;
        if (length < iH) {
            while (length < iH) {
                if (k8.e(obj, this.b[length])) {
                    i = this.a;
                } else {
                    length++;
                }
            }
            return -1;
        }
        if (length < iH) {
            return -1;
        }
        int length2 = this.b.length;
        while (length < length2) {
            if (k8.e(obj, this.b[length])) {
                i = this.a;
            } else {
                length++;
            }
        }
        for (int i2 = 0; i2 < iH; i2++) {
            if (k8.e(obj, this.b[i2])) {
                length = i2 + this.b.length;
                i = this.a;
            }
        }
        return -1;
        return length - i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return a() == 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        Object[] objArr;
        int length;
        int i;
        int iH = h(this.c + this.a);
        int i2 = this.a;
        if (i2 < iH) {
            length = iH - 1;
            if (i2 <= length) {
                while (!k8.e(obj, this.b[length])) {
                    if (length != i2) {
                        length--;
                    }
                }
                i = this.a;
                return length - i;
            }
            return -1;
        }
        if (i2 > iH) {
            do {
                iH--;
                objArr = this.b;
                if (-1 >= iH) {
                    objArr.getClass();
                    length = objArr.length - 1;
                    int i3 = this.a;
                    if (i3 <= length) {
                        while (!k8.e(obj, this.b[length])) {
                            if (length != i3) {
                                length--;
                            }
                        }
                        i = this.a;
                    }
                }
                return length - i;
            } while (!k8.e(obj, objArr[iH]));
            length = iH + this.b.length;
            i = this.a;
            return length - i;
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        int iIndexOf = indexOf(obj);
        if (iIndexOf == -1) {
            return false;
        }
        b(iIndexOf);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        int iH;
        Object[] objArr;
        collection.getClass();
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty() && this.b.length != 0) {
            int iH2 = h(this.c + this.a);
            int i = this.a;
            if (i < iH2) {
                iH = i;
                while (true) {
                    objArr = this.b;
                    if (i >= iH2) {
                        break;
                    }
                    Object obj = objArr[i];
                    if (collection.contains(obj)) {
                        z = true;
                    } else {
                        this.b[iH] = obj;
                        iH++;
                    }
                    i++;
                }
                x0.R0(objArr, iH, iH2);
            } else {
                int length = this.b.length;
                boolean z2 = false;
                int i2 = i;
                while (i < length) {
                    Object[] objArr2 = this.b;
                    Object obj2 = objArr2[i];
                    objArr2[i] = null;
                    if (collection.contains(obj2)) {
                        z2 = true;
                    } else {
                        this.b[i2] = obj2;
                        i2++;
                    }
                    i++;
                }
                iH = h(i2);
                for (int i3 = 0; i3 < iH2; i3++) {
                    Object[] objArr3 = this.b;
                    Object obj3 = objArr3[i3];
                    objArr3[i3] = null;
                    if (collection.contains(obj3)) {
                        z2 = true;
                    } else {
                        this.b[iH] = obj3;
                        iH = e(iH);
                    }
                }
                z = z2;
            }
            if (z) {
                i();
                this.c = f(iH - this.a);
            }
        }
        return z;
    }

    public final Object removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        i();
        Object[] objArr = this.b;
        int i = this.a;
        Object obj = objArr[i];
        objArr[i] = null;
        this.a = e(i);
        this.c = a() - 1;
        return obj;
    }

    public final Object removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        i();
        int iH = h((size() - 1) + this.a);
        Object[] objArr = this.b;
        Object obj = objArr[iH];
        objArr[iH] = null;
        this.c = a() - 1;
        return obj;
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i, int i2) {
        k8.l(i, i2, this.c);
        int i3 = i2 - i;
        if (i3 == 0) {
            return;
        }
        if (i3 == this.c) {
            clear();
            return;
        }
        if (i3 == 1) {
            b(i);
            return;
        }
        i();
        int i4 = this.c - i2;
        int i5 = this.a;
        if (i < i4) {
            int iH = h((i - 1) + i5);
            int iH2 = h(this.a + (i2 - 1));
            while (i > 0) {
                int i6 = iH + 1;
                int iMin = Math.min(i, Math.min(i6, iH2 + 1));
                Object[] objArr = this.b;
                int i7 = iH2 - iMin;
                int i8 = iH - iMin;
                x0.Q0(objArr, objArr, i7 + 1, i8 + 1, i6);
                iH = f(i8);
                iH2 = f(i7);
                i -= iMin;
            }
            int iH3 = h(this.a + i3);
            g(this.a, iH3);
            this.a = iH3;
        } else {
            int iH4 = h(i5 + i2);
            int iH5 = h(this.a + i);
            int i9 = this.c;
            while (true) {
                i9 -= i2;
                if (i9 <= 0) {
                    break;
                }
                Object[] objArr2 = this.b;
                i2 = Math.min(i9, Math.min(objArr2.length - iH4, objArr2.length - iH5));
                Object[] objArr3 = this.b;
                int i10 = iH4 + i2;
                x0.Q0(objArr3, objArr3, iH5, iH4, i10);
                iH4 = h(i10);
                iH5 = h(iH5 + i2);
            }
            int iH6 = h(this.c + this.a);
            g(f(iH6 - i3), iH6);
        }
        this.c -= i3;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection collection) {
        int iH;
        Object[] objArr;
        collection.getClass();
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty() && this.b.length != 0) {
            int iH2 = h(this.c + this.a);
            int i = this.a;
            if (i < iH2) {
                iH = i;
                while (true) {
                    objArr = this.b;
                    if (i >= iH2) {
                        break;
                    }
                    Object obj = objArr[i];
                    if (collection.contains(obj)) {
                        this.b[iH] = obj;
                        iH++;
                    } else {
                        z = true;
                    }
                    i++;
                }
                x0.R0(objArr, iH, iH2);
            } else {
                int length = this.b.length;
                boolean z2 = false;
                int i2 = i;
                while (i < length) {
                    Object[] objArr2 = this.b;
                    Object obj2 = objArr2[i];
                    objArr2[i] = null;
                    if (collection.contains(obj2)) {
                        this.b[i2] = obj2;
                        i2++;
                    } else {
                        z2 = true;
                    }
                    i++;
                }
                iH = h(i2);
                for (int i3 = 0; i3 < iH2; i3++) {
                    Object[] objArr3 = this.b;
                    Object obj3 = objArr3[i3];
                    objArr3[i3] = null;
                    if (collection.contains(obj3)) {
                        this.b[iH] = obj3;
                        iH = e(iH);
                    } else {
                        z2 = true;
                    }
                }
                z = z2;
            }
            if (z) {
                i();
                this.c = f(iH - this.a);
            }
        }
        return z;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        int iA = a();
        if (i < 0 || i >= iA) {
            throw new IndexOutOfBoundsException(c.c(i, iA, "index: ", ", size: "));
        }
        int iH = h(this.a + i);
        Object[] objArr = this.b;
        Object obj2 = objArr[iH];
        objArr[iH] = obj;
        return obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray(Object[] objArr) {
        objArr.getClass();
        int length = objArr.length;
        int i = this.c;
        if (length < i) {
            Object objNewInstance = Array.newInstance(objArr.getClass().getComponentType(), i);
            objNewInstance.getClass();
            objArr = (Object[]) objNewInstance;
        }
        int iH = h(this.c + this.a);
        int i2 = this.a;
        if (i2 < iH) {
            x0.Q0(this.b, objArr, 0, i2, iH);
        } else if (!isEmpty()) {
            Object[] objArr2 = this.b;
            x0.Q0(objArr2, objArr, 0, this.a, objArr2.length);
            Object[] objArr3 = this.b;
            x0.Q0(objArr3, objArr, objArr3.length - this.a, 0, iH);
        }
        int i3 = this.c;
        if (i3 < objArr.length) {
            objArr[i3] = null;
        }
        return objArr;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray() {
        return toArray(new Object[a()]);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        addLast(obj);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        collection.getClass();
        if (collection.isEmpty()) {
            return false;
        }
        i();
        d(collection.size() + a());
        c(h(a() + this.a), collection);
        return true;
    }
}
