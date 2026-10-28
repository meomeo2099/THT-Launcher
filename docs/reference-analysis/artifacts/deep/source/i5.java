package defpackage;

import java.io.File;
import java.util.ArrayDeque;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class i5 extends b {
    public final ArrayDeque c;
    public final /* synthetic */ k5 d;

    public i5(k5 k5Var) {
        this.d = k5Var;
        ArrayDeque arrayDeque = new ArrayDeque();
        this.c = arrayDeque;
        File file = k5Var.a;
        if (file.isDirectory()) {
            arrayDeque.push(b(file));
        } else if (file.isFile()) {
            arrayDeque.push(new g5(file));
        } else {
            this.a = 2;
        }
    }

    @Override // defpackage.b
    public final void a() {
        File file;
        while (true) {
            ArrayDeque arrayDeque = this.c;
            j5 j5Var = (j5) arrayDeque.peek();
            if (j5Var == null) {
                file = null;
                break;
            }
            File fileA = j5Var.a();
            if (fileA == null) {
                arrayDeque.pop();
            } else {
                if (fileA.equals(j5Var.a) || !fileA.isDirectory() || arrayDeque.size() >= Integer.MAX_VALUE) {
                    file = fileA;
                    break;
                }
                arrayDeque.push(b(fileA));
            }
        }
        if (file == null) {
            this.a = 2;
        } else {
            this.b = file;
            this.a = 1;
        }
    }

    public final e5 b(File file) {
        int iOrdinal = this.d.b.ordinal();
        if (iOrdinal == 0) {
            return new h5(this, file);
        }
        if (iOrdinal == 1) {
            return new f5(this, file);
        }
        throw new vc();
    }
}
