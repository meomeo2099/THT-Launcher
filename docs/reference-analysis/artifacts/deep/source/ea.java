package defpackage;

import android.content.Context;
import java.io.File;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public abstract class ea {
    public static final ca a = pi.b;
    public static boolean b;

    public static File a(Context context, boolean z) {
        return new File(context.getFilesDir(), "wallpaper_" + (z ? "night" : "day") + ".png");
    }
}
