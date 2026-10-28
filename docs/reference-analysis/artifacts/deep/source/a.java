package defpackage;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Parcel;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a implements j6, n6 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ a(LinkedHashMap linkedHashMap) {
        this.a = 1;
        List list = z2.a;
        this.b = linkedHashMap;
    }

    /* JADX WARN: Code duplicated, block: B:127:0x02e9  */
    @Override // defpackage.j6
    public Object b(Object obj) {
        String strA;
        String strA2;
        String string;
        String string2;
        Display defaultDisplay;
        int i = this.a;
        num = null;
        num = null;
        num = null;
        num = null;
        Integer num = null;
        ti tiVar = ti.a;
        Object obj2 = this.b;
        switch (i) {
            case 0:
                return obj == ((g) obj2) ? "(this Collection)" : String.valueOf(obj);
            case 1:
                List list = z2.a;
                Map.Entry entry = (Map.Entry) obj;
                entry.getClass();
                Object key = entry.getKey();
                key.getClass();
                Integer num2 = (Integer) key;
                Object value = entry.getValue();
                value.getClass();
                String str = (String) value;
                Integer num3 = (Integer) ((LinkedHashMap) obj2).get(num2);
                if (num3 != null) {
                    strA = z2.g(num2.intValue(), num3.intValue());
                    if (strA == null) {
                        strA = si.a("no_data", new Object[0]);
                    }
                } else {
                    strA = si.a("no_data", new Object[0]);
                }
                return c.d(str, "=", strA);
            case 2:
            case 13:
            default:
                me meVar = (me) obj2;
                r4 r4Var = (r4) obj;
                r4Var.getClass();
                Integer numJ = r4Var.j(537069056);
                String strC = r4Var.c();
                List listD = r4Var.d();
                if (strC.equals("active") && listD.contains(537069058) && listD.contains(537069059)) {
                    if (numJ != null && numJ.intValue() == 537069058) {
                        num = 537069059;
                    } else if (numJ != null && numJ.intValue() == 537069059) {
                        num = 537069058;
                    }
                }
                if (num == null) {
                    throw new IllegalStateException(si.a("cruise_unavailable", new Object[0]));
                }
                meVar.b = num;
                return Boolean.valueOf(r4Var.w(537069056, num.intValue()));
            case 3:
                int iIntValue = ((Integer) obj).intValue();
                ((q8) obj2).d.b("fan:" + iIntValue);
                return tiVar;
            case 4:
                AudioManager audioManager = (AudioManager) obj2;
                float fFloatValue = ((Float) obj).floatValue();
                int i2 = MainActivity.E0;
                try {
                    audioManager.setStreamVolume(3, Math.round(fFloatValue), 0);
                    break;
                } catch (Throwable unused) {
                }
                return tiVar;
            case 5:
                ComponentName componentName = (ComponentName) obj;
                componentName.getClass();
                ((j6) obj2).b("app:" + componentName.flattenToString());
                return tiVar;
            case 6:
                gc gcVar = (gc) obj2;
                MediaController mediaController = (MediaController) obj;
                mediaController.getClass();
                PlaybackState playbackState = mediaController.getPlaybackState();
                Integer numValueOf = playbackState != null ? Integer.valueOf(playbackState.getState()) : null;
                if (numValueOf == null) {
                    strA2 = si.a("no_state", new Object[0]);
                } else if (numValueOf.intValue() == 3) {
                    strA2 = si.a("playing", new Object[0]);
                } else if (numValueOf.intValue() == 2) {
                    strA2 = si.a("paused", new Object[0]);
                } else if (numValueOf.intValue() == 1) {
                    strA2 = si.a("stopped", new Object[0]);
                } else if (numValueOf.intValue() == 0) {
                    strA2 = "NONE";
                } else if (numValueOf.intValue() == 6) {
                    strA2 = si.a("buffering", new Object[0]);
                } else if (numValueOf.intValue() == 8) {
                    strA2 = si.a("connecting", new Object[0]);
                } else {
                    strA2 = numValueOf.intValue() == 7 ? si.a("error", new Object[0]) : si.a("state_0", numValueOf);
                }
                MediaMetadata metadata = mediaController.getMetadata();
                if (metadata == null || (string = metadata.getString("android.media.metadata.TITLE")) == null) {
                    MediaMetadata metadata2 = mediaController.getMetadata();
                    string = metadata2 != null ? metadata2.getString("android.media.metadata.DISPLAY_TITLE") : null;
                    if (string == null) {
                        string = "—";
                    }
                }
                MediaSession.Token sessionToken = mediaController.getSessionToken();
                MediaController mediaController2 = gcVar.i;
                return (k8.e(sessionToken, mediaController2 != null ? mediaController2.getSessionToken() : null) ? "▶ " : "") + mediaController.getPackageName() + "\n" + strA2 + " · " + string + (gcVar.y(mediaController) ? si.a("playing_593", new Object[0]) : "");
            case 7:
                MediaMetadata mediaMetadata = (MediaMetadata) obj2;
                String str2 = (String) obj;
                str2.getClass();
                if (mediaMetadata == null || (string2 = mediaMetadata.getString(str2)) == null || th.A(string2)) {
                    return null;
                }
                return string2;
            case 8:
                yc ycVar = (yc) obj;
                ycVar.getClass();
                return ycVar.a + "=" + ((PackageManager) obj2).hasSystemFeature((String) ycVar.b);
            case 9:
                String str3 = (String) obj;
                str3.getClass();
                return th.E(str3, '.') + "=" + (((Activity) obj2).checkSelfPermission(str3) == 0);
            case 10:
                Integer num4 = (Integer) obj;
                num4.getClass();
                ((TextView) obj2).setText(si.a("radio_scanning_stop_0", num4));
                return tiVar;
            case 11:
                Parcel parcel = (Parcel) obj;
                parcel.getClass();
                parcel.writeStrongBinder(((fe) obj2).l);
                return tiVar;
            case 12:
                Context context = (Context) obj2;
                int iIntValue2 = ((Integer) obj).intValue();
                ch chVarK = eh.k(true);
                if (chVarK != null) {
                    Point point = new Point();
                    WindowManager windowManager = (WindowManager) context.getSystemService(WindowManager.class);
                    if (windowManager != null && (defaultDisplay = windowManager.getDefaultDisplay()) != null) {
                        defaultDisplay.getRealSize(point);
                    }
                    Rect rect = chVarK.b;
                    eh.g(new Rect(rect.left, rect.top, point.x, rect.bottom), iIntValue2);
                    rg.g = null;
                    String str4 = eh.d;
                    if (str4 != null) {
                        int i3 = rg.d;
                        float f = (iIntValue2 - i3) * 100.0f;
                        int i4 = point.x - i3;
                        int iO0 = k8.o0(f / (i4 >= 1 ? i4 : 1));
                        SharedPreferences sharedPreferences = context.getSharedPreferences("panel", 0);
                        sharedPreferences.getClass();
                        eh.i(sharedPreferences, str4, iO0);
                    }
                }
                return tiVar;
            case 14:
                b2 b2Var = (b2) obj2;
                if (((String) obj) != null) {
                    b2Var.a();
                }
                return tiVar;
        }
    }

    @Override // defpackage.n6
    public Object c(Object obj, Object obj2) throws hi {
        int iIndexOf;
        int i = this.a;
        Object obj3 = this.b;
        switch (i) {
            case 2:
                File file = (File) obj;
                IOException iOException = (IOException) obj2;
                file.getClass();
                iOException.getClass();
                if (((n6) obj3).c(file, iOException) != wc.a) {
                    return ti.a;
                }
                throw new hi(file, null, null);
            default:
                char[] cArr = (char[]) obj3;
                CharSequence charSequence = (CharSequence) obj;
                int iIntValue = ((Integer) obj2).intValue();
                charSequence.getClass();
                charSequence.getClass();
                if (cArr.length == 1 && (charSequence instanceof String)) {
                    iIndexOf = ((String) charSequence).indexOf(x0.W0(cArr), iIntValue);
                } else {
                    if (iIntValue < 0) {
                        iIntValue = 0;
                    }
                    int length = charSequence.length() - 1;
                    if (iIntValue <= length) {
                        while (true) {
                            char cCharAt = charSequence.charAt(iIntValue);
                            for (char c : cArr) {
                                if (pi.x(c, cCharAt, false)) {
                                    iIndexOf = iIntValue;
                                }
                            }
                            if (iIntValue != length) {
                                iIntValue++;
                            } else {
                                iIndexOf = -1;
                            }
                        }
                    } else {
                        iIndexOf = -1;
                    }
                }
                if (iIndexOf < 0) {
                    return null;
                }
                return new yc(Integer.valueOf(iIndexOf), 1);
        }
    }

    public /* synthetic */ a(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    public /* synthetic */ a(MainActivity mainActivity, AudioManager audioManager) {
        this.a = 4;
        this.b = audioManager;
    }
}
