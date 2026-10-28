package defpackage;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class u3 {
    public static final u3 a;
    public static final /* synthetic */ u3[] b;

    static {
        u3 u3Var = new u3("COROUTINE_SUSPENDED", 0);
        a = u3Var;
        b = new u3[]{u3Var, new u3("UNDECIDED", 1), new u3("RESUMED", 2)};
    }

    public static u3 valueOf(String str) {
        return (u3) Enum.valueOf(u3.class, str);
    }

    public static u3[] values() {
        return (u3[]) b.clone();
    }
}
