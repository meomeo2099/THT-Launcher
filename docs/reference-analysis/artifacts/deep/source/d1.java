package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class d1 implements y5 {
    public final /* synthetic */ int a;
    public final /* synthetic */ r4 b;

    public /* synthetic */ d1(r4 r4Var, int i) {
        this.a = i;
        this.b = r4Var;
    }

    @Override // defpackage.y5
    public final Object a() {
        int i = this.a;
        r4 r4Var = this.b;
        switch (i) {
            case 0:
                return Boolean.valueOf(r4Var.z(268501504, 1));
            case 1:
                Integer numH = r4Var.o.h(268501504);
                if (numH != null) {
                    return Boolean.valueOf(numH.intValue() == 1);
                }
                return null;
            case 2:
                r4Var.C(0);
                return ti.a;
            default:
                return Boolean.valueOf(r4Var.l());
        }
    }
}
