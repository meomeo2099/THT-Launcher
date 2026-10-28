package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class th extends ci {
    public static boolean A(CharSequence charSequence) {
        charSequence.getClass();
        for (int i = 0; i < charSequence.length(); i++) {
            char cCharAt = charSequence.charAt(i);
            if (!Character.isWhitespace(cCharAt) && !Character.isSpaceChar(cCharAt)) {
                return false;
            }
        }
        return true;
    }

    public static int B(String str, char c) {
        str.getClass();
        int length = str.length() - 1;
        str.getClass();
        return str.lastIndexOf(c, length);
    }

    public static String C(String str, String str2) {
        str.getClass();
        str2.getClass();
        return str.startsWith(str2) ? str.substring(str2.length()) : str;
    }

    public static List D(CharSequence charSequence, char[] cArr) {
        charSequence.getClass();
        if (cArr.length == 1) {
            String strValueOf = String.valueOf(cArr[0]);
            int iW = w(0, charSequence, strValueOf, false);
            if (iW == -1) {
                return pi.J(charSequence.toString());
            }
            ArrayList arrayList = new ArrayList(10);
            int length = 0;
            do {
                arrayList.add(charSequence.subSequence(length, iW).toString());
                length = strValueOf.length() + iW;
                iW = w(length, charSequence, strValueOf, false);
            } while (iW != -1);
            arrayList.add(charSequence.subSequence(length, charSequence.length()).toString());
            return arrayList;
        }
        yf yfVar = new yf(new a4(charSequence, new a(13, cArr)));
        ArrayList arrayList2 = new ArrayList(k3.n0(yfVar));
        Iterator it = yfVar.iterator();
        while (true) {
            z3 z3Var = (z3) it;
            if (!z3Var.hasNext()) {
                return arrayList2;
            }
            j8 j8Var = (j8) z3Var.next();
            j8Var.getClass();
            arrayList2.add(charSequence.subSequence(j8Var.a, j8Var.b + 1).toString());
        }
    }

    public static String E(String str, char c) {
        str.getClass();
        str.getClass();
        int iB = B(str, c);
        return iB == -1 ? str : str.substring(iB + 1, str.length());
    }

    public static String F(String str, char c) {
        str.getClass();
        str.getClass();
        int iZ = z(str, c, 0, 6);
        return iZ == -1 ? str : str.substring(0, iZ);
    }

    public static String G(String str, String str2) {
        str.getClass();
        str.getClass();
        int iY = y(6, str, str2, false);
        return iY == -1 ? str : str.substring(0, iY);
    }

    public static String H(String str, char c) {
        int iB = B(str, c);
        return iB == -1 ? str : str.substring(0, iB);
    }

    public static String I(int i, String str) {
        str.getClass();
        if (i < 0) {
            fg.a(i, "Requested character count ");
            return null;
        }
        int length = str.length();
        if (i > length) {
            i = length;
        }
        return str.substring(0, i);
    }

    public static CharSequence J(CharSequence charSequence) {
        charSequence.getClass();
        int length = charSequence.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            char cCharAt = charSequence.charAt(!z ? i : length);
            boolean z2 = Character.isWhitespace(cCharAt) || Character.isSpaceChar(cCharAt);
            if (z) {
                if (!z2) {
                    break;
                }
                length--;
            } else if (z2) {
                i++;
            } else {
                z = true;
            }
        }
        return charSequence.subSequence(i, length + 1);
    }

    public static String K(String str, char... cArr) {
        CharSequence charSequenceSubSequence;
        str.getClass();
        int length = str.length() - 1;
        if (length < 0) {
            charSequenceSubSequence = "";
            break;
        }
        while (true) {
            int i = length - 1;
            char cCharAt = str.charAt(length);
            int length2 = cArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length2) {
                    i2 = -1;
                    break;
                }
                if (cCharAt == cArr[i2]) {
                    break;
                }
                i2++;
            }
            if (!(i2 >= 0)) {
                charSequenceSubSequence = str.subSequence(0, length + 1);
                break;
            }
            if (i < 0) {
                charSequenceSubSequence = "";
                break;
            }
            length = i;
        }
        return charSequenceSubSequence.toString();
    }

    public static ArrayList u(String str) {
        pi.n(4, 4);
        int length = str.length();
        int i = 0;
        ArrayList arrayList = new ArrayList((length / 4) + (length % 4 == 0 ? 0 : 1));
        while (i >= 0 && i < length) {
            int i2 = i + 4;
            CharSequence charSequenceSubSequence = str.subSequence(i, (i2 < 0 || i2 > length) ? length : i2);
            charSequenceSubSequence.getClass();
            arrayList.add(charSequenceSubSequence.toString());
            i = i2;
        }
        return arrayList;
    }

    public static boolean v(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        charSequence.getClass();
        charSequence2.getClass();
        if (charSequence2 instanceof String) {
            if (y(2, charSequence, (String) charSequence2, z) >= 0) {
                return true;
            }
        } else if (x(charSequence, charSequence2, 0, charSequence.length(), z) >= 0) {
            return true;
        }
        return false;
    }

    public static final int w(int i, CharSequence charSequence, String str, boolean z) {
        charSequence.getClass();
        str.getClass();
        return (z || !(charSequence instanceof String)) ? x(charSequence, str, i, charSequence.length(), z) : ((String) charSequence).indexOf(str, i);
    }

    public static int x(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z) {
        boolean z2;
        boolean z3;
        boolean zRegionMatches;
        if (i < 0) {
            i = 0;
        }
        int length = charSequence.length();
        if (i2 > length) {
            i2 = length;
        }
        j8 j8Var = new j8(i, i2, 1);
        boolean z4 = charSequence instanceof String;
        int i3 = j8Var.c;
        int i4 = j8Var.b;
        if (z4 && (charSequence2 instanceof String)) {
            if ((i3 <= 0 || i > i4) && (i3 >= 0 || i4 > i)) {
                return -1;
            }
            int i5 = i;
            while (true) {
                String str = (String) charSequence2;
                String str2 = (String) charSequence;
                int length2 = str.length();
                if (z) {
                    z3 = z;
                    zRegionMatches = str.regionMatches(z3, 0, str2, i5, length2);
                } else {
                    zRegionMatches = str.regionMatches(0, str2, i5, length2);
                    z3 = z;
                }
                if (zRegionMatches) {
                    return i5;
                }
                if (i5 == i4) {
                    return -1;
                }
                i5 += i3;
                z = z3;
            }
        } else {
            if ((i3 <= 0 || i > i4) && (i3 >= 0 || i4 > i)) {
                return -1;
            }
            while (true) {
                int length3 = charSequence2.length();
                charSequence2.getClass();
                charSequence.getClass();
                if (i >= 0 && charSequence2.length() - length3 >= 0 && i <= charSequence.length() - length3) {
                    int i6 = 0;
                    while (true) {
                        if (i6 >= length3) {
                            z2 = true;
                            break;
                        }
                        if (!pi.x(charSequence2.charAt(0 + i6), charSequence.charAt(i + i6), z)) {
                            z2 = false;
                            break;
                        }
                        i6++;
                    }
                } else {
                    z2 = false;
                    break;
                }
                if (z2) {
                    return i;
                }
                if (i == i4) {
                    return -1;
                }
                i += i3;
            }
        }
    }

    public static /* synthetic */ int y(int i, CharSequence charSequence, String str, boolean z) {
        if ((i & 4) != 0) {
            z = false;
        }
        return w(0, charSequence, str, z);
    }

    public static int z(String str, char c, int i, int i2) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        str.getClass();
        return str.indexOf(c, i);
    }
}
