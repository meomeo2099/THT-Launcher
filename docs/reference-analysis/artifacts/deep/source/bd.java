package defpackage;

import android.widget.SeekBar;
import android.widget.TextView;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class bd implements SeekBar.OnSeekBarChangeListener {
    public final /* synthetic */ TextView a;
    public final /* synthetic */ u2 b;
    public final /* synthetic */ SeekBar c;

    public bd(TextView textView, u2 u2Var, SeekBar seekBar) {
        this.a = textView;
        this.b = u2Var;
        this.c = seekBar;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        this.a.setText(i + " %");
        ((j6) this.b.a).b(Integer.valueOf(i));
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onStopTrackingTouch(SeekBar seekBar) {
        ((j6) this.b.b).b(Integer.valueOf(this.c.getProgress()));
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onStartTrackingTouch(SeekBar seekBar) {
    }
}
