package defpackage;

import java.nio.charset.Charset;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class q2 {
    public static final Charset a;
    public static final Charset b;
    public static final Charset c;
    public static final Charset d;

    static {
        Charset charsetForName = Charset.forName("UTF-8");
        charsetForName.getClass();
        a = charsetForName;
        Charset.forName("UTF-16").getClass();
        Charset charsetForName2 = Charset.forName("UTF-16BE");
        charsetForName2.getClass();
        b = charsetForName2;
        Charset.forName("UTF-16LE").getClass();
        Charset charsetForName3 = Charset.forName("US-ASCII");
        charsetForName3.getClass();
        c = charsetForName3;
        Charset charsetForName4 = Charset.forName("ISO-8859-1");
        charsetForName4.getClass();
        d = charsetForName4;
    }
}
