package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class ci extends bi {
    public static String r(String str, char c, char c2) {
        str.getClass();
        String strReplace = str.replace(c, c2);
        strReplace.getClass();
        return strReplace;
    }

    public static String s(String str, String str2, String str3) {
        int iW = th.w(0, str, str2, false);
        if (iW < 0) {
            return str;
        }
        int length = str2.length();
        int i = length >= 1 ? length : 1;
        int length2 = str3.length() + (str.length() - length);
        if (length2 < 0) {
            throw new OutOfMemoryError();
        }
        StringBuilder sb = new StringBuilder(length2);
        int i2 = 0;
        do {
            sb.append((CharSequence) str, i2, iW);
            sb.append(str3);
            i2 = iW + length;
            if (iW >= str.length()) {
                break;
            }
            iW = th.w(iW + i, str, str2, false);
        } while (iW > 0);
        sb.append((CharSequence) str, i2, str.length());
        return sb.toString();
    }

    public static boolean t(String str, String str2) {
        str.getClass();
        str2.getClass();
        return str.startsWith(str2);
    }
}
