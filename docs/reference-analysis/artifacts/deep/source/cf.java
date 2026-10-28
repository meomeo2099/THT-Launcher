package defpackage;

import android.widget.LinearLayout;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class cf implements j6 {
    public final /* synthetic */ int a;
    public final /* synthetic */ float b;

    public /* synthetic */ cf(float f, int i) {
        this.a = i;
        this.b = f;
    }

    @Override // defpackage.j6
    public final Object b(Object obj) {
        int i = this.a;
        boolean z = false;
        float f = this.b;
        switch (i) {
            case 0:
                int iIntValue = ((Integer) obj).intValue();
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                int i2 = (int) (48.0f * f);
                layoutParams.setMargins(i2, (int) (iIntValue * f), i2, 0);
                return layoutParams;
            default:
                r4 r4Var = (r4) obj;
                r4Var.getClass();
                float fR = pi.r(f, 0.0f, 1.0f);
                yc ycVarP = r4Var.p();
                float fFloatValue = ((Number) ycVarP.a).floatValue();
                float fFloatValue2 = ((((Number) ycVarP.b).floatValue() - fFloatValue) * fR) + fFloatValue;
                Iterator it = j3.i0(538247936, 538248192).iterator();
                while (true) {
                    boolean z2 = false;
                    while (true) {
                        if (!it.hasNext()) {
                            if (!x0.Y0(new String[]{"active", "notactive", "unknown"}).contains(r4Var.F(688063744))) {
                                z = z2;
                            } else if (r4Var.B(688063744, Integer.MIN_VALUE, fR * 15.0f) || z2) {
                                z = true;
                            }
                            return Boolean.valueOf(z);
                        }
                        int iIntValue2 = ((Number) it.next()).intValue();
                        if (x0.Y0(new String[]{"active", "notactive", "unknown"}).contains(r4Var.F(iIntValue2))) {
                            if (k8.e(r4Var.b("setCustomizeFunctionValue", new Class[]{r4Var.f, r4Var.g}, true, Integer.valueOf(iIntValue2), Float.valueOf(fFloatValue2)), Boolean.TRUE) || r4Var.w(iIntValue2, Math.round(fFloatValue2)) || z2) {
                                z2 = true;
                            }
                        }
                    }
                }
                break;
        }
    }
}
