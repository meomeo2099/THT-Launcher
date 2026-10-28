package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.util.Log;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class fe {
    public static final l0 o = new l0();
    public static volatile fe p;
    public final Context a;
    public final Handler b;
    public final Handler c;
    public final CopyOnWriteArraySet d;
    public volatile IBinder e;
    public volatile de f;
    public volatile boolean g;
    public boolean h;
    public boolean i;
    public final ce j;
    public final mb k;
    public final ee l;
    public final ae m;
    public final r1 n;

    /* JADX WARN: Type inference failed for: r2v6, types: [ce] */
    public fe(Context context) {
        this.a = context.getApplicationContext();
        HandlerThread handlerThread = new HandlerThread("ReferencePanel-tuner");
        handlerThread.start();
        this.b = new Handler(handlerThread.getLooper());
        this.c = new Handler(Looper.getMainLooper());
        this.d = new CopyOnWriteArraySet();
        this.j = new IBinder.DeathRecipient() { // from class: ce
            @Override // android.os.IBinder.DeathRecipient
            public final void binderDied() {
                fe feVar = this.a;
                feVar.b.post(new ae(feVar, 4));
            }
        };
        this.k = new mb(1, this);
        this.l = new ee(this);
        this.m = new ae(this, 3);
        this.n = new r1(11, this);
    }

    public final void a() {
        Object ffVar;
        mb mbVar = this.k;
        Context context = this.a;
        if (this.h || this.i) {
            return;
        }
        Intent component = new Intent("android.intent.action.START_XCRADIO_SERVICE").addCategory("android.intent.category.XCRadio").setComponent(new ComponentName("com.ecarx.radio.service", "com.ecarx.radio.service.RadioService"));
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
        boolean zBooleanValue = ((Boolean) ffVar).booleanValue();
        this.h = zBooleanValue;
        if (zBooleanValue) {
            return;
        }
        this.i = true;
        try {
            context.unbindService(mbVar);
        } catch (Throwable unused) {
        }
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
            parcelObtain.writeInterfaceToken("com.ecarx.radio.service.IRadioSvc");
            j6Var.b(parcelObtain);
            iBinder.transact(i, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            parcelObtain.recycle();
            return parcelObtain2;
        } catch (Throwable th) {
            try {
                Log.w("ReferencePanel.Tuner", "call " + i + " failed: " + th);
                parcelObtain2.recycle();
                return null;
            } finally {
                parcelObtain.recycle();
            }
        }
    }

    public final boolean c() {
        return this.e != null;
    }

    public final Integer d(int i) {
        Parcel parcelB = b(i, new w(25));
        if (parcelB != null) {
            try {
                Integer numValueOf = Integer.valueOf(parcelB.readInt());
                parcelB.recycle();
                return numValueOf;
            } catch (Throwable unused) {
                parcelB.recycle();
            }
        }
        return null;
    }

    public final void e() {
        i();
        Handler handler = this.b;
        ae aeVar = this.m;
        handler.postDelayed(aeVar, 500L);
        handler.postDelayed(aeVar, 1500L);
    }

    public final void f(String str) {
        IBinder iBinder = this.e;
        if (iBinder == null) {
            return;
        }
        Log.w("ReferencePanel.Tuner", "radio service lost: ".concat(str));
        try {
            iBinder.unlinkToDeath(this.j, 0);
        } catch (Throwable unused) {
        }
        this.e = null;
        this.g = false;
        if (k8.e(null, this.f)) {
            return;
        }
        this.f = null;
        this.c.post(new ae(this, 0));
    }

    public final boolean g() {
        Object ffVar;
        if (this.e != null) {
            if (!this.g) {
                Parcel parcelB = b(1, new a(11, this));
                if (parcelB != null) {
                    try {
                        ffVar = Boolean.valueOf(parcelB.readInt() != 0);
                    } catch (Throwable th) {
                        ffVar = new ff(th);
                    }
                    Object obj = Boolean.FALSE;
                    if (ffVar instanceof ff) {
                        ffVar = obj;
                    }
                    this.g = ((Boolean) ffVar).booleanValue();
                    parcelB.recycle();
                    if (!this.g) {
                        Log.w("ReferencePanel.Tuner", "openRadioAsync refused");
                    }
                }
            }
            return true;
        }
        if (!this.h && !this.i) {
            a();
            return false;
        }
        return false;
    }

    public final void h() {
        this.b.post(new ae(this, 2));
    }

    public final void i() {
        Integer numD;
        if (this.e == null || (numD = d(13)) == null) {
            return;
        }
        int iIntValue = numD.intValue();
        Integer numD2 = d(12);
        int iIntValue2 = numD2 != null ? numD2.intValue() : -1;
        Integer numD3 = d(20);
        int iIntValue3 = numD3 != null ? numD3.intValue() : -1;
        de deVar = null;
        boolValueOf = null;
        Boolean boolValueOf = null;
        if (iIntValue > 0) {
            String strN = l0.n();
            if (strN != null) {
                if (th.A(strN)) {
                    strN = null;
                }
                if (strN != null) {
                    boolValueOf = Boolean.valueOf(th.v(strN, "com.ecarx.radio.service", true));
                }
            }
            deVar = new de(iIntValue2, iIntValue, iIntValue3, boolValueOf);
        }
        if (k8.e(deVar, this.f)) {
            return;
        }
        this.f = deVar;
        this.c.post(new ae(this, 0));
    }

    /* JADX WARN: Code duplicated, block: B:32:0x0065  */
    /* JADX WARN: Code duplicated, block: B:34:0x0074  */
    public final void j(int i) {
        Parcel parcelB;
        Parcel parcelB2 = b(4, new zd(i, 1));
        if (parcelB2 != null) {
            parcelB2.recycle();
        }
        Integer numD = d(20);
        if ((numD != null && numD.intValue() == 4097) || ((numD != null && numD.intValue() == 8192) || ((numD != null && numD.intValue() == 8193) || (numD != null && numD.intValue() == 16384)))) {
            String strN = l0.n();
            Boolean boolValueOf = null;
            if (strN != null) {
                if (th.A(strN)) {
                    strN = null;
                }
                if (strN != null) {
                    boolValueOf = Boolean.valueOf(th.v(strN, "com.ecarx.radio.service", true));
                }
            }
            if (k8.e(boolValueOf, Boolean.FALSE)) {
                parcelB = b(17, new w(25));
                if (parcelB != null) {
                    parcelB.recycle();
                }
            }
        } else {
            parcelB = b(17, new w(25));
            if (parcelB != null) {
                parcelB.recycle();
            }
        }
        e();
    }

    public final void k(int i, String str) {
        int i2 = !k8.e(str, "AM") ? 1 : 0;
        Integer numD = d(12);
        if (numD == null || numD.intValue() == i2) {
            j(i);
            return;
        }
        Parcel parcelB = b(3, new zd(i2, 0));
        if (parcelB != null) {
            parcelB.recycle();
        }
        this.b.postDelayed(new t0(this, i, 8), 400L);
    }
}
