package defpackage;

import android.content.Context;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class n implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Context b;

    public /* synthetic */ n(Context context, int i) {
        this.a = i;
        this.b = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object ffVar;
        Object ffVar2;
        int i = 0;
        switch (this.a) {
            case 0:
                Context context = this.b;
                l0 l0Var = r4.C;
                context.getClass();
                r4 r4VarP = l0Var.p(context);
                Object obj = null;
                if (r4VarP != null) {
                    if (!r4VarP.m()) {
                        r4VarP = null;
                    }
                    if (r4VarP != null) {
                        try {
                            ffVar = r4VarP.q();
                        } catch (Throwable th) {
                            ffVar = new ff(th);
                        }
                        obj = (v2) (ffVar instanceof ff ? null : ffVar);
                    }
                    break;
                }
                q.c.post(new o(context, obj, 0));
                return;
            default:
                Context context2 = this.b;
                md mdVar = md.a;
                try {
                    InputStream inputStreamOpen = context2.getAssets().open("radio/guide.json");
                    inputStreamOpen.getClass();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamOpen, q2.a), 8192);
                    try {
                        String strL = sh.l(bufferedReader);
                        bufferedReader.close();
                        JSONArray jSONArray = new JSONObject(strL).getJSONArray("cities");
                        j8 j8VarF0 = pi.f0(0, jSONArray.length());
                        ArrayList arrayList = new ArrayList(k3.n0(j8VarF0));
                        Iterator it = j8VarF0.iterator();
                        while (true) {
                            int i2 = 4;
                            int i3 = 16;
                            if (!((i8) it).c) {
                                md.d = arrayList;
                                InputStream inputStreamOpen2 = context2.getAssets().open("radio/logos.json");
                                inputStreamOpen2.getClass();
                                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStreamOpen2, q2.a), 8192);
                                try {
                                    String strL2 = sh.l(bufferedReader2);
                                    bufferedReader2.close();
                                    JSONObject jSONObject = new JSONObject(strL2).getJSONObject("logos");
                                    Iterator<String> itKeys = jSONObject.keys();
                                    itKeys.getClass();
                                    s3 s3Var = new s3(new y0(i2, itKeys));
                                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                                    for (Object obj2 : s3Var) {
                                        linkedHashMap.put(obj2, jSONObject.getString((String) obj2));
                                    }
                                    Set<Map.Entry> setEntrySet = linkedHashMap.entrySet();
                                    int iI0 = hb.i0(k3.n0(setEntrySet));
                                    if (iI0 >= 16) {
                                        i3 = iI0;
                                    }
                                    LinkedHashMap linkedHashMap2 = new LinkedHashMap(i3);
                                    for (Map.Entry entry : setEntrySet) {
                                        Object key = entry.getKey();
                                        key.getClass();
                                        linkedHashMap2.put(md.b((String) key), entry.getValue());
                                    }
                                    md.f = linkedHashMap2;
                                    md.e = linkedHashMap;
                                    ffVar2 = ti.a;
                                    Throwable thA = gf.a(ffVar2);
                                    if (thA != null) {
                                        Log.w("ReferencePanel.RadioGuide", "guide not read", thA);
                                        return;
                                    }
                                    return;
                                } catch (Throwable th2) {
                                    try {
                                        throw th2;
                                    } catch (Throwable th3) {
                                        k8.p(bufferedReader2, th2);
                                        throw th3;
                                    }
                                }
                            }
                            JSONArray jSONArray2 = jSONArray.getJSONArray(((i8) it).nextInt());
                            JSONArray jSONArray3 = jSONArray2.getJSONArray(4);
                            String string = jSONArray2.getString(i);
                            string.getClass();
                            String string2 = jSONArray2.getString(1);
                            string2.getClass();
                            double d = jSONArray2.getDouble(2);
                            double d2 = jSONArray2.getDouble(3);
                            j8 j8VarF1 = pi.f0(i, jSONArray3.length());
                            int iI1 = hb.i0(k3.n0(j8VarF1));
                            if (iI1 >= 16) {
                                i3 = iI1;
                            }
                            LinkedHashMap linkedHashMap3 = new LinkedHashMap(i3);
                            Iterator it2 = j8VarF1.iterator();
                            while (((i8) it2).c) {
                                JSONArray jSONArray4 = jSONArray3.getJSONArray(((i8) it2).nextInt());
                                linkedHashMap3.put(Integer.valueOf(jSONArray4.getInt(i)), jSONArray4.getString(1));
                                i = 0;
                            }
                            arrayList.add(new ld(string, string2, d, d2, linkedHashMap3));
                            i = 0;
                        }
                    } catch (Throwable th4) {
                        try {
                            throw th4;
                        } catch (Throwable th5) {
                            k8.p(bufferedReader, th4);
                            throw th5;
                        }
                    }
                } catch (Throwable th6) {
                    ffVar2 = new ff(th6);
                }
                break;
        }
    }
}
