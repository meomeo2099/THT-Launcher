# THT Launcher 0.3.63 — cửa sổ ứng dụng và vạch chia OEM

APK `THT-Launcher-0.3.63-release.apk` lấy trực tiếp từ CI #63 thành công, commit `180c416df35ed760b99cd1d696dc4be7dd080585`. 92 kiểm thử JVM đạt; chứng thư release SHA-256 `c8a2e9bccf597c2fb6dc66bee293fc13f2fc47ec77bc6b2b0d52c11f51192ab8`. Bản này chưa hợp nhất main hoặc đưa vào tự cập nhật.

Sửa policy fullscreen theo từng app, khôi phục native split cũ có chứa app được mở, trả frame standard fullscreen stack dùng chung về mặc định, bù tọa độ overlay theo vị trí thực, resize ngay lúc kéo và bỏ vạch trắng THT vẽ trùng với native. Dock/Home THT vẫn được giữ theo cài đặt.

Android 9 có thể bắt buộc hiện status/navigation bar khi native split đang hoạt động. Không tuyên bố ẩn mọi thanh OEM bằng immersive hoặc xác nhận đã hết lỗi trên xe. Máy ảo không có firmware Coolray và không hỗ trợ activity trên secondary display.

Xem báo cáo `docs/coolray-2025/oem-window-fix-63.md`. Kết quả CI, chữ ký và runtime cuối được giữ trong `artifacts/evidence/`; fixture của phép thử được lưu kèm báo cáo kiểm chứng. Không có khóa ký trong nhánh này.

Kiểm chứng cuối: **11/11 ca runtime đạt** trên APK từ CI, bao gồm kéo bằng input Android thật; báo cáo chi tiết và giới hạn nằm trong `evidence/runtime-results.json` và `evidence/README.md`.
