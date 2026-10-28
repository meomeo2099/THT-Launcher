package defpackage;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.AtomicFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import org.reference.panel.MainActivity;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class n7 implements y5 {
    public final /* synthetic */ int a;
    public final /* synthetic */ MainActivity b;
    public final /* synthetic */ String c;
    public final /* synthetic */ me d;
    public final /* synthetic */ Object e;

    public /* synthetic */ n7(me meVar, MainActivity mainActivity, String str, j6 j6Var) {
        this.a = 3;
        this.d = meVar;
        this.b = mainActivity;
        this.c = str;
        this.e = j6Var;
    }

    /* JADX WARN: Code duplicated, block: B:54:0x00ee  */
    @Override // defpackage.y5
    public final Object a() {
        boolean z;
        Object ffVar;
        Object ffVar2;
        int i = this.a;
        String str = this.c;
        MainActivity mainActivity = this.b;
        Object obj = ti.a;
        Object obj2 = this.e;
        me meVar = this.d;
        switch (i) {
            case 0:
                Bitmap bitmap = (Bitmap) obj2;
                if (bitmap != null) {
                    me meVar2 = k7.a;
                    mainActivity.getClass();
                    str.getClass();
                    try {
                        AtomicFile atomicFile = new AtomicFile(v7.c(mainActivity, str));
                        FileOutputStream fileOutputStreamStartWrite = atomicFile.startWrite();
                        try {
                            fileOutputStreamStartWrite.getClass();
                            if (!bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStreamStartWrite)) {
                                throw new IllegalStateException("Check failed.");
                            }
                            atomicFile.finishWrite(fileOutputStreamStartWrite);
                            ffVar = obj;
                            z = ffVar instanceof ff ? false : true;
                        } catch (Exception e) {
                            atomicFile.failWrite(fileOutputStreamStartWrite);
                            throw e;
                        }
                    } catch (Throwable th) {
                        ffVar = new ff(th);
                    }
                }
                pi.G(meVar, mainActivity, str, z);
                return obj;
            case 1:
                File file = (File) obj2;
                me meVar3 = k7.a;
                mainActivity.getClass();
                str.getClass();
                file.getClass();
                try {
                    AtomicFile atomicFile2 = new AtomicFile(v7.c(mainActivity, str));
                    FileOutputStream fileOutputStreamStartWrite2 = atomicFile2.startWrite();
                    try {
                        fileOutputStreamStartWrite2.getClass();
                        FileInputStream fileInputStream = new FileInputStream(file);
                        try {
                            pi.u(fileInputStream, fileOutputStreamStartWrite2);
                            fileInputStream.close();
                            atomicFile2.finishWrite(fileOutputStreamStartWrite2);
                            ffVar2 = obj;
                            pi.G(meVar, mainActivity, str, !(ffVar2 instanceof ff));
                            return obj;
                        } catch (Throwable th2) {
                            try {
                                throw th2;
                            } catch (Throwable th3) {
                                k8.p(fileInputStream, th2);
                                throw th3;
                            }
                        }
                    } catch (Exception e2) {
                        atomicFile2.failWrite(fileOutputStreamStartWrite2);
                        throw e2;
                    }
                } catch (Throwable th4) {
                    ffVar2 = new ff(th4);
                }
                break;
            case 2:
                HashMap map = v7.a;
                SharedPreferences sharedPreferencesB = mainActivity.B();
                sharedPreferencesB.getClass();
                f8.c(mainActivity, str, r7.a(v7.d(sharedPreferencesB, str), null, (s7) obj2, 1));
                Object obj3 = meVar.b;
                if (obj3 != null) {
                    ((y5) obj3).a();
                    return obj;
                }
                k8.H0("fill");
                throw null;
            default:
                j6 j6Var = (j6) obj2;
                Object obj4 = meVar.b;
                if (obj4 == null) {
                    k8.H0("dialog");
                    throw null;
                }
                ((Dialog) obj4).dismiss();
                MainActivity mainActivity2 = this.b;
                new i0(mainActivity2, mainActivity2.z(), this.c, false, new a(5, j6Var)).show();
                return obj;
        }
    }

    public /* synthetic */ n7(Bitmap bitmap, MainActivity mainActivity, String str, me meVar) {
        this.a = 0;
        this.e = bitmap;
        this.b = mainActivity;
        this.c = str;
        this.d = meVar;
    }

    public /* synthetic */ n7(MainActivity mainActivity, String str, Object obj, me meVar, int i) {
        this.a = i;
        this.b = mainActivity;
        this.c = str;
        this.e = obj;
        this.d = meVar;
    }
}
