package defpackage;

import android.content.Context;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Looper;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class ye {
    public final Context a;
    public final ua b;
    public final LocationManager c;
    public af e;
    public boolean f;
    public final Handler d = new Handler(Looper.getMainLooper());
    public final x6 g = new x6(2, this);

    public ye(Context context, ua uaVar) {
        this.a = context;
        this.b = uaVar;
        this.c = (LocationManager) context.getSystemService(LocationManager.class);
    }
}
