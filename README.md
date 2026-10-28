# THT Launcher

Launcher Android cho màn hình trung tâm trên ô tô, tối ưu cho Geely Coolray 2025 (đầu ECARX `SX11-LA` / `IHU730P`, Android 9, 1920×720 @160dpi). Một bộ mã dùng chung, mỗi đời xe là một *profile* riêng.

## Tính năng

**Màn hình chính dạng lưới, sửa được ở bất kỳ đâu**
- Khung widget phủ toàn màn hình. Nhấn giữ để vào chế độ sửa: kéo để di chuyển, kéo chấm xanh để đổi cỡ, chạm một widget để nhập chính xác X/Y/rộng/cao, nút × để xoá, "+ Thêm widget" mở thư viện (chia nhóm, có icon).
- Widget: đồng hồ lớn / đồng hồ dạng thẻ (kèm thời tiết), nhạc (ngang hoặc dọc, có tiến trình), dẫn đường, radio, điều hoà, tốc độ & số, xăng, chế độ lái & đèn, chuyến đi, ô nhỏ (xăng, điện áp, nhiệt độ máy, quãng đường, Bluetooth, Wi-Fi), thanh trượt độ sáng / âm lượng, lối tắt ứng dụng.
- Các thanh cũng là widget: thanh trạng thái trên, thanh dưới (dock), thanh dọc, cụm điều hoà gọn.
- Nút ⌄⌄ ẩn tất cả (chỉ còn nền + thanh dưới), ⌃⌃ để hiện lại.
- Chữ số trong widget tự co giãn theo kích thước; tỉ lệ toàn giao diện 75–150%.

**Dock / thanh**
- Tối đa 20 nút: app, điều khiển xe, điều hoà gốc, ngăn kéo ứng dụng, cài đặt, thống kê, dọn dẹp, ẩn/hiện, THT Voice, ô trạng thái sống (Wi-Fi, nhiệt độ máy, xăng lít, điện áp, sưởi ghế, Home). Nhấn giữ một nút để di chuyển/xoá.

**Xe** (xem mục Dữ liệu xe và Điều khiển xe)
- Đọc: tốc độ, số, xăng (%, lít), ODO, phanh tay, khoá điện, vòng tua, nhiệt độ nước làm mát, nhiệt độ ngoài, quãng đường còn lại; trạng thái điều hoà, chế độ lái, đèn viền qua ECARX.
- Trung tâm điều khiển 4 tab (Điều hoà · Ghế & sưởi · Xe · Đèn & màn hình) và popup Điều hoà; chỉ hiện nút xe hỗ trợ.
- Tự động hoá sau khi nổ máy (sưởi/làm mát ghế, sưởi vô lăng, xả nóng, AUTO, đóng cửa sổ trời theo tốc độ).
- Thống kê chuyến đi, đổ xăng, tiêu hao, nhắc bảo dưỡng.

**Giao diện & hệ thống**
- Theme ngày/đêm (tự động theo đèn xe), màu nhấn, nền vẽ bằng code (núi, gradient) hoặc ảnh riêng, độ trong suốt widget/dock.
- Bộ icon Material Design Icons; nạp được bộ icon ngoài (.zip, PNG theo tên + manifest mono/color), chỉ lưu trên máy.
- Thời tiết (Open-Meteo, không cần khoá API).
- Nút Quay lại / Màn hình chính trên mọi app (dịch vụ trợ năng): viên nút nổi hoặc cử chỉ vuốt ở mép dưới.
- Dọn dẹp app chạy nền, bảo vệ màn hình, toàn màn hình, tự mở app khi khởi động, tự phát nhạc, sao lưu/khôi phục cài đặt.

## Xe hỗ trợ

| Profile | Nhận diện | Ghi chú |
|---|---|---|
| `coolray_2025` | `Build.MODEL = SX11-LA`, `PRODUCT = IHU730P` | Android 9 (API 28), 1920×720 @160dpi |
| `ex2` | *(chưa điền)* | cần `adb shell getprop` để bổ sung |
| `generic` | mọi máy khác | nhạc, app, thời tiết; không có dữ liệu xe |

Thêm xe mới: tạo một `VehicleProfile` trong `vehicle/VehicleProfile.kt` và đưa vào `Profiles.all`.

## Dữ liệu xe

- **VHAL chuẩn** (`android.car`, `CarPropertyManager`) qua reflection, mã thuộc tính trong `CarPropertySource.kt`. Thuộc tính xe không có thì không hiện.
- **ECARX AdaptAPI 1.1.18.9** (`Car.create(context).getICarFunction()`, xem `EcarxCarSource.kt`). Function ID trong `EcarxFunctions.kt`, lấy từ `Coolray2025-Voice-Spec.md`. Một function chỉ được dùng khi xe báo `active`.
- Điện áp ắc quy: thử đọc từ `ACTION_BATTERY_CHANGED` (chỉ nhận 9–16 V), chưa kiểm chứng trên Coolray.

## Điều khiển xe

Phần ghi **chỉ mở** khi `PackageManager.checkSignatures("android", vn.tht.launcher)` báo khớp, tức APK ký cùng chứng chỉ với firmware đầu xe (bản release CI ký bằng khoá platform test AOSP `c8a2e9bc…`). Bản debug luôn ở chế độ chỉ đọc. Mỗi lệnh kiểm tra xe hỗ trợ, kiểm tra an toàn (dưới 80 km/h với kính/cửa sổ trời; đứng yên với chế độ lái, gương; số P với cốp), ghi rồi đọc lại, chỉ báo ✔ khi xe đã đổi thật.

Các tính năng cần quyền hệ thống: điều khiển xe, bản đồ nhúng trong ô Dẫn đường (`ActivityView`), buộc dừng app khi dọn dẹp, tự bật dịch vụ Quay lại/Home, đổi độ sáng hệ thống.

## Chế độ demo (bản debug)

Trên máy không có ECARX (máy ảo, điện thoại): *Cài đặt → Gỡ lỗi → Chế độ demo* dùng một xe giả để xem và thử giao diện. Lệnh chỉ ghi vào bộ nhớ, không bao giờ tới xe thật.

## Build

GitHub Actions tự build APK khi push lên `main` hoặc có pull request – tải ở **Actions → lần chạy → Artifacts**. Bản release ký bằng keystore từ secrets `THT_SIGN_*`.

Build trên máy: JDK 17 + Gradle 8.x, `gradle :app:assembleDebug` (cần `local.properties` trỏ tới Android SDK).

## Cài lên xe

```
adb install -r app-release.apk
```

Sau đó bấm Home, chọn **THT Launcher → Luôn luôn**. Lần đầu:
- chạm ô nhạc để cấp quyền *Truy cập thông báo*;
- *Cài đặt → Điều hướng → Dịch vụ điều hướng* để có nút Quay lại/Home trên mọi app.

Kiểm tra trên xe:

```
adb logcat -s EcarxCarSource PlatformSignature EmbeddedMap Weather Cleaner
```

Dòng `PlatformSignature` cho biết chữ ký có khớp firmware (mở phần điều khiển) hay không.

## Cấu trúc

```
app/src/main/java/vn/tht/launcher/
├── MainActivity.kt        màn hình chính, nối mọi phần
├── home/                  khung widget (HomeCanvas), các widget, cụm điều hoà, bản đồ nhúng
├── shortcuts/             dock / thanh (DockBar), lưu shortcuts.json, topbar.json, siderail.json
├── controls/              Trung tâm điều khiển, popup Điều hoà
├── vehicle/               dữ liệu xe (VHAL, ECARX), điều khiển, chữ ký platform, xe demo
├── automation/            tự động hoá sau khi nổ máy
├── stats/                 chuyến đi, đổ xăng, màn Thống kê
├── settings/              Cài đặt, sao lưu
├── system/                thời tiết, dọn dẹp, tự mở app, bảo vệ màn hình, dịch vụ Quay lại/Home
├── ui/                    bảng màu, icon, hộp chọn có icon, nền vẽ bằng code
└── media/                 đọc & điều khiển nhạc
```

Thành phần bên thứ ba: xem `docs/THIRD_PARTY.md`.

## Hồ sơ phân tích tham chiếu

[Tài liệu phân tích bằng REA](docs/reference-analysis/README.md) tổng hợp kiến trúc, điều khiển xe, tự động hóa, cập nhật, GPS, dữ liệu, bảo mật và bằng chứng đối chiếu của một ứng dụng tham chiếu. Hồ sơ dùng tên trung tính và tách rõ quan sát tĩnh với hành vi chưa kiểm chứng trên firmware; mã phục hồi nằm trong tài liệu, không thuộc mã chạy của THT Launcher.
