package defpackage;

import android.os.Handler;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class p1 {
    public long a;
    public Object b;

    /* JADX WARN: Code duplicated, block: B:19:0x0049 A[EDGE_INSN: B:19:0x0049->B:20:0x004a BREAK  A[LOOP:0: B:12:0x0026->B:91:0x0026]] */
    public kd a(List list, lb lbVar, int i, long j) {
        Object objU0;
        kd kdVar = (kd) this.b;
        kd kdVar2 = null;
        Object obj = null;
        Object obj2 = null;
        if (kdVar == null) {
            kdVar = null;
            break;
        }
        long j2 = j - this.a;
        if (0 > j2 || j2 >= 2500 || list.isEmpty()) {
            kdVar = null;
            break;
        }
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                kdVar = null;
                break;
            }
            kd kdVar3 = (kd) it.next();
            if (kdVar3.b().equals(kdVar.b()) && kdVar3.c(kdVar.a)) {
                break;
            }
        }
        lb lbVar2 = kdVar != null ? new lb(kdVar.c, kdVar.a, kdVar.b, "", false, "", "") : lbVar;
        ArrayList arrayList = new ArrayList();
        for (Object obj3 : list) {
            if (!th.A(((kd) obj3).a)) {
                arrayList.add(obj3);
            }
        }
        HashSet hashSet = new HashSet();
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        int i2 = 0;
        int i3 = 0;
        while (i3 < size) {
            Object obj4 = arrayList.get(i3);
            i3++;
            kd kdVar4 = (kd) obj4;
            String strB = kdVar4.b();
            String strB2 = kdVar4.b();
            String str = kdVar4.a;
            str.getClass();
            Set set = nb.l;
            String strZ = pi.z(strB2, str);
            if (strZ == null) {
                strZ = th.J(str).toString();
            }
            if (hashSet.add(new yc(strB, strZ))) {
                arrayList2.add(obj4);
            }
        }
        List listG0 = i3.G0(arrayList2, k8.s(new w(19), new w(20)));
        if (!listG0.isEmpty()) {
            if (lbVar2 == null) {
                kdVar2 = (kd) (i < 0 ? i3.z0(listG0) : i3.u0(listG0));
            } else {
                String str2 = lbVar2.b;
                Iterator it2 = listG0.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        i2 = -1;
                        break;
                    }
                    kd kdVar5 = (kd) it2.next();
                    if (kdVar5.b().equals(lbVar2.a()) && kdVar5.c(str2)) {
                        break;
                    }
                    i2++;
                }
                if (i2 >= 0) {
                    kdVar2 = (kd) listG0.get(Math.floorMod(i2 + i, listG0.size()));
                } else {
                    Integer numValueOf = Integer.valueOf(k8.g(lbVar2.a()));
                    double dM0 = k8.M0(lbVar2.a(), str2);
                    if (i < 0) {
                        ListIterator listIterator = listG0.listIterator(listG0.size());
                        while (listIterator.hasPrevious()) {
                            Object objPrevious = listIterator.previous();
                            kd kdVar6 = (kd) objPrevious;
                            int iT = k8.t(Integer.valueOf(k8.g(kdVar6.b())), numValueOf);
                            if (iT == 0) {
                                if (k8.M0(kdVar6.b(), kdVar6.a) < dM0) {
                                    obj = objPrevious;
                                    break;
                                }
                            } else {
                                if (iT < 0) {
                                    obj = objPrevious;
                                    break;
                                }
                            }
                        }
                        kdVar2 = (kd) obj;
                        if (kdVar2 == null) {
                            objU0 = i3.z0(listG0);
                            kdVar2 = (kd) objU0;
                        }
                    } else {
                        for (Object obj5 : listG0) {
                            kd kdVar7 = (kd) obj5;
                            int iT2 = k8.t(Integer.valueOf(k8.g(kdVar7.b())), numValueOf);
                            if (iT2 == 0) {
                                if (k8.M0(kdVar7.b(), kdVar7.a) > dM0) {
                                    obj2 = obj5;
                                    break;
                                }
                            } else {
                                if (iT2 > 0) {
                                    obj2 = obj5;
                                    break;
                                }
                            }
                        }
                        kdVar2 = (kd) obj2;
                        if (kdVar2 == null) {
                            objU0 = i3.u0(listG0);
                            kdVar2 = (kd) objU0;
                        }
                    }
                }
            }
        }
        this.b = kdVar2;
        this.a = j;
        return kdVar2;
    }

    public void b(int i, y5 y5Var, y5 y5Var2) {
        long j = this.a;
        this.a = 1500 + j;
        Handler handler = ((s1) this.b).b;
        handler.postDelayed(new o1(this, y5Var, 0), j);
        if (i > 0) {
            handler.postDelayed(new o1(this, y5Var2, 1), (((long) i) * 60000) + j);
        }
    }
}
