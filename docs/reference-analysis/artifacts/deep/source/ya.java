package defpackage;

import android.content.ComponentName;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.provider.Settings;
import java.util.ArrayList;
import org.reference.panel.AutomationService;
import org.reference.panel.MainActivity;
import org.reference.panel.b;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ya implements j6 {
    public final /* synthetic */ int a;
    public final /* synthetic */ MainActivity b;

    public /* synthetic */ ya(MainActivity mainActivity, int i) {
        this.a = i;
        this.b = mainActivity;
    }

    @Override // defpackage.j6
    public final Object b(Object obj) {
        Object ffVar;
        int i = this.a;
        int i2 = 1;
        int i3 = 0;
        ti tiVar = ti.a;
        MainActivity mainActivity = this.b;
        switch (i) {
            case 0:
                String str = (String) obj;
                int i4 = MainActivity.E0;
                str.getClass();
                mainActivity.getSharedPreferences("navigation_private", 0).edit().putString("external_launch_diagnostics", th.I(8000, str)).apply();
                return tiVar;
            case 1:
                String str2 = (String) obj;
                int i5 = MainActivity.E0;
                if (str2 != null) {
                    mainActivity.v0(str2);
                }
                return tiVar;
            case 2:
                long jLongValue = ((Long) obj).longValue();
                int i6 = MainActivity.E0;
                mainActivity.y().w(jLongValue);
                return tiVar;
            case 3:
                Float f = (Float) obj;
                float fFloatValue = f.floatValue();
                mainActivity.w0 = f;
                mainActivity.x0 = SystemClock.elapsedRealtime() + 4000;
                mainActivity.B().edit().putFloat("last_brightness", fFloatValue).apply();
                if (Settings.System.canWrite(mainActivity)) {
                    try {
                        Settings.System.putInt(mainActivity.getContentResolver(), "screen_brightness_mode", 0);
                        Settings.System.putInt(mainActivity.getContentResolver(), "screen_brightness", ((int) (pi.r(fFloatValue, 0.0f, 1.0f) * 254.0f)) + 1);
                        break;
                    } catch (Throwable unused) {
                    }
                }
                nj njVarH = mainActivity.H();
                b2 b2Var = new b2(mainActivity, 10);
                njVarH.getClass();
                njVarH.b(new a(14, b2Var), new cf(fFloatValue, i2));
                return tiVar;
            case 4:
                long jLongValue2 = ((Long) obj).longValue();
                int i7 = MainActivity.E0;
                mainActivity.y().w(jLongValue2);
                return tiVar;
            case 5:
                String str3 = (String) obj;
                str3.getClass();
                return mainActivity.M(str3);
            case 6:
                String str4 = (String) obj;
                str4.getClass();
                return mainActivity.W(str4);
            case 7:
                mainActivity.B().edit().putInt("saver_clock_size", ((Integer) obj).intValue()).apply();
                mainActivity.e0();
                return tiVar;
            case 8:
                mainActivity.B().edit().putBoolean("saver_date", ((Boolean) obj).booleanValue()).apply();
                return tiVar;
            case 9:
                mainActivity.B().edit().putBoolean("saver_weather", ((Boolean) obj).booleanValue()).apply();
                return tiVar;
            case 10:
                mainActivity.B().edit().putBoolean("saver_track", ((Boolean) obj).booleanValue()).apply();
                return tiVar;
            case 11:
                mainActivity.B().edit().putBoolean("autostart", ((Boolean) obj).booleanValue()).apply();
                return tiVar;
            case 12:
                mainActivity.B().edit().putString("dock_alignment", (((Integer) obj).intValue() != 1 ? 0 : 1) != 0 ? "spread" : "left").apply();
                mainActivity.o0 = 0;
                mainActivity.e0();
                return tiVar;
            case 13:
                mainActivity.B().edit().putBoolean("fullscreen", ((Boolean) obj).booleanValue()).apply();
                mainActivity.d();
                return tiVar;
            case 14:
                mainActivity.B().edit().putString("navigation_assist_mode", ((Integer) obj).intValue() == 0 ? "gestures" : "buttons").apply();
                mainActivity.e0();
                return tiVar;
            case 15:
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                mainActivity.B().edit().putBoolean("suppress_climate_popup", zBooleanValue).apply();
                if (zBooleanValue) {
                    k8.i0(mainActivity);
                }
                return tiVar;
            case 16:
                mainActivity.B().edit().putBoolean("menu_labels", ((Boolean) obj).booleanValue()).apply();
                return tiVar;
            case 17:
                return Integer.valueOf(mainActivity.r(((Integer) obj).intValue()));
            case 18:
                mainActivity.B().edit().putBoolean("update_auto", ((Boolean) obj).booleanValue()).apply();
                return tiVar;
            case 19:
                int iIntValue = ((Integer) obj).intValue();
                mainActivity.B().edit().putInt("update_installer", iIntValue).apply();
                if (iIntValue == 1) {
                    try {
                        ffVar = mainActivity.getPackageManager().getActivityInfo(new ComponentName("com.teto.inputservice", "com.teto.inputservice.autoboot.ApkInstallActivity"), 0);
                    } catch (Throwable th) {
                        ffVar = new ff(th);
                    }
                    if (ffVar instanceof ff) {
                        mainActivity.v0(si.a("update_freetuga_missing", new Object[0]));
                    }
                    break;
                }
                mainActivity.e0();
                return tiVar;
            case 20:
                String str5 = (String) obj;
                str5.getClass();
                mainActivity.u = str5;
                mainActivity.s = null;
                mainActivity.t = 0;
                mainActivity.e0();
                return tiVar;
            case 21:
                mainActivity.B().edit().putInt(mainActivity.y0, ((Integer) obj).intValue()).apply();
                mainActivity.e0();
                return tiVar;
            case 22:
                mainActivity.B().edit().putBoolean("ios_collapsed", ((Boolean) obj).booleanValue()).apply();
                return tiVar;
            case 23:
                boolean zBooleanValue2 = ((Boolean) obj).booleanValue();
                SharedPreferences.Editor editorPutBoolean = mainActivity.B().edit().putBoolean("music_autoplay", zBooleanValue2);
                if (zBooleanValue2) {
                    editorPutBoolean.putBoolean("radio_autoplay", false);
                }
                editorPutBoolean.apply();
                if (zBooleanValue2) {
                    mainActivity.e0();
                }
                return tiVar;
            case 24:
                boolean zBooleanValue3 = ((Boolean) obj).booleanValue();
                SharedPreferences.Editor editorPutBoolean2 = mainActivity.B().edit().putBoolean("radio_autoplay", zBooleanValue3);
                if (zBooleanValue3) {
                    editorPutBoolean2.putBoolean("music_autoplay", false);
                }
                editorPutBoolean2.apply();
                if (zBooleanValue3) {
                    mainActivity.e0();
                }
                return tiVar;
            case 25:
                mainActivity.B().edit().putBoolean("call_pause_music", ((Boolean) obj).booleanValue()).apply();
                int i8 = AutomationService.c;
                k8.F0(mainActivity);
                return tiVar;
            case 26:
                mainActivity.B().edit().putBoolean("music_cover_swipe", ((Boolean) obj).booleanValue()).apply();
                return tiVar;
            case 27:
                boolean zBooleanValue4 = ((Boolean) obj).booleanValue();
                gc gcVarY = mainActivity.y();
                nb nbVar = gcVarY.u;
                gcVarY.y.edit().putBoolean("radio_widget_enabled", zBooleanValue4).apply();
                if (!zBooleanValue4) {
                    nbVar.d.post(new kb(nbVar, i2));
                } else if (gcVarY.l) {
                    nbVar.d.post(new kb(nbVar, 3));
                }
                return tiVar;
            case 28:
                boolean zBooleanValue5 = ((Boolean) obj).booleanValue();
                SharedPreferences sharedPreferencesB = mainActivity.B();
                ArrayList arrayList = mainActivity.w;
                sharedPreferencesB.edit().putBoolean("navigation_embedded", zBooleanValue5).apply();
                if (!zBooleanValue5) {
                    int size = arrayList.size();
                    while (i3 < size) {
                        Object obj2 = arrayList.get(i3);
                        i3++;
                        ((b) obj2).c();
                    }
                    arrayList.clear();
                }
                mainActivity.e0();
                return tiVar;
            default:
                mainActivity.B().edit().putBoolean("show_vehicle", ((Boolean) obj).booleanValue()).apply();
                mainActivity.e0();
                return tiVar;
        }
    }
}
