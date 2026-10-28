package defpackage;

import java.util.List;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class fj implements j6 {
    public final /* synthetic */ int a;
    public final /* synthetic */ nj b;
    public final /* synthetic */ int c;
    public final /* synthetic */ int d;

    public /* synthetic */ fj(nj njVar, int i, int i2, int i3) {
        this.a = i3;
        this.b = njVar;
        this.c = i;
        this.d = i2;
    }

    @Override // defpackage.j6
    public final Object b(Object obj) {
        boolean zF;
        Float fJ;
        switch (this.a) {
            case 0:
                nj njVar = this.b;
                int i = this.c;
                int i2 = this.d;
                r4 r4Var = (r4) obj;
                r4Var.getClass();
                njVar.getClass();
                return Boolean.valueOf(nj.n(r4Var, i, i2));
            default:
                nj njVar2 = this.b;
                int i3 = this.c;
                int i4 = this.d;
                r4 r4Var2 = (r4) obj;
                r4Var2.getClass();
                List list = z2.a;
                Object objI = njVar2.i("temp:" + i3);
                Float fJ2 = objI instanceof Float ? (Float) objI : null;
                if (fJ2 == null && (fJ2 = z2.j(r4Var2.i(268828928, i3))) == null) {
                    fJ2 = (Float) njVar2.f.i.a.get(Integer.valueOf(i3));
                }
                List list2 = si.a;
                r4Var2.o(si.a("temperature_button_0_auto_when_climate_is", i4 > 0 ? "+" : "−") + (i3 == 4 ? " [4]" : ""));
                u2 u2Var = r4Var2.o;
                synchronized (u2Var) {
                    zF = false;
                    if (i4 == 1 || i4 == -1) {
                        try {
                            Integer numH = u2Var.h(268501248);
                            if (numH != null && numH.intValue() == 0) {
                                zF = u2Var.f(268501504, 1);
                            } else if (numH != null && numH.intValue() == 1 && (fJ = z2.j(fJ2)) != null) {
                                float fH = z2.h((i4 * 0.5f) + fJ.floatValue());
                                Float fValueOf = Float.valueOf(fH);
                                zF = r4Var2.E(fH, i3);
                                if (zF) {
                                    njVar2.d("temp:" + i3, fValueOf, 6000L);
                                }
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                }
                return Boolean.valueOf(zF);
        }
    }
}
