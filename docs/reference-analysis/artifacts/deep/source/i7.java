package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.net.Uri;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class i7 {
    public static final me a = new me("[a-z0-9-]{1,48}\\.png");

    public static String a(Context context) {
        context.getClass();
        String string = context.getSharedPreferences("panel", 0).getString("icon_set", "");
        if (string == null) {
            string = "";
        }
        if (string.length() <= 0 || !b(context, string).isDirectory()) {
            string = null;
        }
        return string == null ? "" : string;
    }

    public static File b(Context context, String str) {
        context.getClass();
        return new File(new File(context.getFilesDir(), "icon_sets"), str);
    }

    public static void c(Context context, Uri uri) throws IOException {
        int i;
        String strM;
        String str;
        uri.getClass();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i2 = 1;
        Paint paint = new Paint(1);
        List listF0 = i3.F0(i9.a.keySet());
        ArrayList arrayList = new ArrayList();
        Iterator it = listF0.iterator();
        while (true) {
            i = 2;
            if (!it.hasNext()) {
                break;
            }
            String str2 = (String) it.next();
            l0 l0Var = ba.h;
            str2.getClass();
            String strReplace = (String) ba.t.get(str2);
            if (strReplace == null) {
                strReplace = str2.replace('_', '-');
                strReplace.getClass();
            }
            yc ycVar = a.a(strReplace.concat(".png")) ? new yc(strReplace.concat(".png"), new y1(str2, paint, i)) : null;
            if (ycVar != null) {
                arrayList.add(ycVar);
            }
        }
        Set set = aj.i;
        Set setKeySet = sh.e().keySet();
        setKeySet.getClass();
        Set set2 = rf.d;
        Set setKeySet2 = pi.B().keySet();
        setKeySet2.getClass();
        LinkedHashSet linkedHashSetH0 = zf.h0(zf.h0(setKeySet, setKeySet2), ba.r);
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : linkedHashSetH0) {
            if (!k8.e((String) obj, "airflow")) {
                arrayList2.add(obj);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        int size = arrayList2.size();
        int i3 = 0;
        int i4 = 0;
        while (i4 < size) {
            Object obj2 = arrayList2.get(i4);
            i4++;
            String str3 = (String) obj2;
            l0 l0Var2 = ba.h;
            str3.getClass();
            String strA = l0.A(str3);
            yc ycVar2 = (strA != null && a.a(strA.concat(".png"))) ? new yc(strA.concat(".png"), new e7(context, str3, i3)) : null;
            if (ycVar2 != null) {
                arrayList3.add(ycVar2);
            }
        }
        List listI0 = j3.i0("air-both", "air-lower", "air-upper", "front-defrost");
        ArrayList arrayList4 = new ArrayList(k3.n0(listI0));
        Iterator it2 = listI0.iterator();
        while (it2.hasNext()) {
            arrayList4.add(new yc(((String) it2.next()) + ".png", h7.a));
        }
        ArrayList arrayListD0 = i3.D0(arrayList3, arrayList4);
        String string = context.getSharedPreferences("panel", 0).getString("vehicle", null);
        int size2 = arrayList2.size();
        int i5 = 0;
        while (i5 < size2) {
            Object obj3 = arrayList2.get(i5);
            i5++;
            String str4 = (String) obj3;
            l0 l0Var3 = ba.h;
            str4.getClass();
            String strA2 = l0.A(str4);
            if (strA2 != null) {
                if (str4.equals("steering")) {
                    strM = si.a("steering_wheel_heating", new Object[0]);
                } else {
                    Set set3 = aj.i;
                    if (sh.e().containsKey(str4)) {
                        strM = sh.m(str4, string);
                    } else {
                        Set set4 = rf.d;
                        rf rfVar = (rf) pi.B().get(str4);
                        if (rfVar == null || (str = rfVar.c) == null) {
                            n4 n4VarJ = pi.j(str4);
                            strM = n4VarJ != null ? n4VarJ.c : (String) o2.b().get(str4);
                        } else {
                            strM = str;
                        }
                    }
                }
                if (strM != null) {
                    linkedHashMap.putIfAbsent(strA2.concat(".png"), strM);
                }
            }
        }
        linkedHashMap.putIfAbsent("front-defrost.png", si.a("windshield_defogging", new Object[0]));
        Collection collectionValues = ba.u.values();
        collectionValues.getClass();
        List listJ0 = i3.J0(i3.K0(collectionValues));
        ArrayList arrayList5 = new ArrayList(k3.n0(listJ0));
        Iterator it3 = listJ0.iterator();
        while (true) {
            int i6 = 5;
            if (!it3.hasNext()) {
                break;
            }
            arrayList5.add(new yc(((String) it3.next()) + ".png", new j(i6)));
        }
        List<String> list = ba.o;
        ArrayList arrayList6 = new ArrayList(k3.n0(list));
        for (String str5 : list) {
            arrayList6.add(new yc(str5 + ".png", new e7(context, str5)));
        }
        v9 v9Var = new v9(context);
        List<yc> list2 = ba.q;
        ArrayList arrayList7 = new ArrayList(k3.n0(list2));
        for (yc ycVar3 : list2) {
            uj ujVar = (uj) ycVar3.a;
            boolean zBooleanValue = ((Boolean) ycVar3.b).booleanValue();
            l0 l0Var4 = ba.h;
            arrayList7.add(new yc(l0.B(ujVar, zBooleanValue).concat(".png"), new f7(v9Var, ujVar, zBooleanValue)));
        }
        List<String> list3 = ba.s;
        ArrayList arrayList8 = new ArrayList(k3.n0(list3));
        for (String str6 : list3) {
            arrayList8.add(new yc(str6 + ".png", new e7(context, str6, i)));
        }
        ArrayList arrayListE0 = i3.E0(arrayList8, new yc("update.png", new z6(context, i2)));
        ArrayList arrayListD1 = i3.D0(i3.D0(i3.D0(i3.D0(i3.D0(arrayList, arrayListD0), arrayList5), arrayList6), arrayList7), arrayListE0);
        ArrayList arrayList9 = new ArrayList(k3.n0(arrayListD1));
        int size3 = arrayListD1.size();
        int i7 = 0;
        while (i7 < size3) {
            Object obj4 = arrayListD1.get(i7);
            i7++;
            arrayList9.add((String) ((yc) obj4).a);
        }
        Set setL0 = i3.L0(arrayList9);
        String[] list4 = context.getAssets().list("launcher_icons");
        if (list4 == null) {
            list4 = new String[0];
        }
        ArrayList arrayList10 = new ArrayList();
        for (String str7 : list4) {
            me meVar = a;
            str7.getClass();
            if (meVar.a(str7) && !setL0.contains(str7)) {
                arrayList10.add(str7);
            }
        }
        List listF1 = i3.F0(arrayList10);
        ArrayList arrayList11 = new ArrayList(k3.n0(listF1));
        Iterator it4 = listF1.iterator();
        while (it4.hasNext()) {
            arrayList11.add(new yc((String) it4.next(), new j(5)));
        }
        LinkedHashMap linkedHashMapH0 = hb.h0(new yc("Интерфейс, кнопки и нижняя панель", arrayList), new yc("Машина: климат, сиденья, люк, режимы, подогревы", arrayListD0), new yc("Музыка", arrayList5), new yc("Виджеты со своим значком (даны в цвете как образец)", arrayList6), new yc("Погода (днём и ночью)", arrayList7), new yc("Классические карточки и окно обновления", arrayListE0), new yc("Прочее", arrayList11));
        List<yc> list5 = ba.q;
        ArrayList arrayList12 = new ArrayList(k3.n0(list5));
        for (yc ycVar4 : list5) {
            uj ujVar2 = (uj) ycVar4.a;
            boolean zBooleanValue2 = ((Boolean) ycVar4.b).booleanValue();
            l0 l0Var5 = ba.h;
            arrayList12.add(l0.B(ujVar2, zBooleanValue2).concat(".png"));
        }
        Set setL1 = i3.L0(arrayList12);
        OutputStream outputStreamOpenOutputStream = context.getContentResolver().openOutputStream(uri, "wt");
        if (outputStreamOpenOutputStream == null) {
            throw new IllegalStateException(si.a("icon_set_file_unavailable", new Object[0]).toString());
        }
        String[] list6 = context.getAssets().list("launcher_icons");
        if (list6 == null) {
            list6 = new String[0];
        }
        Set setY0 = x0.Y0(list6);
        l0 l0Var6 = ba.h;
        ba.m = true;
        try {
            try {
                ZipOutputStream zipOutputStream = new ZipOutputStream(outputStreamOpenOutputStream);
                try {
                    LinkedHashSet linkedHashSet = new LinkedHashSet();
                    Collection collectionValues2 = linkedHashMapH0.values();
                    collectionValues2.getClass();
                    ArrayList arrayList13 = new ArrayList();
                    Iterator it5 = collectionValues2.iterator();
                    while (it5.hasNext()) {
                        o3.p0((Iterable) it5.next(), arrayList13);
                    }
                    int size4 = arrayList13.size();
                    int i8 = 0;
                    while (i8 < size4) {
                        Object obj5 = arrayList13.get(i8);
                        i8++;
                        yc ycVar5 = (yc) obj5;
                        String str8 = (String) ycVar5.a;
                        y5 y5Var = (y5) ycVar5.b;
                        if (linkedHashSet.add(str8)) {
                            zipOutputStream.putNextEntry(new ZipEntry(str8));
                            if (!setY0.contains(str8) || k8.e(str8, "ac.png") || setL1.contains(str8)) {
                                Bitmap bitmap = (Bitmap) y5Var.a();
                                if (bitmap != null) {
                                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, zipOutputStream);
                                    bitmap.recycle();
                                }
                            } else {
                                InputStream inputStreamOpen = context.getAssets().open("launcher_icons/" + str8);
                                try {
                                    inputStreamOpen.getClass();
                                    pi.u(inputStreamOpen, zipOutputStream);
                                    inputStreamOpen.close();
                                } catch (Throwable th) {
                                    try {
                                        throw th;
                                    } catch (Throwable th2) {
                                        k8.p(inputStreamOpen, th);
                                        throw th2;
                                    }
                                }
                            }
                            zipOutputStream.closeEntry();
                        }
                    }
                    zipOutputStream.putNextEntry(new ZipEntry("manifest.json"));
                    String string2 = new JSONObject().put("name", "Мой набор").put("author", "").put("mode", "mono").toString(2);
                    string2.getClass();
                    byte[] bytes = string2.getBytes(q2.a);
                    bytes.getClass();
                    zipOutputStream.write(bytes);
                    zipOutputStream.closeEntry();
                    zipOutputStream.putNextEntry(new ZipEntry("README.txt"));
                    LinkedHashMap linkedHashMap2 = new LinkedHashMap(hb.i0(linkedHashMapH0.size()));
                    for (Object obj6 : linkedHashMapH0.entrySet()) {
                        Object key = ((Map.Entry) obj6).getKey();
                        List list7 = (List) ((Map.Entry) obj6).getValue();
                        ArrayList arrayList14 = new ArrayList(k3.n0(list7));
                        Iterator it6 = list7.iterator();
                        while (it6.hasNext()) {
                            arrayList14.add((String) ((yc) it6.next()).a);
                        }
                        linkedHashMap2.put(key, i3.J0(i3.K0(arrayList14)));
                    }
                    byte[] bytes2 = i(linkedHashMap2, linkedHashMap).getBytes(q2.a);
                    bytes2.getClass();
                    zipOutputStream.write(bytes2);
                    zipOutputStream.closeEntry();
                    zipOutputStream.close();
                    outputStreamOpenOutputStream.close();
                    l0 l0Var7 = ba.h;
                    ba.m = false;
                } catch (Throwable th3) {
                    try {
                        throw th3;
                    } catch (Throwable th4) {
                        k8.p(zipOutputStream, th3);
                        throw th4;
                    }
                }
            } catch (Throwable th5) {
                l0 l0Var8 = ba.h;
                ba.m = false;
                throw th5;
            }
        } catch (Throwable th6) {
            try {
                throw th6;
            } catch (Throwable th7) {
                k8.p(outputStreamOpenOutputStream, th6);
                throw th7;
            }
        }
    }

    public static d7 d(Context context, Uri uri) {
        ZipInputStream zipInputStream;
        Throwable th;
        Object ffVar;
        JSONObject jSONObject;
        int i;
        uri.getClass();
        File file = new File(context.getCacheDir(), "icon-import-" + System.nanoTime());
        file.mkdirs();
        try {
            InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(uri);
            int i2 = 0;
            if (inputStreamOpenInputStream == null) {
                throw new IllegalStateException(si.a("icon_set_file_unavailable", new Object[0]).toString());
            }
            try {
                ZipInputStream zipInputStream2 = new ZipInputStream(inputStreamOpenInputStream);
                int i3 = 0;
                long j = 0;
                while (true) {
                    try {
                        ZipEntry nextEntry = zipInputStream2.getNextEntry();
                        me meVar = a;
                        if (nextEntry == null) {
                            zipInputStream2.close();
                            inputStreamOpenInputStream.close();
                            File[] fileArrListFiles = file.listFiles();
                            if (fileArrListFiles == null) {
                                fileArrListFiles = new File[i2];
                            }
                            ArrayList arrayList = new ArrayList();
                            int length = fileArrListFiles.length;
                            for (int i4 = i2; i4 < length; i4++) {
                                File file2 = fileArrListFiles[i4];
                                String name = file2.getName();
                                name.getClass();
                                if (meVar.a(name)) {
                                    arrayList.add(file2);
                                }
                            }
                            if (arrayList.isEmpty()) {
                                throw new IllegalArgumentException(si.a("icon_set_no_icons", new Object[i2]).toString());
                            }
                            int size = arrayList.size();
                            int i5 = i2;
                            while (i5 < size) {
                                Object obj = arrayList.get(i5);
                                i5++;
                                File file3 = (File) obj;
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inJustDecodeBounds = true;
                                BitmapFactory.decodeFile(file3.getAbsolutePath(), options);
                                int i6 = options.outWidth;
                                if (1 > i6 || i6 >= 1025 || 1 > (i = options.outHeight) || i >= 1025) {
                                    List list = si.a;
                                    throw new IllegalArgumentException(si.a("icon_set_bad_image_0", file3.getName()).toString());
                                }
                            }
                            File file4 = new File(file, "manifest.json");
                            if (!file4.isFile()) {
                                file4 = null;
                            }
                            if (file4 != null) {
                                try {
                                    ffVar = new JSONObject(m5.k0(file4));
                                } catch (Throwable th2) {
                                    ffVar = new ff(th2);
                                }
                                if (ffVar instanceof ff) {
                                    ffVar = null;
                                }
                                jSONObject = (JSONObject) ffVar;
                            } else {
                                jSONObject = null;
                            }
                            String strOptString = jSONObject != null ? jSONObject.optString("name") : null;
                            if (strOptString == null) {
                                strOptString = "";
                            }
                            if (th.A(strOptString)) {
                                strOptString = si.a("icon_set_unnamed", new Object[i2]);
                            }
                            String strH = h(th.I(60, strOptString));
                            if (strH.length() == 0) {
                                strH = "set";
                            }
                            File fileB = b(context, ((Object) strH) + "-" + Long.toString(System.currentTimeMillis() % 1000000, 36));
                            new File(context.getFilesDir(), "icon_sets").mkdirs();
                            if (!file.renameTo(fileB) && !m5.g0(file, fileB)) {
                                throw new IllegalStateException(si.a("icon_set_file_unavailable", new Object[i2]).toString());
                            }
                            d7 d7VarF = f(fileB);
                            if (d7VarF == null) {
                                throw new IllegalStateException(si.a("icon_set_no_icons", new Object[i2]).toString());
                            }
                            m5.i0(file);
                            return d7VarF;
                        }
                        if (!nextEntry.isDirectory()) {
                            String name2 = nextEntry.getName();
                            name2.getClass();
                            String lowerCase = th.E(name2, '/').toLowerCase(Locale.ROOT);
                            lowerCase.getClass();
                            if (!lowerCase.equals("manifest.json")) {
                                try {
                                    if (!meVar.a(lowerCase)) {
                                        continue;
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    zipInputStream = zipInputStream2;
                                }
                            }
                            i3++;
                            if (i3 > 300) {
                                throw new IllegalArgumentException(si.a("icon_set_too_large", new Object[i2]).toString());
                            }
                            FileOutputStream fileOutputStream = new FileOutputStream(new File(file, lowerCase));
                            try {
                                byte[] bArr = new byte[8192];
                                long j2 = 0;
                                while (true) {
                                    int i7 = zipInputStream2.read(bArr);
                                    if (i7 >= 0) {
                                        zipInputStream = zipInputStream2;
                                        long j3 = i7;
                                        j2 += j3;
                                        j += j3;
                                        if (j2 > 2097152 || j > 41943040) {
                                            throw new IllegalArgumentException(si.a("icon_set_too_large", new Object[0]).toString());
                                        }
                                        try {
                                            fileOutputStream.write(bArr, 0, i7);
                                            zipInputStream2 = zipInputStream;
                                        } catch (Throwable th4) {
                                            th = th4;
                                        }
                                        th = th4;
                                        Throwable th5 = th;
                                        try {
                                            throw th5;
                                        } catch (Throwable th6) {
                                            k8.p(fileOutputStream, th5);
                                            throw th6;
                                        }
                                    }
                                    zipInputStream = zipInputStream2;
                                    try {
                                        fileOutputStream.close();
                                        zipInputStream2 = zipInputStream;
                                        i2 = 0;
                                        break;
                                    } catch (Throwable th7) {
                                        th = th7;
                                    }
                                }
                            } catch (Throwable th8) {
                                th = th8;
                                zipInputStream = zipInputStream2;
                            }
                        }
                    } catch (Throwable th9) {
                        th = th9;
                        zipInputStream = zipInputStream2;
                    }
                    th = th;
                    try {
                        throw th;
                    } catch (Throwable th10) {
                        k8.p(zipInputStream, th);
                        throw th10;
                    }
                }
            } catch (Throwable th11) {
                try {
                    throw th11;
                } catch (Throwable th12) {
                    k8.p(inputStreamOpenInputStream, th11);
                    throw th12;
                }
            }
        } catch (Throwable th13) {
            m5.i0(file);
            throw th13;
        }
    }

    public static List e(Context context) {
        context.getClass();
        File[] fileArrListFiles = new File(context.getFilesDir(), "icon_sets").listFiles();
        int i = 0;
        if (fileArrListFiles == null) {
            fileArrListFiles = new File[0];
        }
        ArrayList arrayList = new ArrayList();
        for (File file : fileArrListFiles) {
            if (file.isDirectory()) {
                arrayList.add(file);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            File file2 = (File) obj;
            file2.getClass();
            d7 d7VarF = f(file2);
            if (d7VarF != null) {
                arrayList2.add(d7VarF);
            }
        }
        return i3.G0(arrayList2, new h0(3));
    }

    public static d7 f(File file) {
        Object ffVar;
        try {
            File file2 = new File(file, "manifest.json");
            if (!file2.isFile()) {
                file2 = null;
            }
            JSONObject jSONObject = file2 != null ? new JSONObject(m5.k0(file2)) : null;
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null) {
                fileArrListFiles = new File[0];
            }
            int i = 0;
            for (File file3 : fileArrListFiles) {
                me meVar = a;
                String name = file3.getName();
                name.getClass();
                if (meVar.a(name)) {
                    i++;
                }
            }
            String name2 = file.getName();
            name2.getClass();
            String strOptString = jSONObject != null ? jSONObject.optString("name") : null;
            if (strOptString == null) {
                strOptString = "";
            }
            if (th.A(strOptString)) {
                strOptString = file.getName();
            }
            String str = strOptString;
            str.getClass();
            String strOptString2 = jSONObject != null ? jSONObject.optString("author") : null;
            ffVar = new d7(name2, str, strOptString2 == null ? "" : strOptString2, k8.e(jSONObject != null ? jSONObject.optString("mode") : null, "color"), i);
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        return (d7) (ffVar instanceof ff ? null : ffVar);
    }

    public static void g(Context context, String str) {
        context.getClass();
        context.getSharedPreferences("panel", 0).edit().putString("icon_set", str).apply();
        l0 l0Var = ba.h;
        ba baVar = ba.k;
        if (baVar != null) {
            baVar.c(context);
        }
    }

    public static String h(String str) {
        Map mapJ0 = hb.j0(new yc((char) 1072, "a"), new yc((char) 1073, "b"), new yc((char) 1074, "v"), new yc((char) 1075, "g"), new yc((char) 1076, "d"), new yc((char) 1077, "e"), new yc((char) 1105, "e"), new yc((char) 1078, "zh"), new yc((char) 1079, "z"), new yc((char) 1080, "i"), new yc((char) 1081, "y"), new yc((char) 1082, "k"), new yc((char) 1083, "l"), new yc((char) 1084, "m"), new yc((char) 1085, "n"), new yc((char) 1086, "o"), new yc((char) 1087, "p"), new yc((char) 1088, "r"), new yc((char) 1089, "s"), new yc((char) 1090, "t"), new yc((char) 1091, "u"), new yc((char) 1092, "f"), new yc((char) 1093, "h"), new yc((char) 1094, "c"), new yc((char) 1095, "ch"), new yc((char) 1096, "sh"), new yc((char) 1097, "sch"), new yc((char) 1099, "y"), new yc((char) 1101, "e"), new yc((char) 1102, "yu"), new yc((char) 1103, "ya"));
        String lowerCase = str.toLowerCase(Locale.ROOT);
        lowerCase.getClass();
        ArrayList arrayList = new ArrayList(lowerCase.length());
        int i = 0;
        while (true) {
            String strValueOf = "-";
            if (i >= lowerCase.length()) {
                break;
            }
            char cCharAt = lowerCase.charAt(i);
            String str2 = (String) mapJ0.get(Character.valueOf(cCharAt));
            if (str2 != null) {
                strValueOf = str2;
            } else if (('a' <= cCharAt && cCharAt < '{') || ('0' <= cCharAt && cCharAt < ':')) {
                strValueOf = String.valueOf(cCharAt);
            }
            arrayList.add(strValueOf);
            i++;
        }
        String strY0 = i3.y0(arrayList, "", null, 62);
        Pattern patternCompile = Pattern.compile("-+");
        patternCompile.getClass();
        String strReplaceAll = patternCompile.matcher(strY0).replaceAll("-");
        strReplaceAll.getClass();
        char[] cArr = {'-'};
        int length = strReplaceAll.length() - 1;
        int i2 = 0;
        boolean z = false;
        while (i2 <= length) {
            char cCharAt2 = strReplaceAll.charAt(!z ? i2 : length);
            int i3 = 0;
            while (true) {
                if (i3 >= 1) {
                    i3 = -1;
                    break;
                }
                if (cCharAt2 == cArr[i3]) {
                    break;
                }
                i3++;
            }
            boolean z2 = i3 >= 0;
            if (z) {
                if (!z2) {
                    break;
                }
                length--;
            } else if (z2) {
                i2++;
            } else {
                z = true;
            }
        }
        return th.I(24, strReplaceAll.subSequence(i2, length + 1).toString());
    }

    public static String i(LinkedHashMap linkedHashMap, LinkedHashMap linkedHashMap2) {
        StringBuilder sb = new StringBuilder("Набор значков ReferencePanel\n\nЗдесь ВСЕ значки лаунчера: кнопки, нижняя панель, климат и машина, музыка и виджеты.\nПерерисуйте нужные PNG, сохранив имена файлов, и запакуйте всё в .zip.\nКаких файлов нет в наборе — останутся стандартными.\n\nmanifest.json:\n  name   — название набора в настройках\n  author — автор\n  mode   — \"mono\": силуэт на прозрачном фоне, лаунчер сам перекрашивает его под тему\n           и состояние (вкл/выкл); \"color\": картинки показываются как нарисованы.\n\nСвой цвет для отдельных значков в наборе \"mono\": добавьте к имени файла -color,\nнапример camera360-color.png или screensaver-color.png. Такой значок показывается\nкак нарисован, остальные по-прежнему перекрашиваются под тему и состояние.\nДля ночной темы можно положить ещё и вариант -color-night (camera360-color-night.png);\nбез него и днём, и ночью показывается -color.\n\nСвоя картинка для каждого состояния маленьких значков Wi-Fi, Bluetooth и GPS (необязательно):\n");
        for (Map.Entry entry : ba.p.entrySet()) {
            sb.append("  ".concat(i3.y0((List) entry.getValue(), ", ", new g7(0, (String) entry.getKey()), 30)));
            sb.append('\n');
        }
        sb.append("off — выключено, on — включено (у GPS — есть сигнал), connected — подключено.\nЕсли такой картинки нет, берётся обычная (wifi.png, bluetooth.png, gps.png) и перекрашивается\nпод состояние. С -color в имени (wifi-on-color.png) картинка показывается в своём цвете.\n\nКвадратные PNG, лучше 256×256, не больше 1024×1024.\n");
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            String str = (String) entry2.getKey();
            List<String> list = (List) entry2.getValue();
            sb.append('\n');
            sb.append(str + " (" + list.size() + "):");
            sb.append('\n');
            for (String str2 : list) {
                String str3 = (String) linkedHashMap2.get(str2);
                sb.append("  " + str2 + (str3 != null ? " — ".concat(str3) : ""));
                sb.append('\n');
            }
        }
        return sb.toString();
    }
}
