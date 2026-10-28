package defpackage;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class wc {
    public static final wc a;
    public static final /* synthetic */ wc[] b;

    /* JADX INFO: Fake field, exist only in values array */
    wc EF0;

    static {
        wc wcVar = new wc("SKIP", 0);
        wc wcVar2 = new wc("TERMINATE", 1);
        a = wcVar2;
        b = new wc[]{wcVar, wcVar2};
    }

    public static wc valueOf(String str) {
        return (wc) Enum.valueOf(wc.class, str);
    }

    public static wc[] values() {
        return (wc[]) b.clone();
    }
}
