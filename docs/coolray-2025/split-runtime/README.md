# Kiểm thử native split trên máy ảo

`fixture/src` chứa hai activity resizable, không có quyền điều khiển xe; `Probe` là instrumentation thử nghiệm chạy trên target THT. Fixture không nằm trong sản phẩm.

Môi trường dùng AOSP API 28 1920×720, mật độ 160 dpi, có `am supports-split-screen-multi-window`; THT và fixture được ký cùng khóa thử nghiệm platform công khai khớp framework máy ảo. Không dùng khóa thử nghiệm thay chữ ký OEM trên xe. Không có khóa ký hoặc APK fixture trong thư mục này.

## Tái lập

1. Build THT release và ký phù hợp với **máy ảo thử nghiệm**; cho phép overlay, chọn dock luôn hiển thị và tắt cập nhật tự động trong prefs thử nghiệm. Giữ nguyên kích thước 1920×720 để assertion của Probe phù hợp.
2. Biên dịch Java của từng package với `android.jar`, chuyển JAR thành `classes.dex` bằng d8. Dùng aapt đóng gói manifest tương ứng (đổi basename thành `AndroidManifest.xml` trong thư mục tạm), thêm `classes.dex`, ký APK bằng cùng chứng thư với THT và cài hai fixture.
3. Chạy từng ca, đợi ca trước kết thúc:

```sh
adb shell am instrument -w -e scenario visual vn.tht.fixture/.Probe
adb shell am instrument -w -e scenario cancel vn.tht.fixture/.Probe
adb shell am instrument -w -e scenario release vn.tht.fixture/.Probe
adb shell am instrument -w -e scenario fullscreen vn.tht.fixture/.Probe
adb shell am instrument -w -e scenario settings vn.tht.fixture/.Probe
```

Probe dùng reflection vào build THT được thử, đọc `getStackInfo(3,1)`/`getStackInfo(4,1)` và xác nhận task/bounds. Đọc `INSTRUMENTATION_RESULT: result=PASS`; riêng exit code lệnh hoặc `INSTRUMENTATION_CODE: 0` không chứng minh đạt. `visual` dừng ngắn ở các trạng thái để chụp ảnh. Test phải được cập nhật nếu đổi tên API nội bộ hoặc cấu hình màn hình.

## Dữ liệu

- File `probe-before-*` và `emulator-crash-before-fix.txt` ghi lỗi đã phát hiện trong quá trình sửa, không tính là kết quả đạt.
- File `probe-final-*`, `results.json`, `unit-tests.json` và ảnh là bằng chứng bản kiểm chứng cuối.
- Không phải kiểm thử bản đồ thật, ECARX hoặc Coolray thật. Khôi phục sau hard kill/mất điện chưa được bảo đảm bởi metadata trong bộ nhớ.
