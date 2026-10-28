package defpackage;

import java.util.Locale;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class g7 implements j6 {
    public final /* synthetic */ int a;
    public final /* synthetic */ String b;

    public /* synthetic */ g7(int i, String str) {
        this.a = i;
        this.b = str;
    }

    @Override // defpackage.j6
    public final Object b(Object obj) {
        int i = this.a;
        boolean z = true;
        String str = this.b;
        switch (i) {
            case 0:
                String str2 = (String) obj;
                str2.getClass();
                return str + "-" + str2 + ".png";
            case 1:
                ld ldVar = (ld) obj;
                ldVar.getClass();
                if (str.length() != 0) {
                    String lowerCase = ldVar.b.toLowerCase(Locale.ROOT);
                    lowerCase.getClass();
                    String strReplace = lowerCase.replace((char) 1105, (char) 1077);
                    strReplace.getClass();
                    if (!th.v(strReplace, str, false)) {
                        z = false;
                    }
                }
                return Boolean.valueOf(z);
            default:
                ld ldVar2 = (ld) obj;
                ldVar2.getClass();
                String lowerCase2 = ldVar2.b.toLowerCase(Locale.ROOT);
                lowerCase2.getClass();
                String strReplace2 = lowerCase2.replace((char) 1105, (char) 1077);
                strReplace2.getClass();
                return Boolean.valueOf(!strReplace2.startsWith(str));
        }
    }
}
