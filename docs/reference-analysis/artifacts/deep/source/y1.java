package defpackage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.session.MediaController;
import android.os.Handler;
import android.widget.SeekBar;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.reference.panel.MainActivity;
import org.reference.panel.MusicNotificationListener;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class y1 implements y5 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ y1(Intent intent, MainActivity mainActivity) {
        this.a = 5;
        this.c = intent;
        this.b = mainActivity;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x01c0  */
    /* JADX WARN: Code duplicated, block: B:68:0x0147  */
    /* JADX WARN: Code duplicated, block: B:72:0x0151  */
    @Override // defpackage.y5
    public final Object a() {
        String packageName;
        Intent launchIntentForPackage;
        Object ffVar;
        Boolean bool;
        boolean z;
        Object obj;
        boolean z2;
        boolean z3;
        int i = this.a;
        boolean z4 = true;
        ti tiVar = ti.a;
        Object obj2 = this.c;
        Object obj3 = this.b;
        switch (i) {
            case 0:
                MainActivity mainActivity = (MainActivity) obj3;
                new i0(mainActivity, mainActivity.z(), si.a("choose_an_app", new Object[0]), false, new q0(mainActivity, (y5) obj2, z4 ? 1 : 0)).show();
                return tiVar;
            case 1:
                Handler handler = (Handler) obj3;
                k1 k1Var = (k1) obj2;
                handler.removeCallbacks(k1Var);
                handler.postDelayed(k1Var, 15000L);
                return tiVar;
            case 2:
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888);
                bitmapCreateBitmap.getClass();
                Canvas canvas = new Canvas(bitmapCreateBitmap);
                Map map = i9.a;
                i9.a(canvas, (String) obj3, (Paint) obj2, -1, 32.0f, 32.0f, 192.0f, 2.2f);
                return bitmapCreateBitmap;
            case 3:
                t9 t9Var = (t9) obj3;
                t9Var.setChecked(!t9Var.getChecked());
                ((j6) obj2).b(Boolean.valueOf(t9Var.getChecked()));
                return tiVar;
            case 4:
                o9 o9Var = (o9) obj2;
                if (((MainActivity) obj3).getSharedPreferences("panel", 0).getBoolean("music_cover_swipe", true)) {
                    jc jcVar = o9Var.v;
                    z4 = jcVar.a && jcVar.b;
                }
                return Boolean.valueOf(z4);
            case 5:
                Intent intent = (Intent) obj2;
                MainActivity mainActivity2 = (MainActivity) obj3;
                int i2 = MainActivity.E0;
                ComponentName component = intent.getComponent();
                if (component == null || (packageName = component.getPackageName()) == null || (launchIntentForPackage = mainActivity2.getPackageManager().getLaunchIntentForPackage(packageName)) == null) {
                    return null;
                }
                if (k8.e(launchIntentForPackage.getComponent(), intent.getComponent())) {
                    launchIntentForPackage = null;
                }
                if (launchIntentForPackage != null) {
                    return launchIntentForPackage.addFlags(270532608);
                }
                return null;
            case 6:
                y5 y5Var = (y5) obj2;
                Object obj4 = ((me) obj3).b;
                if (obj4 == null) {
                    k8.H0("dialog");
                    throw null;
                }
                ((Dialog) obj4).dismiss();
                y5Var.a();
                return tiVar;
            case 7:
                MediaController mediaController = (MediaController) obj3;
                xb xbVar = (xb) obj2;
                if (mediaController != null) {
                    try {
                        int iOrdinal = xbVar.ordinal();
                        if (iOrdinal == 0) {
                            mediaController.getTransportControls().play();
                        } else if (iOrdinal != 1) {
                            if (iOrdinal == 2) {
                                z2 = false;
                            } else if (iOrdinal == 3) {
                                mediaController.getTransportControls().skipToNext();
                            } else {
                                if (iOrdinal != 4) {
                                    throw new vc();
                                }
                                mediaController.getTransportControls().skipToPrevious();
                            }
                            ffVar = Boolean.valueOf(z2);
                            bool = Boolean.FALSE;
                            z = ffVar instanceof ff;
                            obj = ffVar;
                            if (z) {
                                obj = bool;
                            }
                            z3 = ((Boolean) obj).booleanValue();
                        } else {
                            mediaController.getTransportControls().pause();
                        }
                        z2 = true;
                        ffVar = Boolean.valueOf(z2);
                    } catch (Throwable th) {
                        ffVar = new ff(th);
                    }
                    bool = Boolean.FALSE;
                    z = ffVar instanceof ff;
                    obj = ffVar;
                    if (z) {
                        obj = bool;
                    }
                    if (((Boolean) obj).booleanValue()) {
                    }
                }
                return Boolean.valueOf(z3);
            case 8:
                String str = (String) obj3;
                xb xbVar2 = (xb) obj2;
                Object obj5 = MusicNotificationListener.a;
                str.getClass();
                hc hcVarL = k8.L(str);
                if (hcVarL == null) {
                    return new zb(false, false);
                }
                Map map2 = wb.a;
                int iOrdinal2 = xbVar2.ordinal();
                xb xbVar3 = xb.c;
                for (xb xbVar4 : (iOrdinal2 == 0 || iOrdinal2 == 1) ? j3.i0(xbVar2, xbVar3) : pi.J(xbVar2)) {
                    PendingIntent pendingIntent = (PendingIntent) hcVarL.h.get(xbVar4);
                    if (pendingIntent != null) {
                        try {
                            pendingIntent.send();
                            return new zb(true, xbVar4 == xbVar3);
                        } catch (PendingIntent.CanceledException | SecurityException unused) {
                            continue;
                        }
                    }
                }
                return new zb(false, false);
            case 9:
                u2 u2Var = (u2) obj3;
                ((j6) u2Var.b).b(Integer.valueOf(((SeekBar) obj2).getProgress()));
                ((Dialog) u2Var.c).dismiss();
                return tiVar;
            case 10:
                me meVar = (me) obj2;
                if (((Dialog) obj3).isShowing()) {
                    Object obj6 = meVar.b;
                    if (obj6 == null) {
                        k8.H0("refresh");
                        throw null;
                    }
                    ((y5) obj6).a();
                }
                return tiVar;
            case 11:
                String str2 = (String) obj2;
                Matcher matcher = ((Pattern) ((me) obj3).b).matcher(str2);
                matcher.getClass();
                if (matcher.find(0)) {
                    return new jb(matcher, str2);
                }
                return null;
            default:
                MainActivity mainActivity3 = (MainActivity) obj3;
                d7 d7Var = (d7) obj2;
                c.e("cancel", new Object[0], new AlertDialog.Builder(mainActivity3).setTitle(d7Var.b).setItems(new String[]{si.a("icon_set_choose", new Object[0]), si.a("delete", new Object[0])}, new c2(mainActivity3, d7Var, 4)), null, mainActivity3);
                return tiVar;
        }
    }

    public /* synthetic */ y1(Object obj, Object obj2, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }
}
