package defpackage;

import android.graphics.drawable.Icon;
import android.media.session.MediaSession;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class hc {
    public final String a;
    public final String b;
    public final long c;
    public final String d;
    public final String e;
    public final Icon f;
    public final MediaSession.Token g;
    public final LinkedHashMap h;

    public hc(String str, String str2, long j, String str3, String str4, Icon icon, MediaSession.Token token, LinkedHashMap linkedHashMap) {
        this.a = str;
        this.b = str2;
        this.c = j;
        this.d = str3;
        this.e = str4;
        this.f = icon;
        this.g = token;
        this.h = linkedHashMap;
    }

    public final Boolean a() {
        Map map = wb.a;
        Set setKeySet = this.h.keySet();
        setKeySet.getClass();
        xb xbVar = xb.b;
        boolean zContains = setKeySet.contains(xbVar);
        xb xbVar2 = xb.a;
        if (zContains && !setKeySet.contains(xbVar2)) {
            return Boolean.TRUE;
        }
        if (!setKeySet.contains(xbVar2) || setKeySet.contains(xbVar)) {
            return null;
        }
        return Boolean.FALSE;
    }

    public final boolean b(xb xbVar) {
        Map map = wb.a;
        int iOrdinal = xbVar.ordinal();
        List listI0 = (iOrdinal == 0 || iOrdinal == 1) ? j3.i0(xbVar, xb.c) : pi.J(xbVar);
        if (listI0.isEmpty()) {
            return false;
        }
        Iterator it = listI0.iterator();
        while (it.hasNext()) {
            if (this.h.containsKey((xb) it.next())) {
                return true;
            }
        }
        return false;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof hc)) {
            return false;
        }
        hc hcVar = (hc) obj;
        return this.a.equals(hcVar.a) && this.b.equals(hcVar.b) && this.c == hcVar.c && this.d.equals(hcVar.d) && this.e.equals(hcVar.e) && k8.e(this.f, hcVar.f) && k8.e(this.g, hcVar.g) && this.h.equals(hcVar.h);
    }

    public final int hashCode() {
        int iB = c.b(c.b((Long.hashCode(this.c) + c.b(this.a.hashCode() * 31, 31, this.b)) * 31, 31, this.d), 31, this.e);
        Icon icon = this.f;
        int iHashCode = (iB + (icon == null ? 0 : icon.hashCode())) * 31;
        MediaSession.Token token = this.g;
        return this.h.hashCode() + ((iHashCode + (token != null ? token.hashCode() : 0)) * 31);
    }

    public final String toString() {
        return "MusicNotification(key=" + this.a + ", packageName=" + this.b + ", postedAt=" + this.c + ", title=" + this.d + ", artist=" + this.e + ", artwork=" + this.f + ", token=" + this.g + ", actions=" + this.h + ")";
    }
}
