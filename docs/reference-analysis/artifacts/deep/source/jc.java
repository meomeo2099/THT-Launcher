package defpackage;

import android.graphics.Bitmap;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class jc {
    public final boolean a;
    public final boolean b;
    public final String c;
    public final String d;
    public final String e;
    public final Bitmap f;
    public final long g;
    public final long h;
    public final boolean i;
    public final boolean j;
    public final boolean k;
    public final boolean l;
    public final String m;
    public final boolean n;
    public final boolean o;
    public final boolean p;

    public /* synthetic */ jc(boolean z, String str, String str2, String str3, Bitmap bitmap, boolean z2, boolean z3, boolean z4, boolean z5, String str4, int i) {
        this((i & 1) != 0 ? false : z, (i & 2) == 0, (i & 4) != 0 ? "" : str, (i & 8) != 0 ? "" : str2, (i & 16) != 0 ? "" : str3, (i & 32) != 0 ? null : bitmap, -1L, 0L, (i & 256) != 0 ? false : z2, (i & 512) != 0 ? false : z3, (i & 1024) != 0 ? false : z4, (i & 2048) != 0 ? false : z5, (i & 4096) != 0 ? "" : str4, false, false, false);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof jc)) {
            return false;
        }
        jc jcVar = (jc) obj;
        return this.a == jcVar.a && this.b == jcVar.b && k8.e(this.c, jcVar.c) && k8.e(this.d, jcVar.d) && k8.e(this.e, jcVar.e) && k8.e(this.f, jcVar.f) && this.g == jcVar.g && this.h == jcVar.h && this.i == jcVar.i && this.j == jcVar.j && this.k == jcVar.k && this.l == jcVar.l && k8.e(this.m, jcVar.m) && this.n == jcVar.n && this.o == jcVar.o && this.p == jcVar.p;
    }

    public final int hashCode() {
        int iB = c.b(c.b(c.b((Boolean.hashCode(this.b) + (Boolean.hashCode(this.a) * 31)) * 31, 31, this.c), 31, this.d), 31, this.e);
        Bitmap bitmap = this.f;
        return Boolean.hashCode(this.p) + ((Boolean.hashCode(this.o) + ((Boolean.hashCode(this.n) + c.b((Boolean.hashCode(this.l) + ((Boolean.hashCode(this.k) + ((Boolean.hashCode(this.j) + ((Boolean.hashCode(this.i) + ((Long.hashCode(this.h) + ((Long.hashCode(this.g) + ((iB + (bitmap == null ? 0 : bitmap.hashCode())) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31, 31, this.m)) * 31)) * 31);
    }

    public final String toString() {
        return "MusicSnapshot(access=" + this.a + ", available=" + this.b + ", title=" + this.c + ", artist=" + this.d + ", packageName=" + this.e + ", art=" + this.f + ", position=" + this.g + ", duration=" + this.h + ", playing=" + this.i + ", canToggle=" + this.j + ", canPrevious=" + this.k + ", canNext=" + this.l + ", source=" + this.m + ", canLike=" + this.n + ", liked=" + this.o + ", canSeek=" + this.p + ")";
    }

    public jc(boolean z, boolean z2, String str, String str2, String str3, Bitmap bitmap, long j, long j2, boolean z3, boolean z4, boolean z5, boolean z6, String str4, boolean z7, boolean z8, boolean z9) {
        str.getClass();
        str2.getClass();
        str3.getClass();
        str4.getClass();
        this.a = z;
        this.b = z2;
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = bitmap;
        this.g = j;
        this.h = j2;
        this.i = z3;
        this.j = z4;
        this.k = z5;
        this.l = z6;
        this.m = str4;
        this.n = z7;
        this.o = z8;
        this.p = z9;
    }
}
