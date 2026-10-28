package defpackage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.reference.panel.AutomationService;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class o0 implements j6 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;

    public /* synthetic */ o0(Object obj, Object obj2, Object obj3, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
        this.d = obj3;
    }

    /* JADX WARN: Code duplicated, block: B:34:0x0096  */
    @Override // defpackage.j6
    public final Object b(Object obj) {
        int i;
        Integer numValueOf = null;
        int i2 = 1;
        int i3 = 0;
        switch (this.a) {
            case 0:
                MainActivity mainActivity = (MainActivity) this.b;
                yi yiVar = (yi) this.c;
                k0 k0Var = (k0) this.d;
                int iIntValue = ((Integer) obj).intValue();
                yiVar.g(0, false);
                new Thread(new p0(mainActivity, new Handler(Looper.getMainLooper()), k0Var, iIntValue == 1, yiVar)).start();
                return ti.a;
            case 1:
                z0 z0Var = (z0) this.c;
                MainActivity mainActivity2 = (MainActivity) this.b;
                mg mgVar = (mg) this.d;
                z0Var.a.edit().putBoolean("stats_enabled", ((Boolean) obj).booleanValue()).apply();
                int i4 = AutomationService.c;
                k8.F0(mainActivity2);
                mgVar.a();
                return ti.a;
            case 2:
                z0 z0Var2 = (z0) this.b;
                String str = (String) this.c;
                y5 y5Var = (y5) this.d;
                z0Var2.c(((Integer) obj).intValue() + 1, str);
                y5Var.a();
                return ti.a;
            case 3:
                z0 z0Var3 = (z0) this.c;
                MainActivity mainActivity3 = (MainActivity) this.b;
                y5 y5Var2 = (y5) this.d;
                z0Var3.a.edit().putBoolean("auto_enabled", ((Boolean) obj).booleanValue()).apply();
                int i5 = AutomationService.c;
                k8.F0(mainActivity3);
                y5Var2.a();
                return ti.a;
            case 4:
                MainActivity mainActivity4 = (MainActivity) this.b;
                String str2 = (String) this.c;
                y5 y5Var3 = (y5) this.d;
                int iIntValue2 = ((Integer) obj).intValue();
                int i6 = zj.a;
                SharedPreferences sharedPreferencesB = mainActivity4.B();
                sharedPreferencesB.getClass();
                if (zj.c(sharedPreferencesB, str2) != iIntValue2) {
                    mainActivity4.B().edit().putInt(str2, iIntValue2).apply();
                    y5Var3.a();
                }
                return ti.a;
            case 5:
                me meVar = (me) this.b;
                ArrayList arrayList = (ArrayList) this.c;
                ArrayList arrayList2 = (ArrayList) this.d;
                String str3 = (String) obj;
                str3.getClass();
                if (str3.length() >= 3 && str3.charAt(1) == ' ' && th.z("oh", str3.charAt(0), 0, 2) >= 0) {
                    List listD = th.D(str3.substring(2), new char[]{','});
                    ArrayList arrayList3 = new ArrayList(k3.n0(listD));
                    Iterator it = listD.iterator();
                    while (it.hasNext()) {
                        arrayList3.add(Double.valueOf(Double.parseDouble((String) it.next())));
                    }
                    double[] dArr = new double[arrayList3.size()];
                    int size = arrayList3.size();
                    int i7 = 0;
                    int i8 = 0;
                    while (i8 < size) {
                        Object obj2 = arrayList3.get(i8);
                        i8++;
                        dArr[i7] = ((Number) obj2).doubleValue();
                        i7++;
                    }
                    char cCharAt = str3.charAt(0);
                    if (cCharAt == 'h') {
                        arrayList.add(dArr);
                    } else if (cCharAt == 'o') {
                        double[] dArr2 = (double[]) meVar.b;
                        if (dArr2 != null) {
                            arrayList2.add(new we(dArr2, i3.J0(arrayList)));
                        }
                        meVar.b = null;
                        arrayList.clear();
                        meVar.b = dArr;
                    }
                }
                return ti.a;
            case 6:
                final MainActivity mainActivity5 = (MainActivity) this.b;
                final b8 b8Var = (b8) this.c;
                final kh khVar = (kh) this.d;
                final int iIntValue3 = ((Integer) obj).intValue();
                final int i9 = 0;
                final int i10 = 1;
                AlertDialog.Builder negativeButton = new AlertDialog.Builder(mainActivity5).setTitle(si.a("refuel_full_question", new Object[0])).setPositiveButton(si.a("refuel_full_yes", new Object[0]), new DialogInterface.OnClickListener(mainActivity5, b8Var, khVar, iIntValue3, i9) { // from class: nh
                    public final /* synthetic */ int a;
                    public final /* synthetic */ b8 b;
                    public final /* synthetic */ kh c;
                    public final /* synthetic */ int d;

                    {
                        this.a = i9;
                        this.b = b8Var;
                        this.c = khVar;
                        this.d = iIntValue3;
                    }

                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i11) {
                        int i12 = this.a;
                        int i13 = this.d;
                        kh khVar2 = this.c;
                        b8 b8Var2 = this.b;
                        switch (i12) {
                            case 0:
                                pi.c(b8Var2, khVar2, i13, true);
                                break;
                            default:
                                pi.c(b8Var2, khVar2, i13, false);
                                break;
                        }
                    }
                }).setNegativeButton(si.a("refuel_full_no", new Object[0]), new DialogInterface.OnClickListener(mainActivity5, b8Var, khVar, iIntValue3, i10) { // from class: nh
                    public final /* synthetic */ int a;
                    public final /* synthetic */ b8 b;
                    public final /* synthetic */ kh c;
                    public final /* synthetic */ int d;

                    {
                        this.a = i10;
                        this.b = b8Var;
                        this.c = khVar;
                        this.d = iIntValue3;
                    }

                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i11) {
                        int i12 = this.a;
                        int i13 = this.d;
                        kh khVar2 = this.c;
                        b8 b8Var2 = this.b;
                        switch (i12) {
                            case 0:
                                pi.c(b8Var2, khVar2, i13, true);
                                break;
                            default:
                                pi.c(b8Var2, khVar2, i13, false);
                                break;
                        }
                    }
                });
                negativeButton.getClass();
                mainActivity5.p0(negativeButton);
                return ti.a;
            case 7:
                nj njVar = (nj) this.b;
                String str4 = (String) this.c;
                rf rfVar = (rf) this.d;
                r4 r4Var = (r4) obj;
                r4Var.getClass();
                Object objI = njVar.i("seat:" + str4);
                Integer num = objI instanceof Integer ? (Integer) objI : null;
                if (num != null) {
                    int iIntValue4 = num.intValue();
                    if (iIntValue4 <= 0) {
                        i = 0;
                    } else {
                        int i11 = rfVar.a;
                        int iA = rfVar.a();
                        if (iIntValue4 > iA) {
                            iIntValue4 = iA;
                        }
                        i = i11 + iIntValue4;
                    }
                    numValueOf = Integer.valueOf(i);
                } else {
                    Integer numR = r4Var.r(rfVar);
                    if (numR == null) {
                        tf tfVar = (tf) njVar.f.j.get(str4);
                        if (tfVar != null) {
                            numValueOf = tfVar.a;
                        }
                    } else {
                        numValueOf = numR;
                    }
                }
                int i12 = rfVar.a + 15;
                if (numValueOf != null && numValueOf.intValue() == i12) {
                    i2 = 0;
                } else {
                    Integer numC = rfVar.c(numValueOf);
                    if (numC != null) {
                        int iIntValue5 = numC.intValue();
                        if (iIntValue5 >= rfVar.a()) {
                            i2 = 0;
                        } else {
                            i2 = 1 + iIntValue5;
                        }
                    }
                }
                int i13 = rfVar.a;
                int i14 = rfVar.b;
                if (i2 > 0) {
                    int iA2 = rfVar.a();
                    if (i2 <= iA2) {
                        iA2 = i2;
                    }
                    i3 = i13 + iA2;
                }
                boolean zX = r4Var.x(i13, i14, i3);
                if (zX) {
                    njVar.d("seat:" + str4, Integer.valueOf(i2), 6000L);
                }
                return Boolean.valueOf(zX);
            default:
                nj njVar2 = (nj) this.b;
                j6 j6Var = (j6) this.c;
                me meVar2 = (me) this.d;
                String str5 = (String) obj;
                if (str5 != null) {
                    njVar2.A = 0L;
                    j6Var.b(str5);
                } else {
                    njVar2.c.postDelayed(new r0(njVar2, j6Var, meVar2), 4000L);
                }
                return ti.a;
        }
    }

    public /* synthetic */ o0(Object obj, MainActivity mainActivity, y5 y5Var, int i) {
        this.a = i;
        this.c = obj;
        this.b = mainActivity;
        this.d = y5Var;
    }
}
