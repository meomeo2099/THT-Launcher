# Sơ đồ làm việc của THT Launcher

Đầu xe Geely Coolray 2025: ECARX SX11-LA (IHU730P), Android 9 API 28, màn 1920×720 @160 dpi, AdaptAPI 1.1.18.9.
Xem thêm: [ecarx-reference.md](ecarx-reference.md) (mã chức năng, cảm biến, thuộc tính xe).

## 1. Toàn cảnh: dữ liệu xe → launcher → màn hình

Mọi nguồn dữ liệu xe đổ về `MainActivity`, từ đó phát cho widget, các thanh và lớp nổi. Lệnh điều khiển đi ngược lại
qua `EcarxController` và chỉ ghi được khi APK ký đúng khoá platform.

```mermaid
flowchart LR
  subgraph XE["Đầu xe ECARX"]
    ADAPT["AdaptAPI<br/>/system/framework/*.jar"]
    VHAL["android.car VHAL<br/>xăng · ODO · tốc độ · 0x287000E4 điện áp"]
    MC["ecarx.xsf.mediacenter<br/>nhạc / đài đang phát"]
    RS["com.ecarx.radio.service<br/>tần số · băng · phát"]
    HV["ecarx.hvac.app<br/>popup điều hoà gốc"]
    MS["MediaSession<br/>Spotify, Zing MP3…"]
  end
  subgraph NGUON["Lớp đọc dữ liệu (vehicle / media / radio)"]
    ECX["EcarxCarSource<br/>connect → onConnected"]
    CPS["CarPropertySource<br/>mỗi 0,5 s"]
    MCS["MediaCenterSource"]
    MW["MediaWatcher"]
    ER["EcarxRadio"]
  end
  subgraph LOI["Lõi"]
    MA(["MainActivity<br/>trung tâm"])
    CTL["EcarxController<br/>ghi + đọc lại xác nhận"]
    SIG{{"PlatformSignature<br/>ký khoá platform?"}}
  end
  subgraph GD["Giao diện"]
    HC["HomeCanvas<br/>widget kéo / đổi cỡ"]
    BAR["Dock · thanh trên · thanh dọc<br/>cụm điều hoà · cụm quạt"]
    POP["ClimatePopup · ControlCenter<br/>Dialogs · IconList"]
  end
  subgraph NOI["Lớp nổi trên app khác"]
    NAV["NavAssistService<br/>nút ← ⌂ · đóng popup gốc"]
    TMP["TempInfoOverlay<br/>khung nhiệt độ"]
    FD["FloatingDock · SplitScreen"]
  end
  ADAPT --> ECX
  VHAL --> CPS
  MC --> MCS --> MW
  MS --> MW
  RS --> ER
  ECX -- "car-io, 2 s" --> MA
  CPS --> MA
  MW --> MA
  ER --> HC
  MA --> HC & BAR & POP
  MA --> NAV & TMP & FD
  HC & BAR & POP -- "lệnh" --> CTL
  CTL --> SIG
  SIG -- "khớp" --> ECX
  HV -.-> NAV
```

## 2. Một vòng cập nhật

Đọc ECARX chạy trên luồng phụ `car-io`. Khi đang mở app khác, launcher chỉ ghi chuyến đi và chạy tự động hoá
(cộng 3 giá trị điều hoà mỗi giây nếu bật khung nhiệt độ).

```mermaid
sequenceDiagram
  autonumber
  participant Xe as Đầu xe
  participant IO as Luồng car-io
  participant MA as MainActivity
  participant W as Widget / thanh
  participant S as Thống kê · Tự động hoá
  Xe->>MA: VHAL mỗi 0,5 s (xăng, tốc độ, điện áp…)
  MA->>S: ghi chuyến đi, điện áp, phát hiện đổ xăng
  alt Launcher đang hiện
    MA->>W: cập nhật (chỉ vẽ lại khi giá trị đổi)
    IO->>Xe: đọc điều hoà, quạt, ghế… (2 s)
    IO-->>MA: EcarxInfo
    MA->>W: cụm điều hoà, cụm quạt, ô số liệu
  else Đang mở app khác
    IO->>Xe: nhiệt độ, quạt, hướng gió (1 s, nếu bật)
    IO-->>MA: thay đổi
    MA->>W: khung nhiệt độ nổi 3,5 s
  end
  W->>MA: người dùng bấm (vd. quạt +)
  MA->>Xe: setFunctionValue qua EcarxController
  Xe-->>MA: đọc lại để xác nhận
```

## 3. Từ mã nguồn lên xe

Mỗi việc một nhánh → pull request → CI → merge vào `main`. CI ký bản release bằng keystore trong secret `THT_SIGN_*`
và tạo bản phát hành `build-N`; launcher trên xe tự cập nhật khi chữ ký khớp.

```mermaid
flowchart LR
  A["Nhánh tính năng"] --> B["Pull request"]
  B --> C{"CI: kiểm thử +<br/>build debug, release"}
  C -- "lỗi" --> A
  C -- "đạt" --> D["Merge vào main"]
  D --> E["CI ký bằng THT_SIGN_*<br/>cert c8a2e9bc…"]
  E --> F["GitHub Release<br/>build-N · THT-Launcher-0.2.N-release.apk"]
  F --> G["Updater trên xe<br/>kiểm tra bản mới"]
  G --> H{"Chữ ký APK<br/>trùng bản đang cài?"}
  H -- "trùng" --> I["PackageInstaller cài đặt"]
  H -- "khác" --> J["Từ chối"]
```

## Các phần chính trong mã

| Thư mục | Việc làm |
|---|---|
| `vehicle/` | Kết nối ECARX (nạp jar hệ thống, chờ kết nối), đọc VHAL, mã chức năng, ghi có xác nhận, kiểm tra chữ ký platform, xe giả cho bản debug |
| `home/` | Khung widget toàn màn hình, các loại widget (nhạc, bản đồ nhúng, radio, ảnh vùng chạm, widget Android), cụm điều hoà và quạt |
| `shortcuts/` | Dock, thanh trạng thái phía trên, thanh dọc, dock nổi trên app |
| `split/` | Chia đôi màn hình: cặp app trái/phải, thanh kéo tỉ lệ |
| `media/`, `radio/` | Bài đang phát (MediaSession và trình phát gốc của xe), điều khiển radio trực tiếp |
| `controls/` | Cửa sổ điều hoà, trung tâm điều khiển |
| `automation/`, `stats/` | Tự động hoá sau khi nổ máy; chuyến đi, đổ xăng, bảo dưỡng, điện áp ắc quy |
| `system/` | Trợ năng (nút ← ⌂, đóng popup gốc, khung nhiệt độ), màn hình chờ, dọn dẹp RAM, tự mở app, thời tiết, tự cập nhật |
| `ui/`, `settings/` | Màu theme ngày/đêm, icon, font, hộp thoại chung; màn Cài đặt, sao lưu, chẩn đoán |

## Kiểm chứng trên xe

Các phần cần dịch vụ của xe mới thử trên emulator với xe giả. Trên xe: Cài đặt → Xe → **Chẩn đoán** →
**Lưu báo cáo đầy đủ** (lưu file .txt, ví dụ vào USB) rồi gửi file để đối chiếu.
