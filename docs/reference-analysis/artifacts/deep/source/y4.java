package defpackage;

import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class y4 extends g implements Serializable {
    public final Enum[] a;

    public y4(Enum[] enumArr) {
        this.a = enumArr;
    }

    @Override // defpackage.g
    public final int a() {
        return this.a.length;
    }

    @Override // defpackage.g, java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        if (!(obj instanceof Enum)) {
            return false;
        }
        Enum r2 = (Enum) obj;
        return ((Enum) x0.T0(this.a, r2.ordinal())) == r2;
    }

    @Override // java.util.List
    public final Object get(int i) {
        Enum[] enumArr = this.a;
        int length = enumArr.length;
        if (i < 0 || i >= length) {
            throw new IndexOutOfBoundsException(c.c(i, length, "index: ", ", size: "));
        }
        return enumArr[i];
    }

    @Override // defpackage.g, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Enum)) {
            return -1;
        }
        Enum r3 = (Enum) obj;
        int iOrdinal = r3.ordinal();
        if (((Enum) x0.T0(this.a, iOrdinal)) == r3) {
            return iOrdinal;
        }
        return -1;
    }

    @Override // defpackage.g, java.util.List
    public final int lastIndexOf(Object obj) {
        if (!(obj instanceof Enum)) {
            return -1;
        }
        Enum r3 = (Enum) obj;
        int iOrdinal = r3.ordinal();
        if (((Enum) x0.T0(this.a, iOrdinal)) == r3) {
            return iOrdinal;
        }
        return -1;
    }
}
