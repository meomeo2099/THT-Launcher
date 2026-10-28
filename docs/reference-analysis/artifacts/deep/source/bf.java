package defpackage;

import android.content.res.Resources;
import java.util.Locale;
import java.util.Set;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class bf {
    public static final Set a = x0.Y0(new String[]{"ru", "be", "kk"});
    public static final Set b = x0.Y0(new Integer[]{19, 20, 21, 22, 23, 66, 160, 61, 62, 82, 4});
    public static volatile xe c;
    public static volatile boolean d;

    public static ze a(MainActivity mainActivity) {
        if (pi.b(mainActivity)) {
            return null;
        }
        String language = b().getLanguage();
        language.getClass();
        Locale locale = Locale.ROOT;
        locale.getClass();
        String lowerCase = language.toLowerCase(locale);
        lowerCase.getClass();
        if (!a.contains(lowerCase)) {
            return ze.a;
        }
        if (mainActivity.getSharedPreferences("panel", 0).getBoolean("region_locked_gps", false)) {
            return ze.b;
        }
        return null;
    }

    public static Locale b() {
        Object ffVar;
        try {
            ffVar = Resources.getSystem().getConfiguration().getLocales().get(0);
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        if (ffVar instanceof ff) {
            ffVar = null;
        }
        Locale locale = (Locale) ffVar;
        if (locale != null) {
            return locale;
        }
        Locale locale2 = Locale.getDefault();
        locale2.getClass();
        return locale2;
    }
}
