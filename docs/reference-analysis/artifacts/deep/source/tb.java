package defpackage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.InputFilter;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class tb implements DialogInterface.OnClickListener {
    public final /* synthetic */ int a = 0;
    public final /* synthetic */ MainActivity b;
    public final /* synthetic */ List c;

    public /* synthetic */ tb(List list, MainActivity mainActivity) {
        this.c = list;
        this.b = mainActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        int i2 = this.a;
        List list = this.c;
        MainActivity mainActivity = this.b;
        switch (i2) {
            case 0:
                int iIntValue = ((Number) list.get(i)).intValue();
                mainActivity.B().edit().putString(MainActivity.f(0), mainActivity.B().getString(MainActivity.f(iIntValue), null)).putString(MainActivity.f(iIntValue), mainActivity.B().getString(MainActivity.f(0), null)).apply();
                mainActivity.e0();
                break;
            default:
                List listH = uh.h();
                ArrayList arrayList = new ArrayList();
                for (Object obj : listH) {
                    if (k8.e(((zi) obj).b, list.get(i))) {
                        arrayList.add(obj);
                    }
                }
                zi ziVar = (zi) (arrayList.size() == 1 ? arrayList.get(0) : null);
                if (k8.e(ziVar != null ? ziVar.a : null, "custom")) {
                    EditText editText = new EditText(mainActivity);
                    editText.setHint(si.a("for_example_changan_q05", new Object[0]));
                    editText.setSingleLine();
                    editText.setText(mainActivity.B().getString("custom_title", ""));
                    editText.setFilters(new InputFilter.LengthFilter[]{new InputFilter.LengthFilter(48)});
                    c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity).setTitle(si.a("vehicle_name", new Object[0])).setView(editText).setPositiveButton(si.a("choose", new Object[0]), new gg(mainActivity, editText)), null, mainActivity);
                } else {
                    AlertDialog.Builder title = new AlertDialog.Builder(mainActivity).setTitle((CharSequence) list.get(i));
                    ArrayList arrayList2 = new ArrayList(k3.n0(arrayList));
                    int size = arrayList.size();
                    int i3 = 0;
                    while (i3 < size) {
                        Object obj2 = arrayList.get(i3);
                        i3++;
                        arrayList2.add(((zi) obj2).c);
                    }
                    c.e("cancel", new Object[0], title.setItems((CharSequence[]) arrayList2.toArray(new String[0]), new dg(mainActivity, arrayList)), null, mainActivity);
                }
                break;
        }
    }

    public /* synthetic */ tb(MainActivity mainActivity, List list) {
        this.b = mainActivity;
        this.c = list;
    }
}
