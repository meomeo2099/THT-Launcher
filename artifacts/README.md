# THT Launcher 0.3.70

APK nguyên từ CI #70 thành công, source 93b04ce7111e8ca59d8fcafdb89b3aedeb1eed1e. 96 JVM tests đạt; chữ ký được xác minh. Chưa hợp nhất main hoặc đưa lên tự cập nhật.

- Thanh trái: THT dùng lề đo thực, bỏ overscan khi về Home; chỉ đặt khoảng chừa toàn màn hình khi chủ động mở app ngoài. Bề rộng rail 120dp.
- Thanh chia hosted 24dp; kéo đổi tỷ lệ, chạm dấu ⋮ để chọn Đóng/Đổi bên.
- Chế độ thanh bên cũng có cử chỉ Back/Home: vùng chạm 400×40dp ở mép dưới; chỉ báo mảnh 160×4dp. Vuốt lên về THT, ngang để Back.
- Home/HVAC gốc và khởi động THT được chỉ định display 0; policy fullscreen áp lại nếu app cần mở thực sự đang visible.
- Chặn nhúng launcher và activity khai báo không cho đổi kích thước. Map giữ đường mở toàn màn hình; split từ chối trước khi tạo host. Lỗi hosted quay về THT.
- Giữ điều hoà một vùng.

Báo cáo chi tiết và nguồn fixture tại evidence. Chưa xác minh firmware Coolray hoặc Vietmap thật; ảnh không đủ xác định duy nhất nguyên nhân OEM rail và khoảng trống. Kiểm tra metadata không bảo đảm mọi ứng dụng hỗ trợ nhúng. Không sửa framework xe. Swap tạo lại pane có thể mất trạng thái Activity.

Bản cuối dùng hộp chọn ở giữa màn hình cho Đóng/Đổi bên, tránh menu nằm dưới dock nổi. Bằng chứng CI69 được giữ ở prior-ci69: insets PASS, gesture sau sửa cách đọc foreground PASS; lượt đầu gesture đọc getRunningTasks bị giới hạn nên báo sai foreground, không dùng kết quả đó làm bằng chứng sản phẩm lỗi. Ca nút của CI69 thất bại vì menu dưới dock là lỗi thực, đã sửa ở source cuối.


APK 0.3.70: 2/2 ca runtime đạt trên AOSP Android 9. ButtonsProbe chạm gutter, chọn Đổi bên trong hộp giữa màn hình tại (960,384), xác nhận đổi app/tỷ lệ, chọn Đóng tại (960,336), xác nhận dọn task. GestureProbe kiểm tra từ chối THT HOME và fixture resizeable=false; bật bar mode, vuốt lên từ ứng dụng về THT, xác nhận overscan THT về 0, mở lại app và vuốt ngang Back. Các thao tác gửi MotionEvent qua InputDispatcher. Insets CI69 đạt riêng (railRight=120, menuLeft=152, hai pane 888×572), không cộng vào số ca cuối. Chưa kiểm tra xe thật.
