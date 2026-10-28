package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class x3 {
    public static final ArrayList a = i3.D0(i3.J0(new j8(1040, 1103, 1)), j3.i0(1025, 1105));
    public static final List b = j3.i0(1240, 1241, 1170, 1171, 1178, 1179, 1186, 1187, 1256, 1257, 1200, 1201, 1198, 1199, 1210, 1211, 1030, 1110);

    public static Set a(byte[] bArr) {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        Integer numJ = j(bArr, "fvar");
        if (numJ == null) {
            return x4.a;
        }
        int iIntValue = numJ.intValue();
        int i = (byteBufferWrap.getShort(iIntValue + 4) & 65535) + iIntValue;
        int i2 = byteBufferWrap.getShort(iIntValue + 8) & 65535;
        int i3 = byteBufferWrap.getShort(iIntValue + 10) & 65535;
        j8 j8VarF0 = pi.f0(0, i2);
        ArrayList arrayList = new ArrayList();
        Iterator it = j8VarF0.iterator();
        while (true) {
            i8 i8Var = (i8) it;
            if (!i8Var.c) {
                return i3.L0(arrayList);
            }
            int iNextInt = (i8Var.nextInt() * i3) + i;
            Integer numValueOf = Integer.valueOf(iNextInt);
            if (iNextInt + 4 > bArr.length) {
                numValueOf = null;
            }
            String str = numValueOf != null ? new String(bArr, numValueOf.intValue(), 4, q2.d) : null;
            if (str != null) {
                arrayList.add(str);
            }
        }
    }

    public static Set b(byte[] bArr) {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        Integer numJ = j(bArr, "cmap");
        if (numJ == null) {
            return x4.a;
        }
        int iIntValue = numJ.intValue();
        HashSet hashSet = new HashSet();
        int i = byteBufferWrap.getShort(iIntValue + 2) & 65535;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = byteBufferWrap.getInt((i2 * 8) + iIntValue + 8) + iIntValue;
            int i4 = byteBufferWrap.getShort(i3) & 65535;
            if (i4 == 4) {
                int i5 = (byteBufferWrap.getShort(i3 + 6) & 65535) / 2;
                for (int i6 = 0; i6 < i5; i6++) {
                    int i7 = i6 * 2;
                    int i8 = byteBufferWrap.getShort(i3 + 14 + i7) & 65535;
                    int i9 = byteBufferWrap.getShort((i5 * 2) + i3 + 16 + i7) & 65535;
                    if (i9 != 65535 && i8 >= i9 && i9 <= i8) {
                        while (true) {
                            hashSet.add(Integer.valueOf(i9));
                            if (i9 != i8) {
                                i9++;
                            }
                        }
                    }
                }
            } else if (i4 == 12) {
                int i10 = byteBufferWrap.getInt(i3 + 12);
                if (i10 > 50000) {
                    i10 = 50000;
                }
                for (int i11 = 0; i11 < i10; i11++) {
                    int i12 = i11 * 12;
                    int i13 = byteBufferWrap.getInt(i3 + 16 + i12);
                    int i14 = byteBufferWrap.getInt(i3 + 20 + i12);
                    if (i14 >= i13 && i14 - i13 < 200000 && i13 <= i14) {
                        while (true) {
                            hashSet.add(Integer.valueOf(i13));
                            if (i13 != i14) {
                                i13++;
                            }
                        }
                    }
                }
            }
        }
        return hashSet;
    }

    public static boolean c(Context context) {
        return k8.e(context.getSharedPreferences("panel", 0).getString("font_choice", ""), "custom") && d(context).isFile();
    }

    public static File d(Context context) {
        return new File(context.getFilesDir(), "fonts/custom.ttf");
    }

    public static String e(byte[] bArr) {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        Integer numJ = j(bArr, "name");
        String str = null;
        if (numJ != null) {
            int iIntValue = numJ.intValue();
            int i = byteBufferWrap.getShort(iIntValue + 2) & 65535;
            int i2 = (byteBufferWrap.getShort(iIntValue + 4) & 65535) + iIntValue;
            for (int i3 = 0; i3 < i; i3++) {
                int i4 = (i3 * 12) + iIntValue + 6;
                int i5 = byteBufferWrap.getShort(i4) & 65535;
                int i6 = byteBufferWrap.getShort(i4 + 6) & 65535;
                int i7 = byteBufferWrap.getShort(i4 + 8) & 65535;
                int i8 = (byteBufferWrap.getShort(i4 + 10) & 65535) + i2;
                if ((i5 == 3 || i5 == 0) && i8 + i7 <= bArr.length) {
                    String string = th.J(new String(bArr, i8, i7, q2.b)).toString();
                    if (i6 == 4 && string.length() > 0) {
                        return string;
                    }
                    if (i6 == 1 && str == null) {
                        str = string;
                    }
                }
            }
        }
        return str;
    }

    public static String f(Context context, Uri uri) throws IllegalAccessException, IOException, InvocationTargetException {
        int i;
        Object ffVar;
        Object ffVar2;
        Object ffVar3;
        Object ffVar4;
        uri.getClass();
        InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(uri);
        boolean z = false;
        if (inputStreamOpenInputStream == null) {
            w.a(si.a("file_unavailable", new Object[0]));
            return null;
        }
        try {
            byte[] bArrI = i(inputStreamOpenInputStream);
            inputStreamOpenInputStream.close();
            if (bArrI.length >= 2 && bArrI[0] == 80 && bArrI[1] == 75) {
                ArrayList arrayList = new ArrayList();
                ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(bArrI));
                while (true) {
                    try {
                        ZipEntry nextEntry = zipInputStream.getNextEntry();
                        if (nextEntry == null) {
                            break;
                        }
                        String name = nextEntry.getName();
                        name.getClass();
                        String lowerCase = th.E(name, '/').toLowerCase(Locale.ROOT);
                        lowerCase.getClass();
                        if (!nextEntry.isDirectory() && (lowerCase.endsWith(".ttf") || lowerCase.endsWith(".otf"))) {
                            arrayList.add(new yc(lowerCase, i(zipInputStream)));
                            if (arrayList.size() >= 20) {
                                break;
                            }
                        }
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            k8.p(zipInputStream, th);
                            throw th2;
                        }
                    }
                }
                zipInputStream.close();
                ArrayList arrayList2 = new ArrayList();
                int size = arrayList.size();
                int i2 = 0;
                while (i2 < size) {
                    Object obj = arrayList.get(i2);
                    i2++;
                    String str = (String) ((yc) obj).a;
                    if (!th.v(str, "italic", false) && !th.v(str, "oblique", false)) {
                        arrayList2.add(obj);
                    }
                }
                if (!arrayList2.isEmpty()) {
                    arrayList = arrayList2;
                }
                yc ycVar = (yc) i3.v0(arrayList);
                if (ycVar == null || (bArrI = (byte[]) ycVar.b) == null) {
                    w.a(si.a("font_not_font", new Object[0]));
                    return null;
                }
            }
            if (bArrI.length < 12 || !((i = ByteBuffer.wrap(bArrI, 0, 4).getInt()) == 65536 || i == 1330926671 || i == 1953658213)) {
                w.a(si.a("font_not_font", new Object[0]));
                return null;
            }
            try {
                ffVar = b(bArrI);
            } catch (Throwable th3) {
                ffVar = new ff(th3);
            }
            if (ffVar instanceof ff) {
                ffVar = null;
            }
            Set set = (Set) ffVar;
            if (set == null) {
                w.a(si.a("font_not_font", new Object[0]));
                return null;
            }
            ArrayList arrayList3 = a;
            if (arrayList3 == null || !arrayList3.isEmpty()) {
                int size2 = arrayList3.size();
                int i3 = 0;
                while (i3 < size2) {
                    Object obj2 = arrayList3.get(i3);
                    i3++;
                    if (!set.contains(Integer.valueOf(((Number) obj2).intValue()))) {
                        w.a(si.a("font_no_cyrillic", new Object[0]));
                        return null;
                    }
                }
            }
            File fileD = d(context);
            File parentFile = fileD.getParentFile();
            if (parentFile != null) {
                parentFile.mkdirs();
            }
            File file = new File(fileD.getParentFile(), "custom.part");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(bArrI);
                fileOutputStream.close();
                try {
                    ffVar2 = new Typeface.Builder(file).build();
                    if (ffVar2 == null) {
                        throw new IllegalStateException("no typeface");
                    }
                } catch (Throwable th4) {
                    ffVar2 = new ff(th4);
                }
                if (gf.a(ffVar2) != null) {
                    file.delete();
                    w.a(si.a("font_not_font", new Object[0]));
                    return null;
                }
                if (!file.renameTo(fileD)) {
                    fileD.delete();
                    if (!file.renameTo(fileD)) {
                        throw new IllegalStateException(si.a("file_unavailable", new Object[0]).toString());
                    }
                }
                try {
                    ffVar3 = e(bArrI);
                } catch (Throwable th5) {
                    ffVar3 = new ff(th5);
                }
                if (ffVar3 instanceof ff) {
                    ffVar3 = null;
                }
                String strA = (String) ffVar3;
                if (strA == null) {
                    String lastPathSegment = uri.getLastPathSegment();
                    String strH = lastPathSegment != null ? th.H(th.E(lastPathSegment, '/'), '.') : null;
                    strA = strH == null ? si.a("font_custom", new Object[0]) : strH;
                }
                SharedPreferences.Editor editorPutString = context.getSharedPreferences("panel", 0).edit().putString("font_choice", "custom").putString("font_custom_name", strA);
                List list = b;
                if (list != null && list.isEmpty()) {
                    z = true;
                    break;
                }
                Iterator it = list.iterator();
                do {
                    if (!it.hasNext()) {
                        z = true;
                        break;
                    }
                } while (set.contains(Integer.valueOf(((Number) it.next()).intValue())));
                SharedPreferences.Editor editorPutBoolean = editorPutString.putBoolean("font_custom_kk", z);
                try {
                    ffVar4 = a(bArrI);
                } catch (Throwable th6) {
                    ffVar4 = new ff(th6);
                }
                if (ffVar4 instanceof ff) {
                    ffVar4 = x4.a;
                }
                editorPutBoolean.putBoolean("font_custom_wght", ((Set) ffVar4).contains("wght")).apply();
                return strA;
            } catch (Throwable th7) {
                try {
                    throw th7;
                } catch (Throwable th8) {
                    k8.p(fileOutputStream, th7);
                    throw th8;
                }
            }
        } catch (Throwable th9) {
            try {
                throw th9;
            } catch (Throwable th10) {
                k8.p(inputStreamOpenInputStream, th9);
                throw th10;
            }
        }
    }

    public static boolean g(MainActivity mainActivity) {
        return d(mainActivity).isFile();
    }

    public static String h(MainActivity mainActivity) {
        String string = mainActivity.getSharedPreferences("panel", 0).getString("font_custom_name", null);
        if (string == null || th.A(string)) {
            return null;
        }
        return string;
    }

    public static byte[] i(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[65536];
        long j = 0;
        while (true) {
            int i = inputStream.read(bArr);
            if (i < 0) {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArray.getClass();
                return byteArray;
            }
            j += (long) i;
            if (j > 26214400) {
                w.a(si.a("font_too_large", new Object[0]));
                return null;
            }
            byteArrayOutputStream.write(bArr, 0, i);
        }
    }

    public static Integer j(byte[] bArr, String str) {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        int i = byteBufferWrap.getShort(4) & 65535;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 * 16;
            int i4 = i3 + 12;
            if (i3 + 28 > bArr.length) {
                return null;
            }
            if (new String(bArr, i4, 4, q2.d).equals(str)) {
                int i5 = byteBufferWrap.getInt(i3 + 20);
                Integer numValueOf = Integer.valueOf(i5);
                if (i5 < 0 || i5 >= bArr.length) {
                    return null;
                }
                return numValueOf;
            }
        }
        return null;
    }
}
