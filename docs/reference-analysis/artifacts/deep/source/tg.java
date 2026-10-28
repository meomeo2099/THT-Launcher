package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class tg extends View {
    public final Paint a;
    public float b;
    public int c;
    public final /* synthetic */ float d;
    public final /* synthetic */ WindowManager.LayoutParams e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Point g;
    public final /* synthetic */ int h;
    public final /* synthetic */ a i;
    public final /* synthetic */ WindowManager j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public tg(Context context, float f, WindowManager.LayoutParams layoutParams, int i, Point point, int i2, a aVar, WindowManager windowManager) {
        super(context);
        this.d = f;
        this.e = layoutParams;
        this.f = i;
        this.g = point;
        this.h = i2;
        this.i = aVar;
        this.j = windowManager;
        this.a = new Paint(1);
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        canvas.getClass();
        float width = getWidth() / 2.0f;
        float height = getHeight() * 0.18f;
        float f = this.d;
        float fMin = Math.min(height, 120.0f * f);
        int iArgb = Color.argb(isPressed() ? 235 : 160, 255, 255, 255);
        Paint paint = this.a;
        paint.setColor(iArgb);
        float f2 = fMin / 2.0f;
        canvas.drawRoundRect(width - (f * 3.0f), (getHeight() / 2.0f) - f2, (f * 3.0f) + width, (getHeight() / 2.0f) + f2, f * 3.0f, f * 3.0f, paint);
        if (isPressed()) {
            paint.setColor(Color.argb(110, 255, 255, 255));
            canvas.drawRect(width - (f * 1.0f), 0.0f, (f * 1.0f) + width, getHeight(), paint);
        }
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0040  */
    /* JADX WARN: Code duplicated, block: B:15:0x004c  */
    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int i;
        motionEvent.getClass();
        int actionMasked = motionEvent.getActionMasked();
        WindowManager.LayoutParams layoutParams = this.e;
        if (actionMasked == 0) {
            this.b = motionEvent.getRawX();
            this.c = layoutParams.x;
            setPressed(true);
            invalidate();
            return true;
        }
        WindowManager windowManager = this.j;
        Point point = this.g;
        int i2 = this.f;
        int i3 = this.h;
        if (actionMasked == 1) {
            setPressed(false);
            invalidate();
            i = point.x - i2;
            if (i < 1) {
                i = 1;
            }
            int i4 = i3 / 2;
            int iS = pi.s((layoutParams.x - ug.c.x) + i4, ((i * 20) / 100) + i2, ((i * 80) / 100) + i2);
            layoutParams.x = (iS - i4) + ug.c.x;
            try {
                windowManager.updateViewLayout(this, layoutParams);
            } catch (Throwable unused) {
            }
            this.i.b(Integer.valueOf(iS));
        } else if (actionMasked == 2) {
            layoutParams.x = pi.s((int) ((motionEvent.getRawX() - this.b) + (this.c - ug.c.x)), i2, point.x - i3) + ug.c.x;
            try {
                windowManager.updateViewLayout(this, layoutParams);
                return true;
            } catch (Throwable unused2) {
            }
        } else if (actionMasked == 3) {
            setPressed(false);
            invalidate();
            i = point.x - i2;
            if (i < 1) {
                i = 1;
            }
            int i5 = i3 / 2;
            int iS2 = pi.s((layoutParams.x - ug.c.x) + i5, ((i * 20) / 100) + i2, ((i * 80) / 100) + i2);
            layoutParams.x = (iS2 - i5) + ug.c.x;
            windowManager.updateViewLayout(this, layoutParams);
            this.i.b(Integer.valueOf(iS2));
        }
        return true;
    }
}
