package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class j4 {
    public final float a;
    public final float b;
    public final float c;
    public final float d;

    public j4(float f, float f2, float f3, float f4) {
        this.a = f;
        this.b = f2;
        this.c = f3;
        this.d = f4;
    }

    public static j4 a(j4 j4Var, float f, float f2, float f3, float f4, int i) {
        if ((i & 1) != 0) {
            f = j4Var.a;
        }
        if ((i & 2) != 0) {
            f2 = j4Var.b;
        }
        if ((i & 4) != 0) {
            f3 = j4Var.c;
        }
        if ((i & 8) != 0) {
            f4 = j4Var.d;
        }
        j4Var.getClass();
        return new j4(f, f2, f3, f4);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof j4)) {
            return false;
        }
        j4 j4Var = (j4) obj;
        return Float.compare(this.a, j4Var.a) == 0 && Float.compare(this.b, j4Var.b) == 0 && Float.compare(this.c, j4Var.c) == 0 && Float.compare(this.d, j4Var.d) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.d) + ((Float.hashCode(this.c) + ((Float.hashCode(this.b) + (Float.hashCode(this.a) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "DesktopRect(x=" + this.a + ", y=" + this.b + ", w=" + this.c + ", h=" + this.d + ")";
    }
}
