package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class d7 {
    public final String a;
    public final String b;
    public final String c;
    public final boolean d;
    public final int e;

    public d7(String str, String str2, String str3, boolean z, int i) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = z;
        this.e = i;
    }

    public final String a() {
        return this.c;
    }

    public final boolean b() {
        return this.d;
    }

    public final int c() {
        return this.e;
    }

    public final String d() {
        return this.a;
    }

    public final String e() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d7)) {
            return false;
        }
        d7 d7Var = (d7) obj;
        return this.a.equals(d7Var.a) && this.b.equals(d7Var.b) && this.c.equals(d7Var.c) && this.d == d7Var.d && this.e == d7Var.e;
    }

    public final int hashCode() {
        return Integer.hashCode(this.e) + ((Boolean.hashCode(this.d) + c.b(c.b(this.a.hashCode() * 31, 31, this.b), 31, this.c)) * 31);
    }

    public final String toString() {
        return "IconSet(id=" + this.a + ", name=" + this.b + ", author=" + this.c + ", color=" + this.d + ", count=" + this.e + ")";
    }
}
