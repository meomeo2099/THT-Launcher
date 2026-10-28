package defpackage;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class sa implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ MainActivity b;

    public /* synthetic */ sa(MainActivity mainActivity, int i) {
        this.a = i;
        this.b = mainActivity;
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0067  */
    @Override // java.lang.Runnable
    public final void run() {
        ch chVarK;
        Object ffVar;
        Object ffVar2;
        int i = this.a;
        MainActivity mainActivity = this.b;
        switch (i) {
            case 0:
                int i2 = MainActivity.E0;
                if (!mainActivity.B().getBoolean("saver_on", false) || mainActivity.d0 != null || mainActivity.c || mainActivity.f || mainActivity.e0 != null) {
                    mainActivity.h0();
                } else if (!mainActivity.B().getBoolean("saver_parked", true)) {
                    mainActivity.g();
                } else {
                    mainActivity.b.execute(new sa(mainActivity, 4));
                }
                break;
            case 1:
                int i3 = MainActivity.E0;
                if (!mainActivity.isFinishing() && mainActivity.hasWindowFocus() && (chVarK = eh.k(true)) != null) {
                    Rect rect = chVarK.b;
                    if (rect.left < 0 || rect.width() < 32) {
                        eh.a();
                    }
                    break;
                }
                break;
            case 2:
                int i4 = MainActivity.E0;
                try {
                    ffVar = ((kh) mainActivity.R.a()).d();
                } catch (Throwable th) {
                    ffVar = new ff(th);
                }
                mainActivity.runOnUiThread(new o(mainActivity, (di) (ffVar instanceof ff ? null : ffVar), 2));
                break;
            case 3:
                int i5 = MainActivity.E0;
                mainActivity.l0 = SystemClock.uptimeMillis();
                if (!mainActivity.hasWindowFocus()) {
                    mainActivity.l0 = 0L;
                    new Handler(Looper.getMainLooper()).postDelayed(new sa(mainActivity, 6), 300L);
                }
                break;
            case 4:
                int i6 = MainActivity.E0;
                try {
                    r4 r4VarP = r4.C.p(mainActivity);
                    if (r4VarP == null) {
                        ffVar2 = null;
                    } else {
                        if (!r4VarP.m()) {
                            r4VarP = null;
                        }
                        if (r4VarP != null) {
                            ffVar2 = r4VarP.v(1048832);
                        } else {
                            ffVar2 = null;
                        }
                    }
                } catch (Throwable th2) {
                    ffVar2 = new ff(th2);
                }
                mainActivity.runOnUiThread(new o((Float) (ffVar2 instanceof ff ? null : ffVar2), mainActivity, 3));
                break;
            case 5:
                int i7 = MainActivity.E0;
                try {
                    mainActivity.startActivity(new Intent(mainActivity, (Class<?>) MainActivity.class).addFlags(537001984));
                } catch (Throwable unused) {
                    return;
                }
                break;
            default:
                int i8 = MainActivity.E0;
                int i9 = c7.a;
                v1 v1Var = new v1(mainActivity, 24);
                IBinder iBinder = k8.a;
                if (iBinder != null && iBinder.isBinderAlive()) {
                    new Thread(new o(v1Var, iBinder, 1)).start();
                } else {
                    k8.i0(mainActivity);
                    v1Var.b(Boolean.FALSE);
                }
                break;
        }
    }
}
