package defpackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class m5 extends pi {
    public static boolean g0(File file, File file2) throws IllegalAccessException, IOException, InvocationTargetException {
        l0 l0Var = l0.c;
        if (!file.exists()) {
            l0Var.c(file, new i(file, null, "The source file doesn't exist."));
            throw null;
        }
        try {
            i5 i5Var = new i5(new k5(file, l5.a, new a(2, l0Var)));
            while (i5Var.hasNext()) {
                File file3 = (File) i5Var.next();
                if (!file3.exists()) {
                    l0Var.c(file3, new i(file3, null, "The source file doesn't exist."));
                    throw null;
                }
                File file4 = new File(file2, l0(file3, file));
                if (file4.exists() && (!file3.isDirectory() || !file4.isDirectory())) {
                    if (file4.isDirectory()) {
                        if (!i0(file4)) {
                            l0Var.c(file4, new i(file3, file4, "The destination file already exists."));
                            throw null;
                        }
                    } else if (!file4.delete()) {
                        l0Var.c(file4, new i(file3, file4, "The destination file already exists."));
                        throw null;
                    }
                }
                if (file3.isDirectory()) {
                    file4.mkdirs();
                } else {
                    h0(file3, file4, 4);
                    if (file4.length() != file3.length()) {
                        l0Var.c(file3, new IOException("Source file wasn't copied completely, length of destination file differs."));
                        throw null;
                    }
                }
            }
            return true;
        } catch (hi unused) {
            return false;
        }
    }

    public static void h0(File file, File file2, int i) throws IllegalAccessException, IOException, InvocationTargetException {
        boolean z = (i & 2) == 0;
        if (!file.exists()) {
            throw new i(file, null, "The source file doesn't exist.");
        }
        if (file2.exists()) {
            if (!z) {
                throw new i(file, file2, "The destination file already exists.");
            }
            if (!file2.delete()) {
                throw new i(file, file2, "Tried to overwrite the destination, but failed to delete it.");
            }
        }
        if (file.isDirectory()) {
            if (!file2.mkdirs()) {
                throw new d5(file, file2, "Failed to create target directory.");
            }
            return;
        }
        File parentFile = file2.getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            try {
                pi.u(fileInputStream, fileOutputStream);
                fileOutputStream.close();
                fileInputStream.close();
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    k8.p(fileOutputStream, th);
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            try {
                throw th3;
            } catch (Throwable th4) {
                k8.p(fileInputStream, th3);
                throw th4;
            }
        }
    }

    public static boolean i0(File file) {
        i5 i5Var = new i5(new k5(file, l5.b, null));
        while (true) {
            boolean z = true;
            while (i5Var.hasNext()) {
                File file2 = (File) i5Var.next();
                if (file2.delete() || !file2.exists()) {
                    if (z) {
                    }
                }
                z = false;
            }
            return z;
        }
    }

    public static final c5 j0(c5 c5Var) {
        File file = c5Var.a;
        List<File> list = c5Var.b;
        ArrayList arrayList = new ArrayList(list.size());
        for (File file2 : list) {
            String name = file2.getName();
            if (!k8.e(name, ".")) {
                if (!k8.e(name, "..")) {
                    arrayList.add(file2);
                } else if (arrayList.isEmpty() || k8.e(((File) i3.z0(arrayList)).getName(), "..")) {
                    arrayList.add(file2);
                }
            }
        }
        return new c5(file, arrayList);
    }

    public static String k0(File file) throws IllegalAccessException, IOException, InvocationTargetException {
        Charset charset = q2.a;
        charset.getClass();
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), charset);
        try {
            String strL = sh.l(inputStreamReader);
            inputStreamReader.close();
            return strL;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                k8.p(inputStreamReader, th);
                throw th2;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:21:0x006d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:22:0x006f  */
    public static final String l0(File file, File file2) {
        c5 c5VarJ0 = j0(pi.c0(file));
        List list = c5VarJ0.b;
        c5 c5VarJ1 = j0(pi.c0(file2));
        List list2 = c5VarJ1.b;
        boolean zEquals = c5VarJ0.a.equals(c5VarJ1.a);
        String string = null;
        if (zEquals) {
            int size = list2.size();
            int size2 = list.size();
            int iMin = Math.min(size2, size);
            int i = 0;
            while (i < iMin && k8.e(list.get(i), list2.get(i))) {
                i++;
            }
            StringBuilder sb = new StringBuilder();
            int i2 = size - 1;
            if (i <= i2) {
                while (true) {
                    if (!k8.e(((File) list2.get(i2)).getName(), "..")) {
                        sb.append("..");
                        if (i2 != i) {
                            sb.append(File.separatorChar);
                        }
                        if (i2 != i) {
                            i2--;
                        } else {
                            if (i < size2) {
                                if (i < size) {
                                    sb.append(File.separatorChar);
                                }
                                List listS0 = i3.s0(list, i);
                                String str = File.separator;
                                str.getClass();
                                i3.x0(listS0, sb, str, "", "", "...", null);
                            }
                            string = sb.toString();
                        }
                    }
                }
            } else {
                if (i < size2) {
                    if (i < size) {
                        sb.append(File.separatorChar);
                    }
                    List listS1 = i3.s0(list, i);
                    String str2 = File.separator;
                    str2.getClass();
                    i3.x0(listS1, sb, str2, "", "", "...", null);
                }
                string = sb.toString();
            }
        }
        if (string != null) {
            return string;
        }
        throw new IllegalArgumentException("this and base files have different roots: " + file + " and " + file2 + '.');
    }
}
