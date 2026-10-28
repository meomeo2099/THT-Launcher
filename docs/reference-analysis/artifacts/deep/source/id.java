package defpackage;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class id {
    public static final id a;
    public static final id b;
    public static final id c;
    public static final /* synthetic */ id[] d;

    static {
        id idVar = new id("RETURN", 0);
        a = idVar;
        id idVar2 = new id("WAIT", 1);
        b = idVar2;
        id idVar3 = new id("STOP", 2);
        c = idVar3;
        d = new id[]{idVar, idVar2, idVar3};
    }

    public static id valueOf(String str) {
        return (id) Enum.valueOf(id.class, str);
    }

    public static id[] values() {
        return (id[]) d.clone();
    }
}
