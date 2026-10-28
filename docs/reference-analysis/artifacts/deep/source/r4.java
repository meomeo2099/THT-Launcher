package defpackage;

import android.content.Context;
import android.util.Log;
import dalvik.system.PathClassLoader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import org.reference.panel.Guard;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class r4 {
    public static final l0 C = new l0();
    public static volatile r4 D;
    public boolean A;
    public boolean B;
    public final Context a;
    public final Object b = new Object();
    public final ConcurrentHashMap c = new ConcurrentHashMap();
    public final ConcurrentHashMap d = new ConcurrentHashMap();
    public volatile yc e;
    public final Class f;
    public final Class g;
    public volatile ClassLoader h;
    public volatile Object i;
    public volatile Object j;
    public volatile boolean k;
    public volatile long l;
    public volatile Object m;
    public volatile y5 n;
    public final u2 o;
    public final ArrayDeque p;
    public volatile String q;
    public volatile boolean r;
    public volatile boolean s;
    public volatile boolean t;
    public volatile boolean u;
    public volatile gj v;
    public final CopyOnWriteArraySet w;
    public volatile ua x;
    public volatile Object y;
    public volatile boolean z;

    public r4(Context context) {
        this.a = context;
        Class cls = Integer.TYPE;
        cls.getClass();
        this.f = cls;
        Class cls2 = Float.TYPE;
        cls2.getClass();
        this.g = cls2;
        this.n = new j(1);
        this.o = new u2(new d1(this, 3), new q4(this), new q4(this));
        this.p = new ArrayDeque();
        this.q = si.a("climate_readings_not_received_yet", new Object[0]);
        this.w = new CopyOnWriteArraySet();
    }

    public final boolean A(boolean z) {
        boolean z2 = B(553845504, 8, z ? 100.0f : 0.0f) || x(553844736, 8, z ? 1 : 0);
        if (z2) {
            this.A = z;
            this.B = true;
        }
        return z2;
    }

    public final boolean B(int i, int i2, float f) {
        Class cls = this.f;
        boolean zE = k8.e(b("setCustomizeFunctionValue", new Class[]{cls, cls, this.g}, true, Integer.valueOf(i), Integer.valueOf(i2), Float.valueOf(f)), Boolean.TRUE);
        if (i == 268828928) {
            List list = si.a;
            K(si.a("command_temperature_0_1_accepted_by_sdk2", Integer.valueOf(i2), Float.valueOf(f), Boolean.valueOf(zE)));
        }
        return zE;
    }

    public final boolean C(int i) {
        int iS = pi.s(i, 0, 100);
        boolean zB = B(553845504, 4, iS);
        if (zB) {
            this.s = iS > 0;
            this.r = false;
        }
        return zB;
    }

    public final boolean D(int i) {
        int i2;
        List list = z2.a;
        if (i <= 0) {
            i2 = 0;
        } else {
            if (i > 3) {
                i = 3;
            }
            i2 = i + 269025536;
        }
        return w(269025536, i2) || x(269025536, 1, i2);
    }

    public final boolean E(float f, int i) {
        boolean zB;
        synchronized (this.o) {
            List list = z2.a;
            zB = Math.abs(f) <= Float.MAX_VALUE ? B(268828928, i, z2.h(f)) : false;
        }
        return zB;
    }

    public final String F(int i) {
        String string;
        Integer numValueOf = Integer.valueOf(i);
        ConcurrentHashMap concurrentHashMap = this.d;
        Object obj = concurrentHashMap.get(numValueOf);
        if (obj == null) {
            Object objB = b("isFunctionSupported", new Class[]{this.f}, false, Integer.valueOf(i));
            if (objB == null || (string = objB.toString()) == null) {
                string = "unknown";
            }
            Object objPutIfAbsent = concurrentHashMap.putIfAbsent(numValueOf, string);
            obj = objPutIfAbsent == null ? string : objPutIfAbsent;
        }
        return (String) obj;
    }

    public final List G(int i) {
        Class cls = this.f;
        Object objB = b("isFunctionSupported", new Class[]{cls}, false, Integer.valueOf(i));
        if (i3.r0(j3.i0("active", "notactive"), objB != null ? objB.toString() : null)) {
            Object objB2 = b("getSupportedFunctionValue", new Class[]{cls}, false, Integer.valueOf(i));
            int[] iArr = objB2 instanceof int[] ? (int[]) objB2 : null;
            List listX0 = iArr != null ? x0.X0(iArr) : null;
            if (listX0 != null) {
                return listX0;
            }
        }
        return u4.a;
    }

    public final boolean H(int i) {
        String lowerCase = F(i).toLowerCase(Locale.ROOT);
        lowerCase.getClass();
        return lowerCase.equals("active") || lowerCase.equals("notactive");
    }

    public final boolean I() {
        boolean zW = l() ? w(553845760, 1) : x(553845760, 4, 1);
        if (zW) {
            this.r = true;
            this.s = false;
        }
        return zW;
    }

    public final boolean J(Integer num) {
        if (s(num)) {
            return false;
        }
        Integer numJ = l() ? j(553845760) : k(553845760, 4);
        Boolean bool = null;
        if (k8.e(this.a.getSharedPreferences("panel", 0).getString("vehicle", null), "livan_x6")) {
            if (numJ != null && numJ.intValue() == 1) {
                this.u = true;
            }
            if (numJ != null && numJ.intValue() == 1) {
                bool = Boolean.TRUE;
            } else if (numJ != null && numJ.intValue() == 0 && this.u) {
                bool = Boolean.FALSE;
            }
        } else if (numJ != null && numJ.intValue() == 0) {
            bool = Boolean.FALSE;
        } else if (numJ != null && numJ.intValue() == 1) {
            bool = Boolean.TRUE;
        }
        return bool != null ? bool.booleanValue() : this.r;
    }

    public final void K(String str) {
        String str2 = new SimpleDateFormat("HH:mm:ss.SSS", Locale.ROOT).format(new Date());
        synchronized (this.p) {
            try {
                if (this.p.size() >= 60) {
                    this.p.removeFirst();
                }
                this.p.addLast(str2 + " " + str);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:11:0x002a  */
    public final byte[] L(int i) {
        Object ffVar;
        try {
            Object obj = this.i;
            if (obj != null) {
                Object objInvoke = obj.getClass().getMethod("getBytesByPropID", this.f).invoke(obj, Integer.valueOf(i));
                if (objInvoke instanceof byte[]) {
                    ffVar = (byte[]) objInvoke;
                } else {
                    ffVar = null;
                }
            } else {
                ffVar = null;
            }
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        return (byte[]) (ffVar instanceof ff ? null : ffVar);
    }

    public final void a() throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
        Object ffVar;
        ClassLoader classLoaderU = u();
        Object objNewInstance = this.i;
        if (objNewInstance == null) {
            objNewInstance = classLoaderU.loadClass("com.ecarx.xui.adaptapi.car.impl.CarImpl").getConstructor(Context.class).newInstance(this.a);
            this.i = objNewInstance;
        }
        try {
            ffVar = classLoaderU.loadClass("com.ecarx.xui.adaptapi.binder.IConnectable");
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        if (ffVar instanceof ff) {
            ffVar = null;
        }
        Class cls = (Class) ffVar;
        if (cls == null || !cls.isInstance(objNewInstance)) {
            objNewInstance.getClass();
            g(objNewInstance);
            return;
        }
        if (!this.k) {
            Class<?> clsLoadClass = classLoaderU.loadClass("com.ecarx.xui.adaptapi.binder.IConnectable$IConnectWatcher");
            cls.getMethod("registerConnectWatcher", clsLoadClass).invoke(objNewInstance, Proxy.newProxyInstance(clsLoadClass.getClassLoader(), new Class[]{clsLoadClass}, new p4(this, 1)));
            this.k = true;
        }
        cls.getMethod("connect", null).invoke(objNewInstance, null);
    }

    public final Object b(String str, Class[] clsArr, boolean z, Object... objArr) {
        int iOffset;
        Object obj = this.j;
        if (obj == null) {
            return null;
        }
        try {
            ConcurrentHashMap concurrentHashMap = this.c;
            String str2 = obj.getClass().getName() + "#" + str + "/" + clsArr.length;
            Object obj2 = concurrentHashMap.get(str2);
            if (obj2 == null) {
                Method method = obj.getClass().getMethod(str, (Class[]) Arrays.copyOf(clsArr, clsArr.length));
                Object objPutIfAbsent = concurrentHashMap.putIfAbsent(str2, method);
                obj2 = objPutIfAbsent == null ? method : objPutIfAbsent;
            }
            Method method2 = (Method) obj2;
            int length = objArr.length;
            Object[] objArr2 = new Object[length];
            for (int i = 0; i < length; i++) {
                objArr2[i] = objArr[i];
            }
            if (str.charAt(0) == 's' && length != 0 && (objArr2[0] instanceof Integer) && (iOffset = Guard.INSTANCE.offset(this.a)) != 0) {
                Object obj3 = objArr2[0];
                obj3.getClass();
                objArr2[0] = Integer.valueOf(((Integer) obj3).intValue() + iOffset);
            }
            return method2.invoke(obj, Arrays.copyOf(objArr2, length));
        } catch (InvocationTargetException e) {
            if (!z) {
                return null;
            }
            String string = Arrays.toString(objArr);
            string.getClass();
            Log.w("ReferencePanel.Ecarx", str + string + ": " + e.getTargetException());
            return null;
        } catch (Exception e2) {
            if (!z) {
                return null;
            }
            String string2 = Arrays.toString(objArr);
            string2.getClass();
            Log.w("ReferencePanel.Ecarx", str + string2 + ": " + e2);
            return null;
        }
    }

    public final String c() {
        String string;
        Object objB = b("isFunctionSupported", new Class[]{this.f}, false, 537069056);
        return (objB == null || (string = objB.toString()) == null) ? "unknown" : string;
    }

    public final List d() {
        Object objB = b("getSupportedFunctionValue", new Class[]{this.f}, false, 537069056);
        int[] iArr = objB instanceof int[] ? (int[]) objB : null;
        List listX0 = iArr != null ? x0.X0(iArr) : null;
        return listX0 == null ? u4.a : listX0;
    }

    public final Float e() {
        yc ycVarP = p();
        float fFloatValue = ((Number) ycVarP.a).floatValue();
        float fFloatValue2 = ((Number) ycVarP.b).floatValue();
        Iterator it = j3.i0(538247936, 538248192).iterator();
        while (true) {
            Float f = null;
            if (!it.hasNext()) {
                Float fI = i(688063744, Integer.MIN_VALUE);
                if (fI == null) {
                    fI = h(688063744);
                }
                if (fI != null) {
                    float fFloatValue3 = fI.floatValue();
                    if (0.0f > fFloatValue3 || fFloatValue3 > 15.0f) {
                        fI = null;
                    }
                    if (fI != null) {
                        return Float.valueOf(fI.floatValue() / 15.0f);
                    }
                }
                return null;
            }
            int iIntValue = ((Number) it.next()).intValue();
            Float fH = h(iIntValue);
            if (fH == null) {
                Integer numJ = j(iIntValue);
                fH = numJ != null ? Float.valueOf(numJ.intValue()) : null;
            }
            if (fH != null) {
                float fFloatValue4 = fH.floatValue();
                if (fFloatValue <= fFloatValue4 && fFloatValue4 <= fFloatValue2) {
                    f = fH;
                }
                if (f != null) {
                    return Float.valueOf((f.floatValue() - fFloatValue) / (fFloatValue2 - fFloatValue));
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:113:0x029e A[Catch: all -> 0x0205, TryCatch #0 {all -> 0x0205, blocks: (B:62:0x01d3, B:63:0x01dc, B:65:0x01e2, B:67:0x01f6, B:72:0x020b, B:73:0x020f, B:75:0x0222, B:81:0x0231, B:83:0x023f, B:98:0x0268, B:111:0x0292, B:113:0x029e, B:115:0x02a6, B:119:0x02af, B:123:0x02ba, B:125:0x02c2, B:127:0x02cb, B:129:0x02d3, B:131:0x02d9, B:134:0x02e2, B:107:0x0287, B:109:0x028d, B:101:0x0279, B:103:0x027f, B:93:0x0256, B:97:0x0262, B:96:0x0260, B:89:0x024a, B:91:0x0250), top: B:235:0x01d3 }] */
    /* JADX WARN: Code duplicated, block: B:115:0x02a6 A[Catch: all -> 0x0205, TryCatch #0 {all -> 0x0205, blocks: (B:62:0x01d3, B:63:0x01dc, B:65:0x01e2, B:67:0x01f6, B:72:0x020b, B:73:0x020f, B:75:0x0222, B:81:0x0231, B:83:0x023f, B:98:0x0268, B:111:0x0292, B:113:0x029e, B:115:0x02a6, B:119:0x02af, B:123:0x02ba, B:125:0x02c2, B:127:0x02cb, B:129:0x02d3, B:131:0x02d9, B:134:0x02e2, B:107:0x0287, B:109:0x028d, B:101:0x0279, B:103:0x027f, B:93:0x0256, B:97:0x0262, B:96:0x0260, B:89:0x024a, B:91:0x0250), top: B:235:0x01d3 }] */
    /* JADX WARN: Code duplicated, block: B:117:0x02ac  */
    /* JADX WARN: Code duplicated, block: B:118:0x02ae  */
    /* JADX WARN: Code duplicated, block: B:120:0x02b4  */
    /* JADX WARN: Code duplicated, block: B:122:0x02b8  */
    /* JADX WARN: Code duplicated, block: B:125:0x02c2 A[Catch: all -> 0x0205, TryCatch #0 {all -> 0x0205, blocks: (B:62:0x01d3, B:63:0x01dc, B:65:0x01e2, B:67:0x01f6, B:72:0x020b, B:73:0x020f, B:75:0x0222, B:81:0x0231, B:83:0x023f, B:98:0x0268, B:111:0x0292, B:113:0x029e, B:115:0x02a6, B:119:0x02af, B:123:0x02ba, B:125:0x02c2, B:127:0x02cb, B:129:0x02d3, B:131:0x02d9, B:134:0x02e2, B:107:0x0287, B:109:0x028d, B:101:0x0279, B:103:0x027f, B:93:0x0256, B:97:0x0262, B:96:0x0260, B:89:0x024a, B:91:0x0250), top: B:235:0x01d3 }] */
    /* JADX WARN: Code duplicated, block: B:126:0x02c9  */
    /* JADX WARN: Code duplicated, block: B:129:0x02d3 A[Catch: all -> 0x0205, TryCatch #0 {all -> 0x0205, blocks: (B:62:0x01d3, B:63:0x01dc, B:65:0x01e2, B:67:0x01f6, B:72:0x020b, B:73:0x020f, B:75:0x0222, B:81:0x0231, B:83:0x023f, B:98:0x0268, B:111:0x0292, B:113:0x029e, B:115:0x02a6, B:119:0x02af, B:123:0x02ba, B:125:0x02c2, B:127:0x02cb, B:129:0x02d3, B:131:0x02d9, B:134:0x02e2, B:107:0x0287, B:109:0x028d, B:101:0x0279, B:103:0x027f, B:93:0x0256, B:97:0x0262, B:96:0x0260, B:89:0x024a, B:91:0x0250), top: B:235:0x01d3 }] */
    /* JADX WARN: Code duplicated, block: B:131:0x02d9 A[Catch: all -> 0x0205, TryCatch #0 {all -> 0x0205, blocks: (B:62:0x01d3, B:63:0x01dc, B:65:0x01e2, B:67:0x01f6, B:72:0x020b, B:73:0x020f, B:75:0x0222, B:81:0x0231, B:83:0x023f, B:98:0x0268, B:111:0x0292, B:113:0x029e, B:115:0x02a6, B:119:0x02af, B:123:0x02ba, B:125:0x02c2, B:127:0x02cb, B:129:0x02d3, B:131:0x02d9, B:134:0x02e2, B:107:0x0287, B:109:0x028d, B:101:0x0279, B:103:0x027f, B:93:0x0256, B:97:0x0262, B:96:0x0260, B:89:0x024a, B:91:0x0250), top: B:235:0x01d3 }] */
    /* JADX WARN: Code duplicated, block: B:133:0x02e0  */
    /* JADX WARN: Code duplicated, block: B:139:0x0312  */
    /* JADX WARN: Code duplicated, block: B:142:0x031f A[Catch: all -> 0x03ce, TryCatch #1 {all -> 0x03ce, blocks: (B:140:0x0319, B:142:0x031f, B:144:0x0328, B:148:0x0331, B:162:0x035a, B:164:0x0360, B:165:0x0370, B:167:0x0376, B:177:0x0391, B:178:0x0395, B:181:0x03a3, B:180:0x03a0, B:151:0x033b, B:159:0x0351, B:161:0x0357, B:154:0x0345, B:156:0x034b), top: B:237:0x0319 }] */
    /* JADX WARN: Code duplicated, block: B:144:0x0328 A[Catch: all -> 0x03ce, TryCatch #1 {all -> 0x03ce, blocks: (B:140:0x0319, B:142:0x031f, B:144:0x0328, B:148:0x0331, B:162:0x035a, B:164:0x0360, B:165:0x0370, B:167:0x0376, B:177:0x0391, B:178:0x0395, B:181:0x03a3, B:180:0x03a0, B:151:0x033b, B:159:0x0351, B:161:0x0357, B:154:0x0345, B:156:0x034b), top: B:237:0x0319 }] */
    /* JADX WARN: Code duplicated, block: B:146:0x032e  */
    /* JADX WARN: Code duplicated, block: B:147:0x0330  */
    /* JADX WARN: Code duplicated, block: B:150:0x0338  */
    /* JADX WARN: Code duplicated, block: B:151:0x033b A[Catch: all -> 0x03ce, TryCatch #1 {all -> 0x03ce, blocks: (B:140:0x0319, B:142:0x031f, B:144:0x0328, B:148:0x0331, B:162:0x035a, B:164:0x0360, B:165:0x0370, B:167:0x0376, B:177:0x0391, B:178:0x0395, B:181:0x03a3, B:180:0x03a0, B:151:0x033b, B:159:0x0351, B:161:0x0357, B:154:0x0345, B:156:0x034b), top: B:237:0x0319 }] */
    /* JADX WARN: Code duplicated, block: B:164:0x0360 A[Catch: all -> 0x03ce, TryCatch #1 {all -> 0x03ce, blocks: (B:140:0x0319, B:142:0x031f, B:144:0x0328, B:148:0x0331, B:162:0x035a, B:164:0x0360, B:165:0x0370, B:167:0x0376, B:177:0x0391, B:178:0x0395, B:181:0x03a3, B:180:0x03a0, B:151:0x033b, B:159:0x0351, B:161:0x0357, B:154:0x0345, B:156:0x034b), top: B:237:0x0319 }] */
    /* JADX WARN: Code duplicated, block: B:167:0x0376 A[Catch: all -> 0x03ce, TryCatch #1 {all -> 0x03ce, blocks: (B:140:0x0319, B:142:0x031f, B:144:0x0328, B:148:0x0331, B:162:0x035a, B:164:0x0360, B:165:0x0370, B:167:0x0376, B:177:0x0391, B:178:0x0395, B:181:0x03a3, B:180:0x03a0, B:151:0x033b, B:159:0x0351, B:161:0x0357, B:154:0x0345, B:156:0x034b), top: B:237:0x0319 }] */
    /* JADX WARN: Code duplicated, block: B:175:0x038e  */
    /* JADX WARN: Code duplicated, block: B:180:0x03a0 A[Catch: all -> 0x03ce, TryCatch #1 {all -> 0x03ce, blocks: (B:140:0x0319, B:142:0x031f, B:144:0x0328, B:148:0x0331, B:162:0x035a, B:164:0x0360, B:165:0x0370, B:167:0x0376, B:177:0x0391, B:178:0x0395, B:181:0x03a3, B:180:0x03a0, B:151:0x033b, B:159:0x0351, B:161:0x0357, B:154:0x0345, B:156:0x034b), top: B:237:0x0319 }] */
    /* JADX WARN: Code duplicated, block: B:184:0x040a  */
    /* JADX WARN: Code duplicated, block: B:186:0x0414  */
    /* JADX WARN: Code duplicated, block: B:189:0x042f  */
    /* JADX WARN: Code duplicated, block: B:192:0x0445  */
    /* JADX WARN: Code duplicated, block: B:202:0x0473  */
    /* JADX WARN: Code duplicated, block: B:204:0x0478  */
    /* JADX WARN: Code duplicated, block: B:207:0x0496  */
    /* JADX WARN: Code duplicated, block: B:210:0x04a3  */
    /* JADX WARN: Code duplicated, block: B:216:0x04bb  */
    /* JADX WARN: Code duplicated, block: B:219:0x04c5  */
    /* JADX WARN: Code duplicated, block: B:229:0x0543  */
    /* JADX WARN: Code duplicated, block: B:249:0x0391 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:252:0x0370 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:255:0x0458 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:257:0x0550 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:259:0x053d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:262:0x04cf A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:265:0x04bf A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:42:0x012a  */
    public final bj f(bj bjVar, Collection collection, boolean z) {
        Integer num;
        Object ffVar;
        Throwable thA;
        String str;
        String strA;
        Integer num2;
        String strA2;
        Object objA;
        Collection collectionValues;
        Iterator it;
        ArrayList arrayList;
        Integer numValueOf;
        Iterator it2;
        Object next;
        n4 n4Var;
        String strC;
        String str2;
        Integer numJ;
        Boolean boolValueOf;
        Boolean bool;
        List listJ0;
        ArrayList arrayList2;
        int iIntValue;
        boolean z2;
        Integer numJ2;
        boolean z3;
        boolean z4;
        Boolean boolValueOf2;
        Boolean bool2;
        Boolean bool3;
        int i;
        Boolean bool4;
        Boolean boolN;
        int i2;
        Integer num3;
        Integer numK;
        Integer numK2;
        Boolean boolValueOf3;
        boolean z5;
        int iIntValue2;
        collection.getClass();
        if (!m()) {
            v2 v2Var = new v2();
            v4 v4Var = v4.a;
            String str3 = (bjVar.a == null && bjVar.c == null && bjVar.d == null) ? (String) this.n.a() : bjVar.k;
            List listI0 = j3.i0((String) this.n.a(), bjVar.l);
            ArrayList arrayList3 = new ArrayList();
            for (Object obj : listI0) {
                if (!th.A((String) obj)) {
                    arrayList3.add(obj);
                }
            }
            return bj.a(bjVar, null, null, null, v2Var, v4Var, str3, i3.y0(arrayList3, "\n", null, 62), null, null, null, null, null, null, false, null, null, null, null, null, null, null, 66711791);
        }
        bj bjVarA = bj.a(bjVar, null, null, null, null, null, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, 66715647);
        String strC2 = c();
        List listD = d();
        Integer numJ3 = x0.Y0(new String[]{"active", "notactive"}).contains(strC2) ? j(537069056) : null;
        Integer num4 = 537069059;
        if (!strC2.equals("active") || !listD.contains(537069058) || !listD.contains(num4)) {
            num4 = null;
        } else if (numJ3 == null || numJ3.intValue() != 537069058) {
            if (numJ3 != null && numJ3.intValue() == 537069059) {
                num4 = 537069058;
            } else {
                num4 = null;
            }
        }
        int i3 = 0;
        bj bjVarA2 = bj.a(bjVarA, null, null, null, null, null, null, null, null, null, null, null, null, numJ3, num4 != null, null, null, null, null, null, null, null, 66715647);
        Integer num5 = numJ3;
        String strF = F(671548416);
        Set setY0 = x0.Y0(new String[]{"active", "notactive"});
        String lowerCase = strF.toLowerCase(Locale.ROOT);
        lowerCase.getClass();
        if (setY0.contains(lowerCase)) {
            Integer numK3 = k(671548416, Integer.MIN_VALUE);
            if (numK3 == null) {
                numK3 = j(671548416);
            }
            num = numK3;
        } else {
            num = null;
        }
        bj bjVarA3 = bj.a(bjVarA2, null, null, null, null, null, null, null, null, null, null, null, null, null, false, num != null ? Boolean.valueOf(num.intValue() != 0) : null, null, null, null, null, null, null, 66584575);
        try {
            ArrayList arrayList4 = new ArrayList();
            Iterator it3 = collection.iterator();
            while (it3.hasNext()) {
                String str4 = (String) it3.next();
                Set set = rf.d;
                rf rfVar = (rf) pi.B().get(str4);
                yc ycVar = rfVar != null ? new yc(str4, new tf(r(rfVar))) : null;
                if (ycVar != null) {
                    arrayList4.add(ycVar);
                }
            }
            Map mapL0 = hb.l0(arrayList4);
            Integer numT = t();
            Integer numK4 = k(553846272, 8);
            Integer num6 = (numK4 == null || (iIntValue2 = numK4.intValue()) < 0 || iIntValue2 >= 101) ? null : numK4;
            v2 v2VarQ = q();
            boolean zS = s(numT);
            boolean zJ = J(numT);
            if (num6 != null || this.B) {
                if (num6 != null && num6.intValue() == 0) {
                    this.A = false;
                    z4 = false;
                } else {
                    z4 = (num6 == null || num6.intValue() < 50) ? this.A : true;
                }
                boolValueOf2 = Boolean.valueOf(z4);
            } else {
                boolValueOf2 = null;
            }
            Integer numJ4 = j(570491136);
            Integer numJ5 = j(537002240);
            try {
                if (numJ5 == null || numJ5.intValue() != 0) {
                    if (numJ5 != null && numJ5.intValue() == 1) {
                        bool2 = Boolean.TRUE;
                    } else {
                        bool3 = null;
                    }
                    Float fE = e();
                    i = o2.a;
                    if (H(i)) {
                        numK2 = k(i, o2.b);
                        if (numK2 != null) {
                            if (numK2.intValue() == 1) {
                                z5 = true;
                            } else {
                                z5 = false;
                            }
                            boolValueOf3 = Boolean.valueOf(z5);
                        } else {
                            boolValueOf3 = null;
                        }
                        bool4 = boolValueOf3;
                    } else {
                        bool4 = null;
                    }
                    if (H(o2.c)) {
                        boolN = n();
                    } else {
                        boolN = null;
                    }
                    i2 = o2.d;
                    if (H(i2)) {
                        numK = k(i2, Integer.MIN_VALUE);
                        if (numK == null) {
                            numK = j(i2);
                        }
                        num3 = numK;
                    } else {
                        num3 = null;
                    }
                    bjVarA3 = bj.a(bjVarA3, null, null, null, v2VarQ, mapL0, null, null, numT, Boolean.valueOf(zS), numJ4, fE, bool3, null, false, null, Boolean.valueOf(zJ), num6, boolValueOf2, bool4, boolN, num3, 920831);
                    ffVar = ti.a;
                    thA = gf.a(ffVar);
                    if (thA != null) {
                        Log.w("ReferencePanel.Ecarx", "climate snapshot", thA);
                    }
                    if (l()) {
                        numJ2 = j(704709376);
                        if (numJ2 != null) {
                            if (numJ2.intValue() != 0) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            boolValueOf = Boolean.valueOf(z3);
                            bool = boolValueOf;
                        } else {
                            bool = null;
                        }
                    } else {
                        numJ = j(553979904);
                        if (numJ != null && numJ.intValue() == 0) {
                            boolValueOf = Boolean.FALSE;
                        } else if (numJ != null && numJ.intValue() == 1) {
                            boolValueOf = Boolean.TRUE;
                        } else {
                            bool = null;
                        }
                        bool = boolValueOf;
                    }
                    LinkedHashMap linkedHashMapC = pi.C();
                    if (z) {
                        List listG = G(704708864);
                        arrayList2 = new ArrayList();
                        for (Object obj2 : listG) {
                            iIntValue = ((Number) obj2).intValue();
                            if (iIntValue >= 0 || (253 <= iIntValue && iIntValue < 256)) {
                                z2 = false;
                            } else {
                                z2 = true;
                            }
                            if (z2) {
                                arrayList2.add(obj2);
                            }
                        }
                        listJ0 = i3.J0(i3.K0(arrayList2));
                    } else {
                        listJ0 = bjVar.g;
                    }
                    bjVarA3 = bj.a(bjVarA3, bool, linkedHashMapC, listJ0, null, null, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, 67108623);
                    bj bjVar2 = bjVarA3;
                    List list = si.a;
                    str = (String) this.n.a();
                    String strA3 = si.a("brightness_day_0_night_1_atlas_2", F(538247936), F(538248192), F(688063744), p().a, p().b);
                    if (this.z) {
                        strA = si.a("subscribed", new Object[0]);
                    } else {
                        strA = si.a("polling_only", new Object[0]);
                    }
                    String str5 = strA;
                    Integer numValueOf2 = Integer.valueOf(bjVar2.i.f.size());
                    num2 = bjVar2.o;
                    if (num2 != null) {
                        int iIntValue3 = num2.intValue();
                        numValueOf = Integer.valueOf(iIntValue3);
                        it2 = pi.A().iterator();
                        do {
                            if (it2.hasNext()) {
                                next = null;
                                break;
                            }
                            next = it2.next();
                        } while (!((n4) next).a(numValueOf));
                        n4Var = (n4) next;
                        if (n4Var != null || (str2 = n4Var.c) == null) {
                            strC = null;
                        } else {
                            strC = th.C(str2, si.a("mode", new Object[0]));
                        }
                        if (strC == null) {
                            strC = "?";
                        }
                        strA2 = iIntValue3 + " (" + strC + ")";
                    } else {
                        str = str;
                        strA2 = si.a("no", new Object[0]);
                    }
                    String str6 = strA2;
                    objA = bjVar2.q;
                    if (objA == null) {
                        objA = si.a("no", new Object[0]);
                    }
                    Object obj3 = objA;
                    collectionValues = bjVar2.j.values();
                    if (collectionValues != null || !collectionValues.isEmpty()) {
                        it = collectionValues.iterator();
                        while (it.hasNext()) {
                            if (((tf) it.next()).a == null && (i3 = i3 + 1) < 0) {
                                j3.l0();
                                throw null;
                            }
                        }
                    }
                    List listI1 = j3.i0(si.a("0_1_events_2_climate_3_functions", str, strA3, str5, numValueOf2, str6, obj3, Integer.valueOf(i3)), "Cruise: status=" + strC2 + " mode=" + num5 + " supported=" + listD, "G-Pilot: status=" + strF + " value=" + num + " supported=" + G(671548416), bjVar2.l);
                    arrayList = new ArrayList();
                    for (Object obj4 : listI1) {
                        if (!th.A((String) obj4)) {
                            arrayList.add(obj4);
                        }
                    }
                    return bj.a(bjVar2, null, null, null, null, null, null, i3.y0(arrayList, "\n", null, 62), null, null, null, null, null, null, false, null, null, null, null, null, null, null, 67106815);
                }
                bool2 = Boolean.FALSE;
                if (l()) {
                    numJ2 = j(704709376);
                    if (numJ2 != null) {
                        if (numJ2.intValue() != 0) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        boolValueOf = Boolean.valueOf(z3);
                        bool = boolValueOf;
                    } else {
                        bool = null;
                    }
                } else {
                    numJ = j(553979904);
                    if (numJ != null) {
                        boolValueOf = Boolean.FALSE;
                        bool = boolValueOf;
                    }
                    if (numJ != null) {
                        boolValueOf = Boolean.TRUE;
                        bool = boolValueOf;
                    }
                    bool = null;
                }
                LinkedHashMap linkedHashMapC2 = pi.C();
                if (z) {
                    List listG2 = G(704708864);
                    arrayList2 = new ArrayList();
                    while (r0.hasNext()) {
                        iIntValue = ((Number) obj2).intValue();
                        if (iIntValue >= 0) {
                            z2 = false;
                        } else {
                            z2 = false;
                        }
                        if (z2) {
                            arrayList2.add(obj2);
                        }
                    }
                    listJ0 = i3.J0(i3.K0(arrayList2));
                } else {
                    listJ0 = bjVar.g;
                }
                bjVarA3 = bj.a(bjVarA3, bool, linkedHashMapC2, listJ0, null, null, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, 67108623);
            } catch (Throwable unused) {
            }
            bool3 = bool2;
            Float fE2 = e();
            i = o2.a;
            if (H(i)) {
                numK2 = k(i, o2.b);
                if (numK2 != null) {
                    if (numK2.intValue() == 1) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    boolValueOf3 = Boolean.valueOf(z5);
                } else {
                    boolValueOf3 = null;
                }
                bool4 = boolValueOf3;
            } else {
                bool4 = null;
            }
            if (H(o2.c)) {
                boolN = n();
            } else {
                boolN = null;
            }
            i2 = o2.d;
            if (H(i2)) {
                numK = k(i2, Integer.MIN_VALUE);
                if (numK == null) {
                    numK = j(i2);
                }
                num3 = numK;
            } else {
                num3 = null;
            }
            bjVarA3 = bj.a(bjVarA3, null, null, null, v2VarQ, mapL0, null, null, numT, Boolean.valueOf(zS), numJ4, fE2, bool3, null, false, null, Boolean.valueOf(zJ), num6, boolValueOf2, bool4, boolN, num3, 920831);
            ffVar = ti.a;
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        thA = gf.a(ffVar);
        if (thA != null) {
            Log.w("ReferencePanel.Ecarx", "climate snapshot", thA);
        }
        bj bjVar3 = bjVarA3;
        List list2 = si.a;
        str = (String) this.n.a();
        String strA4 = si.a("brightness_day_0_night_1_atlas_2", F(538247936), F(538248192), F(688063744), p().a, p().b);
        if (this.z) {
            strA = si.a("subscribed", new Object[0]);
        } else {
            strA = si.a("polling_only", new Object[0]);
        }
        String str7 = strA;
        Integer numValueOf3 = Integer.valueOf(bjVar3.i.f.size());
        num2 = bjVar3.o;
        if (num2 != null) {
            int iIntValue4 = num2.intValue();
            numValueOf = Integer.valueOf(iIntValue4);
            it2 = pi.A().iterator();
            do {
                if (it2.hasNext()) {
                    next = null;
                    break;
                }
                next = it2.next();
            } while (!((n4) next).a(numValueOf));
            n4Var = (n4) next;
            if (n4Var != null) {
                strC = null;
            } else {
                strC = null;
            }
            if (strC == null) {
                strC = "?";
            }
            strA2 = iIntValue4 + " (" + strC + ")";
        } else {
            str = str;
            strA2 = si.a("no", new Object[0]);
        }
        String str8 = strA2;
        objA = bjVar3.q;
        if (objA == null) {
            objA = si.a("no", new Object[0]);
        }
        Object obj5 = objA;
        collectionValues = bjVar3.j.values();
        if (collectionValues != null) {
            it = collectionValues.iterator();
            while (it.hasNext()) {
                if (((tf) it.next()).a == null) {
                }
            }
        } else {
            it = collectionValues.iterator();
            while (it.hasNext()) {
                if (((tf) it.next()).a == null) {
                }
            }
        }
        List listI2 = j3.i0(si.a("0_1_events_2_climate_3_functions", str, strA4, str7, numValueOf3, str8, obj5, Integer.valueOf(i3)), "Cruise: status=" + strC2 + " mode=" + num5 + " supported=" + listD, "G-Pilot: status=" + strF + " value=" + num + " supported=" + G(671548416), bjVar3.l);
        arrayList = new ArrayList();
        while (r1.hasNext()) {
            if (!th.A((String) obj4)) {
                arrayList.add(obj4);
            }
        }
        return bj.a(bjVar3, null, null, null, null, null, null, i3.y0(arrayList, "\n", null, 62), null, null, null, null, null, null, false, null, null, null, null, null, null, null, 67106815);
    }

    /* JADX WARN: Code duplicated, block: B:101:0x01f6  */
    /* JADX WARN: Code duplicated, block: B:126:0x01ea A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:66:0x0172  */
    /* JADX WARN: Code duplicated, block: B:67:0x0175  */
    /* JADX WARN: Code duplicated, block: B:69:0x0179 A[Catch: all -> 0x007f, TRY_LEAVE, TryCatch #5 {all -> 0x007f, blocks: (B:25:0x005b, B:27:0x006a, B:31:0x0082, B:32:0x00ec, B:34:0x00f2, B:44:0x0129, B:47:0x012f, B:57:0x015e, B:60:0x0164, B:102:0x01f7, B:69:0x0179, B:78:0x01a6, B:80:0x01ac, B:82:0x01b0, B:85:0x01b7, B:89:0x01bf, B:90:0x01db, B:93:0x01e0, B:95:0x01e8, B:96:0x01ea, B:77:0x01a0, B:56:0x0158, B:43:0x0123, B:49:0x0139, B:51:0x0151, B:70:0x017b, B:72:0x0199, B:36:0x0102, B:38:0x011c), top: B:122:0x005b, inners: #0, #3, #4 }] */
    /* JADX WARN: Code duplicated, block: B:72:0x0199 A[Catch: all -> 0x019c, TRY_LEAVE, TryCatch #3 {all -> 0x019c, blocks: (B:70:0x017b, B:72:0x0199), top: B:118:0x017b, outer: #5 }] */
    /* JADX WARN: Code duplicated, block: B:76:0x019e  */
    /* JADX WARN: Code duplicated, block: B:80:0x01ac A[Catch: all -> 0x007f, TryCatch #5 {all -> 0x007f, blocks: (B:25:0x005b, B:27:0x006a, B:31:0x0082, B:32:0x00ec, B:34:0x00f2, B:44:0x0129, B:47:0x012f, B:57:0x015e, B:60:0x0164, B:102:0x01f7, B:69:0x0179, B:78:0x01a6, B:80:0x01ac, B:82:0x01b0, B:85:0x01b7, B:89:0x01bf, B:90:0x01db, B:93:0x01e0, B:95:0x01e8, B:96:0x01ea, B:77:0x01a0, B:56:0x0158, B:43:0x0123, B:49:0x0139, B:51:0x0151, B:70:0x017b, B:72:0x0199, B:36:0x0102, B:38:0x011c), top: B:122:0x005b, inners: #0, #3, #4 }] */
    /* JADX WARN: Code duplicated, block: B:82:0x01b0 A[Catch: all -> 0x007f, TryCatch #5 {all -> 0x007f, blocks: (B:25:0x005b, B:27:0x006a, B:31:0x0082, B:32:0x00ec, B:34:0x00f2, B:44:0x0129, B:47:0x012f, B:57:0x015e, B:60:0x0164, B:102:0x01f7, B:69:0x0179, B:78:0x01a6, B:80:0x01ac, B:82:0x01b0, B:85:0x01b7, B:89:0x01bf, B:90:0x01db, B:93:0x01e0, B:95:0x01e8, B:96:0x01ea, B:77:0x01a0, B:56:0x0158, B:43:0x0123, B:49:0x0139, B:51:0x0151, B:70:0x017b, B:72:0x0199, B:36:0x0102, B:38:0x011c), top: B:122:0x005b, inners: #0, #3, #4 }] */
    /* JADX WARN: Code duplicated, block: B:83:0x01b4  */
    /* JADX WARN: Code duplicated, block: B:92:0x01df  */
    /* JADX WARN: Code duplicated, block: B:95:0x01e8 A[Catch: all -> 0x007f, TryCatch #5 {all -> 0x007f, blocks: (B:25:0x005b, B:27:0x006a, B:31:0x0082, B:32:0x00ec, B:34:0x00f2, B:44:0x0129, B:47:0x012f, B:57:0x015e, B:60:0x0164, B:102:0x01f7, B:69:0x0179, B:78:0x01a6, B:80:0x01ac, B:82:0x01b0, B:85:0x01b7, B:89:0x01bf, B:90:0x01db, B:93:0x01e0, B:95:0x01e8, B:96:0x01ea, B:77:0x01a0, B:56:0x0158, B:43:0x0123, B:49:0x0139, B:51:0x0151, B:70:0x017b, B:72:0x0199, B:36:0x0102, B:38:0x011c), top: B:122:0x005b, inners: #0, #3, #4 }] */
    public final void g(Object obj) {
        Object ffVar;
        Object ffVar2;
        Object ffVar3;
        Object ffVar4;
        boolean z;
        int i;
        int i2;
        int i3;
        Object ffVar5;
        Throwable thA;
        InvocationTargetException invocationTargetException;
        Throwable targetException;
        Object objInvoke;
        boolean z2;
        try {
            ffVar = obj.getClass().getMethod("getICarFunction", null).invoke(obj, null);
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        Throwable thA2 = gf.a(ffVar);
        if (thA2 != null) {
            Log.e("ReferencePanel.Ecarx", "getICarFunction", thA2);
        }
        Object obj2 = ffVar instanceof ff ? null : ffVar;
        synchronized (this.b) {
            this.j = obj2;
        }
        this.m = null;
        this.d.clear();
        this.e = null;
        this.n = obj2 != null ? new j(4) : new j(2);
        if (obj2 != null) {
            try {
                Class<?> clsLoadClass = u().loadClass("com.ecarx.xui.adaptapi.car.base.ICarFunction$IFunctionValueWatcher");
                Object objNewProxyInstance = this.y;
                int i4 = 0;
                if (objNewProxyInstance == null) {
                    objNewProxyInstance = Proxy.newProxyInstance(clsLoadClass.getClassLoader(), new Class[]{clsLoadClass}, new p4(this, i4));
                    this.y = objNewProxyInstance;
                }
                Object obj3 = objNewProxyInstance;
                List list = z2.a;
                Set setKeySet = z2.e().keySet();
                setKeySet.getClass();
                LinkedHashSet linkedHashSetH0 = zf.h0(setKeySet, j3.i0(268828928, 268763648, 268763392, 553846272, 553845760, 553979904, 704709376, 570491136, 537002240, 269484800));
                int size = linkedHashSetH0.size();
                int[] iArr = new int[size];
                Iterator it = linkedHashSetH0.iterator();
                int i5 = 0;
                while (it.hasNext()) {
                    int i6 = i5 + 1;
                    iArr[i5] = ((Number) it.next()).intValue();
                    i5 = i6;
                }
                try {
                    Object objInvoke2 = obj2.getClass().getMethod("registerFunctionValueWatcher", int[].class, clsLoadClass).invoke(obj2, iArr, obj3);
                    ffVar3 = objInvoke2 instanceof Boolean ? (Boolean) objInvoke2 : null;
                } catch (Throwable th2) {
                    ffVar3 = new ff(th2);
                }
                if (ffVar3 instanceof ff) {
                    ffVar3 = null;
                }
                if (k8.e((Boolean) ffVar3, Boolean.TRUE)) {
                    if (z) {
                        i2 = 0;
                    } else {
                        i2 = 0;
                        for (i = 0; i < size; i++) {
                            i3 = iArr[i];
                            objInvoke = obj2.getClass().getMethod("registerFunctionValueWatcher", this.f, clsLoadClass).invoke(obj2, Integer.valueOf(i3), obj3);
                            if (objInvoke instanceof Boolean) {
                                ffVar5 = (Boolean) objInvoke;
                            } else {
                                ffVar5 = null;
                            }
                            thA = gf.a(ffVar5);
                            if (thA != null) {
                                if (thA instanceof InvocationTargetException) {
                                    invocationTargetException = (InvocationTargetException) thA;
                                } else {
                                    invocationTargetException = null;
                                }
                                if (invocationTargetException != null) {
                                    thA = targetException;
                                }
                                Log.w("ReferencePanel.Ecarx", "watcher " + i3 + ": " + thA);
                            }
                            if (ffVar5 instanceof ff) {
                                ffVar5 = null;
                            }
                            if (k8.e(ffVar5, Boolean.TRUE)) {
                                i2++;
                            }
                        }
                    }
                    if (z) {
                        z2 = true;
                    } else {
                        z2 = true;
                    }
                    this.z = z2;
                    ffVar2 = Integer.valueOf(Log.i("ReferencePanel.Ecarx", "registerFunctionValueWatcher: all=" + z + ", by function=" + i2 + " of " + size));
                } else {
                    try {
                        Object objInvoke3 = obj2.getClass().getMethod("registerFunctionValueWatcher", clsLoadClass).invoke(obj2, obj3);
                        ffVar4 = objInvoke3 instanceof Boolean ? (Boolean) objInvoke3 : null;
                    } catch (Throwable th3) {
                        ffVar4 = new ff(th3);
                    }
                    if (ffVar4 instanceof ff) {
                        ffVar4 = null;
                    }
                    z = k8.e(ffVar4, Boolean.TRUE);
                    if (z) {
                        i2 = 0;
                    } else {
                        i2 = 0;
                        while (i < size) {
                            i3 = iArr[i];
                            try {
                                objInvoke = obj2.getClass().getMethod("registerFunctionValueWatcher", this.f, clsLoadClass).invoke(obj2, Integer.valueOf(i3), obj3);
                                if (objInvoke instanceof Boolean) {
                                    ffVar5 = (Boolean) objInvoke;
                                } else {
                                    ffVar5 = null;
                                }
                            } catch (Throwable th4) {
                                ffVar5 = new ff(th4);
                            }
                            thA = gf.a(ffVar5);
                            if (thA != null) {
                                if (thA instanceof InvocationTargetException) {
                                    invocationTargetException = (InvocationTargetException) thA;
                                } else {
                                    invocationTargetException = null;
                                }
                                if (invocationTargetException != null && (targetException = invocationTargetException.getTargetException()) != null) {
                                    thA = targetException;
                                }
                                Log.w("ReferencePanel.Ecarx", "watcher " + i3 + ": " + thA);
                            }
                            if (ffVar5 instanceof ff) {
                                ffVar5 = null;
                            }
                            if (k8.e(ffVar5, Boolean.TRUE)) {
                                i2++;
                            }
                        }
                    }
                    if (z || i2 > 0) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    this.z = z2;
                    ffVar2 = Integer.valueOf(Log.i("ReferencePanel.Ecarx", "registerFunctionValueWatcher: all=" + z + ", by function=" + i2 + " of " + size));
                }
            } catch (Throwable th5) {
                ffVar2 = new ff(th5);
            }
            Throwable thA3 = gf.a(ffVar2);
            if (thA3 != null) {
                Log.w("ReferencePanel.Ecarx", "value watcher", thA3);
            }
        }
    }

    public final Float h(int i) {
        Object objB = b("getCustomizeFunctionValue", new Class[]{this.f}, false, Integer.valueOf(i));
        Float f = objB instanceof Float ? (Float) objB : null;
        if (f == null || Math.abs(f.floatValue()) > Float.MAX_VALUE) {
            return null;
        }
        return f;
    }

    public final Float i(int i, int i2) {
        Class cls = this.f;
        Object objB = b("getCustomizeFunctionValue", new Class[]{cls, cls}, false, Integer.valueOf(i), Integer.valueOf(i2));
        Float f = objB instanceof Float ? (Float) objB : null;
        if (f == null || Math.abs(f.floatValue()) > Float.MAX_VALUE) {
            return null;
        }
        return f;
    }

    public final Integer j(int i) {
        int iIntValue;
        Object objB = b("getFunctionValue", new Class[]{this.f}, false, Integer.valueOf(i));
        Integer num = objB instanceof Integer ? (Integer) objB : null;
        if (num == null || (iIntValue = num.intValue()) < 0 || (253 <= iIntValue && iIntValue < 256)) {
            return null;
        }
        return num;
    }

    public final Integer k(int i, int i2) {
        int iIntValue;
        Class cls = this.f;
        Object objB = b("getFunctionValue", new Class[]{cls, cls}, false, Integer.valueOf(i), Integer.valueOf(i2));
        Integer num = objB instanceof Integer ? (Integer) objB : null;
        if (num == null || (iIntValue = num.intValue()) < 0 || (253 <= iIntValue && iIntValue < 256)) {
            return null;
        }
        return num;
    }

    public final boolean l() {
        return k8.e(this.a.getSharedPreferences("panel", 0).getString("vehicle", null), "geely_monjaro");
    }

    public final boolean m() {
        return this.j != null;
    }

    public final Boolean n() {
        int i = o2.a;
        int i2 = o2.c;
        Integer numK = k(i2, Integer.MIN_VALUE);
        if (numK == null) {
            numK = j(i2);
        }
        if (numK != null) {
            return Boolean.valueOf(numK.intValue() == 1);
        }
        return null;
    }

    public final void o(String str) {
        str.getClass();
        K(si.a("action_0", str));
    }

    public final yc p() {
        yc ycVar = this.e;
        if (ycVar == null) {
            Float fH = h(538248704);
            Float fValueOf = null;
            if (fH == null) {
                Integer numJ = j(538248704);
                fH = numJ != null ? Float.valueOf(numJ.intValue()) : null;
            }
            Float fH2 = h(538248448);
            if (fH2 == null) {
                Integer numJ2 = j(538248448);
                if (numJ2 != null) {
                    fValueOf = Float.valueOf(numJ2.intValue());
                }
            } else {
                fValueOf = fH2;
            }
            ycVar = (fH == null || fValueOf == null || fValueOf.floatValue() <= fH.floatValue() || fValueOf.floatValue() > 1000.0f) ? new yc(Float.valueOf(0.0f), Float.valueOf(15.0f)) : new yc(fH, fValueOf);
            this.e = ycVar;
        }
        return ycVar;
    }

    public final v2 q() {
        String str;
        List list = z2.a;
        List listI0 = j3.i0(1, 4);
        ArrayList arrayList = new ArrayList();
        Iterator it = listI0.iterator();
        while (true) {
            yc ycVar = null;
            if (!it.hasNext()) {
                break;
            }
            int iIntValue = ((Number) it.next()).intValue();
            List list2 = z2.a;
            Float fJ = z2.j(i(268828928, iIntValue));
            if (fJ != null) {
                ycVar = new yc(Integer.valueOf(iIntValue), Float.valueOf(fJ.floatValue()));
            }
            if (ycVar != null) {
                arrayList.add(ycVar);
            }
        }
        Map mapL0 = hb.l0(arrayList);
        Integer numH = this.o.h(268566784);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Integer numH2 = this.o.h(268501248);
        if (numH2 != null) {
            linkedHashMap.put(268501248, Integer.valueOf(numH2.intValue()));
        }
        Integer numH3 = this.o.h(268501760);
        if (numH3 != null) {
            linkedHashMap.put(268501760, Integer.valueOf(numH3.intValue()));
        }
        Integer numH4 = this.o.h(268502016);
        if (numH4 != null) {
            linkedHashMap.put(268502016, Integer.valueOf(numH4.intValue()));
        }
        Integer numH5 = this.o.h(268501504);
        if (numH5 != null) {
            linkedHashMap.put(268501504, Integer.valueOf(numH5.intValue()));
        }
        if (numH != null) {
            linkedHashMap.put(268566784, Integer.valueOf(numH.intValue()));
        }
        Integer numH6 = this.o.h(268632320);
        if (numH6 != null) {
            linkedHashMap.put(268632320, Integer.valueOf(numH6.intValue()));
        }
        Integer numH7 = this.o.h(268894464);
        if (numH7 != null) {
            linkedHashMap.put(268894464, Integer.valueOf(numH7.intValue()));
        }
        Integer numH8 = this.o.h(268697856);
        if (numH8 != null) {
            linkedHashMap.put(268697856, Integer.valueOf(numH8.intValue()));
        }
        Integer numH9 = this.o.h(268698368);
        if (numH9 != null) {
            linkedHashMap.put(268698368, Integer.valueOf(numH9.intValue()));
        }
        Integer numH10 = this.o.h(269747456);
        if (numH10 != null) {
            linkedHashMap.put(269747456, Integer.valueOf(numH10.intValue()));
        }
        this.a.getSharedPreferences("panel", 0).getString("vehicle", null);
        Integer numH11 = this.o.h(269747200);
        if (numH11 != null) {
            linkedHashMap.put(269747200, Integer.valueOf(numH11.intValue()));
        }
        Integer numJ = j(269025536);
        if (numJ != null) {
            linkedHashMap.put(269025536, Integer.valueOf(numJ.intValue()));
        }
        Integer numH12 = this.o.h(268829952);
        if (numH12 != null) {
            linkedHashMap.put(268829952, Integer.valueOf(numH12.intValue()));
        }
        Integer numJ2 = j(268830208);
        if (numJ2 != null && numJ2.intValue() == 268830209) {
            str = "°C";
        } else {
            str = (numJ2 != null && numJ2.intValue() == 268830210) ? "°F" : "";
        }
        String str2 = str;
        Set setEntrySet = z2.e().entrySet();
        setEntrySet.getClass();
        String strY0 = i3.y0(setEntrySet, "; ", new a(linkedHashMap), 30);
        if (!strY0.equals(this.q)) {
            this.q = strY0;
            K(si.a("polling_0", strY0));
        }
        return new v2(mapL0, h(268829440), h(268829184), h(268829696), str2, linkedHashMap);
    }

    public final Integer r(rf rfVar) {
        rfVar.getClass();
        int i = rfVar.a;
        Integer numK = k(i, rfVar.b);
        if (numK == null) {
            return null;
        }
        int iIntValue = numK.intValue();
        if (rfVar.c(Integer.valueOf(iIntValue)) != null || iIntValue == i + 15) {
            return numK;
        }
        return null;
    }

    public final boolean s(Integer num) {
        if (num != null && num.intValue() == 0) {
            this.s = false;
            return false;
        }
        if (num == null || num.intValue() < 50) {
            return this.s;
        }
        return true;
    }

    public final Integer t() {
        int iIntValue;
        Integer numK = k(553846272, 4);
        if (numK == null || (iIntValue = numK.intValue()) < 0 || iIntValue >= 101) {
            numK = null;
        }
        if (!k8.e(this.a.getSharedPreferences("panel", 0).getString("vehicle", null), "livan_x6") || numK == null) {
            return numK;
        }
        if (numK.intValue() > 0) {
            this.t = true;
        }
        if (this.t) {
            return numK;
        }
        return null;
    }

    public final ClassLoader u() {
        Object ffVar;
        ClassLoader classLoader = this.h;
        if (classLoader == null) {
            classLoader = this.a.getClassLoader();
            try {
                ffVar = classLoader.loadClass("com.ecarx.xui.adaptapi.car.impl.CarImpl");
            } catch (Throwable th) {
                ffVar = new ff(th);
            }
            if (ffVar instanceof ff) {
                classLoader = new PathClassLoader("/system/framework/AdapterAPI.jar:/system/framework/AdapterAPIImpl.jar:/system/framework/ecarx.jar:/system/framework/ecarxSignalAPI.jar", classLoader);
            }
            this.h = classLoader;
            classLoader.getClass();
        }
        return classLoader;
    }

    /* JADX WARN: Code duplicated, block: B:17:0x002d  */
    /* JADX WARN: Code duplicated, block: B:18:0x002f A[Catch: all -> 0x0027, PHI: r3
      0x002f: PHI (r3v1 java.lang.Object) = (r3v0 java.lang.Object), (r3v5 java.lang.Object) binds: [B:9:0x0011, B:16:0x002b] A[DONT_GENERATE, DONT_INLINE], TryCatch #0 {all -> 0x0027, blocks: (B:8:0x000f, B:10:0x0013, B:12:0x0023, B:18:0x002f, B:20:0x0043, B:24:0x005c, B:26:0x006e, B:29:0x0074), top: B:44:0x000f }] */
    /* JADX WARN: Code duplicated, block: B:20:0x0043 A[Catch: all -> 0x0027, TryCatch #0 {all -> 0x0027, blocks: (B:8:0x000f, B:10:0x0013, B:12:0x0023, B:18:0x002f, B:20:0x0043, B:24:0x005c, B:26:0x006e, B:29:0x0074), top: B:44:0x000f }] */
    /* JADX WARN: Code duplicated, block: B:22:0x0059  */
    /* JADX WARN: Code duplicated, block: B:23:0x005b  */
    /* JADX WARN: Code duplicated, block: B:26:0x006e A[Catch: all -> 0x0027, TryCatch #0 {all -> 0x0027, blocks: (B:8:0x000f, B:10:0x0013, B:12:0x0023, B:18:0x002f, B:20:0x0043, B:24:0x005c, B:26:0x006e, B:29:0x0074), top: B:44:0x000f }] */
    /* JADX WARN: Code duplicated, block: B:27:0x0071  */
    /* JADX WARN: Code duplicated, block: B:29:0x0074 A[Catch: all -> 0x0027, TRY_LEAVE, TryCatch #0 {all -> 0x0027, blocks: (B:8:0x000f, B:10:0x0013, B:12:0x0023, B:18:0x002f, B:20:0x0043, B:24:0x005c, B:26:0x006e, B:29:0x0074), top: B:44:0x000f }] */
    public final Float v(int i) {
        Object obj;
        Object ffVar;
        ConcurrentHashMap concurrentHashMap;
        String strConcat;
        Object obj2;
        Object objInvoke;
        Float f;
        float fFloatValue;
        Float f2;
        Method method;
        Object objPutIfAbsent;
        if (!m() || (obj = this.i) == null) {
            return null;
        }
        try {
            Object obj3 = this.m;
            if (obj3 != null) {
                concurrentHashMap = this.c;
                strConcat = obj3.getClass().getName().concat("#getSensorLatestValue/1");
                obj2 = concurrentHashMap.get(strConcat);
                if (obj2 == null) {
                    method = obj3.getClass().getMethod("getSensorLatestValue", this.f);
                    objPutIfAbsent = concurrentHashMap.putIfAbsent(strConcat, method);
                    if (objPutIfAbsent == null) {
                        obj2 = method;
                    } else {
                        obj2 = objPutIfAbsent;
                    }
                }
                objInvoke = ((Method) obj2).invoke(obj3, Integer.valueOf(i));
                if (objInvoke instanceof Float) {
                    f2 = (Float) objInvoke;
                } else {
                    f = null;
                }
                if (f != null) {
                    f = f2;
                    fFloatValue = f.floatValue();
                    if (Math.abs(fFloatValue) <= Float.MAX_VALUE || fFloatValue <= -2.0E9f || fFloatValue == Float.MIN_VALUE) {
                        ffVar = f;
                        f = f2;
                        ffVar = null;
                    }
                } else {
                    ffVar = f;
                    f = f2;
                    ffVar = null;
                }
            } else {
                Object objInvoke2 = obj.getClass().getMethod("getSensorManager", null).invoke(obj, null);
                if (objInvoke2 != null) {
                    this.m = objInvoke2;
                    obj3 = objInvoke2;
                } else {
                    obj3 = null;
                }
                if (obj3 == null) {
                    ffVar = f;
                    f = f2;
                    ffVar = null;
                } else {
                    concurrentHashMap = this.c;
                    strConcat = obj3.getClass().getName().concat("#getSensorLatestValue/1");
                    obj2 = concurrentHashMap.get(strConcat);
                    if (obj2 == null) {
                        method = obj3.getClass().getMethod("getSensorLatestValue", this.f);
                        objPutIfAbsent = concurrentHashMap.putIfAbsent(strConcat, method);
                        if (objPutIfAbsent == null) {
                            obj2 = method;
                        } else {
                            obj2 = objPutIfAbsent;
                        }
                    }
                    objInvoke = ((Method) obj2).invoke(obj3, Integer.valueOf(i));
                    if (objInvoke instanceof Float) {
                        f2 = (Float) objInvoke;
                    } else {
                        f = null;
                    }
                    if (f != null) {
                        f = f2;
                        fFloatValue = f.floatValue();
                        if (Math.abs(fFloatValue) <= Float.MAX_VALUE) {
                            ffVar = f;
                            f = f2;
                            ffVar = null;
                        } else {
                            ffVar = f;
                            f = f2;
                            ffVar = null;
                        }
                    } else {
                        ffVar = f;
                        f = f2;
                        ffVar = null;
                    }
                }
            }
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        ffVar = f;
        return (Float) (ffVar instanceof ff ? null : ffVar);
    }

    public final boolean w(int i, int i2) {
        Class cls = this.f;
        boolean zE = k8.e(b("setFunctionValue", new Class[]{cls, cls}, true, Integer.valueOf(i), Integer.valueOf(i2)), Boolean.TRUE);
        List list = z2.a;
        if (z2.e().containsKey(Integer.valueOf(i))) {
            List list2 = si.a;
            K(si.a("command_0_no_zone_1_accepted_by", z2.e().get(Integer.valueOf(i)), Integer.valueOf(i2), Boolean.valueOf(zE)));
        }
        return zE;
    }

    public final boolean x(int i, int i2, int i3) {
        Class cls = this.f;
        boolean zE = k8.e(b("setFunctionValue", new Class[]{cls, cls, cls}, true, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)), Boolean.TRUE);
        List list = z2.a;
        if (z2.e().containsKey(Integer.valueOf(i))) {
            List list2 = si.a;
            K(si.a("command_0_1_2_accepted_by_sdk3", z2.e().get(Integer.valueOf(i)), Integer.valueOf(i2), Integer.valueOf(i3), Boolean.valueOf(zE)));
        }
        return zE;
    }

    public final boolean y(int i, int i2) {
        List listI0 = j3.i0(Integer.MIN_VALUE, 0, 1);
        if (!listI0.isEmpty()) {
            Iterator it = listI0.iterator();
            while (it.hasNext()) {
                if (x(i, ((Number) it.next()).intValue(), i2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean z(int i, int i2) {
        List list = z2.a;
        if (i != 269025536) {
            return this.o.f(i, i2);
        }
        List list2 = z2.a;
        Integer numI = z2.i(Integer.valueOf(i2));
        if (numI != null) {
            return D(numI.intValue());
        }
        return false;
    }
}
