package org.reference.panel;

import android.content.Context;
import defpackage.ff;
import defpackage.fi;
import defpackage.hh;
import defpackage.ti;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class Guard {
    public static final Guard INSTANCE = new Guard();
    private static final int NOMINAL_BROKEN = 81;
    private static volatile int cached;
    private static boolean loaded;

    static {
        Object ffVar;
        try {
            System.loadLibrary("dhguard");
            ffVar = ti.a;
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        loaded = !(ffVar instanceof ff);
        cached = Integer.MIN_VALUE;
    }

    private Guard() {
    }

    private final native int probe(Context context);

    private final native int resolve(int i);

    public final int id(int i) {
        Object ffVar;
        if (!loaded) {
            return 0;
        }
        try {
            ffVar = Integer.valueOf(resolve(i));
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        if (ffVar instanceof ff) {
            ffVar = 0;
        }
        return ((Number) ffVar).intValue();
    }

    public final int offset(Context context) {
        Object ffVar;
        context.getClass();
        int i = cached;
        if (i != Integer.MIN_VALUE) {
            return i;
        }
        fi fiVar = hh.a;
        Context applicationContext = context.getApplicationContext();
        applicationContext.getClass();
        try {
            ffVar = Boolean.valueOf(hh.a(applicationContext).equals("GENUINE"));
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        Object obj = Boolean.FALSE;
        if (ffVar instanceof ff) {
            ffVar = obj;
        }
        int i2 = ((Boolean) ffVar).booleanValue() ? 0 : NOMINAL_BROKEN;
        cached = i2;
        return i2;
    }
}
