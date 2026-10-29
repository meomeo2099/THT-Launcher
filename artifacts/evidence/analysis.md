# Nút đóng và đổi bên của chia màn hình

Chủ xe báo nút đóng và đổi bên của bản trước không dùng được. Kiểm tra trước đây gọi hàm trực tiếp nên không chứng minh vùng nút nhận chạm thật.

## Quan sát và sửa

Probe mới gửi MotionEvent DOWN/UP qua InputDispatcher tại tâm View thực tế, sau khi chạm vào pane ứng dụng để chuyển focus. APK 0.3.67 trên AOSP Android 9 vẫn đóng/đổi bên được ở cả native và hosted; chưa tái hiện firmware Coolray và không khẳng định đã xác định nguyên nhân trên xe.

Ở chế độ native, control cũ đặt trên tâm divider, trong cửa sổ overlay cao bằng pane. Vùng kéo của SystemUI có thể nằm trên overlay ứng dụng trên firmware OEM. Bản sửa đặt bảng hai nút rộng 56dp lệch 48dp sang bên phải divider; cửa sổ chỉ cao 120dp. Native handle tiếp tục chịu trách nhiệm kéo tỷ lệ, watcher đồng bộ hình học. Control được di chuyển theo divider khi đổi tỷ lệ; không còn lớp bắt chạm toàn chiều cao ở tâm vạch chia.

Ở chế độ hosted, nút có vùng bấm 64×64dp, nhãn accessibility rõ ràng, không bị minimum/padding của Button mặc định ép vào ô 48dp. Đổi bên kiểm tra trạng thái release, không detach/release pane lặp trước khi closeNow dọn task/display. Giữ cấu hình điều hoà một vùng đã có.

## Giới hạn

Phép thử dùng máy ảo riêng và app fixture, không kết nối xe. Không thay XML/framework xe. Swap hiện tạo lại pane nên có thể mất trạng thái Activity/route; không bảo đảm mọi ứng dụng hỗ trợ embedding. Native fallback vẫn phụ thuộc SystemUI của firmware. Evidence theo CI/APK được lưu tại nhánh artifacts của bản sửa; log baseline 0.3.67 và fixture nằm riêng để phân biệt trước/sau.
