package defpackage;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class qg implements n6, t3, Serializable {
    public final t3 a;
    public Object b;
    public Iterator c;
    public int d;
    public int e;
    public int f;
    public /* synthetic */ Object g;
    public final /* synthetic */ Iterator h;

    public qg(Iterator it, t3 t3Var) {
        this.h = it;
        this.a = t3Var;
        if (t3Var == null || t3Var.a() == s4.a) {
            return;
        }
        w.a("Coroutines with restricted suspension must have EmptyCoroutineContext");
        throw null;
    }

    @Override // defpackage.t3
    public final s4 a() {
        return s4.a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v7 */
    @Override // defpackage.t3
    public final void b(Object obj) {
        ?? r1 = this;
        while (true) {
            qg qgVar = (qg) r1;
            t3 t3Var = qgVar.a;
            t3Var.getClass();
            try {
                Object objD = qgVar.d(obj);
                if (objD == u3.a) {
                    return;
                }
                obj = objD;
                if (!(t3Var instanceof qg)) {
                    t3Var.b(obj);
                    return;
                }
                r1 = t3Var;
            } catch (Throwable th) {
                obj = new ff(th);
            }
        }
    }

    @Override // defpackage.n6
    public final Object c(Object obj, Object obj2) {
        qg qgVar = new qg(this.h, (t3) obj2);
        qgVar.g = (vf) obj;
        return qgVar.d(ti.a);
    }

    public final Object d(Object obj) throws Throwable {
        int i;
        int i2;
        ArrayList arrayList;
        Iterator it;
        int i3;
        int i4;
        int i5;
        jf jfVar;
        vf vfVar = (vf) this.g;
        int i6 = this.f;
        boolean z = true;
        u3 u3Var = u3.a;
        if (i6 != 0) {
            if (i6 != 1) {
                if (i6 != 2) {
                    if (i6 == 3) {
                        i4 = this.e;
                        int i7 = this.d;
                        Iterator it2 = this.c;
                        jf jfVar2 = (jf) this.b;
                        k8.G0(obj);
                        jfVar2.b();
                        while (true) {
                            int i8 = jfVar2.b;
                            Object[] objArr = jfVar2.a;
                            if (!it2.hasNext()) {
                                i5 = i7;
                                jfVar = jfVar2;
                                break;
                            }
                            Object next = it2.next();
                            if (jfVar2.a() == i8) {
                                throw new IllegalStateException("ring buffer is full");
                            }
                            int i9 = jfVar2.c;
                            boolean z2 = z;
                            int i10 = jfVar2.d;
                            objArr[(i9 + i10) % i8] = next;
                            jfVar2.d = i10 + 1;
                            if (jfVar2.a() == i8) {
                                if (jfVar2.d >= 3) {
                                    ArrayList arrayList2 = new ArrayList(jfVar2);
                                    this.g = vfVar;
                                    this.b = jfVar2;
                                    this.c = it2;
                                    this.d = i7;
                                    this.e = i4;
                                    this.f = 3;
                                    vfVar.d(arrayList2, this);
                                    return u3Var;
                                }
                                int i11 = i8 + (i8 >> 1) + 1;
                                if (i11 > 3) {
                                    i11 = 3;
                                }
                                jfVar2 = new jf(jfVar2.c == 0 ? Arrays.copyOf(objArr, i11) : jfVar2.toArray(new Object[i11]), jfVar2.d);
                            }
                            z = z2;
                        }
                    } else if (i6 == 4) {
                        i4 = this.e;
                        i5 = this.d;
                        jfVar = (jf) this.b;
                        k8.G0(obj);
                        jfVar.b();
                    } else {
                        if (i6 != 5) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }
                    if (jfVar.d > 3) {
                        ArrayList arrayList3 = new ArrayList(jfVar);
                        this.g = vfVar;
                        this.b = jfVar;
                        this.c = null;
                        this.d = i5;
                        this.e = i4;
                        this.f = 4;
                        vfVar.d(arrayList3, this);
                        return u3Var;
                    }
                    if (!jfVar.isEmpty()) {
                        this.g = null;
                        this.b = null;
                        this.c = null;
                        this.d = i5;
                        this.e = i4;
                        this.f = 5;
                        vfVar.d(jfVar, this);
                        return u3Var;
                    }
                }
                k8.G0(obj);
            } else {
                i = this.e;
                int i12 = this.d;
                Iterator it3 = this.c;
                k8.G0(obj);
                arrayList = new ArrayList(3);
                it = it3;
                i3 = i12;
                i2 = i;
            }
            return ti.a;
        }
        k8.G0(obj);
        i = 0;
        i2 = 0;
        arrayList = new ArrayList(3);
        it = this.h;
        i3 = 3;
        while (it.hasNext()) {
            Object next2 = it.next();
            if (i > 0) {
                i--;
            } else {
                arrayList.add(next2);
                if (arrayList.size() == 3) {
                    this.g = vfVar;
                    this.b = arrayList;
                    this.c = it;
                    this.d = i3;
                    this.e = i2;
                    this.f = 1;
                    vfVar.d(arrayList, this);
                    return u3Var;
                }
            }
        }
        if (!arrayList.isEmpty()) {
            this.g = null;
            this.b = null;
            this.c = null;
            this.d = i3;
            this.e = i2;
            this.f = 2;
            vfVar.d(arrayList, this);
            return u3Var;
        }
        return ti.a;
    }

    public final String f() {
        int iIntValue;
        String strC;
        Method method;
        Object objInvoke;
        Method method2;
        Object objInvoke2;
        StringBuilder sb = new StringBuilder("Continuation at ");
        y3 y3Var = (y3) getClass().getAnnotation(y3.class);
        Object name = null;
        str = null;
        str = null;
        str = null;
        str = null;
        str = null;
        String str = null;
        if (y3Var != null) {
            int iV = y3Var.v();
            if (iV > 1) {
                throw new IllegalStateException(("Debug metadata version mismatch. Expected: 1, got " + iV + ". Please update the Kotlin standard library.").toString());
            }
            try {
                Field declaredField = getClass().getDeclaredField("label");
                declaredField.setAccessible(true);
                Object obj = declaredField.get(this);
                Integer num = obj instanceof Integer ? (Integer) obj : null;
                iIntValue = (num != null ? num.intValue() : 0) - 1;
            } catch (Exception unused) {
                iIntValue = -1;
            }
            int i = iIntValue >= 0 ? y3Var.l()[iIntValue] : -1;
            u2 u2Var = k8.d;
            u2 u2Var2 = k8.e;
            if (u2Var2 == null) {
                try {
                    u2 u2Var3 = new u2(Class.class.getDeclaredMethod("getModule", null), getClass().getClassLoader().loadClass("java.lang.Module").getDeclaredMethod("getDescriptor", null), getClass().getClassLoader().loadClass("java.lang.module.ModuleDescriptor").getDeclaredMethod("name", null));
                    k8.e = u2Var3;
                    u2Var2 = u2Var3;
                } catch (Exception unused2) {
                    k8.e = u2Var;
                    u2Var2 = u2Var;
                }
            }
            if (u2Var2 != u2Var && (method = (Method) u2Var2.a) != null && (objInvoke = method.invoke(getClass(), null)) != null && (method2 = (Method) u2Var2.b) != null && (objInvoke2 = method2.invoke(objInvoke, null)) != null) {
                Method method3 = (Method) u2Var2.c;
                Object objInvoke3 = method3 != null ? method3.invoke(objInvoke2, null) : null;
                if (objInvoke3 instanceof String) {
                    str = (String) objInvoke3;
                }
            }
            if (str == null) {
                strC = y3Var.c();
            } else {
                strC = str + '/' + y3Var.c();
            }
            name = new StackTraceElement(strC, y3Var.m(), y3Var.f(), i);
        }
        if (name == null) {
            name = getClass().getName();
        }
        sb.append(name);
        return sb.toString();
    }

    public final String toString() {
        if (this.a != null) {
            return f();
        }
        ne.a.getClass();
        String string = qg.class.getGenericInterfaces()[0].toString();
        return string.startsWith("kotlin.jvm.functions.") ? string.substring(21) : string;
    }

    public void e() {
    }
}
