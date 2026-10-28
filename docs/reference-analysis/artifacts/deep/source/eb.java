package defpackage;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class eb implements Map, Serializable, x9 {
    public static final eb n;
    public Object[] a;
    public Object[] b;
    public int[] c;
    public int[] d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public fb j;
    public gb k;
    public fb l;
    public boolean m;

    static {
        eb ebVar = new eb(0);
        ebVar.m = true;
        n = ebVar;
    }

    public eb(int i) {
        if (i < 0) {
            w.a("capacity must be non-negative.");
            throw null;
        }
        Object[] objArr = new Object[i];
        int[] iArr = new int[i];
        int iHighestOneBit = Integer.highestOneBit((i < 1 ? 1 : i) * 3);
        this.a = objArr;
        this.b = null;
        this.c = iArr;
        this.d = new int[iHighestOneBit];
        this.e = 2;
        this.f = 0;
        this.g = Integer.numberOfLeadingZeros(iHighestOneBit) + 1;
    }

    public final int a(Object obj) {
        b();
        while (true) {
            int iH = h(obj);
            int i = this.e * 2;
            int length = this.d.length / 2;
            if (i > length) {
                i = length;
            }
            int i2 = 0;
            while (true) {
                int[] iArr = this.d;
                int i3 = iArr[iH];
                if (i3 <= 0) {
                    int i4 = this.f;
                    Object[] objArr = this.a;
                    if (i4 >= objArr.length) {
                        e(1);
                        break;
                    }
                    int i5 = i4 + 1;
                    this.f = i5;
                    objArr[i4] = obj;
                    this.c[i4] = iH;
                    iArr[iH] = i5;
                    this.i++;
                    this.h++;
                    if (i2 > this.e) {
                        this.e = i2;
                    }
                    return i4;
                }
                if (k8.e(this.a[i3 - 1], obj)) {
                    return -i3;
                }
                i2++;
                if (i2 > i) {
                    i(this.d.length * 2);
                    break;
                }
                iH = iH == 0 ? this.d.length - 1 : iH - 1;
            }
        }
    }

    public final void b() {
        if (this.m) {
            throw new UnsupportedOperationException();
        }
    }

    public final void c(boolean z) {
        int i;
        Object[] objArr = this.b;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            i = this.f;
            if (i2 >= i) {
                break;
            }
            int[] iArr = this.c;
            int i4 = iArr[i2];
            if (i4 >= 0) {
                Object[] objArr2 = this.a;
                objArr2[i3] = objArr2[i2];
                if (objArr != null) {
                    objArr[i3] = objArr[i2];
                }
                if (z) {
                    iArr[i3] = i4;
                    this.d[i4] = i3 + 1;
                }
                i3++;
            }
            i2++;
        }
        pi.N(this.a, i3, i);
        if (objArr != null) {
            pi.N(objArr, i3, this.f);
        }
        this.f = i3;
    }

    @Override // java.util.Map
    public final void clear() {
        b();
        int i = this.f - 1;
        if (i >= 0) {
            int i2 = 0;
            while (true) {
                int[] iArr = this.c;
                int i3 = iArr[i2];
                if (i3 >= 0) {
                    this.d[i3] = 0;
                    iArr[i2] = -1;
                }
                if (i2 == i) {
                    break;
                } else {
                    i2++;
                }
            }
        }
        pi.N(this.a, 0, this.f);
        Object[] objArr = this.b;
        if (objArr != null) {
            pi.N(objArr, 0, this.f);
        }
        this.i = 0;
        this.f = 0;
        this.h++;
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return f(obj) >= 0;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        return g(obj) >= 0;
    }

    public final boolean d(Collection collection) {
        boolean zE;
        collection.getClass();
        for (Object obj : collection) {
            if (obj != null) {
                try {
                    Map.Entry entry = (Map.Entry) obj;
                    int iF = f(entry.getKey());
                    if (iF < 0) {
                        zE = false;
                    } else {
                        Object[] objArr = this.b;
                        objArr.getClass();
                        zE = k8.e(objArr[iF], entry.getValue());
                    }
                    if (!zE) {
                    }
                } catch (ClassCastException unused) {
                }
            }
            return false;
        }
        return true;
    }

    public final void e(int i) {
        Object[] objArr = this.a;
        int length = objArr.length;
        int i2 = this.f;
        int i3 = length - i2;
        int i4 = i2 - this.i;
        if (i3 < i && i3 + i4 >= i && i4 >= objArr.length / 4) {
            c(true);
            return;
        }
        int i5 = i2 + i;
        if (i5 < 0) {
            throw new OutOfMemoryError();
        }
        if (i5 > objArr.length) {
            int length2 = objArr.length;
            int i6 = length2 + (length2 >> 1);
            if (i6 - i5 < 0) {
                i6 = i5;
            }
            if (i6 - 2147483639 > 0) {
                i6 = i5 > 2147483639 ? Integer.MAX_VALUE : 2147483639;
            }
            this.a = Arrays.copyOf(objArr, i6);
            Object[] objArr2 = this.b;
            this.b = objArr2 != null ? Arrays.copyOf(objArr2, i6) : null;
            this.c = Arrays.copyOf(this.c, i6);
            int iHighestOneBit = Integer.highestOneBit((i6 >= 1 ? i6 : 1) * 3);
            if (iHighestOneBit > this.d.length) {
                i(iHighestOneBit);
            }
        }
    }

    @Override // java.util.Map
    public final Set entrySet() {
        fb fbVar = this.l;
        if (fbVar != null) {
            return fbVar;
        }
        fb fbVar2 = new fb(this, 0);
        this.l = fbVar2;
        return fbVar2;
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        return this.i == map.size() && d(map.entrySet());
    }

    public final int f(Object obj) {
        int iH = h(obj);
        int i = this.e;
        while (true) {
            int i2 = this.d[iH];
            if (i2 == 0) {
                return -1;
            }
            if (i2 > 0) {
                int i3 = i2 - 1;
                if (k8.e(this.a[i3], obj)) {
                    return i3;
                }
            }
            i--;
            if (i < 0) {
                return -1;
            }
            iH = iH == 0 ? this.d.length - 1 : iH - 1;
        }
    }

    public final int g(Object obj) {
        int i = this.f;
        while (true) {
            i--;
            if (i < 0) {
                return -1;
            }
            if (this.c[i] >= 0) {
                Object[] objArr = this.b;
                objArr.getClass();
                if (k8.e(objArr[i], obj)) {
                    return i;
                }
            }
        }
    }

    @Override // java.util.Map
    public final Object get(Object obj) {
        int iF = f(obj);
        if (iF < 0) {
            return null;
        }
        Object[] objArr = this.b;
        objArr.getClass();
        return objArr[iF];
    }

    public final int h(Object obj) {
        return ((obj != null ? obj.hashCode() : 0) * (-1640531527)) >>> this.g;
    }

    @Override // java.util.Map
    public final int hashCode() {
        cb cbVar = new cb(this, 0);
        int i = 0;
        while (cbVar.hasNext()) {
            int i2 = cbVar.b;
            eb ebVar = cbVar.a;
            if (i2 >= ebVar.f) {
                throw new NoSuchElementException();
            }
            cbVar.b = i2 + 1;
            cbVar.c = i2;
            Object obj = ebVar.a[i2];
            int iHashCode = obj != null ? obj.hashCode() : 0;
            Object[] objArr = ebVar.b;
            objArr.getClass();
            Object obj2 = objArr[cbVar.c];
            int iHashCode2 = obj2 != null ? obj2.hashCode() : 0;
            cbVar.b();
            i += iHashCode ^ iHashCode2;
        }
        return i;
    }

    public final void i(int i) {
        int[] iArr;
        this.h++;
        int i2 = 0;
        if (this.f > this.i) {
            c(false);
        }
        this.d = new int[i];
        this.g = Integer.numberOfLeadingZeros(i) + 1;
        while (i2 < this.f) {
            int i3 = i2 + 1;
            int iH = h(this.a[i2]);
            int i4 = this.e;
            while (true) {
                iArr = this.d;
                if (iArr[iH] == 0) {
                    break;
                }
                i4--;
                if (i4 < 0) {
                    throw new IllegalStateException("This cannot happen with fixed magic multiplier and grow-only hash array. Have object hashCodes changed?");
                }
                iH = iH == 0 ? iArr.length - 1 : iH - 1;
            }
            iArr[iH] = i3;
            this.c[i2] = iH;
            i2 = i3;
        }
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.i == 0;
    }

    public final void j(int i) {
        Object[] objArr = this.a;
        objArr.getClass();
        objArr[i] = null;
        Object[] objArr2 = this.b;
        if (objArr2 != null) {
            objArr2[i] = null;
        }
        int length = this.c[i];
        int i2 = this.e * 2;
        int length2 = this.d.length / 2;
        if (i2 > length2) {
            i2 = length2;
        }
        int i3 = i2;
        int i4 = 0;
        int i5 = length;
        do {
            length = length == 0 ? this.d.length - 1 : length - 1;
            i4++;
            int i6 = this.e;
            int[] iArr = this.d;
            if (i4 > i6) {
                iArr[i5] = 0;
            } else {
                int i7 = iArr[length];
                if (i7 == 0) {
                    iArr[i5] = 0;
                } else {
                    if (i7 < 0) {
                        iArr[i5] = -1;
                    } else {
                        int i8 = i7 - 1;
                        int iH = h(this.a[i8]) - length;
                        int[] iArr2 = this.d;
                        if ((iH & (iArr2.length - 1)) >= i4) {
                            iArr2[i5] = i7;
                            this.c[i8] = i5;
                        }
                        i3--;
                    }
                    i5 = length;
                    i4 = 0;
                    i3--;
                }
            }
            this.c[i] = -1;
            this.i--;
            this.h++;
        } while (i3 >= 0);
        this.d[i5] = -1;
        this.c[i] = -1;
        this.i--;
        this.h++;
    }

    @Override // java.util.Map
    public final Set keySet() {
        fb fbVar = this.j;
        if (fbVar != null) {
            return fbVar;
        }
        fb fbVar2 = new fb(this, 1);
        this.j = fbVar2;
        return fbVar2;
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        b();
        int iA = a(obj);
        Object[] objArr = this.b;
        if (objArr == null) {
            int length = this.a.length;
            if (length < 0) {
                w.a("capacity must be non-negative.");
                return null;
            }
            objArr = new Object[length];
            this.b = objArr;
        }
        if (iA >= 0) {
            objArr[iA] = obj2;
            return null;
        }
        int i = (-iA) - 1;
        Object obj3 = objArr[i];
        objArr[i] = obj2;
        return obj3;
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        map.getClass();
        b();
        Set<Map.Entry> setEntrySet = map.entrySet();
        if (setEntrySet.isEmpty()) {
            return;
        }
        e(setEntrySet.size());
        for (Map.Entry entry : setEntrySet) {
            int iA = a(entry.getKey());
            Object[] objArr = this.b;
            if (objArr == null) {
                int length = this.a.length;
                if (length < 0) {
                    w.a("capacity must be non-negative.");
                    return;
                } else {
                    objArr = new Object[length];
                    this.b = objArr;
                }
            }
            if (iA >= 0) {
                objArr[iA] = entry.getValue();
            } else {
                int i = (-iA) - 1;
                if (!k8.e(entry.getValue(), objArr[i])) {
                    objArr[i] = entry.getValue();
                }
            }
        }
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        b();
        int iF = f(obj);
        if (iF < 0) {
            return null;
        }
        Object[] objArr = this.b;
        objArr.getClass();
        Object obj2 = objArr[iF];
        j(iF);
        return obj2;
    }

    @Override // java.util.Map
    public final int size() {
        return this.i;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder((this.i * 3) + 2);
        sb.append("{");
        int i = 0;
        cb cbVar = new cb(this, 0);
        while (cbVar.hasNext()) {
            if (i > 0) {
                sb.append(", ");
            }
            int i2 = cbVar.b;
            eb ebVar = cbVar.a;
            if (i2 >= ebVar.f) {
                throw new NoSuchElementException();
            }
            cbVar.b = i2 + 1;
            cbVar.c = i2;
            Object obj = ebVar.a[i2];
            if (obj == ebVar) {
                sb.append("(this Map)");
            } else {
                sb.append(obj);
            }
            sb.append('=');
            Object[] objArr = ebVar.b;
            objArr.getClass();
            Object obj2 = objArr[cbVar.c];
            if (obj2 == ebVar) {
                sb.append("(this Map)");
            } else {
                sb.append(obj2);
            }
            cbVar.b();
            i++;
        }
        sb.append("}");
        return sb.toString();
    }

    @Override // java.util.Map
    public final Collection values() {
        gb gbVar = this.k;
        if (gbVar != null) {
            return gbVar;
        }
        gb gbVar2 = new gb(this);
        this.k = gbVar2;
        return gbVar2;
    }
}
