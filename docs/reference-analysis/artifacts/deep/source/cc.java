package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class cc implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ gc b;

    public /* synthetic */ cc(gc gcVar, int i) {
        this.a = i;
        this.b = gcVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        wa waVar;
        v1 v1Var;
        int i = this.a;
        gc gcVar = this.b;
        switch (i) {
            case 0:
                if (gcVar.l) {
                    gcVar.t();
                }
                break;
            case 1:
                if (gcVar.l) {
                    gcVar.d();
                }
                break;
            case 2:
                if (gcVar.l) {
                    gcVar.t();
                }
                break;
            case 3:
                if (gcVar.l && (waVar = gcVar.x) != null) {
                    waVar.a();
                    break;
                }
                break;
            default:
                if (gcVar.l && (v1Var = gcVar.C) != null) {
                    v1Var.b(si.a("the_player_did_not_start_check_the", new Object[0]));
                    break;
                }
                break;
        }
    }
}
