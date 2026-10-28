package defpackage;

import android.content.ComponentName;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.browse.MediaBrowser;
import android.widget.FrameLayout;
import java.io.File;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class qa implements Runnable {
    public final /* synthetic */ int a = 2;
    public final /* synthetic */ Object b;
    public final /* synthetic */ int c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Serializable e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ qa(int i, hd hdVar, me meVar, hd hdVar2, le leVar, List list) {
        this.c = i;
        this.b = hdVar;
        this.f = meVar;
        this.d = hdVar2;
        this.e = leVar;
        this.g = list;
    }

    /* JADX WARN: Code duplicated, block: B:40:0x00c3 A[Catch: all -> 0x0091, TryCatch #0 {all -> 0x0091, blocks: (B:21:0x0086, B:23:0x008c, B:41:0x00cb, B:26:0x0093, B:27:0x0097, B:29:0x009d, B:34:0x00b9, B:38:0x00bf, B:40:0x00c3, B:42:0x00d8, B:43:0x00df, B:33:0x00b3, B:30:0x00a4), top: B:81:0x0086, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:42:0x00d8 A[Catch: all -> 0x0091, TryCatch #0 {all -> 0x0091, blocks: (B:21:0x0086, B:23:0x008c, B:41:0x00cb, B:26:0x0093, B:27:0x0097, B:29:0x009d, B:34:0x00b9, B:38:0x00bf, B:40:0x00c3, B:42:0x00d8, B:43:0x00df, B:33:0x00b3, B:30:0x00a4), top: B:81:0x0086, inners: #1 }] */
    @Override // java.lang.Runnable
    public final void run() {
        Object ffVar;
        RectF rectF;
        Object next;
        String str;
        ImageDecoder.Source sourceCreateSource;
        Object ffVar2;
        int i = this.a;
        RectF rectF2 = null;
        int i2 = this.c;
        int i3 = 0;
        Object obj = this.g;
        Object obj2 = this.d;
        Object obj3 = this.f;
        Serializable serializable = this.e;
        Object obj4 = this.b;
        switch (i) {
            case 0:
                MainActivity mainActivity = (MainActivity) obj4;
                Bitmap bitmap = (Bitmap) obj3;
                FrameLayout frameLayout = (FrameLayout) obj2;
                String str2 = (String) serializable;
                RectF rectF3 = (RectF) obj;
                int i4 = MainActivity.E0;
                if (mainActivity.isDestroyed() || i2 != mainActivity.n) {
                    return;
                }
                if (bitmap == null) {
                    frameLayout.removeAllViews();
                    frameLayout.addView(mainActivity.u0(si.a("could_not_open_image", new Object[0]), 16.0f, mainActivity.p));
                    return;
                }
                mainActivity.k = bitmap;
                mainActivity.l = str2;
                mainActivity.m = rectF3;
                Iterator it = i3.J0(mainActivity.N).iterator();
                while (it.hasNext()) {
                    mainActivity.n0((FrameLayout) it.next(), bitmap);
                }
                return;
            case 1:
                MainActivity mainActivity2 = (MainActivity) obj4;
                File file = (File) obj3;
                List list = (List) obj;
                FrameLayout frameLayout2 = (FrameLayout) obj2;
                String str3 = (String) serializable;
                int i5 = MainActivity.E0;
                try {
                    if (file.exists()) {
                        sourceCreateSource = ImageDecoder.createSource(file);
                    } else {
                        Iterator it2 = list.iterator();
                        do {
                            if (it2.hasNext()) {
                                next = it2.next();
                                try {
                                    k8.p(mainActivity2.getAssets().open((String) next), null);
                                    ffVar2 = ti.a;
                                } catch (Throwable th) {
                                    ffVar2 = new ff(th);
                                }
                            } else {
                                next = null;
                            }
                            str = (String) next;
                            if (str != null) {
                                throw new IllegalStateException("Vehicle asset missing");
                            }
                            sourceCreateSource = ImageDecoder.createSource(mainActivity2.getAssets(), str);
                            break;
                        } while (ffVar2 instanceof ff);
                        str = (String) next;
                        if (str != null) {
                            throw new IllegalStateException("Vehicle asset missing");
                        }
                        sourceCreateSource = ImageDecoder.createSource(mainActivity2.getAssets(), str);
                    }
                    sourceCreateSource.getClass();
                    ffVar = ImageDecoder.decodeBitmap(sourceCreateSource, new pa(i3));
                } catch (Throwable th2) {
                    ffVar = new ff(th2);
                }
                if (ffVar instanceof ff) {
                    ffVar = null;
                }
                Bitmap bitmap2 = (Bitmap) ffVar;
                if (bitmap2 != null) {
                    if (bitmap2.hasAlpha()) {
                        int width = bitmap2.getWidth();
                        int[] iArr = new int[width];
                        int width2 = bitmap2.getWidth();
                        int height = bitmap2.getHeight();
                        int height2 = bitmap2.getHeight();
                        int iMax = -1;
                        int i6 = 0;
                        int i7 = -1;
                        while (i6 < height2) {
                            bitmap2.getPixels(iArr, 0, bitmap2.getWidth(), 0, i6, bitmap2.getWidth(), 1);
                            iMax = iMax;
                            int iMax2 = i7;
                            for (int i8 = 0; i8 < width; i8++) {
                                if ((iArr[i8] >>> 24) > 12) {
                                    width2 = Math.min(width2, i8);
                                    iMax = Math.max(iMax, i8);
                                    height = Math.min(height, i6);
                                    iMax2 = Math.max(iMax2, i6);
                                }
                            }
                            i6++;
                            i7 = iMax2;
                        }
                        int i9 = iMax;
                        rectF = i9 < width2 ? new RectF(0.0f, 0.0f, bitmap2.getWidth(), bitmap2.getHeight()) : new RectF(width2, height, i9 + 1, i7 + 1);
                    } else {
                        rectF = new RectF(0.0f, 0.0f, bitmap2.getWidth(), bitmap2.getHeight());
                    }
                    rectF2 = rectF;
                }
                mainActivity2.runOnUiThread(new qa(mainActivity2, this.c, bitmap2, frameLayout2, str3, rectF2));
                return;
            case 2:
                hd hdVar = (hd) obj4;
                me meVar = (me) obj3;
                hd hdVar2 = (hd) obj2;
                le leVar = (le) serializable;
                List list2 = (List) obj;
                if (i2 == hdVar.e) {
                    MediaBrowser mediaBrowser = hdVar.c;
                    Object obj5 = meVar.b;
                    if (obj5 == null) {
                        k8.H0("candidate");
                        throw null;
                    }
                    if (mediaBrowser == ((MediaBrowser) obj5) && hdVar.d == null) {
                        hd.b(i2, hdVar2, leVar, list2);
                        return;
                    }
                    return;
                }
                return;
            default:
                final MainActivity mainActivity3 = (MainActivity) obj4;
                final String str4 = (String) serializable;
                final ComponentName componentName = (ComponentName) obj3;
                final ComponentName componentName2 = (ComponentName) obj2;
                final Rect rect = (Rect) obj;
                if (mainActivity3.isDestroyed()) {
                    return;
                }
                List list3 = eh.a;
                final lg lgVar = new lg(mainActivity3, 8);
                str4.getClass();
                componentName.getClass();
                componentName2.getClass();
                final int i10 = eh.c + 1;
                eh.c = i10;
                final int i11 = this.c;
                eh.f(mainActivity3, lgVar, i10, componentName2, new j6() { // from class: vg
                    @Override // defpackage.j6
                    public final Object b(Object obj6) {
                        final int iIntValue = ((Integer) obj6).intValue();
                        final lg lgVar2 = lgVar;
                        final String str5 = str4;
                        final Rect rect2 = rect;
                        final int i12 = i11;
                        final ComponentName componentName3 = componentName;
                        final ComponentName componentName4 = componentName2;
                        final int i13 = i10;
                        eh.f(mainActivity3, lgVar2, i13, componentName3, new j6() { // from class: wg
                            @Override // defpackage.j6
                            public final Object b(Object obj7) {
                                final int iIntValue2 = ((Integer) obj7).intValue();
                                boolean zD = eh.d(iIntValue2, 3);
                                ti tiVar = ti.a;
                                if (!zD) {
                                    lgVar2.b(si.a("split_unsupported", new Object[0]));
                                    return tiVar;
                                }
                                final String str6 = str5;
                                eh.d = str6;
                                final int i14 = iIntValue;
                                final int i15 = i12;
                                final int i16 = i13;
                                final ComponentName componentName5 = componentName3;
                                final ComponentName componentName6 = componentName4;
                                final Rect rect3 = rect2;
                                eh.e(i16, 300L, new y5() { // from class: xg
                                    @Override // defpackage.y5
                                    public final Object a() {
                                        final int i17 = i14;
                                        eh.d(i17, 4);
                                        final int i18 = i15;
                                        final int i19 = iIntValue2;
                                        final int i20 = i16;
                                        final ComponentName componentName7 = componentName5;
                                        final ComponentName componentName8 = componentName6;
                                        final Rect rect4 = rect3;
                                        final String str7 = str6;
                                        eh.e(i20, 250L, new y5(i18, i17, i19, i20, componentName7, componentName8, rect4, str7) { // from class: yg
                                            public final /* synthetic */ Rect a;
                                            public final /* synthetic */ int b;
                                            public final /* synthetic */ int c;
                                            public final /* synthetic */ ComponentName d;
                                            public final /* synthetic */ ComponentName e;
                                            public final /* synthetic */ int f;
                                            public final /* synthetic */ int g;

                                            @Override // defpackage.y5
                                            public final Object a() {
                                                ch chVarK = eh.k(false);
                                                final Rect rect5 = this.a;
                                                final int i21 = this.b;
                                                if (chVarK == null || chVarK.b.width() < (rect5.right - i21) / 4) {
                                                    Objects.toString(chVarK != null ? chVarK.b : null);
                                                    final int i22 = this.c;
                                                    eh.d(i22, 3);
                                                    final int i23 = this.f;
                                                    final int i24 = this.g;
                                                    eh.e(i24, 250L, new y5() { // from class: zg
                                                        @Override // defpackage.y5
                                                        public final Object a() {
                                                            eh.d(i22, 4);
                                                            final int i25 = i23;
                                                            final Rect rect6 = rect5;
                                                            final int i26 = i21;
                                                            final int i27 = i24;
                                                            eh.e(i27, 250L, new y5() { // from class: ah
                                                                @Override // defpackage.y5
                                                                public final Object a() {
                                                                    eh.d(i25, 3);
                                                                    eh.e(i27, 250L, new i2(rect6, i26, 2));
                                                                    return ti.a;
                                                                }
                                                            });
                                                            return ti.a;
                                                        }
                                                    });
                                                } else {
                                                    eh.g(rect5, i21);
                                                }
                                                this.d.getPackageName();
                                                this.e.getPackageName();
                                                return ti.a;
                                            }
                                        });
                                        return ti.a;
                                    }
                                });
                                return tiVar;
                            }
                        });
                        return ti.a;
                    }
                });
                return;
        }
    }

    public /* synthetic */ qa(MainActivity mainActivity, int i, Bitmap bitmap, FrameLayout frameLayout, String str, RectF rectF) {
        this.b = mainActivity;
        this.c = i;
        this.f = bitmap;
        this.d = frameLayout;
        this.e = str;
        this.g = rectF;
    }

    public /* synthetic */ qa(MainActivity mainActivity, File file, List list, int i, FrameLayout frameLayout, String str) {
        this.b = mainActivity;
        this.f = file;
        this.g = list;
        this.c = i;
        this.d = frameLayout;
        this.e = str;
    }

    public /* synthetic */ qa(MainActivity mainActivity, String str, ComponentName componentName, ComponentName componentName2, Rect rect, int i) {
        this.b = mainActivity;
        this.e = str;
        this.f = componentName;
        this.d = componentName2;
        this.g = rect;
        this.c = i;
    }
}
