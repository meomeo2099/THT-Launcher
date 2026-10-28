package org.reference.panel;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import defpackage.a1;
import defpackage.a7;
import defpackage.ab;
import defpackage.ag;
import defpackage.aj;
import defpackage.b2;
import defpackage.ba;
import defpackage.bb;
import defpackage.bf;
import defpackage.bj;
import defpackage.c3;
import defpackage.c4;
import defpackage.ca;
import defpackage.cf;
import defpackage.ci;
import defpackage.cj;
import defpackage.d9;
import defpackage.da;
import defpackage.df;
import defpackage.di;
import defpackage.e8;
import defpackage.ea;
import defpackage.eb;
import defpackage.eh;
import defpackage.f8;
import defpackage.ff;
import defpackage.fi;
import defpackage.gc;
import defpackage.gf;
import defpackage.h2;
import defpackage.hb;
import defpackage.hd;
import defpackage.hh;
import defpackage.i3;
import defpackage.i4;
import defpackage.i8;
import defpackage.ie;
import defpackage.j;
import defpackage.j0;
import defpackage.j3;
import defpackage.j7;
import defpackage.j8;
import defpackage.j9;
import defpackage.jc;
import defpackage.k;
import defpackage.k3;
import defpackage.k4;
import defpackage.k8;
import defpackage.k9;
import defpackage.kb;
import defpackage.kf;
import defpackage.kg;
import defpackage.l;
import defpackage.l0;
import defpackage.l8;
import defpackage.lb;
import defpackage.lf;
import defpackage.li;
import defpackage.m2;
import defpackage.m4;
import defpackage.ma;
import defpackage.n2;
import defpackage.n4;
import defpackage.na;
import defpackage.nb;
import defpackage.ni;
import defpackage.nj;
import defpackage.o;
import defpackage.o2;
import defpackage.o7;
import defpackage.oi;
import defpackage.p3;
import defpackage.p5;
import defpackage.pb;
import defpackage.pc;
import defpackage.pi;
import defpackage.q;
import defpackage.q3;
import defpackage.q7;
import defpackage.q8;
import defpackage.q9;
import defpackage.qa;
import defpackage.qf;
import defpackage.r4;
import defpackage.r9;
import defpackage.ra;
import defpackage.rf;
import defpackage.rg;
import defpackage.s7;
import defpackage.s8;
import defpackage.s9;
import defpackage.sa;
import defpackage.sc;
import defpackage.sf;
import defpackage.sh;
import defpackage.si;
import defpackage.t;
import defpackage.t0;
import defpackage.tf;
import defpackage.th;
import defpackage.ti;
import defpackage.u;
import defpackage.u4;
import defpackage.u5;
import defpackage.ua;
import defpackage.uh;
import defpackage.v1;
import defpackage.v2;
import defpackage.v4;
import defpackage.v7;
import defpackage.v8;
import defpackage.v9;
import defpackage.vb;
import defpackage.vc;
import defpackage.vj;
import defpackage.w;
import defpackage.w2;
import defpackage.w6;
import defpackage.w7;
import defpackage.w8;
import defpackage.wa;
import defpackage.x0;
import defpackage.x4;
import defpackage.x5;
import defpackage.x7;
import defpackage.x8;
import defpackage.xa;
import defpackage.y1;
import defpackage.y5;
import defpackage.y8;
import defpackage.yc;
import defpackage.ye;
import defpackage.z1;
import defpackage.z2;
import defpackage.z9;
import defpackage.za;
import defpackage.ze;
import defpackage.zi;
import defpackage.zj;
import java.io.File;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class MainActivity extends Activity {
    public static final /* synthetic */ int E0 = 0;
    public final ArrayList A;
    public final String A0;
    public final ArrayList B;
    public final int B0;
    public final ArrayList C;
    public final String C0;
    public final ArrayList D;
    public q8 D0;
    public final ArrayList E;
    public String F;
    public final ArrayList G;
    public final ArrayList H;
    public y1 I;
    public final ArrayList J;
    public final ArrayList K;
    public final ArrayList L;
    public final ArrayList M;
    public final ArrayList N;
    public boolean O;
    public final ArrayList P;
    public final ArrayList Q;
    public final fi R;
    public volatile di S;
    public volatile long T;
    public volatile boolean U;
    public final ArrayList V;
    public final ArrayList W;
    public v9 X;
    public final fi Y;
    public bj Z;
    public final fi a0;
    public jc b0;
    public boolean c;
    public FrameLayout c0;
    public boolean d;
    public qf d0;
    public Integer e;
    public FrameLayout e0;
    public boolean f;
    public df f0;
    public c4 g;
    public final fi g0;
    public boolean h;
    public final Handler h0;
    public boolean i;
    public final sa i0;
    public boolean j;
    public final fi j0;
    public Bitmap k;
    public final sa k0;
    public long l0;
    public RectF m;
    public final List m0;
    public int n;
    public final Map n0;
    public int o0;
    public v8 p0;
    public v8 q0;
    public FrameLayout r;
    public yc r0;
    public ScrollView s;
    public Rect s0;
    public int t;
    public ab t0;
    public String u;
    public y5 u0;
    public gc v;
    public final ArrayList v0;
    public final ArrayList w;
    public Float w0;
    public int x;
    public long x0;
    public final sc y;
    public final String y0;
    public final ArrayList z;
    public final String z0;
    public final fi a = new fi(new b2(this, 15));
    public final ExecutorService b = Executors.newSingleThreadExecutor();
    public String l = "";
    public int o = -1;
    public int p = -3355444;
    public int q = -16777216;

    /* JADX WARN: Multi-variable type inference failed */
    public MainActivity() {
        Color.rgb(148, 207, 248);
        this.u = "desktop";
        this.w = new ArrayList();
        this.y = new sc();
        this.z = new ArrayList();
        this.A = new ArrayList();
        this.B = new ArrayList();
        this.C = new ArrayList();
        this.D = new ArrayList();
        this.E = new ArrayList();
        this.G = new ArrayList();
        this.H = new ArrayList();
        this.J = new ArrayList();
        this.K = new ArrayList();
        this.L = new ArrayList();
        this.M = new ArrayList();
        this.N = new ArrayList();
        this.P = new ArrayList();
        this.Q = new ArrayList();
        this.R = new fi(new b2(this, 21));
        this.V = new ArrayList();
        this.W = new ArrayList();
        this.Y = new fi(new b2(this, 22));
        this.Z = new bj(null, null, null, null, null, null, 67108863);
        this.a0 = new fi(new b2(this, 23));
        Object[] objArr = 0 == true ? 1 : 0;
        Object[] objArr2 = 0 == true ? 1 : 0;
        Object[] objArr3 = 0 == true ? 1 : 0;
        Object[] objArr4 = 0 == true ? 1 : 0;
        this.b0 = new jc(false, objArr, objArr2, objArr3, objArr4, false, false, false, false, null, 65535);
        this.g0 = new fi(new b2(this, 24));
        this.h0 = new Handler(Looper.getMainLooper());
        this.i0 = new sa(this, 0);
        this.j0 = new fi(new b2(this, 25));
        this.k0 = new sa(this, 1);
        this.m0 = j3.i0("brightness", "volume", "roof_slider", "nav", "music", "music2", "music_title", "temp", "weather", "fuel", "battery", "bt", "wifi", "maps", "clock", "car", "automation", "stats", "theme", "gps", "temp_passenger", "saver", "camera360", "folder", "fuel_compact", "wifi_mark", "bt_mark", "radio", "degree_driver", "degree_passenger", "battery_compact", "anton", "app_window");
        this.n0 = hb.j0(new yc("brightness", si.a("screen_brightness", new Object[0])), new yc("volume", si.a("volume", new Object[0])), new yc("roof_slider", si.a("sunroof_position", new Object[0])), new yc("nav", si.a("navigation_206", new Object[0])), new yc("music", si.a("music", new Object[0])), new yc("music2", si.a("music_2", new Object[0])), new yc("music_title", si.a("track_title_scrolling_text", new Object[0])), new yc("temp", si.a("temperature_compact", new Object[0])), new yc("weather", si.a("clock_and_weather", new Object[0])), new yc("fuel", si.a("range_fuel", new Object[0])), new yc("battery", si.a("battery", new Object[0])), new yc("bt", "Bluetooth"), new yc("wifi", "Wi-Fi"), new yc("maps", si.a("maps_shortcut", new Object[0])), new yc("clock", si.a("clock", new Object[0])), new yc("car", si.a("vehicle", new Object[0])), new yc("automation", si.a("automation", new Object[0])), new yc("stats", si.a("trip_statistics", new Object[0])), new yc("theme", si.a("theme_switch", new Object[0])), new yc("gps", si.a("gps_widget", new Object[0])), new yc("temp_passenger", si.a("temperature_passenger", new Object[0])), new yc("saver", si.a("screensaver", new Object[0])), new yc("camera360", si.a("camera360", new Object[0])), new yc("folder", si.a("folder", new Object[0])), new yc("fuel_compact", si.a("fuel_compact", new Object[0])), new yc("wifi_mark", si.a("wifi_mark", new Object[0])), new yc("bt_mark", si.a("bt_mark", new Object[0])), new yc("radio", si.a("radio", new Object[0])), new yc("battery_compact", si.a("battery_compact", new Object[0])), new yc("degree_driver", si.a("degree_driver", new Object[0])), new yc("degree_passenger", si.a("degree_passenger", new Object[0])), new yc("anton", si.a("anton", new Object[0])), new yc("app_window", si.a("app_widget", new Object[0])));
        this.v0 = new ArrayList();
        this.y0 = "surface_style";
        this.z0 = "dock_overlay";
        this.A0 = "dock_overlay_mode";
        this.B0 = 8;
        this.C0 = "app_window_open";
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static int[] N(String str) {
        switch (str.hashCode()) {
            case -1302320901:
                if (str.equals("degree_driver")) {
                    return new int[]{470, 350, 156, 100};
                }
                break;
            case -1268966290:
                if (str.equals("folder")) {
                    return new int[]{962, 172, 100, 100};
                }
                break;
            case -1062807891:
                if (str.equals("music2")) {
                    return new int[]{196, 356, 690, 200};
                }
                break;
            case -810883302:
                if (str.equals("volume")) {
                    return new int[]{116, 40, 64, 516};
                }
                break;
            case -799216230:
                if (str.equals("fuel_compact")) {
                    return new int[]{902, 240, 161, 56};
                }
                break;
            case -580800793:
                if (str.equals("degree_passenger")) {
                    return new int[]{646, 350, 156, 100};
                }
                break;
            case -345754895:
                if (str.equals("battery_compact")) {
                    return new int[]{720, 240, 180, 64};
                }
                break;
            case -342035224:
                if (str.equals("camera360")) {
                    return new int[]{1079, 172, 117, 117};
                }
                break;
            case -331239923:
                if (str.equals("battery")) {
                    return new int[]{1079, 306, 161, 117};
                }
                break;
            case 3154:
                if (str.equals("bt")) {
                    return new int[]{902, 439, 161, 117};
                }
                break;
            case 98260:
                if (str.equals("car")) {
                    return new int[]{600, 220, 640, 320};
                }
                break;
            case 102570:
                if (str.equals("gps")) {
                    return new int[]{640, 60, 64, 64};
                }
                break;
            case 108835:
                if (str.equals("nav")) {
                    return new int[]{196, 40, 337, 516};
                }
                break;
            case 3154358:
                if (str.equals("fuel")) {
                    return new int[]{902, 306, 161, 117};
                }
                break;
            case 3344023:
                if (str.equals("maps")) {
                    return new int[]{902, 439, 161, 117};
                }
                break;
            case 3556308:
                if (str.equals("temp")) {
                    return new int[]{902, 439, 338, 117};
                }
                break;
            case 3649301:
                if (str.equals("wifi")) {
                    return new int[]{1079, 439, 161, 117};
                }
                break;
            case 92973574:
                if (str.equals("anton")) {
                    return new int[]{470, 220, 340, 196};
                }
                break;
            case 94755854:
                if (str.equals("clock")) {
                    return new int[]{60, 50, 460, 150};
                }
                break;
            case 104263205:
                if (str.equals("music")) {
                    return new int[]{549, 40, 337, 516};
                }
                break;
            case 108270587:
                if (str.equals("radio")) {
                    return new int[]{549, 470, 337, 86};
                }
                break;
            case 109211285:
                if (str.equals("saver")) {
                    return new int[]{720, 60, 64, 64};
                }
                break;
            case 110327241:
                if (str.equals("theme")) {
                    return new int[]{540, 60, 80, 80};
                }
                break;
            case 193107610:
                if (str.equals("bt_mark")) {
                    return new int[]{880, 60, 64, 64};
                }
                break;
            case 344200471:
                if (str.equals("automation")) {
                    return new int[]{902, 306, 161, 117};
                }
                break;
            case 648162385:
                if (str.equals("brightness")) {
                    return new int[]{40, 40, 64, 516};
                }
                break;
            case 1223440372:
                if (str.equals("weather")) {
                    return new int[]{902, 40, 338, 250};
                }
                break;
            case 1272109551:
                if (str.equals("temp_passenger")) {
                    return new int[]{549, 439, 338, 117};
                }
                break;
            case 1388570574:
                if (str.equals("app_window")) {
                    return new int[]{549, 40, 337, 516};
                }
                break;
            case 1390681772:
                if (str.equals("roof_slider")) {
                    return new int[]{192, 40, 64, 516};
                }
                break;
            case 1400976823:
                if (str.equals("wifi_mark")) {
                    return new int[]{800, 60, 64, 64};
                }
                break;
            case 1589871550:
                if (str.equals("music_title")) {
                    return new int[]{196, 470, 690, 70};
                }
                break;
        }
        return new int[]{420, 220, 320, 184};
    }

    public static final void R(y1 y1Var, MainActivity mainActivity, String str) {
        Object ffVar;
        Intent intent = (Intent) y1Var.a();
        if (intent != null) {
            try {
                mainActivity.startActivity(intent);
                ffVar = ti.a;
            } catch (Throwable th) {
                ffVar = new ff(th);
            }
            if (!(ffVar instanceof ff)) {
                return;
            }
        }
        mainActivity.v0(str);
        ArrayList arrayList = mainActivity.w;
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        int i = 0;
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayList.get(i2);
            i2++;
            if (((b) obj).isShown()) {
                arrayList2.add(obj);
            }
        }
        int size2 = arrayList2.size();
        while (i < size2) {
            Object obj2 = arrayList2.get(i);
            i++;
            ((b) obj2).j();
        }
    }

    public static String f(int i) {
        if (4 <= i && i < 6) {
            return "dock_" + (i - 4);
        }
        if (6 > i || i >= 8) {
            return "slot_" + i;
        }
        return "tile_" + (i - 6);
    }

    public static Button i(MainActivity mainActivity, String str, y5 y5Var) {
        mainActivity.getClass();
        str.getClass();
        Button button = new Button(mainActivity);
        button.setText(str);
        button.setAllCaps(false);
        button.setTextSize(mainActivity.w() * 16.0f);
        button.setMinHeight(mainActivity.r(52));
        button.setMinimumHeight(mainActivity.r(52));
        button.setTextColor(mainActivity.o);
        button.setBackground(mainActivity.m0(mainActivity.q, 16));
        button.setPadding(mainActivity.r(18), mainActivity.r(10), mainActivity.r(18), mainActivity.r(10));
        button.setOnClickListener(new m2(y5Var, 8));
        return button;
    }

    public final boolean A() {
        return (this.q0 == null && this.D.isEmpty()) ? false : true;
    }

    public final SharedPreferences B() {
        return (SharedPreferences) this.a.a();
    }

    public final String C() {
        return this.y0;
    }

    public final String D() {
        String string = B().getString("theme_mode", null);
        if (string == null) {
            return B().getBoolean("night", true) ? "night" : "day";
        }
        return string;
    }

    public final zi E() {
        return uh.e(B().getString("vehicle", "knewstar_001"));
    }

    public final int F() {
        return B().getInt("color_".concat(E().a), E().a.equals("knewstar_001") ? B().getInt("color", 0) : 0);
    }

    public final ArrayList G() {
        List listI0 = j3.i0("temp", "temp2", "temp_passenger", "ac", "ac_max", "auto", "recirc", "defrost_rear", "climate", "airflow", "steering", "ambient", "roof", "roof_tilt", "curtain", "start_stop", "cruise", "nav_window");
        Set set = aj.i;
        ArrayList arrayList = new ArrayList();
        for (Object obj : set) {
            List list = z2.a;
            E().getClass();
            arrayList.add(obj);
        }
        ArrayList arrayListD0 = i3.D0(listI0, arrayList);
        List listA = pi.A();
        ArrayList arrayList2 = new ArrayList(k3.n0(listA));
        Iterator it = listA.iterator();
        while (it.hasNext()) {
            arrayList2.add(((n4) it.next()).a);
        }
        ArrayList arrayListD1 = i3.D0(arrayListD0, arrayList2);
        Set set2 = rf.d;
        Set setKeySet = pi.B().keySet();
        setKeySet.getClass();
        return i3.D0(i3.D0(arrayListD1, setKeySet), o2.b().keySet());
    }

    public final nj H() {
        return (nj) this.a0.a();
    }

    public final vj I() {
        return (vj) this.Y.a();
    }

    public final void J() {
        this.n++;
        this.k = null;
        this.l = "";
        this.m = null;
    }

    public final v8 K(final boolean z) {
        boolean z2 = B().getBoolean("menu_labels", false);
        v8 v8Var = new v8(this, z(), k8.e(B().getString("dock_alignment", "left"), "spread"));
        if (!z) {
            this.p0 = v8Var;
        }
        v8Var.setOnLongClickListener(new ma(z, this));
        v8Var.setOnScroll(new v1(this, 22));
        int iV = V();
        for (final int i = 0; i < iV; i++) {
            View viewN = n(U(i), z2, new y5() { // from class: ta
                @Override // defpackage.y5
                public final Object a() {
                    int i2 = MainActivity.E0;
                    boolean z3 = z;
                    MainActivity mainActivity = this;
                    if (z3) {
                        mainActivity.h();
                    } else {
                        k8.D(mainActivity, Integer.valueOf(i));
                    }
                    return ti.a;
                }
            });
            if (viewN instanceof x8) {
                v8Var.b.add(viewN);
            }
            v8Var.d.addView(viewN);
        }
        c0();
        int i2 = this.o0;
        if (i2 > 0) {
            v8Var.post(new t0(v8Var, i2, 2));
        }
        return v8Var;
    }

    public final String L(String str) {
        if (k8.e(str, "apps")) {
            return "menu";
        }
        if (k8.e(str, "favorites")) {
            return "favorite";
        }
        if (k8.e(str, "settings")) {
            return "gear";
        }
        if (k8.e(str, "home")) {
            return "home";
        }
        if (k8.e(str, "music")) {
            return "music";
        }
        if (k8.e(str, "nav_window")) {
            return "nav";
        }
        if (k8.e(str, this.C0)) {
            return "grid";
        }
        if (k8.e(str, "climate")) {
            return "fan";
        }
        if (k8.e(str, "temp") || k8.e(str, "temp2") || k8.e(str, "temp_passenger")) {
            return "thermometer";
        }
        if (k8.e(str, "theme")) {
            return z() ? "sun" : "moon";
        }
        if (k8.e(str, "stats")) {
            return "lane";
        }
        if (k8.e(str, "automation")) {
            return "ambient";
        }
        if (k8.e(str, "bt")) {
            return "bt";
        }
        if (k8.e(str, "wifi")) {
            return "wifi";
        }
        if (k8.e(str, "collapse")) {
            return u() ? "expand" : "collapse";
        }
        if (k8.e(str, "ac")) {
            return "snow";
        }
        if (k8.e(str, "ac_max")) {
            return "text:A/C|MAX";
        }
        if (k8.e(str, "auto")) {
            return "auto";
        }
        if (k8.e(str, "recirc")) {
            return "recirc";
        }
        if (k8.e(str, "defrost_rear")) {
            return "rear";
        }
        if (k8.e(str, "start_stop")) {
            return "ss";
        }
        if (k8.e(str, "ambient")) {
            return "ambient";
        }
        if (k8.e(str, "voice")) {
            return "mic";
        }
        if (k8.e(str, "ac_eco") || k8.e(str, "eco_hints")) {
            return "leaf";
        }
        if (k8.e(str, "defrost_max")) {
            return "front";
        }
        if (k8.e(str, "air_clean")) {
            return "fan";
        }
        if (k8.e(str, "ac_reminders")) {
            return "clock";
        }
        if (k8.e(str, "esc_sport")) {
            return "sport";
        }
        if (k8.e(str, "lane_keep")) {
            return "lane";
        }
        if (k8.e(str, "g_pilot")) {
            return "pilot";
        }
        if (k8.e(str, "park_assist")) {
            return "park";
        }
        if (k8.e(str, "passenger_screen")) {
            return "screen";
        }
        if (k8.e(str, "drive_power")) {
            return "bolt";
        }
        if (k8.e(str, "drive_eco")) {
            return "leaf";
        }
        if (k8.e(str, "drive_comfort")) {
            return "comfort";
        }
        if (k8.e(str, "drive_sport")) {
            return "sport";
        }
        if (k8.e(str, "drive_sport_plus")) {
            return "sportplus";
        }
        if (k8.e(str, "drive_snow")) {
            return "snowmode";
        }
        if (k8.e(str, "drive_offroad")) {
            return "offroad";
        }
        return k8.e(str, "drive_adaptive") ? "adaptive" : "empty";
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:110:0x0187  */
    /* JADX WARN: Code duplicated, block: B:178:0x0290  */
    /* JADX WARN: Code duplicated, block: B:37:0x00af  */
    /* JADX WARN: Code duplicated, block: B:48:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:70:0x0112  */
    /* JADX WARN: Code duplicated, block: B:85:0x013f  */
    /* JADX WARN: Code duplicated, block: B:89:0x014b  */
    /* JADX WARN: Code duplicated, block: B:96:0x015e  */
    public final View M(String str) {
        Object ffVar;
        Drawable drawable;
        str.getClass();
        if (str.startsWith("widget:") && x0.Y0(new String[]{"saver", "camera360", "gps", "radio"}).contains(th.C(str, "widget:"))) {
            return M(th.C(str, "widget:"));
        }
        String strL = "thermometer";
        if (!str.startsWith("widget:")) {
            if (str.startsWith("split:")) {
                d9 d9Var = new d9(this, "split");
                int i = l8.a;
                d9Var.setColor(l8.j(z()));
                return d9Var;
            }
            if (str.startsWith("x:")) {
                String strC = th.C(str, "x:");
                if (strC.startsWith("air:") || strC.equals("sync") || strC.equals("front")) {
                    return k8.n(strC, this);
                }
                if (strC.equals("power")) {
                    return k8.P("power", this);
                }
                if (strC.startsWith("temp") || strC.startsWith("ptemp")) {
                    return k8.P("thermometer", this);
                }
                if (strC.startsWith("fan")) {
                    return k8.P("fan", this);
                }
                if (strC.startsWith("roof")) {
                    return M("roof");
                }
                if (strC.startsWith("curtain")) {
                    return M("curtain");
                }
                if (strC.equals("trunk_open")) {
                    return M("trunk");
                }
                return (strC.equals("prev") || strC.equals("next")) ? k8.P("music", this) : k8.P("speaker", this);
            }
            if (str.startsWith("app:")) {
                ImageView imageView = new ImageView(this);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(th.C(str, "app:"));
                if (componentNameUnflattenFromString != null) {
                    try {
                        ffVar = getPackageManager().getActivityIcon(componentNameUnflattenFromString);
                    } catch (Throwable th) {
                        ffVar = new ff(th);
                    }
                    drawable = (Drawable) (ffVar instanceof ff ? null : ffVar);
                    if (drawable == null) {
                        drawable = getDrawable(R.drawable.sym_def_app_icon);
                    }
                } else {
                    drawable = getDrawable(R.drawable.sym_def_app_icon);
                }
                imageView.setImageDrawable(drawable);
                return imageView;
            }
            Set set = rf.d;
            if (pi.B().containsKey(str) || ba.r.contains(str)) {
                cj cjVar = new cj(this, str);
                cjVar.setHalo(false);
                cjVar.setNightMode(z());
                return cjVar;
            }
            int i2 = 9;
            if (str.equals("gps")) {
                a7 a7Var = new a7(this, new j(i2));
                a7Var.setClickable(false);
                a7Var.setFocusable(false);
                return a7Var;
            }
            if (str.equals("saver")) {
                lf lfVar = new lf(new j(i2), this, z(), false);
                lfVar.setClickable(false);
                lfVar.setFocusable(false);
                return lfVar;
            }
            if (str.equals("camera360")) {
                return new n2(new j(i2), this, z(), false);
            }
            if (str.equals("folder")) {
                return new p5(this, z(), k8.J(this), false);
            }
            if (str.equals("fuel_compact")) {
                q3 q3Var = new q3(this, z(), false);
                this.G.add(q3Var);
                return q3Var;
            }
            if (str.equals("radio")) {
                ie ieVar = new ie(this, z(), false);
                this.H.add(ieVar);
                lb lbVarS = y().s();
                ie.f(ieVar, lbVarS, y().r(lbVarS), 4);
                return ieVar;
            }
            if (str.equals("wifi_mark")) {
                k9 k9Var = new k9(this, z(), true, new j(i2));
                k9Var.l = true;
                k9Var.setBackground(null);
                k9Var.invalidate();
                k9Var.setClickable(false);
                k9Var.setLongClickable(false);
                k9Var.setFocusable(false);
                return k9Var;
            }
            if (str.equals("bt_mark")) {
                k9 k9Var2 = new k9(this, z(), false, new j(i2));
                k9Var2.l = true;
                k9Var2.setBackground(null);
                k9Var2.invalidate();
                k9Var2.setClickable(false);
                k9Var2.setLongClickable(false);
                k9Var2.setFocusable(false);
                return k9Var2;
            }
            if (str.equals("gap")) {
                int i3 = l8.a;
                return new k4(this, l8.j(z()));
            }
            if (!str.equals(this.C0)) {
                d9 d9Var2 = new d9(this, L(str));
                int i4 = l8.a;
                d9Var2.setColor(l8.j(z()));
                return d9Var2;
            }
            ComponentName componentNameM = m(this.B0);
            if (componentNameM != null) {
                View viewM = M("app:" + componentNameM.flattenToString());
                if (viewM != null) {
                    return viewM;
                }
            }
            d9 d9Var3 = new d9(this, "grid");
            int i5 = l8.a;
            d9Var3.setColor(l8.j(z()));
            return d9Var3;
        }
        String strC2 = th.C(str, "widget:");
        switch (strC2.hashCode()) {
            case -1302320901:
                if (!strC2.equals("degree_driver")) {
                    strL = L(strC2);
                }
                break;
            case -1268966290:
                if (!strC2.equals("folder")) {
                    strL = L(strC2);
                } else {
                    strL = "menu";
                }
                break;
            case -1062807891:
                if (!strC2.equals("music2")) {
                    strL = L(strC2);
                } else {
                    strL = "music";
                }
                break;
            case -810883302:
                if (!strC2.equals("volume")) {
                    strL = L(strC2);
                } else {
                    strL = "speaker";
                }
                break;
            case -799216230:
                if (!strC2.equals("fuel_compact")) {
                    strL = L(strC2);
                } else {
                    strL = "pump";
                }
                break;
            case -580800793:
                if (!strC2.equals("degree_passenger")) {
                    strL = L(strC2);
                }
                break;
            case -345754895:
                if (!strC2.equals("battery_compact")) {
                    strL = L(strC2);
                } else {
                    strL = "batt";
                }
                break;
            case -342035224:
                if (!strC2.equals("camera360")) {
                    strL = L(strC2);
                } else {
                    strL = "screen";
                }
                break;
            case -331239923:
                if (!strC2.equals("battery")) {
                    strL = L(strC2);
                } else {
                    strL = "batt";
                }
                break;
            case 98260:
                if (!strC2.equals("car")) {
                    strL = L(strC2);
                } else {
                    strL = "carfront";
                }
                break;
            case 102570:
                if (!strC2.equals("gps")) {
                    strL = L(strC2);
                } else {
                    strL = "globe";
                }
                break;
            case 108835:
                if (!strC2.equals("nav")) {
                    strL = L(strC2);
                } else {
                    strL = "nav";
                }
                break;
            case 3154358:
                if (!strC2.equals("fuel")) {
                    strL = L(strC2);
                } else {
                    strL = "pump";
                }
                break;
            case 3344023:
                if (!strC2.equals("maps")) {
                    strL = L(strC2);
                } else {
                    strL = "nav";
                }
                break;
            case 3556308:
                if (!strC2.equals("temp")) {
                    strL = L(strC2);
                }
                break;
            case 92973574:
                if (!strC2.equals("anton")) {
                    strL = L(strC2);
                }
                break;
            case 94755854:
                strL = "clock";
                if (!strC2.equals("clock")) {
                    strL = L(strC2);
                }
                break;
            case 104263205:
                if (!strC2.equals("music")) {
                    strL = L(strC2);
                } else {
                    strL = "music";
                }
                break;
            case 108270587:
                if (!strC2.equals("radio")) {
                    strL = L(strC2);
                } else {
                    strL = "music";
                }
                break;
            case 109211285:
                if (!strC2.equals("saver")) {
                    strL = L(strC2);
                } else {
                    strL = "screen";
                }
                break;
            case 193107610:
                if (!strC2.equals("bt_mark")) {
                    strL = L(strC2);
                } else {
                    strL = "bt";
                }
                break;
            case 648162385:
                if (!strC2.equals("brightness")) {
                    strL = L(strC2);
                } else {
                    strL = "sun";
                }
                break;
            case 1223440372:
                if (!strC2.equals("weather")) {
                    strL = L(strC2);
                } else {
                    strL = "sun";
                }
                break;
            case 1272109551:
                if (!strC2.equals("temp_passenger")) {
                    strL = L(strC2);
                }
                break;
            case 1388570574:
                if (!strC2.equals("app_window")) {
                    strL = L(strC2);
                } else {
                    strL = "grid";
                }
                break;
            case 1390681772:
                if (!strC2.equals("roof_slider")) {
                    strL = L(strC2);
                } else {
                    strL = "roofslider";
                }
                break;
            case 1400976823:
                if (!strC2.equals("wifi_mark")) {
                    strL = L(strC2);
                } else {
                    strL = "wifi";
                }
                break;
            case 1589871550:
                if (!strC2.equals("music_title")) {
                    strL = L(strC2);
                } else {
                    strL = "music";
                }
                break;
            default:
                strL = L(strC2);
                break;
        }
        d9 d9Var4 = new d9(this, strL);
        int i6 = l8.a;
        d9Var4.setColor(l8.j(z()));
        return d9Var4;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:112:0x0200  */
    /* JADX WARN: Code duplicated, block: B:153:0x02bd  */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x025c, code lost:

        if (r11.equals("drive_eco") == false) goto L151;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x0292, code lost:

        if (r11.equals("start_stop") == false) goto L151;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x0295, code lost:

        r0 = defpackage.aj.i;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x02ac, code lost:

        return new defpackage.yc(java.lang.Boolean.valueOf(defpackage.sh.n(r11, r10)), java.lang.Integer.valueOf(defpackage.l8.g()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00e4, code lost:

        if (r11.equals("drive_sport") == false) goto L151;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00ee, code lost:

        if (r11.equals("drive_power") == false) goto L151;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0154, code lost:

        if (r11.equals("drive_sport_plus") == false) goto L151;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0158, code lost:

        r0 = defpackage.aj.i;
        r10 = java.lang.Boolean.valueOf(defpackage.sh.n(r11, r10));
        r11 = defpackage.l8.a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0171, code lost:

        return new defpackage.yc(r10, java.lang.Integer.valueOf(android.graphics.Color.rgb(255, 137, 117)));
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final defpackage.yc O(java.lang.String r11) {
        /*
            Method dump skipped, instruction units count: 820
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.reference.panel.MainActivity.O(java.lang.String):yc");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x04a6, code lost:

        if (r17.equals("degree_passenger") == false) goto L194;
     */
    /* JADX WARN: Code restructure failed: missing block: B:193:0x059b, code lost:

        if (r17.equals("degree_driver") == false) goto L194;
     */
    /* JADX WARN: Code restructure failed: missing block: B:196:0x05a2, code lost:

        return q(r17, true);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View P(java.lang.String r17) {
        /*
            Method dump skipped, instruction units count: 1578
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.reference.panel.MainActivity.P(java.lang.String):android.view.View");
    }

    public final void Q(Intent intent) {
        if (k8.g) {
            k8.f++;
            k8.g = false;
        }
        ComponentName component = intent.getComponent();
        if ((component != null ? component.getPackageName() : null) != null) {
            ComponentName component2 = intent.getComponent();
            String packageName = component2 != null ? component2.getPackageName() : null;
            ComponentName componentNameM = m(0);
            if (k8.e(packageName, componentNameM != null ? componentNameM.getPackageName() : null)) {
                Z(0);
                return;
            }
        }
        sc scVar = this.y;
        if (scVar.b && !scVar.c) {
            j();
        }
        ArrayList arrayList = this.w;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            b bVar = (b) obj;
            bVar.v = false;
            bVar.b();
            bVar.f();
        }
        intent.addFlags(270532608);
        y1 y1Var = new y1(intent, this);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            R(y1Var, this, si.a("app_unavailable_on_this_head_unit", new Object[0]));
        } catch (SecurityException unused2) {
            R(y1Var, this, si.a("the_firmware_restricts_launching_this_app", new Object[0]));
        }
    }

    public final int S(Rect rect) {
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getRealSize(point);
        return rect.top - pi.s(point.y - rect.bottom, 0, r(12));
    }

    public final Rect T() {
        v8 v8Var = this.p0;
        if (v8Var != null) {
            if (!v8Var.isAttachedToWindow() || v8Var.getWidth() <= 0 || v8Var.getHeight() <= 0 || !v8Var.isShown()) {
                v8Var = null;
            }
            if (v8Var != null) {
                int[] iArr = new int[2];
                v8Var.getLocationOnScreen(iArr);
                int i = iArr[0];
                return new Rect(i, iArr[1], v8Var.getWidth() + i, v8Var.getHeight() + iArr[1]);
            }
        }
        return null;
    }

    public final String U(int i) {
        String string = B().getString("menu_action_" + i, null);
        if (string != null) {
            return string;
        }
        if (i == 0) {
            return "home";
        }
        if (i != 1 && i != 2) {
            if (i != 3) {
                return i != 4 ? "empty" : "settings";
            }
            return "apps";
        }
        ComponentName componentNameM = m(i + 3);
        if (componentNameM == null) {
            return "empty";
        }
        return "app:" + componentNameM.flattenToString();
    }

    public final int V() {
        int i = B().getInt("menu_count", 5);
        if (i < 1) {
            return 1;
        }
        return i;
    }

    public final String W(String str) {
        Object ffVar;
        str.getClass();
        if (str.equals("wiper_heat")) {
            List list = z2.a;
            return z2.b(E().a);
        }
        boolean zStartsWith = str.startsWith("widget:");
        Map map = this.n0;
        if (zStartsWith) {
            String str2 = (String) map.get(th.C(str, "widget:"));
            return str2 == null ? si.a("widget", new Object[0]) : str2;
        }
        if (str.equals("collapse")) {
            return u() ? si.a("expand_widgets", new Object[0]) : si.a("collapse_widgets", new Object[0]);
        }
        if (str.startsWith("app:")) {
            ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(th.C(str, "app:"));
            if (componentNameUnflattenFromString != null) {
                try {
                    ffVar = getPackageManager().getActivityInfo(componentNameUnflattenFromString, 0).loadLabel(getPackageManager()).toString();
                } catch (Throwable th) {
                    ffVar = new ff(th);
                }
                if (ffVar instanceof ff) {
                    ffVar = null;
                }
                String str3 = (String) ffVar;
                if (str3 != null) {
                    return str3;
                }
            }
            return si.a("app", new Object[0]);
        }
        if (str.startsWith("split:")) {
            return defpackage.c.d(si.a("split", new Object[0]), ": ", k8.A0(th.C(str, "split:"), this));
        }
        if (str.equals("steering")) {
            return si.a("steering_wheel_heating", new Object[0]);
        }
        if (str.equals("gap")) {
            return si.a("dock_gap", new Object[0]);
        }
        if (str.startsWith("x:")) {
            List list2 = j7.a;
            String strC = th.C(str, "x:");
            String str4 = (String) j7.b.get(strC);
            if (str4 != null) {
                return si.a(str4, new Object[0]);
            }
            Map map2 = q8.t;
            String strI = k8.i(this, strC);
            return strI == null ? si.a("action", new Object[0]) : strI;
        }
        if (str.equals(this.C0)) {
            String strC2 = c(this.B0);
            return strC2 == null ? si.a("app_widget", new Object[0]) : strC2;
        }
        Set set = aj.i;
        if (sh.e().containsKey(str)) {
            return (String) hb.g0(sh.e(), str);
        }
        String str5 = (String) hb.j0(new yc("home", si.a("home", new Object[0])), new yc("apps", si.a("all_apps", new Object[0])), new yc("favorites", si.a("favorites", new Object[0])), new yc("settings", si.a("settings", new Object[0])), new yc("climate", si.a("climate", new Object[0])), new yc("steering", si.a("steering_wheel_heating", new Object[0])), new yc("music", si.a("play_pause", new Object[0])), new yc("empty", si.a("assign", new Object[0])), new yc("temp", si.a("temperature_268", new Object[0])), new yc("temp2", si.a("temperature_compact", new Object[0])), new yc("voice", si.a("alice", new Object[0])), new yc("ac", "A/C"), new yc("ac_max", "A/C MAX"), new yc("auto", "AUTO"), new yc("recirc", si.a("recirculation", new Object[0])), new yc("defrost_rear", si.a("rear_window_heating", new Object[0])), new yc("collapse", si.a("collapse_widgets", new Object[0])), new yc("nav_window", si.a("navigation_206", new Object[0]))).get(str);
        if (str5 != null) {
            return str5;
        }
        String str6 = (String) o2.b().get(str);
        if (str6 != null) {
            return str6;
        }
        String str7 = (String) map.get(str);
        return str7 == null ? si.a("action", new Object[0]) : str7;
    }

    public final void X(y5 y5Var) {
        if (!A()) {
            y5Var.a();
        } else {
            this.u0 = y5Var;
            h();
        }
    }

    public final void Y() {
        Intent launchIntentForPackage;
        jc jcVar = this.b0;
        String str = jcVar.e;
        if (!jcVar.b || th.A(str)) {
            str = null;
        }
        if (str != null && (launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(str)) != null) {
            Q(launchIntentForPackage);
            return;
        }
        if (m(1) != null && c(1) != null) {
            a0(1);
            return;
        }
        Intent launchIntentForPackage2 = str != null ? getPackageManager().getLaunchIntentForPackage(str) : null;
        if (launchIntentForPackage2 != null) {
            Q(launchIntentForPackage2);
        } else {
            a0(1);
        }
    }

    public final void Z(int i) {
        int i2 = 1;
        int i3 = 0;
        if (k8.g) {
            k8.f++;
            k8.g = false;
        }
        ComponentName componentNameM = m(i);
        if (componentNameM == null) {
            k8.g0(this, Integer.valueOf(i));
            return;
        }
        ArrayList arrayList = this.w;
        if (arrayList == null || !arrayList.isEmpty()) {
            int size = arrayList.size();
            int i4 = 0;
            while (i4 < size) {
                Object obj = arrayList.get(i4);
                i4++;
                b bVar = (b) obj;
                if (bVar.getSlot() == i || k8.e(bVar.l(), componentNameM.getPackageName())) {
                    sc scVar = this.y;
                    int i5 = scVar.a + 1;
                    scVar.a = i5;
                    scVar.b = true;
                    scVar.c = false;
                    scVar.d = false;
                    ArrayList arrayList2 = this.w;
                    ArrayList arrayList3 = new ArrayList();
                    int size2 = arrayList2.size();
                    int i6 = 0;
                    while (i6 < size2) {
                        Object obj2 = arrayList2.get(i6);
                        i6++;
                        b bVar2 = (b) obj2;
                        if (bVar2.getSlot() == i || k8.e(bVar2.l(), componentNameM.getPackageName())) {
                            arrayList3.add(obj2);
                        }
                    }
                    int size3 = arrayList3.size();
                    while (i3 < size3) {
                        Object obj3 = arrayList3.get(i3);
                        i3++;
                        b bVar3 = (b) obj3;
                        bVar3.u = true;
                        bVar3.k();
                        bVar3.h();
                    }
                    getWindow().getDecorView().postDelayed(new u(this, i5, componentNameM, i2), 300L);
                    return;
                }
            }
        }
        try {
            startActivity(Intent.makeMainActivity(componentNameM).addFlags(268435456));
        } catch (Exception unused) {
            v0(si.a("app_unavailable_on_this_head_unit", new Object[0]));
        }
    }

    public final j9 a(String str, boolean z) {
        j9 j9Var = new j9(this, z(), M(str), W(str), z);
        int i = zj.a;
        SharedPreferences sharedPreferencesB = B();
        sharedPreferencesB.getClass();
        j9Var.setFaceAlpha(((100 - pi.s(zj.c(sharedPreferencesB, "dock_button_transparency_".concat(str)), 0, 100)) * 255) / 100);
        j9Var.setOnClickListener(new na(str, this));
        this.B.add(new yc(str, j9Var));
        return j9Var;
    }

    public final void a0(int i) {
        ComponentName componentNameM = m(i);
        if (componentNameM == null || c(i) == null) {
            k8.g0(this, Integer.valueOf(i));
            return;
        }
        Intent intentMakeMainActivity = Intent.makeMainActivity(componentNameM);
        intentMakeMainActivity.getClass();
        Q(intentMakeMainActivity);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public final void attachBaseContext(Context context) {
        context.getClass();
        super.attachBaseContext(j0.e(context));
    }

    public final t b(boolean z) {
        t tVar = new t(this, z(), z, new b2(this, 11), new b2(this, 12), new v1(this, 17));
        this.v0.add(tVar);
        v2 v2Var = this.Z.i;
        if (v2Var.f.isEmpty() && v2Var.a.isEmpty()) {
            v2Var = null;
        }
        tVar.d(v2Var, 1);
        return tVar;
    }

    public final void b0(FrameLayout frameLayout) {
        List listJ0;
        Bitmap bitmap;
        ArrayList arrayList = this.N;
        if (!arrayList.contains(frameLayout)) {
            arrayList.add(frameLayout);
        }
        if (B().getBoolean("show_vehicle", true)) {
            String str = E().a;
            File fileO = o(str);
            String strC = uh.c(str, F(), !B().getBoolean("show_vehicle", true), fileO.exists());
            if (!fileO.exists() && strC == null) {
                TextView textViewU0 = u0(si.a("add_an_image", new Object[0]), 22.0f, this.p);
                textViewU0.setGravity(17);
                frameLayout.addView(textViewU0, new FrameLayout.LayoutParams(-1, -1));
                return;
            }
            if (strC != null) {
                ca caVar = ea.a;
                listJ0 = i3.J0(i3.K0(j3.i0("vehicles/" + th.H(strC, '.') + ".webp", "vehicles/".concat(strC))));
            } else {
                listJ0 = null;
            }
            if (listJ0 == null) {
                listJ0 = u4.a;
            }
            if (fileO.exists()) {
                strC = str + ":" + fileO.lastModified();
            } else if (strC == null) {
                strC = "";
            }
            String str2 = strC;
            if (k8.e(this.l, str2) && (bitmap = this.k) != null) {
                n0(frameLayout, bitmap);
                return;
            }
            TextView textViewU1 = u0(si.a("loading_image", new Object[0]), 15.0f, this.p);
            textViewU1.setGravity(17);
            frameLayout.addView(textViewU1, new FrameLayout.LayoutParams(-1, -1));
            int i = this.n + 1;
            this.n = i;
            this.b.execute(new qa(this, fileO, listJ0, i, frameLayout, str2));
        }
    }

    public final String c(int i) {
        Object ffVar;
        ComponentName componentNameM = m(i);
        if (componentNameM == null) {
            return null;
        }
        try {
            ffVar = getPackageManager().getActivityInfo(componentNameM, 0).loadLabel(getPackageManager()).toString();
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        return (String) (ffVar instanceof ff ? null : ffVar);
    }

    /* JADX WARN: Code duplicated, block: B:167:0x038f  */
    /* JADX WARN: Code duplicated, block: B:303:0x0672  */
    /* JADX WARN: Code duplicated, block: B:304:0x068e  */
    /* JADX WARN: Code duplicated, block: B:307:0x069e  */
    /* JADX WARN: Code duplicated, block: B:308:0x06b4  */
    /* JADX WARN: Multi-variable type inference failed */
    public final void c0() {
        boolean z;
        String str;
        float f;
        char c;
        float f2;
        Float f3;
        String str2;
        Float fValueOf;
        Object ffVar;
        float fFloatValue;
        Object ffVar2;
        boolean z2;
        String strA;
        String strA2;
        List<String> list;
        int i;
        String strValueOf;
        Integer numValueOf;
        String strA3;
        WindowManager windowManager;
        int iA;
        bj bjVar = this.Z;
        ArrayList arrayListD0 = i3.D0(this.B, this.E);
        int size = arrayListD0.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayListD0.get(i2);
            i2++;
            yc ycVar = (yc) obj;
            String str3 = (String) ycVar.a;
            j9 j9Var = (j9) ycVar.b;
            View icon = j9Var.getIcon();
            if (icon instanceof cj) {
                x0(str3, (cj) icon);
            }
            yc ycVarO = O(str3);
            boolean zBooleanValue = ((Boolean) ycVarO.a).booleanValue();
            int iIntValue = ((Number) ycVarO.b).intValue();
            TextView textView = j9Var.e;
            boolean z3 = j9Var.a;
            if (j9Var.g != zBooleanValue || j9Var.h != iIntValue) {
                j9Var.g = zBooleanValue;
                j9Var.h = iIntValue;
                if (!zBooleanValue || z3) {
                    iA = zBooleanValue ? l8.a(iIntValue, z3) : l8.j(z3);
                } else {
                    int i3 = l8.a;
                    iA = Color.rgb(37, 99, 235);
                }
                textView.setTextColor(iA);
                textView.setTypeface(z9.c());
                j9Var.invalidate();
            }
            if (icon instanceof d9) {
                if (str3.equals("collapse")) {
                    ((d9) icon).setName(L(str3));
                }
                int i4 = l8.a;
                ((d9) icon).setColor(l8.b(z(), zBooleanValue, iIntValue));
            }
        }
        ArrayList arrayList = this.C;
        int size2 = arrayList.size();
        int i5 = 0;
        while (i5 < size2) {
            Object obj2 = arrayList.get(i5);
            i5++;
            f8.e(this, (q7) ((yc) obj2).b);
        }
        ArrayList arrayList2 = this.D;
        int size3 = arrayList2.size();
        int i6 = 0;
        while (true) {
            z = true;
            if (i6 >= size3) {
                break;
            }
            int i7 = i6 + 1;
            q7 q7Var = (q7) arrayList2.get(i6);
            if (f8.e(this, q7Var) && q7Var.getOver() == s7.e) {
                Object tag = q7Var.getTag();
                WindowManager.LayoutParams layoutParams = tag instanceof WindowManager.LayoutParams ? (WindowManager.LayoutParams) tag : null;
                if (layoutParams != null && (windowManager = (WindowManager) getSystemService(WindowManager.class)) != null) {
                    f8.d(layoutParams, q7Var, windowManager, true);
                    q7Var.animate().cancel();
                    q7Var.animate().alpha(1.0f).setDuration(150L).start();
                    WeakHashMap weakHashMap = f8.a;
                    Runnable runnable = (Runnable) weakHashMap.remove(q7Var);
                    if (runnable != null) {
                        q7Var.removeCallbacks(runnable);
                    }
                    l lVar = new l(q7Var, layoutParams, this, windowManager, 2);
                    weakHashMap.put(q7Var, lVar);
                    q7Var.postDelayed(lVar, 4000L);
                }
            }
            i6 = i7;
        }
        Float f4 = (Float) bjVar.i.a.get(1);
        Integer num = (Integer) bjVar.i.f.get(268501248);
        boolean z4 = (num == null || num.intValue() == 0) ? false : true;
        List list2 = z2.a;
        Integer numC = z2.c((Integer) bjVar.i.f.get(268566784));
        ArrayList arrayList3 = this.J;
        int size4 = arrayList3.size();
        int i8 = 0;
        while (true) {
            String str4 = "";
            String strD = "—";
            if (i8 >= size4) {
                break;
            }
            Object obj3 = arrayList3.get(i8);
            i8++;
            s9 s9Var = (s9) obj3;
            if (f4 != null) {
                float fFloatValue2 = f4.floatValue();
                List list3 = z2.a;
                strD = z2.d(fFloatValue2);
            }
            boolean z5 = (!z4 || f4 == null || f4.floatValue() > 17.0f) ? z : false;
            boolean z6 = (!z4 || f4 == null || f4.floatValue() < 32.0f) ? z : false;
            if (z4) {
                int iIntValue2 = numC != null ? numC.intValue() : 0;
                if (iIntValue2 > 8) {
                    iIntValue2 = 0;
                }
                numValueOf = Integer.valueOf(iIntValue2);
            } else {
                numValueOf = null;
            }
            Boolean boolValueOf = Boolean.valueOf(z4);
            y8 y8Var = s9Var.e;
            Float f5 = f4;
            r9 r9Var = s9Var.i;
            Integer num2 = numValueOf;
            r9 r9Var2 = s9Var.h;
            Integer num3 = numC;
            TextView textView2 = s9Var.d;
            s9Var.f = boolValueOf;
            r9 r9Var3 = s9Var.g;
            if (r9Var3 != null) {
                r9Var3.invalidate();
            }
            CharSequence text = textView2.getText();
            if (!k8.e(text != null ? text.toString() : null, strD)) {
                textView2.setText(strD);
                s9Var.requestLayout();
            }
            r9Var2.setEnabled(z5);
            r9Var.setEnabled(z6);
            r9Var2.setAlpha(z5 ? 1.0f : 0.35f);
            r9Var.setAlpha(z6 ? 1.0f : 0.35f);
            y8Var.setVisibility(num2 == null ? 4 : 0);
            y8Var.setLevel(num2 != null ? num2.intValue() : 0);
            String strA4 = si.a("temperature_0", strD);
            if (num2 != null && (strA3 = si.a("fan_0_of_1", Integer.valueOf(num2.intValue()), 8)) != null) {
                str4 = strA3;
            }
            s9Var.setContentDescription(strA4 + str4);
            f4 = f5;
            numC = num3;
            z = true;
        }
        Float f6 = f4;
        Throwable th = null;
        ArrayList arrayList4 = this.P;
        int size5 = arrayList4.size();
        int i9 = 0;
        while (i9 < size5) {
            Object obj4 = arrayList4.get(i9);
            i9++;
            s8 s8Var = (s8) obj4;
            Float f7 = s8Var.getZone() == 1 ? f6 : (Float) bjVar.i.a.get(Integer.valueOf(s8Var.getZone()));
            Integer num4 = (Integer) bjVar.i.f.get(268501504);
            if (num4 != null) {
                num4.intValue();
            }
            s8Var.c(f7, z4);
        }
        ArrayList arrayList5 = this.K;
        int size6 = arrayList5.size();
        int i10 = 0;
        while (i10 < size6) {
            Object obj5 = arrayList5.get(i10);
            i10++;
            ((p3) obj5).b(bjVar.a);
        }
        ArrayList arrayList6 = this.L;
        int size7 = arrayList6.size();
        int i11 = 0;
        while (i11 < size7) {
            Object obj6 = arrayList6.get(i11);
            i11++;
            w2 w2Var = (w2) obj6;
            w2Var.b((Float) bjVar.i.a.get(Integer.valueOf(w2Var.getZone())));
        }
        ArrayList arrayList7 = this.v0;
        int size8 = arrayList7.size();
        int i12 = 0;
        while (i12 < size8) {
            Object obj7 = arrayList7.get(i12);
            i12++;
            t tVar = (t) obj7;
            v2 v2Var = bjVar.i;
            if (v2Var.f.isEmpty() && v2Var.a.isEmpty()) {
                v2Var = null;
            }
            int i13 = t.o;
            tVar.d(v2Var, 1);
        }
        q8 q8Var = this.D0;
        if (q8Var != null) {
            q8Var.e(bjVar);
        }
        char c2 = 0;
        String str5 = "tank_liters";
        float f8 = 100.0f;
        float f9 = 0.0f;
        if (!this.G.isEmpty()) {
            float f10 = B().getFloat("tank_liters", 50.0f);
            Float f11 = bjVar.c;
            Float fValueOf2 = (f11 == null || Math.abs(f10) > Float.MAX_VALUE || f10 <= 0.0f) ? null : Float.valueOf((pi.r(f11.floatValue(), 0.0f, 100.0f) / 100.0f) * f10);
            if (fValueOf2 == null) {
                fValueOf2 = bjVar.b;
                if (fValueOf2 != null) {
                    float fFloatValue3 = fValueOf2.floatValue();
                    if (f10 > 0.0f && fFloatValue3 > f10 * 1.05f) {
                        fValueOf2 = null;
                    }
                } else {
                    fValueOf2 = null;
                }
            }
            ArrayList arrayList8 = this.G;
            int size9 = arrayList8.size();
            int i14 = 0;
            while (i14 < size9) {
                Object obj8 = arrayList8.get(i14);
                i14++;
                q3 q3Var = (q3) obj8;
                Float f12 = bjVar.c;
                q3Var.getClass();
                if (fValueOf2 == null || (strValueOf = String.valueOf(Math.round(fValueOf2.floatValue()))) == null) {
                    strValueOf = "—";
                }
                boolean z7 = f12 != null && f12.floatValue() <= 12.0f;
                if (!strValueOf.equals(q3Var.c) || z7 != q3Var.d) {
                    q3Var.c = strValueOf;
                    q3Var.d = z7;
                    q3Var.setContentDescription(si.a("fuel_compact", new Object[0]) + ": " + si.a("0_l", strValueOf));
                    q3Var.invalidate();
                }
                f8 = f8;
            }
        }
        float f13 = f8;
        ArrayList arrayList9 = this.Q;
        int size10 = arrayList9.size();
        int i15 = 0;
        while (i15 < size10) {
            int i16 = i15 + 1;
            yc ycVar2 = (yc) arrayList9.get(i15);
            String str6 = (String) ycVar2.a;
            q9 q9Var = (q9) ycVar2.b;
            int iHashCode = str6.hashCode();
            if (iHashCode != 3154358) {
                if (iHashCode == 109757599) {
                    f = f9;
                    if (str6.equals("stats")) {
                        long jElapsedRealtime = SystemClock.elapsedRealtime();
                        str = str5;
                        if (!this.U && (this.T == 0 || jElapsedRealtime - this.T > 30000)) {
                            this.U = true;
                            try {
                                this.b.execute(new sa(this, 2));
                                ffVar2 = ti.a;
                            } catch (Throwable th2) {
                                ffVar2 = new ff(th2);
                            }
                            if (gf.a(ffVar2) != null) {
                                this.U = false;
                            }
                        }
                        di diVar = this.S;
                        float f14 = diVar != null ? diVar.a : f;
                        li liVar = ni.N;
                        li liVar2 = ni.N;
                        if (liVar2 != null) {
                            fFloatValue = liVar2.d;
                        } else {
                            try {
                                String string = getSharedPreferences("panel", 0).getString("stats_open_trip", th);
                                ffVar = Float.valueOf(string != null ? (float) new JSONObject(string).optDouble("km", 0.0d) : f);
                            } catch (Throwable th3) {
                                ffVar = new ff(th3);
                            }
                            Object objValueOf = Float.valueOf(f);
                            if (ffVar instanceof ff) {
                                ffVar = objValueOf;
                            }
                            fFloatValue = ((Number) ffVar).floatValue();
                        }
                        float f15 = f14 + fFloatValue;
                        q9Var.a(si.a("stats_now", new Object[0]), f15 >= f13 ? String.valueOf((int) f15) : String.format(si.b.b, "%.1f", Arrays.copyOf(new Object[]{Float.valueOf(f15)}, 1)), si.a("km", new Object[0]), "carfront", Color.rgb(48, 176, 199), null);
                        f2 = f;
                        c = 0;
                    } else {
                        c = c2;
                        str = str5;
                    }
                } else if (iHashCode == 344200471 && str6.equals("automation")) {
                    SharedPreferences sharedPreferences = getSharedPreferences("panel", 0);
                    String strA5 = si.a("automation", new Object[0]);
                    if (sharedPreferences.getBoolean("auto_enabled", false)) {
                        if (sharedPreferences.getBoolean("auto_enabled", false) && ((list = a1.a) == null || !list.isEmpty())) {
                            i = 0;
                            for (String str7 : list) {
                                str7.getClass();
                                float f16 = f9;
                                if (sharedPreferences.getBoolean("auto_" + str7 + "_on", false) && (i = i + 1) < 0) {
                                    j3.l0();
                                    throw th;
                                }
                                f9 = f16;
                            }
                            f = f9;
                        } else {
                            f = f9;
                            i = 0;
                        }
                        strA = String.valueOf(i);
                        z2 = false;
                    } else {
                        f = f9;
                        z2 = false;
                        strA = si.a("off_236", new Object[0]);
                    }
                    String str8 = strA;
                    if (sharedPreferences.getBoolean("auto_enabled", z2)) {
                        List list4 = si.a;
                        strA2 = si.a("of_0", Integer.valueOf(a1.a.size()));
                    } else {
                        strA2 = "";
                    }
                    q9Var.a(strA5, str8, strA2, "ambient", Color.rgb(175, 82, 222), null);
                    c = c2;
                    str = str5;
                    f2 = f;
                } else {
                    str = str5;
                    f = f9;
                    c = c2;
                }
                f3 = bjVar.a;
                String strA6 = si.a("battery", new Object[0]);
                if (f3 != null) {
                    str2 = String.format(si.b.b, "%.1f", Arrays.copyOf(new Object[]{Float.valueOf(f3.floatValue())}, 1));
                } else {
                    str2 = "—";
                }
                String strA7 = si.a("v", new Object[0]);
                int i17 = l8.a;
                if (f3 != null) {
                    f2 = f;
                    fValueOf = Float.valueOf(pi.r(f3.floatValue() / 16.0f, f2, 1.0f));
                } else {
                    f2 = f;
                    fValueOf = null;
                }
                q9Var.a(strA6, str2, strA7, "batt", i17, fValueOf);
            } else {
                str = str5;
                f = f9;
                if (str6.equals("fuel")) {
                    int i18 = B().getInt("fuel_display", 0);
                    c = 0;
                    String strX = pi.X(bjVar, i18, B().getFloat(str, 50.0f));
                    String strA8 = i18 == 0 ? si.a("range", new Object[0]) : si.a("fuel", new Object[0]);
                    String strH = strX != null ? th.H(strX, ' ') : "—";
                    String strE = strX != null ? th.E(strX, ' ') : null;
                    String str9 = strE == null ? "" : strE;
                    String str10 = i18 == 0 ? "carfront" : "pump";
                    int iF = l8.f();
                    Float f17 = bjVar.c;
                    q9Var.a(strA8, strH, str9, str10, iF, f17 != null ? Float.valueOf(f17.floatValue() / f13) : null);
                    f2 = f;
                } else {
                    c = 0;
                    f3 = bjVar.a;
                    String strA9 = si.a("battery", new Object[0]);
                    if (f3 != null) {
                        str2 = String.format(si.b.b, "%.1f", Arrays.copyOf(new Object[]{Float.valueOf(f3.floatValue())}, 1));
                    } else {
                        str2 = "—";
                    }
                    String strA10 = si.a("v", new Object[0]);
                    int i19 = l8.a;
                    if (f3 != null) {
                        f2 = f;
                        fValueOf = Float.valueOf(pi.r(f3.floatValue() / 16.0f, f2, 1.0f));
                    } else {
                        f2 = f;
                        fValueOf = null;
                    }
                    q9Var.a(strA9, str2, strA10, "batt", i19, fValueOf);
                }
            }
            c2 = c;
            i15 = i16;
            th = null;
            f9 = f2;
            str5 = str;
        }
    }

    public final void d() {
        if (!B().getBoolean("fullscreen", true) && this.d0 == null && this.e0 == null && this.f0 == null) {
            getWindow().clearFlags(1024);
            getWindow().getDecorView().setSystemUiVisibility(z() ? 0 : 8192);
            return;
        }
        getWindow().addFlags(1024);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.layoutInDisplayCutoutMode = 1;
        window.setAttributes(attributes);
        getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    public final void d0() {
        v8 v8Var = this.q0;
        if (v8Var == null) {
            return;
        }
        this.q0 = null;
        t0();
        try {
            WindowManager windowManager = (WindowManager) getSystemService(WindowManager.class);
            if (windowManager != null) {
                windowManager.removeView(v8Var);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        keyEvent.getClass();
        if (this.f0 == null || !bf.b.contains(Integer.valueOf(keyEvent.getKeyCode()))) {
            return super.dispatchKeyEvent(keyEvent);
        }
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Object ffVar;
        motionEvent.getClass();
        if (this.f0 == null) {
            if ((getWindow().getAttributes().flags & 1024) != 0) {
                int iIntValue = ((Number) this.j0.a()).intValue();
                if (motionEvent.getActionMasked() == 0) {
                    sh.d = motionEvent.getRawY() <= ((float) iIntValue);
                    sh.e = false;
                    sh.f = motionEvent.getRawY();
                }
                if (sh.d) {
                    Object ffVar2 = null;
                    if (!sh.c) {
                        sh.c = true;
                        Object systemService = getSystemService("statusbar");
                        sh.a = systemService;
                        sh.b = systemService != null ? sh.f(systemService.getClass(), "onTouchEventFromOther", MotionEvent.class) : null;
                    }
                    Method method = sh.b;
                    if (method != null) {
                        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
                        motionEventObtain.setLocation(motionEvent.getRawX(), motionEvent.getRawY());
                        try {
                            ffVar = method.invoke(sh.a, motionEventObtain);
                        } catch (Throwable th) {
                            ffVar = new ff(th);
                        }
                        Throwable thA = gf.a(ffVar);
                        if (thA != null) {
                            Log.w("ReferencePanel.Shade", "shade touch refused", thA);
                            sh.b = null;
                        }
                        motionEventObtain.recycle();
                    } else if (!sh.e && motionEvent.getActionMasked() == 2 && motionEvent.getRawY() - sh.f > iIntValue * 1.5f) {
                        sh.e = true;
                        Object obj = sh.a;
                        if (obj != null) {
                            try {
                                Method methodF = sh.f(obj.getClass(), "expandNotificationsPanel", new Class[0]);
                                if (methodF != null) {
                                    ffVar2 = methodF.invoke(obj, null);
                                }
                            } catch (Throwable th2) {
                                ffVar2 = new ff(th2);
                            }
                            Throwable thA2 = gf.a(ffVar2);
                            if (thA2 != null) {
                                Log.w("ReferencePanel.Shade", "shade refused", thA2);
                            }
                        }
                    }
                    if (motionEvent.getActionMasked() == 1 || motionEvent.getActionMasked() == 3) {
                        sh.d = false;
                    }
                }
            }
            if (motionEvent.getActionMasked() == 0) {
                sc scVar = this.y;
                if (scVar.b && !scVar.c) {
                    j();
                }
            }
            if (this.d0 == null) {
                h0();
                return super.dispatchTouchEvent(motionEvent);
            }
            if (motionEvent.getActionMasked() == 0) {
                s0();
            }
        }
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0041  */
    public final void e() {
        String strA;
        FrameLayout frameLayout = this.c0;
        if (frameLayout == null) {
            return;
        }
        ze zeVarA = bf.a(this);
        int i = 0;
        boolean z = zeVarA != null;
        b2 b2Var = new b2(this, 26);
        SharedPreferences sharedPreferences = getSharedPreferences("panel", 0);
        long jCurrentTimeMillis = System.currentTimeMillis() - sharedPreferences.getLong("region_pass_checked", 0L);
        if (!pi.d) {
            if (0 > jCurrentTimeMillis) {
                pi.d = true;
                new Thread(new l(getApplicationContext(), sharedPreferences, new Handler(Looper.getMainLooper()), b2Var, 3)).start();
            } else {
                if (jCurrentTimeMillis >= (z ? 600000L : 86400000L)) {
                    pi.d = true;
                    new Thread(new l(getApplicationContext(), sharedPreferences, new Handler(Looper.getMainLooper()), b2Var, 3)).start();
                }
            }
        }
        df dfVar = this.f0;
        if ((dfVar != null ? dfVar.getTag() : null) == zeVarA) {
            if ((zeVarA != null) == (this.f0 != null)) {
                return;
            }
        }
        View view = this.f0;
        if (view != null) {
            frameLayout.removeView(view);
        }
        this.f0 = null;
        if (zeVarA != null) {
            s0();
            float f = getResources().getDisplayMetrics().density;
            df dfVar2 = new df(this);
            dfVar2.setOrientation(1);
            dfVar2.setGravity(17);
            dfVar2.setBackgroundColor(Color.rgb(11, 14, 20));
            dfVar2.setClickable(true);
            dfVar2.setFocusable(true);
            dfVar2.setFocusableInTouchMode(true);
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(org.reference.panel.R.drawable.ic_ReferencePanel);
            int i2 = (int) (150.0f * f);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i2, i2);
            layoutParams.bottomMargin = (int) (28.0f * f);
            dfVar2.addView(imageView, layoutParams);
            cf cfVar = new cf(f, i);
            dfVar2.addView(df.a(this, si.a("region_lock_title", new Object[0]), 30.0f, -1), (ViewGroup.LayoutParams) cfVar.b(0));
            dfVar2.addView(df.a(this, "ReferencePanel works only in Russia, Belarus and Kazakhstan", 22.0f, Color.rgb(233, 240, 247)), (ViewGroup.LayoutParams) cfVar.b(10));
            int iOrdinal = zeVarA.ordinal();
            if (iOrdinal == 0) {
                Locale localeB = bf.b();
                String displayLanguage = localeB.getDisplayLanguage(localeB);
                if (th.A(displayLanguage)) {
                    displayLanguage = localeB.getLanguage();
                }
                strA = si.a("region_lock_language_0", displayLanguage);
            } else {
                if (iOrdinal != 1) {
                    throw new vc();
                }
                strA = si.a("region_lock_location", new Object[0]);
            }
            dfVar2.addView(df.a(this, strA, 20.0f, Color.rgb(157, 177, 196)), (ViewGroup.LayoutParams) cfVar.b(18));
            dfVar2.addView(df.a(this, si.a("region_pass_code_0", pi.w(this)), 17.0f, Color.rgb(120, 140, 160)), (ViewGroup.LayoutParams) cfVar.b(30));
            dfVar2.setTag(zeVarA);
            z9.a(dfVar2);
            frameLayout.addView(dfVar2, new FrameLayout.LayoutParams(-1, -1));
            this.f0 = dfVar2;
        }
        d();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [ab, android.view.ViewGroup] */
    /* JADX WARN: Type inference failed for: r24v0, types: [android.app.Activity, android.content.Context, org.reference.panel.MainActivity] */
    /* JADX WARN: Type inference failed for: r3v1, types: [android.content.SharedPreferences$Editor] */
    /* JADX WARN: Type inference failed for: r3v49, types: [android.view.View, android.view.ViewGroup, android.widget.FrameLayout] */
    /* JADX WARN: Type inference failed for: r6v48, types: [java.util.AbstractCollection, java.util.LinkedHashSet] */
    /* JADX WARN: Type inference failed for: r6v49, types: [java.util.Collection, java.util.LinkedHashSet] */
    /* JADX WARN: Type inference failed for: r6v50, types: [java.util.Set] */
    /* JADX WARN: Type inference failed for: r6v52, types: [java.util.Set] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void e0() {
        Map map;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        boolean z;
        i8 i8Var;
        ?? linkedHashSet;
        if (isFinishing() || isDestroyed()) {
            return;
        }
        if (!this.c) {
            k();
        }
        ?? r1 = this.t0;
        Set set = rf.d;
        ?? Edit = B().edit();
        boolean z2 = false;
        for (String str : j3.i0("desktop_vehicle_cards", "wall_vehicle_cards")) {
            SharedPreferences sharedPreferencesB = B();
            Set<String> set2 = x4.a;
            Set<String> stringSet = sharedPreferencesB.getStringSet(str, set2);
            if (stringSet != null) {
                set2 = stringSet;
            }
            if (!set2.isEmpty()) {
                Iterator it = set2.iterator();
                while (it.hasNext()) {
                    if (set.contains((String) it.next())) {
                        if (set.isEmpty()) {
                            linkedHashSet = i3.L0(set2);
                        } else if (set instanceof Set) {
                            linkedHashSet = new LinkedHashSet();
                            for (Object obj : set2) {
                                if (!set.contains(obj)) {
                                    linkedHashSet.add(obj);
                                }
                            }
                        } else {
                            linkedHashSet = new LinkedHashSet(set2);
                            linkedHashSet.removeAll(set);
                        }
                        Edit.putStringSet(str, linkedHashSet);
                        z2 = true;
                        break;
                    }
                }
            }
        }
        Map<String, ?> all = B().getAll();
        all.getClass();
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            key.getClass();
            if (key.startsWith("menu_action_") && (value instanceof String) && set.contains(value)) {
                Edit.putString(key, "empty");
                z2 = true;
            }
        }
        if (z2) {
            Edit.apply();
        }
        LinkedHashSet linkedHashSet2 = pc.a;
        pc.a(this, this.d && !this.c);
        ArrayList arrayList = this.w;
        int size = arrayList.size();
        int i7 = 0;
        while (i7 < size) {
            Object obj2 = arrayList.get(i7);
            i7++;
            ((b) obj2).k();
        }
        this.x = 0;
        if (this.c) {
            ScrollView scrollView = this.s;
            this.t = scrollView != null ? scrollView.getScrollY() : this.t;
        }
        this.s = null;
        ca caVar = ea.a;
        SharedPreferences sharedPreferencesB2 = B();
        sharedPreferencesB2.getClass();
        if (!ea.b || sharedPreferencesB2.contains("launcher_theme")) {
            ea.b = true;
            Map<String, ?> all2 = sharedPreferencesB2.getAll();
            all2.getClass();
            Object obj3 = all2.get("launcher_theme");
            String str2 = obj3 instanceof String ? (String) obj3 : null;
            Set<String> setKeySet = all2.keySet();
            ArrayList arrayList2 = new ArrayList();
            for (Object obj4 : setKeySet) {
                String str3 = (String) obj4;
                if (ci.t(str3, "theme_profile_") || str3.startsWith("dock_wing_")) {
                    arrayList2.add(obj4);
                }
            }
            if (str2 == null && arrayList2.isEmpty()) {
                map = v4.a;
            } else {
                eb ebVar = new eb(8);
                int size2 = arrayList2.size();
                int i8 = 0;
                while (i8 < size2) {
                    Object obj5 = arrayList2.get(i8);
                    i8++;
                    ebVar.put((String) obj5, null);
                }
                ebVar.put("launcher_theme", null);
                if (str2 != null && !str2.equals("classic") && k8.e(all2.get("theme_profile_classic__".concat("ready")), Boolean.TRUE)) {
                    Set<String> setKeySet2 = all2.keySet();
                    ArrayList arrayList3 = new ArrayList();
                    for (Object obj6 : setKeySet2) {
                        if (uh.a((String) obj6)) {
                            arrayList3.add(obj6);
                        }
                    }
                    int size3 = arrayList3.size();
                    int i9 = 0;
                    while (i9 < size3) {
                        Object obj7 = arrayList3.get(i9);
                        i9++;
                        ebVar.put((String) obj7, null);
                    }
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    for (Map.Entry<String, ?> entry2 : all2.entrySet()) {
                        if (ci.t(entry2.getKey(), "theme_profile_classic__")) {
                            linkedHashMap.put(entry2.getKey(), entry2.getValue());
                        }
                    }
                    for (Map.Entry entry3 : linkedHashMap.entrySet()) {
                        String str4 = (String) entry3.getKey();
                        Object value2 = entry3.getValue();
                        String strC = th.C(str4, "theme_profile_classic__");
                        if (uh.a(strC)) {
                            ebVar.put(strC, value2);
                        }
                    }
                }
                if (k8.e(ebVar.containsKey("background") ? ebVar.get("background") : all2.get("background"), 6)) {
                    ebVar.put("background", null);
                }
                ebVar.b();
                ebVar.m = true;
                if (ebVar.i > 0) {
                    map = ebVar;
                } else {
                    map = eb.n;
                    map.getClass();
                }
            }
            if (!map.isEmpty()) {
                SharedPreferences.Editor editorEdit = sharedPreferencesB2.edit();
                for (Map.Entry entry4 : map.entrySet()) {
                    String str5 = (String) entry4.getKey();
                    Object value3 = entry4.getValue();
                    if (value3 == null) {
                        editorEdit.remove(str5);
                    } else if (value3 instanceof Boolean) {
                        editorEdit.putBoolean(str5, ((Boolean) value3).booleanValue());
                    } else if (value3 instanceof Integer) {
                        editorEdit.putInt(str5, ((Number) value3).intValue());
                    } else if (value3 instanceof Float) {
                        editorEdit.putFloat(str5, ((Number) value3).floatValue());
                    } else if (value3 instanceof String) {
                        editorEdit.putString(str5, (String) value3);
                    } else if (value3 instanceof Set) {
                        ArrayList arrayList4 = new ArrayList();
                        for (Object obj8 : (Iterable) value3) {
                            if (obj8 instanceof String) {
                                arrayList4.add(obj8);
                            }
                        }
                        editorEdit.putStringSet(str5, i3.L0(arrayList4));
                    }
                }
                if (editorEdit.commit()) {
                    File[] fileArrListFiles = getFilesDir().listFiles(new da());
                    if (fileArrListFiles != null) {
                        for (File file : fileArrListFiles) {
                            file.delete();
                        }
                    }
                    l0 l0Var = ba.h;
                    ba baVar = ba.k;
                    if (baVar != null) {
                        baVar.c(this);
                    }
                }
            }
        }
        l0 l0Var2 = ba.h;
        ba.n = z();
        this.o = z() ? Color.rgb(233, 240, 247) : Color.rgb(23, 43, 62);
        if (z()) {
            i = 177;
            i2 = 196;
            i3 = 157;
        } else {
            i = 93;
            i2 = 114;
            i3 = 69;
        }
        this.p = Color.rgb(i3, i, i2);
        this.q = z() ? -837410513 : -555027976;
        Window window = getWindow();
        if (z()) {
            i4 = 15;
            i5 = 21;
            i6 = 10;
        } else {
            i4 = 228;
            i5 = 239;
            i6 = 217;
        }
        window.setStatusBarColor(Color.rgb(i6, i4, i5));
        getWindow().setNavigationBarColor(getWindow().getStatusBarColor());
        d();
        this.r = null;
        this.z.clear();
        Set set3 = sf.a;
        String str6 = E().a;
        sf.c = i3.r0(sf.a, str6) ? 3 : i3.r0(sf.b, str6) ? 1 : 2;
        this.A.clear();
        this.K.clear();
        this.L.clear();
        this.M.clear();
        this.N.clear();
        this.B.clear();
        this.C.clear();
        this.G.clear();
        this.H.clear();
        this.Q.clear();
        this.J.clear();
        this.O = false;
        this.P.clear();
        this.V.clear();
        this.W.clear();
        this.v0.clear();
        this.X = null;
        this.d0 = null;
        this.e0 = null;
        this.f0 = null;
        ?? frameLayout = new FrameLayout(this);
        this.c0 = frameLayout;
        kf kfVar = new kf(this, B().getInt("background", 0), z(), true);
        frameLayout.addView(kfVar, new FrameLayout.LayoutParams(-1, -1));
        boolean z3 = x5.a;
        x5.a = B().getInt(this.y0, 0) == 1;
        if (x5.a) {
            Point point = new Point();
            getWindowManager().getDefaultDisplay().getRealSize(point);
            Integer numValueOf = Integer.valueOf(B().getInt("background", 0));
            Boolean boolValueOf = Boolean.valueOf(z());
            ca caVar2 = ea.a;
            File fileA = ea.a(this, z());
            boolean zExists = fileA.exists();
            long jLastModified = fileA.lastModified();
            StringBuilder sb = new StringBuilder();
            sb.append(zExists);
            sb.append(jLastModified);
            String strY0 = i3.y0(j3.i0(numValueOf, boolValueOf, sb.toString()), ":", null, 62);
            int i10 = point.x;
            int i11 = point.y;
            x5.e = new WeakReference(kfVar);
            if (x5.a && i10 > 0 && i11 > 0) {
                String str7 = strY0 + ":" + i10 + "x" + i11;
                if (!str7.equals(x5.d) || x5.b == null) {
                    int i12 = i10 / 4;
                    if (i12 < 1) {
                        i12 = 1;
                    }
                    int i13 = i11 / 4;
                    if (i13 < 1) {
                        i13 = 1;
                    }
                    Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i12, i13, Bitmap.Config.ARGB_8888);
                    bitmapCreateBitmap.getClass();
                    Canvas canvas = new Canvas(bitmapCreateBitmap);
                    canvas.scale(i12 / i10, i13 / i11);
                    kf kfVar2 = new kf(this, B().getInt("background", 0), z(), true);
                    kfVar2.measure(View.MeasureSpec.makeMeasureSpec(point.x, 1073741824), View.MeasureSpec.makeMeasureSpec(point.y, 1073741824));
                    kfVar2.layout(0, 0, point.x, point.y);
                    kfVar2.draw(canvas);
                    int width = bitmapCreateBitmap.getWidth();
                    int height = bitmapCreateBitmap.getHeight();
                    int i14 = width * height;
                    int[] iArr = new int[i14];
                    bitmapCreateBitmap.getPixels(iArr, 0, width, 0, 0, width, height);
                    int[] iArr2 = new int[i14];
                    for (int i15 = 0; i15 < 3; i15++) {
                        x5.a(iArr, iArr2, width, height, true);
                        x5.a(iArr2, iArr, width, height, false);
                    }
                    bitmapCreateBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
                    x5.b = bitmapCreateBitmap;
                    Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                    x5.c = new BitmapShader(bitmapCreateBitmap, tileMode, tileMode);
                    x5.d = str7;
                }
            }
        }
        frameLayout.addView(this.c ? k8.u0(this) : p(), new FrameLayout.LayoutParams(-1, -1));
        if (r1 == 0) {
            FrameLayout frameLayout2 = new FrameLayout(this);
            frameLayout2.setVisibility(8);
            for (b bVar : i3.s0(this.w, this.x)) {
                ViewParent parent = bVar.getParent();
                ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
                if (viewGroup != null) {
                    viewGroup.removeView(bVar);
                }
                frameLayout2.addView(bVar, new FrameLayout.LayoutParams(-1, -1));
            }
            z = true;
            frameLayout.addView(frameLayout2, new FrameLayout.LayoutParams(1, 1));
        } else {
            z = true;
        }
        if (this.h) {
            FrameLayout frameLayout3 = new FrameLayout(this);
            frameLayout3.setBackgroundColor(-1156573152);
            frameLayout3.setClickable(z);
            frameLayout3.setFocusable(z);
            TextView textViewU0 = u0(si.a("please_wait", new Object[0]), 22.0f, -1);
            textViewU0.setGravity(17);
            frameLayout3.addView(textViewU0, new FrameLayout.LayoutParams(-1, -1));
            frameLayout.addView(frameLayout3, new FrameLayout.LayoutParams(-1, -1));
        }
        e();
        z9.a(frameLayout);
        if (r1 != 0) {
            r1.removeAllViews();
            r1.addView(frameLayout, new FrameLayout.LayoutParams(-1, -1));
        } else {
            setContentView(frameLayout);
        }
        if (this.q0 != null && r1 == 0) {
            d0();
            o0();
        }
        if (!this.c && !this.f) {
            ArrayList arrayList5 = this.w;
            ArrayList arrayList6 = new ArrayList();
            int size4 = arrayList5.size();
            int i16 = 0;
            while (i16 < size4) {
                Object obj9 = arrayList5.get(i16);
                i16++;
                if (((b) obj9).isShown()) {
                    arrayList6.add(obj9);
                }
            }
            int size5 = arrayList6.size();
            int i17 = 0;
            while (i17 < size5) {
                Object obj10 = arrayList6.get(i17);
                i17++;
                ((b) obj10).j();
            }
        }
        gc gcVarY = y();
        hd hdVar = gcVarY.t;
        String str8 = hdVar.g;
        if (str8 != null && !str8.equals(gcVarY.B)) {
            hdVar.c();
        }
        if (gcVarY.l) {
            gc.b(gcVarY);
        }
        if (this.X == null || this.h) {
            I().g();
        } else {
            I().f();
        }
        if (this.X != null && !I().b() && !B().getBoolean("weather_asked", false)) {
            B().edit().putBoolean("weather_asked", true).apply();
            requestPermissions(vj.k, 47);
        }
        nj njVarH = H();
        ArrayList arrayList7 = this.z;
        ArrayList arrayList8 = new ArrayList(k3.n0(arrayList7));
        int size6 = arrayList7.size();
        int i18 = 0;
        while (i18 < size6) {
            Object obj11 = arrayList7.get(i18);
            i18++;
            arrayList8.add(((aj) obj11).getKind());
        }
        ArrayList arrayList9 = this.B;
        ArrayList arrayList10 = new ArrayList(k3.n0(arrayList9));
        int size7 = arrayList9.size();
        int i19 = 0;
        while (i19 < size7) {
            Object obj12 = arrayList9.get(i19);
            i19++;
            arrayList10.add((String) ((yc) obj12).a);
        }
        ArrayList arrayListD0 = i3.D0(arrayList8, arrayList10);
        j8 j8VarF0 = pi.f0(0, V());
        ArrayList arrayList11 = new ArrayList(k3.n0(j8VarF0));
        Iterator it2 = j8VarF0.iterator();
        while (true) {
            i8 i8Var2 = (i8) it2;
            if (!i8Var2.c) {
                break;
            } else {
                arrayList11.add(U(i8Var2.nextInt()));
            }
        }
        ArrayList arrayListD1 = i3.D0(arrayListD0, arrayList11);
        ArrayList arrayList12 = new ArrayList();
        int size8 = arrayListD1.size();
        int i20 = 0;
        while (i20 < size8) {
            Object obj13 = arrayListD1.get(i20);
            i20++;
            Set set4 = rf.d;
            if (pi.B().containsKey((String) obj13)) {
                arrayList12.add(obj13);
            }
        }
        Set setL0 = i3.L0(arrayList12);
        njVarH.getClass();
        njVarH.s = setL0;
        if (!this.h) {
            if (this.z.isEmpty() && this.Q.isEmpty() && this.G.isEmpty() && this.J.isEmpty() && !this.O && this.P.isEmpty() && this.L.isEmpty() && this.v0.isEmpty() && this.K.isEmpty()) {
                ArrayList arrayList13 = this.B;
                if (arrayList13 == null || !arrayList13.isEmpty()) {
                    int size9 = arrayList13.size();
                    int i21 = 0;
                    while (i21 < size9) {
                        Object obj14 = arrayList13.get(i21);
                        i21++;
                        if (G().contains(((yc) obj14).a)) {
                        }
                    }
                }
                Iterable iterableF0 = pi.f0(0, V());
                if (!(iterableF0 instanceof Collection) || !((Collection) iterableF0).isEmpty()) {
                    Iterator it3 = iterableF0.iterator();
                    do {
                        i8Var = (i8) it3;
                        if (i8Var.c) {
                        }
                    } while (!U(i8Var.nextInt()).equals("cruise"));
                }
            }
            H().p();
            return;
        }
        H().q();
    }

    public final void f0() {
        defpackage.c.e("later", new Object[0], new AlertDialog.Builder(this).setTitle(si.a("music_information", new Object[0])).setMessage(si.a("enable_notification_access_for_ReferencePanel_music_information", new Object[0])).setPositiveButton(si.a("open_settings", new Object[0]), new z1(this, 2)), null, this);
    }

    public final void g() {
        FrameLayout frameLayout = this.c0;
        if (frameLayout != null && this.d && this.d0 == null && this.f0 == null) {
            z();
            qf qfVar = new qf(this, B().getInt("saver_dim", 35), new b2(this, 27), new b2(this, 28), false);
            frameLayout.addView(qfVar, new FrameLayout.LayoutParams(-1, -1));
            this.d0 = qfVar;
            boolean z = q.a;
            q.b = true;
            d();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:109:0x022b  */
    /* JADX WARN: Code duplicated, block: B:112:0x0247  */
    /* JADX WARN: Code duplicated, block: B:115:0x0270  */
    /* JADX WARN: Code duplicated, block: B:118:0x027d  */
    /* JADX WARN: Code duplicated, block: B:120:0x0291  */
    /* JADX WARN: Code duplicated, block: B:121:0x0294  */
    /* JADX WARN: Code duplicated, block: B:142:0x0317  */
    /* JADX WARN: Code duplicated, block: B:97:0x01fb  */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x02a5, code lost:

        defpackage.k8.m(r48);
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0174, code lost:

        if (r0.equals("music_title") == false) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x018d, code lost:

        if (r0.equals("temp_passenger") == false) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0195, code lost:

        if (r0.equals("weather") == false) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x019c, code lost:

        if (r0.equals("music") == false) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x01a5, code lost:

        if (r0.equals("clock") == false) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x01a8, code lost:

        z0();
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x01b3, code lost:

        if (r0.equals("anton") == false) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x01ba, code lost:

        if (r0.equals("temp") == false) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01c3, code lost:

        if (r0.equals("maps") == false) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x01cc, code lost:

        if (r0.equals("nav") == false) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x01cf, code lost:

        Z(0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x01da, code lost:

        if (r0.equals("degree_passenger") == false) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x01e3, code lost:

        if (r0.equals("music2") == false) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x01e6, code lost:

        Y();
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01f1, code lost:

        if (r0.equals("degree_driver") == false) goto L94;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void g0(int r49, java.lang.String r50) {
        /*
            Method dump skipped, instruction units count: 2414
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.reference.panel.MainActivity.g0(int, java.lang.String):void");
    }

    public final void h() {
        try {
            startActivity(new Intent(this, (Class<?>) MainActivity.class).addFlags(268566528));
        } catch (Throwable unused) {
        }
    }

    public final void h0() {
        Handler handler = this.h0;
        sa saVar = this.i0;
        handler.removeCallbacks(saVar);
        if (this.d && B().getBoolean("saver_on", false) && this.d0 == null) {
            handler.postDelayed(saVar, ((long) pi.s(B().getInt("saver_minutes", 5), 1, 60)) * 60000);
        }
    }

    public final void i0(FrameLayout frameLayout) {
        this.r = frameLayout;
    }

    public final void j() {
        sc scVar = this.y;
        scVar.a++;
        scVar.b = false;
        scVar.c = false;
        scVar.d = false;
        ArrayList arrayList = this.w;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            b bVar = (b) obj;
            if (bVar.u) {
                bVar.u = false;
                bVar.o = false;
            }
            bVar.j();
        }
    }

    public final void j0(Integer num) {
        if (num.equals(this.e)) {
            return;
        }
        this.e = num;
        B().edit().putInt("roof_slider_last", num.intValue()).apply();
    }

    public final void k() {
        ab abVar = this.t0;
        if (abVar == null) {
            return;
        }
        this.t0 = null;
        t0();
        try {
            WindowManager windowManager = (WindowManager) getSystemService(WindowManager.class);
            if (windowManager != null) {
                windowManager.removeView(abVar);
            }
        } catch (Throwable unused) {
        }
    }

    public final void k0(ScrollView scrollView) {
        this.s = scrollView;
    }

    public final LinearLayout l() {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        return linearLayout;
    }

    public final void l0() {
        this.u = "desktop";
    }

    public final ComponentName m(int i) {
        String string = B().getString(f(i), null);
        if (string != null) {
            return ComponentName.unflattenFromString(string);
        }
        return null;
    }

    public final GradientDrawable m0(int i, int i2) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(i);
        gradientDrawable.setCornerRadius(r(i2));
        gradientDrawable.setStroke(r(1), z() ? 810509432 : 1081840810);
        return gradientDrawable;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:101:0x01c5  */
    /* JADX WARN: Code duplicated, block: B:104:0x01cc  */
    /* JADX WARN: Code duplicated, block: B:107:0x01d5  */
    /* JADX WARN: Code duplicated, block: B:110:0x01de  */
    /* JADX WARN: Code duplicated, block: B:113:0x01e7  */
    /* JADX WARN: Code duplicated, block: B:116:0x01f0  */
    /* JADX WARN: Code duplicated, block: B:117:0x01f4  */
    /* JADX WARN: Code duplicated, block: B:120:0x01fd  */
    /* JADX WARN: Code duplicated, block: B:121:0x0201  */
    /* JADX WARN: Code duplicated, block: B:123:0x0207  */
    /* JADX WARN: Code duplicated, block: B:124:0x020a  */
    /* JADX WARN: Code duplicated, block: B:126:0x0214  */
    /* JADX WARN: Code duplicated, block: B:47:0x0111  */
    /* JADX WARN: Code duplicated, block: B:53:0x012a  */
    /* JADX WARN: Code duplicated, block: B:55:0x0134  */
    /* JADX WARN: Code duplicated, block: B:56:0x0136  */
    /* JADX WARN: Code duplicated, block: B:59:0x013d  */
    /* JADX WARN: Code duplicated, block: B:62:0x0149  */
    /* JADX WARN: Code duplicated, block: B:68:0x015c  */
    /* JADX WARN: Code duplicated, block: B:71:0x0166  */
    /* JADX WARN: Code duplicated, block: B:74:0x0170  */
    /* JADX WARN: Code duplicated, block: B:77:0x017a  */
    /* JADX WARN: Code duplicated, block: B:80:0x0184  */
    /* JADX WARN: Code duplicated, block: B:83:0x018e  */
    /* JADX WARN: Code duplicated, block: B:86:0x0198  */
    /* JADX WARN: Code duplicated, block: B:89:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:92:0x01aa  */
    /* JADX WARN: Code duplicated, block: B:95:0x01b3  */
    /* JADX WARN: Code duplicated, block: B:98:0x01bc  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final View n(String str, boolean z, y5 y5Var) {
        View viewA;
        String str2;
        View viewP;
        float f;
        int i = 9;
        int i2 = 2;
        if (str.equals("gap")) {
            viewA = new w8(this);
            viewA.setLongClickable(true);
            viewA.setContentDescription(si.a("dock_gap", new Object[0]));
        } else if (str.startsWith("split:")) {
            String strC = th.C(str, "split:");
            List list = eh.a;
            SharedPreferences sharedPreferencesB = B();
            sharedPreferencesB.getClass();
            viewA = eh.c(sharedPreferencesB).contains(strC) ? new m4(this, k8.C0(strC, this, false), 1.7f) : a("empty", z);
        } else if (str.equals("temp")) {
            viewA = new s9(this, z(), new ua(this, 7), new ua(this, 8), new v1(this, 29), new ua(this, i), new ua(this, 10));
            this.J.add(viewA);
        } else if (str.equals("temp2")) {
            viewA = k8.q((1 & 2) == 0 ? 4 : 1, this, (1 & 1) != 0);
        } else if (str.equals("temp_passenger")) {
            viewA = k8.q(4, this, false);
        } else if (str.startsWith("widget:")) {
            String strC2 = th.C(str, "widget:");
            switch (strC2.hashCode()) {
                case -1302320901:
                    if (!strC2.equals("degree_driver")) {
                        if (strC2.equals("music")) {
                            str2 = "music2";
                        } else {
                            str2 = strC2;
                        }
                        viewP = P(str2);
                        if (viewP == null) {
                            viewP = a("empty", z);
                        }
                        if (strC2.equals("car") && (viewP instanceof FrameLayout)) {
                            b0((FrameLayout) viewP);
                        }
                        switch (strC2) {
                            case "music2":
                                f = 3.2f;
                                break;
                            case "volume":
                                f = 0.7f;
                                break;
                            case "fuel_compact":
                            case "battery_compact":
                            case "battery":
                            case "bt":
                                f = 1.8f;
                                break;
                            case "car":
                                f = 3.2f;
                                break;
                            case "fuel":
                            case "wifi":
                                f = 1.8f;
                                break;
                            case "clock":
                            case "music":
                            case "radio":
                                f = 3.2f;
                                break;
                            case "stats":
                            case "automation":
                                f = 1.8f;
                                break;
                            case "brightness":
                                f = 0.7f;
                                break;
                            case "weather":
                                f = 3.2f;
                                break;
                            case "roof_slider":
                                f = 0.7f;
                                break;
                            case "music_title":
                                f = 3.2f;
                                break;
                            default:
                                f = 1.0f;
                                break;
                        }
                        viewA = new m4(this, viewP, f);
                    } else {
                        viewA = q(strC2, false);
                    }
                    break;
                case -580800793:
                    if (!strC2.equals("degree_passenger")) {
                        if (strC2.equals("music")) {
                            str2 = "music2";
                        } else {
                            str2 = strC2;
                        }
                        viewP = P(str2);
                        if (viewP == null) {
                            viewP = a("empty", z);
                        }
                        if (strC2.equals("car")) {
                            b0((FrameLayout) viewP);
                        }
                        switch (strC2) {
                            case -1062807891:
                                if (!strC2.equals("music2")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case -810883302:
                                if (!strC2.equals("volume")) {
                                    f = 0.7f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case -799216230:
                                if (!strC2.equals("fuel_compact")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case -345754895:
                                if (!strC2.equals("battery_compact")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case -331239923:
                                if (!strC2.equals("battery")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 3154:
                                if (!strC2.equals("bt")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 98260:
                                if (!strC2.equals("car")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 3154358:
                                if (!strC2.equals("fuel")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 3649301:
                                if (!strC2.equals("wifi")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 94755854:
                                if (!strC2.equals("clock")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 104263205:
                                if (!strC2.equals("music")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 108270587:
                                if (!strC2.equals("radio")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 109757599:
                                if (!strC2.equals("stats")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 344200471:
                                if (!strC2.equals("automation")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 648162385:
                                if (!strC2.equals("brightness")) {
                                    f = 0.7f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 1223440372:
                                if (!strC2.equals("weather")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 1390681772:
                                if (!strC2.equals("roof_slider")) {
                                    f = 0.7f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 1589871550:
                                if (!strC2.equals("music_title")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            default:
                                f = 1.0f;
                                break;
                        }
                        viewA = new m4(this, viewP, f);
                    } else {
                        viewA = q(strC2, false);
                    }
                    break;
                case 108835:
                    if (!strC2.equals("nav")) {
                        if (strC2.equals("music")) {
                            str2 = "music2";
                        } else {
                            str2 = strC2;
                        }
                        viewP = P(str2);
                        if (viewP == null) {
                            viewP = a("empty", z);
                        }
                        if (strC2.equals("car")) {
                            b0((FrameLayout) viewP);
                        }
                        switch (strC2) {
                            case -1062807891:
                                if (!strC2.equals("music2")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case -810883302:
                                if (!strC2.equals("volume")) {
                                    f = 0.7f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case -799216230:
                                if (!strC2.equals("fuel_compact")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case -345754895:
                                if (!strC2.equals("battery_compact")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case -331239923:
                                if (!strC2.equals("battery")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 3154:
                                if (!strC2.equals("bt")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 98260:
                                if (!strC2.equals("car")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 3154358:
                                if (!strC2.equals("fuel")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 3649301:
                                if (!strC2.equals("wifi")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 94755854:
                                if (!strC2.equals("clock")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 104263205:
                                if (!strC2.equals("music")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 108270587:
                                if (!strC2.equals("radio")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 109757599:
                                if (!strC2.equals("stats")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 344200471:
                                if (!strC2.equals("automation")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 648162385:
                                if (!strC2.equals("brightness")) {
                                    f = 0.7f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 1223440372:
                                if (!strC2.equals("weather")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 1390681772:
                                if (!strC2.equals("roof_slider")) {
                                    f = 0.7f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 1589871550:
                                if (!strC2.equals("music_title")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            default:
                                f = 1.0f;
                                break;
                        }
                        viewA = new m4(this, viewP, f);
                    } else {
                        viewA = a("nav_window", z);
                    }
                    break;
                case 3344023:
                    if (!strC2.equals("maps")) {
                        if (strC2.equals("music")) {
                            str2 = "music2";
                        } else {
                            str2 = strC2;
                        }
                        viewP = P(str2);
                        if (viewP == null) {
                            viewP = a("empty", z);
                        }
                        if (strC2.equals("car")) {
                            b0((FrameLayout) viewP);
                        }
                        switch (strC2) {
                            case -1062807891:
                                if (!strC2.equals("music2")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case -810883302:
                                if (!strC2.equals("volume")) {
                                    f = 0.7f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case -799216230:
                                if (!strC2.equals("fuel_compact")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case -345754895:
                                if (!strC2.equals("battery_compact")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case -331239923:
                                if (!strC2.equals("battery")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 3154:
                                if (!strC2.equals("bt")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 98260:
                                if (!strC2.equals("car")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 3154358:
                                if (!strC2.equals("fuel")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 3649301:
                                if (!strC2.equals("wifi")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 94755854:
                                if (!strC2.equals("clock")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 104263205:
                                if (!strC2.equals("music")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 108270587:
                                if (!strC2.equals("radio")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 109757599:
                                if (!strC2.equals("stats")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 344200471:
                                if (!strC2.equals("automation")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 648162385:
                                if (!strC2.equals("brightness")) {
                                    f = 0.7f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 1223440372:
                                if (!strC2.equals("weather")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 1390681772:
                                if (!strC2.equals("roof_slider")) {
                                    f = 0.7f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 1589871550:
                                if (!strC2.equals("music_title")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            default:
                                f = 1.0f;
                                break;
                        }
                        viewA = new m4(this, viewP, f);
                    } else {
                        viewA = a("nav_window", z);
                    }
                    break;
                case 3556308:
                    if (!strC2.equals("temp")) {
                        if (strC2.equals("music")) {
                            str2 = "music2";
                        } else {
                            str2 = strC2;
                        }
                        viewP = P(str2);
                        if (viewP == null) {
                            viewP = a("empty", z);
                        }
                        if (strC2.equals("car")) {
                            b0((FrameLayout) viewP);
                        }
                        switch (strC2) {
                            case -1062807891:
                                if (!strC2.equals("music2")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case -810883302:
                                if (!strC2.equals("volume")) {
                                    f = 0.7f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case -799216230:
                                if (!strC2.equals("fuel_compact")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case -345754895:
                                if (!strC2.equals("battery_compact")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case -331239923:
                                if (!strC2.equals("battery")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 3154:
                                if (!strC2.equals("bt")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 98260:
                                if (!strC2.equals("car")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 3154358:
                                if (!strC2.equals("fuel")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 3649301:
                                if (!strC2.equals("wifi")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 94755854:
                                if (!strC2.equals("clock")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 104263205:
                                if (!strC2.equals("music")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 108270587:
                                if (!strC2.equals("radio")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 109757599:
                                if (!strC2.equals("stats")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 344200471:
                                if (!strC2.equals("automation")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 648162385:
                                if (!strC2.equals("brightness")) {
                                    f = 0.7f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 1223440372:
                                if (!strC2.equals("weather")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 1390681772:
                                if (!strC2.equals("roof_slider")) {
                                    f = 0.7f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 1589871550:
                                if (!strC2.equals("music_title")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            default:
                                f = 1.0f;
                                break;
                        }
                        viewA = new m4(this, viewP, f);
                    } else {
                        viewA = k8.q((1 & 2) == 0 ? 4 : 1, this, (1 & 1) != 0);
                    }
                    break;
                case 92973574:
                    if (!strC2.equals("anton")) {
                        if (strC2.equals("music")) {
                            str2 = "music2";
                        } else {
                            str2 = strC2;
                        }
                        viewP = P(str2);
                        if (viewP == null) {
                            viewP = a("empty", z);
                        }
                        if (strC2.equals("car")) {
                            b0((FrameLayout) viewP);
                        }
                        switch (strC2) {
                            case -1062807891:
                                if (!strC2.equals("music2")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case -810883302:
                                if (!strC2.equals("volume")) {
                                    f = 0.7f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case -799216230:
                                if (!strC2.equals("fuel_compact")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case -345754895:
                                if (!strC2.equals("battery_compact")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case -331239923:
                                if (!strC2.equals("battery")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 3154:
                                if (!strC2.equals("bt")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 98260:
                                if (!strC2.equals("car")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 3154358:
                                if (!strC2.equals("fuel")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 3649301:
                                if (!strC2.equals("wifi")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 94755854:
                                if (!strC2.equals("clock")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 104263205:
                                if (!strC2.equals("music")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 108270587:
                                if (!strC2.equals("radio")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 109757599:
                                if (!strC2.equals("stats")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 344200471:
                                if (!strC2.equals("automation")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 648162385:
                                if (!strC2.equals("brightness")) {
                                    f = 0.7f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 1223440372:
                                if (!strC2.equals("weather")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 1390681772:
                                if (!strC2.equals("roof_slider")) {
                                    f = 0.7f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 1589871550:
                                if (!strC2.equals("music_title")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            default:
                                f = 1.0f;
                                break;
                        }
                        viewA = new m4(this, viewP, f);
                    } else {
                        viewA = b(false);
                    }
                    break;
                case 1272109551:
                    if (!strC2.equals("temp_passenger")) {
                        if (strC2.equals("music")) {
                            str2 = "music2";
                        } else {
                            str2 = strC2;
                        }
                        viewP = P(str2);
                        if (viewP == null) {
                            viewP = a("empty", z);
                        }
                        if (strC2.equals("car")) {
                            b0((FrameLayout) viewP);
                        }
                        switch (strC2) {
                            case -1062807891:
                                if (!strC2.equals("music2")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case -810883302:
                                if (!strC2.equals("volume")) {
                                    f = 0.7f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case -799216230:
                                if (!strC2.equals("fuel_compact")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case -345754895:
                                if (!strC2.equals("battery_compact")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case -331239923:
                                if (!strC2.equals("battery")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 3154:
                                if (!strC2.equals("bt")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 98260:
                                if (!strC2.equals("car")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 3154358:
                                if (!strC2.equals("fuel")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 3649301:
                                if (!strC2.equals("wifi")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 94755854:
                                if (!strC2.equals("clock")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 104263205:
                                if (!strC2.equals("music")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 108270587:
                                if (!strC2.equals("radio")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 109757599:
                                if (!strC2.equals("stats")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 344200471:
                                if (!strC2.equals("automation")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 648162385:
                                if (!strC2.equals("brightness")) {
                                    f = 0.7f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 1223440372:
                                if (!strC2.equals("weather")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 1390681772:
                                if (!strC2.equals("roof_slider")) {
                                    f = 0.7f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 1589871550:
                                if (!strC2.equals("music_title")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            default:
                                f = 1.0f;
                                break;
                        }
                        viewA = new m4(this, viewP, f);
                    } else {
                        viewA = k8.q(4, this, false);
                    }
                    break;
                case 1388570574:
                    if (!strC2.equals("app_window")) {
                        if (strC2.equals("music")) {
                            str2 = "music2";
                        } else {
                            str2 = strC2;
                        }
                        viewP = P(str2);
                        if (viewP == null) {
                            viewP = a("empty", z);
                        }
                        if (strC2.equals("car")) {
                            b0((FrameLayout) viewP);
                        }
                        switch (strC2) {
                            case -1062807891:
                                if (!strC2.equals("music2")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case -810883302:
                                if (!strC2.equals("volume")) {
                                    f = 0.7f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case -799216230:
                                if (!strC2.equals("fuel_compact")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case -345754895:
                                if (!strC2.equals("battery_compact")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case -331239923:
                                if (!strC2.equals("battery")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 3154:
                                if (!strC2.equals("bt")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 98260:
                                if (!strC2.equals("car")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 3154358:
                                if (!strC2.equals("fuel")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 3649301:
                                if (!strC2.equals("wifi")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 94755854:
                                if (!strC2.equals("clock")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 104263205:
                                if (!strC2.equals("music")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 108270587:
                                if (!strC2.equals("radio")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 109757599:
                                if (!strC2.equals("stats")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 344200471:
                                if (!strC2.equals("automation")) {
                                    f = 1.8f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 648162385:
                                if (!strC2.equals("brightness")) {
                                    f = 0.7f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 1223440372:
                                if (!strC2.equals("weather")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 1390681772:
                                if (!strC2.equals("roof_slider")) {
                                    f = 0.7f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            case 1589871550:
                                if (!strC2.equals("music_title")) {
                                    f = 3.2f;
                                } else {
                                    f = 1.0f;
                                }
                                break;
                            default:
                                f = 1.0f;
                                break;
                        }
                        viewA = new m4(this, viewP, f);
                    } else {
                        viewA = a(this.C0, z);
                    }
                    break;
                default:
                    if (strC2.equals("music")) {
                        str2 = "music2";
                    } else {
                        str2 = strC2;
                    }
                    viewP = P(str2);
                    if (viewP == null) {
                        viewP = a("empty", z);
                    }
                    if (strC2.equals("car")) {
                        b0((FrameLayout) viewP);
                    }
                    switch (strC2) {
                        case -1062807891:
                            if (!strC2.equals("music2")) {
                                f = 3.2f;
                            } else {
                                f = 1.0f;
                            }
                            break;
                        case -810883302:
                            if (!strC2.equals("volume")) {
                                f = 0.7f;
                            } else {
                                f = 1.0f;
                            }
                            break;
                        case -799216230:
                            if (!strC2.equals("fuel_compact")) {
                                f = 1.8f;
                            } else {
                                f = 1.0f;
                            }
                            break;
                        case -345754895:
                            if (!strC2.equals("battery_compact")) {
                                f = 1.8f;
                            } else {
                                f = 1.0f;
                            }
                            break;
                        case -331239923:
                            if (!strC2.equals("battery")) {
                                f = 1.8f;
                            } else {
                                f = 1.0f;
                            }
                            break;
                        case 3154:
                            if (!strC2.equals("bt")) {
                                f = 1.8f;
                            } else {
                                f = 1.0f;
                            }
                            break;
                        case 98260:
                            if (!strC2.equals("car")) {
                                f = 3.2f;
                            } else {
                                f = 1.0f;
                            }
                            break;
                        case 3154358:
                            if (!strC2.equals("fuel")) {
                                f = 1.8f;
                            } else {
                                f = 1.0f;
                            }
                            break;
                        case 3649301:
                            if (!strC2.equals("wifi")) {
                                f = 1.8f;
                            } else {
                                f = 1.0f;
                            }
                            break;
                        case 94755854:
                            if (!strC2.equals("clock")) {
                                f = 3.2f;
                            } else {
                                f = 1.0f;
                            }
                            break;
                        case 104263205:
                            if (!strC2.equals("music")) {
                                f = 3.2f;
                            } else {
                                f = 1.0f;
                            }
                            break;
                        case 108270587:
                            if (!strC2.equals("radio")) {
                                f = 3.2f;
                            } else {
                                f = 1.0f;
                            }
                            break;
                        case 109757599:
                            if (!strC2.equals("stats")) {
                                f = 1.8f;
                            } else {
                                f = 1.0f;
                            }
                            break;
                        case 344200471:
                            if (!strC2.equals("automation")) {
                                f = 1.8f;
                            } else {
                                f = 1.0f;
                            }
                            break;
                        case 648162385:
                            if (!strC2.equals("brightness")) {
                                f = 0.7f;
                            } else {
                                f = 1.0f;
                            }
                            break;
                        case 1223440372:
                            if (!strC2.equals("weather")) {
                                f = 3.2f;
                            } else {
                                f = 1.0f;
                            }
                            break;
                        case 1390681772:
                            if (!strC2.equals("roof_slider")) {
                                f = 0.7f;
                            } else {
                                f = 1.0f;
                            }
                            break;
                        case 1589871550:
                            if (!strC2.equals("music_title")) {
                                f = 3.2f;
                            } else {
                                f = 1.0f;
                            }
                            break;
                        default:
                            f = 1.0f;
                            break;
                    }
                    viewA = new m4(this, viewP, f);
                    break;
            }
        } else {
            viewA = a(str, z);
        }
        if (str.equals("empty")) {
            viewA.setOnClickListener(new m2(y5Var, i));
        }
        viewA.setOnLongClickListener(new o7(y5Var, i2));
        int i3 = zj.a;
        SharedPreferences sharedPreferencesB2 = B();
        sharedPreferencesB2.getClass();
        zj.a(viewA, zj.c(sharedPreferencesB2, "dock_button_transparency_".concat(str)));
        return viewA;
    }

    public final void n0(FrameLayout frameLayout, Bitmap bitmap) {
        frameLayout.removeAllViews();
        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(bitmap);
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
        List list = si.a;
        imageView.setContentDescription(si.a("image_0", y0()));
        imageView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: oa
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                int i9 = MainActivity.E0;
                this.a.w0();
            }
        });
        frameLayout.addView(imageView, new FrameLayout.LayoutParams(-1, -1));
        w0();
    }

    public final File o(String str) {
        str.getClass();
        return new File(getFilesDir(), "vehicle_" + str + ".png");
    }

    public final boolean o0() {
        WindowManager windowManager;
        Object ffVar;
        if (this.q0 != null) {
            return true;
        }
        Rect rect = this.s0;
        if (rect == null || isFinishing() || isDestroyed() || (windowManager = (WindowManager) getSystemService(WindowManager.class)) == null) {
            return false;
        }
        v8 v8VarK = K(true);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(rect.width(), rect.height(), 2038, 776, -3);
        layoutParams.gravity = 8388659;
        layoutParams.x = rect.left;
        layoutParams.y = rect.top;
        layoutParams.setTitle("ReferencePanel dock");
        v8VarK.setAlpha(0.0f);
        try {
            windowManager.addView(v8VarK, layoutParams);
            ffVar = ti.a;
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        if (ffVar instanceof ff) {
            return false;
        }
        this.q0 = v8VarK;
        t0();
        v8VarK.getViewTreeObserver().addOnGlobalLayoutListener(new bb(v8VarK, rect, layoutParams, windowManager));
        z9.a(v8VarK);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.app.Activity
    public final void onActivityResult(int i, int i2, Intent intent) {
        Uri data;
        String str;
        Uri data2;
        Uri data3;
        super.onActivityResult(i, i2, intent);
        ExecutorService executorService = this.b;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        if (50 <= i && i < 52) {
            if (i2 != -1 || intent == null || (data3 = intent.getData()) == null) {
                return;
            }
            boolean z = i == 50;
            this.h = true;
            e0();
            executorService.execute(new ag(this, z, data3, objArr2 == true ? 1 : 0));
            return;
        }
        if (i == 54) {
            if (i2 != -1 || intent == null || (data2 = intent.getData()) == null) {
                return;
            }
            this.h = true;
            e0();
            executorService.execute(new o(this, data2, 10));
            return;
        }
        if (i == 56) {
            Uri data4 = intent != null ? intent.getData() : null;
            if (i2 != -1) {
                data4 = null;
            }
            WeakHashMap weakHashMap = f8.a;
            String str2 = this.F;
            if (str2 == null) {
                return;
            }
            this.F = null;
            if (data4 == null) {
                return;
            }
            k8.w(this, data4, str2, false, v7.c(this, str2), new w7(this, str2, objArr == true ? 1 : 0), 4);
            return;
        }
        if (((41 > i || i >= 46) && i != 49) || i2 != -1 || intent == null || (data = intent.getData()) == null) {
            return;
        }
        if (44 <= i && i < 46) {
            k8.K0(this, data, i == 45);
            return;
        }
        if (i == 49) {
            k8.w(this, data, "screensaver", true, null, null, 24);
            return;
        }
        if (i != 41) {
            str = i != 42 ? "night" : "day";
        } else {
            str = E().a;
        }
        k8.w(this, data, str, i != 41, null, null, 24);
    }

    @Override // android.app.Activity
    public final void onBackPressed() {
        if (this.h || this.f0 != null) {
            return;
        }
        if (this.f) {
            this.f = false;
            e0();
        } else if (this.c) {
            k8.H(this);
        } else {
            super.onBackPressed();
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        configuration.getClass();
        super.onConfigurationChanged(configuration);
        if (D().equals("system")) {
            e0();
        }
    }

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        Object ffVar;
        String string;
        String string2;
        super.onCreate(bundle);
        boolean z = true;
        boolean z2 = true;
        if (!z9.a) {
            z9.d(this);
            z9.a = true;
        }
        ba.h.q(this);
        ba.l = true;
        k8.E0(this);
        B().edit().remove("car_root").apply();
        int i = 0;
        if (!B().getBoolean("independent_dock", false)) {
            SharedPreferences.Editor editorEdit = B().edit();
            for (int i2 = 0; i2 < 2; i2++) {
                String strF = f(i2 + 4);
                if (!B().contains(strF) && (string2 = B().getString(f(i2), null)) != null) {
                    editorEdit.putString(strF, string2);
                }
            }
            editorEdit.putBoolean("independent_dock", true);
            editorEdit.apply();
        }
        gc gcVar = new gc(this, new b2(this, 5), new v1(this, 21));
        gcVar.C = new v1(this, 25);
        gcVar.x = new wa(this, gcVar, i);
        k8.i = new wa(this, gcVar, z2 ? 1 : 0);
        this.v = gcVar;
        int i3 = AutomationService.c;
        k8.F0(this);
        r4 r4VarP = r4.C.p(this);
        if (r4VarP != null) {
            r4VarP.x = new ua(this, 11);
        }
        try {
            ffVar = hh.a(this);
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        Throwable thA = gf.a(ffVar);
        Object obj = ffVar;
        if (thA != null) {
            obj = "error: " + thA.getMessage();
        }
        Log.w("ReferencePanel.Stamp", "source-stamp verdict = ".concat((String) obj));
        if (B().getBoolean("suppress_climate_popup", true)) {
            k8.i0(this);
        }
        if (bundle == null || (string = bundle.getString("settings_section")) == null) {
            string = "desktop";
        }
        this.u = string;
        if (bundle != null) {
            z = bundle.getBoolean("settings");
        } else if (B().getBoolean("onboarded", false)) {
            z = false;
        }
        this.c = z;
        if (getIntent().getBooleanExtra("org.reference.panel.OPEN_HOME", false)) {
            this.c = false;
        }
    }

    @Override // android.app.Activity
    public final void onDestroy() {
        rg.b();
        d0();
        f8.f(this);
        k();
        ArrayList arrayList = this.w;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((b) obj).c();
        }
        this.w.clear();
        gc gcVarY = y();
        gcVarY.C();
        gcVarY.t.c();
        gcVarY.g.shutdownNow();
        nb nbVar = gcVarY.u;
        nbVar.d.post(new kb(nbVar, 1));
        nbVar.c.quitSafely();
        nj njVarH = H();
        njVarH.q();
        njVarH.d.shutdownNow();
        njVarH.e.shutdownNow();
        vj vjVarI = I();
        vjVarI.g();
        vjVarI.e.shutdownNow();
        this.n++;
        this.b.shutdown();
        super.onDestroy();
    }

    @Override // android.app.Activity
    public final void onNewIntent(Intent intent) {
        intent.getClass();
        super.onNewIntent(intent);
        setIntent(intent);
        if (intent.getBooleanExtra("org.reference.panel.OPEN_HOME", false) || intent.hasCategory("android.intent.category.HOME")) {
            j();
            this.c = false;
            this.f = false;
            e0();
        }
    }

    @Override // android.app.Activity
    public final void onPause() {
        getWindow().getDecorView().removeCallbacks(this.k0);
        boolean z = q.a;
        int i = 0;
        q.a = false;
        sc scVar = this.y;
        if (scVar.b && scVar.c) {
            scVar.d = true;
        }
        ArrayList arrayList = this.w;
        int size = arrayList.size();
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((b) obj).f();
        }
        super.onPause();
    }

    @Override // android.app.Activity
    public final void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        MainActivity mainActivity;
        strArr.getClass();
        iArr.getClass();
        super.onRequestPermissionsResult(i, strArr, iArr);
        int i2 = 0;
        if (i == 46) {
            boolean z = this.i;
            mainActivity = this;
            new h2(mainActivity, z, new pb(3, this, z), new kg(i2, this, z), null, null, 112).b();
        } else {
            mainActivity = this;
        }
        if (i == 52) {
            k8.O(mainActivity, mainActivity.j, false);
        }
        if (i == 55) {
            k8.K(mainActivity, false);
        }
        if (i == 47) {
            mainActivity.I().e(true);
        }
        if (i == 48 || i == 53) {
            mainActivity.e0();
        }
    }

    @Override // android.app.Activity
    public final void onResume() {
        ArrayList arrayList = this.w;
        super.onResume();
        boolean z = q.a;
        q.a = true;
        if (!q.b) {
            q.c.post(new k(3));
        }
        View decorView = getWindow().getDecorView();
        sa saVar = this.k0;
        decorView.removeCallbacks(saVar);
        getWindow().getDecorView().postDelayed(saVar, 1500L);
        h0();
        int i = 0;
        pi.l(this, false);
        sc scVar = this.y;
        if (scVar.b && scVar.c && scVar.d) {
            scVar.a++;
            scVar.b = false;
            scVar.c = false;
            scVar.d = false;
            int size = arrayList.size();
            int i2 = 0;
            while (i2 < size) {
                Object obj = arrayList.get(i2);
                i2++;
                b bVar = (b) obj;
                if (bVar.u) {
                    bVar.u = false;
                    bVar.o = false;
                }
            }
        }
        if (!this.c && !this.f && (arrayList == null || !arrayList.isEmpty())) {
            int size2 = arrayList.size();
            int i3 = 0;
            while (true) {
                if (i3 >= size2) {
                    e0();
                    break;
                }
                Object obj2 = arrayList.get(i3);
                i3++;
                b bVar2 = (b) obj2;
                if (bVar2.isShown() && bVar2.getRetainOnResume()) {
                    int size3 = arrayList.size();
                    while (i < size3) {
                        Object obj3 = arrayList.get(i);
                        i++;
                        ((b) obj3).j();
                    }
                    if (this.X != null) {
                        I().f();
                    }
                    H().p();
                    gc gcVarY = y();
                    hd hdVar = gcVarY.t;
                    String str = hdVar.g;
                    if (str != null && !str.equals(gcVarY.B)) {
                        hdVar.c();
                    }
                    if (!gcVarY.l) {
                        break;
                    }
                    gc.b(gcVarY);
                    break;
                }
            }
        } else {
            e0();
            break;
        }
        y().A();
        e();
        yc ycVar = this.r0;
        if (ycVar != null) {
            int iIntValue = ((Number) ycVar.a).intValue();
            String str2 = (String) ycVar.b;
            this.r0 = null;
            g0(iIntValue, str2);
        }
        y5 y5Var = this.u0;
        if (y5Var != null) {
            this.u0 = null;
            y5Var.a();
        }
    }

    @Override // android.app.Activity
    public final void onSaveInstanceState(Bundle bundle) {
        bundle.getClass();
        bundle.putString("settings_section", this.u);
        bundle.putBoolean("settings", this.c);
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Activity
    public final void onStart() {
        super.onStart();
        rg.b();
        d0();
        f8.f(this);
        t0();
        if (this.t0 != null) {
            k();
            e0();
        }
        this.d = true;
        ye yeVar = (ye) this.g0.a();
        if (!yeVar.f && yeVar.c != null && yeVar.a.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) {
            if (bf.c == null && !bf.d) {
                Set set = bf.a;
                bf.d = true;
                new Thread(new o(yeVar, yeVar.a.getApplicationContext())).start();
            }
            try {
                yeVar.c.requestLocationUpdates("gps", 5000L, 0.0f, yeVar.g, Looper.getMainLooper());
                yeVar.f = true;
            } catch (IllegalArgumentException | SecurityException unused) {
            }
        }
        LinkedHashSet linkedHashSet = pc.a;
        pc.a(this, true ^ this.c);
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0072  */
    @Override // android.app.Activity
    public final void onStop() {
        WindowManager windowManager;
        Object ffVar;
        boolean z = false;
        this.d = false;
        ye yeVar = (ye) this.g0.a();
        if (yeVar.f) {
            try {
                LocationManager locationManager = yeVar.c;
                if (locationManager != null) {
                    locationManager.removeUpdates(yeVar.g);
                }
            } catch (Throwable unused) {
            }
        }
        yeVar.f = false;
        this.h0.removeCallbacks(this.i0);
        s0();
        pc.a(this, false);
        ArrayList arrayList = this.w;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((b) obj).k();
        }
        Rect rectT = T();
        if (rectT == null) {
            rectT = this.s0;
        }
        this.s0 = rectT;
        WindowInsets rootWindowInsets = getWindow().getDecorView().getRootWindowInsets();
        int stableInsetLeft = rootWindowInsets != null ? rootWindowInsets.getStableInsetLeft() : 0;
        Integer numValueOf = Integer.valueOf(v());
        if (this.s0 != null) {
            boolean z2 = q.a;
            if (!q.a(this)) {
                numValueOf = null;
            }
        } else {
            numValueOf = null;
        }
        int iIntValue = numValueOf != null ? numValueOf.intValue() : 0;
        if (iIntValue == 1) {
            r0();
            rg.a(this, new za(this, 6), stableInsetLeft, new v1(this, 15));
        } else if (iIntValue != 2) {
            r0();
            rg.a(this, new j(8), stableInsetLeft, null);
        } else {
            if (!o0()) {
                r0();
            }
            rg.a(this, new ua(this, 29), stableInsetLeft, null);
        }
        WeakHashMap weakHashMap = f8.a;
        ArrayList arrayList2 = this.C;
        ArrayList arrayList3 = new ArrayList();
        int size2 = arrayList2.size();
        int i2 = 0;
        while (i2 < size2) {
            Object obj2 = arrayList2.get(i2);
            i2++;
            yc ycVar = (yc) obj2;
            String str = (String) ycVar.a;
            q7 q7Var = (q7) ycVar.b;
            if (q7Var.isAttachedToWindow() && q7Var.isShown() && q7Var.getWidth() > 0 && q7Var.getHeight() > 0) {
                HashMap map = v7.a;
                SharedPreferences sharedPreferencesB = B();
                sharedPreferencesB.getClass();
                if (v7.d(sharedPreferencesB, str).b != s7.d) {
                    arrayList3.add(obj2);
                }
            }
        }
        ArrayList arrayList4 = new ArrayList(k3.n0(arrayList3));
        int size3 = arrayList3.size();
        int i3 = 0;
        while (i3 < size3) {
            Object obj3 = arrayList3.get(i3);
            i3++;
            yc ycVar2 = (yc) obj3;
            String str2 = (String) ycVar2.a;
            q7 q7Var2 = (q7) ycVar2.b;
            int[] iArr = new int[2];
            q7Var2.getLocationOnScreen(iArr);
            int i4 = iArr[0];
            arrayList4.add(new yc(str2, new Rect(i4, iArr[1], q7Var2.getWidth() + i4, q7Var2.getHeight() + iArr[1])));
        }
        ArrayList arrayList5 = this.D;
        if (!arrayList4.isEmpty()) {
            boolean z3 = q.a;
            if (q.a(this) && bf.a(this) == null && !isFinishing() && !isDestroyed() && (windowManager = (WindowManager) getSystemService(WindowManager.class)) != null) {
                int size4 = arrayList4.size();
                int i5 = 0;
                while (i5 < size4) {
                    int i6 = i5 + 1;
                    yc ycVar3 = (yc) arrayList4.get(i5);
                    String str3 = (String) ycVar3.a;
                    Rect rect = (Rect) ycVar3.b;
                    q7 q7VarA = f8.a(str3, this, true);
                    boolean z4 = q7VarA.getOver() == s7.e;
                    int i7 = zj.a;
                    SharedPreferences sharedPreferencesB2 = B();
                    sharedPreferencesB2.getClass();
                    int iC = zj.c(sharedPreferencesB2, zj.d(str3));
                    Integer numValueOf2 = Integer.valueOf(iC);
                    if (iC <= 0) {
                        numValueOf2 = null;
                    }
                    if (numValueOf2 != null) {
                        zj.a(q7VarA, numValueOf2.intValue());
                    }
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(rect.width(), rect.height(), 2038, (z4 ? 16 : 0) | 776, -3);
                    layoutParams.gravity = 8388659;
                    layoutParams.x = rect.left;
                    layoutParams.y = rect.top;
                    layoutParams.setTitle("ReferencePanel picture");
                    q7VarA.setAlpha(0.0f);
                    q7VarA.setTag(layoutParams);
                    try {
                        windowManager.addView(q7VarA, layoutParams);
                        ffVar = ti.a;
                    } catch (Throwable th) {
                        ffVar = new ff(th);
                    }
                    if (!(ffVar instanceof ff)) {
                        arrayList5.add(q7VarA);
                        q7VarA.getViewTreeObserver().addOnGlobalLayoutListener(new e8(q7VarA, rect, layoutParams, z4, windowManager));
                    }
                    i5 = i6;
                }
                c0();
                z = !arrayList5.isEmpty();
            }
        }
        if (z) {
            t0();
            y().A();
            H().p();
        }
        I().g();
        super.onStop();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        int i = 0;
        ArrayList arrayList = this.w;
        if (!z) {
            int size = arrayList.size();
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                b bVar = (b) obj;
                bVar.f();
                bVar.i();
            }
            if (this.l0 == 0 || SystemClock.uptimeMillis() - this.l0 >= 2500) {
                return;
            }
            this.l0 = 0L;
            new Handler(Looper.getMainLooper()).postDelayed(new sa(this, 6), 300L);
            return;
        }
        d();
        if (this.y.b || this.c || this.f) {
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        int size2 = arrayList.size();
        int i2 = 0;
        while (i2 < size2) {
            Object obj2 = arrayList.get(i2);
            i2++;
            if (((b) obj2).isShown()) {
                arrayList2.add(obj2);
            }
        }
        int size3 = arrayList2.size();
        while (i < size3) {
            Object obj3 = arrayList2.get(i);
            i++;
            ((b) obj3).j();
        }
    }

    /* JADX WARN: Code duplicated, block: B:236:0x059a  */
    /* JADX WARN: Code duplicated, block: B:95:0x02a8  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v50, types: [android.view.ViewGroup, android.widget.FrameLayout] */
    /* JADX WARN: Type inference failed for: r0v59, types: [android.content.SharedPreferences] */
    /* JADX WARN: Type inference failed for: r21v10 */
    /* JADX WARN: Type inference failed for: r21v11 */
    /* JADX WARN: Type inference failed for: r21v12 */
    /* JADX WARN: Type inference failed for: r21v14 */
    /* JADX WARN: Type inference failed for: r2v11, types: [android.view.View, android.view.ViewGroup, android.widget.LinearLayout, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v109 */
    /* JADX WARN: Type inference failed for: r3v8, types: [java.util.Set] */
    /* JADX WARN: Type inference failed for: r3v85 */
    /* JADX WARN: Type inference failed for: r5v25, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v47 */
    public final FrameLayout p() throws Throwable {
        c4 c4Var;
        Throwable th;
        Object obj;
        int i;
        String str;
        byte b;
        int i2;
        MainActivity mainActivity;
        String str2;
        q7 q7VarA;
        Object ffVar;
        String strA;
        Object ffVar2;
        Drawable drawable;
        String str3;
        String str4;
        String str5;
        String str6;
        c4 c4Var2;
        ?? r21;
        Set<String> stringSet;
        c4 c4Var3;
        MainActivity mainActivity2 = this;
        c4 c4Var4 = new c4(mainActivity2, 1280.0f, 720.0f);
        boolean z = mainActivity2.f;
        c4Var4.f = true;
        c4Var4.g = z;
        mainActivity2.g = c4Var4;
        c4Var4.setOnItemTap(new v1(mainActivity2, 27));
        c4Var4.setOnHold(new b2(mainActivity2, 29));
        boolean z2 = mainActivity2.B().getBoolean("ios_music2_hidden", false);
        x4 x4Var = x4.a;
        if (!z2) {
            Set<String> stringSet2 = mainActivity2.B().getStringSet("desktop_hidden", x4Var);
            if (stringSet2 == null) {
                stringSet2 = x4Var;
            }
            Set<String> setK0 = i3.K0(stringSet2);
            setK0.add("ios_music2");
            mainActivity2.B().edit().putStringSet("desktop_hidden", setK0).putBoolean("ios_music2_hidden", true).apply();
        }
        String str7 = "desktop_ios_";
        if (mainActivity2.B().getBoolean("ios_home_v2", false)) {
            c4Var = c4Var4;
            th = null;
        } else {
            SharedPreferences.Editor editorEdit = mainActivity2.B().edit();
            Set<String> setKeySet = mainActivity2.B().getAll().keySet();
            th = null;
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : setKeySet) {
                String str8 = (String) obj2;
                str8.getClass();
                if (str8.startsWith("desktop_ios_")) {
                    c4Var3 = c4Var4;
                } else {
                    c4Var3 = c4Var4;
                    if (str8.startsWith("desktop_wall_")) {
                    }
                    c4Var4 = c4Var3;
                }
                arrayList.add(obj2);
                c4Var4 = c4Var3;
            }
            c4Var = c4Var4;
            int size = arrayList.size();
            int i3 = 0;
            while (i3 < size) {
                Object obj3 = arrayList.get(i3);
                i3++;
                editorEdit.remove((String) obj3);
            }
            Set<String> stringSet3 = mainActivity2.B().getStringSet("desktop_hidden", x4Var);
            if (stringSet3 == null) {
                stringSet3 = x4Var;
            }
            Set<String> setK1 = i3.K0(stringSet3);
            setK1.addAll(j3.i0("ios_maps", "ios_clock", "ios_car"));
            editorEdit.putStringSet("desktop_hidden", setK1).putBoolean("ios_home_v2", true);
            if (mainActivity2.B().getBoolean("ios_home_v1", false)) {
                ArrayList arrayList2 = new ArrayList(k8.T(mainActivity2));
                if (!arrayList2.contains("collapse") && arrayList2.size() < 20) {
                    int iIndexOf = arrayList2.indexOf("settings");
                    if (iIndexOf >= 0) {
                        arrayList2.add(iIndexOf, "collapse");
                    } else {
                        arrayList2.add("collapse");
                    }
                }
                for (int i4 = 0; i4 < 40; i4++) {
                    editorEdit.remove("menu_action_" + i4);
                }
                int i5 = 0;
                int i6 = 0;
                for (int size2 = arrayList2.size(); i6 < size2; size2 = size2) {
                    Object obj4 = arrayList2.get(i6);
                    i6++;
                    int i7 = i5 + 1;
                    if (i5 < 0) {
                        j3.m0();
                        throw null;
                    }
                    editorEdit.putString("menu_action_" + i5, (String) obj4);
                    i5 = i7;
                }
                editorEdit.putInt("menu_count", arrayList2.size());
            }
            editorEdit.apply();
        }
        String str9 = "vehicle_";
        if (!mainActivity2.B().getBoolean("ios_home_v1", false)) {
            List listI0 = j3.i0("temp", "airflow", "seat_heat_1", "apps", "seat_heat_4", "collapse", "settings");
            SharedPreferences.Editor editorEdit2 = mainActivity2.B().edit();
            for (int i8 = 0; i8 < 40; i8++) {
                editorEdit2.remove("menu_action_" + i8);
            }
            Iterator it = listI0.iterator();
            int i9 = 0;
            while (it.hasNext()) {
                Object next = it.next();
                int i10 = i9 + 1;
                if (i9 < 0) {
                    j3.m0();
                    throw th;
                }
                List list = listI0;
                editorEdit2.putString("menu_action_" + i9, (String) next);
                it = it;
                i9 = i10;
                listI0 = list;
            }
            List list2 = listI0;
            Set<String> stringSet4 = mainActivity2.B().getStringSet("desktop_hidden", x4Var);
            if (stringSet4 == null) {
                stringSet4 = x4Var;
            }
            Set<String> setK2 = i3.K0(stringSet4);
            setK2.addAll(j3.i0("ios_maps", "ios_clock", "ios_car"));
            Set<String> stringSet5 = mainActivity2.B().getStringSet("desktop_vehicle_cards", x4Var);
            if (stringSet5 == null) {
                stringSet5 = x4Var;
            }
            Iterator it2 = stringSet5.iterator();
            while (it2.hasNext()) {
                setK2.add("vehicle_" + ((String) it2.next()));
            }
            editorEdit2.putInt("menu_count", list2.size()).putBoolean("menu_labels", false).putStringSet("desktop_hidden", setK2).putBoolean("ios_home_v1", true).apply();
        }
        boolean zU = mainActivity2.u();
        String str10 = zU ? "wall_" : "ios_";
        String str11 = "car";
        if (zU) {
            stringSet = mainActivity2.B().getStringSet("wall_widgets", x0.Y0(new String[]{"clock", "car"}));
            if (stringSet == null) {
                obj = stringSet;
                obj = x4Var;
            }
        } else {
            obj = th;
        }
        obj = stringSet;
        Iterator it3 = mainActivity2.m0.iterator();
        ?? r3 = obj;
        while (true) {
            i = 2;
            str = "temp";
            if (!it3.hasNext()) {
                break;
            }
            String str12 = (String) it3.next();
            if (r3 == 0 || r3.contains(str12)) {
                Set<String> stringSet6 = mainActivity2.B().getStringSet("desktop_hidden", x4Var);
                if (stringSet6 == null) {
                    stringSet6 = x4Var;
                }
                if (stringSet6.contains(str10 + str12) || (((ci.t(str12, "degree_") || str12.equals("battery_compact")) && r3 == 0 && !mainActivity2.B().contains(str7.concat(str12))) || ((str12.equals("music_title") && r3 == 0 && !mainActivity2.B().contains("desktop_ios_music_title")) || ((str12.equals("temp") && r3 == 0 && !mainActivity2.B().contains("desktop_ios_temp")) || ((str12.equals("roof_slider") && r3 == 0 && !mainActivity2.B().contains("desktop_ios_roof_slider")) || ((str12.equals("automation") && r3 == 0 && !mainActivity2.B().contains("desktop_ios_automation")) || ((str12.equals("stats") && r3 == 0 && !mainActivity2.B().contains("desktop_ios_stats")) || ((str12.equals("theme") && r3 == 0 && !mainActivity2.B().contains("desktop_ios_theme")) || ((str12.equals("gps") && r3 == 0 && !mainActivity2.B().contains("desktop_ios_gps")) || ((str12.equals("temp_passenger") && r3 == 0 && !mainActivity2.B().contains("desktop_ios_temp_passenger")) || ((str12.equals("saver") && r3 == 0 && !mainActivity2.B().contains("desktop_ios_saver")) || ((str12.equals("camera360") && r3 == 0 && !mainActivity2.B().contains("desktop_ios_camera360")) || (x0.Y0(new String[]{"folder", "fuel_compact", "wifi_mark", "bt_mark", "radio", "anton", "app_window"}).contains(str12) && r3 == 0 && !mainActivity2.B().contains(str7.concat(str12))))))))))))))) {
                    str4 = str10;
                    str5 = str7;
                    str6 = str11;
                    c4Var2 = c4Var;
                    r21 = r3;
                } else {
                    ?? r7 = r3;
                    View viewP = mainActivity2.P(str12);
                    if (viewP == null) {
                        str4 = str10;
                        str5 = str7;
                        str6 = str11;
                        c4Var2 = c4Var;
                        r21 = r7;
                    } else {
                        int[] iArrN = N(str12);
                        str4 = str10;
                        str5 = str7;
                        str6 = str11;
                        c4Var2 = c4Var;
                        r21 = r7;
                        c4.c(c4Var2, viewP, iArrN[0], iArrN[1], iArrN[2], iArrN[3], str10.concat(str12), 32);
                        if (str12.equals(str6) && (viewP instanceof FrameLayout)) {
                            mainActivity2.b0((FrameLayout) viewP);
                        }
                    }
                }
            } else {
                str4 = str10;
                str5 = str7;
                str6 = str11;
                c4Var2 = c4Var;
                r21 = r3;
            }
            r3 = r21;
            str10 = str4;
            c4Var = c4Var2;
            str11 = str6;
            str7 = str5;
        }
        c4 c4Var5 = c4Var;
        Set<String> stringSet7 = mainActivity2.B().getStringSet(zU ? "wall_actions" : "desktop_actions", x4Var);
        if (stringSet7 == null) {
            stringSet7 = x4Var;
        }
        Iterator it4 = i3.F0(stringSet7).iterator();
        while (true) {
            if (!it4.hasNext()) {
                break;
            }
            String str13 = (String) it4.next();
            String str14 = (zU ? "wall_action_" : "action_") + str13;
            Set<String> stringSet8 = mainActivity2.B().getStringSet("desktop_hidden", x4Var);
            if (stringSet8 == null) {
                stringSet8 = x4Var;
            }
            if (stringSet8.contains(str14)) {
                str3 = str;
            } else {
                str13.getClass();
                str3 = str;
                c4.c(c4Var5, mainActivity2.n(str13, false, new ua(mainActivity2, 6)), 430, 210, str13.equals(str) ? 420 : 180, 180, str14, 32);
            }
            i = i;
            str = str3;
        }
        int i11 = i;
        Set<String> stringSet9 = mainActivity2.B().getStringSet(zU ? "wall_apps" : "desktop_apps", x4Var);
        if (stringSet9 == null) {
            stringSet9 = x4Var;
        }
        Iterator it5 = i3.F0(stringSet9).iterator();
        while (true) {
            b = -2;
            if (!it5.hasNext()) {
                break;
            }
            String str15 = (String) it5.next();
            str15.getClass();
            String string = mainActivity2.B().getString("desktop_app_".concat(str15), th);
            ComponentName componentNameUnflattenFromString = string != null ? ComponentName.unflattenFromString(string) : null;
            if (componentNameUnflattenFromString != null) {
                try {
                    ffVar = mainActivity2.getPackageManager().getActivityInfo(componentNameUnflattenFromString, 0).loadLabel(mainActivity2.getPackageManager()).toString();
                } catch (Throwable th2) {
                    ffVar = new ff(th2);
                }
                if (ffVar instanceof ff) {
                    ffVar = null;
                }
                strA = (String) ffVar;
            } else {
                strA = null;
            }
            vb vbVar = new vb(mainActivity2);
            vbVar.setBackground(new w6(mainActivity2.z()));
            vbVar.setOnClickListener(new u5(componentNameUnflattenFromString, strA, mainActivity2, str15));
            vbVar.setOnLongClickListener(new xa(mainActivity2, str15, 1));
            if (strA == null) {
                strA = si.a("choose_an_app", new Object[0]);
            }
            vbVar.setContentDescription(strA);
            ImageView imageView = new ImageView(mainActivity2);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            if (componentNameUnflattenFromString != null) {
                try {
                    ffVar2 = mainActivity2.getPackageManager().getActivityIcon(componentNameUnflattenFromString);
                } catch (Throwable th3) {
                    ffVar2 = new ff(th3);
                }
                if (ffVar2 instanceof ff) {
                    ffVar2 = null;
                }
                drawable = (Drawable) ffVar2;
                if (drawable == null) {
                    drawable = mainActivity2.getDrawable(R.drawable.sym_def_app_icon);
                }
            } else {
                drawable = mainActivity2.getDrawable(R.drawable.sym_def_app_icon);
            }
            imageView.setImageDrawable(drawable);
            vbVar.addView(imageView, new FrameLayout.LayoutParams(-2, -2, 17));
            c4.c(c4Var5, vbVar, 420, 220, 180, 180, str15, 32);
            th = null;
        }
        Set<String> stringSet10 = mainActivity2.B().getStringSet(zU ? "wall_images" : "desktop_images", x4Var);
        if (stringSet10 == null) {
            stringSet10 = x4Var;
        }
        Iterator it6 = i3.F0(stringSet10).iterator();
        while (true) {
            int i12 = 8;
            if (!it6.hasNext()) {
                break;
            }
            String str16 = (String) it6.next();
            if (mainActivity2.f) {
                boolean z3 = mainActivity2.z();
                HashMap map = v7.a;
                str16.getClass();
                Bitmap bitmapA = v7.a(mainActivity2, str16);
                SharedPreferences sharedPreferencesB = mainActivity2.B();
                sharedPreferencesB.getClass();
                i2 = i11;
                mainActivity = mainActivity2;
                q7VarA = new q7(mainActivity2, z3, bitmapA, v7.d(sharedPreferencesB, str16), null, new w(i12), null, null, null, 944);
                str2 = str16;
            } else {
                i2 = i11;
                mainActivity = mainActivity2;
                str16.getClass();
                str2 = str16;
                q7VarA = f8.a(str2, mainActivity, false);
            }
            c4.c(c4Var5, q7VarA, 400, 200, 240, 240, str2, 32);
            mainActivity2 = mainActivity;
            i11 = i2;
            str9 = str9;
            b = -2;
        }
        String str17 = str9;
        c4 c4Var6 = c4Var5;
        int i13 = i11;
        final MainActivity mainActivity3 = mainActivity2;
        Set<String> stringSet11 = mainActivity3.B().getStringSet(zU ? "wall_splits" : "desktop_splits", x4Var);
        if (stringSet11 == null) {
            stringSet11 = x4Var;
        }
        for (String str18 : i3.F0(stringSet11)) {
            List list3 = eh.a;
            SharedPreferences sharedPreferencesB2 = mainActivity3.B();
            sharedPreferencesB2.getClass();
            if (eh.c(sharedPreferencesB2).contains(str18)) {
                str18.getClass();
                c4 c4Var7 = c4Var6;
                c4.c(c4Var7, k8.C0(str18, mainActivity3, true), 720, 140, 220, 120, (zU ? "wsplit_" : "split_").concat(str18), 32);
                c4Var6 = c4Var7;
            }
        }
        String str19 = zU ? "wall_vehicle_" : str17;
        Set<String> stringSet12 = mainActivity3.B().getStringSet(zU ? "wall_vehicle_cards" : "desktop_vehicle_cards", x4Var);
        if (stringSet12 == null) {
            stringSet12 = x4Var;
        }
        for (String str20 : i3.F0(stringSet12)) {
            String str21 = str19 + str20;
            Set set = aj.i;
            if (sh.e().containsKey(str20)) {
                str20.getClass();
                String str22 = mainActivity3.E().a;
                if (aj.i.contains(str20)) {
                    List list4 = z2.a;
                }
                Set<String> stringSet13 = mainActivity3.B().getStringSet("desktop_hidden", x4Var);
                if (stringSet13 == null) {
                    stringSet13 = x4Var;
                }
                if (!stringSet13.contains(str21)) {
                    c4 c4Var8 = c4Var6;
                    aj ajVar = new aj(mainActivity3, str20, mainActivity3.z(), new w7(mainActivity3, str20, i13));
                    ajVar.a(mainActivity3.Z);
                    ajVar.setOnLongClickListener(new xa(mainActivity3, str20, 0));
                    mainActivity3.z.add(ajVar);
                    c4.c(c4Var8, ajVar, 430, 210, str20.equals("climate") ? 320 : 180, 180, str21, 32);
                    c4Var6 = c4Var8;
                }
            }
        }
        String string2 = mainActivity3.B().getString("dock_size", "m");
        String str23 = string2 != null ? string2 : "m";
        int i14 = str23.equals("s") ? 104 : str23.equals("l") ? 152 : 128;
        List listI1 = j3.i0(40, Integer.valueOf(700 - i14), 1200, Integer.valueOf(i14));
        final c4 c4Var9 = c4Var6;
        c4.c(c4Var9, mainActivity3.K(false), ((Number) listI1.get(0)).intValue(), ((Number) listI1.get(1)).intValue(), ((Number) listI1.get(i13)).intValue(), ((Number) listI1.get(3)).intValue(), "ios_dock", 32);
        if (!mainActivity3.f) {
            return c4Var9;
        }
        ?? frameLayout = new FrameLayout(mainActivity3);
        frameLayout.addView(c4Var9, new FrameLayout.LayoutParams(-1, -1));
        boolean z4 = mainActivity3.z();
        i4 i4Var = new i4(mainActivity3, z4);
        final int i15 = 0;
        ua uaVar = new ua(mainActivity3, i15);
        final int i16 = 1;
        ua uaVar2 = new ua(mainActivity3, i16);
        y5 y5Var = new y5() { // from class: va
            @Override // defpackage.y5
            public final Object a() {
                int i17 = i15;
                ti tiVar = ti.a;
                MainActivity mainActivity4 = mainActivity3;
                c4 c4Var10 = c4Var9;
                switch (i17) {
                    case 0:
                        int i18 = MainActivity.E0;
                        SharedPreferences sharedPreferences = c4Var10.e;
                        b4 b4Var = c4Var10.h;
                        if (b4Var != null) {
                            String str24 = b4Var.e;
                            boolean zStartsWith = str24.startsWith("split_");
                            Set<String> set2 = x4.a;
                            if (zStartsWith || str24.startsWith("wsplit_")) {
                                String str25 = str24.startsWith("wsplit_") ? "wall_splits" : "desktop_splits";
                                int iZ = th.z(str24, '_', 0, 6);
                                String strSubstring = iZ == -1 ? str24 : str24.substring(iZ + 1, str24.length());
                                SharedPreferences.Editor editorEdit3 = sharedPreferences.edit();
                                Set<String> stringSet14 = sharedPreferences.getStringSet(str25, set2);
                                if (stringSet14 != null) {
                                    set2 = stringSet14;
                                }
                                editorEdit3.putStringSet(str25, zf.g0(set2, strSubstring)).remove("desktop_".concat(str24)).apply();
                            } else {
                                HashMap map2 = v7.a;
                                if (str24.startsWith("img_") || str24.startsWith("wimg_")) {
                                    Context context = c4Var10.getContext();
                                    context.getClass();
                                    sharedPreferences.getClass();
                                    String str26 = str24.startsWith("wimg_") ? "wall_images" : "desktop_images";
                                    SharedPreferences.Editor editorEdit4 = sharedPreferences.edit();
                                    Set<String> stringSet15 = sharedPreferences.getStringSet(str26, set2);
                                    if (stringSet15 != null) {
                                        set2 = stringSet15;
                                    }
                                    editorEdit4.putStringSet(str26, zf.g0(set2, str24)).remove(v7.b(str24)).remove("desktop_".concat(str24)).apply();
                                    v7.c(context, str24).delete();
                                    v7.a.remove(str24);
                                } else if (str24.startsWith("extra_") || str24.startsWith("wextra_")) {
                                    String str27 = str24.startsWith("wextra_") ? "wall_apps" : "desktop_apps";
                                    Set<String> stringSet16 = sharedPreferences.getStringSet(str27, set2);
                                    if (stringSet16 != null) {
                                        set2 = stringSet16;
                                    }
                                    Set<String> setK3 = i3.K0(set2);
                                    setK3.remove(str24);
                                    sharedPreferences.edit().putStringSet(str27, setK3).remove("desktop_app_".concat(str24)).remove("desktop_".concat(str24)).apply();
                                } else {
                                    Set<String> stringSet17 = sharedPreferences.getStringSet("desktop_hidden", set2);
                                    if (stringSet17 != null) {
                                        set2 = stringSet17;
                                    }
                                    Set<String> setK4 = i3.K0(set2);
                                    setK4.add(str24);
                                    sharedPreferences.edit().putStringSet("desktop_hidden", setK4).apply();
                                }
                            }
                            c4Var10.d.remove(b4Var);
                            c4Var10.removeView(b4Var.a);
                            c4Var10.h = null;
                        } else {
                            mainActivity4.v0(si.a("tap_a_widget_first", new Object[0]));
                        }
                        break;
                    default:
                        int i19 = MainActivity.E0;
                        if (!c4Var10.b()) {
                            mainActivity4.v0(si.a("tap_a_widget_first", new Object[0]));
                        }
                        break;
                }
                return tiVar;
            }
        };
        ua uaVar3 = new ua(mainActivity3, i13);
        ua uaVar4 = new ua(mainActivity3, 3);
        ua uaVar5 = new ua(mainActivity3, 4);
        y5 y5Var2 = new y5() { // from class: va
            @Override // defpackage.y5
            public final Object a() {
                int i17 = i16;
                ti tiVar = ti.a;
                MainActivity mainActivity4 = mainActivity3;
                c4 c4Var10 = c4Var9;
                switch (i17) {
                    case 0:
                        int i18 = MainActivity.E0;
                        SharedPreferences sharedPreferences = c4Var10.e;
                        b4 b4Var = c4Var10.h;
                        if (b4Var != null) {
                            String str24 = b4Var.e;
                            boolean zStartsWith = str24.startsWith("split_");
                            Set<String> set2 = x4.a;
                            if (zStartsWith || str24.startsWith("wsplit_")) {
                                String str25 = str24.startsWith("wsplit_") ? "wall_splits" : "desktop_splits";
                                int iZ = th.z(str24, '_', 0, 6);
                                String strSubstring = iZ == -1 ? str24 : str24.substring(iZ + 1, str24.length());
                                SharedPreferences.Editor editorEdit3 = sharedPreferences.edit();
                                Set<String> stringSet14 = sharedPreferences.getStringSet(str25, set2);
                                if (stringSet14 != null) {
                                    set2 = stringSet14;
                                }
                                editorEdit3.putStringSet(str25, zf.g0(set2, strSubstring)).remove("desktop_".concat(str24)).apply();
                            } else {
                                HashMap map2 = v7.a;
                                if (str24.startsWith("img_") || str24.startsWith("wimg_")) {
                                    Context context = c4Var10.getContext();
                                    context.getClass();
                                    sharedPreferences.getClass();
                                    String str26 = str24.startsWith("wimg_") ? "wall_images" : "desktop_images";
                                    SharedPreferences.Editor editorEdit4 = sharedPreferences.edit();
                                    Set<String> stringSet15 = sharedPreferences.getStringSet(str26, set2);
                                    if (stringSet15 != null) {
                                        set2 = stringSet15;
                                    }
                                    editorEdit4.putStringSet(str26, zf.g0(set2, str24)).remove(v7.b(str24)).remove("desktop_".concat(str24)).apply();
                                    v7.c(context, str24).delete();
                                    v7.a.remove(str24);
                                } else if (str24.startsWith("extra_") || str24.startsWith("wextra_")) {
                                    String str27 = str24.startsWith("wextra_") ? "wall_apps" : "desktop_apps";
                                    Set<String> stringSet16 = sharedPreferences.getStringSet(str27, set2);
                                    if (stringSet16 != null) {
                                        set2 = stringSet16;
                                    }
                                    Set<String> setK3 = i3.K0(set2);
                                    setK3.remove(str24);
                                    sharedPreferences.edit().putStringSet(str27, setK3).remove("desktop_app_".concat(str24)).remove("desktop_".concat(str24)).apply();
                                } else {
                                    Set<String> stringSet17 = sharedPreferences.getStringSet("desktop_hidden", set2);
                                    if (stringSet17 != null) {
                                        set2 = stringSet17;
                                    }
                                    Set<String> setK4 = i3.K0(set2);
                                    setK4.add(str24);
                                    sharedPreferences.edit().putStringSet("desktop_hidden", setK4).apply();
                                }
                            }
                            c4Var10.d.remove(b4Var);
                            c4Var10.removeView(b4Var.a);
                            c4Var10.h = null;
                        } else {
                            mainActivity4.v0(si.a("tap_a_widget_first", new Object[0]));
                        }
                        break;
                    default:
                        int i19 = MainActivity.E0;
                        if (!c4Var10.b()) {
                            mainActivity4.v0(si.a("tap_a_widget_first", new Object[0]));
                        }
                        break;
                }
                return tiVar;
            }
        };
        ?? E = i4Var.e();
        E.setBackground(i4Var.i(z4 ? -284024271 : -286066692));
        E.setPadding(i4Var.g(16), i4Var.g(10), i4Var.g(16), i4Var.g(10));
        LinearLayout linearLayout = new LinearLayout(mainActivity3);
        linearLayout.setGravity(16);
        LinearLayout linearLayoutE = i4Var.e();
        TextView textViewH = i4Var.h(si.a("your_home_screen", new Object[0]), 22.0f, i4Var.c);
        textViewH.setTypeface(null, 1);
        linearLayoutE.addView(textViewH);
        TextView textViewH2 = i4Var.h(si.a("tap_a_widget_to_select_drag_to", new Object[0]), 13.0f, i4Var.d);
        textViewH2.setPadding(0, i4Var.g(4), 0, 0);
        linearLayoutE.addView(textViewH2);
        linearLayout.addView(linearLayoutE, new LinearLayout.LayoutParams(0, -2, 1.0f));
        linearLayout.addView(i4Var.a(si.a("done_107", new Object[0]), true, uaVar));
        E.addView(linearLayout);
        LinearLayout linearLayout2 = new LinearLayout(mainActivity3);
        oi oiVar = new oi(si.a("widget", new Object[0]), Boolean.TRUE, uaVar2);
        String strA2 = si.a("size_and_position", new Object[0]);
        Boolean bool = Boolean.FALSE;
        for (oi oiVar2 : j3.i0(oiVar, new oi(strA2, bool, y5Var2), new oi(si.a("style_and_grid", new Object[0]), bool, uaVar5), new oi(si.a("scale", new Object[0]), bool, uaVar3), new oi(si.a("remove_selected", new Object[0]), bool, y5Var), new oi(si.a("reset", new Object[0]), bool, uaVar4))) {
            Button buttonA = i4Var.a((String) oiVar2.a, ((Boolean) oiVar2.b).booleanValue(), (y5) oiVar2.c);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, i4Var.g(48));
            layoutParams.setMarginEnd(i4Var.g(8));
            linearLayout2.addView(buttonA, layoutParams);
        }
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(mainActivity3);
        horizontalScrollView.setHorizontalScrollBarEnabled(false);
        horizontalScrollView.addView(linearLayout2);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.topMargin = i4Var.g(10);
        E.addView(horizontalScrollView, layoutParams2);
        frameLayout.addView(E, new FrameLayout.LayoutParams(-1, -2, 80));
        frameLayout.addView(i(mainActivity3, si.a("tools", new Object[0]), new x7(3, (Object) E)), new FrameLayout.LayoutParams(mainActivity3.r(170), mainActivity3.r(48), 8388661));
        return frameLayout;
    }

    public final void p0(AlertDialog.Builder builder) {
        Window window;
        AlertDialog alertDialogCreate = builder.create();
        alertDialogCreate.getClass();
        if (pi.c && (window = alertDialogCreate.getWindow()) != null) {
            window.setType(2038);
        }
        Window window2 = alertDialogCreate.getWindow();
        if (window2 != null) {
            window2.setFlags(8, 8);
        }
        alertDialogCreate.show();
        Window window3 = alertDialogCreate.getWindow();
        if (window3 != null) {
            window3.getDecorView().setSystemUiVisibility(getWindow().getDecorView().getSystemUiVisibility());
            window3.clearFlags(8);
        }
    }

    public final w2 q(String str, boolean z) {
        w2 w2Var = new w2(this, z(), str.equals("degree_passenger") ? 4 : 1, z, new b2(this, 13));
        this.L.add(w2Var);
        w2Var.b((Float) this.Z.i.a.get(Integer.valueOf(w2Var.getZone())));
        return w2Var;
    }

    public final void q0() {
        this.h0.removeCallbacks(this.i0);
        if (this.d0 != null || this.c || this.f) {
            return;
        }
        g();
    }

    public final int r(int i) {
        return (int) (w() * i * getResources().getDisplayMetrics().density);
    }

    public final void r0() {
        y().C();
        H().q();
        this.Z = new bj(null, null, null, null, null, null, 67108863);
    }

    public final FrameLayout s(int i) {
        Object bVar;
        int i2 = this.x;
        this.x = i2 + 1;
        ArrayList arrayList = this.w;
        Iterator it = i3.s0(arrayList, i2).iterator();
        do {
            if (!it.hasNext()) {
                bVar = null;
                break;
            }
            bVar = it.next();
        } while (((b) bVar).getSlot() != i);
        if (bVar != null) {
            b bVar2 = (b) bVar;
            boolean z = !this.f;
            if (!k8.e(bVar2.t, bVar2.c.a()) || bVar2.g != z) {
                bVar2.c();
                arrayList.remove(bVar);
                bVar = null;
            }
        }
        int i3 = 0;
        if (bVar == null) {
            boolean z2 = !this.f;
            boolean z3 = z();
            c3 c3Var = new c3(this, i, 2);
            c3 c3Var2 = new c3(this, i, 3);
            v1 v1Var = new v1(this, 19);
            ra raVar = new ra(this, i, i3);
            boolean z4 = true;
            b2 b2Var = new b2(this, 14);
            if (i == 0) {
                z4 = false;
            }
            bVar = new b(this, z3, c3Var, c3Var2, v1Var, raVar, z2, b2Var, i, z4);
        } else {
            arrayList.remove(bVar);
        }
        int size = arrayList.size();
        if (i2 > size) {
            i2 = size;
        }
        arrayList.add(i2, bVar);
        b bVar3 = (b) bVar;
        boolean z5 = z();
        bVar3.b = z5;
        bVar3.D.setColor(Color.rgb(20, 27, 36));
        int childCount = bVar3.getChildCount();
        while (i3 < childCount) {
            View childAt = bVar3.getChildAt(i3);
            TextView textView = childAt instanceof TextView ? (TextView) childAt : null;
            if (textView != null) {
                textView.setTextColor(l8.k(z5));
                textView.setTypeface(z9.b());
            }
            i3++;
        }
        bVar3.invalidate();
        ViewParent parent = bVar3.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            viewGroup.removeView((View) bVar);
        }
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.addView((View) bVar, new FrameLayout.LayoutParams(-1, -1));
        return frameLayout;
    }

    public final void s0() {
        qf qfVar = this.d0;
        if (qfVar != null) {
            ViewParent parent = qfVar.getParent();
            ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup != null) {
                viewGroup.removeView(qfVar);
            }
        }
        this.d0 = null;
        boolean z = q.a;
        q.b = false;
        if (qfVar != null) {
            d();
        }
        h0();
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public final void startActivity(Intent intent, Bundle bundle) {
        if (this.t0 != null) {
            k();
            e0();
        }
        super.startActivity(intent, bundle);
    }

    @Override // android.app.Activity
    public final void startActivityForResult(Intent intent, int i, Bundle bundle) {
        ab abVar;
        if (i >= 0 && (abVar = this.t0) != null) {
            if (abVar != null) {
                k();
                e0();
            }
            h();
        }
        super.startActivityForResult(intent, i, bundle);
    }

    public final int t() {
        return this.B0;
    }

    public final void t0() {
        pi.c = (this.q0 == null && this.D.isEmpty() && this.t0 == null) ? false : true;
    }

    public final boolean u() {
        return B().getBoolean("ios_collapsed", false);
    }

    public final TextView u0(String str, float f, int i) {
        str.getClass();
        TextView textView = new TextView(this);
        textView.setText(str);
        textView.setTextSize(w() * f);
        textView.setTextColor(i);
        textView.setIncludeFontPadding(false);
        return textView;
    }

    public final int v() {
        return B().getInt(this.A0, B().getBoolean(this.z0, false) ? 1 : 0);
    }

    public final void v0(String str) {
        str.getClass();
        Toast.makeText(this, str, 1).show();
    }

    public final float w() {
        return pi.r(B().getFloat("ui_scale", 1.0f), 0.75f, 1.5f);
    }

    /* JADX WARN: Code duplicated, block: B:30:0x00c7  */
    public final void w0() {
        Drawable drawable;
        float f;
        FrameLayout frameLayout = this.r;
        Iterator it = i3.J0(i3.K0(i3.D0(this.N, frameLayout != null ? pi.J(frameLayout) : u4.a))).iterator();
        while (it.hasNext()) {
            View childAt = ((FrameLayout) it.next()).getChildAt(0);
            ImageView imageView = childAt instanceof ImageView ? (ImageView) childAt : null;
            if (imageView != null && (drawable = imageView.getDrawable()) != null && imageView.getWidth() != 0 && imageView.getHeight() != 0) {
                float intrinsicWidth = drawable.getIntrinsicWidth();
                float intrinsicHeight = drawable.getIntrinsicHeight();
                RectF rectF = this.m;
                if (rectF == null) {
                    rectF = new RectF(0.0f, 0.0f, intrinsicWidth, intrinsicHeight);
                }
                float fMin = Math.min(imageView.getWidth() / rectF.width(), imageView.getHeight() / rectF.height()) * ((B().getInt("image_scale_".concat(E().a), 20) / 100.0f) + 0.8f);
                if (o(E().a).exists()) {
                    f = fMin;
                } else if (x0.Y0(new String[]{"livan_s6", "livan_x6", "geely_coolray", "geely_emgrand", "geely_tugella"}).contains(E().a)) {
                    f = -fMin;
                } else {
                    f = fMin;
                }
                Matrix matrix = new Matrix();
                matrix.setScale(f, fMin);
                matrix.postTranslate((imageView.getWidth() / 2.0f) - (rectF.centerX() * f), (imageView.getHeight() / 2.0f) - (rectF.centerY() * fMin));
                imageView.setImageMatrix(matrix);
            }
        }
    }

    public final boolean x() {
        return this.h;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0050  */
    public final void x0(String str, cj cjVar) {
        Boolean boolValueOf;
        String str2;
        Integer num;
        str.getClass();
        cjVar.getClass();
        cjVar.setNightMode(z());
        Set set = rf.d;
        rf rfVar = (rf) pi.B().get(str);
        boolean z = true;
        Boolean boolValueOf2 = null;
        if (rfVar != null) {
            tf tfVar = (tf) this.Z.j.get(str);
            Integer num2 = tfVar != null ? tfVar.a : null;
            cjVar.setMaxLevel(rfVar.a());
            cjVar.setLevel(rfVar.c(num2));
            Integer numC = rfVar.c(num2);
            if (numC != null) {
                if (numC.intValue() <= 0) {
                    z = false;
                }
                boolValueOf2 = Boolean.valueOf(z);
            } else if (num2 != null) {
                if (num2.intValue() != rfVar.a + 15) {
                    z = false;
                }
                boolValueOf2 = Boolean.valueOf(z);
            }
            cjVar.setEnabledState(boolValueOf2);
            return;
        }
        if (str.equals("steering")) {
            List list = z2.a;
            Integer numI = z2.i((Integer) this.Z.i.f.get(269025536));
            cjVar.setLevel(numI);
            if (numI != null) {
                boolValueOf2 = Boolean.valueOf(numI.intValue() > 0);
            }
            cjVar.setEnabledState(boolValueOf2);
            return;
        }
        if (str.equals("airflow")) {
            List list2 = z2.a;
            cjVar.setBlowMode(z2.a((Integer) this.Z.i.f.get(268894464)));
            cjVar.setLevel(null);
            cjVar.setEnabledState(Boolean.valueOf((cjVar.getBlowMode() == null || (num = (Integer) this.Z.i.f.get(268501248)) == null || num.intValue() == 0) ? false : true));
            return;
        }
        if (!str.equals("cruise")) {
            Set set2 = aj.i;
            if (sh.g(str)) {
                cjVar.setEnabledState(Boolean.valueOf(sh.n(str, this.Z)));
                cjVar.setLevel(null);
                return;
            }
            boolean zEquals = str.equals("ambient");
            bj bjVar = this.Z;
            if (zEquals) {
                cjVar.setEnabledState(bjVar.e);
                cjVar.setLevel(null);
                return;
            }
            Integer num3 = (Integer) bjVar.i.f.get(268501248);
            if (num3 != null) {
                boolValueOf = Boolean.valueOf(num3.intValue() != 0);
            } else {
                boolValueOf = null;
            }
            cjVar.setEnabledState(boolValueOf);
            cjVar.setLevel(null);
            return;
        }
        cjVar.setCruiseMode(this.Z.r);
        Integer num4 = this.Z.r;
        if (num4 != null && num4.intValue() == 537069058) {
            boolValueOf2 = Boolean.FALSE;
        } else if (num4 != null && num4.intValue() == 537069059) {
            boolValueOf2 = Boolean.TRUE;
        }
        cjVar.setEnabledState(boolValueOf2);
        String strA = si.a("cruise_mode", new Object[0]);
        Integer num5 = this.Z.r;
        if (num5 != null && num5.intValue() == 537069058) {
            str2 = "ACC";
        } else {
            str2 = (num5 != null && num5.intValue() == 537069059) ? "G-Pilot" : "—";
        }
        cjVar.setContentDescription(strA + ": " + str2);
    }

    public final gc y() {
        gc gcVar = this.v;
        if (gcVar != null) {
            return gcVar;
        }
        k8.H0("musicMonitor");
        throw null;
    }

    public final String y0() {
        if (!E().a.equals("custom")) {
            zi ziVarE = E();
            return defpackage.c.d(ziVarE.b, " ", ziVarE.c);
        }
        String string = B().getString("custom_title", si.a("my_vehicle", new Object[0]));
        string.getClass();
        return string;
    }

    public final boolean z() {
        String strD = D();
        if (strD.equals("day")) {
            return false;
        }
        return !strD.equals("system") || (getResources().getConfiguration().uiMode & 48) == 32;
    }

    public final void z0() {
        if (!I().b()) {
            requestPermissions(vj.k, 47);
        } else {
            I().e(true);
            v0(si.a("updating_weather", new Object[0]));
        }
    }
}
