# THT Launcher

Launcher Android cho màn hình trung tâm trên ô tô. Một bộ mã dùng chung, mỗi đời xe là một *profile* riêng.

## Tính năng

- Màn hình chính 3 cột, tối ưu cho màn ngang 1920×720
- Đồng hồ, ngày tháng
- Dữ liệu xe theo thời gian thực: tốc độ, số, mức xăng/pin, ODO, phanh tay
- Tự đổi giao diện ngày/đêm theo đèn xe
- Hiển thị và điều khiển nhạc đang phát từ bất kỳ app nào (Spotify, YouTube Music, Zing MP3, Bluetooth…)
- Danh sách app, tự cập nhật khi cài/gỡ app; giữ lâu để mở thông tin app
- Tự mở khi đầu xe khởi động

## Xe hỗ trợ

| Profile | Nhận diện | Ghi chú |
|---|---|---|
| `coolray_2025` | `Build.MODEL = SX11-LA`, `PRODUCT = IHU730P` | Android 9 (API 28), 1920×720 @160dpi |
| `ex2` | *(chưa điền)* | cần `adb shell getprop` để bổ sung |
| `generic` | mọi máy khác | chỉ có nhạc + danh sách app |

Thêm xe mới: tạo một `VehicleProfile` trong `vehicle/VehicleProfile.kt` và đưa vào danh sách `Profiles.all`.

## Dữ liệu xe

Đọc qua API chuẩn Android Automotive (`android.car`, `CarPropertyManager`) bằng reflection, nên APK vẫn chạy trên máy không có thư viện này. Mã thuộc tính nằm trong `CarPropertySource.kt`.

## Build

Mỗi lần push lên `main`, GitHub Actions tự build APK – tải ở tab **Actions → lần chạy mới nhất → Artifacts**.

Build trên máy: mở thư mục bằng Android Studio rồi chạy, hoặc `gradle :app:assembleDebug`.

## Cài lên xe

```
adb install -r app-debug.apk
```

Sau đó bấm nút Home trên màn hình xe, chọn **THT Launcher → Luôn luôn**.
Lần đầu chạm vào ô nhạc để cấp quyền *Truy cập thông báo*.

## Cấu trúc

```
app/src/main/java/vn/tht/launcher/
├── MainActivity.kt          màn hình chính
├── LauncherApp.kt           chọn profile khi khởi động
├── BootReceiver.kt
├── apps/AppsAdapter.kt      danh sách app
├── media/                   đọc & điều khiển nhạc
└── vehicle/                 profile xe + đọc dữ liệu xe
```
