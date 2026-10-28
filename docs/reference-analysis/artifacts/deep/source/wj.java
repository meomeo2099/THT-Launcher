package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class wj {
    public final Float a;
    public final String b;
    public final long c;
    public final String d;

    public /* synthetic */ wj(Float f, String str, long j, int i) {
        this((i & 1) != 0 ? null : f, (i & 2) != 0 ? "" : str, (i & 4) != 0 ? 0L : j, (String) null);
    }

    public static wj a(wj wjVar, String str) {
        Float f = wjVar.a;
        String str2 = wjVar.b;
        long j = wjVar.c;
        wjVar.getClass();
        str2.getClass();
        return new wj(f, str2, j, str);
    }

    public final String b() {
        String str = this.b;
        str.getClass();
        String strF = th.F(str, '_');
        boolean zStartsWith = strF.startsWith("heavy");
        boolean zStartsWith2 = strF.startsWith("light");
        switch (sh.h(str).ordinal()) {
            case 0:
                return sh.j(str) ? si.a("clear_707", new Object[0]) : si.a("sunny", new Object[0]);
            case 1:
                return strF.equals("fair") ? si.a("mostly_clear", new Object[0]) : si.a("partly_cloudy", new Object[0]);
            case 2:
                return si.a("cloudy", new Object[0]);
            case 3:
                if (zStartsWith) {
                    return si.a("heavy_rain", new Object[0]);
                }
                return zStartsWith2 ? si.a("light_rain", new Object[0]) : si.a("rain", new Object[0]);
            case 4:
                if (zStartsWith) {
                    return si.a("heavy_snow", new Object[0]);
                }
                return zStartsWith2 ? si.a("light_snow", new Object[0]) : si.a("snow", new Object[0]);
            case 5:
                return si.a("sleet", new Object[0]);
            case 6:
                return si.a("fog", new Object[0]);
            case 7:
                return si.a("thunderstorm", new Object[0]);
            case 8:
                return "";
            default:
                throw new vc();
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof wj)) {
            return false;
        }
        wj wjVar = (wj) obj;
        return k8.e(this.a, wjVar.a) && k8.e(this.b, wjVar.b) && this.c == wjVar.c && k8.e(this.d, wjVar.d);
    }

    public final int hashCode() {
        Float f = this.a;
        int iHashCode = (Long.hashCode(this.c) + c.b((f == null ? 0 : f.hashCode()) * 31, 31, this.b)) * 31;
        String str = this.d;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }

    public final String toString() {
        return "WeatherSnapshot(temperature=" + this.a + ", symbol=" + this.b + ", updatedAt=" + this.c + ", problem=" + this.d + ")";
    }

    public wj(Float f, String str, long j, String str2) {
        str.getClass();
        this.a = f;
        this.b = str;
        this.c = j;
        this.d = str2;
    }
}
