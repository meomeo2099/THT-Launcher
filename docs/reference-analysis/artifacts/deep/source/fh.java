package defpackage;

import android.content.ComponentName;
import android.content.SharedPreferences;
import java.util.List;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class fh implements j6 {
    public final /* synthetic */ int a = 1;
    public final /* synthetic */ int b;
    public final /* synthetic */ String c;
    public final /* synthetic */ Object d;

    public /* synthetic */ fh(int i, String str, j6 j6Var) {
        this.b = i;
        this.c = str;
        this.d = j6Var;
    }

    @Override // defpackage.j6
    public final Object b(Object obj) {
        int i = this.a;
        Object obj2 = this.d;
        String str = this.c;
        int i2 = this.b;
        switch (i) {
            case 0:
                MainActivity mainActivity = (MainActivity) obj2;
                ComponentName componentName = (ComponentName) obj;
                componentName.getClass();
                List list = eh.a;
                SharedPreferences sharedPreferencesB = mainActivity.B();
                sharedPreferencesB.getClass();
                eh.h(sharedPreferencesB, str, i2 == 0, componentName);
                mainActivity.e0();
                k8.y0(str, mainActivity);
                return ti.a;
            default:
                j6 j6Var = (j6) obj2;
                r4 r4Var = (r4) obj;
                r4Var.getClass();
                if (!r4Var.H(i2)) {
                    throw new IllegalStateException(si.a("0_not_supported_by_the_vehicle", str));
                }
                Boolean bool = (Boolean) j6Var.b(r4Var);
                bool.getClass();
                return bool;
        }
    }

    public /* synthetic */ fh(MainActivity mainActivity, String str, int i) {
        this.d = mainActivity;
        this.c = str;
        this.b = i;
    }
}
