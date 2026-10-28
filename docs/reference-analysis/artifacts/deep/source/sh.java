package defpackage;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class sh {
    public static Object a;
    public static Method b;
    public static boolean c;
    public static boolean d;
    public static boolean e;
    public static float f;

    public static Float a(byte[] bArr) {
        Integer numI = i(bArr);
        if (numI == null) {
            return null;
        }
        float fIntValue = numI.intValue();
        if (fIntValue > 1000.0f) {
            fIntValue /= 100.0f;
        } else if (fIntValue > 100.0f) {
            fIntValue /= 10.0f;
        }
        Float fValueOf = Float.valueOf(fIntValue);
        if (6.0f > fIntValue || fIntValue > 17.0f) {
            return null;
        }
        return fValueOf;
    }

    public static String b(int i) {
        int i2 = i / 3600;
        int i3 = (i % 3600) / 60;
        if (i2 > 0) {
            List list = si.a;
            return si.a("0_h_1_min", Integer.valueOf(i2), Integer.valueOf(i3));
        }
        List list2 = si.a;
        return si.a("0_min", Integer.valueOf(i3));
    }

    public static String c(float f2) {
        if (f2 < 100.0f) {
            return String.format(si.a("1f_km", new Object[0]), Arrays.copyOf(new Object[]{Float.valueOf(Math.max(0.0f, f2))}, 1));
        }
        List list = si.a;
        return si.a("0_km", Integer.valueOf((int) f2));
    }

    public static Float d(byte[] bArr) {
        Integer numI;
        int iIntValue;
        if (bArr == null || (numI = i(bArr)) == null || (iIntValue = numI.intValue()) == 0 || iIntValue == 255 || iIntValue == 65535) {
            return null;
        }
        if (bArr.length == 1) {
            if (iIntValue <= 120) {
                return Float.valueOf(Math.min(iIntValue, 100));
            }
            return null;
        }
        if (iIntValue <= 110) {
            return Float.valueOf(Math.min(iIntValue, 100));
        }
        if (iIntValue <= 1100) {
            return Float.valueOf(Math.min(iIntValue / 10.0f, 100.0f));
        }
        return null;
    }

    public static LinkedHashMap e() {
        LinkedHashMap linkedHashMapH0 = hb.h0(new yc("cruise", si.a("cruise_mode", new Object[0])), new yc("climate", si.a("climate", new Object[0])), new yc("battery", si.a("battery", new Object[0])), new yc("fuel", si.a("fuel", new Object[0])), new yc("ambient", si.a("ambient_lighting", new Object[0])), new yc("roof", si.a("sunroof", new Object[0])), new yc("roof_tilt", si.a("sunroof_tilt", new Object[0])), new yc("curtain", si.a("sun_curtain", new Object[0])), new yc("nozzle_heat", si.a("nozzle_heating", new Object[0])), new yc("wiper_heat", si.a("windshield_heating_wiper_zone", new Object[0])), new yc("start_stop", si.a("startstop", new Object[0])), new yc("airflow", si.a("airflow_direction", new Object[0])), new yc("trunk", si.a("tailgate", new Object[0])), new yc("mirrors", si.a("mirrors", new Object[0])), new yc("ext_light", si.a("exterior_lights", new Object[0])), new yc("wipers", si.a("wiper_service_position", new Object[0])));
        for (n4 n4Var : pi.A()) {
            linkedHashMapH0.put(n4Var.a, n4Var.c);
        }
        Set set = rf.d;
        for (Map.Entry entry : pi.B().entrySet()) {
            linkedHashMapH0.put((String) entry.getKey(), ((rf) entry.getValue()).c);
        }
        return linkedHashMapH0;
    }

    public static Method f(Class cls, String str, Class... clsArr) {
        Object ffVar;
        Object ffVar2;
        try {
            ffVar = cls.getMethod(str, (Class[]) Arrays.copyOf(clsArr, clsArr.length));
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        if (ffVar instanceof ff) {
            ffVar = null;
        }
        Method method = (Method) ffVar;
        if (method != null) {
            return method;
        }
        try {
            Object objInvoke = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, new Class[0].getClass()).invoke(cls, str, clsArr);
            objInvoke.getClass();
            ffVar2 = (Method) objInvoke;
        } catch (Throwable th2) {
            ffVar2 = new ff(th2);
        }
        return (Method) (ffVar2 instanceof ff ? null : ffVar2);
    }

    public static boolean g(String str) {
        str.getClass();
        return j3.i0("cruise", "roof", "roof_tilt", "curtain", "nozzle_heat", "wiper_heat", "start_stop", "trunk", "mirrors", "ext_light", "wipers").contains(str) || pi.j(str) != null;
    }

    public static uj h(String str) {
        str.getClass();
        String strF = th.F(str, '_');
        boolean zA = th.A(strF);
        uj ujVar = uj.i;
        if (zA) {
            return ujVar;
        }
        if (th.v(strF, "thunder", false)) {
            return uj.h;
        }
        if (th.v(strF, "sleet", false)) {
            return uj.f;
        }
        if (th.v(strF, "snow", false)) {
            return uj.e;
        }
        if (th.v(strF, "rain", false)) {
            return uj.d;
        }
        if (strF.equals("fog")) {
            return uj.g;
        }
        boolean zEquals = strF.equals("clearsky");
        uj ujVar2 = uj.b;
        if (zEquals || strF.equals("fair")) {
            return strF.equals("fair") ? ujVar2 : uj.a;
        }
        if (strF.equals("partlycloudy")) {
            return ujVar2;
        }
        return strF.equals("cloudy") ? uj.c : ujVar;
    }

    public static Integer i(byte[] bArr) {
        if (bArr == null || bArr.length == 0 || bArr.length > 4) {
            return null;
        }
        int length = bArr.length - 1;
        int i = 0;
        if (length >= 0) {
            while (true) {
                int i2 = length - 1;
                i = (i << 8) | (bArr[length] & 255);
                if (i2 < 0) {
                    break;
                }
                length = i2;
            }
        }
        return Integer.valueOf(i);
    }

    public static boolean j(String str) {
        str.getClass();
        return str.endsWith("_night") || str.endsWith("_polartwilight");
    }

    public static Float k(Float f2) {
        if (f2 == null) {
            return null;
        }
        float fFloatValue = f2.floatValue();
        if (Math.abs(fFloatValue) > Float.MAX_VALUE || fFloatValue <= 0.0f || fFloatValue > 2000.0f) {
            return null;
        }
        return f2;
    }

    public static final String l(Reader reader) throws IOException {
        StringWriter stringWriter = new StringWriter();
        char[] cArr = new char[8192];
        int i = reader.read(cArr);
        while (i >= 0) {
            stringWriter.write(cArr, 0, i);
            i = reader.read(cArr);
        }
        String string = stringWriter.toString();
        string.getClass();
        return string;
    }

    public static String m(String str, String str2) {
        str.getClass();
        if (str.equals("wiper_heat")) {
            List list = z2.a;
            return z2.b(str2);
        }
        String str3 = (String) e().get(str);
        return str3 == null ? "" : str3;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:78:0x00f0 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:81:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:83:0x00ff  */
    public static boolean n(String str, bj bjVar) {
        str.getClass();
        bjVar.getClass();
        v2 v2Var = bjVar.i;
        switch (str) {
            case "start_stop":
                Boolean bool = bjVar.q;
                if (bool != null) {
                    return bool.booleanValue();
                }
            case "cruise":
                Integer num = bjVar.r;
                if (num != null && num.intValue() == 537069059) {
                    return true;
                }
            case "roof_tilt":
                Boolean bool2 = bjVar.u;
                if (bool2 != null) {
                    return bool2.booleanValue();
                }
            case "wipers":
                if (str.equals("start_stop")) {
                    Boolean bool3 = bjVar.q;
                    if (bool3 != null) {
                        return bool3.booleanValue();
                    }
                } else {
                    n4 n4VarJ = pi.j(str);
                    if (n4VarJ != null) {
                        return n4VarJ.a(bjVar.o);
                    }
                }
                return false;
            case "ext_light":
                Integer num2 = bjVar.z;
                if ((num2 != null ? num2.intValue() : 0) != 0) {
                    return true;
                }
            case "roof":
                Boolean bool4 = bjVar.n;
                if (bool4 != null) {
                    return bool4.booleanValue();
                }
            case "trunk":
                Boolean bool5 = bjVar.x;
                if (bool5 != null) {
                    return bool5.booleanValue();
                }
            case "nozzle_heat":
                Integer num3 = (Integer) v2Var.f.get(269747200);
                if (num3 != null && num3.intValue() == 1) {
                    return true;
                }
            case "mirrors":
                Boolean bool6 = bjVar.y;
                if (bool6 != null) {
                    return bool6.booleanValue();
                }
            case "curtain":
                Boolean bool7 = bjVar.w;
                if (bool7 != null) {
                    return bool7.booleanValue();
                }
            case "wiper_heat":
                Integer num4 = (Integer) v2Var.f.get(269747456);
                if (num4 != null && num4.intValue() == 1) {
                    return true;
                }
            default:
                n4 n4VarJ2 = pi.j(str);
                if (n4VarJ2 != null) {
                    return n4VarJ2.a(bjVar.o);
                }
        }
    }
}
