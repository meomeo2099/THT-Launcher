package defpackage;

import android.os.Parcel;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class w implements j6 {
    public final /* synthetic */ int a;

    public /* synthetic */ w(int i) {
        this.a = i;
    }

    public static /* synthetic */ void a(String str) {
        throw new IllegalArgumentException(str);
    }

    @Override // defpackage.j6
    public Object b(Object obj) throws UnsupportedEncodingException {
        Double dO;
        Double dO2;
        Map map;
        Object next;
        String str;
        List listJ;
        Object ffVar;
        double dDoubleValue = 0.0d;
        LocalDate localDate = null;
        int i = 0;
        switch (this.a) {
            case 0:
                e2 e2Var = (e2) obj;
                e2Var.getClass();
                return e2Var.a + "\t" + pi.s(e2Var.b, 0, 120);
            case 1:
                Byte b = (Byte) obj;
                b.byteValue();
                return String.format("%02x", Arrays.copyOf(new Object[]{b}, 1));
            case 2:
                int iIntValue = ((Integer) obj).intValue();
                List list = z2.a;
                return z2.d(iIntValue / 2.0f);
            case 3:
                Byte b2 = (Byte) obj;
                b2.byteValue();
                return String.format("%02x", Arrays.copyOf(new Object[]{b2}, 1));
            case 4:
                ((Integer) obj).getClass();
                int i2 = q7.v;
                return ti.a;
            case 5:
                ((r7) obj).getClass();
                return ti.a;
            case 6:
                u7 u7Var = (u7) obj;
                u7Var.getClass();
                return i3.y0(j3.i0(Float.valueOf(u7Var.a), Float.valueOf(u7Var.b), Float.valueOf(u7Var.c), Float.valueOf(u7Var.d)), ",", new w(7), 30) + "~" + URLEncoder.encode(u7Var.e, "UTF-8") + "~" + u7Var.f.a;
            case 7:
                Float f = (Float) obj;
                f.floatValue();
                return String.format(Locale.ROOT, "%.4f", Arrays.copyOf(new Object[]{f}, 1));
            case 8:
                ((Integer) obj).getClass();
                int i3 = MainActivity.E0;
                return ti.a;
            case 9:
            default:
                return Boolean.valueOf(!k8.e((String) obj, "car_root"));
            case 10:
                ((Parcel) obj).getClass();
                return ti.a;
            case 11:
                Parcel parcel = (Parcel) obj;
                parcel.getClass();
                parcel.writeInt(3);
                return ti.a;
            case 12:
                return ((Integer) obj).intValue() + ".0";
            case 13:
                return ((Integer) obj).intValue() + ".0";
            case 14:
                return ((Integer) obj).intValue() + ".0";
            case 15:
                return ((Integer) obj).intValue() + ".0";
            case 16:
                return ((Integer) obj).intValue() + ".0";
            case 17:
                ((Integer) obj).getClass();
                return ti.a;
            case 18:
                hc hcVar = (hc) obj;
                hcVar.getClass();
                return "Notification: " + hcVar.b + " · " + i3.y0(hcVar.h.keySet(), null, null, 63);
            case 19:
                kd kdVar = (kd) obj;
                kdVar.getClass();
                String strB = kdVar.b();
                return Integer.valueOf(k8.e(strB, "FM") ? 0 : k8.e(strB, "AM") ? 1 : 2);
            case 20:
                kd kdVar2 = (kd) obj;
                kdVar2.getClass();
                String strB2 = kdVar2.b();
                String str2 = kdVar2.a;
                Set set = nb.l;
                String strZ = pi.z(strB2, str2);
                if (strZ != null && (dO = ai.o(strZ)) != null) {
                    dDoubleValue = dO.doubleValue();
                }
                return Double.valueOf(dDoubleValue);
            case 21:
                kd kdVar3 = (kd) obj;
                kdVar3.getClass();
                return kdVar3.b();
            case 22:
                kd kdVar4 = (kd) obj;
                kdVar4.getClass();
                Set set2 = nb.l;
                String strZ2 = pi.z(kdVar4.b(), kdVar4.a);
                return (strZ2 == null || (dO2 = ai.o(strZ2)) == null) ? Double.valueOf(0.0d) : dO2;
            case 23:
                String str3 = (String) obj;
                str3.getClass();
                return Boolean.valueOf(!th.A(str3));
            case 24:
                String str4 = (String) obj;
                str4.getClass();
                md mdVar = md.a;
                LinkedHashMap linkedHashMap = md.e;
                if (linkedHashMap == null) {
                    return null;
                }
                LinkedHashMap linkedHashMap2 = md.f;
                if (linkedHashMap2 == null) {
                    map = linkedHashMap2;
                    map = v4.a;
                }
                map = linkedHashMap2;
                ArrayList arrayListD0 = i3.D0(pi.J(str4), th.D(str4, new char[]{'/'}));
                ArrayList arrayList = new ArrayList(k3.n0(arrayListD0));
                int size = arrayListD0.size();
                int i4 = 0;
                while (i4 < size) {
                    Object obj2 = arrayListD0.get(i4);
                    i4++;
                    String str5 = (String) obj2;
                    Pattern patternCompile = Pattern.compile("\\s*\\(.*?\\)");
                    patternCompile.getClass();
                    str5.getClass();
                    String strReplaceAll = patternCompile.matcher(str5).replaceAll("");
                    strReplaceAll.getClass();
                    arrayList.add(th.J(strReplaceAll).toString());
                }
                ArrayList arrayList2 = new ArrayList();
                int size2 = arrayList.size();
                int i5 = 0;
                while (i5 < size2) {
                    Object obj3 = arrayList.get(i5);
                    i5++;
                    if (((String) obj3).length() > 0) {
                        arrayList2.add(obj3);
                    }
                }
                ArrayList arrayList3 = new ArrayList();
                int size3 = arrayList2.size();
                int i6 = 0;
                while (i6 < size3) {
                    Object obj4 = arrayList2.get(i6);
                    i6++;
                    String str6 = (String) obj4;
                    me meVar = md.b;
                    meVar.getClass();
                    str6.getClass();
                    String strReplaceAll2 = ((Pattern) meVar.b).matcher(str6).replaceAll("");
                    strReplaceAll2.getClass();
                    o3.p0(j3.i0(str6, th.J(strReplaceAll2).toString()), arrayList3);
                }
                ArrayList arrayList4 = new ArrayList();
                int size4 = arrayList3.size();
                while (i < size4) {
                    Object obj5 = arrayList3.get(i);
                    i++;
                    if (((String) obj5).length() > 0) {
                        arrayList4.add(obj5);
                    }
                }
                List listJ0 = i3.J0(i3.K0(arrayList4));
                Iterator it = listJ0.iterator();
                while (it.hasNext()) {
                    String str7 = (String) linkedHashMap.get(md.f((String) it.next()));
                    if (str7 != null) {
                        return str7;
                    }
                }
                Iterator it2 = listJ0.iterator();
                while (it2.hasNext()) {
                    String strB3 = md.b((String) it2.next());
                    if (strB3.length() < 3) {
                        strB3 = null;
                    }
                    if (strB3 != null && (str = (String) map.get(strB3)) != null) {
                        return str;
                    }
                }
                String strB4 = md.b(str4);
                Set setKeySet = map.keySet();
                ArrayList arrayList5 = new ArrayList();
                for (Object obj6 : setKeySet) {
                    String str8 = (String) obj6;
                    if (str8.length() >= 4 && strB4.startsWith(str8)) {
                        arrayList5.add(obj6);
                    }
                }
                Iterator it3 = arrayList5.iterator();
                if (it3.hasNext()) {
                    next = it3.next();
                    if (it3.hasNext()) {
                        int length = ((String) next).length();
                        do {
                            Object next2 = it3.next();
                            int length2 = ((String) next2).length();
                            if (length < length2) {
                                next = next2;
                                length = length2;
                            }
                        } while (it3.hasNext());
                    }
                } else {
                    next = null;
                }
                String str9 = (String) next;
                if (str9 != null) {
                    return (String) map.get(str9);
                }
                return null;
            case 25:
                ((Parcel) obj).getClass();
                return ti.a;
            case 26:
                Byte b3 = (Byte) obj;
                b3.byteValue();
                return String.format("%02X", Arrays.copyOf(new Object[]{b3}, 1));
            case 27:
                String str10 = (String) obj;
                str10.getClass();
                String string = th.J(th.F(str10, '#')).toString();
                if (string.length() == 0) {
                    return null;
                }
                Pattern patternCompile2 = Pattern.compile("\\s+");
                patternCompile2.getClass();
                Matcher matcher = patternCompile2.matcher(string);
                if (matcher.find()) {
                    ArrayList arrayList6 = new ArrayList(10);
                    int iEnd = 0;
                    do {
                        arrayList6.add(string.subSequence(iEnd, matcher.start()).toString());
                        iEnd = matcher.end();
                    } while (matcher.find());
                    arrayList6.add(string.subSequence(iEnd, string.length()).toString());
                    listJ = arrayList6;
                } else {
                    listJ = pi.J(string.toString());
                }
                String str11 = (String) listJ.get(0);
                str11.getClass();
                String upperCase = str11.toUpperCase(Locale.ROOT);
                upperCase.getClass();
                StringBuilder sb = new StringBuilder();
                int length3 = upperCase.length();
                for (int i7 = 0; i7 < length3; i7++) {
                    char cCharAt = upperCase.charAt(i7);
                    if (('0' <= cCharAt && cCharAt < ':') || ('A' <= cCharAt && cCharAt < 'G')) {
                        sb.append(cCharAt);
                    }
                }
                String string2 = sb.toString();
                if (string2.length() != 12) {
                    string2 = null;
                }
                if (string2 == null) {
                    return null;
                }
                String str12 = (String) i3.w0(listJ, 1);
                if (str12 != null) {
                    try {
                        ffVar = LocalDate.parse(str12);
                    } catch (Throwable th) {
                        ffVar = new ff(th);
                    }
                    boolean z = ffVar instanceof ff;
                    Object obj7 = ffVar;
                    if (z) {
                        obj7 = null;
                    }
                    LocalDate localDate2 = (LocalDate) obj7;
                    if (localDate2 == null) {
                        return null;
                    }
                    localDate = localDate2;
                    break;
                }
                return new ef(string2, localDate);
            case 28:
                return Boolean.valueOf(obj == null);
        }
    }
}
