package defpackage;

import android.widget.LinearLayout;
import android.widget.TextView;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class df extends LinearLayout {
    public static final TextView a(MainActivity mainActivity, String str, float f, int i) {
        TextView textView = new TextView(mainActivity);
        textView.setText(str);
        textView.setTextSize(f);
        textView.setTextColor(i);
        textView.setGravity(17);
        return textView;
    }
}
