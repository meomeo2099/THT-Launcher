package defpackage;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class uj {
    public static final uj a;
    public static final uj b;
    public static final uj c;
    public static final uj d;
    public static final uj e;
    public static final uj f;
    public static final uj g;
    public static final uj h;
    public static final uj i;
    public static final /* synthetic */ uj[] j;

    static {
        uj ujVar = new uj("CLEAR", 0);
        a = ujVar;
        uj ujVar2 = new uj("PARTLY", 1);
        b = ujVar2;
        uj ujVar3 = new uj("CLOUDY", 2);
        c = ujVar3;
        uj ujVar4 = new uj("RAIN", 3);
        d = ujVar4;
        uj ujVar5 = new uj("SNOW", 4);
        e = ujVar5;
        uj ujVar6 = new uj("SLEET", 5);
        f = ujVar6;
        uj ujVar7 = new uj("FOG", 6);
        g = ujVar7;
        uj ujVar8 = new uj("THUNDER", 7);
        h = ujVar8;
        uj ujVar9 = new uj("UNKNOWN", 8);
        i = ujVar9;
        j = new uj[]{ujVar, ujVar2, ujVar3, ujVar4, ujVar5, ujVar6, ujVar7, ujVar8, ujVar9};
    }

    public static uj valueOf(String str) {
        return (uj) Enum.valueOf(uj.class, str);
    }

    public static uj[] values() {
        return (uj[]) j.clone();
    }
}
