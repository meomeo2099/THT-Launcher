package defpackage;

import android.content.Context;
import android.content.res.Configuration;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class j0 {
    public static final List a = j3.i0("Русский", "English", "Français", "العربية", "Қазақша");

    public static void a(Context context) {
        String string = context.getSharedPreferences("panel", 0).getString("app_language", null);
        if (string == null) {
            String language = bf.b().getLanguage();
            language.getClass();
            Locale locale = Locale.ROOT;
            locale.getClass();
            string = language.toLowerCase(locale);
            string.getClass();
        }
        String str = c().contains(string) ? string : null;
        if (str == null) {
            str = "ru";
        }
        d(context, str);
        List list = si.a;
        Locale.setDefault(si.b.b);
    }

    public static boolean b() {
        return c().size() > 1;
    }

    public static ArrayList c() {
        List listJ = pi.J("ru");
        int i = 0;
        List listD = th.D("kk", new char[]{','});
        ArrayList arrayList = new ArrayList(k3.n0(listD));
        Iterator it = listD.iterator();
        while (it.hasNext()) {
            arrayList.add(th.J((String) it.next()).toString());
        }
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            String str = (String) obj;
            if (str.length() > 0 && !str.equals("ru") && si.a.contains(str)) {
                arrayList2.add(obj);
            }
        }
        return i3.D0(listJ, arrayList2);
    }

    public static void d(Context context, String str) throws IllegalAccessException, IOException, InvocationTargetException {
        Map linkedHashMap;
        Map map;
        Map mapSingletonMap = v4.a;
        List list = si.a;
        if (si.b.a.equals(str)) {
            return;
        }
        if (str.equals("ru")) {
            linkedHashMap = mapSingletonMap;
        } else {
            InputStream inputStreamOpen = context.getAssets().open("i18n/" + str + ".json");
            inputStreamOpen.getClass();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamOpen, q2.a), 8192);
            try {
                JSONObject jSONObject = new JSONObject(sh.l(bufferedReader));
                bufferedReader.close();
                Iterator<String> itKeys = jSONObject.keys();
                itKeys.getClass();
                s3 s3Var = new s3(new y0(4, itKeys));
                linkedHashMap = new LinkedHashMap();
                for (Object obj : s3Var) {
                    linkedHashMap.put(obj, jSONObject.getString((String) obj));
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    k8.p(bufferedReader, th);
                    throw th2;
                }
            }
        }
        if (!si.a.contains(str)) {
            w.a("Failed requirement.");
            return;
        }
        if (!str.equals("ru")) {
            int size = linkedHashMap.size();
            if (size != 0) {
                if (size != 1) {
                    mapSingletonMap = new LinkedHashMap(linkedHashMap);
                } else {
                    Map.Entry entry = (Map.Entry) linkedHashMap.entrySet().iterator().next();
                    mapSingletonMap = Collections.singletonMap(entry.getKey(), entry.getValue());
                    mapSingletonMap.getClass();
                }
            }
            if (k8.e(mapSingletonMap.keySet(), qi.a.keySet())) {
                Collection collectionValues = mapSingletonMap.values();
                if (collectionValues == null || !collectionValues.isEmpty()) {
                    Iterator it = collectionValues.iterator();
                    while (it.hasNext()) {
                        if (((String) it.next()).length() != 0) {
                        }
                    }
                }
                map = mapSingletonMap;
            }
            throw new IllegalArgumentException("Incomplete UI catalog: ".concat(str).toString());
        }
        map = qi.a;
        Locale localeForLanguageTag = Locale.forLanguageTag(str);
        localeForLanguageTag.getClass();
        si.b = new ri(str, localeForLanguageTag, map);
    }

    public static Context e(Context context) {
        a(context);
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        List list = si.a;
        configuration.setLocale(si.b.b);
        configuration.setLayoutDirection(si.b.b);
        Context contextCreateConfigurationContext = context.createConfigurationContext(configuration);
        contextCreateConfigurationContext.getClass();
        return contextCreateConfigurationContext;
    }
}
