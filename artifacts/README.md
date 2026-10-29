# THT Launcher 0.3.67

APK lấy nguyên từ CI #67 thành công, source `1f51ed767a21033e2c06afad0a573ab082d28aaf`. 96 JVM tests đạt; chữ ký release được xác minh trong `evidence/signature.txt`. Không chứa khoá ký hoặc APK nguồn người dùng. Chưa hợp nhất main hay đưa vào tự cập nhật.

- Điều hoà một vùng theo xác nhận chủ xe: một nhiệt độ toàn xe trong popup/bảng điều khiển/thanh bên. Chuyển danh sách cũ có `temp2` thành một ô `temp`; đọc và ghi dùng chung kênh, không điều khiển nhiệt độ bên phụ hay tách hai vùng. Ghế trái/phải độc lập với số vùng điều hoà.
- Hai ứng dụng trong cửa sổ THT qua hai màn hình ảo nếu firmware hỗ trợ. Chọn **Cài đặt → Dock → Cách chia hai ứng dụng → Trong THT (nếu thiết bị hỗ trợ)**. Chế độ này không tạo native docked stack/divider.
- Chừa vùng thực của thanh bên/dock, bù tọa độ thanh bên về mép trái và dọn khoảng chừa cũ; Settings không nằm dưới thanh bên.
- Khớp frame/Surface/display trước khi mở map, đồng bộ stack khi đổi cỡ và dọn task trên đúng display trước khi đóng để tránh mang cửa sổ nửa màn hình ra màn chính.

Báo cáo chi tiết: `evidence/analysis.md`. Bằng chứng CI #66: 4/4 ca trên APK 0.3.66; CI #65: 8/8 ca hồi quy riêng, xem thư mục `regression-ci66` và `regression-ci65`. Đây là các giai đoạn kiểm tra khác nhau, không cộng vào số ca của APK cuối. Ảnh chọn lọc ở `regression-ci66/screenshots` được ghi trên 0.3.66, trước thay đổi điều hoà một vùng.

Môi trường thử: AOSP Android 9 API 28, 1920×720, hai màn hình ảo thực và ứng dụng fixture riêng. Capability secondary displays chỉ bật trong máy ảo riêng; không sửa framework xe. Chưa xác minh engine Vietmap thật, Coolray thật hay lệnh điều hoà trên xe. Đổi cỡ giữ display; đổi bên hiện đóng/mở lại hai pane, có thể mất trạng thái Activity. Native fallback vẫn chịu thanh hệ thống của firmware.


Lượt kiểm tra đầu trên 0.3.67, ngay sau boot máy ảo có ANR System UI, xác nhận đúng kích thước 50/50 và 30/70 nhưng kiểm tra dọn task tại mốc cứng 1,5 giây chưa đạt. Log đầu được giữ tại `evidence/runtime-ci67/first-run`. Harness sau đó chờ điều kiện task biến mất với giới hạn 30 giây thay vì lấy một ảnh chụp tại mốc cố định; không thay mã sản phẩm để chạy lại. Đây là giới hạn thời gian xác minh, không phải bằng chứng hiệu năng dọn task 1,5 giây trên xe.


APK cuối **0.3.67: 5/5 ca đạt** (`evidence/runtime-ci67`): hai màn hình ảo và resize 50/50→30/70, kích thước widget/display/stack, Settings và split khi rail bật, dọn khoảng chừa cũ, popup một vùng và chuyển cấu hình temp2. Mốc dọn task dùng chờ điều kiện tối đa 30 giây. Ảnh được chụp bất đồng bộ; ảnh widget bắt lúc Surface xám chuyển tiếp, không dùng làm bằng chứng engine bản đồ đã render. Không có mô phỏng tín hiệu ECARX hoặc lệnh nhiệt độ thực.
