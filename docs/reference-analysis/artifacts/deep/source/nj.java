package defpackage;

import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class nj {
    public long A;
    public final Context a;
    public final v1 b;
    public final Handler c = new Handler(Looper.getMainLooper());
    public volatile ExecutorService d;
    public final ExecutorService e;
    public volatile bj f;
    public volatile boolean g;
    public LinkedHashMap h;
    public Object i;
    public final ConcurrentHashMap j;
    public Object k;
    public volatile Object l;
    public final fi m;
    public volatile boolean n;
    public volatile int o;
    public volatile int p;
    public boolean q;
    public long r;
    public volatile Set s;
    public final r1 t;
    public volatile bj u;
    public long v;
    public volatile long w;
    public final hj x;
    public final gj y;
    public boolean z;

    public nj(Context context, v1 v1Var) {
        this.a = context;
        this.b = v1Var;
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        executorServiceNewSingleThreadExecutor.getClass();
        this.d = executorServiceNewSingleThreadExecutor;
        ExecutorService executorServiceNewSingleThreadExecutor2 = Executors.newSingleThreadExecutor();
        executorServiceNewSingleThreadExecutor2.getClass();
        this.e = executorServiceNewSingleThreadExecutor2;
        this.f = new bj(null, null, null, null, null, null, 67108863);
        this.j = new ConcurrentHashMap();
        int i = 0;
        this.m = new fi(new gj(this, i));
        this.s = x4.a;
        this.t = new r1(14, this);
        this.x = new hj(this, i);
        this.y = new gj(this, 1);
    }

    public static final String k(byte[] bArr) {
        return bArr != null ? x0.U0(bArr, " ", new fg(11)) : si.a("no", new Object[0]);
    }

    public static final Object l(Map map, Method method, Object obj, ArrayList arrayList, int i) {
        Object ffVar;
        Method method2;
        Object objInvoke;
        if (!map.containsKey(Integer.valueOf(i))) {
            return null;
        }
        if (method != null) {
            try {
                int i2 = 0;
                Object objInvoke2 = method.invoke(obj, Float.class, Integer.valueOf(i), 0);
                if (objInvoke2 != null) {
                    Method[] methods = objInvoke2.getClass().getMethods();
                    methods.getClass();
                    int length = methods.length;
                    while (true) {
                        if (i2 >= length) {
                            method2 = null;
                            break;
                        }
                        method2 = methods[i2];
                        if (k8.e(method2.getName(), "getStatus") && method2.getParameterCount() == 0) {
                            break;
                        }
                        i2++;
                    }
                    Object objInvoke3 = method2 != null ? method2.invoke(objInvoke2, null) : null;
                    Integer num = objInvoke3 instanceof Integer ? (Integer) objInvoke3 : null;
                    if (num == null || num.intValue() == 0) {
                        objInvoke = objInvoke2.getClass().getMethod("getValue", null).invoke(objInvoke2, null);
                    }
                }
                objInvoke = null;
            } catch (Throwable th) {
                ffVar = new ff(th);
            }
        } else {
            objInvoke = null;
        }
        ffVar = objInvoke;
        if (gf.a(ffVar) != null) {
            List list = si.a;
            pi.m(16);
            String string = Integer.toString(i, 16);
            string.getClass();
            arrayList.add(si.a("0x0_unavailable", string));
        }
        if (ffVar instanceof ff) {
            return null;
        }
        return ffVar;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0031  */
    public static final byte[] m(Object obj, Method method, r4 r4Var, int i) {
        Object ffVar;
        if (obj != null && method != null) {
            try {
                Object objInvoke = method.invoke(obj, byte[].class, Integer.valueOf(i), 0);
                if (objInvoke != null) {
                    Object objInvoke2 = objInvoke.getClass().getMethod("getValue", null).invoke(objInvoke, null);
                    if (objInvoke2 instanceof byte[]) {
                        ffVar = (byte[]) objInvoke2;
                    } else {
                        ffVar = null;
                    }
                } else {
                    ffVar = null;
                }
            } catch (Throwable th) {
                ffVar = new ff(th);
            }
            if (ffVar instanceof ff) {
                ffVar = null;
            }
            byte[] bArr = (byte[]) ffVar;
            if (bArr != null) {
                return bArr;
            }
        }
        if (r4Var != null) {
            return r4Var.L(i);
        }
        return null;
    }

    public static boolean n(r4 r4Var, int i, int i2) {
        List list = z2.a;
        if (i == 268566784 && i2 == 268566794) {
            return n(r4Var, 268501504, 1);
        }
        r4Var.o(z2.e().get(Integer.valueOf(i)) + " → " + z2.g(i, i2));
        return r4Var.z(i, i2);
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0117 A[PHI: r22 r33 r35
      0x0117: PHI (r22v12 java.lang.Object) =
      (r22v9 java.lang.Object)
      (r22v19 java.lang.Object)
      (r22v19 java.lang.Object)
      (r22v19 java.lang.Object)
      (r22v19 java.lang.Object)
      (r22v19 java.lang.Object)
      (r22v19 java.lang.Object)
     binds: [B:68:0x01dd, B:37:0x0133, B:39:0x0141, B:32:0x011e, B:34:0x012c, B:25:0x010d, B:28:0x0114] A[DONT_GENERATE, DONT_INLINE]
      0x0117: PHI (r33v6 java.lang.String) =
      (r33v3 java.lang.String)
      (r33v8 java.lang.String)
      (r33v8 java.lang.String)
      (r33v8 java.lang.String)
      (r33v8 java.lang.String)
      (r33v8 java.lang.String)
      (r33v8 java.lang.String)
     binds: [B:68:0x01dd, B:37:0x0133, B:39:0x0141, B:32:0x011e, B:34:0x012c, B:25:0x010d, B:28:0x0114] A[DONT_GENERATE, DONT_INLINE]
      0x0117: PHI (r35v11 java.util.Map) =
      (r35v8 java.util.Map)
      (r35v16 java.util.Map)
      (r35v16 java.util.Map)
      (r35v16 java.util.Map)
      (r35v16 java.util.Map)
      (r35v16 java.util.Map)
      (r35v16 java.util.Map)
     binds: [B:68:0x01dd, B:37:0x0133, B:39:0x0141, B:32:0x011e, B:34:0x012c, B:25:0x010d, B:28:0x0114] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:60:0x019e  */
    /* JADX WARN: Code duplicated, block: B:61:0x01a3  */
    /* JADX WARN: Failed to find 'out' block for switch in B:110:0x02cd. Please report as an issue. */
    public final bj a(bj bjVar) {
        Object obj;
        Object obj2;
        Object obj3;
        Boolean bool;
        String str;
        n4 n4Var;
        Boolean bool2;
        lj ljVar;
        ConcurrentHashMap concurrentHashMap;
        int i;
        ConcurrentHashMap concurrentHashMap2 = this.j;
        if (concurrentHashMap2.isEmpty()) {
            return bjVar;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        Map map = bjVar.j;
        v2 v2Var = bjVar.i;
        Map map2 = v2Var.f;
        Map map3 = v2Var.a;
        Boolean bool3 = bjVar.n;
        Boolean bool4 = bjVar.u;
        Integer num = bjVar.m;
        Boolean bool5 = bjVar.e;
        Integer num2 = bjVar.o;
        Boolean bool6 = bjVar.q;
        Boolean bool7 = bjVar.w;
        Set setEntrySet = concurrentHashMap2.entrySet();
        setEntrySet.getClass();
        Iterator it = i3.J0(setEntrySet).iterator();
        Map map4 = map3;
        Integer num3 = num;
        Boolean bool8 = bool7;
        Boolean bool9 = bool4;
        Boolean bool10 = bool6;
        Boolean bool11 = bool3;
        Boolean bool12 = bool5;
        Integer num4 = num2;
        long j = jElapsedRealtime;
        while (it.hasNext()) {
            Object next = it.next();
            next.getClass();
            Map.Entry entry = (Map.Entry) next;
            Object key = entry.getKey();
            key.getClass();
            String str2 = (String) key;
            Object value = entry.getValue();
            value.getClass();
            j = j;
            lj ljVar2 = (lj) value;
            Object obj4 = ljVar2.a;
            it = it;
            v2Var = v2Var;
            String strF = th.F(str2, ':');
            concurrentHashMap2 = concurrentHashMap2;
            int iZ = th.z(str2, ':', 0, 6);
            String strSubstring = iZ == -1 ? "" : str2.substring(iZ + 1, str2.length());
            Boolean bool13 = bool10;
            String str3 = "roof_tilt";
            Integer num5 = num3;
            Boolean bool14 = bool11;
            Object obj5 = "roof_open";
            Boolean bool15 = bool9;
            boolean zE = true;
            Object obj6 = null;
            switch (strF.hashCode()) {
                case -1573164609:
                    map2 = map2;
                    obj = "climate";
                    obj5 = obj5;
                    bool9 = bool15;
                    bool12 = bool12;
                    obj2 = "roof_position";
                    num4 = num4;
                    obj3 = "start_stop";
                    if (!strF.equals(obj3)) {
                        bool = bool13;
                    } else {
                        bool = bool13;
                        zE = k8.e(bool, obj4);
                    }
                    break;
                case -1564215244:
                    map2 = map2;
                    obj = "climate";
                    obj5 = obj5;
                    bool9 = bool15;
                    bool12 = bool12;
                    obj2 = "roof_position";
                    if (strF.equals(obj2)) {
                        zE = k8.e(num5, obj4);
                        obj3 = "start_stop";
                    } else {
                        num4 = num4;
                        obj3 = "start_stop";
                    }
                    bool = bool13;
                    break;
                case -1267940651:
                    map2 = map2;
                    obj = "climate";
                    obj5 = obj5;
                    bool9 = bool15;
                    if (strF.equals(obj5)) {
                        zE = k8.e(bool14, obj4);
                        obj2 = "roof_position";
                        obj3 = "start_stop";
                        bool = bool13;
                    }
                    bool12 = bool12;
                    obj2 = "roof_position";
                    num4 = num4;
                    obj3 = "start_stop";
                    bool = bool13;
                    break;
                case -1267798200:
                    map2 = map2;
                    obj = "climate";
                    if (strF.equals("roof_tilt")) {
                        bool9 = bool15;
                        zE = k8.e(bool9, obj4);
                        obj2 = "roof_position";
                        obj3 = "start_stop";
                        bool = bool13;
                    }
                    obj5 = obj5;
                    bool9 = bool15;
                    bool12 = bool12;
                    obj2 = "roof_position";
                    num4 = num4;
                    obj3 = "start_stop";
                    bool = bool13;
                    break;
                case -892145000:
                    str = "roof_tilt";
                    map2 = map2;
                    obj = "climate";
                    if (strF.equals("ambient")) {
                        zE = k8.e(bool12, obj4);
                        bool9 = bool15;
                        str3 = str;
                        obj2 = "roof_position";
                        obj3 = "start_stop";
                        bool = bool13;
                    }
                    obj5 = obj5;
                    bool9 = bool15;
                    str3 = str;
                    bool12 = bool12;
                    obj2 = "roof_position";
                    num4 = num4;
                    obj3 = "start_stop";
                    bool = bool13;
                    break;
                case -841772073:
                    str = "roof_tilt";
                    map2 = map2;
                    obj = "climate";
                    if (strF.equals("curtain_open")) {
                        zE = k8.e(bool8, obj4);
                        bool9 = bool15;
                        str3 = str;
                        obj2 = "roof_position";
                        obj3 = "start_stop";
                        bool = bool13;
                    }
                    obj5 = obj5;
                    bool9 = bool15;
                    str3 = str;
                    bool12 = bool12;
                    obj2 = "roof_position";
                    num4 = num4;
                    obj3 = "start_stop";
                    bool = bool13;
                    break;
                case 3526149:
                    str = "roof_tilt";
                    map2 = map2;
                    obj = "climate";
                    if (strF.equals("seat")) {
                        Set set = rf.d;
                        rf rfVar = (rf) pi.B().get(strSubstring);
                        if (rfVar != null) {
                            tf tfVar = (tf) map.get(strSubstring);
                            zE = k8.e(rfVar.c(tfVar != null ? tfVar.a : null), obj4);
                        }
                        bool9 = bool15;
                        str3 = str;
                        obj2 = "roof_position";
                        obj3 = "start_stop";
                        bool = bool13;
                    }
                    obj5 = obj5;
                    bool9 = bool15;
                    str3 = str;
                    bool12 = bool12;
                    obj2 = "roof_position";
                    num4 = num4;
                    obj3 = "start_stop";
                    bool = bool13;
                    break;
                case 3556308:
                    str = "roof_tilt";
                    map2 = map2;
                    obj = "climate";
                    if (strF.equals("temp")) {
                        Float f = (Float) map4.get(Integer.valueOf(Integer.parseInt(strSubstring)));
                        if (f != null) {
                            float fFloatValue = f.floatValue();
                            obj4.getClass();
                            if (Math.abs(fFloatValue - ((Float) obj4).floatValue()) >= 0.05f) {
                                zE = false;
                            }
                            bool9 = bool15;
                            str3 = str;
                        } else {
                            bool9 = bool15;
                            str3 = str;
                            zE = false;
                        }
                        obj2 = "roof_position";
                        obj3 = "start_stop";
                        bool = bool13;
                    }
                    obj5 = obj5;
                    bool9 = bool15;
                    str3 = str;
                    bool12 = bool12;
                    obj2 = "roof_position";
                    num4 = num4;
                    obj3 = "start_stop";
                    bool = bool13;
                    break;
                case 95852938:
                    str = "roof_tilt";
                    map2 = map2;
                    obj = "climate";
                    if (strF.equals("drive")) {
                        Iterator it2 = pi.A().iterator();
                        while (it2.hasNext()) {
                            Object next2 = it2.next();
                            int i2 = ((n4) next2).b;
                            Iterator it3 = it2;
                            if ((obj4 instanceof Integer) && i2 == ((Number) obj4).intValue()) {
                                obj6 = next2;
                                n4Var = (n4) obj6;
                                if (n4Var != null) {
                                    zE = n4Var.a(num4);
                                } else {
                                    zE = k8.e(num4, obj4);
                                }
                                bool9 = bool15;
                                str3 = str;
                                obj2 = "roof_position";
                                obj3 = "start_stop";
                                bool = bool13;
                            } else {
                                it2 = it3;
                            }
                            break;
                        }
                        n4Var = (n4) obj6;
                        if (n4Var != null) {
                            zE = n4Var.a(num4);
                        } else {
                            zE = k8.e(num4, obj4);
                        }
                        bool9 = bool15;
                        str3 = str;
                        obj2 = "roof_position";
                        obj3 = "start_stop";
                        bool = bool13;
                    }
                    obj5 = obj5;
                    bool9 = bool15;
                    str3 = str;
                    bool12 = bool12;
                    obj2 = "roof_position";
                    num4 = num4;
                    obj3 = "start_stop";
                    bool = bool13;
                    break;
                case 860813349:
                    if (strF.equals("climate")) {
                        List list = z2.a;
                        obj = "climate";
                        int i3 = Integer.parseInt(strSubstring);
                        str = "roof_tilt";
                        Integer num6 = (Integer) map2.get(Integer.valueOf(Integer.parseInt(strSubstring)));
                        obj4.getClass();
                        Integer num7 = (Integer) obj4;
                        int iIntValue = num7.intValue();
                        map2 = map2;
                        if (i3 == 268566784 ? z2.c(num6) == null || !k8.e(z2.c(num6), z2.c(num7)) : i3 == 269025536 ? z2.i(num6) == null || !k8.e(z2.i(num6), z2.i(num7)) : num6 == null || num6.intValue() != iIntValue) {
                            zE = false;
                        }
                        bool9 = bool15;
                        str3 = str;
                        obj2 = "roof_position";
                        obj3 = "start_stop";
                        bool = bool13;
                        break;
                    }
                default:
                    map2 = map2;
                    obj = "climate";
                    obj5 = obj5;
                    bool9 = bool15;
                    bool12 = bool12;
                    obj2 = "roof_position";
                    num4 = num4;
                    obj3 = "start_stop";
                    bool = bool13;
                    break;
            }
            if (!zE) {
                Object obj7 = obj3;
                bool2 = bool;
                ljVar = ljVar2;
                Object obj8 = obj2;
                if (j <= ljVar.b) {
                    switch (strF.hashCode()) {
                        case -1573164609:
                            if (!strF.equals(obj7)) {
                                concurrentHashMap = concurrentHashMap2;
                                it = it;
                                concurrentHashMap2 = concurrentHashMap;
                                v2Var = v2Var;
                                j = j;
                                bool10 = bool2;
                                bool8 = bool8;
                                num3 = num5;
                                num4 = num4;
                                bool11 = bool14;
                                bool12 = bool12;
                            } else {
                                obj4.getClass();
                                bool10 = (Boolean) obj4;
                                bool8 = bool8;
                                num3 = num5;
                                num4 = num4;
                                bool11 = bool14;
                                bool12 = bool12;
                            }
                            break;
                        case -1564215244:
                            if (!strF.equals(obj8)) {
                                concurrentHashMap = concurrentHashMap2;
                                it = it;
                                concurrentHashMap2 = concurrentHashMap;
                                v2Var = v2Var;
                                j = j;
                                bool10 = bool2;
                                bool8 = bool8;
                                num3 = num5;
                                num4 = num4;
                                bool11 = bool14;
                                bool12 = bool12;
                            } else {
                                obj4.getClass();
                                num3 = (Integer) obj4;
                                it = it;
                                v2Var = v2Var;
                                j = j;
                                concurrentHashMap2 = concurrentHashMap2;
                                bool10 = bool2;
                                bool8 = bool8;
                                num4 = num4;
                                bool11 = bool14;
                                bool12 = bool12;
                            }
                            break;
                        case -1267940651:
                            if (!strF.equals(obj5)) {
                                concurrentHashMap = concurrentHashMap2;
                                it = it;
                                concurrentHashMap2 = concurrentHashMap;
                                v2Var = v2Var;
                                j = j;
                                bool10 = bool2;
                                bool8 = bool8;
                                num3 = num5;
                                num4 = num4;
                                bool11 = bool14;
                                bool12 = bool12;
                            } else {
                                obj4.getClass();
                                bool11 = (Boolean) obj4;
                                it = it;
                                v2Var = v2Var;
                                j = j;
                                concurrentHashMap2 = concurrentHashMap2;
                                bool10 = bool2;
                                bool8 = bool8;
                                num3 = num5;
                                num4 = num4;
                                bool12 = bool12;
                            }
                            break;
                        case -1267798200:
                            if (!strF.equals(str3)) {
                                concurrentHashMap = concurrentHashMap2;
                                it = it;
                                concurrentHashMap2 = concurrentHashMap;
                                v2Var = v2Var;
                                j = j;
                                bool10 = bool2;
                                bool8 = bool8;
                                num3 = num5;
                                num4 = num4;
                                bool11 = bool14;
                                bool12 = bool12;
                            } else {
                                obj4.getClass();
                                bool9 = (Boolean) obj4;
                                bool10 = bool2;
                                bool8 = bool8;
                                num3 = num5;
                                num4 = num4;
                                bool11 = bool14;
                                bool12 = bool12;
                            }
                            break;
                        case -892145000:
                            if (!strF.equals("ambient")) {
                                concurrentHashMap = concurrentHashMap2;
                                it = it;
                                concurrentHashMap2 = concurrentHashMap;
                                v2Var = v2Var;
                                j = j;
                                bool10 = bool2;
                                bool8 = bool8;
                                num3 = num5;
                                num4 = num4;
                                bool11 = bool14;
                                bool12 = bool12;
                            } else {
                                obj4.getClass();
                                bool12 = (Boolean) obj4;
                                it = it;
                                v2Var = v2Var;
                                j = j;
                                concurrentHashMap2 = concurrentHashMap2;
                                bool10 = bool2;
                                bool8 = bool8;
                                num3 = num5;
                                num4 = num4;
                                bool11 = bool14;
                            }
                            break;
                        case -841772073:
                            if (!strF.equals("curtain_open")) {
                                concurrentHashMap = concurrentHashMap2;
                                it = it;
                                concurrentHashMap2 = concurrentHashMap;
                                v2Var = v2Var;
                                j = j;
                                bool10 = bool2;
                                bool8 = bool8;
                                num3 = num5;
                                num4 = num4;
                                bool11 = bool14;
                                bool12 = bool12;
                            } else {
                                obj4.getClass();
                                bool8 = (Boolean) obj4;
                                it = it;
                                v2Var = v2Var;
                                j = j;
                                concurrentHashMap2 = concurrentHashMap2;
                                bool10 = bool2;
                                num3 = num5;
                                num4 = num4;
                                bool11 = bool14;
                                bool12 = bool12;
                            }
                            break;
                        case 3526149:
                            if (!strF.equals("seat")) {
                                concurrentHashMap = concurrentHashMap2;
                                it = it;
                                concurrentHashMap2 = concurrentHashMap;
                                v2Var = v2Var;
                                j = j;
                                bool10 = bool2;
                                bool8 = bool8;
                                num3 = num5;
                                num4 = num4;
                                bool11 = bool14;
                                bool12 = bool12;
                            } else {
                                Set set2 = rf.d;
                                rf rfVar2 = (rf) hb.g0(pi.B(), strSubstring);
                                obj4.getClass();
                                int iIntValue2 = ((Integer) obj4).intValue();
                                if (iIntValue2 <= 0) {
                                    rfVar2.getClass();
                                    i = 0;
                                } else {
                                    int i4 = rfVar2.a;
                                    int iA = rfVar2.a();
                                    if (iIntValue2 > iA) {
                                        iIntValue2 = iA;
                                    }
                                    i = i4 + iIntValue2;
                                }
                                tf tfVar2 = new tf(Integer.valueOf(i));
                                map.getClass();
                                if (map.isEmpty()) {
                                    Map mapSingletonMap = Collections.singletonMap(strSubstring, tfVar2);
                                    mapSingletonMap.getClass();
                                    map = mapSingletonMap;
                                } else {
                                    LinkedHashMap linkedHashMap = new LinkedHashMap(map);
                                    linkedHashMap.put(strSubstring, tfVar2);
                                    map = linkedHashMap;
                                }
                                bool10 = bool2;
                                bool8 = bool8;
                                num3 = num5;
                                num4 = num4;
                                bool11 = bool14;
                                bool12 = bool12;
                            }
                            break;
                        case 3556308:
                            if (!strF.equals("temp")) {
                                concurrentHashMap = concurrentHashMap2;
                                it = it;
                                concurrentHashMap2 = concurrentHashMap;
                                v2Var = v2Var;
                                j = j;
                                bool10 = bool2;
                                bool8 = bool8;
                                num3 = num5;
                                num4 = num4;
                                bool11 = bool14;
                                bool12 = bool12;
                            } else {
                                Integer numValueOf = Integer.valueOf(Integer.parseInt(strSubstring));
                                obj4.getClass();
                                Float f2 = (Float) obj4;
                                map4.getClass();
                                if (map4.isEmpty()) {
                                    Map mapSingletonMap2 = Collections.singletonMap(numValueOf, f2);
                                    mapSingletonMap2.getClass();
                                    map4 = mapSingletonMap2;
                                } else {
                                    LinkedHashMap linkedHashMap2 = new LinkedHashMap(map4);
                                    linkedHashMap2.put(numValueOf, f2);
                                    map4 = linkedHashMap2;
                                }
                                bool10 = bool2;
                                bool8 = bool8;
                                num3 = num5;
                                num4 = num4;
                                bool11 = bool14;
                                bool12 = bool12;
                            }
                            break;
                        case 95852938:
                            if (!strF.equals("drive")) {
                                concurrentHashMap = concurrentHashMap2;
                                it = it;
                                concurrentHashMap2 = concurrentHashMap;
                                v2Var = v2Var;
                                j = j;
                                bool10 = bool2;
                                bool8 = bool8;
                                num3 = num5;
                                num4 = num4;
                                bool11 = bool14;
                                bool12 = bool12;
                            } else {
                                obj4.getClass();
                                num4 = (Integer) obj4;
                                it = it;
                                v2Var = v2Var;
                                j = j;
                                concurrentHashMap2 = concurrentHashMap2;
                                bool10 = bool2;
                                bool8 = bool8;
                                num3 = num5;
                                bool11 = bool14;
                                bool12 = bool12;
                            }
                            break;
                        case 860813349:
                            strF.equals(obj);
                            concurrentHashMap = concurrentHashMap2;
                            it = it;
                            concurrentHashMap2 = concurrentHashMap;
                            v2Var = v2Var;
                            j = j;
                            bool10 = bool2;
                            bool8 = bool8;
                            num3 = num5;
                            num4 = num4;
                            bool11 = bool14;
                            bool12 = bool12;
                            break;
                        default:
                            bool8 = bool8;
                            it = it;
                            v2Var = v2Var;
                            j = j;
                            concurrentHashMap2 = concurrentHashMap2;
                            bool10 = bool2;
                            num3 = num5;
                            num4 = num4;
                            bool11 = bool14;
                            bool12 = bool12;
                            break;
                    }
                }
            } else {
                bool2 = bool;
                ljVar = ljVar2;
            }
            concurrentHashMap = concurrentHashMap2;
            concurrentHashMap.remove(str2, ljVar);
            it = it;
            concurrentHashMap2 = concurrentHashMap;
            v2Var = v2Var;
            j = j;
            bool10 = bool2;
            bool8 = bool8;
            num3 = num5;
            num4 = num4;
            bool11 = bool14;
            bool12 = bool12;
        }
        v2 v2Var2 = v2Var;
        Integer num8 = num3;
        Boolean bool16 = bool10;
        Boolean bool17 = bool8;
        Boolean bool18 = bool11;
        Float f3 = v2Var2.b;
        Float f4 = v2Var2.c;
        Float f5 = v2Var2.d;
        String str4 = v2Var2.e;
        map4.getClass();
        str4.getClass();
        map2.getClass();
        return bj.a(bjVar, bool12, null, null, new v2(map4, f3, f4, f5, str4, map2), map, null, null, num8, bool18, num4, null, bool16, null, false, null, bool9, null, bool17, null, null, null, 61770991);
    }

    public final void b(j6 j6Var, j6 j6Var2) {
        r4 r4VarG = g();
        if (r4VarG == null) {
            j6Var.b(si.a("ecarx_sdk_not_found_on_this_head", new Object[0]));
        } else if (!r4VarG.m()) {
            j6Var.b(si.a("no_vehicle_connection_waiting", new Object[0]));
        } else {
            this.e.execute(new l(this, j6Var2, r4VarG, j6Var, 5));
        }
    }

    public final void c(String str, j6 j6Var) {
        str.getClass();
        Set set = rf.d;
        rf rfVar = (rf) pi.B().get(str);
        if (rfVar == null) {
            j6Var.b(si.a("unknown_seat", new Object[0]));
        } else {
            b(j6Var, new o0(this, str, rfVar, 7));
        }
    }

    public final void d(String str, Object obj, long j) {
        this.j.put(str, new lj(obj, SystemClock.elapsedRealtime() + j));
    }

    public final void f(boolean z, boolean z2, Integer num) {
        d("roof_open", Boolean.valueOf(z), 12000L);
        d("roof_tilt", Boolean.valueOf(z2), 12000L);
        if (num == null) {
            this.j.remove("roof_position");
        } else {
            d("roof_position", num, 12000L);
        }
    }

    public final r4 g() {
        return (r4) this.m.a();
    }

    public final void h(int i, String str, j6 j6Var, j6 j6Var2) {
        b(j6Var, new fh(i, str, j6Var2));
    }

    public final Object i(String str) {
        lj ljVar = (lj) this.j.get(str);
        if (ljVar != null) {
            if (SystemClock.elapsedRealtime() > ljVar.b) {
                ljVar = null;
            }
            if (ljVar != null) {
                return ljVar.a;
            }
        }
        return null;
    }

    public final void j() {
        final Object obj = this.l;
        final r4 r4VarG = g();
        if (!(obj == null && r4VarG == null) && this.n) {
            if (this.q) {
                this.g = true;
                return;
            }
            this.g = false;
            this.q = true;
            this.r = SystemClock.elapsedRealtime();
            final int i = this.o;
            this.p++;
            final int i2 = this.p;
            final Set set = this.s;
            this.d.execute(new Runnable() { // from class: jj
                /* JADX WARN: Code duplicated, block: B:137:0x02b3  */
                /* JADX WARN: Code duplicated, block: B:56:0x012c  */
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
                /* JADX WARN: Unreachable blocks removed: 2, instructions: 3 */
                @Override // java.lang.Runnable
                public final void run() {
                    Object ffVar;
                    Map map;
                    Method method;
                    Float f;
                    Float f2;
                    String str;
                    Object obj2;
                    Float fValueOf;
                    String strA;
                    Float fV;
                    Object ffVar2;
                    bj bjVarF;
                    final nj njVar = this.a;
                    Object obj3 = obj;
                    r4 r4Var = r4VarG;
                    Set set2 = set;
                    final int i3 = i2;
                    final int i4 = i;
                    long jElapsedRealtime = SystemClock.elapsedRealtime();
                    bj bjVar = njVar.u;
                    boolean z = bjVar == null || jElapsedRealtime - njVar.w >= 10000;
                    if (bjVar == null || z) {
                        try {
                            if (obj3 == null) {
                                map = v4.a;
                            } else {
                                map = njVar.h;
                                if (njVar.i != obj3) {
                                    map = null;
                                }
                                if (map == null) {
                                    Object objInvoke = obj3.getClass().getMethod("getPropertyList", null).invoke(obj3, null);
                                    objInvoke.getClass();
                                    ArrayList arrayListT0 = i3.t0((List) objInvoke);
                                    int iI0 = hb.i0(k3.n0(arrayListT0));
                                    if (iI0 < 16) {
                                        iI0 = 16;
                                    }
                                    LinkedHashMap linkedHashMap = new LinkedHashMap(iI0);
                                    int size = arrayListT0.size();
                                    int i5 = 0;
                                    while (i5 < size) {
                                        Object obj4 = arrayListT0.get(i5);
                                        i5++;
                                        Object objInvoke2 = obj4.getClass().getMethod("getPropertyId", null).invoke(obj4, null);
                                        objInvoke2.getClass();
                                        linkedHashMap.put((Integer) objInvoke2, obj4);
                                        arrayListT0 = arrayListT0;
                                    }
                                    njVar.h = linkedHashMap;
                                    njVar.i = obj3;
                                    map = linkedHashMap;
                                }
                            }
                            if (obj3 != null) {
                                Class<?> cls = obj3.getClass();
                                Class cls2 = Integer.TYPE;
                                method = cls.getMethod("getProperty", Class.class, cls2, cls2);
                            } else {
                                method = null;
                            }
                            ArrayList arrayList = new ArrayList();
                            Object objL = nj.l(map, method, obj3, arrayList, 291504903);
                            Float f3 = objL instanceof Float ? (Float) objL : null;
                            Object objL2 = nj.l(map, method, obj3, arrayList, 291504388);
                            Float f4 = objL2 instanceof Float ? (Float) objL2 : null;
                            byte[] bArrM = nj.m(obj3, method, r4Var, 678429191);
                            byte[] bArrM2 = nj.m(obj3, method, r4Var, 678428900);
                            Float fV2 = r4Var != null ? r4Var.v(1050624) : null;
                            Iterator it = j3.i0(1050112, 4211968).iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    f = null;
                                    break;
                                }
                                int iIntValue = ((Number) it.next()).intValue();
                                if (r4Var == null || (fV = r4Var.v(iIntValue)) == null) {
                                    fV = null;
                                } else {
                                    float fFloatValue = fV.floatValue();
                                    if (0.1f > fFloatValue || fFloatValue > 100.0f) {
                                        fV = null;
                                    }
                                }
                                if (fV != null) {
                                    f = fV;
                                    break;
                                }
                            }
                            Float fV3 = (sh.k(fV2) != null || r4Var == null) ? null : r4Var.v(4211456);
                            Float fD = sh.d(bArrM);
                            if (fD != null) {
                                f2 = fD;
                            } else if (f == null) {
                                fD = (f3 == null || f4 == null || Math.abs(f3.floatValue()) > Float.MAX_VALUE || Math.abs(f4.floatValue()) > Float.MAX_VALUE || f4.floatValue() <= 0.0f || f3.floatValue() <= 0.0f || f3.floatValue() > f4.floatValue()) ? null : Float.valueOf((f3.floatValue() / f4.floatValue()) * 100.0f);
                                f2 = fD;
                            } else {
                                f2 = f;
                            }
                            Float fA = sh.a(bArrM2);
                            Float fK = sh.k(fV2);
                            if (fK == null) {
                                fK = sh.k(fV3);
                            }
                            Float f5 = fK;
                            List list = si.a;
                            Float f6 = fV3;
                            String strA2 = si.a("fuel_remainingoil_0_1", nj.k(bArrM), f2 == null ? "—" : f2);
                            String strA3 = si.a("voltage_vpowerinfobatteryvol_0_1_v", nj.k(bArrM2), fA == null ? "—" : fA);
                            Object objA = f;
                            if (f == null) {
                                objA = si.a("no", new Object[0]);
                            }
                            String strA4 = si.a("fuel_isensor_sensors_0", objA);
                            Object obj5 = fV2;
                            if (fV2 == null) {
                                strA = si.a("no", new Object[0]);
                            }
                            if (f6 != null) {
                                str = " / POTENTIAL: " + f6.floatValue();
                            } else {
                                str = "";
                            }
                            if (f5 == null) {
                                obj5 = strA;
                                obj5 = strA;
                                obj2 = "—";
                            } else {
                                obj5 = strA;
                                obj5 = strA;
                                obj2 = f5;
                            }
                            ArrayList arrayListD0 = i3.D0(j3.i0(strA2, strA3, strA4, si.a("range_endurancemileage_01_2_km", obj5, str, obj2), si.a("command_profile_0", (r4Var == null || !r4Var.l()) ? si.a("generic_livan_knewstar_belgee_geely", new Object[0]) : "Geely Monjaro"), "CarPropertyManager: " + (obj3 != null ? si.a("yes", new Object[0]) : si.a("no", new Object[0]))), i3.J0(i3.K0(arrayList)));
                            if (f3 != null) {
                                float fFloatValue2 = f3.floatValue();
                                if (Math.abs(fFloatValue2) > Float.MAX_VALUE || fFloatValue2 <= 0.0f || fFloatValue2 > 500000.0f) {
                                    f3 = null;
                                }
                                if (f3 != null) {
                                    fValueOf = Float.valueOf(f3.floatValue() / 1000.0f);
                                } else {
                                    fValueOf = null;
                                }
                            } else {
                                fValueOf = null;
                            }
                            ffVar = new bj(fA, fValueOf, f2, f5, (fA == null && f2 == null && f5 == null) ? si.a("data_unavailable", new Object[0]) : si.a("vehicle_data", new Object[0]), i3.y0(arrayListD0, "\n", null, 62), 67105776);
                        } catch (Throwable th) {
                            ffVar = new ff(th);
                        }
                        if (gf.a(ffVar) != null) {
                            ffVar = new bj(null, null, null, null, si.a("no_access_to_vehicle_data", new Object[0]), null, 67107839);
                        }
                        bjVar = (bj) ffVar;
                    }
                    bj bjVar2 = bjVar;
                    if (r4Var != null) {
                        try {
                            bjVarF = r4Var.f(bjVar2, set2, z);
                        } catch (Throwable th2) {
                            ffVar2 = new ff(th2);
                        }
                    } else {
                        bjVarF = bjVar2;
                    }
                    ffVar2 = bjVarF;
                    Object obj6 = bjVar2;
                    if (!(ffVar2 instanceof ff)) {
                        obj6 = ffVar2;
                    }
                    final bj bjVar3 = (bj) obj6;
                    if (z) {
                        njVar.u = bjVar3;
                        njVar.w = jElapsedRealtime;
                    }
                    njVar.c.post(new Runnable() { // from class: kj
                        @Override // java.lang.Runnable
                        public final void run() {
                            nj njVar2 = njVar;
                            int i6 = i3;
                            int i7 = i4;
                            bj bjVar4 = bjVar3;
                            if (njVar2.p == i6) {
                                njVar2.q = false;
                            }
                            if (njVar2.n && njVar2.o == i7) {
                                njVar2.f = bjVar4;
                                njVar2.b.b(njVar2.a(bjVar4));
                            }
                            if (njVar2.n && njVar2.g) {
                                njVar2.j();
                            }
                        }
                    });
                }
            });
        }
    }

    public final void o(String str, j6 j6Var) {
        str.getClass();
        n4 n4VarJ = pi.j(str);
        if (n4VarJ == null) {
            j6Var.b(si.a("unknown_mode", new Object[0]));
        } else {
            b(j6Var, new q0(n4VarJ, this, 10));
        }
    }

    public final void p() {
        if (this.n) {
            return;
        }
        this.n = true;
        this.o++;
        int i = this.o;
        this.h = null;
        this.i = null;
        if (SystemClock.elapsedRealtime() - this.v > 60000) {
            this.f = new bj(null, null, null, null, null, null, 67108863);
            this.u = null;
        }
        this.b.b(a(this.f));
        r4 r4VarG = g();
        if (r4VarG != null) {
            r4VarG.v = this.y;
            this.z = true;
            this.c.post(this.t);
        }
        try {
            Class<?> cls = Class.forName("android.car.Car");
            Object objInvoke = cls.getMethod("createCar", Context.class, ServiceConnection.class).invoke(null, this.a, new mj(this, i, cls));
            if (objInvoke == null) {
                throw new IllegalStateException(si.a("vehicle_service_unavailable", new Object[0]).toString());
            }
            this.k = objInvoke;
            cls.getMethod("connect", null).invoke(this.k, null);
            this.c.postDelayed(new t0(this, i, 10), 8000L);
        } catch (Exception unused) {
            if (g() == null) {
                this.b.b(new bj(null, null, null, null, si.a("connect_the_vehicle_first", new Object[0]), null, 67107839));
            }
        }
    }

    public final void q() {
        if (this.n) {
            this.v = SystemClock.elapsedRealtime();
        }
        this.n = false;
        this.o++;
        this.p++;
        this.q = false;
        this.c.removeCallbacksAndMessages(null);
        this.l = null;
        if (this.z) {
            r4 r4VarG = g();
            if (r4VarG != null && r4VarG.v == this.y) {
                r4VarG.v = null;
            }
            this.z = false;
        }
        Object obj = this.k;
        if (obj != null) {
            try {
                obj.getClass().getMethod("disconnect", null).invoke(obj, null);
            } catch (Throwable unused) {
            }
        }
        this.k = null;
    }

    public final void r(j6 j6Var) {
        if (SystemClock.elapsedRealtime() < this.A) {
            j6Var.b(si.a("cruise_wait", new Object[0]));
            return;
        }
        this.A = SystemClock.elapsedRealtime() + 6000;
        me meVar = new me(0);
        b(new o0(this, j6Var, meVar, 8), new a(15, meVar));
    }

    public final void s(int i, j6 j6Var) {
        if (i == 268697856 || i == 269747456) {
            b(j6Var, new zd(i, 4));
            return;
        }
        String strA = (String) o2.a().get(Integer.valueOf(i));
        if (strA == null) {
            strA = si.a("function", new Object[0]);
        }
        h(i, strA, j6Var, new zd(i, 2));
    }
}
