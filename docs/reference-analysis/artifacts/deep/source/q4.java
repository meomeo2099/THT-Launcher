package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class q4 implements n6, o6 {
    public final /* synthetic */ r4 a;

    public /* synthetic */ q4(r4 r4Var) {
        this.a = r4Var;
    }

    @Override // defpackage.o6
    public Object a(Object obj, Object obj2, Object obj3) {
        int iIntValue = ((Integer) obj).intValue();
        Integer num = (Integer) obj2;
        int iIntValue2 = ((Integer) obj3).intValue();
        r4 r4Var = this.a;
        return Boolean.valueOf(num == null ? r4Var.w(iIntValue, iIntValue2) : r4Var.x(iIntValue, num.intValue(), iIntValue2));
    }

    @Override // defpackage.n6
    public Object c(Object obj, Object obj2) {
        int iIntValue = ((Integer) obj).intValue();
        Integer num = (Integer) obj2;
        r4 r4Var = this.a;
        return num == null ? r4Var.j(iIntValue) : r4Var.k(iIntValue, num.intValue());
    }
}
