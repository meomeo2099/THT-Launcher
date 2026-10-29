# Bản kiểm chứng 0.3.62

CI #62 lần chạy 2 đã thành công sau khi repository được mở công khai: debug/release build, 84 tests, 0 failures/errors/skipped. [Tải artifact CI](https://github.com/meomeo2099/THT-Launcher/actions/runs/40848692178/artifacts/12886278515). Bản release CI cũng được giữ tại `ci62/THT-Launcher-0.3.62-release.apk`, SHA-256 `6b534755d36b88a9a8f0a6d134492e069aa08e337ff3b1d204c66c0cc2151ddc`; chữ ký khớp chứng thư AOSP của bản kiểm thử máy ảo. `ci62/` có báo cáo 84 tests và xác minh chữ ký.

APK/ZIP ở thư mục gốc là bản build và kiểm chứng tại workspace trước khi CI chạy lại. Cả hai cùng mã nguồn 311e268; file APK khác byte do môi trường build và cấu hình ký. Bằng chứng runtime 5/5 ca thuộc bản workspace. Chưa kiểm thử Coolray thật; chưa hợp nhất main hoặc đưa vào tự cập nhật.

## Ghi nhận trước khi chạy lại CI

Bản kiểm chứng chia màn hình từ commit 311e268 trên nhánh feature/coolray-reference-launcher, PR #19. Chưa hợp nhất main; nhánh artifacts không đi vào luồng tự cập nhật.

Sửa vòng đời native split, hủy callback, xác nhận task/bounds, lấy kích thước màn hình vật lý, đổi ô, tránh race với fullscreen và khôi phục khi đóng. Bước primary dùng API direct-native không animation để tránh lỗi framework Android 9 khi hủy ngay đường Recents.

Build debug/release tại workspace thành công; 84 kiểm thử JVM đạt và 5/5 instrumentation scenarios trên AOSP API 28 1920×720 đạt (visual, cancel, release, fullscreen, settings). ZIP trong thư mục này có log, JUnit, ảnh, mã fixture và báo cáo.

CI #62 bị chặn trước khi chạy do Actions budget; không phải CI build thành công. APK release trong thư mục này là bản build tại workspace, ký bằng chứng thư platform thử nghiệm AOSP SHA-256 c8a2e9bccf597c2fb6dc66bee293fc13f2fc47ec77bc6b2b0d52c11f51192ab8, cùng bản đã chạy trên máy ảo. Chưa thử trên Coolray thật; cần chữ ký/quyền tương thích firmware xe. Không thay quyền truy cập repository.

APK SHA-256: 89ec414e7b7be5fa976449dbbe2fda577a8eeef67f2aaa6df6d734fb11a5cf6f

GitHub Actions bị chặn bởi budget; upload release asset bị từ chối HTTP 401 trên uploads.github.com. Hai file được lưu bằng git trên nhánh artifacts riêng, giữ nguyên nhánh mã nguồn đã kiểm chứng. Bản nháp phát hành rỗng đã được dọn.
