package defpackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class x0 extends k8 {
    public static void Q0(Object[] objArr, Object[] objArr2, int i, int i2, int i3) {
        objArr.getClass();
        objArr2.getClass();
        System.arraycopy(objArr, i2, objArr2, i, i3 - i2);
    }

    public static final void R0(Object[] objArr, int i, int i2) {
        objArr.getClass();
        Arrays.fill(objArr, i, i2, (Object) null);
    }

    public static List S0(Object[] objArr) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : objArr) {
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static Object T0(Object[] objArr, int i) {
        objArr.getClass();
        if (i < 0 || i >= objArr.length) {
            return null;
        }
        return objArr[i];
    }

    public static String U0(byte[] bArr, String str, j6 j6Var) {
        bArr.getClass();
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) "");
        int i = 0;
        for (byte b : bArr) {
            i++;
            if (i > 1) {
                sb.append((CharSequence) str);
            }
            sb.append((CharSequence) j6Var.b(Byte.valueOf(b)));
        }
        sb.append((CharSequence) "");
        return sb.toString();
    }

    public static String V0(int[] iArr, j6 j6Var, int i) {
        String str = (i & 1) != 0 ? ", " : ",";
        if ((i & 32) != 0) {
            j6Var = null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) "");
        int i2 = 0;
        for (int i3 : iArr) {
            i2++;
            if (i2 > 1) {
                sb.append((CharSequence) str);
            }
            if (j6Var != null) {
                sb.append((CharSequence) j6Var.b(Integer.valueOf(i3)));
            } else {
                sb.append((CharSequence) String.valueOf(i3));
            }
        }
        sb.append((CharSequence) "");
        return sb.toString();
    }

    public static char W0(char[] cArr) {
        int length = cArr.length;
        if (length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        if (length == 1) {
            return cArr[0];
        }
        w.a("Array has more than one element.");
        return (char) 0;
    }

    public static List X0(int[] iArr) {
        int length = iArr.length;
        if (length == 0) {
            return u4.a;
        }
        if (length == 1) {
            return pi.J(Integer.valueOf(iArr[0]));
        }
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int i : iArr) {
            arrayList.add(Integer.valueOf(i));
        }
        return arrayList;
    }

    public static Set Y0(Object[] objArr) {
        int length = objArr.length;
        if (length == 0) {
            return x4.a;
        }
        if (length == 1) {
            return pi.P(objArr[0]);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(hb.i0(objArr.length));
        for (Object obj : objArr) {
            linkedHashSet.add(obj);
        }
        return linkedHashSet;
    }
}
