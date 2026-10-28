package defpackage;

import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class de {
    public final int a;
    public final int b;
    public final int c;
    public final Boolean d;

    public de(int i, int i2, int i3, Boolean bool) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = bool;
    }

    public final boolean a() {
        int i = this.c;
        return (i == 8192 || i == 8193 || i == 16384) && !k8.e(this.d, Boolean.FALSE);
    }

    public final boolean b() {
        int i = this.a;
        return i == 1 || (i != 0 && this.b >= 30000);
    }

    public final String c() {
        String str = b() ? "FM" : "AM";
        Set set = nb.l;
        int i = this.b;
        String strZ = pi.z(str, String.valueOf(i));
        return strZ == null ? String.valueOf(i) : strZ;
    }

    public final boolean d() {
        return this.c == 4097 && !k8.e(this.d, Boolean.FALSE);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof de)) {
            return false;
        }
        de deVar = (de) obj;
        return this.a == deVar.a && this.b == deVar.b && this.c == deVar.c && k8.e(this.d, deVar.d);
    }

    public final int hashCode() {
        int iHashCode = (Integer.hashCode(this.c) + ((Integer.hashCode(this.b) + (Integer.hashCode(this.a) * 31)) * 31)) * 31;
        Boolean bool = this.d;
        return iHashCode + (bool == null ? 0 : bool.hashCode());
    }

    public final String toString() {
        return "State(band=" + this.a + ", frequency=" + this.b + ", status=" + this.c + ", focused=" + this.d + ")";
    }
}
