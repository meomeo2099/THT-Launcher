package defpackage;

import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ue implements j6, Serializable {
    public static final ue d = new ue();
    public transient ue a;
    public final boolean b = false;
    public final int c = 0;

    public final r2 a() {
        if (this.b) {
            ne.a.getClass();
            return new xc();
        }
        ne.a.getClass();
        return new s2();
    }

    @Override // defpackage.j6
    public final Object b(Object obj) {
        jb jbVar = (jb) obj;
        jbVar.getClass();
        return jbVar.a();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        boolean z = obj instanceof ue;
        if (z) {
            ue ueVar = (ue) obj;
            return this.c == ueVar.c && a().equals(ueVar.a());
        }
        if (!z) {
            return false;
        }
        ue ueVar2 = this.a;
        if (ueVar2 == null) {
            ne.a.getClass();
            this.a = this;
        } else {
            this = ueVar2;
        }
        return obj.equals(this);
    }

    public final int hashCode() {
        a();
        return (((a().hashCode() * 31) + 3377907) * 31) - 542707195;
    }

    public final String toString() {
        ue ueVar = this.a;
        if (ueVar == null) {
            ne.a.getClass();
            this.a = this;
            ueVar = this;
        }
        return ueVar != this ? ueVar.toString() : "function next (Kotlin reflection is not available)";
    }
}
