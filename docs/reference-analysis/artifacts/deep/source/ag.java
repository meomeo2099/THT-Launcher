package defpackage;

import android.net.Uri;
import java.util.List;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ag implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ MainActivity b;
    public final /* synthetic */ boolean c;
    public final /* synthetic */ Uri d;

    public /* synthetic */ ag(MainActivity mainActivity, boolean z, Uri uri, int i) {
        this.a = i;
        this.b = mainActivity;
        this.c = z;
        this.d = uri;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object ffVar;
        Object ffVar2;
        switch (this.a) {
            case 0:
                MainActivity mainActivity = this.b;
                boolean z = this.c;
                Uri uri = this.d;
                try {
                    if (z) {
                        ffVar = i7.d(mainActivity, uri);
                    } else {
                        i7.c(mainActivity, uri);
                        ffVar = null;
                    }
                } catch (Throwable th) {
                    ffVar = new ff(th);
                }
                mainActivity.runOnUiThread(new r0(mainActivity, ffVar, uri, 3));
                break;
            default:
                MainActivity mainActivity2 = this.b;
                boolean z2 = this.c;
                Uri uri2 = this.d;
                List list = si.a;
                String str = si.b.a;
                try {
                    u2 u2Var = new u2(mainActivity2);
                    if (z2) {
                        u2Var.e(uri2);
                    } else {
                        u2Var.c(uri2);
                    }
                    ffVar2 = ti.a;
                } catch (Throwable th2) {
                    ffVar2 = new ff(th2);
                }
                mainActivity2.runOnUiThread(new p0(mainActivity2, ffVar2, z2, uri2, str));
                break;
        }
    }
}
