package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class qd {
    public final MainActivity a;
    public final a b;
    public final b3 c;
    public final gc d;
    public lb g;
    public long i;
    public long j;
    public boolean k;
    public final Handler e = new Handler(Looper.getMainLooper());
    public final LinkedHashMap f = new LinkedHashMap();
    public String h = "";

    public qd(MainActivity mainActivity, a aVar, b3 b3Var) {
        this.a = mainActivity;
        this.b = aVar;
        this.c = b3Var;
        this.d = mainActivity.y();
    }

    public static String b(lb lbVar) {
        String strA = lbVar.a();
        String strA2 = lbVar.a();
        String str = lbVar.b;
        str.getClass();
        Set set = nb.l;
        String strZ = pi.z(strA2, str);
        if (strZ == null) {
            strZ = th.J(str).toString();
        }
        return strA + strZ;
    }

    public final void a(boolean z) {
        LinkedHashMap linkedHashMap;
        lb lbVar;
        this.k = false;
        this.e.removeCallbacksAndMessages(null);
        MainActivity mainActivity = this.a;
        List listL0 = k8.l0(mainActivity, "radio_stations");
        ArrayList arrayList = new ArrayList();
        Iterator it = listL0.iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            linkedHashMap = this.f;
            if (!zHasNext) {
                break;
            }
            Object next = it.next();
            kd kdVar = (kd) next;
            Collection collectionValues = linkedHashMap.values();
            collectionValues.getClass();
            if (!collectionValues.isEmpty()) {
                Iterator it2 = collectionValues.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        kd kdVar2 = (kd) it2.next();
                        if (kdVar2.b().equals(kdVar.b()) && kdVar2.c(kdVar.a)) {
                            break;
                        }
                    }
                }
            }
            arrayList.add(next);
        }
        Collection collectionValues2 = linkedHashMap.values();
        collectionValues2.getClass();
        k8.P0(mainActivity, "radio_stations", k8.x0(i3.D0(arrayList, collectionValues2)));
        if (z && (lbVar = this.g) != null) {
            if (th.A(lbVar.b)) {
                lbVar = null;
            }
            if (lbVar != null) {
                kd kdVar3 = new kd(lbVar.b, th.J(lbVar.c).toString(), lbVar.a);
                gc gcVar = this.d;
                gcVar.G.b = null;
                if (gcVar.n()) {
                    gcVar.E(kdVar3);
                }
            }
        }
        this.c.b(Integer.valueOf(linkedHashMap.size()));
    }

    public final void c(lb lbVar) {
        String str = lbVar.b;
        if (th.A(str)) {
            return;
        }
        this.f.put(b(lbVar), new kd(str, th.J(lbVar.c).toString(), lbVar.a));
    }

    public final void d() {
        this.i = SystemClock.uptimeMillis();
        Set set = gc.H;
        if (!this.d.v(true)) {
            a(false);
        } else {
            this.e.postDelayed(new k1(6, this), 400L);
        }
    }
}
