# Hợp đồng tương thích OEM: Coolray 2025

Phạm vi: đọc tĩnh hai APK người dùng cung cấp để cấu hình THT Launcher. Không chạy APK, không gọi dịch vụ nguồn, không thay đổi cấu hình hệ thống và không suy ra giao thức Binder/extras khi không có mã triển khai.

## 1. Định danh hiện vật

| Hiện vật | Package / phiên bản | SHA-256 | Đặc điểm |
|---|---|---|---|
| APK quản lý nguồn | `ecarx.power.service`, versionCode 28, versionName 9 | `a9af15b4d7619bc0f4ea1e3927667e3694ccf6794e4ff10ae064b054b2f66572` | min/target API 28; 50 ZIP entries; không DEX, không native library |
| APK launcher OEM | `ecarx.launcher3`, versionCode 9008, `SY-NEUOverseas-SX11LaCarplay-20240816-AppPaneExitButton` | `ed76fe22239d21d46bd35d6a8d0bb22525b1380004b94a2bb63f9012828a2f61` | min API 21, target 28; 1.401 ZIP entries; không DEX; native libraries chỉ thấy C++ runtime và logging |

Không có `classes.dex`, `.odex` hay `.vdex` trong hai archive. Điều này phù hợp với APK hệ thống đã tách mã sang hiện vật tối ưu hóa ngoài APK; chưa chứng minh chính xác vị trí hoặc định dạng mã bị tách. Không thể giải biên dịch logic dịch vụ/launcher chỉ từ các file này. Resources, manifest, signature và declaration vẫn đọc được.

Không dùng hai APK này như thư viện code hoặc bundle chạy độc lập trong THT Launcher. Việc cùng package/class trong manifest không cung cấp class implementation.

## 2. Chữ ký và đặc quyền

Cả hai APK khai báo `android:sharedUserId="android.uid.system"` và cùng chứng thư SHA-256:

`c8a2e9bccf597c2fb6dc66bee293fc13f2fc47ec77bc6b2b0d52c11f51192ab8`

Android apksigner xác minh thành công: power dùng v3; launcher có v1/v2/v3. Đây là chứng thư platform test AOSP đã biết. Hai file cùng signer không thay thế phép kiểm tra chữ ký framework `android` trên thiết bị thật; THT phải tiếp tục dùng runtime `PackageManager.checkSignatures("android", ownPackage)` trước khi mở đường đặc quyền.

THT không nên thêm `sharedUserId=android.uid.system`, quyền REBOOT hoặc các quyền cuộc gọi chỉ để mô phỏng OEM. Các quyền này không tạo tính tương thích và có thể làm APK không cài được nếu danh tính không khớp.

REA signature observations:

- Power: `power/rea-signature.json`, Evidence `ev_97756885bf4bf5e0eda6bb8ec766cf462b8feb35f1db44f8e82fbf71b5625ceb`.
- Launcher: `oem-launcher/rea-signature.json`, Evidence `ev_c5a9e602ff21225ae24cbd6bac76a7e2b878a57cebb5c1768fa86c78492037a6`.
- Kiểm chứng mật mã độc lập: mỗi thư mục có `signatures.txt` từ Android apksigner. Không gộp declaration của REA với kết luận xác minh cryptographic signature.

## 3. Launcher OEM: component được khai báo

### Home / recovery

Component chính xác:

`ecarx.launcher3/ecarx.launcher3.Launcher`

Activity có MAIN + HOME + DEFAULT + MONKEY, landscape, singleInstance, resizeable=false. Không có category LAUNCHER ở activity này. Vì vậy `getLaunchIntentForPackage("ecarx.launcher3")` có thể không tìm thấy nó. Nút trở về launcher gốc nên dùng explicit ComponentName nêu trên sau khi kiểm tra activity tồn tại, enabled, exported và có thể resolve.

Exported không được ghi trực tiếp cho Home nhưng intent-filter trên target API28 tạo default exported=true. Đây là declaration, không xác nhận runtime package/component đang enabled.

### App pane / ứng dụng gốc

`ecarx.launcher3/ecarx.launcher3.AppPane` được khai báo exported=true, landscape, singleInstance, resizeable=true, process `:appsvr`. `ecarx.launcher3.apppane.AppPaneAssistActivity` cũng exported=true. Có thể cung cấp nút tùy chọn mở app pane OEM bằng explicit component với kiểm tra runtime và xử lý ActivityNotFound/SecurityException.

`ecarx.launcher3.SettingsActivity` không có intent-filter hoặc exported=true; default là không exported. Không dùng activity này làm fallback Settings bên ngoài khi chưa có xác nhận runtime cho phép.

### Services

| Component | Action | Quan sát và giới hạn |
|---|---|---|
| `ecarx.launcher3.apppane.AppPaneService` | `ecarx.launcher3.ACTION_APP_PANE_SERVICE` | exported=true, process `:appsvr`; chỉ biết service declaration, chưa biết Binder interface |
| `ecarx.multiwindow.SplitService` | `ecarx.launcher3.ACTION_SPLITE_SCREEN_SERVICE` | exported=true; chữ `SPLITE` là spelling thật trong manifest; không được tự tạo transaction code/extras |
| `ecarx.launcher3.app.KeepAliveService` | `com.ecarx.action.launcher3.KeepAliveService` | không thấy permission/exported literal; intent-filter có default kiểu API28; không biết protocol |
| `com.blankj.utilcode.util.MessengerUtils$ServerService` | `ecarx.launcher3.messenger` | exported=false; không phải API dành cho launcher thay thế |

Giữ đường chia đôi bằng API Android/firmware đã có của THT với kiểm tra quyền/khả năng và fallback. Service name không đủ để thay bằng một Binder client tự đoán.

### Providers và broadcast

LauncherProvider authority `ecarx.launcher3.settings`, exported=true, readPermission `ecarx.launcher3.permission.READ_SETTINGS`, writePermission `ecarx.launcher3.permission.WRITE_SETTINGS`. Các provider apppre/webpre có authority riêng. Chưa biết schema/query contract nên không đọc/ghi cơ sở dữ liệu OEM bằng phỏng đoán.

Manifest khai báo protected broadcast `ecarx.intent.action.SMART_CARD_RECEIVER`. Đây không phải bằng chứng về wake/sleep event và không nên phát nó từ THT để giả thông báo hệ thống.

## 4. Package ứng dụng hệ thống từ tài nguyên workspace

Nguồn phù hợp với nhãn build NEU: `res/xml/neu_ecarx_workspace_4x2.xml`. Chưa có mã chọn profile nên không khẳng định đây chính là workspace đang dùng trên xe. Tất cả tên dưới đây là candidate khai báo trong tài nguyên; sự hiện diện/hoạt động trên đầu xe phải kiểm tra PackageManager.

| Vai trò | Candidate trong workspace NEU | Cách mở phù hợp |
|---|---|---|
| Dẫn đường | `com.autonavi.amapauto` | launch Intent của package nếu cài; ưu tiên app người dùng đã chọn |
| Media gốc | `com.ecarx.multimedia` | launch Intent đã resolve; không suy ra được action chọn tab radio |
| Điện thoại Bluetooth | `com.ecarx.btphone` | launch Intent package; không cần thêm CALL_PHONE chỉ để mở giao diện |
| Cài đặt đầu xe | `com.neusoft.optimus.wheeljack.setting` | launch Intent package |
| Thông tin xe | `com.ecarx.mycar` | launch Intent package |
| Theme gốc | `com.ecarx.thememanager` | launch Intent package |
| Camera | `com.neusoft.avm` | chỉ shortcut mở package người dùng chọn; không giả lập trigger số lùi |
| DVR | `com.neusoft.dvrlogin` | launch Intent package |
| Kho ứng dụng | `com.ecarx.appstore` | launch Intent package |

Tài nguyên workspace generic bổ sung:

- HVAC `ecarx.hvac.app`.
- Settings `ecarx.settings`, class khai báo trong XML `ecarx.settings.setting.CarSettingActivity`.
- Vehicle assistant `com.neusoft.ssp.ces.c4.car.assistant`.
- Các model khác dùng `com.ecarx.settings`, `com.desay_svautomotive.settings`, `com.ecarx.parking`, `com.desaysv.avm`, `com.desaysv.dvr`; đây không phải bằng chứng đầu Coolray hiện có các package đó.

Không thấy package radio độc lập trong workspace NEU. Giữ radio adapter có sẵn từ dữ liệu GitHub, nhưng không ghi nhận nó như một protocol mới đã được hai APK này xác nhận.

Widget declarations gồm media `ecarx.xsf.widget.MediaWidgetProvider3x1`, navigation `com.ecarx.naviwidget.widget.BaseWidgetProvider1x2`, weather `ecarx.home.module.widget.WeatherWidgetProvider1X2` trong `com.ecarx.smartcloud`, và CarSettingWidget của `ecarx.settings`. Đây là AppWidget provider candidates, không phải Activity launch targets. Chỉ host chúng khi AppWidgetManager trả installed provider thực tế và đã được cấp bind permission/user consent.

`oem-launcher/workspace-contracts.json` lưu resource gốc và candidate/class cho tất cả workspace variants đã đọc, để không trộn bằng chứng giữa các model.

## 5. Power service: giữ chủ quyền quản lý nguồn OEM

Power application persistent=true, directBootAware=true, process `ecarx.power.service`. Các services:

| Component | Start action | Category |
|---|---|---|
| `ecarx.power.service.ECarXPowerManagerService` | `android.intent.action.START_ECARX_POWER_MANAGER_SERVICE` | `android.intent.category.ECARXPOWER` |
| `ecarx.power.service.screen_projection.ECarXScreenProjectionService` | `android.intent.action.START_ECARX_SCREEN_PROJECTION_SERVICE` | `android.intent.category.ECARX_SCREEN_PROJECTION` |
| `ecarx.power.service.ecall_screen.ECarXECallScreenService` | `android.intent.action.START_ECARX_ECALL_SCREEN_SERVICE` | `android.intent.category.ECARX_ECALL_SCREEN` |
| `ecarx.power.service.BTPhoneServiceImpl` | `android.telecom.InCallService` | permission `android.permission.BIND_INCALL_SERVICE`, exported=true |

Các start action không chứng minh wake/sleep broadcast protocol. Không thể suy ra extras, Binder transactions hoặc return values từ manifest. Không gọi các service này để giữ launcher sống, không force-stop/reboot hoặc vẽ đè các UI an toàn.

Resources cho thấy OEM có day/night dialog, cảnh báo pin yếu, nhiệt cao, PEPS/key warning, secure dialog, FOTA và close-window layout. Đây là bằng chứng về tài nguyên, chưa xác định đầy đủ điều kiện kích hoạt. Giữ OEM power/phone/emergency UI và để Activity lifecycle của THT xử lý pause/resume theo Android.

THT có thể dùng các lifecycle chuẩn SCREEN_OFF/SCREEN_ON/USER_PRESENT/ON_RESUME để ngừng polling/timer giao diện, refresh dữ liệu sau thức dậy và reconnect adapter. Không tự gắn tên event vendor chưa thấy trong bằng chứng. Nếu thêm receiver, đăng ký/hủy theo lifecycle và xử lý quyền phù hợp API28.

## 6. Tương thích profile và kích thước

Build string và Tinker metadata chứa SX11La/SX11_LA, NEUOverseas và Carplay. Metadata `minAdaptAPIVersion` là `1.1.17`. Không dùng chuỗi tên build của APK làm khẳng định `Build.MODEL`/`Build.PRODUCT` runtime. Giữ dữ liệu profile Coolray SX11-LA/IHU730P có sẵn trên GitHub và đối chiếu thực tế khi có báo cáo trên xe.

Resources có nhiều configuration variants. Default `app_panel_height` là720dp, `app_panel_width`1760dp, translateX160dp; đây phù hợp hình học app-pane rộng có vùng cạnh nhưng không đủ chứng minh display thật1920x720 hoặc insets của cấu hình đang chạy. Kích thước1920x720@160dpi tiếp tục là dữ liệu profile đã có, không phải thông tin DEX mới.

Không thay toàn bộ UI của launcher tham chiếu bằng layout launcher OEM: mục tiêu UI do hồ sơ tham chiếu quyết định, hai APK OEM cung cấp hợp đồng tương thích đầu xe.

## 7. Quy tắc triển khai cụ thể

1. Candidate resolver ưu tiên lựa chọn người dùng, sau đó package OEM đã có bằng chứng, cuối cùng Android settings/package chooser. Kiểm tra installed/enabled/exported và catch SecurityException.
2. Recovery Home sử dụng explicit component được xác nhận; không đổi default Home bằng lệnh shell/ghi settings không có user choice.
3. Không tắt/chiếm power, projection hoặc eCall services. Không thêm signature/system UID chỉ từ việc APK OEM dùng chúng.
4. Giữ kiểm tra platform signature, capability và an toàn của EcarxController trong repo; không biến đổi property ID vì kiểm tra integrity thất bại.
5. Binder/services chưa có mã chỉ hiển thị trong chẩn đoán với trạng thái chưa kiểm chứng. Không gửi transaction hoặc extras suy đoán.
6. Build/test trên máy giả chỉ kiểm tra UI/fallback; không báo đã điều khiển xe thành công khi chưa có headunit.

## 8. Danh mục bằng chứng

- `{power,oem-launcher}/inventory.json`: byte size, ZIP entry count, DEX/native inventory và hash của APK.
- `{power,oem-launcher}/manifest.txt`: `aapt dump xmltree AndroidManifest.xml`.
- `{power,oem-launcher}/badging.txt`: `aapt dump badging`.
- `{power,oem-launcher}/signatures.txt`: `apksigner verify --verbose --print-certs`.
- `{power,oem-launcher}/rea-signature.json`: REA built-in signature observations.
- `{power,oem-launcher}/resources.txt`: resource table dump.
- `{power,oem-launcher}/component-contracts.json`: structured declaration extraction hỗ trợ; không phải runtime observation hoặc REA Evidence riêng.
- `oem-launcher/res_xml_*.txt`, `res_layout_*.txt`: selected decoded resource XML.
- `oem-launcher/workspace-contracts.json`: package/widget candidates và provenance resource.

Các file hỗ trợ được tạo bằng công cụ tĩnh; không có protocol trace, runtime permission dump, logcat hoặc ODEX/VDEX trong phạm vi đầu vào hiện tại.
