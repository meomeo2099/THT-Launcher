package defpackage;

import android.content.DialogInterface;
import android.net.Uri;
import android.widget.Toast;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c2 implements DialogInterface.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ c2(Object obj, Object obj2, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        int i2 = this.a;
        int i3 = 1;
        Object obj = this.c;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                ((j6) obj2).b(((List) obj).get(i));
                dialogInterface.dismiss();
                return;
            case 1:
                h2 h2Var = (h2) obj;
                File file = (File) ((ArrayList) obj2).get(i);
                if (file.isDirectory()) {
                    h2Var.a(file);
                    return;
                }
                j6 j6Var = h2Var.c;
                Uri uriFromFile = Uri.fromFile(file);
                uriFromFile.getClass();
                j6Var.b(uriFromFile);
                return;
            case 2:
                File file2 = (File) obj2;
                h2 h2Var2 = (h2) obj;
                if (!file2.canWrite()) {
                    Toast.makeText(h2Var2.a, si.a("cannot_write_to_this_folder", new Object[0]), 1).show();
                    h2Var2.a(file2);
                    return;
                }
                String str = h2Var2.e;
                if (str == null) {
                    str = "ReferencePanel-" + new SimpleDateFormat("yyyy-MM-dd-HHmmss", Locale.ROOT).format(new Date());
                }
                File file3 = new File(file2, str.concat(".zip"));
                while (file3.exists()) {
                    file3 = new File(file2, str + "-" + i3 + ".zip");
                    i3++;
                }
                j6 j6Var2 = h2Var2.c;
                Uri uriFromFile2 = Uri.fromFile(file3);
                uriFromFile2.getClass();
                j6Var2.b(uriFromFile2);
                return;
            case 3:
                File file4 = (File) obj2;
                me meVar = (me) obj;
                me meVar2 = k7.a;
                file4.getClass();
                me meVar3 = k7.a;
                String name = file4.getName();
                name.getClass();
                if (meVar3.a(name)) {
                    file4.delete();
                }
                Object obj3 = meVar.b;
                if (obj3 != null) {
                    ((y5) obj3).a();
                    return;
                } else {
                    k8.H0("fill");
                    throw null;
                }
            case 4:
                MainActivity mainActivity = (MainActivity) obj2;
                String str2 = ((d7) obj).a;
                if (i == 0) {
                    me meVar4 = i7.a;
                    i7.g(mainActivity, str2);
                } else {
                    me meVar5 = i7.a;
                    if (i7.a(mainActivity).equals(str2)) {
                        i7.g(mainActivity, "");
                    }
                    m5.i0(i7.b(mainActivity, str2));
                }
                mainActivity.e0();
                return;
            case 5:
                lg lgVar = (lg) obj;
                nj njVarH = ((MainActivity) obj2).H();
                njVarH.getClass();
                if (i == 0) {
                    njVarH.b(lgVar, new ej(njVarH, 2));
                    return;
                } else if (i != 1) {
                    njVarH.b(lgVar, new ej(njVarH, i3));
                    return;
                } else {
                    njVarH.b(lgVar, new ej(njVarH, 5));
                    return;
                }
            default:
                lg lgVar2 = (lg) obj;
                nj njVarH2 = ((MainActivity) obj2).H();
                boolean z = i == 0;
                njVarH2.getClass();
                njVarH2.b(lgVar2, new dj(z, njVarH2, i3));
                return;
        }
    }
}
