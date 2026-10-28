package defpackage;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class l5 {
    public static final l5 a;
    public static final l5 b;
    public static final /* synthetic */ l5[] c;

    static {
        l5 l5Var = new l5("TOP_DOWN", 0);
        a = l5Var;
        l5 l5Var2 = new l5("BOTTOM_UP", 1);
        b = l5Var2;
        c = new l5[]{l5Var, l5Var2};
    }

    public static l5 valueOf(String str) {
        return (l5) Enum.valueOf(l5.class, str);
    }

    public static l5[] values() {
        return (l5[]) c.clone();
    }
}
