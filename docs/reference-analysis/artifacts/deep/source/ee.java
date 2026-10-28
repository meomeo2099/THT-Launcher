package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.util.Log;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class ee extends Binder implements IInterface {
    public final /* synthetic */ fe a;

    public ee(fe feVar) {
        this.a = feVar;
        attachInterface(this, "com.ecarx.radio.service.IRadioSvcCallback");
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        parcel.getClass();
        if (1 > i || i >= 21) {
            return super.onTransact(i, parcel, parcel2, i2);
        }
        fe feVar = this.a;
        try {
            parcel.enforceInterface("com.ecarx.radio.service.IRadioSvcCallback");
            if (i == 1 && parcel.readInt() == 0) {
                Log.w("ReferencePanel.Tuner", "tuner did not open");
                feVar.g = false;
            }
        } catch (Throwable unused) {
        }
        if (parcel2 != null) {
            parcel2.writeNoException();
        }
        fe feVar2 = this.a;
        feVar2.b.removeCallbacks(feVar2.m);
        fe feVar3 = this.a;
        feVar3.b.postDelayed(feVar3.m, 150L);
        return true;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }
}
