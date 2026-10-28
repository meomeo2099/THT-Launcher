package defpackage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class f7 implements y5 {
    public final /* synthetic */ int a = 1;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Serializable d;

    public /* synthetic */ f7(v9 v9Var, uj ujVar, boolean z) {
        this.c = v9Var;
        this.d = ujVar;
        this.b = z;
    }

    @Override // defpackage.y5
    public final Object a() {
        int i = this.a;
        Serializable serializable = this.d;
        Object obj = this.c;
        switch (i) {
            case 0:
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888);
                bitmapCreateBitmap.getClass();
                ((v9) obj).b(new Canvas(bitmapCreateBitmap), (uj) serializable, this.b, 128.0f, 128.0f, 204.8f);
                return bitmapCreateBitmap;
            default:
                ((je) obj).a = this.b;
                Object obj2 = ((me) serializable).b;
                if (obj2 != null) {
                    ((y5) obj2).a();
                    return ti.a;
                }
                k8.H0("fill");
                throw null;
        }
    }

    public /* synthetic */ f7(je jeVar, boolean z, me meVar) {
        this.c = jeVar;
        this.b = z;
        this.d = meVar;
    }
}
