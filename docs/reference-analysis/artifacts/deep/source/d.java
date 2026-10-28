package defpackage;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public class d implements Iterator, x9 {
    public final /* synthetic */ int a;
    public int b;
    public final Object c;

    public d(gi giVar) {
        this.a = 2;
        this.b = 60;
        this.c = giVar.a.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i = this.a;
        Object obj = this.c;
        switch (i) {
            case 0:
                return this.b < ((g) obj).a();
            case 1:
                return this.b < ((Object[]) obj).length;
            default:
                return this.b > 0 && ((Iterator) obj).hasNext();
        }
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i = this.a;
        Object obj = this.c;
        switch (i) {
            case 0:
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                int i2 = this.b;
                this.b = i2 + 1;
                return ((g) obj).get(i2);
            case 1:
                try {
                    int i3 = this.b;
                    this.b = i3 + 1;
                    return ((Object[]) obj)[i3];
                } catch (ArrayIndexOutOfBoundsException e) {
                    this.b--;
                    throw new NoSuchElementException(e.getMessage());
                }
            default:
                int i4 = this.b;
                if (i4 == 0) {
                    throw new NoSuchElementException();
                }
                this.b = i4 - 1;
                return ((Iterator) obj).next();
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.a) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 1:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public /* synthetic */ d(int i, Object obj) {
        this.a = i;
        this.c = obj;
    }
}
