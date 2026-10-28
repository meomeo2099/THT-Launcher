package defpackage;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import java.io.File;
import java.util.List;
import org.reference.panel.AutomationService;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class p0 implements Runnable {
    public final /* synthetic */ int a = 0;
    public final /* synthetic */ MainActivity b;
    public final /* synthetic */ boolean c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ p0(MainActivity mainActivity, Handler handler, k0 k0Var, boolean z, yi yiVar) {
        this.b = mainActivity;
        this.d = handler;
        this.e = k0Var;
        this.c = z;
        this.f = yiVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        final Object ffVar;
        Object objValueOf;
        String strA;
        String strA2;
        int i = 1;
        int i2 = 0;
        switch (this.a) {
            case 0:
                final MainActivity mainActivity = this.b;
                final Handler handler = (Handler) this.d;
                k0 k0Var = (k0) this.e;
                final boolean z = this.c;
                final yi yiVar = (yi) this.f;
                final me meVar = new me(0);
                try {
                    meVar.b = l0.g(mainActivity, k0Var, new q0(handler, yiVar, i2));
                    handler.post(new n0(yiVar, i));
                    if (z) {
                        objValueOf = Boolean.valueOf(handler.post(new r0(mainActivity, meVar, yiVar, i2)));
                    } else {
                        Object obj = meVar.b;
                        obj.getClass();
                        l0.r(mainActivity, (File) obj);
                        objValueOf = ti.a;
                    }
                    ffVar = objValueOf;
                } catch (Throwable th) {
                    ffVar = new ff(th);
                }
                handler.post(new Runnable() { // from class: s0
                    /* JADX WARN: Code duplicated, block: B:24:0x006e  */
                    @Override // java.lang.Runnable
                    public final void run() {
                        String strA3;
                        Object ffVar2;
                        Object obj2 = ffVar;
                        Throwable thA = gf.a(obj2);
                        yi yiVar2 = yiVar;
                        boolean z2 = z;
                        int i3 = 0;
                        if (thA != null) {
                            Log.w("ReferencePanel.Update", "update failed", thA);
                            boolean z3 = thA instanceof SecurityException;
                            if (z3) {
                                strA3 = si.a("update_bad_file", new Object[0]);
                            } else {
                                List list = si.a;
                                String message = thA.getMessage();
                                if (message == null) {
                                    message = thA.getClass().getSimpleName();
                                }
                                strA3 = si.a("update_failed_0", message);
                            }
                            File file = (File) meVar.b;
                            MainActivity mainActivity2 = mainActivity;
                            if (file == null || !file.isFile() || z3 || z2) {
                                file = null;
                            } else {
                                try {
                                    ffVar2 = mainActivity2.getPackageManager().getActivityInfo(new ComponentName("com.teto.inputservice", "com.teto.inputservice.autoboot.ApkInstallActivity"), 0);
                                } catch (Throwable th2) {
                                    ffVar2 = new ff(th2);
                                }
                                if (ffVar2 instanceof ff) {
                                    file = null;
                                }
                            }
                            yiVar2.b(strA3, file != null ? new yc(si.a("update_via_freetuga", new Object[0]), new u0(mainActivity2, file, yiVar2, i3)) : null);
                        }
                        if ((obj2 instanceof ff) || z2) {
                            return;
                        }
                        handler.postDelayed(new n0(yiVar2, 0), 60000L);
                    }
                });
                break;
            default:
                MainActivity mainActivity2 = this.b;
                Object obj2 = this.d;
                boolean z2 = this.c;
                Uri uri = (Uri) this.e;
                String str = (String) this.f;
                mainActivity2.h = false;
                if (!mainActivity2.isDestroyed()) {
                    boolean z3 = obj2 instanceof ff;
                    if (z3) {
                        AlertDialog.Builder title = new AlertDialog.Builder(mainActivity2).setTitle(si.a("backup", new Object[0]));
                        Throwable thA = gf.a(obj2);
                        if (thA == null || (strA = thA.getMessage()) == null) {
                            strA = si.a("could_not_process_the_backup", new Object[0]);
                        }
                        AlertDialog.Builder positiveButton = title.setMessage(strA).setPositiveButton(si.a("ok", new Object[0]), (DialogInterface.OnClickListener) null);
                        positiveButton.getClass();
                        k8.w0(positiveButton);
                    } else {
                        mainActivity2.J();
                        if (z2) {
                            mainActivity2.c = false;
                            mainActivity2.f = false;
                            mainActivity2.s = null;
                            mainActivity2.t = 0;
                            mainActivity2.w0 = null;
                            mainActivity2.B().edit().putBoolean("onboarded", true).apply();
                            j0.a(mainActivity2);
                            int i3 = AutomationService.c;
                            k8.F0(mainActivity2);
                        }
                        if (z2) {
                            strA2 = si.a("settings_restored", new Object[0]);
                        } else {
                            List list = si.a;
                            strA2 = si.a("backup_saved0", k8.e(uri.getScheme(), "file") ? ": " + uri.getPath() : "");
                        }
                        mainActivity2.v0(strA2);
                    }
                    if (!z2 || z3 || str.equals(si.b.a)) {
                        mainActivity2.e0();
                    } else {
                        mainActivity2.recreate();
                    }
                }
                break;
        }
    }

    public /* synthetic */ p0(MainActivity mainActivity, Object obj, boolean z, Uri uri, String str) {
        this.b = mainActivity;
        this.d = obj;
        this.c = z;
        this.e = uri;
        this.f = str;
    }
}
