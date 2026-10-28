package defpackage;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.animation.DecelerateInterpolator;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class w3 implements View.OnTouchListener {
    public final y5 a;
    public final y5 b;
    public final y1 c;
    public final float d;
    public final float e;
    public float f;
    public float g;
    public boolean h;
    public boolean i;
    public VelocityTracker j;
    public long k;

    public w3(MainActivity mainActivity, y5 y5Var, y5 y5Var2, y1 y1Var) {
        this.a = y5Var;
        this.b = y5Var2;
        this.c = y1Var;
        this.d = mainActivity.getResources().getDisplayMetrics().density;
        this.e = ViewConfiguration.get(mainActivity).getScaledTouchSlop();
    }

    public static void a(View view) {
        view.animate().translationX(0.0f).alpha(1.0f).setDuration(180L).setInterpolator(new DecelerateInterpolator()).start();
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(final View view, MotionEvent motionEvent) {
        view.getClass();
        motionEvent.getClass();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f = motionEvent.getRawX();
            this.g = motionEvent.getRawY();
            this.h = false;
            this.i = false;
            VelocityTracker velocityTracker = this.j;
            if (velocityTracker != null) {
                velocityTracker.recycle();
            }
            VelocityTracker velocityTrackerObtain = VelocityTracker.obtain();
            velocityTrackerObtain.addMovement(motionEvent);
            this.j = velocityTrackerObtain;
            view.setPressed(true);
            return true;
        }
        if (actionMasked == 1) {
            VelocityTracker velocityTracker2 = this.j;
            if (velocityTracker2 != null) {
                velocityTracker2.addMovement(motionEvent);
            }
            float rawX = motionEvent.getRawX() - this.f;
            float rawY = motionEvent.getRawY() - this.g;
            view.setPressed(false);
            if (this.h) {
                VelocityTracker velocityTracker3 = this.j;
                if (velocityTracker3 != null) {
                    velocityTracker3.computeCurrentVelocity(1000);
                }
                VelocityTracker velocityTracker4 = this.j;
                float fAbs = Math.abs(velocityTracker4 != null ? velocityTracker4.getXVelocity() : 0.0f);
                float f = this.d;
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                int iY = k8.y(rawX / f, rawY / f, fAbs / f, view.getWidth() / f);
                if (iY == 0 || jElapsedRealtime - this.k < 700) {
                    a(view);
                } else {
                    this.k = jElapsedRealtime;
                    (iY > 0 ? this.a : this.b).a();
                    view.performHapticFeedback(1);
                    final float f2 = rawX < 0.0f ? -1.0f : 1.0f;
                    view.animate().translationX(view.getWidth() * f2 * 0.25f).alpha(0.0f).setDuration(140L).withEndAction(new Runnable() { // from class: v3
                        @Override // java.lang.Runnable
                        public final void run() {
                            float f3 = -f2;
                            View view2 = view;
                            view2.setTranslationX(f3 * view2.getWidth() * 0.12f);
                            view2.animate().translationX(0.0f).alpha(1.0f).setDuration(200L).setInterpolator(new DecelerateInterpolator()).start();
                        }
                    }).start();
                }
            } else if (!this.i) {
                view.performClick();
            }
            this.h = false;
            VelocityTracker velocityTracker5 = this.j;
            if (velocityTracker5 != null) {
                velocityTracker5.recycle();
            }
            this.j = null;
            return true;
        }
        if (actionMasked == 2) {
            VelocityTracker velocityTracker6 = this.j;
            if (velocityTracker6 != null) {
                velocityTracker6.addMovement(motionEvent);
            }
            float rawX2 = motionEvent.getRawX() - this.f;
            float rawY2 = motionEvent.getRawY() - this.g;
            if (!this.h && !this.i) {
                float fAbs2 = Math.abs(rawX2);
                float f3 = this.e;
                if (fAbs2 > f3 || Math.abs(rawY2) > f3) {
                    view.setPressed(false);
                    if (!((Boolean) this.c.a()).booleanValue() || Math.abs(rawX2) < Math.abs(rawY2) * 2.0f) {
                        this.i = true;
                    } else {
                        this.h = true;
                        ViewParent parent = view.getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                    }
                }
            }
            if (this.h) {
                view.setTranslationX(rawX2 * 0.35f);
            }
        } else if (actionMasked == 3) {
            view.setPressed(false);
            if (this.h) {
                a(view);
            }
            this.h = false;
            VelocityTracker velocityTracker7 = this.j;
            if (velocityTracker7 != null) {
                velocityTracker7.recycle();
            }
            this.j = null;
            return true;
        }
        return true;
    }
}
