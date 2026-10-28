package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class dj implements j6 {
    public final /* synthetic */ int a;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ nj c;

    public /* synthetic */ dj(boolean z, nj njVar, int i) {
        this.a = i;
        this.b = z;
        this.c = njVar;
    }

    @Override // defpackage.j6
    public final Object b(Object obj) {
        boolean zC;
        boolean zW;
        int i = this.a;
        nj njVar = this.c;
        boolean z = this.b;
        r4 r4Var = (r4) obj;
        switch (i) {
            case 0:
                r4Var.getClass();
                if (z) {
                    Object objI = njVar.i("roof_tilt");
                    Boolean bool = objI instanceof Boolean ? (Boolean) objI : null;
                    if (bool != null ? bool.booleanValue() : r4Var.J(r4Var.t())) {
                        zC = r4Var.C(0);
                        if (zC) {
                            njVar.f(false, false, 0);
                        }
                    } else {
                        zC = r4Var.I();
                        if (zC) {
                            njVar.f(false, true, null);
                        }
                    }
                } else {
                    Object objI2 = njVar.i("roof_open");
                    Boolean bool2 = objI2 instanceof Boolean ? (Boolean) objI2 : null;
                    if (bool2 != null ? bool2.booleanValue() : r4Var.s(r4Var.t())) {
                        zC = r4Var.C(0);
                        if (zC) {
                            njVar.f(false, false, 0);
                        }
                    } else {
                        boolean zC2 = r4Var.C(100);
                        if (zC2) {
                            njVar.f(true, false, 100);
                        }
                        zC = zC2;
                    }
                }
                return Boolean.valueOf(zC);
            case 1:
                r4Var.getClass();
                boolean zA = r4Var.A(z);
                if (zA) {
                    njVar.d("curtain_open", Boolean.valueOf(z), 12000L);
                }
                return Boolean.valueOf(zA);
            default:
                r4Var.getClass();
                if (r4Var.l()) {
                    zW = r4Var.w(704709376, z ? r4Var.a.getSharedPreferences("panel", 0).getInt("ambient_last_color", 16777215) : 0);
                } else {
                    zW = r4Var.w(553979904, z ? 1 : 0);
                }
                if (zW) {
                    njVar.d("ambient", Boolean.valueOf(z), 6000L);
                }
                return Boolean.valueOf(zW);
        }
    }
}
