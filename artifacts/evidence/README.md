# Kiểm chứng APK từ CI #63

92 JVM tests đạt và 11/11 instrumentation scenarios đạt trên cùng APK release từ CI. `installed-apk.json` xác nhận APK cài trên máy ảo khớp SHA-256 bản CI.

`nativegesture` dùng Instrumentation.sendPointerSync qua hệ thống input Android, xác nhận hai ô đổi kích thước trước UP và còn hợp lệ sau UP/khôi phục. Các ca còn lại kiểm tra native split cũ, mixed standard fullscreen frame, policy restore, offset tổng hợp, đổi tỉ lệ/đổi ô, hủy/release/fullscreen/Settings. Fixture mới ở `fixture/` có cả ca nativegesture; build theo hướng dẫn trong docs/coolray-2025/split-runtime/README.md. Fixture không được đóng gói vào THT.

Ảnh trong `screenshots/` là thao tác kéo sau khi dọn hộp thoại ANR SystemUI của lúc boot; chỉ còn chỉ báo kéo native. `environment/` giữ ảnh các pha visual khi hộp thoại boot cũ còn xuất hiện, không dùng chúng để khẳng định UI không lỗi. `anr-boot-events.txt` ghi thời điểm ANR. Một lần chạy trước bị gián đoạn vì tiến trình máy ảo thoát; lần visual đó không có kết quả PASS và đã chạy lại.

Máy ảo AOSP API 28, 1920×720@160dpi. Thanh trạng thái/điều hướng vẫn có thể hiện do policy native split của Android 9. Chưa kiểm thử đầu Coolray và renderer bản đồ thật trên VirtualDisplay.
