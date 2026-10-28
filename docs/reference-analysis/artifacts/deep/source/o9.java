package defpackage;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioManager;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Iterator;
import java.util.List;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class o9 extends ViewGroup {
    public final boolean a;
    public final y5 b;
    public final y5 c;
    public final boolean d;
    public final j6 e;
    public final int f;
    public final TextView g;
    public final ImageView h;
    public final TextView i;
    public final TextView j;
    public final TextView k;
    public final TextView l;
    public final m9 m;
    public final ob n;
    public final ob o;
    public final ob p;
    public final ob q;
    public final AudioManager r;
    public final ob s;
    public final k1 t;
    public final TextView u;
    public jc v;
    public Bitmap w;

    public o9(MainActivity mainActivity, boolean z, y5 y5Var, y5 y5Var2, y5 y5Var3, y5 y5Var4, y5 y5Var5, y5 y5Var6, boolean z2, y5 y5Var7, j6 j6Var) {
        super(mainActivity);
        this.a = z;
        this.b = y5Var;
        this.c = y5Var2;
        this.d = z2;
        this.e = j6Var;
        int iJ = l8.j(z);
        this.f = iJ;
        int iK = l8.k(z);
        TextView textView = new TextView(mainActivity);
        textView.setTextColor(iJ);
        textView.setMaxLines(1);
        TextUtils.TruncateAt truncateAt = TextUtils.TruncateAt.END;
        textView.setEllipsize(truncateAt);
        final int i = 0;
        textView.setOnClickListener(new View.OnClickListener(this) { // from class: l9
            public final /* synthetic */ o9 b;

            {
                this.b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i2 = i;
                o9 o9Var = this.b;
                switch (i2) {
                    case 0:
                        o9Var.c.a();
                        break;
                    case 1:
                        o9Var.c.a();
                        break;
                    case 2:
                        o9Var.c.a();
                        break;
                    default:
                        (!o9Var.v.a ? o9Var.b : o9Var.c).a();
                        break;
                }
            }
        });
        this.g = textView;
        ImageView imageView = new ImageView(mainActivity);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setContentDescription(si.a("album_cover_open_player", new Object[0]));
        imageView.setBackground(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{Color.rgb(75, 42, 123), Color.rgb(193, 68, 122), Color.rgb(255, 138, 61)}));
        final int i2 = 1;
        imageView.setClipToOutline(true);
        imageView.setOnClickListener(new View.OnClickListener(this) { // from class: l9
            public final /* synthetic */ o9 b;

            {
                this.b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i3 = i2;
                o9 o9Var = this.b;
                switch (i3) {
                    case 0:
                        o9Var.c.a();
                        break;
                    case 1:
                        o9Var.c.a();
                        break;
                    case 2:
                        o9Var.c.a();
                        break;
                    default:
                        (!o9Var.v.a ? o9Var.b : o9Var.c).a();
                        break;
                }
            }
        });
        this.h = imageView;
        TextView textView2 = new TextView(mainActivity);
        textView2.setTextColor(iJ);
        textView2.setMaxLines(1);
        textView2.setEllipsize(truncateAt);
        textView2.setGravity(17);
        textView2.setTypeface(z9.c());
        final int i3 = 2;
        textView2.setOnClickListener(new View.OnClickListener(this) { // from class: l9
            public final /* synthetic */ o9 b;

            {
                this.b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i4 = i3;
                o9 o9Var = this.b;
                switch (i4) {
                    case 0:
                        o9Var.c.a();
                        break;
                    case 1:
                        o9Var.c.a();
                        break;
                    case 2:
                        o9Var.c.a();
                        break;
                    default:
                        (!o9Var.v.a ? o9Var.b : o9Var.c).a();
                        break;
                }
            }
        });
        this.i = textView2;
        TextView textView3 = new TextView(mainActivity);
        textView3.setTextColor(iK);
        textView3.setMaxLines(1);
        textView3.setEllipsize(truncateAt);
        textView3.setGravity(17);
        this.j = textView3;
        TextView textView4 = new TextView(mainActivity);
        textView4.setTextColor(iK);
        this.k = textView4;
        TextView textView5 = new TextView(mainActivity);
        textView5.setTextColor(iK);
        textView5.setGravity(8388613);
        this.l = textView5;
        this.m = new m9(mainActivity, this);
        this.n = new ob(mainActivity, iJ, "prev", si.a("previous_track", new Object[0]), y5Var4);
        ob obVar = new ob(mainActivity, iJ, "play", si.a("play", new Object[0]), y5Var3);
        obVar.setBackground(new b7(z, 999.0f, true));
        this.o = obVar;
        this.p = new ob(mainActivity, iJ, "next", si.a("next_track", new Object[0]), y5Var5);
        this.q = new ob(mainActivity, l8.b, "heart", si.a("like", new Object[0]), y5Var6);
        this.r = (AudioManager) mainActivity.getSystemService(AudioManager.class);
        this.s = new ob(mainActivity, iJ, "sound", si.a("mute", new Object[0]), new x7(1, this));
        this.t = new k1(5, this);
        TextView textView6 = new TextView(mainActivity);
        textView6.setTextColor(-1);
        textView6.setGravity(17);
        textView6.setText(si.a("connect", new Object[0]));
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(l8.f());
        gradientDrawable.setCornerRadius(999.0f);
        textView6.setBackground(gradientDrawable);
        this.u = textView6;
        this.v = new jc(false, null, null, null, null, false, false, false, false, null, 65535);
        setBackground(new n8(z, l8.m(mainActivity) * 26.0f));
        if (!z2) {
            Iterator it = j3.i0(textView, textView2, textView3, textView4, textView5, textView6).iterator();
            while (it.hasNext()) {
                ((TextView) it.next()).setIncludeFontPadding(false);
            }
        }
        final int i4 = 3;
        int i5 = 4;
        Iterator it2 = j3.i0(this.g, this.h, this.i, this.j, this.m, this.k, this.l, this.n, this.o, this.p, this.q, this.u, this.s).iterator();
        while (it2.hasNext()) {
            addView((View) it2.next());
        }
        this.u.setOnClickListener(new View.OnClickListener(this) { // from class: l9
            public final /* synthetic */ o9 b;

            {
                this.b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i6 = i4;
                o9 o9Var = this.b;
                switch (i6) {
                    case 0:
                        o9Var.c.a();
                        break;
                    case 1:
                        o9Var.c.a();
                        break;
                    case 2:
                        o9Var.c.a();
                        break;
                    default:
                        (!o9Var.v.a ? o9Var.b : o9Var.c).a();
                        break;
                }
            }
        });
        this.h.setOnTouchListener(new w3(mainActivity, y5Var5, y5Var7, new y1(mainActivity, this, i5)));
        d(new jc(false, null, null, null, null, false, false, false, false, null, 65535));
    }

    public static void b(View view, float f, float f2, float f3, float f4, boolean z) {
        int iMax = Math.max(0, (int) f3);
        int iMax2 = Math.max(0, (int) f4);
        if (z) {
            view.measure(View.MeasureSpec.makeMeasureSpec(iMax, 1073741824), View.MeasureSpec.makeMeasureSpec(iMax2, 1073741824));
            return;
        }
        int i = (int) f;
        int i2 = (int) f2;
        view.layout(i, i2, iMax + i, iMax2 + i2);
    }

    public final void a(int i, int i2, boolean z) {
        float f = i;
        float f2 = i2;
        if (f <= 0.0f || f2 <= 0.0f) {
            return;
        }
        boolean z2 = this.d;
        int i3 = 1;
        ob obVar = this.s;
        TextView textView = this.l;
        ImageView imageView = this.h;
        TextView textView2 = this.g;
        TextView textView3 = this.u;
        TextView textView4 = this.k;
        TextView textView5 = this.i;
        TextView textView6 = this.j;
        if (z2) {
            float fMin = Math.min(f / 690.0f, f2 / 200.0f);
            if (fMin < 0.2f) {
                fMin = 0.2f;
            }
            float f3 = 16.0f * fMin;
            float f4 = getResources().getDisplayMetrics().scaledDensity;
            float f5 = f2 - (f3 * 2.0f);
            float fMin2 = Math.min(f5, f * 0.42f);
            imageView.setOutlineProvider(new n9(fMin, i3));
            b(imageView, f3, (f2 - fMin2) / 2.0f, fMin2, fMin2, z);
            float f6 = (fMin * 20.0f) + f3 + fMin2;
            float f7 = (f - f6) - f3;
            float f8 = fMin * 26.0f;
            float f9 = fMin * 34.0f;
            float f10 = 22.0f * fMin;
            float fMin3 = Math.min(fMin * 64.0f, f7 / 3.4f);
            float f11 = (((((f5 - f8) - f9) - f8) - f10) - fMin3) / 4.0f;
            float f12 = fMin * 2.0f;
            if (f11 < f12) {
                f11 = f12;
            }
            textView2.setTextSize(f3 / f4);
            textView5.setTextSize((25.0f * fMin) / f4);
            textView6.setTextSize((18.0f * fMin) / f4);
            textView4.setTextSize((13.0f * fMin) / f4);
            textView.setTextSize(textView4.getTextSize());
            textView3.setTextSize((17.0f * fMin) / f4);
            textView5.setGravity(8388627);
            textView6.setGravity(8388627);
            textView4.setGravity(8388627);
            textView.setGravity(8388629);
            float f13 = fMin * 40.0f;
            float f14 = 8.0f * fMin;
            b(textView2, f6, f3, (f7 - f13) - f14, f8, z);
            float f15 = f6 + f7;
            b(this.q, (4.0f * fMin) + (f15 - f13), ((f8 - f13) / 2.0f) + f3, f13, f13, z);
            float f16 = f11 + f8;
            float f17 = f3 + f16;
            b(textView5, f6, f17, f7, f9, z);
            float f18 = f17 + f9;
            b(textView6, f6, f18, f7, f8, z);
            float f19 = f18 + f16;
            float f20 = fMin * 54.0f;
            b(textView4, f6, f19, f20, f10, z);
            float f21 = fMin * 32.0f;
            b(this.m, f6 + f20 + f14, ((f10 - f21) / 2.0f) + f19, (f7 - (f20 * 2.0f)) - f3, f21, z);
            b(textView, f15 - f20, f19, f20, f10, z);
            float f22 = f2 - f3;
            float f23 = f22 - fMin3;
            float f24 = fMin3 * 0.8f;
            float f25 = (f7 / 2.0f) + f6;
            float f26 = fMin3 / 2.0f;
            float f27 = f25 - f26;
            b(this.o, f27, f23, fMin3, fMin3, z);
            float f28 = fMin * 24.0f;
            float f29 = ((fMin3 - f24) / 2.0f) + f23;
            b(this.n, (f27 - f28) - f24, f29, f24, f24, z);
            b(this.p, f25 + f26 + f28, f29, f24, f24, z);
            b(textView3, f6, f23, f7, fMin3, z);
            obVar.setSymbolScale(0.62f);
            b(this.s, (f - f3) - f13, f22 - f13, f13, f13, z);
            return;
        }
        float f30 = f / 337.0f;
        float fMin4 = Math.min(f30, f2 / 516.0f);
        float fMin5 = Math.min(f30, f2 / 220.0f) * 0.5f;
        if (fMin4 < fMin5) {
            fMin4 = fMin5;
        }
        float f31 = fMin4 * 22.0f;
        textView2.setTextSize((21.0f * fMin4) / getResources().getDisplayMetrics().scaledDensity);
        textView5.setTextSize((fMin4 * 26.0f) / getResources().getDisplayMetrics().scaledDensity);
        float f32 = fMin4 * 20.0f;
        textView6.setTextSize(f32 / getResources().getDisplayMetrics().scaledDensity);
        float f33 = fMin4 * 16.0f;
        textView4.setTextSize(f33 / getResources().getDisplayMetrics().scaledDensity);
        textView.setTextSize(textView4.getTextSize());
        textView3.setTextSize(f32 / getResources().getDisplayMetrics().scaledDensity);
        imageView.setOutlineProvider(new n9(fMin4, 0));
        float f34 = 6.0f * fMin4;
        float f35 = f31 * 0.8f;
        float f36 = fMin4 * 40.0f;
        float f37 = f35 + f36;
        float f38 = fMin4 * 76.0f;
        float f39 = f - (f34 * 2.0f);
        float fMin6 = Math.min(f39, (f2 - f37) - (((120.0f * fMin4) + f38) + f31));
        int i4 = fMin6 < 70.0f * fMin4 ? 1 : 0;
        ob obVar2 = this.q;
        if (i4 == 0) {
            float f40 = f31 * 2.0f;
            float f41 = f - f40;
            float f42 = fMin4 * 30.0f;
            b(textView2, f31, f35, f41, f42, z);
            b(imageView, f34, f37, f39, fMin6, z);
            float f43 = 12.0f * fMin4;
            float f44 = f37 + fMin6 + f43;
            float f45 = fMin4 * 38.0f;
            b(textView5, f31, f44, f41, f45, z);
            float f46 = f44 + f45;
            b(textView6, f31, f46, f41, f42, z);
            float f47 = (fMin4 * 34.0f) + f46;
            float f48 = 10.0f * fMin4;
            b(this.m, f31, f47 - f48, f41, fMin4 * 32.0f, z);
            float f49 = f47 + f43;
            float f50 = fMin4 * 100.0f;
            float f51 = fMin4 * 24.0f;
            b(textView4, f31, f49, f50, f51, z);
            b(textView, (f - f31) - f50, f49, f50, f51, z);
            float f52 = (f2 - f31) - f38;
            b(this.o, (f - f38) / 2.0f, f52, f38, f38, z);
            float f53 = f / 2.0f;
            float f54 = fMin4 * 52.0f;
            float f55 = f52 + f43;
            b(this.n, ((f53 - f45) - f42) - f54, f55, f54, f54, z);
            b(this.p, f53 + f45 + f42, f55, f54, f54, z);
            float f56 = fMin4 * 42.0f;
            obVar2.setSymbolScale(0.92f);
            float f57 = fMin4 * 4.0f;
            float f58 = (f2 - f56) - f57;
            b(this.q, f57, f58, f56, f56, z);
            obVar.setSymbolScale(obVar2.getSymbolScale());
            b(this.s, (f - f56) - f57, f58, f56, f56, z);
            b(textView3, f40, f52 + f48, f - (f31 * 4.0f), fMin4 * 56.0f, z);
        } else {
            obVar2.setSymbolScale(0.62f);
            float f59 = (f - f31) - f36;
            b(this.q, f59 + f34, f35 - f34, f36, f36, z);
            float f60 = f31 * 2.0f;
            b(textView2, f31, f35, (f - f60) - f36, fMin4 * 30.0f, z);
            float fMin7 = Math.min((f2 - f60) - f36, 0.32f * f);
            b(imageView, f31, (38.0f * fMin4) + f31, fMin7, fMin7, z);
            float f61 = f31 + fMin7 + f33;
            float f62 = (f - f61) - f31;
            float f63 = fMin4 * 36.0f;
            b(textView5, f61, f31 + f36, f62, f63, z);
            textView5.setGravity(8388611);
            b(textView6, f61, f31 + f38, f62, fMin4 * 28.0f, z);
            textView6.setGravity(8388611);
            b(this.m, f61, ((108.0f * fMin4) + f31) - (11.0f * fMin4), f62, fMin4 * 32.0f, z);
            float f64 = (112.0f * fMin4) + f31;
            b(textView4, f61, f64, 0.0f, 0.0f, z);
            b(textView, f61, f64, 0.0f, 0.0f, z);
            float f65 = f2 - f31;
            float fMin8 = Math.min(fMin4 * 56.0f, f65 - ((128.0f * fMin4) + f31));
            float f66 = fMin8 < f63 ? f63 : fMin8;
            float f67 = f65 - f66;
            float f68 = f66;
            b(this.n, f61, f67, f66, f68, z);
            b(this.o, (10.0f * fMin4) + f61 + f66, f67, f66, f68, z);
            b(this.p, (f66 * 2.0f) + f61 + f32, f67, f66, f68, z);
            b(textView3, f61, f67, Math.min(f62, fMin4 * 220.0f), f66, z);
            obVar.setSymbolScale(0.62f);
            b(this.s, f59, f65 - f36, f36, f36, z);
        }
        if (i4 == 0) {
            textView5.setGravity(17);
            textView6.setGravity(17);
        }
    }

    public final void c() {
        Object ffVar;
        try {
            ffVar = Boolean.valueOf(this.r.isStreamMute(3));
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        if (ffVar instanceof ff) {
            ffVar = null;
        }
        Boolean bool = (Boolean) ffVar;
        boolean z = bool != null;
        ob obVar = this.s;
        obVar.setEnabled(z);
        obVar.setAlpha(bool != null ? 1.0f : 0.3f);
        Boolean bool2 = Boolean.TRUE;
        obVar.b = k8.e(bool, bool2) ? "muted" : "sound";
        obVar.invalidate();
        obVar.setContentDescription(k8.e(bool, bool2) ? si.a("unmute", new Object[0]) : si.a("mute", new Object[0]));
    }

    public final void d(jc jcVar) {
        char c;
        String strA;
        String strA2;
        jcVar.getClass();
        boolean z = jcVar.o;
        boolean z2 = jcVar.i;
        long j = jcVar.h;
        String strA3 = jcVar.d;
        String strA4 = jcVar.c;
        boolean z3 = jcVar.n;
        long j2 = jcVar.g;
        boolean z4 = jcVar.b;
        boolean z5 = jcVar.a;
        this.v = jcVar;
        String strA5 = jcVar.m;
        if (th.A(strA5)) {
            strA5 = si.a("music", new Object[0]);
        }
        this.g.setText(strA5);
        if (!z5) {
            strA4 = si.a("track_information", new Object[0]);
        } else if (!z4) {
            strA4 = si.a("start_the_player", new Object[0]);
        } else if (th.A(strA4)) {
            strA4 = si.a("title_not_provided", new Object[0]);
        }
        this.i.setText(strA4);
        if (!z5) {
            strA3 = si.a("music_access_required", new Object[0]);
        } else if (!z4) {
            strA3 = si.a("tap", new Object[0]);
        } else if (th.A(strA3)) {
            strA3 = si.a("artist_not_provided", new Object[0]);
        }
        this.j.setText(strA3);
        Bitmap bitmap = this.w;
        Bitmap bitmap2 = jcVar.f;
        if (bitmap != bitmap2) {
            this.w = bitmap2;
            this.h.setImageBitmap(bitmap2);
        }
        boolean z6 = z5 && z4;
        int i = z5 ? 8 : 0;
        TextView textView = this.u;
        textView.setVisibility(i);
        if (z5) {
            c = 1;
            strA = si.a("open_player", new Object[0]);
        } else {
            c = 1;
            strA = si.a("connect", new Object[0]);
        }
        textView.setText(strA);
        View[] viewArr = new View[3];
        m9 m9Var = this.m;
        viewArr[0] = m9Var;
        TextView textView2 = this.k;
        viewArr[c] = textView2;
        TextView textView3 = this.l;
        viewArr[2] = textView3;
        Iterator it = j3.i0(viewArr).iterator();
        while (it.hasNext()) {
            ((View) it.next()).setVisibility(z6 ? 0 : 4);
            z = z;
        }
        boolean z7 = z;
        ob obVar = this.n;
        ob obVar2 = this.o;
        ob obVar3 = this.p;
        Iterator it2 = j3.i0(obVar, obVar2, obVar3).iterator();
        while (it2.hasNext()) {
            boolean z8 = z2;
            boolean z9 = z5;
            ((ob) it2.next()).setVisibility(z5 ? 0 : 8);
            z5 = z9;
            z2 = z8;
        }
        boolean z10 = z2;
        boolean z11 = z5;
        List list = dd.a;
        textView3.setText(dd.c(j > 0 ? j : -1L));
        if (m9Var.getTarget() < 0 || SystemClock.elapsedRealtime() - m9Var.getTargetAt() >= 1500 || Math.abs(j2 - m9Var.getTarget()) <= 2000) {
            c = 0;
        }
        if (c == 0) {
            m9Var.setTarget(-1L);
        }
        if (!m9Var.getDragging() && c == 0) {
            textView2.setText(dd.c(j2));
            float fR = 0.0f;
            if (j > 0 && j2 >= 0) {
                fR = pi.r(j2 / j, 0.0f, 1.0f);
            }
            m9Var.setFraction(fR);
            m9Var.invalidate();
        }
        m9Var.setContentDescription(jcVar.p ? si.a("seek_bar_hint", new Object[0]) : null);
        String str = z10 ? "pause" : "play";
        obVar2.getClass();
        obVar2.b = str;
        obVar2.invalidate();
        obVar2.setContentDescription(z10 ? si.a("pause", new Object[0]) : si.a("play", new Object[0]));
        obVar2.setEnabled(jcVar.j);
        obVar.setEnabled(jcVar.k);
        obVar3.setEnabled(jcVar.l);
        Iterator it3 = j3.i0(obVar2, obVar, obVar3).iterator();
        while (true) {
            float f = 0.3f;
            if (!it3.hasNext()) {
                break;
            }
            ob obVar4 = (ob) it3.next();
            if (obVar4.isEnabled()) {
                f = 1.0f;
            }
            obVar4.setAlpha(f);
        }
        int i2 = z11 ? 0 : 8;
        ob obVar5 = this.q;
        obVar5.setVisibility(i2);
        obVar5.setEnabled(z3);
        obVar5.setAlpha(z3 ? 1.0f : 0.3f);
        obVar5.b = z7 ? "liked" : "heart";
        obVar5.invalidate();
        if (z3) {
            strA2 = z7 ? si.a("unlike", new Object[0]) : si.a("like", new Object[0]);
        } else {
            strA2 = si.a("the_player_does_not_support_likes", new Object[0]);
        }
        obVar5.setContentDescription(strA2);
        c();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        c();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        removeCallbacks(this.t);
        super.onDetachedFromWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        a(i3 - i, i4 - i2, false);
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        setMeasuredDimension(size, size2);
        a(size, size2, true);
    }
}
