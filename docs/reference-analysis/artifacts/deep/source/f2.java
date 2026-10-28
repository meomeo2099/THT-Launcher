package defpackage;

import android.content.DialogInterface;
import java.io.File;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class f2 implements DialogInterface.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ f2(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        int i2 = this.a;
        Object obj = this.b;
        switch (i2) {
            case 0:
                h2 h2Var = (h2) obj;
                Object obj2 = h2Var.h.get(i);
                obj2.getClass();
                h2Var.a((File) obj2);
                break;
            case 1:
                ((h2) obj).d.a();
                break;
            case 2:
                ((h2) obj).b();
                break;
            default:
                ((y5) ((yc) ((ArrayList) obj).get(i)).b).a();
                break;
        }
    }
}
