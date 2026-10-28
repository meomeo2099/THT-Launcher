package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class a7 extends View {
    public static final int l = Color.rgb(52, 199, 89);
    public static final int m = Color.rgb(255, 69, 58);
    public final y5 a;
    public final Paint b;
    public final Path c;
    public final Handler d;
    public final LocationManager e;
    public boolean f;
    public long g;
    public boolean h;
    public final fi i;
    public final x6 j;
    public final r1 k;

    public a7(Context context, y5 y5Var) {
        super(context);
        this.a = y5Var;
        this.b = new Paint(1);
        this.c = new Path();
        this.d = new Handler(Looper.getMainLooper());
        this.e = (LocationManager) context.getSystemService(LocationManager.class);
        this.i = new fi(new z6(context, 0));
        this.j = new x6(1, this);
        int i = 4;
        this.k = new r1(i, this);
        setClickable(true);
        setFocusable(true);
        setContentDescription(si.a("gps_off", new Object[0]));
        setOnClickListener(new s(i, this));
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0048  */
    public static final void a(a7 a7Var) {
        boolean z;
        Object ffVar;
        if (!a7Var.f) {
            a7Var.b();
        }
        if (a7Var.g == 0 || SystemClock.elapsedRealtime() - a7Var.g > 10000) {
            z = false;
        } else {
            try {
                LocationManager locationManager = a7Var.e;
                ffVar = locationManager != null ? Boolean.valueOf(locationManager.isProviderEnabled("gps")) : null;
            } catch (Throwable th) {
                ffVar = new ff(th);
            }
            if (k8.e(ffVar instanceof ff ? null : ffVar, Boolean.FALSE)) {
                z = false;
            } else {
                z = true;
            }
        }
        if (z == a7Var.h) {
            return;
        }
        a7Var.h = z;
        List list = si.a;
        a7Var.setContentDescription(si.a(z ? "gps_on" : "gps_off", new Object[0]));
        a7Var.invalidate();
    }

    private final boolean getGenuine() {
        return ((Boolean) this.i.a()).booleanValue();
    }

    public final void b() {
        LocationManager locationManager;
        if (this.f || (locationManager = this.e) == null || !getGenuine() || getContext().checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0) {
            return;
        }
        try {
            Location lastKnownLocation = locationManager.getLastKnownLocation("gps");
            if (lastKnownLocation != null) {
                long jCurrentTimeMillis = System.currentTimeMillis() - lastKnownLocation.getTime();
                if (0 <= jCurrentTimeMillis && jCurrentTimeMillis < 10001) {
                    this.g = SystemClock.elapsedRealtime() - jCurrentTimeMillis;
                }
            }
            this.e.requestLocationUpdates("gps", 1000L, 0.0f, this.j, Looper.getMainLooper());
            this.f = true;
        } catch (IllegalArgumentException | SecurityException unused) {
        }
    }

    @Override // android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        b();
        this.d.post(this.k);
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        this.d.removeCallbacksAndMessages(null);
        if (this.f) {
            try {
                LocationManager locationManager = this.e;
                if (locationManager != null) {
                    locationManager.removeUpdates(this.j);
                }
            } catch (Throwable unused) {
            }
            this.f = false;
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        canvas.getClass();
        super.onDraw(canvas);
        float fMin = Math.min(getWidth(), getHeight());
        if (fMin <= 0.0f) {
            return;
        }
        float width = getWidth() / 2.0f;
        float height = getHeight() / 2.0f;
        l0 l0Var = ba.h;
        boolean z = this.h;
        float f = fMin / 2.0f;
        float f2 = width - f;
        float f3 = height - f;
        if (l0.j(canvas, "gps", z ? "on" : "off", z ? l : m, f2, f3, fMin)) {
            return;
        }
        ba baVar = ba.k;
        if (baVar != null) {
            if (baVar.a(canvas, "gps", this.h ? l : m, f2, f3, fMin)) {
                return;
            }
        }
        float f4 = 0.27f * fMin;
        float f5 = height - (0.1f * fMin);
        float f6 = (fMin * 0.4f) + height;
        this.c.reset();
        Path path = this.c;
        Path.Direction direction = Path.Direction.CW;
        path.addCircle(width, f5, f4, direction);
        Path path2 = new Path();
        float f7 = 0.87f * f4;
        float f8 = (0.5f * f4) + f5;
        path2.moveTo(width - f7, f8);
        path2.lineTo(width, f6);
        path2.lineTo(f7 + width, f8);
        path2.close();
        this.c.op(path2, Path.Op.UNION);
        Path path3 = this.c;
        Path path4 = new Path();
        path4.addCircle(width, f5, f4 * 0.42f, direction);
        path3.op(path4, Path.Op.DIFFERENCE);
        this.b.setStyle(Paint.Style.FILL);
        this.b.setColor(this.h ? l : m);
        canvas.drawPath(this.c, this.b);
    }
}
