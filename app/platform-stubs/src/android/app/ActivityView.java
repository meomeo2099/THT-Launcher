package android.app;

import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;

/** Compile-only Android 9 API signatures. Never packaged in the application. */
public class ActivityView extends ViewGroup {
    public ActivityView(Context context) { super(context); }
    public void setCallback(StateCallback callback) { throw new UnsupportedOperationException(); }
    public void startActivity(Intent intent) { throw new UnsupportedOperationException(); }
    public void release() { throw new UnsupportedOperationException(); }
    @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {}
    public abstract static class StateCallback {
        public abstract void onActivityViewReady(ActivityView view);
        public abstract void onActivityViewDestroyed(ActivityView view);
    }
}
