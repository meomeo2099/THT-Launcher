package defpackage;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class fb extends AbstractSet implements Set, y9 {
    public final /* synthetic */ int a;
    public final eb b;

    public /* synthetic */ fb(eb ebVar, int i) {
        this.a = i;
        this.b = ebVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        switch (this.a) {
            case 0:
                ((Map.Entry) obj).getClass();
                throw new UnsupportedOperationException();
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean addAll(Collection collection) {
        int i = this.a;
        collection.getClass();
        switch (i) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        switch (this.a) {
            case 0:
                this.b.clear();
                break;
            default:
                this.b.clear();
                break;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        int i = this.a;
        eb ebVar = this.b;
        switch (i) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                ebVar.getClass();
                int iF = ebVar.f(entry.getKey());
                if (iF < 0) {
                    return false;
                }
                Object[] objArr = ebVar.b;
                objArr.getClass();
                return k8.e(objArr[iF], entry.getValue());
            default:
                return ebVar.containsKey(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean containsAll(Collection collection) {
        switch (this.a) {
            case 0:
                collection.getClass();
                return this.b.d(collection);
            default:
                return super.containsAll(collection);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        switch (this.a) {
            case 0:
                break;
        }
        return this.b.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        int i = this.a;
        eb ebVar = this.b;
        switch (i) {
            case 0:
                ebVar.getClass();
                return new cb(ebVar, 0);
            default:
                ebVar.getClass();
                return new cb(ebVar, 1);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        int i = this.a;
        eb ebVar = this.b;
        switch (i) {
            case 0:
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry) obj;
                    ebVar.getClass();
                    ebVar.b();
                    int iF = ebVar.f(entry.getKey());
                    if (iF >= 0) {
                        Object[] objArr = ebVar.b;
                        objArr.getClass();
                        if (k8.e(objArr[iF], entry.getValue())) {
                            ebVar.j(iF);
                            return true;
                        }
                    }
                }
                return false;
            default:
                ebVar.b();
                int iF2 = ebVar.f(obj);
                if (iF2 < 0) {
                    return false;
                }
                ebVar.j(iF2);
                return true;
        }
    }

    @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean removeAll(Collection collection) {
        int i = this.a;
        eb ebVar = this.b;
        collection.getClass();
        switch (i) {
            case 0:
                ebVar.b();
                break;
            default:
                ebVar.b();
                break;
        }
        return super.removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean retainAll(Collection collection) {
        int i = this.a;
        eb ebVar = this.b;
        collection.getClass();
        switch (i) {
            case 0:
                ebVar.b();
                break;
            default:
                ebVar.b();
                break;
        }
        return super.retainAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        switch (this.a) {
            case 0:
                break;
            default:
                break;
        }
        return this.b.i;
    }
}
