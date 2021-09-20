#Java Spring Boot - Quản lý thư viện -Phùng Huy Quang
## Môi trường phát triển
- Java 8
- Java Spring Boot + Spring Data Jpa + Thymeleaf
- DB MYSQL 8.26 

## Nội dung

### Login	
   + Nhập thông tin user/password để thực hiện đăng nhập
   + Password mã hóa Bcrypt
   + Nếu Admin account đăng nhập sẽ tự động redirect đến trang quản lý.
   + Nếu User đăng nhập sẽ redirect đến trang home.
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
## Trang Chủ
	Có thể truy cập bằng anonymous
![image](https://user-images.githubusercontent.com/85112203/134086637-b68e066d-512a-40df-a0ad-67cbffd924ce.png)

## Sách
	Hiển thị sách , đã bao gồm search và phân trang
![image](https://user-images.githubusercontent.com/85112203/134087109-408bcafe-ce22-4845-b61c-3d02999978f3.png)

### Chi tiết sách
	Người dùng chỉ có thể xem chi tiết sách, không thể cập nhật hay xóa sách
![image](https://user-images.githubusercontent.com/85112203/134086891-919f0d8c-fc3c-4db9-b38d-6be1ac997152.png)

### User
![image](https://user-images.githubusercontent.com/85112203/134087224-54947ae5-4bae-4a5e-8a89-cb7c1975289c.png)

#### Khi User cố tình truy cập admin sẽ trả về /403
![image](https://user-images.githubusercontent.com/85112203/134087288-88dd2096-8cd1-429c-b79d-38b44028e36a.png)

### Admin
 - Giao diện trang Admin
 - Trang Sách, Tác Giả, Nhà Xuất Bản, Thể loại đề bao gồm chức năng CRUD, search, phân trang 
![image](https://user-images.githubusercontent.com/85112203/134087452-82d52a83-aa2a-4e4b-8ae0-e51426f8456a.png)
![image](https://user-images.githubusercontent.com/85112203/134087661-fd512d91-d706-461d-b4d1-e175c58077c9.png)
![image](https://user-images.githubusercontent.com/85112203/134087674-b5e87682-5963-4cba-bf8e-ac11dd438803.png)
![image](https://user-images.githubusercontent.com/85112203/134087695-232ea3bc-6030-4a67-8431-d5a66b3271f1.png)

- Thông tin cá nhân Admin
![image](https://user-images.githubusercontent.com/85112203/134087741-cb7ee140-014d-45dd-91b8-0612f96b885e.png)




