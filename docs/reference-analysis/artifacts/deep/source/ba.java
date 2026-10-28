package defpackage;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class ba {
    public static volatile ba k;
    public static volatile boolean m;
    public static final List q;
    public static final Set r;
    public static final List s;
    public static final Map t;
    public static final Map u;
    public final AssetManager a;
    public volatile File e;
    public volatile boolean f;
    public static final l0 h = new l0();
    public static final me i = new me("[a-z0-9-]+");
    public static final Path j = ei.a("M2 23 L6.5 9 L11 23 M3.7 18 L9.3 18 M13 25 L19 7 M30 11 C23 4 20 11 20 16 C20 23 25 27 30 21");
    public static volatile boolean l = true;
    public static volatile boolean n = true;
    public static final List o = j3.i0("gps", "screensaver", "camera360", "theme-day", "theme-night", "radio");
    public static final LinkedHashMap p = hb.h0(new yc("wifi", j3.i0("off", "on", "connected")), new yc("bluetooth", j3.i0("off", "on", "connected")), new yc("gps", j3.i0("off", "on")));
    public final aa b = new aa(4194304);
    public final LinkedHashSet c = new LinkedHashSet();
    public final Paint d = new Paint(3);
    public final Set g = Collections.synchronizedSet(new LinkedHashSet());

    static {
        uj ujVar = uj.a;
        Boolean bool = Boolean.FALSE;
        yc ycVar = new yc(ujVar, bool);
        Boolean bool2 = Boolean.TRUE;
        yc ycVar2 = new yc(ujVar, bool2);
        uj ujVar2 = uj.b;
        q = j3.i0(ycVar, ycVar2, new yc(ujVar2, bool), new yc(ujVar2, bool2), new yc(uj.c, bool), new yc(uj.g, bool), new yc(uj.d, bool), new yc(uj.f, bool), new yc(uj.e, bool), new yc(uj.h, bool));
        r = x0.Y0(new String[]{"cruise", "airflow", "steering", "roof", "roof_tilt", "curtain", "nozzle_heat", "wiper_heat", "drive_sand", "trunk", "mirrors", "ext_light", "wipers"});
        s = j3.i0("shortcut", "album");
        t = hb.j0(new yc("menu", "apps"), new yc("grid", "apps"), new yc("gear", "settings"), new yc("home", "home"), new yc("favorite", "favorite-off"), new yc("fan", "fan"), new yc("roofslider", "sunroof-open"), new yc("nav", "navigation"), new yc("navfill", "navigation-active"), new yc("music", "music"), new yc("phone", "phone"), new yc("snow", "ac"), new yc("auto", "auto"), new yc("recirc", "recirc"), new yc("rear", "rear-defrost"), new yc("ss", "start-stop"), new yc("ambient", "ambient"), new yc("leaf", "eco"), new yc("comfort", "comfort"), new yc("sport", "sport"), new yc("sportplus", "sport-plus"), new yc("snowmode", "snow-mode"), new yc("offroad", "offroad"), new yc("adaptive", "adaptive"), new yc("collapse", "expand"), new yc("expand", "collapse"), new yc("bt", "bluetooth"), new yc("wifi", "wifi"), new yc("globe", "globe"), new yc("chev", "chevron-right"), new yc("chevup", "chevron-up"), new yc("chevdown", "chevron-down"), new yc("minus", "minus"), new yc("plus", "plus"), new yc("clock", "clock"), new yc("power", "power"), new yc("bolt", "power-mode"), new yc("front", "front-defrost"), new yc("close", "close"), new yc("sun", "brightness"), new yc("speaker", "speaker"), new yc("carfront", "range"), new yc("batt", "battery"), new yc("pump", "fuel"), new yc("speaker_off", "muted"));
        u = hb.j0(new yc("pause", "pause"), new yc("prev", "previous"), new yc("next", "next"), new yc("heart", "favorite-off"), new yc("liked", "favorite-on"), new yc("play", "play"));
    }

    public ba(ContextWrapper contextWrapper) {
        this.a = contextWrapper.getApplicationContext().getAssets();
    }

    public final boolean a(Canvas canvas, String str, int i2, float f, float f2, float f3) throws IllegalAccessException, InvocationTargetException {
        canvas.getClass();
        if (!m && f3 > 0.0f && Math.abs(f3) <= Float.MAX_VALUE && i.a(str)) {
            if (b(canvas, str, i2, f, f2, f3)) {
                return true;
            }
            if (str.equals("ac")) {
                Paint paint = this.d;
                int iSave = canvas.save();
                canvas.translate(f, f2);
                float f4 = f3 / 32.0f;
                canvas.scale(f4, f4);
                paint.reset();
                paint.setAntiAlias(true);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeCap(Paint.Cap.ROUND);
                paint.setStrokeJoin(Paint.Join.ROUND);
                paint.setStrokeWidth(1.65f);
                paint.setColor(i2);
                canvas.drawPath(j, paint);
                paint.setStyle(Paint.Style.FILL);
                canvas.restoreToCount(iSave);
                return true;
            }
            if (l && !this.c.contains(str)) {
                Bitmap bitmap = (Bitmap) this.b.get(str);
                if (bitmap == null) {
                    try {
                        InputStream inputStreamOpen = this.a.open("launcher_icons/" + str + ".png");
                        try {
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inScaled = false;
                            Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpen, null, options);
                            k8.p(inputStreamOpen, null);
                            if (bitmapDecodeStream == null) {
                                this.c.add(str);
                                return false;
                            }
                            this.b.put(str, bitmapDecodeStream);
                            bitmap = bitmapDecodeStream;
                        } catch (Throwable th) {
                            try {
                                throw th;
                            } catch (Throwable th2) {
                                k8.p(inputStreamOpen, th);
                                throw th2;
                            }
                        }
                    } catch (IOException unused) {
                        this.c.add(str);
                        return false;
                    }
                }
                this.d.setColorFilter(new PorterDuffColorFilter(i2, PorterDuff.Mode.SRC_IN));
                canvas.drawBitmap(bitmap, (Rect) null, new RectF(f, f2, f + f3, f3 + f2), this.d);
                return true;
            }
        }
        return false;
    }

    public final boolean b(Canvas canvas, String str, int i2, float f, float f2, float f3) {
        Bitmap bitmapD;
        canvas.getClass();
        str.getClass();
        if (m || f3 <= 0.0f || Math.abs(f3) > Float.MAX_VALUE || !i.a(str)) {
            return false;
        }
        Bitmap bitmapD2 = n ? d(str.concat("-color-night")) : null;
        if (bitmapD2 == null) {
            bitmapD2 = d(str.concat("-color"));
        }
        if (bitmapD2 == null) {
            bitmapD = d(str);
            if (bitmapD == null) {
                return false;
            }
        } else {
            bitmapD = bitmapD2;
        }
        this.d.setColorFilter((this.f || bitmapD2 != null) ? null : new PorterDuffColorFilter(i2, PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmapD, (Rect) null, new RectF(f, f2, f + f3, f3 + f2), this.d);
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0045  */
    public final void c(Context context) {
        boolean z;
        context.getClass();
        String strA = i7.a(context);
        Object obj = null;
        this.e = strA.length() == 0 ? null : i7.b(context, strA);
        int i2 = 0;
        if (strA.length() > 0) {
            for (Object obj2 : i7.e(context)) {
                if (((d7) obj2).a.equals(strA)) {
                    obj = obj2;
                    break;
                }
            }
            d7 d7Var = (d7) obj;
            if (d7Var != null) {
                z = d7Var.d;
            }
        }
        this.f = z;
        this.g.clear();
        Set setKeySet = this.b.snapshot().keySet();
        ArrayList arrayList = new ArrayList();
        for (Object obj3 : setKeySet) {
            String str = (String) obj3;
            str.getClass();
            if (str.startsWith("set:")) {
                arrayList.add(obj3);
            }
        }
        int size = arrayList.size();
        while (i2 < size) {
            Object obj4 = arrayList.get(i2);
            i2++;
            this.b.remove((String) obj4);
        }
    }

    public final Bitmap d(String str) {
        Object ffVar;
        Bitmap bitmap;
        File file = this.e;
        if (file != null) {
            String str2 = "set:" + file.getName() + "/" + str;
            Bitmap bitmap2 = (Bitmap) this.b.get(str2);
            if (bitmap2 != null) {
                return bitmap2;
            }
            if (!this.g.contains(str2)) {
                File file2 = new File(file, str + ".png");
                if (file2.isFile()) {
                    try {
                        ffVar = BitmapFactory.decodeFile(file2.getAbsolutePath());
                    } catch (Throwable th) {
                        ffVar = new ff(th);
                    }
                    if (ffVar instanceof ff) {
                        ffVar = null;
                    }
                    bitmap = (Bitmap) ffVar;
                } else {
                    bitmap = null;
                }
                if (bitmap == null) {
                    this.g.add(str2);
                    return null;
                }
                this.b.put(str2, bitmap);
                return bitmap;
            }
        }
        return null;
    }
}
