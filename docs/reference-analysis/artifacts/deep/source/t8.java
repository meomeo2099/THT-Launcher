package defpackage;

import android.os.Parcel;
import java.io.Serializable;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class t8 implements j6 {
    public final /* synthetic */ int a = 1;
    public final /* synthetic */ int b;
    public final /* synthetic */ Serializable c;

    public /* synthetic */ t8(int i, String str) {
        this.b = i;
        this.c = str;
    }

    @Override // defpackage.j6
    public final Object b(Object obj) {
        int i = this.a;
        Serializable serializable = this.c;
        int i2 = this.b;
        switch (i) {
            case 0:
                ArrayList arrayList = (ArrayList) serializable;
                float fFloatValue = ((Float) obj).floatValue();
                ArrayList arrayList2 = new ArrayList();
                int size = arrayList.size();
                int i3 = 0;
                while (i3 < size) {
                    Object obj2 = arrayList.get(i3);
                    i3++;
                    if (obj2 instanceof j9) {
                        arrayList2.add(obj2);
                    }
                }
                j9 j9Var = (j9) i3.v0(arrayList2);
                return Float.valueOf(j9Var != null ? j9Var.a((int) fFloatValue, i2) : 0.0f);
            default:
                Parcel parcel = (Parcel) obj;
                parcel.getClass();
                parcel.writeInt(i2);
                parcel.writeString((String) serializable);
                return ti.a;
        }
    }

    public /* synthetic */ t8(ArrayList arrayList, int i) {
        this.c = arrayList;
        this.b = i;
    }
}
