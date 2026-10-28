package defpackage;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class i0 extends Dialog {
    public final Activity a;
    public final boolean b;
    public final String c;
    public final j6 d;
    public final ExecutorService e;
    public List f;
    public List g;
    public boolean h;
    public final SharedPreferences i;
    public final Set j;
    public boolean k;
    public final float l;
    public final int m;
    public final int n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i0(Activity activity, boolean z, String str, boolean z2, j6 j6Var) {
        Window window;
        super(activity);
        activity.getClass();
        str.getClass();
        this.a = activity;
        this.b = z;
        this.c = str;
        this.d = j6Var;
        this.e = Executors.newSingleThreadExecutor();
        u4 u4Var = u4.a;
        this.f = u4Var;
        this.g = u4Var;
        SharedPreferences sharedPreferences = activity.getSharedPreferences("panel", 0);
        this.i = sharedPreferences;
        String[] strArr = {sharedPreferences.getString("slot_2", null), sharedPreferences.getString("slot_3", null)};
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (int i = 0; i < 2; i++) {
            String str2 = strArr[i];
            if (str2 != null) {
                linkedHashSet.add(str2);
            }
        }
        Set<String> stringSet = sharedPreferences.getStringSet("favorite_apps", linkedHashSet);
        this.j = i3.K0(stringSet == null ? x4.a : stringSet);
        this.k = z2;
        this.l = pi.r(sharedPreferences.getFloat("ui_scale", 1.0f), 0.75f, 1.5f);
        this.m = l8.j(z);
        this.n = l8.k(z);
        if (!pi.c || (window = getWindow()) == null) {
            return;
        }
        window.setType(2038);
    }

    public static final void b(g0 g0Var, i0 i0Var, EditText editText, TextView textView, TextView textView2, TextView textView3) {
        String strA;
        String string = th.J(editText.getText().toString()).toString();
        List list = i0Var.f;
        int i = i0Var.m;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            e0 e0Var = (e0) obj;
            if (!i0Var.k || i0Var.j.contains(e0Var.b.flattenToString())) {
                if (!th.v(e0Var.a, string, true)) {
                    String packageName = e0Var.b.getPackageName();
                    packageName.getClass();
                    if (th.v(packageName, string, true)) {
                    }
                }
                arrayList.add(obj);
            }
        }
        i0Var.g = arrayList;
        textView.setBackground(!i0Var.k ? i0Var.e(l8.f(), 9) : null);
        textView2.setBackground(i0Var.k ? i0Var.e(l8.f(), 9) : null);
        textView.setTextColor(!i0Var.k ? -1 : i);
        if (i0Var.k) {
            i = -1;
        }
        textView2.setTextColor(i);
        if (i0Var.g.isEmpty()) {
            strA = si.a("no_favorites_yet_hold_an_app_in", new Object[0]);
        } else {
            List list2 = si.a;
            strA = si.a("apps_0_hold_an_icon_for_favorites", Integer.valueOf(i0Var.g.size()));
        }
        textView3.setText(strA);
        g0Var.notifyDataSetChanged();
    }

    public static final TextView c(i0 i0Var, String str) {
        TextView textView = new TextView(i0Var.getContext());
        textView.setText(str);
        textView.setTextSize(i0Var.l * 16.0f);
        textView.setGravity(17);
        textView.setTextColor(i0Var.m);
        textView.setClickable(true);
        textView.setPadding(i0Var.a(18), 0, i0Var.a(18), 0);
        return textView;
    }

    public final int a(int i) {
        return (int) (i * getContext().getResources().getDisplayMetrics().density * this.l);
    }

    public final void d(String str) {
        Activity activity = this.a;
        try {
            activity.startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", str, null)));
            dismiss();
        } catch (Exception e) {
            Log.w("AppDrawer", "Cannot open app details for ".concat(str), e);
            List list = si.a;
            Toast.makeText(activity, si.a("the_firmware_blocked_app_info_0", e.getClass().getSimpleName()), 1).show();
        }
    }

    public final GradientDrawable e(int i, int i2) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(i);
        gradientDrawable.setCornerRadius(a(i2));
        return gradientDrawable;
    }

    @Override // android.app.Dialog
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int i = 1;
        requestWindowFeature(1);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        linearLayout.setPadding(a(26), a(18), a(26), a(18));
        boolean z = this.b;
        linearLayout.setBackground(e(z ? Color.rgb(17, 24, 38) : Color.rgb(242, 245, 249), 30));
        LinearLayout linearLayout2 = new LinearLayout(getContext());
        linearLayout2.setGravity(16);
        TextView textView = new TextView(getContext());
        textView.setText(this.c);
        float f = this.l;
        textView.setTextSize(28.0f * f);
        int i2 = this.m;
        textView.setTextColor(i2);
        textView.setTypeface(z9.c());
        linearLayout2.addView(textView, new LinearLayout.LayoutParams(0, a(56), 1.0f));
        TextView textView2 = new TextView(getContext());
        textView2.setText(si.a("done", new Object[0]));
        float f2 = 17.0f * f;
        textView2.setTextSize(f2);
        textView2.setTextColor(-1);
        textView2.setGravity(17);
        textView2.setTypeface(z9.c());
        textView2.setBackground(e(l8.f(), 24));
        textView2.setPadding(a(26), 0, a(26), 0);
        textView2.setContentDescription(si.a("close_apps", new Object[0]));
        textView2.setOnClickListener(new s(i, this));
        linearLayout2.addView(textView2, new LinearLayout.LayoutParams(-2, a(48)));
        linearLayout.addView(linearLayout2);
        LinearLayout linearLayout3 = new LinearLayout(getContext());
        linearLayout3.setGravity(16);
        LinearLayout linearLayout4 = new LinearLayout(getContext());
        linearLayout4.setBackground(e(l8.e(z), 12));
        linearLayout4.setPadding(a(3), a(3), a(3), a(3));
        final TextView textViewC = c(this, si.a("favorites", new Object[0]));
        final TextView textViewC2 = c(this, si.a("all", new Object[0]));
        linearLayout4.addView(textViewC, new LinearLayout.LayoutParams(-2, a(42)));
        linearLayout4.addView(textViewC2, new LinearLayout.LayoutParams(-2, a(42)));
        linearLayout3.addView(linearLayout4, new LinearLayout.LayoutParams(-2, -2));
        final EditText editText = new EditText(getContext());
        editText.setHint(si.a("search_apps", new Object[0]));
        editText.setTextSize(f2);
        editText.setSingleLine(true);
        editText.setTextColor(i2);
        int i3 = this.n;
        editText.setHintTextColor(i3);
        editText.setBackground(e(l8.d(z), 16));
        editText.setPadding(a(18), 0, a(18), 0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, a(50), 1.0f);
        layoutParams.leftMargin = a(14);
        linearLayout3.addView(editText, layoutParams);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.topMargin = a(10);
        layoutParams2.bottomMargin = a(12);
        linearLayout.addView(linearLayout3, layoutParams2);
        final TextView textView3 = new TextView(getContext());
        textView3.setText(si.a("loading_apps", new Object[0]));
        textView3.setTextSize(f * 14.0f);
        textView3.setTextColor(i3);
        textView3.setPadding(a(4), 0, 0, a(10));
        linearLayout.addView(textView3);
        GridView gridView = new GridView(getContext());
        gridView.setNumColumns(-1);
        gridView.setColumnWidth(a(128));
        gridView.setStretchMode(2);
        gridView.setHorizontalSpacing(a(10));
        gridView.setVerticalSpacing(a(14));
        gridView.setVerticalScrollBarEnabled(false);
        gridView.setSelector(new ColorDrawable(0));
        final g0 g0Var = new g0(this);
        gridView.setAdapter((ListAdapter) g0Var);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: x
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i4, long j) {
                i0 i0Var = this.a;
                ComponentName componentName = ((e0) i0Var.g.get(i4)).b;
                i0Var.dismiss();
                i0Var.d.b(componentName);
            }
        });
        linearLayout.addView(gridView, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        textViewC2.setOnClickListener(new y(this, editText, textViewC2, textViewC, textView3, g0Var, 0));
        textViewC.setOnClickListener(new y(this, editText, textViewC2, textViewC, textView3, g0Var, 1));
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: z
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public final boolean onItemLongClick(AdapterView adapterView, View view, int i4, long j) {
                Object ffVar;
                final i0 i0Var = this;
                final e0 e0Var = (e0) i0Var.g.get(i4);
                final String strFlattenToString = e0Var.b.flattenToString();
                strFlattenToString.getClass();
                try {
                    ffVar = Boolean.valueOf((i0Var.a.getPackageManager().getApplicationInfo(e0Var.b.getPackageName(), 0).flags & 1) != 0);
                } catch (Throwable th) {
                    ffVar = new ff(th);
                }
                Object obj = Boolean.TRUE;
                if (ffVar instanceof ff) {
                    ffVar = obj;
                }
                boolean zBooleanValue = ((Boolean) ffVar).booleanValue();
                ArrayList arrayListJ0 = j3.j0(i0Var.j.contains(strFlattenToString) ? si.a("remove_from_favorites", new Object[0]) : si.a("add_to_favorites", new Object[0]), si.a("app_info", new Object[0]));
                if (!zBooleanValue) {
                    arrayListJ0.add(si.a("uninstall_app", new Object[0]));
                }
                AlertDialog.Builder title = new AlertDialog.Builder(i0Var.getContext()).setTitle(e0Var.a);
                CharSequence[] charSequenceArr = (CharSequence[]) arrayListJ0.toArray(new String[0]);
                final EditText editText2 = editText;
                final TextView textView4 = textViewC2;
                final TextView textView5 = textViewC;
                final TextView textView6 = textView3;
                final g0 g0Var2 = g0Var;
                AlertDialog.Builder items = title.setItems(charSequenceArr, new DialogInterface.OnClickListener() { // from class: c0
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i5) {
                        i0 i0Var2 = i0Var;
                        if (i5 == 0) {
                            Set set = i0Var2.j;
                            String str = strFlattenToString;
                            if (!set.add(str)) {
                                set.remove(str);
                            }
                            i0Var2.i.edit().putStringSet("favorite_apps", i3.L0(set)).apply();
                            i0.b(g0Var2, i0Var2, editText2, textView4, textView5, textView6);
                            return;
                        }
                        ComponentName componentName = e0Var.b;
                        if (i5 == 1) {
                            String packageName = componentName.getPackageName();
                            packageName.getClass();
                            i0Var2.d(packageName);
                            return;
                        }
                        String packageName2 = componentName.getPackageName();
                        packageName2.getClass();
                        Activity activity = i0Var2.a;
                        try {
                            activity.startActivity(new Intent("android.intent.action.DELETE", Uri.fromParts("package", packageName2, null)));
                            i0Var2.dismiss();
                        } catch (Exception e) {
                            Log.w("AppDrawer", "Cannot open uninstall for ".concat(packageName2), e);
                            Toast.makeText(activity, si.a("could_not_open_uninstall_opening_app_info", new Object[0]), 1).show();
                            i0Var2.d(packageName2);
                        }
                    }
                });
                items.getClass();
                k8.w0(items);
                return true;
            }
        });
        editText.addTextChangedListener(new f0(editText, this, textViewC2, textViewC, textView3, g0Var, 0));
        setContentView(linearLayout);
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.addFlags(2);
            window.setDimAmount(0.65f);
            window.getDecorView().setSystemUiVisibility(this.a.getWindow().getDecorView().getSystemUiVisibility());
            window.setSoftInputMode(19);
        }
        linearLayout.setFocusableInTouchMode(true);
        linearLayout.requestFocus();
        setOnDismissListener(new a0(0, this));
        this.e.execute(new Runnable() { // from class: b0
            @Override // java.lang.Runnable
            public final void run() {
                final Object ffVar;
                final i0 i0Var = this;
                Activity activity = i0Var.a;
                try {
                    List<ResolveInfo> listQueryIntentActivities = activity.getPackageManager().queryIntentActivities(new Intent("android.intent.action.MAIN").addCategory("android.intent.category.LAUNCHER"), 0);
                    listQueryIntentActivities.getClass();
                    ArrayList arrayList = new ArrayList();
                    for (Object obj : listQueryIntentActivities) {
                        if (!k8.e(((ResolveInfo) obj).activityInfo.packageName, activity.getPackageName())) {
                            arrayList.add(obj);
                        }
                    }
                    ArrayList arrayList2 = new ArrayList(k3.n0(arrayList));
                    int size = arrayList.size();
                    int i4 = 0;
                    while (i4 < size) {
                        Object obj2 = arrayList.get(i4);
                        i4++;
                        ResolveInfo resolveInfo = (ResolveInfo) obj2;
                        String string = resolveInfo.loadLabel(activity.getPackageManager()).toString();
                        ActivityInfo activityInfo = resolveInfo.activityInfo;
                        arrayList2.add(new e0(string, new ComponentName(activityInfo.packageName, activityInfo.name), resolveInfo));
                    }
                    HashSet hashSet = new HashSet();
                    ArrayList arrayList3 = new ArrayList();
                    int size2 = arrayList2.size();
                    int i5 = 0;
                    while (i5 < size2) {
                        Object obj3 = arrayList2.get(i5);
                        i5++;
                        if (hashSet.add(((e0) obj3).b)) {
                            arrayList3.add(obj3);
                        }
                    }
                    ffVar = i3.G0(arrayList3, new h0(0));
                } catch (Throwable th) {
                    ffVar = new ff(th);
                }
                final EditText editText2 = editText;
                final TextView textView4 = textViewC2;
                final TextView textView5 = textViewC;
                final TextView textView6 = textView3;
                final g0 g0Var2 = g0Var;
                activity.runOnUiThread(new Runnable() { // from class: d0
                    @Override // java.lang.Runnable
                    public final void run() {
                        i0 i0Var2 = i0Var;
                        if (i0Var2.h) {
                            return;
                        }
                        Object obj4 = ffVar;
                        boolean z2 = obj4 instanceof ff;
                        TextView textView7 = textView6;
                        if (!z2) {
                            i0Var2.f = (List) obj4;
                            i0.b(g0Var2, i0Var2, editText2, textView4, textView5, textView7);
                        }
                        if (gf.a(obj4) != null) {
                            textView7.setText(si.a("could_not_load_the_app_list", new Object[0]));
                        }
                    }
                });
            }
        });
    }

    @Override // android.app.Dialog
    public final void onStart() {
        super.onStart();
        Window window = getWindow();
        if (window != null) {
            Activity activity = this.a;
            window.setLayout((int) (activity.getResources().getDisplayMetrics().widthPixels * 0.94f), (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.9f));
        }
    }
}
