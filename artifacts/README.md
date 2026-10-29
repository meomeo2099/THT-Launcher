Bản kiểm chứng chia màn hình từ commit 311e268 trên nhánh feature/coolray-reference-launcher, PR #19. Chưa hợp nhất main; nhánh artifacts không đi vào luồng tự cập nhật.

Sửa vòng đời native split, hủy callback, xác nhận task/bounds, lấy kích thước màn hình vật lý, đổi ô, tránh race với fullscreen và khôi phục khi đóng. Bước primary dùng API direct-native không animation để tránh lỗi framework Android 9 khi hủy ngay đường Recents.

Build debug/release tại workspace thành công; 84 kiểm thử JVM đạt và 5/5 instrumentation scenarios trên AOSP API 28 1920×720 đạt (visual, cancel, release, fullscreen, settings). ZIP trong thư mục này có log, JUnit, ảnh, mã fixture và báo cáo.

CI #62 bị chặn trước khi chạy do Actions budget; không phải CI build thành công. APK release trong thư mục này là bản build tại workspace, ký bằng chứng thư platform thử nghiệm AOSP SHA-256 c8a2e9bccf597c2fb6dc66bee293fc13f2fc47ec77bc6b2b0d52c11f51192ab8, cùng bản đã chạy trên máy ảo. Chưa thử trên Coolray thật; cần chữ ký/quyền tương thích firmware xe. Không thay quyền truy cập repository.

APK SHA-256: 89ec414e7b7be5fa976449dbbe2fda577a8eeef67f2aaa6df6d734fb11a5cf6f

GitHub Actions bị chặn bởi budget; upload release asset bị từ chối HTTP 401 trên uploads.github.com. Hai file được lưu bằng git trên nhánh artifacts riêng, giữ nguyên nhánh mã nguồn đã kiểm chứng. Bản nháp phát hành rỗng đã được dọn.
