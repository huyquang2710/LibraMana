#Java Spring Boot - Quản lý thư viện -Phùng Huy Quang
## Môi trường phát triển
- Java 8
- Java Spring Boot + Spring Data Jpa + Thymeleaf
- DB MYSQL 8.26 

## Nội dung

### Login	
	Nhập thông tin user/password để thực hiện đăng nhập
	Password mã hóa Bcrypt
### Đăng ký user	
	Cần validate các trường user là duy nhất, địa chỉ email phải hợp lệ, số điện thoại chỉ được nhập số
### Lấy lại mật khẩu	
	Tự động tạo mật khẩu mới rồi gửi mail thông tin mật khẩu
### Thay đổi thông tin cá nhân	
	Chỉ được thay đổi thông tin của cá nhân
### Danh sách user	
	Chỉ có admin có chức năng này
### Quyền	
- Admin
    + có quền thêm, sửa, xóa và quản lý Sách, Tác Giả, Nhà Xuất Bản.
    + thêm Admin mới, quản lý user
- User
    + Chỉ được xem thông tin Sách, Tác Giả, Nhà Xuất Bản
    + Chỉnh sửa thông tin cá nhân.
    + Nếu User cố tình truy cập sẽ trả về /403
