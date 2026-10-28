package defpackage;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInstaller;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.net.Uri;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;
import org.reference.panel.MainActivity;
import org.reference.panel.UpdateApkProvider;
import org.reference.panel.UpdateInstallReceiver;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class l0 implements n6, j6 {
    public static final l0 a = new l0();
    public static final l0 b = new l0();
    public static final l0 c = new l0();
    public static final l0 d = new l0();
    public static final l0 e = new l0();

    /* JADX WARN: Code duplicated, block: B:43:0x0129  */
    /* JADX WARN: Code duplicated, block: B:45:0x0130  */
    public static String A(String str) {
        String str2;
        str.getClass();
        if (!str.startsWith("seat_heat_") && !str.startsWith("seat_vent_")) {
            String str3 = (String) hb.j0(new yc("steering", "wheel-heat"), new yc("roof", "sunroof-open"), new yc("curtain", "sun-curtain"), new yc("nozzle_heat", "nozzle-heat"), new yc("wiper_heat", "wiper-heat"), new yc("drive_eco", "eco"), new yc("drive_comfort", "comfort"), new yc("drive_sport", "sport"), new yc("drive_sport_plus", "sport-plus"), new yc("drive_power", "power-mode"), new yc("drive_snow", "snow-mode"), new yc("drive_offroad", "offroad"), new yc("drive_adaptive", "adaptive"), new yc("start_stop", "start-stop"), new yc("battery", "battery"), new yc("fuel", "fuel"), new yc("ambient", "ambient")).get(str);
            if (str3 != null) {
                return str3;
            }
            String strReplace = str.replace('_', '-');
            strReplace.getClass();
            return strReplace;
        }
        String strE = th.E(str, '_');
        int iHashCode = strE.hashCode();
        if (iHashCode != 49) {
            if (iHashCode != 52) {
                if (iHashCode != 1573) {
                    if (iHashCode != 1631) {
                        if (iHashCode != 1726 || !strE.equals("64")) {
                            return null;
                        }
                    } else if (!strE.equals("32")) {
                        return null;
                    }
                } else if (!strE.equals("16")) {
                    return null;
                }
            } else if (!strE.equals("4")) {
                return null;
            }
            str2 = "right";
            return str.startsWith("seat_heat_") ? "seat-heat-".concat(str2) : "seat-vent-".concat(str2);
        }
        if (!strE.equals("1")) {
            return null;
        }
        str2 = "left";
        if (str.startsWith("seat_heat_")) {
        }
    }

    public static String B(uj ujVar, boolean z) {
        ujVar.getClass();
        switch (ujVar.ordinal()) {
            case 0:
                return z ? "weather-clear-night" : "weather-clear-day";
            case 1:
                return z ? "weather-partly-cloudy-night" : "weather-partly-cloudy";
            case 2:
            case 8:
                return "weather-cloudy";
            case 3:
                return "weather-rain";
            case 4:
                return "weather-snow";
            case 5:
                return "weather-sleet";
            case 6:
                return "weather-fog";
            case 7:
                return "weather-thunder";
            default:
                throw new vc();
        }
    }

    public static final ArrayList a(double[] dArr) {
        ArrayList arrayList = new ArrayList();
        int length = dArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            double d2 = dArr[i];
            int i3 = i2 + 1;
            if (i2 % 2 == 1) {
                arrayList.add(Double.valueOf(d2));
            }
            i++;
            i2 = i3;
        }
        return arrayList;
    }

    public static final ArrayList d(double[] dArr) {
        ArrayList arrayList = new ArrayList();
        int length = dArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            double d2 = dArr[i];
            int i3 = i2 + 1;
            if (i2 % 2 == 0) {
                arrayList.add(Double.valueOf(d2));
            }
            i++;
            i2 = i3;
        }
        return arrayList;
    }

    public static final boolean e(double[] dArr, double d2, double d3) {
        int length = dArr.length / 2;
        int i = length - 1;
        boolean z = false;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            double d4 = dArr[i3];
            double d5 = dArr[i3 + 1];
            int i4 = i * 2;
            double d6 = dArr[i4];
            double d7 = dArr[i4 + 1];
            if ((d5 > d3) != (d7 > d3)) {
                if (d2 < (((d3 - d5) * (d6 - d4)) / (d7 - d5)) + d4) {
                    z = !z;
                }
            }
            i = i2;
        }
        return z;
    }

    public static byte[] f(int i, String str) throws IOException {
        HttpURLConnection httpURLConnectionU = u(str);
        try {
            if (httpURLConnectionU.getResponseCode() != 200) {
                throw new IllegalStateException(("HTTP " + httpURLConnectionU.getResponseCode()).toString());
            }
            InputStream inputStream = httpURLConnectionU.getInputStream();
            try {
                inputStream.getClass();
                byte[] bArrM = pi.M(inputStream);
                inputStream.close();
                if (bArrM.length > i) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                httpURLConnectionU.disconnect();
                return bArrM;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    k8.p(inputStream, th);
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            httpURLConnectionU.disconnect();
            throw th3;
        }
    }

    /*  JADX ERROR: JadxOverflowException in pass: LoopRegionVisitor
        jadx.core.utils.exceptions.JadxOverflowException: LoopRegionVisitor.assignOnlyInLoop endless recursion
            at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static java.io.File g(android.content.Context r19, defpackage.k0 r20, defpackage.q0 r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 468
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.l0.g(android.content.Context, k0, q0):java.io.File");
    }

    public static void h(Canvas canvas, RectF rectF, float f, boolean z, Paint paint, boolean z2, int i, int i2, boolean z3, View view) {
        int iRgb;
        int i3;
        int i4;
        int i5;
        boolean z4;
        int i6;
        int iArgb;
        rectF.getClass();
        paint.getClass();
        if (rectF.isEmpty() || i2 == 0) {
            return;
        }
        boolean z5 = x5.a;
        if (!x5.a || z2) {
            if (z) {
                if (z3) {
                    i3 = 53;
                    i4 = 66;
                    i5 = 46;
                } else {
                    i3 = 38;
                    i4 = 49;
                    i5 = 32;
                }
                iRgb = Color.rgb(i5, i3, i4);
            } else {
                iRgb = z3 ? Color.rgb(248, 250, 253) : Color.rgb(222, 228, 237);
            }
            paint.setShader(null);
            paint.setStyle(Paint.Style.FILL);
            if (z2 && !z) {
                int i7 = l8.a;
                iRgb = Color.rgb(37, 99, 235);
            } else if (z2) {
                int i8 = l8.a;
                float fR = pi.r(0.18f, 0.0f, 1.0f);
                float f2 = 1.0f - fR;
                iRgb = Color.rgb(Math.round((Color.red(iRgb) * f2) + (Color.red(i) * fR)), Math.round((Color.green(iRgb) * f2) + (Color.green(i) * fR)), Math.round((f2 * Color.blue(iRgb)) + (Color.blue(i) * fR)));
            }
            paint.setColor(iRgb);
            paint.setAlpha(i2);
            canvas.drawRoundRect(rectF, f, f, paint);
            paint.setAlpha(255);
            return;
        }
        BitmapShader bitmapShader = x5.c;
        Bitmap bitmap = x5.b;
        View view2 = (View) x5.e.get();
        if (bitmapShader != null && bitmap != null && view2 != null && view != null && view2.getWidth() > 0 && view.getRootView() == view2.getRootView()) {
            Object parent = view.getParent();
            while (true) {
                if (!(parent instanceof View)) {
                    z4 = true;
                    break;
                } else {
                    if ((parent instanceof ScrollView) || ((parent instanceof HorizontalScrollView) && !(parent instanceof v8))) {
                        z4 = false;
                        break;
                    }
                    parent = ((View) parent).getParent();
                }
            }
        } else {
            z4 = false;
            break;
        }
        if (z4) {
            view.getClass();
            int[] iArr = x5.h;
            view.getLocationInWindow(iArr);
            view2.getClass();
            int[] iArr2 = x5.i;
            view2.getLocationInWindow(iArr2);
            Matrix matrix = x5.f;
            float width = view2.getWidth();
            bitmap.getClass();
            matrix.setScale(width / bitmap.getWidth(), view2.getHeight() / bitmap.getHeight());
            matrix.postTranslate(iArr2[0] - iArr[0], iArr2[1] - iArr[1]);
            bitmapShader.getClass();
            bitmapShader.setLocalMatrix(matrix);
            Paint paint2 = x5.g;
            paint2.setShader(bitmapShader);
            paint2.setAlpha(i2);
            canvas.drawRoundRect(rectF, f, f, paint2);
            paint2.setShader(null);
        }
        paint.setShader(null);
        paint.setStyle(Paint.Style.FILL);
        if (z4 || view == null) {
            i6 = 255;
            iArgb = Color.argb(51, 255, 255, 255);
        } else if (z) {
            iArgb = Color.argb(170, 40, 48, 60);
            i6 = 255;
        } else {
            i6 = 255;
            iArgb = Color.argb(200, 250, 252, 255);
        }
        paint.setColor(iArgb);
        paint.setAlpha((paint.getAlpha() * i2) / i6);
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setAlpha(i6);
    }

    public static /* synthetic */ void i(Canvas canvas, RectF rectF, float f, boolean z, Paint paint, boolean z2, int i, int i2, boolean z3, View view, int i3) {
        if ((i3 & 32) != 0) {
            z2 = false;
        }
        if ((i3 & 64) != 0) {
            l8.h();
            i = -10233877;
        }
        if ((i3 & 128) != 0) {
            i2 = 255;
        }
        if ((i3 & 256) != 0) {
            z3 = false;
        }
        if ((i3 & 512) != 0) {
            view = null;
        }
        h(canvas, rectF, f, z, paint, z2, i, i2, z3, view);
    }

    public static boolean j(Canvas canvas, String str, String str2, int i, float f, float f2, float f3) {
        ba baVar = ba.k;
        return baVar != null && baVar.b(canvas, c.d(str, "-", str2), i, f, f2, f3);
    }

    public static boolean k(MainActivity mainActivity) {
        return mainActivity.getSharedPreferences("panel", 0).getBoolean("update_auto", true);
    }

    public static j4 m(l0 l0Var, j4 j4Var) {
        float fR = pi.r(j4Var.c, 1.0f, 1280.0f);
        float fR2 = pi.r(j4Var.d, 1.0f, 720.0f);
        return new j4(pi.r(j4Var.a, 0.0f, 1280.0f - fR), pi.r(j4Var.b, 0.0f, 720.0f - fR2), fR, fR2);
    }

    public static String n() {
        Object ffVar;
        try {
            Object objInvoke = Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, "sys.media.focus");
            ffVar = objInvoke instanceof String ? (String) objInvoke : null;
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        if (ffVar instanceof ff) {
            ffVar = null;
        }
        String str = (String) ffVar;
        if (str != null) {
            return th.J(str).toString();
        }
        return null;
    }

    public static boolean o(Context context) {
        Object ffVar;
        try {
            ffVar = context.getPackageManager().getActivityInfo(new ComponentName("com.teto.inputservice", "com.teto.inputservice.autoboot.ApkInstallActivity"), 0);
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        return !(ffVar instanceof ff);
    }

    public static void r(Context context, File file) throws IllegalAccessException, IOException, InvocationTargetException {
        file.getClass();
        PackageInstaller packageInstaller = context.getPackageManager().getPackageInstaller();
        packageInstaller.getClass();
        PackageInstaller.SessionParams sessionParams = new PackageInstaller.SessionParams(1);
        sessionParams.setAppPackageName(context.getPackageName());
        sessionParams.setSize(file.length());
        int iCreateSession = packageInstaller.createSession(sessionParams);
        PackageInstaller.Session sessionOpenSession = packageInstaller.openSession(iCreateSession);
        try {
            OutputStream outputStreamOpenWrite = sessionOpenSession.openWrite("base.apk", 0L, file.length());
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    outputStreamOpenWrite.getClass();
                    pi.u(fileInputStream, outputStreamOpenWrite);
                    fileInputStream.close();
                    sessionOpenSession.fsync(outputStreamOpenWrite);
                    outputStreamOpenWrite.close();
                    Intent action = new Intent(context, (Class<?>) UpdateInstallReceiver.class).setAction("org.reference.panel.UPDATE_STATUS");
                    action.getClass();
                    sessionOpenSession.commit(PendingIntent.getBroadcast(context, iCreateSession, action, 134217728).getIntentSender());
                    sessionOpenSession.close();
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        k8.p(fileInputStream, th);
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                try {
                    throw th3;
                } catch (Throwable th4) {
                    k8.p(outputStreamOpenWrite, th3);
                    throw th4;
                }
            }
        } catch (Throwable th5) {
            try {
                throw th5;
            } catch (Throwable th6) {
                k8.p(sessionOpenSession, th5);
                throw th6;
            }
        }
    }

    public static void s(Context context, File file) {
        file.getClass();
        if (!file.isFile()) {
            w.a("no file");
            return;
        }
        if (!o(context)) {
            throw new IllegalStateException(si.a("update_freetuga_missing", new Object[0]));
        }
        int i = UpdateApkProvider.a;
        Uri uri = Uri.parse("content://" + context.getPackageName() + ".update/ReferencePanel.apk");
        uri.getClass();
        context.grantUriPermission(new ComponentName("com.teto.inputservice", "com.teto.inputservice.autoboot.ApkInstallActivity").getPackageName(), uri, 1);
        context.startActivity(new Intent("android.intent.action.VIEW").setComponent(new ComponentName("com.teto.inputservice", "com.teto.inputservice.autoboot.ApkInstallActivity")).setDataAndType(uri, "application/vnd.android.package-archive").addFlags(268435457));
    }

    public static int t(MainActivity mainActivity) {
        SharedPreferences sharedPreferences = mainActivity.getSharedPreferences("panel", 0);
        if (sharedPreferences.contains("update_installer")) {
            return sharedPreferences.getInt("update_installer", 0);
        }
        return o(mainActivity) ? 1 : 0;
    }

    public static HttpURLConnection u(String str) throws IOException {
        URLConnection uRLConnectionOpenConnection = new URL(str).openConnection();
        uRLConnectionOpenConnection.getClass();
        HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setReadTimeout(30000);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setRequestProperty("User-Agent", "ReferencePanel/1.0.2");
        return httpURLConnection;
    }

    public static xe v(InputStreamReader inputStreamReader) throws IllegalAccessException, IOException, InvocationTargetException {
        ArrayList arrayList = new ArrayList();
        me meVar = new me(0);
        ArrayList arrayList2 = new ArrayList();
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader, 8192);
        o0 o0Var = new o0(meVar, arrayList2, arrayList, 5);
        try {
            Iterator it = new s3(new y0(3, bufferedReader)).iterator();
            while (it.hasNext()) {
                o0Var.b(it.next());
            }
            bufferedReader.close();
            double[] dArr = (double[]) meVar.b;
            if (dArr != null) {
                arrayList.add(new we(dArr, i3.J0(arrayList2)));
            }
            meVar.b = null;
            arrayList2.clear();
            return new xe(arrayList);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                k8.p(bufferedReader, th);
                throw th2;
            }
        }
    }

    public static boolean y(l0 l0Var, byte[] bArr, String str) {
        Object ffVar;
        PublicKey publicKey = CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(Base64.getDecoder().decode("REDACTED_ENCODED_IDENTITY"))).getPublicKey();
        publicKey.getClass();
        try {
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(publicKey);
            signature.update(bArr);
            ffVar = Boolean.valueOf(signature.verify(Base64.getDecoder().decode(th.J(str).toString())));
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        Object obj = Boolean.FALSE;
        if (ffVar instanceof ff) {
            ffVar = obj;
        }
        return ((Boolean) ffVar).booleanValue();
    }

    public static final float z(float f) {
        float fRint = ((float) Math.rint(f / 20.0f)) * 20.0f;
        if (fRint < 0.0f) {
            return 0.0f;
        }
        return fRint;
    }

    @Override // defpackage.j6
    public Object b(Object obj) {
        ((String) obj).getClass();
        return null;
    }

    @Override // defpackage.n6
    public Object c(Object obj, Object obj2) throws IOException {
        IOException iOException = (IOException) obj2;
        ((File) obj).getClass();
        iOException.getClass();
        throw iOException;
    }

    public k0 l() throws JSONException, IOException {
        byte[] bArrF = f(65536, "https://example.invalid/reference-releases/releases/latest/download/update.json");
        if (!y(this, bArrF, new String(f(8192, "https://example.invalid/reference-releases/releases/latest/download/update.json.sig"), q2.c))) {
            throw new SecurityException("bad signature");
        }
        JSONObject jSONObject = new JSONObject(new String(bArrF, q2.a));
        int i = jSONObject.getInt("versionCode");
        String string = jSONObject.getString("versionName");
        string.getClass();
        String string2 = jSONObject.getString("url");
        string2.getClass();
        String strOptString = jSONObject.optString("mirror");
        String str = th.A(strOptString) ? null : strOptString;
        String string3 = jSONObject.getString("sha256");
        string3.getClass();
        String lowerCase = string3.toLowerCase(Locale.ROOT);
        lowerCase.getClass();
        long j = jSONObject.getLong("size");
        String strOptString2 = jSONObject.optString("notes");
        strOptString2.getClass();
        k0 k0Var = new k0(i, string, string2, str, lowerCase, j, strOptString2);
        if (i > 16) {
            return k0Var;
        }
        return null;
    }

    public r4 p(Context context) {
        r4 r4Var;
        Object ffVar;
        context.getClass();
        if (!new File("/system/framework/AdapterAPIImpl.jar").isFile() && !new File("/system/framework/AdapterAPI.jar").isFile()) {
            try {
                ffVar = Class.forName("com.ecarx.xui.adaptapi.car.impl.CarImpl", false, context.getClassLoader());
            } catch (Throwable th) {
                ffVar = new ff(th);
            }
            if (ffVar instanceof ff) {
                return null;
            }
        }
        r4 r4Var2 = r4.D;
        if (r4Var2 != null) {
            return r4Var2;
        }
        synchronized (this) {
            r4Var = r4.D;
            if (r4Var == null) {
                Context applicationContext = context.getApplicationContext();
                applicationContext.getClass();
                r4Var = new r4(applicationContext);
                r4.D = r4Var;
                Thread thread = new Thread(new k1(4, r4Var), "ReferencePanel-ecarx");
                thread.setDaemon(true);
                thread.start();
            }
        }
        return r4Var;
    }

    public void q(ContextWrapper contextWrapper) {
        if (ba.k == null) {
            synchronized (this) {
                if (ba.k == null) {
                    ba baVar = new ba(contextWrapper);
                    baVar.c(contextWrapper);
                    ba.k = baVar;
                }
            }
        }
    }

    public j4 w(j4 j4Var, float f) {
        j4Var.getClass();
        return m(this, j4.a(j4Var, 0.0f, 0.0f, j4Var.c * f, j4Var.d * f, 3));
    }

    public fe x(Context context) {
        fe feVar;
        context.getClass();
        fe feVar2 = fe.p;
        if (feVar2 != null) {
            return feVar2;
        }
        synchronized (this) {
            feVar = fe.p;
            if (feVar == null) {
                feVar = new fe(context);
                fe.p = feVar;
            }
        }
        return feVar;
    }
}
