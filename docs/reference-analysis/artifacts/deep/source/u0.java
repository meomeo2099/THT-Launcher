package defpackage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class u0 implements y5 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;

    public /* synthetic */ u0(yi yiVar, le leVar, o0 o0Var) {
        this.a = 8;
        this.d = yiVar;
        this.b = leVar;
        this.c = o0Var;
    }

    @Override // defpackage.y5
    public final Object a() {
        Object ffVar;
        int i = this.a;
        final int i2 = 0;
        ti tiVar = ti.a;
        Object obj = this.d;
        Object obj2 = this.c;
        Object obj3 = this.b;
        switch (i) {
            case 0:
                yi yiVar = (yi) obj;
                try {
                    l0.s((MainActivity) obj3, (File) obj2);
                    ffVar = tiVar;
                } catch (Throwable th) {
                    ffVar = new ff(th);
                }
                if (!(ffVar instanceof ff)) {
                    yiVar.c();
                }
                Throwable thA = gf.a(ffVar);
                if (thA != null) {
                    String message = thA.getMessage();
                    if (message == null) {
                        message = thA.getClass().getSimpleName();
                    }
                    yiVar.b(message, null);
                }
                break;
            case 1:
                MainActivity mainActivity = (MainActivity) obj3;
                pi.h((FrameLayout) obj2, mainActivity, (c9) obj);
                mainActivity.c0();
                break;
            case 2:
                AlertDialog.Builder negativeButton = new AlertDialog.Builder((MainActivity) obj3).setMessage(si.a("image_gallery_forget", new Object[0])).setPositiveButton(si.a("delete", new Object[0]), new c2((File) obj2, (me) obj, 3)).setNegativeButton(si.a("cancel", new Object[0]), (DialogInterface.OnClickListener) null);
                negativeButton.getClass();
                k8.w0(negativeButton);
                break;
            case 3:
                MainActivity mainActivity2 = (MainActivity) obj3;
                int i3 = MainActivity.E0;
                ((FrameLayout) obj2).removeView((FrameLayout) obj);
                mainActivity2.e0 = null;
                mainActivity2.d();
                mainActivity2.h0();
                break;
            case 4:
                MainActivity mainActivity3 = (MainActivity) obj3;
                final String str = (String) obj2;
                final c4 c4Var = (c4) obj;
                boolean z = mainActivity3.z();
                String strA = si.a("backing_transparency", new Object[0]);
                int i4 = zj.a;
                SharedPreferences sharedPreferencesB = mainActivity3.B();
                sharedPreferencesB.getClass();
                final int i5 = 1;
                ((Dialog) new u2(mainActivity3, z, strA, zj.c(sharedPreferencesB, zj.d(str)), new j6() { // from class: ig
                    @Override // defpackage.j6
                    public final Object b(Object obj4) {
                        int i6 = i2;
                        ti tiVar2 = ti.a;
                        String str2 = str;
                        c4 c4Var2 = c4Var;
                        int iIntValue = ((Integer) obj4).intValue();
                        switch (i6) {
                            case 0:
                                c4Var2.g(iIntValue, str2);
                                break;
                            default:
                                c4Var2.g(iIntValue, str2);
                                break;
                        }
                        return tiVar2;
                    }
                }, new j6() { // from class: ig
                    @Override // defpackage.j6
                    public final Object b(Object obj4) {
                        int i6 = i5;
                        ti tiVar2 = ti.a;
                        String str2 = str;
                        c4 c4Var2 = c4Var;
                        int iIntValue = ((Integer) obj4).intValue();
                        switch (i6) {
                            case 0:
                                c4Var2.g(iIntValue, str2);
                                break;
                            default:
                                c4Var2.g(iIntValue, str2);
                                break;
                        }
                        return tiVar2;
                    }
                }).c).show();
                break;
            case 5:
                MainActivity mainActivity4 = (MainActivity) obj3;
                mainActivity4.B().edit().putFloat("ui_scale", (((SeekBar) obj2).getProgress() + 75) / 100.0f).apply();
                ((Dialog) obj).dismiss();
                mainActivity4.e0();
                break;
            case 6:
                k8.j0((MainActivity) obj3, (Dialog) obj, (ld) obj2);
                break;
            case 7:
                MainActivity mainActivity5 = (MainActivity) obj3;
                String str2 = (String) obj;
                if (((List) obj2).size() < 8) {
                    k8.o(mainActivity5, str2, null);
                }
                break;
            case 8:
                le leVar = (le) obj3;
                ((yi) obj).a.getSharedPreferences("panel", 0).edit().putInt("update_installer", leVar.a).apply();
                ((o0) obj2).b(Integer.valueOf(leVar.a));
                break;
            default:
                MainActivity mainActivity6 = (MainActivity) obj3;
                bj bjVar = (bj) obj2;
                lg lgVar = (lg) obj;
                AlertDialog.Builder title = new AlertDialog.Builder(mainActivity6).setTitle(si.a("brightness_level", new Object[0]));
                List list = bjVar.g;
                ArrayList arrayList = new ArrayList(k3.n0(list));
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    int iIntValue = ((Number) it.next()).intValue();
                    List list2 = si.a;
                    arrayList.add(si.a("level_0", Integer.valueOf(iIntValue)));
                }
                AlertDialog.Builder items = title.setItems((CharSequence[]) arrayList.toArray(new String[0]), new sb(mainActivity6, bjVar, lgVar, 2));
                items.getClass();
                mainActivity6.p0(items);
                break;
        }
        return tiVar;
    }

    public /* synthetic */ u0(FrameLayout frameLayout, FrameLayout frameLayout2, MainActivity mainActivity) {
        this.a = 3;
        this.c = frameLayout;
        this.d = frameLayout2;
        this.b = mainActivity;
    }

    public /* synthetic */ u0(Object obj, MainActivity mainActivity, Object obj2, int i) {
        this.a = i;
        this.c = obj;
        this.b = mainActivity;
        this.d = obj2;
    }

    public /* synthetic */ u0(MainActivity mainActivity, Object obj, Object obj2, int i) {
        this.a = i;
        this.b = mainActivity;
        this.c = obj;
        this.d = obj2;
    }
}
