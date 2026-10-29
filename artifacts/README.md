# THT Launcher 0.3.74

Source 3300fc662a4d280faef674077ff8473677d3916d. Sửa xuất Chẩn đoán trên đầu xe không có DocumentsUI/trình lưu file.

Nút Lưu báo cáo đầy đủ tự tạo file UTF-8 trong Android/data/vn.tht.launcher/files/reports (fallback thư mục nội bộ nếu không có external-files), rồi mở nội dung có thể cuộn/chọn. Có Sao chép, Chia sẻ file và Chọn nơi lưu khác. Không cần DocumentsUI hay quyền storage để tự lưu/xem/sao chép. Tuỳ chọn Chọn nơi lưu khác vẫn cần trình hệ thống, nhưng thiếu trình đó không làm mất bản đã lưu.

Chia sẻ dùng FileProvider không exported, chỉ cấp đọc tạm cho reports/. Không gửi tự động. Nếu đầu xe không có ứng dụng nhận chia sẻ, dùng clipboard/chụp nội dung. Dữ liệu trên xe thật vẫn cần chủ xe gửi để tiếp tục xử lý dải trắng. Giữ các sửa stock UI, phiên nhúng và điều hoà một vùng của 0.3.73. Chưa hợp nhất main hoặc đưa vào tự cập nhật.

CI74 thành công: 96 JVM tests, 0 lỗi; chữ ký release xác minh. Runtime ReportProbe trên APK 0.3.74 đạt: tự lưu không dùng picker, báo cáo UTF-8 đầy đủ, ContentResolver đọc được URI reports/ của FileProvider không exported, clipboard khớp file. Lượt đầu harness dò static helper của AndroidX qua reflection không tìm thấy ở bản tối ưu; giữ log và sửa harness để đọc URI provider thực, không thay APK giữa hai lượt. Không gửi thử báo cáo cho bên ngoài.
