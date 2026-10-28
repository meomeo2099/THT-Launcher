package defpackage;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class fg implements j6 {
    public final /* synthetic */ int a;

    public /* synthetic */ fg(int i) {
        this.a = i;
    }

    public static /* synthetic */ void a(int i, String str) {
        throw new IllegalArgumentException((str + i + ((Object) " is less than zero.")).toString());
    }

    @Override // defpackage.j6
    public Object b(Object obj) {
        Boolean boolValueOf;
        switch (this.a) {
            case 0:
                ld ldVar = (ld) obj;
                ldVar.getClass();
                return Integer.valueOf(-ldVar.e.size());
            case 1:
                ld ldVar2 = (ld) obj;
                ldVar2.getClass();
                return ldVar2.b;
            case 2:
                Byte b = (Byte) obj;
                b.byteValue();
                return String.format("%02x", Arrays.copyOf(new Object[]{b}, 1));
            case 3:
                Integer num = (Integer) obj;
                num.getClass();
                return si.a("0_l", num);
            case 4:
            default:
                return String.format("%02X", Arrays.copyOf(new Object[]{Integer.valueOf(((Byte) obj).byteValue() & 255)}, 1));
            case 5:
                jb jbVar = (jb) obj;
                jbVar.getClass();
                String strGroup = jbVar.a.group();
                strGroup.getClass();
                return strGroup;
            case 6:
                r4 r4Var = (r4) obj;
                r4Var.getClass();
                int i = !k8.e(r4Var.n(), Boolean.TRUE) ? 1 : 0;
                int i2 = o2.c;
                return Boolean.valueOf(r4Var.x(i2, Integer.MIN_VALUE, i) || r4Var.w(i2, i));
            case 7:
                r4 r4Var2 = (r4) obj;
                r4Var2.getClass();
                int i3 = o2.d;
                Integer numK = r4Var2.k(i3, Integer.MIN_VALUE);
                if (numK == null) {
                    numK = r4Var2.j(i3);
                }
                List list = o2.e;
                list.getClass();
                int iIntValue = ((Number) list.get((list.indexOf(numK) + 1) % list.size())).intValue();
                return Boolean.valueOf(r4Var2.x(i3, Integer.MIN_VALUE, iIntValue) || r4Var2.w(i3, iIntValue));
            case 8:
                r4 r4Var3 = (r4) obj;
                r4Var3.getClass();
                return Boolean.valueOf(r4Var3.x(o2.a, o2.b, 1));
            case 9:
                r4 r4Var4 = (r4) obj;
                r4Var4.getClass();
                return Boolean.valueOf(r4Var4.x(o2.n, 1, 1));
            case 10:
                r4 r4Var5 = (r4) obj;
                r4Var5.getClass();
                int i4 = o2.a;
                int i5 = o2.b;
                Integer numK2 = r4Var5.k(i4, i5);
                if (numK2 != null) {
                    boolValueOf = Boolean.valueOf(numK2.intValue() == 1);
                } else {
                    boolValueOf = null;
                }
                return Boolean.valueOf(r4Var5.x(i4, i5, !k8.e(boolValueOf, Boolean.TRUE) ? 1 : 0));
        }
    }
}
