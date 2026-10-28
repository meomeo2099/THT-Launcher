package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import org.reference.panel.Guard;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class z6 implements y5 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Context b;

    public /* synthetic */ z6(Context context, int i) {
        this.a = i;
        this.b = context;
    }

    @Override // defpackage.y5
    public final Object a() {
        int i = this.a;
        Context context = this.b;
        switch (i) {
            case 0:
                return Boolean.valueOf(Guard.INSTANCE.offset(context) == 0);
            default:
                me meVar = i7.a;
                r9 r9Var = new r9(context);
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888);
                bitmapCreateBitmap.getClass();
                Canvas canvas = new Canvas(bitmapCreateBitmap);
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(256, 1073741824);
                r9Var.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                r9Var.layout(0, 0, 256, 256);
                r9Var.draw(canvas);
                return bitmapCreateBitmap;
        }
    }
}
