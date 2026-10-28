package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.text.TextPaint;
import android.view.View;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class k9 extends View {
    public static final int[] m = {2, 1, 11, 12, 16, 17, 18};
    public final boolean a;
    public final boolean b;
    public final y5 c;
    public final Paint d;
    public final b7 e;
    public final Handler f;
    public boolean g;
    public boolean h;
    public Boolean i;
    public int j;
    public final r1 k;
    public boolean l;

    public k9(MainActivity mainActivity, boolean z, boolean z2, y5 y5Var) {
        super(mainActivity);
        this.a = z;
        this.b = z2;
        this.c = y5Var;
        this.d = new Paint(1);
        b7 b7Var = new b7(z, l8.m(mainActivity) * 26.0f, true);
        this.e = b7Var;
        this.f = new Handler(Looper.getMainLooper());
        this.k = new r1(6, this);
        setBackground(b7Var);
        setClickable(true);
        setFocusable(true);
        setLongClickable(true);
        setOnClickListener(new s(10, this));
        setOnLongClickListener(new l4(this, 3));
    }

    private final BluetoothAdapter getAdapter() {
        Object ffVar;
        try {
            BluetoothManager bluetoothManager = (BluetoothManager) getContext().getSystemService(BluetoothManager.class);
            ffVar = bluetoothManager != null ? bluetoothManager.getAdapter() : null;
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        return (BluetoothAdapter) (ffVar instanceof ff ? null : ffVar);
    }

    private final WifiManager getWifiManager() {
        return (WifiManager) getContext().getApplicationContext().getSystemService(WifiManager.class);
    }

    /* JADX WARN: Code duplicated, block: B:124:0x017d  */
    /* JADX WARN: Code duplicated, block: B:128:0x0187  */
    /* JADX WARN: Code duplicated, block: B:39:0x0083  */
    /* JADX WARN: Code duplicated, block: B:46:0x0096  */
    /* JADX WARN: Code duplicated, block: B:54:0x00ab  */
    public final void a() {
        Object ffVar;
        Object ffVar2;
        NetworkInfo networkInfo;
        boolean z;
        boolean z2;
        Object ffVar3;
        Object obj;
        boolean zBooleanValue;
        Object ffVar4;
        Object ffVar5;
        Object obj2;
        boolean zBooleanValue2;
        Object ffVar6;
        boolean z3;
        Object ffVar7;
        oi oiVar = new oi(Boolean.valueOf(this.g), Boolean.valueOf(this.h), this.i);
        boolean z4 = this.b;
        if (z4) {
            try {
                WifiManager wifiManager = getWifiManager();
                ffVar = Boolean.valueOf(wifiManager != null && wifiManager.isWifiEnabled());
            } catch (Throwable th) {
                ffVar = new ff(th);
            }
            Object obj3 = Boolean.FALSE;
            if (ffVar instanceof ff) {
                ffVar = obj3;
            }
            boolean zBooleanValue3 = ((Boolean) ffVar).booleanValue();
            this.g = zBooleanValue3;
            if (zBooleanValue3) {
                try {
                    ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(ConnectivityManager.class);
                    ffVar2 = Boolean.valueOf((connectivityManager == null || (networkInfo = connectivityManager.getNetworkInfo(1)) == null || !networkInfo.isConnected()) ? false : true);
                } catch (Throwable th2) {
                    ffVar2 = new ff(th2);
                }
                Object obj4 = Boolean.FALSE;
                if (ffVar2 instanceof ff) {
                    ffVar2 = obj4;
                }
                z = ((Boolean) ffVar2).booleanValue();
            }
            this.h = z;
        } else {
            BluetoothAdapter adapter = getAdapter();
            if (adapter != null) {
                try {
                    if (adapter.isEnabled()) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    ffVar3 = Boolean.valueOf(z2);
                } catch (Throwable th3) {
                    ffVar3 = new ff(th3);
                }
                obj = Boolean.FALSE;
                if (ffVar3 instanceof ff) {
                    ffVar3 = obj;
                }
                zBooleanValue = ((Boolean) ffVar3).booleanValue();
                this.g = zBooleanValue;
                if (!zBooleanValue && adapter != null) {
                    try {
                        Object objInvoke = adapter.getClass().getMethod("getConnectionState", null).invoke(adapter, null);
                        ffVar4 = objInvoke instanceof Integer ? (Integer) objInvoke : null;
                    } catch (Throwable th4) {
                        ffVar4 = new ff(th4);
                    }
                    if (ffVar4 instanceof ff) {
                        ffVar4 = null;
                    }
                    Integer num = (Integer) ffVar4;
                    if (num != null && num.intValue() == 2) {
                        zBooleanValue2 = true;
                        break;
                    }
                    int i = 0;
                    while (true) {
                        if (i < 7) {
                            try {
                                ffVar7 = Boolean.valueOf(adapter.getProfileConnectionState(m[i]) == 2);
                            } catch (Throwable th5) {
                                ffVar7 = new ff(th5);
                            }
                            Object obj5 = Boolean.FALSE;
                            if (ffVar7 instanceof ff) {
                                ffVar7 = obj5;
                            }
                            if (((Boolean) ffVar7).booleanValue()) {
                                zBooleanValue2 = true;
                                break;
                            }
                            i++;
                        } else {
                            try {
                                Set<BluetoothDevice> bondedDevices = adapter.getBondedDevices();
                                if (bondedDevices == null) {
                                    bondedDevices = x4.a;
                                }
                                if (!bondedDevices.isEmpty()) {
                                    Iterator<T> it = bondedDevices.iterator();
                                    while (true) {
                                        if (!it.hasNext()) {
                                            z3 = false;
                                            break;
                                        }
                                        BluetoothDevice bluetoothDevice = (BluetoothDevice) it.next();
                                        try {
                                            Object objInvoke2 = bluetoothDevice.getClass().getMethod("isConnected", null).invoke(bluetoothDevice, null);
                                            ffVar6 = objInvoke2 instanceof Boolean ? (Boolean) objInvoke2 : null;
                                        } catch (Throwable th6) {
                                            ffVar6 = new ff(th6);
                                        }
                                        if (ffVar6 instanceof ff) {
                                            ffVar6 = null;
                                        }
                                        if (k8.e(ffVar6, Boolean.TRUE)) {
                                            z3 = true;
                                            break;
                                        }
                                    }
                                    ffVar5 = Boolean.valueOf(z3);
                                    obj2 = Boolean.FALSE;
                                    if (ffVar5 instanceof ff) {
                                        ffVar5 = obj2;
                                    }
                                    zBooleanValue2 = ((Boolean) ffVar5).booleanValue();
                                    break;
                                }
                                z3 = false;
                                ffVar5 = Boolean.valueOf(z3);
                                obj2 = Boolean.FALSE;
                                if (ffVar5 instanceof ff) {
                                    ffVar5 = obj2;
                                }
                                zBooleanValue2 = ((Boolean) ffVar5).booleanValue();
                                break;
                            } catch (Throwable th7) {
                                ffVar5 = new ff(th7);
                            }
                        }
                    }
                    z = zBooleanValue2;
                }
                this.h = z;
            } else {
                z2 = false;
                ffVar3 = Boolean.valueOf(z2);
                obj = Boolean.FALSE;
                if (ffVar3 instanceof ff) {
                    ffVar3 = obj;
                }
                zBooleanValue = ((Boolean) ffVar3).booleanValue();
                this.g = zBooleanValue;
                if (!zBooleanValue) {
                }
                this.h = z;
            }
        }
        if (k8.e(this.i, Boolean.valueOf(this.g))) {
            this.i = null;
        }
        int i2 = b7.h;
        l8.h();
        this.e.a(-10233877, false);
        List list = si.a;
        setContentDescription(si.a("0_1_tap_to_2_hold_for", z4 ? "Wi-Fi" : "Bluetooth", b(), this.g ? si.a("turn_off", new Object[0]) : si.a("turn_on", new Object[0])));
        if (oiVar.equals(new oi(Boolean.valueOf(this.g), Boolean.valueOf(this.h), this.i))) {
            return;
        }
        invalidate();
    }

    public final String b() {
        if (k8.e(this.i, Boolean.TRUE)) {
            return si.a("turning_on", new Object[0]);
        }
        if (k8.e(this.i, Boolean.FALSE)) {
            return si.a("turning_off", new Object[0]);
        }
        if (this.h) {
            return si.a("connected", new Object[0]);
        }
        return this.g ? si.a("on_169", new Object[0]) : si.a("off", new Object[0]);
    }

    @Override // android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f.post(this.k);
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        this.f.removeCallbacksAndMessages(null);
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        boolean z;
        int iA;
        String str;
        canvas.getClass();
        super.onDraw(canvas);
        float width = getWidth();
        float height = getHeight();
        if (width <= 0.0f || height <= 0.0f) {
            return;
        }
        boolean z2 = this.l;
        boolean z3 = this.b;
        Paint paint = this.d;
        boolean z4 = this.a;
        if (z2) {
            float fMin = Math.min(width, height);
            float f = fMin * 0.84f;
            float f2 = (width - f) / 2.0f;
            float f3 = (height - f) / 2.0f;
            if (this.i != null) {
                iA = l8.l(l8.a(l8.a, z4), 150);
            } else if (this.h) {
                iA = l8.a(l8.f(), z4);
            } else {
                iA = this.g ? l8.a(l8.a, z4) : l8.k(z4);
            }
            int i = iA;
            if (this.h) {
                str = "connected";
            } else {
                str = this.g ? "on" : "off";
            }
            String str2 = str;
            l0 l0Var = ba.h;
            if (l0.j(canvas, z3 ? "wifi" : "bluetooth", str2, i, f2, f3, f)) {
                return;
            }
            Map map = i9.a;
            i9.a(canvas, z3 ? "wifi" : "bt", paint, i, f2, f3, f, 2.8f);
            if (this.i != null || this.g) {
                return;
            }
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStrokeWidth(0.08f * fMin);
            paint.setColor(i);
            float f4 = width / 2.0f;
            float f5 = fMin * 0.36f;
            float f6 = height / 2.0f;
            canvas.drawLine(f4 - f5, f6 - f5, f4 + f5, f6 + f5, paint);
            paint.setStyle(Paint.Style.FILL);
            return;
        }
        float fMin2 = Math.min(width / 173.0f, height / 101.0f);
        float f7 = 15.0f * fMin2;
        float f8 = fMin2 * 32.0f;
        Boolean bool = this.i;
        boolean zBooleanValue = bool != null ? bool.booleanValue() : this.g;
        paint.setStyle(Paint.Style.FILL);
        int iF = z3 ? l8.a : l8.f();
        Boolean bool2 = this.i;
        boolean zBooleanValue2 = bool2 != null ? bool2.booleanValue() : this.h;
        int iA2 = l8.a(iF, z4);
        paint.setColor(iF);
        if (this.i != null) {
            paint.setAlpha(150);
        }
        float f9 = f8 / 2.0f;
        float f10 = f7 + f9;
        canvas.drawCircle(f10, f10, f9, paint);
        paint.setAlpha(255);
        Map map2 = i9.a;
        float f11 = (0.18f * f8) + f7;
        i9.a(canvas, z3 ? "wifi" : "bt", paint, -1, f11, f11, 0.64f * f8, 2.8f);
        TextPaint textPaintY = pi.Y(l8.j(z4), 18.0f * fMin2, false);
        k8.F(canvas, pi.y(z3 ? "Wi-Fi" : "BT", textPaintY, ((width - (f7 * 2.0f)) - f8) - (26.0f * fMin2)), (10.0f * fMin2) + f7 + f8, c.a(textPaintY, 0.36f, f10), textPaintY);
        if (!z4 && this.i == null && this.h) {
            z = z4;
            iA2 = l8.a(l8.f(), z);
        } else {
            z = z4;
            if (!z && zBooleanValue) {
                iA2 = l8.a(l8.a, z);
            } else if (!zBooleanValue2 && !zBooleanValue) {
                iA2 = l8.k(z);
            }
        }
        float f12 = fMin2 * 14.0f;
        k8.F(canvas, b(), f7, height - (16.0f * fMin2), pi.Y(iA2, f12, true));
        i9.a(canvas, "chev", paint, l8.k(z), (width - f7) - f12, (height / 2.0f) - (fMin2 * 7.0f), f12, 2.6f);
    }
}
