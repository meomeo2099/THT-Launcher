package defpackage;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.widget.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class v5 implements DialogInterface.OnClickListener {
    public final /* synthetic */ int a = 1;
    public final /* synthetic */ ArrayList b;
    public final /* synthetic */ MainActivity c;
    public final /* synthetic */ String d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ v5(ArrayList arrayList, MainActivity mainActivity, String str, le leVar, me meVar) {
        this.b = arrayList;
        this.c = mainActivity;
        this.d = str;
        this.e = leVar;
        this.f = meVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        t7 t7Var;
        int i2 = this.a;
        Object obj = this.f;
        Object obj2 = this.e;
        String str = this.d;
        MainActivity mainActivity = this.c;
        ArrayList arrayList = this.b;
        switch (i2) {
            case 0:
                w5.b(mainActivity, i3.C0(w5.a(mainActivity), str));
                k8.f0((Dialog) obj, (GridLayout) obj2, arrayList, mainActivity);
                mainActivity.e0();
                return;
            default:
                le leVar = (le) obj2;
                me meVar = (me) obj;
                yc ycVar = (yc) ((yc) arrayList.get(i)).b;
                int iIntValue = ((Number) ycVar.a).intValue();
                int iIntValue2 = ((Number) ycVar.b).intValue();
                HashMap map = v7.a;
                SharedPreferences sharedPreferencesB = mainActivity.B();
                sharedPreferencesB.getClass();
                r7 r7VarD = v7.d(sharedPreferencesB, str);
                int i3 = iIntValue * iIntValue2;
                ArrayList arrayList2 = new ArrayList(i3);
                for (int i4 = 0; i4 < i3; i4++) {
                    u7 u7Var = (u7) i3.w0(r7VarD.a, i4);
                    float f = i4 % iIntValue;
                    float f2 = iIntValue;
                    float f3 = f / f2;
                    float f4 = i4 / iIntValue;
                    float f5 = iIntValue2;
                    float f6 = f4 / f5;
                    float f7 = (f + 1.0f) / f2;
                    float f8 = (f4 + 1.0f) / f5;
                    String str2 = u7Var != null ? u7Var.e : null;
                    if (str2 == null) {
                        str2 = "";
                    }
                    String str3 = str2;
                    if (u7Var == null || (t7Var = u7Var.f) == null) {
                        t7Var = t7.d;
                    }
                    arrayList2.add(new u7(f3, f6, f7, f8, str3, t7Var));
                }
                f8.c(mainActivity, str, r7.a(r7VarD, arrayList2, null, 2));
                leVar.a = -1;
                Object obj3 = meVar.b;
                if (obj3 != null) {
                    ((y5) obj3).a();
                    return;
                } else {
                    k8.H0("fill");
                    throw null;
                }
        }
    }

    public /* synthetic */ v5(MainActivity mainActivity, String str, GridLayout gridLayout, ArrayList arrayList, Dialog dialog) {
        this.c = mainActivity;
        this.d = str;
        this.e = gridLayout;
        this.b = arrayList;
        this.f = dialog;
    }
}
