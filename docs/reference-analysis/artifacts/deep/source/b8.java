package defpackage;

import android.content.SharedPreferences;
import android.widget.FrameLayout;
import java.io.Serializable;
import java.util.HashMap;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b8 implements y5 {
    public final /* synthetic */ int a = 1;
    public final /* synthetic */ MainActivity b;
    public final /* synthetic */ me c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Serializable e;
    public final /* synthetic */ Object f;

    public /* synthetic */ b8(je jeVar, me meVar, kh khVar, FrameLayout frameLayout, MainActivity mainActivity) {
        this.d = frameLayout;
        this.b = mainActivity;
        this.c = meVar;
        this.e = jeVar;
        this.f = khVar;
    }

    @Override // defpackage.y5
    public final Object a() {
        int i = this.a;
        ti tiVar = ti.a;
        Object obj = this.f;
        Serializable serializable = this.e;
        me meVar = this.c;
        MainActivity mainActivity = this.b;
        Object obj2 = this.d;
        switch (i) {
            case 0:
                String str = (String) obj2;
                t7 t7Var = (t7) obj;
                HashMap map = v7.a;
                SharedPreferences sharedPreferencesB = mainActivity.B();
                sharedPreferencesB.getClass();
                r7 r7VarD = v7.d(sharedPreferencesB, str);
                int i2 = ((le) serializable).a;
                f8.c(mainActivity, str, r7VarD.b(i2, u7.b((u7) r7VarD.a.get(i2), 0.0f, 0.0f, 0.0f, 0.0f, null, t7Var, 31)));
                Object obj3 = meVar.b;
                if (obj3 != null) {
                    ((y5) obj3).a();
                    return tiVar;
                }
                k8.H0("fill");
                throw null;
            default:
                pi.T((je) serializable, meVar, (kh) obj, (FrameLayout) obj2, mainActivity);
                return tiVar;
        }
    }

    public /* synthetic */ b8(MainActivity mainActivity, String str, me meVar, le leVar, t7 t7Var) {
        this.b = mainActivity;
        this.d = str;
        this.c = meVar;
        this.e = leVar;
        this.f = t7Var;
    }
}
