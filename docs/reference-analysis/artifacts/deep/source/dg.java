package defpackage;

import android.content.DialogInterface;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class dg implements DialogInterface.OnClickListener {
    public final /* synthetic */ int a = 0;
    public final /* synthetic */ MainActivity b;
    public final /* synthetic */ ArrayList c;

    public /* synthetic */ dg(ArrayList arrayList, MainActivity mainActivity) {
        this.c = arrayList;
        this.b = mainActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) throws IllegalAccessException, IOException, InvocationTargetException {
        switch (this.a) {
            case 0:
                ArrayList arrayList = this.c;
                MainActivity mainActivity = this.b;
                String str = (String) arrayList.get(i);
                List list = si.a;
                if (!k8.e(str, si.b.a)) {
                    List list2 = j0.a;
                    str.getClass();
                    if (!si.a.contains(str)) {
                        w.a("Failed requirement.");
                    } else {
                        if (j0.c().contains(str)) {
                            j0.d(mainActivity, str);
                            Locale.setDefault(si.b.b);
                            mainActivity.getSharedPreferences("panel", 0).edit().putString("app_language", str).apply();
                        }
                        mainActivity.s = null;
                        mainActivity.t = 0;
                        mainActivity.recreate();
                    }
                }
                dialogInterface.dismiss();
                break;
            default:
                MainActivity mainActivity2 = this.b;
                mainActivity2.B().edit().putString("vehicle", ((zi) this.c.get(i)).a).apply();
                mainActivity2.J();
                mainActivity2.e0();
                break;
        }
    }

    public /* synthetic */ dg(MainActivity mainActivity, ArrayList arrayList) {
        this.b = mainActivity;
        this.c = arrayList;
    }
}
