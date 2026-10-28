package defpackage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.Location;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import java.io.File;
import java.io.Serializable;
import java.util.List;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class r0 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;

    public /* synthetic */ r0(nj njVar, j6 j6Var, me meVar) {
        this.a = 5;
        this.b = njVar;
        this.d = j6Var;
        this.c = meVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object ffVar;
        String strA;
        switch (this.a) {
            case 0:
                MainActivity mainActivity = (MainActivity) this.b;
                me meVar = (me) this.c;
                yi yiVar = (yi) this.d;
                try {
                    Object obj = meVar.b;
                    obj.getClass();
                    l0.s(mainActivity, (File) obj);
                    ffVar = ti.a;
                } catch (Throwable th) {
                    ffVar = new ff(th);
                }
                if (!(ffVar instanceof ff)) {
                    yiVar.c();
                }
                Throwable thA = gf.a(ffVar);
                if (thA != null) {
                    Log.w("ReferencePanel.Update", "FreeTUGA handover failed", thA);
                    String message = thA.getMessage();
                    if (message == null) {
                        message = thA.getClass().getSimpleName();
                    }
                    yiVar.b(message, null);
                }
                break;
            case 1:
                f8.d((WindowManager.LayoutParams) this.b, (q7) this.c, (WindowManager) this.d, false);
                break;
            case 2:
                qd qdVar = (qd) this.b;
                String str = (String) this.c;
                lb lbVar = (lb) this.d;
                if (qdVar.k) {
                    lb lbVarS = qdVar.d.s();
                    if (lbVarS != null) {
                        if (qd.b(lbVarS).equals(str)) {
                            qdVar.c(lbVarS);
                        } else {
                            qdVar.c(lbVar);
                        }
                        qdVar.b.b(Integer.valueOf(qdVar.f.size()));
                        qdVar.d();
                    } else {
                        qdVar.a(false);
                    }
                }
                break;
            case 3:
                MainActivity mainActivity2 = (MainActivity) this.b;
                Object obj2 = this.c;
                Uri uri = (Uri) this.d;
                mainActivity2.h = false;
                if (!mainActivity2.isDestroyed()) {
                    if (!(obj2 instanceof ff)) {
                        d7 d7Var = (d7) obj2;
                        if (d7Var != null) {
                            me meVar2 = i7.a;
                            i7.g(mainActivity2, d7Var.a);
                            List list = si.a;
                            mainActivity2.v0(si.a("icon_set_added_0", d7Var.b));
                        } else {
                            mainActivity2.v0(si.a("icon_set_template_saved", new Object[0]) + (k8.e(uri.getScheme(), "file") ? ": " + uri.getPath() : ""));
                        }
                    }
                    Throwable thA2 = gf.a(obj2);
                    if (thA2 != null) {
                        AlertDialog.Builder title = new AlertDialog.Builder(mainActivity2).setTitle(si.a("icons", new Object[0]));
                        String message2 = thA2.getMessage();
                        if (message2 == null) {
                            message2 = si.a("icon_set_file_unavailable", new Object[0]);
                        }
                        AlertDialog.Builder positiveButton = title.setMessage(message2).setPositiveButton(si.a("ok", new Object[0]), (DialogInterface.OnClickListener) null);
                        positiveButton.getClass();
                        mainActivity2.p0(positiveButton);
                    }
                    mainActivity2.e0();
                    break;
                }
                break;
            case 4:
                MainActivity mainActivity3 = (MainActivity) this.b;
                Object obj3 = this.c;
                y5 y5Var = (y5) this.d;
                mainActivity3.h = false;
                if (!mainActivity3.isDestroyed()) {
                    boolean z = obj3 instanceof ff;
                    if (z) {
                        Throwable thA3 = gf.a(obj3);
                        if (thA3 == null || (strA = thA3.getMessage()) == null) {
                            strA = si.a("could_not_load_image", new Object[0]);
                        }
                        mainActivity3.v0(strA);
                    } else {
                        mainActivity3.J();
                        mainActivity3.v0(si.a("image_saved_on_the_device", new Object[0]));
                    }
                    mainActivity3.e0();
                    if (!z && y5Var != null) {
                        y5Var.a();
                        break;
                    }
                }
                break;
            case 5:
                nj njVar = (nj) this.b;
                j6 j6Var = (j6) this.d;
                me meVar3 = (me) this.c;
                njVar.A = 0L;
                j6Var.b((njVar.n && k8.e(njVar.f.r, meVar3.b)) ? null : si.a("the_vehicle_did_not_confirm_the_command", new Object[0]));
                break;
            case 6:
                j6 j6Var2 = (j6) this.b;
                String str2 = (String) this.c;
                nj njVar2 = (nj) this.d;
                j6Var2.b(str2);
                if (njVar2.n) {
                    njVar2.b.b(njVar2.a(njVar2.f));
                    Handler handler = njVar2.c;
                    handler.postDelayed(new hj(njVar2, 1), 400L);
                    handler.postDelayed(new hj(njVar2, 2), 1500L);
                    handler.postDelayed(new hj(njVar2, 3), 4000L);
                }
                break;
            default:
                vj vjVar = (vj) this.b;
                Serializable serializable = (Serializable) this.c;
                Location location = (Location) this.d;
                vjVar.h = false;
                if (!(serializable instanceof ff)) {
                    yc ycVar = (yc) serializable;
                    float fFloatValue = ((Number) ycVar.a).floatValue();
                    String str3 = (String) ycVar.b;
                    wj wjVar = new wj(Float.valueOf(fFloatValue), str3, System.currentTimeMillis(), 8);
                    vjVar.c.edit().putFloat("weather_temp", fFloatValue).putString("weather_symbol", str3).putLong("weather_time", wjVar.c).putFloat("weather_lat", (float) location.getLatitude()).putFloat("weather_lon", (float) location.getLongitude()).apply();
                    vjVar.d(wjVar);
                }
                Throwable thA4 = gf.a(serializable);
                if (thA4 != null) {
                    Log.w("ReferencePanel.Weather", "met.no: " + thA4);
                    wj wjVar2 = vjVar.i;
                    vjVar.d(wj.a(wjVar2, wjVar2.a == null ? si.a("weather_unavailable_no_internet", new Object[0]) : null));
                }
                break;
        }
    }

    public /* synthetic */ r0(WindowManager.LayoutParams layoutParams, q7 q7Var, MainActivity mainActivity, WindowManager windowManager) {
        this.a = 1;
        this.b = layoutParams;
        this.c = q7Var;
        this.d = windowManager;
    }

    public /* synthetic */ r0(Object obj, Object obj2, Object obj3, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
        this.d = obj3;
    }
}
