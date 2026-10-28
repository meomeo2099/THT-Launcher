package defpackage;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class t1 implements j6 {
    public final /* synthetic */ int a;
    public final /* synthetic */ z0 b;
    public final /* synthetic */ y5 c;

    public /* synthetic */ t1(z0 z0Var, y5 y5Var, int i) {
        this.a = i;
        this.b = z0Var;
        this.c = y5Var;
    }

    @Override // defpackage.j6
    public final Object b(Object obj) {
        int i = this.a;
        ti tiVar = ti.a;
        y5 y5Var = this.c;
        z0 z0Var = this.b;
        switch (i) {
            case 0:
                z0Var.d("defrost", ((Boolean) obj).booleanValue());
                y5Var.a();
                break;
            case 1:
                z0Var.d("heat_cold", ((Boolean) obj).booleanValue());
                y5Var.a();
                break;
            case 2:
                z0Var.d("vent_left", ((Boolean) obj).booleanValue());
                y5Var.a();
                break;
            case 3:
                z0Var.d("vent_right", ((Boolean) obj).booleanValue());
                y5Var.a();
                break;
            case 4:
                z0Var.a.edit().putInt("auto_condition", ((Integer) obj).intValue()).apply();
                y5Var.a();
                break;
            case 5:
                z0Var.d("airing", ((Boolean) obj).booleanValue());
                y5Var.a();
                break;
            case 6:
                z0Var.c(((Integer) obj).intValue(), "airing_mode");
                y5Var.a();
                break;
            case 7:
                z0Var.d("climate", ((Boolean) obj).booleanValue());
                y5Var.a();
                break;
            case 8:
                z0Var.d("close", ((Boolean) obj).booleanValue());
                y5Var.a();
                break;
            case 9:
                z0Var.d("apps", ((Boolean) obj).booleanValue());
                y5Var.a();
                break;
            case 10:
                z0Var.d("apps_home", ((Boolean) obj).booleanValue());
                y5Var.a();
                break;
            case 11:
                z0Var.d("seat_left", ((Boolean) obj).booleanValue());
                y5Var.a();
                break;
            case 12:
                z0Var.d("seat_right", ((Boolean) obj).booleanValue());
                y5Var.a();
                break;
            case 13:
                z0Var.d("wheel", ((Boolean) obj).booleanValue());
                y5Var.a();
                break;
            case 14:
                z0Var.c(((Integer) obj).intValue(), "airing_temp");
                y5Var.a();
                break;
            case 15:
                z0Var.c(((Integer) obj).intValue(), "vent_minutes");
                y5Var.a();
                break;
            case 16:
                z0Var.c(((Integer) obj).intValue(), "airing_minutes");
                y5Var.a();
                break;
            case 17:
                z0Var.c(((Integer) obj).intValue(), "close_kmh");
                y5Var.a();
                break;
            case 18:
                z0Var.c(((Integer) obj).intValue(), "airing_fan");
                y5Var.a();
                break;
            case 19:
                z0Var.c(((Integer) obj).intValue(), "vent_temp");
                y5Var.a();
                break;
            case 20:
                z0Var.c(((Integer) obj).intValue(), "heat_minutes");
                y5Var.a();
                break;
            case 21:
                z0Var.c(((Integer) obj).intValue(), "heat_cold_temp");
                y5Var.a();
                break;
            case 22:
                z0Var.c(((Integer) obj).intValue(), "climate_temp_half");
                y5Var.a();
                break;
            case 23:
                z0Var.a.edit().putInt("auto_idle_minutes", pi.s(((Integer) obj).intValue(), 5, 720)).apply();
                y5Var.a();
                break;
            case 24:
                z0Var.d("rear_glass", ((Boolean) obj).booleanValue());
                y5Var.a();
                break;
            case 25:
                z0Var.d("front_glass", ((Boolean) obj).booleanValue());
                y5Var.a();
                break;
            default:
                z0Var.d("nozzle", ((Boolean) obj).booleanValue());
                y5Var.a();
                break;
        }
        return tiVar;
    }
}
