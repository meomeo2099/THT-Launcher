package defpackage;

import android.content.Intent;
import android.media.session.MediaSession;
import android.view.KeyEvent;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class od extends MediaSession.Callback {
    @Override // android.media.session.MediaSession.Callback
    public final boolean onMediaButtonEvent(Intent intent) {
        intent.getClass();
        KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
        if (keyEvent == null) {
            return false;
        }
        if (keyEvent.getAction() != 0 || keyEvent.getRepeatCount() != 0) {
            return true;
        }
        fi fiVar = pd.a;
        pd.a(keyEvent.getKeyCode());
        return true;
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onPause() {
        fi fiVar = pd.a;
        pd.a(127);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onPlay() {
        fi fiVar = pd.a;
        pd.a(126);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onSkipToNext() {
        fi fiVar = pd.a;
        pd.a(87);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onSkipToPrevious() {
        fi fiVar = pd.a;
        pd.a(88);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onStop() {
        fi fiVar = pd.a;
        pd.a(86);
    }
}
