package defpackage;

import java.io.File;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class f5 extends e5 {
    public boolean b;
    public File[] c;
    public int d;
    public boolean e;
    public final /* synthetic */ i5 f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f5(i5 i5Var, File file) {
        super(file);
        this.f = i5Var;
    }

    @Override // defpackage.j5
    public final File a() {
        boolean z = this.e;
        File file = this.a;
        if (!z && this.c == null) {
            File[] fileArrListFiles = file.listFiles();
            this.c = fileArrListFiles;
            if (fileArrListFiles == null) {
                n6 n6Var = this.f.d.c;
                if (n6Var != null) {
                    n6Var.c(file, new i(file, null, "Cannot list files in a directory"));
                }
                this.e = true;
            }
        }
        File[] fileArr = this.c;
        if (fileArr == null || this.d >= fileArr.length) {
            if (this.b) {
                return null;
            }
            this.b = true;
            return file;
        }
        fileArr.getClass();
        int i = this.d;
        this.d = i + 1;
        return fileArr[i];
    }
}
