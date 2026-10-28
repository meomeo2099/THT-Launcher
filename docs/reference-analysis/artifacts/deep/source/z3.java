package defpackage;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class z3 implements Iterator, x9 {
    public int a = -1;
    public int b;
    public int c;
    public j8 d;
    public final /* synthetic */ a4 e;

    public z3(a4 a4Var) {
        this.e = a4Var;
        int iS = pi.s(0, 0, ((CharSequence) a4Var.b).length());
        this.b = iS;
        this.c = iS;
    }

    public final void a() {
        a4 a4Var = this.e;
        CharSequence charSequence = (CharSequence) a4Var.b;
        int i = this.c;
        if (i < 0) {
            this.a = 0;
            this.d = null;
            return;
        }
        if (i > charSequence.length()) {
            int i2 = this.b;
            charSequence.getClass();
            this.d = new j8(i2, charSequence.length() - 1, 1);
            this.c = -1;
        } else {
            yc ycVar = (yc) ((a) a4Var.c).c(charSequence, Integer.valueOf(this.c));
            if (ycVar == null) {
                int i3 = this.b;
                charSequence.getClass();
                this.d = new j8(i3, charSequence.length() - 1, 1);
                this.c = -1;
            } else {
                int iIntValue = ((Number) ycVar.a).intValue();
                int iIntValue2 = ((Number) ycVar.b).intValue();
                this.d = pi.f0(this.b, iIntValue);
                int i4 = iIntValue + iIntValue2;
                this.b = i4;
                this.c = i4 + (iIntValue2 == 0 ? 1 : 0);
            }
        }
        this.a = 1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.a == -1) {
            a();
        }
        return this.a == 1;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.a == -1) {
            a();
        }
        if (this.a == 0) {
            throw new NoSuchElementException();
        }
        j8 j8Var = this.d;
        j8Var.getClass();
        this.d = null;
        this.a = -1;
        return j8Var;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
