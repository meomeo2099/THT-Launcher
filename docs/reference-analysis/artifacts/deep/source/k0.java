package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class k0 {
    public final int a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final long f;
    public final String g;

    public k0(int i, String str, String str2, String str3, String str4, long j, String str5) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = j;
        this.g = str5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof k0)) {
            return false;
        }
        k0 k0Var = (k0) obj;
        return this.a == k0Var.a && this.b.equals(k0Var.b) && this.c.equals(k0Var.c) && k8.e(this.d, k0Var.d) && this.e.equals(k0Var.e) && this.f == k0Var.f && this.g.equals(k0Var.g);
    }

    public final int hashCode() {
        int iB = c.b(c.b(Integer.hashCode(this.a) * 31, 31, this.b), 31, this.c);
        String str = this.d;
        return this.g.hashCode() + ((Long.hashCode(this.f) + c.b((iB + (str == null ? 0 : str.hashCode())) * 31, 31, this.e)) * 31);
    }

    public final String toString() {
        return "Info(versionCode=" + this.a + ", versionName=" + this.b + ", url=" + this.c + ", mirror=" + this.d + ", sha256=" + this.e + ", size=" + this.f + ", notes=" + this.g + ")";
    }
}
