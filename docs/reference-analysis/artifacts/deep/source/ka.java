package defpackage;

import android.graphics.Paint;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class ka {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[Paint.Align.values().length];
        try {
            iArr[Paint.Align.RIGHT.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[Paint.Align.CENTER.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        a = iArr;
    }
}
