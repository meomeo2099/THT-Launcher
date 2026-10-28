package defpackage;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class h4 implements TextWatcher {
    public final /* synthetic */ me a;
    public final /* synthetic */ LinearLayout b;
    public final /* synthetic */ GridLayout c;
    public final /* synthetic */ ArrayList d;
    public final /* synthetic */ i4 e;
    public final /* synthetic */ me f;
    public final /* synthetic */ ArrayList g;
    public final /* synthetic */ j6 h;

    public h4(i4 i4Var, j6 j6Var, me meVar, me meVar2, GridLayout gridLayout, LinearLayout linearLayout, ArrayList arrayList, ArrayList arrayList2) {
        this.a = meVar;
        this.b = linearLayout;
        this.c = gridLayout;
        this.d = arrayList;
        this.e = i4Var;
        this.f = meVar2;
        this.g = arrayList2;
        this.h = j6Var;
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        String string = charSequence != null ? charSequence.toString() : null;
        if (string == null) {
            string = "";
        }
        me meVar = this.a;
        meVar.b = string;
        i4.d(this.e, this.h, this.f, meVar, this.c, this.b, this.d, this.g);
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
