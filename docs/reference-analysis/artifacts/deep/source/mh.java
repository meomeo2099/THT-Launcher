package defpackage;

import android.database.Cursor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class mh {
    public static final SimpleDateFormat a;
    public static final SimpleDateFormat b;

    static {
        Locale locale = Locale.US;
        a = new SimpleDateFormat("yyyy-MM-dd", locale);
        b = new SimpleDateFormat("yyyy-MM", locale);
    }

    public static ArrayList a(kh khVar, lh lhVar, long j, long j2) throws IllegalAccessException, IOException, InvocationTargetException {
        khVar.getClass();
        lhVar.getClass();
        String str = lhVar.b ? "%Y-%m" : "%Y-%m-%d";
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Cursor cursorRawQuery = khVar.getReadableDatabase().rawQuery("SELECT strftime(?, endedAt/1000, 'unixepoch', 'localtime') AS bucket,\n       SUM(distanceKm)\nFROM trips WHERE endedAt >= ? AND endedAt < ?\nGROUP BY bucket ORDER BY bucket", new String[]{str, String.valueOf(j), String.valueOf(j2)});
        while (cursorRawQuery.moveToNext()) {
            try {
                linkedHashMap.put(cursorRawQuery.getString(0), Float.valueOf(cursorRawQuery.getFloat(1)));
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    k8.p(cursorRawQuery, th);
                    throw th2;
                }
            }
        }
        cursorRawQuery.close();
        ArrayList arrayList = new ArrayList();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        while (calendar.getTimeInMillis() < j2) {
            Date time = calendar.getTime();
            if (lhVar.b) {
                List list = si.a;
                String str2 = new SimpleDateFormat("LLL", si.b.b).format(time);
                str2.getClass();
                Float f = (Float) linkedHashMap.get(b.format(time));
                arrayList.add(new ih(str2, f != null ? f.floatValue() : 0.0f));
                calendar.add(2, 1);
            } else {
                List list2 = si.a;
                String str3 = new SimpleDateFormat("d", si.b.b).format(time);
                str3.getClass();
                Float f2 = (Float) linkedHashMap.get(a.format(time));
                arrayList.add(new ih(str3, f2 != null ? f2.floatValue() : 0.0f));
                calendar.add(6, 1);
            }
        }
        return arrayList;
    }
}
