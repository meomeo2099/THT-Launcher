package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.os.SystemClock;
import android.util.LruCache;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class md {
    public static volatile ArrayList d;
    public static volatile LinkedHashMap e;
    public static volatile LinkedHashMap f;
    public static boolean g;
    public static Location h;
    public static long i;
    public static final md a = new md();
    public static final me b = new me(1);
    public static final ExecutorService c = Executors.newSingleThreadExecutor();
    public static final fi j = new fi(new j(11));

    public static ld a(Context context) {
        ArrayList arrayList;
        context.getClass();
        int i2 = 0;
        Object obj = null;
        String string = context.getSharedPreferences("panel", 0).getString("radio_city", null);
        if (string == null || (arrayList = d) == null) {
            return null;
        }
        int size = arrayList.size();
        while (i2 < size) {
            Object obj2 = arrayList.get(i2);
            i2++;
            ld ldVar = (ld) obj2;
            if ((ldVar.a + "|" + ldVar.b).equals(string)) {
                obj = obj2;
                break;
            }
        }
        return (ld) obj;
    }

    public static String b(String str) {
        str.getClass();
        String strS = ci.s(ci.s(ci.s(f(str), "фм", "fm"), "радіо", "radio"), "радио", "radio");
        return (!strS.startsWith("radio") || strS.length() - 5 < 3) ? strS : strS.substring(5);
    }

    public static String f(String str) {
        str.getClass();
        String lowerCase = str.toLowerCase(Locale.ROOT);
        lowerCase.getClass();
        String strReplace = lowerCase.replace((char) 1105, (char) 1077);
        strReplace.getClass();
        StringBuilder sb = new StringBuilder();
        int length = strReplace.length();
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = strReplace.charAt(i2);
            if (Character.isLetterOrDigit(cCharAt)) {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }

    public final void c(Context context) {
        context.getClass();
        Context applicationContext = context.getApplicationContext();
        synchronized (this) {
            if (g) {
                return;
            }
            int i2 = 1;
            g = true;
            c.execute(new n(applicationContext, i2));
        }
    }

    public final Bitmap d(Context context, List list) {
        Object ffVar;
        context.getClass();
        if (e == null) {
            c(context);
            return null;
        }
        n5 n5Var = new n5(wf.Q0(new o5(new y0(1, list), true, new w(23)), new w(24)));
        String str = (String) (!n5Var.hasNext() ? null : n5Var.next());
        if (str != null) {
            Bitmap bitmap = (Bitmap) ((LruCache) j.a()).get(str);
            if (bitmap != null) {
                return bitmap;
            }
            try {
                InputStream inputStreamOpen = context.getAssets().open("radio/logos/".concat(str));
                try {
                    ffVar = BitmapFactory.decodeStream(inputStreamOpen);
                    k8.p(inputStreamOpen, null);
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        k8.p(inputStreamOpen, th);
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                ffVar = new ff(th3);
            }
            if (ffVar instanceof ff) {
                ffVar = null;
            }
            Bitmap bitmap2 = (Bitmap) ffVar;
            if (bitmap2 != null) {
                ((LruCache) j.a()).put(str, bitmap2);
                return bitmap2;
            }
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0025  */
    public final String e(Context context, String str, String str2) {
        Double dO;
        Integer numValueOf;
        Location location;
        Object next;
        Object ffVar;
        Object lastKnownLocation;
        Location location2;
        Location location3;
        float[] fArr;
        context.getClass();
        str2.getClass();
        ArrayList<ld> arrayList = d;
        if (arrayList == null) {
            c(context);
            return "";
        }
        String str3 = null;
        if (str.equals("FM") || str.equals("AM")) {
            Set set = nb.l;
            String strZ = pi.z(str, str2);
            if (strZ == null || (dO = ai.o(strZ)) == null) {
                numValueOf = null;
            } else {
                double dDoubleValue = dO.doubleValue();
                if (!str.equals("AM")) {
                    dDoubleValue *= 1000.0d;
                }
                numValueOf = Integer.valueOf(k8.n0(dDoubleValue));
            }
        } else {
            numValueOf = null;
        }
        if (numValueOf == null) {
            return "";
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long j2 = i;
        if (j2 == 0 || jElapsedRealtime - j2 >= 300000) {
            if (context.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0) {
                LocationManager locationManager = (LocationManager) context.getSystemService(LocationManager.class);
                List<String> listI0 = j3.i0("gps", "network", "passive");
                ArrayList arrayList2 = new ArrayList();
                for (String str4 : listI0) {
                    if (locationManager != null) {
                        try {
                            lastKnownLocation = locationManager.getLastKnownLocation(str4);
                        } catch (Throwable th) {
                            ffVar = new ff(th);
                        }
                    } else {
                        lastKnownLocation = null;
                    }
                    ffVar = lastKnownLocation;
                    if (ffVar instanceof ff) {
                        ffVar = null;
                    }
                    Location location4 = (Location) ffVar;
                    if (location4 != null) {
                        arrayList2.add(location4);
                    }
                }
                Iterator it = arrayList2.iterator();
                if (it.hasNext()) {
                    next = it.next();
                    if (it.hasNext()) {
                        long time = ((Location) next).getTime();
                        do {
                            Object next2 = it.next();
                            long time2 = ((Location) next2).getTime();
                            if (time < time2) {
                                next = next2;
                                time = time2;
                            }
                        } while (it.hasNext());
                    }
                } else {
                    next = null;
                }
                location = (Location) next;
            } else {
                location = null;
            }
            h = location;
            i = jElapsedRealtime;
        } else {
            location = h;
        }
        ld ldVarA = a(context);
        if (ldVarA != null) {
            location2 = new Location("chosen");
            location2.setLatitude(ldVarA.c);
            location2.setLongitude(ldVarA.d);
        } else {
            location2 = null;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("panel", 0);
        if (sharedPreferences.contains("weather_lat")) {
            location3 = new Location("kept");
            location3.setLatitude(sharedPreferences.getFloat("weather_lat", 0.0f));
            location3.setLongitude(sharedPreferences.getFloat("weather_lon", 0.0f));
        } else {
            location3 = null;
        }
        for (Location location5 : x0.S0(new Location[]{location, location2, location3})) {
            location5.getClass();
            float[] fArr2 = new float[1];
            String str5 = "";
            float f2 = 150000.0f;
            for (ld ldVar : arrayList) {
                String str6 = (String) ldVar.e.get(numValueOf);
                if (str6 == null) {
                    fArr = fArr2;
                } else {
                    fArr = fArr2;
                    Location.distanceBetween(location5.getLatitude(), location5.getLongitude(), ldVar.c, ldVar.d, fArr);
                    float f3 = fArr[0];
                    if (f3 < f2) {
                        f2 = f3;
                        fArr2 = fArr;
                        str5 = str6;
                    }
                }
                fArr2 = fArr;
            }
            if (str5.length() > 0) {
                str3 = str5;
                break;
            }
        }
        return str3 == null ? "" : str3;
    }
}
