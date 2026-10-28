package defpackage;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class u2 {
    public final Object a;
    public final Object b;
    public final Object c;

    public u2(final Activity activity, boolean z, String str, int i, j6 j6Var, j6 j6Var2) {
        int i2;
        int i3;
        int i4;
        View decorView;
        Window window;
        str.getClass();
        this.a = j6Var;
        this.b = j6Var2;
        Dialog dialog = new Dialog(activity);
        if (pi.c && (window = dialog.getWindow()) != null) {
            window.setType(2038);
        }
        this.c = dialog;
        final float f = activity.getResources().getDisplayMetrics().density;
        dialog.requestWindowFeature(1);
        int iJ = l8.j(z);
        int iK = l8.k(z);
        LinearLayout linearLayout = new LinearLayout(activity);
        linearLayout.setOrientation(1);
        int i5 = (int) (26.0f * f);
        linearLayout.setPadding(i5, (int) (22.0f * f), i5, (int) (20.0f * f));
        GradientDrawable gradientDrawable = new GradientDrawable();
        if (z) {
            i2 = 38;
            i3 = 49;
            i4 = 32;
        } else {
            i2 = 250;
            i3 = 253;
            i4 = 248;
        }
        gradientDrawable.setColor(Color.rgb(i4, i2, i3));
        gradientDrawable.setCornerRadius((int) (24.0f * f));
        linearLayout.setBackground(gradientDrawable);
        LinearLayout linearLayout2 = new LinearLayout(activity);
        linearLayout2.setGravity(16);
        TextView textView = new TextView(activity);
        textView.setText(str);
        textView.setTextSize(22.0f);
        textView.setTextColor(iJ);
        textView.setTypeface(textView.getTypeface(), 1);
        linearLayout2.addView(textView, new LinearLayout.LayoutParams(0, -2, 1.0f));
        TextView textView2 = new TextView(activity);
        textView2.setTextSize(22.0f);
        textView2.setTextColor(l8.f());
        textView2.setTypeface(textView2.getTypeface(), 1);
        textView2.setText(i + " %");
        linearLayout2.addView(textView2);
        linearLayout.addView(linearLayout2);
        TextView textView3 = new TextView(activity);
        textView3.setText(si.a("transparency_hint", new Object[0]));
        textView3.setTextSize(14.0f);
        textView3.setTextColor(iK);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = (int) (6.0f * f);
        linearLayout.addView(textView3, layoutParams);
        SeekBar seekBar = new SeekBar(activity);
        seekBar.setMax(100);
        seekBar.setProgress(pi.s(i, 0, 100));
        seekBar.setProgressTintList(ColorStateList.valueOf(l8.f()));
        seekBar.setThumbTintList(ColorStateList.valueOf(l8.f()));
        seekBar.setProgressBackgroundTintList(ColorStateList.valueOf(l8.e(z)));
        int i6 = (int) (14.0f * f);
        int i7 = (int) (18.0f * f);
        seekBar.setPadding(i6, i7, i6, i7);
        seekBar.setOnSeekBarChangeListener(new bd(textView2, this, seekBar));
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, (int) (64.0f * f));
        layoutParams2.topMargin = (int) (10.0f * f);
        linearLayout.addView(seekBar, layoutParams2);
        LinearLayout linearLayout3 = new LinearLayout(activity);
        TextView textView4 = new TextView(activity);
        textView4.setText(si.a("transparency_solid", new Object[0]));
        textView4.setTextSize(13.0f);
        textView4.setTextColor(iK);
        linearLayout3.addView(textView4, new LinearLayout.LayoutParams(0, -2, 1.0f));
        TextView textView5 = new TextView(activity);
        textView5.setText(si.a("transparency_clear", new Object[0]));
        textView5.setTextSize(13.0f);
        textView5.setTextColor(iK);
        linearLayout3.addView(textView5);
        linearLayout.addView(linearLayout3);
        View viewA = new i4(activity, z).a(si.a("done", new Object[0]), true, new y1(this, seekBar, 9));
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, (int) (52.0f * f));
        layoutParams3.topMargin = i7;
        linearLayout.addView(viewA, layoutParams3);
        dialog.setContentView(linearLayout);
        Window window2 = dialog.getWindow();
        if (window2 != null) {
            window2.setBackgroundDrawableResource(R.color.transparent);
        }
        Window window3 = dialog.getWindow();
        if (window3 != null && (decorView = window3.getDecorView()) != null) {
            decorView.setSystemUiVisibility(activity.getWindow().getDecorView().getSystemUiVisibility());
        }
        dialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: ad
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
                Dialog dialog2 = (Dialog) this.c;
                Window window4 = dialog2.getWindow();
                if (window4 != null) {
                    int i8 = (int) (displayMetrics.widthPixels * 0.55f);
                    float f2 = f;
                    window4.setLayout(pi.s(i8, (int) (400.0f * f2), (int) (680.0f * f2)), -2);
                }
                Window window5 = dialog2.getWindow();
                if (window5 != null) {
                    window5.setDimAmount(0.35f);
                }
            }
        });
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static LinkedHashMap a(JSONObject jSONObject) throws JSONException {
        Iterator<String> itKeys = jSONObject.keys();
        itKeys.getClass();
        o5 o5Var = new o5(new s3(new y0(4, itKeys)), true, new w(29));
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        n5 n5Var = new n5(o5Var);
        while (n5Var.hasNext()) {
            Object next = n5Var.next();
            String str = (String) next;
            if (str.length() > 200 || jSONObject.length() > 20000) {
                throw new IllegalArgumentException(si.a("too_many_settings", new Object[0]).toString());
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject(str);
            String strE0 = pi.e0(str);
            if (strE0 == null || !strE0.equals(jSONObject2.getString("type"))) {
                throw new IllegalArgumentException(si.a("incompatible_setting_0", str).toString());
            }
            Object objValueOf = jSONObject2.get("value");
            switch (strE0.hashCode()) {
                case -891985903:
                    if (!strE0.equals("string")) {
                        throw new IllegalStateException(si.a("unknown_setting_type", new Object[0]).toString());
                    }
                    if (!(objValueOf instanceof String) || ((String) objValueOf).length() > 16384) {
                        w.a("Failed requirement.");
                        return null;
                    }
                    linkedHashMap.put(next, objValueOf);
                    break;
                    break;
                case 104431:
                    if (!strE0.equals("int")) {
                        throw new IllegalStateException(si.a("unknown_setting_type", new Object[0]).toString());
                    }
                    if (objValueOf instanceof Number) {
                        Number number = (Number) objValueOf;
                        if (number.doubleValue() == number.intValue()) {
                            int iIntValue = number.intValue();
                            if (str.equals("menu_count") && (1 > iIntValue || iIntValue >= 20001)) {
                                throw new IllegalArgumentException(si.a("invalid_button_count_in_archive", new Object[0]).toString());
                            }
                            if (str.startsWith("tile_opacity_") && (iIntValue < 0 || iIntValue >= 101)) {
                                w.a("Failed requirement.");
                                return null;
                            }
                            objValueOf = Integer.valueOf(iIntValue);
                            linkedHashMap.put(next, objValueOf);
                            break;
                        }
                    }
                    w.a("Failed requirement.");
                    return null;
                case 113762:
                    if (!strE0.equals("set")) {
                        throw new IllegalStateException(si.a("unknown_setting_type", new Object[0]).toString());
                    }
                    if (objValueOf instanceof JSONArray) {
                        JSONArray jSONArray = (JSONArray) objValueOf;
                        if (jSONArray.length() <= 20000) {
                            j8 j8VarF0 = pi.f0(0, jSONArray.length());
                            ArrayList arrayList = new ArrayList(k3.n0(j8VarF0));
                            Iterator it = j8VarF0.iterator();
                            while (true) {
                                i8 i8Var = (i8) it;
                                if (!i8Var.c) {
                                    objValueOf = i3.L0(arrayList);
                                    break;
                                }
                                Object obj = jSONArray.get(i8Var.nextInt());
                                if (obj instanceof String) {
                                    String str2 = (String) obj;
                                    if (str2.length() <= 500) {
                                        arrayList.add(str2);
                                    }
                                }
                                w.a("Failed requirement.");
                                return null;
                            }
                            linkedHashMap.put(next, objValueOf);
                            break;
                        }
                    }
                    w.a("Failed requirement.");
                    return null;
                case 64711720:
                    if (!strE0.equals("boolean")) {
                        throw new IllegalStateException(si.a("unknown_setting_type", new Object[0]).toString());
                    }
                    if (!(objValueOf instanceof Boolean)) {
                        w.a("Failed requirement.");
                        return null;
                    }
                    linkedHashMap.put(next, objValueOf);
                    break;
                    break;
                case 97526364:
                    if (!strE0.equals("float")) {
                        throw new IllegalStateException(si.a("unknown_setting_type", new Object[0]).toString());
                    }
                    if (objValueOf instanceof Number) {
                        Number number2 = (Number) objValueOf;
                        float fFloatValue = number2.floatValue();
                        if (Math.abs(fFloatValue) <= Float.MAX_VALUE) {
                            String strK = uh.k(str);
                            if (strK == null) {
                                strK = str;
                            }
                            if (strK.equals("ui_scale")) {
                                if (0.75f > fFloatValue || fFloatValue > 1.5f) {
                                }
                                objValueOf = Float.valueOf(number2.floatValue());
                            } else if (strK.equals("tank_liters")) {
                                if (20.0f > fFloatValue || fFloatValue > 150.0f) {
                                }
                                objValueOf = Float.valueOf(number2.floatValue());
                            } else {
                                if (0.0f > fFloatValue || fFloatValue > 1.0f) {
                                }
                                objValueOf = Float.valueOf(number2.floatValue());
                            }
                            linkedHashMap.put(next, objValueOf);
                            break;
                        }
                    }
                    throw new IllegalArgumentException(si.a("invalid_value_0", str).toString());
                default:
                    throw new IllegalStateException(si.a("unknown_setting_type", new Object[0]).toString());
            }
        }
        return linkedHashMap;
    }

    public static JSONObject b(Map map) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            Object value = entry.getValue();
            String strE0 = pi.e0(str);
            if (strE0 != null) {
                JSONObject jSONObjectPut = new JSONObject().put("type", strE0);
                if (value instanceof Set) {
                    value = new JSONArray((Collection) i3.J0((Iterable) value));
                }
                jSONObject.put(str, jSONObjectPut.put("value", value));
            }
        }
        return jSONObject;
    }

    public void c(Uri uri) throws IllegalAccessException, IOException, InvocationTargetException {
        uri.getClass();
        OutputStream outputStreamOpenOutputStream = ((Context) this.a).getContentResolver().openOutputStream(uri, "wt");
        int i = 0;
        if (outputStreamOpenOutputStream == null) {
            throw new IllegalStateException(si.a("could_not_open_file_for_writing", new Object[0]).toString());
        }
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(outputStreamOpenOutputStream);
            try {
                zipOutputStream.putNextEntry(new ZipEntry("settings.json"));
                JSONObject jSONObjectPut = new JSONObject().put("app", "ReferencePanel").put("version", 1);
                Map<String, ?> all = ((SharedPreferences) this.b).getAll();
                all.getClass();
                String string = jSONObjectPut.put("preferences", b(all)).toString();
                string.getClass();
                byte[] bytes = string.getBytes(q2.a);
                bytes.getClass();
                zipOutputStream.write(bytes);
                zipOutputStream.closeEntry();
                ArrayList arrayListD = d();
                int size = arrayListD.size();
                while (i < size) {
                    Object obj = arrayListD.get(i);
                    i++;
                    File file = (File) obj;
                    zipOutputStream.putNextEntry(new ZipEntry("images/" + file.getName()));
                    FileInputStream fileInputStream = new FileInputStream(file);
                    try {
                        pi.u(fileInputStream, zipOutputStream);
                        fileInputStream.close();
                        zipOutputStream.closeEntry();
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            k8.p(fileInputStream, th);
                            throw th2;
                        }
                    }
                }
                zipOutputStream.close();
                outputStreamOpenOutputStream.close();
            } catch (Throwable th3) {
                try {
                    throw th3;
                } catch (Throwable th4) {
                    k8.p(zipOutputStream, th3);
                    throw th4;
                }
            }
        } catch (Throwable th5) {
            try {
                throw th5;
            } catch (Throwable th6) {
                k8.p(outputStreamOpenOutputStream, th5);
                throw th6;
            }
        }
    }

    public ArrayList d() {
        File[] fileArrListFiles = ((Context) this.a).getFilesDir().listFiles();
        if (fileArrListFiles == null) {
            fileArrListFiles = new File[0];
        }
        ArrayList arrayList = new ArrayList();
        for (File file : fileArrListFiles) {
            if (file.isFile()) {
                me meVar = (me) this.c;
                String name = file.getName();
                name.getClass();
                if (meVar.a(name)) {
                    arrayList.add(file);
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0238 A[Catch: all -> 0x0092, LOOP:8: B:100:0x0236->B:101:0x0238, LOOP_END, TryCatch #1 {all -> 0x0092, blocks: (B:4:0x0026, B:13:0x004a, B:15:0x0053, B:17:0x006f, B:19:0x0078, B:21:0x008f, B:24:0x0095, B:26:0x009e, B:28:0x00ad, B:29:0x00b0, B:30:0x00b3, B:32:0x00ba, B:36:0x00d8, B:39:0x00de, B:42:0x00e9, B:43:0x0102, B:44:0x0103, B:46:0x0110, B:49:0x0123, B:52:0x0149, B:54:0x0154, B:57:0x016e, B:59:0x0175, B:63:0x0199, B:65:0x01a5, B:82:0x01e9, B:68:0x01b9, B:70:0x01c0, B:72:0x01ca, B:74:0x01d0, B:93:0x0214, B:95:0x021f, B:96:0x022b, B:98:0x0231, B:99:0x0234, B:101:0x0238, B:102:0x0251, B:103:0x0254, B:83:0x01ed, B:85:0x01f8, B:88:0x0207, B:89:0x020c, B:90:0x020d, B:50:0x0129, B:51:0x0147, B:104:0x0255, B:105:0x025a, B:106:0x025b, B:107:0x026d, B:108:0x026e, B:109:0x0280, B:167:0x0360, B:168:0x0372, B:165:0x035c, B:166:0x035f, B:7:0x0038, B:12:0x0047, B:161:0x0356, B:162:0x0359, B:159:0x0354, B:9:0x003f, B:113:0x0287, B:115:0x0294, B:117:0x029c, B:119:0x02a2, B:121:0x02a8, B:123:0x02ae, B:128:0x02bd, B:149:0x031a, B:153:0x032a, B:154:0x032d, B:155:0x032e, B:156:0x0340, B:157:0x0341, B:158:0x0353, B:163:0x035a), top: B:174:0x0026, inners: #0, #7 }] */
    /* JADX WARN: Code duplicated, block: B:95:0x021f A[Catch: all -> 0x0092, LOOP:7: B:94:0x021d->B:95:0x021f, LOOP_END, TryCatch #1 {all -> 0x0092, blocks: (B:4:0x0026, B:13:0x004a, B:15:0x0053, B:17:0x006f, B:19:0x0078, B:21:0x008f, B:24:0x0095, B:26:0x009e, B:28:0x00ad, B:29:0x00b0, B:30:0x00b3, B:32:0x00ba, B:36:0x00d8, B:39:0x00de, B:42:0x00e9, B:43:0x0102, B:44:0x0103, B:46:0x0110, B:49:0x0123, B:52:0x0149, B:54:0x0154, B:57:0x016e, B:59:0x0175, B:63:0x0199, B:65:0x01a5, B:82:0x01e9, B:68:0x01b9, B:70:0x01c0, B:72:0x01ca, B:74:0x01d0, B:93:0x0214, B:95:0x021f, B:96:0x022b, B:98:0x0231, B:99:0x0234, B:101:0x0238, B:102:0x0251, B:103:0x0254, B:83:0x01ed, B:85:0x01f8, B:88:0x0207, B:89:0x020c, B:90:0x020d, B:50:0x0129, B:51:0x0147, B:104:0x0255, B:105:0x025a, B:106:0x025b, B:107:0x026d, B:108:0x026e, B:109:0x0280, B:167:0x0360, B:168:0x0372, B:165:0x035c, B:166:0x035f, B:7:0x0038, B:12:0x0047, B:161:0x0356, B:162:0x0359, B:159:0x0354, B:9:0x003f, B:113:0x0287, B:115:0x0294, B:117:0x029c, B:119:0x02a2, B:121:0x02a8, B:123:0x02ae, B:128:0x02bd, B:149:0x031a, B:153:0x032a, B:154:0x032d, B:155:0x032e, B:156:0x0340, B:157:0x0341, B:158:0x0353, B:163:0x035a), top: B:174:0x0026, inners: #0, #7 }] */
    /* JADX WARN: Code duplicated, block: B:98:0x0231 A[Catch: all -> 0x0092, TryCatch #1 {all -> 0x0092, blocks: (B:4:0x0026, B:13:0x004a, B:15:0x0053, B:17:0x006f, B:19:0x0078, B:21:0x008f, B:24:0x0095, B:26:0x009e, B:28:0x00ad, B:29:0x00b0, B:30:0x00b3, B:32:0x00ba, B:36:0x00d8, B:39:0x00de, B:42:0x00e9, B:43:0x0102, B:44:0x0103, B:46:0x0110, B:49:0x0123, B:52:0x0149, B:54:0x0154, B:57:0x016e, B:59:0x0175, B:63:0x0199, B:65:0x01a5, B:82:0x01e9, B:68:0x01b9, B:70:0x01c0, B:72:0x01ca, B:74:0x01d0, B:93:0x0214, B:95:0x021f, B:96:0x022b, B:98:0x0231, B:99:0x0234, B:101:0x0238, B:102:0x0251, B:103:0x0254, B:83:0x01ed, B:85:0x01f8, B:88:0x0207, B:89:0x020c, B:90:0x020d, B:50:0x0129, B:51:0x0147, B:104:0x0255, B:105:0x025a, B:106:0x025b, B:107:0x026d, B:108:0x026e, B:109:0x0280, B:167:0x0360, B:168:0x0372, B:165:0x035c, B:166:0x035f, B:7:0x0038, B:12:0x0047, B:161:0x0356, B:162:0x0359, B:159:0x0354, B:9:0x003f, B:113:0x0287, B:115:0x0294, B:117:0x029c, B:119:0x02a2, B:121:0x02a8, B:123:0x02ae, B:128:0x02bd, B:149:0x031a, B:153:0x032a, B:154:0x032d, B:155:0x032e, B:156:0x0340, B:157:0x0341, B:158:0x0353, B:163:0x035a), top: B:174:0x0026, inners: #0, #7 }] */
    public void e(Uri uri) throws IOException {
        Map mapSingletonMap;
        File file;
        Context context;
        ArrayList arrayListD;
        int size;
        int i;
        File[] fileArrListFiles;
        int i2;
        int i3;
        u2 u2Var = this;
        String str = "images/";
        me meVar = (me) u2Var.c;
        uri.getClass();
        Context context2 = (Context) u2Var.a;
        File fileCreateTempFile = File.createTempFile("settings-import-", "", context2.getCacheDir());
        fileCreateTempFile.delete();
        if (!fileCreateTempFile.mkdir()) {
            throw new IllegalStateException("Check failed.");
        }
        try {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            InputStream inputStreamOpenInputStream = context2.getContentResolver().openInputStream(uri);
            int i4 = 0;
            if (inputStreamOpenInputStream == null) {
                throw new IllegalStateException(si.a("archive_unavailable", new Object[0]).toString());
            }
            try {
                ZipInputStream zipInputStream = new ZipInputStream(inputStreamOpenInputStream);
                long j = 0;
                loop0: while (true) {
                    try {
                        ZipEntry nextEntry = zipInputStream.getNextEntry();
                        if (nextEntry == null) {
                            zipInputStream.close();
                            inputStreamOpenInputStream.close();
                            if (!linkedHashSet.contains("settings.json")) {
                                throw new IllegalArgumentException(si.a("this_is_not_an_ReferencePanel_backup", new Object[0]).toString());
                            }
                            JSONObject jSONObject = new JSONObject(m5.k0(new File(fileCreateTempFile, "settings.json")));
                            if (!k8.e(jSONObject.getString("app"), "ReferencePanel") || jSONObject.getInt("version") != 1) {
                                throw new IllegalArgumentException(si.a("unsupported_archive_version", new Object[0]).toString());
                            }
                            JSONObject jSONObject2 = jSONObject.getJSONObject("preferences");
                            jSONObject2.getClass();
                            LinkedHashMap linkedHashMapL = pi.L(a(jSONObject2));
                            File[] fileArrListFiles2 = fileCreateTempFile.listFiles();
                            if (fileArrListFiles2 == null) {
                                fileArrListFiles2 = new File[i4];
                            }
                            ArrayList arrayList = new ArrayList();
                            int length = fileArrListFiles2.length;
                            for (int i5 = i4; i5 < length; i5++) {
                                File file2 = fileArrListFiles2[i5];
                                String name = file2.getName();
                                name.getClass();
                                if (meVar.a(name)) {
                                    arrayList.add(file2);
                                }
                            }
                            int size2 = arrayList.size();
                            int i6 = i4;
                            while (i6 < size2) {
                                Object obj = arrayList.get(i6);
                                i6++;
                                File file3 = (File) obj;
                                BitmapFactory.Options options = new BitmapFactory.Options();
                                options.inJustDecodeBounds = true;
                                BitmapFactory.decodeFile(file3.getAbsolutePath(), options);
                                int i7 = options.outWidth;
                                if (1 > i7 || i7 >= 8193 || 1 > (i3 = options.outHeight) || i3 >= 8193 || ((long) i7) * ((long) i3) > 24000000) {
                                    List list = si.a;
                                    throw new IllegalArgumentException(si.a("invalid_image_0", file3.getName()).toString());
                                }
                            }
                            File file4 = new File(fileCreateTempFile, "previous");
                            if (!file4.mkdir()) {
                                throw new IllegalStateException("Check failed.");
                            }
                            Map<String, ?> all = ((SharedPreferences) u2Var.b).getAll();
                            all.getClass();
                            int size3 = all.size();
                            if (size3 == 0) {
                                mapSingletonMap = v4.a;
                            } else if (size3 != 1) {
                                mapSingletonMap = new LinkedHashMap(all);
                            } else {
                                Map.Entry<String, ?> next = all.entrySet().iterator().next();
                                mapSingletonMap = Collections.singletonMap(next.getKey(), next.getValue());
                                mapSingletonMap.getClass();
                            }
                            ArrayList arrayListD2 = u2Var.d();
                            int size4 = arrayListD2.size();
                            int i8 = i4;
                            while (i8 < size4) {
                                Object obj2 = arrayListD2.get(i8);
                                i8++;
                                File file5 = (File) obj2;
                                file5.getClass();
                                m5.h0(file5, new File(file4, file5.getName()), 6);
                            }
                            try {
                                int size5 = arrayList.size();
                                int i9 = i4;
                                while (i9 < size5) {
                                    Object obj3 = arrayList.get(i9);
                                    i9++;
                                    File file6 = (File) obj3;
                                    file6.getClass();
                                    m5.h0(file6, new File(context2.getFilesDir(), file6.getName()), 4);
                                }
                                ArrayList arrayList2 = new ArrayList();
                                int size6 = arrayListD2.size();
                                int i10 = 0;
                                while (i10 < size6) {
                                    Object obj4 = arrayListD2.get(i10);
                                    i10++;
                                    File file7 = (File) obj4;
                                    if (arrayList.isEmpty()) {
                                        file = file4;
                                        context = context2;
                                        try {
                                            arrayList2.add(obj4);
                                        } catch (Exception e) {
                                            e = e;
                                            arrayListD = u2Var.d();
                                            size = arrayListD.size();
                                            i = 0;
                                            while (i < size) {
                                                Object obj5 = arrayListD.get(i);
                                                i++;
                                                ((File) obj5).delete();
                                            }
                                            fileArrListFiles = file.listFiles();
                                            if (fileArrListFiles == null) {
                                                fileArrListFiles = new File[0];
                                            }
                                            for (File file8 : fileArrListFiles) {
                                                file8.getClass();
                                                m5.h0(file8, new File(context.getFilesDir(), file8.getName()), 4);
                                            }
                                            u2Var.i(mapSingletonMap);
                                            throw e;
                                        }
                                    } else {
                                        int size7 = arrayList.size();
                                        int i11 = 0;
                                        while (true) {
                                            if (i11 < size7) {
                                                Object obj6 = arrayList.get(i11);
                                                i11++;
                                                file = file4;
                                                try {
                                                    String name2 = ((File) obj6).getName();
                                                    context = context2;
                                                    if (!k8.e(name2, file7.getName())) {
                                                        context2 = context;
                                                        file4 = file;
                                                    }
                                                } catch (Exception e2) {
                                                    e = e2;
                                                    context = context2;
                                                    arrayListD = u2Var.d();
                                                    size = arrayListD.size();
                                                    i = 0;
                                                    while (i < size) {
                                                        Object obj7 = arrayListD.get(i);
                                                        i++;
                                                        ((File) obj7).delete();
                                                    }
                                                    fileArrListFiles = file.listFiles();
                                                    if (fileArrListFiles == null) {
                                                        fileArrListFiles = new File[0];
                                                    }
                                                    while (i2 < r3) {
                                                        file8.getClass();
                                                        m5.h0(file8, new File(context.getFilesDir(), file8.getName()), 4);
                                                    }
                                                    u2Var.i(mapSingletonMap);
                                                    throw e;
                                                }
                                            } else {
                                                file = file4;
                                                context = context2;
                                                arrayList2.add(obj4);
                                            }
                                        }
                                    }
                                    context2 = context;
                                    file4 = file;
                                }
                                int size8 = arrayList2.size();
                                int i12 = 0;
                                while (i12 < size8) {
                                    Object obj8 = arrayList2.get(i12);
                                    i12++;
                                    if (!((File) obj8).delete()) {
                                        throw new IllegalStateException("Check failed.");
                                    }
                                }
                                u2Var.i(linkedHashMapL);
                                m5.i0(fileCreateTempFile);
                                return;
                            } catch (Exception e3) {
                                e = e3;
                                file = file4;
                            }
                        } else {
                            Context context3 = context2;
                            String name3 = nextEntry.getName();
                            name3.getClass();
                            if (!linkedHashSet.add(name3) || linkedHashSet.size() > 100) {
                                throw new IllegalArgumentException(si.a("duplicate_files_in_archive", new Object[0]).toString());
                            }
                            if (nextEntry.isDirectory() || (!name3.equals("settings.json") && (!name3.startsWith(str) || !meVar.a(th.C(name3, str))))) {
                                throw new IllegalArgumentException(si.a("unsupported_file_in_archive", new Object[0]).toString());
                            }
                            FileOutputStream fileOutputStream = new FileOutputStream(new File(fileCreateTempFile, th.E(name3, '/')));
                            try {
                                byte[] bArr = new byte[8192];
                                long j2 = 0;
                                while (true) {
                                    int i13 = zipInputStream.read(bArr);
                                    if (i13 >= 0) {
                                        String str2 = str;
                                        long j3 = i13;
                                        j2 += j3;
                                        j += j3;
                                        if (j2 > 33554432 || j > 268435456 || (name3.equals("settings.json") && j2 > 4194304)) {
                                            break loop0;
                                        }
                                        fileOutputStream.write(bArr, 0, i13);
                                        str = str2;
                                    }
                                    try {
                                        throw th;
                                    } catch (Throwable th) {
                                        k8.p(zipInputStream, th);
                                        throw th;
                                    }
                                }
                                String str3 = str;
                                fileOutputStream.close();
                                zipInputStream.closeEntry();
                                u2Var = this;
                                str = str3;
                                context2 = context3;
                                i4 = 0;
                            } catch (Throwable th2) {
                                try {
                                    throw th2;
                                } catch (Throwable th3) {
                                    k8.p(fileOutputStream, th2);
                                    throw th3;
                                }
                            }
                        }
                    } catch (Throwable th4) {
                        throw th4;
                    }
                    try {
                        throw th;
                    } catch (Throwable th5) {
                        k8.p(inputStreamOpenInputStream, th);
                        throw th5;
                    }
                }
                throw new IllegalArgumentException(si.a("archive_too_large", new Object[0]).toString());
            } catch (Throwable th6) {
                throw th6;
            }
        } catch (Throwable th7) {
            m5.i0(fileCreateTempFile);
            throw th7;
        }
    }

    public synchronized boolean f(int i, int i2) {
        try {
            List list = z2.a;
            if (!g(i, i2)) {
                return false;
            }
            if (i == 269747200 || i == 269747456) {
                return ((Boolean) ((q4) this.c).a(Integer.valueOf(i), null, Integer.valueOf(i2))).booleanValue() || ((Boolean) ((q4) this.c).a(Integer.valueOf(i), 1, Integer.valueOf(i2))).booleanValue();
            }
            Integer numC = i == 268566784 ? z2.c(Integer.valueOf(i2)) : null;
            if (numC != null) {
                j8 j8Var = new j8(9, 9, 1);
                int iIntValue = numC.intValue();
                if (9 <= iIntValue && iIntValue <= j8Var.b) {
                    return false;
                }
            }
            if (i == 268566784 && i2 == 268566794) {
                return f(268501504, 1);
            }
            if (i == 268566784 && numC != null && numC.intValue() > 0) {
                i2 = numC.intValue() + 268566784;
            } else if (((Boolean) ((d1) this.a).a()).booleanValue() && i == 268632320 && i2 == 268632322) {
                i2 = 0;
            }
            return ((Boolean) ((q4) this.c).a(Integer.valueOf(i), j(i), Integer.valueOf(i2))).booleanValue();
        } catch (Throwable th) {
            throw th;
        }
    }

    /* JADX WARN: Code duplicated, block: B:28:0x004d A[RETURN] */
    public boolean g(int i, int i2) {
        List list = z2.a;
        switch (i) {
            case 268501248:
            case 268501504:
            case 268501760:
            case 268697856:
            case 268698368:
            case 268829952:
            case 269747200:
            case 269747456:
                if (i2 == 0 || i2 == 1) {
                    return true;
                }
                return false;
            case 268566784:
                if (z2.c(Integer.valueOf(i2)) != null) {
                    return true;
                }
                return false;
            case 268632320:
                if ((268632321 > i2 || i2 >= 268632324) && !(((Boolean) ((d1) this.a).a()).booleanValue() && i2 == 0)) {
                    return false;
                }
                return true;
            case 268894464:
                if (z2.a(Integer.valueOf(i2)) != null) {
                    return true;
                }
                return false;
            case 269025536:
                if (z2.i(Integer.valueOf(i2)) != null) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    /* JADX WARN: Code duplicated, block: B:17:0x003f A[Catch: all -> 0x001f, TRY_LEAVE, TryCatch #0 {all -> 0x001f, blocks: (B:7:0x000c, B:21:0x0056, B:25:0x0062, B:29:0x0077, B:31:0x007d, B:10:0x0021, B:12:0x0031, B:17:0x003f), top: B:36:0x0005 }] */
    public synchronized Integer h(int i) {
        Integer num;
        try {
            if (i == 269747200 || i == 269747456) {
                num = (Integer) ((q4) this.b).c(Integer.valueOf(i), null);
                if (num == null) {
                    num = (Integer) ((q4) this.b).c(Integer.valueOf(i), 1);
                } else {
                    if (!g(i, num.intValue())) {
                        num = null;
                    }
                    if (num == null) {
                        num = (Integer) ((q4) this.b).c(Integer.valueOf(i), 1);
                    }
                }
            } else {
                num = (Integer) ((q4) this.b).c(Integer.valueOf(i), j(i));
            }
            if (num == null) {
                return null;
            }
            if (!g(i, num.intValue())) {
                return null;
            }
            if (((Boolean) ((d1) this.a).a()).booleanValue() && i == 268632320 && num.intValue() == 0) {
                num = 268632322;
            }
            return num;
        } catch (Throwable th) {
            throw th;
        }
    }

    public void i(Map map) {
        SharedPreferences.Editor editorClear = ((SharedPreferences) this.b).edit().clear();
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Boolean) {
                editorClear.putBoolean(str, ((Boolean) value).booleanValue());
            } else if (value instanceof Integer) {
                editorClear.putInt(str, ((Number) value).intValue());
            } else if (value instanceof Long) {
                editorClear.putLong(str, ((Number) value).longValue());
            } else if (value instanceof Float) {
                editorClear.putFloat(str, ((Number) value).floatValue());
            } else if (value instanceof String) {
                editorClear.putString(str, (String) value);
            } else if (value instanceof Set) {
                ArrayList arrayList = new ArrayList();
                for (Object obj : (Iterable) value) {
                    if (obj instanceof String) {
                        arrayList.add(obj);
                    }
                }
                editorClear.putStringSet(str, i3.L0(arrayList));
            }
        }
        if (!editorClear.commit()) {
            throw new IllegalStateException(si.a("could_not_save_settings", new Object[0]).toString());
        }
    }

    public Integer j(int i) {
        List list = z2.a;
        if (!((Boolean) ((d1) this.a).a()).booleanValue()) {
            return null;
        }
        if (i != 268501504) {
            return (i == 268566784 || i == 268894464) ? 8 : null;
        }
        return Integer.MIN_VALUE;
    }

    public u2(Context context) {
        this.a = context;
        this.b = context.getSharedPreferences("panel", 0);
        this.c = new me("(?:wallpaper_(?:day|night|screensaver)(?:_[a-z0-9_]{1,64})?|vehicle_[a-z0-9_]+|widget_(?:image|gallery)_[a-f0-9]{32})\\.png");
    }

    public /* synthetic */ u2(Object obj, Object obj2, Object obj3) {
        this.a = obj;
        this.b = obj2;
        this.c = obj3;
    }
}
