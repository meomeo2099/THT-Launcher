package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class v7 {
    public static final HashMap a = new HashMap();

    public static Bitmap a(Context context, String str) {
        Object ffVar;
        context.getClass();
        str.getClass();
        File fileC = c(context, str);
        boolean zIsFile = fileC.isFile();
        HashMap map = a;
        if (!zIsFile) {
            map.remove(str);
            return null;
        }
        long length = fileC.length() + fileC.lastModified();
        yc ycVar = (yc) map.get(str);
        if (ycVar != null) {
            if (((Number) ycVar.a).longValue() != length) {
                ycVar = null;
            }
            if (ycVar != null) {
                return (Bitmap) ycVar.b;
            }
        }
        try {
            ffVar = BitmapFactory.decodeFile(fileC.getAbsolutePath());
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        if (ffVar instanceof ff) {
            ffVar = null;
        }
        Bitmap bitmap = (Bitmap) ffVar;
        if (bitmap == null) {
            return null;
        }
        map.put(str, new yc(Long.valueOf(length), bitmap));
        return bitmap;
    }

    public static String b(String str) {
        return "desktop_image_" + str;
    }

    public static File c(Context context, String str) {
        context.getClass();
        str.getClass();
        File filesDir = context.getFilesDir();
        int iZ = th.z(str, '_', 0, 6);
        if (iZ != -1) {
            str = str.substring(iZ + 1, str.length());
        }
        return new File(filesDir, "widget_image_" + str + ".png");
    }

    public static r7 d(SharedPreferences sharedPreferences, String str) {
        List list;
        Object ffVar;
        Object next;
        u7 u7VarA;
        Object obj;
        Object next2;
        str.getClass();
        u7 u7Var = null;
        String string = sharedPreferences.getString(b(str), null);
        if (string == null) {
            return new r7();
        }
        int i = 0;
        List listD = th.D(string, new char[]{'|'});
        if (listD.size() != 3 || !k8.e(listD.get(0), "v2")) {
            return new r7();
        }
        if (((CharSequence) listD.get(2)).length() == 0) {
            list = u4.a;
        } else {
            List listD2 = th.D((CharSequence) listD.get(2), new char[]{';'});
            ArrayList arrayList = new ArrayList();
            Iterator it = listD2.iterator();
            while (it.hasNext()) {
                List listD3 = th.D((String) it.next(), new char[]{'~'});
                List listD4 = th.D((CharSequence) listD3.get(0), new char[]{','});
                ArrayList arrayList2 = new ArrayList();
                Iterator it2 = listD4.iterator();
                while (it2.hasNext()) {
                    Float fP = ai.p((String) it2.next());
                    if (fP == null || Math.abs(fP.floatValue()) > Float.MAX_VALUE) {
                        obj = fP;
                        obj = u7Var;
                    }
                    if (obj != null) {
                        arrayList2.add(obj);
                    }
                }
                if (arrayList2.size() != 4) {
                    u7VarA = u7Var;
                } else {
                    try {
                        ffVar = URLDecoder.decode((String) (1 < listD3.size() ? listD3.get(1) : ""), "UTF-8");
                    } catch (Throwable th) {
                        ffVar = new ff(th);
                    }
                    if (ffVar instanceof ff) {
                        ffVar = "";
                    }
                    String str2 = (String) ffVar;
                    float fFloatValue = ((Number) arrayList2.get(0)).floatValue();
                    float fFloatValue2 = ((Number) arrayList2.get(1)).floatValue();
                    float fFloatValue3 = ((Number) arrayList2.get(2)).floatValue();
                    float fFloatValue4 = ((Number) arrayList2.get(3)).floatValue();
                    str2.getClass();
                    String str3 = (String) i3.w0(listD3, 2);
                    t7.c.getClass();
                    y4 y4Var = t7.h;
                    y4Var.getClass();
                    d dVar = new d(i, y4Var);
                    do {
                        if (!dVar.hasNext()) {
                            next = null;
                            break;
                        }
                        next = dVar.next();
                    } while (!((t7) next).a.equals(str3));
                    t7 t7Var = (t7) next;
                    if (t7Var == null) {
                        t7Var = t7.d;
                    }
                    u7VarA = new u7(fFloatValue, fFloatValue2, fFloatValue3, fFloatValue4, str2, t7Var).a();
                }
                if (u7VarA != null) {
                    arrayList.add(u7VarA);
                }
                u7Var = null;
            }
            list = arrayList;
        }
        Integer numQ = bi.q((String) listD.get(1));
        s7.c.getClass();
        y4 y4Var2 = s7.g;
        y4Var2.getClass();
        d dVar2 = new d(i, y4Var2);
        while (true) {
            if (!dVar2.hasNext()) {
                next2 = null;
                break;
            }
            next2 = dVar2.next();
            int i2 = ((s7) next2).a;
            if (numQ != null && i2 == numQ.intValue()) {
                break;
            }
        }
        s7 s7Var = (s7) next2;
        if (s7Var == null) {
            s7Var = s7.d;
        }
        return new r7(list, s7Var);
    }
}
