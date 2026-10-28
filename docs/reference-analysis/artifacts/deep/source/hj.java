package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class hj implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ nj b;

    public /* synthetic */ hj(nj njVar, int i) {
        this.a = i;
        this.b = njVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.a;
        nj njVar = this.b;
        switch (i) {
            case 0:
                if (njVar.n) {
                    njVar.j();
                }
                break;
            case 1:
                if (njVar.n) {
                    njVar.j();
                }
                break;
            case 2:
                if (njVar.n) {
                    njVar.j();
                }
                break;
            default:
                if (njVar.n) {
                    njVar.j();
                }
                break;
        }
    }
}
