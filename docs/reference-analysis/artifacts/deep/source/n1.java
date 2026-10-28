package defpackage;

import java.util.List;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class n1 implements y5 {
    public final /* synthetic */ int a;
    public final /* synthetic */ s1 b;
    public final /* synthetic */ r4 c;

    public /* synthetic */ n1(s1 s1Var, r4 r4Var, int i) {
        this.a = i;
        this.b = s1Var;
        this.c = r4Var;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.y5
    public final Object a() {
        int i = this.a;
        ti tiVar = ti.a;
        final r4 r4Var = this.c;
        final s1 s1Var = this.b;
        final int i2 = 1;
        char c = 1;
        char c2 = 1;
        char c3 = 1;
        final boolean z = false;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        Object[] objArr3 = 0;
        switch (i) {
            case 0:
                z0 z0Var = (z0) s1Var.f;
                if (z0Var.b("airing", false)) {
                    Float fH = s1Var.h(r4Var);
                    int iA = z0Var.a(15, "airing_temp");
                    if (fH == null) {
                        si.a("ventilation_skipped_outside_0", "?");
                    } else if (fH.floatValue() >= iA) {
                        boolean zI = z0Var.a(0, "airing_mode") == 0 ? r4Var.I() : r4Var.C(100);
                        int iS = pi.s(z0Var.a(4, "airing_fan"), 0, 8);
                        if (iS > 0) {
                            List list = si.a;
                            r4Var.o(si.a("ventilation_automation_speed_0", Integer.valueOf(iS)));
                            s1Var.b("airing fan", new h1(r4Var, iS, 0), new h1(r4Var, iS, 1));
                        }
                        if (zI) {
                            int iA2 = z0Var.a(3, "airing_minutes");
                            d1 d1Var = new d1(r4Var, 2);
                            if (iA2 > 0) {
                                s1Var.b.postDelayed(new k1(s1Var, d1Var), ((long) iA2) * 60000);
                            }
                        }
                    } else {
                        si.a("ventilation_skipped_outside_0", fH);
                    }
                }
                break;
            case 1:
                z0 z0Var2 = (z0) s1Var.f;
                if (z0Var2.b("climate", false)) {
                    r4Var.o(si.a("afterstart_automation_auto", new Object[0]));
                    List list2 = z2.a;
                    final float fH2 = z2.h(z0Var2.a(z0Var2.a(22, "climate_temp") * 2, "climate_temp_half") / 2.0f);
                    s1Var.b("climate AUTO", new d1(r4Var, 0), new d1(r4Var, 1));
                    s1Var.b.postDelayed(new Runnable() { // from class: e1
                        @Override // java.lang.Runnable
                        public final void run() {
                            final int i3 = 0;
                            final r4 r4Var2 = r4Var;
                            final float f = fH2;
                            final int i4 = 1;
                            s1Var.b("climate temperature", new y5() { // from class: l1
                                @Override // defpackage.y5
                                public final Object a() {
                                    int i5 = i3;
                                    float f2 = f;
                                    r4 r4Var3 = r4Var2;
                                    switch (i5) {
                                        case 0:
                                            return Boolean.valueOf(r4Var3.E(f2, 1));
                                        default:
                                            Float f3 = (Float) r4Var3.q().a.get(1);
                                            if (f3 != null) {
                                                return Boolean.valueOf(Math.abs(f3.floatValue() - f2) < 0.26f);
                                            }
                                            return null;
                                    }
                                }
                            }, new y5() { // from class: l1
                                @Override // defpackage.y5
                                public final Object a() {
                                    int i5 = i4;
                                    float f2 = f;
                                    r4 r4Var3 = r4Var2;
                                    switch (i5) {
                                        case 0:
                                            return Boolean.valueOf(r4Var3.E(f2, 1));
                                        default:
                                            Float f3 = (Float) r4Var3.q().a.get(1);
                                            if (f3 != null) {
                                                return Boolean.valueOf(Math.abs(f3.floatValue() - f2) < 0.26f);
                                            }
                                            return null;
                                    }
                                }
                            });
                        }
                    }, 1500L);
                }
                break;
            case 2:
                final char c4 = c3 == true ? 1 : 0;
                final Object[] objArr4 = objArr == true ? 1 : 0;
                y5 y5Var = new y5() { // from class: j1
                    /* JADX WARN: Type inference fix 'apply assigned field type' failed
                    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                        at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
                        at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                        at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                        at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                        at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                        at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                        at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                     */
                    @Override // defpackage.y5
                    public final Object a() {
                        int i3 = objArr4;
                        boolean z2 = c4;
                        r4 r4Var2 = r4Var;
                        switch (i3) {
                            case 0:
                                return Boolean.valueOf(r4Var2.D(z2 ? 1 : 0));
                            default:
                                List list3 = z2.a;
                                Integer numJ = r4Var2.j(269025536);
                                if (numJ == null) {
                                    numJ = r4Var2.k(269025536, 1);
                                }
                                Integer numI = z2.i(numJ);
                                if (numI != null) {
                                    return Boolean.valueOf((numI.intValue() > 0) == z2);
                                }
                                return null;
                        }
                    }
                };
                final char c5 = c2 == true ? 1 : 0;
                final char c6 = c == true ? 1 : 0;
                s1Var.b("steering", y5Var, new y5() { // from class: j1
                    /* JADX WARN: Type inference fix 'apply assigned field type' failed
                    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                        at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
                        at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                        at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                        at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                        at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                        at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                        at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                     */
                    @Override // defpackage.y5
                    public final Object a() {
                        int i3 = c6;
                        boolean z2 = c5;
                        r4 r4Var2 = r4Var;
                        switch (i3) {
                            case 0:
                                return Boolean.valueOf(r4Var2.D(z2 ? 1 : 0));
                            default:
                                List list3 = z2.a;
                                Integer numJ = r4Var2.j(269025536);
                                if (numJ == null) {
                                    numJ = r4Var2.k(269025536, 1);
                                }
                                Integer numI = z2.i(numJ);
                                if (numI != null) {
                                    return Boolean.valueOf((numI.intValue() > 0) == z2);
                                }
                                return null;
                        }
                    }
                });
                break;
            default:
                final Object[] objArr5 = objArr3 == true ? 1 : 0;
                y5 y5Var2 = new y5() { // from class: j1
                    /* JADX WARN: Type inference fix 'apply assigned field type' failed
                    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                        at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
                        at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                        at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                        at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                        at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                        at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                        at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                     */
                    @Override // defpackage.y5
                    public final Object a() {
                        int i3 = objArr5;
                        boolean z2 = z;
                        r4 r4Var2 = r4Var;
                        switch (i3) {
                            case 0:
                                return Boolean.valueOf(r4Var2.D(z2 ? 1 : 0));
                            default:
                                List list3 = z2.a;
                                Integer numJ = r4Var2.j(269025536);
                                if (numJ == null) {
                                    numJ = r4Var2.k(269025536, 1);
                                }
                                Integer numI = z2.i(numJ);
                                if (numI != null) {
                                    return Boolean.valueOf((numI.intValue() > 0) == z2);
                                }
                                return null;
                        }
                    }
                };
                final Object[] objArr6 = objArr2 == true ? 1 : 0;
                s1Var.b("steering", y5Var2, new y5() { // from class: j1
                    /* JADX WARN: Type inference fix 'apply assigned field type' failed
                    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                        at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
                        at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                        at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                        at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                        at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                        at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                        at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                     */
                    @Override // defpackage.y5
                    public final Object a() {
                        int i3 = i2;
                        boolean z2 = objArr6;
                        r4 r4Var2 = r4Var;
                        switch (i3) {
                            case 0:
                                return Boolean.valueOf(r4Var2.D(z2 ? 1 : 0));
                            default:
                                List list3 = z2.a;
                                Integer numJ = r4Var2.j(269025536);
                                if (numJ == null) {
                                    numJ = r4Var2.k(269025536, 1);
                                }
                                Integer numI = z2.i(numJ);
                                if (numI != null) {
                                    return Boolean.valueOf((numI.intValue() > 0) == z2);
                                }
                                return null;
                        }
                    }
                });
                break;
        }
        return tiVar;
    }
}
