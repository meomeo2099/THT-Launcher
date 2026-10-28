package defpackage;

import android.content.SharedPreferences;
import java.util.HashMap;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c1 implements y5 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ String c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ int e;

    public /* synthetic */ c1(Object obj, String str, Object obj2, int i, int i2) {
        this.a = i2;
        this.b = obj;
        this.c = str;
        this.d = obj2;
        this.e = i;
    }

    @Override // defpackage.y5
    public final Object a() {
        int i = this.a;
        ti tiVar = ti.a;
        int i2 = this.e;
        Object obj = this.d;
        String str = this.c;
        Object obj2 = this.b;
        switch (i) {
            case 0:
                r4 r4Var = (r4) obj;
                ((s1) obj2).b(str, new h1(r4Var, i2, 2), new h1(r4Var, i2, 3));
                return tiVar;
            case 1:
                r4 r4Var2 = (r4) obj;
                ((s1) obj2).b(str + " off", new h1(r4Var2, i2, 4), new h1(r4Var2, i2, 5));
                return tiVar;
            default:
                MainActivity mainActivity = (MainActivity) obj2;
                HashMap map = v7.a;
                SharedPreferences sharedPreferencesB = mainActivity.B();
                sharedPreferencesB.getClass();
                r7 r7VarD = v7.d(sharedPreferencesB, str);
                f8.c(mainActivity, str, r7VarD.b(i2, u7.b((u7) r7VarD.a.get(i2), 0.0f, 0.0f, 0.0f, 0.0f, "", null, 47)));
                Object obj3 = ((me) obj).b;
                if (obj3 != null) {
                    ((y5) obj3).a();
                    return tiVar;
                }
                k8.H0("fill");
                throw null;
        }
    }
}
