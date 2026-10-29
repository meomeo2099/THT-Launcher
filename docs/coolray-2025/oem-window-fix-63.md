# Bản 0.3.63: mở app, thanh OEM và vạch chia lệch

Người dùng xác nhận ảnh lỗi được chụp trên THT 0.3.62: bản đồ chiếm một phần màn hình, vùng phải đen; chia đôi có vạch/handle lệch nhau, thanh trái và thanh trạng thái trên vẫn xuất hiện. Các ca AOSP của 0.3.62 không bao phủ hết các trạng thái này; không thể suy từ chúng rằng firmware Coolray đã hết lỗi.

## Những thiếu sót tìm được trong mã

1. `policy_control` trước đây chỉ chứa package THT. Cờ fullscreen của cửa sổ launcher không ép một ứng dụng ngoài ẩn thanh hệ thống. `AppWindowPolicy` nay áp dụng `immersive.full` riêng cho app được chọn hoặc hai app trong cặp; giữ các clause khác và không ghi đè loại trừ rõ ràng của người dùng. Ghi lại baseline và giá trị đã ghi để khôi phục khi về Home, mở OEM hoặc tiến trình được tạo lại. Chỉ khôi phục khi giá trị hiện tại vẫn là của mình; không dùng wildcard để ẩn thanh trên mọi app, không force-stop SystemUI/OEM.
2. `leaveSplit()` chỉ biết phiên do owner THT hiện tại quản lý. Nếu một task vẫn nằm trong native split cũ của OEM, đường mở fullscreen nay kiểm tra và thoát cặp chứa đúng package được yêu cầu trước khi mở. Các timer sửa fullscreen vẫn bị generation vô hiệu hóa khi người dùng mở cặp THT mới.
3. Android có thể đặt nhiều task vào cùng standard stack mode 1. Bản trước chỉ xóa bounds khi toàn bộ stack thuộc một package, nên bỏ qua bounds nửa màn hình của stack dùng chung. Bản này cho phép trả frame của standard fullscreen stack trên display 0 về mặc định khi app được chọn đang trên cùng. Freeform dùng chung, HOME và app khác đang trước vẫn được bảo vệ. Đây là thay đổi có chủ đích so với điều kiện ownership chặt của bản 0.3.62: mọi task trong fullscreen stack dùng cùng frame.
4. Overlay của THT sử dụng `LayoutParams.x/y` như tọa độ màn hình. Firmware có thể thêm origin do inset OEM, khiến handle lệch khỏi ranh giới native. `ScreenOverlayPosition` đo `getLocationOnScreen()` sau layout và bù sai lệch có giới hạn, áp dụng cho cả dock nổi và divider. Đây cũng là bước có trong `sg.java`/`ug.java` của nguồn tham chiếu mà bản THT trước còn thiếu.
5. Bỏ vạch trắng tự vẽ của THT, dùng chỉ báo native sẵn có; vẫn giữ nút đóng/đổi bên. Vạch tự vẽ có tâm theo vùng trên dock, trong khi native lấy tâm màn hình, nên có thể xuất hiện hai handle dù cùng ranh giới ngang.
6. Khi kéo, mã cũ chỉ di chuyển overlay và đợi nhả tay mới resize hai app; vạch native vì thế còn ở vị trí cũ. Bản mới resize native trong lúc MOVE, giới hạn khoảng 70 ms/lần; lưu tỉ lệ khi UP. CANCEL trả về tỉ lệ lúc bắt đầu kéo. Tọa độ kéo luôn là pixel màn hình, không cộng inset lần nữa.

Phần Chẩn đoán bổ sung policy đang áp dụng và trạng thái stack thật (display, mode, bounds, standard, top activity, task IDs), giúp phân biệt cửa sổ app bị nhỏ với renderer bản đồ tự vẽ trong một phần cửa sổ.

## Kiểm chứng

Build debug/release tại workspace thành công. **92 kiểm thử JVM đạt**, gồm giữ nguyên policy khác/blacklist, ngăn chèn wildcard, phân biệt mixed fullscreen với freeform/HOME/foreground khác, và các bài hồi quy chia đôi trước đó.

Các ca runtime mới dùng release 0.3.63 và fixture trên AOSP API 28 1920×720:

| Ca | Trạng thái được tạo và xác nhận |
|---|---|
| `foreignsplit` | Tạo native split rồi bỏ ownership THT mà vẫn giữ stack 3/4; mở fixture fullscreen kết thúc split và trả các task về 1920×720. |
| `mixedbounds` | Cho A/B chung standard stack, ép frame còn 960×720; mở A và xác nhận stack được trả về 1920×720. |
| `policy` | Bắt đầu với clause navigation của package khác; thêm immersive cho fixture rồi restore đúng baseline. |
| `drag` | MOVE từ 50% sang 60% đã resize hai ô **trước UP**, tâm overlay trùng ranh giới thật trong 2 px; CANCEL về 50%, lần kéo/UP tiếp theo chốt 30%. |
| `offset` | Tạo lệch tổng hợp +40 px ngang/+72 px dọc trên view divider; helper đưa tâm về x=960 và top=0, rồi tiếp tục đúng khi bỏ độ lệch. Đây là thử mô phỏng toán tọa độ, không phải SystemUI Coolray. |

Mã fixture, log, kết quả và ảnh bản cuối ở [oem-runtime-63](oem-runtime-63/). Các ca hồi quy `visual`, `cancel`, `release`, `fullscreen`, `settings` chạy lại trên bản mới; xem kết quả có cấu trúc để biết trạng thái từng ca.

## Giới hạn cần giữ rõ

Chưa có kết nối trực tiếp tới đầu xe hoặc dump task/window của ảnh người dùng. Ảnh không đủ để khẳng định mọi thanh đang thấy thuộc SystemUI, launcher OEM hay thanh trợ năng THT. Immersive chỉ tác động các thanh mà firmware quản lý theo policy Android; một overlay riêng của OEM hoặc thanh bên THT do người dùng bật có thể vẫn hiện. Chẩn đoán đã có thêm kiểu thanh THT và overscan để nhận diện trường hợp này, không tự tắt lựa chọn thanh bên của người dùng.

Máy ảo chưa hỗ trợ activity chạy trên secondary display, nên vẫn chưa chứng minh handoff renderer của bản đồ đang chạy thật trong VirtualDisplay. Việc hiện cửa sổ fullscreen và việc renderer bản đồ cập nhật kích thước là hai điều cần kiểm tra riêng trên xe. Chưa có cơ sở khẳng định mọi phần đen trong ảnh có cùng nguyên nhân.

AOSP Android 9 `PhoneWindowManager.updateSystemBarsLw` buộc hiện status/navigation bar khi native split hoặc divider resize đang hoạt động (`mForceShowSystemBars`). Vì vậy ngay cả policy immersive theo package cũng không bảo đảm ẩn được hai thanh trong native split. Bản sửa không tuyên bố bỏ giới hạn framework này; sửa vị trí và loại bỏ chỉ báo kéo tự vẽ trùng lặp. [Nguồn AOSP](https://github.com/aosp-mirror/platform_frameworks_base/blob/android-9.0.0_r61/services/core/java/com/android/server/policy/PhoneWindowManager.java).
