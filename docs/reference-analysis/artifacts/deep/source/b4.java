package defpackage;

import android.view.View;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class b4 {
    public final View a;
    public j4 b;
    public final j4 c;
    public final float d;
    public final String e;

    public b4(View view, j4 j4Var, j4 j4Var2, float f, String str) {
        this.a = view;
        this.b = j4Var;
        this.c = j4Var2;
        this.d = f;
        this.e = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b4)) {
            return false;
        }
        b4 b4Var = (b4) obj;
        return this.a.equals(b4Var.a) && k8.e(this.b, b4Var.b) && this.c.equals(b4Var.c) && Float.compare(this.d, b4Var.d) == 0 && this.e.equals(b4Var.e);
    }

    public final int hashCode() {
        return this.e.hashCode() + ((Float.hashCode(this.d) + ((this.c.hashCode() + ((this.b.hashCode() + (this.a.hashCode() * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "Item(view=" + this.a + ", rect=" + this.b + ", original=" + this.c + ", font=" + this.d + ", key=" + this.e + ")";
    }
}
