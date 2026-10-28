package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class b9 {
    public final String a;
    public final String b;
    public final int c;
    public final String d;

    public b9(int i, String str, String str2, String str3) {
        str2.getClass();
        this.a = str;
        this.b = str2;
        this.c = i;
        this.d = str3;
    }

    public final String a() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b9)) {
            return false;
        }
        b9 b9Var = (b9) obj;
        return this.a.equals(b9Var.a) && k8.e(this.b, b9Var.b) && this.c == b9Var.c && this.d.equals(b9Var.d);
    }

    public final int hashCode() {
        return this.d.hashCode() + ((Integer.hashCode(this.c) + c.b(this.a.hashCode() * 31, 31, this.b)) * 31);
    }

    public final String toString() {
        return "Section(key=" + this.a + ", title=" + this.b + ", color=" + this.c + ", glyph=" + this.d + ")";
    }
}
