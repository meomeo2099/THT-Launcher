package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ImageDecoder;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.net.Uri;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class bc implements Runnable {
    public final /* synthetic */ int a = 1;
    public final /* synthetic */ Object b;
    public final /* synthetic */ int c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Object e;

    public /* synthetic */ bc(gc gcVar, int i, String str, MediaSession.Token token) {
        this.b = gcVar;
        this.c = i;
        this.d = str;
        this.e = token;
    }

    /* JADX WARN: Code duplicated, block: B:70:0x012e  */
    @Override // java.lang.Runnable
    public final void run() {
        Object ffVar;
        Object objDecodeBitmap;
        Object ffVar2;
        Bitmap bitmapCreateBitmap;
        MediaController mediaController;
        Object ffVar3;
        int i = this.a;
        int i2 = this.c;
        Object obj = this.e;
        Object obj2 = this.d;
        Object obj3 = this.b;
        switch (i) {
            case 0:
                gc gcVar = (gc) obj3;
                Context context = gcVar.c;
                Uri uri = (Uri) obj2;
                Icon icon = (Icon) obj;
                int i3 = 1;
                if (uri != null) {
                    try {
                        objDecodeBitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.getContentResolver(), uri), new pa(i3));
                    } catch (Throwable th) {
                        ffVar = new ff(th);
                    }
                    break;
                } else {
                    objDecodeBitmap = null;
                }
                ffVar = objDecodeBitmap;
                if (ffVar instanceof ff) {
                    ffVar = null;
                }
                Bitmap bitmap = (Bitmap) ffVar;
                if (bitmap == null) {
                    if (icon != null) {
                        try {
                            Drawable drawableLoadDrawable = icon.loadDrawable(context);
                            if (drawableLoadDrawable != null) {
                                int intrinsicWidth = drawableLoadDrawable.getIntrinsicWidth();
                                if (intrinsicWidth < 1) {
                                    intrinsicWidth = 1;
                                }
                                int intrinsicHeight = drawableLoadDrawable.getIntrinsicHeight();
                                if (intrinsicHeight < 1) {
                                    intrinsicHeight = 1;
                                }
                                if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
                                    throw new IllegalArgumentException("Failed requirement.");
                                }
                                double dMin = Math.min(1.0d, 512.0d / ((double) Math.max(intrinsicWidth, intrinsicHeight)));
                                bitmapCreateBitmap = Bitmap.createBitmap(Math.max(1, (int) (((double) intrinsicWidth) * dMin)), Math.max(1, (int) (((double) intrinsicHeight) * dMin)), Bitmap.Config.ARGB_8888);
                                drawableLoadDrawable.setBounds(0, 0, bitmapCreateBitmap.getWidth(), bitmapCreateBitmap.getHeight());
                                drawableLoadDrawable.draw(new Canvas(bitmapCreateBitmap));
                            } else {
                                bitmapCreateBitmap = null;
                            }
                        } catch (Throwable th2) {
                            ffVar2 = new ff(th2);
                        }
                    } else {
                        bitmapCreateBitmap = null;
                    }
                    ffVar2 = bitmapCreateBitmap;
                    bitmap = (Bitmap) (ffVar2 instanceof ff ? null : ffVar2);
                }
                gcVar.f.post(new u(gcVar, i2, bitmap, 3));
                return;
            case 1:
                gc gcVar2 = (gc) obj3;
                String str = (String) obj2;
                MediaSession.Token token = (MediaSession.Token) obj;
                if (gcVar2.l && gcVar2.k == i2 && (mediaController = gcVar2.i) != null) {
                    if (!k8.e(mediaController.getSessionToken(), token)) {
                        mediaController = null;
                    }
                    if (mediaController == null) {
                        return;
                    }
                    MediaMetadata metadata = mediaController.getMetadata();
                    boolean zE = k8.e(metadata != null ? c.d(metadata.getString("android.media.metadata.TITLE"), "|", metadata.getString("android.media.metadata.ARTIST")) : null, str);
                    Long lG = gc.g(mediaController);
                    long jLongValue = lG != null ? lG.longValue() : Long.MAX_VALUE;
                    if (!zE || jLongValue >= 3000) {
                        return;
                    }
                    gcVar2.x(false);
                    return;
                }
                return;
            default:
                nj njVar = (nj) obj3;
                try {
                    ffVar3 = ((Class) obj2).getMethod("getCarManager", String.class).invoke(obj, "property");
                    break;
                } catch (Throwable th3) {
                    ffVar3 = new ff(th3);
                }
                njVar.c.post(new u(njVar, i2, ffVar3 instanceof ff ? null : ffVar3, 6));
                return;
        }
    }

    public /* synthetic */ bc(gc gcVar, Uri uri, Icon icon, int i) {
        this.b = gcVar;
        this.d = uri;
        this.e = icon;
        this.c = i;
    }

    public /* synthetic */ bc(mj mjVar, nj njVar, Class cls, Object obj, int i) {
        this.b = njVar;
        this.d = cls;
        this.e = obj;
        this.c = i;
    }
}
