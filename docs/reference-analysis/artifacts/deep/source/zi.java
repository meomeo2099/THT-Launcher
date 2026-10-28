package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class zi {
    public final String a;
    public final String b;
    public final String c;

    public zi(String str, String str2, String str3) {
        str2.getClass();
        str3.getClass();
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zi)) {
            return false;
        }
        zi ziVar = (zi) obj;
        return this.a.equals(ziVar.a) && k8.e(this.b, ziVar.b) && k8.e(this.c, ziVar.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + c.b(this.a.hashCode() * 31, 31, this.b);
    }

    public final String toString() {
        return "Vehicle(id=" + this.a + ", brand=" + this.b + ", model=" + this.c + ")";
    }
}
