package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class z0 {
    public final SharedPreferences a;

    public z0(Context context) {
        this.a = context.getSharedPreferences("panel", 0);
    }

    public int a(int i, String str) {
        return this.a.getInt("auto_".concat(str), i);
    }

    public boolean b(String str, boolean z) {
        str.getClass();
        return this.a.getBoolean("auto_" + str + "_on", z);
    }

    public void c(int i, String str) {
        this.a.edit().putInt("auto_".concat(str), i).apply();
    }

    public void d(String str, boolean z) {
        this.a.edit().putBoolean("auto_" + str + "_on", z).apply();
    }

    public z0(MainActivity mainActivity) {
        this.a = mainActivity.getSharedPreferences("panel", 0);
    }
}
