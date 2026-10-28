package defpackage;

import android.view.View;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class o8 implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ q8 b;
    public final /* synthetic */ String c;

    public /* synthetic */ o8(q8 q8Var, String str, int i) {
        this.a = i;
        this.b = q8Var;
        this.c = str;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.a;
        String str = this.c;
        q8 q8Var = this.b;
        switch (i) {
            case 0:
                q8Var.d.b(str);
                break;
            case 1:
                q8Var.d.b("run:".concat(str));
                break;
            default:
                q8Var.d.b(str);
                break;
        }
    }
}
