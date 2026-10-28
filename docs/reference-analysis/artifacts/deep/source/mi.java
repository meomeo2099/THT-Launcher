package defpackage;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class mi {
    public static final mi a;
    public static final mi b;
    public static final mi c;
    public static final /* synthetic */ mi[] d;

    static {
        mi miVar = new mi("NONE", 0);
        a = miVar;
        mi miVar2 = new mi("SENSOR", 1);
        b = miVar2;
        mi miVar3 = new mi("VENDOR", 2);
        c = miVar3;
        d = new mi[]{miVar, miVar2, miVar3};
    }

    public static mi valueOf(String str) {
        return (mi) Enum.valueOf(mi.class, str);
    }

    public static mi[] values() {
        return (mi[]) d.clone();
    }
}
