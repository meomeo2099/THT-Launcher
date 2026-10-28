package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class kg implements y5 {
    public final /* synthetic */ int a;
    public final /* synthetic */ MainActivity b;
    public final /* synthetic */ boolean c;

    public /* synthetic */ kg(int i, MainActivity mainActivity, boolean z) {
        this.a = i;
        this.b = mainActivity;
        this.c = z;
    }

    @Override // defpackage.y5
    public final Object a() {
        int i = this.a;
        ti tiVar = ti.a;
        boolean z = this.c;
        MainActivity mainActivity = this.b;
        switch (i) {
            case 0:
                if (!mainActivity.h) {
                    Intent intent = new Intent(z ? "android.intent.action.OPEN_DOCUMENT" : "android.intent.action.CREATE_DOCUMENT");
                    intent.setType(z ? "*/*" : "application/zip");
                    intent.addCategory("android.intent.category.OPENABLE");
                    if (!z) {
                        intent.putExtra("android.intent.extra.TITLE", "ReferencePanel-" + new SimpleDateFormat("yyyy-MM-dd-HHmm", Locale.ROOT).format(new Date()) + ".zip");
                    }
                    try {
                        mainActivity.startActivityForResult(intent, z ? 45 : 44);
                    } catch (ActivityNotFoundException unused) {
                        List list = si.a;
                        mainActivity.v0(si.a("no_file_picker_on_this_head_unit", new Object[0]));
                    }
                }
                break;
            default:
                Intent intent2 = new Intent(z ? "android.intent.action.OPEN_DOCUMENT" : "android.intent.action.CREATE_DOCUMENT");
                intent2.setType(z ? "*/*" : "application/zip");
                intent2.addCategory("android.intent.category.OPENABLE");
                if (!z) {
                    intent2.putExtra("android.intent.extra.TITLE", "ReferencePanel-icons-template.zip");
                }
                try {
                    mainActivity.startActivityForResult(intent2, z ? 50 : 51);
                } catch (ActivityNotFoundException unused2) {
                    List list2 = si.a;
                    mainActivity.v0(si.a("no_file_picker_on_this_head_unit", new Object[0]));
                }
                break;
        }
        return tiVar;
        return tiVar;
    }
}
