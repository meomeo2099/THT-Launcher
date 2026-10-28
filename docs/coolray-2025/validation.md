# Kiểm chứng bản 0.3

Ngày kiểm tra: 2026-10-28. Môi trường: Linux, JDK 17, Gradle 8.9, Android SDK 34; min/target API 28.

## Build và unit test

Lệnh: `gradle :app:testDebugUnitTest :app:assembleDebug :app:assembleRelease -Pkotlin.compiler.execution.strategy=in-process`.

30 kiểm thử, 0 lỗi, 0 bỏ qua: decoder 6; profile 2; hình học/khôi phục layout 5; chu kỳ tự động hóa và giới hạn nhiệt độ/tốc độ 7; lịch khởi động app 4; đường dẫn/giới hạn archive 5; độ mới và hợp lệ tốc độ GPS 1. [Tổng hợp JUnit](unit-test-summary.json).

Các test tập trung vào chính sách thuần và dữ liệu, chưa kiểm tra transaction ECARX hoặc toàn bộ giao dịch sao lưu Android. Cả hai APK cục bộ đã qua `apksigner verify` (v2); [hash/chữ ký cục bộ](local-apk-validation.json). APK release cục bộ dùng khóa debug dự phòng, không phải bằng chứng tương thích chữ ký đầu xe. GitHub Actions dùng signing secrets đã cấu hình của repository, kiểm tra chữ ký và lưu cả APK lẫn báo cáo test thành artifact.

## Máy ảo và giới hạn

Máy ảo AOSP Android 9 x86, 1920×720, density 160, software GPU; không có ECARX/ứng dụng OEM hoặc media session hoạt động. Các giá trị xe chưa đọc được phải hiển thị dấu gạch thay vì số giả. Không bật chế độ demo trong ảnh kiểm chứng.

Máy ảo từng báo System UI không phản hồi khi chạy đồng thời trình biên dịch. Đây là hạn chế môi trường kiểm tra; không được dùng lần chạy này để đánh giá độ mượt trên đầu xe.

Chưa có kiểm thử trên xe thật: chữ ký framework, tốc độ/gear, điều hòa/ghế/cửa sổ trời, media OEM, wake/sleep, camera lùi, nhiều app và nâng cấp từ bản cài thực tế vẫn cần xác nhận. Chưa có đối chiếu từng pixel với ảnh ứng dụng tham chiếu đang chạy; các màn hình phụ chưa hoàn toàn tương đương.

Quét các file văn bản mới/thay đổi bằng package và nhãn sản phẩm gốc từ badging APK tham chiếu: không thấy khớp. Phép quét này không khẳng định loại bỏ mọi dấu vết nhận diện gián tiếp.
