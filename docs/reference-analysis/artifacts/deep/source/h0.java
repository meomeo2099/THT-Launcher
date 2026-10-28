package defpackage;

import java.io.File;
import java.util.Comparator;
import java.util.Locale;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class h0 implements Comparator {
    public final /* synthetic */ int a;

    public /* synthetic */ h0(int i) {
        this.a = i;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.a) {
            case 0:
                String str = ((e0) obj).a;
                Locale locale = Locale.ROOT;
                String lowerCase = str.toLowerCase(locale);
                lowerCase.getClass();
                String lowerCase2 = ((e0) obj2).a.toLowerCase(locale);
                lowerCase2.getClass();
                return k8.t(lowerCase, lowerCase2);
            case 1:
                return k8.t(Boolean.valueOf(!((File) obj).isDirectory()), Boolean.valueOf(!((File) obj2).isDirectory()));
            case 2:
                return k8.t(Long.valueOf(((pe) obj).b), Long.valueOf(((pe) obj2).b));
            case 3:
                String str2 = ((d7) obj).b;
                Locale locale2 = Locale.ROOT;
                String lowerCase3 = str2.toLowerCase(locale2);
                lowerCase3.getClass();
                String lowerCase4 = ((d7) obj2).b.toLowerCase(locale2);
                lowerCase4.getClass();
                return k8.t(lowerCase3, lowerCase4);
            case 4:
                return k8.t(Long.valueOf(((File) obj2).lastModified()), Long.valueOf(((File) obj).lastModified()));
            default:
                return k8.t(Long.valueOf(((hc) obj2).c), Long.valueOf(((hc) obj).c));
        }
    }
}
