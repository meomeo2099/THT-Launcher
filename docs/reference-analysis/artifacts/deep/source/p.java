package defpackage;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class p implements Runnable {
    public final /* synthetic */ int a;

    public /* synthetic */ p(int i) {
        this.a = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CopyOnWriteArraySet copyOnWriteArraySet;
        Display defaultDisplay;
        boolean z = true;
        switch (this.a) {
            case 0:
                l0 l0Var = r4.C;
                Context context = q.e;
                if (context != null) {
                    r4 r4VarP = l0Var.p(context);
                    if (r4VarP != null && (copyOnWriteArraySet = r4VarP.w) != null) {
                        copyOnWriteArraySet.add(q.j);
                    }
                    boolean z2 = q.a;
                    q.b();
                    z = r4VarP != null && r4VarP.z;
                    Handler handler = q.d;
                    if (handler != null) {
                        handler.postDelayed(this, z ? 5000L : 1500L);
                    }
                    break;
                }
                break;
            default:
                Context context2 = rg.b;
                if (context2 != null) {
                    try {
                        ch chVarK = eh.k(true);
                        if (chVarK == null || !chVarK.c) {
                            chVarK = null;
                        }
                        if ((chVarK != null) != rg.f) {
                            if (chVarK == null) {
                                z = false;
                            }
                            rg.f = z;
                            if (!z) {
                                eh.d = null;
                            }
                            j6 j6Var = rg.e;
                            if (j6Var != null) {
                                j6Var.b(Boolean.valueOf(rg.f));
                            }
                        }
                        if (chVarK == null) {
                            rg.g = null;
                            ug.a();
                        } else {
                            Rect rect = chVarK.b;
                            y5 y5Var = rg.c;
                            Integer num = y5Var != null ? (Integer) y5Var.a() : null;
                            boolean zEquals = rect.equals(rg.g);
                            rg.g = new Rect(rect);
                            if (zEquals && num != null) {
                                if (rect.bottom != num.intValue()) {
                                    Point point = new Point();
                                    WindowManager windowManager = (WindowManager) context2.getSystemService(WindowManager.class);
                                    if (windowManager != null && (defaultDisplay = windowManager.getDefaultDisplay()) != null) {
                                        defaultDisplay.getRealSize(point);
                                    }
                                    eh.g(new Rect(rect.left, rect.top, point.x, num.intValue()), rect.right);
                                    rg.g = new Rect(rect.left, rect.top, rect.right, num.intValue());
                                } else {
                                    ch chVarK2 = eh.k(false);
                                    if (chVarK2 != null) {
                                        ch chVar = chVarK2.b.bottom != num.intValue() ? chVarK2 : null;
                                        if (chVar != null) {
                                            eh.g(new Rect(rect.left, rect.top, chVar.b.right, num.intValue()), rect.right);
                                        }
                                    }
                                }
                            }
                            Rect rect2 = rg.g;
                            if (rect2 != null) {
                                tg tgVar = ug.a;
                                ug.b(context2, rect2.right, rect2.top, rect2.bottom, rg.d, new a(12, context2));
                            }
                        }
                    } catch (Exception e) {
                        e = e;
                        Throwable cause = e.getCause();
                        if (cause != null) {
                            e = cause;
                        }
                        Log.w("ReferencePanelSplit", "split fit: " + e);
                        rg.b();
                    }
                }
                if (rg.b != null) {
                    rg.a.postDelayed(this, 700L);
                }
                break;
        }
    }
}
