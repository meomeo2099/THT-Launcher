package defpackage;

import android.content.ComponentName;
import android.content.SharedPreferences;
import java.util.HashMap;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class y7 implements j6 {
    public final /* synthetic */ int a;
    public final /* synthetic */ MainActivity b;
    public final /* synthetic */ String c;

    public /* synthetic */ y7(MainActivity mainActivity, String str, int i) {
        this.a = i;
        this.b = mainActivity;
        this.c = str;
    }

    @Override // defpackage.j6
    public final Object b(Object obj) {
        int i = this.a;
        ti tiVar = ti.a;
        String str = this.c;
        MainActivity mainActivity = this.b;
        switch (i) {
            case 0:
                r7 r7Var = (r7) obj;
                r7Var.getClass();
                f8.c(mainActivity, str, r7Var);
                break;
            case 1:
                int iIntValue = ((Integer) obj).intValue();
                str.getClass();
                HashMap map = v7.a;
                SharedPreferences sharedPreferencesB = mainActivity.B();
                sharedPreferencesB.getClass();
                u7 u7Var = (u7) i3.w0(v7.d(sharedPreferencesB, str).a, iIntValue);
                String str2 = u7Var != null ? u7Var.e : null;
                if (str2 == null) {
                    str2 = "";
                }
                if (!th.A(str2) && !str2.equals("empty")) {
                    mainActivity.g0(-1, str2);
                } else if (!mainActivity.A()) {
                    if (!v7.c(mainActivity, str).isFile()) {
                        pi.F(str, mainActivity);
                    } else {
                        f8.b(str, mainActivity);
                    }
                }
                break;
            default:
                ComponentName componentName = (ComponentName) obj;
                componentName.getClass();
                mainActivity.B().edit().putString("desktop_app_".concat(str), componentName.flattenToString()).apply();
                mainActivity.e0();
                break;
        }
        return tiVar;
    }
}
