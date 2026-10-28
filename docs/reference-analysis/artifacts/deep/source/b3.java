package defpackage;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.widget.GridLayout;
import android.widget.TextView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b3 implements j6 {
    public final /* synthetic */ int a = 1;
    public final /* synthetic */ MainActivity b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Serializable d;
    public final /* synthetic */ Object e;

    public /* synthetic */ b3(Dialog dialog, GridLayout gridLayout, ArrayList arrayList, MainActivity mainActivity) {
        this.b = mainActivity;
        this.c = gridLayout;
        this.d = arrayList;
        this.e = dialog;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x00a0  */
    @Override // defpackage.j6
    public final Object b(Object obj) {
        int i = this.a;
        ti tiVar = ti.a;
        Object obj2 = this.e;
        Serializable serializable = this.d;
        MainActivity mainActivity = this.b;
        Object obj3 = this.c;
        switch (i) {
            case 0:
                Integer num = (Integer) serializable;
                String str = (String) obj2;
                String str2 = (String) obj;
                str2.getClass();
                ArrayList arrayList = new ArrayList((List) obj3);
                if (num != null) {
                    j8 j8VarG0 = j3.g0(arrayList);
                    int iIntValue = num.intValue();
                    if (iIntValue < 0 || iIntValue > j8VarG0.b) {
                        arrayList.add(str2);
                    } else {
                        arrayList.set(num.intValue(), str2);
                    }
                } else {
                    arrayList.add(str2);
                }
                List list = y2.a;
                SharedPreferences sharedPreferencesB = mainActivity.B();
                sharedPreferencesB.getClass();
                y2.d(sharedPreferencesB, str, i3.H0(arrayList, 8));
                mainActivity.e0();
                return tiVar;
            case 1:
                String str3 = (String) obj;
                str3.getClass();
                w5.b(mainActivity, i3.E0(w5.a(mainActivity), str3));
                k8.f0((Dialog) obj2, (GridLayout) obj3, (ArrayList) serializable, mainActivity);
                mainActivity.e0();
                return tiVar;
            default:
                TextView textView = (TextView) obj3;
                Integer num2 = (Integer) obj;
                num2.getClass();
                textView.setText(si.a("radio_scan", new Object[0]));
                Drawable background = textView.getBackground();
                background.getClass();
                int i2 = b7.h;
                l8.h();
                ((b7) background).a(-10233877, false);
                mainActivity.v0(si.a("radio_scan_done_0", num2));
                ((je) serializable).a = true;
                Object obj4 = ((me) obj2).b;
                if (obj4 != null) {
                    ((y5) obj4).a();
                    return tiVar;
                }
                k8.H0("refresh");
                throw null;
        }
    }

    public /* synthetic */ b3(TextView textView, MainActivity mainActivity, je jeVar, me meVar) {
        this.c = textView;
        this.b = mainActivity;
        this.d = jeVar;
        this.e = meVar;
    }

    public /* synthetic */ b3(List list, Integer num, MainActivity mainActivity, String str) {
        this.c = list;
        this.d = num;
        this.b = mainActivity;
        this.e = str;
    }
}
