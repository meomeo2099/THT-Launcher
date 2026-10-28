package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class hf extends b {
    public int c;
    public int d;
    public final /* synthetic */ jf e;

    public hf(jf jfVar) {
        this.e = jfVar;
        this.c = jfVar.d;
        this.d = jfVar.c;
    }

    @Override // defpackage.b
    public final void a() {
        int i = this.c;
        if (i == 0) {
            this.a = 2;
            return;
        }
        jf jfVar = this.e;
        Object[] objArr = jfVar.a;
        int i2 = this.d;
        this.b = objArr[i2];
        this.a = 1;
        this.d = (i2 + 1) % jfVar.b;
        this.c = i - 1;
    }
}
