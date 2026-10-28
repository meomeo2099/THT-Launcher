package defpackage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextPaint;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class c {
    public static float a(TextPaint textPaint, float f, float f2) {
        return (textPaint.getTextSize() * f) + f2;
    }

    public static int b(int i, int i2, String str) {
        return (str.hashCode() + i) * i2;
    }

    public static String c(int i, int i2, String str, String str2) {
        return str + i + str2 + i2;
    }

    public static String d(String str, String str2, String str3) {
        return str + str2 + str3;
    }

    public static void e(String str, Object[] objArr, AlertDialog.Builder builder, DialogInterface.OnClickListener onClickListener, MainActivity mainActivity) {
        AlertDialog.Builder negativeButton = builder.setNegativeButton(si.a(str, objArr), onClickListener);
        negativeButton.getClass();
        mainActivity.p0(negativeButton);
    }
}
