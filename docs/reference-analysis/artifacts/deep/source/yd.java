package defpackage;

import android.os.Parcel;
import android.os.SystemClock;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class yd implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ Object c;

    public /* synthetic */ yd(Object obj, boolean z, int i) {
        this.a = i;
        this.c = obj;
        this.b = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                fe feVar = (fe) this.c;
                final boolean z = this.b;
                if (feVar.g()) {
                    Parcel parcelB = feVar.b(5, new j6() { // from class: be
                        @Override // defpackage.j6
                        public final Object b(Object obj) {
                            Parcel parcel = (Parcel) obj;
                            parcel.getClass();
                            parcel.writeInt(!z ? 1 : 0);
                            return ti.a;
                        }
                    });
                    if (parcelB != null) {
                        parcelB.recycle();
                    }
                    feVar.e();
                }
                break;
            default:
                ni niVar = (ni) this.c;
                boolean z2 = this.b;
                niVar.f = false;
                niVar.e.removeCallbacks(niVar.L);
                if (niVar.g) {
                    if (z2) {
                        niVar.b(SystemClock.elapsedRealtime());
                        niVar.g = false;
                    } else {
                        long jCurrentTimeMillis = niVar.j;
                        if (jCurrentTimeMillis <= 0) {
                            jCurrentTimeMillis = System.currentTimeMillis();
                        }
                        niVar.e(jCurrentTimeMillis);
                    }
                }
                ni.N = null;
                break;
        }
    }
}
