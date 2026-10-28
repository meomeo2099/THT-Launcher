package defpackage;

import java.io.Serializable;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class me implements Serializable {
    public final /* synthetic */ int a;
    public Object b;

    public me(int i) {
        this.a = i;
        switch (i) {
            case 1:
                Pattern patternCompile = Pattern.compile("\\s+(Казахстан|Беларусь|Азия|Kazakhstan|Belarus)$", 66);
                patternCompile.getClass();
                this.b = patternCompile;
                break;
        }
    }

    public boolean a(String str) {
        str.getClass();
        return ((Pattern) this.b).matcher(str).matches();
    }

    public final String toString() {
        switch (this.a) {
            case 0:
                return String.valueOf(this.b);
            default:
                String string = ((Pattern) this.b).toString();
                string.getClass();
                return string;
        }
    }

    public me(String str) {
        this.a = 1;
        Pattern patternCompile = Pattern.compile(str);
        patternCompile.getClass();
        this.b = patternCompile;
    }
}
