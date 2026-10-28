package defpackage;

import android.app.Dialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import java.util.Map;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class f0 implements TextWatcher {
    public final /* synthetic */ int a;
    public final /* synthetic */ View b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ KeyEvent.Callback d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ KeyEvent.Callback f;
    public final /* synthetic */ Object g;

    public /* synthetic */ f0(View view, Object obj, KeyEvent.Callback callback, Object obj2, KeyEvent.Callback callback2, Object obj3, int i) {
        this.a = i;
        this.b = view;
        this.c = obj;
        this.d = callback;
        this.e = obj2;
        this.f = callback2;
        this.g = obj3;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        int i = this.a;
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        int i4 = this.a;
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        int i4 = this.a;
        Object obj = this.g;
        KeyEvent.Callback callback = this.f;
        Object obj2 = this.e;
        KeyEvent.Callback callback2 = this.d;
        Object obj3 = this.c;
        View view = this.b;
        switch (i4) {
            case 0:
                i0.b((g0) obj, (i0) obj3, (EditText) view, (TextView) callback2, (TextView) obj2, (TextView) callback);
                break;
            default:
                LinearLayout linearLayout = (LinearLayout) view;
                i4 i4Var = (i4) obj3;
                MainActivity mainActivity = (MainActivity) callback2;
                List list = (List) obj2;
                Dialog dialog = (Dialog) callback;
                Map map = (Map) obj;
                String string = charSequence != null ? charSequence.toString() : null;
                if (string == null) {
                    string = "";
                }
                k8.k0(linearLayout, i4Var, mainActivity, list, dialog, map, string);
                break;
        }
    }

    private final void a(Editable editable) {
    }

    private final void b(Editable editable) {
    }

    private final void c(CharSequence charSequence, int i, int i2, int i3) {
    }

    private final void d(CharSequence charSequence, int i, int i2, int i3) {
    }
}
