package defpackage;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;
import android.os.Parcel;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ScrollView;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class o implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ o(ye yeVar, Context context) {
        this.a = 7;
        this.c = yeVar;
        this.b = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object ffVar;
        Object ffVar2;
        Object interfaceDescriptor;
        Object ffVar3;
        String interfaceDescriptor2;
        Object ffVar4;
        Object ffVar5;
        int i = 8;
        switch (this.a) {
            case 0:
                Context context = (Context) this.b;
                v2 v2Var = (v2) this.c;
                boolean z = q.a;
                context.getClass();
                q.e(context, v2Var, 0, 8);
                return;
            case 1:
                v1 v1Var = (v1) this.b;
                IBinder iBinder = (IBinder) this.c;
                Parcel parcelObtain = Parcel.obtain();
                parcelObtain.getClass();
                Parcel parcelObtain2 = Parcel.obtain();
                parcelObtain2.getClass();
                try {
                    parcelObtain.writeInterfaceToken("ecarx.hvac.app.IOpenHvacAidlInterface");
                    boolean zTransact = iBinder.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    ffVar = Boolean.valueOf(zTransact);
                    break;
                } catch (Throwable th) {
                    ffVar = new ff(th);
                }
                Throwable thA = gf.a(ffVar);
                if (thA != null) {
                    Log.w("ReferencePanel.HvacClose", "close failed", thA);
                }
                Boolean bool = Boolean.FALSE;
                if (ffVar instanceof ff) {
                    ffVar = bool;
                }
                parcelObtain.recycle();
                parcelObtain2.recycle();
                v1Var.b((Boolean) ffVar);
                return;
            case 2:
                MainActivity mainActivity = (MainActivity) this.b;
                mainActivity.S = (di) this.c;
                mainActivity.T = SystemClock.elapsedRealtime();
                mainActivity.U = false;
                if (mainActivity.isDestroyed()) {
                    return;
                }
                mainActivity.c0();
                return;
            case 3:
                Float f = (Float) this.b;
                MainActivity mainActivity2 = (MainActivity) this.c;
                int i2 = MainActivity.E0;
                if (f == null || f.floatValue() * 3.6f <= 3.0f) {
                    mainActivity2.g();
                    return;
                } else {
                    mainActivity2.h0();
                    return;
                }
            case 4:
                IBinder iBinder2 = (IBinder) this.b;
                nb nbVar = (nb) this.c;
                if (iBinder2 != null) {
                    try {
                        interfaceDescriptor = iBinder2.getInterfaceDescriptor();
                    } catch (Throwable th2) {
                        ffVar2 = new ff(th2);
                    }
                    break;
                } else {
                    interfaceDescriptor = null;
                }
                ffVar2 = interfaceDescriptor;
                if (ffVar2 instanceof ff) {
                    ffVar2 = null;
                }
                String str = (String) ffVar2;
                if (iBinder2 != null && k8.e(str, "ecarx.xsf.mediacenter.IMediaCenterWidgetApiSvc")) {
                    nbVar.e = iBinder2;
                    nbVar.c();
                    return;
                }
                Log.w("ReferencePanel.Radio", "media centre widget API not offered (" + str + ")");
                nbVar.i = true;
                if (nbVar.g) {
                    try {
                        nbVar.b.unbindService(nbVar.j);
                        break;
                    } catch (Throwable unused) {
                    }
                }
                nbVar.g = false;
                nbVar.e = null;
                nbVar.e(null);
                return;
            case 5:
                fe feVar = (fe) this.b;
                yc ycVar = (yc) this.c;
                if (feVar.g()) {
                    Integer numD = feVar.d(13);
                    if ((numD != null ? numD.intValue() : 0) <= 0 && ycVar != null) {
                        feVar.k(((Number) ycVar.b).intValue(), (String) ycVar.a);
                        return;
                    }
                    Parcel parcelB = feVar.b(17, new w(25));
                    if (parcelB != null) {
                        parcelB.recycle();
                    }
                    feVar.e();
                    return;
                }
                return;
            case 6:
                IBinder iBinder3 = (IBinder) this.b;
                fe feVar2 = (fe) this.c;
                if (iBinder3 != null) {
                    try {
                        interfaceDescriptor2 = iBinder3.getInterfaceDescriptor();
                    } catch (Throwable th3) {
                        ffVar3 = new ff(th3);
                    }
                    break;
                } else {
                    interfaceDescriptor2 = null;
                }
                ffVar3 = interfaceDescriptor2;
                String str2 = (String) (ffVar3 instanceof ff ? null : ffVar3);
                if (iBinder3 != null && k8.e(str2, "com.ecarx.radio.service.IRadioSvc")) {
                    try {
                        iBinder3.linkToDeath(feVar2.j, 0);
                        break;
                    } catch (Throwable unused2) {
                    }
                    feVar2.e = iBinder3;
                    feVar2.i();
                    return;
                }
                Log.w("ReferencePanel.Tuner", "radio service not offered (" + str2 + ")");
                feVar2.i = true;
                if (feVar2.h) {
                    try {
                        feVar2.a.unbindService(feVar2.k);
                        break;
                    } catch (Throwable unused3) {
                    }
                }
                feVar2.h = false;
                feVar2.f("unbound");
                return;
            case 7:
                ye yeVar = (ye) this.c;
                try {
                    InputStream inputStreamOpen = ((Context) this.b).getAssets().open("geo/allowed.txt");
                    inputStreamOpen.getClass();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStreamOpen, q2.a);
                    try {
                        ffVar4 = l0.v(inputStreamReader);
                        inputStreamReader.close();
                    } catch (Throwable th4) {
                        try {
                            throw th4;
                        } catch (Throwable th5) {
                            k8.p(inputStreamReader, th4);
                            throw th5;
                        }
                    }
                } catch (Throwable th6) {
                    ffVar4 = new ff(th6);
                }
                Throwable thA2 = gf.a(ffVar4);
                if (thA2 != null) {
                    Log.w("ReferencePanel.Region", "borders not read", thA2);
                }
                yeVar.d.post(new k1(8, (xe) (ffVar4 instanceof ff ? null : ffVar4)));
                return;
            case 8:
                MainActivity mainActivity3 = (MainActivity) this.b;
                Object obj = this.c;
                mainActivity3.h = false;
                if (mainActivity3.isDestroyed()) {
                    return;
                }
                if (!(obj instanceof ff)) {
                    z9.d(mainActivity3);
                    z9.a = true;
                    mainActivity3.v0(si.a("font_installed_0", (String) obj));
                }
                Throwable thA3 = gf.a(obj);
                if (thA3 != null) {
                    AlertDialog.Builder title = new AlertDialog.Builder(mainActivity3).setTitle(si.a("font", new Object[0]));
                    String message = thA3.getMessage();
                    if (message == null) {
                        message = si.a("font_not_font", new Object[0]);
                    }
                    AlertDialog.Builder positiveButton = title.setMessage(message).setPositiveButton(si.a("ok", new Object[0]), (DialogInterface.OnClickListener) null);
                    positiveButton.getClass();
                    mainActivity3.p0(positiveButton);
                }
                mainActivity3.e0();
                return;
            case 9:
                ((ScrollView) this.b).scrollTo(0, ((MainActivity) this.c).t);
                return;
            default:
                MainActivity mainActivity4 = (MainActivity) this.b;
                try {
                    ffVar5 = x3.f(mainActivity4, (Uri) this.c);
                    break;
                } catch (Throwable th7) {
                    ffVar5 = new ff(th7);
                }
                mainActivity4.runOnUiThread(new o(mainActivity4, ffVar5, i));
                return;
        }
    }

    public /* synthetic */ o(ServiceConnection serviceConnection, IBinder iBinder, Object obj, int i) {
        this.a = i;
        this.b = iBinder;
        this.c = obj;
    }

    public /* synthetic */ o(Object obj, Object obj2, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }
}
