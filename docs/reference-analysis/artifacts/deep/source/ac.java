package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ac implements y5 {
    public final /* synthetic */ int a;
    public final /* synthetic */ gc b;

    public /* synthetic */ ac(gc gcVar, int i) {
        this.a = i;
        this.b = gcVar;
    }

    @Override // defpackage.y5
    public final Object a() {
        int i = this.a;
        ti tiVar = ti.a;
        gc gcVar = this.b;
        switch (i) {
            case 0:
                gcVar.f.post(new cc(gcVar, 4));
                break;
            case 1:
                gcVar.f.post(new cc(gcVar, 0));
                break;
            case 2:
                gcVar.f.post(new cc(gcVar, 2));
                break;
            case 3:
                gcVar.f.post(new cc(gcVar, 3));
                break;
            default:
                if (gcVar.l) {
                    gc.b(gcVar);
                    wa waVar = gcVar.x;
                    if (waVar != null) {
                        waVar.a();
                    }
                }
                break;
        }
        return tiVar;
    }
}
