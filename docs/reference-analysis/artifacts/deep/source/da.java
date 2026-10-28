package defpackage;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class da implements FileFilter {
    @Override // java.io.FileFilter
    public final boolean accept(File file) {
        String name = file.getName();
        name.getClass();
        Pattern patternCompile = Pattern.compile("wallpaper_(day|night)_[a-z0-9_]+\\.png");
        patternCompile.getClass();
        return patternCompile.matcher(name).matches();
    }
}
