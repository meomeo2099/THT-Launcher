package defpackage;

import android.graphics.Rect;
import android.widget.SeekBar;
import org.reference.panel.BootPlaybackService;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class i2 implements y5 {
    public final /* synthetic */ int a;
    public final /* synthetic */ int b;
    public final /* synthetic */ Object c;

    public /* synthetic */ i2(int i, BootPlaybackService bootPlaybackService) {
        this.a = 0;
        this.b = i;
        this.c = bootPlaybackService;
    }

    @Override // defpackage.y5
    public final Object a() {
        int i = this.a;
        ti tiVar = ti.a;
        int i2 = this.b;
        Object obj = this.c;
        switch (i) {
            case 0:
                BootPlaybackService bootPlaybackService = (BootPlaybackService) obj;
                int i3 = 1;
                if (i2 <= 1) {
                    int i4 = BootPlaybackService.g;
                    bootPlaybackService.stopSelf();
                } else {
                    bootPlaybackService.a.postDelayed(new t0(i2, bootPlaybackService, i3), 3000L);
                }
                break;
            case 1:
                ((SeekBar) obj).setProgress(i2 - 75);
                break;
            default:
                eh.g((Rect) obj, i2);
                break;
        }
        return tiVar;
    }

    public /* synthetic */ i2(Object obj, int i, int i2) {
        this.a = i2;
        this.c = obj;
        this.b = i;
    }
}
