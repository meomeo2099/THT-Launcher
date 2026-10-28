package defpackage;

import java.time.LocalDate;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class ef {
    public final String a;
    public final LocalDate b;

    public ef(String str, LocalDate localDate) {
        this.a = str;
        this.b = localDate;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ef)) {
            return false;
        }
        ef efVar = (ef) obj;
        return this.a.equals(efVar.a) && k8.e(this.b, efVar.b);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        LocalDate localDate = this.b;
        return iHashCode + (localDate == null ? 0 : localDate.hashCode());
    }

    public final String toString() {
        return "Entry(code=" + this.a + ", until=" + this.b + ")";
    }
}
