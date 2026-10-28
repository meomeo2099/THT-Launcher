package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.media.browse.MediaBrowser;
import android.media.session.MediaController;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class hd {
    public final Context a;
    public final Handler b;
    public MediaBrowser c;
    public MediaController d;
    public int e;
    public ed f;
    public String g;

    public hd(Context context) {
        context.getClass();
        this.a = context.getApplicationContext();
        this.b = new Handler(Looper.getMainLooper());
        this.f = new ed();
    }

    public static final void b(int i, hd hdVar, le leVar, List list) {
        Object ffVar;
        if (i != hdVar.e || leVar.a >= list.size()) {
            return;
        }
        MediaBrowser mediaBrowser = hdVar.c;
        if (mediaBrowser != null) {
            try {
                mediaBrowser.disconnect();
            } catch (Throwable unused) {
            }
        }
        hdVar.d = null;
        int i2 = leVar.a;
        leVar.a = i2 + 1;
        ComponentName componentName = (ComponentName) list.get(i2);
        me meVar = new me(0);
        le leVar2 = leVar;
        List list2 = list;
        try {
            MediaBrowser mediaBrowser2 = new MediaBrowser(hdVar.a, componentName, new fd(i, hdVar, meVar, leVar2, list2), null);
            meVar.b = mediaBrowser2;
            hdVar.c = mediaBrowser2;
            mediaBrowser2.connect();
            Handler handler = hdVar.b;
            try {
                qa qaVar = new qa(i, hdVar, meVar, hdVar, leVar2, list2);
                leVar2 = leVar2;
                list2 = list2;
                ffVar = Boolean.valueOf(handler.postDelayed(qaVar, 2000L));
            } catch (Throwable th) {
                th = th;
                leVar2 = leVar2;
                list2 = list2;
                ffVar = new ff(th);
            }
        } catch (Throwable th2) {
            th = th2;
        }
        Throwable thA = gf.a(ffVar);
        if (thA != null) {
            Log.w("ReferencePanel.Music", "Cannot connect to " + componentName, thA);
            b(i, hdVar, leVar2, list2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [ff] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v9, types: [java.util.ArrayList] */
    public final void a(String str, y5 y5Var) {
        ?? ffVar;
        str.getClass();
        c();
        int i = this.e;
        ed edVar = this.f;
        this.g = str;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        je jeVar = new je();
        je jeVar2 = new je();
        try {
            int i2 = 0;
            List<ResolveInfo> listQueryIntentServices = this.a.getPackageManager().queryIntentServices(new Intent("android.media.browse.MediaBrowserService").setPackage(str), 0);
            listQueryIntentServices.getClass();
            ArrayList arrayList = new ArrayList();
            Iterator it = listQueryIntentServices.iterator();
            while (it.hasNext()) {
                ServiceInfo serviceInfo = ((ResolveInfo) it.next()).serviceInfo;
                if (serviceInfo != null) {
                    arrayList.add(serviceInfo);
                }
            }
            ArrayList arrayList2 = new ArrayList();
            int size = arrayList.size();
            int i3 = 0;
            while (i3 < size) {
                Object obj = arrayList.get(i3);
                i3++;
                ServiceInfo serviceInfo2 = (ServiceInfo) obj;
                if (serviceInfo2.enabled && serviceInfo2.exported) {
                    arrayList2.add(obj);
                }
            }
            ffVar = new ArrayList(k3.n0(arrayList2));
            int size2 = arrayList2.size();
            while (i2 < size2) {
                Object obj2 = arrayList2.get(i2);
                i2++;
                ServiceInfo serviceInfo3 = (ServiceInfo) obj2;
                ffVar.add(new ComponentName(serviceInfo3.packageName, serviceInfo3.name));
            }
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        boolean z = ffVar instanceof ff;
        ?? r7 = ffVar;
        if (z) {
            r7 = u4.a;
        }
        b(i, this, new le(), (List) r7);
        this.b.post(new gd(i, this, str, jElapsedRealtime, edVar, y5Var, jeVar2, jeVar));
    }

    public final void c() {
        this.e++;
        this.g = null;
        this.f = new ed();
        this.b.removeCallbacksAndMessages(null);
        MediaBrowser mediaBrowser = this.c;
        if (mediaBrowser != null) {
            try {
                mediaBrowser.disconnect();
            } catch (Throwable unused) {
            }
        }
        this.c = null;
        this.d = null;
    }
}
