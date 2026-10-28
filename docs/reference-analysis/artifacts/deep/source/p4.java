package defpackage;

import android.os.SystemClock;
import android.util.Log;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class p4 implements InvocationHandler {
    public final /* synthetic */ int a;
    public final /* synthetic */ r4 b;

    public /* synthetic */ p4(r4 r4Var, int i) {
        this.a = i;
        this.b = r4Var;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        ua uaVar;
        Object obj2;
        int i = this.a;
        r4 r4Var = this.b;
        switch (i) {
            case 0:
                String name = method.getName();
                if (name == null) {
                    return null;
                }
                switch (name.hashCode()) {
                    case -1787463558:
                        if (!name.equals("onFunctionValueChanged")) {
                            return null;
                        }
                        Object objT0 = objArr != null ? x0.T0(objArr, 0) : null;
                        Integer num = objT0 instanceof Integer ? (Integer) objT0 : null;
                        List list = z2.a;
                        Set setKeySet = z2.e().keySet();
                        setKeySet.getClass();
                        if (i3.r0(setKeySet, num)) {
                            List list2 = si.a;
                            r4Var.K(si.a("vehicle_0_1_2", z2.e().get(num), objArr != null ? x0.T0(objArr, 1) : null, objArr != null ? x0.T0(objArr, 2) : null));
                        }
                        Object objT1 = objArr != null ? x0.T0(objArr, 0) : null;
                        Integer num2 = objT1 instanceof Integer ? (Integer) objT1 : null;
                        if (num2 != null && num2.intValue() == 269484800) {
                            Object objT2 = x0.T0(objArr, 2);
                            Integer num3 = objT2 instanceof Integer ? (Integer) objT2 : null;
                            if (num3 != null && num3.intValue() == 269484801 && (uaVar = r4Var.x) != null) {
                                uaVar.a();
                            }
                        }
                        gj gjVar = r4Var.v;
                        if (gjVar != null) {
                            gjVar.a();
                        }
                        Iterator it = r4Var.w.iterator();
                        while (it.hasNext()) {
                            ((y5) it.next()).a();
                        }
                        return null;
                    case -1776922004:
                        if (name.equals("toString")) {
                            return "ReferencePanelValueWatcher";
                        }
                        return null;
                    case -1295482945:
                        if (name.equals("equals")) {
                            return Boolean.valueOf(obj == (objArr != null ? x0.T0(objArr, 0) : null));
                        }
                        return null;
                    case -935534785:
                        if (!name.equals("onCustomizeFunctionValueChanged")) {
                            return null;
                        }
                        break;
                    case -270692581:
                        if (!name.equals("onSupportedFunctionStatusChanged")) {
                            return null;
                        }
                        break;
                    case 147696667:
                        if (name.equals("hashCode")) {
                            return Integer.valueOf(System.identityHashCode(obj));
                        }
                        return null;
                    case 1310872541:
                        if (!name.equals("onFunctionChanged")) {
                            return null;
                        }
                        break;
                    default:
                        return null;
                }
                gj gjVar2 = r4Var.v;
                if (gjVar2 != null) {
                    gjVar2.a();
                }
                Iterator it2 = r4Var.w.iterator();
                while (it2.hasNext()) {
                    ((y5) it2.next()).a();
                }
                return null;
            default:
                String name2 = method.getName();
                if (name2 == null) {
                    return null;
                }
                switch (name2.hashCode()) {
                    case -1776922004:
                        if (name2.equals("toString")) {
                            return "ReferencePanelCarWatcher";
                        }
                        return null;
                    case -1705092710:
                        if (!name2.equals("onDisConnected")) {
                            return null;
                        }
                        synchronized (r4Var.b) {
                            r4Var.j = null;
                        }
                        r4Var.l = SystemClock.elapsedRealtime();
                        r4Var.n = new j(3);
                        Log.w("ReferencePanel.Ecarx", (String) r4Var.n.a());
                        return null;
                    case -1295482945:
                        if (name2.equals("equals")) {
                            return Boolean.valueOf(obj == (objArr != null ? x0.T0(objArr, 0) : null));
                        }
                        return null;
                    case -609996822:
                        if (!name2.equals("onConnected") || (obj2 = r4Var.i) == null) {
                            return null;
                        }
                        r4Var.g(obj2);
                        return null;
                    case 147696667:
                        if (name2.equals("hashCode")) {
                            return Integer.valueOf(System.identityHashCode(obj));
                        }
                        return null;
                    default:
                        return null;
                }
        }
    }
}
