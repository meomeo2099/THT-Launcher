# THT Launcher 0.3.73

Source 5b55649f044a9201992170fac74b9373cb42d32a. Bản sửa phản hồi về khoảng trống cạnh thanh trái, nút thoát chia đôi, app nhúng khởi động lại và giao diện OEM bị THT can thiệp.

- Khi chủ động mở launcher gốc (qua nút riêng hoặc danh sách ứng dụng OEM): đóng split, bật stock_mode, huỷ sửa cửa sổ trì hoãn, trả policy/overscan của THT, gỡ rail/cử chỉ/khung nhiệt độ, ẩn dock nổi và chặn tạo lại các phần này. Trở về THT bằng biểu tượng app sẽ bật lại giao diện THT.
- Không đặt positive overscan khi mở ứng dụng ngoài; dọn giá trị THT cũ. Đây là thay đổi xử lý nguồn chừa lề kép, chưa chứng minh hết letterbox trên Coolray; app có thể nằm dưới rail nổi trên firmware không có inset OEM.
- Nút × hiện trực tiếp trên gutter 24dp. Chạm ⋮ vẫn có menu đóng/đổi bên.
- Về Home giữ hosted session. Mở lại cùng cặp dùng lại host; đổi bên bằng đổi thứ tự bố trí mà không tháo ActivityView/task. Không dựng lại widget vì stock_mode/nav_bar_overscan thay đổi tạm thời.
- Giữ điều hoà một vùng và cử chỉ Back/Home.

Bằng chứng CI72: 2/2 ca đạt (giữ host/maps qua Home và swap, chạm nút × đóng/dọn task; stock mode gỡ rail/cử chỉ, ẩn dock, xoá overscan và chặn policy; Back/Home). Ghi riêng tại prior-ci72. Source CI73 thêm chặn khung nhiệt độ phát sinh sau khi vào stock mode. Không cộng các giai đoạn thành số ca cuối.

Giới hạn: chưa thử xe Coolray/Vietmap/YouTube thật. Android có thể thu hồi tiến trình/task. Mở app nhúng thành fullscreen riêng và thoát hẳn phiên bằng × vẫn dọn task theo cơ chế handoff trước đây; không bảo đảm giữ Activity ở các trường hợp đó. Đổi tỷ lệ có thể khiến app tự recreate theo configChanges. Chưa hợp nhất main hoặc đưa vào tự cập nhật. Không thay framework xe.

Cách dùng: nhấn × trên thanh chia để thoát hẳn phiên; chạm ⋮ để đổi bên. Về Home THT rồi chọn lại cùng cặp giữ phiên đang có. Cài đặt → Xe → Màn hình gốc của xe bật stock mode; khi ở đó THT ẩn UI nổi. Mở biểu tượng THT để dùng lại. Đóng bằng × hoặc chuyển sang OEM là thao tác kết thúc phiên, khác với chỉ về Home THT.

CI73 thành công, 96 JVM tests (0 failures/errors/skipped); chữ ký APK xác minh trong signature.txt. Runtime trên APK 0.3.73: GestureProbe đạt, bao gồm chặn launcher/non-resizable, stock_mode gỡ rail/cử chỉ, ẩn dock, overscan=0, chặn ghi policy và chặn popup nhiệt độ mới; sau thoát mode, vuốt Home/Back hoạt động. Lượt đầu chưa đạt assertion Back chỉ nhìn tên package; giữ log nguyên, lượt sau ghi thêm focused task để theo dõi chuyển foreground. Không sửa sản phẩm giữa hai lượt. Không khẳng định nguyên nhân của lần chưa đạt chỉ từ log. Các ca giữ host/maps và nút × của CI72 được ghi riêng; không chạy lại trên CI73 vì thay đổi cuối chỉ thêm guard popup nhiệt độ.
