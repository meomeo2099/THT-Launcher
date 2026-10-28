package defpackage;

import android.os.Parcel;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class kb implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ nb b;

    public /* synthetic */ kb(nb nbVar, int i) {
        this.a = i;
        this.b = nbVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.a;
        nb nbVar = this.b;
        switch (i) {
            case 0:
                nbVar.c();
                break;
            case 1:
                nbVar.h = false;
                nbVar.d.removeCallbacks(nbVar.k);
                if (nbVar.g) {
                    try {
                        nbVar.b.unbindService(nbVar.j);
                        break;
                    } catch (Throwable unused) {
                    }
                }
                nbVar.g = false;
                nbVar.e = null;
                nbVar.e(null);
                break;
            case 2:
                Parcel parcelB = nbVar.b(20, new w(11));
                if (parcelB != null) {
                    parcelB.recycle();
                }
                nbVar.d.postDelayed(new kb(nbVar, 4), 800L);
                break;
            case 3:
                if (!nbVar.h) {
                    nbVar.h = true;
                    nbVar.a();
                    nbVar.d.post(nbVar.k);
                    break;
                }
                break;
            case 4:
                nbVar.c();
                break;
            default:
                nbVar.c();
                break;
        }
    }
}
