package defpackage;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.RectF;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import org.reference.panel.b;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class s implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ s(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.a;
        int i2 = 1;
        Object obj = this.b;
        switch (i) {
            case 0:
                t tVar = (t) obj;
                oi oiVarB = t.b(tVar.getWidth(), tVar.getHeight());
                RectF rectF = (RectF) oiVarB.b;
                float fFloatValue = ((Number) oiVarB.c).floatValue();
                yc ycVarC = t.c(rectF);
                float fFloatValue2 = ((Number) ycVarC.a).floatValue();
                float fFloatValue3 = ((Number) ycVarC.b).floatValue();
                y5 y5Var = tVar.c;
                if (y5Var != null && tVar.g < rectF.top - (0.3f * fFloatValue) && tVar.f <= tVar.e.getRight() + fFloatValue) {
                    y5Var.a();
                } else {
                    j6 j6Var = tVar.d;
                    if (j6Var != null && tVar.g >= rectF.top - (fFloatValue * 0.5f)) {
                        float f = tVar.f - fFloatValue2;
                        float f2 = fFloatValue3 - fFloatValue2;
                        if (f2 < 1.0f) {
                            f2 = 1.0f;
                        }
                        j6Var.b(Integer.valueOf(pi.s((int) ((f / f2) * 8.0f), 0, 7) + 1));
                    } else {
                        y5 y5Var2 = tVar.b;
                        if (y5Var2 != null) {
                            y5Var2.a();
                        }
                    }
                }
                break;
            case 1:
                ((i0) obj).dismiss();
                break;
            case 2:
                ((b2) obj).a();
                break;
            case 3:
                b bVar = (b) obj;
                if (bVar.c.a() == null) {
                    bVar.d.a();
                    break;
                } else if (!bVar.n) {
                    if (bVar.l == null) {
                        bVar.o = false;
                    }
                    bVar.f.b(bVar);
                    break;
                }
                break;
            case 4:
                a7 a7Var = (a7) obj;
                if (a7Var.getContext().checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0) {
                    a7Var.a.a();
                    break;
                }
                break;
            case 5:
                ((q8) obj).d.b("power");
                break;
            case 6:
                ((b2) obj).a();
                break;
            case 7:
                ((za) obj).a();
                break;
            case 8:
                ((za) obj).a();
                break;
            case 9:
                ((c3) obj).a();
                break;
            case 10:
                k9 k9Var = (k9) obj;
                r1 r1Var = k9Var.k;
                Handler handler = k9Var.f;
                k9Var.a();
                Context context = k9Var.getContext();
                context.getClass();
                Boolean boolD0 = pi.d0(context, k9Var.b, k9Var.i);
                if (boolD0 == null) {
                    k9Var.c.a();
                } else {
                    k9Var.i = boolD0;
                    int i3 = k9Var.j + 1;
                    k9Var.j = i3;
                    handler.removeCallbacks(r1Var);
                    handler.postDelayed(r1Var, 400L);
                    handler.postDelayed(new t0(i3, k9Var, 3), 8000L);
                    k9Var.invalidate();
                }
                break;
            case 11:
                ((ua) obj).a();
                break;
            case 12:
                ((ua) obj).a();
                break;
            case 13:
                ((ua) obj).a();
                break;
            case 14:
                ((ua) obj).a();
                break;
            case 15:
                ji jiVar = (ji) obj;
                if (!jiVar.f) {
                    jiVar.f = true;
                    ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
                    valueAnimatorOfFloat.setDuration(260L);
                    valueAnimatorOfFloat.setInterpolator(new DecelerateInterpolator());
                    valueAnimatorOfFloat.addUpdateListener(new ge(jiVar, i2));
                    valueAnimatorOfFloat.addListener(new ii(jiVar));
                    valueAnimatorOfFloat.start();
                }
                break;
            default:
                ((w7) obj).a();
                break;
        }
    }
}
