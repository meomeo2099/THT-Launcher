package defpackage;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class rc {
    public static final rc a;
    public static final rc b;
    public static final rc c;
    public static final /* synthetic */ rc[] d;

    static {
        rc rcVar = new rc("NONE", 0);
        a = rcVar;
        rc rcVar2 = new rc("BACK", 1);
        b = rcVar2;
        rc rcVar3 = new rc("HOME", 2);
        c = rcVar3;
        d = new rc[]{rcVar, rcVar2, rcVar3};
    }

    public static rc valueOf(String str) {
        return (rc) Enum.valueOf(rc.class, str);
    }

    public static rc[] values() {
        return (rc[]) d.clone();
    }
}
