package defpackage;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.widget.EditText;
import java.util.ArrayList;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class gg implements DialogInterface.OnClickListener {
    public final /* synthetic */ int a = 1;
    public final /* synthetic */ EditText b;
    public final /* synthetic */ MainActivity c;

    public /* synthetic */ gg(EditText editText, MainActivity mainActivity) {
        this.b = editText;
        this.c = mainActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        int i2 = this.a;
        int i3 = 0;
        MainActivity mainActivity = this.c;
        EditText editText = this.b;
        switch (i2) {
            case 0:
                SharedPreferences.Editor editorEdit = mainActivity.B().edit();
                String string = th.J(editText.getText().toString()).toString();
                if (string.length() == 0) {
                    string = si.a("my_vehicle", new Object[0]);
                }
                editorEdit.putString("custom_title", string).apply();
                mainActivity.B().edit().putString("vehicle", "custom").apply();
                mainActivity.J();
                mainActivity.e0();
                break;
            default:
                Float fP = ai.p(ci.r(editText.getText().toString(), ',', '.'));
                if (fP != null) {
                    float fFloatValue = fP.floatValue();
                    if (fFloatValue >= 20.0f && fFloatValue <= 150.0f) {
                        mainActivity.B().edit().putFloat("tank_liters", fP.floatValue()).apply();
                        ArrayList arrayList = mainActivity.z;
                        int size = arrayList.size();
                        while (i3 < size) {
                            Object obj = arrayList.get(i3);
                            i3++;
                            ((aj) obj).a(mainActivity.Z);
                        }
                        mainActivity.c0();
                    }
                }
                mainActivity.v0(si.a("enter_a_capacity_between_20_and_150", new Object[0]));
                break;
        }
    }

    public /* synthetic */ gg(MainActivity mainActivity, EditText editText) {
        this.c = mainActivity;
        this.b = editText;
    }
}
