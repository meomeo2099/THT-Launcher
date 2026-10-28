package defpackage;

import android.os.Parcel;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class zd implements j6 {
    public final /* synthetic */ int a;
    public final /* synthetic */ int b;

    public /* synthetic */ zd(int i, int i2) {
        this.a = i2;
        this.b = i;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
        at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // defpackage.j6
    public final Object b(Object obj) {
        int iIntValue;
        Boolean boolValueOf = null;
        zF = false;
        int i = 0;
        boolean zF = false;
        switch (this.a) {
            case 0:
                int i2 = this.b;
                Parcel parcel = (Parcel) obj;
                parcel.getClass();
                parcel.writeInt(i2);
                return ti.a;
            case 1:
                int i3 = this.b;
                Parcel parcel2 = (Parcel) obj;
                parcel2.getClass();
                parcel2.writeInt(i3);
                return ti.a;
            case 2:
                int i4 = this.b;
                r4 r4Var = (r4) obj;
                r4Var.getClass();
                Integer numK = r4Var.k(i4, Integer.MIN_VALUE);
                if (numK == null) {
                    numK = r4Var.j(i4);
                }
                if (numK != null) {
                    boolValueOf = Boolean.valueOf(numK.intValue() != 0);
                }
                int i5 = !k8.e(boolValueOf, Boolean.TRUE) ? 1 : 0;
                return Boolean.valueOf(r4Var.x(i4, Integer.MIN_VALUE, i5) || r4Var.w(i4, i5));
            case 3:
                int i6 = this.b;
                r4 r4Var2 = (r4) obj;
                r4Var2.getClass();
                if (x0.Y0(new Integer[]{553979904, 704709120, 704708864}).contains(704708864)) {
                    return Boolean.valueOf(r4Var2.w(704708864, i6));
                }
                w.a("Failed requirement.");
                return null;
            default:
                int i7 = this.b;
                r4 r4Var3 = (r4) obj;
                r4Var3.getClass();
                List list = si.a;
                List list2 = z2.a;
                r4Var3.o(si.a("toggle_0_using_vehicle_state", z2.e().get(Integer.valueOf(i7))));
                u2 u2Var = r4Var3.o;
                synchronized (u2Var) {
                    if (j3.i0(268501248, 268501504, 268501760, 268632320, 268697856, 268698368, 269747456, 269747200, 268829952).contains(Integer.valueOf(i7))) {
                        Integer numH = u2Var.h(i7);
                        if (numH != null) {
                            iIntValue = numH.intValue();
                        } else if (i7 == 268697856 || i7 == 269747200) {
                            iIntValue = 0;
                        }
                        if (i7 == 268632320) {
                            i = 268632321;
                            if (iIntValue == 268632321) {
                                i = 268632322;
                            }
                        } else if (iIntValue != 1) {
                            i = 1;
                        }
                        zF = u2Var.f(i7, i);
                    }
                }
                return Boolean.valueOf(zF);
        }
    }
}
