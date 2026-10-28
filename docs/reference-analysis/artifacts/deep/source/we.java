package defpackage;

import java.util.List;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class we {
    public final double[] a;
    public final List b;
    public final double c;
    public final double d;
    public final double e;
    public final double f;

    public we(double[] dArr, List list) {
        dArr.getClass();
        this.a = dArr;
        this.b = list;
        Double dB0 = i3.B0(l0.d(dArr));
        this.c = dB0 != null ? dB0.doubleValue() : 0.0d;
        Double dA0 = i3.A0(l0.d(dArr));
        this.d = dA0 != null ? dA0.doubleValue() : 0.0d;
        Double dB1 = i3.B0(l0.a(dArr));
        this.e = dB1 != null ? dB1.doubleValue() : 0.0d;
        Double dA1 = i3.A0(l0.a(dArr));
        this.f = dA1 != null ? dA1.doubleValue() : 0.0d;
    }
}
