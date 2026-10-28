package defpackage;

import java.io.File;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class h5 extends e5 {
    public boolean b;
    public File[] c;
    public int d;
    public final /* synthetic */ i5 e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h5(i5 i5Var, File file) {
        super(file);
        this.e = i5Var;
    }

    @Override // defpackage.j5
    public final File a() {
        n6 n6Var;
        boolean z = this.b;
        File file = this.a;
        if (!z) {
            this.b = true;
            return file;
        }
        File[] fileArr = this.c;
        if (fileArr != null && this.d >= fileArr.length) {
            return null;
        }
        if (fileArr == null) {
            File[] fileArrListFiles = file.listFiles();
            this.c = fileArrListFiles;
            if (fileArrListFiles == null && (n6Var = this.e.d.c) != null) {
                n6Var.c(file, new i(file, null, "Cannot list files in a directory"));
            }
            File[] fileArr2 = this.c;
            if (fileArr2 == null || fileArr2.length == 0) {
                return null;
            }
        }
        File[] fileArr3 = this.c;
        fileArr3.getClass();
        int i = this.d;
        this.d = i + 1;
        return fileArr3[i];
    }
}
