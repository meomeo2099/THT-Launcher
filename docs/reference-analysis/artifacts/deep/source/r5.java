package defpackage;

import android.content.DialogInterface;
import android.view.View;
import java.util.ArrayList;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class r5 implements DialogInterface.OnDismissListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ MainActivity b;
    public final /* synthetic */ Object c;

    public /* synthetic */ r5(MainActivity mainActivity, Object obj, int i) {
        this.a = i;
        this.b = mainActivity;
        this.c = obj;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        int size;
        int i = this.a;
        Object obj = this.c;
        MainActivity mainActivity = this.b;
        switch (i) {
            case 0:
                mainActivity.B.removeAll((ArrayList) obj);
                return;
            case 1:
                View view = (View) obj;
                ArrayList arrayList = mainActivity.M;
                pi.d(arrayList).remove(view);
                pi.d(mainActivity.N).remove(view);
                pi.d(mainActivity.V).remove(view);
                pi.d(mainActivity.W).remove(view);
                pi.d(mainActivity.K).remove(view);
                pi.d(mainActivity.G).remove(view);
                pi.d(mainActivity.H).remove(view);
                pi.d(mainActivity.L).remove(view);
                pi.d(mainActivity.P).remove(view);
                ArrayList arrayList2 = mainActivity.Q;
                arrayList2.getClass();
                int size2 = arrayList2.size() - 1;
                int i2 = 0;
                if (size2 >= 0) {
                    int i3 = 0;
                    while (true) {
                        Object obj2 = arrayList2.get(i2);
                        yc ycVar = (yc) obj2;
                        ycVar.getClass();
                        if (ycVar.b != view) {
                            if (i3 != i2) {
                                arrayList2.set(i3, obj2);
                            }
                            i3++;
                        }
                        if (i2 != size2) {
                            i2++;
                        } else {
                            i2 = i3;
                        }
                    }
                }
                if (i2 < arrayList2.size() && i2 <= (size = arrayList2.size() - 1)) {
                    while (true) {
                        arrayList2.remove(size);
                        if (size != i2) {
                            size--;
                        }
                    }
                }
                mainActivity.X = (v9) i3.v0(arrayList);
                if (arrayList.isEmpty()) {
                    mainActivity.I().g();
                    return;
                }
                return;
            default:
                mainActivity.I = null;
                Object obj3 = ((me) obj).b;
                if (obj3 == null) {
                    k8.H0("scan");
                    throw null;
                }
                qd qdVar = (qd) obj3;
                if (qdVar.k) {
                    qdVar.a(true);
                    return;
                }
                return;
        }
    }
}
