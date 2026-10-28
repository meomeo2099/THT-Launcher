package defpackage;

import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class y implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ View d;
    public final /* synthetic */ KeyEvent.Callback e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ y(Object obj, Object obj2, View view, KeyEvent.Callback callback, Object obj3, Object obj4, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
        this.d = view;
        this.e = callback;
        this.f = obj3;
        this.g = obj4;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) throws IllegalAccessException, IOException, InvocationTargetException {
        int i = this.a;
        Object obj = this.g;
        Object obj2 = this.f;
        KeyEvent.Callback callback = this.e;
        View view2 = this.d;
        Object obj3 = this.c;
        Object obj4 = this.b;
        switch (i) {
            case 0:
                i0 i0Var = (i0) obj4;
                i0Var.k = false;
                i0.b((g0) obj, i0Var, (EditText) obj3, (TextView) view2, (TextView) callback, (TextView) obj2);
                break;
            case 1:
                i0 i0Var2 = (i0) obj4;
                i0Var2.k = true;
                i0.b((g0) obj, i0Var2, (EditText) obj3, (TextView) view2, (TextView) callback, (TextView) obj2);
                break;
            default:
                me meVar = (me) obj4;
                meVar.b = (lh) obj3;
                pi.T((je) obj2, meVar, (kh) obj, (FrameLayout) view2, (MainActivity) callback);
                break;
        }
    }
}
