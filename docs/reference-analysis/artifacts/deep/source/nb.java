package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Parcel;
import android.util.Log;
import java.util.Set;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class nb {
    public static final Set l = x0.Y0(new Integer[]{3, 4, 11, 33, 34});
    public final ac a;
    public final Context b;
    public final HandlerThread c;
    public final Handler d;
    public volatile IBinder e;
    public volatile lb f;
    public boolean g;
    public boolean h;
    public boolean i;
    public final mb j;
    public final r1 k;

    public nb(MainActivity mainActivity, ac acVar) {
        this.a = acVar;
        this.b = mainActivity.getApplicationContext();
        HandlerThread handlerThread = new HandlerThread("ReferencePanel-mediacenter");
        handlerThread.start();
        this.c = handlerThread;
        this.d = new Handler(handlerThread.getLooper());
        this.j = new mb(0, this);
        this.k = new r1(9, this);
    }

    public static final String d(IBinder iBinder, int i) {
        String string;
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.getClass();
        Parcel parcelObtain2 = Parcel.obtain();
        parcelObtain2.getClass();
        try {
            parcelObtain.writeInterfaceToken("ecarx.xsf.mediacenter.IMusicPlaybackInfo");
            iBinder.transact(i, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            string = parcelObtain2.readString();
            parcelObtain.recycle();
            parcelObtain2.recycle();
        } catch (Throwable unused) {
            parcelObtain.recycle();
            parcelObtain2.recycle();
            string = null;
        }
        return string == null ? "" : string;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0070  */
    public final void a() {
        Object ffVar;
        Object ffVar2;
        boolean z;
        mb mbVar = this.j;
        Context context = this.b;
        if (this.g || this.i) {
            return;
        }
        Intent component = new Intent("ecarx.xsf.ACTION_MEDIA_CENTER_WIDGET_API_SERVICE").setComponent(new ComponentName("ecarx.xsf.mediacenter", "ecarx.xsf.mediacenter.MediaCenterService"));
        component.getClass();
        try {
            ffVar = Boolean.valueOf(context.bindService(component, mbVar, 1));
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        Object obj = Boolean.FALSE;
        if (ffVar instanceof ff) {
            ffVar = obj;
        }
        if (((Boolean) ffVar).booleanValue()) {
            z = true;
        } else {
            try {
                ffVar2 = Boolean.valueOf(context.bindService(new Intent("ecarx.xsf.ACTION_MEDIA_CENTER_WIDGET_API_SERVICE").setPackage("ecarx.xsf.mediacenter"), mbVar, 1));
            } catch (Throwable th2) {
                ffVar2 = new ff(th2);
            }
            Object obj2 = Boolean.FALSE;
            if (ffVar2 instanceof ff) {
                ffVar2 = obj2;
            }
            if (((Boolean) ffVar2).booleanValue()) {
                z = true;
            } else {
                z = false;
            }
        }
        this.g = z;
        if (z) {
            return;
        }
        this.i = true;
    }

    public final Parcel b(int i, j6 j6Var) {
        IBinder iBinder = this.e;
        if (iBinder == null) {
            return null;
        }
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.getClass();
        Parcel parcelObtain2 = Parcel.obtain();
        parcelObtain2.getClass();
        try {
            parcelObtain.writeInterfaceToken("ecarx.xsf.mediacenter.IMediaCenterWidgetApiSvc");
            j6Var.b(parcelObtain);
            iBinder.transact(i, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            parcelObtain.recycle();
            return parcelObtain2;
        } catch (Throwable th) {
            try {
                Log.w("ReferencePanel.Radio", "call " + i + " failed", th);
                parcelObtain2.recycle();
                return null;
            } finally {
                parcelObtain.recycle();
            }
        }
    }

    public final void c() {
        Parcel parcelB;
        Object ffVar;
        Integer numValueOf;
        if (!this.h || this.e == null || (parcelB = b(2, new w(10))) == null) {
            return;
        }
        try {
            ffVar = parcelB.readStrongBinder();
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        Integer numValueOf2 = null;
        if (ffVar instanceof ff) {
            ffVar = null;
        }
        IBinder iBinder = (IBinder) ffVar;
        parcelB.recycle();
        if (iBinder == null) {
            e(null);
            return;
        }
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.getClass();
        Parcel parcelObtain2 = Parcel.obtain();
        parcelObtain2.getClass();
        try {
            parcelObtain.writeInterfaceToken("ecarx.xsf.mediacenter.IMusicPlaybackInfo");
            iBinder.transact(9, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            numValueOf = Integer.valueOf(parcelObtain2.readInt());
            parcelObtain.recycle();
            parcelObtain2.recycle();
        } catch (Throwable unused) {
            parcelObtain.recycle();
            parcelObtain2.recycle();
            numValueOf = null;
        }
        int iIntValue = numValueOf != null ? numValueOf.intValue() : -1;
        String strD = d(iBinder, 5);
        String strD2 = d(iBinder, 6);
        String strD3 = d(iBinder, 2);
        Parcel parcelObtain3 = Parcel.obtain();
        parcelObtain3.getClass();
        Parcel parcelObtain4 = Parcel.obtain();
        parcelObtain4.getClass();
        try {
            parcelObtain3.writeInterfaceToken("ecarx.xsf.mediacenter.IMusicPlaybackInfo");
            iBinder.transact(11, parcelObtain3, parcelObtain4, 0);
            parcelObtain4.readException();
            numValueOf2 = Integer.valueOf(parcelObtain4.readInt());
        } catch (Throwable unused2) {
        }
        parcelObtain3.recycle();
        parcelObtain4.recycle();
        e(new lb(iIntValue, strD, strD2, strD3, (numValueOf2 != null ? numValueOf2.intValue() : -1) == 1, d(iBinder, 27), d(iBinder, 25)));
    }

    public final void e(lb lbVar) {
        if (k8.e(lbVar, this.f)) {
            return;
        }
        this.f = lbVar;
        this.a.a();
    }
}
