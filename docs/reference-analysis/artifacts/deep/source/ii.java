package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class ii extends AnimatorListenerAdapter {
    public final /* synthetic */ ji a;

    public ii(ji jiVar) {
        this.a = jiVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        animator.getClass();
        ji jiVar = this.a;
        jiVar.f = false;
        jiVar.b.a();
    }
}
