# Đối chiếu và sửa chia màn hình — 29/10/2026

## Kết luận từ mã tham chiếu

Ứng dụng tham chiếu dùng **chia đôi native của Android**, qua `IActivityManager`, không nhúng hai ứng dụng bằng VirtualDisplay. Chức năng bản đồ nhúng và chức năng chia đôi là hai luồng khác nhau. Chỉ mở app bằng intent không đủ để tạo hoặc kết thúc một cặp native đúng cách.

Bằng chứng trong [nguồn phục hồi](../reference-analysis/artifacts/deep/source/):

| Nguồn | Cơ chế quan sát được |
|---|---|
| `k8.J0`, `k8.java:569–688` | Điểm vào chia đôi thực tế, lấy kích thước màn hình vật lý bằng `getRealSize`; tính vạch chia bằng `stableLeft + (width - stableLeft) × ratio / 100`; primary bắt đầu tại x=0, đáy nằm trên dock. |
| `eh.f`, `eh.l`, `dh.run` | Tìm task đúng package trên display 0; lấy/mở ứng dụng phải rồi trái trước khi chuyển mode. |
| `eh.d` | `startActivityFromRecents` chỉ thành công khi kết quả là số nguyên không âm. |
| `qa.run` | Đưa trái vào mode 3, đợi 300 ms, đưa phải vào mode 4; có chuỗi chuyển lại primary/secondary cách nhau 250 ms nếu trạng thái ban đầu chưa đúng. |
| `eh.g` | `resizeDockedStack` cho trái, `resizeStack` cho phải. |
| `rg`, `p.run` | Theo dõi khoảng 700 ms, đọc vị trí primary thật và sửa đáy tránh dock sau khi hình học ổn định. |
| `eh.c`, `eh.e`, `dh` | Generation dùng để bỏ qua một số callback cũ. |
| `eh.a` | Yêu cầu dismiss split và xóa trạng thái cặp. |

Giới hạn của chính bản tham chiếu: nhánh mở phải không kiểm tra đầy đủ kết quả Binder, điều kiện hoàn tất chưa xác nhận chặt cả hai task và kích thước, dismiss không vô hiệu hóa mọi generation; có nhánh chỉ ghi log/toast khi resize thất bại. Vì vậy THT giữ cơ chế native tương ứng nhưng bổ sung xác nhận và khôi phục lỗi. `k8.V` là đổi thứ tự danh sách cặp đã lưu, không phải bằng chứng cho thao tác đổi hai ô đang chạy.

## Sai sót đã phát hiện và bản sửa THT

| Tình huống | Rủi ro trước sửa | Hành vi hiện tại |
|---|---|---|
| Đóng lúc ứng dụng đang mở | Callback còn chạy có thể tạo lại nửa màn hình sau khi đóng | Hủy callback và tăng generation; kiểm tra generation tại mọi bước chờ. |
| Một ứng dụng không khởi chạy | Chuyển trái trước khi có task phải, để lại ô thiếu | Có đủ hai task khác nhau trước khi thay windowing mode. |
| Binder trả lỗi âm | Có giá trị trả về vẫn bị coi là thành công | Chỉ nhận `Int >= 0`; lỗi dẫn tới khôi phục phiên đang sở hữu. |
| Firmware không có API/không đủ quyền | Có thể bắt đầu nhưng không thể kết thúc | Kiểm tra quyền quản lý stack, overlay, API mở/resize/dismiss và hai activity trước khi thay đổi task. |
| Cặp đang hoạt động bị thay bằng cấu hình lỗi | Mất điều khiển cặp cũ | Kiểm tra yêu cầu mới trước khi đóng cặp đã hoàn tất; yêu cầu đang chờ thì bị hủy trước. |
| Chuyển cửa sổ chưa hoàn tất | Đánh dấu thành công dựa vào thời gian cố định | Đọc lại mode, display, task trên cùng, visibility và bounds cả hai ô trước khi công nhận hoạt động. |
| Android ghi đè resize sau animation | Sai tỉ lệ sau chuyển hoặc đổi ô | Đọc lại hình học, đặt lại tối đa hai lần trong bước mở; hết giới hạn thì khôi phục, không công nhận sai kích thước. |
| Launcher tự nằm trong ô phải | Lấy kích thước cửa sổ launcher khiến chia tiếp bên trong nửa phải | Chụp kích thước vật lý và stable inset trước khi chuyển task; không lấy root view đang co nhỏ làm toàn màn hình. |
| Tác vụ sửa fullscreen cũ còn hẹn giờ | Kéo một ô khỏi cặp vừa mở | Hủy tác vụ sửa khi bắt đầu split; tác vụ fullscreen không can thiệp mode 3/4. |
| Mở ứng dụng, Home THT, Settings hoặc Stats | Chuyển ra ngoài nhưng để task có bounds nửa màn hình | Kết thúc cặp đang mở hoặc đang chờ trước khi mở cửa sổ toàn màn hình; xóa bounds override thuộc task của cặp. |
| Kéo vạch native/firmware thay đổi bounds | So sánh cache mong muốn nên không phát hiện trạng thái thật | Theo dõi bounds thực mỗi 700 ms, chờ ổn định rồi cập nhật tỉ lệ và đáy trên dock; giới hạn số lần sửa. |
| Mất một ô hoặc Home thay ô phải | Có thể để lại primary thiếu điều khiển | Khôi phục cặp thuộc THT. Nếu người dùng đã thay bằng cặp task khác, bỏ ownership và không đóng cặp của họ. |
| Activity bị hủy giữa giao dịch | Dừng callback nhưng bỏ lại nửa màn hình | Thử khôi phục khi release; nếu thất bại giữ ID để owner mới thử dọn lại trong cùng tiến trình. |
| Kéo bị CANCEL, xóa cặp đang chờ | Lưu nhầm tỉ lệ hoặc callback mở lại cặp đã xóa | CANCEL trả lại tỉ lệ cũ; xóa chỉ tiếp tục sau khi kết thúc được phiên đang sở hữu. |

Mã chính: `SplitSession` quản lý vòng đời; `SplitGeometry` tính bounds; `SplitOwnership` quyết định quyền dọn; `SplitWatchPolicy` giới hạn sửa; `SplitScreen` giao tiếp Android. Luồng `ExternalAppLauncher`, `LauncherHome` và các điểm mở màn hình trong `MainActivity` cùng dùng quy tắc thoát split.

## Kiểm thử

**84 kiểm thử JVM đạt, 0 lỗi**; build debug và release thành công. Các bài kiểm tra bao gồm generation/hủy, kết quả Binder âm, thiếu task, thất bại từng bước, giới hạn chờ/resize, ownership và khôi phục, stable inset, dock và chế độ fullscreen.

Kết quả cuối và dữ liệu runtime được lưu trong [split-runtime](split-runtime/). Fixture A/B là ứng dụng kiểm thử do dự án tạo, có activity cho phép resize. Probe chạy trong tiến trình THT để kiểm tra coordinator và đọc stack native thật; không được đóng gói vào APK THT.

Môi trường: AOSP Android 9/API 28, 1920×720, 160 dpi, hỗ trợ native split, APK release ký bằng khóa thử nghiệm AOSP công khai trùng framework máy ảo. Không chạy APK tham chiếu hoặc gọi dịch vụ điều khiển xe trong phép thử này.

**5/5 ca instrumentation bản release cuối đạt**: `visual`, `cancel`, `release`, `fullscreen`, `settings`. Xem [kết quả có cấu trúc](split-runtime/results.json). Ca chính xác nhận bounds trái/phải lần lượt `0..960 / 960..1920`, `0..576 / 576..1920`, `0..1152 / 1152..1920`, sau đổi ô `0..768 / 768..1920`; đáy cả hai là 572. Sau close không còn stack mode 3/4, task fixture trở lại bounds `0,0..1920,720`.

Ca thử mở cặp còn đồng thời tạo các timer sửa fullscreen từ lần mở app trước, đợi qua mốc 3 giây để xác nhận timer cũ không phá cặp mới. Sau đó đặt 30%, cố ý thay native bounds thành 60% kéo tới đáy 720, kiểm tra watcher đưa đáy về 572, đổi hai app và đóng. Ca riêng kiểm tra close/release/mở fullscreen ngay trong cùng main-thread turn sau khi yêu cầu mở split; đợi 3,5 giây để phát hiện callback cũ mở lại. Ca Settings xác nhận đường mở ForResult cũng kết thúc chia đôi.

Lần thử đầu đã bắt được hình học sai `965..1442 / 1442..1920`, do dùng root view của launcher đang nằm bên phải. Lần tiếp theo xác nhận 50%, 30%, sửa native 60% nhưng phát hiện resize bị animation đổi ô ghi đè; giao dịch tự rollback. Hai lỗi này được sửa trước bản kiểm chứng cuối, không được tính là ca đạt.

## Giới hạn trên xe thật

Máy ảo chứng minh giao dịch với Android 9 native split và các fixture; chưa chứng minh mọi ứng dụng bản đồ/player hoặc firmware Coolray hỗ trợ resize tương tự. Một activity khóa tỉ lệ, không resizable hoặc OEM chặn API có thể bị từ chối; ứng dụng sẽ cố khôi phục thay vì tuyên bố đã mở đúng.

Metadata khôi phục khi Activity được tạo lại hiện nằm trong bộ nhớ tiến trình. Sau hard kill, reboot hoặc mất điện, không bảo đảm giữ được ownership để tự dọn phiên cũ. Khi không nhận diện được cặp, THT từ chối chiếm một phiên chia đôi bên ngoài. Cần kiểm tra thêm trên xe: inset thanh OEM, đổi độ phân giải, bản đồ thực tế, Home vật lý và ngủ/thức.

## Điều chỉnh so với đường Recents của tham chiếu

Ca hủy ngay sau `startActivityFromRecents(mode=3)` trên AOSP API 28 đã làm `system_server` lỗi `Drag resize mode not allow ... dragResizeMode=1`. Đây là lỗi framework quan sát được, không phải ca đạt. Đối chiếu AOSP `ActivityStackSupervisor.startActivityFromRecents` cho thấy nhánh primary luôn thêm task vào `mResizingTasksDuringAnimation`; khi chuyển về fullscreen quá sớm, `notifyAppTransitionDone` gọi `Task.setDragResizing(false, DOCKED_DIVIDER)` trên stack không còn cho phép dock resize.

THT chuyển riêng bước primary sang `setTaskWindowingModeSplitScreenPrimary(task, 0, true, false, initialBounds, false)`: vẫn là native split, cùng mode 3 và quyền quản lý stack, nhưng không dùng bookkeeping animation riêng của Recents. Secondary vẫn qua `startActivityFromRecents(mode=4)` và kiểm tra mã kết quả. API direct-primary được kiểm tra trước khi mở cặp; firmware không có API sẽ bị từ chối trước khi thay đổi task. Đây là khác biệt chủ đích để tránh lặp lại lỗi đã quan sát, không tuyên bố sao chép nguyên từng lệnh của tham chiếu.

Nguồn AOSP đối chiếu: [ActivityStackSupervisor.java](https://github.com/aosp-mirror/platform_frameworks_base/blob/android-9.0.0_r61/services/core/java/com/android/server/am/ActivityStackSupervisor.java), [ActivityManagerService.java](https://github.com/aosp-mirror/platform_frameworks_base/blob/android-9.0.0_r61/services/core/java/com/android/server/am/ActivityManagerService.java), [Task.java](https://github.com/aosp-mirror/platform_frameworks_base/blob/android-9.0.0_r61/services/core/java/com/android/server/wm/Task.java). Trace lỗi thử nghiệm lưu trong `split-runtime/emulator-crash-before-fix.txt`.

Ảnh kiểm chứng bản cuối: [50/50](split-runtime/screenshots/final-open50_after_old_fullscreen_timers.png), [30/70](split-runtime/screenshots/final-ratio30.png), [native 60/40 được sửa đáy](split-runtime/screenshots/final-native_bounds_refitted60.png), [đổi app 40/60](split-runtime/screenshots/final-swapped40.png).
