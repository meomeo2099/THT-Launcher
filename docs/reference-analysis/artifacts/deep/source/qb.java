package defpackage;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import java.util.ArrayList;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class qb implements View.OnClickListener {
    public final /* synthetic */ int a = 0;
    public final /* synthetic */ me b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;

    public /* synthetic */ qb(me meVar, Dialog dialog, MainActivity mainActivity) {
        this.b = meVar;
        this.c = dialog;
        this.d = mainActivity;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x003f  */
    /* JADX WARN: Code duplicated, block: B:30:0x006c  */
    /* JADX WARN: Code duplicated, block: B:31:0x0070  */
    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        String str;
        Intent intent = null;
        switch (this.a) {
            case 0:
                String str2 = (String) this.c;
                me meVar = this.b;
                j6 j6Var = (j6) this.d;
                Object obj = meVar.b;
                if (obj == null) {
                    k8.H0("dialog");
                    throw null;
                }
                ((Dialog) obj).dismiss();
                j6Var.b(str2);
                return;
            default:
                me meVar2 = this.b;
                Dialog dialog = (Dialog) this.c;
                MainActivity mainActivity = (MainActivity) this.d;
                Object obj2 = meVar2.b;
                if (obj2 == null) {
                    k8.H0("scan");
                    throw null;
                }
                qd qdVar = (qd) obj2;
                if (qdVar.k) {
                    qdVar.a(true);
                }
                dialog.dismiss();
                lb lbVar = mainActivity.y().u.f;
                if (lbVar == null) {
                    str = null;
                } else {
                    if (!lbVar.c()) {
                        lbVar = null;
                    }
                    if (lbVar != null) {
                        str = lbVar.f;
                        if (th.A(str)) {
                            str = null;
                        }
                    } else {
                        str = null;
                    }
                }
                ArrayList arrayListD0 = i3.D0(str != null ? pi.J(str) : u4.a, xd.a);
                int size = arrayListD0.size();
                int i = 0;
                while (i < size) {
                    Object obj3 = arrayListD0.get(i);
                    i++;
                    Intent launchIntentForPackage = mainActivity.getPackageManager().getLaunchIntentForPackage((String) obj3);
                    if (launchIntentForPackage != null) {
                        intent = launchIntentForPackage;
                        if (intent != null) {
                            mainActivity.Q(intent);
                            return;
                        } else {
                            mainActivity.v0(si.a("radio_app_missing", new Object[0]));
                            return;
                        }
                    }
                }
                if (intent != null) {
                    mainActivity.Q(intent);
                    return;
                } else {
                    mainActivity.v0(si.a("radio_app_missing", new Object[0]));
                    return;
                }
        }
    }

    public /* synthetic */ qb(String str, me meVar, j6 j6Var) {
        this.c = str;
        this.b = meVar;
        this.d = j6Var;
    }
}
