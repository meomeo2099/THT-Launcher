package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class kh extends SQLiteOpenHelper {
    public static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    public kh(Context context) {
        super(context.getApplicationContext(), "stats.db", (SQLiteDatabase.CursorFactory) null, 2);
    }

    public static ArrayList f(kh khVar, long j, long j2) throws IllegalAccessException, IOException, InvocationTargetException {
        khVar.getClass();
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = khVar.getReadableDatabase().rawQuery("SELECT id, startedAt, endedAt, distanceKm, driveSec, idleSec,\n       maxSpeed, avgConsumption, fuelUsedL\nFROM trips WHERE endedAt >= ? AND endedAt < ?\nORDER BY endedAt DESC LIMIT ?", new String[]{String.valueOf(j), String.valueOf(j2), String.valueOf(40)});
        while (cursorRawQuery.moveToNext()) {
            try {
                arrayList.add(new li(cursorRawQuery.getLong(0), cursorRawQuery.getLong(1), cursorRawQuery.getLong(2), cursorRawQuery.getFloat(3), cursorRawQuery.getInt(4), cursorRawQuery.getInt(5), cursorRawQuery.getInt(6), cursorRawQuery.getFloat(7), cursorRawQuery.getFloat(8)));
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
        return arrayList;
    }

    public static ArrayList g(kh khVar, long j, long j2) throws IllegalAccessException, IOException, InvocationTargetException {
        int i;
        Object next;
        khVar.getClass();
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = khVar.getReadableDatabase().rawQuery("SELECT ts, v, engine FROM voltage WHERE ts >= ? AND ts < ? ORDER BY ts", new String[]{String.valueOf(j), String.valueOf(j2)});
        while (true) {
            try {
                i = 0;
                boolean z = false;
                if (!cursorRawQuery.moveToNext()) {
                    break;
                }
                long j3 = cursorRawQuery.getLong(0);
                float f = cursorRawQuery.getFloat(1);
                if (cursorRawQuery.getInt(2) == 1) {
                    z = true;
                }
                arrayList.add(new sj(j3, f, z));
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
        if (arrayList.size() <= 360) {
            return arrayList;
        }
        double size = ((double) arrayList.size()) / 180.0d;
        ArrayList arrayList2 = new ArrayList(360);
        while (i < 180) {
            int i2 = (int) (((double) i) * size);
            i++;
            int iMin = Math.min(arrayList.size(), (int) (((double) i) * size));
            int i3 = i2 + 1;
            if (iMin < i3) {
                iMin = i3;
            }
            List listSubList = arrayList.subList(i2, iMin);
            Iterator it = listSubList.iterator();
            Object next2 = null;
            if (it.hasNext()) {
                next = it.next();
                if (it.hasNext()) {
                    float f2 = ((sj) next).b;
                    do {
                        Object next3 = it.next();
                        float f3 = ((sj) next3).b;
                        if (Float.compare(f2, f3) > 0) {
                            next = next3;
                            f2 = f3;
                        }
                    } while (it.hasNext());
                }
            } else {
                next = null;
            }
            next.getClass();
            sj sjVar = (sj) next;
            Iterator it2 = listSubList.iterator();
            if (it2.hasNext()) {
                next2 = it2.next();
                if (it2.hasNext()) {
                    float f4 = ((sj) next2).b;
                    do {
                        Object next4 = it2.next();
                        float f5 = ((sj) next4).b;
                        if (Float.compare(f4, f5) < 0) {
                            next2 = next4;
                            f4 = f5;
                        }
                    } while (it2.hasNext());
                }
            }
            next2.getClass();
            sj sjVar2 = (sj) next2;
            if (sjVar != sjVar2) {
                if (sjVar.a <= sjVar2.a) {
                    arrayList2.add(sjVar);
                    arrayList2.add(sjVar2);
                } else {
                    arrayList2.add(sjVar2);
                }
            }
            arrayList2.add(sjVar);
        }
        return arrayList2;
    }

    public final long a(long j, int i, float f, float f2, float f3, boolean z, boolean z2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("ts", Long.valueOf(j));
        contentValues.put("odometerKm", Integer.valueOf(i));
        contentValues.put("percentBefore", Float.valueOf(f));
        contentValues.put("percentAfter", Float.valueOf(f2));
        contentValues.put("liters", Float.valueOf(f3));
        contentValues.put("full", Integer.valueOf(z ? 1 : 0));
        contentValues.put("manual", Integer.valueOf(z2 ? 1 : 0));
        return getWritableDatabase().insert("refuels", null, contentValues);
    }

    public final ArrayList b() throws IllegalAccessException, IOException, InvocationTargetException {
        boolean z;
        boolean z2;
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = getReadableDatabase().rawQuery("SELECT id, ts, odometerKm, percentBefore, percentAfter, liters, full, manual FROM refuels ORDER BY ts DESC LIMIT ?", new String[]{String.valueOf(30)});
        while (cursorRawQuery.moveToNext()) {
            try {
                long j = cursorRawQuery.getLong(0);
                long j2 = cursorRawQuery.getLong(1);
                int i = cursorRawQuery.getInt(2);
                float f = cursorRawQuery.getFloat(3);
                float f2 = cursorRawQuery.getFloat(4);
                float f3 = cursorRawQuery.getFloat(5);
                if (cursorRawQuery.getInt(6) == 1) {
                    z = true;
                    z2 = true;
                } else {
                    z = true;
                    z2 = false;
                }
                if (cursorRawQuery.getInt(7) != z) {
                    z = false;
                }
                arrayList.add(new pe(j, j2, i, f, f2, f3, z2, z));
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
        return arrayList;
    }

    public final di c(long j, long j2) throws IllegalAccessException, IOException, InvocationTargetException {
        Cursor cursorRawQuery = getReadableDatabase().rawQuery("SELECT IFNULL(SUM(distanceKm),0), COUNT(*), IFNULL(SUM(driveSec),0),\n       IFNULL(SUM(idleSec),0), IFNULL(SUM(fuelUsedL),0)\nFROM trips WHERE endedAt >= ? AND endedAt < ?", new String[]{String.valueOf(j), String.valueOf(j2)});
        try {
            if (cursorRawQuery.moveToFirst()) {
                di diVar = new di(cursorRawQuery.getFloat(0), cursorRawQuery.getInt(1), cursorRawQuery.getInt(2), cursorRawQuery.getInt(3), cursorRawQuery.getFloat(4));
                k8.p(cursorRawQuery, null);
                return diVar;
            }
            di diVar2 = di.f;
            k8.p(cursorRawQuery, null);
            return diVar2;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                k8.p(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    public final di d() throws IllegalAccessException, IOException, InvocationTargetException {
        String str = a.format(new Date(System.currentTimeMillis()));
        str.getClass();
        Cursor cursorRawQuery = getReadableDatabase().rawQuery("SELECT distanceKm, trips, driveSec, idleSec, fuelUsedL FROM days WHERE date = ?", new String[]{str});
        try {
            if (cursorRawQuery.moveToFirst()) {
                di diVar = new di(cursorRawQuery.getFloat(0), cursorRawQuery.getInt(1), cursorRawQuery.getInt(2), cursorRawQuery.getInt(3), cursorRawQuery.getFloat(4));
                cursorRawQuery.close();
                return diVar;
            }
            di diVar2 = di.f;
            cursorRawQuery.close();
            return diVar2;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                k8.p(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    public final di e() throws IllegalAccessException, IOException, InvocationTargetException {
        Cursor cursorRawQuery = getReadableDatabase().rawQuery("SELECT IFNULL(SUM(distanceKm),0), COUNT(*), IFNULL(SUM(driveSec),0),\n       IFNULL(SUM(idleSec),0), IFNULL(SUM(fuelUsedL),0)\nFROM trips", null);
        try {
            if (cursorRawQuery.moveToFirst()) {
                di diVar = new di(cursorRawQuery.getFloat(0), cursorRawQuery.getInt(1), cursorRawQuery.getInt(2), cursorRawQuery.getInt(3), cursorRawQuery.getFloat(4));
                k8.p(cursorRawQuery, null);
                return diVar;
            }
            di diVar2 = di.f;
            k8.p(cursorRawQuery, null);
            return diVar2;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                k8.p(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    public final yc h(long j) throws IllegalAccessException, IOException, InvocationTargetException {
        Cursor cursorRawQuery = getReadableDatabase().rawQuery("SELECT MIN(v), MAX(v), COUNT(*) FROM voltage WHERE ts >= ?", new String[]{String.valueOf(j)});
        try {
            if (cursorRawQuery.moveToFirst() && cursorRawQuery.getInt(2) != 0) {
                yc ycVar = new yc(Float.valueOf(cursorRawQuery.getFloat(0)), Float.valueOf(cursorRawQuery.getFloat(1)));
                k8.p(cursorRawQuery, null);
                return ycVar;
            }
            k8.p(cursorRawQuery, null);
            return null;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                k8.p(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.getClass();
        sQLiteDatabase.execSQL("CREATE TABLE trips (\n    id INTEGER PRIMARY KEY AUTOINCREMENT,\n    startedAt INTEGER NOT NULL,\n    endedAt INTEGER NOT NULL,\n    startOdo INTEGER NOT NULL,\n    endOdo INTEGER NOT NULL,\n    distanceKm REAL NOT NULL,\n    driveSec INTEGER NOT NULL,\n    idleSec INTEGER NOT NULL,\n    maxSpeed INTEGER NOT NULL,\n    avgConsumption REAL NOT NULL,\n    fuelUsedL REAL NOT NULL\n)");
        sQLiteDatabase.execSQL("CREATE TABLE days (\n    date TEXT PRIMARY KEY,\n    distanceKm REAL NOT NULL,\n    trips INTEGER NOT NULL,\n    driveSec INTEGER NOT NULL,\n    idleSec INTEGER NOT NULL,\n    fuelUsedL REAL NOT NULL\n)");
        sQLiteDatabase.execSQL("CREATE TABLE voltage (\n    ts INTEGER PRIMARY KEY,\n    v REAL NOT NULL,\n    engine INTEGER NOT NULL\n)");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS refuels (\n    id INTEGER PRIMARY KEY AUTOINCREMENT,\n    ts INTEGER NOT NULL,\n    odometerKm INTEGER NOT NULL,\n    percentBefore REAL NOT NULL,\n    percentAfter REAL NOT NULL,\n    liters REAL NOT NULL,\n    full INTEGER NOT NULL,\n    manual INTEGER NOT NULL\n)");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.getClass();
        if (i < 2) {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS refuels (\n    id INTEGER PRIMARY KEY AUTOINCREMENT,\n    ts INTEGER NOT NULL,\n    odometerKm INTEGER NOT NULL,\n    percentBefore REAL NOT NULL,\n    percentAfter REAL NOT NULL,\n    liters REAL NOT NULL,\n    full INTEGER NOT NULL,\n    manual INTEGER NOT NULL\n)");
        }
    }
}
