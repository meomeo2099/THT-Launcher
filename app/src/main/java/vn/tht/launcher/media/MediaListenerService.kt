package vn.tht.launcher.media

import android.service.notification.NotificationListenerService

/**
 * Chỉ cần tồn tại và được người dùng cấp quyền "Truy cập thông báo"
 * thì MediaSessionManager mới cho đọc các phiên phát nhạc của app khác.
 */
class MediaListenerService : NotificationListenerService()
