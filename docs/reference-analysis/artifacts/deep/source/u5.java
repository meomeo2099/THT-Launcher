package defpackage;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.view.View;
import android.widget.GridLayout;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class u5 implements View.OnClickListener {
    public final /* synthetic */ int a = 0;
    public final /* synthetic */ MainActivity b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Serializable d;
    public final /* synthetic */ Object e;

    public /* synthetic */ u5(Dialog dialog, GridLayout gridLayout, ArrayList arrayList, MainActivity mainActivity) {
        this.b = mainActivity;
        this.c = gridLayout;
        this.d = arrayList;
        this.e = dialog;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        List listJ0;
        int i = this.a;
        Object obj = this.e;
        Serializable serializable = this.d;
        Object obj2 = this.c;
        switch (i) {
            case 0:
                GridLayout gridLayout = (GridLayout) obj2;
                ArrayList arrayList = (ArrayList) serializable;
                Dialog dialog = (Dialog) obj;
                int i2 = 0;
                String strA = si.a("folder_add", new Object[0]);
                MainActivity mainActivity = this.b;
                List listU = k8.U(mainActivity);
                ArrayList arrayList2 = new ArrayList();
                for (Object obj3 : listU) {
                    if (!k8.e((String) obj3, "folder")) {
                        arrayList2.add(obj3);
                    }
                }
                Set setL0 = i3.L0(w5.a(mainActivity));
                if (setL0.isEmpty()) {
                    listJ0 = i3.J0(arrayList2);
                } else {
                    ArrayList arrayList3 = new ArrayList();
                    int size = arrayList2.size();
                    while (i2 < size) {
                        Object obj4 = arrayList2.get(i2);
                        i2++;
                        if (!setL0.contains(obj4)) {
                            arrayList3.add(obj4);
                        }
                    }
                    listJ0 = arrayList3;
                }
                k8.a(mainActivity, strA, null, listJ0, null, null, null, new b3(dialog, gridLayout, arrayList, mainActivity), 120);
                break;
            default:
                ComponentName componentName = (ComponentName) obj2;
                String str = (String) serializable;
                String str2 = (String) obj;
                MainActivity mainActivity2 = this.b;
                if (componentName == null || str == null) {
                    k8.A(str2, mainActivity2);
                } else {
                    Intent intentMakeMainActivity = Intent.makeMainActivity(componentName);
                    intentMakeMainActivity.getClass();
                    mainActivity2.Q(intentMakeMainActivity);
                }
                break;
        }
    }

    public /* synthetic */ u5(ComponentName componentName, String str, MainActivity mainActivity, String str2) {
        this.c = componentName;
        this.d = str;
        this.b = mainActivity;
        this.e = str2;
    }
}
