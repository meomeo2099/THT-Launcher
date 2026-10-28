# Ghi chú tổng hợp phân tích mở rộng ReferencePanel 1.0.2

Ngày rà soát: 2026-10-28. Đây là sổ ghi nhận phân tích tĩnh, không phải chứng nhận an toàn hoặc kết quả chạy trên đầu xe.

## Định danh và phạm vi

- File người dùng cung cấp: `reference.apk`, 10,150,579 byte.
- SHA-256: `24ab6a3199655531ad80a1d651b961971896fad1715d93799f2bacb4ac93965c`.
- Package `org.reference.panel`; nhãn ReferencePanel; versionName 1.0.2, versionCode 16.
- minSdk và targetSdk 28; compileSdk 36.
- 264 ZIP entries, 241 asset files; một classes.dex, hai thư viện libdhguard.so ARM64/ARMv7.
- 561 raw DEX class definitions; JADX/REA hiển thị 485 lớp cấp cao. Hai số đo có phạm vi khác nhau vì lớp lồng/synthetic.
- Xuất phục hồi Java bằng API của đúng JAR JADX được REA ghim: 485 file, 3,664,755 byte, 78,126 dòng. Đây là supporting evidence, không phải bản nguồn gốc hoặc REA Evidence.
- Engine báo 8 errors và 71 warnings; kiểm tra file xuất thấy 13 phương thức thiếu Java trong 11 file. DEX disassembly vẫn giữ nguyên các phương thức này; placeholder `Method not decompiled` do công cụ tạo, không chứng minh app thật ném lỗi đó.
- Index toàn bộ 33,267 invoke instructions trong DEX. Các phép tìm không thấy API nhạy cảm chỉ có giá trị trong phạm vi trực tiếp của DEX đã đọc; reflection và firmware không thể được loại trừ bằng thống kê này.
- APK không được chạy, sửa, ký lại hoặc cài lên thiết bị. Không gọi endpoint của app để kiểm thử.

## Công cụ và tính tái lập

REA CLI 6.8.0; Node 24.22.1. JADX headless 0.7.1 JAR SHA-256 `6e5eacf500b64292bfb73c49797c1958f6ee44646e43e868039ae7feb573ff75`. Java 17/21 dùng trong các lượt; Java21 cho các lượt sau. GNU objdump 2.44 cho ARM64 và ARMv7. Android aapt/apksigner/dexdump và GNU readelf cung cấp đối chiếu độc lập.

`evidence-index.json` ở thư mục cha lưu ID, operation và parameters của từng kết quả REA thành công. `failed-observations.json` tách riêng lỗi công cụ; lỗi ownership/cleanup của tiến trình Java không được dùng làm bằng chứng hành vi APK.

## Kiến trúc quan sát

LauncherApplication đăng ký receiver radio động; MainActivity là HOME/LAUNCHER và dựng giao diện native. BootReceiver khởi tạo các helper khởi động. BootPlaybackService phục hồi/phát nhạc; AutomationService chạy tự động hóa. NavigationAssistService cung cấp điều hướng trợ năng; MusicNotificationListener xử lý media. `r4` phản chiếu SDK ECARX, `nj` điều phối điều khiển xe và fallback Android Car; `ni` lưu thống kê. `vj` xử lý thời tiết; `l0` cập nhật APK; `bf/pi/x6` xử lý vùng và danh sách miễn trừ; `Guard/hh` kiểm tra dấu chứng thực.

## Phát hiện ưu tiên và phân biệt độ chắc chắn

1. **Khóa ký phát triển AOSP:** fingerprint APK signer trùng platform.x509.pem chính thức của AOSP. Chữ ký v3 và SourceStamp của file đã được Android apksigner xác minh. Tính hợp lệ mật mã không đồng nghĩa khóa ký phát triển xác thực riêng danh tính nhà phát triển. Nguy cơ đặc quyền hệ thống cần firmware thực tế tin cùng khóa; chưa xác minh điều kiện đó.
2. **Guard có thể sửa mã lệnh ECARX:** REA `r4.b` chứng minh tên method bắt đầu bằng `s`, tham số đầu kiểu Integer và Guard.offset khác 0 sẽ làm thay đổi tham số đầu trước Method.invoke. Các wrapper setFunctionValue/setFunctionValueByZone/setCustomizeFunctionValue dùng đường này. Đây là hành vi xác nhận tĩnh; lệnh sai bị từ chối hay được firmware diễn giải thành chức năng khác còn chưa biết. Nên từ chối thao tác rõ ràng khi xác thực lỗi, không biến đổi function ID.
3. **Radio receiver không xác thực người gửi:** manifest receiver exported, không permission; onReceive chỉ lọc action và pd.c thực hiện radio controls/favorites. Dynamic jd receiver cũng không permission nhưng bỏ explicit intents để xử lý đường implicit. Không khẳng định mọi broadcast bị thực thi hai lần.
4. **GENUINE chưa là full signature verification:** hh.a tìm chứng thư trong signing block và so fingerprint; không tự xác minh đầy đủ chữ ký SourceStamp. Guard.offset cache kết quả. Đây là hạn chế cơ chế tự kiểm tra, phân biệt với xác minh apksigner đã thành công trên file gốc.
5. **Metadata cập nhật buffer trước limit:** l0.f -> pi.M -> ByteArrayOutputStream đọc toàn bộ rồi mới kiểm tra64KiB/8KiB. Phản hồi quá lớn có thể cạn RAM; chưa chứng minh kẻ tấn công mạng không xác thực có thể cấp phản hồi vì endpoint HTTPS.
6. **ZIP font thiếu tổng ngân sách giải nén:** mỗi font đọc có hạn nhưng giữ nhiều font đồng thời; điều kiện kích hoạt là người dùng nhập archive. Chưa thực hiện thử gây cạn RAM.
7. **Tự động hóa hành động trì hoãn:** cần phân biệt callback app-autostart có generation guard với callback điều khiển nhiệt/xe. Rà soát chi tiết và các giới hạn xem automation/findings.md, không gọi đây là lỗi đã tái hiện trên xe.

## Những lớp bảo vệ đã quan sát

- Metadata cập nhật được kiểm tra detached RSA4096 SHA256withRSA trước khi parse; khóa nhúng khớp SourceStamp ReferencePanel.
- APK tải về kiểm tra size, SHA256, package và versionCode; streaming cap200MiB; cache lỗi bị xóa; mirror lấy từ metadata có chữ ký.
- Cài qua PackageInstaller hoặc explicit component bên ngoài với URI read-only; không suy ra silent install từ khai báo permission hoặc default update_auto.
- Provider cập nhật không exported; services trợ năng/thông báo có Android bind permission; không coi mọi exported service đều là lỗ hổng.
- Callback accessibility rỗng, canRetrieveWindowContent=false. Media action kiểm tra creator package và loại Activity PendingIntent.
- Native hai ABI có NX stack, RELRO/NOW, stack protector, không segment W+X. Không thấy import exec/network/dlopen trong hai thư viện đã đọc; điều này không bao phủ hành vi Java/firmware.

## Vùng sử dụng và đính chính phân tích trước

Phân tích trước mới đọc danh sách miễn trừ theo ANDROID_ID. Rà soát mở rộng xác nhận app còn khóa theo **ngôn ngữ hệ thống** và **GPS**, không chỉ mã thiết bị.

`bf.a` chấp nhận miễn trừ còn hạn trước; nếu không, yêu cầu ngôn ngữ hệ thống ru/be/kk và kiểm tra cache region_locked_gps. App-language lựa chọn trong giao diện không phải biến được đọc ở gate này. GPS kiểm tra polygon trong asset geo/allowed.txt, có chống mock/độ chính xác kém và hysteresis. MainActivity hiển thị lớp chặn trong app khi không đủ điều kiện. Không có phép thử tại Việt Nam; suy luận tương thích ở Việt Nam phải giữ điều kiện ngôn ngữ/vị trí/cache/miễn trừ.

Danh sách miễn trừ tải qua HTTPS và kiểm tra RSA trước khi cache; mã thiết bị được suy ra từ ANDROID_ID, có ngày hết hạn tùy chọn dùng LocalDate.now. Đây là kiểm soát truy cập sản phẩm, không tự chứng minh hành vi mã độc. Chi tiết trong data-network/findings.md.

## Native và tính reachable

Hai ABI cùng 20-entry lookup table của resolve và cùng phép hash certificate của probe. Probe so với fingerprint SourceStamp ReferencePanel nhưng GET_SIGNATURES bình thường trả APK signer AOSP. Vì vậy nếu gọi dưới semantics Android chuẩn sẽ không match. Không có direct DEX call; REA incoming references báo0 trong static node inventory. Không được trình bày probe như gate đang hoạt động. Guard.offset đang thấy gọi hh.a, còn Guard.id dùng resolve.

## Mạng/quyền riêng tư và giới hạn kết luận

Các HTTP connection construction được tìm thấy tại pi.k, l0.u và luồng weather trong l.run. Endpoint chức năng là GitHub update/unlock và MET Norway weather; hướng network cụ thể, cache local và import xem data-network/findings.md. Weather gửi lat/lon làm tròn4chữ số thập phân; không gọi đây là dữ liệu vị trí thô hoàn toàn nhưng vẫn khá chi tiết.

Không thấy direct shell execution/WebView/SMS/camera/microphone-recording calls trong index DEX, và không thấy network/exec import trong hai thư viện. Đây là bounded negative observation, không bảo đảm không có telemetry ẩn qua mọi reflection/SDK/firmware hoặc trong phiên bản khác.

## Phần chưa xác nhận

Không có headunit/firmware/SDK ECARX và APK com.teto.inputservice để kiểm thử. Không xác minh quyền Android thực tế được cấp, khả năng cài không tương tác, side effect lệnh xe, timing khi động cơ/tốc độ đổi, lưu lượng mạng thực tế, hoặc khả năng khai thác các rủi ro. Không fuzzing/symbolic execution/full formal proof. UI rendering phức tạp có phương thức Java không phục hồi được; DEX gốc được giữ để kiểm tra sau.

## Chi tiết bổ sung đã đối chiếu trong lượt mở rộng

### Điều khiển xe và phản hồi

Các đường hỗ trợ gồm điều hòa/quạt/hướng gió, nhiệt độ hai vùng, sưởi/thông gió ghế, sưởi vô-lăng/kính/vòi phun, cửa sổ trời/rèm, ambient, chế độ lái, setting start-stop, cốp/gương/đèn ngoài/gạt mưa bảo dưỡng và một số tùy chọn hỗ trợ lái. Đây là các chức năng có tên và đường gọi cụ thể trong mã; không chứng minh firmware bất kỳ cho phép mọi thao tác.

Nguồn quan sát giới hạn nhiệt độ17–32°C theo bước0,5, cửa sổ trời0–100%, sưởi vô-lăng off/1–3; nhiều enum/function được whitelist. ACC/G-Pilot có capability/current-mode check, cooldown6giây, đọc lại sau khoảng4giây. Các generic setter/widget đã xem không có một speed/gear/parking-brake interlock chung; interlock có thể nằm trong SDK/ECU nên không suy ra xe thiếu bảo vệ.

UI cache trạng thái mong muốn6–12giây. Cần phân biệt mong muốn của UI, SDK trả true và cảm biến/actuator xác nhận thực tế. Guard.offset sửa function ID là hành vi tĩnh xác nhận, còn hệ quả vật lý chưa được thử.

### Khởi động, dịch vụ và tự động hóa

BOOT_COMPLETED gọi helper automation/playback và mở launcher khi autostart (mặc địnhtrue). Music/radio autoplay mặc địnhfalse, giới hạn một lần/boot. Boot playback chọn đúng package người dùng cấu hình và cần quyền notification listener, có watchdog.

Automation toàn cục mặc địnhfalse; service vẫn có thể chạy vì stats_enabled/call_pause_music/Anton popup mặc địnhtrue. Service foreground có notification và START_STICKY; điều này không tự chứng minh persistence bí mật. Poll2giây khi bật tự đóng cửa sổ trời, ngược lại5giây.

Tự đóng cửa sổ trời theo ngưỡng mặc định30km/h, hysteresis5km/h; tốc độ lấy max của xe và GPS mới≤5giây, không mock. Các nhóm sưởi/thông gió/airing/climate/apps kích hoạt một lần mỗi chu kỳ động cơ. Nhiệt độ ưu tiên cảm biến xe đã kiểm tra miền giá trị, fallback weather cache≤3giờ. App autostart có generation token hủy lịch cũ; callback thermal/airing/retry chưa thấy kiểm tra lại generation hoặc auto_enabled. Callback có thể tồn tại sang chu kỳ khác khi service tiếp tục chạy; onDestroy có hủy callback. Cần device test để đo tác động, chưa là exploit đã xác nhận.

Code call-pause dùng TelephonyManager nhưng manifest thiếu READ_PHONE_STATE. listen/getCallState bị từ chối được catch/log và service tiếp tục, vì vậy không được nói app chắc chắn đọc được trạng thái gọi trên Android chuẩn. Không có bằng chứng đọc số điện thoại hoặc ghi âm ở đường này. Worker ReferencePanel-automation chưa thấy quit thread khi service teardown, là khoảng trống cleanup cần kiểm thử vòng đời.

### Khóa vùng và điều kiện thiếu GPS

Nếu có miễn trừ còn hạn thì gate cho qua. Nếu không, ngôn ngữ hệ thống ngoài ru/be/kk tạo khóa LANGUAGE ngay; ngôn ngữ phù hợp mới xét cache GPS. Không có GPS mới không tự động gây khóa vị trí ngay: cache defaultfalse, nhưng giá trị đã lưu vẫn áp dụng.

GPS bỏ qua mock, reported accuracy>300m và0,0. Trong polygon hoặc cách biên<khoảng5km thì bỏ khóa/counter. Bên ngoài cần≥3fix và≥60giây từ fix đầu để khóa; đã kiểm tra nhánh gốc DEX. UI restriction không đủ chứng minh mọi service nền dừng. Refresh exemption10phút khi bị chặn/24giờ khi không chặn. Với thiết bị dùng tại Việt Nam, khả năng bị chặn là suy luận có điều kiện theo ngôn ngữ hệ thống/vị trí/cache/miễn trừ, không phải kết quả chạy thử.

### Dữ liệu và import

stats.db dùng SQLiteOpenHelper bình thường, chứa trips/days/voltage/refuels: thời gian, odometer, quãng đường, drive/idle, maxspeed, tiêu thụ/nhiên liệu và điện áp. Schema không có cột route coordinates. Không thấy mã hóa cấp app; filesystem encryption do thiết bị quyết định. Voltage được dọn khi quá30ngày, kiểm tra theo nhịp6giờ; chưa thấy tự hết hạn trips/refuels trong các đường delete đã rà. allowBackup=false giảm khả năng Android backup chuẩn, không bảo vệ khỏi root/firmware.

Backup settings ZIP có staging/rollback,≤100entries,32MiB/entry,256MiB tổng,settings JSON≤4MiB, whitelist tên/kiểu preference, ảnh≤8192mỗi chiều và≤24MP. Không thấy ZipSlip ở đường này. Whitelist không nhận region_pass_list/region_locked_gps/weather cache; có nhận cấu hình automation/component hợp lệ nên nội dung backup vẫn ảnh hưởng hành vi sau restore. Archive export không có encryption/authentication được quan sát.

Icon ZIP≤300file được chấp nhận,2MiB/file,40MiB tổng,≤1024px. Font ZIP giữ tối đa20font, mỗi font≤25MiB, thiếu tổng cap nên có thể gần500MiB mảng font+overhead; user phải chọn import, chưa thử crash. Các giới hạn accepted entries không bảo đảm mọi archive đều có tổng thời gian/giải nén hữu hạn hợp lý. Image import thông thường copy≤25MiB rồi downsample1600/512trên các đường đã xem, dùng AtomicFile.

UpdateApkProvider chỉ mở cacheDir/update.apk ở mode r, không biến URI path thành filesystem path. Weather gửi lat/lon4chữ số thập phân, cache private; unlock tải toàn bộ signed list không gửi mã thiết bị trong URL đã xem. Không thấy clipboard-read, POST/upload/telemetry/TLS-trust-all trong các callsite đã rà; vẫn là kết luận có phạm vi tĩnh.

### Hành vi hệ thống và sửa lỗi đọc decompile

Launcher quản lý drawer, split-screen, ActivityView, overlay, brightness/audio/Wi-Fi/BT qua API công khai và hidden/vendor. Restart navigation dùng force-stop/removeTask đối với package được chọn. Java k8.Z có lỗi mất continue khiến nhìn như xóa mọi task; DEX chứng minh bỏ qua package không khớp, nên không báo lỗi kill-all.

Lệnh voice kiểm tra RECORD_AUDIO của package navigator rồi gửi broadcast giới hạn package dd.call_alice; không phải app tự ghi âm. MainActivity.onActivityResult yêu cầu RESULT_OK/Intent/URI hợp lệ trước import. Uninstall dùng ACTION_DELETE chuẩn cho app không phải hệ thống; chưa thấy silent uninstall. Helper Wi-Fi/BT có fallback Settings khi direct toggle thất bại. Tính khả dụng các hidden/vendor API chưa được thử trên firmware.

## Chỉ mục hồ sơ phân tích

- ../manifest.txt, ../badging.txt, ../signature.txt: Android tool observations.
- ../rea-package.json, ../rea-apk-signature.json: REA package/signature observations.
- ../update-audit/findings.md: cập nhật/cài đặt.
- ../surface-audit/findings.md: receiver/accessibility/notification entrypoints.
- native/findings.md: hai ABI và reachability.
- vehicle/findings.md: ECARX/AndroidCar/Guard effect.
- automation/findings.md: boot/triggers/timers/autostart.
- data-network/findings.md: GPS, mạng, dữ liệu, file import.
- ui-system/findings.md: lifecycle, app management, overlays/settings.
- source/: phục hồi Java hỗ trợ; class-inventory.tsv lưu từng hash và export-provenance.txt lưu settings/counts.
- sensitive-call-index.json, static-index-summary.json: toàn bộ direct invoke categories.
- decompilation-coverage.json: lỗi/cảnh báo mã phục hồi.
- apk-inventory.json, integrity-verification.json: inventory hash và kiểm tra CRC/duplicate/hash không đổi.
