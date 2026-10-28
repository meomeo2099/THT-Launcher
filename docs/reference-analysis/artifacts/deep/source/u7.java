package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class u7 {
    public final float a;
    public final float b;
    public final float c;
    public final float d;
    public final String e;
    public final t7 f;

    public u7(float f, float f2, float f3, float f4, String str, t7 t7Var) {
        this.a = f;
        this.b = f2;
        this.c = f3;
        this.d = f4;
        this.e = str;
        this.f = t7Var;
    }

    public static u7 b(u7 u7Var, float f, float f2, float f3, float f4, String str, t7 t7Var, int i) {
        if ((i & 1) != 0) {
            f = u7Var.a;
        }
        float f5 = f;
        if ((i & 2) != 0) {
            f2 = u7Var.b;
        }
        float f6 = f2;
        if ((i & 4) != 0) {
            f3 = u7Var.c;
        }
        float f7 = f3;
        if ((i & 8) != 0) {
            f4 = u7Var.d;
        }
        float f8 = f4;
        if ((i & 16) != 0) {
            str = u7Var.e;
        }
        String str2 = str;
        if ((i & 32) != 0) {
            t7Var = u7Var.f;
        }
        t7 t7Var2 = t7Var;
        u7Var.getClass();
        str2.getClass();
        t7Var2.getClass();
        return new u7(f5, f6, f7, f8, str2, t7Var2);
    }

    public final u7 a() {
        float f = this.c;
        float f2 = this.a;
        float fR = pi.r(f - f2, 0.03f, 1.0f);
        float f3 = this.d;
        float f4 = this.b;
        float fR2 = pi.r(f3 - f4, 0.03f, 1.0f);
        float fR3 = pi.r(f2, 0.0f, 1.0f - fR);
        float fR4 = pi.r(f4, 0.0f, 1.0f - fR2);
        return b(this, fR3, fR4, fR3 + fR, fR4 + fR2, null, null, 48);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof u7)) {
            return false;
        }
        u7 u7Var = (u7) obj;
        return Float.compare(this.a, u7Var.a) == 0 && Float.compare(this.b, u7Var.b) == 0 && Float.compare(this.c, u7Var.c) == 0 && Float.compare(this.d, u7Var.d) == 0 && k8.e(this.e, u7Var.e) && this.f == u7Var.f;
    }

    public final int hashCode() {
        return this.f.hashCode() + c.b((Float.hashCode(this.d) + ((Float.hashCode(this.c) + ((Float.hashCode(this.b) + (Float.hashCode(this.a) * 31)) * 31)) * 31)) * 31, 31, this.e);
    }

    public final String toString() {
        return "Zone(left=" + this.a + ", top=" + this.b + ", right=" + this.c + ", bottom=" + this.d + ", action=" + this.e + ", show=" + this.f + ")";
    }

    public /* synthetic */ u7(float f, float f2, float f3, float f4) {
        this(f, f2, f3, f4, "", t7.d);
    }
}
