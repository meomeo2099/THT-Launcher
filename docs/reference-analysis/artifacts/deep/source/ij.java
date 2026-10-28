package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ij implements j6 {
    public final /* synthetic */ int a;
    public final /* synthetic */ int b;
    public final /* synthetic */ nj c;

    public /* synthetic */ ij(int i, nj njVar, int i2) {
        this.a = i2;
        this.b = i;
        this.c = njVar;
    }

    @Override // defpackage.j6
    public final Object b(Object obj) {
        int i = this.a;
        boolean zY = true;
        nj njVar = this.c;
        int i2 = this.b;
        r4 r4Var = (r4) obj;
        switch (i) {
            case 0:
                r4Var.getClass();
                zY = !r4Var.l() && r4Var.w(704709120, i2) && r4Var.w(537526784, 537526785);
                if (zY) {
                    njVar.d("ambient", Boolean.TRUE, 6000L);
                }
                return Boolean.valueOf(zY);
            case 1:
                r4Var.getClass();
                int i3 = i2 & 16777215;
                if (r4Var.l()) {
                    r4Var.y(705167616, 705167617);
                    zY = r4Var.y(704709376, i3);
                } else if (!r4Var.w(704905472, i3) || !r4Var.w(537526784, 537526787)) {
                    zY = false;
                }
                if (zY && i3 != 0) {
                    r4Var.a.getSharedPreferences("panel", 0).edit().putInt("ambient_last_color", i3).apply();
                }
                if (zY) {
                    njVar.d("ambient", Boolean.TRUE, 6000L);
                }
                return Boolean.valueOf(zY);
            default:
                r4Var.getClass();
                int iS = pi.s(i2, 0, 100);
                boolean zC = r4Var.C(iS);
                if (zC) {
                    njVar.f(iS > 0, false, Integer.valueOf(iS));
                }
                return Boolean.valueOf(zC);
        }
    }
}
