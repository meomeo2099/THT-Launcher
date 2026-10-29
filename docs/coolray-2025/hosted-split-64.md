# 0.3.64 — hai app trong THT, lề thanh bên và viewport bản đồ

Ảnh mới trên xe cho thấy vạch đen vẫn cắt giữa nội dung bản đồ dù ranh giới bản đồ/YouTube đã ở vị trí khác; thanh bên vẽ đè menu Cài đặt; Vietmap trên trang chính đôi khi chỉ vẽ nửa vùng. Bản 0.3.63 chưa giải quyết hết các trường hợp này.

## Thay đổi cách chia hai app

Trên firmware có `android.software.activities_on_secondary_displays` và các quyền embedding/quản lý task, mặc định dùng `HostedSplitActivity`: một cửa sổ THT fullscreen chứa hai `ActivityView`/màn hình ảo riêng. Không chuyển app sang native windowing mode 3/4, nên không tạo vạch `DockedStackDivider` của hệ thống. Thanh kéo/đóng/đổi bên nằm trong dải giữa hai ô; không đặt nút lên nội dung app.

Cài đặt → Dock → **Cách chia hai ứng dụng** có chế độ trong THT khi hỗ trợ và chế độ native. Thiết bị thiếu capability/quyền vẫn dùng native; không giả định mọi Coolray đều có cùng feature. Lựa chọn này được sao lưu. Các luồng native trước vẫn được giữ làm phương án tương thích.

Owner dùng token mới cho mỗi lần mở; hủy lúc Activity chưa tạo xong cũng vô hiệu hóa token. Activity đến muộn chỉ tự đóng, không sửa policy của cửa sổ mới. Đóng/Home/mở app fullscreen giải phóng hai task và hai màn hình ảo thuộc host, rồi mới handoff tới app ngoài. Không giữ strong reference tới MainActivity trong callback tĩnh.

## Sửa bản đồ và vòng đời màn hình ảo

`EmbeddedViewportGate` đợi host, Surface và Display cùng kích thước qua hai mẫu liên tiếp giống nhau trước khi mở app. Cờ mở mới không kế thừa task/bounds của màn hình trước. Sau đó chỉ xóa bounds override của stack nằm trên chính virtual display do view sở hữu.

Khi đổi cỡ ô, giữ nguyên màn hình ảo và để `ActivityView.surfaceChanged` resize, rồi đồng bộ lại stack. Không hủy/tạo màn hình ở mỗi lần kéo. Ca thực nghiệm đã bắt được vấn đề của hướng hủy/tạo: Android 9 có thể chuyển task từ PUBLIC virtual display về display 0 khi display bị bỏ, làm host bị che bởi một Activity mang kích thước cũ. Đây là đường cần xử lý ngoài việc sửa bounds của native split.

Khi đóng, dọn task trên **đúng display ID do view sở hữu trước**, rồi detach Surface và chờ 1 giây cho các thông báo/animation dọn cửa sổ trước khi gọi release display. Cần quyền signature `REMOVE_TASKS`; không tạo đường nhúng nếu không có quyền dọn. Không force-stop package, không dọn display 0 hoặc màn hình phụ không thuộc view. SDK có thể kết thúc tiến trình nền khi task cuối bị remove; không bảo đảm giữ nguyên trạng thái Activity/route của mọi app.

Một candidate trước sửa đã làm AOSP API 28 lỗi `ViewRootImpl` trên thread `android.anim` khi bỏ display. Trace được giữ trong hồ sơ, không tính ca đó là đạt. Callback của ActivityView cũ cũng được chặn bằng generation để không báo lỗi nhầm cho view mới.

## Chừa chỗ cho thanh bên và dock

`ContentInsets` đo mép thật của thanh trợ năng THT, trừ phần cửa sổ đã bị hệ thống/overscan dịch vào. Menu Settings và hai ô host được chừa lề theo phần còn bị phủ; tránh cộng lề hai lần. Khi bật fullscreen và thanh bên, service chủ động giữ vùng riêng của thanh thay vì dựa vào phép đo rail OEM có thể mất hiệu lực sau immersive.

Host/Settings cũng đo dock nổi thực tế để giữ nội dung phía trên dock, không chỉ dùng chiều cao mặc định trước lúc Main.onPause cập nhật vị trí. Thanh trạng thái trên được chừa theo window insets.

## Kiểm chứng và giới hạn

Build local debug/release và **96 kiểm thử JVM đạt**. Máy ảo AOSP API 28 1920×720 đã được khai báo capability secondary-display trong **môi trường thử nghiệm riêng**, sau đó kiểm thử ActivityView/virtual display thật. Không sửa feature/XML hoặc framework trên xe người dùng.

Các ca local cho lõi thay đổi đã đạt: host 50/50→30/70 không có stack 3/4; widget 700×400→1200×500 cùng display ID và task bounds khớp; hủy host đang chờ; từ host mở app fullscreen; menu Cài đặt không nằm dưới rail. Kết quả local có ghi rõ giai đoạn trước các chỉnh sửa lề dock/owner cuối. APK từ CI được kiểm chứng lại và lưu cùng artifact cuối, không suy từ các bài native cũ rằng embedding đã hoạt động.

Mã fixture, dữ liệu trước sửa và local ở [hosted-runtime-64](hosted-runtime-64/). `HostProbe` kiểm tra stack/display/viewport thật; `Probe` ép `split_backend=native` để kiểm tra nhánh tương thích. Không có fixture hoặc stub ActivityView trong sản phẩm.

Chưa có APK Vietmap, dump window/renderer hay kết nối đầu xe để chứng minh engine Vietmap đã hết lỗi. Fixture xác nhận Surface/Display/task đúng kích thước, không thay thế phép thử app bản đồ thực tế. Các app không hỗ trợ embedding, mở Activity ra display khác hoặc giữ renderer riêng có thể cần xử lý tiếp. Chế độ native vẫn chịu giới hạn thanh hệ thống Android/OEM. Chưa tuyên bố giao diện giống từng pixel hoặc giữ được route sau mọi lần đóng/handoff.

## Bổ sung 0.3.65: vùng chừa thanh bên còn sót

Khi xem ảnh bản CI 64 thấy một vùng trái còn được chừa dù đang dùng nút nổi. Service sau khi bị kill/rebind có thể không còn `bar` View, nên điều kiện cleanup cũ `bar != null` bỏ qua overscan đã lưu. Bản bổ sung trả overscan THT về 0 khi khởi động ở mode khác `bar`, và khi service rebuild ở mode đó. Chỉ tác động phần overscan có tracker của THT, không tự xóa cấu hình màn hình không do THT ghi.

## Bổ sung 0.3.66: đặt thanh bên vào đúng vùng đã chừa

Ảnh kiểm chứng cho thấy WindowManager cũng cộng origin overscan vào chính cửa sổ accessibility overlay: thanh bên dự định ở x=0 lại nằm ở x=120. Dùng cùng phép đo/bù `getLocationOnScreen` cho thanh bên mở rộng để đưa nó về mép màn hình; Settings không cần chừa thêm một vùng trống bên trái. Thanh thu gọn vẫn dùng vị trí giữa mép trái như trước.
