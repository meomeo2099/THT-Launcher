package defpackage;

import android.media.session.MediaController;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class g2 implements Comparator {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ g2(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int i = this.a;
        Object obj3 = this.b;
        switch (i) {
            case 0:
                int iCompare = ((h0) obj3).compare(obj, obj2);
                if (iCompare != 0) {
                    return iCompare;
                }
                String name = ((File) obj).getName();
                name.getClass();
                Locale locale = Locale.ROOT;
                String lowerCase = name.toLowerCase(locale);
                lowerCase.getClass();
                String name2 = ((File) obj2).getName();
                name2.getClass();
                String lowerCase2 = name2.toLowerCase(locale);
                lowerCase2.getClass();
                return k8.t(lowerCase, lowerCase2);
            default:
                ArrayList arrayList = (ArrayList) obj3;
                return k8.t(Integer.valueOf(arrayList.indexOf(((MediaController) ((yc) obj).a).getSessionToken())), Integer.valueOf(arrayList.indexOf(((MediaController) ((yc) obj2).a).getSessionToken())));
        }
    }
}
