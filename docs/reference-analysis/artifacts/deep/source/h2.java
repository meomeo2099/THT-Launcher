package defpackage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Environment;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Set;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class h2 {
    public final MainActivity a;
    public final boolean b;
    public final j6 c;
    public final y5 d;
    public final String e;
    public final String f;
    public final Set g;
    public final ArrayList h;

    public h2(MainActivity mainActivity, boolean z, j6 j6Var, y5 y5Var, String str, Set set, int i) {
        String str2 = (i & 16) != 0 ? null : "ReferencePanel-icons-template";
        str = (i & 32) != 0 ? null : str;
        set = (i & 64) != 0 ? pi.P("zip") : set;
        this.a = mainActivity;
        this.b = z;
        this.c = j6Var;
        this.d = y5Var;
        this.e = str2;
        this.f = str;
        this.g = set;
        ja jaVar = new ja(10);
        int i2 = 0;
        if (mainActivity.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0) {
            jaVar.add(Environment.getExternalStorageDirectory());
            File[] externalFilesDirs = mainActivity.getExternalFilesDirs(null);
            externalFilesDirs.getClass();
            ArrayList arrayList = (ArrayList) x0.S0(externalFilesDirs);
            int size = arrayList.size();
            int i3 = 0;
            while (i3 < size) {
                Object obj = arrayList.get(i3);
                i3++;
                File file = (File) obj;
                String absolutePath = file.getAbsolutePath();
                absolutePath.getClass();
                String strG = th.G(absolutePath, "/Android/");
                if (!strG.equals(file.getAbsolutePath())) {
                    jaVar.add(new File(strG));
                }
            }
        }
        File externalFilesDir = this.a.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        if (externalFilesDir != null) {
            jaVar.add(externalFilesDir);
        }
        File file2 = new File(this.a.getFilesDir(), "backups");
        file2.mkdirs();
        jaVar.add(file2);
        jaVar.f();
        jaVar.c = true;
        jaVar = jaVar.b <= 0 ? ja.d : jaVar;
        HashSet hashSet = new HashSet();
        ArrayList arrayList2 = new ArrayList();
        ListIterator listIterator = jaVar.listIterator(0);
        while (true) {
            ha haVar = (ha) listIterator;
            if (!haVar.hasNext()) {
                break;
            }
            Object next = haVar.next();
            if (hashSet.add(((File) next).getAbsolutePath())) {
                arrayList2.add(next);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        int size2 = arrayList2.size();
        while (i2 < size2) {
            Object obj2 = arrayList2.get(i2);
            i2++;
            File file3 = (File) obj2;
            if (file3.isDirectory() && file3.canRead()) {
                arrayList3.add(obj2);
            }
        }
        this.h = arrayList3;
    }

    /* JADX WARN: Code duplicated, block: B:15:0x004e  */
    public final void a(File file) {
        List listG0;
        Object next;
        String name;
        File[] fileArrListFiles = file.listFiles();
        boolean z = this.b;
        if (fileArrListFiles != null) {
            ArrayList arrayList = new ArrayList();
            for (File file2 : fileArrListFiles) {
                if (file2.isDirectory()) {
                    arrayList.add(file2);
                } else if (z) {
                    String name2 = file2.getName();
                    name2.getClass();
                    int iB = th.B(name2, '.');
                    String lowerCase = (iB == -1 ? "" : name2.substring(iB + 1, name2.length())).toLowerCase(Locale.ROOT);
                    lowerCase.getClass();
                    if (this.g.contains(lowerCase)) {
                        arrayList.add(file2);
                    }
                }
            }
            listG0 = i3.G0(arrayList, new g2(0, new h0(1)));
        } else {
            listG0 = null;
        }
        List listJ = u4.a;
        if (listG0 == null) {
            listG0 = listJ;
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = this.h;
        int size = arrayList3.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList3.get(i);
            i++;
            File file3 = (File) obj;
            if (!k8.e(file.getCanonicalPath(), file3.getCanonicalPath())) {
                String canonicalPath = file.getCanonicalPath();
                canonicalPath.getClass();
                if (canonicalPath.startsWith(file3.getCanonicalPath() + File.separator)) {
                }
            }
            arrayList2.add(obj);
        }
        Iterator it = arrayList2.iterator();
        if (it.hasNext()) {
            next = it.next();
            if (it.hasNext()) {
                int length = ((File) next).getAbsolutePath().length();
                do {
                    Object next2 = it.next();
                    int length2 = ((File) next2).getAbsolutePath().length();
                    if (length > length2) {
                        next = next2;
                        length = length2;
                    }
                } while (it.hasNext());
            }
        } else {
            next = null;
        }
        File file4 = (File) next;
        File parentFile = file.getParentFile();
        if (parentFile == null || file4 == null || k8.e(file.getCanonicalPath(), file4.getCanonicalPath())) {
            parentFile = null;
        }
        if (parentFile != null) {
            listJ = pi.J(parentFile);
        }
        ArrayList arrayListD0 = i3.D0(listJ, listG0);
        ArrayList arrayList4 = new ArrayList(k3.n0(arrayListD0));
        int size2 = arrayListD0.size();
        int i2 = 0;
        int i3 = 0;
        while (i3 < size2) {
            Object obj2 = arrayListD0.get(i3);
            i3++;
            int i4 = i2 + 1;
            if (i2 < 0) {
                j3.m0();
                throw null;
            }
            File file5 = (File) obj2;
            if (parentFile != null && i2 == 0) {
                name = si.a("parent_folder", new Object[0]);
            } else if (file5.isDirectory()) {
                name = "▸  " + file5.getName();
            } else {
                name = file5.getName();
            }
            arrayList4.add(name);
            i2 = i4;
        }
        AlertDialog.Builder neutralButton = new AlertDialog.Builder(this.a).setTitle(file.getAbsolutePath()).setItems((CharSequence[]) arrayList4.toArray(new String[0]), new c2(arrayListD0, this, 1)).setNegativeButton(si.a("cancel", new Object[0]), (DialogInterface.OnClickListener) null).setNeutralButton(si.a("storage_devices", new Object[0]), new f2(2, this));
        if (!z) {
            neutralButton.setPositiveButton(si.a("save_here", new Object[0]), new c2(file, this, 2));
        }
        neutralButton.getClass();
        k8.w0(neutralButton);
    }

    public final void b() {
        ArrayList arrayList = this.h;
        if (arrayList.size() == 1) {
            Object objU0 = i3.u0(arrayList);
            objU0.getClass();
            a((File) objU0);
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this.a);
        String strA = this.f;
        if (strA == null) {
            strA = this.b ? si.a("load_settings_from", new Object[0]) : si.a("save_settings_to", new Object[0]);
        }
        AlertDialog.Builder title = builder.setTitle(strA);
        ArrayList arrayList2 = new ArrayList(k3.n0(arrayList));
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            arrayList2.add(((File) obj).getAbsolutePath());
        }
        AlertDialog.Builder negativeButton = title.setItems((CharSequence[]) arrayList2.toArray(new String[0]), new f2(0, this)).setNeutralButton(si.a("system_file_manager", new Object[0]), new f2(1, this)).setNegativeButton(si.a("cancel", new Object[0]), (DialogInterface.OnClickListener) null);
        negativeButton.getClass();
        k8.w0(negativeButton);
    }
}
