package defpackage;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Looper;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class y6 {
    public final Context a;
    public final LocationManager b;
    public volatile Location c;
    public boolean d;
    public final x6 e = new x6(0, this);

    public y6(Context context) {
        this.a = context;
        this.b = (LocationManager) context.getSystemService(LocationManager.class);
    }

    public final void a(boolean z, Looper looper) {
        if (z == this.d) {
            return;
        }
        LocationManager locationManager = this.b;
        if (!z) {
            if (locationManager != null) {
                try {
                    locationManager.removeUpdates(this.e);
                } catch (Throwable unused) {
                }
            }
            this.d = false;
            this.c = null;
            return;
        }
        if (locationManager == null || this.a.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0) {
            return;
        }
        try {
            this.b.requestLocationUpdates("gps", 1000L, 0.0f, this.e, looper);
            this.d = true;
        } catch (IllegalArgumentException | SecurityException unused2) {
        }
    }
}
