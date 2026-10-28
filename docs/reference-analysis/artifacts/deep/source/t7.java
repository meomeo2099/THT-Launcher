package defpackage;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 t7[], still in use, count: 1, list:
  (r0v1 t7[]) from 0x003e: CONSTRUCTOR (r0v1 t7[]) A[MD:(java.lang.Enum[]):void (m), WRAPPED] (LINE:63) call: y4.<init>(java.lang.Enum[]):void type: CONSTRUCTOR
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
public final class t7 {
    d("none", "image_show_none"),
    e("icon", "image_show_icon"),
    /* JADX INFO: Fake field, exist only in values array */
    EF2("glow", "image_show_glow"),
    f("value", "image_show_value");

    public static final l0 c = new l0();
    public static final /* synthetic */ y4 h;
    public final String a;
    public final String b;

    static {
        h = new y4(new t7[]{r0, r1, r2, r3});
    }

    public t7(String str, String str2) {
        super(str, i);
        this.a = str;
        this.b = str2;
    }

    public static t7 valueOf(String str) {
        return (t7) Enum.valueOf(t7.class, str);
    }

    public static t7[] values() {
        return (t7[]) g.clone();
    }
}
