# Tham khảo dữ liệu xe ECARX (Coolray 2025 / SX11-LA)

Tài liệu tương thích do THT Launcher tự ghi lại. Nguồn: đọc tĩnh bằng `dexdump` file `classes.dex`
của một launcher bên thứ ba chạy trên cùng đầu xe, đối chiếu với mã của THT Launcher. Ở đây chỉ ghi
**số hiệu, cách gọi API và quy tắc giải mã**. Không có mã nguồn, hình ảnh, chuỗi giao diện hay tài sản
nào của ứng dụng đó, và không phân tích thư viện native của họ.

Mức tin cậy:
- **Quan sát**: thấy trực tiếp trong bytecode (tên hàm, mã số, tham số).
- **Suy luận**: đoán từ ngữ cảnh (khoảng giá trị, nhãn log); cần kiểm chứng trên xe.

Mọi mã số đều phải kiểm chứng trên xe bằng `isFunctionSupported` (ECARX) hoặc `getPropertyList` (VHAL)
trước khi dùng.

## 1. Kết nối ECARX AdaptAPI (quan sát)

1. AdaptAPI **không** có trong classpath của app thường (manifest chỉ khai báo `android.car`).
   Thử ClassLoader của app trước; không có thì tạo `PathClassLoader` (cha là ClassLoader của app) với
   `/system/framework/AdapterAPI.jar:/system/framework/AdapterAPIImpl.jar:/system/framework/ecarx.jar:/system/framework/ecarxSignalAPI.jar`.
2. Tạo đối tượng xe: `com.ecarx.xui.adaptapi.car.impl.CarImpl(Context)` (hoặc `Car.create(Context)`).
3. Nếu là `com.ecarx.xui.adaptapi.binder.IConnectable`: `registerConnectWatcher(IConnectable$IConnectWatcher)`
   rồi `connect()`. Chỉ gọi `getICarFunction()` trong `onConnected()`; `onDisConnected()` thì nối lại.
4. (Tuỳ chọn) `ICarFunction.registerFunctionValueWatcher(IFunctionValueWatcher, int[] ids)` để nhận
   `onFunctionChanged` / `onCustomizeFunctionValueChanged` / `onSupportedFunctionStatusChanged` thay vì hỏi định kỳ.
5. Cảm biến: `car.getSensorManager().getSensorLatestValue(int sensorId)` → `Float` (xem mục 4).
6. Mảng byte theo mã thuộc tính: `getBytesByPropID(int)` (đường dự phòng khi CarPropertyManager không có).

`THT Launcher` đã làm 1–3 trong `vehicle/EcarxCarSource.kt`.

## 2. Quy tắc gọi ICarFunction (quan sát)

| Việc | Hàm |
|---|---|
| Có hỗ trợ? | `isFunctionSupported(id[, zone])` → `FunctionStatus`; **`active` và `notactive` đều là có** (`notactive` = có nhưng đang tạm không dùng, vd. nhiệt độ khi điều hoà tắt) |
| Giá trị cho phép | `getSupportedFunctionValue(id[, zone])` → `int[]` |
| Đọc số nguyên | `getFunctionValue(id)` hoặc `getFunctionValue(id, zone)` |
| Ghi số nguyên | thử `setFunctionValue(id, value)` (không vùng) **trước**, không ăn mới gửi `setFunctionValue(id, zone, value)` |
| Đọc/ghi số thực | `getCustomizeFunctionValue(id[, zone])`, `setCustomizeFunctionValue(id, zone, float)` |

Vùng: `ZONE_ALL = 0x80000000`, hàng 1 trái `1`, hàng 1 phải `4`, cửa sổ trời `4`/`8` tuỳ chức năng.
Giá trị enum thường là `id + n` (vd. quạt mức 3 = `0x10020103`); `0` = tắt.

## 3. Mã chức năng ECARX thấy được

Cột "Gọi bằng": `get` = getFunctionValue, `getF` = getCustomizeFunctionValue, `set` = setFunctionValue,
`setF` = setCustomizeFunctionValue, `sup` = isFunctionSupported. Tên trong cột 3 là tên trong
`EcarxFunctions.kt` khi trùng; còn lại là suy luận.

| Mã | Gọi bằng (vùng) | Ý nghĩa |
|---|---|---|
| `0x10010100` … `0x10010400` | get/set | Điều hoà: nguồn, AUTO, A/C, A/C MAX (đã có) |
| `0x10020100` | get/set | Tốc độ quạt (đã có) |
| `0x10030100` | get/set | Gió trong/ngoài (đã có) |
| `0x10040100`, `0x10040300` | get/set | Sấy kính trước/sau (đã có) |
| `0x10050100`, `0x10050200` | get/set (zone 1/4) | Thông gió / sưởi ghế (đã có) |
| `0x10060100` | getF, setF (zone) | Nhiệt độ cài đặt (°C, số thực) (đã có) |
| `0x10060200`, `0x10060300` | getF | **Suy luận**: nhiệt độ min / max cho phép |
| `0x10060400` | getF | Bước nhiệt độ (đã có `HVAC_TEMP_STEP`) |
| `0x10060600` | get | **Chưa rõ**, nhóm nhiệt độ (có thể là đồng bộ trái/phải) |
| `0x10070100` | get/set | Hướng gió (đã có) |
| `0x10090100` | set không vùng, rồi zone 1 | Sưởi vô lăng (đã có) |
| `0x20020100` | get/set | **Chưa rõ** (nhóm cài đặt xe) |
| `0x20030600` | get | **Chưa rõ** (đọc khi khởi động) |
| `0x20150300` … `0x20150600` | sup, getF | **Suy luận**: nhóm độ sáng màn ngày/đêm (`0x20150100` = chế độ ngày/đêm đã có) |
| `0x21030000` | set (zone 8) | **Suy luận**: cửa sổ trời – lệnh chung |
| `0x21030300` | setF (zone 4/8) | **Suy luận**: vị trí cửa sổ trời / rèm (%) |
| `0x21030400` | get/set (không vùng, rồi zone 4) | Cửa sổ trời nghiêng (đã có) |
| `0x21030600` | get (zone 4/8) | **Suy luận**: trạng thái/vị trí cửa sổ trời |
| `0x21051000` | get/set | Đèn viền nội thất bật/tắt (đã có) |
| `0x22010100` | get/set | Chế độ lái (đã có) |
| `0x28070400` | sup, get (ZONE_ALL), getF | **Suy luận**: ga tự động / hỗ trợ lái (nhãn log "Cruise") |
| `0x29030500` | sup, getF/setF (ZONE_ALL) | **Suy luận**: độ sáng màn hình (số thực) |
| `0x2a010100` … `0x2a010300` | get/set/getF | Đèn viền: độ sáng, màu (đã có `AMBIENT_*`) |
| `0x2a040100`, `0x2a080101` | set | **Chưa rõ**, nhóm đèn viền (giá trị `0x200a0201`/`0x200a0203`) |

Danh sách ID được đăng ký nhận thay đổi: `0x10060100, 0x10050200, 0x10050100, 0x21030600, 0x21030400,
0x21051000, 0x2a010300, 0x22010100, 0x20020100, 0x10100300`.

## 4. Cảm biến ECARX – `getSensorLatestValue(id)` (suy luận theo ngữ cảnh)

| Mã | Ý nghĩa đoán | Căn cứ |
|---|---|---|
| `0x00100100` | Tốc độ xe (km/h) | dùng cho tự đóng cửa sổ trời theo tốc độ, chế độ chờ khi đỗ |
| `0x00200100` | Trạng thái máy / khoá điện | so với `0x00200106`, `0x00200107` (máy nổ) |
| `0x00100600`, `0x00404500` | Mức xăng (%) | nhận 0,1–100 |
| `0x00403000`, `0x00403100` | Liên quan xăng (lít / dung tích) | dùng trong phát hiện đổ xăng |
| `0x00100800`, dự phòng `0x00404300` | Quãng đường còn đi được (km) | nhận 0–2000, cạnh nhãn "endurance mileage" |
| `0x00100b00` | Nhiệt độ ngoài trời (°C) | nhận −60…70, dùng cùng thời tiết |
| `0x00100700`, `0x00100900` | Chưa rõ | |

## 5. Thuộc tính xe (CarPropertyManager)

VHAL chuẩn (quan sát): `FUEL_LEVEL 0x11600307` (ml), `INFO_FUEL_CAPACITY 0x11600104` (ml).

Thuộc tính riêng ECARX, kiểu `byte[]`, đọc bằng `getProperty(byte[].class, id, 0).getValue()`
(cần quyền `android.car.permission.CAR_VENDOR_EXTENSION`):

| Mã | Ý nghĩa | Giải mã |
|---|---|---|
| `0x287000E4` | Điện áp ắc quy 12 V (VPowerInfo) | 1–4 byte little-endian → số nguyên; >1000 thì mV, >100 thì 0,1 V; nhận 6–17 V. **Đã dùng** trong `CarPropertySource` |
| `0x28700207` | Xăng còn lại | số nguyên little-endian; `0`, `0xFF`, `0xFFFF` = không có; ≤110 → %; ≤1100 → đơn vị 0,1 |
| `0x287000A3`, `0x287000A5`, `0x287000A8`, `0x287000E6` | Chưa rõ (đọc cùng nhóm năng lượng/xăng) | |

## 6. Gói hệ thống liên quan (quan sát)

- Điều hoà gốc: `ecarx.hvac.app` (`ecarx.hvac.app.service.HvacAppService`, AIDL `ecarx.hvac.app.IOpenHvacAidlInterface`).
- Radio: `com.ecarx.radio.service` (`IRadioSvc`, `IRadioSvcCallback`), app `com.ecarx.radio`.
- Thông tin nhạc: `ecarx.xsf.mediacenter` (`IMediaCenterWidgetApiSvc`, action `ecarx.xsf.ACTION_MEDIA_CENTER_WIDGET_API_SERVICE`).
- Camera 360: `com.ecarx.avm.app`.

## 7. Trình phát gốc – `ecarx.xsf.mediacenter` (quan sát)

Bind `ComponentName("ecarx.xsf.mediacenter", "ecarx.xsf.mediacenter.MediaCenterService")` với action
`ecarx.xsf.ACTION_MEDIA_CENTER_WIDGET_API_SERVICE` (dự phòng: `setPackage`). Kiểm tra `interfaceDescriptor`
= `ecarx.xsf.mediacenter.IMediaCenterWidgetApiSvc`. Gọi binder trực tiếp (`writeInterfaceToken` → `transact` → `readException`):

| Giao diện | Mã | Tham số | Trả về |
|---|---|---|---|
| `IMediaCenterWidgetApiSvc` | 2 | – | `IMusicPlaybackInfo` (strong binder) |
| `IMediaCenterWidgetApiSvc` | 20 | int `3` | **chưa rõ** (gọi khi mở nguồn) |
| `IMediaCenterWidgetApiSvc` | 19 | – | **chưa rõ** |
| `IMusicPlaybackInfo` | 9 | – | int loại nguồn |
| `IMusicPlaybackInfo` | 5 / 6 / 2 | – | chuỗi tần số / tên đài / tên bài |
| `IMusicPlaybackInfo` | 11 | – | int đang phát (1 = có) |
| `IMusicPlaybackInfo` | 27 / 25 | – | chuỗi tên gói / tên app |

THT Launcher chỉ đọc (`media/MediaCenterSource.kt`); điều khiển bằng phím media chuẩn vì mã 19/20 chưa rõ nghĩa.

## 8. Radio – `com.ecarx.radio.service` (quan sát)

Bind action `android.intent.action.START_XCRADIO_SERVICE`, category `android.intent.category.XCRadio`,
component `com.ecarx.radio.service/.RadioService`; descriptor `com.ecarx.radio.service.IRadioSvc`.

| Mã | Ý nghĩa | Tham số | Trả về |
|---|---|---|---|
| 1 | Mở radio | strong binder (callback `IRadioSvcCallback`) | int ≠ 0 nếu nhận |
| 3 | Đặt băng | int: AM = 0, FM = 1 | – |
| 4 | Đặt tần số | int kHz | – |
| 17 | Chuyển nguồn âm thanh sang radio | – | – |
| 12 / 13 | Đọc băng / tần số (kHz) | – | int |
| 20 | Trạng thái nguồn | – | int; `0x1001`, `0x2000`, `0x2001`, `0x4000` = radio đang giữ âm thanh |

Tần số kHz: FM ≥ 30000 (98500 = 98,5 MHz), AM khoảng 522–1620. Lệnh dò kênh tự động chưa thấy. Dùng trong `radio/EcarxRadio.kt`.

## 9. Popup điều hoà gốc (quan sát + suy luận)

`IOpenHvacAidlInterface` (service `ecarx.hvac.app.service.HvacAppService`, dự phòng action `ecarx.hvac.app.HvacAppService`):
giao dịch **1** được dùng cả để mở lẫn để đóng cửa sổ điều hoà, nên **suy luận** là bật/tắt. THT Launcher chỉ gửi lệnh đóng
khi dịch vụ trợ năng thấy cửa sổ của `ecarx.hvac.app` vừa hiện (`TYPE_WINDOW_STATE_CHANGED`).

## 10. Chia đôi màn hình (Android 9, API ẩn `IActivityManager`) (quan sát)

1. Tìm task: `getAllStackInfos()` → `displayId`, `taskNames`, `taskIds`; app chưa chạy thì mở thường rồi chờ task xuất hiện.
2. Đưa task vào vùng chia: `startActivityFromRecents(taskId, bundle)` với `"android.activity.windowingMode"` = 3 (trái/chính)
   hoặc 4 (phải/phụ) và `"android:activity.splitScreenCreateMode"` = 0.
3. Tỉ lệ: `resizeDockedStack(Rect trái, null, null, null, null)` + `resizeStack(stackId phụ, Rect phải, true, false, false, -1)`;
   `stackId` phụ lấy từ `getStackInfo(4, 1)`. Tỉ lệ 20–80 %, mặc định 50.
4. Thoát: `dismissSplitScreenMode(false)`.

Quyền: `MANAGE_ACTIVITY_STACKS`, `START_TASKS_FROM_RECENTS`, `INTERNAL_SYSTEM_WINDOW` (bản ký platform). Dùng trong `split/`.

## 11. Nhúng app vào ô (ActivityView, Android 9) (đã thử trên emulator)

`android.app.ActivityView` cần 2 quyền hệ thống: `INJECT_EVENTS` (tạo bộ chuyển thao tác chạm cho màn hình ảo;
thiếu thì `Requires INJECT_EVENTS permission`) và `ACTIVITY_EMBEDDING` (mở app không khai báo `allowEmbedded`, vd. Google Maps;
thiếu thì `Permission Denial … launchDisplayId=…`). Cả hai chỉ có với bản ký platform.

## 12. Cách lấy mã chuẩn trên xe

Cài đặt → Xe → Chẩn đoán → **Lưu báo cáo đầy đủ**: báo cáo liệt kê toàn bộ hằng số số nguyên trong AdaptAPI của chính firmware
(tên → mã), trạng thái hỗ trợ và giá trị của mọi chức năng app dùng, cùng các mã ở mục 3 chưa dùng. Đây là nguồn chuẩn để
thay các mục "suy luận"/"chưa rõ" ở trên.
