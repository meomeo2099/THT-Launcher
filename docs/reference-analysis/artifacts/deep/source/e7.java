package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class e7 implements y5 {
    public final /* synthetic */ int a;
    public final /* synthetic */ String b;
    public final /* synthetic */ Context c;

    public /* synthetic */ e7(Context context, String str) {
        this.a = 1;
        this.b = str;
        this.c = context;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:27:0x0094  */
    @Override // defpackage.y5
    public final Object a() {
        View jiVar;
        int i = this.a;
        String str = this.b;
        Context context = this.c;
        switch (i) {
            case 0:
                me meVar = i7.a;
                cj cjVar = new cj(context, str);
                cjVar.setHalo(false);
                cjVar.setNightMode(true);
                cjVar.setEnabledState(Boolean.FALSE);
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888);
                bitmapCreateBitmap.getClass();
                Canvas canvas = new Canvas(bitmapCreateBitmap);
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(256, 1073741824);
                cjVar.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                cjVar.layout(0, 0, 256, 256);
                cjVar.draw(canvas);
                return bitmapCreateBitmap;
            case 1:
                me meVar2 = i7.a;
                int i2 = 6;
                switch (str) {
                    case "camera360":
                        jiVar = new n2(new j(i2), context, true, false);
                        break;
                    case "screensaver":
                        jiVar = new lf(new j(i2), context, true, false);
                        break;
                    case "gps":
                        jiVar = new a7(context, new j(i2));
                        break;
                    case "theme-day":
                        jiVar = new ji(context, false, new j(i2));
                        break;
                    case "radio":
                        ie ieVar = new ie(context, true, false);
                        ie.f(ieVar, null, null, 6);
                        jiVar = ieVar;
                        break;
                    default:
                        jiVar = new ji(context, true, new j(i2));
                        break;
                }
                Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888);
                bitmapCreateBitmap2.getClass();
                Canvas canvas2 = new Canvas(bitmapCreateBitmap2);
                int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(256, 1073741824);
                jiVar.measure(iMakeMeasureSpec2, iMakeMeasureSpec2);
                jiVar.layout(0, 0, 256, 256);
                jiVar.draw(canvas2);
                return bitmapCreateBitmap2;
            default:
                me meVar3 = i7.a;
                zc zcVar = new zc(context, str);
                Bitmap bitmapCreateBitmap3 = Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888);
                bitmapCreateBitmap3.getClass();
                Canvas canvas3 = new Canvas(bitmapCreateBitmap3);
                int iMakeMeasureSpec3 = View.MeasureSpec.makeMeasureSpec(256, 1073741824);
                zcVar.measure(iMakeMeasureSpec3, iMakeMeasureSpec3);
                zcVar.layout(0, 0, 256, 256);
                zcVar.draw(canvas3);
                return bitmapCreateBitmap3;
        }
    }

    public /* synthetic */ e7(Context context, String str, int i) {
        this.a = i;
        this.c = context;
        this.b = str;
    }
}
