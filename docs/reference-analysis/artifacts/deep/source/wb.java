package defpackage;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class wb {
    public static final Map a;

    static {
        Map mapJ0 = hb.j0(new yc(xb.a, j3.i0("play", "resume", "start", "play track", "воспроизвести", "воспроизведение", "играть", "старт", "продолжить", "lecture", "lire", "reprendre", "demarrer", "تشغيل", "استئناف")), new yc(xb.b, j3.i0("pause", "pause playback", "пауза", "приостановить", "mettre en pause", "إيقاف مؤقت", "ايقاف مؤقت")), new yc(xb.c, j3.i0("play pause", "playpause", "воспроизвести пауза", "воспроизведение пауза", "lecture pause", "تشغيل إيقاف مؤقت")), new yc(xb.d, j3.i0("next", "next track", "next song", "skip next", "skip to next", "следующий", "следующая", "следующий трек", "следующая песня", "далее", "suivant", "piste suivante", "titre suivant", "التالي", "المقطع التالي")), new yc(xb.e, j3.i0("previous", "prev", "previous track", "previous song", "skip previous", "skip to previous", "предыдущий", "предыдущая", "предыдущий трек", "предыдущая песня", "назад", "precedent", "piste precedente", "titre precedent", "السابق", "المقطع السابق")));
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : mapJ0.entrySet()) {
            xb xbVar = (xb) entry.getKey();
            List list = (List) entry.getValue();
            ArrayList arrayList2 = new ArrayList(k3.n0(list));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList2.add(new yc(a((String) it.next()), xbVar));
            }
            o3.p0(arrayList2, arrayList);
        }
        a = hb.l0(arrayList);
    }

    public static String a(String str) {
        Locale locale = Locale.ROOT;
        locale.getClass();
        String lowerCase = str.toLowerCase(locale);
        lowerCase.getClass();
        String strNormalize = Normalizer.normalize(lowerCase, Normalizer.Form.NFKD);
        strNormalize.getClass();
        Pattern patternCompile = Pattern.compile("\\p{M}+");
        patternCompile.getClass();
        String strReplaceAll = patternCompile.matcher(strNormalize).replaceAll("");
        strReplaceAll.getClass();
        Pattern patternCompile2 = Pattern.compile("[^\\p{L}\\p{N}]+");
        patternCompile2.getClass();
        String strReplaceAll2 = patternCompile2.matcher(strReplaceAll).replaceAll(" ");
        strReplaceAll2.getClass();
        String string = th.J(strReplaceAll2).toString();
        Pattern patternCompile3 = Pattern.compile("\\s+");
        patternCompile3.getClass();
        string.getClass();
        String strReplaceAll3 = patternCompile3.matcher(string).replaceAll(" ");
        strReplaceAll3.getClass();
        return strReplaceAll3;
    }
}
