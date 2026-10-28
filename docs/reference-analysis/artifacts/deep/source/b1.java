package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import java.util.ArrayList;
import org.reference.panel.Guard;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b1 implements y5 {
    public final /* synthetic */ int a;
    public final /* synthetic */ s1 b;

    public /* synthetic */ b1(s1 s1Var, int i) {
        this.a = i;
        this.b = s1Var;
    }

    @Override // defpackage.y5
    public final Object a() {
        int i = this.a;
        s1 s1Var = this.b;
        switch (i) {
            case 0:
                return r4.C.p(s1Var.a);
            default:
                int i2 = 0;
                if (((z0) s1Var.f).b("apps", false)) {
                    Context context = s1Var.a;
                    Handler handler = s1Var.b;
                    ArrayList arrayListS = k8.S(context);
                    final boolean zB = ((z0) s1Var.f).b("apps_home", true);
                    handler.getClass();
                    if (!arrayListS.isEmpty()) {
                        final Context applicationContext = context.getApplicationContext();
                        Guard guard = Guard.INSTANCE;
                        applicationContext.getClass();
                        if (guard.offset(applicationContext) == 0) {
                            k8.f++;
                            final int i3 = k8.f;
                            k8.g = true;
                            ArrayList arrayList = new ArrayList(k3.n0(arrayListS));
                            int size = arrayListS.size();
                            long jS = 0;
                            int i4 = 0;
                            while (i4 < size) {
                                Object obj = arrayListS.get(i4);
                                i4++;
                                jS += ((long) pi.s(((e2) obj).b, 0, 120)) * 1000;
                                arrayList.add(Long.valueOf(jS));
                            }
                            int size2 = arrayListS.size();
                            int i5 = 0;
                            while (i5 < size2) {
                                Object obj2 = arrayListS.get(i5);
                                i5++;
                                int i6 = i2 + 1;
                                if (i2 < 0) {
                                    j3.m0();
                                    throw null;
                                }
                                handler.postDelayed(new u(i3, applicationContext, (e2) obj2), ((Number) arrayList.get(i2)).longValue());
                                i2 = i6;
                            }
                            handler.postDelayed(new Runnable() { // from class: v
                                @Override // java.lang.Runnable
                                public final void run() {
                                    Object ffVar;
                                    int i7 = i3;
                                    boolean z = zB;
                                    Context context2 = applicationContext;
                                    if (i7 != k8.f) {
                                        return;
                                    }
                                    k8.g = false;
                                    if (z) {
                                        try {
                                            context2.startActivity(new Intent(context2, (Class<?>) MainActivity.class).addFlags(805437440));
                                            ffVar = ti.a;
                                        } catch (Throwable th) {
                                            ffVar = new ff(th);
                                        }
                                        Throwable thA = gf.a(ffVar);
                                        if (thA != null) {
                                            Log.w("ReferencePanel.Autostart", "return home", thA);
                                        }
                                    }
                                }
                            }, ((Number) i3.z0(arrayList)).longValue() + (zB ? 3000L : 500L));
                        }
                    }
                }
                return ti.a;
        }
    }
}
