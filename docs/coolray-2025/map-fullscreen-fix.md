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
- Chỉ nhúng nếu firmware báo hỗ trợ Activity trên secondary display; không chỉ kiểm tra chữ ký. ActivityView đợi callback ready, có kích thước và window focus. Bridge chỉ compile against API signatures; APK không chứa implementation giả của framework.
- Nút Home THT ở pill/thanh bên/cử chỉ mở explicit MainActivity. Dock nổi thêm nút Home riêng, không phụ thuộc thứ tự shortcuts của người dùng.
- Bản cập nhật bật dock trên app một lần theo lựa chọn người dùng. Người dùng có thể tắt lại trong Cài đặt; không ghi đè lựa chọn này trong các lần khởi động sau.
- Cài đặt hiển thị launcher mặc định hiện tại. Nút Home vật lý hoặc nút OEM vẫn thuộc firmware/launcher mặc định: cần chọn THT trong cài đặt Home để thay đường này; không giả lập hoặc vô hiệu hóa OEM power/eCall/camera.

Dock là cửa sổ overlay trên Activity toàn màn hình của app; đây không phải nhúng mọi ứng dụng vào cùng Activity THT. Khi thiếu quyền overlay, cần cấp quyền Hiển thị trên ứng dụng khác. Khi thiếu quyền quản lý task, vẫn mở theo đường Android công khai và không giả báo đã sửa được task.

## Kiểm chứng

Bổ sung 10 kiểm thử cho handoff pause/resume, lỗi mở app, nhiều chu kỳ, match package chính xác, stack trộn, Binder void/false và cấu trúc WindowConfiguration của Android 9. Test thuần không chứng minh giao thức firmware Coolray. Build debug/release cục bộ thành công; tổng [40 kiểm thử](map-handoff-tests.json), không lỗi. Kết quả runtime ở phần dưới.

### Runtime Android 9, APK 0.3.60 ký platform

Máy ảo không có feature `android.software.activities_on_secondary_displays`. Khi chỉ gọi ActivityView.startActivity trên máy này, Android đưa app sang display 0 dù virtual display đã được tạo. Đã thêm capability guard để dùng nút mở toàn màn hình trên cấu hình này; không khẳng định đã thử thành công handoff từ một Activity thực sự chạy trên secondary display.

Thử bằng [fixture tự viết](map-runtime/fixture/), không dùng app điều khiển xe:

1. Đặt stack riêng của fixture ở chiều rộng 960 px bằng `am stack resize`. [Trước mở](map-runtime/before-half-width.txt): task 16, stack 11, display 0, bounds 960×720.
2. Trở về THT và chạm ô Dẫn đường đã chọn fixture. [Sau mở](map-runtime/after-fullscreen.txt): đúng task/stack đó có bounds 1920×720, mode fullscreen, display 0. [Log](map-runtime/repair-log.txt) xác nhận `cleared=true`.
3. [Ảnh app toàn màn hình cùng dock/Home THT](map-runtime/fullscreen-dock.png). Chạm Home ở dock: [task THT visible=true](map-runtime/after-home.txt), fixture visible=false; [ảnh trở về THT](map-runtime/home-after.png).
4. [Kiểm tra DEX](map-runtime/apk-framework-bridge-check.json): không có định nghĩa `android.app.ActivityView`/StateCallback giả trong APK; có callback subclass của THT. [Chữ ký APK cục bộ](map-runtime/local-platform-signature.txt) trùng framework máy ảo và hai mẫu OEM. Khóa dùng để thử là khóa test AOSP công khai chính thức, không lấy signing secrets của GitHub về máy.

Bài thử đầu phát hiện Android 9 không có getter activityType trực tiếp trên StackInfo, nên điều kiện an toàn đã bỏ qua resizeStack. Đã thêm fallback đọc Configuration.windowConfiguration và ba test hồi quy. Không dùng resizeTask để xóa bounds của task fullscreen, vì Android 9 từ chối lệnh đó; xóa stack override thuộc độc quyền đúng package rồi đặt windowing mode của task.

GitHub run #59 ban đầu không chạy do Actions budget. Sau khi người dùng chuyển repository sang PUBLIC, chạy lại đã thành công. Bản sửa Android 9/capability và bằng chứng này được đưa vào lượt build tiếp theo.

Chưa chạy bản đồ OEM/Vietmap hoặc thử trên đầu xe Coolray thật. Kích thước task, chữ ký và đường Home đã kiểm chứng trên máy ảo không chứng minh mọi SDK bản đồ/vendor SystemUI đều xử lý giống nhau.
