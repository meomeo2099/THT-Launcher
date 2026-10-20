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

Trên đầu ECARX còn đọc thêm qua ECARX AdaptAPI (`EcarxCarSource.kt`, chỉ đọc). Nhiệt độ ngoài trời và quãng đường còn lại chỉ hiện khi xe báo function đó là `active`. Dò toàn bộ catalog: `adb logcat -s EcarxCarSource`.

## Thanh lối tắt

Đầu cột app có 4 lối tắt: Camera 540°, Điều hoà, CarPlay, Cài đặt xe. Cấu hình nằm ở:

```
/sdcard/Android/data/vn.tht.launcher/files/shortcuts.json
```

Lần đầu chạy app sẽ tạo file mặc định. Lối tắt chưa điền package sẽ hiện mờ. Sửa file:

```
adb pull /sdcard/Android/data/vn.tht.launcher/files/shortcuts.json
adb push shortcuts.json /sdcard/Android/data/vn.tht.launcher/files/shortcuts.json
```

Về màn hình chính là app đọc lại file. Mỗi mục có các trường:

| Trường | Ý nghĩa |
|---|---|
| `type` | `activity` (mở `package`/`activity`; bỏ trống `activity` thì mở app), `action` (intent action), `hvac` (điều hoà gốc) |
| `package`, `activity` | ví dụ `"package": "com.ecarx.avm", "activity": ".MainActivity"` (tên chỉ là ví dụ) |
| `service` | chỉ dùng cho `hvac`: service cần bind; bỏ trống thì app tự dò |

Lấy package/activity thật trên xe: `adb logcat -s ShortcutBar` (in các activity có tên gợi ý như camera, carplay, setting), và `adb logcat -s HvacOpener` (in các service của app điều hoà).

Nút Điều hoà bind vào service của `ecarx.hvac.app` rồi gọi transaction 1. Không bind được thì mở app điều hoà như app thường. Cả hai đều lỗi thì chỉ báo một dòng, launcher không bị crash.

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
