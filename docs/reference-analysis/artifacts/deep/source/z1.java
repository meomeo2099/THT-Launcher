package defpackage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.widget.EditText;
import android.widget.FrameLayout;
import java.util.ArrayList;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class z1 implements DialogInterface.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ MainActivity b;

    public /* synthetic */ z1(MainActivity mainActivity, int i) {
        this.a = i;
        this.b = mainActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        int i2 = this.a;
        int i3 = 0;
        MainActivity mainActivity = this.b;
        switch (i2) {
            case 0:
                try {
                    SQLiteDatabase writableDatabase = ((kh) mainActivity.R.a()).getWritableDatabase();
                    writableDatabase.delete("trips", null, null);
                    writableDatabase.delete("days", null, null);
                    writableDatabase.delete("voltage", null, null);
                    writableDatabase.delete("refuels", null, null);
                    break;
                } catch (Throwable unused) {
                }
                mainActivity.v0(si.a("statistics_cleared", new Object[0]));
                break;
            case 1:
                int i4 = MainActivity.E0;
                mainActivity.Q(new Intent("android.settings.action.MANAGE_WRITE_SETTINGS", Uri.parse("package:" + mainActivity.getPackageName())));
                break;
            case 2:
                int i5 = MainActivity.E0;
                mainActivity.Q(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
                break;
            case 3:
                int i6 = MainActivity.E0;
                mainActivity.o(mainActivity.E().a).delete();
                mainActivity.J();
                mainActivity.e0();
                break;
            case 4:
                mainActivity.B().edit().putInt("fuel_display", i).apply();
                ArrayList arrayList = mainActivity.z;
                int size = arrayList.size();
                while (i3 < size) {
                    Object obj = arrayList.get(i3);
                    i3++;
                    ((aj) obj).a(mainActivity.Z);
                }
                mainActivity.c0();
                dialogInterface.dismiss();
                break;
            case 5:
                uh.j(mainActivity);
                break;
            case 6:
                uh.j(mainActivity);
                break;
            case 7:
                mainActivity.getClass();
                float f = mainActivity.B().getFloat("tank_liters", 50.0f);
                EditText editText = new EditText(mainActivity);
                editText.setInputType(8194);
                editText.setText(f % 1.0f == 0.0f ? String.valueOf((int) f) : String.valueOf(f));
                editText.setSelection(editText.getText().length());
                FrameLayout frameLayout = new FrameLayout(mainActivity);
                frameLayout.setPadding(mainActivity.r(24), mainActivity.r(8), mainActivity.r(24), 0);
                frameLayout.addView(editText);
                c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity).setTitle(si.a("tank_capacity_liters", new Object[0])).setMessage(si.a("used_to_convert_percent_to_liters", new Object[0])).setView(frameLayout).setPositiveButton(si.a("save", new Object[0]), new gg(editText, mainActivity)), null, mainActivity);
                break;
            case 8:
                Intent launchIntentForPackage = mainActivity.getPackageManager().getLaunchIntentForPackage("ecarx.settings");
                if (launchIntentForPackage == null) {
                    mainActivity.v0(si.a("stock_vehicle_settings_not_found", new Object[0]));
                } else {
                    mainActivity.Q(launchIntentForPackage);
                }
                break;
            case 9:
                uh.j(mainActivity);
                break;
            default:
                uh.j(mainActivity);
                break;
        }
    }
}
