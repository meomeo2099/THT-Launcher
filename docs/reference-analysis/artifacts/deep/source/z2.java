package defpackage;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class z2 {
    public static final List a = j3.i0(268894465, 268894467, 268894466, 268894470, 268894468);
    public static final Set b = x0.Y0(new String[]{"knewstar_001", "geely_tugella", "belgee_x70"});

    public static Integer a(Integer num) {
        int iIntValue;
        if (num == null || 268894465 > (iIntValue = num.intValue()) || iIntValue >= 268894473) {
            return null;
        }
        return num;
    }

    public static String b(String str) {
        List list = si.a;
        return si.a(k8.e(str, "geely_tugella") ? "electric_front_short" : "windshield_heating_wiper_zone", new Object[0]);
    }

    public static Integer c(Integer num) {
        if (num == null) {
            return null;
        }
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return 0;
        }
        if (268566785 <= iIntValue && iIntValue < 268566794) {
            return Integer.valueOf(iIntValue - 268566784);
        }
        if (iIntValue == 268566794) {
            return 10;
        }
        if (1 > iIntValue || iIntValue >= 10) {
            return null;
        }
        return num;
    }

    public static String d(float f) {
        float fH = h(f);
        int iRound = Math.round(fH);
        if (fH != iRound) {
            List list = si.a;
            return String.format(si.b.b, "%.1f", Arrays.copyOf(new Object[]{Float.valueOf(fH)}, 1)).concat("°");
        }
        return iRound + "°";
    }

    public static LinkedHashMap e() {
        return hb.h0(new yc(268501248, si.a("climate", new Object[0])), new yc(268501760, "A/C"), new yc(268502016, "A/C MAX"), new yc(268501504, "AUTO"), new yc(268566784, si.a("fan", new Object[0])), new yc(268632320, si.a("recirculation", new Object[0])), new yc(268894464, si.a("airflow_direction", new Object[0])), new yc(268697856, si.a("windshield_defogging", new Object[0])), new yc(268698368, si.a("rear_window_heating", new Object[0])), new yc(269747456, si.a("windshield_heating", new Object[0])), new yc(269747200, si.a("nozzle_heating", new Object[0])), new yc(269025536, si.a("steering_wheel_heating", new Object[0])), new yc(268829952, "SYNC"));
    }

    public static boolean f(String str) {
        return i3.r0(b, str);
    }

    public static String g(int i, int i2) {
        if (i2 == 0) {
            return si.a("off_65", new Object[0]);
        }
        if (i == 269025536 && 269025537 <= i2 && i2 < 269025540) {
            List list = si.a;
            return si.a("level_0", Integer.valueOf(i2 - 269025536));
        }
        if (i == 269025536 && i2 == 269025551) {
            return "AUTO";
        }
        if (i == 268566784 && 268566785 <= i2 && i2 < 268566794) {
            List list2 = si.a;
            return si.a("speed_0", Integer.valueOf(i2 - 268566784));
        }
        if (i == 268566784 && i2 == 268566794) {
            return "AUTO";
        }
        if (i == 268894464 && i2 == 268894465) {
            return si.a("face", new Object[0]);
        }
        if (i == 268894464 && i2 == 268894466) {
            return si.a("feet", new Object[0]);
        }
        if (i == 268894464 && i2 == 268894467) {
            return si.a("face_and_feet", new Object[0]);
        }
        if (i == 268894464 && i2 == 268894468) {
            return si.a("windshield", new Object[0]);
        }
        if (i == 268894464 && i2 == 268894469) {
            return si.a("face_and_windshield", new Object[0]);
        }
        if (i == 268894464 && i2 == 268894470) {
            return si.a("feet_and_windshield", new Object[0]);
        }
        if (i == 268894464 && i2 == 268894471) {
            return si.a("all_directions", new Object[0]);
        }
        if (i == 268894464 && i2 == 268894472) {
            return "AUTO";
        }
        if (i == 268632320 && i2 == 268632321) {
            return si.a("recirculated_air", new Object[0]);
        }
        if (i == 268632320 && i2 == 268632322) {
            return si.a("fresh_air", new Object[0]);
        }
        if (i == 268632320 && i2 == 268632323) {
            return "AUTO";
        }
        if (i2 == 1) {
            return si.a("on", new Object[0]);
        }
        List list3 = si.a;
        return si.a("mode_0", Integer.valueOf(i2));
    }

    public static float h(float f) {
        return pi.r(Math.round(f / 0.5f) * 0.5f, 17.0f, 32.0f);
    }

    public static Integer i(Integer num) {
        if (num == null) {
            return null;
        }
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return 0;
        }
        if (1 <= iIntValue && iIntValue < 4) {
            return num;
        }
        if (269025537 > iIntValue || iIntValue >= 269025540) {
            return iIntValue == 269025551 ? 1 : null;
        }
        return Integer.valueOf(iIntValue - 269025536);
    }

    public static Float j(Float f) {
        if (f == null) {
            return null;
        }
        float fFloatValue = f.floatValue();
        if (Math.abs(fFloatValue) > Float.MAX_VALUE || fFloatValue == 0.0f || fFloatValue == Float.MIN_VALUE || 15.0f > fFloatValue || fFloatValue > 33.0f) {
            return null;
        }
        return f;
    }
}
