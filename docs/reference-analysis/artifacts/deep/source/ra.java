package defpackage;

import android.content.SharedPreferences;
import org.reference.panel.MainActivity;
import org.reference.panel.b;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ra implements j6 {
    public final /* synthetic */ int a;
    public final /* synthetic */ MainActivity b;
    public final /* synthetic */ int c;

    public /* synthetic */ ra(MainActivity mainActivity, int i, int i2) {
        this.a = i2;
        this.b = mainActivity;
        this.c = i;
    }

    @Override // defpackage.j6
    public final Object b(Object obj) {
        int i = this.a;
        ti tiVar = ti.a;
        int i2 = this.c;
        MainActivity mainActivity = this.b;
        switch (i) {
            case 0:
                int i3 = MainActivity.E0;
                ((b) obj).getClass();
                mainActivity.Z(i2);
                break;
            default:
                String str = (String) obj;
                str.getClass();
                SharedPreferences.Editor editorPutString = mainActivity.B().edit().putString("menu_action_" + i2, str);
                if (str.equals("widget:car")) {
                    editorPutString.putBoolean("show_vehicle", true);
                }
                editorPutString.apply();
                mainActivity.e0();
                break;
        }
        return tiVar;
    }
}
