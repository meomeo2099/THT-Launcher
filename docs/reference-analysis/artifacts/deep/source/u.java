package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.util.Log;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class u implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ int b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;

    public /* synthetic */ u(int i, Context context, e2 e2Var) {
        this.a = 0;
        this.b = i;
        this.c = context;
        this.d = e2Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Intent launchIntentForPackage;
        Object ffVar;
        String packageName;
        String string;
        int i = 5;
        int i2 = 0;
        switch (this.a) {
            case 0:
                int i3 = this.b;
                Context context = (Context) this.c;
                e2 e2Var = (e2) this.d;
                if (i3 == k8.f) {
                    String str = e2Var.a;
                    ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(str);
                    if (componentNameUnflattenFromString == null || (launchIntentForPackage = Intent.makeMainActivity(componentNameUnflattenFromString)) == null) {
                        launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
                    }
                    if (launchIntentForPackage == null) {
                        Log.w("ReferencePanel.Autostart", "no launch intent: ".concat(str));
                    } else {
                        launchIntentForPackage.addFlags(270532608);
                        try {
                            context.startActivity(launchIntentForPackage);
                        } catch (Exception e) {
                            Intent launchIntentForPackage2 = (componentNameUnflattenFromString == null || (packageName = componentNameUnflattenFromString.getPackageName()) == null) ? null : context.getPackageManager().getLaunchIntentForPackage(packageName);
                            if (launchIntentForPackage2 == null) {
                                Log.w("ReferencePanel.Autostart", "launch failed: ".concat(str), e);
                                return;
                            }
                            try {
                                context.startActivity(launchIntentForPackage2.addFlags(268435456));
                                ffVar = ti.a;
                                break;
                            } catch (Throwable th) {
                                ffVar = new ff(th);
                            }
                            Throwable thA = gf.a(ffVar);
                            if (thA != null) {
                                Log.w("ReferencePanel.Autostart", "launch failed: ".concat(str), thA);
                                return;
                            }
                            return;
                        }
                    }
                }
                break;
            case 1:
                MainActivity mainActivity = (MainActivity) this.c;
                int i4 = this.b;
                ComponentName componentName = (ComponentName) this.d;
                int i5 = MainActivity.E0;
                if (!mainActivity.isDestroyed()) {
                    sc scVar = mainActivity.y;
                    if (scVar.b && scVar.a == i4 && !scVar.c) {
                        scVar.c = true;
                        try {
                            k8.Z(mainActivity, componentName, new c3(mainActivity, i4, 4), new ya(mainActivity, i2));
                            mainActivity.getWindow().getDecorView().postDelayed(new t0(mainActivity, i4, i), 2000L);
                        } catch (Exception e2) {
                            mainActivity.j();
                            SharedPreferences sharedPreferences = mainActivity.getSharedPreferences("navigation_private", 0);
                            Throwable cause = e2.getCause();
                            if (cause == null || (string = cause.getMessage()) == null) {
                                string = e2.toString();
                            }
                            sharedPreferences.edit().putString("external_launch_diagnostics", th.I(8000, "External fullscreen: " + string)).apply();
                            mainActivity.v0(si.a("could_not_open_navigation_see_window_diagnostics", new Object[0]));
                            return;
                        }
                        break;
                    }
                }
                break;
            case 2:
                nb nbVar = (nb) this.c;
                Parcel parcelB = nbVar.b(19, new t8(this.b, (String) this.d));
                if (parcelB != null) {
                    parcelB.recycle();
                }
                nbVar.d.postDelayed(new kb(nbVar, i), 600L);
                break;
            case 3:
                gc gcVar = (gc) this.c;
                int i6 = this.b;
                Bitmap bitmap = (Bitmap) this.d;
                if (gcVar.l && gcVar.r == i6) {
                    gcVar.p = bitmap;
                    gcVar.d();
                    break;
                }
                break;
            case 4:
                Context context2 = (Context) this.c;
                String str2 = (String) this.d;
                int i7 = this.b;
                fi fiVar = pd.a;
                pd.c(context2, str2, i7 - 1);
                break;
            case 5:
                fe feVar = (fe) this.c;
                String str3 = (String) this.d;
                int i8 = this.b;
                if (feVar.g()) {
                    feVar.k(i8, str3);
                }
                break;
            default:
                nj njVar = (nj) this.c;
                int i9 = this.b;
                Object obj = this.d;
                if (njVar.n && njVar.o == i9) {
                    if (obj != null) {
                        njVar.l = obj;
                        njVar.c.removeCallbacks(njVar.t);
                        njVar.c.post(njVar.t);
                    } else if (njVar.g() == null) {
                        njVar.b.b(new bj(null, null, null, null, si.a("no_access_to_vehicle_data", new Object[0]), null, 67107839));
                    }
                    break;
                }
                break;
        }
    }

    public /* synthetic */ u(Object obj, int i, Object obj2, int i2) {
        this.a = i2;
        this.c = obj;
        this.b = i;
        this.d = obj2;
    }

    public /* synthetic */ u(Object obj, String str, int i, int i2) {
        this.a = i2;
        this.c = obj;
        this.d = str;
        this.b = i;
    }
}
