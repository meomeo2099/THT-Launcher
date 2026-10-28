package org.reference.panel;

import android.content.ComponentName;
import defpackage.uc;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class a implements NativeNavigationWindow.Listener {
    public final /* synthetic */ b a;
    public final /* synthetic */ int b;

    public a(b bVar, int i) {
        this.a = bVar;
        this.b = i;
    }

    @Override // org.reference.panel.NativeNavigationWindow.Listener
    public final void destroyed() {
        b bVar = this.a;
        if (bVar.n || bVar.s != this.b) {
            return;
        }
        bVar.p = false;
        bVar.q = false;
        bVar.w = null;
        bVar.b();
    }

    @Override // org.reference.panel.NativeNavigationWindow.Listener
    public final void ready() {
        b bVar = this.a;
        if (bVar.n || bVar.s != this.b) {
            return;
        }
        bVar.p = true;
        bVar.k.removeCallbacks(bVar.H);
        bVar.getWidth();
        bVar.getHeight();
        int i = bVar.getResources().getDisplayMetrics().densityDpi;
        bVar.e();
    }

    @Override // org.reference.panel.NativeNavigationWindow.Listener
    public final void taskMovedToFront(int i, int i2, String str) {
        b bVar = this.a;
        if (!bVar.n && bVar.s == this.b && !bVar.u && bVar.q && bVar.v && bVar.isAttachedToWindow() && bVar.isShown() && ((Boolean) bVar.h.a()).booleanValue()) {
            if (str != null) {
                ComponentName componentName = bVar.r;
                if (!str.equals(componentName != null ? componentName.getPackageName() : null)) {
                    return;
                }
            }
            if (str == null) {
                ComponentName componentName2 = bVar.r;
                str = componentName2 != null ? componentName2.getPackageName() : null;
            }
            bVar.w = new uc(i, i2, str);
            bVar.i();
        }
    }
}
