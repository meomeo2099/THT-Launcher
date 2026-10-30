package vn.tht.fixtureb;
import android.app.Activity; import android.os.Bundle; import android.view.Gravity; import android.widget.TextView;
public class MainActivity extends Activity {
 public void onCreate(Bundle b) { super.onCreate(b); getWindow().getDecorView().setSystemUiVisibility(5894);
  TextView v=new TextView(this) { protected void onSizeChanged(int w,int h,int ow,int oh) { super.onSizeChanged(w,h,ow,oh);setText("PANE B — "+w+" × "+h); } };
  v.setBackgroundColor(0xff27674b);v.setTextColor(-1);v.setTextSize(22);v.setGravity(Gravity.CENTER);setContentView(v);
 }
}
