package defpackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.View;
import java.io.File;
import java.io.InputStream;
import java.util.LinkedHashMap;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class kf extends View {
    public final int a;
    public final boolean b;
    public final Paint c;
    public final Bitmap d;
    public final Bitmap e;

    public kf(MainActivity mainActivity, int i, boolean z, boolean z2) {
        Bitmap bitmap;
        Object ffVar;
        Object ffVar2;
        super(mainActivity);
        this.a = i;
        this.b = z;
        this.c = new Paint(1);
        Bitmap bitmap2 = null;
        if (z2) {
            LinkedHashMap linkedHashMap = tj.a;
            File fileA = ea.a(mainActivity, z);
            if (fileA.exists()) {
                String str = fileA.getAbsolutePath() + ":" + fileA.lastModified() + ":" + fileA.length();
                yc ycVar = (yc) linkedHashMap.get(Boolean.valueOf(z));
                if (!k8.e(ycVar != null ? (String) ycVar.a : null, str)) {
                    Boolean boolValueOf = Boolean.valueOf(z);
                    try {
                        ffVar2 = BitmapFactory.decodeFile(fileA.getAbsolutePath());
                    } catch (Throwable th) {
                        ffVar2 = new ff(th);
                    }
                    linkedHashMap.put(boolValueOf, new yc(str, ffVar2 instanceof ff ? null : ffVar2));
                }
                yc ycVar2 = (yc) linkedHashMap.get(Boolean.valueOf(z));
                bitmap = ycVar2 != null ? (Bitmap) ycVar2.b : bitmap;
            } else {
                linkedHashMap.remove(Boolean.valueOf(z));
            }
            bitmap = null;
        } else {
            bitmap = null;
        }
        this.d = bitmap;
        if (bitmap != null) {
            bitmap2 = bitmap;
        } else if (this.a == 0) {
            LinkedHashMap linkedHashMap2 = tj.a;
            boolean z3 = this.b;
            LinkedHashMap linkedHashMap3 = tj.b;
            if (linkedHashMap3.get(Boolean.valueOf(z3)) == null) {
                try {
                    InputStream inputStreamOpen = mainActivity.getAssets().open(z3 ? "backgrounds/waterfront.png" : "backgrounds/waterfront-day.png");
                    try {
                        Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpen);
                        k8.p(inputStreamOpen, null);
                        ffVar = bitmapDecodeStream;
                    } catch (Throwable th2) {
                        try {
                            throw th2;
                        } catch (Throwable th3) {
                            k8.p(inputStreamOpen, th2);
                            throw th3;
                        }
                    }
                } catch (Throwable th4) {
                    ffVar = new ff(th4);
                }
                Bitmap bitmap3 = (Bitmap) (ffVar instanceof ff ? null : ffVar);
                if (bitmap3 != null) {
                    linkedHashMap3.put(Boolean.valueOf(z3), bitmap3);
                }
            }
            bitmap2 = (Bitmap) linkedHashMap3.get(Boolean.valueOf(z3));
        }
        this.e = bitmap2;
    }

    public final void a(Canvas canvas, float f, float f2, int[] iArr, float[] fArr) {
        LinearGradient linearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, f2, iArr, fArr, Shader.TileMode.CLAMP);
        Paint paint = this.c;
        paint.setShader(linearGradient);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0.0f, 0.0f, f, f2, paint);
        paint.setShader(null);
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int i;
        int i2;
        int iArgb;
        int[] iArr;
        int i3;
        int iRgb;
        Canvas canvas2 = canvas;
        canvas2.getClass();
        float width = getWidth();
        float height = getHeight();
        boolean z = this.b;
        int iRgb2 = z ? Color.rgb(10, 15, 21) : Color.rgb(217, 228, 239);
        canvas2.drawColor(iRgb2);
        Paint paint = this.c;
        Bitmap bitmap = this.e;
        if (bitmap != null) {
            float fMax = Math.max(width / bitmap.getWidth(), height / bitmap.getHeight());
            float width2 = (width - (bitmap.getWidth() * fMax)) / 2.0f;
            float height2 = (height - (bitmap.getHeight() * fMax)) / 2.0f;
            paint.setFilterBitmap(true);
            canvas2.drawBitmap(bitmap, (Rect) null, new RectF(width2, height2, (bitmap.getWidth() * fMax) + width2, (bitmap.getHeight() * fMax) + height2), paint);
            if (this.d == null && z) {
                canvas2.drawColor(335874834);
                return;
            }
            return;
        }
        int i4 = this.a;
        if (i4 == 3) {
            a(canvas2, width, height, z ? new int[]{Color.rgb(11, 16, 38), Color.rgb(42, 27, 69), Color.rgb(122, 59, 82), Color.rgb(224, 121, 60)} : new int[]{Color.rgb(44, 111, 181), Color.rgb(111, 168, 220), Color.rgb(242, 178, 121), Color.rgb(253, 224, 181)}, new float[]{0.0f, 0.45f, 0.76f, 1.0f});
            float f = width * 0.68f;
            float f2 = height * 0.82f;
            float f3 = height * 0.26f;
            if (z) {
                i = 150;
                i2 = 255;
                iArgb = Color.argb(210, 255, 214, 150);
            } else {
                i = 150;
                i2 = 255;
                iArgb = Color.argb(235, 255, 246, 214);
            }
            paint.setShader(new RadialGradient(f, f2, f3, new int[]{iArgb, z ? Color.argb(40, i2, i, 90) : Color.argb(70, i2, 206, i), 0}, new float[]{0.0f, 0.45f, 1.0f}, Shader.TileMode.CLAMP));
            canvas2.drawCircle(f, f2, f3, paint);
            paint.setShader(null);
            paint.setColor(z ? 452972443 : 872415231);
            paint.setStrokeWidth(getResources().getDisplayMetrics().density * 2.0f);
            int i5 = 0;
            while (i5 < 5) {
                float f4 = ((i5 * 0.055f) + 0.7f) * height;
                canvas2.drawLine(width * 0.08f, f4, 0.94f * width, f4, paint);
                i5++;
                canvas2 = canvas;
            }
            return;
        }
        if (i4 == 4) {
            a(canvas, width, height, z ? new int[]{Color.rgb(4, 18, 30), Color.rgb(7, 41, 60), Color.rgb(12, 66, 87)} : new int[]{Color.rgb(191, 230, 242), Color.rgb(127, 198, 224), Color.rgb(47, 135, 168)}, new float[]{0.0f, 0.55f, 1.0f});
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeCap(Paint.Cap.ROUND);
            for (int i6 = 0; i6 < 4; i6++) {
                float f5 = i6;
                float f6 = ((0.13f * f5) + 0.52f) * height;
                paint.setStrokeWidth(((f5 * 0.004f) + 0.012f) * height);
                paint.setColor(z ? Color.argb((i6 * 8) + 26, 190, 230, 255) : Color.argb((i6 * 10) + 50, 255, 255, 255));
                Path path = new Path();
                path.moveTo((-width) * 0.05f, f6);
                float f7 = f6 - (0.06f * height);
                float f8 = 0.02f * height;
                path.cubicTo(width * 0.25f, f7, width * 0.45f, (height * 0.05f) + f6, width * 0.7f, f6 - f8);
                path.cubicTo(width * 0.85f, f7, width * 0.95f, f6 + f8, width * 1.05f, f6 - (0.01f * height));
                canvas.drawPath(path, paint);
            }
            paint.setStyle(Paint.Style.FILL);
            return;
        }
        if (i4 != 5) {
            if (i4 == 1) {
                iRgb = z ? Color.rgb(34, 46, 83) : Color.rgb(172, 191, 224);
            } else if (i4 != 2) {
                iRgb = z ? Color.rgb(42, 54, 65) : Color.rgb(184, 202, 215);
            } else {
                iRgb = z ? Color.rgb(25, 66, 65) : Color.rgb(157, 202, 197);
            }
            paint.setShader(new RadialGradient(width * 0.25f, height * 0.58f, Math.max(width, height) * 0.65f, iRgb, iRgb2, Shader.TileMode.CLAMP));
            canvas2.drawRect(0.0f, 0.0f, width, height, paint);
            paint.setShader(null);
            paint.setColor(z ? 212585967 : 410159522);
            paint.setStrokeWidth(getResources().getDisplayMetrics().density);
            for (int i7 = 0; i7 < 4; i7++) {
                float f9 = i7 * 0.09f;
                canvas.drawLine(0.0f, (f9 + 0.65f) * height, width * 0.62f, (f9 + 0.77f) * height, paint);
            }
            return;
        }
        if (z) {
            iArr = new int[]{Color.rgb(12, 15, 18), Color.rgb(23, 28, 34), Color.rgb(31, 38, 46)};
            i3 = 3;
        } else {
            iArr = new int[]{Color.rgb(233, 237, 242), Color.rgb(214, 221, 229), Color.rgb(196, 206, 216)};
            i3 = 3;
        }
        float[] fArr = new float[i3];
        // fill-array-data instruction
        fArr[0] = 0.0f;
        fArr[1] = 0.6f;
        fArr[2] = 1.0f;
        a(canvas, width, height, iArr, fArr);
        float[] fArr2 = new float[i3];
        // fill-array-data instruction
        fArr2[0] = 0.25f;
        fArr2[1] = 0.5f;
        fArr2[2] = 0.75f;
        paint.setShader(new LinearGradient(0.0f, height, width, 0.0f, new int[]{0, z ? Color.argb(28, 190, 215, 245) : Color.argb(150, 255, 255, 255), 0}, fArr2, Shader.TileMode.CLAMP));
        canvas.drawRect(0.0f, 0.0f, width, height, paint);
        paint.setShader(null);
        paint.setColor(z ? 245941472 : 587202559);
        paint.setStrokeWidth(getResources().getDisplayMetrics().density);
        for (int i8 = 0; i8 < 6; i8++) {
            float f10 = width * 0.1f;
            float f11 = i8 * 0.14f;
            canvas.drawLine(f10, (0.18f + f11) * height, 0.9f * width, (f11 + 0.1f) * height, paint);
        }
    }
}
