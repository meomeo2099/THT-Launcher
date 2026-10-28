package defpackage;

import android.content.Context;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class k7 {
    public static final me a = new me("widget_gallery_[a-f0-9]{32}\\.png");

    public static void a(Context context, File file) {
        context.getClass();
        if (file.isFile()) {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                long length = file.length();
                if (length > 2147483647L) {
                    throw new OutOfMemoryError("File " + file + " is too big (" + length + " bytes) to fit in memory.");
                }
                int i = (int) length;
                byte[] bArrCopyOf = new byte[i];
                int i2 = i;
                int i3 = 0;
                while (i2 > 0) {
                    int i4 = fileInputStream.read(bArrCopyOf, i3, i2);
                    if (i4 < 0) {
                        break;
                    }
                    i2 -= i4;
                    i3 += i4;
                }
                if (i2 > 0) {
                    bArrCopyOf = Arrays.copyOf(bArrCopyOf, i3);
                } else {
                    int i5 = fileInputStream.read();
                    if (i5 != -1) {
                        z4 z4Var = new z4(8193);
                        z4Var.write(i5);
                        pi.u(fileInputStream, z4Var);
                        int size = z4Var.size() + i;
                        if (size < 0) {
                            throw new OutOfMemoryError("File " + file + " is too big to fit in memory.");
                        }
                        byte[] bArrA = z4Var.a();
                        bArrCopyOf = Arrays.copyOf(bArrCopyOf, size);
                        System.arraycopy(bArrA, 0, bArrCopyOf, i, z4Var.size());
                    }
                }
                fileInputStream.close();
                byte[] bArrDigest = messageDigest.digest(bArrCopyOf);
                bArrDigest.getClass();
                String strU0 = x0.U0(bArrDigest, "", new w(3));
                File file2 = new File(context.getFilesDir(), "widget_gallery_" + strU0 + ".png");
                if (file2.exists()) {
                    return;
                }
                try {
                    m5.h0(file, file2, 6);
                } catch (Throwable unused) {
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    k8.p(fileInputStream, th);
                    throw th2;
                }
            }
        }
    }
}
