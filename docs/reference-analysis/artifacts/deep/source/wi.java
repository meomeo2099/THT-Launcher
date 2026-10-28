package defpackage;

import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class wi implements y5 {
    public final /* synthetic */ int a;
    public final /* synthetic */ yi b;

    public /* synthetic */ wi(yi yiVar, int i) {
        this.a = i;
        this.b = yiVar;
    }

    @Override // defpackage.y5
    public final Object a() {
        int i = this.a;
        ti tiVar = ti.a;
        yi yiVar = this.b;
        switch (i) {
            case 0:
                MainActivity mainActivity = yiVar.a;
                mainActivity.getSharedPreferences("panel", 0).edit().putInt("update_skip", yiVar.b.a).apply();
                yiVar.g.dismiss();
                break;
            case 1:
                yiVar.g.dismiss();
                break;
            case 2:
                yiVar.g.dismiss();
                break;
            default:
                yiVar.g.dismiss();
                break;
        }
        return tiVar;
    }
}
