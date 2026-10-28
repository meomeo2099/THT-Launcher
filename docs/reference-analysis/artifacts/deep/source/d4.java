package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public enum d4 {
    TOP_LEFT(true, true),
    TOP_RIGHT(false, true),
    BOTTOM_LEFT(true, false),
    BOTTOM_RIGHT(false, false);

    public final boolean a;
    public final boolean b;

    d4(boolean z, boolean z2) {
        this.a = z;
        this.b = z2;
    }
}
