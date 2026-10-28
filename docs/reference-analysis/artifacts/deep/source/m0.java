package defpackage;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class m0 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ MainActivity b;
    public final /* synthetic */ boolean c;
    public final /* synthetic */ Object d;

    public /* synthetic */ m0(MainActivity mainActivity, Object obj, boolean z, int i) {
        this.a = i;
        this.b = mainActivity;
        this.d = obj;
        this.c = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object ffVar;
        boolean z;
        Object obj;
        int i = 0;
        switch (this.a) {
            case 0:
                MainActivity mainActivity = this.b;
                Handler handler = (Handler) this.d;
                boolean z2 = this.c;
                try {
                    ffVar = l0.a.l();
                    break;
                } catch (Throwable th) {
                    ffVar = new ff(th);
                }
                if (!(ffVar instanceof ff)) {
                    mainActivity.getSharedPreferences("panel", 0).edit().putLong("update_last_check", System.currentTimeMillis()).apply();
                }
                handler.post(new m0(mainActivity, ffVar, z2, 1));
                return;
            default:
                MainActivity mainActivity2 = this.b;
                Object obj2 = this.d;
                boolean z3 = this.c;
                if (mainActivity2.isFinishing() || mainActivity2.isDestroyed()) {
                    return;
                }
                if (obj2 instanceof ff) {
                    z = z3;
                    obj = obj2;
                } else {
                    k0 k0Var = (k0) obj2;
                    if (k0Var == null) {
                        if (z3) {
                            mainActivity2.v0(si.a("update_none", new Object[0]));
                        }
                    } else if (z3 || k0Var.a != mainActivity2.getSharedPreferences("panel", 0).getInt("update_skip", 0)) {
                        final yi yiVar = new yi(mainActivity2, k0Var);
                        o0 o0Var = new o0(mainActivity2, yiVar, k0Var, i);
                        TextView textView = yiVar.h;
                        List list = si.a;
                        textView.setText(si.a("update_available_0", k0Var.b));
                        yiVar.i.setText(si.a("update_current_0", "1.0.2"));
                        yiVar.j.removeAllViews();
                        List listD = th.D(k0Var.g, new char[]{',', ';', '\n'});
                        ArrayList arrayList = new ArrayList(k3.n0(listD));
                        Iterator it = listD.iterator();
                        while (it.hasNext()) {
                            arrayList.add(th.K(th.J((String) it.next()).toString(), '.'));
                        }
                        ArrayList arrayList2 = new ArrayList();
                        int size = arrayList.size();
                        int i2 = 0;
                        while (i2 < size) {
                            Object obj3 = arrayList.get(i2);
                            i2++;
                            if (((String) obj3).length() > 0) {
                                arrayList2.add(obj3);
                            }
                        }
                        int size2 = arrayList2.size();
                        int i3 = 0;
                        while (i3 < size2) {
                            Object obj4 = arrayList2.get(i3);
                            i3++;
                            String string = (String) obj4;
                            LinearLayout linearLayout = yiVar.j;
                            if (string.length() > 0) {
                                StringBuilder sb = new StringBuilder();
                                char cCharAt = string.charAt(0);
                                List list2 = si.a;
                                sb.append((Object) pi.Z(cCharAt, si.b.b));
                                sb.append(string.substring(1));
                                string = sb.toString();
                            }
                            TextView textViewD = yiVar.d("•  ".concat(string), 17.0f, yiVar.e, false);
                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                            layoutParams.bottomMargin = yiVar.a(8);
                            linearLayout.addView(textViewD, layoutParams);
                        }
                        final boolean zO = l0.o(yiVar.a);
                        final le leVar = new le();
                        int iT = l0.t(yiVar.a);
                        if (iT == 1 && !zO) {
                            iT = 0;
                        }
                        leVar.a = iT;
                        LinearLayout linearLayout2 = yiVar.j;
                        TextView textViewD2 = yiVar.d(si.a("update_install_with", new Object[0]), 15.0f, yiVar.f, true);
                        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
                        layoutParams2.topMargin = yiVar.a(8);
                        layoutParams2.bottomMargin = yiVar.a(10);
                        linearLayout2.addView(textViewD2, layoutParams2);
                        LinearLayout linearLayout3 = new LinearLayout(yiVar.a);
                        final ArrayList arrayList3 = new ArrayList();
                        Iterator it2 = j3.i0(new oi(si.a("update_installer_system_title", new Object[0]), si.a("update_installer_system_hint", new Object[0]), Boolean.TRUE), new oi(si.a("update_installer_freetuga_title", new Object[0]), zO ? si.a("update_installer_freetuga_hint", new Object[0]) : si.a("update_freetuga_not_found", new Object[0]), Boolean.valueOf(zO))).iterator();
                        int i4 = 0;
                        while (it2.hasNext()) {
                            Object next = it2.next();
                            int i5 = i4 + 1;
                            if (i4 < 0) {
                                j3.m0();
                                throw null;
                            }
                            oi oiVar = (oi) next;
                            String str = (String) oiVar.a;
                            String str2 = (String) oiVar.b;
                            boolean zBooleanValue = ((Boolean) oiVar.c).booleanValue();
                            boolean z4 = z3;
                            Iterator it3 = it2;
                            LinearLayout linearLayout4 = new LinearLayout(yiVar.a);
                            linearLayout4.setOrientation(1);
                            Object obj5 = obj2;
                            le leVar2 = leVar;
                            final int i6 = i4;
                            linearLayout4.setPadding(yiVar.a(18), yiVar.a(14), yiVar.a(18), yiVar.a(14));
                            linearLayout4.addView(yiVar.d(str, 18.0f, yiVar.e, true));
                            TextView textViewD3 = yiVar.d(str2, 14.0f, yiVar.f, false);
                            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
                            layoutParams3.topMargin = yiVar.a(6);
                            linearLayout4.addView(textViewD3, layoutParams3);
                            linearLayout4.setClickable(zBooleanValue);
                            if (zBooleanValue) {
                                leVar = leVar2;
                                linearLayout4.setOnClickListener(new View.OnClickListener() { // from class: vi
                                    @Override // android.view.View.OnClickListener
                                    public final void onClick(View view) {
                                        le leVar3 = leVar;
                                        leVar3.a = i6;
                                        yi.f(arrayList3, leVar3, zO, yiVar);
                                    }
                                });
                            } else {
                                leVar = leVar2;
                            }
                            arrayList3.add(linearLayout4);
                            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(0, -2, 1.0f);
                            if (i6 > 0) {
                                layoutParams4.leftMargin = yiVar.a(12);
                            }
                            linearLayout3.addView(linearLayout4, layoutParams4);
                            z3 = z4;
                            i4 = i5;
                            it2 = it3;
                            obj2 = obj5;
                        }
                        z = z3;
                        obj = obj2;
                        yi.f(arrayList3, leVar, zO, yiVar);
                        yiVar.j.addView(linearLayout3, new LinearLayout.LayoutParams(-1, -2));
                        yiVar.k.removeAllViews();
                        yi.e(yiVar, yiVar.d.a(si.a("update_skip", new Object[0]), false, new wi(yiVar, 0)));
                        yi.e(yiVar, yiVar.d.a(si.a("update_later", new Object[0]), false, new wi(yiVar, 1)));
                        Button buttonA = yiVar.d.a(si.a("update_install", new Object[0]), true, new u0(yiVar, leVar, o0Var));
                        buttonA.setMinWidth(yiVar.a(150));
                        yi.e(yiVar, buttonA);
                        yiVar.g.setCancelable(true);
                        yiVar.g.show();
                    }
                    z = z3;
                    obj = obj2;
                }
                Throwable thA = gf.a(obj);
                if (thA != null) {
                    Log.w("ReferencePanel.Update", "check failed", thA);
                    if (z) {
                        mainActivity2.v0(thA instanceof SecurityException ? si.a("update_bad_file", new Object[0]) : si.a("update_no_network", new Object[0]));
                        return;
                    }
                    return;
                }
                return;
        }
    }
}
