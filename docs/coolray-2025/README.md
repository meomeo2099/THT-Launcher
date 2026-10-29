# THT Launcher 0.3 cho Coolray 2025

Mã hiện tại kết hợp hồ sơ phân tích ứng dụng tham chiếu, dữ liệu ECARX đã có trong repository và hai APK OEM người dùng cung cấp. Tên ứng dụng tham chiếu được giữ trung tính trong mã và tài liệu mới.

## Nguồn và bằng chứng

- [Hồ sơ tham chiếu đã công bố](../reference-analysis/README.md): kiến trúc, giao diện, điều khiển, tự động hóa và các giới hạn của phân tích tĩnh.
- [Hợp đồng OEM](oem-analysis.md): manifest, chữ ký, Home activity, package ứng dụng và quản lý nguồn.
- [Power inventory](power/inventory.json), [launcher inventory](oem-launcher/inventory.json), [workspace contracts](oem-launcher/workspace-contracts.json): dữ liệu đối chiếu từ hai APK.
- [Nguồn tài nguyên giao diện và giấy phép font](../../app/src/main/assets/reference/ASSETS.md).

Hai APK OEM không chứa DEX. Chưa có ODEX/VDEX, trace dịch vụ, đầu xe hoặc ảnh runtime OEM để xác minh luồng nguồn/ngủ/thức. Không gửi Binder transaction suy đoán tới dịch vụ nguồn, projection hoặc eCall.

## Phạm vi triển khai

| Phần | Đã triển khai | Giới hạn cần kiểm tra |
|---|---|---|
| Trang chính | Tọa độ tham chiếu 1280×720, co sang 1920×720; 10 widget, dock bảy mục | Chưa đối chiếu ảnh từng pixel với mẫu đang chạy |
| Hình thức | Nền ngày/đêm, màu thẻ, bo góc, Manrope, Elastre, 64 icon | Các màn hình phụ còn dùng thành phần THT |
| Nhạc | Session đang hoạt động, album, tiến trình, seek khi session cho phép | Phụ thuộc quyền thông báo và player |
| OEM | Resolve activity có kiểm tra enabled/exported; quay về Home gốc | Package thực tế và quyền cần xác nhận trên xe |
| Điều khiển | Giữ ECARX IDs có sẵn, kiểm tra chữ ký/capability/an toàn, ghi và đọc lại | Không chứng minh hai APK cung cấp toàn bộ giao thức xe |
| Tự động hóa | Chu kỳ động cơ, hủy callback cũ, kiểm tra lại trên worker, tắt hẹn giờ khi mức hiện tại vẫn trùng mức đã bật | ECU, lịch đánh thức và hành vi firmware chưa kiểm thử |
| Tốc độ GPS | Tùy chọn cho đóng cửa sổ trời; chỉ mẫu thật còn mới ≤5 giây, có quyền vị trí chính xác | Không thay trạng thái động cơ; giới hạn chạy nền Android vẫn áp dụng |
| Khởi động app | Theo boot/chu kỳ động cơ, chỉ package được người dùng chọn | Autoplay phụ thuộc media session/browser của player |
| Sao lưu | ZIP v2, đọc JSON v1, whitelist, giới hạn giải nén, staging và rollback | Không bảo đảm giao dịch nhiều file nguyên tử khi mất điện đột ngột |
| Dữ liệu cũ | Giữ layout/dock tùy chỉnh; chọn preset mới trong cài đặt | Không tự ghi đè bố cục đã lưu |

Không tự cài hoặc thay launcher mặc định trên xe. Nút mở cài đặt Home cho người dùng chọn. APK debug chỉ đọc trên xe; APK release chỉ được ghi khi chữ ký khớp framework và chức năng được firmware hỗ trợ.

## Kiểm chứng

Kết quả build, kiểm thử và ảnh máy ảo được ghi trong [validation.md](validation.md). Máy ảo Android 9 1920×720 dùng để kiểm tra UI và fallback, không mô phỏng ECARX hoặc kết nối xe thật.

## Sửa mở ứng dụng toàn màn hình

[Đối chiếu cơ chế tham chiếu, nguyên nhân và bản sửa bản đồ/Home](map-fullscreen-fix.md), theo phản hồi trên xe ngày 29/10/2026.
