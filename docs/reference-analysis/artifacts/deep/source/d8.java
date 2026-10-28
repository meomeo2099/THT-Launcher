package defpackage;

import android.content.SharedPreferences;
import java.util.HashMap;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class d8 implements j6 {
    public final /* synthetic */ int a = 0;
    public final /* synthetic */ int b;
    public final /* synthetic */ String c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Object e;

    public /* synthetic */ d8(int i, me meVar, String str, MainActivity mainActivity) {
        this.d = mainActivity;
        this.c = str;
        this.e = meVar;
        this.b = i;
    }

    @Override // defpackage.j6
    public final Object b(Object obj) {
        int i;
        int i2 = this.a;
        String str = this.c;
        Object obj2 = this.e;
        int i3 = this.b;
        Object obj3 = this.d;
        switch (i2) {
            case 0:
                MainActivity mainActivity = (MainActivity) obj3;
                String str2 = (String) obj;
                str2.getClass();
                HashMap map = v7.a;
                SharedPreferences sharedPreferencesB = mainActivity.B();
                sharedPreferencesB.getClass();
                r7 r7VarD = v7.d(sharedPreferencesB, str);
                f8.c(mainActivity, str, r7VarD.b(i3, u7.b((u7) r7VarD.a.get(i3), 0.0f, 0.0f, 0.0f, 0.0f, str2, null, 47)));
                Object obj4 = ((me) obj2).b;
                if (obj4 != null) {
                    ((y5) obj4).a();
                    return ti.a;
                }
                k8.H0("fill");
                throw null;
            default:
                rf rfVar = (rf) obj3;
                nj njVar = (nj) obj2;
                r4 r4Var = (r4) obj;
                r4Var.getClass();
                int i4 = rfVar.a;
                int i5 = rfVar.b;
                if (i3 <= 0) {
                    i = 0;
                } else {
                    int iA = rfVar.a();
                    if (i3 <= iA) {
                        iA = i3;
                    }
                    i = iA + i4;
                }
                boolean zX = r4Var.x(i4, i5, i);
                if (zX) {
                    njVar.d("seat:".concat(str), Integer.valueOf(i3), 6000L);
                }
                return Boolean.valueOf(zX);
        }
    }

    public /* synthetic */ d8(rf rfVar, int i, nj njVar, String str) {
        this.d = rfVar;
        this.b = i;
        this.e = njVar;
        this.c = str;
    }
}
