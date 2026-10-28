package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class ec {
    public final boolean a;
    public final String b;
    public final String c;
    public final String d;

    public ec(boolean z, String str, String str2, String str3) {
        this.a = z;
        this.b = str;
        this.c = str2;
        this.d = str3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ec)) {
            return false;
        }
        ec ecVar = (ec) obj;
        return this.a == ecVar.a && this.b.equals(ecVar.b) && this.c.equals(ecVar.c) && this.d.equals(ecVar.d);
    }

    public final int hashCode() {
        return this.d.hashCode() + c.b(c.b(Boolean.hashCode(this.a) * 31, 31, this.b), 31, this.c);
    }

    public final String toString() {
        return "LastPlayed(radio=" + this.a + ", packageName=" + this.b + ", title=" + this.c + ", artist=" + this.d + ")";
    }
}
