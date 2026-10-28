package defpackage;

import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.PlaybackState;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class fc extends MediaController.Callback {
    public final /* synthetic */ gc a;

    public fc(gc gcVar) {
        this.a = gcVar;
    }

    @Override // android.media.session.MediaController.Callback
    public final void onMetadataChanged(MediaMetadata mediaMetadata) {
        gc gcVar = this.a;
        if (gcVar.l) {
            gc.b(gcVar);
        }
    }

    @Override // android.media.session.MediaController.Callback
    public final void onPlaybackStateChanged(PlaybackState playbackState) {
        gc gcVar = this.a;
        if (gcVar.l) {
            gc.b(gcVar);
        }
    }

    @Override // android.media.session.MediaController.Callback
    public final void onSessionDestroyed() {
        gc gcVar = this.a;
        if (gcVar.l) {
            gcVar.t();
        }
    }
}
