package defpackage;

import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class kc extends TextView {
    public final void a(jc jcVar) {
        jcVar.getClass();
        List listI0 = j3.i0(jcVar.d, jcVar.c);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listI0) {
            if (!th.A((String) obj)) {
                arrayList.add(obj);
            }
        }
        String strY0 = i3.y0(arrayList, " — ", null, 62);
        if (th.A(strY0)) {
            strY0 = "—";
        }
        if (!k8.e(getText().toString(), strY0)) {
            setText(strY0);
        }
        setContentDescription(strY0);
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i2 > 0) {
            float f = i2;
            setTextSize(0, 0.5f * f);
            int i5 = (int) (f * 0.34f);
            setPadding(i5, 0, i5, 0);
        }
    }
}
