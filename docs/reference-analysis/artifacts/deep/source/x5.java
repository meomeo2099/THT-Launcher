package defpackage;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class x5 {
    public static volatile boolean a = false;
    public static Bitmap b = null;
    public static BitmapShader c = null;
    public static String d = "";
    public static WeakReference e = new WeakReference(null);
    public static final Matrix f = new Matrix();
    public static final Paint g = new Paint(3);
    public static final int[] h = new int[2];
    public static final int[] i = new int[2];

    public static void a(int[] iArr, int[] iArr2, int i2, int i3, boolean z) {
        int i4 = z ? i3 : i2;
        int i5 = z ? i2 : i3;
        for (int i6 = 0; i6 < i4; i6++) {
            int i7 = -2;
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            int i11 = 0;
            while (true) {
                int i12 = i7;
                int iB = b(iArr, z, i6, i2, i5, i12);
                i8 += iB >>> 24;
                i9 += (iB >> 16) & 255;
                i10 += (iB >> 8) & 255;
                i11 += iB & 255;
                if (i12 == 2) {
                    break;
                } else {
                    i7 = i12 + 1;
                }
            }
            for (int i13 = 0; i13 < i5; i13++) {
                iArr2[z ? (i6 * i2) + i13 : (i13 * i2) + i6] = ((i8 / 5) << 24) | ((i9 / 5) << 16) | ((i10 / 5) << 8) | (i11 / 5);
                int iB2 = b(iArr, z, i6, i2, i5, i13 - 2);
                int iB3 = b(iArr, z, i6, i2, i5, i13 + 3);
                i8 += (iB3 >>> 24) - (iB2 >>> 24);
                i9 += ((iB3 >> 16) & 255) - ((iB2 >> 16) & 255);
                i10 += ((iB3 >> 8) & 255) - ((iB2 >> 8) & 255);
                i11 += (iB3 & 255) - (iB2 & 255);
            }
        }
    }

    public static final int b(int[] iArr, boolean z, int i2, int i3, int i4, int i5) {
        int iS;
        if (z) {
            i2 *= i3;
            iS = pi.s(i5, 0, i4 - 1);
        } else {
            iS = pi.s(i5, 0, i4 - 1) * i3;
        }
        return iArr[iS + i2];
    }

    public static void c(View view) {
        view.getClass();
        view.invalidate();
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = viewGroup.getChildAt(i2);
                childAt.getClass();
                c(childAt);
            }
        }
    }
}
