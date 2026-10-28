package defpackage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class pi {
    public static final Object[] a = new Object[0];
    public static final ca b = new ca();
    public static volatile boolean c;
    public static volatile boolean d;

    public static List A() {
        String strA = si.a("eco_mode", new Object[0]);
        x4 x4Var = x4.a;
        return j3.i0(new n4("drive_eco", 570491137, strA, x4Var), new n4("drive_comfort", 570491138, si.a("comfort_mode", new Object[0]), P(570491153)), new n4("drive_sport", 570491139, si.a("sport_mode", new Object[0]), x4Var), new n4("drive_sport_plus", 570491157, si.a("sport_mode_674", new Object[0]), x4Var), new n4("drive_power", 570491144, si.a("power_mode", new Object[0]), x4Var), new n4("drive_snow", 570491145, si.a("snow_mode", new Object[0]), x4Var), new n4("drive_sand", 570491149, si.a("sand_mode", new Object[0]), x4Var), new n4("drive_offroad", 570491155, si.a("offroad_mode", new Object[0]), x4Var), new n4("drive_adaptive", 570491158, si.a("adaptive_mode", new Object[0]), x4Var));
    }

    public static LinkedHashMap B() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Set set = rf.d;
        for (Map.Entry entry : hb.h0(new yc(1, si.a("front_left", new Object[0])), new yc(4, si.a("front_right", new Object[0]))).entrySet()) {
            int iIntValue = ((Number) entry.getKey()).intValue();
            String str = (String) entry.getValue();
            linkedHashMap.put("seat_heat_" + iIntValue, new rf(268763648, iIntValue, si.a("0_heating", str)));
            linkedHashMap.put("seat_vent_" + iIntValue, new rf(268763392, iIntValue, si.a("0_ventilation", str)));
        }
        return linkedHashMap;
    }

    public static LinkedHashMap C() {
        return hb.h0(new yc(16711680, si.a("red", new Object[0])), new yc(16738560, si.a("orange", new Object[0])), new yc(16770846, si.a("yellow", new Object[0])), new yc(1638190, si.a("green", new Object[0])), new yc(60159, si.a("teal", new Object[0])), new yc(47871, si.a("light_blue", new Object[0])), new yc(24575, si.a("blue", new Object[0])), new yc(9326079, si.a("purple", new Object[0])), new yc(16714692, si.a("pink", new Object[0])), new yc(16777215, si.a("white", new Object[0])));
    }

    public static final int D(int i, int i2, int i3) {
        if (i3 > 0) {
            if (i < i2) {
                int i4 = i2 % i3;
                if (i4 < 0) {
                    i4 += i3;
                }
                int i5 = i % i3;
                if (i5 < 0) {
                    i5 += i3;
                }
                int i6 = (i4 - i5) % i3;
                if (i6 < 0) {
                    i6 += i3;
                }
                return i2 - i6;
            }
        } else {
            if (i3 >= 0) {
                w.a("Step is zero.");
                return 0;
            }
            if (i > i2) {
                int i7 = -i3;
                int i8 = i % i7;
                if (i8 < 0) {
                    i8 += i7;
                }
                int i9 = i2 % i7;
                if (i9 < 0) {
                    i9 += i7;
                }
                int i10 = (i8 - i9) % i7;
                if (i10 < 0) {
                    i10 += i7;
                }
                return i10 + i2;
            }
        }
        return i2;
    }

    public static final String E(MainActivity mainActivity, int i) {
        mainActivity.getClass();
        List list = si.a;
        return String.format(si.b.b, "%,d", Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
    }

    public static final void F(String str, MainActivity mainActivity) {
        mainActivity.getClass();
        str.getClass();
        me meVar = k7.a;
        File[] fileArrListFiles = mainActivity.getFilesDir().listFiles();
        if (fileArrListFiles == null) {
            fileArrListFiles = new File[0];
        }
        ArrayList arrayList = new ArrayList();
        for (File file : fileArrListFiles) {
            if (file.isFile()) {
                String name = file.getName();
                name.getClass();
                if (name.startsWith("widget_image_")) {
                    arrayList.add(file);
                }
            }
        }
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            File file2 = (File) obj;
            me meVar2 = k7.a;
            file2.getClass();
            k7.a(mainActivity, file2);
        }
        i4 i4Var = new i4(mainActivity, mainActivity.z());
        LinearLayout linearLayoutL = mainActivity.l();
        me meVar3 = new me(0);
        je jeVar = new je();
        me meVar4 = new me(0);
        l7 l7Var = new l7(linearLayoutL, i4Var, mainActivity, jeVar, meVar3, str, meVar4);
        meVar4.b = l7Var;
        l7Var.a();
        String strA = si.a("image_gallery", new Object[0]);
        String strA2 = si.a("image_gallery_hint", new Object[0]);
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.addView(linearLayoutL);
        Dialog dialogF = i4Var.f(strA, strA2, scrollView);
        meVar3.b = dialogF;
        dialogF.show();
    }

    public static final void G(me meVar, MainActivity mainActivity, String str, boolean z) {
        Object obj = meVar.b;
        if (obj == null) {
            k8.H0("dialog");
            throw null;
        }
        ((Dialog) obj).dismiss();
        if (!z) {
            mainActivity.v0(si.a("could_not_load_image", new Object[0]));
        } else {
            mainActivity.e0();
            f8.b(str, mainActivity);
        }
    }

    public static final void H(GridLayout gridLayout, MainActivity mainActivity, ImageView imageView) {
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        layoutParams.width = 0;
        layoutParams.height = mainActivity.r(110);
        layoutParams.columnSpec = GridLayout.spec(Integer.MIN_VALUE, 1.0f);
        layoutParams.setMargins(mainActivity.r(4), mainActivity.r(4), mainActivity.r(4), mainActivity.r(4));
        gridLayout.addView(imageView, layoutParams);
    }

    public static final ImageView I(MainActivity mainActivity, Bitmap bitmap, String str, y5 y5Var, u0 u0Var) {
        ImageView imageView = new ImageView(mainActivity);
        imageView.setImageBitmap(bitmap);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setContentDescription(str);
        imageView.setPadding(mainActivity.r(8), mainActivity.r(8), mainActivity.r(8), mainActivity.r(8));
        imageView.setBackground(new w6(mainActivity.z()));
        imageView.setClickable(true);
        imageView.setFocusable(true);
        imageView.setOnClickListener(new m2(y5Var, 2));
        if (u0Var != null) {
            imageView.setOnLongClickListener(new o7(u0Var, 0));
        }
        return imageView;
    }

    public static List J(Object obj) {
        List listSingletonList = Collections.singletonList(obj);
        listSingletonList.getClass();
        return listSingletonList;
    }

    public static final void K(MainActivity mainActivity, String str, int i, int i2, int i3, int i4, String str2, j6 j6Var, j6 j6Var2) {
        String strValueOf;
        mainActivity.getClass();
        str.getClass();
        str2.getClass();
        List listJ0 = i3.J0(W(new j8(i2, i3, 1), i4));
        ArrayList arrayList = new ArrayList(k3.n0(listJ0));
        Iterator it = listJ0.iterator();
        while (it.hasNext()) {
            int iIntValue = ((Number) it.next()).intValue();
            if (j6Var == null || (strValueOf = (String) j6Var.b(Integer.valueOf(iIntValue))) == null) {
                if (th.A(str2)) {
                    strValueOf = String.valueOf(iIntValue);
                } else {
                    strValueOf = iIntValue + " " + str2;
                }
            }
            arrayList.add(strValueOf);
        }
        int i5 = 0;
        String[] strArr = (String[]) arrayList.toArray(new String[0]);
        AlertDialog.Builder title = new AlertDialog.Builder(mainActivity).setTitle(str);
        String[] strArr2 = strArr;
        int iIndexOf = listJ0.indexOf(Integer.valueOf(i));
        if (iIndexOf < 0) {
            iIndexOf = 0;
        }
        c.e("cancel", new Object[0], title.setSingleChoiceItems(strArr2, iIndexOf, new c2(j6Var2, listJ0, i5)), null, mainActivity);
    }

    public static LinkedHashMap L(LinkedHashMap linkedHashMap) {
        if (linkedHashMap.isEmpty()) {
            throw new IllegalArgumentException(si.a("the_archive_contains_no_settings_to_restore", new Object[0]).toString());
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(linkedHashMap);
        Set<String> setKeySet = linkedHashMap2.keySet();
        if (setKeySet == null || !setKeySet.isEmpty()) {
            for (String str : setKeySet) {
                if (ci.t(str, "desktop_") || str.startsWith("menu_action_") || str.equals("menu_count") || str.startsWith("wall_")) {
                    for (String str2 : j3.i0("ios_home_v1", "ios_home_v2", "ios_music2_hidden")) {
                        if (!linkedHashMap2.containsKey(str2)) {
                            linkedHashMap2.put(str2, Boolean.TRUE);
                        }
                    }
                    break;
                }
            }
        }
        return linkedHashMap2;
    }

    public static final byte[] M(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(Math.max(8192, inputStream.available()));
        u(inputStream, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArray.getClass();
        return byteArray;
    }

    public static final void N(Object[] objArr, int i, int i2) {
        objArr.getClass();
        while (i < i2) {
            objArr[i] = null;
            i++;
        }
    }

    public static zb O(MediaController mediaController, String str, final xb xbVar) {
        final long j;
        PlaybackState playbackState;
        str.getClass();
        final MediaController mediaController2 = (mediaController == null || !k8.e(mediaController.getPackageName(), str)) ? null : mediaController;
        final long actions = (mediaController2 == null || (playbackState = mediaController2.getPlaybackState()) == null) ? 0L : playbackState.getActions();
        int iOrdinal = xbVar.ordinal();
        if (iOrdinal == 0) {
            j = 4;
        } else if (iOrdinal == 1) {
            j = 2;
        } else if (iOrdinal == 2) {
            j = 512;
        } else if (iOrdinal == 3) {
            j = 32;
        } else {
            if (iOrdinal != 4) {
                throw new vc();
            }
            j = 16;
        }
        boolean z = (mediaController2 == null || (actions & j) == 0 || dd.b(str) || xbVar == xb.c) ? false : true;
        y1 y1Var = new y1(mediaController2, xbVar, 7);
        y1 y1Var2 = new y1(str, xbVar, 8);
        y5 y5Var = new y5() { // from class: yb
            /* JADX WARN: Code duplicated, block: B:16:0x0039  */
            @Override // defpackage.y5
            public final Object a() {
                boolean z2;
                Object ffVar;
                MediaController mediaController3 = mediaController2;
                if (mediaController3 == null) {
                    return new zb(false, false);
                }
                xb xbVar2 = xb.c;
                xb xbVar3 = xbVar;
                if (xbVar3 == xbVar2) {
                    z2 = true;
                } else {
                    if (j3.i0(xb.a, xb.b).contains(xbVar3)) {
                        long j2 = actions;
                        if ((j & j2) == 0 && (j2 & 512) != 0) {
                            z2 = true;
                        }
                    }
                    z2 = false;
                }
                int i = 85;
                if (!z2) {
                    int iOrdinal2 = xbVar3.ordinal();
                    if (iOrdinal2 == 0) {
                        i = 126;
                    } else if (iOrdinal2 == 1) {
                        i = 127;
                    } else if (iOrdinal2 != 2) {
                        if (iOrdinal2 == 3) {
                            i = 87;
                        } else {
                            if (iOrdinal2 != 4) {
                                throw new vc();
                            }
                            i = 88;
                        }
                    }
                }
                int i2 = i;
                try {
                    long jUptimeMillis = SystemClock.uptimeMillis();
                    ffVar = Boolean.valueOf(mediaController3.dispatchMediaButtonEvent(new KeyEvent(jUptimeMillis, jUptimeMillis, 0, i2, 0)) || mediaController3.dispatchMediaButtonEvent(new KeyEvent(jUptimeMillis, jUptimeMillis, 1, i2, 0)));
                } catch (Throwable th) {
                    ffVar = new ff(th);
                }
                Object obj = Boolean.FALSE;
                if (ffVar instanceof ff) {
                    ffVar = obj;
                }
                return new zb(((Boolean) ffVar).booleanValue(), z2);
            }
        };
        if (z && ((Boolean) y1Var.a()).booleanValue()) {
            return new zb(true, false);
        }
        Object objA = y1Var2.a();
        if (!((zb) objA).a) {
            objA = null;
        }
        zb zbVar = (zb) objA;
        if (zbVar != null) {
            return zbVar;
        }
        Object objA2 = y5Var.a();
        zb zbVar2 = (zb) (((zb) objA2).a ? objA2 : null);
        if (zbVar2 != null) {
            return zbVar2;
        }
        return new zb(!z && ((Boolean) y1Var.a()).booleanValue(), false);
    }

    public static Set P(Object obj) {
        Set setSingleton = Collections.singleton(obj);
        setSingleton.getClass();
        return setSingleton;
    }

    public static final LinearLayout Q(MainActivity mainActivity, c9 c9Var, mg mgVar) {
        z0 z0Var = new z0(mainActivity);
        LinearLayout linearLayout = new LinearLayout(c9Var.a);
        int i = 1;
        linearLayout.setOrientation(1);
        linearLayout.addView(c9Var.i(si.a("distance_time_consumption_and_voltage_per_trip", new Object[0])));
        linearLayout.addView(c9Var.d(c9Var.r(si.a("record_statistics", new Object[0]), null, z0Var.a.getBoolean("stats_enabled", true), new o0((Object) z0Var, mainActivity, (y5) mgVar, i)), c9.o(c9Var, si.a("open_statistics", new Object[0]), si.a("summary_chart_and_trip_list", new Object[0]), null, new b2(mainActivity, 0), 12), c9.o(c9Var, si.a("clear_statistics", new Object[0]), null, null, new b2(mainActivity, i), 12)));
        return linearLayout;
    }

    public static final void R(MainActivity mainActivity) {
        Window window;
        kh khVar = (kh) mainActivity.R.a();
        c9 c9Var = new c9(mainActivity, mainActivity.z(), mainActivity.w(), new lg(mainActivity, 9));
        Dialog dialog = new Dialog(mainActivity);
        if (c && (window = dialog.getWindow()) != null) {
            window.setType(2038);
        }
        FrameLayout frameLayout = new FrameLayout(mainActivity);
        me meVar = new me(0);
        meVar.b = lh.WEEK;
        T(new je(), meVar, khVar, frameLayout, mainActivity);
        dialog.setContentView(c9Var.t(si.a("trip_statistics", new Object[0]), frameLayout, new w1(dialog, 3)));
        dialog.setCanceledOnTouchOutside(true);
        Window window2 = dialog.getWindow();
        if (window2 != null) {
            window2.setBackgroundDrawable(new ColorDrawable(0));
            window2.setLayout((int) (l8.m(mainActivity) * 880.0f), (int) (mainActivity.getResources().getDisplayMetrics().heightPixels * 0.9f));
            window2.setDimAmount(0.55f);
            window2.setFlags(8, 8);
            window2.getDecorView().setSystemUiVisibility(mainActivity.getWindow().getDecorView().getSystemUiVisibility());
        }
        dialog.show();
        Window window3 = dialog.getWindow();
        if (window3 != null) {
            window3.clearFlags(8);
        }
    }

    public static final LinearLayout S(MainActivity mainActivity, String str, boolean z, View... viewArr) {
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        n8 n8Var = new n8(mainActivity.z(), mainActivity.r(22));
        if (z) {
            n8Var.setColorFilter(Color.argb(70, 48, 176, 199), PorterDuff.Mode.SRC_ATOP);
        }
        linearLayout.setBackground(n8Var);
        linearLayout.setPadding(mainActivity.r(18), mainActivity.r(14), mainActivity.r(18), mainActivity.r(16));
        int i = l8.a;
        TextView textViewU0 = mainActivity.u0(str, 13.0f, l8.k(mainActivity.z()));
        textViewU0.setLetterSpacing(0.04f);
        linearLayout.addView(textViewU0);
        for (View view : viewArr) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.topMargin = mainActivity.r(10);
            linearLayout.addView(view, layoutParams);
        }
        return linearLayout;
    }

    /* JADX WARN: Code duplicated, block: B:128:0x04e5  */
    /* JADX WARN: Code duplicated, block: B:190:0x068e  */
    /* JADX WARN: Code duplicated, block: B:191:0x0694  */
    /* JADX WARN: Code duplicated, block: B:194:0x069e  */
    /* JADX WARN: Code duplicated, block: B:195:0x06a0  */
    /* JADX WARN: Code duplicated, block: B:198:0x06a4  */
    /* JADX WARN: Code duplicated, block: B:201:0x06a8  */
    /* JADX WARN: Code duplicated, block: B:210:0x06d1  */
    /* JADX WARN: Code duplicated, block: B:217:0x0707  */
    /* JADX WARN: Code duplicated, block: B:227:0x0740  */
    /* JADX WARN: Code duplicated, block: B:230:0x0745  */
    /* JADX WARN: Code duplicated, block: B:234:0x075a  */
    /* JADX WARN: Code duplicated, block: B:237:0x0763  */
    /* JADX WARN: Code duplicated, block: B:238:0x0766  */
    /* JADX WARN: Code duplicated, block: B:241:0x0770  */
    /* JADX WARN: Code duplicated, block: B:242:0x0775  */
    /* JADX WARN: Code duplicated, block: B:245:0x079f  */
    /* JADX WARN: Code duplicated, block: B:249:0x07cf  */
    /* JADX WARN: Code duplicated, block: B:251:0x07f2  */
    /* JADX WARN: Code duplicated, block: B:252:0x07fc  */
    /* JADX WARN: Code duplicated, block: B:255:0x0802  */
    /* JADX WARN: Code duplicated, block: B:256:0x080b  */
    /* JADX WARN: Code duplicated, block: B:259:0x084c  */
    /* JADX WARN: Code duplicated, block: B:260:0x084e  */
    /* JADX WARN: Code duplicated, block: B:262:0x0851  */
    /* JADX WARN: Code duplicated, block: B:263:0x086e  */
    /* JADX WARN: Code duplicated, block: B:277:0x0981  */
    /* JADX WARN: Code duplicated, block: B:280:0x098b  */
    /* JADX WARN: Code duplicated, block: B:284:0x09a7  */
    /* JADX WARN: Code duplicated, block: B:285:0x09b1  */
    /* JADX WARN: Code duplicated, block: B:289:0x0a28  */
    /* JADX WARN: Code duplicated, block: B:291:0x0a4b  */
    /* JADX WARN: Code duplicated, block: B:294:0x0a55 A[LOOP:3: B:292:0x0a4f->B:294:0x0a55, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:297:0x0b3b  */
    /* JADX WARN: Code duplicated, block: B:298:0x0b45  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v36 */
    /* JADX WARN: Type inference failed for: r12v37, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r12v38 */
    /* JADX WARN: Type inference failed for: r5v77 */
    /* JADX WARN: Type inference failed for: r5v78, types: [boolean] */
    /* JADX WARN: Type inference failed for: r5v80 */
    public static final void T(final je jeVar, final me meVar, final kh khVar, final FrameLayout frameLayout, MainActivity mainActivity) throws IllegalAccessException, IOException, InvocationTargetException {
        Object ffVar;
        Object ffVar2;
        Object ffVar3;
        boolean z;
        String str;
        long j;
        Object ffVar4;
        String strA;
        String strA2;
        boolean z2;
        Object ffVar5;
        long j2;
        float f;
        float f2;
        Float f3;
        int i;
        float f4;
        float f5;
        Float fValueOf;
        boolean z3;
        Float f6;
        Float fValueOf2;
        String strA3;
        float f7;
        Integer numValueOf;
        String strA4;
        String str2;
        float fFloatValue;
        ArrayList arrayListJ0;
        int i2;
        final MainActivity mainActivity2;
        me meVar2;
        MainActivity mainActivity3;
        Object ffVar6;
        boolean z4;
        Object obj;
        List<ih> list;
        ?? r12;
        String strA5;
        ?? r5;
        String strA6;
        int i3;
        String strA7;
        String strA8;
        String strY0;
        final LinearLayout linearLayout;
        boolean z5;
        Object ffVar7;
        int iJ;
        int iE;
        me meVar3 = meVar;
        kh khVar2 = khVar;
        MainActivity mainActivity4 = mainActivity;
        di diVar = di.f;
        u4 u4Var = u4.a;
        frameLayout.removeAllViews();
        LinearLayout linearLayout2 = new LinearLayout(mainActivity4);
        linearLayout2.setOrientation(1);
        SimpleDateFormat simpleDateFormat = mh.a;
        lh lhVar = (lh) meVar3.b;
        lhVar.getClass();
        long jCurrentTimeMillis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(jCurrentTimeMillis);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        int iOrdinal = lhVar.ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal == 1) {
                calendar.add(6, -6);
            } else if (iOrdinal == 2) {
                calendar.add(6, -29);
            } else {
                if (iOrdinal != 3) {
                    throw new vc();
                }
                calendar.set(5, 1);
                calendar.add(2, -11);
            }
        }
        long timeInMillis = calendar.getTimeInMillis();
        long j3 = jCurrentTimeMillis + 1;
        try {
            ffVar = khVar2.c(timeInMillis, j3);
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        boolean z6 = ffVar instanceof ff;
        Object obj2 = ffVar;
        if (z6) {
            obj2 = diVar;
        }
        di diVar2 = (di) obj2;
        try {
            ffVar2 = kh.f(khVar2, timeInMillis, j3);
        } catch (Throwable th2) {
            ffVar2 = new ff(th2);
        }
        boolean z7 = ffVar2 instanceof ff;
        Object obj3 = ffVar2;
        if (z7) {
            obj3 = u4Var;
        }
        List<li> list2 = (List) obj3;
        li liVar = ni.N;
        li liVar2 = ni.N;
        try {
            ffVar3 = khVar2.h(timeInMillis);
        } catch (Throwable th3) {
            ffVar3 = new ff(th3);
        }
        li liVar3 = liVar2;
        if (ffVar3 instanceof ff) {
            ffVar3 = null;
        }
        yc ycVar = (yc) ffVar3;
        LinearLayout linearLayout3 = new LinearLayout(mainActivity4);
        linearLayout3.setOrientation(0);
        lh[] lhVarArrValues = lh.values();
        int length = lhVarArrValues.length;
        int i4 = 0;
        while (i4 < length) {
            lh lhVar2 = lhVarArrValues[i4];
            boolean z8 = lhVar2 == meVar3.b;
            lhVar2.getClass();
            List list3 = si.a;
            LinearLayout linearLayout4 = linearLayout3;
            String strA9 = si.a(lhVar2.a, new Object[0]);
            if (z8) {
                iJ = -1;
            } else {
                int i5 = l8.a;
                iJ = l8.j(mainActivity4.z());
            }
            TextView textViewU0 = mainActivity4.u0(strA9, 15.0f, iJ);
            textViewU0.setGravity(17);
            di diVar3 = diVar2;
            long j4 = j3;
            textViewU0.setPadding(mainActivity4.r(18), mainActivity4.r(9), mainActivity4.r(18), mainActivity4.r(9));
            if (z8) {
                iE = l8.f();
            } else {
                int i6 = l8.a;
                iE = l8.e(mainActivity4.z());
            }
            textViewU0.setBackground(mainActivity4.m0(iE, 20));
            textViewU0.setClickable(true);
            meVar3 = meVar;
            y yVar = new y(meVar3, lhVar2, frameLayout, mainActivity4, jeVar, khVar, 2);
            khVar2 = khVar;
            textViewU0.setOnClickListener(yVar);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.rightMargin = mainActivity4.r(8);
            linearLayout4.addView(textViewU0, layoutParams);
            i4++;
            linearLayout3 = linearLayout4;
            diVar2 = diVar3;
            u4Var = u4Var;
            length = length;
            j3 = j4;
            liVar3 = liVar3;
            diVar = diVar;
        }
        di diVar4 = diVar2;
        long j5 = j3;
        di diVar5 = diVar;
        li liVar4 = liVar3;
        u4 u4Var2 = u4Var;
        linearLayout2.addView(linearLayout3);
        if (liVar4 != null) {
            String strA10 = si.a("current_trip", new Object[0]);
            String strA11 = si.a("distance", new Object[0]);
            li liVar5 = ni.N;
            View viewS = S(mainActivity4, strA10, true, V(mainActivity4, U(mainActivity4, strA11, sh.c(liVar4.d), false), U(mainActivity4, si.a("driving_time", new Object[0]), sh.b(liVar4.e), false), U(mainActivity4, si.a("consumption", new Object[0]), t(mainActivity4, liVar4.h), false), U(mainActivity4, si.a("maximum", new Object[0]), si.a("0_kmh", Integer.valueOf(liVar4.g)), false)));
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams2.topMargin = mainActivity4.r(12);
            linearLayout2.addView(viewS, layoutParams2);
        }
        List list4 = si.a;
        lh lhVar3 = (lh) meVar3.b;
        lhVar3.getClass();
        String lowerCase = si.a(lhVar3.a, new Object[0]).toLowerCase(si.b.b);
        lowerCase.getClass();
        String strA12 = si.a("period_0", lowerCase);
        String strA13 = si.a("distance", new Object[0]);
        li liVar6 = ni.N;
        LinearLayout linearLayoutU = U(mainActivity4, strA13, sh.c(diVar4.a), false);
        LinearLayout linearLayoutU2 = U(mainActivity4, si.a("trips", new Object[0]), String.valueOf(diVar4.b), false);
        String strA14 = si.a("consumption", new Object[0]);
        float f8 = diVar4.a;
        LinearLayout linearLayoutV = V(mainActivity4, linearLayoutU, linearLayoutU2, U(mainActivity4, strA14, t(mainActivity4, f8 > 0.5f ? (diVar4.e / f8) * 100.0f : 0.0f), false));
        LinearLayout linearLayoutU3 = U(mainActivity4, si.a("engine_hours", new Object[0]), sh.b(diVar4.c + diVar4.d), false);
        LinearLayout linearLayoutU4 = U(mainActivity4, si.a("idling", new Object[0]), sh.b(diVar4.d), false);
        String strA15 = si.a("fuel", new Object[0]);
        if (diVar4.e > 0.05f) {
            z = true;
            str = String.format(si.a("1f_l", new Object[0]), Arrays.copyOf(new Object[]{Float.valueOf(diVar4.e)}, 1));
        } else {
            z = true;
            str = "—";
        }
        LinearLayout linearLayoutU5 = U(mainActivity4, strA15, str, false);
        boolean z9 = z;
        View[] viewArr = new View[3];
        viewArr[0] = linearLayoutU3;
        viewArr[z9 ? 1 : 0] = linearLayoutU4;
        viewArr[2] = linearLayoutU5;
        LinearLayout linearLayoutV2 = V(mainActivity4, viewArr);
        View[] viewArr2 = new View[2];
        viewArr2[0] = linearLayoutV;
        viewArr2[z9 ? 1 : 0] = linearLayoutV2;
        View viewS2 = S(mainActivity4, strA12, false, viewArr2);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams3.topMargin = mainActivity4.r(10);
        linearLayout2.addView(viewS2, layoutParams3);
        if (ycVar != null) {
            float fFloatValue2 = ((Number) ycVar.a).floatValue();
            float fFloatValue3 = ((Number) ycVar.b).floatValue();
            j = j5;
            try {
                ffVar7 = kh.g(khVar2, timeInMillis, j);
            } catch (Throwable th4) {
                ffVar7 = new ff(th4);
            }
            boolean z10 = ffVar7 instanceof ff;
            Object obj4 = ffVar7;
            if (z10) {
                obj4 = u4Var2;
            }
            List list5 = (List) obj4;
            rj rjVar = new rj(mainActivity4, mainActivity4.z());
            rjVar.f = list5;
            rjVar.g = timeInMillis;
            rjVar.h = Math.max(timeInMillis + 1, j);
            rjVar.invalidate();
            View viewS3 = S(mainActivity4, si.a("voltage", new Object[0]), false, V(mainActivity4, U(mainActivity4, si.a("min_386", new Object[0]), String.format("%.1f", Arrays.copyOf(new Object[]{Float.valueOf(fFloatValue2)}, 1)), fFloatValue2 < 11.5f), U(mainActivity4, si.a("max", new Object[0]), String.format("%.1f", Arrays.copyOf(new Object[]{Float.valueOf(fFloatValue3)}, 1)), false)), rjVar);
            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams4.topMargin = mainActivity4.r(10);
            linearLayout2.addView(viewS3, layoutParams4);
            if (list5.size() > 1) {
                LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-1, mainActivity4.r(140));
                layoutParams5.topMargin = mainActivity4.r(10);
                rjVar.setLayoutParams(layoutParams5);
            } else {
                rjVar.setVisibility(8);
            }
        } else {
            j = j5;
        }
        li liVar7 = ni.N;
        p2 p2Var = ni.O;
        try {
            ffVar4 = khVar.e();
        } catch (Throwable th5) {
            ffVar4 = new ff(th5);
        }
        boolean z11 = ffVar4 instanceof ff;
        Object obj5 = ffVar4;
        if (z11) {
            obj5 = diVar5;
        }
        di diVar6 = (di) obj5;
        int i7 = diVar4.c;
        float f9 = i7 >= 60 ? diVar4.a / (i7 / 3600.0f) : 0.0f;
        Integer num = p2Var.b;
        if (num != null && p2Var.c != null) {
            List list6 = si.a;
            strA = si.a("service_0_km_1_d", E(mainActivity4, num.intValue()), p2Var.c);
        } else if (num != null) {
            List list7 = si.a;
            strA = si.a("0_km", E(mainActivity4, num.intValue()));
        } else {
            Integer num2 = p2Var.c;
            strA = num2 != null ? si.a("days_0", num2) : "—";
        }
        String strA16 = si.a("mileage_and_service", new Object[0]);
        float f10 = f9;
        String strA17 = si.a("odometer", new Object[0]);
        Integer num3 = p2Var.a;
        if (num3 == null || (strA2 = si.a("0_km", E(mainActivity4, num3.intValue()))) == null) {
            strA2 = "—";
        }
        LinearLayout linearLayoutU6 = U(mainActivity4, strA17, strA2, false);
        String strA18 = si.a("to_service", new Object[0]);
        Integer num4 = p2Var.b;
        if ((num4 != null ? num4.intValue() : Integer.MAX_VALUE) < 500) {
            z2 = true;
        } else {
            Integer num5 = p2Var.c;
            if ((num5 != null ? num5.intValue() : Integer.MAX_VALUE) < 14) {
                z2 = true;
            } else {
                z2 = false;
            }
        }
        LinearLayout linearLayoutV3 = V(mainActivity4, linearLayoutU6, U(mainActivity4, strA18, strA, z2));
        String strA19 = si.a("engine_hours_total", new Object[0]);
        li liVar8 = ni.N;
        View viewS4 = S(mainActivity4, strA16, false, linearLayoutV3, V(mainActivity4, U(mainActivity4, strA19, sh.b(diVar6.d + diVar6.c), false), U(mainActivity4, si.a("average_speed", new Object[0]), f10 > 0.0f ? si.a("0_kmh", Integer.valueOf(Math.round(f10))) : "—", false)));
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams6.topMargin = mainActivity4.r(10);
        linearLayout2.addView(viewS4, layoutParams6);
        float f11 = 50.0f;
        float f12 = mainActivity4.B().getFloat("tank_liters", 50.0f);
        try {
            ffVar5 = khVar.b();
        } catch (Throwable th6) {
            ffVar5 = new ff(th6);
        }
        boolean z12 = ffVar5 instanceof ff;
        Object obj6 = ffVar5;
        if (z12) {
            obj6 = u4Var2;
        }
        List list8 = (List) obj6;
        List listG0 = i3.G0(list8, new h0(2));
        ArrayList arrayList = new ArrayList();
        for (Object obj7 : listG0) {
            float f13 = f11;
            List list9 = listG0;
            if (((pe) obj7).g) {
                arrayList.add(obj7);
            }
            f11 = f13;
            listG0 = list9;
        }
        List list10 = listG0;
        float f14 = f11;
        try {
            try {
                if (arrayList.size() >= 2) {
                    pe peVar = (pe) arrayList.get(arrayList.size() - 2);
                    pe peVar2 = (pe) i3.z0(arrayList);
                    ArrayList arrayList2 = new ArrayList();
                    for (Object obj8 : list10) {
                        long j6 = j;
                        float f15 = f12;
                        long j7 = ((pe) obj8).b;
                        if (j7 > peVar.b && j7 <= peVar2.b) {
                            arrayList2.add(obj8);
                        }
                        j = j6;
                        f12 = f15;
                    }
                    j2 = j;
                    f = f12;
                    int size = arrayList2.size();
                    double d2 = 0.0d;
                    for (int i8 = 0; i8 < size; i8++) {
                        d2 += (double) ((pe) arrayList2.get(i8)).f;
                        arrayList2 = arrayList2;
                        size = size;
                    }
                    float f16 = (float) d2;
                    int i9 = peVar.c;
                    if (i9 <= 0 || (i = peVar2.c) <= i9) {
                        Cursor cursorRawQuery = khVar.getReadableDatabase().rawQuery("SELECT IFNULL(SUM(distanceKm),0) FROM trips WHERE endedAt > ? AND endedAt <= ?", new String[]{String.valueOf(peVar.b), String.valueOf(peVar2.b)});
                        try {
                            float f17 = cursorRawQuery.moveToFirst() ? cursorRawQuery.getFloat(0) : 0.0f;
                            k8.p(cursorRawQuery, null);
                            f2 = f17;
                        } catch (Throwable th7) {
                            try {
                                throw th7;
                            } catch (Throwable th8) {
                                k8.p(cursorRawQuery, th7);
                                throw th8;
                            }
                        }
                    } else {
                        f2 = i - i9;
                    }
                    if (f2 >= f14 && f16 > 0.0f) {
                        float f18 = (f16 / f2) * 100.0f;
                        Float fValueOf3 = Float.valueOf(f18);
                        if (2.0f <= f18 && f18 <= 40.0f) {
                            f3 = fValueOf3;
                        }
                    }
                    f4 = diVar6.a;
                    if (f4 > 0.5f) {
                        f5 = (diVar6.e / f4) * 100.0f;
                    } else {
                        f5 = 0.0f;
                    }
                    fValueOf = Float.valueOf(f5);
                    if (f5 > 0.5f) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (!z3) {
                        fValueOf = null;
                    }
                    if (f3 != null) {
                        fValueOf = f3;
                    }
                    f6 = p2Var.d;
                    if (f6 != null || Math.abs(f) > Float.MAX_VALUE || f <= 0.0f) {
                        fValueOf2 = null;
                    } else {
                        fValueOf2 = Float.valueOf((r(f6.floatValue(), 0.0f, 100.0f) / 100.0f) * f);
                    }
                    String strA20 = si.a("fuel_left", new Object[0]);
                    if (fValueOf2 != null || p2Var.d == null) {
                        strA3 = "—";
                    } else {
                        strA3 = si.a("fuel_left_0_l_1", Integer.valueOf(Math.round(fValueOf2.floatValue())), Integer.valueOf(Math.round(p2Var.d.floatValue())));
                    }
                    LinearLayout linearLayoutU7 = U(mainActivity4, strA20, strA3, false);
                    String strA21 = si.a("range_actual", new Object[0]);
                    if (fValueOf2 == null && fValueOf != null) {
                        f7 = 0.0f;
                        if (fValueOf.floatValue() > 0.0f && fValueOf2.floatValue() > 0.0f) {
                            numValueOf = Integer.valueOf((int) ((fValueOf2.floatValue() / fValueOf.floatValue()) * 100.0f));
                        }
                        if (numValueOf != null || (strA4 = si.a("0_km", E(mainActivity4, numValueOf.intValue()))) == null) {
                            strA4 = "—";
                        }
                        LinearLayout linearLayoutU8 = U(mainActivity4, strA21, strA4, false);
                        if (f3 != null) {
                            str2 = "consumption_by_refuels";
                        } else {
                            str2 = "consumption_by_trips";
                        }
                        String strA22 = si.a(str2, new Object[0]);
                        if (fValueOf != null) {
                            fFloatValue = fValueOf.floatValue();
                        } else {
                            fFloatValue = f7;
                        }
                        arrayListJ0 = j3.j0(V(mainActivity4, linearLayoutU7, linearLayoutU8, U(mainActivity4, strA22, t(mainActivity4, fFloatValue), false)));
                        if (list8.isEmpty()) {
                            String strA23 = si.a("no_refuels_yet", new Object[0]);
                            int i10 = l8.a;
                            arrayListJ0.add(mainActivity4.u0(strA23, 13.0f, l8.k(mainActivity4.z())));
                        }
                        for (final pe peVar3 : i3.H0(list8, 6)) {
                            List list11 = si.a;
                            long j8 = timeInMillis;
                            final String str3 = new SimpleDateFormat("d MMM, HH:mm", si.b.b).format(new Date(peVar3.b));
                            if (peVar3.g) {
                                i3 = 0;
                                strA7 = si.a("full_tank", new Object[0]);
                            } else {
                                i3 = 0;
                                strA7 = null;
                            }
                            if (peVar3.h) {
                                strA8 = si.a("manual_entry", new Object[i3]);
                            } else {
                                strA8 = null;
                            }
                            strY0 = i3.y0(x0.S0(new String[]{strA7, strA8}), " · ", null, 62);
                            linearLayout = new LinearLayout(mainActivity4);
                            linearLayout.setOrientation(i3);
                            linearLayout.setGravity(16);
                            linearLayout.setClickable(true);
                            str3.getClass();
                            int i11 = l8.a;
                            linearLayout.addView(mainActivity4.u0(str3, 14.0f, l8.k(mainActivity4.z())), new LinearLayout.LayoutParams(i3, -2, 1.0f));
                            if (strY0.length() > 0) {
                                z5 = true;
                            } else {
                                z5 = false;
                            }
                            if (z5) {
                                TextView textViewU1 = mainActivity4.u0(strY0, 13.0f, l8.k(mainActivity4.z()));
                                LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(-2, -2);
                                layoutParams7.rightMargin = mainActivity4.r(12);
                                linearLayout.addView(textViewU1, layoutParams7);
                            }
                            TextView textViewU2 = mainActivity4.u0(si.a("refuel_plus_0_l", String.format("%.1f", Arrays.copyOf(new Object[]{Float.valueOf(peVar3.f)}, 1))), 16.0f, l8.j(mainActivity4.z()));
                            textViewU2.setTypeface(textViewU2.getTypeface(), 1);
                            linearLayout.addView(textViewU2);
                            final MainActivity mainActivity5 = mainActivity4;
                            mainActivity4 = mainActivity5;
                            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: oh
                                @Override // android.view.View.OnClickListener
                                public final void onClick(View view) {
                                    final MainActivity mainActivity6 = mainActivity5;
                                    AlertDialog.Builder title = new AlertDialog.Builder(mainActivity6).setTitle(si.a("delete_refuel", new Object[0]));
                                    final pe peVar4 = peVar3;
                                    AlertDialog.Builder message = title.setMessage(str3 + " · " + si.a("refuel_plus_0_l", String.format("%.1f", Arrays.copyOf(new Object[]{Float.valueOf(peVar4.f)}, 1))));
                                    String strA24 = si.a("delete", new Object[0]);
                                    final LinearLayout linearLayout5 = linearLayout;
                                    final kh khVar3 = khVar;
                                    final FrameLayout frameLayout2 = frameLayout;
                                    final me meVar4 = meVar;
                                    final je jeVar2 = jeVar;
                                    c.e("cancel", new Object[0], message.setPositiveButton(strA24, new DialogInterface.OnClickListener(linearLayout5, khVar3, peVar4, frameLayout2, mainActivity6, meVar4, jeVar2) { // from class: rh
                                        public final /* synthetic */ kh a;
                                        public final /* synthetic */ pe b;
                                        public final /* synthetic */ FrameLayout c;
                                        public final /* synthetic */ MainActivity d;
                                        public final /* synthetic */ me e;
                                        public final /* synthetic */ je f;

                                        {
                                            this.a = khVar3;
                                            this.b = peVar4;
                                            this.c = frameLayout2;
                                            this.d = mainActivity6;
                                            this.e = meVar4;
                                            this.f = jeVar2;
                                        }

                                        @Override // android.content.DialogInterface.OnClickListener
                                        public final void onClick(DialogInterface dialogInterface, int i12) throws IllegalAccessException, IOException, InvocationTargetException {
                                            kh khVar4 = this.a;
                                            try {
                                                khVar4.getWritableDatabase().delete("refuels", "id = ?", new String[]{String.valueOf(this.b.a)});
                                            } catch (Throwable unused) {
                                            }
                                            pi.T(this.f, this.e, khVar4, this.c, this.d);
                                        }
                                    }), null, mainActivity6);
                                }
                            });
                            arrayListJ0.add(linearLayout);
                            timeInMillis = j8;
                        }
                        long j9 = timeInMillis;
                        TextView textViewU3 = mainActivity4.u0(si.a("add_refuel", new Object[0]), 15.0f, l8.f());
                        textViewU3.setClickable(true);
                        textViewU3.setPadding(0, mainActivity4.r(4), 0, mainActivity4.r(2));
                        i2 = 16;
                        mainActivity2 = mainActivity4;
                        final float f19 = f;
                        textViewU3.setOnClickListener(new View.OnClickListener() { // from class: ph
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                je jeVar2 = jeVar;
                                me meVar4 = meVar;
                                kh khVar3 = khVar;
                                FrameLayout frameLayout2 = frameLayout;
                                MainActivity mainActivity6 = mainActivity2;
                                b8 b8Var = new b8(jeVar2, meVar4, khVar3, frameLayout2, mainActivity6);
                                mainActivity6.getClass();
                                khVar3.getClass();
                                int iS = pi.s((int) f19, 20, 150);
                                pi.K(mainActivity6, si.a("refuel_liters", new Object[0]), Math.min(40, iS), 1, iS, 1, "", new fg(3), new o0(mainActivity6, b8Var, khVar3, 6));
                            }
                        });
                        arrayListJ0.add(textViewU3);
                        String strA24 = si.a("fuel_and_refuels", new Object[0]);
                        View[] viewArr3 = (View[]) arrayListJ0.toArray(new View[0]);
                        View viewS5 = S(mainActivity2, strA24, false, (View[]) Arrays.copyOf(viewArr3, viewArr3.length));
                        LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(-1, -2);
                        layoutParams8.topMargin = mainActivity2.r(10);
                        linearLayout2.addView(viewS5, layoutParams8);
                        TextView textViewU4 = mainActivity2.u0(si.a("refuels_note", new Object[0]), 12.0f, l8.k(mainActivity2.z()));
                        textViewU4.setPadding(mainActivity2.r(4), mainActivity2.r(6), mainActivity2.r(4), 0);
                        linearLayout2.addView(textViewU4);
                        SimpleDateFormat simpleDateFormat2 = mh.a;
                        meVar2 = meVar;
                        mainActivity3 = mainActivity2;
                        ffVar6 = mh.a(khVar, (lh) meVar.b, j9, j2);
                        z4 = ffVar6 instanceof ff;
                        obj = ffVar6;
                        if (z4) {
                            obj = u4Var2;
                        }
                        list = (List) obj;
                        if (!list.isEmpty() && meVar2.b != lh.DAY) {
                            jh jhVar = new jh(mainActivity3, mainActivity3.z());
                            jhVar.set(list);
                            List list12 = si.a;
                            if (((lh) meVar2.b).b) {
                                r5 = 0;
                                strA6 = si.a("month", new Object[0]);
                            } else {
                                r5 = 0;
                                strA6 = si.a("day_390", new Object[0]);
                            }
                            String strA25 = si.a("distance_by_0", strA6);
                            View[] viewArr4 = new View[1];
                            viewArr4[r5] = jhVar;
                            View viewS6 = S(mainActivity3, strA25, r5, viewArr4);
                            LinearLayout.LayoutParams layoutParams9 = new LinearLayout.LayoutParams(-1, -2);
                            layoutParams9.topMargin = mainActivity3.r(10);
                            linearLayout2.addView(viewS6, layoutParams9);
                            jhVar.setLayoutParams(new LinearLayout.LayoutParams(-1, mainActivity3.r(120)));
                        }
                        String strA26 = si.a("trips_391", new Object[0]);
                        int i12 = l8.a;
                        TextView textViewU5 = mainActivity3.u0(strA26, 15.0f, l8.j(mainActivity3.z()));
                        textViewU5.setPadding(mainActivity3.r(4), mainActivity3.r(16), 0, mainActivity3.r(6));
                        textViewU5.setTypeface(textViewU5.getTypeface(), 1);
                        linearLayout2.addView(textViewU5);
                        if (list2.isEmpty()) {
                            TextView textViewU6 = mainActivity3.u0(si.a("no_trips_in_this_period", new Object[0]), 13.0f, l8.k(mainActivity3.z()));
                            textViewU6.setPadding(mainActivity3.r(4), 0, 0, 0);
                            linearLayout2.addView(textViewU6);
                        } else {
                            for (li liVar9 : list2) {
                                List list13 = si.a;
                                String str4 = new SimpleDateFormat("d MMM, HH:mm", si.b.b).format(new Date(liVar9.b));
                                LinearLayout linearLayout5 = new LinearLayout(mainActivity3);
                                linearLayout5.setOrientation(0);
                                linearLayout5.setGravity(i2);
                                linearLayout5.setBackground(new n8(mainActivity3.z(), mainActivity3.r(i2)));
                                linearLayout5.setPadding(mainActivity3.r(14), mainActivity3.r(10), mainActivity3.r(14), mainActivity3.r(10));
                                LinearLayout linearLayout6 = new LinearLayout(mainActivity3);
                                linearLayout6.setOrientation(1);
                                li liVar10 = ni.N;
                                String strC = sh.c(liVar9.d);
                                int i13 = l8.a;
                                TextView textViewU7 = mainActivity3.u0(strC, 17.0f, l8.j(mainActivity3.z()));
                                textViewU7.setTypeface(textViewU7.getTypeface(), 1);
                                linearLayout6.addView(textViewU7);
                                str4.getClass();
                                linearLayout6.addView(mainActivity3.u0(str4, 12.0f, l8.k(mainActivity3.z())));
                                linearLayout5.addView(linearLayout6, new LinearLayout.LayoutParams(0, -2, 1.0f));
                                TextView textViewU8 = mainActivity3.u0(sh.b(liVar9.e), 14.0f, l8.k(mainActivity3.z()));
                                LinearLayout.LayoutParams layoutParams10 = new LinearLayout.LayoutParams(-2, -2);
                                layoutParams10.rightMargin = mainActivity3.r(14);
                                linearLayout5.addView(textViewU8, layoutParams10);
                                linearLayout5.addView(mainActivity3.u0(t(mainActivity3, liVar9.h), 14.0f, l8.k(mainActivity3.z())));
                                LinearLayout.LayoutParams layoutParams11 = new LinearLayout.LayoutParams(-1, -2);
                                layoutParams11.topMargin = mainActivity3.r(6);
                                linearLayout2.addView(linearLayout5, layoutParams11);
                                i2 = 16;
                            }
                        }
                        if (jeVar.a) {
                            r12 = 0;
                            strA5 = si.a("delete_all_statistics", new Object[0]);
                        } else {
                            r12 = 0;
                            strA5 = si.a("clear_statistics", new Object[0]);
                        }
                        TextView textViewU9 = mainActivity3.u0(strA5, 15.0f, Color.rgb(255, 69, 58));
                        textViewU9.setGravity(17);
                        textViewU9.setPadding(r12, mainActivity3.r(18), r12, mainActivity3.r(8));
                        textViewU9.setClickable(true);
                        final me meVar4 = meVar2;
                        final MainActivity mainActivity6 = mainActivity3;
                        textViewU9.setOnClickListener(new View.OnClickListener() { // from class: qh
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) throws IllegalAccessException, IOException, InvocationTargetException {
                                boolean z13;
                                je jeVar2 = jeVar;
                                boolean z14 = jeVar2.a;
                                kh khVar3 = khVar;
                                if (z14) {
                                    SQLiteDatabase writableDatabase = khVar3.getWritableDatabase();
                                    writableDatabase.delete("trips", null, null);
                                    writableDatabase.delete("days", null, null);
                                    writableDatabase.delete("voltage", null, null);
                                    writableDatabase.delete("refuels", null, null);
                                    z13 = false;
                                } else {
                                    z13 = true;
                                }
                                jeVar2.a = z13;
                                pi.T(jeVar2, meVar4, khVar3, frameLayout, mainActivity6);
                            }
                        });
                        linearLayout2.addView(textViewU9);
                        ScrollView scrollView = new ScrollView(mainActivity6);
                        scrollView.setVerticalScrollBarEnabled(r12);
                        scrollView.addView(linearLayout2);
                        frameLayout.addView(scrollView, new FrameLayout.LayoutParams(-1, -1));
                    }
                    f7 = 0.0f;
                    numValueOf = null;
                    if (numValueOf != null) {
                        strA4 = "—";
                    } else {
                        strA4 = "—";
                    }
                    LinearLayout linearLayoutU9 = U(mainActivity4, strA21, strA4, false);
                    if (f3 != null) {
                        str2 = "consumption_by_refuels";
                    } else {
                        str2 = "consumption_by_trips";
                    }
                    String strA27 = si.a(str2, new Object[0]);
                    if (fValueOf != null) {
                        fFloatValue = fValueOf.floatValue();
                    } else {
                        fFloatValue = f7;
                    }
                    arrayListJ0 = j3.j0(V(mainActivity4, linearLayoutU7, linearLayoutU9, U(mainActivity4, strA27, t(mainActivity4, fFloatValue), false)));
                    if (list8.isEmpty()) {
                        String strA28 = si.a("no_refuels_yet", new Object[0]);
                        int i14 = l8.a;
                        arrayListJ0.add(mainActivity4.u0(strA28, 13.0f, l8.k(mainActivity4.z())));
                    }
                    while (r16.hasNext()) {
                        List list14 = si.a;
                        long j10 = timeInMillis;
                        final String str5 = new SimpleDateFormat("d MMM, HH:mm", si.b.b).format(new Date(peVar3.b));
                        if (peVar3.g) {
                            i3 = 0;
                            strA7 = si.a("full_tank", new Object[0]);
                        } else {
                            i3 = 0;
                            strA7 = null;
                        }
                        if (peVar3.h) {
                            strA8 = si.a("manual_entry", new Object[i3]);
                        } else {
                            strA8 = null;
                        }
                        strY0 = i3.y0(x0.S0(new String[]{strA7, strA8}), " · ", null, 62);
                        linearLayout = new LinearLayout(mainActivity4);
                        linearLayout.setOrientation(i3);
                        linearLayout.setGravity(16);
                        linearLayout.setClickable(true);
                        str5.getClass();
                        int i15 = l8.a;
                        linearLayout.addView(mainActivity4.u0(str5, 14.0f, l8.k(mainActivity4.z())), new LinearLayout.LayoutParams(i3, -2, 1.0f));
                        if (strY0.length() > 0) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        if (z5) {
                            TextView textViewU10 = mainActivity4.u0(strY0, 13.0f, l8.k(mainActivity4.z()));
                            LinearLayout.LayoutParams layoutParams12 = new LinearLayout.LayoutParams(-2, -2);
                            layoutParams12.rightMargin = mainActivity4.r(12);
                            linearLayout.addView(textViewU10, layoutParams12);
                        }
                        TextView textViewU11 = mainActivity4.u0(si.a("refuel_plus_0_l", String.format("%.1f", Arrays.copyOf(new Object[]{Float.valueOf(peVar3.f)}, 1))), 16.0f, l8.j(mainActivity4.z()));
                        textViewU11.setTypeface(textViewU11.getTypeface(), 1);
                        linearLayout.addView(textViewU11);
                        final MainActivity mainActivity7 = mainActivity4;
                        mainActivity4 = mainActivity7;
                        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: oh
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                final MainActivity mainActivity8 = mainActivity7;
                                AlertDialog.Builder title = new AlertDialog.Builder(mainActivity8).setTitle(si.a("delete_refuel", new Object[0]));
                                final pe peVar4 = peVar3;
                                AlertDialog.Builder message = title.setMessage(str5 + " · " + si.a("refuel_plus_0_l", String.format("%.1f", Arrays.copyOf(new Object[]{Float.valueOf(peVar4.f)}, 1))));
                                String strA29 = si.a("delete", new Object[0]);
                                final LinearLayout linearLayout7 = linearLayout;
                                final kh khVar3 = khVar;
                                final FrameLayout frameLayout2 = frameLayout;
                                final me meVar5 = meVar;
                                final je jeVar2 = jeVar;
                                c.e("cancel", new Object[0], message.setPositiveButton(strA29, new DialogInterface.OnClickListener(linearLayout7, khVar3, peVar4, frameLayout2, mainActivity8, meVar5, jeVar2) { // from class: rh
                                    public final /* synthetic */ kh a;
                                    public final /* synthetic */ pe b;
                                    public final /* synthetic */ FrameLayout c;
                                    public final /* synthetic */ MainActivity d;
                                    public final /* synthetic */ me e;
                                    public final /* synthetic */ je f;

                                    {
                                        this.a = khVar3;
                                        this.b = peVar4;
                                        this.c = frameLayout2;
                                        this.d = mainActivity8;
                                        this.e = meVar5;
                                        this.f = jeVar2;
                                    }

                                    @Override // android.content.DialogInterface.OnClickListener
                                    public final void onClick(DialogInterface dialogInterface, int i16) throws IllegalAccessException, IOException, InvocationTargetException {
                                        kh khVar4 = this.a;
                                        try {
                                            khVar4.getWritableDatabase().delete("refuels", "id = ?", new String[]{String.valueOf(this.b.a)});
                                        } catch (Throwable unused) {
                                        }
                                        pi.T(this.f, this.e, khVar4, this.c, this.d);
                                    }
                                }), null, mainActivity8);
                            }
                        });
                        arrayListJ0.add(linearLayout);
                        timeInMillis = j10;
                    }
                    long j11 = timeInMillis;
                    TextView textViewU12 = mainActivity4.u0(si.a("add_refuel", new Object[0]), 15.0f, l8.f());
                    textViewU12.setClickable(true);
                    textViewU12.setPadding(0, mainActivity4.r(4), 0, mainActivity4.r(2));
                    i2 = 16;
                    mainActivity2 = mainActivity4;
                    final float f110 = f;
                    textViewU12.setOnClickListener(new View.OnClickListener() { // from class: ph
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            je jeVar2 = jeVar;
                            me meVar5 = meVar;
                            kh khVar3 = khVar;
                            FrameLayout frameLayout2 = frameLayout;
                            MainActivity mainActivity8 = mainActivity2;
                            b8 b8Var = new b8(jeVar2, meVar5, khVar3, frameLayout2, mainActivity8);
                            mainActivity8.getClass();
                            khVar3.getClass();
                            int iS = pi.s((int) f110, 20, 150);
                            pi.K(mainActivity8, si.a("refuel_liters", new Object[0]), Math.min(40, iS), 1, iS, 1, "", new fg(3), new o0(mainActivity8, b8Var, khVar3, 6));
                        }
                    });
                    arrayListJ0.add(textViewU12);
                    String strA29 = si.a("fuel_and_refuels", new Object[0]);
                    View[] viewArr5 = (View[]) arrayListJ0.toArray(new View[0]);
                    View viewS7 = S(mainActivity2, strA29, false, (View[]) Arrays.copyOf(viewArr5, viewArr5.length));
                    LinearLayout.LayoutParams layoutParams13 = new LinearLayout.LayoutParams(-1, -2);
                    layoutParams13.topMargin = mainActivity2.r(10);
                    linearLayout2.addView(viewS7, layoutParams13);
                    TextView textViewU13 = mainActivity2.u0(si.a("refuels_note", new Object[0]), 12.0f, l8.k(mainActivity2.z()));
                    textViewU13.setPadding(mainActivity2.r(4), mainActivity2.r(6), mainActivity2.r(4), 0);
                    linearLayout2.addView(textViewU13);
                    SimpleDateFormat simpleDateFormat3 = mh.a;
                    meVar2 = meVar;
                    mainActivity3 = mainActivity2;
                    ffVar6 = mh.a(khVar, (lh) meVar.b, j11, j2);
                    z4 = ffVar6 instanceof ff;
                    obj = ffVar6;
                    if (z4) {
                        obj = u4Var2;
                    }
                    list = (List) obj;
                    if (!list.isEmpty()) {
                        jh jhVar2 = new jh(mainActivity3, mainActivity3.z());
                        jhVar2.set(list);
                        List list15 = si.a;
                        if (((lh) meVar2.b).b) {
                            r5 = 0;
                            strA6 = si.a("month", new Object[0]);
                        } else {
                            r5 = 0;
                            strA6 = si.a("day_390", new Object[0]);
                        }
                        String strA210 = si.a("distance_by_0", strA6);
                        View[] viewArr6 = new View[1];
                        viewArr6[r5] = jhVar2;
                        View viewS8 = S(mainActivity3, strA210, r5, viewArr6);
                        LinearLayout.LayoutParams layoutParams14 = new LinearLayout.LayoutParams(-1, -2);
                        layoutParams14.topMargin = mainActivity3.r(10);
                        linearLayout2.addView(viewS8, layoutParams14);
                        jhVar2.setLayoutParams(new LinearLayout.LayoutParams(-1, mainActivity3.r(120)));
                    }
                    String strA211 = si.a("trips_391", new Object[0]);
                    int i16 = l8.a;
                    TextView textViewU14 = mainActivity3.u0(strA211, 15.0f, l8.j(mainActivity3.z()));
                    textViewU14.setPadding(mainActivity3.r(4), mainActivity3.r(16), 0, mainActivity3.r(6));
                    textViewU14.setTypeface(textViewU14.getTypeface(), 1);
                    linearLayout2.addView(textViewU14);
                    if (list2.isEmpty()) {
                        TextView textViewU15 = mainActivity3.u0(si.a("no_trips_in_this_period", new Object[0]), 13.0f, l8.k(mainActivity3.z()));
                        textViewU15.setPadding(mainActivity3.r(4), 0, 0, 0);
                        linearLayout2.addView(textViewU15);
                    } else {
                        while (r0.hasNext()) {
                            List list16 = si.a;
                            String str6 = new SimpleDateFormat("d MMM, HH:mm", si.b.b).format(new Date(liVar9.b));
                            LinearLayout linearLayout7 = new LinearLayout(mainActivity3);
                            linearLayout7.setOrientation(0);
                            linearLayout7.setGravity(i2);
                            linearLayout7.setBackground(new n8(mainActivity3.z(), mainActivity3.r(i2)));
                            linearLayout7.setPadding(mainActivity3.r(14), mainActivity3.r(10), mainActivity3.r(14), mainActivity3.r(10));
                            LinearLayout linearLayout8 = new LinearLayout(mainActivity3);
                            linearLayout8.setOrientation(1);
                            li liVar11 = ni.N;
                            String strC2 = sh.c(liVar9.d);
                            int i17 = l8.a;
                            TextView textViewU16 = mainActivity3.u0(strC2, 17.0f, l8.j(mainActivity3.z()));
                            textViewU16.setTypeface(textViewU16.getTypeface(), 1);
                            linearLayout8.addView(textViewU16);
                            str6.getClass();
                            linearLayout8.addView(mainActivity3.u0(str6, 12.0f, l8.k(mainActivity3.z())));
                            linearLayout7.addView(linearLayout8, new LinearLayout.LayoutParams(0, -2, 1.0f));
                            TextView textViewU17 = mainActivity3.u0(sh.b(liVar9.e), 14.0f, l8.k(mainActivity3.z()));
                            LinearLayout.LayoutParams layoutParams15 = new LinearLayout.LayoutParams(-2, -2);
                            layoutParams15.rightMargin = mainActivity3.r(14);
                            linearLayout7.addView(textViewU17, layoutParams15);
                            linearLayout7.addView(mainActivity3.u0(t(mainActivity3, liVar9.h), 14.0f, l8.k(mainActivity3.z())));
                            LinearLayout.LayoutParams layoutParams16 = new LinearLayout.LayoutParams(-1, -2);
                            layoutParams16.topMargin = mainActivity3.r(6);
                            linearLayout2.addView(linearLayout7, layoutParams16);
                            i2 = 16;
                        }
                    }
                    if (jeVar.a) {
                        r12 = 0;
                        strA5 = si.a("delete_all_statistics", new Object[0]);
                    } else {
                        r12 = 0;
                        strA5 = si.a("clear_statistics", new Object[0]);
                    }
                    TextView textViewU18 = mainActivity3.u0(strA5, 15.0f, Color.rgb(255, 69, 58));
                    textViewU18.setGravity(17);
                    textViewU18.setPadding(r12, mainActivity3.r(18), r12, mainActivity3.r(8));
                    textViewU18.setClickable(true);
                    final me meVar5 = meVar2;
                    final MainActivity mainActivity8 = mainActivity3;
                    textViewU18.setOnClickListener(new View.OnClickListener() { // from class: qh
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) throws IllegalAccessException, IOException, InvocationTargetException {
                            boolean z13;
                            je jeVar2 = jeVar;
                            boolean z14 = jeVar2.a;
                            kh khVar3 = khVar;
                            if (z14) {
                                SQLiteDatabase writableDatabase = khVar3.getWritableDatabase();
                                writableDatabase.delete("trips", null, null);
                                writableDatabase.delete("days", null, null);
                                writableDatabase.delete("voltage", null, null);
                                writableDatabase.delete("refuels", null, null);
                                z13 = false;
                            } else {
                                z13 = true;
                            }
                            jeVar2.a = z13;
                            pi.T(jeVar2, meVar5, khVar3, frameLayout, mainActivity8);
                        }
                    });
                    linearLayout2.addView(textViewU18);
                    ScrollView scrollView2 = new ScrollView(mainActivity8);
                    scrollView2.setVerticalScrollBarEnabled(r12);
                    scrollView2.addView(linearLayout2);
                    frameLayout.addView(scrollView2, new FrameLayout.LayoutParams(-1, -1));
                }
                j2 = j;
                f = f12;
                ffVar6 = mh.a(khVar, (lh) meVar.b, j11, j2);
            } catch (Throwable th9) {
                th = th9;
                ffVar6 = new ff(th);
            }
            SimpleDateFormat simpleDateFormat4 = mh.a;
            meVar2 = meVar;
            mainActivity3 = mainActivity2;
        } catch (Throwable th10) {
            th = th10;
            meVar2 = meVar;
            mainActivity3 = mainActivity2;
        }
        f3 = null;
        f4 = diVar6.a;
        if (f4 > 0.5f) {
            f5 = (diVar6.e / f4) * 100.0f;
        } else {
            f5 = 0.0f;
        }
        fValueOf = Float.valueOf(f5);
        if (f5 > 0.5f) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (!z3) {
            fValueOf = null;
        }
        if (f3 != null) {
            fValueOf = f3;
        }
        f6 = p2Var.d;
        if (f6 != null) {
            fValueOf2 = null;
        } else {
            fValueOf2 = null;
        }
        String strA212 = si.a("fuel_left", new Object[0]);
        if (fValueOf2 != null) {
            strA3 = "—";
        } else {
            strA3 = "—";
        }
        LinearLayout linearLayoutU10 = U(mainActivity4, strA212, strA3, false);
        String strA213 = si.a("range_actual", new Object[0]);
        if (fValueOf2 == null) {
            f7 = 0.0f;
            numValueOf = null;
        } else {
            f7 = 0.0f;
            numValueOf = null;
        }
        if (numValueOf != null) {
            strA4 = "—";
        } else {
            strA4 = "—";
        }
        LinearLayout linearLayoutU11 = U(mainActivity4, strA213, strA4, false);
        if (f3 != null) {
            str2 = "consumption_by_refuels";
        } else {
            str2 = "consumption_by_trips";
        }
        String strA214 = si.a(str2, new Object[0]);
        if (fValueOf != null) {
            fFloatValue = fValueOf.floatValue();
        } else {
            fFloatValue = f7;
        }
        arrayListJ0 = j3.j0(V(mainActivity4, linearLayoutU10, linearLayoutU11, U(mainActivity4, strA214, t(mainActivity4, fFloatValue), false)));
        if (list8.isEmpty()) {
            String strA215 = si.a("no_refuels_yet", new Object[0]);
            int i18 = l8.a;
            arrayListJ0.add(mainActivity4.u0(strA215, 13.0f, l8.k(mainActivity4.z())));
        }
        while (r16.hasNext()) {
            List list17 = si.a;
            long j12 = timeInMillis;
            final String str7 = new SimpleDateFormat("d MMM, HH:mm", si.b.b).format(new Date(peVar3.b));
            if (peVar3.g) {
                i3 = 0;
                strA7 = si.a("full_tank", new Object[0]);
            } else {
                i3 = 0;
                strA7 = null;
            }
            if (peVar3.h) {
                strA8 = si.a("manual_entry", new Object[i3]);
            } else {
                strA8 = null;
            }
            strY0 = i3.y0(x0.S0(new String[]{strA7, strA8}), " · ", null, 62);
            linearLayout = new LinearLayout(mainActivity4);
            linearLayout.setOrientation(i3);
            linearLayout.setGravity(16);
            linearLayout.setClickable(true);
            str7.getClass();
            int i19 = l8.a;
            linearLayout.addView(mainActivity4.u0(str7, 14.0f, l8.k(mainActivity4.z())), new LinearLayout.LayoutParams(i3, -2, 1.0f));
            if (strY0.length() > 0) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (z5) {
                TextView textViewU19 = mainActivity4.u0(strY0, 13.0f, l8.k(mainActivity4.z()));
                LinearLayout.LayoutParams layoutParams17 = new LinearLayout.LayoutParams(-2, -2);
                layoutParams17.rightMargin = mainActivity4.r(12);
                linearLayout.addView(textViewU19, layoutParams17);
            }
            TextView textViewU110 = mainActivity4.u0(si.a("refuel_plus_0_l", String.format("%.1f", Arrays.copyOf(new Object[]{Float.valueOf(peVar3.f)}, 1))), 16.0f, l8.j(mainActivity4.z()));
            textViewU110.setTypeface(textViewU110.getTypeface(), 1);
            linearLayout.addView(textViewU110);
            final MainActivity mainActivity9 = mainActivity4;
            mainActivity4 = mainActivity9;
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: oh
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    final MainActivity mainActivity10 = mainActivity9;
                    AlertDialog.Builder title = new AlertDialog.Builder(mainActivity10).setTitle(si.a("delete_refuel", new Object[0]));
                    final pe peVar4 = peVar3;
                    AlertDialog.Builder message = title.setMessage(str7 + " · " + si.a("refuel_plus_0_l", String.format("%.1f", Arrays.copyOf(new Object[]{Float.valueOf(peVar4.f)}, 1))));
                    String strA216 = si.a("delete", new Object[0]);
                    final LinearLayout linearLayout9 = linearLayout;
                    final kh khVar3 = khVar;
                    final FrameLayout frameLayout2 = frameLayout;
                    final me meVar6 = meVar;
                    final je jeVar2 = jeVar;
                    c.e("cancel", new Object[0], message.setPositiveButton(strA216, new DialogInterface.OnClickListener(linearLayout9, khVar3, peVar4, frameLayout2, mainActivity10, meVar6, jeVar2) { // from class: rh
                        public final /* synthetic */ kh a;
                        public final /* synthetic */ pe b;
                        public final /* synthetic */ FrameLayout c;
                        public final /* synthetic */ MainActivity d;
                        public final /* synthetic */ me e;
                        public final /* synthetic */ je f;

                        {
                            this.a = khVar3;
                            this.b = peVar4;
                            this.c = frameLayout2;
                            this.d = mainActivity10;
                            this.e = meVar6;
                            this.f = jeVar2;
                        }

                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i110) throws IllegalAccessException, IOException, InvocationTargetException {
                            kh khVar4 = this.a;
                            try {
                                khVar4.getWritableDatabase().delete("refuels", "id = ?", new String[]{String.valueOf(this.b.a)});
                            } catch (Throwable unused) {
                            }
                            pi.T(this.f, this.e, khVar4, this.c, this.d);
                        }
                    }), null, mainActivity10);
                }
            });
            arrayListJ0.add(linearLayout);
            timeInMillis = j12;
        }
        long j13 = timeInMillis;
        TextView textViewU111 = mainActivity4.u0(si.a("add_refuel", new Object[0]), 15.0f, l8.f());
        textViewU111.setClickable(true);
        textViewU111.setPadding(0, mainActivity4.r(4), 0, mainActivity4.r(2));
        i2 = 16;
        mainActivity2 = mainActivity4;
        final float f111 = f;
        textViewU111.setOnClickListener(new View.OnClickListener() { // from class: ph
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                je jeVar2 = jeVar;
                me meVar6 = meVar;
                kh khVar3 = khVar;
                FrameLayout frameLayout2 = frameLayout;
                MainActivity mainActivity10 = mainActivity2;
                b8 b8Var = new b8(jeVar2, meVar6, khVar3, frameLayout2, mainActivity10);
                mainActivity10.getClass();
                khVar3.getClass();
                int iS = pi.s((int) f111, 20, 150);
                pi.K(mainActivity10, si.a("refuel_liters", new Object[0]), Math.min(40, iS), 1, iS, 1, "", new fg(3), new o0(mainActivity10, b8Var, khVar3, 6));
            }
        });
        arrayListJ0.add(textViewU111);
        String strA216 = si.a("fuel_and_refuels", new Object[0]);
        View[] viewArr7 = (View[]) arrayListJ0.toArray(new View[0]);
        View viewS9 = S(mainActivity2, strA216, false, (View[]) Arrays.copyOf(viewArr7, viewArr7.length));
        LinearLayout.LayoutParams layoutParams18 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams18.topMargin = mainActivity2.r(10);
        linearLayout2.addView(viewS9, layoutParams18);
        TextView textViewU112 = mainActivity2.u0(si.a("refuels_note", new Object[0]), 12.0f, l8.k(mainActivity2.z()));
        textViewU112.setPadding(mainActivity2.r(4), mainActivity2.r(6), mainActivity2.r(4), 0);
        linearLayout2.addView(textViewU112);
        z4 = ffVar6 instanceof ff;
        obj = ffVar6;
        if (z4) {
            obj = u4Var2;
        }
        list = (List) obj;
        if (!list.isEmpty()) {
            jh jhVar3 = new jh(mainActivity3, mainActivity3.z());
            jhVar3.set(list);
            List list18 = si.a;
            if (((lh) meVar2.b).b) {
                r5 = 0;
                strA6 = si.a("month", new Object[0]);
            } else {
                r5 = 0;
                strA6 = si.a("day_390", new Object[0]);
            }
            String strA217 = si.a("distance_by_0", strA6);
            View[] viewArr8 = new View[1];
            viewArr8[r5] = jhVar3;
            View viewS10 = S(mainActivity3, strA217, r5, viewArr8);
            LinearLayout.LayoutParams layoutParams19 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams19.topMargin = mainActivity3.r(10);
            linearLayout2.addView(viewS10, layoutParams19);
            jhVar3.setLayoutParams(new LinearLayout.LayoutParams(-1, mainActivity3.r(120)));
        }
        String strA218 = si.a("trips_391", new Object[0]);
        int i110 = l8.a;
        TextView textViewU113 = mainActivity3.u0(strA218, 15.0f, l8.j(mainActivity3.z()));
        textViewU113.setPadding(mainActivity3.r(4), mainActivity3.r(16), 0, mainActivity3.r(6));
        textViewU113.setTypeface(textViewU113.getTypeface(), 1);
        linearLayout2.addView(textViewU113);
        if (list2.isEmpty()) {
            TextView textViewU114 = mainActivity3.u0(si.a("no_trips_in_this_period", new Object[0]), 13.0f, l8.k(mainActivity3.z()));
            textViewU114.setPadding(mainActivity3.r(4), 0, 0, 0);
            linearLayout2.addView(textViewU114);
        } else {
            while (r0.hasNext()) {
                List list19 = si.a;
                String str8 = new SimpleDateFormat("d MMM, HH:mm", si.b.b).format(new Date(liVar9.b));
                LinearLayout linearLayout9 = new LinearLayout(mainActivity3);
                linearLayout9.setOrientation(0);
                linearLayout9.setGravity(i2);
                linearLayout9.setBackground(new n8(mainActivity3.z(), mainActivity3.r(i2)));
                linearLayout9.setPadding(mainActivity3.r(14), mainActivity3.r(10), mainActivity3.r(14), mainActivity3.r(10));
                LinearLayout linearLayout10 = new LinearLayout(mainActivity3);
                linearLayout10.setOrientation(1);
                li liVar12 = ni.N;
                String strC3 = sh.c(liVar9.d);
                int i111 = l8.a;
                TextView textViewU115 = mainActivity3.u0(strC3, 17.0f, l8.j(mainActivity3.z()));
                textViewU115.setTypeface(textViewU115.getTypeface(), 1);
                linearLayout10.addView(textViewU115);
                str8.getClass();
                linearLayout10.addView(mainActivity3.u0(str8, 12.0f, l8.k(mainActivity3.z())));
                linearLayout9.addView(linearLayout10, new LinearLayout.LayoutParams(0, -2, 1.0f));
                TextView textViewU116 = mainActivity3.u0(sh.b(liVar9.e), 14.0f, l8.k(mainActivity3.z()));
                LinearLayout.LayoutParams layoutParams110 = new LinearLayout.LayoutParams(-2, -2);
                layoutParams110.rightMargin = mainActivity3.r(14);
                linearLayout9.addView(textViewU116, layoutParams110);
                linearLayout9.addView(mainActivity3.u0(t(mainActivity3, liVar9.h), 14.0f, l8.k(mainActivity3.z())));
                LinearLayout.LayoutParams layoutParams111 = new LinearLayout.LayoutParams(-1, -2);
                layoutParams111.topMargin = mainActivity3.r(6);
                linearLayout2.addView(linearLayout9, layoutParams111);
                i2 = 16;
            }
        }
        if (jeVar.a) {
            r12 = 0;
            strA5 = si.a("delete_all_statistics", new Object[0]);
        } else {
            r12 = 0;
            strA5 = si.a("clear_statistics", new Object[0]);
        }
        TextView textViewU117 = mainActivity3.u0(strA5, 15.0f, Color.rgb(255, 69, 58));
        textViewU117.setGravity(17);
        textViewU117.setPadding(r12, mainActivity3.r(18), r12, mainActivity3.r(8));
        textViewU117.setClickable(true);
        final me meVar6 = meVar2;
        final MainActivity mainActivity10 = mainActivity3;
        textViewU117.setOnClickListener(new View.OnClickListener() { // from class: qh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IllegalAccessException, IOException, InvocationTargetException {
                boolean z13;
                je jeVar2 = jeVar;
                boolean z14 = jeVar2.a;
                kh khVar3 = khVar;
                if (z14) {
                    SQLiteDatabase writableDatabase = khVar3.getWritableDatabase();
                    writableDatabase.delete("trips", null, null);
                    writableDatabase.delete("days", null, null);
                    writableDatabase.delete("voltage", null, null);
                    writableDatabase.delete("refuels", null, null);
                    z13 = false;
                } else {
                    z13 = true;
                }
                jeVar2.a = z13;
                pi.T(jeVar2, meVar6, khVar3, frameLayout, mainActivity10);
            }
        });
        linearLayout2.addView(textViewU117);
        ScrollView scrollView3 = new ScrollView(mainActivity10);
        scrollView3.setVerticalScrollBarEnabled(r12);
        scrollView3.addView(linearLayout2);
        frameLayout.addView(scrollView3, new FrameLayout.LayoutParams(-1, -1));
    }

    public static final LinearLayout U(MainActivity mainActivity, String str, String str2, boolean z) {
        int iJ;
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        if (z) {
            iJ = Color.rgb(255, 69, 58);
        } else {
            int i = l8.a;
            iJ = l8.j(mainActivity.z());
        }
        TextView textViewU0 = mainActivity.u0(str2, 22.0f, iJ);
        textViewU0.setTypeface(textViewU0.getTypeface(), 1);
        linearLayout.addView(textViewU0);
        int i2 = l8.a;
        TextView textViewU1 = mainActivity.u0(str, 13.0f, l8.k(mainActivity.z()));
        textViewU1.setPadding(0, mainActivity.r(2), 0, 0);
        linearLayout.addView(textViewU1);
        return linearLayout;
    }

    public static final LinearLayout V(MainActivity mainActivity, View... viewArr) {
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(0);
        for (View view : viewArr) {
            linearLayout.addView(view, new LinearLayout.LayoutParams(0, -2, 1.0f));
        }
        return linearLayout;
    }

    public static h8 W(j8 j8Var, int i) {
        j8Var.getClass();
        boolean z = i > 0;
        Integer numValueOf = Integer.valueOf(i);
        if (!z) {
            throw new IllegalArgumentException("Step must be positive, was: " + numValueOf + '.');
        }
        int i2 = j8Var.a;
        int i3 = j8Var.b;
        if (j8Var.c <= 0) {
            i = -i;
        }
        return new h8(i2, i3, i);
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0065  */
    public static String X(bj bjVar, int i, float f) {
        bjVar.getClass();
        Float f2 = bjVar.c;
        if (i != 1) {
            if (i != 2) {
                Float f3 = bjVar.d;
                if (f3 != null) {
                    float fFloatValue = f3.floatValue();
                    List list = si.a;
                    return si.a("0_km", Integer.valueOf(Math.round(fFloatValue)));
                }
            } else {
                Float fValueOf = (f2 == null || Math.abs(f) > Float.MAX_VALUE || f <= 0.0f) ? null : Float.valueOf((r(f2.floatValue(), 0.0f, 100.0f) / 100.0f) * f);
                if (fValueOf == null) {
                    fValueOf = bjVar.b;
                    if (fValueOf != null) {
                        float fFloatValue2 = fValueOf.floatValue();
                        if (f > 0.0f && fFloatValue2 > f * 1.05f) {
                            fValueOf = null;
                        }
                    } else {
                        fValueOf = null;
                    }
                }
                if (fValueOf != null) {
                    float fFloatValue3 = fValueOf.floatValue();
                    List list2 = si.a;
                    return si.a("0_l", Integer.valueOf(Math.round(fFloatValue3)));
                }
            }
        } else if (f2 != null) {
            return Math.round(f2.floatValue()) + " %";
        }
        return null;
    }

    public static final TextPaint Y(int i, float f, boolean z) {
        TextPaint textPaint = new TextPaint(1);
        textPaint.setColor(i);
        textPaint.setTextSize(f);
        textPaint.setTypeface(z ? z9.c() : z9.b());
        return textPaint;
    }

    public static String Z(char c2, Locale locale) {
        String strValueOf = String.valueOf(c2);
        strValueOf.getClass();
        String upperCase = strValueOf.toUpperCase(locale);
        upperCase.getClass();
        if (upperCase.length() <= 1) {
            String strValueOf2 = String.valueOf(c2);
            strValueOf2.getClass();
            String upperCase2 = strValueOf2.toUpperCase(Locale.ROOT);
            upperCase2.getClass();
            if (upperCase.equals(upperCase2)) {
                return String.valueOf(Character.toTitleCase(c2));
            }
        } else if (c2 != 329) {
            char cCharAt = upperCase.charAt(0);
            String lowerCase = upperCase.substring(1).toLowerCase(Locale.ROOT);
            lowerCase.getClass();
            return cCharAt + lowerCase;
        }
        return upperCase;
    }

    public static final String a(Object[] objArr, int i, int i2, h hVar) {
        StringBuilder sb = new StringBuilder((i2 * 3) + 2);
        sb.append("[");
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 > 0) {
                sb.append(", ");
            }
            Object obj = objArr[i + i3];
            if (obj == hVar) {
                sb.append("(this Collection)");
            } else {
                sb.append(obj);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static final Object[] a0(Collection collection) {
        int size = collection.size();
        if (size != 0) {
            Iterator it = collection.iterator();
            if (it.hasNext()) {
                Object[] objArrCopyOf = new Object[size];
                int i = 0;
                while (true) {
                    int i2 = i + 1;
                    objArrCopyOf[i] = it.next();
                    if (i2 >= objArrCopyOf.length) {
                        if (!it.hasNext()) {
                            return objArrCopyOf;
                        }
                        int i3 = ((i2 * 3) + 1) >>> 1;
                        if (i3 <= i2) {
                            i3 = 2147483645;
                            if (i2 >= 2147483645) {
                                throw new OutOfMemoryError();
                            }
                        }
                        objArrCopyOf = Arrays.copyOf(objArrCopyOf, i3);
                    } else if (!it.hasNext()) {
                        return Arrays.copyOf(objArrCopyOf, i2);
                    }
                    i = i2;
                }
            }
        }
        return a;
    }

    public static boolean b(Context context) {
        LocalDate localDate;
        context.getClass();
        String string = context.getSharedPreferences("panel", 0).getString("region_pass_list", null);
        if (string != null) {
            List<ef> listR0 = wf.R0(wf.Q0(new y0(5, string), new w(27)));
            String strW = w(context);
            LocalDate localDateNow = LocalDate.now();
            localDateNow.getClass();
            String upperCase = strW.toUpperCase(Locale.ROOT);
            upperCase.getClass();
            StringBuilder sb = new StringBuilder();
            int length = upperCase.length();
            for (int i = 0; i < length; i++) {
                char cCharAt = upperCase.charAt(i);
                if (('0' <= cCharAt && cCharAt < ':') || ('A' <= cCharAt && cCharAt < 'G')) {
                    sb.append(cCharAt);
                }
            }
            String string2 = sb.toString();
            if (!listR0.isEmpty()) {
                for (ef efVar : listR0) {
                    if (efVar.a.equals(string2) && ((localDate = efVar.b) == null || !localDateNow.isAfter(localDate))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static final Object[] b0(Collection collection, Object[] objArr) {
        Object[] objArrCopyOf;
        int size = collection.size();
        int i = 0;
        if (size != 0) {
            Iterator it = collection.iterator();
            if (it.hasNext()) {
                if (size <= objArr.length) {
                    objArrCopyOf = objArr;
                } else {
                    Object objNewInstance = Array.newInstance(objArr.getClass().getComponentType(), size);
                    objNewInstance.getClass();
                    objArrCopyOf = (Object[]) objNewInstance;
                }
                while (true) {
                    int i2 = i + 1;
                    objArrCopyOf[i] = it.next();
                    if (i2 >= objArrCopyOf.length) {
                        if (!it.hasNext()) {
                            return objArrCopyOf;
                        }
                        int i3 = ((i2 * 3) + 1) >>> 1;
                        if (i3 <= i2) {
                            i3 = 2147483645;
                            if (i2 >= 2147483645) {
                                throw new OutOfMemoryError();
                            }
                        }
                        objArrCopyOf = Arrays.copyOf(objArrCopyOf, i3);
                    } else if (!it.hasNext()) {
                        if (objArrCopyOf != objArr) {
                            return Arrays.copyOf(objArrCopyOf, i2);
                        }
                        objArr[i2] = null;
                        return objArr;
                    }
                    i = i2;
                }
            } else if (objArr.length > 0) {
                objArr[0] = null;
            }
        } else if (objArr.length > 0) {
            objArr[0] = null;
            return objArr;
        }
        return objArr;
    }

    public static final void c(b8 b8Var, kh khVar, int i, boolean z) {
        li liVar = ni.N;
        p2 p2Var = ni.O;
        Float f = p2Var.d;
        float fFloatValue = f != null ? f.floatValue() : 0.0f;
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            Integer num = p2Var.a;
            khVar.a(jCurrentTimeMillis, num != null ? num.intValue() : 0, fFloatValue, fFloatValue, i, z, true);
        } catch (Throwable unused) {
        }
        b8Var.a();
    }

    public static final c5 c0(File file) {
        int length;
        List list;
        int iZ;
        String path = file.getPath();
        path.getClass();
        char c2 = File.separatorChar;
        int iZ2 = th.z(path, c2, 0, 4);
        if (iZ2 == 0) {
            if (path.length() <= 1 || path.charAt(1) != c2 || (iZ = th.z(path, c2, 2, 4)) < 0) {
                length = 1;
            } else {
                int iZ3 = th.z(path, c2, iZ + 1, 4);
                length = iZ3 >= 0 ? iZ3 + 1 : path.length();
            }
        } else if (iZ2 <= 0 || path.charAt(iZ2 - 1) != ':') {
            length = (iZ2 == -1 && path.length() > 0 && x(path.charAt(path.length() - 1), ':', false)) ? path.length() : 0;
        } else {
            length = iZ2 + 1;
        }
        String strSubstring = path.substring(0, length);
        String strSubstring2 = path.substring(length);
        if (strSubstring2.length() == 0) {
            list = u4.a;
        } else {
            List listD = th.D(strSubstring2, new char[]{c2});
            ArrayList arrayList = new ArrayList(k3.n0(listD));
            Iterator it = listD.iterator();
            while (it.hasNext()) {
                arrayList.add(new File((String) it.next()));
            }
            list = arrayList;
        }
        return new c5(new File(strSubstring), list);
    }

    public static Collection d(Object obj) {
        if ((obj instanceof x9) && !(obj instanceof y9)) {
            ClassCastException classCastException = new ClassCastException(obj.getClass().getName().concat(" cannot be cast to kotlin.collections.MutableCollection"));
            k8.q0(classCastException, pi.class.getName());
            throw classCastException;
        }
        try {
            return (Collection) obj;
        } catch (ClassCastException e) {
            k8.q0(e, pi.class.getName());
            throw e;
        }
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0044  */
    public static Boolean d0(Context context, boolean z, Boolean bool) {
        Object ffVar;
        BluetoothAdapter adapter;
        Boolean bool2;
        Boolean bool3;
        try {
            if (!z) {
                BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService(BluetoothManager.class);
                if (bluetoothManager != null && (adapter = bluetoothManager.getAdapter()) != null) {
                    int state = adapter.getState();
                    if (state != 11) {
                        bool2 = state != 13 ? null : Boolean.FALSE;
                    } else {
                        bool2 = Boolean.TRUE;
                    }
                    boolean zIsEnabled = adapter.isEnabled();
                    if (bool != null) {
                        zIsEnabled = bool.booleanValue();
                    } else if (bool2 != null) {
                        zIsEnabled = bool2.booleanValue();
                    }
                    ffVar = Boolean.valueOf(!zIsEnabled);
                    if (!(!zIsEnabled ? adapter.enable() : adapter.disable())) {
                        ffVar = null;
                    }
                }
                return null;
            }
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(WifiManager.class);
            if (wifiManager == null) {
                return null;
            }
            int wifiState = wifiManager.getWifiState();
            if (wifiState != 0) {
                bool3 = wifiState != 2 ? null : Boolean.TRUE;
            } else {
                bool3 = Boolean.FALSE;
            }
            boolean zIsWifiEnabled = wifiManager.isWifiEnabled();
            if (bool != null) {
                zIsWifiEnabled = bool.booleanValue();
            } else if (bool3 != null) {
                zIsWifiEnabled = bool3.booleanValue();
            }
            boolean z2 = !zIsWifiEnabled;
            ffVar = Boolean.valueOf(z2);
            if (!wifiManager.setWifiEnabled(z2)) {
                ffVar = null;
            }
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        return (Boolean) (ffVar instanceof ff ? null : ffVar);
    }

    public static final LinearLayout e(final MainActivity mainActivity, c9 c9Var, final y5 y5Var) {
        char c2;
        String str;
        z0 z0Var;
        LinearLayout linearLayoutO;
        String strA;
        c9 c9Var2 = c9Var;
        final z0 z0Var2 = new z0((Context) mainActivity);
        LinearLayout linearLayout = new LinearLayout(c9Var2.a);
        linearLayout.setOrientation(1);
        final int i = 0;
        linearLayout.addView(c9Var2.i(si.a("what_the_car_does_after_engine_start", new Object[0])));
        String strA2 = si.a("automations_27", new Object[0]);
        String strA3 = si.a("active_while_the_launcher_is_running", new Object[0]);
        SharedPreferences sharedPreferences = z0Var2.a;
        int i2 = 3;
        final int i3 = 4;
        final int i4 = 9;
        int i5 = 9;
        int i6 = 2;
        linearLayout.addView(c9Var2.d(c9Var2.r(strA2, strA3, sharedPreferences.getBoolean("auto_enabled", false), new o0((Object) z0Var2, mainActivity, y5Var, i2)), c9Var2.q(si.a("condition", new Object[0]), j3.i0(si.a("every_start", new Object[0]), si.a("after_parking", new Object[0])), sharedPreferences.getInt("auto_condition", 0), new t1(z0Var2, y5Var, i3)), c9.o(c9Var2, si.a("parked_for_at_least", new Object[0]), null, si.a("0_min", Integer.valueOf(sharedPreferences.getInt("auto_idle_minutes", 120))), new y5() { // from class: u1
            @Override // defpackage.y5
            public final Object a() {
                int i7 = i4;
                int i8 = 15;
                ti tiVar = ti.a;
                y5 y5Var2 = y5Var;
                z0 z0Var3 = z0Var2;
                switch (i7) {
                    case 0:
                        pi.K(mainActivity, si.a("turn_off_after", new Object[0]), z0Var3.a(15, "heat_minutes"), 0, 60, 5, si.a("min", new Object[0]), null, new t1(z0Var3, y5Var2, 20));
                        break;
                    case 1:
                        pi.K(mainActivity, si.a("heat_cold_temp", new Object[0]), z0Var3.a(5, "heat_cold_temp"), -20, 20, 1, "°C", null, new t1(z0Var3, y5Var2, 21));
                        break;
                    case 2:
                        pi.K(mainActivity, si.a("minimum_outside_temperature", new Object[0]), z0Var3.a(25, "vent_temp"), 10, 40, 1, "°C", null, new t1(z0Var3, y5Var2, 19));
                        break;
                    case 3:
                        pi.K(mainActivity, si.a("turn_off_after", new Object[0]), z0Var3.a(15, "vent_minutes"), 0, 60, 5, si.a("min", new Object[0]), null, new t1(z0Var3, y5Var2, i8));
                        break;
                    case 4:
                        pi.K(mainActivity, si.a("close_after", new Object[0]), z0Var3.a(3, "airing_minutes"), 1, 15, 1, si.a("min", new Object[0]), null, new t1(z0Var3, y5Var2, 16));
                        break;
                    case 5:
                        pi.K(mainActivity, si.a("minimum_outside_temperature", new Object[0]), z0Var3.a(15, "airing_temp"), 0, 30, 1, "°C", null, new t1(z0Var3, y5Var2, 14));
                        break;
                    case 6:
                        pi.K(mainActivity, si.a("airflow_speed", new Object[0]), z0Var3.a(4, "airing_fan"), 0, 8, 1, "", null, new t1(z0Var3, y5Var2, 18));
                        break;
                    case 7:
                        pi.K(mainActivity, si.a("temperature_268", new Object[0]), z0Var3.a(z0Var3.a(22, "climate_temp") * 2, "climate_temp_half"), Math.round(34.0f), Math.round(64.0f), 1, "", new w(2), new t1(z0Var3, y5Var2, 22));
                        break;
                    case 8:
                        pi.K(mainActivity, si.a("speed_threshold", new Object[0]), z0Var3.a(30, "close_kmh"), 10, 90, 5, si.a("kmh", new Object[0]), null, new t1(z0Var3, y5Var2, 17));
                        break;
                    default:
                        pi.K(mainActivity, si.a("parked_for_at_least", new Object[0]), z0Var3.a.getInt("auto_idle_minutes", 120), 15, 720, 15, si.a("min", new Object[0]), null, new t1(z0Var3, y5Var2, 23));
                        break;
                }
                return tiVar;
            }
        }, 8)));
        linearLayout.addView(c9Var2.b(si.a("app_autostart", new Object[0])));
        ArrayList arrayListS = k8.S(mainActivity);
        ArrayList arrayListJ0 = j3.j0(c9Var2.r(si.a("app_autostart", new Object[0]), si.a("app_autostart_subtitle", new Object[0]), z0Var2.b("apps", false), new t1(z0Var2, y5Var, i5)));
        int i7 = 0;
        int i8 = 0;
        for (int size = arrayListS.size(); i8 < size; size = size) {
            Object obj = arrayListS.get(i8);
            int i9 = i8 + 1;
            int i10 = i7 + 1;
            if (i7 < 0) {
                j3.m0();
                throw null;
            }
            e2 e2Var = (e2) obj;
            String strI = i(e2Var.a, mainActivity);
            int i11 = e2Var.b;
            if (i11 <= 0) {
                strA = si.a("app_autostart_now", new Object[0]);
            } else {
                List list = si.a;
                strA = si.a("app_autostart_after_0_s", Integer.valueOf(i11));
            }
            x1 x1Var = new x1(mainActivity, i7, y5Var);
            ArrayList arrayList = arrayListJ0;
            arrayList.add(c9.o(c9Var2, strI, null, strA, x1Var, 8));
            c9Var2 = c9Var;
            arrayListJ0 = arrayList;
            i5 = i5;
            i8 = i9;
            i7 = i10;
        }
        int i12 = i5;
        ArrayList arrayList2 = arrayListJ0;
        arrayList2.add(c9.o(c9Var, si.a("app_autostart_add", new Object[0]), arrayListS.isEmpty() ? si.a("app_autostart_empty", new Object[0]) : null, null, new y1(mainActivity, y5Var, i), 12));
        arrayList2.add(c9Var.r(si.a("app_autostart_home", new Object[0]), si.a("app_autostart_home_hint", new Object[0]), z0Var2.b("apps_home", true), new t1(z0Var2, y5Var, 10)));
        View[] viewArr = (View[]) arrayList2.toArray(new View[0]);
        linearLayout.addView(c9Var.d((View[]) Arrays.copyOf(viewArr, viewArr.length)));
        linearLayout.addView(c9Var.l(si.a("app_autostart_note", new Object[0])));
        linearLayout.addView(c9Var.b(si.a("heating_after_start", new Object[0])));
        Set set = sf.a;
        String str2 = uh.e(mainActivity.getSharedPreferences("panel", 0).getString("vehicle", "knewstar_001")).a;
        int i13 = i3.r0(sf.a, str2) ? 3 : i3.r0(sf.b, str2) ? 1 : 2;
        sf.c = i13;
        LinearLayout linearLayoutR = c9Var.r(si.a("driver_seat_heating", new Object[0]), null, z0Var2.b("seat_left", false), new t1(z0Var2, y5Var, 11));
        LinearLayout linearLayoutF = f(c9Var, z0Var2, y5Var, si.a("driver_level", new Object[0]), "seat_left_level", i13);
        LinearLayout linearLayoutR2 = c9Var.r(si.a("passenger_seat_heating", new Object[0]), null, z0Var2.b("seat_right", false), new t1(z0Var2, y5Var, 12));
        LinearLayout linearLayoutF2 = f(c9Var, z0Var2, y5Var, si.a("passenger_level", new Object[0]), "seat_right_level", i13);
        LinearLayout linearLayoutR3 = c9Var.r(si.a("steering_wheel_heating", new Object[0]), null, z0Var2.b("wheel", false), new t1(z0Var2, y5Var, 13));
        LinearLayout linearLayoutR4 = c9Var.r(si.a("rear_window_heating", new Object[0]), null, z0Var2.b("rear_glass", false), new t1(z0Var2, y5Var, 24));
        List list2 = z2.a;
        LinearLayout linearLayoutR5 = c9Var.r(z2.b(mainActivity.E().a), null, z0Var2.b("front_glass", false), new t1(z0Var2, y5Var, 25));
        LinearLayout linearLayoutR6 = c9Var.r(si.a("nozzle_heating", new Object[0]), null, z0Var2.b("nozzle", false), new t1(z0Var2, y5Var, 26));
        LinearLayout linearLayoutR7 = c9Var.r(si.a("windshield_defogging", new Object[0]), null, z0Var2.b("defrost", false), new t1(z0Var2, y5Var, i));
        LinearLayout linearLayoutO2 = c9.o(c9Var, si.a("turn_off_after", new Object[0]), null, si.a("0_min", Integer.valueOf(z0Var2.a(15, "heat_minutes"))), new y5() { // from class: u1
            @Override // defpackage.y5
            public final Object a() {
                int i14 = i;
                int i15 = 15;
                ti tiVar = ti.a;
                y5 y5Var2 = y5Var;
                z0 z0Var3 = z0Var2;
                switch (i14) {
                    case 0:
                        pi.K(mainActivity, si.a("turn_off_after", new Object[0]), z0Var3.a(15, "heat_minutes"), 0, 60, 5, si.a("min", new Object[0]), null, new t1(z0Var3, y5Var2, 20));
                        break;
                    case 1:
                        pi.K(mainActivity, si.a("heat_cold_temp", new Object[0]), z0Var3.a(5, "heat_cold_temp"), -20, 20, 1, "°C", null, new t1(z0Var3, y5Var2, 21));
                        break;
                    case 2:
                        pi.K(mainActivity, si.a("minimum_outside_temperature", new Object[0]), z0Var3.a(25, "vent_temp"), 10, 40, 1, "°C", null, new t1(z0Var3, y5Var2, 19));
                        break;
                    case 3:
                        pi.K(mainActivity, si.a("turn_off_after", new Object[0]), z0Var3.a(15, "vent_minutes"), 0, 60, 5, si.a("min", new Object[0]), null, new t1(z0Var3, y5Var2, i15));
                        break;
                    case 4:
                        pi.K(mainActivity, si.a("close_after", new Object[0]), z0Var3.a(3, "airing_minutes"), 1, 15, 1, si.a("min", new Object[0]), null, new t1(z0Var3, y5Var2, 16));
                        break;
                    case 5:
                        pi.K(mainActivity, si.a("minimum_outside_temperature", new Object[0]), z0Var3.a(15, "airing_temp"), 0, 30, 1, "°C", null, new t1(z0Var3, y5Var2, 14));
                        break;
                    case 6:
                        pi.K(mainActivity, si.a("airflow_speed", new Object[0]), z0Var3.a(4, "airing_fan"), 0, 8, 1, "", null, new t1(z0Var3, y5Var2, 18));
                        break;
                    case 7:
                        pi.K(mainActivity, si.a("temperature_268", new Object[0]), z0Var3.a(z0Var3.a(22, "climate_temp") * 2, "climate_temp_half"), Math.round(34.0f), Math.round(64.0f), 1, "", new w(2), new t1(z0Var3, y5Var2, 22));
                        break;
                    case 8:
                        pi.K(mainActivity, si.a("speed_threshold", new Object[0]), z0Var3.a(30, "close_kmh"), 10, 90, 5, si.a("kmh", new Object[0]), null, new t1(z0Var3, y5Var2, 17));
                        break;
                    default:
                        pi.K(mainActivity, si.a("parked_for_at_least", new Object[0]), z0Var3.a.getInt("auto_idle_minutes", 120), 15, 720, 15, si.a("min", new Object[0]), null, new t1(z0Var3, y5Var2, 23));
                        break;
                }
                return tiVar;
            }
        }, 8);
        LinearLayout linearLayoutR8 = c9Var.r(si.a("heat_only_cold", new Object[0]), si.a("heat_only_cold_hint", new Object[0]), z0Var2.b("heat_cold", false), new t1(z0Var2, y5Var, 1));
        int i14 = 5;
        if (z0Var2.b("heat_cold", false)) {
            String strA4 = si.a("heat_cold_temp", new Object[0]);
            String str3 = z0Var2.a(5, "heat_cold_temp") + " °C";
            final int i15 = 1;
            y5 y5Var2 = new y5() { // from class: u1
                @Override // defpackage.y5
                public final Object a() {
                    int i16 = i15;
                    int i17 = 15;
                    ti tiVar = ti.a;
                    y5 y5Var3 = y5Var;
                    z0 z0Var3 = z0Var2;
                    switch (i16) {
                        case 0:
                            pi.K(mainActivity, si.a("turn_off_after", new Object[0]), z0Var3.a(15, "heat_minutes"), 0, 60, 5, si.a("min", new Object[0]), null, new t1(z0Var3, y5Var3, 20));
                            break;
                        case 1:
                            pi.K(mainActivity, si.a("heat_cold_temp", new Object[0]), z0Var3.a(5, "heat_cold_temp"), -20, 20, 1, "°C", null, new t1(z0Var3, y5Var3, 21));
                            break;
                        case 2:
                            pi.K(mainActivity, si.a("minimum_outside_temperature", new Object[0]), z0Var3.a(25, "vent_temp"), 10, 40, 1, "°C", null, new t1(z0Var3, y5Var3, 19));
                            break;
                        case 3:
                            pi.K(mainActivity, si.a("turn_off_after", new Object[0]), z0Var3.a(15, "vent_minutes"), 0, 60, 5, si.a("min", new Object[0]), null, new t1(z0Var3, y5Var3, i17));
                            break;
                        case 4:
                            pi.K(mainActivity, si.a("close_after", new Object[0]), z0Var3.a(3, "airing_minutes"), 1, 15, 1, si.a("min", new Object[0]), null, new t1(z0Var3, y5Var3, 16));
                            break;
                        case 5:
                            pi.K(mainActivity, si.a("minimum_outside_temperature", new Object[0]), z0Var3.a(15, "airing_temp"), 0, 30, 1, "°C", null, new t1(z0Var3, y5Var3, 14));
                            break;
                        case 6:
                            pi.K(mainActivity, si.a("airflow_speed", new Object[0]), z0Var3.a(4, "airing_fan"), 0, 8, 1, "", null, new t1(z0Var3, y5Var3, 18));
                            break;
                        case 7:
                            pi.K(mainActivity, si.a("temperature_268", new Object[0]), z0Var3.a(z0Var3.a(22, "climate_temp") * 2, "climate_temp_half"), Math.round(34.0f), Math.round(64.0f), 1, "", new w(2), new t1(z0Var3, y5Var3, 22));
                            break;
                        case 8:
                            pi.K(mainActivity, si.a("speed_threshold", new Object[0]), z0Var3.a(30, "close_kmh"), 10, 90, 5, si.a("kmh", new Object[0]), null, new t1(z0Var3, y5Var3, 17));
                            break;
                        default:
                            pi.K(mainActivity, si.a("parked_for_at_least", new Object[0]), z0Var3.a.getInt("auto_idle_minutes", 120), 15, 720, 15, si.a("min", new Object[0]), null, new t1(z0Var3, y5Var3, 23));
                            break;
                    }
                    return tiVar;
                }
            };
            str = " °C";
            c2 = 1;
            z0Var = z0Var2;
            linearLayoutO = c9.o(c9Var, strA4, null, str3, y5Var2, 8);
        } else {
            c2 = 1;
            str = " °C";
            z0Var = z0Var2;
            linearLayoutO = null;
        }
        View[] viewArr2 = new View[12];
        viewArr2[0] = linearLayoutR;
        viewArr2[c2] = linearLayoutF;
        viewArr2[2] = linearLayoutR2;
        viewArr2[3] = linearLayoutF2;
        viewArr2[4] = linearLayoutR3;
        viewArr2[i14] = linearLayoutR4;
        final int i16 = 6;
        viewArr2[6] = linearLayoutR5;
        viewArr2[7] = linearLayoutR6;
        viewArr2[8] = linearLayoutR7;
        viewArr2[i12] = linearLayoutO2;
        viewArr2[10] = linearLayoutR8;
        viewArr2[11] = linearLayoutO;
        View[] viewArr3 = (View[]) ((ArrayList) x0.S0(viewArr2)).toArray(new View[0]);
        linearLayout.addView(c9Var.d((View[]) Arrays.copyOf(viewArr3, viewArr3.length)));
        linearLayout.addView(c9Var.b(si.a("seat_ventilation_after_start", new Object[0])));
        final z0 z0Var3 = z0Var;
        final int i17 = 2;
        final int i18 = 3;
        View[] viewArr4 = (View[]) ((ArrayList) x0.S0(new View[]{c9Var.r(si.a("driver_seat_ventilation", new Object[0]), null, z0Var.b("vent_left", false), new t1(z0Var, y5Var, i6)), f(c9Var, z0Var3, y5Var, si.a("driver_level", new Object[0]), "vent_left_level", 3), c9Var.r(si.a("passenger_seat_ventilation", new Object[0]), null, z0Var3.b("vent_right", false), new t1(z0Var3, y5Var, i2)), f(c9Var, z0Var3, y5Var, si.a("passenger_level", new Object[0]), "vent_right_level", 3), c9.o(c9Var, si.a("minimum_outside_temperature", new Object[0]), null, z0Var3.a(25, "vent_temp") + str, new y5() { // from class: u1
            @Override // defpackage.y5
            public final Object a() {
                int i19 = i17;
                int i110 = 15;
                ti tiVar = ti.a;
                y5 y5Var3 = y5Var;
                z0 z0Var4 = z0Var3;
                switch (i19) {
                    case 0:
                        pi.K(mainActivity, si.a("turn_off_after", new Object[0]), z0Var4.a(15, "heat_minutes"), 0, 60, 5, si.a("min", new Object[0]), null, new t1(z0Var4, y5Var3, 20));
                        break;
                    case 1:
                        pi.K(mainActivity, si.a("heat_cold_temp", new Object[0]), z0Var4.a(5, "heat_cold_temp"), -20, 20, 1, "°C", null, new t1(z0Var4, y5Var3, 21));
                        break;
                    case 2:
                        pi.K(mainActivity, si.a("minimum_outside_temperature", new Object[0]), z0Var4.a(25, "vent_temp"), 10, 40, 1, "°C", null, new t1(z0Var4, y5Var3, 19));
                        break;
                    case 3:
                        pi.K(mainActivity, si.a("turn_off_after", new Object[0]), z0Var4.a(15, "vent_minutes"), 0, 60, 5, si.a("min", new Object[0]), null, new t1(z0Var4, y5Var3, i110));
                        break;
                    case 4:
                        pi.K(mainActivity, si.a("close_after", new Object[0]), z0Var4.a(3, "airing_minutes"), 1, 15, 1, si.a("min", new Object[0]), null, new t1(z0Var4, y5Var3, 16));
                        break;
                    case 5:
                        pi.K(mainActivity, si.a("minimum_outside_temperature", new Object[0]), z0Var4.a(15, "airing_temp"), 0, 30, 1, "°C", null, new t1(z0Var4, y5Var3, 14));
                        break;
                    case 6:
                        pi.K(mainActivity, si.a("airflow_speed", new Object[0]), z0Var4.a(4, "airing_fan"), 0, 8, 1, "", null, new t1(z0Var4, y5Var3, 18));
                        break;
                    case 7:
                        pi.K(mainActivity, si.a("temperature_268", new Object[0]), z0Var4.a(z0Var4.a(22, "climate_temp") * 2, "climate_temp_half"), Math.round(34.0f), Math.round(64.0f), 1, "", new w(2), new t1(z0Var4, y5Var3, 22));
                        break;
                    case 8:
                        pi.K(mainActivity, si.a("speed_threshold", new Object[0]), z0Var4.a(30, "close_kmh"), 10, 90, 5, si.a("kmh", new Object[0]), null, new t1(z0Var4, y5Var3, 17));
                        break;
                    default:
                        pi.K(mainActivity, si.a("parked_for_at_least", new Object[0]), z0Var4.a.getInt("auto_idle_minutes", 120), 15, 720, 15, si.a("min", new Object[0]), null, new t1(z0Var4, y5Var3, 23));
                        break;
                }
                return tiVar;
            }
        }, 8), c9.o(c9Var, si.a("turn_off_after", new Object[0]), null, si.a("0_min", Integer.valueOf(z0Var3.a(15, "vent_minutes"))), new y5() { // from class: u1
            @Override // defpackage.y5
            public final Object a() {
                int i19 = i18;
                int i110 = 15;
                ti tiVar = ti.a;
                y5 y5Var3 = y5Var;
                z0 z0Var4 = z0Var3;
                switch (i19) {
                    case 0:
                        pi.K(mainActivity, si.a("turn_off_after", new Object[0]), z0Var4.a(15, "heat_minutes"), 0, 60, 5, si.a("min", new Object[0]), null, new t1(z0Var4, y5Var3, 20));
                        break;
                    case 1:
                        pi.K(mainActivity, si.a("heat_cold_temp", new Object[0]), z0Var4.a(5, "heat_cold_temp"), -20, 20, 1, "°C", null, new t1(z0Var4, y5Var3, 21));
                        break;
                    case 2:
                        pi.K(mainActivity, si.a("minimum_outside_temperature", new Object[0]), z0Var4.a(25, "vent_temp"), 10, 40, 1, "°C", null, new t1(z0Var4, y5Var3, 19));
                        break;
                    case 3:
                        pi.K(mainActivity, si.a("turn_off_after", new Object[0]), z0Var4.a(15, "vent_minutes"), 0, 60, 5, si.a("min", new Object[0]), null, new t1(z0Var4, y5Var3, i110));
                        break;
                    case 4:
                        pi.K(mainActivity, si.a("close_after", new Object[0]), z0Var4.a(3, "airing_minutes"), 1, 15, 1, si.a("min", new Object[0]), null, new t1(z0Var4, y5Var3, 16));
                        break;
                    case 5:
                        pi.K(mainActivity, si.a("minimum_outside_temperature", new Object[0]), z0Var4.a(15, "airing_temp"), 0, 30, 1, "°C", null, new t1(z0Var4, y5Var3, 14));
                        break;
                    case 6:
                        pi.K(mainActivity, si.a("airflow_speed", new Object[0]), z0Var4.a(4, "airing_fan"), 0, 8, 1, "", null, new t1(z0Var4, y5Var3, 18));
                        break;
                    case 7:
                        pi.K(mainActivity, si.a("temperature_268", new Object[0]), z0Var4.a(z0Var4.a(22, "climate_temp") * 2, "climate_temp_half"), Math.round(34.0f), Math.round(64.0f), 1, "", new w(2), new t1(z0Var4, y5Var3, 22));
                        break;
                    case 8:
                        pi.K(mainActivity, si.a("speed_threshold", new Object[0]), z0Var4.a(30, "close_kmh"), 10, 90, 5, si.a("kmh", new Object[0]), null, new t1(z0Var4, y5Var3, 17));
                        break;
                    default:
                        pi.K(mainActivity, si.a("parked_for_at_least", new Object[0]), z0Var4.a.getInt("auto_idle_minutes", 120), 15, 720, 15, si.a("min", new Object[0]), null, new t1(z0Var4, y5Var3, 23));
                        break;
                }
                return tiVar;
            }
        }, 8)})).toArray(new View[0]);
        linearLayout.addView(c9Var.d((View[]) Arrays.copyOf(viewArr4, viewArr4.length)));
        linearLayout.addView(c9Var.b(si.a("ventilation_304", new Object[0])));
        LinearLayout linearLayoutR9 = c9Var.r(si.a("ventilate_after_start", new Object[0]), si.a("sunroof_and_fan_when_it_is_warm", new Object[0]), z0Var3.b("airing", false), new t1(z0Var3, y5Var, i14));
        LinearLayout linearLayoutQ = c9Var.q(si.a("sunroof", new Object[0]), j3.i0(si.a("ventilation_304", new Object[0]), si.a("open", new Object[0])), z0Var3.a(0, "airing_mode"), new t1(z0Var3, y5Var, i16));
        LinearLayout linearLayoutO3 = c9.o(c9Var, si.a("close_after", new Object[0]), null, si.a("0_min", Integer.valueOf(z0Var3.a(3, "airing_minutes"))), new y5() { // from class: u1
            @Override // defpackage.y5
            public final Object a() {
                int i19 = i3;
                int i110 = 15;
                ti tiVar = ti.a;
                y5 y5Var3 = y5Var;
                z0 z0Var4 = z0Var3;
                switch (i19) {
                    case 0:
                        pi.K(mainActivity, si.a("turn_off_after", new Object[0]), z0Var4.a(15, "heat_minutes"), 0, 60, 5, si.a("min", new Object[0]), null, new t1(z0Var4, y5Var3, 20));
                        break;
                    case 1:
                        pi.K(mainActivity, si.a("heat_cold_temp", new Object[0]), z0Var4.a(5, "heat_cold_temp"), -20, 20, 1, "°C", null, new t1(z0Var4, y5Var3, 21));
                        break;
                    case 2:
                        pi.K(mainActivity, si.a("minimum_outside_temperature", new Object[0]), z0Var4.a(25, "vent_temp"), 10, 40, 1, "°C", null, new t1(z0Var4, y5Var3, 19));
                        break;
                    case 3:
                        pi.K(mainActivity, si.a("turn_off_after", new Object[0]), z0Var4.a(15, "vent_minutes"), 0, 60, 5, si.a("min", new Object[0]), null, new t1(z0Var4, y5Var3, i110));
                        break;
                    case 4:
                        pi.K(mainActivity, si.a("close_after", new Object[0]), z0Var4.a(3, "airing_minutes"), 1, 15, 1, si.a("min", new Object[0]), null, new t1(z0Var4, y5Var3, 16));
                        break;
                    case 5:
                        pi.K(mainActivity, si.a("minimum_outside_temperature", new Object[0]), z0Var4.a(15, "airing_temp"), 0, 30, 1, "°C", null, new t1(z0Var4, y5Var3, 14));
                        break;
                    case 6:
                        pi.K(mainActivity, si.a("airflow_speed", new Object[0]), z0Var4.a(4, "airing_fan"), 0, 8, 1, "", null, new t1(z0Var4, y5Var3, 18));
                        break;
                    case 7:
                        pi.K(mainActivity, si.a("temperature_268", new Object[0]), z0Var4.a(z0Var4.a(22, "climate_temp") * 2, "climate_temp_half"), Math.round(34.0f), Math.round(64.0f), 1, "", new w(2), new t1(z0Var4, y5Var3, 22));
                        break;
                    case 8:
                        pi.K(mainActivity, si.a("speed_threshold", new Object[0]), z0Var4.a(30, "close_kmh"), 10, 90, 5, si.a("kmh", new Object[0]), null, new t1(z0Var4, y5Var3, 17));
                        break;
                    default:
                        pi.K(mainActivity, si.a("parked_for_at_least", new Object[0]), z0Var4.a.getInt("auto_idle_minutes", 120), 15, 720, 15, si.a("min", new Object[0]), null, new t1(z0Var4, y5Var3, 23));
                        break;
                }
                return tiVar;
            }
        }, 8);
        final int i19 = 5;
        LinearLayout linearLayoutO4 = c9.o(c9Var, si.a("minimum_outside_temperature", new Object[0]), null, z0Var3.a(15, "airing_temp") + str, new y5() { // from class: u1
            @Override // defpackage.y5
            public final Object a() {
                int i110 = i19;
                int i111 = 15;
                ti tiVar = ti.a;
                y5 y5Var3 = y5Var;
                z0 z0Var4 = z0Var3;
                switch (i110) {
                    case 0:
                        pi.K(mainActivity, si.a("turn_off_after", new Object[0]), z0Var4.a(15, "heat_minutes"), 0, 60, 5, si.a("min", new Object[0]), null, new t1(z0Var4, y5Var3, 20));
                        break;
                    case 1:
                        pi.K(mainActivity, si.a("heat_cold_temp", new Object[0]), z0Var4.a(5, "heat_cold_temp"), -20, 20, 1, "°C", null, new t1(z0Var4, y5Var3, 21));
                        break;
                    case 2:
                        pi.K(mainActivity, si.a("minimum_outside_temperature", new Object[0]), z0Var4.a(25, "vent_temp"), 10, 40, 1, "°C", null, new t1(z0Var4, y5Var3, 19));
                        break;
                    case 3:
                        pi.K(mainActivity, si.a("turn_off_after", new Object[0]), z0Var4.a(15, "vent_minutes"), 0, 60, 5, si.a("min", new Object[0]), null, new t1(z0Var4, y5Var3, i111));
                        break;
                    case 4:
                        pi.K(mainActivity, si.a("close_after", new Object[0]), z0Var4.a(3, "airing_minutes"), 1, 15, 1, si.a("min", new Object[0]), null, new t1(z0Var4, y5Var3, 16));
                        break;
                    case 5:
                        pi.K(mainActivity, si.a("minimum_outside_temperature", new Object[0]), z0Var4.a(15, "airing_temp"), 0, 30, 1, "°C", null, new t1(z0Var4, y5Var3, 14));
                        break;
                    case 6:
                        pi.K(mainActivity, si.a("airflow_speed", new Object[0]), z0Var4.a(4, "airing_fan"), 0, 8, 1, "", null, new t1(z0Var4, y5Var3, 18));
                        break;
                    case 7:
                        pi.K(mainActivity, si.a("temperature_268", new Object[0]), z0Var4.a(z0Var4.a(22, "climate_temp") * 2, "climate_temp_half"), Math.round(34.0f), Math.round(64.0f), 1, "", new w(2), new t1(z0Var4, y5Var3, 22));
                        break;
                    case 8:
                        pi.K(mainActivity, si.a("speed_threshold", new Object[0]), z0Var4.a(30, "close_kmh"), 10, 90, 5, si.a("kmh", new Object[0]), null, new t1(z0Var4, y5Var3, 17));
                        break;
                    default:
                        pi.K(mainActivity, si.a("parked_for_at_least", new Object[0]), z0Var4.a.getInt("auto_idle_minutes", 120), 15, 720, 15, si.a("min", new Object[0]), null, new t1(z0Var4, y5Var3, 23));
                        break;
                }
                return tiVar;
            }
        }, 8);
        String strA5 = si.a("airflow", new Object[0]);
        int iA = z0Var3.a(4, "airing_fan");
        linearLayout.addView(c9Var.d(linearLayoutR9, linearLayoutQ, linearLayoutO3, linearLayoutO4, c9.o(c9Var, strA5, null, iA == 0 ? si.a("leave_unchanged", new Object[0]) : String.valueOf(iA), new y5() { // from class: u1
            @Override // defpackage.y5
            public final Object a() {
                int i110 = i16;
                int i111 = 15;
                ti tiVar = ti.a;
                y5 y5Var3 = y5Var;
                z0 z0Var4 = z0Var3;
                switch (i110) {
                    case 0:
                        pi.K(mainActivity, si.a("turn_off_after", new Object[0]), z0Var4.a(15, "heat_minutes"), 0, 60, 5, si.a("min", new Object[0]), null, new t1(z0Var4, y5Var3, 20));
                        break;
                    case 1:
                        pi.K(mainActivity, si.a("heat_cold_temp", new Object[0]), z0Var4.a(5, "heat_cold_temp"), -20, 20, 1, "°C", null, new t1(z0Var4, y5Var3, 21));
                        break;
                    case 2:
                        pi.K(mainActivity, si.a("minimum_outside_temperature", new Object[0]), z0Var4.a(25, "vent_temp"), 10, 40, 1, "°C", null, new t1(z0Var4, y5Var3, 19));
                        break;
                    case 3:
                        pi.K(mainActivity, si.a("turn_off_after", new Object[0]), z0Var4.a(15, "vent_minutes"), 0, 60, 5, si.a("min", new Object[0]), null, new t1(z0Var4, y5Var3, i111));
                        break;
                    case 4:
                        pi.K(mainActivity, si.a("close_after", new Object[0]), z0Var4.a(3, "airing_minutes"), 1, 15, 1, si.a("min", new Object[0]), null, new t1(z0Var4, y5Var3, 16));
                        break;
                    case 5:
                        pi.K(mainActivity, si.a("minimum_outside_temperature", new Object[0]), z0Var4.a(15, "airing_temp"), 0, 30, 1, "°C", null, new t1(z0Var4, y5Var3, 14));
                        break;
                    case 6:
                        pi.K(mainActivity, si.a("airflow_speed", new Object[0]), z0Var4.a(4, "airing_fan"), 0, 8, 1, "", null, new t1(z0Var4, y5Var3, 18));
                        break;
                    case 7:
                        pi.K(mainActivity, si.a("temperature_268", new Object[0]), z0Var4.a(z0Var4.a(22, "climate_temp") * 2, "climate_temp_half"), Math.round(34.0f), Math.round(64.0f), 1, "", new w(2), new t1(z0Var4, y5Var3, 22));
                        break;
                    case 8:
                        pi.K(mainActivity, si.a("speed_threshold", new Object[0]), z0Var4.a(30, "close_kmh"), 10, 90, 5, si.a("kmh", new Object[0]), null, new t1(z0Var4, y5Var3, 17));
                        break;
                    default:
                        pi.K(mainActivity, si.a("parked_for_at_least", new Object[0]), z0Var4.a.getInt("auto_idle_minutes", 120), 15, 720, 15, si.a("min", new Object[0]), null, new t1(z0Var4, y5Var3, 23));
                        break;
                }
                return tiVar;
            }
        }, 8)));
        linearLayout.addView(c9Var.b(si.a("climate_and_sunroof", new Object[0])));
        final int i20 = 7;
        final int i21 = 8;
        linearLayout.addView(c9Var.d(c9Var.r(si.a("start_climate_in_auto_369", new Object[0]), null, z0Var3.b("climate", false), new t1(z0Var3, y5Var, 7)), c9.o(c9Var, si.a("temperature_268", new Object[0]), null, z2.d(z0Var3.a(z0Var3.a(22, "climate_temp") * 2, "climate_temp_half") / 2.0f), new y5() { // from class: u1
            @Override // defpackage.y5
            public final Object a() {
                int i110 = i20;
                int i111 = 15;
                ti tiVar = ti.a;
                y5 y5Var3 = y5Var;
                z0 z0Var4 = z0Var3;
                switch (i110) {
                    case 0:
                        pi.K(mainActivity, si.a("turn_off_after", new Object[0]), z0Var4.a(15, "heat_minutes"), 0, 60, 5, si.a("min", new Object[0]), null, new t1(z0Var4, y5Var3, 20));
                        break;
                    case 1:
                        pi.K(mainActivity, si.a("heat_cold_temp", new Object[0]), z0Var4.a(5, "heat_cold_temp"), -20, 20, 1, "°C", null, new t1(z0Var4, y5Var3, 21));
                        break;
                    case 2:
                        pi.K(mainActivity, si.a("minimum_outside_temperature", new Object[0]), z0Var4.a(25, "vent_temp"), 10, 40, 1, "°C", null, new t1(z0Var4, y5Var3, 19));
                        break;
                    case 3:
                        pi.K(mainActivity, si.a("turn_off_after", new Object[0]), z0Var4.a(15, "vent_minutes"), 0, 60, 5, si.a("min", new Object[0]), null, new t1(z0Var4, y5Var3, i111));
                        break;
                    case 4:
                        pi.K(mainActivity, si.a("close_after", new Object[0]), z0Var4.a(3, "airing_minutes"), 1, 15, 1, si.a("min", new Object[0]), null, new t1(z0Var4, y5Var3, 16));
                        break;
                    case 5:
                        pi.K(mainActivity, si.a("minimum_outside_temperature", new Object[0]), z0Var4.a(15, "airing_temp"), 0, 30, 1, "°C", null, new t1(z0Var4, y5Var3, 14));
                        break;
                    case 6:
                        pi.K(mainActivity, si.a("airflow_speed", new Object[0]), z0Var4.a(4, "airing_fan"), 0, 8, 1, "", null, new t1(z0Var4, y5Var3, 18));
                        break;
                    case 7:
                        pi.K(mainActivity, si.a("temperature_268", new Object[0]), z0Var4.a(z0Var4.a(22, "climate_temp") * 2, "climate_temp_half"), Math.round(34.0f), Math.round(64.0f), 1, "", new w(2), new t1(z0Var4, y5Var3, 22));
                        break;
                    case 8:
                        pi.K(mainActivity, si.a("speed_threshold", new Object[0]), z0Var4.a(30, "close_kmh"), 10, 90, 5, si.a("kmh", new Object[0]), null, new t1(z0Var4, y5Var3, 17));
                        break;
                    default:
                        pi.K(mainActivity, si.a("parked_for_at_least", new Object[0]), z0Var4.a.getInt("auto_idle_minutes", 120), 15, 720, 15, si.a("min", new Object[0]), null, new t1(z0Var4, y5Var3, 23));
                        break;
                }
                return tiVar;
            }
        }, 8), c9Var.r(si.a("close_sunroof_at_speed", new Object[0]), null, z0Var3.b("close", false), new t1(z0Var3, y5Var, 8)), c9.o(c9Var, si.a("speed_threshold", new Object[0]), null, si.a("0_kmh", Integer.valueOf(z0Var3.a(30, "close_kmh"))), new y5() { // from class: u1
            @Override // defpackage.y5
            public final Object a() {
                int i110 = i21;
                int i111 = 15;
                ti tiVar = ti.a;
                y5 y5Var3 = y5Var;
                z0 z0Var4 = z0Var3;
                switch (i110) {
                    case 0:
                        pi.K(mainActivity, si.a("turn_off_after", new Object[0]), z0Var4.a(15, "heat_minutes"), 0, 60, 5, si.a("min", new Object[0]), null, new t1(z0Var4, y5Var3, 20));
                        break;
                    case 1:
                        pi.K(mainActivity, si.a("heat_cold_temp", new Object[0]), z0Var4.a(5, "heat_cold_temp"), -20, 20, 1, "°C", null, new t1(z0Var4, y5Var3, 21));
                        break;
                    case 2:
                        pi.K(mainActivity, si.a("minimum_outside_temperature", new Object[0]), z0Var4.a(25, "vent_temp"), 10, 40, 1, "°C", null, new t1(z0Var4, y5Var3, 19));
                        break;
                    case 3:
                        pi.K(mainActivity, si.a("turn_off_after", new Object[0]), z0Var4.a(15, "vent_minutes"), 0, 60, 5, si.a("min", new Object[0]), null, new t1(z0Var4, y5Var3, i111));
                        break;
                    case 4:
                        pi.K(mainActivity, si.a("close_after", new Object[0]), z0Var4.a(3, "airing_minutes"), 1, 15, 1, si.a("min", new Object[0]), null, new t1(z0Var4, y5Var3, 16));
                        break;
                    case 5:
                        pi.K(mainActivity, si.a("minimum_outside_temperature", new Object[0]), z0Var4.a(15, "airing_temp"), 0, 30, 1, "°C", null, new t1(z0Var4, y5Var3, 14));
                        break;
                    case 6:
                        pi.K(mainActivity, si.a("airflow_speed", new Object[0]), z0Var4.a(4, "airing_fan"), 0, 8, 1, "", null, new t1(z0Var4, y5Var3, 18));
                        break;
                    case 7:
                        pi.K(mainActivity, si.a("temperature_268", new Object[0]), z0Var4.a(z0Var4.a(22, "climate_temp") * 2, "climate_temp_half"), Math.round(34.0f), Math.round(64.0f), 1, "", new w(2), new t1(z0Var4, y5Var3, 22));
                        break;
                    case 8:
                        pi.K(mainActivity, si.a("speed_threshold", new Object[0]), z0Var4.a(30, "close_kmh"), 10, 90, 5, si.a("kmh", new Object[0]), null, new t1(z0Var4, y5Var3, 17));
                        break;
                    default:
                        pi.K(mainActivity, si.a("parked_for_at_least", new Object[0]), z0Var4.a.getInt("auto_idle_minutes", 120), 15, 720, 15, si.a("min", new Object[0]), null, new t1(z0Var4, y5Var3, 23));
                        break;
                }
                return tiVar;
            }
        }, 8)));
        linearLayout.addView(c9Var.l(si.a("each_rule_runs_once_per_engine_start", new Object[0])));
        return linearLayout;
    }

    public static String e0(String str) {
        str.getClass();
        if (x0.Y0(new String[]{"radio_favorites", "radio_stations"}).contains(str) || str.equals("launcher_theme")) {
            return "string";
        }
        if (uh.k(str) != null) {
            String strK = uh.k(str);
            strK.getClass();
            return strK.equals("ready") ? "boolean" : e0(strK);
        }
        if (x0.Y0(new String[]{"auto_last_stop", "auto_last_driving", "stats_fuel_base", "stats_odo_scale", "stats_open_trip", "autoplay_boot_count"}).contains(str)) {
            return null;
        }
        if (str.equals("auto_apps_list")) {
            return "string";
        }
        if (str.equals("auto_enabled")) {
            return "boolean";
        }
        if (str.startsWith("auto_") && str.endsWith("_on")) {
            return "boolean";
        }
        if (str.startsWith("auto_")) {
            return "int";
        }
        if (x0.Y0(new String[]{"stats_enabled", "saver_on", "saver_parked", "saver_track", "saver_date", "saver_weather", "radio_autoplay", "dock_overlay", "dock_overlay_mode", "suppress_climate_popup", "navigation_embedded", "call_pause_music", "music_cover_swipe", "update_auto"}).contains(str)) {
            return "boolean";
        }
        if (x0.Y0(new String[]{"saver_minutes", "saver_dim", "fuel_display", "dock_transparency", "saver_clock", "saver_clock_size", "surface_style"}).contains(str) || e3.a.contains(str)) {
            return "int";
        }
        if (P("icon_set").contains(str) || str.startsWith("saver_pos_") || str.startsWith("climate_group_")) {
            return "string";
        }
        if (str.equals("tank_liters")) {
            return "float";
        }
        if (str.startsWith("split_") && str.endsWith("_ratio")) {
            return "int";
        }
        if (str.equals("split_list") || str.startsWith("split_")) {
            return "string";
        }
        if (x0.Y0(new String[]{"desktop_splits", "wall_splits"}).contains(str)) {
            return "set";
        }
        if (e3.b.contains(str) || x0.Y0(new String[]{"ios_home_v1", "ios_home_v2", "ios_music2_hidden", "ios_collapsed", "fullscreen", "navigation_assist"}).contains(str)) {
            return "boolean";
        }
        if (x0.Y0(new String[]{"wall_widgets", "wall_apps", "wall_vehicle_cards", "desktop_actions", "wall_actions", "desktop_images", "wall_images"}).contains(str)) {
            return "set";
        }
        if (x0.Y0(new String[]{"theme_mode", "dock_size", "dock_alignment", "navigation_assist_mode", "app_language"}).contains(str)) {
            return "string";
        }
        if (x0.Y0(new String[]{"last_brightness", "navigation_assist_x", "navigation_assist_y"}).contains(str)) {
            return "float";
        }
        if (x0.Y0(new String[]{"night", "onboarded", "show_vehicle", "independent_dock", "autostart", "music_autoplay", "menu_labels", "desktop_grid", "desktop_snap", "tile_backgrounds", "separate_radios", "icon_pack"}).contains(str) || str.startsWith("tile_background_")) {
            return "boolean";
        }
        if (x0.Y0(new String[]{"background", "color", "menu_count"}).contains(str)) {
            return "int";
        }
        List<String> listI0 = j3.i0("color_", "image_scale_", "tile_opacity_", "tpms_", "widget_transparency_", "dock_button_transparency_");
        if (!listI0.isEmpty()) {
            for (String str2 : listI0) {
                str2.getClass();
                if (str.startsWith(str2)) {
                    return "int";
                }
            }
        }
        if (str.equals("ui_scale")) {
            return "float";
        }
        if (x0.Y0(new String[]{"desktop_apps", "desktop_hidden", "desktop_vehicle_cards", "favorite_apps"}).contains(str)) {
            return "set";
        }
        if (x0.Y0(new String[]{"vehicle", "custom_title", "dock_0", "dock_1", "tile_0", "tile_1"}).contains(str)) {
            return "string";
        }
        List<String> listI1 = j3.i0("slot_", "menu_action_", "desktop_");
        if (listI1.isEmpty()) {
            return null;
        }
        for (String str3 : listI1) {
            str3.getClass();
            if (str.startsWith(str3)) {
                return "string";
            }
        }
        return null;
    }

    public static final LinearLayout f(c9 c9Var, z0 z0Var, y5 y5Var, String str, String str2, int i) {
        if (i <= 1) {
            return null;
        }
        j8 j8Var = new j8(1, i, 1);
        ArrayList arrayList = new ArrayList(k3.n0(j8Var));
        Iterator it = j8Var.iterator();
        while (true) {
            i8 i8Var = (i8) it;
            if (!i8Var.c) {
                return c9Var.q(str, arrayList, s(z0Var.a(2, str2), 1, i) - 1, new o0(z0Var, str2, y5Var, 2));
            }
            arrayList.add(String.valueOf(i8Var.nextInt()));
        }
    }

    public static j8 f0(int i, int i2) {
        if (i2 > Integer.MIN_VALUE) {
            return new j8(i, i2 - 1, 1);
        }
        j8 j8Var = j8.d;
        return j8.d;
    }

    public static final void g(MainActivity mainActivity) {
        Window window;
        int i = 0;
        c9 c9Var = new c9(mainActivity, mainActivity.z(), mainActivity.w(), new v1(mainActivity, i));
        Dialog dialog = new Dialog(mainActivity);
        if (c && (window = dialog.getWindow()) != null) {
            window.setType(2038);
        }
        FrameLayout frameLayout = new FrameLayout(mainActivity);
        h(frameLayout, mainActivity, c9Var);
        dialog.setContentView(c9Var.t(si.a("automation", new Object[0]), frameLayout, new w1(dialog, i)));
        dialog.setCanceledOnTouchOutside(true);
        Window window2 = dialog.getWindow();
        if (window2 != null) {
            window2.setBackgroundDrawable(new ColorDrawable(0));
            window2.setLayout((int) (l8.m(mainActivity) * 880.0f), (int) (mainActivity.getResources().getDisplayMetrics().heightPixels * 0.86f));
            window2.setDimAmount(0.55f);
            window2.setFlags(8, 8);
            window2.getDecorView().setSystemUiVisibility(mainActivity.getWindow().getDecorView().getSystemUiVisibility());
        }
        dialog.show();
        Window window3 = dialog.getWindow();
        if (window3 != null) {
            window3.clearFlags(8);
        }
    }

    public static final void h(FrameLayout frameLayout, MainActivity mainActivity, c9 c9Var) {
        frameLayout.removeAllViews();
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.addView(e(mainActivity, c9Var, new u0(mainActivity, frameLayout, c9Var, 1)));
        frameLayout.addView(scrollView, new FrameLayout.LayoutParams(-1, -1));
    }

    public static final String i(String str, MainActivity mainActivity) {
        Object ffVar;
        Object ffVar2;
        ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(str);
        if (componentNameUnflattenFromString != null) {
            try {
                ffVar = mainActivity.getPackageManager().getActivityInfo(componentNameUnflattenFromString, 0).loadLabel(mainActivity.getPackageManager()).toString();
            } catch (Throwable th) {
                ffVar = new ff(th);
            }
            if (ffVar instanceof ff) {
                ffVar = null;
            }
            String str2 = (String) ffVar;
            if (str2 != null) {
                return str2;
            }
        }
        try {
            ffVar2 = mainActivity.getPackageManager().getApplicationLabel(mainActivity.getPackageManager().getApplicationInfo(th.F(str, '/'), 0)).toString();
        } catch (Throwable th2) {
            ffVar2 = new ff(th2);
        }
        String str3 = (String) (ffVar2 instanceof ff ? null : ffVar2);
        return str3 == null ? th.F(str, '/') : str3;
    }

    public static n4 j(String str) {
        Object next;
        str.getClass();
        Iterator it = A().iterator();
        while (it.hasNext()) {
            next = it.next();
            if (k8.e(((n4) next).a, str)) {
                return (n4) next;
            }
        }
        next = null;
        return (n4) next;
    }

    public static byte[] k(int i, String str) throws IOException {
        URLConnection uRLConnectionOpenConnection = new URL(str).openConnection();
        uRLConnectionOpenConnection.getClass();
        HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setReadTimeout(30000);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setRequestProperty("User-Agent", "ReferencePanel/1.0.2");
        try {
            if (httpURLConnection.getResponseCode() != 200) {
                throw new IllegalStateException(("HTTP " + httpURLConnection.getResponseCode()).toString());
            }
            InputStream inputStream = httpURLConnection.getInputStream();
            try {
                inputStream.getClass();
                byte[] bArrM = M(inputStream);
                inputStream.close();
                if (bArrM.length > i) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                httpURLConnection.disconnect();
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
            httpURLConnection.disconnect();
            throw th3;
        }
    }

    public static final void l(MainActivity mainActivity, boolean z) {
        int i = 0;
        if (!z) {
            SharedPreferences sharedPreferences = mainActivity.getSharedPreferences("panel", 0);
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (!mainActivity.getSharedPreferences("panel", 0).getBoolean("update_auto", true) || jCurrentTimeMillis - sharedPreferences.getLong("update_last_check", 0L) < 86400000 || jCurrentTimeMillis - sharedPreferences.getLong("update_last_try", 0L) < 3600000) {
                return;
            }
        }
        if (z) {
            mainActivity.v0(si.a("update_checking", new Object[0]));
        }
        Handler handler = new Handler(Looper.getMainLooper());
        mainActivity.getSharedPreferences("panel", 0).edit().putLong("update_last_try", System.currentTimeMillis()).apply();
        new Thread(new m0(mainActivity, handler, z, i)).start();
    }

    public static void m(int i) {
        if (2 > i || i >= 37) {
            throw new IllegalArgumentException("radix " + i + " was not in valid range " + new j8(2, 36, 1));
        }
    }

    public static final void n(int i, int i2) {
        String str;
        if (i <= 0 || i2 <= 0) {
            if (i != i2) {
                str = "Both size " + i + " and step " + i2 + " must be greater than zero.";
            } else {
                str = "size " + i + " must be greater than zero.";
            }
            throw new IllegalArgumentException(str.toString());
        }
    }

    public static final void o(final MainActivity mainActivity, final boolean z) {
        mainActivity.getClass();
        Set set = e3.a;
        final List listI0 = j3.i0(si.a("clock_show_both", new Object[0]), si.a("clock_show_time", new Object[0]), si.a("clock_show_date", new Object[0]));
        List list = e3.c;
        final List listI1 = j3.i0(si.a("clock_24h_0", e3.d((String) list.get(0))), si.a("clock_12h_0", e3.d((String) list.get(1))));
        List list2 = e3.d;
        final ArrayList arrayList = new ArrayList(k3.n0(list2));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(e3.a((String) it.next()));
        }
        final List listI2 = j3.i0(si.a("align_left", new Object[0]), si.a("align_center", new Object[0]), si.a("align_right", new Object[0]));
        final boolean z2 = mainActivity.getSharedPreferences("panel", 0).getBoolean(z ? "wall_".concat("clock_backing") : "clock_backing", false);
        c.e("done", new Object[0], new AlertDialog.Builder(mainActivity).setTitle(si.a("clock_settings", new Object[0])).setItems(new String[]{si.a("clock_show", new Object[0]) + ": " + listI0.get(s(e3.c(mainActivity, z, "clock_show"), 0, 2)), c.d(si.a("clock_time_format", new Object[0]), ": ", e3.d(e3.e(mainActivity, z))), c.d(si.a("clock_date_format", new Object[0]), ": ", e3.a(e3.b(mainActivity, z))), si.a("clock_align", new Object[0]) + ": " + listI2.get(s(e3.c(mainActivity, z, "clock_align"), 0, 2)), c.d(si.a("clock_backing", new Object[0]), ": ", si.a(z2 ? "clock_backing_on" : "clock_backing_off", new Object[0]))}, new DialogInterface.OnClickListener() { // from class: f3
            {
                Set set2 = e3.a;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                Set set2 = e3.a;
                MainActivity mainActivity2 = mainActivity;
                boolean z3 = z;
                if (i == 0) {
                    String strA = si.a("clock_show", new Object[0]);
                    mainActivity2.getClass();
                    pi.p(mainActivity2, z3, strA, listI0, "clock_show", pi.s(e3.c(mainActivity2, z3, "clock_show"), 0, 2));
                } else {
                    if (i == 1) {
                        pi.p(mainActivity2, z3, si.a("clock_time_format", new Object[0]), listI1, "clock_time_format", e3.c.indexOf(e3.e(mainActivity2, z3)));
                        return;
                    }
                    if (i == 2) {
                        pi.p(mainActivity2, z3, si.a("clock_date_format", new Object[0]), arrayList, "clock_date_format", e3.d.indexOf(e3.b(mainActivity2, z3)));
                        return;
                    }
                    if (i != 3) {
                        mainActivity2.B().edit().putBoolean(z3 ? "wall_".concat("clock_backing") : "clock_backing", !z2).apply();
                        mainActivity2.e0();
                        pi.o(mainActivity2, z3);
                    } else {
                        String strA2 = si.a("clock_align", new Object[0]);
                        mainActivity2.getClass();
                        pi.p(mainActivity2, z3, strA2, listI2, "clock_align", pi.s(e3.c(mainActivity2, z3, "clock_align"), 0, 2));
                    }
                }
            }
        }), null, mainActivity);
    }

    public static final void p(final MainActivity mainActivity, final boolean z, String str, List list, final String str2, int i) {
        Set set = e3.a;
        AlertDialog.Builder negativeButton = new AlertDialog.Builder(mainActivity).setTitle(str).setSingleChoiceItems((CharSequence[]) list.toArray(new String[0]), i, new DialogInterface.OnClickListener() { // from class: g3
            {
                Set set2 = e3.a;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                Set set2 = e3.a;
                MainActivity mainActivity2 = mainActivity;
                mainActivity2.getClass();
                SharedPreferences sharedPreferences = mainActivity2.getSharedPreferences("panel", 0);
                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                boolean z2 = z;
                String strConcat = str2;
                if (!z2 && !sharedPreferences.contains("wall_".concat(strConcat)) && sharedPreferences.contains(strConcat)) {
                    editorEdit.putInt("wall_".concat(strConcat), sharedPreferences.getInt(strConcat, 0));
                }
                if (z2) {
                    strConcat = "wall_".concat(strConcat);
                }
                editorEdit.putInt(strConcat, i2).apply();
                dialogInterface.dismiss();
                mainActivity2.e0();
                pi.o(mainActivity2, z2);
            }
        }).setNegativeButton(si.a("close", new Object[0]), new DialogInterface.OnClickListener() { // from class: h3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                pi.o(mainActivity, z);
            }
        });
        negativeButton.getClass();
        mainActivity.p0(negativeButton);
    }

    public static int q(int i) {
        if (i < 0) {
            return 0;
        }
        return i;
    }

    public static float r(float f, float f2, float f3) {
        if (f2 <= f3) {
            if (f < f2) {
                return f2;
            }
            return f > f3 ? f3 : f;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + f3 + " is less than minimum " + f2 + '.');
    }

    public static int s(int i, int i2, int i3) {
        if (i2 <= i3) {
            if (i < i2) {
                return i2;
            }
            return i > i3 ? i3 : i;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + i3 + " is less than minimum " + i2 + '.');
    }

    public static final String t(MainActivity mainActivity, float f) {
        mainActivity.getClass();
        return f > 0.5f ? String.format(si.a("1f_l100", new Object[0]), Arrays.copyOf(new Object[]{Float.valueOf(f)}, 1)) : "—";
    }

    public static final long u(InputStream inputStream, OutputStream outputStream) throws IOException {
        outputStream.getClass();
        byte[] bArr = new byte[8192];
        int i = inputStream.read(bArr);
        long j = 0;
        while (i >= 0) {
            outputStream.write(bArr, 0, i);
            j += (long) i;
            i = inputStream.read(bArr);
        }
        return j;
    }

    public static id v(uc ucVar, uc ucVar2, uc ucVar3) {
        String str;
        ucVar.getClass();
        String str2 = ucVar.c;
        int i = ucVar.b;
        int i2 = ucVar.a;
        id idVar = id.c;
        if (i2 <= 0 || i < 0 || str2 == null || th.A(str2)) {
            return idVar;
        }
        if (i2 > 0 && i >= 0 && str2 != null && !th.A(str2) && ucVar.equals(ucVar2)) {
            return id.a;
        }
        id idVar2 = id.b;
        if (ucVar2 == null || ucVar2.equals(ucVar3)) {
            return idVar2;
        }
        return (ucVar2.a == i2 && ucVar2.b == i && ((str = ucVar2.c) == null || th.A(str))) ? idVar2 : idVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.lang.Iterable] */
    /* JADX WARN: Type inference failed for: r1v7, types: [u4] */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.util.ArrayList] */
    public static String w(Context context) {
        ?? arrayList;
        context.getClass();
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        if (string == null) {
            string = "";
        }
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = "ReferencePanel-region:".concat(string).getBytes(q2.a);
        bytes.getClass();
        byte[] bArrDigest = messageDigest.digest(bytes);
        bArrDigest.getClass();
        int i = 0;
        if (6 >= bArrDigest.length) {
            int length = bArrDigest.length;
            if (length == 0) {
                arrayList = u4.a;
            } else if (length != 1) {
                arrayList = new ArrayList(bArrDigest.length);
                int length2 = bArrDigest.length;
                while (i < length2) {
                    arrayList.add(Byte.valueOf(bArrDigest[i]));
                    i++;
                }
            } else {
                arrayList = J(Byte.valueOf(bArrDigest[0]));
            }
        } else {
            arrayList = new ArrayList(6);
            int length3 = bArrDigest.length;
            int i2 = 0;
            while (i < length3) {
                arrayList.add(Byte.valueOf(bArrDigest[i]));
                i2++;
                if (i2 == 6) {
                    break;
                }
                i++;
            }
        }
        return i3.y0(th.u(i3.y0(arrayList, "", new w(26), 30)), "-", null, 62);
    }

    public static final boolean x(char c2, char c3, boolean z) {
        if (c2 == c3) {
            return true;
        }
        if (!z) {
            return false;
        }
        char upperCase = Character.toUpperCase(c2);
        char upperCase2 = Character.toUpperCase(c3);
        return upperCase == upperCase2 || Character.toLowerCase(upperCase) == Character.toLowerCase(upperCase2);
    }

    public static final String y(String str, TextPaint textPaint, float f) {
        str.getClass();
        return TextUtils.ellipsize(str, textPaint, Math.max(0.0f, f), TextUtils.TruncateAt.END).toString();
    }

    public static String z(String str, String str2) {
        str2.getClass();
        if (str.equals("DAB")) {
            String string = th.J(str2).toString();
            if (string.length() > 0) {
                return string;
            }
            return null;
        }
        Double dO = ai.o(ci.r(th.J(str2).toString(), ',', '.'));
        if (dO == null) {
            return null;
        }
        double dDoubleValue = dO.doubleValue();
        if (dDoubleValue <= 0.0d) {
            return null;
        }
        if (str.equals("AM")) {
            while (dDoubleValue > 3000.0d) {
                dDoubleValue /= 10.0d;
            }
            return String.valueOf(Math.round(dDoubleValue));
        }
        while (dDoubleValue > 200.0d) {
            dDoubleValue /= 10.0d;
        }
        return String.format(Locale.US, "%.1f", Arrays.copyOf(new Object[]{Double.valueOf(dDoubleValue)}, 1));
    }
}
