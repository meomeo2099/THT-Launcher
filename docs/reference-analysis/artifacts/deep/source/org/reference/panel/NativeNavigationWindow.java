package org.reference.panel;

import android.app.ActivityView;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class NativeNavigationWindow extends FrameLayout {
    private float cornerRadius;
    private final ActivityView embedded;
    private final Region opaqueCorners;
    private final Path positionedClip;
    private boolean released;
    private final Region roundedWindow;
    private final Rect windowBounds;
    private final Path windowClip;
    private final int[] windowLocation;

    /* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
    public interface Listener {
        void destroyed();

        void ready();

        void taskMovedToFront(int i, int i2, String str);
    }

    public NativeNavigationWindow(Context context, Listener listener) {
        super(context);
        this.windowClip = new Path();
        this.positionedClip = new Path();
        this.windowBounds = new Rect();
        this.opaqueCorners = new Region();
        this.roundedWindow = new Region();
        this.windowLocation = new int[2];
        setDescendantFocusability(393216);
        setFocusable(false);
        setFocusableInTouchMode(false);
        setImportantForAccessibility(4);
        ActivityView activityView = new ActivityView(context);
        this.embedded = activityView;
        activityView.setFocusable(false);
        activityView.setFocusableInTouchMode(false);
        activityView.setDescendantFocusability(393216);
        activityView.setCallback(new c(this, listener));
        addView((View) activityView, new FrameLayout.LayoutParams(-1, -1));
    }

    private static SurfaceView surface(ViewGroup viewGroup) {
        SurfaceView surfaceViewSurface;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof SurfaceView) {
                return (SurfaceView) childAt;
            }
            if ((childAt instanceof ViewGroup) && (surfaceViewSurface = surface((ViewGroup) childAt)) != null) {
                return surfaceViewSurface;
            }
        }
        return null;
    }

    private void updateWindowClip() {
        float fMin = Math.min(this.cornerRadius, Math.min(getWidth(), getHeight()) / 2.0f);
        this.windowClip.reset();
        this.windowClip.addRoundRect(new RectF(0.0f, 0.0f, getWidth(), getHeight()), fMin, fMin, Path.Direction.CW);
        invalidate();
        if (getParent() != null) {
            getParent().requestTransparentRegion(this);
        }
    }

    public void close() {
        if (this.released) {
            return;
        }
        this.released = true;
        try {
            this.embedded.release();
        } finally {
            removeAllViews();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        int iSave = canvas.save();
        canvas.clipPath(this.windowClip);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(iSave);
    }

    @Override // android.view.View
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean gatherTransparentRegion(Region region) {
        boolean zGatherTransparentRegion = super.gatherTransparentRegion(region);
        if (region != null && this.cornerRadius > 0.0f && getWidth() > 0 && getHeight() > 0) {
            getLocationInWindow(this.windowLocation);
            int[] iArr = this.windowLocation;
            int i = iArr[0];
            int i2 = iArr[1];
            this.windowBounds.set(i, i2, getWidth() + i, getHeight() + i2);
            this.opaqueCorners.set(this.windowBounds);
            this.positionedClip.set(this.windowClip);
            this.positionedClip.offset(i, i2);
            this.roundedWindow.setPath(this.positionedClip, this.opaqueCorners);
            Region region2 = this.opaqueCorners;
            Region region3 = this.roundedWindow;
            Region.Op op = Region.Op.DIFFERENCE;
            region2.op(region3, op);
            region.op(this.opaqueCorners, op);
        }
        return zGatherTransparentRegion;
    }

    public void launch(Intent intent) {
        if (this.released) {
            throw new IllegalStateException("Preview already released");
        }
        this.embedded.startActivity(intent);
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        updateWindowClip();
    }

    public void setCornerRadius(float f) {
        this.cornerRadius = Math.max(0.0f, f);
        updateWindowClip();
    }

    public void showWindow(boolean z) {
        SurfaceView surfaceViewSurface = surface(this.embedded);
        if (surfaceViewSurface != null) {
            surfaceViewSurface.setZOrderMediaOverlay(z);
        }
        this.embedded.setVisibility(z ? 0 : 8);
    }
}
