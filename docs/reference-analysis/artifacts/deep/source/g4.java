package defpackage;

import android.view.View;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class g4 implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ j6 b;
    public final /* synthetic */ Object c;

    public /* synthetic */ g4(j6 j6Var, Object obj, int i) {
        this.a = i;
        this.b = j6Var;
        this.c = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.a;
        Object obj = this.c;
        j6 j6Var = this.b;
        switch (i) {
            case 0:
                j6Var.b((String) obj);
                break;
            default:
                ((ya) j6Var).b(((b9) obj).a);
                break;
        }
    }
}
