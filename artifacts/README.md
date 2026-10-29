# THT Launcher 0.3.68

APK nguyên từ CI #68 thành công, source `0ea5eec1e36180761f42417aa155f822f29a7a66`. 96 JVM tests đạt, chữ ký xác minh trong evidence/signature.txt. Chưa hợp nhất main hoặc đưa vào tự cập nhật.

Chủ xe xác nhận nút đóng/đổi bên không phản hồi trên bản cũ hơn 0.3.67. Bản sửa:

- Native: hai nút 56dp nằm trong bảng nhỏ lệch khỏi tâm divider OEM 48dp, thay vì một overlay toàn chiều cao đè vào vùng kéo. Tiếp tục kéo bằng handle hệ thống; bảng nút di chuyển theo divider.
- Hosted THT: vùng bấm 64×64dp, có mô tả rõ, chặn đổi bên lặp khi release; dọn pane qua một đường closeNow.
- Giữ toàn bộ sửa map/insets và điều hoà một vùng của 0.3.67.

Báo cáo và nguồn fixture tại evidence. Probe chạm MotionEvent DOWN/UP ở tọa độ tâm View thực qua InputDispatcher sau khi chuyển focus vào pane app; kiểm tra thứ tự hai app và dọn task khi đóng. Không dùng performClick hoặc gọi hàm swap/close để xác nhận nút. Baseline 0.3.67 trên AOSP vẫn nhận chạm ở cả hai chế độ, nên chưa tái hiện nguyên nhân riêng trên firmware xe. Log baseline được tách riêng.

Môi trường: máy ảo riêng AOSP Android 9 1920×720 với capability secondary displays; không kết nối hoặc sửa framework xe. Chưa kiểm chứng trên Coolray thật/Vietmap thật. Đổi bên hiện tạo lại hai pane và có thể mất route/trạng thái Activity. Chọn Cài đặt → Dock → Cách chia hai ứng dụng → Trong THT (nếu thiết bị hỗ trợ) để dùng hosted; native fallback phụ thuộc SystemUI firmware.


APK cuối 0.3.68 đạt **4/4 ca chạm qua InputDispatcher**: native 50/50, native 30/70, hosted 50/50, hosted 30/70. Mỗi ca chạm pane để chuyển focus, chạm nút đổi bên, xác nhận hai app đổi thứ tự, chạm pane còn lại, chạm nút đóng và chờ dọn hết trạng thái/task chia đôi. Hosted dùng màn hình ảo thật. Chi tiết tọa độ và kết quả tại `evidence/runtime-ci68`. Đây là kiểm tra giao diện nhận chạm, không phải xác minh trên xe thật.
