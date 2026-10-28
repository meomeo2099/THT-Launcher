package org.reference.panel;

import android.app.Notification;
import android.app.PendingIntent;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import defpackage.ac;
import defpackage.ff;
import defpackage.hc;
import defpackage.k8;
import defpackage.th;
import defpackage.u4;
import defpackage.wb;
import defpackage.xb;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-df02db20992efcdad8c8628fa158af402193d9214b2257c75897211101eb46a6 */
/* JADX INFO: loaded from: classes.dex */
public final class MusicNotificationListener extends NotificationListenerService {
    public static final Object a = new Object();
    public static final LinkedHashMap b = new LinkedHashMap();
    public static ac c;
    public static ac d;

    public static hc a(StatusBarNotification statusBarNotification) {
        Object ffVar;
        CharSequence charSequence;
        CharSequence charSequence2;
        PendingIntent pendingIntent;
        String string;
        String string2;
        try {
            Notification notification = statusBarNotification.getNotification();
            if (notification != null) {
                Bundle bundle = notification.extras;
                MediaSession.Token token = bundle != null ? (MediaSession.Token) bundle.getParcelable("android.mediaSession") : null;
                boolean z = true;
                boolean z2 = (bundle == null || (string2 = bundle.getString("android.template")) == null || !th.v(string2, "MediaStyle", false)) ? false : true;
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                Notification.Action[] actionArr = notification.actions;
                if (actionArr == null) {
                    actionArr = new Notification.Action[0];
                }
                for (Notification.Action action : actionArr) {
                    Map map = wb.a;
                    CharSequence charSequence3 = action.title;
                    xb xbVar = (charSequence3 == null || (string = charSequence3.toString()) == null) ? null : (xb) wb.a.get(wb.a(string));
                    if (xbVar != null && (pendingIntent = action.actionIntent) != null && k8.e(pendingIntent.getCreatorPackage(), statusBarNotification.getPackageName()) && !pendingIntent.isActivity()) {
                        linkedHashMap.putIfAbsent(xbVar, pendingIntent);
                    }
                }
                Map map2 = wb.a;
                if (token == null) {
                    z = false;
                }
                boolean zE = k8.e(notification.category, "transport");
                Set setKeySet = linkedHashMap.keySet();
                setKeySet.getClass();
                if ((z || z2 || (zE && !setKeySet.isEmpty())) && (notification.flags & 512) == 0) {
                    String key = statusBarNotification.getKey();
                    key.getClass();
                    String packageName = statusBarNotification.getPackageName();
                    packageName.getClass();
                    long postTime = statusBarNotification.getPostTime();
                    String string3 = (bundle == null || (charSequence2 = bundle.getCharSequence("android.title")) == null) ? null : charSequence2.toString();
                    String str = "";
                    if (string3 == null) {
                        string3 = "";
                    }
                    String string4 = (bundle == null || (charSequence = bundle.getCharSequence("android.text")) == null) ? null : charSequence.toString();
                    if (string4 != null) {
                        str = string4;
                    }
                    ffVar = new hc(key, packageName, postTime, string3, str, notification.getLargeIcon(), token, linkedHashMap);
                    return (hc) (ffVar instanceof ff ? null : ffVar);
                }
            }
            return null;
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
    }

    @Override // android.service.notification.NotificationListenerService, android.app.Service
    public final void onDestroy() {
        synchronized (a) {
            b.clear();
        }
        ac acVar = c;
        if (acVar != null) {
            acVar.a();
        }
        ac acVar2 = d;
        if (acVar2 != null) {
            acVar2.a();
        }
        super.onDestroy();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [ff] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r1v9 */
    @Override // android.service.notification.NotificationListenerService
    public final void onListenerConnected() {
        ?? ffVar;
        super.onListenerConnected();
        try {
            StatusBarNotification[] activeNotifications = getActiveNotifications();
            if (activeNotifications == null) {
                activeNotifications = new StatusBarNotification[0];
            }
            ffVar = new ArrayList();
            for (StatusBarNotification statusBarNotification : activeNotifications) {
                statusBarNotification.getClass();
                hc hcVarA = a(statusBarNotification);
                if (hcVarA != null) {
                    ffVar.add(hcVarA);
                }
            }
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        u4 u4Var = u4.a;
        boolean z = ffVar instanceof ff;
        ?? r1 = ffVar;
        if (z) {
            r1 = u4Var;
        }
        List<hc> list = (List) r1;
        synchronized (a) {
            b.clear();
            for (hc hcVar : list) {
                b.put(hcVar.a, hcVar);
            }
        }
        ac acVar = c;
        if (acVar != null) {
            acVar.a();
        }
        ac acVar2 = d;
        if (acVar2 != null) {
            acVar2.a();
        }
    }

    @Override // android.service.notification.NotificationListenerService
    public final void onListenerDisconnected() {
        super.onListenerDisconnected();
        synchronized (a) {
            b.clear();
        }
        ac acVar = c;
        if (acVar != null) {
            acVar.a();
        }
        ac acVar2 = d;
        if (acVar2 != null) {
            acVar2.a();
        }
    }

    @Override // android.service.notification.NotificationListenerService
    public final void onNotificationPosted(StatusBarNotification statusBarNotification) {
        boolean z;
        ac acVar;
        statusBarNotification.getClass();
        hc hcVarA = a(statusBarNotification);
        synchronized (a) {
            z = true;
            try {
                if (hcVarA != null) {
                    b.put(hcVarA.a, hcVarA);
                } else if (b.remove(statusBarNotification.getKey()) == null) {
                    z = false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (!z || (acVar = d) == null) {
            return;
        }
        acVar.a();
    }

    @Override // android.service.notification.NotificationListenerService
    public final void onNotificationRemoved(StatusBarNotification statusBarNotification) {
        boolean z;
        ac acVar;
        statusBarNotification.getClass();
        synchronized (a) {
            z = b.remove(statusBarNotification.getKey()) != null;
        }
        if (!z || (acVar = d) == null) {
            return;
        }
        acVar.a();
    }
}
