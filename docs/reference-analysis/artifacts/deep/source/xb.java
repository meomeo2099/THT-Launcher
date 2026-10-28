package defpackage;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class xb {
    public static final xb a;
    public static final xb b;
    public static final xb c;
    public static final xb d;
    public static final xb e;
    public static final /* synthetic */ xb[] f;

    static {
        xb xbVar = new xb("PLAY", 0);
        a = xbVar;
        xb xbVar2 = new xb("PAUSE", 1);
        b = xbVar2;
        xb xbVar3 = new xb("TOGGLE", 2);
        c = xbVar3;
        xb xbVar4 = new xb("NEXT", 3);
        d = xbVar4;
        xb xbVar5 = new xb("PREVIOUS", 4);
        e = xbVar5;
        f = new xb[]{xbVar, xbVar2, xbVar3, xbVar4, xbVar5};
    }

    public static xb valueOf(String str) {
        return (xb) Enum.valueOf(xb.class, str);
    }

    public static xb[] values() {
        return (xb[]) f.clone();
    }
}
