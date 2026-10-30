# Thanh bên, cử chỉ và màn hình phụ

Phản hồi xe: khoảng trống khi bật thanh bên; gutter chia màn hình quá rộng; ứng dụng ngoài hiện thanh OEM; HOME báo không hỗ trợ secondary displays; vuốt Back/Home THT không hoạt động.

Bản sửa:
- Màn chính/hosted dùng lề đo từ vùng thanh bên thực; bỏ overscan khi về THT. Overscan cho ứng dụng ngoài chỉ đặt lúc người dùng mở ứng dụng, không đặt khi tạo thanh bên hoặc khi preview làm Main mất focus. Bề rộng rail cố định 120dp, không suy ra từ hiệu kích thước display đang bị inset. Main cập nhật lề khi layout thay đổi.
- Gutter hosted 24dp. Kéo đổi tỷ lệ, chạm mở menu Đóng/Đổi bên; phân biệt tap/drag bằng touch slop hệ thống.
- Bar mode cũng tạo vùng cử chỉ. Vùng chạm 400×40dp ở mép dưới, chỉ vẽ chỉ báo 160×4dp. Vuốt lên Home THT, vuốt ngang Back.
- OEM Home/HVAC và tự khởi động THT chỉ định display 0. Policy fullscreen của app được áp lại trong lượt sửa task nếu app đó thực sự đang visible.
- Không nhúng launcher hoặc activity khai báo resizeMode=0. Map giữ nút mở fullscreen; split từ chối trước khi tạo host. Lỗi hosted đưa về Home THT. Không ép mọi app hỗ trợ secondary display và không sửa framework xe.

Chưa xác minh trên firmware Coolray hoặc Vietmap thật. Capability và resize metadata không chứng minh mọi engine hỗ trợ nhúng; firmware vẫn có thể từ chối. Chưa thể khẳng định nguyên nhân duy nhất của khoảng trống/OEM rail chỉ từ ảnh. Kết quả CI/runtime được ghi riêng ở nhánh artifacts của bản build.
