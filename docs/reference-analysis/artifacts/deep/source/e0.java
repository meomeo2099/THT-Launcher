package defpackage;

import android.content.ComponentName;
import android.content.pm.ResolveInfo;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class e0 {
    public final String a;
    public final ComponentName b;
    public final ResolveInfo c;

    public e0(String str, ComponentName componentName, ResolveInfo resolveInfo) {
        str.getClass();
        this.a = str;
        this.b = componentName;
        this.c = resolveInfo;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e0)) {
            return false;
        }
        e0 e0Var = (e0) obj;
        return k8.e(this.a, e0Var.a) && this.b.equals(e0Var.b) && this.c.equals(e0Var.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + ((this.b.hashCode() + (this.a.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "App(name=" + this.a + ", component=" + this.b + ", info=" + this.c + ")";
    }
}
