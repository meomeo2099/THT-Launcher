package org.reference.panel;

import android.app.ActivityManager;
import android.app.ActivityView;
import android.content.ComponentName;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class c extends ActivityView.StateCallback {
    public final /* synthetic */ NativeNavigationWindow.Listener a;
    public final /* synthetic */ NativeNavigationWindow b;

    public c(NativeNavigationWindow nativeNavigationWindow, NativeNavigationWindow.Listener listener) {
        this.a = listener;
        this.b = nativeNavigationWindow;
    }

    public void onActivityViewDestroyed(ActivityView activityView) {
        if (this.b.released) {
            return;
        }
        this.a.destroyed();
    }

    public void onActivityViewReady(ActivityView activityView) {
        if (this.b.released) {
            return;
        }
        this.a.ready();
    }

    public void onTaskMovedToFront(ActivityManager.StackInfo stackInfo) {
        int[] iArr;
        if (stackInfo == null || (iArr = stackInfo.taskIds) == null || iArr.length == 0) {
            return;
        }
        final int i = stackInfo.displayId;
        final int i2 = iArr[iArr.length - 1];
        ComponentName componentName = stackInfo.topActivity;
        final String packageName = componentName == null ? null : componentName.getPackageName();
        final NativeNavigationWindow.Listener listener = this.a;
        this.b.post(new Runnable() { // from class: lc
            @Override // java.lang.Runnable
            public final void run() {
                if (this.a.b.released) {
                    return;
                }
                listener.taskMovedToFront(i, i2, packageName);
            }
        });
    }
}
