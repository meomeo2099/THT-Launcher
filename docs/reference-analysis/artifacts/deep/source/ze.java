package defpackage;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class ze {
    public static final ze a;
    public static final ze b;
    public static final /* synthetic */ ze[] c;

    static {
        ze zeVar = new ze("LANGUAGE", 0);
        a = zeVar;
        ze zeVar2 = new ze("LOCATION", 1);
        b = zeVar2;
        c = new ze[]{zeVar, zeVar2};
    }

    public static ze valueOf(String str) {
        return (ze) Enum.valueOf(ze.class, str);
    }

    public static ze[] values() {
        return (ze[]) c.clone();
    }
}
