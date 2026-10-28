package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class i9 {
    public static final Map a = hb.j0(new yc("menu", "O:10 10 4.4|O:22 10 4.4|O:10 22 4.4|O:22 22 4.4"), new yc("gear", "o:16 16 4|s:M13.6 3.5h4.8l.8 3.4 2.5 1.4 3.3-1 2.4 4.1-2.5 2.4v2.4l2.5 2.4-2.4 4.1-3.3-1-2.5 1.4-.8 3.4h-4.8l-.8-3.4-2.5-1.4-3.3 1-2.4-4.1 2.5-2.4v-2.4L4.1 11.4l2.4-4.1 3.3 1 2.5-1.4Z"), new yc("home", "s:M5 14.5 16 5l11 9.5V26a2 2 0 0 1-2 2h-5.5v-7.5h-7V28H7a2 2 0 0 1-2-2Z"), new yc("nav", "s:M16 3.5 26.5 27 16 21.5 5.5 27Z"), new yc("navfill", "f:M16 3.5 26.5 27 16 21.5 5.5 27Z"), new yc("back", "s:M21 6 11 16l10 10"), new yc("plus", "s:M6 16h20M16 6v20"), new yc("handle", "O:11 8 1.6|O:21 8 1.6|O:11 16 1.6|O:21 16 1.6|O:11 24 1.6|O:21 24 1.6"), new yc("favorite", "s:M16 27 5.4 16.8A6.4 6.4 0 0 1 14.8 8l1.2 1.3L17.2 8a6.4 6.4 0 0 1 9.4 8.8Z"), new yc("thermometer", "s:M13 6a3 3 0 0 1 6 0v13a6 6 0 1 1-6 0Z|s:M16 10v12|O:16 24 2"), new yc("roofslider", "r:4 8 24 19 3|s:M9 18h14M16 23V12M12 16l4-4 4 4"), new yc("lane", "s:M5 27 11 5M27 27 21 5|r:12 14 8 9 2"), new yc("park", "r:4 6 24 20 4|s:M11 22V10h5a4 4 0 0 1 0 8h-5"), new yc("screen", "r:4 6 24 18 3|s:M12 28h8M16 24v4"), new yc("pilot", "o:16 17 11|o:16 18 3|s:M5.5 15.5 L13 17.2 M19 17.2 L26.5 15.5 M16 21 V28|s:M16 2.5 L17.1 5 L19.6 6 L17.1 7 L16 9.5 L14.9 7 L12.4 6 L14.9 5 Z"), new yc("music", "s:M12 23.5V7.5l14-3v16|o:8.5 23.5 3.5|o:22.5 20.5 3.5"), new yc("phone", "s:M9.5 4.5h4l2 6-3 2a15 15 0 0 0 7 7l2-3 6 2v4a3 3 0 0 1-3 3C13.5 25.5 6.5 18.5 6.5 7.5a3 3 0 0 1 3-3Z"), new yc("snow", "s:M2 23 L6.5 9 L11 23 M3.7 18 L9.3 18 M13 25 L19 7 M30 11 C23 4 20 11 20 16 C20 23 25 27 30 21"), new yc("auto", "r:3.5 7 25 18 5|s:M11.5 20.5 15.8 11h.4l4.3 9.5M13.1 17h5.8"), new yc("recirc", "s:M3.5 22.5h25M6.5 22.5l2-6a3 3 0 0 1 2.8-2h9.4a3 3 0 0 1 2.8 2l2 6|s:M11 10.5c1.5-3.4 6-5 9.2-3.2L22.5 9|s:M22.8 5.2V9h-3.8"), new yc("rear", "r:4 8 24 16 4|s:M9.5 13.5c1.5-1.5 3 1.5 4.5 0s3 1.5 4.5 0 3 1.5 4.5 0M9.5 18.5c1.5-1.5 3 1.5 4.5 0s3 1.5 4.5 0 3 1.5 4.5 0"), new yc("ss", "s:M26 16A10 10 0 1 1 20.2 6.9|s:M20 3.5 22 7l-3.6 2|s:M11.5 21 16 10.5 20.5 21M13 17.5h6"), new yc("ambient", "s:M13 4.5 15 11l6.5 2L15 15l-2 6.5L11 15l-6.5-2L11 11Z|s:M24 19l1.2 3.3 3.3 1.2-3.3 1.2L24 28l-1.2-3.3-3.3-1.2 3.3-1.2Z"), new yc("leaf", "s:M6 26C4.8 13.7 13.2 5.8 26.5 5.5 27.2 18.8 19 26.4 6 26Z|s:M6 26 19.5 12.5"), new yc("comfort", "s:M8 15v-5a4 4 0 0 1 4-4h8a4 4 0 0 1 4 4v5|s:M5 17a2.5 2.5 0 0 1 5 0v2h12v-2a2.5 2.5 0 0 1 5 0v6a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2Z|s:M9 25v2.5M23 25v2.5"), new yc("sport", "s:M5.2 23a11.5 11.5 0 1 1 21.6 0|s:M16 20.5l6-7|O:16 20.5 2.4|s:M9.5 9.5l1.5 1.5M16 6.5v2M22.5 9.5 21 11"), new yc("sportplus", "s:M3.2 21a10 10 0 1 1 19 0|s:M12.7 18.8l5-6|O:12.7 18.8 2.2|s:M21.5 25h7M25 21.5v7"), new yc("power", "s:M18.5 3.5 7.5 18h7.5l-2.5 10.5L24.5 14H17Z"), new yc("snowmode", "s:M16 3.5v13M10.4 6.8l11.2 6.4M21.6 6.8l-11.2 6.4|s:M4 24c3-2.4 5-2.4 8 0s5 2.4 8 0 5-2.4 8 0"), new yc("offroad", "s:M3 26 11.5 10.5l5.5 9 3.5-5L29 26Z"), new yc("adaptive", "s:M8 4.5v5M8 16v11.5M16 4.5V16M16 22v5.5M24 4.5v3M24 14v13.5|o:8 12.8 3|o:16 19 3|o:24 10.8 3"), new yc("collapse", "s:M8 8l8 7 8-7M8 17l8 7 8-7"), new yc("expand", "s:M8 15l8-7 8 7M8 24l8-7 8 7"), new yc("empty", "s:M16 9v14M9 16h14"), new yc("bt", "s:M9 10.5 22 21.5 16 27V5l6 5.5-13 11"), new yc("wifi", "s:M4 12.5a17 17 0 0 1 24 0M8.2 17a11 11 0 0 1 15.6 0M12.4 21.4a5 5 0 0 1 7.2 0|O:16 25.5 2"), new yc("globe", "o:16 16 11.5|s:M4.5 16h23M16 4.5c3.4 3.4 5 7.4 5 11.5s-1.6 8.1-5 11.5c-3.4-3.4-5-7.4-5-11.5s1.6-8.1 5-11.5Z"), new yc("chev", "s:M12 6l10 10-10 10"), new yc("chevup", "s:M8 20l8-8 8 8"), new yc("chevdown", "s:M8 12l8 8 8-8"), new yc("minus", "s:M8 16h16"), new yc("grid", "r:5 5 9 9 2.5|r:18 5 9 9 2.5|r:5 18 9 9 2.5|r:18 18 9 9 2.5"), new yc("split", "r:4 7 10.5 18 3|r:17.5 7 10.5 18 3"), new yc("clock", "o:16 16 11.5|s:M16 9v7l5 3"), new yc("power", "s:M16 4v11M9.2 8.3a10 10 0 1 0 13.6 0"), new yc("front", "s:M4 24c2.6-9 6.8-13.5 12-13.5S25.4 15 28 24Z|s:M11 19c-1.2-1.4 1.2-2.8 0-4.2M16 18c-1.2-1.4 1.2-2.8 0-4.2M21 19c-1.2-1.4 1.2-2.8 0-4.2"), new yc("close", "s:M9 9l14 14M23 9 9 23"), new yc("moon", "s:M25 19.5A10.5 10.5 0 0 1 12.5 7 10.5 10.5 0 1 0 25 19.5Z"), new yc("sun", "o:16 16 5.5|s:M16 3.5v3M16 25.5v3M3.5 16h3M25.5 16h3M7.2 7.2l2.1 2.1M22.7 22.7l2.1 2.1M7.2 24.8l2.1-2.1M22.7 9.3l2.1-2.1"), new yc("speaker", "s:M4 12.5h5l7-6v19l-7-6H4Z|s:M21 11.5a6 6 0 0 1 0 9M24.5 8a11 11 0 0 1 0 16"), new yc("speaker_off", "s:M4 12.5h5l7-6v19l-7-6H4Z|s:M21 12l7 8M28 12l-7 8"), new yc("carfront", "f:M9.6 7h12.8a3 3 0 0 1 2.8 2l2 5.6A3 3 0 0 1 28 16v6a1.5 1.5 0 0 1-1.5 1.5H25V25a1.5 1.5 0 0 1-3 0v-1.5H10V25a1.5 1.5 0 0 1-3 0v-1.5H5.5A1.5 1.5 0 0 1 4 22v-6a3 3 0 0 1 .8-1.4l2-5.6A3 3 0 0 1 9.6 7Zm.4 2.8-1.6 4.7h15.2L22 9.8ZM8 18a1.6 1.6 0 1 0 0 3.2A1.6 1.6 0 0 0 8 18Zm16 0a1.6 1.6 0 1 0 0 3.2 1.6 1.6 0 0 0 0-3.2Z"), new yc("batt", "f:M11 5h10v3h2.5A2.5 2.5 0 0 1 26 10.5v15a2.5 2.5 0 0 1-2.5 2.5h-15A2.5 2.5 0 0 1 6 25.5v-15A2.5 2.5 0 0 1 8.5 8H11Zm4 7.5V16h-3.5v2.6H15V22h2.6v-3.4H21V16h-3.4v-3.5Z"), new yc("pump", "f:M7 6.5A2.5 2.5 0 0 1 9.5 4h8A2.5 2.5 0 0 1 20 6.5V26h1v2H6v-2h1Zm3 1.5v6h7V8Z|s:M20 12h2.2a1.8 1.8 0 0 1 1.8 1.8v7.4a1.9 1.9 0 0 0 3.8 0V11l-3.3-3.3"), new yc("mic", "f:M16 4a4 4 0 0 1 4 4v8a4 4 0 0 1-8 0V8a4 4 0 0 1 4-4Z|s:M9 15v1a7 7 0 0 0 14 0v-1M16 23v5M12 28h8"), new yc("bolt", "f:M18.1 4.5L10.7 16.6H15.5L13.3 26.2L21.9 12.8H16.8Z"));
    public static final HashMap b = new HashMap();

    public static void a(Canvas canvas, String str, Paint paint, int i, float f, float f2, float f3, float f4) {
        Object ffVar;
        canvas.getClass();
        str.getClass();
        paint.getClass();
        l0 l0Var = ba.h;
        ba baVar = ba.k;
        if (baVar != null) {
            String strReplace = (String) ba.t.get(str);
            if (strReplace == null) {
                strReplace = str.replace('_', '-');
                strReplace.getClass();
            }
            if (baVar.a(canvas, strReplace, i, f, f2, f3)) {
                return;
            }
        }
        int iSave = canvas.save();
        canvas.translate(f, f2);
        float f5 = f3 / 32.0f;
        canvas.scale(f5, f5);
        canvas.save();
        paint.setColor(i);
        paint.setStrokeWidth(f4);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setShader(null);
        HashMap map = b;
        Object obj = map.get(str);
        Object obj2 = obj;
        if (obj == null) {
            Map map2 = a;
            String str2 = (String) map2.get(str);
            if (str2 == null) {
                str2 = (String) hb.g0(map2, "empty");
            }
            List listD = th.D(str2, new char[]{'|'});
            ArrayList arrayList = new ArrayList();
            Iterator it = listD.iterator();
            while (it.hasNext()) {
                try {
                    ffVar = b((String) it.next());
                } catch (Throwable th) {
                    ffVar = new ff(th);
                }
                if (ffVar instanceof ff) {
                    ffVar = null;
                }
                h9 h9Var = (h9) ffVar;
                if (h9Var != null) {
                    arrayList.add(h9Var);
                }
            }
            map.put(str, arrayList);
            obj2 = arrayList;
        }
        for (h9 h9Var2 : (List) obj2) {
            if (h9Var2 instanceof f9) {
                f9 f9Var = (f9) h9Var2;
                paint.setStyle(f9Var.b ? Paint.Style.FILL : Paint.Style.STROKE);
                canvas.drawPath(f9Var.a, paint);
            } else if (h9Var2 instanceof e9) {
                e9 e9Var = (e9) h9Var2;
                paint.setStyle(e9Var.d ? Paint.Style.FILL : Paint.Style.STROKE);
                canvas.drawCircle(e9Var.a, e9Var.b, e9Var.c, paint);
            } else {
                if (!(h9Var2 instanceof g9)) {
                    throw new vc();
                }
                g9 g9Var = (g9) h9Var2;
                paint.setStyle(g9Var.f ? Paint.Style.FILL : Paint.Style.STROKE);
                float f6 = g9Var.a;
                float f7 = g9Var.b;
                float f8 = g9Var.c + f6;
                float f9 = g9Var.d + f7;
                float f10 = g9Var.e;
                canvas.drawRoundRect(f6, f7, f8, f9, f10, f10, paint);
            }
        }
        paint.setShader(null);
        canvas.restore();
        canvas.restoreToCount(iSave);
    }

    public static h9 b(String str) {
        char cCharAt = str.charAt(0);
        String strSubstring = str.substring(2);
        if (cCharAt != 'O') {
            if (cCharAt != 'R') {
                if (cCharAt != 'f') {
                    if (cCharAt != 'o') {
                        if (cCharAt != 'r') {
                            if (cCharAt != 's') {
                                throw new IllegalStateException("glyph part");
                            }
                        }
                    }
                }
                Path pathA = ei.a(strSubstring);
                pathA.setFillType(Path.FillType.EVEN_ODD);
                return new f9(pathA, cCharAt == 'f');
            }
            ArrayList arrayListC = c(strSubstring);
            return new g9(((Number) arrayListC.get(0)).floatValue(), ((Number) arrayListC.get(1)).floatValue(), ((Number) arrayListC.get(2)).floatValue(), ((Number) arrayListC.get(3)).floatValue(), ((Number) arrayListC.get(4)).floatValue(), cCharAt == 'R');
        }
        ArrayList arrayListC2 = c(strSubstring);
        return new e9(((Number) arrayListC2.get(0)).floatValue(), ((Number) arrayListC2.get(1)).floatValue(), ((Number) arrayListC2.get(2)).floatValue(), cCharAt == 'O');
    }

    public static final ArrayList c(String str) {
        List listJ;
        String string = th.J(str).toString();
        Pattern patternCompile = Pattern.compile("[ ,]+");
        patternCompile.getClass();
        string.getClass();
        Matcher matcher = patternCompile.matcher(string);
        if (matcher.find()) {
            ArrayList arrayList = new ArrayList(10);
            int iEnd = 0;
            do {
                arrayList.add(string.subSequence(iEnd, matcher.start()).toString());
                iEnd = matcher.end();
            } while (matcher.find());
            arrayList.add(string.subSequence(iEnd, string.length()).toString());
            listJ = arrayList;
        } else {
            listJ = pi.J(string.toString());
        }
        ArrayList arrayList2 = new ArrayList(k3.n0(listJ));
        Iterator it = listJ.iterator();
        while (it.hasNext()) {
            arrayList2.add(Float.valueOf(Float.parseFloat((String) it.next())));
        }
        return arrayList2;
    }
}
