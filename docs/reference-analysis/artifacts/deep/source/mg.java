package defpackage;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import java.util.List;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class mg implements y5 {
    public final /* synthetic */ int a;
    public final /* synthetic */ MainActivity b;

    public /* synthetic */ mg(MainActivity mainActivity, int i) {
        this.a = i;
        this.b = mainActivity;
    }

    @Override // defpackage.y5
    public final Object a() {
        Object ffVar;
        Object ffVar2;
        int i = this.a;
        int i2 = 0;
        MainActivity mainActivity = this.b;
        switch (i) {
            case 0:
                c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity).setMessage(si.a("delete_the_saved_copy_the_original_file", new Object[0])).setPositiveButton(si.a("delete", new Object[0]), new z1(mainActivity, 3)), null, mainActivity);
                return ti.a;
            case 1:
                uh.j(mainActivity);
                return ti.a;
            case 2:
                ti tiVar = ti.a;
                try {
                    mainActivity.startActivity(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + mainActivity.getPackageName())));
                    ffVar = tiVar;
                } catch (Throwable th) {
                    ffVar = new ff(th);
                }
                if (gf.a(ffVar) != null) {
                    mainActivity.v0(si.a("app_unavailable_on_this_head_unit", new Object[0]));
                }
                return tiVar;
            case 3:
                boolean z = q.a;
                if (q.a(mainActivity)) {
                    new Thread(new n(mainActivity.getApplicationContext(), i2)).start();
                } else {
                    mainActivity.v0(si.a("anton_popup_allow_hint", new Object[0]));
                }
                return ti.a;
            case 4:
                boolean z2 = q.a;
                mainActivity.getSharedPreferences("panel", 0).edit().remove("anton_x").remove("anton_y").apply();
                mainActivity.v0(si.a("anton_popup_reset_done", new Object[0]));
                return ti.a;
            case 5:
                ti tiVar2 = ti.a;
                try {
                    mainActivity.startActivity(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + mainActivity.getPackageName())));
                    ffVar2 = tiVar2;
                } catch (Throwable th2) {
                    ffVar2 = new ff(th2);
                }
                if (gf.a(ffVar2) != null) {
                    mainActivity.v0(si.a("app_unavailable_on_this_head_unit", new Object[0]));
                }
                return tiVar2;
            case 6:
                List list = y2.a;
                SharedPreferences sharedPreferencesB = mainActivity.B();
                sharedPreferencesB.getClass();
                SharedPreferences.Editor editorEdit = sharedPreferencesB.edit();
                for (String str : y2.a) {
                    List list2 = y2.a;
                    str.getClass();
                    editorEdit.remove("climate_group_".concat(str));
                }
                editorEdit.apply();
                mainActivity.e0();
                return ti.a;
            case 7:
                k8.H(mainActivity);
                k8.m(mainActivity);
                return ti.a;
            case 8:
                mainActivity.e0();
                return ti.a;
            case 9:
                mainActivity.e0();
                return ti.a;
            case 10:
                k8.H(mainActivity);
                mainActivity.X(new za(mainActivity, 23));
                return ti.a;
            case 11:
                if (!mainActivity.h) {
                    try {
                        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                        intent.setType("image/*");
                        intent.addCategory("android.intent.category.OPENABLE");
                        intent.addFlags(1);
                        mainActivity.startActivityForResult(intent, 49);
                    } catch (ActivityNotFoundException unused) {
                        mainActivity.v0(si.a("no_file_picker_on_this_head_unit", new Object[0]));
                    }
                    break;
                }
                return ti.a;
            case 12:
                Intent intent2 = new Intent("android.intent.action.OPEN_DOCUMENT");
                intent2.setType("*/*");
                intent2.addCategory("android.intent.category.OPENABLE");
                try {
                    mainActivity.startActivityForResult(intent2, 54);
                    break;
                } catch (ActivityNotFoundException unused2) {
                    List list3 = si.a;
                    mainActivity.v0(si.a("no_file_picker_on_this_head_unit", new Object[0]));
                }
                return ti.a;
            default:
                Intent launchIntentForPackage = mainActivity.getPackageManager().getLaunchIntentForPackage("ecarx.settings");
                if (launchIntentForPackage != null) {
                    mainActivity.Q(launchIntentForPackage);
                } else {
                    mainActivity.v0(si.a("stock_settings_not_found", new Object[0]));
                }
                return ti.a;
        }
    }
}
