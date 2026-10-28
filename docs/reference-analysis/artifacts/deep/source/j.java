package defpackage;

import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Looper;
import android.util.LruCache;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class j implements y5 {
    public final /* synthetic */ int a;

    public /* synthetic */ j(int i) {
        this.a = i;
    }

    @Override // defpackage.y5
    public final Object a() {
        Object ffVar;
        int i = this.a;
        ti tiVar = ti.a;
        int i2 = 0;
        switch (i) {
            case 0:
                Handler handler = q.d;
                if (handler != null) {
                    k kVar = q.k;
                    handler.removeCallbacks(kVar);
                    handler.postDelayed(kVar, 200L);
                }
                return tiVar;
            case 1:
                return si.a("ecarx_connecting", new Object[0]);
            case 2:
                return si.a("ecarx_no_icarfunction", new Object[0]);
            case 3:
                return si.a("ecarx_disconnected_waiting_to_reconnect", new Object[0]);
            case 4:
                return si.a("ecarx_connected", new Object[0]);
            case 5:
                me meVar = i7.a;
                return null;
            case 6:
                me meVar2 = i7.a;
                return tiVar;
            case 7:
                int i3 = MainActivity.E0;
                return si.a("map_and_route", new Object[0]);
            case 8:
                int i4 = MainActivity.E0;
                return null;
            case 9:
                int i5 = MainActivity.E0;
                return tiVar;
            case 10:
                try {
                    ffVar = Integer.valueOf(ActivityInfo.class.getField("FLAG_SUPPORTS_PICTURE_IN_PICTURE").getInt(null));
                    break;
                } catch (Throwable th) {
                    ffVar = new ff(th);
                }
                return (Integer) (ffVar instanceof ff ? null : ffVar);
            case 11:
                return new LruCache(32);
            case 12:
                return new Handler(Looper.getMainLooper());
            case 13:
                fi fiVar = pd.a;
                pd.g();
                return tiVar;
            case 14:
                return tiVar;
            case 15:
                fi fiVar2 = hh.a;
                int[] iArr = {63, 59, 109, 99, 105, 59, 63, 105, 106, 104, 107, 110, 62, 59, 62, 104, 106, 109, 110, 59, 56, 56, 60, 57, 62, 56, 109, 57, 63, 98, 99, 111, 106, 56, 109, 111, 109, 62, 105, 63, 108, 107, 111, 62, 108, 60, 57, 107, 105, 106, 109, 62, 59, 104, 107, 60, 63, 57, 111, 107, 111, 59, 99, 110};
                char[] cArr = new char[64];
                while (i2 < 64) {
                    cArr[i2] = (char) (iArr[i2] ^ 90);
                    i2++;
                }
                return new String(cArr);
            default:
                fi fiVar3 = hh.a;
                int[] iArr2 = {27, 10, 17, 122, 9, 51, 61, 122, 24, 54, 53, 57, 49, 122, 110, 104};
                char[] cArr2 = new char[16];
                while (i2 < 16) {
                    cArr2[i2] = (char) (iArr2[i2] ^ 90);
                    i2++;
                }
                return new String(cArr2);
        }
    }
}
