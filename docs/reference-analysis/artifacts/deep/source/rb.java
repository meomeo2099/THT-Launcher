package defpackage;

import android.app.Dialog;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class rb implements y5 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Dialog b;
    public final /* synthetic */ MainActivity c;

    public /* synthetic */ rb(Dialog dialog, MainActivity mainActivity, int i) {
        this.a = i;
        this.b = dialog;
        this.c = mainActivity;
    }

    @Override // defpackage.y5
    public final Object a() {
        int i = this.a;
        ti tiVar = ti.a;
        Dialog dialog = this.b;
        MainActivity mainActivity = this.c;
        switch (i) {
            case 0:
                dialog.dismiss();
                k8.d(mainActivity);
                break;
            case 1:
                dialog.dismiss();
                mainActivity.e0();
                break;
            case 2:
                SharedPreferences.Editor editorEdit = mainActivity.B().edit();
                Set<String> setKeySet = mainActivity.B().getAll().keySet();
                ArrayList arrayList = new ArrayList();
                for (Object obj : setKeySet) {
                    String str = (String) obj;
                    str.getClass();
                    if (str.startsWith("desktop_") || str.equals("ui_scale")) {
                        arrayList.add(obj);
                    }
                }
                int size = arrayList.size();
                int i2 = 0;
                while (i2 < size) {
                    Object obj2 = arrayList.get(i2);
                    i2++;
                    editorEdit.remove((String) obj2);
                }
                Iterator it = j3.i0("wall_widgets", "wall_apps", "wall_vehicle_cards", "wall_actions", "wall_images").iterator();
                while (it.hasNext()) {
                    editorEdit.remove((String) it.next());
                }
                editorEdit.putStringSet("desktop_hidden", x0.Y0(new String[]{"ios_maps", "ios_clock", "ios_car", "ios_music2"}));
                editorEdit.apply();
                dialog.dismiss();
                mainActivity.e0();
                break;
            default:
                k8.j0(mainActivity, dialog, null);
                break;
        }
        return tiVar;
    }

    public /* synthetic */ rb(MainActivity mainActivity, Dialog dialog, int i) {
        this.a = i;
        this.c = mainActivity;
        this.b = dialog;
    }
}
