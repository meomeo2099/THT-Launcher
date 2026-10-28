package defpackage;

import android.content.ComponentName;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.view.Display;
import android.view.WindowManager;
import org.reference.panel.AutomationService;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class lg implements j6 {
    public final /* synthetic */ int a;
    public final /* synthetic */ MainActivity b;

    public /* synthetic */ lg(MainActivity mainActivity, int i) {
        this.a = i;
        this.b = mainActivity;
    }

    @Override // defpackage.j6
    public final Object b(Object obj) {
        Display defaultDisplay;
        switch (this.a) {
            case 0:
                MainActivity mainActivity = this.b;
                int iIntValue = ((Integer) obj).intValue();
                mainActivity.B().edit().putInt(mainActivity.A0, iIntValue).apply();
                if (iIntValue == 0) {
                    rg.b();
                    Point point = new Point();
                    WindowManager windowManager = (WindowManager) mainActivity.getSystemService(WindowManager.class);
                    if (windowManager != null && (defaultDisplay = windowManager.getDefaultDisplay()) != null) {
                        defaultDisplay.getRealSize(point);
                    }
                    ch chVarK = eh.k(true);
                    if (chVarK != null) {
                        Rect rect = chVarK.b;
                        int i = point.y;
                        if (i > 0 && rect.bottom < i) {
                            eh.g(new Rect(rect.left, rect.top, point.x, point.y), rect.right);
                        }
                    }
                }
                mainActivity.e0();
                return ti.a;
            case 1:
                MainActivity mainActivity2 = this.b;
                mainActivity2.B().edit().putInt("color_".concat(mainActivity2.E().a), ((Integer) obj).intValue()).apply();
                mainActivity2.J();
                mainActivity2.e0();
                return ti.a;
            case 2:
                MainActivity mainActivity3 = this.b;
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                mainActivity3.B().edit().putBoolean("anton_popup", zBooleanValue).apply();
                int i2 = AutomationService.c;
                k8.F0(mainActivity3);
                if (!zBooleanValue) {
                    boolean z = q.a;
                    q.f();
                }
                return ti.a;
            case 3:
                MainActivity mainActivity4 = this.b;
                Uri uri = (Uri) obj;
                uri.getClass();
                mainActivity4.h = true;
                mainActivity4.e0();
                mainActivity4.b.execute(new o(mainActivity4, uri, 10));
                return ti.a;
            case 4:
                MainActivity mainActivity5 = this.b;
                mainActivity5.B().edit().putBoolean("saver_on", ((Boolean) obj).booleanValue()).apply();
                mainActivity5.h0();
                mainActivity5.e0();
                return ti.a;
            case 5:
                this.b.B().edit().putBoolean("saver_parked", ((Boolean) obj).booleanValue()).apply();
                return ti.a;
            case 6:
                MainActivity mainActivity6 = this.b;
                mainActivity6.B().edit().putInt("saver_clock", ((Integer) obj).intValue()).apply();
                mainActivity6.e0();
                return ti.a;
            case 7:
                MainActivity mainActivity7 = this.b;
                ComponentName componentName = (ComponentName) obj;
                componentName.getClass();
                new i0(mainActivity7, mainActivity7.z(), si.a("split_right", new Object[0]), false, new q0(mainActivity7, componentName, 8)).show();
                return ti.a;
            case 8:
                MainActivity mainActivity8 = this.b;
                String str = (String) obj;
                str.getClass();
                mainActivity8.v0(str);
                return ti.a;
            case 9:
                return Integer.valueOf(this.b.r(((Integer) obj).intValue()));
            case 10:
                MainActivity mainActivity9 = this.b;
                String str2 = (String) obj;
                if (str2 != null) {
                    mainActivity9.v0(str2);
                }
                return ti.a;
            case 11:
                MainActivity mainActivity10 = this.b;
                String str3 = (String) obj;
                if (str3 != null) {
                    mainActivity10.v0(str3);
                }
                return ti.a;
            case 12:
                MainActivity mainActivity11 = this.b;
                String str4 = (String) obj;
                if (str4 != null) {
                    mainActivity11.v0(str4);
                }
                return ti.a;
            default:
                MainActivity mainActivity12 = this.b;
                String str5 = (String) obj;
                if (str5 != null) {
                    mainActivity12.v0(str5);
                }
                return ti.a;
        }
    }
}
