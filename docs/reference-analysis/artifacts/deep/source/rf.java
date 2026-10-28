package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class rf {
    public static final Set d;
    public final int a;
    public final int b;
    public final String c;

    static {
        List listI0 = j3.i0(16, 32, 64);
        ArrayList arrayList = new ArrayList();
        Iterator it = listI0.iterator();
        while (it.hasNext()) {
            int iIntValue = ((Number) it.next()).intValue();
            o3.p0(j3.i0("seat_heat_" + iIntValue, "seat_vent_" + iIntValue), arrayList);
        }
        d = i3.L0(arrayList);
    }

    public rf(int i, int i2, String str) {
        str.getClass();
        this.a = i;
        this.b = i2;
        this.c = str;
    }

    public final int a() {
        if (this.a == 268763648) {
            return sf.c;
        }
        return 3;
    }

    public final String b(Integer num) {
        Integer numC = c(num);
        if (num == null) {
            return si.a("unavailable", new Object[0]);
        }
        if (num.intValue() == this.a + 15) {
            return "AUTO";
        }
        if (numC == null) {
            return si.a("unavailable", new Object[0]);
        }
        return numC.intValue() == 0 ? si.a("off_65", new Object[0]) : si.a("level_0", numC);
    }

    public final Integer c(Integer num) {
        if (num != null) {
            int iIntValue = num.intValue();
            if (iIntValue == 0) {
                return 0;
            }
            if (1 <= iIntValue && iIntValue < 4) {
                return num;
            }
            int i = this.a;
            int i2 = i + 1;
            if (iIntValue <= i + 3 && i2 <= iIntValue) {
                return Integer.valueOf(iIntValue - i);
            }
            if (i != 268763648 && 268763905 <= iIntValue && iIntValue < 268763908) {
                return Integer.valueOf(iIntValue - 268763904);
            }
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof rf)) {
            return false;
        }
        rf rfVar = (rf) obj;
        return this.a == rfVar.a && this.b == rfVar.b && k8.e(this.c, rfVar.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + ((Integer.hashCode(this.b) + (Integer.hashCode(this.a) * 31)) * 31);
    }

    public final String toString() {
        return "SeatControl(function=" + this.a + ", zone=" + this.b + ", title=" + this.c + ")";
    }
}
