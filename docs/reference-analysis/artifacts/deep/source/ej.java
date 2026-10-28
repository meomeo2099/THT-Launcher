package defpackage;

import java.util.List;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ej implements j6 {
    public final /* synthetic */ int a;
    public final /* synthetic */ nj b;

    public /* synthetic */ ej(nj njVar, int i) {
        this.a = i;
        this.b = njVar;
    }

    /* JADX WARN: Code duplicated, block: B:48:0x00e1 A[PHI: r3
      0x00e1: PHI (r3v24 java.lang.Integer) = (r3v10 java.lang.Integer), (r3v13 java.lang.Integer), (r3v19 java.lang.Integer) binds: [B:47:0x00df, B:53:0x00f4, B:56:0x010b] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // defpackage.j6
    public final Object b(Object obj) {
        int iIntValue;
        boolean zBooleanValue;
        int iIntValue2;
        int i = this.a;
        Boolean bool = null;
        num = null;
        num = null;
        Integer num = null;
        bool = null;
        boolean zBooleanValue2 = false;
        boolean z = false;
        nj njVar = this.b;
        r4 r4Var = (r4) obj;
        switch (i) {
            case 0:
                r4Var.getClass();
                Object objI = njVar.i("start_stop");
                Boolean bool2 = objI instanceof Boolean ? (Boolean) objI : null;
                if (bool2 != null) {
                    zBooleanValue2 = bool2.booleanValue();
                } else {
                    Integer numJ = r4Var.j(537002240);
                    if (numJ != null && numJ.intValue() == 0) {
                        bool = Boolean.FALSE;
                    } else if (numJ != null && numJ.intValue() == 1) {
                        bool = Boolean.TRUE;
                    }
                    if (bool != null || (bool = njVar.f.q) != null) {
                        zBooleanValue2 = bool.booleanValue();
                    }
                }
                boolean z2 = !zBooleanValue2;
                boolean zW = r4Var.w(537002240, z2 ? 1 : 0);
                if (zW) {
                    njVar.d("start_stop", Boolean.valueOf(z2), 6000L);
                }
                return Boolean.valueOf(zW);
            case 1:
                r4Var.getClass();
                boolean zC = r4Var.C(0);
                if (zC) {
                    njVar.f(false, false, 0);
                }
                return Boolean.valueOf(zC);
            case 2:
                r4Var.getClass();
                boolean zC2 = r4Var.C(100);
                if (zC2) {
                    njVar.f(true, false, 100);
                }
                return Boolean.valueOf(zC2);
            case 3:
                r4Var.getClass();
                List list = z2.a;
                Object objI2 = njVar.i("climate:269025536");
                Integer numI = z2.i(objI2 instanceof Integer ? (Integer) objI2 : null);
                if (numI == null) {
                    Integer numJ2 = r4Var.j(269025536);
                    if (numJ2 == null) {
                        numJ2 = r4Var.k(269025536, 1);
                    }
                    numI = z2.i(numJ2);
                    if (numI == null && (numI = z2.i((Integer) njVar.f.i.f.get(269025536))) == null) {
                        iIntValue = 0;
                    } else {
                        iIntValue = numI.intValue();
                    }
                } else {
                    iIntValue = numI.intValue();
                }
                int i2 = iIntValue > 0 ? 0 : 1;
                boolean zD = r4Var.D(i2);
                if (zD) {
                    njVar.d("climate:269025536", Integer.valueOf(i2 != 0 ? i2 + 269025536 : 0), 6000L);
                }
                return Boolean.valueOf(zD);
            case 4:
                r4Var.getClass();
                Object objI3 = njVar.i("curtain_open");
                Boolean bool3 = objI3 instanceof Boolean ? (Boolean) objI3 : null;
                if (bool3 != null) {
                    zBooleanValue = bool3.booleanValue();
                } else {
                    Integer numK = r4Var.k(553846272, 8);
                    if (numK != null && (iIntValue2 = numK.intValue()) >= 0 && iIntValue2 < 101) {
                        num = numK;
                    }
                    if (num != null && num.intValue() == 0) {
                        r4Var.A = false;
                    } else {
                        z = (num == null || num.intValue() < 50) ? r4Var.A : true;
                    }
                    zBooleanValue = z;
                }
                boolean z3 = !zBooleanValue;
                boolean zA = r4Var.A(z3);
                if (zA) {
                    njVar.d("curtain_open", Boolean.valueOf(z3), 12000L);
                }
                return Boolean.valueOf(zA);
            case 5:
                r4Var.getClass();
                boolean zI = r4Var.I();
                if (zI) {
                    njVar.f(false, true, null);
                }
                return Boolean.valueOf(zI);
            default:
                r4Var.getClass();
                List list2 = z2.a;
                Integer numH = r4Var.o.h(268894464);
                if (numH == null) {
                    numH = (Integer) njVar.f.i.f.get(268894464);
                }
                List list3 = z2.a;
                Integer numA = z2.a(numH);
                list3.getClass();
                int iIntValue3 = ((Number) list3.get((list3.indexOf(numA) + 1) % list3.size())).intValue();
                njVar.getClass();
                return Boolean.valueOf(nj.n(r4Var, 268894464, iIntValue3));
        }
    }
}
