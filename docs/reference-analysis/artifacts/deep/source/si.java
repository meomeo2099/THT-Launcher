package defpackage;

import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class si {
    public static final List a = j3.i0("ru", "en", "fr", "ar", "kk");
    public static volatile ri b;
    public static final me c;

    static {
        Locale localeForLanguageTag = Locale.forLanguageTag("ru");
        localeForLanguageTag.getClass();
        b = new ri("ru", localeForLanguageTag, qi.a);
        c = new me("\\{(\\d+)\\}");
    }

    public static String a(String str, Object... objArr) {
        str.getClass();
        ri riVar = b;
        String str2 = (String) riVar.c.get(str);
        if (str2 != null || (str2 = (String) qi.a.get(str)) != null) {
            str = str2;
        }
        if (objArr.length == 0) {
            return str;
        }
        me meVar = c;
        q0 q0Var = new q0(objArr, riVar, 9);
        meVar.getClass();
        Matcher matcher = ((Pattern) meVar.b).matcher(str);
        matcher.getClass();
        int i = 0;
        jb jbVar = !matcher.find(0) ? null : new jb(matcher, str);
        if (jbVar == null) {
            return str.toString();
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        do {
            Matcher matcher2 = jbVar.a;
            sb.append((CharSequence) str, i, pi.f0(matcher2.start(), matcher2.end()).a);
            sb.append((CharSequence) q0Var.b(jbVar));
            Matcher matcher3 = jbVar.a;
            i = pi.f0(matcher3.start(), matcher3.end()).b + 1;
            jbVar = jbVar.a();
            if (i >= length) {
                break;
            }
        } while (jbVar != null);
        if (i < length) {
            sb.append((CharSequence) str, i, length);
        }
        return sb.toString();
    }

    public static String b() {
        return b.a;
    }
}
