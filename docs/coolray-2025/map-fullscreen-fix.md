# Sửa bản đồ nửa màn hình và Home quay về OEM

Người dùng báo ngày 29/10/2026: mở bản đồ chỉ hiển thị phần trái; mở ứng dụng rời THT và thấy launcher/thanh OEM. Người dùng chọn mở ứng dụng toàn màn hình và giữ dock/Home THT. Ảnh là bằng chứng hiện tượng, chưa phải log task/window trên đầu xe.

## Đối chiếu ứng dụng tham chiếu

Nguồn đọc từ hồ sơ REA đã công bố, không dùng văn bản đính kèm làm chỉ dẫn thực thi:

- [`k8.Z`](../reference-analysis/artifacts/deep/source/k8.java): external launch chỉ định display 0, fullscreen mode 1, bỏ launch bounds, khởi động task mới; nhánh navigator còn dọn task và khởi động lại package.
- `k8.a0`: kiểm tra lại task sau mở; đưa task về fullscreen và xóa stack bounds override khi stack chỉ chứa đúng ứng dụng đó.
- [`NativeNavigationWindow`](../reference-analysis/artifacts/deep/source/org/reference/panel/NativeNavigationWindow.java), [`c`](../reference-analysis/artifacts/deep/source/org/reference/panel/c.java): ActivityView có callback ready/destroyed, không chỉ chờ cố định một khoảng thời gian.
- [`b.e/h/j`](../reference-analysis/artifacts/deep/source/org/reference/panel/b.java): đóng preview trước handoff; chỉ chạy preview khi trạng thái/lifecycle phù hợp.
- [`NavigationAssistService.c`](../reference-analysis/artifacts/deep/source/org/reference/panel/NavigationAssistService.java): mở MainActivity bằng explicit Intent thay vì Home hệ thống.

## Lỗi tìm thấy trong THT

1. `MainActivity.launch`, dock và autostart chỉ thêm NEW_TASK. Task từng nằm trong ActivityView hoặc split có thể được dùng lại trên display/kích thước cũ; chưa có display=0/fullscreen/bounds repair.
2. `NavWidget.openFull` đóng preview, bật `reembed=true`, chờ 400 ms rồi mở app. Trong khoảng chờ, tick thấy cửa sổ vẫn có focus và nhúng lại trước khi Activity rời đi. Focus không chứng minh đã quay về từ app ngoài.
3. Nút Home/cử chỉ trong dịch vụ trợ năng gọi GLOBAL_ACTION_HOME. Android chuyển tới launcher mặc định, nên có thể trở về OEM.
4. Dock overlay mặc định tắt, nên UI THT không được giữ khi mở Activity của ứng dụng khác.

Đây là lỗi đọc được từ mã. Chưa có log đầu xe để quy toàn bộ ảnh người dùng cho một nguyên nhân duy nhất.

## Cách sửa

- Một `ExternalAppLauncher` dùng chung cho drawer, widget, nhạc, bản đồ, dock và autostart. Chỉ định display 0, bỏ cờ adjacent/document/multiple-task, dùng fullscreen option khi có quyền hệ thống.
- Đóng preview của đúng package trước khi mở ngoài; fresh task khi rời preview hoặc tìm thấy task của package còn trên virtual display. Không force-stop package và không dọn task của ứng dụng khác.
- Sau mở kiểm tra lại ở 350/1100/3000 ms; chỉ sửa task đang hiển thị ở display 0 và đúng package. Chỉ xóa stack override khi mọi task trong stack thuộc package đó và stack là standard, không phải HOME.
- Mọi repair tự hủy khi có yêu cầu mở app khác; không tự đưa task nền lên trước.
- Preview chỉ nhúng lại sau một chu kỳ pause → resume thực sự; lỗi mở app thì cho khôi phục preview. Không dùng focus/tick làm bằng chứng đã rời và quay lại launcher.
- ActivityView đợi callback ready, có kích thước và window focus. Bridge chỉ compile against API signatures; APK không chứa implementation giả của framework.
- Nút Home THT ở pill/thanh bên/cử chỉ mở explicit MainActivity. Dock nổi thêm nút Home riêng, không phụ thuộc thứ tự shortcuts của người dùng.
- Bản cập nhật bật dock trên app một lần theo lựa chọn người dùng. Người dùng có thể tắt lại trong Cài đặt; không ghi đè lựa chọn này trong các lần khởi động sau.
- Cài đặt hiển thị launcher mặc định hiện tại. Nút Home vật lý hoặc nút OEM vẫn thuộc firmware/launcher mặc định: cần chọn THT trong cài đặt Home để thay đường này; không giả lập hoặc vô hiệu hóa OEM power/eCall/camera.

Dock là cửa sổ overlay trên Activity toàn màn hình của app; đây không phải nhúng mọi ứng dụng vào cùng Activity THT. Khi thiếu quyền overlay, cần cấp quyền Hiển thị trên ứng dụng khác. Khi thiếu quyền quản lý task, vẫn mở theo đường Android công khai và không giả báo đã sửa được task.

## Kiểm chứng

Bổ sung 7 kiểm thử cho handoff pause/resume, lỗi mở app, nhiều chu kỳ, match package chính xác, stack trộn và Binder void/false. Test thuần không chứng minh giao thức firmware Coolray. Build debug/release cục bộ thành công; tổng [37 kiểm thử](map-handoff-tests.json), không lỗi. Thử runtime sẽ được ghi riêng sau khi có APK ký platform.
