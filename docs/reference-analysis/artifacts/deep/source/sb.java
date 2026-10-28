package defpackage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class sb implements DialogInterface.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ MainActivity b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;

    public /* synthetic */ sb(MainActivity mainActivity, Object obj, Object obj2, int i) {
        this.a = i;
        this.b = mainActivity;
        this.c = obj;
        this.d = obj2;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        int i2 = this.a;
        MainActivity mainActivity = this.b;
        Object obj = this.d;
        Object obj2 = this.c;
        int i3 = 3;
        switch (i2) {
            case 0:
                String[] strArr = (String[]) obj2;
                List list = (List) obj;
                if (i == 0) {
                    k8.g0(mainActivity, 0);
                    break;
                } else if (i == 1) {
                    c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity).setTitle(si.a("swap_with", new Object[0])).setItems(strArr, new tb(list, mainActivity)), null, mainActivity);
                    break;
                } else if (i == 2) {
                    mainActivity.B().edit().remove(MainActivity.f(0)).apply();
                    mainActivity.e0();
                    break;
                } else if (i == 3) {
                    mainActivity.c = false;
                    mainActivity.f = true;
                    mainActivity.e0();
                    break;
                }
                break;
            case 1:
                bh bhVar = (bh) obj2;
                String str = (String) obj;
                MainActivity mainActivity2 = this.b;
                if (i == 0 || i == 1) {
                    boolean z = mainActivity2.z();
                    List list2 = si.a;
                    new i0(mainActivity2, z, si.a(i == 0 ? "split_left" : "split_right", new Object[0]), false, new fh(mainActivity2, str, i)).show();
                } else if (i == 2) {
                    List list3 = eh.a;
                    ArrayList arrayList = new ArrayList(k3.n0(list3));
                    Iterator it = list3.iterator();
                    while (it.hasNext()) {
                        int iIntValue = ((Number) it.next()).intValue();
                        arrayList.add(iIntValue + " / " + (100 - iIntValue));
                    }
                    AlertDialog.Builder negativeButton = new AlertDialog.Builder(mainActivity2).setTitle(si.a("split_ratio", new Object[0])).setSingleChoiceItems((CharSequence[]) arrayList.toArray(new String[0]), eh.a.indexOf(Integer.valueOf(bhVar.c)), new c8(mainActivity2, str, 3)).setNegativeButton(si.a("close", new Object[0]), new c8(mainActivity2, str, 4));
                    negativeButton.getClass();
                    mainActivity2.p0(negativeButton);
                } else if (i == 3) {
                    k8.J0(str, mainActivity2);
                } else if (i == 4) {
                    String str2 = mainActivity2.u() ? "wall_splits" : "desktop_splits";
                    String str3 = (mainActivity2.u() ? "wsplit_" : "split_") + str;
                    SharedPreferences sharedPreferencesB = mainActivity2.B();
                    Set<String> set = x4.a;
                    Set<String> stringSet = sharedPreferencesB.getStringSet("desktop_hidden", set);
                    if (stringSet == null) {
                        stringSet = set;
                    }
                    LinkedHashSet linkedHashSetG0 = zf.g0(stringSet, str3);
                    SharedPreferences.Editor editorEdit = mainActivity2.B().edit();
                    Set<String> stringSet2 = mainActivity2.B().getStringSet(str2, set);
                    if (stringSet2 != null) {
                        set = stringSet2;
                    }
                    editorEdit.putStringSet(str2, zf.i0(set, str)).putStringSet("desktop_hidden", linkedHashSetG0).apply();
                    mainActivity2.v0(si.a("split_added_desktop", new Object[0]));
                    mainActivity2.e0();
                    k8.y0(str, mainActivity2);
                } else if (i == 5) {
                    k8.b("split:" + str, mainActivity2);
                    k8.y0(str, mainActivity2);
                } else {
                    c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity2).setMessage(si.a("split_delete_question", new Object[0])).setPositiveButton(si.a("delete", new Object[0]), new c8(mainActivity2, str, 5)), null, mainActivity2);
                }
                break;
            default:
                nj njVarH = mainActivity.H();
                int iIntValue2 = ((Number) ((bj) obj2).g.get(i)).intValue();
                njVarH.getClass();
                njVarH.b((lg) obj, new zd(iIntValue2, i3));
                break;
        }
    }
}
