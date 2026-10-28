package defpackage;

import android.content.Context;
import android.util.Log;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class hh {
    public static final fi a = new fi(new j(15));
    public static final fi b = new fi(new j(16));
    public static final long c = 1845461005;
    public static final long d = 722016414;

    public static String a(Context context) throws IllegalAccessException, IOException, InvocationTargetException {
        byte[] bArrCopyOfRange;
        Long lValueOf;
        int i;
        String str = context.getApplicationInfo().sourceDir;
        if (str == null) {
            return "no sourceDir";
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(str, "r");
        try {
            long length = randomAccessFile.length();
            int iMin = (int) Math.min(length, 65558L);
            byte[] bArr = new byte[iMin];
            randomAccessFile.seek(length - ((long) iMin));
            randomAccessFile.readFully(bArr);
            int i2 = iMin - 22;
            while (true) {
                bArrCopyOfRange = null;
                if (i2 < 0) {
                    lValueOf = null;
                    break;
                }
                if (bArr[i2] == 80 && bArr[i2 + 1] == 75 && bArr[i2 + 2] == 5 && bArr[i2 + 3] == 6) {
                    lValueOf = Long.valueOf(b(bArr, i2 + 16));
                    break;
                }
                i2--;
            }
            if (lValueOf == null) {
                randomAccessFile.close();
                return "no EOCD";
            }
            long jLongValue = lValueOf.longValue();
            if (jLongValue < 32) {
                randomAccessFile.close();
                return "no signing block";
            }
            byte[] bArr2 = new byte[16];
            randomAccessFile.seek(jLongValue - 16);
            randomAccessFile.readFully(bArr2);
            if (!new String(bArr2, q2.c).equals((String) b.a())) {
                randomAccessFile.close();
                return "no signing block magic";
            }
            long j = jLongValue - 24;
            byte[] bArr3 = new byte[8];
            randomAccessFile.seek(j);
            randomAccessFile.readFully(bArr3);
            long jC = jLongValue - c(bArr3, 0);
            long j2 = 8;
            if (jC - 8 < 0) {
                randomAccessFile.close();
                return "bad block size";
            }
            int i3 = (int) (j - jC);
            if (i3 <= 0) {
                randomAccessFile.close();
                return "empty block";
            }
            byte[] bArr4 = new byte[i3];
            randomAccessFile.seek(jC);
            randomAccessFile.readFully(bArr4);
            StringBuilder sb = new StringBuilder();
            int i4 = 0;
            while (true) {
                int i5 = i4 + 12;
                if (i5 > i3) {
                    break;
                }
                long jC2 = c(bArr4, i4);
                if (jC2 < 4) {
                    break;
                }
                int i6 = i4 + 8;
                long j3 = j2;
                if (((long) i6) + jC2 > i3) {
                    break;
                }
                long jB = b(bArr4, i6);
                sb.append(Long.toHexString(jB));
                sb.append(' ');
                if (jB == c || jB == d) {
                    int i7 = i6 + ((int) jC2);
                    k8.x(i7, i3);
                    bArrCopyOfRange = Arrays.copyOfRange(bArr4, i5, i7);
                    bArrCopyOfRange.getClass();
                }
                i4 += (int) (j3 + jC2);
                j2 = j3;
            }
            Log.w("ReferencePanel.Stamp", "signing-block ids: " + ((Object) sb));
            if (bArrCopyOfRange == null) {
                String str2 = "NO STAMP (ids: " + ((Object) sb) + ")";
                randomAccessFile.close();
                return str2;
            }
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            int i8 = 0;
            while (true) {
                int i9 = i8 + 4;
                if (i9 >= bArrCopyOfRange.length) {
                    randomAccessFile.close();
                    return "STAMP PRESENT, cert MISMATCH";
                }
                if (bArrCopyOfRange[i8] == 48 && bArrCopyOfRange[i8 + 1] == -126 && (i = i9 + (((bArrCopyOfRange[i8 + 2] & 255) << 8) | (bArrCopyOfRange[i8 + 3] & 255))) <= bArrCopyOfRange.length) {
                    k8.x(i, bArrCopyOfRange.length);
                    byte[] bArrCopyOfRange2 = Arrays.copyOfRange(bArrCopyOfRange, i8, i);
                    bArrCopyOfRange2.getClass();
                    byte[] bArrDigest = messageDigest.digest(bArrCopyOfRange2);
                    bArrDigest.getClass();
                    if (x0.U0(bArrDigest, "", new fg(2)).equals((String) a.a())) {
                        randomAccessFile.close();
                        return "GENUINE";
                    }
                }
                i8++;
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                k8.p(randomAccessFile, th);
                throw th2;
            }
        }
    }

    public static long b(byte[] bArr, int i) {
        long j = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            j |= (((long) bArr[i + i2]) & 255) << (i2 * 8);
        }
        return j;
    }

    public static long c(byte[] bArr, int i) {
        long j = 0;
        for (int i2 = 0; i2 < 8; i2++) {
            j |= (((long) bArr[i + i2]) & 255) << (i2 * 8);
        }
        return j;
    }
}
