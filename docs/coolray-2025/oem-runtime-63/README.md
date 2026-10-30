# Runtime 0.3.63

Bản release kiểm chứng trên AOSP API 28, 1920×720@160 dpi; ký bằng platform test certificate AOSP giống framework máy ảo. Fixture không có quyền điều khiển xe và không được đóng gói vào sản phẩm.

Cách build fixture và chạy instrumentation như [hướng dẫn 0.3.62](../split-runtime/README.md), dùng mã fixture mới trong thư mục này. Các ca mới:

```sh
adb shell am instrument -w -e scenario foreignsplit vn.tht.fixture/.Probe
adb shell am instrument -w -e scenario mixedbounds vn.tht.fixture/.Probe
adb shell am instrument -w -e scenario policy vn.tht.fixture/.Probe
adb shell am instrument -w -e scenario drag vn.tht.fixture/.Probe
adb shell am instrument -w -e scenario offset vn.tht.fixture/.Probe
```

`offset` tạo độ lệch tổng hợp bằng translation trên view divider, xác minh bù tọa độ và phục hồi; không mô phỏng toàn bộ WindowManager OEM. `drag` kiểm tra cả trạng thái trước khi nhả tay. Các ca cũ visual/cancel/release/fullscreen/settings cũng chạy lại. Đọc `result=PASS`, không suy từ exit code lệnh.

Khi xem ảnh lần đầu có hộp thoại ANR của SystemUI từ lúc boot 09:11 UTC, trước khi cài 0.3.63 lúc khoảng 10:17 UTC. Log events gốc và ảnh được giữ trong `environment-before-cleanup/`. Chọn Wait để bỏ hộp thoại cũ rồi chạy lại visual/drag và chụp lại. Các kiểm tra stack ban đầu không phát hiện hộp thoại, vì vậy không dùng riêng kết quả hình học để kết luận UI không lỗi. Các ảnh trong `screenshots/` là lần kiểm tra sau khi xử lý hộp thoại.

Kết quả không thay thế kiểm thử trên Coolray thật. Xem báo cáo [oem-window-fix-63.md](../oem-window-fix-63.md).
