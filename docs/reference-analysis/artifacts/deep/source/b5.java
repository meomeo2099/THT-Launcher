package defpackage;

import android.app.Activity;
import android.content.ComponentName;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b5 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ KeyEvent.Callback c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ b5(Object obj, KeyEvent.Callback callback, Object obj2, Object obj3, Object obj4, int i) {
        this.a = i;
        this.b = obj;
        this.c = callback;
        this.d = obj2;
        this.e = obj3;
        this.f = obj4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.a;
        Object obj = this.f;
        Object obj2 = this.e;
        Object obj3 = this.d;
        KeyEvent.Callback callback = this.c;
        Object obj4 = this.b;
        switch (i) {
            case 0:
                k8.a0((c3) obj4, (Activity) callback, (ComponentName) obj3, (ArrayList) obj2, (ya) obj, "repair");
                break;
            case 1:
                k8.a0((c3) obj4, (Activity) callback, (ComponentName) obj3, (ArrayList) obj2, (ya) obj, "verify");
                break;
            case 2:
                k8.a0((c3) obj4, (Activity) callback, (ComponentName) obj3, (ArrayList) obj2, (ya) obj, "settled");
                break;
            default:
                LinearLayout linearLayout = (LinearLayout) callback;
                e0 e0Var = (e0) obj3;
                Drawable drawable = (Drawable) obj2;
                ImageView imageView = (ImageView) obj;
                if (!((i0) obj4).h && k8.e(linearLayout.getTag(), e0Var.b) && drawable != null) {
                    imageView.setImageDrawable(drawable);
                    break;
                }
                break;
        }
    }
}
