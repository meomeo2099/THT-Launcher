# Hồ sơ phân tích ứng dụng tham chiếu

Ngày phân tích: **28/10/2026**. Hồ sơ tập hợp toàn bộ kết quả phân tích tĩnh đã thực hiện với REA 6.8.0, JADX headless 0.7.1 và các công cụ Android/ELF đối chiếu.

Tên sản phẩm, package, namespace JNI, nhãn log, tên file chứa nhận diện và địa chỉ nguồn/cập nhật của ứng dụng tham chiếu đã được chuyển sang tên trung tính. URL `example.invalid` là giá trị thay thế, không phải endpoint hoạt động. Phần mã phục hồi nằm trong tài liệu, không được đưa vào mã chạy của THT Launcher.

## Đọc từ đâu

| Nội dung | Tài liệu |
|---|---|
| Tổng hợp chi tiết bằng tiếng Việt | [Báo cáo tổng hợp](artifacts/deep/review-notes.md) |
| Phạm vi, số lượng và trạng thái hoàn tất | [Tóm tắt kiểm tra](artifacts/deep/review-summary.json) |
| Điều khiển xe, ECARX, Android Car, kiểm tra tính nguyên bản | [Vehicle](artifacts/deep/vehicle/findings.md) |
| Khởi động, tự động hóa, lịch trì hoãn, phát nhạc | [Automation](artifacts/deep/automation/findings.md) |
| GPS, khóa vùng, mạng, dữ liệu riêng tư, nhập file | [Data/network](artifacts/deep/data-network/findings.md) |
| Activity, ứng dụng, split-screen, overlay, Wi-Fi/Bluetooth | [UI/system](artifacts/deep/ui-system/findings.md) |
| Hai thư viện native ARM64 và ARMv7 | [Native](artifacts/deep/native/findings.md) |
| Chữ ký metadata, tải và cài APK | [Update](artifacts/update-audit/findings.md) |
| Receiver, trợ năng, notification listener | [Bề mặt giao tiếp](artifacts/surface-audit/findings.md) |
| Miễn trừ theo thiết bị và hạn sử dụng | [Device exemption](artifacts/region-audit/findings.md) |
| Chỉ mục đầy đủ ID và tham số quan sát REA | [Evidence index](artifacts/evidence-index.json) |
| Đổi tên, hash gốc/hash công bố và danh sách file | [Publication manifest](publication-manifest.json) |

## Phạm vi đã hoàn tất

- Một APK chứa 264 ZIP entries, một DEX và hai ABI native; không thấy lỗi CRC hoặc tên ZIP entry bị trùng.
- 561 định nghĩa lớp DEX, 485 lớp cấp cao theo JADX/REA; hai con số có phạm vi khác nhau vì lớp lồng/synthetic.
- 485 file Java phục hồi, khoảng 78.126 dòng; đây là biểu diễn suy ra, không phải mã nguồn gốc.
- Lập chỉ mục 33.267 direct invoke instructions; phân nhóm mạng, reflection, filesystem, preferences, SQLite, quyền và các API nhạy cảm.
- 71 bản ghi REA thành công. Các lần lỗi công cụ được giữ riêng và không dùng như kết quả phân tích APK thành công.
- Đọc cả hai thư viện native ARM64/ARMv7; đối chiếu assembly, exports, JNI, thuật toán hash và ELF hardening.
- 13 phương thức chưa phục hồi đầy đủ thành Java. Các nhánh nhạy cảm quan trọng được đọc thêm từ DEX; không suy ra lỗi ứng dụng từ placeholder của decompiler.
- Không chạy/cài/sửa/ký lại APK, không gửi lệnh tới xe và không gọi endpoint của ứng dụng để kiểm thử.

## Những phát hiện cần ưu tiên

| Mục | Quan sát đã xác nhận | Giới hạn tác động |
|---|---|---|
| Khóa ký phát triển | APK signer khớp chứng thư platform test AOSP | Đặc quyền thực tế phụ thuộc firmware có tin cùng khóa; không suy ra có root |
| Biến đổi mã lệnh xe khi kiểm tra nguyên bản lỗi | `r4.b()` thay tham số function/property ID bằng ID cộng offset trước khi gọi setter | Có thể bị SDK từ chối hoặc được diễn giải khác; chưa thử trên xe |
| Radio không kiểm tra người gửi | Receiver exported và receiver động nhận broadcast ngầm đều đưa action hợp lệ tới radio dispatcher | Cần app khác cùng thiết bị và radio/SDK khả dụng; không phải API điều khiển tùy ý toàn bộ xe |
| Tự kiểm tra dấu chứng thực | Hàm Java so fingerprint chứng thư, không tự xác minh đầy đủ chữ ký SourceStamp | Phân biệt với chữ ký file gốc đã được apksigner xác minh thành công |
| Lịch automation cũ | Thermal/airing/retry callbacks chưa thấy recheck generation hoặc enabled khi chạy | Tác động phụ thuộc service còn sống và firmware; chưa tái hiện actuator ngoài ý muốn |
| Metadata buffer trước limit | Đọc hết phản hồi vào RAM rồi mới kiểm tra giới hạn metadata/chữ ký | Nguy cơ cạn RAM có điều kiện; endpoint HTTPS, chưa chứng minh kẻ tấn công mạng cấp được phản hồi |
| ZIP font thiếu tổng cap | Có thể giữ 20 font, tối đa 25 MiB mỗi font | Có thể gần 500 MiB cộng overhead; cần người dùng import; chưa chứng minh RCE hoặc crash thực tế |

## Hành vi chức năng và quyền riêng tư

Ứng dụng là launcher native có widget, nhạc/radio, dẫn đường nhúng/chia đôi, overlay điều hướng, điều hòa và các nút xe. ECARX được nạp từ JAR cố định trong `/system/framework`; Android Car được dùng như đường tương thích. Nhiều API là hidden/vendor API nên khai báo quyền không chứng minh được cấp quyền hoặc hoạt động trên mọi firmware.

Automation toàn cục mặc định tắt. Foreground service vẫn có thể tồn tại cho thống kê, call-pause và popup điều hòa. Nhóm hành động gồm đóng cửa sổ trời theo tốc độ, sưởi/thông gió/airing, climate AUTO và mở app. Có giới hạn mức/nhiệt độ, kiểm tra tốc độ GPS mới và đọc lại/retry ở các đường đã mô tả. Chưa tìm thấy interlock chung tốc độ/số/phanh đỗ ở generic setter/widget; SDK/ECU có thể thực thi lớp bảo vệ riêng.

Khóa vùng thực sự tồn tại ngoài cơ chế miễn trừ theo Android ID. Không có miễn trừ còn hạn thì gate yêu cầu ngôn ngữ hệ thống `ru/be/kk`, rồi xét cache khóa GPS. GPS có kiểm tra mock/độ chính xác, polygon, vùng đệm biên và hysteresis. Thiếu GPS mới không tự khóa ngay; cache mặc định false nhưng trạng thái cũ vẫn có thể áp dụng. Gate giao diện không đủ chứng minh mọi service nền dừng.

Ba điểm tạo URL connection trong DEX thuộc unlock, update và weather. Weather gửi tọa độ làm tròn bốn số thập phân qua HTTPS; request đã xem không mang Android ID/VIN/history/notification text. Không thấy POST/upload telemetry, TLS trust-all, direct shell execution, WebView, SMS, camera hoặc ghi âm trong phạm vi callsite đã rà. Các kết luận âm tính này không bao phủ mọi đường reflection, firmware hoặc hành vi runtime chưa quan sát.

SQLite riêng của app lưu trips/days/voltage/refuels: thời gian, odometer, quãng đường, drive/idle, tốc độ tối đa, nhiên liệu và điện áp; schema đã xem không có cột tọa độ lộ trình. Chưa thấy mã hóa cấp app; mã hóa filesystem phụ thuộc thiết bị. Voltage có retention 30 ngày; chưa thấy tự hết hạn trips/refuels trong các đường đã rà.

## Các lớp bảo vệ đã quan sát

- Metadata cập nhật xác thực RSA4096/SHA256withRSA trước khi parse. APK tải về kiểm tra size, SHA-256, package và versionCode, có streaming cap và xóa cache lỗi.
- Provider cập nhật chỉ mở một file cache cố định ở mode đọc; chưa thấy lấy filesystem path từ URI.
- Trợ năng không yêu cầu lấy nội dung cửa sổ; callback event rỗng. Media actions kiểm tra creator package của PendingIntent.
- Backup settings dùng whitelist tên/kiểu, cap, staging và rollback; không thấy ZipSlip trên đường đã rà. Icon/image import có cap và kiểm tra kích thước.
- Hai native ABI có NX stack, RELRO/NOW, stack protector, không thấy segment W+X hoặc import exec/network/dlopen trong phạm vi đã đọc.

## Những hiệu chỉnh quan trọng

- Miễn trừ theo Android ID chỉ là một phần của khóa vùng; kiểm tra GPS/ngôn ngữ hệ thống được bổ sung ở lượt mở rộng.
- Java của `k8.Z` làm mất nhánh `continue`: DEX chứng minh chỉ xóa task của package dẫn đường được chọn, không phải kill mọi app.
- Native `Guard.probe` không có direct incoming reference trong static inventory. Không mô tả nó như gate đang hoạt động; `Guard.offset` gọi kiểm tra Java.
- Voice action kiểm tra quyền micro của package navigator rồi gửi broadcast giới hạn package; đó không phải bằng chứng launcher tự ghi âm.

## Cách đọc bằng chứng sau biên tập

Các JSON trong `artifacts/` là **bản dẫn xuất đã đổi tên**, không còn là raw Evidence nguyên byte. ID REA và SHA-256 mẫu gốc được giữ làm tham chiếu lịch sử; không dùng chúng để xác minh hash của nội dung đã biên tập. `publication-manifest.json` có hash gốc và hash công bố riêng cho từng file. Một số encoded identity/certificate literals được thay bằng marker; source phục hồi đã đổi tên không dùng để build hoặc kiểm tra chữ ký.

Tên sản phẩm đã được loại khỏi bản công bố; hồ sơ vẫn giữ fingerprint và hash kỹ thuật để đối chiếu. Đây là chuẩn hóa tên, không phải cam kết rằng mọi dấu vết kỹ thuật của mẫu đã được loại bỏ.

Không đưa APK gốc, DEX/.so dạng nhị phân hay JAR công cụ vào repo. Toàn bộ kết luận, báo cáo, source phục hồi dạng text, disassembly, inventory, script phân tích và chẩn đoán được lưu. Mã phục hồi là tài liệu về hiện vật được phân tích; không có tuyên bố chuyển quyền sở hữu hoặc cấp giấy phép mới cho mã đó.

## Giới hạn còn lại

Không có headunit/ECARX firmware, ECU hoặc installer bên ngoài để kiểm thử. Quyền Android thực tế, tác động actuator, interlock firmware, timing callback, silent install, mạng thực tế và khả năng khai thác chưa được xác nhận. Chưa có fuzzing hoặc chứng minh hình thức. Các phương thức decompile phức tạp được đánh dấu; source-status complete không đồng nghĩa semantics hoàn hảo.

## Lưu ý khi áp dụng cho THT Launcher

Các quan sát về ứng dụng tham chiếu không tự chứng minh hành vi tương ứng tồn tại trong THT Launcher. Khi dùng làm tài liệu thiết kế, ưu tiên từ chối lệnh rõ ràng khi xác thực lỗi, xác thực người gửi IPC, kiểm tra quyền và interlock theo profile, generation token cho lịch xe, giới hạn dữ liệu trong lúc stream và tổng ngân sách giải nén. Các thay đổi lần này chỉ thêm tài liệu và chuẩn hóa hai comment cũ; không thay logic chạy của launcher.
