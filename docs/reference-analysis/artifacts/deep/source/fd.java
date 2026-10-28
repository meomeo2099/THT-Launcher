package defpackage;

import android.content.Context;
import android.media.browse.MediaBrowser;
import android.media.session.MediaController;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class fd extends MediaBrowser.ConnectionCallback {
    public final /* synthetic */ int a;
    public final /* synthetic */ hd b;
    public final /* synthetic */ me c;
    public final /* synthetic */ le d;
    public final /* synthetic */ List e;

    public fd(int i, hd hdVar, me meVar, le leVar, List list) {
        this.a = i;
        this.b = hdVar;
        this.c = meVar;
        this.d = leVar;
        this.e = list;
    }

    @Override // android.media.browse.MediaBrowser.ConnectionCallback
    public final void onConnected() {
        Object ffVar;
        hd hdVar = this.b;
        if (this.a != hdVar.e) {
            return;
        }
        MediaBrowser mediaBrowser = hdVar.c;
        me meVar = this.c;
        Object obj = meVar.b;
        if (obj == null) {
            k8.H0("candidate");
            throw null;
        }
        if (mediaBrowser != ((MediaBrowser) obj)) {
            return;
        }
        try {
            Context context = hdVar.a;
            Object obj2 = meVar.b;
            if (obj2 == null) {
                k8.H0("candidate");
                throw null;
            }
            ffVar = new MediaController(context, ((MediaBrowser) obj2).getSessionToken());
            hdVar.d = (MediaController) (ffVar instanceof ff ? null : ffVar);
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
    }

    @Override // android.media.browse.MediaBrowser.ConnectionCallback
    public final void onConnectionFailed() {
        hd hdVar = this.b;
        int i = hdVar.e;
        int i2 = this.a;
        if (i2 == i) {
            MediaBrowser mediaBrowser = hdVar.c;
            Object obj = this.c.b;
            if (obj == null) {
                k8.H0("candidate");
                throw null;
            }
            if (mediaBrowser == ((MediaBrowser) obj)) {
                hd.b(i2, hdVar, this.d, this.e);
            }
        }
    }

    @Override // android.media.browse.MediaBrowser.ConnectionCallback
    public final void onConnectionSuspended() {
        hd hdVar = this.b;
        int i = hdVar.e;
        int i2 = this.a;
        if (i2 == i) {
            MediaBrowser mediaBrowser = hdVar.c;
            Object obj = this.c.b;
            if (obj == null) {
                k8.H0("candidate");
                throw null;
            }
            if (mediaBrowser == ((MediaBrowser) obj)) {
                hd.b(i2, hdVar, this.d, this.e);
            }
        }
    }
}
