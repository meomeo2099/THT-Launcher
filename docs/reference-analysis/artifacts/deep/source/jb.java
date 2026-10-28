package defpackage;

import java.util.regex.Matcher;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class jb {
    public final Matcher a;
    public final String b;
    public ib c;

    public jb(Matcher matcher, String str) {
        this.a = matcher;
        this.b = str;
    }

    public final jb a() {
        Matcher matcher = this.a;
        int iEnd = matcher.end() + (matcher.end() == matcher.start() ? 1 : 0);
        String str = this.b;
        if (iEnd > str.length()) {
            return null;
        }
        Matcher matcher2 = matcher.pattern().matcher(str);
        matcher2.getClass();
        if (matcher2.find(iEnd)) {
            return new jb(matcher2, str);
        }
        return null;
    }
}
