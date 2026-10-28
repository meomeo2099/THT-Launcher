package defpackage;

import android.telephony.PhoneStateListener;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class k2 extends PhoneStateListener {
    public final /* synthetic */ s1 a;

    public k2(s1 s1Var) {
        this.a = s1Var;
    }

    @Override // android.telephony.PhoneStateListener
    public final void onCallStateChanged(int i, String str) {
        this.a.f(i);
    }
}
