package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
import org.reference.panel.Guard;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class ni {
    public static volatile li N;
    public static volatile p2 O = new p2(null, null, null, null);
    public static final Set P = x0.Y0(new Integer[]{2097410, 2097411, 2097412, 2097413});
    public boolean A;
    public final te B;
    public float C;
    public long D;
    public float E;
    public int F;
    public float G;
    public long H;
    public long I;
    public float J;
    public long K;
    public final r1 L;
    public float M;
    public final b1 a;
    public final kh b;
    public final Context c;
    public final SharedPreferences d;
    public final Handler e;
    public volatile boolean f;
    public boolean g;
    public long h;
    public long i;
    public long j;
    public long k;
    public long l;
    public boolean m;
    public float n;
    public float o;
    public float p;
    public mi q;
    public float r;
    public float s;
    public float t;
    public float u;
    public int v;
    public float w;
    public float x;
    public float y;
    public float z;

    public ni(Context context, b1 b1Var) {
        this.a = b1Var;
        this.b = new kh(context);
        Context applicationContext = context.getApplicationContext();
        this.c = applicationContext;
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("panel", 0);
        this.d = sharedPreferences;
        HandlerThread handlerThread = new HandlerThread("ReferencePanel-stats");
        handlerThread.start();
        this.e = new Handler(handlerThread.getLooper());
        this.o = -1.0f;
        this.p = -1.0f;
        this.q = mi.a;
        float f = sharedPreferences.getFloat("stats_fuel_base", -1.0f);
        te teVar = new te();
        teVar.a = f;
        teVar.b = -1.0f;
        teVar.c = -1.0f;
        this.B = teVar;
        this.C = f;
        this.D = -1L;
        this.G = sharedPreferences.getFloat("stats_odo_scale", 0.0f);
        this.L = new r1(13, this);
    }

    /* JADX WARN: Code duplicated, block: B:105:0x01ab  */
    /* JADX WARN: Code duplicated, block: B:123:0x01f0  */
    /* JADX WARN: Code duplicated, block: B:125:0x01f9  */
    /* JADX WARN: Code duplicated, block: B:131:0x0209  */
    /* JADX WARN: Code duplicated, block: B:144:0x0239  */
    /* JADX WARN: Code duplicated, block: B:184:0x02d4  */
    /* JADX WARN: Code duplicated, block: B:186:0x02e2  */
    /* JADX WARN: Code duplicated, block: B:187:0x02e9  */
    /* JADX WARN: Code duplicated, block: B:190:0x02fd  */
    /* JADX WARN: Code duplicated, block: B:192:0x0302  */
    /* JADX WARN: Code duplicated, block: B:195:0x0311  */
    /* JADX WARN: Code duplicated, block: B:198:0x031d  */
    /* JADX WARN: Code duplicated, block: B:199:0x0320  */
    /* JADX WARN: Code duplicated, block: B:201:0x0332  */
    /* JADX WARN: Code duplicated, block: B:203:0x0336  */
    /* JADX WARN: Code duplicated, block: B:205:0x033c  */
    /* JADX WARN: Code duplicated, block: B:207:0x0352  */
    /* JADX WARN: Code duplicated, block: B:209:0x0356  */
    /* JADX WARN: Code duplicated, block: B:212:0x0363  */
    /* JADX WARN: Code duplicated, block: B:215:0x036a  */
    /* JADX WARN: Code duplicated, block: B:216:0x036c  */
    /* JADX WARN: Code duplicated, block: B:218:0x03a4  */
    /* JADX WARN: Code duplicated, block: B:220:0x03aa  */
    /* JADX WARN: Code duplicated, block: B:221:0x03af A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:224:0x03be  */
    /* JADX WARN: Code duplicated, block: B:225:0x03d4  */
    /* JADX WARN: Code duplicated, block: B:273:0x04c6  */
    /* JADX WARN: Code duplicated, block: B:303:0x0532  */
    /* JADX WARN: Code duplicated, block: B:61:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:76:0x0127  */
    /* JADX WARN: Code duplicated, block: B:78:0x012b  */
    /* JADX WARN: Code duplicated, block: B:79:0x0130  */
    public static final void a(ni niVar) {
        long j;
        Float fN;
        Integer numJ;
        Integer numJ2;
        Float fValueOf;
        float fFloatValue;
        int iIntValue;
        float f;
        long j2;
        boolean z;
        Float fN2;
        float f2;
        Integer numJ3;
        float f3;
        int iIntValue2;
        Integer numValueOf;
        Object seVar;
        long j3;
        float f4;
        float f5;
        float fR;
        int i;
        se seVar2;
        Integer numH;
        int iIntValue3;
        float f6;
        float fR2;
        boolean z2;
        Float fV;
        Float fV2;
        Float fV3;
        Float fV4;
        Float fV5;
        Long lValueOf;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j4 = jElapsedRealtime - niVar.H;
        float fR3 = pi.r(j4 / 1000.0f, 0.0f, 10.0f);
        niVar.H = jElapsedRealtime;
        long j5 = 0;
        Float f7 = null;
        if (niVar.g) {
            long j6 = niVar.k;
            j = 90000;
            long j7 = niVar.j;
            if (0 > j4 || j4 > 90000) {
                lValueOf = j7 > 0 ? Long.valueOf(j7) : Long.valueOf(j6);
            } else {
                lValueOf = null;
            }
            if (lValueOf != null) {
                niVar.e(lValueOf.longValue());
                niVar.m = false;
            }
        } else {
            j = 90000;
        }
        r4 r4VarG = niVar.g();
        Integer numValueOf2 = (r4VarG == null || (fV5 = r4VarG.v(2097408)) == null) ? null : Integer.valueOf(Math.round(fV5.floatValue()));
        boolean z3 = (numValueOf2 != null && numValueOf2.intValue() == 2097415) || (numValueOf2 != null && numValueOf2.intValue() == 2097414) || (((fN = niVar.n()) != null && fN.floatValue() > 300.0f) || ((numValueOf2 == null || !P.contains(numValueOf2)) && (numJ = niVar.j(678428902)) != null && numJ.intValue() == 3));
        r4 r4VarG2 = niVar.g();
        if (r4VarG2 == null || (fV4 = r4VarG2.v(1048832)) == null) {
            numJ2 = niVar.j(678428840);
            if (numJ2 != null || (iIntValue = numJ2.intValue()) < 0 || iIntValue == 65535 || iIntValue == 32767) {
                fValueOf = null;
            } else if (iIntValue <= 300) {
                fValueOf = Float.valueOf(iIntValue);
            } else if (iIntValue <= 30000) {
                fValueOf = Float.valueOf(iIntValue / 100.0f);
            } else {
                fValueOf = null;
            }
            if (fValueOf != null) {
                fFloatValue = fValueOf.floatValue();
            } else {
                fFloatValue = 0.0f;
            }
        } else {
            float fFloatValue2 = fV4.floatValue();
            Float fValueOf2 = (Math.abs(fFloatValue2) > Float.MAX_VALUE || fFloatValue2 < 0.0f || fFloatValue2 > 100.0f) ? null : Float.valueOf(fFloatValue2 * 3.6f);
            if (fValueOf2 != null) {
                fFloatValue = fValueOf2.floatValue();
            } else {
                numJ2 = niVar.j(678428840);
                if (numJ2 != null) {
                    fValueOf = null;
                } else {
                    fValueOf = null;
                }
                if (fValueOf != null) {
                    fFloatValue = fValueOf.floatValue();
                } else {
                    fFloatValue = 0.0f;
                }
            }
        }
        if (z3) {
            niVar.i = 0L;
            niVar.j = 0L;
        }
        int i2 = niVar.F;
        niVar.F = i2 + 1;
        if (i2 % 3 != 0 || niVar.g() == null) {
            f = fR3;
            j2 = 0;
        } else {
            r4 r4VarG3 = niVar.g();
            Float fD = sh.d(r4VarG3 != null ? r4VarG3.L(678429191) : null);
            if (fD == null) {
                Iterator it = j3.i0(1050112, 4211968).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        j2 = j5;
                        fD = null;
                        break;
                    }
                    int iIntValue4 = ((Number) it.next()).intValue();
                    j2 = j5;
                    r4 r4VarG4 = niVar.g();
                    if (r4VarG4 == null || (fV3 = r4VarG4.v(iIntValue4)) == null) {
                        fV3 = null;
                    } else {
                        float fFloatValue3 = fV3.floatValue();
                        if (0.1f > fFloatValue3 || fFloatValue3 > 100.0f) {
                            fV3 = null;
                        }
                    }
                    if (fV3 != null) {
                        fD = fV3;
                        break;
                    }
                    j5 = j2;
                }
            } else {
                j2 = 0;
            }
            Integer numH2 = niVar.h();
            r4 r4VarG5 = niVar.g();
            if (r4VarG5 == null || (fV2 = r4VarG5.v(4206592)) == null) {
                numJ3 = niVar.j(678428837);
                if (numJ3 != null) {
                    iIntValue2 = numJ3.intValue();
                    f3 = 100.0f;
                    if (iIntValue2 >= 0 || iIntValue2 >= 30001) {
                    }
                } else {
                    f3 = 100.0f;
                }
                numJ3 = null;
            } else {
                float fFloatValue4 = fV2.floatValue();
                if (0.0f > fFloatValue4 || fFloatValue4 > 30000.0f) {
                    fV2 = null;
                }
                if (fV2 != null) {
                    numJ3 = Integer.valueOf((int) fV2.floatValue());
                    f3 = 100.0f;
                } else {
                    numJ3 = niVar.j(678428837);
                    if (numJ3 != null) {
                        iIntValue2 = numJ3.intValue();
                        f3 = 100.0f;
                        if (iIntValue2 >= 0) {
                        }
                    } else {
                        f3 = 100.0f;
                    }
                    numJ3 = null;
                }
            }
            r4 r4VarG6 = niVar.g();
            if (r4VarG6 == null || (fV = r4VarG6.v(4206848)) == null) {
                numValueOf = null;
            } else {
                float fFloatValue5 = fV.floatValue();
                if (0.0f > fFloatValue5 || fFloatValue5 > 730.0f) {
                    fV = null;
                }
                if (fV != null) {
                    numValueOf = Integer.valueOf((int) fV.floatValue());
                } else {
                    numValueOf = null;
                }
            }
            O = new p2(numH2, numJ3, numValueOf, fD);
            if (fD != null) {
                float fFloatValue6 = fD.floatValue();
                boolean z4 = fFloatValue < 1.0f;
                qe qeVar = qe.j;
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                te teVar = niVar.B;
                float f8 = teVar.a;
                if (f8 < 0.0f) {
                    teVar.a = fFloatValue6;
                    f = fR3;
                } else {
                    if (teVar.b >= 0.0f) {
                        f = fR3;
                        if (!z4 || jCurrentTimeMillis2 - teVar.d > 900000) {
                            teVar.b = -1.0f;
                            Float fValueOf3 = Float.valueOf(teVar.c);
                            if (!z4) {
                                fValueOf3 = null;
                            }
                            teVar.a = Math.max(fFloatValue6, fValueOf3 != null ? fValueOf3.floatValue() : fFloatValue6);
                            seVar = qeVar;
                        } else if (fFloatValue6 > teVar.c + 0.5f) {
                            teVar.c = fFloatValue6;
                            seVar = new re(fFloatValue6);
                        }
                    } else {
                        f = fR3;
                        if (z4 && fFloatValue6 - f8 >= 8.0f) {
                            teVar.b = f8;
                            teVar.c = fFloatValue6;
                            teVar.d = jCurrentTimeMillis2;
                            seVar = new se(f8, fFloatValue6);
                        } else if (!z4 || fFloatValue6 < f8) {
                            teVar.a = fFloatValue6;
                        }
                    }
                    if (seVar instanceof se) {
                        seVar2 = (se) seVar;
                        niVar.E = seVar2.j;
                        kh khVar = niVar.b;
                        numH = niVar.h();
                        if (numH != null) {
                            iIntValue3 = numH.intValue();
                        } else {
                            iIntValue3 = 0;
                        }
                        float f9 = seVar2.j;
                        f6 = seVar2.k;
                        float f10 = niVar.d.getFloat("tank_liters", 50.0f);
                        if (f6 > f3) {
                            f3 = f6;
                        }
                        fR2 = f3 - pi.r(f9, 0.0f, f3);
                        if (fR2 < 0.0f) {
                            fR2 = 0.0f;
                        }
                        float f11 = (fR2 / f3) * f10;
                        if (seVar2.k >= 97.0f) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        niVar.D = khVar.a(jCurrentTimeMillis2, iIntValue3, f9, f6, f11, z2, false);
                    } else if (seVar instanceof re) {
                        j3 = niVar.D;
                        if (j3 > j2) {
                            kh khVar2 = niVar.b;
                            f4 = ((re) seVar).j;
                            float f12 = niVar.E;
                            float f13 = niVar.d.getFloat("tank_liters", 50.0f);
                            if (f4 > 100.0f) {
                                f5 = 100.0f;
                            } else {
                                f5 = f4;
                            }
                            fR = f5 - pi.r(f12, 0.0f, 100.0f);
                            if (fR < 0.0f) {
                                fR = 0.0f;
                            }
                            float f14 = (fR / 100.0f) * f13;
                            if (f4 >= 97.0f) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            khVar2.getClass();
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("percentAfter", Float.valueOf(f4));
                            contentValues.put("liters", Float.valueOf(f14));
                            contentValues.put("full", Integer.valueOf(i));
                            khVar2.getWritableDatabase().update("refuels", contentValues, "id = ?", new String[]{String.valueOf(j3)});
                        }
                    } else if (k8.e(seVar, qeVar)) {
                        niVar.D = -1L;
                    } else if (seVar != null) {
                        throw new vc();
                    }
                    if (Math.abs(teVar.a - niVar.C) >= 0.5f) {
                        niVar.C = teVar.a;
                        niVar.d.edit().putFloat("stats_fuel_base", teVar.a).apply();
                    }
                }
                seVar = null;
                if (seVar instanceof se) {
                    seVar2 = (se) seVar;
                    niVar.E = seVar2.j;
                    kh khVar3 = niVar.b;
                    numH = niVar.h();
                    if (numH != null) {
                        iIntValue3 = numH.intValue();
                    } else {
                        iIntValue3 = 0;
                    }
                    float f15 = seVar2.j;
                    f6 = seVar2.k;
                    float f16 = niVar.d.getFloat("tank_liters", 50.0f);
                    if (f6 > f3) {
                        f3 = f6;
                    }
                    fR2 = f3 - pi.r(f15, 0.0f, f3);
                    if (fR2 < 0.0f) {
                        fR2 = 0.0f;
                    }
                    float f17 = (fR2 / f3) * f16;
                    if (seVar2.k >= 97.0f) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    niVar.D = khVar3.a(jCurrentTimeMillis2, iIntValue3, f15, f6, f17, z2, false);
                } else if (seVar instanceof re) {
                    j3 = niVar.D;
                    if (j3 > j2) {
                        kh khVar4 = niVar.b;
                        f4 = ((re) seVar).j;
                        float f18 = niVar.E;
                        float f19 = niVar.d.getFloat("tank_liters", 50.0f);
                        if (f4 > 100.0f) {
                            f5 = 100.0f;
                        } else {
                            f5 = f4;
                        }
                        fR = f5 - pi.r(f18, 0.0f, 100.0f);
                        if (fR < 0.0f) {
                            fR = 0.0f;
                        }
                        float f110 = (fR / 100.0f) * f19;
                        if (f4 >= 97.0f) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        khVar4.getClass();
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("percentAfter", Float.valueOf(f4));
                        contentValues2.put("liters", Float.valueOf(f110));
                        contentValues2.put("full", Integer.valueOf(i));
                        khVar4.getWritableDatabase().update("refuels", contentValues2, "id = ?", new String[]{String.valueOf(j3)});
                    }
                } else if (k8.e(seVar, qeVar)) {
                    niVar.D = -1L;
                } else if (seVar != null) {
                    throw new vc();
                }
                if (Math.abs(teVar.a - niVar.C) >= 0.5f) {
                    niVar.C = teVar.a;
                    niVar.d.edit().putFloat("stats_fuel_base", teVar.a).apply();
                }
            } else {
                f = fR3;
            }
        }
        if (!niVar.g) {
            if (z3) {
                niVar.g = true;
                long jCurrentTimeMillis3 = System.currentTimeMillis();
                niVar.h = jCurrentTimeMillis3;
                niVar.k = jCurrentTimeMillis3;
                long j8 = j2;
                niVar.i = j8;
                niVar.j = j8;
                niVar.n = 0.0f;
                niVar.o = -1.0f;
                niVar.p = -1.0f;
                niVar.q = mi.a;
                niVar.s = 0.0f;
                niVar.t = 0.0f;
                niVar.u = 0.0f;
                niVar.v = 0;
                niVar.w = 0.0f;
                niVar.x = 0.0f;
                niVar.y = 0.0f;
                niVar.z = 0.0f;
                niVar.A = false;
                niVar.m = true;
                niVar.k();
                niVar.b(SystemClock.elapsedRealtime());
            }
            niVar.l(z3);
            niVar.m = z3;
            if (!niVar.g) {
                return;
            }
        }
        if (!z3 || niVar.m) {
            z = false;
        } else {
            float fD2 = niVar.d();
            if (niVar.A) {
                float f20 = niVar.x;
                float f21 = niVar.z;
                float f22 = fD2 - niVar.y;
                f2 = 0.0f;
                niVar.x = ((f21 * (f22 < 0.0f ? 0.0f : f22)) / 100.0f) + f20;
            } else {
                f2 = 0.0f;
            }
            niVar.y = fD2;
            niVar.z = f2;
            z = false;
            niVar.A = false;
        }
        niVar.m = z3;
        boolean z5 = fFloatValue >= 1.0f ? true : z;
        if (z5) {
            niVar.t += f;
            niVar.v = Math.max(niVar.v, (int) fFloatValue);
        } else if (z3) {
            niVar.u += f;
        }
        float f23 = (fFloatValue * f) / 3600.0f;
        niVar.n += f23;
        niVar.k();
        r4 r4VarG7 = niVar.g();
        Float fV6 = r4VarG7 != null ? r4VarG7.v(4194816) : null;
        if (fV6 != null) {
            float fFloatValue7 = fV6.floatValue();
            if (Math.abs(fFloatValue7) > Float.MAX_VALUE || fFloatValue7 < 0.0f) {
                fV6 = null;
            } else if (fFloatValue7 > 99.0f) {
                float f24 = fFloatValue7 / 10.0f;
                if (f24 <= 99.0f) {
                    fV6 = Float.valueOf(f24);
                } else {
                    fV6 = null;
                }
            }
        } else {
            fV6 = null;
        }
        if (fV6 != null) {
            if (!z5) {
                fV6 = null;
            }
            if (fV6 != null) {
                niVar.w = ((fV6.floatValue() * f23) / 100.0f) + niVar.w;
            }
        }
        if (!z5 && z3 && ((fN2 = niVar.n()) == null || fN2.floatValue() > 300.0f)) {
            niVar.w = ((0.8f * f) / 3600.0f) + niVar.w;
        }
        r4 r4VarG8 = niVar.g();
        Float fV7 = r4VarG8 != null ? r4VarG8.v(4195072) : null;
        if (fV7 != null) {
            float fFloatValue8 = fV7.floatValue();
            if (Math.abs(fFloatValue8) > Float.MAX_VALUE || fFloatValue8 < 0.0f) {
                fV7 = null;
            } else if (fFloatValue8 > 40.0f) {
                float f25 = fFloatValue8 / 10.0f;
                if (f25 <= 40.0f) {
                    fV7 = Float.valueOf(f25);
                } else {
                    fV7 = null;
                }
            }
        } else {
            fV7 = null;
        }
        if (fV7 != null && fV7.floatValue() > 0.0f) {
            f7 = fV7;
        }
        if (f7 != null) {
            niVar.z = f7.floatValue();
            niVar.A = true;
        }
        niVar.l(z3);
        niVar.k = jCurrentTimeMillis;
        if (!z3) {
            if (niVar.i == 0) {
                niVar.i = jElapsedRealtime;
                niVar.j = jCurrentTimeMillis;
                niVar.b(jElapsedRealtime);
            }
            if (jElapsedRealtime - niVar.i >= j) {
                niVar.e(niVar.j);
                return;
            }
        }
        if (jElapsedRealtime - niVar.l >= 30000) {
            niVar.b(jElapsedRealtime);
        }
        if (jElapsedRealtime - niVar.K > 21600000) {
            niVar.K = jElapsedRealtime;
            niVar.b.getWritableDatabase().delete("voltage", "ts < ?", new String[]{String.valueOf(System.currentTimeMillis() - 2592000000L)});
        }
    }

    public final void b(long j) {
        if (this.g) {
            this.l = j;
            this.d.edit().putString("stats_open_trip", new JSONObject().put("startedAt", this.h).put("lastWall", this.k).put("stoppedWall", this.j).put("elapsed", j).put("stoppedElapsed", this.i).put("speedKm", this.n).put("startOdoRaw", this.o).put("lastOdoRaw", this.p).put("odoSource", this.q.name()).put("odoScale", this.r).put("speedKmAtOdoChange", this.s).put("odoBaseKm", this.M).put("driveSec", this.t).put("idleSec", this.u).put("maxSpeed", this.v).put("instFuelL", this.w).put("carFuelDoneL", this.x).put("segmentStartKm", this.y).put("carAvg", this.z).put("carAvgSeen", this.A).put("km", d()).toString()).apply();
        }
    }

    public final li c() {
        if (!this.g) {
            return null;
        }
        float fD = d();
        float f = f(fD);
        return new li(0L, this.h, System.currentTimeMillis(), fD, (int) this.t, (int) this.u, this.v, fD > 0.5f ? (f / fD) * 100.0f : 0.0f, f);
    }

    public final float d() {
        if (this.q != mi.a) {
            float f = this.r;
            float f2 = 0.0f;
            if (f != 0.0f) {
                float f3 = this.o;
                if (f3 >= 0.0f) {
                    float f4 = this.M + ((this.p - f3) * f);
                    float f5 = this.n - this.s;
                    if (f < 0.1f) {
                        f = 0.1f;
                    }
                    if (f5 > 0.0f) {
                        f2 = (f5 <= 3.0f * f && f5 > f) ? f : f5;
                    }
                    return f4 + f2;
                }
            }
        }
        return this.n;
    }

    public final void e(long j) {
        int i;
        int i2 = 0;
        this.g = false;
        N = null;
        this.d.edit().remove("stats_open_trip").apply();
        float fD = d();
        float f = f(fD);
        if (fD >= 0.3f || this.t >= 60.0f) {
            float f2 = this.o;
            if (f2 <= 0.0f) {
                i = 0;
            } else {
                float f3 = this.r;
                if (f3 <= 0.0f) {
                    f3 = 1.0f;
                }
                i = (int) (f2 * f3);
            }
            float f4 = this.p;
            if (f4 > 0.0f) {
                float f5 = this.r;
                i2 = (int) (f4 * (f5 > 0.0f ? f5 : 1.0f));
            }
            kh khVar = this.b;
            long j2 = this.h;
            long jMax = Math.max(j, j2);
            int i3 = (int) this.t;
            int i4 = (int) this.u;
            int i5 = this.v;
            khVar.getClass();
            float f6 = fD > 0.5f ? (f / fD) * 100.0f : 0.0f;
            ContentValues contentValues = new ContentValues();
            contentValues.put("startedAt", Long.valueOf(j2));
            contentValues.put("endedAt", Long.valueOf(jMax));
            contentValues.put("startOdo", Integer.valueOf(i));
            contentValues.put("endOdo", Integer.valueOf(i2));
            contentValues.put("distanceKm", Float.valueOf(fD));
            contentValues.put("driveSec", Integer.valueOf(i3));
            contentValues.put("idleSec", Integer.valueOf(i4));
            contentValues.put("maxSpeed", Integer.valueOf(i5));
            contentValues.put("avgConsumption", Float.valueOf(f6));
            contentValues.put("fuelUsedL", Float.valueOf(f));
            khVar.getWritableDatabase().insert("trips", null, contentValues);
            String str = kh.a.format(new Date(jMax));
            str.getClass();
            khVar.getWritableDatabase().execSQL("INSERT INTO days(date, distanceKm, trips, driveSec, idleSec, fuelUsedL)\nVALUES(?, ?, 1, ?, ?, ?)\nON CONFLICT(date) DO UPDATE SET\n    distanceKm = distanceKm + excluded.distanceKm,\n    trips      = trips + 1,\n    driveSec   = driveSec + excluded.driveSec,\n    idleSec    = idleSec + excluded.idleSec,\n    fuelUsedL  = fuelUsedL + excluded.fuelUsedL", new Object[]{str, Float.valueOf(fD), Integer.valueOf(i3), Integer.valueOf(i4), Float.valueOf(f)});
            String.format("%.1f", Arrays.copyOf(new Object[]{Float.valueOf(fD)}, 1));
        }
    }

    public final float f(float f) {
        boolean z = this.A;
        float f2 = 0.0f;
        if (!z && this.x == 0.0f) {
            return this.w;
        }
        if (z) {
            float f3 = this.z;
            float f4 = f - this.y;
            f2 = (f3 * (f4 >= 0.0f ? f4 : 0.0f)) / 100.0f;
        }
        return this.x + f2;
    }

    public final r4 g() {
        r4 r4Var = (r4) this.a.a();
        if (r4Var == null || !r4Var.m()) {
            return null;
        }
        Guard guard = Guard.INSTANCE;
        Context context = this.c;
        context.getClass();
        if (guard.offset(context) == 0) {
            return r4Var;
        }
        return null;
    }

    public final Integer h() {
        yc ycVarI = i();
        if (ycVarI == null) {
            return null;
        }
        float fFloatValue = ((Number) ycVarI.a).floatValue();
        if (((mi) ycVarI.b) == mi.b) {
            return Integer.valueOf((int) fFloatValue);
        }
        float f = this.G;
        if (f > 0.0f) {
            return Integer.valueOf((int) (fFloatValue * f));
        }
        return null;
    }

    public final yc i() {
        Float fV;
        r4 r4VarG = g();
        if (r4VarG != null && (fV = r4VarG.v(1050368)) != null) {
            float fFloatValue = fV.floatValue();
            if (1.0f > fFloatValue || fFloatValue > 2000000.0f) {
                fV = null;
            }
            if (fV != null) {
                return new yc(Float.valueOf(fV.floatValue()), mi.b);
            }
        }
        Integer numJ = j(678428835);
        if (numJ != null) {
            int iIntValue = numJ.intValue();
            if (1 > iIntValue || iIntValue >= 20000001) {
                numJ = null;
            }
            if (numJ != null) {
                return new yc(Float.valueOf(numJ.intValue()), mi.c);
            }
        }
        return null;
    }

    public final Integer j(int i) {
        r4 r4VarG = g();
        byte[] bArrL = r4VarG != null ? r4VarG.L(i) : null;
        if (bArrL == null || bArrL.length == 0 || bArrL.length > 4) {
            return null;
        }
        int length = bArrL.length - 1;
        int i2 = 0;
        if (length >= 0) {
            while (true) {
                int i3 = length - 1;
                i2 = (i2 << 8) | (bArrL[length] & 255);
                if (i3 < 0) {
                    break;
                }
                length = i3;
            }
        }
        return Integer.valueOf(i2);
    }

    /* JADX WARN: Code duplicated, block: B:40:0x0087  */
    public final void k() {
        yc ycVarI = i();
        if (ycVarI == null) {
            return;
        }
        float fFloatValue = ((Number) ycVarI.a).floatValue();
        mi miVar = (mi) ycVarI.b;
        float f = 1.0f;
        if (miVar != this.q) {
            this.q = miVar;
            this.r = miVar != mi.b ? this.G : 1.0f;
            this.o = fFloatValue;
            this.p = fFloatValue;
            float f2 = this.n;
            this.s = f2;
            this.M = f2;
            return;
        }
        float f3 = this.p;
        if (fFloatValue >= f3) {
            float f4 = this.r;
            if (f4 <= 0.0f || (fFloatValue - f3) * f4 <= 50.0f) {
                if (fFloatValue > f3) {
                    this.p = fFloatValue;
                    this.s = this.n;
                }
                if (f4 == 0.0f) {
                    float f5 = fFloatValue - this.o;
                    float f6 = this.n - this.M;
                    if (f6 < 2.0f || f5 <= 0.0f) {
                        f = 0.0f;
                    } else {
                        float f7 = f5 / f6;
                        if (5.0f <= f7 && f7 <= 20.0f) {
                            f = 0.1f;
                        } else if (0.4f > f7 || f7 > 2.5f) {
                            f = 0.0f;
                        }
                    }
                    this.r = f;
                    if (f <= 0.0f || miVar != mi.c) {
                        return;
                    }
                    this.G = f;
                    this.d.edit().putFloat("stats_odo_scale", this.r).apply();
                }
            }
        }
    }

    public final void l(boolean z) {
        r4 r4VarG = g();
        Float fA = sh.a(r4VarG != null ? r4VarG.L(678428900) : null);
        if (fA != null) {
            float fFloatValue = fA.floatValue();
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            if (Math.abs(fFloatValue - this.J) < 0.2f && jElapsedRealtime - this.I < 60000) {
                return;
            }
            this.J = fFloatValue;
            this.I = jElapsedRealtime;
            long jCurrentTimeMillis = System.currentTimeMillis();
            kh khVar = this.b;
            khVar.getClass();
            ContentValues contentValues = new ContentValues();
            contentValues.put("ts", Long.valueOf(jCurrentTimeMillis));
            contentValues.put("v", fA);
            contentValues.put("engine", Integer.valueOf(z ? 1 : 0));
            khVar.getWritableDatabase().insertWithOnConflict("voltage", null, contentValues, 5);
        }
    }

    public final void m() throws JSONException {
        Object ffVar;
        long jOptLong;
        Long lValueOf = null;
        String string = this.d.getString("stats_open_trip", null);
        if (string != null) {
            JSONObject jSONObject = new JSONObject(string);
            this.h = jSONObject.getLong("startedAt");
            this.k = jSONObject.getLong("lastWall");
            this.j = jSONObject.optLong("stoppedWall");
            this.n = (float) jSONObject.getDouble("speedKm");
            this.o = (float) jSONObject.getDouble("startOdoRaw");
            this.p = (float) jSONObject.getDouble("lastOdoRaw");
            try {
                String string2 = jSONObject.getString("odoSource");
                string2.getClass();
                ffVar = mi.valueOf(string2);
            } catch (Throwable th) {
                ffVar = new ff(th);
            }
            Object obj = mi.a;
            if (ffVar instanceof ff) {
                ffVar = obj;
            }
            this.q = (mi) ffVar;
            this.r = (float) jSONObject.getDouble("odoScale");
            this.s = (float) jSONObject.getDouble("speedKmAtOdoChange");
            this.M = (float) jSONObject.getDouble("odoBaseKm");
            this.t = (float) jSONObject.getDouble("driveSec");
            this.u = (float) jSONObject.getDouble("idleSec");
            this.v = jSONObject.getInt("maxSpeed");
            this.w = (float) jSONObject.getDouble("instFuelL");
            this.x = (float) jSONObject.getDouble("carFuelDoneL");
            this.y = (float) jSONObject.getDouble("segmentStartKm");
            this.z = (float) jSONObject.getDouble("carAvg");
            this.A = jSONObject.getBoolean("carAvgSeen");
            this.g = true;
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            long jCurrentTimeMillis = System.currentTimeMillis();
            long j = jSONObject.getLong("elapsed");
            long j2 = jElapsedRealtime >= j ? jElapsedRealtime - j : jCurrentTimeMillis - this.k;
            long j3 = this.k;
            long j4 = this.j;
            if (0 > j2 || j2 > 90000) {
                lValueOf = j4 > 0 ? Long.valueOf(j4) : Long.valueOf(j3);
            }
            if (lValueOf != null) {
                e(lValueOf.longValue());
                return;
            }
            if (this.j == 0) {
                jOptLong = 0;
            } else if (jElapsedRealtime < j || jSONObject.optLong("stoppedElapsed") == 0) {
                long j5 = jCurrentTimeMillis - this.j;
                if (j5 < 0) {
                    j5 = 0;
                }
                long j6 = jElapsedRealtime - j5;
                jOptLong = j6 == 0 ? -1L : j6;
            } else {
                jOptLong = jSONObject.optLong("stoppedElapsed");
            }
            this.i = jOptLong;
            this.m = this.j == 0;
            N = c();
        }
    }

    public final Float n() {
        Float fV;
        r4 r4VarG = g();
        if (r4VarG != null && (fV = r4VarG.v(1050880)) != null) {
            float fFloatValue = fV.floatValue();
            if (0.0f <= fFloatValue && fFloatValue <= 10000.0f) {
                return fV;
            }
        }
        return null;
    }
}
