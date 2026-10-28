package defpackage;

import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
import java.util.WeakHashMap;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class pb implements j6 {
    public final /* synthetic */ int a;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ MainActivity c;

    public /* synthetic */ pb(boolean z, MainActivity mainActivity) {
        this.a = 1;
        this.b = z;
        this.c = mainActivity;
    }

    @Override // defpackage.j6
    public final Object b(Object obj) {
        int i = this.a;
        Set<String> set = x4.a;
        MainActivity mainActivity = this.c;
        ti tiVar = ti.a;
        boolean z = this.b;
        switch (i) {
            case 0:
                String str = (String) obj;
                str.getClass();
                MainActivity mainActivity2 = this.c;
                Set<String> stringSet = mainActivity2.B().getStringSet("desktop_hidden", set);
                if (stringSet == null) {
                    stringSet = set;
                }
                Set<String> setK0 = i3.K0(stringSet);
                if (str.equals("app")) {
                    new i0(mainActivity2, mainActivity2.z(), si.a("add_app_widget", new Object[0]), false, new pb(z, mainActivity2)).show();
                } else if (str.equals("image")) {
                    WeakHashMap weakHashMap = f8.a;
                    HashMap map = v7.a;
                    SharedPreferences sharedPreferencesB = mainActivity2.B();
                    sharedPreferencesB.getClass();
                    String str2 = z ? "wimg_" : "img_";
                    String string = UUID.randomUUID().toString();
                    string.getClass();
                    String strConcat = str2.concat(ci.s(string, "-", ""));
                    String str3 = z ? "wall_images" : "desktop_images";
                    SharedPreferences.Editor editorEdit = sharedPreferencesB.edit();
                    Set<String> stringSet2 = sharedPreferencesB.getStringSet(str3, set);
                    if (stringSet2 != null) {
                        set = stringSet2;
                    }
                    float f = 0.0f;
                    float f2 = 1.0f;
                    editorEdit.putStringSet(str3, zf.i0(set, strConcat)).putString(v7.b(strConcat), "v2|0|".concat(i3.y0(pi.J(new u7(f, f, f2, f2)), ";", new w(6), 30))).apply();
                    mainActivity2.e0();
                    pi.F(strConcat, mainActivity2);
                } else if (str.equals("dock")) {
                    setK0.remove("ios_dock");
                    mainActivity2.B().edit().putStringSet("desktop_hidden", setK0).apply();
                    mainActivity2.e0();
                } else if (str.startsWith("s:")) {
                    String strC = th.C(str, "s:");
                    String str4 = z ? "wall_splits" : "desktop_splits";
                    setK0.remove((z ? "wsplit_" : "split_").concat(strC));
                    SharedPreferences.Editor editorEdit2 = mainActivity2.B().edit();
                    Set<String> stringSet3 = mainActivity2.B().getStringSet(str4, set);
                    if (stringSet3 != null) {
                        set = stringSet3;
                    }
                    editorEdit2.putStringSet(str4, zf.i0(set, strC)).putStringSet("desktop_hidden", setK0).apply();
                    mainActivity2.e0();
                } else if (str.startsWith("a:")) {
                    String strC2 = th.C(str, "a:");
                    String str5 = z ? "wall_actions" : "desktop_actions";
                    setK0.remove((z ? "wall_action_" : "action_").concat(strC2));
                    SharedPreferences.Editor editorEdit3 = mainActivity2.B().edit();
                    Set<String> stringSet4 = mainActivity2.B().getStringSet(str5, set);
                    if (stringSet4 != null) {
                        set = stringSet4;
                    }
                    editorEdit3.putStringSet(str5, zf.i0(set, strC2)).putStringSet("desktop_hidden", setK0).apply();
                    mainActivity2.e0();
                } else if (!str.startsWith("v:")) {
                    String strC3 = th.C(str, "w:");
                    setK0.remove((z ? "wall_" : "ios_").concat(strC3));
                    SharedPreferences.Editor editorPutStringSet = mainActivity2.B().edit().putStringSet("desktop_hidden", setK0);
                    if (z) {
                        Set<String> stringSet5 = mainActivity2.B().getStringSet("wall_widgets", x0.Y0(new String[]{"clock", "car"}));
                        if (stringSet5 != null) {
                            set = stringSet5;
                        }
                        Set<String> setK1 = i3.K0(set);
                        setK1.add(strC3);
                        editorPutStringSet.putStringSet("wall_widgets", setK1);
                    }
                    if ((strC3.startsWith("degree_") || strC3.equals("battery_compact")) && !z && !mainActivity2.B().contains("desktop_ios_".concat(strC3))) {
                        editorPutStringSet.putString("desktop_ios_".concat(strC3), x0.V0(MainActivity.N(strC3), new w(12), 30));
                    }
                    if (strC3.equals("music_title") && !z && !mainActivity2.B().contains("desktop_ios_music_title")) {
                        editorPutStringSet.putString("desktop_ios_music_title", "196.0,470.0,690.0,70.0");
                    }
                    if (strC3.equals("temp") && !z && !mainActivity2.B().contains("desktop_ios_temp")) {
                        editorPutStringSet.putString("desktop_ios_temp", "902.0,439.0,338.0,117.0");
                    }
                    if (strC3.equals("automation") && !z && !mainActivity2.B().contains("desktop_ios_automation")) {
                        editorPutStringSet.putString("desktop_ios_automation", "902.0,306.0,161.0,117.0");
                    }
                    if (strC3.equals("stats") && !z && !mainActivity2.B().contains("desktop_ios_stats")) {
                        editorPutStringSet.putString("desktop_ios_stats", x0.V0(MainActivity.N("stats"), new w(13), 30));
                    }
                    if (strC3.equals("theme") && !z && !mainActivity2.B().contains("desktop_ios_theme")) {
                        editorPutStringSet.putString("desktop_ios_theme", x0.V0(MainActivity.N("theme"), new w(14), 30));
                    }
                    if (strC3.equals("gps") && !z && !mainActivity2.B().contains("desktop_ios_gps")) {
                        editorPutStringSet.putString("desktop_ios_gps", x0.V0(MainActivity.N("gps"), new w(15), 30));
                    }
                    if (x0.Y0(new String[]{"temp_passenger", "saver", "camera360", "folder", "fuel_compact", "wifi_mark", "bt_mark", "radio", "anton", "app_window"}).contains(strC3) && !z && !mainActivity2.B().contains("desktop_ios_".concat(strC3))) {
                        editorPutStringSet.putString("desktop_ios_".concat(strC3), x0.V0(MainActivity.N(strC3), new w(16), 30));
                    }
                    if (strC3.equals("roof_slider") && !z && !mainActivity2.B().contains("desktop_ios_roof_slider")) {
                        editorPutStringSet.putString("desktop_ios_roof_slider", "192.0,40.0,64.0,516.0");
                        if (!mainActivity2.B().contains("desktop_ios_nav")) {
                            editorPutStringSet.putString("desktop_ios_nav", "272.0,40.0,300.0,516.0");
                        }
                        if (!mainActivity2.B().contains("desktop_ios_music")) {
                            editorPutStringSet.putString("desktop_ios_music", "588.0,40.0,298.0,516.0");
                        }
                    }
                    if (strC3.equals("car")) {
                        editorPutStringSet.putBoolean("show_vehicle", true);
                    }
                    editorPutStringSet.apply();
                    mainActivity2.e0();
                } else {
                    String strC4 = th.C(str, "v:");
                    String str6 = z ? "wall_vehicle_cards" : "desktop_vehicle_cards";
                    Set<String> stringSet6 = mainActivity2.B().getStringSet(str6, set);
                    if (stringSet6 != null) {
                        set = stringSet6;
                    }
                    Set<String> setK2 = i3.K0(set);
                    setK2.add(strC4);
                    setK0.remove((z ? "wall_vehicle_" : "vehicle_").concat(strC4));
                    mainActivity2.B().edit().putStringSet(str6, setK2).putStringSet("desktop_hidden", setK0).apply();
                    mainActivity2.e0();
                }
                break;
            case 1:
                ComponentName componentName = (ComponentName) obj;
                componentName.getClass();
                String str7 = z ? "wextra_" : "extra_";
                String str8 = str7 + UUID.randomUUID();
                String str9 = z ? "wall_apps" : "desktop_apps";
                Set<String> stringSet7 = mainActivity.B().getStringSet(str9, set);
                if (stringSet7 != null) {
                    set = stringSet7;
                }
                Set<String> setK3 = i3.K0(set);
                setK3.add(str8);
                mainActivity.B().edit().putStringSet(str9, setK3).putString("desktop_app_".concat(str8), componentName.flattenToString()).apply();
                mainActivity.e0();
                break;
            case 2:
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                mainActivity.B().edit().putBoolean("navigation_assist", zBooleanValue).apply();
                if (zBooleanValue && !z) {
                    mainActivity.v0(si.a("enable_ReferencePanel_back_and_home_in_accessibility", new Object[0]));
                    mainActivity.Q(new Intent("android.settings.ACCESSIBILITY_SETTINGS"));
                }
                break;
            case 3:
                Uri uri = (Uri) obj;
                uri.getClass();
                k8.K0(mainActivity, uri, z);
                break;
            default:
                Uri uri2 = (Uri) obj;
                uri2.getClass();
                mainActivity.h = true;
                mainActivity.e0();
                mainActivity.b.execute(new ag(mainActivity, z, uri2, 0));
                break;
        }
        return tiVar;
    }

    public /* synthetic */ pb(int i, MainActivity mainActivity, boolean z) {
        this.a = i;
        this.c = mainActivity;
        this.b = z;
    }
}
