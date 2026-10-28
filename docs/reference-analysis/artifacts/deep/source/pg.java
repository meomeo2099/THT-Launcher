package defpackage;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class pg implements TextWatcher {
    public final /* synthetic */ je a;
    public final /* synthetic */ EditText b;

    public pg(je jeVar, EditText editText) {
        this.a = jeVar;
        this.b = editText;
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        je jeVar = this.a;
        if (jeVar.a) {
            return;
        }
        jeVar.a = true;
        String string = charSequence != null ? charSequence.toString() : null;
        if (string == null) {
            string = "";
        }
        this.b.setText(string);
        jeVar.a = false;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
