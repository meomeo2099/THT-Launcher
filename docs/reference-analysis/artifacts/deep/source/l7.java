package defpackage;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class l7 implements y5 {
    public final /* synthetic */ int a = 0;
    public final /* synthetic */ LinearLayout b;
    public final /* synthetic */ MainActivity c;
    public final /* synthetic */ String d;
    public final /* synthetic */ i4 e;
    public final /* synthetic */ me f;
    public final /* synthetic */ me g;
    public final /* synthetic */ Serializable h;

    public /* synthetic */ l7(LinearLayout linearLayout, i4 i4Var, MainActivity mainActivity, je jeVar, me meVar, String str, me meVar2) {
        this.b = linearLayout;
        this.e = i4Var;
        this.c = mainActivity;
        this.h = jeVar;
        this.f = meVar;
        this.d = str;
        this.g = meVar2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v44, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v45 */
    /* JADX WARN: Type inference failed for: r0v64 */
    /* JADX WARN: Type inference failed for: r0v65 */
    /* JADX WARN: Type inference failed for: r12v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r13v0 */
    /* JADX WARN: Type inference failed for: r13v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r13v2 */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.view.ViewGroup, android.widget.LinearLayout] */
    /* JADX WARN: Type inference failed for: r1v2, types: [android.view.ViewGroup, android.widget.LinearLayout] */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [android.graphics.Rect, java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r9v23, types: [android.graphics.Bitmap] */
    /* JADX WARN: Type inference failed for: r9v25, types: [android.graphics.Bitmap] */
    /* JADX WARN: Type inference failed for: r9v29 */
    @Override // defpackage.y5
    public final Object a() throws IOException {
        Object ffVar;
        ?? Substring;
        ArrayList arrayList;
        Object ffVar2;
        ?? r9;
        String str;
        me meVar;
        final int i = 1;
        ?? r13 = 0;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        switch (this.a) {
            case 0:
                ?? r1 = this.b;
                i4 i4Var = this.e;
                final MainActivity mainActivity = this.c;
                je jeVar = (je) this.h;
                final me meVar2 = this.f;
                final String str2 = this.d;
                me meVar3 = this.g;
                r1.removeAllViews();
                String strA = si.a("image_from_device", new Object[0]);
                final boolean z4 = z ? 1 : 0;
                r1.addView(i4Var.a(strA, true, new y5() { // from class: m7
                    @Override // defpackage.y5
                    public final Object a() {
                        int i2 = z4;
                        ti tiVar = ti.a;
                        String str3 = str2;
                        MainActivity mainActivity2 = mainActivity;
                        me meVar4 = meVar2;
                        switch (i2) {
                            case 0:
                                Object obj = meVar4.b;
                                if (obj == null) {
                                    k8.H0("dialog");
                                    throw null;
                                }
                                ((Dialog) obj).dismiss();
                                WeakHashMap weakHashMap = f8.a;
                                mainActivity2.getClass();
                                str3.getClass();
                                if (!mainActivity2.h) {
                                    mainActivity2.F = str3;
                                    try {
                                        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                                        intent.setType("image/*");
                                        intent.addCategory("android.intent.category.OPENABLE");
                                        intent.addFlags(1);
                                        mainActivity2.startActivityForResult(intent, 56);
                                    } catch (ActivityNotFoundException unused) {
                                        mainActivity2.v0(si.a("no_file_picker_on_this_head_unit", new Object[0]));
                                    }
                                    break;
                                }
                                return tiVar;
                            default:
                                Object obj2 = meVar4.b;
                                if (obj2 == null) {
                                    k8.H0("dialog");
                                    throw null;
                                }
                                ((Dialog) obj2).dismiss();
                                pi.F(str3, mainActivity2);
                                return tiVar;
                        }
                    }
                }), new LinearLayout.LayoutParams(-1, mainActivity.r(52)));
                LinearLayout linearLayout = new LinearLayout(mainActivity);
                for (yc ycVar : j3.i0(new yc(Boolean.FALSE, "image_gallery_mine"), new yc(Boolean.TRUE, "image_gallery_icons"))) {
                    boolean zBooleanValue = ((Boolean) ycVar.a).booleanValue();
                    Button buttonA = i4Var.a(si.a((String) ycVar.b, new Object[0]), zBooleanValue == jeVar.a, new f7(jeVar, zBooleanValue, meVar3));
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, mainActivity.r(48));
                    layoutParams.setMarginEnd(mainActivity.r(8));
                    layoutParams.topMargin = mainActivity.r(12);
                    linearLayout.addView(buttonA, layoutParams);
                }
                r1.addView(linearLayout);
                int i2 = ((float) mainActivity.getResources().getDisplayMetrics().widthPixels) / mainActivity.getResources().getDisplayMetrics().density < 700.0f ? 3 : 6;
                GridLayout gridLayout = new GridLayout(mainActivity);
                gridLayout.setColumnCount(i2);
                boolean z5 = jeVar.a;
                ?? r3 = 0;
                if (!z5) {
                    me meVar4 = k7.a;
                    File[] fileArrListFiles = mainActivity.getFilesDir().listFiles();
                    if (fileArrListFiles == null) {
                        fileArrListFiles = new File[0];
                    }
                    ArrayList arrayList2 = new ArrayList();
                    for (File file : fileArrListFiles) {
                        if (file.isFile()) {
                            me meVar5 = k7.a;
                            String name = file.getName();
                            name.getClass();
                            if (meVar5.a(name)) {
                                arrayList2.add(file);
                            }
                        }
                    }
                    List<File> listG0 = i3.G0(arrayList2, new h0(4));
                    if (listG0.isEmpty()) {
                        String strA2 = si.a("image_gallery_empty", new Object[0]);
                        int i3 = l8.a;
                        TextView textViewU0 = mainActivity.u0(strA2, 16.0f, l8.k(mainActivity.z()));
                        textViewU0.setPadding(mainActivity.r(4), mainActivity.r(16), 0, 0);
                        r1.addView(textViewU0);
                    }
                    for (File file2 : listG0) {
                        try {
                            String absolutePath = file2.getAbsolutePath();
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            try {
                                options.inSampleSize = 4;
                                ffVar = BitmapFactory.decodeFile(absolutePath, options);
                            } catch (Throwable th) {
                                th = th;
                                ffVar = new ff(th);
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                        if (ffVar instanceof ff) {
                            ffVar = null;
                        }
                        me meVar6 = meVar2;
                        String str3 = str2;
                        pi.H(gridLayout, mainActivity, pi.I(mainActivity, (Bitmap) ffVar, si.a("screensaver_picture", new Object[0]), new n7(mainActivity, str3, file2, meVar6, 1), new u0(mainActivity, file2, meVar3, 2)));
                        str2 = str3;
                        meVar2 = meVar6;
                        break;
                    }
                } else {
                    int i4 = l8.a;
                    int iJ = l8.j(mainActivity.z());
                    me meVar7 = k7.a;
                    String[] list = mainActivity.getAssets().list("launcher_icons");
                    if (list == null) {
                        list = new String[0];
                    }
                    ArrayList arrayList3 = new ArrayList();
                    for (String str4 : list) {
                        str4.getClass();
                        if (str4.endsWith(".png")) {
                            arrayList3.add(str4);
                        }
                    }
                    List listF0 = i3.F0(arrayList3);
                    ArrayList arrayList4 = new ArrayList(k3.n0(listF0));
                    Iterator it = listF0.iterator();
                    while (it.hasNext()) {
                        arrayList4.add("launcher_icons/" + ((String) it.next()));
                    }
                    int size = arrayList4.size();
                    int i5 = 0;
                    while (i5 < size) {
                        int i6 = i5 + 1;
                        String str5 = (String) arrayList4.get(i5);
                        ?? E = th.E(str5, '/');
                        if (E.endsWith(".png")) {
                            Substring = E;
                            Substring = E.substring(r13, E.length() - 4);
                        }
                        Substring = E;
                        ?? r12 = Substring;
                        me meVar8 = k7.a;
                        try {
                            InputStream inputStreamOpen = mainActivity.getAssets().open(str5);
                            try {
                                BitmapFactory.Options options2 = new BitmapFactory.Options();
                                options2.inScaled = r13;
                                Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpen, r3, options2);
                                k8.p(inputStreamOpen, r3);
                                if (bitmapDecodeStream == null) {
                                    r9 = r3;
                                    arrayList = arrayList4;
                                } else {
                                    Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeStream.getWidth(), bitmapDecodeStream.getHeight(), Bitmap.Config.ARGB_8888);
                                    Canvas canvas = new Canvas(bitmapCreateBitmap);
                                    Paint paint = new Paint(1);
                                    arrayList = arrayList4;
                                    try {
                                        paint.setColorFilter(new PorterDuffColorFilter(iJ, PorterDuff.Mode.SRC_IN));
                                        canvas.drawBitmap(bitmapDecodeStream, 0.0f, 0.0f, paint);
                                        bitmapDecodeStream.recycle();
                                        ffVar2 = bitmapCreateBitmap;
                                    } catch (Throwable th3) {
                                        th = th3;
                                        ffVar2 = new ff(th);
                                    }
                                    boolean z6 = ffVar2 instanceof ff;
                                    Object obj = ffVar2;
                                    if (z6) {
                                        obj = null;
                                    }
                                    r9 = (Bitmap) obj;
                                }
                                r3 = 0;
                                pi.H(gridLayout, mainActivity, pi.I(mainActivity, r9, r12, new n7((Bitmap) r9, mainActivity, str2, meVar2), null));
                                i5 = i6;
                                arrayList4 = arrayList;
                                r13 = 0;
                            } catch (Throwable th4) {
                                arrayList = arrayList4;
                                try {
                                    throw th4;
                                } catch (Throwable th5) {
                                    k8.p(inputStreamOpen, th4);
                                    throw th5;
                                }
                            }
                        } catch (Throwable th6) {
                            th = th6;
                            arrayList = arrayList4;
                        }
                    }
                }
                r1.addView(gridLayout);
                return ti.a;
            default:
                ?? r2 = this.b;
                final MainActivity mainActivity2 = this.c;
                final String str6 = this.d;
                le leVar = (le) this.h;
                i4 i4Var2 = this.e;
                me meVar9 = this.f;
                final me meVar10 = this.g;
                r2.removeAllViews();
                HashMap map = v7.a;
                SharedPreferences sharedPreferencesB = mainActivity2.B();
                sharedPreferencesB.getClass();
                r7 r7VarD = v7.d(sharedPreferencesB, str6);
                s7 s7Var = r7VarD.b;
                List list2 = r7VarD.a;
                int i7 = leVar.a;
                if (i7 < 0 || i7 >= list2.size()) {
                    leVar.a = -1;
                }
                Bitmap bitmapA = v7.a(mainActivity2, str6);
                q7 q7Var = new q7(mainActivity2, mainActivity2.z(), bitmapA, r7VarD, null, null, null, new q0(leVar, meVar9, 5), new y7(mainActivity2, str6, z3 ? 1 : 0), 224);
                q7Var.setSelected(leVar.a);
                r2.addView(q7Var, new LinearLayout.LayoutParams(-1, mainActivity2.r(220)));
                LinearLayout linearLayout2 = new LinearLayout(mainActivity2);
                List list3 = si.a;
                yc ycVar2 = new yc(si.a(bitmapA == null ? "image_choose" : "image_change", new Object[0]), new y5() { // from class: m7
                    @Override // defpackage.y5
                    public final Object a() {
                        int i8 = i;
                        ti tiVar = ti.a;
                        String str7 = str6;
                        MainActivity mainActivity3 = mainActivity2;
                        me meVar11 = meVar10;
                        switch (i8) {
                            case 0:
                                Object obj2 = meVar11.b;
                                if (obj2 == null) {
                                    k8.H0("dialog");
                                    throw null;
                                }
                                ((Dialog) obj2).dismiss();
                                WeakHashMap weakHashMap = f8.a;
                                mainActivity3.getClass();
                                str7.getClass();
                                if (!mainActivity3.h) {
                                    mainActivity3.F = str7;
                                    try {
                                        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                                        intent.setType("image/*");
                                        intent.addCategory("android.intent.category.OPENABLE");
                                        intent.addFlags(1);
                                        mainActivity3.startActivityForResult(intent, 56);
                                    } catch (ActivityNotFoundException unused) {
                                        mainActivity3.v0(si.a("no_file_picker_on_this_head_unit", new Object[0]));
                                    }
                                    break;
                                }
                                return tiVar;
                            default:
                                Object obj3 = meVar11.b;
                                if (obj3 == null) {
                                    k8.H0("dialog");
                                    throw null;
                                }
                                ((Dialog) obj3).dismiss();
                                pi.F(str7, mainActivity3);
                                return tiVar;
                        }
                    }
                });
                yc ycVar3 = new yc(si.a("image_split", new Object[0]), new z7(mainActivity2, str6, leVar, meVar9, 0));
                String strA3 = si.a("image_add_zone", new Object[0]);
                z7 z7Var = new z7(mainActivity2, str6, leVar, meVar9, 1);
                String str7 = str6;
                me meVar11 = meVar9;
                for (yc ycVar4 : j3.i0(ycVar2, ycVar3, new yc(strA3, z7Var))) {
                    Button buttonA2 = i4Var2.a((String) ycVar4.a, false, new a8((y5) ycVar4.b, 0));
                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, mainActivity2.r(48));
                    layoutParams2.setMarginEnd(mainActivity2.r(8));
                    linearLayout2.addView(buttonA2, layoutParams2);
                }
                HorizontalScrollView horizontalScrollView = new HorizontalScrollView(mainActivity2);
                horizontalScrollView.setHorizontalScrollBarEnabled(false);
                horizontalScrollView.addView(linearLayout2);
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
                layoutParams3.topMargin = mainActivity2.r(10);
                r2.addView(horizontalScrollView, layoutParams3);
                u7 u7Var = (u7) i3.w0(list2, leVar.a);
                if (u7Var == null) {
                    String strA4 = si.a("image_pick_zone_hint", new Object[0]);
                    int i8 = l8.a;
                    TextView textViewU1 = mainActivity2.u0(strA4, 16.0f, l8.k(mainActivity2.z()));
                    textViewU1.setPadding(mainActivity2.r(4), mainActivity2.r(16), 0, mainActivity2.r(4));
                    r2.addView(textViewU1);
                    str = str7;
                    meVar = meVar11;
                } else {
                    String str8 = u7Var.e;
                    List list4 = si.a;
                    TextView textViewU2 = mainActivity2.u0(si.a("image_zone_0", Integer.valueOf(leVar.a + 1)), 19.0f, mainActivity2.o);
                    textViewU2.setPadding(mainActivity2.r(4), mainActivity2.r(16), 0, mainActivity2.r(8));
                    r2.addView(textViewU2);
                    Button buttonA3 = i4Var2.a(c.d(si.a("function", new Object[0]), ": ", th.A(str8) ? si.a("image_no_function", new Object[0]) : mainActivity2.W(str8)), th.A(str8), new z7(leVar, mainActivity2, str7, meVar11));
                    buttonA3.setGravity(8388627);
                    r2.addView(buttonA3, new LinearLayout.LayoutParams(-1, mainActivity2.r(52)));
                    String strA5 = si.a("image_show", new Object[0]);
                    int i9 = l8.a;
                    TextView textViewU3 = mainActivity2.u0(strA5, 15.0f, l8.k(mainActivity2.z()));
                    textViewU3.setPadding(mainActivity2.r(4), mainActivity2.r(12), 0, mainActivity2.r(6));
                    r2.addView(textViewU3);
                    LinearLayout linearLayout3 = new LinearLayout(mainActivity2);
                    y4 y4Var = t7.h;
                    y4Var.getClass();
                    d dVar = new d(0, y4Var);
                    while (dVar.hasNext()) {
                        t7 t7Var = (t7) dVar.next();
                        List list5 = si.a;
                        me meVar12 = meVar11;
                        String str9 = str7;
                        le leVar2 = leVar;
                        Button buttonA4 = i4Var2.a(si.a(t7Var.b, new Object[0]), t7Var == u7Var.f, new b8(mainActivity2, str9, meVar12, leVar2, t7Var));
                        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-2, mainActivity2.r(48));
                        layoutParams4.setMarginEnd(mainActivity2.r(8));
                        linearLayout3.addView(buttonA4, layoutParams4);
                        str7 = str9;
                        leVar = leVar2;
                        meVar11 = meVar12;
                    }
                    str = str7;
                    meVar = meVar11;
                    HorizontalScrollView horizontalScrollView2 = new HorizontalScrollView(mainActivity2);
                    horizontalScrollView2.setHorizontalScrollBarEnabled(false);
                    horizontalScrollView2.addView(linearLayout3);
                    r2.addView(horizontalScrollView2);
                    Button buttonA5 = i4Var2.a(si.a("image_delete_zone", new Object[0]), false, new z7(mainActivity2, str, leVar, meVar, 3));
                    LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-1, mainActivity2.r(52));
                    layoutParams5.topMargin = mainActivity2.r(10);
                    r2.addView(buttonA5, layoutParams5);
                }
                String strA6 = si.a("dock_overlay", new Object[0]);
                int i10 = l8.a;
                TextView textViewU4 = mainActivity2.u0(strA6, 15.0f, l8.k(mainActivity2.z()));
                textViewU4.setPadding(mainActivity2.r(4), mainActivity2.r(18), 0, mainActivity2.r(6));
                r2.addView(textViewU4);
                LinearLayout linearLayout4 = new LinearLayout(mainActivity2);
                y4 y4Var2 = s7.g;
                y4Var2.getClass();
                d dVar2 = new d(0, y4Var2);
                while (dVar2.hasNext()) {
                    s7 s7Var2 = (s7) dVar2.next();
                    List list6 = si.a;
                    Button buttonA6 = i4Var2.a(si.a(s7Var2.b, new Object[0]), s7Var2 == s7Var, new n7(mainActivity2, str, s7Var2, meVar, 2));
                    LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-2, mainActivity2.r(48));
                    layoutParams6.setMarginEnd(mainActivity2.r(8));
                    linearLayout4.addView(buttonA6, layoutParams6);
                }
                HorizontalScrollView horizontalScrollView3 = new HorizontalScrollView(mainActivity2);
                horizontalScrollView3.setHorizontalScrollBarEnabled(false);
                horizontalScrollView3.addView(linearLayout4);
                r2.addView(horizontalScrollView3);
                if (s7Var != s7.d) {
                    List list7 = si.a;
                    String strA7 = si.a(s7Var == s7.e ? "image_over_change_hint" : "image_over_apps_hint", new Object[0]);
                    int i11 = l8.a;
                    TextView textViewU5 = mainActivity2.u0(strA7, 14.0f, l8.k(mainActivity2.z()));
                    textViewU5.setPadding(mainActivity2.r(4), mainActivity2.r(6), 0, 0);
                    r2.addView(textViewU5);
                    boolean z7 = q.a;
                    if (!q.a(mainActivity2)) {
                        Button buttonA7 = i4Var2.a(si.a("anton_popup_allow", new Object[0]), false, new b2(mainActivity2, 4));
                        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(-1, mainActivity2.r(52));
                        layoutParams7.topMargin = mainActivity2.r(8);
                        r2.addView(buttonA7, layoutParams7);
                    }
                }
                Button buttonA8 = i4Var2.a(si.a("done", new Object[0]), true, new x7((int) (z2 ? 1 : 0), (Object) meVar10));
                LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(-1, mainActivity2.r(52));
                layoutParams8.topMargin = mainActivity2.r(18);
                r2.addView(buttonA8, layoutParams8);
                return ti.a;
        }
    }

    public /* synthetic */ l7(LinearLayout linearLayout, MainActivity mainActivity, String str, le leVar, i4 i4Var, me meVar, me meVar2) {
        this.b = linearLayout;
        this.c = mainActivity;
        this.d = str;
        this.h = leVar;
        this.e = i4Var;
        this.f = meVar;
        this.g = meVar2;
    }
}
