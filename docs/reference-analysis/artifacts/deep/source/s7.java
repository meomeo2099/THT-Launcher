package defpackage;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 s7[], still in use, count: 1, list:
  (r0v1 s7[]) from 0x002a: CONSTRUCTOR (r0v1 s7[]) A[MD:(java.lang.Enum[]):void (m), WRAPPED] (LINE:43) call: y4.<init>(java.lang.Enum[]):void type: CONSTRUCTOR
    at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
    at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
    at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:101)
    at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:100)
    at jadx.core.utils.InsnRemover.removeAllAndUnbind(InsnRemover.java:257)
    at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:187)
    at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class s7 {
    d("OFF", "image_over_off"),
    /* JADX INFO: Fake field, exist only in values array */
    EF1("ALWAYS", "image_over_always"),
    e("CHANGE", "image_over_change");

    public static final l0 c = new l0();
    public static final /* synthetic */ y4 g;
    public final int a;
    public final String b;

    static {
        g = new y4(new s7[]{r0, r1, r2});
    }

    public s7(String str, String str2) {
        super(str, i);
        this.a = i;
        this.b = str2;
    }

    public static s7 valueOf(String str) {
        return (s7) Enum.valueOf(s7.class, str);
    }

    public static s7[] values() {
        return (s7[]) f.clone();
    }
}
