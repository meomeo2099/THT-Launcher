package defpackage;

import android.view.WindowManager;
import android.widget.Toast;
import org.reference.panel.NavigationAssistService;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class mc implements y5 {
    public final /* synthetic */ int a;
    public final /* synthetic */ NavigationAssistService b;

    public /* synthetic */ mc(NavigationAssistService navigationAssistService, int i) {
        this.a = i;
        this.b = navigationAssistService;
    }

    @Override // defpackage.y5
    public final Object a() {
        int i = this.a;
        ti tiVar = ti.a;
        NavigationAssistService navigationAssistService = this.b;
        switch (i) {
            case 0:
                int i2 = NavigationAssistService.d;
                if (!navigationAssistService.performGlobalAction(1)) {
                    Toast.makeText(navigationAssistService, si.a("android_did_not_perform_the_back_action", new Object[0]), 0).show();
                }
                return tiVar;
            case 1:
                int i3 = NavigationAssistService.d;
                navigationAssistService.c();
                return tiVar;
            case 2:
                int i4 = NavigationAssistService.d;
                navigationAssistService.e();
                return tiVar;
            case 3:
                int i5 = NavigationAssistService.d;
                return navigationAssistService.getSharedPreferences("panel", 0);
            default:
                int i6 = NavigationAssistService.d;
                Object systemService = navigationAssistService.getSystemService("window");
                systemService.getClass();
                return (WindowManager) systemService;
        }
    }
}
