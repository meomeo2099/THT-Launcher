package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.text.TextPaint;
import android.view.View;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class m8 extends View {
    public final /* synthetic */ int a = 1;
    public final boolean b;
    public final boolean c;
    public final Object d;
    public final Object e;

    public m8(MainActivity mainActivity, boolean z, y5 y5Var, y5 y5Var2, y5 y5Var3) {
        super(mainActivity);
        this.b = z;
        this.d = y5Var;
        this.e = new Paint(1);
        this.c = mainActivity.getSharedPreferences("panel", 0).getBoolean("menu_labels", false);
        setBackground(new b7(z, l8.m(mainActivity) * 24.0f, true));
        setClickable(true);
        setFocusable(true);
        List list = si.a;
        setContentDescription(si.a("navigation_0_tap_to_open_hold_to", y5Var.a()));
        setOnClickListener(new m2(y5Var2, 7));
        setOnLongClickListener(new o7(y5Var3, 1));
    }

    public static final TextPaint a(int i, boolean z, float f, m8 m8Var, float f2, String str, float f3, boolean z2) {
        TextPaint textPaintY = pi.Y(i, f3, z2);
        if (!z) {
            textPaintY.setShadowLayer(0.06f * f, 0.0f, f * 0.015f, m8Var.b ? Integer.MIN_VALUE : -1711276033);
        }
        float fMeasureText = textPaintY.measureText(str);
        if (fMeasureText > f2) {
            textPaintY.setTextSize((textPaintY.getTextSize() * f2) / fMeasureText);
        }
        return textPaintY;
    }

    public static final void b(float f, Canvas canvas, int i, float f2, float f3, String str, TextPaint textPaint, float f4, float f5) {
        String strY = pi.y(str, textPaint, f - f5);
        Set set = e3.a;
        float fMeasureText = textPaint.measureText(strY);
        float f6 = f5 + f2;
        float f7 = f3 - f2;
        if (i == 1) {
            f6 = ((f6 + f7) - fMeasureText) / 2.0f;
        } else if (i == 2) {
            f6 = f7 - fMeasureText;
        }
        k8.F(canvas, strY, f6, f4, textPaint);
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        switch (this.a) {
            case 0:
                super.onAttachedToWindow();
                ((Handler) this.d).post((r1) this.e);
                break;
            default:
                super.onAttachedToWindow();
                break;
        }
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        switch (this.a) {
            case 0:
                ((Handler) this.d).removeCallbacks((r1) this.e);
                super.onDetachedFromWindow();
                break;
            default:
                super.onDetachedFromWindow();
                break;
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int iRgb;
        int i = this.a;
        boolean z = this.b;
        boolean z2 = this.c;
        switch (i) {
            case 0:
                canvas.getClass();
                float width = getWidth();
                float height = getHeight();
                if (width > 0.0f && height > 0.0f) {
                    boolean z3 = getBackground() != null;
                    float fMin = z3 ? Math.min(width, height) * 0.12f : 0.0f;
                    float f = fMin * 2.0f;
                    float f2 = height - f;
                    float f3 = width - f;
                    if (f2 > 0.0f && f3 > 0.0f) {
                        if (z) {
                            iRgb = -1;
                        } else {
                            int i2 = l8.a;
                            iRgb = Color.rgb(48, 52, 59);
                        }
                        Set set = e3.a;
                        Context context = getContext();
                        context.getClass();
                        int iS = pi.s(e3.c(context, z2, "clock_show"), 0, 2);
                        Context context2 = getContext();
                        context2.getClass();
                        int iS2 = pi.s(e3.c(context2, z2, "clock_align"), 0, 2);
                        Context context3 = getContext();
                        context3.getClass();
                        String strD = e3.d(e3.e(context3, z2));
                        Context context4 = getContext();
                        context4.getClass();
                        String strA = e3.a(e3.b(context4, z2));
                        float f4 = iS2 == 0 ? 0.02f * f2 : 0.0f;
                        if (iS == 1) {
                            TextPaint textPaintA = a(iRgb, z3, f2, this, f3, strD, 0.8f * f2, true);
                            b(f3, canvas, iS2, fMin, width, strD, textPaintA, c.a(textPaintA, 0.36f, height / 2.0f), 0.0f);
                        } else if (iS == 2) {
                            TextPaint textPaintA2 = a(iRgb, z3, f2, this, f3, strA, 0.34f * f2, true);
                            b(f3, canvas, iS2, fMin, width, strA, textPaintA2, c.a(textPaintA2, 0.36f, height / 2.0f), 0.0f);
                        } else {
                            boolean z4 = z3;
                            int i3 = iRgb;
                            TextPaint textPaintA3 = a(i3, z4, f2, this, f3, strD, 0.62f * f2, true);
                            TextPaint textPaintA4 = a(i3, z4, f2, this, f3, strA, 0.19f * f2, false);
                            float f5 = 0.09f * f2;
                            float fA = c.a(textPaintA3, 0.72f, (height - ((textPaintA4.getTextSize() * 0.72f) + c.a(textPaintA3, 0.72f, f5))) / 2.0f);
                            float f6 = fMin;
                            b(f3, canvas, iS2, f6, width, strD, textPaintA3, fA, 0.0f);
                            b(f3, canvas, iS2, f6, width, strA, textPaintA4, c.a(textPaintA4, 0.72f, fA + f5), f4);
                        }
                        break;
                    }
                }
                break;
            default:
                canvas.getClass();
                super.onDraw(canvas);
                float width2 = getWidth();
                float height2 = getHeight();
                if (width2 > 0.0f && height2 > 0.0f) {
                    float fMin2 = Math.min(width2 / 173.0f, height2 / 117.0f);
                    float f7 = z2 ? 44.0f * fMin2 : 0.0f;
                    float fMin3 = Math.min(width2 * 0.78f, (height2 - f7) * 0.78f);
                    float f8 = ((height2 - fMin3) - f7) / 2.0f;
                    Map map = i9.a;
                    i9.a(canvas, "nav", (Paint) this.e, l8.j(z), (width2 - fMin3) / 2.0f, f8, fMin3, 2.6f);
                    if (z2) {
                        TextPaint textPaintY = pi.Y(l8.j(z), 16.0f * fMin2, true);
                        Paint.Align align = Paint.Align.CENTER;
                        textPaintY.setTextAlign(align);
                        TextPaint textPaintY2 = pi.Y(l8.k(z), 12.0f * fMin2, false);
                        textPaintY2.setTextAlign(align);
                        float f9 = width2 - (24.0f * fMin2);
                        float f10 = f9 >= 0.0f ? f9 : 0.0f;
                        float textSize = textPaintY.getTextSize() + (fMin2 * 8.0f) + f8 + fMin3;
                        float f11 = width2 / 2.0f;
                        k8.F(canvas, pi.y(si.a("navigation", new Object[0]), textPaintY, f10), f11, textSize, textPaintY);
                        k8.F(canvas, pi.y((String) ((y5) this.d).a(), textPaintY2, f10), f11, c.a(textPaintY2, 1.5f, textSize), textPaintY2);
                    }
                    break;
                }
                break;
        }
    }

    public m8(MainActivity mainActivity, boolean z, boolean z2) {
        super(mainActivity);
        this.b = z;
        this.c = z2;
        this.d = new Handler(Looper.getMainLooper());
        this.e = new r1(5, this);
        setLongClickable(true);
        Set set = e3.a;
        if (mainActivity.getSharedPreferences("panel", 0).getBoolean(z2 ? "wall_".concat("clock_backing") : "clock_backing", false)) {
            setBackground(new n8(z, l8.m(mainActivity) * 24.0f));
        }
    }
}
