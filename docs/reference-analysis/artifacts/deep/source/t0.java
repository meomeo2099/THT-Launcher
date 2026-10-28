package defpackage;

import android.content.SharedPreferences;
import android.os.Parcel;
import org.reference.panel.BootPlaybackService;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class t0 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ int b;
    public final /* synthetic */ Object c;

    public /* synthetic */ t0(int i, Object obj, int i2) {
        this.a = i2;
        this.b = i;
        this.c = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = 0;
        switch (this.a) {
            case 0:
                ((yi) this.c).g(this.b, false);
                break;
            case 1:
                int i2 = this.b;
                BootPlaybackService bootPlaybackService = (BootPlaybackService) this.c;
                int i3 = i2 - 1;
                hd hdVar = bootPlaybackService.e;
                if (hdVar != null) {
                    hdVar.a(bootPlaybackService.d, new i2(i3, bootPlaybackService));
                }
                break;
            case 2:
                ((v8) this.c).scrollTo(this.b, 0);
                break;
            case 3:
                int i4 = this.b;
                k9 k9Var = (k9) this.c;
                if (i4 == k9Var.j && k9Var.i != null) {
                    k9Var.i = null;
                    k9Var.a();
                    k9Var.invalidate();
                    break;
                }
                break;
            case 4:
                int i5 = this.b;
                p9 p9Var = (p9) this.c;
                if (i5 == p9Var.i) {
                    p9Var.h = null;
                    p9Var.invalidate();
                }
                break;
            case 5:
                MainActivity mainActivity = (MainActivity) this.c;
                int i6 = this.b;
                sc scVar = mainActivity.y;
                if (scVar.b && scVar.a == i6 && scVar.c && !scVar.d) {
                    mainActivity.j();
                    SharedPreferences sharedPreferences = mainActivity.getSharedPreferences("navigation_private", 0);
                    String string = sharedPreferences.getString("external_launch_diagnostics", "");
                    sharedPreferences.edit().putString("external_launch_diagnostics", th.I(8000, (string != null ? string : "").concat("\nNo foreground transition after 2 seconds"))).apply();
                    mainActivity.v0(si.a("navigation_did_not_open_on_the_main", new Object[0]));
                    break;
                }
                break;
            case 6:
                nb nbVar = (nb) this.c;
                Parcel parcelB = nbVar.b(this.b, new w(10));
                if (parcelB != null) {
                    parcelB.recycle();
                }
                nbVar.d.postDelayed(new kb(nbVar, i), 400L);
                break;
            case 7:
                int i7 = this.b;
                hd hdVar2 = (hd) this.c;
                if (i7 == hdVar2.e) {
                    hdVar2.c();
                }
                break;
            case 8:
                ((fe) this.c).j(this.b);
                break;
            case 9:
                int i8 = this.b;
                y5 y5Var = (y5) this.c;
                if (i8 == eh.c) {
                    y5Var.a();
                }
                break;
            default:
                nj njVar = (nj) this.c;
                int i9 = this.b;
                if (njVar.n && njVar.o == i9 && njVar.l == null && njVar.g() == null) {
                    njVar.b.b(new bj(null, null, null, null, si.a("vehicle_not_connected", new Object[0]), null, 67107839));
                    break;
                }
                break;
        }
    }

    public /* synthetic */ t0(Object obj, int i, int i2) {
        this.a = i2;
        this.c = obj;
        this.b = i;
    }
}
