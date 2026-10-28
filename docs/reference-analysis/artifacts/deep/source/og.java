package defpackage;

import android.view.KeyEvent;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.List;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class og implements SeekBar.OnSeekBarChangeListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ KeyEvent.Callback b;

    public /* synthetic */ og(KeyEvent.Callback callback, int i) {
        this.a = i;
        this.b = callback;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        int i2 = this.a;
        KeyEvent.Callback callback = this.b;
        switch (i2) {
            case 0:
                List list = si.a;
                ((TextView) callback).setText(si.a("scale_0", Integer.valueOf(i + 75)));
                break;
            default:
                MainActivity mainActivity = (MainActivity) callback;
                if (z) {
                    mainActivity.B().edit().putInt("image_scale_".concat(mainActivity.E().a), i).apply();
                    mainActivity.w0();
                }
                break;
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onStartTrackingTouch(SeekBar seekBar) {
        int i = this.a;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onStopTrackingTouch(SeekBar seekBar) {
        int i = this.a;
    }

    private final void a(SeekBar seekBar) {
    }

    private final void b(SeekBar seekBar) {
    }

    private final void c(SeekBar seekBar) {
    }

    private final void d(SeekBar seekBar) {
    }
}
