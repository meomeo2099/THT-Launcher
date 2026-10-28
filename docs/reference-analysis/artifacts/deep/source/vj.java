package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class vj {
    public static final String[] k = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
    public final Context a;
    public final v1 b;
    public final SharedPreferences c;
    public final Handler d;
    public final ExecutorService e;
    public boolean f;
    public x6 g;
    public volatile boolean h;
    public wj i;
    public final r1 j;

    public vj(Context context, v1 v1Var) {
        wj wjVar;
        this.a = context;
        this.b = v1Var;
        SharedPreferences sharedPreferences = context.getSharedPreferences("panel", 0);
        this.c = sharedPreferences;
        this.d = new Handler(Looper.getMainLooper());
        this.e = Executors.newSingleThreadExecutor();
        long j = sharedPreferences.getLong("weather_time", 0L);
        if (j == 0 || System.currentTimeMillis() - j > 10800000) {
            wjVar = new wj((Float) null, (String) null, 0L, 15);
        } else {
            Float fValueOf = Float.valueOf(sharedPreferences.getFloat("weather_temp", 0.0f));
            String string = sharedPreferences.getString("weather_symbol", "");
            wjVar = new wj(fValueOf, string == null ? "" : string, j, 8);
        }
        this.i = wjVar;
        this.j = new r1(15, this);
    }

    public static yc c(String str) throws JSONException {
        String str2;
        JSONObject jSONObjectOptJSONObject;
        String strOptString;
        JSONObject jSONObject = new JSONObject(str).getJSONObject("properties").getJSONArray("timeseries").getJSONObject(0).getJSONObject("data");
        float f = (float) jSONObject.getJSONObject("instant").getJSONObject("details").getDouble("air_temperature");
        Iterator it = j3.i0("next_1_hours", "next_6_hours", "next_12_hours").iterator();
        do {
            str2 = null;
            if (!it.hasNext()) {
                break;
            }
            JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject((String) it.next());
            if (jSONObjectOptJSONObject2 != null && (jSONObjectOptJSONObject = jSONObjectOptJSONObject2.optJSONObject("summary")) != null && (strOptString = jSONObjectOptJSONObject.optString("symbol_code")) != null && !th.A(strOptString)) {
                str2 = strOptString;
            }
        } while (str2 == null);
        if (str2 == null) {
            str2 = "";
        }
        return new yc(Float.valueOf(f), str2);
    }

    public final void a(Location location) {
        if (this.h) {
            return;
        }
        this.h = true;
        Locale locale = Locale.US;
        this.e.execute(new l(this, String.format(locale, "%.4f", Arrays.copyOf(new Object[]{Double.valueOf(location.getLatitude())}, 1)), String.format(locale, "%.4f", Arrays.copyOf(new Object[]{Double.valueOf(location.getLongitude())}, 1)), location, 6));
    }

    public final boolean b() {
        Context context = this.a;
        return context.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0 || context.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0;
    }

    public final void d(wj wjVar) {
        this.i = wjVar;
        if (this.f) {
            this.b.b(wjVar);
        }
    }

    /* JADX WARN: Code duplicated, block: B:60:0x0127  */
    public final void e(boolean z) {
        Object next;
        Location location;
        Object ffVar;
        boolean z2;
        LocationManager locationManager;
        if (!b()) {
            d(wj.a(this.i, si.a("tap_to_allow_location", new Object[0])));
            return;
        }
        Context context = this.a;
        LocationManager locationManager2 = (LocationManager) context.getSystemService(LocationManager.class);
        if (locationManager2 == null) {
            location = null;
        } else {
            List listI0 = j3.i0("gps", "network", "passive");
            ArrayList arrayList = new ArrayList();
            Iterator it = listI0.iterator();
            while (it.hasNext()) {
                try {
                    ffVar = locationManager2.getLastKnownLocation((String) it.next());
                } catch (Throwable th) {
                    ffVar = new ff(th);
                }
                if (ffVar instanceof ff) {
                    ffVar = null;
                }
                Location location2 = (Location) ffVar;
                if (location2 != null) {
                    arrayList.add(location2);
                }
            }
            Iterator it2 = arrayList.iterator();
            if (it2.hasNext()) {
                next = it2.next();
                if (it2.hasNext()) {
                    long time = ((Location) next).getTime();
                    do {
                        Object next2 = it2.next();
                        long time2 = ((Location) next2).getTime();
                        if (time < time2) {
                            next = next2;
                            time = time2;
                        }
                    } while (it2.hasNext());
                }
            } else {
                next = null;
            }
            location = (Location) next;
        }
        if (location == null) {
            wj wjVar = this.i;
            d(wj.a(wjVar, wjVar.a == null ? si.a("finding_location", new Object[0]) : null));
            if (this.g == null && (locationManager = (LocationManager) context.getSystemService(LocationManager.class)) != null) {
                x6 x6Var = new x6(3, this);
                this.g = x6Var;
                for (String str : j3.i0("network", "gps")) {
                    try {
                        if (locationManager.isProviderEnabled(str)) {
                            locationManager.requestSingleUpdate(str, x6Var, Looper.getMainLooper());
                        }
                    } catch (Throwable unused) {
                    }
                }
                return;
            }
            return;
        }
        SharedPreferences sharedPreferences = this.c;
        float f = sharedPreferences.getFloat("weather_lat", Float.NaN);
        float f2 = sharedPreferences.getFloat("weather_lon", Float.NaN);
        if (Float.isNaN(f)) {
            z2 = true;
        } else {
            float[] fArr = new float[1];
            Location.distanceBetween(location.getLatitude(), location.getLongitude(), f, f2, fArr);
            if (fArr[0] > 10000.0f) {
                z2 = true;
            } else {
                z2 = false;
            }
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        wj wjVar2 = this.i;
        boolean z3 = jCurrentTimeMillis - wjVar2.c > 1800000;
        if (z || z2 || z3 || wjVar2.a == null) {
            a(location);
        }
    }

    public final void f() {
        if (this.f) {
            return;
        }
        this.f = true;
        this.b.b(this.i);
        this.d.post(this.j);
    }

    public final void g() {
        this.f = false;
        this.d.removeCallbacksAndMessages(null);
        x6 x6Var = this.g;
        if (x6Var == null) {
            return;
        }
        this.g = null;
        try {
            LocationManager locationManager = (LocationManager) this.a.getSystemService(LocationManager.class);
            if (locationManager != null) {
                locationManager.removeUpdates(x6Var);
            }
        } catch (Throwable unused) {
        }
    }
}
