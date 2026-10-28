package defpackage;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class bj {
    public final Float a;
    public final Float b;
    public final Float c;
    public final Float d;
    public final Boolean e;
    public final Map f;
    public final List g;
    public final boolean h;
    public final v2 i;
    public final Map j;
    public final String k;
    public final String l;
    public final Integer m;
    public final Boolean n;
    public final Integer o;
    public final Float p;
    public final Boolean q;
    public final Integer r;
    public final boolean s;
    public final Boolean t;
    public final Boolean u;
    public final Integer v;
    public final Boolean w;
    public final Boolean x;
    public final Boolean y;
    public final Integer z;

    public /* synthetic */ bj(Float f, Float f2, Float f3, Float f4, String str, String str2, int i) {
        Float f5 = (i & 1) != 0 ? null : f;
        Float f6 = (i & 2) != 0 ? null : f2;
        Float f7 = (i & 4) != 0 ? null : f3;
        Float f8 = (i & 8) != 0 ? null : f4;
        v2 v2Var = new v2();
        String strA = (i & 1024) != 0 ? si.a("connecting_to_the_vehicle", new Object[0]) : str;
        String str3 = (i & 2048) != 0 ? "" : str2;
        v4 v4Var = v4.a;
        this(f5, f6, f7, f8, null, v4Var, u4.a, false, v2Var, v4Var, strA, str3, null, null, null, null, null, null, false, null, null, null, null, null, null, null);
    }

    public static bj a(bj bjVar, Boolean bool, LinkedHashMap linkedHashMap, List list, v2 v2Var, Map map, String str, String str2, Integer num, Boolean bool2, Integer num2, Float f, Boolean bool3, Integer num3, boolean z, Boolean bool4, Boolean bool5, Integer num4, Boolean bool6, Boolean bool7, Boolean bool8, Integer num5, int i) {
        Float f2 = bjVar.a;
        Float f3 = bjVar.b;
        Float f4 = bjVar.c;
        Float f5 = bjVar.d;
        Boolean bool9 = (i & 16) != 0 ? bjVar.e : bool;
        Map map2 = (i & 32) != 0 ? bjVar.f : linkedHashMap;
        List list2 = (i & 64) != 0 ? bjVar.g : list;
        boolean z2 = (i & 128) != 0 ? bjVar.h : true;
        v2 v2Var2 = (i & 256) != 0 ? bjVar.i : v2Var;
        Map map3 = (i & 512) != 0 ? bjVar.j : map;
        String str3 = (i & 1024) != 0 ? bjVar.k : str;
        String str4 = (i & 2048) != 0 ? bjVar.l : str2;
        Integer num6 = (i & 4096) != 0 ? bjVar.m : num;
        Boolean bool10 = (i & 8192) != 0 ? bjVar.n : bool2;
        Integer num7 = (i & 16384) != 0 ? bjVar.o : num2;
        Float f6 = (i & 32768) != 0 ? bjVar.p : f;
        Boolean bool11 = (i & 65536) != 0 ? bjVar.q : bool3;
        Integer num8 = (i & 131072) != 0 ? bjVar.r : num3;
        boolean z3 = (i & 262144) != 0 ? bjVar.s : z;
        Boolean bool12 = (i & 524288) != 0 ? bjVar.t : bool4;
        Boolean bool13 = (i & 1048576) != 0 ? bjVar.u : bool5;
        Integer num9 = (i & 2097152) != 0 ? bjVar.v : num4;
        Boolean bool14 = (i & 4194304) != 0 ? bjVar.w : bool6;
        Boolean bool15 = (i & 8388608) != 0 ? bjVar.x : bool7;
        Boolean bool16 = (i & 16777216) != 0 ? bjVar.y : bool8;
        Integer num10 = (i & 33554432) != 0 ? bjVar.z : num5;
        bjVar.getClass();
        map2.getClass();
        list2.getClass();
        v2Var2.getClass();
        map3.getClass();
        str3.getClass();
        str4.getClass();
        return new bj(f2, f3, f4, f5, bool9, map2, list2, z2, v2Var2, map3, str3, str4, num6, bool10, num7, f6, bool11, num8, z3, bool12, bool13, num9, bool14, bool15, bool16, num10);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof bj)) {
            return false;
        }
        bj bjVar = (bj) obj;
        return k8.e(this.a, bjVar.a) && k8.e(this.b, bjVar.b) && k8.e(this.c, bjVar.c) && k8.e(this.d, bjVar.d) && k8.e(this.e, bjVar.e) && k8.e(this.f, bjVar.f) && k8.e(this.g, bjVar.g) && this.h == bjVar.h && k8.e(this.i, bjVar.i) && k8.e(this.j, bjVar.j) && k8.e(this.k, bjVar.k) && k8.e(this.l, bjVar.l) && k8.e(this.m, bjVar.m) && k8.e(this.n, bjVar.n) && k8.e(this.o, bjVar.o) && k8.e(this.p, bjVar.p) && k8.e(this.q, bjVar.q) && k8.e(this.r, bjVar.r) && this.s == bjVar.s && k8.e(this.t, bjVar.t) && k8.e(this.u, bjVar.u) && k8.e(this.v, bjVar.v) && k8.e(this.w, bjVar.w) && k8.e(this.x, bjVar.x) && k8.e(this.y, bjVar.y) && k8.e(this.z, bjVar.z);
    }

    public final int hashCode() {
        Float f = this.a;
        int iHashCode = (f == null ? 0 : f.hashCode()) * 31;
        Float f2 = this.b;
        int iHashCode2 = (iHashCode + (f2 == null ? 0 : f2.hashCode())) * 31;
        Float f3 = this.c;
        int iHashCode3 = (iHashCode2 + (f3 == null ? 0 : f3.hashCode())) * 31;
        Float f4 = this.d;
        int iHashCode4 = (iHashCode3 + (f4 == null ? 0 : f4.hashCode())) * 31;
        Boolean bool = this.e;
        int iB = c.b(c.b((this.j.hashCode() + ((this.i.hashCode() + ((Boolean.hashCode(this.h) + ((this.g.hashCode() + ((this.f.hashCode() + ((iHashCode4 + (bool == null ? 0 : bool.hashCode())) * 31)) * 31)) * 31)) * 31)) * 31)) * 31, 31, this.k), 31, this.l);
        Integer num = this.m;
        int iHashCode5 = (iB + (num == null ? 0 : num.hashCode())) * 31;
        Boolean bool2 = this.n;
        int iHashCode6 = (iHashCode5 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Integer num2 = this.o;
        int iHashCode7 = (iHashCode6 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Float f5 = this.p;
        int iHashCode8 = (iHashCode7 + (f5 == null ? 0 : f5.hashCode())) * 31;
        Boolean bool3 = this.q;
        int iHashCode9 = (iHashCode8 + (bool3 == null ? 0 : bool3.hashCode())) * 31;
        Integer num3 = this.r;
        int iHashCode10 = (Boolean.hashCode(this.s) + ((iHashCode9 + (num3 == null ? 0 : num3.hashCode())) * 31)) * 31;
        Boolean bool4 = this.t;
        int iHashCode11 = (iHashCode10 + (bool4 == null ? 0 : bool4.hashCode())) * 31;
        Boolean bool5 = this.u;
        int iHashCode12 = (iHashCode11 + (bool5 == null ? 0 : bool5.hashCode())) * 31;
        Integer num4 = this.v;
        int iHashCode13 = (iHashCode12 + (num4 == null ? 0 : num4.hashCode())) * 31;
        Boolean bool6 = this.w;
        int iHashCode14 = (iHashCode13 + (bool6 == null ? 0 : bool6.hashCode())) * 31;
        Boolean bool7 = this.x;
        int iHashCode15 = (iHashCode14 + (bool7 == null ? 0 : bool7.hashCode())) * 31;
        Boolean bool8 = this.y;
        int iHashCode16 = (iHashCode15 + (bool8 == null ? 0 : bool8.hashCode())) * 31;
        Integer num5 = this.z;
        return iHashCode16 + (num5 != null ? num5.hashCode() : 0);
    }

    public final String toString() {
        return "VehicleData(voltage=" + this.a + ", fuelLiters=" + this.b + ", fuelPercent=" + this.c + ", rangeKm=" + this.d + ", ambientOn=" + this.e + ", ambientColors=" + this.f + ", ambientLevels=" + this.g + ", ambientPowerWritable=" + this.h + ", climate=" + this.i + ", seats=" + this.j + ", message=" + this.k + ", diagnostics=" + this.l + ", roofPosition=" + this.m + ", roofOpen=" + this.n + ", driveMode=" + this.o + ", displayBrightness=" + this.p + ", startStop=" + this.q + ", cruiseMode=" + this.r + ", cruiseWritable=" + this.s + ", gPilot=" + this.t + ", roofTilt=" + this.u + ", curtainPosition=" + this.v + ", curtainOpen=" + this.w + ", trunkOpen=" + this.x + ", mirrorsFolded=" + this.y + ", exteriorLight=" + this.z + ")";
    }

    public bj(Float f, Float f2, Float f3, Float f4, Boolean bool, Map map, List list, boolean z, v2 v2Var, Map map2, String str, String str2, Integer num, Boolean bool2, Integer num2, Float f5, Boolean bool3, Integer num3, boolean z2, Boolean bool4, Boolean bool5, Integer num4, Boolean bool6, Boolean bool7, Boolean bool8, Integer num5) {
        str.getClass();
        str2.getClass();
        this.a = f;
        this.b = f2;
        this.c = f3;
        this.d = f4;
        this.e = bool;
        this.f = map;
        this.g = list;
        this.h = z;
        this.i = v2Var;
        this.j = map2;
        this.k = str;
        this.l = str2;
        this.m = num;
        this.n = bool2;
        this.o = num2;
        this.p = f5;
        this.q = bool3;
        this.r = num3;
        this.s = z2;
        this.t = bool4;
        this.u = bool5;
        this.v = num4;
        this.w = bool6;
        this.x = bool7;
        this.y = bool8;
        this.z = num5;
    }
}
