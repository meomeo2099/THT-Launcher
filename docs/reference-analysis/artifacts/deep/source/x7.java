package defpackage;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.Iterator;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class x7 implements y5 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ x7(MainActivity mainActivity, AudioManager audioManager) {
        this.a = 4;
        this.b = audioManager;
    }

    @Override // defpackage.y5
    public final Object a() {
        Object ffVar;
        Object ffVar2;
        int i = this.a;
        ti tiVar = ti.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                Object obj2 = ((me) obj).b;
                if (obj2 != null) {
                    ((Dialog) obj2).dismiss();
                    return tiVar;
                }
                k8.H0("dialog");
                throw null;
            case 1:
                o9 o9Var = (o9) obj;
                k1 k1Var = o9Var.t;
                AudioManager audioManager = o9Var.r;
                try {
                    audioManager.adjustStreamVolume(3, audioManager.isStreamMute(3) ? 100 : -100, 0);
                    ffVar = tiVar;
                } catch (Throwable th) {
                    ffVar = new ff(th);
                }
                if (gf.a(ffVar) != null) {
                    Toast.makeText(o9Var.getContext(), si.a("could_not_change_the_volume", new Object[0]), 0).show();
                }
                o9Var.c();
                o9Var.removeCallbacks(k1Var);
                o9Var.postDelayed(k1Var, 250L);
                return tiVar;
            case 2:
                qf qfVar = (qf) obj;
                int i2 = MainActivity.E0;
                SharedPreferences.Editor editorEdit = qfVar.e.edit();
                Iterator it = qf.x.iterator();
                while (it.hasNext()) {
                    editorEdit.remove("saver_pos_" + ((String) it.next()));
                }
                editorEdit.apply();
                qfVar.m.clear();
                qfVar.invalidate();
                return tiVar;
            case 3:
                LinearLayout linearLayout = (LinearLayout) obj;
                int i3 = MainActivity.E0;
                linearLayout.setVisibility(linearLayout.getVisibility() == 0 ? 8 : 0);
                return tiVar;
            default:
                AudioManager audioManager2 = (AudioManager) obj;
                int i4 = MainActivity.E0;
                try {
                    ffVar2 = Float.valueOf(audioManager2.getStreamVolume(3));
                    break;
                } catch (Throwable th2) {
                    ffVar2 = new ff(th2);
                }
                return (Float) (ffVar2 instanceof ff ? null : ffVar2);
        }
    }

    public /* synthetic */ x7(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }
}
