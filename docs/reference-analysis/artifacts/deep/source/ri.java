package defpackage;

import java.util.Locale;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class ri {
    public final String a;
    public final Locale b;
    public final Map c;

    public ri(String str, Locale locale, Map map) {
        map.getClass();
        this.a = str;
        this.b = locale;
        this.c = map;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ri)) {
            return false;
        }
        ri riVar = (ri) obj;
        return this.a.equals(riVar.a) && this.b.equals(riVar.b) && k8.e(this.c, riVar.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + ((this.b.hashCode() + (this.a.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "Catalog(code=" + this.a + ", locale=" + this.b + ", strings=" + this.c + ")";
    }
}
