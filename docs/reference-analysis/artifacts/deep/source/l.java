package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.os.Handler;
import android.util.AtomicFile;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class l implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Object e;

    public /* synthetic */ l(g0 g0Var, i0 i0Var, e0 e0Var, LinearLayout linearLayout, ImageView imageView) {
        this.a = 1;
        this.b = i0Var;
        this.c = e0Var;
        this.d = linearLayout;
        this.e = imageView;
    }

    /* JADX WARN: Code duplicated, block: B:144:0x0328  */
    @Override // java.lang.Runnable
    public final void run() {
        int i;
        Object ffVar;
        Object ffVar2;
        Throwable cause;
        String message;
        Object ffVar3;
        int i2 = 4;
        Object ffVar4 = null;
        File fileCreateTempFile = null;
        switch (this.a) {
            case 0:
                Context context = (Context) this.b;
                v2 v2Var = (v2) this.c;
                r rVar = (r) this.d;
                r rVar2 = (r) this.e;
                boolean z = q.a;
                rVar2.getClass();
                Float f = rVar.e;
                Float f2 = rVar2.e;
                if (f != null ? !(f2 == null || f.floatValue() != f2.floatValue()) : f2 == null) {
                    i = 1;
                } else if (new r(rVar.a, rVar.b, rVar.c, rVar.d, rVar2.e).equals(rVar2)) {
                    i = 4;
                } else {
                    i = 1;
                }
                q.e(context, v2Var, i, 4);
                return;
            case 1:
                i0 i0Var = (i0) this.b;
                Activity activity = i0Var.a;
                e0 e0Var = (e0) this.c;
                LinearLayout linearLayout = (LinearLayout) this.d;
                ImageView imageView = (ImageView) this.e;
                try {
                    ffVar = e0Var.c.loadIcon(activity.getPackageManager());
                    break;
                } catch (Throwable th) {
                    ffVar = new ff(th);
                }
                activity.runOnUiThread(new b5(i0Var, linearLayout, e0Var, (Drawable) (ffVar instanceof ff ? null : ffVar), imageView, 3));
                return;
            case 2:
                q7 q7Var = (q7) this.b;
                q7Var.animate().alpha(0.0f).setDuration(250L).withEndAction(new r0((WindowManager.LayoutParams) this.c, q7Var, (MainActivity) this.d, (WindowManager) this.e)).start();
                return;
            case 3:
                Context context2 = (Context) this.b;
                SharedPreferences sharedPreferences = (SharedPreferences) this.c;
                Handler handler = (Handler) this.d;
                b2 b2Var = (b2) this.e;
                try {
                    context2.getClass();
                    boolean zB = pi.b(context2);
                    byte[] bArrK = pi.k(262144, "https://example.invalid/reference-releases/releases/download/unlock/unlock.txt");
                    if (!l0.y(l0.a, bArrK, new String(pi.k(8192, "https://example.invalid/reference-releases/releases/download/unlock/unlock.txt.sig"), q2.c))) {
                        throw new SecurityException("bad signature");
                    }
                    sharedPreferences.edit().putString("region_pass_list", new String(bArrK, q2.a)).putLong("region_pass_checked", System.currentTimeMillis()).apply();
                    if (pi.b(context2) != zB) {
                        handler.post(new k1(9, b2Var));
                    }
                    return;
                } catch (Throwable th2) {
                    try {
                        sharedPreferences.edit().putLong("region_pass_checked", System.currentTimeMillis()).apply();
                        th2.getMessage();
                    } finally {
                        pi.d = false;
                    }
                }
                break;
            case 4:
                MainActivity mainActivity = (MainActivity) this.b;
                Uri uri = (Uri) this.c;
                File file = (File) this.d;
                y5 y5Var = (y5) this.e;
                try {
                    fileCreateTempFile = File.createTempFile("vehicle-import-", ".tmp", mainActivity.getCacheDir());
                    InputStream inputStreamOpenInputStream = mainActivity.getContentResolver().openInputStream(uri);
                    if (inputStreamOpenInputStream == null) {
                        throw new IllegalStateException(si.a("file_unavailable", new Object[0]).toString());
                    }
                    try {
                        fileCreateTempFile.getClass();
                        FileOutputStream fileOutputStream = new FileOutputStream(fileCreateTempFile);
                        try {
                            byte[] bArr = new byte[8192];
                            int i3 = 0;
                            while (true) {
                                int i4 = inputStreamOpenInputStream.read(bArr);
                                if (i4 >= 0) {
                                    i3 += i4;
                                    if (i3 > 26214400) {
                                        throw new IllegalArgumentException(si.a("choose_an_image_up_to_25_mb", new Object[0]).toString());
                                    }
                                    fileOutputStream.write(bArr, 0, i4);
                                    try {
                                        throw th;
                                    } catch (Throwable th3) {
                                        k8.p(inputStreamOpenInputStream, th);
                                        throw th3;
                                    }
                                }
                                fileOutputStream.close();
                                inputStreamOpenInputStream.close();
                                Bitmap bitmapDecodeBitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(fileCreateTempFile), new pa(2));
                                bitmapDecodeBitmap.getClass();
                                try {
                                    AtomicFile atomicFile = new AtomicFile(file);
                                    FileOutputStream fileOutputStreamStartWrite = atomicFile.startWrite();
                                    try {
                                        if (!bitmapDecodeBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStreamStartWrite)) {
                                            throw new IllegalStateException("Check failed.");
                                        }
                                        atomicFile.finishWrite(fileOutputStreamStartWrite);
                                        bitmapDecodeBitmap.recycle();
                                        ffVar2 = ti.a;
                                    } catch (Exception e) {
                                        atomicFile.failWrite(fileOutputStreamStartWrite);
                                        throw e;
                                    }
                                } catch (Throwable th4) {
                                    bitmapDecodeBitmap.recycle();
                                    throw th4;
                                }
                                ffVar2 = new ff(th);
                            }
                            if (fileCreateTempFile != null) {
                                fileCreateTempFile.delete();
                            }
                            mainActivity.runOnUiThread(new r0(mainActivity, ffVar2, y5Var, i2));
                            return;
                        } catch (Throwable th5) {
                            try {
                                throw th5;
                            } catch (Throwable th6) {
                                k8.p(fileOutputStream, th5);
                                throw th6;
                            }
                        }
                    } catch (Throwable th7) {
                        throw th7;
                    }
                } catch (Throwable th8) {
                    ffVar2 = new ff(th8);
                }
                break;
            case 5:
                nj njVar = (nj) this.b;
                j6 j6Var = (j6) this.c;
                r4 r4Var = (r4) this.d;
                j6 j6Var2 = (j6) this.e;
                try {
                    if (!((Boolean) j6Var.b(r4Var)).booleanValue()) {
                        ffVar4 = si.a("the_vehicle_did_not_confirm_the_command", new Object[0]);
                        break;
                    }
                } catch (Throwable th9) {
                    ffVar4 = new ff(th9);
                }
                Throwable thA = gf.a(ffVar4);
                Object obj = ffVar4;
                if (thA != null && ((cause = thA.getCause()) == null || (message = cause.getMessage()) == null)) {
                    obj = message;
                    String message2 = thA.getMessage();
                    if (message2 == null) {
                        message2 = si.a("command_failed", new Object[0]);
                    }
                    obj = message2;
                }
                obj = message;
                njVar.c.post(new r0(j6Var2, (String) obj, njVar, 6));
                return;
            default:
                vj vjVar = (vj) this.b;
                String str = (String) this.c;
                String str2 = (String) this.d;
                Location location = (Location) this.e;
                try {
                    URLConnection uRLConnectionOpenConnection = new URL("https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=" + str + "&lon=" + str2).openConnection();
                    uRLConnectionOpenConnection.getClass();
                    HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
                    httpURLConnection.setConnectTimeout(10000);
                    httpURLConnection.setReadTimeout(15000);
                    httpURLConnection.setRequestProperty("User-Agent", "ReferencePanel/0.5 example.invalid/reference-source/launch");
                    httpURLConnection.setRequestProperty("Accept", "application/json");
                    try {
                        if (httpURLConnection.getResponseCode() != 200) {
                            throw new IllegalStateException(("HTTP " + httpURLConnection.getResponseCode()).toString());
                        }
                        InputStream inputStream = httpURLConnection.getInputStream();
                        inputStream.getClass();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, q2.a), 8192);
                        try {
                            String strL = sh.l(bufferedReader);
                            bufferedReader.close();
                            ffVar3 = vj.c(strL);
                            httpURLConnection.disconnect();
                            vjVar.d.post(new r0(vjVar, ffVar3, location, 7));
                            return;
                        } catch (Throwable th10) {
                            try {
                                throw th10;
                            } catch (Throwable th11) {
                                k8.p(bufferedReader, th10);
                                throw th11;
                            }
                        }
                    } catch (Throwable th12) {
                        httpURLConnection.disconnect();
                        throw th12;
                    }
                } catch (Throwable th13) {
                    ffVar3 = new ff(th13);
                }
                break;
        }
    }

    public /* synthetic */ l(Object obj, Object obj2, Object obj3, Object obj4, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
        this.d = obj3;
        this.e = obj4;
    }
}
