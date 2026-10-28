package defpackage;

import java.util.Arrays;
import java.util.Iterator;
import java.util.RandomAccess;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class jf extends g implements RandomAccess {
    public final Object[] a;
    public final int b;
    public int c;
    public int d;

    public jf(Object[] objArr, int i) {
        this.a = objArr;
        if (i < 0) {
            throw new IllegalArgumentException(("ring buffer filled size should not be negative but it is " + i).toString());
        }
        if (i <= objArr.length) {
            this.b = objArr.length;
            this.d = i;
            return;
        }
        throw new IllegalArgumentException(("ring buffer filled size: " + i + " cannot be larger than the buffer size: " + objArr.length).toString());
    }

    @Override // defpackage.g
    public final int a() {
        return this.d;
    }

    public final void b() {
        if (3 > this.d) {
            throw new IllegalArgumentException(("n shouldn't be greater than the buffer size: n = 3, size = " + this.d).toString());
        }
        int i = this.c;
        int i2 = this.b;
        int i3 = (i + 3) % i2;
        Object[] objArr = this.a;
        if (i > i3) {
            Arrays.fill(objArr, i, i2, (Object) null);
            Arrays.fill(objArr, 0, i3, (Object) null);
        } else {
            Arrays.fill(objArr, i, i3, (Object) null);
        }
        this.c = i3;
        this.d -= 3;
    }

    @Override // java.util.List
    public final Object get(int i) {
        int i2 = this.d;
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException(c.c(i, i2, "index: ", ", size: "));
        }
        return this.a[(this.c + i) % this.b];
    }

    @Override // defpackage.g, java.util.List, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new hf(this);
    }

    @Override // defpackage.g, java.util.List, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        Object[] objArr2;
        objArr.getClass();
        int length = objArr.length;
        int i = this.d;
        if (length < i) {
            objArr = Arrays.copyOf(objArr, i);
        }
        int i2 = this.d;
        int i3 = this.c;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            objArr2 = this.a;
            if (i5 >= i2 || i3 >= this.b) {
                break;
            }
            objArr[i5] = objArr2[i3];
            i5++;
            i3++;
        }
        while (i5 < i2) {
            objArr[i5] = objArr2[i4];
            i5++;
            i4++;
        }
        if (i2 < objArr.length) {
            objArr[i2] = null;
        }
        return objArr;
    }

    @Override // defpackage.g, java.util.List, java.util.Collection
    public final Object[] toArray() {
        return toArray(new Object[a()]);
    }
}
