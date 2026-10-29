package vn.tht.fixture;
import android.app.Activity; import android.os.Bundle; import android.widget.TextView; import android.view.Gravity; import android.view.View;
public class MainActivity extends Activity {
 public void onCreate(Bundle b) { super.onCreate(b);
  getWindow().getDecorView().setSystemUiVisibility(5894);
  TextView v=new TextView(this);v.setBackgroundColor(0xff174b69);v.setTextColor(-1);v.setTextSize(24);v.setGravity(Gravity.CENTER);
  v.setText("PREVIEW FIXTURE — display "+getWindowManager().getDefaultDisplay().getDisplayId());setContentView(v);
 }
}