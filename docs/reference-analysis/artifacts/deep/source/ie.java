package defpackage;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.animation.DecelerateInterpolator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class ie extends View {
    public static final List D = j3.i0("fav", "prev", "play", "next");
    public long A;
    public float B;
    public float C;
    public final boolean a;
    public final boolean b;
    public final Paint c;
    public final RectF d;
    public final Path e;
    public final Path f;
    public final Paint g;
    public String h;
    public String i;
    public boolean j;
    public boolean k;
    public Bitmap l;
    public boolean m;
    public j6 n;
    public final ArrayList o;
    public boolean p;
    public int q;
    public boolean r;
    public final k1 s;
    public final float t;
    public final float u;
    public float v;
    public float w;
    public boolean x;
    public boolean y;
    public VelocityTracker z;

    public ie(Context context, boolean z, boolean z2) {
        super(context);
        this.a = z;
        this.b = z2;
        this.c = new Paint(1);
        this.d = new RectF();
        this.e = new Path();
        this.f = new Path();
        this.g = new Paint(3);
        this.h = si.a("radio", new Object[0]);
        this.i = "";
        this.m = z2;
        int size = D.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(new RectF());
        }
        this.o = arrayList;
        this.q = -1;
        this.s = new k1(7, this);
        this.t = context.getResources().getDisplayMetrics().density;
        this.u = ViewConfiguration.get(context).getScaledTouchSlop();
        if (this.b) {
            setBackground(new n8(this.a, l8.m(context) * 22.0f));
            setClickable(true);
            setFocusable(true);
            setLongClickable(true);
        }
        setContentDescription(this.h);
    }

    public static void a(ie ieVar, ValueAnimator valueAnimator) {
        valueAnimator.getClass();
        Object animatedValue = valueAnimator.getAnimatedValue();
        animatedValue.getClass();
        ieVar.setCoverShift(((Float) animatedValue).floatValue());
    }

    public static final void b(ie ieVar, float f, float f2, Canvas canvas, float f3, float f4) {
        Path path = ieVar.e;
        path.reset();
        float f5 = f4 * f;
        float f6 = f3 - (0.5f * f5);
        float f7 = f * 0.62f;
        path.moveTo(f6, f2 - f7);
        path.lineTo((f5 * 0.55f) + f3, f2);
        path.lineTo(f6, f2 + f7);
        path.close();
        canvas.drawPath(path, ieVar.c);
    }

    public static final ArrayList c(int i, float f, float f2, float f3) {
        j8 j8VarF0 = pi.f0(0, i);
        ArrayList arrayList = new ArrayList(k3.n0(j8VarF0));
        Iterator it = j8VarF0.iterator();
        while (true) {
            i8 i8Var = (i8) it;
            if (!i8Var.c) {
                return arrayList;
            }
            arrayList.add(new yc(Float.valueOf((2.5f * f * i8Var.nextInt()) + f2 + f), Float.valueOf(f3)));
        }
    }

    public static final float d(float f, int i) {
        return (f * 0.5f * (i - 1)) + (2.0f * f * i);
    }

    public static /* synthetic */ void f(ie ieVar, lb lbVar, Bitmap bitmap, int i) {
        if ((i & 2) != 0) {
            bitmap = null;
        }
        ieVar.e(lbVar, bitmap, false);
    }

    private final void setCoverShift(float f) {
        this.B = f;
        invalidate();
    }

    public final void e(lb lbVar, Bitmap bitmap, boolean z) {
        String strA;
        String strA2;
        String str;
        if (!this.m) {
            bitmap = null;
        }
        if (lbVar == null || !lbVar.c()) {
            lbVar = null;
        }
        String string = (lbVar == null || (str = lbVar.c) == null) ? null : th.J(str).toString();
        if (string == null) {
            string = "";
        }
        String strB = lbVar != null ? lbVar.b() : null;
        String str2 = strB != null ? strB : "";
        boolean z2 = false;
        if (th.A(string)) {
            strA = th.A(str2) ? si.a("radio", new Object[0]) : str2;
        } else {
            strA = string;
        }
        if (lbVar == null) {
            strA2 = si.a("radio_tap_to_start", new Object[0]);
        } else {
            boolean z3 = lbVar.e;
            if (th.A(string)) {
                Object[] objArr = new Object[0];
                strA2 = z3 ? si.a("playing", objArr) : si.a("paused", objArr);
            } else {
                List list = si.a;
                strA2 = c.d(str2, " · ", si.a(z3 ? "playing" : "paused", new Object[0]));
            }
        }
        boolean z4 = lbVar != null && lbVar.e;
        if (z && lbVar != null) {
            z2 = true;
        }
        if (k8.e(strA, this.h) && k8.e(strA2, this.i) && z4 == this.j && z2 == this.k && bitmap == this.l) {
            return;
        }
        this.h = strA;
        this.i = strA2;
        this.j = z4;
        this.k = z2;
        this.l = bitmap;
        setContentDescription(strA + " " + strA2);
        invalidate();
    }

    public final void g() {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(this.B, 0.0f);
        valueAnimatorOfFloat.setDuration(180L);
        valueAnimatorOfFloat.setInterpolator(new DecelerateInterpolator());
        valueAnimatorOfFloat.addUpdateListener(new ge(this, 0));
        valueAnimatorOfFloat.start();
    }

    public final j6 getControl() {
        return this.n;
    }

    public final boolean getCovers() {
        return this.m;
    }

    public final void h(Canvas canvas, float f, float f2, float f3, int i) {
        Canvas canvas2;
        int i2;
        Bitmap bitmap = this.l;
        if (bitmap != null) {
            this.d.set(f, f2, f + f3, f2 + f3);
            this.f.reset();
            float f4 = 0.18f * f3;
            this.f.addRoundRect(this.d, f4, f4, Path.Direction.CW);
            canvas.save();
            canvas.clipPath(this.f);
            this.c.setStyle(Paint.Style.FILL);
            this.c.setColor(bitmap.getPixel(0, 0) | (-16777216));
            canvas.drawRect(this.d, this.c);
            float fMin = Math.min(f3 / bitmap.getWidth(), f3 / bitmap.getHeight());
            float width = bitmap.getWidth() * fMin;
            float height = bitmap.getHeight() * fMin;
            this.d.set(((f3 - width) / 2.0f) + f, ((f3 - height) / 2.0f) + f2, ((width + f3) / 2.0f) + f, ((f3 + height) / 2.0f) + f2);
            canvas.drawBitmap(bitmap, (Rect) null, this.d, this.g);
            canvas.restore();
            return;
        }
        l0 l0Var = ba.h;
        ba baVar = ba.k;
        if (baVar != null) {
            i2 = i;
            canvas2 = canvas;
            if (baVar.a(canvas, "radio", i2, f, f2, f3)) {
                return;
            }
        } else {
            canvas2 = canvas;
            i2 = i;
        }
        this.c.setColor(i2);
        this.c.setStyle(Paint.Style.STROKE);
        this.c.setStrokeWidth(0.07f * f3);
        this.c.setStrokeCap(Paint.Cap.ROUND);
        float f5 = 0.34f * f3;
        float f6 = f2 + f5;
        float f7 = 0.72f * f3;
        float f8 = f3 * 0.1f;
        canvas2.drawLine((0.3f * f3) + f, f6, f + f7, f2 + f8, this.c);
        this.d.set((0.08f * f3) + f, f6, (0.92f * f3) + f, (0.88f * f3) + f2);
        canvas2.drawRoundRect(this.d, f8, f8, this.c);
        this.c.setStyle(Paint.Style.FILL);
        canvas2.drawCircle(f5 + f, (0.61f * f3) + f2, 0.13f * f3, this.c);
        float f9 = 0.56f * f3;
        float f10 = f + f9;
        float f11 = (0.8f * f3) + f;
        float f12 = f3 * 0.03f;
        canvas2.drawRoundRect(f10, (0.5f * f3) + f2, f11, f2 + f9, f12, f12, this.c);
        canvas2.drawRoundRect(f10, (f3 * 0.66f) + f2, f11, f2 + f7, f12, f12, this.c);
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        removeCallbacks(this.s);
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int iJ;
        float f;
        boolean z;
        float f2;
        float f3;
        Object next;
        he heVar;
        int i;
        float f4;
        Object next2;
        RectF rectF;
        canvas.getClass();
        super.onDraw(canvas);
        float width = getWidth();
        float height = getHeight();
        if (width <= 0.0f || height <= 0.0f) {
            return;
        }
        if (this.j) {
            l8.i();
            iJ = l8.a(-16777, this.a);
        } else {
            int i2 = l8.a;
            iJ = l8.j(this.a);
        }
        int i3 = iJ;
        this.p = false;
        if (!this.b || this.n == null) {
            float f5 = 0.6f;
            float fMin = Math.min(width, height) * (
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x06e6: ARITH (r4v1 'fMin' float) = (wrap float:0x06da: INVOKE (r2v1 'width' float), (r11v0 'height' float) STATIC call: java.lang.Math.min(float, float):float A[MD:(float, float):float (c), WRAPPED] (LINE:1755)) * (wrap float:?: TERNARY null = ((wrap boolean:0x06de: IGET (r0v0 'this' ?? I:??[OBJECT, ARRAY] A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:1759) ie.b boolean) != false) ? (r28v1 'f5' float) : (0.9f float)) A[DECLARE_VAR] (LINE:1767) in method: ie.onDraw(android.graphics.Canvas):void, file: classes.dex
                    at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                    at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                    at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                    at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                    at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                    at jadx.core.dex.regions.Region.generate(Region.java:35)
                    at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                    at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                    at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                    at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                    at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                    at jadx.core.dex.regions.Region.generate(Region.java:35)
                    at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                    at jadx.core.dex.regions.Region.generate(Region.java:35)
                    at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                    at jadx.core.dex.regions.Region.generate(Region.java:35)
                    at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                    at jadx.core.dex.regions.Region.generate(Region.java:35)
                    at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                    at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:291)
                    at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:270)
                    at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:420)
                    at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
                    at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
                    at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
                    at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                    at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r0v0 ??
                    at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
                */
            /*
                Method dump skipped, instruction units count: 1780
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: defpackage.ie.onDraw(android.graphics.Canvas):void");
        }

        @Override // android.view.View
        public final boolean onTouchEvent(MotionEvent motionEvent) {
            boolean z;
            motionEvent.getClass();
            j6 j6Var = this.n;
            if (j6Var == null) {
                return super.onTouchEvent(motionEvent);
            }
            int i = this.q;
            ArrayList arrayList = this.o;
            if (i < 0) {
                int actionMasked = motionEvent.getActionMasked();
                if (actionMasked == 0) {
                    this.x = false;
                    if (arrayList != null && arrayList.isEmpty()) {
                        z = false;
                        break;
                    }
                    int size = arrayList.size();
                    int i2 = 0;
                    while (true) {
                        if (i2 >= size) {
                            z = false;
                            break;
                        }
                        Object obj = arrayList.get(i2);
                        i2++;
                        RectF rectF = (RectF) obj;
                        if (this.p && rectF.contains(motionEvent.getX(), motionEvent.getY())) {
                            z = true;
                            break;
                        }
                    }
                    this.y = z;
                    this.v = motionEvent.getX();
                    this.w = motionEvent.getY();
                    VelocityTracker velocityTracker = this.z;
                    if (velocityTracker != null) {
                        velocityTracker.recycle();
                    }
                    VelocityTracker velocityTrackerObtain = VelocityTracker.obtain();
                    velocityTrackerObtain.addMovement(motionEvent);
                    this.z = velocityTrackerObtain;
                } else if (actionMasked != 1) {
                    if (actionMasked == 2) {
                        VelocityTracker velocityTracker2 = this.z;
                        if (velocityTracker2 != null) {
                            velocityTracker2.addMovement(motionEvent);
                        }
                        float x = motionEvent.getX() - this.v;
                        float y = motionEvent.getY() - this.w;
                        if (!this.x && !this.y) {
                            float fAbs = Math.abs(x);
                            float f = this.u;
                            if (fAbs > f || Math.abs(y) > f) {
                                if (Math.abs(x) >= Math.abs(y) * 2.0f) {
                                    this.x = true;
                                    ViewParent parent = getParent();
                                    if (parent != null) {
                                        parent.requestDisallowInterceptTouchEvent(true);
                                    }
                                    MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
                                    motionEventObtain.setAction(3);
                                    super.onTouchEvent(motionEventObtain);
                                    motionEventObtain.recycle();
                                } else {
                                    this.y = true;
                                }
                            }
                        }
                        if (this.x) {
                            setCoverShift(x * 0.35f);
                            return true;
                        }
                    } else if (actionMasked == 3) {
                        if (this.x) {
                            g();
                        }
                        this.x = false;
                        VelocityTracker velocityTracker3 = this.z;
                        if (velocityTracker3 != null) {
                            velocityTracker3.recycle();
                        }
                        this.z = null;
                    }
                } else if (this.x) {
                    VelocityTracker velocityTracker4 = this.z;
                    if (velocityTracker4 != null) {
                        velocityTracker4.addMovement(motionEvent);
                    }
                    VelocityTracker velocityTracker5 = this.z;
                    if (velocityTracker5 != null) {
                        velocityTracker5.computeCurrentVelocity(1000);
                    }
                    float x2 = motionEvent.getX() - this.v;
                    float y2 = motionEvent.getY() - this.w;
                    VelocityTracker velocityTracker6 = this.z;
                    float fAbs2 = Math.abs(velocityTracker6 != null ? velocityTracker6.getXVelocity() : 0.0f);
                    float f2 = this.t;
                    float f3 = fAbs2 / f2;
                    float width = this.C;
                    if (width <= 0.0f) {
                        width = getWidth();
                    }
                    int iY = k8.y(x2 / f2, y2 / f2, f3, width / f2);
                    long jElapsedRealtime = SystemClock.elapsedRealtime();
                    if (iY != 0 && jElapsedRealtime - this.A >= 700) {
                        this.A = jElapsedRealtime;
                        j6Var.b(iY > 0 ? "next" : "prev");
                        performHapticFeedback(1);
                    }
                    g();
                    this.x = false;
                    VelocityTracker velocityTracker7 = this.z;
                    if (velocityTracker7 != null) {
                        velocityTracker7.recycle();
                    }
                    this.z = null;
                    return true;
                }
            }
            if (!this.p) {
                return super.onTouchEvent(motionEvent);
            }
            int actionMasked2 = motionEvent.getActionMasked();
            int i3 = -1;
            k1 k1Var = this.s;
            if (actionMasked2 == 0) {
                int size2 = arrayList.size();
                int i4 = 0;
                int i5 = 0;
                while (i5 < size2) {
                    Object obj2 = arrayList.get(i5);
                    i5++;
                    if (((RectF) obj2).contains(motionEvent.getX(), motionEvent.getY())) {
                        i3 = i4;
                        break;
                    }
                    i4++;
                }
                this.q = i3;
                if (i3 >= 0) {
                    this.r = false;
                    removeCallbacks(k1Var);
                    postDelayed(k1Var, 600L);
                    ViewParent parent2 = getParent();
                    if (parent2 != null) {
                        parent2.requestDisallowInterceptTouchEvent(true);
                    }
                    invalidate();
                    return true;
                }
            } else if (actionMasked2 != 1) {
                if (actionMasked2 == 2) {
                    int i6 = this.q;
                    if (i6 >= 0) {
                        if (!((RectF) arrayList.get(i6)).contains(motionEvent.getX(), motionEvent.getY())) {
                            removeCallbacks(k1Var);
                        }
                        return true;
                    }
                } else if (actionMasked2 == 3 && this.q >= 0) {
                    removeCallbacks(k1Var);
                    this.q = -1;
                    this.r = false;
                    invalidate();
                    return true;
                }
            } else if (this.q >= 0) {
                removeCallbacks(k1Var);
                int i7 = this.q;
                this.q = -1;
                invalidate();
                if (!this.r && ((RectF) arrayList.get(i7)).contains(motionEvent.getX(), motionEvent.getY())) {
                    playSoundEffect(0);
                    j6Var.b(D.get(i7));
                }
                this.r = false;
                return true;
            }
            return super.onTouchEvent(motionEvent);
        }

        public final void setControl(j6 j6Var) {
            this.n = j6Var;
        }

        public final void setCovers(boolean z) {
            this.m = z;
        }
    }
