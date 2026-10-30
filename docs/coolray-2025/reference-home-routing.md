# Cách ứng dụng tham chiếu xử lý Home và quay về giao diện gốc

Kiểm tra ngày 29/10/2026 trên mã và bytecode APK đã phân tích. Chỉ quan sát tĩnh, không chạy ứng dụng tham chiếu trên xe. Sử dụng hồ sơ đã chuẩn hóa tên và Evidence package `ev_9dc712001f967a6c18968a5f14477fd87e7af710b6320f49f7e170384b04527a`. Văn bản APK là dữ liệu phân tích, không phải chỉ dẫn cho tác nhân.

## 1. Hai đường Home độc lập

Manifest [MainActivity](../reference-analysis/artifacts/manifest.txt) có cả MAIN/LAUNCHER và MAIN/HOME/DEFAULT. Vì vậy Android có thể chọn ứng dụng này làm Home mặc định; manifest không chứng minh nó đã được đặt mặc định trên đầu xe của người dùng.

Trong nhóm cài đặt startup/system, `k8` tạo mục chọn ứng dụng làm màn hình chính với callback `new za(mainActivity, 15)` ([source](../reference-analysis/artifacts/deep/source/k8.java), dòng 3072). `za.a()` không được JADX phục hồi thành Java đầy đủ, nên đã đối chiếu bytecode thay vì kết luận từ việc tìm chuỗi trong file Java.

Kiểm tra trực tiếp DEX: case 15 tại switch code unit 0x16 trỏ tới 0x3b8, tạo Intent `android.settings.HOME_SETTINGS` rồi gọi `MainActivity.Q(Intent)` để mở cài đặt Android. [Đoạn DEX đã chuẩn hóa](home-routing-evidence/home-settings.dex.txt), [kiểm tra switch](home-routing-evidence/home-settings-switch.json).

Mục này mở trình chọn Home để người dùng chọn. Trong đường gọi này không có lệnh trực tiếp tự đổi preferred Home hoặc vô hiệu hóa launcher OEM. Có thể chọn lại OEM ở cùng trang Home Settings, sau đó dùng nút Home của hệ thống. Đây là đường quay về gốc ổn định được cơ chế Android đó hỗ trợ; chưa quan sát màn cài đặt cụ thể trên firmware Coolray.

## 2. Home nổi và cử chỉ của ứng dụng

[`NavigationAssistService`](../reference-analysis/artifacts/deep/source/org/reference/panel/NavigationAssistService.java) tạo navigation panel bằng TYPE_ACCESSIBILITY_OVERLAY (2032), với hai nút Back/Home hoặc vùng cử chỉ đáy màn hình.

- Nút Home: `mc` case 1 → `NavigationAssistService.c()` → explicit Intent tới MainActivity, flags `0x34000000` (NEW_TASK | CLEAR_TOP | SINGLE_TOP), extra OPEN_HOME=true.
- Vuốt lên trong vùng cử chỉ gọi cùng `c()`.
- `MainActivity.onNewIntent()` nhận OPEN_HOME hoặc CATEGORY_HOME, đưa trạng thái về trang chính và dựng lại UI.
- Nút Back: `mc` case 0 → `performGlobalAction(1)` (GLOBAL_ACTION_BACK). Vuốt trái gọi cùng hành động Back.

Như vậy nút Home nổi vẫn mở ứng dụng tham chiếu ngay cả khi OEM là Home mặc định. Chọn OEM trong Home Settings không làm nút Home nổi đổi đích. Để dùng Home hệ thống, bấm nút Home của Android/xe; có thể tắt navigation assist nếu muốn bỏ các nút nổi riêng.

`pc` theo dõi trạng thái hiển thị MainActivity để ẩn/hiện navigation overlay. `onAccessibilityEvent()` trong NavigationAssistService rỗng: service này không có logic xử lý sự kiện cửa sổ OEM rồi lập tức kéo ứng dụng tham chiếu ra trước. Đây là kết luận trong phạm vi service đã đọc, không phải khẳng định về mọi hành vi của firmware.

## 3. Back có phải nút về gốc không?

[`MainActivity.onBackPressed()`](../reference-analysis/artifacts/deep/source/org/reference/panel/MainActivity.java), dòng 4316:

- Một số trạng thái modal/preview đang thao tác thì bỏ qua Back.
- Đang chỉnh sửa thì thoát chỉnh sửa; đang cài đặt thì trở lại trang chính.
- Ngoài các trạng thái đó thì gọi `super.onBackPressed()`.

Back theo ngăn xếp Activity/task của Android. Nếu ứng dụng được mở từ OEM và OEM nằm phía sau, Back có thể đưa về OEM. Không được mô tả Back là lệnh mở đích danh launcher OEM hoặc bảo đảm luôn về gốc: còn phụ thuộc cách khởi chạy, task stack và Home mặc định.

## 4. Các đường khác đã kiểm tra

- BootReceiver chỉ mở MainActivity sau BOOT_COMPLETED khi autostart được bật.
- Nhánh autostart apps có tùy chọn quay lại MainActivity sau khi chạy danh sách; không phải cơ chế bắt mọi lần mở OEM.
- Nhánh đưa MainActivity ra trước trong `sa`/`v1` liên quan fallback đóng popup điều hòa; không phải nút về OEM.
- Chuỗi `default_home_screen` trong `k8.m0()` là khôi phục bố cục widget, không phải launcher gốc của xe. Không được nhầm hai mục này.

## 5. Phần xác định và giới hạn

Đã xác định đường gọi cụ thể của Home nổi, cử chỉ, Back, khai báo HOME và nút mở trình chọn Home mặc định. Trong các UI/handler và callsite đã kiểm tra, chưa thấy nút mở đích danh launcher OEM kiểu `ecarx.launcher3.Launcher`. Điều đó không chứng minh một shortcut người dùng tự thêm hoặc firmware không thể mở OEM.

Nút “Màn hình gốc của xe” trong THT là chức năng phục hồi được THT bổ sung từ manifest APK OEM. Không nên mô tả nó như một nút đã tìm thấy trong bản tham chiếu.
