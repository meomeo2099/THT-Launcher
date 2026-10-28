package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class ca {
    public final String a = "sans-serif";

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ca) && this.a.equals(((ca) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode() + ((Integer.hashCode(-16777) + ((Integer.hashCode(-10233877) + 692443566) * 31)) * 31);
    }

    public final String toString() {
        return "LauncherThemeSpec(id=classic, cyan=-10233877, copper=-16777, typeface=" + this.a + ")";
    }
}
