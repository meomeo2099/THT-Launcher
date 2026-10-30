# Tài nguyên giao diện tham chiếu

Ảnh nền ngày/đêm và 64 biểu tượng PNG được trích từ mẫu APK người dùng cung cấp để đối chiếu và triển khai giao diện. Tên sản phẩm, package hoặc địa chỉ máy chủ của mẫu không được đưa vào các tài nguyên này. Không có tuyên bố cấp giấy phép mới hoặc chuyển quyền sở hữu đối với ảnh/biểu tượng.

Font Manrope và Elastre được phân phối cùng thông báo bản quyền và SIL Open Font License tại `OFL-Manrope.txt` và `OFL-Elastre.txt`.

- **Manrope:** giao diện tiếng Việt, trọng lượng 450/600; kiểm tra cmap cho bộ ký tự tiếng Việt đang dùng.
- **Elastre:** đồng hồ và số lớn. Font này không có đủ ký tự có dấu tiếng Việt, nên không dùng cho nhãn tiếng Việt.
- **waterfront-day.png / waterfront.png:** ảnh 1672×941, dựng theo center-crop ở kích thước cửa sổ.
- **icons/**: ảnh 256×256; biểu tượng chưa có ảnh đóng gói dùng vector dự phòng. Bộ icon riêng của người dùng vẫn được ưu tiên.

Màu và thông số giao diện được đối chiếu từ các hàm renderer/font của mẫu; mã Kotlin được viết lại cho cấu trúc THT Launcher.
