package defpackage;

import java.util.List;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class h1 implements y5 {
    public final /* synthetic */ int a;
    public final /* synthetic */ r4 b;
    public final /* synthetic */ int c;

    public /* synthetic */ h1(r4 r4Var, int i, int i2) {
        this.a = i2;
        this.b = r4Var;
        this.c = i;
    }

    @Override // defpackage.y5
    public final Object a() {
        boolean zF;
        int i = this.a;
        boolean z = false;
        int i2 = this.c;
        r4 r4Var = this.b;
        switch (i) {
            case 0:
                zF = r4Var.o.f(268566784, i2 + 268566784);
                break;
            case 1:
                Integer numH = r4Var.o.h(268566784);
                if (numH == null) {
                    return null;
                }
                int iIntValue = numH.intValue();
                List list = z2.a;
                Integer numC = z2.c(Integer.valueOf(iIntValue));
                if (numC != null && numC.intValue() == i2) {
                    z = true;
                }
                return Boolean.valueOf(z);
            case 2:
                zF = r4Var.z(i2, 1);
                break;
            case 3:
                Integer numH2 = r4Var.o.h(i2);
                if (numH2 != null) {
                    return Boolean.valueOf(numH2.intValue() == 1);
                }
                return null;
            case 4:
                zF = r4Var.z(i2, 0);
                break;
            default:
                Integer numH3 = r4Var.o.h(i2);
                if (numH3 != null) {
                    return Boolean.valueOf(numH3.intValue() == 0);
                }
                return null;
        }
        return Boolean.valueOf(zF);
    }
}
