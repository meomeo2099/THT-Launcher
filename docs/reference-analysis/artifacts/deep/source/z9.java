package defpackage;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class z9 {
    public static boolean a;
    public static Typeface b = Typeface.create("sans-serif", 0);
    public static Typeface c = Typeface.create("sans-serif-medium", 0);
    public static boolean d = true;

    public static void a(View view) {
        if (view instanceof kc) {
            return;
        }
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            Typeface typeface = textView.getTypeface();
            textView.setTypeface(((typeface == null || !typeface.isBold()) && textView.getTextSize() < 22.0f * textView.getResources().getDisplayMetrics().scaledDensity) ? b() : c());
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                childAt.getClass();
                a(childAt);
            }
        }
    }

    public static Typeface b() {
        List list = si.a;
        Typeface typefaceCreate = (si.b.a.equals("ar") || (d && si.b.a.equals("kk"))) ? Typeface.create("sans-serif", 0) : b;
        typefaceCreate.getClass();
        return typefaceCreate;
    }

    public static Typeface c() {
        List list = si.a;
        Typeface typefaceCreate = (si.b.a.equals("ar") || (d && si.b.a.equals("kk"))) ? Typeface.create("sans-serif-medium", 0) : c;
        typefaceCreate.getClass();
        return typefaceCreate;
    }

    public static void d(Context context) {
        Object ffVar;
        File fileD = x3.d(context);
        yc ycVar = null;
        if (!x3.c(context)) {
            fileD = null;
        }
        if (fileD != null) {
            try {
                ffVar = new yc(new Typeface.Builder(fileD).setFontVariationSettings("'wght' 450").build(), new Typeface.Builder(fileD).setFontVariationSettings("'wght' 600").build());
            } catch (Throwable th) {
                ffVar = new ff(th);
            }
            if (ffVar instanceof ff) {
                ffVar = null;
            }
            yc ycVar2 = (yc) ffVar;
            if (ycVar2 != null) {
                Typeface typeface = (Typeface) ycVar2.a;
                Typeface typeface2 = (Typeface) ycVar2.b;
                if (typeface != null && typeface2 != null) {
                    ycVar = ycVar2;
                }
            }
        }
        if (ycVar != null) {
            Typeface typeface3 = (Typeface) ycVar.a;
            b = typeface3;
            ArrayList arrayList = x3.a;
            c = context.getSharedPreferences("panel", 0).getBoolean("font_custom_wght", false) ? (Typeface) ycVar.b : Typeface.create(typeface3, 1);
            d = !context.getSharedPreferences("panel", 0).getBoolean("font_custom_kk", false);
            return;
        }
        ArrayList arrayList2 = x3.a;
        if ((k8.e(context.getSharedPreferences("panel", 0).getString("font_choice", ""), "manrope") ? "manrope" : "elastre").equals("manrope")) {
            b = new Typeface.Builder(context.getAssets(), "fonts/Manrope.ttf").setFontVariationSettings("'wght' 450").build();
            c = new Typeface.Builder(context.getAssets(), "fonts/Manrope.ttf").setFontVariationSettings("'wght' 600").build();
            d = true;
        } else {
            Typeface typefaceBuild = new Typeface.Builder(context.getAssets(), "fonts/Elastre.ttf").build();
            b = typefaceBuild;
            c = Typeface.create(typefaceBuild, 1);
            d = true;
        }
    }
}
